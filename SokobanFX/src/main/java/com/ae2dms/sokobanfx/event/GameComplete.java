package com.ae2dms.sokobanfx.event;

import com.ae2dms.sokobanfx.controller.BeginnerGuidanceController;
import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.service.GameEngine;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the object being observed. It could attach observers.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GameComplete {
  private List<GameCompleteObserver> gameCompleteObservers = new ArrayList<>();
  private long time;
  private int move;
  private Stage primaryStage;
  private GameEngine gameEngine;

  /**
   * This is the getter for getting the time.
   *
   * @return The time players take to finish the game/level.
   */
  public long getTime() {
    return time;
  }
  /**
   * This is the getter for getting the move.
   *
   * @return The move players take to finish the game/level.
   */
  public int getMove() {
    return move;
  }
  /**
   * This is the getter for getting the primaryStage.
   *
   * @return The primary stage that displays the game.
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }
  /**
   * This is the getter for getting the gameEngine.
   *
   * @return The gameEngine that provide the core logic service for game.
   */
  public GameEngine getGameEngine() {
    return gameEngine;
  }

  /**
   * This method sets the stage for observers.
   *
   * @param playPageController The {@link BeginnerGuidanceController} object that handles the event
   *     on Play page.
   */
  public void setState(PlayPageController playPageController) {
    this.time = playPageController.getGameEngine().getTimeCount();
    this.move = playPageController.getGameEngine().getMovesCount();
    this.primaryStage = playPageController.getPrimaryStage();
    this.gameEngine = playPageController.getGameEngine();
    notifyAllObservers();
  }

  /**
   * This method registers an object in the List of the observer maintained in the Subject.
   *
   * @param gameCompleteObserver The observer who want to observe the {@link GameComplete} object.
   */
  public void attach(GameCompleteObserver gameCompleteObserver) {
    gameCompleteObservers.add(gameCompleteObserver);
  }

  /** This method is invoked whenever the observed value is changed. */
  public void notifyAllObservers() {
    for (GameCompleteObserver observer : gameCompleteObservers) {
      observer.update();
    }
  }
}
