package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.HighScoreEventImpl;
import com.ae2dms.sokobanfx.manager.GridManager;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
/**
 * This class controls all the events on the High Score List page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class HighScoreController {
  HighScoreEventImpl highScoreEvent = HighScoreEventImpl.getInstance();
  @FXML private Text firstText;
  @FXML private Text secondText;
  @FXML private Text thirdText;
  @FXML private Text forthText;
  @FXML private Text fifthText;
  @FXML private Text sixthText;
  @FXML private Text seventhText;
  @FXML private Text eighthText;
  private Stage primaryStage;
  private Stage highScore;

  /**
   * This method initializes the HighScoreController, sets primaryStage, highScore stage and the
   * text of Text objects in the page.
   *
   * @param scores A string list of scores.
   * @param highScore The stage that displays the pop-ups window.
   * @param primaryStage The primary stage that displays the game.
   */
  public void initialize(List<String> scores, Stage highScore, Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.highScore = highScore;
    highScoreEvent.setText(scores, this);
  }
  /**
   * This method sets the text of Text objects in the page.
   *
   * @param scores A {@link List<String>} of scores
   */
  public void setText(List<String> scores) {
    highScoreEvent.setText(scores, this);
  }

  /** This method handles the redirect to the main menu function. */
  @FXML
  public void handleBack() {
    GridManager gridManager = GridManager.getInstance();
    gridManager.getPlayPageController().autoSaveShutDown();
    highScore.close();
    highScoreEvent.backButton(primaryStage);
  }

  /** This method handles the exit function of the game. */
  @FXML
  public void handleExit() {
    System.exit(0);
  }

  /**
   * This is the getter for getting the firstText object.
   *
   * @return The {@link Text} object which id is firstText in the FXML file.
   */
  public Text getFirstText() {
    return firstText;
  }

  /**
   * This is the getter for getting the secondText object.
   *
   * @return The {@link Text} object which id is secondText in the FXML file.
   */
  public Text getSecondText() {
    return secondText;
  }

  /**
   * This is the getter for getting the thirdText object.
   *
   * @return The {@link Text} object which id is thirdText in the FXML file.
   */
  public Text getThirdText() {
    return thirdText;
  }

  /**
   * This is the getter for getting the forthText object.
   *
   * @return The {@link Text} object which id is forthText in the FXML file.
   */
  public Text getForthText() {
    return forthText;
  }

  /**
   * This is the getter for getting the fifthText object.
   *
   * @return The {@link Text} object which id is fifthText in the FXML file.
   */
  public Text getFifthText() {
    return fifthText;
  }

  /**
   * This is the getter for getting the sixthText object.
   *
   * @return The {@link Text} object which id is sixthText in the FXML file.
   */
  public Text getSixthText() {
    return sixthText;
  }

  /**
   * This is the getter for getting the seventhText object.
   *
   * @return The {@link Text} object which id is seventhText in the FXML file.
   */
  public Text getSeventhText() {
    return seventhText;
  }

  /**
   * This is the getter for getting the eighthText object.
   *
   * @return The {@link Text} object which id is eighthText in the FXML file.
   */
  public Text getEighthText() {
    return eighthText;
  }
}
