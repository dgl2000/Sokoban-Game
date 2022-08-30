package com.ae2dms.sokobanfx.event;

import javafx.stage.Stage;
/**
 * This interface is a skeleton of all the events on the More page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface MorePageEvent {
  /**
   * This method handles the display of Rule page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void ruleButton(Stage primaryStage);
  /**
   * This method handles the display of Support page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void supportButton(Stage primaryStage);
  /**
   * This method handles the display of Beginner Guidance page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void guidanceButton(Stage primaryStage);
  /**
   * This method handles the event of back button, redirect to the main menu.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void backButton(Stage primaryStage);
  /** This method exits the game. */
  void exitButton();
}
