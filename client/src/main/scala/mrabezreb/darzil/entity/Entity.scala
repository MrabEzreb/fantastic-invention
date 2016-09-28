package mrabezreb.darzil.entity

import java.awt.Graphics

abstract class Entity(var x: Double, var y: Double, var width: Int, var height: Int) {
  
  def tick(): Unit
  def render(g: Graphics): Unit
}