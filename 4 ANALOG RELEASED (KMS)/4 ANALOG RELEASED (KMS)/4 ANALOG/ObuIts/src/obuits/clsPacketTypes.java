package obuits;

/*
 * This class defines constants for various packet types used in communication
 * protocols. Packet types are categorized by their source and purpose,
 * facilitating structured communication.
 */
public class clsPacketTypes {

    // received PACKET TYPES
    // CS to OBU
    public static final byte CS_GPRS_PKT_ADHOC = 52; // Ad-hoc packet from CS to OBU
    public static final byte CS_GPRS_PKT_PANIC = 3; // Panic alert packet from CS to OBU
    public static final byte CS_GPRS_PKT_FILE_DOWNLOAD = 60; // File download request packet from CS to OBU
    public static final byte CS_GPRS_PKT_PERMSCH = 61; // Permanent schedule packet from CS to OBU
    public static final byte CS_GPRS_PKT_TEMPSCH = 62; // Temporary schedule packet from CS to OBU
    public static final byte CS_GPRS_PKT_SYSTEM_RESET = 63; // System reset packet from CS to OBU
    public static final byte CS_GPRS_PKT_SCHEDULE_REQUEST_RESPONSE = 64; // Schedule request/response packet from CS to OBU
    public static final short CS_GPRS_PKT_ACK = 254; // Acknowledgment packet from CS to OBU
    public static final byte CS_GPRS_PKT_ROUTE_END = 50; // Route end packet from CS to OBU
    public static final byte CS_GPRS_PKT_ROUTE = 51; // Route packet from CS to OBU
    public static final byte CS_GPRS_HEALTH_PKT = 65; // Health packet from CS to OBU
    public static final byte CS_GPRS_EMERGENCY_MAP_ROUTING = 66; // Emergency map routing packet from CS to OBU
    public static final byte CS_GPRS_FILE_UPLOAD = 70; // File upload packet from CS to OBU

    //CS to OBU camera  releated protocols
    public static final byte CS_OBU_PKT_CAM_MS_RESOLUTION = 101;
    public static final byte CS_OBU_PKT_CAM_MS_IMG_QUALITY = 102;
    public static final byte CS_OBU_PKT_CAM_MS_BITRATE = 103;
    public static final byte CS_OBU_PKT_CAM_MS_BITRATE_TYPE = 104;
    public static final byte CS_OBU_PKT_CAM_MS_MAX_BITRATE = 105;
    public static final byte CS_OBU_PKT_CAM_MS_FRAMERATE = 106;
    public static final byte CS_OBU_PKT_CAM_MS_FRAME_INTERVAL = 107;
    public static final byte CS_OBU_PKT_CAM_SS_RESOLUTION = 108;
    public static final byte CS_OBU_PKT_CAM_SS_IMG_QUALITY = 109;
    public static final byte CS_OBU_PKT_CAM_SS_BITRATE = 110;
    public static final byte CS_OBU_PKT_CAM_SS_BITRATE_TYPE = 111;
    public static final byte CS_OBU_PKT_CAM_SS_MAX_BITRATE = 112;
    public static final byte CS_OBU_PKT_CAM_SS_FRAMERATE = 113;
    public static final byte CS_OBU_PKT_CAM_SS_FRAME_INTERVAL = 114;
    public static final byte CS_OBU_PKT_CAM_VID_REC_ENA_DIS = 115;
    public static final byte CS_OBU_PKT_CAM_AUD_REC_ENA_DIS = 116;
    public static final byte CS_OBU_PKT_CAM_NAME_DISPLAY = 117;
    public static final byte CS_OBU_PKT_CAM_VID_REC_CONT_SCH = 118;
    public static final byte CS_OBU_PKT_CAM_VID_REC_SCH_CONFIG = 119;
    public static final byte CS_OBU_PKT_CAM_VID_REC_EXPIRED_TIME = 120;
    public static final byte CS_OBU_PKT_CAM_VID_REC_MOTION_DET = 121;
    public static final byte CS_OBU_PKT_CAM_VID_REC_MOT_DET_SENS = 122;
    public static final byte CS_OBU_PKT_CAM_VID_REC_MOT_DET_AREA = 123;
    public static final byte CS_OBU_PKT_CAM_VID_REC_PRE_EVENT = 124;
    public static final byte CS_OBU_PKT_CAM_VID_REC_POST_EVENT = 125;
    public static final byte CS_OBU_PKT_CAM_RES_SNAP_BACKEND = 126;
    public static final byte CS_OBU_PKT_CAM_SNAP_CONT_FREQ_INT = 127;
    public static final short CS_OBU_PKT_IGN_ON_OFF_DURATION = 128;
    public static final short CS_OBU_PKT_GET_FIRMWARE_VERSION = 129;
    public static final short CS_OBU_PKT_GET_PROTOCOL_VERSION = 130;
    public static final short CS_OBU_PKT_GET_MAC_ADDRESS = 131;
    public static final short CS_OBU_PKT_GET_PRIMARY_IPADDRESS = 132;
    public static final short CS_OBU_PKT_SET_PRIMARY_IPADDRESS = 133;
    public static final short CS_OBU_PKT_GET_SECONDARY_IPADDRESS = 134;
    public static final short CS_OBU_PKT_SET_SECONDARY_IPADDRESS = 135;
    public static final short CS_OBU_PKT_SET_PORT_NO_PRIMARY_IP = 136;
    public static final short CS_OBU_PKT_SET_PORT_NO_SECONDARY_IP = 137;
    public static final short CS_OBU_PKT_SET_VEHICLE_REGNO = 138;
    public static final short CS_OBU_PKT_SET_APN = 139;
    public static final short CS_OBU_PKT_SET_EMERGENCY_INTERVAL = 140;
    public static final short CS_OBU_PKT_GET_IMEINO = 141;
    public static final short CS_OBU_PKT_TRANS_MODE_ALWAYS_ON = 142;
    public static final short CS_OBU_PKT_GET_VEHICLE_REGNO = 143;
    public static final short CS_OBU_PKT_GET_IGNITIONONOFF_MODE = 144;
    public static final byte CS_OBU_PKT_SYSTEM_RESET = 063;
    public static final byte CS_OBU_PKT_PERMSCH = 061;
    public static final byte CS_OBU_PKT_TEMPSCH = 062;
    public static final byte CS_OBU_PKT_ADHOC = 052;
    public static final byte CS_OBU_PKT_FILE_DOWNLOAD = 060;
    public static final byte CS_OBU_PKT_SCH_REQUEST_RESPONSE = 064;
    public static final byte CS_OBU_PKT_ROUTE_START = 051;
    public static final byte CS_OBU_PKT_ROUTE_END = 050;
    public static final byte CS_OBU_PKT_FILE_UPLOAD = 070;

