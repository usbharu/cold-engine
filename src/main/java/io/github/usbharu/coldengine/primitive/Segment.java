package io.github.usbharu.coldengine.primitive;

public class Segment extends Line {

  public Segment() {
  }

  public Segment(Float3D float3D, Vector3D vector3D) {
    super(float3D, vector3D);
  }

  public Segment(Float3D float3D1, Float3D float3D2) {
    super(float3D1, float3D2.subtract(float3D1));
  }

  public Float3D getEndPoint() {
    return float3D.add(vector3D);
  }
}