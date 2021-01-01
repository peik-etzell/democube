package Demo

import Constants._
import o1._

class Line(val a: Point, val b: Point, val color: o1.gui.Color = LineColor) {
  def toVec = a.vecTo(b)

  def toPic(vectors: Vector[Vec], head: Point): o1.Pic = {
    line(a.relativePos(vectors, head), b.relativePos(vectors, head), color)
  }
}
