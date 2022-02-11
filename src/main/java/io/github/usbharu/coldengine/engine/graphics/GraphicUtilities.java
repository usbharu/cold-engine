package io.github.usbharu.coldengine.engine.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * グラフィックに関するユーティリティクラス
 *
 * @author usbharu
 * @version 0.0.4
 * @since 0.0.4
 */
public class GraphicUtilities {

  private GraphicUtilities() {
  }

  public static BufferedImage createBufferedImage(Image image) {
    return createBufferedImage(image, BufferedImage.TYPE_3BYTE_BGR);
  }

  public static BufferedImage createBufferedImage(Image image, int imageType) {
    BufferedImage bufferedImage =
        new BufferedImage(image.getWidth(null), image.getHeight(null), imageType);

    Graphics g = bufferedImage.getGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();

    return bufferedImage;
  }
}