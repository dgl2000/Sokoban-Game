package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.manager.GridManager;
import com.ae2dms.sokobanfx.view.GameDialog;
/**
 * This class implements the observer, updates messages everytime being notified.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class ShowVictoryMessageObserver extends GameCompleteObserver {
  GridManager gridManager = GridManager.getInstance();
  /**
   * This constructor initializes the GameComplete object.
   *
   * @param gameComplete The {@link GameComplete} object that is being observed.
   */
  public ShowVictoryMessageObserver(GameComplete gameComplete) {
    this.gameComplete = gameComplete;
    this.gameComplete.attach(this);
  }
  /** The method pulls the new state value of the GameComplete, and displays accordingly. */
  @Override
  public void update() {
    String dialogTitle = "Game Over";
    if (gridManager.getChallengeMode() && !gameComplete.getGameEngine().isGameComplete()) {
      String dialogMessage;
      if (gridManager.getAdvertisementPlayed()) {
        dialogMessage =
            "Sorry, game over!\n\n [ You fail to complete < "
                + gameComplete.getGameEngine().mapSetName
                + " > ]\n";

      } else {
        dialogMessage =
            "Oops...Unfortunately!\n\n [ You fail to complete < "
                + gameComplete.getGameEngine().mapSetName
                + " > \n\n You can watch the ad to get more time and move ]\n";
      }
      GameDialog.newDialog(dialogTitle, dialogMessage, null, gameComplete.getPrimaryStage());
    } else {
      String dialogMessage =
          "Congratulation 🎉\n\n [ You completed < "
              + gameComplete.getGameEngine().mapSetName
              + " > \n\nin "
              + gameComplete.getMove()
              + " moves and "
              + formatTime(gameComplete.getTime())
              + " ]\n";
      GameDialog.newDialog(dialogTitle, dialogMessage, null, gameComplete.getPrimaryStage());
    }
  }

  private String formatTime(long timeCount) {
    long hours = (timeCount % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    long minutes = (timeCount % (1000 * 60 * 60)) / (1000 * 60);
    long seconds = (timeCount % (1000 * 60)) / 1000;
    return hours + " hr " + minutes + " min " + seconds + " sec ";
  }
}
