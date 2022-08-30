package com.ae2dms.sokobanfx.event;

import javafx.stage.Stage;
/**
 * This interface is a skeleton of all the events on the Mode choose page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface ModeChooseEvent {
  /**
   * This method handles the display of classic mode game window.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void classicMode(Stage primaryStage);

  /**
   * This method handles the display of challenge mode game window.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void challengeMode(Stage primaryStage);
}
