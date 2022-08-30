package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.RankingDialogEventImpl;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
/**
 * This class controls all the events on the Ranking Dialog.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class RankingDialogController {
  RankingDialogEventImpl rankingDialogEvent = RankingDialogEventImpl.getInstance();
  @FXML private Text firstText;
  @FXML private Text secondText;
  @FXML private Text thirdText;
  @FXML private Text forthText;
  @FXML private Text fifthText;
  @FXML private Text sixthText;
  @FXML private Text seventhText;
  @FXML private Text eighthText;

  /**
   * This method loads the high score list file and sets the content of Text in the ranking dialog.
   */
  public void initialize() {
    rankingDialogEvent.setScoresList(this);
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
