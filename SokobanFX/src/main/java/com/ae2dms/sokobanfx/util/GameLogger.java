package com.ae2dms.sokobanfx.util;

import com.ae2dms.sokobanfx.service.GameEngine;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class writes the game log.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameLogger extends Logger {

  private static final Logger logger = Logger.getLogger("GameLogger");
  private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  private final Calendar calendar = Calendar.getInstance();

  /**
   * This method creates the game logger.
   *
   * @throws IOException Throws occur whenever an input or output operation is failed or
   *     interpreted.
   * @see IOException
   */
  public GameLogger() throws IOException {
    super("com.aes2dms.sokoban", null);

    File directory = new File(System.getProperty("user.dir") + "/" + "logs");
    directory.mkdirs();

    FileHandler fh = new FileHandler(directory + "/" + GameEngine.GAME_NAME + ".log");
    logger.addHandler(fh);
    SimpleFormatter formatter = new SimpleFormatter();
    fh.setFormatter(formatter);
  }

  /**
   * This method creates the formatted message includes the date and the message.
   *
   * @param message The message that will be formatted.
   * @return A string of formatted message.
   */
  private String createFormattedMessage(String message) {
    return dateFormat.format(calendar.getTime()) + " -- " + message;
  }

  /**
   * This method takes the message as input and return the info message.
   *
   * @param message The info message that will be formatted.
   */
  @Override
  public void info(String message) {
    logger.info(createFormattedMessage(message));
  }

  /**
   * This method takes the message as input and return the warning message.
   *
   * @param message The warning message that will be formatted.
   */
  @Override
  public void warning(String message) {
    logger.warning(createFormattedMessage(message));
  }

  /**
   * This method takes the message as input and return the severe message.
   *
   * @param message The severe message that will be formatted.
   */
  @Override
  public void severe(String message) {
    logger.severe(createFormattedMessage(message));
  }
}
