/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

/**
 * This class defines constants for various packet types used in communication
 * protocols. These constants help in identifying and categorizing different
 * types of packets sent between On-Board Units (OBU) and Control Stations (CS).
 *
 * @author Sumitha
 */
public class clsPacketTypes16833 {

    // SMS Location Packet constants
    public static final String OBU_CS_SMS_LOCATION_PKT = "SMS"; // SMS location packet
    public static final String OBU_CS_PEOPLE_COUNT_ON_EACH_STOP_PKT = "009"; // People count on each stop
    public static final String OBU_CS_PEOPLE_COUNT_ON_ROUTE_END = "012"; // People count on route end
    public static final String OBU_CS_CAN_CESL_PKT = "014"; // CAN CESL packet
    public static final String OBU_CS_SMS_PKT = "ACTVR"; // SMS packet
    public static final String OBU_CS_HLTSMS_PKT = "HCHKR"; // Health check SMS packet
    public static final String ALTER_ID = "11"; // Alternate ID
    public static final String VEH_MODE = "ID"; // Vehicle mode
    public static final String ALCO_PKT = "15";

    // OBU to CS packet constants
    public static final String OBU_CS_LOGIN_PKT = "LGN"; // Login packet
    public static final String OBU_CS_LOCATION_PKT = "LOC"; // Location packet
    public static final String OBU_CS_HEALTH_PKT = "HLT"; // Health packet
    public static final String OBU_CS_EMERGENCY_PKT = "EMR"; // Emergency packet
    public static final String OBU_CS_EMERGENCY_ALERT_PKT = "EPB"; // Emergency alert packet
    public static final String OBU_CS_COMMON_ACK_PKT = "ACK"; // Common acknowledgment packet

    public static final String OBU_CS_FIRMWARE_VERSION_PKT = "101"; // Firmware version packet
    public static final String OBU_CS_PROTOCOL_VERSION_PKT = "102"; // Protocol version packet
    public static final String OBU_CS_MAC_ADDR_PKT = "103"; // MAC address packet
    public static final String OBU_CS_APN_ADDR_PKT = "146"; // APN address packet

    public static final String OBU_CS_IPADDRESS1_PKT = "104"; // IP address 1 packet
    public static final String OBU_CS_IPADDRESS2_PKT = "105"; // IP address 2 packet
    public static final String OBU_CS_OVERSPEED_PKT = "075"; // Overspeed packet
    public static final String OBU_CS_HA_PKT = "076"; // Harsh acceleration packet
    public static final String OBU_CS_HB_PKT = "077"; // Harsh braking packet
    public static final String OBU_CS_RT_PKT = "078"; // Rash turning packet

    public static final String OBU_CS_IMEINUMBER_PKT = "106"; // IMEI number packet
    public static final String OBU_CS_VEH_REG_PKT = "107"; // Vehicle registration packet
    public static final String OBU_CS_IGNITION_MODE_PKT = "108"; // Ignition mode packet
    public static final String OBU_CS_PANIC_MESSAGE_PKT = "002"; // Panic message packet
    public static final String OBU_CS_START_ROUTE_PKT = "050"; // Start route packet
    public static final String OBU_CS_CANCEL_ROUTE_PKT = "054"; // Cancel route packet
    public static final String OBU_CS_STOP_APPROACH_PKT = "062"; // Stop approach packet
    public static final String OBU_CS_STOP_REACHED_PKT = "063"; // Stop reached packet
    public static final String OBU_CS_STOP_DEPART_PKT = "064"; // Stop depart packet
    public static final String OBU_CS_STOP_SKIP_PKT = "065"; // Stop skip packet
    public static final String OBU_CS_END_ROUTE_PKT = "066"; // End route packet
    public static final String OBU_CS_DUTY_START_PKT = "067"; // Duty start packet
    public static final String OBU_CS_DUTY_END_PKT = "068"; // Duty end packet
    public static final String OBU_CS_CONDUCTOR_DUTY_START_PKT = "147"; // Conductor duty start packet
    public static final String OBU_CS_CONDUCTOR_DUTY_END_PKT = "148"; // Conductor duty end packet
//checked
    // Overspeed packets
    public static final String OBU_CS_OVERSPEED_START_PKT = "071"; // Overspeed start packet
    public static final String OBU_CS_OVERSPEED_END_PKT = "072"; // Overspeed end packet

    // Non-stoppage and reset packets
    public static final String OBU_CS_VEH_NON_STOPPAGE_PKT = "073"; // Non-stoppage packet
    public static final String OBU_CS_RESET_RES_PKT = "081"; // Reset response packet

    // Driver halt and schedule request packets
    public static final String OBU_CS_DRIVER_HALT_JUST_PKT = "084"; // Driver halt justification packet
    public static final String OBU_CS_SCH_REQUEST_PKT = "085"; // Schedule request packet

