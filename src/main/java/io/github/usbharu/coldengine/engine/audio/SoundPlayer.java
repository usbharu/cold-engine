package io.github.usbharu.coldengine.engine.audio;

import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 音声再生用クラスです。 実際に使用する際は{@link SoundManager}に登録して再生することを推奨します。
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.3
 */
public class SoundPlayer {

  SoundSource cacheSource;
  String oldPath;

  public void playOneShot(String path)
      throws IOException, UnsupportedAudioFileException, LineUnavailableException {
    if (oldPath == null || !oldPath.equals(path)) {
      cacheSource = new SoundSource(path);
      oldPath = path;
      cacheSource.getClip().addLineListener(event -> {
        if (event != null && event.getType() == LineEvent.Type.STOP) {
          cacheSource.getClip().close();
        }
      });
    }
    cacheSource.getClip().setFramePosition(0);
    cacheSource.getClip().start();
  }

  public void playOneShot(SoundSource soundSource) {
    soundSource.getClip().setFramePosition(0);
    soundSource.getClip().start();
  }

  public void play(SoundSource soundSource)
      throws IOException, UnsupportedAudioFileException, LineUnavailableException {
    play(soundSource.getClip());
  }

  public void play(Clip clip) {
    clip.start();
    clip.addLineListener(event -> {
      if (event != null && event.getType() == LineEvent.Type.STOP) {
        clip.close();
      }
    });
  }
}