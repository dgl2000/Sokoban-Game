package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.controller.SavaFilePageController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.model.Level;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class handles the save game click event.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class SaveGameHandler {
  private GameEngine gameEngine;
  /**
   * This method opens a window and stores the game file at the place chosen by the player.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  public void saveGame(PlayPageController playPageController) {
    playPageController.pauseDisplayTimer();
    gameEngine = playPageController.getGameEngine();
    gameEngine.pauseTimer();
    Stage primaryStage = playPageController.getPrimaryStage();
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/SaveFilePage.fxml"));
      AnchorPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Save");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));

      SavaFilePageController savaFilePageController = loader.getController();
      dialog.setScene(new Scene(root));
      // Prevent user from zooming in
      dialog.setResizable(false);
      dialog.show();
      StringBuilder gameText = gameFileContent();
      savaFilePageController.initialize(dialog, gameText, playPageController);
      dialog.setOnCloseRequest(
          event -> {
            playPageController.continueDisplayTimer();
            playPageController.getGameEngine().continueTimer();
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /**
   * This method auto saves the game state into a file.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   * @return The {@link ScheduledExecutorService} object that runs the autoSave.
   * @see ScheduledExecutorService
   */
  public ScheduledExecutorService autoSave(PlayPageController playPageController) {
    gameEngine = playPageController.getGameEngine();
    FileManager fileManager = FileManager.getInstance();
    String filePath = "/AutoSave.skb";
    ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(1);
    mScheduledExecutorService.scheduleAtFixedRate(
        () -> {
          StringBuilder gameText = gameFileContent();
          fileManager.saveFile(filePath, gameText);
        },
        1,
        30,
        TimeUnit.SECONDS);
    return mScheduledExecutorService;
  }

  /**
   * This method combines the objectsGrid and diamondGrids.
   *
   * @return The combined game text.
   * @see StringBuilder
   */
  private StringBuilder gameFileContent() {
    StringBuilder gameText = new StringBuilder();
    gameText.append("MapSetName: ").append(gameEngine.mapSetName).append("\n");
    gameText.append("MovesCount: ").append(gameEngine.getMovesCount()).append("\n");
    gameText.append("TimeCount: ").append(gameEngine.getTimeCount()).append("\n");
    List<Level> levels = gameEngine.getLevels();
    for (Level level : levels) {
      String combGrid = level.toStringCombGrid();
      String levelName = level.getName();
      gameText.append("LevelName: ").append(levelName).append("\n");
      gameText.append(combGrid).append("\n");
    }
    return gameText;
  }
}
