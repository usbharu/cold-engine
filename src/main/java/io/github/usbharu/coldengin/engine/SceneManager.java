package io.github.usbharu.coldengin.engine;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ColdEngine} のシーン管理を行うクラスです。
 * {@link Scene} クラスを管理します。
 */
public class SceneManager {

  private static final SceneManager singleton = new SceneManager();
  private final List<Scene> sceneList = new ArrayList<>();
  private final Scene emptyScene = new Scene();
  private int loadedSceneIndex = -1;

  private final Logger logger = LogManager.getLogger(SceneManager.class);

  private SceneManager() {

  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static SceneManager getInstance() {
    return singleton;
  }

  /**
   * Sets .
   */
  void setup() {
    getScene().setup();
  }

  /**
   * Update.
   */
  void update() {
    getScene().update();
  }

  /**
   * Fixed update.
   */
  void fixedUpdate() {
    getScene().fixedUpdate();
  }

  /**
   * Late update.
   */
  void lateUpdate() {
    getScene().lateUpdate();
  }

  /**
   * Destroyed.
   */
  void destroyed() {
    getScene().destroyed();
  }

  /**
   * Add scene.
   *
   * @param scene the scene
   */
  public void addScene(Scene scene) {
    sceneList.add(scene);
    logger.debug("Scene added: {}", scene.getUniqueSceneName());
  }

  /**
   * Remove scene.
   *
   * @param scene the scene
   */
  public void removeScene(Scene scene) {
    sceneList.remove(scene);
    logger.debug("Scene removed: {}", scene.getUniqueSceneName());
  }

  /**
   * Remove scene.
   *
   * @param uniqueSceneName the unique scene name
   */
  public void removeScene(String uniqueSceneName) {
    for (Scene scene : sceneList) {
      if (scene.getUniqueSceneName().equals(uniqueSceneName)) {
        removeScene(scene);
      }
    }
    throw new IllegalArgumentException();
  }

  /**
   * Is unique name boolean.
   *
   * @param name the name
   * @return the boolean
   */
  public boolean isUniqueName(String name) {
    for (Scene scene : sceneList) {
      if (scene.getUniqueSceneName().equals(name)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Load scene at index.
   *
   * @param index the index
   */
  public void loadSceneAtIndex(int index) {
    if (loadedSceneIndex < 0 || loadedSceneIndex >= sceneList.size()) {
      if (loadedSceneIndex != -1) {
        destroyed();
      }
      loadedSceneIndex = index;
      logger.debug("Scene loaded: {}", sceneList.get(index).getUniqueSceneName());
      setup();
    } else {
      throw new ArrayIndexOutOfBoundsException(index);
    }
  }

  /**
   * Load scene at name.
   *
   * @param name the name
   */
  public void loadSceneAtName(String name) {
    for (int i = 0, sceneListSize = sceneList.size(); i < sceneListSize; i++) {
      Scene scene = sceneList.get(i);
      if (scene.getUniqueSceneName().equals(name)) {
        destroyed();
        loadedSceneIndex = i;
        logger.debug("Scene loaded: {}", scene.getUniqueSceneName());
        setup();
      }
    }
  }

  private Scene getScene() {
    if (loadedSceneIndex >= 0 && loadedSceneIndex < sceneList.size()) {
      return sceneList.get(loadedSceneIndex);
    }
    logger.debug("Scene not found: {}", loadedSceneIndex);
    return emptyScene;
  }

  /**
   * Sets view.
   *
   * @param view the view
   */
  void setView(JComponent view) {
    ColdEngine.getInstance().setView(view);
    logger.debug("View set: {}", view.getClass().getName());
  }
}