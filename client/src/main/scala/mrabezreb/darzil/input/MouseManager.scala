package mrabezreb.darzil.input

import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.MouseEvent
import java.awt.Point
import mrabezreb.darzil.ui.UIManager

object MouseManager extends MouseListener with MouseMotionListener {
  
  var leftPressed = false
  var rightPressed = false
  var middlePressed = false
  var mousePos = new Point()
  var uiManager: UIManager = null
  
  
  def mouseMoved(e: MouseEvent) = {
    mousePos = e.getPoint
    if(uiManager != null) uiManager.onMouseMove(e)
  }
  def mouseDragged(e: MouseEvent) = {
    
  }
  def mouseClicked(e: MouseEvent) = {
    
  }
  def mouseEntered(e: MouseEvent) = {
    
  }
  def mouseExited(e: MouseEvent) = {
    
  }
  def mousePressed(e: MouseEvent) = {
    if(e.getButton == MouseEvent.BUTTON1) {
      leftPressed = true
    } else if(e.getButton == MouseEvent.BUTTON3) {
      middlePressed = true
    } else if(e.getButton == MouseEvent.BUTTON3) {
      rightPressed = true
    }
  }
  def mouseReleased(e: MouseEvent) = {
    if(e.getButton == MouseEvent.BUTTON1) {
      leftPressed = false
    } else if(e.getButton == MouseEvent.BUTTON3) {
      middlePressed = false
    } else if(e.getButton == MouseEvent.BUTTON3) {
      rightPressed = false
    }
    
    if(uiManager != null) uiManager.onMouseRelease(e)
  }
}