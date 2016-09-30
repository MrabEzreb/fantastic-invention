package mrabezreb.darzil.gfx

object Assets {
  var saucers: SpriteSheet = null
  var player: SpriteSheet = null
  var player_character: SpriteSheet = null
  var grass: Image = null
  var stone: Image = null
  var ak: Image = null
  var tree: Image = null
  var scrolls: Image = null
  var menuscroll: Image = null
  var menuscrolln: Image = null
  var menuscrollh: Image = null
  def init() = {
    saucers = new SpriteSheet(Image.load("/ship/saucer.png"), 96, 96)
    player = new SpriteSheet(Image.load("/player/george.png"), 48, 48)
    player_character = new SpriteSheet(Image.load("/character/player.png"), 64, 64)
//    scrolls = Image.load("/daneeku/ui/play_scroll.png")
    menuscroll = Image.load("/daneeku/ui/play_scroll.png")
    menuscrolln = Image.copy(menuscroll.getSubimage(0, 0, 192, 192))
    menuscrollh = Image.copy(menuscroll.getSubimage(192, 0, 192, 192))
    grass = Image.load("/daneeku/tilesets/grass.png")
    stone = Image.load("/daneeku/tilesets/rock.png")
    ak = Image.load("/daneeku/tilesets/ak.png")
    tree = Image.copy(ak.getSubimage(256, 480, 96, 192))
  }
}