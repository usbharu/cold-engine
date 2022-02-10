package io.github.usbharu.coldengine.engine.audio;

import java.util.HashMap;
import java.util.Map;

/**
 * 音声管理用クラスです。
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.3
 */
public class SoundManager {

  private static final SoundManager singleton = new SoundManager();
  Map<String, SoundSource> soundSourceHashMap = new HashMap<>(10);
  private SoundPlayer soundPlayer = new SoundPlayer();

  private SoundManager() {

  }

  public static SoundManager getInstance() {
    return singleton;
  }

  public void addSoundSource(SoundSource soundSource, String key) {
    soundSourceHashMap.put(key, soundSource);
  }

  public void removeSoundSource(String key) {
    soundSourceHashMap.remove(key);
  }

  public void removeSoundSource(SoundSource soundSource, String key) {
    soundSourceHashMap.remove(key, soundSource);
  }

  public void playOneShot(String key) {
    soundPlayer.playOneShot(soundSourceHashMap.get(key));
  }
}