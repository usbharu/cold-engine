package io.github.usbharu.coldengine.primitive;

public class Sphere implements Primitive {

  final Float3D point;
  float radius;

  public Sphere() {
    point = new Float3D();
    radius = 0.5f;
  }

  public Sphere(Float3D point, float radius) {
    this.point = point;
    this.radius = radius;
  }
}