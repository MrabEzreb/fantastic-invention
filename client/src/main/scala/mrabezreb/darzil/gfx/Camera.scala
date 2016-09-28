package mrabezreb.darzil.gfx

import mrabezreb.darzil.Game
import mrabezreb.darzil.entity.Entity
import mrabezreb.darzil.Handler
import mrabezreb.darzil.tile.Tile

object Camera {
  var xOffset: Double = 0.0
  var yOffset: Double = 0.0
  var game: Game = null
  def init() = {
    game = Handler.game
  }
  def checkBounds() = {
    xOffset = Math.max(xOffset, 0)
    yOffset = Math.max(yOffset, 0)
    xOffset = Math.min(xOffset, Handler.world.width * Tile.tileWidth - Handler.game.width)
    yOffset = Math.min(yOffset, Handler.world.height * Tile.tileHeight - Handler.game.height)
  }
  def centerOnEntity(e: Entity) = {
    xOffset = e.x - game.width / 2 + e.width / 2
    yOffset = e.y - game.width / 2 + e.height / 2
    checkBounds()
  }
  def move(x: Double, y: Double): Unit = {
    xOffset += x
    yOffset += y
    checkBounds()
  }
}