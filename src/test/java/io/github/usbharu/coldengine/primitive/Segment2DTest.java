package io.github.usbharu.coldengine.primitive;

import org.testng.annotations.Test;

public class Segment2DTest {

  @Test
  public void testConstructor() {
    new Segment2D(new Float2D(0, 0), new Float2D(1, 1));
  }
}