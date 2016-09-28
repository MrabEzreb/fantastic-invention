package mrabezreb.darzil

import mrabezreb.darzil.display.Display

object Launcher {
  def main(args: Array[String]): Unit = {
    var game = new Game("Hello, World!", 576, 576)
    game.start()
  }
}