package com.ae2dms.sokobanfx.event;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;
/**
 * This class handles the toggle music event.
 *
 * @author Source code provided by UNNC COMP2059. Modified by Gaole DAI(20124917),
 *     scygd1@nottingham.edu.cn
 * @since 24 Oct, 2020
 * @version 1.0
 */
public class MusicHandler {
  private boolean isPlaying = false;
  private MediaPlayer mediaPlayer;

  /** This constructor initializes the music player. */
  public MusicHandler() {
    try {
      String musicFileRoot =
          Objects.requireNonNull(
                  this.getClass().getClassLoader().getResource("music/puzzle_theme.wav"))
              .toString();
      Media sound = new Media(musicFileRoot);
      mediaPlayer = new MediaPlayer(sound);
    } catch (Exception e) {
      System.out.println("cant find the file");
      e.printStackTrace();
    }
  }

  /**
   * This method handles the music play of media player.
   *
   * @param mediaPlayer The music player to be handled.
   */
  private void playMusic(MediaPlayer mediaPlayer) {
    if (!isPlaying) {
      mediaPlayer.play();
      mediaPlayer.setOnEndOfMedia(
          () -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
          });
      isPlaying = true;
    } else {
      mediaPlayer.pause();
      isPlaying = false;
    }
  }

  /** This method calls the playMusic method with the musicPlayer to control the music. */
  public void musicPlayer() {
    playMusic(mediaPlayer);
  }
}
