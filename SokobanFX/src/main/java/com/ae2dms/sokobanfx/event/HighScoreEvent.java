package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.HighScoreController;
import javafx.stage.Stage;

import java.util.List;
/**
 * This interface is a skeleton of all the events on the High Score List page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface HighScoreEvent {
  /**
   * This method handles the event of back button.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void backButton(Stage primaryStage);

  /**
   * This method sets the text of Text objects in the page.
   *
   * @param scores A string list of scores
   * @param highScoreController The {@link HighScoreController} object that will be used to set the
   *     attribute of the text on the page.
   */
  void setText(List<String> scores, HighScoreController highScoreController);
}
