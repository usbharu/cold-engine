package io.github.usbharu.coldengine.primitive;

public class Circle2D implements Primitive {

  final Float2D float2D;
  float radius;

  public Circle2D() {
    float2D = new Float2D();
    radius = 0.5f;
  }

  public Circle2D(Float2D float2D, float radius) {
    this.float2D = float2D;
    this.radius = radius;
  }
}