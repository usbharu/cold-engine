package io.github.usbharu.coldengine.primitive;

public class Segment2D extends Line2D {

  public Segment2D() {
  }

  public Segment2D(Float2D float2D, Vector2D vector2D) {
    super(float2D, vector2D);
  }

  public Segment2D(Float2D float2D1, Float2D float2D2) {
    super(float2D1, float2D2.subtract(float2D1));
  }

  public Float2D getEndPoint() {
    return float2D.add(vector2D);
  }

  public Segment toSegment() {
    return new Segment(float2D.toFloat3D(), vector2D.toFloat3D());
  }
}