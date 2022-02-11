package io.github.usbharu.coldengine.primitive;

public class Capsule2D implements Primitive {

  final Segment2D segment2D;
  float radius;

  public Capsule2D() {
    segment2D = new Segment2D();
    radius = 0.5f;
  }

  public Capsule2D(Segment2D segment2D, float radius) {
    this.segment2D = segment2D;
    this.radius = radius;
  }
}