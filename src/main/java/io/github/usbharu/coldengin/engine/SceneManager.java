package io.github.usbharu.coldengin.engine;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SceneManager {

  private static final SceneManager singleton = new SceneManager();
  private final List<Scene> sceneList = new ArrayList<>();
  private final Scene emptyScene = new Scene();
  private int loadedSceneIndex = -1;

  private final Logger logger = LogManager.getLogger(SceneManager.class);

  private SceneManager() {

  }

  public static SceneManager getInstance() {
    return singleton;
  }

  void setup() {
    getScene().setup();
  }

  void update() {
    getScene().update();
  }

  void fixedUpdate() {
    getScene().fixedUpdate();
  }

  void lateUpdate() {
    getScene().lateUpdate();
  }

  void destroyed() {
    getScene().destroyed();
  }

  public void addScene(Scene scene) {
    sceneList.add(scene);
    logger.debug("Scene added: {}", scene.getUniqueSceneName());
  }

  public void removeScene(Scene scene) {
    sceneList.remove(scene);
    logger.debug("Scene removed: {}", scene.getUniqueSceneName());
  }

  public void removeScene(String uniqueSceneName) {
    for (Scene scene : sceneList) {
      if (scene.getUniqueSceneName().equals(uniqueSceneName)) {
        removeScene(scene);
      }
    }
    throw new IllegalArgumentException();
  }

  public boolean isUniqueName(String name) {
    for (Scene scene : sceneList) {
      if (scene.getUniqueSceneName().equals(name)) {
        return false;
      }
    }
    return true;
  }

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

  void setView(JComponent view) {
    ColdEngine.getInstance().setView(view);
    logger.debug("View set: {}", view.getClass().getName());
  }
}