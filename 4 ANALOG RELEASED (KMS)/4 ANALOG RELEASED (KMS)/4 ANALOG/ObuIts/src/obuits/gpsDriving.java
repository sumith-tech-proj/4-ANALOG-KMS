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
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import static obuits.MainFrmIts.digOutAudioVoiceCallPin;
import static obuits.MainFrmIts.write_gpio_pin_state;
import static obuits.PanDisplayBoardDiag.DTC_GPS_ANTENNA_ERROR;
import static obuits.PanDisplayBoardDiag.DTC_GPS_INVALID_DATA;
import static obuits.PanDisplayBoardDiag.DTC_GPS_LOST_COMM;
import static obuits.PanDisplayBoardDiag.DTC_HIGH_VOLTAGE;
import static obuits.PanDisplayBoardDiag.DTC_LOW_VOLTAGE;
import static obuits.PanDisplayBoardDiag.DTC_LOW_VOLTAGE_RESET;
import static obuits.PanDisplayBoardDiag.DTC_OBU_LOWVOLTAGE;
import static obuits.PanDisplayBoardDiag.DTC_OBU_OVERHEAT;
import static obuits.PanDisplayBoardDiag.DTC_OBU_OVERVOLTAGE;
import static obuits.PanDisplayBoardDiag.DTC_OVER_HEAT_VOLTAGE;
import static obuits.PanDisplayBoardDiag.DTC_USB_INVALID;
import static obuits.PanDisplayBoardDiag.DTC_USB_INVALID_FILESYSTEM;
import static obuits.PanDisplayBoardDiag.DTC_USB_OVERCURRENT;
import static obuits.PanDisplayBoardDiag.DTC_USB_UNKNOWN;
import static obuits.PanDisplayBoardDiag.DTC_WATCHDOG_RESET;
import static obuits.PanDisplayBoardDiag.PID_APP_SW_REV;
import static obuits.PanDisplayBoardDiag.PID_ARTICLE_NO_SIGN_LEVEL;
import static obuits.PanDisplayBoardDiag.PID_BOARD_TEMP_SENSOR;
import static obuits.PanDisplayBoardDiag.PID_BOOT_LOADER_SW_REV;
import static obuits.PanDisplayBoardDiag.PID_BUS_BUILDER_NO;
import static obuits.PanDisplayBoardDiag.PID_COMPILATION_FW_DATE_TIME;
import static obuits.PanDisplayBoardDiag.PID_CPU_PART_NO;
import static obuits.PanDisplayBoardDiag.PID_CPU_QUALIFICATION;
import static obuits.PanDisplayBoardDiag.PID_CPU_TEMP_RANGE;
import static obuits.PanDisplayBoardDiag.PID_END_CUSTOMER;
import static obuits.PanDisplayBoardDiag.PID_FLASH_UPATE_STATUS;
import static obuits.PanDisplayBoardDiag.PID_FONT_LIB_REV;
import static obuits.PanDisplayBoardDiag.PID_HW_REV;
import static obuits.PanDisplayBoardDiag.PID_INTERNAL_CPU_TEMP;
import static obuits.PanDisplayBoardDiag.PID_LANGUAGE;
import static obuits.PanDisplayBoardDiag.PID_MAX_INPUT_VOLT;
import static obuits.PanDisplayBoardDiag.PID_MAX_TEMP_BOARD;
import static obuits.PanDisplayBoardDiag.PID_MAX_TEMP_CPU;
import static obuits.PanDisplayBoardDiag.PID_MIN_INPUT_VOLT;
import static obuits.PanDisplayBoardDiag.PID_MIN_TEMP_BOARD;
import static obuits.PanDisplayBoardDiag.PID_MIN_TEMP_CPU;
import static obuits.PanDisplayBoardDiag.PID_NO_RESETS;
import static obuits.PanDisplayBoardDiag.PID_OPERATING_HRS;
import static obuits.PanDisplayBoardDiag.PID_ORDER_NO;
import static obuits.PanDisplayBoardDiag.PID_PRODUCTION_DATE;
import static obuits.PanDisplayBoardDiag.PID_SER_NO;
import static obuits.PanDisplayBoardDiag.PID_TEST_DATE_TIME;
import static obuits.PanDisplayBoardDiag.PID_VEHICLE_TYPE;
import static obuits.PanDisplayBoardDiag.objPids;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsDefines.COMP_JBM;
import static obuits.clsDefines.DEF_HARSH_ACC_SPEED_1;
import static obuits.clsDefines.END_PKT;
import static obuits.clsDefines.START_PKT;
import static obuits.clsDefines.MAX_ACCELERATION_LIMIT;
import static obuits.clsDefines.SPACE_CHAR;
import static obuits.clsPacketTypes.GPRS_PKT_APP_STOP;
import static obuits.clsPacketTypes.GPRS_PKT_BUS_STOP_CROSSED;
import static obuits.clsPacketTypes.GPRS_PKT_DRIVER_STOPPED_REASON;
import static obuits.clsPacketTypes.GPRS_PKT_DUTY_END;
import static obuits.clsPacketTypes.GPRS_PKT_HARSH_ACC;
import static obuits.clsPacketTypes.GPRS_PKT_HARSH_BRK;
import static obuits.clsPacketTypes.GPRS_PKT_IGNITION_OFF;
import static obuits.clsPacketTypes.GPRS_PKT_IGNITION_ON;
import static obuits.clsPacketTypes.GPRS_PKT_MAINS_OFF;
import static obuits.clsPacketTypes.GPRS_PKT_MAINS_ON;
import static obuits.clsPacketTypes.GPRS_PKT_NON_STOPPAGE;
import static obuits.clsPacketTypes.GPRS_PKT_OVER_SPEED_END;
import static obuits.clsPacketTypes.GPRS_PKT_OVER_SPEED_START;
import static obuits.clsPacketTypes.GPRS_PKT_PANIC;
import static obuits.clsPacketTypes.GPRS_PKT_REACHED_STOP;
import static obuits.clsPacketTypes.GPRS_PKT_RESET_ON;
import static obuits.clsPacketTypes.GPRS_PKT_ROUTE_END;
import static obuits.clsPacketTypes.GPRS_PKT_ROUTE_SKIP;
import static obuits.clsPacketTypes.GPRS_PKT_ROUTE_START;
import static obuits.clsPacketTypes.GPRS_PKT_SKIP_STOP;
import static obuits.clsPacketTypes.GPRS_PKT_SPECIAL_MSG;
import static obuits.clsSharedVariables.getCurDate;
import static obuits.clsSharedVariables.getCurLatitude;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getCurRouteNo;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsDefines.DEF_HARSH_BRK_SPEED_1;
import static obuits.clsDefines.DIG_INPUT_NONE;
import static obuits.clsDefines.GPS_FIXED;
import static obuits.clsDefines.GPS_PKT_ID;
import static obuits.clsDefines.GYROSCOPE_THROUGH_GPS_ENABLE;
import static obuits.clsDefines.OBU_PKT_ID;
import static obuits.clsDefines.Strings.AUDIO_VOICECALL_COMM_ONOFF;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.Strings.VOICE_CALL_OFF_STATE;
import static obuits.clsDefines.Strings.VOICE_CALL_ON_STATE;
import static obuits.clsDefines.USB_PKT_ID;
import static obuits.clsDefines.SW_FIRMWARE_VERSION;
import static obuits.clsPacketTypes.GPRS_PKT_CAN_DOOR_CLOSE;
import static obuits.clsPacketTypes.GPRS_PKT_CAN_DOOR_OPEN;
import static obuits.clsPacketTypes.GPRS_PKT_CAN_PRESELECTED;
import static obuits.clsPacketTypes.GPRS_PKT_DISBRD_DTC;
import static obuits.clsPacketTypes.GPRS_PKT_DISBRD_PIDS;
import static obuits.clsPacketTypes.GPRS_PKT_MIC_OFF;
import static obuits.clsPacketTypes.GPRS_PKT_MIC_ON;
import static obuits.clsPacketTypes.GPRS_PKT_OBU_ON;
import static obuits.clsPacketTypes.GPRS_PKT_OBU_PIDS;
import static obuits.clsPacketTypes.GPRS_PKT_ROUTE_DEVIATION;
import static obuits.clsPacketTypes.OBU_CS_GPRS_FILEUPLOAD_RES_PKT;
import static obuits.clsPacketTypes.OBU_CS_GPRS_PKT_COMMON_ACK_RES_PKT;
import static obuits.clsSharedVariables.boot_ldr_version;
import static obuits.clsSharedVariables.compilation_fw_datetime;
import static obuits.clsSharedVariables.cpu_part_no;
import static obuits.clsSharedVariables.cpu_qualification;
import static obuits.clsSharedVariables.cpu_temp_range;
import static obuits.clsSharedVariables.font_lib_version;
import static obuits.clsSharedVariables.getCurLatitudeDir;
import static obuits.clsSharedVariables.getCurLongitudeDir;
import static obuits.clsSharedVariables.getCurSpeed;
import static obuits.clsSharedVariables.getDisBrdName;
import static obuits.clsSharedVariables.getGpsState;
import static obuits.clsSharedVariables.getOBUID;
import static obuits.clsSharedVariables.get_driver_id;
import static obuits.clsSharedVariables.gps_antenna_error_cnt;
import static obuits.clsSharedVariables.gps_invalid_data_cnt;
import static obuits.clsSharedVariables.gps_lost_comm_cnt;
import static obuits.clsSharedVariables.hw_revision;
import static obuits.clsSharedVariables.obu_high_volt_cnt;
import static obuits.clsSharedVariables.obu_low_volt_cnt;
import static obuits.clsSharedVariables.obu_low_volt_reset_cnt;
import static obuits.clsSharedVariables.obu_over_heat_cnt;
import static obuits.clsSharedVariables.obu_watchdog_reset_cnt;
import static obuits.clsSharedVariables.serial_no;
import static obuits.clsSharedVariables.test_date_time_pid;
import static obuits.clsSharedVariables.usb_invalid_cnt;
import static obuits.clsSharedVariables.usb_invalid_filesystem_cnt;
import static obuits.clsSharedVariables.usb_overcurrent_cnt;
import static obuits.clsSharedVariables.usb_unknown_cnt;

/**
 * Created by Sumitha on 3/10/2017.
 */
public class gpsDriving {

    // Constants for various speed thresholds
/*
     DEF_HARSH_BRK_DUR_1 = 10;
     DEF_HARSH_BRK_SPEED_1 = 20;
     DEF_HARSH_BRK_DUR_2 = 1;
     DEF_HARSH_BRK_SPEED_2 = 8;

     DEF_HARSH_ACC_DUR_1 = 10;
     DEF_HARSH_ACC_SPEED_1 = 20;
     DEF_HARSH_ACC_DUR_2 = 1;
     DEF_HARSH_ACC_SPEED_2 = 6;
     */
    private static final byte ASCII_ZERO = 48;
    public static double[] ha_latitude = new double[clsDefines.MAX_ACCELERATION_LIMIT + 1];
    public static short over_speed_limit = clsDefines.DEF_OVER_SPEED_LIMIT;

    private static int start_harsh_acc_speed = 0;
    public static short harsh_acc_threshold = DEF_HARSH_ACC_SPEED_1;

    private static int start_harsh_brk_speed = 0;
    public static short harsh_brk_threshold = DEF_HARSH_BRK_SPEED_1;

    private static short[] ha_speed = new short[MAX_ACCELERATION_LIMIT + 1];
    private static short[] hb_speed = new short[MAX_ACCELERATION_LIMIT + 1];

    // Variables for overspeed detection
    private static short max_speed = 0;
    private static short over_speed_state = clsDefines.OVER_SPEED_IDLE_STATE;
    private static short harsh_acc_state = clsDefines.HARSH_ACC_IDLE_STATE;
    private static short harsh_brk_state = clsDefines.HARSH_ACC_IDLE_STATE;

    private static long over_speed_start_time_sec = 0;
    private static byte ha_count = 0;
    private static byte hb_count = 0;

    clsReadFiles objReadFiles = new clsReadFiles();

    byte write_gps_pos_cnt_inc = 0;
    short gps_speed;
    long cur_sec;
    double cur_latitude;
    double cur_longitude;
    String schedule_id = "0";
    String sch_time;
    String sch_date;
    short cur_trip_no = 0;
    String cur_route_no;

    DecimalFormat df = new DecimalFormat("#0.000000");

    // Converts a short value to a byte array
    public static byte[] convertToByteArray(short value) {
        byte[] bytes = new byte[2];
        try {
            ByteBuffer buffer;
            buffer = ByteBuffer.allocate(bytes.length);
            buffer.putShort(value);
            bytes = null;
            return buffer.array();
        } catch (Exception ex) {
        }
        return bytes;
    }

    // Converts an int value to a byte array
    public static byte[] convertToByteArrayInt(int value) {
        byte[] bytes = new byte[4];
        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(bytes.length);
        buffer.putInt(value);
        bytes = null;
        return buffer.array();
    }

