package io.github.usbharu.coldengine.utilities;

import io.github.usbharu.coldengine.engine.ColdEngine;
import io.github.usbharu.coldengine.engine.FrameUpdateManager;
import io.github.usbharu.coldengine.engine.InvokeQue;
import io.github.usbharu.coldengine.engine.SceneManager;
import io.github.usbharu.coldengine.engine.audio.SoundManager;

public class GameUtility {

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