    public static final short CS_OBU_PKT_EMERGENCY_MODE_RES = 240;
    public static final byte CS_OBU_PKT_PANIC = 003;
    public static final byte CS_OBU_PKT_HEALTH = 065;
    //END of cs to OBU
    // OBU to CS Send packets
    public static final byte GPRS_PKT_PANIC = 2; // Panic alert packet from OBU to CS
    public static final byte GPRS_PKT_POSITION = 4; // Position update packet from OBU to CS
    public static final byte GPRS_PKT_CAN_PRESELECTED = 10; // CAN preselected packet from OBU to CS
    public static final byte GPRS_PKT_CAN_ALARMS = 11; // CAN alarms packet from OBU to CS
    public static final byte GPRS_PKT_CAN_DTC = 13; // CAN DTC packet from OBU to CS

    public static final byte GPRS_PKT_DISBRD_PIDS = 21;
    public static final byte GPRS_PKT_DISBRD_DTC = 22;
    public static final byte GPRS_PKT_OBU_PIDS = 23;

    public static final byte GPRS_PKT_ROUTE_START = 50; //TRIP START
    public static final byte GPRS_PKT_ROUTE_SKIP = 54; //TRIP SKIP
    public static final byte GPRS_PKT_IGNITION_ON = 60;
    public static final byte GPRS_PKT_IGNITION_OFF = 61;
    public static final byte GPRS_PKT_APP_STOP = 62;

