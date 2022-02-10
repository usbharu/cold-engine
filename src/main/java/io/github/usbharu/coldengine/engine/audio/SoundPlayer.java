package io.github.usbharu.coldengine.engine.audio;

import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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