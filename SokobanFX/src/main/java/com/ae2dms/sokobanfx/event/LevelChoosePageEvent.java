package com.ae2dms.sokobanfx.event;

import javafx.stage.Stage;
/**
 * This interface is a skeleton of all the events on the Choose Level page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface LevelChoosePageEvent {
  /**
   * This method handles the redirect to the player chosen level.
   *
   * @param index This represents the level which player chosen.
   * @param primaryStage The primary stage that displays the game.
   */
  void gotoLevel(int index, Stage primaryStage);
}
