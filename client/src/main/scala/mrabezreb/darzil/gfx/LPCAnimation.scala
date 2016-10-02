package mrabezreb.darzil.gfx

class LPCAnimation(var sheet: Image) {
  
  val speed = 75
  
  var spellcast = new DirectedAnimation(speed, 7, Image.copy(sheet.getSubimage(0, 0, 64*7, 256)))
  var thrust = new DirectedAnimation(speed, 8, Image.copy(sheet.getSubimage(0, 256, 64*8, 256)))
  var walk = new DirectedAnimation(speed, 9, Image.copy(sheet.getSubimage(0, 512, 64*9, 256)))
  var slash = new DirectedAnimation(speed, 6, Image.copy(sheet.getSubimage(0, 768, 64*6, 256)))
  var shoot = new DirectedAnimation(speed, 13, Image.copy(sheet.getSubimage(0, 1024, 64*13, 256)))
  
  var current: LPCAnimation.CAnimation = LPCAnimation.Walk()
  var direction: DirectedAnimation.Direction = DirectedAnimation.Down()
  
  def tick() = {
    spellcast.direction = direction
    spellcast.tick()
    thrust.direction = direction
    thrust.tick()
    walk.direction = direction
    walk.tick()
    slash.direction = direction
    slash.tick()
    shoot.direction = direction
    shoot.tick()
  }
  
  def :=(anim: LPCAnimation.CAnimation, dir: DirectedAnimation.Direction) = {
    current = anim
    direction = dir
  }
  
  def apply(): Image = {
    current match {
      case LPCAnimation.Spell() => spellcast()
      case LPCAnimation.Thrust() => thrust()
      case LPCAnimation.Walk() => walk()
      case LPCAnimation.Slash() => slash()
      case LPCAnimation.Shoot() => shoot()
      case LPCAnimation.Stand() =>
        walk.index = 0
        walk()
    }
  }
}

object LPCAnimation {
  sealed trait CAnimation
  case class Spell() extends CAnimation
  case class Thrust() extends CAnimation
  case class Walk() extends CAnimation
  case class Slash() extends CAnimation
  case class Shoot() extends CAnimation
  case class Stand() extends CAnimation
}