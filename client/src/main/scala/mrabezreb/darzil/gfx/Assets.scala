package mrabezreb.darzil.gfx

object Assets {
  var saucers: SpriteSheet = null
  var player: SpriteSheet = null
  var grass: Image = null
  var stone: Image = null
  def init() = {
    saucers = new SpriteSheet(Image.load("/ship/saucer.png"), 96, 96)
    player = new SpriteSheet(Image.load("/player/george.png"), 48, 48)
    grass = Image.load("/daneeku/tilesets/grass.png")
    stone = Image.load("/daneeku/tilesets/rock.png")
  }
}