package obuits;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.concurrent.LinkedBlockingDeque;

/**
 * A class to manage a deque of NMEA data strings.
 * Provides methods to add, remove, and get the length of the deque.
 * 
 * Author: Sumitha
 */
public class clsNmeaDequeList {

    // Static deque to store NMEA data strings, initialized with LinkedBlockingDeque
    static LinkedBlockingDeque nmeaDeque = new LinkedBlockingDeque();

    /**
     * Adds a new NMEA data string to the deque.
     * If the deque size exceeds 1000, the oldest entry is removed.
     * 
     * @param data the NMEA data string to be added to the deque
     */
    public static void addNmea(String data) {
        try {
            // If the deque size exceeds 1000, remove the oldest entry
            if (nmeaDeque.size() > 1000) {
                nmeaDeque.remove();
            }
            // Add the new NMEA data string to the deque
            nmeaDeque.add(data);
        } catch (Exception ex) {
            // Handle any exception that occurs during adding
        }
        // Set the input data to null to aid garbage collection
        data = null;
    }

    /**
     * Removes and returns the oldest NMEA data string from the deque.
     * 
     * @return the oldest NMEA data string, or an empty string if an exception occurs
     */
    public static String removeNmea() {
        try {
            // Remove and return the oldest NMEA data string from the deque
            return (String) nmeaDeque.remove();
        } catch (Exception ex) {
            // Handle any exception that occurs during removal
        }
        // Return an empty string if an exception occurs
        return "";
    }

    /**
     * Returns the current size of the NMEA deque.
     * 
     * @return the number of elements in the deque, or 0 if an exception occurs
     */
    public static int getLength() {
        try {
            // Return the current size of the deque
            return nmeaDeque.size();
        } catch (Exception ex) {
            // Handle any exception that occurs during size retrieval
        }
        // Return 0 if an exception occurs
        return 0;
    }
}
