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
 * 標準のダイアログボックスを用意にポップアップできるようにします。<br>
 * <p>
 * 基本的には{@link JOptionPane}と使用方法は同じですが、親を指定することができなくなっています。<br> なお、{@link
 * JOptionPane}を継承しているため、{@link JOptionPane}のメソッドを使用することができますが、推奨されません。<br>
 *
 *
 * @author usbharu
 * @version 0.0.1
 * @since 0.0.1
 */
public class Dialog extends JOptionPane {

  private static final Logger logger = LogManager.getLogger(Dialog.class);
  private static Dimension defaultDialogSize = new Dimension(100, 80);

  /**
   * オプションが「はい」、「いいえ」、「取り消し」を含む「オプションの選択」というタイトルのダイアログを表示します。
   *
   * @param message 表示する{@code Object}
   * @return ユーザが選択したオプションを示す整数。
   */
  public static int showConfirmDialog(Object message) {
    return showConfirmDialog(message, UIManager.getString("OptionPane.titleText"),
        JOptionPane.YES_NO_CANCEL_OPTION);
  }

  /**
   * 選択肢の数が{@code optionType}パラメータによって決定されるダイアログを表示します。
   *
   * @param message    表示する{@code Object}
   * @param title      ダイアログのタイトル文字列
   * @param optionType ダイアログで選択可能のオプションを示す{@code int}値。{@code YES_NO_OPTION}、{@code
   *                   YES_NO_CANCEL_OPTION}、{@code OK_CANCEL_OPTION}のいずれか。
   * @return ユーザーが選択したオプションを示す{@code int}値。
   */
  public static int showConfirmDialog(Object message, String title, int optionType) {
    return showConfirmDialog(message, title, optionType, JOptionPane.QUESTION_MESSAGE);
  }

  /**
   * 選択肢の数が{@code optionType}パラメータによって決定され、表示するアイコンが{@code messageType}パラメータによって決定されるダイアログを表示します。
   *
   * @param message     表示する{@code Object}
   * @param title       ダイアログのタイトル文字列
   * @param optionType  ダイアログで選択可能のオプションを示す{@code int}値。{@code YES_NO_OPTION}、{@code
   *                    YES_NO_CANCEL_OPTION}、{@code OK_CANCEL_OPTION}のいずれか。
   * @param messageType 主にプラグイン可能なルック・アンド・フィールのアイコンを指定するために使用される、メッセージの種類を示す整数。{@code
   *                    ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code WARNING_MESSAGE}、{@code
   *                    QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @return ユーザが選択したオプションを示す整数
   */
  public static int showConfirmDialog(Object message, String title, int optionType,
      int messageType) {
    return showConfirmDialog(message, title, optionType, messageType, null);
  }

  /**
   * 選択肢の数が{@code optionType}パラメータによって決定される、指定されたアイコンを持つダイアログを表示します。ルック・アンド・フィールのデフォルト・アイコンを提示するには、主に{@code
   * messageType}パラメータを使用します。
   *
   * @param message     表示する{@code Object}
   * @param title       ダイアログのタイトル文字列
   * @param optionStyle ダイアログで選択可能のオプションを示す{@code int}値。{@code YES_NO_OPTION}ダイアログで選択可能のオプションを示す{@code int}値。{@code YES_NO_OPTION}、{@code YES_NO_CANCEL_OPTION}、{@code OK_CANCEL_OPTION}のいずれか。
   * @param messageType 主にプラグイン可能なルック・アンド・フィールのアイコンを指定するために使用される、メッセージの種類を示す整数。{@code ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @param icon        ダイアログに表示するアイコン
   * @return ユーザーが選択したオプションを示す {@code int}値
   */
  public static int showConfirmDialog(Object message, String title, int optionStyle,
      int messageType,
      Icon icon) {
    logger.debug("showConfirmDialog message={} title={} optionStyle={} messageType={} icon={}",
        message, title, optionStyle, messageType, icon);
    return JOptionPane.showConfirmDialog(ColdEngine.getInstance().getFrame(), message, title,
        optionStyle, messageType, icon);
  }

