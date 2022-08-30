package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.StartPageEventImpl;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * This class controls all the events in the game Start page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class StartPageController {
  StartPageEventImpl startPageEvent = StartPageEventImpl.getInstance();
  private Stage primaryStage;

  /**
   * This method initializes the load file controller, set the primaryStage.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  public void initialize(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  /** This methods handles the display of Start page. */
  @FXML
  public void handleStartButton() {
    startPageEvent.startButton(primaryStage);
  }

  /** This methods handles the display of Level page. */
  @FXML
  public void handleLevelButton() {
    startPageEvent.levelButton(primaryStage);
  }

  /** This methods handles the display of More page. */
  @FXML
  public void handleMoreButton() {
    startPageEvent.moreButton(primaryStage);
  }

  /** This methods handles the display of Ranking page. */
  @FXML
  public void handleRankButton() {
    startPageEvent.rankButton(primaryStage);
  }

  /** This methods handles the display of Info page. */
  @FXML
  public void handleInfoButton() {
    startPageEvent.infoButton(primaryStage);
  }
}
