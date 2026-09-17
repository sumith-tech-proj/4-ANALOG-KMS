package obuits;

import static obuits.clsDefines.MAX_NO_SLOGAN_ID_FILES;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsDisplayBrdSerialPort.objdisQue;
import static obuits.clsSharedVariables.getArtIntEnable;
import static obuits.clsSharedVariables.getArtSideEnable;
import static obuits.clsSharedVariables.getArticulatedBus;
import static obuits.clsSharedVariables.getCurRouteStat;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getCurTripStat;
import static obuits.clsSharedVariables.getFrontEnable;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.getNoRoutes;
import static obuits.clsSharedVariables.getRearEnable;
import static obuits.clsSharedVariables.getSchRouteEnable;
import static obuits.clsSharedVariables.getSideEnable;
import static obuits.clsSharedVariables.objRouteMasFiles;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSwitchStates.SCH_INIT_STATE;

public class ClsSchedule {

    static byte sch_state = SCH_INIT_STATE;
    final clsReadFiles objReadFiles = new clsReadFiles();

    // Method to initialize schedule
    public void schedule_init() {
        short i = 0;
        short cur_trip_no = getCurTripNo();
        String[] id_files;
        objReadFiles.read_current_route_info(); // Read current route information

        try {
            if (getCurRouteStat() == true) {
                id_files = new String[MAX_NO_SLOGAN_ID_FILES];
                // Send slogan files data to Internal display board
                if (cur_trip_no >= objRouteMasFiles[cur_trip_no].no_slogans) {
                    for (i = 0; i < objRouteMasFiles[cur_trip_no].no_slogans; i++) {
                        id_files[i] = objRouteMasFiles[cur_trip_no].slogan_files[i];
                    }
                }
            }
        } catch (Exception ex) {
            // Handle exceptions
        }

        // Read current trip information
        objReadFiles.read_current_trip_info();

        // Read current schedule information
        objReadFiles.read_cur_schedule_file();
        if (getCurTripStat() == TRIP_START) {
            send_route_info(); // Send route information if trip has started
        }

        if (getSchRouteEnable() != ROUTE_ENABLE) {
            // Read permanent and temporary schedule information if current scheduled route is not enabled
            objReadFiles.read_permanent_schedule_file();
            objReadFiles.read_temp_schedule_file();
        }

        id_files = null; // Reset id_files array
    }

    // Method to check route availability
    public boolean check_route_availability() {
        short j = 0;
        short cur_trip_no = getCurTripNo();
        if (getCurSchNoTrips() <= 0) {
            return false; // No scheduled trips, route not available
        }

        if (cur_trip_no < getCurSchNoTrips()) {
            for (j = 0; j < getNoRoutes(); j++) {
                if (objRouteMasFiles[j].route_no.equals(getCurSchRouteNo(cur_trip_no))) {
                    return true; // Route available
                }
            }
        }
        return false; // Route not available
    }

    // Method to send route information
    public synchronized boolean send_route_info() {
        short i = 0;
        short j = 0;
        short cur_trip_no = getCurTripNo();

        try {
            if (getCurSchNoTrips() <= 0) {
                return false; // No scheduled trips
            }

            // Display route number
            if (getCurRouteStat() == false) {
                setCurRouteStat(true); // Set current route status to true
                objReadFiles.write_cur_route_info_file(); // Update route status
            }

            if (cur_trip_no < getCurSchNoTrips()) {
                for (j = 0; j < getNoRoutes(); j++) {
                    if (objRouteMasFiles[j].route_no.equals(getCurSchRouteNo(cur_trip_no))) {
                        // Send data to FD (Front Display)
                        try {
                            objdisQue.removeAllFSRMessagesQueue(); // Remove all messages from queue
                            if (getFrontEnable()) {
                                objdisQue.addFSRData(objRouteMasFiles[j].front_file_name + "," + 0); // Add front data to queue
                            }
                            if (getSideEnable()) {
                                objdisQue.addFSRData(objRouteMasFiles[j].side_file_name + "," + 0); // Add side data to queue
                            }
                            if (getRearEnable()) {
                                objdisQue.addFSRData(objRouteMasFiles[j].rear_file_name + "," + 0); // Add rear data to queue
                            }
                            if (getArtSideEnable() && getArticulatedBus()) {
                                objdisQue.addFSRData(objRouteMasFiles[j].side_file_name + "articulated" + "," + 0); // Add articulated side data to queue
                            }
                        } catch (Exception ex) {
                            // Handle exceptions
                        }

                        objReadFiles.readBdcFile(objRouteMasFiles[j].bdc_file_name); // Read BDC file
                        if (getIntEnable()) {
                            for (i = 0; i < objRouteMasFiles[j].no_slogans; i++) {
                                // Send data to ID (Internal Display) board
                                objdisQue.addFSRData(objRouteMasFiles[j].slogan_files[i] + "," + 0); // Add slogan data to queue
                                if (getArtIntEnable() && getArticulatedBus()) {
                                    objdisQue.addFSRData(objRouteMasFiles[j].slogan_files[i] + "articulated" + "," + 0); // Add articulated slogan data to queue
                                }
                            }
                        }
                        objReadFiles.read_locationbased_ads_master_file(objRouteMasFiles[j].route_no); // Read location-based ads master file
                        return true; // Route information sent successfully
                    }
                }
            }
        } catch (Exception ex) {
            // Handle exceptions
        }
        return false; // Failed to send route information
    }
}
