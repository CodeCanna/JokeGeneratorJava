package joke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;

/**
 * This class plays a sound...TODO: Get this class to create a sound
 */
public class BaddumTss {
  public void baddumTss() {
    try {
      Path root = Paths.get("").toAbsolutePath(); // Get our project root
      File audioFile = new File("/JokeGenerator/resources/drum_sound.mp3"); // Get the path to our audio file relative to our project root

      String audioFilePath = String.format("%s%s", root, audioFile); // Concat an absolute path to the audio file.
      System.out.println(audioFilePath); // This is a valid path, but I'm getting errors

      Media media = new Media(audioFilePath);
      MediaPlayer player = new MediaPlayer(media);

      player.play();

    } catch (IllegalArgumentException e) {
      System.err.println(e);
    }
  }
}
