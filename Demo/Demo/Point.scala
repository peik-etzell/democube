package Demo

import Constants._
import o1.world.Pos
import scala.math._

class Point(val x: Double, val y: Double, val z: Double) {
  def lineTo(point: Point): Line = new Line(this, point)

  def vecTo(point: Point): Vec = new Vec(point.x - this.x, point.y - this.y, point.z - this.z)

  def distance(point: Point): Double = {
    this.vecTo(point).length
  }

  //Projects the point onto the 2D coordinate system relative to the camera position and orientation
  def relativePos(vectors: Vector[Vec], head: Point): Pos = { //Projects 3d point to 2d plane relative to view
    val vector = head.vecTo(this) // Vector from head to point
    vector.scale(1 / vector.length) //Make vector length 1
    val x = vector.dot(vectors(0))
    val y = vector.dot(vectors(1))
    Pos(x + ViewSize / 2, ViewSize / 2 - y) //Dotproduct with relative x, y -axis for x, y Pos in viewing plane
  }
}
