/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.imgCam1;
import static obuits.MainFrmIts.imgCam2;
import static obuits.MainFrmIts.imgCam3;
import static obuits.MainFrmIts.imgCam4;

import static obuits.clsDefines.ANALOG_IPADDR;
import static obuits.clsDefines.CAM_FOORIR;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.ETHERNET_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.MAX_NO_TIMES;
import static obuits.clsDefines.VIDEO_RECORD_CONTINUOUS;
import static obuits.clsDefines.VIDEO_RECORD_MANUAL;
import static obuits.clsDefines.device_power_on;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.video_filepath;
import static obuits.clsSharedVariables.getRecordFromTime;
import static obuits.clsSharedVariables.getRecordToTime;
import static obuits.clsSharedVariables.getVideo1Connected;
import static obuits.clsSharedVariables.route_start;
import static obuits.clsSharedVariables.setVideo1Connected;

/**
 * This class handles video recording for different cameras. It extends the
 * Thread class to perform recording in a separate thread. Each instance of this
 * class is responsible for recording from a specific camera. The class also
 * manages camera connection statuses and updates URLs using the
 * clsOnvifUrlCamera class.
 *
 * @author Sumitha
 */
public class clsVideoRecording extends Thread {

    // Process for recording from camera 1
    Process cam1_rec_proc;
    // Thread to handle output stream from camera 1
    OutStreamCam1Thread outCam1;
    // Process ID
    int pid = 0;
    // Runtime object
    Runtime rt = null;
    // Input URL for the camera stream
    String in_url;
    // Icons for camera status
    ImageIcon imgCctv_on = new javax.swing.ImageIcon(getClass().getResource("/Images/cctv_on.png"));
    ImageIcon imgCctv_off = new javax.swing.ImageIcon(getClass().getResource("/Images/cctv_off.png"));
    // Camera number
    private byte camera_no = 1;
    // Recording types
    byte rec_type = VIDEO_RECORD_CONTINUOUS;
    byte pre_rec_type = VIDEO_RECORD_CONTINUOUS;
    // Date object for GPS date
    Date gpsdate;
    // Calendar object
    Calendar cal;
    // Flag to check if time is updated
    boolean time_updated = false;
    // Constants for video connection status
    final byte VIDEO_CONNECT = 1; // 1 --> connected
    final byte VIDEO_DISCONNECT = 0; // 0 --> disconnected
    final byte VIDEO_NOTSENT = 5; // 5 --> not sent
    // Previous video packet sent status
    byte prev_video_pkt_sent = VIDEO_NOTSENT;
    // Counter for repeated increments
    int repeat_inc = 0;
    // Flag to check record selection
    boolean chkRecordSelection;
    // Output filename
    String OutFilename = null;

    /**
     * Constructor to initialize video recording for a specific camera number.
     * It also updates camera URLs using the clsOnvifUrlCamera class.
     *
     * @param no The camera number to be recorded.
     */
    public clsVideoRecording(byte no) {
        super();
        camera_no = no;
        clsOnvifUrlCamera onvifUrls = new clsOnvifUrlCamera();
        switch (camera_no) {
            case 1:
                onvifUrls.update_cam1_urls(true);
                break;
            case 2:
                onvifUrls.update_cam2_urls(true);
                break;
            case 3:
                onvifUrls.update_cam3_urls(true);
                break;
            case 4:
                onvifUrls.update_cam4_urls(true);
                break;
            
            default:
                break;
        }
        onvifUrls = null;
    }