    // Triggers a driver alarm for overspeed
    private static void driver_alarm() {
        try {
            if (clsSharedVariables.getOverSpeedBuzzerEnabled()) {
                write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);
                StringBuilder sb1 = new StringBuilder();
                try {
                    sb1.append("AT+QLTONE=1,1000,200,300,3000\r\n");
                    atSerialWrite(sb1.toString());
                } catch (Exception ex) {
                }
                sb1 = null;
                Thread.sleep(1000);
                write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
            }
        } catch (Exception ex) {
        }
    }

    // Detects and handles overspeed events
    void over_speed_detection() {
        try {
            switch (over_speed_state) {
                case clsDefines.OVER_SPEED_IDLE_STATE:
                    if (gps_speed >= over_speed_limit) {
                        max_speed = gps_speed;
                        try {
                            over_speed_start_time_sec = Calendar.getInstance().getTimeInMillis() / 1000;
                        } catch (Exception ex) {
                        }

                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.over_speed_start_pkt((byte) over_speed_limit, (byte) gps_speed);
                            obj16833Pkts = null;
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {
                            over_speed_start_pkt((byte) over_speed_limit, (byte) gps_speed);
                        }
                        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol()) {
                            clsSmcTrackingPackets obj = new clsSmcTrackingPackets();
                            obj.trackingMessagePkt_Smc(1, false, false, true, false);
                            obj = null;
                        }

                        driver_alarm();

                        over_speed_state = clsDefines.OVER_SPEED_DETECTED_STATE;
                    }
                    break;
                case clsDefines.OVER_SPEED_DETECTED_STATE:
                    if (gps_speed >= over_speed_limit) {
                        if (gps_speed >= max_speed) {
                            max_speed = gps_speed;
                        }
                    } else {
                        int dur = 10;
                        try {
                            dur = (int) ((Calendar.getInstance().getTimeInMillis() / 1000) - over_speed_start_time_sec);
                        } catch (Exception ex) {
                        }

                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.over_speed_end_pkt((byte) over_speed_limit, (byte) gps_speed, (byte) max_speed, (short) dur);
                            obj16833Pkts = null;
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {
                            over_speed_end_pkt((byte) over_speed_limit, (byte) gps_speed, (byte) max_speed, (short) dur);
                        }
                        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol()) {
                            clsSmcTrackingPackets obj = new clsSmcTrackingPackets();
                            obj.trackingMessagePkt_Smc(1, false, false, false, true);
                            obj = null;
                        }

                        max_speed = 0;
                        over_speed_state = clsDefines.OVER_SPEED_IDLE_STATE;
                        //   write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
                    }
                    break;
                default:
                    over_speed_state = clsDefines.OVER_SPEED_IDLE_STATE;
                    break;
            }// End of Switch statement
        } catch (Exception ex) {
        } finally {
            schedule_id = null;
            sch_time = null;
            sch_date = null;
            cur_route_no = null;
            df = null;
        }
    }

    int harsh_brk_cnt = 0;
    int harsh_acc_cnt = 0;

    void harsh_break_detection() {
        // Store current GPS speed in history array
        hb_speed[9] = (gps_speed);

        // Check if gyroscope through GPS is enabled
        if (GYROSCOPE_THROUGH_GPS_ENABLE == true) {
            // Check if enough samples are available (harsh_brk_cnt >= 10)
            if ((harsh_brk_cnt >= 10)) {
                // Evaluate conditions for harsh braking based on historical speeds

                // Check if current and previous speed difference meets threshold
                if ((hb_speed[8] - hb_speed[9]) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[8];
                    harsh_brk_cnt = 0;
                    // Trigger harsh braking packet transmission
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                    // Check if sum of speed differences meets threshold
                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) >= harsh_brk_threshold)) {
                    start_harsh_brk_speed = hb_speed[7];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[6];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7]) + (hb_speed[5] - hb_speed[6])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[5];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                        + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[4];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                        + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[3];
                    //harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                        + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])
                        + (hb_speed[2] - hb_speed[3])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[2];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                        + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])
                        + (hb_speed[2] - hb_speed[3]) + (hb_speed[1] - hb_speed[2])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[1];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);

                } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                        + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])
                        + (hb_speed[2] - hb_speed[3]) + (hb_speed[1] - hb_speed[2]) + (hb_speed[0] - hb_speed[1])) >= harsh_brk_threshold) {
                    start_harsh_brk_speed = hb_speed[0];
                    harsh_brk_cnt = 0;
                    harsh_brk_pkt((byte) harsh_brk_threshold, (byte) start_harsh_brk_speed, (byte) gps_speed);
                }

            } else {
                // Increment harsh braking count
                harsh_brk_cnt++;

                // Reset count if it exceeds 254 (byte limit)
                if ((harsh_brk_cnt >= 254)) {
                    harsh_brk_cnt = 0;
                }
            }

            // Shift history array to the right
            hb_speed[0] = hb_speed[1];
            hb_speed[1] = hb_speed[2];
            hb_speed[2] = hb_speed[3];
            hb_speed[3] = hb_speed[4];
            hb_speed[4] = hb_speed[5];
            hb_speed[5] = hb_speed[6];
            hb_speed[6] = hb_speed[7];
            hb_speed[7] = hb_speed[8];
            hb_speed[8] = hb_speed[9];
        }
    }

    void harsh_acceleration_detection() {
        // Store current GPS speed in history array
        if (GYROSCOPE_THROUGH_GPS_ENABLE == true) {
            ha_speed[9] = (gps_speed);

            // Check if gyroscope through GPS is enabled
            // Check if enough samples are available (harsh_acc_cnt >= 10)
            if ((harsh_acc_cnt >= 10)) {
                // Evaluate conditions for harsh acceleration based on historical speeds

                // Check if current and previous speed difference meets threshold
                if (((ha_speed[9] - ha_speed[8]) >= (harsh_acc_threshold))) {
                    start_harsh_acc_speed = ha_speed[8];
                    harsh_acc_cnt = 0;
                    // Transmit harsh acceleration packet
                    {
                        harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);
                    }

                    // Check if sum of speed differences meets threshold
                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7])) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[7];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6])) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[6];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6]
                        + (ha_speed[6] - ha_speed[5]))) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[5];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6]
                        + (ha_speed[6] - ha_speed[5]) + (ha_speed[5] - ha_speed[4]))) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[4];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6]
                        + (ha_speed[6] - ha_speed[5]) + (ha_speed[5] - ha_speed[4]) + (ha_speed[4] - ha_speed[3]))) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[3];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6]
                        + (ha_speed[6] - ha_speed[5]) + (ha_speed[5] - ha_speed[4]) + (ha_speed[4] - ha_speed[3])
                        + (ha_speed[3] - ha_speed[2]))) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[2];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6]
                        + (ha_speed[6] - ha_speed[5]) + (ha_speed[5] - ha_speed[4]) + (ha_speed[4] - ha_speed[3])
                        + (ha_speed[3] - ha_speed[2]) + (ha_speed[2] - ha_speed[1]))) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[1];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);

                } else if ((((ha_speed[9] - ha_speed[8]) + (ha_speed[8] - ha_speed[7]) + (ha_speed[7] - ha_speed[6]
                        + (ha_speed[6] - ha_speed[5]) + (ha_speed[5] - ha_speed[4]) + (ha_speed[4] - ha_speed[3])
                        + (ha_speed[3] - ha_speed[2]) + (ha_speed[2] - ha_speed[1]) + (ha_speed[1] - ha_speed[0]))) >= harsh_acc_threshold)) {
                    start_harsh_acc_speed = ha_speed[0];
                    harsh_acc_cnt = 0;
                    harsh_acc_pkt((byte) harsh_acc_threshold, (byte) start_harsh_acc_speed, (byte) gps_speed);
                }

            } else {
                // Increment harsh acceleration count
                harsh_acc_cnt++;

                // Reset count if it exceeds 254 (byte limit)
                if ((harsh_acc_cnt >= 254)) {
                    harsh_acc_cnt = 0;
                }
            }

            // Shift history array to the right
            ha_speed[0] = ha_speed[1];
            ha_speed[1] = ha_speed[2];
            ha_speed[2] = ha_speed[3];
            ha_speed[3] = ha_speed[4];
            ha_speed[4] = ha_speed[5];
            ha_speed[5] = ha_speed[6];
            ha_speed[6] = ha_speed[7];
            ha_speed[7] = ha_speed[8];
            ha_speed[8] = ha_speed[9];
        }
    }

// START BUS PACKET LINKED LIST FUNCTIONS
    public synchronized void add_first_bus_linked_list(String str) {
        try {
            // Create a new clsLinkedList object
            clsLinkedList obj = new clsLinkedList();

            // Add the string data to the linked list
            obj.addLastData4(str);

            // Set object reference to null
            obj = null;
        } catch (Exception ex) {
            // Handle any exceptions (currently empty)
        }
        // bus_linked_list.addFirst(str); // Commented out, no operation
    }

