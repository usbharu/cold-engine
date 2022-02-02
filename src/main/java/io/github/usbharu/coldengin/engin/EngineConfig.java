package io.github.usbharu.coldengin.engin;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
      File config = new File(new URI(getClass().getResource("/EngineConfig.xml").toString()));
      saxParserFactory = SAXParserFactory.newInstance();
      saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(config, new EngineConfigReader());
    } catch (URISyntaxException | ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}