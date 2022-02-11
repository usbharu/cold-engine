package io.github.usbharu.coldengine.primitive;

/**
 * 2成分{@code Float}
 *
 * @author usbharu
 * @version 0.0.4
 * @since 0.0.4
 */
public class Float2D implements Primitive {

  float x;
  float y;

  public Float2D() {
  }

  public Float2D(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public Float2D(Float2D float2D) {
    this(float2D.x, float2D.y);
  }

  public static Float2D zero() {
    return new Float2D(0, 0);
  }

  public static Float2D one() {
    return new Float2D(1, 1);
  }

  public Float2D add(Float2D float2D) {
    return new Float2D(x + float2D.x, y + float2D.y);
  }

  public Float2D subtract(Float2D float2D) {
    return new Float2D(x - float2D.x, y - float2D.y);
  }

  public Float2D minus() {
    return new Float2D(-x, -y);
  }

  public Float2D multiply(Float2D float2D) {
    return new Float2D(x * float2D.x, y * float2D.y);
  }

  public Float2D divide(Float2D float2D) {
    return new Float2D(x / float2D.x, y / float2D.y);
  }

  public Float2D multiply(float r) {
    return new Float2D(x * r, y * r);
  }

  public Float2D divide(float r) {
    return new Float2D(x / r, r / y);
  }

  /**
   * 指定された2成分{@code Float}との内積を返します。
   *
   * @param float2D 計算する2成分{@code Float}
   * @return 内積
   */
  public float dot(Float2D float2D) {
    return x * float2D.x + y * float2D.y;
  }

  /**
   * 指定された2成分{@code Float}との外積を返します。
   *
   * @param float2D 計算する2成分{@code Float}
   * @return 外積
   */
  public float cross(Float2D float2D) {
    return x * float2D.y - y * float2D.x;
  }

  /**
   * ベクトルとして捉えたときの長さを返します。
   *
   * @return 長さ
   */
  public float length() {
    return (float) Math.sqrt(lengthSq());
  }

  /**
   * べき乗長さを返します。
   *
   * @return べき乗長さ
   */
  public float lengthSq() {
    return x * x + y * y;
  }

  public Float3D toFloat3D() {
    return new Float3D(this);
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void normalize() {
    float length = length();
    if (length > 0.0f) {
      x /= length;
      y /= length;
    }
  }

  public Float2D getNormal() {
    float length = length();
    if (length > 0.0f) {
      return new Float2D(x / length, y / length);
    }
    return new Float2D();
  }
}