package io.github.usbharu.coldengin.engine;

public interface IGameController {

  void init();

  void setup();

  void update();

  void fixedUpdate();

  void lateUpdate();

  void destroyed();
}