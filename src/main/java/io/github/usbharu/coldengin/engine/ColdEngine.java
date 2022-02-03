package io.github.usbharu.coldengin.engine;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ColdEngine {

  private static final ColdEngine singleton = new ColdEngine();
  private final JFrame frame = new JFrame();
  private final Logger logger = LogManager.getLogger(ColdEngine.class);
  private Dimension defaultFrameSize = new Dimension(640, 480);
  private String defaultTitle = "ColdEngine";
  private boolean defaultResize = false;

  private ColdEngine() {
  }

  public static ColdEngine getInstance() {
    return singleton;
  }

  public void init() {
    logger.info("ColdEngine initialized.");
    EngineConfig.getInstance().loadEngineConfig();
  }

  public void run() {
    run(defaultTitle);
  }

  public void run(String title) {
    run(title, defaultFrameSize);
  }

  public void run(String title, Dimension size) {
    run(title, size, defaultResize);
  }

  public void run(String title, Dimension size, boolean resize) {
    allParameterRun(title, size, resize);
  }

  public void run(Dimension size) {
    run(defaultTitle, size);
  }

  public void run(Dimension size, boolean resize) {
    run(defaultTitle, size, resize);
  }

  private void allParameterRun(String title, Dimension size, boolean resize) {
    logger.info("ColdEngine run.");
    frame.setTitle(title);
    frame.getContentPane().setPreferredSize(size);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(resize);
    frame.pack();
    frame.setVisible(true);

    logger.debug("Create Frame : title={}, size={}, resize={}", title, size, resize);

    logger.info("ColdEngine run finished.");
    FrameUpdateManager.getInstance().start();
  }

  void setView(JComponent view) {
    logger.debug("setView");
    frame.getContentPane().removeAll();
    frame.getContentPane().add(view);
  }

  void repaint() {
    logger.trace("repaint");
    frame.repaint();
  }

  public void setDefaultFrameSize(Dimension defaultFrameSize) {
    if (defaultFrameSize != null) {
      logger.debug("setDefaultFrameSize :{}", defaultFrameSize);
      this.defaultFrameSize = defaultFrameSize;
    }
  }

  public void setDefaultTitle(String defaultTitle) {
    this.defaultTitle = defaultTitle;
    logger.debug("setDefaultTitle :{}", defaultTitle);
  }

  public void setDefaultResize(boolean defaultResize) {
    this.defaultResize = defaultResize;
    logger.debug("setDefaultResize :{}", defaultResize);
  }

  JFrame getFrame() {
    return frame;
  }
}