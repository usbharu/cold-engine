package io.github.usbharu.coldengine.primitive;

public class Line2D implements Primitive {

  final Float2D float2D;
  final Vector2D vector2D;

  public Line2D() {
    float2D = new Float2D();
    vector2D = null;
  }

  public Line2D(Float2D float2D, Vector2D vector2D) {
    this.float2D = float2D;
    this.vector2D = vector2D;
  }

  protected Line2D(Float2D float2D1, Float2D float2D2) {
    this.float2D = float2D1;
    this.vector2D = new Vector2D(float2D2);
  }

  public Float2D getPoint(float t) {
    return float2D.add(vector2D.multiply(t));
  }

  public Line toLine() {
    return new Line(float2D.toFloat3D(), float2D.toFloat3D());
  }
}