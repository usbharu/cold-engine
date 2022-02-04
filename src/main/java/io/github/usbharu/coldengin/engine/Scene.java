package io.github.usbharu.coldengin.engine;

import io.github.usbharu.coldengin.engine.exception.IsNotUniqueSceneNameException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Scene {

  private final List<GameController> coldGameControllerList = new ArrayList<>();
  private int showViewIndex = 0;
  private String uniqueSceneName;

  private final Logger logger = LogManager.getLogger(Scene.class);

  public Scene() {
    init();
    logger.debug("Scene created.");
  }

  private void init() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.init();
    }
    logger.debug("Scene init.");
  }

  void setup() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.setup();
    }
    logger.debug("Scene setup.");

    try {
      SceneManager.getInstance().setView(coldGameControllerList.get(showViewIndex).getView());
      logger.debug("Scene setView.");
    } catch (IndexOutOfBoundsException exception) {
      exception.printStackTrace();
    }
  }

  void update() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.update();
    }
    logger.debug("Scene update.");
  }

  void fixedUpdate() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.fixedUpdate();
    }
    logger.debug("Scene fixedUpdate.");
  }

  void lateUpdate() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.lateUpdate();
    }
    logger.debug("Scene lateUpdate.");
  }

  void destroyed() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.destroyed();
    }
    logger.debug("Scene destroyed.");
  }

  public void add(GameController gameController) {
    gameController.init();
    coldGameControllerList.add(gameController);
    logger.debug("Add GameController: {}", gameController.getClass().getSimpleName());
  }

  public void remove(GameController gameController) {
    gameController.destroyed();
    coldGameControllerList.remove(gameController);
    logger.debug("Remove GameController: {}", gameController.getClass().getSimpleName());
  }

  public String getUniqueSceneName() {
    return uniqueSceneName;
  }

  public void setUniqueSceneName(String sceneName) {
    if (SceneManager.getInstance().isUniqueName(sceneName)) {
      this.uniqueSceneName = sceneName;
      logger.debug("setUniqueSceneName: {}", sceneName);
      return;
    }
    throw new IsNotUniqueSceneNameException(sceneName + " already used.");
  }

  public void setShowViewIndex(int index) {
    if (index >= 0 && index < coldGameControllerList.size()) {
      showViewIndex = index;
      logger.debug("setShowViewIndex: {}", index);
      SceneManager.getInstance().setView(coldGameControllerList.get(showViewIndex).getView());
      ColdEngine.getInstance().repaint();
    }
    throw new IllegalArgumentException(index + "");
  }
}