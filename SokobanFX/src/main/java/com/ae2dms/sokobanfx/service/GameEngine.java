package com.ae2dms.sokobanfx.service;

import com.ae2dms.sokobanfx.model.Level;
import com.ae2dms.sokobanfx.util.GameLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class provides the core logic service for game.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameEngine {
  /** This public static final String variable GAME_NAME defines the name of the game. */
  public static final String GAME_NAME = "SokobanFX";
  /** This public static GameLogger variable logger stores the log of the game. */
  public static GameLogger logger;
  /**
   * This private static boolean variable debug is set to false initially and will be set to true if
   * there exits bug.
   *
   * <p>default false{@value}
   */
  private static boolean debug = false;
  /** This variable is the name of the game map. */
  public String mapSetName;
  /** This variable counts the number of the movements. */
  private int movesCount = 0;
  /** This variable stores the time of playing. */
  private long timeCount = 0;
  /** This variable stores the start time of last play. */
  private long startTime;
  /** This variable currentLevel keeps the current level. */
  private Level currentLevel;
  /** This Level list stores a list of levels. */
  private List<Level> levels;

  private List<String> scores;
  /**
   * This private boolean gameComplete variable is set to false initially and will turn to true if
   * game ends.
   */
  private boolean gameComplete = false;

  /**
   * Constructor that try to create game logger every time create a new GameEngine object.
   *
   * <p>If fail to create, then catch the exception and give error message or error trace.
   *
   * @param gameFileInput The {@link InputStream} of the game file to be read.
   * @param highScoreInput The {@link InputStream} of the game high score list file to be read.
   */
  public GameEngine(InputStream gameFileInput, InputStream highScoreInput) {
    try {
      logger = new GameLogger();
      levels = loadGameFile(gameFileInput);
      currentLevel = getNextLevel();
      scores = loadHighScoreList(highScoreInput);
      startTime = System.currentTimeMillis();
    } catch (IOException x) {
      System.out.println("Cannot create logger.");
    } catch (NoSuchElementException e) {
      logger.warning("Cannot load the default save file: " + Arrays.toString(e.getStackTrace()));
    }
  }

  /**
   * This method detects whether debug mode turns on.
   *
   * <p>When debug mode turns on returns true, else, returns false.
   *
   * @return whether debug mode is active.
   */
  public static boolean isDebugActive() {
    return debug;
  }

  /**
   * This method loads the game level information in the game file into a list.
   *
   * @param input The game file stream to be read.
   * @return A Level list stores game level information.
   */
  private List<Level> loadGameFile(InputStream input) {
    List<Level> levels = new ArrayList<>(6);
    int levelIndex = 0;

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
      boolean parsedFirstLevel = false;
      List<String> rawLevel = new ArrayList<>();
      String levelName = "";

      do {
        String line = reader.readLine();

        // Empty line -> push the element in the parsedLevel into the Level array.
        if (line == null) {
          if (rawLevel.size() != 0) {
            Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
            levels.add(parsedLevel);
          }
          break;
        }

        if (line.contains("MapSetName")) {
          mapSetName = line.replace("MapSetName: ", "");
          continue;
        }
        if (line.contains("MovesCount")) {
          movesCount = Integer.parseInt(line.replace("MovesCount: ", ""));
          continue;
        }
        if (line.contains("TimeCount")) {
          timeCount = Integer.parseInt(line.replace("TimeCount: ", ""));
          continue;
        }

        if (line.contains("LevelName")) {
          if (parsedFirstLevel) {
            Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
            levels.add(parsedLevel);
            rawLevel.clear();
          } else {
            parsedFirstLevel = true;
          }
          // Replace "LevelName: " with ""
          levelName = line.replace("LevelName: ", "");
          continue;
        }

        line = line.trim();
        line = line.toUpperCase();
        if (line.matches(".*W.*W.*")) {
          rawLevel.add(line);
        }
      } while (true);

    } catch (IOException e) {
      logger.severe("Error trying to load the game file: " + e);
    } catch (NullPointerException e) {
      logger.severe("Cannot open the requested file: " + e);
    }

    return levels;
  }

  /**
   * This is a getter for getting whether the game is completed.
   *
   * @return Whether the game is completed.
   */
  public boolean isGameComplete() {
    return gameComplete;
  }

  /**
   * This is the setter for setting the whether the game is completed.
   *
   * @param gameComplete Whether the game is completed.
   */
  public void setGameComplete(boolean gameComplete) {
    this.gameComplete = gameComplete;
  }

  /**
   * This a getter for getting the next level.
   *
   * @return The next level.
   */
  public Level getNextLevel() {
    if (currentLevel == null) {
      return levels.get(0);
    }
    int currentLevelIndex = currentLevel.getIndex();
    if (currentLevelIndex < levels.size()) {
      // Delete +1 so that all the levels are showed.
      return levels.get(currentLevelIndex);
    }
    gameComplete = true;
    return null;
  }

  /**
   * This is the getter for getting the current level.
   *
   * <p>Enhance identifier naming. Change 'getcurrentlevel' to 'getCurrentLevel'.
   *
   * @return The current level.
   */
  public Level getCurrentLevel() {
    return currentLevel;
  }
  /**
   * This is the setter for setting the current level.
   *
   * @param currentLevel The current level.
   */
  public void setCurrentLevel(Level currentLevel) {
    this.currentLevel = currentLevel;
  }
  /**
   * This is a getter for getting a list contains levels.
   *
   * @return A list of Level.
   */
  public List<Level> getLevels() {
    return levels;
  }
  /**
   * This is a getter for getting a list contains scores.
   *
   * @return A list of score.
   */
  public List<String> getScores() {
    return scores;
  }

  /** This method pauses the timer. */
  public void pauseTimer() {
    long currentTime = System.currentTimeMillis();
    timeCount = timeCount + currentTime - startTime;
  }

  /** This method continues the timer. */
  public void continueTimer() {
    startTime = System.currentTimeMillis();
  }

  /** This method negates the debug variable. */
  public void toggleDebug() {
    debug = !debug;
  }
  /**
   * This is the getter for getting the timeCount.
   *
   * @return The total time.
   */
  public long getTimeCount() {
    return timeCount;
  }
  /**
   * This is the getter for getting the movesCount.
   *
   * @return The total moves.
   */
  public int getMovesCount() {
    return movesCount;
  }
  /**
   * This is the setter for setting the movesCount.
   *
   * @param movesCount The time to be assigned.
   */
  public void setMovesCount(int movesCount) {
    this.movesCount = movesCount;
  }

  private List<String> loadHighScoreList(InputStream input) {
    List<String> scores = new ArrayList<>(16);
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
      do {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        if (line.contains("Move")) {
          scores.add(line.replace("Move: ", "").trim());
        }
        if (line.contains("Time")) {
          scores.add(line.replace("Time: ", "").trim());
        }
      } while (true);
    } catch (IOException e) {
      logger.severe("Error trying to load the game file: " + e);
    } catch (NullPointerException e) {
      logger.severe("Cannot open the requested file: " + e);
    }
    return scores;
  }
}
