package io.github.usbharu.coldengin.engin;

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

  private FrameUpdateManager() {
  }

  public static FrameUpdateManager getInstance() {
    return singleton;
  }

  public void start() {
    Thread thread = new Thread(new FrameUpdateJob());
    thread.start();

    error = 0;
    idealSleep = (1000 << 16) / fps;
    newTime = System.currentTimeMillis() << 16;

    while (true) {
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
    SceneManager.getInstance().fixedUpdate();
  }

  public float getCurrentFPS() {
    try {
      return 1000 / (float) (sleepTime >> 16);
    } catch (ArithmeticException ignored) {
    }
    return 0;
  }

  public int getFPS() {
    return fps;
  }

  public void setFPS(int fps) {
    this.fps = fps;
  }

  public void setFpsLowerLimit(int fpsLowerLimit) {
    this.fpsLowerLimit = fpsLowerLimit;
  }

  public void setFpsUpperLimit(int fpsUpperLimit) {
    this.fpsUpperLimit = fpsUpperLimit;
  }

  private static class FrameUpdateJob implements Runnable {

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
      while (true) {
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