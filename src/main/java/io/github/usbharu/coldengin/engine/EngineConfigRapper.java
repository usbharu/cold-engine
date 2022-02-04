package io.github.usbharu.coldengin.engine;

import java.awt.Dimension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EngineConfigRapper {

  static int defaultWindowSizeX = 0;
  static int defaultWindowSizeY = 0;
  static int defaultDialogSizeX = 0;
  static int defaultDialogSizeY = 0;

  private static final Logger logger = LogManager.getLogger(EngineConfigRapper.class);

  public static void setFPSLowerLimit(int fpsLowerLimit) {
    FrameUpdateManager.getInstance().setFpsLowerLimit(fpsLowerLimit);
  }

  public static void setFPSUpperLimit(int fpsUpperLimit) {
    FrameUpdateManager.getInstance().setFpsUpperLimit(fpsUpperLimit);
  }

  public static void setFPS(int fps) {
    FrameUpdateManager.getInstance().setFPS(fps);
  }

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

  public static void setDefaultDialogSizeX(int defaultDialogSizeX) {
    EngineConfigRapper.defaultDialogSizeX = defaultDialogSizeX;
  }

  public static void setDefaultDialogSizeY(int defaultDialogSizeY) {
    EngineConfigRapper.defaultDialogSizeY = defaultDialogSizeY;
  }

  public static void setDefaultDialogSize() {
    if (defaultDialogSizeX != 0 && defaultDialogSizeY != 0) {
      setDefaultDialogSize(new Dimension(defaultDialogSizeX, defaultDialogSizeY));
    }
  }

  private static void setDefaultDialogSize(Dimension dimension) {
    Dialog.setDefaultDialogSize(dimension);
  }

  public static void setDefaultWindowSize(Dimension size) {
    ColdEngine.getInstance().setDefaultFrameSize(size);
  }

  public static void setDefaultWindowSizeX(int defaultWindowSizeX) {
    EngineConfigRapper.defaultWindowSizeX = defaultWindowSizeX;
  }

  public static void setDefaultWindowSizeY(int defaultWindowSizeY) {
    EngineConfigRapper.defaultWindowSizeY = defaultWindowSizeY;
  }

  public static void setDefaultWindowSize() {
    if (defaultWindowSizeY != 0 && defaultWindowSizeX != 0) {
      setDefaultWindowSize(new Dimension(defaultWindowSizeX, defaultWindowSizeY));
      logger.debug("setDefaultWindowSize :{} , {}", defaultWindowSizeX, defaultWindowSizeY);
    }
  }

  public static void setDefaultWindowResize(boolean resize) {
    ColdEngine.getInstance().setDefaultResize(resize);
  }
}