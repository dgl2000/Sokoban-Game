package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.stage.Stage;

import java.awt.*;

/**
 * This class is a factory of key event.
 *
 * <p>Objects include wall, floor, crate, diamond, keeper.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class KeyHandlerFactory {
  private final GameEngine gameEngine;
  private final Stage primaryStage;
  private final PlayPageController playPageController;

  /**
   * This constructor initializes the KeyHandlerFactory.
   *
   * @param playPageController The {@link PlayPageController} object.
   */
  public KeyHandlerFactory(PlayPageController playPageController) {
    this.gameEngine = playPageController.getGameEngine();
    this.primaryStage = playPageController.getPrimaryStage();
    this.playPageController = playPageController;
  }

  /**
   * The factory to generates different KeyHandler objects.
   *
   * @param type The key code type.
   * @return The KeyHandler corresponding to the key code.
   */
  public KeyHandler getKey(String type) {
    if (type == null) {
      System.out.println("Key type missing");
      return null;
    } else if ("UP".equalsIgnoreCase(type)) {
      return new MoveKeyHandler(new Point(-1, 0), playPageController);
    } else if ("RIGHT".equalsIgnoreCase(type)) {
      return new MoveKeyHandler(new Point(0, 1), playPageController);
    } else if ("DOWN".equalsIgnoreCase(type)) {
      return new MoveKeyHandler(new Point(1, 0), playPageController);
    } else if ("LEFT".equalsIgnoreCase(type)) {
      return new MoveKeyHandler(new Point(0, -1), playPageController);
    } else if ("SPACE".equalsIgnoreCase(type)) {
      return new FunctionKeyHandler(playPageController);
    } else {
      System.out.println("Key type not supported");
      return null;
    }
  }
}
