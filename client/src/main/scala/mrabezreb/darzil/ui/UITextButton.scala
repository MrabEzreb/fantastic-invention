package mrabezreb.darzil.ui

import mrabezreb.darzil.gfx.Image
import java.awt.Graphics
import java.awt.Font
import java.awt.font.FontRenderContext
import java.awt.Color

class UITextButton(sx: Double, sy: Double, font: Font, text: String, clicker: () => Unit) extends UIObject(sx, sy, 0, font.getSize) {
  def tick() = {}
  def render(g: Graphics) = {
    var m = g.getFontMetrics(font)
    g.setColor(Color.red)
    w = m.getStringBounds(text, g).getWidth.intValue()
    var b = m.getStringBounds(text, g)
//    println(m.getStringBounds(text, g).getWidth)
    if(hovering) g.fillRect(x.intValue() + (w/2 - m.stringWidth(text).intValue()/2), sy.intValue(), b.getWidth.intValue(), b.getHeight.intValue())
    else g.drawRect(x.intValue() + (w/2 - m.stringWidth(text).intValue()/2), y.intValue(), b.getWidth.intValue(), b.getHeight.intValue())
    g.setColor(Color.BLACK)
    g.setFont(font)
    g.drawString(text, x.intValue() + (w/2 - m.stringWidth(text).intValue()/2), sy.intValue() + h)
//    else g.drawImage(image, x.intValue(), y.intValue(), w, h, null)
  }
  def onClick() = {
    clicker()
  }
}