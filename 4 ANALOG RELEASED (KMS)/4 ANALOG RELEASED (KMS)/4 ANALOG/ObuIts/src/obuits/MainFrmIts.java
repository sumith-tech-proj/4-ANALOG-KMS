package obuits;

import java.io.InputStreamReader;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static obuits.ClsSchedule.sch_state;
import static obuits.MainFrmIts.objAtPort;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.Strings.*;
import static obuits.clsDefines.*;
import static obuits.clsSharedVariables.*;
import static obuits.clsSwitchStates.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.xml.soap.SOAPException;
import jssc.SerialPortList;
import static obuits.GPSLocationService.close_gps_serial_port;
import static obuits.PanCameraPlay.play_process_id;
import static obuits.PanCameraPlay.play_video_process;
import static obuits.PanDisplayBoardDiag.setDisBrdDelPktCame;
import static obuits.PanVideo.lblCam1;
import static obuits.PanVideo.lblCam2;
import static obuits.PanVideo.lblCam3;
import static obuits.PanVideo.lblCam4;
import static obuits.PanelRoute.selected_trip_no;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsAtSerialPort.close_at_serial_port;
import static obuits.clsBusStopDetection.audio_playing;
//import static obuits.clsCanSerialPort.close_can_serialport;
//import static obuits.clsCanSerialPort1.close_can_serialport1;
import static obuits.clsDisplayBrdSerialPort.close_displayboard_serialport;

import static obuits.clsDisplayBrdSerialPort.objdisQue;
import static obuits.clsGprsService.GET_NETWORK_UUID_PATH;
import static obuits.clsInternalDisBrdMessage.fill_internal_details;
//import static obuits.clsPeopleCntEventClear.getHistoryDataFoorir;
//import static obuits.clsPeopleCntEventClear.peopleCountRouteEnd;
//import static obuits.clsRs232Data.close_rs232_serialport;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class MainFrmIts extends javax.swing.JFrame {

    private JPanel objPanel = null;
    JFrame frameDialogBox;
    public static clsAtSerialPort objAtPort;
  //  private static clsPeoplCountEvent objPeopleCnt;
  //  private static clsPeopleCountEventSecondCamera objPeopleCnt1;
    public static GPSLocationService objLocation;
    private static clsGprsService objGprs;
    public static byte lcd_monitoring_cnt = 0;
    public static long lcd_monitoring_date = 0;
    public static byte hardisk_monitoring_cnt = 0;
    public static long hardisk_monitoring_date = 0;
    public static PinState DigInp1CheckState;
    public static PinState DigInp2CheckState;
    public static PinState DigInp3CheckState;
    public static PinState DigInp4CheckState;
    JButton[] btnList = new JButton[9];
    private static boolean video_started = false;
    private static byte video_live_state = 0;
    private static byte pre_video_live_state = 0;
    private static boolean video_enable_started = false;
    private static boolean dig1_sos_started = false;
    private static boolean dig_stopreq_started = false;
    private static boolean dig_video_record_started = false;
    private static boolean dig_reverse_camera_started = false;
    private static boolean dig_left_arrow_started = false;
    private static boolean dig_right_arrow_started = false;
    static MainFrmIts.StateMachine objStateMachine = null;
    byte prev_video_state = 0;
    static clsVideoRecording objVideoRec1 = null;
    static clsVideoRecording objVideoRec2 = null;
    static clsVideoRecording objVideoRec3 = null;
    static clsVideoRecording objVideoRec4 = null;
    static clsVideoRecording objVideoRec5 = null;
    static clsVideoRecording objVideoRec6 = null;
    static clsVideoRecording objVideoRec7 = null;
    static clsVideoRecording objVideoRec8 = null;
    
    static clsPreEventREcord objPreEventRec1 = null;
    static clsPreEventREcord objPreEventRec2 = null;
    static clsPreEventREcord objPreEventRec3 = null;
    static clsPreEventREcord objPreEventRec4 = null;
    static clsPreEventREcord objPreEventRec5 = null;
    static clsPreEventREcord objPreEventRec6 = null;
    static clsPreEventREcord objPreEventRec7 = null;
    static clsPreEventREcord objPreEventRec8 = null;
    static clsCameraView objCamView1 = null;
    static clsCamera2View objCamView2 = null;
    static clsCamera3View objCamView3 = null;
    static clsCamera4View objCamView4 = null;
   
    private gpsDriving objDriving;
    private cls16833Protocols obj16833Pkts;
    static GpioPinDigitalOutput digOutGsmPowerPin;
    static GpioPinDigitalOutput digOutLcdResetPin;
    static GpioPinDigitalOutput digOutGsmResetPin;
    public static GpioPinDigitalOutput digOutAudioVoiceCallPin;
    public static GpioPinDigitalOutput digOutAudioSpeakerPin;
    public static GpioPinDigitalOutput digOutFMPin;
    public static GpioPinDigitalOutput digOutMicPin;
    public static GpioPinDigitalOutput digOutTest27Pin;
    static GpioPinDigitalOutput digOutPowerPin;
    static GpioPinDigitalOutput digOutPowerOffPin;
    public static GpioPinDigitalOutput digOutRs485Pin;
    // Input pins names
    static GpioPinDigitalInput digInIgnitionGpio;
    static GpioPinDigitalInput digInMainsGpio;
    static GpioPinDigitalInput digInMicStatusGpio;
    static GpioPinDigitalInput digInSosGpio;
    static GpioPinDigitalInput digInput2Gpio;
    static GpioPinDigitalInput digInput3Gpio;
    static GpioPinDigitalInput digInput4Gpio;
    static GpioPinDigitalInput tamperGpio;
    static GpioPinDigitalInput digInGsmStatusGpio;

    static Timer gsm_status_timer = null;
    static TimerTask gsm_status_timer_task = null;
    clsDisplayBrdSerialPort objDisBrdSerial = null;
//    clsCanSerialPort objCanSerial = null;
//    clsCanSerialPort1 objCanSerial1 = null;
    
    ClsSchedule objSchedule = null;
    static Timer sleep_mode_timer = null;
    static TimerTask sleep_mode_timer_task = null;
    public static clsTextSpeechTinker ObjTextSpeech;
    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
    public static boolean RESET_OBU_CALLED = false;
    ImageIcon imgAmbulance = (new javax.swing.ImageIcon(getClass().getResource("/Images/ambulance.png")));
   // public static clsFmRadioI2c objFmRadio;
    private static boolean mic_on = false;
    private static boolean tamper_on = false;
    public static byte savedVideoState = 0;
    volatile boolean reverseActive = false;

    public MainFrmIts() {

        clsReadFiles objReadFiles = new clsReadFiles();
        clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
        File[] out_path;
        File rec_path;
        clsDefines.device_power_on = true;
        int i;
        try {
            this.frameDialogBox = this;
            initComponents();
            jLayeredPane1.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT, 5, 5));
            imgHardDisk.setVisible(true);
            try {
                clsSystemInfo.getSystemInfo();
            } catch (Exception ex) {
            }
            try {
                try {
                    objReadFiles.read_product_storage_name();
                } catch (Exception ex) {
                }
                if (clsSharedVariables.getSharedNetwork() == true) {
                    clsDefines.CAM1_IPADDR = "10.42.0.3";
                    clsDefines.CAM2_IPADDR = "10.42.0.4";
                    clsDefines.CAM3_IPADDR = "10.42.0.5";
                    clsDefines.CAM4_IPADDR = "10.42.0.6";
                    clsDefines.CAM5_IPADDR = "10.42.0.7";
                    clsDefines.CAM6_IPADDR = "10.42.0.8";
                    clsDefines.CAM7_IPADDR = "10.42.0.9";
                    clsDefines.CAM8_IPADDR = "10.42.0.10";
                    clsDefines.CAM_ANALOG_IPADDR = "10.42.0.7";
                } else {
                    clsDefines.CAM1_IPADDR = "192.168.0.3";
                    clsDefines.CAM2_IPADDR = "192.168.0.4";
                    clsDefines.CAM3_IPADDR = "192.168.0.5";
                    clsDefines.CAM4_IPADDR = "192.168.0.6";
                    clsDefines.CAM5_IPADDR = "192.168.0.7";
                    clsDefines.CAM6_IPADDR = "192.168.0.8";
                    clsDefines.CAM7_IPADDR = "192.168.0.9";
                    clsDefines.CAM8_IPADDR = "192.168.0.10";
                    clsDefines.CAM_ANALOG_IPADDR = "192.168.0.7";
                }
            } catch (Exception ex) {
            }
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                update_time_tinkeros();
            }
            try {
                objReadFiles.read_fstab_file();
                objReadDataFiles.read_xdotool_path();
            } catch (Exception ex) {

            }
            gpio_pin_assignment();
            clsSharedVariables.setStorageType(clsDefines.STORAGE_HARDDISK);
            if (clsSharedVariables.getStorageType() == clsDefines.STORAGE_HARDDISK) {
                switch (clsDefines.OBU_BRD_TYPE) {
                    case RASPBERRY_BOARD:
                        clsDefines.obu_board_username = "pi";
                        clsDefines.NETWORKMANAGER_GPRS = true;
                        break;
                    case clsDefines.TINKER_BOARD:
                        clsDefines.obu_board_username = "linaro";
                        clsDefines.NETWORKMANAGER_GPRS = false;
                        break;
                    case clsDefines.BANANAPI_BOARD:
                        clsDefines.obu_board_username = "pi";
                        clsDefines.NETWORKMANAGER_GPRS = true;
                        break;
                    default:
                        clsDefines.obu_board_username = "linaro";
                        clsDefines.NETWORKMANAGER_GPRS = false;
                        break;
                }

                rec_path = new File("/dev/disk/by-uuid");
                out_path = rec_path.listFiles();
                if (out_path != null) {
                    for (i = 0; i < out_path.length; i++) {
                        try {
                            if (out_path[i].getName().endsWith("3339")) {
                                if (objReadFiles.check_harddisk_mount() == true) {
                                    clsSharedVariables.setHardDriveDetected(true);
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            updateHarddiskIcon();
                                        }
                                    });
                                }
                                break;
                            }

                        } catch (Exception ex) {

                        }

                    }
                }
                out_path = null;

            } else {
                clsSharedVariables.setHardDriveDetected(false);
                hardisk_monitoring_cnt = 0;
                objReadFiles.write_harddisk_monitoring_data();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        updateHarddiskIcon();
                    }
                });

            }
            rec_path = null;
        } catch (Exception ex) {

        }
        if (clsSharedVariables.getHardDriveDetected() == false) {
            objReadFiles.harddisk_mount();
        }
        if (objReadFiles.check_3339OBU_mount() == false) {
            objReadFiles.check_3339OBU_mount();
        }
        file_paths_assignment();
        try {
            if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
                objReadDataFiles.create_shell_file_gstreamer_close_script();
                objReadDataFiles.create_shell_file_gstreamer_cam1_script();
                objReadDataFiles.create_shell_file_gstreamer_cam2_script();
                objReadDataFiles.create_shell_file_gstreamer_cam3_script();
                objReadDataFiles.create_shell_file_gstreamer_cam4_script();
            } else {
                objReadDataFiles.create_shell_file_gstreamer_close_script();
            }
        } catch (Exception ex) {

        }
        objReadFiles.read_product_storage_name();
        objReadFiles.read_product_storage_name();
        objReadFiles.read_harddisk_monitoring_data();
//        objFmRadio = new clsFmRadioI2c();
        try {
            imgIgnition.setVisible(false);
            imgServerConn.setVisible(false);
            ButtonStop.setVisible(false);
            imgServerConn2.setVisible(false);
            imgServerConn3.setVisible(false);
            imgServerConn4.setVisible(false);
            imgServerConn5.setVisible(false);
            lblPeopleIn1.setVisible(false);
            lblPeopleIn2.setVisible(false);
            lblPeopleOut1.setVisible(false);
            lblPeopleOut2.setVisible(false);
            imgCan1.setVisible(false);
            imgNetwork.setVisible(false);
            imgMobile.setVisible(false);
            imgWifi.setVisible(false);
            imgEmergency.setText(" ");
            imgStopReq.setText(" ");
            imgSos.setVisible(false);
            imgDigI2.setVisible(false);
            imgDigI3.setVisible(false);
            imgDigI4.setVisible(false);
            lblUsb.setVisible(false);
            //  lblversion1.setText(clsDefines.SW_FIRMWARE_VERSION);
            jLayeredPaneLiveButtons.setVisible(false);
            GPIOWriteData objWriteGpio = new GPIOWriteData();
            objWriteGpio.start();

            try {
                if (objDriving == null) {
                    objDriving = new gpsDriving();
                }

                if (obj16833Pkts == null) {
                    obj16833Pkts = new cls16833Protocols();
                }

                PanCanConfig objCanConfig = new PanCanConfig();

                objCanConfig.init_can_electrical_systems();

                objCanConfig.init_can_engine();

                objCanConfig.init_can_safety();

                objCanConfig.init_can_transmission();

                objCanConfig.init_can_others();
                objCanConfig.init_can_electrical_systems1();

                objCanConfig.init_can_engine1();

                objCanConfig.init_can_safety1();

                objCanConfig.init_can_transmission1();

                objCanConfig.init_can_others1();

                objReadFiles.read_application_version_file();

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

                objReadFiles.read_device_dtc_counts_data();

                objReadFiles.read_emergency_map_data();
                objReadDataFiles.read_omxplayer_filepath();
                objReadDataFiles.read_forrir_rtsp_filepath();
                try {
                    objReadDataFiles.read_mpv_filepath();
                } catch (Exception ex) {

                }
                objReadFiles.read_lcd_touch_monitoring_data();
                objReadFiles.read_logOnvifenabled_data();
                objReadFiles.read_fmradio_status();
                objReadDataFiles.read_spel_audio_data_file();
                try {
                    objReadDataFiles.read_apc_stored_data();
                } catch (Exception ex) {
                }
                objReadFiles.read_imei_no_data();
                objReadFiles.read_cfg_data_file();
                objReadFiles.read_internal_battery_state();
                objReadFiles.read_embedded_switch_profile_data();
                clsOnvifUrlCamera onvifUrls = new clsOnvifUrlCamera();
                onvifUrls.read_cam_urls_file();
                onvifUrls = null;
                clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING = clsDefines.COMPANY_NAME_NMEA_PROT;

            } catch (Exception ex) {

            }

            init_functions();
        } catch (Exception ex) {

        }
        imgCanEmergency.setVisible(false);
        lbldriverid.setVisible(false);
        lblconductorid.setVisible(false);
        if (clsSharedVariables.driverLoginEnabled == true) {
            lbldriverid.setVisible(false);
            btnLogout.setEnabled(false);
            lblNetworkOperatorName.setText("");
            if (objPanel != null) {
                objPanel = null;
            }
            i = 0;

            btnList[i++] = btnHome;

            btnList[i++] = btnRoute;

            btnList[i++] = btnPhone;

            btnList[i++] = btnVideo;

            btnList[i++] = btnVhmd;

            btnList[i++] = btnConfig;

            btnList[i++] = btnfileManager1;

            btnList[i++] = btnDiagnosis;

            btnList[i++] = btnMessages;

            objPanel = new PanLogin(panCenterMainPane, panMainPane, btnLogout, btnList, PanLeftpane, lbldriverid);// PanelRoute(panMainPane);

            panMainPane.removeAll();

            panMainPane.setLayout(new java.awt.BorderLayout());

            panMainPane.add(objPanel);

            panMainPane.revalidate();

            panMainPane.repaint();

            PanLeftpane.setVisible(false);

            btnHome.setVisible(false);

        } else {

            btnLogout.setVisible(false);

            lblNetworkOperatorName.setText("");

        }

        if (getCanEnabled() == true) {
            this.btnCanAlarm.setEnabled(true);
        } else {
            this.btnCanAlarm.setEnabled(false);
        }

        if ((clsSharedVariables.getFmRadioEnable() == true)) {
            this.btnEmergency.setVisible(true);
        } else {
            if (clsSharedVariables.getEmergencyServicesEnable() == true) {
                this.btnEmergency.setVisible(true);
            } else {
                this.btnEmergency.setVisible(false);
            }
        }
        if (clsSharedVariables.getFrontEnable()) {
            lblFD.setVisible(true);
        } else {
            lblFD.setVisible(false);
        }

        if (clsSharedVariables.getSideEnable()) {
            lblSD.setVisible(true);
        } else {
            lblSD.setVisible(false);
        }

        if (clsSharedVariables.getRearEnable()) {
            lblRD.setVisible(true);
        } else {
            lblRD.setVisible(false);
        }

        if (clsSharedVariables.getIntEnable()) {
            lblID.setVisible(true);
        } else {
            lblID.setVisible(false);
        }

        if (clsSharedVariables.getArticulatedBus() == true) {
            if (clsSharedVariables.getArtSideEnable()) {
                lblSDArt.setVisible(true);
            } else {
                lblSDArt.setVisible(false);
            }

            if (clsSharedVariables.getArtIntEnable()) {
                lblIDArt.setVisible(true);
            } else {
                lblIDArt.setVisible(false);
            }

        } else {
            lblSDArt.setVisible(false);
            lblIDArt.setVisible(false);
        }

        if (clsSharedVariables.getCam1Enabled() == true) {
            MainFrmIts.imgCam1.setVisible(true);
        } else {
            MainFrmIts.imgCam1.setVisible(false);
        }

        if (clsSharedVariables.getCam2Enabled() == true) {

            MainFrmIts.imgCam2.setVisible(true);

        } else {

            MainFrmIts.imgCam2.setVisible(false);

        }

        if (clsSharedVariables.getCam3Enabled() == true) {

            MainFrmIts.imgCam3.setVisible(true);

        } else {

            MainFrmIts.imgCam3.setVisible(false);

        }

        if (clsSharedVariables.getCam4Enabled() == true) {
            MainFrmIts.imgCam4.setVisible(true);
        } else {
            MainFrmIts.imgCam4.setVisible(false);
        }

        if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_SOS) {
            imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
        } else if ((clsSharedVariables.getDigInp1Name()) == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
        } else if ((clsSharedVariables.getDigInp1Name()) == clsDefines.DIG_INPUT_STOPREQUEST) {
            imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_request.png")));
        } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_NONE) {
            imgSos.setVisible(false);
        }

        if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
            imgDigI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
        } else if ((clsSharedVariables.getDigInp2Name()) == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            imgDigI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
        } else if ((clsSharedVariables.getDigInp2Name()) == clsDefines.DIG_INPUT_NONE) {
            imgDigI2.setVisible(false);
        }

        if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_SOS) {
            imgDigI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
        } else if ((clsSharedVariables.getDigInp3Name()) == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            imgDigI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
        } else if ((clsSharedVariables.getDigInp3Name()) == clsDefines.DIG_INPUT_NONE) {
            imgDigI3.setVisible(false);
        }

        if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_SOS) {
            imgDigI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
        } else if ((clsSharedVariables.getDigInp4Name()) == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            imgDigI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
        } else if ((clsSharedVariables.getDigInp4Name()) == clsDefines.DIG_INPUT_NONE) {
            imgDigI4.setVisible(false);
        }

        imgTamper.setVisible(false);   
        FilesThread ft = new FilesThread();

        ft.start();
        ObjTextSpeech = new clsTextSpeechTinker("kevin16");
        if (clsSharedVariables.no_times_reset
                > 1000) {
            clsSharedVariables.no_times_reset = 0;
        }
        boolean found = false;

        if (clsSharedVariables.driverLoginEnabled == false) {

            objReadFiles.read_activity_name();

            byte act_type = getCurActivity();

            switch (act_type) {

                case clsDefines.MAIN_ACTIVITY:

                    objPanel = panCenterMainPane;

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.setVisible(true);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.ROUTE_ACTIVITY:

                    objPanel = new PanelRoute(panMainPane, panCenterMainPane);

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.VIDEO_ACTIVITY:

                    break;

                case clsDefines.CONFIG_ACTIVITY:

                    break;

                case clsDefines.DIAGNOSIS_ACTIVITY:

                    break;

                case clsDefines.PHONE_ACTIVITY:

                    objPanel = new PanPhone();

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.FILEFOLDER_ACTIVITY:
                    break;

                case clsDefines.VHMD_ACTIVITY:

                    objPanel = new PanCanDisplay(); //) panVhmd();

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.MESSAGES_ACTIVITY:

                    objPanel = new PanMessages();

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.EMERGENCY_ALERTS_ACTIVITY:

                    if ((clsSharedVariables.getFmRadioEnable() == true)) {

                      //  objPanel = new PanFmRadio();
                        btnEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/radio.png")));
                        btnEmergency.setText("<html>RADIO<br> रेडियो</html>");
                        panMainPane.removeAll();

                        panMainPane.setLayout(new java.awt.BorderLayout());

                        panMainPane.add(objPanel);

                        panMainPane.revalidate();

                        panMainPane.repaint();

                    } else if (clsSharedVariables.getEmergencyServicesEnable() == true) {

                        objPanel = new PanEmergencyMap();
                        btnEmergency.setIcon(imgAmbulance);
                        btnEmergency.setText("<html>EMERGENCY<br> रेडियो</html>");

                        panMainPane.removeAll();

                        panMainPane.setLayout(new java.awt.BorderLayout());

                        panMainPane.add(objPanel);

                        panMainPane.revalidate();

                        panMainPane.repaint();

                        found = true;
                    }
                    break;

                case clsDefines.CAN_ALERTS_ACTIVITY:

                    objPanel = new PanCanAlerts();

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.SOS_ALERTS_ACTIVITY:

                    objPanel = new PanSos();

                    panMainPane.removeAll();

                    panMainPane.setLayout(new java.awt.BorderLayout());

                    panMainPane.add(objPanel);

                    panMainPane.revalidate();

                    panMainPane.repaint();

                    found = true;

                    break;

                case clsDefines.TRIP_ACTIVITY:

                    break;

            }

        }

        if (found == false) {

            setCurActivity(clsDefines.MAIN_ACTIVITY);

            try {

                objReadFiles.write_activity_name(clsDefines.MAIN_ACTIVITY);

            } catch (Exception ex) {

            }

        }

        clsSharedVariables.reset_type = clsSharedVariables.act_reset_type;

        clsSharedVariables.act_reset_type = NORMAL_RESET;

        objReadFiles.write_reset_type();

        clsSharedVariables.no_times_reset = (short) (clsSharedVariables.no_times_reset + 1);

        objReadFiles.write_reset_data();

