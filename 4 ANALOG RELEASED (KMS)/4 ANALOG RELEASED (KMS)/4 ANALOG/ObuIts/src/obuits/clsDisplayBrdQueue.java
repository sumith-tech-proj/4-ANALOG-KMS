package obuits;

import java.util.LinkedList;
import java.util.Queue;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsSharedVariables.getDisBrdName;

/**
 * This class manages queues for display board data, including both general data
 * and FSR (Force-Sensitive Resistor) data.
 */
public class clsDisplayBrdQueue {

    // Queue for storing general display board data
    static Queue<String> displayBrdQueue = new LinkedList<>();

    // Queue for storing FSR data
    static Queue<String> displayBrdFSRQueue = new LinkedList<>();

    /**
     * Method to add FSR data to the queue.
     *
     * @param str The FSR data to be added.
     */
    public synchronized void addFSRData(String str) {
        try {
            // Check if the FSR queue size exceeds the limit, and remove the oldest message if necessary
            if (displayBrdFSRQueue.size() > 100) {
                displayBrdFSRQueue.remove();
            }

            // Add the FSR data to the queue
            displayBrdFSRQueue.add(str);

        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
    }

    /**
     * Method to remove FSR data from the queue.
     *
     * @return The removed FSR data.
     */
    public synchronized String removeFSRData() {
        try {
            String str = displayBrdFSRQueue.remove();
            //clsReadFiles objReadFiles = new clsReadFiles();
            // objReadFiles.write_log_low_memory_data("Dis brd send " + str);
            //  objReadFiles = null;
            return str;
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        return "";
    }

    /**
     * Method to clear all messages from the FSR queue.
     */
    public synchronized void removeAllFSRMessagesQueue() {
        // Clear all messages from the FSR queue
        displayBrdFSRQueue.clear();
    }

    /**
     * Method to get the count of messages in the FSR queue.
     *
     * @return The count of messages in the FSR queue.
     */
    public synchronized int dIsBrdFSRQueueCnt() {
        // Return the count of messages in the FSR queue
        return displayBrdFSRQueue.size();
    }

    /**
     * Method to add general data to the display board queue.
     *
     * @param str The general data to be added.
     */
    public synchronized void addData(String str) {
        try {
            // Check if the general display board queue size exceeds the limit, and remove the oldest message if necessary
            if (displayBrdQueue.size() > 100) {
                displayBrdQueue.remove();
            }

            // Add data to the general display board queue
            if (getDisBrdName() == SUMITH_DISBRD) {
                displayBrdQueue.add(str);
            } else {
                // Add multiple copies of data to the queue for non-Sumith display boards
                displayBrdQueue.add(str);
                displayBrdQueue.add(str);
                displayBrdQueue.add(str);
            }

        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
    }

    /**
     * Method to remove general data from the display board queue.
     *
     * @return The removed general data.
     */
    public synchronized String removeData() {
        try {
            // Remove and return data from the general display board queue
            return displayBrdQueue.remove();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        return "";
    }

    /**
     * Method to get the count of messages in the general display board queue.
     *
     * @return The count of messages in the general display board queue.
     */
    public synchronized int dIsBrdQueueCnt() {
        // Return the count of messages in the general display board queue
        return displayBrdQueue.size();
    }

    /**
     * Method to clear all messages from the general display board queue.
     */
    public synchronized void removeAllMessagesQueue() {
        // Clear all messages from the general display board queue
        displayBrdQueue.clear();
    }

}
