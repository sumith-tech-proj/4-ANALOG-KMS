package obuits;

import java.util.concurrent.LinkedBlockingDeque;

// Class for managing CAN alert display queues
public class clsCanAlertDisplayQueue {

    // Constant specifying the maximum number of alerts
    static final byte MAX_NO_ALERTS = 50;

    // Two queues for storing alert data
    static LinkedBlockingDeque alertDisQueue = new LinkedBlockingDeque();
    static LinkedBlockingDeque alertDisQueue1 = new LinkedBlockingDeque();

    // Method to add data to the first alert display queue
    public static void addData(String data) {
        try {
            // Check if the size of the first queue exceeds the maximum number of alerts
            if (alertDisQueue.size() > MAX_NO_ALERTS) {
                alertDisQueue.remove(); // Remove the oldest element if the limit is reached
            }
            alertDisQueue.add(data); // Add new data to the first queue
        } catch (Exception ex) {
            // Exception handling
        }
        data = null; // Set input data variable to null for cleanup
    }

    // Method to get the contents of the first alert display queue as a string
    public static String getCanAlertDisplayList() {
        try {
            return alertDisQueue.toString(); // Return string representation of the first queue
        } catch (Exception ex) {
            // Exception handling
        }
        return ""; // Return empty string if an exception occurs
    }

    // Method to add data to the second alert display queue
    public static void addData1(String data) {
        try {
            // Check if the size of the second queue exceeds the maximum number of alerts
            if (alertDisQueue1.size() > MAX_NO_ALERTS) {
                alertDisQueue1.remove(); // Remove the oldest element if the limit is reached
            }
            alertDisQueue1.add(data); // Add new data to the second queue
        } catch (Exception ex) {
            // Exception handling
        }
        data = null; // Set input data variable to null for cleanup
    }

}
