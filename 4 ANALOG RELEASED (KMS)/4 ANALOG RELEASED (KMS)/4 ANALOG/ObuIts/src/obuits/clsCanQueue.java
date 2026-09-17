package obuits;

import java.util.concurrent.LinkedBlockingDeque;

public class clsCanQueue {

    // Declare two linked blocking deque objects for storing byte arrays
    static LinkedBlockingDeque canQueue = new LinkedBlockingDeque();
    static LinkedBlockingDeque canQueueIP5 = new LinkedBlockingDeque();

    // Method to add data to the main queue
    public void addData(byte[] data) {
        try {
            // If the main queue exceeds 5000 elements, remove the oldest element
            if (canQueue.size() > 5000) {
                canQueue.remove();
            }
            // Add data to the main queue
            canQueue.add(data);
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Ensure the data reference is cleared to free memory
        data = null;
    }

    // Method to add data to the IP5 queue
    public void addDataIp5(byte[] data) {
        // Check if IP5 feature is enabled
        if (clsSharedVariables.getIpAddr5Enable()) {
            // If the IP5 queue exceeds 500 elements, remove the oldest element
            if (canQueueIP5.size() > 500) {
                canQueueIP5.remove();
            }
            // Add data to the IP5 queue
            canQueueIP5.add(data);
        }
        // Ensure the data reference is cleared to free memory
        data = null;
    }

    // Method to get the last data from the IP5 queue
    public synchronized byte[] GetLastDataIp5() {
        byte[] data = new byte[1]; // Initialize with an arbitrary byte array
        try {
            // If the IP5 queue is not empty, retrieve and remove the last element
            if (!canQueueIP5.isEmpty()) {
                data = (byte[]) canQueueIP5.pollLast(); // Retrieves and removes from the linked list
            }
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return the retrieved data
        return data;
    }

    // Method to remove data from the main queue
    public byte[] removeData() {
        try {
            // Remove and return the first element from the main queue
            return (byte[]) canQueue.remove();
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Return null if an exception occurs
        return null;
    }

    // Method to remove data from the IP5 queue
    public byte[] removeDataIP5() {
        try {
            // Remove and return the first element from the IP5 queue
            return (byte[]) canQueueIP5.remove();
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Return null if an exception occurs
        return null;
    }

    // Method to get the length of the main queue
    public int getLength() {
        try {
            // Return the size of the main queue
            return canQueue.size();
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Return 0 if an exception occurs
        return 0;
    }

    // Method to get the length of the IP5 queue
    public int getLengthIP5() {
        try {
            // Return the size of the IP5 queue
            return canQueueIP5.size();
        } catch (Exception ex) {
            // Handle any exceptions silently
        }
        // Return 0 if an exception occurs
        return 0;
    }
}
