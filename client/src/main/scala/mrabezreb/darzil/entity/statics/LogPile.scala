package mrabezreb.darzil.entity.statics

import mrabezreb.darzil.tile.Tile
import java.awt.Graphics
import mrabezreb.darzil.gfx.Assets
import mrabezreb.darzil.gfx.Camera
import java.awt.Rectangle
import mrabezreb.darzil.entity.item.Item
import mrabezreb.darzil.entity.item.ToolType
import mrabezreb.darzil.entity.Spawnable

@Spawnable
class LogPile(tx: Double, ty: Double) extends StaticEntity(tx, ty, 32, 32) {
  bounds = new Rectangle(8, 8, 32, 32)
  
  def hurt(equipped: ToolType) = {
    if(equipped.typeName.equals("")) hurt(equipped.sDamage)
  }
  
  def tick() = {
    
  }
  def render(g: Graphics) = {
	  g.drawImage(Assets.farming_fishing(1, 1), x.intValue() - Camera.xOffset.intValue() + 8, y.intValue() - Camera.yOffset.intValue() + 8, width, height, null)
	  g.drawRect(getCollisionBounds(0, 0).x, getCollisionBounds(0, 0).y, getCollisionBounds(0, 0).width, getCollisionBounds(0, 0).height)
//	  g.drawRect(bounds.x - Camera.xOffset.intValue() + x.intValue(), bounds.y - Camera.yOffset.intValue() + y.intValue(), bounds.width, bounds.height)
  }
  
  def die() = {
    
  }
}