package obuits;

// Import necessary libraries
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a queue for storing audio file names.
 */
public class clsAudioFilesQueue {

    // Queue for storing regular audio file names
    static Queue<String> AudioQueue = new LinkedList<>();

    // Queue for storing special audio file names
    static Queue<String> SpelAudioQueue = new LinkedList<>();

    /**
     * Method to add a regular audio file name to the queue.
     *
     * @param str The name of the audio file to be added.
     */
    public synchronized void addData(String str) {
        try {
            // Add the audio file name to the regular audio queue
            AudioQueue.add(str);
            // Create an instance of AudioPlayActivity (assuming this class exists)
            AudioPlayActivity obj = new AudioPlayActivity();
        } catch (Exception ex) {
            // Exception handling if any error occurs
        }
    }

    /**
     * Method to add a special audio file name to the queue.
     *
     * @param str The name of the special audio file to be added.
     */
    public synchronized void addDataSpel(String str) {
        try {
            // Add the special audio file name to the special audio queue
            SpelAudioQueue.add(str);
            // Create an instance of AudioPlayActivity (assuming this class exists)
            AudioPlayActivity obj = new AudioPlayActivity();
        } catch (Exception ex) {
            // Exception handling if any error occurs
        }
    }

    /**
     * Method to remove and retrieve a regular audio file name from the queue.
     *
     * @return The name of the removed regular audio file.
     */
    public synchronized String removeData() {
        try {
            // Remove and return the first regular audio file name from the queue
            return AudioQueue.poll();
        } catch (Exception ex) {
            // Exception handling if any error occurs
        }
        return ""; // Return an empty string if no audio file is available
    }

    /**
     * Method to remove and retrieve a special audio file name from the queue.
     *
     * @return The name of the removed special audio file.
     */
    public synchronized String removeDataSpel() {
        try {
            // Remove and return the first special audio file name from the queue
            return SpelAudioQueue.poll();
        } catch (Exception ex) {
            // Exception handling if any error occurs
        }
        return ""; // Return an empty string if no special audio file is available
    }

    /**
     * Method to get the number of regular audio files in the queue.
     *
     * @return The count of regular audio files in the queue.
     */
    public synchronized int audioQueueCnt() {
        // Return the size of the regular audio queue
        return AudioQueue.size();
    }

    /**
     * Method to get the number of special audio files in the queue.
     *
     * @return The count of special audio files in the queue.
     */
    public synchronized int audioQueueCntSpel() {
        // Return the size of the special audio queue
        return SpelAudioQueue.size();
    }

    /**
     * Method to remove all regular audio files from the queue.
     */
    public synchronized void removeAllMessagesQueue() {
        // Clear the regular audio queue
        AudioQueue.clear();
    }
}
