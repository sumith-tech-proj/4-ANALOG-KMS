package obuits;

import java.util.concurrent.LinkedBlockingDeque;

public class clsCanDataQueue {

    // Declare two linked blocking deque objects for storing strings
    static LinkedBlockingDeque canDataQueue = new LinkedBlockingDeque();
    static LinkedBlockingDeque canDataQueue1 = new LinkedBlockingDeque();

    // Method to add data to the first queue
    public void addData(String data) {
        try {
            // If the first queue exceeds 2500 elements, remove the oldest element
            if (canDataQueue.size() > 2500) {
                canDataQueue.remove();
            }
            // Add data to the first queue
            canDataQueue.add(data);
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Ensure the data reference is cleared to free memory
        data = null;
    }

    // Method to remove data from the first queue
    public String removeData() {
        try {
            // Remove and return the first element from the first queue
            return (String) canDataQueue.remove();
        } catch (Exception ex) {
            // Handle any exceptions silently and return an empty string
        }
        // Return an empty string if an exception occurs
        return "";
    }

    // Method to get the length of the first queue
    public int getLength() {
        try {
            // Return the size of the first queue
            return canDataQueue.size();
        } catch (Exception ex) {
            // Handle any exceptions silently and return 0
        }
        // Return 0 if an exception occurs
        return 0;
    }

    // Method to add data to the second queue
    public void addData1(String data) {
        try {
            // If the second queue exceeds 2500 elements, remove the oldest element
            if (canDataQueue1.size() > 2500) {
                canDataQueue1.remove();
            }
            // Add data to the second queue
            canDataQueue1.add(data);
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Ensure the data reference is cleared to free memory
        data = null;
    }

    // Method to remove data from the second queue
    public String removeData1() {
        try {
            // Remove and return the first element from the second queue
            return (String) canDataQueue1.remove();
        } catch (Exception ex) {
            // Handle any exceptions silently and return an empty string
        }
        // Return an empty string if an exception occurs
        return "";
    }

    // Method to get the length of the second queue
    public int getLength1() {
        try {
            // Return the size of the second queue
            return canDataQueue1.size();
        } catch (Exception ex) {
            // Handle any exceptions silently and return 0
        }
        // Return 0 if an exception occurs
        return 0;
    }

}
