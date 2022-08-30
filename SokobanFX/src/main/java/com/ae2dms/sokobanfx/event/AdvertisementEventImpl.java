package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.AdvertisementController;
import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.controller.StartPageController;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class implements the AdvertisementEvent interface to handle all the events on the
 * Advertisement page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class AdvertisementEventImpl implements AdvertisementEvent {
  private static AdvertisementEventImpl instance = new AdvertisementEventImpl();
  GridManager gridManager = GridManager.getInstance();

  private AdvertisementEventImpl() {}
  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of AdvertisementEventImpl object.
   */
  public static AdvertisementEventImpl getInstance() {
    return instance;
  }

  /**
   * This method initializes the page, sets the mediaPlayer to play video.
   *
   * @param advertisementController An {@link AdvertisementController} object that will be used to
   *     set the attribute of the button on the page.
   */
  @Override
  public void initialize(AdvertisementController advertisementController) {
    advertisementController.getContinueButton().setVisible(false);
    Media media =
        new Media(
            Objects.requireNonNull(
                    this.getClass()
                        .getClassLoader()
                        .getResource("video/SVID_20201130_132101_1.mp4"))
                .toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
    advertisementController.getMediaView().setMediaPlayer(mediaPlayer);
    mediaPlayer.setOnEndOfMedia(() -> advertisementController.getContinueButton().setVisible(true));
  }

  /**
   * This method handles the event of back button.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void backButton(Stage primaryStage) {
    PlayPageController playPageController = gridManager.getPlayPageController();
    playPageController.stopDisplayTimer();
    gridManager.setIsSetLevel(false);
    gridManager.setLevelChoose(false);
    gridManager.setBeginnerGuidance(false);
    gridManager.setChallengeMode(false);
    gridManager.setIsFirstLoadGrid(true);
    gridManager.setAdvertisementPlayed(false);
    primaryStage.removeEventFilter(
        KeyEvent.KEY_PRESSED, playPageController.getKeyEventEventHandler());
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/StartPage.fxml"));
      AnchorPane root = loader.load();
      // Set the application window's text in the title bar.
      primaryStage.setTitle(GameEngine.GAME_NAME);
      primaryStage.setScene(new Scene(root));
      // Prevent user from zooming in
      primaryStage.setResizable(false);
      primaryStage.show();

      StartPageController startPageController = loader.getController();
      startPageController.initialize(primaryStage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** This method handles the event of continue button. */
  @Override
  public void continueButton() {
    gridManager.setAdvertisementPlayed(true);
    gridManager.getPlayPageController().initializeMoveText(500);
    gridManager.getPlayPageController().initializeTimer(120000);
    gridManager.getPlayPageController().continueDisplayTimer();
  }
}
