package io.github.usbharu.coldengin.engin;

public interface IGameController {

  void init();

  void setup();

  void update();

  void fixedUpdate();

  void lateUpdate();

  void destroyed();
}