package io.github.usbharu.coldengine.engine;

public class InvokeQue {

  public long issueTime;
  public int waitTime;
  public boolean wasRun = false;
  Runnable runnable;

  public InvokeQue(Runnable runnable, int waitTime) {
    issueTime = System.currentTimeMillis();
    this.runnable = runnable;
    this.waitTime = waitTime;
  }

  public void run() {
    runnable.run();
    wasRun = true;
  }
}