package obuits;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * The clsDriverPktQueue class manages a queue for driver packets.
 */
public class clsDriverPktQueue {

    // Queue to store driver packets
    static LinkedBlockingDeque driverDeque = new LinkedBlockingDeque();

    /**
     * Method to add a driver packet to the queue.
     *
     * @param data The driver packet data to be added.
     */
    public void addDriverPkt(byte[] data) {
        try {
            // Clear the queue before adding the new driver packet
            driverDeque.clear();
            // Add the driver packet data to the queue
            driverDeque.add(data);
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
        // Nullify the data reference to facilitate garbage collection
        data = null;
    }

    /**
     * Method to remove all driver packets from the queue.
     */
    public void removeAllDriverPkt() {
        try {
            // Clear all packets from the driver packet queue
            driverDeque.clear();
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
        }
    }
}
