package mrabezreb.darzil.gfx

import java.awt.Rectangle
import java.awt.Font
import java.awt.GraphicsEnvironment

object Assets {
  var saucers: SpriteSheet = null
  var player: SpriteSheet = null
  var player_character: SpriteSheet = null
  var grass: Image = null
  var stone: Image = null
  var ak: Image = null
  var tree: Image = null
  var scrolls: Image = null
  var scroll: SliceSheet = null
  var menuscroll: Image = null
  var menuscrolln: Image = null
  var menuscrollh: Image = null
  var text: Image = null
  var play1: Image = null
  var play2: Image = null
  var options1: Image = null
  var options2: Image = null
  var cookie: Font = null
  var dejavumono: Font = null
  var farming_fishing: SpriteSheet = null
  var plowed_soil: SliceTile = null
  var plowed_soil_1: SliceSheet = null
  def init() = {
    plowed_soil = new SliceTile(Image.load("/daneeku/tilesets/plowed_soil.png"), "Plowed-Soil", false)
//    plowed_soil_1 = new SliceSheet(Image.copy(plowed_soil.getSubimage(0, 64, 96, 96)), new Rectangle(32, 32, 32, 32))
    dejavumono = Font.createFont(Font.TRUETYPE_FONT, getClass.getResourceAsStream("/DejaVuSansMono.ttf"))
    cookie = Font.createFont(Font.TRUETYPE_FONT, getClass.getResourceAsStream("/cookie.ttf"))
    var ge = GraphicsEnvironment.getLocalGraphicsEnvironment
    ge.registerFont(cookie)
    ge.registerFont(dejavumono)
    farming_fishing = new SpriteSheet(Image.load("/daneeku/tilesets/farming_fishing.png"), 32, 32)
    text = Image.load("/ui/words.png")
    play1 = Image.copy(text.getSubimage(0, 0, 288, 144))
    play2 = Image.copy(text.getSubimage(0,144,288,144))
    options1 = Image.copy(text.getSubimage(288, 0, 288, 144))
    options2 = Image.copy(text.getSubimage(288,144,288,144))
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
    scroll = new SliceSheet(Image.load("/ui/scroll.png"), new Rectangle(72, 72, 48, 48))
  }
}