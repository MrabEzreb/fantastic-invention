package mrabezreb.darzil.state

import java.awt.Graphics
import mrabezreb.darzil.Game

abstract class State(var game: Game) {
  def tick(): Unit
  def render(g: Graphics): Unit
}

object State {
  var state: State = null
//  var state: State = new State() { def tick() = {}; def render(g: Graphics) = {} }
}