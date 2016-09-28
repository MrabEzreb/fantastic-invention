package mrabezreb.darzil.entity

import java.awt.Graphics
import mrabezreb.darzil.Game
import java.awt.Rectangle

abstract class Entity(var x: Double, var y: Double, var width: Int, var height: Int) {
  
  var bounds = new Rectangle(0,0,width,height)
  
  def tick(): Unit
  def render(g: Graphics): Unit
}