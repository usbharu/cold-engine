package io.github.usbharu.coldengine.engine;

import io.github.usbharu.coldengine.utilities.GameUtilities;

/**
 * {@link GameUtilities#invoke(Runnable, int)}で発行された{@code Invoke}のキュー
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.3
 */
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