package obuits;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import static obuits.PanDisplayBoardDiag.DTC_GPS_ANTENNA_ERROR;
import static obuits.PanDisplayBoardDiag.DTC_GPS_INVALID_DATA;
import static obuits.PanDisplayBoardDiag.DTC_GPS_LOST_COMM;
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
import static obuits.PanDisplayBoardDiag.PID_BOOT_LOADER_SW_REV;
import static obuits.PanDisplayBoardDiag.PID_COMPILATION_FW_DATE_TIME;
import static obuits.PanDisplayBoardDiag.PID_CPU_PART_NO;
import static obuits.PanDisplayBoardDiag.PID_CPU_QUALIFICATION;
import static obuits.PanDisplayBoardDiag.PID_CPU_TEMP_RANGE;
import static obuits.PanDisplayBoardDiag.PID_FLASH_UPATE_STATUS;
import static obuits.PanDisplayBoardDiag.PID_FONT_LIB_REV;
import static obuits.PanDisplayBoardDiag.PID_MAX_INPUT_VOLT;
import static obuits.PanDisplayBoardDiag.PID_TEST_DATE_TIME;
import static obuits.PanDisplayBoardDiag.DTC_HIGH_VOLTAGE;
import static obuits.PanDisplayBoardDiag.PID_ARTICLE_NO_SIGN_LEVEL;
import static obuits.PanDisplayBoardDiag.PID_BOARD_TEMP_SENSOR;
import static obuits.PanDisplayBoardDiag.PID_BUS_BUILDER_NO;
import static obuits.PanDisplayBoardDiag.PID_END_CUSTOMER;
import static obuits.PanDisplayBoardDiag.PID_HW_REV;
import static obuits.PanDisplayBoardDiag.PID_INTERNAL_CPU_TEMP;
import static obuits.PanDisplayBoardDiag.PID_LANGUAGE;
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
import static obuits.PanDisplayBoardDiag.PID_VEHICLE_TYPE;
import static obuits.PanDisplayBoardDiag.objPids;
import static obuits.clsDefines.ASCII_ZERO;
import static obuits.clsDefines.BOARD_STATUS_FAILURE;
import static obuits.clsDefines.END_PKT;
import static obuits.clsDefines.GPS_FIXED;
import static obuits.clsDefines.GPS_OFF;
import static obuits.clsDefines.GPS_PKT_ID;
import static obuits.clsDefines.OBU_PKT_ID;
import static obuits.clsDefines.START_PKT;
import static obuits.clsDefines.SW_FIRMWARE_VERSION;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.USB_PKT_ID;
import static obuits.clsDefines.main_route_path;
import static obuits.clsPacketTypes.NORMAL_PKT;
import static obuits.clsSharedVariables.boot_ldr_version;
import static obuits.clsSharedVariables.compilation_fw_datetime;
import static obuits.clsSharedVariables.cpu_part_no;
import static obuits.clsSharedVariables.cpu_qualification;
import static obuits.clsSharedVariables.cpu_temp_range;
import static obuits.clsSharedVariables.font_lib_version;
import static obuits.clsSharedVariables.getCanBusType;
import static obuits.clsSharedVariables.getCurLatitude;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getCurRouteNo;
import static obuits.clsSharedVariables.getCurSpeed;
import static obuits.clsSharedVariables.getDisBrdName;
import static obuits.clsSharedVariables.getVechicleRegNo;
import static obuits.clsSharedVariables.getGpsState;
import static obuits.clsSharedVariables.getOBUID;
import static obuits.clsSharedVariables.getVechicleRegNo;
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
import static obuits.gpsDriving.convertToByteArray;

public class cls16833Protocols {

    static final char SEPARATOR_COMMA = ',';
    SimpleDateFormat sdfDate;
    SimpleDateFormat sdfTime;
    SimpleDateFormat sdfDateTime;
    SimpleDateFormat sdfDateandTime;
    SimpleDateFormat sdfDateTime_sms;
    SimpleDateFormat sdf_date = new SimpleDateFormat("dd/MM/yy");
    SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");
    final String pattern = "00.00000000";
    final DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public cls16833Protocols() {
        this.sdfDate = new SimpleDateFormat("ddMMyyyy");
        sdfTime = new SimpleDateFormat("HHmmss");
        sdfDateTime = new SimpleDateFormat("yyMMddHHmmss");
        sdfDateandTime = new SimpleDateFormat("ddMMyyyyHHmmss");
        sdfDateTime_sms = new SimpleDateFormat("ddMMyyyyHHmmss");
    }
    //adding data to linkedlist

    public synchronized void add_first_bus_linked_list(String str) {
        try {
            clsLinkedList obj = new clsLinkedList();
            obj.addLastData(str);
            // Ensure proper cleanup by explicitly calling the cleanup method or setting the reference to null
            obj = null; // Assuming clsLinkedList has a cleanup method to release resources
        } catch (Exception ex) {
            ex.printStackTrace(); // Print the exception stack trace for debugging purposes
            // Handle the exception according to your application's requirements
        }
    }
    //calculating checksum of the packet

    public String CalculateCRC32ChecksumForByteArray(byte[] bytes) {
        // Variable to store the hexadecimal checksum value
        String hexChecksum;

        // Create a new CRC32 checksum object
        Checksum checksum = new CRC32();

        // Update the checksum with the bytes from the input array
        checksum.update(bytes, 0, bytes.length);

        // Get the current checksum value
        long checksumValue = checksum.getValue();

        // Convert the checksum value to a hexadecimal string representation
        hexChecksum = Long.toHexString(checksumValue);

        // Release resources associated with the checksum object (not mandatory in Java, just for explicitness)
        checksum = null;

        // Return the hexadecimal checksum value as a string
        return hexChecksum;
    }
    //whenever server is connected this login packet will be sent

    public synchronized String serverConnectedLoginMsgPkt() {
        StringBuilder sb = new StringBuilder();
        String length;
        /*
         i) $Msg.Server.Login Start of message —
         ii) $DeviceName Vehicle number where the device was
         installed
         DL3CBM9821
         iii) $IMEI 15 Digit IMEI number 123456789012345
         iv) $Firmware Version of the firmware used in the
         hardware
         1.0.0
         v) $Protocol Version of the frame format protocol 1.0.1
         vi) $LastValidLocation Last location info saved at the device $1,220714,050656,28.758963,
         N,77.6277844,E,25
         */

        sb.append(clsSharedVariables.START_CHARACTER);
        // sb.append(clsPacketTypes16833.OBU_CS_LOGIN_PKT);
        //sb.append(SEPARATOR_COMMA);
        // sb.append(clsSharedVariables.getVendorId());
        //sb.append(SEPARATOR_COMMA);
        sb.append(getVechicleRegNo());
        sb.append(SEPARATOR_COMMA);
        sb.append(clsSharedVariables.getImeiNo());
        sb.append(SEPARATOR_COMMA);
        sb.append(clsDefines.SW_FIRMWARE_VERSION);
        sb.append(SEPARATOR_COMMA);
        sb.append(clsDefines.SW_PROTOCOL_VERSION);
        sb.append(SEPARATOR_COMMA);
        sb.append(clsSharedVariables.getLastValidLocation());
        sb.append(SEPARATOR_COMMA);
        length = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
        sb.append(String.valueOf(length));
        sb.append(clsSharedVariables.END_CHARACTER);

        return sb.toString();
    }
    //This is location packet on every configurable intervel this packet will be sent

    public synchronized void trackingMessagePkt(String pkt_type, char packet_status, boolean server, String val) {

        /*
         i) Start Character $
         ii) Header The header of the packet/identifier
         Vendor ID Vendor identification header
         iii) Firmware Version Version details of the firmware used in EX.1.0.0
         iv) Packet Type Specify the packet type—
         NR = Normal
         EA = Emergency alert
         TA = Tamper alert
         HP = Health packet
         IN = Ignition on
         IF = Ignition off
         BD = Battery disconnect
         BR = Battery reconnect
         BL = Battery low
         v) Packet Status L=Live or H= History
         vi) IMEI Identified of the sending unit. 15 digit standard unique IMEI No.
         vii) Vehicle Registration No. Mapped vehicle registration number
         viii) GPS Fix 1 = GPS fix or 0 = GPS invalid
         ix) Date Date value as per GPS date time (ddmmyy)
         x) Time Time value as per GPS date time in UTC format (hhmmss)
         xi) Latitude Latitude value in decimal degrees (with minimum 6 decimal places)
         xii) Latitude Direction Latitude Direction.
         For example, N=North; S= South
         xiii) Longitude Longitude value in decimal degrees (with minimum 6 decimal places)
         xiv) Longitude Direction Longitude direction.
         For example, E=East, W= West
         xv) Speed Speed in km/h
         xvi) Heading Course over ground in degrees
         xvii) No of Satellites Number of satellites available for fix
         xviii) Altitude Altitude of the device in ‘m’
         xix) PDOP Positional dilution of precision
         xx) HDOP Horizontal dilution of precision
         xxi) Network Operator Name Name of Network operator
         xxii) Ignition 1= Ign On; 0 = Ign Off
         Main Power Status 0 = Vehicle Battery Disconnected
         1= Vehicle Battery Reconnected
         xxiii) Main Input Voltage Indicator showing source voltage in Volts.
         xxiv) Internal Battery Voltage Indicator for Level of battery charge remaining
         xxv) Emergency Status 1= On ; 0 = Off
         xxvi) Tamper Alert C = Cover Closed, O = Cover Open
         xxvii) GSM Signal Strength Value Ranging from 0–31
         xxviii) MCC Mobile Country Code
         xxix) MNC Mobile Network Code
         xxx) LAC Location Area Code
         xxxi) Cell ID GSM Cell ID
         xxxii) NMR (neighbouring Cell ID) Neighbouring 4 cell ID along with their LAC and signal strength
         xxxiii) Digital Input Status 4 external digital input status (Status of Input 1 to Input 3 (0=Off, 1=On))
         xxxiv) Digital Output Status 2 external digital output status
         (0=Off; 1=On)
         xxxv) Frame Number Sequence Number of the messages (000001 to 999999)
         xxxvi) Checksum Insures no error in transmission (optional)
         xxxvii) End Character Indicated end of the frame
         */
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        String checksum;
        Date dat;
        Date time;
        Date dat_now = Calendar.getInstance().getTime();
        String formattedPktSeqNo = null;
        StringBuilder sb = new StringBuilder();
        String formattedDateTimeUTC = null;
        SimpleDateFormat sdfDateTimeUTC = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        sdfDateTimeUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        String[] split_time = null;

        try {
            if (clsSharedVariables.getCurDate() == null) {
                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
            }
            if (clsSharedVariables.getCurTime() == null) {
                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
            }
            dat = sdf_date.parse(clsSharedVariables.getCurDate());
            time = sdf_time.parse(clsSharedVariables.getCurTime());

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(dat);
            calendar.set(java.util.Calendar.HOUR_OF_DAY, time.getHours());
            calendar.set(java.util.Calendar.MINUTE, time.getMinutes());
            calendar.set(java.util.Calendar.SECOND, time.getSeconds());
            // Format and convert the combined date and time to UTC
            formattedDateTimeUTC = sdfDateTimeUTC.format(calendar.getTime());
            // Date dateTime = sdfDateTimeUTC.parse(formattedDateTimeUTC);

            split_time = formattedDateTimeUTC.split(" ");
            //start character
            sb.append(clsSharedVariables.START_CHARACTER);

            sb.append(clsPacketTypes16833.OBU_CS_LOCATION_PKT);  //header
            sb.append(SEPARATOR_COMMA);
            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);
            //packet type
            switch (pkt_type) {
                case clsPacketTypes.ALERT_OVER_AIR_PKT:
                    sb.append(clsPacketTypes.ALERT_OVER_AIR_PKT);
                    break;
                case clsPacketTypes.EMERGENCY_ALERT_ON_PKT:
                    sb.append(clsPacketTypes.EMERGENCY_ALERT_PKT);
                    break;
                case clsPacketTypes.EMERGENCY_ALERT_OFF_PKT:
                    sb.append(clsPacketTypes.EMERGENCY_ALERT_PKT);
                    break;
                case clsPacketTypes.BATTERY_PWR_REMOVED_PKT:
                    sb.append(clsPacketTypes.BATTERY_PWR_RECONNECT_PKT);
                    break;
                default:
                    sb.append(pkt_type);
                    break;
            }
            sb.append(SEPARATOR_COMMA);

            switch (pkt_type) {
                case NORMAL_PKT:
                    sb.append("1");
                    break;
                case clsPacketTypes.BATTERY_PWR_DISCONNECT_PKT:
                    sb.append("3");
                    break;
                case clsPacketTypes.INT_BATTERY_PWR_LOW_PKT:
                    sb.append("4");
                    break;
                case clsPacketTypes.BATTERY_PWR_REMOVED_PKT:
                    sb.append("5");
                    break;
                case clsPacketTypes.BATTERY_PWR_RECONNECT_PKT:
                    sb.append("6");
                    break;
                case clsPacketTypes.IGNITION_ON_PKT:
                    sb.append("7");
                    break;
                case clsPacketTypes.IGNITION_OFF_PKT:
                    sb.append("8");
                    break;
                case clsPacketTypes.EMERGENCY_ALERT_ON_PKT:
                    sb.append("10");
                    break;
                case clsPacketTypes.EMERGENCY_ALERT_OFF_PKT:
                    sb.append("11");
                    break;
                case clsPacketTypes.ALERT_OVER_AIR_PKT:
                    sb.append("12");
                    break;
                case clsPacketTypes.HARSHBRK_PKT:
                    sb.append("13");
                    break;
                case clsPacketTypes.HARSHACC_PKT:
                    sb.append("14");
                    break;
                case clsPacketTypes.RASH_TURNING_PKT:
                    sb.append("15");
                    break;
                case clsPacketTypes.TAMPER_ALERT_PKT:
                    sb.append("16");
                    break;
                default:
                    sb.append("0");
                    break;
            }
            sb.append(SEPARATOR_COMMA);

            //packet status
            sb.append(packet_status);
            sb.append(SEPARATOR_COMMA);

            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);

