package obuits;

import java.util.LinkedList;

public class clsLinkedList {

    // Declare three linked lists to store data packets
    private static final LinkedList linkListDrvingPkts = new LinkedList();
    private static final LinkedList linkListDrvingPkts4 = new LinkedList();
    private static final LinkedList linkListDrvingPkts5 = new LinkedList();

    // Method to add data to the first linked list
    public synchronized void addLastData(String data) {
        try {
            // If the size of the first linked list exceeds 2000, remove the first element
            if (linkListDrvingPkts.size() > 2000) {
                linkListDrvingPkts.removeFirst();
            }
            // Add data to the end of the first linked list
            linkListDrvingPkts.addLast(data);
            // Set data reference to null to free memory
            data = null;
        } catch (Exception e) {
            // Handle any exceptions silently
        }
    }

    // Method to add data to the second linked list
    public synchronized void addLastData4(String data) {
        try {
            // If the size of the second linked list exceeds 2000, remove an element
            if (linkListDrvingPkts4.size() > 2000) {
                linkListDrvingPkts4.remove();
            }
            // Add data to the end of the second linked list
            linkListDrvingPkts4.addLast(data);
            // Set data reference to null to free memory
            data = null;
        } catch (Exception e) {
            // Handle any exceptions silently
        }
    }

    // Method to add data to the third linked list
    public synchronized void addLastData5(String data) {
        try {
            // If the size of the third linked list exceeds 2000, remove an element
            if (linkListDrvingPkts5.size() > 2000) {
                linkListDrvingPkts5.remove();
            }
            // Add data to the end of the third linked list
            linkListDrvingPkts5.addLast(data);
            // Set data reference to null to free memory
            data = null;
        } catch (Exception e) {
            // Handle any exceptions silently
        }
    }

    // Method to get the last data from the first linked list
    public synchronized String GetLastData() {
        String data = "";
        try {
            // If the first linked list has only one element, poll it
            if (linkListDrvingPkts.size() == 1) {
                data = linkListDrvingPkts.poll().toString();
            } else if (linkListDrvingPkts.size() > 1) {
                data = linkListDrvingPkts.pollLast().toString(); // retrieves and removes from the linked list

            }
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        return data;
    }

    // Method to get the last data from the second linked list
    public synchronized String GetLastData4() {
        String data = "";
        try {
            // If the second linked list has only one element, poll it
            if (linkListDrvingPkts4.size() == 1) {
                data = linkListDrvingPkts4.poll().toString();
            } else if (linkListDrvingPkts4.size() > 1) {
                data = linkListDrvingPkts4.pollLast().toString(); // retrieves and removes from the linked list
            }
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        return data;
    }

    // Method to get the last data from the third linked list
    public synchronized String GetLastData5() {
        String data = "";
        try {
            // If the third linked list has only one element, poll it
            if (linkListDrvingPkts5.size() == 1) {
                data = linkListDrvingPkts5.poll().toString();
            } else if (linkListDrvingPkts5.size() > 1) {
                data = linkListDrvingPkts5.pollLast().toString(); // retrieves and removes from the linked list
            }
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        return data;
    }

    // Method to get the first data from the first linked list
    public synchronized String GetFirstData() {
        try {
            // Poll and return the first element from the first linked list
            return linkListDrvingPkts.poll().toString();
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return an empty string if an exception occurs
        return "";
    }

    // Method to get the first data from the second linked list
    public synchronized String GetFirstData4() {
        try {
            // Poll and return the first element from the second linked list
            return linkListDrvingPkts4.poll().toString();
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return an empty string if an exception occurs
        return "";
    }

    // Method to get the first data from the third linked list
    public synchronized String GetFirstData5() {
        try {
            // Poll and return the first element from the third linked list
            return linkListDrvingPkts5.poll().toString();
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return an empty string if an exception occurs
        return "";
    }

    // Method to get the length of the first linked list
    public synchronized int getLength() {
        try {
            // Return the size of the first linked list
            return linkListDrvingPkts.size();
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return 0 if an exception occurs
        return 0;
    }

    // Method to get the length of the second linked list
    public synchronized int getLength4() {
        try {
            // Return the size of the second linked list
            return linkListDrvingPkts4.size();
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return 0 if an exception occurs
        return 0;
    }

    // Method to get the length of the third linked list
    public synchronized int getLength5() {
        try {
            // Return the size of the third linked list
            return linkListDrvingPkts5.size();
        } catch (Exception e) {
            // Handle any exceptions silently
        }
        // Return 0 if an exception occurs
        return 0;
    }
}
