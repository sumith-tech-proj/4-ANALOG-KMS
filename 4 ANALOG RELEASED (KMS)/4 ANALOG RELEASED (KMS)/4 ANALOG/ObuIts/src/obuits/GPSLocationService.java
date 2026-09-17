package obuits;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static obuits.clsDefines.ASCII_NUM;
import static obuits.clsDefines.GPS_BAUDRATE;
import static obuits.clsDefines.GPS_FIXED;
import static obuits.clsDefines.GPS_NOT_FIXED;
import static obuits.clsDefines.END_PKT;
import static obuits.clsDefines.START_PKT;
import static obuits.clsDefines.TRIP_END;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsNmeaDequeList.addNmea;
import static obuits.clsSharedVariables.getCurAutoTripStat;
import static obuits.clsSharedVariables.getCurDate;
import static obuits.clsSharedVariables.getCurLatitude;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getCurRouteNo;
import static obuits.clsSharedVariables.getCurRouteStat;
import static obuits.clsSharedVariables.getCurSec;
import static obuits.clsSharedVariables.getCurTime;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getCurTripStat;
import static obuits.clsSharedVariables.getPrevLat;
import static obuits.clsSharedVariables.getPrevLong;
import static obuits.clsSharedVariables.getSimulationEnabled;
import static obuits.clsSharedVariables.getSimulationInc;
import static obuits.clsSharedVariables.getSimulationcnt;
import static obuits.clsSharedVariables.getTravelledDis;
import static obuits.clsSharedVariables.get_nmea_gpgga;
import static obuits.clsSharedVariables.get_nmea_gpgsa;
import static obuits.clsSharedVariables.get_nmea_gpgsv;
import static obuits.clsSharedVariables.get_nmea_gpvtg;
import static obuits.clsSharedVariables.gps_simul_data;
import static obuits.clsSharedVariables.setCurDate;
import static obuits.clsSharedVariables.setCurHeading;
import static obuits.clsSharedVariables.setCurLatitude;
import static obuits.clsSharedVariables.setCurLongitude;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSharedVariables.setCurSec;
import static obuits.clsSharedVariables.setCurTime;
import static obuits.clsSharedVariables.setGpsSpeed;
import static obuits.clsSharedVariables.setGpsState;
import static obuits.clsSharedVariables.setPrevLat;
import static obuits.clsSharedVariables.setPrevLong;
import static obuits.clsSharedVariables.setSimulationEnabled;
import static obuits.clsSharedVariables.setSimulationInc;
import static obuits.clsSharedVariables.setTravelledDis;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import jssc.*;
import static obuits.MainFrmIts.restart_module_ports_changed;
import static obuits.clsDefines.ASCII_ZERO;
import static obuits.clsDefines.CHAR_COMMA;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.GPS_OFF;
import static obuits.clsDefines.GPS_SEPARATE_BAUDRATE;
import static obuits.clsDefines.GPS_SERIAL_PORT_SERIAL_NO;
import static obuits.clsDefines.OBU_BRD_TYPE;
import static obuits.clsDefines.RASPBERRY_BOARD;
import static obuits.clsDefines.TINKER_BOARD;
import static obuits.clsDefines.obu_images_filepath;
import static obuits.clsDefines.odometer_enable;
import static obuits.clsPacketTypes.NORMAL_PKT;
import static obuits.clsSharedVariables.getFirstFixSec;
import static obuits.clsSharedVariables.getGpsDiagEnabled;
import static obuits.clsSharedVariables.getGpsState;
import static obuits.clsSharedVariables.getGsmModuleOn;
import static obuits.clsSharedVariables.getOBUID;
import static obuits.clsSharedVariables.getOdometerPrevLat;
import static obuits.clsSharedVariables.getOdometerPrevLong;
import static obuits.clsSharedVariables.get_driver_id;
import static obuits.clsSharedVariables.get_gprs_normal_mode_val;
import static obuits.clsSharedVariables.get_gprs_sleep_val;
import static obuits.clsSharedVariables.get_nmea_came;
import static obuits.clsSharedVariables.get_sleep_mode_status;
import static obuits.clsSharedVariables.gps_invalid_data_cnt;
import static obuits.clsSharedVariables.gps_lost_comm_cnt;
import static obuits.clsSharedVariables.setCurLatitudeDir;
import static obuits.clsSharedVariables.setCurLongitudeDir;
import static obuits.clsSharedVariables.setCurNoSatelites;
import static obuits.clsSharedVariables.setFirstFixSec;
import static obuits.clsSharedVariables.setGpsDiagEnabled;
import static obuits.clsSharedVariables.setGpsNmeaPktStatus;
import static obuits.clsSharedVariables.setOdometerPrevLat;
import static obuits.clsSharedVariables.setOdometerPrevLong;
import static obuits.clsSharedVariables.setSevenPortsConfigured;
import static obuits.clsSharedVariables.set_gps_comport_connected;
import static obuits.clsSharedVariables.set_gps_nmea_up;
import static obuits.clsSharedVariables.set_nmea_came;

public class GPSLocationService {

    private static long gps_time_start = 0;
    private static long gps_time_start_sleep = 0;
    private static long rs232_gps_time_start = 0;
    private static long ethernet_gps_time_start = 0;
    static byte no_gps_cnt = 0;
    static short gprmc_inc_cnt = 0;

    static final byte CHAR_DOLLAR = 36;
    static final byte CHAR_ATTHERATE = 64;
    static final byte CHAR_STAR = 42;
    static final byte CHAR_NEWLINE = 10;
    static final byte CHAR_LINEFEED = 13;
    static StringBuilder nmea_concat_data = new StringBuilder();
    static StringBuilder rs232_nmea_concat_data = new StringBuilder();
    static StringBuilder ethernet_nmea_concat_data = new StringBuilder();
    final String pattern = "00000000.00";
    final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    final DecimalFormat decimalFormat_lat = new DecimalFormat("00.00000000");
    public static boolean gps_nmea_fixed = false;
    gpsDriving objDriving = null;

    SimpleDateFormat sdf_nmea = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static boolean first_time_to_update_systime = false;
    private static boolean update_systime_every_30mins = false;

    static clsBusStopDetection objBusStopDetect = new clsBusStopDetection();
    String altitude = "0.0";
    byte no_sat = 0;
    processGprmcData objProcessGprmc;
    Thread thrProcessGprmcData;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yy");
    SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat sdf_time_hwclock = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    SimpleDateFormat sdf_date_16833 = new SimpleDateFormat("ddMMyy");
    SimpleDateFormat sdf_time_16833 = new SimpleDateFormat("HHmmss");
    private static byte app_up_time_inc = 0;
    ReadNmeaDataFromSerialPort objNmea = null;
    static SerialPort gps_serialPort = null;
    static JLabel imgGpsNew;
    static byte gps_at_gprs_ports_available_cnt = 0;

    static long power_on_gps_sec = 0;
    static boolean power_on_gps = false;
    String gps_port_name = "/dev/ttyUSB1";
//    clsRs232GpsQueue objRs232GpsQueue = new clsRs232GpsQueue();
//    clsRs232GpsQueue objEthernetGpsQueue = new clsRs232GpsQueue();

    GPSLocationService(JLabel imgGpsN) {
        imgGpsNew = imgGpsN;
        startNmea();
    }

    String l_cur_date;
    String l_cur_time;
    Date gpsdate = new Date();
    String[] split_str;
    String str;
    String t1;
    String d1;
    Calendar cal;
    StringBuilder sbdate = new StringBuilder();
    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
    cls16833Protocols obj16833 = new cls16833Protocols();
    clsSmcTrackingPackets objSmc = new clsSmcTrackingPackets();
    String lat;
    String longi;
    char lat_dir = 'N';
    char longi_dir = 'E';
    double loc_dis = 0.0;
    short speed = 0;
    float heading = 0;
    double velocity;

    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(new String[]{"bash", "-c", cmd});
            process.waitFor(5, TimeUnit.SECONDS);
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

    // Method to execute a command for updating time
    // Parameters:
    //   cmd - Command to be executed for updating time
    // Returns:
    //   boolean - true if the command executed successfully, false otherwise
    private boolean runCmdUpdateTime(String cmd) {
        Process process = null; // Initialize a Process object to handle the executed command
        Runtime rt = null; // Initialize a Runtime object to handle runtime operations
        try {
            rt = Runtime.getRuntime(); // Get the current runtime environment
            process = rt.exec(new String[]{"bash", "-c", cmd}); // Execute the command using Bash
            process.waitFor(); // Wait for the process to finish
            return true; // Return true indicating successful execution
        } catch (IOException e) {
            if (rt != null) {
                rt.gc(); // If an IO exception occurs, attempt garbage collection
            }
            return false; // Return false indicating execution failure
        } catch (Exception ex) {
            if (rt != null) {
                rt.gc(); // If an exception occurs, attempt garbage collection
            }
            return false; // Return false indicating execution failure
        } finally {
            try {
                if (process != null) {
                    // Close input, output, and error streams, and destroy the process
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                    process = null; // Set the process object to null
                    rt = null; // Set the runtime object to null
                }
            } catch (IOException e) {
                // Ignore IOException
            } catch (Exception ex) {
                // Ignore other exceptions
            }
        }
    }