            //veh no
            sb.append(getVechicleRegNo());
            sb.append(SEPARATOR_COMMA);

            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append(GPS_FIXED);
            } else {
                sb.append(GPS_OFF);
            }
            sb.append(SEPARATOR_COMMA);

            //gps date
            sb.append(split_time[0].replace("/", ""));
            sb.append(SEPARATOR_COMMA);
            sb.append(split_time[1].replace(":", ""));
            sb.append(SEPARATOR_COMMA);

            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            //latitude direction
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);

            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            //longitude direction
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            //heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            //no of satelites
            sb.append(clsSharedVariables.getCurNoSatelites());
            sb.append(SEPARATOR_COMMA);
            //altitude
            sb.append(clsSharedVariables.getCurAltitude());
            sb.append(SEPARATOR_COMMA);

            //pdop
            sb.append(clsSharedVariables.getPdop());
            sb.append(SEPARATOR_COMMA);

            //hdop
            sb.append(clsSharedVariables.getHdop());
            sb.append(SEPARATOR_COMMA);

            // Network Operator Name
            sb.append(clsSharedVariables.getNetworkOperatorName());
            sb.append(SEPARATOR_COMMA);

            //ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);

            //main_on status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            //Battery status
            //sb.append(objHealth.get_battery_status());
            // sb.append(SEPARATOR_COMMA);
            //get main voltage
            sb.append(clsSharedVariables.getAnaMainsVoltage());
            sb.append(SEPARATOR_COMMA);
            //get battery voltage
            sb.append(clsSharedVariables.getAnaBatVoltage());
            sb.append(SEPARATOR_COMMA);
            //get Emergency Alert Status
            // sb.append(clsSharedVariables.getAnaBatVoltage());
            //  sb.append(SEPARATOR_COMMA);
            //get emergency alert status
            // clsSharedVariables.setEmergencyAlertStatus('0');
            sb.append(clsSharedVariables.getEmergencyAlertStatus());
            sb.append(SEPARATOR_COMMA);

            //get Tamper Alert Status
            sb.append(clsSharedVariables.getTamperAlertStatus());
            sb.append(SEPARATOR_COMMA);

            //get GSM Signal Strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            //get mcc
            sb.append(clsSharedVariables.getMcc());
            sb.append(SEPARATOR_COMMA);

            //get mnc
            sb.append(clsSharedVariables.getMnc());
            sb.append(SEPARATOR_COMMA);

            //get lac
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //get cell id
            sb.append(clsSharedVariables.getCellId());
            sb.append(SEPARATOR_COMMA);
            //NMR
            sb.append(clsSharedVariables.getNmr1CellId());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr1Lac());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr1SigStrength());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr2CellId());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr2Lac());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr2SigStrength());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr3CellId());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr3Lac());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr3SigStrength());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr4CellId());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr4Lac());
            sb.append("#");
            sb.append(clsSharedVariables.getNmr4SigStrength());
            sb.append(SEPARATOR_COMMA);
            //get  Digital Input1,2,3,4 Status
            sb.append(clsSharedVariables.getDigInput4Status());
            sb.append(clsSharedVariables.getDigInput3Status());
            sb.append(clsSharedVariables.getDigInput2Status());
            sb.append(clsSharedVariables.getDigInput1Status());

            sb.append(SEPARATOR_COMMA);

            //digital output 1,2 status
            sb.append(clsSharedVariables.getDigOut2Value());
            sb.append(clsSharedVariables.getDigOut1Value());
            sb.append(SEPARATOR_COMMA);

            //sequence number
            //sequence number they nees 000000 6 digit number
            //sb.append(clsSharedVariables.getPktSeqNo());
            formattedPktSeqNo = String.format("%06d", clsSharedVariables.getPktSeqNo());
            sb.append(formattedPktSeqNo);
            sb.append(SEPARATOR_COMMA);

            clsSharedVariables.incPktSeqNo();
            //parameter change over air through server or sms
            if (pkt_type.equals(clsPacketTypes.ALERT_OVER_AIR_PKT)) {
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }

                //which parameter is chenged
                if (val.equals("SETAPN")) {
                    sb.append(clsSharedVariables.getApn());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETOSLIMIT")) {
                    sb.append(gpsDriving.over_speed_limit);
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETHA")) {
                    sb.append(gpsDriving.harsh_acc_threshold);
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETHB")) {
                    sb.append(gpsDriving.harsh_brk_threshold);
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETRT")) {
                    sb.append(clsSharedVariables.getGyroAngle());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETPHNUM")) {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("CLRCFGALL")) {
                    sb.append(gpsDriving.harsh_acc_threshold);
                    sb.append(SEPARATOR_COMMA);
                    sb.append(gpsDriving.harsh_brk_threshold);
                    sb.append(SEPARATOR_COMMA);
                    sb.append(gpsDriving.over_speed_limit);
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETIP1")) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETIP2")) {
                    sb.append(clsSharedVariables.getIpAddr2());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo2());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETVEHREG")) {
                    sb.append(clsSharedVariables.getVechicleRegNo());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETIGNCFG")) {
                    sb.append(clsSharedVariables.get_gprs_sleep_val());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETEMRCFG")) {
                    sb.append(clsSharedVariables.getEmergencyStateTimeDuration());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETNORMALTIME")) {
                    sb.append(clsSharedVariables.get_gprs_normal_mode_val());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETIGNDELAYCFG")) {
                    sb.append(clsSharedVariables.getMainDeviceShutTime());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETDEVRST")) {
                    sb.append("RESET");
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("CLRALMSPEED")) {
                    sb.append(clsSharedVariables.getOverSpeedBuzzerEnabled());
                    sb.append(SEPARATOR_COMMA);
                } else if (val.equals("SETALLINTERVAL")) {
                    sb.append(clsSharedVariables.get_gprs_normal_mode_val());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.get_gprs_sleep_val());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getMainDeviceShutTime());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getSleepModeState());
                    sb.append(SEPARATOR_COMMA);
                }
            }
            //checksum
            checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
            sb.append(String.valueOf(checksum));

            //end of character
            sb.append(clsSharedVariables.LOC_END_CHARACTER);

            clsGpsLinkedList objLst = new clsGpsLinkedList();
            objLst.addLastData(sb.toString().getBytes());

            /*if (clsSharedVariables.getIpAddr3Enable()) {
                trackingMessagePktWithCan(pkt_type, packet_status);
            }*/

 /* if (getIpInterface() == DUAL_IP_INTERFACE) {
                objLst.addLastDataIp2(sb.toString().getBytes());
            }*/
            objLst = null;

        } catch (ParseException ex) {
        } finally {
            objHealth = null;
            checksum = null;
            dat = null;
            time = null;
            sb = null;
            formattedDateTimeUTC = null;
            sdfDateTimeUTC = null;

        }
    }
    //on every configurable intervel this health packet will be sent

    public synchronized String healthMsgPkt(boolean event_based_pkt) {
        StringBuilder sb = new StringBuilder();
        String checksum;
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        Calendar cal = Calendar.getInstance();
        double total_space_3339OBU = 0.0;
        double used_space_3339OBU = 0.0;
        double total_space_root = 0.0;
        double used_space_root = 0.0;
        double totalSpaceCombined = 0.0;
        double usedSpacePercentage3339OBU = 0.0;
        String formattedPercentage3339OBU = null;
        double usedSpacePercentageroot = 0.0;
        String formattedPercentageroot = null;
        String formattedTotalSpaceCombined = null;

        try {
            File file = new File(main_route_path.getAbsolutePath());
            File f = new File("/");
            DecimalFormat df = new DecimalFormat("###.##");
            sb.append(clsSharedVariables.START_CHARACTER);
            //  sb.append(clsPacketTypes16833.OBU_CS_HEALTH_PKT);
            // sb.append(SEPARATOR_COMMA);
            //obuid
            sb.append(clsSharedVariables.getOBUID());
            sb.append(SEPARATOR_COMMA);

            //mdvr name
            sb.append(clsSharedVariables.MDVR_NAME);
            sb.append(SEPARATOR_COMMA);

            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //date and time
            //gps date
            sb.append(sdfDateandTime.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            ////primary IP
            sb.append(clsSharedVariables.getIpAddr1());
            sb.append(SEPARATOR_COMMA);

            //Secondary IP
            sb.append(clsSharedVariables.getIpAddr2());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);

            //protocol version
            sb.append(clsDefines.SW_PROTOCOL_VERSION);
            sb.append(SEPARATOR_COMMA);
            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);

            //veh no
            // sb.append(getVechicleRegNo());
            //sb.append(SEPARATOR_COMMA);
            //storage 1 status
            sb.append(clsSharedVariables.getStorage1Status());
            sb.append(SEPARATOR_COMMA);

            //storage 1 memory status
            sb.append(clsSharedVariables.getStorage1MemStatus());
            sb.append(SEPARATOR_COMMA);

            //storage 2 status
            sb.append(clsSharedVariables.getStorage2Status());
            sb.append(SEPARATOR_COMMA);

            //storage 2 memory status
            sb.append(clsSharedVariables.getStorage2MemStatus());
            sb.append(SEPARATOR_COMMA);

            //CAM1 recording status
            sb.append(objHealth.get_cam1_status());
            sb.append(SEPARATOR_COMMA);

            //CAM2 recording status
            sb.append(objHealth.get_cam2_status());
            sb.append(SEPARATOR_COMMA);
            //CAM3 recording status
            sb.append(objHealth.get_cam3_status());
            sb.append(SEPARATOR_COMMA);
            //CAM4 recording status
            sb.append(objHealth.get_cam4_status());
            sb.append(SEPARATOR_COMMA);
            //CAM5 recording status
            sb.append(objHealth.get_cam5_status());
            sb.append(SEPARATOR_COMMA);
            //CAM6recording status
            sb.append(objHealth.get_cam6_status());
            sb.append(SEPARATOR_COMMA);
            //CAM7 recording status
            sb.append(objHealth.get_cam7_status());
            sb.append(SEPARATOR_COMMA);
            //CAM8 recording status
            sb.append(objHealth.get_cam8_status());
            sb.append(SEPARATOR_COMMA);

            //CAM1 microphone  recording status
            sb.append(objHealth.get_cam_audio1_status());
            sb.append(SEPARATOR_COMMA);

            //CAM2 microphone  recording status
            sb.append(objHealth.get_cam_audio2_status());
            sb.append(SEPARATOR_COMMA);
            //CAM3  microphone recording status
            sb.append(objHealth.get_cam_audio3_status());
            sb.append(SEPARATOR_COMMA);
            //CAM4  microphone recording status
            sb.append(objHealth.get_cam_audio4_status());
            sb.append(SEPARATOR_COMMA);
            //CAM5  microphone recording status
            sb.append(objHealth.get_cam_audio5_status());
            sb.append(SEPARATOR_COMMA);
            //CAM6 microphone recording status
            sb.append(objHealth.get_cam_audio6_status());
            sb.append(SEPARATOR_COMMA);
            //CAM7 microphone recording status
            sb.append(objHealth.get_cam_audio7_status());
            sb.append(SEPARATOR_COMMA);
            //CAM8 microphone recording status
            sb.append(objHealth.get_cam_audio8_status());
            sb.append(SEPARATOR_COMMA);

            //Ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);

            //Emergency Button Status
            sb.append(clsSharedVariables.getEmergencyAlertStatus());
            sb.append(SEPARATOR_COMMA);
            //second icat added packets
            //battery percentage
            if (clsSharedVariables.getbatVolValue() > 8.3) {
                sb.append("100");
            } else if (clsSharedVariables.getbatVolValue() > 8.2) {
                sb.append("90");
            } else if (clsSharedVariables.getbatVolValue() > 8.0) {
                sb.append("80");
            } else if (clsSharedVariables.getbatVolValue() > 7.9) {
                sb.append("70");
            } else if (clsSharedVariables.getbatVolValue() > 7.75) {
                sb.append("60");
            } else if (clsSharedVariables.getbatVolValue() > 7.6) {
                sb.append("50");
            } else if (clsSharedVariables.getbatVolValue() > 7.45) {
                sb.append("40");
            } else if (clsSharedVariables.getbatVolValue() > 7.3) {
                sb.append("30");
            } else if (clsSharedVariables.getbatVolValue() > 7.15) {
                sb.append("20");
            } else if (clsSharedVariables.getbatVolValue() > 7.0) {
                sb.append("10");
            }
            sb.append(SEPARATOR_COMMA);
            //low battery threshold value
            sb.append("7.0");
            sb.append(SEPARATOR_COMMA);
            //memory percentage used by sd card
            try {
                // Calculate used space percentage for "/"
                total_space_root = f.getTotalSpace();
                used_space_root = total_space_root - f.getFreeSpace();
                usedSpacePercentageroot = (used_space_root / total_space_root) * 100.0;
                formattedPercentageroot = df.format(usedSpacePercentageroot);
                // Calculate used space percentage for "/media/pi/3339OBU"
                total_space_3339OBU = file.getTotalSpace();
                used_space_3339OBU = total_space_3339OBU - file.getFreeSpace();
                usedSpacePercentage3339OBU = (used_space_3339OBU / total_space_3339OBU) * 100.0;
                formattedPercentage3339OBU = df.format(usedSpacePercentage3339OBU);
                // Calculate the combined used space percentage
                totalSpaceCombined = (usedSpacePercentage3339OBU + usedSpacePercentageroot) / 2.0;
                formattedTotalSpaceCombined = df.format(totalSpaceCombined);

                sb.append(formattedTotalSpaceCombined);
            } catch (Exception ex) {
            }
            sb.append(SEPARATOR_COMMA);

            //data update rate when ignition on
            sb.append(clsSharedVariables.get_gprs_normal_mode_val());
            sb.append(SEPARATOR_COMMA);
            //data update rate when ignition off
            sb.append(clsSharedVariables.get_gprs_sleep_val());
            sb.append(SEPARATOR_COMMA);
            //digital input status
            //get  Digital Input1,2,3,4 Status
            sb.append(clsSharedVariables.getDigInput4Status());
            sb.append(clsSharedVariables.getDigInput3Status());
            sb.append(clsSharedVariables.getDigInput2Status());
            sb.append(clsSharedVariables.getDigInput1Status());
            sb.append(SEPARATOR_COMMA);
            //Analog input/output, status giving default
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //battery Status
            //sb.append(objHealth.get_battery_status());
            //  sb.append(SEPARATOR_COMMA);
            //gps Status    .
            //  sb.append(objHealth.get_gps_status());
            //  sb.append(SEPARATOR_COMMA);
            //gprs Status    .
            //  sb.append(objHealth.get_gprs_status());
            //  sb.append(SEPARATOR_COMMA);
            //fd Status    .
            //if (objHealth.get_fd_status() < 2) {
            //  sb.append(objHealth.get_fd_status());
            //  } else {
            //     sb.append(BOARD_STATUS_FAILURE);
            //}
            //  sb.append(SEPARATOR_COMMA);
            //sd Status    .
            //  if (objHealth.get_sd_status() < 2) {
            //   sb.append(objHealth.get_sd_status());
            //  } else {
            //   sb.append(BOARD_STATUS_FAILURE);
            // }
            //   sb.append(SEPARATOR_COMMA);
            //rd Status    .
            //   if (objHealth.get_rd_status() < 2) {
            //   sb.append(objHealth.get_rd_status());
            //  } else {
            //   sb.append(BOARD_STATUS_FAILURE);
            // }
            //  sb.append(SEPARATOR_COMMA);
            //id Status    .
            //  if (objHealth.get_id_status() < 2) {
            //    sb.append(objHealth.get_id_status());
            // } else {
            //  sb.append(BOARD_STATUS_FAILURE);
            // }
            // sb.append(SEPARATOR_COMMA);
            //can Status    .
            //   sb.append(objHealth.get_can_status());
            // / sb.append(SEPARATOR_COMMA);
            //latitude     .
            //   sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            //   sb.append(SEPARATOR_COMMA);
            //latitude     .
            //     sb.append(clsSharedVariables.getCurLongitude().toString());
            //  sb.append(SEPARATOR_COMMA);
            //checksum
            checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
            sb.append(String.valueOf(checksum));
            sb.append(clsSharedVariables.END_CHARACTER);

            if (event_based_pkt == false) {
                add_first_bus_linked_list(sb.toString());
            }
        } catch (Exception ex) {

        }
        return sb.toString();
    }
    //on emergency event/panic button press this packet will be send untill the responce from the server is received

    public synchronized void emergency_alert_message_pkt(String message_type, String packet_status) {
        /*
         i	 Packet Header	 EPB  The unique identifier for all messages from ATD	  Character, 3 bytes
         ii	 Message Type	 Message Types supported. Emergency message (EMR  or Stop Message (SEM ))	  Character,2 bytes
         iii	 Vehicle ID	 Unique ID of the vehicle (IMEI Number   	 Character , 15 bytes
         iv	 Packet Type	 NM – Normal packet  SP – Stored packet  	  Character,2 bytes
         v	 Date	 Date and time of the location obtained from the location data in YYYYMMDDhhmmss format  	 Character, 14 bytes
         vi	 GPS Validity	 A – Valid; V – Invalid  	 Character, 1 byte
         vii	 Latitude	 Latitude in decimal degrees - dd.mmmmmm format  	 Double , 12 bytes
         viii	 Latitude Direction	 N – North; S – South  	  Character, 1 byte
         ix	 Longitude	 Longitude in decimal degrees - dd.mmmmmm format  	 Double, 12 bytes
         x	 Longitude Direction	 E – East; W – West 	  Character,1 byte
         xi	 Altitude	 Altitude in ‘m’ (above sea level  Double	 Double , 12 bytes
         xii	 Speed	 Speed in km/h 	 Float, 6 bytes
         xiii	 Distance	 Distance calculated from previous GPS data Float	Float,  6 bytes
         xiv	 Provider	 G - Fine GPS N – Coarse GPS or data from the network 	 Character,  1 byte
         xv	 Vehicle RegnNo	 Registration number of the vehicle Character	  Character, 16 bytes
         xvi	 Reply Number	 The mobile number to which Test response need to be sent	 0 byte
         xvii	 CRC	 The 32 bit checksum of all the characters from the header up to the CRC field 	8 bytes

         */
        String str;
        String checksum;
        Calendar cal = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();

        sb.append(clsSharedVariables.START_CHARACTER);
        //EPB
        sb.append(clsPacketTypes16833.OBU_CS_EMERGENCY_ALERT_PKT);
        sb.append(SEPARATOR_COMMA);
        //EMR
        //sb.append(clsPacketTypes16833.OBU_CS_EMERGENCY_PKT);
        // sb.append(SEPARATOR_COMMA);
        //message type
        sb.append(message_type);
        sb.append(SEPARATOR_COMMA);
        //imei number
        sb.append(clsSharedVariables.getImeiNo());
        sb.append(SEPARATOR_COMMA);

        //packet type
        sb.append(packet_status);
        sb.append(SEPARATOR_COMMA);

        //date and time
        //YYYYMMDDHHMMSS
        sb.append(sdfDateandTime.format(cal.getTime()));

        sb.append(SEPARATOR_COMMA);

        //vendor id
        //sb.append(clsSharedVariables.getVendorId());
        //sb.append(SEPARATOR_COMMA);
        //pkt header
        // sb.append(clsSharedVariables.EMERGENCY_PACKET_HEADER);
        //  sb.append(SEPARATOR_COMMA);
        //message type
        //  sb.append(message_type);
        //  sb.append(SEPARATOR_COMMA);
        //packet type
        // sb.append(clsPacketTypes.EMERGENCY_ALERT_PKT);
        // sb.append(SEPARATOR_COMMA);
        //gps validity
        if (getGpsState() == GPS_FIXED) {
            sb.append("A");
        } else {
            sb.append("V");
        }
        sb.append(SEPARATOR_COMMA);
        if (getGpsState() == GPS_FIXED) {
            //latitude
            str = String.valueOf(getCurLatitude());
            while (str.length() <= 12) {
                str = str + "0";
            }
            str = str.substring(0, 12);
            sb.append(str);
            sb.append(SEPARATOR_COMMA);
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);
            //Longitude
            str = String.valueOf(getCurLongitude());

            while (str.length() <= 12) {
                str = str + "0";
            }

            str = str.substring(0, 12);
            sb.append(str);
            sb.append(SEPARATOR_COMMA);
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //altitude
            str = String.valueOf(clsSharedVariables.getCurAltitude());

            while (str.length() <= 12) {
                str = str + "0";
            }
            str = str.substring(0, 12);
            sb.append(str);
            sb.append(SEPARATOR_COMMA);

            //speed
            str = String.valueOf(clsSharedVariables.getCurSpeed());

            while (str.length() <= 6) {
                str = str + "0";
            }

            str = str.substring(0, 6);
            sb.append(str);
            sb.append(SEPARATOR_COMMA);

            //distance calculated from previous gps data
            str = String.valueOf(clsSharedVariables.getDisCalcPrevGpsData());

            while (str.length() <= 6) {
                str = str + "0";
            }

            str = str.substring(0, 6);
            sb.append(str);
            sb.append(SEPARATOR_COMMA);

            ///fine gps (G) or coarse from network (N)
            sb.append('G');
            sb.append(SEPARATOR_COMMA);
        } else {
            str = "00.000000000";
            sb.append(str); //latitude
            sb.append(SEPARATOR_COMMA);
            sb.append("N"); //lat dir
            sb.append(SEPARATOR_COMMA);
            sb.append(str); //longitude
            sb.append(SEPARATOR_COMMA);
            sb.append("E"); //lon dir
            sb.append(SEPARATOR_COMMA);
            sb.append(str); //altitude
            sb.append(SEPARATOR_COMMA);
            sb.append(00.000);//speed
            sb.append(SEPARATOR_COMMA);
            sb.append(00.000);//distance calculated from previous gps data
            sb.append(SEPARATOR_COMMA);
            sb.append('N');//fine gps (G) or coarse from network (N)
            sb.append(SEPARATOR_COMMA);
        }

        //vehicle number
        str = clsSharedVariables.getVechicleRegNo();
        /*while (str.length() < 16) {
            str = " " + str;
        }
        str = str.substring(0, 16);*/
        sb.append(str);
        sb.append(SEPARATOR_COMMA);
        //reply mobile no 0 bytes ignore this field
        sb.append(clsSharedVariables.getSmsPhoneNo());
        sb.append(SEPARATOR_COMMA);
        sb.append(clsSharedVariables.END_CHARACTER);
        checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
        //  sb.append(CalculateCRC32ChecksumForByteArray(sb.toString().getBytes()));
        sb.append(String.valueOf(checksum));
        //   sb.append(clsSharedVariables.END_CHARACTER);
        //sb.append(clsDefines.END_PKT);
        // sb.append(checksum); //8 bytes

        clsEmergencyPktQueue objEmer = new clsEmergencyPktQueue();
        if (clsSharedVariables.getIpAddr1Enable()) {
            objEmer.addEmergencyPkt(sb.toString().getBytes());
        }

        if (clsSharedVariables.getIpAddr2Enable()) {
            if (clsSharedVariables.getEmergencyAlertCame() == true) {
                objEmer.addEmergencyPktIP2(sb.toString().getBytes());
            }
        }
        sb = null;
        str = null;
        cal = null;

    }
    //on emergency event packet will be sent to the phone number  which is confugured in the device.

    public synchronized String emergency_alert_sms_message_pkt(String message_type, String packet_status) {
        /*
         i	 Packet Header	 EPB  The unique identifier for all messages from ATD	  Character, 3 bytes
         ii	 Message Type	 Message Types supported. Emergency message (EMR  or Stop Message (SEM ))	  Character,2 bytes
         iii	 Vehicle ID	 Unique ID of the vehicle (IMEI Number   	 Character , 15 bytes
         iv	 Packet Type	 NM – Normal packet  SP – Stored packet  	  Character,2 bytes
         v	 Date	 Date and time of the location obtained from the location data in YYYYMMDDhhmmss format  	 Character, 14 bytes
         vi	 GPS Validity	 A – Valid; V – Invalid  	 Character, 1 byte
         vii	 Latitude	 Latitude in decimal degrees - dd.mmmmmm format  	 Double , 12 bytes
         viii	 Latitude Direction	 N – North; S – South  	  Character, 1 byte
         ix	 Longitude	 Longitude in decimal degrees - dd.mmmmmm format  	 Double, 12 bytes
         x	 Longitude Direction	 E – East; W – West 	  Character,1 byte
         xi	 Altitude	 Altitude in ‘m’ (above sea level  Double	 Double , 12 bytes
         xii	 Speed	 Speed in km/h 	 Float, 6 bytes
         xiii	 Distance	 Distance calculated from previous GPS data Float	Float,  6 bytes
         xiv	 Provider	 G - Fine GPS N – Coarse GPS or data from the network 	 Character,  1 byte
         xv	 Vehicle RegnNo	 Registration number of the vehicle Character	  Character, 16 bytes
         xvi	 Reply Number	 The mobile number to which Test response need to be sent	 0 byte
         xvii	 CRC	 The 32 bit checksum of all the characters from the header up to the CRC field 	8 bytes

         */

        String str;
        String checksum;
        Calendar cal = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(clsSharedVariables.START_CHARACTER);
            //EPB
            sb.append(clsPacketTypes16833.OBU_CS_EMERGENCY_ALERT_PKT);
            sb.append(SEPARATOR_COMMA);
            //EMR
            //sb.append(clsPacketTypes16833.OBU_CS_EMERGENCY_PKT);
            // sb.append(SEPARATOR_COMMA);
            //message type
            sb.append(message_type);
            sb.append(SEPARATOR_COMMA);
            //imei number
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);

            //packet type
            sb.append(packet_status);
            sb.append(SEPARATOR_COMMA);

            //date and time
            //YYYYMMDDHHMMSS
            sb.append(sdfDateandTime.format(cal.getTime()));

            sb.append(SEPARATOR_COMMA);

            //vendor id
            //sb.append(clsSharedVariables.getVendorId());
            //sb.append(SEPARATOR_COMMA);
            //pkt header
            // sb.append(clsSharedVariables.EMERGENCY_PACKET_HEADER);
            //  sb.append(SEPARATOR_COMMA);
            //message type
            //  sb.append(message_type);
            //  sb.append(SEPARATOR_COMMA);
            //packet type
            // sb.append(clsPacketTypes.EMERGENCY_ALERT_PKT);
            // sb.append(SEPARATOR_COMMA);
            //gps validity
            if (getGpsState() == GPS_FIXED) {
                sb.append("A");
            } else {
                sb.append("V");
            }
            sb.append(SEPARATOR_COMMA);
            if (getGpsState() == GPS_FIXED) {
                //latitude
                str = String.valueOf(getCurLatitude());
                while (str.length() <= 12) {
                    str = str + "0";
                }
                str = str.substring(0, 12);
                sb.append(str);
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getCurLatitudeDir());
                sb.append(SEPARATOR_COMMA);
                //Longitude
                str = String.valueOf(getCurLongitude());

                while (str.length() <= 12) {
                    str = str + "0";
                }

                str = str.substring(0, 12);
                sb.append(str);
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getCurLongitudeDir());
                sb.append(SEPARATOR_COMMA);

                //altitude
                str = String.valueOf(clsSharedVariables.getCurAltitude());

                while (str.length() <= 12) {
                    str = str + "0";
                }
                str = str.substring(0, 12);
                sb.append(str);
                sb.append(SEPARATOR_COMMA);

                //speed
                str = String.valueOf(clsSharedVariables.getCurSpeed());

                while (str.length() <= 6) {
                    str = str + "0";
                }

                str = str.substring(0, 6);
                sb.append(str);
                sb.append(SEPARATOR_COMMA);

                //distance calculated from previous gps data
                str = String.valueOf(clsSharedVariables.getDisCalcPrevGpsData());

                while (str.length() <= 6) {
                    str = str + "0";
                }

                str = str.substring(0, 6);
                sb.append(str);
                sb.append(SEPARATOR_COMMA);

                ///fine gps (G) or coarse from network (N)
                sb.append('G');
                sb.append(SEPARATOR_COMMA);
            } else {
                str = "00.000000000";
                sb.append(str); //latitude
                sb.append(SEPARATOR_COMMA);
                sb.append("N"); //lat dir
                sb.append(SEPARATOR_COMMA);
                sb.append(str); //longitude
                sb.append(SEPARATOR_COMMA);
                sb.append("E"); //lon dir
                sb.append(SEPARATOR_COMMA);
                sb.append(str); //altitude
                sb.append(SEPARATOR_COMMA);
                sb.append(00.000);//speed
                sb.append(SEPARATOR_COMMA);
                sb.append(00.000);//distance calculated from previous gps data
                sb.append(SEPARATOR_COMMA);
                sb.append('N');//fine gps (G) or coarse from network (N)
                sb.append(SEPARATOR_COMMA);
            }

            //vehicle number
            str = clsSharedVariables.getVechicleRegNo();
            /* while (str.length() < 16) {
                str = " " + str;
            }
            str = str.substring(0, 16);*/
            sb.append(str);
            sb.append(SEPARATOR_COMMA);
            //reply mobile no 0 bytes ignore this field
            sb.append(clsSharedVariables.getSmsPhoneNo());
            sb.append(SEPARATOR_COMMA);
            //LAC
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //get cell id
            sb.append(clsSharedVariables.getCellId());
            sb.append(SEPARATOR_COMMA);
            //END character
            sb.append(clsSharedVariables.END_CHARACTER);
            // checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
            //  sb.append(CalculateCRC32ChecksumForByteArray(sb.toString().getBytes()));
            //  sb.append(String.valueOf(checksum));

            //sb.append(clsDefines.END_PKT);
            // sb.append(checksum); //8 bytes
            //  clsEmergencyPktQueue objEmer = new clsEmergencyPktQueue();
            // objEmer.addEmergencyPkt(sb.toString().getBytes());
            return sb.toString();
        } catch (Exception ex) {
        } finally {

            sb = null;
        }
        return sb.toString();
    }
    //whenever the request for firmware comes from the server this packet will be sent

    public synchronized void send_firmware_version() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_FIRMWARE_VERSION_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //firmware version
                sb.append(clsDefines.SW_FIRMWARE_VERSION);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }

    }
    //whenever the request for protocol version comes from the server this packet will be sent

    public synchronized void send_protocol_version() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_PROTOCOL_VERSION_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Protocol version
                sb.append(clsDefines.SW_PROTOCOL_VERSION);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }

    }
    //whenever the request for mac address comes from the server this packet will be sent

    public synchronized void send_mac_address() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_MAC_ADDR_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Mac Address
                sb.append(clsSharedVariables.getMacAddress());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }

    }
    //whenever the request for apn address comes from the server this packet will be sent

    public synchronized void send_apn_address(boolean server) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_APN_ADDR_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //ip address,port no
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //apn Address
                sb.append(clsSharedVariables.getApn());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }

    }
    //whenever the request for primary ip comes from the server this packet will be sent

    public synchronized void send_primary_ipaddress(boolean server) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_IPADDRESS1_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //IP Address1
                sb.append(clsSharedVariables.getIpAddr1());
                sb.append(SEPARATOR_COMMA);

                //Port No1
                sb.append(clsSharedVariables.getPortNo1());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }

    }
    //whenever the request for secondary address comes from the server this packet will be sent

    public synchronized void send_secondary_ipaddress(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_IPADDRESS2_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //IP addr1
                sb.append(clsSharedVariables.getIpAddr2());
                sb.append(SEPARATOR_COMMA);

                //Port No2
                sb.append(clsSharedVariables.getPortNo2());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for overspeed comes from the server this packet will be sent

    public synchronized void send_overspeedlimit(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_OVERSPEED_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //Overspeedlimit
                sb.append(gpsDriving.over_speed_limit);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for harsh acc comes from the server this packet will be sent

    public synchronized void send_harshacc(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_HA_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //Overspeedlimit
                sb.append(gpsDriving.harsh_acc_threshold);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for harsh break comes from the server this packet will be sent

    public synchronized void send_harshbreak(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_HB_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //Ov
                //Overspeedlimit
                sb.append(gpsDriving.harsh_brk_threshold);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for rash turn comes from the server this packet will be sent

    public synchronized void send_rashturn(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_RT_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //Overspeedlimit
                sb.append(clsSharedVariables.getGyroAngle());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for imei comes from the server this packet will be sent

    public synchronized void send_imeino(boolean server) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_IMEINUMBER_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
//source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //IMEI Number
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for vehcile registration number comes from the server this packet will be sent

    public synchronized void send_vehicle_regno(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_VEH_REG_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                //IMEI Number
                sb.append(clsSharedVariables.getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the request for ignition  comes from the server this packet will be sent

    public synchronized void send_ignition_mode(boolean server) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_IGNITION_MODE_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //source of command
                if (server == true) {
                    sb.append(clsSharedVariables.getIpAddr1());
                    sb.append(SEPARATOR_COMMA);
                    sb.append(clsSharedVariables.getPortNo1());
                    sb.append(SEPARATOR_COMMA);
                } else {
                    sb.append(clsSharedVariables.getSmsPhoneNo());
                    sb.append(SEPARATOR_COMMA);
                }
                sb.append(clsSharedVariables.getEmergencyModeByPanicButton());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getEmergencyModeBySmsMode());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getEmergencyStateTimeDuration());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getSleepModeState());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getMainDeviceShutTime());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.get_gprs_sleep_val());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.get_gprs_normal_mode_val());
                sb.append(SEPARATOR_COMMA);
                sb.append(clsSharedVariables.getSmsPhoneNo());
                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }

        if (clsSharedVariables.getIpAddr1Enable()) {
            byte[] final_buf = new byte[200];
            int buf_inc = 0;
            int i = 0;
            short len;
            byte buf[];
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

                final_buf[buf_inc++] = clsPacketTypes.OBU_CS_PKT_SEND_IGNITION_MOCE; //Message Type

                //length of  PKT
                len = (short) 7;
                buf = convertToByteArray(len);
                for (i = 0; i < buf.length; i++) {
                    final_buf[buf_inc++] = buf[i];
                }

                //reserved 8 bytes
                for (i = 0; i < 8; i++) {
                    final_buf[buf_inc++] = 0;
                }
                buf_inc = 24;

                final_buf[buf_inc++] = clsSharedVariables.getEmergencyModeByPanicButton();
                final_buf[buf_inc++] = clsSharedVariables.getEmergencyModeBySmsMode();
                final_buf[buf_inc++] = clsSharedVariables.getEmergencyStateTimeDuration();
                final_buf[buf_inc++] = clsSharedVariables.getSleepModeState();
                final_buf[buf_inc++] = (byte) clsSharedVariables.getMainDeviceShutTime();
                final_buf[buf_inc++] = (byte) clsSharedVariables.get_gprs_sleep_val();
                final_buf[buf_inc++] = (byte) clsSharedVariables.get_gprs_normal_mode_val();

                //end of the packet
                final_buf[buf_inc++] = END_PKT;
                buf = new byte[buf_inc];
                for (i = 0; i < buf_inc; i++) {
                    buf[i] = final_buf[i];
                }
                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(buf);
                objQue = null;
            } catch (Exception ex) {
            } finally {
                final_buf = null;
                OBUId = null;
                buf = null;
                cal = null;
            }
        }
    }
    //This is commom acknowledgement for all the packets for set commands

    public synchronized void send_common_ack_server(byte res, String message_type) {

        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_COMMON_ACK_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //IMEI Number
                sb.append(res);
                sb.append(SEPARATOR_COMMA);

                //message type
                sb.append(message_type);
                sb.append(SEPARATOR_COMMA);
                //ip addresss
                //  sb.append(clsSharedVariables.getIpAddr1());
                //    sb.append(SEPARATOR_COMMA);
                //port
                // sb.append(clsSharedVariables.getPortNo1());
                //  sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                clsPktResponseQueue objQue = new clsPktResponseQueue();
                objQue.addData(sb.toString().getBytes());
                objQue = null;
            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the panic is pressed on the obu this packet will be sent to the server

    public synchronized void panic_messages(byte panic_id) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_PANIC_MESSAGE_PKT);
                sb.append(SEPARATOR_COMMA);
                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);
                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //IMEI Number
                sb.append(panic_id);
                sb.append(SEPARATOR_COMMA);

                //Latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                //Latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the trip is started this packet will be sent to the server

    public synchronized void trip_start_pkt(byte eta_hr, byte eta_min) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_START_ROUTE_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //ETA
                sb.append(eta_hr);
                sb.append(eta_min);
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the trip is skipped  this packet will be sent to the server

    public synchronized void trip_skip_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_CANCEL_ROUTE_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {

            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the current stop is arrived this packet will be sent to the server

    public void cur_stop_pkt(String stop_name) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_STOP_REACHED_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //Stop Name
                sb.append(stop_name);
                sb.append(SEPARATOR_COMMA);
                //reserved
                sb.append("");
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the approaching stop is arrived this packet will be sent to the server

    public void app_stop_pkt(String stop_name) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_STOP_APPROACH_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //Stop Name
                sb.append(stop_name);
                sb.append(SEPARATOR_COMMA);
                //reserved
                sb.append("");
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //every time whenever the bus crosses the stop this packet will be sent to the server

    public void bus_crossed_stop_pkt(String stop_name) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_STOP_DEPART_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //Stop Id No
                sb.append(stop_name);
                sb.append(SEPARATOR_COMMA);

                //reserved
                sb.append("");
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the skip stop is pressed on obu this packet will be sent to the server

    public void skip_stop_pkt(String stop_name) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_STOP_SKIP_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //Stop Id No
                sb.append(stop_name);
                sb.append(SEPARATOR_COMMA);

                //reserved
                sb.append("");
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the trip is ended  this packet will be sent to the server

    public void trip_end_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_END_ROUTE_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Route Number
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);

                //Trip No
                sb.append(clsSharedVariables.getCurTripNo() + 1);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the driver login and duty starts this packet will be sent to the server

    public void duty_start_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_DUTY_START_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //reserved
                sb.append("0");
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the duty ends this packet will be sent to the server

    public void duty_end_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_DUTY_END_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_duty_id());
                sb.append(SEPARATOR_COMMA);

                //Reserved string
                sb.append("0");
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the conductor login is happened this packet will be sent

    public void conductor_duty_start_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_CONDUCTOR_DUTY_START_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_conductor_id());
                sb.append(SEPARATOR_COMMA);

                //reserved
                sb.append("0");
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());

            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the conductor logout is happened this packet will be sent

    public void conductor_duty_end_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_CONDUCTOR_DUTY_END_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Duty ID
                sb.append(clsSharedVariables.get_conductor_id());
                sb.append(SEPARATOR_COMMA);

                //Reserved string
                sb.append("0");
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the overspeed detection starts this packet will be sent

    public void over_speed_start_pkt(byte speed_limit, byte cur_speed) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_OVERSPEED_START_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //speed_limit
                sb.append(speed_limit);
                sb.append(SEPARATOR_COMMA);

                //current spped
                sb.append(cur_speed);
                sb.append(SEPARATOR_COMMA);

                //latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                //longitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the overspeed detection ends this packet will be sent

    public void over_speed_end_pkt(byte speed_limit, byte cur_speed, byte max_speed, short duration) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_OVERSPEED_END_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //speed_limit
                sb.append(speed_limit);
                sb.append(SEPARATOR_COMMA);

                //current spped
                sb.append(cur_speed);
                sb.append(SEPARATOR_COMMA);
                //max_speed
                sb.append(max_speed);
                sb.append(SEPARATOR_COMMA);

                //duration
                sb.append(duration);
                sb.append(SEPARATOR_COMMA);

                //latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                //longitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the bus is stopped in nonstoppage stop this packet will be sent

    public void nonstoppage_pkt(short non_stop_time_limit, short duration) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_VEH_NON_STOPPAGE_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //speed_limit
                sb.append(non_stop_time_limit);
                sb.append(SEPARATOR_COMMA);

                //current spped
                sb.append(duration);
                sb.append(SEPARATOR_COMMA);

                //latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                //longitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //whenever the reset packet came from server this packet will be sent

    public void reset_on() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_RESET_RES_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //reserved
                sb.append("");
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }

    //if the bus is stsopped in non stoppage stop then when driver selects the message on obu about why the bus is stopped this packet will be sent.
    public void driver_stopped_reason_messages(byte reason_id) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_DRIVER_HALT_JUST_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(reason_id);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }

    //whenever the server is connected if schedule is enable this packet will be sent.
    public byte[] schedule_request_pkt() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_SCH_REQUEST_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //schedule id
                sb.append(clsSharedVariables.get_driver_id());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                return sb.toString().getBytes();
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
        return null;
    }
    //whenever the special message is selected on bdc this packet will be sent

    public void special_messages(byte msg_id) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_SPECIAL_ROUTE_MSG_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(msg_id);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //everytime whenever the obu is power on this packet will be sent

    public void obu_started() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_OBU_STARTED_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(clsSharedVariables.reset_type);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //display board pids will be sent to the server

    public void disbrd_pids() {

        byte brd_type = 0;
        for (brd_type = 0; brd_type < 4; brd_type++) {
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
    //display board pids will be sent to the server

    public void disbrd_pid_pkt(byte disbrd_type, short pid_code) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_DISBRD_PIDS_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //brd type
                sb.append(disbrd_type);
                sb.append(SEPARATOR_COMMA);

                //code
                sb.append(pid_code);
                sb.append(SEPARATOR_COMMA);

                switch (pid_code) {

                    case PID_HW_REV: {
                        sb.append(objPids[disbrd_type].hw_rev_val); //1 byte length of pid
                    }
                    break;
                    case PID_SER_NO: {
                        sb.append(objPids[disbrd_type].ser_no); //1 byte length of pid

                    }
                    break;
                    case PID_BOOT_LOADER_SW_REV: {
                        sb.append(objPids[disbrd_type].boot_loader_sw_rev); //1 byte length of pid

                    }
                    break;

                    case PID_APP_SW_REV: {
                        sb.append(objPids[disbrd_type].app_sw_rev); //1 byte length of pid

                    }
                    break;
                    case PID_FONT_LIB_REV: {
                        sb.append(objPids[disbrd_type].font_lib_rev); //1 byte length of pid

                    }
                    break;

                    case PID_CPU_PART_NO: {
                        sb.append(objPids[disbrd_type].cpu_part_no);
                    }
                    break;

                    case PID_CPU_QUALIFICATION: {
                        sb.append(objPids[disbrd_type].cpu_qual);
                    }
                    break;

                    case PID_CPU_TEMP_RANGE: {
                        sb.append(objPids[disbrd_type].cpu_temp_range);
                    }
                    break;
                    case PID_COMPILATION_FW_DATE_TIME: {
                        sb.append(objPids[disbrd_type].compilation_fw_date);
                    }
                    break;

                    case PID_FLASH_UPATE_STATUS: {
                        sb.append(objPids[disbrd_type].flash_update_status);
                    }
                    break;

                    case PID_TEST_DATE_TIME: {
                        sb.append(objPids[disbrd_type].test_date_time);
                    }
                    break;

                    case PID_ARTICLE_NO_SIGN_LEVEL: {
                        sb.append(objPids[disbrd_type].article_no_sign_level);
                    }
                    break;

                    case PID_PRODUCTION_DATE: {
                        sb.append(objPids[disbrd_type].prod_date);
                    }
                    break;

                    case PID_END_CUSTOMER: {
                        sb.append(objPids[disbrd_type].end_customer);
                    }
                    break;

                    case PID_ORDER_NO: {
                        sb.append(objPids[disbrd_type].order_no);
                    }
                    break;

                    case PID_VEHICLE_TYPE: {
                        sb.append(objPids[disbrd_type].vehicle_type);
                    }
                    break;

                    case PID_BUS_BUILDER_NO: {
                        sb.append(objPids[disbrd_type].bus_builder_no);
                    }
                    break;

                    case PID_LANGUAGE: {
                        sb.append(String.valueOf(objPids[disbrd_type].language));
                    }
                    break;

                    case PID_BOARD_TEMP_SENSOR: {
                        sb.append(objPids[disbrd_type].board_temp_sensor);
                    }
                    break;

                    case PID_INTERNAL_CPU_TEMP: {
                        sb.append(objPids[disbrd_type].internal_cpu_temp);
                    }
                    break;

                    case PID_MIN_TEMP_CPU: {
                        sb.append(objPids[disbrd_type].min_cpu_temp);
                    }
                    break;
                    case PID_MAX_TEMP_CPU: {
                        sb.append(objPids[disbrd_type].max_cpu_temp);
                    }
                    break;

                    case PID_MIN_TEMP_BOARD: {
                        sb.append(objPids[disbrd_type].min_board_temp);
                    }

                    break;
                    case PID_MAX_TEMP_BOARD: {
                        sb.append(objPids[disbrd_type].max_board_temp);
                    }
                    break;
                    case PID_MIN_INPUT_VOLT: {
                        sb.append(objPids[disbrd_type].min_input_volt);
                    }
                    break;

                    case PID_MAX_INPUT_VOLT: {
                        sb.append(objPids[disbrd_type].max_input_volt);
                    }
                    break;
                    case PID_OPERATING_HRS: {
                        sb.append(String.valueOf(objPids[disbrd_type].operating_hours));
                    }
                    break;
                    case PID_NO_RESETS: {
                        sb.append(String.valueOf(objPids[disbrd_type].no_resets));
                    }
                    break;
                }
                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //display board dtc codes will be sent to the server

    public void disbrd_dtc_pkt() {
        byte disbrd_id;
        int dev_dtc = 0;
        for (disbrd_id = 0; disbrd_id < 4; disbrd_id++) {
            for (dev_dtc = 1200; dev_dtc < 1203; dev_dtc++) {
                if (getDisBrdName() == SUMITH_DISBRD) {
                    if (clsSharedVariables.getIpAddr1Enable()) {
                        StringBuilder sb = new StringBuilder();
                        Calendar cal = Calendar.getInstance();
                        String checksum;
                        try {

                            sb.append(clsSharedVariables.START_CHARACTER);

                            sb.append(clsPacketTypes16833.OBU_CS_OBU_DISBRD_USB_GPS_DTC_PKT);
                            sb.append(SEPARATOR_COMMA);

                            //vendor id
                            sb.append(clsSharedVariables.getVendorId());
                            sb.append(SEPARATOR_COMMA);
                            //veh no
                            sb.append(getVechicleRegNo());
                            sb.append(SEPARATOR_COMMA);

                            //imeino
                            sb.append(clsSharedVariables.getImeiNo());
                            sb.append(SEPARATOR_COMMA);

                            //gps date time
                            sb.append(sdfDateTime.format(cal.getTime()));
                            sb.append(SEPARATOR_COMMA);

                            //brd type
                            sb.append(disbrd_id);
                            sb.append(SEPARATOR_COMMA);

                            switch (dev_dtc) {

                                case DTC_HIGH_VOLTAGE: {
                                    if (disbrd_id == 0) {
                                        sb.append(PanDisplayBoardDiag.front_high_volt_cnt); //no of pids default 1

                                    } else if (disbrd_id == 1) {
                                        sb.append(PanDisplayBoardDiag.side_high_volt_cnt);
                                    } else if (disbrd_id == 2) {
                                        sb.append(PanDisplayBoardDiag.rear_high_volt_cnt);
                                    } else if (disbrd_id == 3) {
                                        sb.append(PanDisplayBoardDiag.int_high_volt_cnt);
                                    }
                                    break;
                                }
                                case DTC_LOW_VOLTAGE: {
                                    if (disbrd_id == 0) {
                                        sb.append(PanDisplayBoardDiag.front_low_volt_cnt);
                                    } else if (disbrd_id == 1) {
                                        sb.append(PanDisplayBoardDiag.side_low_volt_cnt);
                                    } else if (disbrd_id == 2) {
                                        sb.append(PanDisplayBoardDiag.rear_low_volt_cnt);
                                    } else if (disbrd_id == 3) {
                                        sb.append(PanDisplayBoardDiag.int_low_volt_cnt);
                                    }
                                    break;
                                }
                                case DTC_OVER_HEAT_VOLTAGE: {
                                    if (disbrd_id == 0) {
                                        sb.append(PanDisplayBoardDiag.front_over_heat_cnt);
                                    } else if (disbrd_id == 1) {
                                        sb.append(PanDisplayBoardDiag.side_over_heat_cnt);
                                    } else if (disbrd_id == 2) {
                                        sb.append(PanDisplayBoardDiag.rear_over_heat_cnt);
                                    } else if (disbrd_id == 3) {
                                        sb.append(PanDisplayBoardDiag.int_over_heat_cnt);
                                    }
                                    break;
                                }
                            }
                            sb.append(SEPARATOR_COMMA);

                            //dtc
                            sb.append(dev_dtc);
                            sb.append(SEPARATOR_COMMA);

                            //dtc value
                            sb.append("0");
                            sb.append(SEPARATOR_COMMA);
                            //checksum
                            checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                            sb.append(String.valueOf(checksum));
                            sb.append(clsSharedVariables.END_CHARACTER);

                            add_first_bus_linked_list(sb.toString());
                        } catch (Exception ex) {
                        } finally {
                            sb = null;
                            cal = null;
                        }
                    }
                }
            }
        }
    }
    //display board pids will be sent to the server on power on

    public void obu_pid_pkt() {

        int dev_pid = 0;
        for (dev_pid = 100; dev_pid < 110; dev_pid++) {
            if (getDisBrdName() == SUMITH_DISBRD) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    StringBuilder sb = new StringBuilder();
                    Calendar cal = Calendar.getInstance();
                    String checksum;
                    try {

                        sb.append(clsSharedVariables.START_CHARACTER);

                        sb.append(clsPacketTypes16833.OBU_CS_OBU_PIDS_PKT);
                        sb.append(SEPARATOR_COMMA);

                        //vendor id
                        sb.append(clsSharedVariables.getVendorId());
                        sb.append(SEPARATOR_COMMA);
                        //veh no
                        sb.append(getVechicleRegNo());
                        sb.append(SEPARATOR_COMMA);

                        //imeino
                        sb.append(clsSharedVariables.getImeiNo());
                        sb.append(SEPARATOR_COMMA);

                        //gps date time
                        sb.append(sdfDateTime.format(cal.getTime()));
                        sb.append(SEPARATOR_COMMA);

                        //brd type
                        sb.append(dev_pid);
                        sb.append(SEPARATOR_COMMA);

                        switch (dev_pid) {

                            case PID_HW_REV: {
                                sb.append(hw_revision);
                            }

                            break;

                            case PID_SER_NO: {
                                sb.append(serial_no);
                            }
                            break;
                            case PID_BOOT_LOADER_SW_REV: {
                                sb.append(boot_ldr_version);
                            }

                            break;

                            case PID_APP_SW_REV: {
                                sb.append(SW_FIRMWARE_VERSION);

                            }
                            break;

                            case PID_FONT_LIB_REV: {
                                sb.append(font_lib_version);
                            }
                            break;

                            case PID_CPU_PART_NO: {
                                sb.append(cpu_part_no);
                            }
                            break;

                            case PID_CPU_QUALIFICATION: {
                                sb.append(cpu_qualification);
                            }
                            break;

                            case PID_CPU_TEMP_RANGE: {
                                sb.append(cpu_temp_range);
                            }
                            break;
                            case PID_COMPILATION_FW_DATE_TIME: {
                                sb.append(compilation_fw_datetime);
                            }
                            break;
                            case PID_TEST_DATE_TIME: {
                                sb.append(test_date_time_pid);
                            }
                            break;

                        }

                        sb.append(SEPARATOR_COMMA);

                        //checksum
                        checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                        sb.append(String.valueOf(checksum));
                        sb.append(clsSharedVariables.END_CHARACTER);
                        add_first_bus_linked_list(sb.toString());
                    } catch (Exception ex) {
                    } finally {
                        sb = null;
                        cal = null;
                    }
                }
            }
        }
    }
    //display board dtc codes will be sent to the server on power on

    public void obu_dtc_pkt() {

        int dev_dtc = 0;

        for (dev_dtc = 0; dev_dtc < 5; dev_dtc++) {
            if (getDisBrdName() == SUMITH_DISBRD) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    StringBuilder sb = new StringBuilder();
                    Calendar cal = Calendar.getInstance();
                    String checksum;
                    try {

                        sb.append(clsSharedVariables.START_CHARACTER);

                        sb.append(clsPacketTypes16833.OBU_CS_OBU_DISBRD_USB_GPS_DTC_PKT);
                        sb.append(SEPARATOR_COMMA);

                        //vendor id
                        sb.append(clsSharedVariables.getVendorId());
                        sb.append(SEPARATOR_COMMA);
                        //veh no
                        sb.append(getVechicleRegNo());
                        sb.append(SEPARATOR_COMMA);

                        //imeino
                        sb.append(clsSharedVariables.getImeiNo());
                        sb.append(SEPARATOR_COMMA);

                        //gps date time
                        sb.append(sdfDateTime.format(cal.getTime()));
                        sb.append(SEPARATOR_COMMA);

                        //brd type
                        sb.append(OBU_PKT_ID);
                        sb.append(SEPARATOR_COMMA);

                        switch (dev_dtc) {
                            case 0: {
                                sb.append(obu_watchdog_reset_cnt);
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_WATCHDOG_RESET);

                                break;
                            }
                            case 1: {
                                sb.append(obu_low_volt_reset_cnt & 0xFF);; //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_LOW_VOLTAGE_RESET);

                                break;
                            }
                            case 2: {
                                sb.append(obu_high_volt_cnt & 0xFF);  //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_OBU_OVERVOLTAGE);

                                break;
                            }
                            case 3: {
                                sb.append(obu_low_volt_cnt); //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_OBU_LOWVOLTAGE);

                                break;

                            }
                            case 4: {
                                sb.append(obu_over_heat_cnt & 0xFF);  //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_OBU_OVERHEAT);

                                break;

                            }
                        }

                        sb.append(SEPARATOR_COMMA);

                        //dtc value
                        sb.append("0");
                        sb.append(SEPARATOR_COMMA);
                        //checksum
                        checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                        sb.append(String.valueOf(checksum));
                        sb.append(clsSharedVariables.END_CHARACTER);

                        add_first_bus_linked_list(sb.toString());
                    } catch (Exception ex) {
                    } finally {
                        sb = null;
                        cal = null;
                    }
                }
            }
        }
    }
    //display board usb dtc will be sent to the server on power on

    public void usb_dtc_pkt() {

        int dev_dtc = 0;

        for (dev_dtc = 0; dev_dtc < 4; dev_dtc++) {
            if (getDisBrdName() == SUMITH_DISBRD) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    StringBuilder sb = new StringBuilder();
                    Calendar cal = Calendar.getInstance();
                    String checksum;
                    try {

                        sb.append(clsSharedVariables.START_CHARACTER);

                        sb.append(clsPacketTypes16833.OBU_CS_OBU_DISBRD_USB_GPS_DTC_PKT);
                        sb.append(SEPARATOR_COMMA);

                        //vendor id
                        sb.append(clsSharedVariables.getVendorId());
                        sb.append(SEPARATOR_COMMA);
                        //veh no
                        sb.append(getVechicleRegNo());
                        sb.append(SEPARATOR_COMMA);

                        //imeino
                        sb.append(clsSharedVariables.getImeiNo());
                        sb.append(SEPARATOR_COMMA);

                        //gps date time
                        sb.append(sdfDateTime.format(cal.getTime()));
                        sb.append(SEPARATOR_COMMA);

                        //brd type
                        sb.append(USB_PKT_ID);
                        sb.append(SEPARATOR_COMMA);

                        switch (dev_dtc) {
                            case 0: {
                                sb.append(usb_invalid_cnt);
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_USB_INVALID);

                                break;
                            }
                            case 1: {
                                sb.append(usb_unknown_cnt & 0xFF);; //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_USB_UNKNOWN);

                                break;
                            }
                            case 2: {
                                sb.append(usb_invalid_filesystem_cnt & 0xFF);  //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_USB_INVALID_FILESYSTEM);

                                break;
                            }
                            case 3: {
                                sb.append(usb_overcurrent_cnt); //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_USB_OVERCURRENT);

                                break;

                            }

                        }

                        sb.append(SEPARATOR_COMMA);

                        //dtc value
                        sb.append("0");
                        sb.append(SEPARATOR_COMMA);
                        //checksum
                        checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                        sb.append(String.valueOf(checksum));
                        sb.append(clsSharedVariables.END_CHARACTER);

                        add_first_bus_linked_list(sb.toString());
                    } catch (Exception ex) {
                    } finally {
                        sb = null;
                        cal = null;
                    }
                }
            }
        }
    }
    //display board gps dtc will be sent to the server on power on

    public void gps_dtc_pkt() {

        int dev_dtc = 0;

        for (dev_dtc = 0; dev_dtc < 3; dev_dtc++) {
            if (getDisBrdName() == SUMITH_DISBRD) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    StringBuilder sb = new StringBuilder();
                    Calendar cal = Calendar.getInstance();
                    String checksum;
                    try {

                        sb.append(clsSharedVariables.START_CHARACTER);

                        sb.append(clsPacketTypes16833.OBU_CS_OBU_DISBRD_USB_GPS_DTC_PKT);
                        sb.append(SEPARATOR_COMMA);

                        //vendor id
                        sb.append(clsSharedVariables.getVendorId());
                        sb.append(SEPARATOR_COMMA);
                        //veh no
                        sb.append(getVechicleRegNo());
                        sb.append(SEPARATOR_COMMA);

                        //imeino
                        sb.append(clsSharedVariables.getImeiNo());
                        sb.append(SEPARATOR_COMMA);

                        //gps date time
                        sb.append(sdfDateTime.format(cal.getTime()));
                        sb.append(SEPARATOR_COMMA);

                        //brd type
                        sb.append(GPS_PKT_ID);
                        sb.append(SEPARATOR_COMMA);

                        switch (dev_dtc) {
                            case 0: {
                                sb.append(gps_lost_comm_cnt);
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_GPS_LOST_COMM);

                                break;
                            }
                            case 1: {
                                sb.append(gps_invalid_data_cnt & 0xFF);; //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_GPS_INVALID_DATA);

                                break;
                            }
                            case 2: {
                                sb.append(gps_antenna_error_cnt & 0xFF);  //no of pids default 1
                                sb.append(SEPARATOR_COMMA);
                                sb.append(DTC_GPS_ANTENNA_ERROR);

                                break;
                            }

                        }

                        sb.append(SEPARATOR_COMMA);

                        //dtc value
                        sb.append("0");
                        sb.append(SEPARATOR_COMMA);
                        //checksum
                        checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                        sb.append(String.valueOf(checksum));
                        sb.append(clsSharedVariables.END_CHARACTER);
                        add_first_bus_linked_list(sb.toString());
                    } catch (Exception ex) {
                    } finally {
                        sb = null;
                        cal = null;
                    }
                }
            }
        }
    }

    //This packet will send can parameters to the server
    public void can_preselected_params_pkt(String can_str) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            short canlen;
            String[] candata_split_str;

            int can_inc = 0;
            clsCanQueue objCanQueue = new clsCanQueue();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_CAN_PRESELECTED_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                candata_split_str = can_str.split("#");
                canlen = (short) candata_split_str.length;
                //can length
                sb.append(canlen);
                sb.append(SEPARATOR_COMMA);
                for (can_inc = 0; can_inc < canlen; can_inc++) {
                    sb.append(candata_split_str[can_inc]);
                    sb.append(SEPARATOR_COMMA);
                }

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                objCanQueue.addData(sb.toString().getBytes());
            } catch (Exception ex) {
            } finally {

                candata_split_str = null;

                can_str = null;

                cal = null;
                objCanQueue = null;
            }
        }
    }
    //This packet will sent whenever display boards are disconnected

    public void displayboard_disconnect_pkt(byte displayboard_no, byte status) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_DISPLAYBOARD_DISCONNECT_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(displayboard_no);
                sb.append(SEPARATOR_COMMA);
                if (status < 2) {
                    sb.append(status);
                } else {
                    sb.append(BOARD_STATUS_FAILURE);
                }

                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //This packet will sent whenever cameras are disconnected

    public void video_disconnect_pkt(byte camera_no, byte status) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_VIDEO_STATUS_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(camera_no);
                sb.append(SEPARATOR_COMMA);

                sb.append(status);
                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //This packet will sent whenever videoloss event comes from the cameras

    public void video_loss_pkt(byte camera_no) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_VIDEOLOSS_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(camera_no);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //This packet will sent whenever video tamper event comes from the cameras

    public void video_tamper_pkt(byte camera_no) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {
                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_VIDEOTAMPER_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(camera_no);
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //This packet will be sent whenever the can door open event value comes from CAN

    public void can_door_open(byte door_no) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_CAN_DOOR_OPEN_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(door_no);
                sb.append(SEPARATOR_COMMA);
                sb.append(getCurSpeed());
                sb.append(SEPARATOR_COMMA);

                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //This packet will be sent whenever the can door close event value comes from CAN

    public void can_door_close(byte door_no) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_CAN_DOOR_CLOSE_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //reason
                sb.append(door_no);
                sb.append(SEPARATOR_COMMA);
                sb.append(getCurSpeed());
                sb.append(SEPARATOR_COMMA);

                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //This packet will be sent to phone whenever the GETLOCATION msg comes from phone

    public synchronized String smsMessagePkt() {
        /*
        - IMEI, Latitude, Direction,
                      Longitude, Direction, location fix, speed, Cell ID, LAC,
                      Date and Time.
         */
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        Date dat_now = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();

        StringBuilder sb = new StringBuilder();

        try {
            if (clsSharedVariables.getCurDate() == null) {
                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
            }
            if (clsSharedVariables.getCurTime() == null) {
                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
            }

            //start character
            sb.append(clsSharedVariables.START_CHARACTER);

            //ACTVR
            sb.append(clsPacketTypes16833.OBU_CS_SMS_PKT);
            sb.append(SEPARATOR_COMMA);
            //Random code
            sb.append(clsSharedVariables.getOBUID());
            sb.append(SEPARATOR_COMMA);
            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);

            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            //alert id
            sb.append(clsPacketTypes16833.ALTER_ID);
            sb.append(SEPARATOR_COMMA);

            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            //latitude direction
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);

            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            //longitude direction
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append(GPS_FIXED);
            } else {
                sb.append(GPS_OFF);
            }
            sb.append(SEPARATOR_COMMA);

            //Date and time
            sb.append(sdfDateTime_sms.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            //gps date
            //  sb.append(sdfDate.format(dat));
            //  sb.append(SEPARATOR_COMMA);
            //gps time
            //  sb.append(sdfTime.format(time));
            //  sb.append(SEPARATOR_COMMA);
            //Heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            //speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            //get GSM Signal Strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            //get mcc
            sb.append(clsSharedVariables.getMcc());
            sb.append(SEPARATOR_COMMA);

            //get mnc
            sb.append(clsSharedVariables.getMnc());
            sb.append(SEPARATOR_COMMA);

            //get lac
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //main_on status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            //ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);

            //mains voltage
            sb.append(clsSharedVariables.getAnaMainsVoltage());
            sb.append(SEPARATOR_COMMA);

            //sequence number
            sb.append(clsSharedVariables.getPktSeqNoSms());
            sb.append(SEPARATOR_COMMA);

            clsSharedVariables.incPktSeqNoSms();
            //Vehcile mode
            sb.append(clsPacketTypes16833.VEH_MODE);
            // sb.append(SEPARATOR_COMMA);

            //end of character
            sb.append(clsSharedVariables.END_CHARACTER);
            return sb.toString();

        } catch (Exception ex) {
            //  //////System.out.println("Exc tracking pkt " + ex.getMessage());
        } finally {
            sb = null;
            cal = null;
        }
        return sb.toString();
    }
    //This packet will be sent to server whenever the GETHEALTH msg comes from phone

    public synchronized String healthMsgPktSms() {

        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        Date dat;
        Date time;
        Date dat_now = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();

        StringBuilder sb = new StringBuilder();

        try {
            if (clsSharedVariables.getCurDate() == null) {
                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
            }
            if (clsSharedVariables.getCurTime() == null) {
                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
            }
            dat = sdf_date.parse(clsSharedVariables.getCurDate());
            time = sdf_time.parse(clsSharedVariables.getCurTime());

            //start character
            sb.append(clsSharedVariables.START_CHARACTER);

            //ACTVR
            sb.append(clsPacketTypes16833.OBU_CS_HLTSMS_PKT);
            sb.append(SEPARATOR_COMMA);
            //Random code
            sb.append(clsSharedVariables.getOBUID());
            sb.append(SEPARATOR_COMMA);
            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);

            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            //alert id
            sb.append(clsPacketTypes16833.ALTER_ID);
            sb.append(SEPARATOR_COMMA);

            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            //latitude direction
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);

            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            //longitude direction
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append(GPS_FIXED);
            } else {
                sb.append(GPS_OFF);
            }
            sb.append(SEPARATOR_COMMA);

            //Date and time
            sb.append(sdfDateTime_sms.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            //gps date
            //  sb.append(sdfDate.format(dat));
            //  sb.append(SEPARATOR_COMMA);
            //gps time
            //  sb.append(sdfTime.format(time));
            //  sb.append(SEPARATOR_COMMA);
            //Heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            //speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            //get GSM Signal Strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            //get mcc
            sb.append(clsSharedVariables.getMcc());
            sb.append(SEPARATOR_COMMA);

            //get mnc
            sb.append(clsSharedVariables.getMnc());
            sb.append(SEPARATOR_COMMA);

            //get lac
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //main_on status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            //ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);
            //mains voltage
            sb.append(clsSharedVariables.getAnaMainsVoltage());
            sb.append(SEPARATOR_COMMA);

            //sequence number
            sb.append(clsSharedVariables.getPktSeqNoSms());
            sb.append(SEPARATOR_COMMA);
            clsSharedVariables.incPktSeqNoSms();

            //Vehcile mode
            sb.append(clsPacketTypes16833.VEH_MODE);
            //sb.append(SEPARATOR_COMMA);

            //end of character
            sb.append(clsSharedVariables.END_CHARACTER);
            return sb.toString();

        } catch (Exception ex) {
            //  //////System.out.println("Exc tracking pkt " + ex.getMessage());
        } finally {

            dat = null;
            time = null;
            sb = null;
            cal = null;
        }
        return sb.toString();

    }

    //this packet will be sent to phone server tracking parameters along with can values
    public synchronized void trackingMessagePktWithCan(String pkt_type, char packet_status) {

        /*
         i) Start Character $
         ii) Header The header of the packet/identifier
         Vendor ID Vendor identification header
         iii) Firmware Version Version details of the firmware used in EX.1.0.0
         iv) Packet Type Specify the packet type—
         NR = Normal
         EA = Emergency alert
         TA = Tamper alert
         HP = Health packet
         IN = Ignition on
         IF = Ignition off
         BD = Battery disconnect
         BR = Battery reconnect
         BL = Battery low
         v) Packet Status L=Live or H= History
         vi) IMEI Identified of the sending unit. 15 digit standard unique IMEI No.
         vii) Vehicle Registration No. Mapped vehicle registration number
         viii) GPS Fix 1 = GPS fix or 0 = GPS invalid
         ix) Date Date value as per GPS date time (ddmmyy)
         x) Time Time value as per GPS date time in UTC format (hhmmss)
         xi) Latitude Latitude value in decimal degrees (with minimum 6 decimal places)
         xii) Latitude Direction Latitude Direction.
         For example, N=North; S= South
         xiii) Longitude Longitude value in decimal degrees (with minimum 6 decimal places)
         xiv) Longitude Direction Longitude direction.
         For example, E=East, W= West
         xv) Speed Speed in km/h
         xvi) Heading Course over ground in degrees
         xvii) No of Satellites Number of satellites available for fix
         xviii) Altitude Altitude of the device in ‘m’
         xix) PDOP Positional dilution of precision
         xx) HDOP Horizontal dilution of precision
         xxi) Network Operator Name Name of Network operator
         xxii) Ignition 1= Ign On; 0 = Ign Off
         Main Power Status 0 = Vehicle Battery Disconnected
         1= Vehicle Battery Reconnected
         xxiii) Main Input Voltage Indicator showing source voltage in Volts.
         xxiv) Internal Battery Voltage Indicator for Level of battery charge remaining
         xxv) Emergency Status 1= On ; 0 = Off
         xxvi) Tamper Alert C = Cover Closed, O = Cover Open
         xxvii) GSM Signal Strength Value Ranging from 0–31
         xxviii) MCC Mobile Country Code
         xxix) MNC Mobile Network Code
         xxx) LAC Location Area Code
         xxxi) Cell ID GSM Cell ID
         xxxii) NMR (neighbouring Cell ID) Neighbouring 4 cell ID along with their LAC and signal strength
         xxxiii) Digital Input Status 4 external digital input status (Status of Input 1 to Input 3 (0=Off, 1=On))
         xxxiv) Digital Output Status 2 external digital output status
         (0=Off; 1=On)
         xxxv) Frame Number Sequence Number of the messages (000001 to 999999)
         xxxvi) Checksum Insures no error in transmission (optional)
         xxxvii) End Character Indicated end of the frame
         */
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        String checksum;
        Date dat;
        Date time;
        Date dat_now = Calendar.getInstance().getTime();
        int i = 0;

        StringBuilder sb = new StringBuilder();
        String formattedDateTimeUTC = null;
        SimpleDateFormat sdfDateTimeUTC = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        sdfDateTimeUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        String[] split_time = null;

        try {
            if (clsSharedVariables.getCurDate() == null) {
                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
            }
            if (clsSharedVariables.getCurTime() == null) {
                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
            }
            dat = sdf_date.parse(clsSharedVariables.getCurDate());
            time = sdf_time.parse(clsSharedVariables.getCurTime());
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(dat);
            calendar.set(java.util.Calendar.HOUR_OF_DAY, time.getHours());
            calendar.set(java.util.Calendar.MINUTE, time.getMinutes());
            calendar.set(java.util.Calendar.SECOND, time.getSeconds());
            // Format and convert the combined date and time to UTC
            formattedDateTimeUTC = sdfDateTimeUTC.format(calendar.getTime());
            // Date dateTime = sdfDateTimeUTC.parse(formattedDateTimeUTC);

            split_time = formattedDateTimeUTC.split(" ");
            //start character
            sb.append(clsSharedVariables.START_CHARACTER);

            sb.append(clsPacketTypes16833.OBU_CS_LOCATION_PKT);  //header
            sb.append(SEPARATOR_COMMA);
            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);
            //packet type
            sb.append(pkt_type);
            sb.append(SEPARATOR_COMMA);

            //packet status
            sb.append(packet_status);
            sb.append(SEPARATOR_COMMA);

            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);

            //veh no
            sb.append(getVechicleRegNo());
            sb.append(SEPARATOR_COMMA);

            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append(GPS_FIXED);
            } else {
                sb.append(GPS_OFF);
            }
            sb.append(SEPARATOR_COMMA);

