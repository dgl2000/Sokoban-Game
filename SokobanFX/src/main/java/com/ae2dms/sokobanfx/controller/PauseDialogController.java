package com.ae2dms.sokobanfx.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * This class controls all the events on the Pause Dialog.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class PauseDialogController {
  @FXML private Text moveText;
  @FXML private Text timeText;

  /**
   * This method initializes the PauseDialog controller, sets the text of moveText, the text of
   * timeText in the page.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  public void initialize(PlayPageController playPageController) {
    moveText.setText(String.valueOf(playPageController.getGameEngine().getMovesCount()));
    playPageController.getGameEngine().pauseTimer();
    String formatTime = formatTime(playPageController.getGameEngine().getTimeCount());
    timeText.setText(formatTime);
  }

  private String formatTime(long timeCount) {
    long hours = (timeCount % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    long minutes = (timeCount % (1000 * 60 * 60)) / (1000 * 60);
    long seconds = (timeCount % (1000 * 60)) / 1000;
    return hours + " hr " + minutes + " min " + seconds + " sec ";
  }
}
