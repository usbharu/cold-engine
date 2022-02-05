package io.github.usbharu.coldengine.engine;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Cold-Idle}用に開発されたゲームエンジンです。
 *
 * @author usbharu
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class ColdEngine {

  private static final ColdEngine singleton = new ColdEngine();
  private final JFrame frame = new JFrame();
  private final Logger logger = LogManager.getLogger(ColdEngine.class);
  private Dimension defaultFrameSize = new Dimension(640, 480);
  private String defaultTitle = "ColdEngine";
  private boolean defaultResize = false;

  private ColdEngine() {
  }

  /**
   * インスタンスを返します。インスタンスを取得するときは必ずこのメソッドを使用して下さい。
   *
   * @return the instance
   */
  public static ColdEngine getInstance() {
    return singleton;
  }

  /**
   * エンジンを初期化します。
   */
  public void init() {
    logger.info("ColdEngine initialized.");
    EngineConfig.getInstance().loadEngineConfig();
  }

  /**
   * デフォルトタイトルで起動します。
   */
  public void run() {
    run(defaultTitle);
  }

  /**
   * タイトルを指定して起動します。
   *
   * @param title 起動時のタイトル。
   */
  public void run(String title) {
    run(title, defaultFrameSize);
  }

  /**
   * タイトルとサイズを指定して起動します。
   *
   *
   * @param title 起動時のタイトル
   * @param size  起動時のサイズ
   */
  public void run(String title, Dimension size) {
    run(title, size, defaultResize);
  }

  /**
   * タイトルとサイズ、リサイズ可能かを指定して起動します。
   *
   * @param title 起動時のタイトル
   * @param size   起動時のサイズ
   * @param resize リサイズ可能か
   *
   */
  public void run(String title, Dimension size, boolean resize) {
    allParameterRun(title, size, resize);
  }

  /**
   * サイズを指定して起動します。
   *
   * @param size 起動時のサイズ
   */
  public void run(Dimension size) {
    run(defaultTitle, size);
  }

  /**
   * サイズとリサイズ可能かを指定して起動します
   *
   * @param size   起動時のサイズ
   * @param resize リサイズ可能か
   */
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

  /**
   * 実際に表示するコンポーネントを設定します。
   *
   * @param view 表示するコンポーネント
   */
  void setView(JComponent view) {
    logger.debug("setView");
    frame.getContentPane().removeAll();
    frame.getContentPane().add(view);
  }

  /**
   * 再描画します。
   * 必ず再描画される保証はありません。
   */
  void repaint() {
    logger.trace("repaint");
    frame.repaint();
  }

  /**
   * デフォルトのフレームサイズを指定します。
   *
   * @param defaultFrameSize デフォルトのフレームサイズ
   */
  void setDefaultFrameSize(Dimension defaultFrameSize) {
    if (defaultFrameSize != null) {
      logger.debug("setDefaultFrameSize :{}", defaultFrameSize);
      this.defaultFrameSize = defaultFrameSize;
    }
  }

  /**
   * デフォルトのタイトルを指定します。
   *
   * @param defaultTitle デフォルトのタイトル
   */
  void setDefaultTitle(String defaultTitle) {
    this.defaultTitle = defaultTitle;
    logger.debug("setDefaultTitle :{}", defaultTitle);
  }

  /**
   * デフォルトでリサイズ可能かを指定します。
   *
   * @param defaultResize リサイズ可能か
   */
  void setDefaultResize(boolean defaultResize) {
    this.defaultResize = defaultResize;
    logger.debug("setDefaultResize :{}", defaultResize);
  }

  /**
   * メインフレームを返します。
   * 通常は使用しないで下さい
   *
   * @return メインフレーム
   */
  JFrame getFrame() {
    return frame;
  }
}