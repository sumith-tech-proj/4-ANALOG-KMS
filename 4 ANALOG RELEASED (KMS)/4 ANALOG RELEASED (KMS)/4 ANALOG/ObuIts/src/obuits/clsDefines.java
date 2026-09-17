package obuits;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.File;

public class clsDefines {

    public static final boolean GSM_MODULE_AVAILABLE = true;// false;
    public static boolean NETWORKMANAGER_GPRS = false;// false;
    public static final byte RASPBERRY_BOARD = 0;
    public static final byte TINKER_BOARD = 1;
    public static final byte BANANAPI_BOARD = 2;
    public static byte OBU_BRD_TYPE = RASPBERRY_BOARD;
    public static final int TIME_LIMIT = 2000; //2 sec 
    public static final String TINKER_BOARD_HARDWARE = "Rockchip";
    public static final String BANANAPI_BOARD_HARDWARE = "sun8iw11p1";
    public static String RASPBERRYPI_BOARD_HARDWARE = "BCM2711";
    public static String obu_board_username = "pi";
    public static boolean specl_audio_playing = true;
    public static boolean SIM_LOG_COLLECT = false;
    public static boolean FM_RADIO_ENABLE = false;
    public static boolean xdotool = false;
    public static boolean apc_data_came = false;
    public static boolean odometer_enable = false;

    public static boolean route_start = false;
    public static boolean device_power_on = false;
    //can parameters cesl
    public static String Odometer_reading = "40";
    public static String Vehicle_Status = "0";
    public static String SOC = "1";
    public static String SOH = "1";
    public static String Speed = "40";
    public static String Acceleration = "20";
    public static String Voltage = "24";
    public static String Current = "3";
    public static String Charger_Current = "3";
    public static String Charger_Voltage = "24";
    public static String Ambient_temperature = "50";
    public static String Cell_temperature = "60";
    public static String Motor_temperature = "50";
    public static String BMS_error = "0";

    //can parameters cesl
    public static final byte COMP_SUMITH = 0;
    public static final byte COMP_JBM = 1;
    public static final byte COMP_AMINEX = 2;
    public static final byte COMP_DIMTS_NAGPUR = 3;

    public static final byte PRODUCT_16833 = 0;
    public static final byte PRODUCT_UBS2 = 1;

    public static final byte STORAGE_SDCARD = 0;
    public static final byte STORAGE_HARDDISK = 1;
    public static final byte STORAGE_SSD = 2;
    public static final byte HISTORY_PACKET_VALUE = 5;
    public static final byte AIS140 = 0;
    public static final byte SURAT = 1;

    public static final byte OLECTRA_BYD = 0;
    public static final byte OLECTRA_CX2 = 1;
    public static final byte JBM_CAN = 2;
    public static final byte PMI_CAN = 3;

    public static final int MAX_NO_TIMES = 2;
    public static final int NO_DAYS_DELETE = 100;
    
    public static final String APC_IPADDRESS = "10.42.0.100";//"10.42.0.100";//00";
    public static final int APC_PORTNO = 13000;
    public static byte COMPANY_NAME_NMEA_PROT = COMP_SUMITH;// COMP_JBM;//COMP_AMINEX
    public static byte COMPANY_NAME_NMEA_PROT_JBMTESTING = COMP_SUMITH;// for displaying lat longs in some pkts COMP_JBM;// COMP_JBM;//COMP_AMINEX
    public static final boolean DUPLICATE_FILES_MAIN_PATH = true;
    public static final boolean isWindows = false;//false;
    public static final byte SEPARATOR_COMMA = ',';
    public static final String SW_PROTOCOL_VERSION = "1.0.2";
    public static final String SW_16833_FIRMWARE_VERSION = "10.1.0"; //6.7";//5.53  //3.63"; //5.15"; //3.54  //COMP_JBM =5.4 & 3.53
    public static final String SW_FIRMWARE_VERSION = "11.0.25.1 KMS"; //6.7";//5.53  //3.63"; //5.15"; //3.54  //COMP_JBM =5.4 & 3.53

    public static final boolean GPRS_LOG_DEBUG_ENABLE = false; //false;// false;
    public static final boolean GYROSCOPE_THROUGH_GPS_ENABLE = true; //false;// false;
    public static final boolean GPRS_LOG_DEBUG_RX_ENABLE = false;// false; // false;// false;
    public static final boolean TEST_DEBUG = false;
    public static final boolean UPDATE_CAMERA_SETTINGS_ON_POWERON = false;
    public static boolean PEDESTRIAN_DETECTION_AVAILABLE = false;
    public static boolean PEDESTRIAN1_AUDIO = false;
    public static boolean PEDESTRIAN2_AUDIO = false;
    public static boolean PEDESTRIAN3_AUDIO = false;
    public static boolean PEDESTRIAN4_AUDIO = false;
    public static boolean PEDESTRIANDIALOG_AUDIO = false;

    public static final String VERSION_PWD = "9898";
    public static final String FACTORY_RESET_PWD = "SUMITHRESET";
    public static String GPRS_LOCK_FILE = "/usr/local/var/run/pppd/lock";
    public static String QUECTEL_PPP_SCRIPT = "/home/linaro/quectel-pppd.sh";
    public static String QUECTEL_KILL_PPP_SCRIPT = "/home/linaro/quectel-ppp-kill";
    public static String GSTREAMER_CAM1_SCRIPT = "/home/linaro/gstreamer_cam1.sh";
    public static String GSTREAMER_CAM2_SCRIPT = "/home/linaro/gstreamer_cam2.sh";
    public static String GSTREAMER_CAM3_SCRIPT = "/home/linaro/gstreamer_cam3.sh";
    public static String GSTREAMER_CAM4_SCRIPT = "/home/linaro/gstreamer_cam4.sh";
    public static String GSTREAMER_TOP_SCRIPT = "/home/linaro/gstreamer_top.sh";
    public static String GSTREAMER_KILL_SCRIPT = "/home/linaro/close_gstreamer.sh";

    public static final String RASPBERRY_DISBRD_COMPORT = "/dev/ttyS0";
    public static final String TINKER_DISBRD_COMPORT = "/dev/ttyS1";

