package mrabezreb.darzil.entity.item

import scala.collection.mutable.ArrayBuffer
import java.awt.Graphics

object ItemManager {
  
  var items = ArrayBuffer.empty[Item]
  
  def tick() = {
    items.foreach { i => i.tick() }
    items = items.filter { i => i.count != Item.pickedUp }
  }
  
  def render(g: Graphics) = {
    items.foreach { i => i.render(g) }
  }
  
  def +=(i: Item) = {
    items += i
  }
}