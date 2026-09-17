package obuits;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static obuits.clsDefines.ETHERNET_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.can_video_event_filepath;
import static obuits.clsSharedVariables.getCam1Enabled;

// Class declaration for recording videos based on CAN data, extending Thread class
public class clsCanBasedVideoRecord extends Thread {

    // Member variable declarations
    Process cam1_rec_proc; // Process variable for camera  recording
    OutStreamCam1Thread outCam1; // Thread for handling camera 1 output stream
    int pid = 0; // Process ID
    Runtime rt = null; // Runtime environment
    String in_url; // Input URL for camera stream
    byte camera_no = 1; // Camera number, initialized to default value 1

    // Constructor method for initializing the class with a specified camera number
    public clsCanBasedVideoRecord(byte cam_no) {
        super(); // Call superclass constructor (Thread)
        camera_no = cam_no; // Assign the camera number passed to the constructor
    }

    // Method for running a command in the system shell
    private boolean runCmd(String cmd) {
        Process process = null; // Variable to hold the process
        Runtime rt1 = null; // Runtime environment
        try {
            rt1 = Runtime.getRuntime(); // Get the runtime environment
            process = rt1.exec(cmd); // Execute the command in the system shell
            process.waitFor(5, TimeUnit.SECONDS); // Wait for the process to finish, with a timeout of 5 seconds
            return true; // Return true indicating successful execution
        } catch (IOException e) { // Handle IOException
            if (rt1 != null) {
                rt1.gc(); // Run garbage collection to free up resources
            }
            return false; // Return false indicating failure
        } catch (Exception ex) { // Catch any other exceptions
            if (rt1 != null) {
                rt1.gc(); // Run garbage collection to free up resources
            }
            return false; // Return false indicating failure
        } finally { // Finally block to ensure cleanup
            try {
                if (process != null) { // Check if the process is not null
                    // Close input, output, and error streams of the process
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    // Destroy the process
                    process.destroy();
                    process = null; // Set process variable to null
                    rt1 = null; // Set runtime environment variable to null
                }
            } catch (IOException e) { // Handle IOException
            } catch (Exception ex) { // Catch any other exceptions
            }
        }
    }
    // continous loop for recording

