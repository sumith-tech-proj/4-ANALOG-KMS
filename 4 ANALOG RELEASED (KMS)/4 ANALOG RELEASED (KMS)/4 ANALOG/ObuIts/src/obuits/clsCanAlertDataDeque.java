package obuits;

import java.util.concurrent.LinkedBlockingDeque;

// Class for managing a deque storing CAN data alerts
public class clsCanAlertDataDeque {

    // Static deque to store CAN data alerts
    static LinkedBlockingDeque canDataDeque = new LinkedBlockingDeque();

    // Method to add CAN data alert to the deque
    public void addCanDataServer(byte[] data) {
        try {
            // Check if deque size exceeds 500, remove oldest element if necessary
            if (canDataDeque.size() > 500) {
                canDataDeque.remove();
            }
            // Add new CAN data alert to the deque
            canDataDeque.add(data);

        } catch (Exception ex) {
            // Exception handling
        }

        data = null; // Set input data variable to null for cleanup
    }

    // Method to remove and return the oldest CAN data alert from the deque
    public byte[] removeCanDataServer() {
        try {
            // Check if deque is not empty, then remove and return the oldest element
            if (canDataDeque.size() > 0) {
                return (byte[]) canDataDeque.remove();
            }
        } catch (Exception ex) {
            // Exception handling
        }
        return null; // Return null if deque is empty or if an exception occurs
    }

    // Method to get the current length (number of elements) of the deque
    public int getLength() {
        try {
            // Return the size of the deque
            return canDataDeque.size();
        } catch (Exception ex) {
            // Exception handling
        }
        return 0; // Return 0 if an exception occurs
    }

}
