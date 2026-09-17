package obuits;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sumitha
 */
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Calendar;

// Import static variables from clsDefines and clsSharedVariables
import static obuits.clsDefines.SERVER_CONNECTED;
import static obuits.clsDefines.SERVER_DISCONNECTED;
import static obuits.clsDefines.Strings.RESET_OBU_CONFIRMED;
import static obuits.clsDefines.Strings.RESET_OBU_FINISHED;

import static obuits.clsSharedVariables.getGsmSignalStrength;
import static obuits.clsSharedVariables.getIpAddr1;
import static obuits.clsSharedVariables.getPortNo1;
import static obuits.clsSharedVariables.getResetObuVal;
import static obuits.clsSharedVariables.getServerConfigStatus;
import static obuits.clsSharedVariables.setGpsPktCamInGprs;
import static obuits.clsSharedVariables.setResetObuVal;
import static obuits.clsSharedVariables.setServerConfigStatus;
import static obuits.clsSharedVariables.setServerConnectImg;

import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.image.ImageView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import jssc.SerialPortList;
//import static obuits.MainFrmIts.ObjTextSpeech;
import static obuits.MainFrmIts.audio_speaker_off;
import static obuits.MainFrmIts.audio_speaker_on;
import static obuits.MainFrmIts.imgMobile;

import static obuits.MainFrmIts.reboot_system;
import static obuits.MainFrmIts.restart_module_ports_changed;
import static obuits.PanLogin.driver_login_send;
import static obuits.PanLogin.set_login_status;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.GPRS_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.HISTORY_PACKET_VALUE;
import static obuits.clsDefines.OBU_BRD_TYPE;
import static obuits.clsDefines.RASPBERRY_BOARD;
import static obuits.clsDefines.SCHDEULE_ENABLE;
import static obuits.clsDefines.Strings.GPRS_CONNECTED;
import static obuits.clsDefines.Strings.GPRS_DISCONNECTED;
import static obuits.clsDefines.Strings.GPRS_FINALLY_CONNECTED;
import static obuits.clsDefines.Strings.GPRS_PKT_ACK_SUCCESS;
import static obuits.clsDefines.Strings.SUMITH_DISBRD;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsDefines.WIFI_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.config_filepath;
import static obuits.clsDefines.extra_video_filepath;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.obu_filepath;
import static obuits.clsDefines.obu_images_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDefines.snap_filepath;
import static obuits.clsDefines.video_filepath;
import static obuits.clsInternalDisBrdMessage.fill_internal_details;
import static obuits.clsSharedVariables.ftp_port_no;
import static obuits.clsSharedVariables.ftpPassword;
import static obuits.clsSharedVariables.ftp_ipaddress;
import static obuits.clsSharedVariables.ftpusername;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getDisBrdName;
import static obuits.clsSharedVariables.getGsmModuleOn;
import static obuits.clsSharedVariables.getIpAddr2;
import static obuits.clsSharedVariables.getIpAddr3;
import static obuits.clsSharedVariables.getIpAddr4;
import static obuits.clsSharedVariables.getIpAddr5;
import static obuits.clsSharedVariables.getPortNo2;
import static obuits.clsSharedVariables.getWifiConnect;
import static obuits.clsSharedVariables.set_panic_data_sent_server;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
//import static org.apache.commons.net.ftp.FTPFile.FILE_TYPE;
import static obuits.clsSharedVariables.getNoRoutes;
import static obuits.clsSharedVariables.getPortNo3;
import static obuits.clsSharedVariables.getPortNo4;
import static obuits.clsSharedVariables.getPortNo5;
import static obuits.clsSharedVariables.getServer2ConfigStatus;
import static obuits.clsSharedVariables.getServer3ConfigStatus;
import static obuits.clsSharedVariables.getServer4ConfigStatus;
import static obuits.clsSharedVariables.getServer5ConfigStatus;
import static obuits.clsSharedVariables.getSimReady;
import static obuits.clsSharedVariables.getSimRegistered;
import static obuits.clsSharedVariables.get_voice_call_enabled_status;
import static obuits.clsSharedVariables.objRouteMasFiles;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSharedVariables.setCurSchEndDate;
import static obuits.clsSharedVariables.setCurSchEndTime;
import static obuits.clsSharedVariables.setCurSchNoTrips;
import static obuits.clsSharedVariables.setCurSchRouteNo;
import static obuits.clsSharedVariables.setCurSchStartDate;
import static obuits.clsSharedVariables.setCurSchStartTime;
import static obuits.clsSharedVariables.setCurSchTripStatus;
import static obuits.clsSharedVariables.setCurTripNo;
import static obuits.clsSharedVariables.setCurTripStat;
import static obuits.clsSharedVariables.setEmergencyMapPktCame;
import static obuits.clsSharedVariables.setGprsRouteEndPktCame;
import static obuits.clsSharedVariables.setGprsRouteStartPktCame;
import static obuits.clsSharedVariables.setSchId;
import static obuits.clsSharedVariables.setServer2ConfigStatus;
import static obuits.clsSharedVariables.setServer3ConfigStatus;
import static obuits.clsSharedVariables.setServer4ConfigStatus;
import static obuits.clsSharedVariables.setServer5ConfigStatus;
import static obuits.clsSharedVariables.set_gprs_res_ackpkt;
import static obuits.clsSharedVariables.set_gprs_res_ackpkt_status;
import static obuits.clsSharedVariables.set_gprs_res_pkt_ackmsg_type;
import org.apache.commons.net.ftp.FTPFile;
import static org.apache.commons.net.ftp.FTPFile.FILE_TYPE;
import static obuits.clsSharedVariables.setServerConnectImg2;
import static obuits.clsSharedVariables.setServerConnectImg3;
import static obuits.clsSharedVariables.setServerConnectImg4;
import static obuits.clsSharedVariables.setServerConnectImg5;

public class clsGprsService {

    Process gprsScriptprocess = null;

    static GsmStateMachine objGsmStateMachine = null;
    static GsmStateMachine2 objGsmStateMachine2 = null;
   

    static clsPppExecuteThread.OutStreamPPPThread objThr = null;
    static clsPppExecuteThread objPppThread = null;

    // JLabels for GUI elements
    JLabel imgServerConn;
    JLabel imgMobile;
    JLabel imgWifi;
    JLabel imgServerConn2;
    JLabel imgServerConn3;
    JLabel imgServerConn4;
    JLabel imgServerConn5;

    // Constants
    final byte MAX_NO_PKT_STORED = 50;
    private static String PPP_TRANSMIT_DATA_PATH = "/sys/class/net/wwan0/statistics/tx_bytes";
    private static String WLAN0_TRANSMIT_DATA_PATH = "/sys/class/net/wlan0/statistics/tx_bytes";
    private static String PPP_CHECK_PATH = "/sys/class/net/wwan0";
    private static final String WIFI_CHECK_PATH = "/sys/class/net/wlan0/operstate";
    private static final String PPP_CHECK_PATH_OPER_STATE = "/sys/class/net/wwan0/operstate";
    private static final String PPP_CHECK_PATH_CARRIER_STATE = "/sys/class/net/wwan0/carrier";
    public static final String GET_NETWORK_UUID_PATH = "/etc/NetworkManager/system-connections/";

    // Constructor
    clsGprsService(JLabel imgSer, JLabel imgMob, JLabel imgWi, JLabel imgSer2, JLabel imgSer3, JLabel imgSer4, JLabel imgSer5) {
        // Initialize JLabels
        imgServerConn = imgSer;
        imgMobile = imgMob;
        imgWifi = imgWi;
        imgServerConn2 = imgSer2;
        imgServerConn3 = imgSer3;
        imgServerConn4 = imgSer4;
        imgServerConn5 = imgSer5;

        // Set network interface path based on OBU_BRD_TYPE
        if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
            clsDefines.GPRS_NETWORKINTERFACE_PATH = "wwan0";
        } else if (OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
            clsDefines.GPRS_NETWORKINTERFACE_PATH = "ppp0";
        } else if (OBU_BRD_TYPE == clsDefines.BANANAPI_BOARD) {
            clsDefines.GPRS_NETWORKINTERFACE_PATH = "ppp0";
        }

        // Set transmit data path based on NETWORKMANAGER_GPRS flag
        if (clsDefines.NETWORKMANAGER_GPRS == true) {
            PPP_TRANSMIT_DATA_PATH = "/sys/class/net/wwan0/statistics/tx_bytes";
        } else {
            PPP_TRANSMIT_DATA_PATH = "/sys/class/net/ppp0/statistics/tx_bytes";
        }

        // Update all images on GUI
        update_all_images();

        // Start GSM state machine
        start_gsm_statemachine();
    }

    // Method to update all images based on image file availability
    private void update_all_images() {
        if (clsDefines.obu_images_filepath.exists() == true) {
            imgWifi.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/wifi-signal.png"));
            imgWifi.repaint();
            imgMobile.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/tower.png"));
            imgMobile.repaint();
            imgServerConn.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
            imgServerConn.repaint();
            imgServerConn2.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
            imgServerConn2.repaint();
            imgServerConn3.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
            imgServerConn3.repaint();
            imgServerConn4.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
            imgServerConn4.repaint();
            imgServerConn5.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
            imgServerConn5.repaint();
        } else {
            imgWifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wifi-signal.png")));
            imgWifi.repaint();
            imgMobile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tower.png")));
            imgMobile.repaint();
            imgServerConn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
            imgServerConn.repaint();
            imgServerConn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
            imgServerConn2.repaint();
            imgServerConn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
            imgServerConn3.repaint();
            imgServerConn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
            imgServerConn4.repaint();
            imgServerConn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
            imgServerConn5.repaint();
        }
    }

    // Method to update Wi-Fi icon based on state
    private synchronized void update_wifi_icon(boolean state) {
        if (state == true) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgWifi.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/wifi-signal.png"));
                imgWifi.repaint();
            } else {
                imgWifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wifi-signal.png")));
                imgWifi.repaint();
            }
            imgWifi.setVisible(true);
        } else {
            imgWifi.setVisible(false);
        }
    }

    // Method to update Mobile icon based on state
    private synchronized void update_mobile_icon(boolean state) {
        if (state == true) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgMobile.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/tower.png"));
                imgMobile.repaint();
            } else {
                imgMobile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tower.png")));
                imgMobile.repaint();
            }
            imgMobile.setVisible(true);
        } else {
            imgMobile.setVisible(false);
        }
    }

    // Updates the icon for server connection 1 based on the state parameter
    private synchronized void update_servercon1_icon(boolean state) {
        if (state == true) {
            // Check if custom image path exists, otherwise use default path
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgServerConn.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
                imgServerConn.repaint();
            } else {
                imgServerConn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
                imgServerConn.repaint();
            }
            imgServerConn.setVisible(true);
        } else {
            imgServerConn.setVisible(false);
        }
    }

// Updates the icon for server connection 2 based on the state parameter
    private synchronized void update_servercon2_icon(boolean state) {
        if (state == true) {
            // Check if custom image path exists, otherwise use default path
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgServerConn2.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
                imgServerConn2.repaint();
            } else {
                imgServerConn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
                imgServerConn2.repaint();
            }
            imgServerConn2.setVisible(true);
        } else {
            imgServerConn2.setVisible(false);
        }
    }

// Updates the icon for server connection 3 based on the state parameter
    private synchronized void update_servercon3_icon(boolean state) {
        if (state == true) {
            // Check if custom image path exists, otherwise use default path
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgServerConn3.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
                imgServerConn3.repaint();
            } else {
                imgServerConn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
                imgServerConn3.repaint();
            }
            imgServerConn3.setVisible(true);
        } else {
            imgServerConn3.setVisible(false);
        }
    }

// Updates the icon for server connection 4 based on the state parameter
    private synchronized void update_servercon4_icon(boolean state) {
        if (state == true) {
            // Check if custom image path exists, otherwise use default path
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgServerConn4.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
                imgServerConn4.repaint();
            } else {
                imgServerConn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
                imgServerConn4.repaint();
            }
            imgServerConn4.setVisible(true);
        } else {
            imgServerConn4.setVisible(false);
        }
    }

// Updates the icon for server connection 5 based on the state parameter
    private synchronized void update_servercon5_icon(boolean state) {
        if (state == true) {
            // Check if custom image path exists, otherwise use default path
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgServerConn5.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/network.png"));
                imgServerConn5.repaint();
            } else {
                imgServerConn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png")));
                imgServerConn5.repaint();
            }
            imgServerConn5.setVisible(true);
        } else {
            imgServerConn5.setVisible(false);
        }
    }

