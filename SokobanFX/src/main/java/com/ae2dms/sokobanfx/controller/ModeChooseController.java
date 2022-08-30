package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.ModeChooseEventImpl;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * This class controls all the events on the Mode choose page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class ModeChooseController {
  ModeChooseEventImpl modeChooseEvent = ModeChooseEventImpl.getInstance();
  private Stage primaryStage;
  private Stage dialog;

  /**
   * This method initializes the ModeChoose controller, sets primaryStage and dialog stage.
   *
   * @param dialog The stage that displays the pop-ups window.
   * @param primaryStage The primary stage that displays the game.
   */
  public void initialize(Stage dialog, Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.dialog = dialog;
  }

  /** This method handles the display of classic mode game window. */
  @FXML
  public void handleClassicMode() {
    dialog.close();
    modeChooseEvent.classicMode(primaryStage);
  }

  /** This method handles the display of challenge mode game window. */
  @FXML
  public void handleChallengeMode() {
    dialog.close();
    modeChooseEvent.challengeMode(primaryStage);
  }
}
