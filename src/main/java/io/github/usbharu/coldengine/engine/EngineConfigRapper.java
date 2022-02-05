package io.github.usbharu.coldengine.engine;

import java.awt.Dimension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code EngineConfigRapper}のラッパークラスです。今後APIが変更された時に変更が発生するクラスを少なくするためのクラスです。
 *
 * @author usbharu
 * @version 0.0.2
 * @since 0.0.1
 */
public class EngineConfigRapper {

  /**
   * The Default window size x.
   */
  static int defaultWindowSizeX = 0;
  /**
   * The Default window size y.
   */
  static int defaultWindowSizeY = 0;
  /**
   * The Default dialog size x.
   */
  static int defaultDialogSizeX = 0;
  /**
   * The Default dialog size y.
   */
  static int defaultDialogSizeY = 0;

  private static final Logger logger = LogManager.getLogger(EngineConfigRapper.class);


  /**
   * Sets fps lower limit.
   *
   * @param fpsLowerLimit the fps lower limit
   */
  public static void setFPSLowerLimit(int fpsLowerLimit) {
    FrameUpdateManager.getInstance().setFpsLowerLimit(fpsLowerLimit);
  }

  /**
   * Sets fps upper limit.
   *
   * @param fpsUpperLimit the fps upper limit
   */
  public static void setFPSUpperLimit(int fpsUpperLimit) {
    FrameUpdateManager.getInstance().setFpsUpperLimit(fpsUpperLimit);
  }

  /**
   * Sets fps.
   *
   * @param fps the fps
   */
  public static void setFPS(int fps) {
    FrameUpdateManager.getInstance().setFPS(fps);
  }

  /**
   * Sets default title.
   *
   * @param title the title
   */
  public static void setDefaultTitle(String title) {
    if (title == null) {
      logger.warn("setDefaultTitle : title is null");
    }
    if (ColdEngine.getInstance() == null) {
      logger.warn("ColdEngine is null!!");
      return;
    }
    ColdEngine.getInstance().setDefaultTitle(title);
  }

  /**
   * Sets default dialog size x.
   *
   * @param defaultDialogSizeX the default dialog size x
   */
  public static void setDefaultDialogSizeX(int defaultDialogSizeX) {
    EngineConfigRapper.defaultDialogSizeX = defaultDialogSizeX;
  }

  /**
   * Sets default dialog size y.
   *
   * @param defaultDialogSizeY the default dialog size y
   */
  public static void setDefaultDialogSizeY(int defaultDialogSizeY) {
    EngineConfigRapper.defaultDialogSizeY = defaultDialogSizeY;
  }

  /**
   * Sets default dialog size.
   */
  public static void setDefaultDialogSize() {
    if (defaultDialogSizeX != 0 && defaultDialogSizeY != 0) {
      setDefaultDialogSize(new Dimension(defaultDialogSizeX, defaultDialogSizeY));
    }
  }

  /**
   * Sets default dialog size.
   *
   * @param dimension the dimension
   */
  public static void setDefaultDialogSize(Dimension dimension) {
    Dialog.setDefaultDialogSize(dimension);
  }

  /**
   * Sets default window size.
   *
   * @param size the size
   */
  public static void setDefaultWindowSize(Dimension size) {
    ColdEngine.getInstance().setDefaultFrameSize(size);
  }

  /**
   * Sets default window size x.
   *
   * @param defaultWindowSizeX the default window size x
   */
  public static void setDefaultWindowSizeX(int defaultWindowSizeX) {
    EngineConfigRapper.defaultWindowSizeX = defaultWindowSizeX;
  }

  /**
   * Sets default window size y.
   *
   * @param defaultWindowSizeY the default window size y
   */
  public static void setDefaultWindowSizeY(int defaultWindowSizeY) {
    EngineConfigRapper.defaultWindowSizeY = defaultWindowSizeY;
  }

  /**
   * Sets default window size.
   */
  public static void setDefaultWindowSize() {
    if (defaultWindowSizeY != 0 && defaultWindowSizeX != 0) {
      setDefaultWindowSize(new Dimension(defaultWindowSizeX, defaultWindowSizeY));
      logger.debug("setDefaultWindowSize :{} , {}", defaultWindowSizeX, defaultWindowSizeY);
    }
  }

  /**
   * Sets default window resize.
   *
   * @param resize the resize
   */
  public static void setDefaultWindowResize(boolean resize) {
    ColdEngine.getInstance().setDefaultResize(resize);
  }
}