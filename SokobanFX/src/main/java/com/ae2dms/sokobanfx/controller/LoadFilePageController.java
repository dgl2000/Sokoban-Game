package com.ae2dms.sokobanfx.controller;

import com.ae2dms.sokobanfx.manager.FileManager;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 * This class controls all the events on the Load File page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class LoadFilePageController {
  FileManager fileManager = FileManager.getInstance();
  private PlayPageController playPageController;
  private Stage dialog;

  /**
   * This method initializes the load file controller.
   *
   * @param playPageController The PlayPageController object that handles the event on Play page.
   * @param dialog The stage that displays the displays the pop-ups window.
   */
  public void initialize(PlayPageController playPageController, Stage dialog) {
    this.playPageController = playPageController;
    this.dialog = dialog;
  }

  /** This method handles the first progress's loading. */
  @FXML
  public void handleProgress1() {
    handleLoadFile("/Progress1.skb");
  }

  /** This method handles the second progress's loading. */
  @FXML
  public void handleProgress2() {
    handleLoadFile("/Progress2.skb");
  }

  /** This method handles the third progress's loading. */
  @FXML
  public void handleProgress3() {
    handleLoadFile("/Progress3.skb");
  }

  /** This method handles the auto saved progress's loading. */
  @FXML
  public void handleAutoSave() {
    handleLoadFile("/AutoSave.skb");
  }

  private void handleLoadFile(String relativePath) {
    dialog.close();
    fileManager.loadGameFile(playPageController.getPrimaryStage(), relativePath);
  }
}
