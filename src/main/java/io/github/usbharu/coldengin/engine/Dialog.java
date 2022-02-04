package io.github.usbharu.coldengin.engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dialog extends JOptionPane {

  private final static Logger logger = LogManager.getLogger(Dialog.class);
  private static Dimension defaultDialogSize = new Dimension(100, 80);

  public static int showConfirmDialog(Object message) {
    return showConfirmDialog(message, UIManager.getString("OptionPane.titleText"),
        JOptionPane.YES_NO_CANCEL_OPTION);
  }

  public static int showConfirmDialog(Object message, String title, int optionType) {
    return showConfirmDialog(message, title, optionType, JOptionPane.QUESTION_MESSAGE);
  }

  public static int showConfirmDialog(Object message, String title, int optionType,
      int messageType) {
    return showConfirmDialog(message, title, optionType, messageType, null);
  }

  public static int showConfirmDialog(Object message, String title, int optionStyle,
      int messageType,
      Icon icon) {
    logger.debug("showConfirmDialog message={} title={} optionStyle={} messageType={} icon={}",message, title, optionStyle, messageType, icon);
    return JOptionPane.showConfirmDialog(ColdEngine.getInstance().getFrame(), message, title,
        optionStyle, messageType, icon);
  }

  public static String showInputDialog(Object message) {
    return showInputDialog(message,
        getString("Option.inputDialogTitle", ColdEngine.getInstance().getFrame()),
        JOptionPane.QUESTION_MESSAGE);
  }

  public static String showInputDialog(Object message, String title, int messageType) {
    return ((String) showInputDialog(message, title, messageType, null, null, null));
  }

  public static String showInputDialog(Object message, Object initialSelectionValue) {
    return (String) showInputDialog(message, getString("OptionPane.inputDialogTitle",
            ColdEngine.getInstance().getFrame()), JOptionPane.QUESTION_MESSAGE, null, null,
        initialSelectionValue);
  }

  public static Object showInputDialog(Object message, String title, int messageType, Icon icon,
      Object[] selectionValues, Object initialSelectionValue) {
    logger.debug("showInputDialog message={} title={} messageType={} icon={} selectionValues={} initialSelectionValue={}",message, title, messageType, icon, selectionValues, initialSelectionValue);
    return JOptionPane.showInputDialog(ColdEngine.getInstance().getFrame(), message, title,
        messageType, icon, selectionValues, initialSelectionValue);
  }

  public static String showInputDialogNoParent(Object message) {
    return showInputDialog(message);
  }

  public static String showInputDialogNoParent(Object message, Object initialSelectionValue) {
    return showInputDialog(message, initialSelectionValue);
  }

  public static void showMessageDialog(Object message) {
    showMessageDialog(message, getString("OptionPane.messageDialogTitle",
        ColdEngine.getInstance().getFrame()), JOptionPane.INFORMATION_MESSAGE);
  }

  public static void showMessageDialog(Object message, String title, int messageType) {
    showMessageDialog(message, title, messageType, null);
  }

  public static void showMessageDialog(Object message, String title, int messageType, Icon icon) {
    logger.debug("showMessageDialog message={} title={} messageType={} icon={}",message, title, messageType, icon);
    JOptionPane.showMessageDialog(ColdEngine.getInstance().getFrame(), message, title, messageType,
        icon);
  }

  public static int showOptionDialog(Object message, String title, int optionType, int messageType,
      Icon icon, Object[] options, Object initialValue) {
    logger.debug("showOptionDialog message={} title={} optionType={} messageType={} icon={} options={} initialValue={}",message, title, optionType, messageType, icon, options, initialValue);
    return JOptionPane.showOptionDialog(ColdEngine.getInstance().getFrame(), message, title,
        optionType,
        messageType, icon, options, initialValue);
  }

  private static String getString(String key, Component c) {
    Method method = null;
    try {
      method = UIManager.class.getDeclaredMethod("getString", Object.class, Component.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    if (method != null) {
      method.setAccessible(true);
      try {
        return String.valueOf(method.invoke(null, key, c));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static void setDefaultDialogSize(Dimension defaultDialogSize) {
    logger.debug("setDefaultDialogSize defaultDialogSize={}",defaultDialogSize);
    Dialog.defaultDialogSize = defaultDialogSize;
  }

  public static void showMessageInGameDialog(Object message, String title) {
    logger.debug("showMessageInGameDialog message={} title={}",message, title);
    JPopupMenu popupMenu = createInGameDialog(title);

    JLabel messageLabel = new JLabel(message.toString());
    messageLabel.setPreferredSize(
        new Dimension(defaultDialogSize.width, defaultDialogSize.height - 20));
    messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    messageLabel.setVerticalAlignment(SwingConstants.CENTER);

    popupMenu.add(messageLabel, BorderLayout.CENTER);

    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> popupMenu.setVisible(false));
    popupMenu.add(okButton, BorderLayout.SOUTH);

    JFrame frame = ColdEngine.getInstance().getFrame();
    Dimension d = popupMenu.getPreferredSize();
    popupMenu.show(frame, (frame.getWidth() - d.width) / 2, (frame.getHeight() - d.height) / 2);
  }

  private static JPopupMenu createInGameDialog(String title) {
    JPopupMenu popupMenu = new JPopupMenu();
    popupMenu.setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel(title);

    titleLabel.setPreferredSize(new Dimension(defaultDialogSize.width, 20));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

    popupMenu.add(titleLabel, BorderLayout.NORTH);
    return popupMenu;
  }
}