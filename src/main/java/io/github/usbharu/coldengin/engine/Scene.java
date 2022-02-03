package io.github.usbharu.coldengin.engine;

import io.github.usbharu.coldengin.engine.exception.IsNotUniqueSceneNameException;
import java.util.ArrayList;
import java.util.List;

public class Scene {

  private final List<GameController> coldGameControllerList = new ArrayList<>();
  private int showViewIndex = 0;
  private String uniqueSceneName;

  public Scene() {
    System.out.println("init3");
    init();
    System.out.println("init4");
  }

  private void init() {
    for (IGameController coldGameController : coldGameControllerList) {
      System.out.println("init2");
      coldGameController.init();
    }
  }

  void setup() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.setup();
    }

    try {
      SceneManager.getInstance().setView(coldGameControllerList.get(showViewIndex).getView());
    } catch (IndexOutOfBoundsException exception) {
      exception.printStackTrace();
    }
  }

  void update() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.update();
    }
  }

  void fixedUpdate() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.fixedUpdate();
    }
  }

  void lateUpdate() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.lateUpdate();
    }
  }

  void destroyed() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.destroyed();
    }
  }

  @Deprecated
  public List<GameController> getColdGameControllerList() {
    return coldGameControllerList;
  }

  public void add(GameController gameController) {
    gameController.init();
    coldGameControllerList.add(gameController);
  }

  public String getUniqueSceneName() {
    return uniqueSceneName;
  }

  public void setUniqueSceneName(String sceneName) {
    if (SceneManager.getInstance().isUniqueName(sceneName)) {
      this.uniqueSceneName = sceneName;
      return;
    }
    throw new IsNotUniqueSceneNameException(sceneName + " already used.");
  }

  public void setShowViewIndex(int index) {
    if (index >= 0 && index < coldGameControllerList.size()) {
      showViewIndex = index;
      SceneManager.getInstance().setView(coldGameControllerList.get(showViewIndex).getView());
      ColdEngine.getInstance().repaint();
    }
    throw new IllegalArgumentException(index + "");
  }
}