package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.LevelChoosePageController;
import com.ae2dms.sokobanfx.controller.ModeChooseController;
import com.ae2dms.sokobanfx.controller.MorePageController;
import com.ae2dms.sokobanfx.controller.RankingDialogController;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class implements the Start Page interface to handle all the events on the Start Page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class StartPageEventImpl implements StartPageEvent {
  private static StartPageEventImpl instance = new StartPageEventImpl();

  private StartPageEventImpl() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of StartPageEventImpl object.
   */
  public static StartPageEventImpl getInstance() {
    return instance;
  }

  /**
   * This methods handles the event of start button, redirect to the play page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void startButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/ModeChoose.fxml"));
      AnchorPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Mode");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));

      ModeChooseController modeChooseController = loader.getController();
      modeChooseController.initialize(dialog, primaryStage);
      dialog.setScene(new Scene(root));
      // Prevent user from zooming in
      dialog.setResizable(false);
      dialog.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles the event of level button, redirect to the level page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void levelButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/LevelChoosePage.fxml"));
      AnchorPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Level");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));

      LevelChoosePageController levelChoosePageController = loader.getController();
      levelChoosePageController.initialize(dialog, primaryStage);
      dialog.setScene(new Scene(root));
      // Prevent user from zooming in
      dialog.setResizable(false);
      dialog.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles the event of more button, redirect to the more page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void moreButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/MorePage.fxml"));
      AnchorPane root = loader.load();
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

      MorePageController morePageController = loader.getController();
      morePageController.initialize(primaryStage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles the event of rank button, redirect to the rank page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void rankButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/RankingPage.fxml"));
      DialogPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Ranking");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));

      RankingDialogController rankingPageController = loader.getController();
      rankingPageController.initialize();
      dialog.setScene(new Scene(root));
      // Prevent user from zooming in
      dialog.setResizable(false);
      dialog.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles the event of info button, redirect to the info page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void infoButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/InfoPage.fxml"));
      DialogPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Info");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));
      dialog.setScene(new Scene(root));
      // Prevent user from zooming in
      dialog.setResizable(false);
      dialog.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