    public static final String RASPBERRY_GPS_COMPORT = "/dev/ttyAMA2";
    public static final String TINKER_GPS_COMPORT = "/dev/ttyS4";

    public static final String RASPBERRY_CAN_COMPORT = "/dev/ttyAMA4";
    public static final String TINKER_CAN_COMPORT = "/dev/ttyS2";

    public static final String RASPBERRY_RS232_COMPORT = "/dev/ttyAMA1";
    public static final String TINKER_RS232_COMPORT = "/dev/ttyS3";
    public static String DISPLAYBRD_COMPORT = RASPBERRY_DISBRD_COMPORT;//COM12";
    public static String RS232_COMPORT = RASPBERRY_RS232_COMPORT; //ttyUSB1";//COM12";
    public static String GPS_SEPARATE_COMPORT = RASPBERRY_GPS_COMPORT;// "/dev/ttyAMA2"; // GPS data
    public static int GPS_SEPARATE_BAUDRATE = 9600; // GPS data
    public static String CAN_COMPORT = RASPBERRY_CAN_COMPORT; // USB0"; // "COM18";///dev/ttyUSB0";//COM12";
    public static int CAN_BAUDRATE = 115200;//230400 ;//115200;// 230400;// 115200;//COM12";
    public static String CAN_COMPORT1 = RASPBERRY_CAN_COMPORT;
    public static int CAN_BAUDRATE1 = 115200;
    public static final String RASPBERRY_CAN_COMPORT1 = "/dev/ttyAMA4";
    public static final String QUECTEL_FIRST_COMPORT = "/dev/ttyUSB0";//COM12";
    public static final byte GPS_SERIAL_PORT_SERIAL_NO = 1;
    public static final byte AT_SERIAL_PORT_SERIAL_NO = 2;
    public static final byte PPP_SERIAL_PORT_SERIAL_NO = 3;
    public static final int FIVE_HALF_HOURS = 19800000;

    public static final byte IP_CAMERA = 0;
    public static final byte ANALOG_CAMERA = 1;
    public static final long DEFAULT_SEC_2018 = 1514768461000L; //"2018/01/01,01:01:01
    public static final byte ASCII_ZERO = 48;
    public static final String ETHERNET_NETWORKINTERFACE_PATH = "eth0";
    public static String GPRS_NETWORKINTERFACE_PATH = "wwan0";//ppp0";//wlan0";//ppp0";///wlan3"; //wlan3
    public static final String WIFI_NETWORKINTERFACE_PATH = "wlan0"; //wlan0" ; //wlan0";//wlan0";//ppp0";//wlan3"; //wlan4
    public static final File omxplayer_filepath = new File("/usr/bin/omxplayer");
    public static final File rtsp_filepath = new File("/usr/bin/rtsp2avi");
    public static boolean omxplayer_found = false;
    public static boolean rtsp_found = false;
    public static final File mpv_filepath = new File("/usr/bin/mpv");
    public static boolean mpv_found = false;
    public static File usb_detection_filepath = new File("/dev/disk");
    public static File usb_filepath = new File("/media/linaro");///D://ITS//"); D://ITS//"); // = Environment.getExternalStorageDirectory() ;
    public static File obu_filepath = new File("/home/linaro/ObuIts/dist");///h/ome/linaro/media/");//D://ITS//"); D://ITS//"); // = Environment.getExternalStorageDirectory() ;
    public static File apc_filepath = new File("/home/linaro/Documents/");
    public static File obu_images_filepath = new File("/usr/share/icons/sumith_icon/Images");
    public static File quectel_rules_d_filepath = new File("/lib/udev/rules.d");
    public static File obu_library_filepath = new File("/home/linaro/ObuIts/dist/lib");
    public static File panel_filepath = new File(".config/lxpanel/LXDE-pi/panels");
    public static File panel_config_filepath = new File("/usr/bin");  ///usr/bin /toggle-matchbox-keyboard.sh
    public static File panel_desktop_filepath = new File("/usr/share/applications");
    // public static File panel_config_filepath = new File("C:\Users\sumit\OneDrive\Desktop\RD_FILES\speclmsg.txt");
    public static File watchdog_filepath = new File("/etc");
    public static File rtsp_forrir_filepath = new File("/usr/bin");
    public static String media_path = ("/media/linaro");
    public static String ROUTEFS_PATH = "/media/pi/3339OBU";
    public static String HARDDISK_PATH = "/mnt/Recorder"; //"/mnt/Recorder";//
    public static File blacklist_config_filepath = new File("/etc/modprobe.d");
    public static File media_filepath = new File("/mnt/Recorder");//E:/linaro/3339OBU");///media/linaro/3339OBU");//E:/linaro/3339OBU/");///media/linaro/3339OBU");//media/linaro/3339OBU");/////media/linaro/3339OBU");//E:/linaro/3339OBU/");///media/linaro/3339OBU");// /media/linaro/3339OBU"); // "E:/linaro/3339OBU/");// "media/linaro/3339OBU"); // E:/linaro/3339OBU/"); ///media/linaro/3339OBU");//"/
    public static File route_filepath = new File("/mnt/Recorder/Route");///media/linaro/3339OBU/Route  ///home/sumith/Documents/Olectra_Demo_Routes/routes  //E:/linaro/3339OBU/Route");///media/linaro/3339OBU/Route");//E:/linaro/3339OBU/Route");///media/linaro/3339OBU/Route");//"E:/linaro/3339OBU/Route");// /media/linaro/3339OBU/Route");///E:/linaro/3339OBU/Route");// /media/linaro/3339OBU/Route");///
    public static File video_filepath = new File("/mnt/Recorder/Videos"); ///home/linaro/Desktop/Videos") ; // /media/linaro/3339OBU/Videos"); //D://ITS//Videos/"); ///home/linaro/Desktop/Videos"); //
    public static File can_filepath = new File("/mnt/Recorder/Log/CAN");//"E://CAN//"
    public static File log_filepath = new File("/mnt/Recorder/Log");
    public static File snap_filepath = new File("/mnt/Recorder/Videos/Snapshot");
    public static File video_event_filepath = new File("/mnt/Recorder/Videos/Videos_event");
    public static File can_video_event_filepath = new File("/mnt/Recorder/Videos/Videos_can");
    public static File motion_video_event_filepath = new File("/mnt/Recorder/Videos/Videos_motion");
    public static File extra_video_filepath = new File("/mnt/Recorder/Videos/Extra");
    public static final byte CAM_MAIN_STREAM = 0;
    public static final byte CAM_SUB_STREAM = 1;
    public static File udev_rules_filepath = new File("/etc/udev/rules.d");
    public static File old_video_path = new File("/home/linaro/Desktop/Videos");
    public static File config_filepath = new File("/home/pi/");
//     public static File config_filepath = new File("C:\Users\sumit\OneDrive\Desktop\RD_FILES\speclmsg.txt");
    public static File main_filepath = new File("/home/linaro");
    public static File autostart_file = new File("/etc/xdg/lxsession/LXDE-pi/");
    public static File sourceslist_file = new File("/etc/apt/");
    public static File main_route_path = new File("/media/linaro/3339OBU"); //("/home/linaro/Route");
    public static File main_route_filepath = new File("/media/linaro/3339OBU/Route"); //("/home/linaro/Route");
    public static File main_video_filepath = new File("/media/linaro/3339OBU/Video"); //("/home/linaro/Route");
    public static File main_can_filepath = new File("/media/linaro/Log/CAN");
    public static File config_title_bar_filepath = new File("/home/linaro/.config/openbox/");
    public static final String HUB_ETHERNET_ADDRESS = "10.42.0.2";
    public static String CAM1_IPADDR = "10.42.0.3";
    public static String CAM2_IPADDR = "10.42.0.4";
    public static String CAM3_IPADDR = "10.42.0.5";
    public static String CAM4_IPADDR = "10.42.0.6";
    public static String CAM5_IPADDR = "10.42.0.7";
    public static String CAM6_IPADDR = "10.42.0.8";
    public static String CAM7_IPADDR = "10.42.0.9";
    public static String CAM8_IPADDR = "10.42.0.10";
    public static String CAM_ANALOG_IPADDR = "10.42.0.7";
    public static String ANALOG_IPADDR = "10.42.0.6";

