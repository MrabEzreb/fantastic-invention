package mrabezreb.darzil.display

import javax.swing.JFrame
import java.awt.Canvas
import java.awt.Dimension

class Display(val title: String, val width: Int, val height: Int) {
  
  var frame = new JFrame(title)
  frame.setSize(width, height)
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frame.setResizable(false)
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)
  
  val canvas = new Canvas
  canvas.setPreferredSize(new Dimension(width, height))
  canvas.setMaximumSize(new Dimension(width, height))
  canvas.setMinimumSize(new Dimension(width, height))
  canvas.setFocusable(false)
  
  frame.add(canvas)
  frame.pack()
  
}