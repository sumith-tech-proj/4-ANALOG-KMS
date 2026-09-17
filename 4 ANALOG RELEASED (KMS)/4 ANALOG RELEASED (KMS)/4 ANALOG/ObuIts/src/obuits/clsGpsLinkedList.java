package obuits;

import java.util.LinkedList;

/**
 * Class to manage multiple linked lists for different types of GPS data. The
 * lists are static and synchronized, meaning they are shared across all
 * instances of this class and thread-safe, ensuring that only one thread can
 * access the methods at a time.
 *
 */
//This class is used to store the gps tracking data from four servers 1,3,4,5
public class clsGpsLinkedList {

    // Static linked lists for storing different types of GPS data.
    private static final LinkedList linkListGpsPkts = new LinkedList();
    private static final LinkedList linkListGpsPkts3 = new LinkedList();
    private static final LinkedList linkListGpsPkts4 = new LinkedList();
    private static final LinkedList linkListGpsPkts5 = new LinkedList();

    /**
     * Adds data to the end of the linkListGpsPkts list.
     *
     * @param data The data to add to the list.
     */
    //This method is used by 
    public synchronized void addLastData(byte[] data) {
        try {
            linkListGpsPkts.offer(data); // offer is equivalent to addLast in this context
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the end of the linkListGpsPkts3 list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addLastDataIp3(byte[] data) {
        try {
            linkListGpsPkts3.addLast(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the end of the linkListGpsPkts4 list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addLastDataIp4(byte[] data) {
        try {
            linkListGpsPkts4.addLast(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the end of the linkListGpsPkts5 list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addLastDataIp5(byte[] data) {
        try {
            linkListGpsPkts5.addLast(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the beginning of the linkListGpsPkts list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addFirstData(byte[] data) {
        try {
            linkListGpsPkts.addFirst(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the beginning of the linkListGpsPkts3 list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addFirstDataIp3(byte[] data) {
        try {
            linkListGpsPkts3.addFirst(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the beginning of the linkListGpsPkts4 list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addFirstDataIp4(byte[] data) {
        try {
            linkListGpsPkts4.addFirst(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Adds data to the beginning of the linkListGpsPkts5 list.
     *
     * @param data The data to add to the list.
     */
    public synchronized void addFirstDataIp5(byte[] data) {
        try {
            linkListGpsPkts5.addFirst(data);
            data = null; // Clear reference to data
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }

    /**
     * Retrieves and removes the last data from the linkListGpsPkts list.
     *
     * @return The last data from the list, or an empty byte array if the list
     * is empty.
     */
    public synchronized byte[] GetLastData() {
        byte[] data = new byte[1]; // Initialize with default byte array
        try {
            if (!linkListGpsPkts.isEmpty()) {
                data = (byte[]) linkListGpsPkts.pollLast(); // Retrieves and removes from the list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Retrieves and removes the last data from the linkListGpsPkts3 list.
     *
     * @return The last data from the list, or an empty byte array if the list
     * is empty.
     */
    public synchronized byte[] GetLastDataIp3() {
        byte[] data = new byte[1]; // Initialize with default byte array
        try {
            if (!linkListGpsPkts3.isEmpty()) {
                data = (byte[]) linkListGpsPkts3.pollLast(); // Retrieves and removes from the list
            }
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return data;
    }

    /**
     * Retrieves and removes the last data from the linkListGpsPkts4 list.
     *
     * @return The last data from the list, or an empty byte array if the list
     * is empty.
     */
    public synchronized byte[] GetLastDataIp4() {
        byte[] data = new byte[1]; // Initialize with default byte array
        try {
            if (!linkListGpsPkts4.isEmpty()) {
                data = (byte[]) linkListGpsPkts4.pollLast(); // Retrieves and removes from the list
            }
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return data;
    }

    /**
     * Retrieves and removes the last data from the linkListGpsPkts5 list.
     *
     * @return The last data from the list, or an empty byte array if the list
     * is empty.
     */
    public synchronized byte[] GetLastDataIp5() {
        byte[] data = new byte[1]; // Initialize with default byte array
        try {
            if (!linkListGpsPkts5.isEmpty()) {
                data = (byte[]) linkListGpsPkts5.pollLast(); // Retrieves and removes from the list
            }
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return data;
    }

    /**
     * Returns the number of items in the linkListGpsPkts list.
     *
     * @return The number of items in the list, or 0 if an exception occurs.
     */
    public synchronized int getLength() {
        try {
            return linkListGpsPkts.size();
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return 0;
    }

    /**
     * Returns the number of items in the linkListGpsPkts3 list.
     *
     * @return The number of items in the list, or 0 if an exception occurs.
     */
    public synchronized int getLengthIp3() {
        try {
            return linkListGpsPkts3.size();
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return 0;
    }

    /**
     * Returns the number of items in the linkListGpsPkts4 list.
     *
     * @return The number of items in the list, or 0 if an exception occurs.
     */
    public synchronized int getLengthIp4() {
        try {
            return linkListGpsPkts4.size();
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return 0;
    }

    /**
     * Returns the number of items in the linkListGpsPkts5 list.
     *
     * @return The number of items in the list, or 0 if an exception occurs.
     */
    public synchronized int getLengthIp5() {
        try {
            return linkListGpsPkts5.size();
        } catch (Exception e) {
            // Handle exception if necessary
        }
        return 0;
    }
}
