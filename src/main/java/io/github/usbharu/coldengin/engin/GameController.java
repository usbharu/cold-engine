package io.github.usbharu.coldengin.engin;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class GameController implements IGameController, GettableView {

  protected JComponent view;

  @Override
  public synchronized void init() {
    System.out.println("init");
    view = new JPanel();
  }

  @Override
  public synchronized void setup() {

  }

  @Override
  public synchronized void update() {

  }

  @Override
  public synchronized void fixedUpdate() {

  }

  @Override
  public synchronized void lateUpdate() {

  }

  @Override
  public synchronized void destroyed() {

  }

  @Override
  public synchronized JComponent getView() {
    return view;
  }
}