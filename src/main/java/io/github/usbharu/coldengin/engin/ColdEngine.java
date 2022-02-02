package io.github.usbharu.coldengin.engin;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ColdEngine {

  private static final ColdEngine singleton = new ColdEngine();
  private final JFrame frame = new JFrame();
  private Dimension defaultFrameSize = new Dimension(640, 480);
  private String defaultTitle = "ColdEngine";
  private boolean defaultResize = false;

  private ColdEngine() {
  }

  public static ColdEngine getInstance() {
    return singleton;
  }

  public void init() {
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
    frame.setTitle(title);
    frame.getContentPane().setPreferredSize(size);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(resize);
    frame.pack();
    frame.setVisible(true);

    FrameUpdateManager.getInstance().start();
  }

  void setView(JComponent view) {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(view);
  }

  void repaint() {
    frame.repaint();
  }

  public void setDefaultFrameSize(Dimension defaultFrameSize) {
    if (defaultFrameSize != null) {
      this.defaultFrameSize = defaultFrameSize;
    }
  }

  public void setDefaultTitle(String defaultTitle) {
    this.defaultTitle = defaultTitle;
  }

  public void setDefaultResize(boolean defaultResize) {
    this.defaultResize = defaultResize;
  }

  JFrame getFrame() {
    return frame;
  }
}