//        try {
//            if ((clsSharedVariables.getFmRadioEnable() == true)) {
//                if (clsSharedVariables.getFmRadioStatus() == true) {
//                    fmradio_speaker_on();
//                    objFmRadio.main("on");
//                } else {
//                    objFmRadio.main("off");
//                }
//            }
//        } catch (Exception ex) {
//        }
        objReadFiles.write_log_low_memory_data(
                "OBU Started");
        objReadFiles = null;

        this.repaint();

        this.pack();
    }

    static int ignition_off_cnt = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
    SimpleDateFormat sdf_format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    SimpleDateFormat sdf_video_year = new SimpleDateFormat("yyyy");

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jToolBar1 = new javax.swing.JToolBar();
        jPanel4 = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        lblNetworkOperatorName = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        PanFullPane = new javax.swing.JPanel();
        panMainPane = new javax.swing.JPanel();
        panCenterMainPane = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRoute = new javax.swing.JButton();
        btnPhone = new javax.swing.JButton();
        btnVideo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnVhmd = new javax.swing.JButton();
        btnConfig = new javax.swing.JButton();
        btnfileManager1 = new javax.swing.JButton();
        panThirdRow = new javax.swing.JPanel();
        btnDiagnosis = new javax.swing.JButton();
        btnMessages = new javax.swing.JButton();
        btnEmergency = new javax.swing.JButton();
        PanLeftpane = new javax.swing.JPanel();
        btnSos = new javax.swing.JButton();
        btnCanAlarm = new javax.swing.JButton();
        btnFire = new javax.swing.JButton();
        btnBreakDown = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        lblFD = new javax.swing.JLabel();
        lblSD = new javax.swing.JLabel();
        lblRD = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblSDArt = new javax.swing.JLabel();
        lblIDArt = new javax.swing.JLabel();
        imgCan = new javax.swing.JLabel();
        imgCan1 = new javax.swing.JLabel();
        imgSos = new javax.swing.JLabel();
        imgIgnition = new javax.swing.JLabel();
        imgHardDisk = new javax.swing.JLabel();
        imgGps = new javax.swing.JLabel();
        imgWifi = new javax.swing.JLabel();
        imgSimDetect = new javax.swing.JLabel();
        imgSigStr = new javax.swing.JLabel();
        imgNetwork = new javax.swing.JLabel();
        imgMobile = new javax.swing.JLabel();
        imgServerConn = new javax.swing.JLabel();
        imgServerConn2 = new javax.swing.JLabel();
        imgServerConn3 = new javax.swing.JLabel();
        imgServerConn4 = new javax.swing.JLabel();
        imgServerConn5 = new javax.swing.JLabel();
        imgDigI2 = new javax.swing.JLabel();
        imgDigI3 = new javax.swing.JLabel();
        imgDigI4 = new javax.swing.JLabel();
        imgTamper = new javax.swing.JLabel();
        lblPeopleOut2 = new javax.swing.JLabel();
        lblPeopleIn2 = new javax.swing.JLabel();
        imgCanEmergency = new javax.swing.JLabel();
        imgMic = new javax.swing.JLabel();
        lblPeopleIn1 = new javax.swing.JLabel();
        lblPeopleOut1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        imgEmergency = new javax.swing.JLabel();
        imgStopReq = new javax.swing.JLabel();
        lblUsb = new javax.swing.JLabel();
        lbldriverid = new javax.swing.JLabel();
        lblconductorid = new javax.swing.JLabel();
        jLayeredPaneLive = new javax.swing.JLayeredPane();
        imgCam1 = new javax.swing.JLabel();
        imgCam2 = new javax.swing.JLabel();
        imgCam3 = new javax.swing.JLabel();
        imgCam4 = new javax.swing.JLabel();
        jLayeredPaneLiveButtons = new javax.swing.JLayeredPane();
        btnLiveCamAll = new javax.swing.JButton();
        btnLiveCam1 = new javax.swing.JButton();
        btnLiveCam2 = new javax.swing.JButton();
        btnLiveCam3 = new javax.swing.JButton();
        btnLiveCam4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setName("frmMainIts"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel4.setLayout(null);

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel4.add(lblTime);
        lblTime.setBounds(606, 48, 190, 30);

        lblNetworkOperatorName.setBackground(new java.awt.Color(30, 61, 249));
        lblNetworkOperatorName.setFont(lblNetworkOperatorName.getFont().deriveFont(lblNetworkOperatorName.getFont().getStyle() | java.awt.Font.BOLD, lblNetworkOperatorName.getFont().getSize()+4));
        lblNetworkOperatorName.setForeground(new java.awt.Color(255, 255, 255));
        lblNetworkOperatorName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNetworkOperatorName.setText("Operator");
        lblNetworkOperatorName.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNetworkOperatorName.setPreferredSize(new java.awt.Dimension(250, 19));
        jPanel4.add(lblNetworkOperatorName);
        lblNetworkOperatorName.setBounds(390, 80, 80, 19);

        btnHome.setBackground(new java.awt.Color(30, 61, 89));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home.png"))); // NOI18N
        btnHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomebtnHomeActionPerformed(evt);
            }
        });
        jPanel4.add(btnHome);
        btnHome.setBounds(10, 10, 65, 57);

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(0, 51, 102));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png"))); // NOI18N
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogout.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        btnLogout.setInheritsPopupMenu(true);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel4.add(btnLogout);
        btnLogout.setBounds(10, 544, 64, 61);

        PanFullPane.setBackground(new java.awt.Color(245, 240, 225));
        PanFullPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 51), 1, true));
        PanFullPane.setOpaque(false);
        PanFullPane.setPreferredSize(new java.awt.Dimension(700, 450));

        panMainPane.setToolTipText("");
        panMainPane.setOpaque(false);
        panMainPane.setPreferredSize(new java.awt.Dimension(700, 430));

        panCenterMainPane.setLayout(new BoxLayout(panCenterMainPane, BoxLayout.PAGE_AXIS));
        panCenterMainPane.setBackground(new java.awt.Color(22, 37, 61));
        panCenterMainPane.setFont(panCenterMainPane.getFont().deriveFont(panCenterMainPane.getFont().getStyle() | java.awt.Font.BOLD, panCenterMainPane.getFont().getSize()+2));
        panCenterMainPane.setOpaque(false);
        panCenterMainPane.setPreferredSize(new java.awt.Dimension(700, 510));
        panCenterMainPane.setVerifyInputWhenFocusTarget(false);

        jPanel2.setBackground(new java.awt.Color(245, 240, 225));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        btnRoute.setBackground(new java.awt.Color(30, 61, 89));
        btnRoute.setFont(btnRoute.getFont().deriveFont(btnRoute.getFont().getStyle() & ~java.awt.Font.BOLD, btnRoute.getFont().getSize()+7));
        btnRoute.setForeground(new java.awt.Color(255, 255, 255));
        btnRoute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/route.png"))); // NOI18N
        btnRoute.setText("Route/रास्ता");
        btnRoute.setToolTipText("");
        btnRoute.setAlignmentX(0.5F);
        btnRoute.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRoute.setBorderPainted(false);
        btnRoute.setContentAreaFilled(false);
        btnRoute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRoute.setMaximumSize(new java.awt.Dimension(140, 100));
        btnRoute.setMinimumSize(new java.awt.Dimension(50, 50));
        btnRoute.setPreferredSize(new java.awt.Dimension(200, 135));
        btnRoute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteActionPerformed(evt);
            }
        });
        jPanel2.add(btnRoute);

        btnPhone.setBackground(new java.awt.Color(30, 61, 89));
        btnPhone.setFont(btnPhone.getFont().deriveFont(btnPhone.getFont().getStyle() & ~java.awt.Font.BOLD, btnPhone.getFont().getSize()+7));
        btnPhone.setForeground(new java.awt.Color(255, 255, 255));
        btnPhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pngwave.png"))); // NOI18N
        btnPhone.setText("Phone/ फ़ोन");
        btnPhone.setToolTipText("");
        btnPhone.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPhone.setBorderPainted(false);
        btnPhone.setContentAreaFilled(false);
        btnPhone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhone.setMaximumSize(new java.awt.Dimension(140, 100));
        btnPhone.setMinimumSize(new java.awt.Dimension(50, 50));
        btnPhone.setPreferredSize(new java.awt.Dimension(200, 135));
        btnPhone.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhoneActionEvent(evt);
            }
        });
        jPanel2.add(btnPhone);

        btnVideo.setBackground(new java.awt.Color(30, 61, 89));
        btnVideo.setFont(btnVideo.getFont().deriveFont(btnVideo.getFont().getStyle() & ~java.awt.Font.BOLD, btnVideo.getFont().getSize()+7));
        btnVideo.setForeground(new java.awt.Color(255, 255, 255));
        btnVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/video-camera.png"))); // NOI18N
        btnVideo.setText("Video/वीडियो");
        btnVideo.setToolTipText("");
        btnVideo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVideo.setBorderPainted(false);
        btnVideo.setContentAreaFilled(false);
        btnVideo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVideo.setMaximumSize(new java.awt.Dimension(140, 100));
        btnVideo.setMinimumSize(new java.awt.Dimension(50, 50));
        btnVideo.setPreferredSize(new java.awt.Dimension(200, 135));
        btnVideo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVideoActionPerformed(evt);
            }
        });
        jPanel2.add(btnVideo);
        btnVideo.getAccessibleContext().setAccessibleName(" Video/वीडियो");

        jPanel3.setMinimumSize(new java.awt.Dimension(377, 105));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(605, 145));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        btnVhmd.setBackground(new java.awt.Color(30, 61, 89));
        btnVhmd.setFont(btnVhmd.getFont().deriveFont(btnVhmd.getFont().getStyle() & ~java.awt.Font.BOLD, btnVhmd.getFont().getSize()+7));
        btnVhmd.setForeground(new java.awt.Color(255, 255, 255));
        btnVhmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can.png"))); // NOI18N
        btnVhmd.setText("VHMD/वीएचएमडी");
        btnVhmd.setToolTipText("");
        btnVhmd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVhmd.setBorderPainted(false);
        btnVhmd.setContentAreaFilled(false);
        btnVhmd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVhmd.setMinimumSize(new java.awt.Dimension(109, 95));
        btnVhmd.setPreferredSize(new java.awt.Dimension(200, 135));
        btnVhmd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVhmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVhmdActionPerformed(evt);
            }
        });
        jPanel3.add(btnVhmd);

        btnConfig.setBackground(new java.awt.Color(30, 61, 89));
        btnConfig.setFont(btnConfig.getFont().deriveFont(btnConfig.getFont().getStyle() & ~java.awt.Font.BOLD, btnConfig.getFont().getSize()+7));
        btnConfig.setForeground(new java.awt.Color(255, 255, 255));
        btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/configo.png"))); // NOI18N
        btnConfig.setText("Config/ कॉन्फ़िग");
        btnConfig.setToolTipText("");
        btnConfig.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConfig.setBorderPainted(false);
        btnConfig.setContentAreaFilled(false);
        btnConfig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfig.setPreferredSize(new java.awt.Dimension(200, 135));
        btnConfig.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        jPanel3.add(btnConfig);

        btnfileManager1.setBackground(new java.awt.Color(30, 61, 89));
        btnfileManager1.setFont(btnfileManager1.getFont().deriveFont(btnfileManager1.getFont().getStyle() & ~java.awt.Font.BOLD, btnfileManager1.getFont().getSize()+7));
        btnfileManager1.setForeground(new java.awt.Color(255, 255, 255));
        btnfileManager1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file-manager.png"))); // NOI18N
        btnfileManager1.setText("<html>File Manager<br>फ़ाइल प्रबंधक</html>");
        btnfileManager1.setToolTipText("");
        btnfileManager1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnfileManager1.setBorderPainted(false);
        btnfileManager1.setContentAreaFilled(false);
        btnfileManager1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnfileManager1.setPreferredSize(new java.awt.Dimension(200, 135));
        btnfileManager1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnfileManager1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfileManager1btnFileManagerActionPerformed(evt);
            }
        });
        jPanel3.add(btnfileManager1);

        panThirdRow.setFocusable(false);
        panThirdRow.setMinimumSize(new java.awt.Dimension(377, 105));
        panThirdRow.setOpaque(false);
        panThirdRow.setPreferredSize(new java.awt.Dimension(665, 149));

        btnDiagnosis.setBackground(new java.awt.Color(30, 61, 89));
        btnDiagnosis.setFont(btnDiagnosis.getFont().deriveFont(btnDiagnosis.getFont().getStyle() & ~java.awt.Font.BOLD, btnDiagnosis.getFont().getSize()+7));
        btnDiagnosis.setForeground(new java.awt.Color(255, 255, 255));
        btnDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/diagnostics.png"))); // NOI18N
        btnDiagnosis.setToolTipText("");
        btnDiagnosis.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDiagnosis.setBorderPainted(false);
        btnDiagnosis.setContentAreaFilled(false);
        btnDiagnosis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDiagnosis.setLabel("<html> Diagnostics<br>निदान</html> ");
        btnDiagnosis.setPreferredSize(new java.awt.Dimension(200, 135));
        btnDiagnosis.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosisActionPerformed(evt);
            }
        });
        panThirdRow.add(btnDiagnosis);

        btnMessages.setBackground(new java.awt.Color(30, 61, 89));
        btnMessages.setFont(btnMessages.getFont().deriveFont(btnMessages.getFont().getStyle() & ~java.awt.Font.BOLD, btnMessages.getFont().getSize()+7));
        btnMessages.setForeground(new java.awt.Color(255, 255, 255));
        btnMessages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/messages.png"))); // NOI18N
        btnMessages.setToolTipText("");
        btnMessages.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMessages.setBorderPainted(false);
        btnMessages.setContentAreaFilled(false);
        btnMessages.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMessages.setLabel("Messages/मेसेजस");
        btnMessages.setPreferredSize(new java.awt.Dimension(200, 135));
        btnMessages.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMessagesActionPerformed(evt);
            }
        });
        panThirdRow.add(btnMessages);

        btnEmergency.setBackground(new java.awt.Color(30, 61, 89));
        btnEmergency.setFont(btnEmergency.getFont().deriveFont(btnEmergency.getFont().getStyle() & ~java.awt.Font.BOLD, btnEmergency.getFont().getSize()+7));
        btnEmergency.setForeground(new java.awt.Color(255, 255, 255));
        btnEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/radio.png"))); // NOI18N
        btnEmergency.setText("<html>RADIO<br> रेडियो</html>");
        btnEmergency.setToolTipText("");
        btnEmergency.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEmergency.setBorderPainted(false);
        btnEmergency.setContentAreaFilled(false);
        btnEmergency.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEmergency.setPreferredSize(new java.awt.Dimension(200, 135));
        btnEmergency.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEmergency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmergencybtnFileManagerActionPerformed(evt);
            }
        });
        panThirdRow.add(btnEmergency);

        javax.swing.GroupLayout panCenterMainPaneLayout = new javax.swing.GroupLayout(panCenterMainPane);
        panCenterMainPane.setLayout(panCenterMainPaneLayout);
        panCenterMainPaneLayout.setHorizontalGroup(
            panCenterMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCenterMainPaneLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panCenterMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panThirdRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panCenterMainPaneLayout.setVerticalGroup(
            panCenterMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCenterMainPaneLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panThirdRow, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panMainPaneLayout = new javax.swing.GroupLayout(panMainPane);
        panMainPane.setLayout(panMainPaneLayout);
        panMainPaneLayout.setHorizontalGroup(
            panMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panMainPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panCenterMainPane, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panMainPaneLayout.setVerticalGroup(
            panMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
            .addGroup(panMainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panCenterMainPane, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );

        panCenterMainPane.getAccessibleContext().setAccessibleName("panCenterMainPane");
        panCenterMainPane.getAccessibleContext().setAccessibleParent(panMainPane);

        javax.swing.GroupLayout PanFullPaneLayout = new javax.swing.GroupLayout(PanFullPane);
        PanFullPane.setLayout(PanFullPaneLayout);
        PanFullPaneLayout.setHorizontalGroup(
            PanFullPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanFullPaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panMainPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanFullPaneLayout.setVerticalGroup(
            PanFullPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanFullPaneLayout.createSequentialGroup()
                .addComponent(panMainPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(PanFullPane);
        PanFullPane.setBounds(92, 106, 700, 430);

        PanLeftpane.setBackground(new java.awt.Color(245, 240, 225));
        PanLeftpane.setForeground(new java.awt.Color(255, 255, 255));
        PanLeftpane.setOpaque(false);
        PanLeftpane.setPreferredSize(new java.awt.Dimension(70, 420));

        btnSos.setBackground(new java.awt.Color(204, 0, 0));
        btnSos.setFont(btnSos.getFont().deriveFont(btnSos.getFont().getStyle() | java.awt.Font.BOLD));
        btnSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sos_alert.png"))); // NOI18N
        btnSos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSos.setBorderPainted(false);
        btnSos.setContentAreaFilled(false);
        btnSos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSos.setIconTextGap(0);
        btnSos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSosActionPerformed(evt);
            }
        });

        btnCanAlarm.setFont(btnCanAlarm.getFont().deriveFont(btnCanAlarm.getFont().getStyle() | java.awt.Font.BOLD, btnCanAlarm.getFont().getSize()+3));
        btnCanAlarm.setForeground(new java.awt.Color(255, 255, 255));
        btnCanAlarm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_alert.png"))); // NOI18N
        btnCanAlarm.setText("CAN");
        btnCanAlarm.setToolTipText("");
        btnCanAlarm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCanAlarm.setBorderPainted(false);
        btnCanAlarm.setContentAreaFilled(false);
        btnCanAlarm.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCanAlarm.setIconTextGap(0);
        btnCanAlarm.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCanAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanAlarmActionPerformed(evt);
            }
        });

        btnFire.setBackground(new java.awt.Color(204, 204, 204));
        btnFire.setFont(btnFire.getFont().deriveFont(btnFire.getFont().getStyle() | java.awt.Font.BOLD, btnFire.getFont().getSize()+1));
        btnFire.setForeground(new java.awt.Color(255, 255, 255));
        btnFire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fire_new.png"))); // NOI18N
        btnFire.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFire.setBorderPainted(false);
        btnFire.setContentAreaFilled(false);
        btnFire.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFire.setIconTextGap(0);
        btnFire.setPreferredSize(new java.awt.Dimension(70, 50));
        btnFire.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnFire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFireActionPerformed(evt);
            }
        });

        btnBreakDown.setBackground(new java.awt.Color(204, 204, 204));
        btnBreakDown.setFont(btnBreakDown.getFont().deriveFont(btnBreakDown.getFont().getStyle() | java.awt.Font.BOLD, btnBreakDown.getFont().getSize()+2));
        btnBreakDown.setForeground(new java.awt.Color(255, 255, 255));
        btnBreakDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Breakdown1.png"))); // NOI18N
        btnBreakDown.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBreakDown.setBorderPainted(false);
        btnBreakDown.setContentAreaFilled(false);
        btnBreakDown.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBreakDown.setIconTextGap(0);
        btnBreakDown.setPreferredSize(new java.awt.Dimension(70, 50));
        btnBreakDown.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnBreakDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBreakDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanLeftpaneLayout = new javax.swing.GroupLayout(PanLeftpane);
        PanLeftpane.setLayout(PanLeftpaneLayout);
        PanLeftpaneLayout.setHorizontalGroup(
            PanLeftpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanLeftpaneLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(PanLeftpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnCanAlarm, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(btnSos)
                    .addComponent(btnFire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBreakDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        PanLeftpaneLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCanAlarm, btnSos});

        PanLeftpaneLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBreakDown, btnFire});

        PanLeftpaneLayout.setVerticalGroup(
            PanLeftpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanLeftpaneLayout.createSequentialGroup()
                .addComponent(btnCanAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnBreakDown, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );

        jPanel4.add(PanLeftpane);
        PanLeftpane.setBounds(10, 106, 82, 440);

        jLayeredPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLayeredPane1.setLayout(new java.awt.FlowLayout());

        lblFD.setBackground(new java.awt.Color(204, 204, 204));
        lblFD.setFont(lblFD.getFont().deriveFont(lblFD.getFont().getStyle() | java.awt.Font.BOLD, lblFD.getFont().getSize()+3));
        lblFD.setForeground(new java.awt.Color(255, 255, 255));
        lblFD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fd_no_status.png"))); // NOI18N
        lblFD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(lblFD);

        lblSD.setBackground(java.awt.SystemColor.controlHighlight);
        lblSD.setFont(lblSD.getFont().deriveFont(lblSD.getFont().getStyle() | java.awt.Font.BOLD, lblSD.getFont().getSize()+3));
        lblSD.setForeground(new java.awt.Color(153, 0, 0));
        lblSD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sd_no_status.png"))); // NOI18N
        lblSD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(lblSD);

        lblRD.setBackground(java.awt.SystemColor.controlHighlight);
        lblRD.setFont(lblRD.getFont().deriveFont(lblRD.getFont().getStyle() | java.awt.Font.BOLD, lblRD.getFont().getSize()+3));
        lblRD.setForeground(new java.awt.Color(153, 0, 0));
        lblRD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rd_no_status.png"))); // NOI18N
        lblRD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(lblRD);

        lblID.setBackground(java.awt.SystemColor.controlHighlight);
        lblID.setFont(lblID.getFont().deriveFont(lblID.getFont().getStyle() | java.awt.Font.BOLD, lblID.getFont().getSize()+3));
        lblID.setForeground(new java.awt.Color(153, 0, 0));
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/id_no_status.png"))); // NOI18N
        lblID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(lblID);

        lblSDArt.setBackground(java.awt.SystemColor.controlHighlight);
        lblSDArt.setFont(lblSDArt.getFont().deriveFont(lblSDArt.getFont().getStyle() | java.awt.Font.BOLD, lblSDArt.getFont().getSize()+3));
        lblSDArt.setForeground(new java.awt.Color(153, 0, 0));
        lblSDArt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSDArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sd_no_status.png"))); // NOI18N
        lblSDArt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(lblSDArt);

        lblIDArt.setBackground(java.awt.SystemColor.controlHighlight);
        lblIDArt.setFont(lblIDArt.getFont().deriveFont(lblIDArt.getFont().getStyle() | java.awt.Font.BOLD, lblIDArt.getFont().getSize()+3));
        lblIDArt.setForeground(new java.awt.Color(153, 0, 0));
        lblIDArt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIDArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/id_no_status.png"))); // NOI18N
        lblIDArt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(lblIDArt);

        imgCan.setBackground(java.awt.SystemColor.controlHighlight);
        imgCan.setFont(imgCan.getFont().deriveFont(imgCan.getFont().getStyle() | java.awt.Font.BOLD, imgCan.getFont().getSize()+3));
        imgCan.setForeground(new java.awt.Color(153, 0, 0));
        imgCan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_no_status.png"))); // NOI18N
        imgCan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(imgCan);

        imgCan1.setBackground(java.awt.SystemColor.controlHighlight);
        imgCan1.setFont(imgCan1.getFont().deriveFont(imgCan1.getFont().getStyle() | java.awt.Font.BOLD, imgCan1.getFont().getSize()+3));
        imgCan1.setForeground(new java.awt.Color(153, 0, 0));
        imgCan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_no_status1.png"))); // NOI18N
        imgCan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(imgCan1);

        imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png"))); // NOI18N
        jLayeredPane1.add(imgSos);

        imgIgnition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ignition.png"))); // NOI18N
        jLayeredPane1.add(imgIgnition);

        imgHardDisk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgHardDisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/harddrive_red.png"))); // NOI18N
        jLayeredPane1.add(imgHardDisk);

        imgGps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/noGps.png"))); // NOI18N
        jLayeredPane1.add(imgGps);

        imgWifi.setBackground(new java.awt.Color(70, 156, 185));
        imgWifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wifi-signal.png"))); // NOI18N
        jLayeredPane1.add(imgWifi);

        imgSimDetect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/no-sim-card.png"))); // NOI18N
        jLayeredPane1.add(imgSimDetect);

        imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_2.png"))); // NOI18N
        jLayeredPane1.add(imgSigStr);

        imgNetwork.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/3g.png"))); // NOI18N
        jLayeredPane1.add(imgNetwork);

        imgMobile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tower.png"))); // NOI18N
        jLayeredPane1.add(imgMobile);

        imgServerConn.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        imgServerConn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgServerConn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png"))); // NOI18N
        imgServerConn.setText("1");
        imgServerConn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgServerConn.setIconTextGap(0);
        imgServerConn.setInheritsPopupMenu(false);
        jLayeredPane1.add(imgServerConn);

        imgServerConn2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        imgServerConn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgServerConn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png"))); // NOI18N
        imgServerConn2.setText("2");
        imgServerConn2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgServerConn2.setIconTextGap(0);
        jLayeredPane1.add(imgServerConn2);

        imgServerConn3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        imgServerConn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgServerConn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png"))); // NOI18N
        imgServerConn3.setText("3");
        imgServerConn3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgServerConn3.setIconTextGap(0);
        jLayeredPane1.add(imgServerConn3);

        imgServerConn4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        imgServerConn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgServerConn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png"))); // NOI18N
        imgServerConn4.setText("4");
        imgServerConn4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgServerConn4.setIconTextGap(0);
        jLayeredPane1.add(imgServerConn4);

        imgServerConn5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        imgServerConn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/network.png"))); // NOI18N
        imgServerConn5.setText("5");
        imgServerConn5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgServerConn5.setIconTextGap(0);
        jLayeredPane1.add(imgServerConn5);

        jPanel4.add(jLayeredPane1);
        jLayeredPane1.setBounds(80, 0, 720, 40);

        imgDigI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png"))); // NOI18N
        imgDigI2.setIconTextGap(0);
        imgDigI2.setName(""); // NOI18N
        jPanel4.add(imgDigI2);
        imgDigI2.setBounds(280, 70, 35, 30);

        imgDigI3.setFont(imgDigI3.getFont().deriveFont(imgDigI3.getFont().getSize()+4f));
        imgDigI3.setForeground(new java.awt.Color(255, 255, 255));
        imgDigI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_request.png"))); // NOI18N
        imgDigI3.setText("DI3");
        jPanel4.add(imgDigI3);
        imgDigI3.setBounds(320, 80, 30, 20);

        imgDigI4.setFont(imgDigI4.getFont().deriveFont(imgDigI4.getFont().getSize()+3f));
        imgDigI4.setForeground(new java.awt.Color(255, 255, 255));
        imgDigI4.setText("DI4");
        imgDigI4.setPreferredSize(new java.awt.Dimension(28, 26));
        jPanel4.add(imgDigI4);
        imgDigI4.setBounds(360, 80, 30, 19);

        imgTamper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boxopen.png"))); // NOI18N
        imgTamper.setIconTextGap(0);
        imgTamper.setName(""); // NOI18N
        jPanel4.add(imgTamper);
        imgTamper.setBounds(570, 50, 30, 30);

        lblPeopleOut2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblPeopleOut2.setForeground(new java.awt.Color(255, 255, 255));
        lblPeopleOut2.setText("00");
        jPanel4.add(lblPeopleOut2);
        lblPeopleOut2.setBounds(230, 80, 50, 20);

        lblPeopleIn2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblPeopleIn2.setForeground(new java.awt.Color(255, 255, 255));
        lblPeopleIn2.setText("00");
        jPanel4.add(lblPeopleIn2);
        lblPeopleIn2.setBounds(130, 80, 50, 20);

        imgCanEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ambulancetop.png"))); // NOI18N
        jPanel4.add(imgCanEmergency);
        imgCanEmergency.setBounds(510, 50, 30, 20);

        imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_off.png"))); // NOI18N
        jPanel4.add(imgMic);
        imgMic.setBounds(540, 40, 20, 30);

        lblPeopleIn1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblPeopleIn1.setForeground(new java.awt.Color(255, 255, 255));
        lblPeopleIn1.setText("IN:");
        jPanel4.add(lblPeopleIn1);
        lblPeopleIn1.setBounds(90, 80, 30, 20);

        lblPeopleOut1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblPeopleOut1.setForeground(new java.awt.Color(255, 255, 255));
        lblPeopleOut1.setText("OUT:");
        jPanel4.add(lblPeopleOut1);
        lblPeopleOut1.setBounds(180, 80, 50, 20);

        jPanel5.setBackground(new java.awt.Color(245, 240, 225));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

        imgEmergency.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        imgEmergency.setForeground(new java.awt.Color(255, 255, 0));
        imgEmergency.setText("EMERGENCY");

        imgStopReq.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        imgStopReq.setForeground(new java.awt.Color(255, 255, 0));
        imgStopReq.setText("STOP REQUEST");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(imgEmergency, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(imgStopReq)
                .addGap(64, 64, 64))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(imgEmergency)
                .addComponent(imgStopReq))
        );

        jPanel4.add(jPanel5);
        jPanel5.setBounds(100, 540, 690, 20);
        jPanel5.getAccessibleContext().setAccessibleParent(panMainPane);

        lblUsb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pendrive_img.png"))); // NOI18N
        lblUsb.setText("jLabel2");
        jPanel4.add(lblUsb);
        lblUsb.setBounds(480, 40, 30, 30);

        lbldriverid.setBackground(new java.awt.Color(30, 61, 249));
        lbldriverid.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbldriverid.setForeground(new java.awt.Color(255, 255, 255));
        lbldriverid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbldriverid.setText("Driver: ");
        lbldriverid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbldriverid.setPreferredSize(new java.awt.Dimension(250, 19));
        jPanel4.add(lbldriverid);
        lbldriverid.setBounds(500, 80, 140, 30);

        lblconductorid.setBackground(new java.awt.Color(30, 61, 249));
        lblconductorid.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblconductorid.setForeground(new java.awt.Color(255, 255, 255));
        lblconductorid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblconductorid.setText("Conductor:");
        lblconductorid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblconductorid.setPreferredSize(new java.awt.Dimension(250, 19));
        jPanel4.add(lblconductorid);
        lblconductorid.setBounds(650, 80, 150, 30);

        ButtonStop.setBackground(new java.awt.Color(0, 0, 0));
        ButtonStop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ButtonStop.setForeground(new java.awt.Color(255, 255, 255));
        ButtonStop.setText("STOP VIDEO");
        ButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStopActionPerformed(evt);
            }
        });
        jPanel4.add(ButtonStop);
        ButtonStop.setBounds(80, 560, 710, 40);

        jLayeredPaneLive.setBackground(new java.awt.Color(0, 0, 0));

        imgCam1.setBackground(new java.awt.Color(255, 255, 255));
        imgCam1.setFont(imgCam1.getFont().deriveFont(imgCam1.getFont().getStyle() | java.awt.Font.BOLD, imgCam1.getFont().getSize()+3));
        imgCam1.setForeground(new java.awt.Color(255, 255, 255));
        imgCam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCam1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cctv_no_status.png"))); // NOI18N
        imgCam1.setText("1");
        imgCam1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgCam1.setIconTextGap(0);
        imgCam1.setMaximumSize(new java.awt.Dimension(44, 30));
        imgCam1.setMinimumSize(new java.awt.Dimension(44, 30));
        imgCam1.setPreferredSize(new java.awt.Dimension(32, 30));

        imgCam2.setBackground(new java.awt.Color(255, 255, 255));
        imgCam2.setFont(imgCam2.getFont().deriveFont(imgCam2.getFont().getStyle() | java.awt.Font.BOLD, imgCam2.getFont().getSize()+3));
        imgCam2.setForeground(new java.awt.Color(255, 255, 255));
        imgCam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCam2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cctv_no_status.png"))); // NOI18N
        imgCam2.setText("2");
        imgCam2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgCam2.setMaximumSize(new java.awt.Dimension(44, 30));
        imgCam2.setMinimumSize(new java.awt.Dimension(44, 30));
        imgCam2.setPreferredSize(new java.awt.Dimension(32, 30));

        imgCam3.setBackground(new java.awt.Color(255, 255, 255));
        imgCam3.setFont(imgCam3.getFont().deriveFont(imgCam3.getFont().getStyle() | java.awt.Font.BOLD, imgCam3.getFont().getSize()+3));
        imgCam3.setForeground(new java.awt.Color(255, 255, 255));
        imgCam3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCam3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cctv_no_status.png"))); // NOI18N
        imgCam3.setText("3");
        imgCam3.setToolTipText("");
        imgCam3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgCam3.setMaximumSize(new java.awt.Dimension(44, 30));
        imgCam3.setMinimumSize(new java.awt.Dimension(44, 30));
        imgCam3.setPreferredSize(new java.awt.Dimension(32, 30));

        imgCam4.setBackground(new java.awt.Color(255, 255, 255));
        imgCam4.setFont(imgCam4.getFont().deriveFont(imgCam4.getFont().getStyle() | java.awt.Font.BOLD, imgCam4.getFont().getSize()+3));
        imgCam4.setForeground(new java.awt.Color(255, 255, 255));
        imgCam4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgCam4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cctv_no_status.png"))); // NOI18N
        imgCam4.setText("4");
        imgCam4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgCam4.setMaximumSize(new java.awt.Dimension(44, 30));
        imgCam4.setMinimumSize(new java.awt.Dimension(44, 30));
        imgCam4.setPreferredSize(new java.awt.Dimension(32, 30));

        jLayeredPaneLive.setLayer(imgCam1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLive.setLayer(imgCam2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLive.setLayer(imgCam3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLive.setLayer(imgCam4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneLiveLayout = new javax.swing.GroupLayout(jLayeredPaneLive);
        jLayeredPaneLive.setLayout(jLayeredPaneLiveLayout);
        jLayeredPaneLiveLayout.setHorizontalGroup(
            jLayeredPaneLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneLiveLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(imgCam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(imgCam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(imgCam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(imgCam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jLayeredPaneLiveLayout.setVerticalGroup(
            jLayeredPaneLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneLiveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgCam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jLayeredPaneLive);
        jLayeredPaneLive.setBounds(80, 40, 343, 40);

        btnLiveCamAll.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnLiveCamAll.setText("ALL");
        btnLiveCamAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLiveCamAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiveCamAllActionPerformed(evt);
            }
        });

        btnLiveCam1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnLiveCam1.setText("1");
        btnLiveCam1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLiveCam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiveCam1ActionPerformed(evt);
            }
        });

        btnLiveCam2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnLiveCam2.setText("2");
        btnLiveCam2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLiveCam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiveCam2ActionPerformed(evt);
            }
        });

        btnLiveCam3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnLiveCam3.setText("3");
        btnLiveCam3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLiveCam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiveCam3ActionPerformed(evt);
            }
        });

        btnLiveCam4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnLiveCam4.setText("4");
        btnLiveCam4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLiveCam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiveCam4ActionPerformed(evt);
            }
        });

        jLayeredPaneLiveButtons.setLayer(btnLiveCamAll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLiveButtons.setLayer(btnLiveCam1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLiveButtons.setLayer(btnLiveCam2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLiveButtons.setLayer(btnLiveCam3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneLiveButtons.setLayer(btnLiveCam4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPaneLiveButtonsLayout = new javax.swing.GroupLayout(jLayeredPaneLiveButtons);
        jLayeredPaneLiveButtons.setLayout(jLayeredPaneLiveButtonsLayout);
        jLayeredPaneLiveButtonsLayout.setHorizontalGroup(
            jLayeredPaneLiveButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneLiveButtonsLayout.createSequentialGroup()
                .addComponent(btnLiveCamAll, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnLiveCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnLiveCam2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnLiveCam3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnLiveCam4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        jLayeredPaneLiveButtonsLayout.setVerticalGroup(
            jLayeredPaneLiveButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneLiveButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneLiveButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneLiveButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLiveCamAll, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLiveCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLiveCam2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLiveCam3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLiveCam4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        btnLiveCam1.getAccessibleContext().setAccessibleDescription("");

        jPanel4.add(jLayeredPaneLiveButtons);
        jLayeredPaneLiveButtons.setBounds(80, 40, 400, 40);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        getContentPane().add(jPanel4, gridBagConstraints);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/road.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 600));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jLabel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private synchronized void load_top_images() {
        if (clsDefines.obu_images_filepath.exists() == true) {
            btnRoute.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/route.png")); // NOI18N
            btnRoute.repaint();
            btnPhone.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/pngwave.png")); // NOI18N
            btnPhone.repaint();
            btnVideo.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/video-camera.png")); // NOI18N
            btnVideo.repaint();
            btnVhmd.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/can.png")); // NOI18N
            btnVhmd.repaint();
            btnConfig.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/configo.png")); // NOI18N
            btnConfig.repaint();
            btnfileManager1.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/file-manager.png")); // NOI18N
            btnfileManager1.repaint();
            btnDiagnosis.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/diagnostics.png")); // NOI18N
            btnDiagnosis.repaint();
            btnMessages.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/messages.png")); // NOI18N
            btnMessages.repaint();
            btnEmergency.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/radio.png"));
            btnEmergency.repaint();
            btnHome.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/home.png"));
            btnCanAlarm.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/can_alert.png"));
            btnSos.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/sos_alert.png"));
            btnLogout.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/logout.png"));
            imgWifi.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/wifi-signal.png"));
            imgWifi.repaint();
            imgMobile.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/tower.png")); // NOI18N
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
            imgGps.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/noGps.png"));
            updateIgnitionIcon();
            updateHarddiskIcon();
            updateMicStatusIcon();
            updateTamperIcon();
        } else {
            imgGps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/noGps.png")));
            btnRoute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/route.png"))); // NOI18N
            btnRoute.repaint();
            btnPhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pngwave.png"))); // NOI18N
            btnPhone.repaint();
            btnVideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/video-camera.png"))); // NOI18N
            btnVideo.repaint();
            btnVhmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can.png"))); // NOI18N
            btnVhmd.repaint();
            btnConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/configo.png"))); // NOI18N
            btnConfig.repaint();
            btnfileManager1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/file-manager.png"))); // NOI18N
            btnfileManager1.repaint();
            btnDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/diagnostics.png"))); // NOI18N
            btnDiagnosis.repaint();
            btnMessages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/messages.png"))); // NOI18N
            btnMessages.repaint();
            btnEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/radio.png")));
            btnEmergency.repaint();
            btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home.png")));
            btnCanAlarm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/can_alert.png")));
            btnSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sos_alert.png")));
            imgWifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wifi-signal.png")));
            imgWifi.repaint();
            imgMobile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tower.png"))); // NOI18N
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
            btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logout.png")));
            updateIgnitionIcon();
            updateHarddiskIcon();
            updateMicStatusIcon();
            updateTamperIcon();
        }
    }

    private synchronized void updateIgnitionIcon() {
        boolean state = clsSharedVariables.getIgnitionStatus();

        if (state == true) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgIgnition.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/ignition.png"));
                imgIgnition.repaint();
            } else {
                imgIgnition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ignition.png")));
                imgIgnition.repaint();
            }
            imgIgnition.setVisible(true);
        } else {
            imgIgnition.setVisible(false);
        }
    }

    private synchronized void updateHarddiskIcon() {
        boolean state = clsSharedVariables.getHardDriveDetected();
        if (state == false) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgHardDisk.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/hard_drive_red.png"));

            } else {
                imgHardDisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hard_drive_red.png")));

            }
        } else {
            if (clsDefines.obu_images_filepath.exists() == true) {

                imgHardDisk.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/hard_drive_normal.png"));

            } else {
                imgHardDisk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hard_drive_normal.png")));
            }
        }
        imgHardDisk.repaint();
    }

    private synchronized void updateMicStatusIcon() {
        if (mic_on == true) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgMic.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/microphone_on.png"));

            } else {
                imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_on.png")));
            }
        } else {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgMic.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/microphone_off.png"));

            } else {
                imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_off.png")));
            }
        }
        imgMic.repaint();
    }

    private synchronized void updateTamperIcon() {
        if (tamper_on == true) {
            if (clsDefines.obu_images_filepath.exists() == true) {
                imgTamper.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/boxopen.png"));

            } else {
                imgTamper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boxopen.png")));

            }
            imgTamper.repaint();
            imgTamper.setVisible(true);
        } else {

            imgTamper.setVisible(false);
        }
    }

    public static synchronized void setVideoStarted(boolean state) {
        video_started = state;
    }

    public static synchronized boolean getVideoStarted() {
        return video_started;

    }

    public static synchronized void setVideoEnableStarted(boolean state) {
        video_enable_started = state;
    }

    public static synchronized boolean getVideoEnableStarted() {
        return video_enable_started;

    }

    public static synchronized void setVideoLiveState(byte state) {

        video_live_state = state;

    }

    public static synchronized byte getVideoLiveState() {

        return video_live_state;

    }

    public static synchronized void setPreVideoLiveState(byte state) {

        pre_video_live_state = state;

    }

    public static synchronized byte getPreVideoLiveState() {

        return pre_video_live_state;

    }

    public static synchronized void setDigSosStarted(boolean state) {

        dig1_sos_started = state;

    }

    public static synchronized boolean getDigSosStarted() {

        return dig1_sos_started;

    }

    public static synchronized void setDigStopReqStarted(boolean state) {

        dig_stopreq_started = state;

    }

    public static synchronized boolean getDigStopReqStarted() {

        return dig_stopreq_started;

    }

    public static synchronized void setVideoRecordStarted(boolean state) {

        dig_video_record_started = state;

    }

    public static synchronized boolean getVideoRecordStarted() {

        return dig_video_record_started;

    }

    public static synchronized void setReverseCameraStarted(boolean state) {

        dig_reverse_camera_started = state;

    }

    public static synchronized boolean getReverseCameraStarted() {

        return dig_reverse_camera_started;

    }

    public static synchronized void setLeftArrowStarted(boolean state) {

        dig_left_arrow_started = state;

    }

    public static synchronized boolean getLeftArrowStarted() {

        return dig_left_arrow_started;

    }

    public static synchronized void setRightArrowStarted(boolean state) {

        dig_right_arrow_started = state;

    }

    public static synchronized boolean getRightArrowStarted() {

        return dig_right_arrow_started;

    }

    public void killomxplayer() {
        String processName = "omxplayer";
        try {
            Process process = Runtime.getRuntime().exec("pgrep " + processName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String pid;
            while ((pid = reader.readLine()) != null) {
                Runtime.getRuntime().exec("kill " + pid);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void killffplay() {
        String processName = "ffplay";
        try {
            Process process = Runtime.getRuntime().exec("pgrep " + processName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String pid;
            while ((pid = reader.readLine()) != null) {
                Runtime.getRuntime().exec("kill " + pid);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void killGstreamer() {
        String processName = "gst-launch-1.0";
        try {
            Process process = Runtime.getRuntime().exec("pgrep " + processName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String pid;
            while ((pid = reader.readLine()) != null) {
                Runtime.getRuntime().exec("kill " + pid);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start_video() {

        if (savedVideoState <= 0) {
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

  private void stop_video(){
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
  
    private boolean runCmdUpdateTime(String cmd) {

        Process process = null;
        Runtime rt = null;
        try {

            rt = Runtime.getRuntime();
            process = rt.exec(new String[]{"bash", "-c", cmd});
            process.waitFor();
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

    private void update_time_tinkeros() {
        try {
            DS1307RTC obj;
            obj = new DS1307RTC();
            Date d = obj.getDate();
            runCmdUpdateTime("sudo hwclock --set --date=\"" + d.toString() + "\"");
            runCmdUpdateTime("sudo hwclock --hctosys --utc");
            runCmdUpdateTime("sudo hwclock --hctosys --utc");

        } catch (IOException ex) {
            Logger.getLogger(MainFrmIts.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean permissionsObu() {

        Process process = null;

        DataOutputStream os = null;

        Runtime rt = null;

        File f;

        boolean found = false;

        File obu_dir;

        File[] files;

        String str;

        File[] list_file_names;
        File[] files_list;
        File[] l_files_list;

        int file_inc = 0;

        Double freespace;//totalSpace;

        File file;

        int i;

        byte reset_times;

        clsReadFiles objReadFiles = new clsReadFiles();

        reset_times = objReadFiles.read_reset_data_today();

        try {
            rt = Runtime.getRuntime();

            //Thread.sleep(2000);
            obu_dir = new File(clsDefines.media_path);

            files = obu_dir.listFiles();

            if (files != null) {
                Arrays.sort(files);

                list_file_names = new File[files.length];

                for (i = 0; i < files.length; i++) {

                    str = files[i].getName();

                    if (files[i].isDirectory()) {

                        if (str.trim().contains("3339OBU")) {

                            list_file_names[file_inc++] = files[i];

                        }

                    }

                }

                for (i = 0; i < file_inc; i++) {

                    try {

                        list_file_names[i].setExecutable(true, false);

                        list_file_names[i].setReadable(true);

                        list_file_names[i].setWritable(true);

                        freespace = list_file_names[i].getFreeSpace() / ONE_GB;
                        if (freespace <= 2) {
                            found = true;

                            files = list_file_names[i].listFiles();
                            if (files != null) {
                                for (int j = 0; j < files.length; j++) {
                                    if (files[j].getName().startsWith("FSCK")) {
                                        files[j].delete();
                                    } else if (files[j].isDirectory()) {
                                        if (files[j].getName().equals("Videos") && files[j].isDirectory()) {
                                            files_list = files[j].listFiles();
                                            if (files != null) {
                                                Arrays.sort(files);
                                                for (int l = 0; l < files_list.length; l++) {
                                                    if (files_list[l].isDirectory()) {
                                                        l_files_list = files_list[l].listFiles();
                                                        Arrays.sort(l_files_list);
                                                        for (j = 0; j < l_files_list.length && j < 10; j++) {
                                                            l_files_list[j].delete();
                                                        }
                                                    } else {
                                                        files_list[l].delete();
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            freespace = list_file_names[i].getFreeSpace() / ONE_GB;
                            if (freespace < 2) {
                                files = list_file_names[i].listFiles();
                                if (files != null) {
                                    for (int j = 0; j < files.length; j++) {
                                        if (!files[j].getName().equals("Route") && files[j].isDirectory()) {
                                            files_list = files[j].listFiles();
                                            if (files != null) {
                                                for (int l = 0; l < files_list.length; l++) {
                                                    files_list[l].delete();
                                                }
                                            }
                                        }
                                    }
                                }
                                freespace = list_file_names[i].getFreeSpace() / ONE_GB;
                                if (freespace < 2) {
                                    files = list_file_names[i].listFiles();
                                    if (files != null) {
                                        for (int j = 0; j < files.length; j++) {
                                            if (files[j].isFile()) {
                                                files[j].delete();
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                    } catch (Exception ex) {
                    }

                }
            }

            if (found == true && file_inc > 1 && reset_times < 3) {

                reboot_instant();

            }

            if (clsSharedVariables.getHardDriveDetected()) {

                if (!clsDefines.video_filepath.exists()) {
                    video_filepath.mkdirs();
                }

                if (!clsDefines.can_filepath.exists()) {
                    can_filepath.mkdirs();
                }

                f = new File(video_filepath + "/Cam1/");

                if (!f.exists()) {

                    f.mkdirs();

                    f.canRead();

                    f.canWrite();

                }
                f = new File(video_filepath + "/Cam2/");

                if (!f.exists()) {

                    f.mkdirs();

                    f.canRead();

                    f.canWrite();

                }

                f = new File(video_filepath + "/Cam3/");

                if (!f.exists()) {

                    f.mkdirs();

                    f.canRead();

                    f.canWrite();

                }

                f = new File(video_filepath + "/Cam4/");

                if (!f.exists()) {

                    f.mkdirs();

                    f.canRead();

                    f.canWrite();

                }
                f = new File(video_filepath + "/Videos_motion/Cam1");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_motion/Cam2");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_motion/Cam3");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_motion/Cam4");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_motion/Cam5");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_motion/Cam6");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_motion/Cam7");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_motion/Cam8");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_event/Cam1");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_event/Cam2");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_event/Cam3");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_event/Cam4");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_event/Cam5");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_event/Cam6");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_event/Cam7");
                if (!f.exists()) {
                    f.mkdirs();
                }
                f = new File(video_filepath + "/Videos_event/Cam8");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_can/Cam1");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_can/Cam2");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_can/Cam3");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Videos_can/Cam4");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Snapshot/Cam1");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Snapshot/Cam2");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Snapshot/Cam3");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(video_filepath + "/Snapshot/Cam4");
                if (!f.exists()) {
                    f.mkdirs();
                }

                f = new File(media_filepath, "Log");

                if (!f.exists()) {

                    f.mkdirs();

                }

            }
            runCmdCont("sudo chmod 777 -R proc/sysrq-trigger");
            runCmdCont("sudo chmod -R 777 " + clsDefines.main_filepath); // /home/pi
            runCmdCont("sudo chmod -R 777 " + clsDefines.media_path);// /media/pi
            runCmdCont("sudo chmod -R 777 " + clsDefines.HARDDISK_PATH);// /mnt/Recorder
            runCmdCont("sudo chmod -R 777 " + clsDefines.main_route_path);// /media/pi/3339OBU
            runCmdCont("sudo chmod 777 -R " + usb_filepath);
            runCmdCont("sudo chmod 777 -R " + clsDefines.GPRS_LOCK_FILE);
            process = rt.exec("sudo chmod -R 777 " + GET_NETWORK_UUID_PATH);
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

                    os.close();

                    process.getInputStream().close();

                    process.getOutputStream().close();

                    process.getErrorStream().close();

                    process.destroy();

                    process = null;

                    rt = null;

                    os = null;

                    rt = null;

                }

            } catch (IOException e) {

            } catch (Exception ex) {

            }

            process = null;

            os = null;

            rt = null;

            f = null;

            obu_dir = null;

            files = null;

            str = null;

            list_file_names = null;

            file = null;

            objReadFiles = null;
        }

    }

    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(5, TimeUnit.SECONDS);
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

    private boolean runCmdCont(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(500, TimeUnit.SECONDS);
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

    String prev_control_name = "";

    public String getPassword(String title) {

        JPanel panel = new JPanel();

        final JPasswordField passwordField = new JPasswordField(10);

        panel.add(new JLabel("Password"));

        panel.add(passwordField);

        JOptionPane pane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION) {

            @Override

            public void selectInitialValue() {

                passwordField.requestFocusInWindow();

            }

        };

        pane.setPreferredSize(new Dimension(300, 130));

        pane.setFont(new java.awt.Font("Tahoma", 1, 18));

        JPanel buttonPanel = (JPanel) pane.getComponent(1);

        // get the handle to the ok button
        JButton buttonOk = (JButton) buttonPanel.getComponent(0);

        // set the text
        buttonOk.setText("OK");

        buttonOk.setFont(new java.awt.Font("Tahoma", 1, 18));

        buttonOk.setPreferredSize(new Dimension(80, 40));  //Set Button size here

        buttonOk.validate();

        JButton buttonCancel = (JButton) buttonPanel.getComponent(1);

        // set the text
        buttonCancel.setFont(new java.awt.Font("Tahoma", 1, 18));

        buttonCancel.setText("CANCEL");

        buttonCancel.setPreferredSize(new Dimension(100, 40));  //Set Button size here

        buttonCancel.validate();

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {

                txtPasswordFieldFocusGained(evt);

            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {

                txtPasswordFieldFocusLost(evt);

            }

            
            private void txtPasswordFieldFocusGained(FocusEvent evt) {

                // TODO add your handling code here:
                if (!prev_control_name.equals("Password")) {

                    clsKeyBoardView obj = new clsKeyBoardView();

                    String str = obj.getKeyBoardValue("Password", "");

                    if (str != null) {

                        passwordField.setText(str);

                    }

                    prev_control_name = "Password";

                    obj = null;

                    str = null;

                } else {

                    prev_control_name = "";

                }

            }

            private void txtPasswordFieldFocusLost(FocusEvent evt) {
                prev_control_name = "";
            }

        });

        JDialog dlg = pane.createDialog(null, title);
        dlg.setAlwaysOnTop(true);
        dlg.setVisible(true);

        Object selectedValue = pane.getValue();

        int n = -1;

        if (selectedValue == null) {

            n = JOptionPane.CLOSED_OPTION;

        } else {

            n = Integer.parseInt(selectedValue.toString());

        }

        if (n == JOptionPane.OK_OPTION) {

            return new String(passwordField.getPassword());

        } else if (n == JOptionPane.CANCEL_OPTION) {

            return null;

        } else {

            return null;

        }

    }

    private void close_keypad() {
        prev_control_name = "";
    }

    private synchronized void show_message_dialogbox(String data) {
        try {
            final JLabel label = new JLabel();
            int timerDelay = 1000;
            final String[] split_str = data.split(":");
            label.setText(split_str[0]);
            label.setBackground(Color.RED);
            label.setOpaque(true);
            label.setFont(new java.awt.Font("Tahoma", 1, 32));
            label.setPreferredSize(new java.awt.Dimension(250, 250));
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            int cnt;

            if (split_str.length > 1) {
                cnt = Integer.parseInt(split_str[1].trim());
            } else {
                cnt = 5;
            }

            final int time_cnt = cnt;

            new javax.swing.Timer(timerDelay, new ActionListener() {
                int timeLeft = 0;// time_cnt;
                boolean window_closed = false;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeLeft <= time_cnt) {
                        timeLeft++;
                        if (timeLeft == 2 && window_closed == false) {

                            if (getCurActivity() != clsDefines.VIDEO_ACTIVITY) {
                                window_closed = true;

                                Window win = SwingUtilities.getWindowAncestor(label);
                                win.setVisible(false);
                                win = null;

                            }
                        }
                    } else {
                        try {
                            driver_alarm_off();
                            //setDig1Started(false);

                        } catch (Exception ex) {
                        }
                        ((javax.swing.Timer) e.getSource()).stop();
                        if (window_closed == false) {
                            if (getCurActivity() != clsDefines.VIDEO_ACTIVITY) {

                                window_closed = true;
                                Window win = SwingUtilities.getWindowAncestor(label);
                                win.setVisible(false);
                                win = null;
                            }
                        }
                    }
                }
            }) {
                {
                    setInitialDelay(0);
                }
            }.start();
            if (getCurActivity() != clsDefines.VIDEO_ACTIVITY) {
                // JOptionPane.showMessageDialog(null, label);
                JOptionPane.showMessageDialog(null, label, "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
        }
    }

    public static void videoall_live(byte from_can) {

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {

                try {
                    if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
                        clsSharedVariables.setVideo1Started(false);

                        clsSharedVariables.setVideo2Started(false);

                        clsSharedVariables.setVideo3Started(false);

                        clsSharedVariables.setVideo4Started(false);
                        if (clsSharedVariables.getCam1Enabled()) {

                            if (objCamView1 != null) {
                                objCamView1.interrupt();
                                objCamView1 = null;
                            }
                            //  Thread.sleep(100);
                            setVideoStarted(true);
                            clsSharedVariables.setVideoAllStarted(true);
                            MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());
                            objCamView1 = new clsCameraView(true);
                            objCamView1.start();

                        }

                        if (clsSharedVariables.getCam2Enabled()) {
                            if (objCamView2 != null) {
                                objCamView2.interrupt();
                                objCamView2 = null;
                            }
                            // Thread.sleep(100);
                            setVideoStarted(true);
                            clsSharedVariables.setVideoAllStarted(true);
                            MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());

                            objCamView2 = new clsCamera2View(true);
                            objCamView2.start();

                        }

                        if (clsSharedVariables.getCam3Enabled()) {
                            if (objCamView3 != null) {
                                objCamView3.interrupt();
                                objCamView3 = null;
                            }
                            //  Thread.sleep(100);
                            setVideoStarted(true);
                            clsSharedVariables.setVideoAllStarted(true);
                            MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());

                            objCamView3 = new clsCamera3View(true);

                            objCamView3.start();

                        }

                        if (clsSharedVariables.getCam4Enabled()) {
                            if (objCamView4 != null) {
                                objCamView4.interrupt();
                                objCamView4 = null;
                            }
                            //  Thread.sleep(100);
                            setVideoStarted(true);
                            clsSharedVariables.setVideoAllStarted(true);
                            MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());

                            objCamView4 = new clsCamera4View(true);

                            objCamView4.start();

                        }
                    }  
                    if (from_can == VIDEO_FROM_VIDEO) {
                        setVideoStarted(true);
                        clsSharedVariables.setVideoAllStarted(true);
                        Thread.sleep(200);
                    }
                } catch (Exception ex) {

                }

                return "";

            }

            @Override

            protected void done() {

            }

        };

        sw1.execute();

    }

    public static void video1_live(boolean from_can) {

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {

                try {
                    if (objCamView1 != null) {
                        objCamView1.interrupt();
                        objCamView1 = null;
                    }
                    Thread.sleep(500);
                    setVideoStarted(true);
                    MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());
                    objCamView1 = new clsCameraView(false);
                    clsSharedVariables.setVideo1Started(true);
                    clsSharedVariables.setVideoAllStarted(false);
                    clsSharedVariables.setVideo2Started(false);
                    clsSharedVariables.setVideo3Started(false);
                    clsSharedVariables.setVideo4Started(false);
                    clsSharedVariables.setVideo5Started(false);
                    clsSharedVariables.setVideo6Started(false);
                    clsSharedVariables.setVideo7Started(false);
                    clsSharedVariables.setVideo8Started(false);

                    if (clsSharedVariables.getCam1Enabled()) {
                        objCamView1.start();
                    }
                    if (from_can == false) {
                        // Thread.sleep(200);
                    }

                } catch (Exception ex) {
                }
                return "";
            }

            @Override
            protected void done() {
            }

        };

        sw1.execute();
    }

    public static void video2_live(boolean from_can) {

        SwingWorker sw1 = new SwingWorker() {

            @Override

            protected String doInBackground() throws Exception {

                try {
                    if (objCamView2 != null) {
                        objCamView2.interrupt();
                        objCamView2 = null;
                    }
                    Thread.sleep(500);
                    setVideoStarted(true);

                    MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());

                    objCamView2 = new clsCamera2View(false);
                    clsSharedVariables.setVideo2Started(true);
                    clsSharedVariables.setVideoAllStarted(false);

                    clsSharedVariables.setVideo1Started(false);

                    clsSharedVariables.setVideo3Started(false);

                    clsSharedVariables.setVideo4Started(false);

                    clsSharedVariables.setVideo5Started(false);

                    clsSharedVariables.setVideo6Started(false);

                    clsSharedVariables.setVideo7Started(false);
                    clsSharedVariables.setVideo8Started(false);

                    setVideoStarted(true);

                    if (clsSharedVariables.getCam2Enabled()) {

                        objCamView2.start();

                    }

                    if (from_can == false) {
                        //    Thread.sleep(200);
                    }

                } catch (Exception ex) {

                }

                return "";

            }

            @Override

            protected void done() {
            }

        };

        sw1.execute();

    }

    public static void video3_live(boolean from_can) {

        SwingWorker sw1 = new SwingWorker() {

            @Override

            protected String doInBackground() throws Exception {

                try {
                    if (objCamView3 != null) {
                        objCamView3.interrupt();
                        objCamView3 = null;
                    }
                    Thread.sleep(500);
                    setVideoStarted(true);

                    MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());

                    objCamView3 = new clsCamera3View(false);

                    clsSharedVariables.setVideo3Started(true);

                    clsSharedVariables.setVideoAllStarted(false);

                    clsSharedVariables.setVideo1Started(false);

                    clsSharedVariables.setVideo2Started(false);

                    clsSharedVariables.setVideo4Started(false);

                    clsSharedVariables.setVideo5Started(false);

                    clsSharedVariables.setVideo6Started(false);

                    clsSharedVariables.setVideo7Started(false);
                    clsSharedVariables.setVideo8Started(false);

                    if (clsSharedVariables.getCam3Enabled()) {

                        objCamView3.start();

                    }

                    if (from_can == false) {
                        //    Thread.sleep(200);
                    }

                } catch (Exception ex) {

                }

                return "";

            }

            @Override

            protected void done() {

            }

        };

        sw1.execute();

    }

    public static void video4_live(boolean from_can) {

        SwingWorker sw1 = new SwingWorker() {

            @Override

            protected String doInBackground() throws Exception {

                try {
                    if (objCamView4 != null) {
                        objCamView4.interrupt();
                        objCamView4 = null;
                    }
                    Thread.sleep(500);
                    setVideoStarted(true);

                    MainFrmIts.setPreVideoLiveState(MainFrmIts.getPreVideoLiveState());

                    objCamView4 = new clsCamera4View(false);

                    clsSharedVariables.setVideo4Started(true);

                    clsSharedVariables.setVideoAllStarted(false);

                    clsSharedVariables.setVideo1Started(false);

                    clsSharedVariables.setVideo2Started(false);

                    clsSharedVariables.setVideo3Started(false);

                    clsSharedVariables.setVideo5Started(false);

                    clsSharedVariables.setVideo6Started(false);

                    clsSharedVariables.setVideo7Started(false);
                    clsSharedVariables.setVideo8Started(false);

                    if (clsSharedVariables.getCam4Enabled()) {

                        objCamView4.start();

                    }

                    if (from_can == false) {
                        //  Thread.sleep(200);

                    }

                } catch (Exception ex) {

                }

                return "";

            }

            @Override

            protected void done() {

            }

        };

        sw1.execute();

    }

    

    private void btnRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteActionPerformed
                savedVideoState = 0;

        stop_video();
        panCenterMainPane.setVisible(false);

        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }
        objPanel = new PanelRoute(panMainPane, panCenterMainPane);
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
    }//GEN-LAST:event_btnRouteActionPerformed

    private void btnHomebtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomebtnHomeActionPerformed
        savedVideoState = 0;
        btnLiveCamAll.setBackground(Color.WHITE);
        btnLiveCam1.setBackground(Color.WHITE);
        btnLiveCam2.setBackground(Color.WHITE);
        btnLiveCam3.setBackground(Color.WHITE);
        btnLiveCam4.setBackground(Color.WHITE);
       
        setCurActivity(clsDefines.MAIN_ACTIVITY);
        panCenterMainPane.setVisible(true);
        btnFire.setVisible(true);
        btnBreakDown.setVisible(true);
        btnSos.setVisible(true);
        btnCanAlarm.setVisible(true);
        jLayeredPaneLive.setVisible(true);
        jLayeredPaneLiveButtons.setVisible(false);
        setVideoLiveState((byte) 0);
        setPreVideoLiveState((byte) 0);
        setReverseCameraOn(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setVideoEnableStarted(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        MainFrmIts.ButtonStop.setVisible(false);

        if ((clsSharedVariables.getFmRadioEnable() == true)) {
            this.btnEmergency.setVisible(true);
            btnEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/radio.png")));
            btnEmergency.setText("<html>RADIO<br>रेडियो</html>");
        } else if (clsSharedVariables.getEmergencyServicesEnable() == false) {
            this.btnEmergency.setVisible(false);
        } else if (clsSharedVariables.getEmergencyServicesEnable() == true) {
            this.btnEmergency.setVisible(true);
            btnEmergency.setIcon(imgAmbulance);
            btnEmergency.setText("<html>Emergency<br> आपातकालीन</html>");
        }
        try {
            stop_video();
        } catch (Exception ex) {
        }
        if (get_voice_call_start_status() == true) {
            set_voice_call_start_status(false);

            set_voice_call_enabled_status(false);

            PanPhone objPhone = new PanPhone();

            objPhone.close_call_end_gpio_controls();

            objPhone = null;

            try {

                clsAtSerialPort.atSerialWrite("ATH\r\n");

                clsAtSerialPort.atSerialWrite("ATH\r\n");

                clsAtSerialPort.atSerialWrite("ATH\r\n");

            } catch (Exception ex) {

            }

            set_phonecall_type(CALL_NOCARRIER);

        }

        if (objPanel != null) {
            panMainPane.remove(objPanel);
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }

        objPanel = panCenterMainPane;
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.setVisible(true);
        panMainPane.revalidate();
        panMainPane.repaint();

        try {

            clsReadFiles objReadFilesAct = new clsReadFiles();
            objReadFilesAct.write_activity_name(clsDefines.MAIN_ACTIVITY);
            objReadFilesAct = null;

        } catch (Exception ex) {
        }
        clsSharedVariables.setDiagDispForm(false);

    }//GEN-LAST:event_btnHomebtnHomeActionPerformed

    private void btnPhoneActionEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhoneActionEvent
//        savedVideoState = 0;
//
//        stop_video();
//        panCenterMainPane.setVisible(false);
//
//        if (objPanel != null) {
//
//            if (objPanel != panCenterMainPane) {
//
//                objPanel.removeAll();
//            }
//
//            objPanel = null;
//        }
//
//        objPanel = new PanPhone();
//        panMainPane.removeAll();
//        panMainPane.setLayout(new java.awt.BorderLayout());
//        panMainPane.add(objPanel);
//        panMainPane.revalidate();
//        panMainPane.repaint();
    }//GEN-LAST:event_btnPhoneActionEvent
    private void setVideoButtonsEnable() {
        if (clsSharedVariables.getCam1Enabled() == true) {
            btnLiveCam1.setVisible(true);
        } else {
            btnLiveCam1.setVisible(false);
        }
        if (clsSharedVariables.getCam2Enabled() == true) {
            btnLiveCam2.setVisible(true);
        } else {
            btnLiveCam2.setVisible(false);
        }
        if (clsSharedVariables.getCam3Enabled() == true) {
            btnLiveCam3.setVisible(true);
        } else {
            btnLiveCam3.setVisible(false);
        }
        if (clsSharedVariables.getCam4Enabled() == true) {
            btnLiveCam4.setVisible(true);
        } else {
            btnLiveCam4.setVisible(false);
        }
      

    }
    byte cam_no_button = 0;
    private void btnVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVideoActionPerformed
        //  stop_video();
       
        savedVideoState = 1;
        System.out.println("entered into cam panels");
        setVideoStarted(true);
        setVideoEnableStarted(true);
        videoall_live(VIDEO_FROM_VIDEO);
        System.out.println("updating guissss");
        btnHome.setEnabled(false);
        btnLiveCamAll.setBackground(Color.GREEN);
        setVideoLiveState((byte) 0);
        setPreVideoLiveState((byte) 0);
        setVideoLiveState((byte) 5);
        setPreVideoLiveState((byte) 5);
        btnFire.setVisible(false);
        btnBreakDown.setVisible(false);
        btnSos.setVisible(false);
        jLayeredPaneLive.setVisible(false);
        jLayeredPaneLiveButtons.setVisible(true);
        btnCanAlarm.setVisible(false);
        setVideoButtonsEnable();
        btnLiveCamAll.setEnabled(false);
        btnLiveCam1.setEnabled(false);
        btnLiveCam2.setEnabled(false);
        btnLiveCam3.setEnabled(false);
        btnLiveCam4.setEnabled(false);
        
        cam_no_button = 0;
        panCenterMainPane.setVisible(false);
        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }

        try {
            if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
                clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
            } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
                clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam5_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam6_status_live(clsDefines.CAM_CONNECTING);
            } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
                clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam5_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam6_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam7_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam8_status_live(clsDefines.CAM_CONNECTING);
            }
            clsSharedVariables.setVideoAllStarted(true);
            //   videoall_live(VIDEO_FROM_VIDEO);

        } catch (Exception ex) {
        }
        objPanel = new PanVideo();

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();
        if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
            if (clsSharedVariables.getCam1Enabled() == false) {
                PanVideo.lblCam1.setText("");
            } else {
                PanVideo.lblCam1.setText("CAM1 connecting...");
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                PanVideo.lblCam2.setText("");
            } else {
                PanVideo.lblCam2.setText("CAM2 connecting...");
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                PanVideo.lblCam3.setText("");
            } else {
                PanVideo.lblCam3.setText("CAM3 connecting...");
            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                PanVideo.lblCam4.setText("");
            } else {
                PanVideo.lblCam4.setText("CAM4 connecting...");
            }
        } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
            if (clsSharedVariables.getCam1Enabled() == false) {
                PanVideo.lblCam1.setText("");
            } else {
                PanVideo.lblCam1.setText("CAM1 connecting...");
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                PanVideo.lblCam2.setText("");
            } else {
                PanVideo.lblCam2.setText("CAM2 connecting...");
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                PanVideo.lblCam3.setText("");
            } else {
                PanVideo.lblCam3.setText("CAM3 connecting...");
            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                PanVideo.lblCam4.setText("");
            } else {
                PanVideo.lblCam4.setText("CAM4 connecting...");
            }
            if (clsSharedVariables.getCam5Enabled() == false) {
                PanVideo.lblCam5.setText("");
            } else {
                PanVideo.lblCam5.setText("CAM5 connecting...");
            }
            if (clsSharedVariables.getCam6Enabled() == false) {
                PanVideo.lblCam6.setText("");
            } else {
                PanVideo.lblCam6.setText("CAM6 connecting...");
            }
        } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
            if (clsSharedVariables.getCam1Enabled() == false) {
                PanVideo.lblCam1.setText("");
            } else {
                PanVideo.lblCam1.setText("CAM1 connecting...");
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                PanVideo.lblCam2.setText("");
            } else {
                PanVideo.lblCam2.setText("CAM2 connecting...");
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                PanVideo.lblCam3.setText("");
            } else {
                PanVideo.lblCam3.setText("CAM3 connecting...");
            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                PanVideo.lblCam4.setText("");
            } else {
                PanVideo.lblCam4.setText("CAM4 connecting...");
            }
            if (clsSharedVariables.getCam5Enabled() == false) {
                PanVideo.lblCam5.setText("");
            } else {
                PanVideo.lblCam5.setText("CAM5 connecting...");
            }
            if (clsSharedVariables.getCam6Enabled() == false) {
                PanVideo.lblCam6.setText("");
            } else {
                PanVideo.lblCam6.setText("CAM6 connecting...");
            }
            if (clsSharedVariables.getCam7Enabled() == false) {
                PanVideo.lblCam7.setText("");
            } else {
                PanVideo.lblCam7.setText("CAM7 connecting...");
            }
            if (clsSharedVariables.getCam8Enabled() == false) {
                PanVideo.lblCam8.setText("");
            } else {
                PanVideo.lblCam8.setText("CAM8 connecting...");
            }
        }
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                if (reverseActive) {
                    return "fail";
                }

                int i = 0;
                for (i = 0; i < 10; i++) {
                    if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_DISCONNECTED || clsSharedVariables.getCam1_status_live() == clsDefines.CAM_NO_ROUTE_TO_HOST) {
                        // return "success";
                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
                        if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED) {

                            return "success";
                        }
                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
                        if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam5_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam6_status_live() == clsDefines.CAM_CONNECTED) {

                            return "success";
                        }

                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
                        if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam5_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam6_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam7_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam8_status_live() == clsDefines.CAM_CONNECTED) {

                            return "success";
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                    if (cam_no_button != 0) {
                        break;
                    }
                }
                return "fail";
            }

            @Override
            protected void done() {
                if (reverseActive) {
                    return;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }

                if (cam_no_button == 0) {
                    if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {

                        if (!clsSharedVariables.getVideo1Connected() && clsSharedVariables.getCam1Enabled()) {
                            PanVideo.lblCam1.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo2Connected() && clsSharedVariables.getCam2Enabled()) {
                            PanVideo.lblCam2.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo3Connected() && clsSharedVariables.getCam3Enabled()) {
                            PanVideo.lblCam3.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo4Connected() && clsSharedVariables.getCam4Enabled()) {
                            PanVideo.lblCam4.setText("CAMERA DISCONNECTED");
                        }
                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
                        if (!clsSharedVariables.getVideo1Connected() && clsSharedVariables.getCam1Enabled()) {
                            PanVideo.lblCam1.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo2Connected() && clsSharedVariables.getCam2Enabled()) {
                            PanVideo.lblCam2.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo3Connected() && clsSharedVariables.getCam3Enabled()) {
                            PanVideo.lblCam3.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo4Connected() && clsSharedVariables.getCam4Enabled()) {
                            PanVideo.lblCam4.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo5Connected() && clsSharedVariables.getCam5Enabled()) {
                            PanVideo.lblCam5.setText("CAMERA DISCONNECTED");
                        }

                        if (!clsSharedVariables.getVideo6Connected() && clsSharedVariables.getCam6Enabled()) {
                            PanVideo.lblCam6.setText("CAMERA DISCONNECTED");
                        }

                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
                        if (!clsSharedVariables.getVideo1Connected() && clsSharedVariables.getCam1Enabled()) {
                            PanVideo.lblCam1.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo2Connected() && clsSharedVariables.getCam2Enabled()) {
                            PanVideo.lblCam2.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo3Connected() && clsSharedVariables.getCam3Enabled()) {
                            PanVideo.lblCam3.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo4Connected() && clsSharedVariables.getCam4Enabled()) {
                            PanVideo.lblCam4.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo5Connected() && clsSharedVariables.getCam5Enabled()) {
                            PanVideo.lblCam5.setText("CAMERA DISCONNECTED");
                        }

                        if (!clsSharedVariables.getVideo6Connected() && clsSharedVariables.getCam6Enabled()) {
                            PanVideo.lblCam6.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo7Connected() && clsSharedVariables.getCam7Enabled()) {
                            PanVideo.lblCam7.setText("CAMERA DISCONNECTED");
                        }
                        if (!clsSharedVariables.getVideo8Connected() && clsSharedVariables.getCam8Enabled()) {
                            PanVideo.lblCam8.setText("CAMERA DISCONNECTED");
                        }
                    }
                    btnLiveCamAll.setEnabled(true);
                    btnLiveCam1.setEnabled(true);
                    btnLiveCam2.setEnabled(true);
                    btnLiveCam3.setEnabled(true);
                    btnLiveCam4.setEnabled(true);
                   
                    btnHome.setEnabled(true);
                }
            }
        };
        sw1.execute();

    }//GEN-LAST:event_btnVideoActionPerformed

    private void btnVhmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVhmdActionPerformed
