package io.github.usbharu.coldengine.primitive;

public class Line implements Primitive {

  final Float3D float3D;
  final Vector3D vector3D;

  public Line() {
    float3D = new Float3D();
    vector3D = new Vector3D();
  }

  public Line(Float3D float3D, Vector3D vector3D) {
    this.float3D = float3D;
    this.vector3D = vector3D;
  }

  protected Line(Float3D float3D1, Float3D float3D2) {
    this.float3D = float3D1;
    this.vector3D = new Vector3D(float3D2);
  }

  public Float3D getPoint(float t) {
    return float3D.add(vector3D.multiply(t));
  }
}