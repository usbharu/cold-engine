package io.github.usbharu.coldengine.engine.audio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 音声を保持するクラスです。
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.3
 */
public class SoundSource {

  private Clip clip;

  public SoundSource() {
  }

  public SoundSource(Clip clip) {
    this.clip = clip;
  }

  public SoundSource(String filepath)
      throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
    clip = AudioSystem.getClip();
    clip.open(audioInputStream);
  }

  public SoundSource(File file)
      throws LineUnavailableException, UnsupportedAudioFileException, IOException {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
    clip = AudioSystem.getClip();
    clip.open(audioInputStream);
  }

  public SoundSource(InputStream inputStream)
      throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
    clip = AudioSystem.getClip();
    clip.open(audioInputStream);
  }

  public Clip getClip() {
    return clip;
  }

}