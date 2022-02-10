package io.github.usbharu.coldengine.engine;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.UIManager;

public class DialogUtility {

  public static String getString(String key, Component c) {
    Method method = null;
    try {
      method = UIManager.class.getDeclaredMethod("getString", Object.class, Component.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    if (method != null) {
      method.setAccessible(true);
      try {
        return String.valueOf(method.invoke(null, key, c));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}