    public static final byte CAM_CPPLUS = 0;
    public static final byte CAM_HIKVISION = 1;
    public static final byte CAM_SPARSH = 2;
        public static final byte CAM_DAHUA = 6;

    public static final byte CAM_FOORIR = 3;
    public static final byte CAM_VICON = 5;
    public static final String CAM1_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.3/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM2_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.4/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM3_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.5/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM4_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.6/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM5_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.7/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM6_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.8/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM7_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.9/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM8_MIRROR_OFF = "omxplayer http://admin:admin@10.42.0.10/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM1_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.3/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM2_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.4/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM3_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.5/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM4_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.6/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM5_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.7/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM6_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.8/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM7_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.9/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final String CAM8_VER2_MIRROR_OFF = "omxplayer http://admin:sumith123@10.42.0.10/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
    public static final byte VIDEO_MAIN_STREAM_TYPE = 0;
    public static final byte VIDEO_SUB_STREAM_TYPE = 1;

    public static final String HINDI_FONT_NAME = "Mangal";
    public static final String REG_FONT_NAME = "Kalinga";

    public static final double ONE_GB = 1073741824.00;
    public static final long ONE_DAY = 86400000;
    public static double ACTUAL_SIZE_HARDDISK = 930;// 55;
    public static double TOTAL_SIZE_HARDDISK = 850;// 55;
    public static final double EVENT_FILES_SIZE = 10;// 55;
    public static String stop_view_path = "sudo pkill ffplay ";
    public static String stop_view_path_omxplayer = "sudo pkill omxplayer ";
    public static final byte LANG_ENGLISH = 0;
    public static final byte LANG_HINDI = 1;
    public static final byte LANG_REG = 2;
    public static final byte CAM_CONNECTING = 0;
    public static final byte CAM_CONNECTED = 1;
    public static final byte CAM_DISCONNECTED = 2;
    public static final byte CAM_NO_ROUTE_TO_HOST = 3;
    public static final byte VIDEO_RECORD_CONTINUOUS = 0;
    public static final byte VIDEO_RECORD_MANUAL = 1;
    public static final byte EVENT_VIDEO_RECORD = 2;
    public static final byte CAN_VIDEO_RECORD = 3;
    public static final int GPS_BAUDRATE = 115200;
    public static final int GPRS_BAUDRATE = 115200;
    public static final int AT_BAUDRATE = 115200;
    public static final int DISPLAY_BAUDRATE = 115200;
    public static final byte NO_CALL = 0;
    public static final byte OUT_CALL = 1;
    public static final byte IN_CALL = 2;
    public static final byte CALL_BUSY = 3;
    public static final byte CALL_DIALTONE = 4;
    public static final byte CALL_NOCARRIER = 5;
    public static final byte CALL_RING = 6;
    public static final byte CALL_CONNECTED = 7;
    public static final byte CALL_RING_OK = 8;
    public static final byte CALL_RING_NOTOK = 9;
    public static final byte CALL_WAIT = 10;
    public static final byte CALL_OUT_DIAL = 11;
    public static final byte CALL_CONNECT = 12;
    public static final byte CALL_HELD = 13;
    public static final byte CALL_OUT_ALERT = 14;
    public static final byte CALL_IN_RING = 15;
    public static final byte CALL_IN_ALERT = 16;
    public static final byte CALL_IN_CONNECT = 17;