//        savedVideoState = 0;
//
//        stop_video();
//        panCenterMainPane.setVisible(false);
//
//        if (objPanel != null) {
//            if (objPanel != panCenterMainPane) {
//                objPanel.removeAll();
//            }
//            objPanel = null;
//        }
//
//        objPanel = new PanCanDisplay();
//
//        panMainPane.removeAll();
//
//        panMainPane.setLayout(new java.awt.BorderLayout());
//
//        panMainPane.add(objPanel);
//
//        panMainPane.revalidate();
//
//        panMainPane.repaint();

    }//GEN-LAST:event_btnVhmdActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        savedVideoState = 0;

        stop_video();
        panCenterMainPane.setVisible(false);

        if (objPanel != null) {

            if (objPanel != panCenterMainPane) {

                objPanel.removeAll();

            }

            objPanel = null;

        }

        objPanel = new PanConfiguration();

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();

        setCurActivity(clsDefines.MAIN_ACTIVITY);

        try {

            clsReadFiles objReadFilesAct = new clsReadFiles();

            objReadFilesAct.write_activity_name(clsDefines.MAIN_ACTIVITY);

            objReadFilesAct = null;

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnEmergencybtnFileManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmergencybtnFileManagerActionPerformed
//        savedVideoState = 0;
//
//        stop_video();
//
//        setEmergencyMapPktCame(false);
//
//        btnEmergency.setBackground(java.awt.SystemColor.controlHighlight);
//
//        panCenterMainPane.setVisible(false);
//
//        if (objPanel != null) {
//            panMainPane.remove(objPanel);
//
//            objPanel = null;
//
//        }
//
//        if ((clsSharedVariables.getFmRadioEnable() == true)) {
//
//            objPanel = new PanFmRadio();
//            btnEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/radio.png")));
//            btnEmergency.setText("<html>RADIO<br> रेडियो</html>");
//
//            panMainPane.removeAll();
//
//            panMainPane.setLayout(new java.awt.BorderLayout());
//
//            panMainPane.add(objPanel);
//
//            panMainPane.revalidate();
//
//            panMainPane.repaint();
//        } else if (clsSharedVariables.getEmergencyServicesEnable() == true) {
//            btnEmergency.setText("<html>Emergency<br> आपातकालीन</html>");
//
//            objPanel = new PanEmergencyMap();
//
//            btnEmergency.setIcon(imgAmbulance);
//            panMainPane.removeAll();
//
//            panMainPane.setLayout(new java.awt.BorderLayout());
//
//            panMainPane.add(objPanel);
//
//            panMainPane.revalidate();
//
//            panMainPane.repaint();
//        }
//        setCurActivity(clsDefines.EMERGENCY_ALERTS_ACTIVITY);
//
//        try {
//
//            clsReadFiles objReadFilesAct = new clsReadFiles();
//
//            objReadFilesAct.write_activity_name(clsDefines.EMERGENCY_ALERTS_ACTIVITY);
//
//            objReadFilesAct = null;
//
//        } catch (Exception ex) {
//
//        }
    }//GEN-LAST:event_btnEmergencybtnFileManagerActionPerformed

    private void btnDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosisActionPerformed
                savedVideoState = 0;

        stop_video();
        String m = getPassword("Configuration Password");
        if (m == null) {

            close_keypad();

        } else if (m.equals(configuration_pwd)) {

            panCenterMainPane.setVisible(false);

            if (objPanel != null) {

                if (objPanel != panCenterMainPane) {

                    objPanel.removeAll();

                }

                objPanel = null;

            }

            objPanel = new PanAllDiagnosis();

            panMainPane.removeAll();

            panMainPane.setLayout(new java.awt.BorderLayout());

            panMainPane.add(objPanel);

            panMainPane.revalidate();

            panMainPane.repaint();

        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();
        m = null;

        setCurActivity(clsDefines.MAIN_ACTIVITY);

        try {

            clsReadFiles objReadFilesAct = new clsReadFiles();

            objReadFilesAct.write_activity_name(clsDefines.MAIN_ACTIVITY);

            objReadFilesAct = null;

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnDiagnosisActionPerformed

    private void btnSosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSosActionPerformed
        setVideoLiveState((byte) 0);
        setPreVideoLiveState((byte) 0);
        stop_video();
        setVideoEnableStarted(false);
        setReverseCameraOn(false);
        panCenterMainPane.setVisible(false);

        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }
        objPanel = new PanSos();

        panMainPane.removeAll();

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();
        setCurActivity(clsDefines.SOS_ALERTS_ACTIVITY);
        try {
            clsReadFiles objReadFilesAct = new clsReadFiles();
            objReadFilesAct.write_activity_name(clsDefines.SOS_ALERTS_ACTIVITY);
            objReadFilesAct = null;
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btnSosActionPerformed

    private void btnMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMessagesActionPerformed
  savedVideoState = 0;
        stop_video();

        panCenterMainPane.setVisible(false);

        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }

        objPanel = new PanPreloads();

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();

    }//GEN-LAST:event_btnMessagesActionPerformed

    private void btnFireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFireActionPerformed

        // TODO add your handling code here:
        if (objDriving == null) {
            objDriving = new gpsDriving();
        }

        if (obj16833Pkts == null) {
            obj16833Pkts = new cls16833Protocols();
        }
        try {

            set_panic_data_sent_server(false);
            if (clsSharedVariables.getIpAddr1Enable()) {
                obj16833Pkts.panic_messages(CODED_FIRE);
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                objDriving.panic_messages(CODED_FIRE);
            }
            if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_EMERGENCY_ALERT);

            }

            show_message_dialogbox("FIRE");

        } catch (Exception ex) {
        }

        setCurActivity(clsDefines.MAIN_ACTIVITY);

        try {
            clsReadFiles objReadFilesAct = new clsReadFiles();
            objReadFilesAct.write_activity_name(clsDefines.MAIN_ACTIVITY);
            objReadFilesAct = null;
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_btnFireActionPerformed

    private void btnBreakDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBreakDownActionPerformed

        // TODO add your handling code here:
        if (objDriving == null) {
            objDriving = new gpsDriving();
        }

        if (obj16833Pkts == null) {
            obj16833Pkts = new cls16833Protocols();
        }

        try {

            set_panic_data_sent_server(false);

            if (clsSharedVariables.getIpAddr1Enable()) {
                obj16833Pkts.panic_messages(CODED_BREAKDOWN);
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                objDriving.panic_messages(CODED_BREAKDOWN);
            }
            show_message_dialogbox("BRAKE DOWN");

        } catch (Exception ex) {

        }

        setCurActivity(clsDefines.MAIN_ACTIVITY);

        try {

            clsReadFiles objReadFilesAct = new clsReadFiles();

            objReadFilesAct.write_activity_name(clsDefines.MAIN_ACTIVITY);

            objReadFilesAct = null;

        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btnBreakDownActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed

        // TODO add your handling code here:
        lblNetworkOperatorName.setText("");

        if (objDriving == null) {
            objDriving = new gpsDriving();
        }

        if (obj16833Pkts == null) {
            obj16833Pkts = new cls16833Protocols();
        }

        if (clsSharedVariables.getIpAddr1Enable()) {
            obj16833Pkts.duty_end_pkt();
        }
        if (clsSharedVariables.getIpAddr4Enable()) {
            objDriving.duty_end_pkt();
        }
        stop_video();
        setReverseCameraOn(false);

        if (getWifiConnect() == true) {

            show_message_dialogbox("WIFI is Enabled, please disable it ");

            return;

        }

        if (objPanel != null) {

            if (objPanel != panCenterMainPane) {

                objPanel.removeAll();

            }

            objPanel = null;

        }

        objPanel = new PanLogin(panCenterMainPane, panMainPane, btnLogout, btnList, PanLeftpane, lbldriverid);// PanelRoute(panMainPane);

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();

        PanLeftpane.setVisible(false);

        btnHome.setVisible(false);

        btnLogout.setEnabled(false);

    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCanAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanAlarmActionPerformed

        setVideoLiveState((byte) 0);
        setPreVideoLiveState((byte) 0);
        stop_video();
        setVideoEnableStarted(false);
        setReverseCameraOn(false);

        panCenterMainPane.setVisible(false);

        if (objPanel != null) {
            objPanel = null;
        }

        objPanel = new PanCanAlerts();

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();

        setCurActivity(clsDefines.CAN_ALERTS_ACTIVITY);

        try {

            clsReadFiles objReadFilesAct = new clsReadFiles();

            objReadFilesAct.write_activity_name(clsDefines.CAN_ALERTS_ACTIVITY);

            objReadFilesAct = null;

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnCanAlarmActionPerformed

    private void btnfileManager1btnFileManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfileManager1btnFileManagerActionPerformed
               savedVideoState = 0;

        stop_video();
        String m = getPassword("Configuration Password");

        if (m.equals(configuration_pwd)) {

            panCenterMainPane.setVisible(false);

            if (objPanel != null) {

                if (objPanel != panCenterMainPane) {

                    objPanel.removeAll();

                }

                objPanel = null;

            }

            objPanel = new PanFileTransfer();

            panMainPane.removeAll();

            panMainPane.setLayout(new java.awt.BorderLayout());

            panMainPane.add(objPanel);

            panMainPane.revalidate();

            panMainPane.repaint();

        } else {
            show_message_dialogbox("Wrong Password");
        }

        close_keypad();
        m = null;

        setCurActivity(clsDefines.MAIN_ACTIVITY);

        try {

            clsReadFiles objReadFilesAct = new clsReadFiles();

            objReadFilesAct.write_activity_name(clsDefines.MAIN_ACTIVITY);

            objReadFilesAct = null;

        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btnfileManager1btnFileManagerActionPerformed

    private void panelTopFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelTopFocusGained

    }//GEN-LAST:event_panelTopFocusGained

    private void panelTopFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelTopFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_panelTopFocusLost

    private void ButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStopActionPerformed
        // TODO add your handling code here:
        MainFrmIts.ButtonStop.setVisible(false);
        setVideoEnableStarted(false);
        clsSharedVariables.setVideoPlayStarted(false);
        SwingUtilities.invokeLater(() -> {
            Process p;
            try {
                if (mpv_found == true) {
                    p = Runtime.getRuntime().exec("sudo pkill mpv");
                }
                if (play_process_id > 0) {
                    p = Runtime.getRuntime().exec("sudo pkill ffplay");
                    p.waitFor();
                }
                if (play_video_process != null && play_video_process.exitValue() != 0) {
                    play_video_process.destroy();
                    play_video_process = null;
                }
            } catch (Exception ex) {

            }
        });
    }//GEN-LAST:event_ButtonStopActionPerformed

    private void btnLiveCamAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiveCamAllActionPerformed
        // TODO add your handling code here:
    

        savedVideoState = 1;
        stop_video();

        videoall_live(VIDEO_FROM_VIDEO);

        cam_no_button = 0;
        setVideoLiveState((byte) 5);
        btnLiveCamAll.setBackground(Color.GREEN);
        btnLiveCam1.setBackground(Color.WHITE);
        btnLiveCam2.setBackground(Color.WHITE);
        btnLiveCam3.setBackground(Color.WHITE);
        btnLiveCam4.setBackground(Color.WHITE);
       
        btnLiveCamAll.setEnabled(false);
        btnLiveCam1.setEnabled(false);
        btnLiveCam2.setEnabled(false);
        btnLiveCam3.setEnabled(false);
        btnLiveCam4.setEnabled(false);
       
        btnHome.setEnabled(false);

        // stop_video();
        panCenterMainPane.setVisible(false);
        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }
        try {
            if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
                clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
            } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
                clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam5_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam6_status_live(clsDefines.CAM_CONNECTING);
            } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
                clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam5_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam6_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam7_status_live(clsDefines.CAM_CONNECTING);
                clsSharedVariables.setCam8_status_live(clsDefines.CAM_CONNECTING);
            }
            //  videoall_live(VIDEO_FROM_VIDEO);
        } catch (Exception ex) {

        }
        objPanel = new PanVideo();
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
        if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
            if (clsSharedVariables.getCam1Enabled() == false) {
                PanVideo.lblCam1.setText("");
            } else {
                PanVideo.lblCam1.setText("CAM1 connecting...");
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                PanVideo.lblCam2.setText("");
            } else {
                PanVideo.lblCam2.setText("CAM2 connecting...");
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                PanVideo.lblCam3.setText("");
            } else {
                PanVideo.lblCam3.setText("CAM3 connecting...");
            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                PanVideo.lblCam4.setText("");
            } else {

                PanVideo.lblCam4.setText("CAM4 connecting...");

            }
        } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
            if (clsSharedVariables.getCam1Enabled() == false) {
                PanVideo.lblCam1.setText("");
            } else {
                PanVideo.lblCam1.setText("CAM1 connecting...");
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                PanVideo.lblCam2.setText("");
            } else {
                PanVideo.lblCam2.setText("CAM2 connecting...");
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                PanVideo.lblCam3.setText("");
            } else {
                PanVideo.lblCam3.setText("CAM3 connecting...");
            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                PanVideo.lblCam4.setText("");
            } else {

                PanVideo.lblCam4.setText("CAM4 connecting...");

            }
            if (clsSharedVariables.getCam5Enabled() == false) {
                PanVideo.lblCam5.setText("");
            } else {

                PanVideo.lblCam5.setText("CAM5 connecting...");

            }
            if (clsSharedVariables.getCam6Enabled() == false) {
                PanVideo.lblCam6.setText("");
            } else {

                PanVideo.lblCam6.setText("CAM6 connecting...");

            }
        } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
            if (clsSharedVariables.getCam1Enabled() == false) {
                PanVideo.lblCam1.setText("");
            } else {
                PanVideo.lblCam1.setText("CAM1 connecting...");
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                PanVideo.lblCam2.setText("");
            } else {
                PanVideo.lblCam2.setText("CAM2 connecting...");
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                PanVideo.lblCam3.setText("");
            } else {
                PanVideo.lblCam3.setText("CAM3 connecting...");
            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                PanVideo.lblCam4.setText("");
            } else {

                PanVideo.lblCam4.setText("CAM4 connecting...");

            }
            if (clsSharedVariables.getCam5Enabled() == false) {
                PanVideo.lblCam5.setText("");
            } else {

                PanVideo.lblCam5.setText("CAM5 connecting...");

            }
            if (clsSharedVariables.getCam6Enabled() == false) {
                PanVideo.lblCam6.setText("");
            } else {

                PanVideo.lblCam6.setText("CAM6 connecting...");

            }
            if (clsSharedVariables.getCam7Enabled() == false) {
                PanVideo.lblCam7.setText("");
            } else {
                PanVideo.lblCam7.setText("CAM7 connecting...");
            }
            if (clsSharedVariables.getCam8Enabled() == false) {
                PanVideo.lblCam8.setText("");
            } else {
                PanVideo.lblCam8.setText("CAM8 connecting...");
            }
        }

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
 


                int i = 0;
                for (i = 0; i < 10; i++) {
                    if (clsSharedVariables.getCam_status_live() == clsDefines.CAM_DISCONNECTED || clsSharedVariables.getCam_status_live() == clsDefines.CAM_NO_ROUTE_TO_HOST) {
                        return "success";
                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
                        if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED) {
                            return "success";
                        }
                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
                        if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam5_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam6_status_live() == clsDefines.CAM_CONNECTED) {
                            return "success";
                        }
                    } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
                        if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam5_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam6_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam7_status_live() == clsDefines.CAM_CONNECTED
                                && clsSharedVariables.getCam8_status_live() == clsDefines.CAM_CONNECTED) {
                            return "success";
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
                return "fail";
            }

            @Override
            protected void done() {


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                if (!clsSharedVariables.getVideo1Connected()) {
                    lblCam1.setText("CAMERA DISCONNECTED");
                }
                btnLiveCamAll.setEnabled(true);
                btnLiveCam1.setEnabled(true);
                btnLiveCam2.setEnabled(true);
                btnLiveCam3.setEnabled(true);
                btnLiveCam4.setEnabled(true);
               
                btnHome.setEnabled(true);

            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnLiveCamAllActionPerformed

    private void btnLiveCam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiveCam1ActionPerformed
        // TODO add your handling code here:

        savedVideoState = 2;
        setVideoLiveState((byte) 1);
        stop_video();

        video1_live(false);

        btnLiveCamAll.setBackground(Color.WHITE);
        btnLiveCam1.setBackground(Color.GREEN);
        btnLiveCam2.setBackground(Color.WHITE);
        btnLiveCam3.setBackground(Color.WHITE);
        btnLiveCam4.setBackground(Color.WHITE);
        
        btnLiveCamAll.setEnabled(false);
        btnLiveCam1.setEnabled(false);
        btnLiveCam2.setEnabled(false);
        btnLiveCam3.setEnabled(false);
        btnLiveCam4.setEnabled(false);
       
        btnHome.setEnabled(false);

        // stop_video();
        panCenterMainPane.setVisible(false);
        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }

        try {
            clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
            clsSharedVariables.setCam1_status_live(clsDefines.CAM_CONNECTING);
            //        video1_live(false);
        } catch (Exception ex) {
        }
        
        objPanel = new PanVideo();

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {


                int i = 0;
                for (i = 0; i < 10; i++) {
                    if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_DISCONNECTED || clsSharedVariables.getCam1_status_live() == clsDefines.CAM_NO_ROUTE_TO_HOST) {
                        return "success";
                    } else if (clsSharedVariables.getCam1_status_live() == clsDefines.CAM_CONNECTED) {

                        return "success";
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
                return "fail";
            }

            @Override
            protected void done() {


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                if (!clsSharedVariables.getVideo1Connected()) {
                    lblCam1.setText("CAMERA DISCONNECTED");
                }
                btnLiveCamAll.setEnabled(true);
                btnLiveCam1.setEnabled(true);
                btnLiveCam2.setEnabled(true);
                btnLiveCam3.setEnabled(true);
                btnLiveCam4.setEnabled(true);
               
                btnHome.setEnabled(true);

            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnLiveCam1ActionPerformed

    private void btnLiveCam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiveCam2ActionPerformed
        // TODO add your handling code here:
   

        savedVideoState = 3;
        setVideoLiveState((byte) 2);
        stop_video();

        video2_live(false);
        btnHome.setEnabled(false);
        btnLiveCamAll.setBackground(Color.WHITE);
        btnLiveCam1.setBackground(Color.WHITE);
        btnLiveCam2.setBackground(Color.GREEN);
        btnLiveCam3.setBackground(Color.WHITE);
        btnLiveCam4.setBackground(Color.WHITE);
       
        btnLiveCamAll.setEnabled(false);
        btnLiveCam1.setEnabled(false);
        btnLiveCam2.setEnabled(false);
        btnLiveCam3.setEnabled(false);
        btnLiveCam4.setEnabled(false);
        
        panCenterMainPane.setVisible(false);
        if (objPanel != null) {

            if (objPanel != panCenterMainPane) {

                objPanel.removeAll();
            }
            objPanel = null;
        }
        try {
            clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
            clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTING);
            //  video2_live(false);
        } catch (Exception ex) {

        }
        objPanel = new PanVideo();
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
   

                int i;
                for (i = 0; i < 10; i++) {
                    if (clsSharedVariables.getCam2_status_live() == clsDefines.CAM_DISCONNECTED || clsSharedVariables.getCam2_status_live() == clsDefines.CAM_NO_ROUTE_TO_HOST) {
                        return "success";
                    } else if (clsSharedVariables.getCam2_status_live() == clsDefines.CAM_CONNECTED) {

                        return "success";
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
                return "fail";
            }

            @Override
            protected void done() {
     
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }

                if (!clsSharedVariables.getVideo2Connected()) {
                    lblCam1.setText("CAMERA DISCONNECTED");
                }
                btnLiveCamAll.setEnabled(true);
                btnLiveCam1.setEnabled(true);
                btnLiveCam2.setEnabled(true);
                btnLiveCam3.setEnabled(true);
                btnLiveCam4.setEnabled(true);
               
                btnHome.setEnabled(true);

            }
        };
        // executes the swingworker on worker thread
        sw1.execute();

    }//GEN-LAST:event_btnLiveCam2ActionPerformed

    private void btnLiveCam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiveCam3ActionPerformed
        // TODO add your handling code here:


        savedVideoState = 4;
        setVideoLiveState((byte) 3);
        stop_video();

        video3_live(false);
        btnHome.setEnabled(false);

        btnLiveCamAll.setBackground(Color.WHITE);
        btnLiveCam1.setBackground(Color.WHITE);
        btnLiveCam2.setBackground(Color.WHITE);
        btnLiveCam3.setBackground(Color.GREEN);
        btnLiveCam4.setBackground(Color.WHITE);
        
        btnLiveCamAll.setEnabled(false);
        btnLiveCam1.setEnabled(false);
        btnLiveCam2.setEnabled(false);
        btnLiveCam3.setEnabled(false);
        btnLiveCam4.setEnabled(false);
       
        panCenterMainPane.setVisible(false);
        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }
        clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
        clsSharedVariables.setCam3_status_live(clsDefines.CAM_CONNECTING);
        try {
        } catch (Exception ex) {

        }
        objPanel = new PanVideo();

        panMainPane.removeAll();

        panMainPane.setLayout(new java.awt.BorderLayout());

        panMainPane.add(objPanel);

        panMainPane.revalidate();

        panMainPane.repaint();

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {


                int i = 0;
                for (i = 0; i < 10; i++) {
                    if (clsSharedVariables.getCam3_status_live() == clsDefines.CAM_DISCONNECTED || clsSharedVariables.getCam3_status_live() == clsDefines.CAM_NO_ROUTE_TO_HOST) {
                        return "success";
                    } else if (clsSharedVariables.getCam3_status_live() == clsDefines.CAM_CONNECTED) {
                        return "success";
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
                return "fail";
            }

            @Override
            protected void done() {


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                if (!clsSharedVariables.getVideo3Connected()) {
                    lblCam1.setText("CAMERA DISCONNECTED");
                }
                btnLiveCamAll.setEnabled(true);
                btnLiveCam1.setEnabled(true);
                btnLiveCam2.setEnabled(true);
                btnLiveCam3.setEnabled(true);
                btnLiveCam4.setEnabled(true);
                
                btnHome.setEnabled(true);

            }
        };
        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnLiveCam3ActionPerformed

    private void btnLiveCam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiveCam4ActionPerformed
        // TODO add your handling code here:
        if (reverseActive) {
    return;
}

        savedVideoState = 5;
        setVideoLiveState((byte) 4);
        stop_video();
        video4_live(false);
        btnLiveCamAll.setBackground(Color.WHITE);
        btnLiveCam1.setBackground(Color.WHITE);
        btnLiveCam2.setBackground(Color.WHITE);
        btnLiveCam3.setBackground(Color.WHITE);
        btnLiveCam4.setBackground(Color.GREEN);
       
        btnLiveCamAll.setEnabled(false);
        btnLiveCam1.setEnabled(false);
        btnLiveCam2.setEnabled(false);
        btnLiveCam3.setEnabled(false);
        btnLiveCam4.setEnabled(false);
       
        btnHome.setEnabled(false);

        panCenterMainPane.setVisible(false);
        if (objPanel != null) {
            if (objPanel != panCenterMainPane) {
                objPanel.removeAll();
            }
            objPanel = null;
        }
        clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTING);
        clsSharedVariables.setCam4_status_live(clsDefines.CAM_CONNECTING);
        try {

        } catch (Exception ex) {

        }
        objPanel = new PanVideo();
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
 

                int i = 0;
                for (i = 0; i < 10; i++) {
                    if (clsSharedVariables.getCam4_status_live() == clsDefines.CAM_DISCONNECTED || clsSharedVariables.getCam4_status_live() == clsDefines.CAM_NO_ROUTE_TO_HOST) {
                        return "success";
                    } else if (clsSharedVariables.getCam4_status_live() == clsDefines.CAM_CONNECTED) {

                        return "success";
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }
                return "fail";
            }

            @Override
            protected void done() {
  

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                }
                if (!clsSharedVariables.getVideo4Connected()) {
                    lblCam1.setText("CAMERA DISCONNECTED");
                }
                btnLiveCamAll.setEnabled(true);
                btnLiveCam1.setEnabled(true);
                btnLiveCam2.setEnabled(true);
                btnLiveCam3.setEnabled(true);
                btnLiveCam4.setEnabled(true);
               
                btnHome.setEnabled(true);

            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnLiveCam4ActionPerformed

    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.

         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html

         */
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    break;

                }

            }

        } catch (ClassNotFoundException ex) {

            // java.util.logging.//Logger.getLogger(MainFrmIts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {

            // java.util.logging.//Logger.getLogger(MainFrmIts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {

            // java.util.logging.//Logger.getLogger(MainFrmIts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

            // java.util.logging.//Logger.getLogger(MainFrmIts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrmIts().setVisible(true);
            }
        });
    }
    //usb detection
    Timer timerusb;
    TimerTask timerTaskUsb;
    static boolean pendrive_detected = false;
    //sos timer
    Timer timerSos;
    TimerTask timerTaskSos;
    static int timersosinc = 0;
    ///stop request
    Timer timerStopReq;
    TimerTask timerTaskStopReq;
    static int timerstopreqinc = 0;
    //record
    Timer timerRecord;
    TimerTask timerTaskRecord;

    public static int port_inc_cnt = 0;

    public static int port_perm_inc_cnt = 0;
    private static boolean restart_module_on = false;

    private void nmcli_commands() {
        File f = new File("/etc/NetworkManager/system-connections");
        File[] str = f.listFiles();
        String[] split_str;
        int i;
        try {
            if (str != null) {
                for (i = 0; i < str.length; i++) {
                    split_str = str[i].getName().split("\\.", -1);
                    runCmdNmcli("sudo nmcli con delete " + split_str[0]);
                    str[i].delete();
                }
            }
            runCmdNmcli("sudo nmcli radio wifi on");
            runCmdNmcli("sudo nmcli radio wwan on");
            runCmdNmcli("sudo nmcli radio wwan on");
            runCmdNmcli("sudo rm -rf /etc/NetworkManager/system-connections/*.*");

            if (clsSharedVariables.getSharedNetwork() == true) {
                runCmdNmcli("sudo nmcli connection add type ethernet ifname eth0 con-name WIRED save yes autoconnect yes ipv4.method shared");
            } else {
                runCmdNmcli("sudo nmcli connection add type ethernet ifname eth0 con-name WIRED save yes autoconnect yes ip4 192.168.0.2/24 gw4 192.168.0.1");
            }

            if (clsDefines.NETWORKMANAGER_GPRS == true) {

                runCmdNmcli("sudo nmcli connection add type gsm apn " + clsSharedVariables.apn + " ifname cdc-wdm0 con-name GSM save yes autoconnect yes");
            }
            runCmdNmcli("sudo ifconfig wlan0 up");
            runCmdNmcli("sudo chmod -R 777 /etc/NetworkManager/system-connections");
        } catch (Exception ex) {

        }
    }

    private void init_functions() {
        String[] data_plcnt;
        String str_plcnt;
        String[] data_plcnt1;
        String str_plcnt1;
        clsReadDataFiles objData = new clsReadDataFiles();
        clsReadFiles objReadFiles = new clsReadFiles();
        try {

            File f = new File(obu_filepath.getPath() + "/ObuIts1.jar");

            f.delete();

            f = new File(obu_filepath.getPath() + "/ObuIts2.jar");

            f.delete();

        } catch (Exception ex) {

        }

        try {

            runCmd("sudo chmod 777 -R /usr/local/data");
            runCmd("sudo chmod 777 -R /sys/class/gpio");

            File file = new File("/media/" + clsDefines.obu_board_username + "/3339OBU/MobileNetSSD_deploy.caffemodel");
            File files = new File("/usr/local/data/MobileNetSSD_deploy.caffemodel");
            if (file.exists()) {
                if (!files.exists()) {
                    runCmd("sudo cp -rf /media/" + clsDefines.obu_board_username + "/3339OBU/MobileNetSSD_deploy.caffemodel /usr/local/data/");
                }
            }
            file = new File("/media/" + clsDefines.obu_board_username + "/3339OBU/MobileNetSSD_deploy.prototxt");
            files = new File("/usr/local/data/MobileNetSSD_deploy.prototxt");
            if (file.exists()) {
                if (!files.exists()) {
                    runCmd("sudo cp -rf /media/" + clsDefines.obu_board_username + "/3339OBU/MobileNetSSD_deploy.prototxt /usr/local/data/");
                }
            }

        } catch (Exception ex) {

        }

        objReadFiles.read_port_detection_data();
        objReadFiles.read_camera_cfg_file();
        objReadFiles.read_pid_codes();
        objReadFiles.read_special_msg_info();
        objReadFiles.read_storedData_Params_file();
        objReadFiles.read_storedData_Params_file2();
        objReadFiles.read_storedData_Params_file4();
        objReadFiles.init_schedule_files();

        objReadFiles.init_route_master_file();

        objReadFiles.init_locationbased_ads_master_file();

        objReadFiles.read_route_master_file();

        objReadFiles.read_phone_no_details();
        objReadFiles.read_reset_data();

        objReadFiles.read_reset_type_data();

        objReadFiles.read_password_change();

        objReadFiles.read_traffic_points();

        objReadFiles.read_pre_def_location_points();
        objReadFiles.read_driver_names();

        objReadFiles.read_conductor_names();

        objReadFiles.read_label_names_details();

        objReadFiles.read_wpa();

        PanDisplayBoardDiag.init_front_pid_codes();
        objReadFiles.read_disbrd_pid_file();

        objReadFiles.read_disbrd_dtc_file();

        panic_message_id[0] = 7;

        panic_message_id[1] = 6;

        panic_message_id[2] = 1;

        panic_message_id[3] = 2;

        panic_message_id[4] = 3;

        panic_message_id[5] = 4;

        panic_message_id[6] = 5;

        panic_message[0] = "FIRE";

        panic_message[1] = "BRAKEDOWN";

        panic_message[2] = "TERRORIST ATTACK";

        panic_message[3] = "BUS HIJACKED";

        panic_message[4] = "MET SEVERE ACCIDENT";

        panic_message[5] = "BUS CAUGHT FIRE";

        panic_message[6] = "DROWNING IN WATER";

        clsSharedVariables.no_panic_messages = 7;

        objReadFiles.read_panic_messages();
        try {
            data_plcnt = objData.read_apc_hour_stored_data();
            if (data_plcnt != null && data_plcnt.length > 1) {
                str_plcnt = data_plcnt[data_plcnt.length - 1]; // = startdate + "," + day + "," + people_in + "," + people_out;
                data_plcnt = str_plcnt.split(",");
                if (data_plcnt.length >= 4) {
                    clsSharedVariables.setApcHourDateTime(Long.parseLong(data_plcnt[0]));
                    clsSharedVariables.setDateHour(data_plcnt[1]);
                    clsSharedVariables.setApcHourHour(Byte.parseByte(data_plcnt[2]));
                    clsSharedVariables.setApcHourPeopleIn(Byte.parseByte(data_plcnt[3]));
                    clsSharedVariables.setApcHourPeopleOut(Byte.parseByte(data_plcnt[4]));
                }
            }

            data_plcnt = objData.read_apc_day_stored_data();
            if (data_plcnt != null && data_plcnt.length > 1) {
                str_plcnt = data_plcnt[data_plcnt.length - 1];
                data_plcnt = str_plcnt.split(",");
                if (data_plcnt.length >= 4) {
                    clsSharedVariables.setApcDayDateTime(Long.parseLong(data_plcnt[0]));
                    clsSharedVariables.setDateDay(data_plcnt[1]);
                    clsSharedVariables.setApcDayDay(Byte.parseByte(data_plcnt[2]));
                    clsSharedVariables.setApcDayPeopleIn(Byte.parseByte(data_plcnt[3]));
                    clsSharedVariables.setApcDayPeopleOut(Byte.parseByte(data_plcnt[4]));

                }
            }
            try {
                objData.read_apc_present_stored_data();
            } catch (Exception ex) {
            }
//            clsPeopleCntEventClear objWaterMarking = new clsPeopleCntEventClear();
         //   objWaterMarking.setWaterMarkingUpdateDahuaApc(clsSharedVariables.getApcPeopleIn(), clsSharedVariables.getApcPeopleOut());
         //   objWaterMarking = null;
        } catch (Exception ex) {
        }
        //apc second camera
        try {
            data_plcnt1 = objData.read_apc_hour_stored_data1();
            if (data_plcnt1 != null && data_plcnt1.length > 1) {
                str_plcnt1 = data_plcnt1[data_plcnt1.length - 1];
                data_plcnt1 = str_plcnt1.split(",");
                if (data_plcnt1.length >= 4) {
                    clsSharedVariables.setApcHourDateTime1(Long.parseLong(data_plcnt1[0]));
                    clsSharedVariables.setDateHour1(data_plcnt1[1]);
                    clsSharedVariables.setApcHourHour1(Byte.parseByte(data_plcnt1[2]));
                    clsSharedVariables.setApcHourPeopleIn1(Byte.parseByte(data_plcnt1[3]));
                    clsSharedVariables.setApcHourPeopleOut1(Byte.parseByte(data_plcnt1[4]));
                }
            }
            data_plcnt1 = objData.read_apc_day_stored_data1();
            if (data_plcnt1 != null && data_plcnt1.length > 1) {
                str_plcnt1 = data_plcnt1[data_plcnt1.length - 1];
                data_plcnt1 = str_plcnt1.split(",");
                if (data_plcnt1.length >= 4) {
                    clsSharedVariables.setApcDayDateTime1(Long.parseLong(data_plcnt1[0]));
                    clsSharedVariables.setDateDay1(data_plcnt1[1]);
                    clsSharedVariables.setApcDayDay1(Byte.parseByte(data_plcnt1[2]));
                    clsSharedVariables.setApcDayPeopleIn1(Byte.parseByte(data_plcnt1[3]));
                    clsSharedVariables.setApcDayPeopleOut1(Byte.parseByte(data_plcnt1[4]));
                }
            }
            try {

                objData.read_apc_present_stored_data1();
            } catch (Exception ex) {

            }
            try {

                objData.read_both_camera_stored_data();
            } catch (Exception ex) {

            }
            MainFrmIts.lblPeopleIn2.setText(String.valueOf(clsSharedVariables.getApcPeopleCountIn()));
            MainFrmIts.lblPeopleOut2.setText(String.valueOf(clsSharedVariables.getApcPeopleCountOut()));
         //   clsPeopleCntEventClear objWaterMarking = new clsPeopleCntEventClear();
            //objWaterMarking.setWaterMarkingUpdateDahuaApc1(clsSharedVariables.getApcPeopleIn1(), clsSharedVariables.getApcPeopleOut1());
           // objWaterMarking = null;
        } catch (Exception ex) {

        }

        objSchedule = new ClsSchedule();

        objSchedule.schedule_init();
        if (clsSharedVariables.getDigInp1DefaultClosedState() == true) {
            DigInp1CheckState = PinState.HIGH;
        } else {
            DigInp1CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp2DefaultClosedState() == true) {
            DigInp2CheckState = PinState.HIGH;
        } else {
            DigInp2CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp3DefaultClosedState() == true) {
            DigInp3CheckState = PinState.HIGH;
        } else {
            DigInp3CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp4DefaultClosedState() == true) {
            DigInp4CheckState = PinState.HIGH;
        } else {
            DigInp4CheckState = PinState.LOW;
        }
        objReadFiles = null;
        objData = null;
    }

    public static synchronized void write_gpio_pin_state(Pin pin_no, PinState state, GpioPinDigitalOutput pin) {

        try {

            final GpioController gpio = GpioFactory.getInstance();

            pin = gpio.provisionDigitalOutputPin(pin_no, "lcd_state", state);

            gpio.unprovisionPin(pin);

            pin = null;

        } catch (java.lang.UnsatisfiedLinkError | Exception ex1) {

        }
    }

    public static synchronized boolean read_gpio_pin_state(Pin pin_no, GpioPinDigitalOutput pin) {
        try {
            final GpioController gpio = GpioFactory.getInstance();
            clsReadFiles obj = new clsReadFiles();
            pin = gpio.provisionDigitalOutputPin(pin_no);
            PinState state = pin.getState();

            if (state.isHigh()) {
                // System.out.println("High");
                obj.write_data_imp_log("LCD ON");
                gpio.unprovisionPin(pin);
                pin = null;
                return true;
            } else {
                write_gpio_pin_state(LCD_RESET, LCD_RESET_ON_STATE, digOutLcdResetPin);

                obj.write_data_imp_log("LCD OFF");
                gpio.unprovisionPin(pin);
                pin = null;
                return false;
            }

        } catch (java.lang.UnsatisfiedLinkError | Exception ex1) {

        }
        return false;

    }

    //reading ignition status
    public void read_ignition_state() {
        try {
            final GpioController gpio = GpioFactory.getInstance();

            digInIgnitionGpio = gpio.provisionDigitalInputPin(IGNITION_STATUS,
                    "ignition",
                    PinPullResistance.OFF);
            //read pin state
            PinState ignitionState = digInIgnitionGpio.getState();
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                ignitionState = read_tinker_gpio_state(IGNITION_STATUS_TINKER_CPU);
                if (ignitionState == null) {
                    ignitionState = digInIgnitionGpio.getState();
                }
            }
            if (ignitionState == IGNITION_PIN_OFF_STATE) {
                startSleepModeTimer();
                clsSharedVariables.setIgnitionStatus(false);
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            imgIgnition.setVisible(false);
                        }
                    });
                } catch (Exception ex) {
                }
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.IGNITION_OFF_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.ignition_off();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_IGNITION_OFF);

                }
                clsHealthPacketStructure obj = new clsHealthPacketStructure();

                obj.set_ignition_status((byte) 0);

                obj = null;

            } else {

                clsSharedVariables.setIgnitionStatus(true);

                set_sleep_mode_status(false);

                try {

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            updateIgnitionIcon();
                        }
                    });

                } catch (Exception ex) {

                }
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.IGNITION_ON_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.ignition_on();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_IGNITION_ON);

                }

                clsHealthPacketStructure obj = new clsHealthPacketStructure();

                obj.set_ignition_status((byte) 1);

                obj = null;
            }

            digInIgnitionGpio.addListener(new GpioIgnitionListener());

        } catch (Exception ex) {

        }

    }

    //listen for pin change states
    public class GpioIgnitionListener implements GpioPinListenerDigital {
                                               
        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            PinState ignitionState;
            ignitionState = event.getState();
            if (ignitionState == IGNITION_PIN_OFF_STATE) {
                setLastIgnitionState(IGNITION_READ_OFF_STATE);
                startSleepModeTimer();
                clsSharedVariables.setIgnitionStatus(false);
                try {
                    SwingUtilities.invokeLater(() -> {
                        imgIgnition.setVisible(false);
                    });

                } catch (Exception ex) {

                }
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.IGNITION_OFF_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.ignition_off();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_IGNITION_OFF);

                }
                clsHealthPacketStructure obj = new clsHealthPacketStructure();
                obj.set_ignition_status((byte) 0);
                obj = null;
            } else {
                clsSharedVariables.setIgnitionStatus(true);
                setLastIgnitionState(IGNITION_READ_ON_STATE);
                set_sleep_mode_status(false);
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.IGNITION_ON_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.ignition_on();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_IGNITION_ON);
                }
                try {
                    SwingUtilities.invokeLater(() -> {
                        updateIgnitionIcon();
                    });
                } catch (Exception ex) {

                }
                clsHealthPacketStructure obj = new clsHealthPacketStructure();

                obj.set_ignition_status((byte) 1);

                obj = null;

                stop_sleep_modetimer();
            }
            ignitionState = null;
        }
    }

    public static void harddisk_unmount() {
        try {
            Process proc;
            proc = Runtime.getRuntime().exec("sudo umount -l " + HARDDISK_PATH);  //sudo reboot
            proc.waitFor();
            proc = null;
        } catch (Exception e) {
        }
    }

    public static double extra_files_size() {
        File extra_cam_path;
        double extra_filepath_size = 0.0;
        try {
            if (clsSharedVariables.getCam1Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam1");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB;
            }
            if (clsSharedVariables.getCam2Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam2");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            if (clsSharedVariables.getCam3Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam3");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;

            }
            if (clsSharedVariables.getCam4Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam4");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            if (clsSharedVariables.getCam5Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam5");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            if (clsSharedVariables.getCam6Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam6");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            if (clsSharedVariables.getCam7Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam7");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            if (clsSharedVariables.getCam8Enabled() == false) {
                extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Cam8");
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Snapshot");
            if (extra_cam_path.exists()) {
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Videos_can");
            if (extra_cam_path.exists()) {
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Videos_event");
            if (extra_cam_path.exists()) {
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Videos_motion");
            if (extra_cam_path.exists()) {
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
            extra_cam_path = new File(video_filepath.getAbsolutePath() + "/Videos_preevent");
            if (extra_cam_path.exists()) {
                extra_filepath_size = FileUtils.sizeOfDirectory(extra_cam_path) / ONE_GB + extra_filepath_size;
            }
        } catch (Exception ex) {

        }
        return extra_filepath_size;

    }

    public void power_off_conditions() {

        set_sleep_mode_status(true);

    }

    //reading mains status
    public void read_mains_state() {
        try {
            final GpioController gpio = GpioFactory.getInstance();
            digInMainsGpio = gpio.provisionDigitalInputPin(MAINS_STATUS,
                    "mains",
                    PinPullResistance.OFF);

            //read pin state
            PinState mainsState = digInMainsGpio.getState();
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                mainsState = read_tinker_gpio_state(MAINS_STATUS_TINKER_CPU);
                if (mainsState == null) {
                    mainsState = digInMainsGpio.getState();
                }
            }
            if (mainsState == MAINS_ON_STATE) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    if (obj16833Pkts == null) {
                        obj16833Pkts = new cls16833Protocols();
                    }
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.BATTERY_PWR_RECONNECT_PKT, 'L', false, " ");

                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.main_on();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_BAT_DIS_CONN);

                }

                setMainsStatus(true);

                clsHealthPacketStructure obj = new clsHealthPacketStructure();

                obj.set_mains_status((byte) 1);

                obj.set_battery_status((byte) 0);

                obj = null;

            } else {

                if (clsSharedVariables.getIpAddr1Enable()) {
                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.BATTERY_PWR_DISCONNECT_PKT, 'L', false, " ");
                    obj16833Pkts = null;
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.main_off();

                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_BAT_CONN);

                }

                setMainsStatus(false);
                clsHealthPacketStructure obj = new clsHealthPacketStructure();

                obj.set_mains_status((byte) 0);

                obj.set_battery_status((byte) 1);

                obj = null;
                clsSharedVariables.setHardDriveDetected(false);

                try {

                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            updateHarddiskIcon();
                        }
                    });

                } catch (Exception ex) {
                }
            }
            digInMainsGpio.addListener(new GpioMainsListener());
            mainsState = null;

        } catch (Exception ex) {

        }

    }

    //listen for pin change states
    public class GpioMainsListener implements GpioPinListenerDigital {

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

            PinState mainsState = event.getState();
            if (mainsState == MAINS_ON_STATE) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    if (obj16833Pkts == null) {
                        obj16833Pkts = new cls16833Protocols();
                    }
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.BATTERY_PWR_RECONNECT_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.main_on();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_BAT_DIS_CONN);
                }
                setLastMainsState(MAINS_READ_ON_STATE);

                clsHealthPacketStructure obj = new clsHealthPacketStructure();
                obj.set_mains_status((byte) 1);
                obj = null;
                setMainsStatus(true);
                clsReadFiles objReadFiles = new clsReadFiles();
                objReadFiles.harddisk_mount();
                objReadFiles = null;
            } else {

                setLastMainsState(MAINS_READ_OFF_STATE);
                if (clsSharedVariables.getIpAddr1Enable()) {
                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.BATTERY_PWR_DISCONNECT_PKT, 'L', false, " ");
                    obj16833Pkts = null;
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.main_off();
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_BAT_CONN);
                }
                setMainsStatus(false);
                clsHealthPacketStructure obj = new clsHealthPacketStructure();
                obj.set_mains_status((byte) 0);

                obj = null;
                clsSharedVariables.setHardDriveDetected(false);
                updateHarddiskIcon();
            }
            mainsState = null;

        }

    }
