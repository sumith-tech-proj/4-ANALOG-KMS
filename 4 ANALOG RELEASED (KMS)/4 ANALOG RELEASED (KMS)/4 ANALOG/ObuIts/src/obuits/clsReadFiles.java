package obuits;

import java.net.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import static obuits.clsBusStopDetection.no_traffic_points;
import static obuits.clsBusStopDetection.objPreDefLocPoints;
import static obuits.clsBusStopDetection.objTrafficPoints;
import static obuits.clsDefines.MAX_NO_SLOGAN_ID_FILES;
import static obuits.clsDefines.MAX_NO_TRIPS;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.TRIP_END;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsSharedVariables.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.RESET_OBU_CALLED;
import static obuits.MainFrmIts.lcd_monitoring_cnt;
import static obuits.PanDisplayBoardDiag.objPids;
import static obuits.PanDisplayBoardDiag.front_high_volt;
import static obuits.PanDisplayBoardDiag.front_high_volt_cnt;
import static obuits.PanDisplayBoardDiag.front_low_volt;
import static obuits.PanDisplayBoardDiag.front_low_volt_cnt;
import static obuits.PanDisplayBoardDiag.front_over_heat;
import static obuits.PanDisplayBoardDiag.front_over_heat_cnt;
import static obuits.PanDisplayBoardDiag.int_high_volt;
import static obuits.PanDisplayBoardDiag.int_high_volt_cnt;
import static obuits.PanDisplayBoardDiag.int_low_volt;
import static obuits.PanDisplayBoardDiag.int_low_volt_cnt;
import static obuits.PanDisplayBoardDiag.int_over_heat;
import static obuits.PanDisplayBoardDiag.int_over_heat_cnt;
import static obuits.PanDisplayBoardDiag.rear_high_volt;
import static obuits.PanDisplayBoardDiag.rear_high_volt_cnt;
import static obuits.PanDisplayBoardDiag.rear_low_volt;
import static obuits.PanDisplayBoardDiag.rear_low_volt_cnt;
import static obuits.PanDisplayBoardDiag.rear_over_heat;
import static obuits.PanDisplayBoardDiag.rear_over_heat_cnt;
import static obuits.PanDisplayBoardDiag.side_high_volt;
import static obuits.PanDisplayBoardDiag.side_high_volt_cnt;
import static obuits.PanDisplayBoardDiag.side_low_volt;
import static obuits.PanDisplayBoardDiag.side_low_volt_cnt;
import static obuits.PanDisplayBoardDiag.side_over_heat;
import static obuits.PanDisplayBoardDiag.side_over_heat_cnt;
import static obuits.clsDefines.COMM_GPRS;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.DIG_INPUT_NONE;
import static obuits.clsDefines.FRONT_DB;
import static obuits.clsDefines.HARDDISK_PATH;
import static obuits.clsDefines.INT_DB;
import static obuits.clsDefines.LANG_ENGLISH;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;
import static obuits.clsDefines.MAX_NO_ROUTES;
import static obuits.clsDefines.OBU_BRD_TYPE;
import static obuits.clsDefines.REAR_DB;
import static obuits.clsDefines.SIDE_DB;
import static obuits.clsDefines.SLEEP_SLEEP_MODE;
import static obuits.clsDefines.TIME_DISABLED;
import static obuits.clsDefines.VIDEO_RECORD_CONTINUOUS;
import static obuits.clsDefines.can_filepath;
import static obuits.clsDefines.config_filepath;
import static obuits.clsDefines.log_filepath;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.main_route_path;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.media_path;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDefines.video_filepath;
import org.apache.commons.io.FileUtils;
import java.util.TimeZone;


public class clsReadFiles {

    private final int MBSIZE = 1000000;
    private static String current_gps_stored_file = "";
    private static byte current_gps_stored_file_cnt = 0;
    private static String current_gps_stored_file2 = "";
    private static byte current_gps_stored_file_cnt2 = 0;
    private static String current_gps_stored_file3 = "";
    private static byte current_gps_stored_file_cnt3 = 0;
    private static String current_gps_stored_file4 = "";
    private static byte current_gps_stored_file_cnt4 = 0;
    private static String current_gps_stored_file5 = "";
    private static byte current_gps_stored_file_cnt5 = 0;
    private static String current_can_stored_file = "";
    private static byte current_can_stored_file_cnt = 0;
    private static String current_can_stored_file2 = "";
    private static byte current_can_stored_file_cnt2 = 0;
    private static String current_can_stored_file5 = "";
    private static byte current_can_stored_file_cnt5 = 0;
    public static final byte MAX_NO_GPS_STORED_FILES = 100;
    public static final int MAX_NO_CAN_STORED_FILES = 100;
    public static String[] label_names = new String[4000];
    public static int label_names_cnt = 0;
    DecimalFormat decimalFormat = new DecimalFormat("00000000.0000");

    public void init_schedule_files() {
        //permanent schedule init
        try {
            int i;
            setPermSchAvailable(false);
            clsSharedVariables.perm_sch.trip_status = new byte[MAX_NO_TRIPS];
            clsSharedVariables.perm_sch.route_nos = new String[MAX_NO_TRIPS];
            clsSharedVariables.perm_sch.sch_endtime = 0;
            clsSharedVariables.perm_sch.no_trips = 0;
            clsSharedVariables.perm_sch.sch_startdatetime = null;
            clsSharedVariables.perm_sch.sch_enddatetime = null;
            setPermSchStartTime(0);
            clsSharedVariables.perm_sch.sch_id = "0";
            for (i = 0; i < MAX_NO_TRIPS; i++) {
                clsSharedVariables.perm_sch.trip_status[i] = clsDefines.TRIP_UNKNOWN;
                clsSharedVariables.perm_sch.route_nos[i] = "";
            }
            setTempSchAvailable(false);
            clsSharedVariables.temp_sch.trip_status = new byte[MAX_NO_TRIPS];
            clsSharedVariables.temp_sch.route_nos = new String[MAX_NO_TRIPS];
            clsSharedVariables.temp_sch.sch_endtime = 0;
            clsSharedVariables.temp_sch.no_trips = 0;
            clsSharedVariables.temp_sch.sch_startdatetime = null;
            clsSharedVariables.temp_sch.sch_enddatetime = null;
            clsSharedVariables.temp_sch.sch_starttime = 0;
            clsSharedVariables.temp_sch.sch_id = "0";
            for (i = 0; i < MAX_NO_TRIPS; i++) {
                clsSharedVariables.temp_sch.trip_status[i] = clsDefines.TRIP_UNKNOWN;
                clsSharedVariables.temp_sch.route_nos[i] = "";
            }
            setCurSchAvailable(false);
            clsSharedVariables.cur_sch_route.trip_status = new byte[MAX_NO_ROUTES];
            clsSharedVariables.cur_sch_route.route_nos = new String[MAX_NO_ROUTES];
            clsSharedVariables.cur_sch_route.src_name = new String[MAX_NO_ROUTES];
            clsSharedVariables.cur_sch_route.des_name = new String[MAX_NO_ROUTES];
            setCurSchEndTime(0);
            setCurSchNoTrips(0);
            setCurSchStartDate(null);
            setCurSchEndDate(null);
            setCurSchStartTime(0);
            setSchId("0");
            for (i = 0; i < MAX_NO_ROUTES; i++) {
                setCurSchTripStatus(i, clsDefines.TRIP_UNKNOWN);
                setCurSchRouteNo(i, "");
            }
            setCurTripNo((byte) 0);
            setCurTripStat(clsDefines.TRIP_UNKNOWN);
            setCurRouteStat(false);
            setCurRouteNo("");
        } catch (Exception ex) {

        }
    }

    public synchronized void read_permanent_schedule_file() {
        short i;
        short j;
        String[] split_str;
        String line;
        BufferedReader br = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        File file = new File(route_filepath, "perm_sch.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));
                i = 0;
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    clsSharedVariables.perm_sch.sch_id = (split_str[i++]);
                    clsSharedVariables.perm_sch.sch_startdatetime = sdf.parse(split_str[i++]);
                    clsSharedVariables.perm_sch.sch_enddatetime = sdf.parse(split_str[i++]);
                    clsSharedVariables.perm_sch.no_trips = Short.parseShort(split_str[i++]);
                    for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                        clsSharedVariables.perm_sch.route_nos[j] = split_str[i++];
                    }
                    for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                        clsSharedVariables.perm_sch.trip_status[j] = Byte.parseByte(split_str[i++]);
                    }

                    setPermSchAvailable(Boolean.parseBoolean(split_str[i++]));

                    setPermSchStartTime(clsSharedVariables.perm_sch.sch_startdatetime.getTime());
                    clsSharedVariables.perm_sch.sch_endtime = clsSharedVariables.perm_sch.sch_enddatetime.getTime();
                    setPermSchStatus(true);
                    break;
                }

            } catch (IOException e) {
            } catch (ParseException e) {

            } catch (Exception ex) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                        br = null;
                        file = null;

                        line = null;
                    }
                } catch (IOException e) {

                }
            }

        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
            File f = new File(main_route_filepath, "perm_sch.txt");

            if (f.exists()) {
                try {
                    br = new BufferedReader(new FileReader(f));

                    i = 0;
                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",");
                        clsSharedVariables.perm_sch.sch_id = (split_str[i++]);
                        clsSharedVariables.perm_sch.sch_startdatetime = sdf.parse(split_str[i++]);

                        clsSharedVariables.perm_sch.sch_enddatetime = sdf.parse(split_str[i++]);

                        clsSharedVariables.perm_sch.no_trips = Short.parseShort(split_str[i++]);
                        for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                            clsSharedVariables.perm_sch.route_nos[j] = split_str[i++];
                        }
                        for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                            clsSharedVariables.perm_sch.trip_status[j] = Byte.parseByte(split_str[i++]);
                        }

                        setPermSchAvailable(Boolean.parseBoolean(split_str[i++]));

                        setPermSchStartTime(clsSharedVariables.perm_sch.sch_startdatetime.getTime());
                        clsSharedVariables.perm_sch.sch_endtime = clsSharedVariables.perm_sch.sch_enddatetime.getTime();
                        setPermSchStatus(true);
                        break;
                    }

                } catch (IOException e) {
                } catch (ParseException e) {

                } catch (Exception ex) {

                } finally {
                    try {
                        if (br != null) {
                            br.close();
                            br = null;
                            file = null;
                            f = null;
                            line = null;
                        }
                    } catch (IOException e) {

                    }
                }

            } else {
                setPermSchAvailable(false);
            }
        } else {
            setPermSchAvailable(false);
        }
        split_str = null;
        line = null;
        sdf = null;
    }

    public synchronized boolean write_data_to_perm_sch_file(String[] split_str, short start_index) {
        short i;
        short j;
        short k;

        byte route_inc = 0;
        i = start_index;
        Date start_date = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        boolean route_found = false;
        try {
            clsSharedVariables.perm_sch.sch_id = (split_str[i++].trim());
            try {
                start_date = sdf.parse(split_str[i + 1] + " " + split_str[i]);
            } catch (ParseException e) {

                return false;
            }
            i += 2;
            clsSharedVariables.perm_sch.no_trips = Integer.parseInt(split_str[i++].trim());
            route_inc = 0;
            for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                route_found = false;
                for (k = 0; k < getNoRoutes(); k++) {
                    if (objRouteMasFiles[k].route_no.equals(split_str[i])) {
                        route_found = true;
                        clsSharedVariables.perm_sch.trip_status[route_inc] = TRIP_UNKNOWN;
                        clsSharedVariables.perm_sch.route_nos[route_inc++] = split_str[i];

                    }
                }

                i++;

            }
            clsSharedVariables.perm_sch.no_trips = route_inc;
            c.setTime(start_date);
            c.add(Calendar.DATE, 1);
            clsSharedVariables.perm_sch.sch_enddatetime = c.getTime();
            clsSharedVariables.perm_sch.sch_endtime = clsSharedVariables.perm_sch.sch_enddatetime.getTime();
            clsSharedVariables.perm_sch.sch_startdatetime = start_date;
            setPermSchStartTime(start_date.getTime());
            setPermSchAvailable(true);
            if (getSimulationEnabled() == true) {
                setCurSec(getPermSchStartTime() + 10);
            }
            write_perm_schedule_file();
            return true;
        } catch (Exception ex) {

            return false;
        } finally {
            c = null;
            start_date = null;
            sdf = null;
        }

    }

    public synchronized boolean write_data_to_temp_sch_file(String[] split_str, short start_index) {
        short i;
        short j;
        i = start_index;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        try {
            clsSharedVariables.temp_sch.sch_id = (split_str[i++]);
            try {
                clsSharedVariables.temp_sch.sch_startdatetime = sdf.parse(split_str[i + 1] + " " + split_str[i]);
            } catch (ParseException e) {
                return false;
            }
            i += 2;
            try {
                clsSharedVariables.temp_sch.sch_enddatetime = sdf.parse(split_str[i + 1] + " " + split_str[i]);
            } catch (ParseException e) {
                return false;
            }
            i += 2;
            clsSharedVariables.temp_sch.no_trips = Integer.parseInt(split_str[i++]);
            for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                clsSharedVariables.temp_sch.trip_status[j] = TRIP_UNKNOWN;
                clsSharedVariables.temp_sch.route_nos[j] = split_str[i++];
            }
            clsSharedVariables.temp_sch.sch_starttime = clsSharedVariables.temp_sch.sch_startdatetime.getTime();
            clsSharedVariables.temp_sch.sch_endtime = clsSharedVariables.temp_sch.sch_enddatetime.getTime();
            setTempSchAvailable(true);
            write_temp_schedule_file();
            return true;
        } catch (Exception ex) {

            return false;
        } finally {
            sdf = null;
        }
    }

    public synchronized void write_perm_schedule_file() {
        short j = 0;
        String sch_route_no = "";
        String status = "";
        String data = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        File file = new File(route_filepath, "perm_sch.txt");
        //
        FileOutputStream f;
        PrintWriter pw;
        try {
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                if (clsSharedVariables.perm_sch.no_trips > 0) {
                    for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                        sch_route_no = sch_route_no + "," + clsSharedVariables.perm_sch.route_nos[j];
                    }
                    sch_route_no = sch_route_no.replaceFirst(",", "");
                    for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                        status = status + "," + clsSharedVariables.perm_sch.trip_status[j];
                    }
                    status = status.replaceFirst(",", "");

                    data = clsSharedVariables.perm_sch.sch_id + ","
                            + sdf.format(clsSharedVariables.perm_sch.sch_startdatetime) + ","
                            + sdf.format(clsSharedVariables.perm_sch.sch_enddatetime) + ","
                            + clsSharedVariables.perm_sch.no_trips + ","
                            + sch_route_no + "," + status + ","
                            + getPermSchAvailable();
                }
                pw.println(data);
                pw.flush();
                pw.close();
                f.close();
            }

            if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                try {
                    File file1 = new File(main_route_filepath, "perm_sch.txt");
                    f = new FileOutputStream(file1, false);
                    pw = new PrintWriter(f);

                    if (clsSharedVariables.perm_sch.no_trips > 0) {
                        for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                            sch_route_no = sch_route_no + "," + clsSharedVariables.perm_sch.route_nos[j];
                        }
                        sch_route_no = sch_route_no.replaceFirst(",", "");
                        for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                            status = status + "," + clsSharedVariables.perm_sch.trip_status[j];
                        }
                        status = status.replaceFirst(",", "");

                        data = clsSharedVariables.perm_sch.sch_id + ","
                                + sdf.format(clsSharedVariables.perm_sch.sch_startdatetime) + ","
                                + sdf.format(clsSharedVariables.perm_sch.sch_enddatetime) + ","
                                + clsSharedVariables.perm_sch.no_trips + ","
                                + sch_route_no + "," + status + ","
                                + getPermSchAvailable();
                    }
                    pw.println(data);
                    pw.flush();
                    pw.close();
                    f.close();
                } catch (Exception e) {

                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            sch_route_no = null;
            status = null;
            data = null;
            sdf = null;
            pw = null;
            file = null;
            f = null;
        }
    }

    public synchronized void read_temp_schedule_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        //Get the text file
        File file = new File(route_filepath, "temp_sch.txt");
        File f = new File(main_route_filepath, "temp_sch.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    clsSharedVariables.temp_sch.sch_id = (split_str[i++]);
                    clsSharedVariables.temp_sch.sch_startdatetime = sdf.parse(split_str[i++]);

                    clsSharedVariables.temp_sch.sch_enddatetime = sdf.parse(split_str[i++]);

                    clsSharedVariables.temp_sch.no_trips = Short.parseShort(split_str[i++]);
                    for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                        clsSharedVariables.temp_sch.route_nos[j] = split_str[i++];
                    }
                    for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                        clsSharedVariables.temp_sch.trip_status[j] = Byte.parseByte(split_str[i++]);
                    }
                    setTempSchAvailable(Boolean.parseBoolean(split_str[i++]));

                    clsSharedVariables.temp_sch.sch_starttime = clsSharedVariables.temp_sch.sch_startdatetime.getTime();
                    clsSharedVariables.temp_sch.sch_endtime = clsSharedVariables.temp_sch.sch_enddatetime.getTime();

                    break;
                }
            } catch (IOException e) {
            } catch (ParseException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;
                sdf = null;
                file = null;
            }
            setTempSchAvailable(false);
        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
            try {
                if (f.exists()) {

                    br = new BufferedReader(new FileReader(f));

                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",");
                        clsSharedVariables.temp_sch.sch_id = (split_str[i++]);
                        clsSharedVariables.temp_sch.sch_startdatetime = sdf.parse(split_str[i++]);

                        clsSharedVariables.temp_sch.sch_enddatetime = sdf.parse(split_str[i++]);

                        clsSharedVariables.temp_sch.no_trips = Short.parseShort(split_str[i++]);
                        for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                            clsSharedVariables.temp_sch.route_nos[j] = split_str[i++];
                        }
                        for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                            clsSharedVariables.temp_sch.trip_status[j] = Byte.parseByte(split_str[i++]);
                        }
                        setTempSchAvailable(Boolean.parseBoolean(split_str[i++]));

                        clsSharedVariables.temp_sch.sch_starttime = clsSharedVariables.temp_sch.sch_startdatetime.getTime();
                        clsSharedVariables.temp_sch.sch_endtime = clsSharedVariables.temp_sch.sch_enddatetime.getTime();

                        break;
                    }
                }
            } catch (IOException e) {
            } catch (ParseException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;
                sdf = null;
                file = null;
                f = null;
            }
            setTempSchAvailable(false);
        } else {
            setTempSchAvailable(false);
        }
    }

    public synchronized void write_temp_schedule_file() {

        short j = 0;
        String sch_route_no = "";
        String status = "";
        String data;
        FileOutputStream f;
        PrintWriter pw;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        File file = new File(route_filepath, "temp_sch.txt");
        File file1 = new File(main_route_filepath, "temp_sch.txt");

        try {
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                if (clsSharedVariables.temp_sch.no_trips > 0) {
                    for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                        sch_route_no = sch_route_no + "," + clsSharedVariables.temp_sch.route_nos[j];
                    }
                    sch_route_no = sch_route_no.replaceFirst(",", "");
                    for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                        status = status + "," + clsSharedVariables.temp_sch.trip_status[j];
                    }
                    status = status.replaceFirst(",", "");
                }
                data = clsSharedVariables.temp_sch.sch_id + ","
                        + sdf.format(clsSharedVariables.temp_sch.sch_startdatetime) + ","
                        + sdf.format(clsSharedVariables.temp_sch.sch_enddatetime) + ","
                        + clsSharedVariables.temp_sch.no_trips + ","
                        + sch_route_no + "," + status + ","
                        + getTempSchAvailable();

                pw.println(data);
                pw.flush();
                pw.close();
                f.close();
                pw = null;
                f = null;
            }
            if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                try {
                    f = new FileOutputStream(file1, false);
                    pw = new PrintWriter(f);

                    if (clsSharedVariables.temp_sch.no_trips > 0) {
                        for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                            sch_route_no = sch_route_no + "," + clsSharedVariables.temp_sch.route_nos[j];
                        }
                        sch_route_no = sch_route_no.replaceFirst(",", "");
                        for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
                            status = status + "," + clsSharedVariables.temp_sch.trip_status[j];
                        }
                        status = status.replaceFirst(",", "");
                    }

                    data = clsSharedVariables.temp_sch.sch_id + ","
                            + sdf.format(clsSharedVariables.temp_sch.sch_startdatetime) + ","
                            + sdf.format(clsSharedVariables.temp_sch.sch_enddatetime) + ","
                            + clsSharedVariables.temp_sch.no_trips + ","
                            + sch_route_no + "," + status + ","
                            + getTempSchAvailable();

                    pw.println(data);

                    pw.flush();
                    pw.close();
                    f.close();
                    pw = null;
                    f = null;
                } catch (Exception e) {

                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } finally {
            sch_route_no = null;
            status = null;
            data = null;
            sdf = null;
            file1 = null;
        }
    }

    public synchronized void read_cur_schedule_file() {
        short i = 0;
        short j = 0;
        short cur_trip_no = getCurTripNo();
        short no_of_routes = getNoRoutes();
        String[] split_str;
        String line;
        BufferedReader br = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        if (getSchRouteEnable() == ROUTE_ENABLE) {
            try {
                setCurSchAvailable(true);
                setCurSchNoTrips(no_of_routes);
                for (j = 0; j < no_of_routes; j++) {
                    setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                }
                for (j = 0; j < getCurSchNoTrips(); j++) {
                    setCurSchTripStatus(j, TRIP_UNKNOWN);
                }
                setSchId("1");
                setCurSchStartDate(cal.getTime());
                cal.add(Calendar.DATE, 1);
                setCurSchEndDate(cal.getTime());
                setCurSchStartTime(getCurSchStartDate().getTime());
                setCurSchEndTime(getCurSchEndDate().getTime());
                if (cur_trip_no >= no_of_routes) {
                    setCurTripNo((byte) 0);
                }
                for (j = 0; j < cur_trip_no; j++) {
                    setCurSchTripStatus(j, TRIP_END);

                }
                if (j == cur_trip_no) {
                    setCurSchTripStatus(j, getCurTripStat());
                }
            } catch (Exception e) {
            } finally {
                sdf = null;
                cal = null;
            }
            return;

        } else {
            File file = new File(route_filepath, "current_sch.txt");
            File f = new File(main_route_filepath, "current_sch.txt");

            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                try {
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",", -1);
                        setSchId((split_str[i++]));
                        setCurSchStartDate(sdf.parse(split_str[i++]));
                        setCurSchEndDate(sdf.parse(split_str[i++]));
                        setCurSchNoTrips(Short.parseShort(split_str[i++]));
                        for (j = 0; j < getCurSchNoTrips(); j++) {
                            setCurSchRouteNo(j, split_str[i++]);
                        }
                        for (j = 0; j < getCurSchNoTrips(); j++) {
                            setCurSchTripStatus(j, Byte.parseByte(split_str[i++]));
                        }
                        setCurSchAvailable(Boolean.parseBoolean(split_str[i++]));
                        setCurSchStartTime(getCurSchStartDate().getTime());
                        setCurSchEndTime(getCurSchEndDate().getTime());
                        return;
                    }
                } catch (IOException e) {
                } catch (ParseException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        if (br != null) {
                            br.close();
                            br = null;
                        }
                    } catch (IOException e) {

                    }
                    sdf = null;
                    cal = null;
                    file = null;
                }
            } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                if (f.exists()) {
                    try {
                        br = new BufferedReader(new FileReader(f));

                        while ((line = br.readLine()) != null) {
                            split_str = line.split(",", -1);
                            setSchId((split_str[i++]));
                            setCurSchStartDate(sdf.parse(split_str[i++]));
                            setCurSchEndDate(sdf.parse(split_str[i++]));
                            setCurSchNoTrips(Short.parseShort(split_str[i++]));
                            for (j = 0; j < getCurSchNoTrips(); j++) {
                                setCurSchRouteNo(j, split_str[i++]);
                            }
                            for (j = 0; j < getCurSchNoTrips(); j++) {
                                setCurSchTripStatus(j, Byte.parseByte(split_str[i++]));
                            }
                            setCurSchAvailable(Boolean.parseBoolean(split_str[i++]));
                            setCurSchStartTime(getCurSchStartDate().getTime());
                            setCurSchEndTime(getCurSchEndDate().getTime());
                            return;
                        }

                    } catch (IOException e) {
                    } catch (ParseException e) {

                    } catch (Exception e) {

                    } finally {
                        try {
                            if (br != null) {
                                br.close();
                                br = null;
                            }
                        } catch (IOException e) {

                        }
                        sdf = null;
                        cal = null;
                        file = null;
                        f = null;
                    }

                } else {
                    setCurSchAvailable(false);
                    try {
                        if (getSchRouteEnable() == ROUTE_ENABLE) {
                            setCurSchAvailable(true);
                            setCurSchNoTrips(no_of_routes);
                            for (j = 0; j < no_of_routes; j++) {
                                setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                            }
                            for (j = 0; j < getCurSchNoTrips(); j++) {
                                setCurSchTripStatus(j, TRIP_UNKNOWN);
                            }

                            setSchId("1");
                            setCurSchStartDate(cal.getTime());

                            cal.add(Calendar.DATE, 1);
                            setCurSchEndDate(cal.getTime());

                            setCurSchStartTime(getCurSchStartDate().getTime());
                            setCurSchEndTime(getCurSchEndDate().getTime());
                        }
                    } catch (Exception e) {

                    } finally {
                        try {
                            if (br != null) {
                                br.close();
                                br = null;
                            }
                        } catch (IOException e) {

                        }
                        sdf = null;
                        cal = null;
                        file = null;
                    }
                }
            } else {
                setCurSchAvailable(false);
            }
            return;
        }
    }

    public synchronized void write_cur_schedule_file() {

        short j = 0;
        String sch_route_no = "";
        String status = "";
        String data;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        File file = new File(route_filepath, "current_sch.txt");
        File file1 = new File(main_route_filepath, "current_sch.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                if (getCurSchNoTrips() > 0) {

                    for (j = 0; j < getCurSchNoTrips(); j++) {
                        sch_route_no = sch_route_no + "," + getCurSchRouteNo(j);
                    }
                    sch_route_no = sch_route_no.replaceFirst(",", "");
                    for (j = 0; j < getCurSchNoTrips(); j++) {
                        status = status + "," + getCurSchTripStatus(j);
                    }
                    status = status.replaceFirst(",", "");
                }
                data = clsSharedVariables.getSchId() + ","
                        + sdf.format(getCurSchStartDate()) + ","
                        + sdf.format(getCurSchEndDate()) + ","
                        + getCurSchNoTrips() + ","
                        + sch_route_no + "," + status + ","
                        + getCurSchAvailable();

                pw.println(data);

                pw.flush();
                pw.close();
                f.close();
            }
            if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                try {
                    f = new FileOutputStream(file1, false);
                    pw = new PrintWriter(f);

                    if (getCurSchNoTrips() > 0) {

                        for (j = 0; j < getCurSchNoTrips(); j++) {
                            sch_route_no = sch_route_no + "," + getCurSchRouteNo(j);
                        }
                        sch_route_no = sch_route_no.replaceFirst(",", "");
                        for (j = 0; j < getCurSchNoTrips(); j++) {
                            status = status + "," + getCurSchTripStatus(j);
                        }
                        status = status.replaceFirst(",", "");
                    }
                    data = clsSharedVariables.getSchId() + ","
                            + sdf.format(getCurSchStartDate()) + ","
                            + sdf.format(getCurSchEndDate()) + ","
                            + getCurSchNoTrips() + ","
                            + sch_route_no + "," + status + ","
                            + getCurSchAvailable();

                    pw.println(data);

                    pw.flush();
                    pw.close();
                    f.close();
                } catch (Exception e) {

                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception e) {

        } finally {
            sch_route_no = null;
            status = null;
            data = null;
            sdf = null;
            pw = null;
            f = null;
            file = null;
            file1 = null;
        }
    }

    public synchronized void read_current_trip_info() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(route_filepath, "currentTripInfo.txt");
        File f = new File(main_route_filepath, "currentTripInfo.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    setCurTripNo(Byte.parseByte(split_str[0]));
                    setCurTripStat(Byte.parseByte(split_str[1]));
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;
                file = null;
            }
        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {

            try {
                br = new BufferedReader(new FileReader(f));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    setCurTripNo(Byte.parseByte(split_str[0]));
                    setCurTripStat(Byte.parseByte(split_str[1]));
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;
                file = null;
                f = null;
            }
        }
    }

    public synchronized void write_cur_trip_info_file() {

        String data;
        File file = new File(route_filepath, "currentTripInfo.txt");
        File file1 = new File(main_route_filepath, "currentTripInfo.txt");
        FileOutputStream f;
        PrintWriter pw;
        try {
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                data = getCurTripNo() + "," + getCurTripStat();

                pw.println(data);

                pw.flush();
                pw.close();
                f.close();
            }
            if (clsDefines.DUPLICATE_FILES_MAIN_PATH && clsSharedVariables.getStorageType() == clsDefines.STORAGE_HARDDISK) {
                try {
                    f = new FileOutputStream(file1, false);
                    pw = new PrintWriter(f);
                    data = getCurTripNo() + "," + getCurTripStat();
                    pw.println(data);
                    pw.flush();
                    pw.close();
                    f.close();
                } catch (Exception e) {

                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            data = null;
            f = null;
            pw = null;
            file = null;
            file1 = null;
        }
    }

    public void write_odometer_distance_file(double distance, double lat, double longi, long sec) {
        FileOutputStream f;
        PrintWriter pw;
        RandomAccessFile raFile;
        String path;
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                path = media_filepath.getAbsolutePath();
            } else {
                path = main_route_path.getAbsolutePath();
            }
            raFile = new RandomAccessFile(path + "/odometerDis.txt", "rw");
            f = new FileOutputStream(raFile.getFD());
            raFile.seek(0);
            pw = new PrintWriter(f);
            pw.println(decimalFormat.format(distance) + "," + String.valueOf(lat) + "," + String.valueOf(longi) + "," + String.valueOf(sec));
            pw.flush();
            pw.close();
            f.close();
            raFile.close();
        } catch (FileNotFoundException e) {
            write_log_low_memory_data("odometerDis.txt " + e.getMessage());
        } catch (IOException e) {
            write_log_low_memory_data("odometerDis.txt " + e.getMessage());
        } catch (Exception e) {
            write_log_low_memory_data("odometerDis.txt " + e.getMessage());
        } finally {
            f = null;
            pw = null;
        }
        raFile = null;
        runCmd("sudo sync");
    }

    public synchronized void write_odometer_distance_alt_file(double distance, double lat, double longi, long sec) {
        FileOutputStream f;
        PrintWriter pw;
        RandomAccessFile raFile;
        String path;
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                path = media_filepath.getAbsolutePath();
            } else {
                path = main_route_path.getAbsolutePath();
            }
            raFile = new RandomAccessFile(path + "/odometerDis2.txt", "rw");
            f = new FileOutputStream(raFile.getFD());
            raFile.seek(0);
            pw = new PrintWriter(f);
            pw.println(decimalFormat.format(distance) + "," + String.valueOf(lat) + "," + String.valueOf(longi) + "," + String.valueOf(sec));
            pw.flush();
            pw.close();
            f.close();
            raFile.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        } finally {
            f = null;
            pw = null;
        }
        raFile = null;
        runCmd("sudo sync");
    }

    public synchronized void read_odometer_distance_file(long sec) {
        String line = "";
        BufferedReader br = null;
        String[] split_str;
        double trav_dis = 0.0;
        String path;
        if (clsSharedVariables.getHardDriveDetected()) {
            path = media_filepath.getAbsolutePath();
        } else {
            path = main_route_path.getAbsolutePath();
        }
        //Get the text file
        File file = new File(path, "odometerDis.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    trav_dis = Double.parseDouble(split_str[0]);
                    if (trav_dis > 99999999.0) {
                        trav_dis = 0.0;
                    }
                    setTravelledDis(trav_dis);
                    break;
                }
            } catch (FileNotFoundException e) {
                write_log_low_memory_data("odometerDis.txt exc " + e.getMessage());
            } catch (IOException e) {
                write_log_low_memory_data("odometerDis.txt File  exc " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } catch (Exception e) {
                write_log_low_memory_data("odometerDis.txt File exc " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } finally {
                try {
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                } catch (IOException e) {
                }
            }
            if (trav_dis == 0.0 || file.getFreeSpace() == 0) {
                read_odometer_distance_alt_file(sec);
            }
        } else {
            read_odometer_distance_alt_file(sec);
        }
        file = null;
        line = null;
    }

    public synchronized void read_odometer_distance_alt_file(long sec) {
        String line = "";
        BufferedReader br = null;
        String[] split_str;
        double trav_dis = 0.0;
        String path;

        if (clsSharedVariables.getHardDriveDetected()) {
            path = media_filepath.getAbsolutePath();
        } else {
            path = main_route_path.getAbsolutePath();
        }
        //Get the text file
        File file = new File(path, "odometerDis2.txt");
        if (file.exists()) {

            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    trav_dis = Double.parseDouble(split_str[0]);
                    if (trav_dis > 99999999.0) {
                        trav_dis = 0.0;
                    }
                    setTravelledDis(trav_dis);
                    write_log_gprs_connectivity("Trvelled Distnce Alt File " + trav_dis);
                    break;
                }

            } catch (FileNotFoundException e) {
                write_log_gprs_connectivity("odometerDis2.txt file not found exc  " + getTravelledDis() + " " + getOdometerPrevLat() + getOdometerPrevLong() + e.getMessage());

            } catch (IOException e) {
                write_log_gprs_connectivity("odometerDis2 " + getTravelledDis() + " " + getOdometerPrevLat() + getOdometerPrevLong() + e.getMessage());

            } catch (Exception e) {
                write_log_gprs_connectivity("odometerDis2 " + getTravelledDis() + " " + getOdometerPrevLat() + getOdometerPrevLong() + e.getMessage());

            } finally {
                try {
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                } catch (IOException e) {
                }
            }

        }
        file = null;
        line = null;
    }

    public void write_gps_data_to_file(String str) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        File file = new File(route_filepath, "simulGprmc.txt");
        FileOutputStream f;
        PrintWriter pw;
        try {
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception e) {

        } finally {
            file = null;
            f = null;
            pw = null;
        }
    }

    public void write_gps_sample_collection_file() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        File file = new File(route_filepath, "SampleRoute.txt");
        FileOutputStream f;
        PrintWriter pw;
        String str = String.valueOf(Calendar.getInstance().getTime() + "   :" + clsSharedVariables.getCurLatitude()) + "," + String.valueOf(clsSharedVariables.getCurLongitude());
        try {
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        } finally {
            file = null;
            f = null;
            pw = null;
        }
    }

    public boolean read_gps_data_from_file() {
        int i = 0;
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        String line;
        BufferedReader br = null;
        File file = new File(route_filepath, "simulGprmc.txt");
        setSimulationcnt(0);
        i = 0;
        if (file.exists()) {
            try {
                clsSharedVariables.gps_simul_data = new String[12000];
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        gps_simul_data[i++] = line;
                    }
                }
                setSimulationcnt(i);
                return true;
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception ex) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;

                    }
                    setSimulationcnt(i);
                } catch (IOException e) {

                }
            }
        } else {
            return false;
        }
        return false;
    }

    public void read_can_data_from_file() {
        short i = 0;
        String line;
        BufferedReader br = null;
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        File file = new File(media_filepath, "candata.txt");
        setCanSimulationcnt(0);
        i = 0;
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        can_simul_data[i++] = line;
                    }
                }
                setCanSimulationcnt(i);

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception ex) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;

                    }
                    setCanSimulationcnt(i);
                } catch (IOException e) {

                }
            }
        }
    }

    public void write_gps_stored_data_to_file4(String str) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        if (current_gps_stored_file4.equals("")) {
            current_gps_stored_file4 = "gpsStoredData1.txt";
            current_gps_stored_file_cnt4 = 1;
            write_storedData_Params_file4(current_gps_stored_file4, current_gps_stored_file_cnt4);
        }

        File file;
        File destFile;
        PrintWriter pw = null;
        FileOutputStream f = null;
        byte i = 0;
        byte j = 0;

        file = new File(media_filepath.getAbsolutePath() + "/storeddata4");
        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true, false);
            file.setReadable(true, false);
        }

        file = new File(media_filepath.getAbsolutePath() + "/storeddata4", current_gps_stored_file4);
        try {
            if (file.length() > 10 * MBSIZE) {
                //check for unused file
                i = current_gps_stored_file_cnt4;
                i++;
                if (i < MAX_NO_GPS_STORED_FILES) {
                    current_gps_stored_file4 = "gpsStoredData" + (i) + ".txt";
                    current_gps_stored_file_cnt4 = i;
                    write_storedData_Params_file4(current_gps_stored_file4, current_gps_stored_file_cnt4);
                } else {
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata4", "gpsStoredData1.txt");
                    if (file.exists()) {
                        file.delete();
                    }
                    j = 1;
                    for (i = 2; i < MAX_NO_GPS_STORED_FILES; i++) {
                        file = new File(media_filepath.getAbsolutePath() + "/storeddata4", "gpsStoredData" + i + ".txt");
                        if (file.exists()) {
                            destFile = new File(media_filepath.getAbsolutePath() + "/storeddata4", "gpsStoredData" + (j++) + ".txt");
                            file.renameTo(destFile);
                        }
                    }
                    current_gps_stored_file4 = "gpsStoredData" + (j) + ".txt";
                    current_gps_stored_file_cnt4 = j;
                    write_storedData_Params_file4(current_gps_stored_file4, current_gps_stored_file_cnt4);
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata4", current_gps_stored_file4);
                }
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();

        } catch (Exception e) {

        } finally {

            try {
                if (pw != null) {
                    pw.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {

            }
            str = null;
            file = null;
            destFile = null;
            pw = null;
            f = null;
        }
    }

    public void write_gps_stored_data_to_file5(String str) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        if (current_gps_stored_file5.equals("")) {
            current_gps_stored_file5 = "gpsStoredData1.txt";
            current_gps_stored_file_cnt5 = 1;
            write_storedData_Params_file5(current_gps_stored_file5, current_gps_stored_file_cnt5);
        }

        File file;
        File destFile;
        PrintWriter pw = null;
        FileOutputStream f = null;
        byte i = 0;
        byte j = 0;

        file = new File(media_filepath.getAbsolutePath() + "/storeddata5");
        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true, false);
            file.setReadable(true, false);
        }

        file = new File(media_filepath.getAbsolutePath() + "/storeddata5", current_gps_stored_file5);
        try {
            if (file.length() > 10 * MBSIZE) {
                i = current_gps_stored_file_cnt5;
                i++;
                if (i < MAX_NO_GPS_STORED_FILES) {
                    current_gps_stored_file5 = "gpsStoredData" + (i) + ".txt";
                    current_gps_stored_file_cnt5 = i;
                    write_storedData_Params_file5(current_gps_stored_file5, current_gps_stored_file_cnt5);
                } else {
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata5", "gpsStoredData1.txt");
                    if (file.exists()) {
                        file.delete();
                    }
                    j = 1;
                    for (i = 2; i < MAX_NO_GPS_STORED_FILES; i++) {
                        file = new File(media_filepath.getAbsolutePath() + "/storeddata5", "gpsStoredData" + i + ".txt");
                        if (file.exists()) {
                            destFile = new File(media_filepath.getAbsolutePath() + "/storeddata5", "gpsStoredData" + (j++) + ".txt");
                            file.renameTo(destFile);
                        }
                    }

                    current_gps_stored_file5 = "gpsStoredData" + (j) + ".txt";
                    current_gps_stored_file_cnt5 = j;
                    write_storedData_Params_file5(current_gps_stored_file5, current_gps_stored_file_cnt5);
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata5", current_gps_stored_file5);
                }
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();

        } catch (Exception e) {

        } finally {

            try {
                if (pw != null) {
                    pw.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {

            }
            str = null;
            file = null;
            destFile = null;
            pw = null;
            f = null;
        }

    }

    public void write_can_stored_data_to_file5(String str) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        if (str == null) {
            return;
        }
        if (current_can_stored_file5.equals("")) {
            current_can_stored_file5 = "canStoredData1.txt";
            current_can_stored_file_cnt5 = 1;
            write_can_storedData_Params_file5(current_can_stored_file5, current_can_stored_file_cnt5);
        }

        File file;
        File destFile;
        PrintWriter pw = null;
        FileOutputStream f = null;
        byte i = 0;
        byte j = 0;

        file = new File(media_filepath.getAbsolutePath() + "/canstoreddata5");
        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true, false);
            file.setReadable(true, false);
        }

        file = new File(media_filepath.getAbsolutePath() + "/canstoreddata5", current_can_stored_file5);
        try {
            if (file.length() > 10 * MBSIZE) {
                //check for unused file
                i = current_can_stored_file_cnt5;
                i++;
                if (i < MAX_NO_CAN_STORED_FILES) {
                    current_can_stored_file5 = "canStoredData" + (i) + ".txt";
                    current_can_stored_file_cnt5 = i;
                    write_can_storedData_Params_file5(current_can_stored_file5, current_can_stored_file_cnt5);
                } else {
                    file = new File(media_filepath.getAbsolutePath() + "/canstoreddata5", "canStoredData1.txt");
                    if (file.exists()) {
                        file.delete();
                    }
                    j = 1;
                    for (i = 2; i < MAX_NO_CAN_STORED_FILES; i++) {
                        file = new File(media_filepath.getAbsolutePath() + "/canstoreddata5", "canStoredData" + i + ".txt");
                        if (file.exists()) {
                            destFile = new File(media_filepath.getAbsolutePath() + "/canstoreddata5", "canStoredData" + (j++) + ".txt");
                            file.renameTo(destFile);
                        }
                    }

                    current_can_stored_file5 = "canStoredData" + (j) + ".txt";
                    current_can_stored_file_cnt5 = j;
                    write_can_storedData_Params_file5(current_can_stored_file5, current_can_stored_file_cnt5);
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata5", current_can_stored_file5);
                }
            }

            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();

        } catch (Exception e) {

        } finally {

            try {
                if (pw != null) {
                    pw.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {

            }
            str = null;
            file = null;
            destFile = null;
            pw = null;
            f = null;
        }
    }

    public void write_gps_stored_data_to_file_16833(String str) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        if (current_gps_stored_file.equals("")) {
            current_gps_stored_file = "gpsStoredData1.txt";
            current_gps_stored_file_cnt = 1;
            write_storedData_Params_file(current_gps_stored_file, current_gps_stored_file_cnt);
        }
        File file;
        File destFile;
        PrintWriter pw = null;
        FileOutputStream f = null;
        byte i = 0;
        byte j = 0;

        file = new File(media_filepath.getAbsolutePath() + "/storeddata");
        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true, false);
            file.setReadable(true, false);
        }
        file = new File(media_filepath.getAbsolutePath() + "/storeddata", current_gps_stored_file);
        try {
            if (file.length() > 10 * MBSIZE) {
                //check for unused file
                i = current_gps_stored_file_cnt;
                i++;
                if (i < MAX_NO_GPS_STORED_FILES) {
                    current_gps_stored_file = "gpsStoredData" + (i) + ".txt";
                    current_gps_stored_file_cnt = i;
                    write_storedData_Params_file(current_gps_stored_file, current_gps_stored_file_cnt);
                } else {
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata", "gpsStoredData1.txt");
                    if (file.exists()) {
                        file.delete();
                    }
                    j = 1;
                    for (i = 2; i < MAX_NO_GPS_STORED_FILES; i++) {
                        file = new File(media_filepath.getAbsolutePath() + "/storeddata", "gpsStoredData" + i + ".txt");
                        if (file.exists()) {
                            destFile = new File(media_filepath.getAbsolutePath() + "/storeddata", "gpsStoredData" + (j++) + ".txt");
                            file.renameTo(destFile);
                        }
                    }

                    current_gps_stored_file = "gpsStoredData" + (j) + ".txt";
                    current_gps_stored_file_cnt = j;
                    write_storedData_Params_file(current_gps_stored_file, current_gps_stored_file_cnt);
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata", current_gps_stored_file);
                }
            }

            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();

        } catch (Exception e) {

        } finally {

            try {
                if (pw != null) {
                    pw.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {

            }
            str = null;
            file = null;
            destFile = null;
            pw = null;
            f = null;
        }

    }

    public void write_gps_stored_data_to_file3_16833(String str) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        if (current_gps_stored_file3.equals("")) {
            current_gps_stored_file3 = "gpsStoredData1.txt";
            current_gps_stored_file_cnt3 = 1;
            write_storedData_Params_file3(current_gps_stored_file3, current_gps_stored_file_cnt3);
        }

        File file;
        File destFile;
        PrintWriter pw = null;
        FileOutputStream f = null;
        byte i;
        byte j;

        file = new File(media_filepath.getAbsolutePath() + "/storeddata3");
        if (!file.exists()) {
            file.mkdirs();
            file.setWritable(true, false);
            file.setReadable(true, false);
        }

        file = new File(media_filepath.getAbsolutePath() + "/storeddata3", current_gps_stored_file3);
        try {
            if (file.length() > 10 * MBSIZE) {
                //check for unused file
                i = current_gps_stored_file_cnt3;
                i++;
                if (i < MAX_NO_GPS_STORED_FILES) {
                    current_gps_stored_file3 = "gpsStoredData" + (i) + ".txt";
                    current_gps_stored_file_cnt3 = i;
                    write_storedData_Params_file3(current_gps_stored_file3, current_gps_stored_file_cnt3);
                } else {
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata3", "gpsStoredData1.txt");
                    if (file.exists()) {
                        file.delete();
                    }
                    j = 1;
                    for (i = 3; i < MAX_NO_GPS_STORED_FILES; i++) {
                        file = new File(media_filepath.getAbsolutePath() + "/storeddata3", "gpsStoredData" + i + ".txt");
                        if (file.exists()) {
                            destFile = new File(media_filepath.getAbsolutePath() + "/storeddata3", "gpsStoredData" + (j++) + ".txt");
                            file.renameTo(destFile);
                        }
                    }

                    current_gps_stored_file3 = "gpsStoredData" + (j) + ".txt";
                    current_gps_stored_file_cnt3 = j;
                    write_storedData_Params_file3(current_gps_stored_file3, current_gps_stored_file_cnt3);
                    file = new File(media_filepath.getAbsolutePath() + "/storeddata3", current_gps_stored_file3);
                }
            }

            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();

        } catch (FileNotFoundException e) {

        } finally {

            try {
                if (pw != null) {
                    pw.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (IOException e) {

            }
            str = null;
            file = null;
            destFile = null;
            pw = null;
            f = null;
        }

    }

    public void delete_gps_can_stored_data_file_obu_change() {

        File file;
        File[] files;
        int i = 0;

        try {
            file = new File(media_filepath.getAbsolutePath() + "/storeddata");
            if (file.exists()) {

                files = file.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {
                        if (!files[i].isDirectory()) {

                            try {

                                files[i].delete();

                            } catch (Exception ex) {

                            }
                        }

                    } catch (Exception ex) {

                    }

                }
            }

            current_gps_stored_file = "gpsStoredData1.txt";
            current_gps_stored_file_cnt = 1;
            write_storedData_Params_file(current_gps_stored_file, current_gps_stored_file_cnt);

            //gps stored data 2
            file = new File(media_filepath.getAbsolutePath() + "/storeddata2");
            if (file.exists()) {

                files = file.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {
                        if (!files[i].isDirectory()) {

                            try {

                                files[i].delete();

                            } catch (Exception ex) {

                            }
                        }

                    } catch (Exception ex) {

                    }

                }
            }

            current_gps_stored_file2 = "gpsStoredData1.txt";
            current_gps_stored_file_cnt2 = 1;
            write_storedData_Params_file2(current_gps_stored_file2, current_gps_stored_file_cnt2);

            //gps stored data 4
            file = new File(media_filepath.getAbsolutePath() + "/storeddata4");
            if (file.exists()) {

                files = file.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {
                        if (!files[i].isDirectory()) {

                            try {

                                files[i].delete();

                            } catch (Exception ex) {

                            }
                        }

                    } catch (Exception ex) {

                    }

                }
            }

            current_gps_stored_file4 = "gpsStoredData1.txt";
            current_gps_stored_file_cnt4 = 1;
            write_storedData_Params_file4(current_gps_stored_file4, current_gps_stored_file_cnt4);

            //can file path 1
            file = new File(media_filepath.getAbsolutePath() + "/canstoreddata");
            if (file.exists()) {

                files = file.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {
                        if (!files[i].isDirectory()) {

                            try {

                                files[i].delete();

                            } catch (Exception ex) {

                            }
                        }

                    } catch (Exception ex) {

                    }

                }
            }

            current_can_stored_file = "canStoredData1.txt";
            current_can_stored_file_cnt = 1;
            write_can_storedData_Params_file(current_can_stored_file, current_can_stored_file_cnt);

            file = new File(media_filepath.getAbsolutePath() + "/canstoreddata2");
            if (file.exists()) {

                files = file.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {
                        if (!files[i].isDirectory()) {
                            try {
                                files[i].delete();
                            } catch (Exception ex) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
            current_can_stored_file2 = "canStoredData1.txt";
            current_can_stored_file_cnt2 = 1;
            write_can_storedData_Params_file2(current_can_stored_file2, current_can_stored_file_cnt2);

        } catch (Exception e) {

        } finally {
            file = null;
            files = null;
        }

    }

    public void delete_nonobuid_files(String obuid) {
        int i = 0;
        File[] files = null;
        File f = log_filepath;
        String str;
        try {
            if (f.exists()) {
                files = f.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {
                        if (!files[i].isDirectory()) {
                            str = files[i].getName();
                            try {
                                if (!str.contains(obuid)) {
                                    files[i].delete();
                                }
                            } catch (Exception ex) {
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
            }
            f = can_filepath;
            if (f.exists()) {
                files = f.listFiles();
                if (files == null) {
                    return;
                }
                Arrays.sort(files);

                for (i = 0; i < files.length; i++) {
                    try {

                        str = files[i].getName();
                        try {
                            if (!str.contains(obuid)) {
                                files[i].delete();
                            }
                        } catch (Exception ex) {
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (Exception ex) {
        } finally {
            files = null;
            f = null;
            str = null;
            obuid = null;
        }
    }

    public synchronized boolean write_can_storedData_Params_file(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "CanStoredDataParams.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            file = null;
            f = null;
            pw = null;

        }
        return true;
    }

    public synchronized boolean write_can_storedData_Params_file2(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "CanStoredDataParams2.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception ex) {
            return false;
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        return true;
    }

    public synchronized boolean write_can_storedData_Params_file5(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "CanStoredDataParams5.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception ex) {
            return false;
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        return true;
    }

    public int read_can_stored_data_from_file5() {
        BufferedReader br = null;
        int i = 0;
        boolean found = false;
        File file = null;
        File[] listFilesStoredData = new File[1];
        String fileName;
        int cnt = 0;
        String str;
        String[] split_str;
        byte[] readDataBuf;
        clsCanQueue objCanQueue = new clsCanQueue();
        try {
            file = new File(media_filepath.getAbsolutePath() + "/canstoreddata5");
            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true, false);
                file.setReadable(true, false);
            } else {
                if (file.listFiles() != null && file.listFiles().length == 0) {
                    file = null;
                    fileName = null;
                    listFilesStoredData = null;

                    return 0;
                }

                listFilesStoredData = file.listFiles();
            }
            fileName = media_filepath.getAbsolutePath() + "/canstoreddata5/" + current_can_stored_file5;
            file = new File(fileName);
            if (!file.exists()) {

                if (listFilesStoredData.length > 0) {
                    file = listFilesStoredData[listFilesStoredData.length - 1];
                    if (file.exists()) {
                        current_can_stored_file5 = listFilesStoredData[listFilesStoredData.length - 1].getName();
                        current_can_stored_file_cnt5 = (byte) (listFilesStoredData.length - 1);
                        write_can_storedData_Params_file5(current_can_stored_file5, current_can_stored_file_cnt5);
                        found = true;
                    }
                } else {
                    found = false;
                }

            } else {
                found = true;
            }
            if (!found && !current_can_stored_file5.equals("canStoredData1.txt")) {
                current_can_stored_file5 = "canStoredData1.txt";
                current_can_stored_file_cnt5 = 1;
                write_can_storedData_Params_file5(current_can_stored_file5, current_can_stored_file_cnt5);
            } else if (found) {
                fileName = media_filepath.getAbsolutePath() + "/canstoreddata5/" + current_can_stored_file5;
                file = new File(fileName);
                if (file.exists()) {
                    br = new BufferedReader(new FileReader(file));
                    while ((str = br.readLine()) != null) {
                        if (str.length() > 10) {
                            readDataBuf = str.getBytes();
                            objCanQueue.addDataIp5(readDataBuf);
                            cnt++;
                        }
                        if (cnt > 500) {
                            break;
                        }
                    }
                    if (cnt >= 500) {
                        br.close();
                        br = null;
                        file = null;
                        delete_lines_file_can_storeddata5("canstoreddata5/" + current_can_stored_file5, cnt);
                    } else {
                        br.close();
                        br = null;
                        file = new File(fileName);
                        try {
                            file.delete();
                            file = null;
                        } catch (Exception ex) {

                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            try {
                if (br != null) {
                    br.close();
                    br = null;
                }
                file = null;
                fileName = null;
                listFilesStoredData = null;

                br = null;

                str = null;
                split_str = null;
                readDataBuf = null;
                objCanQueue = null;
            } catch (IOException e) {

            }
        }
        return cnt;
    }

    public void delete_lines_file_can_storeddata5(String filename, int no_lines) {
        try {
            File targetFile = new File(media_filepath, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));
                File tempFile = new File(media_filepath, "tempcanstored5.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                targetBuf.close();
                printTemp.close();
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public int read_gps_stored_data_from_file4() {
        BufferedReader br = null;
        int i;
        boolean found = false;
        File file = null;
        File[] listFilesStoredData = new File[1];
        String fileName;
        int cnt = 0;

        String str;
        String[] split_str;
        byte[] readDataBuf;
        clsGpsLinkedList objList = new clsGpsLinkedList();
        try {
            if (!getHardDriveDetected()) {
                return 0;
            }
            file = new File(media_filepath.getAbsolutePath() + "/storeddata4");

            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true, false);
                file.setReadable(true, false);
            } else {
                listFilesStoredData = file.listFiles();
                if (listFilesStoredData != null && listFilesStoredData.length == 0) {
                    file = null;
                    fileName = null;
                    listFilesStoredData = null;
                    return 0;
                }

            }
            fileName = media_filepath.getAbsolutePath() + "/storeddata4" + "/" + current_gps_stored_file4;
            file = new File(fileName);
            if (!file.exists()) {

                if (listFilesStoredData.length > 0) {
                    file = listFilesStoredData[listFilesStoredData.length - 1];
                    if (file.exists()) {
                        current_gps_stored_file4 = listFilesStoredData[listFilesStoredData.length - 1].getName();
                        current_gps_stored_file_cnt4 = (byte) (listFilesStoredData.length - 1);
                        write_storedData_Params_file4(current_gps_stored_file4, current_gps_stored_file_cnt4);
                        found = true;
                    }
                } else {
                    found = false;
                }

            } else {
                found = true;
            }
            if (!found && !current_gps_stored_file4.equals("gpsStoredData1.txt")) {
                current_gps_stored_file4 = "gpsStoredData1.txt";
                current_gps_stored_file_cnt4 = 1;
                write_storedData_Params_file4(current_gps_stored_file4, current_gps_stored_file_cnt4);
            } else if (found) {
                fileName = media_filepath.getAbsolutePath() + "/storeddata4" + "/" + current_gps_stored_file4;
                file = new File(fileName);
                if (file.exists()) {
                    br = new BufferedReader(new FileReader(file));

                    while ((str = br.readLine()) != null) {
                        split_str = str.split(",");
                        readDataBuf = new byte[split_str.length];
                        if (readDataBuf.length > 16) {
                            for (i = 0; i < readDataBuf.length; i++) {
                                try {
                                    readDataBuf[i] = (byte) (Short.parseShort(split_str[i]));
                                } catch (NumberFormatException ex) {
                                }
                            }
                            objList.addLastDataIp4(readDataBuf);

                            cnt++;
                        }
                        if (cnt > 100) {
                            break;
                        }
                    }
                    if (cnt >= 100) {
                        br.close();
                        br = null;
                        file = null;
                        delete_lines_file_storeddata4("storeddata4/" + current_gps_stored_file4, cnt);

                    } else {
                        br.close();
                        br = null;
                        file = new File(fileName);
                        try {
                            file.delete();
                            file = null;
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            try {
                if (br != null) {
                    br.close();
                    br = null;
                }
                file = null;
                fileName = null;
                listFilesStoredData = null;
                str = null;
                split_str = null;
                readDataBuf = null;
                objList = null;
            } catch (IOException e) {

            }
        }
        return cnt;
    }

    public int read_gps_stored_data_from_file5() {
        BufferedReader br = null;
        int i;
        boolean found = false;
        File file = null;
        File[] listFilesStoredData = new File[1];
        String fileName;
        int cnt = 0;
        String str;
        byte[] readDataBuf;
        clsGpsLinkedList objList = new clsGpsLinkedList();
        try {
            if (!getHardDriveDetected()) {
                return 0;
            }
            file = new File(media_filepath.getAbsolutePath() + "/storeddata5");
            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true, false);
                file.setReadable(true, false);
            } else {
                listFilesStoredData = file.listFiles();
                if (listFilesStoredData != null && listFilesStoredData.length == 0) {
                    file = null;
                    fileName = null;
                    listFilesStoredData = null;
                    return 0;
                }

            }
            fileName = media_filepath.getAbsolutePath() + "/storeddata5" + "/" + current_gps_stored_file5;
            file = new File(fileName);
            if (!file.exists()) {

                if (listFilesStoredData.length > 0) {
                    file = listFilesStoredData[listFilesStoredData.length - 1];
                    if (file.exists()) {
                        current_gps_stored_file5 = listFilesStoredData[listFilesStoredData.length - 1].getName();
                        current_gps_stored_file_cnt5 = (byte) (listFilesStoredData.length - 1);
                        write_storedData_Params_file5(current_gps_stored_file5, current_gps_stored_file_cnt5);
                        found = true;
                    }
                } else {
                    found = false;
                }
            } else {
                found = true;
            }
            if (!found && !current_gps_stored_file5.equals("gpsStoredData1.txt")) {
                current_gps_stored_file5 = "gpsStoredData1.txt";
                current_gps_stored_file_cnt5 = 1;
                write_storedData_Params_file5(current_gps_stored_file5, current_gps_stored_file_cnt5);
            } else if (found) {
                fileName = media_filepath.getAbsolutePath() + "/storeddata5" + "/" + current_gps_stored_file5;
                file = new File(fileName);
                if (file.exists()) {
                    br = new BufferedReader(new FileReader(file));

                    while ((str = br.readLine()) != null) {

                        if (str.length() > 10) {
                            readDataBuf = str.getBytes();
                            objList.addLastDataIp5(readDataBuf);
                            cnt++;
                        }
                        if (cnt > 100) {
                            break;
                        }
                    }
                    if (cnt >= 100) {
                        br.close();
                        br = null;
                        file = null;
                        delete_lines_file_storeddata5("storeddata5/" + current_gps_stored_file5, cnt);
                    } else {
                        br.close();
                        br = null;
                        file = new File(fileName);
                        try {
                            file.delete();
                            file = null;
                        } catch (Exception ex) {

                        }
                    }

                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            try {
                if (br != null) {
                    br.close();
                    br = null;
                }
                file = null;
                fileName = null;
                listFilesStoredData = null;
                str = null;

                readDataBuf = null;
                objList = null;
            } catch (IOException e) {
            }
        }
        return cnt;
    }

    public int read_gps_stored_data_from_file_16833() {
        BufferedReader br = null;
        int i;
        boolean found = false;
        File file;
        File[] listFilesStoredData = new File[1];
        String fileName;
        int cnt = 0;

        String str;

        byte[] readDataBuf;
        try {
            if (!getHardDriveDetected()) {
                return 0;
            }
            file = new File(media_filepath.getAbsolutePath() + "/storeddata");
            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true, false);
                file.setReadable(true, false);
            } else {
                listFilesStoredData = file.listFiles();
                if (listFilesStoredData != null && listFilesStoredData.length == 0) {
                    file = null;
                    fileName = null;
                    listFilesStoredData = null;
                    return 0;
                }
            }
            fileName = media_filepath.getAbsolutePath() + "/storeddata" + "/" + current_gps_stored_file;
            file = new File(fileName);
            if (!file.exists()) {
                if (listFilesStoredData.length > 0) {
                    file = listFilesStoredData[listFilesStoredData.length - 1];
                    if (file.exists()) {
                        current_gps_stored_file = listFilesStoredData[listFilesStoredData.length - 1].getName();
                        current_gps_stored_file_cnt = (byte) (listFilesStoredData.length - 1);
                        write_storedData_Params_file(current_gps_stored_file, current_gps_stored_file_cnt);
                        found = true;
                    }
                } else {
                    found = false;
                }

            } else {
                found = true;
            }
            if (!found && !current_gps_stored_file.equals("gpsStoredData1.txt")) {
                current_gps_stored_file = "gpsStoredData1.txt";
                current_gps_stored_file_cnt = 1;
                write_storedData_Params_file(current_gps_stored_file, current_gps_stored_file_cnt);
            } else if (found) {
                fileName = media_filepath.getAbsolutePath() + "/storeddata" + "/" + current_gps_stored_file;
                file = new File(fileName);
                if (file.exists()) {
                    br = new BufferedReader(new FileReader(file));
                    clsGpsLinkedList objList = new clsGpsLinkedList();
                    while ((str = br.readLine()) != null) {
                        if (str.startsWith("$LOC")) {
                            readDataBuf = str.getBytes();
                            objList.addLastData(readDataBuf);
                            cnt++;
                        }
                    }
                    br.close();
                    br = null;
                    file = new File(fileName);
                    try {
                        if (file.delete() == false) {
                            file.delete();
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            try {
                if (br != null) {
                    br.close();
                    br = null;
                }
                file = null;
                fileName = null;
                listFilesStoredData = null;
                str = null;
                readDataBuf = null;
            } catch (IOException e) {

            }
        }
        return cnt;
    }

    public int read_gps_stored_data_from_file3_16833() {
        BufferedReader br = null;

        boolean found = false;
        File file;
        File[] listFilesStoredData = new File[1];
        String fileName;
        int cnt = 0;
        String str;
        byte[] readDataBuf;
        try {
            if (!getHardDriveDetected()) {
                return 0;
            }
            file = new File(media_filepath.getAbsolutePath() + "/storeddata3");
            if (!file.exists()) {
                file.mkdirs();
                file.setWritable(true, false);
                file.setReadable(true, false);
            } else {
                listFilesStoredData = file.listFiles();
                if (listFilesStoredData != null && listFilesStoredData.length == 0) {
                    file = null;
                    fileName = null;
                    listFilesStoredData = null;
                    return 0;
                }
            }
            fileName = media_filepath.getAbsolutePath() + "/storeddata3" + "/" + current_gps_stored_file3;
            file = new File(fileName);
            if (!file.exists()) {
                if (listFilesStoredData.length > 0) {
                    file = listFilesStoredData[listFilesStoredData.length - 1];
                    if (file.exists()) {
                        current_gps_stored_file3 = listFilesStoredData[listFilesStoredData.length - 1].getName();
                        current_gps_stored_file_cnt3 = (byte) (listFilesStoredData.length - 1);
                        write_storedData_Params_file3(current_gps_stored_file3, current_gps_stored_file_cnt3);
                        found = true;
                    }
                } else {
                    found = false;
                }
            } else {
                found = true;
            }
            if (!found && !current_gps_stored_file3.equals("gpsStoredData1.txt")) {
                current_gps_stored_file3 = "gpsStoredData1.txt";
                current_gps_stored_file_cnt3 = 1;
                write_storedData_Params_file3(current_gps_stored_file3, current_gps_stored_file_cnt3);
            } else if (found) {
                fileName = media_filepath.getAbsolutePath() + "/storeddata3" + "/" + current_gps_stored_file3;
                file = new File(fileName);
                if (file.exists()) {
                    br = new BufferedReader(new FileReader(file));
                    clsGpsLinkedList objList = new clsGpsLinkedList();
                    while ((str = br.readLine()) != null) {
                        if (str.startsWith("$LOC")) {
                            readDataBuf = str.getBytes();
                            objList.addLastDataIp3(readDataBuf);
                            cnt++;
                        }
                    }
                    objList = null;

                    br.close();
                    br = null;

                    file = new File(fileName);
                    try {
                        file.delete();
                        file = null;
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (Exception ex) {

        } finally {
            try {
                if (br != null) {
                    br.close();
                    br = null;
                }
                file = null;
                fileName = null;
                listFilesStoredData = null;
                str = null;
                readDataBuf = null;
            } catch (IOException e) {
            }
        }
        return cnt;
    }

    public synchronized void read_current_route_info() {

        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(route_filepath, "currentRouteInfo.txt");
        File f = new File(main_route_filepath, "currentRouteInfo.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    setCurRouteNo((split_str[0]));
                    setCurRouteStat(Boolean.parseBoolean(split_str[1]));
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    file = null;
                    br = null;
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                    f = null;
                } catch (IOException e) {
                }
            }
        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    if (split_str.length >= 2) {
                        setCurRouteNo((split_str[0]));
                        setCurRouteStat(Boolean.parseBoolean(split_str[1]));
                    }
                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    file = null;
                    br = null;
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                    f = null;
                } catch (IOException e) {
                }
            }
        }
    }

    public synchronized void write_cur_route_info_file() {

        String data;
        File file = new File(route_filepath, "currentRouteInfo.txt");
        File file1 = new File(main_route_filepath, "currentRouteInfo.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;

        try {
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                data = getCurRouteNo() + "," + getCurRouteStat();
                pw.println(data);
                pw.flush();
                pw.close();
                f.close();
                pw = null;
                f = null;
            }
            try {
                if (clsDefines.DUPLICATE_FILES_MAIN_PATH && clsSharedVariables.getStorageType() == clsDefines.STORAGE_HARDDISK) {
                    f = new FileOutputStream(file1, false);
                    pw = new PrintWriter(f);
                    data = getCurRouteNo() + "," + getCurRouteStat();
                    pw.println(data);
                    pw.flush();
                    pw.close();
                    f.close();
                    pw = null;
                    f = null;
                }
            } catch (Exception e) {

            }

        } catch (FileNotFoundException e) {
        } catch (Exception e) {

        } finally {
            try {
                if (pw != null) {
                    pw.close();
                    f.close();
                }
            } catch (Exception e) {

            }
            file = null;
            pw = null;
            f = null;
            data = null;
            file1 = null;
        }
    }

    public synchronized void init_route_master_file() {
        short i = 0;
        short j = 0;
        objRouteMasFiles = new clsSharedVariables.RouteMasFiles[clsDefines.MAX_NO_ROUTES];
        for (i = 0; i < clsDefines.MAX_NO_ROUTES; i++) {
            objRouteMasFiles[i] = new clsSharedVariables.RouteMasFiles();
            objRouteMasFiles[i].front_file_name = "";
            objRouteMasFiles[i].side_file_name = "";
            objRouteMasFiles[i].rear_file_name = "";
            objRouteMasFiles[i].no_slogans = 0;
            objRouteMasFiles[i].slogan_files = new String[MAX_NO_SLOGAN_ID_FILES];
            for (j = 0; j < MAX_NO_SLOGAN_ID_FILES; j++) {
                objRouteMasFiles[i].slogan_files[j] = "";
            }
        }
    }

    public synchronized void read_route_master_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(route_filepath, "routemas.txt");
        File f = new File(main_route_filepath, "routemas.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    try {
                        if (!split_str[0].equals("")) {
                            objRouteMasFiles[i].route_no = split_str[0];
                            objRouteMasFiles[i].front_file_name = split_str[1];
                            objRouteMasFiles[i].side_file_name = split_str[2];
                            objRouteMasFiles[i].rear_file_name = split_str[3];
                            objRouteMasFiles[i].bdc_file_name = split_str[4];
                            objRouteMasFiles[i].no_slogans = Short.parseShort(split_str[5]);
                            objRouteMasFiles[i].slogan_files = new String[objRouteMasFiles[i].no_slogans];
                            for (j = 0; j < objRouteMasFiles[i].no_slogans; j++) {
                                objRouteMasFiles[i].slogan_files[j] = split_str[j + 6];
                            }
                            i++;
                        }
                    } catch (Exception ex) {
                    }
                }
                setNoRoutes(i);

            } catch (IOException e) {
            } catch (Exception ex) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    br = null;
                    file = null;
                    line = null;
                    br = null;
                    f = null;
                } catch (IOException e) {

                } catch (Exception e) {

                }
            }
        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {

            try {
                br = new BufferedReader(new FileReader(f));

                while ((line = br.readLine()) != null) {

                    split_str = line.split(",");
                    try {
                        if (!split_str[0].equals("")) {
                            objRouteMasFiles[i].route_no = split_str[0];
                            objRouteMasFiles[i].front_file_name = split_str[1];
                            objRouteMasFiles[i].side_file_name = split_str[2];
                            objRouteMasFiles[i].rear_file_name = split_str[3];
                            objRouteMasFiles[i].bdc_file_name = split_str[4];
                            objRouteMasFiles[i].no_slogans = Short.parseShort(split_str[5]);
                            objRouteMasFiles[i].slogan_files = new String[objRouteMasFiles[i].no_slogans];
                            for (j = 0; j < objRouteMasFiles[i].no_slogans; j++) {
                                objRouteMasFiles[i].slogan_files[j] = split_str[j + 6];
                            }
                            i++;
                        }
                    } catch (Exception ex) {
                    }
                }
                setNoRoutes(i);

            } catch (IOException e) {
            } catch (Exception ex) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    br = null;
                    file = null;
                    line = null;
                    br = null;
                    f = null;

                } catch (IOException e) {

                } catch (Exception e) {

                }
            }
        } else {
            setNoRoutes((short) 0);
        }
    }

    public synchronized void init_locationbased_ads_master_file() {
        short i = 0;

        objLocBasedAdsFiles = new clsSharedVariables.RouteLocationAdsFiles[clsDefines.MAX_NO_LOCATION_BASED_ADS];
        for (i = 0; i < clsDefines.MAX_NO_LOCATION_BASED_ADS; i++) {
            objLocBasedAdsFiles[i] = new clsSharedVariables.RouteLocationAdsFiles();
            objLocBasedAdsFiles[i].audio_file_name = "";
            objLocBasedAdsFiles[i].audio_repeat = 0;
            objLocBasedAdsFiles[i].distance = 0;
            objLocBasedAdsFiles[i].file_name = "";
            objLocBasedAdsFiles[i].latitude = 0.0;
            objLocBasedAdsFiles[i].longitude = 0.0;
            objLocBasedAdsFiles[i].message_name = "";
            objLocBasedAdsFiles[i].route_no = "";
            objLocBasedAdsFiles[i].status = false;
        }
    }

    public synchronized void read_locationbased_ads_master_file(String route_no) {
        short i = 0;
        byte j = 0;
        String[] split_str;
        String[] split_str2;
        String line;
        BufferedReader br = null;
        File file = new File(route_filepath, "routeLocationAds.txt");
        File f = new File(main_route_filepath, "routeLocationAds.txt");
        current_loc_based_ad = 0;
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {

                    split_str = line.split(",");
                    try {
                        if (split_str[0].equals(route_no)) {

                            split_str2 = line.split("#", -1);
                            if (split_str2.length > 0) {
                                for (j = 0; j < split_str2.length; j++) {

                                    split_str = split_str2[j].split(",");

                                    for (i = 0; i < split_str.length; i++) {
                                        if (j == 0) {
                                            objLocBasedAdsFiles[j].route_no = split_str[i];

                                        }
                                        i++;
                                        try {
                                            objLocBasedAdsFiles[j].message_name = split_str[i++];
                                            objLocBasedAdsFiles[j].audio_repeat = Byte.parseByte(split_str[i++]);
                                            objLocBasedAdsFiles[j].distance = Integer.parseInt(split_str[i++]);
                                            objLocBasedAdsFiles[j].latitude = Double.parseDouble(split_str[i++]);
                                            objLocBasedAdsFiles[j].longitude = Double.parseDouble(split_str[i++]);
                                            objLocBasedAdsFiles[j].file_name = split_str[i++];
                                            objLocBasedAdsFiles[j].audio_file_name = split_str[i++];
                                            objLocBasedAdsFiles[j].status = false;
                                        } catch (Exception ex) {

                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
                no_loc_based_ads = (j);

            } catch (IOException e) {
            } catch (Exception ex) {
            } finally {
                try {
                    no_loc_based_ads = (j);
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    br = null;
                    file = null;
                    line = null;
                    br = null;
                    f = null;
                } catch (IOException e) {

                } catch (Exception e) {

                }
            }
        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {

            try {
                br = new BufferedReader(new FileReader(f));

                while ((line = br.readLine()) != null) {

                    split_str = line.split(",");
                    try {
                        if (split_str[0].equals(route_no)) {
                            split_str2 = line.split("^");
                            if (split_str2.length > 0) {
                                for (j = 0; j < split_str2.length; j++) {
                                    split_str = split_str2[j].split(",");

                                    for (i = 0; i < split_str.length; i++) {
                                        if (i == 0) {
                                            objLocBasedAdsFiles[j].route_no = split_str[i++];
                                        }
                                        objLocBasedAdsFiles[j].message_name = split_str[i++];
                                        objLocBasedAdsFiles[j].audio_repeat = Byte.parseByte(split_str[i++]);
                                        objLocBasedAdsFiles[j].distance = Integer.parseInt(split_str[i++]);
                                        objLocBasedAdsFiles[j].latitude = Double.parseDouble(split_str[i++]);
                                        objLocBasedAdsFiles[j].longitude = Double.parseDouble(split_str[i++]);
                                        objLocBasedAdsFiles[j].file_name = split_str[i++];
                                        objLocBasedAdsFiles[j].audio_file_name = split_str[i++];
                                        objLocBasedAdsFiles[j].status = false;
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                    }
                }

            } catch (IOException e) {
            } catch (Exception ex) {
            } finally {
                try {
                    no_loc_based_ads = (j);
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    br = null;
                    file = null;
                    line = null;
                    br = null;
                    f = null;

                } catch (IOException e) {

                } catch (Exception e) {

                }
            }
        } else {
            no_loc_based_ads = (j);
        }
    }

    public synchronized void readBdcFile(String file_name) {
        short i = 0;
        int j = 0;
        short k = 0;
        short l = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        String reg_name = "";
        String[] reg_name_split;
        byte[] bytes;
        File file = new File(route_filepath, file_name);
        File f = new File(main_route_filepath, file_name);
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {

                    k = 0;
                    split_str = line.split(",");
                    try {
                        if (!split_str[0].equals("")) {
                            gps_data[i] = new clsSharedVariables.classGPSData();
                            gps_data[i].stop_name = split_str[k++];

                            try {
                                reg_name = split_str[k++];
                                if (!reg_name.trim().equals("")) {
                                    reg_name = reg_name.replace("\\", "");
                                    reg_name_split = reg_name.split("u", -1);
                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                    l = 0;
                                    for (j = 0; j < reg_name_split.length; j++) {
                                        if (!reg_name_split[j].trim().equals("")) {
                                            if (reg_name_split[j].length() == 4) {
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                            }
                                        }
                                    }
                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                }
                            } catch (Exception ex) {
                                reg_name = gps_data[i].stop_name;
                            }
                            gps_data[i].reg1_stop_name = reg_name;

                            //reg2 stop name
                            try {
                                reg_name = split_str[k++];
                                if (!reg_name.trim().equals("")) {
                                    reg_name = reg_name.replace("\\", "");
                                    reg_name_split = reg_name.split("u", -1);
                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                    l = 0;
                                    for (j = 0; j < reg_name_split.length; j++) {
                                        if (!reg_name_split[j].trim().equals("")) {
                                            if (reg_name_split[j].length() == 4) {
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                            }
                                        }
                                    }
                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                }
                            } catch (Exception ex) {
                                reg_name = gps_data[i].stop_name;
                            }
                            gps_data[i].reg2_stop_name = reg_name;
                            gps_data[i].latitude = Double.parseDouble(split_str[k++]);
                            gps_data[i].longitude = Double.parseDouble(split_str[k++]);
                            gps_data[i].arr_dis = Short.parseShort(split_str[k++]);
                            gps_data[i].arr_db_file = split_str[k++]; //text file display brd
                            gps_data[i].arr_eng_file = split_str[k++]; //audio
                            gps_data[i].arr_reg1_file = split_str[k++];//audio
                            gps_data[i].arr_reg2_file = split_str[k++];//audio
                            gps_data[i].cur_dis = Short.parseShort(split_str[k++]);
                            gps_data[i].cur_db_file = split_str[k++];//text file display brd
                            gps_data[i].cur_eng_file = split_str[k++];//audio
                            gps_data[i].cur_reg1_file = split_str[k++];//audio
                            gps_data[i].cur_reg2_file = split_str[k++];//audio
                            gps_data[i].next_dis = Short.parseShort(split_str[k++]);
                            gps_data[i].next_db_file = split_str[k++];//text file display brd
                            gps_data[i].next_eng_file = split_str[k++];//audio
                            gps_data[i].next_reg1_file = split_str[k++];//audio
                            gps_data[i].next_reg2_file = split_str[k++];//audio
                            gps_data[i].stop_stop_dis = Short.parseShort(split_str[k++]);
                            gps_data[i].stop_identify_status = false;
                            i++;
                        }
                    } catch (Exception ex) {

                    }

                }

            } catch (IOException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
                setNoStopsRoute(i);
            }
        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {

            try {
                br = new BufferedReader(new FileReader(f));

                while ((line = br.readLine()) != null) {

                    k = 0;
                    split_str = line.split(",");
                    try {
                        if (!split_str[0].equals("")) {
                            gps_data[i] = new clsSharedVariables.classGPSData();
                            gps_data[i].stop_name = split_str[k++];

                            try {
                                reg_name = split_str[k++];
                                if (!reg_name.trim().equals("")) {
                                    reg_name = reg_name.replace("\\", "");
                                    reg_name_split = reg_name.split("u", -1);
                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                    l = 0;
                                    for (j = 0; j < reg_name_split.length; j++) {
                                        if (!reg_name_split[j].trim().equals("")) {
                                            if (reg_name_split[j].length() == 4) {
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                            }
                                        }
                                    }
                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                }
                            } catch (Exception ex) {
                                reg_name = gps_data[i].stop_name;
                            }
                            gps_data[i].reg1_stop_name = reg_name;

                            //reg2 stop name
                            try {
                                reg_name = split_str[k++];
                                if (!reg_name.trim().equals("")) {
                                    reg_name = reg_name.replace("\\", "");
                                    reg_name_split = reg_name.split("u", -1);
                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                    l = 0;
                                    for (j = 0; j < reg_name_split.length; j++) {
                                        if (!reg_name_split[j].trim().equals("")) {
                                            if (reg_name_split[j].length() == 4) {
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                            }
                                        }
                                    }
                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                }
                            } catch (Exception ex) {
                                reg_name = gps_data[i].stop_name;
                            }
                            gps_data[i].reg2_stop_name = reg_name;

                            gps_data[i].latitude = Double.parseDouble(split_str[k++].trim());

                            gps_data[i].longitude = Double.parseDouble(split_str[k++].trim());

                            gps_data[i].arr_dis = Short.parseShort(split_str[k++].trim());

                            gps_data[i].arr_db_file = split_str[k++]; //text file display brd
                            gps_data[i].arr_eng_file = split_str[k++]; //audio
                            gps_data[i].arr_reg1_file = split_str[k++];//audio
                            gps_data[i].arr_reg2_file = split_str[k++];//audio

                            gps_data[i].cur_dis = Short.parseShort(split_str[k++].trim());
                            gps_data[i].cur_db_file = split_str[k++];//text file display brd
                            gps_data[i].cur_eng_file = split_str[k++];//audio
                            gps_data[i].cur_reg1_file = split_str[k++];//audio
                            gps_data[i].cur_reg2_file = split_str[k++];//audio

                            gps_data[i].next_dis = Short.parseShort(split_str[k++].trim());
                            gps_data[i].next_db_file = split_str[k++];//text file display brd
                            gps_data[i].next_eng_file = split_str[k++];//audio
                            gps_data[i].next_reg1_file = split_str[k++];//audio
                            gps_data[i].next_reg2_file = split_str[k++];//audio

                            gps_data[i].stop_stop_dis = Short.parseShort(split_str[k++].trim());
                            gps_data[i].stop_identify_status = false;
                            i++;
                        }
                    } catch (Exception ex) {

                    }

                }

            } catch (IOException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    br = null;
                    file = null;
                    line = null;

                    reg_name = null;
                    reg_name_split = null;
                    bytes = null;
                } catch (IOException e) {

                }
                setNoStopsRoute(i);
            }
        } else {
            setNoStopsRoute((short) 0);
        }
    }

    public synchronized boolean load_temp_schedule() {
        short j = 0;

        if (clsSharedVariables.temp_sch.sch_id.equals("0")) {
            return false;
        }
        setSchId(clsSharedVariables.temp_sch.sch_id);
        setCurSchStartDate(clsSharedVariables.temp_sch.sch_startdatetime);
        setCurSchEndDate(clsSharedVariables.temp_sch.sch_enddatetime);
        setCurSchStartTime(clsSharedVariables.temp_sch.sch_startdatetime.getTime());
        setCurSchEndTime(clsSharedVariables.temp_sch.sch_enddatetime.getTime());
        setCurSchNoTrips(clsSharedVariables.temp_sch.no_trips);
        for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
            setCurSchRouteNo(j, clsSharedVariables.temp_sch.route_nos[j]);
        }
        for (j = 0; j < clsSharedVariables.temp_sch.no_trips; j++) {
            setCurSchTripStatus(j, clsSharedVariables.temp_sch.trip_status[j]);
        }
        setCurStopNo((byte) 0);
        setCurTripNo((byte) 0);
        setCurRouteNo(clsSharedVariables.temp_sch.route_nos[0]);
        setCurSchNoTrips(clsSharedVariables.temp_sch.no_trips);
        setCurRouteStat(false);
        setCurTripStat(TRIP_UNKNOWN);
        setCurSchAvailable(true);
        setTempSchAvailable(false);
        write_cur_trip_info_file();
        write_cur_route_info_file();
        write_cur_schedule_file();
        write_temp_schedule_file();
        return true;
    }

    public synchronized boolean load_perm_schedule() {
        short j = 0;
        Calendar c = Calendar.getInstance();

        if (clsSharedVariables.perm_sch.sch_id.equals("0")) {
            return false;
        }
        try {
            setSchId(clsSharedVariables.perm_sch.sch_id);
            setCurSchStartDate(clsSharedVariables.perm_sch.sch_startdatetime);
            setCurSchEndDate(clsSharedVariables.perm_sch.sch_enddatetime);

            setCurSchStartTime(getPermSchStartTime());
            setCurSchEndTime(clsSharedVariables.perm_sch.sch_endtime);

            setCurSchNoTrips(clsSharedVariables.perm_sch.no_trips);
            for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                setCurSchRouteNo(j, clsSharedVariables.perm_sch.route_nos[j]);
            }
            for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                setCurSchTripStatus(j, clsSharedVariables.perm_sch.trip_status[j]);
                setCurRouteNo(clsSharedVariables.perm_sch.route_nos[0]);
                setCurRouteStat(false);
                setCurTripNo((byte) 0);
            }

            setCurStopNo((byte) 0);
            setCurTripStat(TRIP_UNKNOWN);

            //  make start_time as end time
            clsSharedVariables.perm_sch.sch_startdatetime = clsSharedVariables.perm_sch.sch_enddatetime;
            setPermSchStartTime(clsSharedVariables.perm_sch.sch_endtime);

            c.setTime(clsSharedVariables.perm_sch.sch_enddatetime);
            c.add(Calendar.DATE, 1);

            //make end time as end time + one day
            clsSharedVariables.perm_sch.sch_enddatetime = c.getTime();

            clsSharedVariables.perm_sch.sch_endtime = clsSharedVariables.perm_sch.sch_enddatetime.getTime();

            setCurSchAvailable(true);

            setPermSchAvailable(false);
        } catch (Exception ex) {

        }

        write_cur_trip_info_file();
        write_cur_route_info_file();
        write_perm_schedule_file();
        write_cur_schedule_file();

        setPermSchStatus(true);
        c = null;
        return true;
    }

    public synchronized boolean load_perm_sch_again() {
        try {
            int j = 0;
            setCurTripNo((byte) 0);
            setCurTripStat(TRIP_UNKNOWN);
            write_cur_trip_info_file();
            long cur_sec;
            cur_sec = Calendar.getInstance().getTimeInMillis();
            // current route information
            setCurRouteNo(clsSharedVariables.perm_sch.route_nos[0]);
            setCurRouteStat(false);
            write_cur_route_info_file();
            setSchId(clsSharedVariables.perm_sch.sch_id);
            setCurSchStartDate(clsSharedVariables.perm_sch.sch_startdatetime);
            setCurSchEndDate(clsSharedVariables.perm_sch.sch_enddatetime);
            setCurSchNoTrips(clsSharedVariables.perm_sch.no_trips);
            for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {
                setCurSchRouteNo(j, clsSharedVariables.perm_sch.route_nos[j]);
            }
            for (j = 0; j < clsSharedVariables.perm_sch.no_trips; j++) {

                setCurSchTripStatus(j, clsSharedVariables.perm_sch.trip_status[j]);
            }
            setCurTripNo((byte) 0);

            setCurRouteNo(clsSharedVariables.perm_sch.route_nos[0]);
            setCurRouteStat(false);
            setCurTripStat(TRIP_UNKNOWN);
            setCurSchAvailable(true);
            setPermSchAvailable(false);
            setPermSchStatus(true);
            // current schedule infomation
            setCurSchStartTime(getCurSchEndTime());
            setCurSchEndTime(getCurSchEndTime() + clsDefines.SCHEDULE_TIME_ONE_DAY); // next day

            while (cur_sec > getCurSchStartTime()) {
                setCurSchStartTime(getCurSchEndTime());
                setCurSchEndTime(getCurSchEndTime() + clsDefines.SCHEDULE_TIME_ONE_DAY);// next day
            }

            setCurSchStartDate(new Date(getCurSchStartTime()));
            setCurSchEndDate(new Date(getCurSchEndTime()));

            setCurSchStartTime(getCurSchStartDate().getTime());
            setCurSchEndTime(getCurSchEndDate().getTime());

            setPermSchStartTime(getCurSchStartTime());
            clsSharedVariables.perm_sch.sch_endtime = getCurSchEndTime();
            clsSharedVariables.perm_sch.sch_startdatetime = getCurSchStartDate();
            clsSharedVariables.perm_sch.sch_enddatetime = getCurSchEndDate();

            setPermSchAvailable(false);
            setCurSchAvailable(true);
            write_cur_trip_info_file();
            write_cur_route_info_file();
            write_perm_schedule_file();
            write_cur_schedule_file();
            return true;
        } catch (Exception ex) {

            return false;
        }

    }

    public boolean write_camera_cfg_file() {

        String data;
        File file = new File(config_filepath, "CameraConfig.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);

            pw = new PrintWriter(f);

            data = "SELECT CAMERA :" + String.valueOf(clsSharedVariables.getSelectCamera());
            pw.println(data);

            data = "STREAM TYPE :" + String.valueOf(clsSharedVariables.getStreamtype());
            pw.println(data);

            data = "VIDEO MODE :" + String.valueOf(clsSharedVariables.getVideomode());
            pw.println(data);

            data = "RESOLUTON :" + String.valueOf(clsSharedVariables.getResolution());
            pw.println(data);

            data = "BITRATE TYPE :" + String.valueOf(clsSharedVariables.getBitratetype());
            pw.println(data);

            data = "VIDEO QUALITY :" + String.valueOf(clsSharedVariables.getVideoquality());
            pw.println(data);

            data = "FRAME RATE :" + String.valueOf(clsSharedVariables.getFramerate());
            pw.println(data);

            data = "MAX BITRATE :" + String.valueOf(clsSharedVariables.getbitrate());
            pw.println(data);

            data = "VIDEO ENCODING :" + String.valueOf(clsSharedVariables.getVideoEncode());
            pw.println(data);

            data = "SUBSTREAM TYPE :" + String.valueOf(clsSharedVariables.getSubStreamtype());
            pw.println(data);

            data = "SUBVIDEO MODE :" + String.valueOf(clsSharedVariables.getSubVideomode());
            pw.println(data);

            data = "SUBRESOLUTON :" + String.valueOf(clsSharedVariables.getSubstreamResolution());
            pw.println(data);

            data = "SUBBITRATE TYPE :" + String.valueOf(clsSharedVariables.getSubBitratetype());
            pw.println(data);

            data = "SUBVIDEO QUALITY :" + String.valueOf(clsSharedVariables.getSubVideoquality());
            pw.println(data);

            data = "SUBFRAME RATE :" + String.valueOf(clsSharedVariables.getSubFramerate());
            pw.println(data);

            data = "SUBMAX BITRATE :" + String.valueOf(clsSharedVariables.getSubbitrate());
            pw.println(data);

            data = "SUBVIDEO ENCODING :" + String.valueOf(clsSharedVariables.getSubVideoEncode());
            pw.println(data);

            data = "SELECT CAMERA CAM2 :" + String.valueOf(clsSharedVariables.getSelectCamera2());
            pw.println(data);

            data = "STREAM TYPE CAM2 :" + String.valueOf(clsSharedVariables.getStreamtype2());
            pw.println(data);

            data = "VIDEO MODE CAM2 :" + String.valueOf(clsSharedVariables.getVideomode2());
            pw.println(data);

            data = "RESOLUTON CAM2 :" + String.valueOf(clsSharedVariables.getResolution2());
            pw.println(data);

            data = "BITRATE TYPE CAM2 :" + String.valueOf(clsSharedVariables.getBitratetype2());
            pw.println(data);

            data = "VIDEO QUALITY CAM2 :" + String.valueOf(clsSharedVariables.getVideoquality2());
            pw.println(data);

            data = "FRAME RATE CAM2 :" + String.valueOf(clsSharedVariables.getFramerate2());
            pw.println(data);

            data = "MAX BITRATE CAM2 :" + String.valueOf(clsSharedVariables.getbitrate2());
            pw.println(data);

            data = "VIDEO ENCODING CAM2 :" + String.valueOf(clsSharedVariables.getVideoEncode2());
            pw.println(data);

            data = "SUBSTREAM TYPE CAM2 :" + String.valueOf(clsSharedVariables.getSubStreamtype2());
            pw.println(data);

            data = "SUBVIDEO MODE CAM2 :" + String.valueOf(clsSharedVariables.getSubVideomode2());
            pw.println(data);

            data = "SUBRESOLUTON CAM2 :" + String.valueOf(clsSharedVariables.getSubstreamResolution2());
            pw.println(data);

            data = "SUBBITRATE TYPE CAM2 :" + String.valueOf(clsSharedVariables.getSubBitratetype2());
            pw.println(data);

            data = "SUBVIDEO QUALITY CAM2 :" + String.valueOf(clsSharedVariables.getSubVideoquality2());
            pw.println(data);

            data = "SUBFRAME RATE CAM2 :" + String.valueOf(clsSharedVariables.getSubFramerate2());
            pw.println(data);

            data = "SUBMAX BITRATE CAM2 :" + String.valueOf(clsSharedVariables.getSubbitrate2());
            pw.println(data);

            data = "SUBVIDEO ENCODING CAM2 :" + String.valueOf(clsSharedVariables.getSubVideoEncode2());
            pw.println(data);

            data = "SELECT CAMERA CAM3 :" + String.valueOf(clsSharedVariables.getSelectCamera3());
            pw.println(data);

            data = "STREAM TYPE CAM3 :" + String.valueOf(clsSharedVariables.getStreamtype3());
            pw.println(data);

            data = "VIDEO MODE CAM3 :" + String.valueOf(clsSharedVariables.getVideomode3());
            pw.println(data);

            data = "RESOLUTON CAM3 :" + String.valueOf(clsSharedVariables.getResolution3());
            pw.println(data);

            data = "BITRATE TYPE CAM3 :" + String.valueOf(clsSharedVariables.getBitratetype3());
            pw.println(data);

            data = "VIDEO QUALITY CAM3 :" + String.valueOf(clsSharedVariables.getVideoquality3());
            pw.println(data);

            data = "FRAME RATE CAM3 :" + String.valueOf(clsSharedVariables.getFramerate3());
            pw.println(data);

            data = "MAX BITRATE CAM3 :" + String.valueOf(clsSharedVariables.getbitrate3());
            pw.println(data);

            data = "VIDEO ENCODING CAM3 :" + String.valueOf(clsSharedVariables.getVideoEncode3());
            pw.println(data);

            data = "SUBSTREAM TYPE CAM3 :" + String.valueOf(clsSharedVariables.getSubStreamtype3());
            pw.println(data);

            data = "SUBVIDEO MODE CAM3 :" + String.valueOf(clsSharedVariables.getSubVideomode3());
            pw.println(data);

            data = "SUBRESOLUTON CAM3 :" + String.valueOf(clsSharedVariables.getSubstreamResolution3());
            pw.println(data);

            data = "SUBBITRATE TYPE CAM3 :" + String.valueOf(clsSharedVariables.getSubBitratetype3());
            pw.println(data);

            data = "SUBVIDEO QUALITY CAM3 :" + String.valueOf(clsSharedVariables.getSubVideoquality3());
            pw.println(data);

            data = "SUBFRAME RATE CAM3 :" + String.valueOf(clsSharedVariables.getSubFramerate3());
            pw.println(data);

            data = "SUBMAX BITRATE CAM3 :" + String.valueOf(clsSharedVariables.getSubbitrate3());
            pw.println(data);

            data = "SUBVIDEO ENCODING CAM3 :" + String.valueOf(clsSharedVariables.getSubVideoEncode3());
            pw.println(data);

            data = "SELECT CAMERA CAM4 :" + String.valueOf(clsSharedVariables.getSelectCamera4());
            pw.println(data);

            data = "STREAM TYPE CAM4 :" + String.valueOf(clsSharedVariables.getStreamtype4());
            pw.println(data);

            data = "VIDEO MODE CAM4 :" + String.valueOf(clsSharedVariables.getVideomode4());
            pw.println(data);

            data = "RESOLUTON CAM4 :" + String.valueOf(clsSharedVariables.getResolution4());
            pw.println(data);

            data = "BITRATE TYPE CAM4 :" + String.valueOf(clsSharedVariables.getBitratetype4());
            pw.println(data);

            data = "VIDEO QUALITY CAM4 :" + String.valueOf(clsSharedVariables.getVideoquality4());
            pw.println(data);

            data = "FRAME RATE CAM4 :" + String.valueOf(clsSharedVariables.getFramerate4());
            pw.println(data);

            data = "MAX BITRATE CAM4 :" + String.valueOf(clsSharedVariables.getbitrate4());
            pw.println(data);

            data = "VIDEO ENCODING CAM4 :" + String.valueOf(clsSharedVariables.getVideoEncode4());
            pw.println(data);

            data = "SUBSTREAM TYPE CAM4 :" + String.valueOf(clsSharedVariables.getSubStreamtype4());
            pw.println(data);

            data = "SUBVIDEO MODE CAM4 :" + String.valueOf(clsSharedVariables.getSubVideomode4());
            pw.println(data);

            data = "SUBRESOLUTON CAM4 :" + String.valueOf(clsSharedVariables.getSubstreamResolution4());
            pw.println(data);

            data = "SUBBITRATE TYPE CAM4 :" + String.valueOf(clsSharedVariables.getSubBitratetype4());
            pw.println(data);

            data = "SUBVIDEO QUALITY CAM4 :" + String.valueOf(clsSharedVariables.getSubVideoquality4());
            pw.println(data);

            data = "SUBFRAME RATE CAM4 :" + String.valueOf(clsSharedVariables.getSubFramerate4());
            pw.println(data);

            data = "SUBMAX BITRATE CAM4 :" + String.valueOf(clsSharedVariables.getSubbitrate4());
            pw.println(data);

            data = "SUBVIDEO ENCODING CAM4 :" + String.valueOf(clsSharedVariables.getSubVideoEncode4());
            pw.println(data);

            //image adjustment configuration
            data = "Brihtness Level Cam1 :" + String.valueOf(getBrightnessLevel());
            pw.println(data);
            data = "Contrast Level Cam1 :" + String.valueOf(getContrastLevel());
            pw.println(data);
            data = "Saturation Level Cam1 :" + String.valueOf(getSaturationLevel());
            pw.println(data);

            data = "Brihtness Level Cam2 :" + String.valueOf(getBrightnessLevelCam2());
            pw.println(data);
            data = "Contrast Level Cam2 :" + String.valueOf(getContrastLevelCam2());
            pw.println(data);
            data = "Saturation Level Cam2 :" + String.valueOf(getSaturationLevelCam2());
            pw.println(data);

            data = "Brihtness Level Cam3 :" + String.valueOf(getBrightnessLevelCam3());
            pw.println(data);
            data = "Contrast Level Cam3 :" + String.valueOf(getContrastLevelCam3());
            pw.println(data);
            data = "Saturation Level Cam3 :" + String.valueOf(getSaturationLevelCam3());
            pw.println(data);

            data = "Brihtness Level Cam4 :" + String.valueOf(getBrightnessLevelCam4());
            pw.println(data);
            data = "Contrast Level Cam4 :" + String.valueOf(getContrastLevelCam4());
            pw.println(data);
            data = "Saturation Level Cam4 :" + String.valueOf(getSaturationLevelCam4());
            pw.println(data);

            //camera 1
            data = "Snapshot Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam1());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam1());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam1());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam1());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam1());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam1());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam1());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam1());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam1());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam1());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam1());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam1());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam1());
            pw.println(data);

            data = "Snapshot Cont Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam1());
            pw.println(data);
            data = "Snapshot Cont Stream Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam1());
            pw.println(data);
            data = "Snapshot Cont Interval Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam1());
            pw.println(data);

            data = "Event Based Rec  Enable Cam1 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam1());
            pw.println(data);
            data = "Event Based Rec    Stream Cam1 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam1());
            pw.println(data);
            data = "Event Based Rec    Interval Cam1 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam1());
            pw.println(data);

            //CAMERA 2
            data = "Snapshot Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam2());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam2());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam2());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam2());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam2());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam2());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam2());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam2());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam2());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam2());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam2());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam2());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam2());
            pw.println(data);

            data = "Snapshot Cont Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam2());
            pw.println(data);
            data = "Snapshot Cont Stream Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam2());
            pw.println(data);
            data = "Snapshot Cont Interval Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam2());
            pw.println(data);

            data = "Event Based Rec Enable Cam2 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam2());
            pw.println(data);
            data = "Event Based Rec Stream Cam2 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam2());
            pw.println(data);
            data = "Event Based Rec Interval Cam2 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam2());
            pw.println(data);

            //CAMERA3
            data = "Snapshot Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam3());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam3());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam3());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam3());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam3());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam3());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam3());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam3());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam3());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam3());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam3());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam3());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam3());
            pw.println(data);

            data = "Snapshot Cont Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam3());
            pw.println(data);
            data = "Snapshot Cont Stream Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam3());
            pw.println(data);
            data = "Snapshot Cont Interval Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam3());
            pw.println(data);

            data = "Event Based Rec Enable Cam3 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam3());
            pw.println(data);
            data = "Event Based Rec Stream Cam3 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam3());
            pw.println(data);
            data = "Event Based Rec Interval Cam3 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam3());
            pw.println(data);

            //CAMERA 4
            data = "Snapshot Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam4());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam4());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam4());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam4());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam4());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam4());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam4());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam4());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam4());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam4());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam4());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam4());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam4());
            pw.println(data);

            data = "Snapshot Cont Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam4());
            pw.println(data);
            data = "Snapshot Cont Stream Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam4());
            pw.println(data);
            data = "Snapshot Cont Interval Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam4());
            pw.println(data);

            data = "Event Based Rec  Enable Cam4 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam4());
            pw.println(data);
            data = "Event Based Rec    Stream Cam4 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam4());
            pw.println(data);
            data = "Event Based Rec    Interval Cam4 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam4());
            pw.println(data);

            //CAMERA 5
            data = "Snapshot Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam5());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam5());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam5());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam5());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam5());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam5());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam5());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam5());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam5());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam5());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam5());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam5());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam5());
            pw.println(data);

            data = "Snapshot Cont Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam5());
            pw.println(data);
            data = "Snapshot Cont Stream Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam5());
            pw.println(data);
            data = "Snapshot Cont Interval Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam5());
            pw.println(data);

            data = "Event Based Rec  Enable Cam5 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam5());
            pw.println(data);
            data = "Event Based Rec    Stream Cam5 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam5());
            pw.println(data);
            data = "Event Based Rec    Interval Cam5 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam5());
            pw.println(data);

            //CAMERA 6
            data = "Snapshot Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam6());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam6());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam6());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam6());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam6());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam6());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam6());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam6());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam6());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam6());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam6());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam6());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam6());
            pw.println(data);

            data = "Snapshot Cont Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam6());
            pw.println(data);
            data = "Snapshot Cont Stream Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam6());
            pw.println(data);
            data = "Snapshot Cont Interval Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam6());
            pw.println(data);

            data = "Event Based Rec  Enable Cam6 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam6());
            pw.println(data);
            data = "Event Based Rec    Stream Cam6 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam6());
            pw.println(data);
            data = "Event Based Rec    Interval Cam6 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam6());
            pw.println(data);

            //CAMERA 7
            data = "Snapshot Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam7());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam7());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam7());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam7());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam7());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam7());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam7());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam7());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam7());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam7());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam7());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam7());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam7());
            pw.println(data);

            data = "Snapshot Cont Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam7());
            pw.println(data);
            data = "Snapshot Cont Stream Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam7());
            pw.println(data);
            data = "Snapshot Cont Interval Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam7());
            pw.println(data);

            data = "Event Based Rec  Enable Cam7 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam7());
            pw.println(data);
            data = "Event Based Rec    Stream Cam7 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam7());
            pw.println(data);
            data = "Event Based Rec    Interval Cam7 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam7());
            pw.println(data);

            //CAMERA 8
            data = "Snapshot Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotEnableCam8());
            pw.println(data);

            data = "Snapshot Dig1 Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig1EnableCam8());
            pw.println(data);
            data = "Snapshot Dig1 Stream Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig1StreamCam8());
            pw.println(data);
            data = "Snapshot Dig1 Interval Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig1IntervalCam8());
            pw.println(data);

            data = "Snapshot Dig2 Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig2EnableCam8());
            pw.println(data);
            data = "Snapshot Dig2 Stream Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig2StreamCam8());
            pw.println(data);
            data = "Snapshot Dig2 Interval Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig2IntervalCam8());
            pw.println(data);

            data = "Snapshot Dig3 Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig3EnableCam8());
            pw.println(data);
            data = "Snapshot Dig3 Stream Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig3StreamCam8());
            pw.println(data);
            data = "Snapshot Dig3 Interval Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig3IntervalCam8());
            pw.println(data);

            data = "Snapshot Dig4 Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig4EnableCam8());
            pw.println(data);
            data = "Snapshot Dig4 Stream Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig4StreamCam8());
            pw.println(data);
            data = "Snapshot Dig4 Interval Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig4IntervalCam8());
            pw.println(data);

            data = "Snapshot Cont Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotContEnableCam8());
            pw.println(data);
            data = "Snapshot Cont Stream Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotContStreamCam8());
            pw.println(data);
            data = "Snapshot Cont Interval Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotContIntervalCam8());
            pw.println(data);

            data = "Event Based Rec  Enable Cam8 :" + String.valueOf(clsSharedVariables.getEventBasedRecEnableCam8());
            pw.println(data);
            data = "Event Based Rec    Stream Cam8 :" + String.valueOf(clsSharedVariables.getEventBasedStreamTypeCam8());
            pw.println(data);
            data = "Event Based Rec    Interval Cam8 :" + String.valueOf(clsSharedVariables.getEventBasedIntervalCam8());
            pw.println(data);

            //Cam Encoding conf CAM5
            data = "SELECT CAMERA CAM5 :" + String.valueOf(clsSharedVariables.getSelectCamera5());
            pw.println(data);

            data = "STREAM TYPE CAM5 :" + String.valueOf(clsSharedVariables.getStreamtype5());
            pw.println(data);

            data = "VIDEO MODE CAM5 :" + String.valueOf(clsSharedVariables.getVideomode5());
            pw.println(data);

            data = "RESOLUTON CAM5 :" + String.valueOf(clsSharedVariables.getResolution5());
            pw.println(data);

            data = "BITRATE TYPE CAM5 :" + String.valueOf(clsSharedVariables.getBitratetype5());
            pw.println(data);

            data = "VIDEO QUALITY CAM5 :" + String.valueOf(clsSharedVariables.getVideoquality5());
            pw.println(data);

            data = "FRAME RATE CAM5 :" + String.valueOf(clsSharedVariables.getFramerate5());
            pw.println(data);

            data = "MAX BITRATE CAM5 :" + String.valueOf(clsSharedVariables.getbitrate5());
            pw.println(data);

            data = "VIDEO ENCODING CAM5 :" + String.valueOf(clsSharedVariables.getVideoEncode5());
            pw.println(data);

            data = "SUBSTREAM TYPE CAM5 :" + String.valueOf(clsSharedVariables.getSubStreamtype5());
            pw.println(data);

            data = "SUBVIDEO MODE CAM5 :" + String.valueOf(clsSharedVariables.getSubVideomode5());
            pw.println(data);

            data = "SUBRESOLUTON CAM5 :" + String.valueOf(clsSharedVariables.getSubstreamResolution5());
            pw.println(data);

            data = "SUBBITRATE TYPE CAM5 :" + String.valueOf(clsSharedVariables.getSubBitratetype5());
            pw.println(data);

            data = "SUBVIDEO QUALITY CAM5 :" + String.valueOf(clsSharedVariables.getSubVideoquality5());
            pw.println(data);

            data = "SUBFRAME RATE CAM5 :" + String.valueOf(clsSharedVariables.getSubFramerate5());
            pw.println(data);

            data = "SUBMAX BITRATE CAM5 :" + String.valueOf(clsSharedVariables.getSubbitrate5());
            pw.println(data);

            data = "SUBVIDEO ENCODING CAM5 :" + String.valueOf(clsSharedVariables.getSubVideoEncode5());
            pw.println(data);

            //Cam Encoding conf CAM6
            data = "SELECT CAMERA CAM6 :" + String.valueOf(clsSharedVariables.getSelectCamera6());
            pw.println(data);

            data = "STREAM TYPE CAM6 :" + String.valueOf(clsSharedVariables.getStreamtype6());
            pw.println(data);

            data = "VIDEO MODE CAM6 :" + String.valueOf(clsSharedVariables.getVideomode6());
            pw.println(data);

            data = "RESOLUTON CAM6 :" + String.valueOf(clsSharedVariables.getResolution6());
            pw.println(data);

            data = "BITRATE TYPE CAM6 :" + String.valueOf(clsSharedVariables.getBitratetype6());
            pw.println(data);

            data = "VIDEO QUALITY CAM6 :" + String.valueOf(clsSharedVariables.getVideoquality6());
            pw.println(data);

            data = "FRAME RATE CAM6 :" + String.valueOf(clsSharedVariables.getFramerate6());
            pw.println(data);

            data = "MAX BITRATE CAM6 :" + String.valueOf(clsSharedVariables.getbitrate6());
            pw.println(data);

            data = "VIDEO ENCODING CAM6 :" + String.valueOf(clsSharedVariables.getVideoEncode6());
            pw.println(data);

            data = "SUBSTREAM TYPE CAM6 :" + String.valueOf(clsSharedVariables.getSubStreamtype6());
            pw.println(data);

            data = "SUBVIDEO MODE CAM6 :" + String.valueOf(clsSharedVariables.getSubVideomode6());
            pw.println(data);

            data = "SUBRESOLUTON CAM6 :" + String.valueOf(clsSharedVariables.getSubstreamResolution6());
            pw.println(data);

            data = "SUBBITRATE TYPE CAM6 :" + String.valueOf(clsSharedVariables.getSubBitratetype6());
            pw.println(data);

            data = "SUBVIDEO QUALITY CAM6 :" + String.valueOf(clsSharedVariables.getSubVideoquality6());
            pw.println(data);

            data = "SUBFRAME RATE CAM6 :" + String.valueOf(clsSharedVariables.getSubFramerate6());
            pw.println(data);

            data = "SUBMAX BITRATE CAM6 :" + String.valueOf(clsSharedVariables.getSubbitrate6());
            pw.println(data);

            data = "SUBVIDEO ENCODING CAM6 :" + String.valueOf(clsSharedVariables.getSubVideoEncode6());
            pw.println(data);

            //Cam Encoding conf CAM7
            data = "SELECT CAMERA CAM7 :" + String.valueOf(clsSharedVariables.getSelectCamera7());
            pw.println(data);

            data = "STREAM TYPE CAM7 :" + String.valueOf(clsSharedVariables.getStreamtype7());
            pw.println(data);

            data = "VIDEO MODE CAM7 :" + String.valueOf(clsSharedVariables.getVideomode7());
            pw.println(data);

            data = "RESOLUTON CAM7 :" + String.valueOf(clsSharedVariables.getResolution7());
            pw.println(data);

            data = "BITRATE TYPE CAM7 :" + String.valueOf(clsSharedVariables.getBitratetype7());
            pw.println(data);

            data = "VIDEO QUALITY CAM7 :" + String.valueOf(clsSharedVariables.getVideoquality7());
            pw.println(data);

            data = "FRAME RATE CAM7 :" + String.valueOf(clsSharedVariables.getFramerate7());
            pw.println(data);

            data = "MAX BITRATE CAM7 :" + String.valueOf(clsSharedVariables.getbitrate7());
            pw.println(data);

            data = "VIDEO ENCODING CAM7 :" + String.valueOf(clsSharedVariables.getVideoEncode7());
            pw.println(data);

            data = "SUBSTREAM TYPE CAM7 :" + String.valueOf(clsSharedVariables.getSubStreamtype7());
            pw.println(data);

            data = "SUBVIDEO MODE CAM7 :" + String.valueOf(clsSharedVariables.getSubVideomode7());
            pw.println(data);

            data = "SUBRESOLUTON CAM7 :" + String.valueOf(clsSharedVariables.getSubstreamResolution7());
            pw.println(data);

            data = "SUBBITRATE TYPE CAM7 :" + String.valueOf(clsSharedVariables.getSubBitratetype7());
            pw.println(data);

            data = "SUBVIDEO QUALITY CAM7 :" + String.valueOf(clsSharedVariables.getSubVideoquality7());
            pw.println(data);

            data = "SUBFRAME RATE CAM7 :" + String.valueOf(clsSharedVariables.getSubFramerate7());
            pw.println(data);

            data = "SUBMAX BITRATE CAM7 :" + String.valueOf(clsSharedVariables.getSubbitrate7());
            pw.println(data);

            data = "SUBVIDEO ENCODING CAM7 :" + String.valueOf(clsSharedVariables.getSubVideoEncode7());
            pw.println(data);

            //Cam Encoding conf CAM8
            data = "SELECT CAMERA CAM8 :" + String.valueOf(clsSharedVariables.getSelectCamera8());
            pw.println(data);

            data = "STREAM TYPE CAM8 :" + String.valueOf(clsSharedVariables.getStreamtype8());
            pw.println(data);

            data = "VIDEO MODE CAM8 :" + String.valueOf(clsSharedVariables.getVideomode8());
            pw.println(data);

            data = "RESOLUTON CAM8 :" + String.valueOf(clsSharedVariables.getResolution8());
            pw.println(data);

            data = "BITRATE TYPE CAM8 :" + String.valueOf(clsSharedVariables.getBitratetype8());
            pw.println(data);

            data = "VIDEO QUALITY CAM8 :" + String.valueOf(clsSharedVariables.getVideoquality8());
            pw.println(data);

            data = "FRAME RATE CAM8 :" + String.valueOf(clsSharedVariables.getFramerate8());
            pw.println(data);

            data = "MAX BITRATE CAM8 :" + String.valueOf(clsSharedVariables.getbitrate8());
            pw.println(data);

            data = "VIDEO ENCODING CAM8 :" + String.valueOf(clsSharedVariables.getVideoEncode8());
            pw.println(data);

            data = "SUB STREAM TYPE CAM8 :" + String.valueOf(clsSharedVariables.getSubStreamtype8());
            pw.println(data);

            data = "SUB VIDEO MODE CAM8 :" + String.valueOf(clsSharedVariables.getSubVideomode8());
            pw.println(data);

            data = "SUBRESOLUTON CAM8 :" + String.valueOf(clsSharedVariables.getSubstreamResolution8());
            pw.println(data);

            data = "SUB BITRATE TYPE CAM8 :" + String.valueOf(clsSharedVariables.getSubBitratetype8());
            pw.println(data);

            data = "SUB VIDEO QUALITY CAM8 :" + String.valueOf(clsSharedVariables.getSubVideoquality8());
            pw.println(data);

            data = "SUB FRAME RATE CAM8 :" + String.valueOf(clsSharedVariables.getSubFramerate8());
            pw.println(data);

            data = "SUB MAX BITRATE CAM8 :" + String.valueOf(clsSharedVariables.getSubbitrate8());
            pw.println(data);

            data = "SUB VIDEO ENCODING CAM8 :" + String.valueOf(clsSharedVariables.getSubVideoEncode8());
            pw.println(data);

            //image adjustment configuration
            data = "Brightness Level Cam5 :" + String.valueOf(getBrightnessLevelCam5());
            pw.println(data);
            data = "Contrast Level Cam5 :" + String.valueOf(getContrastLevelCam5());
            pw.println(data);
            data = "Saturation Level Cam5 :" + String.valueOf(getSaturationLevelCam5());
            pw.println(data);

            data = "Brihtness Level Cam6 :" + String.valueOf(getBrightnessLevelCam6());
            pw.println(data);
            data = "Contrast Level Cam6 :" + String.valueOf(getContrastLevelCam6());
            pw.println(data);
            data = "Saturation Level Cam6 :" + String.valueOf(getSaturationLevelCam6());
            pw.println(data);

            data = "Brihtness Level Cam7 :" + String.valueOf(getBrightnessLevelCam7());
            pw.println(data);
            data = "Contrast Level Cam7 :" + String.valueOf(getContrastLevelCam7());
            pw.println(data);
            data = "Saturation Level Cam7 :" + String.valueOf(getSaturationLevelCam7());
            pw.println(data);

            data = "Brihtness Level Cam8 :" + String.valueOf(getBrightnessLevelCam8());
            pw.println(data);
            data = "Contrast Level Cam8 :" + String.valueOf(getContrastLevelCam8());
            pw.println(data);
            data = "Saturation Level Cam8 :" + String.valueOf(getSaturationLevelCam8());
            pw.println(data);

            //Water Marking for Cam1
            data = "Identifier Cam1 :" + String.valueOf(getCameraIdentifer1());
            pw.println(data);
            data = "Device name Cam1 :" + String.valueOf(getCameraName1());
            pw.println(data);
            data = "Speed Cam1 :" + String.valueOf(getCamSpeedEnable1());
            pw.println(data);
            data = "coordinates Cam1 :" + String.valueOf(getCamlatlangEnable1());
            pw.println(data);
            data = "Vechicle Reg Cam1 :" + String.valueOf(getCamVehRegEnable1());
            pw.println(data);

            //Water Marking for Cam2
            data = "Identifier Cam2 :" + String.valueOf(getCameraIdentifer2());
            pw.println(data);
            data = "Device name Cam2 :" + String.valueOf(getCameraName2());
            pw.println(data);
            data = "Speed Cam2 :" + String.valueOf(getCamSpeedEnable2());
            pw.println(data);
            data = "coordinates Cam2 :" + String.valueOf(getCamlatlangEnable2());
            pw.println(data);
            data = "Vechicle Reg Cam2 :" + String.valueOf(getCamVehRegEnable2());
            pw.println(data);

            //Water Marking for Cam3
            data = "Identifier Cam3 :" + String.valueOf(getCameraIdentifer3());
            pw.println(data);
            data = "Device name Cam3 :" + String.valueOf(getCameraName3());
            pw.println(data);
            data = "Speed Cam3 :" + String.valueOf(getCamSpeedEnable3());
            pw.println(data);
            data = "coordinates Cam3 :" + String.valueOf(getCamlatlangEnable3());
            pw.println(data);
            data = "Vechicle Reg Cam3 :" + String.valueOf(getCamVehRegEnable3());
            pw.println(data);

            //Water Marking for Cam4
            data = "Identifier Cam4 :" + String.valueOf(getCameraIdentifer4());
            pw.println(data);
            data = "Device name Cam4 :" + String.valueOf(getCameraName4());
            pw.println(data);
            data = "Speed Cam4 :" + String.valueOf(getCamSpeedEnable4());
            pw.println(data);
            data = "coordinates Cam4 :" + String.valueOf(getCamlatlangEnable4());
            pw.println(data);
            data = "Vechicle Reg Cam4 :" + String.valueOf(getCamVehRegEnable4());
            pw.println(data);

            //Water Marking for Cam5
            data = "Identifier Cam5 :" + String.valueOf(getCameraIdentifer5());
            pw.println(data);
            data = "Device name Cam5 :" + String.valueOf(getCameraName5());
            pw.println(data);
            data = "Speed Cam5 :" + String.valueOf(getCamSpeedEnable5());
            pw.println(data);
            data = "coordinates Cam5 :" + String.valueOf(getCamlatlangEnable5());
            pw.println(data);
            data = "Vechicle Reg Cam5 :" + String.valueOf(getCamVehRegEnable5());
            pw.println(data);

            //Water Marking for Cam6
            data = "Identifier Cam6 :" + String.valueOf(getCameraIdentifer6());
            pw.println(data);
            data = "Device name Cam6 :" + String.valueOf(getCameraName6());
            pw.println(data);
            data = "Speed Cam6 :" + String.valueOf(getCamSpeedEnable6());
            pw.println(data);
            data = "coordinates Cam6 :" + String.valueOf(getCamlatlangEnable6());
            pw.println(data);
            data = "Vechicle Reg Cam6 :" + String.valueOf(getCamVehRegEnable6());
            pw.println(data);

            //Water Marking for Cam7
            data = "Identifier Cam7 :" + String.valueOf(getCameraIdentifer7());
            pw.println(data);
            data = "Device name Cam7 :" + String.valueOf(getCameraName7());
            pw.println(data);
            data = "Speed Cam7 :" + String.valueOf(getCamSpeedEnable7());
            pw.println(data);
            data = "coordinates Cam7 :" + String.valueOf(getCamlatlangEnable7());
            pw.println(data);
            data = "Vechicle Reg Cam7 :" + String.valueOf(getCamVehRegEnable7());
            pw.println(data);

            //Water Marking for Cam8
            data = "Identifier Cam8 :" + String.valueOf(getCameraIdentifer8());
            pw.println(data);
            data = "Device name Cam8 :" + String.valueOf(getCameraName8());
            pw.println(data);
            data = "Speed Cam8 :" + String.valueOf(getCamSpeedEnable8());
            pw.println(data);
            data = "coordinates Cam8 :" + String.valueOf(getCamlatlangEnable8());
            pw.println(data);
            data = "Vechicle Reg Cam8 :" + String.valueOf(getCamVehRegEnable8());
            pw.println(data);

            data = "Rec Sunday :" + String.valueOf(clsSharedVariables.getSchRecSunday());
            pw.println(data);

            data = "Rec Monday :" + String.valueOf(clsSharedVariables.getSchRecMonday());
            pw.println(data);

            data = "Rec Tuesday :" + String.valueOf(clsSharedVariables.getSchRecTuesday());
            pw.println(data);

            data = "Rec Wednesday :" + String.valueOf(clsSharedVariables.getSchRecWednesday());
            pw.println(data);

            data = "Rec Thursday :" + String.valueOf(clsSharedVariables.getSchRecThursday());
            pw.println(data);

            data = "Rec Friday :" + String.valueOf(clsSharedVariables.getSchRecFriday());
            pw.println(data);

            data = "Rec Saturday :" + String.valueOf(clsSharedVariables.getSchRecSaturday());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam1());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam2());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam3());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam4());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam5());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam6());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam7());
            pw.println(data);

            data = "Snapshot Cont ftp Snap Upload Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotContFtpUploadEnableCam8());
            pw.println(data);

            //cam1
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam1());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam1());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam1());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam1 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam1());
            pw.println(data);

            //Cam2
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam2());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam2());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam2());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam2 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam2());
            pw.println(data);

            //Cam3
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam3());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam3());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam3());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam3 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam3());
            pw.println(data);

            //Cam4
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam4());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam4());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam4());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam4 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam4());
            pw.println(data);

            //Cam5
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam5());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam5());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam5());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam5 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam5());
            pw.println(data);

            //Cam6
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam6());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam6());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam6());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam6 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam6());
            pw.println(data);

            //Cam7
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam7());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam7());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam7());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam7 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam7());
            pw.println(data);

            //Cam8
            data = "Snapshot Dig1 ftp Snap Upload Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam8());
            pw.println(data);
            data = "Snapshot Dig2 ftp Snap Upload Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam8());
            pw.println(data);
            data = "Snapshot Dig3 ftp Snap Upload Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam8());
            pw.println(data);
            data = "Snapshot Dig4 ftp Snap Upload Enable Cam8 :" + String.valueOf(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam8());
            pw.println(data);

            data = "VIDEO BITRATE CAM1 :" + String.valueOf(clsSharedVariables.getbitrate());
            pw.println(data);
            data = "VIDEO BITRATE CAM2 :" + String.valueOf(clsSharedVariables.getbitrate2());
            pw.println(data);
            data = "VIDEO BITRATE CAM3 :" + String.valueOf(clsSharedVariables.getbitrate3());
            pw.println(data);
            data = "VIDEO BITRATE CAM4 :" + String.valueOf(clsSharedVariables.getbitrate4());
            pw.println(data);
            data = "VIDEO BITRATE CAM5 :" + String.valueOf(clsSharedVariables.getbitrate5());
            pw.println(data);
            data = "VIDEO BITRATE CAM6 :" + String.valueOf(clsSharedVariables.getbitrate6());
            pw.println(data);
            data = "VIDEO BITRATE CAM7 :" + String.valueOf(clsSharedVariables.getbitrate7());
            pw.println(data);
            data = "VIDEO BITRATE CAM8 :" + String.valueOf(clsSharedVariables.getbitrate8());
            pw.println(data);

            data = "VIDEO SUB BITRATE CAM1 :" + String.valueOf(clsSharedVariables.getSubbitrate());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM2 :" + String.valueOf(clsSharedVariables.getSubbitrate2());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM3 :" + String.valueOf(clsSharedVariables.getSubbitrate3());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM4 :" + String.valueOf(clsSharedVariables.getSubbitrate4());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM5 :" + String.valueOf(clsSharedVariables.getSubbitrate5());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM6 :" + String.valueOf(clsSharedVariables.getSubbitrate6());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM7 :" + String.valueOf(clsSharedVariables.getSubbitrate7());
            pw.println(data);
            data = "VIDEO SUB BITRATE CAM8 :" + String.valueOf(clsSharedVariables.getSubbitrate8());
            pw.println(data);

            data = "VIDEO FRAME INTERVAL  CAM1 :" + String.valueOf(clsSharedVariables.getFrameInterval());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM2 :" + String.valueOf(clsSharedVariables.getFrameInterval2());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM3 :" + String.valueOf(clsSharedVariables.getFrameInterval3());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM4 :" + String.valueOf(clsSharedVariables.getFrameInterval4());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM5 :" + String.valueOf(clsSharedVariables.getFrameInterval5());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM6 :" + String.valueOf(clsSharedVariables.getFrameInterval6());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM7 :" + String.valueOf(clsSharedVariables.getFrameInterval7());
            pw.println(data);
            data = "VIDEO FRAME INTERVAL CAM8 :" + String.valueOf(clsSharedVariables.getFrameInterval8());
            pw.println(data);

            data = "VIDEO SUB FRAME INTERVAL  CAM1 :" + String.valueOf(clsSharedVariables.getSubFrameInterval());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM2 :" + String.valueOf(clsSharedVariables.getSubFrameInterval2());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM3 :" + String.valueOf(clsSharedVariables.getSubFrameInterval3());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM4 :" + String.valueOf(clsSharedVariables.getSubFrameInterval4());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM5 :" + String.valueOf(clsSharedVariables.getSubFrameInterval5());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM6 :" + String.valueOf(clsSharedVariables.getSubFrameInterval6());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM7 :" + String.valueOf(clsSharedVariables.getSubFrameInterval7());
            pw.println(data);
            data = "VIDEO SUB FRAME INTERVAL CAM8 :" + String.valueOf(clsSharedVariables.getSubFrameInterval8());
            pw.println(data);
            data = "VIDEO Rec Expiry Days :" + String.valueOf(clsSharedVariables.getRecExpiryDays());
            pw.println(data);

            //Cam1 for Motion Detection
            data = "Motion Detection Enable Cam1 :" + String.valueOf(clsSharedVariables.getMotionEnableCam1());
            pw.println(data);
            data = "Motion Detection X1 Cam1 :" + String.valueOf(clsSharedVariables.getMotionX1Cam1());
            pw.println(data);
            data = "Motion Detection Y1 Cam1 :" + String.valueOf(clsSharedVariables.getMotionY1Cam1());
            pw.println(data);
            data = "Motion Detection X2 Cam1 :" + String.valueOf(clsSharedVariables.getMotionX2Cam1());
            pw.println(data);
            data = "Motion Detection Y2 Cam1 :" + String.valueOf(clsSharedVariables.getMotionY2Cam1());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam1 :" + String.valueOf(clsSharedVariables.getSensitivityCam1());
            pw.println(data);
            data = "Motion Detection Threshold Cam1 :" + String.valueOf(clsSharedVariables.getThresholdCam1());
            pw.println(data);
            data = "Motion Detection Record Time Cam1 :" + String.valueOf(clsSharedVariables.getRecordTimeCam1());
            pw.println(data);

            //Cam2 for Motion Detection
            data = "Motion Detection Enable Cam2 :" + String.valueOf(clsSharedVariables.getMotionEnableCam2());
            pw.println(data);
            data = "Motion Detection X1 Cam2 :" + String.valueOf(clsSharedVariables.getMotionX1Cam2());
            pw.println(data);
            data = "Motion Detection Y1 Cam2 :" + String.valueOf(clsSharedVariables.getMotionY1Cam2());
            pw.println(data);
            data = "Motion Detection X2 Cam2 :" + String.valueOf(clsSharedVariables.getMotionX2Cam2());
            pw.println(data);
            data = "Motion Detection Y2 Cam2 :" + String.valueOf(clsSharedVariables.getMotionY2Cam2());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam2 :" + String.valueOf(clsSharedVariables.getSensitivityCam2());
            pw.println(data);
            data = "Motion Detection Threshold Cam2 :" + String.valueOf(clsSharedVariables.getThresholdCam2());
            pw.println(data);
            data = "Motion Detection Record Time Cam2 :" + String.valueOf(clsSharedVariables.getRecordTimeCam2());
            pw.println(data);

            //Cam1 for Motion Detection
            data = "Motion Detection Enable Cam3 :" + String.valueOf(clsSharedVariables.getMotionEnableCam3());
            pw.println(data);
            data = "Motion Detection X1 Cam3 :" + String.valueOf(clsSharedVariables.getMotionX1Cam3());
            pw.println(data);
            data = "Motion Detection Y1 Cam3 :" + String.valueOf(clsSharedVariables.getMotionY1Cam3());
            pw.println(data);
            data = "Motion Detection X2 Cam3 :" + String.valueOf(clsSharedVariables.getMotionX2Cam3());
            pw.println(data);
            data = "Motion Detection Y2 Cam3 :" + String.valueOf(clsSharedVariables.getMotionY2Cam3());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam3 :" + String.valueOf(clsSharedVariables.getSensitivityCam3());
            pw.println(data);
            data = "Motion Detection Threshold Cam3 :" + String.valueOf(clsSharedVariables.getThresholdCam3());
            pw.println(data);
            data = "Motion Detection Record Time Cam3 :" + String.valueOf(clsSharedVariables.getRecordTimeCam3());
            pw.println(data);

            //Cam4 for Motion Detection
            data = "Motion Detection Enable Cam4 :" + String.valueOf(clsSharedVariables.getMotionEnableCam4());
            pw.println(data);
            data = "Motion Detection X1 Cam4 :" + String.valueOf(clsSharedVariables.getMotionX1Cam4());
            pw.println(data);
            data = "Motion Detection Y1 Cam4 :" + String.valueOf(clsSharedVariables.getMotionY1Cam4());
            pw.println(data);
            data = "Motion Detection X2 Cam4 :" + String.valueOf(clsSharedVariables.getMotionX2Cam4());
            pw.println(data);
            data = "Motion Detection Y2 Cam4 :" + String.valueOf(clsSharedVariables.getMotionY2Cam4());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam4 :" + String.valueOf(clsSharedVariables.getSensitivityCam4());
            pw.println(data);
            data = "Motion Detection Threshold Cam4 :" + String.valueOf(clsSharedVariables.getThresholdCam4());
            pw.println(data);
            data = "Motion Detection Record Time Cam4 :" + String.valueOf(clsSharedVariables.getRecordTimeCam4());
            pw.println(data);

            //Cam1 for Motion Detection
            data = "Motion Detection Enable Cam5 :" + String.valueOf(clsSharedVariables.getMotionEnableCam5());
            pw.println(data);
            data = "Motion Detection X1 Cam5 :" + String.valueOf(clsSharedVariables.getMotionX1Cam5());
            pw.println(data);
            data = "Motion Detection Y1 Cam5 :" + String.valueOf(clsSharedVariables.getMotionY1Cam5());
            pw.println(data);
            data = "Motion Detection X2 Cam5 :" + String.valueOf(clsSharedVariables.getMotionX2Cam5());
            pw.println(data);
            data = "Motion Detection Y2 Cam5 :" + String.valueOf(clsSharedVariables.getMotionY2Cam5());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam5 :" + String.valueOf(clsSharedVariables.getSensitivityCam5());
            pw.println(data);
            data = "Motion Detection Threshold Cam5 :" + String.valueOf(clsSharedVariables.getThresholdCam5());
            pw.println(data);
            data = "Motion Detection Record Time Cam5 :" + String.valueOf(clsSharedVariables.getRecordTimeCam5());
            pw.println(data);

            //Cam1 for Motion Detection
            data = "Motion Detection Enable Cam6 :" + String.valueOf(clsSharedVariables.getMotionEnableCam6());
            pw.println(data);
            data = "Motion Detection X1 Cam6 :" + String.valueOf(clsSharedVariables.getMotionX1Cam6());
            pw.println(data);
            data = "Motion Detection Y1 Cam6 :" + String.valueOf(clsSharedVariables.getMotionY1Cam6());
            pw.println(data);
            data = "Motion Detection X2 Cam6 :" + String.valueOf(clsSharedVariables.getMotionX2Cam6());
            pw.println(data);
            data = "Motion Detection Y2 Cam6 :" + String.valueOf(clsSharedVariables.getMotionY2Cam6());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam6 :" + String.valueOf(clsSharedVariables.getSensitivityCam6());
            pw.println(data);
            data = "Motion Detection Threshold Cam6 :" + String.valueOf(clsSharedVariables.getThresholdCam6());
            pw.println(data);
            data = "Motion Detection Record Time Cam6 :" + String.valueOf(clsSharedVariables.getRecordTimeCam6());
            pw.println(data);

            //Cam1 for Motion Detection
            data = "Motion Detection Enable Cam7 :" + String.valueOf(clsSharedVariables.getMotionEnableCam7());
            pw.println(data);
            data = "Motion Detection X1 Cam7 :" + String.valueOf(clsSharedVariables.getMotionX1Cam7());
            pw.println(data);
            data = "Motion Detection Y1 Cam7 :" + String.valueOf(clsSharedVariables.getMotionY1Cam7());
            pw.println(data);
            data = "Motion Detection X2 Cam7 :" + String.valueOf(clsSharedVariables.getMotionX2Cam7());
            pw.println(data);
            data = "Motion Detection Y2 Cam7 :" + String.valueOf(clsSharedVariables.getMotionY2Cam7());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam7 :" + String.valueOf(clsSharedVariables.getSensitivityCam7());
            pw.println(data);
            data = "Motion Detection Threshold Cam7 :" + String.valueOf(clsSharedVariables.getThresholdCam7());
            pw.println(data);
            data = "Motion Detection Record Time Cam7 :" + String.valueOf(clsSharedVariables.getRecordTimeCam7());
            pw.println(data);

            //Cam1 for Motion Detection
            data = "Motion Detection Enable Cam8 :" + String.valueOf(clsSharedVariables.getMotionEnableCam8());
            pw.println(data);
            data = "Motion Detection X1 Cam8 :" + String.valueOf(clsSharedVariables.getMotionX1Cam8());
            pw.println(data);
            data = "Motion Detection Y1 Cam8 :" + String.valueOf(clsSharedVariables.getMotionY1Cam8());
            pw.println(data);
            data = "Motion Detection X2 Cam8 :" + String.valueOf(clsSharedVariables.getMotionX2Cam8());
            pw.println(data);
            data = "Motion Detection Y2 Cam8 :" + String.valueOf(clsSharedVariables.getMotionY2Cam8());
            pw.println(data);
            data = "Motion Detection Sensitivity Cam8 :" + String.valueOf(clsSharedVariables.getSensitivityCam8());
            pw.println(data);
            data = "Motion Detection Threshold Cam8 :" + String.valueOf(clsSharedVariables.getThresholdCam8());
            pw.println(data);
            data = "Motion Detection Record Time Cam8 :" + String.valueOf(clsSharedVariables.getRecordTimeCam8());
            pw.println(data);

            //video expiry
            data = "Event expiry:" + String.valueOf(clsSharedVariables.getRecExpiryDaysEventBased());
            pw.println(data);
            data = "Snapshot expiry :" + String.valueOf(clsSharedVariables.getSnapDelNoDays());
            pw.println(data);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            pw = null;
            f = null;
            data = null;
            file = null;
        }
        return true;
    }

    public synchronized void read_camera_cfg_file() {
        short i = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        //Get the text file
        File file = new File(config_filepath, "CameraConfig.txt");
        if (file.exists()) {

            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    try {
                        switch (i) {
                            case 0:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera((split_str[1].trim()));
                                }
                                break;
                            case 1:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype((split_str[1].trim()));
                                }
                                break;
                            case 2:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode((split_str[1].trim()));
                                }
                                break;
                            case 3:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 4:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype((split_str[1].trim()));
                                }
                                break;
                            case 5:
                                split_str = line.split(":");
                                if (split_str.length > 1) {

                                    clsSharedVariables.setVideoquality(Byte.parseByte((split_str[1].trim())));

                                }
                                break;
                            case 6:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 7:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 8:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode((split_str[1].trim()));
                                }
                                break;
                            case 9:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype((split_str[1].trim()));
                                }
                                break;
                            case 10:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode((split_str[1].trim()));
                                }
                                break;
                            case 11:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 12:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype((split_str[1].trim()));
                                }
                                break;
                            case 13:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 14:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 15:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 16:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode((split_str[1].trim()));
                                }
                                break;
                            case 17:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera2((split_str[1].trim()));
                                }
                                break;
                            case 18:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype2((split_str[1].trim()));
                                }
                                break;
                            case 19:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode2((split_str[1].trim()));
                                }
                                break;
                            case 20:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 21:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype2((split_str[1].trim()));
                                }
                                break;
                            case 22:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 23:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 24:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 25:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode2((split_str[1].trim()));
                                }
                                break;
                            case 26:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype2((split_str[1].trim()));
                                }
                                break;
                            case 27:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode2((split_str[1].trim()));
                                }
                                break;
                            case 28:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 29:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype2((split_str[1].trim()));
                                }
                                break;
                            case 30:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 31:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 32:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 33:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode2((split_str[1].trim()));
                                }
                                break;
                            case 34:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera3((split_str[1].trim()));
                                }
                                break;
                            case 35:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype3((split_str[1].trim()));
                                }
                                break;
                            case 36:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode3((split_str[1].trim()));
                                }
                                break;
                            case 37:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 38:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype3((split_str[1].trim()));
                                }
                                break;
                            case 39:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 40:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 41:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 42:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode3((split_str[1].trim()));
                                }
                                break;
                            case 43:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype3((split_str[1].trim()));
                                }
                                break;
                            case 44:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode3((split_str[1].trim()));
                                }
                                break;
                            case 45:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 46:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype3((split_str[1].trim()));
                                }
                                break;
                            case 47:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 48:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 49:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 50:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode3((split_str[1].trim()));
                                }
                                break;
                            case 51:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera4((split_str[1].trim()));
                                }
                                break;
                            case 52:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype4((split_str[1].trim()));
                                }
                                break;
                            case 53:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode4((split_str[1].trim()));
                                }
                                break;
                            case 54:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 55:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype4((split_str[1].trim()));
                                }
                                break;
                            case 56:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 57:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 58:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 59:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode4((split_str[1].trim()));
                                }
                                break;
                            case 60:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype4((split_str[1].trim()));
                                }
                                break;
                            case 61:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode4((split_str[1].trim()));
                                }
                                break;
                            case 62:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 63:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype4((split_str[1].trim()));
                                }
                                break;
                            case 64:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 65:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 66:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 67:
                                split_str = line.split(":");
                                if (split_str.length > 1) {

                                    clsSharedVariables.setSubVideoEncode4((split_str[1].trim()));
                                }
                                break;
                            case 68:
                                split_str = line.split(":");
                                if (split_str.length > 1) {

                                    setBrightnessLevel(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 69:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevel(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 70:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevel(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 71:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 72:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 73:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 74:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 75:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 76:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 77:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 78:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 79:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //camera 1
                            case 80:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 81:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 82:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 83:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 84:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 85:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 86:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 87:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 88:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 89:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 90:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 91:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 92:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 93:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 94:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 95:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 96:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 97:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 98:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 99:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 100:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 101:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 102:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 103:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 104:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 105:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 106:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 107:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 108:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 109:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 110:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 111:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 112:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 113:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 114:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 115:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 116:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 117:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            //camera 3
                            case 118:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 119:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 120:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 121:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 122:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 123:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 124:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 125:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 126:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 127:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 128:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 129:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 130:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 131:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 132:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 133:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 134:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 135:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 136:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam3(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            //camera 1
                            case 137:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 138:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 139:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 140:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 141:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 142:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 143:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 144:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 145:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 146:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 147:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 148:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 149:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 150:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 151:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 152:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 153:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 154:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 155:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam4(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            //Cam 5
                            case 156:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 157:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 158:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 159:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 160:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 161:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 162:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 163:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 164:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 165:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 166:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 167:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 168:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 169:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 170:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 171:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 172:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 173:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 174:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            //Cam 6
                            case 175:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 176:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 177:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 178:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 179:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 180:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 181:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 182:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 183:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 184:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 185:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 186:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 187:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 188:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 189:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 190:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 191:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 192:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 193:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            //Cam 7
                            case 194:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 195:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 196:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 197:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 198:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 199:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 200:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 201:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 202:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 203:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 204:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 205:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 206:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 207:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 208:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 209:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 210:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 211:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 212:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            //Cam 8
                            case 213:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotEnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 214:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1EnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 215:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1StreamCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 216:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1IntervalCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 217:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2EnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 218:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2StreamCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 219:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2IntervalCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            case 220:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3EnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 221:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3StreamCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 222:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3IntervalCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 223:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4EnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 224:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4StreamCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 225:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4IntervalCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 226:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContEnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 227:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContStreamCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 228:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContIntervalCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 229:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedRecEnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 230:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedStreamTypeCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 231:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventBasedIntervalCam8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;

                            //Cam 5 for Encoding
                            case 232:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera5((split_str[1].trim()));
                                }
                                break;
                            case 233:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype5((split_str[1].trim()));
                                }
                                break;
                            case 234:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode5((split_str[1].trim()));
                                }
                                break;
                            case 235:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 236:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype5((split_str[1].trim()));
                                }
                                break;
                            case 237:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 238:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 239:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 240:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode5((split_str[1].trim()));
                                }
                                break;
                            case 241:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype5((split_str[1].trim()));
                                }
                                break;
                            case 242:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode5((split_str[1].trim()));
                                }
                                break;
                            case 243:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 244:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype5((split_str[1].trim()));
                                }
                                break;
                            case 245:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 246:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 247:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate5(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 248:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode5((split_str[1].trim()));
                                }
                                break;

                            //Cam6
                            case 249:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera6((split_str[1].trim()));
                                }
                                break;
                            case 250:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype6((split_str[1].trim()));
                                }
                                break;
                            case 251:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode6((split_str[1].trim()));
                                }
                                break;
                            case 252:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 253:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype6((split_str[1].trim()));
                                }
                                break;
                            case 254:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 255:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 256:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 257:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode6((split_str[1].trim()));
                                }
                                break;
                            case 258:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype6((split_str[1].trim()));
                                }
                                break;
                            case 259:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode6((split_str[1].trim()));
                                }
                                break;
                            case 260:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 261:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype6((split_str[1].trim()));
                                }
                                break;
                            case 262:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 263:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 264:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate6(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 265:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode6((split_str[1].trim()));
                                }
                                break;

                            //Cam7
                            case 266:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera7((split_str[1].trim()));
                                }
                                break;
                            case 267:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype7((split_str[1].trim()));
                                }
                                break;
                            case 268:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode7((split_str[1].trim()));
                                }
                                break;
                            case 269:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 270:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype7((split_str[1].trim()));
                                }
                                break;
                            case 271:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 272:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 273:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 274:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode7((split_str[1].trim()));
                                }
                                break;
                            case 275:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype7((split_str[1].trim()));
                                }
                                break;
                            case 276:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode7((split_str[1].trim()));
                                }
                                break;
                            case 277:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 278:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype7((split_str[1].trim()));
                                }
                                break;
                            case 279:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 280:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 281:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate7(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 282:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode7((split_str[1].trim()));
                                }
                                break;

                            // Cam 8
                            case 283:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSelectCamera8((split_str[1].trim()));
                                }
                                break;
                            case 284:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStreamtype8((split_str[1].trim()));
                                }
                                break;
                            case 285:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideomode8((split_str[1].trim()));
                                }
                                break;
                            case 286:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setResolution8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 287:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setBitratetype8((split_str[1].trim()));
                                }
                                break;
                            case 288:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoquality8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 289:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFramerate8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 290:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMaxbitrate8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 291:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoEncode8((split_str[1].trim()));
                                }
                                break;
                            case 292:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubStreamtype8((split_str[1].trim()));
                                }
                                break;
                            case 293:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideomode8((split_str[1].trim()));
                                }
                                break;
                            case 294:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubstreamResolution8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 295:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubBitratetype8((split_str[1].trim()));
                                }
                                break;
                            case 296:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoquality8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 297:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFramerate8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 298:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubMaxbitrate8(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 299:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubVideoEncode8((split_str[1].trim()));
                                }
                                break;
                            //Cam 5
                            case 300:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 301:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 302:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Cam 6
                            case 303:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 304:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 305:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Cam 7
                            case 306:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 307:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 308:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Cam 8
                            case 309:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setBrightnessLevelCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 310:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setContrastLevelCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 311:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSaturationLevelCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 312:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 313:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName1((split_str[1].trim()));
                                }
                                break;
                            case 314:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 315:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 316:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            //cam2
                            case 317:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 318:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName2((split_str[1].trim()));
                                }
                                break;
                            case 319:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 320:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 321:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            //cam3
                            case 322:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 323:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName3((split_str[1].trim()));
                                }
                                break;
                            case 324:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 325:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 326:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            //cam4
                            case 327:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 328:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName4((split_str[1].trim()));
                                }
                                break;
                            case 329:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 330:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 331:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            //cam5
                            case 332:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 333:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName5((split_str[1].trim()));
                                }
                                break;
                            case 334:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 335:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 336:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            //cam6
                            case 337:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 338:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName6((split_str[1].trim()));
                                }
                                break;
                            case 339:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 340:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 341:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            //Cma7
                            case 342:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 343:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName7((split_str[1].trim()));
                                }
                                break;
                            case 344:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 345:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 346:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            //Cma8
                            case 347:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraIdentifer8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 348:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCameraName8((split_str[1].trim()));
                                }
                                break;
                            case 349:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamSpeedEnable8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 350:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamlatlangEnable8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 351:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamVehRegEnable8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 352:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecSunday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 353:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecMonday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;

                            case 354:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecTuesday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 355:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecWednesday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 356:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecThursday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 357:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecFriday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 358:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSchRecSaturday(Byte.parseByte(split_str[1].trim()));
                                }
                                break;

                            case 359:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam1(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 360:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam2(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 361:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam3(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 362:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam4(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 363:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam5(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 364:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam6(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 365:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam7(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 366:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotContFtpUploadEnableCam8(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 367:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam1(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 368:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam1(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 369:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam1(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 370:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam1(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 371:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam2(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 372:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam2(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 373:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam2(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 374:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam2(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 375:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam3(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 376:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam3(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 377:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam3(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 378:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam3(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 379:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam4(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 380:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam4(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 381:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam4(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 382:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam4(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 383:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam5(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 384:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam5(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 385:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam5(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 386:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam5(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 387:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam6(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 388:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam6(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 389:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam6(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 390:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam6(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 391:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam7(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 392:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam7(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 393:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam7(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 394:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam7(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 395:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam8(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 396:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam8(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 397:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam8(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 398:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam8(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 399:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 400:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate2(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 401:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate3(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 402:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate4(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 403:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate5(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 404:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate6(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 405:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate7(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 406:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setbitrate8(Integer.parseInt(split_str[1].trim()));     // bitrate cam8
                                }
                                break;
                            case 407:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 408:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate2(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 409:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate3(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 410:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate4(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 411:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate5(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 412:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate6(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 413:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate7(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 414:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubbitrate8(Integer.parseInt(split_str[1].trim()));     // bitrate cam8
                                }
                                break;
                            case 415:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 416:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval2(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 417:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval3(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 418:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval4(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 419:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval5(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 420:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval6(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 421:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval7(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 422:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setFrameInterval8(Integer.parseInt(split_str[1].trim()));     // bitrate cam8
                                }
                                break;
                            case 423:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 424:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval2(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 425:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval3(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 426:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval4(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 427:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval5(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 428:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval6(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 429:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval7(Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 430:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSubFrameInterval8(Integer.parseInt(split_str[1].trim()));     // bitrate cam8
                                }
                                break;
                            case 431:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecExpiryDays(Integer.parseInt(split_str[1].trim()));     // bitrate cam8
                                }
                                break;
                            //Motion Cam1
                            case 432:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 433:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 434:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 435:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 436:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 437:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 438:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 439:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam1(Byte.parseByte(split_str[1].trim()));
                                }
                                break;

                            //Motion Cam2
                            case 440:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 441:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 442:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 443:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 444:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 445:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 446:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 447:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam2(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Motion Cam3
                            case 448:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 449:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 450:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 451:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 452:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 453:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 454:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 455:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam3(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Motion Cam4
                            case 456:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 457:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 458:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 459:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 460:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 461:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 462:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 463:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam4(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Motion Cam5
                            case 464:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 465:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 466:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 467:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 468:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 469:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 470:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 471:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam5(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Motion Cam6
                            case 472:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam6(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 473:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 474:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 475:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 476:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 477:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 478:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 479:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam6(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Motion Cam7
                            case 480:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam7(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 481:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 482:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 483:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 484:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 485:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 486:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 487:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam7(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            //Motion Cam8
                            case 488:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionEnableCam8(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 489:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX1Cam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 490:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY1Cam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 491:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionX2Cam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 492:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMotionY2Cam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 493:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSensitivityCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 494:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setThresholdCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 495:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordTimeCam8(Byte.parseByte(split_str[1].trim()));
                                }
                                break;

                            case 496:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecExpiryDaysEventBased(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 497:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSnapDelNoDays(Byte.parseByte(split_str[1].trim()));
                                }
                                break;

                        }
                    } catch (Exception ex) {

                    }
                    i++;
                }

            } catch (FileNotFoundException e) {
                write_log_low_memory_data("cameraconfig.txt File " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } catch (IOException e) {
                write_log_low_memory_data("cameraconfig.txt File " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } catch (Exception e) {
                write_log_low_memory_data("cameraconfig.txt File " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } finally {
                try {
                    if (br != null) {
                        br.close();

                    }
                } catch (IOException e) {

                }
                split_str = null;
                file = null;
                br = null;
            }
        }
    }

    public boolean write_cfg_data_file() {

        String data;
        File file = new File(config_filepath, "ConfigParams.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);

            pw = new PrintWriter(f);

            // SERVER CONFIGURATION
            pw.println("SERVER CONFIGURATION");
            data = "OBUID :" + getOBUID();
            pw.println(data);
            data = "DEVICE SHUT TIME :" + getMainDeviceShutTime();
            pw.println(data);
            data = "SLEEP MODE/SHUTDOWN MODE :" + getSleepModeState();
            pw.println(data);
            data = "COMM MODE GPRS/CPRS_SMS :" + clsSharedVariables.getCommMode();
            pw.println(data);

            //DEVICE CONFIGURATION
            pw.println("DEVICE CONFIGURATION");
            data = "PRIMARY IP ADDR :" + getIpAddr1();
            pw.println(data);
            data = "PRIMARY PORT NO :" + String.valueOf(getPortNo1());
            pw.println(data);
            data = "SECONDARY IP ADDR :" + getIpAddr2();
            pw.println(data);
            data = "SECONDARY PORT NO :" + String.valueOf(getPortNo2());
            pw.println(data);
            data = "IP INTERFACE :" + String.valueOf(getIpInterface());
            pw.println(data);

            data = "APN :" + clsSharedVariables.getApn();
            pw.println(data);
            data = "URL :" + server_url;
            pw.println(data);
            data = "DNS ENABLE/DISABLE :" + chk_server_dns;
            pw.println(data);
            data = "DNS :" + server_dns;
            pw.println(data);
            data = "ROUTE DEVIATION DIS :" + getRouteDevDistance();
            pw.println(data);
            data = "GPRS NORMAL :" + gps_update_rate_normal;
            pw.println(data);
            data = "VIDEO UPDATE RATE :" + video_update_rate;
            pw.println(data);
            data = "GPRS SLEEP :" + get_gprs_sleep_val();
            pw.println(data);
            data = "NON STOP DURATION :" + getNonStopTime();
            pw.println(data);
            data = "FTP FILE PATH :" + ftp_ipaddress;
            pw.println(data);
            data = "FTP USER NAME :" + ftpusername;
            pw.println(data);
            data = "FTP PASSWORD :" + ftpPassword;
            pw.println(data);
           
            //DISPLAY BOARD CONFIGURATION
            pw.println("DISPLAY BOARD CONFIGURATION");
            data = "DISPLAY BOARD TYPE :" + getDisBrdName();
            pw.println(data);
            data = "DISPLAY BOARD BAUD RATE :" + getDisBrdBaudRate();
            pw.println(data);
            data = "DISPLAY BOARD FRONT ENABLE :" + getFrontEnable();
            pw.println(data);
            data = "DISPLAY BOARD SIDE ENABLE :" + getSideEnable();
            pw.println(data);
            data = "DISPLAY BOARD REAR ENABLE :" + getRearEnable();
            pw.println(data);
            data = "DISPLAY BOARD INTERNAL ENABLE :" + getIntEnable();
            pw.println(data);
            data = "DISPLAY BOARD RETRY COUNT :" + getDisBrdRetries();
            pw.println(data);
            data = "DISPLAY BOARD RETRY WAIT TIME :" + getDisbRdRetryWaitTime();
            pw.println(data);

            //DRIVING PARAMETERS
            pw.println("DRIVING CONFIGURATION");
            data = "OVER SPEED LIMIT :" + gpsDriving.over_speed_limit;
            pw.println(data);
            data = "HARSH BREAK THRESHOLD :" + gpsDriving.harsh_brk_threshold;
            pw.println(data);
            data = "HARSH ACC THRESHOLD :" + gpsDriving.harsh_acc_threshold;
            pw.println(data);

            //SCHEDULE
            pw.println("SCHEDULE CONFIGURATION");
            data = "SCHEDULE / ROUTE ENABLE :" + getSchRouteEnable();
            pw.println(data);
            data = "AUTO TRIP ENABLE :" + String.valueOf(getCurAutoTripStat());
            pw.println(data);
            //language
            pw.println("LANGUAGE CONFIGURATION");
            data = "LANGUAGE NAME :" + lang_type;
            pw.println(data);

            //NMEA data
            pw.println("NMEA CONFIGURATION");
            data = "GPRMC :" + get_nmea_gprmc();
            pw.println(data);
            data = "GPGGA :" + get_nmea_gpgga();
            pw.println(data);
            data = "GPVTG :" + get_nmea_gpvtg();
            pw.println(data);
            data = "GPGSV :" + get_nmea_gpgsv();
            pw.println(data);
            data = "GPGSA :" + get_nmea_gpgsa();
            pw.println(data);

            //ping
            pw.println("Ping CONFIGURATION");
            data = "Ping Config Time :" + String.valueOf(getPingConfigTime());
            pw.println(data);

            data = "Ping ACK IP1 ENABLE/DISABLE :" + String.valueOf(clsDefines.ACK_IMPLEMENT);
            pw.println(data);
            data = "Ping ACK IP2 ENABLE/DISABLE :" + String.valueOf(clsDefines.ACK_IMPLEMENT2);
            pw.println(data);

            //volume
            pw.println("Volume CONFIGURATION");
            data = "Voice Call Volume :" + String.valueOf(get_voice_call_volume());
            pw.println(data);
            data = "Speaker Volume :" + String.valueOf(get_speaker_volume());
            pw.println(data);
            data = "Driver Login :" + String.valueOf(clsSharedVariables.driverLoginEnabled);
            pw.println(data);
            data = "Vehicle Name  :" + String.valueOf(clsSharedVariables.vehicle_name);
            pw.println(data);
            data = "Internal Displayboard Time Enabled  :" + String.valueOf(clsSharedVariables.int_disbrd_time_enabled);
            pw.println(data);

            //volume
            pw.println("CAN CONFIGURATION");
            data = "Can Enabled :" + String.valueOf(getCanEnabled());
            pw.println(data);
            data = "Reverse CAM Value :" + String.valueOf(getReverseCamVal());
            pw.println(data);

            //Record Video
            pw.println("RECORD CONFIGURATION");
            data = "video Record Type :" + String.valueOf(getRecordType());
            pw.println(data);
            data = "Video Record From Date:" + (getRecordFromTime());
            pw.println(data);
            data = "Video Record To Date:" + (getRecordToTime());
            pw.println(data);
            data = "Video Record CAM1:" + String.valueOf(getCam1RecordSelected());
            pw.println(data);
            data = "Video Record CAM2:" + String.valueOf(getCam2RecordSelected());
            pw.println(data);
            data = "Video Record CAM3:" + String.valueOf(getCam3RecordSelected());
            pw.println(data);
            data = "Video Record CAM4:" + String.valueOf(getCam4RecordSelected());
            pw.println(data);

            data = "Reverse CAM PGN :" + String.valueOf(getReverseCamPgn());
            pw.println(data);
            data = "Reverse CAM SPN :" + String.valueOf(getReverseCamSpn());
            pw.println(data);

            data = "CAM1 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam1MirrorEnable);
            pw.println(data);
            data = "CAM2 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam2MirrorEnable);
            pw.println(data);
            data = "CAM3 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam3MirrorEnable);
            pw.println(data);
            data = "CAM4 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam4MirrorEnable);
            pw.println(data);
            data = "CAN UPDATE TIME INTEVAL :" + String.valueOf(clsSharedVariables.getCanUpdateTimeInterval());
            pw.println(data);
            data = "CAMERA1 TYPE RTSP :" + String.valueOf(clsSharedVariables.getCamera1TypeRtsp());
            pw.println(data);
            data = "CAMERA2 TYPE RTSP :" + String.valueOf(clsSharedVariables.getCamera2TypeRtsp());
            pw.println(data);
            data = "CAMERA3 TYPE RTSP :" + String.valueOf(clsSharedVariables.getCamera3TypeRtsp());
            pw.println(data);
            data = "CAMERA4 TYPE RTSP :" + String.valueOf(clsSharedVariables.getCamera4TypeRtsp());
            pw.println(data);

            data = "STOP REQUESTED PGN :" + String.valueOf(getStopReqPgn());
            pw.println(data);
            data = "STOP REQUESTED SPN :" + String.valueOf(getStopReqSpn());
            pw.println(data);

            data = "STOP REQUESTED Value :" + String.valueOf(getStopReqVal());
            pw.println(data);

            data = "GAP BETWEEN TRANSMISSION DISPLAY BOARD  :" + String.valueOf(getDisbRdGap());
            pw.println(data);

            data = "MAPS ENABLED  :" + String.valueOf(clsSharedVariables.getMapsEnable());
            pw.println(data);

            data = "EMERRGENCY SERVICES  :" + String.valueOf(clsSharedVariables.getEmergencyServicesEnable());
            pw.println(data);

            //SPL DISPLAY BOARD CONFIGURATION
            data = "DISPLAY BOARD SPECIAL OR NORMAL ENABLE :" + String.valueOf(clsSharedVariables.getArticulatedBus());
            pw.println(data);
            data = "DISPLAY BOARD SPECIAL SIDE ENABLE :" + String.valueOf(clsSharedVariables.getArtSideEnable());
            pw.println(data);
            data = "DISPLAY BOARD SPECIAL INTERNAL ENABLE :" + String.valueOf(clsSharedVariables.getArtIntEnable());
            pw.println(data);
            data = "DISPLAY BOARD SPECIAL SIDE ADDRESS :" + String.valueOf(clsDefines.SD_ART_ADDR);
            pw.println(data);
            data = "DISPLAY BOARD SPECIAL INTERNAL ADDRESS :" + String.valueOf(clsDefines.ID_ART_ADDR);
            pw.println(data);

            //enabling cameras
            //SPL DISPLAY BOARD CONFIGURATION
            data = "CAMERA1 ENABLE :" + String.valueOf(clsSharedVariables.getCam1Enabled());
            pw.println(data);
            data = "CAMERA2 ENABLE :" + String.valueOf(clsSharedVariables.getCam2Enabled());
            pw.println(data);
            data = "CAMERA3 ENABLE :" + String.valueOf(clsSharedVariables.getCam3Enabled());
            pw.println(data);
            data = "CAMERA4 ENABLE :" + String.valueOf(clsSharedVariables.getCam4Enabled());
            pw.println(data);

            //door 1 can Settings
            data = "DOOR1 PGN :" + String.valueOf(clsSharedVariables.getDoor1Pgn());
            pw.println(data);
            data = "DOOR1  SPN :" + String.valueOf(clsSharedVariables.getDoor1Spn());
            pw.println(data);
            data = "DOOR1 OPEN VALUE :" + String.valueOf(clsSharedVariables.getDoor1OpenVal());
            pw.println(data);
            data = "DOOR1 CLOSE VALUE :" + String.valueOf(clsSharedVariables.getDoor1CloseVal());
            pw.println(data);

            //door 2 can Settings
            data = "DOOR2 PGN :" + String.valueOf(clsSharedVariables.getDoor2Pgn());
            pw.println(data);
            data = "DOOR2  SPN :" + String.valueOf(clsSharedVariables.getDoor2Spn());
            pw.println(data);
            data = "DOOR2 OPEN VALUE :" + String.valueOf(clsSharedVariables.getDoor2OpenVal());
            pw.println(data);
            data = "DOOR2CLOSE VALUE :" + String.valueOf(clsSharedVariables.getDoor2CloseVal());
            pw.println(data);

            //camera type configuration
            data = "CAMERA MODEL :";
            pw.println(data);
            data = "CAMERA2 MODEL :";
            pw.println(data);
            data = "CAMERA3 MODEL :";
            pw.println(data);
            data = "CAMERA4 MODEL :";
            pw.println(data);
            data = "CAMERA TYPE :" + String.valueOf(clsSharedVariables.getCam1Type());
            pw.println(data);
            data = "CAMERA2 TYPE :" + String.valueOf(clsSharedVariables.getCam2Type());
            pw.println(data);
            data = "CAMERA3 TYPE :" + String.valueOf(clsSharedVariables.getCam3Type());
            pw.println(data);
            data = "CAMERA4 TYPE :" + String.valueOf(clsSharedVariables.getCam4Type());
            pw.println(data);

            data = "NET BALANCE COMMAND :" + String.valueOf(clsSharedVariables.getChkNetBalCommand());
            pw.println(data);

            data = "CAN DATA ENABLED :" + String.valueOf(clsSharedVariables.getCanDataEnabled());
            pw.println(data);

            data = "CAN LOG ENABLED :" + String.valueOf(clsSharedVariables.getCanLogEnabled());
            pw.println(data);

            data = "NON STOP DISTANCE :" + String.valueOf(clsSharedVariables.getNonStopDistance());
            pw.println(data);

            data = "PID DB ENABLE/DISABLE :" + String.valueOf(clsSharedVariables.getPidsDbEnable());
            pw.println(data);

            data = "DRIVER INFO DB ENABLE/DISABLE :" + String.valueOf(clsSharedVariables.getDisplayDriverDb());
            pw.println(data);

            data = "OVERSPEED BUZZER ENABLE/DISABLE :" + String.valueOf(clsSharedVariables.getOverSpeedBuzzerEnabled());
            pw.println(data);

            data = "Ring Tone Volume :" + String.valueOf(get_ring_tone_volume());
            pw.println(data);

            data = "DIG INPUT1 NAME :" + String.valueOf(clsSharedVariables.getDigInp1Name());
            pw.println(data);

            data = "DIG INPUT2 NAME :" + String.valueOf(clsSharedVariables.getDigInp2Name());
            pw.println(data);

            data = "DIG INPUT3 NAME :" + String.valueOf(clsSharedVariables.getDigInp3Name());
            pw.println(data);

            data = "DIG INPUT4 NAME :" + String.valueOf(clsSharedVariables.getDigInp4Name());
            pw.println(data);

            data = "SMS Contact No :" + clsSharedVariables.getSmsPhoneNo();
            pw.println(data);

            data = "DIG INPUT3 STATUS :" + "1";
            pw.println(data);

            data = "DIG INPUT4 STATUS :" + "1";
            pw.println(data);

            data = "EVENT RECORDING ENABLED :" + String.valueOf(clsSharedVariables.getEventRecordEnabled());
            pw.println(data);

            data = "CAN RECORDING ENABLED :" + String.valueOf(clsSharedVariables.getCanRecordEnabled());
            pw.println(data);

            data = "EVENT RECORDING TIME :" + String.valueOf(clsSharedVariables.getEventPostRecordTime());
            pw.println(data);

            data = "CAN RECORDING TIME :" + String.valueOf(clsSharedVariables.getCanPostRecordTime());
            pw.println(data);

            data = "EVENT RECORDING STREAM TYPE :" + String.valueOf(clsSharedVariables.getEventStreamType());
            pw.println(data);

            data = "CAN RECORDING STREAM TYPE :" + String.valueOf(clsSharedVariables.getCanStreamType());
            pw.println(data);

            data = "RS232 ENABLED :" + String.valueOf(clsSharedVariables.getRs232Enable());
            pw.println(data);

            data = "RS232 TYPE :" + String.valueOf(clsSharedVariables.getRs232Type());
            pw.println(data);

            data = "RS232 BAUDRATE :" + String.valueOf(clsSharedVariables.getRs232BaudRate());
            pw.println(data);

            data = "RS232 INTERVAL :" + String.valueOf(clsSharedVariables.getRs232GpsModeInterval());
            pw.println(data);

            data = "8 CAMERAS ENABLE :" + clsSharedVariables.getLiveType();
            pw.println(data);

            data = "CAMERA5 ENABLE :" + String.valueOf(clsSharedVariables.getCam5Enabled());
            pw.println(data);
            data = "CAMERA6 ENABLE :" + String.valueOf(clsSharedVariables.getCam6Enabled());
            pw.println(data);
            data = "CAMERA7 ENABLE :" + String.valueOf(clsSharedVariables.getCam7Enabled());
            pw.println(data);
            data = "CAMERA8 ENABLE :" + String.valueOf(clsSharedVariables.getCam8Enabled());
            pw.println(data);

            data = "CAMERA5 MODEL :";
            pw.println(data);
            data = "CAMERA6 MODEL :";
            pw.println(data);
            data = "CAMERA7 MODEL :";
            pw.println(data);
            data = "CAMERA8 MODEL :";
            pw.println(data);
            data = "CAMERA5 TYPE :" + String.valueOf(clsSharedVariables.getCam5Type());
            pw.println(data);
            data = "CAMERA6 TYPE :" + String.valueOf(clsSharedVariables.getCam6Type());
            pw.println(data);
            data = "CAMERA7 TYPE :" + String.valueOf(clsSharedVariables.getCam7Type());
            pw.println(data);
            data = "CAMERA8 TYPE :" + String.valueOf(clsSharedVariables.getCam8Type());
            pw.println(data);

            data = "Video Record CAM5:" + String.valueOf(getCam5RecordSelected());
            pw.println(data);
            data = "Video Record CAM6:" + String.valueOf(getCam6RecordSelected());
            pw.println(data);
            data = "Video Record CAM7:" + String.valueOf(getCam7RecordSelected());
            pw.println(data);
            data = "Video Record CAM8:" + String.valueOf(getCam8RecordSelected());
            pw.println(data);

            data = "CAM5 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam5MirrorEnable);
            pw.println(data);
            data = "CAM6 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam6MirrorEnable);
            pw.println(data);
            data = "CAM7 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam7MirrorEnable);
            pw.println(data);
            data = "CAM8 MIRROR ON/OFF :" + String.valueOf(clsSharedVariables.cam8MirrorEnable);
            pw.println(data);
            data = "Vehicle Reg No :" + getVechicleRegNo();
            pw.println(data);
            data = "Emergency State Duration :" + String.valueOf(getEmergencyStateTimeDuration());
            pw.println(data);

            data = "Emergency Mode by Panic  Button :" + String.valueOf(clsSharedVariables.getEmergencyModeByPanicButton());
            pw.println(data);

            data = "Emergency Mode by SMS :" + String.valueOf(clsSharedVariables.getEmergencyModeBySmsMode());
            pw.println(data);

            data = "Cam1 User :" + String.valueOf(clsSharedVariables.getCam1UserName());
            pw.println(data);
            data = "Cam2 User :" + String.valueOf(clsSharedVariables.getCam2UserName());
            pw.println(data);
            data = "Cam3 User :" + String.valueOf(clsSharedVariables.getCam3UserName());
            pw.println(data);
            data = "Cam4 User :" + String.valueOf(clsSharedVariables.getCam4UserName());
            pw.println(data);
            data = "Cam5 User :" + String.valueOf(clsSharedVariables.getCam5UserName());
            pw.println(data);
            data = "Cam6 User :" + String.valueOf(clsSharedVariables.getCam6UserName());
            pw.println(data);
            data = "Cam7 User :" + String.valueOf(clsSharedVariables.getCam7UserName());
            pw.println(data);
            data = "Cam8 User :" + String.valueOf(clsSharedVariables.getCam8UserName());
            pw.println(data);

            data = "Cam1 pd :" + String.valueOf(clsSharedVariables.getCam1Pwd());
            pw.println(data);
            data = "Cam2  pd :" + String.valueOf(clsSharedVariables.getCam2Pwd());
            pw.println(data);
            data = "Cam3  pd :" + String.valueOf(clsSharedVariables.getCam3Pwd());
            pw.println(data);
            data = "Cam4  pd :" + String.valueOf(clsSharedVariables.getCam4Pwd());
            pw.println(data);
            data = "Cam5  pd :" + String.valueOf(clsSharedVariables.getCam5Pwd());
            pw.println(data);
            data = "Cam6  pd :" + String.valueOf(clsSharedVariables.getCam6Pwd());
            pw.println(data);
            data = "Cam7  pd :" + String.valueOf(clsSharedVariables.getCam7Pwd());
            pw.println(data);
            data = "Cam8  pd :" + String.valueOf(clsSharedVariables.getCam8Pwd());
            pw.println(data);
            data = "Cam1  Audio :" + String.valueOf(clsSharedVariables.getCam1RecordAudioSelected());
            pw.println(data);
            data = "Cam2  Audio :" + String.valueOf(clsSharedVariables.getCam2RecordAudioSelected());
            pw.println(data);
            data = "Cam3  Audio :" + String.valueOf(clsSharedVariables.getCam3RecordAudioSelected());
            pw.println(data);
            data = "Cam4  Audio :" + String.valueOf(clsSharedVariables.getCam4RecordAudioSelected());
            pw.println(data);
            data = "Cam5  Audio :" + String.valueOf(clsSharedVariables.getCam5RecordAudioSelected());
            pw.println(data);
            data = "Cam6  Audio :" + String.valueOf(clsSharedVariables.getCam6RecordAudioSelected());
            pw.println(data);
            data = "Cam7  Audio :" + String.valueOf(clsSharedVariables.getCam7RecordAudioSelected());
            pw.println(data);
            data = "Cam8  Audio :" + String.valueOf(clsSharedVariables.getCam8RecordAudioSelected());
            pw.println(data);
            data = "Pre Event Record Time :" + String.valueOf(clsSharedVariables.getEventPreRecordTime());
            pw.println(data);
            data = "Pre Event Record Time :" + String.valueOf(clsSharedVariables.getGyroscope());
            pw.println(data);
            data = "DIG INPUT1 DEF STATE :" + String.valueOf(clsSharedVariables.getDigInp1DefaultClosedState());
            pw.println(data);

            data = "DIG INPUT2 DEF STATE :" + String.valueOf(clsSharedVariables.getDigInp2DefaultClosedState());
            pw.println(data);

            data = "DIG INPUT3 DEF STATE :" + String.valueOf(clsSharedVariables.getDigInp3DefaultClosedState());
            pw.println(data);

            data = "DIG INPUT4 DEF STATE :" + String.valueOf(clsSharedVariables.getDigInp4DefaultClosedState());
            pw.println(data);

            data = "ETHERNET ENABLED :" + String.valueOf(clsSharedVariables.getEthernetEnable());
            pw.println(data);

            data = "ETHERNET TYPE :" + String.valueOf(clsSharedVariables.getEthernetType());
            pw.println(data);

            data = "ETHERNET port :" + String.valueOf(clsSharedVariables.getEthernetPortNo());
            pw.println(data);

            data = "ETHERNET ipaddr :" + String.valueOf(clsSharedVariables.getEthernetIpAddr());
            pw.println(data);

            data = "ETHERNET INTERVAL :" + String.valueOf(clsSharedVariables.getEthernetGpsModeInterval());
            pw.println(data);

            //FTP UPLOAD for video
            data = "Video Dig1 ftp Upload Enable :" + String.valueOf(clsSharedVariables.getVideoDig1FtpUploadEnable());
            pw.println(data);
            data = "Video Dig2 ftp Upload Enable :" + String.valueOf(clsSharedVariables.getVideoDig2FtpUploadEnable());
            pw.println(data);
            data = "Video Dig3 ftp Upload Enable :" + String.valueOf(clsSharedVariables.getVideoDig3FtpUploadEnable());
            pw.println(data);
            data = "Video Dig4 ftp Upload Enable :" + String.valueOf(clsSharedVariables.getVideoDig4FtpUploadEnable());
            pw.println(data);

            data = "SMS UPDATE RATE :" + String.valueOf(clsSharedVariables.sms_update_rate);
            pw.println(data);

            data = "GYRO ANGLE :" + String.valueOf(clsSharedVariables.getGyroAngle());
            pw.println(data);

            data = "GPS ENABLED :" + String.valueOf(clsSharedVariables.get_gps_enabled());
            pw.println(data);
            data = "IRNSS ENABLED :" + String.valueOf(clsSharedVariables.get_irnss_enabled());
            pw.println(data);
            data = "GLONASS ENABLED :" + String.valueOf(clsSharedVariables.get_glonass_enabled());
            pw.println(data);

            //audio status
            data = "Cam1 audio status :" + String.valueOf(objHealth.get_cam_audio1_status());
            pw.println(data);
            data = "Cam2 audio status :" + String.valueOf(objHealth.get_cam_audio2_status());
            pw.println(data);
            data = "Cam3 audio status :" + String.valueOf(objHealth.get_cam_audio3_status());
            pw.println(data);
            data = "Cam4 audio status :" + String.valueOf(objHealth.get_cam_audio4_status());
            pw.println(data);
            data = "Cam5 audio status :" + String.valueOf(objHealth.get_cam_audio5_status());
            pw.println(data);
            data = "Cam6 audio status :" + String.valueOf(objHealth.get_cam_audio6_status());
            pw.println(data);
            data = "Cam7 audio status :" + String.valueOf(objHealth.get_cam_audio6_status());
            pw.println(data);
            data = "Cam8 audio status :" + String.valueOf(objHealth.get_cam_audio6_status());
            pw.println(data);

            data = "Third IP Enable :" + String.valueOf(clsSharedVariables.getIpAddr3Enable());
            pw.println(data);
            data = "Third IP IPAddress  :" + String.valueOf(clsSharedVariables.getIpAddr3());
            pw.println(data);
            data = "Third IP Port No :" + String.valueOf(clsSharedVariables.getPortNo3());
            pw.println(data);

            data = "First IP Enable :" + String.valueOf(clsSharedVariables.getIpAddr1Enable());
            pw.println(data);

            data = "Second IP Enable :" + String.valueOf(clsSharedVariables.getIpAddr2Enable());
            pw.println(data);

            data = "Fourth IP Enable :" + String.valueOf(clsSharedVariables.getIpAddr4Enable());
            pw.println(data);

            data = "Fourth IP Address :" + String.valueOf(clsSharedVariables.getIpAddr4());
            pw.println(data);

            data = "Fourth Port  :" + String.valueOf(clsSharedVariables.getPortNo4());
            pw.println(data);

            data = "Fifth IP Enable :" + String.valueOf(clsSharedVariables.getIpAddr5Enable());
            pw.println(data);

            data = "Fifth IP Address :" + String.valueOf(clsSharedVariables.getIpAddr5());
            pw.println(data);

            data = "Fifth Port  :" + String.valueOf(clsSharedVariables.getPortNo5());
            pw.println(data);

            data = "CAN BUS TYPE :" + String.valueOf(clsSharedVariables.getCanBusType());
            pw.println(data);
            data = "Reverse CAM SPN1 :" + String.valueOf(getReverseCamSpn1());
            pw.println(data);
            data = "Reverse CAM SPN2 :" + String.valueOf(getReverseCamSpn2());
            pw.println(data);
            data = "Reverse CAM PGN1 :" + String.valueOf(getReverseCamPgn1());
            pw.println(data);
            data = "Reverse CAM PGN2 :" + String.valueOf(getReverseCamPgn2());
            pw.println(data);
            data = "Reverse CAM Value1 :" + String.valueOf(getReverseCamVal1());
            pw.println(data);
            data = "Reverse CAM Value2 :" + String.valueOf(getReverseCamVal2());
            pw.println(data);
            data = "Set ip5 protocol:" + String.valueOf(getIp5Protocol());
            pw.println(data);
            data = "Surat protocol :" + String.valueOf(getSuratProtocol());
            pw.println(data);
            data = "Can2 Enabled :" + String.valueOf(getCanEnabled1());
            pw.println(data);
            data="ftp_port :" + String.valueOf(getFtpPortNo());
            pw.println(data);
            //ftp port no
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
//            System.out.println("File Not found");
            return false;
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
//            System.out.println("Expection IOS");
            return false;
        } catch (Exception ex) {
//            System.out.println("Expection");
            return false;
        } finally {
            runCmd("sudo sync");
            pw = null;
            f = null;
            data = null;
            file = null;
        }
        return true;
    }

    public synchronized void read_cfg_data_file() {
        short i = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        //Get the text file
        File file = new File(config_filepath, "ConfigParams.txt");
        if (file.exists()) {

            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    try {
                        switch (i) {
                            case 0:
                                // skip SERVER CONFIGURATION
                                break;
                            case 1:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setOBUID(split_str[1].trim());     // OBUID:
                                } else {
                                    setOBUID("000009");
                                }

                                break;
                            case 2:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setMainDeviceShutTime(Integer.parseInt(split_str[1].trim()));     // device shut :
                                }

                                break;
                            case 3:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSleepModeState(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    setSleepModeState(SLEEP_SLEEP_MODE);
                                }
                            case 4:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte(split_str[1].trim()) > 1) {
                                        clsSharedVariables.setCommMode(COMM_GPRS);

                                    } else {
                                        clsSharedVariables.setCommMode((Byte.parseByte(split_str[1].trim())));
                                    }
                                }
                                break;
                            case 5:
                                break; //// skip DEVICE CONFIGURATION
                            case 6:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setIpAddr1(split_str[1].trim());     // Ip Addr:
                                }
                                break;
                            case 7:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setPortNo1(Integer.parseInt(split_str[1].trim()));     // Port:
                                }
                                break;
                            case 8:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setIpAddr2(split_str[1].trim());     // Ip Addr:
                                }
                                break;
                            case 9:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setPortNo2(Integer.parseInt(split_str[1].trim()));     // Port:
                                }
                                break;
                            case 10:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setIpInterface(Byte.parseByte(split_str[1].trim()));     // Port:
                                }
                                break;
                            case 11:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setApn(split_str[1].trim());     // server_url:

                                }
                                break;
                            case 12:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    server_url = split_str[1].trim();     // server_url:
                                }

                                break;
                            case 13:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    chk_server_dns = Boolean.parseBoolean(split_str[1].trim());     // chk_server_dns:
                                }
                                break;
                            case 14:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    server_dns = split_str[1].trim();     // server_dns:
                                }
                                break;
                            case 15:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setRouteDevDistance(Integer.parseInt(split_str[1].trim()));     // route dev distance:
                                }
                                break;
                            case 16:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_gprs_normal_mode_val(Short.parseShort(((split_str[1].trim()))));     // normal:
                                }
                                break;
                            case 17:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    video_update_rate = Short.parseShort(((split_str[1].trim())));     // active:
                                }
                                break;
                            case 18:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_gprs_sleep_val(Short.parseShort(((split_str[1].trim()))));     // sleep:
                                }
                                break;
                            case 19:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setNonStopTime(Integer.parseInt(split_str[1].trim()));     // non stop:
                                }
                                break;
                            case 20:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    ftp_ipaddress = ((split_str[1].trim()));     // ftp ipaddr:
                                }
                                break;
                            case 21:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    ftpusername = ((split_str[1].trim()));     // ftp username:
                                }
                                break;
                            case 22:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    ftpPassword = ((split_str[1].trim()));     // ftp password:
                                }
                                break;
                            case 23:
                                //skip displaynboard CONFIGURATION
                                break;
                            case 24:
                                /* split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setDisBrdName(Byte.parseByte(split_str[1].trim()));
                                }*/
                                break;
                            case 25:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setDisBrdBaudRate(Integer.parseInt((split_str[1].trim())));
                                }
                                setDisBrdBaudRate(115200);

                                break;
                            case 26:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setFrontEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 27:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSideEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 28:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setRearEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 29:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setIntEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 30:
                                /* split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setDisBrdRetries(Integer.parseInt(split_str[1].trim()));
                                }*/
                                break;
                            case 31:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setDisbRdRetryWaitTime(Integer.parseInt(split_str[1].trim()));
                                } else {
                                    setDisbRdRetryWaitTime(100);
                                }
                                break;
                            case 32:
                                //Skip DRIVING PARAMETERS
                                break;
                            case 33:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    gpsDriving.over_speed_limit = Short.parseShort(((split_str[1].trim())));
                                }
                                break;
                            case 34:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    gpsDriving.harsh_brk_threshold = Short.parseShort(((split_str[1].trim())));
                                }
                                break;

                            case 35:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    gpsDriving.harsh_acc_threshold = Short.parseShort(((split_str[1].trim())));
                                }
                                break;

                            case 36:
                                //SKIP SCHEDULE CONFIGURATION
                                break;
                            case 37:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setSchRouteEnable(Byte.parseByte(split_str[1].trim()));

                                }
                                break;
                            case 38:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setAutoTripStat(Boolean.parseBoolean((split_str[1].trim())));

                                }
                                break;
                            case 39:
                                //skip language configuration
                                break;
                            case 40:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (clsSharedVariables.driverLoginEnabled == false) {
                                        if (Byte.parseByte(split_str[1].trim()) > 12) {
                                            lang_type = LANG_ENGLISH;
                                        } else {
                                            lang_type = (Byte.parseByte((split_str[1].trim())));
                                        }
                                    }
                                }
                                break;
                            case 41:
                                //skip NMEA configuration
                                break;
                            case 42:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_nmea_gprmc((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;
                            case 43:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_nmea_gpgga((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;
                            case 44:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_nmea_gpvtg((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;
                            case 45:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_nmea_gpgsv((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;
                            case 46:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_nmea_gpgsa((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;
                            case 47:
                                //skip Ping configuration
                                break;
                            case 48:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setPingConfigTime((Integer.parseInt((split_str[1].trim()))));
                                }
                                break;
                            case 49:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsDefines.ACK_IMPLEMENT = ((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;
                            case 50:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsDefines.ACK_IMPLEMENT2 = ((Boolean.parseBoolean((split_str[1].trim()))));
                                }
                                break;

                            case 51:
                                //skip volume configuration
                                break;
                            case 52:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_voice_call_volume(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 53:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    set_speaker_volume(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 54:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.driverLoginEnabled = (Boolean.parseBoolean((split_str[1].trim())));
                                } else {
                                    clsSharedVariables.driverLoginEnabled = false;
                                }
                                break;

                            case 55:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.vehicle_name = (Byte.parseByte((split_str[1].trim())));
                                } else {
                                    clsSharedVariables.vehicle_name = (clsDefines.JBM_BODYCOMPUTER);
                                }
                                break;

                            case 56:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.int_disbrd_time_enabled = (Byte.parseByte((split_str[1].trim())));
                                } else {
                                    clsSharedVariables.int_disbrd_time_enabled = TIME_DISABLED;
                                }
                                break;
                            case 57:
                                break;
                            case 58:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanEnabled(Boolean.parseBoolean(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setCanEnabled(true);
                                }
                                break;
                            case 59:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamVal(Short.parseShort(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setReverseCamVal((short) 124);
                                }
                                break;
                            case 60:
                                break;
                            case 61:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordType(Byte.parseByte(split_str[1].trim()));

                                } else {
                                    clsSharedVariables.setRecordType(VIDEO_RECORD_CONTINUOUS);
                                }
                                break;
                            case 62:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordFromTime((split_str[1].trim()) + ":" + (split_str[2].trim()));
                                }
                                break;
                            case 63:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRecordToTime((split_str[1].trim()) + ":" + (split_str[2].trim()));
                                }
                                break;
                            case 64:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam1RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 65:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam2RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 66:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam3RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 67:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam4RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 68:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamPgn(Integer.parseInt(split_str[1].trim()));
                                }

                                break;
                            case 69:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamSpn(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 70:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam1MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 71:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam2MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 72:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam3MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 73:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam4MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 74:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setCanUpdateTimeInterval(Short.parseShort(split_str[1].trim()));
                                }
                                break;
                            case 75:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamera1TypeRtsp(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 76:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamera2TypeRtsp(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 77:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamera3TypeRtsp(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 78:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCamera4TypeRtsp(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;

                            case 79:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStopReqPgn(Integer.parseInt(split_str[1].trim()));
                                }

                                break;
                            case 80:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStopReqSpn(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 81:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setStopReqVal(Short.parseShort(split_str[1].trim()));
                                }
                                break;
                            case 82:
                                /* split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setDisbRdGap(Integer.parseInt(split_str[1].trim()));
                                }*/
                                break;
                            case 83:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setMapsEnable(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                if (clsDefines.COMPANY_NAME_NMEA_PROT == COMP_AMINEX || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH) {
                                    clsSharedVariables.setMapsEnable(Boolean.parseBoolean(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setMapsEnable(false);
                                }

                                break;
                            case 84:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEmergencyServicesEnable(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                if (clsDefines.COMPANY_NAME_NMEA_PROT == COMP_AMINEX || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH) {
                                    clsSharedVariables.setEmergencyServicesEnable(Boolean.parseBoolean(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setEmergencyServicesEnable(false);
                                }

                                break;
                            case 85:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setArticulatedBus(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 86:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setArtSideEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 87:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setArtIntEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 88:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsDefines.SD_ART_ADDR = Byte.parseByte(split_str[1].trim());
                                }
                                break;
                            case 89:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsDefines.ID_ART_ADDR = Byte.parseByte(split_str[1].trim());
                                }
                                break;

                            case 90:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam1Enabled(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 91:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam2Enabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 92:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam3Enabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 93:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam4Enabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 94:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor1Pgn(Integer.parseInt(((split_str[1].trim()))));
                                }
                                break;
                            case 95:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor1Spn(Integer.parseInt(((split_str[1].trim()))));
                                }
                                break;
                            case 96:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor1OpenVal(Short.parseShort(((split_str[1].trim()))));
                                }
                                break;
                            case 97:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor1CloseVal(Short.parseShort(((split_str[1].trim()))));
                                }
                                break;

                            case 98:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor2Pgn(Integer.parseInt(((split_str[1].trim()))));
                                }
                                break;
                            case 99:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor2Spn(Integer.parseInt(((split_str[1].trim()))));
                                }
                                break;
                            case 100:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor2OpenVal(Short.parseShort(((split_str[1].trim()))));
                                }
                                break;
                            case 101:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDoor2CloseVal(Short.parseShort(((split_str[1].trim()))));
                                }
                                break;
                            case 102:
                                split_str = line.split(":");

                                break;
                            case 103:
                                split_str = line.split(":");

                                break;
                            case 104:
                                split_str = line.split(":");

                                break;
                            case 105:
                                split_str = line.split(":");

                                break;
                            case 106:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam1Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam1Type(Byte.parseByte((split_str[1].trim())));
                                    }
                                }
                                break;
                            case 107:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam2Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam2Type(Byte.parseByte((split_str[1].trim())));
                                    }
                                }
                                break;
                            case 108:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam3Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam3Type(Byte.parseByte((split_str[1].trim())));
                                    }
                                }
                                break;
                            case 109:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam4Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam4Type(Byte.parseByte((split_str[1].trim())));
                                    }
                                }
                                break;
                            case 110:
                                split_str = line.split(":");
                                if (split_str.length > 1) {

                                    clsSharedVariables.setChkNetBalCommand(((split_str[1].trim())));

                                }
                                break;
                            case 111:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanDataEnabled(Boolean.parseBoolean((split_str[1].trim())));
                                }
                                break;
                            case 112:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanLogEnabled(Boolean.parseBoolean((split_str[1].trim())));
                                }
                                break;

                            case 113:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setNonStopDistance(Integer.parseInt(((split_str[1].trim()))));
                                }
                                break;
                            case 114:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setPidsDbEnable(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 115:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setDisplayDriverDb(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;

                            case 116:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("true")) {
                                        clsSharedVariables.setOverSpeedBuzzerEnabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                    } else {
                                        clsSharedVariables.setOverSpeedBuzzerEnabled(false);
                                    }
                                }
                                break;
                            case 117:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte(split_str[1].trim()) < 50) {
                                        set_ring_tone_volume((byte) 50);
                                    } else {
                                        set_ring_tone_volume(Byte.parseByte(split_str[1].trim()));
                                    }
                                }
                                break;

                            case 118:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte(split_str[1].trim()) > 7) {
                                        clsSharedVariables.setDigInp1Name(DIG_INPUT_NONE);

                                    } else {
                                        clsSharedVariables.setDigInp1Name(Byte.parseByte(split_str[1].trim()));
                                    }
                                }
                                break;
                            case 119:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte(split_str[1].trim()) > 7) {
                                        clsSharedVariables.setDigInp2Name(DIG_INPUT_NONE);

                                    } else {
                                        clsSharedVariables.setDigInp2Name(Byte.parseByte(split_str[1].trim()));
                                    }
                                }
                                break;
                            case 120:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte(split_str[1].trim()) > 7) {
                                        clsSharedVariables.setDigInp3Name(DIG_INPUT_NONE);

                                    } else {
                                        clsSharedVariables.setDigInp3Name(Byte.parseByte(split_str[1].trim()));
                                    }
                                }
                                break;
                            case 121:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte(split_str[1].trim()) > 7) {
                                        clsSharedVariables.setDigInp4Name(DIG_INPUT_NONE);

                                    } else {
                                        clsSharedVariables.setDigInp4Name(Byte.parseByte(split_str[1].trim()));
                                    }
                                }
                                break;
                            case 122:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSmsPhoneNo((split_str[1].trim()));
                                }

                                break;
                            case 123:

                                break;
                            case 124:

                                break;
                            case 125:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventRecordEnabled(Boolean.parseBoolean(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEventRecordEnabled(false);
                                }
                                break;
                            case 126:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanRecordEnabled(Boolean.parseBoolean(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setCanRecordEnabled(false);
                                }
                                break;
                            case 127:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventPostRecordTime(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEventPostRecordTime((byte) 0);
                                }
                                break;
                            case 128:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanPostRecordTime(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setCanPostRecordTime((byte) 0);
                                }
                                break;

                            case 129:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventStreamType(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEventStreamType((byte) 0);
                                }
                                break;
                            case 130:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanStreamType(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setCanStreamType((byte) 0);
                                }
                                break;
                            case 131:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRs232Enable(Boolean.parseBoolean(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setRs232Enable(false);
                                }
                                break;

                            case 132:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRs232Type(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setRs232Type((byte) 0);
                                }
                                break;
                            case 133:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRs232BaudRate(Integer.parseInt(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setRs232BaudRate(115200);
                                }
                                break;

                            case 134:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setRs232GpsModeInterval(Integer.parseInt(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setRs232GpsModeInterval(5);
                                }
                                break;
                            case 135:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setLiveType(Byte.parseByte(split_str[1].trim()));
                                }
                                break;
                            case 136:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam5Enabled(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 137:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam6Enabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 138:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam7Enabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 139:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam8Enabled(Boolean.parseBoolean(((split_str[1].trim()))));
                                }
                                break;
                            case 140:

                                break;
                            case 141:

                                break;
                            case 142:

                                break;
                            case 143:

                                break;
                            case 144:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam5Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam5Type((Byte.parseByte(split_str[1].trim())));
                                    }
                                }
                                break;
                            case 145:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam6Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam6Type((Byte.parseByte(split_str[1].trim())));
                                    }
                                }
                                break;
                            case 146:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam7Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam7Type((Byte.parseByte(split_str[1].trim())));
                                    }
                                }
                                break;
                            case 147:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (Byte.parseByte((split_str[1].trim())) > 5) {
                                        clsSharedVariables.setCam8Type(clsDefines.CAM_HIKVISION);
                                    } else {
                                        clsSharedVariables.setCam8Type((Byte.parseByte(split_str[1].trim())));
                                    }
                                }
                                break;
                            case 148:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam5RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 149:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam6RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 150:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam7RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 151:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam8RecordSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 152:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam5MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 153:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam6MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 154:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam7MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 155:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.cam8MirrorEnable = (Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 156:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setVechicleRegNo(split_str[1].trim());     // VechicleRegNo:
                                }
                                break;
                            case 157:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    setEmergencyStateTimeDuration(Byte.parseByte(split_str[1].trim()));     // VechicleRegNo:
                                }

                                break;
                            case 158:
                                /* split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEmergencyModeByPanicButton(Byte.parseByte(split_str[1].trim()));     // VechicleRegNo:
                                }*/

                                break;
                            case 159:
                                /*  split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEmergencyModeBySmsMode(Byte.parseByte(split_str[1].trim()));     // VechicleRegNo:
                                }*/

                                break;
                            case 160:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam1UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam1UserName((split_str[1].trim()));
                                    }
                                }

                                break;
                            case 161:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam2UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam2UserName((split_str[1].trim()));     //  user name
                                    }
                                }
                                break;
                            case 162:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam3UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam3UserName((split_str[1].trim()));     //  user name
                                    }
                                }
                                break;
                            case 163:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam4UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam4UserName((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 164:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam5UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam5UserName((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 165:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam6UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam6UserName((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 166:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam7UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam7UserName((split_str[1].trim()));
                                    }
                                }

                                break;
                            case 167:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam8UserName("admin");
                                    } else {
                                        clsSharedVariables.setCam8UserName((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 168:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam1Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam1Pwd((split_str[1].trim()));     //  user name
                                    }
                                }
                                break;
                            case 169:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam2Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam2Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 170:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam3Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam3Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 171:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam4Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam4Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 172:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam5Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam5Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 173:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam6Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam6Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 174:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam7Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam7Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 175:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("0") || split_str[1].equals("true")) {
                                        clsSharedVariables.setCam8Pwd("sumith123");
                                    } else {
                                        clsSharedVariables.setCam8Pwd((split_str[1].trim()));     //  user name
                                    }
                                }

                                break;
                            case 176:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam1RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 177:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam2RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 178:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam3RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 179:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam4RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 180:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam5RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 181:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam6RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 182:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam7RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 183:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCam8RecordAudioSelected(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 184:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEventPreRecordTime(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEventPreRecordTime((byte) 0);
                                }
                                break;
                            case 185:
                                /*  split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setGyroscope(Boolean.parseBoolean(split_str[1].trim()));     // Gyroscope:
                                }*/
                                break;
                            case 186:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("true")) {
                                        clsSharedVariables.setDigInp1DefaultClosedState(Boolean.parseBoolean(split_str[1].trim()));     // Gyroscope:
                                    } else {
                                        clsSharedVariables.setDigInp1DefaultClosedState(true);
                                    }
                                }
                                break;
                            case 187:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("true")) {
                                        clsSharedVariables.setDigInp2DefaultClosedState(Boolean.parseBoolean(split_str[1].trim()));     // Gyroscope:
                                    } else {
                                        clsSharedVariables.setDigInp2DefaultClosedState(true);
                                    }
                                }
                                break;
                            case 188:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("true")) {
                                        clsSharedVariables.setDigInp3DefaultClosedState(Boolean.parseBoolean(split_str[1].trim()));     // Gyroscope:
                                    } else {
                                        clsSharedVariables.setDigInp3DefaultClosedState(true);
                                    }

                                }
                                break;
                            case 189:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    if (split_str[1].equals("false") || split_str[1].equals("true")) {
                                        clsSharedVariables.setDigInp4DefaultClosedState(Boolean.parseBoolean(split_str[1].trim()));     // Gyroscope:
                                    } else {
                                        clsSharedVariables.setDigInp4DefaultClosedState(true);
                                    }
                                }
                                break;
                            case 190:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEthernetEnable(Boolean.parseBoolean(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEthernetEnable(false);
                                }
                                break;

                            case 191:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEthernetType(Byte.parseByte(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEthernetType((byte) 0);
                                }
                                break;
                            case 192:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEthernetPortNo(Integer.parseInt(split_str[1].trim()));     // mic interval:
                                }
                                break;
                            case 193:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEthernetIpAddr((split_str[1].trim()));     // mic interval:
                                }
                                break;
                            case 194:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setEthernetGpsModeInterval(Integer.parseInt(split_str[1].trim()));     // mic interval:
                                } else {
                                    clsSharedVariables.setEthernetGpsModeInterval(5);
                                }
                                break;
                            case 195:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoDig1FtpUploadEnable(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 196:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoDig2FtpUploadEnable(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 197:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoDig3FtpUploadEnable(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 198:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setVideoDig4FtpUploadEnable(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 199:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.sms_update_rate = (Integer.parseInt(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 200:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setGyroAngle(Byte.parseByte(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 201:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.set_gps_enabled(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 202:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.set_irnss_enabled(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 203:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.set_glonass_enabled(false);//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            //audio status
                            case 204:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio1_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 205:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio2_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 206:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio3_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 207:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio4_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 208:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio5_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 209:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio6_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 210:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio7_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 211:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    objHealth.set_cam_audio8_status(Byte.parseByte(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 212:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr3Enable(Boolean.parseBoolean(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 213:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr3((split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 214:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setPortNo3(Integer.parseInt(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 215:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr1Enable(Boolean.parseBoolean(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 216:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr2Enable(Boolean.parseBoolean(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 217:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr4Enable(Boolean.parseBoolean(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 218:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr4((split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 219:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setPortNo4(Integer.parseInt(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;

                            case 220:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr5Enable(Boolean.parseBoolean(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }

                                break;

                            case 221:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIpAddr5((split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 222:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setPortNo5(Integer.parseInt(split_str[1].trim()));//(Boolean.parseBoolean(split_str[1].trim()));     // VechicleRegNo:
                                }
                                break;
                            case 223:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanBusType(Byte.parseByte(split_str[1].trim()));     // Port:
                                }
                                break;
                            case 224:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamSpn1(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 225:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamSpn2(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 226:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamPgn1(Integer.parseInt(split_str[1].trim()));
                                }

                                break;
                            case 227:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamPgn2(Integer.parseInt(split_str[1].trim()));
                                }

                                break;
                            case 228:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamVal1(Short.parseShort(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setReverseCamVal1((short) 124);
                                }
                                break;
                            case 229:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setReverseCamVal2(Short.parseShort(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setReverseCamVal2((short) 124);
                                }
                                break;
                            case 230:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setIp5Protocol(Byte.parseByte(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setIp5Protocol(clsDefines.AIS140);
                                }
                                break;

                            case 231:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSuratProtocol(Boolean.parseBoolean(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setSuratProtocol(false);
                                }
                                break;
                            case 232:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setCanEnabled1(Boolean.parseBoolean(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setCanEnabled1(true);
                                }
                                break;
                             case 233:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                   clsSharedVariables.setFtpPortNo(Integer.parseInt(split_str[1].trim()));     // Port:
                                }
                                break;
                        }

                    } catch (Exception ex) {
                        //  System.out.println("exception");
                    }
                    i++;
                }
                if ((clsSharedVariables.getIpAddr5Enable()) && ((clsSharedVariables.getIp5Protocol() == clsDefines.AIS140) || (clsSharedVariables.getIp5Protocol() == clsDefines.SURAT))) {
                    clsDefines.odometer_enable = true;
                }

            } catch (FileNotFoundException e) {
                write_log_low_memory_data("configparams.txt File " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } catch (IOException e) {
                write_log_low_memory_data("configparams.txt File " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } catch (Exception e) {
                write_log_low_memory_data("configparams.txt File " + getTravelledDis() + " " + getOdometerPrevLat() + " , " + getOdometerPrevLong() + e.getMessage());

            } finally {

                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                }

                split_str = null;
                file = null;
                br = null;
            }
        }
    }

    public boolean wpa() {
        String data;
        File file = new File(config_filepath, "wpa_supplicant.conf");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            // if (file.exists()) 
            {
                file.setReadable(true, false);

                file.setWritable(true, false);
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                data = "ssid=" + '"' + wifi_ssid + '"';
                pw.println(data);

                data = "psk=" + '"' + wifi_password + '"';
                pw.println(data);
                pw.println("}");
                pw.flush();
                pw.close();
                f.close();
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            runCmd("sudo sync");
            file = null;
            f = null;
            pw = null;
        }

        return true;
    }

    public void read_wpa() {

        String line;
        BufferedReader br = null;
        String[] split_str;
//Get the text file
        File file = new File(config_filepath, "wpa_supplicant.conf");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    if (line.contains("ssid")) {
                        line = line.replaceAll("\"", "");
                        split_str = line.split("ssid=");
                        wifi_ssid = split_str[1];
                    }
                    if (line.contains("psk")) {
                        line = line.replaceAll("\"", "");
                        split_str = line.split("psk=");
                        wifi_password = split_str[1];
                        break;

                    }

                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }

                line = null;
                br = null;

                //Get the text file
                file = null;
            }
        }

    }

    public boolean write_reset_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }

        File file = new File(media_filepath, "fileReset.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.no_times_reset + "," + clsSharedVariables.reset_type);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }

        return true;

    }

    public void read_reset_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String[] split_str;
        String line;
        BufferedReader br = null;

        File file = new File(media_filepath, "fileReset.txt");
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length >= 2) {
                        clsSharedVariables.no_times_reset = Byte.parseByte(split_str[0]);
                        clsSharedVariables.reset_type = Byte.parseByte(split_str[1]);
                    }
                    break;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;

                file = null;
            }
        }
    }

    public boolean write_max_reset_data_today(byte max_reset_count) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "fileMaxResetToday.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(max_reset_count + "," + Calendar.getInstance().getTimeInMillis());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }
        return true;

    }

    public byte read_max_reset_data_today() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 10;
        }
        String[] split_str;
        String line;
        BufferedReader br = null;
        byte max_reset_count = 0;
        long date_today = 0;
        Calendar cal = Calendar.getInstance();
        Calendar cal_old = Calendar.getInstance();
        File file = new File(media_filepath, "fileMaxResetToday.txt");
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length >= 3) {
                        max_reset_count = Byte.parseByte(split_str[0]);
                        date_today = Long.parseLong(split_str[1]);
                        cal_old.setTimeInMillis(date_today);
                        if (cal_old.getTime().getDate() == cal.getTime().getDate() && cal_old.getTime().getMonth() == cal.getTime().getMonth()
                                && cal_old.getTime().getYear() == cal.getTime().getYear()) {
                            return max_reset_count;
                        } else {
                            return 0;
                        }
                    }
                    break;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;

                file = null;
            }
        }
        return max_reset_count;
    }

    public boolean write_reset_data_today(byte no_times) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }

        File file = new File(media_filepath, "fileResetToday.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(no_times + "," + Calendar.getInstance().getTimeInMillis());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }

        return true;

    }

    public byte read_reset_data_today() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 10;
        }
        String[] split_str;
        String line;
        BufferedReader br = null;
        byte reset_times = 0;
        long date_today = 0;
        Calendar cal = Calendar.getInstance();
        Calendar cal_old = Calendar.getInstance();
        File file = new File(media_filepath, "fileResetToday.txt");
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length >= 2) {
                        reset_times = Byte.parseByte(split_str[0]);
                        date_today = Long.parseLong(split_str[1]);
                        cal_old.setTimeInMillis(date_today);
                        if (cal_old.getTime().getDate() == cal.getTime().getDate() && cal_old.getTime().getMonth() == cal.getTime().getMonth()
                                && cal_old.getTime().getYear() == cal.getTime().getYear()) {
                            return reset_times;
                        } else {
                            return 0;
                        }
                    }
                    break;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;

                file = null;
            }
        }
        return reset_times;
    }

    public boolean write_reset_type() {

        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "fileResetType.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.act_reset_type);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            return false;

        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }

        return true;

    }

    public void read_reset_type_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String line;
        BufferedReader br = null;

        File file = new File(media_filepath, "fileResetType.txt");
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    clsSharedVariables.act_reset_type = Byte.parseByte(line.trim());
                    break;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }

                line = null;
                br = null;

                file = null;
            }
        }
    }

    public synchronized void write_pid_codes_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }

        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-PIDLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, true);

            if (file.length() > 10000) {
                delete_lines_file_pidcodes(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_gps_connect_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        File file = new File(log_filepath, "OBUITS-AVLLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, true);

            if (file.length() > 50000) {
                delete_lines_file_gpsconnect(file, 10 * 10); //commented sumitha
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_camera_connect_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-SCNLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 80000) {
                delete_lines_file_cameraconnect(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_camera1_connect_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-SCNLOG1-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 8000000) {
                delete_lines_file_cameraconnect(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;

        }
    }

    public synchronized void write_camera2_connect_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-SCNLOG2-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 80000) {
                delete_lines_file_cameraconnect(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_camera3_connect_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-SCNLOG3-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);
            if (file.length() > 80000) {
                delete_lines_file_cameraconnect(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;

        }
        //   tv.append("\n\nFile written to "+file);
    }

    public synchronized void write_camera4_connect_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        File file = new File(log_filepath, "OBUITS-SCNLOG4-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);
            if (file.length() > 80000) {
                delete_lines_file_cameraconnect(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + "," + str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_tx_data_from_server(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        File file = new File(log_filepath, "OBUITS-TXDATASERVERLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder(str.length() + 20);
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 10000000) {
                delete_lines_file_txdata_server(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            sb.append(Calendar.getInstance().getTime().toString());
            sb.append(" ");
            sb.append(str);
            pw.println(sb.toString());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
            sb = null;
        }

    }

    public synchronized void write_rx_data_from_server(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-RXDATASERVERLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);
            if (file.length() > 5000000) {
                delete_lines_file_rxdata_server(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;

        }

        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
    }

    public synchronized void write_rx_data_from_server_to_upload_ftp(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        File file = new File(log_filepath, "ftp_upload_pkts.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_gyro_data_from_server(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-GYRODATALOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder(str.length() + 20);
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 10000000) {
                delete_lines_file_txdata_server(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            sb.append(Calendar.getInstance().getTime().toString());
            sb.append(" ");
            sb.append(str);
            pw.println(sb.toString());

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
            sb = null;

        }

    }

    public synchronized void write_displayboard_serialport_log(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-DISPLAYBRD_SERIALLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 10000) {
                delete_lines_file_displayboard_server(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_rs232_serialport_log(String str) {

        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file = new File(log_filepath, "OBUITS-RS232_SERIALLOG-" + getOBUID() + ".log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);

            if (file.length() > 10000) {
                delete_lines_file_displayboard_server(file, 10 * 10);
            }
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;

        }

    }

    public synchronized void write_log_low_memory_data(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(log_filepath, getOBUID() + "_ports.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 50000) {  //50000
                delete_lines_file_lowmem(file, 20 * 10);
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_special_pkts_data(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(log_filepath, getOBUID() + "splPkts.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 50000) {
                delete_lines_file_splpkts(file, 20 * 10);
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_log_gprs_connectivity(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }

        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file = new File(log_filepath, "OBUITS-GPRSLOG-" + getOBUID() + ".log");
            file.setWritable(true);
            file.setReadable(true);

            if (file.length() > 800000) {
                delete_lines_file_gprsconnect(file, 10 * 10);
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_log_gprs_connectivity_ip2(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        File file;
        FileOutputStream f;
        PrintWriter pw;
        try {
            file = new File(log_filepath, "OBUITS-GPRSLOGIP2-" + getOBUID() + ".log");
            file.setWritable(true);
            file.setReadable(true);

            if (file.length() > 800000) {
                delete_lines_file_gprsconnect(file, 10 * 10);
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized void write_log_ppp_data(String str) {
        if (!getHardDriveDetected()) {
            return;
        }
        if (clsSharedVariables.getLogFilesRequired() == false) {
            return;
        }
        if (!log_filepath.exists()) {
            log_filepath.mkdirs();
            log_filepath.setWritable(true, false);
            log_filepath.setReadable(true, false);
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(log_filepath, "OBUITS-PPPLOG-" + getOBUID() + ".log");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 800000) {
                delete_lines_file_networkdata(file, 10 * 10);
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public synchronized boolean write_all_stored_non_gps_pkts() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;
        clsLinkedList obj;
        String str;
        int i;
        int cnt;
        try {
            file = new File(media_filepath, "NonGpsPkts.txt");
            obj = new clsLinkedList();
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            cnt = obj.getLength();
            for (i = 0; i < cnt; i++) {
                str = obj.GetFirstData();
                pw.println(str);
            }
            pw.flush();
            if (cnt > 0) {
                return true;
            }
        } catch (FileNotFoundException e) {

        } catch (Exception ex) {
        } finally {
            try {

                if (pw != null) {
                    pw.close();
                }

                if (f != null) {
                    f.close();
                }

            } catch (IOException e) {

            }

            f = null;
            pw = null;
            obj = null;
            str = null;
            file = null;

        }
        return false;
    }

    public synchronized boolean write_all_stored_non_gps_pkts4() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;
        clsLinkedList obj;
        String str;
        int i;
        int cnt;
        try {
            file = new File(media_filepath, "NonGpsPkts4.txt");
            obj = new clsLinkedList();

            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            cnt = obj.getLength4();
            for (i = 0; i < cnt; i++) {
                str = obj.GetFirstData4();
                pw.println(str);
            }
            pw.flush();
            if (cnt > 0) {
                return true;
            }

        } catch (FileNotFoundException e) {

        } catch (Exception ex) {

        } finally {
            try {

                if (pw != null) {
                    pw.close();
                }

                if (f != null) {
                    f.close();
                }

            } catch (IOException e) {

            }
            f = null;
            pw = null;
            obj = null;
            str = null;
            file = null;
        }
        return false;
    }

    public synchronized boolean write_all_stored_non_gps_pkts5_surat(String data) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;

        int i;
        int cnt;
        try {
            file = new File(media_filepath, "NonGpsPkts5.txt");

            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(data);
            pw.flush();
            pw.flush();
        } catch (Exception ex) {

        } finally {
            try {

                if (pw != null) {
                    pw.close();
                }

                if (f != null) {
                    f.close();
                }

            } catch (IOException e) {

            }
            f = null;
            pw = null;
            // obj = null;

            file = null;
        }
        return false;
    }

    public synchronized boolean write_all_stored_non_gps_pkts5() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;
        clsLinkedList obj;
        String str;
        String[] split_str;
        int i;
        int cnt;
        try {
            file = new File(media_filepath, "NonGpsPkts5.txt");
            obj = new clsLinkedList();
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            cnt = obj.getLength5();
            for (i = 0; i < cnt; i++) {
                str = obj.GetFirstData5();
                pw.println(str);
            }
            pw.flush();
            if (cnt > 0) {
                return true;
            }

        } catch (FileNotFoundException e) {

        } catch (Exception ex) {

        } finally {
            try {

                if (pw != null) {
                    pw.close();
                }

                if (f != null) {
                    f.close();
                }

            } catch (IOException e) {

            }
            f = null;
            pw = null;
            obj = null;
            str = null;
            file = null;
        }
        return false;
    }

    public synchronized boolean write_all_stored_emergency_pkts_ip2() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;
        clsEmergencyPktQueue objEmer = new clsEmergencyPktQueue();
        StringBuilder sb = new StringBuilder();
        byte[] data;
        int j;
        int i;
        int cnt;
        if (objEmer.getLength() <= 0) {
            return false;
        }
        try {
            file = new File(media_filepath, "EmergencyPkts2.txt");
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            cnt = objEmer.getLength();
            for (i = 0; i < cnt; i++) {
                data = objEmer.removeEmergencyPktIP2();
                for (j = 0; j < data.length; j++) {
                    sb.append(data[j]);
                    sb.append(",");
                }
                pw.println(sb.toString());
            }
            pw.flush();
            if (cnt > 0) {
                return true;
            }
        } catch (FileNotFoundException e) {

        } catch (Exception ex) {

        } finally {
            try {

                if (pw != null) {
                    pw.close();
                }

                if (f != null) {
                    f.close();
                }

            } catch (IOException e) {

            }
            f = null;
            pw = null;
            file = null;

        }
        return false;
    }

    public synchronized int read_stored_non_gps_pkts() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 0;
        }
        String line;
        BufferedReader br = null;
        clsLinkedList obj = new clsLinkedList();
        int cnt = 0;
        //Get the text file
        File file = new File(media_filepath, "NonGpsPkts.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    if (line.trim().length() > 5) {
                        obj.addLastData(line);
                        cnt++;
                    }
                }

                br.close();
                br = null;

                file.delete();

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                    obj = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
        }
        return cnt;
    }

    public synchronized int read_stored_non_gps_pkts4() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 0;
        }
        String line;
        BufferedReader br = null;
        clsLinkedList obj = new clsLinkedList();
        int cnt = 0;
        //Get the text file
        File file = new File(media_filepath, "NonGpsPkts4.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    if (line.trim().length() > 5) {
                        obj.addLastData4(line);
                        cnt++;
                    }
                }

                file.delete();

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    obj = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
        }
        return cnt;
    }

    public synchronized int read_stored_non_gps_pkts5() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 0;
        }
        String line;
        BufferedReader br = null;
        clsLinkedList obj = new clsLinkedList();
        int cnt = 0;
        //Get the text file
        File file = new File(media_filepath, "NonGpsPkts5.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    if (line.trim().length() > 5) {
                        obj.addLastData5(line);
                        cnt++;
                    }
                }

                file.delete();

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    obj = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
        }
        return cnt;
    }

    public synchronized int read_stored_emergency_pkts2() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 0;
        }

        String line;
        BufferedReader br = null;
        clsEmergencyPktQueue objEmer = new clsEmergencyPktQueue();
        int cnt = 0;
        String[] split_str;
        int i = 0;
        byte[] data;
        //Get the text file
        File file = new File(media_filepath, "EmergencyPkts2.txt");
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    data = new byte[split_str.length];
                    for (i = 0; i < split_str.length; i++) {
                        data[i] = Byte.parseByte((split_str[i]));
                    }
                    objEmer.addEmergencyPktIP2(data);

                    cnt++;
                }

                br.close();
                br = null;

                file.delete();

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (NumberFormatException e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    objEmer = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
        }
        return cnt;
    }

    public synchronized void read_phone_no_details() {
        byte i = 0;

        String[] split_str;
        String line;
        BufferedReader br = null;

        //Get the text file
        File file = new File(media_filepath, "phonenum.txt");

        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");

                    if (split_str.length >= 2) {
                        setPhoneNo(i, split_str[0]);
                        
                        setPhoneName(i, split_str[1]);
                        if (split_str.length >= 4) {
                            if (lang_type == LANG_HINDI) {
                                if (split_str[2].trim() == null || split_str[2].trim().equals("")) {
                                    setPhoneName(i, split_str[1]);
                                } else {
                                    setPhoneName(i, split_str[2]);
                                }
                            } else if (lang_type == LANG_REG) {
                                if (split_str[3].trim() == null || split_str[3].trim().equals("")) {
                                    setPhoneName(i, split_str[1]);
                                } else {
                                    setPhoneName(i, split_str[3]);
                                }
                            }
                        }
                        i++;
                    }
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                setPhoneNoCnt(i);
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
            file = new File(config_filepath, "phonenum.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "phonenum.txt", config_filepath.getAbsolutePath() + "/" + "phonenum.txt");
            }
        } else {
            file = new File(config_filepath, "phonenum.txt");
            if (file.exists()) {

                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));

                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",");

                        if (split_str.length >= 2) {
                            setPhoneNo(i, split_str[0]);
                            setPhoneName(i, split_str[1]);
                            if (split_str.length >= 4) {
                                if (lang_type == LANG_HINDI) {
                                    if (split_str[2].trim() == null || split_str[2].trim().equals("")) {
                                        setPhoneName(i, split_str[1]);
                                    } else {
                                        setPhoneName(i, split_str[2]);
                                    }
                                } else if (lang_type == LANG_REG) {
                                    if (split_str[3].trim() == null || split_str[3].trim().equals("")) {
                                        setPhoneName(i, split_str[1]);
                                    } else {
                                        setPhoneName(i, split_str[3]);
                                    }
                                }
                            }
                            i++;
                        }
                    }

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    setPhoneNoCnt(i);
                    try {
                        if (br != null) {
                            br.close();
                        }
                        split_str = null;
                        line = null;
                        br = null;
                        file = null;
                    } catch (IOException e) {

                    }
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "phonenum.txt", media_filepath.getAbsolutePath() + "/" + "phonenum.txt");

                }
            }
        }
    }

    public synchronized void read_incoming_call_name(String phnum) {
        byte i = 0;
        String[] split_str;
        String line;
        String str = null;
        BufferedReader br = null;
        File file = new File(media_filepath, "phonenum.txt");
        clsSharedVariables.call_in_mobile_name = "";
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    str = "91" + split_str[0].trim();
                    if (str.equals(phnum)) {
                        clsSharedVariables.call_in_mobile_name = split_str[1].trim();
                    }
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }

        } else {
            file = new File(config_filepath, "phonenum.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",");
                        str = "91" + split_str[0].trim();
                        if (str.equals(phnum)) {
                            clsSharedVariables.call_in_mobile_name = split_str[1].trim();
                        }
                    }
                } catch (Exception e) {
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                        split_str = null;
                        line = null;
                        br = null;
                        file = null;
                    } catch (IOException e) {
                    }
                }

            }
        }
    }

    public synchronized void read_label_names_details() {
        int i = 0;

        String[] split_str;
        String line;
        BufferedReader br = null;

        //Get the text file
        File file = new File(media_filepath, "names.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                //lang_type=LANG_REG;
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length >= 3) {
                        if (lang_type == LANG_ENGLISH) {
                            label_names[i] = split_str[0];
                        } else if (lang_type == LANG_HINDI) {
                            label_names[i] = split_str[1];
                        } else {
                            label_names[i] = split_str[2];
                        }
                    }
                    i++;
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {

                try {
                    if (br != null) {
                        br.close();
                    }
                    label_names_cnt = i;
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
            file = new File(config_filepath, "names.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "names.txt", config_filepath.getAbsolutePath() + "/" + "names.txt");
            }
        } else {
            file = new File(config_filepath, "names.txt");
            if (file.exists()) {

                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    //lang_type=LANG_REG;
                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",");

                        if (split_str.length >= 3) {
                            if (lang_type == LANG_ENGLISH) {
                                label_names[i] = split_str[0];
                            } else if (lang_type == LANG_HINDI) {
                                label_names[i] = split_str[1];
                            } else {
                                label_names[i] = split_str[2];
                            }
                        }
                        i++;
                    }

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {

                    try {
                        if (br != null) {
                            br.close();
                        }
                        label_names_cnt = i;
                        split_str = null;
                        line = null;
                        br = null;
                        file = null;
                    } catch (IOException e) {

                    }
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "names.txt", media_filepath.getAbsolutePath() + "/" + "names.txt");
                }
            }
        }
    }

    public synchronized void read_pre_def_location_points() {
        byte i = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        boolean first_line = false;

        //Get the text file
        File file = new File(media_filepath, "PreDefLoc.txt");

        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                lineNumberReader.skip(Long.MAX_VALUE);
                int lines = lineNumberReader.getLineNumber();

                objPreDefLocPoints.latitude = new double[lines];
                objPreDefLocPoints.longitude = new double[lines];

                while ((line = br.readLine()) != null) {
                    if (first_line == false) {
                        first_line = true;
                        objPreDefLocPoints.geofence_pre_def_loc = Integer.parseInt(line);
                    } else {
                        split_str = line.trim().split(",");
                        if (split_str.length >= 3) {
                            try {
                                objPreDefLocPoints.latitude[i] = Double.parseDouble(split_str[1]);
                                objPreDefLocPoints.longitude[i] = Double.parseDouble(split_str[2]);
                                i++;
                            } catch (Exception ex) {

                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                clsBusStopDetection.no_pre_def_loc_points = i;
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
            file = new File(config_filepath, "PreDefLoc.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "PreDefLoc.txt", config_filepath.getAbsolutePath() + "/" + "PreDefLoc.txt");
            }
        } else {
            file = new File(config_filepath, "PreDefLoc.txt");

            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                    lineNumberReader.skip(Long.MAX_VALUE);
                    int lines = lineNumberReader.getLineNumber();

                    objPreDefLocPoints.latitude = new double[lines];
                    objPreDefLocPoints.longitude = new double[lines];

                    while ((line = br.readLine()) != null) {
                        if (first_line == false) {
                            first_line = true;
                            objPreDefLocPoints.geofence_pre_def_loc = Integer.parseInt(line);
                        } else {
                            split_str = line.trim().split(",");
                            if (split_str.length >= 3) {
                                try {
                                    objPreDefLocPoints.latitude[i] = Double.parseDouble(split_str[1]);
                                    objPreDefLocPoints.longitude[i] = Double.parseDouble(split_str[2]);
                                    i++;
                                } catch (Exception ex) {

                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    clsBusStopDetection.no_pre_def_loc_points = i;
                    try {
                        if (br != null) {
                            br.close();
                        }
                        split_str = null;
                        line = null;
                        br = null;
                        file = null;
                    } catch (IOException e) {

                    }
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "PreDefLoc.txt", media_filepath.getAbsolutePath() + "/" + "PreDefLoc.txt");

                }
            }
        }
    }

    public synchronized void read_traffic_points() {
        byte i = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        boolean first_line = false;

        //Get the text file
        File file = new File(media_filepath, "TSPPoint.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                lineNumberReader.skip(Long.MAX_VALUE);
                int lines = lineNumberReader.getLineNumber();
                objTrafficPoints.latitude = new double[lines];
                objTrafficPoints.longitude = new double[lines];
                while ((line = br.readLine()) != null) {

                    if (first_line == false) {
                        first_line = true;
                        objTrafficPoints.geofence_traffic = Integer.parseInt(line);

                    } else {
                        split_str = line.trim().split(",");
                        if (split_str.length >= 3) {
                            try {
                                objTrafficPoints.latitude[i] = Double.parseDouble(split_str[1]);
                                objTrafficPoints.longitude[i] = Double.parseDouble(split_str[2]);
                                i++;
                            } catch (Exception ex) {

                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                no_traffic_points = i;
                try {
                    if (br != null) {
                        br.close();
                    }
                    split_str = null;
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {

                }
            }
            file = new File(config_filepath, "TSPPoint.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "TSPPoint.txt", config_filepath.getAbsolutePath() + "/" + "TSPPoint.txt");
            }
        } else {
            file = new File(config_filepath, "TSPPoint.txt");
            if (file.exists()) {

                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                    lineNumberReader.skip(Long.MAX_VALUE);
                    int lines = lineNumberReader.getLineNumber();
                    objTrafficPoints.latitude = new double[lines];
                    objTrafficPoints.longitude = new double[lines];
                    while ((line = br.readLine()) != null) {

                        if (first_line == false) {
                            first_line = true;
                            objTrafficPoints.geofence_traffic = Integer.parseInt(line);

                        } else {
                            split_str = line.trim().split(",");
                            if (split_str.length >= 3) {
                                try {
                                    objTrafficPoints.latitude[i] = Double.parseDouble(split_str[1]);
                                    objTrafficPoints.longitude[i] = Double.parseDouble(split_str[2]);
                                    i++;
                                } catch (Exception ex) {

                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    no_traffic_points = i;
                    try {
                        if (br != null) {
                            br.close();
                        }
                        split_str = null;
                        line = null;
                        br = null;
                        file = null;
                    } catch (IOException e) {

                    }
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "TSPPoint.txt", media_filepath.getAbsolutePath() + "/" + "TSPPoint.txt");

                }
            }
        }
    }

    public synchronized boolean check_phone_no_details(String phone_no) {
        byte i;
        for (i = 0; i < getPhoneNoCnt(); i++) {
            if (phone_no.contains(getPhoneNo(i))) {
                return true;
            }
        }

        return false;
    }

    public synchronized boolean write_storedData_Params_file(String file_name, int cnt) {

        File file = new File(media_filepath, "StoredDataParams.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
            current_gps_stored_file = file_name;
            current_gps_stored_file_cnt = (byte) cnt;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            file = null;
            f = null;
            pw = null;

        }

        return true;
    }

    public synchronized boolean write_storedData_Params_file2(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "StoredDataParams2.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
            current_gps_stored_file2 = file_name;
            current_gps_stored_file_cnt2 = (byte) cnt;
        } catch (FileNotFoundException e) {

            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception ex) {
            return false;
        } finally {
            file = null;
            f = null;
            pw = null;

        }

        return true;
    }

    public synchronized boolean write_storedData_Params_file3(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "StoredDataParams3.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
            current_gps_stored_file3 = file_name;
            current_gps_stored_file_cnt3 = (byte) cnt;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        return true;
    }

    public synchronized boolean write_storedData_Params_file4(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "StoredDataParams4.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
            current_gps_stored_file4 = file_name;
            current_gps_stored_file_cnt4 = (byte) cnt;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        return true;
    }

    public synchronized boolean write_storedData_Params_file5(String file_name, int cnt) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "StoredDataParams5.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(file_name + "," + cnt);
            pw.flush();
            pw.close();
            f.close();
            current_gps_stored_file5 = file_name;
            current_gps_stored_file_cnt5 = (byte) cnt;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        return true;
    }

    public synchronized void read_storedData_Params_file() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String line;
        BufferedReader br = null;
        String[] split_str;
        File file = new File(media_filepath, "StoredDataParams.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length > 1) {
                        current_gps_stored_file = split_str[0];
                        current_gps_stored_file_cnt = Byte.parseByte(split_str[1]);
                    }
                    break;
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                } catch (IOException e) {

                }
                br = null;
                file = null;
                split_str = null;
                line = null;
            }
        }
    }

    public synchronized void read_storedData_Params_file2() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String line;
        BufferedReader br = null;
        String[] split_str;
        File file = new File(media_filepath, "StoredDataParams2.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length > 1) {
                        current_gps_stored_file2 = split_str[0];
                        current_gps_stored_file_cnt2 = Byte.parseByte(split_str[1]);
                    }
                    break;
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                } catch (IOException e) {

                }
                br = null;
                file = null;
                split_str = null;
                line = null;
            }
        }
    }

    public synchronized void read_storedData_Params_file3() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String line;
        BufferedReader br = null;
        String[] split_str;
        File file = new File(media_filepath, "StoredDataParams3.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length > 1) {
                        current_gps_stored_file3 = split_str[0];
                        current_gps_stored_file_cnt3 = Byte.parseByte(split_str[1]);
                    }
                    break;
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                } catch (IOException e) {

                }
                br = null;
                file = null;
                split_str = null;
                line = null;
            }
        }
    }

    public synchronized void read_storedData_Params_file4() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String line;
        BufferedReader br = null;
        String[] split_str;
        File file = new File(media_filepath, "StoredDataParams4.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length > 1) {
                        current_gps_stored_file4 = split_str[0];
                        current_gps_stored_file_cnt4 = Byte.parseByte(split_str[1]);
                    }
                    break;
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                } catch (IOException e) {

                }
                br = null;
                file = null;
                split_str = null;
                line = null;
            }
        }
    }

    public synchronized void read_panic_messages() {
        String line;
        FileInputStream fs = null;
        Scanner scanner = null;
        String[] split_str;
        byte[] bytes;
        int j = 0;
        byte cnt = 0;
        int l = 0;
        String reg_name;
        String[] reg_name_split;

        File file = new File(media_filepath, "codedmsg.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                fs = new FileInputStream(file);
                scanner = new Scanner(fs);

                while ((scanner.hasNextLine())) {
                    try {
                        line = scanner.nextLine();
                        if (!line.trim().equals("")) {
                            split_str = line.split(",", -1);
                            panic_message_id[cnt] = Byte.parseByte(split_str[0]);
                            if (lang_type == LANG_ENGLISH) {
                                panic_message[cnt] = split_str[1].trim();
                            } else if (lang_type == LANG_HINDI) {
                                if (split_str.length >= 3) {
                                    if (!split_str[2].trim().equals("")) {
                                        try {
                                            reg_name = split_str[2];
                                            if (!reg_name.trim().equals("")) {
                                                if (!reg_name.contains("\\")) {
                                                    reg_name = split_str[2];
                                                } else {
                                                    reg_name = reg_name.replace("\\", "");
                                                    reg_name_split = reg_name.split("u", -1);
                                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                                    l = 0;
                                                    for (j = 0; j < reg_name_split.length; j++) {
                                                        if (!reg_name_split[j].trim().equals("")) {
                                                            if (reg_name_split[j].length() == 4) {
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                            }
                                                        }
                                                    }
                                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                                }
                                            }
                                        } catch (Exception ex) {
                                            reg_name = split_str[0];
                                        }
                                        panic_message[cnt] = reg_name;
                                    } else {
                                        panic_message[cnt] = split_str[1];
                                    }
                                } else {
                                    panic_message[cnt] = split_str[1];
                                }
                            } else {
                                if (split_str.length > 3) {
                                    if (!split_str[3].trim().equals("")) {
                                        try {
                                            reg_name = split_str[3];
                                            if (!reg_name.trim().equals("")) {

                                                if (!reg_name.contains("\\")) {
                                                    reg_name = split_str[3];
                                                } else {
                                                    reg_name = reg_name.replace("\\", "");
                                                    reg_name_split = reg_name.split("u", -1);
                                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                                    l = 0;
                                                    for (j = 0; j < reg_name_split.length; j++) {
                                                        if (!reg_name_split[j].trim().equals("")) {
                                                            if (reg_name_split[j].length() == 4) {
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                            }
                                                        }
                                                    }
                                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                                }
                                            }
                                        } catch (Exception ex) {
                                            reg_name = split_str[0];
                                        }

                                        panic_message[cnt] = reg_name;
                                    } else {
                                        panic_message[cnt] = split_str[1];
                                    }
                                } else {
                                    panic_message[cnt] = split_str[1];
                                }
                            }
                            cnt++;
                        }

                    } catch (Exception ex) {

                    }
                }

            } catch (FileNotFoundException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (scanner != null) {
                        scanner.close();
                    }
                    if (fs != null) {
                        fs.close();
                    }

                } catch (Exception e) {

                }
                scanner = null;
                file = null;
                fs = null;
                if (cnt > 0) {
                    no_panic_messages = cnt;
                }

                line = null;
                bytes = null;
                split_str = null;
                reg_name = null;
                reg_name_split = null;
            }
            file = new File(config_filepath, "codedmsg.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "codedmsg.txt", config_filepath.getAbsolutePath() + "/" + "codedmsg.txt");
            }
        } else {
            file = new File(config_filepath, "codedmsg.txt");
            if (file.exists()) {

                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    fs = new FileInputStream(file);
                    scanner = new Scanner(fs);
                    while ((scanner.hasNextLine())) {
                        try {
                            line = scanner.nextLine();
                            if (!line.trim().equals("")) {
                                split_str = line.split(",", -1);
                                panic_message_id[cnt] = Byte.parseByte(split_str[0]);
                                if (lang_type == LANG_ENGLISH) {
                                    panic_message[cnt] = split_str[1].trim();
                                } else if (lang_type == LANG_HINDI) {
                                    if (split_str.length >= 3) {
                                        if (!split_str[2].trim().equals("")) {
                                            try {
                                                reg_name = split_str[2];
                                                if (!reg_name.trim().equals("")) {
                                                    if (!reg_name.contains("\\")) {
                                                        reg_name = split_str[2];
                                                    } else {
                                                        reg_name = reg_name.replace("\\", "");
                                                        reg_name_split = reg_name.split("u", -1);
                                                        bytes = new byte[reg_name_split.length * 2 - 2];
                                                        l = 0;
                                                        for (j = 0; j < reg_name_split.length; j++) {
                                                            if (!reg_name_split[j].trim().equals("")) {
                                                                if (reg_name_split[j].length() == 4) {
                                                                    bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                                    bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                                }
                                                            }
                                                        }
                                                        reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                                    }
                                                }
                                            } catch (Exception ex) {
                                                reg_name = split_str[0];
                                            }
                                            panic_message[cnt] = reg_name;
                                        } else {
                                            panic_message[cnt] = split_str[1];
                                        }
                                    } else {
                                        panic_message[cnt] = split_str[1];
                                    }
                                } else {
                                    if (split_str.length > 3) {
                                        if (!split_str[3].trim().equals("")) {
                                            try {
                                                reg_name = split_str[3];
                                                if (!reg_name.trim().equals("")) {

                                                    if (!reg_name.contains("\\")) {
                                                        reg_name = split_str[3];
                                                    } else {
                                                        reg_name = reg_name.replace("\\", "");
                                                        reg_name_split = reg_name.split("u", -1);
                                                        bytes = new byte[reg_name_split.length * 2 - 2];
                                                        l = 0;
                                                        for (j = 0; j < reg_name_split.length; j++) {
                                                            if (!reg_name_split[j].trim().equals("")) {
                                                                if (reg_name_split[j].length() == 4) {
                                                                    bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                                    bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                                }
                                                            }
                                                        }
                                                        reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);
                                                    }
                                                }
                                            } catch (Exception ex) {
                                                reg_name = split_str[0];
                                            }

                                            panic_message[cnt] = reg_name;
                                        } else {
                                            panic_message[cnt] = split_str[1];
                                        }
                                    } else {
                                        panic_message[cnt] = split_str[1];
                                    }
                                }
                                cnt++;
                            }

                        } catch (Exception ex) {

                        }
                    }

                } catch (FileNotFoundException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        if (scanner != null) {
                            scanner.close();
                        }
                        if (fs != null) {
                            fs.close();
                        }

                    } catch (Exception e) {

                    }
                    scanner = null;
                    file = null;
                    fs = null;
                    if (cnt > 0) {
                        no_panic_messages = cnt;
                    }
                    line = null;
                    bytes = null;
                    split_str = null;
                    reg_name = null;
                    reg_name_split = null;
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "codedmsg.txt", media_filepath.getAbsolutePath() + "/" + "codedmsg.txt");
                }
            }
        }

    }

    public synchronized String read_special_messages() {
        String line;
        String finalStr = "";
        FileInputStream fs = null;
        Scanner scanner = null;
        String[] split_str;
        byte[] bytes;
        String reg_name;
        String[] reg_name_split;
        short l = 0;
        short j = 0;
        String eng_name = "";

        File file = new File(media_filepath, "speclmsg.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                fs = new FileInputStream(file);
                scanner = new Scanner(fs);
                while ((scanner.hasNextLine())) {
                    try {
                        line = scanner.nextLine();
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            if (lang_type == LANG_ENGLISH) {
                                finalStr = finalStr + "," + split_str[0];
                            } else if (lang_type == LANG_HINDI) {
                                eng_name = split_str[0];
                                if (!"".equals(split_str[1].trim())) {
                                    try {
                                        reg_name = split_str[1];
                                        if (!reg_name.trim().equals("")) {
                                            if (!reg_name.contains("\\")) {
                                                reg_name = split_str[3];
                                            } else {
                                                reg_name = reg_name.replace("\\", "");
                                                reg_name_split = reg_name.split("u", -1);
                                                bytes = new byte[reg_name_split.length * 2 - 2];
                                                l = 0;
                                                for (j = 0; j < reg_name_split.length; j++) {
                                                    if (!reg_name_split[j].trim().equals("")) {
                                                        if (reg_name_split[j].length() == 4) {
                                                            bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                            bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                        }
                                                    }
                                                }
                                                reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);
                                                if (reg_name.trim().equals("")) {
                                                    reg_name = eng_name;
                                                }

                                            }
                                        }
                                    } catch (Exception ex) {
                                        reg_name = split_str[0];
                                    }
                                    finalStr = finalStr + "," + reg_name;
                                } else {
                                    finalStr = finalStr + "," + split_str[0];
                                }
                            } else {
                                eng_name = split_str[0];
                                if (!"".equals(split_str[2].trim())) {
                                    try {
                                        reg_name = split_str[2];
                                        if (!reg_name.trim().equals("")) {
                                            if (!reg_name.contains("\\")) {
                                                reg_name = split_str[3];
                                            } else {
                                                reg_name = reg_name.replace("\\", "");
                                                reg_name_split = reg_name.split("u", -1);
                                                bytes = new byte[reg_name_split.length * 2 - 2];
                                                l = 0;
                                                for (j = 0; j < reg_name_split.length; j++) {
                                                    if (!reg_name_split[j].trim().equals("")) {
                                                        if (reg_name_split[j].length() == 4) {
                                                            bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                            bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                        }
                                                    }
                                                }
                                                reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                                if (reg_name.trim().equals("")) {
                                                    reg_name = eng_name;
                                                }

                                            }
                                        }
                                    } catch (Exception ex) {
                                        reg_name = split_str[0];
                                    }
                                    finalStr = finalStr + "," + reg_name;
                                } else {
                                    finalStr = finalStr + "," + split_str[0];
                                }
                            }
                            if (split_str.length >= 4) {
                                finalStr = finalStr + "^" + split_str[3];
                            }
                            finalStr = finalStr;//+ ",";
                        }
                    } catch (Exception ex) {

                    }
                }
                finalStr = finalStr.replaceFirst(",", "");
                return finalStr;

            } catch (FileNotFoundException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (scanner != null) {
                        scanner.close();
                    }
                    if (fs != null) {
                        fs.close();
                    }
                } catch (IOException e) {

                }
                scanner = null;
                fs = null;
                file = null;
                bytes = null;
                split_str = null;
                line = null;
            }
            file = new File(config_filepath, "speclmsg.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "speclmsg.txt", config_filepath.getAbsolutePath() + "/" + "speclmsg.txt");
            }
        } else {
            file = new File(config_filepath, "speclmsg.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    fs = new FileInputStream(file);
                    scanner = new Scanner(fs);
                    while ((scanner.hasNextLine())) {
                        try {

                            line = scanner.nextLine();
                            if (!line.trim().equals("")) {
                                split_str = line.split(",");
                                if (lang_type == LANG_ENGLISH) {
                                    finalStr = finalStr + "," + split_str[0];
                                } else if (lang_type == LANG_HINDI) {
                                    eng_name = split_str[0];
                                    if (!"".equals(split_str[1].trim())) {
                                        try {
                                            reg_name = split_str[1];
                                            if (!reg_name.trim().equals("")) {
                                                if (!reg_name.contains("\\")) {
                                                    reg_name = split_str[3];
                                                } else {
                                                    reg_name = reg_name.replace("\\", "");
                                                    reg_name_split = reg_name.split("u", -1);
                                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                                    l = 0;
                                                    for (j = 0; j < reg_name_split.length; j++) {
                                                        if (!reg_name_split[j].trim().equals("")) {
                                                            if (reg_name_split[j].length() == 4) {
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                            }
                                                        }
                                                    }
                                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);
                                                    if (reg_name.trim().equals("")) {
                                                        reg_name = eng_name;
                                                    }

                                                }
                                            }
                                        } catch (Exception ex) {
                                            reg_name = split_str[0];
                                        }
                                        finalStr = finalStr + "," + reg_name;
                                    } else {
                                        finalStr = finalStr + "," + split_str[0];
                                    }
                                } else {
                                    eng_name = split_str[0];
                                    if (!"".equals(split_str[2].trim())) {
                                        try {
                                            reg_name = split_str[2];
                                            if (!reg_name.trim().equals("")) {
                                                if (!reg_name.contains("\\")) {
                                                    reg_name = split_str[3];
                                                } else {
                                                    reg_name = reg_name.replace("\\", "");
                                                    reg_name_split = reg_name.split("u", -1);
                                                    bytes = new byte[reg_name_split.length * 2 - 2];
                                                    l = 0;
                                                    for (j = 0; j < reg_name_split.length; j++) {
                                                        if (!reg_name_split[j].trim().equals("")) {
                                                            if (reg_name_split[j].length() == 4) {
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(0, 2));
                                                                bytes[l++] = Byte.parseByte(reg_name_split[j].substring(2, 4));
                                                            }
                                                        }
                                                    }
                                                    reg_name = new String(bytes, 0, l, StandardCharsets.UTF_16);

                                                    if (reg_name.trim().equals("")) {
                                                        reg_name = eng_name;
                                                    }

                                                }
                                            }
                                        } catch (Exception ex) {
                                            reg_name = split_str[0];
                                        }
                                        finalStr = finalStr + "," + reg_name;
                                    } else {
                                        finalStr = finalStr + "," + split_str[0];
                                    }
                                }
                                if (split_str.length >= 4) {
                                    finalStr = finalStr + "^" + split_str[3];
                                }
                                finalStr = finalStr;//+ ",";
                            }
                        } catch (Exception ex) {

                        }
                    }
                    finalStr = finalStr.replaceFirst(",", "");
                    return finalStr;

                } catch (FileNotFoundException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        if (scanner != null) {
                            scanner.close();
                        }
                        if (fs != null) {
                            fs.close();
                        }
                    } catch (IOException e) {

                    }
                    scanner = null;
                    fs = null;
                    file = null;
                    bytes = null;
                    split_str = null;
                    line = null;
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "speclmsg.txt", media_filepath.getAbsolutePath() + "/" + "speclmsg.txt");

                }
            }
        }
        return "";
    }

    public synchronized String read_driver_delay_reason_messages() {
        String line;
        String finalStr = "";
        FileInputStream fs = null;
        Scanner scanner = null;
        String[] split_str;
        byte[] bytes;
        int i = 0;
        int k = 0;
        File file = new File(media_filepath, "drivrlst.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                fs = new FileInputStream(file);
                scanner = new Scanner(fs);
                while ((scanner.hasNextLine())) {
                    try {
                        line = scanner.nextLine();
                        if (!line.trim().equals("")) {

                            split_str = line.split(",");
                            finalStr = finalStr + "," + split_str[0];
                            if (lang_type == LANG_ENGLISH) {
                                finalStr = finalStr + "," + split_str[1];
                            } else {
                                if (split_str.length > 2) {

                                    try {
                                        split_str[2] = split_str[2].replace("\\", "");
                                        split_str = split_str[2].split("u", -1);
                                        bytes = new byte[split_str.length * 2 - 2];
                                        k = 0;
                                        for (i = 0; i < split_str.length; i++) {
                                            if (!split_str[i].trim().equals("")) {
                                                if (split_str[i].length() == 4) {
                                                    bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                    bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                }
                                            }
                                        }
                                        finalStr = finalStr + "," + new String(bytes, 0, k, StandardCharsets.UTF_16);

                                    } catch (Exception ex) {

                                    }

                                } else {
                                    finalStr = finalStr + "," + split_str[1];
                                }
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
                finalStr = finalStr.replaceFirst(",", "");
                return finalStr;
            } catch (FileNotFoundException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (scanner != null) {
                        scanner.close();
                    }
                    if (fs != null) {
                        fs.close();
                    }
                } catch (IOException e) {

                }
                scanner = null;
                fs = null;
                file = null;
                bytes = null;
                split_str = null;
                line = null;
            }
            file = new File(config_filepath, "drivrlst.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "drivrlst.txt", config_filepath.getAbsolutePath() + "/" + "drivrlst.txt");
            }
        } else {
            file = new File(config_filepath, "drivrlst.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    fs = new FileInputStream(file);
                    scanner = new Scanner(fs);
                    while ((scanner.hasNextLine())) {
                        try {
                            line = scanner.nextLine();
                            if (!line.trim().equals("")) {

                                split_str = line.split(",");
                                finalStr = finalStr + "," + split_str[0];
                                if (lang_type == LANG_ENGLISH) {
                                    finalStr = finalStr + "," + split_str[1];
                                } else {
                                    if (split_str.length > 2) {

                                        try {
                                            split_str[2] = split_str[2].replace("\\", "");
                                            split_str = split_str[2].split("u", -1);
                                            bytes = new byte[split_str.length * 2 - 2];
                                            k = 0;
                                            for (i = 0; i < split_str.length; i++) {
                                                if (!split_str[i].trim().equals("")) {
                                                    if (split_str[i].length() == 4) {
                                                        bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                        bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                    }
                                                }
                                            }
                                            finalStr = finalStr + "," + new String(bytes, 0, k, StandardCharsets.UTF_16);

                                        } catch (Exception ex) {

                                        }

                                    } else {
                                        finalStr = finalStr + "," + split_str[1];
                                    }
                                }
                            }
                        } catch (Exception ex) {

                        }
                    }
                    finalStr = finalStr.replaceFirst(",", "");
                    return finalStr;
                } catch (FileNotFoundException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        if (scanner != null) {
                            scanner.close();
                        }
                        if (fs != null) {
                            fs.close();
                        }
                    } catch (IOException e) {

                    }
                    scanner = null;
                    fs = null;
                    file = null;
                    bytes = null;
                    split_str = null;
                    line = null;
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "drivrlst.txt", media_filepath.getAbsolutePath() + "/" + "drivrlst.txt");
                }
            }
        }
        return "";
    }

    public synchronized void read_special_msg_info() {

        String[] split_str;
        String line;
        BufferedReader br = null;

//Get the text file
        File file = new File(media_filepath, "special_msg.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    set_special_msg_status(Boolean.parseBoolean(split_str[0]));
                    set_special_msg_no(Byte.parseByte(split_str[1]));

                }

                copyFile(media_filepath.getAbsolutePath() + "/" + "special_msg.txt", config_filepath.getAbsolutePath() + "/" + "speclmsg.txt");

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    br = null;
                    file = null;
                    line = null;
                    split_str = null;
                } catch (IOException e) {

                }
            }
        } else {
            file = new File(config_filepath, "special_msg.txt");
            if (file.exists()) {

                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));

                    while ((line = br.readLine()) != null) {
                        split_str = line.split(",", -1);
                        set_special_msg_status(Boolean.parseBoolean(split_str[0]));
                        set_special_msg_no(Byte.parseByte(split_str[1]));

                    }

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                        br = null;
                        file = null;
                        line = null;
                        split_str = null;
                    } catch (IOException e) {

                    }
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "special_msg.txt", media_filepath.getAbsolutePath() + "/" + "speclmsg.txt");
                }
            }
        }

    }

    public synchronized void write_special_msg_info() {
        String data;
        File file = new File(media_filepath, "special_msg.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                file.setReadable(true, false);
                file.setWritable(true, false);
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                data = get_special_msg_status() + "," + String.valueOf(get_special_msg_no());
                pw.println(data);
                pw.flush();
                pw.close();
                f.close();
            }
            file = new File(config_filepath, "special_msg.txt");
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);

            data = get_special_msg_status() + "," + String.valueOf(get_special_msg_no());
            pw.println(data);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {

        } finally {
            pw = null;
            f = null;
            file = null;
            data = null;
        }
        //   tv.append("\n\nFile written to "+file);
    }

    public boolean write_password_change(String str) {

        String data;
        File file = new File(media_filepath, "fileChangePwd.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                file.setReadable(true, false);
                file.setWritable(true, false);
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                pw.println(str);
                pw.flush();
                pw.close();
                f.close();
            }
            file = new File(config_filepath, "fileChangePwd.txt");
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            return false;
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }
        return true;

    }

    public void read_password_change() {
        String line;
        BufferedReader br = null;
        //Get the text file
        File file = new File(media_filepath, "fileChangePwd.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    configuration_pwd = line;
                    break;
                }
            } catch (FileNotFoundException e) {
                write_log_low_memory_data("fileChangePwd.txt " + e.getMessage());
            } catch (IOException e) {
                write_log_low_memory_data("fileChangePwd.txt " + e.getMessage());
            } catch (Exception e) {
                write_log_low_memory_data("fileChangePwd.txt " + e.getMessage());
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;
            }
            file = new File(config_filepath, "fileChangePwd.txt");
            if (!file.exists()) {
                copyFile(media_filepath.getAbsolutePath() + "/" + "fileChangePwd.txt", config_filepath.getAbsolutePath() + "/" + "fileChangePwd.txt");
            }
        } else {
            file = new File(config_filepath, "fileChangePwd.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        configuration_pwd = line;
                        break;
                    }
                } catch (FileNotFoundException e) {
                    write_log_low_memory_data("fileChangePwd.txt " + e.getMessage());
                } catch (IOException e) {
                    write_log_low_memory_data("fileChangePwd.txt " + e.getMessage());
                } catch (Exception e) {
                    write_log_low_memory_data("fileChangePwd.txt " + e.getMessage());
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                    line = null;
                    br = null;
                    file = null;
                }
                if (clsSharedVariables.getHardDriveDetected()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "fileChangePwd.txt", media_filepath.getAbsolutePath() + "/" + "fileChangePwd.txt");
                }
            }
        }
    }

    public void delete_lines_file_storeddata4(String filename, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......
            File targetFile = new File(media_filepath, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempstored4.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_storeddata5(String filename, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......
            File targetFile = new File(media_filepath, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempstored5.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_displayboard_server(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempdisplaylog.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_rxdata_server(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempgprsrx.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_txdata_server(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempgprstx.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_rxdata_server4(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempgprsrx4.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_txdata_server4(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempgprstx4.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_pidcodes(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "temp.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_gpsconnect(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempgps.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public synchronized void delete_lines_file_cameraconnect(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempcamera.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_lowmem(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "templowmem.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_splpkts(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempSplPkts.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;

            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_gprsconnect(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempgprs.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_networkdata(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......

            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempnetwork.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file(File targetFile, int no_lines, String del_file) {
        try {
            //Here I am opening the target File named 1.txt......
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, del_file);
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                printTemp = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_nongpsdata(File targetFile, int no_lines) {
        try {
            //Here I am opening the target File named 1.txt......
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));

                //Opening a Temp file.......
                File tempFile = new File(media_filepath, "tempnongps.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);

                //Here is the important part... skipping first 20 lines....
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {

                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                //closing the files after finished copying from target file to temp file....
                targetBuf.close();
                printTemp.close();
                //Now replace and delete the target file with temp file....
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                printTemp = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public String getCurSrcDesStopName(short l_cur_trip_no) {
        short i = 0;
        short k;
        String[] split_str = null;
        String line;
        BufferedReader br = null;
        String src_name = "";
        String des_name = "";
        int j = 0;

        String eng_name = "";
        byte[] bytes;
        try {
            if (l_cur_trip_no < getCurSchNoTrips()) {
                for (j = 0; j < getNoRoutes(); j++) {
                    if (objRouteMasFiles[j].route_no.equals(getCurSchRouteNo(l_cur_trip_no))) {
                        File file = new File(route_filepath, objRouteMasFiles[j].bdc_file_name);
                        File f = new File(main_route_filepath, objRouteMasFiles[j].bdc_file_name);
                        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                            //Read text from file
                            file.setReadable(true, false);
                            file.setWritable(true, false);
                            try {
                                br = new BufferedReader(new FileReader(file));
                                while ((line = br.readLine()) != null) {
                                    k = 0;
                                    split_str = line.split(",");
                                    try {
                                        if (!split_str[0].equals("")) {
                                            if (i == 0) {
                                                if (lang_type == LANG_ENGLISH) {
                                                    src_name = split_str[0];
                                                } else if (lang_type == LANG_HINDI) {
                                                    eng_name = split_str[0];
                                                    if (split_str.length > 1) {
                                                        if (split_str[1].trim().equals("")) {
                                                            src_name = split_str[0];
                                                        } else {
                                                            try {
                                                                if (!split_str[1].contains("\\")) {
                                                                    src_name = split_str[1];
                                                                } else {
                                                                    split_str[1] = split_str[1].replace("\\", "");
                                                                    split_str = split_str[1].split("u", -1);
                                                                    bytes = new byte[split_str.length * 2 - 2];
                                                                    k = 0;
                                                                    for (i = 0; i < split_str.length; i++) {
                                                                        if (!split_str[i].trim().equals("")) {
                                                                            if (split_str[i].length() == 4) {
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                            }
                                                                        }
                                                                    }
                                                                    src_name = new String(bytes, 0, k, StandardCharsets.UTF_16);
                                                                    if (src_name == null || src_name.trim().equals("")) {
                                                                        src_name = eng_name;
                                                                    }
                                                                }

                                                            } catch (Exception ex) {

                                                            }
                                                        }
                                                    } else {
                                                        src_name = split_str[0];
                                                    }
                                                } else if (lang_type == LANG_REG) {
                                                    eng_name = split_str[0];
                                                    if (split_str.length > 2) {
                                                        if (split_str[2].trim().equals("")) {
                                                            src_name = split_str[0];
                                                        } else {
                                                            try {
                                                                if (!split_str[2].contains("\\")) {
                                                                    src_name = split_str[2];
                                                                } else {
                                                                    split_str[2] = split_str[2].replace("\\", "");
                                                                    split_str = split_str[2].split("u", -1);
                                                                    bytes = new byte[split_str.length * 2 - 2];
                                                                    k = 0;
                                                                    for (i = 0; i < split_str.length; i++) {
                                                                        if (!split_str[i].trim().equals("")) {
                                                                            if (split_str[i].length() == 4) {
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                            }
                                                                        }
                                                                    }

                                                                    src_name = new String(bytes, 0, k, StandardCharsets.UTF_16);
                                                                    if (src_name == null || src_name.trim().equals("")) {
                                                                        src_name = eng_name;
                                                                    }
                                                                }

                                                            } catch (Exception ex) {

                                                            }
                                                        }
                                                    } else {
                                                        src_name = split_str[0];
                                                    }
                                                }
                                            } else {
                                                if (lang_type == LANG_ENGLISH) {
                                                    des_name = split_str[0];
                                                } else if (lang_type == LANG_HINDI) {
                                                    try {
                                                        eng_name = split_str[0];
                                                        if (split_str[1].trim().equals("")) {
                                                            des_name = split_str[0];
                                                        } else {
                                                            if (!split_str[1].contains("\\")) {
                                                                des_name = split_str[1];
                                                            } else {
                                                                split_str[1] = split_str[1].replace("\\", "");
                                                                split_str = split_str[1].split("u", -1);
                                                                bytes = new byte[split_str.length * 2 + 1];
                                                                k = 0;
                                                                for (i = 0; i < split_str.length; i++) {
                                                                    if (!split_str[i].trim().equals("")) {
                                                                        if (split_str[i].length() == 4) {
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                        }
                                                                    }
                                                                }
                                                                des_name = new String(bytes, 0, k, StandardCharsets.UTF_16);
                                                            }
                                                        }
                                                        if (des_name.equals("")) {
                                                            des_name = eng_name;
                                                        }

                                                    } catch (Exception ex) {

                                                    }
                                                } else if (lang_type == LANG_REG) {

                                                    try {
                                                        if (split_str[2].trim().equals("")) {
                                                            des_name = split_str[0];
                                                        } else {
                                                            if (!split_str[2].contains("\\")) {
                                                                des_name = split_str[2];
                                                            } else {
                                                                split_str[2] = split_str[2].replace("\\", "");
                                                                split_str = split_str[2].split("u", -1);
                                                                bytes = new byte[split_str.length * 2 + 1];
                                                                k = 0;
                                                                for (i = 0; i < split_str.length; i++) {
                                                                    if (!split_str[i].trim().equals("")) {
                                                                        if (split_str[i].length() == 4) {
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                        }
                                                                    }
                                                                }
                                                                des_name = new String(bytes, 0, k, StandardCharsets.UTF_16);

                                                            }
                                                            if (des_name.equals("")) {
                                                                des_name = eng_name;
                                                            }
                                                        }

                                                    } catch (Exception ex) {

                                                    }
                                                }
                                            }
                                            i++;
                                        }
                                    } catch (Exception ex) {

                                    }
                                }
                            } catch (IOException e) {
                                //You'll need to add proper error handling here
                            } finally {
                                try {
                                    if (br != null) {
                                        br.close();
                                    }
                                    split_str = null;
                                    br = null;
                                    file = null;
                                    f = null;
                                } catch (IOException e) {
                                }
                            }
                        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                            //Read text from file
                            f.setReadable(true, false);
                            f.setWritable(true, false);
                            try {
                                br = new BufferedReader(new FileReader(f));
                                while ((line = br.readLine()) != null) {
                                    k = 0;
                                    split_str = line.split(",");
                                    try {
                                        if (!split_str[0].equals("")) {
                                            if (i == 0) {
                                                if (lang_type == LANG_ENGLISH) {
                                                    src_name = split_str[0];
                                                } else if (lang_type == LANG_HINDI) {
                                                    eng_name = split_str[0];
                                                    if (split_str.length > 1) {
                                                        if (split_str[1].trim().equals("")) {
                                                            src_name = split_str[0];
                                                        } else {
                                                            try {
                                                                if (!split_str[1].contains("\\")) {
                                                                    src_name = split_str[1];
                                                                } else {
                                                                    split_str[1] = split_str[1].replace("\\", "");
                                                                    split_str = split_str[1].split("u", -1);
                                                                    bytes = new byte[split_str.length * 2 - 2];
                                                                    k = 0;
                                                                    for (i = 0; i < split_str.length; i++) {
                                                                        if (!split_str[i].trim().equals("")) {
                                                                            if (split_str[i].length() == 4) {
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                            }
                                                                        }
                                                                    }
                                                                    src_name = new String(bytes, 0, k, StandardCharsets.UTF_16);
                                                                    if (src_name == null || src_name.trim().equals("")) {
                                                                        src_name = eng_name;
                                                                    }
                                                                }

                                                            } catch (Exception ex) {

                                                            }
                                                        }
                                                    } else {
                                                        src_name = split_str[0];
                                                    }
                                                } else if (lang_type == LANG_REG) {
                                                    eng_name = split_str[0];
                                                    if (split_str.length > 2) {
                                                        if (split_str[2].trim().equals("")) {
                                                            src_name = split_str[0];
                                                        } else {
                                                            try {
                                                                if (!split_str[2].contains("\\")) {
                                                                    src_name = split_str[2];
                                                                } else {
                                                                    split_str[2] = split_str[2].replace("\\", "");
                                                                    split_str = split_str[2].split("u", -1);
                                                                    bytes = new byte[split_str.length * 2 - 2];
                                                                    k = 0;
                                                                    for (i = 0; i < split_str.length; i++) {
                                                                        if (!split_str[i].trim().equals("")) {
                                                                            if (split_str[i].length() == 4) {
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                                bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                            }
                                                                        }
                                                                    }

                                                                    src_name = new String(bytes, 0, k, StandardCharsets.UTF_16);
                                                                    if (src_name == null || src_name.trim().equals("")) {
                                                                        src_name = eng_name;
                                                                    }
                                                                }

                                                            } catch (Exception ex) {

                                                            }
                                                        }
                                                    } else {
                                                        src_name = split_str[0];
                                                    }
                                                }
                                            } else {
                                                if (lang_type == LANG_ENGLISH) {
                                                    des_name = split_str[0];
                                                } else if (lang_type == LANG_HINDI) {
                                                    try {
                                                        eng_name = split_str[0];
                                                        if (split_str[1].trim().equals("")) {
                                                            des_name = split_str[0];
                                                        } else {
                                                            if (!split_str[1].contains("\\")) {
                                                                des_name = split_str[1];
                                                            } else {
                                                                split_str[1] = split_str[1].replace("\\", "");
                                                                split_str = split_str[1].split("u", -1);
                                                                bytes = new byte[split_str.length * 2 + 1];
                                                                k = 0;
                                                                for (i = 0; i < split_str.length; i++) {
                                                                    if (!split_str[i].trim().equals("")) {
                                                                        if (split_str[i].length() == 4) {
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                        }
                                                                    }
                                                                }
                                                                des_name = new String(bytes, 0, k, StandardCharsets.UTF_16);
                                                            }
                                                        }
                                                        if (des_name.equals("")) {
                                                            des_name = eng_name;
                                                        }

                                                    } catch (Exception ex) {
                                                    }
                                                } else if (lang_type == LANG_REG) {

                                                    try {
                                                        if (split_str[2].trim().equals("")) {
                                                            des_name = split_str[0];
                                                        } else {
                                                            if (!split_str[2].contains("\\")) {
                                                                des_name = split_str[2];
                                                            } else {
                                                                split_str[2] = split_str[2].replace("\\", "");
                                                                split_str = split_str[2].split("u", -1);
                                                                bytes = new byte[split_str.length * 2 + 1];
                                                                k = 0;
                                                                for (i = 0; i < split_str.length; i++) {
                                                                    if (!split_str[i].trim().equals("")) {
                                                                        if (split_str[i].length() == 4) {
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(0, 2));
                                                                            bytes[k++] = Byte.parseByte(split_str[i].substring(2, 4));
                                                                        }
                                                                    }
                                                                }
                                                                des_name = new String(bytes, 0, k, StandardCharsets.UTF_16);

                                                            }
                                                            if (des_name.equals("")) {
                                                                des_name = eng_name;
                                                            }
                                                        }

                                                    } catch (Exception ex) {

                                                    }
                                                }
                                            }
                                            i++;
                                        }
                                    } catch (Exception ex) {

                                    }
                                }
                            } catch (IOException e) {
                                //You'll need to add proper error handling here
                            } finally {
                                try {
                                    if (br != null) {
                                        br.close();
                                    }
                                    split_str = null;
                                    br = null;
                                    file = null;
                                    f = null;
                                } catch (IOException e) {
                                }
                            }
                        }
                        break;
                    }
                }
            }
            if (i > 0) {
                set_src_name(src_name, l_cur_trip_no);
                set_des_name(des_name, l_cur_trip_no);
                return getCurSchRouteNo(l_cur_trip_no) + " " + src_name + "-" + des_name;
            }
        } catch (Exception ex) {
        }
        return getCurSchRouteNo(l_cur_trip_no);
    }

    public boolean write_activity_name(byte act_name) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "fileActivity.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(String.valueOf(act_name));
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            return false;
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }

        runCmd("sudo sync");
        return true;

    }

    private boolean runCmd(String cmd) {
        Process process = null;

        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(1, TimeUnit.SECONDS);
            return true;
        } catch (IOException e) {
            if (rt != null) {
                rt.gc();
            }
            return false;

        } catch (Exception ex) {
            if (rt != null) {
                rt.gc();
            }
            return false;

        } finally {

            try {
                if (process != null) {

                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                    process = null;
                    rt = null;
                }
            } catch (IOException e) {
            } catch (Exception ex) {
            }
        }
    }

    public byte read_activity_name() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return 0;
        }
        String line;
        BufferedReader br = null;
//Get the text file
        File file = new File(media_filepath, "fileActivity.txt");
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    setCurActivity(Byte.parseByte(line.trim()));
                    return Byte.parseByte(line);

                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;
            }
        }
        return 0;
    }

    public void read_pid_codes() {
        short i = 0;

        String line;
        BufferedReader br = null;

        //Get the text file
        File file = new File(media_filepath, "pid_codes.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            file.setReadable(true, false);
            file.setWritable(true, false);
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    try {
                        switch (i) {
                            case 0:
                                serial_no = line;    // OBUID:
                                break;
                            case 1:
                                test_date_time_pid = line;
                                break;
                            case 2:
                                hw_revision = line;
                                break;
                            case 3:
                                compilation_fw_datetime = line;
                                break;
                            case 4:
                                obu_watchdog_reset_cnt = (Short.parseShort(line));
                                break;
                            case 5:
                                obu_low_volt_reset_cnt = (Short.parseShort(line));
                                break;
                            case 6:
                                obu_high_volt_cnt = (Short.parseShort(line));
                                break;
                            case 7:
                                obu_low_volt_cnt = (Short.parseShort(line));
                                break;
                            case 8:
                                obu_over_heat_cnt = (Short.parseShort(line));
                                break;
                            case 9:
                                gps_lost_comm_cnt = (Short.parseShort(line));
                                break;
                            case 10:
                                gps_invalid_data_cnt = (Short.parseShort(line));
                                break;
                            case 11:
                                gps_antenna_error_cnt = (Short.parseShort(line));
                                break;
                            case 12:
                                usb_invalid_cnt = (Short.parseShort(line));
                                break;
                            case 13:
                                usb_unknown_cnt = (Short.parseShort(line));
                                break;
                            case 14:
                                usb_invalid_filesystem_cnt = (Short.parseShort(line));
                                break;

                            case 15:
                                usb_overcurrent_cnt = (Short.parseShort(line));
                                break;

                        }
                        i++;
                    } catch (Exception ex) {

                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;

            }

        } else {
            file = new File(config_filepath, "pid_codes.txt");
            if (file.exists()) {
                file.setReadable(true, false);
                file.setWritable(true, false);
                try {
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        try {
                            switch (i) {
                                case 0:
                                    serial_no = line;    // OBUID:
                                    break;
                                case 1:
                                    test_date_time_pid = line;
                                    break;
                                case 2:
                                    hw_revision = line;
                                    break;
                                case 3:
                                    compilation_fw_datetime = line;
                                    break;
                                case 4:
                                    obu_watchdog_reset_cnt = (Short.parseShort(line));
                                    break;
                                case 5:
                                    obu_low_volt_reset_cnt = (Short.parseShort(line));
                                    break;
                                case 6:
                                    obu_high_volt_cnt = (Short.parseShort(line));
                                    break;
                                case 7:
                                    obu_low_volt_cnt = (Short.parseShort(line));
                                    break;
                                case 8:
                                    obu_over_heat_cnt = (Short.parseShort(line));
                                    break;
                                case 9:
                                    gps_lost_comm_cnt = (Short.parseShort(line));
                                    break;
                                case 10:
                                    gps_invalid_data_cnt = (Short.parseShort(line));
                                    break;
                                case 11:
                                    gps_antenna_error_cnt = (Short.parseShort(line));
                                    break;
                                case 12:
                                    usb_invalid_cnt = (Short.parseShort(line));
                                    break;
                                case 13:
                                    usb_unknown_cnt = (Short.parseShort(line));
                                    break;
                                case 14:
                                    usb_invalid_filesystem_cnt = (Short.parseShort(line));
                                    break;

                                case 15:
                                    usb_overcurrent_cnt = (Short.parseShort(line));
                                    break;

                            }
                            i++;
                        } catch (Exception ex) {

                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                    line = null;
                    br = null;
                    file = null;

                }

            }
        }

    }

    public boolean write_pid_codes() {

        File file;
        FileOutputStream f = null;
        PrintWriter pw = null;
        if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "pid_codes.txt");

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                pw.println(serial_no);
                pw.println(test_date_time_pid);

                pw.println(hw_revision);// this.spinHwRev.getValue().toString();
                pw.println(compilation_fw_datetime);// sdf.format(this.spinComDate.getValue());
                pw.println(obu_watchdog_reset_cnt);//(short)this.spinWatchdogReset.getValue() ;
                pw.println(obu_low_volt_reset_cnt);// (short)this.spinLowVoltReset.getValue() ;
                pw.println(obu_high_volt_cnt);// (short)this.spinOverVolt.getValue() ;
                pw.println(obu_low_volt_cnt);// (short)this.spinLowVoltage.getValue() ;
                pw.println(obu_over_heat_cnt);// (short)this.spinOverHeat.getValue() ;
                pw.println(gps_lost_comm_cnt);// (short)this.spinGpsLost.getValue() ;
                pw.println(gps_invalid_data_cnt);// (short)this.spinGpsInvalid.getValue() ;
                pw.println(gps_antenna_error_cnt);// (short)this.spinGpsAntenna.getValue() ;
                pw.println(usb_invalid_cnt);// (short)this.spinUsbInvalid.getValue() ;
                pw.println(usb_unknown_cnt);//(short)this.spinUsbUnknown.getValue() ;
                pw.println(usb_invalid_filesystem_cnt);//(short)this.spinUsbInvalFileSys.getValue() ;
                pw.println(usb_overcurrent_cnt);// (short)this.spinUsbOverCurrent.getValue() ;

                pw.flush();
                pw.close();
                f.close();

            } catch (Exception Ex) {

            } finally {
                file = null;
                pw = null;
                f = null;
            }
        }
        file = new File(config_filepath, "pid_codes.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(serial_no);
            pw.println(test_date_time_pid);

            pw.println(hw_revision);// this.spinHwRev.getValue().toString();
            pw.println(compilation_fw_datetime);// sdf.format(this.spinComDate.getValue());
            pw.println(obu_watchdog_reset_cnt);//(short)this.spinWatchdogReset.getValue() ;
            pw.println(obu_low_volt_reset_cnt);// (short)this.spinLowVoltReset.getValue() ;
            pw.println(obu_high_volt_cnt);// (short)this.spinOverVolt.getValue() ;
            pw.println(obu_low_volt_cnt);// (short)this.spinLowVoltage.getValue() ;
            pw.println(obu_over_heat_cnt);// (short)this.spinOverHeat.getValue() ;
            pw.println(gps_lost_comm_cnt);// (short)this.spinGpsLost.getValue() ;
            pw.println(gps_invalid_data_cnt);// (short)this.spinGpsInvalid.getValue() ;
            pw.println(gps_antenna_error_cnt);// (short)this.spinGpsAntenna.getValue() ;
            pw.println(usb_invalid_cnt);// (short)this.spinUsbInvalid.getValue() ;
            pw.println(usb_unknown_cnt);//(short)this.spinUsbUnknown.getValue() ;
            pw.println(usb_invalid_filesystem_cnt);//(short)this.spinUsbInvalFileSys.getValue() ;
            pw.println(usb_overcurrent_cnt);// (short)this.spinUsbOverCurrent.getValue() ;

            pw.flush();
            pw.close();
            f.close();
            return true;

        } catch (Exception Ex) {

        } finally {
            runCmd("sudo sync");
            file = null;
            pw = null;
            f = null;
        }
        return false;
    }

    public void read_driver_names() {
        short i = 0;
        String line;
        BufferedReader br = null;
        String[] split_str;

        //Get the text file
        File file = new File(media_filepath, "driver_details.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    try {
                        split_str = line.split(",");
                        objDriverDetails[i] = new clsDriverDetails();
                        objDriverDetails[i].driver_id = split_str[0];
                        objDriverDetails[i].driver_name = split_str[1];
                        objDriverDetails[i].pwd = split_str[2];
                        objDriverDetails[i].rfid = split_str[3];
                        i++;
                    } catch (Exception ex) {

                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    driverDetailsCnt = i;
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;

            }

        } else {
            file = new File(config_filepath, "driver_details.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        try {
                            split_str = line.split(",");
                            objDriverDetails[i] = new clsDriverDetails();
                            objDriverDetails[i].driver_id = split_str[0];
                            objDriverDetails[i].driver_name = split_str[1];
                            objDriverDetails[i].pwd = split_str[2];
                            objDriverDetails[i].rfid = split_str[3];
                            i++;
                        } catch (Exception ex) {

                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        driverDetailsCnt = i;
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                    line = null;
                    br = null;
                    file = null;

                }

            }
        }

    }

    public void read_conductor_names() {
        short i = 0;
        String line;
        BufferedReader br = null;
        String[] split_str;

        //Get the text file
        File file = new File(media_filepath, "ConductorIds.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    try {
                        split_str = line.split(",");
                        objConductorDetails[i] = new clsConductorDetails();
                        objConductorDetails[i].conductor_id = split_str[0];
                        objConductorDetails[i].conductor_name = split_str[1];
                        objConductorDetails[i].conductor_rfid = split_str[2];
                        i++;
                    } catch (Exception ex) {

                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    conductorDetailsCnt = i;
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;

            }

        } else {
            file = new File(config_filepath, "ConductorIds.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        try {
                            split_str = line.split(",");
                            objConductorDetails[i] = new clsConductorDetails();
                            objConductorDetails[i].conductor_id = split_str[0];
                            objConductorDetails[i].conductor_name = split_str[1];
                            objConductorDetails[i].conductor_rfid = split_str[2];
                            i++;
                        } catch (Exception ex) {

                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    try {
                        conductorDetailsCnt = i;
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                    line = null;
                    br = null;
                    file = null;

                }

            }
        }

    }

    public void write_driver_details_file() {
        File file = new File(media_filepath, "driver_details.txt");
        FileOutputStream f = null;
        int i = 0;
        PrintWriter pw = null;
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                file.setReadable(true, false);
                file.setWritable(true, false);
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                for (i = 0; i < driverDetailsCnt; i++) {
                    pw.println(objDriverDetails[i].driver_id + "," + objDriverDetails[i].driver_name + "," + objDriverDetails[i].pwd + "," + objDriverDetails[i].rfid);
                }
                pw.flush();
                pw.close();
                f.close();
            }
            file = new File(config_filepath, "driver_details.txt");
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            for (i = 0; i < driverDetailsCnt; i++) {
                pw.println(objDriverDetails[i].driver_id + "," + objDriverDetails[i].driver_name + "," + objDriverDetails[i].pwd + "," + objDriverDetails[i].rfid);

            }
            pw.flush();
            pw.close();
            f.close();

        } catch (Exception Ex) {

        } finally {
            file = null;
            pw = null;
            f = null;
        }

    }

    public void write_last_driver_details_file(String driver_id, String conductor_id) {
        File file = new File(media_filepath, "driver_conductor_last_details.txt");
        FileOutputStream f = null;
        int i = 0;
        PrintWriter pw = null;
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                file.setReadable(true, false);
                file.setWritable(true, false);
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);

                pw.println(driver_id + "," + conductor_id);

                pw.flush();
                pw.close();
                f.close();
            }
            file = new File(config_filepath, "driver_conductor_last_details.txt");
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            for (i = 0; i < driverDetailsCnt; i++) {
                pw.println(objDriverDetails[i].driver_id + "," + objDriverDetails[i].driver_name + "," + objDriverDetails[i].pwd);

            }
            pw.flush();
            pw.close();
            f.close();

        } catch (Exception Ex) {
            //  System.out.println("Exception in file" + Ex.getMessage());
        } finally {
            file = null;
            pw = null;
            f = null;
        }

    }

    public String read_last_driver_details_file() {

        String line = null;
        BufferedReader br = null;

        //Get the text file
        File file = new File(media_filepath, "driver_conductor_last_details.txt");
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                line = br.readLine();

                return line;
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {

                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;

            }

        } else {
            file = new File(config_filepath, "driver_conductor_last_details.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    line = br.readLine();

                    return line;
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception e) {

                } finally {
                    try {

                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                    line = null;
                    br = null;
                    file = null;

                }

            }
        }
        return line;
    }

    public void write_disbrd_pid_file() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        File file = new File(media_filepath, "disbrd_pid.txt");
        FileOutputStream f = null;

        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);

            byte brd_type = FRONT_DB;
            pw.println(objPids[brd_type].ser_no + "," + objPids[brd_type].compilation_fw_date + ","
                    + objPids[brd_type].flash_update_status + "," + objPids[brd_type].test_date_time + ","
                    + objPids[brd_type].prod_date + "," + objPids[brd_type].end_customer + ","
                    + objPids[brd_type].order_no + "," + objPids[brd_type].vehicle_type + ","
                    + objPids[brd_type].bus_builder_no + "," + objPids[brd_type].language1 + ","
                    + objPids[brd_type].language2 + "," + objPids[brd_type].language3 + ","
                    + objPids[brd_type].operating_hours + "," + objPids[brd_type].no_resets
                    + "," + objPids[brd_type].internal_cpu_temp + "," + objPids[brd_type].app_sw_rev
                    + "," + objPids[brd_type].hw_rev_val + "," + objPids[brd_type].article_no_sign_level
                    + "," + objPids[brd_type].board_temp_sensor + "," + objPids[brd_type].boot_loader_sw_rev
                    + "," + objPids[brd_type].cpu_part_no + "," + objPids[brd_type].cpu_qual + ","
                    + objPids[brd_type].cpu_temp_range + "," + objPids[brd_type].font_lib_rev
                    + "," + objPids[brd_type].max_board_temp + "," + objPids[brd_type].max_cpu_temp
                    + "," + objPids[brd_type].max_input_volt + "," + objPids[brd_type].min_board_temp
                    + "," + objPids[brd_type].min_cpu_temp + "," + objPids[brd_type].min_input_volt
                    + "," + objPids[brd_type].board_temp_sensor);

            brd_type = SIDE_DB;
            pw.println(objPids[brd_type].ser_no + "," + objPids[brd_type].compilation_fw_date + ","
                    + objPids[brd_type].flash_update_status + "," + objPids[brd_type].test_date_time + ","
                    + objPids[brd_type].prod_date + "," + objPids[brd_type].end_customer + ","
                    + objPids[brd_type].order_no + "," + objPids[brd_type].vehicle_type + ","
                    + objPids[brd_type].bus_builder_no + "," + objPids[brd_type].language1 + ","
                    + objPids[brd_type].language2 + "," + objPids[brd_type].language3 + ","
                    + objPids[brd_type].operating_hours + "," + objPids[brd_type].no_resets
                    + "," + objPids[brd_type].internal_cpu_temp + "," + objPids[brd_type].app_sw_rev
                    + "," + objPids[brd_type].hw_rev_val + "," + objPids[brd_type].article_no_sign_level
                    + "," + objPids[brd_type].board_temp_sensor + "," + objPids[brd_type].boot_loader_sw_rev
                    + "," + objPids[brd_type].cpu_part_no + "," + objPids[brd_type].cpu_qual + ","
                    + objPids[brd_type].cpu_temp_range + "," + objPids[brd_type].font_lib_rev
                    + "," + objPids[brd_type].max_board_temp + "," + objPids[brd_type].max_cpu_temp
                    + "," + objPids[brd_type].max_input_volt + "," + objPids[brd_type].min_board_temp
                    + "," + objPids[brd_type].min_cpu_temp + "," + objPids[brd_type].min_input_volt
                    + "," + objPids[brd_type].board_temp_sensor);

            brd_type = REAR_DB;
            pw.println(objPids[brd_type].ser_no + "," + objPids[brd_type].compilation_fw_date + ","
                    + objPids[brd_type].flash_update_status + "," + objPids[brd_type].test_date_time + ","
                    + objPids[brd_type].prod_date + "," + objPids[brd_type].end_customer + ","
                    + objPids[brd_type].order_no + "," + objPids[brd_type].vehicle_type + ","
                    + objPids[brd_type].bus_builder_no + "," + objPids[brd_type].language1 + ","
                    + objPids[brd_type].language2 + "," + objPids[brd_type].language3 + ","
                    + objPids[brd_type].operating_hours + "," + objPids[brd_type].no_resets
                    + "," + objPids[brd_type].internal_cpu_temp + "," + objPids[brd_type].app_sw_rev
                    + "," + objPids[brd_type].hw_rev_val + "," + objPids[brd_type].article_no_sign_level
                    + "," + objPids[brd_type].board_temp_sensor + "," + objPids[brd_type].boot_loader_sw_rev
                    + "," + objPids[brd_type].cpu_part_no + "," + objPids[brd_type].cpu_qual + ","
                    + objPids[brd_type].cpu_temp_range + "," + objPids[brd_type].font_lib_rev
                    + "," + objPids[brd_type].max_board_temp + "," + objPids[brd_type].max_cpu_temp
                    + "," + objPids[brd_type].max_input_volt + "," + objPids[brd_type].min_board_temp
                    + "," + objPids[brd_type].min_cpu_temp + "," + objPids[brd_type].min_input_volt
                    + "," + objPids[brd_type].board_temp_sensor);

            brd_type = INT_DB;
            pw.println(objPids[brd_type].ser_no + "," + objPids[brd_type].compilation_fw_date + ","
                    + objPids[brd_type].flash_update_status + "," + objPids[brd_type].test_date_time + ","
                    + objPids[brd_type].prod_date + "," + objPids[brd_type].end_customer + ","
                    + objPids[brd_type].order_no + "," + objPids[brd_type].vehicle_type + ","
                    + objPids[brd_type].bus_builder_no + "," + objPids[brd_type].language1 + ","
                    + objPids[brd_type].language2 + "," + objPids[brd_type].language3 + ","
                    + objPids[brd_type].operating_hours + "," + objPids[brd_type].no_resets
                    + "," + objPids[brd_type].internal_cpu_temp + "," + objPids[brd_type].app_sw_rev
                    + "," + objPids[brd_type].hw_rev_val + "," + objPids[brd_type].article_no_sign_level
                    + "," + objPids[brd_type].board_temp_sensor + "," + objPids[brd_type].boot_loader_sw_rev
                    + "," + objPids[brd_type].cpu_part_no + "," + objPids[brd_type].cpu_qual + ","
                    + objPids[brd_type].cpu_temp_range + "," + objPids[brd_type].font_lib_rev
                    + "," + objPids[brd_type].max_board_temp + "," + objPids[brd_type].max_cpu_temp
                    + "," + objPids[brd_type].max_input_volt + "," + objPids[brd_type].min_board_temp
                    + "," + objPids[brd_type].min_cpu_temp + "," + objPids[brd_type].min_input_volt
                    + "," + objPids[brd_type].board_temp_sensor);

            pw.flush();
            pw.close();
            f.close();

        } catch (Exception Ex) {

        } finally {
            file = null;
            pw = null;
            f = null;
        }

    }

    public void read_disbrd_pid_file() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        short i = 0;
        String line;
        BufferedReader br = null;
        String[] split_str;
        byte brd_type = 0;

        //Get the text file
        File file = new File(media_filepath, "disbrd_pid.txt");
        if (file.exists()) {
            file.setReadable(true, false);
            file.setWritable(true, false);
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    try {
                        split_str = line.split(",");
                        i = 0;
                        switch (brd_type) {
                            case 0:
                                objPids[brd_type].ser_no = split_str[i++];
                                objPids[brd_type].compilation_fw_date = split_str[i++];
                                objPids[brd_type].flash_update_status = split_str[i++];
                                objPids[brd_type].test_date_time = split_str[i++];
                                objPids[brd_type].prod_date = split_str[i++];
                                objPids[brd_type].end_customer = split_str[i++];
                                objPids[brd_type].order_no = split_str[i++];
                                objPids[brd_type].vehicle_type = split_str[i++];
                                objPids[brd_type].bus_builder_no = split_str[i++];
                                objPids[brd_type].language1 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language2 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language3 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].operating_hours = Long.parseLong(split_str[i++]);
                                objPids[brd_type].no_resets = Integer.parseInt(split_str[i++]);
                                objPids[brd_type].internal_cpu_temp = (split_str[i++]);
                                objPids[brd_type].app_sw_rev = split_str[i++];
                                objPids[brd_type].hw_rev_val = split_str[i++];
                                objPids[brd_type].article_no_sign_level = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];
                                objPids[brd_type].boot_loader_sw_rev = split_str[i++];
                                objPids[brd_type].cpu_part_no = split_str[i++];
                                objPids[brd_type].cpu_qual = split_str[i++];
                                objPids[brd_type].cpu_temp_range = split_str[i++];
                                objPids[brd_type].font_lib_rev = split_str[i++];
                                objPids[brd_type].max_board_temp = split_str[i++];
                                objPids[brd_type].max_cpu_temp = split_str[i++];
                                objPids[brd_type].max_input_volt = split_str[i++];
                                objPids[brd_type].min_board_temp = split_str[i++];
                                objPids[brd_type].min_cpu_temp = split_str[i++];
                                objPids[brd_type].min_input_volt = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];

                                objPids[brd_type].language = objPids[brd_type].language1;

                                break;

                            case 1:
                                objPids[brd_type].ser_no = split_str[i++];
                                objPids[brd_type].compilation_fw_date = split_str[i++];
                                objPids[brd_type].flash_update_status = split_str[i++];
                                objPids[brd_type].test_date_time = split_str[i++];
                                objPids[brd_type].prod_date = split_str[i++];
                                objPids[brd_type].end_customer = split_str[i++];
                                objPids[brd_type].order_no = split_str[i++];
                                objPids[brd_type].vehicle_type = split_str[i++];
                                objPids[brd_type].bus_builder_no = split_str[i++];
                                objPids[brd_type].language1 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language2 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language3 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].operating_hours = Long.parseLong(split_str[i++]);
                                objPids[brd_type].no_resets = Integer.parseInt(split_str[i++]);
                                objPids[brd_type].internal_cpu_temp = (split_str[i++]);
                                objPids[brd_type].app_sw_rev = split_str[i++];
                                objPids[brd_type].hw_rev_val = split_str[i++];
                                objPids[brd_type].article_no_sign_level = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];
                                objPids[brd_type].boot_loader_sw_rev = split_str[i++];
                                objPids[brd_type].cpu_part_no = split_str[i++];
                                objPids[brd_type].cpu_qual = split_str[i++];
                                objPids[brd_type].cpu_temp_range = split_str[i++];
                                objPids[brd_type].font_lib_rev = split_str[i++];
                                objPids[brd_type].max_board_temp = split_str[i++];
                                objPids[brd_type].max_cpu_temp = split_str[i++];
                                objPids[brd_type].max_input_volt = split_str[i++];
                                objPids[brd_type].min_board_temp = split_str[i++];
                                objPids[brd_type].min_cpu_temp = split_str[i++];
                                objPids[brd_type].min_input_volt = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];

                                objPids[brd_type].language = objPids[brd_type].language1;
                                break;

                            case 2:
                                objPids[brd_type].ser_no = split_str[i++];
                                objPids[brd_type].compilation_fw_date = split_str[i++];
                                objPids[brd_type].flash_update_status = split_str[i++];
                                objPids[brd_type].test_date_time = split_str[i++];
                                objPids[brd_type].prod_date = split_str[i++];
                                objPids[brd_type].end_customer = split_str[i++];
                                objPids[brd_type].order_no = split_str[i++];
                                objPids[brd_type].vehicle_type = split_str[i++];
                                objPids[brd_type].bus_builder_no = split_str[i++];
                                objPids[brd_type].language1 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language2 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language3 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].operating_hours = Long.parseLong(split_str[i++]);
                                objPids[brd_type].no_resets = Integer.parseInt(split_str[i++]);
                                objPids[brd_type].internal_cpu_temp = (split_str[i++]);
                                objPids[brd_type].app_sw_rev = split_str[i++];
                                objPids[brd_type].hw_rev_val = split_str[i++];
                                objPids[brd_type].article_no_sign_level = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];
                                objPids[brd_type].boot_loader_sw_rev = split_str[i++];
                                objPids[brd_type].cpu_part_no = split_str[i++];
                                objPids[brd_type].cpu_qual = split_str[i++];
                                objPids[brd_type].cpu_temp_range = split_str[i++];
                                objPids[brd_type].font_lib_rev = split_str[i++];
                                objPids[brd_type].max_board_temp = split_str[i++];
                                objPids[brd_type].max_cpu_temp = split_str[i++];
                                objPids[brd_type].max_input_volt = split_str[i++];
                                objPids[brd_type].min_board_temp = split_str[i++];
                                objPids[brd_type].min_cpu_temp = split_str[i++];
                                objPids[brd_type].min_input_volt = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];

                                objPids[brd_type].language = objPids[brd_type].language1;
                                break;

                            case 3:
                                objPids[brd_type].ser_no = split_str[i++];
                                objPids[brd_type].compilation_fw_date = split_str[i++];
                                objPids[brd_type].flash_update_status = split_str[i++];
                                objPids[brd_type].test_date_time = split_str[i++];
                                objPids[brd_type].prod_date = split_str[i++];
                                objPids[brd_type].end_customer = split_str[i++];
                                objPids[brd_type].order_no = split_str[i++];
                                objPids[brd_type].vehicle_type = split_str[i++];
                                objPids[brd_type].bus_builder_no = split_str[i++];
                                objPids[brd_type].language1 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language2 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].language3 = Byte.parseByte(split_str[i++]);
                                objPids[brd_type].operating_hours = Long.parseLong(split_str[i++]);
                                objPids[brd_type].no_resets = Integer.parseInt(split_str[i++]);
                                objPids[brd_type].internal_cpu_temp = (split_str[i++]);
                                objPids[brd_type].app_sw_rev = split_str[i++];
                                objPids[brd_type].hw_rev_val = split_str[i++];
                                objPids[brd_type].article_no_sign_level = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];
                                objPids[brd_type].boot_loader_sw_rev = split_str[i++];
                                objPids[brd_type].cpu_part_no = split_str[i++];
                                objPids[brd_type].cpu_qual = split_str[i++];
                                objPids[brd_type].cpu_temp_range = split_str[i++];
                                objPids[brd_type].font_lib_rev = split_str[i++];
                                objPids[brd_type].max_board_temp = split_str[i++];
                                objPids[brd_type].max_cpu_temp = split_str[i++];
                                objPids[brd_type].max_input_volt = split_str[i++];
                                objPids[brd_type].min_board_temp = split_str[i++];
                                objPids[brd_type].min_cpu_temp = split_str[i++];
                                objPids[brd_type].min_input_volt = split_str[i++];
                                objPids[brd_type].board_temp_sensor = split_str[i++];

                                objPids[brd_type].language = objPids[brd_type].language1;
                                break;
                        }

                    } catch (Exception ex) {

                    }
                    brd_type++;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {

                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;

            }

        }

    }

    public void write_disbrd_dtc_file() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        File file = new File(media_filepath, "disbrd_dtc.txt");
        FileOutputStream f = null;

        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);

            pw.println(front_high_volt + "," + front_low_volt + ","
                    + front_over_heat + "," + front_high_volt_cnt + ","
                    + front_low_volt_cnt + "," + front_over_heat_cnt);
            pw.println(side_high_volt + "," + side_low_volt + ","
                    + side_over_heat + "," + side_high_volt_cnt + ","
                    + side_low_volt_cnt + "," + side_over_heat_cnt);
            pw.println(rear_high_volt + "," + rear_low_volt + ","
                    + rear_over_heat + "," + rear_high_volt_cnt + ","
                    + rear_low_volt_cnt + "," + rear_over_heat_cnt);
            pw.println(int_high_volt + "," + int_low_volt + ","
                    + int_over_heat + "," + int_high_volt_cnt + ","
                    + int_low_volt_cnt + "," + int_over_heat_cnt);

            pw.flush();
            pw.close();
            f.close();

        } catch (Exception Ex) {

        } finally {
            file = null;
            pw = null;
            f = null;
        }

    }

    public void read_disbrd_dtc_file() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        short i = 0;
        String line;
        BufferedReader br = null;
        String[] split_str;
        byte brd_type = 0;

        //Get the text file
        File file = new File(media_filepath, "disbrd_dtc.txt");
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    try {
                        split_str = line.split(",");
                        switch (brd_type) {
                            case 0:
                                i = 0;
                                front_high_volt = Integer.parseInt(split_str[i++]);
                                front_low_volt = Integer.parseInt(split_str[i++]);
                                front_over_heat = Integer.parseInt(split_str[i++]);
                                front_high_volt_cnt = Integer.parseInt(split_str[i++]);
                                front_low_volt_cnt = Integer.parseInt(split_str[i++]);
                                front_over_heat_cnt = Integer.parseInt(split_str[i++]);
                                break;
                            case 1:
                                i = 0;
                                side_high_volt = Integer.parseInt(split_str[i++]);
                                side_low_volt = Integer.parseInt(split_str[i++]);
                                side_over_heat = Integer.parseInt(split_str[i++]);
                                side_high_volt_cnt = Integer.parseInt(split_str[i++]);
                                side_low_volt_cnt = Integer.parseInt(split_str[i++]);
                                side_over_heat_cnt = Integer.parseInt(split_str[i++]);
                                break;
                            case 2:
                                i = 0;
                                rear_high_volt = Integer.parseInt(split_str[i++]);
                                rear_low_volt = Integer.parseInt(split_str[i++]);
                                rear_over_heat = Integer.parseInt(split_str[i++]);
                                rear_high_volt_cnt = Integer.parseInt(split_str[i++]);
                                rear_low_volt_cnt = Integer.parseInt(split_str[i++]);
                                rear_over_heat_cnt = Integer.parseInt(split_str[i++]);
                                break;
                            case 3:
                                i = 0;
                                int_high_volt = Integer.parseInt(split_str[i++]);
                                int_low_volt = Integer.parseInt(split_str[i++]);
                                int_over_heat = Integer.parseInt(split_str[i++]);
                                int_high_volt_cnt = Integer.parseInt(split_str[i++]);
                                int_low_volt_cnt = Integer.parseInt(split_str[i++]);
                                int_over_heat_cnt = Integer.parseInt(split_str[i++]);
                                break;
                        }

                    } catch (Exception ex) {

                    }
                    brd_type++;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {

                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                line = null;
                br = null;
                file = null;

            }

        }

    }

    public void init_can_params_value() {
        int i = 0;
        for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
            PanCanConfig.objCanElectrical[i].value = 0;
            PanCanConfig.objCanElectrical[i].data_updated = false;
        }
        for (i = 0; i < PanCanConfig.objCanSafetyCnt; i++) {
            PanCanConfig.objCanSafety[i].value = 0;
            PanCanConfig.objCanSafety[i].data_updated = false;
        }

        for (i = 0; i < PanCanConfig.objCanTransmitCnt; i++) {
            PanCanConfig.objCanTransmit[i].value = 0;
            PanCanConfig.objCanTransmit[i].data_updated = false;
        }

        for (i = 0; i < PanCanConfig.objCanEngineCnt; i++) {
            PanCanConfig.objCanEngine[i].value = 0;
            PanCanConfig.objCanEngine[i].data_updated = false;
        }

        for (i = 0; i < PanCanConfig.objCanOthersCnt; i++) {
            PanCanConfig.objCanOthers[i].value = 0;
            PanCanConfig.objCanOthers[i].data_updated = false;
        }
    }

    public void read_canElectricals_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canElectrical.txt");

        int inc;
        i = 0;
        if (file.exists()) {

            try {
                file.setReadable(true, false);
                file.setWritable(true, false);

                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");
                        if (j > 0) {
                            inc = 0;
                            if (split_str.length >= 12) {
                                PanCanConfig.objCanElectrical[i].name = split_str[inc++].trim();
                                PanCanConfig.objCanElectrical[i].source_address = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].min = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].max = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].spn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical[i].unit = split_str[inc++].trim();
                                try {
                                    PanCanConfig.objCanElectrical[i].can_id = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].factor = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].offset = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }

                                try {
                                    PanCanConfig.objCanElectrical[i].threshold = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                i++;
                            }
                        } else {

                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception ex) {
            } finally {
                try {

                    PanCanConfig.objCanElectricalCnt = (byte) i;

                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;
                    }
                } catch (IOException e) {

                }
            }
            file = new File(media_filepath, "canElectrical.txt");
            if (!file.exists() && clsSharedVariables.getHardDriveDetected()) {
                copyFile(config_filepath.getAbsolutePath() + "/" + "canElectrical.txt", media_filepath.getAbsolutePath() + "/" + "canElectrical.txt");
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {

            file = new File(media_filepath, "canElectrical.txt");
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);

                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            if (j > 0) {
                                inc = 0;
                                if (split_str.length >= 12) {
                                    PanCanConfig.objCanElectrical[i].name = split_str[inc++].trim();
                                    PanCanConfig.objCanElectrical[i].source_address = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].min = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].max = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].spn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical[i].unit = split_str[inc++].trim();
                                    try {
                                        PanCanConfig.objCanElectrical[i].can_id = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].factor = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].offset = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }

                                    try {
                                        PanCanConfig.objCanElectrical[i].threshold = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }
                                    i++;
                                }
                            } else {

                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception ex) {
                } finally {
                    try {

                        PanCanConfig.objCanElectricalCnt = (byte) i;

                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }
                    } catch (IOException e) {

                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canElectrical.txt", config_filepath.getAbsolutePath() + "/" + "canElectrical.txt");

            }
        }
    }

    public void read_canSafety_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canSafety.txt");

        int inc = 0;
        i = 0;
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                file.setWritable(true, false);

                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");
                        if (j > 0) {

                            inc = 0;
                            if (split_str.length >= 12) {
                                PanCanConfig.objCanSafety[i].name = split_str[inc++].trim();
                                PanCanConfig.objCanSafety[i].source_address = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].min = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].max = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].spn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety[i].unit = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanSafety[i].can_id = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].factor = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].offset = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                try {
                                    PanCanConfig.objCanSafety[i].threshold = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                i++;
                            }
                        } else {

                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception ex) {
            } finally {

                PanCanConfig.objCanSafetyCnt = (byte) i;

                try {
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;

                    }

                } catch (IOException e) {

                }
            }
            file = new File(media_filepath, "canSafety.txt");
            if (!file.exists() && clsSharedVariables.getHardDriveDetected()) {
                copyFile(config_filepath.getAbsolutePath() + "/" + "canSafety.txt", media_filepath.getAbsolutePath() + "/" + "canSafety.txt");
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {

            file = new File(media_filepath, "canSafety.txt");

            inc = 0;
            i = 0;
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    file.setWritable(true, false);

                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            if (j > 0) {

                                inc = 0;
                                if (split_str.length >= 12) {
                                    PanCanConfig.objCanSafety[i].name = split_str[inc++].trim();
                                    PanCanConfig.objCanSafety[i].source_address = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].min = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].max = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].spn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety[i].unit = split_str[inc++].trim();

                                    try {
                                        PanCanConfig.objCanSafety[i].can_id = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].factor = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].offset = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }
                                    try {
                                        PanCanConfig.objCanSafety[i].threshold = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }
                                    i++;
                                }
                            } else {

                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception ex) {
                } finally {

                    PanCanConfig.objCanSafetyCnt = (byte) i;

                    try {
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;

                        }

                    } catch (IOException e) {

                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canSafety.txt", config_filepath.getAbsolutePath() + "/" + "canSafety.txt");
            }
        }
    }

    public void read_canTransmit_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canTransmit.txt");
        int inc = 0;

        i = 0;
        if (file.exists()) {
            try {

                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");

                        if (j > 0) {
                            inc = 0;
                            if (split_str.length >= 12) {
                                PanCanConfig.objCanTransmit[i].name = split_str[inc++].trim();
                                PanCanConfig.objCanTransmit[i].source_address = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].min = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].max = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].spn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit[i].unit = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanTransmit[i].can_id = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].factor = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].offset = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                try {
                                    PanCanConfig.objCanTransmit[i].threshold = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                i++;
                            }
                        } else {

                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception ex) {

            } finally {
                try {

                    PanCanConfig.objCanTransmitCnt = (byte) i;

                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;

                    }

                } catch (IOException e) {

                }
            }
            if (clsSharedVariables.getHardDriveDetected()) {
                file = new File(media_filepath, "canTransmit.txt");
                if (!file.exists()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "canTransmit.txt", media_filepath.getAbsolutePath() + "/" + "canTransmit.txt");
                }
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "canTransmit.txt");
            inc = 0;

            i = 0;
            if (file.exists()) {
                try {

                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");

                            if (j > 0) {
                                inc = 0;
                                if (split_str.length >= 12) {
                                    PanCanConfig.objCanTransmit[i].name = split_str[inc++].trim();
                                    PanCanConfig.objCanTransmit[i].source_address = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].min = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].max = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].spn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit[i].unit = split_str[inc++].trim();

                                    try {
                                        PanCanConfig.objCanTransmit[i].can_id = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].factor = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].offset = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }
                                    try {
                                        PanCanConfig.objCanTransmit[i].threshold = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }
                                    i++;
                                }
                            } else {

                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception ex) {

                } finally {
                    try {

                        PanCanConfig.objCanTransmitCnt = (byte) i;

                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;

                        }

                    } catch (IOException e) {

                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canTransmit.txt", config_filepath.getAbsolutePath() + "/" + "canTransmit.txt");

            }
        }
    }

    public void read_canEngine_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        int inc = 0;
        File file = new File(config_filepath, "canEngine.txt");

        i = 0;
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");
                        inc = 0;
                        if (j > 0) {
                            if (split_str.length >= 12) {

                                PanCanConfig.objCanEngine[i].name = split_str[inc++].trim();
                                PanCanConfig.objCanEngine[i].source_address = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].min = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].max = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].spn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine[i].unit = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanEngine[i].can_id = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].factor = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].offset = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                try {
                                    PanCanConfig.objCanEngine[i].threshold = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }

                                i++;
                            }
                        } else {
                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception ex) {
                //
            } finally {
                try {
                    PanCanConfig.objCanEngineCnt = (byte) i;

                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;

                    }

                } catch (IOException e) {

                }
            }
            if (clsSharedVariables.getHardDriveDetected()) {
                file = new File(media_filepath, "canEngine.txt");
                if (!file.exists()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "canEngine.txt", media_filepath.getAbsolutePath() + "/" + "canEngine.txt");
                }
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "canEngine.txt");

            i = 0;
            if (file.exists()) {
                try {

                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            inc = 0;
                            if (j > 0) {
                                if (split_str.length >= 12) {

                                    PanCanConfig.objCanEngine[i].name = split_str[inc++].trim();
                                    PanCanConfig.objCanEngine[i].source_address = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].min = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].max = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].spn = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine[i].unit = split_str[inc++].trim();

                                    try {
                                        PanCanConfig.objCanEngine[i].can_id = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].factor = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].offset = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }
                                    try {
                                        PanCanConfig.objCanEngine[i].threshold = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {

                                    }

                                    i++;
                                }
                            } else {
                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception ex) {

                } finally {
                    try {
                        PanCanConfig.objCanEngineCnt = (byte) i;

                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;

                        }

                    } catch (IOException e) {

                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canEngine.txt", config_filepath.getAbsolutePath() + "/" + "canEngine.txt");

            }
        }
    }

    public void read_canOthers_file() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canOthers.txt");

        int inc = 0;
        i = 0;
        if (file.exists()) {
            try {

                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");
                        inc = 0;

                        if (j > 0) {
                            if (split_str.length >= 12) {

                                PanCanConfig.objCanOthers[i].name = split_str[inc++].trim();
                                PanCanConfig.objCanOthers[i].source_address = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].min = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].max = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].spn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].unit = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanOthers[i].can_id = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].factor = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].offset = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                try {
                                    PanCanConfig.objCanOthers[i].threshold = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                i++;

                            }
                        } else {
                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception ex) {

            } finally {

                PanCanConfig.objCanOthersCnt = (byte) i;

                try {
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;
                    }

                } catch (IOException e) {

                }
            }
            if (clsSharedVariables.getHardDriveDetected()) {
                file = new File(media_filepath, "canOthers.txt");
                if (!file.exists()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "canOthers.txt", media_filepath.getAbsolutePath() + "/" + "canOthers.txt");
                }
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "canOthers.txt");

            inc = 0;
            i = 0;
            if (file.exists()) {
                try {

                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            inc = 0;

                            if (split_str.length >= 12) {

                                PanCanConfig.objCanOthers[i].name = split_str[inc++].trim();
                                PanCanConfig.objCanOthers[i].source_address = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].log_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].display_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].transmit_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].alert_enable = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].alarm_id = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].min = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].max = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].pgn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].spn = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers[i].unit = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanOthers[i].can_id = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].factor = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].offset = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].byte_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].byte_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].bit_pos = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].bit_length = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers[i].reverse = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                try {
                                    PanCanConfig.objCanOthers[i].threshold = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {

                                }
                                i++;

                            }

                        }
                    }
                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                } catch (Exception ex) {

                } finally {

                    PanCanConfig.objCanOthersCnt = (byte) i;

                    try {
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }

                    } catch (IOException e) {

                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canOthers.txt", config_filepath.getAbsolutePath() + "/" + "canOthers.txt");

            }
        }
    }

    public synchronized boolean write_device_dtc_counts_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        File file = new File(media_filepath, "fileDeviceDtcCounts.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.obu_watchdog_reset_cnt + "," + clsSharedVariables.obu_low_volt_reset_cnt + "," + clsSharedVariables.gps_lost_comm_cnt
                    + "," + clsSharedVariables.gps_invalid_data_cnt + "," + clsSharedVariables.gps_antenna_error_cnt
                    + "," + clsSharedVariables.usb_invalid_cnt + "," + clsSharedVariables.usb_unknown_cnt
                    + "," + clsSharedVariables.usb_invalid_filesystem_cnt + "," + clsSharedVariables.obu_high_volt_cnt
                    + "," + clsSharedVariables.obu_low_volt_cnt + "," + clsSharedVariables.obu_over_heat_cnt);

            pw.flush();
            pw.close();
            f.close();

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception e) {

            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
        }

        return true;

    }

    public void read_device_dtc_counts_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String[] split_str;
        String line;
        BufferedReader br = null;
        byte i = 0;

        File file = new File(media_filepath, "fileDeviceDtcCounts.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length >= 11) {
                        clsSharedVariables.obu_watchdog_reset_cnt = Short.parseShort(split_str[i++]);
                        clsSharedVariables.obu_low_volt_reset_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.gps_lost_comm_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.gps_invalid_data_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.gps_antenna_error_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.usb_invalid_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.usb_unknown_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.usb_invalid_filesystem_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.obu_high_volt_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.obu_low_volt_cnt = Short.parseShort(split_str[i++]);

                        clsSharedVariables.obu_over_heat_cnt = Short.parseShort(split_str[i++]);

                    }
                    break;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;

                file = null;
            }
        }
    }

    public synchronized void write_port_detection_data(int val, int tot_val) {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String data;
        File file = new File(media_filepath, "port_detection_data.txt");
        FileOutputStream f;
        PrintWriter pw;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);

            data = String.valueOf(val) + "," + String.valueOf(tot_val);
            pw.println(data);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } finally {
            data = null;
            f = null;
            pw = null;
            file = null;
        }
    }

    public void read_port_detection_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String[] split_str;
        String line;
        BufferedReader br = null;
        byte i = 0;

        File file = new File(media_filepath, "port_detection_data.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    if (split_str.length >= 2) {
                        MainFrmIts.port_inc_cnt = Integer.parseInt(split_str[i++]);
                        MainFrmIts.port_perm_inc_cnt = Integer.parseInt(split_str[i++]);
                    }
                    break;
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;

                file = null;
            }
        }
    }

    public void read_emergency_map_data() {
        String[] split_str;
        String[] split_str1;
        String line;
        BufferedReader br = null;
        boolean first = false;
        int j = 0;
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        File file = new File(media_filepath, "emergency_map_data.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {

                    if (first == false) {
                        first = true;
                        split_str = line.split(",");
                        if (split_str.length >= 4) {
                            clsSharedVariables.setEmergencySrcLat(Double.parseDouble(split_str[0]));
                            clsSharedVariables.setEmergencySrcLong(Double.parseDouble(split_str[1]));
                            clsSharedVariables.setEmergencyDesLat(Double.parseDouble(split_str[2]));
                            clsSharedVariables.setEmergencyDesLong(Double.parseDouble(split_str[3]));
                            if (split_str.length >= 5) {
                                clsSharedVariables.setNoEmergencyPtsFromGprs(Integer.parseInt(split_str[4]));
                            }
                        }
                    } else {
                        split_str = line.split(" ");

                        objMapData = new clsSharedVariables.S_claslatlong[split_str.length];

                        for (j = 0; j < split_str.length; j++) {
                            objMapData[j] = new clsSharedVariables.S_claslatlong();
                        }
                        j = 0;
                        for (String split_str2 : split_str) {
                            split_str1 = split_str2.split(",");
                            if (split_str1.length >= 2) {
                                objMapData[j].longitude = Double.parseDouble(split_str1[0]);
                                objMapData[j].latitude = Double.parseDouble(split_str1[1]);
                                j++;
                            }
                        }
                        clsSharedVariables.no_maps_latlong_points = j;

                        break;
                    }
                }
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }
                split_str = null;
                line = null;
                br = null;

                file = null;
            }
        }
    }

    public synchronized void write_emergency_map_data() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        String data = "";
        File file = new File(media_filepath, "emergency_map_data.txt");

        FileOutputStream f;
        PrintWriter pw;

        int j = 0;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);

            data = clsSharedVariables.getEmergencySrcLat() + "," + clsSharedVariables.getEmergencySrcLong() + ","
                    + clsSharedVariables.getEmergencyDesLat() + "," + clsSharedVariables.getEmergencyDesLong() + ","
                    + clsSharedVariables.getNoEmergencyPtsFromGprs();

            pw.println(data.trim());

            data = "";
            for (j = 0; j < clsSharedVariables.no_maps_latlong_points; j++) {
                data = data + String.valueOf(objMapData[j].longitude) + "," + String.valueOf(objMapData[j].latitude);
                data = data + " ";
            }

            pw.println(data.trim());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } finally {
            data = null;
            f = null;
            pw = null;
            file = null;
        }
    }

    public boolean read_destination_map_data_google(double source_lat, double source_long, double dest_lat, double dest_long) {
        URL url_map = null;
        StringBuilder sb = null;
        int ch;
        String str;
        BufferedReader in = null;
        String url;
        JSONObject jObj;
        int length;
        JSONArray dataArray;
        JSONArray dataArray2;
        JSONArray dataArray3;
        JSONObject latLngObjs;
        JSONObject latLngObjs2;
        JSONObject latLngObjs3;
        JSONObject latLngObjs4;
        Iterator ite;
        Iterator iteLatLongs;
        Iterator iteLatLongs2;
        Iterator iteLatLongs3;
        int i = 0;

        try {
            try {
                url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + source_lat + "," + source_long + "&destination=" + dest_lat + "," + dest_long + "&key=AIzaSyCwL08wIRjecbr2IO3un18BQ3hzlsEVE6c";
                url_map = new URL(url);
                in = new BufferedReader(
                        new InputStreamReader(url_map.openStream()));

                sb = new StringBuilder();
                while ((ch = in.read()) != -1) {
                    sb.append((char) ch);
                }
            } catch (MalformedURLException error) {
                return false;
            } catch (IOException error) {
                return false;
            } catch (Exception error) {
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex) {
                    }
                    in = null;
                }
            }
            try {
                jObj = new JSONObject(sb.toString());
                ite = jObj.keys();
                no_maps_latlong_points = 0;
                while (ite.hasNext()) {
                    str = ite.next().toString();
                    if (str.equals("routes")) {
                        dataArray = (JSONArray) jObj.get(str);
                        length = dataArray.length();
                        for (i = 0; i < length; i++) {
                            latLngObjs = dataArray.getJSONObject(i);
                            iteLatLongs = latLngObjs.keys();
                            while (iteLatLongs.hasNext()) {
                                str = iteLatLongs.next().toString();
                                if (str.equals("legs")) {
                                    dataArray2 = (JSONArray) latLngObjs.get(str);
                                    for (i = 0; i < dataArray2.length(); i++) {
                                        latLngObjs2 = dataArray2.getJSONObject(i);
                                        iteLatLongs2 = latLngObjs2.keys();
                                        while (iteLatLongs2.hasNext()) {
                                            str = iteLatLongs2.next().toString();
                                            if (str.equals("steps")) {
                                                dataArray3 = (JSONArray) latLngObjs2.get(str);
                                                objMapData = new clsSharedVariables.S_claslatlong[dataArray3.length() + 10];
                                                for (i = 0; i < dataArray3.length(); i++) {
                                                    latLngObjs3 = dataArray3.getJSONObject(i);
                                                    iteLatLongs3 = latLngObjs3.keys();
                                                    while (iteLatLongs3.hasNext()) {
                                                        str = iteLatLongs3.next().toString();
                                                        if (str.equals("start_location")) {
                                                            try {
                                                                latLngObjs4 = (JSONObject) latLngObjs3.get(str);
                                                                objMapData[no_maps_latlong_points] = new S_claslatlong();
                                                                objMapData[no_maps_latlong_points].latitude = latLngObjs4.getDouble("lat");
                                                                objMapData[no_maps_latlong_points].longitude = latLngObjs4.getDouble("lng");
                                                                no_maps_latlong_points++;
                                                            } catch (Exception ex) {
                                                            }
                                                        }
                                                        if (str.equals("end_location")) {
                                                            try {
                                                                if (i >= latLngObjs3.length()) {
                                                                    latLngObjs4 = (JSONObject) latLngObjs3.get(str);

                                                                    objMapData[no_maps_latlong_points] = new S_claslatlong();
                                                                    objMapData[no_maps_latlong_points].latitude = latLngObjs4.getDouble("lat");
                                                                    objMapData[no_maps_latlong_points].longitude = latLngObjs4.getDouble("lng");

                                                                    no_maps_latlong_points++;
                                                                }
                                                            } catch (Exception ex) {
                                                            }

                                                        }
                                                    }
                                                }
                                                break; //end of steps
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (NoSuchElementException | NumberFormatException e) {
            } catch (Exception e) {
            }
            return true;
        } catch (Exception ex) {
        } finally {
            url_map = null;
            url = null;
            sb = null;
            str = null;
            jObj = null;
            dataArray = null;
            dataArray2 = null;
            dataArray3 = null;
            latLngObjs = null;
            latLngObjs2 = null;
            latLngObjs3 = null;
            latLngObjs4 = null;
            ite = null;
            iteLatLongs = null;
            iteLatLongs2 = null;
            iteLatLongs3 = null;
        }
        return false;
    }

    public void read_destination_map_data(double source_lat, double source_long, double dest_lat, double dest_long) {
        URL url_map = null;
        String[] split_str;
        StringBuilder sb;
        int ch;
        String str;
        String[] split_str1;
        int j = 0;
        BufferedReader in = null;
        try {
            url_map = new URL("http://www.yournavigation.org/api/1.0/gosmore.php?format=kml&flat=" + source_lat + "&flon=" + source_long + "&tlat=" + dest_lat + "&tlon=" + dest_long + "&v=motorcar&fast=1&layer=mapnik");
            in = new BufferedReader(
                    new InputStreamReader(url_map.openStream()));
            sb = new StringBuilder();
            while ((ch = in.read()) != -1) {
                sb.append((char) ch);
            }
            str = sb.toString();
            split_str = str.split("coordinates>");
            if (split_str.length > 1) {

                split_str = split_str[1].split("\n");
                objMapData = new clsSharedVariables.S_claslatlong[split_str.length + 2];
                for (j = 0; j < split_str.length + 2; j++) {
                    objMapData[j] = new clsSharedVariables.S_claslatlong();
                }
                objMapData[0].longitude = source_long;
                objMapData[0].latitude = source_lat;

                j = 1;
                for (int k = 0; k < split_str.length; k++) {
                    split_str1 = split_str[k].split(",");
                    if (split_str1.length >= 2) {
                        try {
                            objMapData[j].longitude = Double.parseDouble(split_str1[0].trim());
                            objMapData[j].latitude = Double.parseDouble(split_str1[1].trim());
                        } catch (Exception ex) {

                        }
                        j++;
                    }
                }
                objMapData[j].longitude = dest_long;
                objMapData[j++].latitude = dest_lat;

                clsSharedVariables.no_maps_latlong_points = j;
                clsReadFiles objReadFiles = new clsReadFiles();
                objReadFiles.write_rx_data_from_server(" reading from url no_maps_latlong_points  " + j);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(clsReadFiles.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(clsReadFiles.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            url_map = null;
            split_str = null;
            sb = null;
            str = null;
            split_str1 = null;
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(clsReadFiles.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                in = null;
            }
        }
    }

    public synchronized void read_application_version_file() {
        BufferedReader br = null;
        String line;
        File file = new File(config_filepath, "ConfigSwVersion.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    clsDefines.COMPANY_NAME_NMEA_PROT = Byte.parseByte(line);
                    break;
                }
            } catch (Exception ex) {
            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {
                }
            }
        }
    }

    public synchronized void write_application_version_file(String str) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(config_filepath, "ConfigSwVersion.txt");
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
        }
    }

    public void write_data_imp_log(String str) {
        String file_path = log_filepath.getPath();
        if (!getHardDriveDetected()) {
            file_path = media_path;
        }
        File file = new File(file_path + "/imp_data.log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 5000000) {
                delete_lines_file(file, 20 * 10, "tempimp_data1.txt");
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
            file_path = null;
        }
    }

    public void write_data_imp1_log(String str) {
        String file_path = log_filepath.getPath();
        if (!getHardDriveDetected()) {
            file_path = media_path;
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(file_path, "imp_data.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 5000000) {
                delete_lines_file(file, 20 * 10, "tempimp_data.txt");
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(Calendar.getInstance().getTime().toString() + " " + str);

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;

        }
    }

    public void delete_alco_folders() {
        File alcobrakeDir = new File(video_filepath + "/ALCOBRAKE");

        if (alcobrakeDir.exists() && alcobrakeDir.isDirectory()) {
            // Step 1: Get subdirectories
            File[] subfolders = alcobrakeDir.listFiles(File::isDirectory);

            if (subfolders != null && subfolders.length > 30) {
                // Step 2: Sort subfolders by name
                Arrays.sort(subfolders, (a, b) -> a.getName().compareTo(b.getName()));

                // Step 3: Delete the first folder in the sorted list
                File folderToDelete = subfolders[0];

                if (deleteFolder(folderToDelete)) {
                    System.out.println("Deleted folder: " + folderToDelete.getName());
                } else {
                    System.out.println("Failed to delete folder: " + folderToDelete.getName());
                }
            } else {
                System.out.println("There are 30 or fewer folders, no deletion needed.");
            }
        } else {
            System.out.println("The specified alcobrake directory does not exist or is not a directory.");
        }
    }

    private boolean deleteFolder(File folder) {
        // Recursively delete files and subfolders within the folder
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file); // Recursively delete subfolders
                } else {
                    file.delete(); // Delete file
                }
            }
        }
        return folder.delete(); // Finally, delete the folder itself
    }

    public void write_data_alco_log(String str,String snap) {
        String file_path = log_filepath.getPath();
        if (!getHardDriveDetected()) {
            return;
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(file_path, "alco_data.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 5000000) {
                delete_lines_file(file, 20 * 10, "tempalco_data.txt");
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            Calendar cal = Calendar.getInstance();
           final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
              String formattedCurrentTime = sdf1.format(cal.getTime());
           String formattedDate = sdf.format(cal.getInstance().getTime());
           pw.println(formattedCurrentTime+ "," + formattedDate + "," + str + "," + snap);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;

        }
    }
public synchronized String[] read_data_alco_log() {
    String[] data = null;
    String file_path = log_filepath + "/alco_data.txt";  // Ensure log_filepath is correctly initialized
    File file = new File(file_path);

    if (file.exists()) {
        try {
            // Load the data from the file as a List of Strings
            List<String> listOfStrings = Files.readAllLines(Paths.get(file_path));

            // Convert List to Array (String[])
            data = listOfStrings.toArray(new String[0]);
        } catch (IOException ex) {
            Logger.getLogger(clsReadFiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            file = null; // Optional cleanup (though file will be out of scope soon)
        }
    }

    return data;
}

    private boolean copyFile(String inputPath, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        byte[] buffer = new byte[1024];
        int read;
        try {
            File outDir = new File(outputPath);
            File inDir = new File(inputPath);
            if (inDir.isDirectory()) {
            } else if (inDir.isFile()) {
                try {
                    in = new FileInputStream(inDir);
                    if (!outDir.exists()) {
                        outDir.createNewFile();
                    }
                    out = new FileOutputStream(outDir, false);
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }
                } catch (Exception ex) {
                } finally {
                    out.flush();
                    out.close();
                    in.close();
                    in = null;
                    out = null;
                    outDir = null;
                    inDir = null;
                }
                return true;
            }
        } catch (Exception e) {
        } finally {
            buffer = null;
        }
        return false;
    }

    public boolean write_log_tick_data() {
        if (RESET_OBU_CALLED == false) {
            File file = new File(main_route_path + "/fileLogTick.log");
            FileOutputStream f = null;
            PrintWriter pw = null;
            try {
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                pw.println(1);
                pw.flush();
                pw.close();
                f.close();
            } catch (FileNotFoundException e) {
                return false;
            } catch (IOException e) {
                return false;
            } catch (Exception e) {
                return false;
            } finally {
                file = null;
                pw = null;
                f = null;
            }
            return true;
        }
        return false;
    }

    public boolean check_harddisk_mounted() {
        File f = new File("/dev/disk/by-uuid");
        File[] files;
        int i = 0;
        boolean found = false;
        try {
            if (f.exists()) {
                try {
                    files = f.listFiles();
                    for (i = 0; files != null && i < files.length; i++) {
                        if (files[i].getName().endsWith("3339")) {
                            found = true;
                            break;
                        }
                    }
                } catch (Exception ex) {
                }
            }
            //  runCmd("sudo mount /mnt/Recorder");
        } catch (Exception ex) {

        } finally {
            f = null;
            files = null;
        }
        if (found == true) {
            if (check_harddisk_mount() == true) {
                return true;
            }
        }
        return false;
    }

    public boolean harddisk_mount() {
        runCmd("sudo mount " + HARDDISK_PATH);
        return check_harddisk_mounted();
    }

    public boolean harddisk_automount() {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    // TODO add your handling code here:
                    clsReadFiles objReadFiles = new clsReadFiles();
                    objReadFiles.harddisk_power_on(false);
                    publish("Harddisk Power Off..");
                    Thread.sleep(1000);
                    objReadFiles.harddisk_power_on(true);
                    publish("Harddisk Power on..");
                    Thread.sleep(1000);
                    objReadFiles.harddisk_mount();
                    publish("Harddisk Mounting..");
                    Thread.sleep(1000);
                    if (objReadFiles.check_harddisk_mounted() == true) {
                        publish("Harddisk Mounted");
                    } else {
                        publish("Harddisk Not Mounted");
                    }
                } catch (InterruptedException ex) {
                    //   Logger.getLogger(panDiagDigOut.class
                    //  .getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }

            @Override
            protected void process(List chunks) {

            }

            @Override
            protected void done() {

            }
        };
        sw1.execute();
        return false;
    }

    public void harddisk_power_on(boolean value) {
        if (value == true) {
            clsSharedVariables.setHdStatEnableDisable((byte) 1); // harddisk power on
        } else {
            clsSharedVariables.setHdStatEnableDisable((byte) 0);// harddisk power off disconnected
        }
    }

    public boolean check_harddisk_mount_state() {
        File f = new File("/proc/mounts");
        BufferedReader br = null;
        String line;
        if (f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));
                try {
                    while ((line = br.readLine()) != null) {
                        if (line.contains("Recorder")) {
                            if (line.contains("rw,")) {
                                return true;
                            }
                        }
                    }
                } catch (Exception ex) {
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(clsReadFiles.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                        br = null;
                        f = null;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(clsReadFiles.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private void delete_extra_3339OBU_names(String act_name) {
        if (OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
            File f = new File(media_path);
            File[] lst_files = f.listFiles();
            for (File lst_file : lst_files) {
                if (lst_file.getName().contains("3339OBU")) {
                    if (!lst_file.getAbsolutePath().equals(act_name)) {
                        try {
                            FileUtils.deleteDirectory(lst_file);
                        } catch (Exception ex) {

                        }
                    }
                }
            }

        }
    }

    public boolean check_harddisk_mount() {
        File f = new File("/proc/mounts");
        BufferedReader br = null;
        String line;
        String[] split_str;
        boolean found = false;
        boolean hd_detect = false;
        String str = "";
        byte i = 0;

        try {
            if (f.exists()) {
                try {
                    br = new BufferedReader(new FileReader(f));
                    try {
                        while ((line = br.readLine()) != null) {
                            if (line.contains("Recorder")) {
                                if (line.contains("rw,")) {
                                    split_str = line.split(" ");
                                    for (i = 0; i < split_str.length; i++) {
                                        if (split_str[i].contains("Recorder")) {
                                            split_str[i] = split_str[i].trim();
                                            clsDefines.HARDDISK_PATH = split_str[i];
                                            hd_detect = true;
                                            return true;
                                        }
                                    }
                                    return true;
                                } else if (line.contains("ro,")) {
                                    runCmd("sudo mount " + HARDDISK_PATH);
                                    str = line;
                                    found = true;
                                }
                            }
                        }
                    } catch (Exception ex) {
                    } finally {
                        if (br != null) {
                            br.close();
                            br = null;
                            f = null;
                        }
                    }
                } catch (Exception ex) {
                } finally {
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                }
            }

            if (found == true) {
                split_str = str.split(" ");
                if (split_str.length > 1) {
                    if (split_str[0].startsWith("/dev")) {
                        if (split_str[1].contains("Recorder")) {
                            clsDefines.HARDDISK_PATH = split_str[1].trim();
                        }
                        runCmd("sudo mount -o remount,rw " + split_str[0] + " " + HARDDISK_PATH);
                        if (check_harddisk_mountonly() == false) {
                            runCmd("sudo mount " + HARDDISK_PATH);
                            if (check_harddisk_mountonly() == false) {
                                write_data_imp_log("HD Mounted readonly");
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                }
            } else {
                runCmd("sudo mount " + HARDDISK_PATH);
                if (check_harddisk_mountonly() == false) {
                    write_data_imp_log("HD Mounted readonly");
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception ex) {
        } finally {
            f = null;
            br = null;
            line = null;
            split_str = null;
            str = null;
        }
        return false;
    }

    public boolean check_3339OBU_mount() {
        File f = new File("/proc/mounts");
        BufferedReader br = null;
        String line;
        String[] split_str;
        byte i;
        try {
            if (f.exists()) {
                try {
                    br = new BufferedReader(new FileReader(f));
                    try {
                        while ((line = br.readLine()) != null) {
                            if (line.contains("3339OBU")) {
                                if (line.contains("rw,")) {
                                    split_str = line.split(" ", -1);
                                    for (i = 0; i < split_str.length; i++) {
                                        if (split_str[i].contains("3339OBU")) {
                                            split_str[i] = split_str[i].trim();
                                            clsDefines.ROUTEFS_PATH = split_str[i];
                                            return true;
                                        }
                                    }
                                    return true;
                                } else if (line.contains("ro,")) {
                                    split_str = line.split(" ", -1);
                                    for (i = 0; i < split_str.length; i++) {
                                        if (split_str[i].contains("3339OBU")) {
                                            split_str[i] = split_str[i].trim();
                                            clsDefines.ROUTEFS_PATH = split_str[i];
                                            Process process = null;
                                            Runtime rt = null;
                                            try {
                                                rt = Runtime.getRuntime();
                                                runCmd("sudo mount " + clsDefines.ROUTEFS_PATH);
                                                runCmd("sudo chmod +rwx " + clsDefines.ROUTEFS_PATH);
                                                process = rt.exec(new String[]{"bash", "-c", "sudo chmod +rwx " + clsDefines.ROUTEFS_PATH});
                                                process.waitFor();
                                            } catch (IOException e) {
                                                if (rt != null) {
                                                    rt.gc();
                                                }
                                            } catch (Exception ex) {
                                                if (rt != null) {
                                                    rt.gc();
                                                }
                                            } finally {
                                                try {
                                                    if (process != null) {
                                                        process.getInputStream().close();
                                                        process.getOutputStream().close();
                                                        process.getErrorStream().close();
                                                        process.destroy();
                                                        process = null;
                                                        rt = null;
                                                    }
                                                } catch (IOException e) {
                                                } catch (Exception ex) {
                                                }
                                            }
                                            delete_extra_3339OBU_names(split_str[i]);
                                            return true;
                                        }
                                    }
                                    return true;
                                }
                            }
                        }
                    } catch (Exception ex) {
                    } finally {
                        if (br != null) {
                            br.close();
                            br = null;
                            f = null;
                        }
                    }
                } catch (Exception ex) {
                } finally {
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                }
            }

        } catch (Exception ex) {
        } finally {
            f = null;
            br = null;
            line = null;
            split_str = null;
        }
        clsDefines.ROUTEFS_PATH = "/media/" + clsDefines.obu_board_username + "/3339OBU";
        return false;
    }

    public boolean check_harddisk_mountonly() {
        File f = new File("/proc/mounts");
        BufferedReader br = null;
        String line;

        try {
            if (f.exists()) {
                try {
                    br = new BufferedReader(new FileReader(f));
                    try {
                        while ((line = br.readLine()) != null) {
                            if (line.contains("Recorder")) {
                                if (line.contains("rw,")) {
                                    write_data_imp_log("HD Mounted rw ");
                                    return true;
                                }
                            }

                        }
                    } catch (Exception ex) {

                    } finally {
                        if (br != null) {
                            br.close();
                            br = null;

                            f = null;
                        }
                    }
                } catch (Exception ex) {

                }
            }

        } catch (Exception ex) {

        } finally {
            f = null;
            br = null;
            line = null;

        }
        write_data_imp_log("HD  mounted ro");
        return false;
    }

    public boolean write_lcd_touch_monitoring_data() {
        String file_path = media_filepath.getPath();
        if (!getHardDriveDetected()) {
            file_path = media_path;
        }
        File file = new File(file_path + "/fileLcdTouchMonitor.log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            lcd_monitoring_cnt++;
            pw.println(String.valueOf(lcd_monitoring_cnt) + "," + String.valueOf(Calendar.getInstance().getTimeInMillis()));
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
            file_path = null;
        }
        return true;
    }

    public synchronized void read_lcd_touch_monitoring_data() {
        BufferedReader br = null;
        String line;
        String[] split_str;
        String file_path = media_filepath.getPath();
        if (!getHardDriveDetected()) {
            file_path = media_path;
        }
        File file = new File(file_path + "/fileLcdTouchMonitor.log");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    lcd_monitoring_cnt = Byte.parseByte(split_str[0]);
                    MainFrmIts.lcd_monitoring_date = Long.parseLong(split_str[1]);

                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                    file_path = null;
                } catch (IOException ex) {
                    //Logger.getLogger(clsReadFiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (MainFrmIts.lcd_monitoring_date + 3600000 > Calendar.getInstance().getTimeInMillis()) {
            lcd_monitoring_cnt = 0;
            MainFrmIts.lcd_monitoring_date = Calendar.getInstance().getTimeInMillis();
            write_lcd_touch_monitoring_data();
        }
    }

    public boolean write_harddisk_monitoring_data() {
        String file_path = media_filepath.getPath();
        if (!getHardDriveDetected()) {
            file_path = media_path;
        }
        File file = new File(file_path + "/fileHardDisk.log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            MainFrmIts.hardisk_monitoring_cnt++;
            pw.println(String.valueOf(MainFrmIts.hardisk_monitoring_cnt) + "," + String.valueOf(Calendar.getInstance().getTimeInMillis()));

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            file = null;
            pw = null;
            f = null;
            file_path = null;
        }
        return true;
    }

    public synchronized void read_harddisk_monitoring_data() {
        BufferedReader br = null;
        String line;
        String[] split_str;
        String file_path = media_filepath.getPath();
        if (!getHardDriveDetected()) {
            file_path = media_path;
        }
        File file = new File(file_path + "/fileHardDisk.log");

        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    MainFrmIts.hardisk_monitoring_cnt = Byte.parseByte(split_str[0]);
                    MainFrmIts.hardisk_monitoring_date = Long.parseLong(split_str[1]);

                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                    file_path = null;
                } catch (IOException ex) {
                    //Logger.getLogger(clsReadFiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (MainFrmIts.hardisk_monitoring_date + 3600000 > Calendar.getInstance().getTimeInMillis()) {
            MainFrmIts.hardisk_monitoring_cnt = 0;
            MainFrmIts.hardisk_monitoring_date = Calendar.getInstance().getTimeInMillis();
            write_harddisk_monitoring_data();
        }
    }

    public boolean write_logOnvifenabled_data() {

        File file = new File(config_filepath + "/fileLogEnable.log");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getOnvifSupported()
                    + "," + clsSharedVariables.getSeparateGPS());

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            runCmd("sudo sync");
            file = null;
            pw = null;
            f = null;

        }
        return true;
    }

    public synchronized void read_logOnvifenabled_data() {
        BufferedReader br = null;
        String line;
        String[] split_str;
        File file = new File(config_filepath + "/fileLogEnable.log");

        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    clsSharedVariables.setOnvifSupported(Boolean.parseBoolean(split_str[0]));
                    clsSharedVariables.setSeparateGPS(Boolean.parseBoolean(split_str[1]));

                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;

                } catch (IOException ex) {
                    //Logger.getLogger(clsReadFiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public boolean write_imei_no_data() {

        File file = new File(config_filepath + "/fileImei.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getImeiNo() + "," + clsSharedVariables.getNetworkOperatorName());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            file = null;
            pw = null;
            f = null;

        }
        return true;
    }

    public synchronized void read_imei_no_data() {
        BufferedReader br = null;
        String line;
        File file = new File(config_filepath + "/fileImei.txt");
        String[] split_str;
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {

                    if (line.length() >= 15) {
                        clsSharedVariables.setImeiNo(line.trim().substring(0, 15));
                    }

                    split_str = line.split(",", -1);
                    if (split_str[0].length() >= 15) {
                        clsSharedVariables.setImeiNo(split_str[0].substring(0, 15));
                    }

                    break;

                }
            } catch (Exception ex) {
            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                    split_str = null;

                } catch (IOException ex) {
                }
            }
        }

    }

    public synchronized void read_product_storage_name() {
        BufferedReader br = null;
        String line;
        String[] split_str;
        File file = new File(config_filepath, "ConfigProductStorageName.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    clsSharedVariables.setStorageType(Byte.parseByte(split_str[1]));
                    if (split_str.length > 2) {
                        clsSharedVariables.setRestartPortsDetected(Boolean.parseBoolean(split_str[2]));
                        if (split_str.length > 3) {
                            clsSharedVariables.setQuecModuleRev(Byte.parseByte(split_str[3]));
                        }
                        if (split_str.length > 4) {
                            clsSharedVariables.setCameraType(Byte.parseByte(split_str[4]));
                        }
                         if (split_str.length > 5) {
                            clsSharedVariables.set4CamsLive(Boolean.parseBoolean(split_str[5]));

                        }
                        if (split_str.length > 6) {
                            clsSharedVariables.setApcEnabled(Boolean.parseBoolean(split_str[6]));

                        }
                        if (split_str.length > 7) {
                            clsSharedVariables.setFmRadioEnable(Boolean.parseBoolean(split_str[7]));

                        }
                        if (split_str.length > 8) {
                            clsSharedVariables.setPedEnable(Boolean.parseBoolean(split_str[8]));

                        }
                        if (split_str.length > 9) {
                            clsSharedVariables.setApcEnabled1(Boolean.parseBoolean(split_str[9]));

                        }
                        if (split_str.length > 10) {
                            clsSharedVariables.setPmiEnable(Boolean.parseBoolean(split_str[10]));

                        }
                        if (split_str.length > 11) {
                            clsSharedVariables.setSharedNetwork(Boolean.parseBoolean(split_str[11]));

                        }
                        if (split_str.length > 12) {
                            clsSharedVariables.setSplAudioAnnouncement(Boolean.parseBoolean(split_str[12]));

                        }
                    }
                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {
                }
            }
        }
    }

    public synchronized void write_product_storage_name() {

        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(config_filepath, "ConfigProductStorageName.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsDefines.PRODUCT_16833 + "," + clsSharedVariables.getStorageType()
                    + "," + clsSharedVariables.getRestartPortsDetected() + "," + clsSharedVariables.getQuecModuleRev()
                    + "," + clsSharedVariables.getCameraType() + "," + clsSharedVariables.get4CamsLive() + ","
                    + clsSharedVariables.getApcEnabled() + "," + clsSharedVariables.getFmRadioEnable() + ","
                    + clsSharedVariables.getPedEnable() + "," + clsSharedVariables.getApcEnabled1() + ","
                    + clsSharedVariables.getPmiEnable() + "," + clsSharedVariables.getSharedNetwork()
                    + "," + clsSharedVariables.getSplAudioAnnouncement());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            runCmd("sudo sync");
            f = null;
            pw = null;
            file = null;
        }
    }

    public void write_line_cross_detect_log(String str) {
        String file_path = log_filepath.getPath();
        File file = new File(file_path + "/line_cross_data.log");
        FileOutputStream f = null;
        PrintWriter pw = null;

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (file.length() > 5000000) {
                delete_lines_file(file, 20 * 10, "templine_cross_data.txt");
            }
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
            str = null;
            file_path = null;
        }
    }

    public boolean write_embedded_switch_profile_data() {
        File file = new File(config_filepath + "/fileEmbSimProfile.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getEmbSimAutoSwitch() + "," + clsSharedVariables.getEmbSimFixedSwitchMode() + "," + clsSharedVariables.getEmbNorSimType());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            runCmd("sudo sync");
            file = null;
            pw = null;
            f = null;
        }
        return true;
    }

    public synchronized void read_mac_address() {
        BufferedReader br = null;
        String line;
        File file = new File("/sys/class/net/eth0/address");

        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (line.length() >= 3) {
                        clsSharedVariables.setMacAddress(line.trim());
                    }
                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {
                }
            }
        }
    }

    public synchronized void read_embedded_switch_profile_data() {
        BufferedReader br = null;
        String line;
        String[] split_str;
        File file = new File(config_filepath + "/fileEmbSimProfile.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",");
                    clsSharedVariables.setEmbSimAutoSwitch(Byte.parseByte(split_str[0]));
                    if (split_str.length > 1) {
                        clsSharedVariables.setEmbSimFixedSwitchMode(Byte.parseByte(split_str[1]));
                    }
                    if (split_str.length > 2) {
                        clsSharedVariables.setEmbNorSimType(Byte.parseByte(split_str[2]));
                    }
                    break;
                }
            } catch (Exception ex) {
            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {
                }
            }
        }
    }

    public synchronized void read_fmradio_status() {
        BufferedReader br = null;
        String line;
        //Get the text file
        File file = new File(config_filepath, "fmradio_status.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    clsSharedVariables.setFmRadioStatus(Boolean.parseBoolean(line));
                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }
    }

    public synchronized void write_fmradio_status() {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(config_filepath, "fmradio_status.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getFmRadioStatus());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized void read_internal_battery_state() {
        BufferedReader br = null;

        String line;
        //Get the text file
        File file = new File(config_filepath, "intBatState.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    clsSharedVariables.setPrevIntBatState(Byte.parseByte(line));
                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }
    }

    public synchronized void write_internal_battery_state() {

        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(config_filepath, "intBatState.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getPrevIntBatState());
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        } catch (Exception ex) {

        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized void read_fstab_file() {
        BufferedReader br = null;
        String line;
        File file = new File("/etc/fstab");
        boolean label_found = false;
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (line.contains("LABEL")) {
                        label_found = true;
                        break;
                    }
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
            if (label_found == false) {
                clsDefines.HARDDISK_PATH = "/mnt/Recorder";
            } else {
                clsDefines.HARDDISK_PATH = "/media/" + clsDefines.obu_board_username + "/Recorder";
            }
            if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
                clsDefines.HARDDISK_PATH = "/media/" + clsDefines.obu_board_username + "/Recorder";
            }
        }
    }

    public synchronized void write_frame_seqno5() {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(main_route_path, "file_frame_seqno5.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getPktSeqNoIp5());
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized boolean write_frame_seqno5_hd() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(media_filepath, "file_frame_seqno5.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getPktSeqNoIp5());
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
            return true;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
        return false;
    }

    public synchronized void write_event_frame_seqno5() {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(main_route_path, "file_event_frame_seqno5.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getEventPktSeqNo5());
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized boolean write_event_frame_seqno5_hd() {
        if (!clsSharedVariables.getHardDriveDetected()) {
            return false;
        }
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(media_filepath, "file_event_frame_seqno5.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(clsSharedVariables.getEventPktSeqNo5());
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
            return true;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
        return false;
    }

    public synchronized void write_driver_conductor_login_info(String str) {
        FileOutputStream f = null;
        PrintWriter pw = null;

        File file = new File(log_filepath, "/Login_Details.txt");
        try {
            if (file.length() > 20000) {
                delete_lines_file_driver_conductor_data("/Login_Details.txt", 20);
            }
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_driver_conductor_login_info() {
        BufferedReader br = null;
        String line;
        int cnt;
        String[] data = null;
        int i = 0;
        File file = new File(log_filepath, "/Login_Details.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                cnt = (int) (br.lines().count());
                data = new String[cnt];
                br.close();
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    data[i++] = (line);
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }
        return data;
    }

    public void delete_lines_file_driver_conductor_data(String filename, int no_lines) {
        try {
            File targetFile = new File(log_filepath, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));
                File tempFile = new File(log_filepath, "/Login_Details.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {
                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                targetBuf.close();
                printTemp.close();
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    
}
