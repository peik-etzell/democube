package Demo

import Constants._
import o1._

object Demo extends App {
  val cube = new CubeTest(3)
  val container = new CubeTest(2 * WorldSize)
  val camera = new Camera

  val gui = new View(cube, TickRate, "Cube") {
    def makePic = {
      camera.draw
    }

    override def onKeyDown(key: Key) = {
      if (key == Key.Q) {
        camera.lookLeft()
      } else if (key == Key.E) {
        camera.lookRight()
      } else if (key == Key.W) {
        camera.move(camera.vectors(2).scaled(0.0001))
      } else if (key == Key.A) {
        camera.move(camera.vectors(0).scaled(-0.0001))
      } else if (key == Key.S) {
        camera.move(camera.vectors(2).scaled(-0.0001))
      } else if (key == Key.D) {
        camera.move(camera.vectors(0).scaled(0.0001))
      } else if (key == Key.R) {
        cube.reset()
      }
    }

    override def onTick(): Unit = {
      camera.addLines(cube.lines.appendedAll(container.lines))
      cube.onTick()
    }
  }
  gui.start()

}
