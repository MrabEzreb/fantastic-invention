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

class MenuState() extends State {
  
  var uim = new UIManager()
  var playButton = new UIImageButton(100, 100, 192, 192, Assets.ak, Assets.ak, {() => {}})
  
  Camera.init()
//  val playButton = new Rectangle(Handler.game.width/2 - 96, Handler.game.height/2 - 96, 192, 192)
  
  def init() = {
    MouseManager.uiManager = uim
    uim += 
  }
  
  def tick(): Unit = {
//    if(MouseManager.leftPressed) {
//      if(playButton.contains(MouseManager.mousePos)) {
//        State.state = State.gameState
//      }
//    }
//    Camera.move(1, 1)  
  }
  def render(g: Graphics): Unit = {
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