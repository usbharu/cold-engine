package io.github.usbharu.coldengine.engine;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

@Test
public class ColdEngineUtilitiesTest {

  @Test
  public void testIsDigit() {
  }

  @Test
  public void testIsBoolean() {
    assertTrue(ColdEngineUtilities.isBoolean("true"));
    assertTrue(ColdEngineUtilities.isBoolean("false"));
    assertFalse(ColdEngineUtilities.isBoolean(""));
  }
}