    public static boolean ACK_IMPLEMENT = true; //true;
    public static boolean ACK_IMPLEMENT2 = true; //true;
    public static final boolean REFRESH_WATCHDOG_TIMER_ENABLED = false;
    public static final byte MAX_NO_CONTACTS = 25;
    public static final byte MAX_NO_PROTOCOLS_CFG_IP = 9;
    public static final String IPADDRESS = "";// "125.99.173.162"; //125.99.173.162"; // "103.243.226.86";
    public static final int PORTNO = 0;//5558;// 3030;//5557; //3030;// 14526;
  //5558;// 3030;//5557; //3030;// 14526;
    public static final byte SPACE_CHAR = 32;
    public static final byte START_PKT = '$';
    public static final byte END_PKT = '#';
    public static final byte CHAR_COMMA = ',';
    public static final byte START_REPORT = 0;
    public static final byte COMM_GPRS = 0;
    public static final byte COMM_GPRS_SMS = 1;
    public static final byte SCHDEULE_ENABLE = 1;
    public static final byte ROUTE_ENABLE = 2;
    public static final byte SCHDEULE_ROUTE_DISABLE = 0;
    public static final byte STOP_CROSSED_BUS = 1;
    public static final byte STOP_CROSSED_INVALID_STOP = 2;
    public static final int UPDATE_INTERVAL_KEYS = 1000;
    public static final int MIC_AUTOSHUTDOWN_VALUE = 5;
    public static final int UPDATE_INTERVAL_IGNMAINDIG = 1000;
    public static final int UPDATE_INTERVAL_SOS = 100;
    public static final int KEYS_WAIT_TIME_TO_READ_AGAIN = 120;
    public static final int KEYS_WAIT_TIME_TO_INVOKE_AGAIN = 10;

    public static final File dir = null;
    public static final char COMMUNICATIONSERVER_ID = '2';
    public static final byte sequence_no = 1;
    public static char FIELD_SEPARATOR = ',';
    public static final byte GPS_GPRMC_TIME = 1;
    public static final byte GPS_GPRMC_STATUS = 2;
    public static final byte GPS_GPRMC_LAT = 3;
    public static final byte GPS_GPRMC_LAT_DIR = 4;
    public static final byte GPS_GPRMC_LONG = 5;
    public static final byte GPS_GPRMC_LONG_DIR = 6;
    public static final byte GPS_GPRMC_SPEED = 7;
    public static final byte GPS_GPRMC_COG = 8;
    public static final byte GPS_GPRMC_DATE = 9;
    public static final byte GPS_GGA_STAT = 6;
    public static final byte GPS_GGA_NO_SATELITES = 7;
    public static final byte GPS_GGA_ALTITUDE = 9;
    public static final byte GPS_GSA_PDOP = 15;
    public static final byte GPS_GSA_HDOP = 16;
    public static final byte CAM1 = 0;
    public static final byte CAM2 = 1;
    public static final byte CAM3 = 2;
    public static final byte CAM4 = 3;
    public static final byte MAX_ACCELERATION_LIMIT = 9;
    public static final byte AVG_BUS_SPEED = 30;
    public static final byte DEF_OVER_SPEED_LIMIT = 45;
    public static final byte DEF_OVER_SPEED_LIM_TIME = 10;
    public static final byte DEF_HARSH_BRK_DUR_1 = 10;
    public static final byte DEF_HARSH_BRK_SPEED_1 = 20;
    public static final byte DEF_HARSH_BRK_DUR_2 = 1;
    public static final byte DEF_HARSH_BRK_SPEED_2 = 8;
    public static final byte DEF_HARSH_ACC_DUR_1 = 10;
    public static final byte DEF_HARSH_ACC_SPEED_1 = 20;
    public static final byte DEF_HARSH_ACC_DUR_2 = 1;
    public static final byte DEF_HARSH_ACC_SPEED_2 = 6;
    // Over Speed States
    public static final byte OVER_SPEED_IDLE_STATE = 0;
    public static final byte OVER_SPEED_DETECTED_STATE = (OVER_SPEED_IDLE_STATE + 1);
    public static final byte HARSH_ACC_IDLE_STATE = 0;
    public static final byte HARSH_ACC_DETECTED_STATE = (HARSH_ACC_IDLE_STATE + 1);
    public static final byte HARSH_BRK_IDLE_STATE = 0;
    public static final byte HARSH_BRK_DETECTED_STATE = (HARSH_BRK_IDLE_STATE + 1);
    public static final short MAX_NO_TRIPS = 500;
    public static final short MAX_NO_ROUTES = 1000;
    public static final short MAX_NO_STOPS = 500;
    public static final short MAX_NO_LOCATION_BASED_ADS = 200;
    public static final int MAX_NO_TRAFFIC_SIGNAL_POINTS = 200;
    public static final int MAX_NO_PRE_DEFINED_LOCATION_POINTS = 100;
    public static final byte CODED_SYSTEM_RESET = 1;
    public static final byte CODED_SYSTEM_RESET_STATUS_NORMAL = 1;
    public static final byte CODED_POWER = 2;
    public static final byte CODED_POWER_STAT_ON = 1;
    public static final byte CODED_POWER_STAT_OFF = 0;

    public static final byte CODED_BATTERY = 3;
    public static final byte CODED_BATTERY_STAT_NORMAL = 1;
    public static final byte CODED_BATTERY_STAT_BELOW_THRESHOLD = 2;
    public static final byte CODED_BATTERY_STAT_ABT_TO_SHUTDOWN = 3;

    public static final byte CODED_TAMPER = 4;
    public static final byte CODED_TAMPER_STAT_ON = 1;
    public static final byte CODED_TAMPER_STAT_OFF = 0;

    public static final byte CODED_RESERRVED = 0;
    public static final byte CODED_EMERGENCY = 5;
    public static final byte CODED_BREAKDOWN = 6;
    public static final byte CODED_FIRE = 7;
    public static final byte CODED_PRELOAD = 8;
    public static final byte CODED_NO_ROUTEMAS = 9;