    /**
     * Method to run a command using the Runtime exec method. This method
     * handles the execution and termination of the process.
     *
     * @param cmd The command to be executed.
     * @return True if the command executes successfully, false otherwise.
     */
    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt1 = null;
        try {
            rt1 = Runtime.getRuntime();
            process = rt1.exec(cmd);
            process.waitFor(5, TimeUnit.SECONDS);
            return true;
        } catch (IOException e) {
            if (rt1 != null) {
                rt1.gc();
            }
            return false;
        } catch (Exception ex) {
            if (rt1 != null) {
                rt1.gc();
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
                    rt1 = null;
                }
            } catch (IOException e) {
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void run() {
        startAllCameraMonitors();
        // Initialize date formatters for output file naming and time display
        SimpleDateFormat sdfOutFileFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // Used for output file names
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Used for time display

        // Initialize time variables with epoch time (beginning of time)
        Date from_time = Date.from(Instant.EPOCH);
        Date to_time = Date.from(Instant.EPOCH);
        Date now_time = null;

        // Fetch initial settings for camera 1
        chkRecordSelection = clsSharedVariables.getCam1RecordSelected();
        boolean chkCamEnable = clsSharedVariables.getCam1Enabled();
        String video_path = "/Cam1/" + clsSharedVariables.getOBUID() + "_1";
        File chkPath;

        // Flags and settings initialization
        boolean first_time = false;
        clsOnvifUrlCamera onvifUrls = new clsOnvifUrlCamera();
        clsVideoNormalConfigUpdates objVidUpdate = new clsVideoNormalConfigUpdates();
        byte cam_type = clsDefines.CAM_HIKVISION;
        Field f;
        int inc = 0;
        NetworkInterface nif;
        clsReadFiles objReadfiles = new clsReadFiles();

        // Set exception handler for the current thread
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        boolean audio_enable = false;

        // Read MAC address
        objReadfiles.read_mac_address();

        // Main loop to continuously monitor and record video
        while (true) {
            // Switch case to handle each camera individually
            switch (camera_no) {
                case 1:
                    // Fetch settings for camera 1
                    chkRecordSelection = clsSharedVariables.getCam1RecordSelected();
                    chkCamEnable = clsSharedVariables.getCam1Enabled();
                    video_path = video_filepath.getAbsolutePath() + "/Cam1/" + clsSharedVariables.getOBUID() + "_1";
                    chkPath = new File(video_filepath.getAbsolutePath() + "/Cam1/");
                    if (!chkPath.exists()) {
                        chkPath.mkdirs(); // Create directory if it doesn't exist
                    }
                    rec_type = clsSharedVariables.getRecordType();
                    cam_type = clsSharedVariables.getCam1Type();
                    audio_enable = clsSharedVariables.getCam1RecordAudioSelected();
                    break;
                case 2:
                    // Fetch settings for camera 2
                    chkRecordSelection = clsSharedVariables.getCam2RecordSelected();
                    chkCamEnable = clsSharedVariables.getCam2Enabled();
                    video_path = video_filepath.getAbsolutePath() + "/Cam2/" + clsSharedVariables.getOBUID() + "_2";
                    chkPath = new File(video_filepath.getAbsolutePath() + "/Cam2/");
                    if (!chkPath.exists()) {
                        chkPath.mkdirs(); // Create directory if it doesn't exist
                    }
                    rec_type = clsSharedVariables.getRecordType();
                    cam_type = clsSharedVariables.getCam2Type();
                    audio_enable = clsSharedVariables.getCam2RecordAudioSelected();
                    break;
                case 3:
                    // Fetch settings for camera 3
                    chkRecordSelection = clsSharedVariables.getCam3RecordSelected();
                    chkCamEnable = clsSharedVariables.getCam3Enabled();
                    video_path = video_filepath.getAbsolutePath() + "/Cam3/" + clsSharedVariables.getOBUID() + "_3";
                    chkPath = new File(video_filepath.getAbsolutePath() + "/Cam3/");
                    if (!chkPath.exists()) {
                        chkPath.mkdirs(); // Create directory if it doesn't exist
                    }
                    rec_type = clsSharedVariables.getRecordType();
                    cam_type = clsSharedVariables.getCam3Type();
                    audio_enable = clsSharedVariables.getCam3RecordAudioSelected();
                    break;
                case 4:
                    // Fetch settings for camera 4
                    chkRecordSelection = clsSharedVariables.getCam4RecordSelected();
                    chkCamEnable = clsSharedVariables.getCam4Enabled();
                    video_path = video_filepath.getAbsolutePath() + "/Cam4/" + clsSharedVariables.getOBUID() + "_4";
                    chkPath = new File(video_filepath.getAbsolutePath() + "/Cam4/");
                    if (!chkPath.exists()) {
                        chkPath.mkdirs(); // Create directory if it doesn't exist
                    }
                    rec_type = clsSharedVariables.getRecordType();
                    cam_type = clsSharedVariables.getCam4Type();
                    audio_enable = clsSharedVariables.getCam4RecordAudioSelected();
                    break;
              
                default:
                    break;
            }
            // If the first_time flag or time_updated flag is false, and the camera is enabled
            if ((first_time == false || time_updated == false) && chkCamEnable) {
                // Check if the socket connection is active
                if (check_socket_con() == true) {

                    inc++; // Increment the counter
                    // Try to update the camera time to the system time
                    if (objVidUpdate.update_camera_time_system_time(camera_no) == true) {
                        time_updated = true;
                    }

                   
                    checkAndSetPassword(camera_no);
                    // Attempt to update the camera time up to 3 times
                    if (inc >= 3) {
                        time_updated = true;
                    }
                    // If this is the first time connecting
                    if (first_time == false) {
                        // Update camera URLs based on the camera number
                        switch (camera_no) {
                            case 1:
                                if (onvifUrls.update_cam1_urls(false) == true) {
                                    update_camera_settings_on_reconnection();
                                }
                                break;
                            case 2:
                                if (onvifUrls.update_cam2_urls(false) == true) {
                                    update_camera_settings_on_reconnection();
                                }
                                break;
                            case 3:
                                if (onvifUrls.update_cam3_urls(false) == true) {
                                    update_camera_settings_on_reconnection();
                                }
                                break;
                            case 4:
                                if (onvifUrls.update_cam4_urls(false) == true) {
                                    update_camera_settings_on_reconnection();
                                }
                                break;
                          
                            default:
                                break;
                        }
                    }
                    first_time = true; // Set first_time to true after initial setup

                } else {
                    try {
                        Thread.sleep(5000); // Wait for 5 seconds before retrying connection
                    } catch (InterruptedException ex) {
                        // Handle InterruptedException
                    }
                }
            } else if (chkRecordSelection == true && chkCamEnable && clsSharedVariables.getHardDriveDetected()) {
                // If recording is enabled, the camera is enabled, and the hard drive is detected
                try {
                    // Get the network interface by name
                    nif = NetworkInterface.getByName(ETHERNET_NETWORKINTERFACE_PATH);
                    if (nif == null) {
                        objReadfiles.write_camera_connect_log("nif null");
                        runCmd("sudo ifconfig eth0 up"); // Bring up the network interface
                        Thread.sleep(5000); // Wait for 5 seconds
                    } else if (!nif.isUp()) {
                        objReadfiles.write_camera_connect_log("nif down");
                        runCmd("sudo ifconfig eth0 up"); // Bring up the network interface if it's down
                        Thread.sleep(5000); // Wait for 5 seconds
                    }
                } catch (SocketException ex) {
                    // Handle SocketException
                } catch (InterruptedException ex) {
                    // Handle InterruptedException
                } catch (Exception ex) {
                    // Handle other exceptions
                }
                now_time = new Date(Calendar.getInstance().getTimeInMillis()); // Get the current time
                try {
                    rt = Runtime.getRuntime(); // Get the runtime instance

                    if (rec_type == VIDEO_RECORD_CONTINUOUS) {
                        // Set the input URL based on the camera number
                        switch (camera_no) {
                            case 1:
                                in_url = onvifUrls.get_cam1_record_main_url();
                                break;
                            case 2:
                                in_url = onvifUrls.get_cam2_record_main_url();
                                break;
                            case 3:
                                in_url = onvifUrls.get_cam3_record_main_url();
                                break;
                            case 4:
                                in_url = onvifUrls.get_cam4_record_main_url();
                                System.out.println("in video recording cls the cam4 path is "+in_url);
                                break;
                          
                            default:
                                break;
                        }
                        
                        runCmd("sudo chmod 777 -R " + media_filepath); // Set permissions on the media file path
                        // Set the output file name with the current timestamp
                        OutFilename = video_path + "%Y%m%d%H%M%S" + ".avi ";
                        objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + ": Connecting");
                        try {
                            if (cam_type == clsDefines.CAM_FOORIR) {
                                if (clsDefines.rtsp_found == true) {
                                    cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "/usr/bin/rtsp2avi -i '" + in_url + "' -o " + OutFilename});
                                } else {
                                    cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg -loglevel error -stats -y -rtsp_transport tcp -i '" + in_url + "' -vcodec copy -f segment -reset_timestamps 1 -strftime 1 -segment_time 3600 " + OutFilename});
                                }
                            } else {              
                                if (audio_enable == true) {
                                    cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg -loglevel error -stats -y -rtsp_transport tcp -i '" + in_url + "' -vcodec copy -f segment -reset_timestamps 1 -strftime 1 -segment_time 3600 " + OutFilename});
                                } else {
                                    cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg -loglevel error -stats -y -rtsp_transport tcp -i '" + in_url + "' -vcodec copy -an -f segment -reset_timestamps 1 -strftime 1 -segment_time 3600 " + OutFilename});
                                }
                                System.out.println("the recording cmd is "+in_url);
                            }
                            final String dat = in_url;
                            final String out_file = OutFilename;
                            // If diagnostics are enabled, update the UI with the recording details
                            if (clsSharedVariables.getCamDiagStatusEnabled() && (clsSharedVariables.getCamDiagStatusCamera() == (camera_no + 1))) {
                                try {
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            PanGpsDiag.txtData.append("hdpath: " + clsDefines.HARDDISK_PATH);
                                            PanGpsDiag.txtData.append("\n");
                                            PanGpsDiag.txtData.append(out_file);
                                            PanGpsDiag.txtData.append("\n");
                                            PanGpsDiag.txtData.append(dat);
                                            PanGpsDiag.txtData.append("\n");
                                            int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - clsDefines.SCROLL_BUFFER_SIZE;
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
                                        }
                                    });
                                } catch (Exception ex) {
                                }
                            }
                            try {
                                if (check_socket_con() == false) {
                                    Thread.sleep(5000); // Wait for 5 seconds if socket connection is not active
                                    if (check_socket_con() == false) {
                                        Thread.sleep(5000); // Wait for another 5 seconds if still not active
                                    }
                                }
                            } catch (Exception e) {
                            }
                            try {
                                f = cam1_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                pid = (int) f.get(cam1_rec_proc); // Get the process ID of the FFmpeg process
                            } catch (Exception ex) {
                            }

                            outCam1 = new OutStreamCam1Thread(cam_type);
                            // Start the output stream thread
                            outCam1.start();

                            objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + " before wait ");
                            cam1_rec_proc.waitFor(); // Wait for the FFmpeg process to complete

