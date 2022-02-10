package io.github.usbharu.coldengine.engine;

/**
 * {@code ColdEngineUtilities} is a utility class for {@link ColdEngine}.
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.1
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
    if (str == null || str.isEmpty()) {
      return false;
    }
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
    if (str == null || str.isEmpty()) {
      return false;
    }
    return str.equalsIgnoreCase("false") || str.equalsIgnoreCase("true");
  }

  public static void invoke(Runnable runnable, int waitTime) {
    FrameUpdateManager.getInstance().addInvokeQue(new InvokeQue(runnable, waitTime));
  }

}

class InvokeQue {

  long issueTime;
  Runnable runnable;
  int waitTime;
  boolean wasRun = false;

  InvokeQue(Runnable runnable, int waitTime) {
    issueTime = System.currentTimeMillis();
    this.runnable = runnable;
    this.waitTime = waitTime;
  }

  public void run() {
    runnable.run();
    wasRun = true;
  }
}