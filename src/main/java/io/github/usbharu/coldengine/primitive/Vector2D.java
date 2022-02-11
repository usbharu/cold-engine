package io.github.usbharu.coldengine.primitive;

import static io.github.usbharu.coldengine.primitive.Primitive2D.EPSILON;

public class Vector2D extends Float2D {

  public Vector2D() {
  }

  public Vector2D(float x, float y) {
    super(x, y);
  }

  public Vector2D(Float2D float2D) {
    super(float2D.x, float2D.y);
  }

  public boolean isVertical(Vector2D vector2D) {
    float dot = dot(vector2D);
    return (-EPSILON < dot && dot < EPSILON);
  }

  public boolean isParallel(Vector2D vector2D) {
    float cross = cross(vector2D);
    return (-EPSILON < cross && cross < EPSILON);
  }

  public boolean isSharpAngle(Vector2D vector2D) {
    return (dot(vector2D) >= 0.0f);
  }
}