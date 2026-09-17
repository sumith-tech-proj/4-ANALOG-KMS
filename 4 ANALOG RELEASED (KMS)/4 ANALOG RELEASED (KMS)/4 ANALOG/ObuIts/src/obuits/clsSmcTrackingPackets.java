/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static obuits.cls16833Protocols.SEPARATOR_COMMA;
import static obuits.clsDefines.GPS_FIXED;
import static obuits.clsSharedVariables.getCurRouteNo;
import static obuits.clsSharedVariables.getGpsState;
import static obuits.clsSharedVariables.getNoStopsRoute;
import static obuits.clsSharedVariables.gps_data;

/**
 *
 * @author Dell
 */
public class clsSmcTrackingPackets {

    // Decimal pattern for latitude and longitude
    final String pattern = "00.00000000";
    // Formatter for decimal values
    final DecimalFormat decimalFormat = new DecimalFormat(pattern);
    // Date-time formatter
    SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyMMddHHmmss");

    public synchronized void trackingMessagePkt_Smc(int event, boolean ha, boolean hb, boolean overspeedstart, boolean overspeedend) {
        // Health packet structure object
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        String checksum;
        // Current time instance
        Calendar cal = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();

        try {
            // Start building the packet
            sb.append(clsSharedVariables.START_CHARACTER_SMC);
            sb.append(SEPARATOR_COMMA);
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            sb.append(clsSharedVariables.event_default_smc);
            sb.append(SEPARATOR_COMMA);

            // Latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            // Longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            // Date and time
            sb.append(sdfDateTime.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            // GPS status
            if (getGpsState() == GPS_FIXED) {
                sb.append("A");
            } else {
                sb.append("V");
            }
            sb.append(SEPARATOR_COMMA);

            // GSM signal strength
            sb.append(clsSharedVariables.getGsmSignalStrength());
            sb.append(SEPARATOR_COMMA);

            // Speed
            sb.append(clsSharedVariables.getCurSpeed());
            sb.append(SEPARATOR_COMMA);

            // Distance travelled in meters
            sb.append(String.valueOf(((int) ((double) clsSharedVariables.getTravelledDis())) * 1000));
            sb.append(SEPARATOR_COMMA);

            // Heading
            sb.append(clsSharedVariables.getCurHeading());
            sb.append(SEPARATOR_COMMA);

            // Number of satellites
            sb.append(clsSharedVariables.getCurNoSatelites());
            sb.append(SEPARATOR_COMMA);

            // HDOP
            sb.append(clsSharedVariables.getHdop());
            sb.append(SEPARATOR_COMMA);

            // Reserved fields
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            // Analog voltage
            sb.append((int) (clsSharedVariables.getAnaMainsVoltage()));
            sb.append(SEPARATOR_COMMA);

            // Digital input 1 status
            sb.append(clsSharedVariables.getDigInput1Status());
            sb.append(SEPARATOR_COMMA);

            // Tamper alert status
            if (clsSharedVariables.getTamperAlertStatus() == clsDefines.TAMPER_CLOSED) {
                sb.append("0");
            } else {
                sb.append("1");
            }
            sb.append(SEPARATOR_COMMA);

            // Overspeed start status
            if (overspeedstart == true) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(SEPARATOR_COMMA);

            // Overspeed end status
            if (overspeedend == true) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(SEPARATOR_COMMA);

            // Reserved fields
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            // Immobiliser status
            if (clsSharedVariables.getCurSpeed() > 5 || objHealth.get_ignition_status() == 1 || clsSharedVariables.getDigOut1Value() == 1) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(SEPARATOR_COMMA);

            // Mains status
            sb.append(objHealth.get_mains_status());
            sb.append(SEPARATOR_COMMA);

            // Digital input 2 status
            sb.append(clsSharedVariables.getDigInput2Status());
            sb.append(SEPARATOR_COMMA);

            // Reserved fields
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            // Ignition status
            sb.append(objHealth.get_ignition_status());
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            // Internal battery status
            if (clsSharedVariables.getAnaBatVoltage() < 7.0) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(SEPARATOR_COMMA);

            // Additional reserved fields
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            // Digital output 1 value
            sb.append(clsSharedVariables.getDigOut1Value());
            sb.append(SEPARATOR_COMMA);

            // Additional reserved fields
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            // HA status
            if (ha == true) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(SEPARATOR_COMMA);

            // HB status
            if (hb == true) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("1");
            sb.append(SEPARATOR_COMMA);
            //reserved
            sb.append("0");
            sb.append(SEPARATOR_COMMA);

            // External battery status
            sb.append((int) (clsSharedVariables.getAnaMainsVoltage() * 1000));
            sb.append(SEPARATOR_COMMA);

            // Internal battery voltage
            sb.append((int) (clsSharedVariables.getAnaBatVoltage() * 1000));
            sb.append(SEPARATOR_COMMA);

            // Calculate and append checksum
            checksum = calculateChecksumXOR(sb.toString());
            sb.append(SEPARATOR_COMMA);
            sb.append(clsSharedVariables.END_CHARACTER);
            //checksum
            sb.append(String.valueOf(checksum));
            sb.append("\r\n");

            // Send the packet
            clsGpsLinkedList objLst = new clsGpsLinkedList();
            objLst.addLastDataIp5(sb.toString().getBytes());

        } catch (Exception ex) {
            // Handle exceptions
        } finally {
            objHealth = null;
            sb = null;
        }
    }

    public synchronized void can_message_smc() {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        clsCanQueue objCanQueue = new clsCanQueue();
        String checksum;
        short canlen;

        try {
            // Start building the packet
            sb.append(clsSharedVariables.START_CHARACTER_SMC);
            sb.append(SEPARATOR_COMMA);
            //obuid
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            //event
            sb.append(clsSharedVariables.event_can_smc);
            sb.append(SEPARATOR_COMMA);

            // Latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);

            // Longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);

            // Date and time
            sb.append(sdfDateTime.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);

            // GPS status
            if (getGpsState() == GPS_FIXED) {
                sb.append("A");
            } else {
                sb.append("V");
            }
            sb.append(SEPARATOR_COMMA);

            // CAN message length
            canlen = PanCanConfig.objCanElectricalCnt;
            sb.append(canlen);
            sb.append(SEPARATOR_COMMA);

            // CAN message values
            for (int i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                sb.append(PanCanConfig.objCanElectrical[i].value);
                sb.append(SEPARATOR_COMMA);
            }
            checksum = calculateChecksumXOR(sb.toString());
            sb.append(clsSharedVariables.END_CHARACTER);
            //checksum
            sb.append(String.valueOf(checksum));
            sb.append("\r\n");
            objCanQueue.addDataIp5(sb.toString().getBytes());
            objCanQueue = null;
        } catch (Exception ex) {

        } finally {
            checksum = null;
            // checksum = null;
            sb = null;
        }
    }

    public synchronized void event_pkt_smc(int bdc_data_type) {
        StringBuilder sb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        String data = getCurRouteNo();
        String[] split_str = data.split(" ");
        String str = "up";
        clsHealthPacketStructure objhealth = new clsHealthPacketStructure();
        String checksum;
        int no_stops = getNoStopsRoute();
        try {
            //start character
            sb.append(clsSharedVariables.START_CHARACTER_SMC);
            sb.append(SEPARATOR_COMMA);
            //obuid
            sb.append(clsSharedVariables.getImeiNo());
            sb.append(SEPARATOR_COMMA);
            //event
            sb.append(clsSharedVariables.event_serial_smc);
            sb.append(SEPARATOR_COMMA);
            //latitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLatitude()));
            sb.append(SEPARATOR_COMMA);
            //longitude
            sb.append(decimalFormat.format(clsSharedVariables.getCurLongitude()));
            sb.append(SEPARATOR_COMMA);
            //date and time
            sb.append(sdfDateTime.format(cal.getTime()));
            sb.append(SEPARATOR_COMMA);
            //gps status
            //gps fix
            if (getGpsState() == GPS_FIXED) {
                sb.append("A");
            } else {
                sb.append("V");
            }
            sb.append(SEPARATOR_COMMA);
            //device index
            sb.append("0");
            sb.append(SEPARATOR_COMMA);
            //bdc parameters
            // $PMOBR,126,46,1,C,0,F0F2600059C3,123456
            if (bdc_data_type == clsSharedVariables.route_start) {
                sb.append("$PMOBR");
                sb.append(SEPARATOR_COMMA);
                //route id
                sb.append(getCurRouteNo());
                sb.append(SEPARATOR_COMMA);
                //destination stop id
                if (no_stops > 0) {
                    sb.append(gps_data[no_stops - 1].stop_name);
                } else {
                    sb.append("0");
                }
                sb.append(SEPARATOR_COMMA);
                //direction of route    
                if (data.isEmpty()) {
                    sb.append("0");
                } else {
                    if (split_str.length > 1) {
                        str = split_str[1].toUpperCase();
                        if (str.equals("UP")) {
                            sb.append("1");
                        } else {
                            sb.append("0");
                        }
                    } else {
                        sb.append("0");
                    }
                }
                sb.append(SEPARATOR_COMMA);
                //door status
                sb.append("O");
                sb.append(SEPARATOR_COMMA);
                //tacho meter reading
                sb.append(clsSharedVariables.getCurSpeed());
                sb.append(SEPARATOR_COMMA);
                //macid
                sb.append(clsSharedVariables.getMacAddress());
                sb.append(SEPARATOR_COMMA);
                //vehcile number
                sb.append(clsSharedVariables.getVechicleRegNo());
            } else if (bdc_data_type == clsSharedVariables.version_pkt) {
                sb.append("$PMOBV");
                sb.append(SEPARATOR_COMMA);
                //version of bdc
                sb.append(clsDefines.SW_FIRMWARE_VERSION);
                sb.append(SEPARATOR_COMMA);
                //version of route
                sb.append("1.0");
            } else if (bdc_data_type == clsSharedVariables.status_bdc) {
                sb.append("$PMOBS");
                sb.append(SEPARATOR_COMMA);
                sb.append("N");
            } else if (bdc_data_type == clsSharedVariables.dis_brd_status) {
                sb.append("$PMOPS");
                sb.append(SEPARATOR_COMMA);
                //display board status
                sb.append(objhealth.get_fd_status());
                sb.append(SEPARATOR_COMMA);
                //sd status
                sb.append(objhealth.get_sd_status());
                sb.append(SEPARATOR_COMMA);
                //rd status
                sb.append(objhealth.get_rd_status());
            }
            checksum = calculateChecksumXOR(sb.toString());
            sb.append(SEPARATOR_COMMA);
            sb.append(clsSharedVariables.END_CHARACTER);
            //checksum
            sb.append(checksum);
            sb.append("\r\n");
            clsLinkedList objLst = new clsLinkedList();
            objLst.addLastData5(sb.toString());
            objLst = null;
        } catch (Exception ex) {
        } finally {
            checksum = null;
            sb = null;
        }
    }

    public static String calculateChecksumXOR(String data) {
        int xorValue = 0;
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c != ',') { // Exclude the data separator character
                xorValue ^= (byte) c; // XOR operation with the ASCII value of the character
            }
        }
        return Integer.toHexString(xorValue).toUpperCase();
    }
}
