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

class GameState() extends State {
  
  private val player = new Player(100, 100)
  private val world = getWorld()
  
  Camera.init()
  Handler.world = world
  
//  Camera.move(100, 100)
  
  private def getWorld(): World = {
//    if(new File("worlds/world1.world").exists()) {
//      World.loadWorld("world1.world")
    var w = new World("")
    w.init()
    w
//    } else {
//    }
  }
  
  def tick(): Unit = {
    world.tick()
    player.tick()
//    Camera.move(1, 1)  
  }
  def render(g: Graphics): Unit = {
//    g.drawImage(Assets.saucers(0,0), 100, 100, null)
//    Tile.tiles(0).render(g, 0, 0)
//    Tile.tiles(1).render(g, 48, 0)
    world.render(g)
    player.render(g)
  }
}