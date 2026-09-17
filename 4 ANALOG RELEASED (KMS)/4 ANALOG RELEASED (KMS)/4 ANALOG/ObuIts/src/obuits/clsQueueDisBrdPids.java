/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * A class to manage a queue for DisBrdPids data.
 * Provides synchronized methods to add, remove, get the count, and clear the queue.
 * 
 * Author: Sumitha
 */
public class clsQueueDisBrdPids {

    // Static deque to store DisBrdPids data, initialized with LinkedBlockingDeque
    static LinkedBlockingDeque objQueueDisBrdPid = new LinkedBlockingDeque();

    /**
     * Adds new data to the DisBrdPids queue.
     * If the queue size exceeds 500, the oldest entry is removed.
     * This method is synchronized to ensure thread safety.
     * 
     * @param data the data to be added to the queue
     */
    public synchronized void addData(byte[] data) {
        try {
            // If the queue size exceeds 500, remove the oldest entry
            if (objQueueDisBrdPid.size() > 500) {
                objQueueDisBrdPid.remove();
            }
            // Add the new data to the end of the queue
            objQueueDisBrdPid.offerLast(data);
        } catch (Exception ex) {
            // Handle any exception that occurs during adding
        }
        // Set the input data to null to aid garbage collection
        data = null;
    }

    /**
     * Removes and returns the oldest data from the DisBrdPids queue.
     * This method is synchronized to ensure thread safety.
     * 
     * @return the oldest data, or null if an exception occurs
     */
    public synchronized byte[] removeData() {
        byte[] data = null;
        try {
            // Remove and return the oldest data from the queue
            return(byte[]) objQueueDisBrdPid.pollFirst();
        } catch (Exception ex) {
            // Handle any exception that occurs during removal
        }
        // Return null if an exception occurs
        return data;
    }

    /**
     * Returns the current size of the DisBrdPids queue.
     * This method is synchronized to ensure thread safety.
     * 
     * @return the number of elements in the queue
     */
    public synchronized int disBrdQueueCnt() {
        return objQueueDisBrdPid.size();
    }    
}
