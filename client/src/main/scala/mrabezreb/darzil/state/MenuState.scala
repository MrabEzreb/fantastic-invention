package mrabezreb.darzil.state

import java.awt.Graphics
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.entity.Player
import mrabezreb.darzil.Game
import mrabezreb.darzil.tile.Tile
import mrabezreb.darzil.world.World
import mrabezreb.darzil.input.KeyManager
import java.io.File
import scala.util.Random
import mrabezreb.darzil.gfx.Camera
import mrabezreb.darzil.Handler
import mrabezreb.darzil.input.MouseManager
import java.awt.Color
import java.awt.Rectangle
import mrabezreb.darzil.ui.UIManager
import mrabezreb.darzil.ui.UIImageButton
import java.awt.Font
import java.awt.RenderingHints
import java.awt.Graphics2D
import mrabezreb.darzil.ui.UITextButton

class MenuState() extends State {
  
  var uim = new UIManager()
  var scrollPlay = Assets.scroll(288, 336)
//  var text1 = Assets.play1.append(Assets.options1)
//  var text2 = Assets.play2.append(Assets.options2)
  var playButton = new UIImageButton(0, 50, 288, 144, Assets.play1, Assets.play2, {() => State.setState(State.gameState)}) //new UIImageButton(100, 100, 192, 192, Assets.ak, Assets.ak, {() => {}})
  var optionsButton = new UIImageButton(0, 144, 288, 144, Assets.options1, Assets.options2, {() => println("NI")})
  var optionsButton2 = new UITextButton(0, 400, Assets.cookie.deriveFont(75f), "hqllo", {() => println("NI")})
  var s = Assets.scroll(192, 288 + 24)
  var s2 = Assets.scroll(192, 336)
  var s3 = Assets.scroll(64, 64)
  var s4 = Assets.scroll(336, 192)
//  println("1")
  Camera.init()
//  val playButton = new Rectangle(Handler.game.width/2 - 96, Handler.game.height/2 - 96, 192, 192)
  
  def init() = {
    MouseManager.uiManager = uim
    uim += playButton
    uim += optionsButton
    uim += optionsButton2 
  }
  
  def tick(): Unit = {
//    if(MouseManager.leftPressed) {
//      if(playButton.contains(MouseManager.mousePos)) {
//        State.state = State.gameState
//      }
//    }
//    Camera.move(1, 1)
    uim.tick()
  }
  def render(g: Graphics): Unit = {
//    g.drawImage(s, 0, 0, null)
//    g.drawImage(s2, 300, 0, null)
//    g.drawImage(s3, 0, 400, null)
    var rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
    g.asInstanceOf[Graphics2D].addRenderingHints(rh)
    g.setFont(Assets.cookie.deriveFont(75f))
    g.drawImage(scrollPlay, 0, 0, null)
    uim.render(g)
    g.drawString("Play", 300, 300)
    g.drawString("Options", 300, 400)
//    g.drawImage(Assets.playT, 100, 100, null)
//    g.drawImage(Assets.saucers(0,0), 100, 100, null)
//    Tile.tiles(0).render(g, 0, 0)
//    Tile.tiles(1).render(g, 48, 0)
//    world.render(g)
//    g.drawImage(Assets.menuscrolln, Handler.game.width/2 - 96, Handler.game.height/2 - 96, null)
//    g.setColor(Color.RED)
//    g.fillOval(MouseManager.mousePos.x, MouseManager.mousePos.y, 10, 10)
//    g.drawString("Play", Handler.game.width/2 - 96, Handler.game.height/2 - 96)
  }
}