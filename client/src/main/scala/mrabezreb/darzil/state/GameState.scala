package mrabezreb.darzil.state

import java.awt.Graphics
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.entity.Player
import mrabezreb.darzil.Game
import mrabezreb.darzil.tile.Tile
import mrabezreb.darzil.world.World

class GameState(ggame: Game) extends State(ggame) {
  
  private val player = new Player(ggame, 100, 100)
  private val world = new World("")
  
  def tick(): Unit = {
    world.tick()
    player.tick()
  }
  def render(g: Graphics): Unit = {
//    g.drawImage(Assets.saucers(0,0), 100, 100, null)
//    Tile.tiles(0).render(g, 0, 0)
//    Tile.tiles(1).render(g, 48, 0)
    world.render(g)
    player.render(g)
  }
}