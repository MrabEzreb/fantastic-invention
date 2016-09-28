package mrabezreb.darzil.physics

import processing.core.PApplet

class AnimatedValue(var value: Float) {
  def apply(): Float = {
    value
  }
  def change(f: Float) = {
    value += f
  }
  def animateChange(totalChange: Float, millis: Int, func: (Float, Float, AnimatedValue.AnimateCallback) => Unit) = {
    AnimationController.animate(new AnimatedValue.AnimateCallback(millis) {
      var seconds = 0
      var startValue = value
      def started(): Unit = {
//        println("Starting")
//        println(value)
      }
      def update(delta: Float, parent: AnimatedValue.AnimateCallback): Unit = {
        change(totalChange * (delta / (millis.toFloat / 1000f)))
        func(value, delta, parent)
//        if(parent.elapsed / 1000 > seconds) {
////          println("it has been " + seconds + " seconds: " + parent.elapsed)
////          println(value)
//        }
        seconds = parent.elapsed / 1000
      }
      def complete(): Unit = {
//        println("Done")
        value = startValue + totalChange
//        println(value)
      }
    })
  }
}
object AnimatedValue {
  abstract class AnimateCallback(val time: Int) {
    var elapsed = 0
    def started(): Unit
    def update(delta: Float, parent: AnimateCallback): Unit
    def apply(delta: Float): Unit = {
      elapsed += (delta * 1000).floor.toInt
      update(delta, this)
    }
    def complete(): Unit
  }
}