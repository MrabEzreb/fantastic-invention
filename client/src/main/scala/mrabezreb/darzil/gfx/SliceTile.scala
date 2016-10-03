package mrabezreb.darzil.gfx

import java.awt.Rectangle
import mrabezreb.darzil.tile.Tile
import mrabezreb.darzil.tile.SurroundingTiles
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import java.awt.image.BufferedImage

class SliceTile(sheet: Image, a: Boolean) {
  def this(sheet: Image, p: String, a: Boolean) = {
    this(sheet, a)
    if(!a) {
      println(p + " Sliced32 " + sliced32.grid.size)
      sliced32.grid.foreach { li => println("\t"+li.size) }
      println(p + " Sliced16 " + sliced16.grid.size)
      sliced16.grid.foreach { li => println("\t"+li.size) }
    }
  }
  var sliced32: SpriteSheet = null
  var sliced16: SpriteSheet = null
  if(!a) {
    sliced32 = new SpriteSheet(sheet, 32, 32)
    sliced16 = new SpriteSheet(sheet, 16, 16)
  }
  val trans32 = new Image(32, 32, BufferedImage.TYPE_INT_ARGB)
  val trans16 = new Image(16, 16, BufferedImage.TYPE_INT_ARGB)
  
  def finalize(im: Image): Image = {
    if(im.getWidth == Tile.tileWidth * 3) {
      if(im.getHeight == Tile.tileHeight * 3) {
        im
      } else {
        val ret = new Image(Tile.tileWidth*3, Tile.tileHeight*3, BufferedImage.TYPE_INT_ARGB)
        val g = ret.getGraphics()
        g.drawImage(im, 0, 0, Tile.tileWidth*3, Tile.tileHeight*3, null)
        g.dispose()
        ret
      }
    } else {
      val ret = new Image(Tile.tileWidth*3, Tile.tileHeight*3, BufferedImage.TYPE_INT_ARGB)
      val g = ret.getGraphics()
      g.drawImage(im, 0, 0, Tile.tileWidth*3, Tile.tileHeight*3, null)
      g.dispose()
      ret
    }
  }
  
  def addTransN(m: Image) = {
    val ret = new Image(Tile.tileWidth*3, Tile.tileHeight*3, BufferedImage.TYPE_INT_ARGB)
    val g = ret.getGraphics()
    g.drawImage(m, ret.getWidth/2 - m.getWidth/2, ret.getHeight/2 - m.getHeight/2, Tile.tileWidth, Tile.tileHeight, null)
    g.dispose()
    ret
  }
  
  def addTrans32(m: Image) = {
    val ret = new Image(96, 96, BufferedImage.TYPE_INT_ARGB)
    val g = ret.getGraphics()
    g.drawImage(m, 32, 32, null)
    g.dispose()
    ret
  }
  
  def combine32(tl: Image, tr: Image, bl: Image, br: Image, t: Image, l: Image, b: Image, r: Image, m: Image) = {
    val ret = new Image(96, 96, BufferedImage.TYPE_INT_ARGB)
    val g = ret.getGraphics
    g.drawImage(tl, 0, 0, null)
    g.drawImage(l, 0, 32, null)
    g.drawImage(bl, 0, 64, null)
    g.drawImage(b, 32, 64, null)
    g.drawImage(tr, 64, 0, null)
    g.drawImage(r, 64, 32, null)
    g.drawImage(br, 64, 64, null)
    g.drawImage(m, 32, 32, null)
    g.dispose()
    ret
  }
  
  def combine16(tl: Image, tr: Image, bl: Image, br: Image) = {
    val ret = new Image(32, 32, BufferedImage.TYPE_INT_ARGB)
    val g = ret.getGraphics
    g.drawImage(tl, 0, 0, null)
    g.drawImage(tr, 16, 0, null)
    g.drawImage(bl, 0, 16, null)
    g.drawImage(br, 16, 16, null)
    g.dispose()
    ret
  }
  
  var tSheet: Image = null
  var all: Image = null
  var nright: Image = null
  var nleft: Image = null
  var updown: Image = null
  var ndown: Image = null
  var upleft: Image = null
  var upright: Image = null
  var up: Image = null
  var nup: Image = null
  var downleft: Image = null
  var downRight: Image = null
  var down: Image = null
  var leftright: Image = null
  var left: Image = null
  var right: Image = null
  var none: Image = null
  
  if(!a) {
    all = finalize(addTrans32(sliced32(1, 3)))
    nright = finalize(addTrans32(sliced32(2, 3)))
    nleft = finalize(addTrans32(sliced32(0, 3)))
    updown = finalize(combine32(trans32, trans32, trans32, trans32, trans32, combine16(sliced16(0, 6), trans16, sliced16(0, 7), trans16), trans32, combine16(trans16, sliced16(5, 6), trans16, sliced16(5, 7)), combine16(sliced16(1, 6), sliced16(4, 6), sliced16(1, 7), sliced16(4, 7))))
    ndown = finalize(addTrans32(sliced32(1, 4)))
    upleft = finalize(addTrans32(sliced32(2, 4)))
    upright = finalize(addTrans32(sliced32(0, 4)))
    up = finalize(addTrans32(combine16(sliced16(1, 8), sliced16(4, 8), sliced16(0, 1), sliced16(1, 1))))
    nup = finalize(addTrans32(sliced32(1, 2)))
    downleft = finalize(addTrans32(sliced32(2, 2)))
    downRight = finalize(addTrans32(sliced32(0, 2)))
    down = finalize(addTrans32(combine16(sliced16(0, 1), sliced16(1, 1), sliced16(1, 5), sliced16(4, 5))))
    leftright = finalize(combine32(trans32, trans32, trans32, trans32, combine16(trans16, trans16, sliced16(2, 4), sliced16(3, 4)), trans32, combine16(trans16, trans16, sliced16(2, 8), sliced16(3, 8)), trans32, combine16(sliced16(2, 5), sliced16(3, 5), sliced16(2, 8), sliced16(3, 8))))
    left = finalize(addTrans32(combine16(sliced16(4, 5), sliced16(1, 0), sliced16(4, 8), sliced16(1, 1))))
    right = finalize(addTrans32(combine16(sliced16(0, 0), sliced16(1, 8).rotate(90f), sliced16(0, 1), sliced16(1, 5).rotate(-90f))))
    none = finalize(addTrans32(sliced32(0, 0)))
  } else {
    tSheet = finalize(addTransN(sheet))
  }
  
  def apply(s: SurroundingTiles, id: Int): Image = {
    if(a) {
      return tSheet
    } else {
      val sup = s.up() == id
      val sdown = s.down() == id
      val sleft = s.left() == id
      val sright = s.right() == id
      if(sup) {
        if(sdown) {
          if(sleft) {
            if(sright) { //All Sides
              all
            } else { //All sides but right
              nright
            }
          } else {
            if(sright) { //All but left
              nleft
            } else { //Up and Down
              updown
            }
          }
        } else {
          if(sleft) {
            if(sright) { //All but down
              ndown
            } else { //Up and Left
              upleft
            }
          } else {
            if(sright) { //Up and Right
              upright
            } else { //Up
              up
            }
          }
        }
      } else {
        if(sdown) {
          if(sleft) {
            if(sright) { //All but up
              nup
            } else { //Down and left
              downleft
            }
          } else {
            if(sright) { //Down and right
              downRight
            } else { //Down
              down
            }
          }
        } else {
          if(sleft) {
            if(sright) { //Left and Right
              leftright
            } else { //Left
              left
            }
          } else {
            if(sright) { //Right
              right
            } else { //None
              none
            }
          }
        }
      }
    }
  }
}