  /**
   * ユーザからの入力を要求するクエスチョンメッセージダイアログを表示します。ダイアログは、{@code Component}のフレームの上に表示され、通常は{@code Component}の下に配置されます。
   *
   * @param message 表示する{@code Object}
   * @return ユーザー入力。{@code null}の場合はユーザーが入力を取り消したことを意味する。
   */
  public static String showInputDialog(Object message) {
    return showInputDialog(message,
        getString("Option.inputDialogTitle", ColdEngine.getInstance().getFrame()),
        JOptionPane.QUESTION_MESSAGE);
  }

  /**
   * ユーザーが選択したオプションを示すクエスチョンメッセージダイアログを表示します。このダイアログのタイトルは{@code title}で、メッセージ型{@code messageType}です。
   *
   * @param message     表示する{@code Object}
   * @param title       ダイアログのタイトルバーに表示する{@code String}
   * @param messageType 表示されるメッセージの種類。{@code ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code WARNING_MESSAGE}、{@code QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @return ユーザー入力。{@code null}の場合はユーザーが入力を取り消したことを意味する。
   */
  public static String showInputDialog(Object message, String title, int messageType) {
    return ((String) showInputDialog(message, title, messageType, null, null, null));
  }

  /**
   * Show input dialog string.
   *
   * @param message               表示する{@code Object}
   * @param initialSelectionValue 入力フィールドの初期化に使用する値
   * @return ユーザー入力。{@code null}の場合はユーザーが入力を取り消したことを意味する。
   */
  public static String showInputDialog(Object message, Object initialSelectionValue) {
    return (String) showInputDialog(message, getString("OptionPane.inputDialogTitle",
            ColdEngine.getInstance().getFrame()), JOptionPane.QUESTION_MESSAGE, null, null,
        initialSelectionValue);
  }

  /**
   * 初期選択、指定可能な選択項目、及びそれ以外のすべてのオプションの指定が可能なブロッキングダイアログでユーザーに入力を求めます。ユーザーの選択項目は{@code selectionValues}で指定します。{@code null}の場合、ユーザーは任意の値を入力できます。入力の手段は通常は{@code JTextField}です。{@code initialSelectionValue}はユーザーに示される初期値です。{@code selectionValues}をどのように最適に提示するかは{@code UI}によって異なりますが、通常は{@code JComboBox}、{@code JList}または{@code JTextField}を使用します。
   *
   * @param message               表示する{@code Object}
   * @param title                 ダイアログのタイトルバーに表示する{@code String}
   * @param messageType           表示されるメッセージの種類。{@code ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code WARNING_MESSAGE}、{@code QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @param icon                  表示する{@code Icon}イメージ
   * @param selectionValues       選択可能な項目を示す{@code Object}の配列
   * @param initialSelectionValue 入力フィールドの初期化に使用する値
   * @return ユーザー入力。{@code null}の場合はユーザーが入力を取り消したことを意味する
   */
  public static Object showInputDialog(Object message, String title, int messageType, Icon icon,
      Object[] selectionValues, Object initialSelectionValue) {
    logger.debug(
        "showInputDialog message={} title={} messageType={} icon={} selectionValues={} initialSelectionValue={}",
        message, title, messageType, icon, selectionValues, initialSelectionValue);
    return JOptionPane.showInputDialog(ColdEngine.getInstance().getFrame(), message, title,
        messageType, icon, selectionValues, initialSelectionValue);
  }

  /**
   * Show input dialog no parent string.
   *
   * @param message the message
   * @return the string
   */
  public static String showInputDialogNoParent(Object message) {
    return showInputDialog(message);
  }

  /**
   * Show input dialog no parent string.
   *
   * @param message               the message
   * @param initialSelectionValue the initial selection value
   * @return the string
   */
  public static String showInputDialogNoParent(Object message, Object initialSelectionValue) {
    return showInputDialog(message, initialSelectionValue);
  }

