package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.MorePageEventImpl;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 * This class controls all the events on the More page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class MorePageController {
  MorePageEventImpl morePageEvent = MorePageEventImpl.getInstance();
  private Stage primaryStage;

  /**
   * This method initializes the MorePage controller, sets primaryStage.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  public void initialize(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  /** This method handles the display of Rule page. */
  @FXML
  public void handleRuleButton() {
    morePageEvent.ruleButton(primaryStage);
  }
  /** This method handles the display of Support page. */
  @FXML
  public void handleSupportButton() {
    morePageEvent.supportButton(primaryStage);
  }
  /** This method handles the display of Beginner Guidance page. */
  @FXML
  public void handleGuidanceButton() {
    morePageEvent.guidanceButton(primaryStage);
  }
  /** This method handles the redirect to main menu. */
  @FXML
  public void handleBackButton() {
    morePageEvent.backButton(primaryStage);
  }
  /** This method handles the exit function of the game. */
  @FXML
  public void handleExitButton() {
    morePageEvent.exitButton();
  }
}
