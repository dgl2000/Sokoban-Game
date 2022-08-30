package com.ae2dms.sokobanfx.manager;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.stage.Stage;

import java.io.*;

/**
 * This class manages all the methods related to file.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class FileManager {

  private static FileManager instance = new FileManager();
  private PlayPageController playPageController;
  private String directoryPath;

  private FileManager() {}
  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of FileManager object.
   */
  public static FileManager getInstance() {
    return instance;
  }

  /**
   * This method loads the game file which player chooses and binds to the controller.
   *
   * @param primaryStage The primary stage that displays the game.
   * @param relativePath The path of file to load.
   */
  public void loadGameFile(Stage primaryStage, String relativePath) {
    playPageController.autoSaveShutDown();
    playPageController.stopDisplayTimer();
    if (GameEngine.isDebugActive()) {
      GameEngine.logger.info("Loading save file.");
    }
    try {
      InputStream gameFileInput = new FileInputStream(directoryPath + relativePath);
      InputStream highScoreInput = new FileInputStream(directoryPath + "/HighScoreList.skb");
      playPageController.initializeGame(gameFileInput, highScoreInput, primaryStage);
      playPageController.initializeTimer(0);
      playPageController.initializeMoveText(0);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * This methods loads the default save file and binds to the controller.
   *
   * @param playPageController The controller that handles play page's click event.
   * @param primaryStage The primary stage that displays the game.
   */
  public void loadDefaultSaveFile(PlayPageController playPageController, Stage primaryStage) {
    this.playPageController = playPageController;
    try {
      InputStream sampleGameInput =
          getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
      InputStream highScoreInput = new FileInputStream(directoryPath + "/HighScoreList.skb");
      playPageController.initializeGame(sampleGameInput, highScoreInput, primaryStage);
      playPageController.setEventFilter();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * This is a getter for getting the path of the game data directory.
   *
   * @return The path of the data directory.
   */
  public String getDirectoryPath() {
    return directoryPath;
  }

  /**
   * This method saves the file with input file path and text content.
   *
   * @param relativePath The path of file to save.
   * @param gameText The content of the file.
   * @return Whether the save successes.
   */
  public boolean saveFile(String relativePath, StringBuilder gameText) {
    try {
      String path = directoryPath + relativePath;
      File file = new File(path);
      FileWriter out = new FileWriter(file);
      out.write(String.valueOf(gameText));
      out.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * This methods loads the beginner guidance game file and binds to the controller.
   *
   * @param playPageController The {@link PlayPageController} that handles play page's click event
   * @param primaryStage The primary stage that displays the game
   */
  public void loadGuidanceFile(PlayPageController playPageController, Stage primaryStage) {
    this.playPageController = playPageController;
    InputStream sampleGameInput =
        getClass().getClassLoader().getResourceAsStream("level/BeginnerGuidance.skb");
    InputStream highScoreInput =
        getClass().getClassLoader().getResourceAsStream("record/HighScoreList.skb");
    playPageController.initializeGame(sampleGameInput, highScoreInput, primaryStage);
    playPageController.setEventFilter();
  }

  /**
   * This method initializes a game data directory and copies the files in the resources/record to
   * this directory.
   */
  public void initializeDirectory() {
    File directory = new File(System.getProperty("user.dir") + "/" + "data");
    directoryPath = directory.getPath();
    if (!directory.exists() && !directory.isDirectory()) {
      directory.mkdirs();
      initializeFile("record/SaveProgress/AutoSave.skb", directoryPath, "AutoSave.skb");
      initializeFile("record/SaveProgress/Progress1.skb", directoryPath, "Progress1.skb");
      initializeFile("record/SaveProgress/Progress2.skb", directoryPath, "Progress2.skb");
      initializeFile("record/SaveProgress/Progress3.skb", directoryPath, "Progress3.skb");
      initializeFile("record/HighScoreList.skb", directoryPath, "HighScoreList.skb");
    }
  }

  private void initializeFile(String relativeFilePath, String directoryPath, String fileName) {
    InputStream in = getClass().getClassLoader().getResourceAsStream(relativeFilePath);
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      StringBuilder gameText = new StringBuilder();
      do {
        String line = reader.readLine();
        if (line != null) {
          gameText.append(line).append("\n");
        } else {
          break;
        }
      } while (true);
      File file = new File(directoryPath, fileName);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter out = new FileWriter(file);
      out.write(String.valueOf(gameText));
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