    // Method to update the system time in TinkerOS using an external RTC module (DS1307)
    // Parameters:
    //   d - Date object representing the new date and time to be set
    private void update_time_tinkeros(Date d) {
        try {
            DS1307RTC obj; // Declare a variable to hold the DS1307RTC object
            obj = new DS1307RTC(); // Instantiate a DS1307RTC object

            obj.setDate(d); // Set the date of the DS1307RTC object to the specified date

            // Set the system time using the 'hwclock' command with the specified date
            runCmdUpdateTime("sudo hwclock --set --date=\"" + d.toString() + "\"");

            // Synchronize hardware clock to system time in UTC
            runCmdUpdateTime("sudo hwclock --hctosys --utc");
            runCmdUpdateTime("sudo hwclock --hctosys --utc");
        } catch (IOException ex) {
            // Log any IOException that occurs during the process
            Logger.getLogger(MainFrmIts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to handle incoming NMEA sentences received from a GPS receiver
    // Parameters:
    //   nmea - NMEA sentence received from the GPS receiver
    public void onNmeaReceived(String nmea) {

        //$GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A
        //$GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47
        cal = Calendar.getInstance();
        long gps_end_time;
        long dur;
        long rs232_dur = 0;
        long ethernet_dur = 0;
        int inc;
        long loc_sec = 0;
        //  double lat;

        set_gps_nmea_up((byte) 0);
        gps_end_time = (cal.getTimeInMillis() / 1000);
        objwatDog.setgps_watchdog_val((byte) 2);

        nmea = nmea.trim();
        if (nmea.equals("")) {
            return;
        }

        if (nmea.startsWith("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC")) {
            if (clsDefines.SIM_LOG_COLLECT == true) {
                objReadFiles.write_gps_data_to_file(nmea);
            }
            if (getSimulationEnabled() == true && nmea.equals("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC")) {
                try {
                    inc = getSimulationInc();
                    if (inc < getSimulationcnt() - 1 && inc < 9999) {
                        nmea = gps_simul_data[inc];
                        inc++;
                        setSimulationInc(inc);
                        addNmea(nmea);
                    } else {
                        setSimulationInc(0);
                    }
                } catch (Exception ex) {
                    setSimulationEnabled(false);
                }

            }
            set_nmea_came(true);
            split_str = nmea.split(",");

            //////System.out.println(nmea);
            if (split_str.length > clsDefines.GPS_GPRMC_STATUS && (split_str[clsDefines.GPS_GPRMC_STATUS].equals("A") || split_str[clsDefines.GPS_GPRMC_STATUS].equals("D"))) {

                try {
                    str = split_str[clsDefines.GPS_GPRMC_DATE];
                    sbdate = sbdate.delete(0, sbdate.length());
                    sbdate.append(str.charAt(0));
                    sbdate.append(str.charAt(1));
                    sbdate.append("/");
                    sbdate.append(str.charAt(2));
                    sbdate.append(str.charAt(3));
                    sbdate.append("/");
                    sbdate.append(str.charAt(4));
                    sbdate.append(str.charAt(5));
                    sbdate.append(" ");

                    str = split_str[clsDefines.GPS_GPRMC_TIME];
                    sbdate.append(str.charAt(0));
                    sbdate.append(str.charAt(1));
                    sbdate.append(":");
                    sbdate.append(str.charAt(2));
                    sbdate.append(str.charAt(3));
                    sbdate.append(":");
                    sbdate.append(str.charAt(4));
                    sbdate.append(str.charAt(5));
                    str = split_str[clsDefines.GPS_GPRMC_SPEED];
                    if (str.equals("")) {
                        speed = 0;
                    } else {
                        speed = (short) Float.parseFloat(str);
                    }

                    gpsdate = sdf.parse(sbdate.toString()); //d1 + " " + t1);
                    cal.setTime(gpsdate);
                    cal.add(Calendar.HOUR_OF_DAY, 5);
                    cal.add(Calendar.MINUTE, 30);
                    gpsdate = cal.getTime();

                    setCurDate(sdf_date.format(gpsdate));
                    setCurTime(sdf_time.format(gpsdate));

                    ///runCmd("sudo hwclock --set --date=")
                    loc_sec = gpsdate.getTime();

                    if (getCurSec() == 0) {
                        setFirstFixSec(loc_sec);

                        try {
                            SwingUtilities.invokeLater(() -> {
                                if (OBU_BRD_TYPE == TINKER_BOARD) {
                                    update_time_tinkeros(gpsdate);
                                } else {
                                    runCmdUpdateTime("sudo hwclock --set --date=\"" + gpsdate.toString() + "\"");

                                    runCmdUpdateTime("sudo hwclock --hctosys --utc");
                                }
                            });
                        } catch (Exception ex) {
                        }

                    } else if (getFirstFixSec() == 0) {
                        setFirstFixSec(loc_sec);
                        try {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    runCmdUpdateTime("sudo hwclock --set --date=\"" + gpsdate.toString() + "\"");

                                    runCmdUpdateTime("sudo hwclock --hctosys --utc");

                                    // runCmdUpdateTime("sudo timedatectl set-time "+sdf_time_hwclock.format(gpsdate));
                                }
                            });
                        } catch (Exception ex) {
                        }
                    }

                    setCurSec(loc_sec);
                    //$1,220714,050656,28.758963,N,77.6277844,E,25 (last valid location

                    sbdate = sbdate.delete(0, sbdate.length());

                    sbdate.append("1"); //gps fix
                    sbdate.append(",");
                    sbdate.append(sdf_date_16833.format(gpsdate)); //ddmmyy
                    sbdate.append(",");
                    sbdate.append(sdf_time_16833.format(gpsdate));//hhmmss
                    sbdate.append(",");
                    sbdate.append(decimalFormat_lat.format(getCurLatitude()));
                    sbdate.append(",");
                    sbdate.append(lat_dir);
                    sbdate.append(",");
                    sbdate.append(decimalFormat_lat.format(getCurLongitude()));
                    sbdate.append(",");
                    sbdate.append(longi_dir);
                    sbdate.append(",");
                    sbdate.append(speed);
                    clsSharedVariables.setLastValidLocation(sbdate.toString());

                } catch (ParseException e) {

                } catch (Exception ex) {

                }
            }
        }

        if (get_sleep_mode_status()) {
            if (gps_time_start_sleep == 0) {
                gps_time_start_sleep = gps_end_time;
            }
            dur = gps_end_time - gps_time_start_sleep;
            if (dur <= get_gprs_sleep_val() * 60) {
                return;
            }

        }

        if (gps_time_start == 0) {
            gps_time_start = cal.getTimeInMillis() / 1000;
        }
        dur = gps_end_time - gps_time_start;

        //////System.out.println("dur "+dur);
        if ((nmea.startsWith("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC")) && (dur >= get_gprs_normal_mode_val())) {
            /*
             String pkt_type, char packet_status, byte gps_fix, String gps_date, String gps_time, double lat, char lat_dir,
             double gps_lon, char lon_dir, double speed, double heading, byte no_satelites, double altitude
             */
            ////System.out.println(" dur >get_gprs_normal_mode_val ");
            if (nmea.contains(",A,") || (nmea.contains(",D,"))) {
                split_str = nmea.split(",");
                if (split_str.length > clsDefines.GPS_GPRMC_DATE) {
                    lat = split_str[clsDefines.GPS_GPRMC_LAT];
                    longi = (split_str[clsDefines.GPS_GPRMC_LONG]);
                    lat_dir = split_str[clsDefines.GPS_GPRMC_LAT_DIR].charAt(0);
                    longi_dir = split_str[clsDefines.GPS_GPRMC_LONG_DIR].charAt(0);

                    str = split_str[clsDefines.GPS_GPRMC_TIME];
                    t1 = String.valueOf(str.charAt(0)) + String.valueOf(str.charAt(1)) + String.valueOf(str.charAt(2)) + String.valueOf(str.charAt(3)) + String.valueOf(str.charAt(4)) + String.valueOf(str.charAt(5));
                    str = split_str[clsDefines.GPS_GPRMC_DATE];
                    d1 = String.valueOf(str.charAt(0)) + String.valueOf(str.charAt(1)) + String.valueOf(str.charAt(2)) + String.valueOf(str.charAt(3)) + String.valueOf(str.charAt(4)) + String.valueOf(str.charAt(5));
                    if (split_str[clsDefines.GPS_GPRMC_SPEED].equals("")) {
                        speed = 0;
                    } else {
                        speed = (short) Float.parseFloat(split_str[clsDefines.GPS_GPRMC_SPEED]);
                    }

                    if (split_str[clsDefines.GPS_GPRMC_COG].equals("")) {
                        heading = 0;
                    } else {
                        heading = (short) Float.parseFloat(split_str[clsDefines.GPS_GPRMC_COG]);
                    }
                    setCurHeading(heading);

                    gps_coordinate_string_to_float(lat, longi, lat_dir, longi_dir);

                    if (speed <= 3) {
                        speed = 0;
                    }
                    velocity = speed * 1.852;
                    speed = (short) velocity;

                    setCurLatitude(get_latitude_to_float(lat, lat_dir));
                    setCurLongitude(get_longitude_to_float(longi, longi_dir));
                    setCurLatitudeDir(lat_dir);
                    setCurLongitudeDir(longi_dir);
                    setGpsState(GPS_FIXED);

                    if (clsSharedVariables.getIpAddr1Enable()) {
                        obj16833.trackingMessagePkt(NORMAL_PKT, 'L', false, " ");
                    }
                    if (clsSharedVariables.getIpAddr3Enable()) {
                        obj16833.trackingMessagePktWithCan(NORMAL_PKT, 'L');
                    }
                    if (clsSharedVariables.getIpAddr5Enable()) {
                        if (clsSharedVariables.getSuratProtocol() == true) {
                            objSmc.trackingMessagePkt_Smc(1, false, false, false, false);

                        } else {
                            objDriving.obu_cs_AIS140_pos_pkt();
                        }
                    }
                    // }
                    gps_time_start = gps_end_time;
                    gprmc_inc_cnt = 0;
                    if (get_sleep_mode_status()) {
                        gps_time_start = 0;
                    }
                    gps_time_start_sleep = 0;
                }
            } else {
                //////System.out.println(" gps not fix");
                setGpsState(GPS_NOT_FIXED);
                if (cal.getTimeInMillis() > gpsdate.getTime()) {
                    gpsdate = cal.getTime();
                    setCurDate(sdf_date.format(gpsdate));
                    setCurTime(sdf_time.format(gpsdate));
                }
                ////System.out.println("trackingMessagePkt  not fixed");
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833.trackingMessagePkt(NORMAL_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr3Enable()) {
                    ////System.out.println("ip3 enable");
                    obj16833.trackingMessagePktWithCan(NORMAL_PKT, 'L');
                }
                if (clsSharedVariables.getIpAddr5Enable()) {
                    if (clsSharedVariables.getSuratProtocol() == true) {
                        objSmc.trackingMessagePkt_Smc(1, false, false, false, false);

                    } else {
                        objDriving.obu_cs_AIS140_pos_pkt();
                    }
                }
                gps_time_start = gps_end_time;
                gprmc_inc_cnt = 0;
                if (get_sleep_mode_status()) {
                    gps_time_start = 0;
                }
                gps_time_start_sleep = 0;
            }

        }
        if (dur >= get_gprs_normal_mode_val() && (nmea.startsWith("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC")) && clsSharedVariables.getIpAddr4Enable()) {
            gps_time_start = gps_end_time;
            gprmc_inc_cnt = 0;
            if (get_sleep_mode_status()) {
                gps_time_start = 0;
            }
            split_str = nmea.split(",");
            if (split_str[clsDefines.GPS_GPRMC_STATUS].equals("A") || split_str[clsDefines.GPS_GPRMC_STATUS].equals("D")) {
                try {
                    nmea_concat_data.append(nmea);
                    nmea_packet_construct(nmea_concat_data.toString(), loc_sec);

                } catch (Exception ex) {
                    // ex.printStackTrace();
                }
            } else {
                nmea_concat_data.append(nmea);
                loc_sec = cal.getTimeInMillis();
                nmea_packet_construct(nmea_concat_data.toString(), loc_sec);
            }

            nmea_concat_data = nmea_concat_data.delete(0, nmea_concat_data.length());
            gps_time_start_sleep = 0;
        }
        try {

            if (get_gprs_normal_mode_val() >= 1 && dur >= get_gprs_normal_mode_val() - 1 && clsSharedVariables.getIpAddr4Enable()) {
                if (nmea.startsWith("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC")) {
                } else if ((get_nmea_gpgga() == true) && (nmea.startsWith("$GPGGA") || nmea.contains("$GNGGA") || nmea.contains("$GIGGA"))) {
                    if (nmea_concat_data.toString().contains(nmea) == false) {
                        nmea_concat_data.append(nmea);
                    }
                } else if ((get_nmea_gpvtg() == true) && nmea.startsWith("$GPVTG") || nmea.contains("$GNVTG") || nmea.contains("$GIVTG")) {
                    if (nmea_concat_data.toString().contains(nmea) == false) {
                        nmea_concat_data.append(nmea);
                    }
                } else if ((get_nmea_gpgsa() == true) && nmea.startsWith("$GPGSA") || nmea.contains("$GNGSA") || nmea.contains("$GIGSA")) {
                    if (nmea_concat_data.toString().contains(nmea) == false) {
                        nmea_concat_data.append(nmea);
                    }
                } else if (get_nmea_gpgsv() == true && nmea.startsWith("$GPGSV") || nmea.contains("$GNGSV") || nmea.contains("$GIGSV")) {
                    if (nmea_concat_data.toString().contains(nmea) == false) {
                        nmea_concat_data.append(nmea);
                    }
                }
                //  rs232_gps_time_start_sleep = 0;
            }
            try {
//                if (clsSharedVariables.getRs232Enable() && clsSharedVariables.getRs232Type() == clsDefines.RS232_GPS) {
//                    // rs232_nmea_concat_data.append(nmea);
//                    objRs232GpsQueue.addRs232Nmea(nmea + "\r\n");
//
//                }
                if (clsSharedVariables.getEthernetEnable() && clsSharedVariables.getEthernetType() == clsDefines.ETHERNET_GPS) {

                    if (ethernet_gps_time_start == 0) {
                        ethernet_gps_time_start = gps_end_time;
                    }
                    ethernet_dur = gps_end_time - ethernet_gps_time_start;

                    if (ethernet_dur >= clsSharedVariables.getEthernetGpsModeInterval() && (nmea.startsWith("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC"))) {
                        ethernet_gps_time_start = gps_end_time;
                        split_str = nmea.split(",");
                        try {
                            ethernet_nmea_concat_data.append(nmea);
                            //objEthernetGpsQueue.addEthernetNmea(ethernet_nmea_concat_data.toString());
                            ethernet_nmea_concat_data = ethernet_nmea_concat_data.delete(0, ethernet_nmea_concat_data.length());
                        } catch (Exception ex) {

                        }
                        if (clsSharedVariables.getEthernetGpsModeInterval() >= 1 && ethernet_dur >= clsSharedVariables.getEthernetGpsModeInterval() - 1) {
                            if (nmea.startsWith("$GPRMC") || nmea.startsWith("$GNRMC") || nmea.startsWith("$GIRMC")) {

                            } else if ((get_nmea_gpgga() == true) && nmea.contains("GGA")) {
                                if (ethernet_nmea_concat_data.toString().contains(nmea) == false) {
                                    ethernet_nmea_concat_data.append(nmea);
                                }
                            } else if ((get_nmea_gpvtg() == true) && nmea.contains("VTG")) {
                                if (ethernet_nmea_concat_data.toString().contains(nmea) == false) {
                                    ethernet_nmea_concat_data.append(nmea);
                                }
                            } else if ((get_nmea_gpgsa() == true) && nmea.contains("GSA")) {
                                if (ethernet_nmea_concat_data.toString().contains(nmea) == false) {
                                    ethernet_nmea_concat_data.append(nmea);
                                }
                            } else if (get_nmea_gpgsv() == true && nmea.contains("GSV")) {
                                if (ethernet_nmea_concat_data.toString().contains(nmea) == false) {
                                    ethernet_nmea_concat_data.append(nmea);
                                }
                            }
                        }
                    }

                }
            } catch (Exception ex) {

                // //////System.out.println("Exce gps " + ex.getMessage());
            }

            if (nmea.contains("GPRMC") || nmea.contains("GNRMC") || nmea.contains("GIRMC")) {
                if (app_up_time_inc > 100) {
                    app_up_time_inc = 0;
                }
                app_up_time_inc++;

                if (getSimulationEnabled() == true) {
                    try {
                        inc = getSimulationInc();
                        if (inc < getSimulationcnt() - 1 && inc < 9999) {
                            nmea = gps_simul_data[inc];
                            inc++;
                            setSimulationInc(inc);
                            addNmea(nmea);
                        } else {
                            setSimulationInc(0);
                        }
                    } catch (Exception ex) {
                        setSimulationEnabled(false);
                    }

                } else if (nmea.contains(",A,") || (nmea.contains(",D,"))) {
                    gps_nmea_fixed = true;

                    if (getGpsState() == GPS_NOT_FIXED || getGpsState() == GPS_OFF) {

                        objReadFiles.write_gps_connect_log("GPS FIXED");
                        clsHealthPacketStructure obj = new clsHealthPacketStructure();
                        obj.set_gps_status((byte) 1);
                        obj = null;
                    }
                    setGpsState(GPS_FIXED);
                    try {
                        if (app_up_time_inc > 12 || app_up_time_inc == 1) {
                            app_up_time_inc = 1;
                            try {

                                SwingUtilities.invokeLater(() -> {
                                    try {
                                        if (clsDefines.obu_images_filepath.exists() == true) {
                                            imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/gps_fixed.png"));
                                        } else {
                                            imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gps_fixed.png"))); //new javax.swing.ImageIcon(getClass().getResource("/Images/gps_fixed.png")));
                                        }
                                    } catch (Exception ex) {
                                    }
                                });
                            } catch (Exception ex) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                    if (getFirstFixSec() > 0) {
                        addNmea(nmea);
                    }

                } else {
                    gps_nmea_fixed = false;
                    objwatDog.setBusStopDet_watchdog_val((byte) 0);
                    if (getGpsState() == GPS_FIXED || getGpsState() == GPS_OFF) {
                        if (gps_invalid_data_cnt > 254) {
                            gps_invalid_data_cnt = 0;
                        }
                        gps_invalid_data_cnt++;
                        if (gps_lost_comm_cnt > 254) {
                            gps_lost_comm_cnt = 0;
                        }
                        gps_lost_comm_cnt++;
                        objReadFiles.write_device_dtc_counts_data();
                        objReadFiles.write_gps_connect_log("GPS INVALID DATA");
                        clsHealthPacketStructure obj = new clsHealthPacketStructure();
                        obj.set_gps_status((byte) 0);
                        obj = null;
                    }
                    setGpsState(GPS_NOT_FIXED);
                    try {
                        if (app_up_time_inc >= 10) {
                            app_up_time_inc = 0;
                            try {
                                SwingUtilities.invokeLater(() -> {
                                    try {
                                        if (clsDefines.obu_images_filepath.exists() == true) {
                                            imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/gps-not-fixed.png"));
                                        } else {
                                            imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gps-not-fixed.png")));//new javax.swing.ImageIcon(getClass().getResource("/Images/gps-not-fixed.png")));
                                        }
                                    } catch (Exception ex) {
                                    }
                                });
                            } catch (Exception ex) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            } else if (nmea.contains("GPGGA") || nmea.contains("GNGGA")) {
                if (getSimulationEnabled() == true) {
                    try {
                        inc = getSimulationInc();
                        if (inc < getSimulationcnt() - 1 && inc < 9999) {
                            nmea = gps_simul_data[inc];
                            inc++;
                            setSimulationInc(inc);
                            addNmea(nmea);
                        } else {
                            setSimulationInc(0);
                        }
                    } catch (Exception ex) {
                        setSimulationEnabled(false);
                    }
                } else {
                    split_str = nmea.split(",");

                    //////System.out.println("GPGGA :" + nmea);
                    if (split_str.length > clsDefines.GPS_GGA_ALTITUDE + 1) {

                        if (split_str[clsDefines.GPS_GGA_STAT].contains("1") || split_str[clsDefines.GPS_GGA_STAT].contains("2")) {
                            try {
                                no_sat = Byte.parseByte(split_str[clsDefines.GPS_GGA_NO_SATELITES]);

                                setCurNoSatelites(no_sat);

                                altitude = (split_str[clsDefines.GPS_GGA_ALTITUDE]);

                                clsSharedVariables.setCurAltitude(altitude);

                            } catch (Exception ex) {
                                //  //////System.out.println("no_sat Exc " + nmea);
                            }
                        }
                    }
                }
            } else if (nmea.contains("GPGSA") || nmea.contains("GNGSA")) {
                split_str = nmea.split(",");
                if (split_str.length > clsDefines.GPS_GSA_HDOP + 1) {
                    try {
                        clsSharedVariables.setPdop(split_str[clsDefines.GPS_GSA_PDOP]);
                        clsSharedVariables.setHdop(split_str[clsDefines.GPS_GSA_HDOP]);
                    } catch (Exception ex) {
                    }
                }
            }
        } catch (Exception ex) {
        } finally {
            cal = null;
            nmea = null;
        }

    }

    Calendar calGps;

    private synchronized void nmea_packet_construct(String nmea, long sec) {
        byte buf[]; // Byte array for temporary storage
        byte[] final_buf_gps; // Byte array for the final GPS packet
        String first_dis; // String for first distance
        try {
            int i = 0; // Loop variable
            int buf_inc = 0; // Buffer increment variable
            buf_inc = 0; // Initialize buffer increment

            final_buf_gps = new byte[nmea.length() + 50]; // Initialize final GPS packet array

            // Start of the packet
            final_buf_gps[buf_inc++] = START_PKT;

            // OBU ID of 6 characters
            buf = getOBUID().getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf_gps[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf_gps[buf_inc++] = ASCII_ZERO;
            }

            buf_inc = 7; // Increment buffer index
            // YYMMDDHHMMSS
            calGps = Calendar.getInstance(); // Get current instance of Calendar
            calGps.setTimeInMillis(sec); // Set time based on provided seconds
            if (calGps.get(Calendar.YEAR) > 2000) {
                final_buf_gps[buf_inc++] = (byte) (calGps.get(Calendar.YEAR) - 2000); // Year
            } else {
                final_buf_gps[buf_inc++] = (byte) (01);
            }
            // Month, Day, Hour, Minute, Second
            final_buf_gps[buf_inc++] = (byte) (calGps.get(Calendar.MONTH) + 1);
            final_buf_gps[buf_inc++] = (byte) calGps.get(Calendar.DAY_OF_MONTH);
            final_buf_gps[buf_inc++] = (byte) calGps.get(Calendar.HOUR_OF_DAY);
            final_buf_gps[buf_inc++] = (byte) calGps.get(Calendar.MINUTE);
            final_buf_gps[buf_inc++] = (byte) calGps.get(Calendar.SECOND);

            buf_inc = 13; // Increment buffer index
            final_buf_gps[buf_inc++] = 4; // Message Type (GPRS_PKT_POSITION)

            buf_inc = 14; // Increment buffer index
            // Length of NMEA data
            buf = convertToByteArray((short) nmea.length());
            for (i = 0; i < buf.length; i++) {
                final_buf_gps[buf_inc++] = (buf[i]);
            }

            buf = nmea.getBytes(); // Get bytes of NMEA data

            buf_inc = 16; // Increment buffer index
            // Reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf_gps[buf_inc++] = 0;
            }

            buf_inc = 24; // Increment buffer index
            // NMEA data
            for (i = 0; i < buf.length; i++) {
                final_buf_gps[buf_inc++] = buf[i];
            }

            if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                first_dis = "00000000.00";

                final_buf_gps[buf_inc++] = CHAR_COMMA;

                // Odometer 11 characters (8.2 format)
                try {
                    first_dis = decimalFormat.format(getTravelledDis());
                } catch (Exception ex) {
                }

                buf = first_dis.getBytes();

                for (i = 0; i < buf.length && i < 11; i++) {
                    final_buf_gps[buf_inc++] = buf[i];
                }

                // Update packet length
                buf = convertToByteArray((short) ((short) nmea.length() + 12));
                final_buf_gps[14] = (buf[0]);
                final_buf_gps[15] = (buf[1]);
            }

            // End of the packet
            final_buf_gps[buf_inc++] = END_PKT;

            // Store final GPS packet in a byte array
            buf = new byte[buf_inc];
            for (i = 0; i < buf_inc; i++) {
                buf[i] = final_buf_gps[i];
            }

            // Add packet data to GPS linked list
            clsGpsLinkedList objLst = new clsGpsLinkedList();
            objLst.addLastDataIp4(buf);

            objLst = null;
        } catch (Exception ex) {
            // Handle exceptions
        } finally {
            // Clean up resources
            final_buf_gps = null;
            buf = null;
            calGps = null;
            first_dis = null;
        }
    }

    // Method to convert a short value to a byte array
// Parameters:
//   value - Short value to be converted
// Returns:
//   Byte array representing the short value
    public byte[] convertToByteArray(short value) {
        byte[] bytes = new byte[2]; // Byte array to store the result
        ByteBuffer buffer; // ByteBuffer for manipulation

        buffer = ByteBuffer.allocate(bytes.length); // Allocate ByteBuffer with the size of the byte array
        buffer.putShort(value); // Put the short value into the ByteBuffer
        bytes = null; // Set the byte array to null (not necessary)
        return buffer.array(); // Return the byte array representation of the short value
    }

    // Method to start NMEA processing
    public void startNmea() {

        objDriving = new gpsDriving();
        startLocating();

        // Create an instance of processGprmcData class
        objProcessGprmc = new processGprmcData();

        // Create a new thread with objProcessGprmc as the target
        thrProcessGprmcData = new Thread(objProcessGprmc);

        // Start the thread
        thrProcessGprmcData.start();
    }

    // Method to check if a specified GPS port is configured
    private boolean check_configured_ports(byte port_no) {
        try {
            byte port_inc = 0;

            // Specify the directory where the port information is stored
            File f = new File("/sys/bus/usb-serial/drivers/option1");

            // List the files in the directory
            File[] files = f.listFiles();

            // If there are no files, return false
            if (files == null) {
                return false;
            }

            // Sort the files
            Arrays.sort(files);

            // Iterate through the files
            for (File file : files) {
                // Check if the file name contains "tty"
                if (file.getName().contains("tty")) {
                    // Check if the port number matches the specified port
                    if (port_no == port_inc) {
                        // If the port name matches the specified GPS port name, return true
                        if (gps_port_name.equals("/dev/" + file.getName())) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    port_inc++;
                }
            }

            // Clean up resources
            f = null;
            files = null;
        } catch (Exception ex) {
            // Handle any exceptions
        }

        // If no matching port is found or an exception occurs, return false
        return false;
    }

//whenever uncaught exception occurs thread will be caught and intiates the thread to start again
    class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            objReadFiles.write_gps_connect_log(" GPS Location Service " + e.getMessage() + " Restarting Gps Location Service Thread ");

            if (objNmea == null) {
                objNmea = new ReadNmeaDataFromSerialPort();
                objNmea.start();
            } else {
                try {
                    objNmea.interrupt();
                    objNmea = null;
                } catch (Exception ex) {

                }
                try {
                    objNmea = new ReadNmeaDataFromSerialPort();
                    objNmea.start();
                } catch (Exception ex) {

                }
            }
        }
    }

    private static byte gps_connect_inc = 0;
    clsReadFiles objReadFiles = new clsReadFiles();

    public class ReadNmeaDataFromSerialPort extends Thread {

        // Counter for tracking the number of NMEA data occurrences
        byte nmea_inc = 0;

        @Override
        public void run() {
            // Set uncaught exception handler for the thread
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());

            // Determine the board type and set the GPS separate comport accordingly
            if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
                clsDefines.GPS_SEPARATE_COMPORT = clsDefines.RASPBERRY_GPS_COMPORT;
            } else if (OBU_BRD_TYPE == TINKER_BOARD) {
                clsDefines.GPS_SEPARATE_COMPORT = clsDefines.TINKER_GPS_COMPORT;
            }

            // Infinite loop for continuously reading NMEA data
            while (true) {
                // Set GPS serial watchdog value
                objwatDog.setgpsSerial_watchdog_val((byte) 2);

                // Check if separate GPS is enabled
                if (clsSharedVariables.getSeparateGPS() == true) {
                    // Connect to separate GPS serial port
                    if (connectSeparateGpsSerialport() == false) {
                        // If connection fails, update status and retry
                        setGpsNmeaPktStatus(false);
                        set_gps_comport_connected(false);
                        nmea_inc++;
                        if (nmea_inc > 5) {
                            // If retries exceed limit, close port and reset watchdog
                            nmea_inc = 0;
                            try {
                                SwingUtilities.invokeLater(() -> {
                                    try {
                                        if (clsDefines.obu_images_filepath.exists() == true) {
                                            imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/gps-not-fixed.png"));
                                        } else {
                                            imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gps-not-fixed.png")));
                                        }
                                    } catch (Exception ex) {
                                        // Handle exception
                                    }
                                });
                            } catch (Exception ex) {
                                // Handle exception
                            }

                            objReadFiles.write_gps_connect_log("CLOSING GPS SERIALPORT");
                            closeSeparateGpsSerialPort();

                            set_gps_comport_connected(false);

                            gps_connect_inc++;
                            if (gps_connect_inc >= 5) {
                                gps_connect_inc = 0;
                                objwatDog.setgpsSerial_watchdog_val((byte) 0);
                            }
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            // Handle exception
                        }
                    } else {
                        while (true) {
                            objwatDog.setgpsSerial_watchdog_val((byte) 2);
                            if (gps_serialPort == null) {
                                set_gps_comport_connected(false);
                                objReadFiles.write_gps_connect_log("GPS SERIALPORT NULL");
                                break;
                            } else if (gps_serialPort.isOpened() == false) {
                                set_gps_comport_connected(false);
                                objReadFiles.write_gps_connect_log("CLOSING SERIALPORT ");
                                break;
                            }
                            if (get_nmea_came() == true) {
                                set_nmea_came(false);
                                gps_connect_inc = 0;
                                nmea_inc = 0;
                            } else {
                                nmea_inc++;

                                if (nmea_inc > 10) {
                                    nmea_inc = 0;
                                    gps_connect_inc++;
                                    setGpsNmeaPktStatus(false);
                                    try {
                                        SwingUtilities.invokeLater(() -> {
                                            try {
                                                if (clsDefines.obu_images_filepath.exists() == true) {
                                                    imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/gps-not-fixed.png"));
                                                } else {
                                                    imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gps-not-fixed.png")));
                                                }
                                            } catch (Exception ex) {
                                                // Handle exception
                                            }
                                        });
                                    } catch (Exception ex) {
                                        // Handle exception
                                    }

                                    objReadFiles.write_gps_connect_log("CLOSING GPS SERIALPORT");

                                    closeSeparateGpsSerialPort();

                                    set_gps_comport_connected(false);
                                    if (gps_connect_inc >= 5) {
                                        gps_connect_inc = 0;
                                        objReadFiles.write_gps_connect_log("CLOSING SERIALPORT AFTER  5 RETRIES");
                                        objwatDog.setgpsSerial_watchdog_val((byte) 0);
                                    }
                                    break;
                                }
                            }
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException ex) {
                                // Handle exception
                            }
                        }
                    }
                } else {
                    // If separate GPS is not enabled
                    if (getGsmModuleOn() == true) {
                        // Check if GSM module is on
                        if (connectGpsSerialport() == false) {
                            // Connect to integrated GPS serial port
                            setGpsNmeaPktStatus(false);
                            set_gps_comport_connected(false);
                            nmea_inc++;
                            if (nmea_inc > 5) {
                                // If connection fails, update status and retry
                                nmea_inc = 0;
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        try {
                                            if (clsDefines.obu_images_filepath.exists() == true) {
                                                imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/gps-not-fixed.png"));
                                            } else {
                                                imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gps-not-fixed.png")));
                                            }
                                        } catch (Exception ex) {
                                            // Handle exception
                                        }
                                    });
                                } catch (Exception ex) {
                                    // Handle exception
                                }

                                objReadFiles.write_gps_connect_log("CLOSING GPS SERIALPORT");
                                close_gps_serial_port();

                                set_gps_comport_connected(false);

                                gps_connect_inc++;
                                if (gps_connect_inc >= 5) {
                                    gps_connect_inc = 0;

                                    objReadFiles.write_gps_connect_log("RESTARTING GPS MODULE ");
                                    objwatDog.setgpsSerial_watchdog_val((byte) 0);
                                    restart_module_ports_changed(true);
                                }
                            }

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                // Handle exception
                            }
                        } else {
                            while (true) {
                                objwatDog.setgpsSerial_watchdog_val((byte) 2);
                                if (gps_serialPort == null) {
                                    set_gps_comport_connected(false);
                                    objReadFiles.write_gps_connect_log("GPS SERIALPORT NULL");
                                    break;
                                } else if (gps_serialPort.isOpened() == false) {
                                    set_gps_comport_connected(false);
                                    objReadFiles.write_gps_connect_log("CLOSING SERIALPORT ");
                                    break;
                                }

                                if (check_configured_ports(clsDefines.GPS_SERIAL_PORT_SERIAL_NO) == false) {
                                    set_gps_comport_connected(false);
                                    close_gps_serial_port();
                                    objReadFiles.write_gps_connect_log("PORTS CHANGED GPS");
                                    break;
                                }

                                if (get_nmea_came() == true) {
                                    set_nmea_came(false);
                                    gps_connect_inc = 0;
                                    nmea_inc = 0;
                                } else {
                                    nmea_inc++;

                                    if (nmea_inc > 10) {
                                        nmea_inc = 0;
                                        gps_connect_inc++;

                                        setGpsNmeaPktStatus(false);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                try {
                                                    if (clsDefines.obu_images_filepath.exists() == true) {
                                                        imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/gps-not-fixed.png"));
                                                    } else {
                                                        imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gps-not-fixed.png")));
                                                    }
                                                } catch (Exception ex) {
                                                    // Handle exception
                                                }
                                            });
                                        } catch (Exception ex) {
                                            // Handle exception
                                        }

                                        objReadFiles.write_gps_connect_log("CLOSING GPS SERIALPORT");

                                        close_gps_serial_port();

                                        set_gps_comport_connected(false);
                                        if (gps_connect_inc >= 5) {
                                            gps_connect_inc = 0;

                                            objReadFiles.write_gps_connect_log("CLOSING SERIALPORT AFTER  5 RETRIES");

                                            objwatDog.setgpsSerial_watchdog_val((byte) 0);
                                            restart_module_ports_changed(true);
                                        }
                                        break;
                                    }
                                }
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException ex) {
                                    // Handle exception
                                }

                            }
                        }
                    } else if (getSimulationEnabled() == true) {
                        // If simulation mode is enabled
                        onNmeaReceived("$GPRMC");
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            // Handle exception
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // Handle exception
                }
            }
        }
    }

    static boolean first_gps_port = false;

    public static void close_gps_serial_port() {
        try {
            clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
            objwatDog.setgps_watchdog_val((byte) 0);
            objwatDog = null;

            setSevenPortsConfigured(false);

            if (gps_serialPort != null) {
                if (gps_serialPort.isOpened()) {
                    gps_serialPort.removeEventListener();
                }
                gps_serialPort.closePort();

            }
            try {

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if (clsDefines.obu_images_filepath.exists() == true) {
                                imgGpsNew.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/noGps.png"));
                            } else {
                                imgGpsNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/noGps.png")));
                            }
                        } catch (Exception ex) {

                        }

                    }
                });
            } catch (Exception ex) {

            }

        } catch (SerialPortException ex) {

        } catch (Exception ex) {
            //Logger.getLogger(GPSLocationService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            gps_serialPort = null;
            set_gps_comport_connected(false);
        }
    }
    //connect to the gps serial port

    boolean connectGpsSerialport() {
        String[] portNames = new String[7];
        byte port_inc = 0;
        StringBuilder sb1 = new StringBuilder();
        File f;
        File[] files;

        try {
            if (gps_serialPort != null) {
                if (gps_serialPort.isOpened() == true) {
                    gps_serialPort.closePort();

                }
            }
            Thread.sleep(5000);
            //portNames = SerialPortList.getPortNames();
            //check port no according to option1 command 
            try {
                f = new File("/sys/bus/usb-serial/drivers/option1");
                files = f.listFiles();
                if (files == null) {
                    return false;
                }
                Arrays.sort(files);

                for (File file : files) {
                    if (file.getName().contains("tty")) {
                        portNames[port_inc] = "/dev/" + file.getName();
                        if (port_inc == GPS_SERIAL_PORT_SERIAL_NO) {
                            gps_port_name = portNames[clsDefines.GPS_SERIAL_PORT_SERIAL_NO];
                            objReadFiles.write_gps_connect_log("GPS Port path " + gps_port_name.charAt(gps_port_name.length() - 1));

                        }
                        sb1.append(files[port_inc].getName().charAt(files[port_inc].getName().length() - 1));
                        sb1.append(",");
                        port_inc++;
                    }
                }
                objReadFiles.write_log_low_memory_data("Ports List " + sb1.toString().charAt(sb1.toString().length() - 1));

            } catch (Exception ex) {
            } finally {
                f = null;
                files = null;
                sb1 = null;
            }

            gps_at_gprs_ports_available_cnt = 0;

            objReadFiles.write_gps_connect_log("GPS SERIALPORT  CONNECTING " + gps_port_name.charAt(gps_port_name.length() - 1));
            gps_serialPort = (SerialPort) new SerialPort(gps_port_name);
            gps_serialPort.openPort();
            gps_serialPort.setParams(GPS_BAUDRATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            gps_serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            objReadFiles.write_gps_connect_log("GPS SERIAL PORT OPENED " + gps_port_name.charAt(gps_port_name.length() - 1));

            return true;

        } catch (Exception e) {

            return false;
        } finally {
            portNames = null;
        }
    }
//calculting the checksum for the input value

    private static String prepare_checksum(String str) {
        /*
            A hexadecimal number calculated by exclusive OR of all characters between ‘$’ and ‘*’
         */
        int xor = 0;
        byte[] b = str.getBytes();
        for (int i = 0; i < str.length(); i++) {
            xor ^= b[i];
        }

        return Integer.toHexString(xor);
    }
    //closes the separate gps serial port

    static void closeSeparateGpsSerialPort() {
        try {
            if (gps_serialPort != null) {
                gps_serialPort.removeEventListener();
                gps_serialPort.closePort();
                gps_serialPort = null;
                set_gps_comport_connected(false);
            }
        } catch (SerialPortException e) {
        } catch (Exception e) {
        }

    }
    //connect to the serial port

    boolean connectSeparateGpsSerialport() {
        try {
            String str;
            if (gps_serialPort != null) {
                if (gps_serialPort.isOpened() == true) {
                    gps_serialPort.closePort();
                    Thread.sleep(2000);
                }
            }
            objReadFiles.write_gps_connect_log("GPS SERIALPORT  CONNECTING " + clsDefines.GPS_SEPARATE_COMPORT);
            gps_serialPort = (SerialPort) new SerialPort(clsDefines.GPS_SEPARATE_COMPORT);
            gps_serialPort.openPort();
            gps_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            gps_serialPort.setParams(GPS_SEPARATE_BAUDRATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
            );

            gps_serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            objReadFiles.write_gps_connect_log("GPS SERIAL PORT OPENED ");
            Thread.sleep(5000);

            if (gps_serialPort.isOpened()) {
                gpsSerialWrite();
                if (clsSharedVariables.get_gps_enabled() && clsSharedVariables.get_irnss_enabled()) {
                    str = "$PAIR066,1,0,1,0,0,1*3B\r\n";
                    gpsSerialWriteString(str);

                } else if (clsSharedVariables.get_gps_enabled()) {
                    str = "$PAIR066,1,0,0,0,0,0*3B\r\n";
                    gpsSerialWriteString(str);
                    //  lblMsgData.setText(str);

                } else if (clsSharedVariables.get_irnss_enabled()) {
                    str = "$PAIR066,0,0,0,0,0,1*3B\r\n";
                    gpsSerialWriteString(str);
                    //  lblMsgData.setText(str);

                }
            }
            return true;
        } catch (SerialPortException e) {
            return false;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }
    //This method is to write to the gps serial port

    public synchronized static String gpsSerialWriteString(String str) {
        try {
            if (gps_serialPort == null) {
                return "Serialport Closed";
            }
            gps_serialPort.writeString(str);
        } catch (SerialPortException ex) {
            closeSeparateGpsSerialPort();
            return (ex.getMessage());

        }
        return "Success";
    }
    //This method is to write to gps serial port

    public synchronized static void gpsSerialWrite() {
        String checksum;
        String str;
        try {
            if (gps_serialPort == null) {
                return;
            }

            if (clsSharedVariables.get_gps_enabled() && clsSharedVariables.get_irnss_enabled()) {
                /*
                bit 0: Enabling/disabling GPS constellation 
                bit 1: Enabling/disabling GLONASS constellation 
                bit 2: Enabling/disabling QZSS constellation 
                bit 3: Enabling/disabling Galileo constellation
                bit 7: Enabling/disabling BeiDou constellation
                bit 10: Enabling/disabling IRNSS constellation
                 */
                checksum = prepare_checksum("PSTMSETCONSTMASK,1025");
                str = "$PSTMSETCONSTMASK,1025*" + checksum + "\r\n";
                //  ////System.out.println(str);
                gps_serialPort.writeString(str);
                //  ////System.out.println("GPS and IRNSS ENABLED");
                Thread.sleep(1000);
            } else if (clsSharedVariables.get_gps_enabled()) {
                checksum = prepare_checksum("PSTMSETCONSTMASK,1");
                str = "$PSTMSETCONSTMASK,1*" + checksum + "\r\n";
                // ////System.out.println(str);
                gps_serialPort.writeString(str);
                // ////System.out.println("only GPS   ENABLED");
                Thread.sleep(1000);
            } else {
                checksum = prepare_checksum("PSTMSETCONSTMASK,1024");
                str = "$PSTMSETCONSTMASK,1024*" + checksum + "\r\n";
                // ////System.out.println(str);
                gps_serialPort.writeString(str);
                Thread.sleep(1000);
                // ////System.out.println("only IRNSS   ENABLED");
            }
            checksum = prepare_checksum("PSTMSAVEPAR");
            str = "$PSTMSAVEPAR*" + checksum + "\r\n";
            // ////System.out.println(str);
            gps_serialPort.writeString(str);
            Thread.sleep(1000);
        } catch (SerialPortException ex) {
            // Logger.getLogger(GPSLocationService.class.getName()).log(Level.SEVERE, null, ex);
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_gps_connect_log("GPS exc " + ex.getMessage());
            objReadFiles = null;
        } catch (InterruptedException ex) {
            // Logger.getLogger(GPSLocationService.class.getName()).log(Level.SEVERE, null, ex);
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_gps_connect_log("GPS exc " + ex.getMessage());
            objReadFiles = null;
        } finally {
            checksum = null;
            str = null;
        }
    }
    byte retry_cnt = 0;
    byte[] gpsrx_buf = new byte[5000];
    static byte state = 0;
    StringBuilder sb = new StringBuilder();

    private class PortReader implements SerialPortEventListener {

        // Variables for managing data reception and processing
        int i = 0;
        int SCROLL_BUFFER_SIZE = 30; // Size of the buffer for scrolling GPS diagnostic data
        StringBuilder sb_data = new StringBuilder(5000); // StringBuilder for accumulating received data
        int linescount = 0; // Counter for tracking the number of lines displayed in the GPS diagnostic window

        @Override
        public void serialEvent(SerialPortEvent event) {
            try {
                // Check if data is available to read from the serial port
                if (event.isRXCHAR() && (event.getEventValue() > 0)) {
                    // Set GPS watchdog value to indicate data reception
                    objwatDog.setgps_watchdog_val((byte) 1);
                    boolean data_came = false; // Flag to indicate whether new data has been received
                    int k = 0;
                    gpsrx_buf = gps_serialPort.readBytes(); // Read the available bytes from the serial port

                    // Loop through the received bytes
                    for (i = 0; i < gpsrx_buf.length; i++) {
                        switch (state) {
                            case 0: { // State for detecting the start of an NMEA sentence
                                if (gpsrx_buf[i] == CHAR_DOLLAR) {
                                    state = 1;
                                    // Clear the StringBuilder to prepare for new data
                                    try {
                                        if (sb.length() > 1) {
                                            sb = sb.delete(0, sb.length());
                                        }
                                    } catch (Exception ex) {
                                        // Handle exception
                                    }
                                    sb.append("$"); // Append the dollar sign to indicate the start of an NMEA sentence
                                    setGpsNmeaPktStatus(true); // Set GPS NMEA packet status to indicate data reception
                                }
                            }
                            break;
                            case 1: { // State for reading the NMEA sentence until the end-of-line character
                                if (gpsrx_buf[i] == CHAR_NEWLINE || gpsrx_buf[i] == CHAR_LINEFEED) {
                                    state = 2;
                                    break;
                                }
                                sb.append((char) gpsrx_buf[i]); // Append the character to the StringBuilder
                            }
                            break;
                            case 2: { // State for processing the complete NMEA sentence
                                state = 0; // Reset the state to prepare for the next sentence
                                try {
                                    // Process the received NMEA sentence
                                    onNmeaReceived(sb.toString());
                                } catch (Exception ex) {
                                    // Handle exception
                                }
                                set_gps_comport_connected(true); // Set GPS comport connected status
                                retry_cnt = 0; // Reset the retry count
                                data_came = true; // Set the flag to indicate new data reception

                                // Update GPS diagnostic window with received data
                                if (getGpsDiagEnabled()) {
                                    try {
                                        SwingUtilities.invokeLater(() -> {
                                            // Append the NMEA sentence to the diagnostic window
                                            if (linescount++ >= 20) {
                                                linescount = 0;
                                                PanGpsDiag.txtData.append(sb.toString());
                                                PanGpsDiag.txtData.append("\n");
                                            }
                                            // Truncate excess lines to manage buffer size
                                            int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - SCROLL_BUFFER_SIZE;
                                            if (numLinesToTrunk > 0) {
                                                try {
                                                    int posOfLastLineToTrunk = PanGpsDiag.txtData.getLineEndOffset(numLinesToTrunk - 1);
                                                    PanGpsDiag.txtData.replaceRange("", 0, posOfLastLineToTrunk);
                                                } catch (Exception ex) {
                                                    // Handle exception
                                                }
                                                // Trigger garbage collection to manage memory
                                                try {
                                                    System.gc();
                                                } catch (Exception ex) {
                                                    // Handle exception
                                                }
                                            }
                                        });
                                    } catch (Exception ex) {
                                        // Handle exception
                                    }
                                }
                            }
                            break;
                        }
                    }

                    // Handle connection retries if no new data is received
                    if (getSimulationEnabled() == false) {
                        if (data_came == false) {
                            retry_cnt++;
                            if (retry_cnt > 10) {
                                retry_cnt = 0;
                                try {
                                    // Close the serial port and reset watchdog if retries exceed limit
                                    gps_serialPort.closePort();
                                    gps_serialPort.removeEventListener();
                                    gps_serialPort = null;
                                    objwatDog.setgps_watchdog_val((byte) 0);
                                } catch (SerialPortException ex1) {
                                    // Handle exception
                                }
                            }
                        }
                    } else {
                        // If simulation mode is enabled, generate simulated NMEA data
                        // onNmeaReceived("$GPRMC");
                    }
                }
            } catch (SerialPortException ex) {
                // Handle SerialPortException
                objReadFiles.write_gps_connect_log("GPS SERIALPORT SerialPortException" + ex.getMessage());
                try {
                    // Close the serial port in case of exception
                    gps_serialPort.closePort();
                    gps_serialPort.removeEventListener();
                    gps_serialPort = null;
                } catch (SerialPortException ex1) {
                    // Handle exception
                }
            } catch (Exception ex) {
                // Handle other exceptions
                objReadFiles.write_gps_connect_log("GPS SERIALPORT EXCEPTION" + ex.getMessage());
            }
        }

    }

//Starting the ReadNmeaDataFromSerialPort() thread
    public void startLocating() {
        if (objNmea == null) {
            objNmea = new ReadNmeaDataFromSerialPort();
            objNmea.start();
        } else {
            try {
                objNmea.interrupt();
                objNmea = null;
                objNmea = new ReadNmeaDataFromSerialPort();
                objNmea.start();
            } catch (Exception ex) {

            }
        }

    }

    public void stopLocating() {
        // stop thread to run NMEA
        objNmea.interrupt();
        objNmea = null;

    }

    class processGprmcExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            objReadFiles.write_gps_connect_log(" GPS processGprmcDataexchandler " + e.getMessage() + " Restarting processGprmcData Thread ");

            objProcessGprmc = new processGprmcData();
            thrProcessGprmcData = new Thread(objProcessGprmc);
            thrProcessGprmcData.start();
            objwatDog.setgps_watchdog_val((byte) 0);
            objwatDog.setgprmc_watchdog_val((byte) 0);
        }
    }

    public class processGprmcData implements Runnable {

        @Override
        public void run() {
            // Set uncaught exception handler for the thread
            Thread.currentThread().setUncaughtExceptionHandler(new processGprmcExceptionHandler());

            String nmea;
            String[] split_str;

            // Continuously run the thread
            while (true) {
                // Set watchdog value
                objwatDog.setgprmc_watchdog_val((byte) 2);

                // Check if there is NMEA data in the deque
                if (clsNmeaDequeList.getLength() > 0) {
                    try {
                        // Retrieve NMEA sentence from the deque
                        nmea = clsNmeaDequeList.removeNmea();

                        // Process GPRMC sentences
                        if (nmea.contains("GPRMC") || nmea.contains("GNRMC") || nmea.contains("GIRMC")) {
                            try {
                                // Parse and process GPRMC data
                                gps_state_machine(nmea, no_sat, altitude);
                                no_gps_cnt = 0;
                            } catch (Exception ex) {
                                // Handle exception
                            }
                        } // Process GPGGA sentences
                        else if (nmea.contains("GPGGA") || nmea.contains("GNGGA") || nmea.contains("GIGGA")) {
                            split_str = nmea.split(",");
                            // Check GPS status in GPGGA sentence
                            if (split_str[clsDefines.GPS_GGA_STAT].equals("1") || split_str[clsDefines.GPS_GGA_STAT].equals("2")) {
                                // Implement logic for valid GPS fix
                            }
                        }

                        // Clean up variables
                        nmea = null;
                        split_str = null;
                    } catch (Exception ex) {
                        // Handle exception
                    }

                    try {
                        Thread.sleep(10); // Sleep for a short duration
                    } catch (InterruptedException ex) {
                        // Handle InterruptedException
                        //Logger.getLogger(GPSLocationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        Thread.sleep(100); // Sleep for a longer duration if no data is available
                    } catch (InterruptedException ex) {
                        // Handle InterruptedException
                        //Logger.getLogger(GPSLocationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    objwatDog.setBusStopDet_watchdog_val((byte) 0); // Reset watchdog value for bus stop detection
                }
            }
        }
    }

    private void gps_coordinate_string_to_float(String lat_str, String long_str, char lat_dir, char long_dir) {

        // Variables to hold degrees, minutes, and seconds for latitude and longitude
        Double deg = 0.0;
        Double min = 0.0;
        Double sec = 0.0;

        // Variables to hold latitude and longitude values
        Double latitude;
        Double longitude;

        // Convert latitude string to floating-point value
        deg = Double.valueOf((((lat_str.charAt(0)) - ASCII_NUM) * 10) + (lat_str.charAt(1) - ASCII_NUM));
        min = Double.valueOf((((lat_str.charAt(2) - ASCII_NUM) * 10) + (lat_str.charAt(3) - ASCII_NUM)));
        sec = Double.valueOf((((lat_str.charAt(5) - ASCII_NUM) * 1000) + ((lat_str.charAt(6) - ASCII_NUM) * 100) + ((lat_str.charAt(7) - ASCII_NUM) * 10) + (lat_str.charAt(8) - ASCII_NUM)));
        sec = (sec / 10000);
        min = ((min + sec) / 60);
        latitude = deg + min;

        // Adjust latitude based on direction (North/South)
        if (lat_dir == 'S') {
            latitude = latitude * (-(1.0));
        }

        // Convert longitude string to floating-point value
        deg = Double.valueOf((((long_str.charAt(0) - ASCII_NUM) * 100) + ((long_str.charAt(1) - ASCII_NUM) * 10) + (long_str.charAt(2) - ASCII_NUM)));
        min = Double.valueOf((((long_str.charAt(3) - ASCII_NUM) * 10) + (long_str.charAt(4) - ASCII_NUM)));
        sec = Double.valueOf((((long_str.charAt(6) - ASCII_NUM) * 1000) + ((long_str.charAt(7) - ASCII_NUM) * 100) + ((long_str.charAt(8) - ASCII_NUM) * 10) + (long_str.charAt(9) - ASCII_NUM)));
        sec = (sec / 10000);
        min = ((min + sec) / 60);
        longitude = deg + min;

        // Adjust longitude based on direction (East/West)
        if (long_dir == 'W') {
            longitude = longitude * (-(1.0));
        }

        // Set the current latitude and longitude values
        setCurLatitude(latitude);
        setCurLongitude(longitude);
    }

    private double get_latitude_to_float(String lat_str, char lat_dir) {

        // Variables to hold degrees, minutes, and seconds for latitude
        Double deg = 0.0;
        Double min = 0.0;
        Double sec = 0.0;
        Double latitude;

        // Convert latitude string to floating-point value
        deg = Double.valueOf((((lat_str.charAt(0)) - ASCII_NUM) * 10) + (lat_str.charAt(1) - ASCII_NUM));
        min = Double.valueOf((((lat_str.charAt(2) - ASCII_NUM) * 10) + (lat_str.charAt(3) - ASCII_NUM)));
        sec = Double.valueOf((((lat_str.charAt(5) - ASCII_NUM) * 1000) + ((lat_str.charAt(6) - ASCII_NUM) * 100) + ((lat_str.charAt(7) - ASCII_NUM) * 10) + (lat_str.charAt(8) - ASCII_NUM)));
        sec = (sec / 10000);
        min = ((min + sec) / 60);
        latitude = deg + min;

        // Adjust latitude based on direction (North/South)
        if (lat_dir == 'S') {
            latitude = latitude * (-(1.0));
        }

        return (latitude);

    }

    private double get_longitude_to_float(String long_str, char long_dir) {

        // Variables to hold degrees, minutes, and seconds for longitude
        Double deg = 0.0;
        Double min = 0.0;
        Double sec = 0.0;
        Double longitude;

        // Convert longitude string to floating-point value
        deg = Double.valueOf((((long_str.charAt(0) - ASCII_NUM) * 100) + ((long_str.charAt(1) - ASCII_NUM) * 10) + (long_str.charAt(2) - ASCII_NUM)));
        min = Double.valueOf((((long_str.charAt(3) - ASCII_NUM) * 10) + (long_str.charAt(4) - ASCII_NUM)));
        sec = Double.valueOf((((long_str.charAt(6) - ASCII_NUM) * 1000) + ((long_str.charAt(7) - ASCII_NUM) * 100) + ((long_str.charAt(8) - ASCII_NUM) * 10) + (long_str.charAt(9) - ASCII_NUM)));
        sec = (sec / 10000);
        min = ((min + sec) / 60);
        longitude = deg + min;

        // Adjust longitude based on direction (East/West)
        if (long_dir == 'W') {
            longitude = longitude * (-(1.0));
        }

        // Return the longitude value
        return longitude;
    }

    private static int odometer_file_update_inc = 0;
    // private static double temp_lat_long = 0.1;

    public boolean gps_data_process(String gps_data, short no_sate, String altitude) {
        String[] split_str1;
        String lat;
        String longi;
        char lat_dir;
        char longi_dir;
        double loc_dis = 0.0;
        short speed = 0;
        float heading = 0;
        double velocity;
        double tra_dis = getTravelledDis();

        try {

            split_str1 = gps_data.split(",");
            lat = split_str1[clsDefines.GPS_GPRMC_LAT];
            longi = (split_str1[clsDefines.GPS_GPRMC_LONG]);
            lat_dir = split_str1[clsDefines.GPS_GPRMC_LAT_DIR].charAt(0);
            longi_dir = split_str1[clsDefines.GPS_GPRMC_LONG_DIR].charAt(0);

            if (split_str1[clsDefines.GPS_GPRMC_SPEED].equals("")) {
                speed = 0;
            } else {
                speed = (short) Float.parseFloat(split_str1[clsDefines.GPS_GPRMC_SPEED]);
            }

            if (split_str1[clsDefines.GPS_GPRMC_COG].equals("")) {
                heading = 0;
            } else {
                heading = (short) Float.parseFloat(split_str1[clsDefines.GPS_GPRMC_COG]);
            }
            setCurHeading(heading);

            gps_coordinate_string_to_float(lat, longi, lat_dir, longi_dir);
            if (getPrevLat() == 0.0) {
                setPrevLat(getCurLatitude());
            }

            if (getPrevLong() == 0.0) {
                setPrevLong(getCurLongitude());
            }

            // temp_lat_long = temp_lat_long + 0.0001;
            //double temp_lat;
            //  temp_lat = getCurLatitude() + temp_lat_long;
            //  setCurLatitude(temp_lat);
            //converting into KMPH
            if (speed <= 3) {
                speed = 0;
            }
            velocity = speed * 1.852;
            speed = (short) velocity;
            clsSharedVariables.setCurSpeed(velocity);
            if (speed > 0) {
                if (odometer_enable == true) {
                    if (tra_dis == 0.0) {
                        try {
                            objReadFiles.read_odometer_distance_file(Calendar.getInstance().getTimeInMillis());
                            tra_dis = getTravelledDis();
                        } catch (Exception ex) {
                        }
                    }

                    if (getOdometerPrevLat() == 0.0) {
                        setOdometerPrevLat(getCurLatitude());
                    }

                    if (getOdometerPrevLong() == 0.0) {
                        setOdometerPrevLong(getCurLongitude());
                    }

                    loc_dis = calc_odometer_distance(getCurLatitude(), getCurLongitude(), getOdometerPrevLat(), getOdometerPrevLong());

                    loc_dis = loc_dis + tra_dis;

                    if (loc_dis >= 99999999.99) {
                        objReadFiles.write_gps_connect_log("Odometer reset 0.0 ");
                        loc_dis = 0.0;
                    }
                    setOdometerPrevLat(getCurLatitude());
                    setOdometerPrevLong(getCurLongitude());
                    setTravelledDis(loc_dis);
                    // System.out.println("settravelleddistance");

                    if (odometer_file_update_inc == 10) {
                        //  odometer_file_update_inc = 0;
                        objReadFiles.write_odometer_distance_file(loc_dis, getCurLatitude(), getCurLongitude(), Calendar.getInstance().getTimeInMillis());
                    } else if (odometer_file_update_inc == 20) {
                        odometer_file_update_inc = 0;
                        // objReadFiles. write_odometer_distance_alt_file();
                        objReadFiles.write_odometer_distance_alt_file(loc_dis, getCurLatitude(), getCurLongitude(), Calendar.getInstance().getTimeInMillis());
                    }

                    odometer_file_update_inc++;
                }

                objDriving.cur_route_no = getCurRouteNo();
                objDriving.cur_trip_no = getCurTripNo();
                objDriving.sch_date = getCurDate();
                objDriving.sch_time = getCurTime();
                objDriving.cur_latitude = getCurLatitude();
                objDriving.cur_longitude = getCurLongitude();
                objDriving.cur_sec = Calendar.getInstance().getTimeInMillis();
                objDriving.schedule_id = get_driver_id();// getSchId();
                objDriving.gps_speed = speed; //getGpsSpeed();
                objDriving.harsh_acceleration_detection();
                objDriving.harsh_break_detection();
                objDriving.gps_speed = speed;
                objDriving.over_speed_detection();
            } else if (objDriving.cur_latitude == 0) {

                objDriving.cur_route_no = getCurRouteNo();
                objDriving.cur_trip_no = getCurTripNo();
                objDriving.sch_date = getCurDate();
                objDriving.sch_time = getCurTime();
                objDriving.cur_latitude = getCurLatitude();
                objDriving.cur_longitude = getCurLongitude();
                objDriving.cur_sec = Calendar.getInstance().getTimeInMillis();
                objDriving.schedule_id = get_driver_id();// getSchId();
                objDriving.gps_speed = speed; //getGpsSpeed();
                objDriving.harsh_acceleration_detection();
                objDriving.harsh_break_detection();
                objDriving.gps_speed = speed;
                objDriving.over_speed_detection();
            }

        } catch (Exception ex) {
            return false;
        } finally {
            setGpsSpeed(speed);
            gps_data = null;
            gpsdate = null;
            split_str1 = null;
            d1 = null;
            t1 = null;
            gpsdate = null;
            lat = null;
            longi = null;
            l_cur_time = null;
            l_cur_date = null;
        }
        return true;
    }

    private void gps_state_machine(String gps_data, short no_satelites, String altitude) {
        // Process GPS data, return if unsuccessful
        if (gps_data_process(gps_data, no_satelites, altitude) == false) {
            objwatDog.setBusStopDet_watchdog_val((byte) 0); // Reset watchdog value
            return;
        }

        // Check current route and trip status
        if (getCurRouteStat() == true && getCurTripStat() == TRIP_START) {
            // Perform bus stop detection if route and trip are started
            objBusStopDetect.gps_bus_stop_detection();
            // Update previous latitude and longitude
            setPrevLat(getCurLatitude());
            setPrevLong(getCurLongitude());
        } else if (getCurRouteStat() == true && getCurTripStat() != TRIP_START) {
            // Check trip status and start a new trip if not started
            objwatDog.setBusStopDet_watchdog_val((byte) 0); // Reset watchdog value
            setPrevLat(0.0); // Reset previous latitude
            setPrevLat(0.0); // Reset previous longitude
            objBusStopDetect.gps_trip_start_detection(); // Start a new trip
        } else if (getCurRouteStat() == false && (getCurTripStat() == TRIP_UNKNOWN || getCurTripStat() == TRIP_END)) {
            // Check route and trip status and start a new route if necessary
            objwatDog.setBusStopDet_watchdog_val((byte) 0); // Reset watchdog value
            // Check if automatic trip selection is enabled
            if (getCurAutoTripStat() == true) {
                // Send route information and set current route status to true
                final ClsSchedule objSch = new ClsSchedule();
                objSch.send_route_info();
                setCurRouteStat(true);
            }
            // gps_state =cls GPS_STATE_IDLE;
        } else {
            objwatDog.setBusStopDet_watchdog_val((byte) 0); // Reset watchdog value
        }
    }

    double calc_odometer_distance(double lat1, double lang1, double lat2, double lang2) {
        // Initialize variables
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

        // Convert latitude and longitude values to radians
        dLat1InRad = lat1 * (PI / 180.0);
        dLat2InRad = lat2 * (PI / 180.0);
        dLong1InRad = lang1 * (PI / 180.0);
        dLong2InRad = lang2 * (PI / 180.0);

        // Calculate differences in latitude and longitude
        dLongitude = dLong2InRad - dLong1InRad;
        dLatitude = dLat2InRad - dLat1InRad;

        // Calculate distance using Haversine formula
        a = pow(sin(dLatitude / 2), 2) + cos(dLat1InRad) * cos(dLat2InRad) * pow(sin(dLongitude / 2), 2);
        c = 2 * atan2(sqrt(a), sqrt(1 - a));
        kEarthRadiusKms = 6376.5;
        dDistance = kEarthRadiusKms * c;//km

        // Return the calculated distance
        return dDistance;
    }

    public synchronized static void setupdateSystemTimeStat(boolean state) {
        first_time_to_update_systime = state;
    }

    public synchronized static boolean getupdateSystemTimeStat() {
        return first_time_to_update_systime;
    }

    public synchronized static void setupdateSystemTimeStat30min(boolean state) {
        update_systime_every_30mins = state;
    }

    public synchronized static boolean getupdateSystemTimeStat30min() {
        return update_systime_every_30mins;
    }
    /*
     private void load_all_Stops() {
     try {
     LinearLayout rl = (LinearLayout) findViewById(R.id.linearMap);
     int i = 0;
     short no_of_stops_in_a_route = getNoStopsRoute();
     byte cur_stop_no = getCurStopNo();

     final TextView[] tv = new TextView[no_of_stops_in_a_route];
     rl.removeAllViews();

     for (i = 0; i < no_of_stops_in_a_route; i++) {
     {
     tv[i] = new TextView(this);
     if(lang_hin==false) {
     tv[i].setText(gps_data[i].stop_name);
     }
     else {
     tv[i].setText(gps_data[i].reg1_stop_name);
     }
     tv[i].setTextSize((float) 22);
     tv[i].setPadding(0, 20, 0, 0);
     tv[i].setMaxWidth(150);
     tv[i].setMinWidth(150);
     tv[i].setGravity(Gravity.CENTER);
     if (gps_data[i].stop_identify_status == true) {
     tv[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_vertical_dots_red);
     } else if (i == cur_stop_no) {
     tv[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_vertical_dots_green);
     } else {
     tv[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_vertical_dots_yellow);
     }
     tv[i].setLines(2);
     tv[i].setHorizontalScrollBarEnabled(true);
     rl.addView(tv[i]);

     }
     }
     }
     catch (Exception ex ){

     }
     }*/

}
