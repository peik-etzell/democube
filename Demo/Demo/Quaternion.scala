package Demo

import math._

class Quaternion(val w: Double, val i: Double, val j: Double, val k: Double) {
  /*alpha = alpha / 2
  q1 = cos(alpha) + sin(alpha)(i + j + k)
  p = point
  q2 = cos(-alpha) + sin(-alpha)(i + j + k)

  f(p) = q1*p*q2

  */


  def multiply(by: Quaternion): Quaternion = {
    new Quaternion(
      this.w * by.w - this.i * by.i - this.j * by.j - this.k * by.k,
      this.w * by.i + this.i * by.w + this.j * by.k - this.k * by.j,
      this.w * by.j - this.i * by.k + this.j * by.w + this.k * by.i,
      this.w * by.k + this.i * by.j - this.j * by.i + this.k * by.w
    )
  }


}

object Quaternion {
  def q(spin: Vec, angle: Double): Quaternion = {
    val temp = spin.unit.scaled(sin(angle)).toQuaternion
    new Quaternion(cos(angle), temp.i, temp.j, temp.k)
  }
}