  /**
   * Show message dialog.
   *
   * @param message the message
   */
  public static void showMessageDialog(Object message) {
    showMessageDialog(message, getString("OptionPane.messageDialogTitle",
        ColdEngine.getInstance().getFrame()), JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Show message dialog.
   *
   * @param message     the message
   * @param title       the title
   * @param messageType the message type
   */
  public static void showMessageDialog(Object message, String title, int messageType) {
    showMessageDialog(message, title, messageType, null);
  }

  /**
   * Show message dialog.
   *
   * @param message     the message
   * @param title       the title
   * @param messageType the message type
   * @param icon        the icon
   */
  public static void showMessageDialog(Object message, String title, int messageType, Icon icon) {
    logger.debug("showMessageDialog message={} title={} messageType={} icon={}", message, title,
        messageType, icon);
    JOptionPane.showMessageDialog(ColdEngine.getInstance().getFrame(), message, title, messageType,
        icon);
  }

  /**
   * 最初の選択が{@code initialValue}によって決定され、選択肢の数が{@code optionType}によって決定される、指定されたアイコンを持つダイアログを表示します。
   * {@code optionType}が{@code YES_NO_OPTION}または{@code YES_NO_CANCEL_OPTION}で{@code options}パラメータが{@code null}の場合、オプションはルック・アンド・フィールによって提供されます。
   * ルック・アンド・フィールのデフォルトアイコンを提示するには、主に{@code messageType}パラメータを使用します。
   *
   * @param message      表示する{@code Object}
   * @param title        ダイアログのタイトルバーに表示する{@code String}
   * @param optionType   ダイアログで選択可能のオプションを示す{@code int}値。{@code YES_NO_OPTION}、{@code
   *                   YES_NO_CANCEL_OPTION}、{@code OK_CANCEL_OPTION}のいずれか。
   * @param messageType  表示されるメッセージの種類。{@code ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code WARNING_MESSAGE}、{@code QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @param icon         表示する{@code Icon}イメージ
   * @param options      ユーザーが選択可能な項目を示すオブジェクト指向の配列。オブジェクトがコンポーネントの場合は適切にレンダリングされる。{@code String}以外のオブジェクトは、{@code toString()}メソッドを使用してレンダリングされる。このパラメータが{@code null}の場合、オプションはルック・アンド・フィールで決まる。
   * @param initialValue ダイアログのデフォルト選択を示すオブジェクト。{@code options}が使用される場合にだけ意味を持つ。{@code null}の場合もある。
   * @return ユーザーが選択したオプションを示す整数。ユーザーがダアログを閉じた場合は{@code CLOSED_OPTION}
   */
  public static int showOptionDialog(Object message, String title, int optionType, int messageType,
      Icon icon, Object[] options, Object initialValue) {
    logger.debug(
        "showOptionDialog message={} title={} optionType={} messageType={} icon={} options={} initialValue={}",
        message, title, optionType, messageType, icon, options, initialValue);
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

  /**
   * Sets default dialog size.
   *
   * @param defaultDialogSize the default dialog size
   */
  public static void setDefaultDialogSize(Dimension defaultDialogSize) {
    logger.debug("setDefaultDialogSize defaultDialogSize={}", defaultDialogSize);
    Dialog.defaultDialogSize = defaultDialogSize;
  }

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
   * 指定されたアイコンを持つインゲームダイアログを表示します。ルック・アンド・フィールのデフォルトアイコンを提示するには、主に{@code messageType}パラメータを使用します。
   *
   * @param message     表示する{@code Object}
   * @param title       ダイアログのタイトルバーに表示する{@code String}
   * @param options     ユーザーが選択可能な項目を示すオブジェクト指向の配列。{@code String}以外のオブジェクトは、{@code toString()}メソッドを使用してレンダリングされる。
   * @param messageType 表示されるメッセージの種類。{@code ERROR_MESSAGE}、{@code INFORMATION_MESSAGE}、{@code WARNING_MESSAGE}、{@code QUESTION_MESSAGE}、または{@code PLAIN_MESSAGE}
   * @param icon        表示する{@code Icon}イメージ
   * @param cancel      {@code cancel}されたときの{@code ActionListener}
   * @param listeners   {@code options}で指定されたオブジェクトから生成された{@code JButton}に追加される{@code ActionListener}
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
}