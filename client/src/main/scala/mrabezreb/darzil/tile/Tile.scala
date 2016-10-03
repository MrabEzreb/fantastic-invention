package mrabezreb.darzil.tile

import mrabezreb.darzil.gfx.Image
import java.awt.Graphics
import mrabezreb.darzil.gfx.SliceTile
import mrabezreb.darzil.gfx.SliceTile
import mrabezreb.darzil.gfx.SliceTile
import mrabezreb.darzil.gfx.Assets

class Tile(val sheet: SliceTile, val id: Int, val texture: Image) {
  def this(sheet: SliceTile, id: Int) = {
    this(sheet, id, sheet.sliced32.apply(0, 0))
  }
  def this(texture: Image, id: Int) = {
    this(new SliceTile(texture, true), id, texture)
  }
  Tile.tiles.update(id, this)
  def tick() = {
    
  }
  
  def isSolid() = {
    false
  }
  
  def render(g: Graphics, x: Int, y: Int, surrounding: SurroundingTiles) = {
    g.drawImage(sheet(surrounding, id), x, y, Tile.tileWidth * 3, Tile.tileHeight * 3, null)
  }
}

class SurroundingTiles(x: Int, y: Int, tiles: Array[Array[Int]]) {
  def left(): Int = {
    if(x == 0) {
      return -1
    }
    tiles(x-1)(y)
  }
  def right(): Int = {
    if(x == tiles.size-1) {
      return -1
    }
    tiles(x+1)(y)
  }
  def up(): Int = {
    if(y == 0) return -1
    tiles(x)(y-1)
  }
  def down(): Int = {
    if(y == tiles(0).size) return -1
    tiles(x)(y+1)
  }
}

object Tile {
  val tileWidth = 48
  val tileHeight = 48
  val tiles = Array.ofDim[Tile](256)
  val grassTile = new GrassTile(0)
  val stoneTile = new RockTile(1)
  val farmTile = new Tile(Assets.plowed_soil, 2)
}