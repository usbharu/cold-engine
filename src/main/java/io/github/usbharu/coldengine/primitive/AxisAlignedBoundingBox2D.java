package io.github.usbharu.coldengine.primitive;

public class AxisAlignedBoundingBox2D implements Primitive {

  final Float2D point;

  /**
   * 各軸の辺の長さの半分
   */
  final Float2D hl;

  public AxisAlignedBoundingBox2D() {
    point = new Float2D();
    hl = new Float2D();
  }

  public AxisAlignedBoundingBox2D(Float2D point, Float2D hl) {
    this.point = point;
    this.hl = hl;
  }

  public float lengthX() {
    return hl.x * 2.0f;
  }

  public float lengthY() {
    return hl.y * 2.0f;
  }

  public float length(int i) {
    return ((hl.x) + i) * 2.0f;
  }
}