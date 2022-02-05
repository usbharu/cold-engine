package io.github.usbharu.coldengine.engine;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * {@code ColdEngine}の{@code Scene}に登録される、シーン開始時に読み込まれるクラスです。 ゲームの処理の大部分を担います。
 */
public abstract class GameController implements IGameController, GettableView {

  protected JComponent view;

  /**
   * 初期化時に呼ばれます
   */
  @Override
  public void init() {
    view = new JPanel();
  }

  /**
   * シーン初期化後に呼ばれます
   */
  @Override
  public void setup() {

  }

  /**
   * フレームに関係なく呼ばれます
   */
  @Override
  public void update() {

  }

  /**
   * フレームに合わせて呼ばれます
   */
  @Override
  public void fixedUpdate() {

  }

  /**
   * {@link #update()}が呼び出されたあとに呼び出されます
   */
  @Override
  public void lateUpdate() {

  }

  /**
   * シーン終了時や破棄時に呼ばれます。
   */
  @Override
  public void destroyed() {

  }


  @Override
  public synchronized JComponent getView() {
    return view;
  }
}