// Starts GSM state machines based on configuration flags
    private void start_gsm_statemachine() {
        if (objPppThread == null) {
            // Start PPP execution thread if not already running
            objPppThread = new clsPppExecuteThread();
            objPppThread.start();
        } else {
            // Restart PPP execution thread if already running
            objPppThread.interrupt();
            objPppThread = null;
            objPppThread = new clsPppExecuteThread();
            objPppThread.start();
        }

        // Start GSM state machines based on enabled IP addresses
        if (clsSharedVariables.getIpAddr1Enable()) {
            if (objGsmStateMachine == null) {
                objGsmStateMachine = new GsmStateMachine();
                objGsmStateMachine.start();
            } else {
                objGsmStateMachine.interrupt();
                objGsmStateMachine = null;
                objGsmStateMachine = new GsmStateMachine();
                objGsmStateMachine.start();
            }
        }

        if (clsSharedVariables.getIpAddr2Enable()) {
            if (objGsmStateMachine2 == null) {
                objGsmStateMachine2 = new GsmStateMachine2();
                objGsmStateMachine2.start();
            } else {
                objGsmStateMachine2.interrupt();
                objGsmStateMachine2 = null;
                objGsmStateMachine2 = new GsmStateMachine2();
                objGsmStateMachine2.start();
            }
        }

       

        
    }

    class ExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // Create an instance of clsReadFiles to handle logging
            clsReadFiles objReadFiles = new clsReadFiles();

            // Log the exception information
            objReadFiles.write_log_gprs_connectivity(" clsGprsService UnCaughtExceptionHandler  " + e.getClass().getName() + e.getMessage() + " Restarting clsGprsService Again ");

            // Clean up the objReadFiles instance
            objReadFiles = null;

            // Attempt to restart the clsGprsService and GSM state machine
            // Create a new instance of clsGprsService with provided JLabels
            clsGprsService objGprs = new clsGprsService(imgServerConn, imgMobile, imgWifi, imgServerConn2, imgServerConn3, imgServerConn4, imgServerConn5);

            // Create a new instance of GsmStateMachine and start it
            objGsmStateMachine = new GsmStateMachine();
            objGsmStateMachine.start();
        }
    }

    class GsmStateMachine extends Thread {

        public GsmStateMachine() {
            super();
        }

        // Declaring necessary variables and objects
        clsGpsLinkedList objGpsLnkLst = new clsGpsLinkedList();
        byte[] readDataBuf = new byte[5000];
        final clsLinkedList objLinkList = new clsLinkedList();

        Socket socket = null;
        DataOutputStream CsWrite = null;
        DataInputStream CsRead = null;
        clsReadFiles objReadFiles = new clsReadFiles();
        boolean socket_connected = false;
        Thread objGsmReadThread = null;
        private String sch_req_data;
        StringBuilder sbGprsData = new StringBuilder(5000);
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        cls16833Protocols obj16833 = new cls16833Protocols();
        clsEmergencyPktQueue objEmer = new clsEmergencyPktQueue();
        clsPktResponseQueue objResponseQue = new clsPktResponseQueue();

        @Override
        public void run() {
            // Setting an uncaught exception handler
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
            final int timeoutInMs = 30000; // Timeout for socket connection
            int inc;
            int inc1;
            setGpsPktCamInGprs(0);

            String data_loc;
            String[] split_str_loc;

            // Logging the start of GPRS network connectivity
            objReadFiles.write_log_gprs_connectivity("Start of GPRS Network ");

            while (true) {
                try {
                    // Writing data on reset to file
                    write_data_on_reset_to_file_ip1();

                    if (!clsSharedVariables.getIpAddr1Enable()) {
                        socket_connected = false;
                        Thread.sleep(10000); // Wait if IP address is not enabled
                    } else {
                        if (getServerConfigStatus() == true) {
                            setServerConfigStatus(false);
                            closeSocketCommunication();
                            socket_connected = false;
                            objReadFiles.write_log_gprs_connectivity("Network Disconnect: Server Configuration Changed");
                        }
                        if (socket_connected == false) {
                            setServerConnectImg(SERVER_DISCONNECTED);

                            try {
                                // Establishing a socket connection
                                socket = new java.net.Socket();
                                objReadFiles.write_log_gprs_connectivity("GPRS Network Connecting Single IP Interface " + getIpAddr1() + " " + getPortNo1());
                                socket.connect(new InetSocketAddress(getIpAddr1(), getPortNo1()), timeoutInMs);
                                objReadFiles.write_log_gprs_connectivity("Connected to Server Signal strength");

                                CsWrite = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                                CsRead = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                                socket_connected = true;
                                objReadFiles.read_stored_non_gps_pkts();

                                sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                                sbGprsData.append(obj16833.serverConnectedLoginMsgPkt());
                                socket_connected = write_serial_port_data(sbGprsData.toString()); // Send login packet to server

                                if (socket_connected == true) {
                                    //schedule packet
                                    if (clsSharedVariables.getSchRouteEnable() == SCHDEULE_ENABLE) {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                            readDataBuf = obj16833Pkts.schedule_request_pkt();
                                            obj16833Pkts = null;
                                            write_serial_port_data(readDataBuf);
                                        }
                                    }

                                    // Starting a thread to read data
                                    GprsDataRead16833 objGsmRead = new GprsDataRead16833();
                                    objGsmReadThread = new Thread((Runnable) objGsmRead);
                                    objGsmReadThread.start();
                                }

                            } catch (UnknownHostException e) {
                                closeSocketCommunication();
                                socket_connected = false;
                                socket = null;
                                objReadFiles.write_log_gprs_connectivity("Network Disconnect Unknown Host: Signal Strength");
                            } catch (SocketException e) {
                                socket_connected = false;
                                objReadFiles.write_log_gprs_connectivity("Network Disconnect Socket Exception " + e.getMessage());
                                socket = null;
                                closeSocketCommunication();
                            } catch (IOException e) {
                                objReadFiles.write_log_gprs_connectivity("Network Disconnect IO Exception " + e.getMessage());
                                closeSocketCommunication();
                            } catch (Exception e) {
                                objReadFiles.write_log_gprs_connectivity("Network Disconnect exc1: " + e.getMessage());
                            }
                        }
                        if (socket_connected == true) {
                            setServerConnectImg(SERVER_CONNECTED);
                            try {
                                // Updating UI icons
                                SwingUtilities.invokeLater(() -> {
                                    try {
                                        if (clsSharedVariables.getWifiConnect() == false) {
                                            update_mobile_icon(true);
                                        }
                                        update_servercon1_icon(true);
                                    } catch (Exception ex) {
                                    }
                                });
                            } catch (Exception ex) {
                            }

                            // Handling emergency packets
                            if (objEmer.getLength() > 0) {
                                readDataBuf = objEmer.removeEmergencyPkt();
                                if (write_serial_port_data(readDataBuf) == false) {
                                    objEmer.addEmergencyPkt(readDataBuf);
                                }
                            }

                            // Handling response packets for server commands
                            if (objResponseQue.getLength() > 0) {
                                readDataBuf = objResponseQue.removeData();
                                if (write_serial_port_data(readDataBuf) == false) {
                                    objResponseQue.addData(readDataBuf);
                                }
                            }

                            /**
                             * **************** GPS location packet
                             * *************************
                             */
                            for (inc = 0; inc < objGpsLnkLst.getLength() && inc < 10; inc++) {
                                readDataBuf = (byte[]) objGpsLnkLst.GetLastData();
                                if (write_serial_port_data(readDataBuf) == false) {
                                    try {
                                        sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                                        for (inc1 = 0; inc1 < readDataBuf.length; inc1++) {
                                            sbGprsData.append((char) (readDataBuf[inc1]));
                                        }
                                        data_loc = sbGprsData.toString();
                                        if (data_loc.startsWith("$LOC") || data_loc.startsWith("LOC")) {
                                            split_str_loc = data_loc.split(",");
                                            if (split_str_loc.length > 8) {
                                                split_str_loc[4] = "2";
                                                split_str_loc[HISTORY_PACKET_VALUE] = "H";
                                            }
                                            sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                                            for (inc1 = 0; inc1 < split_str_loc.length; inc1++) {
                                                sbGprsData.append(split_str_loc[inc1]);
                                                if (inc1 < split_str_loc.length - 1) {
                                                    sbGprsData.append(",");
                                                }
                                            }
                                            readDataBuf = sbGprsData.toString().getBytes();
                                        }
                                    } catch (Exception ex) {
                                    }
                                    objGpsLnkLst.addFirstData(readDataBuf);
                                    break;
                                }
                            }
                            /**
                             * **************** END of GPS location packet
                             * *************************
                             */

                            /**
                             * **************** Start NON_NMEA request
                             * *************************
                             */
                            for (inc = 0; inc < objLinkList.getLength() && inc < 10; inc++) {
                                sch_req_data = objLinkList.GetLastData();
                                if (!sch_req_data.trim().equals("")) {
                                    try {
                                        readDataBuf = sch_req_data.getBytes();
                                        if (write_serial_port_data(readDataBuf) == false) {
                                            objLinkList.addLastData(sch_req_data);
                                            break;
                                        }
                                        set_panic_data_sent_server(true);
                                    } catch (ClassCastException ex) {
                                    } catch (Exception ex) {
                                    }
                                    sch_req_data = "";
                                }
                            }
                            /**
                             * **************** END NON_NMEA DATA
                             * *************************
                             */
                        }
                    }
                    if (socket_connected == true) {
                        read_data_from_files();
                    } else {
                        // Writing data to file if network is disconnected
                        write_data_tofile_if_network_disconnected();
                    }

                } catch (InterruptedException ex) {
                    // Handle interruption
                } catch (Exception ex) {
                    objReadFiles.write_log_gprs_connectivity("Exception objGpsLnkLst " + ex.getMessage());
                }
                try {
                    Thread.sleep(100); // Sleep for a short duration before next iteration
                } catch (InterruptedException ex) {
                }
            }
        }

        private void read_data_from_files() {
            if (objGpsLnkLst.getLength() < 5) {
                // Read GPS data from file if list length is less than 5
                if (objReadFiles.read_gps_stored_data_from_file_16833() > 0) {
                    objReadFiles.write_log_gprs_connectivity("Read Data from Stored File ");
                }
            }

            if (objLinkList.getLength() < 5) { // Read non-GPS packets if list length is less than 5
                if (objReadFiles.read_stored_non_gps_pkts() > 0) {
                    objReadFiles.write_log_gprs_connectivity(" Read Data from Stored File for non GPS packets ");
                }
            }
        }

        private void write_data_tofile_if_network_disconnected() {
            int inc;
            int inc1;
            int len;
            String data_loc;
            String[] split_str_loc;
            boolean found = false;
            try {
                if (objGpsLnkLst.getLength() > MAX_NO_PKT_STORED) {
                    len = objGpsLnkLst.getLength() - 10;
                    for (inc = 0; inc < len; inc++) {
                        //store data in file
                        readDataBuf = (byte[]) objGpsLnkLst.GetLastData();
                        sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                        for (inc1 = 0; inc1 < readDataBuf.length; inc1++) {
                            sbGprsData.append((char) (readDataBuf[inc1]));
                        }
                        data_loc = sbGprsData.toString();
                        if (data_loc.startsWith("$LOC") || data_loc.startsWith("LOC")) {
                            split_str_loc = data_loc.split(",");
                            if (split_str_loc.length > 8) {
                                split_str_loc[4] = "2";
                                split_str_loc[HISTORY_PACKET_VALUE] = "H";
                            }
                            sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                            for (inc1 = 0; inc1 < split_str_loc.length; inc1++) {
                                sbGprsData.append(split_str_loc[inc1]);
                                if (inc1 < split_str_loc.length - 1) {
                                    sbGprsData.append(",");
                                }
                            }
                            objReadFiles.write_gps_stored_data_to_file_16833(sbGprsData.toString());
                        }

                    }
                    found = true;
                }
                //can stored data
                if (objLinkList.getLength() > 0) {
                    if (objReadFiles.write_all_stored_non_gps_pkts() == true) {
                        found = true;
                    }

                }
            } catch (Exception ex) {
            } finally {
                data_loc = null;
                split_str_loc = null;
                if (found == true) {
                    runCmd("sync");
                }
            }

        }

        private void write_data_on_reset_to_file_ip1() {
            int inc;
            int inc1;
            int len;
            String data_loc;
            String[] split_str_loc;
            try {
                if (getResetObuVal() == RESET_OBU_CONFIRMED) {
                    //store data in file as got message to reset the system.
                    len = objGpsLnkLst.getLength();
                    for (inc = 0; inc < len; inc++) {
                        if (objGpsLnkLst.getLength() <= 1) {
                            setResetObuVal(RESET_OBU_FINISHED);
                        }
                        try {
                            readDataBuf = (byte[]) objGpsLnkLst.GetLastData();
                            sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                            for (inc1 = 0; inc1 < readDataBuf.length; inc1++) {
                                sbGprsData.append((char) (readDataBuf[inc1]));
                            }
                            data_loc = sbGprsData.toString();
                            //////////System.out.println("data_loc  011: " + data_loc);
                            if (data_loc.startsWith("$LOC") || data_loc.startsWith("LOC")) {
                                split_str_loc = data_loc.split(",");
                                if (split_str_loc.length > 8) {
                                    split_str_loc[4] = "2";
                                    split_str_loc[HISTORY_PACKET_VALUE] = "H";
                                }
                                sbGprsData = sbGprsData.delete(0, sbGprsData.length());
                                for (inc1 = 0; inc1 < split_str_loc.length; inc1++) {
                                    sbGprsData.append(split_str_loc[inc1]);
                                    if (inc1 < split_str_loc.length - 1) {
                                        sbGprsData.append(",");
                                    }
                                }
                                objReadFiles.write_gps_stored_data_to_file_16833(sbGprsData.toString());
                            }
                        } catch (Exception ex) {
                        }
                    }
                    if (len > 5) {
                        runCmd("sync");
                    }
                    setResetObuVal(RESET_OBU_FINISHED);
                }
            } catch (Exception ex) {

            } finally {
                data_loc = null;
                split_str_loc = null;
            }
        }

        private boolean write_serial_port_data(String str) {
            long tx_bytes_previous;
            long tx_bytes_previous_wifi;
            byte retry_pkt_inc;
            objHealth.set_gprs_status((byte) 1);
            if (socket_connected == false) {
                return false;
            }
            if (str.length() < 3) {
                return true;
            }
            for (retry_pkt_inc = 0; retry_pkt_inc < 3; retry_pkt_inc++) {
                try {
                    tx_bytes_previous = read_ppp_transmit_bytes_cnt();
                    tx_bytes_previous_wifi = read_wifi_transmit_bytes_cnt();
                    CsWrite.writeBytes(str);
                    CsWrite.flush();
                    clsSharedVariables.setSmsSeqNo(0);
                    if (check_ppp_no_packets_sent(str.length(), tx_bytes_previous) == true) {
                        return true;
                    } else if (check_wifi_no_packets_sent(str.length(), tx_bytes_previous_wifi) == true) {
                        return true;
                    }
                } catch (IOException ex) {
                    break;
                }
            }
            closeSocketCommunication();
            return false;
        }

        private boolean write_serial_port_data(byte[] bytes) {
            long tx_bytes_previous;
            long tx_bytes_previous_wifi;
            byte retry_pkt_inc;
            byte inc1;
            objHealth.set_gprs_status((byte) 1);
            StringBuilder sbGprsLogData = new StringBuilder();
            if (bytes.length < 3) {
                return true;
            }
            try {
                for (retry_pkt_inc = 0; retry_pkt_inc < 3; retry_pkt_inc++) {
                    try {
                        tx_bytes_previous = read_ppp_transmit_bytes_cnt();
                        tx_bytes_previous_wifi = read_wifi_transmit_bytes_cnt();
                        CsWrite.write(bytes, 0, bytes.length);
                        CsWrite.flush();
                        clsSharedVariables.setSmsSeqNo(0);
                        if (check_ppp_no_packets_sent(bytes.length, tx_bytes_previous) == true) {
                            if (clsDefines.GPRS_LOG_DEBUG_ENABLE) {
                                sbGprsLogData = sbGprsLogData.delete(0, sbGprsLogData.length());
                                for (inc1 = 0; inc1 < readDataBuf.length; inc1++) {
                                    sbGprsLogData.append(readDataBuf[inc1]);
                                    sbGprsLogData.append(",");
                                }
                                objReadFiles.write_tx_data_from_server(sbGprsLogData.toString());
                            }
                            return true;
                        } else if (check_wifi_no_packets_sent(bytes.length, tx_bytes_previous_wifi) == true) {
                            if (clsDefines.GPRS_LOG_DEBUG_ENABLE) {
                                sbGprsLogData = sbGprsLogData.delete(0, sbGprsLogData.length());
                                for (inc1 = 0; inc1 < readDataBuf.length; inc1++) {
                                    sbGprsLogData.append(readDataBuf[inc1]);
                                    sbGprsLogData.append(",");
                                }
                                objReadFiles.write_tx_data_from_server(sbGprsLogData.toString());
                            }
                            return true;
                        }
                    } catch (IOException ex) {
                        break;
                    }
                }

                closeSocketCommunication();

            } catch (Exception ex) {
            } finally {
                sbGprsLogData = null;
            }

            return false;
        }

        private long read_ppp_transmit_bytes_cnt() {
            BufferedReader br = null;
            String line;
            File file;
            try {

                file = new File(PPP_TRANSMIT_DATA_PATH);

                if (file.exists()) {
                    file.setReadable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        return Long.parseLong(line);
                    }
                }

            } catch (FileNotFoundException e) {

            } catch (IOException | NumberFormatException e) {

            } finally {

                try {
                    if (br != null) {
                        br.close();
                    }

                    line = null;
                    br = null;
                    file = null;

                } catch (IOException e) {

                }
            }

            return 0;
        }

        private synchronized boolean check_ppp_no_packets_sent(long actual_bytes_cnt, long prev_bytes_cnt) {

            long diff;
            long cnt;
            int i;
            boolean found = false;
            try {
                for (i = 0; i < 60 && found == false; i++) {

                    cnt = read_ppp_transmit_bytes_cnt();
                    if (prev_bytes_cnt < 10) {
                        if (cnt > actual_bytes_cnt || cnt > (prev_bytes_cnt + actual_bytes_cnt) / 2) {
                            return true;
                        }
                    }

                    if (cnt > prev_bytes_cnt) {
                        diff = cnt - prev_bytes_cnt;
                        //// ////////System.out.println("cnt > prev_bytes_cnt read_ppp_transmit_bytes_cnt " + cnt + "diff "+diff);
                    } else {
                        diff = prev_bytes_cnt - cnt;
                        //// ////////System.out.println("read_ppp_transmit_bytes_cnt " + cnt + "diff "+diff);
                    }

                    if (diff >= actual_bytes_cnt && diff > 5) {
                        //objReadFiles.write_log_gprs_connectivity("true actual bytes sent :" + actual_bytes_cnt + " diff :" + diff + "cnt : " + cnt + "prev_bytes_cnt :" + prev_bytes_cnt);
                        //////////System.out.println("true actual bytes sent :" + actual_bytes_cnt + " diff :" + diff + "cnt : " + cnt+ "prev_bytes_cnt :" + prev_bytes_cnt);
                        return true;
                    }

                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                return false;
            }

            return false;
        }

        private long read_wifi_transmit_bytes_cnt() {
            BufferedReader br = null;
            String line;
            File file;
            try {

                file = new File(WLAN0_TRANSMIT_DATA_PATH);

                if (file.exists()) {
                    file.setReadable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        return Long.parseLong(line);
                    }
                }

            } catch (FileNotFoundException e) {

            } catch (IOException | NumberFormatException e) {

            } finally {

                try {
                    if (br != null) {
                        br.close();
                    }

                    line = null;
                    br = null;
                    file = null;

                } catch (IOException e) {

                }
            }

            return 0;
        }

        private synchronized boolean check_wifi_no_packets_sent(long actual_bytes_cnt, long prev_bytes_cnt) {
            long diff;
            long cnt;
            int i;
            boolean found = false;
            try {
                for (i = 0; i < 60 && found == false; i++) {
                    cnt = read_wifi_transmit_bytes_cnt();
                    if (prev_bytes_cnt < 10) {
                        if (cnt > actual_bytes_cnt || cnt > (prev_bytes_cnt + actual_bytes_cnt) / 2) {
                            return true;
                        }
                    }

                    if (cnt > prev_bytes_cnt) {
                        diff = cnt - prev_bytes_cnt;
                        //// ////////System.out.println("cnt > prev_bytes_cnt read_ppp_transmit_bytes_cnt " + cnt + "diff "+diff);
                    } else {
                        diff = prev_bytes_cnt - cnt;
                        //// ////////System.out.println("read_ppp_transmit_bytes_cnt " + cnt + "diff "+diff);
                    }
                    if (diff >= actual_bytes_cnt && diff > 5) {
                        return true;
                    }
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                return false;
            }
            return false;
        }

        private void chkExceptionMsg(String data) {

            if (data.contains("No route to host") || data.contains("Network is unreachable")) {
                try {
                    if (getWifiConnect() == false) {
                        runCmd("sudo ifconfig wlan0 down");
                        runCmd("sudo rfkill  block wifi");
                        Thread.sleep(3000);
                        runCmd("sudo rfkill  unblock ppp0");
                        Thread.sleep(3000);
                        runCmd("sudo ifconfig ppp0 up");
                        NetworkInterface nif;
                        try {
                            nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);
                            if (nif == null || !nif.isUp()) {
                                runCmd("sudo rfkill   block ppp0");
                                runCmd("sudo rfkill   unblock ppp0");
                                runCmd("sudo ifconfig ppp0 up");
                                Thread.sleep(1000);
                                if (nif == null || !nif.isUp()) {
                                    runCmd("sudo rfkill  unblock ppp0");
                                    runCmd("sudo ifconfig ppp0 up");
                                }
                            }

                        } catch (SocketException ex) {
                        }

                        objReadFiles.write_log_gprs_connectivity("Network Disconnect reachable " + data);

                    } else {

                        objReadFiles.write_log_gprs_connectivity_ip2(" Processing gprs Disconnection...");

                        runCmd("sudo rfkill  block ppp0");
                        Thread.sleep(3000);
                        runCmd("sudo rfkill  unblock wifi");
                        // runCmd("sudo rfkill  block wifi");
                        // runCmd("sudo ifconfig ppp0 down");
                        Thread.sleep(3000);
                        runCmd("sudo ifconfig wifi up");
                        NetworkInterface nif;
                        try {
                            nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);
                            if (nif == null || !nif.isUp()) {
                                runCmd("sudo rfkill block wifi");
                                runCmd("sudo rfkill unblock wifi");
                                runCmd("sudo ifconfig wlan0 up");
                                Thread.sleep(1000);
                                if (nif == null || !nif.isUp()) {
                                    runCmd("sudo rfkill  unblock wifi");
                                    runCmd("sudo ifconfig wlan0 up");
                                }
                            }

                        } catch (SocketException ex) {
                        }

                    }
                } catch (InterruptedException ex) {
                    //  Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        public synchronized void closeSocketCommunication() {
            try {
                socket_connected = false;
                setGpsPktCamInGprs(0);

                setServerConnectImg(SERVER_DISCONNECTED);
                try {
                    try {
                        SwingUtilities.invokeLater(() -> {
                            update_servercon1_icon(false);
                        });
                    } catch (Exception ex) {

                    }
                } catch (Exception ex) {

                }

                if (socket != null) {
                    try {
                        try {
                            if (objGsmReadThread != null) {
                                objGsmReadThread.interrupt();
                                try {
                                    objGsmReadThread.stop();
                                } catch (Exception ex) {

                                }
                                objGsmReadThread = null;
                                // objGsmReadThread.destroy();
                            }
                            // socket.shutdownOutput();
                        } catch (Exception ex) {

                        }
                        socket.close();

                    } catch (IOException e) {
                    } catch (Exception ex) {

                    } finally {
                        socket_connected = false;
                        objGsmReadThread = null;
                        CsRead = null;
                        CsWrite = null;
                        socket = null;
                    }
                    objReadFiles.write_log_gprs_connectivity("Socket closed ");
                }
            } catch (Exception ex) {
            }
        }

        class GprsDataRead16833 implements Runnable {

            @Override
            public void run() {
                int data;
                byte state = 0;
                int i;
                byte[] charBuf = new byte[500];
                StringBuilder sb1 = new StringBuilder();
             //   clsGprsRead16833 objRead = new clsGprsRead16833();

                try {
                    while (true) {
                        if (CsRead != null) {
                            try {
                                int size = CsRead.read(charBuf);
                                set_gprs_res_ackpkt(true);
                                //    objReadFiles.write_rx_data_from_server("RX data " + sb2.toString());
                                if (size > 0) {
                                    for (i = 0; i < size; i++) {
                                        data = (int) charBuf[i];
                                        switch (state) {
                                            case 0:
                                                if (data == (char) ('$')) {
                                                    // objReadFiles.write_rx_data_from_server("$ came");
                                                    sb1 = sb1.delete(0, sb1.length());
                                                    state++;
                                                }
                                                break;
                                            case 1:
                                                if (data == (char) ('#')) {
                                                    state = 2;
                                                } else {
                                                    sb1.append((char) data);
                                                    break;
                                                }

                                            case 2: {

                                                // objReadFiles.write_rx_data_from_server(sb1.toString());
                                                try {

                                                 //   objRead.gprs_data_rx_process(sb1.toString(), (byte) 1);

                                                } catch (Exception ex) {

                                                }
                                                state = 0;
                                            }
                                            break;
                                        }
                                    }

                                    if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                        objReadFiles.write_rx_data_from_server("RX data " + sb1.toString());
                                    }
                                }
                            } catch (IOException ex) {
                                try {
                                    closeSocketCommunication();
                                } catch (Exception e) {

                                }
                                break;
                            } catch (Exception ex) {
                            }
                        }

                    }
                } catch (Exception ex) {
                }

            }

            public synchronized String convertToHexString(byte[] byteArray, int length) {
                // String nmea_data ="";// HexDump.toHexString(byteArray, 0, length);
                //byte[] bytes = hexStringToByteArray(nmea_data);
                // nmea_data = new String(byteArray, StandardCharsets.UTF_8);
                StringBuilder sb = new StringBuilder(length * 2);
                for (int b = 0; b < length * 2; b++) {
                    sb.append(String.format("%02x", byteArray[b]));
                }
                return sb.toString();
                // return nmea_data;
            }

            public synchronized void gprs_data_rx_process(byte[] buf, int length) {
                short packet_type;
                byte coded_no;
                int year;
                int mont;
                int day;
                int hr;
                int min;
                int sec;
                int k;
                //String OBUId;
                StringBuilder sb = new StringBuilder();
                int data_len;
                int i;
                int j;
                short s;
                int no_trips;
                // byte buf[] = str.getBytes();
                String[] sch_str = new String[100];
                String rx_str = "";
                byte ack_res = 0;
                short res_pkt_type;
                short ack_msg_type = 0;
                clsReadFiles objReadFiles = new clsReadFiles();
                try {
                    for (i = 0; i < 6; i++) {
                        sb.append((char) buf[i]);
                    }
                    rx_str = rx_str + "," + sb.toString();

                    //  OBUId = sb.toString();
                    //7 to 12 date and time
                    year = (buf[i++]);
                    mont = (buf[i++]);
                    day = (buf[i++]);
                    hr = (buf[i++]);
                    min = (buf[i++]);
                    sec = (buf[i++]);

                    rx_str = rx_str + "," + year + "," + mont + "," + day + "," + hr + "," + min + "," + sec;
                    //13 message type
                    packet_type = buf[i++];

                    rx_str = rx_str + "," + packet_type;

                    //data length 14 to 15
                    s = (short) ((short) (buf[i++] & 0xFF) << 8);
                    data_len = buf[i++] & 0xFF;
                    data_len = (s | data_len);
                    rx_str = rx_str + "," + data_len;
                    //16 to 23 reserved

                    i = i + 8;  //reserved

                    //data starts from 23
                    switch (packet_type) {
                        case clsPacketTypes.CS_GPRS_PKT_ACK:

                            try {
                                ack_res = buf[i++];
                                ack_msg_type = buf[i];

                                if (ack_res == GPRS_PKT_ACK_SUCCESS) {

                                    set_gprs_res_ackpkt_status(true);
                                } else {

                                    set_gprs_res_ackpkt_status(false);
                                }
                                set_gprs_res_pkt_ackmsg_type((byte) ack_msg_type);
                                set_gprs_res_ackpkt(true);
                                res_pkt_type = buf[i++];

                                if (res_pkt_type == clsPacketTypes.GPRS_PKT_DUTY_START) {

                                    clsDriverPktQueue obj = new clsDriverPktQueue();
                                    obj.removeAllDriverPkt();
                                    obj = null;
                                    if (ack_res == GPRS_PKT_ACK_SUCCESS) {

                                        set_gprs_res_ackpkt_status(true);

                                        set_login_status(clsDefines.DRIVER_LOGIN_SUCCESS);
                                        driver_login_send = clsDefines.DRIVER_LOGIN_SUCCESS;
                                    } else {

                                        set_login_status(clsDefines.DRIVER_LOGIN_FAILURE);
                                        set_gprs_res_ackpkt_status(false);
                                    }
                                }

                            } catch (Exception ex) {

                            }

                            if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                objReadFiles.write_rx_data_from_server("ACK RESPONSE " + ack_res + " msg type " + ack_msg_type);
                            }
                            break;
                        case clsPacketTypes.CS_GPRS_PKT_ADHOC:
                            //example 243030303030310E010D0E1E0034000E00000000000000004841505059204E4557205945415223
                            try {
                                sb.delete(0, sb.length());
                                j = 0;
                                for (; j < data_len && i < length; i++, j++) {
                                    // sb.append(str.charAt(i));
                                    sb.append((char) buf[i]);
                                }
                                rx_str = rx_str + "," + sb.toString();
                                if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                    objReadFiles.write_rx_data_from_server("ADHOC MESSAGE " + rx_str);
                                }
                                show_message_dialogbox(sb.toString());
                                if (getDisBrdName() == SUMITH_DISBRD) {
                                    fill_internal_details(sb.toString(), clsDefines.INTDB_DATA_NOT_SAVE);
                                }

                                audio_speaker_on();

                                //ObjTextSpeech.speak(sb.toString());
                                audio_speaker_off();
                               // ObjTextSpeech = null;

                            } catch (Exception ex) {

                            }

                            break;
                        case clsPacketTypes.CS_GPRS_PKT_PERMSCH:
                            /*

                             2430303030303111091210030B3D00230000000000000000202020202020204355525244757479320F071C0F072E0220202031303120202031303223
                             Duty ID 16 Unsigned
                             Time 8 String Time in HH:MM:SS format
                             Date 8 String Date in DD/MM/YY format
                             No of Trips 1 Byte Maximum 40 route Numbers
                             Route Number1 6 String Route no ,Maximum No of chars 6
                             ……….
                             Route Number 40
                             */

                            // show_message_dialogbox("Permanent Schedule Came");
                            try {
                                sb = sb.delete(0, sb.length());
                                i = 23;
                                for (j = 0; j < 16 && i < length; i++, j++) {
                                    // sb.append(str.charAt(i));
                                    sb.append((char) buf[i]);
                                }
                                sch_str[0] = sb.toString().trim(); //schid 16
                                rx_str = rx_str + "," + sch_str[0];
                                year = (buf[i++]);
                                mont = (buf[i++]);
                                day = (buf[i++]);
                                hr = (buf[i++]);
                                min = (buf[i++]);
                                sec = (buf[i++]);
                                //time
                                if (hr < 10) {
                                    sch_str[1] = "0" + String.valueOf(hr) + ":";
                                } else {
                                    sch_str[1] = String.valueOf(hr) + ":";
                                }

                                if (min < 10) {
                                    sch_str[1] = sch_str[1] + "0" + String.valueOf(min) + ":";
                                } else {
                                    sch_str[1] = sch_str[1] + String.valueOf(min) + ":";
                                }

                                if (sec < 10) {
                                    sch_str[1] = sch_str[1] + "0" + String.valueOf(sec);
                                } else {
                                    sch_str[1] = sch_str[1] + String.valueOf(sec);
                                }

                                //date
                                if (day < 10) {
                                    sch_str[2] = "0" + String.valueOf(day) + "/";
                                } else {
                                    sch_str[2] = String.valueOf(day) + "/";
                                }
                                if (mont < 10) {
                                    sch_str[2] = sch_str[2] + "0" + String.valueOf(mont) + "/";
                                } else {
                                    sch_str[2] = sch_str[2] + String.valueOf(mont) + "/";
                                }

                                if (year < 10) {
                                    sch_str[2] = sch_str[2] + "0" + String.valueOf(year);
                                } else {
                                    sch_str[2] = sch_str[2] + String.valueOf(year);
                                }

                                //  sch_str[1] = hr + ":" + min + ":" + sec;
                                //  sch_str[2] = day + ":" + mont + ":" + year;
                                rx_str = rx_str + "," + sch_str[1] + "," + sch_str[2];

                                no_trips = (buf[i++]);
                                sch_str[3] = (String.valueOf(no_trips));
                                rx_str = rx_str + "," + sch_str[3];
                                for (j = 0; j < no_trips; j++) {
                                    sb = sb.delete(0, sb.length());
                                    for (k = 0; k < 6 && i < length; i++, k++) {
                                        sb.append((char) buf[i]);
                                    }
                                    sch_str[4 + j] = sb.toString().trim();
                                    rx_str = rx_str + "," + sch_str[4 + j];
                                }
                                if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                    objReadFiles.write_rx_data_from_server("PERMANENT SCH " + rx_str);
                                }
                                if (objReadFiles.write_data_to_perm_sch_file(sch_str, (short) (0)) == true) {
                                    /*  MainActivityIts.handler_ui.post(new Runnable() {
                                     public void run() {
                                     SpannableStringBuilder biggerText = new SpannableStringBuilder("PERMANENT SCHEDULE PKT CAME");
                                     biggerText.setSpan(new RelativeSizeSpan(2.25f), 0, "PERMANENT SCHEDULE PKT CAME".length(), 0);
                                     toast = Toast.makeText(getApplicationContext(), biggerText, Toast.LENGTH_LONG);
                                     toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                                     toast.show();
                                     }
                                     });*/
                                } else {
                                    /* MainActivityIts.handler_ui.post(new Runnable() {
                                     public void run() {
                                     SpannableStringBuilder biggerText = new SpannableStringBuilder("PERMANENT SCHEDULE PKT ERROR");
                                     biggerText.setSpan(new RelativeSizeSpan(2.25f), 0, "PERMANENT SCHEDULE PKT ERROR".length(), 0);
                                     toast = Toast.makeText(getApplicationContext(), biggerText, Toast.LENGTH_LONG);
                                     toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                                     toast.show();
                                     }
                                     });*/

                                }

                            } catch (Exception ex) {

                            }
                            break;

                        case clsPacketTypes.CS_GPRS_PKT_TEMPSCH:

                            /*
                             Duty ID 16 Unsigned
                             Time 8 String Time in HH:MM:SS format
                             Date 8 String Date in DD/MM/YY format
                             No of Trips 1 Byte Maximum 40 route Numbers
                             Route Number1 6 String Route no ,Maximum No of chars 6
                             ……….
                             Route Number 40
                             */
                            //  show_message_dialogbox("Temp Schedule Came");
                            try {
                                sb = sb.delete(0, sb.length());
                                i = 23;
                                for (j = 0; j < 16 && i < length; i++, j++) {
                                    //  sb.append(str.charAt(i));
                                    sb.append((char) buf[i]);
                                }
                                sch_str[0] = sb.toString().trim(); //schid 16

                                year = (buf[i++]);
                                mont = (buf[i++]);
                                day = (buf[i++]);
                                hr = (buf[i++]);
                                min = (buf[i++]);
                                sec = (buf[i++]);

                                //time
                                if (hr < 10) {
                                    sch_str[1] = "0" + String.valueOf(hr) + ":";
                                } else {
                                    sch_str[1] = String.valueOf(hr) + ":";
                                }

                                if (min < 10) {
                                    sch_str[1] = sch_str[1] + "0" + String.valueOf(min) + ":";
                                } else {
                                    sch_str[1] = sch_str[1] + String.valueOf(min) + ":";
                                }

                                if (sec < 10) {
                                    sch_str[1] = sch_str[1] + "0" + String.valueOf(sec);
                                } else {
                                    sch_str[1] = sch_str[1] + String.valueOf(sec);
                                }

                                //date
                                if (day < 10) {
                                    sch_str[2] = "0" + String.valueOf(day) + "/";
                                } else {
                                    sch_str[2] = String.valueOf(day) + "/";
                                }
                                if (mont < 10) {
                                    sch_str[2] = sch_str[2] + "0" + String.valueOf(mont) + "/";
                                } else {
                                    sch_str[2] = sch_str[2] + String.valueOf(mont) + "/";
                                }

                                if (year < 10) {
                                    sch_str[2] = sch_str[2] + "0" + String.valueOf(year);
                                } else {
                                    sch_str[2] = sch_str[2] + String.valueOf(year);
                                }

                                // sch_str[1] = hr + ":" + min + ":" + sec;
                                // sch_str[2] = day + ":" + mont + ":" + year;
                                year = (buf[i++]);
                                mont = (buf[i++]);
                                day = (buf[i++]);
                                hr = (buf[i++]);
                                min = (buf[i++]);
                                sec = (buf[i++]);

                                //time
                                if (hr < 10) {
                                    sch_str[3] = "0" + String.valueOf(hr) + ":";
                                } else {
                                    sch_str[3] = String.valueOf(hr) + ":";
                                }

                                if (min < 10) {
                                    sch_str[3] = sch_str[3] + "0" + String.valueOf(min) + ":";
                                } else {
                                    sch_str[3] = sch_str[3] + String.valueOf(min) + ":";
                                }

                                if (sec < 10) {
                                    sch_str[3] = sch_str[3] + "0" + String.valueOf(sec);
                                } else {
                                    sch_str[3] = sch_str[3] + String.valueOf(sec);
                                }

                                //date
                                if (day < 10) {
                                    sch_str[4] = "0" + String.valueOf(day) + "/";
                                } else {
                                    sch_str[4] = String.valueOf(day) + "/";
                                }
                                if (mont < 10) {
                                    sch_str[4] = sch_str[4] + "0" + String.valueOf(mont) + "/";
                                } else {
                                    sch_str[4] = sch_str[4] + String.valueOf(mont) + "/";
                                }

                                if (year < 10) {
                                    sch_str[4] = sch_str[4] + "0" + String.valueOf(year);
                                } else {
                                    sch_str[4] = sch_str[4] + String.valueOf(year);
                                }

                                // sch_str[3] = hr + ":" + min + ":" + sec;
                                // sch_str[4] = day + ":" + mont + ":" + year;
                                no_trips = (buf[i++]);
                                sch_str[5] = (String.valueOf(no_trips));
                                for (j = 0; j < no_trips; j++) {
                                    sb = sb.delete(0, sb.length());
                                    for (k = 0; k < 6 && i < length; i++, k++) {
                                        // sb.append(str.charAt(i));
                                        sb.append((char) buf[i]);
                                    }
                                    sch_str[6 + j] = sb.toString().trim();
                                }
                                for (j = 0; j <= 5 + no_trips; j++) {
                                    rx_str = rx_str + "," + sch_str[j];
                                }
                                if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                    objReadFiles.write_rx_data_from_server("TEMP SCHEDULE " + rx_str);
                                }

                                if (objReadFiles.write_data_to_temp_sch_file(sch_str, (short) (0)) == true) {
                                    /* MainActivityIts.handler_ui.post(new Runnable() {
                                     public void run() {
                                     SpannableStringBuilder biggerText = new SpannableStringBuilder("TEMP SCHEDULE PKT CAME");
                                     biggerText.setSpan(new RelativeSizeSpan(2.25f), 0, "TEMP SCHEDULE PKT CAME".length(), 0);
                                     toast = Toast.makeText(getApplicationContext(), biggerText, Toast.LENGTH_LONG);
                                     toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                                     toast.show();
                                     }
                                     });*/
                                } else {
                                    /* MainActivityIts.handler_ui.post(new Runnable() {
                                     public void run() {
                                     SpannableStringBuilder biggerText = new SpannableStringBuilder("TEMP SCHEDULE PKT ERROR");
                                     biggerText.setSpan(new RelativeSizeSpan(2.25f), 0, "TEMP SCHEDULE PKT ERROR".length(), 0);
                                     toast = Toast.makeText(getApplicationContext(), biggerText, Toast.LENGTH_LONG);
                                     toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                                     toast.show();
                                     }
                                     });*/
                                }

                            } catch (Exception ex) {

                            }
                            break;
                        case clsPacketTypes.CS_GPRS_PKT_PANIC:
                            //243030303030310E05170F1B193C000100000000000000000323
                            //24000E8E43E3450E05170F1B1903000100000000000000000323
                            coded_no = buf[i];

                            //  final String show_data1 = "Coded Number " + String.valueOf(coded_no);
                            rx_str = rx_str + "," + coded_no;
                            if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                objReadFiles.write_rx_data_from_server("PANIC " + rx_str);
                            }

                            show_message_dialogbox("Coded Number " + String.valueOf(coded_no));
                            break;
                        case clsPacketTypes.CS_GPRS_PKT_SYSTEM_RESET:
                            try {
                                //243030303030310E05170F1B193F000100000000000000000023

                                gpsDriving objDriving = new gpsDriving();
                                objDriving.reset_on();
                                objDriving = null;
                                if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                    objReadFiles.write_rx_data_from_server("KPIT_CS_GPRS_PKT_SYSTEM_RESET " + rx_str);
                                }
                                show_message_dialogbox("Resetting OBU Command from Server");

                                reboot_system("GPRS RESET CMD");

                            } catch (Exception e) {
                                // TODO Auto-generated catch block

                            }
                            break;
                        case clsPacketTypes.CS_GPRS_PKT_ROUTE_END: {

                            Date start_date_time = null;
                            String str = "";

                            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

                            try {

                                try {

                                    year = (buf[i++]);
                                    mont = (buf[i++]);
                                    day = (buf[i++]);
                                    hr = (buf[i++]);
                                    min = (buf[i++]);
                                    sec = (buf[i++]);

                                    if (year < 10) {
                                        str = str + "0" + String.valueOf(year) + "/";
                                    } else {
                                        str = str + String.valueOf(year) + "/";
                                    }

                                    if (mont < 10) {
                                        str = str + "0" + String.valueOf(mont) + "/";
                                    } else {
                                        str = str + String.valueOf(mont) + "/";
                                    }

                                    //date
                                    if (day < 10) {
                                        str = str + "0" + String.valueOf(day) + " ";
                                    } else {
                                        str = str + String.valueOf(day) + " ";
                                    }

                                    //time
                                    if (hr < 10) {
                                        str = str + "0" + String.valueOf(hr) + ":";
                                    } else {
                                        str = str + String.valueOf(hr) + ":";
                                    }

                                    if (min < 10) {
                                        str = str + "0" + String.valueOf(min) + ":";
                                    } else {
                                        str = str + String.valueOf(min) + ":";
                                    }

                                    if (sec < 10) {
                                        str = str + "0" + String.valueOf(sec);
                                    } else {
                                        str = str + String.valueOf(sec);
                                    }

                                    start_date_time = sdf.parse(str);
                                } catch (ParseException ex) {

                                }

                                clsSharedVariables.setGprsRouteEndDateTime(start_date_time.getTime());
                            } catch (Exception ex) {

                            }
                            setGprsRouteEndPktCame(true);
                            show_message_dialogbox("Route End PAcket Came from Server " + start_date_time.toString());
                            break;
                        }
                        case clsPacketTypes.CS_GPRS_PKT_ROUTE: {

                            String route_id = null;
                            Date start_date_time = null;

                            int cnt;
                            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                            String duty_id = "";
                            String str = "";
                            try {
                                i = 23;
                                route_id = "";
                                cnt = i + 6;
                                for (j = i; j < cnt; j++) {
                                    route_id = route_id + (char) buf[i++];
                                }

                                clsSharedVariables.setGprsRouteId(route_id);

                                try {

                                    year = (buf[i++]);
                                    mont = (buf[i++]);
                                    day = (buf[i++]);
                                    hr = (buf[i++]);
                                    min = (buf[i++]);
                                    sec = (buf[i++]);

                                    if (year < 10) {
                                        str = str + "0" + String.valueOf(year) + "/";
                                    } else {
                                        str = str + String.valueOf(year) + "/";
                                    }

                                    if (mont < 10) {
                                        str = str + "0" + String.valueOf(mont) + "/";
                                    } else {
                                        str = str + String.valueOf(mont) + "/";
                                    }

                                    //date
                                    if (day < 10) {
                                        str = str + "0" + String.valueOf(day) + " ";
                                    } else {
                                        str = str + String.valueOf(day) + " ";
                                    }

                                    //time
                                    if (hr < 10) {
                                        str = str + "0" + String.valueOf(hr) + ":";
                                    } else {
                                        str = str + String.valueOf(hr) + ":";
                                    }

                                    if (min < 10) {
                                        str = str + "0" + String.valueOf(min) + ":";
                                    } else {
                                        str = str + String.valueOf(min) + ":";
                                    }

                                    if (sec < 10) {
                                        str = str + "0" + String.valueOf(sec);
                                    } else {
                                        str = str + String.valueOf(sec);
                                    }

                                    start_date_time = sdf.parse(str);
                                } catch (ParseException ex) {

                                }

                                clsSharedVariables.setGprsRouteStartDateTime(start_date_time.getTime());
                                try {
                                    str = "";
                                    if (min < 10) {
                                        str = str + "0" + String.valueOf(min) + ":";
                                    } else {
                                        str = str + String.valueOf(min) + ":";
                                    }

                                    if (sec < 10) {
                                        str = str + "0" + String.valueOf(sec);
                                    } else {
                                        str = str + String.valueOf(sec);
                                    }

                                    sdf1.parse(str);
                                } catch (ParseException ex) {
                                }

                                for (j = 0; j < 16; j++) {
                                    duty_id = duty_id + (char) buf[i++];
                                }

                            } catch (Exception ex) {
                            }
                            setGprsRouteStartPktCame(true);
                            show_message_dialogbox(route_id + " Route Start PAcket Came from Server " + start_date_time.toString());
                        }
                        break;
                        case clsPacketTypes.CS_GPRS_PKT_FILE_DOWNLOAD:

                            //ftp commands from server ITS.apk 243030303030310E05170F1B193C00070000000000000000004954532E61706B23
                            //phonenum.txt --> 243030303030310E05170F1B193C000C00000000000000000070686F6E656E756D2E74787423
                            //folder  --> 243030303030310E05170F1B193C0002000000000000000001202023
                            //routefiles -- >  243030303030310E05170F1B193C000A000000000000000001526f75746566696c657323
                            sb = sb.delete(0, sb.length());
                            byte type_folder_file;

                            try {
                                i = 23;
                                type_folder_file = (buf[i++]);

                                for (j = 0; j < data_len && i < length; i++) {
                                    //sb.append(str.charAt(i));
                                    sb.append((char) buf[i]);
                                    j++;
                                }

                                show_message_dialogbox("File Download " + sb.toString());

                                if (type_folder_file == FILE_TYPE) {

                                    final String str1 = sb.toString().trim();
                                    rx_str = rx_str + "," + str1;
                                    if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                        objReadFiles.write_rx_data_from_server("CS_GPRS_PKT_FILE_DOWNLOAD " + str1);
                                    }

                                    AsyncTaskRunnerGprsSingle gprsRunnerSingle;
                                    gprsRunnerSingle = new AsyncTaskRunnerGprsSingle(str1);
                                    gprsRunnerSingle.start();

                                } else { //DIRECTORY_TYPE

                                    final String folder_name = sb.toString().trim();
                                    startDownloadAllFilesWifiThread(folder_name);
                                }

                            } catch (Exception ex) {

                            }

                            break;
                        case clsPacketTypes.CS_GPRS_HEALTH_PKT:
                            //24000E8E43E3450E05170F1B1941000100000000000000000023

                            gpsDriving obj = new gpsDriving();
                            obj.health_construct_pkt();
                            obj = null;
                            break;
                        case clsPacketTypes.CS_GPRS_EMERGENCY_MAP_ROUTING: {
                            int no_location_points = 0;
                            clsSharedVariables.setNoEmergencyPtsFromGprs(0);
                            StringBuilder sb1 = new StringBuilder();
                            i = 23;
                            no_location_points = buf[i++];

                            if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                objReadFiles.write_rx_data_from_server("no_location_points " + rx_str + "," + no_location_points);
                            }
                            try {
                                clsSharedVariables.objMapData = new clsSharedVariables.S_claslatlong[no_location_points];
                                for (j = 0; j < no_location_points; j++) {
                                    clsSharedVariables.objMapData[j] = new clsSharedVariables.S_claslatlong();
                                }

                                if (no_location_points == 2) {
                                    double src_lat = 0.0;
                                    double src_long = 0.0;
                                    double des_lat = 0.0;
                                    double des_long = 0.0;
                                    sb1 = sb1.delete(0, sb1.length());
                                    for (k = 0; k < 8; k++) {
                                        sb1.append((char) buf[i++]);
                                    }
                                    //rx_str = sb1.toString();
                                    //     objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_latitude " + rx_str);
                                    try {
                                        src_lat = Double.parseDouble(sb1.toString());
                                    } catch (NumberFormatException ex) {
                                        if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                            objReadFiles.write_rx_data_from_server("Exception latitude " + ex.getMessage());
                                        }
                                    }
                                    sb1 = sb1.delete(0, sb1.length());
                                    for (k = 0; k < 8; k++) {
                                        sb1.append((char) buf[i++]);
                                    }
                                    //rx_str = sb1.toString();
                                    //     objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_longitude " + rx_str);
                                    try {
                                        src_long = Double.parseDouble(sb1.toString());
                                    } catch (Exception ex) {
                                        objReadFiles.write_rx_data_from_server("Exception Longitude " + ex.getMessage());

                                    }

                                    sb1 = sb1.delete(0, sb1.length());
                                    for (k = 0; k < 8; k++) {
                                        sb1.append((char) buf[i++]);
                                    }
                                    //  rx_str = sb1.toString();
                                    // objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_latitude " + rx_str);
                                    try {
                                        des_lat = Double.parseDouble(sb1.toString());
                                    } catch (Exception ex) {
                                        objReadFiles.write_rx_data_from_server("Exception latitude " + ex.getMessage());
                                    }
                                    sb1 = sb1.delete(0, sb1.length());
                                    for (k = 0; k < 8; k++) {
                                        sb1.append((char) buf[i++]);
                                    }
                                    // rx_str = sb1.toString();
                                    //  objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_longitude " + rx_str);
                                    try {
                                        des_long = Double.parseDouble(sb1.toString());
                                    } catch (NumberFormatException ex) {
                                        objReadFiles.write_rx_data_from_server("Exception Longitude " + ex.getMessage());
                                    }

                                    clsSharedVariables.setEmergencySrcLat(src_lat);
                                    clsSharedVariables.setEmergencySrcLong(src_long);
                                    clsSharedVariables.setEmergencyDesLat(des_lat);
                                    clsSharedVariables.setEmergencyDesLong(des_long);
                                    clsSharedVariables.setNoEmergencyPtsFromGprs(no_location_points);

                                    if (clsSharedVariables.getCurLatitude() == 0.0 || clsSharedVariables.getCurLongitude() == 0.0) {
                                        //      objReadFiles.write_rx_data_from_server("from src location");

                                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                                            objReadFiles.read_destination_map_data_google(src_lat, src_long, des_lat, des_long);

                                        } else {
                                            objReadFiles.read_destination_map_data(src_lat, src_long, des_lat, des_long);
                                        }
                                    } else {
                                        //   objReadFiles.write_rx_data_from_server(" from current location  ");
                                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                                            objReadFiles.read_destination_map_data_google(clsSharedVariables.getCurLatitude(), clsSharedVariables.getCurLongitude(), des_lat, des_long);

                                        } else {
                                            objReadFiles.read_destination_map_data(clsSharedVariables.getCurLatitude(), clsSharedVariables.getCurLongitude(), des_lat, des_long);
                                        }
                                    }

                                } else {
                                    for (j = 0; j < no_location_points; j++) {

                                        sb1 = sb1.delete(0, sb1.length());
                                        for (k = 0; k < 8; k++) {
                                            sb1.append((char) buf[i++]);
                                        }
                                        // rx_str = sb1.toString();
                                        //  objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_latitude " + rx_str);
                                        try {
                                            clsSharedVariables.objMapData[j].latitude = Double.parseDouble(sb1.toString());
                                        } catch (NumberFormatException ex) {
                                            objReadFiles.write_rx_data_from_server("Exception latitude " + ex.getMessage());
                                        }
                                        sb1 = sb1.delete(0, sb1.length());
                                        for (k = 0; k < 8; k++) {
                                            sb1.append((char) buf[i++]);
                                        }
                                        // rx_str = sb1.toString();
                                        //   objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_longitude " + rx_str);
                                        try {
                                            clsSharedVariables.objMapData[j].longitude = Double.parseDouble(sb1.toString());
                                        } catch (NumberFormatException ex) {
                                            objReadFiles.write_rx_data_from_server("Exception Longitude " + ex.getMessage());

                                        }
                                    }
                                    clsSharedVariables.setNoEmergencyPtsFromGprs(no_location_points);
                                    clsSharedVariables.no_maps_latlong_points = no_location_points;
                                    if (no_location_points > 0) {
                                        clsSharedVariables.setEmergencySrcLat(clsSharedVariables.objMapData[0].latitude);
                                        clsSharedVariables.setEmergencySrcLong(clsSharedVariables.objMapData[0].longitude);
                                        clsSharedVariables.setEmergencyDesLat(clsSharedVariables.objMapData[no_location_points - 1].latitude);
                                        clsSharedVariables.setEmergencyDesLong(clsSharedVariables.objMapData[no_location_points - 1].longitude);
                                    }

                                }
                            } catch (Exception e) {
                                objReadFiles.write_rx_data_from_server("Exception " + e);
                            }
                            // rx_str = rx_str + "," + sb1.toString();
                            //  objReadFiles.write_rx_data_from_server("GPRS_EMERGENCY_final " + rx_str);
                            objReadFiles.write_emergency_map_data();
                            objReadFiles.write_rx_data_from_server(" emergency map came true  ");
                            setEmergencyMapPktCame(true);

                        }
                        break;
                        default:
                            break;
                    }
                } catch (Exception ex) {

                } finally {
                    sb = null;
                    buf = null;
                    sch_str = null;
                    rx_str = null;
                    objReadFiles = null;
                }
            }

        }
    }

    // Class to handle uncaught exceptions for threads
    class ExceptionHandler2 implements UncaughtExceptionHandler {

        // Method called when an uncaught exception occurs in a thread
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // Create a new instance of the file read helper class
            clsReadFiles objReadFiles = new clsReadFiles();
            // Log the exception and indicate that the service will be restarted
            objReadFiles.write_log_gprs_connectivity("clsGprsService UncaughtExceptionHandler " + e.getMessage() + " Restarting clsGprsService Again ");
            objReadFiles = null;

            // Restart the GsmStateMachine2 thread if the second IP address is enabled
            if (clsSharedVariables.getIpAddr2Enable()) {
                // If the GsmStateMachine2 instance is null, create and start a new instance
                if (objGsmStateMachine2 == null) {
                    objGsmStateMachine2 = new GsmStateMachine2();
                    objGsmStateMachine2.start();
                } else {
                    // If the GsmStateMachine2 instance is already running, interrupt and recreate it
                    objGsmStateMachine2.interrupt();
                    objGsmStateMachine2 = null;
                    objGsmStateMachine2 = new GsmStateMachine2();
                    objGsmStateMachine2.start();
                }
            }
        }
    }

    class GsmStateMachine2 extends Thread {

        // Constructor
        public GsmStateMachine2() {
            super();
        }

        // Buffer for reading data
        byte[] readDataBuf = new byte[5000];
        // Socket for network communication
        Socket socket = null;
        // Output stream for writing data
        DataOutputStream CsWrite = null;
        // Input stream for reading data
        DataInputStream CsRead = null;
        // Helper class for file operations
        clsReadFiles objReadFiles = new clsReadFiles();
        // Flag to indicate socket connection status
        boolean socket_connected = false;
        // Thread for reading GPRS data
        Thread objGsmReadThread = null;
        // Helper class for health packet structure
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

        // Main logic of the thread
        @Override
        public void run() {
            // Queue for emergency packets
            clsEmergencyPktQueue objEmer = new clsEmergencyPktQueue();
            // Set exception handler for the thread
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler2());
            // Timeout for socket connection in milliseconds
            final int timeoutInMs = 30000;

            // Infinite loop for continuous operation
            while (true) {
                try {
                    // Write data to file on reset
                    write_data_on_reset_to_file();

                    // Check if the second IP address is enabled
                    if (!clsSharedVariables.getIpAddr2Enable()) {
                        setServerConnectImg2(SERVER_DISCONNECTED);
                        Thread.sleep(10000);
                    } else {
                        // Handle server configuration change
                        if (getServer2ConfigStatus() == true) {
                            setServer2ConfigStatus(false);
                            closeSocketCommunication();
                            socket_connected = false;
                            objReadFiles.write_log_gprs_connectivity_ip2("Second IP Network Disconnect : Server Config Changed");
                        }

                        // Attempt to establish socket connection if not connected
                        if (socket_connected == false) {
                            try {
                                // Create and connect socket
                                socket = new java.net.Socket();
                                socket.connect(new InetSocketAddress(getIpAddr2(), getPortNo2()), timeoutInMs);
                                objReadFiles.write_log_gprs_connectivity_ip2("GPRS Connecting to Dual Interface: " + getIpAddr2() + " " + getPortNo2());
                                CsWrite = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                                CsRead = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

                                socket_connected = true;

                                // Read stored emergency packets
                                objReadFiles.read_stored_emergency_pkts2();
                                objReadFiles.write_log_gprs_connectivity_ip2("Network Connected Second Interface Signal strength ");

                                // Start thread to read GPRS data
                                GprsDataRead16833 objGsmRead = new GprsDataRead16833();
                                objGsmReadThread = new Thread((Runnable) objGsmRead);
                                objGsmReadThread.start();
                            } catch (UnknownHostException e) {
                                closeSocketCommunication();
                                socket_connected = false;
                                socket = null;
                                objReadFiles.write_log_gprs_connectivity_ip2("Network Disconnect Second IP : Trying to connect to server UnknownHostException " + getIpAddr1() + " " + getPortNo1() + " sig ");
                            } catch (SocketException e) {
                                closeSocketCommunication();
                                socket_connected = false;
                                objReadFiles.write_log_gprs_connectivity_ip2("Second IP  Network Disconnect : Trying socket exc " + e.getMessage() + " " + getIpAddr1() + " " + getPortNo1() + " sig ");
                                socket = null;
                            } catch (IOException e) {
                                closeSocketCommunication();
                                socket_connected = false;
                                objReadFiles.write_log_gprs_connectivity_ip2("Second IP  Network Disconnect IO Exception  sig " + e.getMessage());
                                socket = null;
                            } catch (Exception e) {
                                closeSocketCommunication();
                                socket_connected = false;
                                objReadFiles.write_log_gprs_connectivity_ip2("Second IP  Network Disconnect : exc " + e.getMessage());
                                socket = null;
                            }
                        } else {
                            // Handle emergency packet transmission
                            if (objEmer.getLengthIP2() > 0) {
                                readDataBuf = objEmer.removeEmergencyPktIP2();
                                if (write_serial_port_dataIP2(readDataBuf) == false) {
                                    objEmer.addEmergencyPktIP2(readDataBuf);
                                }
                            }

                            // Update server connection status
                            setServerConnectImg2(SERVER_CONNECTED);
                            try {
                                SwingUtilities.invokeLater(() -> {
                                    if (clsSharedVariables.getWifiConnect() == false) {
                                        update_mobile_icon(true);
                                    }
                                    update_servercon2_icon(true);
                                });
                            } catch (Exception ex) {
                            }
                        }
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    // Sleep for a short duration before next iteration
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }

        }

        // Write data to file on reset
        private void write_data_on_reset_to_file() {
            try {
                if (getResetObuVal() == RESET_OBU_CONFIRMED) {
                    // Store data in file as reset message received
                    if (objReadFiles.write_all_stored_emergency_pkts_ip2() == true) {
                        runCmd("sync");
                    }
                    setResetObuVal(RESET_OBU_FINISHED);
                }
            } catch (Exception ex) {
            } finally {
            }
        }

        // Write data to serial port
        private boolean write_serial_port_dataIP2(byte[] bytes) {
            if (socket_connected == false) {
                return false;
            }
            if (bytes.length < 3) {
                return true;
            }
            long tx_bytes_previous;
            long tx_bytes_previous_wifi;
            byte retry_pkt_inc;
            objHealth.set_gprs_status((byte) 1);
            for (retry_pkt_inc = 0; retry_pkt_inc < 3; retry_pkt_inc++) {
                try {
                    tx_bytes_previous = read_ppp_transmit_bytes_cnt();
                    tx_bytes_previous_wifi = read_wifi_transmit_bytes_cnt();
                    CsWrite.write(bytes, 0, bytes.length);
                    CsWrite.flush();
                    if (check_ppp_no_packets_sent(bytes.length, tx_bytes_previous) == true) {
                        return true;
                    } else if (check_wifi_no_packets_sent(bytes.length, tx_bytes_previous_wifi) == true) {
                        return true;
                    }
                } catch (IOException ex) {
                    break;
                }
            }
            if (getGsmSignalStrength() < 10) {
                // Switch network if in manual mode
            }
            closeSocketCommunication();
            return false;
        }

        // Read PPP transmit bytes count
        private long read_ppp_transmit_bytes_cnt() {
            BufferedReader br = null;
            String line;
            File file;
            try {
                file = new File(PPP_TRANSMIT_DATA_PATH);
                if (file.exists()) {
                    file.setReadable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        return Long.parseLong(line);
                    }
                }
            } catch (FileNotFoundException e) {
            } catch (IOException | NumberFormatException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {
                }
            }
            return 0;
        }

        // Check if PPP sent no packets
        private synchronized boolean check_ppp_no_packets_sent(long actual_bytes_cnt, long prev_bytes_cnt) {
            long diff;
            long cnt;
            int i;
            boolean found = false;
            try {
                for (i = 0; i < 60 && found == false; i++) {
                    cnt = read_ppp_transmit_bytes_cnt();
                    if (prev_bytes_cnt < 10) {
                        if (cnt > actual_bytes_cnt || cnt > (prev_bytes_cnt + actual_bytes_cnt) / 2) {
                            return true;
                        }
                    }
                    if (cnt > prev_bytes_cnt) {
                        diff = cnt - prev_bytes_cnt;
                        //// ////////System.out.println("cnt > prev_bytes_cnt read_ppp_transmit_bytes_cnt " + cnt + "diff "+diff);
                    } else {
                        diff = prev_bytes_cnt - cnt;
                        //// ////////System.out.println("read_ppp_transmit_bytes_cnt " + cnt + "diff "+diff);
                    }
                    if (diff >= actual_bytes_cnt && diff > 5) {
                        return true;
                    }
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                return false;
            }
            return false;
        }

        private long read_wifi_transmit_bytes_cnt() {
            BufferedReader br = null;
            String line;
            File file;
            try {
                file = new File(WLAN0_TRANSMIT_DATA_PATH);
                if (file.exists()) {
                    file.setReadable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        return Long.parseLong(line);
                    }
                }
            } catch (FileNotFoundException e) {
            } catch (IOException | NumberFormatException e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    line = null;
                    br = null;
                    file = null;
                } catch (IOException e) {
                }
            }
            return 0;
        }

        private synchronized boolean check_wifi_no_packets_sent(long actual_bytes_cnt, long prev_bytes_cnt) {
            long diff;
            long cnt;
            int i;
            boolean found = false;
            try {
                for (i = 0; i < 60 && found == false; i++) {
                    cnt = read_wifi_transmit_bytes_cnt();
                    if (prev_bytes_cnt < 10) {
                        if (cnt > actual_bytes_cnt || cnt > (prev_bytes_cnt + actual_bytes_cnt) / 2) {
                            return true;
                        }
                    }
                    if (cnt > prev_bytes_cnt) {
                        diff = cnt - prev_bytes_cnt;
                    } else {
                        diff = prev_bytes_cnt - cnt;
                    }
                    if (diff >= actual_bytes_cnt && diff > 5) {
                        return true;
                    }
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                return false;
            }
            return false;
        }

        private void chkExceptionMsg(String data) {
            if (data.contains("No route to host") || data.contains("Network is unreachable")) {
                try {
                    if (getWifiConnect() == false) {
                        objReadFiles.write_log_gprs_connectivity_ip2(" Processing Wifi Disconnection...");

                        runCmd("sudo ifconfig wlan0 down");
                        runCmd("sudo rfkill  block wifi");
                        Thread.sleep(3000);
                        runCmd("sudo rfkill  unblock ppp0");
                        Thread.sleep(3000);
                        runCmd("sudo ifconfig ppp0 up");
                        NetworkInterface nif;
                        try {
                            nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);
                            if (nif == null || !nif.isUp()) {
                                runCmd("sudo rfkill   block ppp0");
                                runCmd("sudo rfkill   unblock ppp0");
                                runCmd("sudo ifconfig ppp0 up");
                                Thread.sleep(1000);
                                if (nif == null || !nif.isUp()) {
                                    runCmd("sudo rfkill  unblock ppp0");
                                    runCmd("sudo ifconfig ppp0 up");
                                }
                            }
                        } catch (SocketException ex) {
                        }
                    } else {
                        objReadFiles.write_log_gprs_connectivity_ip2(" Processing gprs Disconnection...");
                        runCmd("sudo rfkill  block ppp0");
                        Thread.sleep(3000);
                        runCmd("sudo rfkill  unblock wifi");
                        // runCmd("sudo rfkill  block wifi");
                        // runCmd("sudo ifconfig ppp0 down");
                        Thread.sleep(3000);
                        runCmd("sudo ifconfig wifi up");
                        NetworkInterface nif;
                        try {
                            nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);
                            if (nif == null || !nif.isUp()) {
                                runCmd("sudo rfkill block wifi");
                                runCmd("sudo rfkill unblock wifi");
                                runCmd("sudo ifconfig wlan0 up");
                                Thread.sleep(1000);
                                if (nif == null || !nif.isUp()) {
                                    runCmd("sudo rfkill  unblock wifi");
                                    runCmd("sudo ifconfig wlan0 up");
                                }
                            }
                        } catch (SocketException ex) {
                        }
                    }
                } catch (InterruptedException ex) {
                    // Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public synchronized void closeSocketCommunication() {
            try {
                socket_connected = false;
                setGpsPktCamInGprs(0);
                setServerConnectImg2(SERVER_DISCONNECTED);
                try {
                    try {
                        SwingUtilities.invokeLater(() -> {
                            update_servercon2_icon(false);
                        });
                    } catch (Exception ex) {
                    }
                } catch (Exception ex) {
                }
                if (socket != null) {
                    try {
                        try {
                            if (objGsmReadThread != null) {
                                objGsmReadThread.interrupt();
                            }
                            // socket.shutdownOutput();
                        } catch (Exception ex) {

                        }
                        socket.close();
                    } catch (IOException e) {
                    } catch (Exception ex) {

                    } finally {
                        socket_connected = false;
                        objGsmReadThread = null;
                        CsRead = null;
                        CsWrite = null;
                        socket = null;
                    }
                    objReadFiles.write_log_gprs_connectivity_ip2("Second IP Socket closed ");
                }
            } catch (Exception ex) {
            }
        }

        class GprsDataRead16833 implements Runnable {

            @Override
            public void run() {
                int data;
                byte state = 0;
                int i;
                byte[] charBuf = new byte[500];
                StringBuilder sb1 = new StringBuilder();
//                clsGprsRead16833 objRead = new clsGprsRead16833();
                try {
                    while (true) {
                        if (CsRead != null) {
                            try {
                                int size = CsRead.read(charBuf);
                                set_gprs_res_ackpkt(true);
                                //    objReadFiles.write_rx_data_from_server("RX data " + sb2.toString());
                                if (size > 0) {
                                    for (i = 0; i < size; i++) {
                                        data = (int) charBuf[i];
                                        switch (state) {
                                            case 0:
                                                if (data == (char) ('$')) {
                                                    // objReadFiles.write_rx_data_from_server("$ came");
                                                    sb1 = sb1.delete(0, sb1.length());
                                                    state++;
                                                }
                                                break;
                                            case 1:
                                                if (data == (char) ('#')) {
                                                    state = 2;
                                                } else {
                                                    sb1.append((char) data);
                                                    break;
                                                }
                                            case 2: {
                                                //objReadFiles.write_rx_data_from_server(sb1.toString());
                                                try {
                                                  //  objRead.gprs_data_rx_process(sb1.toString(), (byte) 2);
                                                } catch (Exception ex) {
                                                }
                                                state = 0;
                                            }
                                            break;
                                        }

                                    }

                                    if (clsDefines.GPRS_LOG_DEBUG_RX_ENABLE) {
                                        objReadFiles.write_rx_data_from_server("RX data " + sb1.toString());
                                    }
                                }
                            } catch (IOException ex) {

                                try {
                                    closeSocketCommunication();
                                } catch (Exception e) {
                                }
                                break;
                            } catch (Exception ex) {
                            }

                        }

                    }
                } catch (Exception ex) {
                }
            }

            public synchronized String convertToHexString(byte[] byteArray, int length) {
                StringBuilder sb = new StringBuilder(length * 2);
                for (int b = 0; b < length * 2; b++) {
                    sb.append(String.format("%02x", byteArray[b]));
                }
                return sb.toString();
            }
        }
    }

   

  

    private synchronized boolean runCmd(String cmd) {
        Process process = null;

        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(2, TimeUnit.SECONDS);
            return true;
        } catch (IOException | InterruptedException e) {
            return false; // Return false if there are IO or interruption exceptions
            //
        } //
        finally {

            try {
                if (process != null) {
                    rt.freeMemory(); // Free up memory used by the runtime
                    rt.gc(); // Request garbage collection to reclaim memory

                    process.getInputStream().close(); // Close input stream of the process
                    process.getOutputStream().close(); // Close output stream of the process
                    process.getErrorStream().close(); // Close error stream of the process
                    process.destroy(); // Destroy the process
                    process = null; // Set process to null
                    rt = null; // Set runtime to null

                }
            } catch (IOException e) {
                //
            } catch (Exception ex) {
                //
            }
        }
    }

    /**
     * Converts a short value to a byte array.
     *
     * @param value The short value to convert.
     * @return The byte array representation of the short value.
     */
    public byte[] convertToByteArray(short value) {
        // Initialize a byte array of length 2
        byte[] bytes = new byte[2];
        ByteBuffer buffer;

        // Allocate a ByteBuffer of the same length as the byte array
        buffer = ByteBuffer.allocate(bytes.length);

        // Put the short value into the ByteBuffer
        buffer.putShort(value);

        // Nullify the 'bytes' array reference (likely unnecessary here)
        bytes = null;

        // Return the byte array representation of the ByteBuffer
        return buffer.array();
    }

    /**
     * Copies a file from the specified input path to the output path. Creates
     * the output directory if it doesn't exist. Deletes the input file after
     * successful copying.
     *
     * @param inputPath The path of the file to copy.
     * @param outputPath The path where the copied file should be placed.
     * @return true if the file is successfully copied, false otherwise.
     */
    private synchronized boolean copyFile(String inputPath, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        File outFile;
        File inFile = null;
        boolean copied = false;
        byte[] buffer = new byte[1024];
        int read;

        try {
            // Create output directory if it doesn't exist
            outFile = new File(outputPath);
            inFile = new File(inputPath);

            // Check if input file exists
            if (inFile.exists() == false) {
                return false;
            }

            // Check if input is a file
            if (inFile.isFile()) {
                // Open input stream
                in = new FileInputStream(inFile);

                // Create output file if it doesn't exist
                try {
                    if (!outFile.exists()) {
                        outFile.createNewFile();
                    }
                } catch (IOException ex) {
                    // Handle exception if file creation fails
                }

                // Open output stream
                out = new FileOutputStream(outFile, false);

                // Copy data from input to output
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                    copied = true;
                }

                return copied; // Return true if file copied successfully
            }

        } catch (IOException e) {
            // Handle IO exceptions
            // Log.e("tag", e.getMessage());
        } finally {
            try {
                // Close output stream
                if (out != null) {
                    out.flush();
                    out.close();
                    out = null;
                }

                // Close input stream
                if (in != null) {
                    in.close();
                    in = null;
                }

                // Delete input file after copying
                if (inFile != null) {
                    inFile.delete();
                }

                // Clean up resources
                outFile = null;
                inFile = null;
                buffer = null;

            } catch (IOException e) {
                // Handle IO exceptions while closing streams
            }
        }

        return false; // Return false if copying fails
    }

    /**
     * Initiates a download process for all files from an FTP server over WiFi,
     * based on specified folder_name.
     *
     * @param folder_name The name of the folder on the FTP server from which
     * files are to be downloaded.
     */
    private void startDownloadAllFilesWifiThread(String folder_name) {
        SwingWorker<String, String> sw1 = new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() {
                Session session = null;
                ChannelSftp channelSftp = null;
                boolean route_files_found = false;
                try {
                    JSch jsch = new JSch();
                    session = jsch.getSession(ftpusername, ftp_ipaddress.split("/")[0],ftp_port_no);
                    session.setPassword(ftpPassword);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();
                    Channel channel = session.openChannel("sftp");
                    channel.connect();
                    channelSftp = (ChannelSftp) channel;
                    String[] split_str = ftp_ipaddress.split("/");
                    StringBuilder file_path = new StringBuilder();
                    for (int i = 1; i < split_str.length; i++) {
                        file_path.append("/").append(split_str[i]);
                    }
                    if (!folder_name.isEmpty()) {
                        file_path.append("/").append(folder_name);
                    }
                  String  file1_path = "/home/sumith";
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = sdf.format(new Date());
                    String file2_path = file1_path + "/" + currentDate;
                    String vehicleId = clsSharedVariables.getVechicleRegNo();
                        String vehicleFolderPath = file2_path + "/" + vehicleId;                    
                    // Change to the correct directory if needed
                    file_path = new StringBuilder(vehicleFolderPath);
                    Vector<ChannelSftp.LsEntry> files = channelSftp.ls(file_path.toString());
                    int size_dir = files.size();
                    if (size_dir > 3) {
                        channelSftp.getSession().setTimeout(60000 * size_dir);
                    }
                    String[] files_list = new String[size_dir];
                    String fileCopyListFile;
                    if (file_path.toString().isEmpty()) {
                        fileCopyListFile = "FILECOPY.LST";
                    } else {
                        fileCopyListFile = file_path + "/" + "FILECOPY.LST";
                    }
                    SftpATTRS attrs = channelSftp.lstat(fileCopyListFile);
                    long size_file = attrs.getSize();
                    channelSftp.get(fileCopyListFile, "FILECOPY.LST");
                    if (new File("FILECOPY.LST").length() == size_file) {
                        try (BufferedReader br = new BufferedReader(new FileReader("FILECOPY.LST"))) {
                            String line;
                            int files_cnt = 0;
                            while ((line = br.readLine()) != null && files_cnt < 1000) {
                                for (ChannelSftp.LsEntry file : files) {
                                    if (file.getFilename().equals(line)) {
                                        files_list[files_cnt++] = line;
                                    }
                                }
                            }
                            for (int i = 0; i < files_cnt; i++) {
                                try {
                                    File file = clsSharedVariables.getHardDriveDetected()
                                            ? new File(route_filepath, files_list[i])
                                            : new File(main_route_filepath, files_list[i]);

                                    line = files_list[i];
                                    if (!file_path.toString().trim().isEmpty()) {
                                        files_list[i] = file_path + "/" + files_list[i];
                                    }
                                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                                        channelSftp.get(files_list[i], outputStream);
                                        outputStream.flush();
                                    }
                                    size_file = file.length();
                                    if (size_file == file.length()) {
                                        publish(files_list[i] + " Download Success");

                                        runCmd("sudo chmod 777 " + files_list[i]);
                                        if (line.contains("ConfigParams.txt") || line.contains("CameraConfig.txt") || line.contains("CameraConfigArray.txt")) {
                                            if (copyFile(file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line)) {
                                                publish(line + " File Copied");
                                            }
                                            if (clsSharedVariables.getHardDriveDetected()) {
                                                if (copyFile(file.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + line)) {
                                                    publish(line + " File Copied");
                                                }
                                            }
                                        } else if (line.contains("canElectrical.txt") || line.contains("canSafety.txt") || line.contains("canTransmit.txt")
                                                || line.contains("canEngine.txt") || line.contains("canOthers.txt") || line.contains("canElectrical1.txt")
                                                || line.contains("canSafety1.txt") || line.contains("canTransmit1.txt") || line.contains("canEngine1.txt")
                                                || line.contains("canOthers1.txt") || line.contains("phonenum.txt") || line.contains("names.txt")
                                                || line.contains("PreDefLoc.txt") || line.contains("TSPPoint.txt") || line.contains("codedmsg.txt")
                                                || line.contains("speclmsg.txt") || line.contains("drivrlst.txt") || line.contains("fileChangePwd.txt")
                                                || line.contains("driver_details.txt") || line.contains("ConductorIds.txt")) {

                                            if (clsSharedVariables.getHardDriveDetected()) {
                                                if (copyFile(file.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + line)) {
                                                    publish(line + " File Copied");
                                                }
                                            }

                                            if (copyFile(file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line)) {
                                                publish(line + " File Copied");
                                            }
                                        } else {
                                            route_files_found = true;
                                        }
                                    } else {
                                        publish(files_list[i] + " Download Failure");
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            publish("All Files Downloaded");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            clsReadFiles objReadFiles = new clsReadFiles();
                            clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
                            objReadFiles.read_special_msg_info();
                            objReadFiles.read_route_master_file();
                            objReadFiles.read_cfg_data_file();
                            objReadFiles.read_camera_cfg_file();
                            objReadFiles.read_phone_no_details();
                            objReadFiles.read_password_change();
                            objReadFiles.read_traffic_points();
                            objReadFiles.read_pre_def_location_points();
                            objReadFiles.read_panic_messages();
                            objReadFiles.read_special_msg_info();
                            objReadFiles.read_reset_data();
                            objReadFiles.read_driver_names();
                            objReadFiles.read_conductor_names();
                            objReadFiles.read_label_names_details();
                            objReadFiles.read_canElectricals_file();
                            objReadFiles.read_canSafety_file();
                            objReadFiles.read_canEngine_file();
                            objReadFiles.read_canTransmit_file();
                            objReadFiles.read_canOthers_file();
                            objReadDataFiles.read_canElectricals_file1();
                            objReadDataFiles.read_canSafety_file1();
                            objReadDataFiles.read_canEngine_file1();
                            objReadDataFiles.read_canTransmit_file1();
                            objReadDataFiles.read_canOthers_file1();

                            if (route_files_found) {
                                if (clsSharedVariables.getCurTripStat() != TRIP_START) {
                                    setCurSchNoTrips(getNoRoutes());
                                    setSchId("1");
                                    for (int j = 0; j < getCurSchNoTrips(); j++) {
                                        setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                                        setCurSchTripStatus(j, TRIP_UNKNOWN);
                                    }
                                    final Calendar cal = Calendar.getInstance();
                                    setCurSchStartDate(cal.getTime());
                                    setCurSchStartTime(cal.getTime().getTime());
                                    cal.add(Calendar.DATE, 1);
                                    setCurSchEndDate(cal.getTime());
                                    setCurSchEndTime(cal.getTime().getTime());
                                    setCurTripNo((byte) 0);
                                    setCurTripStat(TRIP_UNKNOWN);
                                    setCurRouteStat(false);
                                    objReadFiles.write_cur_route_info_file();
                                    objReadFiles.write_cur_trip_info_file();
                                    objReadFiles.write_cur_schedule_file();
                                    final ClsSchedule objSch = new ClsSchedule();
                                    if (getNoRoutes() > 0) {
                                        objSch.send_route_info();
                                    }
                                }
                            }
                        }
                    } else {
                        publish("Download Failure");
                        return "Failure";
                    }
                    return "Over";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return "Failure";
                } finally {
                    if (channelSftp != null) {
                        channelSftp.exit();
                    }
                    if (session != null) {
                        session.disconnect();
                    }
                }
            }

            @Override
            protected void process(List<String> chunks) {
                for (String data : chunks) {

                    if (data.equals("Over") || data.equals("Disconnected") || data.equals("Failure")) {
                        break;
                    }
                }
            }

            @Override
            protected void done() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Execute the SwingWorker thread
        sw1.execute();
    }

    /**
     * This class handles the asynchronous download of a single file via FTP
     * using a SwingWorker thread.
     */
    private class AsyncTaskRunnerGprsSingle extends Thread {

        /**
         * Name of the file to be downloaded.
         */
        String filename;

        /**
         * Constructor initializing the filename.
         *
         * @param file_name The name of the file to download.
         */
        AsyncTaskRunnerGprsSingle(String file_name) {
            filename = file_name;
        }

        /**
         * Main execution method that initiates the download process.
         */
        @Override
        public void run() {
            try {
                if (filename.contains("ObuIts.jar")) {
                    // Handling specific case for ObuIts.jar
                    final File file1 = new File(obu_filepath + "/" + "ObuIts1.jar");
                    startDownloadSingleFileWifiThread("ObuIts.jar", file1);
                } else {
                    // General case for other files
                    File file1;
                    if (clsSharedVariables.getHardDriveDetected()) {
                        file1 = new File(route_filepath + "/" + filename);
                    } else {
                        file1 = new File(main_route_filepath + "/" + filename);
                    }
                    startDownloadSingleFileWifiThread(filename, file1);
                }
            } catch (Exception e) {
                // Handle any exceptions
            }
        }

        /**
         * Method to start the download of a single file over Wi-Fi using FTP.
         *
         * @param filename The name of the file to download.
         * @param localFile The local file object where the downloaded file will
         * be saved.
         */
        private void startDownloadSingleFileWifiThread(String filename, File localFile) {
            SwingWorker<Void, String> sw1 = new SwingWorker<Void, String>() {
                @Override
                protected Void doInBackground() throws Exception {
                    JSch jsch = new JSch();
                    Session session = null;
                    ChannelSftp channelSftp = null;
                    try {
                        publish("Connecting to SFTP Server");
                        session = jsch.getSession(ftpusername, ftp_ipaddress, ftp_port_no);
                        session.setPassword(ftpPassword);
                        session.setConfig("StrictHostKeyChecking", "no");
                        session.connect();
                        channelSftp = (ChannelSftp) session.openChannel("sftp");
                        channelSftp.connect();
                        publish("SFTP Connected");
                        // Download file
                        publish("Downloading file: " + filename);
                        channelSftp.get(filename, localFile.getAbsolutePath());
                        publish("Downloaded Successfully");
                        // Additional processing for "ObuIts" file
                        if (filename.contains("ObuIts")) {
                            File f = new File(obu_filepath.getPath() + "/ObuIts.jar");
                            File oldFile = new File(obu_filepath.getPath() + "/ObuIts2.jar");
                            f.renameTo(oldFile);

                            // Copy new file to actual file
                            f = new File(obu_filepath.getPath() + "/ObuIts1.jar");
                            File newFile = new File(obu_filepath.getPath() + "/ObuIts.jar");
                            if (f.renameTo(newFile)) {
                                publish("Rebooting System...");
                                reboot_system("App Wifi Download");
                            }
                        } else {
                            publish("Downloaded Successfully");

                            runCmd("sudo chmod 777 " + localFile);
                            if (filename.contains("ConfigParams.txt")) {
                                try {
                                    if (copyFile(localFile.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + filename)) {
                                        publish(filename + " File Copied");
                                    }
                                    if (clsSharedVariables.getHardDriveDetected() && copyFile(localFile.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + filename)) {
                                        publish(filename + " File Copied");
                                    }
                                } catch (Exception ex) {
                                    publish("Error handling ConfigParams.txt: " + ex.getMessage());
                                }
                            } else if (filename.contains("canElectrical.txt") || filename.contains("canSafety.txt")
                                    || filename.contains("canTransmit.txt") || filename.contains("canEngine.txt")
                                    || filename.contains("canOthers.txt") || filename.contains("phonenum.txt")
                                    || filename.contains("names.txt") || filename.contains("PreDefLoc.txt")
                                    || filename.contains("TSPPoint.txt") || filename.contains("codedmsg.txt")
                                    || filename.contains("speclmsg.txt") || filename.contains("drivrlst.txt")
                                    || filename.contains("fileChangePwd.txt") || filename.contains("driver_details.txt")) {
                                try {
                                    if (clsSharedVariables.getHardDriveDetected() && copyFile(localFile.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + filename)) {
                                        publish(filename + " File Copied");
                                    }
                                    if (copyFile(localFile.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + filename)) {
                                        publish(filename + " File Copied");
                                    }

                                    clsReadFiles objReadFiles = new clsReadFiles();
                                    clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
                                    objReadFiles.read_special_msg_info();
                                    objReadFiles.read_route_master_file();
                                    objReadFiles.read_cfg_data_file();
                                    objReadFiles.read_camera_cfg_file();
                                    objReadFiles.read_phone_no_details();
                                    objReadFiles.read_password_change();
                                    objReadFiles.read_traffic_points();
                                    objReadFiles.read_pre_def_location_points();
                                    objReadFiles.read_panic_messages();
                                    objReadFiles.read_special_msg_info();
                                    objReadFiles.read_reset_data();
                                    objReadFiles.read_driver_names();
                                    objReadFiles.read_conductor_names();
                                    objReadFiles.read_label_names_details();
                                    objReadFiles.read_canElectricals_file();
                                    objReadFiles.read_canSafety_file();
                                    objReadFiles.read_canEngine_file();
                                    objReadFiles.read_canTransmit_file();
                                    objReadFiles.read_canOthers_file();
                                    objReadDataFiles.read_canElectricals_file1();
                                    objReadDataFiles.read_canSafety_file1();
                                    objReadDataFiles.read_canEngine_file1();
                                    objReadDataFiles.read_canTransmit_file1();
                                    objReadDataFiles.read_canOthers_file1();

                                } catch (Exception ex) {
                                    publish("Error handling special file: " + ex.getMessage());
                                }
                            }
                        }
                    } catch (Exception ex) {
                        publish("Download Failure: " + ex.getMessage());
                    } finally {
                        if (channelSftp != null) {
                            channelSftp.disconnect();
                        }
                        if (session != null) {
                            session.disconnect();
                        }
                    }
                    return null;
                }

                @Override
                protected void process(List<String> chunks) {
                    for (String data : chunks) {
                    }
                }

                @Override
                protected void done() {
                }
            };
            sw1.execute();
        }

    }

    private static byte ppp_status_inc = 0;
    private static byte gsm_module_inc = 0;

    class clsPppExecuteThread extends Thread {

        public clsPppExecuteThread() {
            super();
        }

        clsReadFiles objReadFiles = new clsReadFiles();

        @Override
        public void run() {
            NetworkInterface nif;
            String[] portNames;
            boolean found;
            byte j;
            try {
                for (int i = 0; i < 60; i++) {
                    if (getGsmModuleOn() == true) {
                        //////System.out.println("2");
                        break;
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                //////System.out.println("3");
            }

            boolean net_up_first_time = false;
            byte restart_module_inc = 0;
            int wifi_inc = 0;
            String subscriber_name;
            boolean check_gprs_connected = false;

            byte chk_inc = 0;
            boolean check_gprs_connection_status;
            short google_conn_inc = 0;
            boolean google_conn_connected = false;
            //boolean check_gprs_connected = false;
            while (true) {

                if (getWifiConnect() == true) {
                    try {
                        ////System.out.println("4");
                        if (check_wifi_connectivity()) {
                            ////System.out.println(" IN wifi");
                            nif = NetworkInterface.getByName(WIFI_NETWORKINTERFACE_PATH);
                            if (nif == null) {

                                SwingUtilities.invokeLater(() -> {
                                    update_wifi_icon(false);
                                });

                                //runCmd("sudo ifconfig ppp0 down");
                                // runCmd("sudo nmcli radio wifi on");
                                runCmdNmcli("sudo ifconfig wlan0 up");
                                // uuid = get_wifi_UUID();
                                //  runCmd("nmcli connection up " + uuid);

                            } else if (!nif.isUp()) {
                                //////System.out.println("5");

                                SwingUtilities.invokeLater(() -> {
                                    update_wifi_icon(false);
                                });

                                // check_gprs_connected = false;
                                runCmdNmcli("sudo ifconfig wlan0 up");
                                // ////////System.out.println("wifi not up " + Calendar.getInstance().getTimeInMillis());
                            } else if (nif.isUp()) {
                                //////System.out.println("6");
                                wifi_inc = 0;
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        update_wifi_icon(true);
                                    });

                                } catch (Exception ex) {
                                }
                                Thread.sleep(60000);
                            }
                        } else {

                        }
                        wifi_inc++;
                        Thread.sleep(5000);
                        if (wifi_inc % 40 == 0) {
                            //////System.out.println("7");
                            wifi_inc = 0;
                            nmcli_commands_wifi();
                            Thread.sleep(10000);
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SocketException ex) {
                        Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    if (clsDefines.NETWORKMANAGER_GPRS == false) {
                        try {
                            nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);
                            if (nif == null) {
                                // ////System.out.println("ppp null " + Calendar.getInstance().getTimeInMillis());

                                check_gprs_connected = false;
                                google_conn_connected = false;
                                found = false;
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        update_mobile_icon(false);
                                    });
                                } catch (Exception ex) {
                                }
                                if (getGsmModuleOn() == true && getSimReady() == true) {
                                    for (j = 0; j < 3; j++) {
                                        atSerialWrite("AT+CREG?");
                                        atSerialWrite("AT+CGREG?");
                                        for (int x = 0; x < 50; x++) {
                                            if (getSimRegistered() == clsDefines.SIM_REGISTERED) {
                                                ////System.out.println("sim registered");
                                                // ////System.out.println("ppp null sim reg " + Calendar.getInstance().getTimeInMillis());

                                                restart_module_inc = 0;
                                                found = true;
                                                break;
                                            } else if (getSimRegistered() == clsDefines.SIM_REG_DENIED) {
                                                ////System.out.println("sim denied registered");

                                                found = true;
                                                break;
                                            }
                                            Thread.sleep(6000);

                                        }
                                        if (found == true) {
                                            break;
                                        }
                                    }
                                    if (found == false) {
                                        ////System.out.println(" GPRS not registered ....");

                                        if (restart_module_inc % 3 == 0) {
                                            objReadFiles.write_log_ppp_data("Restart GSM Module restart_module_inc ==3 ...");
                                            restart_module_ports_changed(false);
                                            // ////System.out.println("ppp null restart module " + Calendar.getInstance().getTimeInMillis());

                                        } else if (restart_module_inc == 2) {
                                            atSerialWrite("AT+CFUN=4\r\n");
                                            Thread.sleep(5000);
                                            atSerialWrite("AT+CFUN=1,1\r\n");
                                            // ////System.out.println("ppp null cfun " + Calendar.getInstance().getTimeInMillis());
                                        }
                                        Thread.sleep(10000);
                                        restart_module_inc++;
                                        objReadFiles.write_log_ppp_data("Restart GSM Module SIM not Registered for 3 minutes");
                                    } else if (found == true) {
                                        portNames = SerialPortList.getPortNames();
                                        if (portNames.length >= 6) //portIdentifier.isCurrentlyOwned()) {
                                        {
                                            ////System.out.println("portNames");
                                            found = false;
                                            for (j = 0; j < 5; j++) {
                                                if (nif == null) {
                                                    ////System.out.println("nif is null");
                                                } else if (nif.isUp()) {
                                                    // ////System.out.println("ppp null nif up " + Calendar.getInstance().getTimeInMillis());
                                                    break;
                                                } else {
                                                    runCmd("sudo ifconfig ppp0 up");
                                                }
                                                // ////System.out.println("ppp null load gprdscript " + Calendar.getInstance().getTimeInMillis());

                                                if (loadGprsScript("null") == 1) {
                                                    gsm_module_inc = 0;
                                                    found = true;
                                                    break;
                                                } else {
                                                    for (int x = 0; x < 20; x++) {
                                                        Thread.sleep(1000);
                                                        if (nif == null) {
                                                        } else if (nif.isUp()) {
                                                            gsm_module_inc = 0;
                                                            found = true;
                                                            break;
                                                        } else {
                                                            runCmd("sudo ifconfig ppp0 up");
                                                        }
                                                    }
                                                }
                                            }
                                            if (found == false && gsm_module_inc <= 3) {
                                                gsm_module_inc++;
                                                objReadFiles.write_log_ppp_data("Restarta GSM Module Not Connecting GPRS...");
                                                restart_module_ports_changed(false);
                                            } else if (found == false) {
                                                gsm_module_inc = 0;
                                                //switch network
                                                // // ////System.out.println("not connecting to server ...");
                                                subscriber_name = clsSharedVariables.getNetworkOperatorName();
                                                // // ////System.out.println(subscriber_name);
                                                objReadFiles.write_log_ppp_data("subscriber_name  " + subscriber_name);
                                            }
                                        } else {
                                            gsm_module_inc++;
                                            if (gsm_module_inc > 60) {
                                                gsm_module_inc = 0;
                                                ppp_status_inc = 0;
                                                //restart module
                                                objReadFiles.write_log_ppp_data("Ports Low RESTART GSM MODULE AFTER RETRY 6 TIMES");
                                                restart_module_inc++;
                                                restart_module_ports_changed(false);

                                                for (int x = 0; x < 30; x++) {
                                                    if (nif == null) {
                                                    } else if (nif.isUp()) {
                                                        gsm_module_inc = 0;
                                                        found = true;
                                                        break;
                                                    } else {
                                                        runCmd("sudo ifconfig ppp0 up");
                                                    }
                                                    Thread.sleep(1000);
                                                }
                                            }
                                        }
                                    }
                                } else if (getGsmModuleOn() == false) {
                                    gsm_module_inc++;
                                    if (gsm_module_inc > 60) {
                                        gsm_module_inc = 0;
                                        ppp_status_inc = 0;
                                        //restart module
                                        objReadFiles.write_log_gprs_connectivity("ppp: Restart Module ");
                                        restart_module_inc++;
                                        restart_module_ports_changed(false);

                                        for (int x = 0; x < 30; x++) {
                                            Thread.sleep(1000);
                                        }
                                    }
                                }

                            } else if (nif.isUp() == false) {
                                try {
                                    // ////System.out.println("nif is not up");
                                    SwingUtilities.invokeLater(() -> {
                                        update_mobile_icon(false);
                                    });
                                } catch (Exception ex) {
                                }
                                check_gprs_connected = false;
                                google_conn_connected = false;
                                if (getGsmModuleOn() == true && getSimReady() == true) {
                                    // ////System.out.println("ppp not up " + Calendar.getInstance().getTimeInMillis());

                                    found = false;
                                    runCmd("sudo ifconfig ppp0 up");
                                    runCmd("sudo ifconfig ppp0 up");

                                    for (j = 0; j < 3; j++) {
                                        atSerialWrite("AT+CGREG?");
                                        for (int x = 0; x < 50; x++) {
                                            if (getSimRegistered() == clsDefines.SIM_REGISTERED) {
                                                // ////System.out.println("ppp not up SIM REGISTERED " + Calendar.getInstance().getTimeInMillis());

                                                found = true;
                                                break;
                                            } else {
                                                // ////System.out.println("ppp not up SIM denied " + Calendar.getInstance().getTimeInMillis());

                                            }

                                            Thread.sleep(6000);
                                        }
                                        if (found == true) {
                                            break;
                                        }
                                    }
                                    if (found == false) {
                                        if (restart_module_inc % 3 == 0) {
                                            objReadFiles.write_log_ppp_data("Restart nif not null GSM Module restart_module_inc ==3 ...");
                                            // ////System.out.println("ppp not up restart_module_inc " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                            restart_module_ports_changed(false);
                                        } else {
                                            atSerialWrite("AT+CFUN=4\r\n");
                                            Thread.sleep(5000);
                                            atSerialWrite("AT+CFUN=1,1\r\n");
                                            // ////System.out.println("ppp not up CFUN " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                        }
                                        restart_module_inc++;
                                        objReadFiles.write_log_ppp_data("nif.isUp() Restart GSM Module SIM not Registered for 3 minutes");
                                    } else if (found == true) {
                                        if (nif.isUp() == false) {

                                            found = false;
                                            for (j = 0; j < 5; j++) {
                                                // ////System.out.println("ppp not up check gprsscript " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                                if (loadGprsScript("null") == 1) {
                                                    // ////System.out.println("ppp not up check gprsscript true " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                                    found = true;
                                                    break;
                                                } else {
                                                    for (int x = 0; x < 20; x++) {

                                                        if (nif.isUp()) {
                                                            // ////System.out.println("ppp not up check gprsscript fail and nif up " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                                            found = true;
                                                            break;
                                                        }
                                                        Thread.sleep(5000);
                                                    }
                                                }
                                            }
                                            if (found == false && gsm_module_inc <= 3) {
                                                gsm_module_inc++;
                                                objReadFiles.write_log_ppp_data("nif.isUp() Restart GSM Module Not Connecting GPRS...");
                                                restart_module_ports_changed(false);
                                                // ////System.out.println("ppp not up check gprsscript fail and restart_module_ports_changed " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                            } else if (found == false) {
                                                gsm_module_inc = 0;
                                            }
                                        }
                                    } else if (getGsmModuleOn() == false) {
                                        gsm_module_inc++;
                                        if (gsm_module_inc > 60) {
                                            gsm_module_inc = 0;
                                            ppp_status_inc = 0;
                                            //restart module
                                            objReadFiles.write_log_gprs_connectivity_ip2("ppp: Restart Module ");
                                            restart_module_ports_changed(false);
                                            // ////System.out.println("ppp not up check gprsscript fail and getGsmModuleOn false  " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                            for (int x = 0; x < 30; x++) {
                                                Thread.sleep(1000);
                                                if (nif.isUp()) {
                                                    // ////System.out.println("ppp not up check gprsscript fail and getGsmModuleOn nif up " + restart_module_inc + Calendar.getInstance().getTimeInMillis());

                                                    found = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException ex) {
                                    }
                                } else {
                                    // ////System.out.println("ppp not up " + getGsmModuleOn() + getSimReady() + Calendar.getInstance().getTimeInMillis());
                                }

                            } else if (nif.isUp() == true) {
                                try {
                                    // ////System.out.println("nif is  up");
                                    SwingUtilities.invokeLater(() -> {
                                        update_mobile_icon(true);
                                    });
                                } catch (Exception ex) {
                                }
                                if (!net_up_first_time) {
                                    net_up_first_time = true;
                                    runCmd("sudo hwclock -w");
                                }

                                check_gprs_connection_status = false;
                                if (clsSharedVariables.getIpAddr1Enable()) {
                                    if (clsSharedVariables.getServerConnectImg() == SERVER_CONNECTED) {
                                        //////System.out.println("ip addr 1 server connected");
                                        check_gprs_connection_status = true;
                                    }
                                }
                                if (check_gprs_connection_status == false) {
                                    if (clsSharedVariables.getIpAddr2Enable()) {
                                        if (clsSharedVariables.getServerConnectImg2() == SERVER_CONNECTED) {
                                            check_gprs_connection_status = true;
                                        }
                                    }
                                }
                                if (check_gprs_connection_status == false) {
                                    if (clsSharedVariables.getIpAddr3Enable()) {
                                        if (clsSharedVariables.getServerConnectImg3() == SERVER_CONNECTED) {
                                            check_gprs_connection_status = true;
                                        }
                                    }
                                }
                                if (check_gprs_connection_status == false) {
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        if (clsSharedVariables.getServerConnectImg4() == SERVER_CONNECTED) {
                                            check_gprs_connection_status = true;
                                        }
                                    }
                                }
                                if (check_gprs_connection_status == false && google_conn_connected == false) {
                                    if (clsSharedVariables.getIpAddr1Enable() || clsSharedVariables.getIpAddr2Enable()
                                            || clsSharedVariables.getIpAddr3Enable() || clsSharedVariables.getIpAddr4Enable()) {

                                        google_conn_inc++;
                                        if (google_conn_inc > 5 * 60) {
                                            google_conn_inc = 0;
                                            google_conn_connected = false;
                                            // ////System.out.println("google check again");

                                        } else if (google_conn_connected == false) {
                                            google_conn_connected = check_google_connection() != false;
                                            // ////System.out.println("ppp nif up check_google_connection ip enable " + check_gprs_connected + Calendar.getInstance().getTimeInMillis());

                                            chk_inc++;
                                            if (chk_inc > 5 && check_gprs_connected == false) {
                                                chk_inc = 0;
                                                // ////System.out.println("ppp nif up check_google_connection blk and unblk " + check_gprs_connected + Calendar.getInstance().getTimeInMillis());

                                                runCmd("sudo ifconfig ppp0 down");
                                                runCmd("sudo rfkill block wifi");
                                                runCmd("sudo ifconfig wlan0 down");
                                                runCmd("sudo ifconfig wwan0 down");
                                                runCmd("sudo ifconfig ppp0 up");
                                                Thread.sleep(10000);
                                            }
                                        }
                                    }
                                }
                                Thread.sleep(60000);
                            }
                        } catch (SocketException ex) {
                            Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        ////System.out.println("8");
                        //// //////System.out.println("ppp " + Calendar.getInstance().getTimeInMillis());
                        if (check_ppp0_connectivity() == true) {
                            try {
                                gsm_module_inc = 0;

                                //gprs is up
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        try {
                                            update_mobile_icon(true);
                                        } catch (Exception ex) {

                                        }
                                    });
                                } catch (Exception ex) {
                                }
                                if (!net_up_first_time) {
                                    net_up_first_time = true;
                                    runCmd("sudo hwclock -w");
                                }
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                //up wwan0
                                try {
                                    SwingUtilities.invokeLater(() -> {
                                        try {
                                            update_mobile_icon(false);
                                        } catch (Exception ex) {

                                        }
                                    });
                                } catch (Exception ex) {
                                }
                                // uuid = get_ppp_UUID();
                                //  runCmd("nmcli connection up " + uuid);
                                Thread.sleep(5000);
                                gsm_module_inc++;
                                if (gsm_module_inc % 40 == 0) {
                                    runCmd("sudo nmcli radio wwan on");
                                    runCmd("sudo nmcli radio wwan on");

                                    nmcli_commands_gsm();

                                    Thread.sleep(10000);
                                } else if (gsm_module_inc > 110) {
                                    //////System.out.println("9");
                                    gsm_module_inc = 0;

                                    //restart module
                                    objReadFiles.write_log_ppp_data("Ports Low RESTART GSM MODULE AFTER RETRY 6 TIMES");
                                    restart_module_inc++;
                                    restart_module_ports_changed(false);
                                    nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);

                                    for (int x = 0; x < 30; x++) {
                                        if (nif == null) {
                                        } else if (nif.isUp()) {
                                            gsm_module_inc = 0;

                                            break;
                                        } else {

                                        }
                                        Thread.sleep(1000);
                                    }
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SocketException ex) {
                                Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {

                }
            }

        }

        private void nmcli_commands_gsm() {
            File f = new File("/etc/NetworkManager/system-connections");
            File[] str = f.listFiles();
            String[] split_str;
            int i;
            try {
                if (str != null) {

                    for (i = 0; i < str.length; i++) {
                        if (str[i].getName().startsWith("GSM")) {
                            // //////System.out.println("file1  :" + str[i].getName());
                            split_str = str[i].getName().split("\\.", -1);
                            // //////System.out.println("file name  :" + split_str.length);
                            runCmdNmcli("sudo nmcli con delete " + split_str[0]);
                            // //////System.out.println("file name  :" + split_str[0]);
                            str[i].delete();
                        }
                    }
                }

            } catch (Exception ex) {
                // //////System.out.println("Error  :" + ex.getMessage());
            }

            ////////System.out.println("length :" + f.listFiles().length);
            runCmdNmcli("sudo nmcli connection add type gsm apn " + clsSharedVariables.apn + " ifname cdc-wdm0 con-name GSM save yes autoconnect yes");

            runCmdNmcli("sudo chmod -R 777 /etc/NetworkManager/system-connections");

        }

        private void nmcli_commands_wifi() {
            File f = new File("/etc/NetworkManager/system-connections");
            File[] str = f.listFiles();
            String[] split_str;
            int i;
            try {
                if (str != null) {

                    for (i = 0; i < str.length; i++) {
                        if (str[i].getName().startsWith("WIRELESS")) {
                            // //////System.out.println("file1  :" + str[i].getName());
                            split_str = str[i].getName().split("\\.", -1);
                            // //////System.out.println("file name  :" + split_str.length);
                            runCmdNmcli("sudo nmcli con delete " + split_str[0]);
                            // //////System.out.println("file name  :" + split_str[0]);
                            str[i].delete();
                        }
                    }
                }

            } catch (Exception ex) {
                // //////System.out.println("Error  :" + ex.getMessage());
            }

            runCmdNmcli("sudo ifconfig wlan0 up");
            runCmdNmcli("sudo ifconfig wlan0 up");
            runCmdNmcli("sudo nmcli device wifi connect \"" + clsSharedVariables.wifi_ssid + "\" password " + clsSharedVariables.wifi_password + " name WIRELESS");

            //////System.out.println(" in clsGprsService sudo nmcli device wifi connect \"" + clsSharedVariables.wifi_ssid + "\" password " + clsSharedVariables.wifi_password + " name WIRELESS");
            runCmdNmcli("sudo chmod -R 777 /etc/NetworkManager/system-connections");

        }

        private boolean runCmdNmcli(String cmd) {
            Process process = null;
            Runtime rt = null;
            try {
                rt = Runtime.getRuntime();
                process = rt.exec(cmd);
                process.waitFor(60, TimeUnit.SECONDS);

                return true;
            } catch (IOException e) {
                if (rt != null) {
                    rt.gc();
                }
                return false;
            } catch (Exception ex) {
                if (rt != null) {
                    rt.gc();
                }
                return false;
            } finally {
                try {
                    if (process != null) {
                        process.getInputStream().close();
                        process.getOutputStream().close();
                        process.getErrorStream().close();
                        process.destroy();
                        process = null;
                        rt = null;
                    }
                } catch (IOException e) {
                } catch (Exception ex) {
                }
            }
        }

        private boolean check_wifi_connectivity() {
            BufferedReader br = null;
            String line;
            File file = new File(WIFI_CHECK_PATH);
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        return line.contains("up");
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }

                        line = null;
                        br = null;
                        file = null;

                    } catch (IOException e) {

                    }
                }

            } else {
                return false;
            }
            return false;
        }

        private byte loadGprsScript(String state) {
            try {
                String port_name = "";
                String[] portNames = new String[10];
                byte port_inc = 0;
                byte k;
                File filegprslock;
                File[] filesgprslock;

                try {
                    File f = new File("/sys/bus/usb-serial/drivers/option1");
                    File[] files = f.listFiles();
                    Arrays.sort(files);
                    for (File file : files) {
                        if (file.getName().contains("ttyUSB")) {
                            portNames[port_inc++] = "/dev/" + file.getName();
                        }
                    }
                } catch (Exception ex) {
                }

                if (port_inc >= 4) {
                    port_name = portNames[clsDefines.PPP_SERIAL_PORT_SERIAL_NO];
                    ////System.out.println("gprs port number" +clsDefines.PPP_SERIAL_PORT_SERIAL_NO + port_name );

                    objReadFiles.write_log_ppp_data("GPRS PORT path " + port_name.charAt(port_name.length() - 1));
                } else {
                    ////System.out.println("gprs port number  in else block" +clsDefines.PPP_SERIAL_PORT_SERIAL_NO + port_name);
                }
                ppp_status_inc++;

                if (ppp_status_inc >= 18 && get_voice_call_enabled_status() == false
                        && getSimRegistered() == clsDefines.SIM_REGISTERED && getSimReady() == true) {
                    ppp_status_inc = 0;

                    //restart module
                    objReadFiles.write_log_ppp_data("RESTART GSM MODULE AFTER 18 RETRIES");
                    // ////System.out.println("loadgprsscript ppp_status_inc 18 " + Calendar.getInstance().getTimeInMillis());

                    restart_module_ports_changed(false);

                    for (int x = 0; x < 30; x++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    try {
                        File f = new File("/sys/bus/usb-serial/drivers/option1");
                        File[] files = f.listFiles();
                        Arrays.sort(files);
                        for (File file : files) {
                            if (file.getName().contains("tty")) {

                                portNames[port_inc++] = "/dev/" + file.getName();
                            }
                        }
                        if (port_inc >= 4) {
                            port_name = portNames[clsDefines.PPP_SERIAL_PORT_SERIAL_NO];
                        }
                    } catch (Exception ex) {
                    }
                } else if ((ppp_status_inc == 5 || ppp_status_inc == 10 || ppp_status_inc == 15)
                        && get_voice_call_enabled_status() == false
                        && getSimRegistered() == clsDefines.SIM_REGISTERED && getSimReady() == true) {

                    try {
                        // ////System.out.println("loadgprsscript KillGprsScript " + ppp_status_inc + Calendar.getInstance().getTimeInMillis());

                        objReadFiles.write_log_ppp_data("PPP  FUNCTION OFF");
                        KillGprsScript();
                        String str;
                        str = "AT+CFUN=0\r\n";
                        atSerialWrite(str);
                        Thread.sleep(5000);
                        str = "AT+CFUN=1\r\n";
                        atSerialWrite(str);
                        Thread.sleep(10000);
                        str = null;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // runCmd("sudo ifconfig wwan0 down \n");
                Thread.sleep(5000);
                try {
                    if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
                        filegprslock = new File(clsDefines.GPRS_LOCK_FILE);

                        if (filegprslock.exists()) {
                            filesgprslock = filegprslock.listFiles();
                            for (int i = 0; i < filesgprslock.length; i++) {
                                filesgprslock[i].delete();
                            }
                        } else {
                            filegprslock.mkdirs();
                            runCmd("sudo chmod 777 -R " + clsDefines.GPRS_LOCK_FILE);
                        }
                    }
                } catch (Exception ex) {

                }

                String cmd = "sudo sh " + clsDefines.QUECTEL_PPP_SCRIPT + " " + port_name + " " + clsSharedVariables.apn + "\n";
                objReadFiles.write_log_ppp_data("STARTING GPRS   " + port_name.charAt(port_name.length() - 1));
                Runtime rt = null;
                ////System.out.println("loadgprsscript  " + cmd + Calendar.getInstance().getTimeInMillis());

                try {
                    rt = Runtime.getRuntime();
                    try {
                        gprsScriptprocess = rt.exec(cmd);
                        objThr = new OutStreamPPPThread();
                        objThr.start();

                        rt.exec("sudo stty sane -F " + port_name);
                        ////System.out.println("gprs watr for");
                        gprsScriptprocess.waitFor();

                        // runCmd("sudo cat /etc/resolv.conf");
                        for (k = 0; k < 3; k++) {
                            File f = new File(PPP_CHECK_PATH);
                            if (f.exists()) {

                                ////System.out.println("loadgprsscript PPP_CHECK_PATH true " + +Calendar.getInstance().getTimeInMillis());
                                break;
                            } else {
                                ////System.out.println("loadgprsscript PPP_CHECK_PATH false " + +Calendar.getInstance().getTimeInMillis());

                            }
                            Thread.sleep(10000);
                        }
                        //  runCmd("sudo cat /etc/resolv.conf");

                        NetworkInterface nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);
                        for (k = 0; k < 10; k++) {
                            if (nif == null) {
                                // ////System.out.println("loadgprsscript nif null " + +Calendar.getInstance().getTimeInMillis());

                            } else if (!nif.isUp()) {
                                // ////System.out.println("loadgprsscript nif down " + +Calendar.getInstance().getTimeInMillis());

                                runCmd("sudo ifconfig ppp0 up");
                            } else if (nif.isUp()) {
                                // ////System.out.println("loadgprsscript nif up " + +Calendar.getInstance().getTimeInMillis());

                                if (check_google_connection() == false) {
                                    // ////System.out.println("loadgprsscript nif check_google_connection false " + +Calendar.getInstance().getTimeInMillis());

                                    // runCmd("sudo echo \"nameserver 8.8.8.8\" | sudo tee /etc/resolv.conf");
                                    Thread.sleep(5000);
                                } else {

                                    try {
                                        SwingUtilities.invokeLater(() -> {

                                            update_mobile_icon(true);

                                        });
                                    } catch (Exception ex) {
                                    }
                                    // ////System.out.println("loadgprsscript nif check_google_connection true " + +Calendar.getInstance().getTimeInMillis());

                                    return 1;
                                }

                            }
                        }
                        objReadFiles.write_log_ppp_data("EXITING GPRS   ");

                    } catch (InterruptedException ex) {
                        //Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 3;
                } catch (IOException e) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, e);
                    return 4;

                } catch (Exception ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                    return 4;

                } finally {
                    try {
                        if (gprsScriptprocess != null) {
                            rt.freeMemory();
                            rt.gc();
                            filegprslock = null;
                            filegprslock = null;
                            gprsScriptprocess.getInputStream().close();
                            gprsScriptprocess.getOutputStream().close();
                            gprsScriptprocess.getErrorStream().close();
                            gprsScriptprocess.destroy();
                            gprsScriptprocess = null;
                            objThr.interrupt();
                            objThr = null;
                            //set_gprs_connected_status(false);
                            objReadFiles.write_log_ppp_data("CLOSING GPRS   ");
                            // pppfilepath = null;
                            cmd = null;
                            rt = null;

                        }
                    } catch (IOException ex) {
                        //Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 5;
        }

        private boolean check_ppp0_connectivity() {
            BufferedReader br = null;
            String line;
            File file = new File(PPP_CHECK_PATH_OPER_STATE);
            if (file.exists()) {
                try {
                    file.setReadable(true, false);
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (line.contains("down")) {
                            return false;
                        } else {
                            br.close();
                            br = null;
                            // check PPP_CHECK_PATH_CARRIER_STATE
                            file = new File(PPP_CHECK_PATH_CARRIER_STATE);
                            if (file.exists()) {
                                file.setReadable(true, false);
                                br = new BufferedReader(new FileReader(file));
                                while ((line = br.readLine()) != null) {
                                    return line.length() > 0;
                                }
                            }

                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }

                        line = null;
                        br = null;
                        file = null;

                    } catch (IOException e) {

                    }
                }

            }
            return false;
        }

        private String get_ppp_UUID() {
            BufferedReader br = null;
            String line;
            String[] split_str;
            File[] files_list;
            byte i = 0;
            File file = new File(GET_NETWORK_UUID_PATH);
            if (file.exists()) {
                try {
                    files_list = file.listFiles();
                    if (files_list != null && files_list.length > 0) {
                        for (i = 0; i < files_list.length; i++) {
                            if (files_list[i].getName().contains("GSM")) {
                                // //////System.out.println("UUID file name : " + files_list[i]);
                                br = new BufferedReader(new FileReader(files_list[i]));
                                while ((line = br.readLine()) != null) {
                                    if (line.contains("uuid")) {
                                        split_str = line.split("=");
                                        if (split_str.length > 1) {
                                            //  //////System.out.println("UUID : " + split_str[1].trim());
                                            return split_str[1].trim();
                                        }

                                    }
                                }
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }

                        line = null;
                        br = null;
                        file = null;

                    } catch (IOException e) {

                    }
                }

            }
            return "";
        }

        // Thread that reads process error output
        class OutStreamPPPThread extends Thread {

            NetworkInterface nif = null;
            BufferedReader reader = null;

            public OutStreamPPPThread() {
                super();
            }

            @Override
            public void run() {
                String data;
                byte status = 0;
                try {

                    nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);

                    reader = new BufferedReader(new InputStreamReader(gprsScriptprocess.getInputStream()));
                    while ((data = reader.readLine()) != null) {

                        if (data.contains("local  IP address")) {
                            status = GPRS_CONNECTED;

                        } else if (data.contains("disconnect")) {
                            // break;
                        }
                        if (data.contains("ip-up finished")) {
                            status = GPRS_CONNECTED;
                            //set_gprs_connected_status(true);

                        }
                        if (data.contains("remote IP address")) {
                            //status = GPRS_REMOTE_IP;

                        }
                        if (data.contains("primary DNS address")) {
                            // status = GPRS_PRIMARY_DNS;

                            //  set_gprs_connected_status(true);
                        }
                        if (data.contains("secondary DNS address")) {

                        }
                        if (data.contains("ip-up started")) {
                            // status = GPRS_SEC_DNS;

                            status = GPRS_FINALLY_CONNECTED;
                            //set_gprs_connected_status(true);
                            break;
                        }
                        if (data.contains("Modem hangup")) {
                            // status = GPRS_SEC_DNS;
                            status = GPRS_DISCONNECTED;
                            // set_gprs_connected_status(false);
                            break;
                        }
                        if (data.contains("Connection terminated")) {
                            // status = GPRS_SEC_DNS;
                            status = GPRS_DISCONNECTED;
                            // set_gprs_connected_status(false);
                            break;
                        }
                        if (data.contains("ip-down")) {
                            // status = GPRS_SEC_DNS;
                            status = GPRS_DISCONNECTED;
                            // set_gprs_connected_status(false);
                            break;
                        }
                        if (data.contains("lock")) {
                            // status = GPRS_SEC_DNS;
                            // status = GPRS_CONNECTED;
                            // set_gprs_connected_status(false);
                            // break;
                        }

                        if (nif != null) {
                            if (nif.isUp()) {
                                status = GPRS_CONNECTED;
                                // set_gprs_connected_status(true);
                                break;
                            }
                        }
                    }
                    if (status == GPRS_DISCONNECTED) {
                        objReadFiles.write_log_ppp_data("GPRS DISCONNECTED : KILL SCRIPT ");
                        //  //System.out.println("Kill Gprs OutStreamPPPThread ");
                        KillGprsScript();
                    }

                } catch (IOException e) {

                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            //Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        }

        private boolean check_google_connection() {
            if (clsSharedVariables.getEmbNorSimType() == clsDefines.EMBEDDED_SIM) {
                return true;
            }
            Socket google_socket = null;
            try {
                google_socket = new java.net.Socket();
                google_socket.connect(new InetSocketAddress("8.8.8.8", 443), 1000);
                return true;
            } catch (UnknownHostException ex) {
                // runCmd("sudo cat /etc/resolv.conf");
                // //////System.out.println("check_google_connection UnknownHostException " + Calendar.getInstance().getTimeInMillis());
                return false;
            } catch (IOException ex) {
                // ////////System.out.println("check_google_connection io Exc " + Calendar.getInstance().getTimeInMillis() + ex.getMessage());
                // runCmd("sudo ifconfig wwan0 down");
                return false;
            } finally {
                if (google_socket != null) {
                    try {
                        google_socket.close();
                    } catch (IOException ex) {
                    }
                    google_socket = null;
                }
            }
        }

    }

    /**
     * Kills the GPRS script process.
     *
     * @return true if the process was successfully killed, false otherwise.
     */
    public static synchronized boolean KillGprsScript() {
        int data;
        StringBuilder sb = new StringBuilder();

        // Command to execute the script that kills the GPRS script
        Process kill_proc = null;
        String cmd = "sudo sh " + clsDefines.QUECTEL_KILL_PPP_SCRIPT + "\n";

        Runtime rt = null;
        DataInputStream is = null;
        try {
            rt = Runtime.getRuntime();
            kill_proc = rt.exec(cmd);

            is = new DataInputStream(kill_proc.getInputStream());

            // Read the output stream of the process
            while ((data = is.read()) != -1) {
                sb.append(data);
            }

            // Sleep for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                // Handle interruption exception
            }
            return true; // Process successfully killed
        } catch (IOException e) {
            return false; // Unable to kill process due to IO exception
        } catch (Exception ex) {
            return false; // Other exceptions occurred during process execution
        } finally {
            try {
                // Cleanup resources
                if (kill_proc != null) {
                    long freeMemory = rt.freeMemory();
                    rt.gc();
                    kill_proc.getInputStream().close();
                    kill_proc.getOutputStream().close();
                    kill_proc.getErrorStream().close();
                    kill_proc.destroy();
                    kill_proc = null;
                    rt = null;
                    cmd = null;
                }
            } catch (IOException e) {
                // Handle IO exception during cleanup
            } catch (Exception ex) {
                // Handle other exceptions during cleanup
            }
        }
    }

    /**
     * Displays a message dialog box with a specified message and auto-closes it
     * after 5 seconds.
     *
     * @param data The message to display in the dialog box.
     */
    private void show_message_dialogbox(String data) {
        try {
            final JLabel label = new JLabel();
            label.setText(data);
            int timerDelay = 1000;

            // Timer to close the dialog box after 5 seconds
            new javax.swing.Timer(timerDelay, new ActionListener() {
                int timeLeft = 5;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeLeft > 0) {
                        timeLeft--;
                    } else {
                        ((javax.swing.Timer) e.getSource()).stop();
                        Window win = SwingUtilities.getWindowAncestor(label);
                        win.setVisible(false);
                        win = null;
                    }
                }
            }) {
                {
                    setInitialDelay(0);
                }
            }.start();

            // Display the message dialog box
            JOptionPane.showMessageDialog(null, label);
        } catch (HeadlessException ex) {
            // Handle headless environment exception
        }
    }

    /**
     * Closes the GPRS service by interrupting associated threads. Stops
     * multiple GSM state machines and interrupts the PPP thread if active.
     */
    public static void close_gprs_service() {
        try {
            // Check and interrupt GSM state machine for IP Address 1
            if (clsSharedVariables.getIpAddr1Enable()) {
                if (objGsmStateMachine != null) {
                    objGsmStateMachine.interrupt();
                    objGsmStateMachine = null;
                }
            }
        } catch (Exception ex) {
            // Handle exception while closing IP Address 1 GSM state machine
        }

        try {
            // Check and interrupt GSM state machine for IP Address 2
            if (clsSharedVariables.getIpAddr2Enable()) {
                if (objGsmStateMachine2 != null) {
                    objGsmStateMachine2.interrupt();
                    objGsmStateMachine2 = null;
                }
            }
        } catch (Exception ex) {
            // Handle exception while closing IP Address 2 GSM state machine
        }

        

        

       

        try {
            // Check and interrupt PPP thread
            if (objPppThread != null) {
                objPppThread.interrupt();
                objPppThread = null;
            }
        } catch (Exception ex) {
            // Handle exception while closing PPP thread
        }
    }

}
