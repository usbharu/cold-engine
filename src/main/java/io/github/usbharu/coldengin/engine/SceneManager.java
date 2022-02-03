package io.github.usbharu.coldengin.engine;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

public class SceneManager {

  private static final SceneManager singleton = new SceneManager();
  private final List<Scene> sceneList = new ArrayList<>();
  private final Scene emptyScene = new Scene();
  private int loadedSceneIndex = -1;

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
  }

  public void removeScene(Scene scene) {
    sceneList.remove(scene);
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
        setup();
      }
    }
  }

  private Scene getScene() {
    if (loadedSceneIndex >= 0 && loadedSceneIndex < sceneList.size()) {
      return sceneList.get(loadedSceneIndex);
    }
    return emptyScene;
  }

  void setView(JComponent view) {
    ColdEngine.getInstance().setView(view);
  }
}