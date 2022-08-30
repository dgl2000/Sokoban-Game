package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.model.Level;
import com.ae2dms.sokobanfx.service.GameEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * This class implements the reset level event.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class ResetLevelHandler {
  FileManager fileManager = FileManager.getInstance();

  /**
   * This method resets the current level. Restores to the original level.
   *
   * @param playPageController The {@link PlayPageController} object that handles the event on Play
   *     page.
   */
  public void resetLevel(PlayPageController playPageController) {
    int currentIndex = playPageController.getGameEngine().getCurrentLevel().getIndex() - 1;
    try {
      InputStream sampleGameInput =
          getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
      InputStream highScoreFileInput =
          new FileInputStream(fileManager.getDirectoryPath() + "/HighScoreList.skb");
      GameEngine permanentGameEngine = new GameEngine(sampleGameInput, highScoreFileInput);
      List<Level> levels = permanentGameEngine.getLevels();
      playPageController.getGameEngine().setCurrentLevel(levels.get(currentIndex));
      GridManager.getInstance().reloadGrid(null);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
