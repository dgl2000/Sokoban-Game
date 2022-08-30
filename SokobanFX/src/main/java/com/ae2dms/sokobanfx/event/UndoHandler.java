package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.model.GameGrid;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;
import com.ae2dms.sokobanfx.view.GameDialog;
import javafx.stage.Stage;

import java.awt.*;
import java.util.LinkedList;
/**
 * This class handles the undo click event.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class UndoHandler {

  private LinkedList<Point> pointLinkedList;
  private LinkedList<GameObject> gameObjectLinkedList;
  private GameEngine gameEngine;
  private Stage primaryStage;
  private PlayPageController playPageController;

  /**
   * This constructor initializes the UndoHandler object.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  public UndoHandler(PlayPageController playPageController) {
    this.gameEngine = playPageController.getGameEngine();
    this.primaryStage = playPageController.getPrimaryStage();
    this.playPageController = playPageController;
    gameObjectLinkedList = gameEngine.getCurrentLevel().getGameObjectLinkedList();
    pointLinkedList = gameEngine.getCurrentLevel().getPointLinkedList();
  }

  /** This method handles the undo feature. */
  public void handleUndo() {
    if (alertUndo()) {
      return;
    }
    gameEngine.setMovesCount(gameEngine.getMovesCount() + 1);
    playPageController.setMoveText();
    GameObject gameObject = gameObjectLinkedList.getLast();
    Point point = pointLinkedList.getLast();
    Point pointBeforeMove = new Point(-(int) point.getX(), -(int) point.getY());

    Point keeperPosition = gameEngine.getCurrentLevel().getKeeperPosition();
    GameObject keeper = gameEngine.getCurrentLevel().objectsGrid.getGameObjectAt(keeperPosition);
    Point targetObjectPoint = GameGrid.translatePoint(keeperPosition, point);
    GameObject keeperTarget =
        gameEngine.getCurrentLevel().objectsGrid.getGameObjectAt(targetObjectPoint);

    if (gameObject == GameObject.CRATE) {
      gameEngine
          .getCurrentLevel()
          .objectsGrid
          .putGameObjectAt(
              gameEngine
                  .getCurrentLevel()
                  .objectsGrid
                  .getGameObjectAt(GameGrid.translatePoint(keeperPosition, pointBeforeMove)),
              keeperPosition);
      gameEngine
          .getCurrentLevel()
          .objectsGrid
          .putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, pointBeforeMove));
      gameEngine
          .getCurrentLevel()
          .objectsGrid
          .putGameObjectAt(
              gameEngine
                  .getCurrentLevel()
                  .objectsGrid
                  .getGameObjectAt(GameGrid.translatePoint(targetObjectPoint, pointBeforeMove)),
              targetObjectPoint);
      gameEngine
          .getCurrentLevel()
          .objectsGrid
          .putGameObjectAt(
              keeperTarget, GameGrid.translatePoint(targetObjectPoint, pointBeforeMove));

    } else if (gameObject == GameObject.FLOOR) {
      gameEngine
          .getCurrentLevel()
          .objectsGrid
          .putGameObjectAt(
              gameEngine
                  .getCurrentLevel()
                  .objectsGrid
                  .getGameObjectAt(GameGrid.translatePoint(keeperPosition, pointBeforeMove)),
              keeperPosition);
      gameEngine
          .getCurrentLevel()
          .objectsGrid
          .putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, pointBeforeMove));
    }
    keeperPosition.translate((int) pointBeforeMove.getX(), (int) pointBeforeMove.getY());
    gameObjectLinkedList.removeLast();
    pointLinkedList.removeLast();
  }

  /**
   * This method opens a dialog to prompt the player when they can't undo.
   *
   * @return Whether shows the dialog
   */
  private Boolean alertUndo() {
    if (gameObjectLinkedList.isEmpty()) {
      String dialogTitle = "Warning";
      String dialogMessage = "[ Can't undo! Please continue the game :D ]\n\n";
      GameDialog.newDialog(dialogTitle, dialogMessage, null, primaryStage);
      return true;
    }
    return false;
  }
}
