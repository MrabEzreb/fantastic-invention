package mrabezreb.darzil.gfx

import java.awt.Rectangle
import scala.annotation.tailrec
import java.awt.image.BufferedImage
import scala.math.Fractional

class SliceSheet(val sheet: Image, val middleRect: Rectangle) {
  //println("started")
  val tl: Rectangle = new Rectangle(0, 0, middleRect.x, middleRect.y)
  val ml: Rectangle = new Rectangle(0, middleRect.y, middleRect.x, middleRect.height)
  val bl: Rectangle = new Rectangle(0, middleRect.y + middleRect.height, middleRect.x, sheet.getHeight - middleRect.height - middleRect.y)
  val tm: Rectangle = new Rectangle(middleRect.x, 0, middleRect.width, middleRect.y)
  val mm: Rectangle = new Rectangle(middleRect)
  val bm: Rectangle = new Rectangle(middleRect.x, middleRect.y + middleRect.height, middleRect.width, sheet.getHeight - middleRect.height - middleRect.y)
  val tr: Rectangle = new Rectangle(middleRect.x + middleRect.width, 0, sheet.getWidth - middleRect.width - middleRect.x, middleRect.y)
  val mr: Rectangle = new Rectangle(middleRect.x + middleRect.width, middleRect.y, sheet.getWidth - middleRect.width - middleRect.x, middleRect.height)
  val br: Rectangle = new Rectangle(middleRect.x + middleRect.width, middleRect.y + middleRect.height, sheet.getWidth - middleRect.width - middleRect.x, sheet.getHeight - middleRect.height - middleRect.y)
  val topLeft = Image.copy(sheet.getSubimage(tl.x, tl.y, tl.width, tl.height))
  val topMiddle = Image.copy(sheet.getSubimage(tm.x, tm.y, tm.width, tm.height))
  val topRight = Image.copy(sheet.getSubimage(tr.x, tr.y, tr.width, tr.height))
  val middleLeft = Image.copy(sheet.getSubimage(ml.x, ml.y, ml.width, ml.height))
  val middleMiddle = Image.copy(sheet.getSubimage(mm.x, mm.y, mm.width, mm.height))
  val middleRight = Image.copy(sheet.getSubimage(mr.x, mr.y, mr.width, mr.height))
  
  val bottomLeft = Image.copy(sheet.getSubimage(bl.x, bl.y, bl.width, bl.height))
  val bottomMiddle = Image.copy(sheet.getSubimage(bm.x, bm.y, bm.width, bm.height))
  val bottomRight = Image.copy(sheet.getSubimage(br.x, br.y, br.width, br.height))
  