    public static final byte CODED_IGNITION = 10;
    public static final byte CODED_IGNITION_STAT_ON = 1;
    public static final byte CODED_IGNITION_STAT_OFF = 0;
    public static final byte ASCII_NUM = 48;
    public static final byte MAX_NO_SLOGAN_ID_FILES = 10;
    public static final int SCHEDULE_TIME_ONE_DAY = 86400 * 1000;
    public static final byte TRIP_END = 0;
    public static final byte TRIP_START = 1;
    public static final byte TRIP_SKIP = 2;
    public static final byte TRIP_UNKNOWN = 3;
    public static final short ARRIVAL_MIN_DISTANE = 0;// 100;
    public static final short NEXT_MIN_DISTANE = 0;// 100;
    public static final short CURRENT_MIN_DISTANE = 50;// 100;
    public static final byte CUR_STOP_STATE = 0;
    public static final byte NEXT_STOP_STATE = 1;
    public static final byte APP_STOP_STATE = 2;
    public static final byte ROUTE_DEVIATE_STATE = 3;
    public static final byte STOP_STATE_NONE = 5;
    public static final byte STOP_STATE_IDENTIFYING = 6;
    public static final byte NON_AUDIO_STOP_STATE = 0;
    public static final byte IDENTIFY_AUDIO_STOP_STATE = 1;
    public static final byte CUR_AUDIO_STOP_STATE = 2;
    public static final byte NEXT_AUDIO_STOP_STATE = 3;
    public static final byte APP_AUDIO_STOP_STATE = 4;
    public static final byte ENDSTOP_AUDIO_STOP_STATE = 5;
    public static final byte CUR_INC_AUDIO_STOP_STATE = 6;
    public static final byte NONE_AUDIO_STOP_STATE = 6;
    public static final byte MAIN_ACTIVITY = 1;
    public static final byte ROUTE_ACTIVITY = 2;
    public static final byte TRIP_ACTIVITY = 3;
    public static final byte VIDEO_ACTIVITY = 4;
    public static final byte AUDIO_ACTIVITY = 5;
    public static final byte CONFIG_ACTIVITY = 6;
    public static final byte DIAGNOSIS_ACTIVITY = 7;
    public static final byte PHONE_ACTIVITY = 8;
    public static final byte PHONEADD_ACTIVITY = 9;
    public static final byte PHONECALL_ACTIVITY = 10;
    public static final byte FILEFOLDER_ACTIVITY = 11;
    public static final byte SOS_ALERT_ACTIVITY = 12;
    public static final byte VHMD_ACTIVITY = 13;
    public static final byte MESSAGES_ACTIVITY = 14;
    public static final byte CAN_ALERTS_ACTIVITY = 15;
    public static final byte SOS_ALERTS_ACTIVITY = 16;
    public static final byte EMERGENCY_ALERTS_ACTIVITY = 17;

    public static final byte DOLLAAR = 36;
    public static final byte ATHERATE = 64;
    public static final byte HASH = 35;

    public static final byte SRC_BYTE = 0;
    public static final byte DES_BYTE = 1;
    public static final byte SEQNO_BYTE = 2;
    public static final byte PKT_TYPE_BYTE = 3;
    public static final byte RX_DATA_BYTE = 4;
    public static final byte MSG_LCD = 1;
    public static final byte MSG_DISPLAY_BRD = 2;
    public static final byte MSG_LCD_DISPLAYBRD = 3;

    public static final byte GPS_OFF = 0;
    public static final byte GPS_FIXED = 1;
    public static final byte GPS_NOT_FIXED = 2;

    public static final byte GSM_OFF = 0;
    public static final byte GSM_CLK_FIXED = 1;
    public static final byte GSM_CLK_NOT_FIXED = 2;

    public static final byte SERVER_CONNECTED = 0;
    public static final byte SERVER_DISCONNECTED = 1;

    public static final byte SLEEP_SLEEP_MODE = 0;
    public static final byte SLEEP_SHUTDOWN_MODE = 1;

    public static final byte HIGH = 1;
    public static final byte LOW = 0;

    public static final char TAMPER_OPEN = 'O';
    public static final char TAMPER_CLOSED = 'C';

    public static final byte EXTERNAL_SOS_HIGH = 1;
    public static final byte EXTERNAL_SOS_LOW = 0;

    public static final byte DIGINPIN_HIGH = 1;
    public static final byte DIGINPIN_LOW = 0;

    public static final short MAX_NO_DRIVER_IDS = 500;

    public static final short MAX_NO_CONDUCTOR_IDS = 500;

    public static final byte SINGLE_IP_INTERFACE = 0;
    public static final byte DUAL_IP_INTERFACE = 1;
    public static final byte ALTERNATE_IP_INTERFACE = 2;

    public static final byte IP1 = 0;
    public static final byte IP2 = 1;
    //coded messages

    public static final byte DIESEL = 0;
    public static final byte CNG = 1;

    public static final byte JBM_BODYCOMPUTER = 1;
    public static final byte JBM_GATEWAY = 2;
    public static final byte ASHOKLEYLAND = 3;
    public static final byte TATA = 4;
    public static final byte EICHER = 5;
    public static final byte CARONA = 6;
    public static final byte GOLD_STONE = 7;

    //display board params
    public static final byte FRONT_DB = 0;
    public static final byte SIDE_DB = 1;
    public static final byte REAR_DB = 2;
    public static final byte INT_DB = 3;
    public static final byte SIDE_DB_ART = 4;
    public static final byte INT_DB_ART = 5;

    public static final byte FRONT_BRD = 1;
    public static final byte SIDE_BRD = 2;
    public static final byte REAR_BRD = 3;
    public static final byte INT_BRD = 4;

    public static final byte BROADCAST_ADDR = (byte) 255;
    public static final byte BROADCAST_FD_ADDR = (byte) 254;
    public static final byte BROADCAST_SD_ADDR = (byte) 253;
    public static final byte BROADCAST_RD_ADDR = (byte) 252;
    public static final byte BROADCAST_ID_ADDR = (byte) 251;

    public static final byte OBU_PKT_ID = 10;
    public static final byte GPS_PKT_ID = 20;
    public static final byte USB_PKT_ID = 30;

    public static final short HEADER1 = 170;
    public static final short HEADER2 = 204;
    public static final byte VMU_ADDR = 1;

    public static final byte FD_ADDR = 2;
    public static final byte SD_ADDR = 3;
    public static final byte RD_ADDR = 4;
    public static final byte ID_ADDR = 5;

    public static final byte FD_BRD_WIDTH = (byte) 160;// 128;
    public static final byte SD_BRD_WIDTH = (byte) 96;
    public static final byte RD_BRD_WIDTH = 96;
    public static final byte ID_BRD_WIDTH = 112;

    public static byte SD_ART_ADDR = 6;
    public static byte ID_ART_ADDR = 7;

    public static final short DATA_PKT = 128;

    public static final byte HEADER_BYTE1 = 0;
    public static final byte HEADER_BYTE2 = 1;
    public static final byte PKT_LEN_MSB_BYTE = 2;
    public static final byte PKT_LEN_LSB_BYTE = 3;
    public static final byte SRC_ADDR_BYTE = 4;
    public static final byte DES_ADDR_BYTE = 5;
    public static final byte FC_BYTE = 6;
    public static final byte DATA_BYTE = 7;

