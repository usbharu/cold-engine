package io.github.usbharu.coldengine.engine;

/**
 * {@code ColdEngineUtilities} is a utility class for {@link ColdEngine}.
 */
public class ColdEngineUtilities {

  private ColdEngineUtilities() {
  }

  /**
   * {@code str}が数字かを判定します。
   *
   * @param str 判定する文字列
   * @return 数字の場合はtrue、それ以外はfalse
   * */
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

  /**
   * {@code str}が{@code boolean}かを判定します。
   *
   * @param str 判定する文字列
   * @return {@code boolean}の場合はtrue、それ以外はfalse
   */
  public static boolean isBoolean(String str) {
    return str.equalsIgnoreCase("false") || str.equalsIgnoreCase("true");
  }

}