package Demo
import Constants._
import o1._

class Camera {
  var head = new Point(0, 0, - 5 * WorldSize)
  val headVectorSize = 1000
  val vectors = Vector(new Vec(headVectorSize, 0, 0), new Vec(0, headVectorSize, 0), new Vec(0, 0, headVectorSize))
  var lines = Vector[Line]()

  val background = rectangle(ViewSize, ViewSize, BackGroundColor)

  def draw = {
    var view = background
    for (line <- lines) {
      view = view.place(line.toPic(vectors, head), line.a.relativePos(vectors, head))

    }
    view
  }

  def addLines(lines: Vector[Line]): Unit = {
    this.lines = lines
  }

  def move(vec: Vec) = {
    this.head = vec.from(head)
  }

  def lookLeft() = {
    this.vectors.foreach(_.rotate('y', 0.5))
  }

  def lookRight() = {
    this.vectors.foreach(_.rotate('y', -0.5))
  }
}
