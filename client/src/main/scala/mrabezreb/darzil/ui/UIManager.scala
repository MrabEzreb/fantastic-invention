package mrabezreb.darzil.ui

import scala.collection.mutable.ArrayBuffer
import java.awt.event.MouseEvent
import java.awt.Graphics

class UIManager {
  
  val objects = ArrayBuffer.empty[UIObject]
  
  val += = objects.+= _
  val ++= = objects.++= _
  val -= = objects.-= _
  val --= = objects.--= _
  
  def tick() = {
    objects.foreach { o => o.tick() }
  }
  
  def render(g: Graphics) = {
    objects.foreach { o => o.render(g) }
  }
  
  def onMouseMove(e: MouseEvent) = {
    objects.foreach { o => o.onMouseMove(e) }
  }
  
  def onMouseRelease(e: MouseEvent) = {
    objects.foreach { o => o.onMouseRelease(e) }
  }
}