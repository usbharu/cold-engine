package io.github.usbharu.coldengine.primitive;

public class Float3D {

  float x = 1;
  float y = 1;
  float z = 1;

  public Float3D() {
  }

  public Float3D(Float2D float2D) {
    x = float2D.x;
    y = float2D.y;
  }

  public Float3D(Float3D float3D) {
    this(float3D.x, float3D.y, float3D.z);
  }

  public Float3D(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Float3D(Float2D float2D, float z) {
    this(float2D);
    this.z = z;
  }

  public Float3D add(Float2D float2D) {
    return add(float2D.toFloat3D());
  }

  public Float3D add(Float3D float3D) {
    return new Float3D(x + float3D.x, y + float3D.y, z + float3D.z);
  }

  public Float3D subtract(Float2D float2D) {
    return subtract(float2D.toFloat3D());
  }

  public Float3D subtract(Float3D float3D) {
    return new Float3D(x - float3D.x, y - float3D.y, z - float3D.z);
  }

  public Float3D minus() {
    return new Float3D(-x, -y, -z);
  }

  public Float3D multiply(Float2D float2D) {
    return multiply(float2D.toFloat3D());
  }

  public Float3D multiply(Float3D float3D) {
    return new Float3D(x * float3D.x, y * float3D.y, z * float3D.z);
  }

  public Float3D divide(Float2D float2D) {
    return divide(float2D.toFloat3D());
  }

  public Float3D divide(Float3D float3D) {
    return new Float3D(x / float3D.x, y / float3D.y, x / float3D.z);
  }

  public Float3D multiply(float r) {
    return new Float3D(x * r, y * r, z * r);
  }

  public Float3D divide(float r) {
    return new Float3D(x / r, y / r, z / r);
  }


  public float dot(Float3D float3D) {
    return x * float3D.x + y * float3D.y + z * float3D.z;
  }

  public Float3D cross(Float3D float3D) {
    return new Float3D(y * float3D.z - z * float3D.y, z * float3D.x - x * float3D.z,
        x * float3D.y - y * float3D.x);
  }

  public float length() {
    return (float) Math.sqrt(lengthSq());
  }

  public float lengthSq() {
    return x * x + y * y + z * z;
  }

}