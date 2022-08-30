package com.ae2dms.sokobanfx.util;

/**
 * This enum defines the objects in the game.
 *
 * <p>Objects include wall, floor, crate, diamond, keeper.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public enum GameObject {
  /** Char 'W' represents WALL. */
  WALL('W'),
  /** Char ' ' represents FLOOR. */
  FLOOR(' '),
  /** Char 'C' represents CRATE. */
  CRATE('C'),
  /** Char 'D' represents DIAMOND. */
  DIAMOND('D'),
  /** Char 'S' represents KEEPER. */
  KEEPER('S'),
  /** Char 'O' represents CRATE and DIAMOND are overlapped. */
  CRATE_ON_DIAMOND('O'),
  /** Char '=' represents strange cases, eg. DIAMOND and KEEPER are overlapped. */
  DEBUG_OBJECT('=');

  /** This final char variable keeps the char symbol corresponding to GameObject. */
  public final char symbol;

  /**
   * The constructor assigns the input symbol value to the symbol variable.
   *
   * @param symbol The input symbol that will be set to the symbol variable.
   */
  GameObject(final char symbol) {
    this.symbol = symbol;
  }

  /**
   * This method returns the corresponding object of the input char if the symbol exits in the
   * GameObject, else considers as error and returns WALL.
   *
   * @param c The input char symbol to be compared.
   * @return The {@link GameObject} corresponding the the char.
   */
  public static GameObject fromChar(char c) {
    for (GameObject t : GameObject.values()) {
      if (Character.toUpperCase(c) == t.symbol) {
        return t;
      }
    }
    return WALL;
  }

  /**
   * This method returns the value of symbol variable.
   *
   * @return The char of the corresponding symbol.
   */
  public char getCharSymbol() {
    return symbol;
  }
}
