package io.github.usbharu.coldengine.primitive;

public class AxisAlignedBoundingBox implements Primitive {

  Float3D point;
  /**
   * 各軸の辺の長さの半分
   */
  Float3D hl;

  public AxisAlignedBoundingBox() {
  }

  public AxisAlignedBoundingBox(Float3D point, Float3D hl) {
    this.point = point;
    this.hl = hl;
  }

  public float lengthX() {
    return hl.x * 2.0f;
  }

  public float lengthY() {
    return hl.y * 2.0f;
  }

  public float lengthZ() {
    return hl.z * 2.0f;
  }

  public float length(int index) {
    return (hl.x + index) * 2.0f;
  }
}