//            //gps date
//            sb.append(sdfDate.format(dat));
//            sb.append(SEPARATOR_COMMA);
//
//            //gps time
//            // sb.append(sdfTime.format(time));
//            // sb.append(SEPARATOR_COMMA);
//            //UTC time
//            //SimpleDateFormat sdf_format = new SimpleDateFormat("hhmmss");
//            sdfTime.setTimeZone(TimeZone.getTimeZone("UTC"));
//            sb.append(sdfTime.format(time));
//            sb.append(SEPARATOR_COMMA);
//gps date and time in utc
            sb.append(split_time[0].replace("/", ""));
            sb.append(SEPARATOR_COMMA);
            sb.append(split_time[1].replace(":", ""));
            sb.append(SEPARATOR_COMMA);

            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            //latitude direction
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);

            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            //longitude direction
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            //heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            //no of satelites
            // sb.append(clsSharedVariables.getCurNoSatelites());
            //  sb.append(SEPARATOR_COMMA);
            //altitude
            sb.append(clsSharedVariables.getCurAltitude());
            sb.append(SEPARATOR_COMMA);

            //pdop
            sb.append(clsSharedVariables.getPdop());
            sb.append(SEPARATOR_COMMA);

            //hdop
            sb.append(clsSharedVariables.getHdop());
            sb.append(SEPARATOR_COMMA);

            // Network Operator Name
            sb.append(clsSharedVariables.getNetworkOperatorName());
            sb.append(SEPARATOR_COMMA);

            //ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);

            //main_on status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            //Battery status
            //sb.append(objHealth.get_battery_status());
            // sb.append(SEPARATOR_COMMA);
            //get main voltage
            //sb.append(clsSharedVariables.getAnaMainsVoltage());
            // sb.append(SEPARATOR_COMMA);
            //get battery voltage
            //sb.append(clsSharedVariables.getAnaBatVoltage());
            // sb.append(SEPARATOR_COMMA);
            //get Emergency Alert Status
            // sb.append(clsSharedVariables.getAnaBatVoltage());
            //  sb.append(SEPARATOR_COMMA);
            //get emergency alert status
            // clsSharedVariables.setEmergencyAlertStatus('0');
            sb.append(clsSharedVariables.getEmergencyAlertStatus());
            sb.append(SEPARATOR_COMMA);

            //get Tamper Alert Status
            sb.append(clsSharedVariables.getTamperAlertStatus());
            sb.append(SEPARATOR_COMMA);

            //get GSM Signal Strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            //get mcc
            sb.append(clsSharedVariables.getMcc());
            sb.append(SEPARATOR_COMMA);

            //get mnc
            sb.append(clsSharedVariables.getMnc());
            sb.append(SEPARATOR_COMMA);

            //get lac
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //get cell id
            sb.append(clsSharedVariables.getCellId());
            sb.append(SEPARATOR_COMMA);

            //get  Digital Input1,2,3,4 Status
            sb.append(clsSharedVariables.getDigInput4Status());
            sb.append(clsSharedVariables.getDigInput3Status());
            sb.append(clsSharedVariables.getDigInput2Status());
            sb.append(clsSharedVariables.getDigInput1Status());

            sb.append(SEPARATOR_COMMA);

            //digital output 1,2 status
            sb.append(clsSharedVariables.getDigOut2Value());
            sb.append(clsSharedVariables.getDigOut1Value());
            sb.append(SEPARATOR_COMMA);

            //sequence number
            sb.append(clsSharedVariables.getPktSeqNoIp3());
            sb.append(SEPARATOR_COMMA);

            clsSharedVariables.incPktSeqNoIp3();

            //can parameters
            if (getCanBusType() == clsDefines.OLECTRA_BYD) {
                if (PanCanConfig.objCanElectricalCnt == 17) {
                    for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                        sb.append(PanCanConfig.objCanElectrical[i].value);
                        sb.append(SEPARATOR_COMMA);
                    }
                } else {
                    for (i = 0; i < 17; i++) {
                        sb.append("1");
                        sb.append(SEPARATOR_COMMA);
                    }
                }
            } else if (getCanBusType() == clsDefines.OLECTRA_CX2) {
                if (PanCanConfig.objCanElectricalCnt == 35) {
                    for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                        sb.append(PanCanConfig.objCanElectrical[i].value);
                        sb.append(SEPARATOR_COMMA);
                    }
                } else {
                    for (i = 0; i < 35; i++) {
                        sb.append("1");
                        sb.append(SEPARATOR_COMMA);
                    }
                }
            }

            //check sum
            checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
            sb.append(String.valueOf(checksum));

            //end of character
            sb.append(clsSharedVariables.LOC_END_CHARACTER);

            //  //System.out.println("Location Packet IP3 : " + sb.toString());
            clsGpsLinkedList objLst = new clsGpsLinkedList();
            objLst.addLastDataIp3(sb.toString().getBytes());

            /* if (getIpInterface() == DUAL_IP_INTERFACE) {
                objLst.addLastDataIp2(sb.toString().getBytes());
            }*/
            objLst = null;
        } catch (Exception ex) {
            // //////System.out.println("Exc tracking pkt " + ex.getMessage());
        } finally {
            objHealth = null;
            checksum = null;
            dat = null;
            time = null;
            sb = null;
            formattedDateTimeUTC = null;
            sdfDateTimeUTC = null;
        }
    }

    //after every bus stop cross this packet will be sent
    public void people_count_packet_on_every_stop(String stop_name) {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_PEOPLE_COUNT_ON_EACH_STOP_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                //latitude direction
                sb.append(clsSharedVariables.getCurLatitudeDir());
                sb.append(SEPARATOR_COMMA);

                //longitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);

                //longitude direction
                sb.append(clsSharedVariables.getCurLongitudeDir());
                sb.append(SEPARATOR_COMMA);

                //stop name
                sb.append(stop_name);
                sb.append(SEPARATOR_COMMA);
                //route number
                sb.append(clsSharedVariables.getCurRouteNo());
                sb.append(SEPARATOR_COMMA);
                //FRONT APC IN
                sb.append(clsSharedVariables.getApcPeopleIn());
                sb.append(SEPARATOR_COMMA);
                //FRONT APC OUT
                sb.append(clsSharedVariables.getApcPeopleOut());
                sb.append(SEPARATOR_COMMA);
                //BACK APC IN
                sb.append(clsSharedVariables.getApcPeopleIn1());
                sb.append(SEPARATOR_COMMA);
                //BACK APC OUT
                sb.append(clsSharedVariables.getApcPeopleOut1());
                sb.append(SEPARATOR_COMMA);
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);

                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }
    //everytime whenever route is ended either through gps or either through bdc

    public void people_count_packet_on_route_end() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {

                sb.append(clsSharedVariables.START_CHARACTER);

                sb.append(clsPacketTypes16833.OBU_CS_PEOPLE_COUNT_ON_ROUTE_END);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDateTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //latitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
                sb.append(SEPARATOR_COMMA);

                //latitude direction
                sb.append(clsSharedVariables.getCurLatitudeDir());
                sb.append(SEPARATOR_COMMA);

                //longitude
                sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
                sb.append(SEPARATOR_COMMA);

                //longitude direction
                sb.append(clsSharedVariables.getCurLongitudeDir());
                sb.append(SEPARATOR_COMMA);

                //route number
                sb.append(clsSharedVariables.getCurRouteNo());
                sb.append(SEPARATOR_COMMA);
                //FRONT APC IN
                sb.append(clsSharedVariables.getApcPeopleIn());
                sb.append(SEPARATOR_COMMA);
                //FRONT APC OUT
                sb.append(clsSharedVariables.getApcPeopleOut());
                sb.append(SEPARATOR_COMMA);
                //BACK APC IN
                sb.append(clsSharedVariables.getApcPeopleIn1());
                sb.append(SEPARATOR_COMMA);
                //BACK APC OUT
                sb.append(clsSharedVariables.getApcPeopleOut1());
                sb.append(SEPARATOR_COMMA);

                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }

    //if can is enable on every configurable intervel this packet will be sent
    public void can_protocol_cesl() {
        if (clsSharedVariables.getIpAddr1Enable()) {
            StringBuilder sb = new StringBuilder();
            Calendar cal = Calendar.getInstance();
            String checksum;
            try {
                sb.append(clsSharedVariables.START_CHARACTER);
                sb.append(clsPacketTypes16833.OBU_CS_CAN_CESL_PKT);
                sb.append(SEPARATOR_COMMA);

                //vendor id
                sb.append(clsSharedVariables.getVendorId());
                sb.append(SEPARATOR_COMMA);
                //veh no
                sb.append(getVechicleRegNo());
                sb.append(SEPARATOR_COMMA);

                //imeino
                sb.append(clsSharedVariables.getImeiNo());
                sb.append(SEPARATOR_COMMA);

                //gps date time
                sb.append(sdfDate.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);

                //Timestamp
                sb.append(sdfTime.format(cal.getTime()));
                sb.append(SEPARATOR_COMMA);
                //can parameters
                // CAN1 message values
                sb.append("CAN1:");
                sb.append(SEPARATOR_COMMA);
                for (int i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                    sb.append(PanCanConfig.objCanElectrical[i].value);
                    sb.append(SEPARATOR_COMMA);
                }
                if (clsSharedVariables.getCanEnabled1()) {
                    // CAN2 message values
                    sb.append("CAN2:");
                    sb.append(SEPARATOR_COMMA);
                    for (int i = 0; i < PanCanConfig.objCanElectricalCnt1; i++) {
                        sb.append(PanCanConfig.objCanElectrical1[i].value1);
                        sb.append(SEPARATOR_COMMA);
                    }
                }

                /*   //odometer
                sb.append(clsDefines.Odometer_reading);
                sb.append(SEPARATOR_COMMA);
                // Vehicle Status
                sb.append(clsDefines.Vehicle_Status);
                sb.append(SEPARATOR_COMMA);
                //SoC (State of Charge)
                sb.append(clsDefines.SOC);
                sb.append(SEPARATOR_COMMA);
                //SoH (State of Health)
                sb.append(clsDefines.SOH);
                sb.append(SEPARATOR_COMMA);
                //Speed
                sb.append(clsDefines.Speed);
                sb.append(SEPARATOR_COMMA);
                //Acceleration
                sb.append(clsDefines.Acceleration);
                sb.append(SEPARATOR_COMMA);
                // Voltage (pack and cell level)
                sb.append(clsDefines.Voltage);
                sb.append(SEPARATOR_COMMA);
                //Current (pack and cell level)
                sb.append(clsDefines.Current);
                sb.append(SEPARATOR_COMMA);
                // Charger Current
                sb.append(clsDefines.Charger_Current);
                sb.append(SEPARATOR_COMMA);
                //Charger Voltage
                sb.append(clsDefines.Charger_Voltage);
                sb.append(SEPARATOR_COMMA);
                // Ambient temperature
                sb.append(clsDefines.Ambient_temperature);
                sb.append(SEPARATOR_COMMA);
                // Cell temperature
                sb.append(clsDefines.Cell_temperature);
                sb.append(SEPARATOR_COMMA);
                // Motor temperature
                sb.append(clsDefines.Motor_temperature);
                sb.append(SEPARATOR_COMMA);
                // BMS error/charging fault
                sb.append(clsDefines.BMS_error);
                sb.append(SEPARATOR_COMMA);*/
                //checksum
                checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                sb.append(String.valueOf(checksum));
                sb.append(clsSharedVariables.END_CHARACTER);
                add_first_bus_linked_list(sb.toString());
            } catch (Exception ex) {
            } finally {
                sb = null;
                cal = null;
            }
        }
    }

    //This packet will be sent to server on every getlocation message from phone
    public synchronized void smsMessagePkt_toserver() {
        /*
        - IMEI, Latitude, Direction,
                      Longitude, Direction, location fix, speed, Cell ID, LAC,
                      Date and Time.
         */
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

        Date dat_now = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();

        StringBuilder sb = new StringBuilder();

        try {
            if (clsSharedVariables.getCurDate() == null) {
                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
            }
            if (clsSharedVariables.getCurTime() == null) {
                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
            }

            //start character
            sb.append(clsSharedVariables.START_CHARACTER);

            //ACTVR
            sb.append(clsPacketTypes16833.OBU_CS_SMS_PKT);
            sb.append(SEPARATOR_COMMA);
            //Random code
            sb.append(clsSharedVariables.getOBUID());
            sb.append(SEPARATOR_COMMA);
            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);

            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            //alert id
            sb.append(clsPacketTypes16833.ALTER_ID);
            sb.append(SEPARATOR_COMMA);

            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            //latitude direction
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);

            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            //longitude direction
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append(GPS_FIXED);
            } else {
                sb.append(GPS_OFF);
            }
            sb.append(SEPARATOR_COMMA);

            //Date and time
            sb.append(sdfDateTime_sms.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            //gps date
            //  sb.append(sdfDate.format(dat));
            //  sb.append(SEPARATOR_COMMA);
            //gps time
            //  sb.append(sdfTime.format(time));
            //  sb.append(SEPARATOR_COMMA);
            //Heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            //speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            //get GSM Signal Strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            //get mcc
            sb.append(clsSharedVariables.getMcc());
            sb.append(SEPARATOR_COMMA);

            //get mnc
            sb.append(clsSharedVariables.getMnc());
            sb.append(SEPARATOR_COMMA);

            //get lac
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //main_on status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            //ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);

            //mains voltage
            sb.append(clsSharedVariables.getAnaMainsVoltage());
            sb.append(SEPARATOR_COMMA);

            //sequence number
            sb.append(clsSharedVariables.getPktSeqNo());
            sb.append(SEPARATOR_COMMA);

            //Vehcile mode
            sb.append(clsPacketTypes16833.VEH_MODE);
            // sb.append(SEPARATOR_COMMA);

            //end of character
            sb.append(clsSharedVariables.END_CHARACTER);
            clsPktResponseQueue objQue = new clsPktResponseQueue();
            objQue.addData(sb.toString().getBytes());
            objQue = null;

        } catch (Exception ex) {
        } finally {
            sb = null;
            objHealth = null;
        }

    }
    //This packet will be sent to server on every gethealth message from phone

    public synchronized void healthMsgPktSms_toserver() {

        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        Date dat;
        Date time;
        Date dat_now = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();

        StringBuilder sb = new StringBuilder();

        try {
            if (clsSharedVariables.getCurDate() == null) {
                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
            }
            if (clsSharedVariables.getCurTime() == null) {
                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
            }
            dat = sdf_date.parse(clsSharedVariables.getCurDate());
            time = sdf_time.parse(clsSharedVariables.getCurTime());

            //start character
            sb.append(clsSharedVariables.START_CHARACTER);

            //ACTVR
            sb.append(clsPacketTypes16833.OBU_CS_HLTSMS_PKT);
            sb.append(SEPARATOR_COMMA);
            //Random code
            sb.append(clsSharedVariables.getOBUID());
            sb.append(SEPARATOR_COMMA);
            //vendor id
            sb.append(clsSharedVariables.getVendorId());
            sb.append(SEPARATOR_COMMA);

            //firmware version
            sb.append(clsDefines.SW_FIRMWARE_VERSION);
            sb.append(SEPARATOR_COMMA);

            //imeino
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            //alert id
            sb.append(clsPacketTypes16833.ALTER_ID);
            sb.append(SEPARATOR_COMMA);

            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            //latitude direction
            sb.append(clsSharedVariables.getCurLatitudeDir());
            sb.append(SEPARATOR_COMMA);

            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            //longitude direction
            sb.append(clsSharedVariables.getCurLongitudeDir());
            sb.append(SEPARATOR_COMMA);

            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append(GPS_FIXED);
            } else {
                sb.append(GPS_OFF);
            }
            sb.append(SEPARATOR_COMMA);

            //Date and time
            sb.append(sdfDateTime_sms.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            //gps date
            //  sb.append(sdfDate.format(dat));
            //  sb.append(SEPARATOR_COMMA);
            //gps time
            //  sb.append(sdfTime.format(time));
            //  sb.append(SEPARATOR_COMMA);
            //Heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            //speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            //get GSM Signal Strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            //get mcc
            sb.append(clsSharedVariables.getMcc());
            sb.append(SEPARATOR_COMMA);

            //get mnc
            sb.append(clsSharedVariables.getMnc());
            sb.append(SEPARATOR_COMMA);

            //get lac
            sb.append(clsSharedVariables.getLac());
            sb.append(SEPARATOR_COMMA);

            //main_on status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            //ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);
            //mains voltage
            sb.append(clsSharedVariables.getAnaMainsVoltage());
            sb.append(SEPARATOR_COMMA);

            //sequence number
            sb.append(clsSharedVariables.getPktSeqNo());
            sb.append(SEPARATOR_COMMA);

            //Vehcile mode
            sb.append(clsPacketTypes16833.VEH_MODE);
            //sb.append(SEPARATOR_COMMA);

            //end of character
            sb.append(clsSharedVariables.END_CHARACTER);
            clsPktResponseQueue objQue = new clsPktResponseQueue();
            objQue.addData(sb.toString().getBytes());
            objQue = null;

        } catch (Exception ex) {
            //  ////System.out.println("Exc tracking pkt " + ex.getMessage());
        } finally {
            dat = null;
            time = null;
            sb = null;
            cal = null;
        }
    }
