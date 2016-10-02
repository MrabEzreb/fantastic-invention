package mrabezreb.darzil.entity

import mrabezreb.darzil.Game
import mrabezreb.darzil.Handler
import mrabezreb.darzil.tile.Tile
import java.awt.Rectangle

abstract class Creature(cx: Double, cy: Double, var cwidth: Int, var cheight: Int) extends Entity(cx, cy, cwidth, cheight) {
  var speed = Creature.defaultSpeed
  var xMove, yMove = 0.0
  
  def move() = {
    if(!checkEntityCollisions(xMove, 0)) moveX()
    if(!checkEntityCollisions(0, yMove)) moveY()
  }
  
  def moveX() = {
    if(xMove > 0) {
      val tx = (x + xMove + bounds.x + bounds.width).intValue() / Tile.tileWidth
      if(!collisionWithTile(tx, (y + bounds.y).intValue() / Tile.tileHeight) && !collisionWithTile(tx, (y + bounds.y + bounds.height).intValue() / Tile.tileHeight)) {
        x += xMove
      } else {
        x = tx * Tile.tileWidth - bounds.x - bounds.width - 1
      }
    } else if(xMove < 0) {
      val tx = (x + xMove + bounds.x).intValue() / Tile.tileWidth
      if(!collisionWithTile(tx, (y + bounds.y).intValue() / Tile.tileHeight) && !collisionWithTile(tx, (y + bounds.y + bounds.height).intValue() / Tile.tileHeight)) {
        x += xMove
      } else {
        x = tx * Tile.tileWidth + Tile.tileWidth - bounds.x
      }
    }
//    x += xMove
  }
  
  def moveY() = {
    if(yMove < 0) {
      val ty = (y + yMove + bounds.y).intValue() / Tile.tileHeight
      if(!collisionWithTile((x + bounds.x).intValue() / Tile.tileWidth, ty) && !collisionWithTile((x + bounds.x + bounds.width).intValue() / Tile.tileWidth, ty)) {
        y += yMove
      } else {
        y = ty * Tile.tileHeight + Tile.tileHeight - bounds.y
      }
    } else if(yMove > 0) {
      val ty = (y + yMove + bounds.y + bounds.height).intValue() / Tile.tileHeight
      if(!collisionWithTile((x + bounds.x).intValue() / Tile.tileWidth, ty) && !collisionWithTile((x + bounds.x + bounds.width).intValue() / Tile.tileWidth, ty)) {
        y += yMove
      } else {
        y = ty * Tile.tileHeight - bounds.y - bounds.height - 1
      }
    }
  }
  
  def collisionWithTile(x: Int, y: Int): Boolean = {
    Handler.world.getTile(x, y).isSolid()
  }
}

object Creature {
  val defaultSpeed = 2.0
  val defaultWidth = 48
  val defaultHeight = 48
}