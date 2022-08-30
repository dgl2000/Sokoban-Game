package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.RankingDialogController;

import java.util.List;
/**
 * This interface is a skeleton of all the events on the Ranking Dialog.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface RankingDialogEvent {
  /**
   * This method sets the text of Text objects in the page.
   *
   * @param scores A string list of scores
   * @param rankingDialogController The {@link RankingDialogController} object that will be used to
   *     set the attribute of the text on the page.
   * @see List<String>
   */
  void setText(List<String> scores, RankingDialogController rankingDialogController);
  /**
   * This method calls the setText to help setting the text in the page.
   *
   * @param rankingDialogController The {@link RankingDialogController} object that will be used to
   *     set the attribute of the text on the page.
   */
  void setScoresList(RankingDialogController rankingDialogController);
}
