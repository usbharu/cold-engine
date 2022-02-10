package io.github.usbharu.coldengine.utilities;

import io.github.usbharu.coldengine.engine.ColdEngine;
import io.github.usbharu.coldengine.engine.FrameUpdateManager;
import io.github.usbharu.coldengine.engine.InvokeQue;
import io.github.usbharu.coldengine.engine.SceneManager;
import io.github.usbharu.coldengine.engine.audio.SoundManager;

/**
 * ゲーム制作のためのユーティリティクラス
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.3
 */
public class GameUtilities {

  private GameUtilities() {
  }

  public static SoundManager SoundManager() {
    return SoundManager.getInstance();
  }

  public static ColdEngine ColdEngine() {
    return ColdEngine.getInstance();
  }

  public static SceneManager SceneManager() {
    return SceneManager.getInstance();
  }

  public static FrameUpdateManager FrameUpdateManager() {
    return FrameUpdateManager.getInstance();
  }

  public static void invoke(Runnable runnable, int waitTime) {
    FrameUpdateManager.getInstance().addInvokeQue(new InvokeQue(runnable, waitTime));
  }
}