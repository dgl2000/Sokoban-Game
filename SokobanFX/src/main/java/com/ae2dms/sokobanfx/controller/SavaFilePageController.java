package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.view.GameDialog;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 * This class controls all the events on the Save File page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class SavaFilePageController {
  FileManager fileManager = FileManager.getInstance();
  private Stage dialog;
  private StringBuilder gameText;
  private PlayPageController playPageController;
  /**
   * This method initializes the save file controller.
   *
   * @param gameText The text of the game file.
   * @param playPageController The PlayPageController object that handles the event on Play page.
   * @param dialog The stage that displays the displays the pop-ups window.
   */
  public void initialize(
      Stage dialog, StringBuilder gameText, PlayPageController playPageController) {
    this.dialog = dialog;
    this.gameText = gameText;
    this.playPageController = playPageController;
  }

  /** This method handles the file save of position 3. */
  @FXML
  public void handlePosition3() {
    handleSaveFile("/Progress3.skb");
  }

  /** This method handles the file save of position 2. */
  @FXML
  public void handlePosition2() {
    handleSaveFile("/Progress2.skb");
  }

  /** This method handles the file save of position 1. */
  @FXML
  public void handlePosition1() {
    handleSaveFile("/Progress1.skb");
  }

  private void handleSaveFile(String relativeFilePath) {
    alertDialog(fileManager.saveFile(relativeFilePath, gameText));
    dialog.close();
    playPageController.continueDisplayTimer();
    playPageController.getGameEngine().continueTimer();
  }

  private void alertDialog(boolean state) {
    String dialogTitle;
    String dialogMessage;
    if (state) {
      dialogTitle = "Success";
      dialogMessage = "[ Save Successfully ! ]\n";
    } else {
      dialogTitle = "Fail";
      dialogMessage = "[ Oops...Save fail! Try again! ]\n";
    }
    GameDialog.newDialog(dialogTitle, dialogMessage, null, playPageController.getPrimaryStage());
  }
}
