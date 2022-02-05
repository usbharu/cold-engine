package io.github.usbharu.coldengin.engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ColdEngine}のフレーム更新処理を管理するクラス。
 * @author usbharu
 * @since 0.0.1
 * @version 0.0.1
 */
public class FrameUpdateManager {

  private static final FrameUpdateManager singleton = new FrameUpdateManager();
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
   * フレーム更新処理を開始します。
   * 無限ループです。
   */
  public void start() {

    logger.info("FrameUpdate Started");

    Thread thread = new Thread(new FrameUpdateJob());
    thread.start();

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
      try {
        Thread.sleep(sleepTime >> 16);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      newTime = System.currentTimeMillis() << 16;
      error = newTime - oldTime - sleepTime;
    }
  }

  private void update() {
    logger.trace("current FPS: {}", getCurrentFPS());
    SceneManager.getInstance().fixedUpdate();
  }

  /**
   * 現在のfpsを返します。
   * 取得に失敗した際は0を返します。
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
    this.fps = fps;
    logger.debug("FPS set to {}", fps);
  }

  /**
   * 設定できるfpsの下限を設定します。
   *
   * @param fpsLowerLimit fpsの下限
   */
  void setFpsLowerLimit(int fpsLowerLimit) {
    this.fpsLowerLimit = fpsLowerLimit;
    logger.debug("FPS lower limit set to {}", fpsLowerLimit);
  }

  /**
   * 設定できるfpsの上限を設定します。
   *
   * @param fpsUpperLimit fpsの上限
   */
  void setFpsUpperLimit(int fpsUpperLimit) {
    this.fpsUpperLimit = fpsUpperLimit;
    logger.debug("FPS upper limit set to {}", fpsUpperLimit);
  }

  private class FrameUpdateJob implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may take any action
     * whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
      logger.debug("Update Thread Start");
      while (true) {
        logger.trace("Update Loop");
        try {
          SceneManager.getInstance().update();
          SceneManager.getInstance().lateUpdate();
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}