package mrabezreb.darzil.physics

import scala.collection.mutable.ArrayBuffer
import processing.core.PApplet
import mrabezreb.darzil.physics.AnimatedValue.AnimateCallback

object AnimationController {
  var objs = new ArrayBuffer[AnimateCallback]
  var currTime = 0
  var prevTime = 0
  var deltaTime = 0f
  def setup(p: PApplet): Unit = {
    prevTime = p.millis()
    currTime = prevTime
  }
  def update(p: PApplet): Unit = {
    currTime = p.millis()
    deltaTime = (currTime - prevTime).toFloat / 1000f
    prevTime = currTime
    objs.foreach { obj => obj(deltaTime)
      if (obj.elapsed >= obj.time) {
        remove(obj)
      }
    }
  }
  def animate(obj: AnimateCallback): Unit = {
    obj.started()
    objs.append(obj)
  }
  def remove(obj: AnimateCallback): Unit = {
    obj.complete()
    objs.remove(objs.indexOf(obj))
  }
}