//reading mains status

    public void read_mic_state() {
        try {
            final GpioController gpio = GpioFactory.getInstance();

            digInMicStatusGpio = gpio.provisionDigitalInputPin(MIC_STATUS, // PIN NUMBER
                    "mic", // PIN FRIENDLY NAME (optional)
                    PinPullResistance.OFF); // PIN RESISTANCE (optional)

            //read pin state
            PinState micState = digInMicStatusGpio.getState();
            imgMic.setVisible(true);
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                micState = read_tinker_gpio_state(MIC_STATUS_TINKER_CPU);
                if (micState == null) {
                    micState = digInMicStatusGpio.getState();
                }
            }
            // use convenience wrapper method to interrogate the button state
            if (micState == MIC_STATUS_ON_STATE) {
                mic_on = true;
                // status = 1;
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.mic_on();
                }
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_on.png")));// new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_on.png")));

                            imgMic.repaint();
                        }

                    });

                } catch (Exception ex) {

                }
            } else {
                mic_on = false;
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.mic_off();

                }

                try {

                    SwingUtilities.invokeLater(() -> {
                        imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_off.png")));//new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_off.png")));

                        imgMic.repaint();
                    });

                } catch (Exception ex) {
                }
            }

            digInMicStatusGpio.addListener(new GpioMicListener());
            micState = null;
        } catch (Exception ex) {

        }

    }

    public synchronized static void fmradio_speaker_on() {
        write_gpio_pin_state(FM_RADIO_PIN_ONOFF, FM_ON_STATE, digOutFMPin);
        write_gpio_pin_state(AUDIO_SPEAKER_COMM_ONOFF, AUDIO_SIGNAL_SPEAKER_ON_STATE, digOutAudioSpeakerPin);
    }

    public synchronized static void fmradio_speaker_off() {
        write_gpio_pin_state(FM_RADIO_PIN_ONOFF, FM_OFF_STATE, digOutFMPin);
        if (get_speaker_enabled_status() == false && get_voice_call_enabled_status() == false && mic_on == false) {
            write_gpio_pin_state(AUDIO_SPEAKER_COMM_ONOFF, AUDIO_SIGNAL_SPEAKER_OFF_STATE, digOutAudioSpeakerPin);
        }
    }

    public synchronized static void audio_speaker_on() {
        set_speaker_enabled_status(true);
        write_gpio_pin_state(FM_RADIO_PIN_ONOFF, FM_OFF_STATE, digOutFMPin);
        write_gpio_pin_state(AUDIO_SPEAKER_COMM_ONOFF, AUDIO_SIGNAL_SPEAKER_ON_STATE, digOutAudioSpeakerPin);
        if (clsSharedVariables.getFmRadioStatus() == true && (clsSharedVariables.getFmRadioEnable() == true)) {
         //   objFmRadio.main("off");
        }
    }

    public synchronized static void audio_speaker_off() {
        set_speaker_enabled_status(false);
        if (clsSharedVariables.getFmRadioStatus() == true && (clsSharedVariables.getFmRadioEnable() == true)) {
            fmradio_speaker_on();
          //  objFmRadio.main("on");
        } else {
            fmradio_speaker_off();

        }
    }

    //listen for pin change states
    public class GpioMicListener implements GpioPinListenerDigital {

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            PinState micState = event.getState();

            if (micState == MIC_STATUS_ON_STATE) {
                mic_on = true;
                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.mic_on();
                }
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_on.png")));//new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_on.png")));
                            imgMic.repaint();
                            if (get_voice_call_enabled_status() == false && clsSharedVariables.getFmRadioStatus() == true && (clsSharedVariables.getFmRadioEnable() == true)) {
                                fmradio_speaker_off();
                              //  objFmRadio.main("off");
                            }
                            if (get_speaker_enabled_status() == false && get_voice_call_enabled_status() == false) {
                                write_gpio_pin_state(AUDIO_SPEAKER_COMM_ONOFF, AUDIO_SIGNAL_SPEAKER_ON_STATE, digOutAudioSpeakerPin);
                            }
                        }

                    });

                } catch (Exception ex) {

                }

            } else if (micState == MIC_STATUS_OFF_STATE) {
                mic_on = false;
                try {

                    if (get_speaker_enabled_status() == false && get_voice_call_enabled_status() == false) {

                        write_gpio_pin_state(AUDIO_SPEAKER_COMM_ONOFF, AUDIO_SIGNAL_SPEAKER_OFF_STATE, digOutAudioSpeakerPin);

//                        if (clsSharedVariables.getFmRadioStatus() == true && (clsSharedVariables.getFmRadioEnable() == true)) {
//                            fmradio_speaker_on();
//                            objFmRadio.main("on");
//                        } else {
//                            fmradio_speaker_off();
//                        }
                    }

                } catch (Exception ex) {

                }

                if (clsSharedVariables.getIpAddr4Enable()) {
                    objDriving.mic_off();
                }

                try {

                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {

                            imgMic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_off.png")));//new javax.swing.ImageIcon(getClass().getResource("/Images/microphone_off.png")));

                            imgMic.repaint();
                        }

                    });

                } catch (Exception ex) {

                }

            }
            micState = null;

        }

    }

    public void startTimerUsb() {
        timerusb = new Timer();
        initializeTimerTaskUsb();
        timerusb.scheduleAtFixedRate(timerTaskUsb, 0, 10000);
    }

    public void initializeTimerTaskUsb() {
        timerTaskUsb = new TimerTask() {
            @Override
            public void run() {
                read_input_usb_devices();
            }

        };
    }

    public void startTimerSos() {
        //set a new Timer
        clsSharedVariables.setEmergencyAlertCame(true);
        clsSharedVariables.setEmergencyAlertStatus('1');

        timerSos = new Timer();
        //initialize the TimerTask's job
        initializeTimerTaskSos();
        //////////System.out.println("emergency packet0");
        timerSos.schedule(timerTaskSos, 0, 100); //
    }

    public void initializeTimerTaskSos() {
        timerTaskSos = new TimerTask() {
            @Override
            public void run() {
                if (timersosinc % 2 == 0) {
                    imgEmergency.setForeground(Color.RED);
                } else {
                    imgEmergency.setForeground(Color.WHITE);
                }
                if (timersosinc++ > clsSharedVariables.getEmergencyStateTimeDuration() * 10) {
                    timersosinc = 0;
                    if (get_phonecall_type() != OUT_CALL) {
                        driver_alarm_off();
                    }
                    setDigSosStarted(false);
                    imgEmergency.setText(" ");
                    //construct packet emergency
                    if (clsSharedVariables.getEmergencyAlertCame() == false) {
                        stop_timer_sos();
                    }
                    cls16833Protocols obj16833 = new cls16833Protocols();
                    if (clsSharedVariables.getIpAddr2Enable()) {
                        obj16833.emergency_alert_message_pkt(EMERGENCY_MESSAGE_TYPE, "NM");
                    }
                    //  //////////System.out.println("emergency packet sent");
                    obj16833 = null;
                    //if you need emegency packet only once uncomment this
                    // stop_timer_sos();
                    //if you need continously packet
                    if (clsSharedVariables.getEmergencyAlertCame() == false) {
                        stop_timer_sos();
                    }
                }
            }
        };
    }

    private void stop_timer_sos() {
        try {
            clsSharedVariables.setSosStarted(false);
            try {
                SwingUtilities.invokeLater(() -> {
                    imgSos.setVisible(false);

                    clsSharedVariables.setEmergencyAlertCame(false);
                    clsSharedVariables.setEmergencyAlertStatus('0');

                    try {

                        if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_SOS) {
                            imgSos.setVisible(false);
                        }
                        if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
                            imgDigI2.setVisible(false);
                        }
                        if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_SOS) {
                            imgDigI3.setVisible(false);
                        }
                        if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_SOS) {
                            imgDigI4.setVisible(false);
                        }

                    } catch (Exception ex) {

                    }
                });
            } catch (Exception ex) {
            }
            if (timerSos != null) {
                timerSos.cancel();
                timerSos = null;
            }
            if (timerTaskSos != null) {
                timerTaskSos.cancel();
                timerTaskSos = null;
            }
        } catch (Exception ex) {

        }
    }


   

    public void startTimerStopReq() {
        timerStopReq = new Timer();
        initializeTimerTaskStopReq();
        timerStopReq.schedule(timerTaskStopReq, 0, 100); //
    }

    public void initializeTimerTaskStopReq() {
        timerTaskStopReq = new TimerTask() {
            @Override
            public void run() {
                if (timerstopreqinc % 2 == 0) {
                    imgStopReq.setForeground(Color.RED);
                } else {
                    imgStopReq.setForeground(Color.WHITE);
                }
                if (timerstopreqinc++ > 5 * 10) {
                    timerstopreqinc = 0;
                    if (get_phonecall_type() != OUT_CALL) {
                        driver_alarm_off();
                    }
                    setDigStopReqStarted(false);
                    imgStopReq.setText(" ");
                    stop_timer_stop_req();

                }
            }
        };
    }

    private void stop_timer_stop_req() {
        try {
            setDigStopReqStarted(false);
            try {
                SwingUtilities.invokeLater(() -> {
                    imgSos.setVisible(false);
                    try {
                        if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                            imgSos.setVisible(false);
                        }
                        if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                            imgDigI2.setVisible(false);
                        }
                        if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                            imgDigI3.setVisible(false);
                        }
                        if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                            imgDigI4.setVisible(false);
                        }
                    } catch (Exception ex) {

                    }
                });
            } catch (Exception ex) {
            }
            if (timerStopReq != null) {
                timerStopReq.cancel();
                timerStopReq = null;
            }
            if (timerTaskStopReq != null) {
                timerTaskStopReq.cancel();
                timerTaskStopReq = null;
            }
        } catch (Exception ex) {

        }
    }

    private void dig_input_external_sos(byte dig_input_no) {
        setDigSosStarted(true);
        imgEmergency.setText("EMERGENCY");
        if (clsSharedVariables.getSosStarted() == false) {

            clsSharedVariables.setSosStarted(true);

            //PACKET FORMAT IS DIFERENT FOR SOS
            if (clsSharedVariables.getIpAddr4Enable()) {
                objDriving.panic_messages(PANIC_EMERGENCY);
            }
            if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_EMERGENCY_ALERT);

            }

            if (getDisBrdName() == SUMITH_DISBRD) {
                fill_internal_details("EMERGENCY STOP", clsDefines.INTDB_DATA_NOT_SAVE);
            }

            try {
                SwingUtilities.invokeLater(() -> {
                    switch (dig_input_no) {
                        case 1:
                            imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
                            imgSos.setVisible(true);
                            imgSos.repaint();

                            break;
                        case 2:
                            imgDigI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
                            imgDigI2.setVisible(true);
                            imgDigI2.repaint();
                            break;
                        case 3:
                            imgDigI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
                            imgDigI3.setVisible(true);
                            imgDigI3.repaint();
                            break;
                        case 4:
                            imgDigI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/external_sos.png")));
                            imgDigI4.setVisible(true);
                            imgDigI4.repaint();
                            break;
                        default:
                            break;
                    }
                });

            } catch (Exception ex) {

            }
            driver_alarm_on_sos();
            startTimerSos();

        }

    }

    public void startTimerRecord() {
        //set a new Timer
        timerRecord = new Timer();
        //initialize the TimerTask's job
        initializeTimerTaskRecord();
        timerRecord.schedule(timerTaskRecord, clsSharedVariables.getEventPostRecordTime() * 60 * 1000, clsSharedVariables.getEventPostRecordTime() * 60 * 1000); //

    }

    public void initializeTimerTaskRecord() {
        timerTaskRecord = new TimerTask() {
            @Override
            public void run() {
                clsEventRecord obj = new clsEventRecord();

                obj.start();

                try {
                    if (timerRecord != null) {
                        timerRecord.cancel();
                        timerRecord = null;
                    }
                    if (timerTaskRecord != null) {
                        timerTaskRecord.cancel();
                        timerTaskRecord = null;
                    }
                } catch (Exception ex) {

                }
            }
        };
    }

    private void dig_input_video_recording(byte dig_input_no) {
        setVideoRecordStarted(true);
        if (clsSharedVariables.getEventRecordEnabled() && clsSharedVariables.getEventBasedRecordingStart() == false) {
            clsSharedVariables.setEventBasedRecordingStart(true);
            switch (dig_input_no) {
                case 1:
                    imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
                    imgSos.setVisible(true);
                    break;
                case 2:
                    imgDigI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
                    imgDigI2.setVisible(true);
                    break;
                case 3:
                    imgDigI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
                    imgDigI3.setVisible(true);
                    break;
                case 4:
                    imgDigI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/videorec_dig.png")));
                    imgDigI4.setVisible(true);
                    break;
                default:
                    break;
            }
            startTimerRecord();
        }
    }

    private void dig_input_right_arrow() {
        setRightArrowStarted(true);
        String message_file = "RIGHT";
        // Add rear data to queue if rear display is enabled
        if (getRearEnable()) {
            File file = new File(route_filepath, message_file + ".rdb");
            File f = new File(main_route_filepath, message_file + ".rdb");
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addFSRData(message_file + ".rdb" + "," + 0);
            } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                objdisQue.addFSRData(message_file + ".rdb" + "," + 0);
            }
        }

    }

    private void dig_input_left_arrow() {
        setLeftArrowStarted(true);
        String message_file = "LEFT";
        // Add rear data to queue if rear display is enabled
        if (getRearEnable()) {
            File file = new File(route_filepath, message_file + ".rdb");
            File f = new File(main_route_filepath, message_file + ".rdb");
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addFSRData(message_file + ".rdb" + "," + 0);
            } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                objdisQue.addFSRData(message_file + ".rdb" + "," + 0);
            }
        }
    }

    private void dig_input_stop_request(byte dig_input_no) {
        try {
            setDigStopReqStarted(true);
            imgStopReq.setText("STOP REQUEST");
            switch (dig_input_no) {
                case 1:
                    imgSos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_request.png ")));
                    imgSos.setVisible(true);
                    break;
                case 2:
                    imgDigI2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_request.png")));
                    imgDigI2.setVisible(true);
                    break;
                case 3:
                    imgDigI3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_request.png")));
                    imgDigI3.setVisible(true);
                    break;
                case 4:
                    imgDigI4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_request.png")));
                    imgDigI4.setVisible(true);
                    break;
                default:
                    break;
            }
            if (getDisBrdName() == SUMITH_DISBRD) {
                fill_internal_details("PLEASE STOP THE BUS", clsDefines.INTDB_DATA_NOT_SAVE);
            }
            driver_alarm_on_stop_request();
            startTimerStopReq();

        } catch (Exception ex) {

        }
    }

    private void driver_alarm_on_sos() {
        try {
            if (clsSharedVariables.getPmiEnable()) {
                clsSharedVariables.objAudQue.addData("/emergency.wav");

            } else {
                try {
                    if (get_phonecall_type() != OUT_CALL) {
                        final String data = "Emergency";
                        SwingWorker sw1 = new SwingWorker() {
                            @Override
                            protected String doInBackground() throws Exception {
                                try {

                                    write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);

                                    String str = "AT+QTTS=2,\"" + data + "\" \r\n";
                                    atSerialWrite(str);
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) {
                                } catch (Exception ex) {
                                }
                                return null;
                            }

                            @Override
                            protected void process(List chunks) {
                            }
                        };
                        sw1.execute();
                    }
                } catch (Exception ex) {
                }
            }
        } catch (Exception e) {
        }
    }

    private void driver_alarm_on_stop_request() {
        try {
            if (clsSharedVariables.getPmiEnable()) {
                clsSharedVariables.objAudQue.addData("/stoprequest.wav");
            } else {
                try {
                    if (get_phonecall_type() != OUT_CALL) {
                        final String data = "PLEASE STOP THE BUS";
                        SwingWorker sw1 = new SwingWorker() {
                            @Override
                            protected String doInBackground() throws Exception {
                                try {
                                    // TODO add your handling code here:
                                    write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);
                                    String str = "AT+QTTS=2,\"" + data + "\" \r\n";
                                    atSerialWrite(str);
                                    Thread.sleep(3000);
                                } catch (InterruptedException ex) {
                                } catch (Exception ex) {
                                }
                                return null;
                            }

                            @Override
                            protected void process(List chunks) {
                            }
                        };

                        sw1.execute();

                    }
                } catch (Exception ex) {

                }

            }

        } catch (Exception e) {
        }
    }

    private void driver_alarm_off() {
        try {
            write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
        } catch (Exception e) {

        }
    }

    //reading sos status
    public void read_sos_state() {

        try {
            final GpioController gpio = GpioFactory.getInstance();
            digInSosGpio = gpio.provisionDigitalInputPin(SOS_INPUT,
                    "sos",
                    PinPullResistance.OFF);
            digInSosGpio.addListener(new GpioSosListener());
        } catch (Exception ex) {
        }
    }

    public void read_diginput2_state() {
        try {

            final GpioController gpio = GpioFactory.getInstance();

            digInput2Gpio = gpio.provisionDigitalInputPin(DIG2_INPUT,
                    "dig2",
                    PinPullResistance.OFF);
            digInput2Gpio.addListener(new GpioDigInput2Listener());
        } catch (Exception ex) {

        }

    }

    public void read_diginput3_state() {

        try {

            final GpioController gpio = GpioFactory.getInstance();

            digInput3Gpio = gpio.provisionDigitalInputPin(DIG3_INPUT,
                    "dig3",
                    PinPullResistance.OFF);
            digInput3Gpio.addListener(new GpioDigInput3Listener());
        } catch (Exception ex) {
        }

    }

    public void read_diginput4_state() {

        try {

            final GpioController gpio = GpioFactory.getInstance();

            digInput4Gpio = gpio.provisionDigitalInputPin(DIG4_INPUT,
                    "dig4",
                    PinPullResistance.OFF);
            digInput4Gpio.addListener(new GpioDigInput4Listener());
        } catch (Exception ex) {

        }

    }

    private PinState read_tinker_gpio_state(int pin_no) {
        String line;
        BufferedReader br = null;
        byte val;

        String path = "/sys/class/gpio/gpio" + pin_no + "/value";
        File file = new File(path);
        if (file.exists()) {
            try {
                file.setReadable(true, false);
                br = new BufferedReader(new FileReader(file));

                line = br.readLine();
                val = Byte.parseByte(line.trim());
                if (val == 0) {
                    return PinState.LOW;
                } else {
                    return PinState.HIGH;
                }

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            } catch (Exception e) {

            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }

                line = null;
                br = null;

                file = null;
            }
        }
        return null;
    }

    private void read_tamper_state() {

        try {

            final GpioController gpio = GpioFactory.getInstance();

            tamperGpio = gpio.provisionDigitalInputPin(TAMPER_DIG_INPUT, // PIN NUMBER

                    "tamper", // PIN FRIENDLY NAME (optional)

                    PinPullResistance.OFF); // PIN RESISTANCE (optional)

            //read pin state
            PinState inState = tamperGpio.getState();
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                inState = read_tinker_gpio_state(TAMPER_DIG_INPUT_TINKER_CPU);
                if (inState == null) {
                    inState = tamperGpio.getState();
                }
            }
            // use convenience wrapper method to interrogate the button state
            if (inState == LOW_STATE) {  //TAMPER CLOSED
                tamper_on = false;
                clsSharedVariables.setTamperAlertStatus(clsDefines.TAMPER_CLOSED);
                updateTamperIcon();
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.TAMPER_ALERT_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TAMPER_OFF);

                }

            } else {
                tamper_on = true;
                clsSharedVariables.setTamperAlertStatus(clsDefines.TAMPER_OPEN);
                updateTamperIcon();
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.TAMPER_ALERT_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TAMPER_ON);

                }
            }

            tamperGpio.addListener(new tamperListener());
            inState = null;

        } catch (Exception ex) {

        }
    }

    public class tamperListener implements GpioPinListenerDigital {

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

            // display pin state on console
            PinState state = event.getState();
            if (state == LOW_STATE) {  //TAMPER CLOSED=false;
                tamper_on = false;
                clsSharedVariables.setTamperAlertStatus(clsDefines.TAMPER_CLOSED);
                updateTamperIcon();

                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.TAMPER_ALERT_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TAMPER_OFF);

                }
            } else {
                tamper_on = true;
                clsSharedVariables.setTamperAlertStatus(clsDefines.TAMPER_OPEN);
                updateTamperIcon();
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.TAMPER_ALERT_PKT, 'L', false, " ");
                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TAMPER_ON);
                }
            }
            state = null;
        }

    }

    private boolean send_sms_emergency_data() {
        String sms_data;
        try {
            if ((clsSharedVariables.getSimRegistered() == clsDefines.SIM_REGISTERED) && clsSharedVariables.sms_ph_no.length() > 9) {

                setSendSmsGreaterSymState(false);
                setSmsResponseCame((byte) 0);
                cls16833Protocols obj16833 = new cls16833Protocols();
                sms_data = obj16833.emergency_alert_sms_message_pkt(EMERGENCY_MESSAGE_TYPE, "NM");
                obj16833 = null;
                clsSharedVariables.setSmsData(sms_data);
                String dat = "AT+CMGS=\"" + clsSharedVariables.getSmsPhoneNo() + "\"\r\n";
                atSerialWrite(dat);
                clsSharedVariables.setSendSmsState(true);
                dat = null;
                return true;

            }
        } catch (Exception e) {

        }
        return false;
    }

    private void read_input_usb_devices() {
        File file = new File(clsDefines.usb_detection_filepath.getAbsolutePath() + "/by-label/");
        File[] files_list;
        boolean found = false;
        try {
            if (file.exists()) {
                files_list = file.listFiles();
                if (files_list != null) {
                    for (int i = 0; i < files_list.length; i++) {
                        try {
                            if (!files_list[i].toString().contains("3339OBU") && !files_list[i].toString().contains("Recorder")
                                    && !files_list[i].toString().contains("boot") && !files_list[i].toString().contains("rootfs")) {
                                found = true;

                            }
                        } catch (Exception ex) {

                        }
                    }
                    try {

                        if (found == true) {
                            if (pendrive_detected == false) {
                                //show__pendrive_message_dialogbox("Pendrive Detected");
                                pendrive_detected = true;
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        lblUsb.setVisible(true);
                                    }
                                });
                            }
                        } else {
                            pendrive_detected = false;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    lblUsb.setVisible(false);
                                }
                            });

                        }
                    } catch (Exception ex) {

                    }

                }
            }
        } catch (Exception ex) {

        }
    }

    public class GpioSosListener implements GpioPinListenerDigital {

        private long lastEventTime = 0;

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

            long currentTime = System.currentTimeMillis();

            if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_SOS) {
                if (getDigSosStarted() == true) {
                    return;
                }
            } /*else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_STOPREQUEST) { //stop reueset
                if (getDigStopReqStarted() == true) {
                    return;
                }
            }*/ else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) { //stop reueset
                if (getVideoRecordStarted() == true) {
                    return;
                }
            }/* else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {

            }else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (getLeftArrowStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (getRightArrowStarted() == true) {
                    return;
                }
            }else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_DOORCAM) {

            } */ else {
                return;
            }
            PinState state = event.getState();
            clsSharedVariables.setDigInp1Value(state.toString());
            if (state == DigInp1CheckState) {
                clsSharedVariables.setDigInput1Status((byte) 1);
                clsSharedVariables.setEmergencyAlertStatus('1');
                obj16833Pkts.trackingMessagePkt(clsPacketTypes.EMERGENCY_ALERT_ON_PKT, 'L', false, " ");
            } else {
                clsSharedVariables.setDigInput1Status((byte) 0);

            }
            if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_SOS) {
                if (state == DigInp1CheckState) {
                    send_sms_emergency_data();
                    dig_input_external_sos((byte) 1);
                    if (clsSharedVariables.getSnapDig1State() == false) {
                        clsSharedVariables.setSnapDig1State(true);
                        clsSnapshotDigInputs objDig1 = new clsSnapshotDigInputs((byte) 1);
                        objDig1.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                if (state == DigInp1CheckState) {
                    dig_input_video_recording((byte) 1);
                    if (clsSharedVariables.getSnapDig1State() == false) {
                        clsSharedVariables.setSnapDig1State(true);
                        clsSnapshotDigInputs objDig1 = new clsSnapshotDigInputs((byte) 1);
                        objDig1.start();
                    }
                }
            } /*else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                if (state == DigInp1CheckState) {
                    dig_input_stop_request((byte) 1);
                }
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                if (state == DigInp1CheckState) {
                    
            ();
                } else {
                    dig_input_reverse_gear_stop();
                }
            }else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp1CheckState) {
                        dig_input_left_arrow();
                        setLeftArrowStarted(false);
                    } else {
                        setLeftArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            } else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp1CheckState) {
                        dig_input_right_arrow();
                        setRightArrowStarted(false);
                    } else {
                        setRightArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            }  else if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_DOORCAM) {
                if (state == DigInp1CheckState) {
                    dig_input_door_cam_start();
                } else {
                    dig_input_door_cam_stop();
                }
            }*/ else {
                return;
            }
            state = null;

        }

    }

