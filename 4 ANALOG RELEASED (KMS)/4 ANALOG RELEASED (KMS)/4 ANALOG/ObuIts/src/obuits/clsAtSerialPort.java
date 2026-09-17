package obuits;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static obuits.clsDefines.AT_BAUDRATE;
import static obuits.clsDefines.CALL_BUSY;
import static obuits.clsDefines.CALL_DIALTONE;
import static obuits.clsDefines.CALL_NOCARRIER;
import static obuits.clsDefines.CALL_OUT_DIAL;
import static obuits.clsDefines.CALL_WAIT;
import static obuits.clsDefines.NO_CALL;
import static obuits.clsSharedVariables.set_phonecall_type;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import static obuits.MainFrmIts.audio_speaker_off;
import static obuits.MainFrmIts.digOutAudioVoiceCallPin;
import static obuits.MainFrmIts.reboot_system;
import static obuits.MainFrmIts.restart_module_ports_changed;
import static obuits.MainFrmIts.write_gpio_pin_state;
import static obuits.clsDefines.CALL_CONNECT;
import static obuits.clsDefines.CALL_IN_CONNECT;
import static obuits.clsDefines.DEFAULT_SEC_2018;
import static obuits.clsDefines.DEF_HARSH_ACC_SPEED_1;
import static obuits.clsDefines.DEF_HARSH_BRK_SPEED_1;
import static obuits.clsDefines.DEF_OVER_SPEED_LIMIT;
import static obuits.clsDefines.GPS_FIXED;
import static obuits.clsDefines.GSM_CLK_FIXED;
import static obuits.clsDefines.GSM_OFF;
import static obuits.clsDefines.OUT_CALL;
import static obuits.clsDefines.SIM_AIRTEL;
import static obuits.clsDefines.SIM_IDEA;
import static obuits.clsDefines.SIM_NOT_CHECKED;
import static obuits.clsDefines.Strings.AUDIO_VOICECALL_COMM_ONOFF;
import static obuits.clsDefines.Strings.VOICE_CALL_OFF_STATE;
import static obuits.clsDefines.Strings.VOICE_CALL_ON_STATE;
import static obuits.clsDefines.obu_images_filepath;
import static obuits.clsSharedVariables.EMERGENCY_MESSAGE_TYPE;
import static obuits.clsSharedVariables.getChkNetBalDelimitter;
import static obuits.clsSharedVariables.getGpsState;
import static obuits.clsSharedVariables.getGsmDiagEnabled;
import static obuits.clsSharedVariables.getSimReady;
import static obuits.clsSharedVariables.getSimType;
import static obuits.clsSharedVariables.get_at_came;
import static obuits.clsSharedVariables.get_phonecall_type;
import static obuits.clsSharedVariables.get_ring_tone_volume;
import static obuits.clsSharedVariables.get_voice_call_volume;
import static obuits.clsSharedVariables.setAtPktStatus;
import static obuits.clsSharedVariables.setCurSec;
import static obuits.clsSharedVariables.setGsmClkState;
import static obuits.clsSharedVariables.setGsmModuleOn;
import static obuits.clsSharedVariables.setGsmSignalStrength;
import static obuits.clsSharedVariables.setSimReady;
import static obuits.clsSharedVariables.setSimRegistered;
import static obuits.clsSharedVariables.setSimType;
import static obuits.clsSharedVariables.set_QCCID;
import static obuits.clsSharedVariables.set_at_came;
import static obuits.clsSharedVariables.set_at_comport_connected;
import static obuits.clsSharedVariables.setImeiNo;
import static obuits.clsSharedVariables.setSendSmsGreaterSymState;
import static obuits.clsSharedVariables.set_own_mobileno;
import static obuits.clsSharedVariables.set_voice_call_enabled_status;
import static obuits.clsSharedVariables.set_voice_call_start_status;
import static obuits.clsSharedVariables.set_voice_call_volume;

public class clsAtSerialPort {

    static SerialPort at_serialPort;
    static ReadAtPortDataFromSerialPort objAtPort;
    static JLabel imgSigStr;
    static JLabel imgNetwork;
    static JLabel imgSimDetect;
    JFrame frameDialogBox;
    clsReadFiles objReadFiles = new clsReadFiles();
    static byte clip_came = 0;
    static byte pre_sig_str = 0;
    String at_serial_port_name = "/dev/ttyUSB2";
    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();

    private int embsimstkurc = 0;
    private int embsimstkgi = 0;
    private boolean time_came = false;
    private boolean cmeerror_came = false;
    private boolean cpinready = false;
    // ImageIcon img2G = (new javax.swing.ImageIcon(getClass().getResource("/Images/2g.png")));
    // ImageIcon img3G = (new javax.swing.ImageIcon(getClass().getResource("/Images/3g.png")));
    //  ImageIcon img4G = (new javax.swing.ImageIcon(getClass().getResource("/Images/lte.png")));

    // Method to initialize the Serial Port and related components
// Parameters:
//    - imgStr: JLabel for signal strength icon
//    - frame: JFrame for dialog box
//    - imgNet: JLabel for network detection icon
//    - imgSimDet: JLabel for SIM card detection icon
    clsAtSerialPort(JLabel imgStr, JFrame frame, JLabel imgNet, JLabel imgSimDet) {
        // Assigning references to the respective components
        imgSigStr = imgStr;
        imgNetwork = imgNet;
        imgSimDetect = imgSimDet;
        frameDialogBox = frame;

        // Resetting watchdog value for AT Serial Port
        objwatDog.setAtSerial_watchdog_val((byte) 0);

        try {
            // Updating UI components in Swing thread
            SwingUtilities.invokeLater(() -> {
                // Updating SIM card detection icon
                updateSimDetectIcon(false);
                // Updating network detection icon and setting initial network state
                updateNetworkDetectIcon(false, (byte) 0);
                // Updating signal strength icon and setting initial strength value
                updateSignalStrengthDetectIcon((byte) 0);
            });
        } catch (Exception ex) {
            // Exception handling in case of Swing thread issues
        }
    }

    private static byte at_connect_inc = 0;

    class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        // Method to handle uncaught exceptions
        public void uncaughtException(Thread t, Throwable e) {
            // Writing exception details to log file
            objReadFiles.write_log_gprs_connectivity(" AT Serialport " + e.getMessage() + " Restarting AtPort Thread ");
            // Resetting watchdog value for AT Serial Port
            objwatDog.setAtSerial_watchdog_val((byte) 0);

            // Restarting AT Port Thread
            if (objAtPort == null) {
                // If AT Port Thread is not initialized, create a new instance and start it
                objAtPort = new ReadAtPortDataFromSerialPort();
                objAtPort.start();
            } else {
                try {
                    // If AT Port Thread is already running, interrupt it, create a new instance, and start it
                    objAtPort.interrupt();
                    objAtPort = null;
                    objAtPort = new ReadAtPortDataFromSerialPort();
                    objAtPort.start();
                } catch (Exception ex) {
                    // Exception handling in case of thread interruption or initialization issues
                }
            }
        }
    }

    private synchronized void set_embsimstkgi(int val) {
        embsimstkgi = val;
    }

    private synchronized int get_embsimstkgi() {
        return embsimstkgi;
    }

    private synchronized void set_embsimstkurc(int val) {
        embsimstkurc = val;
    }

    private synchronized int get_embsimstkurc() {
        return embsimstkurc;
    }

    private synchronized void set_timecame(boolean val) {
        time_came = val;
    }

    private synchronized boolean get_timecame() {
        return time_came;
    }

    private synchronized void set_cmeerror_came(boolean val) {
        cmeerror_came = val;
    }

    private synchronized boolean get_cmeerror_came() {
        return cmeerror_came;
    }

    private synchronized void set_cpin_came(boolean val) {
        cpinready = val;
    }

    private synchronized boolean get_cpin_came() {
        return cpinready;
    }

    private boolean check_configured_ports(byte port_no) {
        try {
            // Initializing variables
            byte port_inc = 0;
            // Accessing USB serial driver directory
            File f = new File("/sys/bus/usb-serial/drivers/option1");
            // Retrieving list of files
            File[] files = f.listFiles();
            // Checking if the directory is empty
            if (files == null) {
                return false;
            }
            // Sorting files
            Arrays.sort(files);
            // Iterating through files
            for (File file : files) {
                // Checking if the file name contains "tty"
                if (file.getName().contains("tty")) {
                    // Checking if the port number matches
                    if (port_no == port_inc) {
                        // Checking if the serial port name matches
                        if (at_serial_port_name.equals("/dev/" + file.getName())) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    port_inc++;
                }
            }
            // Cleaning up resources
            f = null;
            files = null;
        } catch (Exception ex) {
            // Exception handling
        }
        return false;
    }

// Method to set up the embedded SIM profile switch
    private void embeddedsim_profile_switch_setup() {
        try {
            // Initializing variables
            String str;
            byte j;
            boolean found = false;

            // Fetching SIM Toolkit menu
            fetch_stk_menu();
            // Resetting embedded SIM status
            set_embsimstkurc(0);
            // Sending AT command to set embedded SIM status
            str = "AT+QSTKRSP=253,0,128\r\n";
            atSerialWrite(str);

            // Waiting for response indicating embedded SIM status change
            found = false;
            for (j = 0; j < 10; j++) {
                if (get_embsimstkurc() == 36) {
                    found = true;
                    break;
                }
                Thread.sleep(2000);
            }

            // Setting embedded SIM status
            set_embsimstkurc(0);
            str = "AT+QSTKRSP=36,0,1\r\n";
            atSerialWrite(str);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            // Waiting for response indicating embedded SIM status change
            found = false;
            for (j = 0; j < 10; j++) {
                if (get_embsimstkurc() == 36) {
                    found = true;
                    break;
                }
                Thread.sleep(2000);
            }
            // Setting embedded SIM status
            set_embsimstkgi(0);
            str = "AT+QSTKGI=36\r\n";
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            // Waiting for response indicating embedded SIM status change
            found = false;
            for (j = 0; j < 10; j++) {
                if (get_embsimstkgi() == 11) {
                    found = true;
                    break;
                }
                Thread.sleep(2000);
            }
            // Sending AT command to set embedded SIM status
            atSerialWrite(str);
            set_embsimstkurc(0);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            // Setting auto switch mode based on configuration
            if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_AUTO_MODE) {
                str = "AT+QSTKRSP=36,0,11\r\n";  // Enable auto switch
            } else {
                str = "AT+QSTKRSP=36,0,12\r\n";  // Disable auto switch
            }
            // Waiting for response indicating embedded SIM status change
            found = false;
            for (j = 0; j < 10; j++) {
                if (get_embsimstkurc() == 253) {
                    found = true;
                    break;
                }
                Thread.sleep(2000);
            }
            // Sending AT command to set embedded SIM status
            atSerialWrite(str);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            // Delay for SIM profile switch setup
            Thread.sleep(10000);

        } catch (InterruptedException ex) {
            // Exception handling
        }
    }

