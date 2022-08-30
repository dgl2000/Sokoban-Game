package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PauseDialogController;
import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
/**
 * This class handles the function key.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class FunctionKeyHandler extends KeyHandler {
  private final GameEngine gameEngine;
  private final Stage primaryStage;
  private final PlayPageController playPageController;

  /**
   * This constructor initializes the function key handler.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  public FunctionKeyHandler(PlayPageController playPageController) {
    this.playPageController = playPageController;
    this.gameEngine = playPageController.getGameEngine();
    this.primaryStage = playPageController.getPrimaryStage();
  }

  /** This methods implements the feature of function key. */
  @Override
  public void handleKey() {
    playPageController.pauseDisplayTimer();
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/PauseDialog.fxml"));
      DialogPane root = loader.load();
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Pause");
      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));
      PauseDialogController pauseDialogController = loader.getController();
      pauseDialogController.initialize(playPageController);
      dialog.setOnCloseRequest(
          event -> {
            gameEngine.continueTimer();
            playPageController.continueDisplayTimer();
          });
      dialog.setScene(new Scene(root));
      dialog.setResizable(false);
      dialog.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
