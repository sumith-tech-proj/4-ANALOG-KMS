package obuits;

import java.util.logging.Level;
import java.util.logging.Logger;

// Main class for the OBU (On-Board Unit) ITS (Intelligent Transportation System) application
public class ObuIts {
    
    public static void main(String[] args) {
        
        // Variable to track if the main frame is opened
        boolean main_frm_opened = false;
        
        // Continuous loop for running the application
        while (true) {
            try {
                // Check if the main frame is not opened
                if (main_frm_opened == false) {
                    main_frm_opened = true; // Mark main frame as opened
                    MainFrmIts obj = new MainFrmIts(); // Create an instance of the main frame
                    obj.setAlwaysOnTop(false); // Set main frame to not always on top
                    obj.show(); // Show the main frame
                }
            } catch (Exception ex) {
                // Log any exceptions that occur
                Logger.getLogger(ObuIts.class.getName()).log(Level.SEVERE, null, ex);
               
	        main_frm_opened = false; // Mark main frame as closed in case of exception
            }
            try {
                Thread.sleep(5000); // Sleep for 5 seconds before next iteration
            } catch (InterruptedException ex) {
                // Log any interruptions that occur during sleep
                Logger.getLogger(ObuIts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
