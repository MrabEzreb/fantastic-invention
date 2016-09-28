package mrabezreb.darzil.gfx

import scala.collection.mutable.ArrayBuffer

class SpriteSheet(val image: Image, val w: Int, val h: Int) {
  private def gen(): List[List[Image]] = {
    val t = List.newBuilder[List[Image]]
    var xi = 0
    var yi = 0
    while(xi <= image.getWidth-w) {
      var row = List.newBuilder[Image]
      while(yi <= image.getHeight-h) {
        row += Image.copy(image.getSubimage(xi, yi, w, h))
        yi += h
      }
      t += row.result()
      xi += w
    }
    t.result()
  }
  
  val grid = gen()
  def apply(x: Int, y: Int) = {
    grid(x)(y)
  }
}