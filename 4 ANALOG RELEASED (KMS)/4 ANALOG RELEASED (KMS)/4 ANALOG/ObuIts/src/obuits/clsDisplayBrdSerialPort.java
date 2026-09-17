package obuits;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import static obuits.MainFrmIts.digOutRs485Pin;
import static obuits.MainFrmIts.lblFD;
import static obuits.MainFrmIts.lblID;
import static obuits.MainFrmIts.lblIDArt;
import static obuits.MainFrmIts.lblRD;
import static obuits.MainFrmIts.lblSD;
import static obuits.MainFrmIts.lblSDArt;
import static obuits.MainFrmIts.write_gpio_pin_state;
import static obuits.PanDisplayBoardDiag.CONFIGURATION_INTENSITY_RES_PKT;
import static obuits.PanDisplayBoardDiag.DATA_RES_PKT;
import static obuits.PanDisplayBoardDiag.DELETE_PKT;
import static obuits.PanDisplayBoardDiag.DELETE_PKT_RES_PKT;
import static obuits.PanDisplayBoardDiag.DISBRD_GET_BRD_ADDR_CONFIG_RES_PKT;
import static obuits.PanDisplayBoardDiag.DISBRD_SET_BRD_ADDR_CONFIG_RES_PKT;
import static obuits.PanDisplayBoardDiag.DTC_CODEALL_PKT;
import static obuits.PanDisplayBoardDiag.DTC_CODEALL_RES_PKT;
import static obuits.PanDisplayBoardDiag.DTC_CODE_RES_PKT;
import static obuits.PanDisplayBoardDiag.DTC_COUNT_CODE_PKT;
import static obuits.PanDisplayBoardDiag.DTC_COUNT_CODE_RES_PKT;
import static obuits.PanDisplayBoardDiag.DTC_HIGH_VOLTAGE;
import static obuits.PanDisplayBoardDiag.DTC_LOW_VOLTAGE;
import static obuits.PanDisplayBoardDiag.LINK_CHECK_PKT;
import static obuits.PanDisplayBoardDiag.LINK_CHECK_RES_PKT;
import static obuits.PanDisplayBoardDiag.PID_APP_SW_REV;
import static obuits.PanDisplayBoardDiag.PID_BUS_BUILDER_NO;
import static obuits.PanDisplayBoardDiag.PID_CODEALL_PKT;
import static obuits.PanDisplayBoardDiag.PID_CODEALL_RES_PKT;
import static obuits.PanDisplayBoardDiag.PID_CODE_PKT;
import static obuits.PanDisplayBoardDiag.PID_CODE_RES_PKT;
import static obuits.PanDisplayBoardDiag.PID_COMPILATION_FW_DATE_TIME;
import static obuits.PanDisplayBoardDiag.PID_END_CUSTOMER;
import static obuits.PanDisplayBoardDiag.PID_FLASH_UPATE_STATUS;
import static obuits.PanDisplayBoardDiag.PID_INTERNAL_CPU_TEMP;
import static obuits.PanDisplayBoardDiag.PID_LANGUAGE;
import static obuits.PanDisplayBoardDiag.PID_NO_RESETS;
import static obuits.PanDisplayBoardDiag.PID_OPERATING_HRS;
import static obuits.PanDisplayBoardDiag.PID_ORDER_NO;
import static obuits.PanDisplayBoardDiag.PID_PRODUCTION_DATE;
import static obuits.PanDisplayBoardDiag.PID_READ;
import static obuits.PanDisplayBoardDiag.PID_SER_NO;
import static obuits.PanDisplayBoardDiag.PID_TEST_DATE_TIME;
import static obuits.PanDisplayBoardDiag.PID_VEHICLE_TYPE;
import static obuits.PanDisplayBoardDiag.SOFTREST_RES_PKT;
import static obuits.PanDisplayBoardDiag.TESTC_PKT;
import static obuits.PanDisplayBoardDiag.TESTC_RES_PKT;
import static obuits.PanDisplayBoardDiag.TEST_RES_PKT;
import static obuits.PanDisplayBoardDiag.TIME_PKT;
import static obuits.PanDisplayBoardDiag.TIME_RES_PKT;
import static obuits.PanDisplayBoardDiag.objBrdLnkRstTst;
import static obuits.PanDisplayBoardDiag.objPids;
import static obuits.PanDisplayBoardDiag.disBrdLinkChkBuf;
import static obuits.PanDisplayBoardDiag.getDisBrdDelPktCame;
import static obuits.PanDisplayBoardDiag.getDisBrdLnkChkCame;
import static obuits.PanDisplayBoardDiag.setDisBrdDelPktCame;
import static obuits.PanDisplayBoardDiag.setDisBrdLnkChkCame;
import static obuits.PanPreloads.lblPreloadMsg;
//import static obuits.PanSpecialMessages.lblSplMsg;
import static obuits.clsDefines.DISPLAYBRD_COMPORT;
import static obuits.clsDefines.FC_BYTE;
import static obuits.clsDefines.FD_ADDR;
import static obuits.clsDefines.FRONT_DB;
import static obuits.clsDefines.ID_ADDR;
import static obuits.clsDefines.ID_ART_ADDR;
import static obuits.clsDefines.INT_DB;
import static obuits.clsDefines.INT_DB_ART;
import static obuits.clsDefines.OBU_BRD_TYPE;
import static obuits.clsDefines.RASPBERRY_BOARD;
import static obuits.clsDefines.RD_ADDR;
import static obuits.clsDefines.REAR_DB;
import static obuits.clsDefines.RES_CHKSUM_FAIL;
import static obuits.clsDefines.RES_FC_BYTE;
import static obuits.clsDefines.RES_PID_CMD_BYTE;
import static obuits.clsDefines.RES_SRC_BYTE;
import static obuits.clsDefines.RES_SUCCESS;
import static obuits.clsDefines.RES_UNSUCCESS;
import static obuits.clsDefines.SD_ADDR;
import static obuits.clsDefines.SD_ART_ADDR;
import static obuits.clsDefines.SIDE_DB;
import static obuits.clsDefines.SIDE_DB_ART;
import static obuits.clsDefines.Strings.KEMITO_DISBRD;
import static obuits.clsDefines.Strings.NORMAL_RESET;
import static obuits.clsDefines.Strings.RS485_ONOFF;
import static obuits.clsDefines.Strings.RS485_SIGNAL_OFF_STATE;
import static obuits.clsDefines.Strings.RS485_SIGNAL_ON_STATE;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.TIME_ENABLED;
import static obuits.clsDefines.TINKER_BOARD;
import static obuits.clsDefines.WRITE_MODE;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsSharedVariables.getArtIntEnable;
import static obuits.clsSharedVariables.getArtSideEnable;
import static obuits.clsSharedVariables.getArticulatedBus;
import static obuits.clsSharedVariables.getDisBrdBaudRate;
import static obuits.clsSharedVariables.getDisBrdName;
import static obuits.clsSharedVariables.getDisbRdGap;
import static obuits.clsSharedVariables.getDisbRdRetryWaitTime;
import static obuits.clsSharedVariables.getFrontEnable;
import static obuits.clsSharedVariables.getIntDisbBrdMsgCame;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.getPreloadMsgDataSent;
import static obuits.clsSharedVariables.getRearEnable;
import static obuits.clsSharedVariables.getSideEnable;
import static obuits.clsSharedVariables.int_disbrd_msg_buf;
import static obuits.clsSharedVariables.setIntDisbBrdMsgCame;
import static obuits.clsSharedVariables.setPreloadMsgDataSent;

public class clsDisplayBrdSerialPort {

    // Serial port instance and buffer for data transmission
    static SerialPort displaybrd_serialPort;
    static ReadDisplayBrdPortDataFromSerialPort objDisplaytPort = null;
    static byte[] txBuffer = new byte[33000];

    // Queue for managing display board data
    public static clsDisplayBrdQueue objdisQue = new clsDisplayBrdQueue();
    clsQueueDisBrdPids objDisBrdPid = new clsQueueDisBrdPids();
    // Flags to track different states of communication
    static boolean res_pkt_came = false;
    static boolean link_res_pkt_came = false;
    static boolean pid_res_pkt_came = false;
    static boolean frontlink_res_pkt_came = false;
    static boolean sidelink_res_pkt_came = false;
    static boolean rearlink_res_pkt_came = false;
    static boolean intlink_res_pkt_came = false;
    static boolean sideartlink_res_pkt_came = false;
    static boolean intartlink_res_pkt_came = false;
    static boolean display_brd_serial_connected = false;

    // Instance for handling serial port data reading
    static ReadDisplayBrdPortDataFromSerialPort objDisplayBrdSerial = null;

    // Constants for hex values and wait time
    static byte AA_HEX = (byte) 170;
    static byte CC_HEX = (byte) 204;
    static final byte RES_WAIT_TIME = 8;

    // Instance for handling health packet structure
    clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

    // File paths for various data
    static String fd_filepath = null;
    static String sd_filepath = null;
    static String rd_filepath = null;
    static String id_filepath = null;
    static String sd_art_filepath = null;
    static String id_art_filepath = null;

    // Flags for data response status
    static boolean fd_data_response_fail = false;
    static boolean sd_data_response_fail = false;
    static boolean rd_data_response_fail = false;
    static boolean id_data_response_fail = false;
    static boolean sd_art_data_response_fail = false;
    static boolean id_art_data_response_fail = false;

    // Image icons for success and failure states
    final ImageIcon fd_fail_icon;
    final ImageIcon sd_fail_icon;
    final ImageIcon rd_fail_icon;
    final ImageIcon id_fail_icon;
    final ImageIcon fd_succ_icon;
    final ImageIcon sd_succ_icon;
    final ImageIcon rd_succ_icon;
    final ImageIcon id_succ_icon;

    // Constants for board status
    final byte BOARD_STATUS_SUCCESS = 1;
    final byte BOARD_STATUS_FAILURE = 0;

    // Instance for managing watchdog reset variables
    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();

    // Constants for display driver and counters
    static final byte DRIVER_NO_DISPLAY_INT_PKT_NO = 5;

    public clsDisplayBrdSerialPort() {
        // Initialize image icons
        fd_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/fd_fail.png"));
        sd_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/sd_fail.png"));
        rd_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/rd_fail.png"));
        id_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/id_fail.png"));
        fd_succ_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/fd_ok.png"));
        sd_succ_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/sd_ok.png"));
        rd_succ_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/rd_ok.png"));
        id_succ_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/id_ok.png"));

        // Set initial values for watchdog reset variables
        objwatDog.setDisbrdSerial_watchdog_val((byte) 0);
        objwatDog.setDisbrd_watchdog_val((byte) 0);

        // Determine the display board type and port
        if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
            DISPLAYBRD_COMPORT = clsDefines.RASPBERRY_DISBRD_COMPORT;
        } else if (OBU_BRD_TYPE == TINKER_BOARD) {
            DISPLAYBRD_COMPORT = clsDefines.TINKER_DISBRD_COMPORT;
        }

        // Start the thread for reading data from the serial port
        if (objDisplayBrdSerial == null) {
            objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
            objDisplayBrdSerial.start();
        }

