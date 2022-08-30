package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.LevelChoosePageEventImpl;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * This class controls all the events on the Choose Level page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class LevelChoosePageController {
  LevelChoosePageEventImpl levelChoosePageEvent = LevelChoosePageEventImpl.getInstance();
  private Stage primaryStage;
  private Stage dialog;

  /**
   * This method initializes the LevelChoose controller, sets primaryStage and dialog stage.
   *
   * @param dialog The stage that displays the pop-ups window.
   * @param primaryStage The primary stage that displays the game.
   * @see Stage
   */
  public void initialize(Stage dialog, Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.dialog = dialog;
  }

  /** This method handles the redirect to level 1. */
  @FXML
  public void goToLevel1() {
    dialog.close();
    levelChoosePageEvent.gotoLevel(1, primaryStage);
  }

  /** This method handles the redirect to level 2. */
  @FXML
  public void goToLevel2() {
    dialog.close();
    levelChoosePageEvent.gotoLevel(2, primaryStage);
  }

  /** This method handles the redirect to level 3. */
  @FXML
  public void goToLevel3() {
    dialog.close();
    levelChoosePageEvent.gotoLevel(3, primaryStage);
  }

  /** This method handles the redirect to level 4. */
  @FXML
  public void goToLevel4() {
    dialog.close();
    levelChoosePageEvent.gotoLevel(4, primaryStage);
  }

  /** This method handles the redirect to level 5. */
  @FXML
  public void goToLevel5() {
    dialog.close();
    levelChoosePageEvent.gotoLevel(5, primaryStage);
  }

  /** This method handles the redirect to level 6. */
  @FXML
  public void goToLevel6() {
    dialog.close();
    levelChoosePageEvent.gotoLevel(6, primaryStage);
  }
}
