package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.RankingDialogController;
import com.ae2dms.sokobanfx.manager.FileManager;
import com.ae2dms.sokobanfx.service.GameEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
/**
 * This class implements the all the events on the Ranking Dialog.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class RankingDialogEventImpl implements RankingDialogEvent {
  private static RankingDialogEventImpl instance = new RankingDialogEventImpl();

  private RankingDialogEventImpl() {}

  /**
   * This is the getter for getting the only object available.
   *
   * @return The only available of RankingDialogEventImpl object.
   */
  public static RankingDialogEventImpl getInstance() {
    return instance;
  }

  /**
   * This method translate the millisecond to " hr min sec"
   *
   * @param timeCount millisecond of time
   * @return a string of formatted time
   */
  private String formatTime(long timeCount) {
    long hours = (timeCount % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    long minutes = (timeCount % (1000 * 60 * 60)) / (1000 * 60);
    long seconds = (timeCount % (1000 * 60)) / 1000;
    return hours + " hr " + minutes + " min " + seconds + " sec ";
  }
  /**
   * This method sets the text of Text objects in the page.
   *
   * @param scores A string list of scores
   * @param rankingDialogController The {@link RankingDialogController} object that will be used to
   *     set the attribute of the text on the page.
   * @see List<String>
   */
  @Override
  public void setText(List<String> scores, RankingDialogController rankingDialogController) {
    rankingDialogController
        .getFirstText()
        .setText(
            "Move: " + scores.get(0) + "   Time: " + formatTime(Long.parseLong(scores.get(1))));
    rankingDialogController
        .getSecondText()
        .setText(
            "Move: " + scores.get(2) + "   Time: " + formatTime(Long.parseLong(scores.get(3))));
    rankingDialogController
        .getThirdText()
        .setText(
            "Move: " + scores.get(4) + "   Time: " + formatTime(Long.parseLong(scores.get(5))));
    rankingDialogController
        .getForthText()
        .setText(
            "Move: " + scores.get(6) + "   Time: " + formatTime(Long.parseLong(scores.get(7))));
    rankingDialogController
        .getFifthText()
        .setText(
            "Move: " + scores.get(8) + "   Time: " + formatTime(Long.parseLong(scores.get(9))));
    rankingDialogController
        .getSixthText()
        .setText(
            "Move: " + scores.get(10) + "   Time: " + formatTime(Long.parseLong(scores.get(11))));
    rankingDialogController
        .getSeventhText()
        .setText(
            "Move: " + scores.get(12) + "   Time: " + formatTime(Long.parseLong(scores.get(13))));
    rankingDialogController
        .getEighthText()
        .setText(
            "Move: " + scores.get(14) + "   Time: " + formatTime(Long.parseLong(scores.get(15))));
  }

  /**
   * This method calls the setText to help setting the text in the page.
   *
   * @param rankingDialogController The {@link RankingDialogController} object that will be used to
   *     set the attribute of the text on the page.
   */
  @Override
  public void setScoresList(RankingDialogController rankingDialogController) {
    InputStream sampleGameInput =
        getClass().getClassLoader().getResourceAsStream("level/SampleGame.skb");
    FileManager fileManager = FileManager.getInstance();
    try {
      InputStream highScoreInput =
          new FileInputStream(fileManager.getDirectoryPath() + "/HighScoreList.skb");
      GameEngine permanentGameEngine = new GameEngine(sampleGameInput, highScoreInput);
      setText(permanentGameEngine.getScores(), rankingDialogController);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
