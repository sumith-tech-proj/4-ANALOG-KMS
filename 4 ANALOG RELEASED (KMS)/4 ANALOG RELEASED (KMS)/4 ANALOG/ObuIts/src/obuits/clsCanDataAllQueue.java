package obuits;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * A class for managing two queues storing CAN (Controller Area Network) data.
 */
public class clsCanDataAllQueue {

    // Queue for storing CAN1 data
    static LinkedBlockingDeque canAllQueue = new LinkedBlockingDeque();

    // Another queue for storing CAN2 data 
    static LinkedBlockingDeque canAllQueue1 = new LinkedBlockingDeque();

    /**
     * Add CAN1 data to the first queue.
     * 
     * @param data The CAN1 data to be added.
     */
    public void addAllData(String data) {
        try {
            // Check if the size of the first queue exceeds 5000, remove oldest element if necessary
            if (canAllQueue.size() > 5000) {
                canAllQueue.remove();
            }
            // Add the data to the first queue
            canAllQueue.add(data);
        } catch (Exception ex) {
            // Exception handling
        }
        // Clean up data variable
        data = null;
    }

    /**
     * Remove and return the oldest CAN1 data from the first queue.
     * 
     * @return The oldest CAN1 data, or null if the queue is empty.
     */
    public String removeAllData() {
        try {
            // Remove and return the oldest element from the CAN1 queue
            return (String) canAllQueue.remove();
        } catch (Exception ex) {
            // Exception handling
        }
        return null; // Return null if an exception occurs
    }

    /**
     * Get the current length (number of elements) of the first queue.
     * 
     * @return The length of the first queue.
     */
    public int getAllLength() {
        try {
            // Return the size of the first queue
            return canAllQueue.size();
        } catch (Exception ex) {
            // Exception handling
        }
        return 0; // Return 0 if an exception occurs
    }

    /**
     * Add CAN2 data to the second queue.
     * 
     * @param data The CAN2 data to be added.
     */
    public void addAllData1(String data) {
        try {
            // Check if the size of the second queue exceeds 5000, remove oldest element if necessary
            if (canAllQueue1.size() > 5000) {
                canAllQueue1.remove();
            }
            // Add the data to the can2 queue
            canAllQueue1.add(data);
        } catch (Exception ex) {
            // Exception handling
        }
        // Clean up data variable
        data = null;
    }

    /**
     * Remove and return the oldest CAN2 data from the second queue.
     * 
     * @return The oldest CAN2 data, or null if the queue is empty.
     */
    public String removeAllData1() {
        try {
            // Remove and return the oldest element from the can2 queue
            return (String) canAllQueue1.remove();
        } catch (Exception ex) {
            // Exception handling
        }
        return null; // Return null if an exception occurs
    }

    /**
     * Get the current length (number of elements) of the second queue.
     * 
     * @return The length of the second queue.
     */
    public int getAllLength1() {
        try {
            // Return the size of the second queue
            return canAllQueue1.size();
        } catch (Exception ex) {
            // Exception handling
        }
        return 0; // Return 0 if an exception occurs
    }
}