        // Start the thread for handling the latest PID display board
        if (getDisBrdName() == SUMITH_DISBRD) {
            clsPidDisBrdLatestThread obj = new clsPidDisBrdLatestThread();
            obj.start();
        }
    }

    public synchronized static void setPidResCame(boolean state) {
        pid_res_pkt_came = state;
    }

    public synchronized static boolean getPidResCame() {
        return pid_res_pkt_came;
    }

    public synchronized static void setDisBrdResCame(boolean state) {
        res_pkt_came = state;
    }

    public synchronized static boolean getDisBrdResCame() {
        return res_pkt_came;
    }

    public synchronized static void setLinkDisBrdResCame(boolean state) {
        link_res_pkt_came = state;
    }

    public synchronized static boolean getLinkDisBrdResCame() {
        return link_res_pkt_came;
    }

    public synchronized static void setFrontLinkDisBrdResCame(boolean state) {
        frontlink_res_pkt_came = state;
    }

    public synchronized static boolean getFrontLinkDisBrdResCame() {
        return frontlink_res_pkt_came;
    }

    public synchronized static void setSideLinkDisBrdResCame(boolean state) {
        sidelink_res_pkt_came = state;
    }

    public synchronized static boolean getSideLinkDisBrdResCame() {
        return sidelink_res_pkt_came;
    }

    public synchronized static void setRearLinkDisBrdResCame(boolean state) {
        rearlink_res_pkt_came = state;
    }

    public synchronized static boolean getRearLinkDisBrdResCame() {
        return rearlink_res_pkt_came;
    }

    public synchronized static void setIntLinkDisBrdResCame(boolean state) {
        intlink_res_pkt_came = state;
    }

    public synchronized static boolean getIntLinkDisBrdResCame() {
        return intlink_res_pkt_came;
    }

    public synchronized static void setSideArtLinkDisBrdResCame(boolean state) {
        sideartlink_res_pkt_came = state;
    }

    public synchronized static boolean getSideArtLinkDisBrdResCame() {
        return sideartlink_res_pkt_came;
    }

    public synchronized static void setIntArtLinkDisBrdResCame(boolean state) {
        intartlink_res_pkt_came = state;
    }

    public synchronized static boolean getIntArtLinkDisBrdResCame() {
        return intartlink_res_pkt_came;
    }

    private class ReadDisplayBrdPortDataFromSerialPort extends Thread {

        @Override
        public void run() {
            while (true) {
                objwatDog.setDisbrdSerial_watchdog_val((byte) 2);

                if (display_brd_serial_connected == false) {
                    if (connectDisplayCommandsSerialport() == false) {

                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException ex) {
                        }
                        if (display_brd_serial_connected == true) {
                            display_brd_serial_connected = false;
                            lblFD.setIcon(fd_fail_icon);
                            lblSD.setIcon(sd_fail_icon);
                            lblRD.setIcon(rd_fail_icon);
                            lblID.setIcon(id_fail_icon);
                            lblSDArt.setIcon(sd_fail_icon);
                            lblIDArt.setIcon(id_fail_icon);
                        }

                    } else {
                        display_brd_serial_connected = true;
                    }
                } else {
                    displayBrdSerialWrite();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }
    }

    // Method to connect to the display board serial port
    boolean connectDisplayCommandsSerialport() {
        clsReadFiles obj = new clsReadFiles();
        try {
            if (SerialPortList.getPortNames().length <= 0) //portIdentifier.isCurrentlyOwned()) {
            {
                obj.write_displayboard_serialport_log("Display board Serialport are 0");
                Thread.sleep(2000);
                return false;
            } else {
                obj.write_displayboard_serialport_log("Opening Display board Serialport");
                displaybrd_serialPort = (SerialPort) new SerialPort(DISPLAYBRD_COMPORT);
                displaybrd_serialPort.openPort();
                displaybrd_serialPort.setParams(getDisBrdBaudRate(),
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                displaybrd_serialPort.addEventListener(new clsDisplayBrdSerialPort.PortReader(), SerialPort.MASK_RXCHAR);
                obj.write_displayboard_serialport_log("Display board Serialport Opened ");
                return true;
            }
        } catch (SerialPortException ex) {
            obj.write_displayboard_serialport_log("Display board SerialPortException  " + ex.getMessage());
            if (find_displayserialport_available() == false) {
                obj.write_displayboard_serialport_log("Display board SerialPort is not available ");
            }
            rs485_off();
            close_displayboard_serialport();
            displaybrd_serialPort = null;
            return false;
        } catch (InterruptedException e) {
            obj.write_displayboard_serialport_log("Display board Serialport Exception  " + e.getMessage());
            rs485_off();
            close_displayboard_serialport();
            return false;
        } finally {
            obj = null;
        }
    }

    // Method to check if the display board serial port is available
    private boolean find_displayserialport_available() {
        File f = new File(DISPLAYBRD_COMPORT);
        return f.exists();
    }

    // Methods to control the RS485 signal
    static void rs485_on() {
        write_gpio_pin_state(RS485_ONOFF, RS485_SIGNAL_ON_STATE, digOutRs485Pin);
    }

    static void rs485_off() {
        write_gpio_pin_state(RS485_ONOFF, RS485_SIGNAL_OFF_STATE, digOutRs485Pin);
    }

    private static int read_data_from_file(String file_name) {
        InputStream input = null; // Initialize input stream variable
        File file = null; // Initialize file variable
        File f = null; // Initialize another file variable
        int len = 0; // Initialize length variable
        int bytesRead = 0; // Initialize bytesRead variable
        int i = 0; // Initialize counter variable

        try {
            if (!"".equals(file_name)) { // Check if file_name is not empty
                file = new File(route_filepath, file_name); // Create file object with given file name

                // Check if file exists and hard drive is detected
                if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                    input = new BufferedInputStream(new FileInputStream(file)); // Open input stream to read from file

                    len = 0; // Reset length counter

                    // Read bytes from input stream until end of file is reached
                    while ((bytesRead = input.read()) != -1) {
                        txBuffer[i++] = (byte) bytesRead; // Store byte into buffer
                    }
                    len = i; // Update length counter
                } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                    f = new File(main_route_filepath, file_name); // Create file object with given file name in another directory

                    // Check if file exists in alternate directory
                    if (f.exists()) {
                        input = new BufferedInputStream(new FileInputStream(f)); // Open input stream to read from alternate file
                        len = 0; // Reset length counter

                        // Read bytes from input stream until end of file is reached
                        while ((bytesRead = input.read()) != -1) {
                            txBuffer[i++] = (byte) bytesRead; // Store byte into buffer
                        }
                        len = i; // Update length counter
                    }
                }
                return len; // Return length of data read from file
            }
        } catch (FileNotFoundException e) { // Handle file not found exception
            e.printStackTrace(); // Print stack trace for debugging
        } catch (IOException e) { // Handle IO exception
            e.printStackTrace(); // Print stack trace for debugging
        } catch (Exception e) { // Handle other exceptions
            e.printStackTrace(); // Print stack trace for debugging
        } finally {
            if (input != null) { // Check if input stream is not null
                try {
                    input.close(); // Close input stream
                    file = null; // Clear file reference
                    f = null; // Clear alternate file reference
                } catch (IOException e) { // Handle IO exception
                    e.printStackTrace(); // Print stack trace for debugging
                }
            }
        }
        return len; // Return length of data read from file
    }
    static int pid_res_inc = 0;
    static int int_msg_inc = 0;

    static byte fd_fail_img_inc = 0;
    static byte sd_fail_img_inc = 0;
    static byte rd_fail_img_inc = 0;
    static byte id_fail_img_inc = 0;
    static byte sdart_fail_img_inc = 0;
    static byte idart_fail_img_inc = 0;

    public synchronized void displayBrdSerialWrite() {
        String[] split_str;
        int cnt = 0;
        int delay = 0;
        String str;
        int txtBufferLen = 0;
        byte brd_addr = 0;
        byte[] dis_brd_data;
        byte more_wait_time;
        byte[] buf;
        byte[] delc_buf;
        boolean wrong_kemito_file = false;
        boolean check_link_check = false;
        byte read_inc_cnt;
        int i;
        try {
            txtBufferLen = 0;
            //display data from  front. side , rear and internal display boards route data
            if (objdisQue.dIsBrdFSRQueueCnt() > 0) {
                str = objdisQue.removeFSRData();

                split_str = str.split(",");

                if (split_str.length >= 1) {
                    cnt = Byte.parseByte(split_str[1]);
                }

                txtBufferLen = read_data_from_file(split_str[0].replace("articulated", ""));
                if (txtBufferLen > 5) {
                    dis_brd_data = new byte[txtBufferLen];
                    for (i = 0; i < txtBufferLen; i++) {
                        dis_brd_data[i] = txBuffer[i];
                    }
                    if (split_str[0].endsWith("sdbarticulated")) {
                        dis_brd_data[5] = SD_ART_ADDR;
                        prepare_checksum(dis_brd_data, (short) txtBufferLen);
                    } else if (split_str[0].endsWith("idsarticulated")) {
                        dis_brd_data[5] = ID_ART_ADDR;
                        prepare_checksum(dis_brd_data, (short) txtBufferLen);
                    } else if (split_str[0].endsWith("sdsarticulated")) {
                        dis_brd_data[5] = SD_ART_ADDR;
                        prepare_checksum(dis_brd_data, (short) txtBufferLen);
                    }
                    delay = (10 * 1000 * txtBufferLen) / clsSharedVariables.getDisBrdBaudRate();
                    rs485_on();
                    setDisBrdResCame(false);
                    if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                        TimeUnit.SECONDS.sleep(1);//1000 sumitha
                    } else {
                        TimeUnit.MILLISECONDS.sleep(10); //1000 sumitha
                    }

                    try {

                        wrong_kemito_file = false;
                        if (getDisBrdName() == KEMITO_DISBRD) {
                            if (dis_brd_data[0] == 01) {
                                displaybrd_serialPort.writeBytes(dis_brd_data);
                            } else {
                                wrong_kemito_file = true;
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_pid_codes_log("Wrong Kemito File " + split_str[0]);
                                objReadFiles = null;
                            }
                        } else {

                            //send FD test cont disable packet
                            delc_buf = delete_cont_pkt(dis_brd_data[5]);
                            displaybrd_serialPort.writeBytes(delc_buf);
                            delc_buf = null;

                            Thread.sleep(2000);
                            index = 0;
                            displaybrd_serialPort.writeBytes(dis_brd_data);

                        }

                        if (getDisBrdName() == SUMITH_DISBRD) {
                            brd_addr = dis_brd_data[5];
                        }

                    } catch (SerialPortException ex) {
                        display_brd_serial_connected = false;
                        try {
                            objDisplayBrdSerial.interrupt();
                            objDisplayBrdSerial = null;
                        } catch (Exception ex1) {

                        }
                    } catch (Exception ex) {
                        if (objDisplayBrdSerial == null) {
                            objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                            objDisplayBrdSerial.start();
                        }

                    }
                    if (clsSharedVariables.getDisBrdName() == KEMITO_DISBRD && wrong_kemito_file == false) {
                        //Thread.sleep(delay + getDisbRdRetryWaitTime());
                        TimeUnit.MILLISECONDS.sleep(delay + getDisbRdRetryWaitTime());
                    } else {

                        //if (brd_type == ID_ADDR || brd_type == ID_ART_ADDR) {
                        // Thread.sleep(delay + 50);
                        // TimeUnit.MILLISECONDS.sleep(delay + 50);
                        //  } else {
                        // Thread.sleep(delay + getDisbRdRetryWaitTime());
                        TimeUnit.MILLISECONDS.sleep(delay + getDisbRdRetryWaitTime());
                        // }
                    }

                    rs485_off();

                    if (clsSharedVariables.getDisBrdName() == KEMITO_DISBRD && wrong_kemito_file == false) {
                        //  Thread.sleep(5000);
                        TimeUnit.SECONDS.sleep(5);
                    } else {
                        // Thread.sleep(500);
                    }
                    //read response from display board wait for 5 seconds
                    more_wait_time = 1;
                    if (txtBufferLen > 5000) {
                        more_wait_time = 2;
                    } else {
                        more_wait_time = 1;
                    }

                    try {
                        for (read_inc_cnt = 0; read_inc_cnt < more_wait_time * RES_WAIT_TIME && wrong_kemito_file == false; read_inc_cnt++) {
                            if (getDisBrdResCame()) {

                                if (brd_addr == FD_ADDR) {
                                    fd_filepath = null;
                                    fd_data_response_fail = false;
                                } else if (brd_addr == SD_ADDR) {
                                    sd_filepath = null;
                                    sd_data_response_fail = false;
                                } else if (brd_addr == RD_ADDR) {
                                    rd_filepath = null;
                                    rd_data_response_fail = false;
                                } else if (brd_addr == ID_ADDR) {
                                    id_filepath = null;
                                    id_data_response_fail = false;
                                } else if (brd_addr == SD_ART_ADDR) {
                                    sd_art_filepath = null;
                                    sd_art_data_response_fail = false;
                                } else if (brd_addr == ID_ART_ADDR) {
                                    id_art_filepath = null;
                                    id_art_data_response_fail = false;
                                }
                                break;
                            }
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (!getDisBrdResCame() && wrong_kemito_file == false) {
                            // add to message queue to send data again

                            cnt++;
                            if (cnt <= clsSharedVariables.getDisBrdRetries()) {
                                objdisQue.addFSRData(split_str[0] + "," + cnt);
                            }
                            if (getDisBrdName() == SUMITH_DISBRD) {
                                final byte board_type = brd_addr;
                                try {
                                    if (board_type == FD_ADDR) {
                                        fd_filepath = str;
                                        fd_data_response_fail = true;
                                    } else if (board_type == SD_ADDR) {
                                        sd_filepath = str;
                                        sd_data_response_fail = true;
                                    } else if (board_type == RD_ADDR) {
                                        rd_filepath = str;
                                        rd_data_response_fail = true;
                                    } else if (board_type == ID_ADDR) {
                                        if (getPreloadMsgDataSent() == false) {
                                            id_filepath = str;
                                            id_data_response_fail = true;
                                        }
                                    } else if (board_type == SD_ART_ADDR) {
                                        sd_art_filepath = str;
                                        sd_art_data_response_fail = true;
                                    } else if (board_type == ID_ART_ADDR) {
                                        if (getPreloadMsgDataSent() == false) {
                                            id_art_filepath = str;
                                            id_art_data_response_fail = true;
                                        }
                                    }

                                    SwingUtilities.invokeLater(() -> {
                                        if (board_type == FD_ADDR) {
                                            if (objHealth.get_fd_status() == 5) {
                                                lblFD.setIcon(fd_fail_icon);
                                            }
                                            if (fd_fail_img_inc++ > 3) {
                                                fd_fail_img_inc = 0;
                                                lblFD.setIcon(fd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("FD Board Sent Fail");
//                                            }

                                            objHealth.set_fd_status(BOARD_STATUS_FAILURE);
                                        } else if (board_type == SD_ADDR) {
                                            if (objHealth.get_sd_status() == 5) {
                                                lblSD.setIcon(sd_fail_icon);
                                            }
                                            if (sd_fail_img_inc++ > 3) {
                                                sd_fail_img_inc = 0;
                                                lblSD.setIcon(sd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("SD Board Sent Fail");
//                                            }

                                            objHealth.set_sd_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == RD_ADDR) {
                                            if (objHealth.get_rd_status() == 5) {
                                                lblRD.setIcon(rd_fail_icon);
                                            }

                                            if (rd_fail_img_inc++ > 3) {
                                                rd_fail_img_inc = 0;
                                                lblRD.setIcon(rd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("RD Board Sent Fail");
//                                            }

                                            objHealth.set_rd_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == ID_ADDR) {
                                            if (objHealth.get_id_status() == 5) {
                                                lblID.setIcon(id_fail_icon);
                                            }
                                            if (id_fail_img_inc++ > 3) {
                                                id_fail_img_inc = 0;
                                                lblID.setIcon(id_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("ID Board Sent Fail");
//                                            }
                                            if (getPreloadMsgDataSent() == true) {
                                                setPreloadMsgDataSent(false);
                                                lblPreloadMsg.setText("ID Board Sent Fail");
                                            }
                                            objHealth.set_id_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == SD_ART_ADDR) {
                                            if (objHealth.get_sd_art_status() == 5) {
                                                lblSDArt.setIcon(sd_fail_icon);
                                            }
                                            if (sdart_fail_img_inc++ > 3) {
                                                sdart_fail_img_inc = 0;
                                                lblSDArt.setIcon(sd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("SD ART Board Sent Fail");
//                                            }
                                            objHealth.set_sd_art_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == ID_ART_ADDR) {
                                            if (objHealth.get_id_art_status() == 5) {
                                                lblIDArt.setIcon(id_fail_icon);
                                            }
                                            if (idart_fail_img_inc++ > 3) {
                                                idart_fail_img_inc = 0;
                                                lblIDArt.setIcon(id_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("ID ART Board Sent Fail");
//                                            }
                                            if (getPreloadMsgDataSent() == true) {
                                                setPreloadMsgDataSent(false);
                                                lblPreloadMsg.setText("ID ART Board Sent Fail");
                                            }
                                            objHealth.set_id_art_status((byte) BOARD_STATUS_FAILURE);
                                        }
                                    });
                                } catch (Exception ex) {
                                }
                            }

                        }

                    } catch (Exception ex) {

                    }
                    //  Thread.sleep(getDisbRdGap());
                }

            } else if (objdisQue.dIsBrdQueueCnt() > 0) {
                //display data from   internal display boards for stops data
                str = objdisQue.removeData();
                split_str = str.split(",");// objdisQue.removeData().split(",");

                if (split_str.length >= 1) {
                    cnt = Byte.parseByte(split_str[1]);
                }

                txtBufferLen = read_data_from_file(split_str[0].replace("articulated", ""));
                if (txtBufferLen > 0) {
                    dis_brd_data = new byte[txtBufferLen];
                    for (i = 0; i < txtBufferLen; i++) {
                        dis_brd_data[i] = txBuffer[i];
                    }

                    if (split_str[0].endsWith("articulated")) {
                        dis_brd_data[5] = ID_ART_ADDR;
                        prepare_checksum(dis_brd_data, (short) txtBufferLen);
                    }

                    delay = (10 * 1000 * txtBufferLen) / clsSharedVariables.getDisBrdBaudRate();
                    /*
                  

                     
                     if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                     Thread.sleep(delay + getDisbRdRetryWaitTime());
                     }
                     */
                    rs485_on();
                    setDisBrdResCame(false);
                    if (clsSharedVariables.getDisBrdName() == 9600) {
                        // Thread.sleep(1000); //1000 sumitha
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        //Thread.sleep(10); //1000 sumitha
                        TimeUnit.MILLISECONDS.sleep(10);
                    }

                    try {

                        // displaybrd_serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
                        //displaybrd_serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
                        wrong_kemito_file = false;
                        if (getDisBrdName() == KEMITO_DISBRD) {
                            if (dis_brd_data[0] == 01) {
                                displaybrd_serialPort.writeBytes(dis_brd_data);
                            } else {
                                wrong_kemito_file = true;
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_pid_codes_log("Wrong Kemito File " + split_str[0]);
                                objReadFiles = null;
                            }
                        } else {
                            index = 0;
                            displaybrd_serialPort.writeBytes(dis_brd_data);

                        }

                        if (getDisBrdName() == SUMITH_DISBRD) {
                            brd_addr = dis_brd_data[5];
                        }

                    } catch (SerialPortException ex) {
                        display_brd_serial_connected = false;
                        try {
                            objDisplayBrdSerial.interrupt();
                            objDisplayBrdSerial = null;
                        } catch (Exception ex1) {
                        }
                    } catch (Exception ex) {
                        if (objDisplayBrdSerial == null) {
                            objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                            objDisplayBrdSerial.start();
                        }

                    }
                    if (clsSharedVariables.getDisBrdName() == KEMITO_DISBRD && wrong_kemito_file == false) {
                        //Thread.sleep(delay + getDisbRdRetryWaitTime());
                        TimeUnit.MILLISECONDS.sleep(delay + getDisbRdRetryWaitTime());
                    } else {
                        /* if (txtBufferLen >= 7000) {
                         delay = (delay + getDisbRdRetryWaitTime());
                         } else if (txtBufferLen >= 5000) {
                         delay = 400;
                         } else if (txtBufferLen >= 3000) {
                         delay = 300;
                         } else if (txtBufferLen >= 1000) {
                         delay = 250;
                         } else if (txtBufferLen >= 500) {
                         delay = 200;
                         } else {
                         delay = 100;
                         }*/
                        //  delay = (getDisbRdRetryWaitTime());
                        // Thread.sleep(delay + 50);
                        TimeUnit.MILLISECONDS.sleep(delay + getDisbRdRetryWaitTime());
                    }

                    rs485_off();

                    if (clsSharedVariables.getDisBrdName() == KEMITO_DISBRD && wrong_kemito_file == false) {
                        // Thread.sleep(5000);
                        TimeUnit.SECONDS.sleep(5);

                    } else {
                        // Thread.sleep(500);
                    }
                    //read response from display board wait for 5 seconds
                    more_wait_time = 1;
                    if (txtBufferLen > 5000) {
                        more_wait_time = 2;
                    } else {
                        more_wait_time = 1;
                    }

                    try {

                        for (read_inc_cnt = 0; read_inc_cnt < more_wait_time * RES_WAIT_TIME && wrong_kemito_file == false; read_inc_cnt++) {
                            if (getDisBrdResCame()) {
                                break;
                            }

                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (!getDisBrdResCame() & wrong_kemito_file == false) {
                            // add to message queue to send data again

                            cnt++;
                            if (cnt <= clsSharedVariables.getDisBrdRetries()) {
                                objdisQue.addData(split_str[0] + "," + cnt);
                            }

                            if (getDisBrdName() == SUMITH_DISBRD) {
                                final byte board_type = brd_addr;
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        if (board_type == FD_ADDR) {
                                            if (objHealth.get_fd_status() == 5) {
                                                lblFD.setIcon(fd_fail_icon);
                                            }
                                            if (fd_fail_img_inc++ > 3) {
                                                fd_fail_img_inc = 0;
                                                lblFD.setIcon(fd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("FD Board Sent Fail");
//                                            }
                                            objHealth.set_fd_status(BOARD_STATUS_FAILURE);
                                        } else if (board_type == SD_ADDR) {
                                            if (objHealth.get_sd_status() == 5) {
                                                lblSD.setIcon(sd_fail_icon);
                                            }
                                            if (sd_fail_img_inc++ > 3) {
                                                sd_fail_img_inc = 0;
                                                lblSD.setIcon(sd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("SD Board Sent Fail");
//                                            }
                                            objHealth.set_sd_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == RD_ADDR) {
                                            if (objHealth.get_rd_status() == 5) {
                                                lblRD.setIcon(rd_fail_icon);
                                            }
                                            if (rd_fail_img_inc++ > 3) {
                                                rd_fail_img_inc = 0;
                                                lblRD.setIcon(rd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("RD Board Sent Fail");
//                                            }
                                            objHealth.set_rd_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == ID_ADDR) {
                                            if (objHealth.get_id_status() == 5) {
                                                lblID.setIcon(id_fail_icon);
                                            }
                                            if (id_fail_img_inc++ > 3) {
                                                id_fail_img_inc = 0;
                                                lblID.setIcon(id_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("ID Board Sent Fail");
//                                            }
                                            if (getPreloadMsgDataSent() == true) {
                                                setPreloadMsgDataSent(false);
                                                lblPreloadMsg.setText("ID Board Sent Fail");
                                            }
                                            objHealth.set_id_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == SD_ART_ADDR) {
                                            if (objHealth.get_sd_art_status() == 5) {
                                                lblSDArt.setIcon(sd_fail_icon);
                                            }
                                            if (sdart_fail_img_inc++ > 3) {
                                                sdart_fail_img_inc = 0;
                                                lblSDArt.setIcon(sd_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("SD ART Board Sent Fail");
//                                            }
                                            objHealth.set_sd_art_status((byte) BOARD_STATUS_FAILURE);
                                        } else if (board_type == ID_ART_ADDR) {
                                            if (objHealth.get_id_art_status() == 5) {
                                                lblIDArt.setIcon(id_fail_icon);
                                            }
                                            if (idart_fail_img_inc++ > 3) {
                                                idart_fail_img_inc = 0;
                                                lblIDArt.setIcon(id_fail_icon);
                                            }
//                                            if (clsSharedVariables.get_special_msg_status() == true) {
//                                                lblSplMsg.setText("ID ART Board Sent Fail");
//                                            }
                                            if (getPreloadMsgDataSent() == true) {
                                                setPreloadMsgDataSent(false);
                                                lblPreloadMsg.setText("ID ART Board Sent Fail");
                                            }
                                            objHealth.set_id_art_status((byte) BOARD_STATUS_FAILURE);
                                        }
                                    });
                                } catch (Exception ex) {
                                }
                            }

                        }

                    } catch (Exception ex) {

                    }
                    //  Thread.sleep(getDisbRdGap());
                }

            } else if (getDisBrdLnkChkCame() == true) {

                //display data from  front. side , rear and internal display boards LInk check data
                rs485_on();
                setLinkDisBrdResCame(false);
                if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                    //  Thread.sleep(1000); //1000 sumitha
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    //  Thread.sleep(10); //1000 sumitha
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                try {
                    index = 0;
                    displaybrd_serialPort.writeBytes(disBrdLinkChkBuf);

                    brd_addr = disBrdLinkChkBuf[5];

                } catch (SerialPortException ex) {
                    display_brd_serial_connected = false;
                    try {
                        objDisplayBrdSerial.interrupt();
                        objDisplayBrdSerial = null;
                    } catch (Exception ex1) {

                    }
                } catch (Exception ex) {
                    if (objDisplayBrdSerial == null) {
                        objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                        objDisplayBrdSerial.start();
                    }

                }
                if (clsSharedVariables.getDisBrdName() == KEMITO_DISBRD) {
                    TimeUnit.MILLISECONDS.sleep(getDisbRdRetryWaitTime());
                } else {
                    if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH) {
                        TimeUnit.MILLISECONDS.sleep(getDisbRdRetryWaitTime());
                    } else {
                        TimeUnit.MICROSECONDS.sleep(getDisbRdRetryWaitTime());
                    }

                }
                rs485_off();
                try {
                    for (read_inc_cnt = 0; read_inc_cnt < RES_WAIT_TIME; read_inc_cnt++) {
                        if (getLinkDisBrdResCame()) {

                            try {
                                if (brd_addr == FD_ADDR) {
                                    if (fd_data_response_fail == true) {
                                        if (fd_filepath != null) {
                                            objdisQue.addFSRData(fd_filepath);
                                            fd_filepath = null;
                                        }
                                    }
                                    setFrontLinkDisBrdResCame(true);
                                } else if (brd_addr == SD_ADDR) {
                                    if (sd_data_response_fail == true) {
                                        if (sd_filepath != null) {
                                            objdisQue.addFSRData(sd_filepath);
                                            sd_filepath = null;
                                        }
                                    }
                                    setSideLinkDisBrdResCame(true);
                                } else if (brd_addr == RD_ADDR) {
                                    if (rd_data_response_fail == true) {
                                        if (rd_filepath != null) {
                                            objdisQue.addFSRData(rd_filepath);
                                            rd_filepath = null;
                                        }
                                    }
                                    setRearLinkDisBrdResCame(true);
                                } else if (brd_addr == ID_ADDR) {
                                    if (id_data_response_fail == true) {
                                        if (id_filepath != null) {
                                            objdisQue.addFSRData(id_filepath);
                                            id_filepath = null;
                                        }
                                    }
                                    setIntLinkDisBrdResCame(true);
                                } else if (brd_addr == SD_ART_ADDR) {
                                    if (sd_art_data_response_fail == true) {
                                        if (sd_art_filepath != null) {
                                            objdisQue.addFSRData(sd_art_filepath);
                                            sd_art_filepath = null;
                                        }
                                    }
                                    setSideArtLinkDisBrdResCame(true);
                                } else if (brd_addr == ID_ART_ADDR) {
                                    if (id_art_data_response_fail == true) {
                                        if (id_art_filepath != null) {
                                            objdisQue.addFSRData(id_art_filepath);
                                            id_art_filepath = null;
                                        }
                                    }
                                    setIntArtLinkDisBrdResCame(true);
                                }

                            } catch (Exception ex) {
                            }
                            break;
                        }
                        //  Thread.sleep(1000);
                        TimeUnit.SECONDS.sleep(1);
                    }
                    if (!getLinkDisBrdResCame()) {

                        final byte board_type = brd_addr;

                        try {
                            SwingUtilities.invokeLater(() -> {

                                if (board_type == FD_ADDR) {
                                    objBrdLnkRstTst[FRONT_DB].linkCkeck = false;
                                    if (objHealth.get_fd_status() == 5) {
                                        lblFD.setIcon(fd_fail_icon);
                                    }
                                    if (fd_fail_img_inc++ > 3) {
                                        fd_fail_img_inc = 0;
                                        lblFD.setIcon(fd_fail_icon);
                                    }
                                    objHealth.set_fd_status(BOARD_STATUS_FAILURE);
                                    setFrontLinkDisBrdResCame(false);
                                } else if (board_type == SD_ADDR) {
                                    if (objHealth.get_sd_status() == 5) {
                                        lblSD.setIcon(sd_fail_icon);
                                    }
                                    objBrdLnkRstTst[SIDE_DB].linkCkeck = false;
                                    objHealth.set_sd_status((byte) BOARD_STATUS_FAILURE);
                                    if (sd_fail_img_inc++ > 3) {
                                        sd_fail_img_inc = 0;
                                        lblSD.setIcon(sd_fail_icon);
                                    }
                                    setSideLinkDisBrdResCame(false);
                                } else if (board_type == RD_ADDR) {
                                    if (objHealth.get_rd_status() == 5) {
                                        lblRD.setIcon(rd_fail_icon);
                                    }
                                    objBrdLnkRstTst[REAR_DB].linkCkeck = false;
                                    objHealth.set_rd_status((byte) BOARD_STATUS_FAILURE);
                                    if (rd_fail_img_inc++ > 3) {
                                        rd_fail_img_inc = 0;
                                        lblRD.setIcon(rd_fail_icon);
                                    }
                                    setRearLinkDisBrdResCame(false);
                                } else if (board_type == ID_ADDR) {
                                    if (objHealth.get_id_status() == 5) {
                                        lblID.setIcon(id_fail_icon);
                                    }
                                    objBrdLnkRstTst[INT_DB].linkCkeck = false;
                                    objHealth.set_id_status((byte) BOARD_STATUS_FAILURE);
                                    if (id_fail_img_inc++ > 3) {
                                        id_fail_img_inc = 0;
                                        lblID.setIcon(id_fail_icon);
                                    }
                                    setIntLinkDisBrdResCame(false);
                                } else if (board_type == SD_ART_ADDR) {
                                    if (objHealth.get_sd_art_status() == 5) {
                                        lblSDArt.setIcon(sd_fail_icon);
                                    }
                                    objBrdLnkRstTst[SIDE_DB_ART].linkCkeck = false;
                                    objHealth.set_sd_art_status((byte) BOARD_STATUS_FAILURE);
                                    if (sdart_fail_img_inc++ > 3) {
                                        sdart_fail_img_inc = 0;
                                        lblSDArt.setIcon(sd_fail_icon);
                                    }
                                    setSideArtLinkDisBrdResCame(false);
                                } else if (board_type == ID_ART_ADDR) {
                                    if (objHealth.get_id_art_status() == 5) {
                                        lblIDArt.setIcon(id_fail_icon);
                                    }
                                    objBrdLnkRstTst[INT_DB_ART].linkCkeck = false;
                                    objHealth.set_id_art_status((byte) BOARD_STATUS_FAILURE);
                                    if (idart_fail_img_inc++ > 3) {
                                        idart_fail_img_inc = 0;
                                        lblIDArt.setIcon(id_fail_icon);
                                    }
                                    setIntArtLinkDisBrdResCame(false);
                                }
                            });
                        } catch (Exception ex) {
                        }

                    }

                } catch (Exception ex) {

                }
                setDisBrdLnkChkCame(false);
                //Thread.sleep(50);

            } else if (getIntDisbBrdMsgCame()) {
                //display data from    internal display boards  Adhoc messages data

                rs485_on();
                setDisBrdResCame(false);
                if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                    //Thread.sleep(1000); //1000 sumitha
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    // Thread.sleep(10); //1000 sumitha
                    TimeUnit.MILLISECONDS.sleep(10);
                }

                try {

                    index = 0;

                    displaybrd_serialPort.writeBytes(int_disbrd_msg_buf);

                    if (getDisBrdName() == SUMITH_DISBRD) {
                        brd_addr = int_disbrd_msg_buf[5];
                    }

                } catch (SerialPortException ex) {
                    display_brd_serial_connected = false;
                    try {
                        objDisplayBrdSerial.interrupt();
                        objDisplayBrdSerial = null;
                    } catch (Exception ex1) {

                    }
                } catch (Exception ex) {
                    if (objDisplayBrdSerial == null) {
                        objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                        objDisplayBrdSerial.start();
                    }

                }

                TimeUnit.MILLISECONDS.sleep(delay + getDisbRdRetryWaitTime());

                rs485_off();
                more_wait_time = 1;
                if (txtBufferLen > 5000) {
                    more_wait_time = 2;
                } else {
                    more_wait_time = 1;
                }

                //read response from display board wait for 5 seconds
                try {
                    for (read_inc_cnt = 0; read_inc_cnt < more_wait_time * RES_WAIT_TIME; read_inc_cnt++) {
                        if (getDisBrdResCame()) {
                            int_msg_inc = 0;
                            setIntDisbBrdMsgCame(false);
                            break;
                        }
                        TimeUnit.SECONDS.sleep(1);
                    }
                    if (!getDisBrdResCame()) {
                        // add to message queue to send data again

                        int_msg_inc++;
                        if (int_msg_inc >= clsSharedVariables.getDisBrdRetries()) {
                            setIntDisbBrdMsgCame(false);
                        }

                        if (getDisBrdName() == SUMITH_DISBRD) {
                            try {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        lblID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/id_fail.png")));
                                    }
                                });
                                objHealth.set_id_status((byte) 0);
                            } catch (Exception ex) {
                            }
                        }

                    }

                } catch (Exception ex) {

                }

                // for articulated internal bus
                if (getArtIntEnable() && getArticulatedBus()) {

                    rs485_on();
                    setDisBrdResCame(false);
                    if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                        //Thread.sleep(1000); //1000 sumitha
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        // Thread.sleep(10); //1000 sumitha
                        TimeUnit.MILLISECONDS.sleep(10);
                    }

                    try {

                        //  displaybrd_serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
                        //  displaybrd_serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
                        index = 0;
                        int_disbrd_msg_buf[5] = ID_ART_ADDR;
                        displaybrd_serialPort.writeBytes(int_disbrd_msg_buf);

                    } catch (SerialPortException ex) {
                        display_brd_serial_connected = false;
                        try {
                            objDisplayBrdSerial.interrupt();
                            objDisplayBrdSerial = null;
                        } catch (Exception ex1) {

                        }
                    } catch (Exception ex) {
                        if (objDisplayBrdSerial == null) {
                            objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                            objDisplayBrdSerial.start();
                        }

                    }

                    TimeUnit.MILLISECONDS.sleep(delay + getDisbRdRetryWaitTime());

                    rs485_off();
                    more_wait_time = 1;
                    if (txtBufferLen > 5000) {
                        more_wait_time = 2;
                    } else {
                        more_wait_time = 1;
                    }

                    //read response from display board wait for 5 seconds
                    try {
                        for (read_inc_cnt = 0; read_inc_cnt < more_wait_time * RES_WAIT_TIME; read_inc_cnt++) {
                            if (getDisBrdResCame()) {
                                int_msg_inc = 0;
                                setIntDisbBrdMsgCame(false);
                                break;
                            }
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (!getDisBrdResCame()) {
                            // add to message queue to send data again

                            int_msg_inc++;
                            if (int_msg_inc >= clsSharedVariables.getDisBrdRetries()) {
                                setIntDisbBrdMsgCame(false);
                            }

                            if (getDisBrdName() == SUMITH_DISBRD) {
                                try {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        public void run() {
                                            lblIDArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/id_fail.png")));
                                        }
                                    });
                                    objHealth.set_id_status((byte) 0);
                                } catch (Exception ex) {
                                }
                            }

                        }

                    } catch (Exception ex) {

                    }
                }
            } else if (getDisBrdDelPktCame()) {

                setDisBrdDelPktCame(false);
                rs485_on();
                setDisBrdResCame(false);

                if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    TimeUnit.MILLISECONDS.sleep(10);
                }

                try {

                    index = 0;
                    dis_brd_data = delete_pkt_display_board(INT_DB, (byte) 254);// DRIVER_NO_DISPLAY_INT_PKT_NO);
                    displaybrd_serialPort.writeBytes(dis_brd_data);

                    if (getArtIntEnable() && getArticulatedBus()) {
                        //write code
                    }
                    TimeUnit.MILLISECONDS.sleep(getDisbRdRetryWaitTime());

                    rs485_off();
                    TimeUnit.SECONDS.sleep(2);

                } catch (SerialPortException ex) {
                    display_brd_serial_connected = false;
                    try {
                        objDisplayBrdSerial.interrupt();
                        objDisplayBrdSerial = null;
                    } catch (Exception ex1) {

                    }
                } catch (Exception ex) {
                    if (objDisplayBrdSerial == null) {
                        objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                        objDisplayBrdSerial.start();
                    }

                }

                // for articulated internal bus
                if (getArtIntEnable() && getArticulatedBus()) {
                    rs485_on();
                    setDisBrdResCame(false);
                    if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        TimeUnit.MILLISECONDS.sleep(10);
                    }

                    try {

                        index = 0;
                        dis_brd_data = delete_pkt_display_board(INT_DB, (byte) 5);

                        dis_brd_data[5] = ID_ART_ADDR;
                        displaybrd_serialPort.writeBytes(dis_brd_data);

                        TimeUnit.MILLISECONDS.sleep(getDisbRdRetryWaitTime());

                        rs485_off();
                        TimeUnit.SECONDS.sleep(2);

                    } catch (SerialPortException ex) {
                        display_brd_serial_connected = false;
                        try {
                            objDisplayBrdSerial.interrupt();
                            objDisplayBrdSerial = null;
                        } catch (Exception ex1) {

                        }
                    } catch (Exception ex) {
                        if (objDisplayBrdSerial == null) {
                            objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                            objDisplayBrdSerial.start();
                        }

                    }
                }
            } else if (objDisBrdPid.disBrdQueueCnt() > 0 && objdisQue.dIsBrdQueueCnt() <= 0) {

                //display data from  front. side , rear and internal display boards PID data
                buf = objDisBrdPid.removeData();

                if (buf != null) {

                    rs485_on();
                    setDisBrdResCame(false);
                    setPidResCame(false);

                    setDisBrdLnkChkCame(true);
                    if (clsSharedVariables.getDisBrdBaudRate() == 9600) {
                        //Thread.sleep(1000); //1000 sumitha
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        TimeUnit.MILLISECONDS.sleep(10);
                    }

                    try {

                        if (clsSharedVariables.getLogFilesRequired() == true) {
                            clsReadFiles objReadFiles = new clsReadFiles();
                            objReadFiles.write_pid_codes_log("PID PKT SENT");
                            objReadFiles = null;
                        }
                        index = 0;
                        displaybrd_serialPort.writeBytes(buf);

                        if (getDisBrdName() == SUMITH_DISBRD) {
                            brd_addr = buf[5];
                        }

                        if (buf[FC_BYTE] == LINK_CHECK_PKT) {
                            check_link_check = true;
                        } else {
                            check_link_check = false;
                        }
                    } catch (SerialPortException ex) {
                        display_brd_serial_connected = false;
                        try {
                            objDisplayBrdSerial.interrupt();
                            objDisplayBrdSerial = null;
                        } catch (Exception ex1) {
                        }
                    } catch (Exception ex) {
                        if (objDisplayBrdSerial == null) {
                            objDisplayBrdSerial = new ReadDisplayBrdPortDataFromSerialPort();
                            objDisplayBrdSerial.start();
                        }
                    }
                    if (clsSharedVariables.getDisBrdName() == KEMITO_DISBRD) {
                        // Thread.sleep(delay + getDisbRdRetryWaitTime());
                        TimeUnit.MILLISECONDS.sleep(getDisbRdRetryWaitTime());
                    } else {
                        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH) {
                            TimeUnit.MILLISECONDS.sleep(getDisbRdRetryWaitTime());
                        } else {
                            TimeUnit.MICROSECONDS.sleep(getDisbRdRetryWaitTime());
                        }
                    }

                    // Thread.sleep(getDisbRdRetryWaitTime());
                    rs485_off();
                    try {
                        for (read_inc_cnt = 0; read_inc_cnt < RES_WAIT_TIME; read_inc_cnt++) {
                            if (check_link_check) {
                                if (getLinkDisBrdResCame()) {
                                    try {
                                        if (brd_addr == FD_ADDR) {
                                            if (fd_data_response_fail == true) {
                                                if (fd_filepath != null) {
                                                    objdisQue.addFSRData(fd_filepath);
                                                    fd_filepath = null;
                                                }
                                            }
                                            setFrontLinkDisBrdResCame(true);
                                        } else if (brd_addr == SD_ADDR) {
                                            if (sd_data_response_fail == true) {
                                                if (sd_filepath != null) {
                                                    objdisQue.addFSRData(sd_filepath);
                                                    sd_filepath = null;
                                                }
                                            }
                                            setSideLinkDisBrdResCame(true);
                                        } else if (brd_addr == RD_ADDR) {
                                            if (rd_data_response_fail == true) {
                                                if (rd_filepath != null) {
                                                    objdisQue.addFSRData(rd_filepath);
                                                    rd_filepath = null;
                                                }
                                            }
                                            setRearLinkDisBrdResCame(true);
                                        } else if (brd_addr == ID_ADDR) {
                                            if (id_data_response_fail == true) {
                                                if (id_filepath != null) {
                                                    objdisQue.addFSRData(id_filepath);
                                                    id_filepath = null;
                                                }
                                            }
                                            setIntLinkDisBrdResCame(true);
                                        } else if (brd_addr == SD_ART_ADDR) {
                                            if (sd_art_data_response_fail == true) {
                                                if (sd_art_filepath != null) {
                                                    objdisQue.addFSRData(sd_art_filepath);
                                                    sd_art_filepath = null;
                                                }
                                            }
                                            setSideArtLinkDisBrdResCame(true);
                                        } else if (brd_addr == ID_ART_ADDR) {
                                            if (id_art_data_response_fail == true) {
                                                if (id_art_filepath != null) {
                                                    objdisQue.addFSRData(id_art_filepath);
                                                    id_art_filepath = null;
                                                }
                                            }
                                            setIntArtLinkDisBrdResCame(true);
                                        }
                                    } catch (Exception ex) {
                                    }
                                    break;
                                }
                            } else if (getPidResCame()) {
                                pid_res_inc = 0;
                                try {
                                    if (brd_addr == FD_ADDR) {
                                        if (fd_data_response_fail == true) {
                                            if (fd_filepath != null) {
                                                objdisQue.addFSRData(fd_filepath);
                                                fd_filepath = null;
                                            }
                                        }
                                        setFrontLinkDisBrdResCame(true);
                                    } else if (brd_addr == SD_ADDR) {
                                        if (sd_data_response_fail == true) {
                                            if (sd_filepath != null) {
                                                objdisQue.addFSRData(sd_filepath);
                                                sd_filepath = null;
                                            }
                                        }
                                        setSideLinkDisBrdResCame(true);
                                    } else if (brd_addr == RD_ADDR) {
                                        if (rd_data_response_fail == true) {
                                            if (rd_filepath != null) {
                                                objdisQue.addFSRData(rd_filepath);
                                                rd_filepath = null;
                                            }
                                        }
                                        setRearLinkDisBrdResCame(true);
                                    } else if (brd_addr == ID_ADDR) {
                                        if (id_data_response_fail == true) {

                                            if (id_filepath != null) {
                                                objdisQue.addFSRData(id_filepath);
                                                id_filepath = null;
                                            }
                                        }
                                        setIntLinkDisBrdResCame(true);
                                    } else if (brd_addr == SD_ART_ADDR) {
                                        if (sd_art_data_response_fail == true) {
                                            if (sd_art_filepath != null) {
                                                objdisQue.addFSRData(sd_art_filepath);
                                                sd_art_filepath = null;
                                            }
                                        }
                                        setSideArtLinkDisBrdResCame(true);
                                    } else if (brd_addr == ID_ART_ADDR) {
                                        if (id_art_data_response_fail == true) {
                                            if (id_art_filepath != null) {
                                                objdisQue.addFSRData(id_art_filepath);
                                                id_art_filepath = null;
                                            }
                                        }
                                        setIntArtLinkDisBrdResCame(true);
                                    }
                                } catch (Exception ex) {
                                }
                                break;
                            }
                            TimeUnit.SECONDS.sleep(1);
                        }
                        if (check_link_check) {
                            if (!getLinkDisBrdResCame()) {
                                final byte board_type = brd_addr;

                                try {
                                    SwingUtilities.invokeLater(() -> {

                                        if (board_type == FD_ADDR) {
                                            objBrdLnkRstTst[FRONT_DB].linkCkeck = false;
                                            if (objHealth.get_fd_status() == 5) {
                                                lblFD.setIcon(fd_fail_icon);
                                            }
                                            if (fd_fail_img_inc++ > 3) {
                                                fd_fail_img_inc = 0;
                                                lblFD.setIcon(fd_fail_icon);
                                            }
                                            objHealth.set_fd_status(BOARD_STATUS_FAILURE);
                                            setFrontLinkDisBrdResCame(false);
                                        } else if (board_type == SD_ADDR) {
                                            if (objHealth.get_sd_status() == 5) {
                                                lblSD.setIcon(sd_fail_icon);
                                            }
                                            objBrdLnkRstTst[SIDE_DB].linkCkeck = false;
                                            objHealth.set_sd_status((byte) BOARD_STATUS_FAILURE);
                                            if (sd_fail_img_inc++ > 3) {
                                                sd_fail_img_inc = 0;
                                                lblSD.setIcon(sd_fail_icon);
                                            }
                                            setSideLinkDisBrdResCame(false);
                                        } else if (board_type == RD_ADDR) {
                                            if (objHealth.get_rd_status() == 5) {
                                                lblRD.setIcon(rd_fail_icon);
                                            }
                                            objBrdLnkRstTst[REAR_DB].linkCkeck = false;
                                            objHealth.set_rd_status((byte) BOARD_STATUS_FAILURE);
                                            if (rd_fail_img_inc++ > 3) {
                                                rd_fail_img_inc = 0;
                                                lblRD.setIcon(rd_fail_icon);
                                            }
                                            setRearLinkDisBrdResCame(false);
                                        } else if (board_type == ID_ADDR) {
                                            if (objHealth.get_id_status() == 5) {
                                                lblID.setIcon(id_fail_icon);
                                            }
                                            objBrdLnkRstTst[INT_DB].linkCkeck = false;
                                            objHealth.set_id_status((byte) BOARD_STATUS_FAILURE);
                                            if (id_fail_img_inc++ > 3) {
                                                id_fail_img_inc = 0;
                                                lblID.setIcon(id_fail_icon);
                                            }
                                            setIntLinkDisBrdResCame(false);
                                        } else if (board_type == SD_ART_ADDR) {
                                            if (objHealth.get_sd_art_status() == 5) {
                                                lblSDArt.setIcon(sd_fail_icon);
                                            }
                                            objBrdLnkRstTst[SIDE_DB_ART].linkCkeck = false;
                                            objHealth.set_sd_art_status((byte) BOARD_STATUS_FAILURE);
                                            if (sdart_fail_img_inc++ > 3) {
                                                sdart_fail_img_inc = 0;
                                                lblSDArt.setIcon(sd_fail_icon);
                                            }
                                            setSideArtLinkDisBrdResCame(false);
                                        } else if (board_type == ID_ART_ADDR) {
                                            if (objHealth.get_id_art_status() == 5) {
                                                lblIDArt.setIcon(id_fail_icon);
                                            }
                                            objBrdLnkRstTst[INT_DB_ART].linkCkeck = false;
                                            objHealth.set_id_art_status((byte) BOARD_STATUS_FAILURE);
                                            if (idart_fail_img_inc++ > 3) {
                                                idart_fail_img_inc = 0;
                                                lblIDArt.setIcon(id_fail_icon);
                                            }
                                            setIntArtLinkDisBrdResCame(false);
                                        }
                                    });
                                } catch (Exception ex) {
                                }

                            }
                        } else if (!getPidResCame()) {
                            // add to message queue to send data again
                            pid_res_inc++;
                            if (pid_res_inc <= clsSharedVariables.getDisBrdRetries()) {
                                objDisBrdPid.addData(buf);
                            }
                            if (getDisBrdName() == SUMITH_DISBRD) {

                                // final byte board_type = brd_addr;

                                /*
                                 try {
                                 SwingUtilities.invokeLater(() -> {

                                 if (board_type == FD_ADDR) {
                                 lblFD.setIcon(fd_fail_icon);
                                 objHealth.set_fd_status((byte) 0);
                                 } else if (board_type == SD_ADDR) {
                                 lblSD.setIcon(sd_fail_icon);
                                 objHealth.set_sd_status((byte) 0);
                                 } else if (board_type == RD_ADDR) {
                                 lblRD.setIcon(rd_fail_icon);
                                 objHealth.set_rd_status((byte) 0);
                                 } else if (board_type == ID_ADDR) {
                                 lblID.setIcon(id_fail_icon);
                                 objHealth.set_id_status((byte) 0);
                                 } else if (board_type == SD_ART_ADDR) {
                                 lblSDArt.setIcon(sd_fail_icon);
                                 objHealth.set_sd_art_status((byte) 0);
                                 } else if (board_type == ID_ART_ADDR) {
                                 lblIDArt.setIcon(id_fail_icon);
                                 objHealth.set_id_art_status((byte) 0);
                                 }

                                 });
                                 } catch (Exception ex) {
                                 }
                                 */
                            }
                        }
                        //Thread.sleep(100);
                    } catch (Exception ex) {

                    }
                }
            }

        } catch (Exception e) {

        } finally {
            split_str = null;
            dis_brd_data = null;
            buf = null;
            str = null;
            delc_buf = null;
        }
        try {

            TimeUnit.MILLISECONDS.sleep(getDisbRdGap());
        } catch (InterruptedException ex) {

        }

    }
    static short disbrd_pkt_length = 0;
    static long msb_val;
    long lsb_val;
    long pid_val;
    byte pkt_type;
    short pid_code;
    short dtc_code;
    static byte index = 0;
    short disbbrd_buf_inc = 0;
    static byte[] dis_brd_rx_buf = new byte[200];

    private class PortReader implements SerialPortEventListener {

        clsReadFiles objReadFiles = new clsReadFiles();

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && (event.getEventValue() > 0)) {
                byte[] pkt_buf = null;
                byte[] bytes;
                String pid_res_string;
                int i = 0;
                int inc = 0;
                try {

                    if (getDisBrdName() == KEMITO_DISBRD) {
                        setDisBrdResCame(true);
                    } else if (getDisBrdName() == SUMITH_DISBRD) {
                        boolean data_check = false;
                        pkt_buf = displaybrd_serialPort.readBytes();
                        byte brd_type = FRONT_DB;

                        for (i = 0; i < pkt_buf.length; i++) {
                            switch (index) {
                                case 0:
                                    if (pkt_buf[i] == AA_HEX) {
                                        index = 1;
                                    }
                                    break;
                                case 1:
                                    if (pkt_buf[i] == CC_HEX) {
                                        index = 2;
                                        disbrd_pkt_length = 0;
                                        disbbrd_buf_inc = 0;
                                        data_check = false;
                                    }
                                    break;
                                case 2:
                                    msb_val = pkt_buf[i] & 0xFF;
                                    dis_brd_rx_buf[disbbrd_buf_inc++] = pkt_buf[i];
                                    msb_val <<= 8;
                                    index = 3;
                                    break;
                                case 3:
                                    lsb_val = pkt_buf[i] & 0xFF;
                                    dis_brd_rx_buf[disbbrd_buf_inc++] = pkt_buf[i];
                                    disbrd_pkt_length = (short) (msb_val | lsb_val);

                                    if (clsSharedVariables.getPidsDbEnable() == false) {
                                        if (disbrd_pkt_length >= 200) {
                                            index = 0;
                                            break;
                                        }
                                    } else if (disbrd_pkt_length >= 200) {
                                        index = 0;
                                        break;
                                    }
                                    index = 4;
                                    break;
                                case 4:
                                    dis_brd_rx_buf[disbbrd_buf_inc++] = pkt_buf[i];
                                    if (disbbrd_buf_inc >= disbrd_pkt_length) {
                                        data_check = true;
                                        index = 0;
                                    }
                            }

                            if (data_check == true) {
                                if (dis_brd_rx_buf[RES_SRC_BYTE] == clsDefines.BROADCAST_ADDR) {
                                    brd_type = FRONT_DB;
                                } else if (dis_brd_rx_buf[RES_SRC_BYTE] == FD_ADDR) {
                                    brd_type = FRONT_DB;
                                    objBrdLnkRstTst[brd_type].linkCkeck = true;
                                    try {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (lblFD != null) {
                                                    fd_fail_img_inc = 0;
                                                    lblFD.setIcon(fd_succ_icon);
                                                    lblFD.repaint();
//                                                    if (clsSharedVariables.get_special_msg_status() == true) {
//                                                        if (lblSplMsg != null) {
//                                                            lblSplMsg.setText("FD Board Sent Success");
//                                                        }
//                                                    }
                                                    objHealth.set_fd_status(BOARD_STATUS_SUCCESS);
                                                }
                                            }
                                        });
                                    } catch (Exception ex) {
                                    }
                                } else if (dis_brd_rx_buf[RES_SRC_BYTE] == SD_ADDR) {
                                    brd_type = SIDE_DB;
                                    objBrdLnkRstTst[brd_type].linkCkeck = true;
                                    try {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (lblSD != null) {
                                                    lblSD.setIcon(sd_succ_icon);
                                                    lblSD.repaint();

//                                                    if (clsSharedVariables.get_special_msg_status() == true) {
//                                                        if (lblSplMsg != null) {
//                                                            lblSplMsg.setText("SD Board Sent Success");
//                                                        }
//                                                    }
                                                    objHealth.set_sd_status((byte) BOARD_STATUS_SUCCESS);
                                                }
                                            }

                                        });
                                    } catch (Exception ex) {
                                    }
                                } else if (dis_brd_rx_buf[RES_SRC_BYTE] == RD_ADDR) {
                                    brd_type = REAR_DB;
                                    objBrdLnkRstTst[brd_type].linkCkeck = true;
                                    try {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (lblRD != null) {
                                                    lblRD.setIcon(rd_succ_icon);
                                                    lblRD.repaint();
//                                                    if (clsSharedVariables.get_special_msg_status() == true) {
//                                                        if (lblSplMsg != null) {
//                                                            lblSplMsg.setText("RD Board Sent Success");
//                                                        }
//                                                    }
                                                    objHealth.set_rd_status((byte) BOARD_STATUS_SUCCESS);

                                                }
                                            }
                                        });
                                    } catch (Exception ex) {
                                    }
                                } else if (dis_brd_rx_buf[RES_SRC_BYTE] == ID_ADDR) {
                                    brd_type = INT_DB;
                                    objBrdLnkRstTst[brd_type].linkCkeck = true;
                                    try {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (lblID != null) {
                                                    lblID.setIcon(id_succ_icon);
                                                    lblID.repaint();
//                                                    if (clsSharedVariables.get_special_msg_status() == true) {
//                                                        if (lblSplMsg != null) {
//                                                            lblSplMsg.setText("ID Board Sent Success");
//                                                        }
//                                                    }
                                                    objHealth.set_id_status((byte) BOARD_STATUS_SUCCESS);
                                                    if (getPreloadMsgDataSent() == true) {
                                                        setPreloadMsgDataSent(false);
                                                        if (lblPreloadMsg != null) {
                                                            lblPreloadMsg.setText("ID Board Sent Success");
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                    } catch (Exception ex) {
                                    }
                                } else if (dis_brd_rx_buf[RES_SRC_BYTE] == SD_ART_ADDR) {
                                    brd_type = SIDE_DB_ART;
                                    objBrdLnkRstTst[brd_type].linkCkeck = true;
                                    try {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (lblSDArt != null) {
                                                    lblSDArt.setIcon(sd_succ_icon);

//                                                    if (clsSharedVariables.get_special_msg_status() == true) {
//                                                        if (lblSplMsg != null) {
//                                                            lblSplMsg.setText("SD Art Board Sent Success");
//                                                        }
//                                                    }
                                                    objHealth.set_sd_art_status((byte) BOARD_STATUS_SUCCESS);
                                                }
                                            }

                                        });
                                    } catch (Exception ex) {
                                    }
                                } else if (dis_brd_rx_buf[RES_SRC_BYTE] == ID_ART_ADDR) {
                                    brd_type = INT_DB_ART;
                                    objBrdLnkRstTst[brd_type].linkCkeck = true;
                                    try {
                                        SwingUtilities.invokeLater(new Runnable() {
                                            public void run() {
                                                if (lblIDArt != null) {
                                                    lblIDArt.setIcon(id_succ_icon);
//                                                    if (clsSharedVariables.get_special_msg_status() == true) {
//                                                        if (lblSplMsg != null) {
//                                                            lblSplMsg.setText("ID Art Board Sent Success");
//                                                        }
//                                                    }
                                                    objHealth.set_id_art_status((byte) BOARD_STATUS_SUCCESS);
                                                    if (getPreloadMsgDataSent() == true) {
                                                        setPreloadMsgDataSent(false);
                                                        if (lblPreloadMsg != null) {
                                                            lblPreloadMsg.setText("ID Art Board Sent Success");
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                    } catch (Exception ex) {
                                    }
                                }

                                pkt_type = dis_brd_rx_buf[RES_FC_BYTE];
                                switch (pkt_type) {
                                    case LINK_CHECK_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        setPidResCame(true);
                                        objBrdLnkRstTst[brd_type].linkCkeck = true;
                                        break;
                                    case SOFTREST_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        objBrdLnkRstTst[brd_type].reset = true;
                                        break;
                                    case TEST_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        objBrdLnkRstTst[brd_type].test = true;
                                        break;
                                    case DELETE_PKT_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        objBrdLnkRstTst[brd_type].delPkt = true;
                                        break;
                                    case TESTC_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        objBrdLnkRstTst[brd_type].test_cont = true;
                                        break;
                                    case CONFIGURATION_INTENSITY_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        objBrdLnkRstTst[brd_type].intensity = true;
                                        break;

                                    case TIME_RES_PKT:
                                        setLinkDisBrdResCame(true);
                                        break;
                                    case DATA_RES_PKT:

                                        setDisBrdResCame(true);
                                        break;
                                    case DTC_CODE_RES_PKT: {
                                        setPidResCame(true);
                                        msb_val = dis_brd_rx_buf[RES_FC_BYTE + 1] & 0xFF;
                                        msb_val <<= 8;
                                        lsb_val = dis_brd_rx_buf[RES_FC_BYTE + 2] & 0xFF;
                                        dtc_code = (short) (msb_val | lsb_val);

                                        if (dis_brd_rx_buf[RES_FC_BYTE + 3] == RES_SUCCESS) {
                                            switch (dtc_code) {
                                                case DTC_HIGH_VOLTAGE:
                                                    if (brd_type == FRONT_DB) {
                                                        PanDisplayBoardDiag.front_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("FRONT DTC HIGH VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == SIDE_DB) {
                                                        PanDisplayBoardDiag.side_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("SIDE DTC HIGH VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == REAR_DB) {
                                                        PanDisplayBoardDiag.rear_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("REAR DTC HIGH VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == INT_DB) {
                                                        PanDisplayBoardDiag.int_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("INT DTC HIGH VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == SIDE_DB_ART) {
                                                        PanDisplayBoardDiag.sideart_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("SIDE ART DTC HIGH VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == INT_DB_ART) {
                                                        PanDisplayBoardDiag.intart_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("INTART DTC HIGH VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    }
                                                    break;
                                                case DTC_LOW_VOLTAGE:
                                                    if (brd_type == FRONT_DB) {
                                                        PanDisplayBoardDiag.front_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("FRONT DTC LOW VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == SIDE_DB) {
                                                        PanDisplayBoardDiag.side_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("SIDE DTC LOW VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == REAR_DB) {
                                                        PanDisplayBoardDiag.rear_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("REAR DTC LOW VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == INT_DB) {
                                                        PanDisplayBoardDiag.int_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("INT DTC LOW VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == SIDE_DB_ART) {
                                                        PanDisplayBoardDiag.sideart_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("SIDE ART DTC LOW VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == INT_DB_ART) {
                                                        PanDisplayBoardDiag.intart_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("INT ART DTC LOW VOLTAGE RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    }
                                                    break;
                                                default:
                                                    PanDisplayBoardDiag.front_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                    if (brd_type == FRONT_DB) {
                                                        PanDisplayBoardDiag.front_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("FRONT DTC OVER HEAT RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == SIDE_DB) {
                                                        PanDisplayBoardDiag.side_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("SIDE DTC OVER HEAT RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == REAR_DB) {
                                                        PanDisplayBoardDiag.rear_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("REAR DTC OVER HEAT RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == INT_DB) {
                                                        PanDisplayBoardDiag.int_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("INT DTC OVER HEAT RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == SIDE_DB_ART) {
                                                        PanDisplayBoardDiag.sideart_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("SIDE ART DTC OVER HEAT RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    } else if (brd_type == INT_DB_ART) {
                                                        PanDisplayBoardDiag.intart_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 4];
                                                        objReadFiles.write_pid_codes_log("INT ART DTC OVER HEAT RES : " + dis_brd_rx_buf[RES_FC_BYTE + 4]);

                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                    case DTC_COUNT_CODE_RES_PKT: {
                                        setPidResCame(true);
                                        msb_val = dis_brd_rx_buf[RES_FC_BYTE + 1] & 0xFF;
                                        msb_val <<= 8;
                                        lsb_val = dis_brd_rx_buf[RES_FC_BYTE + 2] & 0xFF;
                                        dtc_code = (short) (msb_val | lsb_val);
                                        if (dis_brd_rx_buf[RES_FC_BYTE + 3] == WRITE_MODE) {
                                            setLinkDisBrdResCame(true);
                                            objBrdLnkRstTst[brd_type].dtcCodes = true;
                                            break;
                                        } else if (dis_brd_rx_buf[RES_FC_BYTE + 4] == RES_SUCCESS) {
                                            try {
                                                switch (dtc_code) {
                                                    case DTC_HIGH_VOLTAGE:
                                                        if (brd_type == FRONT_DB) {
                                                            PanDisplayBoardDiag.front_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.front_high_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("FRONT HIGH VOLT COUNT RES : " + PanDisplayBoardDiag.front_high_volt_cnt);

                                                        } else if (brd_type == SIDE_DB) {
                                                            PanDisplayBoardDiag.side_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.side_high_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("SIDE HIGH VOLT COUNT RES : " + PanDisplayBoardDiag.side_high_volt_cnt);

                                                        } else if (brd_type == REAR_DB) {
                                                            PanDisplayBoardDiag.rear_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.rear_high_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("REAR HIGH VOLT COUNT RES : " + PanDisplayBoardDiag.rear_high_volt_cnt);

                                                        } else if (brd_type == INT_DB) {
                                                            PanDisplayBoardDiag.int_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.int_high_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("INT HIGH VOLT COUNT RES : " + PanDisplayBoardDiag.int_high_volt_cnt);
                                                        } else if (brd_type == SIDE_DB_ART) {
                                                            PanDisplayBoardDiag.sideart_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.sideart_high_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("SIDE ART HIGH VOLT COUNT RES : " + PanDisplayBoardDiag.sideart_high_volt_cnt);

                                                        } else if (brd_type == INT_DB_ART) {
                                                            PanDisplayBoardDiag.intart_high_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.intart_high_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("INT ART HIGH VOLT COUNT RES : " + PanDisplayBoardDiag.intart_high_volt_cnt);
                                                        }
                                                        break;
                                                    case DTC_LOW_VOLTAGE:
                                                        if (brd_type == FRONT_DB) {
                                                            PanDisplayBoardDiag.front_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.front_low_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("FRONT LOW VOLT COUNT RES : " + PanDisplayBoardDiag.front_low_volt_cnt);

                                                        } else if (brd_type == SIDE_DB) {
                                                            PanDisplayBoardDiag.side_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.side_low_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("SIDE LOW VOLT COUNT RES : " + PanDisplayBoardDiag.side_low_volt_cnt);

                                                        } else if (brd_type == REAR_DB) {
                                                            PanDisplayBoardDiag.rear_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.rear_low_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("REAR LOW VOLT COUNT RES : " + PanDisplayBoardDiag.rear_low_volt_cnt);

                                                        } else if (brd_type == INT_DB) {
                                                            PanDisplayBoardDiag.int_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.int_low_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("INT LOW VOLT COUNT RES : " + PanDisplayBoardDiag.int_low_volt_cnt);

                                                        } else if (brd_type == SIDE_DB_ART) {
                                                            PanDisplayBoardDiag.sideart_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.sideart_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("SIDE ART LOW VOLT COUNT RES : " + PanDisplayBoardDiag.sideart_low_volt_cnt);

                                                        } else if (brd_type == INT_DB_ART) {
                                                            PanDisplayBoardDiag.intart_low_volt = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.intart_low_volt_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("INT ART LOW VOLT COUNT RES : " + PanDisplayBoardDiag.intart_low_volt_cnt);

                                                        }
                                                        break;
                                                    default:
                                                        if (brd_type == FRONT_DB) {
                                                            PanDisplayBoardDiag.front_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.front_over_heat_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("FRONT OVERHEAT VOLT COUNT RES : " + PanDisplayBoardDiag.front_over_heat_cnt);

                                                        } else if (brd_type == SIDE_DB) {
                                                            PanDisplayBoardDiag.side_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.side_over_heat_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("SIDE OVERHEAT VOLT COUNT RES : " + PanDisplayBoardDiag.side_over_heat_cnt);

                                                        } else if (brd_type == REAR_DB) {
                                                            PanDisplayBoardDiag.rear_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.rear_over_heat_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("REAR OVERHEAT VOLT COUNT RES : " + PanDisplayBoardDiag.rear_over_heat_cnt);

                                                        } else if (brd_type == INT_DB) {
                                                            PanDisplayBoardDiag.int_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.int_over_heat_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("INT OVERHEAT VOLT COUNT RES : " + PanDisplayBoardDiag.int_over_heat_cnt);

                                                        } else if (brd_type == SIDE_DB_ART) {
                                                            PanDisplayBoardDiag.sideart_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.sideart_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("SIDE ART OVERHEAT VOLT COUNT RES : " + PanDisplayBoardDiag.sideart_over_heat_cnt);

                                                        } else if (brd_type == INT_DB_ART) {
                                                            PanDisplayBoardDiag.intart_over_heat = dis_brd_rx_buf[RES_FC_BYTE + 5] & 0xFF;

                                                            PanDisplayBoardDiag.intart_over_heat_cnt = dis_brd_rx_buf[RES_FC_BYTE + 6] & 0xFF;
                                                            objReadFiles.write_pid_codes_log("INT ART OVERHEAT VOLT COUNT RES : " + PanDisplayBoardDiag.intart_over_heat_cnt);

                                                        }
                                                        break;
                                                }
                                            } catch (Exception ex) {
                                            }
                                        }
                                        break;
                                    }
                                    case DTC_CODEALL_RES_PKT: {
                                        if (dis_brd_rx_buf[RES_FC_BYTE + 1] == RES_SUCCESS) {
                                            try {

                                                inc = RES_FC_BYTE + 2;
                                                if (brd_type == FRONT_DB) {

                                                    PanDisplayBoardDiag.front_cur_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_high_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_high_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_low_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_low_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_cur_temp = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_over_heat = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.front_over_heat_cnt = dis_brd_rx_buf[inc++] & 0xFF;

                                                    objReadFiles.write_disbrd_dtc_file();
                                                } else if (brd_type == SIDE_DB) {
                                                    PanDisplayBoardDiag.side_cur_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_high_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_high_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_low_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_low_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_cur_temp = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_over_heat = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.side_over_heat_cnt = dis_brd_rx_buf[inc++] & 0xFF;

                                                    objReadFiles.write_disbrd_dtc_file();
                                                } else if (brd_type == REAR_DB) {
                                                    PanDisplayBoardDiag.rear_cur_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_high_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_high_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_low_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_low_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_cur_temp = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_over_heat = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.rear_over_heat_cnt = dis_brd_rx_buf[inc++] & 0xFF;

                                                    objReadFiles.write_disbrd_dtc_file();
                                                } else if (brd_type == INT_DB) {
                                                    PanDisplayBoardDiag.int_cur_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_high_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_high_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_low_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_low_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_cur_temp = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_over_heat = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.int_over_heat_cnt = dis_brd_rx_buf[inc++] & 0xFF;

                                                    objReadFiles.write_disbrd_dtc_file();
                                                } else if (brd_type == SIDE_DB_ART) {
                                                    PanDisplayBoardDiag.sideart_cur_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_high_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_high_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_low_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_low_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_cur_temp = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_over_heat = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.sideart_over_heat_cnt = dis_brd_rx_buf[inc++] & 0xFF;

                                                    objReadFiles.write_disbrd_dtc_file();
                                                } else if (brd_type == INT_DB_ART) {
                                                    PanDisplayBoardDiag.intart_cur_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_high_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_high_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_low_volt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_low_volt_cnt = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_cur_temp = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_over_heat = dis_brd_rx_buf[inc++] & 0xFF;
                                                    PanDisplayBoardDiag.intart_over_heat_cnt = dis_brd_rx_buf[inc++] & 0xFF;

                                                    objReadFiles.write_disbrd_dtc_file();
                                                }
                                            } catch (Exception ex) {
                                            }
                                        }

                                        setPidResCame(true);
                                        break;
                                    }

                                    case PID_CODE_RES_PKT: {
                                        msb_val = dis_brd_rx_buf[RES_FC_BYTE + 1] & 0xFF;
                                        msb_val <<= 8;
                                        lsb_val = (short) dis_brd_rx_buf[RES_FC_BYTE + 2] & 0xFF;
                                        pid_code = (short) (msb_val | lsb_val);
                                        setPidResCame(true);

                                        if (dis_brd_rx_buf[RES_PID_CMD_BYTE + 1] == RES_SUCCESS) {
                                            /**
                                             * ****
                                             *
                                             */
                                            if (dis_brd_rx_buf[RES_FC_BYTE + 3] == WRITE_MODE) {
                                                setLinkDisBrdResCame(true);
                                                objBrdLnkRstTst[brd_type].dtcCodes = true;
                                                break;
                                            }

                                            if (pid_code == PID_SER_NO) {
                                                msb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                msb_val <<= 8;
                                                lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3] & 0xFF;
                                                objPids[brd_type].ser_no = String.valueOf((short) msb_val | lsb_val);
                                                objReadFiles.write_pid_codes_log(brd_type + " PID SER NO : " + objPids[brd_type].ser_no);
                                            }
                                            switch (pid_code) {
                                                case PID_APP_SW_REV:
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                    objPids[brd_type].app_sw_rev = String.valueOf(lsb_val);
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID APP SW Version : " + objPids[brd_type].app_sw_rev);
                                                    break;
                                                // try {
                                                // gpsDriving objDriv = new gpsDriving();
                                                //objDriv.disbrd_pid_pkt(brd_type, pid_code);
                                                // objDriv = null;
                                                //} catch (Exception ex) {
                                                // }
                                                case PID_COMPILATION_FW_DATE_TIME:
                                                    objPids[brd_type].compilation_fw_date = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 3]
                                                            + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 4] + " " + dis_brd_rx_buf[RES_PID_CMD_BYTE + 5] + ":"
                                                            + dis_brd_rx_buf[RES_PID_CMD_BYTE + 6];
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_COMPILATION_FW_DATE_TIME : " + objPids[brd_type].compilation_fw_date);
                                                    break;
                                                case PID_FLASH_UPATE_STATUS:
                                                    objPids[brd_type].flash_update_status = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 3]
                                                            + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 4] + " " + dis_brd_rx_buf[RES_PID_CMD_BYTE + 5] + ":"
                                                            + dis_brd_rx_buf[RES_PID_CMD_BYTE + 6];
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_FLASH_UPATE_STATUS : " + objPids[brd_type].flash_update_status);
                                                    break;
                                                case PID_TEST_DATE_TIME:
                                                    objPids[brd_type].test_date_time = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 3]
                                                            + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 4] + " " + dis_brd_rx_buf[RES_PID_CMD_BYTE + 5] + ":"
                                                            + dis_brd_rx_buf[RES_PID_CMD_BYTE + 6];
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_TEST_DATE_TIME : " + objPids[brd_type].test_date_time);
                                                    break;
                                                case PID_PRODUCTION_DATE:
                                                    objPids[brd_type].prod_date = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 3]
                                                            + "-" + dis_brd_rx_buf[RES_PID_CMD_BYTE + 4];
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_PRODUCTION_DATE : " + objPids[brd_type].prod_date);
                                                    break;
                                                case PID_END_CUSTOMER:
                                                    msb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                    msb_val <<= 8;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3] & 0xFF;
                                                    objPids[brd_type].end_customer = String.valueOf((short) msb_val | lsb_val);
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_END_CUSTOMER : " + objPids[brd_type].end_customer);
                                                    break;
                                                case PID_ORDER_NO:
                                                    pid_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                    byte j = 0;
                                                    pid_res_string = "";
                                                    bytes = new byte[(int) pid_val];
                                                    for (j = 0; j < pid_val; j++) {
                                                        bytes[j] = (dis_brd_rx_buf[RES_PID_CMD_BYTE + 3 + j]);
                                                    }
                                                    pid_res_string = new String(bytes);
                                                    objPids[brd_type].order_no = pid_res_string;
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_ORDER_NO : " + objPids[brd_type].order_no);
                                                    break;
                                                case PID_VEHICLE_TYPE:
                                                    msb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                    msb_val <<= 8;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3] & 0xFF;
                                                    objPids[brd_type].vehicle_type = String.valueOf((short) msb_val | lsb_val);
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_VEHICLE_TYPE : " + objPids[brd_type].vehicle_type);
                                                    break;
                                                case PID_BUS_BUILDER_NO:
                                                    msb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2];
                                                    msb_val <<= 8;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3];
                                                    objPids[brd_type].bus_builder_no = String.valueOf((short) msb_val | lsb_val);
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_BUS_BUILDER_NO : " + objPids[brd_type].bus_builder_no);
                                                    break;
                                                case PID_LANGUAGE:
                                                    objPids[brd_type].language1 = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2];
                                                    objPids[brd_type].language2 = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3];
                                                    objPids[brd_type].language3 = dis_brd_rx_buf[RES_PID_CMD_BYTE + 4];
                                                    objPids[brd_type].language = (objPids[brd_type].language1);
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_LANGUAGE : " + objPids[brd_type].language);
                                                    break;
                                                case PID_INTERNAL_CPU_TEMP:
                                                    byte b = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2];
                                                    objPids[brd_type].internal_cpu_temp = String.valueOf(b) + "°C";
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_INTERNAL_CPU_TEMP : " + objPids[brd_type].internal_cpu_temp);
                                                    break;
                                                case PID_OPERATING_HRS:
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                    pid_val = lsb_val << 24;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3] & 0xFF;
                                                    lsb_val <<= 16;
                                                    pid_val = pid_val | lsb_val;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 4] & 0xFF;
                                                    lsb_val <<= 8;
                                                    pid_val = pid_val | lsb_val;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 5] & 0xFF;
                                                    pid_val = pid_val | lsb_val;
                                                    objPids[brd_type].operating_hours = pid_val;
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_OPERATING_HRS : " + objPids[brd_type].operating_hours);
                                                    break;
                                                case PID_NO_RESETS:
                                                    msb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 2] & 0xFF;
                                                    msb_val <<= 8;
                                                    lsb_val = dis_brd_rx_buf[RES_PID_CMD_BYTE + 3] & 0xFF;
                                                    objPids[brd_type].no_resets = (int) (msb_val | lsb_val);
                                                    objReadFiles.write_pid_codes_log(brd_type + " PID_NO_RESETS : " + objPids[brd_type].no_resets);
                                                    break;
                                                default:
                                                    break;
                                            }
                                        } else if (dis_brd_rx_buf[RES_PID_CMD_BYTE + 1] == RES_UNSUCCESS) {

                                        } else if (dis_brd_rx_buf[RES_PID_CMD_BYTE + 1] == RES_CHKSUM_FAIL) {

                                        }

                                        break;
                                    }
                                    case PID_CODEALL_RES_PKT: {
                                        setPidResCame(true);
                                        try {
                                            if (dis_brd_rx_buf[RES_FC_BYTE + 1] == RES_SUCCESS) {
                                                inc = RES_FC_BYTE + 2;

                                                //hardware revision
                                                objPids[brd_type].hw_rev_val = String.valueOf((float) (dis_brd_rx_buf[inc++] & 0xFF) / 10);

                                                //serial number
                                                msb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                msb_val <<= 8;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                objPids[brd_type].ser_no = String.valueOf((short) msb_val | lsb_val);

                                                //boot loader s/w revision
                                                objPids[brd_type].boot_loader_sw_rev = String.valueOf((float) (dis_brd_rx_buf[inc++] & 0xFF) / 10);

                                                //application sw revision 
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                objPids[brd_type].app_sw_rev = String.valueOf((float) lsb_val / 10);

                                                //Font Library   revision 
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                objPids[brd_type].font_lib_rev = String.valueOf((float) lsb_val / 10);

                                                //cpu Part No
                                                // length of cpu qual
                                                pid_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                //String cpu qual
                                                byte j = 0;
                                                pid_res_string = "";
                                                bytes = new byte[(int) pid_val];
                                                for (j = 0; j < pid_val; j++) {
                                                    bytes[j] = (dis_brd_rx_buf[inc++]);
                                                }

                                                pid_res_string = new String(bytes);
                                                objPids[brd_type].cpu_part_no = pid_res_string;

                                                //cpu qualificatiion
                                                // length of cpu qual
                                                pid_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                //String cpu qual
                                                j = 0;
                                                pid_res_string = "";
                                                bytes = new byte[(int) pid_val];
                                                for (j = 0; j < pid_val; j++) {
                                                    bytes[j] = (dis_brd_rx_buf[inc++]);
                                                }

                                                pid_res_string = new String(bytes);
                                                objPids[brd_type].cpu_qual = pid_res_string;

                                                // cpu temperature min val
                                                objPids[brd_type].min_cpu_temp = "-" + (dis_brd_rx_buf[inc++] & 0xFF);
                                                // cpu temperature max val
                                                objPids[brd_type].max_cpu_temp = String.valueOf(dis_brd_rx_buf[inc++] & 0xFF);

                                                //compilation firmware date and time
                                                objPids[brd_type].compilation_fw_date = dis_brd_rx_buf[inc++] + "-" + dis_brd_rx_buf[inc++]
                                                        + "-" + dis_brd_rx_buf[inc++] + " " + dis_brd_rx_buf[inc++] + ":"
                                                        + dis_brd_rx_buf[inc++];

                                                //flash update date and time
                                                objPids[brd_type].flash_update_status = dis_brd_rx_buf[inc++] + "-" + dis_brd_rx_buf[inc++]
                                                        + "-" + dis_brd_rx_buf[inc++] + " " + dis_brd_rx_buf[inc++] + ":"
                                                        + dis_brd_rx_buf[inc++];

                                                //test date and time
                                                objPids[brd_type].test_date_time = dis_brd_rx_buf[inc++] + "-" + dis_brd_rx_buf[inc++]
                                                        + "-" + dis_brd_rx_buf[inc++] + " " + dis_brd_rx_buf[inc++] + ":"
                                                        + dis_brd_rx_buf[inc++];

                                                //article sign number
                                                j = 0;
                                                pid_res_string = "";
                                                bytes = new byte[(int) 2];
                                                for (j = 0; j < 2; j++) {
                                                    bytes[j] = (dis_brd_rx_buf[inc++]);
                                                }

                                                pid_res_string = new String(bytes);

                                                objPids[brd_type].article_no_sign_level = pid_res_string;

                                                //production date
                                                objPids[brd_type].prod_date = dis_brd_rx_buf[inc++] + "-" + dis_brd_rx_buf[inc++]
                                                        + "-" + dis_brd_rx_buf[inc++];

                                                //end customer
                                                msb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                msb_val <<= 8;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                objPids[brd_type].end_customer = String.valueOf((short) msb_val | lsb_val);

                                                //order number
                                                //order length
                                                pid_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                //order value

                                                pid_res_string = "";

                                                bytes = new byte[(int) pid_val];
                                                for (j = 0; j < pid_val; j++) {
                                                    bytes[j] = (dis_brd_rx_buf[inc++]);
                                                }
                                                pid_res_string = new String(bytes);

                                                objPids[brd_type].order_no = pid_res_string;

                                                // bus vehicle type
                                                msb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                msb_val <<= 8;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                objPids[brd_type].vehicle_type = String.valueOf((short) msb_val | lsb_val);

                                                // bus body builder number 
                                                msb_val = dis_brd_rx_buf[inc++];
                                                msb_val <<= 8;
                                                lsb_val = dis_brd_rx_buf[inc++];
                                                objPids[brd_type].bus_builder_no = String.valueOf((short) msb_val | lsb_val);

                                                // language
                                                objPids[brd_type].language1 = dis_brd_rx_buf[inc++];
                                                objPids[brd_type].language2 = dis_brd_rx_buf[inc++];
                                                objPids[brd_type].language3 = dis_brd_rx_buf[inc++];
                                                objPids[brd_type].language = (objPids[brd_type].language1);

                                                //temperature sensor string
                                                //temperature sensor length
                                                pid_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                //temperature sensor value
                                                j = 0;

                                                bytes = new byte[(int) pid_val];
                                                for (j = 0; j < pid_val; j++) {
                                                    bytes[j] = (dis_brd_rx_buf[inc++]);
                                                }
                                                pid_res_string = new String(bytes);

                                                objPids[brd_type].board_temp_sensor = pid_res_string;

                                                //internal cpu tempearature
                                                objPids[brd_type].internal_cpu_temp = String.valueOf(dis_brd_rx_buf[inc++]) + "°C";
                                                // Minimum CPU Temperature
                                                objPids[brd_type].min_cpu_temp = "-" + String.valueOf(dis_brd_rx_buf[inc++]) + "°C";
                                                //Maximum CPU Temperature

                                                objPids[brd_type].max_cpu_temp = String.valueOf(dis_brd_rx_buf[inc++]) + "°C";
                                                // Minimum Board Temperature
                                                objPids[brd_type].min_board_temp = "-" + String.valueOf(dis_brd_rx_buf[inc++]) + "°C";
                                                //Maximum Board Temperature
                                                objPids[brd_type].max_board_temp = String.valueOf(dis_brd_rx_buf[inc++]) + "°C";
                                                //Maximum Input Voltage
                                                objPids[brd_type].max_input_volt = String.valueOf(dis_brd_rx_buf[inc++]) + "V";

                                                //Minimum Input Voltage
                                                objPids[brd_type].min_input_volt = String.valueOf(dis_brd_rx_buf[inc++]) + "V";

                                                //operating hours
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                pid_val = lsb_val << 24;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                lsb_val <<= 16;
                                                pid_val = pid_val | lsb_val;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                lsb_val <<= 8;
                                                pid_val = pid_val | lsb_val;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                pid_val = pid_val | lsb_val;
                                                objPids[brd_type].operating_hours = pid_val;

                                                // no of resets
                                                msb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                msb_val <<= 8;
                                                lsb_val = dis_brd_rx_buf[inc++] & 0xFF;
                                                objPids[brd_type].no_resets = (int) (msb_val | lsb_val);

                                                objReadFiles.write_disbrd_pid_file();
                                            }
                                        } catch (Exception ex) {

                                        }

                                        break;
                                    }
                                    case DISBRD_SET_BRD_ADDR_CONFIG_RES_PKT:
                                        objBrdLnkRstTst[brd_type].setAddressPkt = true;
                                        setLinkDisBrdResCame(true);
                                        break;

                                    case DISBRD_GET_BRD_ADDR_CONFIG_RES_PKT:

                                        setLinkDisBrdResCame(true);
                                        objBrdLnkRstTst[brd_type].getAddressPkt = true;
                                        brd_type = dis_brd_rx_buf[RES_FC_BYTE + 3];
                                        if (brd_type == clsDefines.FD_ADDR) {
                                            clsSharedVariables.setDisBrdSetAddrData("Addr: " + dis_brd_rx_buf[RES_FC_BYTE + 3] + " ,Width: "
                                                    + (dis_brd_rx_buf[RES_FC_BYTE + 4] & 0xFF) + " Front Board");
                                        } else if (brd_type == clsDefines.SD_ADDR) {
                                            clsSharedVariables.setDisBrdSetAddrData("Addr: " + dis_brd_rx_buf[RES_FC_BYTE + 3] + " ,Width: "
                                                    + (dis_brd_rx_buf[RES_FC_BYTE + 4] & 0xFF) + " Side Board");
                                        } else if (brd_type == clsDefines.RD_ADDR) {
                                            clsSharedVariables.setDisBrdSetAddrData("Addr: " + dis_brd_rx_buf[RES_FC_BYTE + 3] + " ,Width: "
                                                    + (dis_brd_rx_buf[RES_FC_BYTE + 4] & 0xFF) + " Rear Board");
                                        } else if (brd_type == clsDefines.ID_ADDR) {
                                            clsSharedVariables.setDisBrdSetAddrData("Addr: " + dis_brd_rx_buf[RES_FC_BYTE + 3] + " ,Width: "
                                                    + (dis_brd_rx_buf[RES_FC_BYTE + 4] & 0xFF) + " Internal Board");
                                        } else if (brd_type == clsDefines.SD_ART_ADDR) {
                                            clsSharedVariables.setDisBrdSetAddrData("Addr: " + dis_brd_rx_buf[RES_FC_BYTE + 3] + " ,Width: "
                                                    + (dis_brd_rx_buf[RES_FC_BYTE + 4] & 0xFF) + " Side Articulated Board");
                                        } else if (brd_type == clsDefines.ID_ART_ADDR) {
                                            clsSharedVariables.setDisBrdSetAddrData("Addr: " + dis_brd_rx_buf[RES_FC_BYTE + 3] + " ,Width: "
                                                    + (dis_brd_rx_buf[RES_FC_BYTE + 4] & 0xFF) + " Internal Articulated Board");
                                        }

                                        break;
                                }
                            }
                        }

                    }
                } catch (Exception ex) {
                } finally {
                    pkt_buf = null;
                    bytes = null;
                    pid_res_string = null;
                }
            }

        }
    }
    //method for closing the serial port.

    public static void close_displayboard_serialport() {
        if (displaybrd_serialPort != null) {
            try {
                clsReadFiles obj = new clsReadFiles();
                obj.write_displayboard_serialport_log("Displayboard Serial port closed");
                obj = null;
                displaybrd_serialPort.closePort();
                displaybrd_serialPort.removeEventListener();
                displaybrd_serialPort = null;
            } catch (SerialPortException ex) {
                //Logger.getLogger(clsDisplayBrdSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    //

    public static byte[] convertToByteArray(short value) {
        // Convert a short value to a byte array
        byte[] bytes = new byte[2]; // Create a byte array of length 2
        try {
            ByteBuffer buffer; // Declare a ByteBuffer variable

            buffer = ByteBuffer.allocate(bytes.length); // Allocate memory for the ByteBuffer
            buffer.putShort(value); // Put the short value into the ByteBuffer
            bytes = null; // Set the bytes array to null (Note: this line is unnecessary and can be removed)
            return buffer.array(); // Return the byte array representation of the short value stored in the ByteBuffer
        } catch (Exception ex) {
            // Handle any exceptions that might occur during the conversion
        }
        return bytes; // Return the byte array
    }

    public static byte[] convertToByteArrayInt(int value) {

        byte[] bytes = new byte[4];
        ByteBuffer buffer;

        buffer = ByteBuffer.allocate(bytes.length);
        buffer.putInt(value);
        bytes = null;
        return buffer.array();

    }

    public void pid_codes_display_board(byte index, short pkt_code_type) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;
        int buf_inc = 0;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4;

        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            if (getFrontEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            if (getSideEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            if (getRearEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            if (getIntEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            if (getArtSideEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            if (getArtIntEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        } else {
            pid_buf = null;
            return;
        }
        pid_buf[buf_inc++] = (byte) PID_CODE_PKT;
        buf_inc = clsDefines.DATA_BYTE;

        buf = convertToByteArray(pkt_code_type); //PID_SER_NO);

        pid_buf[buf_inc++] = buf[0];
        pid_buf[buf_inc++] = buf[1];
        pid_buf[buf_inc++] = PID_READ;

        pkt_length += (short) (buf_inc);

        buf = convertToByteArray((short) (pkt_length));

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB)
        //  pkt_buf[LEN_LSB)

        pkt_length = (short) (pkt_length + 2);

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        buf = new byte[buf_inc + 2];

        for (i = 0; i < buf_inc + 2; i++) {
            buf[i] = pid_buf[i];
        }
        objDisBrdPid.addData(buf);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
        }
        pid_buf = null;
        buf = null;
    }

    public void dtc_codes_display_board(byte index, short pkt_type, int pkt_code_type) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;

        int buf_inc = 0;

        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4;

        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            if (getFrontEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            if (getSideEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            if (getRearEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            if (getIntEnable() == false) {
                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            if (getArticulatedBus() == true && getArtSideEnable()) {

                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {

            if (getArticulatedBus() == true && getArtIntEnable()) {

                pid_buf = null;
                return;
            }
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        } else {
            pid_buf = null;
            return;
        }

        pid_buf[buf_inc++] = (byte) pkt_type;
        buf_inc = clsDefines.DATA_BYTE;

        buf = convertToByteArray((short) pkt_code_type);

        pid_buf[buf_inc++] = buf[0];
        pid_buf[buf_inc++] = buf[1];

        if (pkt_type == DTC_COUNT_CODE_PKT) {
            pid_buf[buf_inc++] = (byte) 2; //read type
        }

        pkt_length += (short) (buf_inc);
        buf = convertToByteArray((short) (pkt_length));

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB)
        //  pkt_buf[LEN_LSB)

        pkt_length = (short) (pkt_length + 2);

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        buf = new byte[buf_inc + 2];
        // StringBuilder sb = new StringBuilder();
        for (i = 0; i < buf_inc + 2; i++) {
            buf[i] = pid_buf[i];
        }
        objDisBrdPid.addData(buf);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            //Logger.getLogger(clsDisplayBrdSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        }
        pid_buf = null;
        buf = null;
    }

    public void link_display_board(byte index, short pkt_type) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;

        int buf_inc = 0; // Initialize buffer increment variable

        // Add header information to the packet buffer
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4; // Set buffer increment to 4

        // Add address information based on the index value
        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        } else {
            pid_buf = null; // Set pid_buf to null if index is invalid
            return; // Exit the method
        }
        pid_buf[buf_inc++] = (byte) pkt_type; // Add packet type to the buffer
        buf_inc = clsDefines.DATA_BYTE; // Set buffer increment to DATA_BYTE value

        pkt_length += (short) (buf_inc); // Update packet length
        buf = convertToByteArray((short) (pkt_length)); // Convert packet length to byte array

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB)
        //  pkt_buf[LEN_LSB)

        pkt_length = (short) (pkt_length + 2);

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        buf = new byte[buf_inc + 2];
        // StringBuilder sb = new StringBuilder();

        disBrdLinkChkBuf = new byte[buf_inc + 2];
        // StringBuilder sb = new StringBuilder();
        for (i = 0; i < buf_inc + 2; i++) {
            disBrdLinkChkBuf[i] = pid_buf[i];
        }
        setDisBrdLnkChkCame(true);

        pid_buf = null;
        buf = null;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            //Logger.getLogger(clsDisplayBrdSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void link_display_board_queue(byte index, short pkt_type) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;

        int buf_inc = 0;

        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4;

        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        } else {
            pid_buf = null;
            return;
        }
        pid_buf[buf_inc++] = (byte) pkt_type;
        buf_inc = clsDefines.DATA_BYTE;

        pkt_length += (short) (buf_inc);
        buf = convertToByteArray((short) (pkt_length));

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];

        pkt_length = (short) (pkt_length + 2); // Update packet length

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        buf = new byte[buf_inc + 2];

        for (i = 0; i < buf_inc + 2; i++) {
            buf[i] = pid_buf[i]; // Copy data from pid_buf to buf
        }
        objDisBrdPid.addData(buf); // Add data to display board
        pid_buf = null; // Set pid_buf to null
        buf = null; // Set buf to null
        try {
            TimeUnit.SECONDS.sleep(5); // Sleep for 5 seconds
        } catch (InterruptedException ex) {
            // Handle InterruptedException
        }
    }

    public byte[] delete_cont_pkt(byte addr) {
        // Method to delete a continuous packet
        byte[] pid_buf = new byte[200]; // Initialize a byte array for the packet buffer
        byte[] buf; // Initialize a byte array for temporary storage
        short pkt_length = 0; // Initialize packet length variable
        byte i = 0; // Initialize loop variable

        int buf_inc = 0; // Initialize buffer increment variable

        // Add header information to the packet buffer
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4; // Set buffer increment to 4

        // Add address information and packet type to the buffer
        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        pid_buf[buf_inc++] = addr;
        pid_buf[buf_inc++] = (byte) TESTC_PKT;
        pid_buf[buf_inc++] = (byte) 0;

        pkt_length += (short) (buf_inc); // Update packet length
        buf = convertToByteArray((short) (pkt_length)); // Convert packet length to byte array

        // Add packet length to the buffer
        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];

        pkt_length = (short) (pkt_length + 2); // Update packet length

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        buf = new byte[buf_inc + 2];

        for (i = 0; i < buf_inc + 2; i++) {
            buf[i] = pid_buf[i]; // Copy data from pid_buf to buf
        }

        pid_buf = null; // Set pid_buf to null
        return buf; // Return the buffer
    }

    public byte[] delete_pkt_display_board(byte index, byte no) {
        // Method to delete a packet from the display board
        byte[] pid_buf = new byte[200]; // Initialize a byte array for the packet buffer
        byte[] buf; // Initialize a byte array for temporary storage
        short pkt_length = 0; // Initialize packet length variable
        byte i = 0; // Initialize loop variable

        int buf_inc = 0; // Initialize buffer increment variable

        // Add header information to the packet buffer
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4; // Set buffer increment to 4

        // Add address information based on the index value
        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        } else {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        }
        pid_buf[buf_inc++] = (byte) DELETE_PKT; // Add packet type to the buffer
        buf_inc = clsDefines.DATA_BYTE; // Set buffer increment to DATA_BYTE value
        pid_buf[buf_inc++] = no; // Add packet number to the buffer

        pkt_length += (short) (buf_inc); // Update packet length
        buf = convertToByteArray((short) (pkt_length)); // Convert packet length to byte array

        // Add packet length to the buffer
        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];

        pkt_length = (short) (pkt_length + 2); // Update packet length

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length); // Calculate checksum

        buf = new byte[buf_inc + 2]; // Create a new byte array for temporary storage
        for (i = 0; i < buf_inc + 2; i++) {
            buf[i] = pid_buf[i]; // Copy data from pid_buf to buf
        }

        return buf; // Return the buffer
    }

    public void time_pkt_internal_display_board() {
        // Initialize variables
        byte[] time_buf = new byte[200]; // Buffer for the time packet
        byte[] buf; // Buffer for packet length conversion
        short pkt_length = 0; // Length of the packet
        byte i = 0; // Loop variable

        int buf_inc = 0; // Buffer increment variable
        Calendar cal = Calendar.getInstance(); // Calendar object to get current time

        // Add packet headers and addresses
        time_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        time_buf[buf_inc++] = (byte) clsDefines.HEADER2;
        buf_inc = 4; // Skip to the data section

        time_buf[buf_inc++] = clsDefines.VMU_ADDR; // VMU address
        time_buf[buf_inc++] = clsDefines.ID_ADDR; // ID address

        time_buf[buf_inc++] = TIME_PKT; // Packet type
        buf_inc = clsDefines.DATA_BYTE; // Move to the data section

        // Add hour and minute to the packet
        time_buf[buf_inc++] = (byte) cal.get(Calendar.HOUR_OF_DAY); // Hour
        time_buf[buf_inc++] = (byte) cal.get(Calendar.MINUTE); // Minute

        pkt_length += (short) (buf_inc); // Update packet length
        buf = convertToByteArray((short) (pkt_length)); // Convert packet length to byte array

        // Add packet length to the buffer
        time_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        time_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];
        pkt_length = (short) (pkt_length + 2); // Update packet length with additional bytes

        // Prepare checksum
        time_buf = prepare_checksum(time_buf, (short) pkt_length);

        // Copy a part of the buffer for link check
        disBrdLinkChkBuf = new byte[buf_inc + 2];
        for (i = 0; i < buf_inc + 2; i++) {
            disBrdLinkChkBuf[i] = time_buf[i];
        }
        setDisBrdLnkChkCame(true); // Set link check flag

        // Cleanup
        time_buf = null; // Release memory
        buf = null; // Release memory

        try {
            TimeUnit.SECONDS.sleep(5); // Wait for 5 seconds
        } catch (InterruptedException ex) {
            // Exception handling for interruption
            // Logger.getLogger(clsDisplayBrdSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to calculate checksum for the packet
    private static byte[] prepare_checksum(byte[] str, short length) {
        int index = 2;
        int CRCITTSum = 0xffff;
        int ByteValue = 0;
        byte bitindex = 0;
        byte temp = 0;
        try {
            while (index < (length - 2)) {
                temp = str[index];
                ByteValue = temp;
                ByteValue <<= 8;
                bitindex = 0;

                // Calculate checksum
                while (bitindex < 8) {
                    if (((((CRCITTSum ^ ByteValue) & 0x8000)) != 0)) {
                        CRCITTSum = (int) (CRCITTSum << 1);
                        CRCITTSum = (int) (CRCITTSum ^ 0x1021);
                    } else {
                        CRCITTSum <<= 1;
                    }
                    ByteValue <<= 1;
                    bitindex += (byte) (1);
                }
                index += (byte) (1);
            }

            // Add checksum to the packet
            str[length - 1] = (byte) (CRCITTSum & 0xff);
            str[length - 2] = (byte) ((CRCITTSum & 0xff00) >> 8);
        } catch (Exception ex) {
            // Exception handling
        }
        return str;
    }
    //This thread is used for checking the status f display boards 
    private class clsPidDisBrdLatestThread extends Thread {

        @Override
        public void run() {
            int index = 0;
            int time_inc = 0;
            boolean front_pid_chk_completed = false;
            boolean side_pid_chk_completed = false;
            boolean rear_pid_chk_completed = false;
            boolean int_pid_chk_completed = false;
            boolean side_art_pid_chk_completed = false;
            boolean int_art_pid_chk_completed = false;

            final byte START_FD_INDEX = 1;
            final byte START_SD_INDEX = 19;
            final byte START_RD_INDEX = 38;
            final byte START_ID_INDEX = 57;
            final byte START_SD_ART_INDEX = 77;
            final byte START_ID_ART_INDEX = 96;
            final byte START_WRITE_SENDDATA_INDEX = 116;
            final byte START_LINKCHK_INDEX = 117;

            final byte START_LINKCHK_SD_INDEX = 118;
            final byte START_LINKCHK_RD_INDEX = 119;
            final byte START_LINKCHK_ID_INDEX = 120;
            final byte START_LINKCHK_SDART_INDEX = 121;
            final byte START_LINKCHK_IDART_INDEX = 122;

            final byte START_FAIL_LINKCHK_INDEX = 123;

            final byte MAX_WAIT_FIND_PIDS = 5; // 50; //100
            boolean link_all_brds = false;

            int fail_link_inc = 0;
            byte retry_fail_inc = 0;

            byte retry_link_inc = 0;
            int k = 0;

            boolean updated_db_send_server = false;

            //  byte[] buf;
            // gpsDriving objDriving = new gpsDriving();
            while (true) {

                objwatDog.setDisbrd_watchdog_val((byte) 2);
                if (!clsSharedVariables.getDiagDispForm()) {

                    try {
                        switch (index) {
                            case 0:

                                if (getFrontEnable()) {
                                    link_display_board_queue(FRONT_DB, LINK_CHECK_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                }

                                if (getSideEnable()) {
                                    link_display_board_queue(SIDE_DB, LINK_CHECK_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                }

                                if (getRearEnable()) {
                                    link_display_board_queue(REAR_DB, LINK_CHECK_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                }

                                if (getIntEnable()) {

                                    if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                        link_display_board_queue(INT_DB, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                        time_pkt_internal_display_board();
                                        TimeUnit.SECONDS.sleep(2);
                                    } else {
                                        link_display_board_queue(INT_DB, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                    }
                                }

                                if (getArticulatedBus() == true && getArtSideEnable()) {
                                    link_display_board_queue(SIDE_DB_ART, LINK_CHECK_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                }

                                if (getArticulatedBus() == true && getArtIntEnable()) {
                                    link_display_board_queue(INT_DB_ART, LINK_CHECK_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                }

                                if (retry_link_inc++ > 5) {
                                    retry_link_inc = 0;
                                    if (clsSharedVariables.getPidsDbEnable() == false) {
                                        index = START_FAIL_LINKCHK_INDEX;
                                        front_pid_chk_completed = true;
                                        side_pid_chk_completed = true;
                                        rear_pid_chk_completed = true;
                                        int_pid_chk_completed = true;
                                        side_art_pid_chk_completed = true;
                                        int_art_pid_chk_completed = true;
                                    } else {
                                        index = START_FAIL_LINKCHK_INDEX;
                                    }
                                }

                                break;
                            case START_FD_INDEX:
                                if (!getFrontEnable() && getFrontLinkDisBrdResCame() == false && front_pid_chk_completed == true) {
                                    if (getSideLinkDisBrdResCame() == false && getSideEnable() && side_pid_chk_completed == false) {
                                        index = START_SD_INDEX;
                                    } else if (getRearLinkDisBrdResCame() == false && getRearEnable() && rear_pid_chk_completed == false) {
                                        index = START_RD_INDEX;
                                    } else if (getIntLinkDisBrdResCame() == false && getIntEnable() && int_pid_chk_completed == false) {
                                        index = START_ID_INDEX;
                                    } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                        index = START_SD_ART_INDEX;
                                    } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                        index = START_ID_ART_INDEX;
                                    } else {
                                        index = START_LINKCHK_INDEX;
                                    }
                                } else if (front_pid_chk_completed == false && (objHealth.get_fd_status() == BOARD_STATUS_SUCCESS)) {
                                    link_display_board_queue(FRONT_DB, PID_CODEALL_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                    link_display_board_queue(FRONT_DB, DTC_CODEALL_PKT);
                                    front_pid_chk_completed = true;
                                    index = START_SD_INDEX;
                                } else if (getSideLinkDisBrdResCame() == false && getSideEnable() && side_pid_chk_completed == false) {
                                    index = START_SD_INDEX;
                                } else if (getRearLinkDisBrdResCame() == false && getRearEnable() && rear_pid_chk_completed == false) {
                                    index = START_RD_INDEX;
                                } else if (getIntLinkDisBrdResCame() == false && getIntEnable() && int_pid_chk_completed == false) {
                                    index = START_ID_INDEX;
                                } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                    index = START_SD_ART_INDEX;
                                } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                    index = START_ID_ART_INDEX;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                }
                                break;

                            case START_SD_INDEX:
                                if (!getSideEnable() && getSideLinkDisBrdResCame() == false && side_pid_chk_completed == true) {
                                    if (getRearLinkDisBrdResCame() == false && getRearEnable() && rear_pid_chk_completed == false) {
                                        index = START_RD_INDEX;
                                    } else if (getIntLinkDisBrdResCame() == false && getIntEnable() && int_pid_chk_completed == false) {
                                        index = START_ID_INDEX;
                                    } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                        index = START_SD_ART_INDEX;
                                    } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                        index = START_ID_ART_INDEX;
                                    } else {
                                        index = START_LINKCHK_INDEX;
                                    }
                                } else if (side_pid_chk_completed == false && (objHealth.get_sd_status() == BOARD_STATUS_SUCCESS)) {
                                    link_display_board_queue(SIDE_DB, PID_CODEALL_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                    link_display_board_queue(SIDE_DB, DTC_CODEALL_PKT);
                                    side_pid_chk_completed = true;
                                    index = START_RD_INDEX;

                                } else if (getRearLinkDisBrdResCame() == false && getRearEnable() && rear_pid_chk_completed == false) {
                                    index = START_RD_INDEX;
                                } else if (getIntLinkDisBrdResCame() == false && getIntEnable() && int_pid_chk_completed == false) {
                                    index = START_ID_INDEX;
                                } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                    index = START_SD_ART_INDEX;
                                } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                    index = START_ID_ART_INDEX;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                }

                                if (getIntEnable() && getIntLinkDisBrdResCame() == true) {
                                    TimeUnit.SECONDS.sleep(1);
                                    if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                        time_pkt_internal_display_board();
                                    }
                                }

                                break;

                            case START_RD_INDEX:
                                if (!getRearEnable() && getRearLinkDisBrdResCame() == false && rear_pid_chk_completed == true) {
                                    if (getIntLinkDisBrdResCame() == false && getIntEnable() && int_pid_chk_completed == false) {
                                        index = START_ID_INDEX;
                                    } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                        index = START_SD_ART_INDEX;
                                    } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                        index = START_ID_ART_INDEX;
                                    } else {
                                        index = START_LINKCHK_INDEX;
                                    }
                                } else if (rear_pid_chk_completed == false && (objHealth.get_rd_status() == BOARD_STATUS_SUCCESS)) {
                                    link_display_board_queue(REAR_DB, PID_CODEALL_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                    link_display_board_queue(REAR_DB, DTC_CODEALL_PKT);

                                    rear_pid_chk_completed = true;

                                    index = START_ID_INDEX;

                                } else if (getIntLinkDisBrdResCame() == false && getIntEnable() && int_pid_chk_completed == false) {
                                    index = START_ID_INDEX;
                                } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                    index = START_SD_ART_INDEX;
                                } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                    index = START_ID_ART_INDEX;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                }

                                if (getIntEnable() && getIntLinkDisBrdResCame() == true) {
                                    if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                        TimeUnit.SECONDS.sleep(1);
                                        time_pkt_internal_display_board();
                                    }
                                }

                                break;

                            case START_ID_INDEX:
                                if (!getIntEnable() && getIntLinkDisBrdResCame() == false && int_pid_chk_completed == true) {
                                    if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                        index = START_SD_ART_INDEX;
                                    } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                        index = START_ID_ART_INDEX;
                                    } else {
                                        index = START_LINKCHK_INDEX;
                                    }
                                } else if (int_pid_chk_completed == false && (objHealth.get_id_status() == BOARD_STATUS_SUCCESS)) {
                                    link_display_board_queue(INT_DB, PID_CODEALL_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                    link_display_board_queue(INT_DB, DTC_CODEALL_PKT);

                                    int_pid_chk_completed = true;
                                    index = START_SD_ART_INDEX;
                                } else if (getSideArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtSideEnable() && side_art_pid_chk_completed == false) {
                                    index = START_SD_ART_INDEX;
                                } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                    index = START_ID_ART_INDEX;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                }

                                if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                    TimeUnit.SECONDS.sleep(1);
                                    time_pkt_internal_display_board();
                                }

                                break;

                            case START_SD_ART_INDEX:
                                if (!getArticulatedBus() && !getArtSideEnable() && getSideArtLinkDisBrdResCame() == false && side_art_pid_chk_completed == true) {
                                    if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                        index = START_ID_ART_INDEX;
                                    } else {
                                        index = START_LINKCHK_INDEX;
                                    }
                                } else if (side_art_pid_chk_completed == false && getArtSideEnable() == true && getArticulatedBus() == true && (objHealth.get_sd_art_status() == BOARD_STATUS_SUCCESS)) {
                                    link_display_board_queue(SIDE_DB_ART, PID_CODEALL_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                    link_display_board_queue(SIDE_DB_ART, DTC_CODEALL_PKT);

                                    side_art_pid_chk_completed = true;
                                    index = START_ID_ART_INDEX;

                                } else if (getIntArtLinkDisBrdResCame() == false && getArticulatedBus() == true && getArtIntEnable() && int_art_pid_chk_completed == false) {
                                    index = START_ID_ART_INDEX;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                }

                                if (getIntEnable() && getIntLinkDisBrdResCame() == true) {
                                    TimeUnit.SECONDS.sleep(1);
                                    if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                        time_pkt_internal_display_board();
                                    }
                                }
                                break;

                            case START_ID_ART_INDEX:
                                if (!getArticulatedBus() && !getArtIntEnable() && getIntArtLinkDisBrdResCame() == false && int_art_pid_chk_completed == true) {
                                    index = START_LINKCHK_INDEX;
                                } else if (int_art_pid_chk_completed == false && getArticulatedBus() && getArtIntEnable() && (objHealth.get_id_art_status() == BOARD_STATUS_SUCCESS)) {
                                    link_display_board_queue(INT_DB_ART, PID_CODEALL_PKT);
                                    TimeUnit.SECONDS.sleep(2);
                                    link_display_board_queue(INT_DB_ART, DTC_CODEALL_PKT);
                                    int_art_pid_chk_completed = true;
                                    TimeUnit.SECONDS.sleep(5);
                                    index = START_WRITE_SENDDATA_INDEX;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                }

                                if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                    TimeUnit.SECONDS.sleep(1);
                                    time_pkt_internal_display_board();
                                }
                                index = START_WRITE_SENDDATA_INDEX;
                                break;

                            case START_WRITE_SENDDATA_INDEX:
                                // if (objDisBrdPid.disBrdQueueCnt() <= 0) {
                                if (updated_db_send_server == false) {
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_disbrd_pid_file();
                                    objReadFiles.write_disbrd_dtc_file();

                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                        obj16833Pkts.disbrd_pids();
                                        obj16833Pkts.disbrd_dtc_pkt();
                                        obj16833Pkts = null;
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {

                                        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                                                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                                                || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {
                                            if (clsSharedVariables.reset_type == NORMAL_RESET && clsSharedVariables.getPidsDbEnable() == false) {
                                                gpsDriving objDriving = new gpsDriving();
                                                objDriving.disbrd_pids();
                                                objDriving.disbrd_dtc_pkt();
                                                objDriving = null;
                                            }
                                        }
                                    }

                                    updated_db_send_server = true;
                                    objReadFiles = null;
                                }
                                index = START_LINKCHK_INDEX;

                                break;

                            case START_LINKCHK_INDEX:
                                if (getFrontEnable()) {
                                    link_display_board_queue(FRONT_DB, LINK_CHECK_PKT);
                                }
                                index = START_LINKCHK_SD_INDEX;
                                break;
                            case START_LINKCHK_SD_INDEX:
                                if (getSideEnable()) {
                                    link_display_board_queue(SIDE_DB, LINK_CHECK_PKT);
                                }
                                index = START_LINKCHK_RD_INDEX;
                                break;
                            case START_LINKCHK_RD_INDEX:
                                if (getRearEnable()) {
                                    link_display_board_queue(REAR_DB, LINK_CHECK_PKT);
                                }
                                index = START_LINKCHK_ID_INDEX;
                                break;
                            case START_LINKCHK_ID_INDEX:
                                if (getIntEnable()) {
                                    if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                        time_pkt_internal_display_board();
                                    } else {
                                        link_display_board_queue(INT_DB, LINK_CHECK_PKT);
                                    }
                                }
                                index = START_LINKCHK_SDART_INDEX;
                                break;
                            case START_LINKCHK_SDART_INDEX:
                                if (getArticulatedBus() && getArtSideEnable()) {
                                    link_display_board_queue(SIDE_DB_ART, LINK_CHECK_PKT);
                                }

                                index = START_LINKCHK_IDART_INDEX;
                                break;
                            case START_LINKCHK_IDART_INDEX:
                                if (getArticulatedBus() && getArtIntEnable()) {
                                    link_display_board_queue(INT_DB_ART, LINK_CHECK_PKT);
                                }
                                index = START_FAIL_LINKCHK_INDEX;
                                break;
                            case START_FAIL_LINKCHK_INDEX:
                                fail_link_inc = 0;

                                if (getFrontEnable()) {
                                    if (objHealth.get_fd_status() == BOARD_STATUS_FAILURE) {
                                        link_display_board_queue(FRONT_DB, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                        fail_link_inc++;
                                    } else if (link_all_brds == true) {
                                        link_display_board_queue(FRONT_DB, LINK_CHECK_PKT);
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);
                                        TimeUnit.SECONDS.sleep(2);
                                    }
                                }

                                if (getSideEnable()) {
                                    if (objHealth.get_sd_status() == BOARD_STATUS_FAILURE) {
                                        link_display_board_queue(SIDE_DB, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                        fail_link_inc++;
                                    } else if (link_all_brds == true) {
                                        link_display_board_queue(SIDE_DB, LINK_CHECK_PKT);
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);
                                        TimeUnit.SECONDS.sleep(2);
                                    }
                                }

                                if (getRearEnable()) {
                                    if (objHealth.get_rd_status() == BOARD_STATUS_FAILURE) {
                                        link_display_board_queue(REAR_DB, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                        fail_link_inc++;
                                    } else if (link_all_brds == true) {
                                        link_display_board_queue(REAR_DB, LINK_CHECK_PKT);
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);
                                        TimeUnit.SECONDS.sleep(2);

                                    }
                                }

                                if (getIntEnable()) {
                                    if (objHealth.get_id_status() == BOARD_STATUS_FAILURE) {
                                        link_display_board_queue(INT_DB, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                        fail_link_inc++;
                                    } else if (link_all_brds == true) {
                                        link_display_board_queue(INT_DB, LINK_CHECK_PKT);
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);
                                        TimeUnit.SECONDS.sleep(2);
                                    }
                                }
                                if (getArticulatedBus() && getArtSideEnable()) {
                                    if (objHealth.get_sd_art_status() == BOARD_STATUS_FAILURE) {
                                        link_display_board_queue(SIDE_DB_ART, LINK_CHECK_PKT);
                                        TimeUnit.SECONDS.sleep(2);
                                        fail_link_inc++;
                                    } else if (link_all_brds == true) {
                                        link_display_board_queue(SIDE_DB_ART, LINK_CHECK_PKT);
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);
                                        TimeUnit.SECONDS.sleep(2);
                                    }
                                }

                                if (getArticulatedBus() && getArtIntEnable()) {
                                    if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED && link_all_brds == true) {
                                        time_pkt_internal_display_board();

                                    } else if (objHealth.get_id_art_status() == BOARD_STATUS_FAILURE) {
                                        link_display_board_queue(INT_DB_ART, LINK_CHECK_PKT);

                                        fail_link_inc++;
                                    } else if (link_all_brds == true) {
                                        link_display_board_queue(INT_DB_ART, LINK_CHECK_PKT);
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);

                                    }
                                }

                                if (time_inc > MAX_WAIT_FIND_PIDS * 3) {
                                    time_inc = 0;
                                }
                                time_inc++;

                                if (!getFrontEnable()) {
                                    front_pid_chk_completed = true;
                                }
                                if (!getSideEnable()) {
                                    side_pid_chk_completed = true;
                                }
                                if (!getRearEnable()) {
                                    rear_pid_chk_completed = true;
                                }
                                if (!getIntEnable()) {
                                    int_pid_chk_completed = true;
                                }
                                if (!getArtSideEnable()) {
                                    side_art_pid_chk_completed = true;
                                }
                                if (!getArtIntEnable()) {
                                    int_art_pid_chk_completed = true;
                                }
                                if (front_pid_chk_completed == false && getFrontEnable() && time_inc > MAX_WAIT_FIND_PIDS) {
                                    time_inc = 0;
                                    index = START_FD_INDEX;
                                    break;
                                } else if (side_pid_chk_completed == false && getSideEnable() && time_inc > MAX_WAIT_FIND_PIDS) {
                                    time_inc = 0;
                                    index = START_SD_INDEX;
                                    break;
                                } else if (rear_pid_chk_completed == false && getRearEnable() && time_inc > MAX_WAIT_FIND_PIDS) {
                                    time_inc = 0;
                                    index = START_RD_INDEX;
                                } else if (int_pid_chk_completed == false && getIntEnable() && time_inc > MAX_WAIT_FIND_PIDS) {
                                    time_inc = 0;
                                    index = START_ID_INDEX;
                                    break;
                                } else if (side_art_pid_chk_completed == false && getArticulatedBus() && getArtSideEnable() && time_inc > MAX_WAIT_FIND_PIDS) {
                                    time_inc = 0;
                                    index = START_SD_ART_INDEX;
                                    break;
                                } else if (int_art_pid_chk_completed == false && getArticulatedBus() && getArtIntEnable() && time_inc > MAX_WAIT_FIND_PIDS) {
                                    time_inc = 0;
                                    index = START_ID_ART_INDEX;
                                    break;
                                } else {
                                    index = START_LINKCHK_INDEX;
                                    if (time_inc == 0) {
                                        if (updated_db_send_server == false) {
                                            if (front_pid_chk_completed && side_pid_chk_completed && rear_pid_chk_completed
                                                    && int_pid_chk_completed && side_art_pid_chk_completed && int_art_pid_chk_completed) {
                                                index = START_WRITE_SENDDATA_INDEX;
                                            }
                                        }
                                    }
                                }
                                if (fail_link_inc == 0) {
                                    for (k = 0; k < 60; k++) {
                                        if ((getFrontEnable() && objHealth.get_fd_status() == BOARD_STATUS_FAILURE)
                                                || (getSideEnable() && objHealth.get_sd_status() == BOARD_STATUS_FAILURE)
                                                || (getRearEnable() && objHealth.get_rd_status() == BOARD_STATUS_FAILURE)
                                                || (getIntEnable() && objHealth.get_id_status() == BOARD_STATUS_FAILURE)
                                                || (getArtSideEnable() && objHealth.get_sd_art_status() == BOARD_STATUS_FAILURE)
                                                || (getArtIntEnable() && objHealth.get_id_art_status() == BOARD_STATUS_FAILURE)) {
                                            fail_link_inc++;
                                            link_all_brds = false;
                                            break;
                                        }
                                        if (k % 10 == 0) {
                                            if (clsSharedVariables.int_disbrd_time_enabled == TIME_ENABLED) {
                                                time_pkt_internal_display_board();
                                                TimeUnit.SECONDS.sleep(2);
                                            }
                                        }
                                        objwatDog.setDisbrd_watchdog_val((byte) 2);
                                        TimeUnit.SECONDS.sleep(5);
                                    }

                                    if (fail_link_inc > 0) {
                                        if (k > 55) {
                                            link_all_brds = true;
                                        }

                                    } else {
                                        link_all_brds = true;
                                    }
                                    index = START_LINKCHK_INDEX;
                                } else {
                                    link_all_brds = false;
                                    if (retry_fail_inc++ > 6) {
                                        retry_fail_inc = 0;
                                        link_all_brds = true;
                                        TimeUnit.SECONDS.sleep(10);// fail_link_inc * 2 * 1000); 
                                    } else {
                                        TimeUnit.SECONDS.sleep(10);//fail_link_inc * 2 * 1000);
                                        break;
                                    }
                                }
                                break;
                        }

                        objwatDog.setDisbrd_watchdog_val((byte) 2);

                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException ex) {
                    } catch (Exception ex) {
                    }
                } else {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(clsDisplayBrdSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
