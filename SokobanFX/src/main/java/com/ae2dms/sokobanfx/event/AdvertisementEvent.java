package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.AdvertisementController;
import javafx.stage.Stage;
/**
 * This interface is a skeleton of all the events on the Advertisement page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface AdvertisementEvent {

  /**
   * This method initializes the page, sets the mediaPlayer to play video.
   *
   * @param advertisementController An {@link AdvertisementController} object that will be used to
   *     set the attribute of the button on the page.
   */
  void initialize(AdvertisementController advertisementController);
  /**
   * This method handles the event of back button.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  void backButton(Stage primaryStage);

  /** This method handles the event of continue button. */
  void continueButton();
}