// Method to set up the embedded SIM profile switch for Intalia
// Parameters:
//    - mode: The mode to set (0 for primary, 1 for secondary, 2 for another secondary mode)
    private void embeddedsim_profile_switch_setup_intalia(byte mode) {
        try {
            // Initializing variables
            String str = "AT+QSTKRSP=36,0,2\r\n";
            byte j;
            boolean found = false;

            // Fetching SIM Toolkit menu for Intalia
            fetch_stk_menu_intalia();
            // Checking if embedded SIM status is not 36 (embedded SIM not initialized)
            if (get_embsimstkurc() != 36) {
                // Resetting embedded SIM status
                set_embsimstkurc(0);
                // Sending AT command to set embedded SIM status
                str = "AT+QSTKRSP=253,0,1\r\n";
                atSerialWrite(str);

                found = false;
                // Waiting for response indicating embedded SIM status change
                for (j = 0; j < 10; j++) {
                    if (get_embsimstkurc() == 36) {
                        found = true;
                        break;
                    }
                    Thread.sleep(2000);
                }
            }
            // Resetting embedded SIM status
            set_embsimstkurc(0);
            // Determining AT command based on mode
            if (mode == 0) {
                str = "AT+QSTKRSP=36,0,1\r\n"; // Selecting primary mode
            } else if (mode == 1) {
                str = "AT+QSTKRSP=36,0,2\r\n"; // Selecting secondary mode
            } else if (mode == 2) {
                str = "AT+QSTKRSP=36,0,3\r\n"; // Selecting another secondary mode
            }
            // Sending AT command to set embedded SIM status
            atSerialWrite(str);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            found = false;
            // Waiting for response indicating embedded SIM status change
            for (j = 0; j < 10; j++) {
                if (get_embsimstkurc() == 253) {
                    found = true;
                    break;
                }
                Thread.sleep(2000);
            }
            // Delay for SIM profile switch setup
            Thread.sleep(10000);

        } catch (InterruptedException ex) {
            // Exception handling
        }
    }

    private boolean check_operator(boolean first_subscriber) {
        byte j = 0;
        String name;
        if (first_subscriber == true) {
            name = "AIRTEL";
        } else {
            name = "BSNL";
        }

        for (j = 0; j < 60; j++) {
            try {
                if (clsSharedVariables.getNetworkOperatorName().contains(name)) {
                    return true;
                } else if (get_timecame() == true) {
                    atSerialWrite("AT+COPS?\r\n");
                    Thread.sleep(2000);
                    if (clsSharedVariables.getNetworkOperatorName().contains(name)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private boolean check_cpinready() {
        byte j = 0;
        for (j = 0; j < 60; j++) {
            try {
                if (get_cpin_came() == true) {
                    return true;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private boolean fetch_stk_menu() {
        try {
            // Initializing variables
            byte j = 0;
            String str;
            set_cmeerror_came(false);
            set_embsimstkurc(0);

            // Sending AT command to retrieve SIM Toolkit menu items
            str = "AT+QSTKGI=37\r\n";
            atSerialWrite(str);
            Thread.sleep(10000);
            set_embsimstkurc(0);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            str = "AT+QSTKRSP=37,0\r\n";
            atSerialWrite(str);
            // Waiting for response indicating SIM Toolkit menu retrieval
            for (j = 0; j < 15; j++) {
                try {
                    if (get_embsimstkurc() == 37) {
                        break;
                    } else if (get_embsimstkurc() == 253) {
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                // Handling response
                if (get_cmeerror_came() == true || get_embsimstkurc() == 37) {
                    str = "AT+QSTKRSP=37,128\r\n";
                    atSerialWrite(str);
                    Thread.sleep(2000);
                    return false;
                } else if (get_embsimstkurc() > 0) {
                    return true;
                }
                objwatDog.setAtSerial_watchdog_val((byte) 2);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

// Method to fetch SIM Toolkit menu for Intalia
    private boolean fetch_stk_menu_intalia() {
        try {
            // Initializing variables
            byte j = 0;
            String str;
            set_cmeerror_came(false);
            set_embsimstkurc(0);

            // Sending AT command to retrieve SIM Toolkit menu items for Intalia
            str = "AT+QSTKGI=37\r\n";
            atSerialWrite(str);
            Thread.sleep(5000);
            set_embsimstkurc(0);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            str = "AT+QSTKRSP=37,0,1\r\n";
            atSerialWrite(str);
            // Waiting for response indicating SIM Toolkit menu retrieval for Intalia
            for (j = 0; j < 15; j++) {
                try {
                    if (get_embsimstkurc() == 37) {
                        break;
                    } else if (get_embsimstkurc() == 253) {
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                // Handling response
                if (get_cmeerror_came() == true || get_embsimstkurc() == 37) {
                    str = "AT+QSTKRSP=37,0,1\r\n";
                    atSerialWrite(str);
                    Thread.sleep(5000);

                    str = "AT+QSTKRSP=253,0,1\r\n";
                    atSerialWrite(str);
                    Thread.sleep(5000);
                    return false;
                } else if (get_embsimstkurc() == 253) {

                    str = "AT+QSTKRSP=253,0,1\r\n";
                    atSerialWrite(str);
                    Thread.sleep(5000);
                    return true;
                } else if (get_embsimstkurc() > 0) {

                    str = "AT+QSTKRSP=253,0,1\r\n";
                    atSerialWrite(str);
                    Thread.sleep(5000);
                    return true;
                }
                objwatDog.setAtSerial_watchdog_val((byte) 2);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

// Method to convert SIM change
// Parameters:
//    - first_subscriber: Flag indicating if it's the first subscriber
    private boolean convert_sim_change(boolean first_subscriber) {
        try {
            // Initializing variables
            String str;
            fetch_stk_menu();
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            set_cmeerror_came(false);
            str = "AT+QSTKRSP=253,0,128\r\n";
            atSerialWrite(str);
            Thread.sleep(5000);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            str = "AT+QSTKGI=36\r\n";
            atSerialWrite(str);
            Thread.sleep(10000);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            str = "AT+QSTKRSP=36,0,2\r\n";
            atSerialWrite(str);
            Thread.sleep(10000);
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            // Selecting SIM profile based on first_subscriber flag
            if (first_subscriber == true) {
                str = "AT+QSTKRSP=36,0,21\r\n";  // First subscriber (BSNL)
            } else {
                str = "AT+QSTKRSP=36,0,22\r\n"; // Second subscriber (Airtel)
            }
            objwatDog.setAtSerial_watchdog_val((byte) 2);
            atSerialWrite(str);
            Thread.sleep(10000);

        } catch (InterruptedException ex) {
            Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

// Thread class for reading data from the AT port
    private class ReadAtPortDataFromSerialPort extends Thread {

        // Increment counters
        byte at_inc = 0;
        byte cpin_inc = 0;

        // Run method for the thread
        @Override
        public void run() {
            // Setting uncaught exception handler
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());

            // Command string
            String cmd;

            // Infinite loop for continuous reading
            while (true) {
                // Setting watchdog value
                objwatDog.setAtSerial_watchdog_val((byte) 2);
                try {
                    // if (getGsmModuleOn() == true)
                    {
                        if (connectAtCommandsSerialport() == false) {
                            ////////System.out.println("coonected false");
                            setAtPktStatus(false);
                            set_at_comport_connected(false);
                            at_inc++;
                            if (at_inc > 5) {
                                at_connect_inc++;
                                at_inc = 0;
                                //////////System.out.println("ReadAtPortDataFromSerialPort");
                                close_at_serial_port();
                                set_at_comport_connected(false);

                                if (at_connect_inc >= 10) {
                                    at_connect_inc = 0;
                                    objReadFiles.write_log_gprs_connectivity("AT Disconnect waiting for 50 sec restart Module");
                                    objwatDog.setAtSerial_watchdog_val((byte) 0);
                                    restart_module_ports_changed(false);
                                }
                            }
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                            }
                        } else {

                            while (true) {
                                ////////System.out.println("connected true");
                                objwatDog.setAtSerial_watchdog_val((byte) 2);
                                try {
                                    if (at_serialPort == null) {
                                        set_at_comport_connected(false);
                                        break;
                                    } else if (at_serialPort.isOpened() == false) {
                                        set_at_comport_connected(false);
                                        break;
                                    }
                                    if (check_configured_ports(clsDefines.AT_SERIAL_PORT_SERIAL_NO) == false) {
                                        set_at_comport_connected(false);
                                        ////////System.out.println("ReadAtPortDataFromSerialPort check_configured_ports");
                                        close_at_serial_port();
                                        objReadFiles.write_gps_connect_log("PORTS CHANGED at ");
                                        break;
                                    }
                                    ////////System.out.println("IN AT Serialport...");
                                    if (clsSharedVariables.getEmbNorSimType() == clsDefines.EMBEDDED_SIM) {
                                        //////System.out.println("Embedded SIM");
                                        objwatDog.setAtSerial_watchdog_val((byte) 0);
                                        if (clsSharedVariables.getEmbeddedSimName() == clsDefines.SENSORIZE_SIM) {
                                            //////System.out.println("SENSORIZE_SIM sim");
                                            if (clsSharedVariables.getEmbSimAutoCommandStart() == 1) {
                                                objReadFiles.write_gps_connect_log("PROFILE SETUP FROM AT ");
                                                ////////System.out.println("PROFILE SETUP FROM AT");
                                                clsSharedVariables.setEmbSimAutoCommandStart((byte) 0);
                                                embeddedsim_profile_switch_setup();
                                                objwatDog.setAtSerial_watchdog_val((byte) 2);
                                                if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_MANUAL_MODE) {
                                                    convert_sim_change(false); //SECONDARY mode airtel
                                                    clsSharedVariables.setEmbSimManualSwitchMode(clsDefines.SWITCH_SECONDARY_MODE);
                                                } else if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_FIXED_MODE) {
                                                    if (clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_PRIMARY_MODE) {
                                                        convert_sim_change(true);
                                                    } else {
                                                        convert_sim_change(false);
                                                    }
                                                }

                                        } else if (clsSharedVariables.getEmbSimAutoCommandStart() == 2) {
                                            objReadFiles.write_gps_connect_log("PROFILE change to primary FROM AT ");
                                            clsSharedVariables.setEmbSimAutoCommandStart((byte) 0);
                                            convert_sim_change(true);
                                        } else if (clsSharedVariables.getEmbSimAutoCommandStart() == 3) {
                                            objReadFiles.write_gps_connect_log("PROFILE change to secondary FROM AT ");
                                            clsSharedVariables.setEmbSimAutoCommandStart((byte) 0);
                                            convert_sim_change(false);
                                        }
                                    } else if (clsSharedVariables.getEmbeddedSimName() == clsDefines.INTALIA_SIM) {
                                        // Handling automatic profile setup and switching for Intalia SIM
                                        if (clsSharedVariables.getEmbSimAutoCommandStart() == 1
                                                || clsSharedVariables.getEmbSimAutoCommandStart() == 2
                                                || clsSharedVariables.getEmbSimAutoCommandStart() == 3) {
                                            clsSharedVariables.setEmbSimAutoCommandStart((byte) 0);
                                            if (clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_PRIMARY_MODE) {
                                                embeddedsim_profile_switch_setup_intalia((byte) 0);
                                            } else if ((clsSharedVariables.getEmbSimFixedSwitchMode()) == clsDefines.SWITCH_SECONDARY_MODE) {
                                                embeddedsim_profile_switch_setup_intalia((byte) 1);
                                            } else if (clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_THIRD_MODE) {
                                                embeddedsim_profile_switch_setup_intalia((byte) 2);
                                            }
                                            // Refreshing AT commands
                                            atSerialWrite("AT+CFUN=0\n");
                                            Thread.sleep(5000);
                                            atSerialWrite("AT+CFUN=1\n");
                                            Thread.sleep(5000);
                                        }
                                    }
                                }

                                // Checking phone call type and SIM readiness
                                if (get_phonecall_type() != OUT_CALL) {
                                    if (getSimReady() == false) {
                                        cmd = "AT+QSIMSTAT?\r\n";
                                        atSerialWrite(cmd);
                                        Thread.sleep(1000);
                                        atSerialWrite("AT+CPIN?\r\n");
                                        Thread.sleep(200);
                                        cmd = null;
                                    } else if (cpin_inc++ > 5) {
                                        cpin_inc = 0;
                                        atSerialWrite("AT+COPS?\r\n");
                                        Thread.sleep(6000);
                                        atSerialWrite("AT+QENG=\"servingcell\"\r\n");
                                        Thread.sleep(1000);
                                        atSerialWrite("AT+QENG=\"neighbourcell\"\r\n");
                                        Thread.sleep(1000);
                                    }

                                        if (clsSharedVariables.getChkNetBalance() == true) {
                                            ////////System.out.println("IN AT 7...");
                                            clsSharedVariables.setChkNetBalance(false);
                                            if (getSimReady() == true) {
                                                cmd = "AT+CUSD=1,\"" + clsSharedVariables.getChkNetBalCommand() + "\",15\r\n";
                                                atSerialWrite(cmd);
                                                Thread.sleep(200);
                                                cmd = null;
                                            }
                                        }
                                        ////////System.out.println("IN AT 8...");
                                        if (get_at_came() == true) {
                                            ////////System.out.println("IN AT 9...");
                                            set_at_came(false);
                                            at_connect_inc = 0;
                                            at_inc = 0;
                                        } else {
                                            ////////System.out.println("IN AT 9...");
                                            at_inc++;
                                            if (at_inc > 20) {
                                                at_inc = 0;
                                                at_connect_inc++;
                                                setAtPktStatus(false);
                                                ////////System.out.println("ReadAtPortDataFromSerialPort at_inc>20");
                                                close_at_serial_port();
                                                set_at_comport_connected(false);
                                                objReadFiles.write_log_gprs_connectivity("AT Disconnected Closing AT Serial port ");
                                                if (at_connect_inc >= 5) {
                                                    at_connect_inc = 0;
                                                    objReadFiles.write_log_gprs_connectivity("AT Disconnect Restarting Module");
                                                    objwatDog.setAtSerial_watchdog_val((byte) 0);
                                                    restart_module_ports_changed(false);
                                                }
                                                break;
                                            }
                                        }

                                    }
                                    ////////System.out.println("At port 2");
                                } catch (Exception ex) {
                                    break;
                                }
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) {

                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                // Delay before next iteration
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {

                }
            }
        }
    }

// Method to connect to AT commands serial port
    boolean connectAtCommandsSerialport() {
        String[] portNames = new String[6];
        File f;
        File[] files;
        byte port_inc = 0;
        boolean found = false;
        try {
            // Closing serial port if already opened
            if (at_serialPort != null) {
                if (at_serialPort.isOpened() == true) {
                    at_serialPort.closePort();
                }
            }
            try {
                // Finding available serial ports
                f = new File("/sys/bus/usb-serial/drivers/option1");
                files = f.listFiles();
                if (files == null) {
                    return false;
                } else {
                    Arrays.sort(files);
                    for (File file : files) {
                        if (file.getName().contains("tty")) {
                            portNames[port_inc] = "/dev/" + file.getName();
                            if (port_inc == clsDefines.AT_SERIAL_PORT_SERIAL_NO) {
                                at_serial_port_name = portNames[clsDefines.AT_SERIAL_PORT_SERIAL_NO];
                                found = true;
                                break;
                            }
                            port_inc++;
                        }
                    }
                }
            } catch (Exception ex) {
            }

            if (found == false) {
                //check 
                return false;
            }

            // Opening serial port
            at_serialPort = (SerialPort) new SerialPort(at_serial_port_name);
            at_serialPort.openPort();
            at_serialPort.setParams(AT_BAUDRATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            objReadFiles.write_log_gprs_connectivity("AT PORT OPENED " + at_serial_port_name.charAt(at_serial_port_name.length() - 1));
            at_serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            Thread.sleep(10000);
            default_write_at_commands();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            portNames = null;
            files = null;
            f = null;
        }
    }

// Method to close AT serial port
    public static void close_at_serial_port() {
        try {
            // Resetting watchdog value
            clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
            objwatDog.setAt_watchdog_val((byte) 0);
            objwatDog = null;
            // Closing serial port
            if (at_serialPort != null) {
                at_serialPort.removeEventListener();
                at_serialPort.closePort();
            }
            try {
                // Updating UI
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_no.png")));
                        imgNetwork.setVisible(false);
                        imgSigStr.repaint();
                    }
                });
            } catch (Exception ex) {
            }
            Thread.sleep(1000);
        } catch (SerialPortException ex) {
        } catch (Exception ex) {
        } finally {
            at_serialPort = null;
        }
    }

// Method for writing to AT serial port
    public static synchronized void atSerialWrite(String str) {
        try {
            // Writing to serial port
            if (str.equals("+++")) {
                if (at_serialPort != null) {
                    Thread.sleep(1000);
                    at_serialPort.writeString(str);
                    Thread.sleep(1000);
                }
            } else {
                if (at_serialPort != null) {
                    at_serialPort.writeString(str);
                }
            }
        } catch (SerialPortException ex) {
        } catch (InterruptedException ex) {
        }
    }

    public static synchronized String atSerialRead() {
        String str = "";
        try {

            if (at_serialPort != null) {
                str = at_serialPort.readString();

            }

        } catch (SerialPortException ex) {
        }
        return str;
    }

    public static synchronized void atSerialWriteChar(byte str) {
        try {

            if (at_serialPort != null) {
                at_serialPort.writeByte(str);
            }

        } catch (SerialPortException ex) {
        }
    }

    public static synchronized void atSerialWriteSms() {
        try {
            if (at_serialPort != null) {
                at_serialPort.writeString("");
                at_serialPort.writeByte((byte) 26);
            }
        } catch (SerialPortException ex) {
        }
    }

// Method for setting SMS data to send
    public static synchronized boolean setSmsDataToSend() {
        if (clsSharedVariables.getSmsPhoneNo().length() >= 10) {
            clsSharedVariables.setSmsData("");
            String dat = "AT+CMGS=\"" + clsSharedVariables.getSmsPhoneNo() + "\"\r\n";
            atSerialWrite(dat);
            clsSharedVariables.setSendSmsState(true);
            dat = null;
            return true;
        } else {
            return false;
        }
    }
    boolean alert_window_opened = false;
    static JOptionPane alertEndCall = null;
    static JOptionPane alertCall = null;
    static JDialog CallDialog = null;
    static JDialog EngCallDialog = null;
    byte in_call_connect = NO_CALL;
    long sec;
    SimpleDateFormat sdf_dt = new SimpleDateFormat("yy/MM/dd,HH:mm:ss");
    SimpleDateFormat sdf_dt1 = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss");

    int SCROLL_BUFFER_SIZE = 50;

    private synchronized void updateSimDetectIcon(boolean state) {
        if (state == false) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgSimDetect.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/no-sim-card.png"));
            } else {
                imgSimDetect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/no-sim-card.png")));
            }
            imgSimDetect.repaint();
            imgSimDetect.setVisible(true);
        } else {
            imgSimDetect.setVisible(false);
        }
    }

// Method for updating network detection icon
    private synchronized void updateNetworkDetectIcon(boolean state, byte no) {

        if (clsDefines.obu_images_filepath.exists() == true) {
            switch (no) {
                case 2:
                    imgNetwork.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/2g.png"));
                    imgNetwork.repaint();
                    imgNetwork.setVisible(true);
                    break;
                case 3:
                    imgNetwork.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/3g.png"));
                    imgNetwork.repaint();
                    imgNetwork.setVisible(true);
                    break;
                case 4:
                    imgNetwork.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/lte.png"));
                    imgNetwork.repaint();
                    imgNetwork.setVisible(true);
                    break;
                default:
                    break;
            }
        } else {
            switch (no) {
                case 2:
                    imgNetwork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2g.png")));
                    imgNetwork.repaint();
                    imgNetwork.setVisible(true);
                    break;
                case 3:
                    imgNetwork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/3g.png")));
                    imgNetwork.repaint();
                    imgNetwork.setVisible(true);
                    break;
                case 4:
                    imgNetwork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lte.png")));
                    imgNetwork.repaint();
                    imgNetwork.setVisible(true);
                    break;
                default:
                    break;
            }
        }
        if (no == 0) {
            if (state == true) {
                imgNetwork.setVisible(true);
            } else {
                imgNetwork.setVisible(false);
            }
        }
    }

    private synchronized void updateSignalStrengthDetectIcon(byte no) {

        if (clsDefines.obu_images_filepath.exists() == true) {
            switch (no) {
                case 0:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/signal-strength_no.png"));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 1:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/signal-strength_1.png"));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 2:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/signal-strength_2.png"));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 3:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/signal-strength_3.png"));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 4:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/signal-strength_4.png"));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 5:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/signal-strength_5.png"));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                default:
                    break;
            }
        } else {
            switch (no) {
                case 0:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_no.png")));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 1:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_1.png")));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 2:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_2.png")));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 3:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_3.png")));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 4:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_4.png")));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                case 5:
                    imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_5.png")));
                    imgSigStr.repaint();
                    imgSigStr.setVisible(true);
                    break;
                default:
                    break;
            }
        }
    }

    //port reader is used for reading the data and processing the data.
    private class PortReader implements SerialPortEventListener {

        String[] split_str;
        String data;
        String[] split_data_str;
        boolean operator_name_save = false;
        int int_val;
        clsSmsDataQueue smsQueue = null;
        cls16833Protocols objRes1 = null;

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && (event.getEventValue() > 0)) {
                smsQueue = new clsSmsDataQueue();
                objRes1 = new cls16833Protocols();
                try {
                    setAtPktStatus(true);
                    set_at_came(true);
                    objwatDog.setAt_watchdog_val((byte) 2);
                    Thread.sleep(10);
                    data = at_serialPort.readString();
                    if (data == null) {
                        return;
                    }
                    data = data.trim();
                    int k;
                    int j;
                    int data_bal;
                    byte qeng_no = 0;
                    String sms_data = "";
                    if (getGsmDiagEnabled()) {
                        try {
                            SwingUtilities.invokeLater(() -> {
                                PanGpsDiag.txtData.append(data);
                                PanGpsDiag.txtData.append("\n");
                                int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - SCROLL_BUFFER_SIZE;
                                if (numLinesToTrunk > 0) {
                                    try {
                                        int posOfLastLineToTrunk = PanGpsDiag.txtData.getLineEndOffset(numLinesToTrunk - 1);
                                        PanGpsDiag.txtData.replaceRange("", 0, posOfLastLineToTrunk);
                                    } catch (BadLocationException ex) {
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

                    set_at_comport_connected(true);
                    //////////System.out.println("At Data : " + data);
                    if (data.contains(">")) {
                        if (clsSharedVariables.getSendSmsState() == true) {
                            clsSharedVariables.setSmsResponseCame((byte) 0);
                            setSendSmsGreaterSymState(true);
                            sms_data = clsSharedVariables.getSmsData();
                            clsSharedVariables.setSmsData("");
                            if (sms_data.trim().equals("")) {
                                if (smsQueue.getLength() > 0) {
                                    sms_data = smsQueue.removeData();
                                } else {
                                    cls16833Protocols obj16833 = new cls16833Protocols();
                                    sms_data = obj16833.smsMessagePkt();
                                    obj16833 = null;
                                }
                            }
                            atSerialWrite(sms_data + "\r");
                            atSerialWriteSms();
                            clsSharedVariables.setSendSmsState(false);
                        }
                    }
                    split_str = data.split("\\r\\n");
                    for (j = 0; j < split_str.length; j++) {
                        data = split_str[j];
                        data = data.trim();

                        if (!data.contains("+QENG:")) {
                            qeng_no = 0;
                        }
                        if (data.contains("+CME ERROR: SIM not inserted")) {
                            setSimReady(false);
                            setGsmClkState(GSM_OFF);
                            try {
                                SwingUtilities.invokeLater(() -> {
                                    updateSimDetectIcon(false);
                                });
                            } catch (Exception ex) {

                            }
                        } else if (data.contains("CPIN")) {
                            setGsmModuleOn(true);
                            if (data.contains("NOT READY")) {
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        updateSimDetectIcon(false);
                                    });
                                } catch (Exception ex) {

                                }
                                setSimReady(false);
                                setGsmClkState(GSM_OFF);
                            } else if (data.contains("CPIN: READY")) {
                                setSimReady(true);
                                set_cpin_came(true);
                                try {
                                    SwingUtilities.invokeLater(() -> {

                                        updateSimDetectIcon(true);
                                    });
                                } catch (Exception ex) {

                                }

                            }
                        } else if (data.contains("NORMAL POWER DOWN")) {
                            setSimReady(false);
                            setGsmModuleOn(false);
                            try {
                                SwingUtilities.invokeLater(() -> {
                                    updateSimDetectIcon(false);
                                });
                            } catch (Exception ex) {

                            }
                        } else if (data.contains("POWERED DOWN")) {
                            setSimReady(false);
                            setGsmModuleOn(false);
                            setGsmClkState(GSM_OFF);
                            try {
                                SwingUtilities.invokeLater(() -> {
                                    updateSimDetectIcon(false);
                                });
                            } catch (Exception ex) {

                            }
                        } else if (data.contains("QSIMSTAT")) {
                            setGsmModuleOn(true);
                            split_data_str = data.split(",", -1);
                            if (split_data_str.length > 1) {
                                try {
                                    data_bal = Integer.parseInt(split_data_str[1].trim());
                                    if (data_bal == 1) {
                                        setSimReady(true);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateSimDetectIcon(true);
                                            });
                                        } catch (Exception ex) {

                                        }

                                    } else if (data_bal == 0) {
                                        setSimReady(false);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateSimDetectIcon(false);
                                            });
                                        } catch (Exception ex) {

                                        }
                                    }
                                } catch (Exception ex) {
                                }
                            }
                        } else if (data.contains("+CCLK")) {

                            setGsmClkState(GSM_CLK_FIXED);
                            // setSimReady(true);
                            setGsmModuleOn(true);
                            if (getGpsState() != GPS_FIXED) {
                                try {
                                    data = data.replaceAll("\"", "");
                                    split_data_str = data.split("CCLK:", -1);
                                    data = split_data_str[1].trim();
                                    split_data_str = data.split("\\+", -1);
                                    sec = sdf_dt.parse(split_data_str[0]).getTime() + clsDefines.FIVE_HALF_HOURS;
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTimeInMillis(sec);
                                    if (sec > DEFAULT_SEC_2018) {
                                        setCurSec(sec);
                                    }
                                    split_data_str = null;
                                    cal = null;
                                } catch (Exception ex) {
                                }
                            }
                        } else if (data.contains("+CGREG") || data.contains("+CREG")) {
                            split_data_str = data.split(",", -1);
                            setSimReady(true);
                            setGsmModuleOn(true);
                            setGsmModuleOn(true);

                            //  //////System.out.println("SIM reg " + data);
                            /* +CGREG: <stat>[,<lac>,<ci>[,<Act>]]
                            0 GSM
                            2 UTRAN
                            3 GSM W/EGPRS
                            4 UTRAN W/HSDPA
                            5 UTRAN W/HSUPA
                            6 UTRAN W/HSDPA and HSUPA
                             7 E-UTRAN
                             */
                            if (split_data_str.length == 1) {
                                split_data_str[0] = split_data_str[0].replace("+CGREG: ", "");
                                split_data_str[0] = split_data_str[0].replace("+CREG: ", "");

                                data_bal = Integer.parseInt(split_data_str[0].trim());
                                switch (data_bal) {
                                    case 1:
                                    case 5:
                                        setSimRegistered(clsDefines.SIM_REGISTERED);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateSimDetectIcon(true);
                                            });
                                        } catch (Exception ex) {

                                        }
                                        atSerialWrite("AT+QNWINFO\r\n");
                                        Thread.sleep(1000);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(false, (byte) 0);
                                            });
                                        } catch (Exception ex) {

                                        }
                                        break;
                                    case 2:
                                        setSimRegistered(clsDefines.SIM_REG_SEARCHING);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(false, (byte) 0);
                                            });
                                        } catch (Exception ex) {

                                        }
                                        break;
                                    case 3:
                                        setSimRegistered(clsDefines.SIM_REG_DENIED);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(false, (byte) 0);
                                            });
                                        } catch (Exception ex) {

                                        }
                                        break;
                                    case 4:
                                    case 0:
                                        setSimRegistered(clsDefines.SIM_UNKNOWN);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(false, (byte) 0);
                                            });
                                        } catch (Exception ex) {

                                        }
                                        break;
                                    default:
                                        break;
                                }

                            } else if (split_data_str.length > 1) {
                                //+CREG: <stat>[,<lac>,<ci>[,<Act>]]
                                //AT+CREG=2
                                //+CREG: 1,"D509","80D413D",2 
                                if (split_data_str[1].startsWith("\"")) {
                                    try {
                                        split_data_str[0] = split_data_str[0].replace("+CGREG: ", "");
                                        split_data_str[0] = split_data_str[0].replace("+CREG: ", "");

                                        data_bal = Integer.parseInt(split_data_str[0].trim());
                                        switch (data_bal) {
                                            case 1:
                                            case 5:
                                                setSimRegistered(clsDefines.SIM_REGISTERED);
                                                try {
                                                    SwingUtilities.invokeLater(() -> {
                                                        updateSimDetectIcon(true);
                                                        updateNetworkDetectIcon(false, (byte) 0);
                                                    });
                                                } catch (Exception ex) {

                                                }
                                                atSerialWrite("AT+QNWINFO\r\n");
                                                break;
                                            case 2:
                                                setSimRegistered(clsDefines.SIM_REG_SEARCHING);
                                                break;
                                            case 3:
                                                setSimRegistered(clsDefines.SIM_REG_DENIED);
                                                break;
                                            case 4:
                                                setSimRegistered(clsDefines.SIM_UNKNOWN);
                                                break;
                                            default:
                                                break;
                                        }

                                        if (split_data_str.length > 2) {
                                            split_data_str[1] = split_data_str[1].replace("\"", "");
                                            split_data_str[2] = split_data_str[2].replace("\"", "");
                                            clsSharedVariables.setLac(split_data_str[1]);
                                            clsSharedVariables.setCellId(split_data_str[2]);
                                        }
                                    } catch (NumberFormatException ex) {

                                    }

                                } else if (split_data_str.length > 2) {

                                    if (split_data_str[2].startsWith("\"")) {
                                        try {
                                            data_bal = Integer.parseInt(split_data_str[1].trim());

                                            switch (data_bal) {
                                                case 1:
                                                case 5:
                                                    atSerialWrite("AT+QNWINFO\r\n");
                                                    setSimRegistered(clsDefines.SIM_REGISTERED);
                                                    updateSimDetectIcon(true);
                                                    break;
                                                case 2:
                                                    setSimRegistered(clsDefines.SIM_REG_SEARCHING);
                                                    break;
                                                case 3:
                                                    setSimRegistered(clsDefines.SIM_REG_DENIED);
                                                    break;
                                                case 4:
                                                case 0:
                                                    setSimRegistered(clsDefines.SIM_UNKNOWN);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            if (split_data_str.length > 3) {
                                                split_data_str[2] = split_data_str[2].replace("\"", "");
                                                split_data_str[3] = split_data_str[3].replace("\"", "");
                                                clsSharedVariables.setLac(split_data_str[2]);
                                                clsSharedVariables.setCellId(split_data_str[3]);
                                            }

                                        } catch (NumberFormatException ex) {

                                        }
                                    }
                                } else if (split_data_str.length == 2) {
                                    //AT+CREG?
                                    //+CREG: 2,1,"3747","A23C2",100 
                                    try {
                                        data_bal = Integer.parseInt(split_data_str[1].trim());
                                        switch (data_bal) {
                                            case 1:
                                            case 5:
                                                setSimRegistered(clsDefines.SIM_REGISTERED);
                                                updateSimDetectIcon(true);
                                                atSerialWrite("AT+QNWINFO\r\n");
                                                break;
                                            case 2:
                                                setSimRegistered(clsDefines.SIM_REG_SEARCHING);
                                                break;
                                            case 3:
                                                setSimRegistered(clsDefines.SIM_REG_DENIED);
                                                break;
                                            case 4:
                                            case 0:
                                                setSimRegistered(clsDefines.SIM_UNKNOWN);
                                                break;
                                            default:
                                                break;
                                        }

                                    } catch (NumberFormatException ex) {

                                    }

                                }
                            }

                        } else if (data.contains("+QNWINFO:")) {
                            if (data.contains("WCDMA")) {
                                clsSharedVariables.setCellTowerMode(clsDefines.CELL_3G);
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        updateNetworkDetectIcon(true, (byte) 3);
                                    });
                                } catch (Exception ex) {

                                }
                            } else if (data.contains("LTE") || data.contains("TDSCDMA")) {
                                clsSharedVariables.setCellTowerMode(clsDefines.CELL_LTE);
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        updateNetworkDetectIcon(true, (byte) 4);
                                    });
                                } catch (Exception ex) {

                                }
                            } else if (data.contains("GSM") || (data.contains("CDMA"))) {
                                clsSharedVariables.setCellTowerMode(clsDefines.CELL_2G);
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        updateNetworkDetectIcon(true, (byte) 2);
                                    });
                                } catch (Exception ex) {

                                }
                            }

                        } else if (data.contains("+QENG:")) {
                            split_data_str = data.split(",", -1);
                            /*
                                        +QENG: "neighbourcell intra","LTE",1415,249,-10,-104,-74,-20,19,7,6,6,44
                                        +QENG: "neighbourcell inter","LTE",39150,-,-,-,-,-,-,0,14,6
                                        +QENG: "neighbourcell inter","LTE",1440,-,-,-,-,-,-,0,14,7
                                        +QENG: "neighbourcell","GSM",565,1,4,62,255,0,0,-1920,0
                                        +QENG: "neighbourcell","GSM",564,1,4,62,255,0,0,-1920,0
                                        +QENG: "neighbourcell","GSM",563,1,4,62,255,0,0,-1920,0
                                        +QENG: "neighbourcell","GSM",562,1,4,62,255,0,0,-1920,0
                                        +QENG: "neighbourcell","GSM",568,1,4,62,255,0,0,-1920,0
                                        +QENG: "neighbourcell","GSM",567,1,4,62,255,0,0,-1920,0
                                        +QENG: "neighbourcell","GSM",566,1,4,62,255,0,0,-1920,0
                             */
                            if (split_data_str[0].contains("neighbourcell")) {

                                /*
                                    [+QENG:
                                    "neighbourcell","GSM",<mcc>,<mnc>,<lac>,<cellid>,<bsi
                                    c>,<arfcn>,<rxlev>,<c1>,<c2>,<c31>,<c32>
                                    […]]
                                 */

 /*
                                    +QENG: "neighbourcell","GSM",70,1,4,0,255,0,0,-1920,0 
                                 */
                                if (split_data_str.length > 6) {
                                    if (split_data_str[1].contains("neighbourcell intra")) {
                                        /*
                                        +QENG: 
                                        "neighbourcell
                                        intra","LTE",<earfcn>,<pcid>,<rsrq>,<rsrp>,<rssi>,<sinr>
                                        ,<srxlev>,<cell_resel_priority>,<s_non_intra_search>,<th
                                        resh_serving_low>,<s_intra_search>
                                        […]]

                                         */
                                        //pcid as cell id
                                        //rssi as signal strength
                                        qeng_no = 0;
                                        qeng_no++;

                                        clsSharedVariables.setNmr1Lac("0");
                                        clsSharedVariables.setNmr1SigStrength(split_data_str[6]);
                                    } else if (split_data_str[1].contains("neighbourcell inter")) {
                                        /*
                                        [+QENG: 
                                            "neighbourcell
                                            inter","LTE",<earfcn>,<pcid>,<rsrq>,<rsrp>,<rssi>,<sinr>
                                            ,<srxlev>,<threshX_low>,<threshX_high>,<cell_resel_pri
                                            ority>
                                            […]]

                                         */
                                        //pcid as cell id
                                        //rssi as signal strength
                                        if (!split_data_str[3].equals("-")) {
                                            clsSharedVariables.setNmr1CellId(split_data_str[3]);
                                        }
                                        if (qeng_no == 1) {
                                            clsSharedVariables.setNmr2CellId(split_data_str[3]);
                                            clsSharedVariables.setNmr2Lac("0");
                                            clsSharedVariables.setNmr2SigStrength(split_data_str[6]);
                                        } else if (qeng_no == 2) {
                                            clsSharedVariables.setNmr3CellId(split_data_str[3]);
                                            clsSharedVariables.setNmr3Lac("0");
                                            clsSharedVariables.setNmr3SigStrength(split_data_str[6]);
                                        }
                                        qeng_no++;
                                    } else if (split_data_str[1].contains("GSM")) {

                                        /*
                                     [+QENG:
                                        "neighbourcell","GSM",<arfcn>,<cell_resel_priority>,<thr
                                        esh_gsm_high>,<thresh_gsm_low>,<ncc_permitted>,<b
                                        and>,<bsic_id>,<rssi>,<srxlev>
                                        […]]

                                         */
                                        if (qeng_no == 2) {
                                            qeng_no++;
                                            clsSharedVariables.setNmr3CellId("0");
                                            clsSharedVariables.setNmr3Lac("0");
                                            clsSharedVariables.setNmr3SigStrength(split_data_str[9]);
                                        } else if (qeng_no == 3) {
                                            qeng_no++;
                                            clsSharedVariables.setNmr4CellId("0");
                                            clsSharedVariables.setNmr4Lac("0");
                                            clsSharedVariables.setNmr4SigStrength(split_data_str[9]);

                                        }
                                    }
                                }
                            } else if (split_data_str[0].contains("servingcell")) {
                                /*
                                 +QENG:
                                 "servingscell",<state>,"GSM",<mcc>,<mnc>,<lac>,<celli
                                 d>,<bsic>,<arfcn>,<band>,<rxlev>,<txp>,<rla>,<drx>,<c1
                                 >,<c2>,<gprs>,<tch>,<ts>,<ta>,<maio>,<hsn>,<rxlevsub>,
                                 <rxlevfull>,<rxqualsub>,<rxqualfull>,<voicecodec>
                                
                                 "+QENG: "servingcell","NOCONN","LTE","FDD",404,49,DC28701,114,1373,3,5,5,3901,-103,-11.-72,10,20
                                 */

                                // ////////System.out.println("in QENG");
                                if (split_data_str.length > 7) {
                                    if (split_data_str[2].trim().contains("LTE") || split_data_str[2].trim().contains("TDSCDMA")) {
                                        //    ////////System.out.println("LTE Mode");
                                        clsSharedVariables.setCellTowerMode(clsDefines.CELL_LTE);

                                        clsSharedVariables.setMcc(split_data_str[4]);
                                        clsSharedVariables.setMnc(split_data_str[5]);
                                        //clsSharedVariables.setLac(split_data_str[6]);
                                        clsSharedVariables.setCellId(split_data_str[6]);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(true, (byte) 4);
                                            });
                                        } catch (Exception ex) {

                                        }
                                    } else if (split_data_str[2].trim().contains("GSM") || split_data_str[2].trim().contains("CDMA")) {

                                        clsSharedVariables.setCellTowerMode(clsDefines.CELL_2G);
                                        clsSharedVariables.setMcc(split_data_str[3]);
                                        clsSharedVariables.setMnc(split_data_str[4]);
                                        clsSharedVariables.setLac(split_data_str[5]);
                                        clsSharedVariables.setCellId(split_data_str[6]);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(true, (byte) 2);
                                            });
                                        } catch (Exception ex) {

                                        }
                                    } else {

                                        clsSharedVariables.setCellTowerMode(clsDefines.CELL_3G);
                                        clsSharedVariables.setMcc(split_data_str[3]);
                                        clsSharedVariables.setMnc(split_data_str[4]);
                                        clsSharedVariables.setLac(split_data_str[5]);
                                        clsSharedVariables.setCellId(split_data_str[6]);
                                        try {
                                            SwingUtilities.invokeLater(() -> {
                                                updateNetworkDetectIcon(true, (byte) 3);
                                            });
                                        } catch (Exception ex) {

                                        }
                                    }
                                }
                            }

                        } else if (data.contains("+CMGS")) {
                            clsSharedVariables.setSmsResponseCame((byte) 1);
                            // ////////System.out.println("SMS Response Came " + data);
                            clsSharedVariables.setSendSmsState(false);
                            //  ////////System.out.println("SMS response came sent success" + data);

                        } else if (data.contains("+CMS ERROR:")) { //+CMS ERROR: unknown error 
                            clsSharedVariables.setSmsResponseCame((byte) 2);
                            if (sms_data.length() > 0) {
                                if (setSmsDataToSend() == true) {
                                    smsQueue.addData(sms_data);
                                    sms_data = "";
                                    clsSharedVariables.setSendSmsState(true);
                                } else {
                                    clsSharedVariables.setSendSmsState(false);
                                }
                            }

                            //   ////////System.out.println("SMS data error : " + data);
                        } else if (data.contains("+CUSD")) {
                            clsSharedVariables.setChkNetBalance(false);
                            clsSharedVariables.setNetBalanceStatusPresent(clsDefines.NET_BAL_NEUTRAL);
                            data = data.replaceAll("\"", "");

                            if (getSimType() == SIM_AIRTEL) {

                                //data = split_data_str[1].trim();
                                split_data_str = data.split(getChkNetBalDelimitter());
                                if (split_data_str.length > 1) {
                                    split_data_str = split_data_str[1].split("\\.", -1);

                                    if (split_data_str.length > 1) {
                                        try {
                                            data_bal = Integer.parseInt(split_data_str[0].trim());
                                            if (data_bal > 0) {
                                                clsSharedVariables.setNetBalanceStatusPresent(clsDefines.NET_BAL_PRESENT);
                                            } else {
                                                // clsSharedVariables.setNetBalanceStatusPresent(clsDefines.NET_BAL_NOTPRESENT);
                                                break;
                                            }
                                        } catch (NumberFormatException ex) {

                                        }
                                    }
                                }
                            } else if (getSimType() == SIM_IDEA) {
                                split_data_str = data.split(",", -1);

                                if (split_data_str.length > 1) {
                                    data = split_data_str[1].trim();

                                    split_data_str = data.split(" ");
                                    for (k = 0; k < split_data_str.length; k++) {

                                        try {
                                            data_bal = (int) (Double.parseDouble(split_data_str[k].trim()));
                                            if (data_bal > 0) {
                                                clsSharedVariables.setNetBalanceStatusPresent(clsDefines.NET_BAL_PRESENT);
                                            } else {
                                                clsSharedVariables.setNetBalanceStatusPresent(clsDefines.NET_BAL_NOTPRESENT);
                                                break;
                                            }
                                        } catch (Exception ex) {

                                        }
                                    }
                                }
                            } else {
                                clsSharedVariables.setNetBalanceStatusPresent(clsDefines.NET_BAL_PRESENT);
                            }
                        } else if (data.contains("+COPS")) {
                            data = data.replaceAll("\"", "");
                            split_data_str = data.split(",", -1);
                            {
                                if (split_data_str.length > 2) {
                                    if (split_data_str[2].contains("Jio")) {
                                        clsSharedVariables.setNetworkOperatorName("Jio");
                                    } else if (split_data_str[2].contains("airtel")) {
                                        clsSharedVariables.setNetworkOperatorName("Airtel");
                                    } else if (split_data_str[2].contains("dea")) {
                                        clsSharedVariables.setNetworkOperatorName("Idea");
                                    } else if (split_data_str[2].contains("BSNL")) {
                                        clsSharedVariables.setNetworkOperatorName("Bsnl");
                                    } else {
                                        clsSharedVariables.setNetworkOperatorName(split_data_str[2]);
                                    }
                                    if (operator_name_save == false) {
                                        clsReadFiles objReadFiles = new clsReadFiles();
                                        objReadFiles.write_imei_no_data(); //and operator
                                        objReadFiles = null;
                                    }
                                    if (split_data_str[2].contains("dea")) {
                                        setSimType(clsDefines.SIM_IDEA);

                                    } else if (split_data_str[2].contains("Airtel") || split_data_str[2].contains("airtel")) {
                                        // if (!clsSharedVariables.apn.contains("airtelgprs.com")) {
                                        //clsSharedVariables.apn = "airtelgprs.com";
                                        // }
                                        setSimType(clsDefines.SIM_AIRTEL);
                                        if (clsSharedVariables.getEmbNorSimType() == clsDefines.EMBEDDED_SIM
                                                && clsSharedVariables.getEmbeddedSimName() == clsDefines.INTALIA_SIM
                                                && clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_PRIMARY_MODE) {
                                            // clsSharedVariables.setEmbSimAutoCommandStart((byte) 1);
                                            //////System.out.println("IN AIRTEL MODE");
                                        }

                                    } else if (split_data_str[2].contains("BSNL")) {
                                        setSimType(clsDefines.SIM_BSNL);
                                        if (clsSharedVariables.getEmbNorSimType() == clsDefines.EMBEDDED_SIM
                                                && clsSharedVariables.getEmbeddedSimName() == clsDefines.INTALIA_SIM
                                                && clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_SECONDARY_MODE) {
                                            //clsSharedVariables.setEmbSimAutoCommandStart((byte) 1);
                                            //////System.out.println("IN BSNL MODE");
                                        }
                                    }
                                } else if (split_data_str.length == 1) {
                                    //clsSharedVariables.setNetworkOperatorName("NO Operator");
                                }

                            }
                            if (clsSharedVariables.getSimRegistered() == SIM_NOT_CHECKED) {
                                atSerialWrite("AT+CGREG?\r\n");
                                atSerialWrite("AT+CREG=2\r\n");
                                atSerialWrite("AT+CGREG=2\r\n");
                            }
                        } else if (data.contains("+CTZE")) {
                            //+CTZE: "+22",0,"2018/12/06,06:14:03"
                            //objReadFiles.write_log_low_memory_data(data);

                            set_timecame(true);
                            if (getGpsState() != GPS_FIXED) {
                                try {
                                    data = data.replaceAll("\"", "");  //REMOVE " (QUOTATIONS FROM DATA)
                                    split_data_str = data.split(",", -1);
                                    data = split_data_str[2].trim() + "," + split_data_str[3].trim();
                                    sec = (sdf_dt1.parse(data).getTime()) + clsDefines.FIVE_HALF_HOURS;
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTimeInMillis(sec);
                                    if (sec > DEFAULT_SEC_2018) {
                                        setCurSec(sec);
                                        // clsApcPacketConstruct objApcPktCon = new clsApcPacketConstruct();
                                        // objApcPktCon.apc_update_datetime_packet();
                                        //  objApcPktCon = null;

                                    }
                                    split_data_str = null;
                                } catch (ParseException ex) {
                                }
                            }
                            atSerialWrite("AT+COPS?\r\n");
                            if (clsSharedVariables.getSimRegistered() == SIM_NOT_CHECKED) {
                                atSerialWrite("AT+CGREG?\r\n");
                                atSerialWrite("AT+CREG=2\r\n");
                                atSerialWrite("AT+CGREG=2\r\n");
                            }
                        } else if (data.contains("+QSTKURC")) {
                            //////System.out.println("value +QSTKURC :" + data);
                            split_data_str = data.split(" ", -1);
                            if (split_data_str.length > 1) {
                                try {
                                    int_val = Integer.parseInt(split_data_str[1].trim());
                                    set_embsimstkurc(int_val);
                                    //////System.out.println("value +QSTKURC :" + int_val);

                                } catch (NumberFormatException ex) {
                                }
                            }
                        } else if (data.contains("+QSTKGI")) {
                            //////System.out.println("value +QSTKGI :" + data);
                            split_data_str = data.split(",", -1);
                            if (split_data_str.length > 1) {
                                try {
                                    int_val = Integer.parseInt(split_data_str[1].trim());
                                    set_embsimstkgi(int_val);
                                    //////System.out.println("value +QSTKGI :" + int_val);
                                } catch (Exception ex) {
                                }
                            }
                        } else if (data.contains("+CME ERROR")) {
                            set_cmeerror_came(true);
                            //////System.out.println("value +CME ERROR :" + data);
                        } else if (data.contains("BUSY")) {
                            clip_came = 0;
                            set_voice_call_enabled_status(false);
                            close_call_end_gpio_controls();
                            set_phonecall_type(CALL_BUSY);
                            try {
                                if (alertEndCall != null) {
                                    alertEndCall.setVisible(false);
                                    alertEndCall.removeAll();
                                    alertEndCall = null;
                                    EngCallDialog.setVisible(false);
                                    EngCallDialog.dispose();
                                }
                            } catch (Exception ex) {
                            }
                            try {
                                if (alertCall != null) {
                                    alertCall.removeAll();
                                    alertCall = null;
                                    CallDialog.setVisible(false);
                                    CallDialog.dispose();
                                    CallDialog = null;
                                }
                            } catch (Exception ex) {
                            }
                        } else if (data.contains("NO DIALTONE")) {
                            clip_came = 0;
                            set_voice_call_enabled_status(false);
                            close_call_end_gpio_controls();
                            set_phonecall_type(CALL_DIALTONE);
                            try {
                                if (alertEndCall != null) {
                                    alertEndCall.setVisible(false);
                                    alertEndCall.removeAll();
                                    alertEndCall = null;
                                    EngCallDialog.setVisible(false);
                                    EngCallDialog.dispose();
                                }
                            } catch (Exception ex) {
                            }
                            try {
                                if (alertCall != null) {
                                    alertCall.removeAll();
                                    alertCall = null;
                                    CallDialog.setVisible(false);
                                    CallDialog.dispose();
                                    CallDialog = null;
                                }
                            } catch (Exception ex) {
                            }
                        } else if (data.contains("NO ANSWER")) {
                            clip_came = 0;
                            set_voice_call_enabled_status(false);
                            close_call_end_gpio_controls();
                            set_phonecall_type(CALL_DIALTONE);
                            try {
                                if (alertEndCall != null) {
                                    alertEndCall.setVisible(false);
                                    alertEndCall.removeAll();
                                    alertEndCall = null;
                                    EngCallDialog.setVisible(false);
                                    EngCallDialog.dispose();
                                }
                            } catch (Exception ex) {
                            }
                            try {
                                if (alertCall != null) {
                                    alertCall.removeAll();
                                    alertCall = null;
                                    CallDialog.setVisible(false);
                                    CallDialog.dispose();
                                    CallDialog = null;
                                }
                            } catch (Exception ex) {
                            }
                        } else if (data.contains("NO CARRIER")) {
                            clip_came = 0;
                            set_voice_call_enabled_status(false);
                            close_call_end_gpio_controls();

                            set_phonecall_type(CALL_NOCARRIER);
                            try {
                                if (alertEndCall != null) {
                                    alertEndCall.setInputValue(JOptionPane.OK_OPTION);
                                    alertEndCall.setVisible(false);
                                    alertEndCall.hide();
                                    alertEndCall.removeAll();
                                    alertEndCall = null;
                                    EngCallDialog.setVisible(false);
                                    EngCallDialog.dispose();
                                }
                            } catch (Exception ex) {

                            }
                            try {
                                if (alertCall != null) {
                                    alertCall.removeAll();
                                    alertCall = null;
                                    CallDialog.setVisible(false);
                                    CallDialog.dispose();
                                    CallDialog = null;
                                }
                            } catch (Exception ex) {

                            }

                        } else if (data.contains("RING") && data.contains("CLIP") && clip_came == 0) {

                            clsSharedVariables.call_in_mobile_no = "";
                            split_data_str = data.split("[\\r\\n]+");

                            if (split_data_str.length > 1) {

                                split_data_str = split_data_str[1].split(":");

                                if (split_data_str.length > 1) {

                                    split_data_str = split_data_str[1].split(",");
                                    split_data_str[0] = split_data_str[0].trim().replace("+", "");
                                    split_data_str[0] = split_data_str[0].trim().replace("\"", "");
                                    clsSharedVariables.call_in_mobile_no = split_data_str[0].trim();
                                    objReadFiles.read_incoming_call_name(clsSharedVariables.call_in_mobile_no);
                                    if (objReadFiles.check_phone_no_details(clsSharedVariables.call_in_mobile_no) == false) {

                                        clip_came = 0;
                                        StringBuilder sb1 = new StringBuilder();
                                        set_voice_call_enabled_status(false);

                                        try {
                                            sb1.append("ATH\r\n");
                                            atSerialWrite(sb1.toString());
                                            sb1.append("AT+CHUP\r\n");
                                            atSerialWrite(sb1.toString());

                                        } catch (Exception ex) {
                                        }
                                        sb1 = null;
                                        close_call_end_gpio_controls();
                                        return;
                                    }

                                }
                                clip_came = 2;
                                {
                                    alert_window_opened = true;
                                    open_call_gpio_controls();
                                    // set_phonecall_type(CALL_RING);
                                    /*
                                     open_call_gpio_controls();

                                    
                                     File file = new File(route_filepath, "ring.wav");
                                     File f = new File(main_route_filepath, "ring.wav");
                                     if (file.exists()) {
                                     clsAudioFilesQueue objQue = new clsAudioFilesQueue();
                                     objQue.addData("/ring.wav");
                                     } else if (f.exists()) {
                                     clsAudioFilesQueue objQue = new clsAudioFilesQueue();
                                     objQue.addData("/ring.wav");
                                     }                                   
                                     */
                                    SwingUtilities.invokeLater(() -> {
                                        alertCall = new JOptionPane();
                                        alertCall.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                                        Font fontBtn = new Font("Courier", Font.BOLD, 16);
                                        JPanel buttonPanel = (JPanel) alertCall.getComponent(1);
                                        JButton buttonOk = (JButton) buttonPanel.getComponent(0);
                                        buttonOk.setText("Accept");
                                        buttonOk.setPreferredSize(new Dimension(100, 50));  //Set Button size here
                                        buttonOk.setBackground(Color.LIGHT_GRAY);
                                        buttonOk.setFont(fontBtn);
                                        JButton buttonCancel = (JButton) buttonPanel.getComponent(1);
                                        buttonCancel.setText("Reject");
                                        buttonCancel.setPreferredSize(new Dimension(100, 50));  //Set Button size here
                                        buttonCancel.setBackground(Color.LIGHT_GRAY);
                                        buttonCancel.setFont(fontBtn);
                                        Font font = new Font("Arial", Font.BOLD, 14);
                                        Object[] call_options = {buttonOk, buttonCancel};
                                        alertCall.setOptions(call_options);

                                        alertCall.setFont(font);
                                        JLabel lblTxt = new JLabel();
                                        lblTxt.setFont(fontBtn);
                                        lblTxt.setBackground(Color.RED);
                                        lblTxt.setText("Incoming Call: \n" + clsSharedVariables.call_in_mobile_name + ":" + clsSharedVariables.call_in_mobile_no);
                                        alertCall.setMessage(lblTxt);
                                        //alertCall.setMessage("   Incoming Call " + clsSharedVariables.call_in_mobile_no + "    ");
                                        alertCall.setPreferredSize(new Dimension(400, 200));
                                        alertCall.setSize(new Dimension(400, 200));
                                        alertCall.setBackground(Color.blue);

                                        CallDialog = alertCall.createDialog(frameDialogBox, "Call");
                                        CallDialog.setFont(font);
                                        //CallDialog.setSize(new Dimension(400, 400));
                                        CallDialog.setBackground(Color.BLUE);
                                        CallDialog.setVisible(true);
                                        try {
                                            CallDialog.setAutoRequestFocus(true);
                                            CallDialog.setAlwaysOnTop(true);
                                        } catch (Exception ex) {

                                        }

                                        JButton btn = (JButton) alertCall.getValue();

                                        if ((btn.getText().contains("Accept"))) {
                                            clip_came = 3;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("ATA\r\n");

                                            try {
                                                atSerialWrite(sb.toString());
                                                //  atSerialWrite(sb.toString());
                                                //  atSerialWrite(sb.toString());
                                            } catch (Exception ex) {
                                            }
                                            try {
                                                Thread.sleep(2000);
                                            } catch (InterruptedException ex) {
                                                //Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            sb = null;
                                            clip_came = 0;
                                            alertCall = null;
                                            CallDialog.setVisible(false);
                                            CallDialog.dispose();
                                            CallDialog = null;
                                            SwingUtilities.invokeLater(() -> {
                                                alert_window_opened = false;
                                                alertEndCall = new JOptionPane();
                                                Font fontBtn1 = new Font("Courier", Font.BOLD, 16);
                                                JPanel buttonPanel1 = (JPanel) alertEndCall.getComponent(1);
                                                JButton buttonOk1 = (JButton) buttonPanel1.getComponent(0);
                                                buttonOk1.setText("End Call");
                                                buttonOk1.setPreferredSize(new Dimension(150, 50)); //Set Button size here
                                                buttonOk1.setBackground(Color.LIGHT_GRAY);
                                                buttonOk1.setFont(fontBtn1);
                                                Object[] options = {buttonOk1};
                                                alertEndCall.setOptions(options);
                                                alertEndCall.setPreferredSize(new Dimension(400, 200));
                                                alertEndCall.setSize(new Dimension(400, 200));
                                                alertEndCall.setBackground(Color.green);
                                                alertEndCall.setFont(fontBtn1);
                                                alertEndCall.setMessage("Call Connected \n" + clsSharedVariables.call_in_mobile_no);
                                                EngCallDialog = alertEndCall.createDialog(frameDialogBox, "Call Connected \n" + clsSharedVariables.call_in_mobile_no);
                                                EngCallDialog.setVisible(true);
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException ex) {
                                                    //Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                JButton btn1 = (JButton) alertEndCall.getValue();
                                                if (btn1.getText().equals("End Call")) {
                                                    StringBuilder sb1 = new StringBuilder();
                                                    set_voice_call_enabled_status(false);
                                                    try {
                                                        sb1.append("ATH\r\n");
                                                        atSerialWrite(sb1.toString());
                                                        sb1.append("AT+CHUP\r\n");
                                                        atSerialWrite(sb1.toString());

                                                    } catch (Exception ex) {
                                                    }
                                                    sb1 = null;
                                                    close_call_end_gpio_controls();
                                                    clip_came = 0;
                                                    if (alertEndCall != null) {
                                                        alertEndCall.hide();
                                                        alertEndCall.removeAll();
                                                        alertEndCall = null;
                                                        EngCallDialog.setVisible(false);
                                                        EngCallDialog.dispose();
                                                    }
                                                } else {
                                                    StringBuilder sb1 = new StringBuilder();
                                                    set_voice_call_enabled_status(false);
                                                    try {
                                                        sb1.append("ATH/r/n");
                                                        atSerialWrite(sb1.toString());
                                                        sb1.append("AT+CHUP\r\n");
                                                        atSerialWrite(sb1.toString());

                                                    } catch (Exception ex) {
                                                    }
                                                    sb1 = null;
                                                    close_call_end_gpio_controls();
                                                    clip_came = 0;
                                                    if (alertEndCall != null) {
                                                        alertEndCall.hide();
                                                        alertEndCall.removeAll();
                                                        alertEndCall = null;
                                                        EngCallDialog.setVisible(false);
                                                        EngCallDialog.dispose();
                                                    }
                                                }
                                            });
                                        } else {
                                            StringBuilder sb = new StringBuilder();
                                            set_voice_call_enabled_status(false);
                                            clip_came = 3;
                                            sb.append("ATH\r\n");

                                            try {
                                                atSerialWrite(sb.toString());
                                                atSerialWrite(sb.toString());
                                            } catch (Exception ex) {

                                            }
                                            sb = null;
                                            clip_came = 0;
                                            close_call_end_gpio_controls();
                                            alert_window_opened = false;
                                            try {
                                                alertCall.removeAll();
                                                alertCall = null;
                                                alertEndCall.removeAll();
                                                alertEndCall = null;
                                            } catch (Exception ex) {

                                            }
                                        }
                                    });
                                }
                            }
                        } else if (data.contains("RING") && clip_came == 2) {
                            /*
                             File file = new File(route_filepath, "ring.wav");
                             File f = new File(main_route_filepath, "ring.wav");
                             if (file.exists()) {
                             clsAudioFilesQueue objQue = new clsAudioFilesQueue();
                             objQue.addData("/ring.wav");
                             } else if (f.exists()) {
                             clsAudioFilesQueue objQue = new clsAudioFilesQueue();
                             objQue.addData("/ring.wav");
                             }
                             file = null;
                             f = null;
                             data = null;
                             */
                        } else if (data.contains("ccinfo")) {
                            // +QIND: "ccinfo",1,0,0,0,0,"+919246246114",129 // out calling connect
                            // +QIND: "ccinfo",1,0,-1,0,0,"+919246246114",129 // out call end
                            // +QIND: "ccinfo",1,1,0,0,0,"+919246246114",145 call connecct
                            //+QIND: "ccinfo",1,1,-1,0,0,"+919246246114",145 call end
                            // 1,0,3,0,0
                            // 1,0,0,0,0
                            // 1,0,-1,0,0  
                            //   1,1,-1,0,0(incoming call)

                            //////////System.out.println("ccinfodata : " + data);
                            split_data_str = data.split(",", -1);
                            //  if (clsDefines.DEBUG_ENABLE) {
                            //  }
                            if (split_data_str.length > 4) {
                                // if (clsDefines.DEBUG_ENABLE) {
                                // }

                                split_data_str[2] = split_data_str[2].trim();
                                split_data_str[3] = split_data_str[3].trim();
                                if (split_data_str[2].equals("0")) {
                                    //outgoing call 
                                    if (split_data_str[3].equals("0")) {
                                        set_phonecall_type(CALL_CONNECT);
                                    } else if (split_data_str[3].equals("3")) {
                                        // set_phonecall_type(CALL_CONNECT); //ringing
                                    } else if (split_data_str[3].equals("-1")) {
                                        set_phonecall_type(CALL_NOCARRIER);
                                        clip_came = 0;
                                        set_voice_call_enabled_status(false);
                                        close_call_end_gpio_controls();

                                        set_phonecall_type(CALL_NOCARRIER);
                                        try {
                                            if (alertEndCall != null) {
                                                alertEndCall.setInputValue(JOptionPane.OK_OPTION);
                                                alertEndCall.setVisible(false);
                                                alertEndCall.hide();
                                                alertEndCall.removeAll();
                                                alertEndCall = null;
                                                EngCallDialog.setVisible(false);
                                                EngCallDialog.dispose();
                                            }
                                        } catch (Exception ex) {

                                        }
                                        try {
                                            if (alertCall != null) {
                                                alertCall.removeAll();
                                                alertCall = null;
                                                CallDialog.setVisible(false);
                                                CallDialog.dispose();
                                                CallDialog = null;
                                            }
                                        } catch (Exception ex) {

                                        }

                                    }

                                } else if (split_data_str[2].equals("1")) {

                                    if (split_data_str[3].equals("0")) {
                                        set_phonecall_type(CALL_IN_CONNECT);
                                    } else if (split_data_str[3].equals("4")) {
                                        // set_phonecall_type(CALL_IN_CONNECT); //ringing
                                    } else if (split_data_str[3].equals("-1")) {
                                        set_phonecall_type(CALL_NOCARRIER);
                                        clip_came = 0;
                                        set_voice_call_enabled_status(false);
                                        close_call_end_gpio_controls();

                                        set_phonecall_type(CALL_NOCARRIER);
                                        try {
                                            if (alertEndCall != null) {
                                                alertEndCall.setVisible(false);
                                                alertEndCall.hide();
                                                alertEndCall.removeAll();
                                                alertEndCall = null;
                                                EngCallDialog.setVisible(false);
                                                EngCallDialog.dispose();
                                            }
                                        } catch (Exception ex) {

                                        }
                                        try {
                                            if (alertCall != null) {
                                                alertCall.removeAll();
                                                alertCall = null;
                                                CallDialog.setVisible(false);
                                                CallDialog.dispose();
                                                CallDialog = null;
                                            }
                                        } catch (Exception ex) {

                                        }

                                    }
                                }

                            }

                        } else if (data.contains("+QIND: \"smsfull\"")) {
                            // //////////System.out.println("SMS Full " + data);
                            atSerialWrite("AT+CMGD=1,4\r\n");

                        } else if (data.contains("GSN")) {
                            try {
                                //System.out.println("dataGSN" + data.trim());
                                Long.valueOf(data.trim());
                                setImeiNo(data.trim());

                                //System.out.println("IMEINUMBER" + clsSharedVariables.getImeiNo());
                                //System.out.println("IMEINUMBER" + clsSharedVariables.getImeiNo());
                                objReadFiles.write_imei_no_data();
                            } catch (Exception ex) {

                            }

                            // if (clsDefines.DEBUG_ENABLE) {
                            //   //////////System.out.println("IMEI Number " + data);
                            //  }
                        } else if (data.contains("QCCID")) {

                            set_QCCID(data.trim());
                            //if (clsDefines.DEBUG_ENABLE) {
                            //    //////////System.out.println("QCCID " + data);
                            // }

                        } else if (data.contains("CNUM")) {
                            split_data_str = data.split(",", -1);

                            if (split_data_str.length > 1) {
                                set_own_mobileno(split_data_str[1]);

                            }

                        } else if (data.contains("csq")) {
                            //+CSQ: 28,0
                            // Change the signal strength indication image

                            if (data.contains("QIND")) {
                                getSignalStrength(data);
                            }
                        } else if (data.contains("COLP")) {
                            // Change of voice call state
                            //+COLP: "02151082965",129,,,"QUECTEL"
                            clip_came = 0;

                            set_phonecall_type(CALL_CONNECT);

                        } else if (data.contains("CLIP") && clip_came == 0) {
                            clip_came = 1;
                            // Change of voice call state
                            //+CLIP: "02151082965",129,,,"QUECTEL",0

                            // +CLIP: "+919246246114",145,,,,0
                            clsSharedVariables.call_in_mobile_no = "";
                            split_data_str = data.split(":");
                            if (split_data_str.length > 1) {
                                split_data_str = split_data_str[1].split(",");
                                split_data_str[0] = split_data_str[0].trim().replace("+", "");
                                split_data_str[0] = split_data_str[0].trim().replace("\"", "");
                                clsSharedVariables.call_in_mobile_no = split_data_str[0].trim();
                                // System.out.println("clsSharedVariables.call_in_mobile_no " + clsSharedVariables.call_in_mobile_no);
                                objReadFiles.read_incoming_call_name(clsSharedVariables.call_in_mobile_no);
                                if (objReadFiles.check_phone_no_details(clsSharedVariables.call_in_mobile_no) == false) {

                                    clip_came = 0;
                                    StringBuilder sb1 = new StringBuilder();
                                    set_voice_call_enabled_status(false);

                                    try {
                                        sb1.append("ATH\r\n");
                                        atSerialWrite(sb1.toString());
                                        sb1.append("AT+CHUP\r\n");
                                        atSerialWrite(sb1.toString());

                                    } catch (Exception ex) {
                                    }
                                    sb1 = null;
                                    close_call_end_gpio_controls();
                                    return;
                                }
                                /* if (split_str[split_str.length - 1].equals("0")) {
                                 set_phonecall_type(clsDefines.CALL_RING_OK);
                                 } else if (split_str[split_str.length - 1].equals("1")
                                 || split_str[split_str.length - 1].equals("2")) {
                                 set_phonecall_type(clsDefines.CALL_RING_NOTOK);
                                 }*/
                                clip_came = 2;

                                {

                                    alert_window_opened = true;
                                    open_call_gpio_controls();
                                    //  set_phonecall_type(CALL_RING);
                                    /*
                                     open_call_gpio_controls();

                                     File file = new File(route_filepath, "ring.wav");
                                     File f = new File(main_route_filepath, "ring.wav");
                                     if (file.exists()) {
                                     clsAudioFilesQueue objQue = new clsAudioFilesQueue();
                                     objQue.addData("/ring.wav");
                                     } else if (f.exists()) {
                                     clsAudioFilesQueue objQue = new clsAudioFilesQueue();
                                     objQue.addData("/ring.wav");
                                     }
                                     */
                                    SwingUtilities.invokeLater(() -> {
                                        alertCall = new JOptionPane();
                                        alertCall.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                                        Font fontBtn = new Font("Courier", Font.BOLD, 16);
                                        JPanel buttonPanel = (JPanel) alertCall.getComponent(1);
                                        JButton buttonOk = (JButton) buttonPanel.getComponent(0);
                                        buttonOk.setText("Accept");
                                        buttonOk.setPreferredSize(new Dimension(100, 50));  //Set Button size here
                                        buttonOk.setBackground(Color.LIGHT_GRAY);
                                        buttonOk.setFont(fontBtn);

                                        JButton buttonCancel = (JButton) buttonPanel.getComponent(1);
                                        buttonCancel.setText("Reject");
                                        buttonCancel.setPreferredSize(new Dimension(100, 50));  //Set Button size here
                                        buttonCancel.setBackground(Color.LIGHT_GRAY);
                                        buttonCancel.setFont(fontBtn);
                                        Font font = new Font("Arial", Font.BOLD, 14);
                                        Object[] call_options = {buttonOk, buttonCancel};
                                        alertCall.setOptions(call_options);

                                        alertCall.setFont(font);
                                        JLabel lblTxt = new JLabel();
                                        lblTxt.setFont(fontBtn);
                                        lblTxt.setBackground(Color.RED);
                                        lblTxt.setText("Incoming Call: \n" + clsSharedVariables.call_in_mobile_name + ":" + clsSharedVariables.call_in_mobile_no);
                                        alertCall.setMessage(lblTxt);
                                        //alertCall.setMessage("   Incoming Call " + clsSharedVariables.call_in_mobile_no + "    ");
                                        alertCall.setPreferredSize(new Dimension(400, 200));
                                        alertCall.setSize(new Dimension(400, 200));
                                        alertCall.setBackground(Color.blue);

                                        CallDialog = alertCall.createDialog(frameDialogBox, "Call");
                                        CallDialog.setFont(font);
                                        //CallDialog.setSize(new Dimension(400, 400));
                                        CallDialog.setBackground(Color.BLUE);
                                        CallDialog.setVisible(true);

                                        JButton btn = (JButton) alertCall.getValue();

                                        if ((btn.getText().equals("Accept"))) {
                                            clip_came = 3;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("ATA\r\n");

                                            try {
                                                atSerialWrite(sb.toString());

                                            } catch (Exception ex) {
                                            }
                                            try {
                                                Thread.sleep(50);//2000);
                                            } catch (InterruptedException ex) {
                                                //Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            sb = null;
                                            clip_came = 0;
                                            alertCall = null;
                                            CallDialog.setVisible(false);
                                            CallDialog.dispose();
                                            CallDialog = null;
                                            SwingUtilities.invokeLater(() -> {
                                                alert_window_opened = false;
                                                alertEndCall = new JOptionPane();
                                                Font fontBtn1 = new Font("Courier", Font.BOLD, 16);
                                                JPanel buttonPanel1 = (JPanel) alertEndCall.getComponent(1);
                                                JButton buttonOk1 = (JButton) buttonPanel1.getComponent(0);
                                                buttonOk1.setText("End Call");
                                                buttonOk1.setPreferredSize(new Dimension(150, 50)); //Set Button size here
                                                buttonOk1.setBackground(Color.LIGHT_GRAY);
                                                buttonOk1.setFont(fontBtn1);
                                                Object[] options = {buttonOk1};
                                                alertEndCall.setOptions(options);
                                                alertEndCall.setPreferredSize(new Dimension(400, 200));
                                                alertEndCall.setSize(new Dimension(400, 200));
                                                alertEndCall.setBackground(Color.green);
                                                alertEndCall.setFont(fontBtn1);
                                                alertEndCall.setMessage("Call Connected \n" + clsSharedVariables.call_in_mobile_no);
                                                EngCallDialog = alertEndCall.createDialog(frameDialogBox, "Call Connected \n" + clsSharedVariables.call_in_mobile_no);
                                                EngCallDialog.setVisible(true);
                                                try {
                                                    Thread.sleep(50);//1000);
                                                } catch (InterruptedException ex) {
                                                    //Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                JButton btn1 = (JButton) alertEndCall.getValue();
                                                if (btn1.getText().equals("End Call")) {
                                                    StringBuilder sb1 = new StringBuilder();
                                                    set_voice_call_enabled_status(false);
                                                    try {
                                                        sb1.append("ATH\r\n");
                                                        atSerialWrite(sb1.toString());
                                                        sb1.append("AT+CHUP\r\n");
                                                        atSerialWrite(sb1.toString());

                                                    } catch (Exception ex) {
                                                    }
                                                    sb1 = null;
                                                    close_call_end_gpio_controls();
                                                    clip_came = 0;
                                                    if (alertEndCall != null) {
                                                        alertEndCall.hide();
                                                        alertEndCall.removeAll();
                                                        alertEndCall = null;
                                                        EngCallDialog.setVisible(false);
                                                        EngCallDialog.dispose();
                                                    }
                                                } else {
                                                    StringBuilder sb1 = new StringBuilder();
                                                    set_voice_call_enabled_status(false);
                                                    try {
                                                        sb1.append("ATH/r/n");
                                                        atSerialWrite(sb1.toString());
                                                        sb1.append("AT+CHUP\r\n");
                                                        atSerialWrite(sb1.toString());

                                                    } catch (Exception ex) {
                                                    }
                                                    sb1 = null;
                                                    close_call_end_gpio_controls();
                                                    clip_came = 0;
                                                    if (alertEndCall != null) {
                                                        alertEndCall.hide();
                                                        alertEndCall.removeAll();
                                                        alertEndCall = null;
                                                        EngCallDialog.setVisible(false);
                                                        EngCallDialog.dispose();
                                                    }
                                                }
                                            });
                                        } else {
                                            StringBuilder sb = new StringBuilder();
                                            set_voice_call_enabled_status(false);
                                            clip_came = 3;
                                            sb.append("ATH\r\n");

                                            try {
                                                atSerialWrite(sb.toString());
                                                atSerialWrite(sb.toString());
                                            } catch (Exception ex) {

                                            }
                                            sb = null;
                                            clip_came = 0;
                                            close_call_end_gpio_controls();
                                            alert_window_opened = false;
                                            try {
                                                alertCall.removeAll();
                                                alertCall = null;
                                                alertEndCall.removeAll();
                                                alertEndCall = null;
                                            } catch (Exception ex) {

                                            }
                                        }
                                    });
                                }
                            }
                        } else if (data.contains("+CCWA")) {
                            // Change of voice call state
                            //+CCWA: "02154450293",129,1 //Indication of a call that has been waiting

                            // set_phonecall_type(CALL_WAIT);
                        } else if (data.contains("+CLCC")) {

                            // Change of voice call state
                            // +CCWA: "02154450293",129,1 //Indication of a call that has been waiting
                            // +CLCC: <id2>,<dir>,<stat>,<mode>,<mpty>[,<number>,<type>[,<alpha>]]
                            /*
                             <dir> 0 Mobile originated (MO) call
                        
                             1 Mobile terminated (MT) call
                         
                             <stat> State of the call
                        
                             0 Active
                             1 Held
                             2 Dialing (MO call)
                             3 Alerting (MO call)
                             4 Incoming (MT call)
                             5 Waiting (MT call)
                        
                             */
                            split_data_str = data.split("+CLCC: ");
                            split_data_str = split_data_str[1].split(",");
                            String dir = split_data_str[1];
                            String stat = split_data_str[2];
                            if (dir.equals("0")) {
                                switch (stat) {
                                    case "0":
                                        set_phonecall_type(clsDefines.CALL_CONNECT);
                                        break;
                                    case "1":
                                        set_phonecall_type(clsDefines.CALL_HELD);
                                        break;
                                    case "2":
                                        set_phonecall_type(CALL_OUT_DIAL);
                                        break;
                                    case "3":
                                        set_phonecall_type(clsDefines.CALL_OUT_ALERT);
                                        break;
                                    case "4":
                                        set_phonecall_type(clsDefines.CALL_IN_RING);
                                        break;
                                    case "5":
                                        set_phonecall_type(CALL_WAIT);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else if (data.contains("+QLTONE")) {
                            //   close_call_end_gpio_controls();
                            write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);

                        } else if (data.contains("+CMTI")) {
                            // ////////System.out.println("SMS  Came " + data);
                            // setSmsReadDataCame(true);
                            split_data_str = data.split(",");
                            // //////////System.out.println("SMS Came " + data);
                            if (split_data_str.length > 1) {
                                atSerialWrite("AT+CMGR=" + split_data_str[1].trim() + "\r\n");
                                //////////System.out.println("SMS reading..  AT+CMGR=" + split_data_str[1].trim() + "\n");
                            }
                        } else if (data.contains("+CMGR")) {
                            // ////////System.out.println("SMS Response Came " + data);
                            //setSmsReadDataCame(true);
                            //////////System.out.println("SMS CMGR Res.." + data);
                        } else if (data.contains("Revision: ")) {
                            if (data.contains("EC20")) {
                                clsSharedVariables.setQuecModuleRev(clsDefines.QUECTEL_MOD_EC20);
                            } else if (data.contains("EC25")) {
                                clsSharedVariables.setQuecModuleRev(clsDefines.QUECTEL_MOD_EC25);
                            }
                        } else if (data.trim().length() == 15) {
                            try {
                                //System.out.println("imei number" + clsSharedVariables.getImeiNo());
                                Long.valueOf(data.trim());
                                //System.out.println("imei number2" + Long.valueOf(data.trim()));
                                setImeiNo(data.trim());
                                //System.out.println("imei number reading4" + clsSharedVariables.getImeiNo());

                            } catch (NumberFormatException ex) {

                            }
                        } else if (data.trim().contains("GETLOCATION")) {
                            smsQueue.addData(objRes1.smsMessagePkt());
                            setSmsDataToSend();
                            objRes1.smsMessagePkt_toserver();
                        } else if (data.trim().contains("GETHEALTH")) {
                            smsQueue.addData(objRes1.healthMsgPktSms());
                            setSmsDataToSend();
                            objRes1.healthMsgPktSms_toserver();
                        } else if (data.trim().contains("STOPSMSSUMITH")) {
                            clsSharedVariables.setCommMode(clsDefines.COMM_GPRS);
                            clsReadFiles objReadFiles = new clsReadFiles();
                            objReadFiles.write_cfg_data_file();
                            objReadFiles = null;
                        } else if (data.trim().contains("STARTSMSSUMITH") || data.trim().contains("ACTV,333999," + clsSharedVariables.getSmsPhoneNo())) {
                            clsSharedVariables.setCommMode(clsDefines.COMM_GPRS_SMS);
                            clsReadFiles objReadFiles = new clsReadFiles();
                            objReadFiles.write_cfg_data_file();
                            objReadFiles = null;
                        } else if (data.trim().contains("SETAPN")) {
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                clsSharedVariables.setApn(split_data_str[1]);
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            }
                            smsQueue.addData("$SETAPN#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETAPN");
                        } else if (data.trim().contains("SETOSLIMIT")) {
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                gpsDriving.over_speed_limit = Short.parseShort(split_data_str[1]);
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            }
                            smsQueue.addData("$SETOSLIMIT#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETOSLIMIT");
                        } else if (data.trim().contains("SETHA")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                gpsDriving.harsh_acc_threshold = Short.parseShort(split_data_str[1]);
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            }
                            smsQueue.addData("$SETHA#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETHA");
                        } else if (data.trim().contains("SETHB")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                gpsDriving.harsh_brk_threshold = Short.parseShort(split_data_str[1]);
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            }
                            smsQueue.addData("$SETHB#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETHB");
                        } else if (data.trim().contains("SETRT")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                clsSharedVariables.setGyroAngle(Short.parseShort(split_data_str[1]));
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            }
                            smsQueue.addData("$SETRT#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETRT");
                        } else if (data.trim().contains("SETPHNUM")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                clsSharedVariables.setSmsPhoneNo(split_data_str[1]);
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            }
                            smsQueue.addData("$SETPHNUM#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETPHNUM");
                        } else if (data.trim().contains("CLRCFGALL")) {
                            //  ////////System.out.println(data);

                            try {
                                gpsDriving.harsh_acc_threshold = DEF_HARSH_ACC_SPEED_1;
                                gpsDriving.harsh_brk_threshold = DEF_HARSH_BRK_SPEED_1;
                                gpsDriving.over_speed_limit = DEF_OVER_SPEED_LIMIT;
                                clsReadFiles objReadFiles = new clsReadFiles();
                                objReadFiles.write_cfg_data_file();
                                objReadFiles = null;
                            } catch (Exception ex) {

                            }
                            smsQueue.addData("$CLRCFGALL#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "CLRCFGALL");

                        } else if (data.trim().contains("SETIP1")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 3) {
                                try {
                                    clsSharedVariables.setIpAddr1(split_data_str[1]);
                                    clsSharedVariables.setPortNo1(Integer.parseInt(split_data_str[2]));
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();
                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETIP1#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETIP1");
                        } else if (data.trim().contains("SETIP2")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 3) {
                                try {
                                    clsSharedVariables.setIpAddr2(split_data_str[1]);
                                    clsSharedVariables.setPortNo2(Integer.parseInt(split_data_str[2]));
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();
                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETIP2#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETIP2");
                        } else if (data.trim().contains("SETVEHREG")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                try {
                                    clsSharedVariables.setVechicleRegNo(split_data_str[1]);
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();
                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETVEHREG#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETVEHREG");
                        } else if (data.trim().contains("SETIGNCFG")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                try {
                                    clsSharedVariables.set_gprs_sleep_val(Short.parseShort(split_data_str[1]));
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();
                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETIGNCFG#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETIGNCFG");
                        } else if (data.trim().contains("SETEMRCFG")) {
                            //  ////////System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                try {
                                    clsSharedVariables.setEmergencyStateTimeDuration(Byte.parseByte(split_data_str[1]));
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();
                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETEMRCFG#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETEMRCFG");
                        } else if (data.trim().contains("SETNORMALTIME")) {
                            //  System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                try {
                                    clsSharedVariables.set_gprs_normal_mode_val(Byte.parseByte(split_data_str[1]));
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();

                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETNORMALTIME#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "$SETNORMALTIME");
                            //objRes1.send_common_ack_server_on_cellular(ACK_SUCCESS, clsPacketTypes16833.CS_OBU_PKT_IGN_ON_OFF_DURATION);
                        } else if (data.trim().contains("SETIGNDELAYCFG")) {
                            //  System.out.println(data);
                            data = data.replace("#", "");
                            split_data_str = data.split(",");
                            if (split_data_str.length >= 2) {
                                try {
                                    clsSharedVariables.setMainDeviceShutTime(Byte.parseByte(split_data_str[1]));
                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    objReadFiles.write_cfg_data_file();

                                    objReadFiles = null;
                                } catch (Exception ex) {

                                }
                            }
                            smsQueue.addData("$SETIGNDELAYCFG#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETIGNDELAYCFG");
                        } else if (data.trim().contains("SETDEVRST")) {
                            smsQueue.addData("$SETDEVRST#");
                            setSmsDataToSend();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "SETDEVRST");
                            reboot_system(" SMS SETDEVRST");
                        } else if (data.trim().contains("GETAPN")) {
                            //   ////////System.out.println(data);
                            clsSharedVariables.getApn();
                            smsQueue.addData("$APN," + clsSharedVariables.getApn() + "#");
                            setSmsDataToSend();
                            objRes1.send_apn_address(false);
                        } else if (data.trim().contains("GETOSLIMIT")) {
                            clsSharedVariables.getApn();
                            smsQueue.addData("$GETOSLIMIT," + gpsDriving.over_speed_limit + "#");
                            setSmsDataToSend();
                            objRes1.send_overspeedlimit(false);
                        } else if (data.trim().contains("GETHA")) {
                            clsSharedVariables.getApn();
                            smsQueue.addData("$GETHA," + gpsDriving.harsh_acc_threshold + "#");
                            setSmsDataToSend();
                            objRes1.send_harshacc(false);
                        } else if (data.trim().contains("GETHB")) {
                            clsSharedVariables.getApn();
                            smsQueue.addData("$GETHB," + gpsDriving.harsh_brk_threshold + "#");
                            setSmsDataToSend();
                            objRes1.send_harshbreak(false);
                        } else if (data.trim().contains("GETRT")) {
                            clsSharedVariables.getGyroAngle();
                            smsQueue.addData("$GETRT," + clsSharedVariables.getGyroAngle() + "#");
                            setSmsDataToSend();
                            objRes1.send_rashturn(false);
                        } else if (data.trim().contains("GETIP1")) {
                            //  ////////System.out.println(data);
                            smsQueue.addData("$IP1," + clsSharedVariables.getIpAddr1() + "," + clsSharedVariables.getPortNo1() + "#");
                            setSmsDataToSend();
                            objRes1.send_primary_ipaddress(false);
                        } else if (data.trim().contains("GETIP2")) {
                            //  ////////System.out.println(data);
                            objRes1.send_secondary_ipaddress(false);
                            smsQueue.addData("$IP2," + clsSharedVariables.getIpAddr2() + "," + clsSharedVariables.getPortNo2() + "#");
                            setSmsDataToSend();
                        } else if (data.trim().contains("GETVEHREG")) {
                            //  ////////System.out.println(data);
                            smsQueue.addData("$VEHREG," + clsSharedVariables.getVechicleRegNo() + "#");
                            setSmsDataToSend();
                            objRes1.send_vehicle_regno(false);
                        } else if (data.trim().contains("GETIGNCFG")) {
                            smsQueue.addData("$IGNCFG," + clsSharedVariables.get_gprs_sleep_val() + "#");
                            setSmsDataToSend();
                            objRes1.send_ignition_mode(false);
                        } else if (data.trim().contains("GETEMRCFG")) {
                            smsQueue.addData("$EMRCFG," + clsSharedVariables.getEmergencyStateTimeDuration() + "#");
                            setSmsDataToSend();
                            objRes1.send_ignition_mode(false);
                        } else if (data.trim().contains("GETIMEINO")) {
                            smsQueue.addData("$IMEINO," + clsSharedVariables.getImeiNo() + "#");
                            setSmsDataToSend();
                            objRes1.send_imeino(false);
                        } else if (data.trim().contains("GETPHNUM")) {
                            smsQueue.addData("$PHNUM," + clsSharedVariables.getSmsPhoneNo() + "#");
                            setSmsDataToSend();
                            objRes1.send_ignition_mode(false);
                        } else if (data.trim().contains("GETNORMALTIME")) {
                            smsQueue.addData("$NORMALTIME," + clsSharedVariables.get_gprs_normal_mode_val() + "#");
                            setSmsDataToSend();
                            objRes1.send_ignition_mode(false);
                        } else if (data.trim().contains("GETIGNDELAYCFG")) {
                            smsQueue.addData("$IGNDDELAYCCFG," + clsSharedVariables.getMainDeviceShutTime() + "#");
                            setSmsDataToSend();
                            objRes1.send_ignition_mode(false);
                        } else if (data.trim().contains("CLRALMSPEED")) {
                            clsSharedVariables.setOverSpeedBuzzerEnabled(false);
                            clsReadFiles objReadFiles = new clsReadFiles();
                            objReadFiles.write_cfg_data_file();
                            objRes1.trackingMessagePkt(clsPacketTypes.ALERT_OVER_AIR_PKT, 'L', false, "CLRALMSPEED");
                            //objRes1.send_common_ack_server_on_cellular(ACK_SUCCESS, clsPacketTypes16833.CS_OBU_PKT_SET_OVERSPEED_BUZZER);
                            objReadFiles = null;
                        } else if (data.trim().contains("GETEMRSMS")) {
                            smsQueue.addData(objRes1.emergency_alert_sms_message_pkt(EMERGENCY_MESSAGE_TYPE, "NM"));
                            objRes1 = null;
                            setSmsDataToSend();
                        }
                    }
                } catch (InterruptedException | NumberFormatException | SerialPortException ex) {
                } finally {
                    smsQueue = null;
                    objRes1 = null;
                }
            }
        }

        private void close_call_end_gpio_controls() {
            try {
                if (alertEndCall != null) {
                    alertEndCall.removeAll();
                    alertEndCall = null;
                    EngCallDialog.setVisible(false);
                    EngCallDialog.dispose();
                }
            } catch (Exception ex) {

            }

            set_voice_call_enabled_status(false);

            write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);

            audio_speaker_off();
            // write_gpio_pin_state(MIC_CONTROL_CALL_SPEAKER, MIC_CONTROL_SPEAKER_STATE, digOutMicPin);
            set_voice_call_start_status(false);

        }

        private void open_call_gpio_controls() {

            set_voice_call_enabled_status(true);

            write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);

            //write_gpio_pin_state(MIC_CONTROL_CALL_SPEAKER, MIC_CONTROL_VOICE_CALL_STATE, digOutMicPin);
        }

    }

    public void startAtPort() {
        // Check if the communication object is not initialized
        if (objAtPort == null) {
            // Initialize the communication object and start its thread
            objAtPort = new ReadAtPortDataFromSerialPort();
            objAtPort.start();
        } else {
            try {
                // If the object is already initialized, interrupt its thread
                objAtPort.interrupt();
                // Set the object to null
                objAtPort = null;
                // Reinitialize the object and start its thread
                objAtPort = new ReadAtPortDataFromSerialPort();
                objAtPort.start();
            } catch (Exception ex) {
                // Handle any exceptions that occur
            }
        }
    }

    public void stopAtPort() {
        // Stop the thread associated with the communication object
        objAtPort.interrupt();
        // Set the communication object to null
        objAtPort = null;
    }

    //default commands which is written to at serial port on connection
    private void default_write_at_commands() {
        try {
            String str;
            str = "AT\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "ATI\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "ATE0\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+GSN\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CGSN\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);
            str = "AT+QSIMSTAT=1\r\n";

            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QSIMSTAT?\r\n";
            atSerialWrite(str);
            Thread.sleep(100);

            str = "AT+QURCCFG=\"URCPORT\",\"usbat\"\r\n";

            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CSCS=\"IRA\"\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QINDCFG=\"csq\",1,1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QINDCFG=\"all\",1,1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QCFG=\"urc/ri/ring\",\"auto\"\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QINDCFG=\"ccinfo\",1,1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            if (clsSharedVariables.getSeparateGPS()) {
                str = "AT+QGPS=0\r\n";
            } else {
                str = "AT+QGPS=1\r\n";
            }
            atSerialWrite(str);
            Thread.sleep(1000);

            {
                str = "AT+CMGF=1\r\n";
                Thread.sleep(1000);
                atSerialWrite(str);

                str = "AT+CSCS=\"IRA\"\r\n";
                Thread.sleep(1000);
                atSerialWrite(str);
                str = "AT+QINDCFG=\"ring\",1,1\r\n";
                atSerialWrite(str);
                Thread.sleep(1000);
            }

            if (clsSharedVariables.getQuecModuleRev() == 0) {
                str = "ATI\r\n";
                atSerialWrite(str);
                Thread.sleep(1000);
            }

            str = "AT+CLIP=1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CRSL=7\r\n";
            atSerialWrite(str);

            Thread.sleep(1000);
            byte vol = get_voice_call_volume();

            set_voice_call_volume((byte) vol);

            str = "AT+CLVL=" + vol + "\r\n";

            atSerialWrite(str);
            Thread.sleep(1000);
            str = "AT+QDAI=2\r\n"; //,0,0,4,0 
            atSerialWrite(str);

            Thread.sleep(1000);

            if (clsSharedVariables.getQuecModuleRev() == clsDefines.QUECTEL_MOD_EC20) {
                str = "AT+QAUDCFG=\"nau8814/dlgain\"," + get_ring_tone_volume() + "\r\n";
                atSerialWrite(str);
                Thread.sleep(1000);

                str = "AT+QAUDCFG=\"nau8814/aoutput\",1\r\n";
                atSerialWrite(str);
                Thread.sleep(1000);

                str = "AT+QAUDCFG=\"nau8814/ainput\",1\r\n";
                atSerialWrite(str);
                Thread.sleep(1000);

            }

            str = "AT+QAUDMOD=2\r\n"; //str = "AT+QAUDMOD=2\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+COLP=1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CCWA=1,1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+GSN\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = " AT+CGREG=2\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = " AT+CREG=2\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QGPSCFG=\"autogps\",1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+SIDET=1310\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+COPS?\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CTZU=1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CTZR=2\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QCCID\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+CNUM\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QCFG=\"tone/incoming\",1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QSIMSTAT=1\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            str = "AT+QURCCFG=\"URCPORT\",\"usbat\"\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);

            if (clsSharedVariables.getEmbNorSimType() == clsDefines.EMBEDDED_SIM) {
                if (clsSharedVariables.getGsmModuleOnPowerOn() == false) {

                    if (clsSharedVariables.getEmbeddedSimName() == clsDefines.SENSORIZE_SIM) {
                        str = "AT+QSTK=1,1,300\r\n";
                        atSerialWrite(str);
                        Thread.sleep(1000);

                        str = "AT+CRSM=214,28539,0,0,12,\"FFFFFFFFFFFFFFFFFFFFFFFF\"\r\n";
                        atSerialWrite(str);
                        Thread.sleep(5000);

                        str = "AT+CRSM=214,28539,0,0,11,\"FFFFFFFFFFFFFFFFFFFFFFFF\"\r\n";
                        atSerialWrite(str);
                        Thread.sleep(5000);

                        embeddedsim_profile_switch_setup();
                        if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_MANUAL_MODE) {
                            convert_sim_change(false); //SECONDARY mode airtel
                        } else if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_FIXED_MODE) {
                            if (clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_PRIMARY_MODE) {
                                convert_sim_change(true);
                            } else {
                                convert_sim_change(false);
                            }
                        }
                    } else if (clsSharedVariables.getEmbeddedSimName() == clsDefines.INTALIA_SIM) {
                        str = "AT+QSTK=1\r\n";
                        atSerialWrite(str);
                        Thread.sleep(1000);
                        if (clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_PRIMARY_MODE) {
                            embeddedsim_profile_switch_setup_intalia((byte) 0);
                        } else //if ((clsSharedVariables.getEmbSimFixedSwitchMode()) == clsDefines.SWITCH_SECONDARY_MODE) {
                        {
                            //////System.out.println("secondary");
                            embeddedsim_profile_switch_setup_intalia((byte) 1);
                        }

                    }
                }
            } else {
                str = "AT+QSTK=0\r\n";
                atSerialWrite(str);
                Thread.sleep(1000);
            }
            atSerialWrite("AT+QNWINFO\r\n");
            Thread.sleep(1000);

            atSerialWrite("AT+QENG=\"servingcell\"\r\n");
            Thread.sleep(1000);

            atSerialWrite("AT+QENG=\"neighbourcell\"\r\n");
            Thread.sleep(1000);

            str = "AT&W\r\n";
            atSerialWrite(str);
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            //Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {

        }

    }

    private int getSignalStrength(String str) {
        // Split the input string by commas.
        String[] split_str = str.split(",");
        int sig_str = 0;

        try {
            // Attempt to parse the second element of the array as an integer.
            sig_str = Integer.parseInt(split_str[1]);

            // If the signal strength is not equal to 99, process it further.
            if (sig_str != 99) {
                // Set the GSM signal strength.
                setGsmSignalStrength(sig_str);

                // Check various ranges of signal strength and update the signal strength icon accordingly.
                if (sig_str < 8) {
                    pre_sig_str = (byte) sig_str;
                    try {
                        // Use SwingUtilities.invokeLater to update the icon on the Event Dispatch Thread.
                        SwingUtilities.invokeLater(() -> {
                            updateSignalStrengthDetectIcon((byte) 0);
                        });
                    } catch (Exception ex) {
                        // Catch any exception that occurs during the icon update.
                    }
                } else if (sig_str < 8) { // This condition is redundant since it repeats the previous check.
                    pre_sig_str = (byte) sig_str;
                    try {
                        SwingUtilities.invokeLater(() -> {
                            updateSignalStrengthDetectIcon((byte) 1);
                        });
                    } catch (Exception ex) {
                        // Catch any exception that occurs during the icon update.
                    }
                } else if (sig_str < 12) {
                    pre_sig_str = (byte) sig_str;
                    try {
                        SwingUtilities.invokeLater(() -> {
                            updateSignalStrengthDetectIcon((byte) 2);
                        });
                    } catch (Exception ex) {
                        // Catch any exception that occurs during the icon update.
                    }
                } else if (sig_str < 20) {
                    pre_sig_str = (byte) sig_str;
                    try {
                        SwingUtilities.invokeLater(() -> {
                            updateSignalStrengthDetectIcon((byte) 3);
                        });
                    } catch (Exception ex) {
                        // Catch any exception that occurs during the icon update.
                    }
                } else if (sig_str < 25) {
                    pre_sig_str = (byte) sig_str;
                    try {
                        SwingUtilities.invokeLater(() -> {
                            updateSignalStrengthDetectIcon((byte) 4);
                        });
                    } catch (Exception ex) {
                        // Catch any exception that occurs during the icon update.
                    }
                } else if (sig_str < 35) {
                    pre_sig_str = (byte) sig_str;
                    try {
                        SwingUtilities.invokeLater(() -> {
                            updateSignalStrengthDetectIcon((byte) 5);
                        });
                    } catch (Exception ex) {
                        // Catch any exception that occurs during the icon update.
                    }
                }
            } else {
                // If the signal strength is 99, update the icon to indicate no signal.
                try {
                    SwingUtilities.invokeLater(() -> {
                        updateSignalStrengthDetectIcon((byte) 0);
                    });
                } catch (Exception ex) {
                    // Catch any exception that occurs during the icon update.
                }
            }
        } catch (NumberFormatException ex) {
            // Catch any exception that occurs during the parsing of the signal strength.
        } finally {
            // The finally block is empty and could be omitted.
        }

        // Return the signal strength value.
        return sig_str;
    }

}
