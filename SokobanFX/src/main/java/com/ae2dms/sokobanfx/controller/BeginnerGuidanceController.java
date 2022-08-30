package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.event.BeginnerGuidanceEventImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This class controls all the events on the Beginner Guidance page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class BeginnerGuidanceController {

  BeginnerGuidanceEventImpl beginnerGuidanceEvent = BeginnerGuidanceEventImpl.getInstance();
  @FXML private MediaView mediaView;
  @FXML private ImageView imageView;
  @FXML private Button leftButton;
  @FXML private Button rightButton;
  @FXML private Text text;

  /**
   * This method initialize the Beginner Guidance page.
   *
   * @param primaryStage The primary stage that displays the game.
   * @param stage The stage that displays the pop-ups window.
   */
  public void initialize(Stage primaryStage, Stage stage) {
    beginnerGuidanceEvent.setIndex();
    beginnerGuidanceEvent.initialize(primaryStage, stage, this);
  }
  /** This method handles the click event of the button on the left side of the page. */
  @FXML
  public void handleLeftButton() {
    beginnerGuidanceEvent.leftButton(this);
  }
  /** This method handles the click event of the button on the right side of the page. */
  @FXML
  public void handleRightButton() {
    beginnerGuidanceEvent.rightButton();
  }

  /**
   * This is the getter for getting the imageView object.
   *
   * @return The object which id is imageView in the FXML file.
   * @see ImageView
   */
  public ImageView getImageView() {
    return imageView;
  }

  /**
   * This is the getter for getting the mediaView object.
   *
   * @return The {@link MediaView} object which id is mediaView in the FXML file.
   */
  public MediaView getMediaView() {
    return mediaView;
  }

  /**
   * This is the getter for getting the leftButton object.
   *
   * @return The {@link Button} object which id is leftButton in the FXML file.
   */
  public Button getLeftButton() {
    return leftButton;
  }

  /**
   * This is the getter for getting the rightButton object.
   *
   * @return The {@link Button} object which id is rightButton in the FXML file.
   */
  public Button getRightButton() {
    return rightButton;
  }

  /**
   * This is the getter for getting the text object.
   *
   * @return The {@link Text} object which id is text in the FXML file.
   */
  public Text getText() {
    return text;
  }
}
