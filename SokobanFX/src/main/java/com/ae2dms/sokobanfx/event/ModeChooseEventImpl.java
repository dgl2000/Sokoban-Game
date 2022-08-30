package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class implements the Mode choose interface to handle all the events on the Mode Choose page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class ModeChooseEventImpl implements ModeChooseEvent {

  private static ModeChooseEventImpl instance = new ModeChooseEventImpl();
  FileManager fileManager = FileManager.getInstance();
  GridManager gridManager = GridManager.getInstance();
  private PlayPageController playPageController;

  private ModeChooseEventImpl() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of ModeChooseEventImpl object.
   */
  public static ModeChooseEventImpl getInstance() {
    return instance;
  }

  /**
   * This method handles the display of classic mode game window.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void classicMode(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("ui/PlayPage.fxml"));
    try {
      GridPane root = loader.load();
      // Set the application window's text in the title bar.
      primaryStage.setTitle(GameEngine.GAME_NAME);
      primaryStage.setScene(new Scene(root));
      // Prevent user from zooming in
      primaryStage.setResizable(false);
      primaryStage.show();
      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      primaryStage.getIcons().add(new Image(logoPath));
      playPageController = loader.getController();
      gridManager.setPlayPageController(playPageController);
      fileManager.loadDefaultSaveFile(playPageController, primaryStage);
      playPageController.initializeTimer(0);
      playPageController.initializeMoveText(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles the display of challenge mode game window.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void challengeMode(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("ui/PlayPage.fxml"));
    try {
      GridPane root = loader.load();
      // Set the application window's text in the title bar.
      primaryStage.setTitle(GameEngine.GAME_NAME);
      primaryStage.setScene(new Scene(root));
      // Prevent user from zooming in
      primaryStage.setResizable(false);
      primaryStage.show();
      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      primaryStage.getIcons().add(new Image(logoPath));
      playPageController = loader.getController();
      gridManager.setChallengeMode(true);
      gridManager.setPlayPageController(playPageController);
      fileManager.loadDefaultSaveFile(playPageController, primaryStage);
      playPageController.initializeTimer(360000);
      playPageController.initializeMoveText(1000);
      playPageController.hideMenuItem();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
