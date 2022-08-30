package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.concurrent.ScheduledExecutorService;

/**
 * This interface is a skeleton of all the controller's handling method.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface PlayPageEvent {
  /**
   * This methods handles the interaction between player's key input and game.
   *
   * @param code Player's press key.
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  void controlKey(KeyCode code, PlayPageController playPageController);

  /**
   * This method handles the redirect to the main menu.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void backMain(Stage primaryStage);

  /** This method exits the game. */
  void closeGame();

  /**
   * This method saves the current stage of the game.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  void saveGame(PlayPageController playPageController);

  /**
   * This method auto saves the current stage of the game.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   * @return The ScheduledExecutorService object that runs the autoSave.
   */
  ScheduledExecutorService autoSave(PlayPageController playPageController);

  /**
   * This method loads a game file chose by player.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  void loadGame(PlayPageController playPageController);

  /**
   * This methods handles the step back of the movement.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  void undo(PlayPageController playPageController);

  /**
   * This method handles the reset of the current level, restore to the original stage of this
   * level.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  void resetLevel(PlayPageController playPageController);

  /**
   * This method prompts a message including the info of the game.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  void showAbout(PlayPageController playPageController);

  /** This method handles the music play. */
  void toggleMusic();
}
