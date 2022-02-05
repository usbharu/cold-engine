package io.github.usbharu.coldengine.engine;

import javax.swing.JComponent;

/**
 * ビューを取得できるインターフェースです。
 *
 * @author usbharu
 * @version 0.0.2
 * @since 0.0.1
 */
public interface GettableView {

  /**
   * 設定されているビューを返します。
   * @return ビュー
   */
  JComponent getView();
}