    public void run() {
        SimpleDateFormat sdfOutFileFormat = new SimpleDateFormat("yyyyMMddHHmmss");//%Y%m%d%H%M%S
        Date now_time;
        String OutFilename;
        Field f;
        NetworkInterface nif;
        clsReadFiles objReadfiles = new clsReadFiles();
        String video_path;
        File dir;
        byte cam_type = clsSharedVariables.getCam1Type();
        boolean cam_enable = getCam1Enabled();
        boolean can_rec_enable = clsSharedVariables.getCanRecordEnabled();
        boolean can_rec_started = clsSharedVariables.getCanBasedRecording1Started();
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
        String addr = clsDefines.CAM1_IPADDR;
        boolean audio_enable = clsSharedVariables.getCam1RecordAudioSelected();
        video_path = can_video_event_filepath.getAbsolutePath() + "/Cam1/";
        dir = new File(video_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (camera_no == 2) {
            cam_type = clsSharedVariables.getCam2Type();
            cam_enable = clsSharedVariables.getCam2Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording2Started();
            user = clsSharedVariables.getCam2UserName();
            pwd = clsSharedVariables.getCam2Pwd();
            addr = clsDefines.CAM2_IPADDR;
            audio_enable = clsSharedVariables.getCam2RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam2/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else if (camera_no == 3) {
            cam_type = clsSharedVariables.getCam3Type();
            cam_enable = clsSharedVariables.getCam3Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording3Started();
            user = clsSharedVariables.getCam3UserName();
            pwd = clsSharedVariables.getCam3Pwd();
            addr = clsDefines.CAM3_IPADDR;
            audio_enable = clsSharedVariables.getCam3RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam3/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else if (camera_no == 4) {
            cam_type = clsSharedVariables.getCam4Type();
            cam_enable = clsSharedVariables.getCam4Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording4Started();
            user = clsSharedVariables.getCam4UserName();
            pwd = clsSharedVariables.getCam4Pwd();
            addr = clsDefines.CAM4_IPADDR;
            audio_enable = clsSharedVariables.getCam4RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam4/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else if (camera_no == 5) {
            cam_type = clsSharedVariables.getCam5Type();
            cam_enable = clsSharedVariables.getCam5Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording5Started();
            user = clsSharedVariables.getCam5UserName();
            pwd = clsSharedVariables.getCam5Pwd();
            addr = clsDefines.CAM5_IPADDR;
            audio_enable = clsSharedVariables.getCam5RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam5/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else if (camera_no == 6) {
            cam_type = clsSharedVariables.getCam6Type();
            cam_enable = clsSharedVariables.getCam6Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording6Started();
            user = clsSharedVariables.getCam6UserName();
            pwd = clsSharedVariables.getCam6Pwd();
            addr = clsDefines.CAM6_IPADDR;
            audio_enable = clsSharedVariables.getCam6RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam6/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else if (camera_no == 7) {
            cam_type = clsSharedVariables.getCam7Type();
            cam_enable = clsSharedVariables.getCam7Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording7Started();
            user = clsSharedVariables.getCam7UserName();
            pwd = clsSharedVariables.getCam7Pwd();
            addr = clsDefines.CAM7_IPADDR;
            audio_enable = clsSharedVariables.getCam7RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam7/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } else if (camera_no == 8) {
            cam_type = clsSharedVariables.getCam8Type();
            cam_enable = clsSharedVariables.getCam8Enabled();
            can_rec_enable = clsSharedVariables.getCanRecordEnabled();
            can_rec_started = clsSharedVariables.getCanBasedRecording8Started();
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
            addr = clsDefines.CAM8_IPADDR;
            audio_enable = clsSharedVariables.getCam8RecordAudioSelected();
            video_path = can_video_event_filepath.getAbsolutePath() + "/Cam8/";
            dir = new File(video_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        while (true) {
            if (cam_enable && can_rec_enable == true && can_rec_started == false && clsSharedVariables.getHardDriveDetected()) {

                can_rec_started = true;
                if (camera_no == 1) {
                    clsSharedVariables.setCanBasedRecording1Started(true);
                } else if (camera_no == 2) {
                    clsSharedVariables.setCanBasedRecording2Started(true);
                } else if (camera_no == 3) {
                    clsSharedVariables.setCanBasedRecording3Started(true);
                } else if (camera_no == 4) {
                    clsSharedVariables.setCanBasedRecording4Started(true);
                } else if (camera_no == 5) {
                    clsSharedVariables.setCanBasedRecording5Started(true);
                } else if (camera_no == 6) {
                    clsSharedVariables.setCanBasedRecording6Started(true);
                } else if (camera_no == 7) {
                    clsSharedVariables.setCanBasedRecording7Started(true);
                } else if (camera_no == 8) {
                    clsSharedVariables.setCanBasedRecording8Started(true);
                }

                try {
                    nif = NetworkInterface.getByName(ETHERNET_NETWORKINTERFACE_PATH); //GPRS_NETWORKINTERFACE_PATH);
                    if (nif == null) {
                        objReadfiles.write_camera_connect_log("nif null");
                        runCmd("sudo ifconfig eth0 up");
                        Thread.sleep(5000);
                    } else if (!nif.isUp()) {
                        objReadfiles.write_camera_connect_log("nif down");
                        runCmd("sudo ifconfig eth0 up");
                        Thread.sleep(5000);

                    }
                } catch (SocketException ex) {
                    //Logger.getLogger(clsVideoRecord.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(clsVideoRecord.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    //Logger.getLogger(clsVideoRecord.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    rt = Runtime.getRuntime();
                    {
                        now_time = new Date(Calendar.getInstance().getTimeInMillis());

                        if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                            if (clsSharedVariables.getCanStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/cam/realmonitor?channel=1&subtype=0";
                            } else {
                                in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/cam/realmonitor?channel=1&subtype=1";
                            }
                        } else if (cam_type == clsDefines.CAM_HIKVISION) {

                            if (clsSharedVariables.getCanStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/Streaming/channels/101";
                            } else {
                                in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/Streaming/channels/102";
                            }
                        } else if (cam_type == clsDefines.CAM_FOORIR) {

                            if (clsSharedVariables.getCanStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":8554/camera";
                            } else {
                                in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":8554/camera";
                            }
                        }
                        if (camera_no == 1) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam1/" + clsSharedVariables.getOBUID() + "_1" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 2) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam2/" + clsSharedVariables.getOBUID() + "_2" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 3) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam3/" + clsSharedVariables.getOBUID() + "_3" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 4) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam4/" + clsSharedVariables.getOBUID() + "_4" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 5) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam5/" + clsSharedVariables.getOBUID() + "_5" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 6) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam6/" + clsSharedVariables.getOBUID() + "_6" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 7) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam7/" + clsSharedVariables.getOBUID() + "_7" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else if (camera_no == 8) {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam8/" + clsSharedVariables.getOBUID() + "_8" + sdfOutFileFormat.format(now_time) + ".avi ";
                        } else {
                            OutFilename = can_video_event_filepath.getAbsolutePath() + "/Cam1/" + clsSharedVariables.getOBUID() + "_1" + sdfOutFileFormat.format(now_time) + ".avi ";
                        }
                        objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "Can : Connecting");

                        try {

                            if (audio_enable == true) {
                                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg  -y -rtsp_transport tcp -i '" + in_url + "' -t " + clsSharedVariables.getCanPostRecordTime() * 60 + "  -vcodec copy " + OutFilename});
                            } else {
                                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg  -y -rtsp_transport tcp -i '" + in_url + "' -an -t " + clsSharedVariables.getCanPostRecordTime() * 60 + "  -vcodec copy " + OutFilename});
                            }

                            f = cam1_rec_proc.getClass().getDeclaredField("pid");
                            f.setAccessible(true);
                            pid = (int) f.get(cam1_rec_proc);

                            outCam1 = new OutStreamCam1Thread();
                            outCam1.start();

                            objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "Can Based   : start ");

                            cam1_rec_proc.waitFor();

                            objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "Can Based   : Disconnected");

                            break;

                        } catch (InterruptedException ex) {

                            objReadfiles.write_camera1_connect_log("InterruptedException Video Cam" + camera_no + "Can Based  : Disconnected");

                            break;

                        } catch (IOException ex) {

                            objReadfiles.write_camera1_connect_log("IOException Video Cam" + camera_no + "Can Based  : Disconnected");

                            break;

                        } catch (Exception ex) {

                            objReadfiles.write_camera1_connect_log("Exception Video Cam" + camera_no + "Can Based  : Disconnected");

                            break;

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
                                    can_rec_started = true;
                                    if (camera_no == 1) {
                                        clsSharedVariables.setCanBasedRecording1Started(false);
                                    } else if (camera_no == 2) {
                                        clsSharedVariables.setCanBasedRecording2Started(false);
                                    } else if (camera_no == 3) {
                                        clsSharedVariables.setCanBasedRecording3Started(false);
                                    } else if (camera_no == 4) {
                                        clsSharedVariables.setCanBasedRecording4Started(false);
                                    } else if (camera_no == 5) {
                                        clsSharedVariables.setCanBasedRecording5Started(false);
                                    } else if (camera_no == 6) {
                                        clsSharedVariables.setCanBasedRecording6Started(false);
                                    } else if (camera_no == 7) {
                                        clsSharedVariables.setCanBasedRecording7Started(false);
                                    } else if (camera_no == 8) {
                                        clsSharedVariables.setCanBasedRecording8Started(false);
                                    }

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

                } catch (Exception ex) {
                    can_rec_started = true;
                    if (camera_no == 1) {
                        clsSharedVariables.setCanBasedRecording1Started(false);
                    } else if (camera_no == 2) {
                        clsSharedVariables.setCanBasedRecording2Started(false);
                    } else if (camera_no == 3) {
                        clsSharedVariables.setCanBasedRecording3Started(false);
                    } else if (camera_no == 4) {
                        clsSharedVariables.setCanBasedRecording4Started(false);
                    } else if (camera_no == 5) {
                        clsSharedVariables.setCanBasedRecording5Started(false);
                    } else if (camera_no == 6) {
                        clsSharedVariables.setCanBasedRecording6Started(false);
                    } else if (camera_no == 7) {
                        clsSharedVariables.setCanBasedRecording7Started(false);
                    } else if (camera_no == 8) {
                        clsSharedVariables.setCanBasedRecording8Started(false);
                    }

                    break;
                }

            } else {
                can_rec_started = true;
                if (camera_no == 1) {
                    clsSharedVariables.setCanBasedRecording1Started(false);
                } else if (camera_no == 2) {
                    clsSharedVariables.setCanBasedRecording2Started(false);
                } else if (camera_no == 3) {
                    clsSharedVariables.setCanBasedRecording3Started(false);
                } else if (camera_no == 4) {
                    clsSharedVariables.setCanBasedRecording4Started(false);
                } else if (camera_no == 5) {
                    clsSharedVariables.setCanBasedRecording5Started(false);
                } else if (camera_no == 6) {
                    clsSharedVariables.setCanBasedRecording6Started(false);
                } else if (camera_no == 7) {
                    clsSharedVariables.setCanBasedRecording7Started(false);
                } else if (camera_no == 8) {
                    clsSharedVariables.setCanBasedRecording8Started(false);
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                can_rec_started = true;
                if (camera_no == 1) {
                    clsSharedVariables.setCanBasedRecording1Started(false);
                } else if (camera_no == 2) {
                    clsSharedVariables.setCanBasedRecording2Started(false);
                } else if (camera_no == 3) {
                    clsSharedVariables.setCanBasedRecording3Started(false);
                } else if (camera_no == 4) {
                    clsSharedVariables.setCanBasedRecording4Started(false);
                } else if (camera_no == 5) {
                    clsSharedVariables.setCanBasedRecording5Started(false);
                } else if (camera_no == 6) {
                    clsSharedVariables.setCanBasedRecording6Started(false);
                } else if (camera_no == 7) {
                    clsSharedVariables.setCanBasedRecording7Started(false);
                } else if (camera_no == 8) {
                    clsSharedVariables.setCanBasedRecording8Started(false);
                }

                break;

            }
        }
        can_rec_started = true;
        if (camera_no == 1) {
            clsSharedVariables.setCanBasedRecording1Started(false);
        } else if (camera_no == 2) {
            clsSharedVariables.setCanBasedRecording2Started(false);
        } else if (camera_no == 3) {
            clsSharedVariables.setCanBasedRecording3Started(false);
        } else if (camera_no == 4) {
            clsSharedVariables.setCanBasedRecording4Started(false);
        } else if (camera_no == 5) {
            clsSharedVariables.setCanBasedRecording5Started(false);
        } else if (camera_no == 6) {
            clsSharedVariables.setCanBasedRecording6Started(false);
        } else if (camera_no == 7) {
            clsSharedVariables.setCanBasedRecording7Started(false);
        } else if (camera_no == 8) {
            clsSharedVariables.setCanBasedRecording8Started(false);
        }

        if (outCam1 != null) {
            try {
                outCam1.interrupt();
                outCam1.stop();
                outCam1 = null;
            } catch (Exception ex) {

            }
        }
        sdfOutFileFormat = null;
        now_time = null;
        OutFilename = null;
        f = null;
        nif = null;
        objReadfiles = null;
        cam1_rec_proc = null;
        outCam1 = null;
        rt = null;
        in_url = null;
    }

    class OutStreamCam1Thread extends Thread {

        BufferedReader reader = null; // Buffered reader to read error stream of the recording process

        // Constructor
        public OutStreamCam1Thread() {
            super();
        }

        clsReadFiles objReadfiles = new clsReadFiles(); // Instance of clsReadFiles for logging

        // Run method to handle processing of the output stream
        public void run() {
            String data; // Variable to hold stream data

            Process p; // Variable to hold process reference
            int can_rec_time_inc = 0; // Counter for recording time

            try {
                // Initialize buffered reader to read error stream of the recording process
                reader = new BufferedReader(new InputStreamReader(cam1_rec_proc.getErrorStream()));

                // Continuous loop for reading error stream
                while (true) {
                    // Check if CAN recording is enabled
                    if (clsSharedVariables.getCanRecordEnabled() == true) {

                        can_rec_time_inc++; // Increment recording time counter

                        // Check if recording time exceeds the specified limit
                        if (can_rec_time_inc < clsSharedVariables.getCanPostRecordTime() * (60 * 1000) / 5) { //5 sec sleep

                            // Check if reader is ready to read
                            if (reader.ready()) {
                                data = reader.readLine(); // Read a line from the error stream

                                // Check for disconnection message
                                if (data.contains("disonnected")) {
                                    objReadfiles.write_camera1_connect_log("Cam1 rec Can Based   Disconnected" + data);

                                    // Destroy the recording process
                                    try {
                                        cam1_rec_proc.destroy();
                                    } catch (Exception ex) {
                                        objReadfiles.write_camera1_connect_log("Cam1 process Can Based   destroy" + data);
                                    }

                                    // Update shared variable based on camera number
                                    if (camera_no == 1) {
                                        clsSharedVariables.setCanBasedRecording1Started(false);
                                    } else if (camera_no == 2) {
                                        clsSharedVariables.setCanBasedRecording2Started(false);
                                    } else if (camera_no == 3) {
                                        clsSharedVariables.setCanBasedRecording3Started(false);
                                    } else if (camera_no == 4) {
                                        clsSharedVariables.setCanBasedRecording4Started(false);
                                    } else if (camera_no == 5) {
                                        clsSharedVariables.setCanBasedRecording5Started(false);
                                    } else if (camera_no == 6) {
                                        clsSharedVariables.setCanBasedRecording6Started(false);
                                    } else if (camera_no == 7) {
                                        clsSharedVariables.setCanBasedRecording7Started(false);
                                    } else if (camera_no == 8) {
                                        clsSharedVariables.setCanBasedRecording8Started(false);
                                    }

                                    break; // Exit the loop
                                }
                                // Check for failure message
                                if (data.contains("failed")) {
                                    objReadfiles.write_camera1_connect_log("Cam1 rec data Can Based  failed : Disconnected" + data);

                                    // Destroy the recording process
                                    try {
                                        cam1_rec_proc.destroy();
                                    } catch (Exception ex) {
                                        objReadfiles.write_camera1_connect_log("Cam1 process Can Based  destroy" + data);

                                    }

                                    break; // Exit the loop
                                }
                                // Check for route to host message
                                if (data.contains("route to host")) {
                                    objReadfiles.write_camera1_connect_log("Cam1 rec data Can Based  No route to host" + data);

                                    // Destroy the recording process
                                    try {
                                        cam1_rec_proc.destroy();
                                    } catch (Exception ex) {
                                        objReadfiles.write_camera1_connect_log("Cam1 process Can Based  destroy" + data);

                                    }

                                    break; // Exit the loop
                                }
                            }
                            // Sleep for 5 seconds
                            Thread.sleep(5000);

                        } else { // If recording time exceeds limit

                            // Clean up resources and exit loop
                            try {
                                cam1_rec_proc.getInputStream().close();
                                cam1_rec_proc.getOutputStream().close();
                                cam1_rec_proc.getErrorStream().close();
                                cam1_rec_proc.destroy();
                                cam1_rec_proc = null;
                                p = Runtime.getRuntime().exec("sudo kill -9 " + pid);
                                p.waitFor(5, TimeUnit.SECONDS);
                                p.destroy();
                                p = null;
                            } catch (Exception ex) {
                            }

                            break; // Exit the loop
                        }

                    } else { // If CAN recording is not enabled

                        // Clean up resources and exit loop
                        try {
                            cam1_rec_proc.getInputStream().close();
                            cam1_rec_proc.getOutputStream().close();
                            cam1_rec_proc.getErrorStream().close();
                            cam1_rec_proc.destroy();
                            if (cam1_rec_proc.isAlive()) {
                                cam1_rec_proc.destroyForcibly();
                            }
                            outCam1.interrupt();
                            outCam1.stop();
                        } catch (Exception ex) {
                            objReadfiles.write_camera1_connect_log("Cam1 process Can Based  destroy");
                        }

                        break; // Exit the loop
                    }
                }
            } catch (Exception ex) {
            } finally { // Finally block for cleanup

                // Update shared variable based on camera number
                if (camera_no == 1) {
                    clsSharedVariables.setCanBasedRecording1Started(false);
                } else if (camera_no == 2) {
                    clsSharedVariables.setCanBasedRecording2Started(false);
                } else if (camera_no == 3) {
                    clsSharedVariables.setCanBasedRecording3Started(false);
                } else if (camera_no == 4) {
                    clsSharedVariables.setCanBasedRecording4Started(false);
                } else if (camera_no == 5) {
                    clsSharedVariables.setCanBasedRecording5Started(false);
                } else if (camera_no == 6) {
                    clsSharedVariables.setCanBasedRecording6Started(false);
                } else if (camera_no == 7) {
                    clsSharedVariables.setCanBasedRecording7Started(false);
                } else if (camera_no == 8) {
                    clsSharedVariables.setCanBasedRecording8Started(false);
                }

                // Nullify references and clean up resources
                objReadfiles = null;
                reader = null;
                data = null;
                p = null;
                outCam1 = null;
                cam1_rec_proc = null;
            }
        }
    }

}
