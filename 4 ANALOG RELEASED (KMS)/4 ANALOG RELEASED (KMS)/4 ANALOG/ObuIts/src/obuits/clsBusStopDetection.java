package obuits;

import java.io.File;
import static obuits.clsDefines.ARRIVAL_MIN_DISTANE;
import static obuits.clsDefines.AVG_BUS_SPEED;
import static obuits.clsDefines.SCHDEULE_ENABLE;
import static obuits.clsDefines.TRIP_END;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsSharedVariables.getCurAutoTripStat;
import static obuits.clsSharedVariables.getCurLatitude;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getDriverStopCnt;
import static obuits.clsSharedVariables.getGpsSpeed;
import static obuits.clsSharedVariables.getNoStopsRoute;
import static obuits.clsSharedVariables.getNonStopTime;
import static obuits.clsSharedVariables.getPrevLat;
import static obuits.clsSharedVariables.getPrevLong;
import static obuits.clsSharedVariables.getRouteDevDistance;
import static obuits.clsSharedVariables.getSchRouteEnable;
import static obuits.clsSharedVariables.getStoptoStopDis;
import static obuits.clsSharedVariables.getTripDetDistance;
import static obuits.clsSharedVariables.incCurTripNo;
import static obuits.clsSharedVariables.incDriverStopCnt;
import static obuits.clsSharedVariables.setCurRouteNo;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSharedVariables.setCurSchTripStatus;
import static obuits.clsSharedVariables.setCurStopNo;
import static obuits.clsSharedVariables.setCurTripStat;
import static obuits.clsSharedVariables.setMapStopState;
import static obuits.clsSharedVariables.setNoStopsRoute;
import static obuits.clsSharedVariables.setNxtLatitude;
import static obuits.clsSharedVariables.setNxtLongitude;
import static obuits.clsSharedVariables.setPrevLat;
import static obuits.clsSharedVariables.setShowStopStopDis;
import static obuits.clsSharedVariables.setStopState;
import static obuits.clsSharedVariables.setStoptoStopDis;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.Calendar;
import javax.swing.SwingWorker;
import static obuits.PanDisplayBoardDiag.setDisBrdDelPktCame;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.route_filepath;

import static obuits.clsDisplayBrdSerialPort.objdisQue;
import static obuits.clsInternalDisBrdMessage.fill_internal_details;
//import static obuits.clsPeopleCntEventClear.peopleCountRouteEnd;
import static obuits.clsSharedVariables.current_loc_based_ad;
import static obuits.clsSharedVariables.getApcStatus;
import static obuits.clsSharedVariables.getArtIntEnable;
import static obuits.clsSharedVariables.getArticulatedBus;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getCurStopNo;
import static obuits.clsSharedVariables.getCurTripStat;
import static obuits.clsSharedVariables.getDisBrdName;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.getNonStopDistance;
import static obuits.clsSharedVariables.getSimulationEnabled;
import static obuits.clsSharedVariables.gps_data;
import static obuits.clsSharedVariables.objAudQue;
import static obuits.clsSharedVariables.objLocBasedAdsFiles;
import static obuits.clsSharedVariables.setApcStatus;
import static obuits.clsSharedVariables.setPrevLong;

// Class for handling bus stop detection logic
public class clsBusStopDetection {

    // Enum for bus stop state
    public static boolean beep_sound_start = false;
    static short gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
    static short stop_duration = 0;
    static short stop_deviation = 0;
    static double last_pnt_distance = 0;
    static short last_dist_cnt = 0;
    static double local_distance = 0;
    static double bus_stop_distance = 0; // this is used to exactly find out the stop
    static boolean bus_stop_dis_flag = false;
    static boolean next_stop_dis_flag = false;
    static short cur_stop_dis_cnt = 0;
    static boolean stop_stop_dis_flag = false;
    static double route_div_distance = 0;
    static byte audio_play_cnt = 0;
    static boolean audio_playing = false;
    static boolean next_announce = false;
    static boolean app_announce = false;
    static boolean cur_announce = false;
    boolean first_time_map = true;
   // clsApcPacketConstruct objApc = new clsApcPacketConstruct();
    static long speed_0_start_time = 0;
    static long speed_0_end_time = 0;
    static boolean speed_0_started = false;
    static double route_dev_dis_inc = 0;
    static double next_stop_distance = 0;
    static double next_stop_full_distance = 0;
    static boolean next_stop_full_detect = false;
    static double next_stop_from_current_dis = 0;
    static double prev_dis = 0;

    static double prev_detected_non_stop_lat = 0.0;
    static double prev_detected_non_stop_lon = 0.0;
    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
    //  clsApcPacketConstruct objApc = new clsApcPacketConstruct();

    //  clsAudioFilesQueue objAudQue = new clsAudioFilesQueue();
    public static void setNextAnnounce(boolean stat) {
        next_announce = stat;
    }

    public static boolean getNextAnnounce() {
        return next_announce;
    }

    public void setAppAnnounce(boolean stat) {
        app_announce = stat;
    }

    // Getter for app announcement
    public boolean getAppAnnounce() {
        return app_announce;
    }

    // Setter for current announcement
    public void setCurAnnounce(boolean stat) {
        cur_announce = stat;
    }

    // Getter for current announcement
    public boolean getCurAnnounce() {
        return cur_announce;
    }

    // Inner class for traffic signal locations
    public static class clsTrafficSignal {

        public double[] latitude = new double[clsDefines.MAX_NO_TRAFFIC_SIGNAL_POINTS];
        public double[] longitude = new double[clsDefines.MAX_NO_TRAFFIC_SIGNAL_POINTS];
        public int geofence_traffic = 50;
    }
    public static clsTrafficSignal objTrafficPoints = new clsTrafficSignal();
    public static int no_traffic_points = 0;

    // Inner class for predefined locations
    public static class clsPreDefinedLocations {

        public double[] latitude = new double[clsDefines.MAX_NO_PRE_DEFINED_LOCATION_POINTS];
        public double[] longitude = new double[clsDefines.MAX_NO_PRE_DEFINED_LOCATION_POINTS];
        public int geofence_pre_def_loc = 50;
    }
    public static clsPreDefinedLocations objPreDefLocPoints = new clsPreDefinedLocations();
    public static int no_pre_def_loc_points = 0;

