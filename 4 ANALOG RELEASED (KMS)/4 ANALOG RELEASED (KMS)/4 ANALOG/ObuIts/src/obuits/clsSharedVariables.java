package obuits;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sumitha
 */
import java.util.Date;
import static obuits.clsCanParams.JBM_D_PGN_DOOR1_OPEN_STATUS;
import static obuits.clsCanParams.JBM_D_PGN_DOOR2_OPEN_STATUS;
import static obuits.clsCanParams.JBM_D_PGN_EMERGENCY_STOP_STATUS;
import static obuits.clsCanParams.JBM_D_PGN_TRAN_CURRENT_GEAR;
import static obuits.clsCanParams.JBM_D_SPN_DOOR1_OPEN_STATUS;
import static obuits.clsCanParams.JBM_D_SPN_DOOR2_OPEN_STATUS;
import static obuits.clsCanParams.JBM_D_SPN_EMERGENCY_STOP_STATUS;
import static obuits.clsCanParams.JBM_D_SPN_TRAN_CURRENT_GEAR;
import static obuits.clsDefines.CELL_LTE;

import static obuits.clsDefines.COMM_GPRS;
import static obuits.clsDefines.DIG_INPUT_NONE;
import static obuits.clsDefines.DIG_INPUT_SOS;

import static obuits.clsDefines.IPADDRESS;
import static obuits.clsDefines.PORTNO;
import static obuits.clsDefines.LANG_ENGLISH;
import static obuits.clsDefines.MAX_NO_STOPS;
import static obuits.clsDefines.NO_CALL;
import static obuits.clsDefines.PEDESTRIAN1_AUDIO;
import static obuits.clsDefines.PEDESTRIAN2_AUDIO;
import static obuits.clsDefines.PEDESTRIAN3_AUDIO;
import static obuits.clsDefines.PEDESTRIAN4_AUDIO;
import static obuits.clsDefines.PEDESTRIANDIALOG_AUDIO;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.SERVER_DISCONNECTED;
import static obuits.clsDefines.SIM_NOT_CHECKED;
import static obuits.clsDefines.SINGLE_IP_INTERFACE;
import static obuits.clsDefines.SLEEP_SLEEP_MODE;
import static obuits.clsDefines.Strings.IGNITION_READ_DEFAULT_STATE;
import static obuits.clsDefines.Strings.LCD_ON;
import static obuits.clsDefines.Strings.MAINS_READ_DEFAULT_STATE;
import static obuits.clsDefines.Strings.NORMAL_RESET;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.TAMPER_CLOSED;
import static obuits.clsDefines.TIME_ENABLED;
import static obuits.clsDefines.VIDEO_MAIN_STREAM_TYPE;
import static obuits.clsDefines.VIDEO_SUB_STREAM_TYPE;
import static obuits.clsDefines.PEDESTRIAN_DETECTION_AVAILABLE;

public class clsSharedVariables {

    public static clsAudioFilesQueue objAudQue = new clsAudioFilesQueue();
    // public static clsSendSerialPort objSer;

    public static byte storage_type = clsDefines.STORAGE_HARDDISK;
    private static boolean onvif_support = false;
    private static boolean separate_gps = false;

    public static boolean fm_radio_enable = false;

    private static boolean gyroscope_enabled = true;

    public static boolean first_time_to_send_sch_req = false;
    public static boolean first_time_to_send_sch_req_ip2 = false;

    public static byte lang_type = LANG_ENGLISH;
    public static boolean exception_came = false;

    public static byte camera_type = clsDefines.IP_CAMERA;

    // ******************************not implemented ***************************************
    //gsm/gprs params
    //mode of communication variables
    // public static byte comm_poll = START_REPORT;
    private static boolean force_sms_came = false;
    private static byte comm_mode_type = COMM_GPRS;
    private static String sms_data_to_send = "";
    private static boolean send_sms_state = false;
    private static boolean send_sms_greater_sym_state = false;
    private static byte sms_response_came = 0;

    public static String sms_ph_no = "";
//    public static boolean video_4_screens_enable = false;

    public static byte prevIntBatState = 0;
    //public static byte prevIntBatState1 = 0;
    private static boolean live_stream_enabled = false; // false;

    public static int route_deviate_time = 60;  // we will see lter  presently ot implemented
    public static int non_stop_geofence_dis = 100;
    public static short keep_alive_timer = 15;

    public static short gps_update_rate_normal = 10;
    public static short gps_update_rate_sleep = 15; //minutes
    public static short video_update_rate = 30; // minutes

    private static short emergency_alert_state_time_duration = 5;
    private static boolean emergency_alert_came = false;

    private static byte emergency_alert_panic_button = 1;
    private static byte emergency_alert_sms_mode = 0;

    private static String setAddrDisbrdData = "";

    public static int sms_update_rate = 1; //minutes
    public static short no_gps_config_value = 30;

    public static short can_update_time_interval = 10;

    private static String ip_address2 = "";// "182.76.74.78";
    private static int port_no2 = 0; //3030;
    public static String ip_add = "";//10.42.0.100";

    private static boolean ip_address1_enable = true;
    private static boolean ip_address2_enable = false;
    private static boolean ip_address3_enable = false;
    private static boolean ip_address4_enable = false;
    private static boolean ip_address5_enable = false;

    private static byte ip_interface = SINGLE_IP_INTERFACE;
    private static String ip_address3 = "";
    private static String ip_address4 = "";
    private static int port_no3 = 0;
    private static int port_no4 = 0;
    private static String ip_address5 = "";
    private static int port_no5 = 0;
    public static String apn = "ctplm2map.in";// "internet";
    public static String server_url;
    public static boolean chk_server_dns = false;
    public static String server_dns;

    private static int apc_hour_people_in = 0;
    private static int apc_hour_people_out = 0;
    private static long apc_hour_date_time = 0;
    private static byte apc_hour_hour = 0;
    private static int apc_day_people_in = 0;
    private static int apc_day_people_out = 0;
    private static long apc_day_date_time = 0;
    private static byte apc_day_day = 0;
    private static byte embedded_sim_name = clsDefines.INTALIA_SIM;
    private static byte storage1_status = 1;
    private static byte storage1_mem_status = 1;
    private static byte storage2_status = 1;
    private static byte storage2_mem_status = 1;

    public static String ftp_ipaddress = "152.52.139.83";//"122.169.250.93"; //172.10.21.108/R001/RO"; //"183.82.116.93";
    public static String ftpusername = "cctv";//"sumith";//SumithFtp";// "kpiBITS_"; // "SumithFtp";
    public static String ftpPassword = "C@mera@321";//sumith1234";// "Suhj"; // "sumith1234";
     public static int ftp_port_no=22;

    
    // ******************************END  not implemented ***************************************
    //**************************************  set and get variables **************************/
    private static String OBUId = "000009";
    private static String imei_number = "123456789123456";
    private static String ethernet_ip = "10.42.0.1";
    private static String mac_address = " ";
    private static String vehicle_reg_no = "000009";
    private static String OBU_name = "SUMITH 3339";

    private static String hour_date = "";
    private static String day_date = "";
    private static String hour_date1 = "";
    private static String day_date1 = "";

    private static String ip_address1 = IPADDRESS; //"122.169.250.93";
    private static int port_no1 = PORTNO; // 5558;

    private static short cur_trip_no = 0; // shared var has function for set and get
    private static byte cur_trip_status = clsDefines.TRIP_START; // shared var has function for set and get
    private static boolean auto_trip_status = false; // shared var has function for set and get

    private static Double previous_latitude = 0.0;
    private static Double previous_longitude = 0.0;
    private static Double odometer_previous_latitude = 0.0;
    private static Double odometer_previous_longitude = 0.0;

    private static Double next_latitude = 0.0;
    private static Double next_longitude = 0.0;

    private static Double cur_longitude = 0.0;
    private static Double cur_latitude = 0.0;

    private static char cur_latitude_dir = 'N';
    private static char cur_longitude_dir = 'E';

    private static String cur_altitude = "0.0";
    private static double cur_speed = 0.0;
    private static double dis_prev_gps_data = 0.0;

    private static byte no_satelites = 0;
    private static float heading = 0;
    private static String cur_time = null;
    private static String cur_date = null;
    private static boolean cur_route_status = false;
    private static String cur_route_no;
    private static Double show_stop_dis = 0.0;
    private static Double travelled_distance = 0.0;
    private static short gps_speed = 0;
    private static long cur_sec = 0;
    private static short gyro_angle = 60;
    private static boolean gyro_values_activated = false;

    private static int event_pkt_seq_number5 = 1;
    private static boolean sch_req_send = false;
    public static boolean server_config_changed = false;
    public static boolean server2_config_changed = false;
    public static boolean server3_config_changed = false;
    public static boolean server4_config_changed = false;
    public static boolean server5_config_changed = false;
    private static byte sleep_mode_state = SLEEP_SLEEP_MODE;

    private static boolean front_disbrd_enable = true;
    private static boolean side_disbrd_enable = true;
    private static boolean rear_disbrd_enable = true;
    private static boolean int_disbrd_enable = true;
    private static boolean normal_mode_disbrd_enable = true;
    private static boolean side_art_disbrd_enable = false;
    private static boolean int_art_disbrd_enable = false;
    private static boolean send_pids_db_enable = true;
    private static boolean send_driverinfo_db_enable = false;

    private static int day_intensity = 250;
    private static int night_intensity = 100;

    private static boolean led_data_res_came = false;
    //  private static boolean led_data_res_came1 = false;
    private static boolean reset_can_data_res_came = false;
    //  private static boolean reset_can_data_res_came1 = false;

    //  private static Date day_intensity_time;
    // private static Date night_intensity_time;
    private static int mains_off_intensity = 60;
    private static boolean lcd_onoff_state = LCD_ON;
    private static short no_of_stops_in_a_route = 0;
    private static byte cur_stop_no = 0;
    private static double stop_stop_dis = 0;

    private static byte stop_state = clsDefines.STOP_STATE_IDENTIFYING;
    private static byte map_stop_state = clsDefines.NON_AUDIO_STOP_STATE;
    public static String[] panic_message = new String[50];
    public static byte[] panic_message_id = new byte[50];
    public static byte no_panic_messages = 0;

    public static byte[] coded_msg_no;
    public static String[] coded_msg_name;
    public static byte no_coded_messages = 0;
    //CUR_STOP_STATE;
    private static boolean harsh_acc_state = false;
    private static boolean harsh_break_state = false;

    private static boolean over_speed_state = false;
    private static byte current_activity = clsDefines.MAIN_ACTIVITY;
    //  private static Activity current_activity_name;
    private static boolean schedule_updated = false;
    private static short no_of_routes = 0;
    private static boolean per_sch_status = false;
    private static boolean mic_pressed = false;
    private static boolean audio_play_on = false;

    //DISPLAY BOARD PARAMS
    private static byte g_dis_brd_name = SUMITH_DISBRD;
    private static int g_dis_brd_baudrate = 115200;//9600;
    private static int g_dis_brd_no_retries = 3;
    private static int g_dis_brd_retry_wait_time = 100;
    private static int g_dis_brd_gap = 200;

    private static int trip_detect_dis = 40;
    private static int route_deviate_dis = 500;
    private static byte sch_route_enable = ROUTE_ENABLE;
    private static int non_stop_time = 180;
    private static int non_stop_distance = 100;
    private static int main_device_shut_time = 30;
    private static byte coded_data_from_cs_string = 0;
    private static short[] driver_bus_stop_dur = new short[100];
    private static short driver_bus_stop_cnt = 0;
    private static boolean panic_data_sent_server = false;
    public static boolean driverLoginEnabled = false;
    public static boolean OverSpeedBuzzerEnabled = false;

    private static byte digInput1Name = DIG_INPUT_NONE;
    private static byte digInput2Name = DIG_INPUT_NONE;//DIG_INPUT_VIDEO_RECORDING;
    private static byte digInput3Name = DIG_INPUT_NONE;
    private static byte digInput4Name = DIG_INPUT_NONE;

    private static String digInput1Value = "LOW";
    private static String digInput2Value = "LOW";
    ;
    private static String digInput3Value = "LOW";
    ;
    private static String digInput4Value = "LOW";
    ;

    private static boolean digInput1ClosedState = true;
    private static boolean digInput2ClosedState = true;
    private static boolean digInput3ClosedState = false;
    private static boolean digInput4ClosedState = false;

    private static byte record_type;
    private static byte sch_record_sunday = clsDefines.ENABLED;
    private static byte sch_record_monday = clsDefines.ENABLED;
    private static byte sch_record_tuesday = clsDefines.ENABLED;
    private static byte sch_record_wednesday = clsDefines.ENABLED;
    private static byte sch_record_thursday = clsDefines.ENABLED;
    private static byte sch_record_friday = clsDefines.ENABLED;
    private static byte sch_record_saturday = clsDefines.ENABLED;

    private static int rec_expiry_days = 30;

    private static int rec_expiry_days_event = 30;

    private static boolean event_record_enabled = false;
    private static byte event_post_record_time = 1;
    private static byte event_pre_record_time = 1;
    private static byte event_stream_type = clsDefines.VIDEO_SUB_STREAM_TYPE;

    private static boolean can_record_enabled = false;
    private static byte can_post_record_time = 1;
    private static byte can_pre_record_time = 1;
    private static byte can_stream_type = clsDefines.VIDEO_SUB_STREAM_TYPE;

    private static String record_from_time = "06:00";
    private static String record_to_time = "22:00";
    private static boolean PMI_COMPANY = false;
    private static boolean shared_network = true;

    private static boolean server_live_cam1 = true;
    private static boolean server_live_cam2 = true;
    private static boolean server_live_cam3 = true;
    private static boolean server_live_cam4 = true;
    private static boolean server_live_cam5 = true;
    private static boolean server_live_cam6 = true;
    private static boolean server_live_cam7 = true;
    private static boolean server_live_cam8 = true;

    private static boolean record_cam1 = true;
    private static boolean record_cam2 = true;
    private static boolean record_cam3 = true;
    private static boolean record_cam4 = true;
    private static boolean record_cam5 = true;
    private static boolean record_cam6 = true;
    private static boolean record_cam7 = true;
    private static boolean record_cam8 = true;

    private static boolean record_cam1_audio = false;
    private static boolean record_cam2_audio = false;
    private static boolean record_cam3_audio = false;
    private static boolean record_cam4_audio = false;
    private static boolean record_cam5_audio = false;
    private static boolean record_cam6_audio = false;
    private static boolean record_cam7_audio = false;
    private static boolean record_cam8_audio = false;

    private static boolean enable_8cams = false;
    private static boolean enable_cam1 = true;
    private static boolean enable_cam2 = true;
    private static boolean enable_cam3 = true;
    private static boolean enable_cam4 = true;
    private static boolean enable_cam5 = true;
    private static boolean enable_cam6 = true;
    private static boolean enable_cam7 = true;
    private static boolean enable_cam8 = true;

    private static boolean articulated_bus = false;

    private static String cam1UserName = "admin";
    private static String cam2UserName = "admin";
    private static String cam3UserName = "admin";
    private static String cam4UserName = "admin";
    private static String cam5UserName = "admin";
    private static String cam6UserName = "admin";
    private static String cam7UserName = "admin";
    private static String cam8UserName = "admin";

    private static String cam1Pwd = "sumith123";
    private static String cam2Pwd = "sumith123";
    private static String cam3Pwd = "sumith123";
    private static String cam4Pwd = "sumith321";
    private static String cam5Pwd = "sumith123";
    private static String cam6Pwd = "sumith123";
    private static String cam7Pwd = "sumith123";
    private static String cam8Pwd = "sumith123";

    private static byte camType1 = clsDefines.CAM_HIKVISION;
    private static byte camType2 = clsDefines.CAM_HIKVISION;
    private static byte camType3 = clsDefines.CAM_HIKVISION;
    private static byte camType4 = clsDefines.CAM_HIKVISION;
    private static byte camType5 = clsDefines.CAM_HIKVISION;
    private static byte camType6 = clsDefines.CAM_HIKVISION;
    private static byte camType7 = clsDefines.CAM_HIKVISION;
    private static byte camType8 = clsDefines.CAM_HIKVISION;

    private static String camera = "Cam1";
    private static String streamtype = "Main Stream"; //videomode
    private static String videomode = "videomode";
    private static byte resolution = 0;
    private static String bitratetype = "VBR";
    private static byte videoquality = 6;
    private static int framerate = 25;  //maxbitrate
    private static int frameInterval = 50;
    private static int bitrate = 256;
    private static int maxbitrate = 256;  //videoencode
    private static String videoencode = "H.264";
    private static String substreamtype = "Sub Stream"; //videomode
    private static String subvideomode = "audiomode";
    private static byte subresolution = 3;
    private static String subbitratetype = "VBR";
    private static byte subvideoquality = 2;
    private static int subframerate = 20;  //maxbitrate
    private static int subFrameInterval = 50;

    private static int subbitrate = 256;  //videoencode
    private static int submaxbitrate = 256;  //videoencode
    private static String subvideoencode = "MPEG";

    private static String camera2 = "Cam2";
    private static String streamtype2 = "Main Stream"; //videomode
    private static String videomode2 = "videomode";
    private static byte resolution2 = 0;
    private static String bitratetype2 = "VBR";
    private static byte videoquality2 = 6;
    private static int frameInterval2 = 50;
    private static int framerate2 = 25;  //maxbitrate
    private static int bitrate2 = 256;
    private static int maxbitrate2 = 256;  //videoencode
    private static String videoencode2 = "H.264";
    private static String substreamtype2 = "Sub Stream"; //videomode
    private static String subvideomode2 = "audiomode";
    private static byte subresolution2 = 3;
    private static String subbitratetype2 = "VBR";
    private static byte subvideoquality2 = 2;
    private static int subframerate2 = 20;  //maxbitrate
    private static int subFrameInterval2 = 50;
    private static int subbitrate2 = 256;
    private static int submaxbitrate2 = 256;  //videoencode
    private static String subvideoencode2 = "MPEG";

    private static String camera3 = "Cam3";
    private static String streamtype3 = "Main Stream"; //videomode
    private static String videomode3 = "videomode";
    private static byte resolution3 = 0;
    private static String bitratetype3 = "VBR";
    private static byte videoquality3 = 6;
    private static int frameInterval3 = 50;
    private static int framerate3 = 25;  //maxbitrate
    private static int bitrate3 = 256;
    private static int maxbitrate3 = 256;  //videoencode
    private static String videoencode3 = "H.264";
    private static String substreamtype3 = "Sub Stream"; //videomode
    private static String subvideomode3 = "audiomode";
    private static byte subresolution3 = 3;
    private static String subbitratetype3 = "VBR";
    private static byte subvideoquality3 = 2;
    private static int subframerate3 = 20;  //maxbitrate
    private static int subFrameInterval3 = 50;
    private static int subbitrate3 = 256;
    private static int submaxbitrate3 = 256;  //videoencode
    private static String subvideoencode3 = "MPEG";

    private static String camera4 = "Cam4";
    private static String streamtype4 = "Main Stream"; //videomode
    private static String videomode4 = "videomode";
    private static byte resolution4 = 0;
    private static String bitratetype4 = "VBR";
    private static byte videoquality4 = 6;
    private static int frameInterval4 = 50;
    private static int framerate4 = 25;  //maxbitrate
    private static int bitrate4 = 256;
    private static int maxbitrate4 = 256;  //videoencode
    private static String videoencode4 = "H.264";
    private static String substreamtype4 = "Sub Stream"; //videomode
    private static String subvideomode4 = "audiomode";
    private static byte subresolution4 = 3;
    private static String subbitratetype4 = "VBR";
    private static byte subvideoquality4 = 6;
    private static int subframerate4 = 20;  //maxbitrate
    private static int subFrameInterval4 = 50;
    private static int subbitrate4 = 256;
    private static int submaxbitrate4 = 256;  //videoencode
    private static String subvideoencode4 = "MPEG";

    private static String camera5 = "Cam5";
    private static String streamtype5 = "Main Stream"; //videomode
    private static String videomode5 = "videomode";
    private static byte resolution5 = 0;
    private static String bitratetype5 = "VBR";
    private static byte videoquality5 = 0;
    private static int frameInterval5 = 50;
    private static int framerate5 = 25;  //maxbitrate
    private static int bitrate5 = 256;
    private static int maxbitrate5 = 256;  //videoencode
    private static String videoencode5 = "H.264";
    private static String substreamtype5 = "Sub Stream"; //videomode
    private static String subvideomode5 = "audiomode";
    private static byte subresolution5 = 3;
    private static String subbitratetype5 = "VBR";
    private static byte subvideoquality5 = 2;
    private static int subframerate5 = 20;  //maxbitrate
    private static int subFrameInterval5 = 50;
    private static int subbitrate5 = 256;
    private static int submaxbitrate5 = 256;  //videoencode
    private static String subvideoencode5 = "MPEG";

    private static String camera6 = "Cam6";
    private static String streamtype6 = "Main Stream"; //videomode
    private static String videomode6 = "videomode";
    private static byte resolution6 = 0;
    private static String bitratetype6 = "VBR";
    private static byte videoquality6 = 0;
    private static int frameInterval6 = 50;
    private static int framerate6 = 25;  //maxbitrate
    private static int bitrate6 = 256;
    private static int maxbitrate6 = 256;  //videoencode
    private static String videoencode6 = "H.264";
    private static String substreamtype6 = "Sub Stream"; //videomode
    private static String subvideomode6 = "audiomode";
    private static byte subresolution6 = 3;
    private static String subbitratetype6 = "VBR";
    private static byte subvideoquality6 = 2;
    private static int subframerate6 = 20;  //maxbitrate
    private static int subFrameInterval6 = 50;
    private static int subbitrate6 = 256;
    private static int submaxbitrate6 = 256;  //videoencode
    private static String subvideoencode6 = "MPEG";

    private static String camera7 = "Cam7";
    private static String streamtype7 = "Main Stream"; //videomode
    private static String videomode7 = "videomode";
    private static byte resolution7 = 0;
    private static String bitratetype7 = "VBR";
    private static byte videoquality7 = 0;
    private static int frameInterval7 = 50;
    private static int framerate7 = 25;  //maxbitrate
    private static int bitrate7 = 256;
    private static int maxbitrate7 = 256;  //videoencode
    private static String videoencode7 = "H.264";
    private static String substreamtype7 = "Sub Stream"; //videomode
    private static String subvideomode7 = "audiomode";
    private static byte subresolution7 = 3;
    private static String subbitratetype7 = "VBR";
    private static byte subvideoquality7 = 2;
    private static int subframerate7 = 20;  //maxbitrate
    private static int subFrameInterval7 = 50;
    private static int submaxbitrate7 = 256;  //videoencode
    private static int subbitrate7 = 256;
    private static String subvideoencode7 = "MPEG";

    private static String camera8 = "Cam8";
    private static String streamtype8 = "Main Stream"; //videomode
    private static String videomode8 = "videomode";
    private static byte resolution8 = 0;
    private static String bitratetype8 = "VBR";
    private static byte videoquality8 = 0;
    private static int frameInterval8 = 50;
    private static int framerate8 = 25;  //maxbitrate
    private static int bitrate8 = 256;
    private static int maxbitrate8 = 256;  //videoencode
    private static String videoencode8 = "H.264";
    private static String substreamtype8 = "Sub Stream"; //videomode
    private static String subvideomode8 = "audiomode";
    private static byte subresolution8 = 3;
    private static String subbitratetype8 = "VBR";
    private static byte subvideoquality8 = 2;
    private static int subframerate8 = 20;  //maxbitrate
    private static int subFrameInterval8 = 50;
    private static int subbitrate8 = 256;
    private static int submaxbitrate8 = 256;  //videoencode
    private static String subvideoencode8 = "MPEG";

    private static boolean snapshot_enable_cam1 = false;
    private static boolean snapshot_enable_cam2 = false;
    private static boolean snapshot_enable_cam3 = false;
    private static boolean snapshot_enable_cam4 = false;
    private static boolean snapshot_enable_cam5 = false;
    private static boolean snapshot_enable_cam6 = false;
    private static boolean snapshot_enable_cam7 = false;
    private static boolean snapshot_enable_cam8 = false;

    private static boolean snap_shot_event_enable_cam1 = false;
    private static boolean snap_shot_cont_enable_cam1 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam1 = false;

    private static boolean snap_shot_dig1_enable_cam1 = false;
    private static boolean snap_shot_dig2_enable_cam1 = false;
    private static boolean snap_shot_dig3_enable_cam1 = false;
    private static boolean snap_shot_dig4_enable_cam1 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam1 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam1 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam1 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam1 = false;

    private static boolean event_based_rec_enable_cam1 = false;

    private static int snap_shot_cont_interval_cam1 = 15;
    private static int snap_shot_cont_stream_cam1 = VIDEO_MAIN_STREAM_TYPE;

    private static int snap_shot_dig1_stream_cam1 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam1 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam1 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam1 = VIDEO_SUB_STREAM_TYPE;

    private static int snap_shot_dig1_interval_cam1 = 5;
    private static int snap_shot_dig2_interval_cam1 = 5;
    private static int snap_shot_dig3_interval_cam1 = 5;
    private static int snap_shot_dig4_interval_cam1 = 5;

    //  private static boolean start_people_enable = false;
    //  private static boolean start_people_enable1 = false;
    private static boolean snap_shot_event_enable_cam2 = false;
    private static boolean snap_shot_cont_enable_cam2 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam2 = false;
    private static boolean snap_shot_dig1_enable_cam2 = false;
    private static boolean snap_shot_dig2_enable_cam2 = false;
    private static boolean snap_shot_dig3_enable_cam2 = false;
    private static boolean snap_shot_dig4_enable_cam2 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam2 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam2 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam2 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam2 = false;

    private static int snap_shot_dig1_stream_cam2 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam2 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam2 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam2 = VIDEO_SUB_STREAM_TYPE;

    private static boolean event_based_rec_enable_cam2 = false;

    private static int snap_shot_cont_stream_cam2 = VIDEO_MAIN_STREAM_TYPE;
    private static int snap_shot_cont_interval_cam2 = 15;

    private static int snap_shot_dig1_interval_cam2 = 5;
    private static int snap_shot_dig2_interval_cam2 = 5;
    private static int snap_shot_dig3_interval_cam2 = 5;
    private static int snap_shot_dig4_interval_cam2 = 5;

    private static boolean snap_shot_event_enable_cam3 = false;
    private static boolean snap_shot_cont_enable_cam3 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam3 = false;
    private static boolean snap_shot_dig1_enable_cam3 = false;
    private static boolean snap_shot_dig2_enable_cam3 = false;
    private static boolean snap_shot_dig3_enable_cam3 = false;
    private static boolean snap_shot_dig4_enable_cam3 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam3 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam3 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam3 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam3 = false;

    private static int snap_shot_cont_stream_cam3 = VIDEO_MAIN_STREAM_TYPE;
    private static int snap_shot_cont_interval_cam3 = 15;
    private static int snap_shot_dig1_stream_cam3 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam3 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam3 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam3 = VIDEO_SUB_STREAM_TYPE;
    private static boolean event_based_rec_enable_cam3 = false;
    private static int snap_shot_dig1_interval_cam3 = 5;
    private static int snap_shot_dig2_interval_cam3 = 5;
    private static int snap_shot_dig3_interval_cam3 = 5;
    private static int snap_shot_dig4_interval_cam3 = 5;

    private static boolean snap_shot_event_enable_cam4 = false;
    private static int snap_shot_cont_stream_cam4 = VIDEO_MAIN_STREAM_TYPE;
    private static boolean snap_shot_cont_enable_cam4 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam4 = false;
    private static boolean snap_shot_dig1_enable_cam4 = false;
    private static boolean snap_shot_dig2_enable_cam4 = false;
    private static boolean snap_shot_dig3_enable_cam4 = false;
    private static boolean snap_shot_dig4_enable_cam4 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam4 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam4 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam4 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam4 = false;

    private static int snap_shot_dig1_stream_cam4 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam4 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam4 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam4 = VIDEO_SUB_STREAM_TYPE;
    private static boolean event_based_rec_enable_cam4 = false;
    private static int snap_shot_cont_interval_cam4 = 15;
    private static int snap_shot_dig1_interval_cam4 = 5;
    private static int snap_shot_dig2_interval_cam4 = 5;
    private static int snap_shot_dig3_interval_cam4 = 5;
    private static int snap_shot_dig4_interval_cam4 = 5;

    private static boolean snap_shot_event_enable_cam5 = false;
    private static int snap_shot_cont_stream_cam5 = VIDEO_MAIN_STREAM_TYPE;
    private static boolean snap_shot_cont_enable_cam5 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam5 = false;
    private static boolean snap_shot_dig1_enable_cam5 = false;
    private static boolean snap_shot_dig2_enable_cam5 = false;
    private static boolean snap_shot_dig3_enable_cam5 = false;
    private static boolean snap_shot_dig4_enable_cam5 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam5 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam5 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam5 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam5 = false;
    private static int snap_shot_dig1_stream_cam5 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam5 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam5 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam5 = VIDEO_SUB_STREAM_TYPE;
    private static boolean event_based_rec_enable_cam5 = false;
    private static int snap_shot_cont_interval_cam5 = 15;
    private static int snap_shot_dig1_interval_cam5 = 5;
    private static int snap_shot_dig2_interval_cam5 = 5;
    private static int snap_shot_dig3_interval_cam5 = 5;
    private static int snap_shot_dig4_interval_cam5 = 5;

    private static boolean snap_shot_event_enable_cam6 = false;
    private static int snap_shot_cont_stream_cam6 = VIDEO_MAIN_STREAM_TYPE;
    private static boolean snap_shot_cont_enable_cam6 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam6 = false;
    private static boolean snap_shot_dig1_enable_cam6 = false;
    private static boolean snap_shot_dig2_enable_cam6 = false;
    private static boolean snap_shot_dig3_enable_cam6 = false;
    private static boolean snap_shot_dig4_enable_cam6 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam6 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam6 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam6 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam6 = false;

    private static int snap_shot_dig1_stream_cam6 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam6 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam6 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam6 = VIDEO_SUB_STREAM_TYPE;
    private static boolean event_based_rec_enable_cam6 = false;
    private static int snap_shot_cont_interval_cam6 = 15;
    private static int snap_shot_dig1_interval_cam6 = 5;
    private static int snap_shot_dig2_interval_cam6 = 5;
    private static int snap_shot_dig3_interval_cam6 = 5;
    private static int snap_shot_dig4_interval_cam6 = 5;

    private static boolean snap_shot_event_enable_cam7 = false;
    private static int snap_shot_cont_stream_cam7 = VIDEO_MAIN_STREAM_TYPE;
    private static boolean snap_shot_cont_enable_cam7 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam7 = false;
    private static boolean snap_shot_dig1_enable_cam7 = false;
    private static boolean snap_shot_dig2_enable_cam7 = false;
    private static boolean snap_shot_dig3_enable_cam7 = false;
    private static boolean snap_shot_dig4_enable_cam7 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam7 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam7 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam7 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam7 = false;

    private static int snap_shot_dig1_stream_cam7 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam7 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam7 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam7 = VIDEO_SUB_STREAM_TYPE;
    private static boolean event_based_rec_enable_cam7 = false;
    private static int snap_shot_cont_interval_cam7 = 15;
    private static int snap_shot_dig1_interval_cam7 = 5;
    private static int snap_shot_dig2_interval_cam7 = 5;
    private static int snap_shot_dig3_interval_cam7 = 5;
    private static int snap_shot_dig4_interval_cam7 = 5;

    private static boolean snap_shot_event_enable_cam8 = false;
    private static int snap_shot_cont_stream_cam8 = VIDEO_MAIN_STREAM_TYPE;
    private static boolean snap_shot_cont_enable_cam8 = false;
    private static boolean snap_shot_cont_ftpupload_enable_cam8 = false;
    private static boolean snap_shot_dig1_enable_cam8 = false;
    private static boolean snap_shot_dig2_enable_cam8 = false;
    private static boolean snap_shot_dig3_enable_cam8 = false;
    private static boolean snap_shot_dig4_enable_cam8 = false;
    private static boolean snap_shot_dig1_ftpupload_enable_cam8 = false;
    private static boolean snap_shot_dig2_ftpupload_enable_cam8 = false;
    private static boolean snap_shot_dig3_ftpupload_enable_cam8 = false;
    private static boolean snap_shot_dig4_ftpupload_enable_cam8 = false;

    private static int snap_shot_dig1_stream_cam8 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig2_stream_cam8 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig3_stream_cam8 = VIDEO_SUB_STREAM_TYPE;
    private static int snap_shot_dig4_stream_cam8 = VIDEO_SUB_STREAM_TYPE;
    private static boolean event_based_rec_enable_cam8 = false;
    private static int snap_shot_cont_interval_cam8 = 15;
    private static int snap_shot_dig1_interval_cam8 = 5;
    private static int snap_shot_dig2_interval_cam8 = 5;
    private static int snap_shot_dig3_interval_cam8 = 5;
    private static int snap_shot_dig4_interval_cam8 = 5;

    //   private static byte snap_shot_stream_type_cam1 = clsDefines.VIDEO_MAIN_STREAM_TYPE;
    //   private static byte snap_shot_stream_type_cam2 = clsDefines.VIDEO_MAIN_STREAM_TYPE;
    //  private static byte snap_shot_stream_type_cam3 = clsDefines.VIDEO_MAIN_STREAM_TYPE;
    //  private static byte snap_shot_stream_type_cam4 = clsDefines.VIDEO_MAIN_STREAM_TYPE;
    private static byte eventbased_stream_type_cam1 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam2 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam3 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam4 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam5 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam6 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam7 = clsDefines.VIDEO_SUB_STREAM_TYPE;
    private static byte eventbased_stream_type_cam8 = clsDefines.VIDEO_SUB_STREAM_TYPE;

    private static int eventbased_interval_cam1 = 5;
    private static int eventbased_interval_cam2 = 5;
    private static int eventbased_interval_cam3 = 5;
    private static int eventbased_interval_cam4 = 5;
    private static int eventbased_interval_cam5 = 5;
    private static int eventbased_interval_cam6 = 5;
    private static int eventbased_interval_cam7 = 5;
    private static int eventbased_interval_cam8 = 5;

    private static boolean canport_detected = false;
    private static boolean canport_detected1 = false;
    private static boolean rs232port_detected = false;
    private static boolean harddrive_detected = false;
    private static boolean restart_ports_detected = false;

    static boolean snap_dig1_state = false;
    static boolean snap_dig2_state = false;
    static boolean snap_dig3_state = false;
    static boolean snap_dig4_state = false;
    static boolean sos_started = false;

    private static String cameraname1 = "CAM1";
    private static String cameraname2 = "CAM2";
    private static String cameraname3 = "CAM3";
    private static String cameraname4 = "CAM4";
    private static String cameraname5 = "CAM5";
    private static String cameraname6 = "CAM6";
    private static String cameraname7 = "CAM7";
    private static String cameraname8 = "CAM8";

    static boolean chk_cam1 = true;
    static boolean chk_cam2 = true;
    static boolean chk_cam3 = true;
    static boolean chk_cam4 = true;
    static boolean chk_cam5 = true;
    static boolean chk_cam6 = true;
    static boolean chk_cam7 = true;
    static boolean chk_cam8 = true;  //Chk_spd2

    static boolean chk_spd1 = false;
    static boolean chk_spd2 = false;
    static boolean chk_spd3 = false;
    static boolean chk_spd4 = false;
    static boolean chk_spd5 = false;
    static boolean chk_spd6 = false;
    static boolean chk_spd7 = false;
    static boolean chk_spd8 = false;

    static boolean chk_latlang1 = false;
    static boolean chk_latlang2 = false;
    static boolean chk_latlang3 = false;
    static boolean chk_latlang4 = false;
    static boolean chk_latlang5 = false;
    static boolean chk_latlang6 = false;
    static boolean chk_latlang7 = false;
    static boolean chk_latlang8 = false;

    static boolean chk_vehreg1 = false;
    static boolean chk_vehreg2 = false;
    static boolean chk_vehreg3 = false;
    static boolean chk_vehreg4 = false;
    static boolean chk_vehreg5 = false;
    static boolean chk_vehreg6 = false;
    static boolean chk_vehreg7 = false;
    static boolean chk_vehreg8 = false;
    //  static boolean sos_started = false;

    //log files enable
    static boolean log_files_required = false;

    private static boolean video_dig1_ftpupload_enable = false;
    private static boolean video_dig2_ftpupload_enable = false;
    private static boolean video_dig3_ftpupload_enable = false;
    private static boolean video_dig4_ftpupload_enable = false;

    public static class clsPhoneNum {

        public String[] phone_num = new String[clsDefines.MAX_NO_CONTACTS];
        public String[] phone_name = new String[clsDefines.MAX_NO_CONTACTS];
    }
    public static clsPhoneNum objPhoneNoList = new clsPhoneNum();
    public static byte no_phone_nos = 0;
    public static String configuration_pwd = "3339";
    private static boolean nmea_came = true;
    private static boolean at_con_came = true;

    public static boolean nmea_gprmc = true;
    public static boolean nmea_gpgga = false;
    public static boolean nmea_gpvtg = false;
    public static boolean nmea_gpgsa = false;
    public static boolean nmea_gpgsv = false;

    public static boolean gps_enabled = true;
    public static boolean irnss_enabled = true;
    public static boolean glonass_enabled = false;

    private static byte gps_state_img = 0;
    private static byte gsm_clk_state_img = 0;
    private static byte server_connect_img = SERVER_DISCONNECTED;
    private static byte server_connect_img2 = SERVER_DISCONNECTED;
    private static byte server_connect_img3 = SERVER_DISCONNECTED;
    private static byte server_connect_img4 = SERVER_DISCONNECTED;
    private static byte server_connect_img5 = SERVER_DISCONNECTED;
    private static String time_display_img = "";
    public static boolean new_apk_loading = false;

    public static long signal_date = 0;
    private static byte reset_obu_val = 0;
    private static int ping_config_time = 50;
    private static byte call_type_in_out = NO_CALL;//OUT_CALL;
    public static String call_in_mobile_no = "";
    public static String call_in_mobile_name = "";

    public static boolean voice_call_enabled = false;
    public static boolean speaker_enabled = false;

    public static boolean vocie_call_started = false;

    private static String driver_name = "";
    private static String driver_id = "3339";
    private static String duty_id = "1234";
    private static String previous_driver_duty_id = "1234";
    private static String previous_con_duty_id = "1234";

    private static String usb_check = "";
    private static String conductor_name = "";
    private static String conductor_id = "";
    private static double odometer_reading = 0.0;
    private static boolean gsm_status_connected = false;

    private static String IMEINumber = "";
    private static String qccid = "";
    private static String own_mobile_no = "";

    private static boolean ignition_on_status = true;
    private static byte tamper_state = clsDefines.HIGH;

    public static String serial_no = "SEPL-1234";
    public static String test_date_time_pid = null;
    public static String hw_revision = "1.0";
    public static String boot_ldr_version = "1.0";
    public static String font_lib_version = "1.0";
    public static String cpu_part_no = "ARMV7 Processor rev 3(v71)";
    public static String cpu_qualification = "BCM2835";
    public static String cpu_temp_range = "-25°C to 85°C";
    public static String compilation_fw_datetime = null;
    public static String os_version = "";

    public static byte int_disbrd_time_enabled = TIME_ENABLED;
    public static byte vehicle_name = clsDefines.JBM_BODYCOMPUTER;
    static String wifi_ssid = "";
    static String wifi_password = "";

    public static boolean wifi_connect = false;
    public static boolean reverse_camera_turnedon = false;
    public static boolean door_camera_turnedon = false;
    // public static boolean reverse_camera_turnedon1 = false;

    public static byte[] int_disbrd_msg_buf = new byte[1];
    public static int int_disbrd_msg_buf_len = 0;
    public static boolean int_disbrd_msg_came = false;
    public static boolean stop_req_message_came = false;
    // public static boolean stop_req_message_came1 = false;
    public static boolean stop_req_message_others_came = false;
    public static boolean can_enabled = true;
    public static boolean can_enabled1 = false;
    public static boolean spl_audio_enabled = false;
    public static boolean spl_audio_anounc_enable = false;
    public static boolean audio_enabled1 = false;
    public static boolean audio_enabled2 = false;
    public static boolean audio_enabled3 = false;
    public static boolean audio_enabled4 = false;
    public static boolean audio_enabled5 = false;
    private static int spl_Audio_Interval = 5;
    public static boolean can_data_enabled = false;
    public static boolean can_log_enabled = false;
    private static short reverse_cam_val1 = 124;
    private static short reverse_cam_val2 = 124;
    private static short reverse_cam_val = 124;

    private static int reverse_cam_pgn = JBM_D_PGN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_pgn1 = JBM_D_PGN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_pgn2 = JBM_D_PGN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_spn = JBM_D_SPN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_spn1 = JBM_D_SPN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_spn2 = JBM_D_SPN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_pgn_can1 = JBM_D_PGN_TRAN_CURRENT_GEAR;
    private static int reverse_cam_spn_can1 = JBM_D_SPN_TRAN_CURRENT_GEAR;
    private static short reverse_cam_val_can1 = 124;

    private static short stop_req_val = 1;
    private static int stop_req_pgn = JBM_D_PGN_EMERGENCY_STOP_STATUS;
    private static int stop_req_spn = JBM_D_SPN_EMERGENCY_STOP_STATUS;

    private static short door1_open_val = 1;
    private static short door1_close_val = 0;
    private static int door1_pgn = JBM_D_PGN_DOOR1_OPEN_STATUS;
    private static int door1_spn = JBM_D_SPN_DOOR1_OPEN_STATUS;

    private static short door2_open_val = 1;
    private static short door2_close_val = 0;
    private static int door2_pgn = JBM_D_PGN_DOOR2_OPEN_STATUS;
    private static int door2_spn = JBM_D_SPN_DOOR2_OPEN_STATUS;

    private static byte can_vehicle_speed = 1;
    private static byte can_vehicle_speed1 = 1;
    private static int can_speed_pgn = 65215;
    private static int can_speed_spn = 1;

    private static double an_bat_voltage = 0.0;
    private static double an_mains_voltage = 0.0;
    private static double an_temp = 0.0;
    private static double an_bat_voltage1 = 0.0;
    private static double an_mains_voltage1 = 0.0;
    private static double an_temp1 = 0.0;
    private static double an_adc0 = 0.0;
    private static double an_adc1 = 0.0;
    private static double an_adc2 = 0.0;
    private static double an_adc3 = 0.0;
    private static double an_adc0_can1 = 0.0;
    private static double an_adc1_can1 = 0.0;
    private static double an_adc2_can1 = 0.0;
    private static double an_adc3_can1 = 0.0;
    private static byte di_tamper;
    private static byte di1_status = 0;
    private static byte di2_status = 0;
    private static byte di3_status = 0;
    private static byte di4_status = 0;
    private static byte do1_status = 0;
    private static byte do2_status = 0;
    private static int pkt_seq_no = 0;
    private static int pkt_seq_no3 = 0;
    private static int pkt_seq_no5 = 0;
    private static boolean preload_msg_data_sent = false;
    public static byte ign_status_pkt_send;

    private static boolean video1_started = false;
    private static boolean video2_started = false;
    private static boolean video3_started = false;
    private static boolean video4_started = false;
    private static boolean video5_started = false;
    private static boolean video6_started = false;
    private static boolean video7_started = false;
    private static boolean video8_started = false;
    private static boolean videoplay_started = false;
    private static boolean videoall_started = false;

    private static boolean afcs_enabled = false;

    private static boolean emegency_route_packet_came = false;
    private static boolean emergencyService_enable = false;
    private static boolean maps_enable = false;
    public static double batery_voltage_value = 0.0;

    private static double emergency_src_lat = 0.0;
    private static double emergency_src_long = 0.0;
    private static double emergency_des_lat = 0.0;
    private static double emergency_des_long = 0.0;
    private static int no_emergency_lat_long_points_from_gprs = 0;

    private static boolean check_net_balance = false;
    private static byte sim_register = SIM_NOT_CHECKED;

    private static byte net_balance_status_present = 0;
    private static String check_net_balance_command = "*121*114#"; // "*121*4*4#";
    private static String check_net_balance_delimitter = "available of";
    private static byte sim_type = clsDefines.SIM_AIRTEL;

    private static boolean dispbrd_diag_form = false;

    private static boolean gprs_route_start_pkt_came = false;
    private static boolean gprs_route_end_pkt_came = false;
    private static long gprs_route_end_date_time = 0;
    private static long gprs_route_start_date_time = 0;
    private static String gprs_route_id = "";

    private static boolean event_based_video_record = false;
    private static boolean can_based_video_record = false;

    private static boolean event_based_video_record_start = false;
    private static boolean event_based_video_record_start1 = false;
    private static boolean event_based_video_record_start2 = false;
    private static boolean event_based_video_record_start3 = false;
    private static boolean event_based_video_record_start4 = false;
    private static boolean event_based_video_record_start5 = false;
    private static boolean event_based_video_record_start6 = false;
    private static boolean event_based_video_record_start7 = false;
    private static boolean event_based_video_record_start8 = false;

    private static boolean event_based_video_record_end = false;

    private static boolean can_based_video_record_start = false;
    private static boolean can_based_video_record_start1 = false;
    private static boolean can_based_video_record_start2 = false;
    private static boolean can_based_video_record_start3 = false;
    private static boolean can_based_video_record_start4 = false;
    private static boolean can_based_video_record_start5 = false;
    private static boolean can_based_video_record_start6 = false;
    private static boolean can_based_video_record_start7 = false;
    private static boolean can_based_video_record_start8 = false;

    //smc surat packets
    private static boolean surat_protocol = false;
    private static byte ip5_protocol = clsDefines.AIS140;
    public static final String START_CHARACTER_SMC = "$$CLIENT_1NS";
    public static final int event_default_smc = 1;
    public static final int event_can_smc = 15;
    public static final int event_serial_smc = 20;
    public static final int event_serial_smc_history = 100;
    public static int route_start = 1;
    public static int version_pkt = 2;
    public static int status_bdc = 3;
    public static int dis_brd_status = 4;

    //  public static final String HW_FIRMWARE_VERSION = "1.0.1";
    public static final char START_CHARACTER = '$';
    public static final char END_CHARACTER = '#';
    public static final char LOC_END_CHARACTER = '#';
    public static final String START_HEADER = "EPB";
    public static final String MDVR_NAME = "Dik3339";
    public static final String HEADER_ALL_PKTS = "ITS";
    public static String vendor_id = "3339";

    public static final String MSG_SERVER_LOGIN_HEADER = "LGN";
    public static final String MSG_HEALTH_HEADER = "HLT";
    public static final String EMERGENCY_PACKET_HEADER = "EPB";
    public static final String EMERGENCY_MESSAGE_TYPE = "EMR";
    public static final String EMERGENCY_STOP_MESSAGE_TYPE = "SEM";

    public static String device_vehicle_no = "TS1234";
    public static String last_valid_location = "";

    public static String pdop = "0.0";
    public static String hdop = "0.0";
    public static String network_operator_name = " ";  //at+cops?
    public static boolean video_4_screens_enable = false;
    public static char emergency_alert_status = '0'; //1= On ; 0 = Off
    public static char tamper_alert_status = TAMPER_CLOSED; //C = Cover Closed, O = Cover Open

    public static byte celltower_type = CELL_LTE;  //

    public static String mcc = "0";  //
    public static String mnc = "0";  //
    public static String lac = "0";  //
    public static String cellId = "0";  //

    public static String nmr1CellId = "0";  //
    public static String nmr1Lac = "0";  //
    public static String nmr1SigStrength = "0";  //

    public static String nmr2CellId = "0";  //
    public static String nmr2Lac = "0";  //
    public static String nmr2SigStrength = "0";  //

    public static String nmr3CellId = "0";  //
    public static String nmr3Lac = "0";  //
    public static String nmr3SigStrength = "0";  //

    public static String nmr4CellId = "0";  //
    public static String nmr4Lac = "0";  //
    public static String nmr4SigStrength = "0";  //

    private static byte module_rev = 0;
    private static byte cam_status_live = 0;

    // "/dev/ttyUSB1"; //COM5"; ///dev/ttyUSB1"; //COM15";
    //  private static String AT_COMPORT = "/dev/ttyUSB2";//COM4";///dev/ttyUSB2";//COM14";
    // private static String GPRS_COMPORT = "/dev/ttyUSB3";//COM3";///dev/ttyUSB3";//COM12";
    //**************************************  set and get variables end **************************/
    public static class classGPSData {

        String stop_name; // english name
        String reg1_stop_name;
        String reg2_stop_name;
        double latitude;
        double longitude;
        double arr_dis; // arriving stop distance
        String arr_db_file; // display board file
        String arr_eng_file; // English wave file
        String arr_reg1_file; // REG1 wave file
        String arr_reg2_file; // REG2 wave file
        double cur_dis; // current stop distance
        String cur_db_file; // display board file
        String cur_eng_file; // English wave file
        String cur_reg1_file; // REG1 wave file
        String cur_reg2_file; // REG2 wave file
        double next_dis; // next stop distance
        String next_db_file; // next stop display board file name
        String next_eng_file; // English wave file
        String next_reg1_file; // REG1 wave file
        String next_reg2_file; // REG2 wave file
        double stop_stop_dis; // stop to stop distance
        boolean stop_identify_status; // status of the bus stop detected ot not.
    }
    public static classGPSData[] gps_data = new classGPSData[MAX_NO_STOPS];

    public static class RouteMasFiles {

        public String route_no;
        public String front_file_name;
        public String side_file_name;
        public String rear_file_name;
        public String bdc_file_name;
        public short no_slogans;
        public String[] slogan_files;
    }
    public static RouteMasFiles[] objRouteMasFiles;

    public static class RouteLocationAdsFiles {

        public String route_no;
        public String message_name;
        public byte audio_repeat;
        public int distance;
        public String file_name;
        public String audio_file_name;
        public double latitude;
        public double longitude;
        public boolean status;
    }
    public static RouteLocationAdsFiles[] objLocBasedAdsFiles;
    public static byte no_loc_based_ads = 0;
    public static byte current_loc_based_ad = 0;

    public static class ClassSchedule {

        String sch_id;
        Date sch_startdatetime;
        Date sch_enddatetime;
        long sch_starttime;
        long sch_endtime;
        int no_trips;
        String[] route_nos;
        byte[] trip_status;
        boolean schedule_available;
        String[] src_name;
        String[] des_name;
    }
    public static ClassSchedule perm_sch = new ClassSchedule();
    public static ClassSchedule temp_sch = new ClassSchedule();
    public static ClassSchedule cur_sch_route = new ClassSchedule();

    public static String route_src_des_display = "";

    public static boolean fm_radio_start = false;

    public static boolean embedded_sim_commands_start = false;
    public static String embedded_sim_commands_data = "";

    public static byte embedded_normal_sim_type = clsDefines.EMBEDDED_SIM;
    public static byte embedded_sim_autoswitch = clsDefines.SWITCH_MANUAL_MODE;
    public static byte embedded_sim_switch_mode = clsDefines.SWITCH_SECONDARY_MODE;
    public static byte embedded_sim_manual_switch_mode = clsDefines.SWITCH_SECONDARY_MODE;

    public static byte embedded_sim_command_start = 0;

    public static class S_claslatlong {

        double latitude;
        double longitude;
    }
    public static S_claslatlong[] objMapData = new S_claslatlong[clsDefines.MAX_NO_MAP_POINTS];
    public static int no_maps_latlong_points = 0;

    // simulation data
    public static String[] gps_simul_data;
    private static int gps_simul_data_cnt = 0;
    private static int simul_data_inc = 0;
    private static boolean SIMUL_ENABLED = false;
    private static boolean stops_simulation_enabled = false;
    // CAN simulation data
    public static String[] can_simul_data;
    private static int can_simul_data_cnt = 0;
    private static int can_simul_data_inc = 0;
    private static boolean CAN_SIMUL_ENABLED = false;

    // private static long gps_queue_data_cnt = 0;
    private static boolean special_msg_checked = false;
    private static byte special_msg_checked_no = 0;

    private static boolean sleep_mode_entered = false;
    private static boolean sleep_mode_refresh_send_data = false;

    private static int gps_pkt_came_in_gprs_inc = 0;
    private static int data_send_to_server_inc = 0;

    private static int gprs_module_enab_dis_inc = 0;

    private static int gsm_signal_strength = 0;

    private static boolean gprs_res_pkt_status = false;
    private static boolean gprs_res_pkt_came = false;
    private static byte gprs_res_pkt_msg_type = 0;

    private static boolean gprs_res_pkt_status2 = false;
    private static boolean gprs_res_pkt_came2 = false;
    private static byte gprs_res_pkt_msg_type2 = 0;

    private static boolean reboot_system_enabled = false;

    private static byte gps_nmea_up = 0;

    private static byte voice_call_volume = 8;
    private static byte speaker_volume = 85;
    private static byte ring_tone_volume = 85;

    private static String gps_comport_name = null; //"/dev/ttyUSB1";
    private static String at_comport_name = null;//"/dev/ttyUSB2";
    private static String ppp_comport_name = "/dev/ttyUSB3";
    private static boolean gps_comport_connected = false;
    private static boolean at_comport_connected = false;
    private static boolean ppp_comport_connected = false;
    private static boolean gsm_module_on = false;
    private static boolean gsm_module_power_on = false;

    private static boolean gps_nmea_pkt_came = false;
    private static boolean at_pkt_came = false;

    private static boolean start_gps_diag_data = false;
    private static boolean start_gps_nmea_data = false;
    private static String nmea_diag_data = "";

    private static boolean seven_ports_configured = false;
    private static boolean sim_ready = false;

    public static byte act_reset_type = NORMAL_RESET;
    public static byte reset_type = NORMAL_RESET;
    public static short no_times_reset = 0;

    private static boolean video1_connected = false;
    private static boolean video2_connected = false;
    private static boolean video3_connected = false;
    private static boolean video4_connected = false;
    private static boolean video5_connected = false;
    private static boolean video6_connected = false;
    private static boolean video7_connected = false;
    private static boolean video8_connected = false;

    private static boolean mains_status = false;
    private static boolean gsm_diag_status = false;
    private static boolean gps_diag_status = false;
    private static boolean can_diag_status = false;
    private static boolean can_raw_status = false;
    private static boolean can1_raw_status = false;
    private static boolean can_diag_status1 = false;
    private static boolean rs232tx_diag_status = false;
    private static boolean rs232rx_diag_status = false;
    private static boolean video_tamper_diag_status = false;

    private static boolean ethernettx_diag_status = false;
    private static boolean ethernetrx_diag_status = false;

    private static boolean cam_diag_status = false;
    private static byte cam_diag_status_camerano = 0;
    //all are these single byte information send to server
    public static short obu_watchdog_reset_cnt = 0;
    public static short obu_low_volt_reset_cnt = 0;
    public static short obu_high_volt_cnt = 0;
    public static short obu_low_volt_cnt = 0;
    public static short obu_over_heat_cnt = 0;
    public static short obu_high_volt_cnt1 = 0;
    public static short obu_low_volt_cnt1 = 0;
    public static short obu_over_heat_cnt1 = 0;
    public static short gps_lost_comm_cnt = 0;
    public static short gps_invalid_data_cnt = 0;
    public static short gps_antenna_error_cnt = 0;
    public static short usb_invalid_cnt = 0;
    public static short usb_unknown_cnt = 0;
    public static short usb_invalid_filesystem_cnt = 0;
    public static short usb_overcurrent_cnt = 0;

    private static byte last_ignition_state = IGNITION_READ_DEFAULT_STATE;
    private static byte last_mains_state = MAINS_READ_DEFAULT_STATE;

    private static long first_fix_sec = 0;

    private static boolean canVhmdDisplayScreenOn = false;
    private static boolean canVhmdElectricalDisplayScreenOn = false;
    private static boolean canVhmdSafetyDisplayScreenOn = false;
    private static boolean canVhmdTransmitDisplayScreenOn = false;
    private static boolean canVhmdEngineDisplayScreenOn = false;
    private static boolean canVhmdOthersDisplayScreenOn = false;
    //can2
    private static boolean canVhmdElectricalDisplayScreenOn1 = false;
    private static boolean canVhmdSafetyDisplayScreenOn1 = false;
    private static boolean canVhmdTransmitDisplayScreenOn1 = false;
    private static boolean canVhmdEngineDisplayScreenOn1 = false;
    private static boolean canVhmdOthersDisplayScreenOn1 = false;

    public static boolean cam1MirrorEnable = false;
    public static boolean cam2MirrorEnable = false;
    public static boolean cam3MirrorEnable = false;
    public static boolean cam4MirrorEnable = true;
    public static boolean cam5MirrorEnable = false;
    public static boolean cam6MirrorEnable = false;
    public static boolean cam7MirrorEnable = false;
    public static boolean cam8MirrorEnable = false;

    public static boolean camera1_type_rtsp = false;
    public static boolean camera2_type_rtsp = false;
    public static boolean camera3_type_rtsp = false;
    public static boolean camera4_type_rtsp = false;

    private static byte brightness_level = 8;
    private static byte saturation_level = 7;
    private static byte contrast_level = 7;

    private static byte brightness_level2 = 8;
    private static byte saturation_level2 = 7;
    private static byte contrast_level2 = 7;

    private static byte brightness_level3 = 8;
    private static byte saturation_level3 = 7;
    private static byte contrast_level3 = 7;

    private static byte brightness_level4 = 8;
    private static byte saturation_level4 = 7;
    private static byte contrast_level4 = 7;

    private static byte brightness_level5 = 8;
    private static byte saturation_level5 = 7;
    private static byte contrast_level5 = 7;

    private static byte brightness_level6 = 8;
    private static byte saturation_level6 = 7;
    private static byte contrast_level6 = 7;

    private static byte brightness_level7 = 8;
    private static byte saturation_level7 = 7;
    private static byte contrast_level7 = 7;

    private static byte brightness_level8 = 8;
    private static byte saturation_level8 = 7;
    private static byte contrast_level8 = 7;

    //rs232 details
    private static boolean rs232_enable = false;
    private static int rs232_baudrate = 9600;
    private static byte rs232_type = clsDefines.RS232_GPS;
    private static int rs232_mode_interval = 5;

    //Ethernet details
    private static boolean ethernet_enable = false;
    private static int ethernet_portno = 5557;
    private static String ethernet_ipaddr = "10.42.0.2";

    private static byte ethernet_type = clsDefines.RS232_GPS;
    private static int ethernet_mode_interval = 5;
    private static byte live_type = clsDefines.CAM_8;

    //digital output values
    private static byte dig1_out_value = 0;
    private static byte dig2_out_value = 0;
    private static byte dig3_out_value = 0;
    private static byte dig4_out_value = 0;
    private static boolean dig_out_pkt_came = false;
    private static boolean dig_out_pkt_came1 = false;

    private static byte usb_stat_enable_disable = 1;
    private static byte hd_stat_enable_disable = 1;
    private static boolean usb_hd_pkt_came = false;
    //  private static boolean usb_hd_pkt_came1 = false;
    private static boolean usb_hd_pkt_res_came = false;
    //  private static boolean usb_hd_pkt_res_came1 = false;

    private static boolean motion_enable_cam1 = false;
    private static boolean motion_enable_cam2 = false;
    private static boolean motion_enable_cam3 = false;
    private static boolean motion_enable_cam4 = false;
    private static boolean motion_enable_cam5 = false;
    private static boolean motion_enable_cam6 = false;
    private static boolean motion_enable_cam7 = false;
    private static boolean motion_enable_cam8 = false;

    private static int motion_x1_cam1 = 5;
    private static int motion_x2_cam1 = 5;
    private static int motion_y1_cam1 = 5;
    private static int motion_y2_cam1 = 5;
    private static int motion_sensitivity_cam1 = 60;
    private static int motion_threshold_cam1 = 10;
    private static int motion_recorded_time_cam1 = 1;

    private static int motion_x1_cam2 = 5;
    private static int motion_x2_cam2 = 5;
    private static int motion_y1_cam2 = 5;
    private static int motion_y2_cam2 = 5;
    private static int motion_sensitivity_cam2 = 60;
    private static int motion_threshold_cam2 = 10;
    private static int motion_recorded_time_cam2 = 1;

    private static int motion_x1_cam3 = 5;
    private static int motion_x2_cam3 = 5;
    private static int motion_y1_cam3 = 5;
    private static int motion_y2_cam3 = 5;
    private static int motion_sensitivity_cam3 = 60;
    private static int motion_threshold_cam3 = 10;
    private static int motion_recorded_time_cam3 = 1;

    private static int motion_x1_cam4 = 5;
    private static int motion_x2_cam4 = 5;
    private static int motion_y1_cam4 = 5;
    private static int motion_y2_cam4 = 5;
    private static int motion_sensitivity_cam4 = 60;
    private static int motion_threshold_cam4 = 10;
    private static int motion_recorded_time_cam4 = 1;

    private static int motion_x1_cam5 = 5;
    private static int motion_x2_cam5 = 5;
    private static int motion_y1_cam5 = 5;
    private static int motion_y2_cam5 = 5;
    private static int motion_sensitivity_cam5 = 60;
    private static int motion_threshold_cam5 = 10;
    private static int motion_recorded_time_cam5 = 1;

    private static int motion_x1_cam6 = 5;
    private static int motion_x2_cam6 = 5;
    private static int motion_y1_cam6 = 5;
    private static int motion_y2_cam6 = 5;
    private static int motion_sensitivity_cam6 = 60;
    private static int motion_threshold_cam6 = 10;
    private static int motion_recorded_time_cam6 = 1;

    private static int motion_x1_cam7 = 5;
    private static int motion_x2_cam7 = 5;
    private static int motion_y1_cam7 = 5;
    private static int motion_y2_cam7 = 5;
    private static int motion_sensitivity_cam7 = 60;
    private static int motion_threshold_cam7 = 10;
    private static int motion_recorded_time_cam7 = 1;

    private static int motion_x1_cam8 = 5;
    private static int motion_x2_cam8 = 5;
    private static int motion_y1_cam8 = 5;
    private static int motion_y2_cam8 = 5;
    private static int motion_sensitivity_cam8 = 60;
    private static int motion_threshold_cam8 = 10;
    private static int motion_recorded_time_cam8 = 1;

    // line dtection
    private static boolean linedet_enable_cam1 = false;
    private static boolean linedet_enable_cam2 = false;
    private static boolean linedet_enable_cam3 = false;
    private static boolean linedet_enable_cam4 = false;
    private static boolean linedet_enable_cam5 = false;
    private static boolean linedet_enable_cam6 = false;
    private static boolean linedet_enable_cam7 = false;
    private static boolean linedet_enable_cam8 = false;

    private static boolean linedet_detected_cam1 = false;
    private static boolean linedet_detected_cam2 = false;
    private static boolean linedet_detected_cam3 = false;
    private static boolean linedet_detected_cam4 = false;
    private static boolean linedet_detected_cam5 = false;
    private static boolean linedet_detected_cam6 = false;
    private static boolean linedet_detected_cam7 = false;
    private static boolean linedet_detected_cam8 = false;

    private static int linedet_displayalarm_time_cam1 = 10;
    private static int linedet_displayalarm_time_cam2 = 10;
    private static int linedet_displayalarm_time_cam3 = 10;
    private static int linedet_displayalarm_time_cam4 = 10;
    private static int linedet_displayalarm_time_cam5 = 10;
    private static int linedet_displayalarm_time_cam6 = 10;
    private static int linedet_displayalarm_time_cam7 = 10;
    private static int linedet_displayalarm_time_cam8 = 10;
    private static int linedet_speed_cam3 = 5;

    private static int linedet_x1_cam1 = 5;
    private static int linedet_x2_cam1 = 5;
    private static int linedet_y1_cam1 = 5;
    private static int linedet_y2_cam1 = 5;
    private static int linedet_sensitivity_cam1 = 60;
    private static int linedet_threshold_cam1 = 10;
    private static int linedet_recorded_time_cam1 = 1;

    private static int linedet_x1_cam2 = 5;
    private static int linedet_x2_cam2 = 5;
    private static int linedet_y1_cam2 = 5;
    private static int linedet_y2_cam2 = 5;
    private static int linedet_sensitivity_cam2 = 60;
    private static int linedet_threshold_cam2 = 10;
    private static int linedet_recorded_time_cam2 = 1;

    private static int linedet_x1_cam3 = 5;
    private static int linedet_x2_cam3 = 5;
    private static int linedet_y1_cam3 = 5;
    private static int linedet_y2_cam3 = 5;
    private static int linedet_sensitivity_cam3 = 60;
    private static int linedet_threshold_cam3 = 10;
    private static int linedet_recorded_time_cam3 = 1;

    private static int linedet_x1_cam4 = 5;
    private static int linedet_x2_cam4 = 5;
    private static int linedet_y1_cam4 = 5;
    private static int linedet_y2_cam4 = 5;
    private static int linedet_sensitivity_cam4 = 60;
    private static int linedet_threshold_cam4 = 10;
    private static int linedet_recorded_time_cam4 = 1;

    private static int linedet_x1_cam5 = 5;
    private static int linedet_x2_cam5 = 5;
    private static int linedet_y1_cam5 = 5;
    private static int linedet_y2_cam5 = 5;
    private static int linedet_sensitivity_cam5 = 60;
    private static int linedet_threshold_cam5 = 10;
    private static int linedet_recorded_time_cam5 = 1;

    private static int linedet_x1_cam6 = 5;
    private static int linedet_x2_cam6 = 5;
    private static int linedet_y1_cam6 = 5;
    private static int linedet_y2_cam6 = 5;
    private static int linedet_sensitivity_cam6 = 60;
    private static int linedet_threshold_cam6 = 10;
    private static int linedet_recorded_time_cam6 = 1;

    private static int linedet_x1_cam7 = 5;
    private static int linedet_x2_cam7 = 5;
    private static int linedet_y1_cam7 = 5;
    private static int linedet_y2_cam7 = 5;
    private static int linedet_sensitivity_cam7 = 60;
    private static int linedet_threshold_cam7 = 10;
    private static int linedet_recorded_time_cam7 = 1;

    private static int linedet_x1_cam8 = 5;
    private static int linedet_x2_cam8 = 5;
    private static int linedet_y1_cam8 = 5;
    private static int linedet_y2_cam8 = 5;
    private static int linedet_sensitivity_cam8 = 60;
    private static int linedet_threshold_cam8 = 10;
    private static int linedet_recorded_time_cam8 = 1;

    //apc second camera
    private static int apc_hour_people_in1 = 0;
    private static int apc_hour_people_out1 = 0;
    private static long apc_hour_date_time1 = 0;
    private static byte apc_hour_hour1 = 0;
    private static int apc_day_people_in1 = 0;
    private static int apc_day_people_out1 = 0;
    private static long apc_day_date_time1 = 0;
    private static byte apc_day_day1 = 0;
    private static int apc_people_in1 = 0;
    private static int apc_people_out1 = 0;
    private static int apc_people_count_in = 0;
    private static int apc_people_count_out = 0;

    // private static boolean fm_radio_start = false;
    private static int apc_people_in = 0;
    private static int apc_people_out = 0;
    private static boolean apc_people_status_change = false;
    private static int sms_seq_no = 0;
    private static byte can_bustype = clsDefines.OLECTRA_BYD;

    public static class clsDriverDetails {

        String driver_id;
        String driver_name;
        String pwd;
        String rfid;
    }

    public static class clsConductorDetails {

        String conductor_id;
        String conductor_name;
        String conductor_rfid;
        //  String pwd;
    }
    // public static   clsDriverDetails[] objDriverDetails   ;
    public static clsDriverDetails[] objDriverDetails = new clsDriverDetails[clsDefines.MAX_NO_DRIVER_IDS];

    public static clsConductorDetails[] objConductorDetails = new clsConductorDetails[clsDefines.MAX_NO_CONDUCTOR_IDS];

    public static int driverDetailsCnt = 0;
    public static int conductorDetailsCnt = 0;

    private static String cam1_quality_control_arr = "";
    private static String cam1_constant_bitrate_min_max_arr = "";
    private static String cam1_fixed_quality_arr = "";
    private static String cam1_max_framerate_arr = "";
    private static String cam1_width_arr = "";
    private static String cam1_height_arr = "";
    private static String cam1_frame_interval_arr = "";

    private static String cam2_quality_control_arr = "";
    private static String cam2_constant_bitrate_min_max_arr = "";
    private static String cam2_fixed_quality_arr = "";
    private static String cam2_max_framerate_arr = "";
    private static String cam2_width_arr = "";
    private static String cam2_height_arr = "";
    private static String cam2_frame_interval_arr = "";

    private static String cam3_quality_control_arr = "";
    private static String cam3_constant_bitrate_min_max_arr = "";
    private static String cam3_fixed_quality_arr = "";
    private static String cam3_max_framerate_arr = "";
    private static String cam3_width_arr = "";
    private static String cam3_height_arr = "";
    private static String cam3_frame_interval_arr = "";

    private static String cam4_quality_control_arr = "";
    private static String cam4_constant_bitrate_min_max_arr = "";
    private static String cam4_fixed_quality_arr = "";
    private static String cam4_max_framerate_arr = "";
    private static String cam4_width_arr = "";
    private static String cam4_height_arr = "";
    private static String cam4_frame_interval_arr = "";

    private static String cam5_quality_control_arr;
    private static String cam5_constant_bitrate_min_max_arr;
    private static String cam5_fixed_quality_arr;
    private static String cam5_max_framerate_arr;
    private static String cam5_width_arr;
    private static String cam5_height_arr;
    private static String cam5_frame_interval_arr;

    private static String cam6_quality_control_arr;
    private static String cam6_constant_bitrate_min_max_arr;
    private static String cam6_fixed_quality_arr;
    private static String cam6_max_framerate_arr;
    private static String cam6_width_arr;
    private static String cam6_height_arr;
    private static String cam6_frame_interval_arr;

    private static String cam7_quality_control_arr;
    private static String cam7_constant_bitrate_min_max_arr;
    private static String cam7_fixed_quality_arr;
    private static String cam7_max_framerate_arr;
    private static String cam7_width_arr;
    private static String cam7_height_arr;
    private static String cam7_frame_interval_arr;

    private static String cam8_quality_control_arr;
    private static String cam8_constant_bitrate_min_max_arr;
    private static String cam8_fixed_quality_arr;
    private static String cam8_max_framerate_arr;
    private static String cam8_width_arr;
    private static String cam8_height_arr;
    private static String cam8_frame_interval_arr;

    private static String cam1_quality_control_arr_sub = "";
    private static String cam1_constant_bitrate_min_max_arr_sub = "";
    private static String cam1_fixed_quality_arr_sub = "";
    private static String cam1_max_framerate_arr_sub = "";
    private static String cam1_width_arr_sub = "";
    private static String cam1_height_arr_sub = "";
    private static String cam1_frame_interval_arr_sub = "";

    private static String cam2_quality_control_arr_sub = "";
    private static String cam2_constant_bitrate_min_max_arr_sub = "";
    private static String cam2_fixed_quality_arr_sub = "";
    private static String cam2_max_framerate_arr_sub = "";
    private static String cam2_width_arr_sub = "";
    private static String cam2_height_arr_sub = "";
    private static String cam2_frame_interval_arr_sub = "";

    private static String cam3_quality_control_arr_sub = "";
    private static String cam3_constant_bitrate_min_max_arr_sub = "";
    private static String cam3_fixed_quality_arr_sub = "";
    private static String cam3_max_framerate_arr_sub = "";
    private static String cam3_width_arr_sub = "";
    private static String cam3_height_arr_sub = "";
    private static String cam3_frame_interval_arr_sub = "";

    private static String cam4_quality_control_arr_sub = "";
    private static String cam4_constant_bitrate_min_max_arr_sub = "";
    private static String cam4_fixed_quality_arr_sub = "";
    private static String cam4_max_framerate_arr_sub = "";
    private static String cam4_width_arr_sub = "";
    private static String cam4_height_arr_sub = "";
    private static String cam4_frame_interval_arr_sub = "";

    private static String cam5_quality_control_arr_sub;
    private static String cam5_constant_bitrate_min_max_arr_sub;
    private static String cam5_fixed_quality_arr_sub;
    private static String cam5_max_framerate_arr_sub;
    private static String cam5_width_arr_sub;
    private static String cam5_height_arr_sub;
    private static String cam5_frame_interval_arr_sub;

    private static String cam6_quality_control_arr_sub;
    private static String cam6_constant_bitrate_min_max_arr_sub;
    private static String cam6_fixed_quality_arr_sub;
    private static String cam6_max_framerate_arr_sub;
    private static String cam6_width_arr_sub;
    private static String cam6_height_arr_sub;
    private static String cam6_frame_interval_arr_sub;

    private static String cam7_quality_control_arr_sub;
    private static String cam7_constant_bitrate_min_max_arr_sub;
    private static String cam7_fixed_quality_arr_sub;
    private static String cam7_max_framerate_arr_sub;
    private static String cam7_width_arr_sub;
    private static String cam7_height_arr_sub;
    private static String cam7_frame_interval_arr_sub;

    private static String cam8_quality_control_arr_sub;
    private static String cam8_constant_bitrate_min_max_arr_sub;
    private static String cam8_fixed_quality_arr_sub;
    private static String cam8_max_framerate_arr_sub;
    private static String cam8_width_arr_sub;
    private static String cam8_height_arr_sub;
    private static String cam8_frame_interval_arr_sub;

    private static int snap_del_no_days = 3;
    private static byte cam1_status_live = 0;
    private static byte cam2_status_live = 0;
    private static byte cam3_status_live = 0;
    private static byte cam4_status_live = 0;
    private static byte cam5_status_live = 0;
    private static byte cam6_status_live = 0;
    private static byte cam7_status_live = 0;
    private static byte cam8_status_live = 0;
    //APC VARIABLES
    private static int apc_seq_no = 1;
    private static byte apc_status = 0;
    private static String apc_start_time = "";
    private static String apc_stop_time = "";
    private static String apc_routeno = "";
    private static String apc_stopname = "";
    private static boolean apc_enabled = false;
    private static boolean apc_enabled2 = false;
    private static boolean apc_link_status = false;
    private static String apc_version = "1.0";

    //***************************************start of set and get methods *******************************
    //simulation
    public synchronized static void setSimulationEnabled(boolean state) {
        SIMUL_ENABLED = state;
    }

    public synchronized static boolean getSimulationEnabled() {
        return SIMUL_ENABLED;
    }

    public synchronized static void setCam1_status_live(byte status) {
        cam1_status_live = status;
    }

    public synchronized static byte getCam1_status_live() {
        return cam1_status_live;
    }

    public synchronized static void setCam2_status_live(byte status) {
        cam2_status_live = status;
    }

    public synchronized static byte getCam2_status_live() {
        return cam2_status_live;
    }

    public synchronized static void setCam3_status_live(byte status) {
        cam3_status_live = status;
    }

    public synchronized static byte getCam3_status_live() {
        return cam3_status_live;
    }

    public synchronized static byte getCam4_status_live() {
        return cam4_status_live;
    }

    public synchronized static void setCam4_status_live(byte status) {
        cam4_status_live = status;
    }

    public synchronized static byte getCam5_status_live() {
        return cam5_status_live;
    }

    public synchronized static void setCam5_status_live(byte status) {
        cam5_status_live = status;
    }

    public synchronized static byte getCam6_status_live() {
        return cam6_status_live;
    }

    public synchronized static void setCam6_status_live(byte status) {
        cam6_status_live = status;
    }

    public synchronized static byte getCam7_status_live() {
        return cam7_status_live;
    }

    public synchronized static void setCam7_status_live(byte status) {
        cam7_status_live = status;
    }

    public synchronized static byte getCam8_status_live() {
        return cam8_status_live;
    }

    public synchronized static void setCam8_status_live(byte status) {
        cam8_status_live = status;
    }

    public synchronized static void setStopSimulationEnabled(boolean state) {
        stops_simulation_enabled = state;
    }

    public synchronized static boolean getStopSimulationEnabled() {
        return stops_simulation_enabled;
    }

    public synchronized static void setSimulationcnt(int val) {
        gps_simul_data_cnt = val;
    }

    public synchronized static int getSimulationcnt() {
        return gps_simul_data_cnt;
    }

    public synchronized static void setSimulationInc(int val) {
        simul_data_inc = val;
    }

    public synchronized static int getSimulationInc() {
        return simul_data_inc;
    }

    //***************************************start of set and get methods *******************************
    //CAN simulation
    public synchronized static void setCanSimulationEnabled(boolean state) {
        CAN_SIMUL_ENABLED = state;
    }

    public synchronized static boolean getCanSimulationEnabled() {
        return CAN_SIMUL_ENABLED;
    }

    public synchronized static void setCanSimulationcnt(int val) {
        can_simul_data_cnt = val;
    }

    public synchronized static int getCanSimulationcnt() {
        return can_simul_data_cnt;
    }

    public synchronized static void setCanSimulationInc(int val) {
        can_simul_data_inc = val;
    }

    public synchronized static int getCanSimulationInc() {
        return can_simul_data_inc;
    }

    //OBU ID set and get methods
    public synchronized static void setOBUID(String id) {
        OBUId = id;
    }

    public synchronized static String getOBUID() {
        return OBUId;
    }

    public synchronized static void setOBUName(String id) {
        OBU_name = id;
    }

    public synchronized static String getOBUName() {
        return OBU_name;
    }

    //Vechicle Reg no
    public synchronized static void setVechicleRegNo(String id) {
        vehicle_reg_no = id;
    }

    public synchronized static String getVechicleRegNo() {
        return vehicle_reg_no;
    }

    //IMEI NO set and get methods
    public synchronized static void setImeiNo(String id) {
        imei_number = id;

    }

    public synchronized static String getImeiNo() {
        return imei_number;
    }

    public synchronized static void setMacAddress(String id) {
        mac_address = id;

    }

    public synchronized static String getMacAddress() {
        return mac_address;
    }

    //current trip no set and get methods
    public synchronized static void setCurTripNo(short tripno) {
        cur_trip_no = tripno;
    }

    public synchronized static void incCurTripNo() {
        cur_trip_no++;
    }

    public synchronized static short getCurTripNo() {
        return cur_trip_no;
    }

    //current trip status set and get methods
    public synchronized static void setCurTripStat(byte state) {
        cur_trip_status = state;
    }

    public synchronized static byte getCurTripStat() {
        return cur_trip_status;
    }

    // auto trip status set and get methods
    public synchronized static void setAutoTripStat(boolean state) {
        auto_trip_status = state;
    }

    public synchronized static boolean getCurAutoTripStat() {
        return auto_trip_status;
    }

    //previous latitude  set and get methods
    public synchronized static void setPrevLat(double lat) {
        previous_latitude = lat;
    }

    public synchronized static double getPrevLat() {
        return previous_latitude;
    }

    //previous longitude  set and get methods
    public synchronized static void setPrevLong(double longi) {
        previous_longitude = longi;
    }

    public synchronized static double getPrevLong() {
        return previous_longitude;
    }

    //previous latitude  set and get methods
    public synchronized static void setOdometerPrevLat(double lat) {
        odometer_previous_latitude = lat;
    }

    public synchronized static double getOdometerPrevLat() {
        return odometer_previous_latitude;
    }

    //previous longitude  set and get methods
    public synchronized static void setOdometerPrevLong(double longi) {
        odometer_previous_longitude = longi;
    }

    public synchronized static double getOdometerPrevLong() {
        return odometer_previous_longitude;
    }

    //Next latitude  set and get methods
    public synchronized static Double getNxtLatitude() {
        return next_latitude;
    }

    public synchronized static void setNxtLatitude(Double lat) {
        next_latitude = lat;
    }

    //Next longitude  set and get methods
    public synchronized static Double getNxtLongitude() {
        return next_longitude;
    }

    public synchronized static void setNxtLongitude(Double longi) {
        next_longitude = longi;
    }

    //current latitude  set and get methods
    public synchronized static void setCurLatitude(Double lat) {
        cur_latitude = lat;
    }

    public synchronized static Double getCurLatitude() {
        return cur_latitude;
    }

    //current longitude  set and get methods
    public synchronized static void setCurLongitude(Double lat) {
        cur_longitude = lat;
    }

    public synchronized static Double getCurLongitude() {
        return cur_longitude;
    }

    public synchronized static void setCurLatitudeDir(char lat) {
        cur_latitude_dir = lat;
    }

    public synchronized static char getCurLatitudeDir() {
        return cur_latitude_dir;
    }

    //current longitude  set and get methods
    public synchronized static void setCurLongitudeDir(char lat) {
        cur_longitude_dir = lat;
    }

    public synchronized static char getCurLongitudeDir() {
        return cur_longitude_dir;
    }

    //float
    public synchronized static void setCurAltitude(String alti) {
        cur_altitude = alti;
    }

    public synchronized static String getCurAltitude() {
        return cur_altitude;
    }

    //Speed
    public synchronized static void setCurSpeed(Double speed) {
        cur_speed = speed;
    }

    public synchronized static Double getCurSpeed() {
        return cur_speed;
    }

    //disstance calculated from previous gps data
    public synchronized static void setDisCalcPrevGpsData(Double speed) {
        dis_prev_gps_data = speed;
    }

    public synchronized static Double getDisCalcPrevGpsData() {
        return dis_prev_gps_data;
    }

    //heading set and get methods
    public synchronized static float getCurHeading() {
        return heading;
    }

    public synchronized static void setCurHeading(float c_headi) {
        heading = c_headi;
    }

    public synchronized static byte getCurNoSatelites() {
        return no_satelites;
    }

    public synchronized static void setCurNoSatelites(byte sat) {
        no_satelites = sat;
    }

    //time set and get methods
    public synchronized static void setCurTime(String tim) {
        cur_time = tim;
    }

    public synchronized static String getCurTime() {
        return cur_time;
    }

    //date set and get methods
    public synchronized static void setCurDate(String date) {
        cur_date = date;
    }

    public synchronized static String getCurDate() {
        return cur_date;
    }

    //current trip status set and get methods
    public synchronized static void setCurRouteStat(boolean state) {
        cur_route_status = state;
    }

    public synchronized static boolean getCurRouteStat() {
        return cur_route_status;
    }

    //current Route No set and get methods
    public synchronized static void setCurRouteNo(String routeno) {
        cur_route_no = routeno;
    }

    public synchronized static String getCurRouteNo() {
        return cur_route_no;
    }

    // show Stop to stop distance  set and get methods
    public synchronized static void setShowStopStopDis(Double value) {
        show_stop_dis = value;
    }

    public synchronized static Double getShowStopStopDis() {
        return show_stop_dis;
    }

    // travelled_distance  set and get methods
    public synchronized static void setTravelledDis(Double value) {
        travelled_distance = value;
    }

    public synchronized static Double getTravelledDis() {
        return travelled_distance;
    }

    // GPS Speed  set and get methods
    public synchronized static void setGpsSpeed(short dis) {
        gps_speed = dis;
    }

    public synchronized static short getGpsSpeed() {
        return gps_speed;
    }

    // cur_sec set and get methods
    public synchronized static void setCurSec(long dis) {
        cur_sec = dis;
    }

    public synchronized static long getCurSec() {
        return cur_sec;
    }

    //  sch_req_send set and get methods
    public synchronized static void setSchReq(boolean state) {
        sch_req_send = state;
    }

    public synchronized static boolean getSchReq() {
        return sch_req_send;
    }

    //  server_config_changed set and get methods
    public synchronized static void setServerConfigStatus(boolean state) {
        server_config_changed = state;
    }

    public synchronized static boolean getServerConfigStatus() {
        return server_config_changed;
    }

    public synchronized static void setServer2ConfigStatus(boolean state) {
        server2_config_changed = state;
    }

    public synchronized static boolean getServer2ConfigStatus() {
        return server2_config_changed;
    }

    public synchronized static void setServer3ConfigStatus(boolean state) {
        server3_config_changed = state;
    }

    public synchronized static boolean getServer3ConfigStatus() {
        return server3_config_changed;
    }

    public synchronized static void setServer4ConfigStatus(boolean state) {
        server4_config_changed = state;
    }

    public synchronized static boolean getServer4ConfigStatus() {
        return server4_config_changed;
    }

    public synchronized static void setServer5ConfigStatus(boolean state) {
        server5_config_changed = state;
    }

    public synchronized static boolean getServer5ConfigStatus() {
        return server5_config_changed;
    }

    //mic_timer_value
    public synchronized static void setSleepModeState(byte state) {
        sleep_mode_state = state;
    }

    public synchronized static byte getSleepModeState() {
        return sleep_mode_state;
    }

    //  Front Board enable/disbale
    public synchronized static void setFrontEnable(boolean state) {
        front_disbrd_enable = state;
    }

    public synchronized static boolean getFrontEnable() {
        return front_disbrd_enable;
    }

    //  Side Board enable/disbale
    public synchronized static void setSideEnable(boolean state) {
        side_disbrd_enable = state;
    }

    public synchronized static boolean getSideEnable() {
        return side_disbrd_enable;
    }

    //  Front Board enable/disbale
    public synchronized static void setRearEnable(boolean state) {
        rear_disbrd_enable = state;
    }

    public synchronized static boolean getRearEnable() {
        return rear_disbrd_enable;
    }

    //  Front Board enable/disbale
    public synchronized static void setIntEnable(boolean state) {
        int_disbrd_enable = state;
    }

    public synchronized static boolean getIntEnable() {
        return int_disbrd_enable;
    }

    public synchronized static void setArtSideEnable(boolean state) {
        side_art_disbrd_enable = state;
    }

    public synchronized static boolean getArtSideEnable() {
        return side_art_disbrd_enable;
    }

    public synchronized static void setArtIntEnable(boolean state) {
        int_art_disbrd_enable = state;
    }

    public synchronized static boolean getArtIntEnable() {
        return int_art_disbrd_enable;
    }

    public synchronized static void setPidsDbEnable(boolean state) {
        send_pids_db_enable = state;
    }

    public synchronized static boolean getPidsDbEnable() {
        return send_pids_db_enable;
    }

    public synchronized static void setDisplayDriverDb(boolean state) {
        send_driverinfo_db_enable = state;
    }

    public synchronized static boolean getDisplayDriverDb() {
        return send_driverinfo_db_enable;
    }

    //day_intensity
    public synchronized static void setDayInt(int val) {
        day_intensity = val;
    }

    public synchronized static int getDayInt() {
        return day_intensity;
    }

    //night intensity
    public synchronized static void setNightInt(int val) {
        night_intensity = val;
    }

    public synchronized static int getNightInt() {
        return night_intensity;
    }

    //mains_off_intensity
    public synchronized static void setMainoffInt(int val) {
        mains_off_intensity = val;
    }

    public synchronized static int getMainsoffInt() {
        return mains_off_intensity;
    }

    //lcd_onoff_state
    public synchronized static void setLcdOnOff(boolean state) {
        lcd_onoff_state = state;
    }

    public synchronized static boolean getLcdOnOff() {
        return lcd_onoff_state;
    }

    //no_of_stops_in_a_route
    public synchronized static void setNoStopsRoute(short val) {
        no_of_stops_in_a_route = val;
    }

    public synchronized static short getNoStopsRoute() {
        return no_of_stops_in_a_route;
    }

    //cur_stop_no
    public synchronized static void setCurStopNo(byte val) {
        cur_stop_no = val;
    }

    public synchronized static byte getCurStopNo() {
        return cur_stop_no;
    }

    //stop to stop distance
    public synchronized static double getStoptoStopDis() {
        return stop_stop_dis;
    }

    public synchronized static void setStoptoStopDis(double dis) {
        stop_stop_dis = dis;
    }

    //stop state
    public synchronized static byte getStopState() {
        return stop_state;
    }

    public synchronized static void setStopState(byte state) {
        stop_state = state;
    }

    //map stop state
    public synchronized static byte getMapStopState() {
        return map_stop_state;
    }

    public synchronized static void setMapStopState(byte state) {
        map_stop_state = state;
    }

    //harsh acceleration
    public synchronized static void setHarshAcc(boolean state) {
        harsh_acc_state = state;
    }

    public synchronized static boolean getHarshAcc() {
        return harsh_acc_state;
    }

    //harsh break
    public synchronized static void setHarshBrk(boolean state) {
        harsh_break_state = state;
    }

    public synchronized static boolean getHarshBrk() {
        return harsh_break_state;
    }

    //over speed
    public synchronized static void setOverSpeed(boolean state) {
        over_speed_state = state;
    }

    public synchronized static boolean getOverSpeed() {
        return over_speed_state;
    }

    //current_activity
    public synchronized static void setCurActivity(byte val) {
        current_activity = val;
    }

    public synchronized static byte getCurActivity() {
        return current_activity;
    }

    //current_activity Name
    /*
     public synchronized static void setCurActivityName(Activity val) {
     current_activity_name = val;
     }

     public synchronized static Activity getCurActivityName() {
     return current_activity_name;
     }
     */
//schedule_updated
    public synchronized static void setSchUpdated(boolean state) {
        schedule_updated = state;
    }

    public synchronized static boolean getSchUpdated() {
        return schedule_updated;
    }

    public synchronized static void setTripStatusUpdated(boolean state) {
        schedule_updated = state;
    }

    public synchronized static boolean getTripStatusUpdated() {
        return schedule_updated;
    }

    // no_of_routes
    public synchronized static void setNoRoutes(short val) {
        no_of_routes = val;
    }

    public synchronized static short getNoRoutes() {
        return no_of_routes;
    }

    // per_sch_status
    public synchronized static void setPermSchStatus(boolean state) {
        per_sch_status = state;
    }

    public synchronized static boolean getPermSchStatus() {
        return per_sch_status;
    }

    // Mic status
    public synchronized static boolean getMicStatus() {
        return mic_pressed;
    }

    public synchronized static void setMicStatus(boolean state) {
        mic_pressed = state;
    }

    //audio play
    public synchronized static boolean getAudioStatus() {
        return audio_play_on;
    }

    public synchronized static void setAudioStatus(boolean state) {
        audio_play_on = state;
    }

    //g_dis_brd_name
    public synchronized static void setDisBrdName(byte val) {
        g_dis_brd_name = val;
    }

    public synchronized static byte getDisBrdName() {
        return g_dis_brd_name;
    }

    //g_dis_brd_baudrate=9600 ;
    public synchronized static void setDisBrdBaudRate(int val) {
        g_dis_brd_baudrate = val;
    }

    public synchronized static int getDisBrdBaudRate() {
        return g_dis_brd_baudrate;
    }

    //g_dis_brd_no_retries=3 ;
    public synchronized static void setDisBrdRetries(int val) {
        g_dis_brd_no_retries = val;
    }

    public synchronized static int getDisBrdRetries() {
        return g_dis_brd_no_retries;
    }

    // g_dis_brd_retry_wait_time
    public synchronized static void setDisbRdRetryWaitTime(int val) {
        g_dis_brd_retry_wait_time = val;
    }

    public synchronized static int getDisbRdRetryWaitTime() {
        return g_dis_brd_retry_wait_time;
    }

    public synchronized static void setDisbRdGap(int val) {
        g_dis_brd_gap = val;
    }

    public synchronized static int getDisbRdGap() {
        return g_dis_brd_gap;
    }

    // trip_detect_dis
    public synchronized static void setTripDetDistance(int val) {
        trip_detect_dis = val;
    }

    public synchronized static int getTripDetDistance() {
        return trip_detect_dis;
    }

    // route_deviate_dis
    public synchronized static void setRouteDevDistance(int val) {
        route_deviate_dis = val;
    }

    public synchronized static int getRouteDevDistance() {
        return route_deviate_dis;
    }

    //sch_route_enable
    public synchronized static void setSchRouteEnable(byte val) {
        sch_route_enable = val;
    }

    public synchronized static byte getSchRouteEnable() {
        return sch_route_enable;
    }

    //nonstoptime
    public synchronized static void setNonStopTime(int val) {
        non_stop_time = val;
    }

    public synchronized static int getNonStopTime() {
        return non_stop_time;
    }

    public synchronized static void setNonStopDistance(int val) {
        non_stop_distance = val;
    }

    public synchronized static int getNonStopDistance() {
        return non_stop_distance;
    }

    //main_device_shut_time
    public synchronized static void setMainDeviceShutTime(int val) {
        main_device_shut_time = val;
    }

    public synchronized static int getMainDeviceShutTime() {
        return main_device_shut_time;
    }

    //  coded_data_from_cs_string
    public synchronized static void setCodedMsgFromCs(byte val) {
        coded_data_from_cs_string = val;
    }

    public synchronized static byte getCodedMsgFromCs() {
        return coded_data_from_cs_string;
    }

    //ip_address1
    public synchronized static void setIpAddr1(String val) {
        ip_address1 = val;
    }

    public synchronized static String getIpAddr1() {
        return ip_address1;
    }

    //  Port no1
    public synchronized static void setPortNo1(int val) {
        port_no1 = val;
    }

    public synchronized static int getPortNo1() {
        return port_no1;
    }

    //ipaddress 2
    //ip_address1
    public synchronized static void setIpAddr2(String val) {
        ip_address2 = val;
    }

    public synchronized static String getIpAddr2() {
        return ip_address2;
    }

    //  Port no1
    public synchronized static void setPortNo2(int val) {
        port_no2 = val;
    }

    public synchronized static int getPortNo2() {
        return port_no2;
    }

    //ip_address1 enable
    public synchronized static void setIpAddr1Enable(boolean val) {
        ip_address1_enable = val;
    }

    public synchronized static boolean getIpAddr1Enable() {
        return ip_address1_enable;
    }
    //ip_address2 enable

    public synchronized static void setIpAddr2Enable(boolean val) {
        ip_address2_enable = val;
    }

    public synchronized static boolean getIpAddr2Enable() {
        return ip_address2_enable;
    }

    //ip_address3
    public synchronized static void setIpAddr3Enable(boolean val) {
        ip_address3_enable = val;
    }

    public synchronized static boolean getIpAddr3Enable() {
        return ip_address3_enable;
    }

    public synchronized static void setIpAddr3(String val) {
        ip_address3 = val;
    }

    public synchronized static String getIpAddr3() {
        return ip_address3;
    }

    //  Port no1
    public synchronized static void setPortNo3(int val) {
        port_no3 = val;
    }

    public synchronized static int getPortNo3() {
        return port_no3;
    }
    //ip address 4

    public synchronized static void setIpAddr4Enable(boolean val) {
        ip_address4_enable = val;
    }

    public synchronized static boolean getIpAddr4Enable() {
        return ip_address4_enable;
    }

    public synchronized static void setIpAddr5Enable(boolean val) {
        ip_address5_enable = val;
    }

    public synchronized static boolean getIpAddr5Enable() {
        return ip_address5_enable;
    }

    public synchronized static void setIpAddr4(String val) {
        ip_address4 = val;
    }

    public synchronized static String getIpAddr4() {
        return ip_address4;
    }

    //  Port no1
    public synchronized static void setPortNo4(int val) {
        port_no4 = val;
    }

    public synchronized static int getPortNo4() {
        return port_no4;
    }
     public synchronized static void setFtpPortNo(int val) {
        ftp_port_no = val;
    }

    public synchronized static int getFtpPortNo() {
        return ftp_port_no;
    }

    //ip5
    public synchronized static void setPortNo5(int val) {
        port_no5 = val;
    }

    public synchronized static int getPortNo5() {
        return port_no5;
    }

    public synchronized static void setIpAddr5(String val) {
        ip_address5 = val;
    }

    public synchronized static String getIpAddr5() {
        return ip_address5;
    }

    public synchronized static void setApn(String val) {
        apn = val;
    }

    public synchronized static String getApn() {
        return apn;
    }

    //interfacing selecting ipaddresses
    public synchronized static void setIpInterface(byte val) {
        ip_interface = val;
    }

    public synchronized static byte getIpInterface() {
        return ip_interface;
    }

    // ******************************current schedule set and get methods ********************************
    //schdeule id
    public synchronized static String getSchId() {
        return clsSharedVariables.cur_sch_route.sch_id;
    }

    public synchronized static void setSchId(String id) {
        clsSharedVariables.cur_sch_route.sch_id = id;
    }

    //schedule availability
    public synchronized static boolean getCurSchAvailable() {
        return clsSharedVariables.cur_sch_route.schedule_available;
    }

    public synchronized static void setCurSchAvailable(boolean id) {
        clsSharedVariables.cur_sch_route.schedule_available = id;
    }

    //No trips
    public synchronized static int getCurSchNoTrips() {
        return clsSharedVariables.cur_sch_route.no_trips;
    }

    public synchronized static void setCurSchNoTrips(int id) {
        clsSharedVariables.cur_sch_route.no_trips = id;
    }

    //Start Date
    public synchronized static Date getCurSchStartDate() {
        return clsSharedVariables.cur_sch_route.sch_startdatetime;
    }

    public synchronized static void setCurSchStartDate(Date id) {
        clsSharedVariables.cur_sch_route.sch_startdatetime = id;
    }

    //End Date
    public synchronized static Date getCurSchEndDate() {
        return clsSharedVariables.cur_sch_route.sch_enddatetime;
    }

    public synchronized static void setCurSchEndDate(Date id) {
        clsSharedVariables.cur_sch_route.sch_enddatetime = id;
    }

    //Start Time
    public synchronized static long getCurSchStartTime() {
        return clsSharedVariables.cur_sch_route.sch_starttime;
    }

    public synchronized static void setCurSchStartTime(long id) {
        clsSharedVariables.cur_sch_route.sch_starttime = id;
    }

    //End Time
    public synchronized static long getCurSchEndTime() {
        return clsSharedVariables.cur_sch_route.sch_endtime;
    }

    public synchronized static void setCurSchEndTime(long id) {
        clsSharedVariables.cur_sch_route.sch_endtime = id;
    }

    //Trip Status
    public synchronized static byte getCurSchTripStatus(int stop_no) {
        return clsSharedVariables.cur_sch_route.trip_status[stop_no];
    }

    public synchronized static void setCurSchTripStatus(int stop_no, byte status) {
        clsSharedVariables.cur_sch_route.trip_status[stop_no] = status;
    }

    //Route No
    public synchronized static String getCurSchRouteNo(int trip_no) {
        return clsSharedVariables.cur_sch_route.route_nos[trip_no];
    }

    public synchronized static void setCurSchRouteNo(int trip_no, String routeno) {
        clsSharedVariables.cur_sch_route.route_nos[trip_no] = routeno;
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.getCurSrcDesStopName((short) trip_no);
        objReadFiles = null;
    }

    //permanent schdeule available
    //schedule availability
    public synchronized static boolean getPermSchAvailable() {
        return clsSharedVariables.perm_sch.schedule_available;
    }

    public synchronized static void setPermSchAvailable(boolean id) {
        clsSharedVariables.perm_sch.schedule_available = id;
    }

    //Temprary schdeule available
    //schedule availability
    public synchronized static boolean getTempSchAvailable() {
        return clsSharedVariables.temp_sch.schedule_available;
    }

    public synchronized static void setTempSchAvailable(boolean id) {
        clsSharedVariables.temp_sch.schedule_available = id;
    }

    //Start Time
    public synchronized static long getPermSchStartTime() {
        return clsSharedVariables.perm_sch.sch_starttime;
    }

    public synchronized static void setPermSchStartTime(long id) {
        clsSharedVariables.perm_sch.sch_starttime = id;
    }

    //***************************************END of set and get methods *******************************
    //driver bus stopped  cnt
    public synchronized static short getDriverStopCnt() {
        return clsSharedVariables.driver_bus_stop_cnt;
    }

    public synchronized static void incDriverStopCnt() {
        driver_bus_stop_cnt = (short) (driver_bus_stop_cnt + 1);
    }

    public synchronized static void setDriverStopCnt(short id) {
        clsSharedVariables.driver_bus_stop_cnt = id;
    }

    //driver bus stopped   duration
    public synchronized static short getDriverStopDur(short i) {
        return clsSharedVariables.driver_bus_stop_dur[i];
    }

    public synchronized static void setDriverStopDur(short i, short id) {
        clsSharedVariables.driver_bus_stop_dur[i] = id;
    }

    //Phone no
    public synchronized static String getPhoneNo(byte i) {
        return clsSharedVariables.objPhoneNoList.phone_num[i];
    }

    public synchronized static void setPhoneNo(byte i, String id) {
        clsSharedVariables.objPhoneNoList.phone_num[i] = new String();
        clsSharedVariables.objPhoneNoList.phone_num[i] = id;
    }

    //    Phone name
    public synchronized static String getPhoneName(byte i) {
        return clsSharedVariables.objPhoneNoList.phone_name[i];
    }

    public synchronized static void setPhoneName(byte i, String id) {
        clsSharedVariables.objPhoneNoList.phone_name[i] = new String();
        clsSharedVariables.objPhoneNoList.phone_name[i] = id;
    }

    //
    public synchronized static byte getPhoneNoCnt() {
        return clsSharedVariables.no_phone_nos;
    }

    public synchronized static void setPhoneNoCnt(byte i) {
        clsSharedVariables.no_phone_nos = i;

    }

    public synchronized static boolean get_special_msg_status() {
        return special_msg_checked;
    }

    public synchronized static void set_special_msg_status(boolean status) {
        special_msg_checked = status;
    }

    public synchronized static byte get_special_msg_no() {
        return special_msg_checked_no;
    }

    public synchronized static void set_special_msg_no(byte no) {
        special_msg_checked_no = no;
    }

    public synchronized static boolean get_sleep_mode_status() {
        return sleep_mode_entered;
    }

    public synchronized static void set_sleep_mode_status(boolean status) {
        sleep_mode_entered = status;
    }

    public synchronized static short get_gprs_sleep_val() {
        return gps_update_rate_sleep;
    }

    public synchronized static void set_gprs_sleep_val(short no) {
        gps_update_rate_sleep = no;
    }

    public synchronized static short get_gprs_normal_mode_val() {
        return gps_update_rate_normal;
    }

    public synchronized static void set_gprs_normal_mode_val(short no) {
        gps_update_rate_normal = no;
    }

    public synchronized static boolean get_sleep_mode_refresh_send() {
        return sleep_mode_refresh_send_data;
    }

    public synchronized static void set_sleep_mode_refresh_send(boolean status) {
        sleep_mode_refresh_send_data = status;
    }

    public synchronized static boolean get_nmea_gprmc() {
        return nmea_gprmc;
    }

    public synchronized static void set_nmea_gprmc(boolean status) {
        nmea_gprmc = status;
    }

    public synchronized static boolean get_nmea_gpgga() {
        return nmea_gpgga;
    }

    public synchronized static void set_nmea_gpgga(boolean status) {
        nmea_gpgga = status;
    }

    public synchronized static boolean get_nmea_gpgsv() {
        return nmea_gpgsv;
    }

    public synchronized static void set_nmea_gpgsv(boolean status) {
        nmea_gpgsv = status;
    }

    public synchronized static boolean get_nmea_gpvtg() {
        return nmea_gpvtg;
    }

    public synchronized static void set_nmea_gpvtg(boolean status) {
        nmea_gpvtg = status;
    }

    public synchronized static boolean get_nmea_gpgsa() {
        return nmea_gpgsa;
    }

    public synchronized static void set_nmea_gpgsa(boolean status) {
        nmea_gpgsa = status;
    }

    public synchronized static void set_src_name(String val, short l_cur_trip_no) {
        clsSharedVariables.cur_sch_route.src_name[l_cur_trip_no] = val;
    }

    public synchronized static String get_src_name(short l_cur_trip_no) {
        return clsSharedVariables.cur_sch_route.src_name[l_cur_trip_no];
    }

    public synchronized static void set_des_name(String val, short l_cur_trip_no) {
        clsSharedVariables.cur_sch_route.des_name[l_cur_trip_no] = val;
    }

    public synchronized static String get_des_name(short l_cur_trip_no) {
        return clsSharedVariables.cur_sch_route.des_name[l_cur_trip_no];
    }

    public synchronized static void setGpsState(byte state) {
        gps_state_img = state;
    }

    public synchronized static byte getGpsState() {
        return gps_state_img;
    }

    public synchronized static void setGsmClkState(byte state) {
        gsm_clk_state_img = state;
    }

    public synchronized static byte getGsmClkState() {
        return gsm_clk_state_img;
    }

    public synchronized static void setServerConnectImg(byte state) {
        server_connect_img = state;
    }

    public synchronized static byte getServerConnectImg() {
        return server_connect_img;
    }

    public synchronized static void setServerConnectImg2(byte state) {
        server_connect_img2 = state;
    }

    public synchronized static byte getServerConnectImg2() {
        return server_connect_img2;
    }

    public synchronized static void setServerConnectImg3(byte state) {
        server_connect_img3 = state;
    }

    public synchronized static byte getServerConnectImg3() {
        return server_connect_img3;
    }

    public synchronized static void setServerConnectImg4(byte state) {
        server_connect_img4 = state;
    }

    public synchronized static byte getServerConnectImg4() {
        return server_connect_img4;
    }

    public synchronized static void setServerConnectImg5(byte state) {
        server_connect_img5 = state;
    }

    public synchronized static byte getServerConnectImg5() {
        return server_connect_img5;
    }

    public synchronized static void setTimeDisplayImg(String val) {
        time_display_img = val;
    }

    public synchronized static String getTimeDisplayImg() {
        return time_display_img;
    }

    public synchronized static void setGpsPktCamInGprs(int val) {
        gps_pkt_came_in_gprs_inc = val;
    }

    public synchronized static int getGpsPktCamInGprs() {
        return gps_pkt_came_in_gprs_inc;
    }

    public synchronized static void setGsmSignalStrength(int val) {
        gsm_signal_strength = val;
    }

    public synchronized static int getGsmSignalStrength() {
        return gsm_signal_strength;
    }

    public synchronized static void setDataSendTOServer(int val) {
        data_send_to_server_inc = val;
    }

    public synchronized static int getDataSendTOServer() {
        return data_send_to_server_inc;
    }

    public synchronized static void setResetObuVal(byte val) {
        reset_obu_val = val;
    }

    public synchronized static byte getResetObuVal() {
        return reset_obu_val;
    }

    //ip address 1 respose ack packets
    public synchronized static boolean get_gprs_res_ackpkt() {
        return gprs_res_pkt_came;
    }

    public synchronized static void set_gprs_res_ackpkt(boolean status) {
        gprs_res_pkt_came = status;
    }

    public synchronized static void set_gprs_res_pkt_ackmsg_type(byte val) {
        gprs_res_pkt_msg_type = val;
    }

    public synchronized static byte get_gprs_res_pkt_ackmsg_type() {
        return gprs_res_pkt_msg_type;
    }

    public synchronized static boolean get_gprs_res_ackpkt_status() {
        return gprs_res_pkt_status;
    }

    public synchronized static void set_gprs_res_ackpkt_status(boolean status) {
        gprs_res_pkt_status = status;
    }

    //ip address 2 respose ack packets
    public synchronized static boolean get_gprs_res_ackpkt2() {
        return gprs_res_pkt_came2;
    }

    public synchronized static void set_gprs_res_ackpkt2(boolean status) {
        gprs_res_pkt_came2 = status;
    }

    public synchronized static void set_gprs_res_pkt_ackmsg_type2(byte val) {
        gprs_res_pkt_msg_type2 = val;
    }

    public synchronized static byte get_gprs_res_pkt_ackmsg_type2() {
        return gprs_res_pkt_msg_type2;
    }

    public synchronized static boolean get_gprs_res_ackpkt_status2() {
        return gprs_res_pkt_status2;
    }

    public synchronized static void set_gprs_res_ackpkt_status2(boolean status) {
        gprs_res_pkt_status2 = status;
    }

    public synchronized static void setPingConfigTime(int val) {
        ping_config_time = val;
    }

    public synchronized static int getPingConfigTime() {
        if (ping_config_time < 50) {
            ping_config_time = 50;
        }
        return ping_config_time;
    }

    public synchronized static void setRebootSystemEnabled(boolean state) {
        reboot_system_enabled = state;
    }

    public synchronized static boolean getRebootSystemEnabled() {
        return reboot_system_enabled;
    }

    public synchronized static void set_phonecall_type(byte val) {
        call_type_in_out = val;
    }

    public synchronized static byte get_phonecall_type() {
        return call_type_in_out;
    }

    public synchronized static boolean get_voice_call_enabled_status() {
        return voice_call_enabled;
    }

    public synchronized static void set_voice_call_enabled_status(boolean status) {
        voice_call_enabled = status;
    }

    public synchronized static boolean get_speaker_enabled_status() {
        return speaker_enabled;
    }

    public synchronized static void set_speaker_enabled_status(boolean status) {
        speaker_enabled = status;
    }

    public synchronized static boolean get_voice_call_start_status() {
        return vocie_call_started;
    }

    public synchronized static void set_voice_call_start_status(boolean status) {
        vocie_call_started = status;
    }

    public synchronized static byte get_gps_nmea_up() {
        return gps_nmea_up;
    }

    public static synchronized void set_gps_nmea_up(byte val) {
        gps_nmea_up = val;
    }

    public synchronized static String get_driver_name() {
        return driver_name;
    }

    public static synchronized void set_driver_name(String val) {
        driver_name = val;
    }

    public synchronized static String get_driver_id() {
        return driver_id;
    }

    public static synchronized void set_driver_id(String val) {
        driver_id = val;
    }

    public synchronized static String get_duty_id() {
        return duty_id;
    }

    public static synchronized void set_duty_id(String val) {
        duty_id = val;
    }

    public synchronized static String get_previous_driver_rfid() {
        return previous_driver_duty_id;
    }

    public static synchronized void set_previous_driver_rfid(String val) {
        previous_driver_duty_id = val;
    }

    public synchronized static String get_previous_conductor_rfid() {
        return previous_con_duty_id;
    }

    public static synchronized void set_previous_conductor_rfid(String val) {
        previous_con_duty_id = val;
    }

    public synchronized static String get_conductor_id() {
        return conductor_id;
    }

    public static synchronized void set_conductor_id(String val) {
        conductor_id = val;
    }

    public synchronized static String get_usb() {
        return usb_check;
    }

    public static synchronized void set_usb(String val) {
        usb_check = val;
    }

    public synchronized static String get_conductor_name() {
        return conductor_name;
    }

    public static synchronized void set_conductor_name(String val) {
        conductor_name = val;
    }

    public synchronized static byte get_ring_tone_volume() {
        return ring_tone_volume;
    }

    public static synchronized void set_ring_tone_volume(byte val) {
        ring_tone_volume = val;
    }

    public synchronized static byte get_voice_call_volume() {
        return voice_call_volume;
    }

    public static synchronized void set_voice_call_volume(byte val) {
        voice_call_volume = val;
    }

    public synchronized static byte get_speaker_volume() {
        return speaker_volume;
    }

    public static synchronized void set_speaker_volume(byte val) {
        speaker_volume = val;
    }

    public synchronized static boolean get_panic_data_sent_server() {
        return panic_data_sent_server;
    }

    public static synchronized void set_panic_data_sent_server(boolean val) {
        panic_data_sent_server = val;
    }

    public synchronized static boolean get_gsm_connected_status() {
        return gsm_status_connected;
    }

    public static synchronized void set_gsm_connected_status(boolean val) {
        gsm_status_connected = val;
    }

    public synchronized static boolean get_nmea_came() {
        return nmea_came;
    }

    public static synchronized void set_nmea_came(boolean val) {
        nmea_came = val;
    }

    public synchronized static boolean get_at_came() {
        return at_con_came;
    }

    public static synchronized void set_at_came(boolean val) {
        at_con_came = val;
    }

    public synchronized static String get_QCCID() {
        return qccid;
    }

    public static synchronized void set_QCCID(String val) {
        qccid = val;
    }

    public synchronized static String get_own_mobileno() {
        return own_mobile_no;
    }

    public static synchronized void set_own_mobileno(String val) {
        own_mobile_no = val;
    }

    public synchronized static String get_gps_comport_name() {
        return gps_comport_name;
    }

    public static synchronized void set_gps_comport_name(String val) {
        gps_comport_name = val;
    }

    public synchronized static String get_at_comport_name() {
        return at_comport_name;
    }

    public static synchronized void set_at_comport_name(String val) {
        at_comport_name = val;
    }

    public synchronized static String get_ppp_comport_name() {
        return ppp_comport_name;
    }

    public static synchronized void set_ppp_comport_name(String val) {
        ppp_comport_name = val;
    }

    public synchronized static boolean get_gps_comport_connected() {
        return gps_comport_connected;
    }

    public static synchronized void set_gps_comport_connected(boolean val) {
        gps_comport_connected = val;
    }

    public synchronized static boolean get_at_comport_connected() {
        return gps_comport_connected;
    }

    public static synchronized void set_at_comport_connected(boolean val) {
        gps_comport_connected = val;
    }

    public synchronized static boolean get_ppp_comport_connected() {
        return ppp_comport_connected;
    }

    public static synchronized void set_ppp_comport_connected(boolean val) {
        ppp_comport_connected = val;
    }

    public synchronized static void setGsmModuleOn(boolean state) {
        gsm_module_on = state;
    }

    public synchronized static boolean getGsmModuleOn() {
        return gsm_module_on;
    }

    public synchronized static void setGsmModuleOnPowerOn(boolean state) {
        gsm_module_power_on = state;
    }

    public synchronized static boolean getGsmModuleOnPowerOn() {
        return gsm_module_power_on;
    }

    public synchronized static void setIgnitionStatus(boolean state) {
        ignition_on_status = state;
    }

    public synchronized static boolean getIgnitionStatus() {
        return ignition_on_status;
    }

    public synchronized static void setTamperState(byte state) {
        tamper_state = state;
    }

    public synchronized static byte getTamperState() {
        return tamper_state;
    }

    public synchronized static void setStartGpsDiagData(boolean state) {
        start_gps_diag_data = state;
    }

    public synchronized static boolean getStartGpsDiagData() {
        return start_gps_diag_data;
    }

    public synchronized static void setNmeaDiagData(String state) {
        nmea_diag_data = state;
    }

    public synchronized static String getNmeaDiagData() {
        return nmea_diag_data;
    }

    public synchronized static void setGpsNmeaPktStatus(boolean state) {
        gps_nmea_pkt_came = state;
    }

    public synchronized static boolean getGpsNmeaPktStatus() {
        return gps_nmea_pkt_came;
    }

    public synchronized static void setAtPktStatus(boolean state) {
        at_pkt_came = state;
    }

    public synchronized static boolean getAtPktStatus() {
        return at_pkt_came;
    }

    public synchronized static void setSevenPortsConfigured(boolean state) {
        seven_ports_configured = state;
    }

    public synchronized static boolean getSevenPortsConfigured() {
        return seven_ports_configured;
    }

    public synchronized static void setSimReady(boolean state) {
        sim_ready = state;
    }

    public synchronized static boolean getSimReady() {
        return sim_ready;
    }

    public synchronized static void setWifiConnect(boolean state) {
        wifi_connect = state;
    }

    public synchronized static boolean getWifiConnect() {
        return wifi_connect;
    }

    public synchronized static void setReverseCameraOn(boolean state) {
        reverse_camera_turnedon = state;
    }

    public synchronized static boolean getReverseCameraOn() {
        return reverse_camera_turnedon;
    }
    public synchronized static void setDoorCameraOn(boolean state) {
        door_camera_turnedon = state;
    }

    public synchronized static boolean getDoorCameraOn() {
        return door_camera_turnedon;
    }
    public synchronized static void setIntDisbBrdMsgCame(boolean state) {
        int_disbrd_msg_came = state;
    }

    public synchronized static boolean getIntDisbBrdMsgCame() {
        return int_disbrd_msg_came;
    }

    public synchronized static void setStopReqMsg(boolean state) {
        stop_req_message_came = state;
    }

    public synchronized static boolean getStopReqMsg() {
        return stop_req_message_came;
    }

    public synchronized static void setStopReqMsgOthers(boolean state) {
        stop_req_message_others_came = state;
    }

    public synchronized static boolean getStopReqMsgOthers() {
        return stop_req_message_others_came;
    }

    public synchronized static void setCanEnabled(boolean state) {
        can_enabled = state;
    }

    public synchronized static boolean getCanEnabled() {
        return can_enabled;
    }

    public synchronized static void setCanEnabled1(boolean state) {
        can_enabled1 = state;
    }

    public synchronized static boolean getCanEnabled1() {
        return can_enabled1;
    }

    public synchronized static void setCanDataEnabled(boolean state) {
        can_data_enabled = state;
    }

    public synchronized static boolean getCanDataEnabled() {
        return can_data_enabled;
    }

    public synchronized static void setCanLogEnabled(boolean state) {
        can_log_enabled = state;
    }

    public synchronized static boolean getCanLogEnabled() {
        return can_log_enabled;
    }

    public synchronized static void setReverseCamVal(short val) {
        reverse_cam_val = val;
    }

    public synchronized static short getReverseCamVal() {
        return reverse_cam_val;
    }

    public synchronized static void setReverseCamVal1(short val) {
        reverse_cam_val1 = val;
    }

    public synchronized static short getReverseCamVal1() {
        return reverse_cam_val1;
    }

    public synchronized static void setReverseCamVal2(short val) {
        reverse_cam_val2 = val;
    }

    public synchronized static short getReverseCamVal2() {
        return reverse_cam_val2;
    }

    public synchronized static void setReverseCamPgn(int val) {
        reverse_cam_pgn = val;
    }

    public synchronized static int getReverseCamPgn() {
        return reverse_cam_pgn;
    }

    public synchronized static void setReverseCamPgn1(int val) {
        reverse_cam_pgn1 = val;
    }

    public synchronized static int getReverseCamPgn1() {
        return reverse_cam_pgn1;
    }

    public synchronized static void setReverseCamPgn2(int val) {
        reverse_cam_pgn2 = val;
    }

    public synchronized static int getReverseCamPgn2() {
        return reverse_cam_pgn2;
    }

    public synchronized static void setReverseCamSpn(int val) {
        reverse_cam_spn = val;
    }

    public synchronized static int getReverseCamSpn() {
        return reverse_cam_spn;
    }

    public synchronized static void setReverseCamSpn1(int val) {
        reverse_cam_spn1 = val;
    }

    public synchronized static int getReverseCamSpn1() {
        return reverse_cam_spn1;
    }

    public synchronized static void setReverseCamSpn2(int val) {
        reverse_cam_spn2 = val;
    }

    public synchronized static int getReverseCamSpn2() {
        return reverse_cam_spn2;
    }
//stop requested

    public synchronized static void setStopReqVal(short val) {
        stop_req_val = val;
    }

    public synchronized static short getStopReqVal() {
        return stop_req_val;
    }

    public synchronized static void setStopReqPgn(int val) {
        stop_req_pgn = val;
    }

    public synchronized static int getStopReqPgn() {
        return stop_req_pgn;
    }

    public synchronized static void setStopReqSpn(int val) {
        stop_req_spn = val;
    }

    public synchronized static int getStopReqSpn() {
        return stop_req_spn;
    }

    // Door 1
    public synchronized static void setDoor1OpenVal(short val) {
        door1_open_val = val;
    }

    public synchronized static short getDoor1OpenVal() {
        return door1_open_val;
    }

    public synchronized static void setDoor1CloseVal(short val) {
        door1_close_val = val;
    }

    public synchronized static short getDoor1CloseVal() {
        return door1_close_val;
    }

    public synchronized static void setDoor1Pgn(int val) {
        door1_pgn = val;
    }

    public synchronized static int getDoor1Pgn() {
        return door1_pgn;
    }

    public synchronized static void setDoor1Spn(int val) {
        door1_spn = val;
    }

    public synchronized static int getDoor1Spn() {
        return door1_spn;
    }

    // Door 2
    public synchronized static void setDoor2OpenVal(short val) {
        door2_open_val = val;
    }

    public synchronized static short getDoor2OpenVal() {
        return door2_open_val;
    }

    public synchronized static void setDoor2CloseVal(short val) {
        door2_close_val = val;
    }

    public synchronized static short getDoor2CloseVal() {
        return door2_close_val;
    }

    public synchronized static void setDoor2Pgn(int val) {
        door2_pgn = val;
    }

    public synchronized static int getDoor2Pgn() {
        return door2_pgn;
    }

    public synchronized static void setDoor2Spn(int val) {
        door2_spn = val;
    }

    public synchronized static int getDoor2Spn() {
        return door2_spn;
    }

    // CAn Video Record
    public synchronized static void setVideoRecordStartVal(short val) {
        door1_open_val = val;
    }

    public synchronized static short getVideoRecordStartVal() {
        return door1_open_val;
    }

    public synchronized static void setVideoRecordEndVal(short val) {
        door1_close_val = val;
    }

    public synchronized static short getVideoRecordEndVal() {
        return door1_close_val;
    }

    public synchronized static void setVideoRecordPgn(int val) {
        door1_pgn = val;
    }

    public synchronized static int getVideoRecordPgn() {
        return door1_pgn;
    }

    public synchronized static void setVideoRecordSpn(int val) {
        door1_spn = val;
    }

    public synchronized static int getVideoRecordSpn() {
        return door1_spn;
    }

    public synchronized static void setCanVehicleSpeed(byte val) {
        can_vehicle_speed = val;
    }

    public synchronized static int getCanVehicleSpeed() {
        return can_vehicle_speed;
    }

    /* public synchronized static void setCanVehicleSpeed1(byte val) {
        can_vehicle_speed1 = val;
    }

    public synchronized static int getCanVehicleSpeed1() {
        return can_vehicle_speed1;
    }*/
    public synchronized static void setCanSpeedPgn(int val) {
        can_speed_pgn = val;
    }

    public synchronized static int getCanSpeedPgn() {
        return can_speed_pgn;
    }

    public synchronized static void setCanSpeedSpn(int val) {
        can_speed_spn = val;
    }

    public synchronized static int getCanSpeedSpn() {
        return can_speed_spn;
    }

    //record type
    public synchronized static void setRecordType(byte val) {
        record_type = val;
    }

    public synchronized static byte getRecordType() {
        return record_type;
    }

    //digital input event enabled or not
    public synchronized static void setEventRecordEnabled(boolean val) {
        event_record_enabled = val;
    }

    public synchronized static boolean getEventRecordEnabled() {
        return event_record_enabled;
    }

    public synchronized static void setEventPostRecordTime(byte val) {
        event_post_record_time = val;
    }

    public synchronized static byte getEventPostRecordTime() {
        return event_post_record_time;
    }

    public synchronized static void setEventPreRecordTime(byte val) {
        event_pre_record_time = val;
    }

    public synchronized static byte getEventPreRecordTime() {
        return event_pre_record_time;
    }

    public synchronized static void setEventStreamType(byte val) {
        event_stream_type = val;
    }

    public synchronized static byte getEventStreamType() {
        return event_stream_type;
    }

    //can based event enabled for recording
    public synchronized static void setCanRecordEnabled(boolean val) {
        can_record_enabled = val;
    }

    public synchronized static boolean getCanRecordEnabled() {
        return can_record_enabled;
    }

    public synchronized static void setCanPostRecordTime(byte val) {
        can_post_record_time = val;
    }

    public synchronized static byte getCanPostRecordTime() {
        return can_post_record_time;
    }

    public synchronized static void setCanPreRecordTime(byte val) {
        can_pre_record_time = val;
    }

    public synchronized static byte getCanPreRecordTime() {
        return can_pre_record_time;
    }

    public synchronized static void setCanStreamType(byte val) {
        can_stream_type = val;
    }

    public synchronized static byte getCanStreamType() {
        return can_stream_type;
    }

    public synchronized static void setRecordFromTime(String val) {
        record_from_time = val;
    }

    public synchronized static String getRecordFromTime() {
        return record_from_time;
    }

    public synchronized static void setRecordToTime(String val) {
        record_to_time = val;
    }

    public synchronized static String getRecordToTime() {
        return record_to_time;
    }

    public synchronized static void setCam1RecordSelected(boolean state) {
        record_cam1 = state;
    }

    public synchronized static boolean getCam1RecordSelected() {
        return record_cam1;
    }

    public synchronized static void setCam2RecordSelected(boolean state) {
        record_cam2 = state;
    }

    public synchronized static boolean getCam2RecordSelected() {
        return record_cam2;
    }

    public synchronized static void setCam3RecordSelected(boolean state) {
        record_cam3 = state;
    }

    public synchronized static boolean getCam3RecordSelected() {
        return record_cam3;
    }

    public synchronized static void setCam4RecordSelected(boolean state) {
        record_cam4 = state;
    }

    public synchronized static boolean getCam4RecordSelected() {
        return record_cam4;
    }

    public synchronized static void setCam5RecordSelected(boolean state) {
        record_cam5 = state;
    }

    public synchronized static boolean getCam5RecordSelected() {
        return record_cam5;
    }

    public synchronized static void setCam6RecordSelected(boolean state) {
        record_cam6 = state;
    }

    public synchronized static boolean getCam6RecordSelected() {
        return record_cam6;
    }

    public synchronized static void setCam7RecordSelected(boolean state) {
        record_cam7 = state;
    }

    public synchronized static boolean getCam7RecordSelected() {
        return record_cam7;
    }

    public synchronized static void setCam8RecordSelected(boolean state) {
        record_cam8 = state;
    }

    public synchronized static boolean getCam8RecordSelected() {
        return record_cam8;
    }

    public synchronized static void setCam1Enabled(boolean state) {
        enable_cam1 = state;
    }

    public synchronized static boolean getCam1Enabled() {
        return enable_cam1;
    }

    public synchronized static void setCam2Enabled(boolean state) {
        enable_cam2 = state;
    }

    public synchronized static boolean getCam2Enabled() {
        return enable_cam2;
    }

    public synchronized static void setCam3Enabled(boolean state) {
        enable_cam3 = state;
    }

    public synchronized static boolean getCam3Enabled() {
        return enable_cam3;
    }

    public synchronized static void setCam4Enabled(boolean state) {
        enable_cam4 = state;
    }

    public synchronized static boolean getCam4Enabled() {
        return enable_cam4;
    }

    public synchronized static void setCam5Enabled(boolean state) {
        enable_cam5 = state;
    }

    public synchronized static boolean getCam5Enabled() {
        return enable_cam5;
    }

    public synchronized static void setCam6Enabled(boolean state) {
        enable_cam6 = state;
    }

    public synchronized static boolean getCam6Enabled() {
        return enable_cam6;
    }

    public synchronized static void setCam7Enabled(boolean state) {
        enable_cam7 = state;
    }

    public synchronized static boolean getCam7Enabled() {
        return enable_cam7;
    }

    public synchronized static void setCam8Enabled(boolean state) {
        enable_cam8 = state;
    }

    public synchronized static boolean getCam8Enabled() {
        return enable_cam8;
    }

    public synchronized static void setVideo1Connected(boolean state) {
        video1_connected = state;
    }

    public synchronized static boolean getVideo1Connected() {
        return video1_connected;
    }

    public synchronized static void setVideo2Connected(boolean state) {
        video2_connected = state;
    }

    public synchronized static boolean getVideo2Connected() {
        return video2_connected;
    }

    public synchronized static void setVideo3Connected(boolean state) {
        video3_connected = state;
    }

    public synchronized static boolean getVideo3Connected() {
        return video3_connected;
    }

    public synchronized static void setVideo4Connected(boolean state) {
        video4_connected = state;
    }

    public synchronized static boolean getVideo4Connected() {
        return video4_connected;
    }

    public synchronized static void setVideo5Connected(boolean state) {
        video5_connected = state;
    }

    public synchronized static boolean getVideo5Connected() {
        return video5_connected;
    }

    public synchronized static void setVideo6Connected(boolean state) {
        video6_connected = state;
    }

    public synchronized static boolean getVideo6Connected() {
        return video6_connected;
    }

    public synchronized static void setVideo7Connected(boolean state) {
        video7_connected = state;
    }

    public synchronized static boolean getVideo7Connected() {
        return video7_connected;
    }

    public synchronized static void setVideo8Connected(boolean state) {
        video8_connected = state;
    }

    public synchronized static boolean getVideo8Connected() {
        return video8_connected;
    }

    public synchronized static void setMainsStatus(boolean state) {
        mains_status = state;
    }

    public synchronized static boolean getMainsStatus() {
        return mains_status;
    }

    public synchronized static void setGsmDiagEnabled(boolean state) {
        gsm_diag_status = state;
    }

    public synchronized static boolean getGsmDiagEnabled() {
        return gsm_diag_status;
    }

    public synchronized static void setGpsDiagEnabled(boolean state) {
        gps_diag_status = state;
    }

    public synchronized static boolean getGpsDiagEnabled() {
        return gps_diag_status;
    }

    public synchronized static void setVideoTamperDiagEnabled(boolean state) {
        video_tamper_diag_status = state;
    }

    public synchronized static boolean getVideoTamperDiagEnabled() {
        return video_tamper_diag_status;
    }

    public synchronized static void setCanDiagEnabled(boolean state) {
        can_diag_status = state;
    }

    public synchronized static boolean getCanDiagEnabled() {
        return can_diag_status;
    }

    //comment for raw data
    public synchronized static void setCanRawEnable(boolean state) {
        can_raw_status = state;
    }

    public synchronized static boolean getCanRawEnable() {
        return can_raw_status;
    }

    public synchronized static void setCan1RawEnable(boolean state) {
        can1_raw_status = state;
    }

    public synchronized static boolean getCan1RawEnable() {
        return can1_raw_status;
    }
    //comment for raw data

    public synchronized static void setCanDiagEnabled1(boolean state) {
        can_diag_status1 = state;
    }

    public synchronized static boolean getCanDiagEnabled1() {
        return can_diag_status1;
    }

    public synchronized static void setRs232TxDiagEnabled(boolean state) {
        rs232tx_diag_status = state;
    }

    public synchronized static boolean getRs232TxDiagEnabled() {
        return rs232tx_diag_status;
    }

    public synchronized static void setRs232RxDiagEnabled(boolean state) {
        rs232rx_diag_status = state;
    }

    public synchronized static boolean getRs232RxDiagEnabled() {
        return rs232rx_diag_status;
    }

    public synchronized static void setEthernetTxDiagEnabled(boolean state) {
        ethernettx_diag_status = state;
    }

    public synchronized static boolean getEthernetTxDiagEnabled() {
        return ethernettx_diag_status;
    }

    public synchronized static void setEthernetRxDiagEnabled(boolean state) {
        ethernetrx_diag_status = state;
    }

    public synchronized static boolean getEthernetRxDiagEnabled() {
        return ethernetrx_diag_status;
    }

    public synchronized static void setCamDiagStatusEnabled(boolean state) {
        cam_diag_status = state;
    }

    public synchronized static boolean getCamDiagStatusEnabled() {
        return cam_diag_status;
    }

    public synchronized static void setCamDiagStatusCamera(byte cam_no) {
        cam_diag_status_camerano = cam_no;
    }

    public synchronized static byte getCamDiagStatusCamera() {
        return cam_diag_status_camerano;
    }

    /*
     an_bat_voltage;
     public static double an_mains_voltage;
     public static double an_temp;
     public static double an_adc0;
     public static double an_adc1;
     public static double an_adc2;
     public static double an_adc3;
     public static byte di_tamper;
     */
    public synchronized static void setAnaBatVoltage(double data) {
        an_bat_voltage = data;
    }

    public synchronized static double getAnaBatVoltage() {
        return an_bat_voltage;
    }

    public synchronized static void setAnaMainsVoltage(double data) {
        an_mains_voltage = data;
    }

    public synchronized static double getAnaMainsVoltage() {
        return an_mains_voltage;
    }

    public synchronized static void setAnaTemp(double data) {
        an_temp = data;
    }

    public synchronized static double getAnaTemp() {
        return an_temp;
    }

    /* public synchronized static void setAnaBatVoltage1(double data) {
        an_bat_voltage1 = data;
    }

    public synchronized static double getAnaBatVoltage1() {
        return an_bat_voltage1;
    }

    public synchronized static void setAnaMainsVoltage1(double data) {
        an_mains_voltage1 = data;
    }

    public synchronized static double getAnaMainsVoltage1() {
        return an_mains_voltage1;
    }

    public synchronized static void setAnaTemp1(double data) {
        an_temp1 = data;
    }

    public synchronized static double getAnaTemp1() {
        return an_temp1;
    }*/
    public synchronized static void setAnaAdc0(double data) {
        an_adc0 = data;
    }

    public synchronized static double getAnaAdc0() {
        return an_adc0;
    }

    public synchronized static void setAnaAdc1(double data) {
        an_adc1 = data;
    }

    public synchronized static double getAnaAdc1() {
        return an_adc1;
    }

    public synchronized static void setAnaAdc2(double data) {
        an_adc2 = data;
    }

    public synchronized static double getAnaAdc2() {
        return an_adc2;
    }

    public synchronized static void setAnaAdc3(double data) {
        an_adc3 = data;
    }

    public synchronized static double getAnaAdc3() {
        return an_adc3;
    }

    /* public synchronized static void setAnaAdc0Can1(double data) {
        an_adc0_can1 = data;
    }

    public synchronized static double getAnaAdc0Can1() {
        return an_adc0_can1;
    }

    public synchronized static void setAnaAdc1Can1(double data) {
        an_adc1_can1 = data;
    }

    public synchronized static double getAnaAdc1Can1() {
        return an_adc1_can1;
    }

    public synchronized static void setAnaAdc2Can1(double data) {
        an_adc2_can1 = data;
    }

    public synchronized static double getAnaAdc2Can1() {
        return an_adc2_can1;
    }

    public synchronized static void setAnaAdc3Can1(double data) {
        an_adc3_can1 = data;
    }

    public synchronized static double getAnaAdc3Can1() {
        return an_adc3_can1;
    }*/
    public synchronized static void setDigInTamper(byte data) {
        di_tamper = data;
    }

    public synchronized static byte getDigInTamper() {
        return di_tamper;
    }

    public synchronized static void setPreloadMsgDataSent(boolean data) {
        preload_msg_data_sent = data;
    }

    public synchronized static boolean getPreloadMsgDataSent() {
        return preload_msg_data_sent;
    }

    public synchronized static void setLastIgnitionState(byte data) {
        last_ignition_state = data;
    }

    public synchronized static byte getLastIgnitionState() {
        return last_ignition_state;
    }

    public synchronized static void setLastMainsState(byte data) {
        last_mains_state = data;
    }

    public synchronized static byte getLastMainsState() {
        return last_mains_state;
    }

    // cur_sec set and get methods
    public synchronized static void setFirstFixSec(long sec) {
        first_fix_sec = sec;
    }

    public synchronized static long getFirstFixSec() {
        return first_fix_sec;
    }

    public synchronized static void setCanVhmdScreen(boolean display) {
        canVhmdDisplayScreenOn = display;
    }

    public synchronized static boolean getCanVhmdScreen() {
        return canVhmdDisplayScreenOn;
    }

    public synchronized static void setCanElectricalVhmdScreen(boolean display) {
        canVhmdElectricalDisplayScreenOn = display;
    }

    public synchronized static boolean getCanElectricalVhmdScreen() {
        return canVhmdElectricalDisplayScreenOn;
    }

    public synchronized static void setCanSafetyVhmdScreen(boolean display) {
        canVhmdSafetyDisplayScreenOn = display;
    }

    public synchronized static boolean getCanSafetyVhmdScreen() {
        return canVhmdSafetyDisplayScreenOn;
    }

    public synchronized static void setCanTransmitVhmdScreen(boolean display) {
        canVhmdTransmitDisplayScreenOn = display;
    }

    public synchronized static boolean getCanTransmitVhmdScreen() {
        return canVhmdTransmitDisplayScreenOn;
    }

    public synchronized static void setCanEngineVhmdScreen(boolean display) {
        canVhmdEngineDisplayScreenOn = display;
    }

    public synchronized static boolean getCanEngineVhmdScreen() {
        return canVhmdEngineDisplayScreenOn;
    }

    public synchronized static void setCanOthersVhmdScreen(boolean display) {
        canVhmdOthersDisplayScreenOn = display;
    }

    public synchronized static boolean getCanOthersVhmdScreen() {
        return canVhmdOthersDisplayScreenOn;
    }

    //can2
    public synchronized static void setCanElectricalVhmdScreen1(boolean display) {
        canVhmdElectricalDisplayScreenOn1 = display;
    }

    public synchronized static boolean getCanElectricalVhmdScreen1() {
        return canVhmdElectricalDisplayScreenOn1;
    }

    public synchronized static void setCanSafetyVhmdScreen1(boolean display) {
        canVhmdSafetyDisplayScreenOn1 = display;
    }

    public synchronized static boolean getCanSafetyVhmdScreen1() {
        return canVhmdSafetyDisplayScreenOn1;
    }

    public synchronized static void setCanTransmitVhmdScreen1(boolean display) {
        canVhmdTransmitDisplayScreenOn1 = display;
    }

    public synchronized static boolean getCanTransmitVhmdScreen1() {
        return canVhmdTransmitDisplayScreenOn1;
    }

    public synchronized static void setCanEngineVhmdScreen1(boolean display) {
        canVhmdEngineDisplayScreenOn1 = display;
    }

    public synchronized static boolean getCanEngineVhmdScreen1() {
        return canVhmdEngineDisplayScreenOn1;
    }

    public synchronized static void setCanOthersVhmdScreen1(boolean display) {
        canVhmdOthersDisplayScreenOn1 = display;
    }

    public synchronized static boolean getCanOthersVhmdScreen1() {
        return canVhmdOthersDisplayScreenOn1;
    }

    public synchronized static void setCanUpdateTimeInterval(short data) {
        can_update_time_interval = data;
    }

    public synchronized static short getCanUpdateTimeInterval() {
        return can_update_time_interval;
    }

    public synchronized static void setCamera1TypeRtsp(boolean display) {
        camera1_type_rtsp = display;
    }

    public synchronized static boolean getCamera1TypeRtsp() {
        return camera1_type_rtsp;
    }

    public synchronized static void setCamera2TypeRtsp(boolean display) {
        camera2_type_rtsp = display;
    }

    public synchronized static boolean getCamera2TypeRtsp() {
        return camera2_type_rtsp;
    }

    public synchronized static void setCamera3TypeRtsp(boolean display) {
        camera3_type_rtsp = display;
    }

    public synchronized static boolean getCamera3TypeRtsp() {
        return camera3_type_rtsp;
    }

    public synchronized static void setCamera4TypeRtsp(boolean display) {
        camera4_type_rtsp = display;
    }

    public synchronized static boolean getCamera4TypeRtsp() {
        return camera4_type_rtsp;
    }

    public static synchronized void setVideo1Started(boolean state) {
        video1_started = state;
    }

    public static synchronized boolean getVideo1Started() {
        return video1_started;
    }

    public static synchronized void setVideo2Started(boolean state) {
        video2_started = state;
    }

    public static synchronized boolean getVideo2Started() {
        return video2_started;
    }

    public static synchronized void setVideo3Started(boolean state) {
        video3_started = state;
    }

    public static synchronized boolean getVideo3Started() {
        return video3_started;
    }

    public static synchronized void setVideo4Started(boolean state) {
        video4_started = state;
    }

    public static synchronized boolean getVideo4Started() {
        return video4_started;
    }

    public static synchronized void setVideo5Started(boolean state) {
        video5_started = state;
    }

    public static synchronized boolean getVideo5Started() {
        return video5_started;
    }

    public static synchronized void setVideo6Started(boolean state) {
        video6_started = state;
    }

    public static synchronized boolean getVideo6Started() {
        return video6_started;
    }

    public static synchronized void setVideo7Started(boolean state) {
        video7_started = state;
    }

    public static synchronized boolean getVideo7Started() {
        return video7_started;
    }

    public static synchronized void setVideo8Started(boolean state) {
        video8_started = state;
    }

    public static synchronized boolean getVideo8Started() {
        return video8_started;
    }

    public static synchronized void setVideoPlayStarted(boolean state) {
        videoplay_started = state;
    }

    public static synchronized boolean getVideoPlayStarted() {
        return videoplay_started;
    }

    public static synchronized void setVideoAllStarted(boolean state) {
        videoall_started = state;
    }

    public static synchronized boolean getVideoAllStarted() {
        return videoall_started;
    }

    public static synchronized void setAfcsEnabled(boolean state) {
        afcs_enabled = state;
    }

    public static synchronized boolean getAfcsEnabled() {
        return afcs_enabled;
    }

    public static synchronized void setEmergencyMapPktCame(boolean state) {
        emegency_route_packet_came = state;
    }

    public static synchronized boolean getEmergencyMapPktCame() {
        return emegency_route_packet_came;
    }

    public static synchronized void setMapsEnable(boolean state) {
        maps_enable = state;
    }

    public static synchronized boolean getMapsEnable() {
        return maps_enable;
    }

    public static synchronized void setEmergencyServicesEnable(boolean state) {
        emergencyService_enable = state;
    }

    public static synchronized boolean getEmergencyServicesEnable() {
        return emergencyService_enable;
    }

    public static synchronized void setEmergencySrcLat(double data) {
        emergency_src_lat = data;
    }

    public static synchronized double getEmergencySrcLat() {
        return emergency_src_lat;
    }

    public static synchronized void setEmergencySrcLong(double data) {
        emergency_src_long = data;
    }

    public static synchronized double getEmergencySrcLong() {
        return emergency_src_long;
    }

    public static synchronized void setEmergencyDesLat(double data) {
        emergency_des_lat = data;
    }

    public static synchronized double getEmergencyDesLat() {
        return emergency_des_lat;
    }

    public static synchronized void setEmergencyDesLong(double data) {
        emergency_des_long = data;
    }

    public static synchronized double getEmergencyDesLong() {
        return emergency_des_long;
    }

    public static synchronized void setNoEmergencyPtsFromGprs(int data) {
        no_emergency_lat_long_points_from_gprs = data;
    }

    public static synchronized int getNoEmergencyPtsFromGprs() {
        return no_emergency_lat_long_points_from_gprs;
    }

    public static synchronized void setArticulatedBus(boolean data) {
        articulated_bus = data;
    }

    public static synchronized boolean getArticulatedBus() {
        return articulated_bus;
    }

    public synchronized static void setCam1Type(byte camtype) {
        camType1 = camtype;
    }

    public synchronized static byte getCam1Type() {
        return camType1;
    }

    public synchronized static void setCam2Type(byte camtype2) {
        camType2 = camtype2;
    }

    public synchronized static byte getCam2Type() {
        return camType2;
    }

    public synchronized static void setCam3Type(byte camtype3) {
        camType3 = camtype3;
    }

    public synchronized static byte getCam3Type() {
        return camType3;
    }

    public synchronized static void setCam4Type(byte camtype4) {
        camType4 = camtype4;
    }

    public synchronized static byte getCam4Type() {
        return camType4;
    }

    public synchronized static void setCam5Type(byte type) {
        camType5 = type;
    }

    public synchronized static byte getCam5Type() {
        return camType5;
    }

    public synchronized static void setCam6Type(byte type) {
        camType6 = type;
    }

    public synchronized static byte getCam6Type() {
        return camType6;
    }

    public synchronized static void setCam7Type(byte type) {
        camType7 = type;
    }

    public synchronized static byte getCam7Type() {
        return camType7;
    }

    public synchronized static void setCam8Type(byte type) {
        camType8 = type;
    }

    public synchronized static byte getCam8Type() {
        return camType8;
    }

    public synchronized static void setChkNetBalance(boolean bal) {
        check_net_balance = bal;
    }

    public synchronized static boolean getChkNetBalance() {
        return check_net_balance;
    }

    public synchronized static void setNetBalanceStatusPresent(byte valid) {
        net_balance_status_present = valid;
    }

    public synchronized static byte getNetBalanceStatusPresent() {
        return net_balance_status_present;
    }

    public synchronized static void setChkNetBalCommand(String data) {
        check_net_balance_command = data;
    }

    public synchronized static String getChkNetBalCommand() {
        return check_net_balance_command;
    }

    public synchronized static void setChkNetBalDelimitter(String data) {
        check_net_balance_delimitter = data;
    }

    public synchronized static String getChkNetBalDelimitter() {
        return check_net_balance_delimitter;
    }

    //sim_type
    public synchronized static byte getSimType() {
        return sim_type;
    }

    public synchronized static void setSimType(byte data) {
        sim_type = data;
    }

    public synchronized static byte getSimRegistered() {
        return sim_register;
    }

    public synchronized static void setSimRegistered(byte data) {
        sim_register = data;
    }

    public synchronized static void setDiagDispForm(boolean val) {
        dispbrd_diag_form = val;
    }

    public synchronized static boolean getDiagDispForm() {
        return dispbrd_diag_form;
    }

    public synchronized static void setOverSpeedBuzzerEnabled(boolean val) {
        OverSpeedBuzzerEnabled = val;
    }

    public synchronized static boolean getOverSpeedBuzzerEnabled() {
        return OverSpeedBuzzerEnabled;
    }

    public synchronized static void setGprsRouteStartPktCame(boolean val) {
        gprs_route_start_pkt_came = val;
    }

    public synchronized static boolean getGprsRouteStartPktCame() {
        return gprs_route_start_pkt_came;
    }

    public synchronized static void setGprsRouteEndPktCame(boolean val) {
        gprs_route_end_pkt_came = val;
    }

    public synchronized static boolean getGprsRouteEndPktCame() {
        return gprs_route_end_pkt_came;
    }

    public synchronized static void setGprsRouteEndDateTime(long val) {
        gprs_route_end_date_time = val;
    }

    public synchronized static long getGprsRouteEndDateTime() {
        return gprs_route_end_date_time;
    }

    public synchronized static void setGprsRouteStartDateTime(long val) {
        gprs_route_start_date_time = val;
    }

    public synchronized static long getGprsRouteStartDateTime() {
        return gprs_route_start_date_time;
    }

    public synchronized static void setGprsRouteId(String val) {
        gprs_route_id = val;
    }

    public synchronized static String getGprsRouteId() {
        return gprs_route_id;
    }

    public synchronized static void setEventBasedRecordingStart(boolean val) {
        event_based_video_record_start = val;
    }

    public synchronized static boolean getEventBasedRecordingStart() {
        return event_based_video_record_start;
    }

    public synchronized static void setEventBasedRecording1Started(boolean val) {
        event_based_video_record_start1 = val;
    }

    public synchronized static boolean getEventBasedRecording1Started() {
        return event_based_video_record_start1;
    }

    public synchronized static void setEventBasedRecording2Started(boolean val) {
        event_based_video_record_start2 = val;
    }

    public synchronized static boolean getEventBasedRecording2Started() {
        return event_based_video_record_start2;
    }

    public synchronized static void setEventBasedRecording3Started(boolean val) {
        event_based_video_record_start3 = val;
    }

    public synchronized static boolean getEventBasedRecording3Started() {
        return event_based_video_record_start3;
    }

    public synchronized static void setEventBasedRecording4Started(boolean val) {
        event_based_video_record_start4 = val;
    }

    public synchronized static boolean getEventBasedRecording4Started() {
        return event_based_video_record_start4;
    }

    public synchronized static void setEventBasedRecording5Started(boolean val) {
        event_based_video_record_start5 = val;
    }

    public synchronized static boolean getEventBasedRecording5Started() {
        return event_based_video_record_start5;
    }

    public synchronized static void setEventBasedRecording6Started(boolean val) {
        event_based_video_record_start6 = val;
    }

    public synchronized static boolean getEventBasedRecording6Started() {
        return event_based_video_record_start6;
    }

    public synchronized static void setEventBasedRecording7Started(boolean val) {
        event_based_video_record_start7 = val;
    }

    public synchronized static boolean getEventBasedRecording7Started() {
        return event_based_video_record_start7;
    }

    public synchronized static void setEventBasedRecording8Started(boolean val) {
        event_based_video_record_start8 = val;
    }

    public synchronized static boolean getEventBasedRecording8Started() {
        return event_based_video_record_start8;
    }

    public synchronized static void setEventBasedRecordingEnd(boolean val) {
        event_based_video_record_end = val;
    }

    public synchronized static boolean GetEventBasedRecordingEnd() {
        return event_based_video_record_end;
    }

    public synchronized static void setCanBasedRecordingStart(boolean val) {
        can_based_video_record_start = val;
    }

    public synchronized static boolean getCanBasedRecordingStart() {
        return can_based_video_record_start;
    }

    public synchronized static void setCanBasedRecording1Started(boolean val) {
        can_based_video_record_start1 = val;
    }

    public synchronized static boolean getCanBasedRecording1Started() {
        return can_based_video_record_start1;
    }

    public synchronized static void setCanBasedRecording2Started(boolean val) {
        can_based_video_record_start2 = val;
    }

    public synchronized static boolean getCanBasedRecording2Started() {
        return can_based_video_record_start2;
    }

    public synchronized static void setCanBasedRecording3Started(boolean val) {
        can_based_video_record_start3 = val;
    }

    public synchronized static boolean getCanBasedRecording3Started() {
        return can_based_video_record_start3;
    }

    public synchronized static void setCanBasedRecording4Started(boolean val) {
        can_based_video_record_start4 = val;
    }

    public synchronized static boolean getCanBasedRecording4Started() {
        return can_based_video_record_start4;
    }

    public synchronized static void setCanBasedRecording5Started(boolean val) {
        can_based_video_record_start5 = val;
    }

    public synchronized static boolean getCanBasedRecording5Started() {
        return can_based_video_record_start5;
    }

    public synchronized static void setCanBasedRecording6Started(boolean val) {
        can_based_video_record_start6 = val;
    }

    public synchronized static boolean getCanBasedRecording6Started() {
        return can_based_video_record_start6;
    }

    public synchronized static void setCanBasedRecording7Started(boolean val) {
        can_based_video_record_start7 = val;
    }

    public synchronized static boolean getCanBasedRecording7Started() {
        return can_based_video_record_start7;
    }

    public synchronized static void setCanBasedRecording8Started(boolean val) {
        can_based_video_record_start8 = val;
    }

    public synchronized static boolean getCanBasedRecording8Started() {
        return can_based_video_record_start8;
    }

    public synchronized static void setCanBasedRecordingEnd(boolean val) {
        event_based_video_record_end = val;
    }

    public synchronized static boolean GetCanBasedRecordingEnd() {
        return event_based_video_record_end;
    }

    public synchronized static void setDeviceVehicleNo(String val) {
        device_vehicle_no = val;
    }

    public synchronized static String getDeviceVehicleNo() {
        return device_vehicle_no;
    }

    public synchronized static void setLastValidLocation(String val) {
        last_valid_location = val;
    }

    public synchronized static String getLastValidLocation() {
        return last_valid_location;
    }

    public synchronized static void setDigInp1Name(byte val) {
        digInput1Name = val;
    }

    public synchronized static byte getDigInp1Name() {
        return digInput1Name;
    }

    public synchronized static void setDigInp2Name(byte val) {
        digInput2Name = val;
    }

    public synchronized static byte getDigInp2Name() {
        return digInput2Name;
    }

    public synchronized static void setDigInp3Name(byte val) {
        digInput3Name = val;
    }

    public synchronized static byte getDigInp3Name() {
        return digInput3Name;
    }

    public synchronized static void setDigInp4Name(byte val) {
        digInput4Name = val;
    }

    public synchronized static byte getDigInp4Name() {
        return digInput4Name;
    }

    public synchronized static void setDigInp1Value(String val) {
        digInput1Value = val;
    }

    public synchronized static String getDigInp1Value() {
        return digInput1Value;
    }

    public synchronized static void setDigInp2Value(String val) {
        digInput2Value = val;
    }

    public synchronized static String getDigInp2Value() {
        return digInput2Value;
    }

    public synchronized static void setDigInp3Value(String val) {
        digInput3Value = val;
    }

    public synchronized static String getDigInp3Value() {
        return digInput3Value;
    }

    public synchronized static void setDigInp4Value(String val) {
        digInput4Value = val;
    }

    public synchronized static String getDigInp4Value() {
        return digInput4Value;
    }

    public synchronized static void setDigInp1DefaultClosedState(boolean val) {
        digInput1ClosedState = val;
    }

    public synchronized static boolean getDigInp1DefaultClosedState() {
        return digInput1ClosedState;
    }

    public synchronized static void setDigInp2DefaultClosedState(boolean val) {
        digInput2ClosedState = val;
    }

    public synchronized static boolean getDigInp2DefaultClosedState() {
        return digInput2ClosedState;
    }

    public synchronized static void setDigInp3DefaultClosedState(boolean val) {
        digInput3ClosedState = val;
    }

    public synchronized static boolean getDigInp3DefaultClosedState() {
        return digInput3ClosedState;
    }

    public synchronized static void setDigInp4DefaultClosedState(boolean val) {
        digInput4ClosedState = val;
    }

    public synchronized static boolean getDigInp4DefaultClosedState() {
        return digInput4ClosedState;
    }

    public synchronized static void setSelectCamera(String cam) {
        camera = cam;
    }

    public synchronized static String getSelectCamera() {
        return camera;
    }

    public synchronized static void setStreamtype(String stream) {
        streamtype = stream;
    }

    public synchronized static String getStreamtype() {
        return streamtype;
    }

    public synchronized static void setSubStreamtype(String stream) {
        substreamtype = stream;
    }

    public synchronized static String getSubStreamtype() {
        return substreamtype;
    }

    public synchronized static void setVideomode(String mode) {
        videomode = mode;
    }

    public synchronized static String getVideomode() {
        return videomode;
    }

    public synchronized static void setSubVideomode(String mode) {
        subvideomode = mode;
    }

    public synchronized static String getSubVideomode() {
        return subvideomode;
    }

    public synchronized static void setResolution(byte res) {
        resolution = res;
    }

    public synchronized static byte getResolution() {
        return resolution;
    }

    public synchronized static void setSubstreamResolution(byte res) {
        subresolution = res;
    }

    public synchronized static byte getSubstreamResolution() {
        return subresolution;
    }

    public synchronized static void setBitratetype(String type) {
        bitratetype = type;
    }

    public synchronized static String getBitratetype() {
        return bitratetype;
    }

    public synchronized static void setSubBitratetype(String type) {
        subbitratetype = type;
    }

    public synchronized static String getSubBitratetype() {
        return subbitratetype;
    }

    public synchronized static void setVideoquality(byte quality) {
        videoquality = quality;
    }

    public synchronized static byte getVideoquality() {
        return videoquality;
    }

    public synchronized static void setSubVideoquality(byte quality) {
        subvideoquality = quality;
    }

    public synchronized static byte getSubVideoquality() {
        return subvideoquality;
    }

    public synchronized static void setFramerate(int val) {
        framerate = val;
    }

    public synchronized static int getFramerate() {
        return framerate;
    }

    public synchronized static void setFrameInterval(int val) {
        frameInterval = val;
    }

    public synchronized static int getFrameInterval() {
        return frameInterval;
    }

    public synchronized static void setSubFramerate(int val) {
        subframerate = val;
    }

    public synchronized static int getSubFramerate() {
        return subframerate;
    }

    public synchronized static void setSubFrameInterval(int val) {
        subFrameInterval = val;
    }

    public synchronized static int getSubFrameInterval() {
        return subFrameInterval;
    }

    public synchronized static void setbitrate(int val) {
        bitrate = val;
    }

    public synchronized static int getbitrate() {
        return bitrate;
    }

    public synchronized static void setMaxbitrate(int val) {
        maxbitrate = val;
    }

    public synchronized static int getMaxbitrate() {
        return maxbitrate;
    }

    public synchronized static void setSubbitrate(int val) {
        subbitrate = val;
    }

    public synchronized static int getSubbitrate() {
        return subbitrate;
    }

    public synchronized static void setSubMaxbitrate(int val) {
        submaxbitrate = val;
    }

    public synchronized static int getSubMaxbitrate() {
        return submaxbitrate;
    }

    public synchronized static void setVideoEncode(String val) {
        videoencode = val;
    }

    public synchronized static String getVideoEncode() {
        return videoencode;

    }

    public synchronized static void setSubVideoEncode(String val) {
        subvideoencode = val;
    }

    public synchronized static String getSubVideoEncode() {
        return subvideoencode;

    }

    public synchronized static void setSelectCamera2(String cam) {
        camera2 = cam;
    }

    public synchronized static String getSelectCamera2() {
        return camera2;
    }

    public synchronized static void setStreamtype2(String stream) {
        streamtype2 = stream;
    }

    public synchronized static String getStreamtype2() {
        return streamtype2;
    }

    public synchronized static void setSubStreamtype2(String stream) {
        substreamtype2 = stream;
    }

    public synchronized static String getSubStreamtype2() {
        return substreamtype2;
    }

    public synchronized static void setVideomode2(String mode) {
        videomode2 = mode;
    }

    public synchronized static String getVideomode2() {
        return videomode2;
    }

    public synchronized static void setSubVideomode2(String mode) {
        subvideomode2 = mode;
    }

    public synchronized static String getSubVideomode2() {
        return subvideomode2;
    }

    public synchronized static void setResolution2(byte res) {
        resolution2 = res;
    }

    public synchronized static byte getResolution2() {
        return resolution2;
    }

    public synchronized static void setSubstreamResolution2(byte res) {
        subresolution2 = res;
    }

    public synchronized static byte getSubstreamResolution2() {
        return subresolution2;
    }

    public synchronized static void setBitratetype2(String type) {
        bitratetype2 = type;
    }

    public synchronized static String getBitratetype2() {
        return bitratetype2;
    }

    public synchronized static void setSubBitratetype2(String type) {
        subbitratetype2 = type;
    }

    public synchronized static String getSubBitratetype2() {
        return subbitratetype2;
    }

    public synchronized static void setVideoquality2(byte quality) {
        videoquality2 = quality;
    }

    public synchronized static byte getVideoquality2() {
        return videoquality2;
    }

    public synchronized static void setSubVideoquality2(byte quality) {
        subvideoquality2 = quality;
    }

    public synchronized static byte getSubVideoquality2() {
        return subvideoquality2;
    }

    public synchronized static void setFramerate2(int val) {
        framerate2 = val;
    }

    public synchronized static int getFramerate2() {
        return framerate2;
    }

    public synchronized static void setFrameInterval2(int val) {
        frameInterval2 = val;
    }

    public synchronized static int getFrameInterval2() {
        return frameInterval2;
    }

    public synchronized static void setSubFramerate2(int val) {
        subframerate2 = val;
    }

    public synchronized static int getSubFramerate2() {
        return subframerate2;
    }

    public synchronized static void setSubFrameInterval2(int val) {
        subFrameInterval2 = val;
    }

    public synchronized static int getSubFrameInterval2() {
        return subFrameInterval2;
    }

    public synchronized static void setbitrate2(int val) {
        bitrate2 = val;
    }

    public synchronized static int getbitrate2() {
        return bitrate2;
    }

    public synchronized static void setMaxbitrate2(int val) {
        maxbitrate2 = val;
    }

    public synchronized static int getMaxbitrate2() {
        return maxbitrate2;
    }

    public synchronized static void setSubbitrate2(int val) {
        subbitrate2 = val;
    }

    public synchronized static int getSubbitrate2() {
        return subbitrate2;
    }

    public synchronized static void setSubMaxbitrate2(int val) {
        submaxbitrate2 = val;
    }

    public synchronized static int getSubMaxbitrate2() {
        return submaxbitrate2;
    }

    public synchronized static void setVideoEncode2(String val) {
        videoencode2 = val;
    }

    public synchronized static String getVideoEncode2() {
        return videoencode2;

    }

    public synchronized static void setSubVideoEncode2(String val) {
        subvideoencode2 = val;
    }

    public synchronized static String getSubVideoEncode2() {
        return subvideoencode2;

    }

    public synchronized static void setSelectCamera3(String cam) {
        camera3 = cam;
    }

    public synchronized static String getSelectCamera3() {
        return camera3;
    }

    public synchronized static void setStreamtype3(String stream) {
        streamtype3 = stream;
    }

    public synchronized static String getStreamtype3() {
        return streamtype3;
    }

    public synchronized static void setSubStreamtype3(String stream) {
        substreamtype3 = stream;
    }

    public synchronized static String getSubStreamtype3() {
        return substreamtype3;
    }

    public synchronized static void setVideomode3(String mode) {
        videomode3 = mode;
    }

    public synchronized static String getVideomode3() {
        return videomode3;
    }

    public synchronized static void setSubVideomode3(String mode) {
        subvideomode3 = mode;
    }

    public synchronized static String getSubVideomode3() {
        return subvideomode3;
    }

    public synchronized static void setResolution3(byte res) {
        resolution3 = res;
    }

    public synchronized static byte getResolution3() {
        return resolution3;
    }

    public synchronized static void setSubstreamResolution3(byte res) {
        subresolution3 = res;
    }

    public synchronized static byte getSubstreamResolution3() {
        return subresolution3;
    }

    public synchronized static void setBitratetype3(String type) {
        bitratetype3 = type;
    }

    public synchronized static String getBitratetype3() {
        return bitratetype3;
    }

    public synchronized static void setSubBitratetype3(String type) {
        subbitratetype3 = type;
    }

    public synchronized static String getSubBitratetype3() {
        return subbitratetype3;
    }

    public synchronized static void setVideoquality3(byte quality) {
        videoquality3 = quality;
    }

    public synchronized static byte getVideoquality3() {
        return videoquality3;
    }

    public synchronized static void setSubVideoquality3(byte quality) {
        subvideoquality3 = quality;
    }

    public synchronized static byte getSubVideoquality3() {
        return subvideoquality3;
    }

    public synchronized static void setFramerate3(int val) {
        framerate3 = val;
    }

    public synchronized static int getFramerate3() {
        return framerate3;
    }

    public synchronized static void setFrameInterval3(int val) {
        frameInterval3 = val;
    }

    public synchronized static int getFrameInterval3() {
        return frameInterval3;
    }

    public synchronized static void setSubFramerate3(int val) {
        subframerate3 = val;
    }

    public synchronized static int getSubFramerate3() {
        return subframerate3;
    }

    public synchronized static void setSubFrameInterval3(int val) {
        subFrameInterval3 = val;
    }

    public synchronized static int getSubFrameInterval3() {
        return subFrameInterval3;
    }

    public synchronized static void setbitrate3(int val) {
        bitrate3 = val;
    }

    public synchronized static int getbitrate3() {
        return bitrate3;
    }

    public synchronized static void setMaxbitrate3(int val) {
        maxbitrate3 = val;
    }

    public synchronized static int getMaxbitrate3() {
        return maxbitrate3;
    }

    public synchronized static void setSubbitrate3(int val) {
        subbitrate3 = val;
    }

    public synchronized static int getSubbitrate3() {
        return subbitrate3;
    }

    public synchronized static void setSubMaxbitrate3(int val) {
        submaxbitrate3 = val;
    }

    public synchronized static int getSubMaxbitrate3() {
        return submaxbitrate3;
    }

    public synchronized static void setVideoEncode3(String val) {
        videoencode3 = val;
    }

    public synchronized static String getVideoEncode3() {
        return videoencode3;

    }

    public synchronized static void setSubVideoEncode3(String val) {
        subvideoencode3 = val;
    }

    public synchronized static String getSubVideoEncode3() {
        return subvideoencode3;

    }

    public synchronized static void setSelectCamera4(String cam) {
        camera4 = cam;
    }

    public synchronized static String getSelectCamera4() {
        return camera4;
    }

    public synchronized static void setStreamtype4(String stream) {
        streamtype4 = stream;
    }

    public synchronized static String getStreamtype4() {
        return streamtype4;
    }

    public synchronized static void setSubStreamtype4(String stream) {
        substreamtype4 = stream;
    }

    public synchronized static String getSubStreamtype4() {
        return substreamtype4;
    }

    public synchronized static void setVideomode4(String mode) {
        videomode4 = mode;
    }

    public synchronized static String getVideomode4() {
        return videomode4;
    }

    public synchronized static void setSubVideomode4(String mode) {
        subvideomode4 = mode;
    }

    public synchronized static String getSubVideomode4() {
        return subvideomode4;
    }

    public synchronized static void setResolution4(byte res) {
        resolution4 = res;
    }

    public synchronized static byte getResolution4() {
        return resolution4;
    }

    public synchronized static void setSubstreamResolution4(byte res) {
        subresolution4 = res;
    }

    public synchronized static byte getSubstreamResolution4() {
        return subresolution4;
    }

    public synchronized static void setBitratetype4(String type) {
        bitratetype4 = type;
    }

    public synchronized static String getBitratetype4() {
        return bitratetype4;
    }

    public synchronized static void setSubBitratetype4(String type) {
        subbitratetype4 = type;
    }

    public synchronized static String getSubBitratetype4() {
        return subbitratetype4;
    }

    public synchronized static void setVideoquality4(byte quality) {
        videoquality4 = quality;
    }

    public synchronized static byte getVideoquality4() {
        return videoquality4;
    }

    public synchronized static void setSubVideoquality4(byte quality) {
        subvideoquality4 = quality;
    }

    public synchronized static byte getSubVideoquality4() {
        return subvideoquality4;
    }

    public synchronized static void setFramerate4(int val) {
        framerate4 = val;
    }

    public synchronized static int getFramerate4() {
        return framerate4;
    }

    public synchronized static void setFrameInterval4(int val) {
        frameInterval4 = val;
    }

    public synchronized static int getFrameInterval4() {
        return frameInterval4;
    }

    public synchronized static void setSubFramerate4(int val) {
        subframerate4 = val;
    }

    public synchronized static int getSubFramerate4() {
        return subframerate4;
    }

    public synchronized static void setSubFrameInterval4(int val) {
        subFrameInterval4 = val;
    }

    public synchronized static int getSubFrameInterval4() {
        return subFrameInterval4;
    }

    public synchronized static void setbitrate4(int val) {
        bitrate4 = val;
    }

    public synchronized static int getbitrate4() {
        return bitrate4;
    }

    public synchronized static void setMaxbitrate4(int val) {
        maxbitrate4 = val;
    }

    public synchronized static int getMaxbitrate4() {
        return maxbitrate4;
    }

    public synchronized static void setSubbitrate4(int val) {
        subbitrate4 = val;
    }

    public synchronized static int getSubbitrate4() {
        return subbitrate4;
    }

    public synchronized static void setSubMaxbitrate4(int val) {
        submaxbitrate4 = val;
    }

    public synchronized static int getSubMaxbitrate4() {
        return submaxbitrate4;
    }

    public synchronized static void setVideoEncode4(String val) {
        videoencode4 = val;
    }

    public synchronized static String getVideoEncode4() {
        return videoencode4;

    }

    public synchronized static void setSubVideoEncode4(String val) {
        subvideoencode4 = val;
    }

    public synchronized static String getSubVideoEncode4() {
        return subvideoencode4;
    }

    public synchronized static void setSelectCamera5(String cam) {
        camera5 = cam;
    }

    public synchronized static String getSelectCamera5() {
        return camera5;
    }

    public synchronized static void setStreamtype5(String stream) {
        streamtype5 = stream;
    }

    public synchronized static String getStreamtype5() {
        return streamtype5;
    }

    public synchronized static void setSubStreamtype5(String stream) {
        substreamtype5 = stream;
    }

    public synchronized static String getSubStreamtype5() {
        return substreamtype5;
    }

    public synchronized static void setVideomode5(String mode) {
        videomode5 = mode;
    }

    public synchronized static String getVideomode5() {
        return videomode5;
    }

    public synchronized static void setSubVideomode5(String mode) {
        subvideomode5 = mode;
    }

    public synchronized static String getSubVideomode5() {
        return subvideomode5;
    }

    public synchronized static void setResolution5(byte res) {
        resolution5 = res;
    }

    public synchronized static byte getResolution5() {
        return resolution5;
    }

    public synchronized static void setSubstreamResolution5(byte res) {
        subresolution5 = res;
    }

    public synchronized static byte getSubstreamResolution5() {
        return subresolution5;
    }

    public synchronized static void setBitratetype5(String type) {
        bitratetype5 = type;
    }

    public synchronized static String getBitratetype5() {
        return bitratetype5;
    }

    public synchronized static void setSubBitratetype5(String type) {
        subbitratetype5 = type;
    }

    public synchronized static String getSubBitratetype5() {
        return subbitratetype5;
    }

    public synchronized static void setVideoquality5(byte quality) {
        videoquality5 = quality;
    }

    public synchronized static byte getVideoquality5() {
        return videoquality5;
    }

    public synchronized static void setSubVideoquality5(byte quality) {
        subvideoquality5 = quality;
    }

    public synchronized static byte getSubVideoquality5() {
        return subvideoquality5;
    }

    public synchronized static void setFramerate5(int val) {
        framerate5 = val;
    }

    public synchronized static int getFramerate5() {
        return framerate5;
    }

    public synchronized static void setFrameInterval5(int val) {
        frameInterval5 = val;
    }

    public synchronized static int getFrameInterval5() {
        return frameInterval5;
    }

    public synchronized static void setSubFramerate5(int val) {
        subframerate5 = val;
    }

    public synchronized static int getSubFramerate5() {
        return subframerate5;
    }

    public synchronized static void setSubFrameInterval5(int val) {
        subFrameInterval5 = val;
    }

    public synchronized static int getSubFrameInterval5() {
        return subFrameInterval5;
    }

    public synchronized static void setbitrate5(int val) {
        bitrate5 = val;
    }

    public synchronized static int getbitrate5() {
        return bitrate5;
    }

    public synchronized static void setMaxbitrate5(int val) {
        maxbitrate5 = val;
    }

    public synchronized static int getMaxbitrate5() {
        return maxbitrate5;
    }

    public synchronized static void setSubbitrate5(int val) {
        subbitrate5 = val;
    }

    public synchronized static int getSubbitrate5() {
        return subbitrate5;
    }

    public synchronized static void setSubMaxbitrate5(int val) {
        submaxbitrate5 = val;
    }

    public synchronized static int getSubMaxbitrate5() {
        return submaxbitrate5;
    }

    public synchronized static void setVideoEncode5(String val) {
        videoencode5 = val;
    }

    public synchronized static String getVideoEncode5() {
        return videoencode5;

    }

    public synchronized static void setSubVideoEncode5(String val) {
        subvideoencode5 = val;
    }

    public synchronized static String getSubVideoEncode5() {
        return subvideoencode5;
    }

    public synchronized static void setSelectCamera6(String cam) {
        camera6 = cam;
    }

    public synchronized static String getSelectCamera6() {
        return camera6;
    }

    public synchronized static void setStreamtype6(String stream) {
        streamtype6 = stream;
    }

    public synchronized static String getStreamtype6() {
        return streamtype6;
    }

    public synchronized static void setSubStreamtype6(String stream) {
        substreamtype6 = stream;
    }

    public synchronized static String getSubStreamtype6() {
        return substreamtype6;
    }

    public synchronized static void setVideomode6(String mode) {
        videomode6 = mode;
    }

    public synchronized static String getVideomode6() {
        return videomode6;
    }

    public synchronized static void setSubVideomode6(String mode) {
        subvideomode6 = mode;
    }

    public synchronized static String getSubVideomode6() {
        return subvideomode6;
    }

    public synchronized static void setResolution6(byte res) {
        resolution6 = res;
    }

    public synchronized static byte getResolution6() {
        return resolution6;
    }

    public synchronized static void setSubstreamResolution6(byte res) {
        subresolution6 = res;
    }

    public synchronized static byte getSubstreamResolution6() {
        return subresolution6;
    }

    public synchronized static void setBitratetype6(String type) {
        bitratetype6 = type;
    }

    public synchronized static String getBitratetype6() {
        return bitratetype6;
    }

    public synchronized static void setSubBitratetype6(String type) {
        subbitratetype6 = type;
    }

    public synchronized static String getSubBitratetype6() {
        return subbitratetype6;
    }

    public synchronized static void setVideoquality6(byte quality) {
        videoquality6 = quality;
    }

    public synchronized static byte getVideoquality6() {
        return videoquality6;
    }

    public synchronized static void setSubVideoquality6(byte quality) {
        subvideoquality6 = quality;
    }

    public synchronized static byte getSubVideoquality6() {
        return subvideoquality6;
    }

    public synchronized static void setFramerate6(int val) {
        framerate6 = val;
    }

    public synchronized static int getFramerate6() {
        return framerate6;
    }

    public synchronized static void setFrameInterval6(int val) {
        frameInterval6 = val;
    }

    public synchronized static int getFrameInterval6() {
        return frameInterval6;
    }

    public synchronized static void setSubFramerate6(int val) {
        subframerate6 = val;
    }

    public synchronized static int getSubFramerate6() {
        return subframerate6;
    }

    public synchronized static void setSubFrameInterval6(int val) {
        subFrameInterval6 = val;
    }

    public synchronized static int getSubFrameInterval6() {
        return subFrameInterval6;
    }

    public synchronized static void setbitrate6(int val) {
        bitrate6 = val;
    }

    public synchronized static int getbitrate6() {
        return bitrate6;
    }

    public synchronized static void setMaxbitrate6(int val) {
        maxbitrate6 = val;
    }

    public synchronized static int getMaxbitrate6() {
        return maxbitrate6;
    }

    public synchronized static void setSubbitrate6(int val) {
        subbitrate6 = val;
    }

    public synchronized static int getSubbitrate6() {
        return subbitrate6;
    }

    public synchronized static void setSubMaxbitrate6(int val) {
        submaxbitrate6 = val;
    }

    public synchronized static int getSubMaxbitrate6() {
        return submaxbitrate6;
    }

    public synchronized static void setVideoEncode6(String val) {
        videoencode6 = val;
    }

    public synchronized static String getVideoEncode6() {
        return videoencode6;

    }

    public synchronized static void setSubVideoEncode6(String val) {
        subvideoencode6 = val;
    }

    public synchronized static String getSubVideoEncode6() {
        return subvideoencode6;
    }

    public synchronized static void setSelectCamera7(String cam) {
        camera7 = cam;
    }

    public synchronized static String getSelectCamera7() {
        return camera7;
    }

    public synchronized static void setStreamtype7(String stream) {
        streamtype7 = stream;
    }

    public synchronized static String getStreamtype7() {
        return streamtype7;
    }

    public synchronized static void setSubStreamtype7(String stream) {
        substreamtype7 = stream;
    }

    public synchronized static String getSubStreamtype7() {
        return substreamtype7;
    }

    public synchronized static void setVideomode7(String mode) {
        videomode7 = mode;
    }

    public synchronized static String getVideomode7() {
        return videomode7;
    }

    public synchronized static void setSubVideomode7(String mode) {
        subvideomode7 = mode;
    }

    public synchronized static String getSubVideomode7() {
        return subvideomode7;
    }

    public synchronized static void setResolution7(byte res) {
        resolution7 = res;
    }

    public synchronized static byte getResolution7() {
        return resolution7;
    }

    public synchronized static void setSubstreamResolution7(byte res) {
        subresolution7 = res;
    }

    public synchronized static byte getSubstreamResolution7() {
        return subresolution7;
    }

    public synchronized static void setBitratetype7(String type) {
        bitratetype7 = type;
    }

    public synchronized static String getBitratetype7() {
        return bitratetype7;
    }

    public synchronized static void setSubBitratetype7(String type) {
        subbitratetype7 = type;
    }

    public synchronized static String getSubBitratetype7() {
        return subbitratetype7;
    }

    public synchronized static void setVideoquality7(byte quality) {
        videoquality7 = quality;
    }

    public synchronized static byte getVideoquality7() {
        return videoquality7;
    }

    public synchronized static void setSubVideoquality7(byte quality) {
        subvideoquality7 = quality;
    }

    public synchronized static byte getSubVideoquality7() {
        return subvideoquality7;
    }

    public synchronized static void setFramerate7(int val) {
        framerate7 = val;
    }

    public synchronized static int getFramerate7() {
        return framerate7;
    }

    public synchronized static void setFrameInterval7(int val) {
        frameInterval7 = val;
    }

    public synchronized static int getFrameInterval7() {
        return frameInterval7;
    }

    public synchronized static void setSubFramerate7(int val) {
        subframerate7 = val;
    }

    public synchronized static int getSubFramerate7() {
        return subframerate7;
    }

    public synchronized static void setSubFrameInterval7(int val) {
        subFrameInterval7 = val;
    }

    public synchronized static int getSubFrameInterval7() {
        return subFrameInterval7;
    }

    public synchronized static void setbitrate7(int val) {
        bitrate7 = val;
    }

    public synchronized static int getbitrate7() {
        return bitrate7;
    }

    public synchronized static void setMaxbitrate7(int val) {
        maxbitrate7 = val;
    }

    public synchronized static int getMaxbitrate7() {
        return maxbitrate7;
    }

    public synchronized static void setSubbitrate7(int val) {
        subbitrate7 = val;
    }

    public synchronized static int getSubbitrate7() {
        return subbitrate7;
    }

    public synchronized static void setSubMaxbitrate7(int val) {
        submaxbitrate7 = val;
    }

    public synchronized static int getSubMaxbitrate7() {
        return submaxbitrate7;
    }

    public synchronized static void setVideoEncode7(String val) {
        videoencode7 = val;
    }

    public synchronized static String getVideoEncode7() {
        return videoencode7;

    }

    public synchronized static void setSubVideoEncode7(String val) {
        subvideoencode7 = val;
    }

    public synchronized static String getSubVideoEncode7() {
        return subvideoencode7;
    }

    public synchronized static void setSelectCamera8(String cam) {
        camera8 = cam;
    }

    public synchronized static String getSelectCamera8() {
        return camera8;
    }

    public synchronized static void setStreamtype8(String stream) {
        streamtype8 = stream;
    }

    public synchronized static String getStreamtype8() {
        return streamtype8;
    }

    public synchronized static void setSubStreamtype8(String stream) {
        substreamtype8 = stream;
    }

    public synchronized static String getSubStreamtype8() {
        return substreamtype8;
    }

    public synchronized static void setVideomode8(String mode) {
        videomode8 = mode;
    }

    public synchronized static String getVideomode8() {
        return videomode8;
    }

    public synchronized static void setSubVideomode8(String mode) {
        subvideomode8 = mode;
    }

    public synchronized static String getSubVideomode8() {
        return subvideomode8;
    }

    public synchronized static void setResolution8(byte res) {
        resolution8 = res;
    }

    public synchronized static byte getResolution8() {
        return resolution8;
    }

    public synchronized static void setSubstreamResolution8(byte res) {
        subresolution8 = res;
    }

    public synchronized static byte getSubstreamResolution8() {
        return subresolution8;
    }

    public synchronized static void setBitratetype8(String type) {
        bitratetype8 = type;
    }

    public synchronized static String getBitratetype8() {
        return bitratetype8;
    }

    public synchronized static void setSubBitratetype8(String type) {
        subbitratetype8 = type;
    }

    public synchronized static String getSubBitratetype8() {
        return subbitratetype8;
    }

    public synchronized static void setVideoquality8(byte quality) {
        videoquality8 = quality;
    }

    public synchronized static byte getVideoquality8() {
        return videoquality8;
    }

    public synchronized static void setSubVideoquality8(byte quality) {
        subvideoquality8 = quality;
    }

    public synchronized static byte getSubVideoquality8() {
        return subvideoquality8;
    }

    public synchronized static void setFramerate8(int val) {
        framerate8 = val;
    }

    public synchronized static int getFramerate8() {
        return framerate8;
    }

    public synchronized static void setFrameInterval8(int val) {
        frameInterval8 = val;
    }

    public synchronized static int getFrameInterval8() {
        return frameInterval8;
    }

    public synchronized static void setSubFramerate8(int val) {
        subframerate8 = val;
    }

    public synchronized static int getSubFramerate8() {
        return subframerate8;
    }

    public synchronized static void setSubFrameInterval8(int val) {
        subFrameInterval8 = val;
    }

    public synchronized static int getSubFrameInterval8() {
        return subFrameInterval8;
    }

    public synchronized static void setbitrate8(int val) {
        bitrate8 = val;
    }

    public synchronized static int getbitrate8() {
        return bitrate8;
    }

    public synchronized static void setMaxbitrate8(int val) {
        maxbitrate8 = val;
    }

    public synchronized static int getMaxbitrate8() {
        return maxbitrate8;
    }

    public synchronized static void setSubbitrate8(int val) {
        subbitrate8 = val;
    }

    public synchronized static int getSubbitrate8() {
        return subbitrate8;
    }

    public synchronized static void setSubMaxbitrate8(int val) {
        submaxbitrate8 = val;
    }

    public synchronized static int getSubMaxbitrate8() {
        return submaxbitrate8;
    }

    public synchronized static void setVideoEncode8(String val) {
        videoencode8 = val;
    }

    public synchronized static String getVideoEncode8() {
        return videoencode8;

    }

    public synchronized static void setSubVideoEncode8(String val) {
        subvideoencode8 = val;
    }

    public synchronized static String getSubVideoEncode8() {
        return subvideoencode8;
    }

    public synchronized static byte getBrightnessLevel() {
        return brightness_level;
    }

    public static synchronized void setBrightnessLevel(byte val) {
        brightness_level = val;
    }

    public synchronized static byte getSaturationLevel() {
        return saturation_level;
    }

    public static synchronized void setSaturationLevel(byte val) {
        saturation_level = val;
    }

    public synchronized static byte getContrastLevel() {
        return contrast_level;
    }

    public static synchronized void setContrastLevel(byte val) {
        contrast_level = val;
    }

    public synchronized static byte getBrightnessLevelCam2() {
        return brightness_level2;
    }

    public static synchronized void setBrightnessLevelCam2(byte val) {
        brightness_level2 = val;
    }

    public synchronized static byte getSaturationLevelCam2() {
        return saturation_level2;
    }

    public static synchronized void setSaturationLevelCam2(byte val) {
        saturation_level2 = val;
    }

    public synchronized static byte getContrastLevelCam2() {
        return contrast_level2;
    }

    public static synchronized void setContrastLevelCam2(byte val) {
        contrast_level2 = val;
    }

    public synchronized static byte getBrightnessLevelCam3() {
        return brightness_level3;
    }

    public static synchronized void setBrightnessLevelCam3(byte val) {
        brightness_level3 = val;
    }

    public synchronized static byte getSaturationLevelCam3() {
        return saturation_level3;
    }

    public static synchronized void setSaturationLevelCam3(byte val) {
        saturation_level3 = val;
    }

    public synchronized static byte getContrastLevelCam3() {
        return contrast_level3;
    }

    public static synchronized void setContrastLevelCam3(byte val) {
        contrast_level3 = val;
    }

    public synchronized static byte getBrightnessLevelCam4() {
        return brightness_level4;
    }

    public static synchronized void setBrightnessLevelCam4(byte val) {
        brightness_level4 = val;
    }

    public synchronized static byte getSaturationLevelCam4() {
        return saturation_level4;
    }

    public static synchronized void setSaturationLevelCam4(byte val) {
        saturation_level4 = val;
    }

    public synchronized static byte getContrastLevelCam4() {
        return contrast_level4;
    }

    public static synchronized void setContrastLevelCam4(byte val) {
        contrast_level4 = val;
    }

    public synchronized static byte getBrightnessLevelCam5() {
        return brightness_level5;
    }

    public static synchronized void setBrightnessLevelCam5(byte val) {
        brightness_level5 = val;
    }

    public synchronized static byte getSaturationLevelCam5() {
        return saturation_level5;
    }

    public static synchronized void setSaturationLevelCam5(byte val) {
        saturation_level5 = val;
    }

    public synchronized static byte getContrastLevelCam5() {
        return contrast_level5;
    }

    public static synchronized void setContrastLevelCam5(byte val) {
        contrast_level5 = val;
    }

    public synchronized static byte getBrightnessLevelCam6() {
        return brightness_level6;
    }

    public static synchronized void setBrightnessLevelCam6(byte val) {
        brightness_level6 = val;
    }

    public synchronized static byte getSaturationLevelCam6() {
        return saturation_level6;
    }

    public static synchronized void setSaturationLevelCam6(byte val) {
        saturation_level6 = val;
    }

    public synchronized static byte getContrastLevelCam6() {
        return contrast_level6;
    }

    public static synchronized void setContrastLevelCam6(byte val) {
        contrast_level6 = val;
    }

    public synchronized static byte getBrightnessLevelCam7() {
        return brightness_level7;
    }

    public static synchronized void setBrightnessLevelCam7(byte val) {
        brightness_level7 = val;
    }

    public synchronized static byte getSaturationLevelCam7() {
        return saturation_level7;
    }

    public static synchronized void setSaturationLevelCam7(byte val) {
        saturation_level7 = val;
    }

    public synchronized static byte getContrastLevelCam7() {
        return contrast_level7;
    }

    public static synchronized void setContrastLevelCam7(byte val) {
        contrast_level7 = val;
    }

    public synchronized static byte getBrightnessLevelCam8() {
        return brightness_level8;
    }

    public static synchronized void setBrightnessLevelCam8(byte val) {
        brightness_level8 = val;
    }

    public synchronized static byte getSaturationLevelCam8() {
        return saturation_level8;
    }

    public static synchronized void setSaturationLevelCam8(byte val) {
        saturation_level8 = val;
    }

    public synchronized static byte getContrastLevelCam8() {
        return contrast_level8;
    }

    public static synchronized void setContrastLevelCam8(byte val) {
        contrast_level8 = val;
    }

    public synchronized static boolean getRs232Enable() {
        return rs232_enable;
    }

    public static synchronized void setRs232Enable(boolean val) {
        rs232_enable = val;
    }

    public synchronized static byte getRs232Type() {
        return rs232_type;
    }

    public static synchronized void setRs232Type(byte val) {
        rs232_type = val;
    }

    public synchronized static int getRs232BaudRate() {
        return rs232_baudrate;
    }

    public static synchronized void setRs232BaudRate(int val) {
        rs232_baudrate = val;
    }

    public synchronized static int getRs232GpsModeInterval() {
        return rs232_mode_interval;
    }

    public static synchronized void setRs232GpsModeInterval(int val) {
        rs232_mode_interval = val;
    }
//ETHERET

    public synchronized static boolean getEthernetEnable() {
        return ethernet_enable;
    }

    public static synchronized void setEthernetEnable(boolean val) {
        ethernet_enable = val;
    }

    public synchronized static byte getEthernetType() {
        return ethernet_type;
    }

    public static synchronized void setEthernetType(byte val) {
        ethernet_type = val;
    }

    public synchronized static byte getLiveType() {
        return live_type;
    }

    public static synchronized void setLiveType(byte val) {
        live_type = val;
    }

    public synchronized static int getEthernetPortNo() {
        return ethernet_portno;
    }

    public static synchronized void setEthernetPortNo(int val) {
        ethernet_portno = val;
    }

    public synchronized static String getEthernetIpAddr() {
        return ethernet_ipaddr;
    }

    public static synchronized void setEthernetIpAddr(String val) {
        ethernet_ipaddr = val;
    }

    public synchronized static int getEthernetGpsModeInterval() {
        return ethernet_mode_interval;
    }

    public static synchronized void setEthernetGpsModeInterval(int val) {
        ethernet_mode_interval = val;
    }

    public synchronized static byte getDigOut1Value() {
        return dig1_out_value;
    }

    public static synchronized void setDigOut1Value(byte val) {
        dig1_out_value = val;
    }

    public synchronized static byte getDigOut2Value() {
        return dig2_out_value;
    }

    public static synchronized void setDigOut2Value(byte val) {
        dig2_out_value = val;
    }

    public synchronized static byte getDigOut3Value() {
        return dig3_out_value;
    }

    public static synchronized void setDigOut3Value(byte val) {
        dig3_out_value = val;
    }

    public synchronized static byte getDigOut4Value() {
        return dig4_out_value;
    }

    public static synchronized void setDigOut4Value(byte val) {
        dig4_out_value = val;
    }

    public synchronized static boolean getDigOutPktCame() {
        return dig_out_pkt_came;
    }

    public static synchronized void setDigOutPktCame(boolean val) {
        dig_out_pkt_came = val;
    }

    /*   public synchronized static boolean getDigOutPktCame1() {
        return dig_out_pkt_came1;
    }

    public static synchronized void setDigOutPktCame1(boolean val) {
        dig_out_pkt_came1 = val;
    }*/
    public synchronized static byte getUsbStatEnableDisable() {
        return usb_stat_enable_disable;
    }

    public static synchronized void setUsbStatEnableDisable(byte val) {
        usb_stat_enable_disable = val;
    }

    public synchronized static byte getHdStatEnableDisable() {
        return hd_stat_enable_disable;
    }

    public static synchronized void setHdStatEnableDisable(byte val) {
        hd_stat_enable_disable = val;
    }

    public synchronized static boolean getUsbHdPktCame() {
        return usb_hd_pkt_came;
    }

    public static synchronized void setUsbHdPktCame(boolean val) {
        usb_hd_pkt_came = val;
    }

    /*  public synchronized static boolean getUsbHdPktCame1() {
        return usb_hd_pkt_came1;
    }

    public static synchronized void setUsbHdPktCame1(boolean val) {
        usb_hd_pkt_came1 = val;
    }*/
    public synchronized static boolean getUsbHdPktResCame() {
        return usb_hd_pkt_res_came;
    }

    public static synchronized void setUsbHdPktResCame(boolean val) {
        usb_hd_pkt_res_came = val;
    }

    /*  public synchronized static boolean getUsbHdPktResCam1() {
        return usb_hd_pkt_res_came1;
    }

    public static synchronized void setUsbHdPktResCame1(boolean val) {
        usb_hd_pkt_res_came1 = val;
    }*/
    //camera1 snap shot/event
    public synchronized static boolean getSnapShotEnableCam1() {
        return snapshot_enable_cam1;
    }

    public static synchronized void setSnapShotEnableCam1(boolean val) {
        snapshot_enable_cam1 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam1() {
        return event_based_rec_enable_cam1;
    }

    public static synchronized void setEventBasedRecEnableCam1(boolean val) {
        event_based_rec_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam1() {
        return snap_shot_event_enable_cam1;
    }

    public static synchronized void setSnapShotEventEnableCam1(boolean val) {
        snap_shot_event_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam1() {
        return snap_shot_cont_enable_cam1;
    }

    public static synchronized void setSnapShotContEnableCam1(boolean val) {
        snap_shot_cont_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam1() {
        return snap_shot_cont_ftpupload_enable_cam1;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam1(boolean val) {
        snap_shot_cont_ftpupload_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig1EnableCam1() {
        return snap_shot_dig1_enable_cam1;
    }

    public static synchronized void setSnapShotDig1EnableCam1(boolean val) {
        snap_shot_dig1_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam1() {
        return snap_shot_dig2_enable_cam1;
    }

    public static synchronized void setSnapShotDig2EnableCam1(boolean val) {
        snap_shot_dig2_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam1() {
        return snap_shot_dig3_enable_cam1;
    }

    public static synchronized void setSnapShotDig3EnableCam1(boolean val) {
        snap_shot_dig3_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam1() {
        return snap_shot_dig4_enable_cam1;
    }

    public static synchronized void setSnapShotDig4EnableCam1(boolean val) {
        snap_shot_dig4_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam1() {
        return snap_shot_dig1_ftpupload_enable_cam1;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam1(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam1() {
        return snap_shot_dig2_ftpupload_enable_cam1;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam1(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam1() {
        return snap_shot_dig3_ftpupload_enable_cam1;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam1(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam1 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam1() {
        return snap_shot_dig4_ftpupload_enable_cam1;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam1(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam1 = val;
    }

    public synchronized static int getSnapShotContStreamCam1() {
        return snap_shot_cont_stream_cam1;
    }

    public static synchronized void setSnapShotContStreamCam1(int val) {
        snap_shot_cont_stream_cam1 = val;
    }

    public synchronized static int getSnapShotContIntervalCam1() {
        return snap_shot_cont_interval_cam1;
    }

    public static synchronized void setSnapShotContIntervalCam1(int val) {
        snap_shot_cont_interval_cam1 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam1() {
        return snap_shot_dig1_stream_cam1;
    }

    public static synchronized void setSnapShotDig1StreamCam1(int val) {
        snap_shot_dig1_stream_cam1 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam1() {
        return snap_shot_dig2_stream_cam1;
    }

    public static synchronized void setSnapShotDig2StreamCam1(int val) {
        snap_shot_dig2_stream_cam1 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam1() {
        return snap_shot_dig3_stream_cam1;
    }

    public static synchronized void setSnapShotDig3StreamCam1(int val) {
        snap_shot_dig3_stream_cam1 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam1() {
        return snap_shot_dig4_stream_cam1;
    }

    public static synchronized void setSnapShotDig4StreamCam1(int val) {
        snap_shot_dig4_stream_cam1 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam1() {
        return snap_shot_dig1_interval_cam1;
    }

    public static synchronized void setSnapShotDig1IntervalCam1(int val) {
        snap_shot_dig1_interval_cam1 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam1() {
        return snap_shot_dig2_interval_cam1;
    }

    public static synchronized void setSnapShotDig2IntervalCam1(int val) {
        snap_shot_dig2_interval_cam1 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam1() {
        return snap_shot_dig3_interval_cam1;
    }

    public static synchronized void setSnapShotDig3IntervalCam1(int val) {
        snap_shot_dig3_interval_cam1 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam1() {
        return snap_shot_dig4_interval_cam1;
    }

    public static synchronized void setSnapShotDig4IntervalCam1(int val) {
        snap_shot_dig4_interval_cam1 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam1() {
        return eventbased_stream_type_cam1;
    }

    public static synchronized void setEventBasedStreamTypeCam1(byte val) {
        eventbased_stream_type_cam1 = val;
    }

    public synchronized static int getEventBasedIntervalCam1() {
        return eventbased_interval_cam1;
    }

    public static synchronized void setEventBasedIntervalCam1(int val) {
        eventbased_interval_cam1 = val;
    }

    //cam2 snapshot/event
    public synchronized static boolean getSnapShotEnableCam2() {
        return snapshot_enable_cam2;
    }

    public static synchronized void setSnapShotEnableCam2(boolean val) {
        snapshot_enable_cam2 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam2() {
        return event_based_rec_enable_cam2;
    }

    public static synchronized void setEventBasedRecEnableCam2(boolean val) {
        event_based_rec_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam2() {
        return snap_shot_event_enable_cam2;
    }

    public static synchronized void setSnapShotEventEnableCam2(boolean val) {
        snap_shot_event_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam2() {
        return snap_shot_cont_enable_cam2;
    }

    public static synchronized void setSnapShotContEnableCam2(boolean val) {
        snap_shot_cont_enable_cam2 = val;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam2(boolean val) {
        snap_shot_cont_ftpupload_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam2() {
        return snap_shot_cont_ftpupload_enable_cam2;
    }

    public synchronized static boolean getSnapShotDig1EnableCam2() {
        return snap_shot_dig1_enable_cam2;
    }

    public static synchronized void setSnapShotDig1EnableCam2(boolean val) {
        snap_shot_dig1_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam2() {
        return snap_shot_dig2_enable_cam2;
    }

    public static synchronized void setSnapShotDig2EnableCam2(boolean val) {
        snap_shot_dig2_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam2() {
        return snap_shot_dig3_enable_cam2;
    }

    public static synchronized void setSnapShotDig3EnableCam2(boolean val) {
        snap_shot_dig3_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam2() {
        return snap_shot_dig4_enable_cam2;
    }

    public static synchronized void setSnapShotDig4EnableCam2(boolean val) {
        snap_shot_dig4_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam2() {
        return snap_shot_dig1_ftpupload_enable_cam2;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam2(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam2() {
        return snap_shot_dig2_ftpupload_enable_cam2;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam2(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam2() {
        return snap_shot_dig3_ftpupload_enable_cam2;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam2(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam2 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam2() {
        return snap_shot_dig4_ftpupload_enable_cam2;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam2(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam2 = val;
    }

    public synchronized static int getSnapShotContStreamCam2() {
        return snap_shot_cont_stream_cam2;
    }

    public static synchronized void setSnapShotContStreamCam2(int val) {
        snap_shot_cont_stream_cam2 = val;
    }

    public synchronized static int getSnapShotContIntervalCam2() {
        return snap_shot_cont_interval_cam2;
    }

    public static synchronized void setSnapShotContIntervalCam2(int val) {
        snap_shot_cont_interval_cam2 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam2() {
        return snap_shot_dig1_stream_cam2;
    }

    public static synchronized void setSnapShotDig1StreamCam2(int val) {
        snap_shot_dig1_stream_cam2 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam2() {
        return snap_shot_dig2_stream_cam2;
    }

    public static synchronized void setSnapShotDig2StreamCam2(int val) {
        snap_shot_dig2_stream_cam2 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam2() {
        return snap_shot_dig3_stream_cam2;
    }

    public static synchronized void setSnapShotDig3StreamCam2(int val) {
        snap_shot_dig3_stream_cam2 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam2() {
        return snap_shot_dig4_stream_cam2;
    }

    public static synchronized void setSnapShotDig4StreamCam2(int val) {
        snap_shot_dig4_stream_cam2 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam2() {
        return snap_shot_dig1_interval_cam2;
    }

    public static synchronized void setSnapShotDig1IntervalCam2(int val) {
        snap_shot_dig1_interval_cam2 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam2() {
        return snap_shot_dig2_interval_cam2;
    }

    public static synchronized void setSnapShotDig2IntervalCam2(int val) {
        snap_shot_dig2_interval_cam2 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam2() {
        return snap_shot_dig3_interval_cam2;
    }

    public static synchronized void setSnapShotDig3IntervalCam2(int val) {
        snap_shot_dig3_interval_cam2 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam2() {
        return snap_shot_dig4_interval_cam2;
    }

    public static synchronized void setSnapShotDig4IntervalCam2(int val) {
        snap_shot_dig4_interval_cam2 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam2() {
        return eventbased_stream_type_cam2;
    }

    public static synchronized void setEventBasedStreamTypeCam2(byte val) {
        eventbased_stream_type_cam2 = val;
    }

    public synchronized static int getEventBasedIntervalCam2() {
        return eventbased_interval_cam2;
    }

    public static synchronized void setEventBasedIntervalCam2(int val) {
        eventbased_interval_cam2 = val;
    }

    //cam3 snapshot/event
    public synchronized static boolean getSnapShotEnableCam3() {
        return snapshot_enable_cam3;
    }

    public static synchronized void setSnapShotEnableCam3(boolean val) {
        snapshot_enable_cam3 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam3() {
        return event_based_rec_enable_cam3;
    }

    public static synchronized void setEventBasedRecEnableCam3(boolean val) {
        event_based_rec_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam3() {
        return snap_shot_event_enable_cam3;
    }

    public static synchronized void setSnapShotEventEnableCam3(boolean val) {
        snap_shot_event_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam3() {
        return snap_shot_cont_enable_cam3;
    }

    public static synchronized void setSnapShotContEnableCam3(boolean val) {
        snap_shot_cont_enable_cam3 = val;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam3(boolean val) {
        snap_shot_cont_ftpupload_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam3() {
        return snap_shot_cont_ftpupload_enable_cam3;
    }

    public synchronized static boolean getSnapShotDig1EnableCam3() {
        return snap_shot_dig1_enable_cam3;
    }

    public static synchronized void setSnapShotDig1EnableCam3(boolean val) {
        snap_shot_dig1_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam3() {
        return snap_shot_dig2_enable_cam3;
    }

    public static synchronized void setSnapShotDig2EnableCam3(boolean val) {
        snap_shot_dig2_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam3() {
        return snap_shot_dig3_enable_cam3;
    }

    public static synchronized void setSnapShotDig3EnableCam3(boolean val) {
        snap_shot_dig3_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam3() {
        return snap_shot_dig4_enable_cam3;
    }

    public static synchronized void setSnapShotDig4EnableCam3(boolean val) {
        snap_shot_dig4_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam3() {
        return snap_shot_dig1_ftpupload_enable_cam3;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam3(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam3() {
        return snap_shot_dig2_ftpupload_enable_cam3;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam3(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam3() {
        return snap_shot_dig3_ftpupload_enable_cam3;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam3(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam3 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam3() {
        return snap_shot_dig4_ftpupload_enable_cam3;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam3(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam3 = val;
    }

    public synchronized static int getSnapShotContStreamCam3() {
        return snap_shot_cont_stream_cam3;
    }

    public static synchronized void setSnapShotContStreamCam3(int val) {
        snap_shot_cont_stream_cam3 = val;
    }

    public synchronized static int getSnapShotContIntervalCam3() {
        return snap_shot_cont_interval_cam3;
    }

    public static synchronized void setSnapShotContIntervalCam3(int val) {
        snap_shot_cont_interval_cam3 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam3() {
        return snap_shot_dig1_stream_cam3;
    }

    public static synchronized void setSnapShotDig1StreamCam3(int val) {
        snap_shot_dig1_stream_cam3 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam3() {
        return snap_shot_dig2_stream_cam3;
    }

    public static synchronized void setSnapShotDig2StreamCam3(int val) {
        snap_shot_dig2_stream_cam3 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam3() {
        return snap_shot_dig3_stream_cam3;
    }

    public static synchronized void setSnapShotDig3StreamCam3(int val) {
        snap_shot_dig3_stream_cam3 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam3() {
        return snap_shot_dig4_stream_cam3;
    }

    public static synchronized void setSnapShotDig4StreamCam3(int val) {
        snap_shot_dig4_stream_cam3 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam3() {
        return snap_shot_dig1_interval_cam3;
    }

    public static synchronized void setSnapShotDig1IntervalCam3(int val) {
        snap_shot_dig1_interval_cam3 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam3() {
        return snap_shot_dig2_interval_cam3;
    }

    public static synchronized void setSnapShotDig2IntervalCam3(int val) {
        snap_shot_dig2_interval_cam3 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam3() {
        return snap_shot_dig3_interval_cam3;
    }

    public static synchronized void setSnapShotDig3IntervalCam3(int val) {
        snap_shot_dig3_interval_cam3 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam3() {
        return snap_shot_dig4_interval_cam3;
    }

    public static synchronized void setSnapShotDig4IntervalCam3(int val) {
        snap_shot_dig4_interval_cam3 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam3() {
        return eventbased_stream_type_cam3;
    }

    public static synchronized void setEventBasedStreamTypeCam3(byte val) {
        eventbased_stream_type_cam3 = val;
    }

    public synchronized static int getEventBasedIntervalCam3() {
        return eventbased_interval_cam3;
    }

    public static synchronized void setEventBasedIntervalCam3(int val) {
        eventbased_interval_cam3 = val;
    }

    //cam4 snapshot/event
    public synchronized static boolean getSnapShotEnableCam4() {
        return snapshot_enable_cam4;
    }

    public static synchronized void setSnapShotEnableCam4(boolean val) {
        snapshot_enable_cam4 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam4() {
        return event_based_rec_enable_cam4;
    }

    public static synchronized void setEventBasedRecEnableCam4(boolean val) {
        event_based_rec_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam4() {
        return snap_shot_event_enable_cam4;
    }

    public static synchronized void setSnapShotEventEnableCam4(boolean val) {
        snap_shot_event_enable_cam4 = val;
    }

    public synchronized static int getSnapShotContStreamCam4() {
        return snap_shot_cont_stream_cam4;
    }

    public static synchronized void setSnapShotContStreamCam4(int val) {
        snap_shot_cont_stream_cam4 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam4() {
        return snap_shot_cont_enable_cam4;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam4(boolean val) {
        snap_shot_cont_ftpupload_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam4() {
        return snap_shot_cont_ftpupload_enable_cam4;
    }

    public static synchronized void setSnapShotContEnableCam4(boolean val) {
        snap_shot_cont_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig1EnableCam4() {
        return snap_shot_dig1_enable_cam4;
    }

    public static synchronized void setSnapShotDig1EnableCam4(boolean val) {
        snap_shot_dig1_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam4() {
        return snap_shot_dig2_enable_cam4;
    }

    public static synchronized void setSnapShotDig2EnableCam4(boolean val) {
        snap_shot_dig2_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam4() {
        return snap_shot_dig3_enable_cam4;
    }

    public static synchronized void setSnapShotDig3EnableCam4(boolean val) {
        snap_shot_dig3_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam4() {
        return snap_shot_dig4_enable_cam4;
    }

    public static synchronized void setSnapShotDig4EnableCam4(boolean val) {
        snap_shot_dig4_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam4() {
        return snap_shot_dig1_ftpupload_enable_cam4;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam4(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam4() {
        return snap_shot_dig2_ftpupload_enable_cam4;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam4(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam4() {
        return snap_shot_dig3_ftpupload_enable_cam4;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam4(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam4 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam4() {
        return snap_shot_dig4_ftpupload_enable_cam4;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam4(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam4 = val;
    }

    public synchronized static int getSnapShotContIntervalCam4() {
        return snap_shot_cont_interval_cam4;
    }

    public static synchronized void setSnapShotContIntervalCam4(int val) {
        snap_shot_cont_interval_cam4 = val;
    }

    //cam5 snapshot/event
    public synchronized static boolean getSnapShotEnableCam5() {
        return snapshot_enable_cam5;
    }

    public static synchronized void setSnapShotEnableCam5(boolean val) {
        snapshot_enable_cam5 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam5() {
        return event_based_rec_enable_cam5;
    }

    public static synchronized void setEventBasedRecEnableCam5(boolean val) {
        event_based_rec_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam5() {
        return snap_shot_event_enable_cam5;
    }

    public static synchronized void setSnapShotEventEnableCam5(boolean val) {
        snap_shot_event_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam5() {
        return snap_shot_cont_enable_cam5;
    }

    public static synchronized void setSnapShotContEnableCam5(boolean val) {
        snap_shot_cont_enable_cam5 = val;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam5(boolean val) {
        snap_shot_cont_ftpupload_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam5() {
        return snap_shot_cont_ftpupload_enable_cam5;
    }

    public synchronized static boolean getSnapShotDig1EnableCam5() {
        return snap_shot_dig1_enable_cam5;
    }

    public static synchronized void setSnapShotDig1EnableCam5(boolean val) {
        snap_shot_dig1_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam5() {
        return snap_shot_dig2_enable_cam5;
    }

    public static synchronized void setSnapShotDig2EnableCam5(boolean val) {
        snap_shot_dig2_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam5() {
        return snap_shot_dig3_enable_cam5;
    }

    public static synchronized void setSnapShotDig3EnableCam5(boolean val) {
        snap_shot_dig3_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam5() {
        return snap_shot_dig4_enable_cam5;
    }

    public static synchronized void setSnapShotDig4EnableCam5(boolean val) {
        snap_shot_dig4_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam5() {
        return snap_shot_dig1_ftpupload_enable_cam5;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam5(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam5() {
        return snap_shot_dig2_ftpupload_enable_cam5;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam5(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam5() {
        return snap_shot_dig3_ftpupload_enable_cam5;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam5(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam5 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam5() {
        return snap_shot_dig4_ftpupload_enable_cam5;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam5(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam5 = val;
    }

    public synchronized static int getSnapShotContStreamCam5() {
        return snap_shot_cont_stream_cam5;
    }

    public static synchronized void setSnapShotContStreamCam5(int val) {
        snap_shot_cont_stream_cam5 = val;
    }

    public synchronized static int getSnapShotContIntervalCam5() {
        return snap_shot_cont_interval_cam5;
    }

    public static synchronized void setSnapShotContIntervalCam5(int val) {
        snap_shot_cont_interval_cam5 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam5() {
        return snap_shot_dig1_stream_cam5;
    }

    public static synchronized void setSnapShotDig1StreamCam5(int val) {
        snap_shot_dig1_stream_cam5 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam5() {
        return snap_shot_dig2_stream_cam5;
    }

    public static synchronized void setSnapShotDig2StreamCam5(int val) {
        snap_shot_dig2_stream_cam5 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam5() {
        return snap_shot_dig3_stream_cam5;
    }

    public static synchronized void setSnapShotDig3StreamCam5(int val) {
        snap_shot_dig3_stream_cam5 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam5() {
        return snap_shot_dig4_stream_cam5;
    }

    public static synchronized void setSnapShotDig4StreamCam5(int val) {
        snap_shot_dig4_stream_cam5 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam5() {
        return snap_shot_dig1_interval_cam5;
    }

    public static synchronized void setSnapShotDig1IntervalCam5(int val) {
        snap_shot_dig1_interval_cam5 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam5() {
        return snap_shot_dig2_interval_cam5;
    }

    public static synchronized void setSnapShotDig2IntervalCam5(int val) {
        snap_shot_dig2_interval_cam5 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam5() {
        return snap_shot_dig3_interval_cam5;
    }

    public static synchronized void setSnapShotDig3IntervalCam5(int val) {
        snap_shot_dig3_interval_cam5 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam5() {
        return snap_shot_dig4_interval_cam5;
    }

    public static synchronized void setSnapShotDig4IntervalCam5(int val) {
        snap_shot_dig4_interval_cam5 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam5() {
        return eventbased_stream_type_cam5;
    }

    public static synchronized void setEventBasedStreamTypeCam5(byte val) {
        eventbased_stream_type_cam5 = val;
    }

    public synchronized static int getEventBasedIntervalCam5() {
        return eventbased_interval_cam5;
    }

    public static synchronized void setEventBasedIntervalCam5(int val) {
        eventbased_interval_cam5 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam4() {
        return snap_shot_dig1_stream_cam4;
    }

    public static synchronized void setSnapShotDig1StreamCam4(int val) {
        snap_shot_dig1_stream_cam4 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam4() {
        return snap_shot_dig2_stream_cam4;
    }

    public static synchronized void setSnapShotDig2StreamCam4(int val) {
        snap_shot_dig2_stream_cam4 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam4() {
        return snap_shot_dig3_stream_cam4;
    }

    public static synchronized void setSnapShotDig3StreamCam4(int val) {
        snap_shot_dig3_stream_cam4 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam4() {
        return snap_shot_dig4_stream_cam4;
    }

    public static synchronized void setSnapShotDig4StreamCam4(int val) {
        snap_shot_dig4_stream_cam4 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam4() {
        return snap_shot_dig1_interval_cam4;
    }

    public static synchronized void setSnapShotDig1IntervalCam4(int val) {
        snap_shot_dig1_interval_cam4 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam4() {
        return snap_shot_dig2_interval_cam4;
    }

    public static synchronized void setSnapShotDig2IntervalCam4(int val) {
        snap_shot_dig2_interval_cam4 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam4() {
        return snap_shot_dig3_interval_cam4;
    }

    public static synchronized void setSnapShotDig3IntervalCam4(int val) {
        snap_shot_dig3_interval_cam4 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam4() {
        return snap_shot_dig4_interval_cam4;
    }

    public static synchronized void setSnapShotDig4IntervalCam4(int val) {
        snap_shot_dig4_interval_cam4 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam4() {
        return eventbased_stream_type_cam4;
    }

    public static synchronized void setEventBasedStreamTypeCam4(byte val) {
        eventbased_stream_type_cam4 = val;
    }

    public synchronized static int getEventBasedIntervalCam4() {
        return eventbased_interval_cam4;
    }

    public static synchronized void setEventBasedIntervalCam4(int val) {
        eventbased_interval_cam4 = val;
    }

    //cam6 snapshot/event
    public synchronized static boolean getSnapShotEnableCam6() {
        return snapshot_enable_cam6;
    }

    public static synchronized void setSnapShotEnableCam6(boolean val) {
        snapshot_enable_cam6 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam6() {
        return event_based_rec_enable_cam6;
    }

    public static synchronized void setEventBasedRecEnableCam6(boolean val) {
        event_based_rec_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam6() {
        return snap_shot_event_enable_cam6;
    }

    public static synchronized void setSnapShotEventEnableCam6(boolean val) {
        snap_shot_event_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam6() {
        return snap_shot_cont_enable_cam6;
    }

    public static synchronized void setSnapShotContEnableCam6(boolean val) {
        snap_shot_cont_enable_cam6 = val;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam6(boolean val) {
        snap_shot_cont_ftpupload_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam6() {
        return snap_shot_cont_ftpupload_enable_cam6;
    }

    public synchronized static boolean getSnapShotDig1EnableCam6() {
        return snap_shot_dig1_enable_cam6;
    }

    public static synchronized void setSnapShotDig1EnableCam6(boolean val) {
        snap_shot_dig1_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam6() {
        return snap_shot_dig2_enable_cam6;
    }

    public static synchronized void setSnapShotDig2EnableCam6(boolean val) {
        snap_shot_dig2_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam6() {
        return snap_shot_dig3_enable_cam6;
    }

    public static synchronized void setSnapShotDig3EnableCam6(boolean val) {
        snap_shot_dig3_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam6() {
        return snap_shot_dig4_enable_cam6;
    }

    public static synchronized void setSnapShotDig4EnableCam6(boolean val) {
        snap_shot_dig4_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam6() {
        return snap_shot_dig1_ftpupload_enable_cam6;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam6(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam6() {
        return snap_shot_dig2_ftpupload_enable_cam6;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam6(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam6() {
        return snap_shot_dig3_ftpupload_enable_cam6;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam6(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam6 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam6() {
        return snap_shot_dig4_ftpupload_enable_cam6;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam6(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam6 = val;
    }

    public synchronized static int getSnapShotContStreamCam6() {
        return snap_shot_cont_stream_cam6;
    }

    public static synchronized void setSnapShotContStreamCam6(int val) {
        snap_shot_cont_stream_cam6 = val;
    }

    public synchronized static int getSnapShotContIntervalCam6() {
        return snap_shot_cont_interval_cam6;
    }

    public static synchronized void setSnapShotContIntervalCam6(int val) {
        snap_shot_cont_interval_cam6 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam6() {
        return snap_shot_dig1_stream_cam6;
    }

    public static synchronized void setSnapShotDig1StreamCam6(int val) {
        snap_shot_dig1_stream_cam6 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam6() {
        return snap_shot_dig2_stream_cam6;
    }

    public static synchronized void setSnapShotDig2StreamCam6(int val) {
        snap_shot_dig2_stream_cam6 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam6() {
        return snap_shot_dig3_stream_cam6;
    }

    public static synchronized void setSnapShotDig3StreamCam6(int val) {
        snap_shot_dig3_stream_cam6 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam6() {
        return snap_shot_dig4_stream_cam6;
    }

    public static synchronized void setSnapShotDig4StreamCam6(int val) {
        snap_shot_dig4_stream_cam6 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam6() {
        return snap_shot_dig1_interval_cam6;
    }

    public static synchronized void setSnapShotDig1IntervalCam6(int val) {
        snap_shot_dig1_interval_cam6 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam6() {
        return snap_shot_dig2_interval_cam6;
    }

    public static synchronized void setSnapShotDig2IntervalCam6(int val) {
        snap_shot_dig2_interval_cam6 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam6() {
        return snap_shot_dig3_interval_cam6;
    }

    public static synchronized void setSnapShotDig3IntervalCam6(int val) {
        snap_shot_dig3_interval_cam6 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam6() {
        return snap_shot_dig4_interval_cam6;
    }

    public static synchronized void setSnapShotDig4IntervalCam6(int val) {
        snap_shot_dig4_interval_cam6 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam6() {
        return eventbased_stream_type_cam6;
    }

    public static synchronized void setEventBasedStreamTypeCam6(byte val) {
        eventbased_stream_type_cam6 = val;
    }

    public synchronized static int getEventBasedIntervalCam6() {
        return eventbased_interval_cam6;
    }

    public static synchronized void setEventBasedIntervalCam6(int val) {
        eventbased_interval_cam6 = val;
    }

    //cam7 snapshot/event
    public synchronized static boolean getSnapShotEnableCam7() {
        return snapshot_enable_cam7;
    }

    public static synchronized void setSnapShotEnableCam7(boolean val) {
        snapshot_enable_cam7 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam7() {
        return event_based_rec_enable_cam7;
    }

    public static synchronized void setEventBasedRecEnableCam7(boolean val) {
        event_based_rec_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam7() {
        return snap_shot_event_enable_cam7;
    }

    public static synchronized void setSnapShotEventEnableCam7(boolean val) {
        snap_shot_event_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam7() {
        return snap_shot_cont_enable_cam7;
    }

    public static synchronized void setSnapShotContEnableCam7(boolean val) {
        snap_shot_cont_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig1EnableCam7() {
        return snap_shot_dig1_enable_cam7;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam7(boolean val) {
        snap_shot_cont_ftpupload_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam7() {
        return snap_shot_cont_ftpupload_enable_cam7;
    }

    public static synchronized void setSnapShotDig1EnableCam7(boolean val) {
        snap_shot_dig1_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam7() {
        return snap_shot_dig2_enable_cam7;
    }

    public static synchronized void setSnapShotDig2EnableCam7(boolean val) {
        snap_shot_dig2_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam7() {
        return snap_shot_dig3_enable_cam7;
    }

    public static synchronized void setSnapShotDig3EnableCam7(boolean val) {
        snap_shot_dig3_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam7() {
        return snap_shot_dig4_enable_cam7;
    }

    public static synchronized void setSnapShotDig4EnableCam7(boolean val) {
        snap_shot_dig4_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam7() {
        return snap_shot_dig1_ftpupload_enable_cam7;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam7(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam7() {
        return snap_shot_dig2_ftpupload_enable_cam7;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam7(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam7() {
        return snap_shot_dig3_ftpupload_enable_cam7;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam7(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam7 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam7() {
        return snap_shot_dig4_ftpupload_enable_cam7;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam7(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam7 = val;
    }

    public synchronized static int getSnapShotContStreamCam7() {
        return snap_shot_cont_stream_cam7;
    }

    public static synchronized void setSnapShotContStreamCam7(int val) {
        snap_shot_cont_stream_cam7 = val;
    }

    public synchronized static int getSnapShotContIntervalCam7() {
        return snap_shot_cont_interval_cam7;
    }

    public static synchronized void setSnapShotContIntervalCam7(int val) {
        snap_shot_cont_interval_cam7 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam7() {
        return snap_shot_dig1_stream_cam7;
    }

    public static synchronized void setSnapShotDig1StreamCam7(int val) {
        snap_shot_dig1_stream_cam7 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam7() {
        return snap_shot_dig2_stream_cam7;
    }

    public static synchronized void setSnapShotDig2StreamCam7(int val) {
        snap_shot_dig2_stream_cam7 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam7() {
        return snap_shot_dig3_stream_cam7;
    }

    public static synchronized void setSnapShotDig3StreamCam7(int val) {
        snap_shot_dig3_stream_cam7 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam7() {
        return snap_shot_dig4_stream_cam7;
    }

    public static synchronized void setSnapShotDig4StreamCam7(int val) {
        snap_shot_dig4_stream_cam7 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam7() {
        return snap_shot_dig1_interval_cam7;
    }

    public static synchronized void setSnapShotDig1IntervalCam7(int val) {
        snap_shot_dig1_interval_cam7 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam7() {
        return snap_shot_dig2_interval_cam7;
    }

    public static synchronized void setSnapShotDig2IntervalCam7(int val) {
        snap_shot_dig2_interval_cam7 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam7() {
        return snap_shot_dig3_interval_cam7;
    }

    public static synchronized void setSnapShotDig3IntervalCam7(int val) {
        snap_shot_dig3_interval_cam7 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam7() {
        return snap_shot_dig4_interval_cam7;
    }

    public static synchronized void setSnapShotDig4IntervalCam7(int val) {
        snap_shot_dig4_interval_cam7 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam7() {
        return eventbased_stream_type_cam7;
    }

    public static synchronized void setEventBasedStreamTypeCam7(byte val) {
        eventbased_stream_type_cam7 = val;
    }

    public synchronized static int getEventBasedIntervalCam7() {
        return eventbased_interval_cam7;
    }

    public static synchronized void setEventBasedIntervalCam7(int val) {
        eventbased_interval_cam7 = val;
    }

    //cam8 snapshot/event
    public synchronized static boolean getSnapShotEnableCam8() {
        return snapshot_enable_cam8;
    }

    public static synchronized void setSnapShotEnableCam8(boolean val) {
        snapshot_enable_cam8 = val;
    }

    public synchronized static boolean getEventBasedRecEnableCam8() {
        return event_based_rec_enable_cam8;
    }

    public static synchronized void setEventBasedRecEnableCam8(boolean val) {
        event_based_rec_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotEventEnableCam8() {
        return snap_shot_event_enable_cam8;
    }

    public static synchronized void setSnapShotEventEnableCam8(boolean val) {
        snap_shot_event_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotContEnableCam8() {
        return snap_shot_cont_enable_cam8;
    }

    public static synchronized void setSnapShotContEnableCam8(boolean val) {
        snap_shot_cont_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig1EnableCam8() {
        return snap_shot_dig1_enable_cam8;
    }

    public static synchronized void setSnapShotContFtpUploadEnableCam8(boolean val) {
        snap_shot_cont_ftpupload_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotContFtpUploadEnableCam8() {
        return snap_shot_cont_ftpupload_enable_cam8;
    }

    public static synchronized void setSnapShotDig1EnableCam8(boolean val) {
        snap_shot_dig1_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig2EnableCam8() {
        return snap_shot_dig2_enable_cam8;
    }

    public static synchronized void setSnapShotDig2EnableCam8(boolean val) {
        snap_shot_dig2_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig3EnableCam8() {
        return snap_shot_dig3_enable_cam8;
    }

    public static synchronized void setSnapShotDig3EnableCam8(boolean val) {
        snap_shot_dig3_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig4EnableCam8() {
        return snap_shot_dig4_enable_cam8;
    }

    public static synchronized void setSnapShotDig4EnableCam8(boolean val) {
        snap_shot_dig4_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig1FtpUploadEnableCam8() {
        return snap_shot_dig1_ftpupload_enable_cam8;
    }

    public static synchronized void setSnapShotDig1FtpUploadEnableCam8(boolean val) {
        snap_shot_dig1_ftpupload_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig2FtpUploadEnableCam8() {
        return snap_shot_dig2_ftpupload_enable_cam8;
    }

    public static synchronized void setSnapShotDig2FtpUploadEnableCam8(boolean val) {
        snap_shot_dig2_ftpupload_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig3FtpUploadEnableCam8() {
        return snap_shot_dig3_ftpupload_enable_cam8;
    }

    public static synchronized void setSnapShotDig3FtpUploadEnableCam8(boolean val) {
        snap_shot_dig3_ftpupload_enable_cam8 = val;
    }

    public synchronized static boolean getSnapShotDig4FtpUploadEnableCam8() {
        return snap_shot_dig4_ftpupload_enable_cam8;
    }

    public static synchronized void setSnapShotDig4FtpUploadEnableCam8(boolean val) {
        snap_shot_dig4_ftpupload_enable_cam8 = val;
    }

    public synchronized static int getSnapShotContStreamCam8() {
        return snap_shot_cont_stream_cam8;
    }

    public static synchronized void setSnapShotContStreamCam8(int val) {
        snap_shot_cont_stream_cam8 = val;
    }

    public synchronized static int getSnapShotContIntervalCam8() {
        return snap_shot_cont_interval_cam8;
    }

    public static synchronized void setSnapShotContIntervalCam8(int val) {
        snap_shot_cont_interval_cam8 = val;
    }

    public synchronized static int getSnapShotDig1StreamCam8() {
        return snap_shot_dig1_stream_cam8;
    }

    public static synchronized void setSnapShotDig1StreamCam8(int val) {
        snap_shot_dig1_stream_cam8 = val;
    }

    public synchronized static int getSnapShotDig2StreamCam8() {
        return snap_shot_dig2_stream_cam8;
    }

    public static synchronized void setSnapShotDig2StreamCam8(int val) {
        snap_shot_dig2_stream_cam8 = val;
    }

    public synchronized static int getSnapShotDig3StreamCam8() {
        return snap_shot_dig3_stream_cam8;
    }

    public static synchronized void setSnapShotDig3StreamCam8(int val) {
        snap_shot_dig3_stream_cam8 = val;
    }

    public synchronized static int getSnapShotDig4StreamCam8() {
        return snap_shot_dig4_stream_cam8;
    }

    public static synchronized void setSnapShotDig4StreamCam8(int val) {
        snap_shot_dig4_stream_cam8 = val;
    }

    public synchronized static int getSnapShotDig1IntervalCam8() {
        return snap_shot_dig1_interval_cam8;
    }

    public static synchronized void setSnapShotDig1IntervalCam8(int val) {
        snap_shot_dig1_interval_cam8 = val;
    }

    public synchronized static int getSnapShotDig2IntervalCam8() {
        return snap_shot_dig2_interval_cam8;
    }

    public static synchronized void setSnapShotDig2IntervalCam8(int val) {
        snap_shot_dig2_interval_cam8 = val;
    }

    public synchronized static int getSnapShotDig3IntervalCam8() {
        return snap_shot_dig3_interval_cam8;
    }

    public static synchronized void setSnapShotDig3IntervalCam8(int val) {
        snap_shot_dig3_interval_cam8 = val;
    }

    public synchronized static int getSnapShotDig4IntervalCam8() {
        return snap_shot_dig4_interval_cam8;
    }

    public static synchronized void setSnapShotDig4IntervalCam8(int val) {
        snap_shot_dig4_interval_cam8 = val;
    }

    public synchronized static byte getEventBasedStreamTypeCam8() {
        return eventbased_stream_type_cam8;
    }

    public static synchronized void setEventBasedStreamTypeCam8(byte val) {
        eventbased_stream_type_cam8 = val;
    }

    public synchronized static int getEventBasedIntervalCam8() {
        return eventbased_interval_cam8;
    }

    public static synchronized void setEventBasedIntervalCam8(int val) {
        eventbased_interval_cam8 = val;
    }

    public synchronized static boolean getCamPortDetected() {
        return canport_detected;
    }

    public static synchronized void setCamPortDetected(boolean val) {
        canport_detected = val;
    }

    public synchronized static boolean getCamPortDetected1() {
        return canport_detected1;
    }

    public static synchronized void setCamPortDetected1(boolean val) {
        canport_detected1 = val;
    }

    public synchronized static boolean getHardDriveDetected() {
        return harddrive_detected;
    }

    public static synchronized void setHardDriveDetected(boolean val) {
        harddrive_detected = val;
    }

    public synchronized static boolean getRestartPortsDetected() {
        return restart_ports_detected;
    }

    public static synchronized void setRestartPortsDetected(boolean val) {
        restart_ports_detected = val;
    }

    public synchronized static boolean getSnapDig1State() {
        return snap_dig1_state;
    }

    public static synchronized void setSnapDig1State(boolean val) {
        snap_dig1_state = val;
    }

    public synchronized static boolean getSnapDig2State() {
        return snap_dig2_state;
    }

    public static synchronized void setSnapDig2State(boolean val) {
        snap_dig2_state = val;
    }

    public synchronized static boolean getSnapDig3State() {
        return snap_dig3_state;
    }

    public static synchronized void setSnapDig3State(boolean val) {
        snap_dig3_state = val;
    }

    public synchronized static boolean getSnapDig4State() {
        return snap_dig4_state;
    }

    public static synchronized void setSnapDig4State(boolean val) {
        snap_dig4_state = val;
    }

    public synchronized static boolean getSosStarted() {
        return sos_started;
    }

    public static synchronized void setSosStarted(boolean val) {
        sos_started = val;
    }

    public static synchronized void setLogFilesRequired(boolean val) {
        log_files_required = val;
    }

    public static synchronized boolean getLogFilesRequired() {
        return log_files_required;
    }

    public synchronized static void setVendorId(String val) {
        vendor_id = val;
    }

    public synchronized static String getVendorId() {
        return vendor_id;
    }

    public synchronized static void setPdop(String val) {
        pdop = val;
    }

    public synchronized static String getPdop() {
        return pdop;
    }

    public synchronized static void setHdop(String val) {
        hdop = val;
    }

    public synchronized static String getHdop() {
        return hdop;
    }

    public synchronized static void setNetworkOperatorName(String val) {
        network_operator_name = val;
    }

    public synchronized static String getNetworkOperatorName() {
        return network_operator_name;
    }

    public synchronized static void setEmergencyAlertStatus(char val) {
        emergency_alert_status = val;
    }

    public static synchronized void setLinedetSpeedCam3(int val) {
        linedet_speed_cam3 = val;
    }

    public synchronized static int getLinedetSpeedCam3() {
        return linedet_speed_cam3;
    }

    public synchronized static char getEmergencyAlertStatus() {
        return emergency_alert_status;
    }

    public synchronized static void setTamperAlertStatus(char val) {
        tamper_alert_status = val;
    }

    public synchronized static boolean getFmRadioEnable() {
        return fm_radio_enable;
    }

    public static synchronized void setFmRadioEnable(boolean val) {
        fm_radio_enable = val;
    }

    public synchronized static char getTamperAlertStatus() {
        return tamper_alert_status;
    }

    public synchronized static boolean get4CamsLive() {
        return video_4_screens_enable;
    }

    public static synchronized void set4CamsLive(boolean val) {
        video_4_screens_enable = val;
    }

    public synchronized static void setMcc(String val) {
        mcc = val;
    }

    public synchronized static String getMcc() {
        return mcc;
    }

    public synchronized static void setMnc(String val) {
        mnc = val;
    }

    public synchronized static String getMnc() {
        return mnc;
    }

    public synchronized static void setLac(String val) {
        lac = val;
    }

    public synchronized static String getLac() {
        return lac;
    }

    public synchronized static void setCellId(String val) {
        cellId = val;
    }

    public synchronized static String getCellId() {
        return cellId;
    }

    public synchronized static void setNmr1CellId(String val) {
        nmr1CellId = val;
    }

    public synchronized static String getNmr1CellId() {
        return nmr1CellId;
    }

    public synchronized static void setNmr1Lac(String val) {
        nmr1Lac = val;
    }

    public synchronized static String getNmr1Lac() {
        return nmr1Lac;
    }

    public synchronized static void setNmr1SigStrength(String val) {
        nmr1SigStrength = val;
    }

    public synchronized static String getNmr1SigStrength() {
        return nmr1SigStrength;
    }

    //second neighbour cell
    public synchronized static void setNmr2CellId(String val) {
        nmr2CellId = val;
    }

    public synchronized static String getNmr2CellId() {
        return nmr2CellId;
    }

    public synchronized static void setNmr2Lac(String val) {
        nmr2Lac = val;
    }

    public synchronized static String getNmr2Lac() {
        return nmr2Lac;
    }

    public synchronized static void setNmr2SigStrength(String val) {
        nmr2SigStrength = val;
    }

    public synchronized static String getNmr2SigStrength() {
        return nmr2SigStrength;
    }
//third neighbour cell

    public synchronized static void setNmr3CellId(String val) {
        nmr3CellId = val;
    }

    public synchronized static String getNmr3CellId() {
        return nmr3CellId;
    }

    public synchronized static void setNmr3Lac(String val) {
        nmr3Lac = val;
    }

    public synchronized static String getNmr3Lac() {
        return nmr3Lac;
    }

    public synchronized static void setNmr3SigStrength(String val) {
        nmr3SigStrength = val;
    }

    public synchronized static String getNmr3SigStrength() {
        return nmr3SigStrength;
    }

    public synchronized static void setNmr4CellId(String val) {
        nmr4CellId = val;
    }

    public synchronized static String getNmr4CellId() {
        return nmr4CellId;
    }

    public synchronized static void setNmr4Lac(String val) {
        nmr4Lac = val;
    }

    public synchronized static String getNmr4Lac() {
        return nmr4Lac;
    }

    public synchronized static void setNmr4SigStrength(String val) {
        nmr4SigStrength = val;
    }

    public synchronized static String getNmr4SigStrength() {
        return nmr4SigStrength;
    }

    public synchronized static void setDigInput1Status(byte val) {
        di1_status = val;
    }

    public synchronized static byte getDigInput1Status() {
        return di1_status;
    }

    public synchronized static void setDigInput2Status(byte val) {
        di2_status = val;
    }

    public synchronized static byte getDigInput2Status() {
        return di2_status;
    }

    public synchronized static void setDigInput3Status(byte val) {
        di3_status = val;
    }

    public synchronized static byte getDigInput3Status() {
        return di3_status;
    }

    public synchronized static void setDigInput4Status(byte val) {
        di4_status = val;
    }

    public synchronized static byte getDigInput4Status() {
        return di4_status;
    }

    public synchronized static void setDigOutput1Status(byte val) {
        do1_status = val;
    }

    public synchronized static byte getDigOutput1Status() {
        return do1_status;
    }

    public synchronized static void setDigOutput2Status(byte val) {
        do2_status = val;
    }

    public synchronized static byte getDigOutput2Status() {
        return do2_status;
    }

    public synchronized static void setPktSeqNo(int val) {
        pkt_seq_no = val;
    }

    public synchronized static void incPktSeqNo() {
        pkt_seq_no++;
        if (pkt_seq_no >= 999999) {
            pkt_seq_no = 0;
        }
    }

    public synchronized static int getPktSeqNo() {
        return pkt_seq_no;
    }

    public synchronized static void setPktSeqNoIp3(int val) {
        pkt_seq_no3 = val;
    }

    public synchronized static void incPktSeqNoIp3() {
        pkt_seq_no3++;
        if (pkt_seq_no3 >= 999999) {
            pkt_seq_no3 = 0;
        }
    }

    public synchronized static int getPktSeqNoIp3() {
        return pkt_seq_no3;
    }

    public synchronized static void setPktSeqNoIp5(int val) {
        pkt_seq_no5 = val;
    }

    public synchronized static void incPktSeqNoIp5() {
        pkt_seq_no5++;
        if (pkt_seq_no5 >= 999999) {
            pkt_seq_no5 = 0;
        }
    }

    public synchronized static int getPktSeqNoIp5() {
        return pkt_seq_no5;
    }

    public synchronized static void setPktSeqNoSms(int val) {
        pkt_seq_no3 = val;
    }

    public synchronized static void incPktSeqNoSms() {
        pkt_seq_no3++;
        if (pkt_seq_no3 >= 999999) {
            pkt_seq_no3 = 0;
        }
    }

    public synchronized static int getPktSeqNoSms() {
        return pkt_seq_no3;
    }

    public synchronized static void setEventPktSeqNo5(int val) {
        event_pkt_seq_number5 = val;
    }

    public synchronized static int getEventPktSeqNo5() {
        return event_pkt_seq_number5;
    }

    public synchronized static void setCameraName1(String name) {
        cameraname1 = name;
    }

    public synchronized static String getCameraName1() {
        return cameraname1;
    }

    public synchronized static void setCameraName2(String name) {
        cameraname2 = name;
    }

    public synchronized static String getCameraName2() {
        return cameraname2;
    }

    public synchronized static void setCameraName3(String name) {
        cameraname3 = name;
    }

    public synchronized static String getCameraName3() {
        return cameraname3;
    }

    public synchronized static void setCameraName4(String name) {
        cameraname4 = name;
    }

    public synchronized static String getCameraName4() {
        return cameraname4;
    }

    public synchronized static void setCameraName5(String name) {
        cameraname5 = name;
    }

    public synchronized static String getCameraName5() {
        return cameraname5;
    }

    public synchronized static void setCameraName6(String name) {
        cameraname6 = name;
    }

    public synchronized static String getCameraName6() {
        return cameraname6;
    }

    public synchronized static void setCameraName7(String name) {
        cameraname7 = name;
    }

    public synchronized static String getCameraName7() {
        return cameraname7;
    }

    public synchronized static void setCameraName8(String name) {
        cameraname8 = name;
    }

    public synchronized static String getCameraName8() {
        return cameraname8;
    }

    public synchronized static void setCameraIdentifer1(boolean name) {
        chk_cam1 = name;
    }

    public synchronized static boolean getCameraIdentifer1() {
        return chk_cam1;
    }

    public synchronized static void setCameraIdentifer2(boolean name) {
        chk_cam2 = name;
    }

    public synchronized static boolean getCameraIdentifer2() {
        return chk_cam2;
    }

    public synchronized static void setCameraIdentifer3(boolean name) {
        chk_cam3 = name;
    }

    public synchronized static boolean getCameraIdentifer3() {
        return chk_cam3;
    }

    public synchronized static void setCameraIdentifer4(boolean name) {
        chk_cam4 = name;
    }

    public synchronized static boolean getCameraIdentifer4() {
        return chk_cam4;
    }

    public synchronized static void setCameraIdentifer5(boolean name) {
        chk_cam5 = name;
    }

    public synchronized static boolean getCameraIdentifer5() {
        return chk_cam5;
    }

    public synchronized static void setCameraIdentifer6(boolean name) {
        chk_cam6 = name;
    }

    public synchronized static boolean getCameraIdentifer6() {
        return chk_cam6;
    }

    public synchronized static void setCameraIdentifer7(boolean name) {
        chk_cam7 = name;
    }

    public synchronized static boolean getCameraIdentifer7() {
        return chk_cam7;
    }

    public synchronized static void setCameraIdentifer8(boolean name) {
        chk_cam8 = name;
    }

    public synchronized static boolean getCameraIdentifer8() {
        return chk_cam8;
    }

    public synchronized static void setCamSpeedEnable1(boolean name) {
        chk_spd1 = name;
    }

    public synchronized static boolean getCamSpeedEnable1() {
        return chk_spd1;
    }

    public synchronized static void setCamSpeedEnable2(boolean name) {
        chk_spd2 = name;
    }

    public synchronized static boolean getCamSpeedEnable2() {
        return chk_spd2;
    }

    public synchronized static void setCamSpeedEnable3(boolean name) {
        chk_spd3 = name;
    }

    public synchronized static boolean getCamSpeedEnable3() {
        return chk_spd3;
    }

    public synchronized static void setCamSpeedEnable4(boolean name) {
        chk_spd4 = name;
    }

    public synchronized static boolean getCamSpeedEnable4() {
        return chk_spd4;
    }

    public synchronized static void setCamSpeedEnable5(boolean name) {
        chk_spd5 = name;
    }

    public synchronized static boolean getCamSpeedEnable5() {
        return chk_spd5;
    }

    public synchronized static void setCamSpeedEnable6(boolean name) {
        chk_spd6 = name;
    }

    public synchronized static boolean getCamSpeedEnable6() {
        return chk_spd6;
    }

    public synchronized static void setCamSpeedEnable7(boolean name) {
        chk_spd7 = name;
    }

    public synchronized static boolean getCamSpeedEnable7() {
        return chk_spd7;
    }

    public synchronized static void setCamSpeedEnable8(boolean name) {
        chk_spd8 = name;
    }

    public synchronized static boolean getCamSpeedEnable8() {
        return chk_spd8;
    }

    public synchronized static void setCamlatlangEnable1(boolean name) {
        chk_latlang1 = name;
    }

    public synchronized static boolean getCamlatlangEnable1() {
        return chk_latlang1;
    }

    public synchronized static void setCamlatlangEnable2(boolean name) {
        chk_latlang2 = name;
    }

    public synchronized static boolean getCamlatlangEnable2() {
        return chk_latlang2;
    }

    public synchronized static void setCamlatlangEnable3(boolean name) {
        chk_latlang3 = name;
    }

    public synchronized static boolean getCamlatlangEnable3() {
        return chk_latlang3;
    }

    public synchronized static void setCamlatlangEnable4(boolean name) {
        chk_latlang4 = name;
    }

    public synchronized static boolean getCamlatlangEnable4() {
        return chk_latlang4;
    }

    public synchronized static void setCamlatlangEnable5(boolean name) {
        chk_latlang5 = name;
    }

    public synchronized static boolean getCamlatlangEnable5() {
        return chk_latlang5;
    }

    public synchronized static void setCamlatlangEnable6(boolean name) {
        chk_latlang6 = name;
    }

    public synchronized static boolean getCamlatlangEnable6() {
        return chk_latlang6;
    }

    public synchronized static void setCamlatlangEnable7(boolean name) {
        chk_latlang7 = name;
    }

    public synchronized static boolean getCamlatlangEnable7() {
        return chk_latlang7;
    }

    public synchronized static void setCamlatlangEnable8(boolean name) {
        chk_latlang8 = name;
    }

    public synchronized static boolean getCamlatlangEnable8() {
        return chk_latlang8;
    }

    public synchronized static void setCamVehRegEnable1(boolean name) {
        chk_vehreg1 = name;
    }

    public synchronized static boolean getCamVehRegEnable1() {
        return chk_vehreg1;
    }

    public synchronized static void setCamVehRegEnable2(boolean name) {
        chk_vehreg2 = name;
    }

    public synchronized static boolean getCamVehRegEnable2() {
        return chk_vehreg2;
    }

    public synchronized static void setCamVehRegEnable3(boolean name) {
        chk_vehreg3 = name;
    }

    public synchronized static boolean getCamVehRegEnable3() {
        return chk_vehreg3;
    }

    public synchronized static void setCamVehRegEnable4(boolean name) {
        chk_vehreg4 = name;
    }

    public synchronized static boolean getCamVehRegEnable4() {
        return chk_vehreg4;
    }

    public synchronized static void setCamVehRegEnable5(boolean name) {
        chk_vehreg5 = name;
    }

    public synchronized static boolean getCamVehRegEnable5() {
        return chk_vehreg5;
    }

    public synchronized static void setCamVehRegEnable6(boolean name) {
        chk_vehreg6 = name;
    }

    public synchronized static boolean getCamVehRegEnable6() {
        return chk_vehreg6;
    }

    public synchronized static void setCamVehRegEnable7(boolean name) {
        chk_vehreg7 = name;
    }

    public synchronized static boolean getCamVehRegEnable7() {
        return chk_vehreg7;
    }

    public synchronized static void setCamVehRegEnable8(boolean name) {
        chk_vehreg8 = name;
    }

    public synchronized static boolean getCamVehRegEnable8() {
        return chk_vehreg8;
    }

    public synchronized static void setCam1RecordAudioSelected(boolean state) {
        record_cam1_audio = state;
    }

    public synchronized static boolean getCam1RecordAudioSelected() {
        return record_cam1_audio;
    }

    public synchronized static void setCam2RecordAudioSelected(boolean state) {
        record_cam2_audio = state;
    }

    public synchronized static boolean getCam2RecordAudioSelected() {
        return record_cam2_audio;
    }

    public synchronized static void setCam3RecordAudioSelected(boolean state) {
        record_cam3_audio = state;
    }

    public synchronized static boolean getCam3RecordAudioSelected() {
        return record_cam3_audio;
    }

    public synchronized static void setCam4RecordAudioSelected(boolean state) {
        record_cam4_audio = state;
    }

    public synchronized static boolean getCam4RecordAudioSelected() {
        return record_cam4_audio;
    }

    public synchronized static void setCam5RecordAudioSelected(boolean state) {
        record_cam5_audio = state;
    }

    public synchronized static boolean getCam5RecordAudioSelected() {
        return record_cam5_audio;
    }

    public synchronized static void setCam6RecordAudioSelected(boolean state) {
        record_cam6_audio = state;
    }

    public synchronized static boolean getCam6RecordAudioSelected() {
        return record_cam6_audio;
    }

    public synchronized static void setCam7RecordAudioSelected(boolean state) {
        record_cam7_audio = state;
    }

    public synchronized static boolean getCam7RecordAudioSelected() {
        return record_cam7_audio;
    }

    public synchronized static void setCam8RecordAudioSelected(boolean state) {
        record_cam8_audio = state;
    }

    public synchronized static boolean getCam8RecordAudioSelected() {
        return record_cam8_audio;
    }

    public synchronized static void setSchRecSunday(byte data) {
        sch_record_sunday = data;
    }

    public synchronized static byte getSchRecSunday() {
        return sch_record_sunday;
    }

    public synchronized static void setSchRecMonday(byte data) {
        sch_record_monday = data;
    }

    public synchronized static byte getSchRecMonday() {
        return sch_record_monday;
    }

    public synchronized static void setSchRecTuesday(byte data) {
        sch_record_tuesday = data;
    }

    public synchronized static byte getSchRecTuesday() {
        return sch_record_tuesday;
    }

    public synchronized static void setSchRecWednesday(byte data) {
        sch_record_wednesday = data;
    }

    public synchronized static byte getSchRecWednesday() {
        return sch_record_wednesday;
    }

    public synchronized static void setSchRecThursday(byte data) {
        sch_record_thursday = data;
    }

    public synchronized static byte getSchRecThursday() {
        return sch_record_thursday;
    }

    public synchronized static void setSchRecFriday(byte data) {
        sch_record_friday = data;
    }

    public synchronized static byte getSchRecFriday() {
        return sch_record_friday;
    }

    public synchronized static void setSchRecSaturday(byte data) {
        sch_record_saturday = data;
    }

    public synchronized static byte getSchRecSaturday() {
        return sch_record_saturday;
    }

    public synchronized static void setRecExpiryDays(int data) {
        rec_expiry_days = data;
    }

    public synchronized static int getRecExpiryDays() {
        return rec_expiry_days;
    }

    public synchronized static void setRecExpiryDaysEventBased(int data) {
        rec_expiry_days_event = data;
    }

    public synchronized static int getRecExpiryDaysEventBased() {
        return rec_expiry_days_event;
    }

    public synchronized static void setEmergencyAlertCame(boolean data) {
        emergency_alert_came = data;
    }

    public synchronized static boolean getEmergencyAlertCame() {
        return emergency_alert_came;
    }

    public synchronized static void setEmergencyStateTimeDuration(byte data) {
        emergency_alert_state_time_duration = data;
    }

    public synchronized static byte getEmergencyStateTimeDuration() {
        return (byte) emergency_alert_state_time_duration;
    }

    public synchronized static void setEmergencyModeByPanicButton(byte data) {
        emergency_alert_panic_button = data;
    }

    public synchronized static byte getEmergencyModeByPanicButton() {
        return (byte) emergency_alert_panic_button;
    }

    public synchronized static void setEmergencyModeBySmsMode(byte data) {
        emergency_alert_sms_mode = data;
    }

    public synchronized static byte getEmergencyModeBySmsMode() {
        return (byte) emergency_alert_sms_mode;
    }

    public synchronized static void setDisBrdSetAddrData(String data) {
        setAddrDisbrdData = data;
    }

    public synchronized static String getDisBrdSetAddrData() {
        return setAddrDisbrdData;
    }

    public synchronized static void setStorage1Status(byte data) {
        storage1_status = data;
    }

    public synchronized static byte getStorage1Status() {
        return storage1_status;
    }

    public synchronized static void setStorage1MemStatus(byte data) {
        storage1_mem_status = data;
    }

    public synchronized static byte getStorage1MemStatus() {
        return storage1_mem_status;
    }

    public synchronized static void setStorage2Status(byte data) {
        storage2_status = data;
    }

    public synchronized static byte getStorage2Status() {
        return storage2_status;
    }

    public synchronized static void setStorage2MemStatus(byte data) {
        storage2_mem_status = data;
    }

    public synchronized static byte getStorage2MemStatus() {
        return storage2_mem_status;
    }

    public synchronized static void setStorageType(byte data) {
        storage_type = data;
    }

    public synchronized static byte getStorageType() {
        return storage_type;
    }

    public synchronized static void setOnvifSupported(boolean data) {
        onvif_support = data;
    }

    public synchronized static boolean getOnvifSupported() {
        return onvif_support;
    }

    public synchronized static void setSeparateGPS(boolean data) {
        separate_gps = data;
    }

    public synchronized static boolean getSeparateGPS() {
        return separate_gps;
    }

    public synchronized static boolean getPedEnable() {
        return PEDESTRIAN_DETECTION_AVAILABLE;
    }

    public static synchronized void setPedEnable(boolean val) {
        PEDESTRIAN_DETECTION_AVAILABLE = val;
    }

    public synchronized static void setGyroscope(boolean data) {
        gyroscope_enabled = data;
    }

    public synchronized static boolean getGyroscope() {
        return gyroscope_enabled;
    }

    public synchronized static void setCam1UserName(String name) {
        cam1UserName = name;
    }

    public synchronized static String getCam1UserName() {
        return cam1UserName;
    }

    public synchronized static void setCam2UserName(String name) {
        cam2UserName = name;
    }

    public synchronized static String getCam2UserName() {
        return cam2UserName;
    }

    public synchronized static void setCam3UserName(String name) {
        cam3UserName = name;
    }

    public synchronized static String getCam3UserName() {
        return cam3UserName;
    }

    public synchronized static void setCam4UserName(String name) {
        cam4UserName = name;
    }

    public synchronized static String getCam4UserName() {
        return cam4UserName;
    }

    public synchronized static void setCam5UserName(String name) {
        cam5UserName = name;
    }

    public synchronized static String getCam5UserName() {
        return cam5UserName;
    }

    public synchronized static void setCam6UserName(String name) {
        cam6UserName = name;
    }

    public synchronized static String getCam6UserName() {
        return cam6UserName;
    }

    public synchronized static void setCam7UserName(String name) {
        cam7UserName = name;
    }

    public synchronized static String getCam7UserName() {
        return cam7UserName;
    }

    public synchronized static void setCam8UserName(String name) {
        cam8UserName = name;
    }

    public synchronized static String getCam8UserName() {
        return cam8UserName;
    }

    public synchronized static void setCam1Pwd(String name) {
        cam1Pwd = name;
    }

    public synchronized static String getCam1Pwd() {
        return cam1Pwd;
    }

    public synchronized static void setCam2Pwd(String name) {
        cam2Pwd = name;
    }

    public synchronized static String getCam2Pwd() {
        return cam2Pwd;
    }

    public synchronized static void setCam3Pwd(String name) {
        cam3Pwd = name;
    }

    public synchronized static String getCam3Pwd() {
        return cam3Pwd;
    }

    public synchronized static void setCam4Pwd(String name) {
        cam4Pwd = name;
    }

    public synchronized static String getCam4Pwd() {
        return cam4Pwd;
    }

    public synchronized static void setCam5Pwd(String name) {
        cam5Pwd = name;
    }

    public synchronized static String getCam5Pwd() {
        return cam5Pwd;
    }

    public synchronized static void setCam6Pwd(String name) {
        cam6Pwd = name;
    }

    public synchronized static String getCam6Pwd() {
        return cam6Pwd;
    }

    public synchronized static void setCam7Pwd(String name) {
        cam7Pwd = name;
    }

    public synchronized static String getCam7Pwd() {
        return cam7Pwd;
    }

    public synchronized static void setCam8Pwd(String name) {
        cam8Pwd = name;
    }

    public synchronized static String getCam8Pwd() {
        return cam8Pwd;
    }

    public synchronized static void setCommMode(byte mode) {
        comm_mode_type = mode;
    }

    public synchronized static byte getCommMode() {
        return comm_mode_type;
    }

    public synchronized static void setSmsData(String mode) {
        sms_data_to_send = mode;
    }

    public synchronized static String getSmsData() {
        return sms_data_to_send;
    }

    public synchronized static void setSendSmsState(boolean mode) {
        send_sms_state = mode;
    }

    public synchronized static boolean getSendSmsState() {
        return send_sms_state;
    }

    public synchronized static void setSendSmsGreaterSymState(boolean mode) {
        send_sms_greater_sym_state = mode;
    }

    public synchronized static boolean getSendSmsGreaterSymState() {
        return send_sms_greater_sym_state;
    }

    public synchronized static void setSmsResponseCame(byte mode) {
        sms_response_came = mode;
    }

    public synchronized static byte getSmsResponseCame() {
        return sms_response_came;
    }

    public synchronized static boolean getLedDataResCame() {
        return led_data_res_came;
    }

    public synchronized static void setLedDataResCame(boolean data) {
        led_data_res_came = data;
    }

    /* public synchronized static boolean getLedDataResCame1() {
        return led_data_res_came1;
    }

    public synchronized static void setLedDataResCame1(boolean data) {
        led_data_res_came1 = data;
    }*/
    public synchronized static boolean getResetCanDataResCame() {
        return reset_can_data_res_came;
    }

    public synchronized static void setResetCanDataResCame(boolean data) {
        reset_can_data_res_came = data;
    }

    /*public synchronized static boolean getResetCanDataResCame1() {
        return reset_can_data_res_came1;
    }

    public synchronized static void setResetCanDataResCame1(boolean data) {
        reset_can_data_res_came1 = data;
    }*/
    public synchronized static boolean getVideoDig1FtpUploadEnable() {
        return video_dig1_ftpupload_enable;
    }

    public static synchronized void setVideoDig1FtpUploadEnable(boolean val) {
        video_dig1_ftpupload_enable = val;
    }

    public synchronized static boolean getVideoDig2FtpUploadEnable() {
        return video_dig2_ftpupload_enable;
    }

    public static synchronized void setVideoDig2FtpUploadEnable(boolean val) {
        video_dig2_ftpupload_enable = val;
    }

    public synchronized static boolean getVideoDig3FtpUploadEnable() {
        return video_dig3_ftpupload_enable;
    }

    public static synchronized void setVideoDig3FtpUploadEnable(boolean val) {
        video_dig3_ftpupload_enable = val;
    }

    public synchronized static boolean getVideoDig4FtpUploadEnable() {
        return video_dig4_ftpupload_enable;
    }

    public static synchronized void setVideoDig4FtpUploadEnable(boolean val) {
        video_dig4_ftpupload_enable = val;
    }

    //motion detection
    public static synchronized void setMotionEnableCam1(boolean val) {
        motion_enable_cam1 = val;
    }

    public synchronized static boolean getMotionEnableCam1() {
        return motion_enable_cam1;
    }

    public static synchronized void setMotionEnableCam2(boolean val) {
        motion_enable_cam2 = val;
    }

    public synchronized static boolean getMotionEnableCam2() {
        return motion_enable_cam2;
    }

    public static synchronized void setMotionEnableCam3(boolean val) {
        motion_enable_cam3 = val;
    }

    public synchronized static boolean getMotionEnableCam3() {
        return motion_enable_cam3;
    }

    public static synchronized void setMotionEnableCam4(boolean val) {
        motion_enable_cam4 = val;
    }

    public synchronized static boolean getMotionEnableCam4() {
        return motion_enable_cam4;
    }

    public static synchronized void setMotionEnableCam5(boolean val) {
        motion_enable_cam5 = val;
    }

    public synchronized static boolean getMotionEnableCam5() {
        return motion_enable_cam5;
    }

    public static synchronized void setMotionEnableCam6(boolean val) {
        motion_enable_cam6 = val;
    }

    public synchronized static boolean getMotionEnableCam6() {
        return motion_enable_cam6;
    }

    public static synchronized void setMotionEnableCam7(boolean val) {
        motion_enable_cam7 = val;
    }

    public synchronized static boolean getMotionEnableCam7() {
        return motion_enable_cam7;
    }

    public static synchronized void setMotionEnableCam8(boolean val) {
        motion_enable_cam8 = val;
    }

    public synchronized static boolean getMotionEnableCam8() {
        return motion_enable_cam8;
    }

    public synchronized static int getMotionX1Cam1() {
        return motion_x1_cam1;
    }

    public static synchronized void setMotionX1Cam1(int val) {
        motion_x1_cam1 = val;
    }

    public synchronized static int getMotionX2Cam1() {
        return motion_x2_cam1;
    }

    public static synchronized void setMotionX2Cam1(int val) {
        motion_x2_cam1 = val;
    }

    public synchronized static int getMotionY1Cam1() {
        return motion_y1_cam1;
    }

    public static synchronized void setMotionY1Cam1(int val) {
        motion_y1_cam1 = val;
    }

    public synchronized static int getMotionY2Cam1() {
        return motion_y2_cam1;
    }

    public static synchronized void setMotionY2Cam1(int val) {
        motion_y2_cam1 = val;
    }

    public synchronized static int getSensitivityCam1() {
        return motion_sensitivity_cam1;
    }

    public static synchronized void setSensitivityCam1(int val) {
        motion_sensitivity_cam1 = val;
    }

    public synchronized static int getThresholdCam1() {
        return motion_threshold_cam1;
    }

    public static synchronized void setThresholdCam1(int val) {
        motion_threshold_cam1 = val;
    }

    public synchronized static int getRecordTimeCam1() {
        return motion_recorded_time_cam1;
    }

    public static synchronized void setRecordTimeCam1(int val) {
        motion_recorded_time_cam1 = val;
    }

    public synchronized static void setCam_status_live(byte status) {
        cam_status_live = status;
    }

    public synchronized static int getMotionX1Cam2() {
        return motion_x1_cam2;
    }

    public static synchronized void setMotionX1Cam2(int val) {
        motion_x1_cam2 = val;
    }

    public synchronized static int getMotionX2Cam2() {
        return motion_x2_cam2;
    }

    public static synchronized void setMotionX2Cam2(int val) {
        motion_x2_cam2 = val;
    }

    public synchronized static int getMotionY1Cam2() {
        return motion_y1_cam2;
    }

    public static synchronized void setMotionY1Cam2(int val) {
        motion_y1_cam2 = val;
    }

    public synchronized static int getMotionY2Cam2() {
        return motion_y2_cam2;
    }

    public static synchronized void setMotionY2Cam2(int val) {
        motion_y2_cam2 = val;
    }

    public synchronized static int getSensitivityCam2() {
        return motion_sensitivity_cam2;
    }

    public static synchronized void setSensitivityCam2(int val) {
        motion_sensitivity_cam2 = val;
    }

    public synchronized static int getThresholdCam2() {
        return motion_threshold_cam2;
    }

    public static synchronized void setThresholdCam2(int val) {
        motion_threshold_cam2 = val;
    }

    public synchronized static int getRecordTimeCam2() {
        return motion_recorded_time_cam2;
    }

    public static synchronized void setRecordTimeCam2(int val) {
        motion_recorded_time_cam2 = val;
    }

    public synchronized static int getMotionX1Cam3() {
        return motion_x1_cam3;
    }

    public static synchronized void setMotionX1Cam3(int val) {
        motion_x1_cam3 = val;
    }

    public synchronized static int getMotionX2Cam3() {
        return motion_x2_cam3;
    }

    public static synchronized void setMotionX2Cam3(int val) {
        motion_x2_cam3 = val;
    }

    public synchronized static int getMotionY1Cam3() {
        return motion_y1_cam3;
    }

    public static synchronized void setMotionY1Cam3(int val) {
        motion_y1_cam3 = val;
    }

    public synchronized static int getMotionY2Cam3() {
        return motion_y2_cam3;
    }

    public static synchronized void setMotionY2Cam3(int val) {
        motion_y2_cam3 = val;
    }

    public synchronized static int getSensitivityCam3() {
        return motion_sensitivity_cam3;
    }

    public static synchronized void setSensitivityCam3(int val) {
        motion_sensitivity_cam3 = val;
    }

    public synchronized static int getThresholdCam3() {
        return motion_threshold_cam3;
    }

    public static synchronized void setThresholdCam3(int val) {
        motion_threshold_cam3 = val;
    }

    public synchronized static int getRecordTimeCam3() {
        return motion_recorded_time_cam3;
    }

    public static synchronized void setRecordTimeCam3(int val) {
        motion_recorded_time_cam3 = val;
    }

    public synchronized static int getMotionX1Cam4() {
        return motion_x1_cam4;
    }

    public static synchronized void setMotionX1Cam4(int val) {
        motion_x1_cam4 = val;
    }

    public synchronized static int getMotionX2Cam4() {
        return motion_x2_cam4;
    }

    public static synchronized void setMotionX2Cam4(int val) {
        motion_x2_cam4 = val;
    }

    public synchronized static int getMotionY1Cam4() {
        return motion_y1_cam4;
    }

    public static synchronized void setMotionY1Cam4(int val) {
        motion_y1_cam4 = val;
    }

    public synchronized static int getMotionY2Cam4() {
        return motion_y2_cam4;
    }

    public static synchronized void setMotionY2Cam4(int val) {
        motion_y2_cam4 = val;
    }

    public synchronized static int getSensitivityCam4() {
        return motion_sensitivity_cam4;
    }

    public static synchronized void setSensitivityCam4(int val) {
        motion_sensitivity_cam4 = val;
    }

    public synchronized static int getThresholdCam4() {
        return motion_threshold_cam4;
    }

    public static synchronized void setThresholdCam4(int val) {
        motion_threshold_cam4 = val;
    }

    public synchronized static int getRecordTimeCam4() {
        return motion_recorded_time_cam4;
    }

    public static synchronized void setRecordTimeCam4(int val) {
        motion_recorded_time_cam4 = val;
    }

    public synchronized static int getMotionX1Cam5() {
        return motion_x1_cam5;
    }

    public static synchronized void setMotionX1Cam5(int val) {
        motion_x1_cam5 = val;
    }

    public synchronized static int getMotionX2Cam5() {
        return motion_x2_cam5;
    }

    public static synchronized void setMotionX2Cam5(int val) {
        motion_x2_cam5 = val;
    }

    public synchronized static int getMotionY1Cam5() {
        return motion_y1_cam5;
    }

    public static synchronized void setMotionY1Cam5(int val) {
        motion_y1_cam5 = val;
    }

    public synchronized static int getMotionY2Cam5() {
        return motion_y2_cam5;
    }

    public static synchronized void setMotionY2Cam5(int val) {
        motion_y2_cam5 = val;
    }

    public synchronized static int getSensitivityCam5() {
        return motion_sensitivity_cam5;
    }

    public static synchronized void setSensitivityCam5(int val) {
        motion_sensitivity_cam5 = val;
    }

    public synchronized static int getThresholdCam5() {
        return motion_threshold_cam5;
    }

    public static synchronized void setThresholdCam5(int val) {
        motion_threshold_cam5 = val;
    }

    public synchronized static int getRecordTimeCam5() {
        return motion_recorded_time_cam5;
    }

    public static synchronized void setRecordTimeCam5(int val) {
        motion_recorded_time_cam5 = val;
    }

    public synchronized static int getMotionX1Cam6() {
        return motion_x1_cam6;
    }

    public static synchronized void setMotionX1Cam6(int val) {
        motion_x1_cam6 = val;
    }

    public synchronized static int getMotionX2Cam6() {
        return motion_x2_cam6;
    }

    public static synchronized void setMotionX2Cam6(int val) {
        motion_x2_cam6 = val;
    }

    public synchronized static int getMotionY1Cam6() {
        return motion_y1_cam6;
    }

    public static synchronized void setMotionY1Cam6(int val) {
        motion_y1_cam6 = val;
    }

    public synchronized static int getMotionY2Cam6() {
        return motion_y2_cam6;
    }

    public static synchronized void setMotionY2Cam6(int val) {
        motion_y2_cam6 = val;
    }

    public synchronized static int getSensitivityCam6() {
        return motion_sensitivity_cam6;
    }

    public static synchronized void setSensitivityCam6(int val) {
        motion_sensitivity_cam6 = val;
    }

    public synchronized static int getThresholdCam6() {
        return motion_threshold_cam6;
    }

    public static synchronized void setThresholdCam6(int val) {
        motion_threshold_cam6 = val;
    }

    public synchronized static int getRecordTimeCam6() {
        return motion_recorded_time_cam6;
    }

    public static synchronized void setRecordTimeCam6(int val) {
        motion_recorded_time_cam6 = val;
    }

    public synchronized static int getMotionX1Cam7() {
        return motion_x1_cam7;
    }

    public static synchronized void setMotionX1Cam7(int val) {
        motion_x1_cam7 = val;
    }

    public synchronized static int getMotionX2Cam7() {
        return motion_x2_cam7;
    }

    public static synchronized void setMotionX2Cam7(int val) {
        motion_x2_cam7 = val;
    }

    public synchronized static int getMotionY1Cam7() {
        return motion_y1_cam7;
    }

    public static synchronized void setMotionY1Cam7(int val) {
        motion_y1_cam7 = val;
    }

    public synchronized static int getMotionY2Cam7() {
        return motion_y2_cam7;
    }

    public static synchronized void setMotionY2Cam7(int val) {
        motion_y2_cam7 = val;
    }

    public synchronized static int getSensitivityCam7() {
        return motion_sensitivity_cam7;
    }

    public static synchronized void setSensitivityCam7(int val) {
        motion_sensitivity_cam7 = val;
    }

    public synchronized static int getThresholdCam7() {
        return motion_threshold_cam7;
    }

    public static synchronized void setThresholdCam7(int val) {
        motion_threshold_cam7 = val;
    }

    public synchronized static int getRecordTimeCam7() {
        return motion_recorded_time_cam7;
    }

    public static synchronized void setRecordTimeCam7(int val) {
        motion_recorded_time_cam7 = val;
    }

    public synchronized static int getMotionX1Cam8() {
        return motion_x1_cam8;
    }

    public static synchronized void setMotionX1Cam8(int val) {
        motion_x1_cam8 = val;
    }

    public synchronized static int getMotionX2Cam8() {
        return motion_x2_cam8;
    }

    public static synchronized void setMotionX2Cam8(int val) {
        motion_x2_cam8 = val;
    }

    public synchronized static int getMotionY1Cam8() {
        return motion_y1_cam8;
    }

    public static synchronized void setMotionY1Cam8(int val) {
        motion_y1_cam8 = val;
    }

    public synchronized static int getMotionY2Cam8() {
        return motion_y2_cam8;
    }

    public static synchronized void setMotionY2Cam8(int val) {
        motion_y2_cam8 = val;
    }

    public synchronized static int getSensitivityCam8() {
        return motion_sensitivity_cam8;
    }

    public static synchronized void setSensitivityCam8(int val) {
        motion_sensitivity_cam8 = val;
    }

    public synchronized static int getThresholdCam8() {
        return motion_threshold_cam8;
    }

    public static synchronized void setThresholdCam8(int val) {
        motion_threshold_cam8 = val;
    }

    public synchronized static int getRecordTimeCam8() {
        return motion_recorded_time_cam8;
    }

    public static synchronized void setRecordTimeCam8(int val) {
        motion_recorded_time_cam8 = val;
    }

    public synchronized static void setSmsSeqNo(int val) {
        sms_seq_no = val;
    }

    public synchronized static void incSmsSeqNo() {
        sms_seq_no++;
        if (sms_seq_no >= 9999) {
            sms_seq_no = 0;
        }
    }

    public synchronized static int getSmsSeqNo() {
        return sms_seq_no;
    }

    public synchronized static void setMotionRecording1Started(boolean val) {
        event_based_video_record_start1 = val;
    }

    public synchronized static boolean getMotionRecording1Started() {
        return event_based_video_record_start1;
    }

    public synchronized static void setMotionRecording2Started(boolean val) {
        event_based_video_record_start2 = val;
    }

    public synchronized static boolean getMotionRecording2Started() {
        return event_based_video_record_start2;
    }

    public synchronized static void setMotionRecording3Started(boolean val) {
        event_based_video_record_start3 = val;
    }

    public synchronized static boolean getMotionRecording3Started() {
        return event_based_video_record_start3;
    }

    public synchronized static void setMotionRecording4Started(boolean val) {
        event_based_video_record_start4 = val;
    }

    public synchronized static boolean getMotionRecording4Started() {
        return event_based_video_record_start4;
    }

    public synchronized static void setMotionRecording5Started(boolean val) {
        event_based_video_record_start5 = val;
    }

    public synchronized static boolean getMotionRecording5Started() {
        return event_based_video_record_start5;
    }

    public synchronized static void setMotionRecording6Started(boolean val) {
        event_based_video_record_start6 = val;
    }

    public synchronized static boolean getMotionRecording6Started() {
        return event_based_video_record_start6;
    }

    public synchronized static void setMotionRecording7Started(boolean val) {
        event_based_video_record_start7 = val;
    }

    public synchronized static boolean getMotionRecording7Started() {
        return event_based_video_record_start7;
    }

    public synchronized static void setMotionRecording8Started(boolean val) {
        event_based_video_record_start8 = val;
    }

    public synchronized static boolean getMotionRecording8Started() {
        return event_based_video_record_start8;
    }

    // travelled_distance  set and get methods
    public synchronized static void setGyroAngle(short value) {
        gyro_angle = value;
    }

    public synchronized static short getGyroAngle() {
        return gyro_angle;
    }

    public synchronized static void setCellTowerMode(byte val) {
        celltower_type = val;
    }

    public synchronized static byte getCellTowerMode() {
        return celltower_type;
    }

    public synchronized static void setForceSmsSend(boolean res) {
        force_sms_came = res;
    }

    public synchronized static boolean getForceSmsSend() {
        return force_sms_came;
    }

    public synchronized static void setSmsPhoneNo(String phno) {
        sms_ph_no = phno;
    }

    public synchronized static String getSmsPhoneNo() {
        return sms_ph_no;
    }

    public synchronized static void setQuecModuleRev(byte res) {
        module_rev = res;
    }

    public synchronized static byte getQuecModuleRev() {
        return module_rev;
    }

    public synchronized static byte getCam_status_live() {
        return cam_status_live;
    }

    public synchronized static void setCameraType(byte data) {
        camera_type = data;
    }

    public synchronized static byte getCameraType() {
        return camera_type;
    }

    //camera 1 configuration of camera paramters
    public synchronized static void setCam1WidthArr(String data) {
        cam1_width_arr = data;
    }

    public synchronized static String getCam1WidthArr() {
        return cam1_width_arr;
    }

    public synchronized static void setCam1HeightArr(String data) {
        cam1_height_arr = data;
    }

    public synchronized static String getCam1HeightArr() {
        return cam1_height_arr;
    }

    public synchronized static void setCam1QualityCtrlArr(String data) {  //VBR,CBR
        cam1_quality_control_arr = data;
    }

    public synchronized static String getCam1QualityCtrlArr() {
        return cam1_quality_control_arr;
    }

    public synchronized static void setCam1ConstBitrateArr(String data) {
        cam1_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam1ConstBitrateArr() {
        return cam1_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam1FixedQualityArr(String data) {
        cam1_fixed_quality_arr = data;
    }

    public synchronized static String getCam1FixedQualityArr() {
        return cam1_fixed_quality_arr;
    }

    public synchronized static void setCam1MaxFramerateArr(String data) {
        cam1_max_framerate_arr = data;
    }

    public synchronized static String getCam1MaxFramerateArr() {
        return cam1_max_framerate_arr;
    }

    public synchronized static void setCam1FrameIntervalArr(String data) {
        cam1_frame_interval_arr = data;
    }

    public synchronized static String getCam1FrameIntervalArr() {
        return cam1_frame_interval_arr;
    }

    //camera 2
    public synchronized static void setCam2WidthArr(String data) {
        cam2_width_arr = data;
    }

    public synchronized static String getCam2WidthArr() {
        return cam2_width_arr;
    }

    public synchronized static void setCam2HeightArr(String data) {
        cam2_height_arr = data;
    }

    public synchronized static String getCam2HeightArr() {
        return cam2_height_arr;
    }

    public synchronized static void setCam2QualityCtrlArr(String data) {  //VBR,CBR
        cam2_quality_control_arr = data;
    }

    public synchronized static String getCam2QualityCtrlArr() {
        return cam2_quality_control_arr;
    }

    public synchronized static void setCam2ConstBitrateArr(String data) {
        cam2_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam2ConstBitrateArr() {
        return cam2_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam2FixedQualityArr(String data) {
        cam2_fixed_quality_arr = data;
    }

    public synchronized static String getCam2FixedQualityArr() {
        return cam2_fixed_quality_arr;
    }

    public synchronized static void setCam2MaxFramerateArr(String data) {
        cam2_max_framerate_arr = data;
    }

    public synchronized static String getCam2MaxFramerateArr() {
        return cam2_max_framerate_arr;
    }

    public synchronized static void setCam2FrameIntervalArr(String data) {
        cam2_frame_interval_arr = data;
    }

    public synchronized static String getCam2FrameIntervalArr() {
        return cam2_frame_interval_arr;
    }

    //camera 3
    public synchronized static void setCam3WidthArr(String data) {
        cam3_width_arr = data;
    }

    public synchronized static String getCam3WidthArr() {
        return cam3_width_arr;
    }

    public synchronized static void setCam3HeightArr(String data) {
        cam3_height_arr = data;
    }

    public synchronized static String getCam3HeightArr() {
        return cam3_height_arr;
    }

    public synchronized static void setCam3QualityCtrlArr(String data) {  //VBR,CBR
        cam3_quality_control_arr = data;
    }

    public synchronized static String getCam3QualityCtrlArr() {
        return cam3_quality_control_arr;
    }

    public synchronized static void setCam3ConstBitrateArr(String data) {
        cam3_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam3ConstBitrateArr() {
        return cam3_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam3FixedQualityArr(String data) {
        cam3_fixed_quality_arr = data;
    }

    public synchronized static String getCam3FixedQualityArr() {
        return cam3_fixed_quality_arr;
    }

    public synchronized static void setCam3MaxFramerateArr(String data) {
        cam3_max_framerate_arr = data;
    }

    public synchronized static String getCam3MaxFramerateArr() {
        return cam3_max_framerate_arr;
    }

    public synchronized static void setCam3FrameIntervalArr(String data) {
        cam3_frame_interval_arr = data;
    }

    public synchronized static String getCam3FrameIntervalArr() {
        return cam3_frame_interval_arr;
    }

    //camera 4
    public synchronized static void setCam4WidthArr(String data) {
        cam4_width_arr = data;
    }

    public synchronized static String getCam4WidthArr() {
        return cam4_width_arr;
    }

    public synchronized static void setCam4HeightArr(String data) {
        cam4_height_arr = data;
    }

    public synchronized static String getCam4HeightArr() {
        return cam4_height_arr;
    }

    public synchronized static void setCam4QualityCtrlArr(String data) {  //VBR,CBR
        cam4_quality_control_arr = data;
    }

    public synchronized static String getCam4QualityCtrlArr() {
        return cam4_quality_control_arr;
    }

    public synchronized static void setCam4ConstBitrateArr(String data) {
        cam4_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam4ConstBitrateArr() {
        return cam4_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam4FixedQualityArr(String data) {
        cam4_fixed_quality_arr = data;
    }

    public synchronized static String getCam4FixedQualityArr() {
        return cam4_fixed_quality_arr;
    }

    public synchronized static void setCam4MaxFramerateArr(String data) {
        cam4_max_framerate_arr = data;
    }

    public synchronized static String getCam4MaxFramerateArr() {
        return cam4_max_framerate_arr;
    }

    public synchronized static void setCam4FrameIntervalArr(String data) {
        cam4_frame_interval_arr = data;
    }

    public synchronized static String getCam4FrameIntervalArr() {
        return cam4_frame_interval_arr;
    }

    //camera 5
    public synchronized static void setCam5WidthArr(String data) {
        cam5_width_arr = data;
    }

    public synchronized static String getCam5WidthArr() {
        return cam5_width_arr;
    }

    public synchronized static void setCam5HeightArr(String data) {
        cam5_height_arr = data;
    }

    public synchronized static String getCam5HeightArr() {
        return cam5_height_arr;
    }

    public synchronized static void setCam5QualityCtrlArr(String data) {  //VBR,CBR
        cam5_quality_control_arr = data;
    }

    public synchronized static String getCam5QualityCtrlArr() {
        return cam5_quality_control_arr;
    }

    public synchronized static void setCam5ConstBitrateArr(String data) {
        cam5_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam5ConstBitrateArr() {
        return cam5_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam5FixedQualityArr(String data) {
        cam5_fixed_quality_arr = data;
    }

    public synchronized static String getCam5FixedQualityArr() {
        return cam5_fixed_quality_arr;
    }

    public synchronized static void setCam5MaxFramerateArr(String data) {
        cam5_max_framerate_arr = data;
    }

    public synchronized static String getCam5MaxFramerateArr() {
        return cam5_max_framerate_arr;
    }

    public synchronized static void setCam5FrameIntervalArr(String data) {
        cam5_frame_interval_arr = data;
    }

    public synchronized static String getCam5FrameIntervalArr() {
        return cam5_frame_interval_arr;
    }

    //camera 6
    public synchronized static void setCam6WidthArr(String data) {
        cam6_width_arr = data;
    }

    public synchronized static String getCam6WidthArr() {
        return cam6_width_arr;
    }

    public synchronized static void setCam6HeightArr(String data) {
        cam6_height_arr = data;
    }

    public synchronized static String getCam6HeightArr() {
        return cam6_height_arr;
    }

    public synchronized static void setCam6QualityCtrlArr(String data) {  //VBR,CBR
        cam6_quality_control_arr = data;
    }

    public synchronized static String getCam6QualityCtrlArr() {
        return cam6_quality_control_arr;
    }

    public synchronized static void setCam6ConstBitrateArr(String data) {
        cam6_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam6ConstBitrateArr() {
        return cam6_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam6FixedQualityArr(String data) {
        cam6_fixed_quality_arr = data;
    }

    public synchronized static String getCam6FixedQualityArr() {
        return cam6_fixed_quality_arr;
    }

    public synchronized static void setCam6MaxFramerateArr(String data) {
        cam6_max_framerate_arr = data;
    }

    public synchronized static String getCam6MaxFramerateArr() {
        return cam6_max_framerate_arr;
    }

    public synchronized static void setCam6FrameIntervalArr(String data) {
        cam6_frame_interval_arr = data;
    }

    public synchronized static String getCam6FrameIntervalArr() {
        return cam6_frame_interval_arr;
    }

    //camera 7
    public synchronized static void setCam7WidthArr(String data) {
        cam7_width_arr = data;
    }

    public synchronized static String getCam7WidthArr() {
        return cam7_width_arr;
    }

    public synchronized static void setCam7HeightArr(String data) {
        cam7_height_arr = data;
    }

    public synchronized static String getCam7HeightArr() {
        return cam7_height_arr;
    }

    public synchronized static void setCam7QualityCtrlArr(String data) {  //VBR,CBR
        cam7_quality_control_arr = data;
    }

    public synchronized static String getCam7QualityCtrlArr() {
        return cam7_quality_control_arr;
    }

    public synchronized static void setCam7ConstBitrateArr(String data) {
        cam7_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam7ConstBitrateArr() {
        return cam7_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam7FixedQualityArr(String data) {
        cam7_fixed_quality_arr = data;
    }

    public synchronized static String getCam7FixedQualityArr() {
        return cam7_fixed_quality_arr;
    }

    public synchronized static void setCam7MaxFramerateArr(String data) {
        cam7_max_framerate_arr = data;
    }

    public synchronized static String getCam7MaxFramerateArr() {
        return cam7_max_framerate_arr;
    }

    public synchronized static void setCam7FrameIntervalArr(String data) {
        cam7_frame_interval_arr = data;
    }

    public synchronized static String getCam7FrameIntervalArr() {
        return cam7_frame_interval_arr;
    }

    //camera 8
    public synchronized static void setCam8WidthArr(String data) {
        cam8_width_arr = data;
    }

    public synchronized static String getCam8WidthArr() {
        return cam8_width_arr;
    }

    public synchronized static void setCam8HeightArr(String data) {
        cam8_height_arr = data;
    }

    public synchronized static String getCam8HeightArr() {
        return cam8_height_arr;
    }

    public synchronized static void setCam8QualityCtrlArr(String data) {  //VBR,CBR
        cam8_quality_control_arr = data;
    }

    public synchronized static String getCam8QualityCtrlArr() {
        return cam8_quality_control_arr;
    }

    public synchronized static void setCam8ConstBitrateArr(String data) {
        cam8_constant_bitrate_min_max_arr = data;
    }

    public synchronized static String getCam8ConstBitrateArr() {
        return cam8_constant_bitrate_min_max_arr;
    }

    public synchronized static void setCam8FixedQualityArr(String data) {
        cam8_fixed_quality_arr = data;
    }

    public synchronized static String getCam8FixedQualityArr() {
        return cam8_fixed_quality_arr;
    }

    public synchronized static void setCam8MaxFramerateArr(String data) {
        cam8_max_framerate_arr = data;
    }

    public synchronized static String getCam8MaxFramerateArr() {
        return cam8_max_framerate_arr;
    }

    public synchronized static void setCam8FrameIntervalArr(String data) {
        cam8_frame_interval_arr = data;
    }

    public synchronized static String getCam8FrameIntervalArr() {
        return cam8_frame_interval_arr;
    }

    //camera 1 configuration of camera paramters
    public synchronized static void setCam1WidthArrSub(String data) {
        cam1_width_arr_sub = data;
    }

    public synchronized static String getCam1WidthArrSub() {
        return cam1_width_arr_sub;
    }

    public synchronized static void setCam1HeightArrSub(String data) {
        cam1_height_arr_sub = data;
    }

    public synchronized static String getCam1HeightArrSub() {
        return cam1_height_arr_sub;
    }

    public synchronized static void setCam1QualityCtrlArrSub(String data) {  //VBR,CBR
        cam1_quality_control_arr_sub = data;
    }

    public synchronized static String getCam1QualityCtrlArrSub() {
        return cam1_quality_control_arr_sub;
    }

    public synchronized static void setCam1ConstBitrateArrSub(String data) {
        cam1_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam1ConstBitrateArrSub() {
        return cam1_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam1FixedQualityArrSub(String data) {
        cam1_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam1FixedQualityArrSub() {
        return cam1_fixed_quality_arr_sub;
    }

    public synchronized static void setCam1MaxFramerateArrSub(String data) {
        cam1_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam1MaxFramerateArrSub() {
        return cam1_max_framerate_arr_sub;
    }

    public synchronized static void setCam1FrameIntervalArrSub(String data) {
        cam1_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam1FrameIntervalArrSub() {
        return cam1_frame_interval_arr_sub;
    }

    //camera 2
    public synchronized static void setCam2WidthArrSub(String data) {
        cam2_width_arr_sub = data;
    }

    public synchronized static String getCam2WidthArrSub() {
        return cam2_width_arr_sub;
    }

    public synchronized static void setCam2HeightArrSub(String data) {
        cam2_height_arr_sub = data;
    }

    public synchronized static String getCam2HeightArrSub() {
        return cam2_height_arr_sub;
    }

    public synchronized static void setCam2QualityCtrlArrSub(String data) {  //VBR,CBR
        cam2_quality_control_arr_sub = data;
    }

    public synchronized static String getCam2QualityCtrlArrSub() {
        return cam2_quality_control_arr_sub;
    }

    public synchronized static void setCam2ConstBitrateArrSub(String data) {
        cam2_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam2ConstBitrateArrSub() {
        return cam2_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam2FixedQualityArrSub(String data) {
        cam2_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam2FixedQualityArrSub() {
        return cam2_fixed_quality_arr_sub;
    }

    public synchronized static void setCam2MaxFramerateArrSub(String data) {
        cam2_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam2MaxFramerateArrSub() {
        return cam2_max_framerate_arr_sub;
    }

    public synchronized static void setCam2FrameIntervalArrSub(String data) {
        cam2_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam2FrameIntervalArrSub() {
        return cam2_frame_interval_arr_sub;
    }

    //camera 3
    public synchronized static void setCam3WidthArrSub(String data) {
        cam3_width_arr_sub = data;
    }

    public synchronized static String getCam3WidthArrSub() {
        return cam3_width_arr_sub;
    }

    public synchronized static void setCam3HeightArrSub(String data) {
        cam3_height_arr_sub = data;
    }

    public synchronized static String getCam3HeightArrSub() {
        return cam3_height_arr_sub;
    }

    public synchronized static void setCam3QualityCtrlArrSub(String data) {  //VBR,CBR
        cam3_quality_control_arr_sub = data;
    }

    public synchronized static String getCam3QualityCtrlArrSub() {
        return cam3_quality_control_arr_sub;
    }

    public synchronized static void setCam3ConstBitrateArrSub(String data) {
        cam3_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam3ConstBitrateArrSub() {
        return cam3_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam3FixedQualityArrSub(String data) {
        cam3_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam3FixedQualityArrSub() {
        return cam3_fixed_quality_arr_sub;
    }

    public synchronized static void setCam3MaxFramerateArrSub(String data) {
        cam3_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam3MaxFramerateArrSub() {
        return cam3_max_framerate_arr_sub;
    }

    public synchronized static void setCam3FrameIntervalArrSub(String data) {
        cam3_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam3FrameIntervalArrSub() {
        return cam3_frame_interval_arr_sub;
    }

    //camera 4
    public synchronized static void setCam4WidthArrSub(String data) {
        cam4_width_arr_sub = data;
    }

    public synchronized static String getCam4WidthArrSub() {
        return cam4_width_arr_sub;
    }

    public synchronized static void setCam4HeightArrSub(String data) {
        cam4_height_arr_sub = data;
    }

    public synchronized static String getCam4HeightArrSub() {
        return cam4_height_arr_sub;
    }

    public synchronized static void setCam4QualityCtrlArrSub(String data) {  //VBR,CBR
        cam4_quality_control_arr_sub = data;
    }

    public synchronized static String getCam4QualityCtrlArrSub() {
        return cam4_quality_control_arr_sub;
    }

    public synchronized static void setCam4ConstBitrateArrSub(String data) {
        cam4_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam4ConstBitrateArrSub() {
        return cam4_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam4FixedQualityArrSub(String data) {
        cam4_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam4FixedQualityArrSub() {
        return cam4_fixed_quality_arr_sub;
    }

    public synchronized static void setCam4MaxFramerateArrSub(String data) {
        cam4_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam4MaxFramerateArrSub() {
        return cam4_max_framerate_arr_sub;
    }

    public synchronized static void setCam4FrameIntervalArrSub(String data) {
        cam4_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam4FrameIntervalArrSub() {
        return cam4_frame_interval_arr_sub;
    }

    //camera 5
    public synchronized static void setCam5WidthArrSub(String data) {
        cam5_width_arr_sub = data;
    }

    public synchronized static String getCam5WidthArrSub() {
        return cam5_width_arr_sub;
    }

    public synchronized static void setCam5HeightArrSub(String data) {
        cam5_height_arr_sub = data;
    }

    public synchronized static String getCam5HeightArrSub() {
        return cam5_height_arr_sub;
    }

    public synchronized static void setCam5QualityCtrlArrSub(String data) {  //VBR,CBR
        cam5_quality_control_arr_sub = data;
    }

    public synchronized static String getCam5QualityCtrlArrSub() {
        return cam5_quality_control_arr_sub;
    }

    public synchronized static void setCam5ConstBitrateArrSub(String data) {
        cam5_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam5ConstBitrateArrSub() {
        return cam5_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam5FixedQualityArrSub(String data) {
        cam5_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam5FixedQualityArrSub() {
        return cam5_fixed_quality_arr_sub;
    }

    public synchronized static void setCam5MaxFramerateArrSub(String data) {
        cam5_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam5MaxFramerateArrSub() {
        return cam5_max_framerate_arr_sub;
    }

    public synchronized static void setCam5FrameIntervalArrSub(String data) {
        cam5_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam5FrameIntervalArrSub() {
        return cam5_frame_interval_arr_sub;
    }

    //camera 6
    public synchronized static void setCam6WidthArrSub(String data) {
        cam6_width_arr_sub = data;
    }

    public synchronized static String getCam6WidthArrSub() {
        return cam6_width_arr_sub;
    }

    public synchronized static void setCam6HeightArrSub(String data) {
        cam6_height_arr_sub = data;
    }

    public synchronized static String getCam6HeightArrSub() {
        return cam6_height_arr_sub;
    }

    public synchronized static void setCam6QualityCtrlArrSub(String data) {  //VBR,CBR
        cam6_quality_control_arr_sub = data;
    }

    public synchronized static String getCam6QualityCtrlArrSub() {
        return cam6_quality_control_arr_sub;
    }

    public synchronized static void setCam6ConstBitrateArrSub(String data) {
        cam6_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam6ConstBitrateArrSub() {
        return cam6_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam6FixedQualityArrSub(String data) {
        cam6_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam6FixedQualityArrSub() {
        return cam6_fixed_quality_arr_sub;
    }

    public synchronized static void setCam6MaxFramerateArrSub(String data) {
        cam6_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam6MaxFramerateArrSub() {
        return cam6_max_framerate_arr_sub;
    }

    public synchronized static void setCam6FrameIntervalArrSub(String data) {
        cam6_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam6FrameIntervalArrSub() {
        return cam6_frame_interval_arr_sub;
    }

    //camera 7
    public synchronized static void setCam7WidthArrSub(String data) {
        cam7_width_arr_sub = data;
    }

    public synchronized static String getCam7WidthArrSub() {
        return cam7_width_arr_sub;
    }

    public synchronized static void setCam7HeightArrSub(String data) {
        cam7_height_arr_sub = data;
    }

    public synchronized static String getCam7HeightArrSub() {
        return cam7_height_arr_sub;
    }

    public synchronized static void setCam7QualityCtrlArrSub(String data) {  //VBR,CBR
        cam7_quality_control_arr_sub = data;
    }

    public synchronized static String getCam7QualityCtrlArrSub() {
        return cam7_quality_control_arr_sub;
    }

    public synchronized static void setCam7ConstBitrateArrSub(String data) {
        cam7_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam7ConstBitrateArrSub() {
        return cam7_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam7FixedQualityArrSub(String data) {
        cam7_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam7FixedQualityArrSub() {
        return cam7_fixed_quality_arr_sub;
    }

    public synchronized static void setCam7MaxFramerateArrSub(String data) {
        cam7_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam7MaxFramerateArrSub() {
        return cam7_max_framerate_arr_sub;
    }

    public synchronized static void setCam7FrameIntervalArrSub(String data) {
        cam7_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam7FrameIntervalArrSub() {
        return cam7_frame_interval_arr_sub;
    }

    //camera 8
    public synchronized static void setCam8WidthArrSub(String data) {
        cam8_width_arr_sub = data;
    }

    public synchronized static String getCam8WidthArrSub() {
        return cam8_width_arr_sub;
    }

    public synchronized static void setCam8HeightArrSub(String data) {
        cam8_height_arr_sub = data;
    }

    public synchronized static String getCam8HeightArrSub() {
        return cam8_height_arr_sub;
    }

    public synchronized static void setCam8QualityCtrlArrSub(String data) {  //VBR,CBR
        cam8_quality_control_arr_sub = data;
    }

    public synchronized static String getCam8QualityCtrlArrSub() {
        return cam8_quality_control_arr_sub;
    }

    public synchronized static void setCam8ConstBitrateArrSub(String data) {
        cam8_constant_bitrate_min_max_arr_sub = data;
    }

    public synchronized static String getCam8ConstBitrateArrSub() {
        return cam8_constant_bitrate_min_max_arr_sub;
    }

    public synchronized static void setCam8FixedQualityArrSub(String data) {
        cam8_fixed_quality_arr_sub = data;
    }

    public synchronized static String getCam8FixedQualityArrSub() {
        return cam8_fixed_quality_arr_sub;
    }

    public synchronized static void setCam8MaxFramerateArrSub(String data) {
        cam8_max_framerate_arr_sub = data;
    }

    public synchronized static String getCam8MaxFramerateArrSub() {
        return cam8_max_framerate_arr_sub;
    }

    public synchronized static void setCam8FrameIntervalArrSub(String data) {
        cam8_frame_interval_arr_sub = data;
    }

    public synchronized static String getCam8FrameIntervalArrSub() {
        return cam8_frame_interval_arr_sub;
    }

    public synchronized static void setSnapDelNoDays(int no_days) {
        snap_del_no_days = no_days;
    }

    public synchronized static int getSnapDelNoDays() {
        return snap_del_no_days;
    }

    public synchronized static void setGyroValuesActivated(boolean val) {
        gyro_values_activated = val;
    }

    public synchronized static boolean getGyroValuesActivated() {
        return gyro_values_activated;
    }

    //line detection
    public static synchronized void setLinedetEnableCam1(boolean val) {
        linedet_enable_cam1 = val;
    }

    public synchronized static boolean getLinedetEnableCam1() {
        return linedet_enable_cam1;
    }

    public static synchronized void setLinedetEnableCam2(boolean val) {
        linedet_enable_cam2 = val;
    }

    public synchronized static boolean getLinedetEnableCam2() {
        return linedet_enable_cam2;
    }

    public static synchronized void setLinedetEnableCam3(boolean val) {
        linedet_enable_cam3 = val;
    }

    public synchronized static boolean getLinedetEnableCam3() {
        return linedet_enable_cam3;
    }

    public static synchronized void setLinedetEnableCam4(boolean val) {
        linedet_enable_cam4 = val;
    }

    public synchronized static boolean getLinedetEnableCam4() {
        return linedet_enable_cam4;
    }

    public static synchronized void setLinedetEnableCam5(boolean val) {
        linedet_enable_cam5 = val;
    }

    public synchronized static boolean getLinedetEnableCam5() {
        return linedet_enable_cam5;
    }

    public static synchronized void setLinedetEnableCam6(boolean val) {
        linedet_enable_cam6 = val;
    }

    public synchronized static boolean getLinedetEnableCam6() {
        return linedet_enable_cam6;
    }

    public static synchronized void setLinedetEnableCam7(boolean val) {
        linedet_enable_cam7 = val;
    }

    public synchronized static boolean getLinedetEnableCam7() {
        return linedet_enable_cam7;
    }

    public static synchronized void setLinedetEnableCam8(boolean val) {
        linedet_enable_cam8 = val;
    }

    public synchronized static boolean getLinedetEnableCam8() {
        return linedet_enable_cam8;
    }

    //line detected or not
    public static synchronized void setLinedetDetectedCam1(boolean val) {
        linedet_detected_cam1 = val;
    }

    public synchronized static boolean getLinedetDetectedCam1() {
        return linedet_detected_cam1;
    }

    public static synchronized void setLinedetDetectedCam2(boolean val) {
        linedet_detected_cam2 = val;
    }

    public synchronized static boolean getLinedetDetectedCam2() {
        return linedet_detected_cam2;
    }

    public static synchronized void setLinedetDetectedCam3(boolean val) {
        linedet_detected_cam3 = val;
    }

    public synchronized static boolean getLinedetDetectedCam3() {
        return linedet_detected_cam3;
    }

    public static synchronized void setLinedetDetectedCam4(boolean val) {
        linedet_detected_cam4 = val;
    }

    public synchronized static boolean getLinedetDetectedCam4() {
        return linedet_detected_cam4;
    }

    public static synchronized void setLinedetDetectedCam5(boolean val) {
        linedet_detected_cam5 = val;
    }

    public synchronized static boolean getLinedetDetectedCam5() {
        return linedet_detected_cam5;
    }

    public static synchronized void setLinedetDetectedCam6(boolean val) {
        linedet_detected_cam6 = val;
    }

    public synchronized static boolean getLinedetDetectedCam6() {
        return linedet_detected_cam6;
    }

    public static synchronized void setLinedetDetectedCam7(boolean val) {
        linedet_detected_cam7 = val;
    }

    public synchronized static boolean getLinedetDetectedCam7() {
        return linedet_detected_cam7;
    }

    public static synchronized void setLinedetDetectedCam8(boolean val) {
        linedet_detected_cam8 = val;
    }

    public synchronized static boolean getLinedetDetectedCam8() {
        return linedet_detected_cam8;
    }

    //display /alarm time after line detection
    //line detected or not
    public static synchronized void setLinedetDisplayalarmCam1(int val) {
        linedet_displayalarm_time_cam1 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam1() {
        return linedet_displayalarm_time_cam1;
    }

    public static synchronized void setLinedetDisplayalarmCam2(int val) {
        linedet_displayalarm_time_cam2 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam2() {
        return linedet_displayalarm_time_cam2;
    }

    public static synchronized void setLinedetDisplayalarmCam3(int val) {
        linedet_displayalarm_time_cam3 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam3() {
        return linedet_displayalarm_time_cam3;
    }

    public static synchronized void setLinedetDisplayalarmCam4(int val) {
        linedet_displayalarm_time_cam4 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam4() {
        return linedet_displayalarm_time_cam4;
    }

    public static synchronized void setLinedetDisplayalarmCam5(int val) {
        linedet_displayalarm_time_cam5 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam5() {
        return linedet_displayalarm_time_cam5;
    }

    public static synchronized void setLinedetDisplayalarmCam6(int val) {
        linedet_displayalarm_time_cam6 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam6() {
        return linedet_displayalarm_time_cam6;
    }

    public static synchronized void setLinedetDisplayalarmCam7(int val) {
        linedet_displayalarm_time_cam7 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam7() {
        return linedet_displayalarm_time_cam7;
    }

    public static synchronized void setLinedetDisplayalarmCam8(int val) {
        linedet_displayalarm_time_cam8 = val;
    }

    public synchronized static int getLinedetDisplayalarmCam8() {
        return linedet_displayalarm_time_cam8;
    }

    public synchronized static int getLinedetX1Cam1() {
        return linedet_x1_cam1;
    }

    public static synchronized void setLinedetX1Cam1(int val) {
        linedet_x1_cam1 = val;
    }

    public synchronized static int getLinedetX2Cam1() {
        return linedet_x2_cam1;
    }

    public static synchronized void setLinedetX2Cam1(int val) {
        linedet_x2_cam1 = val;
    }

    public synchronized static int getLinedetY1Cam1() {
        return linedet_y1_cam1;
    }

    public static synchronized void setLinedetY1Cam1(int val) {
        linedet_y1_cam1 = val;
    }

    public synchronized static int getLinedetY2Cam1() {
        return linedet_y2_cam1;
    }

    public static synchronized void setLinedetY2Cam1(int val) {
        linedet_y2_cam1 = val;
    }

    public synchronized static int getLinedetSensitivityCam1() {
        return linedet_sensitivity_cam1;
    }

    public static synchronized void setLinedetSensitivityCam1(int val) {
        linedet_sensitivity_cam1 = val;
    }

    public synchronized static int getLinedetThresholdCam1() {
        return linedet_threshold_cam1;
    }

    public static synchronized void setLinedetThresholdCam1(int val) {
        linedet_threshold_cam1 = val;
    }

    public synchronized static int getLinedetRecordTimeCam1() {
        return linedet_recorded_time_cam1;
    }

    public static synchronized void setLinedetRecordTimeCam1(int val) {
        linedet_recorded_time_cam1 = val;
    }

    public synchronized static int getLinedetX1Cam2() {
        return linedet_x1_cam2;
    }

    public static synchronized void setLinedetX1Cam2(int val) {
        linedet_x1_cam2 = val;
    }

    public synchronized static int getLinedetX2Cam2() {
        return linedet_x2_cam2;
    }

    public static synchronized void setLinedetX2Cam2(int val) {
        linedet_x2_cam2 = val;
    }

    public synchronized static int getLinedetY1Cam2() {
        return linedet_y1_cam2;
    }

    public static synchronized void setLinedetY1Cam2(int val) {
        linedet_y1_cam2 = val;
    }

    public synchronized static int getLinedetY2Cam2() {
        return linedet_y2_cam2;
    }

    public static synchronized void setLinedetY2Cam2(int val) {
        linedet_y2_cam2 = val;
    }

    public synchronized static int getLinedetSensitivityCam2() {
        return linedet_sensitivity_cam2;
    }

    public static synchronized void setLinedetSensitivityCam2(int val) {
        linedet_sensitivity_cam2 = val;
    }

    public synchronized static int getLinedetThresholdCam2() {
        return linedet_threshold_cam2;
    }

    public static synchronized void setLinedetThresholdCam2(int val) {
        linedet_threshold_cam2 = val;
    }

    public synchronized static int getLinedetRecordTimeCam2() {
        return linedet_recorded_time_cam2;
    }

    public static synchronized void setLinedetRecordTimeCam2(int val) {
        linedet_recorded_time_cam2 = val;
    }

    public synchronized static int getLinedetX1Cam3() {
        return linedet_x1_cam3;
    }

    public static synchronized void setLinedetX1Cam3(int val) {
        linedet_x1_cam3 = val;
    }

    public synchronized static int getLinedetX2Cam3() {
        return linedet_x2_cam3;
    }

    public static synchronized void setLinedetX2Cam3(int val) {
        linedet_x2_cam3 = val;
    }

    public synchronized static int getLinedetY1Cam3() {
        return linedet_y1_cam3;
    }

    public static synchronized void setLinedetY1Cam3(int val) {
        linedet_y1_cam3 = val;
    }

    public synchronized static int getLinedetY2Cam3() {
        return linedet_y2_cam3;
    }

    public static synchronized void setLinedetY2Cam3(int val) {
        linedet_y2_cam3 = val;
    }

    public synchronized static int getLinedetSensitivityCam3() {
        return linedet_sensitivity_cam3;
    }

    public static synchronized void setLinedetSensitivityCam3(int val) {
        linedet_sensitivity_cam3 = val;
    }

    public synchronized static int getLinedetThresholdCam3() {
        return linedet_threshold_cam3;
    }

    public static synchronized void setLinedetThresholdCam3(int val) {
        linedet_threshold_cam3 = val;
    }

    public synchronized static int getLinedetRecordTimeCam3() {
        return linedet_recorded_time_cam3;
    }

    public static synchronized void setLinedetRecordTimeCam3(int val) {
        linedet_recorded_time_cam3 = val;
    }

    public synchronized static int getLinedetX1Cam4() {
        return linedet_x1_cam4;
    }

    public static synchronized void setLinedetX1Cam4(int val) {
        linedet_x1_cam4 = val;
    }

    public synchronized static int getLinedetX2Cam4() {
        return linedet_x2_cam4;
    }

    public static synchronized void setLinedetX2Cam4(int val) {
        linedet_x2_cam4 = val;
    }

    public synchronized static int getLinedetY1Cam4() {
        return linedet_y1_cam4;
    }

    public static synchronized void setLinedetY1Cam4(int val) {
        linedet_y1_cam4 = val;
    }

    public synchronized static int getLinedetY2Cam4() {
        return linedet_y2_cam4;
    }

    public static synchronized void setLinedetY2Cam4(int val) {
        linedet_y2_cam4 = val;
    }

    public synchronized static int getLinedetSensitivityCam4() {
        return linedet_sensitivity_cam4;
    }

    public static synchronized void setLinedetSensitivityCam4(int val) {
        linedet_sensitivity_cam4 = val;
    }

    public synchronized static int getLinedetThresholdCam4() {
        return linedet_threshold_cam4;
    }

    public static synchronized void setLinedetThresholdCam4(int val) {
        linedet_threshold_cam4 = val;
    }

    public synchronized static int getLinedetRecordTimeCam4() {
        return linedet_recorded_time_cam4;
    }

    public static synchronized void setLinedetRecordTimeCam4(int val) {
        linedet_recorded_time_cam4 = val;
    }

    public synchronized static int getLinedetX1Cam5() {
        return linedet_x1_cam5;
    }

    public static synchronized void setLinedetX1Cam5(int val) {
        linedet_x1_cam5 = val;
    }

    public synchronized static int getLinedetX2Cam5() {
        return linedet_x2_cam5;
    }

    public static synchronized void setLinedetX2Cam5(int val) {
        linedet_x2_cam5 = val;
    }

    public synchronized static int getLinedetY1Cam5() {
        return linedet_y1_cam5;
    }

    public static synchronized void setLinedetY1Cam5(int val) {
        linedet_y1_cam5 = val;
    }

    public synchronized static int getLinedetY2Cam5() {
        return linedet_y2_cam5;
    }

    public static synchronized void setLinedetY2Cam5(int val) {
        linedet_y2_cam5 = val;
    }

    public synchronized static int getLinedetSensitivityCam5() {
        return linedet_sensitivity_cam5;
    }

    public static synchronized void setLinedetSensitivityCam5(int val) {
        linedet_sensitivity_cam5 = val;
    }

    public synchronized static int getLinedetThresholdCam5() {
        return linedet_threshold_cam5;
    }

    public static synchronized void setLinedetThresholdCam5(int val) {
        linedet_threshold_cam5 = val;
    }

    public synchronized static int getLinedetRecordTimeCam5() {
        return linedet_recorded_time_cam5;
    }

    public static synchronized void setLinedetRecordTimeCam5(int val) {
        linedet_recorded_time_cam5 = val;
    }

    public synchronized static int getLinedetX1Cam6() {
        return linedet_x1_cam6;
    }

    public static synchronized void setLinedetX1Cam6(int val) {
        linedet_x1_cam6 = val;
    }

    public synchronized static int getLinedetX2Cam6() {
        return linedet_x2_cam6;
    }

    public static synchronized void setLinedetX2Cam6(int val) {
        linedet_x2_cam6 = val;
    }

    public synchronized static int getLinedetY1Cam6() {
        return linedet_y1_cam6;
    }

    public static synchronized void setLinedetY1Cam6(int val) {
        linedet_y1_cam6 = val;
    }

    public synchronized static int getLinedetY2Cam6() {
        return linedet_y2_cam6;
    }

    public static synchronized void setLinedetY2Cam6(int val) {
        linedet_y2_cam6 = val;
    }

    public synchronized static int getLinedetSensitivityCam6() {
        return linedet_sensitivity_cam6;
    }

    public static synchronized void setLinedetSensitivityCam6(int val) {
        linedet_sensitivity_cam6 = val;
    }

    public synchronized static int getLinedetThresholdCam6() {
        return linedet_threshold_cam6;
    }

    public static synchronized void setLinedetThresholdCam6(int val) {
        linedet_threshold_cam6 = val;
    }

    public synchronized static int getLinedetRecordTimeCam6() {
        return linedet_recorded_time_cam6;
    }

    public static synchronized void setLinedetRecordTimeCam6(int val) {
        linedet_recorded_time_cam6 = val;
    }

    public synchronized static int getLinedetX1Cam7() {
        return linedet_x1_cam7;
    }

    public static synchronized void setLinedetX1Cam7(int val) {
        linedet_x1_cam7 = val;
    }

    public synchronized static int getLinedetX2Cam7() {
        return linedet_x2_cam7;
    }

    public static synchronized void setLinedetX2Cam7(int val) {
        linedet_x2_cam7 = val;
    }

    public synchronized static int getLinedetY1Cam7() {
        return linedet_y1_cam7;
    }

    public static synchronized void setLinedetY1Cam7(int val) {
        linedet_y1_cam7 = val;
    }

    public synchronized static int getLinedetY2Cam7() {
        return linedet_y2_cam7;
    }

    public static synchronized void setLinedetY2Cam7(int val) {
        linedet_y2_cam7 = val;
    }

    public synchronized static int getLinedetSensitivityCam7() {
        return linedet_sensitivity_cam7;
    }

    public static synchronized void setLinedetSensitivityCam7(int val) {
        linedet_sensitivity_cam7 = val;
    }

    public synchronized static int getLinedetThresholdCam7() {
        return linedet_threshold_cam7;
    }

    public static synchronized void setLinedetThresholdCam7(int val) {
        linedet_threshold_cam7 = val;
    }

    public synchronized static int getLinedetRecordTimeCam7() {
        return linedet_recorded_time_cam7;
    }

    public static synchronized void setLinedetRecordTimeCam7(int val) {
        linedet_recorded_time_cam7 = val;
    }

    public synchronized static int getLinedetX1Cam8() {
        return linedet_x1_cam8;
    }

    public static synchronized void setLinedetX1Cam8(int val) {
        linedet_x1_cam8 = val;
    }

    public synchronized static int getLinedetX2Cam8() {
        return linedet_x2_cam8;
    }

    public static synchronized void setLinedetX2Cam8(int val) {
        linedet_x2_cam8 = val;
    }

    public synchronized static int getLinedetY1Cam8() {
        return linedet_y1_cam8;
    }

    public static synchronized void setLinedetY1Cam8(int val) {
        linedet_y1_cam8 = val;
    }

    public synchronized static int getLinedetY2Cam8() {
        return linedet_y2_cam8;
    }

    public static synchronized void setLinedetY2Cam8(int val) {
        linedet_y2_cam8 = val;
    }

    public synchronized static int getLinedetSensitivityCam8() {
        return linedet_sensitivity_cam8;
    }

    public static synchronized void setLinedetSensitivityCam8(int val) {
        linedet_sensitivity_cam8 = val;
    }

    public synchronized static int getLinedetThresholdCam8() {
        return linedet_threshold_cam8;
    }

    public static synchronized void setLinedetThresholdCam8(int val) {
        linedet_threshold_cam8 = val;
    }

    public synchronized static int getLinedetRecordTimeCam8() {
        return linedet_recorded_time_cam8;
    }

    public static synchronized void setLinedetRecordTimeCam8(int val) {
        linedet_recorded_time_cam8 = val;
    }

    public synchronized static boolean getFmRadioStatus() {
        return fm_radio_start;
    }

    public static synchronized void setFmRadioStatus(boolean val) {
        fm_radio_start = val;
    }

    public synchronized static boolean getEmbeddedSimCmdsStart() {
        return embedded_sim_commands_start;
    }

    public static synchronized void setEmbeddedSimCmdsStart(boolean val) {
        embedded_sim_commands_start = val;
    }

    public synchronized static String getEmbeddedSimCmdsData() {
        return embedded_sim_commands_data;
    }

    public static synchronized void setEmbeddedSimCmdsData(String val) {
        embedded_sim_commands_data = val;
    }

    public synchronized static byte getEmbNorSimType() {
        return embedded_normal_sim_type;
    }

    public static synchronized void setEmbNorSimType(byte val) {
        embedded_normal_sim_type = val;
    }

    public synchronized static byte getEmbSimAutoSwitch() {
        return embedded_sim_autoswitch;
    }

    public static synchronized void setEmbSimAutoSwitch(byte val) {
        embedded_sim_autoswitch = val;
    }

    public synchronized static byte getEmbSimFixedSwitchMode() {
        return embedded_sim_switch_mode;
    }

    public static synchronized void setEmbSimFixedSwitchMode(byte val) {
        embedded_sim_switch_mode = val;
    }

    public synchronized static byte getEmbSimManualSwitchMode() {
        return embedded_sim_manual_switch_mode;
    }

    public static synchronized void setEmbSimManualSwitchMode(byte val) {
        embedded_sim_manual_switch_mode = val;
    }

    public synchronized static byte getEmbSimAutoCommandStart() {
        return embedded_sim_command_start;
    }

    public static synchronized void setEmbSimAutoCommandStart(byte val) {
        embedded_sim_command_start = val;
    }

    public synchronized static boolean get_gps_enabled() {
        return gps_enabled;
    }

    public synchronized static void set_gps_enabled(boolean status) {
        gps_enabled = status;
    }

    public synchronized static boolean get_irnss_enabled() {
        return irnss_enabled;
    }

    public synchronized static void set_irnss_enabled(boolean status) {
        irnss_enabled = status;
    }

    public synchronized static boolean get_glonass_enabled() {
        return glonass_enabled;
    }

    public synchronized static void set_glonass_enabled(boolean status) {
        glonass_enabled = status;
    }

    public synchronized static byte getPrevIntBatState() {
        return prevIntBatState;
    }

    public static synchronized void setPrevIntBatState(byte val) {
        prevIntBatState = val;
    }

    /*  public synchronized static byte getPrevIntBatState1() {
        return prevIntBatState1;
    }

    public static synchronized void setPrevIntBatState1(byte val) {
        prevIntBatState1 = val;
    }*/
    public synchronized static boolean getPedAudio1Enable() {
        return PEDESTRIAN1_AUDIO;
    }

    public static synchronized void setPedAudio1Enable(boolean val) {
        PEDESTRIAN1_AUDIO = val;
    }

    public synchronized static boolean getPedAudio2Enable() {
        return PEDESTRIAN2_AUDIO;
    }

    public static synchronized void setPedAudio2Enable(boolean val) {
        PEDESTRIAN2_AUDIO = val;
    }

    public synchronized static boolean getPedAudio3Enable() {
        return PEDESTRIAN3_AUDIO;
    }

    public static synchronized void setPedAudio3Enable(boolean val) {
        PEDESTRIAN3_AUDIO = val;
    }

    public synchronized static boolean getPedAudio4Enable() {
        return PEDESTRIAN4_AUDIO;
    }

    public static synchronized void setPedAudio4Enable(boolean val) {
        PEDESTRIAN4_AUDIO = val;
    }

    public synchronized static boolean getPedDialog3Enable() {
        return PEDESTRIANDIALOG_AUDIO;
    }

    public static synchronized void setPedDialog3Enable(boolean val) {
        PEDESTRIANDIALOG_AUDIO = val;
    }

    public synchronized static int getApcSeqNo() {
        return apc_seq_no;
    }

    public static synchronized void setApcSeqNo(int val) {
        apc_seq_no = val;
    }

    public synchronized static byte getApcStatus() {
        return apc_status;
    }

    public static synchronized void setApcStatus(byte val) {
        apc_status = val;
    }

    /*
     private static String apc_start_time = "";
    private static String apc_stop_time = "";
    private static String apc_start_routeno = "";
    private static String apc_stop_routeno = "";
     */
    public synchronized static String getApcStartTime() {
        return apc_start_time;
    }

    public static synchronized void setApcStartTime(String val) {
        apc_start_time = val;
    }

    public synchronized static String getApcStopTime() {
        return apc_stop_time;
    }

    public static synchronized void setApcStopTime(String val) {
        apc_stop_time = val;
    }

    public synchronized static String getApcRouteNo() {
        return apc_routeno;
    }

    public static synchronized void setApcRouteNo(String val) {
        apc_routeno = val;
    }

    public synchronized static String getApcStopName() {
        return apc_stopname;
    }

    public static synchronized void setApcStopName(String val) {
        apc_stopname = val;
    }

    public synchronized static boolean getApcEnabled() {
        return apc_enabled;
    }

    public static synchronized void setApcEnabled(boolean val) {
        apc_enabled = val;
    }

    public synchronized static boolean getApcLinkCheckStatus() {
        return apc_link_status;
    }

    public static synchronized void setApcLinkCheckStatus(boolean val) {
        apc_link_status = val;
    }

    public synchronized static boolean getLiveStreamEnabled() {
        return live_stream_enabled;
    }

    public static synchronized void setLiveStreamEnabled(boolean val) {
        live_stream_enabled = val;
    }

    public synchronized static void setApcPeopleIn(int id) {
        apc_people_in = id;
    }

    public synchronized static int getApcPeopleIn() {
        return apc_people_in;
    }

    public synchronized static void setApcPeopleOut(int id) {
        apc_people_out = id;
    }

    public synchronized static int getApcPeopleOut() {
        return apc_people_out;
    }

    public synchronized static void setApcPeopleCntStatusChange(boolean stat) {
        apc_people_status_change = stat;
    }

    public synchronized static boolean getApcPeopleCntStatusChange() {
        return apc_people_status_change;
    }

    public synchronized static void setIpAdd(String val) {
        ip_add = val;
    }

    public synchronized static String getIpAdd() {
        return ip_add;
    }

    public synchronized static void setCanBusType(byte val) {
        can_bustype = val;
    }

    public synchronized static byte getCanBusType() {
        return can_bustype;
    }

    //live server parameters enable/disable
    public synchronized static void setCam1ServerLiveSelected(boolean state) {
        server_live_cam1 = state;
    }

    public synchronized static boolean getCam1ServerLiveSelected() {
        return server_live_cam1;
    }

    public synchronized static void setCam2ServerLiveSelected(boolean state) {
        server_live_cam2 = state;
    }

    public synchronized static boolean getCam2ServerLiveSelected() {
        return server_live_cam2;
    }

    public synchronized static void setCam3ServerLiveSelected(boolean state) {
        server_live_cam3 = state;
    }

    public synchronized static boolean getCam3ServerLiveSelected() {
        return server_live_cam3;
    }

    public synchronized static void setCam4ServerLiveSelected(boolean state) {
        server_live_cam4 = state;
    }

    public synchronized static boolean getCam4ServerLiveSelected() {
        return server_live_cam4;
    }

    public synchronized static void setCam5ServerLiveSelected(boolean state) {
        server_live_cam5 = state;
    }

    public synchronized static boolean getCam5ServerLiveSelected() {
        return server_live_cam5;
    }

    public synchronized static void setCam6ServerLiveSelected(boolean state) {
        server_live_cam6 = state;
    }

    public synchronized static boolean getCam6ServerLiveSelected() {
        return server_live_cam6;
    }

    public synchronized static void setCam7ServerLiveSelected(boolean state) {
        server_live_cam7 = state;
    }

    public synchronized static boolean getCam7ServerLiveSelected() {
        return server_live_cam7;
    }

    public synchronized static void setCam8ServerLiveSelected(boolean state) {
        server_live_cam8 = state;
    }

    public synchronized static boolean getCam8ServerLiveSelected() {
        return server_live_cam8;
    }

    public synchronized static void setApcHourPeopleIn(int id) {
        apc_hour_people_in = id;
    }

    public synchronized static int getApcHourPeopleIn() {
        return apc_hour_people_in;
    }

    public synchronized static void setApcHourPeopleOut(int id) {
        apc_hour_people_out = id;
    }

    public synchronized static int getApcHourPeopleOut() {
        return apc_hour_people_out;
    }

    public synchronized static void setApcDayPeopleIn(int id) {
        apc_day_people_in = id;
    }

    public synchronized static byte getApcHourHour() {
        return apc_hour_hour;
    }

    public synchronized static void setApcHourHour(byte id) {
        apc_hour_hour = id;
    }

    public synchronized static int getApcDayPeopleIn() {
        return apc_day_people_in;
    }

    public synchronized static void setApcDayPeopleOut(int id) {
        apc_day_people_out = id;
    }

    public synchronized static int getApcDayPeopleOut() {
        return apc_day_people_out;
    }

    public synchronized static void setApcHourDateTime(long id) {
        apc_hour_date_time = id;
    }

    public synchronized static byte getApcDayDay() {
        return apc_day_day;
    }

    public synchronized static void setApcDayDay(byte id) {
        apc_day_day = id;
    }

    public synchronized static long getApcHourDateTime() {
        return apc_hour_date_time;
    }

    public synchronized static void setApcDayDateTime(long id) {
        apc_day_date_time = id;
    }

    public synchronized static long getApcDayDateTime() {
        return apc_day_date_time;
    }

    public synchronized static void setEmbeddedSimName(byte id) {
        embedded_sim_name = id;
    }

    public synchronized static byte getEmbeddedSimName() {
        return embedded_sim_name;
    }

    //apc second camera
    public synchronized static void setApcPeopleIn1(int id) {
        apc_people_in1 = id;
    }

    public synchronized static int getApcPeopleIn1() {
        return apc_people_in1;
    }

    public synchronized static void setApcPeopleOut1(int id) {
        apc_people_out1 = id;
    }

    public synchronized static int getApcPeopleOut1() {
        return apc_people_out1;
    }

    public synchronized static void setApcHourPeopleIn1(int id) {
        apc_hour_people_in1 = id;
    }

    public synchronized static int getApcHourPeopleIn1() {
        return apc_hour_people_in1;
    }

    public synchronized static void setApcHourPeopleOut1(int id) {
        apc_hour_people_out1 = id;
    }

    public synchronized static int getApcHourPeopleOut1() {
        return apc_hour_people_out1;
    }

    public synchronized static void setApcDayPeopleIn1(int id) {
        apc_day_people_in1 = id;
    }

    public synchronized static byte getApcHourHour1() {
        return apc_hour_hour1;
    }

    public synchronized static void setApcHourHour1(byte id) {
        apc_hour_hour1 = id;
    }

    public synchronized static int getApcDayPeopleIn1() {
        return apc_day_people_in1;
    }

    public synchronized static void setApcDayPeopleOut1(int id) {
        apc_day_people_out1 = id;
    }

    public synchronized static int getApcDayPeopleOut1() {
        return apc_day_people_out1;
    }

    public synchronized static void setApcHourDateTime1(long id) {
        apc_hour_date_time1 = id;
    }

    public synchronized static byte getApcDayDay1() {
        return apc_day_day1;
    }

    public synchronized static void setApcDayDay1(byte id) {
        apc_day_day1 = id;
    }

    public synchronized static long getApcHourDateTime1() {
        return apc_hour_date_time1;
    }

    public synchronized static void setApcDayDateTime1(long id) {
        apc_day_date_time1 = id;
    }

    public synchronized static long getApcDayDateTime1() {
        return apc_day_date_time1;
    }

    public synchronized static boolean getApcEnabled1() {
        return apc_enabled2;
    }

    public static synchronized void setApcEnabled1(boolean val) {
        apc_enabled2 = val;
    }

    public synchronized static void setApcPeopleCountIn(int id) {
        apc_people_count_in = id;
    }

    public synchronized static int getApcPeopleCountIn() {
        return apc_people_count_in;
    }

    public synchronized static void setApcPeopleCountOut(int id) {
        apc_people_count_out = id;
    }

    public synchronized static int getApcPeopleCountOut() {
        return apc_people_count_out;
    }

    public synchronized static void setEthernetip(String id) {
        ethernet_ip = id;

    }

    public synchronized static String getEthernetip() {
        return ethernet_ip;
    }

    public synchronized static void setPmiEnable(boolean state) {
        PMI_COMPANY = state;
    }

    public synchronized static boolean getPmiEnable() {
        return PMI_COMPANY;
    }

    public synchronized static void setSharedNetwork(boolean state) {
        // System.out.print("set shared variabble"+ shared_network);
        shared_network = state;
    }

    public synchronized static boolean getSharedNetwork() {
        return shared_network;
    }

    public synchronized static void setSuratProtocol(boolean state) {
        surat_protocol = state;
    }

    public synchronized static boolean getSuratProtocol() {
        return surat_protocol;
    }

    public synchronized static void setIp5Protocol(byte state) {
        ip5_protocol = state;
    }

    public synchronized static byte getIp5Protocol() {
        return ip5_protocol;
    }

    public synchronized static void setDateHour(String date) {
        hour_date = date;

    }

    public synchronized static String getDateHour() {
        return hour_date;
    }

    public synchronized static void setDateDay(String date) {
        day_date = date;

    }

    public synchronized static String getDateDay() {
        return day_date;
    }

    public synchronized static void setDateHour1(String date) {
        hour_date1 = date;

    }

    public synchronized static String getDateHour1() {
        return hour_date1;
    }

    public synchronized static void setDateDay1(String date) {
        day_date1 = date;

    }

    public synchronized static String getDateDay1() {
        return day_date1;
    }

    public static synchronized void setbatVolValue(double data) {
        batery_voltage_value = data;
    }

    public static synchronized double getbatVolValue() {
        return batery_voltage_value;
    }

    /*  public synchronized static boolean getFoorirApcEnable() {
        return start_people_enable;
    }

    public static synchronized void setFoorirApcEnable(boolean val) {
        start_people_enable = val;
    }

    public synchronized static boolean getFoorirApcEnable1() {
        return start_people_enable1;
    }

    public static synchronized void setFoorirApcEnable1(boolean val) {
        start_people_enable1 = val;
    }*/
    public synchronized static void setSplAudioInterval(int val) {
        spl_Audio_Interval = val;
    }

    public synchronized static int getSplAudioInterval() {
        return spl_Audio_Interval;
    }

    public synchronized static void setSplAudioEnabled(boolean state) {
        spl_audio_enabled = state;
    }

    public synchronized static boolean getSplAudioEnabled() {
        return spl_audio_enabled;
    }

    public synchronized static void setSplAudio1(boolean state) {
        audio_enabled1 = state;
    }

    public synchronized static boolean getSplAudio1() {
        return audio_enabled1;
    }

    public synchronized static void setSplAudio2(boolean state) {
        audio_enabled2 = state;
    }

    public synchronized static boolean getSplAudio2() {
        return audio_enabled2;
    }

    public synchronized static void setSplAudio3(boolean state) {
        audio_enabled3 = state;
    }

    public synchronized static boolean getSplAudio3() {
        return audio_enabled3;
    }

    public synchronized static void setSplAudio4(boolean state) {
        audio_enabled4 = state;
    }

    public synchronized static boolean getSplAudio4() {
        return audio_enabled4;
    }

    public synchronized static void setSplAudio5(boolean state) {
        audio_enabled5 = state;
    }

    public synchronized static boolean getSplAudio5() {
        return audio_enabled5;
    }

    public synchronized static void setSplAudioAnnouncement(boolean state) {
        spl_audio_anounc_enable = state;
    }

    public synchronized static boolean getSplAudioAnnouncement() {
        return spl_audio_anounc_enable;
    }
}
