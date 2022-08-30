package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.BeginnerGuidanceController;
import com.ae2dms.sokobanfx.controller.StartPageController;
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
 * This class implements the MorePageEvent interface to handle all the events on the More page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class MorePageEventImpl implements MorePageEvent {
  private static MorePageEventImpl instance = new MorePageEventImpl();

  private MorePageEventImpl() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of MorePageEventImpl object.
   */
  public static MorePageEventImpl getInstance() {
    return instance;
  }
  /**
   * This method handles the display of Rule page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void ruleButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/RulePage.fxml"));
      DialogPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Rule");

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

  /**
   * This method handles the display of Beginner Guidance page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void guidanceButton(Stage primaryStage) {
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/BeginnerGuidance.fxml"));
      AnchorPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.initOwner(primaryStage);
      stage.setTitle("Guidance");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      stage.getIcons().add(new Image(logoPath));

      stage.setScene(new Scene(root));
      BeginnerGuidanceController beginnerGuidanceController = loader.getController();
      beginnerGuidanceController.initialize(primaryStage, stage);
      // Prevent user from zooming in
      stage.setResizable(false);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method handles the display of Support page.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void supportButton(Stage primaryStage) {

    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/SupportPage.fxml"));
      DialogPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Support");

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

  /** This method exits the game. */
  @Override
  public void exitButton() {
    System.exit(0);
  }

  /**
   * This method handles the event of back button, redirect to the main menu.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void backButton(Stage primaryStage) {
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
}
