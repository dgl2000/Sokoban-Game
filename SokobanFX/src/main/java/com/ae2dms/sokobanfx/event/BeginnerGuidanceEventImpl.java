package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.BeginnerGuidanceController;
import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
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
public class BeginnerGuidanceEventImpl implements BeginnerGuidanceEvent {
  private static BeginnerGuidanceEventImpl instance = new BeginnerGuidanceEventImpl();
  GridManager gridManager = GridManager.getInstance();
  FileManager fileManager = FileManager.getInstance();
  private Stage primaryStage;
  private Stage stage;
  private MediaView mediaView;
  private ImageView imageView;
  private Button leftButton;
  private Button rightButton;
  private Text text;
  private int index = 0;

  private BeginnerGuidanceEventImpl() {}
  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of BeginnerGuidanceEventImpl object.
   */
  public static BeginnerGuidanceEventImpl getInstance() {
    return instance;
  }

  /** This is the setter for setting the index */
  public void setIndex() {
    index = 0;
  }
  /**
   * This method initialize the Beginner Guidance page.
   *
   * @param primaryStage The primary stage that displays the game.
   * @param stage The stage that displays the displays the pop-ups window.
   * @param beginnerGuidanceController An {@link BeginnerGuidanceController} object that will be
   *     used to set the attribute of the button on the page.
   */
  @Override
  public void initialize(
      Stage primaryStage, Stage stage, BeginnerGuidanceController beginnerGuidanceController) {
    this.stage = stage;
    this.primaryStage = primaryStage;
    this.mediaView = beginnerGuidanceController.getMediaView();
    this.imageView = beginnerGuidanceController.getImageView();
    this.leftButton = beginnerGuidanceController.getLeftButton();
    this.rightButton = beginnerGuidanceController.getRightButton();
    this.text = beginnerGuidanceController.getText();
    leftButton.setVisible(false);
    rightButton.setVisible(false);
    mediaPlayer("video/Guidance.mp4");
  }

  /**
   * This method handles the click event of the button on the left side of the page.
   *
   * @param beginnerGuidanceController An {@link BeginnerGuidanceController} object that will be
   *     used to set the attribute of the button on the page.
   */
  @Override
  public void leftButton(BeginnerGuidanceController beginnerGuidanceController) {
    index--;
    rightButton.setVisible(true);
    imageView.setVisible(true);
    mediaView.setVisible(false);
    text.setVisible(true);
    if (index == 0) {
      rightButton.setVisible(false);
      imageView.setVisible(false);
      mediaView.setVisible(true);
      text.setVisible(false);
      initialize(primaryStage, stage, beginnerGuidanceController);
    } else {
      switchCase(index);
    }
  }

  private Image initializeImg(String imgPath) {
    String getImgPath =
        Objects.requireNonNull(this.getClass().getClassLoader().getResource(imgPath)).toString();
    return new Image(getImgPath);
  }
  /** This method handles the click event of the button on the right side of the page. */
  @Override
  public void rightButton() {
    index++;
    mediaView.setVisible(false);
    imageView.setVisible(true);
    text.setVisible(true);
    if (index < 10) {
      if (index == 1) {
        leftButton.setVisible(true);
      }
      switchCase(index);
    } else if (index == 10) {
      mediaView.setVisible(true);
      imageView.setVisible(false);
      text.setVisible(false);
      rightButton.setVisible(false);
      mediaPlayer("video/GuidanceEnd.mp4");
    } else if (index == 11) {
      stage.close();
      loadPlayPage();
    }
  }

  /**
   * This method switches different cases of displaying the page according to the index.
   *
   * @param index the index id of different situations
   */
  private void switchCase(int index) {
    if (index == 1) {
      Image image = initializeImg("guidance/introObject.png");
      imageView.setImage(image);
      text.setText(
          "These are different game objects. You are the keeper.\nYou need to use arrow keys to move the keeper!");
    } else if (index == 2) {
      Image image = initializeImg("guidance/moveDirection.png");
      imageView.setImage(image);
      text.setText(
          "Remember! Your target is the diamond, so press UP to push the crate into the diamond.\n");
    } else if (index == 3) {
      Image image = initializeImg("guidance/fileMenu.png");
      imageView.setImage(image);
      text.setText(
          "Oh wait! I forget to introduce the menu :D\nClick the File, you could save file, load file, return to the main menu or exit!");
    } else if (index == 4) {
      Image image = initializeImg("guidance/levelMenu.png");
      imageView.setImage(image);
      text.setText(
          "Click the Level, you could move back, toggle music, toggle debug or reset the level!");
    } else if (index == 5) {
      Image image = initializeImg("guidance/aboutMenu.png");
      imageView.setImage(image);
      text.setText("Click the About this Game, you could view developer's info!");

    } else if (index == 6) {
      Image image = initializeImg("guidance/settingMenu.png");
      imageView.setImage(image);
      text.setText(
          "Click the Setting, you could change the color theme or change the arrow keys to W A S D.");
    } else if (index == 7) {
      Image image = initializeImg("guidance/moveFinal.png");
      imageView.setImage(image);
      text.setText("Alright, keep moving up and you'll get the diamond!");
    } else if (index == 8) {
      Image image = initializeImg("guidance/victoryMessage.png");
      imageView.setImage(image);
      text.setText(
          "Hooray! You can see the victory message!\nShowing the moves and time you take to complete the game.");
    } else if (index == 9) {
      Image image = initializeImg("guidance/highScoreList.png");
      imageView.setImage(image);
      text.setText("Good! Close the message, you could view the current high score list~");
    }
  }

  /**
   * This method implements the media player for video play.
   *
   * @param filePath the file path fo the video
   */
  private void mediaPlayer(String filePath) {
    Media media =
        new Media(
            Objects.requireNonNull(this.getClass().getClassLoader().getResource(filePath))
                .toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setAutoPlay(true);
    mediaPlayer.setMute(true);
    mediaView.setMediaPlayer(mediaPlayer);
    mediaPlayer.setOnEndOfMedia(() -> rightButton.setVisible(true));
  }

  private void loadPlayPage() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("ui/PlayPage.fxml"));
    try {
      GridPane root = loader.load();
      primaryStage.setTitle(GameEngine.GAME_NAME);
      primaryStage.setScene(new Scene(root));
      primaryStage.setResizable(false);
      primaryStage.show();
      String logoPath =
          Objects.requireNonNull(this.getClass().getClassLoader().getResource("logo.png"))
              .toString();
      primaryStage.getIcons().add(new Image(logoPath));

      PlayPageController playPageController = loader.getController();
      gridManager.setBeginnerGuidance(true);
      gridManager.setPlayPageController(playPageController);
      fileManager.loadGuidanceFile(playPageController, primaryStage);
      playPageController.initializeTimer(0);
      playPageController.initializeMoveText(0);
      playPageController.hideMenuItem();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
