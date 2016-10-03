package mrabezreb.darzil.entity

import scala.collection.mutable.ArrayBuffer
import java.awt.Graphics
import java.util.Comparator
import java.lang.annotation.Annotation

class EntityManager(var player: Player) {
  
  var entities = ArrayBuffer.empty[Entity]
  
  entities += player
  
//  var += = entities.+= _
//  var ++= = entities.++= _
  
  def sort() = {
    entities = entities.sortBy(_.getBottom())
  }
  
  def +=(e: Entity) = {
    entities += e
  }
  
  def ++=(es: TraversableOnce[Entity]) = {
    entities ++= es
  }
  
  def tick() = {
    entities.foreach { e => e.tick() }
    entities = entities.filter { e => e.active }
    sort()
  }
  
  def render(g: Graphics) = {
    entities.foreach { e => e.render(g) }
  }
}