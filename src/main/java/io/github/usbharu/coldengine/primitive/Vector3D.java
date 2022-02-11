package io.github.usbharu.coldengine.primitive;

import static io.github.usbharu.coldengine.primitive.Primitive3D.EPSILON;

public class Vector3D extends Float3D {

  public Vector3D() {
    super();
  }

  public Vector3D(Float2D float2D) {
    super(float2D);
  }

  public Vector3D(Float3D float3D) {
    super(float3D);
  }

  public Vector3D(float x, float y, float z) {
    super(x, y, z);
  }

  public Vector3D(Float2D float2D, float z) {
    super(float2D, z);
  }

  public boolean isVertical(Vector3D vector3D) {
    float d = dot(vector3D);
    return (-EPSILON < d && d < EPSILON);
  }

  public boolean isParallel(Vector3D vector3D) {
    float d = cross(vector3D).lengthSq();
    return (-EPSILON < d && d < EPSILON);
  }

  public boolean isSharpAngle(Vector3D vector3D) {
    return (dot(vector3D) >= 0.0f);
  }
}