//********************** START OF KPIT PACKETS ****************************************8
    public void panic_messages(byte panic_id) {
        // Initialize buffer and other variables
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String OBUId;
        try {
            // Get OBU ID
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (pad with zeros if necessary)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // Add current date and time (YYMMDDHHMMSS)
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (01);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            // Add message type
            final_buf[buf_inc++] = GPRS_PKT_PANIC;

            //length of nmea data
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                len = 1 + 24;
            } else {
                len = 1;
            }

            // Convert length to byte array and add to final buffer
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Add reserved 8 bytes (zeroed out)
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Add panic data
            final_buf[buf_inc++] = panic_id;

            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                if (getGpsState() == GPS_FIXED) {
                    // Add current latitude
                    OBUId = String.valueOf(getCurLatitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    // Add current longitude
                    OBUId = String.valueOf(getCurLongitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                } else {
                    // Add default GPS data if not fixed
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                }
            }

            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert final buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            // Write special packets data to log
            objReadFiles.write_special_pkts_data("Panic Message  " + sb.toString());
        } catch (Exception ex) {
            // Handle any exceptions (currently empty)
        } finally {
            // Clean up resources
            sb = null;
            cal = null;
            final_buf = null;
            buf = null;
            OBUId = null;
        }
    }

    public static synchronized String convertToHexString(byte[] byteArray, int length) {
        // Convert byte array to hexadecimal string (currently commented out)
        String nmea_data = "";//HexDump.toHexString(byteArray, 0, length);
        return nmea_data;
    }

    public void driver_stopped_reason_messages(byte reason_id) {
        // Initialize buffer and other variables
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String OBUId;

        try {
            // Get OBU ID
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (pad with zeros if necessary)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // Add current date and time (YYMMDDHHMMSS)
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (01);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            // Add message type
            final_buf[buf_inc++] = GPRS_PKT_DRIVER_STOPPED_REASON;

            // Set length of NMEA data
            len = 1;

            // Convert length to byte array and add to final buffer
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Add reserved 8 bytes (zeroed out)
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Add reason data
            final_buf[buf_inc++] = reason_id;

            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert final buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            // Write log data (commented out)
            // objReadFiles.write_log_stored_send_gprs_data("Driver Delay Reason messages " + sb.toString());
        } catch (Exception ex) {
            // Handle any exceptions (currently empty)
        } finally {
            // Clean up resources
            sb = null;
            cal = null;
            final_buf = null;
            buf = null;
            OBUId = null;
        }
    }

    public void ignition_on() {
        {
            // Initialize buffer and other variables
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            long sec = cal.getTimeInMillis();
            String OBUId;
            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters (pad with zeros if necessary)
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // Add current date and time (YYMMDDHHMMSS)
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (01);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Add message type
                final_buf[buf_inc++] = GPRS_PKT_IGNITION_ON;

                //length of   data
                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    len = 2 + 1 + 24;
                } else {
                    len = 2;
                }

                // Convert length to byte array and add to final buffer
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Add reserved 8 bytes (zeroed out)
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Add ignition on data
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    if (getGpsState() == GPS_FIXED) {
                        // Add speed
                        final_buf[buf_inc++] = (byte) (gps_speed & 0xFF);

                        // Add current latitude
                        OBUId = String.valueOf(getCurLatitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }

                        // Add current longitude
                        OBUId = String.valueOf(getCurLongitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    } else {
                        // Add default GPS data if not fixed
                        final_buf[buf_inc++] = (byte) 0;
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    }
                }

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert final buffer to string and add to linked list
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());

                // Write special packets data to log
                objReadFiles.write_special_pkts_data("Ignition On  " + sb.toString());
            } catch (Exception ex) {
                // Handle any exceptions (currently empty)
            } finally {
                // Clean up resources
                sb = null;
                cal = null;
                final_buf = null;
                buf = null;
                sb = null;
                OBUId = null;
            }
        }
    }

    public void ignition_off() {
        {
            // Initialize buffer and other variables
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            long sec;

            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            sec = cal.getTimeInMillis();
            String OBUId;
            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // Add OBU ID (6 characters, pad with zeros if necessary)
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // Add current date and time (YYMMDDHHMMSS)
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Add message type for ignition off
                final_buf[buf_inc++] = GPRS_PKT_IGNITION_OFF;

                // Determine length of data and add to buffer
                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    len = 2 + 1 + 24;
                } else {
                    len = 2;
                }
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Add reserved 8 bytes (zeroed out)
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Add ignition off data
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // Add GPS data if applicable
                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    if (getGpsState() == GPS_FIXED) {
                        // Add speed
                        final_buf[buf_inc++] = (byte) (gps_speed & 0xFF);

                        // Add current latitude
                        OBUId = String.valueOf(getCurLatitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }

                        // Add current longitude
                        OBUId = String.valueOf(getCurLongitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    } else {
                        // Add default GPS data if not fixed
                        final_buf[buf_inc++] = (byte) 0;
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    }
                }

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert buffer to string and add to linked list
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());

                // Log the packet data
                objReadFiles.write_special_pkts_data("Ignition Off  " + sb.toString());
            } catch (Exception ex) {
                // Handle any exceptions (currently empty)
            } finally {
                // Clean up resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void reset_on() {
        {
            // Initialize buffer and other variables
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();

            String OBUId;
            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // Add OBU ID (6 characters, pad with zeros if necessary)
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // Add current date and time (YYMMDDHHMMSS)
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Add message type for reset on
                final_buf[buf_inc++] = GPRS_PKT_RESET_ON;

                // Determine length of data and add to buffer
                len = 2;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Add reserved 8 bytes (zeroed out)
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Add reset on data
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert buffer to string and add to linked list
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
                // Handle any exceptions (currently empty)
            } finally {
                // Clean up resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void main_on() {
        // Check if the company name matches certain conditions
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {
            // Initialize buffer and other variables
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;

            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // Add OBU ID (6 characters, pad with zeros if necessary)
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // Add current date and time (YYMMDDHHMMSS)
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Add message type for main on
                final_buf[buf_inc++] = GPRS_PKT_MAINS_ON;

                // Determine length of data and add to buffer
                len = 2;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Add reserved 8 bytes (zeroed out)
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Add main on data
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert buffer to string and add to linked list
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
                // Handle any exceptions (currently empty)
            } finally {
                // Clean up resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void main_off() {
        {
            // Initialize buffer and other variables
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];

            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;
            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // Add OBU ID (6 characters, pad with zeros if necessary)
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // Add current date and time (YYMMDDHHMMSS)
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Add message type for main off
                final_buf[buf_inc++] = GPRS_PKT_MAINS_OFF;

                // Determine length of data and add to buffer
                len = 2;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Add reserved 8 bytes (zeroed out)
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Add main off data
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert buffer to string and add to linked list
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
                // Handle any exceptions (currently empty)
            } finally {
                // Clean up resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void mic_on() {
        // Check company name to determine action
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

            // Initialization
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;

            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
                final_buf[buf_inc++] = GPRS_PKT_MIC_ON; // Message Type

                // Length of nmea data
                len = 2;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Ignition on data 24 to 25
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Prepare string representation of the packet
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add to linked list
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
                // Exception handling
            } finally {
                // Clean up resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void mic_off() {
        // Check company name to determine action
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {
            {
                // Initialization
                byte[] final_buf = new byte[200];
                int buf_inc = 0;
                int i = 0;
                short len = 0;
                byte buf[];
                StringBuilder sb = new StringBuilder();
                Calendar cal = Calendar.getInstance();
                String OBUId;

                try {
                    // Get OBU ID
                    OBUId = getOBUID();
                    buf_inc = 0;

                    // Start of the packet
                    final_buf[buf_inc++] = START_PKT;

                    // OBU ID of 12 characters
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 6; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 6; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    // YYMMDDHHMMSS
                    if (cal.get(Calendar.YEAR) > 2000) {
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                    } else {
                        final_buf[buf_inc++] = (byte) (1);
                    }
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
                    final_buf[buf_inc++] = GPRS_PKT_MIC_OFF; // Message Type

                    // Length of nmea data
                    len = 2;
                    buf = convertToByteArray(len);
                    for (i = 0; i < buf.length; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }

                    // Reserved 8 bytes
                    for (i = 0; i < 8; i++) {
                        final_buf[buf_inc++] = 0;
                    }

                    // Ignition off data 24 to 25
                    buf_inc = 24;
                    final_buf[buf_inc++] = 0;
                    final_buf[buf_inc++] = 0;

                    // End of the packet
                    final_buf[buf_inc++] = END_PKT;

                    // Prepare string representation of the packet
                    for (i = 0; i < buf_inc; i++) {
                        sb.append(final_buf[i]);
                        sb.append(clsDefines.FIELD_SEPARATOR);
                    }
                    sb = sb.deleteCharAt(sb.length() - 1);

                    // Add to linked list
                    add_first_bus_linked_list(sb.toString());

                } catch (Exception ex) {
                    // Exception handling
                } finally {
                    // Clean up resources
                    sb = null;
                    final_buf = null;
                    OBUId = null;
                    buf = null;
                    cal = null;
                }
            }
        }
    }

    public synchronized void trip_start_pkt(byte eta_hr, byte eta_min) {
        // Check company name to determine action
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

            // Initialization
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len;
            byte buf[];
            String sch_id = get_driver_id();
            String route_no = getCurRouteNo();
            byte trip_no = (byte) (getCurTripNo() + 1);
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;

            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_ROUTE_START; // Message Type

                // Length of nmea data
                len = 31;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Trip start data
                // 24 to 29 bytes current route id 6 byte
                buf_inc = 24;
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Start Date Time, Format = YYMMDDHHMMSS 30 to 35 bytes
                buf_inc = 30;
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (18);
                }

                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Expected Completion Time, Format = HHMM 36-37
                buf_inc = 36;
                final_buf[buf_inc++] = eta_hr;
                final_buf[buf_inc++] = eta_min;

                // Duty Id, Format = ‘X1Y2’ or ‘ Y2’ (Maximum 16 character ) 38-53 SCHEDULE id
                buf_inc = 38;
                buf = sch_id.getBytes();
                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Trip ID 54
                buf_inc = 54;
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Prepare string representation of the packet
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add to linked list
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
                // Exception handling
            } finally {
                // Clean up resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void trip_end_pkt() {
        // Prepare variables and initialize buffers
        {
            byte[] final_buf = new byte[200]; // Final packet buffer
            int buf_inc = 0; // Buffer index counter
            int i = 0; // Loop iterator
            short len; // Length variable
            byte buf[]; // Byte buffer
            String sch_id = get_driver_id(); // Get driver ID

            // Retrieve current route and trip information
            String route_no = getCurRouteNo();
            byte trip_no = (byte) (getCurTripNo() + 1);

            StringBuilder sb = new StringBuilder(); // String builder for constructing packet
            Calendar cal = Calendar.getInstance(); // Calendar instance for date/time operations

            String OBUId; // OBU ID string
            try {
                OBUId = getOBUID(); // Get OBU ID
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_ROUTE_END; // Message Type

                // Length of the packet
                len = 25;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Duty ID (Schedule ID)
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;
                //duty id (schedule ID 16
                buf_inc = 26;
                buf = sch_id.getBytes();
                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Route ID
                buf_inc = 42;
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Trip ID
                buf_inc = 48;
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Append packet data to StringBuilder
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1); // Remove last FIELD_SEPARATOR
                add_first_bus_linked_list(sb.toString()); // Add packet to linked list

            } catch (Exception ex) {
                // Exception handling
            } finally {
                // Cleanup resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void trip_skip_pkt(byte eta_hr, byte eta_min) {
        // Prepare variables and initialize buffers
        {
            byte[] final_buf = new byte[200]; // Final packet buffer
            int buf_inc = 0; // Buffer index counter
            int i = 0; // Loop iterator
            short len; // Length variable
            byte buf[]; // Byte buffer
            String sch_id = get_driver_id(); // Get driver ID
            String route_no = getCurRouteNo(); // Retrieve current route
            byte trip_no = (byte) (getCurTripNo() + 1); // Increment trip number
            String dat = getCurDate(); // Get current date string
            StringBuilder sb = new StringBuilder(); // String builder for constructing packet
            Calendar cal = Calendar.getInstance(); // Calendar instance for date/time operations

            String OBUId; // OBU ID string
            try {
                OBUId = getOBUID(); // Get OBU ID
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
                final_buf[buf_inc++] = GPRS_PKT_ROUTE_SKIP; // Message Type

                // Length of the packet
                len = 31;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Cancelled Route ID
                buf_inc = 24;
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Start Date Time
                buf_inc = 30;
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Expected Completion Time
                buf_inc = 36;
                final_buf[buf_inc++] = eta_hr;
                final_buf[buf_inc++] = eta_min;

                // Duty Id (Schedule ID)
                buf_inc = 38;
                buf = sch_id.getBytes();
                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Trip ID
                //  buf_inc = 54;
                //final_buf[buf_inc++] = trip
// ID
                buf_inc = 54;
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Append packet data to StringBuilder
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1); // Remove last FIELD_SEPARATOR
                add_first_bus_linked_list(sb.toString()); // Add packet to linked list

            } catch (Exception ex) {
                // Exception handling
            } finally {
                // Cleanup resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void cur_stop_pkt(String stop_name) {
        // Reached stop packet
        {

            byte[] final_buf = new byte[200]; // Final packet buffer
            int buf_inc; // Buffer index
            int i; // Loop iterator
            short len; // Length variable
            byte buf[]; // Byte buffer
            String sch_id = get_driver_id(); // Get driver ID

            // Retrieve current route and trip information
            String route_no = getCurRouteNo();
            byte trip_no = (byte) (getCurTripNo() + 1);

            StringBuilder sb = new StringBuilder(); // String builder for constructing packet
            Calendar cal = Calendar.getInstance(); // Calendar instance for date/time operations

            String OBUId; // OBU ID string
            try {
                OBUId = getOBUID(); // Get OBU ID
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_REACHED_STOP; // Message Type

                // Length of the packet
                len = 48;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Reserved (24 to 25)
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // Duty ID (Schedule ID)
                buf_inc = 26;
                buf = sch_id.getBytes();
                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Route ID
                buf_inc = 42;
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Stop ID
                buf_inc = 48;
                buf = stop_name.getBytes();
                if (buf.length <= 17) {
                    len = (short) (17 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }
                for (i = 0; i < buf.length && i < 17; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved (65 to 70)
                buf_inc = 65;
                for (i = buf_inc; i < 70; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Trip ID
                buf_inc = 71;
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Append packet data to StringBuilder
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1); // Remove last FIELD_SEPARATOR
                add_first_bus_linked_list(sb.toString()); // Add packet to linked list

            } catch (Exception ex) {
                // Exception handling
            } finally {
                // Cleanup resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void app_stop_pkt(String stop_name) {
        // Method to construct and send a packet indicating the bus has reached a stop

        // Initialization of variables and objects
        {
            byte[] final_buf = new byte[200]; // Buffer to hold the final packet data
            int buf_inc; // Index for buffer incrementation
            int i; // General index variable
            short len; // Length variable for data
            byte buf[]; // Temporary byte buffer for conversions
            String sch_id = get_driver_id(); // Fetch driver ID
            String route_no = getCurRouteNo(); // Fetch current route number
            byte trip_no = (byte) (getCurTripNo() + 1); // Fetch current trip number and increment

            StringBuilder sb = new StringBuilder(); // StringBuilder for constructing output
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp

            String OBUId;
            try {
                OBUId = getOBUID(); // Fetch OBU ID
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS timestamp
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_APP_STOP; // Message Type indicating app stop

                // Length of the data packet
                len = 48;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Duty ID (Schedule ID)
                buf_inc = 24; // Start index for duty ID
                final_buf[buf_inc++] = 0; // First reserved byte
                final_buf[buf_inc++] = 0; // Second reserved byte

                buf_inc = 26; // Start index for schedule ID
                buf = sch_id.getBytes();

                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Route ID
                buf_inc = 42; // Start index for route ID
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Stop ID
                buf_inc = 48; // Start index for stop ID
                buf = stop_name.getBytes();

                if (buf.length <= 17) {
                    len = (short) (17 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 17; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 6 bytes
                buf_inc = 65; // Start index for reserved bytes
                for (i = buf_inc; i < 70; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Trip ID
                buf_inc = 71; // Start index for trip ID
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Append to StringBuilder for logging
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add to linked list for processing
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
                // Handle exceptions
            } finally {
                // Cleanup resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void skip_stop_pkt(String stop_name) {
        // Method to construct and send a packet indicating the bus skipped a stop

        // Initialization of variables and objects
        {
            byte[] final_buf = new byte[200]; // Buffer to hold the final packet data
            int buf_inc; // Index for buffer incrementation
            int i; // General index variable
            short len; // Length variable for data
            byte buf[]; // Temporary byte buffer for conversions
            String sch_id = get_driver_id(); // Fetch driver ID
            String route_no = getCurRouteNo(); // Fetch current route number
            byte trip_no = (byte) (getCurTripNo() + 1); // Fetch current trip number and increment

            StringBuilder sb = new StringBuilder(); // StringBuilder for constructing output
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp

            String OBUId;
            try {
                OBUId = getOBUID(); // Fetch OBU ID
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS timestamp
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_SKIP_STOP; // Message Type indicating skip stop

                // Length of the data packet
                len = 48;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Reserved 2 bytes
                buf_inc = 24; // Start index for reserved bytes
                final_buf[buf_inc++] = 0; // First reserved byte
                final_buf[buf_inc++] = 0; // Second reserved byte

                // Duty ID (Schedule ID)
                buf_inc = 26; // Start index for schedule ID
                buf = sch_id.getBytes();

                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Route ID
                buf_inc = 42; // Start index for route ID
                buf = route_no.getBytes();
                if (buf.length <= 6) {

                    len = (short) (6 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Stop ID
                buf_inc = 48; // Start index for stop ID
                buf = stop_name.getBytes();

                if (buf.length <= 17) {
                    len = (short) (17 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 17; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 6 bytes
                buf_inc = 65; // Start index for reserved bytes
                for (i = buf_inc; i < 70; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Trip ID
                buf_inc = 71; // Start index for trip ID
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Append to StringBuilder for logging
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add to linked list for processing
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
                // Handle exceptions
            } finally {
                // Cleanup resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    public void bus_crossed_stop_pkt(String stop_name) {
        // Method to construct and send a packet indicating the bus has crossed a stop

        // Initialization of variables and objects
        {
            byte[] final_buf = new byte[200]; // Buffer to hold the final packet data
            int buf_inc = 0; // Index for buffer incrementation
            int i; // General index variable
            short len; // Length variable for data
            byte buf[]; // Temporary byte buffer for conversions
            String sch_id = get_driver_id(); // Fetch driver ID
            String route_no = getCurRouteNo(); // Fetch current route number
            byte trip_no = (byte) (getCurTripNo() + 1); // Fetch current trip number and increment

            StringBuilder sb = new StringBuilder(); // StringBuilder for constructing output
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp

            String OBUId;
            try {
                OBUId = getOBUID(); // Fetch OBU ID
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS timestamp
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_BUS_STOP_CROSSED; // Message Type indicating bus stop crossed

                // Length of the data packet
                len = 48;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Reserved 2 bytes
                buf_inc = 24; // Start index for reserved bytes
                final_buf[buf_inc++] = 0; // First reserved byte
                final_buf[buf_inc++] = 0; // Second reserved byte

                // Duty ID (Schedule ID)
                buf_inc = 26; // Start index for schedule ID
                buf = sch_id.getBytes();

                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Route ID
                buf_inc = 42; // Start index for route ID
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Stop ID
                buf_inc = 48; // Start index for stop ID
                buf = stop_name.getBytes();

                if (buf.length <= 17) {
                    len = (short) (17 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 17; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 6 bytes
                buf_inc = 65; // Start index for reserved bytes
                for (i = buf_inc; i < 70; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Trip ID
                buf_inc = 71; // Start index for trip ID
                final_buf[buf_inc++] = trip_no;

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Append to StringBuilder for logging
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add to linked list for processing
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
                // Handle exceptions
            } finally {
                // Cleanup resources
                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }

    // Method to construct and send a duty end packet
    public void duty_end_pkt() {
        {
            byte[] final_buf = new byte[200]; // Buffer to hold the final packet
            int buf_inc = 0; // Buffer index
            int i; // Loop variable
            short len; // Length variable
            byte buf[]; // Byte array buffer

            StringBuilder sb = new StringBuilder(); // StringBuilder for constructing the packet string
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp
            String OBUId; // Variable to hold the OBU ID

            try {
                OBUId = getOBUID(); // Get OBU ID

                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO; // Padding with zeros if OBU ID is shorter than 6 characters
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_DUTY_END; // Message Type

                // Length of nmea data
                len = 18;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // TRIP start data (assuming this part is commented out, so no specific action needed here)
                buf_inc = 24;
                final_buf[buf_inc++] = 0;
                final_buf[buf_inc++] = 0;

                // Duty ID (schedule ID)
                buf_inc = 26;
                buf = get_driver_id().getBytes();
                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR; // Padding with space characters
                }
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Constructing the packet string for logging
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Adding the constructed packet string to a linked list
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
                // Exception handling
            } finally {
                // Cleanup
                final_buf = null;
                buf = null;
                sb = null;
                cal = null;
                OBUId = null;
            }
        }
    }

    private void harsh_acc_pkt(byte threshold_speed, byte cur_speed, byte previous_speed) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            cls16833Protocols obj16833Pkts = new cls16833Protocols();
            obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
            obj16833Pkts = null;
        }
        if (clsSharedVariables.getIpAddr4Enable()) {
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i;
            short len;
            byte buf[];

            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;

            try {
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_HARSH_ACC; // Message Type

                // Length of nmea data
                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    len = 3 + 24;
                } else {
                    len = 3;
                }

                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // 24 Threshold value (In Decimal)
                buf_inc = 24;
                final_buf[buf_inc++] = threshold_speed;

                // 25 Current speed (In Decimal)
                final_buf[buf_inc++] = cur_speed;

                // 26 Previous speed (In Decimal)
                final_buf[buf_inc++] = previous_speed;

                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    if (getGpsState() == GPS_FIXED) {
                        // Latitude
                        OBUId = String.valueOf(getCurLatitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }

                        // Longitude
                        OBUId = String.valueOf(getCurLongitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    } else {
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    }
                }

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert byte array to string
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add to linked list
                add_first_bus_linked_list(sb.toString());

                clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
                objHealth.set_last_harsh_acc_time(Calendar.getInstance().getTimeInMillis());
                objHealth = null;

                objReadFiles.write_special_pkts_data("Harsh Acceleration Packet  " + sb.toString());

            } catch (Exception ex) {
                // Handle exceptions
            } finally {
                // Clean up resources
                final_buf = null;
                buf = null;
                sb = null;
                cal = null;
                OBUId = null;
            }
        }
        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol()) {
            clsSmcTrackingPackets obj = new clsSmcTrackingPackets();
            obj.trackingMessagePkt_Smc(1, true, false, false, false);
            obj = null;
        }
    }

    private void harsh_brk_pkt(byte threshold_speed, byte cur_speed, byte previous_speed) {
        // Check if IP Address 1 is enabled
        if (clsSharedVariables.getIpAddr1Enable()) {
            // Create instance of cls16833Protocols and send tracking message packet
            cls16833Protocols obj16833Pkts = new cls16833Protocols();
            obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
            obj16833Pkts = null; // Release object reference
        }

        // Check if IP Address 4 is enabled
        if (clsSharedVariables.getIpAddr4Enable()) {
            // Initialize variables and objects for packet construction
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i;
            short len;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;

            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                // YYMMDDHHMMSS (current date and time)
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1); // Default year value
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1); // Month (1-based)
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH); // Day
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY); // Hour
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE); // Minute
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND); // Second

                // Message Type (Harsh Brake Packet)
                final_buf[buf_inc++] = GPRS_PKT_HARSH_BRK;

                // Length of NMEA data
                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    len = 3 + 24; // Length calculation based on company name
                } else {
                    len = 3;
                }

                buf = convertToByteArray(len); // Convert length to byte array
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes (zeroed out)
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Threshold value (In Decimal)
                buf_inc = 24; // Position in the buffer for threshold speed data
                final_buf[buf_inc++] = threshold_speed;

                // Current speed (In Decimal)
                final_buf[buf_inc++] = cur_speed;

                // Previous speed (In Decimal)
                final_buf[buf_inc++] = previous_speed;

                // Add GPS data if available
                if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                        || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                    if (getGpsState() == GPS_FIXED) {
                        // Add current latitude
                        OBUId = String.valueOf(getCurLatitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }

                        // Add current longitude
                        OBUId = String.valueOf(getCurLongitude());
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    } else {
                        // Default GPS data if not fixed
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                        OBUId = "00.000000000";
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 12; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 12; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }
                    }
                }

                // End of the packet
                final_buf[buf_inc++] = END_PKT;

                // Convert final buffer to string and add to linked list
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);

                // Add packet data to linked list
                add_first_bus_linked_list(sb.toString());

                // Update last harsh brake time in health packet structure
                clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
                objHealth.set_last_harsh_brk_time(Calendar.getInstance().getTimeInMillis());
                objHealth = null; // Release object reference

                // Write special packets data to log
                objReadFiles.write_special_pkts_data("Harsh Brake Packet  " + sb.toString());

            } catch (Exception ex) {
                // Exception handling (currently empty)
            } finally {
                // Clean up resources
                final_buf = null;
                buf = null;
                sb = null;
                cal = null;
                OBUId = null;
            }
        }

        // Check if IP Address 5 and Surat protocol are enabled
        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol()) {
            // Create instance of clsSmcTrackingPackets and send SMC tracking message packet
            clsSmcTrackingPackets obj = new clsSmcTrackingPackets();
            obj.trackingMessagePkt_Smc(1, false, true, false, false);
            obj = null; // Release object reference

        }
    }

    private void over_speed_start_pkt(byte speed_limit, byte cur_speed) {
        // Initialize variables and objects
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i;
        short len;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String OBUId;

        try {
            // Get OBU ID
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (pad with zeros if necessary)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // YYMMDDHHMMSS (current date and time)
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1); // Assuming default value for year if not greater than 2000
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1); // Month (1-based)
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH); // Day
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY); // Hour
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE); // Minute
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND); // Second

            // Message Type (Over Speed Start)
            final_buf[buf_inc++] = GPRS_PKT_OVER_SPEED_START;

            // Length of NMEA data
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                len = 2 + 24; // Length calculation based on company name
            } else {
                len = 2;
            }
            buf = convertToByteArray(len); // Convert length to byte array
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Reserved 8 bytes (zeroed out)
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Trip start data section
            // Upper speed limit
            buf_inc = 24; // Position in the buffer for speed limit data
            final_buf[buf_inc++] = speed_limit;

            // Current speed
            final_buf[buf_inc++] = cur_speed;

            // Add GPS data if available
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                if (getGpsState() == GPS_FIXED) {
                    // Add current latitude
                    OBUId = String.valueOf(getCurLatitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    // Add current longitude
                    OBUId = String.valueOf(getCurLongitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                } else {
                    // Default GPS data if not fixed
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                }
            }

            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert final buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);

            // Add packet data to linked list
            add_first_bus_linked_list(sb.toString());

            // Write special packets data to log
            objReadFiles.write_special_pkts_data("Over Speed Start Packet  " + sb.toString());

        } catch (Exception ex) {
            // Exception handling (currently empty)
        } finally {
            // Clean up resources
            final_buf = null;
            buf = null;
            sb = null;
            cal = null;
            OBUId = null;
        }
    }

    private void over_speed_end_pkt(byte speed_limit, byte cur_speed, byte max_speed, short duration) {

        // Prepare buffer and variables
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i;
        short len;
        byte buf[];

        String OBUId;
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        try {
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (filling remaining with ASCII_ZERO)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // Add timestamp YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            // Message Type
            final_buf[buf_inc++] = GPRS_PKT_OVER_SPEED_END;

            // Length of NMEA data
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                len = 5 + 24;
            } else {
                len = 5;
            }
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Fill speed limit, current speed, max speed, and duration
            buf_inc = 24;
            final_buf[buf_inc++] = speed_limit;
            final_buf[buf_inc++] = cur_speed;
            final_buf[buf_inc++] = max_speed;
            buf = convertToByteArray(duration);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Fill GPS data if available
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                if (getGpsState() == GPS_FIXED) {
                    OBUId = String.valueOf(getCurLatitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    OBUId = String.valueOf(getCurLongitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                } else {
                    // Default GPS data
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                }
            }
            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);

            add_first_bus_linked_list(sb.toString());
            clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
            objHealth.set_last_overspeed_time(Calendar.getInstance().getTimeInMillis());
            objHealth = null;

            objReadFiles.write_special_pkts_data("Over Speed End Packet  " + sb.toString());

        } catch (Exception ex) {
            // Handle exceptions
        } finally {
            // Cleanup
            final_buf = null;
            buf = null;
            sb = null;
            cal = null;
            OBUId = null;
        }
    }

    public void nonstoppage_pkt(short non_stop_time_limit, short duration) {

        // Prepare buffer and variables
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i;
        short len;
        byte buf[];

        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        String OBUId;
        try {
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (filling remaining with ASCII_ZERO)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // Add timestamp YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            // Message Type
            final_buf[buf_inc++] = GPRS_PKT_NON_STOPPAGE;

            // Length of NMEA data
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                len = 4 + 24;
            } else {
                len = 4;
            }
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Fill non-stop time limit and duration
            buf_inc = 24;
            buf = convertToByteArray(non_stop_time_limit);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            buf_inc = 26;
            buf = convertToByteArray(duration);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Fill GPS data if available
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                if (getGpsState() == GPS_FIXED) {
                    OBUId = String.valueOf(getCurLatitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    OBUId = String.valueOf(getCurLongitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                } else {
                    // Default GPS data
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                }
            }

            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);

            add_first_bus_linked_list(sb.toString());

            objReadFiles.write_special_pkts_data("Non Stoppage Packet  " + sb.toString());// sb.toString());
            // objReadFiles.write_log_stored_send_gprs_data(" Non Stoppage  " + sb.toString());
        } catch (Exception ex) {
        } finally {
            final_buf = null;
            buf = null;
            sb = null;
            cal = null;
            OBUId = null;

        }
    }

    public void special_messages(byte msg_id) {

        // Prepare buffer and variables
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        String OBUId;
        try {
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (filling remaining with ASCII_ZERO)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // Add timestamp YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            // Message Type
            final_buf[buf_inc++] = GPRS_PKT_SPECIAL_MSG;

            // Length of NMEA data
            len = 1;
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Message ID data
            final_buf[buf_inc++] = msg_id;

            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            // Uncomment the following line to log the message
            // objReadFiles.write_log_stored_send_gprs_data("SPECIAL messages " + sb.toString());
        } catch (Exception ex) {
            // Handle exceptions
        } finally {
            // Cleanup
            final_buf = null;
            buf = null;
            sb = null;
            cal = null;
            OBUId = null;
        }
    }

    public void obu_started() {

        // Prepare buffer and variables
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        String OBUId;

        try {
            OBUId = getOBUID();
            buf_inc = 0;

            // Start of the packet
            final_buf[buf_inc++] = START_PKT;

            // OBU ID of 12 characters (filling remaining with ASCII_ZERO)
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            // Add timestamp YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            // Message Type
            final_buf[buf_inc++] = GPRS_PKT_OBU_ON;

            // Length of NMEA data
            len = 2;
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            // Reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            // Ignition on data (reserved 8 bytes start at 24)
            buf_inc = clsSharedVariables.reset_type;
            final_buf[buf_inc++] = 0;

            // End of the packet
            final_buf[buf_inc++] = END_PKT;

            // Convert buffer to string and add to linked list
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            // Uncomment the following line to log the message
            // objReadFiles.write_log_stored_send_gprs_data("Ignition On " + sb.toString());
        } catch (Exception ex) {
            // Handle exceptions
        } finally {
            // Cleanup
            sb = null;
            final_buf = null;
            OBUId = null;
            buf = null;
            cal = null;
        }
    }

    public void can_preselected_params_pkt(String can_str) {
        // if (PROTOCOLS_SUMITH == false) 
        {
            // Initialize the final buffer with a size of 2000 bytes
            byte[] final_buf = new byte[2000];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[] = new byte[1];
            Calendar cal = Calendar.getInstance();
            short pgn;
            short spn;
            int val;
            String OBUId;

            short canlen;
            String[] candata_split_str;
            String[] split_str;
            int can_len_inc = 0;
            int can_inc = 0;
            clsCanQueue objCanQueue = new clsCanQueue();

            try {
                // Get OBU ID
                OBUId = getOBUID();
                buf_inc = 0;

                // Start of the packet
                final_buf[buf_inc++] = START_PKT;

                // OBU ID of 12 characters (fill with 0 if length is less than 6)
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // YYMMDDHHMMSS timestamp
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (2019 - 2000);
                }

                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                // Message Type (GPRS_PKT_CAN_PRESELECTED)
                final_buf[buf_inc++] = GPRS_PKT_CAN_PRESELECTED;

                // Length of CAN data
                candata_split_str = can_str.split("#");
                canlen = (short) candata_split_str.length;
                len = (short) (canlen * 8);
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                // Reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                // Process each CAN data entry
                for (can_inc = 0; can_inc < canlen; can_inc++) {
                    try {
                        split_str = candata_split_str[can_inc].split(",");
                        if (split_str.length >= 3) {
                            pgn = (short) Integer.parseInt(split_str[0]);
                            spn = (short) Integer.parseInt(split_str[1]);
                            val = Integer.parseInt(split_str[2]);

                            // Convert and add PGN, SPN, and Value to final_buf
                            buf = convertToByteArray(pgn);
                            for (i = 0; i < buf.length; i++) {
                                final_buf[buf_inc++] = buf[i];
                            }
                            buf = convertToByteArray(spn);
                            for (i = 0; i < buf.length; i++) {
                                final_buf[buf_inc++] = buf[i];
                            }
                            buf = convertToByteArrayInt(val);
                            for (i = 0; i < buf.length; i++) {
                                final_buf[buf_inc++] = buf[i];
                            }
                            can_len_inc++;
                        }
                    } catch (Exception ex) {
                        // Handle any exceptions while parsing CAN data
                    }
                }

                // Update the length in the packet
                len = (short) (can_len_inc * 8);
                buf = convertToByteArray(len);
                final_buf[14] = buf[0];
                final_buf[15] = buf[1];

                // End of the packet
                final_buf[buf_inc++] = END_PKT;
                buf = new byte[buf_inc];
                for (i = 0; i < buf_inc; i++) {
                    buf[i] = final_buf[i];
                }
                objCanQueue.addData(buf);
                //out.println(" Can data added to queue  ");
            } catch (Exception ex) {
                // Handle any exceptions in processing CAN parameters packet
            } finally {
                // Clean up resources
                final_buf = null;
                OBUId = null;
                candata_split_str = null;
                split_str = null;
                can_str = null;
                buf = null;
                cal = null;
                objCanQueue = null;
            }
        }
    }

    public void obu_pid_pkt() {
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {
            byte buf[] = null;
            byte dev_pid = 0;
            byte[] final_buf = new byte[500];
            int buf_inc = 0;
            int i = 0;
            Calendar cal = Calendar.getInstance();
            String OBUId;
            byte LEN1_BYTE = 14;
            byte LEN2_BYTE = 15;

            short pkt_len_inc = 0;
            StringBuilder sb = new StringBuilder();

            // Iterate through device PIDs range 100 to 109
            for (dev_pid = 100; dev_pid < 110; dev_pid++) {
                //if (getDisBrdName() == SUMITH_DISBRD)
                {
                    try {
                        OBUId = getOBUID();
                        buf_inc = 0;
                        //Start of the packet
                        final_buf[buf_inc++] = START_PKT;

                        // OBU ID of 12 characters (fill with 0 if length is less than 6)
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 6; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 6; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }

                        // YYMMDDHHMMSS timestamp
                        if (cal.get(Calendar.YEAR) > 2000) {
                            final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                        } else {
                            final_buf[buf_inc++] = (byte) (2019 - 2000);
                        }
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                        // Message Type (GPRS_PKT_OBU_PIDS)
                        final_buf[buf_inc++] = GPRS_PKT_OBU_PIDS;

                        // Length placeholders for PIDs data
                        LEN1_BYTE = (byte) buf_inc++;
                        LEN2_BYTE = (byte) buf_inc++;

                        // Reserved 8 bytes
                        for (i = 0; i < 8; i++) {
                            final_buf[buf_inc++] = 0;
                        }

                        buf_inc = 24;
                        final_buf[buf_inc++] = 1;
                        pkt_len_inc++;

                        // Convert and add current device PID to final_buf
                        buf = convertToByteArray(dev_pid);
                        for (i = 0; i < buf.length; i++) {
                            final_buf[buf_inc++] = buf[i];
                            pkt_len_inc++;
                        }

                        // Switch case to handle different PIDs
                        switch (dev_pid) {
                            // Handle each PID case with respective data
                            case PID_HW_REV: {
                                // Add hardware revision data
                                final_buf[buf_inc++] = (byte) hw_revision.length(); // Length of PID data
                                pkt_len_inc++;
                                for (i = 0; i < hw_revision.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) hw_revision.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;
                            // Repeat similar cases for other PIDs (serial number, software versions, etc.)
                            case PID_SER_NO: {
                                // Add serial number data
                                final_buf[buf_inc++] = (byte) serial_no.length(); // Length of PID data
                                pkt_len_inc++;
                                for (i = 0; i < serial_no.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) serial_no.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;
                            case PID_BOOT_LOADER_SW_REV: {
                                final_buf[buf_inc++] = (byte) boot_ldr_version.length(); //1 byte length of pid
                                pkt_len_inc++;
                                for (i = 0; i < boot_ldr_version.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) boot_ldr_version.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;

                            case PID_APP_SW_REV: {
                                final_buf[buf_inc++] = (byte) SW_FIRMWARE_VERSION.length(); //1 byte length of pid
                                pkt_len_inc++;
                                for (i = 0; i < SW_FIRMWARE_VERSION.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) SW_FIRMWARE_VERSION.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;

                            case PID_FONT_LIB_REV: {
                                final_buf[buf_inc++] = (byte) font_lib_version.length(); //1 byte length of pid
                                pkt_len_inc++;
                                for (i = 0; i < font_lib_version.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) font_lib_version.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;

                            case PID_CPU_PART_NO: {
                                final_buf[buf_inc++] = (byte) cpu_part_no.length(); //1 byte length of pid
                                pkt_len_inc++;
                                for (i = 0; i < cpu_part_no.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) cpu_part_no.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;

                            case PID_CPU_QUALIFICATION: {
                                final_buf[buf_inc++] = (byte) cpu_qualification.length(); //1 byte length of pid
                                pkt_len_inc++;
                                for (i = 0; i < cpu_qualification.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) cpu_qualification.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;
                            case PID_CPU_TEMP_RANGE: {
                                final_buf[buf_inc++] = (byte) cpu_temp_range.length(); //1 byte length of pid
                                pkt_len_inc++;
                                for (i = 0; i < cpu_temp_range.length(); i++) {
                                    final_buf[buf_inc++] = (byte) (((byte) cpu_temp_range.charAt(i)) & 0XFF);
                                    pkt_len_inc++;
                                }
                            }
                            break;
                            case PID_COMPILATION_FW_DATE_TIME: {
                                final_buf[buf_inc++] = (byte) compilation_fw_datetime.length(); //1 byte length of pid
                                pkt_len_inc++;
                                try {
                                    for (i = 0; i < compilation_fw_datetime.length(); i++) {
                                        final_buf[buf_inc++] = (byte) (((byte) compilation_fw_datetime.charAt(i)) & 0XFF);
                                        pkt_len_inc++;
                                    }
                                } catch (Exception ex) {
                                }
                            }
                            break;
                            case PID_TEST_DATE_TIME: {
                                if (clsSharedVariables.test_date_time_pid == null) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
                                    sdf.format(cal.getTime());
                                    sdf = null;
                                }
                                final_buf[buf_inc++] = (byte) test_date_time_pid.length(); //1 byte length of pid
                                pkt_len_inc++;
                                try {
                                    for (i = 0; i < test_date_time_pid.length(); i++) {
                                        final_buf[buf_inc++] = (byte) (((byte) test_date_time_pid.charAt(i)) & 0XFF);
                                        pkt_len_inc++;
                                    }
                                } catch (Exception ex) {
                                }
                            }
                            break;
                        }
                        buf = convertToByteArray(pkt_len_inc);
                        final_buf[LEN1_BYTE] = buf[0];
                        final_buf[LEN2_BYTE] = buf[1];

                        // End of the packet
                        final_buf[buf_inc++] = END_PKT;

                        // Append final_buf to StringBuilder sb
                        if (sb.length() > 0) {
                            sb = sb.delete(0, sb.length());
                        }
                        for (i = 0; i < buf_inc; i++) {
                            sb.append(final_buf[i]);
                            sb.append(clsDefines.FIELD_SEPARATOR);
                        }
                        sb = sb.deleteCharAt(sb.length() - 1);

                        // Add sb.toString() to a linked list or processing function
                        add_first_bus_linked_list(sb.toString());

                    } catch (Exception ex) {
                        // Handle exceptions during processing of OBU PIDs
                    } finally {
                        // Cleanup resources
                    }
                }
            }
            // Cleanup resources after processing all device PIDs
            final_buf = null;
            OBUId = null;
            buf = null;
            sb = null;
            cal = null;
        }
    }

    public void disbrd_pids() {
        // Initialize board type variable
        byte brd_type = 0;

        // Iterate through all board types (0 to 3)
        for (brd_type = 0; brd_type < 4; brd_type++) {
            // Call disbrd_pid_pkt() method for each board type and each PID type
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_HW_REV);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_SER_NO);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_BOOT_LOADER_SW_REV);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_APP_SW_REV);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_FONT_LIB_REV);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_CPU_PART_NO);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_CPU_QUALIFICATION);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_CPU_TEMP_RANGE);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_COMPILATION_FW_DATE_TIME);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_FLASH_UPATE_STATUS);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_TEST_DATE_TIME);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_ARTICLE_NO_SIGN_LEVEL);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_PRODUCTION_DATE);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_END_CUSTOMER);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_ORDER_NO);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_VEHICLE_TYPE);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_BUS_BUILDER_NO);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_LANGUAGE);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_BOARD_TEMP_SENSOR);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_INTERNAL_CPU_TEMP);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_MIN_TEMP_CPU);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_MAX_TEMP_CPU);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_MAX_TEMP_BOARD);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_MIN_TEMP_BOARD);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_MAX_INPUT_VOLT);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_MIN_INPUT_VOLT);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_OPERATING_HRS);
            disbrd_pid_pkt(brd_type, PanDisplayBoardDiag.PID_NO_RESETS);
        }
    }

