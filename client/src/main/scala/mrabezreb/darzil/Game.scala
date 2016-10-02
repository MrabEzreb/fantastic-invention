package mrabezreb.darzil

import mrabezreb.darzil.display.Display
import java.awt.image.BufferStrategy
import mrabezreb.darzil.gfx.Assets
import java.awt.image.BufferStrategy
import mrabezreb.darzil.gfx.Assets
import java.awt.Graphics
import mrabezreb.darzil.state.State
import mrabezreb.darzil.state.GameState
import mrabezreb.darzil.input.KeyManager
import mrabezreb.darzil.gfx.Camera
import mrabezreb.darzil.input.MouseManager
import mrabezreb.darzil.state.MenuState
import mrabezreb.darzil.cheat.Console

class Game(val title: String, val width: Int, val height: Int) extends Runnable {
  
  private var display: Display = null
  private var thread: Thread = null
  private var running = false
  private var bs: BufferStrategy = null
  private var g: Graphics = null
  
  def start(): Unit = {
    if(running) return
    running = true
    thread = new Thread(this)
    thread.start()
  }
  
  def stop(): Unit = {
    if(!running) return
    running = false
    thread.join()
  }
  
  def init(): Unit = {
    display = new Display(title, width, height)
    display.frame.addKeyListener(KeyManager)
    display.frame.addMouseListener(MouseManager)
    display.frame.addMouseMotionListener(MouseManager)
    display.canvas.addMouseListener(MouseManager)
    display.canvas.addMouseMotionListener(MouseManager)
    Assets.init()
    Handler.game = this
    State.setState(State.menuState)
  }
  
  def tick(): Unit = {
    if(State.state != null) {
      State.state.tick()
    }
    KeyManager.tick()
    Console.tick()
  }
  
  def render(): Unit = {
    bs = display.canvas.getBufferStrategy
    if(bs == null) {
      display.canvas.createBufferStrategy(3)
      return
    }
    g = bs.getDrawGraphics
    g.clearRect(0, 0, width, height)
    //Draw here!
    if(State.state != null) {
      State.state.render(g)
    }
    //End Drawing!
    bs.show()
    g.dispose()
  }
  
  def run(): Unit = {
    init()
    
    val tps = 60.0
    val timePerTick = 1000000000 / tps
    var delta = 0.0
    var now = 0L
    var lastTime = System.nanoTime()
    var timer = 0L
    var ticks = 0
    var frames = 0
    
    while (running) {
      now = System.nanoTime()
      delta += (now - lastTime) / timePerTick
      timer += now - lastTime
      lastTime = now
      if(delta >= 1) {
        tick()
        ticks += 1
        delta -= 1
      }
      if(timer >= 1000000000) {
        println("TPS: " + ticks)
        println("FPS: " + frames)
        timer = 0L
        ticks = 0
        frames = 0
      }
      render()
      frames += 1
    }
    stop()
  }
}