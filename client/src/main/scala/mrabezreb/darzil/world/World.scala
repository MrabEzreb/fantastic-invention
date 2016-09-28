package mrabezreb.darzil.world

import java.awt.Graphics
import mrabezreb.darzil.tile.Tile

@SerialVersionUID(100L)
class World(path: String) extends Serializable {
  private var width = 12
  private var height = 12
  var tiles: Array[Array[Int]] = null
  loadWorld(path)
  def tick() = {
    
  }
  def render(g: Graphics) = {
    var xi = 0
    var yi = 0
    while(yi < height) {
      xi = 0
      while(xi < width) {
        getTile(xi, yi).render(g, xi*48, yi*48)
        xi += 1
      }
      yi += 1
    }
//    tiles.foreach { tl => tl.foreach { t => getTile(x, y) } }
  }
  def getTile(x: Int, y: Int) = {
    var ti = tiles(x)(y)
    var t = Tile.tiles(ti)
    if(t == null) Tile.grassTile
    t
  }
  private def loadWorld(p: String) = {
    tiles = Array.ofDim[Array[Int]](width)
    val tileRow = List(0,0,0,0,0,0,0,0,0,0,0,0).toArray
    tiles.update(0, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(1, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(2, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(3, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(4, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(5, List(0,0,0,0,0,1,0,0,0,0,0,0).toArray)
    tiles.update(6, List(0,0,0,0,0,0,1,0,0,0,0,0).toArray)
    tiles.update(7, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(8, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(9, List(0,0,0,0,0,0,1,0,0,0,0,0).toArray)
    tiles.update(10, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
    tiles.update(11, List(0,0,0,0,0,0,0,0,0,0,0,0).toArray)
  }
}