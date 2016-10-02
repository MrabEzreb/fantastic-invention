package mrabezreb.darzil.gfx

class DirectedAnimation(var speed: Int, var fcount: Int, var sheet: Image) {
  
  var lastTime = System.currentTimeMillis()
  var timer = 0L
  var index = 0
  var direction: DirectedAnimation.Direction = DirectedAnimation.Down()
  
  val up = Image.copy(sheet.getSubimage(0, 0, (fcount)*64, 64))
  val left = Image.copy(sheet.getSubimage(0, 64, (fcount)*64, 64))
  val down = Image.copy(sheet.getSubimage(0, 128, (fcount)*64, 64))
  val right = Image.copy(sheet.getSubimage(0, 192, (fcount)*64, 64))
  
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
    direction match {
      case DirectedAnimation.Left() => Image.copy(left.getSubimage(index*64, 0, 64, 64))
      case DirectedAnimation.Up() => Image.copy(up.getSubimage(index*64, 0, 64, 64))
      case DirectedAnimation.Right() => Image.copy(right.getSubimage(index*64, 0, 64, 64))
      case DirectedAnimation.Down() => Image.copy(down.getSubimage(index*64, 0, 64, 64))
    }
  }
  
}

object DirectedAnimation {
  sealed trait Direction
  case class Left() extends Direction
  case class Right() extends Direction
  case class Up() extends Direction
  case class Down() extends Direction
}