    public static final byte GPRS_PKT_REACHED_STOP = 63;
    public static final byte GPRS_PKT_BUS_STOP_CROSSED = 64; //DEPART STOP\
    public static final byte GPRS_PKT_SKIP_STOP = 65;
    public static final byte GPRS_PKT_ROUTE_END = 66; //TRIP END
    public static final byte GPRS_PKT_DUTY_START = 67;
    public static final byte GPRS_PKT_DUTY_END = 68;
    public static final byte GPRS_PKT_HARSH_ACC = 69;
    public static final byte GPRS_PKT_HARSH_BRK = 70;
    public static final byte GPRS_PKT_OVER_SPEED_START = 71;
    public static final byte GPRS_PKT_OVER_SPEED_END = 72;
    public static final byte GPRS_PKT_NON_STOPPAGE = 73; //sumitha ADDED PKT
    public static final byte GPRS_PKT_ROUTE_DEVIATION = 74; //sumitha ADDED PKT
    public static final byte GPRS_PKT_RESET_ON = 81;//sumitha added packet
    public static final byte GPRS_PKT_MAINS_ON = 82;//sumitha added packet
    public static final byte GPRS_PKT_MAINS_OFF = 83;//sumitha added packet
    public static final byte GPRS_PKT_DRIVER_STOPPED_REASON = 84;
    public static final byte GPRS_PKT_SCHEDULE_REQUEST = 85;
    public static final byte GPRS_PKT_SPECIAL_MSG = 86;
    public static final byte GPRS_PKT_FILEDOWNLOAD_ACK = 87;
    public static final byte GPRS_PKT_TAMPER_ON = 88;
    public static final byte GPRS_PKT_TAMPER_OFF = 89;
    public static final byte GPRS_PKT_PING = 90;
    public static final byte GPRS_PKT_MIC_ON = 91;
    public static final byte GPRS_PKT_MIC_OFF = 92;
    public static final byte GPRS_PKT_VIDEO_STATUS = 93;
    public static final byte GPRS_PKT_OBU_ON = 94;
    public static final byte GPRS_PKT_MISS_STOP = 95;
    public static final byte GPRS_PKT_DISPLAYBOARD_DISCONNECT = 96;
    public static final byte GPRS_PKT_CAN_DOOR_OPEN = 97;
    public static final byte GPRS_PKT_CAN_DOOR_CLOSE = 98;
    public static final byte OBU_CS_PKT_PEOPLE_COUNT = 99;
    public static final byte CS_GPRS_HEALTH_RES_PKT = 100;
    public static final byte OBU_CS_GPRS_FILEUPLOAD_RES_PKT = 101; // File upload response packet from OBU to CS
    public static final short OBU_CS_GPRS_PKT_COMMON_ACK_RES_PKT = 254; // Common acknowledgment packet from OBU to CS

    // OBU to CS protocols
    public static final byte OBU_CS_PKT_SEND_FIRMWARE_VERSION = 101;
    public static final byte OBU_CS_PKT_SEND_PROTOCOL_VERSION = 102;
    public static final byte OBU_CS_PKT_SEND_MAC_ADDRESS = 103;
    public static final byte OBU_CS_PKT_SEND_PRIMARY_IPADDRESS = 104;
    public static final byte OBU_CS_PKT_SEND_SECONDARY_IPADDRESS = 105;
    public static final byte OBU_CS_PKT_SEND_IMEI_NUMBER = 106;
    public static final byte OBU_CS_PKT_SEND_VEHCLE_REG_NUMBER = 107;
    public static final byte OBU_CS_PKT_SEND_IGNITION_MOCE = 108;
    //CAM PKT
    //ENd of OBU to Cs Packets
    public static final byte CAN_OBU_CAN_PKT = 10; // CAN protocol packet
    public static final byte CAN_OBU_ANALOG_PKT = 11; // Analog data packet
    public static final byte CAN_OBU_DIGITAL_PKT = 12; // Digital data packet
    public static final byte CAN_OBU_DIGITAL_RES_PKT = 72; // Digital data response packet
    public static final byte CAN_LED_PKT = 13; // LED control packet
    public static final byte CAN_LED_RES_PKT = 73; // LED control response packet
    public static final byte CAN_TAMPER_PKT = 14; // Tamper alert packet
    public static final byte CAN_OBU_USB_HD_PKT = 15; // USB hard drive packet
    public static final byte CAN_OBU_USB_HD_RES_PKT = 75; // USB hard drive response packet

    // OBU CAN connection check and reset packets
    public static final byte OBU_CAN_CONN_CHK_PKT = 22; // Connection check packet
    public static final byte OBU_CAN_CONN_CHK_RES_PKT = 82; // Connection check response packet
    public static final byte OBU_CAN_CONN_RESET_PKT = 23; // Connection reset packet
    public static final byte OBU_CAN_CONN_RESET_RES_PKT = 83; // Connection reset response packet

    // OBU CAN configuration bus type response packet
    public static final byte OBU_CAN_CONFIGURATION_BUSTYPE_RES_PKT = 81; // Configuration bus type response packet

