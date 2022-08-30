package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.PlayPageEventImpl;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.view.GraphicObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;

/**
 * This class controls all the events on the game Play page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class PlayPageController {

  PlayPageEventImpl playPageEvent = PlayPageEventImpl.getInstance();
  GridManager gridManager = GridManager.getInstance();
  private GraphicObject graphicObject;
  @FXML private GridPane gameGrid = new GridPane();
  private Image crateBlue;
  private Image crateBrown;
  private Image diamondBlue;
  private Image diamondBrown;
  private Image floorGreen;
  private Image floorGrey;
  private Image wallRed;
  private Image wallGrey;
  private Image playerGreenDown;
  private Image playerGreenLeft;
  private Image playerGreenRight;
  private Image playerGreenUp;
  private Image playerGreyDown;
  private Image playerGreyLeft;
  private Image playerGreyRight;
  private Image playerGreyUp;
  private Image crateOnDiamondBlue;
  private Image crateOnDiamondBrown;
  private Stage primaryStage;
  private GameEngine gameEngine;
  private Boolean isSwitchTheme = false;
  private Boolean isSwitchKey = false;
  private javafx.event.EventHandler<KeyEvent> keyEventEventHandler;
  @FXML private MenuItem loadGame;
  @FXML private MenuItem saveGame;
  @FXML private MenuItem resetLevel;
  @FXML private SeparatorMenuItem fileSeparator;
  @FXML private Label moveText;
  @FXML private Label timeText;
  @FXML private SeparatorMenuItem levelSeparator;
  private Timeline animation;
  private long time = 0;
  private int move = 0;
  private long challengeTime = 0;
  private int challengeMove = 0;
  private ScheduledExecutorService mScheduledExecutorService;

  /**
   * This method initializes the game by calling the reLoadGrid method.
   *
   * @param inputGameFile Input stream from a game file.
   * @param inputHighScore Input stream from the high score list file.
   * @param primaryStage The primary stage that displays the game.
   */
  public void initializeGame(
      InputStream inputGameFile, InputStream inputHighScore, Stage primaryStage) {
    this.primaryStage = primaryStage;
    gameEngine = new GameEngine(inputGameFile, inputHighScore);
    initializeAllImg();
    graphicObject =
        new GraphicObject(
            wallGrey,
            floorGreen,
            crateBlue,
            playerGreenUp,
            playerGreenDown,
            playerGreenRight,
            playerGreenLeft,
            diamondBlue,
            crateOnDiamondBlue);
    gridManager.reloadGrid(null);
    if (!gridManager.getChallengeMode()
        && !gridManager.getLevelChoose()
        && !gridManager.getBeginnerGuidance()) {
      mScheduledExecutorService = playPageEvent.autoSave(this);
      primaryStage.setOnCloseRequest(event -> mScheduledExecutorService.shutdown());
    }
  }

  /**
   * This method adds event filter to the window to handle click events between players and the
   * program.
   *
   * <p>including keyboard actions, mouse actions, scrolling actions, and other interactions between
   * the user and the program
   */
  public void setEventFilter() {
    keyEventEventHandler =
        event -> {
          playPageEvent.controlKey(event.getCode(), this);
          gridManager.reloadGrid(event.getCode());
        };
    primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, keyEventEventHandler);
  }

  /** This method returns to the main menu. */
  @FXML
  public void handleBackMain() {
    autoSaveShutDown();
    stopDisplayTimer();
    playPageEvent.backMain(primaryStage);
  }

  /** This method handles the shut down operation of ScheduledExecutorService */
  public void autoSaveShutDown() {
    if (!gridManager.getChallengeMode()
        && !gridManager.getLevelChoose()
        && !gridManager.getBeginnerGuidance()) {
      mScheduledExecutorService.shutdown();
    }
  }

  /** This method closes the game. */
  @FXML
  public void handleCloseGame() {
    playPageEvent.closeGame();
  }

  /** This method saves the current level. */
  @FXML
  public void handleSaveGame() {
    playPageEvent.saveGame(this);
  }

  /** This methods takes a step back. */
  @FXML
  public void handleUndo() {
    playPageEvent.undo(this);
    gridManager.reloadGrid(null);
  }

  /** This method resets the level, restores to the original stage of this level. */
  @FXML
  public void handleResetLevel() {
    playPageEvent.resetLevel(this);
  }

  /** This method prompts a message including the information about this game. */
  @FXML
  public void handleShowAbout() {
    playPageEvent.showAbout(this);
  }

  /** This method loads a game file chose by player. */
  @FXML
  public void handleLoadGame() {
    autoSaveShutDown();
    playPageEvent.loadGame(this);
  }

  /** This method toggles the playing of music. */
  @FXML
  public void handleToggleMusic() {
    playPageEvent.toggleMusic();
  }

  /** This method toggles the debug mode. */
  @FXML
  public void handleToggleDebug() {
    gameEngine.toggleDebug();
    gridManager.reloadGrid(null);
  }

  /** This methods reverts the isSwitchKey variable. */
  @FXML
  void handleSwitchKey() {
    isSwitchKey = !isSwitchKey;
  }

  /** This method handles the switch theme feature. */
  @FXML
  public void handleSwitchTheme() {
    if (!isSwitchTheme) {
      isSwitchTheme = true;
      graphicObject =
          new GraphicObject(
              wallRed,
              floorGrey,
              crateBrown,
              playerGreyUp,
              playerGreyDown,
              playerGreyRight,
              playerGreyLeft,
              diamondBrown,
              crateOnDiamondBrown);
    } else {
      isSwitchTheme = false;
      graphicObject =
          new GraphicObject(
              wallGrey,
              floorGreen,
              crateBlue,
              playerGreenUp,
              playerGreenDown,
              playerGreenRight,
              playerGreenLeft,
              diamondBlue,
              crateOnDiamondBlue);
    }
    gridManager.reloadGrid(null);
  }

  /** This method initializes all the images. */
  private void initializeAllImg() {
    crateBlue = initializeImg("object/crate/crate_blue.png");
    crateBrown = initializeImg("object/crate/crate_brown.png");

    diamondBlue = initializeImg("object/diamond/diamond_blue.png");
    diamondBrown = initializeImg("object/diamond/diamond_brown.png");

    floorGreen = initializeImg("object/floor/floor_green.png");
    floorGrey = initializeImg("object/floor/floor_grey.png");

    wallRed = initializeImg("object/wall/wall_red.png");
    wallGrey = initializeImg("object/wall/wall_grey.png");

    playerGreenDown = initializeImg("object/player/playerGreen_down.png");
    playerGreenLeft = initializeImg("object/player/playerGreen_left.png");
    playerGreenRight = initializeImg("object/player/playerGreen_right.png");
    playerGreenUp = initializeImg("object/player/playerGreen_up.png");

    playerGreyDown = initializeImg("object/player/playerGrey_down.png");
    playerGreyLeft = initializeImg("object/player/playerGrey_left.png");
    playerGreyRight = initializeImg("object/player/playerGrey_right.png");
    playerGreyUp = initializeImg("object/player/playerGrey_up.png");

    crateOnDiamondBlue = initializeImg("object/crate_on_diamond/crateOnDiamond_blue.png");
    crateOnDiamondBrown = initializeImg("object/crate_on_diamond/crateOnDiamond_brown.png");
  }

  /**
   * This method initialize the image from the given image path.
   *
   * @param imgPath The path of a image.
   * @return A new image object.
   */
  private Image initializeImg(String imgPath) {
    String getImgPath =
        Objects.requireNonNull(this.getClass().getClassLoader().getResource(imgPath)).toString();
    return new Image(getImgPath);
  }

  /**
   * This method returns the keyEventEventHandler.
   *
   * @return Event of the *eyEventEventHandler.
   */
  public javafx.event.EventHandler<KeyEvent> getKeyEventEventHandler() {
    return keyEventEventHandler;
  }

  /**
   * This is the getter for getting the secondText object.
   *
   * @return The isSwitchKey variable.
   */
  public Boolean getIsSwitchKey() {
    return isSwitchKey;
  }

  /** This method hides some of the menuItems. */
  public void hideMenuItem() {
    loadGame.setVisible(false);
    saveGame.setVisible(false);
    resetLevel.setVisible(false);
    fileSeparator.setVisible(false);
    levelSeparator.setVisible(false);
  }

  /**
   * This method initializes the text of moveText.
   *
   * @param challengeMove The limited move in the challenge mode.
   */
  public void initializeMoveText(int challengeMove) {
    if (gridManager.getChallengeMode()) {
      this.challengeMove = challengeMove;
      this.move = challengeMove;
    } else {
      this.move = gameEngine.getMovesCount();
    }
    moveText.setText("Move:  " + this.move);
  }

  /** This method sets the text of moveText. */
  public void setMoveText() {
    if (gridManager.getChallengeMode()) {
      move--;
    } else {
      move++;
    }
    moveText.setText("Move:  " + move);
  }
  /**
   * This method initializes the text of timeText.
   *
   * @param challengeTime The limited time in the challenge mode.
   */
  public void initializeTimer(long challengeTime) {
    if (gridManager.getChallengeMode()) {
      this.challengeTime = challengeTime;
      this.time = challengeTime;
      animation = new Timeline(new KeyFrame(Duration.millis(1), e -> setChallengeModeLabel()));
    } else {
      this.time = gameEngine.getTimeCount();
      animation = new Timeline(new KeyFrame(Duration.millis(1), e -> setClassicModeTimeLabel()));
    }
    timeText.setText("Time: " + formatTime(this.time));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
  }

  /** This method pauses the timer. */
  public void pauseDisplayTimer() {
    animation.pause();
  }
  /** This method continues the timer. */
  public void continueDisplayTimer() {
    animation.play();
  }
  /** This method stops the timer. */
  public void stopDisplayTimer() {
    animation.stop();
  }

  private void setClassicModeTimeLabel() {
    this.time++;
    timeText.setText("Time: " + formatTime(this.time));
  }

  private void setChallengeModeLabel() {
    if (time == 0) {
      gridManager.reloadGrid(null);
    } else {
      this.time--;
      timeText.setText("Time: " + formatTime(this.time));
    }
  }

  private String formatTime(long timeCount) {
    long hours = (timeCount % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    long minutes = (timeCount % (1000 * 60 * 60)) / (1000 * 60);
    long seconds = (timeCount % (1000 * 60)) / 1000;
    return hours + " hr " + minutes + " min " + seconds + " sec ";
  }

  /**
   * This is the getter for getting the time.
   *
   * @return The value of time.
   */
  public long getTime() {
    return time;
  }

  /**
   * This is the setter for setting the time.
   *
   * @param time The time to be assigned.
   */
  public void setTime(long time) {
    this.time = time;
  }

  /**
   * This is the getter for getting the move.
   *
   * @return The value of move.
   */
  public int getMove() {
    return move;
  }
  /**
   * This is the setter for setting the move.
   *
   * @param move The move to be assigned.
   */
  public void setMove(int move) {
    this.move = move;
  }
  /**
   * This is the getter for getting the primaryStage.
   *
   * @return The primary {@link Stage} that displays the game.
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }
  /**
   * This is the getter for getting the gameEngine.
   *
   * @return The {@link GameEngine} that provide the core logic service for game.
   */
  public GameEngine getGameEngine() {
    return gameEngine;
  }
  /**
   * This is the getter for getting the getGraphicObject.
   *
   * @return The {@link GraphicObject} game object.
   */
  public GraphicObject getGraphicObject() {
    return graphicObject;
  }
  /**
   * This is the getter for getting the gameGrid.
   *
   * @return The {@link GridPane} object of the game.
   */
  public GridPane getGameGrid() {
    return gameGrid;
  }
}
