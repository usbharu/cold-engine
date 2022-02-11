package io.github.usbharu.coldengine.primitive;

import org.testng.annotations.Test;

public class Float3DTest {

  @Test
  public void testAdd() {
    new Float3D(0, 0, 0).add(Float2D.one());
  }
}