package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.HighScoreController;
import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.controller.StartPageController;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This class implements the HighScoreEvent interface to handle all the events on the High Score
 * List page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class HighScoreEventImpl implements HighScoreEvent {
  private static HighScoreEventImpl instance = new HighScoreEventImpl();
  GridManager gridManager = GridManager.getInstance();

  private HighScoreEventImpl() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of HighScoreEventImpl object.
   */
  public static HighScoreEventImpl getInstance() {
    return instance;
  }

  private String formatTime(long timeCount) {
    long hours = (timeCount % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    long minutes = (timeCount % (1000 * 60 * 60)) / (1000 * 60);
    long seconds = (timeCount % (1000 * 60)) / 1000;
    return hours + " hr " + minutes + " min " + seconds + " sec ";
  }

  /**
   * This method sets the text of Text objects in the page.
   *
   * @param scores A string list of scores
   * @param highScoreController The {@link HighScoreController} object that will be used to set the
   *     attribute of the text on the page.
   */
  @Override
  public void setText(List<String> scores, HighScoreController highScoreController) {
    highScoreController
        .getFirstText()
        .setText(
            "Move: " + scores.get(0) + "   Time: " + formatTime(Long.parseLong(scores.get(1))));
    highScoreController
        .getSecondText()
        .setText(
            "Move: " + scores.get(2) + "   Time: " + formatTime(Long.parseLong(scores.get(3))));
    highScoreController
        .getThirdText()
        .setText(
            "Move: " + scores.get(4) + "   Time: " + formatTime(Long.parseLong(scores.get(5))));
    highScoreController
        .getForthText()
        .setText(
            "Move: " + scores.get(6) + "   Time: " + formatTime(Long.parseLong(scores.get(7))));
    highScoreController
        .getFifthText()
        .setText(
            "Move: " + scores.get(8) + "   Time: " + formatTime(Long.parseLong(scores.get(9))));
    highScoreController
        .getSixthText()
        .setText(
            "Move: " + scores.get(10) + "   Time: " + formatTime(Long.parseLong(scores.get(11))));
    highScoreController
        .getSeventhText()
        .setText(
            "Move: " + scores.get(12) + "   Time: " + formatTime(Long.parseLong(scores.get(13))));
    highScoreController
        .getEighthText()
        .setText(
            "Move: " + scores.get(14) + "   Time: " + formatTime(Long.parseLong(scores.get(15))));
  }

  /**
   * This method handles the event of back button.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void backButton(Stage primaryStage) {
    PlayPageController playPageController = gridManager.getPlayPageController();
    playPageController.stopDisplayTimer();
    gridManager.setIsSetLevel(false);
    gridManager.setLevelChoose(false);
    gridManager.setBeginnerGuidance(false);
    gridManager.setChallengeMode(false);
    gridManager.setIsFirstLoadGrid(true);
    gridManager.setAdvertisementPlayed(false);
    primaryStage.removeEventFilter(
        KeyEvent.KEY_PRESSED, playPageController.getKeyEventEventHandler());
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/StartPage.fxml"));
      AnchorPane root = loader.load();
      // Set the application window's text in the title bar.
      primaryStage.setTitle(GameEngine.GAME_NAME);
      primaryStage.setScene(new Scene(root));
      // Prevent user from zooming in
      primaryStage.setResizable(false);
      primaryStage.show();
      StartPageController startPageController = loader.getController();
      startPageController.initialize(primaryStage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
