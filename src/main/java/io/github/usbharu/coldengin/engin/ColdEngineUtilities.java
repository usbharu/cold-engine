package io.github.usbharu.coldengin.engin;

public class ColdEngineUtilities {

  public static boolean isDigit(String str) {
    boolean isDigit = true;
    for (int i = 0; i < str.length(); i++) {
      if ((i == 1 && str.charAt(i) == '-') || str.charAt(i) == '.') {
        continue;
      }
      isDigit = Character.isDigit(str.charAt(i));
      if (!isDigit) {
        break;
      }
    }
    return isDigit;
  }

  public static boolean isBoolean(String str) {
    return str.equalsIgnoreCase("false") || str.equalsIgnoreCase("true");
  }

}