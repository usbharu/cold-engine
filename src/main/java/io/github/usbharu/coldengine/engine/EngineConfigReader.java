package io.github.usbharu.coldengine.engine;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * {@code ColdEngine}の設定ファイルを読み込み、適用します。
 *
 * @author usbharu
 * @version 0.0.2
 * @since 0.0.1
 */
public class EngineConfigReader extends DefaultHandler {

  private final List<String> attribute = new ArrayList<>();
  private final Logger logger = LogManager.getLogger(EngineConfigReader.class);
  private boolean bool = false;
  private int integer = 0;
  private String string = "";
  private long longNumber = 0;
  private float floatNumber = 0f;
  private double doubleNumber = 0;


  /**
   * Receive notification of the start of an element.
   *
   * <p>By default, do nothing.  Application writers may override this
   * method in a subclass to take specific actions at the start of each element (such as allocating
   * a new tree node or writing output to a file).</p>
   *
   * @param uri        The Namespace URI, or the empty string if the element has no Namespace URI or
   *                   if Namespace processing is not being performed.
   * @param localName  The local name (without prefix), or the empty string if Namespace processing
   *                   is not being performed.
   * @param qName      The qualified name (with prefix), or the empty string if qualified names are
   *                   not available.
   * @param attributes The attributes attached to the element.  If there are no attributes, it shall
   *                   be an empty Attributes object.
   * @throws SAXException Any SAX exception, possibly wrapping another exception.
   * @see ContentHandler#startElement
   */
  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    for (int i = 0; i < attributes.getLength(); i++) {
      attribute.add(attributes.getValue(i));
    }
  }

  /**
   * Receive notification of the end of an element.
   *
   * <p>By default, do nothing.  Application writers may override this
   * method in a subclass to take specific actions at the end of each element (such as finalising a
   * tree node or writing output to a file).</p>
   *
   * @param uri       The Namespace URI, or the empty string if the element has no Namespace URI or
   *                  if Namespace processing is not being performed.
   * @param localName The local name (without prefix), or the empty string if Namespace processing
   *                  is not being performed.
   * @param qName     The qualified name (with prefix), or the empty string if qualified names are
   *                  not available.
   * @throws SAXException Any SAX exception, possibly wrapping another exception.
   * @see ContentHandler#endElement
   */
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    switch (qName) {
      case "Version":
        logger.debug("Version:{}", floatNumber);
        if (floatNumber < 0.1f) {
          throw new IllegalArgumentException("The EngineConfig.xml version is wrong.");
        }
        break;
      case "Runtime":
        logger.debug("Runtime Config");
        break;
      case "FPSLowerLimit":
        EngineConfigRapper.setFPSLowerLimit(integer);
        logger.debug("FPSLowerLimit:{}", floatNumber);
        break;
      case "FPSUpperLimit":
        EngineConfigRapper.setFPSUpperLimit(integer);
        logger.debug("FPSUpperLimit:{}", floatNumber);
        break;
      case "DefaultFPS":
        EngineConfigRapper.setFPS(integer);
        logger.debug("DefaultFPS:{}", integer);
        break;
      case "DefaultTitle":
        EngineConfigRapper.setDefaultTitle(string);
        logger.debug("DefaultTitle:{}", string);
        break;
      case "DefaultWindowSize":
        if (attribute.get(0).equals("x")) {
          EngineConfigRapper.setDefaultWindowSizeX(integer);
          logger.debug("DefaultWindowSize:{}", integer);
          EngineConfigRapper.setDefaultWindowSize();
          attribute.remove(0);
        } else if (attribute.get(0).equals("y")) {
          EngineConfigRapper.setDefaultWindowSizeY(integer);
          logger.debug("DefaultWindowSize:{}", integer);
          EngineConfigRapper.setDefaultWindowSize();
          attribute.remove(0);
        }
        break;
      case "DefaultDialogSize":
        if (attribute.get(0).equals("x")) {
          EngineConfigRapper.setDefaultDialogSizeX(integer);
          EngineConfigRapper.setDefaultDialogSize();
          attribute.remove(0);
        } else if (attribute.get(0).equals("y")) {
          EngineConfigRapper.setDefaultDialogSizeY(integer);
          EngineConfigRapper.setDefaultDialogSize();
          attribute.remove(0);
        }
        break;
      case "DefaultWindowResize":
        EngineConfigRapper.setDefaultWindowResize(bool);
        logger.debug("DefaultWindowResize:{}", bool);
        break;
      case "EngineConfig":
        logger.debug("Config End");
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + qName);
    }
  }

  /**
   * Receive notification of character data inside an element.
   *
   * <p>By default, do nothing.  Application writers may override this
   * method to take specific actions for each chunk of character data (such as adding the data to a
   * node or buffer, or printing it to a file).</p>
   *
   * @param ch     The characters.
   * @param start  The start position in the character array.
   * @param length The number of characters to use from the character array.
   * @throws SAXException Any SAX exception, possibly wrapping another exception.
   * @see ContentHandler#characters
   */
  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String str = new String(ch, start, length);
    if (ColdEngineUtilities.isBoolean(str)) {
      bool = str.charAt(0) == 't';
      return;
    }
    if (ColdEngineUtilities.isDigit(str)) {
      try {
        integer = Integer.parseInt(str);
      } catch (NumberFormatException e) {
        try {
          longNumber = Long.parseLong(str);
        } catch (NumberFormatException numberFormatException) {
          try {
            floatNumber = Float.parseFloat(str);
          } catch (NumberFormatException formatException) {
            doubleNumber = Double.parseDouble(str);
          }
        }
      }
    }
    string = str;
  }

  private void debug_AllVariablesPrint() {
    logger.debug("bool: {}", bool);
    logger.debug("integer: {}", integer);
    logger.debug("longNumber: {}", longNumber);
    logger.debug("floatNumber: {}", floatNumber);
    logger.debug("doubleNumber: {}", doubleNumber);
    logger.debug("string: {}", string);
  }
}