//listen for pin change states
    public class GpioDigInput2Listener implements GpioPinListenerDigital {

        private long lastEventTime = 0;

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            long currentTime = System.currentTimeMillis();
            if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {

            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
                if (getDigSosStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_STOPREQUEST) { //stop reueset
                if (getDigStopReqStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) { //stop reueset
                if (getVideoRecordStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (getLeftArrowStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (getRightArrowStarted() == true) {
                    return;
                }
            } else {
                return;
            }

            PinState state = event.getState();
            clsSharedVariables.setDigInp2Value(state.toString());
            if (state == DigInp2CheckState) {
                clsSharedVariables.setDigInput2Status((byte) 1);
            } else {
                clsSharedVariables.setDigInput2Status((byte) 0);
            }
            if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                if (state == DigInp2CheckState) {
                    dig_input_reverse_gear_start();
                } else {
                    dig_input_reverse_gear_stop();
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
                if (state == DigInp2CheckState) {

                    dig_input_external_sos((byte) 2);
                    if (clsSharedVariables.getSnapDig2State() == false) {
                        clsSharedVariables.setSnapDig2State(true);
                        clsSnapshotDigInputs objDig2 = new clsSnapshotDigInputs((byte) 2);
                        objDig2.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                if (state == DigInp2CheckState) {

                    dig_input_video_recording((byte) 2);
                    if (clsSharedVariables.getSnapDig2State() == false) {
                        clsSharedVariables.setSnapDig2State(true);
                        clsSnapshotDigInputs objDig2 = new clsSnapshotDigInputs((byte) 2);
                        objDig2.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                if (state == DigInp2CheckState) {

                    dig_input_stop_request((byte) 2);
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp2CheckState) {
                        dig_input_left_arrow();
                        setLeftArrowStarted(false);
                    } else {
                        setLeftArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp2CheckState) {
                        dig_input_right_arrow();
                        setRightArrowStarted(false);
                    } else {
                        setRightArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            }

            state = null;
        }
    }

    public class GpioDigInput3Listener implements GpioPinListenerDigital {

        private long lastEventTime = 0;

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

            long currentTime = System.currentTimeMillis();
            /* if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_SOS) {
                if (getDigSosStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                if (getDigStopReqStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                if (getVideoRecordStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {

            } else*/ if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (getLeftArrowStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (getRightArrowStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_DOORCAM) {

            } else {
                return;
            }
            PinState state = event.getState();
            clsSharedVariables.setDigInp3Value(state.toString());
            if (state == DigInp3CheckState) {
                clsSharedVariables.setDigInput3Status((byte) 1);
            } else {
                clsSharedVariables.setDigInput3Status((byte) 0);
            }

            /* if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_SOS) {
                if (state == DigInp3CheckState) {

                    dig_input_external_sos((byte) 3);
                    if (clsSharedVariables.getSnapDig3State() == false) {
                        clsSharedVariables.setSnapDig3State(true);
                        clsSnapshotDigInputs objDig3 = new clsSnapshotDigInputs((byte) 3);
                        objDig3.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                if (state == DigInp3CheckState) {

                    dig_input_video_recording((byte) 3);
                    if (clsSharedVariables.getSnapDig3State() == false) {
                        clsSharedVariables.setSnapDig3State(true);
                        clsSnapshotDigInputs objDig3 = new clsSnapshotDigInputs((byte) 3);
                        //  clsUploadVideoDigInputsServer objvideoDig = new clsUploadVideoDigInputsServer(Calendar.getInstance().getTimeInMillis());
                        objDig3.start();
                        //  objvideoDig.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                if (state == DigInp3CheckState) {
                    dig_input_stop_request((byte) 3);
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                if (state == DigInp3CheckState) {
                    dig_input_reverse_gear_start();
                } else {
                    dig_input_reverse_gear_stop();
                }
            } else*/ if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp3CheckState) {
                        dig_input_left_arrow();
                        setLeftArrowStarted(false);
                    } else {
                        setLeftArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp3CheckState) {
                        dig_input_right_arrow();
                        setRightArrowStarted(false);
                    } else {
                        setRightArrowStarted(false);
                        if (getRearEnable()) {

                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_DOORCAM) {
                if (state == DigInp3CheckState) {
                    dig_input_door_cam_start();
                } else {
                    dig_input_door_cam_stop();
                }
            }
            state = null;

        }

    }

    public class GpioDigInput4Listener implements GpioPinListenerDigital {

        private long lastEventTime = 0;

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

            long currentTime = System.currentTimeMillis();
            /* if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_SOS) {
                if (getDigSosStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                if (getDigStopReqStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                if (getVideoRecordStarted() == true) {
                    return;
                }
            } else*/ if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {

            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_LEFTARROW) { //stop reueset
                if (getLeftArrowStarted() == true) {
                    return;
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_RIGHTARROW) { //stop reueset
                if (getRightArrowStarted() == true) {
                    return;
                }
            } /* else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_DOORCAM) {

            }*/ else {
                return;
            }
            PinState state = event.getState();
            clsSharedVariables.setDigInp4Value(state.toString());
            if (state == DigInp4CheckState) {
                clsSharedVariables.setDigInput4Status((byte) 1);
            } else {
                clsSharedVariables.setDigInput4Status((byte) 0);
            }
            /*  if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_SOS) {
                if (state == DigInp4CheckState) {
                    dig_input_external_sos((byte) 4);
                    if (clsSharedVariables.getSnapDig4State() == false) {
                        clsSharedVariables.setSnapDig4State(true);
                        clsSnapshotDigInputs objDig4 = new clsSnapshotDigInputs((byte) 4);
                        objDig4.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                if (state == DigInp4CheckState) {
                    dig_input_video_recording((byte) 4);

                    if (clsSharedVariables.getSnapDig4State() == false) {
                        clsSharedVariables.setSnapDig4State(true);
                        clsSnapshotDigInputs objDig4 = new clsSnapshotDigInputs((byte) 4);
                        objDig4.start();
                    }
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_STOPREQUEST) {
                if (state == DigInp4CheckState) {
                    dig_input_stop_request((byte) 4);
                }
            } else */
            if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_REVERSE_GEAR) {
                if (state == DigInp4CheckState) {
                    dig_input_reverse_gear_start();
                } else {
                    dig_input_reverse_gear_stop();
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_LEFTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp4CheckState) {
                        dig_input_left_arrow();
                        setLeftArrowStarted(false);
                    } else {
                        setLeftArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_RIGHTARROW) {
                if (currentTime - lastEventTime > TIME_LIMIT) {
                    lastEventTime = currentTime;
                    if (state == DigInp4CheckState) {
                        dig_input_right_arrow();
                        setRightArrowStarted(false);
                    } else {
                        setRightArrowStarted(false);
                        if (getRearEnable()) {
                            objdisQue.addFSRData(objRouteMasFiles[getCurTripNo()].rear_file_name + "," + 0); // Add rear data to queue
                        }
                    }
                }
            }
            /* else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_DOORCAM) {
                if (state == DigInp4CheckState) {
                    dig_input_door_cam_start();
                } else {
                    dig_input_door_cam_stop();
                }
            }*/
            state = null;

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

    private void dig_input_reverse_gear_start() {
        if (getReverseCameraOn() == false) {
            setReverseCameraOn(true);
            try {
                setVideoLiveState((byte) 0);
              //  reverseActive = true;   // 🔒 BLOCK ALL LIVE COMPLETELY
                captureCurrentVideoState();
                stop_video();
                setVideoStarted(true);
                setVideoLiveState((byte) 2);
                objCamView2 = new clsCamera2View(false);
                objCamView2.setPriority(8);
                objCamView2.start();
            } catch (Exception ex) {

            }
        }
    }

    private void dig_input_door_cam_start() {
        if (getDoorCameraOn() == false) {
            setDoorCameraOn(true);
            try {
                setVideoLiveState((byte) 0);
                if (getVideoEnableStarted() == true) {
                    stop_video();
                }
                setVideoEnableStarted(true);
                setVideoStarted(true);
                setVideoLiveState((byte) 3);
                objCamView3 = new clsCamera3View(false);
                objCamView3.setPriority(8);
                objCamView3.start();
            } catch (Exception ex) {
            }
        }
    }

    private void dig_input_reverse_gear_stop() {
        try {
            setReverseCameraOn(false);
            reverseActive = false;   // 🔒 BLOCK ALL LIVE COMPLETELY
            setVideoLiveState((byte) 0);
            setPreVideoLiveState((byte) 0);
            setVideoStarted(false);
            objCamView2.interrupt();
            objCamView2 = null;
            if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD && xdotool == true) {
                killGstreamer();
            } else if (clsDefines.omxplayer_found == true) {
                killomxplayer();
            } else {
                killffplay();
            }
            if (!isHomeScreen()) {
                start_video();
            }
            savedVideoState = 0;
        } catch (Exception ex) {
        }
    }

    private void dig_input_door_cam_stop() {
        try {
            setDoorCameraOn(false);
            setVideoLiveState((byte) 0);
            setPreVideoLiveState((byte) 0);
            setVideoStarted(false);
            setVideoEnableStarted(false);
            objCamView3.interrupt();
            objCamView3 = null;
            if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD && xdotool == true) {
                killGstreamer();
            } else if (clsDefines.omxplayer_found == true) {
                killomxplayer();
            } else {
                killffplay();
            }
        } catch (Exception ex) {
            System.out.println("exceptionnnnnn " + ex);
        }
    }

    //reading gsm status
    public boolean read_gsm_status_state_on_poweron() {
        boolean state = false;
        try {

            clsSharedVariables.setGsmModuleOnPowerOn(false);
            final GpioController gpio = GpioFactory.getInstance();

            digInGsmStatusGpio = gpio.provisionDigitalInputPin(GSM_STATUS_PIN,
                    "gsm_status",
                    PinPullResistance.OFF);

            //read pin state
            PinState gsmState = digInGsmStatusGpio.getState();
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                gsmState = read_tinker_gpio_state(GSM_STATUS_PIN_TINKER_CPU);
                if (gsmState == null) {
                    gsmState = digInGsmStatusGpio.getState();
                }
            }
            if (gsmState == GSM_STATUS_ON_STATE) {
                set_gsm_connected_status(true);
                setGsmModuleOn(true);
                clsSharedVariables.setGsmModuleOnPowerOn(true);

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("GSM STATUS  ON");

                objReadFiles = null;
                state = true;

            } else {

                set_gsm_connected_status(false);
                setGsmModuleOn(false);

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("GSM STATUS  OFF");

                objReadFiles = null;

                state = false;
            }
            gpio.unprovisionPin(digInGsmStatusGpio);
            gsmState = null;

        } catch (Exception ex) {
        }
        return state;

    }

    public void read_gsm_status_state() {

        try {

            final GpioController gpio = GpioFactory.getInstance();

            digInGsmStatusGpio = gpio.provisionDigitalInputPin(GSM_STATUS_PIN, // PIN NUMBER

                    "gsm_status", // PIN FRIENDLY NAME (optional)

                    PinPullResistance.OFF); // PIN RESISTANCE (optional)

            PinState gsmState = digInGsmStatusGpio.getState();

            if (gsmState == GSM_STATUS_ON_STATE) {

                set_gsm_connected_status(true);

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("GSM STATUS  ON");
                objReadFiles = null;

            } else {

                set_gsm_connected_status(false);

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("GSM STATUS  OFF");
                objReadFiles = null;

                startTimer();

            }

            digInGsmStatusGpio.addListener(new GpioGsmStateListener());

            gpio.unprovisionPin(digInGsmStatusGpio);
            gsmState = null;

        } catch (Exception ex) {
        }

    }

    public class GpioGsmStateListener implements GpioPinListenerDigital {

        @Override

        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

            // display pin state on console
            PinState gsmState = event.getState();
            if (gsmState == GSM_STATUS_ON_STATE) {
                set_gsm_connected_status(true);

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("GSM STATUS  ON");

                objReadFiles = null;

            } else {

                set_gsm_connected_status(false);

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("GSM STATUS OFF");

                objReadFiles = null;

                setGsmModuleOn(false);

                startTimer();

                if (!clsSharedVariables.getSeparateGPS()) {
                    close_gps_serial_port();
                }
                close_at_serial_port();

                try {

                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                imgGps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/noGps.png")));
                                imgNetwork.setVisible(false);
                                imgMobile.setVisible(false);
                                imgServerConn.setVisible(false);
                                imgServerConn2.setVisible(false);
                                imgServerConn3.setVisible(false);
                                imgServerConn4.setVisible(false);
                                imgServerConn5.setVisible(false);
                                imgSigStr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signal-strength_5.png")));

                            } catch (Exception ex) {

                            }

                        }

                    });

                } catch (Exception ex) {

                }

            }
            gsmState = null;
        }

    }

    public void startTimer() {

        //set a new Timer
        if (gsm_status_timer != null) {
            gsm_status_timer = new Timer();
            //initialize the TimerTask's job
            initializeGsmTimerTask();
            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            gsm_status_timer.schedule(gsm_status_timer_task, 120000, 120000);
        }

    }

    public void initializeGsmTimerTask() {

        gsm_status_timer_task = new TimerTask() {

            @Override
            public void run() {

                if (get_gsm_connected_status() == true) {

                    //stop timerr
                    try {

                        gsm_status_timer.cancel();

                        gsm_status_timer_task.cancel();

                    } catch (Exception ex) {

                    } finally {

                        gsm_status_timer = null;

                        gsm_status_timer_task = null;

                    }

                } else {

                    if (SerialPortList.getPortNames().length >= 6) {

                        set_gsm_connected_status(true);

                        try {

                            gsm_status_timer.cancel();

                            gsm_status_timer_task.cancel();

                        } catch (Exception ex) {

                        } finally {

                            gsm_status_timer = null;

                            gsm_status_timer_task = null;

                        }

                    } else {

                        write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_OFF_STATE, digOutGsmResetPin);

                        try {

                            Thread.sleep(2000);

                        } catch (InterruptedException ex) {

                        }

                        write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);

                    }

                }

            }

        };

    }

    public void startSleepModeTimer() {

        //set a new Timer
        if (sleep_mode_timer == null) {

            ignition_off_cnt = 0;

            sleep_mode_timer = new Timer();

            //initialize the TimerTask's job
            initializeSleepModeTimerTask();

            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            sleep_mode_timer.schedule(sleep_mode_timer_task, 0, 60000);

        }

    }

    public void stop_sleep_modetimer() {

        try {

            if (sleep_mode_timer != null) {

                sleep_mode_timer.cancel();

                sleep_mode_timer_task.cancel();

            }

        } catch (Exception ex) {

        } finally {

            sleep_mode_timer = null;

            sleep_mode_timer_task = null;

        }

    }

    public void initializeSleepModeTimerTask() {

        sleep_mode_timer_task = new TimerTask() {

            @Override
            public void run() {

                if (clsSharedVariables.getIgnitionStatus() == false) {
                    if (ignition_off_cnt++ >= clsSharedVariables.getMainDeviceShutTime()) {

                        ignition_off_cnt = 0;

                        clsSharedVariables.setIgnitionStatus(true);

                        if (clsSharedVariables.getSleepModeState() == SLEEP_SHUTDOWN_MODE) {

                            shutdown_system();

                        } else {
                            power_off_conditions();
                            show_message_dialogbox("Sleep Mode Enabled \n packets are sent at configurable sleep time");
                        }
                        stop_sleep_modetimer();
                    }
                }
            }
        };

    }

    class StateMachine extends Thread {

        clsReadFiles objReadFiles = new clsReadFiles();

        int timer_inc = 0;

        int time_update_inc = 0;

        boolean time_upadte_status = false;

        int timer_inc1 = 0;

        int timer_apc_inc = 0;

        int video_inc = 0;

        int specl_audio_inc = 0;

        int specl_value = 0;

        byte ign_state = 0;

        byte mains_state = 0;

        boolean test_disbrd = false;

        byte port_check_cnt = 0;

        Date today = null;

        Calendar cal;

        boolean emer_color_change = false;

        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

        boolean net_bal_visible = false;
        boolean hdisk_mounted = false;
        boolean hdisk_automounted_first = false;
        int no_working_minutes_of_unit = 0;
        final int MAX_NO_MINUTES_TO_RESTART = 60 * 10 * 2; //10 hours
        int no_of_times = 0;
        int harddisk_unmount_inc = 0;
        boolean power_on = false;
        long present_Time;
        long previous_Time = 0;
        long prev_Time = 0;
        boolean fetchmisseddatacam1 = false;
        boolean fetchmisseddatacam5 = false;

        @Override
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
            cal = Calendar.getInstance();
            today = new Date(cal.getTimeInMillis());
            clsAudioFilesQueue objAudioQueue = new clsAudioFilesQueue();
            while (true) {

                objwatDog.setSch_watchdog_val((byte) 2);
                try {

                    if (power_on == true && (timer_inc % 10 == 0)) {
                        if (no_of_times++ > 3) {
                            power_on = false;
                        }
                    }
                    // schedule state machine
                    if (timer_inc % 5 == 0) {
                        if (get_special_msg_status() == false) {
                            schedule_state_machine();
                        }
                    }

                    if (clsSharedVariables.getNetBalanceStatusPresent() == clsDefines.NET_BAL_NOTPRESENT) {

                        if (net_bal_visible == false) {

                            net_bal_visible = true;

                            imgMobile.setVisible(true);

                        } else {

                            net_bal_visible = false;

                            imgMobile.setVisible(false);
                        }
                    }
                    if (clsSharedVariables.getEmergencyServicesEnable() == true) {

                        if (getEmergencyMapPktCame() == true) {

                            if (objPanel == null) {

                                if (emer_color_change == false) {

                                    emer_color_change = true;

                                    SwingUtilities.invokeLater(() -> {

                                        try {
                                            btnEmergency.setBackground(Color.red);
                                        } catch (Exception ex) {

                                        }
                                    });

                                } else {

                                    emer_color_change = false;

                                    SwingUtilities.invokeLater(() -> {

                                        try {

                                            btnEmergency.setBackground(Color.GREEN);

                                        } catch (Exception ex) {

                                        }

                                    });

                                }

                            } else if (objPanel.equals(panCenterMainPane)) {
                                if (emer_color_change == false) {
                                    emer_color_change = true;
                                    SwingUtilities.invokeLater(() -> {
                                        try {
                                            btnEmergency.setBackground(Color.red);
                                        } catch (Exception ex) {
                                        }

                                    });

                                } else {
                                    emer_color_change = false;
                                    SwingUtilities.invokeLater(() -> {
                                        try {
                                            btnEmergency.setBackground(Color.GREEN);
                                        } catch (Exception ex) {

                                        }

                                    });

                                }

                            }

                        }

                    }
                    cal = Calendar.getInstance();
                    today = new Date(cal.getTimeInMillis());
                    SwingUtilities.invokeLater(() -> {
                        if (cal.getTime().getYear() + 1900 >= 2023) {
                            try {
                                lblTime.setText(sdf.format(today));
                            } catch (Exception ex) {
                            }
                        }
                    });
                    try {
                        cal = Calendar.getInstance();
                        present_Time = (cal.getTimeInMillis());
                        if (previous_Time == 0) {
                            previous_Time = present_Time;
                        }
                        if (present_Time - previous_Time > clsSharedVariables.getCanUpdateTimeInterval() * 1000) {
                            previous_Time = present_Time;
                            if (clsSharedVariables.getCanEnabled()) {
                                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol()) {
                                    clsSmcTrackingPackets objSmc = new clsSmcTrackingPackets();
                                    objSmc.can_message_smc();
                                    objSmc = null;
                                }
                                try {
                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        cls16833Protocols obj16833protocols = new cls16833Protocols();
                                        obj16833protocols.can_protocol_cesl();
                                        obj16833protocols = null;

                                    }
                                } catch (Exception ex) {
                                }
                            }
                        }
                    } catch (Exception ex) {
                    }
                    //surat event packet
                    try {
                        if (prev_Time == 0) {
                            prev_Time = present_Time;
                        }
                        if (present_Time - prev_Time > (video_update_rate * 60) * 1000) {
                            prev_Time = present_Time;
                            if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol()) {
                                clsSmcTrackingPackets objSmc = new clsSmcTrackingPackets();
                                objSmc.event_pkt_smc(clsSharedVariables.dis_brd_status);
                                objSmc = null;
                            }
                        }
                    } catch (Exception ex) {

                    }
                    if (timer_inc++ > 30) {
                        timer_inc = 0;
                        if (time_upadte_status == false) {
                            time_update_inc++;
                            cal = Calendar.getInstance();
                            if (cal.getTime().getYear() + 1900 < 2023) {
                                update_time_tinkeros();
                                if (time_update_inc > 5) {
                                    time_upadte_status = true;
                                }
                            } else {

                                time_upadte_status = true;
                            }

                        }
                        if (clsSharedVariables.getStorageType() == clsDefines.STORAGE_HARDDISK && getMainsStatus() == true) {
                            hdisk_mounted = objReadFiles.check_harddisk_mounted();
                            try {
                                if (hdisk_mounted == true && media_filepath.listFiles() != null && media_filepath.listFiles().length == 0 && hdisk_automounted_first == false) {
                                    hdisk_automounted_first = true;
                                    objReadFiles.harddisk_automount();
                                }
                            } catch (Exception ex) {
                            }
                            if (hdisk_mounted == false && (clsSharedVariables.getHardDriveDetected() == true)) {
                                clsSharedVariables.setHardDriveDetected(false);
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateHarddiskIcon();
                                    }
                                });
                                objReadFiles.write_data_imp1_log("Hardisk not detected");
                            } else if (hdisk_mounted == true && (clsSharedVariables.getHardDriveDetected() == false)) {

                                harddisk_unmount_inc = 0;
                                file_paths_assignment();
                                clsSharedVariables.setHardDriveDetected(true);
                                objReadFiles.write_data_imp1_log("Hardisk detected");
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateHarddiskIcon();
                                    }
                                });
                            } else if (hdisk_mounted == false && (clsSharedVariables.getHardDriveDetected() == false)) {
                                harddisk_unmount_inc++;
                                switch (harddisk_unmount_inc) {
                                    case 10:
                                        objReadFiles.harddisk_power_on(false);

                                        break;
                                    case 11:
                                        objReadFiles.harddisk_power_on(true);
                                        break;
                                    case 12:
                                        if (objReadFiles.harddisk_mount() == false) {
                                            objReadFiles.harddisk_mount();
                                        }
                                        harddisk_unmount_inc = 0;
                                        objReadFiles.write_data_imp1_log("Hardisk power on/off");
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                        SwingUtilities.invokeLater(() -> {
                            lblNetworkOperatorName.setText(clsSharedVariables.getNetworkOperatorName());
                        });
                        //special audio
                        try {
                            if (clsSharedVariables.getSplAudioEnabled() && clsSharedVariables.getSplAudioAnnouncement()) {
                                if (specl_audio_playing == true) {
                                    if (specl_audio_inc++ > clsSharedVariables.getSplAudioInterval()) {
                                        specl_audio_playing = false;
                                        specl_audio_inc = 0;
                                        // System.out.println("value : " + specl_value);
                                        switch (specl_value) {
                                            case 0:
                                                if (clsSharedVariables.getSplAudio1()) {
                                                    objAudioQueue.addDataSpel("/SpelAudio1.WAV");
                                                    if (clsSharedVariables.getSplAudio2()) {
                                                        specl_value = 1;
                                                    } else {
                                                        specl_value = 0;
                                                    }
                                                }

                                                break;
                                            case 1:
                                                if (clsSharedVariables.getSplAudio2()) {
                                                    objAudioQueue.addDataSpel("/SpelAudio2.WAV");
                                                    if (clsSharedVariables.getSplAudio3()) {
                                                        specl_value = 2;
                                                    } else {
                                                        specl_value = 0;
                                                    }
                                                }
                                                break;
                                            case 2:
                                                if (clsSharedVariables.getSplAudio3()) {
                                                    objAudioQueue.addDataSpel("/SpelAudio3.WAV");
                                                    if (clsSharedVariables.getSplAudio4()) {
                                                        specl_value = 3;
                                                    } else {
                                                        specl_value = 0;
                                                    }
                                                }

                                                break;
                                            case 3:
                                                if (clsSharedVariables.getSplAudio4()) {
                                                    objAudioQueue.addDataSpel("/SpelAudio4.WAV");
                                                    if (clsSharedVariables.getSplAudio5()) {
                                                        specl_value = 4;
                                                    } else {
                                                        specl_value = 0;
                                                    }
                                                }

                                                break;
                                            case 4:
                                                if (clsSharedVariables.getSplAudio5()) {
                                                    objAudioQueue.addDataSpel("/SpelAudio5.WAV");
                                                    specl_value = 0;
                                                }
                                                break;
                                            default:
                                                specl_value = 0;
                                                break;

                                        }
                                        if (specl_value == 5) {
                                            specl_value = 0;
                                        }
                                    }
                                }

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        //apc forrir
                        try {
                            // if (apc_data_came == true) {
                            if (timer_apc_inc++ > 100) {// 60 30 min 120 1 hour 1 hr 5 min
                                timer_apc_inc = 0;
//                                if ((clsSharedVariables.getCam6Type() == clsDefines.CAM_FOORIR) && (clsSharedVariables.getVideo6Connected())) {
//                                    try {
//                                        fetch_apc_data((byte) 6);
//                                    } catch (Exception ex) {
//
//                                    }
//                                }
//                                if ((clsSharedVariables.getCam7Type() == clsDefines.CAM_FOORIR) && (clsSharedVariables.getVideo7Connected())) {
//                                    try {
//                                        fetch_apc_data((byte) 7);
//                                    } catch (Exception ex) {
//
//                                    }
//                                }
                            }
                            /*} else {
                                if (timer_apc_inc++ > 5) {//5
                                    timer_apc_inc = 0;
                                    if ((clsSharedVariables.getCam1Type() == clsDefines.CAM_FOORIR) && (clsSharedVariables.getVideo1Connected())) {
                                        if (!fetchmisseddatacam1) {
                                            try {
                                                fetchMissedData((byte) 1);
                                                fetchmisseddatacam1 = true;
                                            } catch (Exception ex) {

                                            }

                                        }
                                        fetch_apc_data((byte) 1);
                                    }
                                    if ((clsSharedVariables.getCam5Type() == clsDefines.CAM_FOORIR) && (clsSharedVariables.getVideo5Connected())) {
                                        if (!fetchmisseddatacam5) {
                                            try {
                                                fetchMissedData((byte) 5);
                                                fetchmisseddatacam5 = true;
                                            } catch (Exception ex) {

                                            }
                                        }
                                        fetch_apc_data((byte) 5);
                                    }
                                }
                            }*/
                        } catch (Exception ex) {

                        }

                        try {
                            if (timer_inc1 == 0 || timer_inc1 > 60) {
                                timer_inc1 = 1;
                                cal = null;
                                try {
                                    System.gc();

                                } catch (Exception ex) {

                                }
                            }
                            timer_inc1++;
                        } catch (Exception ex) {

                        }
                        try {
                            if (video_inc++ > video_update_rate * 2) {
                                video_inc = 0;
                                if (clsSharedVariables.getCam1Enabled()) {
                                    if (getVideo1Connected()) {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 1, clsDefines.VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 1, VIDEO_CONNECT);
                                        }
                                    } else {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 1, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 1, VIDEO_DISCONNECT);
                                        }

                                    }
                                }
                                if (clsSharedVariables.getCam2Enabled()) {
                                    if (getVideo2Connected()) {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 2, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 2, VIDEO_CONNECT);
                                        }

                                    } else {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 2, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 2, VIDEO_DISCONNECT);
                                        }

                                    }
                                }
                                if (clsSharedVariables.getCam3Enabled()) {

                                    if (getVideo3Connected()) {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 3, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 3, VIDEO_CONNECT);
                                        }

                                    } else {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 3, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 3, VIDEO_DISCONNECT);
                                        }

                                    }
                                }
                                if (clsSharedVariables.getCam4Enabled()) {

                                    if (getVideo4Connected()) {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 4, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 4, VIDEO_CONNECT);
                                        }

                                    } else {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 4, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 4, VIDEO_DISCONNECT);
                                        }
                                    }
                                }
                                if (clsSharedVariables.getCam5Enabled()) {

                                    if (clsSharedVariables.getVideo5Connected()) {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 5, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 5, VIDEO_CONNECT);
                                        }

                                    } else {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 5, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 5, VIDEO_DISCONNECT);
                                        }

                                    }
                                }
                                if (clsSharedVariables.getCam6Enabled()) {

                                    if (clsSharedVariables.getVideo6Connected()) {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 6, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 6, VIDEO_CONNECT);
                                        }

                                    } else {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 6, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 6, VIDEO_DISCONNECT);
                                        }

                                    }
                                }
                                if (clsSharedVariables.getCam7Enabled()) {

                                    if (clsSharedVariables.getVideo7Connected()) {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 7, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 7, VIDEO_CONNECT);
                                        }

                                    } else {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 7, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 7, VIDEO_DISCONNECT);
                                        }

                                    }
                                }
                                if (clsSharedVariables.getCam8Enabled()) {

                                    if (clsSharedVariables.getVideo8Connected()) {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 8, VIDEO_CONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 8, VIDEO_CONNECT);
                                        }

                                    } else {

                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.video_disconnect_pkt((byte) 8, VIDEO_DISCONNECT);
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.video_disconnect_pkt((byte) 8, VIDEO_DISCONNECT);
                                        }
                                    }
                                }
                                if (getDisBrdName() == SUMITH_DISBRD) {

                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        obj16833Pkts.displayboard_disconnect_pkt((byte) 1, objHealth.get_fd_status());
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        objDriving.displayboard_disconnect_pkt((byte) 1, objHealth.get_fd_status());
                                    }

                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        obj16833Pkts.displayboard_disconnect_pkt((byte) 2, objHealth.get_sd_status());
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        objDriving.displayboard_disconnect_pkt((byte) 2, objHealth.get_sd_status());
                                    }
                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        obj16833Pkts.displayboard_disconnect_pkt((byte) 3, objHealth.get_rd_status());
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        objDriving.displayboard_disconnect_pkt((byte) 3, objHealth.get_rd_status());
                                    }
                                    if (clsSharedVariables.getIpAddr1Enable()) {
                                        obj16833Pkts.displayboard_disconnect_pkt((byte) 4, objHealth.get_id_status());
                                    }
                                    if (clsSharedVariables.getIpAddr4Enable()) {
                                        objDriving.displayboard_disconnect_pkt((byte) 4, objHealth.get_id_status());
                                    }
                                    if (getArticulatedBus() && getArtSideEnable()) {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.displayboard_disconnect_pkt((byte) 5, objHealth.get_sd_art_status());
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.displayboard_disconnect_pkt((byte) 5, objHealth.get_sd_art_status());
                                        }
                                    }
                                    if (getArticulatedBus() && getArtIntEnable()) {
                                        if (clsSharedVariables.getIpAddr1Enable()) {
                                            obj16833Pkts.displayboard_disconnect_pkt((byte) 6, objHealth.get_id_art_status());
                                        }
                                        if (clsSharedVariables.getIpAddr4Enable()) {
                                            objDriving.displayboard_disconnect_pkt((byte) 6, objHealth.get_id_art_status());
                                        }
                                    }
                                }
                                if (clsSharedVariables.getIpAddr1Enable()) {
                                    obj16833Pkts.healthMsgPkt(false);
                                }
                                if (clsSharedVariables.getIpAddr4Enable()) {
                                    objDriving.health_construct_pkt();
                                }
                            }
                        } catch (Exception ex) {

                        }
                        if (port_check_cnt++ >= 6) {
                            port_check_cnt = 0;
                            findPortsDetection();
                        }
                        try {
                            if (today != null) {

                                no_working_minutes_of_unit++;  // every 30 sec one inc

                                if (no_working_minutes_of_unit > MAX_NO_MINUTES_TO_RESTART) {

                                    if (clsSharedVariables.getIgnitionStatus()) {

                                        if (today.getHours() > 1 && today.getHours() < 8) {

                                            try {
                                                reboot_system("Night " + today.getHours());
                                                Process proc;
                                                reset_obu();
                                                if (OBU_BRD_TYPE == TINKER_BOARD) {
                                                    reboot_tinker_board("Night " + today.getHours());
                                                } else {
                                                    proc = Runtime.getRuntime().exec("sudo reboot");  //modified sumitha
                                                    proc.waitFor();
                                                    proc = null;
                                                }

                                            } catch (Exception e) {

                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {

                        }
                    } else {
                        Thread.sleep(1000);
                        if (isInterrupted()) {
                            break;
                        }
                    }
                } catch (Exception ex) {
                }
                try {
                    Thread.sleep(10);

                } catch (InterruptedException ex) {
                }

            }

        }

        class ExceptionHandler implements UncaughtExceptionHandler {

            @Override

            public void uncaughtException(Thread t, Throwable e) {

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_low_memory_data(" StateMachine UnCaughtExceptionHandler 1 " + e.getClass().getName() + e.getMessage() + " Restarting StateMachine Again " + e.getMessage());

                objReadFiles = new clsReadFiles();

                try {

                    objStateMachine = new StateMachine();

                    objStateMachine.start();

                } catch (Exception ex) {

                    objStateMachine = null;

                }

            }

        }

        public boolean check_route_availability() {

            short j = 0;

            //  String file_name;
            short cur_trip_no = getCurTripNo();

            if (getCurSchNoTrips() <= 0) {

                return false;

            }

            if (cur_trip_no < getCurSchNoTrips()) {

                for (j = 0; j < getNoRoutes(); j++) {

                    if (objRouteMasFiles[j].route_no.equals(getCurSchRouteNo(cur_trip_no))) {

                        return true;

                    }

                }

            }

            return false;

        }

        private void route_end_from_gprs() {
           // clsApcPacketConstruct objApc = new clsApcPacketConstruct();
            clsReadDataFiles objReadData = new clsReadDataFiles();
            int k = 0;

            short l_cur_trip_no_end = (short) getCurTripNo();

            if (driverLoginEnabled == true && clsSharedVariables.getDisplayDriverDb()) {

                if (getIntEnable()) {

                    setDisBrdDelPktCame(true);

                }

            }
            try {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.trip_end_pkt();
                }
            } catch (Exception ex) {
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                objDriving.trip_end_pkt();
            }
            if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_END);
            }
