package mrabezreb.darzil.cheat

import javax.swing.JFrame
import mrabezreb.darzil.Handler
import javax.swing.WindowConstants
import javax.swing.JTextField
import java.awt.Dimension
import javax.swing.JTextArea
import javax.swing.JButton
import mrabezreb.darzil.gfx.Assets
import javax.swing.SwingConstants
import javax.swing.JPanel
import java.awt.BorderLayout
import mrabezreb.darzil.input.KeyManager
import java.awt.event.KeyEvent
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import co.technius.scalua.Globals
import co.technius.scalua.LuaFunction
import co.technius.scalua.LuaValue
import co.technius.scalua.LuaTable
import co.technius.scalua.LuaInt
import co.technius.scalua.LuaString
import mrabezreb.darzil.entity.Entity
import mrabezreb.darzil.entity.statics.Tree
import org.reflections.Reflections
import org.reflections.util.ConfigurationBuilder
import org.reflections.scanners.TypeAnnotationsScanner
import mrabezreb.darzil.entity.Spawnable
import mrabezreb.darzil.entity.Spawnable
import mrabezreb.darzil.entity.Spawnable
import java.lang.annotation.Annotation
import scala.collection.mutable.ArrayBuffer
import java.lang.reflect.Constructor
import mrabezreb.darzil.entity.Spawnable
import org.reflections.scanners.SubTypesScanner
import scala.annotation.Annotation
import java.lang.annotation.Annotation
import org.reflections.util.ClasspathHelper
import co.technius.scalua.LuaDouble

object Console {
  
  val width = 300
  val height = 300
  
  val luaG = Globals.standard()
  
  val print = LuaFunction.simple { luav =>
    consoleOutput.append(luav.toString() + "\n")
    LuaValue.Nil
  }
  
  val ref = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("mrabezreb.darzil.entity")).setScanners(new TypeAnnotationsScanner(), new SubTypesScanner()))
  
  val spawnabless = ref.getTypesAnnotatedWith(classOf[Spawnable])
  val it = spawnabless.iterator()
  val spawnables = ArrayBuffer.empty[Class[_]]
  val spawnablesL = LuaTable()
  var in = 0
  while(it.hasNext()) {
    in += 1
    val i = it.next()
//    println(i.getSimpleName)
    spawnables.append(i)
    spawnablesL.update(in, i.getSimpleName)
  }
  
  val entityManager = LuaTable()
  entityManager.update("add", LuaFunction.singleReturn { li =>
    val tipe = LuaString.unapply(li(0)).get
    val x = li(1).wrapped.todouble()
    val y = li(2).wrapped.todouble()
    val chosen = spawnables.filter { clss => clss.getSimpleName.equals(tipe) }(0)
    val const = chosen.getDeclaredConstructor(classOf[Double], classOf[Double])
    var entity: Entity = const.newInstance(double2Double(x),double2Double(y)).asInstanceOf[Entity]
    Handler.world.entityManager += entity
    LuaValue.Nil
  })
  
  luaG("print") = print
  luaG("entityManager") = entityManager
  
  var frame = new JFrame("Cheat Console")
  frame.setSize(width, height)
  frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE)
  frame.setResizable(false)
  frame.setLocationRelativeTo(null)
  frame.setVisible(false)
  
  var commandPane = new JPanel()
  
  var commandSend = new JButton("Send")
  commandSend.addActionListener(new ActionListener() {
    def actionPerformed(event: ActionEvent): Unit = {
      val command = commandEntry.getText
      commandEntry.setText("")
      luaG.load(command).invoke()
    }
  })
  commandSend.setFont(Assets.dejavumono.deriveFont(20f))
  commandSend.setHorizontalAlignment(SwingConstants.RIGHT)
  
  var commandEntry = new JTextField()
  commandEntry.setPreferredSize(new Dimension(width, 25))
  commandEntry.setMaximumSize(new Dimension(width, 25))
  commandEntry.setMinimumSize(new Dimension(width, 25))
  commandEntry.setFocusable(true)
  commandEntry.setFont(Assets.dejavumono.deriveFont(20f))
  commandEntry.setHorizontalAlignment(SwingConstants.LEFT)
  
  commandPane.add(commandEntry)
  commandPane.add(commandSend)
  
  frame.add(commandPane, BorderLayout.SOUTH)
  
  var consoleOutput = new JTextArea()
  consoleOutput.setPreferredSize(new Dimension(width, height - 30))
  consoleOutput.setMaximumSize(new Dimension(width, height - 30))
  consoleOutput.setMinimumSize(new Dimension(width, height - 30))
  consoleOutput.setFocusable(false)
  consoleOutput.setFont(Assets.dejavumono.deriveFont(20f))
  
  frame.add(consoleOutput, BorderLayout.CENTER)
  
  frame.pack()
  
  
  def tick() = {
    if(KeyManager.keyJustPressed(KeyEvent.VK_BACK_QUOTE)) frame.setVisible(true)
  }
}