package io.github.usbharu.coldengine.utilities;

import java.util.Locale;
import java.util.ResourceBundle;

public class TranslatableString
    implements Translatable {

  public static String getString(String key) {
    return ResourceBundle.getBundle("lang", Locale.getDefault()).getString(key);
  }
}