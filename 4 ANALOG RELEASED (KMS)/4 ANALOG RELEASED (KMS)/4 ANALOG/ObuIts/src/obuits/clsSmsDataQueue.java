/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * A class to manage a queue for SMS data. Provides methods to add, remove, and
 * get the length of the queue.
 *
 * Author: Dell
 */
public class clsSmsDataQueue {

    // Static deque to store SMS data, initialized with LinkedBlockingDeque
    static LinkedBlockingDeque smsDataQueue = new LinkedBlockingDeque();

    /**
     * Adds new SMS data to the queue. If the queue size exceeds 2500, the
     * oldest entry is removed.
     *
     * @param data the SMS data to be added to the queue
     */
    public void addData(String data) {
        try {
            // If the queue size exceeds 2500, remove the oldest entry
            if (smsDataQueue.size() > 2500) {
                smsDataQueue.remove();
            }
            // Add the new SMS data to the queue
            smsDataQueue.add(data);
        } catch (NullPointerException ex) {
            // Handle NullPointerException specifically
            ex.printStackTrace();
        } catch (Exception ex) {
            // Handle any other exceptions
        }
        // Set the input data to null to aid garbage collection
        data = null;
    }

    /**
     * Removes and returns the oldest SMS data from the queue.
     *
     * @return the oldest SMS data, or an empty string if an exception occurs
     */
    public String removeData() {
        try {
            // Remove and return the oldest SMS data from the queue
            return (String) smsDataQueue.remove();
        } catch (Exception ex) {
            // Handle any exception that occurs during removal
        }
        // Return an empty string if an exception occurs
        return "";
    }

    /**
     * Returns the current size of the SMS data queue.
     *
     * @return the number of elements in the queue, or 0 if an exception occurs
     */
    public int getLength() {
        try {
            // Return the current size of the queue
            return smsDataQueue.size();
        } catch (Exception ex) {
            // Handle any exception that occurs during size retrieval
        }
        // Return 0 if an exception occurs
        return 0;
    }

}
