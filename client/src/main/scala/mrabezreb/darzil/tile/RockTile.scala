package mrabezreb.darzil.tile

import mrabezreb.darzil.gfx.Assets

class RockTile(var tid: Int) extends Tile(Assets.stone, tid) {
  
  override def isSolid() = {
    true
  }
}