    public static final byte WRITE_MODE = 1;
    public static final byte READ_MODE = 2;

    public static final byte RES_SUCCESS = 0;
    public static final byte RES_UNSUCCESS = 1;
    public static final byte RES_CHKSUM_FAIL = 2;

    public static final byte RES_PKT_LEN_MSB_BYTE = 0;
    public static final byte RES_PKT_LEN_LSB_BYTE = 1;
    public static final byte RES_SRC_BYTE = 2;
    public static final byte RES_DES_BYTE = 3;
    public static final byte RES_FC_BYTE = 4;
    public static final byte RES_PID_CMD_BYTE = RES_FC_BYTE + 3;

    public static final byte NO_SCROLL_INTERNAL_DISPLAY_BRD = 3;
    public static final byte FONT_16_REGULAR = 0;
    public static final byte FONT_16_BOLD = 1;
    public static final byte SCR_FULL = 0;

    public static final byte TIME_ENABLED = 1;
    public static final byte TIME_DISABLED = 0;

    public static final byte ENABLED = 1;
    public static final byte DISABLED = 0;

    public static final byte DRIVER_LOGIN_INITIAL = 0;
    public static final byte DRIVER_LOGIN_SUCCESS = 1;
    public static final byte DRIVER_LOGIN_FAILURE = 2;

    public static final byte BATTERY_STATE_INITIAL = 0;
    public static final byte BATTERY_STATE_CONNECT = 1;
    public static final byte BATTERY_STATE_DISCONNECT = 2;
    public static final byte BATTERY_STATE_LOW = 3;

    public static final double BATTERY_DISCONNECTED_VOLTAGE_VALUE = 8.41;
    public static final double BATTERY_CONNECTED_VOLTAGE_VALUE = 8.25; //8.4
    public static final double BATTERY_CONN_SWITCH_OFF_VOLT_VAL = 6.9;// keep 7.7 for icat battery low

    public static final double LOW_VOLTAGE_MIN_VALUE = 18.0;
    public static final double HIGH_VOLTAGE_MAX_VALUE = 36.0;
    public static final double OVER_HEAT_MAX_VALUE = 80.0;
    public static final short MAX_NO_MAP_POINTS = 100;

    public static final double DEFAULT_MAP_LATITIDE = 20.2961;
    public static final double DEFAULT_MAP_LONGITUDE = 85.8245;
    public static final byte VIDEO_FROM_CAN = 1;
    // public static final byte VIDEO_FROM_CAN2 = 1;
    public static final byte VIDEO_FROM_HOME = 2;
    public static final byte VIDEO_FROM_VIDEO = 0;

    public static final byte NET_BAL_NEUTRAL = 0;
    public static final byte NET_BAL_PRESENT = 1;
    public static final byte NET_BAL_NOTPRESENT = 2;

    public static final byte CELL_LTE = 0;
    public static final byte CELL_3G = 1;
    public static final byte CELL_2G = 2;

    public static final byte SIM_AIRTEL = 1;
    public static final byte SIM_IDEA = 2;
    public static final byte SIM_BSNL = 3;

    public static final byte SIM_NOT_CHECKED = 0;
    public static final byte SIM_NOT_REGISTERED = 1;
    public static final byte SIM_REGISTERED = 2;
    public static final byte SIM_REG_DENIED = 3;
    public static final byte SIM_UNKNOWN = 4;
    public static final byte SIM_REG_SEARCHING = 5;

    public static final byte SWITCH_AUTO_MODE = 0;
    public static final byte SWITCH_MANUAL_MODE = 1;
    public static final byte SWITCH_FIXED_MODE = 2;

    public static final byte SWITCH_PRIMARY_MODE = 0;
    public static final byte SWITCH_SECONDARY_MODE = 1;
    public static final byte SWITCH_THIRD_MODE = 2;

    public static final byte EMBEDDED_SIM = 0;
    public static final byte NORMAL_SIM = 1;

    public static final byte DIG_INPUT_NONE = 0;
    public static final byte DIG_INPUT_SOS = 1;
    public static final byte DIG_INPUT_VIDEO_RECORDING = 2;
    public static final byte DIG_INPUT_STOPREQUEST = 3;
    public static final byte DIG_INPUT_REVERSE_GEAR = 4;
    public static final byte DIG_INPUT_LEFTARROW = 5;
    public static final byte DIG_INPUT_RIGHTARROW = 6;
    public static final byte DIG_INPUT_DOORCAM= 7;

    public static final int SCROLL_BUFFER_SIZE = 200;

    public static final byte VIDEO_CONNECT = 1;
    public static final byte VIDEO_DISCONNECT = 0;

    public static final byte BOARD_STATUS_SUCCESS = 1;
    public static final byte BOARD_STATUS_FAILURE = 0;
    //apc variables
    public static final byte APC_START = 1;
    public static final byte APC_STOP = 2;
    public static final char APC_START_CHAR = '^';
    public static final char APC_END_CHAR = '*';
    // public static  int FTP_PORT_NO=0;
    enum Strings {

