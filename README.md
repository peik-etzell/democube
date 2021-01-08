# democube
A simple demo of a spinning cube. 

The cube has a moving centerpoint in 3d space, with 8 corner-vectors anchored to it, rotating around. 
Rotating is done with either matrix- or quaternion multiplication. Quaternion multiplication should be accurate, while matrix multiplication will make it drift slightly off. The bouncing physics at the walls is somewhat realistic, but is using magic numbers though, adjusted to make it look better. The camera can be moved with WASD, rotated with Q and E, while the cube can be reset with R. The camera is made up of three orthogonal vectors that define the reference point for the perspective graphics. 
