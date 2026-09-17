package obuits;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import static obuits.MainFrmIts.getVideoStarted;
import static obuits.MainFrmIts.imgCan;
import static obuits.MainFrmIts.imgTamper;
import static obuits.MainFrmIts.objCamView1;
import static obuits.MainFrmIts.objCamView2;
import static obuits.MainFrmIts.objCamView3;
import static obuits.MainFrmIts.objCamView4;

import static obuits.MainFrmIts.savedVideoState;
import static obuits.MainFrmIts.setPreVideoLiveState;
import static obuits.MainFrmIts.setVideoLiveState;
import static obuits.MainFrmIts.setVideoStarted;
import static obuits.PanCameraPlay.play_process_id;
import static obuits.PanCameraPlay.play_video_process;
import static obuits.PanCanConfig.objCanElectricalCnt;
import static obuits.PanCanConfig.objCanEngineCnt;
import static obuits.PanCanConfig.objCanOthersCnt;
import static obuits.PanCanConfig.objCanSafetyCnt;
import static obuits.PanCanConfig.objCanTransmitCnt;
import static obuits.PanCanDisplay.tabCanParamsElectrical;
import static obuits.PanCanDisplay.tabCanParamsEngine;
import static obuits.PanCanDisplay.tabCanParamsOthers;
import static obuits.PanCanDisplay.tabCanParamsSafety;
import static obuits.PanCanDisplay.tabCanParamsTransmit;
import static obuits.cls16833Protocols.SEPARATOR_COMMA;
import static obuits.clsDefines.CAN_BAUDRATE;
import static obuits.clsDefines.CAN_COMPORT;
import static obuits.clsDefines.OBU_BRD_TYPE;
import static obuits.clsDefines.OVER_HEAT_MAX_VALUE;
import static obuits.clsDefines.RASPBERRY_BOARD;

import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.TINKER_BOARD;
import static obuits.clsDefines.VIDEO_FROM_CAN;
import static obuits.clsDefines.can_filepath;
import static obuits.clsDefines.log_filepath;
import static obuits.clsDefines.xdotool;
import static obuits.clsInternalDisBrdMessage.fill_internal_details;
import static obuits.clsPacketTypes.CAN_OBU_ANALOG_PKT;
import static obuits.clsPacketTypes.CAN_OBU_CAN_PKT;
import static obuits.clsPacketTypes.CAN_OBU_DIGITAL_PKT;
import static obuits.clsPacketTypes.OBU_CAN_CONN_CHK_PKT;
import static obuits.clsPacketTypes.OBU_CAN_CONN_CHK_RES_PKT;
import static obuits.clsPacketTypes.OBU_CAN_CONN_RESET_PKT;
import static obuits.clsPacketTypes.OBU_CAN_CONN_RESET_RES_PKT;
import static obuits.clsPacketTypes.OBU_CAN_START_DATA;
import static obuits.clsPacketTypes.OBU_CAN_STOP_DATA;
import static obuits.clsSharedVariables.getCam4Enabled;
import static obuits.clsSharedVariables.getCamPortDetected;
import static obuits.clsSharedVariables.getCanDataEnabled;
import static obuits.clsSharedVariables.getCanDiagEnabled;
import static obuits.clsSharedVariables.getCanElectricalVhmdScreen;
import static obuits.clsSharedVariables.getCanEngineVhmdScreen;
import static obuits.clsSharedVariables.getCanLogEnabled;
import static obuits.clsSharedVariables.getCanOthersVhmdScreen;
import static obuits.clsSharedVariables.getCanSafetyVhmdScreen;
import static obuits.clsSharedVariables.getCanTransmitVhmdScreen;
import static obuits.clsSharedVariables.getCanUpdateTimeInterval;
import static obuits.clsSharedVariables.getCurActivity;
import static obuits.clsSharedVariables.getCurSec;
import static obuits.clsSharedVariables.getDisBrdName;
import static obuits.clsSharedVariables.getDoor1CloseVal;
import static obuits.clsSharedVariables.getDoor1OpenVal;
import static obuits.clsSharedVariables.getDoor1Pgn;
import static obuits.clsSharedVariables.getDoor1Spn;
import static obuits.clsSharedVariables.getDoor2CloseVal;
import static obuits.clsSharedVariables.getDoor2OpenVal;
import static obuits.clsSharedVariables.getDoor2Pgn;
import static obuits.clsSharedVariables.getDoor2Spn;
import static obuits.clsSharedVariables.getOBUID;
import static obuits.clsSharedVariables.getReverseCamPgn;
import static obuits.clsSharedVariables.getReverseCamSpn;
import static obuits.clsSharedVariables.getReverseCamSpn1;
import static obuits.clsSharedVariables.getReverseCamSpn2;
import static obuits.clsSharedVariables.getReverseCamVal;
import static obuits.clsSharedVariables.getReverseCameraOn;
import static obuits.clsSharedVariables.getVechicleRegNo;
import static obuits.clsSharedVariables.getVideoRecordPgn;
import static obuits.clsSharedVariables.getVideoRecordSpn;
import static obuits.clsSharedVariables.getVideoRecordStartVal;
import static obuits.clsSharedVariables.obu_high_volt_cnt;
import static obuits.clsSharedVariables.obu_low_volt_cnt;
import static obuits.clsSharedVariables.obu_over_heat_cnt;
import static obuits.clsSharedVariables.setAnaAdc0;
import static obuits.clsSharedVariables.setAnaAdc1;
import static obuits.clsSharedVariables.setAnaAdc2;
import static obuits.clsSharedVariables.setAnaAdc3;
import static obuits.clsSharedVariables.setAnaBatVoltage;
import static obuits.clsSharedVariables.setAnaMainsVoltage;
import static obuits.clsSharedVariables.setAnaTemp;
import static obuits.clsSharedVariables.setCamPortDetected;

import static obuits.clsSharedVariables.setReverseCameraOn;
import static obuits.clsSharedVariables.setStopReqMsg;

/**
 *
 * @author Sumitha
 */

public class clsCanSerialPort {
    //global variable declaration

    static final byte ALRM_ENABLE = 1;
    static final byte ALRM_DISABLE = 0;
    static SerialPort can_serialPort;
    static boolean res_pkt_came = false;
    static boolean can_data_coming = false;
    static boolean can_data_res_coming = false;
    static ReadCanDataFromSerialPort objCanSerial = null;
    static clsCanDataAllQueue objAllQueue = new clsCanDataAllQueue();
    static clsCanAlertDisplayQueue objAlertDisplayQueue = new clsCanAlertDisplayQueue();
    static boolean can_serial_connected = false;
    static final clsCanAlertDataDeque objCanAlertDataServer = new clsCanAlertDataDeque();
    Calendar cal;
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
    SimpleDateFormat sdf_time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
    static boolean msg_box_opened = false;
    static int chk_pkt_inc = 0;
    static short can_data_notcame_inc = 0;
    static short can_data_res_notcame_inc = 0;
    boolean can_image_set = false;
    static clsCanDataQueue objCanDataQueue = new clsCanDataQueue();
    clsCanParseDataThread objCanParseThread;
    Timer timer = null;
    TimerTask timerTask = null;
    static byte emergency_timer_inc = 0;
    final DecimalFormat decimalFormat = new DecimalFormat("##0.##");
    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();

