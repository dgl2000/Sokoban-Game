package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.AdvertisementEventImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * This class controls all the events on the Advertisement page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class AdvertisementController {
  AdvertisementEventImpl advertisementEvent = AdvertisementEventImpl.getInstance();
  private Stage primaryStage;
  private Stage dialog;
  @FXML private MediaView mediaView;
  @FXML private Button continueButton;

  /**
   * This method initializes the page, sets the mediaPlayer to play video.
   *
   * @param primaryStage The primary stage that displays the game.
   * @param dialog The {@link Stage} that displays the pop-ups window.
   */
  public void initialize(Stage dialog, Stage primaryStage) {
    this.dialog = dialog;
    this.primaryStage = primaryStage;
    advertisementEvent.initialize(this);
  }

  /** This method handles the event of back button. */
  @FXML
  public void handleBackButton() {
    dialog.close();
    advertisementEvent.backButton(primaryStage);
  }

  /** This method handles the event of continue button. */
  @FXML
  public void handleContinueButton() {
    dialog.close();
    advertisementEvent.continueButton();
  }

  /**
   * This is the getter for getting the mediaView object.
   *
   * @return The object which id is mediaView in the FXML file.
   * @see MediaView
   */
  public MediaView getMediaView() {
    return mediaView;
  }

  /**
   * This is the getter for getting the continueButton object.
   *
   * @return The object which id is continueButton in the FXML file.
   * @see Button
   */
  public Button getContinueButton() {
    return continueButton;
  }
}
