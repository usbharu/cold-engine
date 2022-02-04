package io.github.usbharu.coldengin.engine;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class EngineConfig {

  private static final EngineConfig singleton = new EngineConfig();
  private SAXParserFactory saxParserFactory;
  private SAXParser saxParser;

  private final Logger logger = LogManager.getLogger(EngineConfig.class);
  private EngineConfig() {
  }

  public static EngineConfig getInstance() {
    return singleton;
  }

  public void loadEngineConfig() {
    try {
      logger.info("Loading engine config...");
      InputStream config = getClass().getResourceAsStream("/EngineConfig.xml");
      logger.info("loading engine config from {}", getClass().getResource("/EngineConfig.xml").getPath());
      saxParserFactory = SAXParserFactory.newInstance();
      saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(config, new EngineConfigReader());
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}