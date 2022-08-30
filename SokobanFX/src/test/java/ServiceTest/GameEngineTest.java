package ServiceTest;

import com.ae2dms.sokobanfx.model.Level;
import com.ae2dms.sokobanfx.service.GameEngine;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;
/**
 * This class tests the function of GameEngine class.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameEngineTest {
  private GameEngine gameEngine;
  /**
   * This class tests the constructor, and it runs before all the tests to instantiate a gameEngine
   * object.
   */
  @Before
  public void testConstructor() {
    InputStream sampleGameInput = getClass().getClassLoader().getResourceAsStream("debugGame.skb");
    InputStream highScoreInput =
        getClass().getClassLoader().getResourceAsStream("debugHighScoreList.skb");
    gameEngine = new GameEngine(sampleGameInput, highScoreInput);
  }

  /** This method tests the isDebugActive method. */
  @Test
  public void isDebugActive() {
    assertFalse(GameEngine.isDebugActive());
  }

  /** This method tests the isGameComplete method. */
  @Test
  public void isGameComplete() {
    assertFalse(gameEngine.isGameComplete());
  }

  /** This method tests the setGameComplete method. */
  @Test
  public void setGameComplete() {
    gameEngine.setGameComplete(true);
    assertTrue(gameEngine.isGameComplete());
    gameEngine.setGameComplete(false);
    assertFalse(gameEngine.isGameComplete());
  }

  /** This method tests the getNextLevel method. */
  @Test
  public void getNextLevel() {
    List<Level> level = gameEngine.getLevels();
    assertEquals(level.get(1), gameEngine.getNextLevel());
  }

  /** This method tests the getCurrentLevel method. */
  @Test
  public void getCurrentLevel() {
    List<Level> level = gameEngine.getLevels();
    assertEquals(level.get(0), gameEngine.getCurrentLevel());
  }

  /** This method tests the setCurrentLevel method. */
  @Test
  public void setCurrentLevel() {
    List<Level> level = gameEngine.getLevels();
    gameEngine.setCurrentLevel(level.get(2));
    assertEquals(level.get(2), gameEngine.getCurrentLevel());
  }

  /** This method tests the toggleDebug method. */
  @Test
  public void toggleDebug() {
    gameEngine.toggleDebug();
    assertTrue(GameEngine.isDebugActive());
    gameEngine.toggleDebug();
  }

  /** This method tests the getTimeCount method. */
  @Test
  public void getTimeCount() {
    assertEquals(20, gameEngine.getTimeCount());
  }

  /** This method tests the getMovesCount method. */
  @Test
  public void getMovesCount() {
    assertEquals(10, gameEngine.getMovesCount());
  }

  /** This method tests the setMovesCount method. */
  @Test
  public void setMovesCount() {
    gameEngine.setMovesCount(2);
    assertEquals(2, gameEngine.getMovesCount());
  }
}
