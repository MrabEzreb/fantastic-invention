package mrabezreb.darzil.inventory

import java.awt.Graphics
import mrabezreb.darzil.input.KeyManager
import java.awt.event.KeyEvent
import scala.collection.mutable.ArrayBuffer
import mrabezreb.darzil.entity.item.Item

class Inventory {
  
  var active = false
  var inventoryItems = ArrayBuffer.empty[Item]
  
  def tick(): Unit = {
    if(KeyManager.keyJustPressed(KeyEvent.VK_I)) active = !active
    if(!active) return
    println("Inventory: ")
    inventoryItems.foreach { i =>
      println(i.count + " of " + i.name)
    }
  }
  
  def render(g: Graphics): Unit = {
    if(!active) return
  }
  
  def +=(i: Item) = {
    var filtered = inventoryItems.filter { ai => ai.id == i.id }
    if(filtered.size == 0) {
      inventoryItems += i
    } else {
      filtered(0).count += i.count
    }
//    inventoryItems += i
  }
}