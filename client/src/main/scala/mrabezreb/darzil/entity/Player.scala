package mrabezreb.darzil.entity

import java.awt.Graphics
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.Game
import mrabezreb.darzil.input.KeyManager

class Player(game: Game, var px: Double, var py: Double) extends Creature(px, py, Creature.defaultWidth, Creature.defaultHeight) {
  
  def tick() = {
    getInput
    move()
  }
  
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
    g.drawImage(Assets.player(0,0), x.intValue(), y.intValue(), width, height, null)
  }
}