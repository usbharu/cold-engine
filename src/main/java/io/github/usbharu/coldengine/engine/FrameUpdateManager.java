package io.github.usbharu.coldengine.engine;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ColdEngine}のフレーム更新処理を管理するクラス。
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.1
 */
public class FrameUpdateManager {

  private static final FrameUpdateManager singleton = new FrameUpdateManager();
  private final List<InvokeQue> invokeQues = new ArrayList<>();
  private long error = 0;
  private int fps = 10;
  private int fpsLowerLimit = 1;
  private int fpsUpperLimit = 200;
  private long idealSleep = (1000 << 16) / fps;
  private long oldTime;
  private long newTime;
  private long sleepTime;
  private Logger logger = LogManager.getLogger(FrameUpdateManager.class);

  private FrameUpdateManager() {
  }

  /**
   * インスタンスを返します。インスタンスを取得するときはこのメソッドを使用してください。
   *
   * @return the instance
   */
  public static FrameUpdateManager getInstance() {
    return singleton;
  }

  /**
   * フレーム更新処理を開始します。 無限ループです。
   */
  public void start() {

    logger.info("FrameUpdate Started");

    error = 0;
    idealSleep = (1000 << 16) / fps;
    newTime = System.currentTimeMillis() << 16;

    while (true) {
      logger.trace("FixedUpdate loop");
      oldTime = newTime;
      update();
      ColdEngine.getInstance().repaint();
      newTime = System.currentTimeMillis() << 16;
      sleepTime = idealSleep - (newTime - oldTime) - error;
      if (sleepTime < 0x20000) {
        sleepTime = 0x20000;
      }
      oldTime = newTime;
      sleep(sleepTime >> 16);

      newTime = System.currentTimeMillis() << 16;
      error = newTime - oldTime - sleepTime;
    }
  }

  private void update() {
    logger.trace("current FPS: {}", getCurrentFPS());
    SceneManager.getInstance().fixedUpdate();
  }

  /**
   * 現在のfpsを返します。 取得に失敗した際は0を返します。
   *
   * @return the current fps
   */
  public float getCurrentFPS() {
    try {
      return 1000 / (float) (sleepTime >> 16);
    } catch (ArithmeticException ignored) {
    }
    return 0;
  }

  /**
   * 設定されている目標fpsを返します。
   *
   * @return 目標fps
   */
  public int getFPS() {
    return fps;
  }

  /**
   * 目標fpsを設定します。
   *
   * @param fps 目標fps
   */
  public void setFPS(int fps) {
    if (fps < fpsLowerLimit) {
      this.fps = fpsLowerLimit;
    } else if (fps > fpsUpperLimit) {
      this.fps = fpsUpperLimit;
    } else {
      this.fps = fps;
    }
    logger.debug("FPS set to {}", this.fps);
  }

  /**
   * 設定できるfpsの下限を設定します。
   *
   * @param fpsLowerLimit fpsの下限
   */
  void setFpsLowerLimit(int fpsLowerLimit) {
    if (fpsLowerLimit > fpsUpperLimit) {
      this.fpsLowerLimit = fpsUpperLimit;
    } else {
      this.fpsLowerLimit = fpsLowerLimit;
    }
    logger.debug("FPS lower limit set to {}", this.fpsLowerLimit);
  }

  /**
   * 設定できるfpsの上限を設定します。
   *
   * @param fpsUpperLimit fpsの上限
   */
  void setFpsUpperLimit(int fpsUpperLimit) {
    if (fpsUpperLimit < fpsLowerLimit) {
      this.fpsUpperLimit = fpsLowerLimit;
    } else {
      this.fpsUpperLimit = fpsUpperLimit;
    }
    logger.debug("FPS upper limit set to {}", this.fpsUpperLimit);
  }

  private void sleep(long sleepTime) {
    long newTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - newTime <= sleepTime) {
      logger.trace("Update Loop");
      try {
        SceneManager.getInstance().update();
        for (int i = 0, invokeQuesSize = invokeQues.size(); i < invokeQuesSize; i++) {
          InvokeQue invokeQue = invokeQues.get(i);
          if (!invokeQue.wasRun) {
            if (invokeQue.issueTime + invokeQue.waitTime * 1000L <= System.currentTimeMillis()) {
              logger.debug("invoke Que run : {}", invokeQue);
              invokeQue.run();
              invokeQues.remove(invokeQue);
              i--;
              invokeQuesSize--;
            }
          }
        }
        SceneManager.getInstance().lateUpdate();
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public void addInvokeQue(InvokeQue invokeQue) {
    invokeQues.add(invokeQue);
  }

  void removeInvokeQue(InvokeQue invokeQue) {
    invokeQues.remove(invokeQue);
  }
}