        CODED_1("START THE BUS"),
        CODED_2("STOP THE BUS"),
        CODED_3("TAKE LEFT"),
        CODED_4("TAKE RIGHT"),
        CODED_5("GO STRAIGHT"),
        CODED_6("GO FOR TEA"),
        CODED_7("COME BACK TO DEPOT"),
        CODED_8("COME BACK TO TERMINAL"),
        CODED_9("GO SLOW"),
        CODED_10("SQUAD CHECKING"),
        CODED_11("ROUTE DIVERTION");
        private final String stringValue;
        Strings(final String s) {
            stringValue = s;
        }
        @Override
        public String toString() {
            return stringValue;
        }
        public static Pin GSM_POWER_ONOFF = RaspiPin.GPIO_05; //same for both boards
        public static Pin GSM_RESET = RaspiPin.GPIO_06; //old board : RaspiPin.GPIO_25;
        public static Pin GSM_STATUS_PIN = RaspiPin.GPIO_04; //old board : RaspiPin.GPIO_07;
        public static Pin IGNITION_STATUS = RaspiPin.GPIO_00; //same for both boards
        public static Pin MAINS_STATUS = RaspiPin.GPIO_02;//
        public static Pin SOS_INPUT = RaspiPin.GPIO_03;
        public static Pin RS485_ONOFF = RaspiPin.GPIO_01; //same for both boards
        public static Pin DIG2_INPUT = RaspiPin.GPIO_22;
        public static Pin DIG3_INPUT = RaspiPin.GPIO_27;
        public static Pin DIG4_INPUT = RaspiPin.GPIO_13;
        public static Pin AUDIO_VOICECALL_COMM_ONOFF = RaspiPin.GPIO_24; // audio control 2 //same for both boards Audio_CTRL_2
        public static Pin TAMPER_DIG_INPUT = RaspiPin.GPIO_28; //NEW
        public static Pin FM_RADIO_PIN_ONOFF = RaspiPin.GPIO_12;
        public static Pin AUDIO_SPEAKER_COMM_ONOFF = RaspiPin.GPIO_25; //new card RaspiPin.GPIO_23; //   //old board : RaspiPin.GPIO_28;  AUDIO_CTRL1

        public static Pin MIC_STATUS = RaspiPin.GPIO_29; //same for both boards pin 40 mic status
        public static Pin POWER_ONOFF = RaspiPin.GPIO_11; ////old board :  RaspiPin.GPIO_08;
        public static Pin LCD_RESET = RaspiPin.GPIO_10;
        //tinker board cpu pin nos
        public static short GSM_STATUS_PIN_TINKER_CPU = 162; //old board : RaspiPin.GPIO_07;
        public static short IGNITION_STATUS_TINKER_CPU = 164; //same for both boards
        public static short MAINS_STATUS_TINKER_CPU = 17;//
        public static short SOS_INPUT_TINKER_CPU = 233;
        public static short DIG2_INPUT_TINKER_CPU = 168;
        public static short DIG3_INPUT_TINKER_CPU = 234;
        public static short DIG4_INPUT_TINKER_CPU = 256;
        public static short MIC_STATUS_TINKER_CPU = 188; //same for both boards pin 40 mic status
        public static short TAMPER_DIG_INPUT_TINKER_CPU = 187; //NEW
        //status of input and output pins
        //input pins
        public static PinState MAINS_ON_STATE = PinState.LOW;
        public static PinState MAINS_OFF_STATE = PinState.HIGH;
        public static final PinState MIC_STATUS_ON_STATE = PinState.LOW;
        public static final PinState MIC_STATUS_OFF_STATE = PinState.HIGH;
        public static PinState IGNITION_PIN_ON_STATE = PinState.LOW;
        public static PinState IGNITION_PIN_OFF_STATE = PinState.HIGH;
        public static PinState LOW_STATE = PinState.LOW;
        public static PinState HIGH_STATE = PinState.HIGH;
        public static PinState LCD_RESET_ON_STATE = PinState.HIGH;
        public static PinState LCD_RESET_OFF_STATE = PinState.LOW;
        public static PinState GSM_STATUS_ON_STATE = PinState.LOW;
        public static PinState GSM_STATUS_OFF_STATE = PinState.HIGH;
        //output status pins
        public static final PinState GSM_RESET_PIN_ON_STATE = PinState.LOW;
        public static final PinState GSM_RESET_PIN_OFF_STATE = PinState.HIGH;
        public static final PinState POWER_ON_STATE = PinState.HIGH;
        public static final PinState POWER_OFF_STATE = PinState.LOW;
        public static final PinState RS485_SIGNAL_ON_STATE = PinState.HIGH;
        public static final PinState RS485_SIGNAL_OFF_STATE = PinState.LOW;
        public static final PinState GSM_POWER_ON_STATE = PinState.HIGH;
        public static final PinState GSM_POWER_OFF_STATE = PinState.LOW;
        public static final PinState VOICE_CALL_ON_STATE = PinState.HIGH;
        public static final PinState VOICE_CALL_OFF_STATE = PinState.LOW;
        public static final PinState USBHUB_ON_STATE = PinState.HIGH;
        public static final PinState USBHUB_OFF_STATE = PinState.LOW;
        public static final PinState MIC_CONTROL_VOICE_CALL_STATE = PinState.HIGH;
        public static final PinState MIC_CONTROL_SPEAKER_STATE = PinState.LOW;
        public static PinState FM_ON_STATE = PinState.HIGH; // PinState.LOW;
        public static PinState FM_OFF_STATE = PinState.LOW;// PinState.HIGH;
        public static final PinState AUDIO_SIGNAL_SPEAKER_ON_STATE = PinState.HIGH;
        public static final PinState AUDIO_SIGNAL_SPEAKER_OFF_STATE = PinState.LOW;
        public static final PinState DIGITAL_OUTPUT1_ON_STATE = PinState.HIGH;
        public static final PinState DIGITAL_OUTPUT1_OFF_STATE = PinState.LOW;
        public static final PinState DIGITAL_OUTPUT2_ON_STATE = PinState.HIGH;
        public static final PinState DIGITAL_OUTPUT2_OFF_STATE = PinState.LOW;
        public static final PinState DEVICE_RESET_OFF_STATE = PinState.HIGH;
        public static final PinState DEVICE_RESET_ON_STATE = PinState.LOW;
        public static final byte IGNITION_READ_DEFAULT_STATE = 0;
        public static final byte IGNITION_READ_OFF_STATE = 1;
        public static final byte IGNITION_READ_ON_STATE = 2;
        public static final byte MAINS_READ_DEFAULT_STATE = 0;
        public static final byte MAINS_READ_OFF_STATE = 1;
        public static final byte MAINS_READ_ON_STATE = 2;
        public static byte KEYS_PIN_OFF = 49;
        public static byte KEYS_PIN_ON = 48;
        public static byte GPRS_MSGTYPE_POS = 13;
        public static byte GPRS_PKT_ACK_SUCCESS = 1;
        public static byte GPRS_PKT_ACK_FAILURE = 0;
        public static byte DIG_INPUT_KEYS_PIN_ON = 49;
        public static byte DIG_INPUT_KEYS_PIN_OFF = 48;
        public static final byte MAX_DISBRD_RETRYCNT = 2;
        public static final byte DIG_INPUT_TYPE = 1;
        public static final byte DIG_OUTPUT_TYPE = 2;
        public static final byte DIG_INPUT1_ID = 1;
        public static final byte DIG_INPUT2_ID = 2;
        public static final byte DIG_INPUT3_ID = 3;
        public static final byte DIG_INPUT4_ID = 4;
        public static final String DIG_INPUT1_NAME = "Ignition";
        public static final String DIG_INPUT2_NAME = "";
        public static final String DIG_INPUT3_NAME = "";
        public static final String DIG_INPUT4_NAME = "";
        public static final char SWITCH_ON = '1';
        public static final char SWITCH_OFF = '0';
        public static final byte KEMITO_DISBRD = 0;
        public static final byte SUMITH_DISBRD = 1;
        public static final int SUMITH_BAUDRATE = 115200;
        public static final int KEMITO_BAUDRATE = 9600;
        public static final boolean LCD_ON = true;
        public static final boolean LCD_OFF = false;
        public static final int MAX_NO_BUF_LEN = 5000;
        public static final byte PANIC_TERRORIST_ATTACK = 1;
        public static final byte PANIC_BUS_HIJACKED = 2;
        public static final byte PANIC_MET_SEVERE_ACCIDENT = 3;
        public static final byte PANIC_BUS_CAUGHT_FIRE = 4;
        public static final byte PANIC_DROWNING_IN_WATER = 5;
        public static final byte PANIC_BREAKDOWN = 6;
        public static final byte PANIC_FIRE = 7;
        public static final byte PANIC_SOS = 8;
        public static final byte PANIC_EMERGENCY = 9;
        public static final byte GPRS_CONN_MAX_RETRIES = 6;//MINUTES
        public static final byte GPRS_CONNECTIVITY_CHECK_TIME = 5;// 15 ;//MINUTES
        public static final byte SERVERDISCONNECT_CHECK_TIME = 5;// 30 ;//MINUTES
        public static final byte GPSPKT_IN_GPRS_CHECK_TIME = 5;// 30 ;//MINUTES
        public static final byte PING_STATUS_PKTS_CNT = 10;
        public static final byte GPRS_SERVER_MAX_RETRIES = 2;
        public static final byte MAX_NO_GPRS_PKT_MISSED_DATA_QUEUE = 40;
        public static final byte RESET_OBU_DEFAULT = 0;
        public static final byte RESET_OBU_CONFIRMED = 1;
        public static final byte RESET_OBU_FINISHED = 2;
        public static final byte MAX_ACK_PKT_RES_WAIT_TIME = 20;
        public static final byte GPRS_CONNECTED = 1;
        public static final byte GPRS_DISCONNECTED = 2;
        public static final byte GPRS_LOCAL_IP = 3;
        public static final byte GPRS_REMOTE_IP = 4;
        public static final byte GPRS_PRIMARY_DNS = 5;
        public static final byte GPRS_SEC_DNS = 6;
        public static final byte GPRS_FINALLY_CONNECTED = 7;
        public static final byte NORMAL_RESET = 0;
        public static final byte WATCHDOG_RESET = 1;
        public static final byte LOW_PWR_RESET = 2;
        public static final byte MANUAL_RESET = 3;
        public static final byte CAM_CONNECTED = 1;
        public static final byte CAM_DISCONNECTED = 0;
    }
    public static final byte INTDB_DATA_NOT_SAVE = 0;
    public static final byte INTDB_DATA_SAVE = 1;
    public static final byte RS232_NONE = 0;
    public static final byte RS232_GPS = 1;
    public static final byte RS232_RFID = 2;
    public static final byte RS232_ALCO = 3;
    public static final byte CAM_1 = 0;
    public static final byte CAM_2 = 1;
    public static final byte CAM_3 = 2;
    public static final byte CAM_4 = 3;
    public static final byte CAM_5 = 4;
    public static final byte CAM_6 = 5;
    public static final byte CAM_7 = 6;
    public static final byte CAM_8 = 7;

