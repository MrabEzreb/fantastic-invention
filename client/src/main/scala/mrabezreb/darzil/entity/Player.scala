package mrabezreb.darzil.entity

import java.awt.Graphics

import mrabezreb.darzil.Game
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.input.KeyManager
import mrabezreb.darzil.gfx.Camera
import java.awt.Color
import mrabezreb.darzil.gfx.Animation
import mrabezreb.darzil.gfx.Image
import java.awt.Rectangle
import mrabezreb.darzil.Handler
import mrabezreb.darzil.gfx.LPCAnimation
import mrabezreb.darzil.gfx.DirectedAnimation

class Player(var px: Double, var py: Double) extends Creature(px, py, Creature.defaultWidth, Creature.defaultHeight) {
  
  val animDown = new Animation(250, 4, 0, Assets.player)
  val animLeft = new Animation(250, 4, 1, Assets.player)
  val animUp = new Animation(250, 4, 2, Assets.player)
  val animRight = new Animation(250, 4, 3, Assets.player)
  
  var anims = new LPCAnimation(Image.load("/character/player.png"))
  
  var lastAttack = 0L
  var attackCooldown = 800L
  var attackTimer = attackCooldown.longValue()
  
  def tick() = {
    anims.tick()
    animDown.tick()
    animLeft.tick()
    animUp.tick()
    animRight.tick()
    if(!getInput) {
//      anims := (LPCAnimation.Stand(), anims.direction)
      getAttack
    }
    move()
    Camera.centerOnEntity(this)
  }
  
  bounds.x = 10
  bounds.y = 10
  bounds.width = 28
  bounds.height = 31
  
  def getAttack(): Boolean = {
    attackTimer += System.currentTimeMillis() - lastAttack
    lastAttack = System.currentTimeMillis()
    if(attackTimer < attackCooldown) {
      return false
    }
    
//    println("AttacK")
    var arSize = 20
    var ar = new Rectangle(0,0,arSize,arSize)
    var cb = getCollisionBounds(0, 0)
    if(KeyManager.aUp) {
      ar.x = cb.x + cb.width / 2 - arSize / 2
      ar.y = cb.y - arSize
      anims := (LPCAnimation.Slash(), DirectedAnimation.Up())
    } else if(KeyManager.aDown) {
      ar.x = cb.x + cb.width / 2 - arSize / 2
      ar.y = cb.y + cb.height
      anims := (LPCAnimation.Slash(), DirectedAnimation.Down())
    } else if(KeyManager.aLeft) {
      ar.x = cb.x - arSize
      ar.y = cb.y + cb.height / 2 - arSize / 2
      anims := (LPCAnimation.Slash(), DirectedAnimation.Left())
    } else if(KeyManager.aRight) {
      ar.x = cb.x + cb.width
      ar.y = cb.y + cb.height / 2 - arSize / 2
      anims := (LPCAnimation.Slash(), DirectedAnimation.Right())
    } else {
      anims := (LPCAnimation.Stand(), anims.direction)
      return false
    }
    
    attackTimer = 0
    
    for(e <- Handler.world.entityManager.entities) {
      if(!e.equals(this)) {
        if(e.getCollisionBounds(0, 0).intersects(ar)) {
          e.hurt(1)
          return true
        }
      }
    }
    return false
  }
  
  def getInput(): Boolean = {
    xMove = 0.0
    yMove = 0.0
    if(KeyManager.up) {
      yMove = -speed
      anims := (LPCAnimation.Walk(), DirectedAnimation.Up())
    }
    if(KeyManager.down) {
      yMove = speed
      anims := (LPCAnimation.Walk(), DirectedAnimation.Down())
    }
    if(KeyManager.left) {
      xMove = -speed
      anims := (LPCAnimation.Walk(), DirectedAnimation.Left())
    }
    if(KeyManager.right) {
      xMove = speed
      anims := (LPCAnimation.Walk(), DirectedAnimation.Right())
    }
    if(xMove != 0.0 || yMove != 0.0) {
      return true
    } else {
      return false
    }
  }
  
  def render(g: Graphics) = {
//    g.drawImage(getCurrentAnimationFrame(), x.intValue() - Camera.xOffset.intValue(), y.intValue() - Camera.yOffset.intValue(), width, height, null)
    g.drawImage(anims(), x.intValue() - Camera.xOffset.intValue(), y.intValue() - Camera.yOffset.intValue(), width, height, null)
    g.setColor(Color.red)
//    g.drawRect(x.intValue() + bounds.x.intValue() - Camera.xOffset.intValue(), y.intValue() + bounds.y.intValue() - Camera.yOffset.intValue(), bounds.width, bounds.height)
  }
  
  def getCurrentAnimationFrame(): Image = {
    if(xMove < 0) {
      animLeft()
    } else if(xMove > 0) {
      animRight()
    } else if(yMove < 0) {
      animUp()
    } else if(yMove > 0) {
      animDown()
    } else {
      Assets.player(0,0)
    }
  }
  
  def die() = {
    println("ded")
  }
}