package mrabezreb.darzil.gfx

import java.awt.image.BufferedImage

class Animation(var speed: Int, var fcount: Int, var col: Int, var sheet: SpriteSheet) {
  
  var lastTime = System.currentTimeMillis()
  var timer = 0L
  var index = 0
  
  def tick() = {
    timer += System.currentTimeMillis() - lastTime
    lastTime = System.currentTimeMillis()
    if(timer > speed) {
      index += 1
      timer = 0
      index %= fcount
    }
  }
  
  def apply(): Image = {
    sheet(col, index)
  }
}