// Add further comments or modifications as needed for clarity
    public void disbrd_pid_pkt(byte disbrd_type, short pid_code) {
        byte buf[] = null;
        {
            // Check if the display board name matches a specific value
            if (getDisBrdName() == SUMITH_DISBRD) {
                byte[] final_buf = new byte[500];
                int buf_inc = 0;
                int i = 0;
                Calendar cal = Calendar.getInstance();
                String OBUId;
                byte LEN1_BYTE = 14;
                byte LEN2_BYTE = 15;
                short pkt_len_inc = 0;
                StringBuilder sb = new StringBuilder();

                // Adjust display board type if it equals 3
                if (disbrd_type == 3) {
                    disbrd_type = 4; // For internal display board packet 4
                }

                try {
                    // Get the OBU (On-Board Unit) ID
                    OBUId = getOBUID();
                    buf_inc = 0;

                    // Start of the packet
                    final_buf[buf_inc++] = START_PKT;

                    // Add OBU ID of up to 12 characters, padded with zeros if necessary
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 6; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 6; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    // Add current date and time (YYMMDDHHMMSS format)
                    if (cal.get(Calendar.YEAR) > 2000) {
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                    } else {
                        final_buf[buf_inc++] = (byte) (2019 - 2000);
                    }
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
                    final_buf[buf_inc++] = GPRS_PKT_DISBRD_PIDS; // Message Type

                    // Reserve space for length of CAN data
                    LEN1_BYTE = (byte) buf_inc++;
                    LEN2_BYTE = (byte) buf_inc++;

                    // Reserved 8 bytes
                    for (i = 0; i < 8; i++) {
                        final_buf[buf_inc++] = 0;
                    }

                    // Set buffer index to 24 and add display board type and number of PIDs
                    buf_inc = 24;
                    final_buf[buf_inc++] = disbrd_type;
                    pkt_len_inc++;
                    final_buf[buf_inc++] = 1; // Number of PIDs, default is 1
                    pkt_len_inc++;

                    // Convert PID code to byte array and add it to the final buffer
                    buf = convertToByteArray(pid_code);
                    for (i = 0; i < buf.length; i++) {
                        final_buf[buf_inc++] = buf[i];
                        pkt_len_inc++;
                    }

                    // Switch statement to handle different PID codes
                    switch (pid_code) {
                        case PID_HW_REV: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].hw_rev_val.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].hw_rev_val.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].hw_rev_val.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_SER_NO: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].ser_no.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].ser_no.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].ser_no.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_BOOT_LOADER_SW_REV: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].boot_loader_sw_rev.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].boot_loader_sw_rev.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].boot_loader_sw_rev.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_APP_SW_REV: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].app_sw_rev.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].app_sw_rev.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].app_sw_rev.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_FONT_LIB_REV: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].font_lib_rev.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].font_lib_rev.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].font_lib_rev.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_CPU_PART_NO: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].cpu_part_no.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].cpu_part_no.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].cpu_part_no.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_CPU_QUALIFICATION: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].cpu_qual.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].cpu_qual.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].cpu_qual.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_CPU_TEMP_RANGE: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].cpu_temp_range.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].cpu_temp_range.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].cpu_temp_range.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_COMPILATION_FW_DATE_TIME: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].compilation_fw_date.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].compilation_fw_date.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].compilation_fw_date.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_FLASH_UPATE_STATUS: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].flash_update_status.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].flash_update_status.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].flash_update_status.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_TEST_DATE_TIME: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].test_date_time.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].test_date_time.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].test_date_time.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_ARTICLE_NO_SIGN_LEVEL: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].article_no_sign_level.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].article_no_sign_level.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].article_no_sign_level.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_PRODUCTION_DATE: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].prod_date.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].prod_date.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].prod_date.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_END_CUSTOMER: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].end_customer.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].end_customer.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].end_customer.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_ORDER_NO: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].order_no.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].order_no.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].order_no.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_VEHICLE_TYPE: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].vehicle_type.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].vehicle_type.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].vehicle_type.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_BUS_BUILDER_NO: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].bus_builder_no.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].bus_builder_no.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].bus_builder_no.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_LANGUAGE: {
                            final_buf[buf_inc++] = (byte) (String.valueOf(objPids[disbrd_type].language).length());  //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < (String.valueOf(objPids[disbrd_type].language).length()); i++) {
                                final_buf[buf_inc++] = (byte) String.valueOf(objPids[disbrd_type].language).charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_BOARD_TEMP_SENSOR: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].board_temp_sensor.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].board_temp_sensor.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].board_temp_sensor.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_INTERNAL_CPU_TEMP: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].internal_cpu_temp.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].internal_cpu_temp.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].internal_cpu_temp.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_MIN_TEMP_CPU: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].min_cpu_temp.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].min_cpu_temp.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].min_cpu_temp.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_MAX_TEMP_CPU: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].max_cpu_temp.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].max_cpu_temp.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].max_cpu_temp.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_MIN_TEMP_BOARD: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].min_board_temp.length(); //1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].min_board_temp.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].min_board_temp.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_MAX_TEMP_BOARD: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].max_board_temp.length();//1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].max_board_temp.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].max_board_temp.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_MIN_INPUT_VOLT: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].min_input_volt.length();//1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].min_input_volt.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].min_input_volt.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_MAX_INPUT_VOLT: {
                            final_buf[buf_inc++] = (byte) objPids[disbrd_type].max_input_volt.length();//1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < objPids[disbrd_type].max_input_volt.length(); i++) {
                                final_buf[buf_inc++] = (byte) objPids[disbrd_type].max_input_volt.charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_OPERATING_HRS: {
                            final_buf[buf_inc++] = (byte) (String.valueOf(objPids[disbrd_type].operating_hours).length());//1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < (String.valueOf(objPids[disbrd_type].operating_hours).length()); i++) {
                                final_buf[buf_inc++] = (byte) String.valueOf(objPids[disbrd_type].operating_hours).charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                        case PID_NO_RESETS: {
                            final_buf[buf_inc++] = (byte) (String.valueOf(objPids[disbrd_type].no_resets).length());//1 byte length of pid
                            pkt_len_inc++;
                            for (i = 0; i < (String.valueOf(objPids[disbrd_type].no_resets).length()); i++) {
                                final_buf[buf_inc++] = (byte) String.valueOf(objPids[disbrd_type].no_resets).charAt(i);
                                pkt_len_inc++;
                            }
                        }
                        break;
                    }

                    // Add length of CAN data to the final buffer
                    buf = convertToByteArray(pkt_len_inc);
                    final_buf[LEN1_BYTE] = buf[0];
                    final_buf[LEN2_BYTE] = buf[1];

                    // End of the packet
                    final_buf[buf_inc++] = END_PKT;

                    // Clear the StringBuilder and add the final buffer data to it
                    if (sb.length() > 0) {
                        sb = sb.delete(0, sb.length());
                    }
                    for (i = 0; i < buf_inc; i++) {
                        sb.append(final_buf[i]);
                        sb.append(clsDefines.FIELD_SEPARATOR);
                    }
                    sb = sb.deleteCharAt(sb.length() - 1);

                    // Add the packet to the linked list
                    add_first_bus_linked_list(sb.toString());

                } catch (Exception ex) {
                    // Handle exception
                } finally {
                    // Clean up resources
                    final_buf = null;
                    OBUId = null;
                    buf = null;
                    sb = null;
                    cal = null;
                }
            }
        }
    }

    public void obu_dtc_pkt() {
        // Check if the company name matches specific constants
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

            byte buf[] = null;
            int dev_dtc = 0;
            byte[] final_buf = new byte[500];
            int buf_inc = 0;
            int i = 0;
            Calendar cal = Calendar.getInstance();
            String OBUId;
            byte LEN1_BYTE = 14;
            byte LEN2_BYTE = 15;
            short pkt_len_inc = 0;
            StringBuilder sb = new StringBuilder();

            OBUId = getOBUID();

            // Iterate over each device DTC
            for (dev_dtc = 0; dev_dtc < 5; dev_dtc++) {
                // Check for specific disbrd name
                if (getDisBrdName() == SUMITH_DISBRD) {
                    try {
                        buf_inc = 0;
                        // Start of the packet
                        final_buf[buf_inc++] = START_PKT;

                        // Append OBU ID to final buffer
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 6; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 6; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO;
                        }

                        // Append current date and time to final buffer
                        if (cal.get(Calendar.YEAR) > 2000) {
                            final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                        } else {
                            final_buf[buf_inc++] = (byte) (2019 - 2000);
                        }
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                        // Set message type in final buffer
                        final_buf[buf_inc++] = GPRS_PKT_DISBRD_DTC; // Message Type

                        // Length of CAN data
                        LEN1_BYTE = (byte) buf_inc++;
                        LEN2_BYTE = (byte) buf_inc++;

                        // Reserved 8 bytes
                        for (i = 0; i < 8; i++) {
                            final_buf[buf_inc++] = 0;
                        }

                        // Set OBU packet ID in final buffer
                        buf_inc = 24;
                        final_buf[buf_inc++] = OBU_PKT_ID;
                        pkt_len_inc = 0;

                        // Switch based on device DTC type
                        switch (dev_dtc) {
                            case 0: {
                                // Append watchdog reset count and status
                                final_buf[buf_inc++] = (byte) (obu_watchdog_reset_cnt & 0xFF);
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_WATCHDOG_RESET);
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i];
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 --> inactive, 1 --> active
                                pkt_len_inc++;
                                break;
                            }
                            case 1: {
                                // Append low voltage reset count and status
                                final_buf[buf_inc++] = (byte) (obu_low_volt_reset_cnt & 0xFF);
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_LOW_VOLTAGE_RESET);
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i];
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 --> inactive, 1 --> active
                                pkt_len_inc++;
                                break;
                            }
                            case 2: {
                                // Append high voltage count and status
                                final_buf[buf_inc++] = (byte) (obu_high_volt_cnt & 0xFF);
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_OBU_OVERVOLTAGE);
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i];
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 --> inactive, 1 --> active
                                pkt_len_inc++;
                                break;
                            }
                            case 3: {
                                // Append low voltage count and status
                                final_buf[buf_inc++] = (byte) (obu_low_volt_cnt & 0xFF);
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_OBU_LOWVOLTAGE);
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i];
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 --> inactive, 1 --> active
                                pkt_len_inc++;
                                break;
                            }
                            case 4: {
                                // Append overheat count and status
                                final_buf[buf_inc++] = (byte) (obu_over_heat_cnt & 0xFF);
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_OBU_OVERHEAT);
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i];
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 --> inactive, 1 --> active
                                pkt_len_inc++;
                                break;
                            }
                        }

                        // Convert packet length increment to byte array and set in final buffer
                        buf = convertToByteArray(pkt_len_inc);
                        final_buf[LEN1_BYTE] = buf[0];
                        final_buf[LEN2_BYTE] = buf[1];

                        // End of the packet
                        final_buf[buf_inc++] = END_PKT;

                        // Construct final string representation and add to linked list
                        if (sb.length() > 0) {
                            sb = sb.delete(0, sb.length());
                        }
                        for (i = 0; i < buf_inc; i++) {
                            sb.append(final_buf[i]);
                            sb.append(clsDefines.FIELD_SEPARATOR);
                        }
                        sb = sb.deleteCharAt(sb.length() - 1);
                        add_first_bus_linked_list(sb.toString());

                    } catch (Exception ex) {
                        // Handle any exceptions
                    } finally {
                        // Clean up resources
                    }
                }
            }

            // Clean up resources outside the loop
            final_buf = null;
            OBUId = null;
            cal = null;
            buf = null;
            sb = null;
        }
    }

    public void usb_dtc_pkt() {
        // Check if the company name matches certain predefined constants
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

            byte buf[] = null; // Buffer for byte arrays
            int dev_dtc = 0; // Device DTC counter
            byte[] final_buf = new byte[500]; // Final data packet buffer
            int buf_inc = 0; // Buffer increment counter
            int i = 0; // General purpose loop variable
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp
            String OBUId; // OBU ID string
            byte LEN1_BYTE = 14; // Length byte 1
            byte LEN2_BYTE = 15; // Length byte 2
            short pkt_len_inc = 0; // Packet length increment
            StringBuilder sb = new StringBuilder(); // String builder for constructing final output

            OBUId = getOBUID(); // Get OBU ID from a method

            // Loop over device DTCs (0 to 3)
            for (dev_dtc = 0; dev_dtc < 4; dev_dtc++) {
                // Check if display board name matches SUMITH_DISBRD
                if (getDisBrdName() == SUMITH_DISBRD) {
                    try {
                        buf_inc = 0; // Reset buffer increment counter

                        // Start of the packet
                        final_buf[buf_inc++] = START_PKT;

                        // Copy OBU ID into final buffer
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 6; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 6; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO; // Pad with zeros if OBU ID is shorter
                        }

                        // YYMMDDHHMMSS timestamp
                        if (cal.get(Calendar.YEAR) > 2000) {
                            final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                        } else {
                            final_buf[buf_inc++] = (byte) (2019 - 2000); // Default year if current year is not valid
                        }
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1); // Month
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH); // Day
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY); // Hour
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE); // Minute
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND); // Second
                        final_buf[buf_inc++] = GPRS_PKT_DISBRD_DTC; // Message Type

                        // Length of CAN data (placeholders for now)
                        LEN1_BYTE = (byte) buf_inc++;
                        LEN2_BYTE = (byte) buf_inc++;

                        // Reserved 8 bytes
                        for (i = 0; i < 8; i++) {
                            final_buf[buf_inc++] = 0; // Fill with zeros
                        }

                        // Set packet identifier based on device DTC
                        buf_inc = 24; // Start at offset 24
                        final_buf[buf_inc++] = USB_PKT_ID;
                        pkt_len_inc = 0; // Reset packet length increment

                        // Switch based on device DTC type
                        switch (dev_dtc) {
                            case 0: {
                                final_buf[buf_inc++] = (byte) (usb_invalid_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_USB_INVALID); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                            case 1: {
                                final_buf[buf_inc++] = (byte) (usb_unknown_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_USB_UNKNOWN); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                            case 2: {
                                final_buf[buf_inc++] = (byte) (usb_invalid_filesystem_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_USB_INVALID_FILESYSTEM); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                            case 3: {
                                final_buf[buf_inc++] = (byte) (usb_overcurrent_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_USB_OVERCURRENT); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                        }

                        // Set packet length in final buffer
                        buf = convertToByteArray(pkt_len_inc);
                        final_buf[LEN1_BYTE] = buf[0];
                        final_buf[LEN2_BYTE] = buf[1];

                        // End of the packet
                        final_buf[buf_inc++] = END_PKT;

                        // Construct string representation of the packet
                        if (sb.length() > 0) {
                            sb = sb.delete(0, sb.length());
                        }
                        for (i = 0; i < buf_inc; i++) {
                            sb.append(final_buf[i]); // Append each byte to StringBuilder
                            sb.append(clsDefines.FIELD_SEPARATOR); // Add field separator
                        }
                        sb = sb.deleteCharAt(sb.length() - 1); // Remove last field separator

                        // Add formatted packet to linked list
                        add_first_bus_linked_list(sb.toString());

                    } catch (Exception ex) {
                        // Exception handling
                    } finally {
                        // Cleanup resources
                    }
                }
            }

            // Nullify references to release memory
            final_buf = null;
            OBUId = null;
            cal = null;
            buf = null;
            sb = null;
        }
    }

    public void gps_dtc_pkt() {
        // Check if the company name matches certain predefined constants
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

            byte buf[] = null; // Buffer for byte arrays
            int dev_dtc = 0; // Device DTC counter
            byte[] final_buf = new byte[500]; // Final data packet buffer
            int buf_inc = 0; // Buffer increment counter
            int i = 0; // General purpose loop variable
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp
            String OBUId; // OBU ID string
            byte LEN1_BYTE = 14; // Length byte 1
            byte LEN2_BYTE = 15; // Length byte 2
            short pkt_len_inc = 0; // Packet length increment
            StringBuilder sb = new StringBuilder(); // String builder for constructing final output

            OBUId = getOBUID(); // Get OBU ID from a method

            // Loop over device DTCs (0 to 2)
            for (dev_dtc = 0; dev_dtc < 3; dev_dtc++) {
                // Check if display board name matches SUMITH_DISBRD
                if (getDisBrdName() == SUMITH_DISBRD) {
                    try {
                        buf_inc = 0; // Reset buffer increment counter

                        // Start of the packet
                        final_buf[buf_inc++] = START_PKT;

                        // Copy OBU ID into final buffer
                        buf = OBUId.getBytes();
                        for (i = 0; i < buf.length && i < 6; i++) {
                            final_buf[buf_inc++] = buf[i];
                        }
                        for (; i < 6; i++) {
                            final_buf[buf_inc++] = ASCII_ZERO; // Pad with zeros if OBU ID is shorter
                        }

                        // YYMMDDHHMMSS timestamp
                        if (cal.get(Calendar.YEAR) > 2000) {
                            final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                        } else {
                            final_buf[buf_inc++] = (byte) (2019 - 2000); // Default year if current year is not valid
                        }
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1); // Month
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH); // Day
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY); // Hour
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE); // Minute
                        final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND); // Second
                        final_buf[buf_inc++] = GPRS_PKT_DISBRD_DTC; // Message Type

                        // Length of CAN data (placeholders for now)
                        LEN1_BYTE = (byte) buf_inc++;
                        LEN2_BYTE = (byte) buf_inc++;

                        // Reserved 8 bytes
                        for (i = 0; i < 8; i++) {
                            final_buf[buf_inc++] = 0; // Fill with zeros
                        }

                        // Set packet identifier based on device DTC
                        buf_inc = 24; // Start at offset 24
                        final_buf[buf_inc++] = GPS_PKT_ID;
                        pkt_len_inc = 0; // Reset packet length increment

                        // Switch based on device DTC type
                        switch (dev_dtc) {
                            case 0: {
                                final_buf[buf_inc++] = (byte) (gps_lost_comm_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_GPS_LOST_COMM); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                            case 1: {
                                final_buf[buf_inc++] = (byte) (gps_invalid_data_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_GPS_INVALID_DATA); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                            case 2: {
                                final_buf[buf_inc++] = (byte) (gps_antenna_error_cnt & 0xFF); // Number of PIDs (default 1)
                                pkt_len_inc++;
                                buf = convertToByteArray((short) DTC_GPS_ANTENNA_ERROR); // Convert DTC code to byte array
                                for (i = 0; i < buf.length; i++) {
                                    final_buf[buf_inc++] = buf[i]; // Copy DTC data
                                    pkt_len_inc++;
                                }
                                final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                                pkt_len_inc++;
                                break;
                            }
                        }

                        // Set packet length in final buffer
                        buf = convertToByteArray(pkt_len_inc);
                        final_buf[LEN1_BYTE] = buf[0];
                        final_buf[LEN2_BYTE] = buf[1];

                        // End of the packet
                        final_buf[buf_inc++] = END_PKT;

                        // Construct string representation of the packet
                        if (sb.length() > 0) {
                            sb = sb.delete(0, sb.length());
                        }
                        for (i = 0; i < buf_inc; i++) {
                            sb.append(final_buf[i]); // Append each byte to StringBuilder
                            sb.append(clsDefines.FIELD_SEPARATOR); // Add field separator
                        }
                        sb = sb.deleteCharAt(sb.length() - 1); // Remove last field separator

                        // Add formatted packet to linked list
                        add_first_bus_linked_list(sb.toString());

                    } catch (Exception ex) {
                        // Exception handling
                    } finally {
                        // Cleanup resources
                    }
                }
            }

            // Nullify references to release memory
            final_buf = null;
            OBUId = null;
            cal = null;
            buf = null;
            sb = null;
        }
    }

    public void disbrd_dtc_pkt() {
        // Check if the company name matches certain predefined constants
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

            byte buf[] = null; // Buffer for byte arrays
            int dev_dtc = 0; // Device DTC counter
            byte[] final_buf = new byte[500]; // Final data packet buffer
            int buf_inc = 0; // Buffer increment counter
            int i = 0; // General purpose loop variable
            Calendar cal = Calendar.getInstance(); // Calendar instance for timestamp
            String OBUId; // OBU ID string
            byte LEN1_BYTE = 14; // Length byte 1
            byte LEN2_BYTE = 15; // Length byte 2
            short pkt_len_inc = 0; // Packet length increment
            StringBuilder sb = new StringBuilder(); // String builder for constructing final output
            byte disbrd_id; // Display board ID

            // Loop through display board IDs (0 to 3)
            for (disbrd_id = 0; disbrd_id < 4; disbrd_id++) {
                // Loop through device DTCs (1200 to 1202)
                for (dev_dtc = 1200; dev_dtc < 1203; dev_dtc++) {
                    // Check if display board name matches SUMITH_DISBRD
                    if (getDisBrdName() == SUMITH_DISBRD) {
                        try {
                            OBUId = getOBUID(); // Get OBU ID from a method
                            buf_inc = 0; // Reset buffer increment counter

                            // Start of the packet
                            final_buf[buf_inc++] = START_PKT;

                            // Copy OBU ID into final buffer
                            buf = OBUId.getBytes();
                            for (i = 0; i < buf.length && i < 6; i++) {
                                final_buf[buf_inc++] = buf[i];
                            }
                            for (; i < 6; i++) {
                                final_buf[buf_inc++] = ASCII_ZERO; // Pad with zeros if OBU ID is shorter
                            }

                            // YYMMDDHHMMSS timestamp
                            if (cal.get(Calendar.YEAR) > 2000) {
                                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                            } else {
                                final_buf[buf_inc++] = (byte) (2019 - 2000); // Default year if current year is not valid
                            }
                            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1); // Month
                            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH); // Day
                            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY); // Hour
                            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE); // Minute
                            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND); // Second
                            final_buf[buf_inc++] = GPRS_PKT_DISBRD_DTC; // Message Type

                            // Length of CAN data (placeholders for now)
                            LEN1_BYTE = (byte) buf_inc++;
                            LEN2_BYTE = (byte) buf_inc++;
                            for (i = 0; i < 8; i++) {
                                final_buf[buf_inc++] = 0; // Fill with zeros
                            }

                            buf_inc = 24; // Start at offset 24 in final buffer

                            // Switch based on device DTC
                            switch (dev_dtc) {
                                case DTC_HIGH_VOLTAGE: {
                                    if (disbrd_id == 0) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.front_high_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 1) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.side_high_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 2) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.rear_high_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 3) {
                                        final_buf[buf_inc++] = (byte) (disbrd_id + 1); // Display board ID (for internal board)
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.int_high_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    }
                                    break;
                                }
                                case DTC_LOW_VOLTAGE: {
                                    if (disbrd_id == 0) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.front_low_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 1) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.side_low_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 2) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.rear_low_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 3) {
                                        final_buf[buf_inc++] = (byte) (disbrd_id + 1); // Display board ID (for internal board)
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.int_low_volt_cnt; // Data value
                                        pkt_len_inc++;
                                    }
                                    break;
                                }
                                case DTC_OVER_HEAT_VOLTAGE: {
                                    if (disbrd_id == 0) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.front_over_heat_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 1) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.side_over_heat_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 2) {
                                        final_buf[buf_inc++] = disbrd_id; // Display board ID
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.rear_over_heat_cnt; // Data value
                                        pkt_len_inc++;
                                    } else if (disbrd_id == 3) {
                                        final_buf[buf_inc++] = (byte) (disbrd_id + 1); // Display board ID (for internal board)
                                        pkt_len_inc++;
                                        final_buf[buf_inc++] = (byte) PanDisplayBoardDiag.int_over_heat_cnt; // Data value
                                        pkt_len_inc++;
                                    }
                                    break;
                                }
                            }

                            // Convert device DTC to byte array and add to final buffer
                            buf = convertToByteArray((short) dev_dtc);
                            for (i = 0; i < buf.length; i++) {
                                final_buf[buf_inc++] = buf[i];
                                pkt_len_inc++;
                            }
                            final_buf[buf_inc++] = 0; // 0 means inactive, 1 means active
                            pkt_len_inc++;

                            // Convert packet length increment to byte array and set in final buffer
                            buf = convertToByteArray(pkt_len_inc);
                            final_buf[LEN1_BYTE] = buf[0];
                            final_buf[LEN2_BYTE] = buf[1];

                            // End of the packet
                            final_buf[buf_inc++] = END_PKT;

                            // Construct string representation of the packet
                            if (sb.length() > 0) {
                                sb = sb.delete(0, sb.length());
                            }
                            for (i = 0; i < buf_inc; i++) {
                                sb.append(final_buf[i]);
                                sb.append(clsDefines.FIELD_SEPARATOR);
                            }
                            sb = sb.deleteCharAt(sb.length() - 1); // Remove last field separator

                            // Add formatted packet to linked list
                            add_first_bus_linked_list(sb.toString());

                        } catch (Exception ex) {
                            // Exception handling
                        } finally {
                            // Cleanup resources
                        }
                    }
                }
            }

            // Nullify references to release memory
            final_buf = null;
            //OBU
            // Nullify references to release memory
            OBUId = null;
            cal = null;
            sb = null;
        }
    }

    public void video_disconnect_pkt(byte camera_no, byte status) {
        //1-->Cam1
        //2--> cam2
        //3--> cam3
        //4-->cam4
        //1-->conn
        //0-->disconnect
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;
            try {
                OBUId = getOBUID();
                buf_inc = 0;
                //Start of the packet
                final_buf[buf_inc++] = START_PKT;

                //OBU ID of 12 characters
                // OBUId="000001";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                //YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (01);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = clsPacketTypes.GPRS_PKT_VIDEO_STATUS; //Message Type

                //length of nmea data
                len = 2;

                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                //reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                //panic data
                final_buf[buf_inc++] = camera_no;
                final_buf[buf_inc++] = status;  //1 -->connected  0-->disconnect

                //end of the packet
                final_buf[buf_inc++] = END_PKT;

                if (sb.length() > 0) {
                    sb = sb.delete(0, sb.length());
                }
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());

                // objReadFiles.write_log_stored_send_gprs_data("panic messages " + sb.toString());// sb.toString());
            } catch (Exception ex) {

            } finally {
                final_buf = null;
                buf = null;
                sb = null;
                cal = null;
                OBUId = null;
            }
        }
    }

    public void displayboard_disconnect_pkt(byte displayboard_no, byte status) {
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len = 0;
            byte buf[];
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String OBUId;
            try {
                OBUId = getOBUID();
                buf_inc = 0;
                //Start of the packet
                final_buf[buf_inc++] = START_PKT;

                //OBU ID of 12 characters
                // OBUId="000001";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                //YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (01);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = clsPacketTypes.GPRS_PKT_DISPLAYBOARD_DISCONNECT; //Message Type

                //length of nmea data
                len = 2;

                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                //reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                //panic data
                final_buf[buf_inc++] = displayboard_no;
                final_buf[buf_inc++] = status;
                //end of the packet
                final_buf[buf_inc++] = END_PKT;

                if (sb.length() > 0) {
                    sb = sb.delete(0, sb.length());
                }
                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());

                // objReadFiles.write_log_stored_send_gprs_data("panic messages " + sb.toString());// sb.toString());
            } catch (Exception ex) {

            } finally {
                final_buf = null;
                buf = null;
                sb = null;
                cal = null;
                OBUId = null;
            }
        }
    }

    public void health_construct_pkt() {

        //Health Packet  36,48,48,48,48,48,57,19,6,19,23,32,57,100,0,42,0,0,0,0,0,0,0,0,1,1,0,1,1,19,6,19,23,32,42,19,6,19,23,29,46,32,32,49,74,66,77,1,5,30,0,5,30,0,5,30,0,0,1,1,1,0,1,0,1,1,35
        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte k = 0;
        byte buf[];
        long date_data;
        String data;
        List lst = new ArrayList();
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String OBUId;
        try {
            OBUId = getOBUID();
            buf_inc = 0;
            //Start of the packet
            final_buf[buf_inc++] = START_PKT;

            //OBU ID of 12 characters
            // OBUId="000001";
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (01);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            final_buf[buf_inc++] = clsPacketTypes.CS_GPRS_HEALTH_RES_PKT; //Message Type

            //length of nmea data
            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                len = 42 + 24;
            } else {
                len = 42;
            }

            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            //reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }
            clsHealthPacketStructure obj = new clsHealthPacketStructure();
            lst = obj.getDataIp4();

            //panic data
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //ignition
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //Mains
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //Battery
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //GPS
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //GPRS
            date_data = Long.parseLong(lst.get(k++).toString()); //Last Packet Send from OBU Date and Time, Format = YYMMDDHHMMSS

            cal.setTimeInMillis(date_data);

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (01);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            date_data = Long.parseLong(lst.get(k++).toString()); //Last Packet received  to OBU Date and Time, Format = YYMMDDHHMMSS

            cal.setTimeInMillis(date_data);

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (01);
            }
            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            data = lst.get(k++).toString(); //route ID
            buf = data.getBytes();
            if (buf.length <= 6) {
                len = (short) (6 - buf.length);
            }

            for (i = 0; i < len; i++) {
                final_buf[buf_inc++] = SPACE_CHAR;
            }

            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //Trip No

            date_data = Long.parseLong(lst.get(k++).toString()); //Last over Speed Time, Format = HHMMSS

            cal.setTimeInMillis(date_data);

            // HHMMSS 
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            date_data = Long.parseLong(lst.get(k++).toString()); //Last Harsh Acceleration Time  , Format = HHMMSS

            cal.setTimeInMillis(date_data);

            // HHMMSS 
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            date_data = Long.parseLong(lst.get(k++).toString()); //Last Harsh Brake Time  , Format = HHMMSS

            cal.setTimeInMillis(date_data);

            // HHMMSS 
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //cam1
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //cam2
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //cam3
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //cam4
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //can

            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //fd
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //sd
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //rd
            final_buf[buf_inc++] = Byte.parseByte(lst.get(k++).toString()); //id

            if (clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == COMP_JBM
                    || clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING == clsDefines.COMP_SUMITH) {
                if (getGpsState() == GPS_FIXED) {
                    OBUId = String.valueOf(getCurLatitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }

                    //Longitude
                    OBUId = String.valueOf(getCurLongitude());
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                } else {

                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                    OBUId = "00.000000000";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 12; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 12; i++) {
                        final_buf[buf_inc++] = ASCII_ZERO;
                    }
                }

            }

            //end of the packet
            final_buf[buf_inc++] = END_PKT;

            if (sb.length() > 0) {
                sb = sb.delete(0, sb.length());
            }
            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            objReadFiles.write_special_pkts_data("Health Packet  " + sb.toString());// sb.toString());
        } catch (Exception ex) {

        } finally {
            final_buf = null;
            buf = null;
            data = null;
            lst = null;
            sb = null;
            cal = null;
            OBUId = null;
        }

    }

    public void can_door_open(byte door_no) {

        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        String OBUId;

        try {
            OBUId = getOBUID();
            buf_inc = 0;
            //Start of the packet
            final_buf[buf_inc++] = START_PKT;

            //OBU ID of 12 characters
            // OBUId="000001";
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }

            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
            final_buf[buf_inc++] = GPRS_PKT_CAN_DOOR_OPEN; //Message Type

            len = 26; //lat & longi each 12 characters
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            //reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            //door_no
            final_buf[buf_inc++] = door_no;

            if (getGpsState() == GPS_FIXED) {
                //speed
                final_buf[buf_inc++] = (byte) gps_speed;
                //latitude
                OBUId = String.valueOf(getCurLatitude());
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                //Longitude
                OBUId = String.valueOf(getCurLongitude());
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

            } else {
                //speed
                final_buf[buf_inc++] = (byte) 0;
                OBUId = "00.000000000";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }
                OBUId = "00.000000000";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }
            }
            //end of the packet
            final_buf[buf_inc++] = END_PKT;

            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            objReadFiles.write_special_pkts_data(door_no + " Door Status open  " + sb.toString());// sb.toString());

            //   objReadFiles.write_log_stored_send_gprs_data("Ignition On " + sb.toString()); //sb.toString());
        } catch (Exception ex) {

        } finally {

            sb = null;
            final_buf = null;
            OBUId = null;
            buf = null;
            cal = null;
        }

    }

    public void can_door_close(byte door_no) {

        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        String OBUId;

        try {
            OBUId = getOBUID();
            buf_inc = 0;
            //Start of the packet
            final_buf[buf_inc++] = START_PKT;

            //OBU ID of 12 characters
            // OBUId="000001";
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }

            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
            final_buf[buf_inc++] = GPRS_PKT_CAN_DOOR_CLOSE; //Message Type

            len = 26; //lat & longi each 12 characters
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            //reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            //door no
            final_buf[buf_inc++] = door_no;
            if (getGpsState() == GPS_FIXED) {
                //speed
                final_buf[buf_inc++] = (byte) gps_speed;
                //latitude
                OBUId = String.valueOf(getCurLatitude());
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                //latitude
                OBUId = String.valueOf(getCurLongitude());
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

            } else {
                //speed
                final_buf[buf_inc++] = (byte) 0;
                OBUId = "00.000000000";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }
                OBUId = "00.000000000";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 12; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 12; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }
            }
            //end of the packet
            final_buf[buf_inc++] = END_PKT;

            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            objReadFiles.write_special_pkts_data(door_no + " Door Status Close  " + sb.toString());// sb.toString());

            //   objReadFiles.write_log_stored_send_gprs_data("Ignition On " + sb.toString()); //sb.toString());
        } catch (Exception ex) {

        } finally {

            sb = null;
            final_buf = null;
            OBUId = null;
            buf = null;
            cal = null;
        }
    }

    public synchronized void route_deviate_pkt() {
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH) {
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len;
            byte buf[];
            String sch_id = get_driver_id();
            //  long tim_sec = getCurSec();
            String route_no = getCurRouteNo();
            byte trip_no = (byte) (getCurTripNo() + 1);
            //String dat = getCurDate();
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();

            String OBUId;
            try {
                OBUId = getOBUID();
                buf_inc = 0;
                //Start of the packet
                final_buf[buf_inc++] = START_PKT;

                //OBU ID of 12 characters
                // OBUId="000001";
                buf = OBUId.getBytes();
                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                for (; i < 6; i++) {
                    final_buf[buf_inc++] = ASCII_ZERO;
                }

                //YYMMDDHHMMSS
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (1);
                }
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                final_buf[buf_inc++] = GPRS_PKT_ROUTE_DEVIATION; //Message Type

                //length of nmea data
                len = 31;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                //reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }

                //TRIP start data
                //24 to 29 bytes current route id 6 byte
                buf_inc = 24;
                buf = route_no.getBytes();
                if (buf.length <= 6) {
                    len = (short) (6 - buf.length);
                }

                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length && i < 6; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                //Start Date Time, Format = YYMMDDHHMMSS 30 to 35 bytes
                //cal.setTimeInMillis(tim_sec); //*1000);
                buf_inc = 30;
                if (cal.get(Calendar.YEAR) > 2000) {
                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                } else {
                    final_buf[buf_inc++] = (byte) (18);
                }

                final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);

                //Expected Completion Time, Format = HHMM 36-37
                buf_inc = 36;
                final_buf[buf_inc++] = clsSharedVariables.getCurStopNo();

                //Duty Id, Format = ‘X1Y2’ or ‘ Y2’ (Maximum 16 character ) 38-53 SCHEDuLE id
                buf_inc = 37;

                buf = sch_id.getBytes();

                if (buf.length <= 16) {
                    len = (short) (16 - buf.length);
                }
                for (i = 0; i < len; i++) {
                    final_buf[buf_inc++] = SPACE_CHAR;
                }

                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }
                //trip id 54

                buf_inc = 53;
                final_buf[buf_inc++] = trip_no;

                //end of the packet
                final_buf[buf_inc++] = END_PKT;

                for (i = 0; i < buf_inc; i++) {
                    sb.append(final_buf[i]);
                    sb.append(clsDefines.FIELD_SEPARATOR);
                }
                sb = sb.deleteCharAt(sb.length() - 1);
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {

            } finally {

                sb = null;
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }

    }

    public void ack_packet_obu_cs(short packet_type, byte res) {

        byte[] final_buf = new byte[200];
        int buf_inc = 0;
        int i = 0;
        short len = 0;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();

        String OBUId;

        try {
            OBUId = getOBUID();
            buf_inc = 0;
            //Start of the packet
            final_buf[buf_inc++] = START_PKT;

            //OBU ID of 12 characters
            // OBUId="000001";
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }

            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
            final_buf[buf_inc++] = (byte) OBU_CS_GPRS_PKT_COMMON_ACK_RES_PKT; //Message Type

            len = 2; //lat & longi each 12 characters
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            //reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            //Response success/fail
            final_buf[buf_inc++] = res;

            //Packet Type
            final_buf[buf_inc++] = (byte) packet_type;

            //end of the packet
            final_buf[buf_inc++] = END_PKT;

            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

            ////System.out.println(" \n Ack pkt File upload sent : " + OBUId);
            if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                OBUId = convertToHexString(final_buf, buf_inc);
                objReadFiles.write_rx_data_from_server("file upload response to server 254 : " + OBUId);
            };
            //   objReadFiles.write_log_stored_send_gprs_data("Ignition On " + sb.toString()); //sb.toString());
        } catch (Exception ex) {

        } finally {

            sb = null;
            final_buf = null;
            OBUId = null;
            buf = null;
            cal = null;
        }

    }

    public void obu_cs_file_upload_response_pkt(String file_name, byte res, byte cam_no, byte type) {

        byte[] final_buf = new byte[200];
        int buf_inc;
        int i;
        short len;
        byte buf[];
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String OBUId;

        try {
            OBUId = getOBUID();
            buf_inc = 0;
            //Start of the packet
            final_buf[buf_inc++] = START_PKT;

            //OBU ID of 12 characters
            // OBUId="000001";
            buf = OBUId.getBytes();
            for (i = 0; i < buf.length && i < 6; i++) {
                final_buf[buf_inc++] = buf[i];
            }
            for (; i < 6; i++) {
                final_buf[buf_inc++] = ASCII_ZERO;
            }

            //YYMMDDHHMMSS
            if (cal.get(Calendar.YEAR) > 2000) {
                final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
            } else {
                final_buf[buf_inc++] = (byte) (1);
            }

            final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
            final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
            final_buf[buf_inc++] = (byte) OBU_CS_GPRS_FILEUPLOAD_RES_PKT; //Message Type

            //len of pkt contents
            len = (short) (file_name.length() + 4);
            buf = convertToByteArray(len);
            for (i = 0; i < buf.length; i++) {
                final_buf[buf_inc++] = buf[i];
            }

            //reserved 8 bytes
            for (i = 0; i < 8; i++) {
                final_buf[buf_inc++] = 0;
            }

            //Response success/fail
            final_buf[buf_inc++] = res;

            //camera no
            final_buf[buf_inc++] = cam_no;

            //snapshot/video type
            final_buf[buf_inc++] = type;

            //   length of file name
            final_buf[buf_inc++] = (byte) file_name.length();

            //File name
            for (i = 0; i < file_name.length(); i++) {
                final_buf[buf_inc++] = (byte) file_name.charAt(i);
            }

            //end of the packet
            final_buf[buf_inc++] = END_PKT;

            for (i = 0; i < buf_inc; i++) {
                sb.append(final_buf[i]);
                sb.append(clsDefines.FIELD_SEPARATOR);
            }

            sb = sb.deleteCharAt(sb.length() - 1);
            add_first_bus_linked_list(sb.toString());

        } catch (Exception ex) {
        } finally {
            sb = null;
            final_buf = null;
            OBUId = null;
            buf = null;
            cal = null;
        }

    }

    public void obu_cs_AIS140_pos_pkt() {

        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf_date = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat sdf_time = new SimpleDateFormat("HHmmss");
        sdf_date.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdf_time.setTimeZone(TimeZone.getTimeZone("GMT"));
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        DecimalFormat df1 = new DecimalFormat("000000.0");
        DecimalFormat df_latlong = new DecimalFormat("00.000000");
        DecimalFormat dfspeed = new DecimalFormat("00.00");
        int frame_seq_no = 0;
        try {
            // start flag
            sb.append(clsDefines.START_FLAG);
            sb.append(clsDefines.HEADER_NORMAL);

            sb.append(clsDefines.COMMA);

            // ATL ID
            sb.append(clsDefines.VENDOR_ID);
            sb.append(clsDefines.COMMA);

            // Firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(clsDefines.COMMA);

            // packet type
            sb.append(clsDefines.PKT_TYPE_NORMAL);
            sb.append(clsDefines.COMMA);

            // packet status 
            sb.append(clsDefines.LIVE_PACKET);
            sb.append(clsDefines.COMMA);

            // IMEI 
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(clsDefines.COMMA);

            // Vehicle registration number
            sb.append(clsSharedVariables.getVechicleRegNo());
            sb.append(clsDefines.COMMA);

            // Fence ID , what is this ?
            // within the fence
            sb.append('1');
            sb.append(clsDefines.COMMA);

            // GPS Fix 
            if (getGpsState() == 1) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            sb.append(clsDefines.COMMA);

            //Date 
            sb.append(sdf_date.format(cal.getTime()));
            sb.append(clsDefines.COMMA);
            // Time
            sb.append(sdf_time.format(cal.getTime()));
            sb.append(clsDefines.COMMA);

            //Latitude
            sb.append(df_latlong.format(getCurLatitude()));
            sb.append(clsDefines.COMMA);

            // latitude_dir
            sb.append(getCurLatitudeDir());
            sb.append(clsDefines.COMMA);
            // longitude
            sb.append(df_latlong.format(getCurLongitude()));
            sb.append(clsDefines.COMMA);

            // longitude_dir
            sb.append(getCurLongitudeDir());
            sb.append(clsDefines.COMMA);

            // speed
            sb.append(dfspeed.format(getCurSpeed()));
            sb.append(clsDefines.COMMA);

            // Heading 
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(clsDefines.COMMA);

            // No of satellites
            sb.append(clsSharedVariables.getCurNoSatelites());
            sb.append(clsDefines.COMMA);

            // Altitude
            sb.append(clsSharedVariables.getCurAltitude());
            sb.append(clsDefines.COMMA);

            //PDOP
            sb.append(clsSharedVariables.getPdop());
            sb.append(clsDefines.COMMA);

            // Odo meter rading 
            sb.append(df1.format(clsSharedVariables.getTravelledDis()));
            // sb.append((clsSharedVariables.getTravelledDis()));
            sb.append(clsDefines.COMMA);

            //Network Operator
            sb.append((clsSharedVariables.getNetworkOperatorName()));
            sb.append(clsDefines.COMMA);

            // Ignition Status 
            sb.append(objHealth.get_ignition_status());
            sb.append(clsDefines.COMMA);

            // Main status 
            sb.append(objHealth.get_mains_status());
            sb.append(clsDefines.COMMA);

            //Emergency status 
            if (clsSharedVariables.getDigInp1Name() != DIG_INPUT_NONE) {
                sb.append(clsSharedVariables.getDigInput1Status());
            } else {
                sb.append('0');
            }
            sb.append(clsDefines.COMMA);

            //Tamper
            sb.append('C'); //cover closed 
            sb.append(clsDefines.COMMA);

            // Internal battery voltage
            sb.append(clsSharedVariables.getAnaBatVoltage()); //cover closed 
            sb.append(clsDefines.COMMA);

            //GSM signal strenghth
            sb.append(clsSharedVariables.getGsmSignalStrength()); //cover closed 
            sb.append(clsDefines.COMMA);

            // MCC
            sb.append(clsSharedVariables.getMcc());
            sb.append(clsDefines.COMMA);
            //MNC
            sb.append(clsSharedVariables.getMnc());
            sb.append(clsDefines.COMMA);

            //LAC
            sb.append(clsSharedVariables.getLac());
            sb.append(clsDefines.COMMA);

            //CellID
            sb.append(clsSharedVariables.getCellId());
            sb.append(clsDefines.COMMA);

            // digital Input 
            sb.append(clsSharedVariables.getDigInput4Status());
            sb.append(clsSharedVariables.getDigInput3Status());
            sb.append(clsSharedVariables.getDigInput2Status());
            sb.append(clsSharedVariables.getDigInput1Status());
            sb.append(clsDefines.COMMA);

            //Digital Output
            sb.append(clsSharedVariables.getDigOut1Value());
            sb.append(clsSharedVariables.getDigOut2Value());

            sb.append(clsDefines.COMMA);

            // Analoue Input 1
            sb.append(clsSharedVariables.getAnaAdc0());
            sb.append(clsDefines.COMMA);

            //Analoue input 2
            sb.append(clsSharedVariables.getAnaAdc1());
            sb.append(clsDefines.COMMA);

            // Analoue Input 3
            sb.append(clsSharedVariables.getAnaAdc2());
            sb.append(clsDefines.COMMA);

            // Analoue Input 4
            sb.append(clsSharedVariables.getAnaAdc3());
            sb.append(clsDefines.COMMA);
            sb.append(0);

            // end character 
            sb.append(clsDefines.END_FLAG);

            clsGpsLinkedList objLst = new clsGpsLinkedList();
            objLst.addLastDataIp5(sb.toString().getBytes());

            objLst = null;
        } catch (Exception ex) {
            //////System.out.println("pkt error " + ex.getMessage() + sb.toString());
        } finally {
            sb = null;
            cal = null;
            sdf_date = null;
            sdf_time = null;
            objHealth = null;
            df1 = null;
        }

    }

    public void obu_cs_event_pkt_construction(String pkt_type) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        int event_frame_seq_no = 0;
        SimpleDateFormat sdf_date = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat sdf_time = new SimpleDateFormat("HHmmss");

        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        DecimalFormat df1 = new DecimalFormat("000000.0");
        DecimalFormat df_latlong = new DecimalFormat("00.000000");
        DecimalFormat dfspeed = new DecimalFormat("00.00");
        try {
            sdf_date.setTimeZone(TimeZone.getTimeZone("GMT"));
            sdf_time.setTimeZone(TimeZone.getTimeZone("GMT"));
            // start flag
            sb.append(clsDefines.START_FLAG);
            sb.append(clsDefines.HEADER_NORMAL);

            sb.append(clsDefines.COMMA);

            // ATL ID
            sb.append(clsDefines.VENDOR_ID);
            sb.append(clsDefines.COMMA);

            // Firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(clsDefines.COMMA);

            sb.append(pkt_type);
            sb.append(clsDefines.COMMA);

            // packet status 
            sb.append('L');
            sb.append(clsDefines.COMMA);

            // IMEI 
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(clsDefines.COMMA);

            // Vehicle registration number
            sb.append(clsSharedVariables.getVechicleRegNo());
            sb.append(clsDefines.COMMA);

            // Fence ID , what is this ?
            // within the fence
            sb.append('1');
            sb.append(clsDefines.COMMA);

            // GPS Fix 
            if (getGpsState() == 1) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            sb.append(clsDefines.COMMA);

            //Date 
            sb.append(sdf_date.format(cal.getTime()));
            sb.append(clsDefines.COMMA);
            // Time
            sb.append(sdf_time.format(cal.getTime()));
            sb.append(clsDefines.COMMA);

            //Latitude
            sb.append(df_latlong.format(getCurLatitude()));
            sb.append(clsDefines.COMMA);

            // latitude_dir
            sb.append(getCurLatitudeDir());
            sb.append(clsDefines.COMMA);
            // longitude
            sb.append(df_latlong.format(getCurLongitude()));
            sb.append(clsDefines.COMMA);

            // longitude_dir
            sb.append(getCurLongitudeDir());
            sb.append(clsDefines.COMMA);

            // speed
            sb.append(dfspeed.format(getCurSpeed()));
            sb.append(clsDefines.COMMA);

            // Heading 
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(clsDefines.COMMA);

            // No of satellites
            sb.append(clsSharedVariables.getCurNoSatelites());
            sb.append(clsDefines.COMMA);

            // Altitude
            sb.append(clsSharedVariables.getCurAltitude());
            sb.append(clsDefines.COMMA);

            //PDOP
            sb.append(clsSharedVariables.getPdop());
            sb.append(clsDefines.COMMA);

            // Odo meter rading 
            sb.append(df1.format(clsSharedVariables.getTravelledDis()));
            // sb.append((clsSharedVariables.getTravelledDis()));
            sb.append(clsDefines.COMMA);

            //Network Operator
            sb.append((clsSharedVariables.getNetworkOperatorName()));
            sb.append(clsDefines.COMMA);

            // Ignition Status 
            sb.append(objHealth.get_ignition_status());
            sb.append(clsDefines.COMMA);

            // Main status 
            sb.append(objHealth.get_mains_status());
            sb.append(clsDefines.COMMA);

            //Emergency status 
            if (clsSharedVariables.getDigInp1Name() != DIG_INPUT_NONE) {
                sb.append(clsSharedVariables.getDigInput1Status());
            } else {
                sb.append('0');
            }
            sb.append(clsDefines.COMMA);

            //Tamper
            sb.append('C'); //cover closed 
            sb.append(clsDefines.COMMA);

            // Internal battery voltage
            sb.append(clsSharedVariables.getAnaBatVoltage()); //cover closed 
            sb.append(clsDefines.COMMA);

            //GSM signal strenghth
            sb.append(clsSharedVariables.getGsmSignalStrength()); //cover closed 
            sb.append(clsDefines.COMMA);

            // MCC
            sb.append(clsSharedVariables.getMcc());
            sb.append(clsDefines.COMMA);
            //MNC
            sb.append(clsSharedVariables.getMnc());
            sb.append(clsDefines.COMMA);

            //LAC
            sb.append(clsSharedVariables.getLac());
            sb.append(clsDefines.COMMA);

            //CellID
            sb.append(clsSharedVariables.getCellId());
            sb.append(clsDefines.COMMA);

            // digital Input 
            sb.append(clsSharedVariables.getDigInput4Status());
            sb.append(clsSharedVariables.getDigInput3Status());
            sb.append(clsSharedVariables.getDigInput2Status());
            sb.append(clsSharedVariables.getDigInput1Status());
            sb.append(clsDefines.COMMA);

            //Digital Output
            sb.append(clsSharedVariables.getDigOut1Value());
            sb.append(clsSharedVariables.getDigOut2Value());

            sb.append(clsDefines.COMMA);

            // Analoue Input 1
            sb.append(clsSharedVariables.getAnaAdc0());
            sb.append(clsDefines.COMMA);

            //Analoue input 2
            sb.append(clsSharedVariables.getAnaAdc1());
            sb.append(clsDefines.COMMA);

            // Analoue Input 3
            sb.append(clsSharedVariables.getAnaAdc2());
            sb.append(clsDefines.COMMA);

            // Analoue Input 4
            sb.append(clsSharedVariables.getAnaAdc3());
            sb.append(clsDefines.COMMA);

            sb.append(0);

            // end character 
            sb.append(clsDefines.END_FLAG);
            clsLinkedList obj = new clsLinkedList();
            obj.addLastData5(sb.toString());

            obj = null;
        } catch (Exception ex) {
            // ////System.out.println("Event Pkt Exception  :" + ex.getMessage() + sb.toString());
        } finally {
            sb = null;
            cal = null;
            sdf_date = null;
            sdf_time = null;
            objHealth = null;
            df1 = null;
        }
    }

}
