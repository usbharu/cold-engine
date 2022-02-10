package io.github.usbharu.coldengine.engine;

import io.github.usbharu.coldengine.engine.audio.SoundPlayer;
import io.github.usbharu.coldengine.engine.audio.SoundSource;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.testng.annotations.Test;

public class SoundPlayerTest {

  @Test
  public void testPlayOneShot()
      throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    SoundPlayer soundPlayer = new SoundPlayer();
    SoundSource soundSource = new SoundSource(getClass().getResource("/sample.wav").getPath());
    try {
      soundPlayer.play(soundSource);
    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}