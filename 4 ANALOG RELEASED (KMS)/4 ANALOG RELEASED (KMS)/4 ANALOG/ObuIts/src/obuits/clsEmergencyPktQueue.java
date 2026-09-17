package obuits;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * This class manages emergency packets using two separate linked blocking
 * deques.
 */
public class clsEmergencyPktQueue {

    // Deque for storing emergency packets
    static LinkedBlockingDeque emergencyDeque = new LinkedBlockingDeque();
    // Second deque for storing emergency packets
    static LinkedBlockingDeque emergencyDeque2 = new LinkedBlockingDeque();

    /**
     * Adds an emergency packet to the first deque.
     *
     * @param data The emergency packet to add
     */
    public void addEmergencyPkt(byte[] data) {
        try {
            // Check if the deque size exceeds the limit, and remove the oldest packet if necessary
            if (emergencyDeque.size() > 10) {
                emergencyDeque.remove();
            }

            // Add the emergency packet to the deque
            emergencyDeque.add(data);
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        // Set the data reference to null to release memory
        data = null;
    }

    /**
     * Removes and returns the oldest emergency packet from the first deque.
     *
     * @return The oldest emergency packet, or null if the deque is empty or an
     * exception occurs
     */
    public byte[] removeEmergencyPkt() {
        try {
            return (byte[]) emergencyDeque.remove();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        return null;
    }

    /**
     * Returns the current number of emergency packets stored in the first
     * deque.
     *
     * @return The number of emergency packets in the deque
     */
    public int getLength() {
        try {
            return emergencyDeque.size();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        return 0;
    }

    /**
     * Adds an emergency packet to the second deque.
     *
     * @param data The emergency packet to add
     */
    public void addEmergencyPktIP2(byte[] data) {
        try {
            // Check if the deque size exceeds the limit, and remove the oldest packet if necessary
            if (emergencyDeque2.size() > 10) {
                emergencyDeque2.remove();
            }

            // Add the emergency packet to the deque
            emergencyDeque2.add(data);
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        // Set the data reference to null to release memory
        data = null;
    }

    /**
     * Removes and returns the oldest emergency packet from the second deque.
     *
     * @return The oldest emergency packet, or null if the deque is empty or an
     * exception occurs
     */
    public byte[] removeEmergencyPktIP2() {
        try {
            return (byte[]) emergencyDeque2.remove();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        return null;
    }

    /**
     * Returns the current number of emergency packets stored in the second
     * deque.
     *
     * @return The number of emergency packets in the deque
     */
    public int getLengthIP2() {
        try {
            return emergencyDeque2.size();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        return 0;
    }
}
