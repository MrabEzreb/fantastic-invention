package mrabezreb.darzil.entity

abstract class Creature(cx: Double, cy: Double, var cwidth: Int, var cheight: Int) extends Entity(cx, cy, cwidth, cheight) {
  var health = Creature.defaultHealth
  var speed = Creature.defaultSpeed
  var xMove, yMove = 0.0
  
  def move() = {
    x += xMove
    y += yMove
  }
}

object Creature {
  val defaultHealth = 10
  val defaultSpeed = 2.0
  val defaultWidth = 48
  val defaultHeight = 48
}