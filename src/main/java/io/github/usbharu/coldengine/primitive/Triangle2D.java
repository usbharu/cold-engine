package io.github.usbharu.coldengine.primitive;

public class Triangle2D implements Primitive {

  Float2D[] p;

  public Triangle2D() {
    p = new Float2D[] {Float2D.zero(), Float2D.zero(), Float2D.zero()};
  }

  public Triangle2D(Float2D p1, Float2D p2, Float2D p3) {
    p = new Float2D[] {p1, p2, p3};
  }

  float length(int index) {
    return vector(index).length();
  }

  Vector2D vector(int index) {
    int[] indexRap = {1, 2, 0, 1};
    return new Vector2D(p[indexRap[index + 1]].subtract(p[indexRap[index]]));
  }
}