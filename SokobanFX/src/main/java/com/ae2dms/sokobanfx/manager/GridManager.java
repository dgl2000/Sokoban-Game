package com.ae2dms.sokobanfx.manager;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.event.GameComplete;
import com.ae2dms.sokobanfx.event.HighScoreListObserver;
import com.ae2dms.sokobanfx.event.ShowVictoryMessageObserver;
import com.ae2dms.sokobanfx.model.Level;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.awt.*;

/**
 * This class controls all the methods related to grid.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GridManager {
  private static GridManager instance = new GridManager();
  private int levelIndex = 0;
  private boolean isLevelChoose = false;
  private boolean isBeginnerGuidance = false;
  private boolean isChallengeMode = false;
  private boolean isSetLevel = false;
  private boolean isFirstLoadGrid = true;
  private boolean isAdvertisementPlayed = false;
  private PlayPageController playPageController;

  private GridManager() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of GridManager object.
   */
  public static GridManager getInstance() {
    return instance;
  }

  /**
   * This method reloads the whole game grid when being called.
   *
   * @param keyCode The key code player inputs.
   */
  public void reloadGrid(KeyCode keyCode) {
    GameComplete gameComplete = new GameComplete();
    new HighScoreListObserver(gameComplete);
    new ShowVictoryMessageObserver(gameComplete);
    if (isLevelChoose && !isSetLevel) {
      isSetLevel = true;
      for (int i = 0; i < levelIndex - 1; i++) {
        playPageController
            .getGameEngine()
            .setCurrentLevel(playPageController.getGameEngine().getNextLevel());
      }
    }
    boolean isComplete =
        playPageController.getGameEngine().isGameComplete()
            || (playPageController.getMove() == 0 && isChallengeMode && !isFirstLoadGrid)
            || (playPageController.getTime() == 0 && isChallengeMode && !isFirstLoadGrid);
    if (isComplete) {
      playPageController.getGameEngine().pauseTimer();
      playPageController.pauseDisplayTimer();
      gameComplete.setState(playPageController);
      return;
    }

    Level currentLevel = playPageController.getGameEngine().getCurrentLevel();
    while (currentLevel.isComplete()) {
      playPageController
          .getGameEngine()
          .setCurrentLevel(playPageController.getGameEngine().getNextLevel());
      currentLevel = playPageController.getGameEngine().getCurrentLevel();
    }
    Level.LevelIterator levelGridIterator = (Level.LevelIterator) currentLevel.iterator();
    playPageController.getGameGrid().getChildren().clear();
    while (levelGridIterator.hasNext()) {
      isFirstLoadGrid = false;
      addObjectToGrid(levelGridIterator.next(), levelGridIterator.getCurrentPosition(), keyCode);
    }
    playPageController.getGameGrid().autosize();
    playPageController.getPrimaryStage().sizeToScene();
  }

  /**
   * /** This method adds object to the grid, giving the location.
   *
   * @param gameObject as the input of the newly graphic {@link GameObject}.
   * @param location the location {@link Point} of the object.
   */
  private void addObjectToGrid(GameObject gameObject, Point location, KeyCode keyCode) {
    Image image =
        playPageController.getGraphicObject().setImage(gameObject, keyCode, playPageController);

    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(30);
    imageView.setFitWidth(30);
    if (GameEngine.isDebugActive()) {
      imageView.setEffect(new Lighting());
    }
    playPageController.getGameGrid().add(imageView, location.y, location.x);
  }

  /**
   * This is the setter for setting the index of the level.
   *
   * @param levelIndex The index of the level.
   */
  public void setLevelIndex(int levelIndex) {
    this.levelIndex = levelIndex;
  }
  /**
   * This is the getter for getting the isLevelChoose variable.
   *
   * @return Whether is in level choose mode.
   */
  public boolean getLevelChoose() {
    return isLevelChoose;
  }
  /**
   * This is the setter for setting whether is in level choose mode.
   *
   * @param levelChoose Whether in the level choose mode.
   */
  public void setLevelChoose(boolean levelChoose) {
    this.isLevelChoose = levelChoose;
  }

  /**
   * This is the getter for getting the getPlayPageController.
   *
   * @return The {@link PlayPageController} object that handles the event on Play page.
   */
  public PlayPageController getPlayPageController() {
    return playPageController;
  }
  /**
   * This is the setter for setting the playPageController.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  public void setPlayPageController(PlayPageController playPageController) {
    this.playPageController = playPageController;
  }
  /**
   * This is the getter for getting the isBeginnerGuidance variable.
   *
   * @return Whether is in beginner guidance mode.
   */
  public boolean getBeginnerGuidance() {
    return isBeginnerGuidance;
  }
  /**
   * This is the setter for setting whether is in beginner guidance mode.
   *
   * @param isBeginnerGuidance Whether in the beginner guidance mode.
   */
  public void setBeginnerGuidance(boolean isBeginnerGuidance) {
    this.isBeginnerGuidance = isBeginnerGuidance;
  }
  /**
   * This is the getter for getting the getChallengeMode variable.
   *
   * @return Whether is in challenge mode.
   */
  public boolean getChallengeMode() {
    return isChallengeMode;
  }
  /**
   * This is the setter for setting whether is in challenge mode.
   *
   * @param isChallengeMode Whether in the challenge mode.
   */
  public void setChallengeMode(boolean isChallengeMode) {
    this.isChallengeMode = isChallengeMode;
  }
  /**
   * This is the setter for setting in the level choose mode whether the level is set.
   *
   * @param isSetLevel Whether the level is set.
   */
  public void setIsSetLevel(boolean isSetLevel) {
    this.isSetLevel = isSetLevel;
  }
  /**
   * This is the setter for setting in the challenge mode whether the grid has been loaded.
   *
   * @param isFirstLoadGrid Whether the level is set.
   */
  public void setIsFirstLoadGrid(boolean isFirstLoadGrid) {
    this.isFirstLoadGrid = isFirstLoadGrid;
  }

  /**
   * This is the getter for getting the isAdvertisementPlayed variable.
   *
   * @return Whether the advertisement has played.
   */
  public boolean getAdvertisementPlayed() {
    return isAdvertisementPlayed;
  }
  /**
   * This is the setter for setting in the challenge mode whether the advertisement has been played.
   *
   * @param advertisementPlayed Whether the advertisement has been played.
   */
  public void setAdvertisementPlayed(boolean advertisementPlayed) {
    isAdvertisementPlayed = advertisementPlayed;
  }
}
