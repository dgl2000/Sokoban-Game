package com.ae2dms.sokobanfx.view;

import com.ae2dms.sokobanfx.controller.PlayPageController;
import com.ae2dms.sokobanfx.service.GameEngine;
import com.ae2dms.sokobanfx.util.GameObject;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
/**
 * This class beautify the graphic of the objects in the game.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class GraphicObject {

  private final Image wallImage;
  private final Image floorImage;
  private final Image crateImage;
  private final Image playerUpImage;
  private final Image playerDownImage;
  private final Image playerRightImage;
  private final Image playerLeftImage;
  private final Image diamondImage;
  private final Image crateOnDiamondImage;
  private Image lastPlayerImage = null;

  /**
   * This constructor initialize a whole set of game object.
   *
   * @param wallImage The image of the wall.
   * @param floorImage The image of the floor.
   * @param crateImage The image of the crate.
   * @param playerUpImage The image of the player upward.
   * @param playerDownImage The image of the player downward.
   * @param playerRightImage The image of the player rightward.
   * @param playerLeftImage The image of the player leftward.
   * @param diamondImage The image of the diamond.
   * @param crateOnDiamondImage The image of the crate on the diamond.
   */
  public GraphicObject(
      Image wallImage,
      Image floorImage,
      Image crateImage,
      Image playerUpImage,
      Image playerDownImage,
      Image playerRightImage,
      Image playerLeftImage,
      Image diamondImage,
      Image crateOnDiamondImage) {
    this.wallImage = wallImage;
    this.floorImage = floorImage;
    this.crateImage = crateImage;
    this.playerUpImage = playerUpImage;
    this.playerDownImage = playerDownImage;
    this.playerRightImage = playerRightImage;
    this.playerLeftImage = playerLeftImage;
    this.diamondImage = diamondImage;
    this.crateOnDiamondImage = crateOnDiamondImage;
  }

  /**
   * This method beautifies the game object and returns a image object corresponding to different
   * GameObject.
   *
   * @param obj The game object that need to be beautified.
   * @param keyCode The key press player inputs.
   * @param playPageController The PlayPageController object that handles the event on Play page.
   * @return The image corresponding to the GameObject.
   */
  public Image setImage(GameObject obj, KeyCode keyCode, PlayPageController playPageController) {
    Image image;
    if (obj != GameObject.WALL) {
      if (obj == GameObject.CRATE) {
        image = crateImage;
      } else if (obj == GameObject.DIAMOND) {
        image = diamondImage;
      } else if (obj == GameObject.KEEPER) {
        if (playPageController.getIsSwitchKey()) {
          if (keyCode == KeyCode.W || keyCode == null) {
            image = playerUpImage;
            lastPlayerImage = playerUpImage;
          } else if (keyCode == KeyCode.S) {
            image = playerDownImage;
            lastPlayerImage = playerDownImage;
          } else if (keyCode == KeyCode.A) {
            image = playerLeftImage;
            lastPlayerImage = playerLeftImage;
          } else if (keyCode == KeyCode.D) {
            image = playerRightImage;
            lastPlayerImage = playerRightImage;
          } else {
            image = lastPlayerImage;
          }
        } else {
          if (keyCode == KeyCode.UP || keyCode == null) {
            image = playerUpImage;
            lastPlayerImage = playerUpImage;
          } else if (keyCode == KeyCode.DOWN) {
            image = playerDownImage;
            lastPlayerImage = playerDownImage;
          } else if (keyCode == KeyCode.LEFT) {
            image = playerLeftImage;
            lastPlayerImage = playerLeftImage;
          } else if (keyCode == KeyCode.RIGHT) {
            image = playerRightImage;
            lastPlayerImage = playerRightImage;
          } else {
            image = lastPlayerImage;
          }
        }

      } else if (obj == GameObject.FLOOR) {
        image = floorImage;
      } else if (obj == GameObject.CRATE_ON_DIAMOND) {
        image = crateOnDiamondImage;
      } else {
        String message = "Error in Level constructor. Object not recognized.";
        GameEngine.logger.severe(message);
        throw new AssertionError(message);
      }
    } else {
      image = wallImage;
    }
    return image;
  }
}