    // Special route and ping packets
    public static final String OBU_CS_SPECIAL_ROUTE_MSG_PKT = "086"; // Special route message packet
    public static final String OBU_CS_PING_CONN_CHK_PKT = "090"; // Ping connection check packet
    public static final String OBU_CS_OBU_STARTED_PKT = "094"; // OBU started packet

    // Disboard and CAN packets
    public static final String OBU_CS_DISBRD_PIDS_PKT = "021"; // Disboard PIDS packet
    public static final String OBU_CS_OBU_DISBRD_USB_GPS_DTC_PKT = "022"; // Disboard USB GPS DTC packet
    public static final String OBU_CS_OBU_PIDS_PKT = "023"; // OBU PIDS packet
    public static final String OBU_CS_CAN_PRESELECTED_PKT = "010"; // CAN preselected packet
    public static final String OBU_CS_CAN_ALARMS_PKT = "011"; // CAN alarms packet
    public static final String OBU_CS_DISPLAYBOARD_DISCONNECT_PKT = "096"; // Displayboard disconnect packet

    // Video packets
    public static final String OBU_CS_VIDEOLOSS_PKT = "091"; // Video loss packet
    public static final String OBU_CS_VIDEOTAMPER_PKT = "092"; // Video tamper packet
    public static final String OBU_CS_VIDEO_STATUS_PKT = "093"; // Video status packet

    // CAN door packets
    public static final String OBU_CS_CAN_DOOR_OPEN_PKT = "097"; // CAN door open packet
    public static final String OBU_CS_CAN_DOOR_CLOSE_PKT = "098"; // CAN door close packet