//     public synchronized void alcoPktsmstoserver(){
//         if (clsSharedVariables.getIpAddr1Enable()) {
//         clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
//        Date dat;
//        Date time;
//        Date dat_now = Calendar.getInstance().getTime();
//        Calendar cal = Calendar.getInstance();
//        String checksum;
//        StringBuilder sb = new StringBuilder();
//        try {
//            if (clsSharedVariables.getCurDate() == null) {
//                clsSharedVariables.setCurDate(sdf_date.format(dat_now));
//            }
//            if (clsSharedVariables.getCurTime() == null) {
//                clsSharedVariables.setCurTime(sdf_time.format(dat_now));
//            }
//            dat = sdf_date.parse(clsSharedVariables.getCurDate());
//            time = sdf_time.parse(clsSharedVariables.getCurTime());
//            //start char
//            sb.append(clsSharedVariables.START_CHARACTER);
//            //Packet Type
//            sb.append(clsPacketTypes16833.ALCO_PKT);
//            sb.append(SEPARATOR_COMMA);
//              //vendor id
//            sb.append(clsSharedVariables.getVendorId());
//            sb.append(SEPARATOR_COMMA);
//            //veh no
//            sb.append(getVechicleRegNo());
//            sb.append(SEPARATOR_COMMA);
//            //imeino
//            sb.append(clsSharedVariables.getImeiNo());
//            sb.append(SEPARATOR_COMMA);
//            //Date and time
//            sb.append(sdfDate.format(cal.getTime()));
//            sb.append(SEPARATOR_COMMA);
//            //Timestamp
//            sb.append(sdfTime.format(cal.getTime()));
//            sb.append(SEPARATOR_COMMA);
//            //Result
//            sb.append(alco_res);
//            sb.append(SEPARATOR_COMMA);
//            //checksum
//            checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
//            sb.append(String.valueOf(checksum));
//            sb.append(clsSharedVariables.END_CHARACTER);  
//            add_first_bus_linked_list(sb.toString());
//    }
//         catch (Exception ex) {
//            //  ////System.out.println("Exc tracking pkt " + ex.getMessage());
//        }
//    }
//     }

}
