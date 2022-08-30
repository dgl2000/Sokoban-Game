package com.ae2dms.sokobanfx.model;

import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;

import java.awt.*;
import java.util.Iterator;

/**
 * This class generates the grid for the game.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameGrid implements Iterable {
  final int COLUMNS;
  final int ROWS;

  private GameObject[][] gameObjects;

  /**
   * This constructor initializes a 2D array.
   *
   * @param columns As the input column value when initialize array.
   * @param rows As the input row value when initialize array.
   */
  public GameGrid(int columns, int rows) {
    COLUMNS = columns;
    ROWS = rows;

    // Initialize the array
    gameObjects = new GameObject[COLUMNS][ROWS];
  }

  /**
   * This method moves the current location and returns the translated point.
   *
   * @param sourceLocation The initial location of the Point.
   * @param delta Used for translate.
   * @return A point after translate.
   */
  public static Point translatePoint(Point sourceLocation, Point delta) {
    Point translatedPoint = new Point(sourceLocation);
    translatedPoint.translate((int) delta.getX(), (int) delta.getY());
    return translatedPoint;
  }

  /**
   * This method returns the gameObject after translated.
   *
   * @param source The initial location of the Point.
   * @param delta Used for translate.
   * @return {@link GameObject} from the target source.
   */
  public GameObject getTargetFromSource(Point source, Point delta) {
    return getGameObjectAt(translatePoint(source, delta));
  }

  /**
   * This method returns the gameObject after translated.
   *
   * @param col The column location of the target object to get.
   * @param row The row location of the target object to get.
   * @return GameObject at target point.
   * @throws ArrayIndexOutOfBoundsException Throws when the array's index is out of bounds.
   */
  public GameObject getGameObjectAt(int col, int row) throws ArrayIndexOutOfBoundsException {
    if (isPointOutOfBounds(col, row)) {
      if (GameEngine.isDebugActive()) {
        System.out.printf("Trying to get null GameObject from COL: %d  ROW: %d", col, row);
      }
      throw new ArrayIndexOutOfBoundsException(
          "The point [" + col + ":" + row + "] is outside the map.");
    }

    return gameObjects[col][row];
  }

  /**
   * This method returns the coordinates of the object location.
   *
   * @param p The location of the target object to get.
   * @return The Game Object at the given position.
   */
  public GameObject getGameObjectAt(Point p) {
    if (p == null) {
      throw new IllegalArgumentException("Point cannot be null.");
    }

    return getGameObjectAt((int) p.getX(), (int) p.getY());
  }

  /**
   * This method put the {@link GameObject} with the input x and y.
   *
   * <p>Invokes the isPointOutOfBounds method to check if the object is out of bounds and return
   * false if isPointOutOfBounds method returns true, else sets the coordinates and returns true.
   *
   * @param gameObject The target game object to put.
   * @param x The x coordinate that the target object will be put.
   * @param y The y coordinate that the target object will be put.
   * @return Whether the put operation is succeed or not, false if the location is out of bounds.
   */
  public boolean putGameObjectAt(GameObject gameObject, int x, int y) {
    if (isPointOutOfBounds(x, y)) {
      return false;
    }

    gameObjects[x][y] = gameObject;
    return gameObjects[x][y] == gameObject;
  }

  /**
   * This method put the {@link GameObject} with the input point.
   *
   * <p>returns true if the pointer is not null and setting succeed, false if either pointer is null
   * nor setting false.
   *
   * @param gameObject The target game object to be put.
   * @param p The target location where the game object will be placed.
   * @return Whether the put operation is succeed or not.
   */
  public boolean putGameObjectAt(GameObject gameObject, Point p) {
    return p != null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY());
  }

  /**
   * This method checks if the point is out of bounds.
   *
   * <p>if either the coordinates are less than 0 nor the coordinates are beyond border, return
   * true, else return false.
   *
   * @param x The x coordinate of the Point that will be checked if it's out of bounds.
   * @param y The y coordinate of the Point that will be checked if it's out of bounds.
   * @return Whether the point is out of bounds.
   */
  private boolean isPointOutOfBounds(int x, int y) {
    return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
  }

  /**
   * This method translates the gameObject to a string.
   *
   * @return A string of gameObject.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(gameObjects.length);

    for (GameObject[] gameObject : gameObjects) {
      for (GameObject aGameObject : gameObject) {
        if (aGameObject == null) {
          aGameObject = GameObject.DEBUG_OBJECT;
        }
        sb.append(aGameObject.getCharSymbol());
      }

      sb.append('\n');
    }

    return sb.toString();
  }

  /**
   * This overridden method returns a newly generated GridIterator object.
   *
   * @return An iterator GameObject.
   */
  @Override
  public Iterator<GameObject> iterator() {
    return new GridIterator();
  }

  /**
   * This class iterates the grid for the current {@link Level}.
   *
   * @see Iterator
   */
  public class GridIterator implements Iterator<GameObject> {
    int row = 0;
    int column = 0;

    /**
     * This overridden method checks whether get the next object, if reaches the border, returns
     * false, true otherwise.
     *
     * @return Whether the grid is fully loaded.
     */
    @Override
    public boolean hasNext() {
      return !(row == ROWS && column == COLUMNS);
    }

    /**
     * This overridden method get the next object.
     *
     * <p>set column to 0 and add one to the row if column is bigger than or equal to the COLUMNS,
     * then calls the getGameObjectAt method with the input of column++ and row.
     *
     * @return The next {@link GameObject}.
     */
    @Override
    public GameObject next() {
      if (column >= COLUMNS) {
        column = 0;
        row++;
      }
      return getGameObjectAt(column++, row);
    }
  }
}