    // CS to OBU packet constants
    public static final String CS_OBU_PKT_EMERGENCY_RES = "EMR"; // Emergency response packet
    public static final String CS_OBU_PKT_CAM_MS_RESOLUTION = "101"; // Camera main stream resolution packet
    public static final String CS_OBU_PKT_CAM_MS_IMG_QUALITY = "102"; // Camera main stream image quality packet
    public static final String CS_OBU_PKT_CAM_MS_BITRATE = "103"; // Camera main stream bitrate packet
    public static final String CS_OBU_PKT_CAM_MS_BITRATE_TYPE = "104"; // Camera main stream bitrate type packet
    public static final String CS_OBU_PKT_CAM_MS_MAX_BITRATE = "105"; // Camera main stream max bitrate packet
    public static final String CS_OBU_PKT_CAM_MS_FRAMERATE = "106"; // Camera main stream framerate packet
    public static final String CS_OBU_PKT_CAM_MS_FRAME_INTERVAL = "107"; // Camera main stream frame interval packet
    public static final String CS_OBU_PKT_CAM_SS_RESOLUTION = "108"; // Camera sub stream resolution packet
    public static final String CS_OBU_PKT_CAM_SS_IMG_QUALITY = "109"; // Camera sub stream image quality packet
    public static final String CS_OBU_PKT_CAM_SS_BITRATE = "110"; // Camera sub stream bitrate packet
    public static final String CS_OBU_PKT_CAM_SS_BITRATE_TYPE = "111"; // Camera sub stream bitrate type packet
    public static final String CS_OBU_PKT_CAM_SS_MAX_BITRATE = "112"; // Camera sub stream max bitrate packet
    public static final String CS_OBU_PKT_CAM_SS_FRAMERATE = "113"; // Camera sub stream framerate packet
    public static final String CS_OBU_PKT_CAM_SS_FRAME_INTERVAL = "114"; // Camera sub stream frame interval packet
    public static final String CS_OBU_PKT_CAM_VID_REC_ENA_DIS = "115"; // Camera video recording enable/disable packet
    public static final String CS_OBU_PKT_CAM_AUD_REC_ENA_DIS = "116"; // Camera audio recording enable/disable packet
    public static final String CS_OBU_PKT_CAM_NAME_DISPLAY = "117"; // Camera name display packet
    public static final String CS_OBU_PKT_CAM_VID_REC_CONT_SCH = "118"; // Camera video recording continuous schedule packet
    public static final String CS_OBU_PKT_CAM_VID_REC_SCH_CONFIG = "119"; // Camera video recording schedule configuration packet
    public static final String CS_OBU_PKT_CAM_VID_REC_EXPIRED_TIME = "120"; // Camera video recording expired time packet
    public static final String CS_OBU_PKT_CAM_VID_REC_MOTION_DET = "121"; // Camera video recording motion detection packet
    public static final String CS_OBU_PKT_CAM_VID_REC_MOT_DET_SENS = "122"; // Camera video recording motion detection sensitivity packet
    public static final String CS_OBU_PKT_CAM_VID_REC_MOT_DET_AREA = "123"; // Camera video recording motion detection area packet
    public static final String CS_OBU_PKT_CAM_VID_REC_PRE_EVENT = "124"; // Camera video recording pre-event packet
    public static final String CS_OBU_PKT_CAM_VID_REC_POST_EVENT = "125"; // Camera video recording post-event packet
    public static final String CS_OBU_PKT_CAM_RES_SNAP_BACKEND = "126"; // Camera resolution snapshot backend packet
    public static final String CS_OBU_PKT_CAM_SNAP_CONT_FREQ_INT = "127"; // Camera snapshot continuous frequency interval packet
    public static final String CS_OBU_PKT_IGN_ON_OFF_DURATION = "128"; // Ignition on/off duration packet
    public static final String CS_OBU_PKT_GET_FIRMWARE_VERSION = "129"; // Get firmware version packet
    public static final String CS_OBU_PKT_GET_PROTOCOL_VERSION = "130"; // Get protocol version packet
    public static final String CS_OBU_PKT_GET_MAC_ADDRESS = "131"; // Get MAC address packet
    public static final String CS_OBU_PKT_GET_APN_ADDRESS = "145"; // Get APN address packet
    public static final String CS_OBU_PKT_GET_PRIMARY_IPADDRESS = "132"; // Get primary IP address packet
    public static final String CS_OBU_PKT_SET_PRIMARY_IPADDRESS = "133"; // Set primary IP address packet
    public static final String CS_OBU_PKT_GET_SECONDARY_IPADDRESS = "134"; // Get secondary IP address packet
    public static final String CS_OBU_PKT_SET_SECONDARY_IPADDRESS = "135"; // Set secondary IP address packet
    public static final String CS_OBU_PKT_SET_PORT_NO_PRIMARY_IP = "136"; // Set port number for primary IP packet
    public static final String CS_OBU_PKT_SET_PORT_NO_SECONDARY_IP = "137"; // Set port number for secondary IP packet
    public static final String CS_OBU_PKT_SET_VEHICLE_REGNO = "138"; // Set vehicle registration number packet
    public static final String CS_OBU_PKT_SET_APN = "139"; // Set APN packet
    public static final String CS_OBU_PKT_SET_EMERGENCY_INTERVAL = "140"; // Set emergency interval packet
    public static final String CS_OBU_PKT_GET_IMEINO = "141"; // Get IMEI number packet
    public static final String CS_OBU_PKT_TRANS_MODE_ALWAYS_ON = "142"; // Transmission mode always on packet
    public static final String CS_OBU_PKT_GET_VEHICLE_REGNO = "143"; // Get vehicle registration number packet
    public static final String CS_OBU_PKT_GET_IGNITIONONOFF_MODE = "144"; // Get ignition on/off mode packet
    public static final String CS_OBU_PKT_SYSTEM_RESET = "063"; // System reset packet
    public static final String CS_OBU_PKT_PERMSCH = "061"; // Permanent schedule packet
    public static final String CS_OBU_PKT_TEMPSCH = "062"; // Temporary schedule packet
    public static final String CS_OBU_PKT_ADHOC = "052"; // Adhoc packet
    public static final String CS_OBU_PKT_FILE_DOWNLOAD = "060"; // File download packet
    public static final String CS_OBU_PKT_SCH_REQUEST_RESPONSE = "064"; // Schedule request response packet
    public static final String CS_OBU_PKT_ROUTE_START = "051"; // Route start packet
    public static final String CS_OBU_PKT_ROUTE_END = "050"; // Route end packet
    public static final String CS_OBU_PKT_FILE_UPLOAD = "070"; // File upload packet
    public static final String CS_OBU_PKT_ACK = "ACK"; // Acknowledgment packet
    public static final String CS_OBU_PKT_PANIC = "003"; // Panic packet
    public static final String CS_OBU_PKT_HEALTH = "065"; // Health packet

    // Buzzer and default value packets
    public static final String CS_OBU_PKT_CLR_OS_BUZZER = "071"; // Clear overspeed buzzer packet
    public static final String CS_OBU_PKT_CLR_DEFAULT_VALUES = "072"; // Clear default values packet
    public static final String CS_OBU_PKT_SET_OS_VALUES = "074"; // Set overspeed values packet
    public static final String CS_OBU_PKT_SET_HA_VALUES = "079"; // Set harsh acceleration values packet
    public static final String CS_OBU_PKT_SET_HB_VALUES = "080"; // Set harsh braking values packet
    public static final String CS_OBU_PKT_SET_RT_VALUES = "082"; // Set rash turning values packet

    // Get values packets
    public static final String CS_OBU_PKT_GET_OS_VALUES = "075"; // Get overspeed values packet
    public static final String CS_OBU_PKT_GET_HA_VALUES = "076"; // Get harsh acceleration values packet
    public static final String CS_OBU_PKT_GET_HB_VALUES = "077"; // Get harsh braking values packet
    public static final String CS_OBU_PKT_GET_RT_VALUES = "078"; // Get rash turning values packet
}
