package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.BeginnerGuidanceController;
import javafx.stage.Stage;
/**
 * This interface is a skeleton of all the events on the Beginner Guidance page.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public interface BeginnerGuidanceEvent {
  /**
   * This method initialize the Beginner Guidance page.
   *
   * @param primaryStage The primary stage that displays the game.
   * @param stage The stage that displays the displays the pop-ups window.
   * @param beginnerGuidanceController A {@link BeginnerGuidanceController} object that that will be
   *     used to set the attribute of the element on the page.
   */
  void initialize(
      Stage primaryStage, Stage stage, BeginnerGuidanceController beginnerGuidanceController);
  /**
   * This method handles the click event of the button on the left side of the page.
   *
   * @param beginnerGuidanceController An {@link BeginnerGuidanceController} object that will be
   *     used to set the attribute of the button on the page.
   */
  void leftButton(BeginnerGuidanceController beginnerGuidanceController);

  /** This method handles the click event of the button on the right side of the page. */
  void rightButton();
}
