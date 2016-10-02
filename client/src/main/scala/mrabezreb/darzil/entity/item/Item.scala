package mrabezreb.darzil.entity.item

import mrabezreb.darzil.gfx.Image
import java.awt.Graphics
import mrabezreb.darzil.Handler
import mrabezreb.darzil.gfx.Camera
import scala.collection.mutable.ArrayBuffer
import mrabezreb.darzil.gfx.Assets
import java.awt.Rectangle

class Item(var texture: Image, var name: String, val id: Int, val toolType: ToolType) {
  var x = 0
  var y = 0
  var count = 1
  var bounds = new Rectangle(x, y, Item.itemWidth, Item.itemHeight)
  var pickedUp = false
  
  def this(texture: Image, name: String, id: Int) = {
    this(texture, name, id, new ToolType(0.0, 0.0, ""))
  }
  
//  println(Item.items.toString())
  Item.items.insert(id, this)
//  Item.items.update(id, this)
  
  def tick() = {
    if(Handler.world.entityManager.player.getCollisionBounds(0.0, 0.0).intersects(bounds)) {
      pickedUp = true
      Handler.world.entityManager.player.inventory += this
    }
  }
  
  def createNew(x1: Int, y1: Int): Item = {
    var i = new Item(texture, name, id)
    i.setPosition(x1, y1)
    i
  }
  
  def setPosition(x: Int, y: Int) = {
    this.x = x
    this.y = y
    bounds.x = x
    bounds.y = y
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
  
  val hands = new ToolType(0.0, 0.0, "")
  
  var items: ArrayBuffer[Item] = null
  var woodItem: Item = null
  
  def init() = {
    items = ArrayBuffer.empty[Item]
    woodItem = new Item(Assets.farming_fishing(1, 1), "Wood", 0)
  }
}

class ToolType(val sDamage: Double, val aDamage: Double, val typeName: String) {}