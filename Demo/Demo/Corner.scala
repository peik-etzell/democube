package Demo

class Corner(i: Double, j: Double, k: Double, val partOf: CubeTest) extends Vec(i, j, k) {
  def point: Point = {
    this.from(partOf.center)
  }

  def velocity: Vec = {
    this.cross(partOf.spin) + partOf.velocity
  }
}