                            check_socket_con(); // Check the socket connection

                            objReadfiles.write_camera1_connect_log("VideoCam" + camera_no + " after wait : Disconnected");

                        } catch (InterruptedException ex) {
                            objReadfiles.write_camera1_connect_log("VideoCam" + camera_no + " interrrupt : Disconnected");
                        } catch (IOException ex) {
                            objReadfiles.write_camera1_connect_log("VideoCam" + camera_no + " io : Disconnected");
                        } catch (Exception ex) {
                            objReadfiles.write_camera1_connect_log("exception occurred" + camera_no);
                        } finally {
                            try {
                                outCam1.stop();
                                outCam1.interrupt();
                                if (cam1_rec_proc != null) {
                                    cam1_rec_proc.getInputStream().close();
                                    cam1_rec_proc.getOutputStream().close();
                                    cam1_rec_proc.getErrorStream().close();
                                    cam1_rec_proc.destroy();
                                    if (cam1_rec_proc.isAlive()) {
                                        cam1_rec_proc.destroyForcibly();
                                    }
                                }
                                rt.freeMemory();
                                rt.gc();
                                rt = null;
                            } catch (Exception ex) {
                            } finally {
                                outCam1 = null;
                            }
                        }
                    } else if (rec_type == VIDEO_RECORD_MANUAL) {
                        now_time = new Date(Calendar.getInstance().getTimeInMillis());
                        if (now_time.getDay() == 0 && clsSharedVariables.getSchRecSunday() == clsDefines.ENABLED
                                || now_time.getDay() == 1 && clsSharedVariables.getSchRecMonday() == clsDefines.ENABLED
                                || now_time.getDay() == 2 && clsSharedVariables.getSchRecTuesday() == clsDefines.ENABLED
                                || now_time.getDay() == 3 && clsSharedVariables.getSchRecWednesday() == clsDefines.ENABLED
                                || now_time.getDay() == 4 && clsSharedVariables.getSchRecThursday() == clsDefines.ENABLED
                                || now_time.getDay() == 5 && clsSharedVariables.getSchRecFriday() == clsDefines.ENABLED
                                || now_time.getDay() == 6 && clsSharedVariables.getSchRecSaturday() == clsDefines.ENABLED) {
                            try {
                                from_time = sdf.parse(getRecordFromTime());
                                to_time = sdf.parse(getRecordToTime());
                            } catch (ParseException ex) {
                            } catch (Exception ex) {
                            }
                            if (now_time.getHours() >= from_time.getHours() && now_time.getHours() <= to_time.getHours()) {
                                //  if (now_time.getMinutes() >= from_time.getMinutes() && now_time.getMinutes() <= to_time.getMinutes()) {
                                if (now_time.getHours() == to_time.getHours() && now_time.getMinutes() > to_time.getMinutes()
                                        || (now_time.getHours() == from_time.getHours() && now_time.getMinutes() < from_time.getMinutes())) {
                                    check_socket_con();
                                    Thread.sleep(5000);
                                } else {
                                    switch (camera_no) {
                                        case 1:
                                            in_url = onvifUrls.get_cam1_record_main_url();
                                            break;
                                        case 2:
                                            in_url = onvifUrls.get_cam2_record_main_url();
                                            break;
                                        case 3:
                                            in_url = onvifUrls.get_cam3_record_main_url();
                                            break;
                                        case 4:
                                            in_url = onvifUrls.get_cam4_record_main_url();
                                            break;
                                       
                                        default:
                                            break;
                                    }
                                    OutFilename = video_path + sdfOutFileFormat.format(now_time) + ".avi ";
                                    objReadfiles.write_camera1_connect_log("VideoCam" + camera_no + " Manual : Connecting");
                                    try {
                                        if (cam_type == clsDefines.CAM_FOORIR) {
                                            if (clsDefines.rtsp_found == true) {
                                                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "rtsp2avi -i '" + in_url + "' -o " + OutFilename});
                                            } else {
                                                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg -loglevel error -stats -y -rtsp_transport tcp -i '" + in_url + "' -vcodec copy -f segment -reset_timestamps 1 -strftime 1 -segment_time 3600 " + OutFilename});
                                            }
                                        } else {
                                            if (audio_enable == true) {
                                                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg  -y -rtsp_transport tcp -i '" + in_url + "' -t 3600 -vcodec copy " + OutFilename});
                                            } else {
                                                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg  -y -rtsp_transport tcp -i '" + in_url + "' -an -t 3600 -vcodec copy " + OutFilename});
                                            }
                                        }
                                        try {
                                            if (check_socket_con() == false) {
                                                Thread.sleep(5000);
                                                if (check_socket_con() == false) {
                                                    Thread.sleep(5000);
                                                }
                                            }
                                        } catch (Exception e) {
                                        }
                                        final String dat = in_url;
                                        if (clsSharedVariables.getCamDiagStatusEnabled() && (clsSharedVariables.getCamDiagStatusCamera() == (camera_no + 1))) {
                                            try {
                                                final String out_file = OutFilename;
                                                SwingUtilities.invokeLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        PanGpsDiag.txtData.append("hdpath: " + clsDefines.HARDDISK_PATH);
                                                        PanGpsDiag.txtData.append("\n");
                                                        PanGpsDiag.txtData.append(out_file);
                                                        PanGpsDiag.txtData.append("\n");
                                                        PanGpsDiag.txtData.append(dat);
                                                        PanGpsDiag.txtData.append("\n");
                                                        int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - clsDefines.SCROLL_BUFFER_SIZE;
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
                                                    }
                                                });
                                            } catch (Exception ex) {
                                            }
                                        }
                                        f = cam1_rec_proc.getClass().getDeclaredField("pid");
                                        f.setAccessible(true);
                                        pid = (int) f.get(cam1_rec_proc);
                                        outCam1 = new OutStreamCam1Thread(cam_type);
                                        outCam1.start();
                                        check_socket_con();
                                        objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + " Manual : start ");
                                        cam1_rec_proc.waitFor();
                                        check_socket_con();
                                        objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + " Manual : Disconnected");
                                    } catch (InterruptedException ex) {
                                        objReadfiles.write_camera1_connect_log("InterruptedException Video Cam" + camera_no + " Manual : Disconnected");
                                    } catch (IOException ex) {
                                        objReadfiles.write_camera1_connect_log("IOException Video Cam" + camera_no + " Manual : Disconnected");
                                    } catch (Exception ex) {
                                        objReadfiles.write_camera1_connect_log("Exception Video Cam" + camera_no + " Manual : Disconnected");
                                    } finally {
                                        try {
                                            if (cam1_rec_proc != null) {
                                                cam1_rec_proc.getInputStream().close();
                                                cam1_rec_proc.getOutputStream().close();
                                                cam1_rec_proc.getErrorStream().close();
                                                cam1_rec_proc.destroy();
                                                if (cam1_rec_proc.isAlive()) {
                                                    cam1_rec_proc.destroyForcibly();
                                                }
                                                outCam1.interrupt();
                                                outCam1.stop();
                                                cam1_rec_proc = null;
                                                in_url = null;
                                            }
                                        } catch (Exception ex) {
                                        } finally {
                                            cam1_rec_proc = null;
                                            outCam1 = null;
                                            in_url = null;
                                            rt.freeMemory();
                                            rt.gc();
                                            rt = null;
                                        }
                                    }
                                }
                            } else {
                                if (check_socket_con() == false) {
                                    Thread.sleep(5000);
                                }
                            }
                        } else {
                            if (check_socket_con() == false) {
                                Thread.sleep(5000);
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            } else if (chkCamEnable == false || chkRecordSelection == false) {
                if (check_socket_con() == false) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                //Logger.getLogger(clsVideoRecording.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class OutStreamCam1Thread extends Thread {

        BufferedReader reader = null;
        byte cameratype;

        public OutStreamCam1Thread(byte camera_type) {
            super();
            cameratype = camera_type;
        }

        clsReadFiles objReadfiles = new clsReadFiles();
        clsVideoNormalConfigUpdates objNormalUpdate = new clsVideoNormalConfigUpdates();

        @Override
        public void run() {

            String data;
            int framedata;
            int data_wait_cnt = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date from_time = Date.from(Instant.EPOCH);
            Date to_time = Date.from(Instant.EPOCH);
            Date now_time;
            Process p;
            boolean first_time_icon_update = false;
            // boolean first_time_icon_update_disc = false;
            int inc = 0;

            try {

                // Initialize BufferedReader to read the error stream of the camera process
                if (cameratype == clsDefines.CAM_FOORIR) {
                    reader = new BufferedReader(new InputStreamReader(cam1_rec_proc.getInputStream()));
                } else {
                    reader = new BufferedReader(new InputStreamReader(cam1_rec_proc.getErrorStream()));
                }
                while (true) {
                    // Check if the recording type is manual
                    if (rec_type == VIDEO_RECORD_MANUAL) {
                        now_time = new Date(Calendar.getInstance().getTimeInMillis());

                        // Parse the scheduled recording start and end times
                        try {
                            from_time = sdf.parse(getRecordFromTime());
                            to_time = sdf.parse(getRecordToTime());
                        } catch (ParseException ex) {

                        } catch (Exception ex) { // Check if the current day and time falls within the scheduled recording time
                        }
                        if ((now_time.getDay() == 0 && clsSharedVariables.getSchRecSunday() == clsDefines.ENABLED)
                                || (now_time.getDay() == 1 && clsSharedVariables.getSchRecMonday() == clsDefines.ENABLED)
                                || (now_time.getDay() == 2 && clsSharedVariables.getSchRecTuesday() == clsDefines.ENABLED)
                                || (now_time.getDay() == 3 && clsSharedVariables.getSchRecWednesday() == clsDefines.ENABLED)
                                || (now_time.getDay() == 4 && clsSharedVariables.getSchRecThursday() == clsDefines.ENABLED)
                                || (now_time.getDay() == 5 && clsSharedVariables.getSchRecFriday() == clsDefines.ENABLED)
                                || (now_time.getDay() == 6 && clsSharedVariables.getSchRecSaturday() == clsDefines.ENABLED)
                                && (now_time.getHours() >= from_time.getHours() && now_time.getHours() <= to_time.getHours())) {

                            // Check if the current time is within the scheduled recording minutes
                            if (now_time.getHours() == to_time.getHours() && now_time.getMinutes() > (to_time.getMinutes() + 2)
                                    || (now_time.getHours() == from_time.getHours() && now_time.getMinutes() < (from_time.getMinutes()) - 1)) {
                                try {
                                    // Terminate the camera process if the scheduled time is exceeded
                                    if (cam1_rec_proc != null) {
                                        p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                        p.waitFor(5, TimeUnit.SECONDS);
                                        p.destroy();
                                        if (p.isAlive()) {
                                            p.destroyForcibly();
                                            if (p.isAlive()) {
                                                p.destroyForcibly();

                                            }
                                        }
                                        p = null;
                                        rt.freeMemory();
                                        rt.gc();
                                        cam1_rec_proc.getInputStream().close();
                                        cam1_rec_proc.getOutputStream().close();
                                        cam1_rec_proc.getErrorStream().close();
                                        cam1_rec_proc.destroy();
                                        if (cam1_rec_proc.isAlive()) {
                                            cam1_rec_proc.destroyForcibly();
                                        }
                                    }
                                } catch (Exception ex) {
                                } finally {
                                    cam1_rec_proc = null;
                                    rt = null;
                                    p = null;
                                }
                                break;
                            } else {
                                // Read data from the camera process error stream
                                if (reader.ready()) {
                                    data_wait_cnt = 0;
                                    data = reader.readLine();
                                    final String dat = data;

                                    // Update diagnostic status if enabled
                                    if (clsSharedVariables.getCamDiagStatusEnabled() && (clsSharedVariables.getCamDiagStatusCamera() == (camera_no + 1))) {
                                        try {
                                            String out_file = OutFilename;
                                            SwingUtilities.invokeLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    PanGpsDiag.txtData.append("hdpath: " + clsDefines.HARDDISK_PATH);
                                                    PanGpsDiag.txtData.append("\n");
                                                    PanGpsDiag.txtData.append(out_file);
                                                    PanGpsDiag.txtData.append("\n");
                                                    PanGpsDiag.txtData.append(dat);
                                                    PanGpsDiag.txtData.append("\n");
                                                    int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - clsDefines.SCROLL_BUFFER_SIZE;
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
                                                }
                                            });
                                        } catch (Exception ex) {
                                        }
                                    }

                                    // Check for various error messages in the data and handle them accordingly
                                    if (data.contains("disonnected")) {
                                        objReadfiles.write_camera1_connect_log("Cam" + camera_no + " rec data Disconnected" + data);
                                        try {
                                            cam1_rec_proc.destroy();
                                        } catch (Exception ex) {
                                            objReadfiles.write_camera1_connect_log("Cam" + camera_no + " process destroy" + data);
                                        }
                                        break;
                                    } else if (data.contains("failed")) {
                                        objReadfiles.write_camera1_connect_log("Cam" + camera_no + " rec data failed : Disconnected" + data);
                                        try {
                                            cam1_rec_proc.destroy();
                                        } catch (Exception ex) {
                                            objReadfiles.write_camera1_connect_log("Cam" + camera_no + " process destroy" + data);
                                        }
                                        break;
                                    } else if (data.contains("route to host")) {
                                        objReadfiles.write_camera1_connect_log("Cam" + camera_no + " rec data No route to host" + data);
                                        try {
                                            cam1_rec_proc.destroy();
                                        } catch (Exception ex) {
                                            objReadfiles.write_camera1_connect_log("Cam" + camera_no + " process destroy" + data);
                                        }
                                        break;
                                    } else if (chkRecordSelection == false) {
                                        try {
                                            if (cam1_rec_proc != null) {
                                                objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  Record unselected Closing Conn" + data);
                                                cam1_rec_proc.getInputStream().close();
                                                cam1_rec_proc.getOutputStream().close();
                                                cam1_rec_proc.getErrorStream().close();
                                                cam1_rec_proc.destroy();
                                                cam1_rec_proc = null;
                                                p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                                p.waitFor(5, TimeUnit.SECONDS);
                                                p.destroy();
                                                p = null;
                                            }
                                            rt.freeMemory();
                                            rt.gc();
                                            rt = null;
                                        } catch (Exception ex) {
                                        }
                                        break;
                                    } else {
                                        // Update icon and settings if frame data is received
                                        if (data.startsWith("frame")) {
                                            if (first_time_icon_update == false) {
                                                update_image_icon(true);
                                                first_time_icon_update = true;
                                                update_camera_settings_on_reconnection();
                                            }
                                        }
                                    }
                                } else {
                                    // Increment data wait count and check connection if needed
                                    data_wait_cnt++;
                                    if (first_time_icon_update == true) {
                                        if (data_wait_cnt > 20) {
                                            objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  data waiting elapsed ");

                                            update_image_icon(false);
                                        }
                                    }
                                    if (data_wait_cnt > 100 * 2) {
                                        data_wait_cnt = 0;
                                        check_socket_con();
                                        try {
                                            if (cam1_rec_proc != null) {
                                                objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  data waiting elapsed  200 ");
                                                p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                                p.waitFor(20, TimeUnit.SECONDS);
                                                p.destroy();
                                                if (p.isAlive()) {
                                                    p.destroyForcibly();
                                                }
                                                p = null;
                                                rt.freeMemory();
                                                rt.gc();
                                                cam1_rec_proc.getInputStream().close();
                                                cam1_rec_proc.getOutputStream().close();
                                                cam1_rec_proc.getErrorStream().close();
                                                cam1_rec_proc.destroy();
                                                if (cam1_rec_proc.isAlive()) {
                                                    cam1_rec_proc.destroyForcibly();
                                                }
                                            }
                                        } catch (Exception ex) {
                                        } finally {
                                            cam1_rec_proc = null;
                                            rt = null;
                                            p = null;
                                        }
                                        break;
                                    }
                                }
                                Thread.sleep(100);
                            }
                        } else {
                            try {
                                // Terminate the camera process if recording is not manual
                                if (cam1_rec_proc != null) {
                                    objReadfiles.write_camera1_connect_log("Cam" + camera_no + " kill app ");
                                    p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                    p.waitFor(5, TimeUnit.SECONDS);
                                    p.destroy();
                                    if (p.isAlive()) {
                                        p.destroyForcibly();
                                    }
                                    p = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    cam1_rec_proc.getInputStream().close();
                                    cam1_rec_proc.getOutputStream().close();
                                    cam1_rec_proc.getErrorStream().close();
                                    cam1_rec_proc.destroy();
                                    if (cam1_rec_proc.isAlive()) {
                                        cam1_rec_proc.destroyForcibly();
                                    }
                                }
                            } catch (Exception ex) {
                            } finally {
                                cam1_rec_proc = null;
                                rt = null;
                                p = null;
                            }
                            break;
                        }
                    } else {
                        if (reader.ready()) {
                             //System.out.println("reader is ready: " + camera_no);
                            data_wait_cnt = 0;
                            char[] cbuf = new char[200];
                            framedata = reader.read(cbuf);
                            //added newly
                            data = new String(cbuf, 0, framedata).trim();
                            final String str = data;
                            // objReadfiles.write_test_log(data);
                            //  System.out.println("Data: " + data);
                            //added
                            if (clsSharedVariables.getCamDiagStatusEnabled() && (clsSharedVariables.getCamDiagStatusCamera() == (camera_no + 1))) {
                                try {
                                    String out_file = OutFilename;
                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            PanGpsDiag.txtData.append("hdpath: " + clsDefines.HARDDISK_PATH);
                                            PanGpsDiag.txtData.append("\n");
                                            PanGpsDiag.txtData.append(out_file);
                                            PanGpsDiag.txtData.append("\n");
                                            PanGpsDiag.txtData.append(str);
                                            PanGpsDiag.txtData.append("\n");
                                            int numLinesToTrunk = PanGpsDiag.txtData.getLineCount() - clsDefines.SCROLL_BUFFER_SIZE;
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
                                        }
                                    });
                                } catch (Exception ex) {
                                }
                            }
                            //added
                            //added newly
                            if (data.contains("route to host")) {
                                objReadfiles.write_camera1_connect_log(camera_no + " rec data No route to host" + data);
                                try {
                                    cam1_rec_proc.getInputStream().close();
                                    cam1_rec_proc.getOutputStream().close();
                                    cam1_rec_proc.getErrorStream().close();
                                    cam1_rec_proc.destroy();
                                    if (cam1_rec_proc.isAlive()) {
                                        cam1_rec_proc.destroyForcibly();
                                    }
                                    /*  if (processBuilder != null) {
                                        processBuilder = null;
                                    }*/
                                } catch (Exception ex) {
                                }
                                break;
                            
                           } else if (chkRecordSelection == false) {
                                //System.out.println("chkRecordSelection");
                                try {
                                    if (cam1_rec_proc != null) {
                                        objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  rec not selected ");
                                        cam1_rec_proc.getInputStream().close();
                                        cam1_rec_proc.getOutputStream().close();
                                        cam1_rec_proc.getErrorStream().close();
                                        cam1_rec_proc.destroy();
                                        if (cam1_rec_proc.isAlive()) {
                                            cam1_rec_proc.destroyForcibly();
                                        }
                                        /*  if (processBuilder != null) {
                                        processBuilder = null;
                                    }*/
                                        cam1_rec_proc = null;
                                    }
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                } catch (Exception ex) {
                                }
                                break;
                            } else if (data.contains("finished")) {
                                try {
                                    if (cam1_rec_proc != null) {
                                        objReadfiles.write_camera1_connect_log("Cam" + camera_no + "data equals finished ");
                                        cam1_rec_proc.getInputStream().close();
                                        cam1_rec_proc.getOutputStream().close();
                                        cam1_rec_proc.getErrorStream().close();
                                        cam1_rec_proc.destroy();
                                        if (cam1_rec_proc.isAlive()) {
                                            cam1_rec_proc.destroyForcibly();
                                        }
                                        cam1_rec_proc = null;
                                    }
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                } catch (Exception ex) {
                                }
                                break;
                            } else if (framedata > 0) {
                                //  System.out.println(" frame data is>0" + camera_no);
                                //objReadfiles.write_camera1_connect_log(camera_no + " frame data is>0");
                                try {
                                    if (first_time_icon_update == false) {
                                        update_image_icon(true);

                                        first_time_icon_update = true;
                                        update_camera_settings_on_reconnection();
                                    }
                                    if (inc++ > 100) {
                                        inc = 0;
                                        objNormalUpdate.setWaterMarking(camera_no);
                                    }
                                } catch (Exception ex) {
                                    objReadfiles.write_camera1_connect_log("exception ex" + ex.getMessage());
                                }
                            } else {
                                objReadfiles.write_camera1_connect_log(camera_no + " outdata " + data);
                            }
                        } else {
                            // System.out.println("reader is not ready: " + camera_no);
                            objReadfiles.write_camera1_connect_log(camera_no + "reader not ready");
                            data_wait_cnt++;
                            //objReadfiles.write_test_log("count: " + data_wait_cnt);
                            //System.out.println("count: " + data_wait_cnt);
                            if (data_wait_cnt % 2 == 0) {
                                if (!OutFilename.contains(clsDefines.HARDDISK_PATH)) {
                                    // System.out.println("harddisk path");
                                    data_wait_cnt = 0;
                                    check_socket_con();
                                    try {
                                        objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  rec data wait exceeded  200");
                                        p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                        p.waitFor(5, TimeUnit.SECONDS);
                                        p.destroy();
                                        rt.freeMemory();
                                        rt.gc();
                                        cam1_rec_proc.getInputStream().close();
                                        cam1_rec_proc.getOutputStream().close();
                                        cam1_rec_proc.getErrorStream().close();
                                        /*  if (processBuilder != null) {
                                        processBuilder = null;
                                    }*/
                                        cam1_rec_proc.destroy();
                                        if (cam1_rec_proc.isAlive()) {
                                            cam1_rec_proc.destroyForcibly();
                                        }
                                    } catch (Exception ex) {
                                        objReadfiles.write_camera1_connect_log("Exception indata_wait_cnt" + ex.getMessage());
                                    } finally {
                                        cam1_rec_proc = null;
                                        rt = null;
                                        p = null;
                                    }
                                    break;
                                }
                            }
                            if (data_wait_cnt >= 500) {//500 kept for  disconnecting issue

                                objReadfiles.write_camera1_connect_log(camera_no + " :tried so many times " + data_wait_cnt);
                                data_wait_cnt = 0;
                                check_socket_con();
                                try {
                                    objReadfiles.write_camera1_connect_log("Camera" + camera_no + "  rec data wait exceeded" + data_wait_cnt);
                                    p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                    p.waitFor(5, TimeUnit.SECONDS);
                                    p.destroy();
                                    rt.freeMemory();
                                    rt.gc();
                                    cam1_rec_proc.getInputStream().close();
                                    cam1_rec_proc.getOutputStream().close();
                                    cam1_rec_proc.getErrorStream().close();
                                    cam1_rec_proc.destroy();
                                    if (cam1_rec_proc.isAlive()) {
                                        cam1_rec_proc.destroyForcibly();
                                    }
                                    /*  if (processBuilder != null) {
                                        processBuilder = null;
                                    }*/
                                } catch (Exception ex) {
                                    objReadfiles.write_camera1_connect_log("Exception last bef " + ex.getMessage());
                                } finally {
                                    cam1_rec_proc = null;
                                    rt = null;
                                    p = null;
                                }
                                break;
                            }
                        }
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception ex) {
                objReadfiles.write_camera1_connect_log("Exception last " + ex.getMessage());
            } finally {
                objReadfiles = null;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        // Logger.getLogger(clsVideoRecording.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    reader = null;
                }
            }
        }
    }

    public static void checkAndSetPassword(byte camera_no) {
        try {
            String user_name = null;
            String pwd = null;
            String camurl = null;
            clsReadFiles objReadfiles = new clsReadFiles();
            String line;
            int exitCode;
           // System.out.println("line" + camera_no);
            
            // Start the ffplay process
            switch (camera_no) {
                case 1:
                    user_name = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    camurl = "@" + clsDefines.CAM1_IPADDR;
                    break;
                case 2:
                    user_name = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    camurl = "@" + clsDefines.CAM2_IPADDR;
                    break;
                case 3:
                    user_name = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    camurl = "@" + clsDefines.CAM3_IPADDR;
                    break;
                case 4:
                    user_name = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    camurl = "@" + clsDefines.CAM4_IPADDR;
                    break;
                
                default:
                    break;
            }
            Process process = new ProcessBuilder("ffplay", "-i", "rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102", "-vn", "-an").start();
            // Read the standard output (stdout) of ffplay using a single reader
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Read each line from stdout
            while ((line = errorReader.readLine()) != null) {
                //System.out.println("line" + line);
                if (line.toLowerCase().contains("unauthorized")) {
                   // System.out.println("unauthorised" + camera_no);
                    if (camera_no == 1 && clsSharedVariables.getCam1Type() == clsDefines.CAM_HIKVISION) {
                        clsSharedVariables.setCam1Pwd("sumith321");
                    } else if (camera_no == 2 && clsSharedVariables.getCam2Type() == clsDefines.CAM_HIKVISION) {
                        clsSharedVariables.setCam2Pwd("sumith321");
                    } else if (camera_no == 3 && clsSharedVariables.getCam3Type() == clsDefines.CAM_HIKVISION) {
                        clsSharedVariables.setCam3Pwd("sumith321");
                    } else if (camera_no == 4 && clsSharedVariables.getCam4Type() == clsDefines.CAM_HIKVISION) {
                        clsSharedVariables.setCam4Pwd("sumith321");
                    }
                }
            }
            objReadfiles.write_cfg_data_file();
            // Wait for the ffplay process to finish
             exitCode = process.waitFor();
            //System.out.println("ffplay process finished with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean check_socket_con() {
        // Initialize the status of the socket connection
        boolean status = false;

        // Check the socket connection status
        status = check_socket_connection();
        if (status == true) {
            // If the connection is successful, log the connection and update the image icon
            clsReadFiles objReadfiles = new clsReadFiles();
            objReadfiles.write_camera_connect_log("Camera" + camera_no + "  : Connected");

            update_image_icon(true);

            objReadfiles = null;
            return true;
        } else {
            // If the connection fails, log the disconnection and update the image icon
            clsReadFiles objReadfiles = new clsReadFiles();
            gpsDriving objDrive = new gpsDriving();

            update_image_icon(false);
            objReadfiles.write_camera_connect_log("Camera" + camera_no + "  : disConnected");
            switch (camera_no) {
                case 1:
                    // Handle disconnection logic for camera 1
                    if ((getVideo1Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 1, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    setVideo1Connected(false);
                    break;
                case 2:
                    // Handle disconnection logic for camera 2
                    if ((clsSharedVariables.getVideo2Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 2, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo2Connected(false);
                    break;
                case 3:
                    // Handle disconnection logic for camera 3
                    if ((clsSharedVariables.getVideo3Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 3, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo3Connected(false);
                    break;
                case 4:
                    // Handle disconnection logic for camera 4
                    if ((clsSharedVariables.getVideo4Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 4, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo4Connected(false);
                    break;
                case 5:
                    // Handle disconnection logic for camera 5
                    if ((clsSharedVariables.getVideo5Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 5, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo5Connected(false);
                    break;
                case 6:
                    // Handle disconnection logic for camera 6
                    if ((clsSharedVariables.getVideo6Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 6, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo6Connected(false);
                    break;
                case 7:
                    // Handle disconnection logic for camera 7
                    if ((clsSharedVariables.getVideo7Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 7, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo7Connected(false);
                    break;
                case 8:
                    // Handle disconnection logic for camera 8
                    if ((clsSharedVariables.getVideo8Connected() == true && prev_video_pkt_sent != VIDEO_DISCONNECT)) {
                        if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                            objDrive.video_disconnect_pkt((byte) 8, VIDEO_DISCONNECT);
                        }
                        prev_video_pkt_sent = VIDEO_DISCONNECT;
                    }
                    clsSharedVariables.setVideo8Connected(false);
                    break;
                default:
                    break;
            }
            objReadfiles = null;
            objDrive = null;
            return false;
        }
    }

private boolean check_socket_connection() {
    String addr;
    boolean cam_enabled;
    byte cam_type = clsSharedVariables.getCam1Type();
    int channel = 0; // for DVR/analog
    int subtype=0;

    // Determine camera address, type, enable status, and channel
    switch (camera_no) {
        case 1:
            addr = ANALOG_IPADDR;
            cam_enabled = clsSharedVariables.getCam1Enabled();
            cam_type = clsSharedVariables.getCam1Type();
            channel = 1;
            break;
        case 2:
            addr = ANALOG_IPADDR;
            cam_enabled = clsSharedVariables.getCam2Enabled();
            cam_type = clsSharedVariables.getCam2Type();
                        channel = 2;

            break;
        case 3:
            addr = ANALOG_IPADDR;
            cam_enabled = clsSharedVariables.getCam3Enabled();
            cam_type = clsSharedVariables.getCam3Type();
            channel = 3;

            break;
        case 4:
            addr = ANALOG_IPADDR;
            cam_enabled = clsSharedVariables.getCam4Enabled();
            cam_type = clsSharedVariables.getCam4Type();
            channel = 4;
           // channel = 1;
            break;
       
        default:
            addr = clsDefines.CAM4_IPADDR;
            cam_enabled = clsSharedVariables.getCam4Enabled();
            cam_type = clsSharedVariables.getCam4Type();
            break;
    }

    if (!cam_enabled) return false;

    try {
        // IP cameras (1–3): check connectivity on ports 80 and 554
      

        // Analog/DVR cameras (4–7): check RTSP on port 554 using channel
        if (camera_no >= 1 && camera_no <= 4) {
    // Username and password for DVR
    String user = clsSharedVariables.getCam4UserName();
    String pwd = clsSharedVariables.getCam4Pwd();
    System.out.println("password "+pwd);
    
    // Standard DVR RTSP path: /Streaming/channels/{channel}
    String rtspUrl = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/cam/realmonitor?channel="+channel + "&subtype="+subtype;
    return check_rtsp_connection(rtspUrl);
}

    } catch (Exception ex) {
        return false;
    }

    return false;
}

private boolean check_rtsp_connection(String rtspUrl) {
    Process process = null;
    try {
        // ffplay runs headless (-nodisp), no audio (-an), test for 5 sec (-t 5)
        String[] cmd = {
            "ffplay", "-t", "5", "-nodisp", "-an",
            "-loglevel", "info", "-rtsp_transport", "tcp",
            rtspUrl
        };
        process = new ProcessBuilder(cmd).redirectErrorStream(true).start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean hasVideo = false;

        while ((line = reader.readLine()) != null) {
            if (line.contains("Video:")) { 
                // ffplay detected an actual video stream
                hasVideo = true;
            }
        }

        int exitCode = process.waitFor();
        return (exitCode == 0 && hasVideo); // ✅ Only green if video stream detected

    } catch (Exception e) {
        return false;
    } finally {
        if (process != null) process.destroy();
    }
}




    private void update_image_icon(boolean connected) {
        
        // Create an instance of clsHealthPacketStructure to update camera status
        final clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        objHealth.set_cam_status_updated(true);

        // Use a switch statement to handle different camera numbers
        switch (camera_no) {
            case 1:
                // Update UI components for camera 1 using SwingUtilities.invokeLater for thread safety
                SwingUtilities.invokeLater(() -> {
                    if (connected == true) {
                        // If connected, set the icon to "on" and update status to connected
                        imgCam1.setIcon(imgCctv_on);
                        setVideo1Connected(true);
                        objHealth.set_cam1_status(VIDEO_CONNECT);
                    } else {
                        // If not connected, set the icon to "off" and update status to disconnected
                        imgCam1.setIcon(imgCctv_off);
                        setVideo1Connected(false);
                        objHealth.set_cam1_status(VIDEO_DISCONNECT);
                    }
                });
                break;
            case 2:
                // Update UI components for camera 2 using SwingUtilities.invokeLater for thread safety
                SwingUtilities.invokeLater(() -> {
                    if (connected == true) {
                        imgCam2.setIcon(imgCctv_on);
                        clsSharedVariables.setVideo2Connected(true);
                        objHealth.set_cam2_status(VIDEO_CONNECT);
                    } else {
                        imgCam2.setIcon(imgCctv_off);
                        clsSharedVariables.setVideo2Connected(false);
                        objHealth.set_cam2_status(VIDEO_DISCONNECT);
                    }
                });
                break;
            case 3:
                // Update UI components for camera 3 using SwingUtilities.invokeLater for thread safety
                SwingUtilities.invokeLater(() -> {
                    if (connected == true) {
                        imgCam3.setIcon(imgCctv_on);
                        clsSharedVariables.setVideo3Connected(true);
                        objHealth.set_cam3_status(VIDEO_CONNECT);
                    } else {
                        imgCam3.setIcon(imgCctv_off);
                        clsSharedVariables.setVideo3Connected(false);
                        objHealth.set_cam3_status(VIDEO_DISCONNECT);
                    }
                });
                break;
            case 4:
                // Update UI components for camera 4 using SwingUtilities.invokeLater for thread safety
                SwingUtilities.invokeLater(() -> {
                    if (connected == true) {
                        imgCam4.setIcon(imgCctv_on);
                        clsSharedVariables.setVideo4Connected(true);
                        objHealth.set_cam4_status(VIDEO_CONNECT);
                    } else {
                        imgCam4.setIcon(imgCctv_off);
                        clsSharedVariables.setVideo4Connected(false);
                        objHealth.set_cam4_status(VIDEO_DISCONNECT);
                    }
                });
                break;
           
           
            default:
                // Handle any other camera numbers (if any)
                break;
        }
    }

    private void update_camera_settings_on_reconnection() {
        // Create an instance of clsVideoNormalConfigUpdates to update video configurations
        clsVideoNormalConfigUpdates objVidUpdate = new clsVideoNormalConfigUpdates();

        // Check if the previous video packet sent was not a VIDEO_CONNECT packet
        if (prev_video_pkt_sent != VIDEO_CONNECT) {

            // Update the image icon to reflect the reconnection state
            update_image_icon(true);

            // Double-check if the previous video packet sent is still not VIDEO_CONNECT
            if (prev_video_pkt_sent != VIDEO_CONNECT) {
                try {
                    // Update the mirror image settings for the specified camera
                    objVidUpdate.update_mirror_image_settings(camera_no);
                } catch (Exception ex) {
                    // Handle exceptions silently
                }
            }

            // Mark the previous video packet sent as VIDEO_CONNECT
            prev_video_pkt_sent = VIDEO_CONNECT;

            try {
                // Attempt to update the camera time to system time for a maximum number of retries
                for (repeat_inc = 0; repeat_inc < MAX_NO_TIMES; repeat_inc++) {
                    try {
                        // Check if the camera type is IP_CAMERA
                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            // Update the camera time to the system time and break the loop if successful
                            if (objVidUpdate.update_camera_time_system_time(camera_no) == true) {
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        // Handle exceptions silently
                    }
                }

            } catch (Exception e) {
                // Handle exceptions silently
            } finally {
                // Clean up the objVidUpdate instance
                objVidUpdate = null;
            }
        }
    }
    public void startAllCameraMonitors() {
    startCameraMonitor("CAM1", "10.42.0.3", 554, imgCam1);
    startCameraMonitor("CAM2", "10.42.0.4", 554, imgCam2);
    startCameraMonitor("CAM3", "10.42.0.5", 554, imgCam3);
    startCameraMonitor("CAM4", "10.42.0.6", 554, imgCam4);
//    startCameraMonitor("CAM5", "10.42.0.7", 554, imgCam5);
//    startCameraMonitor("CAM6", "10.42.0.8", 554, imgCam6);
//    startCameraMonitor("CAM7", "10.42.0.9", 554, imgCam7);
//    startCameraMonitor("CAM8", "10.42.0.10", 554, imgCam8);
}

    public void startCameraMonitor(String camName, String camIp, int camPort, JLabel camIconLabel) {
    new Thread(() -> {
        boolean lastStatus = false;

        while (true) {
            boolean isOnline = false;

            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(camIp, camPort), 2000); // 2s timeout
                isOnline = true;
            } catch (IOException ignored) {
                isOnline = false;
            }

            final boolean status = isOnline;

            if (status != lastStatus) {
                SwingUtilities.invokeLater(() -> {
                    if (status) {
                        camIconLabel.setIcon(imgCctv_on);
                       // System.out.println(camName + " is ONLINE");
                    } else {
                        camIconLabel.setIcon(imgCctv_off);
                       // System.out.println(camName + " is OFFLINE");
                    }
                });
                lastStatus = status;
            }

            try {
                Thread.sleep(3000); // check every 3 seconds
            } catch (InterruptedException ignored) {}
        }
    }, camName + "_MonitorThread").start();
}


    class ExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_camera1_connect_log("clsVideoRecord  Cam" + camera_no + " uncaughtExceptionHandler " + e.getMessage());
            try {
                clsVideoRecording objVid = new clsVideoRecording(camera_no);
                objVid.start();
            } catch (Exception ex) {
            }
            objReadFiles = null;
        }
    }

}
