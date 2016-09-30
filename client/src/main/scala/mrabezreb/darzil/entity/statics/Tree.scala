package mrabezreb.darzil.entity.statics

import mrabezreb.darzil.tile.Tile
import java.awt.Graphics
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.gfx.Camera
import java.awt.Rectangle

class Tree(tx: Float, ty: Float) extends StaticEntity(tx, ty, Tile.tileWidth, Tile.tileHeight*2) {
  bounds = new Rectangle(20, 48, 8, 16)
  def tick() = {
    
  }
  def render(g: Graphics) = {
	  g.drawImage(Assets.tree, x.intValue() - Camera.xOffset.intValue(), y.intValue() - Camera.yOffset.intValue(), width, height, null)
//	  g.drawRect(bounds.x - Camera.xOffset.intValue() + x.intValue(), bounds.y - Camera.yOffset.intValue() + y.intValue(), bounds.width, bounds.height)
  }
}