package io.github.usbharu.coldengine.engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * インゲームダイアログボックスをポップアップできるようにします。<br>
 * <p>
 * インゲームダイアログはゲームのループをストップさせることなくダイアログの表示を続けます。<br> ゲームの進行を続けるため実行時に{@code
 * ActionListener}を登録する必要があります。
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.3
 */
public class InGameDialog extends JOptionPane {

  private static final Logger logger = LogManager.getLogger(InGameDialog.class);

  private static Dimension defaultDialogSize = new Dimension(100, 80);

  /**
   * メッセージを表示するインゲームダイアログを表示します。<br>
   *
   * @param message 表示する{@code Object}
   * @param title   ダイアログのタイトルバーに表示する{@code String}
   * @param ok      {@code ok}ボタンが押されたときの{@code ActionListener}
   * @param cancel  {@code cancel}されたときの{@code ActionListener}
   */
  public static void showMessageInGameDialog(Object message, String title, ActionListener ok,
      ActionListener cancel) {
    showOptionInGameDialog(message, title, new String[] {"ok"}, 1, null, cancel, ok);
  }

  /**
   * メッセージを表示するんゲームダイアログを表示します。
   *
   * @param message 表示する{@code Object}
   * @param title   ダイアログのタイトルバーに表示する{@code String}
   */
  public static void showMessageInGameDialog(Object message, String title) {
    showMessageInGameDialog(message, title, null, null);
  }

  public static void showMessageInGameDialog(Object message) {
    showMessageInGameDialog(message,
        DialogUtilities.getString("OptionPane.messageDialogTitle",
            ColdEngine.getInstance().getFrame()));
  }

  /**
   * 指定されたアイコンを持つインゲームダイアログを表示します。ルック・アンド・フィールのデフォルトアイコンを提示するには、主に{@code messageType}パラメータを使用します。
   *
   * @param message     表示する{@code Object}
   * @param title       ダイアログのタイトルバーに表示する{@code String}
   * @param options     ユーザーが選択可能な項目を示すオブジェクト指向の配列。{@code String}以外のオブジェクトは、{@code
   *                    toString()}メソッドを使用してレンダリングされる。
   * @param messageType 表示されるメッセージの種類。{@code ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code
   *                    WARNING_MESSAGE}、{@code QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @param icon        表示する{@code Icon}イメージ
   * @param cancel      {@code cancel}されたときの{@code ActionListener}
   * @param listeners   {@code options}で指定されたオブジェクトから生成された{@code JButton}に追加される{@code
   *                    ActionListener}
   */
  public static void showOptionInGameDialog(Object message, String title, Object[] options,
      int messageType, Icon icon, ActionListener cancel, ActionListener... listeners) {
    logger.debug(
        "showOptionInGameDialog message={} title={} options={} messageType={} icon={} cancel={} listeners={}",
        message, title, options, messageType, icon, cancel, listeners);
    JPopupMenu popupMenu = createInGameDialog(title);

    JLabel messageLabel = new JLabel(message.toString(), icon == null ? getIcon(messageType) : icon,
        SwingConstants.CENTER);
    messageLabel.setPreferredSize(
        new Dimension(defaultDialogSize.width, defaultDialogSize.height - 20));
    messageLabel.setVerticalAlignment(SwingConstants.CENTER);

    popupMenu.add(messageLabel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, options.length));

    JButton[] buttons = new JButton[options.length];
    for (int i = 0; i < options.length; i++) {
      buttons[i] = new JButton(options[i].toString());
      buttons[i].addActionListener(e -> popupMenu.setVisible(false));
      if (listeners != null && listeners.length > i) {
        buttons[i].addActionListener(listeners[i]);
      }
      buttonPanel.add(buttons[i]);
    }

    popupMenu.add(buttonPanel, BorderLayout.SOUTH);
    popupMenu.addPopupMenuListener(new PopupMenuListener() {
      @Override
      public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
      }

      @Override
      public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
      }

      @Override
      public void popupMenuCanceled(PopupMenuEvent e) {
        if (cancel != null) {
          cancel.actionPerformed(null);
        }
      }
    });

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

  private static String getKeyFromMessageType(int messageType) {
    logger.debug("getKeyFromMessageType messageType={}", messageType);
    switch (messageType) {
      case JOptionPane.ERROR_MESSAGE:
        return "OptionPane.errorIcon";
      case JOptionPane.INFORMATION_MESSAGE:
        return "OptionPane.informationIcon";
      case JOptionPane.WARNING_MESSAGE:
        return "OptionPane.warningIcon";
      case JOptionPane.QUESTION_MESSAGE:
        return "OptionPane.questionIcon";
      case JOptionPane.PLAIN_MESSAGE:
        return "";
      default:
        return "OptionPane.informationIcon";
    }
  }

  private static Icon getKeyFromIcon(String key) {
    logger.debug("getKeyFromIcon key={}", key);
    try {
      return UIManager.getIcon(key);
    } catch (Exception e) {
      return null;
    }
  }

  private static Icon getIcon(int messageType) {
    return getKeyFromIcon(getKeyFromMessageType(messageType));
  }

  public static void setDefaultDialogSize(Dimension defaultDialogSize) {
    logger.debug("setDefaultDialogSize defaultDialogSize={}", defaultDialogSize);
    InGameDialog.defaultDialogSize = defaultDialogSize;
  }

  @Deprecated
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
}