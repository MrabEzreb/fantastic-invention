package mrabezreb.darzil.tile

import mrabezreb.darzil.gfx.Image
import java.awt.Graphics

class Tile(val texture: Image, val id: Int) {
  Tile.tiles.update(id, this)
  def tick() = {
    
  }
  
  def isSolid() = {
    false
  }
  
  def render(g: Graphics, x: Int, y: Int) = {
    g.drawImage(texture, x, y, Tile.tileWidth, Tile.tileHeight, null)
  }
}

object Tile {
  val tileWidth = 48
  val tileHeight = 48
  val tiles = Array.ofDim[Tile](256)
  val grassTile = new GrassTile(0)
  val stoneTile = new RockTile(1)
}