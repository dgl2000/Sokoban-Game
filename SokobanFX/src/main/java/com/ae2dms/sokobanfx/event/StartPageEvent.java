package com.ae2dms.sokobanfx.event;

import javafx.stage.Stage;

/**
 * This interface shows the skeleton of all the start page game events.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface StartPageEvent {
  /**
   * This methods handles the display of Start page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void startButton(Stage primaryStage);

  /**
   * This methods handles the display of Level page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void levelButton(Stage primaryStage);

  /**
   * This methods handles the display of More page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void moreButton(Stage primaryStage);

  /**
   * This methods handles the display of Ranking page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void rankButton(Stage primaryStage);

  /**
   * This methods handles the display of Info page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void infoButton(Stage primaryStage);
}
