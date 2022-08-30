package com.ae2dms.sokobanfx.model;

import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class creates the level of the game and handles operations related to level. Place the
 * {@link GameObject} in a 2D array.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public final class Level implements Iterable<GameObject> {
  /** This final GameGrid variable objectsGrid keeps the grid of the object. */
  public final GameGrid objectsGrid;
  /** This final GameGrid variable diamondsGrid keeps the grid of the diamond. */
  public final GameGrid diamondsGrid;
  /** This final String variable name stores the name of the game level. */
  private final String name;
  /** This final int variable index stores the index of the game level. */
  private final int index;

  private final LinkedList<Point> pointLinkedList;
  private final LinkedList<GameObject> gameObjectLinkedList;
  private int numberOfDiamonds = 0;
  private Point keeperPosition = new Point(0, 0);

  /**
   * This method handles the creation of the {@link Level} and place the gameObjects in a 2D array.
   *
   * @param levelName The name of this level.
   * @param levelIndex The index of this level.
   * @param rawLevel A list stores a string of this game level.
   */
  public Level(String levelName, int levelIndex, List<String> rawLevel) {
    if (GameEngine.isDebugActive()) {
      System.out.printf("[ADDING LEVEL] LEVEL [%d]: %s\n", levelIndex, levelName);
    }

    pointLinkedList = new LinkedList<>();
    gameObjectLinkedList = new LinkedList<>();
    name = levelName;
    index = levelIndex;

    int rows = rawLevel.size();
    int columns = rawLevel.get(0).trim().length();

    objectsGrid = new GameGrid(rows, columns);
    diamondsGrid = new GameGrid(rows, columns);

    for (int row = 0; row < rawLevel.size(); row++) {

      for (int col = 0; col < rawLevel.get(row).length(); col++) {
        GameObject curTile = GameObject.fromChar(rawLevel.get(row).charAt(col));

        if (curTile == GameObject.DIAMOND) {
          numberOfDiamonds++;
          diamondsGrid.putGameObjectAt(curTile, row, col);
          curTile = GameObject.FLOOR;
        } else if (curTile == GameObject.KEEPER) {
          keeperPosition = new Point(row, col);
        } else if (curTile == GameObject.CRATE_ON_DIAMOND) {
          numberOfDiamonds++;
          diamondsGrid.putGameObjectAt(GameObject.DIAMOND, row, col);
          curTile = GameObject.CRATE;
        } else if (curTile == GameObject.DEBUG_OBJECT) {
          keeperPosition = new Point(row, col);
          numberOfDiamonds++;
          diamondsGrid.putGameObjectAt(GameObject.DIAMOND, row, col);
          curTile = GameObject.KEEPER;
        }
        objectsGrid.putGameObjectAt(curTile, row, col);
        curTile = null;
      }
    }
  }

  /**
   * This method checks whether the game is completed.
   *
   * @return Whether this level is completed.
   */
  public boolean isComplete() {
    int cratedDiamondsCount = 0;
    for (int row = 0; row < objectsGrid.ROWS; row++) {
      for (int col = 0; col < objectsGrid.COLUMNS; col++) {
        if (objectsGrid.getGameObjectAt(col, row) == GameObject.CRATE
            && diamondsGrid.getGameObjectAt(col, row) == GameObject.DIAMOND) {
          cratedDiamondsCount++;
        }
      }
    }
    return cratedDiamondsCount >= numberOfDiamonds;
  }

  /**
   * This is the getter for getting the name of the current level.
   *
   * @return The name of this level.
   */
  public String getName() {
    return name;
  }

  /**
   * This is the getter for getting the index of the current level.
   *
   * @return The index of this level.
   */
  public int getIndex() {
    return index;
  }

  /**
   * This is the getter for getting the position of the Keeper.
   *
   * @return The position of the Keeper
   */
  public Point getKeeperPosition() {
    return keeperPosition;
  }

  /**
   * This method returns the target {@link GameObject} from the source.
   *
   * @param source A source point.
   * @param delta A point of the distance from the source point.
   * @return The target game object that is at the distance from the source point.
   */
  public GameObject getTargetObject(Point source, Point delta) {
    return objectsGrid.getTargetFromSource(source, delta);
  }

  /**
   * The method translates the objectsGrid to {@link String}.
   *
   * @return The {@link String} of objectGrid array.
   */
  public String toStringObjectGrid() {
    return objectsGrid.toString();
  }

  /**
   * This method translates the diamondGrid to {@link String}.
   *
   * @return The {@link String} of diamondGrid array.
   */
  public String toStringDiamondGrid() {
    return diamondsGrid.toString();
  }

  /**
   * This method translates the combined grid to {@link String}.
   *
   * @return A {@link String} of combined grid.
   */
  public String toStringCombGrid() {
    GameGrid combGrid = combineObjectDiamondGrid();
    return combGrid.toString();
  }

  /**
   * This method combines objectsGrid and diamondsGrid.
   *
   * @return A {@link GameGrid} combined objectsGrid and diamondsGrid
   */
  private GameGrid combineObjectDiamondGrid() {

    GameGrid combGrid = new GameGrid(objectsGrid.COLUMNS, objectsGrid.ROWS);

    for (int row = 0; row < objectsGrid.ROWS; row++) {

      for (int col = 0; col < objectsGrid.COLUMNS; col++) {
        GameObject diamondGridObject = diamondsGrid.getGameObjectAt(col, row);
        GameObject objectGridObject = objectsGrid.getGameObjectAt(col, row);
        if (diamondGridObject == GameObject.DIAMOND) {
          if (objectGridObject == GameObject.CRATE) {
            combGrid.putGameObjectAt(GameObject.CRATE_ON_DIAMOND, col, row);
          } else if (objectGridObject == GameObject.FLOOR) {
            combGrid.putGameObjectAt(GameObject.DIAMOND, col, row);
          }
        } else {
          combGrid.putGameObjectAt(objectGridObject, col, row);
        }
      }
    }
    return combGrid;
  }

  /**
   * This overridden method returns a new {@link Iterator} object.
   *
   * @return GameObject Iterator.
   * @see Iterator
   */
  @Override
  public Iterator<GameObject> iterator() {
    return new LevelIterator();
  }
  /**
   * This is the getter for getting the {@link Point} type's {@link LinkedList}.
   *
   * @return The Point Linked List that contain the points.
   * @see LinkedList
   */
  public LinkedList<Point> getPointLinkedList() {
    return pointLinkedList;
  }
  /**
   * This is the getter for getting the {@link GameObject} type's {@link LinkedList}.
   *
   * @return The Game Object Linked List that contain the game object. * @see LinkedList
   */
  public LinkedList<GameObject> getGameObjectLinkedList() {
    return gameObjectLinkedList;
  }

  /** This class iterates new level. */
  public class LevelIterator implements Iterator<GameObject> {

    int column = 0;
    int row = 0;

    /**
     * This method returns whether this level has been fully loaded.
     *
     * @return Whether the this level has fully loaded.
     */
    @Override
    public boolean hasNext() {
      return !(row == objectsGrid.ROWS - 1 && column == objectsGrid.COLUMNS);
    }

    /**
     * This method returns the next {@link GameObject}.
     *
     * @return The next {@link GameObject}.
     */
    @Override
    public GameObject next() {
      if (column >= objectsGrid.COLUMNS) {
        column = 0;
        row++;
      }
      GameObject object = objectsGrid.getGameObjectAt(column, row);
      GameObject diamond = diamondsGrid.getGameObjectAt(column, row);
      GameObject retObj = object;
      column++;
      if (diamond == GameObject.DIAMOND) {
        if (object == GameObject.CRATE) {
          retObj = GameObject.CRATE_ON_DIAMOND;
        } else if (object == GameObject.FLOOR) {
          retObj = diamond;
        }
      }
      return retObj;
    }

    /**
     * This is the getter for getting the current position.
     *
     * @return The current position.
     * @see Point
     */
    public Point getCurrentPosition() {
      return new Point(column, row);
    }
  }
}
