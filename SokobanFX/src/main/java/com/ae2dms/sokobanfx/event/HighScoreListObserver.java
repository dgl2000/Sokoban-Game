package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.AdvertisementController;
import com.ae2dms.sokobanfx.controller.HighScoreController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
/**
 * This class implements the observer, updates the high score list everytime being notified.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class HighScoreListObserver extends GameCompleteObserver {

  /**
   * This constructor initializes the GameComplete object.
   *
   * @param gameComplete The {@link GameComplete} object that is being observed.
   */
  public HighScoreListObserver(GameComplete gameComplete) {
    this.gameComplete = gameComplete;
    this.gameComplete.attach(this);
  }

  /** This method helps to sort the high score list. */
  private void sortHighScoreList() {
    int insertIndex = 0;
    boolean hasInserted = false;
    for (int m = 0; m < 8; m++) {
      if (Integer.parseInt(gameComplete.getGameEngine().getScores().get(2 * m)) == 0) {
        insertIndex = m;
        hasInserted = true;
      } else {
        boolean isInserted =
            gameComplete.getMove()
                    < Integer.parseInt(gameComplete.getGameEngine().getScores().get(2 * m))
                || (gameComplete.getMove()
                        == Integer.parseInt(gameComplete.getGameEngine().getScores().get(2 * m))
                    && gameComplete.getTime()
                        < Long.parseLong(gameComplete.getGameEngine().getScores().get(2 * m + 1)));
        if (isInserted) {
          insertIndex = m;
          hasInserted = true;
          for (int n = 7; n > insertIndex; n--) {
            gameComplete
                .getGameEngine()
                .getScores()
                .set(2 * n + 1, gameComplete.getGameEngine().getScores().get(2 * (n - 1) + 1));
            gameComplete
                .getGameEngine()
                .getScores()
                .set(2 * n, gameComplete.getGameEngine().getScores().get(2 * (n - 1)));
          }
        }
      }
      if (hasInserted) {
        gameComplete
            .getGameEngine()
            .getScores()
            .set(2 * insertIndex, Integer.toString(gameComplete.getMove()));
        gameComplete
            .getGameEngine()
            .getScores()
            .set(2 * insertIndex + 1, Long.toString(gameComplete.getTime()));
        break;
      }
    }
  }
  /** The method pulls the new state value of the GameComplete, and displays accordingly. */
  @Override
  public void update() {
    GridManager gridManager = GridManager.getInstance();
    if (gridManager.getChallengeMode()
        && !gridManager.getAdvertisementPlayed()
        && !gameComplete.getGameEngine().isGameComplete()) {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getClassLoader().getResource("ui/Advertisement.fxml"));
      try {
        AnchorPane root = loader.load();
        // Set the application window's text in the title bar.
        Stage ad = new Stage();
        ad.initModality(Modality.APPLICATION_MODAL);
        ad.initOwner(gameComplete.getPrimaryStage());
        ad.initStyle(StageStyle.UNDECORATED);
        String logoPath =
            Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
                .toString();
        gameComplete.getPrimaryStage().getIcons().add(new Image(logoPath));
        AdvertisementController advertisementController = loader.getController();
        advertisementController.initialize(ad, gameComplete.getPrimaryStage());
        ad.setScene(new Scene(root));
        // Prevent user from zooming in
        ad.setResizable(false);
        ad.show();
      } catch (IOException e) {
        e.printStackTrace();
      }

    } else {
      if (!gridManager.getLevelChoose()
          && !gridManager.getBeginnerGuidance()
          && !gridManager.getChallengeMode()) {
        sortHighScoreList();
      }
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getClassLoader().getResource("ui/HighScore.fxml"));
      try {
        AnchorPane root = loader.load();
        // Set the application window's text in the title bar.
        Stage highScore = new Stage();
        highScore.initModality(Modality.APPLICATION_MODAL);
        highScore.initOwner(gameComplete.getPrimaryStage());
        highScore.initStyle(StageStyle.UNDECORATED);
        String logoPath =
            Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
                .toString();
        gameComplete.getPrimaryStage().getIcons().add(new Image(logoPath));
        HighScoreController highScoreController = loader.getController();

        highScoreController.initialize(
            gameComplete.getGameEngine().getScores(), highScore, gameComplete.getPrimaryStage());
        highScore.setScene(new Scene(root));
        // Prevent user from zooming in
        highScore.setResizable(false);
        highScore.show();
        if (!gridManager.getLevelChoose()
            && !gridManager.getBeginnerGuidance()
            && !gridManager.getChallengeMode()) {
          saveHighScoreGame();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /** This method opens a window and stores the game file at the place chosen by the player. */
  public void saveHighScoreGame() {
    StringBuilder gameText = listContent();
    FileManager fileManager = FileManager.getInstance();
    fileManager.saveFile("/HighScoreList.skb", gameText);
  }

  /**
   * This method combines the objectsGrid and diamondGrids.
   *
   * @return the combined game text.
   * @see StringBuilder
   */
  private StringBuilder listContent() {
    GameEngine gameEngine = gameComplete.getGameEngine();
    StringBuilder gameText = new StringBuilder();
    List<String> scores = gameEngine.getScores();
    for (int i = 0; i < 8; i++) {
      String move = scores.get(2 * i);
      String time = scores.get(2 * i + 1);
      gameText.append("Move: ").append(move).append("\n");
      gameText.append("Time: ").append(time).append("\n");
    }
    return gameText;
  }
}