    public clsCanSerialPort() {
        //check the board and declares the baurate according to the board type.
        if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
            CAN_COMPORT = clsDefines.RASPBERRY_CAN_COMPORT;
            CAN_BAUDRATE = 230400;

        } else if (OBU_BRD_TYPE == TINKER_BOARD) {
            CAN_COMPORT = clsDefines.TINKER_CAN_COMPORT;
            CAN_BAUDRATE = 115200;
        }
        //Starts the ReadCanDataFromSerialPort for checking connection
        if (objCanSerial == null) {
            objCanSerial = new ReadCanDataFromSerialPort();
            objCanSerial.start();
        }
        //Starts the clsCanParseDataThread for checking serial port data
        objCanParseThread = new clsCanParseDataThread();
        objCanParseThread.start();
    }

    private synchronized boolean getCanDataCame() {
        return can_data_coming;
    }

    private synchronized void setCanDataCame(boolean data) {
        can_data_coming = data;
    }

    private synchronized boolean getCanDataResCame() {
        return can_data_res_coming;
    }

    private synchronized void setCanDataResCame(boolean data) {
        can_data_res_coming = data;
    }

    private synchronized boolean getCanImage() {
        return can_image_set;
    }

    private synchronized void setCanImage(boolean data) {
        can_image_set = data;
    }
    // takes the pgn,spn,val from serial port and add it to the server

    private String fill_can_data_to_server() {
        int i = 0;
        StringBuilder sbCan = new StringBuilder();
        if (getCanUpdateTimeInterval() > 0) {
            for (i = 0; i < objCanElectricalCnt; i++) {
                if (PanCanConfig.objCanElectrical[i].transmit_enable == true && PanCanConfig.objCanElectrical[i].data_updated == true) {
                    sbCan = sbCan.delete(0, sbCan.length());
                    sbCan.append(PanCanConfig.objCanElectrical[i].pgn);
                    sbCan.append(",");
                    sbCan.append(PanCanConfig.objCanElectrical[i].spn);
                    sbCan.append(",");
                    sbCan.append((int) PanCanConfig.objCanElectrical[i].value);
                    sbCan.append("#");
                    //objQueue.addData(sbCan.toString());
                }
            }

            for (i = 0; i < objCanSafetyCnt; i++) {
                if (PanCanConfig.objCanSafety[i].transmit_enable == true && PanCanConfig.objCanSafety[i].data_updated == true) {
                    sbCan = sbCan.delete(0, sbCan.length());
                    sbCan.append(PanCanConfig.objCanSafety[i].pgn);
                    sbCan.append(",");
                    sbCan.append(PanCanConfig.objCanSafety[i].spn);
                    sbCan.append(",");
                    sbCan.append((int) PanCanConfig.objCanSafety[i].value);
                    sbCan.append("#");
                    //objQueue.addData(sbCan.toString());
                }
            }

            for (i = 0; i < objCanTransmitCnt; i++) {
                if (PanCanConfig.objCanTransmit[i].transmit_enable == true && PanCanConfig.objCanTransmit[i].data_updated == true) {
                    sbCan = sbCan.delete(0, sbCan.length());
                    sbCan.append(PanCanConfig.objCanTransmit[i].pgn);
                    sbCan.append(",");
                    sbCan.append(PanCanConfig.objCanTransmit[i].spn);
                    sbCan.append(",");
                    sbCan.append((int) PanCanConfig.objCanTransmit[i].value);
                    sbCan.append("#");
                    //objQueue.addData(sbCan.toString());
                }
            }

            for (i = 0; i < objCanEngineCnt; i++) {
                if (PanCanConfig.objCanEngine[i].transmit_enable == true && PanCanConfig.objCanEngine[i].data_updated == true) {
                    sbCan = sbCan.delete(0, sbCan.length());
                    sbCan.append(PanCanConfig.objCanEngine[i].pgn);
                    sbCan.append(",");
                    sbCan.append(PanCanConfig.objCanEngine[i].spn);
                    sbCan.append(",");
                    sbCan.append((int) PanCanConfig.objCanEngine[i].value);
                    sbCan.append("#");
                    // objQueue.addData(sbCan.toString());
                }
            }

            for (i = 0; i < objCanOthersCnt; i++) {
                if (PanCanConfig.objCanOthers[i].transmit_enable == true && PanCanConfig.objCanOthers[i].data_updated == true) {
                    sbCan = sbCan.delete(0, sbCan.length());
                    sbCan.append(PanCanConfig.objCanOthers[i].pgn);
                    sbCan.append(",");
                    sbCan.append(PanCanConfig.objCanOthers[i].spn);
                    sbCan.append(",");
                    sbCan.append((int) PanCanConfig.objCanOthers[i].value);
                    sbCan.append("#");
                    // objQueue.addData(sbCan.toString());
                }
            }
            if (sbCan.length() > 0) {
                sbCan.deleteCharAt(sbCan.length() - 1);
            } else {
                return null;
            }
            return sbCan.toString();
            // objQueue.addData(sbCan.toString());
        }
        return null;
    }

    //takes all the can data and send it to the server.
    private String fill_allcan_data_to_server() {
        int len = objAllQueue.getAllLength();
        String str;
        byte i = 0;
        StringBuilder sbCan = new StringBuilder();
        if (len > 10) {
            for (i = 0; i < len && i < 100; i++) {
                str = objAllQueue.removeAllData();
                if (str != null) {
                    sbCan.append(str);
                    sbCan.append("#");
                }
            }
            if (sbCan.length() > 0) {
                sbCan.deleteCharAt(sbCan.length() - 1);
            } else {
                return null;
            }
            return sbCan.toString();
        }
        return null;
    }
    //handles the uncaught exception

    class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_log_gprs_connectivity(" CAN Exceptiion  " + e.getMessage() + " Restarting ReadCanDataFromSerialPort Thread ");
            if (objCanSerial != null) {
                objCanSerial.isInterrupted();
                objCanSerial = null;
            }
            objCanSerial = new ReadCanDataFromSerialPort();
            objCanSerial.start();
        }
    }
    //tries to sonnect to can serial port and handles the events

    private class ReadCanDataFromSerialPort extends Thread {

        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

        @Override
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
            StringBuilder sbCanRead = new StringBuilder();
            clsReadFiles objReadFiles = new clsReadFiles();
            int can_data_send_inc = 0;
            int dig_led_inc = 0;
            String can_data;
            if (!clsSharedVariables.getCanEnabled()) {
                imgCan.setVisible(false);
            } else {
                imgCan.setVisible(true);
                imgCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_fail.png")));
            }
            while (true) {
                objwatDog.setCanSerial_watchdog_val((byte) 2);
                if (can_serial_connected == false) {
                    objwatDog.setCanSerial_watchdog_val((byte) 0);
                    if (clsSharedVariables.getCanEnabled()) {
                        setCanImage(false);
                        SwingUtilities.invokeLater(() -> {
                            imgCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_fail.png")));
                        });
                    }
                    if (connectCanSerialport() == false) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                        }

                        can_serial_connected = false;
                    } else {
                        can_serial_connected = true;

                    }

                } else {
                    if (clsSharedVariables.getCanEnabled()) {
                        if (getCanDataResCame() == false) {
                            can_data_res_notcame_inc++;
                            if (can_data_res_notcame_inc > 60) {
                                can_data_res_notcame_inc = 0;

                                setCanImage(false);
                                SwingUtilities.invokeLater(() -> {
                                    imgCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_fail.png")));
                                });
                                objHealth.set_can_status((byte) 0);
                                objReadFiles.init_can_params_value();
                            }
                        } else {
                            can_data_res_notcame_inc = 0;
                        }

                        if (getCanDataCame() == false) {
                            sbCanRead = sbCanRead.delete(0, sbCanRead.length());
                            sbCanRead.append("$");
                            sbCanRead.append(OBU_CAN_CONN_CHK_PKT);
                            sbCanRead.append(",");
                            sbCanRead.append(OBU_CAN_START_DATA);
                            sbCanRead.append("@");
                            if (canSerialWrite(sbCanRead.toString()) == true) {
                            }
                            if (clsSharedVariables.getDigOutPktCame() == true) {
                                send_digital_output();
                            }
                            if (objHealth.get_cam_status_updated()) {
                                send_led_status_output();
                            }
                            if (clsSharedVariables.getUsbHdPktCame()) {
                                send_usb_hd_output();
                            }

                            dig_led_inc++;
                            if (dig_led_inc > 4) {
                                dig_led_inc = 0;
                                send_digital_output();
                                send_led_status_output();
                                send_usb_hd_output();
                            }

                            //objReadFiles.write_log_low_memory_data("CAN CONN CHK PKT ");
                            can_data_notcame_inc++;
                            if (can_data_notcame_inc > 60) {
                                can_data_notcame_inc = 0;
                                setCanImage(false);
                                SwingUtilities.invokeLater(() -> {
                                    imgCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_fail.png")));
                                });

                                objHealth.set_can_status((byte) 0);

                                objReadFiles.init_can_params_value();
                                sbCanRead = sbCanRead.delete(0, sbCanRead.length());
                                sbCanRead.append("$");
                                sbCanRead.append(OBU_CAN_CONN_RESET_PKT);
                                sbCanRead.append("@");
                                canSerialWrite(sbCanRead.toString());
                                can_serial_connected = false;
                            }

                        } else {
                            setCanDataCame(false);

                            can_data_notcame_inc = 0;

                            if (getCanUpdateTimeInterval() > 0) {

                                if (can_data_send_inc++ >= getCanUpdateTimeInterval()) {
                                    can_data_send_inc = 0;
                                    can_data = fill_can_data_to_server();
                                    if (can_data != null) {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                            obj16833Pkts.can_preselected_params_pkt(can_data);
                                            obj16833Pkts = null;
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {

                                            gpsDriving objDriving = new gpsDriving();
                                            objDriving.can_preselected_params_pkt(can_data);
                                            objDriving = null;
                                        }

                                    }
                                }
                            } else {
                                can_data = fill_allcan_data_to_server();
                                if (can_data != null) {
                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                        obj16833Pkts.can_preselected_params_pkt(can_data);
                                        obj16833Pkts = null;
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        gpsDriving objDriving = new gpsDriving();
                                        objDriving.can_preselected_params_pkt(can_data);
                                        objDriving = null;
                                    }

                                }
                            }

                            if (clsSharedVariables.getDigOutPktCame() == true) {
                                send_digital_output();
                            }
                            if (clsSharedVariables.getUsbHdPktCame()) {
                                send_usb_hd_output();
                            }
                            if (objHealth.get_cam_status_updated() && clsSharedVariables.getLedDataResCame() == false) {
                                send_led_status_output();
                                objHealth.set_cam_status_updated(false);
                            }
                            dig_led_inc++;
                            if (dig_led_inc > 4) {
                                dig_led_inc = 0;
                                send_digital_output();
                                send_led_status_output();
                                send_usb_hd_output();
                            }
                        }
                    } else {
                        chk_pkt_inc++;
                        if (chk_pkt_inc > 2) {
                            chk_pkt_inc = 0;
                            sbCanRead = sbCanRead.delete(0, sbCanRead.length());
                            sbCanRead.append("$");
                            sbCanRead.append(OBU_CAN_CONN_CHK_PKT);
                            sbCanRead.append(",");
                            sbCanRead.append(OBU_CAN_START_DATA);
                            sbCanRead.append("@");
                            if (canSerialWrite(sbCanRead.toString()) == true) {
                            }
                        }
                        if (clsSharedVariables.getDigOutPktCame() == true) {
                            send_digital_output();
                        }
                        if (clsSharedVariables.getUsbHdPktCame()) {
                            send_usb_hd_output();
                        }
                        if (objHealth.get_cam_status_updated() && clsSharedVariables.getLedDataResCame() == false) {
                            send_led_status_output();
                            objHealth.set_cam_status_updated(false);
                        }

                        dig_led_inc++;
                        if (dig_led_inc > 4) {
                            dig_led_inc = 0;
                            send_digital_output();
                            send_led_status_output();
                            send_usb_hd_output();
                        }

                    }

                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
            }
        }

        void close_can_serial_port() {
            try {
                can_serial_connected = false;
                // if (can_serialPort != null) {
                can_serialPort.closePort();
                can_serialPort.removeEventListener();
                //  }
                can_serialPort = null;
            } catch (SerialPortException ex) {
                can_serialPort = null;
            } catch (Exception ex) {
                can_serialPort = null;
            }
        }
        int SCROLL_BUFFER_SIZE = 30;

        boolean connectCanSerialportStarting() {
            StringBuilder sbCanSer = new StringBuilder();
            byte i = 0;

            File f = new File("/sys/bus/usb-serial/drivers/ftdi_sio");
            File[] files = f.listFiles();
            String[] tty_usb_names = new String[5];
            byte length = 0;
            if (files != null) {
                for (File file : files) {
                    if (file.getName().contains("tty")) {
                        tty_usb_names[length] = "/dev/" + file.getName();
                        length++;
                    }
                }
                close_can_serial_port();

                for (i = 0; i < length; i++) {
                    try {

                        CAN_COMPORT = tty_usb_names[i];
                        // CAN_BAUDRATE = 230400;

                        if (getCanDiagEnabled()) {

                            try {
                                SwingUtilities.invokeLater(() -> {
                                    PanGpsDiag.txtData.append("CAN_COMPORT " + CAN_COMPORT + "\n");

                                    int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - SCROLL_BUFFER_SIZE;
                                    if (numLinesToTrunk > 0) {
                                        try {
                                            int posOfLastLineToTrunk = PanGpsDiag.txtData.getLineEndOffset(numLinesToTrunk - 1);
                                            PanGpsDiag.txtData.replaceRange("", 0, posOfLastLineToTrunk);
                                        } catch (Exception ex) {

                                        }

                                        try {
                                            System.gc();
                                        } catch (Exception ex) {

                                        }
                                    }
                                });
                            } catch (Exception ex) {
                            }
                        }

                        can_serialPort = (SerialPort) new SerialPort(CAN_COMPORT);
                        can_serialPort.openPort();
                        can_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                        can_serialPort.setParams(CAN_BAUDRATE,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);

                        can_serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

                        setCanDataResCame(false);
                        sbCanSer = sbCanSer.delete(0, sbCanSer.length());
                        sbCanSer.append("$");
                        sbCanSer.append(OBU_CAN_CONN_CHK_PKT);
                        sbCanSer.append(",");
                        sbCanSer.append(OBU_CAN_START_DATA);
                        sbCanSer.append("@");

                        canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");
                        Thread.sleep(1000);

                        canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");

                        Thread.sleep(1000);
                        canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");

                        can_serialPort.removeEventListener();

                    } catch (Exception e) {

                    }
                }
            }
            return false;
        }

        boolean connectCanSerialport() {
            StringBuilder sbCanSer = new StringBuilder();
            close_can_serial_port();

            try {

                setCamPortDetected(false);

                // CAN_BAUDRATE = 230400;
                can_serialPort = (SerialPort) new SerialPort(CAN_COMPORT);
                can_serialPort.openPort();
                can_serialPort.setParams(CAN_BAUDRATE,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                can_serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

                setCanDataResCame(false);
                sbCanSer = sbCanSer.delete(0, sbCanSer.length());
                sbCanSer.append("$");
                sbCanSer.append(OBU_CAN_CONN_CHK_PKT);
                sbCanSer.append(",");
                sbCanSer.append(OBU_CAN_START_DATA);
                sbCanSer.append("@");

                ////System.out.println("Can Serial 1");
                canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");
                Thread.sleep(1000);

                canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");
                if (getCamPortDetected()) {
                    return true;
                }
                Thread.sleep(1000);
                canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");
                if (getCamPortDetected()) {
                    return true;
                }
                canSerialWrite(sbCanSer.toString()); //"$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_START_DATA + ",@");
                if (getCamPortDetected()) {
                    return true;
                }
                // ////System.out.println("Can Serial 2");
                can_serialPort.removeEventListener();

            } catch (SerialPortException e) {
                try {
                    can_serial_connected = false;
                    can_serialPort.closePort();
                    can_serialPort = null;

                } catch (SerialPortException ex) {
                    Logger.getLogger(clsCanSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException e) {

            }

            return false;
        }

        //raw data
        private void write_can1_serial_raw_data_log_file(String str) {
            if (!clsSharedVariables.getHardDriveDetected()) {
                return;
            }
            File file;
            FileOutputStream f;
            PrintWriter pw;
            String path;
            StringBuilder sb = new StringBuilder(500);

            try {
                if (!log_filepath.exists()) {
                    log_filepath.mkdirs();
                    log_filepath.setWritable(true, false);
                    log_filepath.setReadable(true, false);
                }
                sb = sb.delete(0, sb.length());
                sb.append("OBITS-APP-CAN1RAWDATA-");
                sb.append(getOBUID());
                sb.append(".log");
                path = sb.toString();
                file = new File(log_filepath, path);
                f = new FileOutputStream(file, true);
                pw = new PrintWriter(f);
                sb = sb.delete(0, sb.length());
                sb.append(str);
                pw.println(sb.toString());
                pw.flush();
                if (file.length() > 100 * 1024 * 1024) { // 200 MB in bytes
                    file.delete(); // Delete the file if size exceeds 200 MB
                }
                pw.close();
                f.close();

            } catch (FileNotFoundException e) {
                // e.printStackTrace();
            } catch (IOException e) {
                // e.printStackTrace();
            } catch (Exception e) {
                // e.printStackTrace();
            } finally {
                f = null;
                pw = null;
                file = null;
                str = null;
                path = null;
                sb = null;
            }
        }
//raw data

        private class PortReader implements SerialPortEventListener {

            byte state = 0;
            String str;
            int i = 0;
            StringBuilder sb = new StringBuilder();
            // clsReadFiles objReadFiles = new clsReadFiles();
            cls16833Protocols obj16833Pkts = new cls16833Protocols();

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.isRXCHAR() && (event.getEventValue() > 0)) {

                    objwatDog.setCan_watchdog_val((byte) 2);
                    try {
                        str = can_serialPort.readString();

                        if (getCanDiagEnabled()) {
                            try {
                                SwingUtilities.invokeLater(() -> {

                                    PanGpsDiag.txtData.append(str.trim());
                                    PanGpsDiag.txtData.append("\n");
                                    //for raw data gathereing
                                    if (clsSharedVariables.getCanRawEnable()) {
                                        write_can1_serial_raw_data_log_file(str.trim() + "\n");
                                    }
                                    //for raw data gathereing
                                    int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - SCROLL_BUFFER_SIZE;
                                    if (numLinesToTrunk > 0) {
                                        try {
                                            int posOfLastLineToTrunk = PanGpsDiag.txtData.getLineEndOffset(numLinesToTrunk - 1);
                                            PanGpsDiag.txtData.replaceRange("", 0, posOfLastLineToTrunk);
                                        } catch (Exception ex) {
                                        }

                                        try {
                                            System.gc();
                                        } catch (Exception ex) {
                                        }
                                    }
                                });
                            } catch (Exception ex) {
                            }
                        }

                        for (i = 0; i < str.length(); i++) {
                            switch (state) {
                                case 0:
                                    if (str.charAt(i) == '$') {
                                        if (sb.length() > 0) {
                                            sb = sb.delete(0, sb.length());
                                        }
                                        state = 1;
                                    }
                                    break;
                                case 1:
                                    if (str.charAt(i) == '@') {
                                        setCanDataCame(true);
                                        setCanDataResCame(false);
                                        state = 2;
                                    } else {
                                        sb = sb.append(str.charAt(i));
                                        break;
                                    }
                                case 2:
                                    try {

                                        // if (getCamPortDetected() == false) {
                                        if (sb.toString().startsWith("82,1")) {
                                            res_pkt_came = true;
                                            setCamPortDetected(true);
                                            //}
                                        } else if (sb.toString().startsWith(clsPacketTypes.CAN_OBU_DIGITAL_RES_PKT + ",")) {
                                            res_pkt_came = true;
                                            clsSharedVariables.setDigOutPktCame(false);
                                            setCamPortDetected(true);
                                        } else if (sb.toString().startsWith(clsPacketTypes.OBU_CAN_CONN_CHK_RES_PKT + ",")) {
                                            res_pkt_came = true;
                                            //   setCanDataResCame(true);
                                            setCamPortDetected(true);
                                        } else if (sb.toString().startsWith(clsPacketTypes.CAN_LED_RES_PKT + ",")) {
                                            res_pkt_came = true;
                                            clsSharedVariables.setLedDataResCame(true);
                                            objHealth.set_cam_status_updated(false);
                                            setCamPortDetected(true);
                                        } else if (sb.toString().startsWith(clsPacketTypes.CAN_OBU_USB_HD_RES_PKT + ",")) {
                                            res_pkt_came = true;
                                            clsSharedVariables.setUsbHdPktResCame(true);
                                            setCamPortDetected(true);
                                        } else {
                                            setCamPortDetected(true);
                                            objCanDataQueue.addData(sb.toString());
                                            res_pkt_came = true;
                                        }

                                    } catch (Exception ex) {
                                    }
                                    state = 0;
                                    break;
                            }
                        }
                        Thread.sleep(5);

                    } catch (Exception ex) {
                        objwatDog.setCan_watchdog_val((byte) 0);
                    }
                }
            }

            private void write_can_raw_data_log_file(String str) {
                File file;
                FileOutputStream f;
                PrintWriter pw;
                String path;
                StringBuilder sb = new StringBuilder(500);
                long sec = getCurSec();
                Date today = new Date();;
                try {
                    if (!can_filepath.exists()) {
                        can_filepath.mkdirs();
                        can_filepath.setWritable(true, false);
                        can_filepath.setReadable(true, false);
                    }

                    // cal = Calendar.getInstance();
                    if (sec > 0) {
                        today = new Date(sec);
                        sdf.format(today);
                    }

                    sb = sb.delete(0, sb.length());
                    sb.append("OBITS-APP-CANRRAWLOG-");
                    sb.append(getOBUID());
                    sb.append("-");
                    sb.append(sdf.format(today)); //cal.getTime()));
                    sb.append(".log");
                    path = sb.toString();

                    file = new File(can_filepath, path);
                    f = new FileOutputStream(file, true);
                    pw = new PrintWriter(f);
                    sb = sb.delete(0, sb.length());
                    sb.append(sdf_time.format(today));//cal.getTime()));
                    sb.append("  , ");
                    sb.append(str);
                    pw.println(sb.toString());
                    pw.flush();
                    pw.close();
                    f.close();

                } catch (FileNotFoundException e) {
                    // e.printStackTrace();
                } catch (IOException e) {
                    // e.printStackTrace();
                } catch (Exception e) {
                    // e.printStackTrace();
                } finally {
                    f = null;
                    pw = null;
                    file = null;
                    str = null;
                    path = null;
                    sb = null;
                    sec = 0;
                    today = null;
                }
            }

        }
    }
    //this methos is used to write the string data to serial port

    private synchronized boolean canSerialWrite(String str) {
        try {
            if (str.length() > 0) {
                try {

                    can_serialPort.writeString(str);

                } catch (Exception ex) {

                    can_serial_connected = false;

                    if (objCanSerial == null) {
                        objCanSerial = new ReadCanDataFromSerialPort();
                        objCanSerial.start();
                    }
                    return false;
                }
                Thread.sleep(10);
                //read response from can wait for 5 seconds
                try {
                    for (byte read_inc_cnt = 0; read_inc_cnt < 100; read_inc_cnt++) {
                        if (res_pkt_came) {
                            break;
                        }
                        Thread.sleep(10);
                    }
                    if (res_pkt_came) {
                        res_pkt_came = false;
                        return true;
                    }
                } catch (Exception ex) {
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
    //this method will construct the reset packet and write it to can serial port to rest the CAN.

    public void can_reset_serialport() {
        StringBuilder sbCanSer = new StringBuilder();
        sbCanSer.append("$");
        sbCanSer.append(OBU_CAN_CONN_RESET_PKT);
        sbCanSer.append("@");
        canSerialWrite(sbCanSer.toString());
        clsSharedVariables.setResetCanDataResCame(false);
    }

    //adds the electrical data to the gui tab
    private synchronized void can_display_electrical_data(int i) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                try {

                    if (PanCanConfig.objCanElectrical[i].unit.equals("bit")) {
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].name, i, 0);
                        tabCanParamsElectrical.setValueAt((int) (PanCanConfig.objCanElectrical[i].value), i, 1);
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].unit, i, 2);
                    } else {
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].name, i, 0);
                        tabCanParamsElectrical.setValueAt(decimalFormat.format(PanCanConfig.objCanElectrical[i].value), i, 1);
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].unit, i, 2);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }
    
    //adds the safety data to the gui tab

    private synchronized void can_display_safety_data(int i) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                try {

                    if (PanCanConfig.objCanSafety[i].unit.equals("bit")) {
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].name, i, 0);
                        tabCanParamsSafety.setValueAt((int) (PanCanConfig.objCanSafety[i].value), i, 1);
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].unit, i, 2);
                    } else {
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].name, i, 0);
                        tabCanParamsSafety.setValueAt(decimalFormat.format(PanCanConfig.objCanSafety[i].value), i, 1);
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].unit, i, 2);
                    }

                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }
    //adds the transmit data to the gui tab

    private synchronized void can_display_transmit_data(int i) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                try {

                    if (PanCanConfig.objCanTransmit[i].unit.equals("bit")) {
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].name, i, 0);
                        tabCanParamsTransmit.setValueAt((int) (PanCanConfig.objCanTransmit[i].value), i, 1);
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].unit, i, 2);
                    } else {
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].name, i, 0);
                        tabCanParamsTransmit.setValueAt(decimalFormat.format(PanCanConfig.objCanTransmit[i].value), i, 1);
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].unit, i, 2);
                    }

                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }
    //adds the engine data to the gui tab

    private synchronized void can_display_engine_data(int i) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                try {

                    if (PanCanConfig.objCanEngine[i].unit.equals("bit")) {
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].name, i, 0);
                        tabCanParamsEngine.setValueAt((int) (PanCanConfig.objCanEngine[i].value), i, 1);
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].unit, i, 2);

                    } else {
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].name, i, 0);
                        tabCanParamsEngine.setValueAt(decimalFormat.format(PanCanConfig.objCanEngine[i].value), i, 1);
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].unit, i, 2);
                    }
                } catch (Exception e) {
                    //   e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }
    //adds the other data to the gui tab

    private synchronized void can_display_others_data(int i) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                try {
                    if (PanCanConfig.objCanOthers[i].unit.equals("bit")) {

                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].name, i, 0);
                        tabCanParamsOthers.setValueAt((int) (PanCanConfig.objCanOthers[i].value), i, 1);
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].unit, i, 2);
                    } else {
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].name, i, 0);
                        tabCanParamsOthers.setValueAt(decimalFormat.format(PanCanConfig.objCanOthers[i].value), i, 1);
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].unit, i, 2);
                    }

                } catch (Exception e) {
                    //   e.printStackTrace();
                }
            }
        };
        sw1.execute();

    }
    //handles the uncaught exception for clsCanparseThread 

    class ParseCanExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_log_gprs_connectivity(" CAN Parse exc " + e.getMessage() + " Restarting Can Parse Thread ");

            if (objCanParseThread != null) {
                objCanParseThread.isInterrupted();
                objCanParseThread = null;
            }
            objCanParseThread = new clsCanParseDataThread();
            objCanParseThread.start();
        }
    }

    class clsCanParseDataThread extends Thread {

        public clsCanParseDataThread() {
            super();
        }
        byte prev_video_state = 0;

        @Override
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(new ParseCanExceptionHandler());
            String[] split_str;
            String data = "";
            byte can_bytes[] = new byte[8];
            byte pkt_type;//= CAN_OBU_CAN_PKT;
            String canid_str;
            String pgn_str;
            long can_id = 0;
            int pgn_val = 0;
            int spn_val = 0;
            byte can_bytes_length = 0;
            short i = 0;
            StringBuilder sbCan = new StringBuilder();
            double val = 0;
            double prev_door1_val = 0;
            double prev_door2_val = 0;
            int can_que_cnt = 0;
            double bat_voltage_value = 0.0;
            double mains_voltage_value = 0.0;
            double temp_value = 0.0;
            boolean high_volt_came = false;
            boolean low_volt_came = false;
            boolean high_temp_came = false;
            clsReadFiles objReadFiles = new clsReadFiles();
            clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
            byte battery_connect_state = clsDefines.BATTERY_STATE_INITIAL;
            int img_cnt = 0;
            while (true) {
                try {
                    objwatDog.setCanQueue_watchdog_val((byte) 2);
                    try {
                        can_que_cnt = objCanDataQueue.getLength();
                        if (can_que_cnt > 0) {
                            write_open_can_log();

                            for (int inc = 0; inc < can_que_cnt; inc++) {
                                data = objCanDataQueue.removeData();
                                split_str = data.split(",");
                                pkt_type = Byte.parseByte(split_str[0]);
                                switch (pkt_type) {
                                    case CAN_OBU_CAN_PKT: {
                                        try {
                                            setCanDataResCame(true);
                                            if (!clsSharedVariables.getCanEnabled()) {
                                                break;
                                            }
                                            if (getCanImage() == false || img_cnt++ > 500) {
                                                img_cnt = 0;
                                                setCanImage(true);
                                                SwingUtilities.invokeLater(() -> {
                                                    imgCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_ok.png")));
                                                });
                                                objHealth.set_can_status((byte) 1);
                                            }

                                            can_id = Long.parseLong(split_str[1]);

                                            can_bytes_length = Byte.parseByte(split_str[2]);

                                             for (i = 0; i < can_bytes_length; i++) {
                                                can_bytes[i] = (byte) (Short.parseShort(split_str[i + 3]));

                                            }
                                            canid_str = Long.toHexString(can_id);
                                            if (canid_str.length() == 7) {
                                                pgn_str = canid_str.substring(1, 5);
                                                pgn_str = "0x" + pgn_str;
                                                pgn_val = Integer.decode(pgn_str);
                                            } else {
                                                pgn_str = canid_str.substring(2, 6);
                                                pgn_str = "0x" + pgn_str;
                                                pgn_val = Integer.decode(pgn_str);
                                            }

                                            //Engine
                                            for (i = 0; i < PanCanConfig.objCanEngineCnt; i++) {
                                                if (PanCanConfig.objCanEngine[i].can_id == can_id) {//&& PanCanConfig.objCanEngine[i].pgn == pgn_val) {

                                                    if (PanCanConfig.objCanEngine[i].byte_pos > 0) {
                                                        if (PanCanConfig.objCanEngine[i].bit_length <= 8) {
                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanEngine[i].byte_pos - 1], (byte) 0, (byte) 1,
                                                                    (byte) PanCanConfig.objCanEngine[i].bit_pos, PanCanConfig.objCanEngine[i].bit_length, PanCanConfig.objCanEngine[i].reverse);
                                                            spn_val = PanCanConfig.objCanEngine[i].spn;
                                                        } else if (PanCanConfig.objCanEngine[i].bit_length <= 16) {
                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanEngine[i].byte_pos - 1], (byte) can_bytes[PanCanConfig.objCanEngine[i].byte_pos], (byte) 2,
                                                                    (byte) PanCanConfig.objCanEngine[i].bit_pos, PanCanConfig.objCanEngine[i].bit_length, PanCanConfig.objCanEngine[i].reverse);
                                                            spn_val = PanCanConfig.objCanEngine[i].spn;
                                                        } else {
                                                            val = get_bit_pos_value_int(can_bytes[PanCanConfig.objCanEngine[i].byte_pos - 1], (byte) can_bytes[PanCanConfig.objCanEngine[i].byte_pos], can_bytes[PanCanConfig.objCanEngine[i].byte_pos + 1], (byte) can_bytes[PanCanConfig.objCanEngine[i].byte_pos + 2], (byte) 4,
                                                                    (byte) PanCanConfig.objCanEngine[i].bit_pos, PanCanConfig.objCanEngine[i].bit_length, PanCanConfig.objCanEngine[i].reverse);
                                                            spn_val = PanCanConfig.objCanEngine[i].spn;
                                                        }

                                                        if (PanCanConfig.objCanEngine[i].factor > 0.0) {
                                                            val = (val * PanCanConfig.objCanEngine[i].factor + PanCanConfig.objCanEngine[i].offset);
                                                        }

                                                        PanCanConfig.objCanEngine[i].value = val;
                                                        PanCanConfig.objCanEngine[i].data_updated = true;
                                                        try {
                                                            if (PanCanConfig.objCanEngine[i].transmit_enable == true && getCanDataEnabled()) {

                                                                if (getCanUpdateTimeInterval() == 0) {
                                                                    sbCan = sbCan.delete(0, sbCan.length());
                                                                    sbCan.append(pgn_val);
                                                                    sbCan.append(",");
                                                                    sbCan.append(spn_val);
                                                                    sbCan.append(",");
                                                                    sbCan.append((int) val);
                                                                    objAllQueue.addAllData(sbCan.toString());
                                                                }
                                                            }
                                                            if (PanCanConfig.objCanEngine[i].display_enable && getCanEngineVhmdScreen() == true) {
                                                                can_display_engine_data(i);
                                                            }
                                                            if (PanCanConfig.objCanEngine[i].log_enable == true && getCanLogEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(PanCanConfig.objCanEngine[i].name);
                                                                sbCan.append(",");
                                                                sbCan.append(val);
                                                                write_can_data_log_file(sbCan.toString());
                                                                // write_can_data_log_file(PanCanConfig.objCanEngine[i].name + "," + val);
                                                            }
                                                            if (PanCanConfig.objCanEngine[i].alert_enable == true) {
                                                                if (val >= PanCanConfig.objCanEngine[i].min && (val <= PanCanConfig.objCanEngine[i].threshold)) {
                                                                    if (PanCanConfig.objCanEngine[i].alarm_state == ALRM_ENABLE) {
                                                                        // objAlertQueue.addData(PanCanConfig.objCanEngine[i].alarm_id + "," + ALRM_DISABLE);
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanEngine[i].name);

                                                                        // cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanEngine[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanEngine[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); // cal_time + "*" + PanCanConfig.objCanEngine[i].name + " < " + PanCanConfig.objCanEngine[i].min);

                                                                        } else if (val > PanCanConfig.objCanEngine[i].threshold && val < PanCanConfig.objCanEngine[i].max) {

                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanEngine[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanEngine[i].name + " < " + PanCanConfig.objCanEngine[i].max);

                                                                        } else if (val > PanCanConfig.objCanEngine[i].max) {

                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanEngine[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanEngine[i].name + " < " + PanCanConfig.objCanEngine[i].max);

                                                                        }
                                                                        PanCanConfig.objCanEngine[i].alarm_state = ALRM_DISABLE;

                                                                        if (PanCanConfig.objCanEngine[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanEngine[i].alarm_id, ALRM_DISABLE);

                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanEngine[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {

                                                                    if (PanCanConfig.objCanEngine[i].alarm_state == ALRM_DISABLE) {

                                                                        PanCanConfig.objCanEngine[i].alarm_state = ALRM_ENABLE;
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanEngine[i].name);

                                                                        //  cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanEngine[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanEngine[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());

                                                                            //objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanEngine[i].name + " < " + PanCanConfig.objCanEngine[i].min);
                                                                        } else if (val > PanCanConfig.objCanEngine[i].threshold && val < PanCanConfig.objCanEngine[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanEngine[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());

                                                                            //  objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanEngine[i].name + " < " + PanCanConfig.objCanEngine[i].max);
                                                                        } else if (val > PanCanConfig.objCanEngine[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanEngine[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());

                                                                            //  objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanEngine[i].name + " < " + PanCanConfig.objCanEngine[i].max);
                                                                        }

                                                                        if (PanCanConfig.objCanEngine[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanEngine[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanEngine[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                        }
                                                                        cal = null;
                                                                    }

                                                                }
                                                            }
                                                        } catch (Exception ex) {

                                                        }

                                                        //return;
                                                    }
                                                }
                                            }

                                            //Transmit
                                            for (i = 0; i < PanCanConfig.objCanTransmitCnt; i++) {
                                                if (PanCanConfig.objCanTransmit[i].can_id == can_id) {// && PanCanConfig.objCanTransmit[i].pgn == pgn_val) {

                                                    if (PanCanConfig.objCanTransmit[i].byte_pos > 0) {
                                                        if (PanCanConfig.objCanTransmit[i].bit_length <= 8) {

                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanTransmit[i].byte_pos - 1], (byte) 0, (byte) 1,
                                                                    (byte) PanCanConfig.objCanTransmit[i].bit_pos, PanCanConfig.objCanTransmit[i].bit_length, PanCanConfig.objCanTransmit[i].reverse);
                                                            spn_val = PanCanConfig.objCanTransmit[i].spn;
                                                            
                                                        } else if (PanCanConfig.objCanTransmit[i].bit_length <= 16) {
                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanTransmit[i].byte_pos - 1], (byte) can_bytes[PanCanConfig.objCanTransmit[i].byte_pos], (byte) 2,
                                                                    (byte) PanCanConfig.objCanTransmit[i].bit_pos, PanCanConfig.objCanTransmit[i].bit_length, PanCanConfig.objCanTransmit[i].reverse);
                                                            spn_val = PanCanConfig.objCanTransmit[i].spn;
                                                        } else {
                                                            val = get_bit_pos_value_int(can_bytes[PanCanConfig.objCanTransmit[i].byte_pos - 1], (byte) can_bytes[PanCanConfig.objCanTransmit[i].byte_pos], (byte) can_bytes[PanCanConfig.objCanTransmit[i].byte_pos + 1], (byte) can_bytes[PanCanConfig.objCanTransmit[i].byte_pos + 2], (byte) 4,
                                                                    (byte) PanCanConfig.objCanTransmit[i].bit_pos, PanCanConfig.objCanTransmit[i].bit_length, PanCanConfig.objCanTransmit[i].reverse);
                                                            spn_val = PanCanConfig.objCanTransmit[i].spn;
                                                        }

                                                        if (PanCanConfig.objCanTransmit[i].factor > 0.0) {
                                                            val = (val * PanCanConfig.objCanTransmit[i].factor + PanCanConfig.objCanTransmit[i].offset);
                                                        }

                                                        PanCanConfig.objCanTransmit[i].value = val;
                                                        PanCanConfig.objCanTransmit[i].data_updated = true;
                                                        if (PanCanConfig.objCanTransmit[i].pgn == getReverseCamPgn() // JBM_D_PGN_TRAN_CURRENT_GEAR
                                                                && (PanCanConfig.objCanTransmit[i].spn == getReverseCamSpn() || PanCanConfig.objCanTransmit[i].spn == getReverseCamSpn1() || PanCanConfig.objCanTransmit[i].spn == getReverseCamSpn2())) {//JBM_D_SPN_TRAN_CURRENT_GEAR) {
                                                            // System.out.println("Current Gear " + val);
                                                            if (val == getReverseCamVal() && getCam4Enabled()) {
                                                                // start reverse camerra

                                                                if (getReverseCameraOn() == false) {
                                                                    setReverseCameraOn(true);
                                                                    //    showReverseCameraUI();
                                                                    try {
                                                                        // System.out.println("Current Gear1 " + val);
                                                                        setVideoLiveState((byte) 0);
                                                                        stop_video();
                                                                        // setVideoEnableStarted(true);
                                                                        setCanDataCame(true);
                                                                        setVideoStarted(true);
                                                                        setVideoLiveState((byte) 4);

                                                                        objCamView4 = new clsCamera4View(false);
                                                                        objCamView4.start();
                                                                        System.out.println("reverse gear activated and cam4 opened");
                                                                        setCanDataCame(true);
                                                                    } catch (Exception ex) {
                                                                        //System.out.println("Exception occurred" + ex.getMessage());
                                                                    }
                                                                }
                                                            } else {
                                                                 if (getReverseCameraOn() == true) {
                                                                    setReverseCameraOn(false);
                                                                    setVideoLiveState((byte) 0);
                                                                    setPreVideoLiveState((byte) 0);
                                                                    // showCameraUI();
                                                                    stop_video();
                                                                    System.out.println("reverse gear stoppeddd");

                                                                    if ((clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) && (xdotool == true)) {
                                                                        killGstreamer();
                                                                    } else if (clsDefines.omxplayer_found == true) {
                                                                        Process p1 = Runtime.getRuntime().exec("sudo pkill omxplayer");
                                                                        p1.waitFor();
                                                                        p1.destroy();
                                                                        p1 = null;
                                                                    } else {
                                                                        Process p = Runtime.getRuntime().exec("sudo pkill ffplay");
                                                                        p.waitFor();
                                                                        p.destroy();
                                                                        p = null;
                                                                    }
                                                                    start_video();
                                                                }
                                                            }
                                                        }

                                                        if (PanCanConfig.objCanTransmit[i].display_enable && getCanTransmitVhmdScreen() == true) {
                                                            can_display_transmit_data(i);
                                                        }

                                                        try {
                                                            if (PanCanConfig.objCanTransmit[i].transmit_enable == true && getCanDataEnabled()) {
                                                                if (getCanUpdateTimeInterval() == 0) {
                                                                    sbCan = sbCan.delete(0, sbCan.length());
                                                                    sbCan.append(pgn_val);
                                                                    sbCan.append(",");
                                                                    sbCan.append(spn_val);
                                                                    sbCan.append(",");
                                                                    sbCan.append((int) val);
                                                                    objAllQueue.addAllData(sbCan.toString());
                                                                }
                                                            }
                                                            if (PanCanConfig.objCanTransmit[i].log_enable == true && getCanLogEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(PanCanConfig.objCanTransmit[i].name);
                                                                sbCan.append(",");
                                                                sbCan.append(val);
                                                                write_can_data_log_file(sbCan.toString());
                                                                // write_can_data_log_file(PanCanConfig.objCanTransmit[i].name + "," + val);
                                                            }
                                                            if (PanCanConfig.objCanTransmit[i].alert_enable == true) {
                                                                if (val >= PanCanConfig.objCanTransmit[i].min && (val <= PanCanConfig.objCanTransmit[i].threshold)) {
                                                                    if (PanCanConfig.objCanTransmit[i].alarm_state == ALRM_ENABLE) {
                                                                        // objAlertQueue.addData(PanCanConfig.objCanTransmit[i].alarm_id + "," + ALRM_DISABLE);
                                                                        PanCanConfig.objCanTransmit[i].alarm_state = ALRM_DISABLE;
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanTransmit[i].name);
                                                                        //cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanTransmit[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanTransmit[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanTransmit[i].name + " < " + PanCanConfig.objCanTransmit[i].min);
                                                                        } else if (val > PanCanConfig.objCanTransmit[i].threshold || val < PanCanConfig.objCanTransmit[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanTransmit[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanTransmit[i].name + " > " + PanCanConfig.objCanTransmit[i].max);
                                                                        } else if (val > PanCanConfig.objCanTransmit[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanTransmit[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanTransmit[i].name + " > " + PanCanConfig.objCanTransmit[i].max);
                                                                        }

                                                                        if (PanCanConfig.objCanTransmit[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanTransmit[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanTransmit[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {

                                                                    if (PanCanConfig.objCanTransmit[i].alarm_state == ALRM_DISABLE) {
                                                                        // objAlertQueue.addData(PanCanConfig.objCanTransmit[i].alarm_id + "," + ALRM_ENABLE);
                                                                        PanCanConfig.objCanTransmit[i].alarm_state = ALRM_ENABLE;
                                                                        cal = Calendar.getInstance();
                                                                        if (getCurSec() > 0) {
                                                                            cal.setTimeInMillis(getCurSec());
                                                                        }
                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanTransmit[i].name);

                                                                        // cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanTransmit[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanTransmit[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanTransmit[i].name + " < " + PanCanConfig.objCanTransmit[i].min);
                                                                        } else if (val > PanCanConfig.objCanTransmit[i].threshold && val < PanCanConfig.objCanTransmit[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanTransmit[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanTransmit[i].name + " > " + PanCanConfig.objCanTransmit[i].max);
                                                                        } else if (val > PanCanConfig.objCanTransmit[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanTransmit[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanTransmit[i].name + " > " + PanCanConfig.objCanTransmit[i].max);
                                                                        }

                                                                        if (PanCanConfig.objCanTransmit[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanTransmit[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanTransmit[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                        }
                                                                        cal = null;
                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception ex) {

                                                        }
                                                    }
                                                }
                                            }

                                            //Electrical
                                            for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                                                if (PanCanConfig.objCanElectrical[i].can_id == can_id) {// && PanCanConfig.objCanElectrical[i].pgn == pgn_val) {
                                                    if (PanCanConfig.objCanElectrical[i].byte_pos > 0) {

                                                        if (PanCanConfig.objCanElectrical[i].bit_length <= 8) {

                                                            val = get_bit_pos_value(
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos - 1],
                                                                    (byte) 0,
                                                                    (byte) 1,
                                                                    (byte) PanCanConfig.objCanElectrical[i].bit_pos,
                                                                    PanCanConfig.objCanElectrical[i].bit_length,
                                                                    PanCanConfig.objCanElectrical[i].reverse);
                                                        } else if (PanCanConfig.objCanElectrical[i].bit_length <= 16) {

                                                            val = get_bit_pos_value(
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos - 1],
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos],
                                                                    (byte) 2,
                                                                    (byte) PanCanConfig.objCanElectrical[i].bit_pos,
                                                                    PanCanConfig.objCanElectrical[i].bit_length,
                                                                    PanCanConfig.objCanElectrical[i].reverse);
                                                        } else if (PanCanConfig.objCanElectrical[i].bit_length <= 24) {

                                                            val = get_24bit_value(
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos - 1],
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos],
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos + 1],
                                                                    PanCanConfig.objCanElectrical[i].bit_pos,
                                                                    PanCanConfig.objCanElectrical[i].reverse);
                                                        } else {

                                                            val = get_bit_pos_value_int(
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos - 1],
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos],
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos + 1],
                                                                    can_bytes[PanCanConfig.objCanElectrical[i].byte_pos + 2],
                                                                    (byte) 4,
                                                                    (byte) PanCanConfig.objCanElectrical[i].bit_pos,
                                                                    PanCanConfig.objCanElectrical[i].bit_length,
                                                                    PanCanConfig.objCanElectrical[i].reverse);
                                                        }

                                                        if (PanCanConfig.objCanElectrical[i].factor > 0.0) {

                                                            val = (val * PanCanConfig.objCanElectrical[i].factor + PanCanConfig.objCanElectrical[i].offset);
                                                        }
                                                        PanCanConfig.objCanElectrical[i].value = val;
                                                        PanCanConfig.objCanElectrical[i].data_updated = true;
                                                        try {
                                                            if (PanCanConfig.objCanElectrical[i].pgn == clsSharedVariables.getStopReqPgn() // JBM_D_PGN_TRAN_CURRENT_GEAR
                                                                    && PanCanConfig.objCanElectrical[i].spn == clsSharedVariables.getStopReqSpn()) {//JBM_D_SPN_TRAN_CURRENT_GEAR) {
                                                                // show_message_dialogbox("Current Gear " + val);
                                                                if (val == clsSharedVariables.getStopReqVal()) {
                                                                    // if (PanCanConfig.objCanElectrical[i].name.equalsIgnoreCase("Emergency stop / status")) {
                                                                    //if (val == 1) //{
                                                                    //{
                                                                    if (clsSharedVariables.getStopReqMsg() == false) {
                                                                        setStopReqMsg(true);
                                                                        setCanDataCame(true);
                                                                        if (getDisBrdName() == SUMITH_DISBRD) {
                                                                            fill_internal_details("STOP REQUESTED", clsDefines.INTDB_DATA_NOT_SAVE);
                                                                        }
                                                                        setCanDataCame(true);
                                                                        clsSharedVariables.objAudQue.addData("/canstoprequested.wav");
                                                                        try {
                                                                            SwingUtilities.invokeLater(() -> {
                                                                                MainFrmIts.imgCanEmergency.setVisible(true);
                                                                            });
                                                                        } catch (Exception ex) {
                                                                        }
                                                                        emergency_timer_inc = 0;
                                                                        startTimer();
                                                                        setCanDataCame(true);
                                                                    }

                                                                } else {
                                                                    setStopReqMsg(false);
                                                                }
                                                            }
                                                            //can vehicle speed for olectra vehicle

                                                            if (PanCanConfig.objCanElectrical[i].pgn == clsSharedVariables.getCanSpeedPgn()// JBM_D_PGN_TRAN_CURRENT_GEAR
                                                                    && PanCanConfig.objCanElectrical[i].spn == clsSharedVariables.getCanSpeedSpn()) {//JBM_D_SPN_TRAN_CURRENT_GEAR) {
                                                                // show_message_dialogbox("Current Gear " + val);
                                                                try {
                                                                    clsSharedVariables.setCanVehicleSpeed((byte) val);
                                                                } catch (Exception ex) {

                                                                }

                                                            }
                                                            if (PanCanConfig.objCanElectrical[i].transmit_enable == true && getCanDataEnabled()) {
                                                                try {
                                                                    sbCan = sbCan.delete(0, sbCan.length());
                                                                    sbCan.append(pgn_val);
                                                                    sbCan.append(",");
                                                                    sbCan.append(spn_val);
                                                                    sbCan.append(",");
                                                                    sbCan.append((int) val);
                                                                    if (getCanUpdateTimeInterval() == 0) {
                                                                        objAllQueue.addAllData(sbCan.toString());
                                                                    }

                                                                } catch (Exception ex) {
                                                                }
                                                            }
                                                            if (PanCanConfig.objCanElectrical[i].log_enable == true && getCanLogEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(PanCanConfig.objCanElectrical[i].name);
                                                                sbCan.append(",");
                                                                sbCan.append(val);
                                                                write_can_data_log_file(sbCan.toString());
                                                            }

                                                            if (PanCanConfig.objCanElectrical[i].display_enable && getCanElectricalVhmdScreen() == true) {
                                                                // if (val >= PanCanConfig.objCanElectrical[i].min && val <= PanCanConfig.objCanElectrical[i].max) {
                                                                can_display_electrical_data(i);
                                                                // }
                                                            }

                                                            if (PanCanConfig.objCanElectrical[i].alert_enable == true) {
                                                                if (val >= PanCanConfig.objCanElectrical[i].min && (val <= PanCanConfig.objCanElectrical[i].threshold)) {

                                                                    if (PanCanConfig.objCanElectrical[i].alarm_state == ALRM_ENABLE) {
                                                                        //objAlertQueue.addData(PanCanConfig.objCanElectrical[i].alarm_id + "," + ALRM_DISABLE);
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanElectrical[i].name);
                                                                        //cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanElectrical[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanElectrical[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanElectrical[i].name + " < " + PanCanConfig.objCanElectrical[i].min);
                                                                        } else if (val > PanCanConfig.objCanElectrical[i].threshold && val < PanCanConfig.objCanElectrical[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanElectrical[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanElectrical[i].name + " > " + PanCanConfig.objCanElectrical[i].max);
                                                                        } else if (val > PanCanConfig.objCanElectrical[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanElectrical[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanElectrical[i].name + " > " + PanCanConfig.objCanElectrical[i].max);
                                                                        }

                                                                        PanCanConfig.objCanElectrical[i].alarm_state = ALRM_DISABLE;

                                                                        if (PanCanConfig.objCanElectrical[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanElectrical[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanElectrical[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                        }
                                                                        cal = null;
                                                                    } else if (PanCanConfig.objCanElectrical[i].alarm_state == ALRM_DISABLE) {

                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanElectrical[i].name);

                                                                        if (val < PanCanConfig.objCanElectrical[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanElectrical[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanElectrical[i].name + " < " + PanCanConfig.objCanElectrical[i].min);
                                                                        } else if (val > PanCanConfig.objCanElectrical[i].threshold && val < PanCanConfig.objCanElectrical[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanElectrical[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanElectrical[i].name + " > " + PanCanConfig.objCanElectrical[i].max);
                                                                        } else if (val > PanCanConfig.objCanElectrical[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanElectrical[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanElectrical[i].name + " > " + PanCanConfig.objCanElectrical[i].max);
                                                                        }
                                                                        cal = null;
                                                                        //  objAlertQueue.addData(PanCanConfig.objCanElectrical[i].alarm_id + "," + ALRM_ENABLE);
                                                                        PanCanConfig.objCanElectrical[i].alarm_state = ALRM_ENABLE;

                                                                        if (PanCanConfig.objCanElectrical[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanElectrical[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanElectrical[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception ex) {

                                                        }
                                                        //return;
                                                    }
                                                }
                                            }

                                            //safety
                                            for (i = 0; i < PanCanConfig.objCanSafetyCnt; i++) {
                                                if (PanCanConfig.objCanSafety[i].can_id == can_id) {// && PanCanConfig.objCanSafety[i].pgn == pgn_val) {

                                                    if (PanCanConfig.objCanSafety[i].byte_pos > 0) {
                                                        if (PanCanConfig.objCanSafety[i].bit_length <= 8) {

                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanSafety[i].byte_pos - 1], (byte) 0, (byte) 1,
                                                                    (byte) PanCanConfig.objCanSafety[i].bit_pos, PanCanConfig.objCanSafety[i].bit_length, PanCanConfig.objCanSafety[i].reverse);
                                                            spn_val = PanCanConfig.objCanSafety[i].spn;
                                                        } else if (PanCanConfig.objCanSafety[i].bit_length <= 16) {
                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanSafety[i].byte_pos - 1], (byte) can_bytes[PanCanConfig.objCanSafety[i].byte_pos], (byte) 2,
                                                                    (byte) PanCanConfig.objCanSafety[i].bit_pos, PanCanConfig.objCanSafety[i].bit_length, PanCanConfig.objCanSafety[i].reverse);
                                                            spn_val = PanCanConfig.objCanSafety[i].spn;
                                                        } else {
                                                            val = get_bit_pos_value_int(can_bytes[PanCanConfig.objCanSafety[i].byte_pos - 1], can_bytes[PanCanConfig.objCanSafety[i].byte_pos], can_bytes[PanCanConfig.objCanSafety[i].byte_pos + 1], (byte) can_bytes[PanCanConfig.objCanSafety[i].byte_pos + 2], (byte) 4,
                                                                    (byte) PanCanConfig.objCanSafety[i].bit_pos, PanCanConfig.objCanSafety[i].bit_length, PanCanConfig.objCanSafety[i].reverse);
                                                            spn_val = PanCanConfig.objCanSafety[i].spn;
                                                        }

                                                        if (PanCanConfig.objCanSafety[i].factor > 0.0) {
                                                            val = (val * PanCanConfig.objCanSafety[i].factor + PanCanConfig.objCanSafety[i].offset);
                                                        }

                                                        PanCanConfig.objCanSafety[i].data_updated = true;
                                                        PanCanConfig.objCanSafety[i].value = val;
                                                        if (PanCanConfig.objCanSafety[i].display_enable && getCanSafetyVhmdScreen() == true) {
                                                            // if (val >= PanCanConfig.objCanSafety[i].min && val <= PanCanConfig.objCanSafety[i].max) {
                                                            can_display_safety_data(i);
                                                            // }
                                                        }

                                                        try {
                                                            if (PanCanConfig.objCanSafety[i].transmit_enable == true && getCanDataEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(pgn_val);
                                                                sbCan.append(",");
                                                                sbCan.append(spn_val);
                                                                sbCan.append(",");
                                                                sbCan.append((int) val);

                                                                if (getCanUpdateTimeInterval() == 0) {
                                                                    objAllQueue.addAllData(sbCan.toString());
                                                                }
                                                            }

                                                            if (PanCanConfig.objCanSafety[i].pgn == getDoor1Pgn() // JBM_D_PGN_TRAN_CURRENT_GEAR
                                                                    && PanCanConfig.objCanSafety[i].spn == getDoor1Spn()) {
                                                                if (val == getDoor1OpenVal() && prev_door1_val != getDoor1OpenVal()) {
                                                                    prev_door1_val = val;
                                                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                                                        obj16833Pkts.can_door_open((byte) 1);
                                                                        obj16833Pkts = null;
                                                                    }
                                                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                                                        gpsDriving objDriving = new gpsDriving();
                                                                        objDriving.can_door_open((byte) 1);
                                                                        objDriving = null;
                                                                    }

                                                                } else if (val == getDoor1CloseVal() && prev_door1_val != getDoor1CloseVal()) {
                                                                    prev_door1_val = val;
                                                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                                                        obj16833Pkts.can_door_close((byte) 1);
                                                                        obj16833Pkts = null;
                                                                    }
                                                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                                                        gpsDriving objDriving = new gpsDriving();
                                                                        objDriving.can_door_close((byte) 1);
                                                                        objDriving = null;
                                                                    }

                                                                } else {
                                                                    prev_door1_val = val;
                                                                }
                                                            } else if (PanCanConfig.objCanSafety[i].pgn == getDoor2Pgn() // JBM_D_PGN_TRAN_CURRENT_GEAR
                                                                    && PanCanConfig.objCanSafety[i].spn == getDoor2Spn()) {
                                                                if (val == getDoor2OpenVal() && prev_door2_val != getDoor2OpenVal()) {
                                                                    prev_door2_val = val;
                                                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                                                        obj16833Pkts.can_door_open((byte) 2);
                                                                        obj16833Pkts = null;
                                                                    }
                                                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                                                        gpsDriving objDriving = new gpsDriving();
                                                                        objDriving.can_door_open((byte) 2);
                                                                        objDriving = null;
                                                                    }

                                                                } else if (val == getDoor2CloseVal() && prev_door2_val != getDoor2CloseVal()) {
                                                                    prev_door2_val = val;

                                                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                                                        obj16833Pkts.can_door_close((byte) 2);
                                                                        obj16833Pkts = null;
                                                                    }
                                                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                                                        gpsDriving objDriving = new gpsDriving();
                                                                        objDriving.can_door_close((byte) 2);
                                                                        objDriving = null;
                                                                    }
                                                                } else {
                                                                    prev_door2_val = val;
                                                                }
                                                            }
                                                            if (PanCanConfig.objCanSafety[i].log_enable == true && getCanLogEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(PanCanConfig.objCanSafety[i].name);
                                                                sbCan.append(",");
                                                                sbCan.append(val);
                                                                write_can_data_log_file(sbCan.toString()); // PanCanConfig.objCanSafety[i].name + "," + val);
                                                            }

                                                            if (PanCanConfig.objCanSafety[i].alert_enable == true) {
                                                                if (val >= PanCanConfig.objCanSafety[i].min && (val <= PanCanConfig.objCanSafety[i].threshold)) {
                                                                    if (PanCanConfig.objCanSafety[i].alarm_state == ALRM_ENABLE) {
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanSafety[i].name);

                                                                        // cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanSafety[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanSafety[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanSafety[i].name + " < " + PanCanConfig.objCanSafety[i].min);
                                                                        } else if (val > PanCanConfig.objCanSafety[i].threshold && val < PanCanConfig.objCanSafety[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanSafety[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanSafety[i].name + " > " + PanCanConfig.objCanSafety[i].max);
                                                                        } else if (val > PanCanConfig.objCanSafety[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanSafety[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanSafety[i].name + " > " + PanCanConfig.objCanSafety[i].max);
                                                                        }
                                                                        cal = null;

                                                                        //objAlertQueue.addData(PanCanConfig.objCanSafety[i].alarm_id + "," + ALRM_DISABLE);
                                                                        PanCanConfig.objCanSafety[i].alarm_state = ALRM_DISABLE;

                                                                        if (PanCanConfig.objCanSafety[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanSafety[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanSafety[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {

                                                                    if (PanCanConfig.objCanSafety[i].alarm_state == ALRM_DISABLE) {
                                                                        // objAlertQueue.addData(PanCanConfig.objCanSafety[i].alarm_id + "," + ALRM_ENABLE);
                                                                        PanCanConfig.objCanSafety[i].alarm_state = ALRM_ENABLE;
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanSafety[i].name);

                                                                        //cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanSafety[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanSafety[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());//cal_time + "*" + PanCanConfig.objCanSafety[i].name + " < " + PanCanConfig.objCanSafety[i].min);
                                                                        } else if (val > PanCanConfig.objCanSafety[i].threshold && val <= PanCanConfig.objCanSafety[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanSafety[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanSafety[i].name + " > " + PanCanConfig.objCanSafety[i].max);
                                                                        } else if (val > PanCanConfig.objCanSafety[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanSafety[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString()); //cal_time + "*" + PanCanConfig.objCanSafety[i].name + " > " + PanCanConfig.objCanSafety[i].max);
                                                                        }
                                                                        cal = null;

                                                                        if (PanCanConfig.objCanSafety[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanSafety[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanSafety[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                        }
                                                                        cal = null;

                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception ex) {

                                                        }
                                                        //return;
                                                    }
                                                }
                                            }

                                            //Others
                                            for (i = 0; i < PanCanConfig.objCanOthersCnt; i++) {
                                                if (PanCanConfig.objCanOthers[i].can_id == can_id) {//&& PanCanConfig.objCanOthers[i].pgn == pgn_val) {
                                                    if (PanCanConfig.objCanOthers[i].byte_pos > 0) {
                                                        if (PanCanConfig.objCanOthers[i].bit_length <= 8) {

                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanOthers[i].byte_pos - 1], (byte) 0, (byte) 1,
                                                                    (byte) PanCanConfig.objCanOthers[i].bit_pos, PanCanConfig.objCanOthers[i].bit_length, PanCanConfig.objCanOthers[i].reverse);
                                                            spn_val = PanCanConfig.objCanOthers[i].spn;
                                                        } else if (PanCanConfig.objCanOthers[i].bit_length <= 16) {
                                                            val = get_bit_pos_value(can_bytes[PanCanConfig.objCanOthers[i].byte_pos - 1], (byte) can_bytes[PanCanConfig.objCanOthers[i].byte_pos], (byte) 2,
                                                                    (byte) PanCanConfig.objCanOthers[i].bit_pos, PanCanConfig.objCanOthers[i].bit_length, PanCanConfig.objCanOthers[i].reverse);
                                                            spn_val = PanCanConfig.objCanOthers[i].spn;
                                                        } else {
                                                            val = get_bit_pos_value_int(can_bytes[PanCanConfig.objCanOthers[i].byte_pos - 1], can_bytes[PanCanConfig.objCanOthers[i].byte_pos], can_bytes[PanCanConfig.objCanOthers[i].byte_pos + 1], (byte) can_bytes[PanCanConfig.objCanOthers[i].byte_pos + 2], (byte) 4,
                                                                    (byte) PanCanConfig.objCanOthers[i].bit_pos, PanCanConfig.objCanOthers[i].bit_length, PanCanConfig.objCanOthers[i].reverse);
                                                            spn_val = PanCanConfig.objCanOthers[i].spn;
                                                        }

                                                        if (PanCanConfig.objCanOthers[i].factor > 0.0) {

                                                            val = (val * PanCanConfig.objCanOthers[i].factor + PanCanConfig.objCanOthers[i].offset);
                                                        }
                                                        PanCanConfig.objCanOthers[i].value = val;
                                                        PanCanConfig.objCanOthers[i].data_updated = true;

                                                        try {
                                                            if (PanCanConfig.objCanOthers[i].pgn == getVideoRecordPgn() // Video_Record_Pgn
                                                                    && PanCanConfig.objCanOthers[i].spn == getVideoRecordSpn()) {//Video_Record {
                                                                // show_message_dialogbox("Current Gear " + val);
                                                                if (val == getVideoRecordStartVal()) {
                                                                    // start reverse camerra

                                                                    try {
                                                                        if (clsSharedVariables.getCanRecordEnabled() == true) {
                                                                            //
                                                                            clsSharedVariables.setCanBasedRecordingStart(true);
                                                                            show_message_dialogbox("RECORDING START");
                                                                            clsCanBasedVideoRecord objCan1 = new clsCanBasedVideoRecord((byte) 1);
                                                                            objCan1.start();
                                                                            clsCanBasedVideoRecord objCan2 = new clsCanBasedVideoRecord((byte) 2);
                                                                            objCan2.start();
                                                                            clsCanBasedVideoRecord objCan3 = new clsCanBasedVideoRecord((byte) 3);
                                                                            objCan3.start();
                                                                            clsCanBasedVideoRecord objCan4 = new clsCanBasedVideoRecord((byte) 4);
                                                                            objCan4.start();

                                                                            clsCanBasedVideoRecord objCan5 = new clsCanBasedVideoRecord((byte) 5);
                                                                            objCan5.start();
                                                                            clsCanBasedVideoRecord objCan6 = new clsCanBasedVideoRecord((byte) 6);
                                                                            objCan6.start();
                                                                            clsCanBasedVideoRecord objCan7 = new clsCanBasedVideoRecord((byte) 7);
                                                                            objCan7.start();
                                                                            clsCanBasedVideoRecord objCan8 = new clsCanBasedVideoRecord((byte) 8);
                                                                            objCan8.start();

                                                                            if (clsSharedVariables.getCanBasedRecording1Started() == false && clsSharedVariables.getCanBasedRecording2Started() == false
                                                                                    && clsSharedVariables.getCanBasedRecording3Started() == false && clsSharedVariables.getCanBasedRecording4Started() == false) {

                                                                                if (clsSharedVariables.getCanBasedRecording5Started() == false && clsSharedVariables.getCanBasedRecording6Started() == false
                                                                                        && clsSharedVariables.getCanBasedRecording7Started() == false && clsSharedVariables.getCanBasedRecording8Started() == false) {

                                                                                    clsSharedVariables.setCanBasedRecordingStart(false);
                                                                                }

                                                                            }
                                                                        }

                                                                    } catch (Exception ex) {

                                                                    }

                                                                } else {
                                                                    clsSharedVariables.setCanBasedRecordingStart(false);
                                                                }
                                                            }

                                                            if (PanCanConfig.objCanOthers[i].transmit_enable == true && getCanDataEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(pgn_val);
                                                                sbCan.append(",");
                                                                sbCan.append(spn_val);
                                                                sbCan.append(",");
                                                                sbCan.append((int) val);

                                                                if (getCanUpdateTimeInterval() == 0) {
                                                                    objAllQueue.addAllData(sbCan.toString());
                                                                }
                                                            }
                                                            if (PanCanConfig.objCanOthers[i].display_enable && getCanOthersVhmdScreen() == true) {
                                                                // if (val >= PanCanConfig.objCanOthers[i].min && val <= PanCanConfig.objCanOthers[i].max) {
                                                                can_display_others_data(i);
                                                                // }
                                                            }
                                                            if (PanCanConfig.objCanOthers[i].log_enable == true && getCanLogEnabled()) {
                                                                sbCan = sbCan.delete(0, sbCan.length());
                                                                sbCan.append(PanCanConfig.objCanOthers[i].name);
                                                                sbCan.append(",");
                                                                sbCan.append(val);
                                                                write_can_data_log_file(sbCan.toString());
                                                                // write_can_data_log_file(PanCanConfig.objCanOthers[i].name + "," + val);
                                                            }
                                                            if (PanCanConfig.objCanOthers[i].alert_enable == true) {
                                                                if (val >= PanCanConfig.objCanOthers[i].min && (val <= PanCanConfig.objCanOthers[i].threshold)) {
                                                                    if (PanCanConfig.objCanOthers[i].alarm_state == ALRM_ENABLE) {
                                                                        // objAlertQueue.addData(PanCanConfig.objCanOthers[i].alarm_id + "," + ALRM_DISABLE);
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanOthers[i].name);
                                                                        //cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanOthers[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanOthers[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());

                                                                            //objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanOthers[i].name + " < " + PanCanConfig.objCanOthers[i].min);
                                                                        } else if (val > PanCanConfig.objCanOthers[i].threshold && val < PanCanConfig.objCanOthers[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanOthers[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());
                                                                            //objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanOthers[i].name + " > " + PanCanConfig.objCanOthers[i].max);
                                                                        } else if (val > PanCanConfig.objCanOthers[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanOthers[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());
                                                                            //objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanOthers[i].name + " > " + PanCanConfig.objCanOthers[i].max);
                                                                        }

                                                                        PanCanConfig.objCanOthers[i].alarm_state = ALRM_DISABLE;

                                                                        if (PanCanConfig.objCanOthers[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanOthers[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanOthers[i].alarm_id, ALRM_DISABLE);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {

                                                                    if (PanCanConfig.objCanOthers[i].alarm_state == ALRM_DISABLE) {
                                                                        //objAlertQueue.addData(PanCanConfig.objCanOthers[i].alarm_id + "," + ALRM_ENABLE);
                                                                        PanCanConfig.objCanOthers[i].alarm_state = ALRM_ENABLE;
                                                                        cal = Calendar.getInstance();

                                                                        sbCan = sbCan.delete(0, sbCan.length());
                                                                        sbCan.append(cal.getTime().getHours());
                                                                        sbCan.append(":");
                                                                        sbCan.append(cal.getTime().getMinutes());
                                                                        sbCan.append("*");
                                                                        sbCan.append(PanCanConfig.objCanOthers[i].name);
                                                                        // cal_time = String.valueOf(cal.getTime().getHours()) + ":" + String.valueOf(cal.getTime().getMinutes());
                                                                        if (val < PanCanConfig.objCanOthers[i].min) {
                                                                            sbCan.append(" < ");
                                                                            sbCan.append(PanCanConfig.objCanOthers[i].min);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());
                                                                            // objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanOthers[i].name + " < " + PanCanConfig.objCanOthers[i].min);
                                                                        } else if (val > PanCanConfig.objCanOthers[i].threshold && val < PanCanConfig.objCanOthers[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanOthers[i].threshold);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());
                                                                            // objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanOthers[i].name + " > " + PanCanConfig.objCanOthers[i].max);
                                                                        } else if (val > PanCanConfig.objCanOthers[i].max) {
                                                                            sbCan.append(" > ");
                                                                            sbCan.append(PanCanConfig.objCanOthers[i].max);
                                                                            objAlertDisplayQueue.addData(sbCan.toString());
                                                                            // objAlertDisplayQueue.addData(cal_time + "*" + PanCanConfig.objCanOthers[i].name + " > " + PanCanConfig.objCanOthers[i].max);
                                                                        }

                                                                        if (PanCanConfig.objCanOthers[i].transmit_enable == true && getCanDataEnabled()) {
                                                                            if (clsSharedVariables.getIpAddr1Enable()) {
                                                                                can_alarm_params_pkt_16833(PanCanConfig.objCanOthers[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                            if (clsSharedVariables.getIpAddr4Enable()) {
                                                                                can_alarm_params_pkt(PanCanConfig.objCanOthers[i].alarm_id, ALRM_ENABLE);
                                                                            }
                                                                        }
                                                                        cal = null;

                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception ex) {

                                                        }
                                                    }
                                                    //return;
                                                }

                                            }
                                        } catch (Exception ex) {

                                        }
                                    }
                                    break;

                                    case CAN_OBU_ANALOG_PKT: {
                                        try {
                                            //$Pkt Type,BAT_Vol,Mains_Vol,Temp,ADC0,ADC1,ADC2,ADC3@

                                            if (split_str.length >= 8) {
                                                setAnaBatVoltage(Double.parseDouble(split_str[1]));
                                                bat_voltage_value = Double.parseDouble(split_str[1]);
                                                clsSharedVariables.setbatVolValue(bat_voltage_value);
                                                mains_voltage_value = Double.parseDouble(split_str[2]);
                                                setAnaMainsVoltage(mains_voltage_value);
                                                temp_value = Double.parseDouble(split_str[3]);
                                                setAnaTemp(temp_value);
                                                setAnaAdc0(Double.parseDouble(split_str[4]));
                                                setAnaAdc1(Double.parseDouble(split_str[5]));
                                                setAnaAdc2(Double.parseDouble(split_str[6]));
                                                setAnaAdc3(Double.parseDouble(split_str[7]));
                                                // //System.out.println("bat_voltage_value" + bat_voltage_value);

                                                if (bat_voltage_value > clsDefines.BATTERY_DISCONNECTED_VOLTAGE_VALUE) {

                                                } else if (bat_voltage_value <= clsDefines.BATTERY_CONNECTED_VOLTAGE_VALUE && bat_voltage_value > clsDefines.BATTERY_CONN_SWITCH_OFF_VOLT_VAL) {
                                                    if (clsSharedVariables.getPrevIntBatState() == clsDefines.BATTERY_STATE_LOW) {

                                                        battery_connect_state = clsDefines.BATTERY_STATE_CONNECT;
                                                        clsSharedVariables.setPrevIntBatState(battery_connect_state);
                                                        objReadFiles.write_internal_battery_state();
                                                    }
                                                    battery_connect_state = clsDefines.BATTERY_STATE_CONNECT;
                                                    clsSharedVariables.setPrevIntBatState(battery_connect_state);

                                                } else if (bat_voltage_value < clsDefines.BATTERY_CONN_SWITCH_OFF_VOLT_VAL & bat_voltage_value > 5) {
                                                    //shut down unit
                                                    if (battery_connect_state != clsDefines.BATTERY_STATE_LOW) {
                                                        clsSharedVariables.setPrevIntBatState(clsDefines.BATTERY_STATE_LOW);
                                                        // bat_dis_cnt = 0;
                                                        battery_connect_state = clsDefines.BATTERY_STATE_LOW;
                                                        if (clsSharedVariables.getIpAddr1Enable()) {

                                                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                                            obj16833Pkts.trackingMessagePkt(clsPacketTypes.INT_BATTERY_PWR_LOW_PKT, 'L', false, " ");

                                                            obj16833Pkts = null;

                                                        }
//                                                        if (clsSharedVariables.getIpAddr3Enable()) {
//
//                                                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
//                                                            obj16833Pkts.trackingMessagePktwithCan(clsPacketTypes.INT_BATTERY_PWR_LOW_PKT, 'L', false, " ");
//
//                                                            obj16833Pkts = null;
//
//                                                        }
                                                        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                                                            gpsDriving objDriving = new gpsDriving();
                                                            objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_BAT_LOW);
                                                            objDriving = null;
                                                        }

                                                        //save in file to send reconnect packet
                                                        objReadFiles.write_internal_battery_state();

                                                    }

                                                }

                                                if (mains_voltage_value < clsDefines.LOW_VOLTAGE_MIN_VALUE) {
                                                    if (low_volt_came == false) {
                                                        low_volt_came = true;
                                                        if (obu_low_volt_cnt > 254) {
                                                            obu_low_volt_cnt = 0;
                                                        }
                                                        obu_low_volt_cnt++;
                                                        objReadFiles.write_device_dtc_counts_data();
                                                    }
                                                } else if (mains_voltage_value > clsDefines.HIGH_VOLTAGE_MAX_VALUE) {
                                                    if (high_volt_came == false) {
                                                        high_volt_came = true;
                                                        if (obu_high_volt_cnt > 254) {
                                                            obu_high_volt_cnt = 0;
                                                        }
                                                        obu_high_volt_cnt++;
                                                        objReadFiles.write_device_dtc_counts_data();
                                                    }
                                                } else {
                                                    high_volt_came = false;
                                                    low_volt_came = false;
                                                }

                                                if (temp_value > OVER_HEAT_MAX_VALUE) {
                                                    if (high_temp_came == false) {
                                                        high_temp_came = true;
                                                        if (obu_over_heat_cnt > 254) {
                                                            obu_over_heat_cnt = 0;
                                                        }
                                                        obu_over_heat_cnt++;
                                                        objReadFiles.write_device_dtc_counts_data();
                                                    }
                                                }
                                            }

                                        } catch (Exception ex) {

                                        }

                                        break;
                                    }

                                    case clsPacketTypes.CAN_LED_RES_PKT: {
                                        try {
                                            clsSharedVariables.setLedDataResCame(true);
                                            objHealth.set_cam_status_updated(false);

                                            //     ////System.out.println("LED response Came");
                                        } catch (Exception ex) {

                                        }
                                        break;
                                    }
                                    case clsPacketTypes.CAN_OBU_USB_HD_RES_PKT: {
                                        try {
                                            clsSharedVariables.setUsbHdPktResCame(true);

                                        } catch (Exception ex) {

                                        }
                                        break;
                                    }
                                    case clsPacketTypes.CAN_TAMPER_PKT: {
                                        try {
                                            if ("1".equals(split_str[1])) {
                                                clsSharedVariables.setTamperAlertStatus('O');
                                                imgTamper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boxopen.png")));
                                                imgTamper.setVisible(true);
                                            } else {
                                                clsSharedVariables.setTamperAlertStatus('C');
                                                imgTamper.setVisible(false);
                                            }

                                            // ////System.out.println("Tamper Packet Came");
                                        } catch (Exception ex) {

                                        }
                                        break;
                                    }
                                    case OBU_CAN_CONN_CHK_RES_PKT: {
                                        //  setCanDataResCame(true);
                                        break;
                                    }
                                    case OBU_CAN_CONN_RESET_RES_PKT: {
                                        clsSharedVariables.setResetCanDataResCame(true);
                                        break;
                                    }
                                    case clsPacketTypes.CAN_OBU_DIGITAL_RES_PKT: {
                                        clsSharedVariables.setDigOutPktCame(false);
                                        break;
                                    }

                                }
                            }
                            write_close_can_log();
                        }

                    } catch (NumberFormatException ex) {
                        objReadFiles.write_log_gprs_connectivity(" CAN Exceptiion  " + ex.getMessage() + " DATA  " + data);

                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        //  //Logger.getLogger(clsCanSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    //clsReadFiles objReadFiles = new clsReadFiles();
                    objReadFiles.write_log_gprs_connectivity(" CAN DATA Exceptiion  " + ex.getMessage());

                }
            }
        }
private boolean isHomeScreen() {
        return getCurActivity() == clsDefines.MAIN_ACTIVITY;
    }

    private void captureCurrentVideoState() {

        if (clsSharedVariables.getVideoAllStarted()) {
            savedVideoState = 1;
        } else if (clsSharedVariables.getVideo1Started()) {
            savedVideoState = 2;
        } else if (clsSharedVariables.getVideo2Started()) {
            savedVideoState = 3;
        } else if (clsSharedVariables.getVideo3Started()) {
            savedVideoState = 4;
        } else if (clsSharedVariables.getVideo4Started()) {
            savedVideoState = 5;
        } else if (clsSharedVariables.getVideo5Started()) {
            savedVideoState = 6;
        } else if (clsSharedVariables.getVideo6Started()) {
            savedVideoState = 7;
        } else if (clsSharedVariables.getVideo7Started()) {
            savedVideoState = 8;
        } else if (clsSharedVariables.getVideo8Started()) {
            savedVideoState = 9;
        }
    }
    

        private void start_video() {

            if (prev_video_state > 0) {

                if (clsSharedVariables.getVideoAllStarted() == false && clsSharedVariables.getVideo1Started() == false
                        && clsSharedVariables.getVideo2Started() == false && clsSharedVariables.getVideo3Started() == false
                        && clsSharedVariables.getVideo4Started() == false && clsSharedVariables.getVideo5Started() == false
                        && clsSharedVariables.getVideo6Started() == false && clsSharedVariables.getVideo7Started() == false
                        && clsSharedVariables.getVideo8Started() == false) {
                    prev_video_state = 0;
                    return;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // //Logger.getLogger(clsCanSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                }
                switch (savedVideoState) {
                    case 0:
                        break;
                    case 2:
                        MainFrmIts.video1_live(true);
                        break;
                    case 3:
                        MainFrmIts.video2_live(true);
                        break;
                    case 4:
                        MainFrmIts.video3_live(true);
                        break;
                    case 5:
                        MainFrmIts.video4_live(true);
                        break;
                    
                    case 1:
                        MainFrmIts.videoall_live(VIDEO_FROM_CAN);
                        break;
                }
            }
        }

        private void stop_video() {
            try {
                prev_video_state = 0;
                if (getVideoStarted() == true) {

                    setVideoStarted(false);
                    if (clsSharedVariables.getVideoAllStarted() == true) {
                        prev_video_state = 9;
                        if (objCamView1 != null) {
                            objCamView1.interrupt();
                            objCamView1 = null;
                        }
                        if (objCamView2 != null) {
                            objCamView2.interrupt();
                            objCamView2 = null;
                        }
                        if (objCamView3 != null) {
                            objCamView3.interrupt();
                            objCamView3 = null;
                        }
                        if (objCamView4 != null) {
                            objCamView4.interrupt();
                            objCamView4 = null;
                        }
                       
                    } else {
                        if (clsSharedVariables.getVideo1Started() == true) {

                            if (objCamView1 != null) {
                                objCamView1.interrupt();
                                objCamView1 = null;
                            }

                            prev_video_state = 1;

                        } else if (clsSharedVariables.getVideo2Started() == true) {

                            if (objCamView2 != null) {
                                objCamView2.interrupt();
                                objCamView2 = null;
                            }

                            prev_video_state = 2;

                        } else if (clsSharedVariables.getVideo3Started() == true) {

                            if (objCamView3 != null) {
                                objCamView3.interrupt();
                                objCamView3 = null;
                            }

                            prev_video_state = 3;

                        } else if (clsSharedVariables.getVideo4Started() == true) {

                            if (objCamView4 != null) {
                                objCamView4.interrupt();
                                objCamView4 = null;
                            }

                            prev_video_state = 4;

                        } 

                    }
                    // clsSharedVariables.setVideoAllStarted(false);
                    //clsSharedVariables.setVideo1Started(false);
                    // clsSharedVariables.setVideo2Started(false);
                    // clsSharedVariables.setVideo3Started(false);
                    // clsSharedVariables.setVideo4Started(false);
                    Process p;
                    if (clsDefines.omxplayer_found == true) {
                        p = Runtime.getRuntime().exec("sudo pkill omxplayer \n");
                    } else {
                        p = Runtime.getRuntime().exec("sudo pkill ffplay \n");
                    }
                    p.waitFor();
                    p.destroy();
                    p = null;
                    Thread.sleep(1000);
                }

            } catch (Exception ex) {
                //Logger.getLogger(MainFrmIts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public long CalculateCRC32ChecksumForByteArray(byte[] bytes) {

            Checksum checksum = new CRC32();
            // update the current checksum with the specified array of bytes
            checksum.update(bytes, 0, bytes.length); //bytes.length);
            // get the current checksum value
            long checksumValue = checksum.getValue();
            //ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            // buffer.putLong(checksumValue);

            checksum = null;

            return checksumValue;//buffer.array();

        }

        public void can_alarm_params_pkt_16833(short alarm_id, byte val) {
            if (clsSharedVariables.getIpAddr1Enable()) {
                StringBuilder sb = new StringBuilder();
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyMMddHHmmss");

                long checksum;
                try {

                    sb.append(clsSharedVariables.START_CHARACTER);

                    sb.append(clsPacketTypes16833.OBU_CS_CAN_ALARMS_PKT);
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

                    sb.append(alarm_id);
                    sb.append(SEPARATOR_COMMA);

                    sb.append(val);
                    sb.append(SEPARATOR_COMMA);

                    //checksum
                    checksum = CalculateCRC32ChecksumForByteArray(sb.toString().getBytes());
                    sb.append(String.valueOf(checksum));
                    sb.append(clsSharedVariables.END_CHARACTER);

                    objCanAlertDataServer.addCanDataServer(sb.toString().getBytes());
                } catch (Exception ex) {
                } finally {

                    cal = null;

                }
            }
        }

        private void can_alarm_params_pkt(short alarm_id, byte val) {
            {
                byte[] final_buf = new byte[200];

                int buf_inc = 0;
                int i = 0;
                short len = 0;
                byte buf[];
                //StringBuilder sb = new StringBuilder();
                Calendar cal = Calendar.getInstance();
                String OBUId;
                try {
                    OBUId = getOBUID();
                    buf_inc = 0;
                    //Start of the packet
                    final_buf[buf_inc++] = clsDefines.START_PKT;

                    //OBU ID of 12 characters
                    // OBUId="000001";
                    buf = OBUId.getBytes();
                    for (i = 0; i < buf.length && i < 6; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }
                    for (; i < 6; i++) {
                        final_buf[buf_inc++] = clsDefines.ASCII_ZERO;
                    }

                    //YYMMDDHHMMSS
                    if (cal.get(Calendar.YEAR) > 2000) {
                        final_buf[buf_inc++] = (byte) (cal.get(Calendar.YEAR) - 2000);
                    } else {
                        final_buf[buf_inc++] = (byte) (2018 - 2000);
                    }

                    final_buf[buf_inc++] = (byte) (cal.get(Calendar.MONTH) + 1);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.DAY_OF_MONTH);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE);
                    final_buf[buf_inc++] = (byte) cal.get(Calendar.SECOND);
                    final_buf[buf_inc++] = clsPacketTypes.GPRS_PKT_CAN_ALARMS; //Message Type
                    //length of can data

                    len = 3;
                    buf = gpsDriving.convertToByteArray(len);
                    for (i = 0; i < buf.length; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }

                    //reserved 8 bytes
                    for (i = 0; i < 8; i++) {
                        final_buf[buf_inc++] = 0;
                    }

                    buf = gpsDriving.convertToByteArray(alarm_id);
                    for (i = 0; i < buf.length; i++) {
                        final_buf[buf_inc++] = buf[i];
                    }

                    final_buf[buf_inc++] = val;

                    //end of the packet
                    final_buf[buf_inc++] = clsDefines.END_PKT;

                    buf = new byte[buf_inc];
                    for (i = 0; i < buf_inc; i++) {
                        buf[i] = final_buf[i];
                    }

                    objCanAlertDataServer.addCanDataServer(buf);

                } catch (Exception ex) {

                } finally {

                    // sb = null;
                    final_buf = null;
                    OBUId = null;
                    buf = null;
                    cal = null;
                }
            }
        }

        File canfile;
        FileOutputStream canfoutstream;
        PrintWriter canpw;

        private void write_open_can_log() {

            if (!clsSharedVariables.getHardDriveDetected()) {
                return;
            }
            String path;
            StringBuilder sb = new StringBuilder(500);
            long sec = Calendar.getInstance().getTimeInMillis();
            Date today = new Date();
            try {
                if (!can_filepath.exists()) {
                    can_filepath.mkdirs();
                    can_filepath.setWritable(true, false);
                    can_filepath.setReadable(true, false);
                }

                // cal = Calendar.getInstance();
                if (sec > 0) {
                    today = new Date(sec);
                    sdf.format(today);
                }

                sb = sb.delete(0, sb.length());
                sb.append("OBITS-APP-CANLOG-");
                sb.append(getOBUID());
                sb.append("-");
                sb.append(sdf.format(today)); //cal.getTime()));
                sb.append(".log");
                path = sb.toString();

                canfile = new File(can_filepath, path);
                canfoutstream = new FileOutputStream(canfile, true);
                canpw = new PrintWriter(canfoutstream);
            } catch (FileNotFoundException ex) {
                // //Logger.getLogger(clsCanSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {

            } finally {
                path = null;
                sb = null;
                sec = 0;
                today = null;
            }
        }

        private void write_close_can_log() {

            try {
                if (canpw != null) {
                    canpw.close();
                    canfoutstream.close();
                }

            } catch (IOException ex) {
                //Logger.getLogger(clsCanSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                canfile = null;
                canfoutstream = null;
                canpw = null;
            }
        }

        private void write_can_data_log_file(String str) {

            StringBuilder sb = new StringBuilder(500);
            long sec = Calendar.getInstance().getTimeInMillis();
            Date today = new Date();
            today = new Date(sec);
            try {
                sb = sb.delete(0, sb.length());
                sb.append(sdf_time.format(today));//cal.getTime()));
                sb.append("  , ");
                sb.append(str);
                canpw.println(sb.toString());
                canpw.flush();
            } catch (Exception e) {
                // e.printStackTrace();
            } finally {
                sb = null;
                sec = 0;
                today = null;
            }
        }

        public final void startTimer() {
            //set a new Timer

            if (timer == null) {
                timer = new Timer();
                //initialize the TimerTask's job
                initializeTimerTask();
                //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
                timer.schedule(timerTask, 0, 1000);
            }
        }

        private void stopTimer() {
            try {
                setStopReqMsg(false);
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
                if (timerTask != null) {
                    timerTask.cancel();
                    timerTask = null;
                }
            } catch (Exception ex) {

            }

        }

        public void initializeTimerTask() {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (emergency_timer_inc++ >= 5) {
                            emergency_timer_inc = 0;
                            SwingUtilities.invokeLater(() -> {
                                MainFrmIts.imgCanEmergency.setVisible(false);
                                stopTimer();
                            });
                        }
                    } catch (Exception ex) {
                    }
                }

            };
        }

        private void show_message_dialogbox(String data) {
            try {
                if (msg_box_opened == true) {
                    return;
                }
                final JLabel label = new JLabel();
                int timerDelay = 1000;
                new javax.swing.Timer(timerDelay, new ActionListener() {
                    int timeLeft = 1;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (timeLeft > 0) {
                            label.setText(data);
                            timeLeft--;
                        } else {
                            msg_box_opened = false;
                            try {
                                ((javax.swing.Timer) e.getSource()).stop();
                                Window win = SwingUtilities.getWindowAncestor(label);
                                win.setVisible(false);
                            } catch (Exception ex) {

                            } finally {
                                msg_box_opened = false;
                            }
                        }
                    }
                }) {
                    {
                        setInitialDelay(0);
                    }
                }.start();

                JOptionPane.showMessageDialog(null, label);
            } catch (Exception ex) {
                msg_box_opened = false;
            }
        }

        public short getRequiredBitsfromByte(short b, byte bit_pos, byte bit_length) {
            BitSet bits = new BitSet(8);
            BitSet final_bits = new BitSet(8);
            int j = 0;
            int k = 0;

            for (int i = 0; i < 8; i++) {
                bits.set(i, (b & 1) == 1);
                final_bits.set(i, false);
                b >>= 1;
            }

            j = bit_pos - 1;
            for (k = 0; k < bit_length; k++) {
                final_bits.set(k, bits.get(j));
                j++;
            }

            byte[] arr = final_bits.toByteArray();
            if (arr.length == 0) {
                return 0;
            } else {
                return (short) (arr[0] & 0xFF);
            }
        }

        public int getRequiredBitsfromShort(int b, byte bit_pos, byte bit_length) {
            BitSet bits = new BitSet(8);
            BitSet final_bits = new BitSet(16);
            int j = 0;
            int k = 0;

            for (int i = 0; i < 16; i++) {
                bits.set(i, (b & 1) == 1);
                final_bits.set(i, false);
                b >>= 1;
            }

            j = bit_pos - 1;
            for (k = 0; k < bit_length; k++) {
                final_bits.set(j, bits.get(j));
                j++;
            }

            byte[] arr = final_bits.toByteArray();
            if (arr.length == 0) {
                return 0;
            } else if (arr.length == 1) {
                return arr[0];
            } else {

                ByteBuffer bb = ByteBuffer.allocate(2);
                bb.order(ByteOrder.BIG_ENDIAN);
                bb.put((byte) arr[0]);
                bb.put((byte) arr[1]);
                return (int) (bb.getShort(0) & 0xFFFF);
            }
        }

        public int getRequiredBitsfromInt(int b, byte bit_pos, byte bit_length) {
            BitSet bits = new BitSet(8);
            BitSet final_bits = new BitSet(32);
            int j = 0;
            int k = 0;

            for (int i = 0; i < 32; i++) {
                bits.set(i, (b & 1) == 1);
                final_bits.set(i, false);
                b >>= 1;
            }

            j = bit_pos - 1;
            for (k = 0; k < bit_length; k++) {
                final_bits.set(j, bits.get(j));
                j++;
            }

            byte[] arr = final_bits.toByteArray();
            if (arr.length == 0) {
                return 0;
            } else if (arr.length == 1) {
                return arr[0];
            } else {

                ByteBuffer bb = ByteBuffer.allocate(4);
                bb.order(ByteOrder.BIG_ENDIAN);
                bb.put((byte) arr[0]);
                bb.put((byte) arr[1]);
                bb.put((byte) arr[2]);
                bb.put((byte) arr[3]);
                return (int) (bb.getInt(0) & 0xFFFFFFFF);
            }
        }

        public int ReverseBitsfromByte(short data, short from, byte bitlen) {

            if (bitlen > 8) {
                bitlen = 8;
            }
            BitSet bits = new BitSet(8);
            BitSet reverse_bits = new BitSet(8);
            try {
                byte j = 7;
                int i;
                int k = 0;
                for (i = 0; i < 8; i++) {
                    bits.set(i, (data & 1) == 1);
                    reverse_bits.set(i, false);
                    data >>= 1;
                }
                j = (byte) (from - 1);
                k = from - bitlen;

                for (i = 0; i < bitlen; i++) {
                    //  reverse_bits.set(j, bits.get(k));
                    reverse_bits.set(i, bits.get(j));
                    j--;
                    k++;
                }
            } catch (Exception ex) {

            }
            byte[] arr = reverse_bits.toByteArray();
            return arr[0] & 0xFF;
        }

        private int get_bit_pos_value(short num1, short num2, byte byte_len, byte bit_pos, byte bit_length, boolean reverse) {
            int val = 0;
            int val2 = 0;

            try {
                if (reverse == false) {
                    if (byte_len > 1) {
                        if (byte_len == 4) {

                        } else {
                            val2 = (short) (num1 & 0xFF);
                            val2 <<= 8;
                            val2 |= (short) (num2 & 0xFF);

                            val = getRequiredBitsfromShort(val2, bit_pos, bit_length);
                            val = val & 0xFFFF;
                        }

                    } else {
                        val = getRequiredBitsfromByte(num1, bit_pos, bit_length);
                        val = val & 0xFF;
                    }

                } else {
                    int a = 0;
                    if (byte_len == 1) {
                        val = ReverseBitsfromByte(num1, bit_pos, bit_length);
                        val = val & 0xFF;
                    } else {
                        val = ReverseBitsfromByte(num1, bit_pos, bit_length);
                        val2 = ReverseBitsfromByte(num2, bit_pos, bit_length);

                        // val =  (short) ( val & 0xFF );
                        //val <<= 8;
                        // val2 = (short) (val2 & 0xFF);
                        // val =  (val | val2);
                        ByteBuffer bb = ByteBuffer.allocate(2);
                        bb.order(ByteOrder.LITTLE_ENDIAN);
                        //  bb.order(ByteOrder.BIG_ENDIAN);
                        bb.put((byte) val);
                        bb.put((byte) val2);
                        val = bb.getShort(0) & 0xFFFF;
                    }
                }
            } catch (Exception ex) {

            }
            return val;
        }

        private int get_bit_pos_value_int(int num1, int num2, int num3, int num4, byte byte_len, byte bit_pos, byte bit_length, boolean reverse) {
            int val = 0;
            int val2 = 0;

            int lsb_val;
            int pid_val;
            try {

                lsb_val = num1 & 0xFF;
                pid_val = lsb_val << 24;
                lsb_val = num2 & 0xFF;
                lsb_val <<= 16;
                pid_val = pid_val | lsb_val;
                lsb_val = num3 & 0xFF;
                lsb_val <<= 8;
                pid_val = pid_val | lsb_val;
                lsb_val = num4 & 0xFF;
                pid_val = pid_val | lsb_val;
                val2 = pid_val;

                val = getRequiredBitsfromInt(val2, bit_pos, bit_length);
                val = val & 0xFFFFFFFF;

            } catch (Exception ex) {

            }
            return val;
        }

        public byte ReverseBitsfromByte(byte b) {
            BitSet bits = new BitSet(8);
            byte j = 7;
            for (int i = 0; i < 8; i++) {
                bits.set(j, (b & 1) == 1);
                b >>= 1;
                j--;
            }
            byte[] arr = bits.toByteArray();
            return arr[0];
        }

        //
        private int get_24bit_value(byte b0, byte b1, byte b2, int bitPos, boolean reverse) {

            int raw;

            if (!reverse) {
                // Little-endian (your bus uses this)
                raw = (b0 & 0xFF) | ((b1 & 0xFF) << 8) | ((b2 & 0xFF) << 16);
            } else {
                // Big-endian (rare)
                raw = ((b0 & 0xFF) << 16) | ((b1 & 0xFF) << 8) | (b2 & 0xFF);
            }

            return raw;
        }

        //
    }
    //This method is designed for closing the serial port

    public static void close_can_serialport() {
        if (can_serialPort != null) {
            try {
                clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();

                objwatDog.setCan_watchdog_val((byte) 0);
                objwatDog.setCanQueue_watchdog_val((byte) 0);
                objwatDog.setCanSerial_watchdog_val((byte) 0);
                objwatDog = null;

                can_serialPort.writeString("$" + OBU_CAN_CONN_CHK_PKT + "," + OBU_CAN_STOP_DATA + "@");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                can_serialPort.removeEventListener();
                can_serialPort.closePort();
                can_serialPort = null;

            } catch (SerialPortException ex) {
                //Logger.getLogger(clsDisplayBrdSerialPort.class
                //   .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    //This method is used to kill the gstreamer video live

    public void killGstreamer() {
        String processName = "gst-launch-1.0";
        try {
            Process process = Runtime.getRuntime().exec("pgrep " + processName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String pid;
            while ((pid = reader.readLine()) != null) {
                Runtime.getRuntime().exec("kill " + pid);
                ////System.out.println("killed ffplay");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //This method will construct the digital output  packet and send it to the can serial port.

    public void send_digital_output() {
        StringBuilder sbDigOut = new StringBuilder();

        sbDigOut = sbDigOut.delete(0, sbDigOut.length());
        sbDigOut.append("$");
        sbDigOut.append(CAN_OBU_DIGITAL_PKT);
        sbDigOut.append(",");
        sbDigOut.append(clsSharedVariables.getDigOut1Value());
        sbDigOut.append(",");
        sbDigOut.append(clsSharedVariables.getDigOut2Value());
        sbDigOut.append(",");
        sbDigOut.append(clsSharedVariables.getDigOut3Value());
        sbDigOut.append(",");
        sbDigOut.append(clsSharedVariables.getDigOut4Value());
        sbDigOut.append("@");
        try {
            can_serialPort.writeString(sbDigOut.toString());

        } catch (SerialPortException ex) {
            // Logger.getLogger(clsCanSerialPort.class
            // .getName()).log(Level.SEVERE, null, ex);
        }

    }

    //This method will construct the led status packet and send it to the can serial port.
    public void send_led_status_output() {
        StringBuilder sb = new StringBuilder();
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        sb = sb.delete(0, sb.length());
        sb.append("$");
        sb.append(clsPacketTypes.CAN_LED_PKT);
        sb.append(",");
        sb.append(objHealth.get_cam1_status());
        sb.append(",");
        sb.append(objHealth.get_cam2_status());
        sb.append(",");
        sb.append(objHealth.get_cam3_status());
        sb.append(",");
        sb.append(objHealth.get_cam4_status());
        sb.append(",");
        sb.append(objHealth.get_cam5_status());
        sb.append(",");
        sb.append(objHealth.get_cam6_status());
        sb.append(",");
        sb.append(objHealth.get_cam7_status());
        sb.append(",");
        sb.append(objHealth.get_cam8_status());
        sb.append("@");
        try {
            can_serialPort.writeString(sb.toString());

        } catch (SerialPortException ex) {
            //   Logger.getLogger(clsCanSerialPort.class
            //.getName()).log(Level.SEVERE, null, ex);
        }
        objHealth = null;
        sb = null;
    }
    //This method will construct the usb  packet and send it to the can serial port.

    public void send_usb_hd_output() {
        clsSharedVariables.setUsbHdPktCame(false);
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        sb.append(clsPacketTypes.CAN_OBU_USB_HD_PKT);
        sb.append(",");
        sb.append(clsSharedVariables.getHdStatEnableDisable());
        sb.append(",");
        sb.append(clsSharedVariables.getUsbStatEnableDisable()); //getHdStatEnableDisable());
        sb.append("@");
        try {
            can_serialPort.writeString(sb.toString());

        } catch (SerialPortException ex) {
            // Logger.getLogger(clsCanSerialPort.class
            //.getName()).log(Level.SEVERE, null, ex);
        }
        sb = null;
    }
}
