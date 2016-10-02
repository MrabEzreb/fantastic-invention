package mrabezreb.darzil.input

import java.awt.event.KeyListener
import java.awt.event.KeyEvent

object KeyManager extends KeyListener {
  
  private var keys = Array.ofDim[Boolean](256)
  private var justPressed = Array.ofDim[Boolean](256)
  private var cantPress = Array.ofDim[Boolean](256)
  var up, down, left, right, aUp, aDown, aLeft, aRight: Boolean = false
  
  def tick() = {
    var i = 0
    while(i < keys.length) {
      if(cantPress(i) && !keys(i)) {
        cantPress.update(i, false)
      } else if(justPressed(i)) {
        cantPress.update(i, true)
        justPressed.update(i, false)
      }
      if(!cantPress(i) && keys(i)) {
        justPressed.update(i, true)
      }
      i += 1
    }
    
    up = keys(KeyEvent.VK_W)
    down = keys(KeyEvent.VK_S)
    left = keys(KeyEvent.VK_A)
    right = keys(KeyEvent.VK_D)
    
    aUp = keys(KeyEvent.VK_UP)
    aDown = keys(KeyEvent.VK_DOWN)
    aLeft = keys(KeyEvent.VK_LEFT)
    aRight = keys(KeyEvent.VK_RIGHT)
  }
  
  def keyJustPressed(keyCode: Int): Boolean = {
    justPressed(keyCode)
  }
  
  def keyPressed(e: KeyEvent) = {
    keys.update(e.getKeyCode, true)
  }
  def keyReleased(e: KeyEvent) = {
    keys.update(e.getKeyCode, false)
  }
  def keyTyped(e: KeyEvent) = {
    
  }
}