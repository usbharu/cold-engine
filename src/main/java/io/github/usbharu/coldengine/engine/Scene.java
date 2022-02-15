package io.github.usbharu.coldengine.engine;

import io.github.usbharu.coldengine.engine.exception.IsNotUniqueSceneNameException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ColdEngine}のシーンです。 シーンはゲームの一つの場面であり、場面ごとにシーンを用意します。 シーンにはいくつかの{@code
 * GameController}を登録することができシーン中で切り替えることができます。 シーンは{@code SceneManager}に登録することで使用することができます。
 *
 * @author usbharu
 * @version 0.0.2
 * @since 0.0.1
 */
public abstract class Scene {

  protected final List<GameController> coldGameControllerList = new ArrayList<>();
  protected final Logger logger = LogManager.getLogger(Scene.class);
  protected int showViewIndex = 0;
  protected String uniqueSceneName;

  /**
   * Instantiates a new Scene.
   */
  public Scene() {
    init();
    sceneInit();
    logger.debug("Scene created.");
    uniqueSceneName = String.valueOf(hashCode());
  }

  protected void init() {

  }

  private void sceneInit() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.init();
    }
    logger.debug("Scene init. {}", toString());
  }

  /**
   * Sets .
   */
  void setup() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.setup();
    }
    logger.debug("Scene setup. {}", toString());

    try {
      SceneManager.getInstance().setView(coldGameControllerList.get(showViewIndex).getView());
      logger.debug("Scene setView.");
    } catch (IndexOutOfBoundsException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Update.
   */
  void update() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.update();
    }
    logger.trace("Scene update.");
  }

  /**
   * Fixed update.
   */
  void fixedUpdate() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.fixedUpdate();
    }
    logger.trace("Scene fixedUpdate.");
  }

  /**
   * Late update.
   */
  void lateUpdate() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.lateUpdate();
    }
    logger.trace("Scene lateUpdate.");
  }

  /**
   * Destroyed.
   */
  void destroyed() {
    for (IGameController coldGameController : coldGameControllerList) {
      coldGameController.destroyed();
    }
    logger.debug("Scene destroyed. :{}", toString());
  }

  /**
   * Add.
   *
   * @param gameController the game controller
   */
  public final void add(GameController gameController) {
    if (gameController == null || coldGameControllerList.contains(gameController)) {
      return;
    }
    gameController.init();
    coldGameControllerList.add(gameController);
    logger.debug("Add GameController: {}", gameController.getClass().getSimpleName());
  }

  /**
   * Remove.
   *
   * @param gameController the game controller
   */
  public void remove(GameController gameController) {
    if (gameController == null || !coldGameControllerList.contains(gameController)) {
      return;
    }
    gameController.destroyed();
    coldGameControllerList.remove(gameController);
    logger.debug("Remove GameController: {}", gameController.getClass().getSimpleName());
  }

  /**
   * Gets unique scene name.
   *
   * @return the unique scene name
   */
  public String getUniqueSceneName() {
    return uniqueSceneName;
  }

  /**
   * Sets unique scene name.
   *
   * @param sceneName the scene name
   */
  public void setUniqueSceneName(String sceneName) {
    if (sceneName == null || sceneName.isEmpty()) {
      return;
    }
    if (SceneManager.getInstance().isUniqueName(sceneName)) {
      this.uniqueSceneName = sceneName;
      logger.debug("setUniqueSceneName: {}", sceneName);
      return;
    }
    throw new IsNotUniqueSceneNameException(sceneName + " already used.");
  }

  /**
   * Sets show view index.
   *
   * @param index the index
   */
  public void setShowViewIndex(int index) {
    if (index >= 0 && index < coldGameControllerList.size()) {
      showViewIndex = index;
      logger.debug("setShowViewIndex: {}", index);
      SceneManager.getInstance().setView(coldGameControllerList.get(showViewIndex).getView());
      ColdEngine.getInstance().repaint();
    }
    throw new IllegalArgumentException(index + "");
  }

  public Scene load() {
    SceneManager.getInstance().loadSceneAtName(this.getUniqueSceneName());
    return this;
  }
}