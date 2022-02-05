package io.github.usbharu.coldengin.engine;

/**
 * updateなどを実行できるゲームコントローラーです。
 * @author usbharu
 * @since 0.0.1
 * @version 0.0.1
 */
public interface IGameController {

  /**
   * 初期化時に呼ばれます
   */
  void init();

  /**
   * シーン初期化後に呼ばれます
   */
  void setup();

  /**
   * フレームに関係なく呼ばれます
   */
  void update();

  /**
   * フレームに合わせて呼ばれます
   */
  void fixedUpdate();

  /**
   * {@link #update()}が呼び出されたあとに呼び出されます
   */
  void lateUpdate();

  /**
   * シーン終了時や破棄時に呼ばれます。
   */
  void destroyed();
}