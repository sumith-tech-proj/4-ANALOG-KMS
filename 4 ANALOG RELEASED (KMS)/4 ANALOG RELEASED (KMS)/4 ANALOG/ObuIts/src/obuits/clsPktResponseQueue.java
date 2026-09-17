/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * A class to manage a response queue for packet data.
 * Provides methods to add, remove, and get the length of the queue.
 * 
 * Author: Sumitha
 */
public class clsPktResponseQueue {

    // Static deque to store response data, initialized with LinkedBlockingDeque
    static LinkedBlockingDeque resQueue = new LinkedBlockingDeque();

  

    /**
     * Adds a new packet data to the response queue.
     * If the queue size exceeds 10, the oldest entry is removed.
     * 
     * @param data the packet data to be added to the queue
     */
    public void addData(byte[] data) {
        try {
            // If the queue size exceeds 10, remove the oldest entry
            if (resQueue.size() > 10) {
                resQueue.remove();
            }
            // Add the new packet data to the queue
            resQueue.add(data);
        } catch (Exception ex) {
            // Handle any exception that occurs during adding
        }
        // Set the input data to null to aid garbage collection
        data = null;
    }

    /**
     * Removes and returns the oldest packet data from the response queue.
     * 
     * @return the oldest packet data, or null if an exception occurs
     */
    public byte[] removeData() {
        try {
            // Remove and return the oldest packet data from the queue
            return (byte[])resQueue.remove();
        } catch (Exception ex) {
            // Handle any exception that occurs during removal
        }
        // Return null if an exception occurs
        return null;
    }

    /**
     * Returns the current size of the response queue.
     * 
     * @return the number of elements in the queue, or 0 if an exception occurs
     */
    public int getLength() {
        try {
            // Return the current size of the queue
            return resQueue.size();
        } catch (Exception ex) {
            // Handle any exception that occurs during size retrieval
        }
        // Return 0 if an exception occurs
        return 0;
    }
}
