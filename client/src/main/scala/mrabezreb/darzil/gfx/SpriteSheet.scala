package mrabezreb.darzil.gfx

import scala.collection.mutable.ArrayBuffer

class SpriteSheet(val image: Image, val w: Int, val h: Int) {
//  println("Spritesheet w/h " + w+"/"+h)
  private def gen(): List[List[Image]] = {
    val t = List.newBuilder[List[Image]]
    var xi = 0
    var yi = 0
    val xm = image.getWidth-w
    while(xi <= xm) {
      var row = List.newBuilder[Image]
      yi = 0
      while(yi <= image.getHeight-h) {
        row += Image.copy(image.getSubimage(xi, yi, w, h))
        yi += h
//        println("Got xi" + xi + " yi" + yi)
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