package ModelTest;

import com.ae2dms.sokobanfx.model.GameGrid;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * This class tests the function of GameGrid class.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameGridTest {

  private GameGrid gameGrid;
  private int rows = 3;
  private int columns = 3;

  /**
   * This method tests the constructor, and it runs before all the tests to instantiate a gameGrid.
   */
  @Before
  public void testConstructor() {
    gameGrid = new GameGrid(columns, rows);
  }

  /** This method tests the translatePoint method. */
  @Test
  public void testTranslatePoint() {
    Point point = GameGrid.translatePoint(new Point(0, 2), new Point(1, 0));
    assertEquals(new Point(1, 2), point);
  }

  /** This method tests the getTargetFromSource method. */
  @Test
  public void testGetTargetFromSource() {
    if (gameGrid.putGameObjectAt(GameObject.CRATE_ON_DIAMOND, new Point(1, 2))) {
      GameObject gameObject = gameGrid.getTargetFromSource(new Point(1, 1), new Point(0, 1));
      assertEquals(GameObject.CRATE_ON_DIAMOND, gameObject);
    }
  }

  /** This method tests the getGameObjectAt method in two cases. */
  @Test
  public void testGetGameObjectAt() {
    if (gameGrid.putGameObjectAt(GameObject.DIAMOND, new Point(0, 2))) {
      GameObject gameObject = gameGrid.getGameObjectAt(0, 2);
      assertEquals(GameObject.DIAMOND, gameObject);
    }
    if (gameGrid.putGameObjectAt(GameObject.CRATE, new Point(0, 1))) {
      GameObject gameObject = gameGrid.getGameObjectAt(0, 1);
      assertNotSame(GameObject.DIAMOND, gameObject);
    }
  }

  /** This method tests the getGameObjectAt method when ArrayIndexOutOfBoundsException throw. */
  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testGetGameObjectAtWhenExceptionThrow() {
    int row = rows + 1;
    int column = columns + 1;
    gameGrid.getGameObjectAt(column, row);
  }

  /**
   * This method tests the getGameObjectAt method when ArrayIndexOutOfBoundsException throw and
   * debug is active.
   */
  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testGetGameObjectAtWhenExceptionThrowDebugActive() {
    InputStream sampleGameInput = getClass().getClassLoader().getResourceAsStream("debugGame.skb");
    InputStream highScoreInput =
        getClass().getClassLoader().getResourceAsStream("debugHighScoreList.skb");
    GameEngine gameEngine = new GameEngine(sampleGameInput, highScoreInput);
    gameEngine.toggleDebug();
    assertEquals(
        "Trying to get null GameObject from COL: 3  ROW: 3", gameGrid.getGameObjectAt(3, 3));
  }

  /** This method tests the getGameObjectAt method when IllegalArgumentException throw. */
  @Test(expected = IllegalArgumentException.class)
  public void testTestGetGameObjectAt() {
    gameGrid.getGameObjectAt(null);
  }

  /** This method tests the putGameObjectAt method. */
  @Test
  public void testPutGameObjectAt() {
    int row = rows + 1;
    int column = columns + 1;
    assertFalse(gameGrid.putGameObjectAt(GameObject.CRATE_ON_DIAMOND, column, row));

    assertTrue(gameGrid.putGameObjectAt(GameObject.WALL, 2, 2));
  }

  /** This method tests the putGameObjectAt method. */
  @Test
  public void testTestPutGameObjectAt() {
    assertTrue(gameGrid.putGameObjectAt(GameObject.CRATE, new Point(2, 1)));
    assertFalse(gameGrid.putGameObjectAt(GameObject.WALL, null));
  }

  /** This method tests the toString method. */
  @Test
  public void testTestToString() {
    assertEquals("===\n===\n===\n", gameGrid.toString());
    gameGrid.putGameObjectAt(GameObject.CRATE, 0, 0);
    gameGrid.putGameObjectAt(GameObject.DIAMOND, 1, 1);
    assertEquals("C==\n=D=\n===\n", gameGrid.toString());
  }
}
