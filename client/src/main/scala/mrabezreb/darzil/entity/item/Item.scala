package mrabezreb.darzil.entity.item

import mrabezreb.darzil.gfx.Image
import java.awt.Graphics
import mrabezreb.darzil.Handler
import mrabezreb.darzil.gfx.Camera
import scala.collection.mutable.ArrayBuffer
import mrabezreb.darzil.gfx.Assets

class Item(var texture: Image, var name: String, val id: Int) {
  var x = 0
  var y = 0
  var count = 1
  
//  println(Item.items.toString())
  Item.items.insert(id, this)
//  Item.items.update(id, this)
  
  def tick() = {
    
  }
  
  def createNew(x1: Int, y1: Int): Item = {
    var i = new Item(texture, name, id)
    i.setPosition(x1, y1)
    i
  }
  
  def setPosition(x: Int, y: Int) = {
    this.x = x
    this.y = y
  }
  
  def render(g: Graphics): Unit = {
	  render(g, x - Camera.xOffset.intValue(), y - Camera.yOffset.intValue())
  }
  def render(g: Graphics, x: Int, y: Int): Unit = {
    g.drawImage(texture, x, y, Item.itemWidth, Item.itemHeight, null)
    g.drawRect(x, y, Item.itemWidth, Item.itemHeight)
  }
}

object Item {
  val itemWidth = 32
  val itemHeight = 32
  val pickedUp = -1
  
  var items: ArrayBuffer[Item] = null
  var woodItem: Item = null
  
  def init() = {
    items = ArrayBuffer.empty[Item]
    woodItem = new Item(Assets.farming_fishing(1, 1), "Wood", 0)
  }
}