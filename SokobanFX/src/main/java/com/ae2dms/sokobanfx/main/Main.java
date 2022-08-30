package com.ae2dms.sokobanfx.main;

import com.ae2dms.sokobanfx.controller.StartPageController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The Sokoban program implements a game that players need to push the boxes to the target using the
 * fewest steps and time. Have fun and enjoy!
 *
 * <p>The entry point of the game. This class launch a application window.
 *
 * @see Application
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class Main extends Application {

  /**
   * com.ae2dms.Main method reads the Application and then invokes start.
   *
   * @param args Arguments for the main function.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This method loads the window, includes the menu and primary stage.
   *
   * <p>File includes Save Game, Load Game, Exit.
   *
   * <p>Level includes Undo, Toggle Music, Toggle Debug, Reset Level.
   *
   * <p>About includes About This Game.
   *
   * @param primaryStage As the primary stage to set the window.
   */
  @Override
  public void start(Stage primaryStage) {
    FileManager fileManager = FileManager.getInstance();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("ui/StartPage.fxml"));
    try {
      AnchorPane root = loader.load();
      primaryStage.setTitle(GameEngine.GAME_NAME);
      primaryStage.setScene(new Scene(root));
      primaryStage.setResizable(false);
      primaryStage.show();
      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      primaryStage.getIcons().add(new Image(logoPath));
      StartPageController startPageController = loader.getController();
      startPageController.initialize(primaryStage);
      fileManager.initializeDirectory();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
