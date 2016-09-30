package mrabezreb.darzil.state

import java.awt.Graphics
import mrabezreb.darzil.Game

abstract class State() {
  def init(): Unit
  def tick(): Unit
  def render(g: Graphics): Unit
}

object State {
  var state: State = null
  var gameState = new GameState()
  var menuState = new MenuState()
  def setState(s: State) = {
    state = s
    state.init()
  }
//  var state: State = new State() { def tick() = {}; def render(g: Graphics) = {} }
}