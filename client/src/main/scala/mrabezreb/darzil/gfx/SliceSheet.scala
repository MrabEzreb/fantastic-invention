package mrabezreb.darzil.gfx

import java.awt.Rectangle
import scala.annotation.tailrec
import java.awt.image.BufferedImage

class SliceSheet(val sheet: Image, val tl: Rectangle, val ml: Rectangle, val bl: Rectangle, val tm: Rectangle, val mm: Rectangle, val bm: Rectangle, val tr: Rectangle, val mr: Rectangle, val br: Rectangle) {
  
  val topLeft = Image.copy(sheet.getSubimage(tl.x, tl.y, tl.width, tl.height))
  val topMiddle = Image.copy(sheet.getSubimage(tm.x, tm.y, tm.width, tm.height))
  val topRight = Image.copy(sheet.getSubimage(tr.x, tr.y, tr.width, tr.height))
  
  val middleLeft = Image.copy(sheet.getSubimage(ml.x, ml.y, ml.width, ml.height))
  val middleMiddle = Image.copy(sheet.getSubimage(mm.x, mm.y, mm.width, mm.height))
  val middleRight = Image.copy(sheet.getSubimage(mr.x, mr.y, mr.width, mr.height))
  
  val bottomLeft = Image.copy(sheet.getSubimage(bl.x, bl.y, bl.width, bl.height))
  val bottomMiddle = Image.copy(sheet.getSubimage(bm.x, bm.y, bm.width, bm.height))
  val bottomRight = Image.copy(sheet.getSubimage(br.x, br.y, br.width, br.height))
  
  def apply(w: Int, h: Int) = {
    var t = w - tl.width - tr.width
    var trt = (t.doubleValue()/tm.width.doubleValue())
    var tg = gcd(tm.width, t)
    var b = w - bl.width - br.width
    var bg = gcd(bm.width, b)
    var l = h - tl.height - bl.height
    var lg = gcd(ml.width, l)
    var r = h - tr.height - br.height
    var rg = gcd(mr.width, r)
    var ret = new Image(w, h, BufferedImage.TYPE_INT_ARGB)
    val g1 = ret.getGraphics
    g1.drawImage(topLeft, 0, 0,  null)
//    g1.drawImage(tms, topLeft.getWidth, 0, null)
    g1.drawImage(topRight, w-topRight.getWidth, 0, null)
    g1.dispose()
    ret
  }
  
  @tailrec
  private def gcd(a: Double, b: Double): Double = {
    if(b == 0) a
    gcd(b, a % b)
  }
}