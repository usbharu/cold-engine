package io.github.usbharu.coldengine.primitive;

public class Capsule implements Primitive {

  final Segment segment;
  float radius;

  public Capsule() {
    segment = new Segment();
    radius = 0.5f;
  }

  public Capsule(Segment segment, float radius) {
    this.segment = segment;
    this.radius = radius;
  }
}