    // Method to calculate GPS distance
    static Double calc_gps_distance(double lat1, double lang1, double lat2, double lang2) {
        double dDistance = 0;
        double dLat1InRad = 0;
        double dLat2InRad = 0;
        double dLong1InRad = 0;
        double dLong2InRad = 0;
        double dLongitude = 0;
        double dLatitude = 0;
        double a = 0;
        double c = 0;
        double kEarthRadiusKms = 0;
        double x = 0;

        dLat1InRad = lat1 * (Math.PI / 180.0);
        dLat2InRad = lat2 * (Math.PI / 180.0);
        dLong1InRad = lang1 * (Math.PI / 180.0);
        dLong2InRad = lang2 * (Math.PI / 180.0);
        dLongitude = dLong2InRad - dLong1InRad;
        dLatitude = dLat2InRad - dLat1InRad;
        a = pow(sin(dLatitude / 2), 2) + cos(dLat1InRad) * cos(dLat2InRad) * pow(sin(dLongitude / 2), 2);
        c = 2 * atan2(sqrt(a), sqrt(1 - a));
        kEarthRadiusKms = 6376.5;
        dDistance = kEarthRadiusKms * c;
        x = dDistance * 1000;
        return (x);
    }

    // Method for GPS trip start detection
    void gps_trip_start_detection() {

        short i = 0;
        short j = 0;
        short cur_trip_no = getCurTripNo();
        byte cur_stop_no = getCurStopNo();
        int avg_speed = AVG_BUS_SPEED;
        int dur = 100;
        long sec;
        double next_stop_distance;
        gpsDriving objDriving = new gpsDriving();
        Calendar cal = Calendar.getInstance();
        if (getCurAutoTripStat() == false) {
            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
            return;
        } else {
            ClsSchedule objSch = new ClsSchedule();

            clsReadFiles obj = new clsReadFiles();
            local_distance = calc_gps_distance(getCurLatitude(), getCurLongitude(), gps_data[0].latitude,
                    gps_data[0].longitude);

            if (local_distance <= (getTripDetDistance() + 30)) {
                setCurTripStat(clsDefines.TRIP_START);

                setCurSchTripStatus(cur_trip_no, clsDefines.TRIP_START);

                setCurTripStat(TRIP_START);
                setCurSchTripStatus(cur_trip_no, TRIP_START);
                setCurRouteNo(getCurSchRouteNo(cur_trip_no));
                objSch.send_route_info();

                sec = Calendar.getInstance().getTimeInMillis();

                try {
                    if (getNoStopsRoute() > 1) {
                        next_stop_distance = gps_data[getNoStopsRoute() - 1].stop_stop_dis;
                        dur = (int) (next_stop_distance * 18) / (avg_speed * 5);  //(formaula : time = ((distance * 18)/(speed *5) )
                    }
                } catch (Exception ex) {
                    dur = 10;
                }
                dur = dur * 1000;

                cal.setTimeInMillis(sec + dur);
                if (clsSharedVariables.getIpAddr1Enable()) {
                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));
                    obj16833Pkts = null;
                }
                if (clsSharedVariables.getIpAddr4Enable()) {

                    objDriving.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));

                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_START);

                }
