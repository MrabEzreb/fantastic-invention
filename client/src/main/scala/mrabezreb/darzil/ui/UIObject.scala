package mrabezreb.darzil.ui

import java.awt.Graphics
import java.awt.event.MouseEvent
import java.awt.Rectangle

abstract class UIObject(val x: Double, val y: Double, val w: Int, val h: Int) {
  
  var hovering = false
  val bounds = new Rectangle(x.intValue(), y.intValue(), w, h)
  
  def tick(): Unit
  def render(g: Graphics): Unit
  def onClick(): Unit
  def onMouseMove(e: MouseEvent) = {
    hovering = bounds.contains(e.getPoint)
  }
  def onMouseRelease(e: MouseEvent) = {
    if(hovering) onClick()
  }
}