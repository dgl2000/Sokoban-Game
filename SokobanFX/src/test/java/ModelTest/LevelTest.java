package ModelTest;

import com.ae2dms.sokobanfx.model.Level;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * This class tests the function of Level class.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class LevelTest {
  private Level level;
  /**
   * This class tests the constructor, and it runs before all the tests to instantiate a level
   * object.
   */
  @Before
  public void testConstructor() {
    InputStream sampleGameInput = getClass().getClassLoader().getResourceAsStream("debugLevel.skb");
    InputStream highScoreInput =
        getClass().getClassLoader().getResourceAsStream("debugHighScoreList.skb");
    GameEngine gameEngine = new GameEngine(sampleGameInput, highScoreInput);
    gameEngine.toggleDebug();
    List<String> rawLevel = new ArrayList<>();
    rawLevel.add("WWWWWWWWWWWWWwWWWWWW");
    rawLevel.add("Ws cd              W");
    rawLevel.add("wwwwwwwwWwwwwwwwwwww");
    level = new Level("Just this level", 1, rawLevel);
  }

  /** This method tests the isComplete method. */
  @Test
  public void isComplete() {
    assertFalse(level.isComplete());
  }

  /** This method tests the getName method. */
  @Test
  public void getName() {
    assertEquals("Just this level", level.getName());
  }

  /** This method tests the getIndex method. */
  @Test
  public void getIndex() {
    assertEquals(1, level.getIndex());
  }

  /** This method tests the getKeeperPosition method. */
  @Test
  public void getKeeperPosition() {
    assertEquals(new Point(1, 1), level.getKeeperPosition());
  }

  /** This method tests the getTargetObject method. */
  @Test
  public void getTargetObject() {
    assertEquals(GameObject.CRATE, level.getTargetObject(new Point(1, 2), new Point(0, 1)));
  }

  /** This method tests the GetTargetObject method when ArrayIndexOutOfBoundsException throw. */
  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testGetTargetObjectWhenExceptionThrow() {
    assertEquals(GameObject.CRATE, level.getTargetObject(new Point(1, 2), new Point(2, 1)));
  }

  /** This method tests the toStringObjectGrid method. */
  @Test
  public void toStringObjectGrid() {
    String objectGrid = "WWWWWWWWWWWWWWWWWWWW\nWS C               W\nWWWWWWWWWWWWWWWWWWWW\n";
    assertEquals(objectGrid, level.toStringObjectGrid());
  }

  /** This method tests the toStringDiamondGrid method. */
  @Test
  public void toStringDiamondGrid() {
    String diamondGrid = "====================\n====D===============\n====================\n";
    assertEquals(diamondGrid, level.toStringDiamondGrid());
  }

  /** This method tests the toStringCombGrid method. */
  @Test
  public void toStringCombGrid() {
    String combGrid = "WWWWWWWWWWWWWWWWWWWW\nWS CD              W\nWWWWWWWWWWWWWWWWWWWW\n";
    assertEquals(combGrid, level.toStringCombGrid());
  }
}
