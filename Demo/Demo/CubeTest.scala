package Demo

import scala.math._
import Constants._

import scala.util.Random

class CubeTest(val sideLength: Double) {
  //Used in checking corners trajectory
  val xAxis = new Vec(1, 0, 0)
  val yAxis = new Vec(0, 1, 0)
  val zAxis = new Vec(0, 0, 1)

  //Vectors
  var spin = new Vec(1, 1, 1)
  var velocity = new Vec(0, 0, 0)

  //[Point]
  var center = new Point(0, 0, 0)

  //Corner objects (Vecs)
  val l = 0.5 * sideLength
  val a = new Corner(l, l, l, this)
  val b = new Corner(l, l, -l, this)
  val c = new Corner(l, -l, l, this)
  val d = new Corner(-l, l, l, this)
  val e = new Corner(l, -l, -l, this)
  val f = new Corner(-l, -l, l, this)
  val g = new Corner(-l, l, -l, this)
  val h = new Corner(-l, -l, -l, this)

  //[Corner]
  val corners = Vector(a, b, c, d, e, f, g, h)

  //Defines the lines between the correct corners
  def lines = {
    Vector(
      new Line(a.point, b.point),
      new Line(a.point, c.point),
      new Line(a.point, d.point),
      new Line(b.point, e.point),
      new Line(b.point, g.point),
      new Line(c.point, e.point),
      new Line(c.point, f.point),
      new Line(d.point, f.point),
      new Line(d.point, g.point),
      new Line(e.point, h.point),
      new Line(f.point, h.point),
      new Line(g.point, h.point),
    )
  }

  //Calculates the collision semi-realistically
  private def impact(corner: Corner, axis: Char): Unit = {
    val cornerVel = corner.velocity
    val inverseVel = cornerVel.scaled(-1.0 / pow(sideLength, 2) )
    this.spin = inverseVel.cross(corner)
    axis match {
      case 'x' => this.velocity.i *= -0.6
      case 'y' => this.velocity.j *= -0.6
      case 'z' => this.velocity.k *= -0.6
    }
    this.velocity add inverseVel.scaled(0.4)
  }

  //Move centerpoint according to velocity
  private def move(): Unit = {
    this.center = this.velocity.scaled(1.0 / TickRate).from(this.center)
  }

  //Return corners with lowest and highest x, y, z coordinates
  private def boundsX: (Corner, Corner) = {
    val x = corners.sortBy(_.point.x)
    (x.head, x.last)
  }
  private def boundsY: (Corner, Corner) = {
    val y = corners.sortBy(_.point.y)
    (y.head, y.last)
  }
  private def boundsZ: (Corner, Corner) = {
    val z = corners.sortBy(_.point.z)
    (z.head, z.last)
  }

  def onTick() {
    this.move()
    //corners.foreach(_.rotate('x', spin.i))
    //corners.foreach(_.rotate('y', spin.j))
    //corners.foreach(_.rotate('z', spin.k))

    corners.foreach(_.rotateWithQ(spin))

    //Checks if any corner is outside the world AND has a trajectory going outwards
    if (boundsX._1.point.x < -WorldSize && boundsX._1.velocity.dot(xAxis) < 0) {
      impact(boundsX._1, 'x')
    } else if (boundsX._2.point.x > WorldSize && boundsX._2.velocity.dot(xAxis) > 0) {
      impact(boundsX._2, 'x')
    } else if (boundsY._1.point.y < -WorldSize && boundsY._1.velocity.dot(yAxis) < 0) {
      impact(boundsY._1, 'y')
    } else if (boundsY._2.point.y > WorldSize && boundsY._2.velocity.dot(yAxis) > 0) {
      impact(boundsY._2, 'y')
    } else if (boundsZ._1.point.z < -WorldSize && boundsZ._1.velocity.dot(zAxis) < 0) {
      impact(boundsZ._1, 'z')
    } else if (boundsZ._2.point.z > WorldSize && boundsZ._2.velocity.dot(zAxis) > 0) {
      impact(boundsZ._2, 'z')
    }
  }

  def reset(): Unit = {
    def randomVec(max: Int) = {
      def random(limit: Int) = {
        Random.nextInt(2 * limit) - limit
      }
      new Vec(random(max), random(max), random(max))
    }
    this.center = new Point(0, 0, 0)
    this.velocity = randomVec(3)
    this.spin = randomVec(2)
  }
}
