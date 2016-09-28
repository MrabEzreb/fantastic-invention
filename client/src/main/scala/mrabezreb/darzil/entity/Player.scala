package mrabezreb.darzil.entity

import java.awt.Graphics

import mrabezreb.darzil.Game
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.input.KeyManager
import mrabezreb.darzil.gfx.Camera
import java.awt.Color
import mrabezreb.darzil.gfx.Animation
import mrabezreb.darzil.gfx.Image

class Player(var px: Double, var py: Double) extends Creature(px, py, Creature.defaultWidth, Creature.defaultHeight) {
  
  val animDown = new Animation(250, 4, 0, Assets.player)
  val animLeft = new Animation(250, 4, 1, Assets.player)
  val animUp = new Animation(250, 4, 2, Assets.player)
  val animRight = new Animation(250, 4, 3, Assets.player)
  
  def tick() = {
    animDown.tick()
    animLeft.tick()
    animUp.tick()
    animRight.tick()
    getInput
    move()
    Camera.centerOnEntity(this)
  }
  
  bounds.x = 10
  bounds.y = 10
  bounds.width = 28
  bounds.height = 31
  
  def getInput = {
    xMove = 0.0
    yMove = 0.0
    if(KeyManager.up) {
      yMove = -speed
    }
    if(KeyManager.down) {
      yMove = speed
    }
    if(KeyManager.left) {
      xMove = -speed
    }
    if(KeyManager.right) {
      xMove = speed
    }
  }
  
  def render(g: Graphics) = {
    g.drawImage(getCurrentAnimationFrame(), x.intValue() - Camera.xOffset.intValue(), y.intValue() - Camera.yOffset.intValue(), width, height, null)
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
}