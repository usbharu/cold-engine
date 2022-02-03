package io.github.usbharu.coldengin.engine;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class EngineConfig {

  private static final EngineConfig singleton = new EngineConfig();
  private SAXParserFactory saxParserFactory;
  private SAXParser saxParser;

  private EngineConfig() {
  }

  public static EngineConfig getInstance() {
    return singleton;
  }

  public void loadEngineConfig() {
    try {
      InputStream config = getClass().getResourceAsStream("/EngineConfig.xml");
      saxParserFactory = SAXParserFactory.newInstance();
      saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(config, new EngineConfigReader());
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}