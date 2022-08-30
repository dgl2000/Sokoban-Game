package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.model.GameGrid;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;

import java.awt.*;
import java.util.LinkedList;

/**
 * This class handles interaction between player's key input and movement of game objects.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class MoveKeyHandler extends KeyHandler {
  private final GameEngine gameEngine;
  private final Point delta;

  private final PlayPageController playPageController;
  GridManager gridManager = GridManager.getInstance();
  /**
   * This constructor initializes the move key handler.
   *
   * @param point The move {@link Point} of the keeper.
   * @param playPageController The PlayPageController object that handles the event on Play page.
   */
  public MoveKeyHandler(Point point, PlayPageController playPageController) {
    delta = point;
    this.playPageController = playPageController;
    this.gameEngine = playPageController.getGameEngine();
  }
  /** This methods implements the feature of move key. */
  @Override
  public void handleKey() {
    if (gameEngine.isGameComplete()) {
      return;
    }
    LinkedList<Point> pointLinkedList = gameEngine.getCurrentLevel().getPointLinkedList();
    LinkedList<GameObject> gameObjectLinkedList =
        gameEngine.getCurrentLevel().getGameObjectLinkedList();
    Point keeperPosition = gameEngine.getCurrentLevel().getKeeperPosition();
    GameObject keeper = gameEngine.getCurrentLevel().objectsGrid.getGameObjectAt(keeperPosition);
    Point targetObjectPoint = GameGrid.translatePoint(keeperPosition, delta);
    GameObject keeperTarget =
        gameEngine.getCurrentLevel().objectsGrid.getGameObjectAt(targetObjectPoint);

    isDebugActive(keeperPosition, keeper, keeperTarget, targetObjectPoint);
    boolean keeperMoved = moveKeeper(keeperPosition, keeper, targetObjectPoint, keeperTarget);
    if (keeperMoved) {
      pointLinkedList.add(delta);
      gameObjectLinkedList.add(keeperTarget);
      keeperPosition.translate((int) delta.getX(), (int) delta.getY());
      gameEngine.setMovesCount(gameEngine.getMovesCount() + 1);
      playPageController.setMoveText();
      if (gameEngine.getCurrentLevel().isComplete()) {
        if (gridManager.getLevelChoose()) {
          gameEngine.setGameComplete(true);
        }
        if (GameEngine.isDebugActive()) {
          System.out.println("Level complete!");
        }
        gameEngine.setCurrentLevel(gameEngine.getNextLevel());
      }
    }
  }

  private boolean moveKeeper(
      Point keeperPosition, GameObject keeper, Point targetObjectPoint, GameObject keeperTarget) {

    boolean keeperMoved = false;

    if (keeperTarget != GameObject.WALL) {
      if (keeperTarget == GameObject.CRATE) {
        GameObject crateTarget =
            gameEngine.getCurrentLevel().getTargetObject(targetObjectPoint, delta);
        if (crateTarget == GameObject.FLOOR) {
          gameEngine
              .getCurrentLevel()
              .objectsGrid
              .putGameObjectAt(
                  gameEngine
                      .getCurrentLevel()
                      .objectsGrid
                      .getGameObjectAt(GameGrid.translatePoint(targetObjectPoint, delta)),
                  targetObjectPoint);
          gameEngine
              .getCurrentLevel()
              .objectsGrid
              .putGameObjectAt(keeperTarget, GameGrid.translatePoint(targetObjectPoint, delta));
          gameEngine
              .getCurrentLevel()
              .objectsGrid
              .putGameObjectAt(
                  gameEngine
                      .getCurrentLevel()
                      .objectsGrid
                      .getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)),
                  keeperPosition);
          gameEngine
              .getCurrentLevel()
              .objectsGrid
              .putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
          keeperMoved = true;
        }
      } else if (keeperTarget == GameObject.FLOOR) {
        gameEngine
            .getCurrentLevel()
            .objectsGrid
            .putGameObjectAt(
                gameEngine
                    .getCurrentLevel()
                    .objectsGrid
                    .getGameObjectAt(GameGrid.translatePoint(keeperPosition, delta)),
                keeperPosition);
        gameEngine
            .getCurrentLevel()
            .objectsGrid
            .putGameObjectAt(keeper, GameGrid.translatePoint(keeperPosition, delta));
        keeperMoved = true;
      } else {
        GameEngine.logger.severe("The object to be moved was not a recognised GameObject.");
        throw new AssertionError(
            "This should not have happened. Report this problem to the developer.");
      }
    }
    return keeperMoved;
  }

  /**
   * This method prints the detail of object position in the output window if debug mode turns on.
   *
   * @param keeperPosition The position of the Keeper.
   * @param keeper A {@link GameObject} of the Keeper.
   * @param keeperTarget A {@link GameObject} of the target keeper.
   * @param targetObjectPoint The position {@link Point} of the target object.
   */
  private void isDebugActive(
      Point keeperPosition, GameObject keeper, GameObject keeperTarget, Point targetObjectPoint) {
    if (GameEngine.isDebugActive()) {
      System.out.println("Current level state:");
      System.out.println(gameEngine.getCurrentLevel().toStringObjectGrid());
      System.out.println("Keeper pos: " + keeperPosition);
      System.out.println("Movement source obj: " + keeper);
      System.out.println("Target object: " + keeperTarget + " at [" + targetObjectPoint + "]");
    }
  }
}
