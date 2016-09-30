package mrabezreb.darzil.ui

import scala.collection.mutable.ArrayBuffer
import mrabezreb.darzil.gfx.Image
import java.awt.Graphics
import java.awt.event.MouseEvent

class UIImageButton(sx: Double, sy: Double, sw: Int, sh: Int, image: Image, hoverImage: Image, clicker: () => Unit) extends UIObject(sx, sy, sw, sh) {
  
  def tick() = {}
  def render(g: Graphics) = {
    if(hovering) g.drawImage(hoverImage, x.intValue(), y.intValue(), w, h, null)
    else g.drawImage(image, x.intValue(), y.intValue(), w, h, null)
  }
  def onClick() = {
    clicker()
  }
}