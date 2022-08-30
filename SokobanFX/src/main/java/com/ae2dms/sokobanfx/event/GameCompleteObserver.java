package com.ae2dms.sokobanfx.event;

/**
 * This abstract class shows the
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public abstract class GameCompleteObserver {
  /** The {@link GameComplete} object that is being observed. */
  protected GameComplete gameComplete;

  /** The abstract method pull the new state value of the GameComplete, and display accordingly. */
  public abstract void update();
}
