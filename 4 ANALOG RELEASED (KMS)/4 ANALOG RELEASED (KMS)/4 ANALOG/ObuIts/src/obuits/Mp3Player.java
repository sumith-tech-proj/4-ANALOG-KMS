package obuits;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 * A simple MP3 player class to play audio files.
 */
public class Mp3Player {

    private Player player = null; // Player object for audio playback

    /**
     * Constructor for Mp3Player class.
     */
    public Mp3Player() {
        // Empty constructor
    }

    /**
     * Play an audio file.
     *
     * @param filename The path of the audio file to play.
     */
    public void play(String filename) {
        BufferedInputStream buffer = null; // Buffered input stream for file reading
        try {
            // Create a buffered input stream to read from the file
            buffer = new BufferedInputStream(new FileInputStream(filename));
            // Initialize a Player object with the buffered input stream
            player = new Player(buffer);
            // Start playback
            player.play();

        } catch (Exception e) {
            // Exception handling
        } finally {
            try {
                // Close the Player and release resources
                player.close();
                player = null;
                // Close the buffered input stream
                buffer.close();
                buffer = null;
            } catch (Exception ex) {
                // Exception handling
            }
        }
    }

    /**
     * Stop the audio playback.
     */
    public void stop() {
        try {
            // Check if the player object is not null
            if (player != null) {
                // Close the Player and release resources
                player.close();
                player = null;
            }

        } catch (Exception e) {
            // Exception handling
        }
    }

    /**
     * Check if the audio playback is completed.
     *
     * @return True if playback is completed, false otherwise.
     */
    public boolean isCompleted() {
        try {
            // Check if the player object is not null
            if (player != null) {
                // Return the completion status of playback
                return player.isComplete();
            }
        } catch (Exception e) {
            // Exception handling
        }
        return false; // Default return value
    }
}
