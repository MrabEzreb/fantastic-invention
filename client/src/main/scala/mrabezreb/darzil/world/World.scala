package mrabezreb.darzil.world

import java.awt.Graphics
import mrabezreb.darzil.tile.Tile
import java.io.ObjectOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.FileInputStream
import mrabezreb.darzil.input.KeyManager
import mrabezreb.darzil.Game
import mrabezreb.darzil.gfx.Camera
import mrabezreb.darzil.Handler
import java.awt.Rectangle
import mrabezreb.darzil.entity.EntityManager
import mrabezreb.darzil.entity.Player
import mrabezreb.darzil.entity.statics.Tree
import mrabezreb.darzil.entity.statics.LogPile
import mrabezreb.darzil.entity.item.ItemManager
import mrabezreb.darzil.entity.item.Item

@SerialVersionUID(100L)
class World extends Serializable {
  var width = 32
  var height = 32
  private var playerStartX = 100
  private var playerStartY = 250
  private val path: String = ""
  var tiles: Array[Array[Int]] = null
  private var game: Game = null
  var entityManager = new EntityManager(new Player(playerStartX, playerStartY))
  entityManager += new Tree(100, 150)
  entityManager += new Tree(200, 150)
  entityManager += new Tree(100, 350)
  entityManager += new Tree(200, 350)
//  entityManager += new LogPile(150, 150)
  def this(p: String) = {
    this()
    genWorld()
  }
  def init() = {
    game = Handler.game
    Item.init()
  }
  def tick() = {
    ItemManager.tick()
    entityManager.tick()
  }
  def render(g: Graphics) = {
    val xStart = Math.max(0, Camera.xOffset / Tile.tileWidth.doubleValue())
    val xEnd = Math.min(width, (Camera.xOffset + game.width) / Tile.tileWidth + 1)
    val yStart = Math.max(0, Camera.yOffset / Tile.tileHeight.doubleValue())
    val yEnd = Math.min(height, (Camera.yOffset + game.height) / Tile.tileHeight + 1)
    
    var xi = xStart.intValue()
    var yi = yStart.intValue()
    while(yi < yEnd.intValue()) {
      xi = xStart.intValue()
      while(xi < xEnd.intValue()) {
    	  getTile(xi.intValue(), yi.intValue()).render(g, xi.intValue()*Tile.tileWidth - Camera.xOffset.intValue(), yi.intValue()*Tile.tileHeight - Camera.yOffset.intValue())
        xi += 1
      }
      yi += 1
    }
    ItemManager.render(g)
    entityManager.render(g)
//    tiles.foreach { tl => tl.foreach { t => getTile(x, y) } }
  }
  def getTile(x: Int, y: Int) = {
    if(x < 0 || x >= width || y < 0 || y >= height) Tile.grassTile
    var ti = tiles(x)(y)
    var t = Tile.tiles(ti)
    if(t == null) Tile.grassTile
    t
  }
  def saveWorld(p: String) = {
    new File("worlds").mkdirs()
    val f = new File("worlds/"+p)
    f.createNewFile()
    val oos = new ObjectOutputStream(new FileOutputStream(f))
    oos.writeObject(this)
    oos.flush()
    oos.close()
  }
  def genWorld() = {
    tiles = Array.ofDim[Array[Int]](width)
    var yi = 0
    var xi = 0
    while(xi < width) {
      yi = 0
      val ya = Array.ofDim[Int](height)
      while(yi < height) {
        ya.update(yi, 0)
        if(xi == 0 || yi == 0 || xi == width-1 || yi == height-1) {
          ya.update(yi, 1)
        }
        yi += 1
      }
      tiles.update(xi, ya)
      xi += 1
    }
  }
}

object World {
  def loadWorld(p: String): World = {
    val ois = new ObjectInputStream(new FileInputStream(new File("worlds/"+p)))
    var w = ois.readObject().asInstanceOf[World]
    w.game = Handler.game
    w
  }
}