    public static final byte ETHERNET_NONE = 0;
    public static final byte ETHERNET_GPS = 1;
    public static final byte ETHERNET_RFID = 2;
    public static String LCD_TOUCH_COMPANY_NAME = "ILITEK";
    public static final String LCD_TOUCH_COMPANY_NAME1 = "wch";
    public static final String LCD_TOUCH_COMPANY_NAME2 = "eGalax Inc.";
    public static final byte QUECTEL_MOD_EC20 = 1;
    public static final byte QUECTEL_MOD_EC25 = 2;
    public static final byte UPLOADTYPE_SNAPSHOT = 0;
    public static final byte UPLOADTYPE_VIDEO = 1;
    public static final byte ACK_FAILURE = 0;
    public static final byte ACK_SUCCESS = 1;
    public static final byte ACK_NO_FILES = 2;
    public static final byte ACK_FTP_FAILURE = 3;
    public static final byte ACK_HD_FAILURE = 4;
    public static final char COMMA = ',';
    public static final char START_FLAG = '$';
    public static final char END_FLAG = '*';
    public static final String HEADER_LOGIN = "LGN";
    public static final String HEADER_NORMAL = "VTU";
    public static final String VENDOR_ID = "ATL";
    public static final char LIVE_PACKET = 'L';
    public static final char HISTORY_PACKET = 'H';
    public static final String PKT_TYPE_NORMAL = "NR";
    public static final String PKT_TYPE_IGNITION_ON = "IN";
    public static final String PKT_TYPE_IGNITION_OFF = "IF";
    public static final String PKT_TYPE_TAMPER_ON = "TA";
    public static final String PKT_TYPE_TAMPER_OFF = "TC";
    public static final String PKT_TYPE_BAT_CONN = "BR";
    public static final String PKT_TYPE_BAT_DIS_CONN = "BD";
    public static final String PKT_TYPE_BAT_LOW = "BL";
    public static final String PKT_TYPE_EMERGENCY_ALERT = "EA";
    public static final String PKT_TYPE_TRIP_END = "TE";
    public static final String PKT_TYPE_TRIP_START = "TS";
    public static final byte SENSORIZE_SIM = 0;
    public static final byte INTALIA_SIM = 1;
}