//            try {
//                if (getApcStatus() != clsDefines.APC_STOP) {
//                    setApcStatus(clsDefines.APC_STOP);
//                    objApc.apc_end_packet();
//
//                }
//            } catch (Exception ex) {
//            }
//            SwingWorker sw1 = new SwingWorker() {
//                @Override
//                protected String doInBackground() throws Exception {
//                    try {
//                        peopleCountRouteEnd((byte) 6);
//                    } catch (Exception ex) {
//                    }
//                    try {
//                        StringBuilder sb = new StringBuilder();
//                        sb.append(Calendar.getInstance().getTimeInMillis());
//                        sb.append(",");
//                        sb.append(clsSharedVariables.getApcStartTime());
//                        sb.append(",");
//                        sb.append(clsSharedVariables.getApcStopTime());
//                        sb.append(",");
//                        sb.append(clsSharedVariables.getApcPeopleIn());
//                        sb.append(",");
//                        sb.append(clsSharedVariables.getApcPeopleOut());
//                        sb.append(",");
//                        sb.append(clsSharedVariables.getApcStopName());
//                        sb.append(",");
//                        sb.append(clsSharedVariables.getApcRouteNo());
//                        sb.append(",");
//
//                        objReadData.write_apc_route_data(sb.toString());
//                        try {
//                            peopleCountRouteEnd((byte) 7);
//                        } catch (Exception ex) {
//                        }
//                        StringBuilder sb1 = new StringBuilder();
//                        sb1.append(Calendar.getInstance().getTimeInMillis());
//                        sb1.append(",");
//                        sb1.append(clsSharedVariables.getApcStartTime());
//                        sb1.append(",");
//                        sb1.append(clsSharedVariables.getApcStopTime());
//                        sb1.append(",");
//                        sb1.append(clsSharedVariables.getApcPeopleIn1());
//                        sb1.append(",");
//                        sb1.append(clsSharedVariables.getApcPeopleOut1());
//                        sb1.append(",");
//                        sb1.append(clsSharedVariables.getApcStopName());
//                        sb1.append(",");
//                        sb1.append(clsSharedVariables.getApcRouteNo());
//                        sb1.append(",");
//                        objReadData.write_apc_route_data1(sb1.toString());
//                    } catch (Exception ex) {
//                    }
//                    try {
//                        if (clsSharedVariables.getIpAddr1Enable()) {
//                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
//                            // System.out.println(clsSharedVariables.getApcPeopleIn() + clsSharedVariables.getApcPeopleOut() + clsSharedVariables.getApcPeopleIn1() + clsSharedVariables.getApcPeopleOut1());
//                            obj16833Pkts.people_count_packet_on_route_end();
//                            obj16833Pkts = null;
//                        }
//                    } catch (Exception ex) {
//                    }
//                    return "Over";
//                }
//            };
//            sw1.execute();

            setCurTripStat(TRIP_END);

            setCurRouteStat(false);

            setCurStopNo((byte) 0);

            for (k = 0; k < getNoRoutes(); k++) {

                setCurSchTripStatus(k, TRIP_UNKNOWN);

                setCurRouteStat(false);

            }

            setCurSchTripStatus(l_cur_trip_no_end, TRIP_END);

            setMapStopState(clsDefines.ENDSTOP_AUDIO_STOP_STATE);

            setShowStopStopDis(0.0);

            setPrevLat(0.0);

            setPrevLat(0.0);

            setCurTripStat(TRIP_UNKNOWN);

            objReadFiles.write_cur_trip_info_file();

            objReadFiles.write_cur_route_info_file();

            objReadFiles.write_cur_schedule_file();

            selected_trip_no = l_cur_trip_no_end;
            clsSharedVariables.setTripStatusUpdated(true);
        }

        private void route_start_from_gprs() {

            int j = 0;

            boolean route_fnd = false;

            short k;

            String route_id = clsSharedVariables.getGprsRouteId().trim();

            for (j = 0; j < getNoRoutes(); j++) {

                if (objRouteMasFiles[j].route_no.trim().equals(route_id)) {

                    setCurTripNo((short) j);

                    selected_trip_no = (short) j;

                    clsBusStopDetection.gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;

                    setShowStopStopDis((double) 0.0);

                    setDisBrdDelPktCame(false);

                    setPrevLat(0.0);

                    setPrevLat(0.0);

                    setCurRouteStat(false);

                    setCurStopNo((byte) 0);

                    setCurTripStat(TRIP_UNKNOWN);

                    setCurStopNo((byte) 0);

                    setCurTripStat(TRIP_START);

                    setCurSchTripStatus(j, TRIP_START);

                    setCurRouteNo(getCurSchRouteNo(j));

                    final ClsSchedule objSch = new ClsSchedule();

                    objSch.send_route_info();

                    int avg_speed = AVG_BUS_SPEED;

                    int dur = 100;

                    long sec;

                    sec = Calendar.getInstance().getTimeInMillis();

                    try {

                        if (getNoStopsRoute() > 1) {

                            double next_stop_distance = gps_data[getNoStopsRoute() - 1].stop_stop_dis;

                            dur = (int) (next_stop_distance * 18) / (avg_speed * 5);  //(formaula : time = ((distance * 18)/(speed *5) )

                        }

                    } catch (Exception ex) {

                        dur = 10;

                    }

                    dur = dur * 1000;

                    Calendar cal = Calendar.getInstance();

                    cal.setTimeInMillis(sec + dur);

                    if (clsSharedVariables.getIpAddr1Enable()) {
                        obj16833Pkts.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));
                    }
                    if (clsSharedVariables.getIpAddr4Enable()) {
                        objDriving.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));
                    }
                    if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {
                        objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_START);
                    }

                    setCurSchTripStatus(j, TRIP_START);

                    objReadFiles.write_cur_trip_info_file();

                    objReadFiles.write_cur_route_info_file();

                    objReadFiles.write_cur_schedule_file();

                    clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

                    objHealth.set_route_no(getCurSchRouteNo(j));

                    objHealth.set_trip_no((byte) (j + 1));

                    objHealth = null;
                    clsSharedVariables.setTripStatusUpdated(true);
                    break;

                }

            }

        }

        public void schedule_state_machine() {

            try {

                if (getSchRouteEnable() == ROUTE_ENABLE) {
                    if (clsSharedVariables.getGprsRouteEndPktCame()) {
                        long cur_sec = Calendar.getInstance().getTimeInMillis();
                        if (cur_sec > clsSharedVariables.getGprsRouteEndDateTime()) {
                            route_end_from_gprs();
                            clsSharedVariables.setGprsRouteEndPktCame(false);
                        }
                    }

                    if (clsSharedVariables.getGprsRouteStartPktCame()) {
                        long cur_sec = Calendar.getInstance().getTimeInMillis();
                        if (cur_sec > clsSharedVariables.getGprsRouteStartDateTime()) {
                            route_start_from_gprs();
                            clsSharedVariables.setGprsRouteStartPktCame(false);
                        }
                    }
                    return;
                }

                switch (sch_state) {

                    case SCH_INIT_STATE:
                        sch_state = SCH_SCH_REQ;

                        break;

                    case SCH_SCH_REQ:

                        if (getSchReq() == false) {

                            setSchReq(true);

                            // send schedule request packet
                            if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                                    || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                                    || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

                                clsSharedVariables.first_time_to_send_sch_req = true;

                                clsSharedVariables.first_time_to_send_sch_req_ip2 = true;

                            }

                        }

                        sch_state = SCH_STATE_CHK_SCHDULE;

                        break;

                    case SCH_STATE_CHK_SCHDULE:

                        if (getCurSchAvailable() == true) {

                            // load current schuedule
                            sch_state = SCH_STATE_LOAD_CUR_SCH;

                        } else {

                            // wait for new schedule because there is no any schedule available
                            // send schedule request
                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    case SCH_STATE_LOAD_CUR_SCH:

                        if (getCurSchAvailable() == true) {

                            sch_state = SCH_STATE_SEND_DISPLAY_DATA;

                        } else {

                            // wait for new schedule because there is no any schedule available
                            // send schedule request
                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    case SCH_STATE_WAIT_FOR_SCHEDULE:

                        // it will be reached here if current schedule is not available
                        // it will here only until schedule received
                        long cur_sec = Calendar.getInstance().getTimeInMillis();
                        if ((getTempSchAvailable() == true) && (cur_sec > temp_sch.sch_starttime)) {

                            sch_state = SCH_STATE_LOAD_TEMP_SCH;

                            setSchUpdated(true);

                        } else if ((getPermSchAvailable() == true) && (cur_sec > getPermSchStartTime())) {

                            sch_state = SCH_STATE_LOAD_PER_SCH;

                            setSchUpdated(true);
                        } else if (cur_sec > getCurSchEndTime()) {

                            sch_state = SCH_STATE_LOAD_PER_AIGAN_SCH;

                            setSchUpdated(true);

                        } else {

                            sch_state = SCH_STATE_CHK_SCHDULE;

                        }

                        break;

                    case SCH_STATE_LOAD_TEMP_SCH:

                        if (objReadFiles.load_temp_schedule() == true && getCurTripStat() != TRIP_START) {

                            setSchUpdated(true);

                            sch_state = SCH_STATE_SEND_DISPLAY_DATA;

                        } else {

                            // wait for new schedule because there is no any schedule available
                            // send schedule request
                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    case SCH_STATE_LOAD_PER_SCH:

                        if (objReadFiles.load_perm_schedule() == true && getCurTripStat() != TRIP_START) {

                            setSchUpdated(true);

                            sch_state = SCH_STATE_SEND_DISPLAY_DATA;

                        } else { // wait for new schedule because there is no any schedule available

                            // send schedule request
                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    case SCH_STATE_LOAD_PER_AIGAN_SCH:

                        if (objReadFiles.load_perm_sch_again() == true && getCurTripStat() != TRIP_START) {

                            sch_state = SCH_STATE_SEND_DISPLAY_DATA;

                        } else {

                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    case SCH_STATE_SEND_DISPLAY_DATA:

                        // send all data to the display boards
                        if (check_route_availability() == true) //send_route_info() == true) {
                        {

                            sch_state = SCH_CHK_CUR_STATE;

                        } else {

                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    case SCH_CHK_CUR_STATE:

                        // check end time if GPS available
                        // on end time , delete current schedule file and go wait for schedule
                        // goto temp schedule checking
                        cur_sec = Calendar.getInstance().getTimeInMillis();
                        if ((getTempSchAvailable() == true) && (cur_sec > clsSharedVariables.temp_sch.sch_starttime) && getCurTripStat() != TRIP_START) {

                            sch_state = SCH_STATE_LOAD_TEMP_SCH;

                        } else if ((getPermSchAvailable() == true) && (cur_sec > getPermSchStartTime()) && getCurTripStat() != TRIP_START) {

                            sch_state = SCH_STATE_LOAD_PER_SCH;

                        } else if (cur_sec > getCurSchEndTime()) {

                            objReadFiles.load_perm_schedule();

                            setSchUpdated(true);

                            sch_state = SCH_STATE_WAIT_FOR_SCHEDULE;

                        }

                        break;

                    default:

                        break;

                }

            } catch (Exception ex) {

            }

        }

    }

    class FilesThread extends Thread {

        @Override
        public void run() {
            try {
                clsReadFiles objReadFiles = new clsReadFiles();
                try {
                    init_functions_file_thread();
                } catch (Exception e) {
                }
                runCmd("sudo timedatectl set-timezone Asia/Kolkata");
                runCmd("sudo timedatectl set-ntp true");
                runCmd("sudo rfkill block bluetooth");
                runCmd("sudo timedatectl set-timezone Asia/Kolkata");
                runCmd("sudo systemctl disable rsyslog");
                runCmd("sudo watchdog -f interval=10");
                runCmd("sudo watchdog -f logtick=60");
                runCmd("sudo watchdog -f max-load-1=60");
                nmcli_commands();
                try {
                    try {
                        objReadFiles.read_product_storage_name();
                    } catch (Exception ex) {
                    }
                    if (clsSharedVariables.getSharedNetwork() == true) {
                        clsDefines.CAM1_IPADDR = "10.42.0.3";
                        clsDefines.CAM2_IPADDR = "10.42.0.4";
                        clsDefines.CAM3_IPADDR = "10.42.0.5";
                        clsDefines.CAM4_IPADDR = "10.42.0.6";
                        clsDefines.CAM5_IPADDR = "10.42.0.7";
                        clsDefines.CAM6_IPADDR = "10.42.0.8";
                        clsDefines.CAM7_IPADDR = "10.42.0.9";
                        clsDefines.CAM8_IPADDR = "10.42.0.10";
                        clsDefines.CAM_ANALOG_IPADDR = "10.42.0.7";
                    } else {
                        clsDefines.CAM1_IPADDR = "192.168.0.3";
                        clsDefines.CAM2_IPADDR = "192.168.0.4";
                        clsDefines.CAM3_IPADDR = "192.168.0.5";
                        clsDefines.CAM4_IPADDR = "192.168.0.6";
                        clsDefines.CAM5_IPADDR = "192.168.0.7";
                        clsDefines.CAM6_IPADDR = "192.168.0.8";
                        clsDefines.CAM7_IPADDR = "192.168.0.9";
                        clsDefines.CAM8_IPADDR = "192.168.0.10";
                        clsDefines.CAM_ANALOG_IPADDR = "192.168.0.7";
                    }
                } catch (Exception ex) {
                }
                threads_initiate();

                routefilecheck();

                deleteExtraVideoFilesPowerOn();

                delete_previous_snapshots_event_files();

            } catch (Exception e) {

            }

        }

        private void threads_initiate() {
            clsReadFiles objReadFiles = new clsReadFiles();
            try {

                if (clsDefines.GSM_MODULE_AVAILABLE) {
                    objLocation = new GPSLocationService(imgGps);

                    objGprs = new clsGprsService(imgServerConn, imgMobile, imgWifi, imgServerConn2, imgServerConn3, imgServerConn4, imgServerConn5);

                    objAtPort = new clsAtSerialPort(imgSigStr, frameDialogBox, imgNetwork, imgSimDetect);

                    objAtPort.startAtPort();
                }

                objDisBrdSerial = new clsDisplayBrdSerialPort();

                try {

                    ClsVideoDeleteFiles objVideoDel1 = new ClsVideoDeleteFiles();

                    objVideoDel1.start();

                } catch (Exception e) {

                }

//                if (getCanEnabled()) {
//                    objCanSerial = new clsCanSerialPort();
//                }
//                if (getCanEnabled1() && clsSharedVariables.getRs232Enable() == false) {
//                    imgCan1.setVisible(true);
//                    objCanSerial1 = new clsCanSerialPort1();
//                }

//                if (clsSharedVariables.getRs232Enable() && clsSharedVariables.getCanEnabled1() == false) {
//                    objRs232 = new clsRs232Data(lbldriverid, lblconductorid);
//                }
//                if (clsSharedVariables.getEthernetEnable()) {
//                    objEthernet = new clsEthernetSerialport();
//                }
                try {
                    objStateMachine = new StateMachine();
                    objStateMachine.start();

                } catch (Exception ex) {
                    objStateMachine = null;
                }
                try {
                    startTimerUsb();
                } catch (Exception ex) {
                }
//                try {
//                    if (clsSharedVariables.getSplAudioEnabled() && clsSharedVariables.getSplAudioAnnouncement()) {
//                        startTimerSpelAudio();
//                    }
//                } catch (Exception ex) {
//
//                }

                if (getCam1Enabled()) {
                    objVideoRec1 = new clsVideoRecording((byte) 1);
                    objVideoRec1.start();
                }
            } catch (Exception ex) {
            }
            try {
                if (getCam2Enabled()) {
                    objVideoRec2 = new clsVideoRecording((byte) 2);
                    objVideoRec2.start();
                }
            } catch (Exception ex) {
            }

            try {
                if (getCam3Enabled()) {

                    objVideoRec3 = new clsVideoRecording((byte) 3);
                    objVideoRec3.start();
                }
            } catch (Exception ex) {

            }

            try {

                if (getCam4Enabled()) {
                    objVideoRec4 = new clsVideoRecording((byte) 4);
                    objVideoRec4.start();
                }

            } catch (Exception ex) {

            }

            try {
                if (clsSharedVariables.getCam5Enabled()) {
                    objVideoRec5 = new clsVideoRecording((byte) 5);
                    objVideoRec5.start();
                }
            } catch (Exception ex) {

            }
            try {
                if (clsSharedVariables.getCam6Enabled()) {
                    objVideoRec6 = new clsVideoRecording((byte) 6);
                    objVideoRec6.start();
                }
            } catch (Exception ex) {

            }
            try {
                if (clsSharedVariables.getCam7Enabled()) {
                    objVideoRec7 = new clsVideoRecording((byte) 7);
                    objVideoRec7.start();
                }
            } catch (Exception ex) {

            }
            try {
                if (clsSharedVariables.getCam8Enabled()) {
                    objVideoRec8 = new clsVideoRecording((byte) 8);
                    objVideoRec8.start();
                }
            } catch (Exception ex) {

            }

            try {
                if (clsSharedVariables.getGyroscope()) {
                    clsGyroScope objgyro = new clsGyroScope();
                    objgyro.start();
                }
            } catch (Exception ex) {
            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam1() || clsSharedVariables.getLinedetEnableCam1()) {
//                        objMotiondet1 = new clsMotionDet((byte) 1);
//                        objMotiondet1.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam2() || clsSharedVariables.getLinedetEnableCam2()) {
//                        objMotiondet2 = new clsMotionDet((byte) 2);
//                        objMotiondet2.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam3() || clsSharedVariables.getLinedetEnableCam3()) {
//                        objMotiondet3 = new clsMotionDet((byte) 3);
//                        objMotiondet3.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam4() || clsSharedVariables.getLinedetEnableCam4()) {
//                        objMotiondet4 = new clsMotionDet((byte) 4);
//                        objMotiondet4.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam5() || clsSharedVariables.getLinedetEnableCam5()) {
//                        objMotiondet5 = new clsMotionDet((byte) 5);
//                        objMotiondet5.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam6() || clsSharedVariables.getLinedetEnableCam6()) {
//                        objMotiondet6 = new clsMotionDet((byte) 6);
//                        objMotiondet6.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam7() || clsSharedVariables.getLinedetEnableCam7()) {
//                        objMotiondet7 = new clsMotionDet((byte) 7);
//                        objMotiondet7.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (clsSharedVariables.getOnvifSupported() == true) {
//                    if (clsSharedVariables.getMotionEnableCam8() || clsSharedVariables.getLinedetEnableCam8()) {
//                        objMotiondet8 = new clsMotionDet((byte) 8);
//                        objMotiondet8.start();
//                    }
//                }
//            } catch (Exception ex) {
//            }

            try {
                if (clsSharedVariables.getApcEnabled() == true) {
                    if (clsSharedVariables.getCam6Type() == clsDefines.CAM_DAHUA) {
                        lblPeopleIn1.setVisible(true);
                        lblPeopleIn2.setVisible(true);
                        lblPeopleOut1.setVisible(true);
                        lblPeopleOut2.setVisible(true);
//                        objPeopleCnt = new clsPeoplCountEvent();
//                        objPeopleCnt.start();
                    }
                }
            } catch (Exception ex) {

            }
            try {
                if (clsSharedVariables.getApcEnabled1() == true) {
                    if (clsSharedVariables.getCam7Type() == clsDefines.CAM_DAHUA) {
                        lblPeopleIn1.setVisible(true);
                        lblPeopleIn2.setVisible(true);
                        lblPeopleOut1.setVisible(true);
                        lblPeopleOut2.setVisible(true);
//                        objPeopleCnt1 = new clsPeopleCountEventSecondCamera();
//                        objPeopleCnt1.start();
                    }
                }
            } catch (Exception ex) {

            }

            if ((clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING)
                    || (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING)
                    || (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING)
                    || (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING)) {
                try {
                    if (clsSharedVariables.getCam1Enabled()) {
                        objPreEventRec1 = new clsPreEventREcord((byte) 1);
                        objPreEventRec1.start();
                    }
                } catch (Exception ex) {
                }

                try {
                    if (clsSharedVariables.getCam2Enabled()) {
                        objPreEventRec2 = new clsPreEventREcord((byte) 2);
                        objPreEventRec2.start();
                    }
                } catch (Exception ex) {
                }

                try {
                    if (clsSharedVariables.getCam3Enabled()) {
                        objPreEventRec3 = new clsPreEventREcord((byte) 3);
                        objPreEventRec3.start();
                    }
                } catch (Exception ex) {
                }
                try {
                    if (clsSharedVariables.getCam4Enabled()) {
                        objPreEventRec4 = new clsPreEventREcord((byte) 4);
                        objPreEventRec4.start();
                    }
                } catch (Exception ex) {
                }
                try {
                    if (clsSharedVariables.getCam5Enabled()) {
                        objPreEventRec5 = new clsPreEventREcord((byte) 5);
                        objPreEventRec5.start();
                    }
                } catch (Exception ex) {
                }
                try {
                    if (clsSharedVariables.getCam6Enabled()) {
                        objPreEventRec6 = new clsPreEventREcord((byte) 6);
                        objPreEventRec6.start();
                    }
                } catch (Exception ex) {
                }
                try {
                    if (clsSharedVariables.getCam7Enabled()) {
                        objPreEventRec7 = new clsPreEventREcord((byte) 7);
                        objPreEventRec7.start();
                    }
                } catch (Exception ex) {
                }

                try {
                    if (clsSharedVariables.getCam8Enabled()) {
                        objPreEventRec8 = new clsPreEventREcord((byte) 8);
                        objPreEventRec8.start();
                    }
                } catch (Exception ex) {
                }

                if (clsSharedVariables.getSnapShotContEnableCam1() || clsSharedVariables.getSnapShotContEnableCam2()
                        || clsSharedVariables.getSnapShotContEnableCam3() || clsSharedVariables.getSnapShotContEnableCam4()
                        || clsSharedVariables.getSnapShotContEnableCam5()
                        || clsSharedVariables.getSnapShotContEnableCam6()
                        || clsSharedVariables.getSnapShotContEnableCam7()
                        || clsSharedVariables.getSnapShotContEnableCam8()) {

                    clsSnapshotContinuous objSnapCont = new clsSnapshotContinuous();
                    objSnapCont.start();

                    /* if ((clsSharedVariables.getSnapShotContFtpUploadEnableCam1() == true) || (clsSharedVariables.getSnapShotContFtpUploadEnableCam2() == true)
                            || (clsSharedVariables.getSnapShotContFtpUploadEnableCam3() == true) || (clsSharedVariables.getSnapShotContFtpUploadEnableCam4() == true)) {
                        objReadFiles.read_snapshot_upload_lastfile();
                        clsUploadSnapshotsServer objUploadCont = new clsUploadSnapshotsServer();
                        objUploadCont.start();
                    }*/
                }
                /*else if (clsSharedVariables.getSnapShotContEnableCam5() || clsSharedVariables.getSnapShotContEnableCam6()
                        || clsSharedVariables.getSnapShotContEnableCam7() || clsSharedVariables.getSnapShotContEnableCam8()) {
                    clsSnapshotContinuous objSnapCont = new clsSnapshotContinuous();
                    objSnapCont.start();

                    /* if ((clsSharedVariables.getSnapShotContFtpUploadEnableCam5() == true) || (clsSharedVariables.getSnapShotContFtpUploadEnableCam6() == true)
                                || (clsSharedVariables.getSnapShotContFtpUploadEnableCam7() == true) || (clsSharedVariables.getSnapShotContFtpUploadEnableCam7() == true)) {
                            objReadFiles.read_snapshot_upload_lastfile();
                            clsUploadSnapshotsServer objUploadCont = new clsUploadSnapshotsServer();
                            objUploadCont.start();
                        }*/
                // }  */

            }
            clsWatchDogThread objWatchDog = new clsWatchDogThread();

            objWatchDog.start();
//            clsFtpUploadThread objFtp = new clsFtpUploadThread();
//
//            objFtp.setDaemon(
//                    true);
//            objFtp.start();
        }

        private void init_functions_file_thread() {
            clsReadFiles objReadFiles = new clsReadFiles();
            runCmd("sudo timedatectl set-timezone Asia/Kolkata");
            runCmd("sudo timedatectl set-ntp true");
            runCmd("sudo rfkill block bluetooth");
            runCmd("sudo timedatectl set-timezone Asia/Kolkata");
            runCmd("sudo systemctl disable rsyslog");
            permissionsObu();
            byte vol = get_speaker_volume();
            if (clsSharedVariables.os_version.contains("5.15.32")) {

                runCmd("sudo amixer sset 'Headphone'  unmute");
                switch (vol) {
                    case 0:

                        runCmd("sudo amixer sset 'Headphone' 73% unmute \r\n ");

                        break;

                    case 1:

                        runCmd("sudo amixer sset 'Headphone' 74% unmute \r\n");

                        break;

                    case 2:

                        runCmd("sudo amixer sset 'Headphone' 75% unmute \r\n");

                        break;

                    case 3:

                        runCmd("sudo amixer sset 'Headphone' 78% unmute \r\n");

                        break;

                    case 4:

                        runCmd("sudo amixer sset 'Headphone' 80% unmute \r\n");

                        break;

                    case 5:

                        runCmd("sudo amixer sset 'Headphone' 85% unmute \r\n");

                        break;

                    case 6:

                        runCmd("sudo amixer sset 'Headphone' 88% unmute \r\n");

                        break;

                    case 7:

                        runCmd("sudo amixer sset 'Headphone' 90% unmute \r\n");

                        break;

                    case 8:

                        runCmd("sudo amixer sset 'Headphone' 92% unmute \r\n");

                        break;

                    case 9:

                        runCmd("sudo amixer sset 'Headphone' 94% unmute \r\n");

                        break;

                    case 10:

                        runCmd("sudo amixer sset 'Headphone' 96% unmute \r\n");

                        break;
                }

            } else if (OBU_BRD_TYPE == TINKER_BOARD) {
                runCmd("amixer sset Master unmute \r\n");

                switch (vol) {

                    case 0:

                        runCmd("amixer sset Master 0%");

                        break;

                    case 1:

                        runCmd("amixer sset Master 40%");
                        break;

                    case 2:

                        runCmd("amixer sset Master 50%");
                        break;

                    case 3:

                        runCmd("amixer sset Master 60%");
                        break;

                    case 4:

                        runCmd("amixer sset Master 70%");
                        break;

                    case 5:

                        runCmd("amixer sset Master 76%");
                        break;

                    case 6:

                        runCmd("amixer sset Master 78%");
                        break;

                    case 7:

                        runCmd("amixer sset Master 80%");
                        break;

                    case 8:

                        runCmd("amixer sset Master 85%");
                        break;

                    case 9:

                        runCmd("amixer sset Master 88%");
                        break;

                    case 10:

                        runCmd("amixer sset Master 89%");

                        break;
                }
            } else if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
                if (clsSharedVariables.os_version.contains("5.10.103")) {

                    runCmd("amixer sset Master unmute \r\n");

                    switch (vol) {

                        case 0:

                            runCmd("amixer sset Master 0%");

                            break;

                        case 1:

                            runCmd("amixer sset Master 40%");
                            break;

                        case 2:

                            runCmd("amixer sset Master 50%");
                            break;

                        case 3:

                            runCmd("amixer sset Master 60%");
                            break;

                        case 4:

                            runCmd("amixer sset Master 70%");
                            break;

                        case 5:

                            runCmd("amixer sset Master 76%");
                            break;

                        case 6:

                            runCmd("amixer sset Master 78%");
                            break;

                        case 7:

                            runCmd("amixer sset Master 80%");
                            break;

                        case 8:

                            runCmd("amixer sset Master 85%");
                            break;

                        case 9:

                            runCmd("amixer sset Master 88%");
                            break;

                        case 10:

                            runCmd("amixer sset Master 89%");

                            break;

                    }
                }
            } else {
                if (vol < 3 || vol > 10) {

                    vol = 7;

                    set_speaker_volume((byte) 7);

                }

                runCmd("amixer set 'PCM' unmute \r\n");

                switch (vol) {

                    case 0:

                        runCmd("amixer -c 0 set  PCM  mute \r\n ");

                        break;

                    case 1:

                        runCmd("amixer -c 0 set  PCM   60% unmute \r\n");

                        break;

                    case 2:

                        runCmd("amixer -c 0 set  PCM   65% unmute \r\n");

                        break;

                    case 3:

                        runCmd("amixer -c 0 set  PCM  70% unmute \r\n");

                        break;

                    case 4:

                        runCmd("amixer -c 0 set  PCM  73% unmute \r\n");

                        break;

                    case 5:

                        runCmd("amixer -c 0 set  PCM  76% unmute \r\n");

                        break;

                    case 6:

                        runCmd("amixer -c 0 set  PCM  78% unmute \r\n");

                        break;

                    case 7:

                        runCmd("amixer -c 0 set  PCM  80% unmute \r\n");

                        break;

                    case 8:

                        runCmd("amixer -c 0 set  PCM  82% unmute \r\n");

                        break;

                    case 9:

                        runCmd("amixer -c 0 set  PCM  83% unmute \r\n");

                        break;

                    case 10:

                        runCmd("amixer -c 0 set  PCM  85% unmute \r\n");

                        break;

                }
            }

            if (clsSharedVariables.getIpAddr1Enable()) {
                if (obj16833Pkts == null) {
                    obj16833Pkts = new cls16833Protocols();
                }
                obj16833Pkts.obu_started();
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                if (objDriving == null) {
                    objDriving = new gpsDriving();
                }

                objDriving.obu_started();
            }
            if (clsSharedVariables.no_times_reset > 1000) {

                clsSharedVariables.no_times_reset = 0;

            }
            clsSharedVariables.reset_type = clsSharedVariables.act_reset_type;

            clsSharedVariables.act_reset_type = NORMAL_RESET;

            objReadFiles.write_reset_type();

            clsSharedVariables.no_times_reset = (short) (clsSharedVariables.no_times_reset + 1);

            objReadFiles.write_reset_data();

        }

        private boolean copyFile(String inputPath, String outputPath) {

            InputStream in = null;

            OutputStream out = null;

            byte[] buffer = new byte[1024];

            int read;

            try {
                //create output directory if it doesn't exist
                File outDir = new File(outputPath);

                File inDir = new File(inputPath);

                if (inDir.isDirectory()) {

                } else if (inDir.isFile()) {

                    try {

                        in = new FileInputStream(inDir);

                        if (!outDir.exists()) {

                            outDir.createNewFile();

                        }

                        out = new FileOutputStream(outDir, false);

                        while ((read = in.read(buffer)) != -1) {

                            out.write(buffer, 0, read);
                        }
                    } catch (Exception ex) {

                    } finally {

                        if (out != null) {
                            out.flush();
                            out.close();
                        }
                        if (in != null) {
                            in.close();
                            in = null;
                        }
                        out = null;

                        outDir = null;

                        inDir = null;

                    }

                    return true;

                }

            } catch (Exception e) {

            } finally {

                buffer = null;
            }
            return false;
        }

        private void routefilecheck() {

            try {

                File[] route_files;

                clsReadFiles objReadFiles = new clsReadFiles();
                int l = 0;

                File f = new File("/home/" + clsDefines.obu_board_username + "/ObuIts/src/obuits/");
                if (f.exists()) {
                    runCmd("sudo rm -rf " + f.getAbsolutePath());
                    if (f.exists()) {
                        f.delete();
                    }
                }
                //route_filepath = new File("/media/linaro/3339OBU/Route");
                // main_route_filepath = new File("/home/linaro/Route");
                /* if (Files.exists(Paths.get(clsDefines.QUECTEL_PPP_SCRIPT))) {

                    if (!Files.exists(Paths.get(clsDefines.main_QUECTEL_PPP_SCRIPT))) {

                        copyFile(clsDefines.QUECTEL_PPP_SCRIPT, clsDefines.main_QUECTEL_PPP_SCRIPT);

                        Thread.sleep(1000);

                    }

                }

                if (Files.exists(Paths.get(clsDefines.main_QUECTEL_PPP_SCRIPT))) {

                    if (!Files.exists(Paths.get(clsDefines.QUECTEL_PPP_SCRIPT))) {

                        copyFile(clsDefines.main_QUECTEL_PPP_SCRIPT, clsDefines.QUECTEL_PPP_SCRIPT);

                        Thread.sleep(1000);

                    }

                }

                if (Files.exists(Paths.get(clsDefines.QUECTEL_KILL_PPP_SCRIPT))) {

                    if (!Files.exists(Paths.get(clsDefines.main_QUECTEL_KILL_PPP_SCRIPT))) {

                        copyFile(clsDefines.QUECTEL_KILL_PPP_SCRIPT, clsDefines.main_QUECTEL_KILL_PPP_SCRIPT);

                        Thread.sleep(1000);

                    }

                }

                if (Files.exists(Paths.get(clsDefines.main_QUECTEL_KILL_PPP_SCRIPT))) {

                    if (!Files.exists(Paths.get(clsDefines.QUECTEL_KILL_PPP_SCRIPT))) {

                        copyFile(clsDefines.main_QUECTEL_KILL_PPP_SCRIPT, clsDefines.QUECTEL_KILL_PPP_SCRIPT);

                        Thread.sleep(1000);
                    }
                }*/
                if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {

                    if (!clsDefines.route_filepath.exists() || (route_filepath.listFiles() != null && route_filepath.listFiles().length == 0)) {
                        if (objReadFiles.check_harddisk_mounted() == true && main_route_filepath.exists() && main_route_filepath.listFiles() != null && main_route_filepath.listFiles().length > 0) {
                            route_filepath.mkdirs();
                            runCmd("sudo -R chmod 777 " + route_filepath);
                            route_files = main_route_filepath.listFiles();

                            for (l = 0; route_files != null && l < route_files.length; l++) {
                                copyFile(route_files[l].getAbsolutePath(), route_filepath.getAbsolutePath() + "/" + route_files[l].getName());
                            }
                            route_files = null;
                        }
                    } else {
                        if (!main_route_filepath.exists() || (main_route_filepath.listFiles().length == 0)) {
                            main_route_filepath.mkdirs();

                            runCmd("sudo -R chmod 777 " + main_route_filepath);

                            route_files = route_filepath.listFiles();

                            for (l = 0; route_files != null && l < route_files.length; l++) {

                                copyFile(route_files[l].getAbsolutePath(), main_route_filepath.getAbsoluteFile() + "/" + route_files[l].getName());

                            }

                        }

                    }
                }

            } catch (Exception e) {

            } finally {

            }

        }

        private void deleteExtraVideoFilesPowerOn() {

            try {
                runCmd("sudo rm -rf " + clsDefines.extra_video_filepath);
                File[] files = clsDefines.extra_video_filepath.listFiles();
                for (int i = 0; files != null && i < files.length; i++) {

                    files[i].delete();
                }
            } catch (Exception ex) {

            }
        }

        private void delete_previous_snapshots_event_files() {
            int i;

            File[] l_files_list;
            File[] l_files_list2;
            int j = 0;
            int k = 0;
            File cam_path;
            File[] files_list;
            String str;
            String[] split_str;
            long str_int;
            long now_date_int = Calendar.getInstance().getTimeInMillis() - (getSnapDelNoDays() * 86400000);
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            Date file_date;
            try {

                //Snap shot files
                cam_path = snap_filepath;

                files_list = cam_path.listFiles();
                if (files_list != null) {
                    Arrays.sort(files_list);
                    for (i = 0; i < files_list.length; i++) {
                        if (files_list[i].getName().equals("Cam1") || files_list[i].getName().equals("Cam2")
                                || files_list[i].getName().equals("Cam3") || files_list[i].getName().equals("Cam4")) {

                            l_files_list = files_list[i].listFiles();
                            for (j = 0; l_files_list != null && j < l_files_list.length; j++) {
                                if (l_files_list[j].getName().equals("Cont") || l_files_list[j].getName().equals("Dig1")
                                        || l_files_list[j].getName().equals("Dig2") || l_files_list[j].getName().equals("Dig3")
                                        || l_files_list[j].getName().equals("Dig4")) {
                                    l_files_list2 = l_files_list[j].listFiles();
                                    if (l_files_list2 != null) {
                                        Arrays.sort(l_files_list2);

                                        for (k = 0; k < l_files_list2.length; k++) {
                                            str = l_files_list2[k].getName();
                                            split_str = str.split("_");
                                            if (split_str.length > 1) {
                                                str = split_str[1];
                                            }
                                            // str_int = Long.parseLong(str.substring(1, str.length() - 4));
                                            file_date = sdf.parse(str.substring(1, str.length() - 4));
                                            str_int = file_date.getTime();
                                            if (str_int < now_date_int) {
                                                l_files_list2[k].delete();
                                                //// System.out.println("Snap deleted ");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //video event files
                cam_path = video_event_filepath;
                files_list = cam_path.listFiles();
                if (files_list != null) {
                    Arrays.sort(files_list);
                    for (i = 0; i < files_list.length; i++) {
                        if (files_list[i].getName().equals("Cam1") || files_list[i].getName().equals("Cam2")
                                || files_list[i].getName().equals("Cam3") || files_list[i].getName().equals("Cam4")) {

                            l_files_list = files_list[i].listFiles();
                            for (j = 0; l_files_list != null && j < l_files_list.length; j++) {
                                str = l_files_list[j].getName();
                                split_str = str.split("_");
                                if (split_str.length > 1) {
                                    str = split_str[1];
                                }
                                // str_int = Long.parseLong(str.substring(1, str.length() - 4));
                                file_date = sdf.parse(str.substring(1, str.length() - 4));
                                str_int = file_date.getTime();
                                if (str_int < now_date_int) {
                                    l_files_list[j].delete();
                                    //////////System.out.println("Snap deleted ");
                                }

                            }
                        }
                    }
                }

                //CAN event files
                cam_path = can_video_event_filepath;
                files_list = cam_path.listFiles();
                if (files_list != null) {
                    Arrays.sort(files_list);
                    for (i = 0; i < files_list.length; i++) {
                        if (files_list[i].getName().equals("Cam1") || files_list[i].getName().equals("Cam2")
                                || files_list[i].getName().equals("Cam3") || files_list[i].getName().equals("Cam4")) {

                            l_files_list = files_list[i].listFiles();
                            for (j = 0; l_files_list != null && j < l_files_list.length; j++) {
                                str = l_files_list[j].getName();
                                split_str = str.split("_");
                                if (split_str.length > 1) {
                                    str = split_str[1];
                                }
                                // str_int = Long.parseLong(str.substring(1, str.length() - 4));
                                file_date = sdf.parse(str.substring(1, str.length() - 4));
                                str_int = file_date.getTime();
                                if (str_int < now_date_int) {
                                    l_files_list[j].delete();
                                    //// System.out.println("Snap deleted ");
                                }

                            }
                        }
                    }
                }

            } catch (Exception ex) {

            } finally {

                l_files_list = null;

                cam_path = null;
                files_list = null;
                l_files_list2 = null;
                str = null;
                split_str = null;
                file_date = null;
            }

        }

    }

    private void findPortsDetection() {

        try {

            int len = SerialPortList.getPortNames().length;

            if (len >= 6) {

                if (port_inc_cnt > 0) {

                    port_inc_cnt = 0;

                    clsReadFiles objReadFiles = new clsReadFiles();

                    objReadFiles.write_port_detection_data(port_inc_cnt, port_perm_inc_cnt);

                    objReadFiles = null;

                }

            } else {

                clsReadFiles objReadFil = new clsReadFiles();

                port_inc_cnt++;

                port_perm_inc_cnt++;

                if (port_inc_cnt <= 6) {

                    if (port_perm_inc_cnt > 5000) {

                        port_perm_inc_cnt = 0;

                    }

                    objReadFil.write_port_detection_data(port_inc_cnt, port_perm_inc_cnt);

                    objReadFil.write_log_low_memory_data("Ports are not detected Rebooting System: port length : " + len);

                    reboot_ports_undetect_system();

                    objReadFil.write_log_low_memory_data("Ports are not detected Rebooting System: port length : " + len);

                    reboot_ports_undetect_system();

                    objReadFil = null;

                } else {

                    objReadFil.write_log_low_memory_data("Ports are not detected can't reboot : port length : " + len);

                    objReadFil = null;

                }

            }

        } catch (Exception ex) {

        }

    }

    public static synchronized boolean write_gpio_power_off_gsm(Pin pin_no, PinState state, GpioPinDigitalOutput pin, GpioPinDigitalInput status_pin) {

        try {

            boolean found = false;

            String[] portNames;

            final GpioController gpio = GpioFactory.getInstance();

            pin = gpio.provisionDigitalOutputPin(pin_no, "gpio_state", PinState.LOW);

            try {

                status_pin = gpio.provisionDigitalInputPin(GSM_STATUS_PIN, // PIN NUMBER

                        "gsm_status", // PIN FRIENDLY NAME (optional)

                        PinPullResistance.OFF); // PIN RESISTANCE (optional)    //CHECK FOR gsm STATUS ON /NOT

                portNames = SerialPortList.getPortNames();;

                if (portNames.length <= 3 && status_pin.getState() == GSM_STATUS_OFF_STATE) {

                    gpio.unprovisionPin(pin);

                    gpio.unprovisionPin(status_pin);

                    return true;

                }

                pin.high();

                Thread.sleep(1500);

                pin.low();

                Thread.sleep(5000);

                found = false;

                for (byte i = 0; i < 60; i++) { // 3 sec

                    if (status_pin.getState() == GSM_STATUS_OFF_STATE) {

                        pin.low();

                        found = true;

                        setGsmModuleOn(false);

                        break;

                    }

                    Thread.sleep(10);

                }
                if (found == true) {

                    gpio.unprovisionPin(pin);

                    gpio.unprovisionPin(status_pin);

                    return true;

                }

            } catch (Exception ex) {

            }

            gpio.unprovisionPin(pin);

            gpio.unprovisionPin(status_pin);

        } catch (Exception ex) {

        }

        return false;

    }

    public static synchronized void write_gpio_pin_state_gsmreset(Pin pin_no, PinState state, GpioPinDigitalOutput pin) {

        try {

            final GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(pin_no, "gpio_state", PinState.LOW);

            try {
                Thread.sleep(5);

                pin.high();

                Thread.sleep(600);

                pin.low();

                pin.setShutdownOptions(true, PinState.LOW);

            } catch (Exception ex) {

            }
            gpio.unprovisionPin(pin);

        } catch (Exception ex) {

        }

    }

    public static boolean write_gpio_power_on_gsm(Pin pin_no, PinState state, GpioPinDigitalOutput pin, GpioPinDigitalInput status_pin) {

        try {
            boolean found = false;
            //  String[] portNames;
            final GpioController gpio = GpioFactory.getInstance();
            try {

                pin = gpio.provisionDigitalOutputPin(pin_no, "gpio_power", PinState.LOW);

                status_pin = gpio.provisionDigitalInputPin(GSM_STATUS_PIN, // PIN NUMBER

                        "gsm_status", // PIN FRIENDLY NAME (optional)

                        PinPullResistance.OFF); // PIN RESISTANCE (optional)    //CHECK FOR gsm STATUS ON /NOT

                if (status_pin.getState() == GSM_STATUS_ON_STATE) {
                    //////////System.out.println("GSM ON");
                    setGsmModuleOn(true);
                    return true;
                }

                for (byte j = 0; j < 3; j++) {

                    pin.setState(PinState.HIGH);
                    found = false;
                    for (byte i = 0; i < 30; i++) { // 3 sec

                        Thread.sleep(100);

                        if (status_pin.getState() == GSM_STATUS_ON_STATE) {

                            pin.low();
                            found = true;

                            try {

                                Thread.sleep(2000);

                            } catch (Exception ex) {
                            }

                            setGsmModuleOn(true);
                            return true;

                        }
                    }

                    if (found == true) {
                        return true;
                    }
                    pin.setState(PinState.LOW);
                    write_gpio_pin_state_gsmreset(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);
                }

                for (byte j = 0; j < 50; j++) {

                    if (status_pin.getState() == GSM_STATUS_OFF_STATE) {
                        found = false;

                    } else {

                        return true;

                    }

                    Thread.sleep(100);

                }

                pin.setShutdownOptions(true, PinState.LOW);

            } catch (Exception ex) {

            } finally {
                gpio.unprovisionPin(pin);
                gpio.unprovisionPin(status_pin);
            }
        } catch (Exception ex) {

        }
        return false;
    }

    public static void file_paths_assignment() {

        String harddisk_mounted_path = clsDefines.HARDDISK_PATH;
        usb_filepath = new File("/media/" + clsDefines.obu_board_username);///D://ITS//"); D://ITS//"); // = Environment.getExternalStorageDirectory() ;
        obu_filepath = new File("/home/" + clsDefines.obu_board_username + "/ObuIts/dist");///h/ome/linaro/media/");//D://ITS//"); D://ITS//"); // = Environment.getExternalStorageDirectory() ;
        clsDefines.apc_filepath = new File("/home/" + clsDefines.obu_board_username + "/Documents/");
        clsDefines.obu_library_filepath = new File("/home/" + clsDefines.obu_board_username + "/ObuIts/dist/lib");
        clsDefines.media_path = ("/media/" + clsDefines.obu_board_username);
        clsDefines.HARDDISK_PATH = harddisk_mounted_path; //"/mnt/Recorder";
        clsDefines.GSTREAMER_KILL_SCRIPT = ("/home/" + clsDefines.obu_board_username + "/close_gstreamer.sh");
        clsDefines.GSTREAMER_TOP_SCRIPT = ("/home/" + clsDefines.obu_board_username + "/gstreamer_top.sh");
        clsDefines.media_filepath = new File(harddisk_mounted_path);//E:/linaro/3339OBU");///media/linaro/3339OBU");//E:/linaro/3339OBU/");///media/linaro/3339OBU");//media/linaro/3339OBU");/////media/linaro/3339OBU");//E:/linaro/3339OBU/");///media/linaro/3339OBU");// /media/linaro/3339OBU"); // "E:/linaro/3339OBU/");// "media/linaro/3339OBU"); // E:/linaro/3339OBU/"); ///media/linaro/3339OBU");//"/
        clsDefines.route_filepath = new File(harddisk_mounted_path + "/Route");///media/linaro/3339OBU/Route  ///home/sumith/Documents/Olectra_Demo_Routes/routes  //E:/linaro/3339OBU/Route");///media/linaro/3339OBU/Route");//E:/linaro/3339OBU/Route");///media/linaro/3339OBU/Route");//"E:/linaro/3339OBU/Route");// /media/linaro/3339OBU/Route");///E:/linaro/3339OBU/Route");// /media/linaro/3339OBU/Route");///
        clsDefines.video_filepath = new File(harddisk_mounted_path + "/Videos"); ///home/linaro/Desktop/Videos") ; // /media/linaro/3339OBU/Videos"); //D://ITS//Videos/"); ///home/linaro/Desktop/Videos"); //
        clsDefines.can_filepath = new File(harddisk_mounted_path + "/Log/CAN");//"E://CAN//"
        clsDefines.log_filepath = new File(harddisk_mounted_path + "/Log");
        clsDefines.snap_filepath = new File(harddisk_mounted_path + "/Videos/Snapshot");
        clsDefines.video_event_filepath = new File(harddisk_mounted_path + "/Videos/Videos_event");
        clsDefines.can_video_event_filepath = new File(harddisk_mounted_path + "/Videos/Videos_can");
        clsDefines.motion_video_event_filepath = new File(harddisk_mounted_path + "/Videos/Videos_motion");
        clsDefines.extra_video_filepath = new File(harddisk_mounted_path + "/Videos/Extra");
        clsDefines.old_video_path = new File("/home/" + clsDefines.obu_board_username + "/Desktop/Videos");
        clsDefines.config_filepath = new File("/home/" + clsDefines.obu_board_username + "/");
        clsDefines.main_filepath = new File("/home/" + clsDefines.obu_board_username);
        clsDefines.main_route_path = new File(clsDefines.ROUTEFS_PATH); //("/home/linaro/Route");
        clsDefines.main_route_filepath = new File(clsDefines.ROUTEFS_PATH + "/Route"); //("/home/linaro/Route");
        clsDefines.main_video_filepath = new File(clsDefines.ROUTEFS_PATH + "/Video"); //("/home/linaro/Route");
        clsDefines.main_can_filepath = new File(clsDefines.ROUTEFS_PATH + "/Log/CAN");
        clsDefines.QUECTEL_PPP_SCRIPT = "/home/" + clsDefines.obu_board_username + "/quectel-pppd.sh";
        clsDefines.QUECTEL_KILL_PPP_SCRIPT = "/home/" + clsDefines.obu_board_username + "/quectel-ppp-kill";
    }

    private void gpio_pin_assignment() {
        if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
            GSM_POWER_ONOFF = RaspiPin.GPIO_05;
            GSM_RESET = RaspiPin.GPIO_06; //old board : RaspiPin.GPIO_25;
            GSM_STATUS_PIN = RaspiPin.GPIO_04; //old board : RaspiPin.GPIO_07;
            IGNITION_STATUS = RaspiPin.GPIO_00; //same for both boards
            MAINS_STATUS = RaspiPin.GPIO_02;//
            SOS_INPUT = RaspiPin.GPIO_03;
            RS485_ONOFF = RaspiPin.GPIO_01; //same for both boards
            DIG2_INPUT = RaspiPin.GPIO_22;
            DIG3_INPUT = RaspiPin.GPIO_27;
            DIG4_INPUT = RaspiPin.GPIO_13;
            AUDIO_VOICECALL_COMM_ONOFF = RaspiPin.GPIO_24; //audion control 2 same for both boards Audio_CTRL_2
            TAMPER_DIG_INPUT = RaspiPin.GPIO_28; //NEW
            FM_RADIO_PIN_ONOFF = RaspiPin.GPIO_12;
            AUDIO_SPEAKER_COMM_ONOFF = RaspiPin.GPIO_25; //audio control 1  new card RaspiPin.GPIO_23; //   //old board : RaspiPin.GPIO_28;  AUDIO_CTRL1
            //MIC_CONTROL_CALL_SPEAKER = RaspiPin.GPIO_28;//new card RaspiPin.GPIO_25;//// old board : RaspiPin.GPIO_27;
            MIC_STATUS = RaspiPin.GPIO_29; //same for both boards pin 40 mic status
            POWER_ONOFF = RaspiPin.GPIO_11; ////old board :  RaspiPin.GPIO_08;
            LCD_RESET = RaspiPin.GPIO_10;
            FM_ON_STATE = PinState.LOW; // PinState.LOW;
            FM_OFF_STATE = PinState.HIGH;// PinState.HIGH;
        } else if (OBU_BRD_TYPE == TINKER_BOARD) {
            GSM_POWER_ONOFF = RaspiPin.GPIO_05; //same for both boards
            GSM_RESET = RaspiPin.GPIO_06; //old board : RaspiPin.GPIO_25;
            GSM_STATUS_PIN = RaspiPin.GPIO_04; //old board : RaspiPin.GPIO_07;
            IGNITION_STATUS = RaspiPin.GPIO_00; //same for both boards
            MAINS_STATUS = RaspiPin.GPIO_07;//
            SOS_INPUT = RaspiPin.GPIO_30;
            RS485_ONOFF = RaspiPin.GPIO_01; //same for both boards
            DIG2_INPUT = RaspiPin.GPIO_22;
            DIG3_INPUT = RaspiPin.GPIO_31;
            DIG4_INPUT = RaspiPin.GPIO_13;
            AUDIO_VOICECALL_COMM_ONOFF = RaspiPin.GPIO_24; //audio control 2 same for both boards Audio_CTRL_2
            TAMPER_DIG_INPUT = RaspiPin.GPIO_28; //NEW
            FM_RADIO_PIN_ONOFF = RaspiPin.GPIO_12;
            AUDIO_SPEAKER_COMM_ONOFF = RaspiPin.GPIO_21; //audio control 1 new card RaspiPin.GPIO_23; //   //old board : RaspiPin.GPIO_28;  AUDIO_CTRL1
            MIC_STATUS = RaspiPin.GPIO_29; //RaspiPin.GPIO_29; //same for both boards pin 40 mic status
            POWER_ONOFF = RaspiPin.GPIO_11; ////old board :  RaspiPin.GPIO_08;
            LCD_RESET = RaspiPin.GPIO_10;
            FM_ON_STATE = PinState.HIGH; // PinState.LOW;
            FM_OFF_STATE = PinState.LOW;// PinState.HIGH;

        }
    }

    public class GPIOWriteData extends Thread {

        @Override
        public void run() {
            try {
                WriteGpioOutput();
            } catch (Exception ex) {
            }
            try {
                read_ignition_state();
                read_mains_state();
                read_mic_state();
                read_sos_state();
                read_tamper_state();
                read_diginput2_state();
                read_diginput3_state();
                read_diginput4_state();
                if (write_gpio_power_on_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmResetPin, digInGsmStatusGpio) == false) {
                    if (write_gpio_power_on_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmResetPin, digInGsmStatusGpio) == false) {
                        write_gpio_pin_state_gsmreset(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);
                        write_gpio_power_on_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmResetPin, digInGsmStatusGpio);
                    }
                }
            } catch (Exception e) {
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }
            read_gsm_status_state();
            load_top_images();
            if (clsSharedVariables.reset_type != MANUAL_RESET) {
                if (clsSharedVariables.getIpAddr1Enable()) {
                    obj16833Pkts.obu_pid_pkt();
                    obj16833Pkts.obu_dtc_pkt();
                    obj16833Pkts.usb_dtc_pkt();
                    obj16833Pkts.gps_dtc_pkt();
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH
                            || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX
                            || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_DIMTS_NAGPUR) {

                        objDriving.obu_pid_pkt();
                        objDriving.obu_dtc_pkt();
                        objDriving.usb_dtc_pkt();
                        objDriving.gps_dtc_pkt();
                    }
                }
            }
        }

        private void WriteGpioOutput() {
            try {
                setGsmModuleOn(false);
                //   write_gpio_pin_state(DEVICE_RESET, DEVICE_RESET_OFF_STATE, digOutDeviceResetPin);
                //    write_gpio_pin_state(USB_HUB_RESET, USBHUB_ON_STATE, hubResetPin);
                //write_gpio_pin_state(LCD_RESET, LCD_RESET_ON_STATE, digOutLcdResetPin); //low reset signal
                //      ldc_off();
                //   Thread.sleep(2000);
                write_gpio_pin_state(LCD_RESET, LCD_RESET_ON_STATE, digOutLcdResetPin); //low reset signal
                write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin); //low reset signal
                write_gpio_pin_state(GSM_POWER_ONOFF, GSM_POWER_OFF_STATE, digOutGsmPowerPin); //Power signal  reset signal low
                write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
                write_gpio_pin_state(RS485_ONOFF, RS485_SIGNAL_OFF_STATE, digOutRs485Pin);
                write_gpio_pin_state(AUDIO_SPEAKER_COMM_ONOFF, AUDIO_SIGNAL_SPEAKER_OFF_STATE, digOutAudioSpeakerPin);
                write_gpio_pin_state(FM_RADIO_PIN_ONOFF, FM_OFF_STATE, digOutFMPin);

                write_gpio_pin_state(POWER_ONOFF, POWER_ON_STATE, digOutPowerPin);
                // write_gpio_pin_state(MIC_CONTROL_CALL_SPEAKER, MIC_CONTROL_SPEAKER_STATE, digOutMicPin);
                write_gpio_pin_state(LCD_RESET, LCD_RESET_ON_STATE, digOutLcdResetPin); //low reset signal

                //("LCD Pin Read");
                //if (read_gpio_pin_state(LCD_RESET, digOutLcdResetPin) == false) {
                // read_gpio_pin_state(LCD_RESET, digOutLcdResetPin);
                // }
            } catch (Exception ex) {

            }
        }
    }

    public synchronized static void restart_module_ports_changed(boolean isGps) {

        if (isGps == false) {

            if (!"0.0.0.0".equals(clsSharedVariables.getIpAddr1())) {

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_log_gprs_connectivity("ppp: Restarting Module GSM ");

                objReadFiles = null;

                if (restart_module_on == false) {
                    restart_module_on = true;
                    try {
                        setGsmModuleOn(false);
                        if (!clsSharedVariables.getSeparateGPS()) {
                            close_gps_serial_port();
                        }
                        close_at_serial_port();
                        write_gpio_power_off_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmPowerPin, digInGsmStatusGpio);

                        write_gpio_pin_state_gsmreset(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);

                        if (write_gpio_power_on_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmResetPin, digInGsmStatusGpio) == false) {

                            write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_OFF_STATE, digOutGsmResetPin);

                            try {
                                Thread.sleep(1000);

                            } catch (InterruptedException ex) {
                            }

                            write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);
                        }
                    } catch (Exception ex) {
                    }
                    restart_module_on = false;
                }
            }
        } else if (isGps == true) {

            clsReadFiles objReadFiles = new clsReadFiles();

            objReadFiles.write_log_gprs_connectivity("ppp: Restarting Module GPS ");

            objReadFiles = null;

            if (restart_module_on == false) {

                restart_module_on = true;

                try {

                    setGsmModuleOn(false);

                    if (!clsSharedVariables.getSeparateGPS()) {
                        close_gps_serial_port();
                    }
                    close_at_serial_port();
                    write_gpio_power_off_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmPowerPin, digInGsmStatusGpio);

                    write_gpio_pin_state_gsmreset(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);

                    if (write_gpio_power_on_gsm(GSM_POWER_ONOFF, GSM_POWER_ON_STATE, digOutGsmResetPin, digInGsmStatusGpio) == false) {
                        write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_OFF_STATE, digOutGsmResetPin);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {

                        }
                        write_gpio_pin_state(GSM_RESET, GSM_RESET_PIN_ON_STATE, digOutGsmResetPin);
                    }

                } catch (Exception ex) {
                }
                restart_module_on = false;
            }

        }

    }

    public void shutdown_system() {
        RESET_OBU_CALLED = true;
        setRebootSystemEnabled(true);
        reset_obu();
        try {
            Process proc;
            harddisk_unmount();
            show_message_dialogbox("Shutdown Mode Enabled");
            write_gpio_pin_state(POWER_ONOFF, POWER_OFF_STATE, digOutPowerOffPin);
            write_gpio_pin_state(POWER_ONOFF, POWER_OFF_STATE, digOutPowerOffPin);
            proc = Runtime.getRuntime().exec("sudo halt");
            proc.waitFor();
            proc = null;
        } catch (Exception e) {

        }
    }

    public static void reboot_permission_tinker_board() {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(new String[]{"bash", "-c", "sudo chmod 777 /proc/sysrq-trigger"});
            process.waitFor();

        } catch (IOException e) {
            if (rt != null) {
                rt.gc();
            }

        } catch (Exception ex) {
            if (rt != null) {
                rt.gc();
            }

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

    public static void reboot_tinker_board(String str) {
        Process process = null;
        Runtime rt = null;
        try {
            clsReadFiles obj = new clsReadFiles();
            obj.write_data_imp1_log("Reboot from tinker: " + str);
            reboot_permission_tinker_board();
            rt = Runtime.getRuntime();
            process = rt.exec(new String[]{"bash", "-c", "sudo echo b > /proc/sysrq-trigger\n"});
            process.waitFor();
            process = rt.exec(new String[]{"bash", "-c", "echo b > /proc/sysrq-trigger"});
            process.waitFor();
            process = rt.exec("sudo echo b > /proc/sysrq-trigger");
            process.waitFor();

        } catch (IOException e) {
            if (rt != null) {
                rt.gc();
            }
        } catch (Exception ex) {
            if (rt != null) {
                rt.gc();
            }
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

    public static void reboot_instant() {
        RESET_OBU_CALLED = true;
        clsSharedVariables.act_reset_type = MANUAL_RESET;
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_reset_type();
        setRebootSystemEnabled(true);
        try {
            if (clsSharedVariables.getHardDriveDetected()) {
                harddisk_unmount();
            }
            try {
                objReadFiles.write_activity_name(getCurActivity());
            } catch (Exception ex) {
            }
            try {
                if (objStateMachine != null) {
                    objStateMachine.interrupt();
                    objStateMachine.stop();
                    objStateMachine = null;
                }
            } catch (Exception ex) {
            }
            try {
                close_gps_serial_port();
                close_at_serial_port();
//                close_can_serialport();
//                close_can_serialport1();
                close_displayboard_serialport();
                clsGprsService.close_gprs_service();

                if (gsm_status_timer != null) {
                    gsm_status_timer.cancel();
                    gsm_status_timer_task.cancel();
                    gsm_status_timer = null;
                    gsm_status_timer_task = null;
                }

                if (sleep_mode_timer != null) {
                    sleep_mode_timer.cancel();
                    sleep_mode_timer_task.cancel();
                    sleep_mode_timer = null;
                    sleep_mode_timer_task = null;
                }
              //  close_rs232_serialport();

            } catch (Exception ex) {

            }
            Thread.sleep(5000);
            Process proc;

            if (OBU_BRD_TYPE == TINKER_BOARD) {
                reboot_tinker_board(" Instant freespace <2");
            } else {
                proc = Runtime.getRuntime().exec("sudo systemctl start reboot.target");  //sudo reboot
                proc.waitFor();
                proc = null;
            }
        } catch (InterruptedException e) {

            try {
                Runtime.getRuntime().exec("sudo reboot");   //sudo reboot
                Runtime.getRuntime().exec("sudo systemctl start reboot.target");   //sudo reboot

            } catch (IOException ex) {
            }
        } catch (IOException e) {
            try {
                Runtime.getRuntime().exec("sudo reboot");   //sudo reboot
                Runtime.getRuntime().exec("sudo systemctl start reboot.target");   //sudo reboot

            } catch (IOException ex) {

            }
        }
    }

    public static void reboot_system(String str) {
        try {
            RESET_OBU_CALLED = true;
            clsSharedVariables.act_reset_type = MANUAL_RESET;
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_data_imp_log(str + "reboot");
            objReadFiles.write_reset_type();
            byte reset_times;
            reset_times = objReadFiles.read_reset_data_today();
            reset_times++;
            objReadFiles.write_reset_data_today(reset_times);
            setRebootSystemEnabled(true);
            try {
                reset_obu();
                try {
                    if (clsSharedVariables.getHardDriveDetected()) {
                        harddisk_unmount();
                    }

                    Thread.sleep(3000);
                    Process proc;
                    proc = Runtime.getRuntime().exec("sudo systemctl start reboot.target");
                    proc.waitFor();
                    proc = null;

                } catch (InterruptedException e) {

                    try {

                        Runtime.getRuntime().exec("sudo systemctl start reboot.target");   //sudo reboot

                    } catch (IOException ex) {
                    }

                    e.printStackTrace();

                } catch (IOException e) {

                    try {

                        Runtime.getRuntime().exec("sudo systemctl start reboot.target");   //sudo reboot

                    } catch (IOException ex) {
                    }

                    e.printStackTrace();

                }

            } catch (Exception e) {
            }
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                reboot_tinker_board(str);
            } else {
                Process proc = Runtime.getRuntime().exec("sudo reboot\r\n");

                proc.waitFor();

                proc = null;

            }

        } catch (IOException ex) {

        } catch (InterruptedException ex) {
        }
    }

    public static void reboot_ports_undetect_system() {
        RESET_OBU_CALLED = true;
        clsSharedVariables.act_reset_type = MANUAL_RESET;

        clsReadFiles objReadFiles = new clsReadFiles();

        objReadFiles.write_reset_type();

        try {

            Process proc;
            harddisk_unmount();
            Thread.sleep(3000);
            if (OBU_BRD_TYPE == TINKER_BOARD) {
                reboot_tinker_board("PORTs not detected");
            } else {
                proc = Runtime.getRuntime().exec("sudo reboot\r\n");

                proc.waitFor();
            }
            proc = null;

        } catch (InterruptedException e) {

            try {
                Runtime.getRuntime().exec("sudo reboot\r\n");
            } catch (IOException ex) {
            }

        } catch (IOException e) {
            try {
                Runtime.getRuntime().exec("sudo reboot\r\n ");

            } catch (IOException ex) {
            }

        } catch (Exception e) {
            try {
                Runtime.getRuntime().exec("sudo reboot\r\n ");

            } catch (IOException ex) {
            }

        }

    }

    public synchronized static void reset_obu() {

        try {

            clsReadFiles obj = new clsReadFiles();

            obj.write_all_stored_non_gps_pkts();
            obj.write_all_stored_non_gps_pkts4();
            setResetObuVal(RESET_OBU_CONFIRMED);

            for (int i = 0; i < 50; i++) {

                if (getResetObuVal() == RESET_OBU_FINISHED) {

                    break;

                }

                Thread.sleep(100);

            }

            try {

                obj.write_activity_name(getCurActivity());

            } catch (Exception ex) {

            }

            obj = null;

        } catch (Exception ex) {

        }

        try {

            if (objStateMachine != null) {
                objStateMachine.interrupt();
                objStateMachine.stop();
                objStateMachine = null;
            }

        } catch (Exception ex) {

        }

        try {
            close_gps_serial_port();

            close_at_serial_port();

//            close_can_serialport();
//            close_can_serialport1();

            close_displayboard_serialport();

            clsGprsService.close_gprs_service();
            if (gsm_status_timer != null) {
                gsm_status_timer.cancel();
                gsm_status_timer_task.cancel();
                gsm_status_timer = null;
                gsm_status_timer_task = null;
            }

            if (sleep_mode_timer != null) {
                sleep_mode_timer.cancel();
                sleep_mode_timer_task.cancel();
                sleep_mode_timer = null;
                sleep_mode_timer_task = null;
            }

        } catch (Exception ex) {

        }

    }

    public static synchronized void refresh_watchdog() {

        File f = new File("/dev/watchdog");

        if (f.exists()) {

            try {

                FileOutputStream fos = new FileOutputStream(f);

                PrintWriter pw = new PrintWriter(fos);

                pw.write("w");

                pw.flush();
                pw.close();

                fos.close();

                pw = null;

                fos = null;

            } catch (IOException e) {
            } catch (Exception e) {
            }

        }
        f = null;

    }

    private class clsWatchDogThread extends Thread {

        clsWatchdogResetVariables objwatDogRead = new clsWatchdogResetVariables();

        final int MAX_TIME = 1000;

        @Override

        public void run() {

            int gps_watchdog_var = 0;
            int apc_watchdog_var = 0;
            int apc_watchdog_var1 = 0;
            byte max_reset_count = 0;
            int gps_serial_watchdog_var = 0;
            int gprmc_watchdog_var = 0;

            int at_watchdog_var = 0;

            int at_serial_watchdog_var = 0;

            int can_serial_watchdog_var = 0;

            int can_serial_watchdog_var1 = 0;

            int can_queue_watchdog_var = 0;

            int can_queue_watchdog_var1 = 0;

            int bus_stopdet_watchdog_var = 0;

            int disbrd_watchdog_var = 0;

            int disbrdSerial_watchdog_var = 0;

            int sch_watchdog_var = 0;

            int val;

            int no_times_reset;

            final byte RESET_VALUE = 10;

            clsReadFiles objReadFiles = new clsReadFiles();

            clsReadFiles obj = new clsReadFiles();

            no_times_reset = obj.read_reset_data_today();

            max_reset_count = obj.read_max_reset_data_today();
            while (true) {

                try {
                    {
                        if (REFRESH_WATCHDOG_TIMER_ENABLED) {
                            refresh_watchdog();
                        }
                        objReadFiles.write_log_tick_data();

                        val = objwatDogRead.getapc_watchdog_val();
                        if (val != 0) {
                            if (val == RESET_VALUE) {
                                apc_watchdog_var++;
                                if (apc_watchdog_var > 20) {
                                    try {
//                                        objPeopleCnt.interrupt();
//                                        objPeopleCnt.stop();
//                                        objPeopleCnt = null;
                                    } catch (Exception ex) {

                                    }
                                    if (clsSharedVariables.getApcEnabled() == true) {
                                        if (clsSharedVariables.getCam1Type() == clsDefines.CAM_DAHUA) {
                                            lblPeopleIn1.setVisible(true);
                                            lblPeopleIn2.setVisible(true);
                                            lblPeopleOut1.setVisible(true);
                                            lblPeopleOut2.setVisible(true);
//                                            objPeopleCnt = new clsPeoplCountEvent();
//                                            objPeopleCnt.start();
                                        }
                                    }

                                }

                            } else {

                                objwatDogRead.setapc_watchdog_val(RESET_VALUE);

                                apc_watchdog_var = 0;

                            }

                        }
                        val = objwatDogRead.getapc_watchdog_val1();
                        if (val != 0) {
                            if (val == RESET_VALUE) {
                                apc_watchdog_var1++;
                                if (apc_watchdog_var1 > 20) {
                                    try {
//                                        objPeopleCnt1.interrupt();
//                                        objPeopleCnt1.stop();
//                                        objPeopleCnt1 = null;
                                    } catch (Exception ex) {

                                    }
                                    if (clsSharedVariables.getApcEnabled1() == true) {
                                        if (clsSharedVariables.getCam1Type() == clsDefines.CAM_DAHUA) {
                                            lblPeopleIn1.setVisible(true);
                                            lblPeopleIn2.setVisible(true);
                                            lblPeopleOut1.setVisible(true);
                                            lblPeopleOut2.setVisible(true);
//                                            objPeopleCnt1 = new clsPeopleCountEventSecondCamera();
//                                            objPeopleCnt1.start();
                                        }
                                    }

                                }

                            } else {

                                objwatDogRead.setapc_watchdog_val1(RESET_VALUE);

                                apc_watchdog_var1 = 0;

                            }

                        }

                        val = objwatDogRead.getgps_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                gps_watchdog_var++;

                                if (gps_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in GPS onNmeaReceived ");

                                    if (gps_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {
                                        max_reset_count++;
                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        objReadFiles.write_data_imp_log("Hang in GPS NMEA rebooting System ");
                                        reboot_system("GPS hang");

                                    }

                                }

                            } else {

                                objwatDogRead.setgps_watchdog_val(RESET_VALUE);

                                gps_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getgpsSerial_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                gps_serial_watchdog_var++;

                                if (gps_serial_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    if (gps_serial_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        objReadFiles.write_data_imp_log("HANG in GPS SERIAL PORT rebooting system");
                                        reboot_system("HANG in GPS SERIAL PORT");

                                    }

                                }

                            } else {

                                objwatDogRead.setgpsSerial_watchdog_val(RESET_VALUE);

                                gps_serial_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getgprmc_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                gprmc_watchdog_var++;

                                if (gprmc_watchdog_var > MAX_TIME) {
                                    //reboot the system hang in the gps
                                    if (gprmc_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        objReadFiles.write_data_imp_log("HANG in GPRMC Thread rebooting system");
                                        reboot_system("HANG in GPRMC Thread");
                                    }

                                }

                            } else {

                                objwatDogRead.setgprmc_watchdog_val(RESET_VALUE);

                                gprmc_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getAtSerial_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                at_serial_watchdog_var++;

                                if (at_serial_watchdog_var > MAX_TIME * 2) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in AT Serial Port Thread ");

                                    if (at_serial_watchdog_var > MAX_TIME * 2 + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);

                                        reboot_system("HANG in AT Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setAtSerial_watchdog_val(RESET_VALUE);

                                at_serial_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getAt_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                at_watchdog_var++;

                                if (at_watchdog_var > MAX_TIME * 2) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in AT Thread ");

                                    if (at_watchdog_var > MAX_TIME * 2 + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in AT2 Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setAt_watchdog_val(RESET_VALUE);

                                at_watchdog_var = 0;

                            }

                        }
                        val = objwatDogRead.getCanSerial_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                can_serial_watchdog_var++;

                                if (can_serial_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in CAN  SERIAL Thread ");

                                    if (can_serial_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in CAN Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setCanSerial_watchdog_val(RESET_VALUE);

                                can_serial_watchdog_var = 0;

                            }

                        }
                        //can2
                        val = objwatDogRead.getCanSerial_watchdog_val1();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                can_serial_watchdog_var1++;

                                if (can_serial_watchdog_var1 > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in CAN2  SERIAL Thread ");

                                    if (can_serial_watchdog_var1 > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in CAN2 Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setCanSerial_watchdog_val1(RESET_VALUE);

                                can_serial_watchdog_var1 = 0;

                            }

                        }
                        val = objwatDogRead.getCanQueue_watchdog_val1();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                can_queue_watchdog_var1++;

                                if (can_queue_watchdog_var1 > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in CAN2 QUEUE Thread ");

                                    if (can_queue_watchdog_var1 > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in CAN2 Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setCanQueue_watchdog_val1(RESET_VALUE);

                                can_queue_watchdog_var1 = 0;

                            }

                        }
                        val = objwatDogRead.getCanQueue_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                can_queue_watchdog_var++;
                                objReadFiles.write_data_imp_log("HANG in CAN QUEUE Thread open ");

                                if (can_queue_watchdog_var > MAX_TIME) {// max time 1000

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in CAN QUEUE Thread ");

                                    if (can_queue_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);

                                        reboot_system("HANG in CAN2 Thread");

                                    }

                                }

                            } else {
                                objwatDogRead.setCanQueue_watchdog_val(RESET_VALUE);
                                can_queue_watchdog_var = 0;
                            }
                        }
                        val = objwatDogRead.getBusStopDet_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                bus_stopdet_watchdog_var++;

                                if (bus_stopdet_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in BUS STOP DET Thread ");

                                    if (bus_stopdet_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in BUS STOP Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setBusStopDet_watchdog_val(RESET_VALUE);

                                bus_stopdet_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getDisbrd_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                disbrd_watchdog_var++;

                                if (disbrd_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in DIS BRD Thread ");

                                    if (disbrd_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in DISBRD Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setDisbrd_watchdog_val(RESET_VALUE);

                                disbrd_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getDisbrdSerial_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                disbrdSerial_watchdog_var++;

                                if (disbrdSerial_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in DIS BRD SERIAL Thread ");

                                    if (disbrdSerial_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);
                                        reboot_system("HANG in DISBRD2 Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setDisbrdSerial_watchdog_val(RESET_VALUE);

                                disbrdSerial_watchdog_var = 0;

                            }

                        }

                        val = objwatDogRead.getSch_watchdog_val();

                        if (val != 0) {

                            if (val == RESET_VALUE) {

                                sch_watchdog_var++;

                                if (sch_watchdog_var > MAX_TIME) {

                                    //reboot the system hang in the gps
                                    objReadFiles.write_data_imp_log("HANG in SCHEDULE Thread ");

                                    if (sch_watchdog_var > MAX_TIME + 5 && max_reset_count < 5) {

                                        max_reset_count++;

                                        objReadFiles.write_max_reset_data_today(max_reset_count);

                                        reboot_system("HANG in SCH Thread");

                                    }

                                }

                            } else {

                                objwatDogRead.setSch_watchdog_val(RESET_VALUE);

                                sch_watchdog_var = 0;

                            }

                        }

                    }

                } catch (Exception ex) {

                }

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                }

            }

        }

    }
    
    private static boolean prev_file_del_status = false;

    private class ClsVideoDeleteFiles extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    try {
                        delete_previous_video_files();
                    } catch (Exception ex) {
                    }
                    try {

                        delete_previous_can_files();

                    } catch (Exception ex) {
                    }
                    try {
                        delete_preevent_video_files();
                    } catch (Exception ex) {

                    }

                    try {
                        delete_extra_files();

                    } catch (Exception ex) {

                    }
                    TimeUnit.MINUTES.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrmIts.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        }

        private void delete_previous_video_files() {
            int i = 0;
            double size;
            double totalSize;
            File[] files = null;
            File[] cam_files = null;
            File f;
            File[] l_files_list;
            File[] l_files_list2;
            int j = 0;
            int l = 0;
            File cam_path;
            File[] files_list;
            double video_size;
            String str;
            Date dat;
            Date now_date;
            long difference = 0;
            float daysBetween;
            boolean del_file = false;
            byte cam_inc = 0;
            int k = 0;
            int h = 0;
            int m = 0;
            int n = 0;

            String[] arr;
            String[] split_str;
            String str1;
            int expiry_days = clsSharedVariables.getRecExpiryDays();
            int expiry_days_event = clsSharedVariables.getRecExpiryDaysEventBased();
            SimpleDateFormat sdfOutFileFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            double extra_all_files_size = extra_files_size();

            try {

                //if sd card full
                f = new File(media_path);
                // System.out.println("media_path   " + media_path);
                size = f.getFreeSpace() / ONE_GB;
                // System.out.println("Size   " + size + "a: " + a);
                if (size < 3 && prev_file_del_status == false) {
                    prev_file_del_status = true;
                    clsReadFiles objReadFiles = new clsReadFiles();
                    objReadFiles.write_data_imp_log("Size < 3");
                    harddisk_unmount();
                    if (objReadFiles.check_harddisk_mount_state() == false) {
                        objReadFiles.write_data_imp_log("unmounted...");
                        f = media_filepath;
                        files_list = f.listFiles();
                        if (files_list.length > 0) {
                            for (j = 0; j < files_list.length; j++) {
                                if (files_list[j].isDirectory()) {
                                    {
                                        // System.out.println("files_list[j].getAbsolutePath() " + files_list[j].getAbsolutePath());
                                        objReadFiles.write_data_imp_log("removing   Files" + files_list[j].getAbsolutePath());
                                        runCmd("sudo rm -rf " + files_list[j].getAbsolutePath());
                                    }
                                }
                            }
                        }
                    }
                    // mount harddisk
                    objReadFiles.harddisk_power_on(true);
                    objReadFiles.harddisk_mount();
                    objReadFiles = null;
                }

                //video files
                f = media_filepath;
                size = FileUtils.sizeOfDirectory(f);
                totalSize = size / ONE_GB;
                clsDefines.ACTUAL_SIZE_HARDDISK = f.getTotalSpace() / ONE_GB;
                clsDefines.TOTAL_SIZE_HARDDISK = (f.getTotalSpace() / ONE_GB) - 10;
                if (f.getTotalSpace() / ONE_GB > 850) {
                    clsDefines.TOTAL_SIZE_HARDDISK = ((f.getTotalSpace() / ONE_GB) - extra_all_files_size) - 30; //850;
                } else {
                    clsDefines.TOTAL_SIZE_HARDDISK = ((f.getTotalSpace() / ONE_GB) - extra_all_files_size) - 30;
                }
                if (clsSharedVariables.getCam1Enabled()) {
                    cam_inc++;
                }
                if (clsSharedVariables.getCam2Enabled()) {
                    cam_inc++;
                }
                if (clsSharedVariables.getCam3Enabled()) {
                    cam_inc++;
                }
                if (clsSharedVariables.getCam4Enabled()) {
                    cam_inc++;
                }

                if (clsSharedVariables.getCam5Enabled()) {
                    cam_inc++;
                }
                if (clsSharedVariables.getCam6Enabled()) {
                    cam_inc++;
                }
                if (clsSharedVariables.getCam7Enabled()) {
                    cam_inc++;
                }
                if (clsSharedVariables.getCam8Enabled()) {
                    cam_inc++;
                }

                if (cam_inc > 0) {
                    video_size = clsDefines.TOTAL_SIZE_HARDDISK / cam_inc;
                } else {
                    video_size = clsDefines.TOTAL_SIZE_HARDDISK;
                }
                cam_path = clsDefines.video_filepath;

                files_list = cam_path.listFiles();
                if (files_list != null) {
                    Arrays.sort(files_list);

                    //getRecExpiryDays
                    now_date = Calendar.getInstance().getTime();
                    for (i = 0; i < files_list.length; i++) {
                        del_file = false;
                        if (files_list[i].getName().equals("Cam1") || files_list[i].getName().equals("Cam2")
                                || files_list[i].getName().equals("Cam3") || files_list[i].getName().equals("Cam4")
                                || files_list[i].getName().equals("Cam5") || files_list[i].getName().equals("Cam6")
                                || files_list[i].getName().equals("Cam7") || files_list[i].getName().equals("Cam8")) {

                            size = FileUtils.sizeOfDirectory(files_list[i]) / ONE_GB;
                            l_files_list = files_list[i].listFiles();
                            Arrays.sort(l_files_list);
                            if (size > video_size) {
                                l_files_list = files_list[i].listFiles();
                                arr = new String[l_files_list.length];
                                h = 0;
                                for (k = 0; k < l_files_list.length; k++) {
                                    str1 = l_files_list[k].getName();
                                    split_str = str1.split("_");
                                    if (split_str.length > 1) {
                                        arr[h] = split_str[1];
                                        h++;
                                    }
                                }
                                Arrays.sort(arr);
                                for (m = 0; m < arr.length; m++) {
                                    if (FileUtils.sizeOfDirectory(files_list[i]) / ONE_GB > video_size) {
                                        for (n = 0; n < l_files_list.length; n++) {
                                            if (l_files_list[n].getName().contains(arr[m])) {
                                                l_files_list[n].delete();
                                                break;
                                            }
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                try {
                                    if (del_file == false) {
                                        for (j = 0; j < l_files_list.length; j++) {
                                            str = l_files_list[j].getName().substring(8, 22);
                                            dat = sdfOutFileFormat.parse(str);
                                            difference = now_date.getTime() - dat.getTime();
                                            daysBetween = (difference / (1000 * 60 * 60 * 24));
                                            if (daysBetween >= expiry_days) {
                                                l_files_list[j].delete();

                                            }
                                        }
                                    }
                                } catch (Exception e) {

                                }

                            }
                        } else if (files_list[i].getName().equals("Videos_event") || files_list[i].getName().equals("Videos_can")) {
                            size = FileUtils.sizeOfDirectory(files_list[i]) / ONE_GB;
                            l_files_list = files_list[i].listFiles();
                            if (l_files_list != null) {
                                Arrays.sort(l_files_list);
                                del_file = false;
                                if (size > cam_inc * clsDefines.EVENT_FILES_SIZE) { //40GB

                                    for (j = 0; j < l_files_list.length; j++) {
                                        l_files_list2 = l_files_list[j].listFiles();
                                        if (l_files_list2 != null) {
                                            for (l = 0; l < 10; l++) {

                                                l_files_list2[l].delete();
                                                del_file = true;
                                            }
                                        }
                                    }

                                }
                                if (del_file == false) {
                                    for (j = 0; j < l_files_list.length; j++) {
                                        str = l_files_list[j].getName().substring(1, l_files_list[j].getName().length());
                                        dat = sdfOutFileFormat.parse(str);
                                        difference = now_date.getTime() - dat.getTime();
                                        daysBetween = (difference / (1000 * 60 * 60 * 24));
                                        if (daysBetween >= expiry_days_event) {
                                            l_files_list[j].delete();
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
                
                //total size exceeds delte video files
                f = media_filepath;
                size = FileUtils.sizeOfDirectory(f);
                totalSize = size / ONE_GB;
                if (totalSize > clsDefines.ACTUAL_SIZE_HARDDISK - 5) { //TOTAL_SIZE_SDCARD) {
                    f = clsDefines.video_filepath;
                    if (f.exists()) {
                        files = f.listFiles();
                        if (files != null) {
                            Arrays.sort(files);
                            for (i = 0; i < files.length; i++) { //video files
                                if (files[i].isDirectory()) {
                                    cam_files = files[i].listFiles();
                                    if (cam_files != null) {
                                        Arrays.sort(cam_files);
                                        if (cam_files.length > 0) {
                                            for (l = 0; l < cam_files.length && l < cam_inc; l++) {
                                                cam_files[l].delete();
                                            }

                                        }
                                    }

                                }

                            }
                        }

                    }

                }

            } catch (Exception ex) {

            } finally {
                size = 0;
                totalSize = 0;
                files = null;
                cam_files = null;
                f = null;
                files = null;
                cam_files = null;
                f = null;
                l_files_list = null;
                l_files_list2 = null;
                cam_path = null;
                files_list = null;
                video_size = 0;
            }

        }

        private void delete_previous_can_files() {
            int i = 0;
            int no_days = 1;

            File[] files = null;
            File f = can_filepath;
            String str;
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            Date then = new Date(Calendar.getInstance().getTimeInMillis() - no_days * 24 * 3600 * 1000);
            String sort_date_3 = sdf.format(then);
            int sort_date_3_int = Integer.parseInt(sort_date_3);
            int str_int;
            String[] split_str;
            try {
                if (f.exists()) {
                    files = f.listFiles();
                    if (files != null) {
                        for (i = 0; i < files.length; i++) {

                            try {

                                split_str = files[i].getName().split("-");

                                str = split_str[split_str.length - 1]; //files[i].getName().substring(32, 40);

                                str = str.replace(".log", "");

                                str_int = Integer.parseInt(str);

                                if (str_int < sort_date_3_int) {

                                    try {

                                        files[i].delete();

                                    } catch (Exception ex) {

                                    }

                                }

                            } catch (Exception ex) {

                            }

                        }
                    }

                }

            } catch (Exception ex) {

            } finally {

                files = null;

                f = null;

                sort_date_3 = null;

                split_str = null;

                sdf = null;

                then = null;

                str = null;

            }

        }

        private void delete_extra_files() {

            int i = 0;

            File[] files = null;

            File f = (main_route_path);

            String str;

            try {

                if (f.exists()) {

                    files = f.listFiles();

                    if (files != null) {
                        for (i = 0; i < files.length; i++) {

                            try {
                                str = files[i].getName(); //files[i].getName().substring(32, 40);

                                if (str.startsWith("FSCK")) {

                                    try {

                                        files[i].delete();
                                    } catch (Exception ex) {

                                    }

                                }

                            } catch (Exception ex) {

                            }

                        }
                    }

                }
                f = clsDefines.main_filepath;

                if (f.exists()) {

                    files = f.listFiles();
                    if (files != null) {
                        for (i = 0; i < files.length; i++) {

                            try {

                                str = files[i].getName();

                                if (str.startsWith("hs_err")) {

                                    try {

                                        files[i].delete();
                                    } catch (Exception ex) {

                                    }

                                }

                            } catch (Exception ex) {

                            }

                        }
                    }

                }

                f = new File("/var/log");

                if (f.exists()) {

                    files = f.listFiles();
                    if (files != null) {
                        for (i = 0; i < files.length; i++) {

                            try {

                                str = files[i].getName(); //files[i].getName().substring(32, 40);

                                try {

                                    files[i].delete();

                                } catch (Exception ex) {

                                }

                            } catch (Exception ex) {

                            }

                        }
                    }

                }

            } catch (Exception ex) {

            } finally {

                files = null;

                f = null;

                str = null;

            }

        }

        private void delete_preevent_video_files() {
            int cnt = 0;
            int j = 0;
            int k = 0;
            File cam_path;
            File[] files_list;
            try {
                for (k = 0; k < 8; k++) {
                    cam_path = new File(video_filepath.getAbsolutePath() + "/Videos_preevent/Cam" + (k + 1) + "/");
                    if (cam_path.exists()) {

                        files_list = cam_path.listFiles();
                        if (files_list != null) {
                            cnt = files_list.length;
                            if (cnt > 40) {
                                Arrays.sort(files_list);
                                for (j = 0; j < cnt - 80; j++) {
                                    files_list[j].delete();

                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
            } finally {
                cam_path = null;
                files_list = null;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JButton ButtonStop = new javax.swing.JButton();
    private javax.swing.JPanel PanFullPane;
    public static javax.swing.JPanel PanLeftpane;
    public static javax.swing.JButton btnBreakDown;
    private javax.swing.JButton btnCanAlarm;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnDiagnosis;
    private javax.swing.JButton btnEmergency;
    public static javax.swing.JButton btnFire;
    public static javax.swing.JButton btnHome;
    private javax.swing.JButton btnLiveCam1;
    private javax.swing.JButton btnLiveCam2;
    private javax.swing.JButton btnLiveCam3;
    private javax.swing.JButton btnLiveCam4;
    private static javax.swing.JButton btnLiveCamAll;
    public static javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMessages;
    private javax.swing.JButton btnPhone;
    private javax.swing.JButton btnRoute;
    private javax.swing.JButton btnSos;
    private javax.swing.JButton btnVhmd;
    private javax.swing.JButton btnVideo;
    private javax.swing.JButton btnfileManager1;
    public static javax.swing.JLabel imgCam1;
    public static javax.swing.JLabel imgCam2;
    public static javax.swing.JLabel imgCam3;
    public static javax.swing.JLabel imgCam4;
    public static javax.swing.JLabel imgCan;
    public static javax.swing.JLabel imgCan1;
    public static javax.swing.JLabel imgCanEmergency;
    public static javax.swing.JLabel imgDigI2;
    public static javax.swing.JLabel imgDigI3;
    public static javax.swing.JLabel imgDigI4;
    private javax.swing.JLabel imgEmergency;
    private javax.swing.JLabel imgGps;
    private javax.swing.JLabel imgHardDisk;
    private javax.swing.JLabel imgIgnition;
    private javax.swing.JLabel imgMic;
    public static javax.swing.JLabel imgMobile;
    public static javax.swing.JLabel imgNetwork;
    private javax.swing.JLabel imgServerConn;
    private javax.swing.JLabel imgServerConn2;
    private javax.swing.JLabel imgServerConn3;
    private javax.swing.JLabel imgServerConn4;
    private javax.swing.JLabel imgServerConn5;
    private javax.swing.JLabel imgSigStr;
    private javax.swing.JLabel imgSimDetect;
    public static javax.swing.JLabel imgSos;
    private javax.swing.JLabel imgStopReq;
    public static javax.swing.JLabel imgTamper;
    public static javax.swing.JLabel imgWifi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPaneLive;
    private javax.swing.JLayeredPane jLayeredPaneLiveButtons;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JLabel lblFD;
    public static javax.swing.JLabel lblID;
    public static javax.swing.JLabel lblIDArt;
    private javax.swing.JLabel lblNetworkOperatorName;
    private javax.swing.JLabel lblPeopleIn1;
    public static javax.swing.JLabel lblPeopleIn2;
    private javax.swing.JLabel lblPeopleOut1;
    public static javax.swing.JLabel lblPeopleOut2;
    public static javax.swing.JLabel lblRD;
    public static javax.swing.JLabel lblSD;
    public static javax.swing.JLabel lblSDArt;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUsb;
    private javax.swing.JLabel lblconductorid;
    public static javax.swing.JLabel lbldriverid;
    public static javax.swing.JPanel panCenterMainPane;
    public static javax.swing.JPanel panMainPane;
    private javax.swing.JPanel panThirdRow;
    // End of variables declaration//GEN-END:variables
}
