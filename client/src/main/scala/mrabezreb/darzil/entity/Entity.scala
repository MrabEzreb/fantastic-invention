package mrabezreb.darzil.entity

import java.awt.Graphics
import mrabezreb.darzil.Game
import java.awt.Rectangle
import mrabezreb.darzil.Handler

abstract class Entity(var x: Double, var y: Double, var width: Int, var height: Int) {
  var health = Entity.defaultHealth
  var active = true
  
  def hurt(amt: Double) = {
    health -= amt
    if(health <= 0) {
      active = false
      die()
    }
  }
  
  def die(): Unit
  
  var bounds = new Rectangle(0,0,width,height)
  
  def tick(): Unit
  def render(g: Graphics): Unit
  
  def getCollisionBounds(xOffset: Double, yOffset: Double) = {
    new Rectangle((x + bounds.x + xOffset).intValue(), (y + bounds.y + yOffset).intValue(), bounds.width, bounds.height)
  }
  
  def getBottom() = {
    y + height
  }
  
  def checkEntityCollisions(xOffset: Double, yOffset: Double) = {
    var collides = false
    Handler.world.entityManager.entities.foreach { e =>
      if(e.equals(this)) {
        
      } else if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset))) {
        collides = true
      }
    }
    collides
  }
}

object Entity {
  val defaultHealth = 10.0
}