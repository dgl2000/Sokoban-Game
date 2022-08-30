package com.ae2dms.sokobanfx.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class opens a dialog window and shows prompt to players.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameDialog {

  /**
   * This method creates/pop-ups some dialogs for the game.
   *
   * @param dialogTitle Gives the title of the dialog.
   * @param dialogMessage Gives the message of the dialog.
   * @param dialogMessageEffect Sets special effect for the dialog.
   * @param primaryStage The primary stage that displays the game.
   */
  public static void newDialog(
      String dialogTitle, String dialogMessage, Effect dialogMessageEffect, Stage primaryStage) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    dialog.setResizable(false);
    dialog.setTitle(dialogTitle);

    Text text1 = new Text(dialogMessage);
    text1.setTextAlignment(TextAlignment.CENTER);
    text1.setFont(javafx.scene.text.Font.font("Arial", 14));

    if (dialogMessageEffect != null) {
      text1.setEffect(dialogMessageEffect);
    }

    VBox dialogVbox = new VBox(20);
    dialogVbox.setAlignment(Pos.CENTER);
    dialogVbox.setBackground(Background.EMPTY);
    dialogVbox.getChildren().add(text1);

    Scene dialogScene = new Scene(dialogVbox, 350, 150);
    dialog.setScene(dialogScene);
    dialog.show();
  }
}