  val topRatio = gcd(topLeft.getWidth() + topRight.getWidth(), topMiddle.getWidth())
  //println(topRatio)
  val bottomRatio = gcd(bottomLeft.getWidth() + bottomRight.getWidth(), bottomMiddle.getWidth())
  //println(bottomRatio)
  val leftRatio = gcd(topLeft.getHeight + bottomLeft.getHeight, middleLeft.getHeight())
  val rightRatio = gcd(topRight.getHeight + bottomRight.getHeight, middleRight.getHeight())
  //println(leftRatio)
  //println(rightRatio)
  //println("dumb")
  def apply(w: Int, h: Int): Image = {
    var t = calc(w, topRatio)
    //println(t)
    var l = calc(h, leftRatio)
    //println(l)
    var r = calc(h, rightRatio)
    var b = calc(w, bottomRatio)
    var rat = 1.0 / (Math.min(t, l) / 48.0)
    //println("rat"+rat)
    
    var width = (w.doubleValue()).intValue()
    //println("width" + width)
    var height = (h.doubleValue()).intValue()
    //println("height" + height)
    
    var buf = new Image(width, height, BufferedImage.TYPE_INT_ARGB)
    var g = buf.getGraphics
    
    g.drawImage(topLeft.scale(1.0/rat), 0, 0, null)
    g.drawImage(topRight.scale(1.0/rat), width - topRight.scale(1.0/rat).getWidth, 0, null)
    g.drawImage(bottomLeft.scale(1.0/rat), 0, height-bottomLeft.scale(1.0/rat).getHeight, null)
    g.drawImage(bottomRight.scale(1.0/rat), width - bottomRight.scale(1.0/rat).getWidth, height-bottomRight.scale(1.0/rat).getHeight, null)
    
    var w2 = topLeft.scale(1.0/rat).getWidth
    while(w2 < width - topRight.scale(1.0/rat).getWidth) {
      g.drawImage(topMiddle.scale(1.0/rat), w2, 0, null)
      w2 += topMiddle.scale(1.0/rat).getWidth
    }
    var w3 = bottomLeft.scale(1.0/rat).getWidth
    while(w3 < width - bottomRight.scale(1.0/rat).getWidth) {
      g.drawImage(bottomMiddle.scale(1.0/rat), w3, height - bottomMiddle.scale(1.0/rat).getHeight, null)
      w3 += bottomMiddle.scale(1.0/rat).getWidth
    }
    var h2 = topLeft.scale(1.0/rat).getHeight
    while(h2 < height - bottomLeft.scale(1.0/rat).getHeight) {
      g.drawImage(middleLeft.scale(1.0/rat), 0, h2, null)
      h2 += middleLeft.scale(1.0/rat).getHeight
    }
    var h3 = topRight.scale(1.0/rat).getHeight
    while(h3 < height - bottomRight.scale(1.0/rat).getHeight) {
      g.drawImage(middleRight.scale(1.0/rat), width - middleRight.scale(1.0/rat).getWidth, h3, null)
      h3 += middleRight.scale(1.0/rat).getHeight
    }
    var m1 = middleLeft.scale(1.0/rat).getWidth
    while(m1 < width - middleRight.scale(1.0/rat).getWidth) {
      //println("m1w" + m1 + "," + (width - middleRight.scale(1.0/rat).getWidth))
      var m2 = bottomMiddle.scale(1.0/rat).getHeight
      while(m2 < height - bottomMiddle.scale(1.0/rat).getHeight) {
        g.drawImage(middleMiddle.scale(1.0/rat), m1, m2, null)
        //println(m1 + "," + m2)
        m2 += middleMiddle.scale(1.0/rat).getHeight
      }
      m1 += middleMiddle.scale(1.0/rat).getWidth
    }
    g.dispose()
    //println("hi")
    //println(buf.getHeight + " " + buf.getWidth)
//    buf.resize(w, h)
    buf
//    var topwidth = w
//    var topwidth2 = w - tl.width - tr.width
//    var topwidth3 = 0
//    if(topwidth2 % topMiddle.getWidth == 0) {
//      topwidth3 = topwidth2 / topMiddle.getWidth
//    } else {
//      
//    }
//    var t = w - tl.width - tr.width
//    var trt = (t.doubleValue()/tm.width.doubleValue())
//    var tg = gcd(tm.width, t)
//    var b = w - bl.width - br.width
//    var bg = gcd(bm.width, b)
//    var l = h - tl.height - bl.height
//    var lg = gcd(ml.width, l)
//    var r = h - tr.height - br.height
//    var rg = gcd(mr.width, r)
//    var ret = new Image(w, h, BufferedImage.TYPE_INT_ARGB)
//    val g1 = ret.getGraphics
//    g1.drawImage(topLeft, 0, 0,  null)
//    g1.drawImage(tms, topLeft.getWidth, 0, null)
//    g1.drawImage(topRight, w-topRight.getWidth, 0, null)
//    g1.dispose()
//    ret
  }
  
  private def calc(l: Double, r: Double): Double = {
    var l1 = l / r
    var l2 = gcd(l, r)
//    var l2 = fds(l1)
//    //println("l" + l.intValue() * l2)
//    //println("r" + r.intValue() * l2)
    l2
  }
  
  private def fds(d: Double): Double = {
    gcd(fn(d), fd(d))
  }
  
  private def fn(d: Double): Double = {
    val df = fd(d)
    (d * df)
  }
  
  private def fd(d: Double): Double = {
    val s = d.toString()
    s.length() - 1 - s.indexOf('.')
  }
  
  @tailrec
  private def mmod(a: Double, b: Double): Double = {
    if(a % b == 0) a / b
    mmod(a, b)
  }
  
  @tailrec
  private def gcd(a: Double, b: Double): Double = {
    if(b == 0.0) return a
    gcd(b, a % b)
  }
  
  private def lcm(a: Double, b: Double): Double = {
    a * (b / gcd(a, b).intValue())
  }
}