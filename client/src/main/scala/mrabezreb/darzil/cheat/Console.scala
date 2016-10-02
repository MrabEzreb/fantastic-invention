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

object Console {
  
  val width = 300
  val height = 300
  
  val luaG = Globals.standard()
  
  val print = LuaFunction.simple { luav =>
    consoleOutput.append(luav.toString() + "\n")
    LuaValue.Nil
  }
  
  luaG("print") = print
  
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