//                try {
//
//                    SwingWorker sw1 = new SwingWorker() {
//                        @Override
//                        protected String doInBackground() throws Exception {
//                            clsPeopleCntEventClear objWaterMarking = new clsPeopleCntEventClear();
//                            objWaterMarking.setwatermarkforrir();
//                            objWaterMarking.setwatermarkforrir1();
//                            MainFrmIts.lblPeopleOut2.setText("0");
//                            MainFrmIts.lblPeopleIn2.setText("0");
//                            clsSharedVariables.setApcPeopleCountIn(0);
//                            clsSharedVariables.setApcPeopleCountOut(0);
//                            clsSharedVariables.setApcPeopleIn(0);
//                            clsSharedVariables.setApcPeopleOut(0);
//                            objWaterMarking.clearPeopleCountValues();
//                            objWaterMarking.setWaterMarkingDahuaApc(clsSharedVariables.getApcPeopleCountIn(), clsSharedVariables.getApcPeopleCountOut());
//                            clsSharedVariables.setApcPeopleIn1(0);
//                            clsSharedVariables.setApcPeopleOut1(0);
//                            objWaterMarking.clearPeopleCountValues1();
//                            objWaterMarking.setWaterMarkingDahuaApc1(clsSharedVariables.getApcPeopleCountIn(), clsSharedVariables.getApcPeopleCountOut());
//
//                            objWaterMarking = null;
//                            return "Over";
//                        }
//                    };
//                    sw1.execute();
//                } catch (Exception ex) {
//
//                }
//                if (getApcStatus() != clsDefines.APC_START) {
//                    setApcStatus(clsDefines.APC_START);
//                    objApc.apc_start_packet(getCurSchRouteNo(cur_trip_no), gps_data[cur_stop_no + 1].stop_name);
//
//                }

                if (cur_stop_no < getNoStopsRoute()) {
                    setNxtLatitude(gps_data[cur_stop_no + 1].latitude);
                    setNxtLongitude(gps_data[cur_stop_no + 1].longitude);
                }
                setPrevLat(0.0);
                setPrevLat(0.0);
                setStoptoStopDis(0);
                gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                obj.write_cur_trip_info_file();
                obj.write_cur_schedule_file();
                obj.write_cur_route_info_file();

                clsSharedVariables.setTripStatusUpdated(true);

            }
            objSch = null;

            obj = null;
            cal = null;
        }
    }

    // Method to check traffic signal
    private boolean check_traffic_signal(double cur_lat, double cur_long) {
        int i = 0;
        double dis = 0.0;
        for (i = 0; i < no_traffic_points; i++) {
            dis = calc_gps_distance(cur_lat, cur_long, objTrafficPoints.latitude[i], objTrafficPoints.longitude[i]);
            if (dis <= objTrafficPoints.geofence_traffic) {
                return true;
            }
        }
        return false;
    }

    // Method to check with previous non-stop latitude and longitude
    private boolean check_with_prev_non_stop_lat_long(double cur_lat, double cur_long) {

        double dis = 0.0;
        if (prev_detected_non_stop_lat > 0.0 && prev_detected_non_stop_lon > 0.0) {
            dis = calc_gps_distance(cur_lat, cur_long, prev_detected_non_stop_lat,
                    prev_detected_non_stop_lon);
            if (dis <= getNonStopDistance()) {
                prev_detected_non_stop_lat = cur_lat;
                prev_detected_non_stop_lon = cur_long;
                return true;
            }
        } else {
            prev_detected_non_stop_lat = cur_lat;
            prev_detected_non_stop_lon = cur_long;
            return true;
        }
        return false;
    }

    // Method to check location based advertisements
    private boolean check_location_based_ads(double cur_lat, double cur_long) {
        byte i = 0;
        double dis = 0.0;
        current_loc_based_ad = 0;
        try {
            for (i = current_loc_based_ad; i < clsSharedVariables.no_loc_based_ads; i++) {
                if (objLocBasedAdsFiles[i].status == false) {
                    dis = calc_gps_distance(cur_lat, cur_long, objLocBasedAdsFiles[i].latitude,
                            objLocBasedAdsFiles[i].longitude);
                    clsReadFiles obj = new clsReadFiles();
                    // obj.write_gps_connect_log("Distance " + dis);
                    if (dis <= objLocBasedAdsFiles[i].distance) {
                        objLocBasedAdsFiles[i].status = true;
                        obj.write_gps_connect_log("filename  " + objLocBasedAdsFiles[i].file_name);
                        objdisQue.addData(objLocBasedAdsFiles[i].file_name + ".ids" + "," + 0);
                        String[] audFiles = new String[1];
                        obj.write_gps_connect_log("audio   " + objLocBasedAdsFiles[i].audio_file_name);
                        audFiles[0] = objLocBasedAdsFiles[i].audio_file_name;
                        add_audio_files(audFiles, (byte) 1);
                        audFiles = null;
                        current_loc_based_ad = i;

                        return true;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    // Method to check non-stop location
    private boolean check_non_stop_loc(double cur_lat, double cur_long) {
        int i = 0;
        double dis = 0.0;
        for (i = getCurStopNo(); i < getNoStopsRoute(); i++) {
            dis = calc_gps_distance(cur_lat, cur_long, gps_data[i].latitude,
                    gps_data[i].longitude);
            if (dis <= 50) {
                return true;
            }
        }
        return false;
    }

    // Method to check predefined location signal
    private boolean check_predefined_loc_signal(double cur_lat, double cur_long) {
        int i = 0;
        double dis = 0.0;
        for (i = 0; i < no_pre_def_loc_points; i++) {
            dis = calc_gps_distance(cur_lat, cur_long, objPreDefLocPoints.latitude[i], objPreDefLocPoints.longitude[i]);
            if (dis <= objPreDefLocPoints.geofence_pre_def_loc) {
                return true;
            }
        }
        return false;
    }

    //This method will be used for detecting the bus stops based on gps location.
    public void gps_bus_stop_detection() {
        short i;
        short j = 0;
        int dur = 0;
        double point_point_dis = 0;
        double cur_lat = getCurLatitude();
        double cur_long = getCurLongitude();
        int speed = getGpsSpeed();
        double s_s_dis = getStoptoStopDis();
        short cur_trip_no = getCurTripNo();
        double cur_dis;//
        // double prev_lat = getPrevLat();
        short no_of_stops_in_a_route = getNoStopsRoute();
        int cur_stop_no = getCurStopNo();
        int non_stop_time = getNonStopTime();
        gpsDriving objDriving = new gpsDriving();
        objwatDog.setBusStopDet_watchdog_val((byte) 2);
        clsReadFiles objReadFiles = new clsReadFiles();
        clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
        point_point_dis = calc_gps_distance(cur_lat, cur_long, getPrevLat(), getPrevLong());

        try {

            if (speed == 0 && speed_0_started == false) {
                speed_0_started = true;
                speed_0_start_time = Calendar.getInstance().getTimeInMillis() / 1000;
            } else if (speed > 0 && speed_0_started == true) {
                speed_0_end_time = Calendar.getInstance().getTimeInMillis() / 1000;
                speed_0_started = false;
                if (speed_0_end_time > speed_0_start_time) {
                    dur = (int) (speed_0_end_time - speed_0_start_time);
                    if (dur > non_stop_time && non_stop_time >= 30) {

                        if (gps_bus_stop_state != clsSwitchStates.BUS_STOP_STATE_IDLE) //clsSwitchStates.BUS_STOP_STATE_REACHED || gps_bus_stop_state!=clsSwitchStates.BUS_STOP_STATE_CROSSED) {
                        {  // check if it is  a traffic signal point location
                            if (check_with_prev_non_stop_lat_long(cur_lat, cur_long) == false) {
                                if (check_non_stop_loc(cur_lat, cur_long) == false && check_traffic_signal(cur_lat, cur_long) == false && check_predefined_loc_signal(cur_lat, cur_long) == false) {
                                    //send non stop duration packet
                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                        obj16833Pkts.nonstoppage_pkt((short) non_stop_time, (short) dur);
                                        obj16833Pkts = null;
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        objDriving.nonstoppage_pkt((short) non_stop_time, (short) dur);

                                    }

                                    clsSharedVariables.setDriverStopDur(getDriverStopCnt(), (short) dur);
                                    incDriverStopCnt();
                                }
                            }
                        }
                    }
                }
                speed_0_started = false;
            }
            if (check_location_based_ads(cur_lat, cur_long) == true) {
                if (!objLocBasedAdsFiles[current_loc_based_ad].audio_file_name.equals("")) {

                    String[] audFiles = new String[objLocBasedAdsFiles[current_loc_based_ad].audio_repeat];
                    for (int k = 0; k < objLocBasedAdsFiles[current_loc_based_ad].audio_repeat; k++) {
                        audFiles[k] = objLocBasedAdsFiles[current_loc_based_ad].audio_file_name;
                    }
                    add_audio_files(audFiles, (byte) 3);
                    audFiles = null;
                }
                if (getDisBrdName() == SUMITH_DISBRD) {
                    if (objLocBasedAdsFiles[current_loc_based_ad].file_name.equals("")) {
                        if (getIntEnable()) {
                            fill_internal_details(objLocBasedAdsFiles[current_loc_based_ad].message_name, clsDefines.INTDB_DATA_NOT_SAVE);
                        }
                    } else {
                        if (getIntEnable()) {
                            // clsDisplayBrdQueue objQue = new clsDisplayBrdQueue();
                            File f;
                            if (clsSharedVariables.getHardDriveDetected()) {
                                f = new File(route_filepath, objLocBasedAdsFiles[current_loc_based_ad].file_name + ".ids");
                            } else {
                                f = new File(main_route_filepath, objLocBasedAdsFiles[current_loc_based_ad].file_name + ".ids");

                            }
                            if (f.exists()) {
                                objdisQue.addData(objLocBasedAdsFiles[current_loc_based_ad].file_name + ".ids" + "," + 0);
                            } else {
                                fill_internal_details(objLocBasedAdsFiles[current_loc_based_ad].message_name, clsDefines.INTDB_DATA_NOT_SAVE);
                            }
                            f = null;
                        }
                        if (getArtIntEnable() && getArticulatedBus()) {
                            objdisQue.addData(objLocBasedAdsFiles[current_loc_based_ad].file_name + ".ids" + "articulated" + "," + 0);

                        }
                    }
                }
            }
        } catch (Exception ex) {

        }

        if ((bus_stop_dis_flag == true) && (speed > 0)) {
            bus_stop_distance = bus_stop_distance - point_point_dis;
            if (bus_stop_distance >= 0x7FFFFFF0) {
                bus_stop_distance = 0;
            }
        }

        if ((next_stop_dis_flag == true) && (speed > 0)) {
            next_stop_distance = next_stop_distance - point_point_dis;
            if (next_stop_distance >= 0x7FFFFFF0) {
                next_stop_distance = 0;
            }
        }

        if ((stop_stop_dis_flag == true) && (s_s_dis > 0) && (speed > 0)) {
            s_s_dis = s_s_dis - point_point_dis;
            if (s_s_dis >= 0x7FFFFFF0) {
                s_s_dis = 0;
            }
        }
        if (next_stop_full_detect == true && (speed > 0) && point_point_dis > 0) {

            next_stop_full_distance = next_stop_full_distance - point_point_dis;
            if (next_stop_full_distance >= 0x7FFFFFF0) {
                next_stop_full_distance = 0;
            }
        } else if (point_point_dis < 0) {
            next_stop_full_distance = next_stop_full_distance + point_point_dis;
            if (next_stop_full_distance >= 0x7FFFFFF0) {
                next_stop_full_distance = 0;
            }
        }

        try {
            // current stop checking
            switch (gps_bus_stop_state) {
                case clsSwitchStates.BUS_STOP_STATE_IDLE:
                    // identifying the bus stop
                    stop_stop_dis_flag = false;
                    //lcd_display_line("Identifying Nearest Stop", 24, LCD_THIRD_LINE, LCD_HOME_COL_ADDR, FALSE);

                    if (cur_stop_no < no_of_stops_in_a_route) {
                        setShowStopStopDis(calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no].latitude,
                                gps_data[cur_stop_no].longitude));
                        setMapStopState(clsDefines.IDENTIFY_AUDIO_STOP_STATE);
                    }

                    for (i = 0; i < no_of_stops_in_a_route; i++) {
                        local_distance = calc_gps_distance(cur_lat, cur_long, gps_data[i].latitude,
                                gps_data[i].longitude);
                        cur_dis = gps_data[i].cur_dis;
                        if (cur_dis == 0) {
                            cur_dis = clsDefines.CURRENT_MIN_DISTANE;
                        }
                        if ((gps_data[i].stop_identify_status == false)
                                && (local_distance <= cur_dis + 30)) { // detecting 30 meters before the current stop distance

                            last_pnt_distance = 0;
                            last_dist_cnt = 0;
                            route_div_distance = 0;
                            bus_stop_distance = 0;
                            setCurStopNo((byte) i);

                            for (j = (short) cur_stop_no; j < i; j++) {
                                if (clsSharedVariables.getIpAddr1Enable()) {
                                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                    obj16833Pkts.skip_stop_pkt(gps_data[j].stop_name);
                                    obj16833Pkts = null;
                                }
                                if (clsSharedVariables.getIpAddr4Enable()) {

                                    objDriving.skip_stop_pkt(gps_data[j].stop_name);

                                }

                            }
                            cur_stop_no = i;
                            setCurStopNo((byte) cur_stop_no);
                            if (i == 0) {

                                if (clsSharedVariables.getIpAddr1Enable()) {
                                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                    obj16833Pkts.cur_stop_pkt(gps_data[cur_stop_no].stop_name);
                                    obj16833Pkts = null;
                                }
                                if (clsSharedVariables.getIpAddr4Enable()) {

                                    objDriving.cur_stop_pkt(gps_data[cur_stop_no].stop_name);

                                }

                            }
                            next_stop_full_distance = local_distance;
                            for (i = 0; i < cur_stop_no; i++) {
                                gps_data[i].stop_identify_status = true;
                            }
                            for (; i < no_of_stops_in_a_route; i++) {
                                gps_data[i].stop_identify_status = false;
                            }
                            setAppAnnounce(false);

                            if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                                try {
                                    setNxtLatitude(gps_data[cur_stop_no + 1].latitude);
                                    setNxtLongitude(gps_data[cur_stop_no + 1].longitude);
                                } catch (Exception ex) {

                                }
                            }
                            setStopState(clsDefines.CUR_STOP_STATE);
                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_REACH_ANNOUNCE;
                            setMapStopState(clsDefines.IDENTIFY_AUDIO_STOP_STATE);
                            /*  try {

                                SwingWorker sw1 = new SwingWorker() {
                                    @Override
                                    protected String doInBackground() throws Exception {
                                        clsPeopleCntEventClear objWaterMarking = new clsPeopleCntEventClear();
                                        MainFrmIts.lblPeopleOut2.setText("0");
                                        MainFrmIts.lblPeopleIn2.setText("0");
                                        clsSharedVariables.setApcPeopleCountIn(0);
                                        clsSharedVariables.setApcPeopleCountOut(0);
                                        clsSharedVariables.setApcPeopleIn(0);
                                        clsSharedVariables.setApcPeopleOut(0);
                                        objWaterMarking.setwatermarkforrir();
                                        objWaterMarking.setwatermarkforrir1();
                                        objWaterMarking.clearPeopleCountValues();
                                        objWaterMarking.setWaterMarkingDahuaApc(clsSharedVariables.getApcPeopleCountIn(), clsSharedVariables.getApcPeopleCountOut());
                                        clsSharedVariables.setApcPeopleIn1(0);
                                        clsSharedVariables.setApcPeopleOut1(0);
                                        objWaterMarking.clearPeopleCountValues1();
                                        objWaterMarking.setWaterMarkingDahuaApc1(clsSharedVariables.getApcPeopleCountIn(), clsSharedVariables.getApcPeopleCountOut());

                                        objWaterMarking = null;
                                        return "Over";
                                    }
                                };
                                sw1.execute();
                            } catch (Exception ex) {

                            }*/
 /*  try {
                                if (getApcStatus() != clsDefines.APC_START) {
                                    setApcStatus(clsDefines.APC_START);
                                    objApc.apc_start_packet(getCurSchRouteNo(cur_trip_no), gps_data[getCurStopNo()].stop_name);

                                }
                            } catch (Exception ex) {

                            }*/
                            return;
                        }
                    }

                    if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                        try {
                            setNxtLatitude(gps_data[cur_stop_no + 1].latitude);
                            setNxtLongitude(gps_data[cur_stop_no + 1].longitude);
                        } catch (Exception ex) {

                        }
                    }
                    break;
                case clsSwitchStates.BUS_STOP_STATE_REACH_ANNOUNCE:
                    // end stop distance
                    cur_dis = gps_data[no_of_stops_in_a_route - 1].cur_dis;
                    if (cur_dis == 0) {
                        cur_dis = clsDefines.CURRENT_MIN_DISTANE;
                    }

                    if ((calc_gps_distance(cur_lat, cur_long, gps_data[no_of_stops_in_a_route - 1].latitude,
                            gps_data[no_of_stops_in_a_route - 1].longitude) <= cur_dis)
                            && (cur_stop_no > 1)) { //cur_stop_no !=0 sumitha
                        if (cur_stop_no < no_of_stops_in_a_route) {
                            setShowStopStopDis(0.0);
                        }

                        // disable the stop
                        gps_data[no_of_stops_in_a_route - 1].stop_identify_status = true;

                        //send file to display board
                        if (gps_data[no_of_stops_in_a_route - 1].cur_dis > 0) {
                            if (getIntEnable()) {
                                // clsDisplayBrdQueue objQue = new clsDisplayBrdQueue();
                                objdisQue.addData(gps_data[no_of_stops_in_a_route - 1].cur_db_file + "," + 0);
                            }
                            if (getArtIntEnable() && getArticulatedBus()) {
                                objdisQue.addData(gps_data[no_of_stops_in_a_route - 1].cur_db_file + "articulated" + "," + 0);
                            }
                            String[] audFiles = new String[3];
                            audFiles[0] = gps_data[no_of_stops_in_a_route - 1].cur_eng_file;
                            audFiles[1] = gps_data[no_of_stops_in_a_route - 1].cur_reg1_file;
                            audFiles[2] = gps_data[no_of_stops_in_a_route - 1].cur_reg2_file;

                            clsSharedVariables.objAudQue.removeAllMessagesQueue();
                            add_audio_files(audFiles, (byte) 3);
                            audFiles = null;
                            setCurAnnounce(true);

                        }
                        setMapStopState(clsDefines.CUR_AUDIO_STOP_STATE);
                        // current stop last
                        stop_duration = 0;
                        stop_deviation = 0;

                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.cur_stop_pkt(gps_data[no_of_stops_in_a_route - 1].stop_name);
                            obj16833Pkts = null;
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.cur_stop_pkt(gps_data[no_of_stops_in_a_route - 1].stop_name);

                        }

                        // objDriving.trip_end_pkt();
                        gps_bus_stop_state = clsSwitchStates.BUS_STATE_LAST_STOP;
                        return;
                    }

                    // current stop checking
                    local_distance = calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no].latitude, gps_data[cur_stop_no].longitude);
                    next_stop_full_distance = local_distance;
                    setShowStopStopDis(local_distance);
                    cur_dis = gps_data[cur_stop_no].cur_dis;
                    if (cur_dis == 0) {
                        cur_dis = clsDefines.CURRENT_MIN_DISTANE;
                    }
                    if (getAppAnnounce() == false) {
                        if (gps_data[cur_stop_no].arr_dis == ARRIVAL_MIN_DISTANE) {
                            setAppAnnounce(true);
                            setStopState(clsDefines.CUR_STOP_STATE);
                        } else {
                            setStopState(clsDefines.APP_STOP_STATE);

                            if ((local_distance <= gps_data[cur_stop_no].arr_dis) && local_distance >= cur_dis
                                    && (gps_data[cur_stop_no].stop_identify_status == false)) {
                                setAppAnnounce(true);
                                setStopState(clsDefines.CUR_STOP_STATE);
                                //send file to display board
                                if (!gps_data[cur_stop_no].arr_db_file.equals("")) {
                                    if (getIntEnable()) {
                                        //  clsDisplayBrdQueue objQue = new clsDisplayBrdQueue();

                                        objdisQue.addData(gps_data[cur_stop_no].arr_db_file + "," + 0);

                                    }
                                    if (getArtIntEnable() && getArticulatedBus()) {
                                        objdisQue.addData(gps_data[cur_stop_no].arr_db_file + "articulated" + "," + 0);
                                    }
                                }
                                setMapStopState(clsDefines.APP_AUDIO_STOP_STATE);
                                String[] audFiles = new String[3];
                                audFiles[0] = gps_data[cur_stop_no].arr_eng_file;
                                audFiles[1] = gps_data[cur_stop_no].arr_reg1_file;
                                audFiles[2] = gps_data[cur_stop_no].arr_reg2_file;
                                objAudQue.removeAllMessagesQueue();
                                add_audio_files(audFiles, (byte) 3);
                                audFiles = null;

                                if (clsSharedVariables.getIpAddr1Enable()) {
                                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                    obj16833Pkts.app_stop_pkt(gps_data[cur_stop_no].stop_name);
                                    obj16833Pkts = null;
                                }
                                if (clsSharedVariables.getIpAddr4Enable()) {

                                    objDriving.app_stop_pkt(gps_data[cur_stop_no].stop_name);

                                }

                            }
                        }
                    }

                    if ((local_distance <= cur_dis)
                            && (gps_data[cur_stop_no].stop_identify_status == false)) {
                        bus_stop_distance = local_distance;
                        bus_stop_dis_flag = true;

                        if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                            next_stop_full_distance = calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no + 1].latitude,
                                    gps_data[cur_stop_no + 1].longitude);
                            next_stop_full_detect = true;
                        }

                        next_stop_distance = gps_data[cur_stop_no].next_dis + local_distance;
                        next_stop_dis_flag = true;

                        s_s_dis = gps_data[cur_stop_no].stop_stop_dis + getRouteDevDistance();

                        if (stop_stop_dis_flag == false) {
                            route_dev_dis_inc = local_distance;
                        }
                        if (s_s_dis > 0) {
                            stop_stop_dis_flag = true;
                        }

                        if (gps_data[cur_stop_no].cur_dis > 0) {
                            //send file to display board
                            if (getIntEnable()) {
                                // clsDisplayBrdQueue objQue = new clsDisplayBrdQueue();

                                objdisQue.addData(gps_data[cur_stop_no].cur_db_file + "," + 0);

                            }
                            if (getArtIntEnable() && getArticulatedBus()) {
                                objdisQue.addData(gps_data[cur_stop_no].cur_db_file + "articulated" + "," + 0);
                            }
                            String[] audFiles = new String[3];
                            audFiles[0] = gps_data[cur_stop_no].cur_eng_file;
                            audFiles[1] = gps_data[cur_stop_no].cur_reg1_file;
                            audFiles[2] = gps_data[cur_stop_no].cur_reg2_file;
                            objAudQue.removeAllMessagesQueue();
                            add_audio_files(audFiles, (byte) 3);
                            audFiles = null;

                            setCurAnnounce(true);
                        }
                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.cur_stop_pkt(gps_data[cur_stop_no].stop_name);
                            obj16833Pkts = null;
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.cur_stop_pkt(gps_data[cur_stop_no].stop_name);

                        }

                        setMapStopState(clsDefines.CUR_AUDIO_STOP_STATE);
                        //     play_audio_file( );

                        setStopState(clsDefines.CUR_STOP_STATE);
                        // disable the stop
                        gps_data[cur_stop_no].stop_identify_status = true;

                        stop_duration = 0;
                        stop_deviation = 0;
                        last_dist_cnt = 0;
                        route_div_distance = 0;
                        last_pnt_distance = 0;
                        setShowStopStopDis(0.0);

                        gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_REACHED;
                        return;
                    }

                    prev_dis = local_distance;
                    if (((s_s_dis <= 0) && (stop_stop_dis_flag == true)) && (cur_stop_no > 0)) {

                        if (local_distance >= getRouteDevDistance()) {
                            //last_dist_cnt++;
                            //if ((last_dist_cnt > route_deviate_time)) {
                            last_dist_cnt = 0;
                            route_div_distance = 0;
                            setStopState(clsDefines.ROUTE_DEVIATE_STATE);
                            // objDriving.route_deviate_pkt();

                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
                            return;
                            //  }
                            // last_pnt_distance = local_distance;
                        } else {
                            last_dist_cnt = 0;
                            route_div_distance = 0;
                            last_pnt_distance = local_distance;
                        }

                    } else {
                        last_dist_cnt = 0;
                        route_div_distance = 0;
                        last_pnt_distance = local_distance;
                    }
                    break;
                case clsSwitchStates.BUS_STOP_STATE_REACHED:
                    // end stop of detection
                    local_distance = calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no].latitude,
                            gps_data[cur_stop_no].longitude);

                    if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                        setShowStopStopDis(calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no + 1].latitude,
                                gps_data[cur_stop_no + 1].longitude));
                    }
                    next_stop_full_distance = local_distance;
                    if (speed == 0) {
                        if (stop_duration++ >= 0xFFE0) {
                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_CROSSED;
                            return;
                        }
                        stop_deviation = (short) (((int) local_distance & 0xFF));
                    }

                    if (bus_stop_distance <= 0 || local_distance < 0) {
                        gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_CROSSED;

                        return;
                    }

                    //  lcd_display_line("CHK BUS STOP CRRSD", sizeof ("CHK BUS STOP CRRSD")-1, LCD_THIRD_LINE, LCD_HOME_COL_ADDR, FALSE);
                    break;
                case clsSwitchStates.BUS_STOP_STATE_CROSSED:
                    next_stop_full_distance = 0;
                    if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                        setShowStopStopDis(calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no + 1].latitude,
                                gps_data[cur_stop_no + 1].longitude));
                    }
                    if (speed == 0) {
                        stop_duration++;
                    } else {
                        try {
                            if (clsSharedVariables.getIpAddr1Enable()) {
                                cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                obj16833Pkts.bus_crossed_stop_pkt(gps_data[cur_stop_no].stop_name);
//                                try {
//                                    peopleCountRouteEnd((byte) 1);
//                                    peopleCountRouteEnd((byte) 5);
//                                } catch (Exception ex) {
//
//                                }
                                obj16833Pkts.people_count_packet_on_every_stop(gps_data[cur_stop_no].stop_name);

                                obj16833Pkts = null;
                            }
                        } catch (Exception ex) {
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.bus_crossed_stop_pkt(gps_data[cur_stop_no].stop_name);

                        }

                        if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                            next_stop_full_distance = calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no + 1].latitude,
                                    gps_data[cur_stop_no + 1].longitude);
                            next_stop_full_detect = true;
                        }
                        next_stop_from_current_dis = gps_data[cur_stop_no].next_dis;
                        cur_stop_no++;
                        setCurStopNo((byte) cur_stop_no);
                        setMapStopState(clsDefines.CUR_INC_AUDIO_STOP_STATE);
                        setStopState(clsDefines.NEXT_STOP_STATE);

                        if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                            setNxtLatitude(gps_data[cur_stop_no + 1].latitude);
                            setNxtLongitude(gps_data[cur_stop_no + 1].longitude);
                        }
                        gps_bus_stop_state = clsSwitchStates.BUS_STATE_NEXT_STOP;

                        return;
                    }
                    break;
                case clsSwitchStates.BUS_STATE_NEXT_STOP:
                    try {
                        local_distance = calc_gps_distance(cur_lat, cur_long, gps_data[cur_stop_no].latitude,
                                gps_data[cur_stop_no].longitude);
                    } catch (Exception ex) {

                    }
                    next_stop_full_distance = local_distance;
                    setShowStopStopDis(local_distance);

                    if (next_stop_from_current_dis == clsDefines.NEXT_MIN_DISTANE) {
                       setAppAnnounce(false);
                        gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_REACH_ANNOUNCE;
                        setMapStopState(clsDefines.NEXT_AUDIO_STOP_STATE);
                        return;
                    } else if ((next_stop_distance <= 0)) {// && (next_stop_from_current_dis > clsDefines.NEXT_MIN_DISTANE)) {
                        next_stop_dis_flag = false;
                        next_stop_distance = 0;
                        last_dist_cnt = 0;
                        route_div_distance = 0;
                        last_pnt_distance = 0;

                        //send file to display board
                        if (getIntEnable()) {
                            //  clsDisplayBrdQueue objQue = new clsDisplayBrdQueue();

                            objdisQue.addData(gps_data[cur_stop_no].next_db_file + "," + 0);

                        }
                        if (getArtIntEnable() && getArticulatedBus()) {
                            objdisQue.addData(gps_data[cur_stop_no].next_db_file + "articulated" + "," + 0);
                        }
                        setMapStopState(clsDefines.NEXT_AUDIO_STOP_STATE);
                        String[] audFiles = new String[3];
                        audFiles[0] = gps_data[cur_stop_no].next_eng_file;
                        audFiles[1] = gps_data[cur_stop_no].next_reg1_file;
                        audFiles[2] = gps_data[cur_stop_no].next_reg2_file;
                        objAudQue.removeAllMessagesQueue();
                        add_audio_files(audFiles, (byte) 3);
                        audFiles = null;
                        setAppAnnounce(false);
                        /*if (getApcStatus() != clsDefines.APC_STOP) {
                            setApcStatus(clsDefines.APC_STOP);
                            objApc.apc_end_packet();

                        }*/
                        gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_REACH_ANNOUNCE;
                        return;
                    }
                    cur_dis = gps_data[no_of_stops_in_a_route - 1].cur_dis;
                    if (cur_dis == 0) {
                        cur_dis = clsDefines.CURRENT_MIN_DISTANE;
                    }
                    if ((calc_gps_distance(cur_lat, cur_long, gps_data[no_of_stops_in_a_route - 1].latitude,
                            gps_data[no_of_stops_in_a_route - 1].longitude) <= cur_dis)
                            && (cur_stop_no > 1)) { //cur_stop_no != 0
                        // disable the stop
                        gps_data[no_of_stops_in_a_route - 1].stop_identify_status = true;

                        //send file to display board
                        if (gps_data[no_of_stops_in_a_route - 1].cur_dis > 0) {
                            if (getIntEnable()) {
                                //  clsDisplayBrdQueue objQue = new clsDisplayBrdQueue();

                                objdisQue.addData(gps_data[no_of_stops_in_a_route - 1].cur_db_file + "," + 0);

                            }
                            if (getArtIntEnable() && getArticulatedBus()) {
                                objdisQue.addData(gps_data[no_of_stops_in_a_route - 1].cur_db_file + "articulated" + "," + 0);
                            }
                            String[] audFiles = new String[3];
                            audFiles[0] = gps_data[no_of_stops_in_a_route - 1].cur_eng_file;
                            audFiles[1] = gps_data[no_of_stops_in_a_route - 1].cur_reg1_file;
                            audFiles[2] = gps_data[no_of_stops_in_a_route - 1].cur_reg2_file;

                            objAudQue.removeAllMessagesQueue();
                            add_audio_files(audFiles, (byte) 3);
                            audFiles = null;
                            setCurAnnounce(true);
                        }
                        setMapStopState(clsDefines.CUR_AUDIO_STOP_STATE);
                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.cur_stop_pkt(gps_data[no_of_stops_in_a_route - 1].stop_name);
                            obj16833Pkts = null;
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.cur_stop_pkt(gps_data[no_of_stops_in_a_route - 1].stop_name);

                        }

                        //  play_audio_file( );
                        // bus stop crossed
                        stop_duration = 0;
                        stop_deviation = 0;
                        setShowStopStopDis(0.0);

                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.cur_stop_pkt(gps_data[no_of_stops_in_a_route - 1].stop_name);
                            obj16833Pkts = null;
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.cur_stop_pkt(gps_data[no_of_stops_in_a_route - 1].stop_name);

                        }

                        gps_bus_stop_state = clsSwitchStates.BUS_STATE_LAST_STOP;

                        return;

                    }

                    // Route Deviation
                    if (((s_s_dis <= 0) && (stop_stop_dis_flag == true)) && (cur_stop_no > 0)) {
                        if (local_distance >= getRouteDevDistance()) {
                            // last_dist_cnt++;
                            //  if ((last_dist_cnt > route_deviate_time)) {
                            // last_dist_cnt = 0;
                            // route_div_distance = 0;
                            setStopState(clsDefines.ROUTE_DEVIATE_STATE);
                            if (clsSharedVariables.getIpAddr1Enable()) {
                                //cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                // obj16833Pkts.route_deviate_pkt();
                                // obj16833Pkts = null;
                            }
                            if (clsSharedVariables.getIpAddr4Enable()) {

                                objDriving.route_deviate_pkt();

                            }

                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
                            return;
                            // }
                            // last_pnt_distance = local_distance;
                        } else {
                            last_dist_cnt = 0;
                            route_div_distance = 0;
                            last_pnt_distance = local_distance;
                        }

                    } else {
                        last_dist_cnt = 0;
                        route_div_distance = 0;
                        last_pnt_distance = local_distance;
                    }

                    break;

                case clsSwitchStates.BUS_STATE_LAST_STOP:

                    if (getCurAutoTripStat() == true) {
                        next_stop_full_distance = 0;
                        // trip end
                        setCurTripStat(TRIP_END);
                        setMapStopState(clsDefines.ENDSTOP_AUDIO_STOP_STATE);
                        try {
                            if (clsSharedVariables.getIpAddr1Enable()) {
                                cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                obj16833Pkts.trip_end_pkt();
                                obj16833Pkts = null;
                            }
                        } catch (Exception ex) {
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.trip_end_pkt();

                        }
                        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                            objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_END);

                        }