    // Constants for special packet data and events
    public static final byte OBU_CAN_STOP_DATA = 0; // Stop data constant
    public static final byte OBU_CAN_START_DATA = 1; // Start data constant
    public static final byte OBU_CAN_CHECK_DATA = 2; // Check data constant
    public static final byte OBU_APC_STOP_COUNTING = 1; // Stop counting constant
    /*
    Specify the packet type 
     NR = Normal
     EA = Emergency alert
     TA = Tamper alert
     HP = Health packet
     IN = Ignition On
     IF = Ignition Off
     BPD = Battery power disconnect
     BPR = Battery power reconnect
     */

    // Packet type identifiers for various alerts and events
    public static final String NORMAL_PKT = "NR"; // Normal packet identifier
    public static final String EMERGENCY_ALERT_PKT = "EA"; // Emergency alert packet identifier
    public static final String TAMPER_ALERT_PKT = "TA"; // Tamper alert packet identifier
    public static final String HEALTH_PKT = "HP"; // Health packet identifier
    public static final String IGNITION_ON_PKT = "IN"; // Ignition on packet identifier
    public static final String IGNITION_OFF_PKT = "IF"; // Ignition off packet identifier
    public static final String BATTERY_PWR_DISCONNECT_PKT = "BD"; // Battery power disconnect packet identifier
    //public static final String BATTERY_PWR_RECONNECT_PKT = "BR"; // Battery power reconnect packet identifier
    public static final String BATTERY_PWR_RECONNECT_PKT = "BR";
    public static final String BATTERY_PWR_REMOVED_PKT = "BN"; // Battery power removed packet identifier
    public static final String ALERT_OVER_AIR_PKT = "PC"; // Over-the-air alert packet identifier
    public static final String EMERGENCY_ALERT_ON_PKT = "EO"; // Emergency alert on packet identifier
    public static final String EMERGENCY_ALERT_OFF_PKT = "EF"; // Emergency alert off packet identifier
    //EXTRA
    public static final String INT_BATTERY_PWR_LOW_PKT = "BL"; // Internal battery power low packet identifier
    public static final String HARSHBRK_PKT = "HB"; // Harsh braking packet identifier
    public static final String HARSHACC_PKT = "HA"; // Harsh acceleration packet identifier
    public static final String RASH_TURNING_PKT = "RT"; // Rash turning packet identifier

    // APC command and response constants
    public static final byte OBU_APC_START_COUNTING = 2; // Start counting constant for APC
    public static final byte OBU_APC_LINK_CHECK = 3; // Link check constant for APC
    public static final byte OBU_APC_EVENT_BASED = 44; // Event-based constant for APC
    public static final byte OBU_APC_UPDATE_DATETIME = 5; // Update date/time constant for APC
    public static final byte OBU_APC_GET_VERSION = 6; // Get version constant for APC
    public static final byte OBU_APC_APPLICATION_LOADING = 7; // Application loading constant for APC
    public static final byte OBU_APC_REBOOT = 8; // Reboot constant for APC
    public static final byte OBU_APC_INOUT_PARAMS = 9; // Input/output parameters constant for APC
    public static final byte OBU_APC_PEDESTRIAN_START = 10; // Pedestrian start constant for APC
    public static final byte OBU_APC_PEDESTRIAN_STOP = 11; // Pedestrian stop constant for APC
    public static final byte OBU_APC_PEDESTRIAN_DETECT_SRES = 12; // Pedestrian detection response constant for APC

    // APC response packets
    public static final byte APC_OBU_STOP_COUNTING_RES = 41; // Stop counting response packet for APC
    public static final byte APC_OBU_START_COUNTING_RES = 42; // Start counting response packet for APC
    public static final byte APC_OBU_LINK_CHECK_RES = 43; // Link check response packet for APC
    public static final byte APC_OBU_EVENT_BASED = 4; // Event-based response packet for APC
    public static final byte APC_OBU_UPDATE_DATETIME_RES = 45; // Update date/time response packet for APC
    public static final byte APC_OBU_GET_VERSION_RES = 46; // Get version response packet for APC
    public static final byte APC_OBU_APPLICATION_LOADING_RES = 47; // Application loading response packet for APC
    public static final byte APC_OBU_REBOOT_RES = 48; // Reboot response packet for APC
    public static final byte APC_OBU_PEOPLE_INOUT = 49; // People in/out response packet for APC
    public static final byte APC_OBU_PEDESTRIAN_START_RES = 50; // Pedestrian start response packet for APC
    public static final byte APC_OBU_PEDESTRIAN_STOP_RES = 51; // Pedestrian stop response packet for APC
    public static final byte APC_OBU_PEDESTRIAN_DETECT = 52; // Pedestrian detection packet for APC

}
