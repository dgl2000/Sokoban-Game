package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.LoadFilePageController;
import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.controller.StartPageController;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.view.GameDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;

/**
 * This class implements the all the events on the game Play page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class PlayPageEventImpl implements PlayPageEvent {
  private static PlayPageEventImpl instance = new PlayPageEventImpl();
  GridManager gridManager = GridManager.getInstance();
  MusicHandler musicHandler = new MusicHandler();
  ResetLevelHandler resetLevelHandler = new ResetLevelHandler();
  SaveGameHandler saveGameHandler = new SaveGameHandler();

  private PlayPageEventImpl() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of PlayPageEventImpl object.
   */
  public static PlayPageEventImpl getInstance() {
    return instance;
  }

  /**
   * This methods handles the interaction between player's key input and game.
   *
   * @param code Player's press key.
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  @Override
  public void controlKey(KeyCode code, PlayPageController playPageController) {

    KeyHandlerFactory kf = new KeyHandlerFactory(playPageController);
    if (playPageController.getIsSwitchKey()) {
      if (code == KeyCode.W) {
        KeyHandler up = kf.getKey("UP");
        up.handleKey();
      } else if (code == KeyCode.S) {
        KeyHandler down = kf.getKey("DOWN");
        down.handleKey();
      } else if (code == KeyCode.D) {
        KeyHandler right = kf.getKey("RIGHT");
        right.handleKey();
      } else if (code == KeyCode.A) {
        KeyHandler left = kf.getKey("LEFT");
        left.handleKey();
      } else if (code == KeyCode.SPACE) {
        KeyHandler space = kf.getKey("SPACE");
        space.handleKey();
      }
    } else {
      if (code == KeyCode.UP) {
        KeyHandler up = kf.getKey("UP");
        up.handleKey();
      } else if (code == KeyCode.DOWN) {
        KeyHandler down = kf.getKey("DOWN");
        down.handleKey();
      } else if (code == KeyCode.RIGHT) {
        KeyHandler right = kf.getKey("RIGHT");
        right.handleKey();
      } else if (code == KeyCode.LEFT) {
        KeyHandler left = kf.getKey("LEFT");
        left.handleKey();
      } else if (code == KeyCode.SPACE) {
        KeyHandler space = kf.getKey("SPACE");
        space.handleKey();
      }
    }
  }

  /**
   * This method handles the redirect to the main menu.
   *
   * @param primaryStage The primary stage that displays the game.
   */
  @Override
  public void backMain(Stage primaryStage) {
    PlayPageController playPageController = gridManager.getPlayPageController();
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
  /**
   * This method loads a game file chose by player.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  @Override
  public void loadGame(PlayPageController playPageController) {
    playPageController.pauseDisplayTimer();
    playPageController.getGameEngine().pauseTimer();
    Stage primaryStage = playPageController.getPrimaryStage();
    FXMLLoader loader = new FXMLLoader();
    try {
      loader.setLocation(getClass().getClassLoader().getResource("ui/LoadFilePage.fxml"));
      AnchorPane root = loader.load();
      /* Set the application window's text in the title bar.*/
      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle("Load");

      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      dialog.getIcons().add(new Image(logoPath));

      LoadFilePageController loadFilePageController = loader.getController();
      dialog.setScene(new Scene(root));
      // Prevent user from zooming in
      dialog.setResizable(false);
      dialog.show();
      loadFilePageController.initialize(playPageController, dialog);
      dialog.setOnCloseRequest(
          event -> {
            playPageController.continueDisplayTimer();
            playPageController.getGameEngine().continueTimer();
          });

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** This method exits the game. */
  @Override
  public void closeGame() {
    System.exit(0);
  }

  /**
   * This method saves the current stage of the game.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  @Override
  public void saveGame(PlayPageController playPageController) {
    saveGameHandler.saveGame(playPageController);
  }

  /**
   * This method auto saves the current stage of the game.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   * @return The ScheduledExecutorService object that runs the autoSave.
   */
  @Override
  public ScheduledExecutorService autoSave(PlayPageController playPageController) {
    return saveGameHandler.autoSave(playPageController);
  }

  /**
   * This methods handles the step back of the movement.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  @Override
  public void undo(PlayPageController playPageController) {
    UndoHandler undoHandler = new UndoHandler(playPageController);
    undoHandler.handleUndo();
  }

  /**
   * This method handles the reset of the current level, restore to the original stage of this
   * level.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  @Override
  public void resetLevel(PlayPageController playPageController) {
    resetLevelHandler.resetLevel(playPageController);
  }

  /**
   * This method prompts a message including the info of the game.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  @Override
  public void showAbout(PlayPageController playPageController) {
    String title = "About this game";
    //    Modify the message 2020/10/24
    String message =
        "\n\n[ Game created by Gaole DAI (20124917)\n\n This is a coursework of student at UNNC\n\n If you have any question or suggestion :)\n\n Please contact via scygd1@nottingham.edu.cn ]\n\n";

    GameDialog.newDialog(title, message, null, playPageController.getPrimaryStage());
  }

  /** This method handles the music play. */
  @Override
  public void toggleMusic() {
    musicHandler.musicPlayer();
  }
}
