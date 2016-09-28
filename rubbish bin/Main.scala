package mrabezreb.darzil

import processing.core.PApplet
import mrabezreb.darzil.video.ZipVid
import processing.video.Movie
import processing.core.PConstants
import java.io.File
import mrabezreb.darzil.physics.AnimationController
import mrabezreb.darzil.physics.AnimatedValue

class Main extends PApplet {
  override def settings() = {
    fullScreen()
  }
  var INTRO1: Movie = null
  var INTRO2: Movie = null
  var INTRO1A: AnimatedValue = null
  var INTRO2A: AnimatedValue = null
  var currentError = ""
  override def setup() = {
    frameRate(60)
    INTRO1 = findMovie("intro")
    INTRO1.frameRate(60)
    INTRO2 = findMovie("p5")
    INTRO2.frameRate(60)
    AnimationController.setup(this)
  }
  def fpsCounter(): Unit = {
    textSize(25)
    textAlign(PConstants.LEFT, PConstants.TOP)
    fill(0,255,0)
    text(frameRate.ceil.intValue().toString, 0, 0)
  }
  def errorMessage(): Unit = {
    textSize(25)
    textAlign(PConstants.RIGHT, PConstants.TOP)
    fill(255,0,0)
    text(currentError.substring(math.min(3, currentError.length())), width, 0)
  }
  var fade = 0f
  var fadeD = 0
  override def draw() = {
    AnimationController.update(this)
    State() match {
      case "INTRO1" => {
        if (!State.started) {
          State.begin()
          INTRO1.play()
//          INTRO1.pause()
          INTRO1A = new AnimatedValue(0)
          INTRO1A.animateChange(5, 5000, { (value: Float, delta: Float, parent: AnimatedValue.AnimateCallback) =>
            INTRO1.jump(value + delta)
//            INTRO1.read()
          })
        }
        background(0)
        image(INTRO1, 0, 0)
        if(INTRO1.time() == INTRO1.duration()) {
          State++
        }
        fpsCounter()
        errorMessage()
      }
      case "INTRO2" => {
        if (!State.started) {
          State.begin()
          INTRO2.play()
//          INTRO2.pause()
          INTRO2A = new AnimatedValue(0)
          INTRO2A.animateChange(5, 5000, { (value: Float, delta: Float, parent: AnimatedValue.AnimateCallback) =>
            INTRO2.jump(value + delta)
//            INTRO2.read()
          })
          fade = 60f
          fadeD = 1
        }
        fade += -1
        if(fadeD == 1) {
          if (fade <= 0) {
            fade = 180f
            fadeD = 0
          } else {
            tint(math.abs(((fade/60f)*255f)-255f))
          }
        } else if(fadeD == -1) {
          if (fade <= 0) {
            fade = 180f
            fadeD = 0
          } else {
            tint((fade/60f)*255f)
          }
        } else if(fadeD == 0) {
          if (fade <= 0) {
            fade = 60f
            fadeD = -1
          }
        }
        background(0)
        image(INTRO2, 0, 0)
        fpsCounter()
        errorMessage()
        if(INTRO2.time() == INTRO2.duration()) {
          State++
        }
      }
      case "MENU" => {
      }
    }
  }
  def movieEvent(m: Movie) = {
    m.read()
  }
  def findMovie(name: String): Movie = {
    if(currentError.substring(0, math.min(3, currentError.length())).equals("RNF")) {
      currentError = ""
    }
    var mov: Movie = null
    if(new File(this.dataPath(name+"/"+displayWidth+"x"+displayHeight+".avi")).exists()) {
      mov = new Movie(this, name+"/"+displayWidth+"x"+displayHeight+".avi")
    } else {
        currentError = "RNFUnsupported Resolution"
        mov = new Movie(this, name+".avi")
    }
    mov
  }
  def displayMovieFrame(m: Movie): Unit = {
    displayMovieFrame(m, 0, 0, width, height)
  }
  def displayMovieFrame(m: Movie, x: Int, y: Int, w: Int, h: Int): Unit = {
    if(m.pixelWidth != 0) {
      if(w != m.pixelWidth || h != m.pixelHeight) {
        val im = m.get()
        image(im, x, y)
      } else {
        image(m, x, y)
      }
    }
  }
}

object State {
  var index = 0
  var started = false
  val names = List("INTRO1", "INTRO2", "MENU")
  def apply(): String = {
    names(index)
  }
  def ++(): Unit = {
    started = false
    index += 1
  }
  def :=(v: Int): Unit = {
    started = false
    index = v
  }
  def begin(): Unit = {
    started = true
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    PApplet.main("mrabezreb.darzil.Main")
  }
}