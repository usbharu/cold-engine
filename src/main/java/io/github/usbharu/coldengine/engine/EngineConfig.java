package io.github.usbharu.coldengine.engine;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * The type Engine config.
 *
 * @author usbharu
 * @version 0.0.3
 * @since 0.0.1
 */
public class EngineConfig {

  private static final EngineConfig singleton = new EngineConfig();
  private SAXParserFactory saxParserFactory;
  private SAXParser saxParser;

  private final Logger logger = LogManager.getLogger(EngineConfig.class);
  private EngineConfig() {
  }

  /**
   * インスタンスを返します
   * インスタンスを取得するときは必ずこのメソッドを使用して下さい。
   *
   * @return インスタンス
   */
  public static EngineConfig getInstance() {
    return singleton;
  }

  /**
   * {@code cold-engine}のデフォルト設定を読み込みます。
   */
  public void loadEngineConfig() {
    logger.info("Loading engine config...");
    try (InputStream config = getClass().getResourceAsStream("/EngineConfig.xml");) {
      logger.info("loading engine config from {}",
          getClass().getResource("/EngineConfig.xml").getPath());
      saxParserFactory = SAXParserFactory.newInstance();
      saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(config, new EngineConfigReader());
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }
  }
}