//                        try {
//                            if (getApcStatus() != clsDefines.APC_STOP) {
//                                setApcStatus(clsDefines.APC_STOP);
//                                objApc.apc_end_packet();
//
//                            }
//                        } catch (Exception ex) {
//                        }
//                        SwingWorker sw1 = new SwingWorker() {
//                            @Override
//                            protected String doInBackground() throws Exception {
//                                try {
//                                    peopleCountRouteEnd((byte) 1);
//                                } catch (Exception ex) {
//
//                                }
//                                try {
//                                    StringBuilder sb = new StringBuilder();
//                                    sb.append(Calendar.getInstance().getTimeInMillis());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcStartTime());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcStopTime());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcPeopleIn());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcPeopleOut());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcStopName());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcRouteNo());
//                                    sb.append(",");
//
//                                    objReadDataFiles.write_apc_route_data(sb.toString());
//                                    try {
//                                        peopleCountRouteEnd((byte) 5);
//                                    } catch (Exception ex) {
//
//                                    }
//                                    StringBuilder sb1 = new StringBuilder();
//                                    sb1.append(Calendar.getInstance().getTimeInMillis());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcStartTime());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcStopTime());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcPeopleIn1());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcPeopleOut1());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcStopName());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcRouteNo());
//                                    sb1.append(",");
//                                    objReadDataFiles.write_apc_route_data1(sb1.toString());
//                                } catch (Exception ex) {
//                                }
//                                try {
//                                    if (clsSharedVariables.getIpAddr1Enable()) {
//                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
//                                       // System.out.println(clsSharedVariables.getApcPeopleIn() + clsSharedVariables.getApcPeopleOut() + clsSharedVariables.getApcPeopleIn1() + clsSharedVariables.getApcPeopleOut1());
//                                        obj16833Pkts.people_count_packet_on_route_end();
//                                        obj16833Pkts = null;
//                                    }
//                                } catch (Exception ex) {
//                                }
//                                return "Over";
//                            }
//                        };
//                        sw1.execute();

                        setShowStopStopDis(0.0);
                        setPrevLat(0.0);
                        setPrevLong(0.0);
                        setCurSchTripStatus(cur_trip_no, TRIP_END);
                        setCurRouteNo(getCurSchRouteNo(cur_trip_no));
                        setCurRouteStat(false);
                        setCurTripStat(TRIP_UNKNOWN);
                        if (getIntEnable()) {
                            setDisBrdDelPktCame(true);
                        }
                        cur_stop_no = 0;
                        if (getSimulationEnabled() == true) {
                            incCurTripNo();

                            // read bdc file
                            final ClsSchedule objSch = new ClsSchedule();
                            objSch.send_route_info();

                            // update_cur_schedule_file();
                            objReadFiles.write_cur_route_info_file();
                            objReadFiles.write_cur_trip_info_file();
                            objReadFiles.write_cur_schedule_file();
                            clsSharedVariables.setTripStatusUpdated(true);

                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                            return;
                        } else if (cur_trip_no < getCurSchNoTrips()) {
                            // go to next trip
                            incCurTripNo();
                            setCurStopNo((byte) 0);
                            if (cur_trip_no >= getCurSchNoTrips()) {

                                if (getSchRouteEnable() == SCHDEULE_ENABLE) {
                                    for (i = 0; i < getCurSchNoTrips(); i++) {
                                        setCurSchTripStatus(i, TRIP_END);
                                    }
                                }
                                setCurRouteStat(false);
                                setCurRouteNo("");
                                setCurTripStat(TRIP_UNKNOWN);
                                setNoStopsRoute((short) 0);

                                gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                                // update_cur_schedule_file();
                                objReadFiles.write_cur_route_info_file();
                                objReadFiles.write_cur_trip_info_file();
                                objReadFiles.write_cur_schedule_file();

                                clsSharedVariables.setTripStatusUpdated(true);
                                return;
                            }
                            // read bdc file
                            final ClsSchedule objSch = new ClsSchedule();
                            objSch.send_route_info();

                            // update_cur_schedule_file();
                            objReadFiles.write_cur_route_info_file();
                            objReadFiles.write_cur_trip_info_file();
                            objReadFiles.write_cur_schedule_file();
                            clsSharedVariables.setTripStatusUpdated(true);

                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                            return;
                        }
                        clsSharedVariables.setTripStatusUpdated(true);
                        gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
                    } else {
                        if (getCurTripStat() == TRIP_END) {
                            setMapStopState(clsDefines.ENDSTOP_AUDIO_STOP_STATE);

                            setShowStopStopDis(0.0);
                            setPrevLat(0.0);
                            setPrevLat(0.0);
                            setCurSchTripStatus(cur_trip_no, TRIP_END);

                            setCurRouteStat(false);
                            cur_stop_no = 0;
                            setCurStopNo((byte) 0);
                            setCurTripStat(TRIP_UNKNOWN);
                            setNoStopsRoute((short) 0);

                            if (getSimulationEnabled() == true) {
                                incCurTripNo();
                                setNoStopsRoute((short) 0);

                                // read bdc file
                                final ClsSchedule objSch = new ClsSchedule();
                                objSch.send_route_info();

                                // update_cur_schedule_file();
                                objReadFiles.write_cur_route_info_file();
                                objReadFiles.write_cur_trip_info_file();
                                objReadFiles.write_cur_schedule_file();
                                clsSharedVariables.setTripStatusUpdated(true);

                                gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                                return;
                            } else if (getSchRouteEnable() == SCHDEULE_ENABLE) {
                                setCurRouteNo(getCurSchRouteNo(cur_trip_no));
                                if (cur_trip_no < getCurSchNoTrips()) {
                                    // go to next trip
                                    incCurTripNo();
                                    setCurStopNo((byte) 0);
                                    if (cur_trip_no >= getCurSchNoTrips()) {
                                        if (getSchRouteEnable() == SCHDEULE_ENABLE) {
                                            for (i = 0; i < getCurSchNoTrips(); i++) {
                                                setCurSchTripStatus(i, TRIP_END);
                                            }
                                        }
                                        setCurRouteStat(false);
                                        setCurRouteNo("");
                                        setCurTripStat(TRIP_UNKNOWN);
                                        setNoStopsRoute((short) 0);

                                        gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                                        // update_cur_schedule_file();
                                        objReadFiles.write_cur_route_info_file();
                                        objReadFiles.write_cur_trip_info_file();
                                        objReadFiles.write_cur_schedule_file();

                                        clsSharedVariables.setTripStatusUpdated(true);
                                        return;
                                    }

                                    setNoStopsRoute((short) 0);
                                    // read bdc file
                                    if (getCurAutoTripStat() == true) {
                                        final ClsSchedule objSch = new ClsSchedule();
                                        objSch.send_route_info();

                                    }

                                    // update_cur_schedule_file();
                                    objReadFiles.write_cur_route_info_file();
                                    objReadFiles.write_cur_trip_info_file();
                                    objReadFiles.write_cur_schedule_file();
                                    clsSharedVariables.setTripStatusUpdated(true);

                                    gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                                    return;
                                }
                            }

                            gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            setStoptoStopDis(s_s_dis);
            setCurStopNo((byte) cur_stop_no);
            objDriving = null;
        }
    }
    //this method is used for adding the filenames to the audio queue

    public synchronized void add_audio_files(String[] file_name, byte cnt) {

        for (byte i = 0; i < cnt; i++) {
            clsSharedVariables.objAudQue.addData(file_name[i]);
        }
        AudioPlayActivity obj = new AudioPlayActivity();

        file_name = null;

    }

}
