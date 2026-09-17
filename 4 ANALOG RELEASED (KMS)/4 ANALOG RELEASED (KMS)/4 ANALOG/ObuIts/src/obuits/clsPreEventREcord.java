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
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;
import static obuits.clsDefines.ETHERNET_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.video_filepath;
import static obuits.clsSharedVariables.getCam1Enabled;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class handles pre-event recording for a specified camera. It extends the
 * Thread class to allow concurrent execution.
 *
 * @author Sumitha
 */
public class clsPreEventREcord extends Thread {

    Process cam1_rec_proc; // Process for camera recording
    OutStreamCam1Thread outCam1; // Thread to handle the output stream of the camera process
    int pid = 0; // Process ID of the camera recording process
    Runtime rt = null; // Runtime instance to execute commands
    String in_url; // URL for the camera stream
    byte camera_no = 1; // Camera number

    // Constructor to initialize the camera number
    public clsPreEventREcord(byte cam_no) {
        super();
        camera_no = cam_no;
    }

    // Method to run a command and handle exceptions
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

    // Main method for the thread
    @Override
    public void run() {

        String OutFilename;
        Field f;
        String video_path;

        File dir;
        NetworkInterface nif;
        clsReadFiles objReadfiles = new clsReadFiles();
        byte cam_type = clsSharedVariables.getCam1Type();
        boolean cam_enable = getCam1Enabled();
        boolean event_rec_enable = clsSharedVariables.getEventRecordEnabled();

        boolean camera_running = false;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
        String addr = clsDefines.CAM1_IPADDR;
        boolean audio_enable = clsSharedVariables.getCam1RecordAudioSelected();
        video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam1/";
        dir = new File(video_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Loop to continuously check the camera status and record if enabled
        while (true) {
            // Switch case to handle different cameras
            switch (camera_no) {
                case 1:
                    cam_type = clsSharedVariables.getCam1Type();
                    cam_enable = clsSharedVariables.getCam1Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    addr = clsDefines.CAM1_IPADDR;
                    camera_running = clsSharedVariables.getVideo1Connected();
                    audio_enable = clsSharedVariables.getCam1RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam1/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                case 2:
                    cam_type = clsSharedVariables.getCam2Type();
                    cam_enable = clsSharedVariables.getCam2Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    addr = clsDefines.CAM2_IPADDR;
                    camera_running = clsSharedVariables.getVideo2Connected();
                    audio_enable = clsSharedVariables.getCam2RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam2/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                case 3:
                    cam_type = clsSharedVariables.getCam3Type();
                    cam_enable = clsSharedVariables.getCam3Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    addr = clsDefines.CAM3_IPADDR;
                    camera_running = clsSharedVariables.getVideo3Connected();
                    audio_enable = clsSharedVariables.getCam3RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam3/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                case 4:
                    cam_type = clsSharedVariables.getCam4Type();
                    cam_enable = clsSharedVariables.getCam4Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    addr = clsDefines.CAM4_IPADDR;
                    camera_running = clsSharedVariables.getVideo4Connected();
                    audio_enable = clsSharedVariables.getCam4RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam4/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                case 5:
                    cam_type = clsSharedVariables.getCam5Type();
                    cam_enable = clsSharedVariables.getCam5Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    addr = clsDefines.CAM5_IPADDR;
                    camera_running = clsSharedVariables.getVideo5Connected();
                    audio_enable = clsSharedVariables.getCam5RecordAudioSelected();
                    try {
                        video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam5/";

                        dir = new File(video_path);

                        if (!dir.exists()) {

                            dir.mkdirs();
                        }
                    } catch (Exception e) {
                    }
                    break;
                case 6:
                    cam_type = clsSharedVariables.getCam6Type();
                    cam_enable = clsSharedVariables.getCam6Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    addr = clsDefines.CAM6_IPADDR;
                    camera_running = clsSharedVariables.getVideo6Connected();
                    audio_enable = clsSharedVariables.getCam6RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam6/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                case 7:
                    cam_type = clsSharedVariables.getCam7Type();
                    cam_enable = clsSharedVariables.getCam7Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    addr = clsDefines.CAM7_IPADDR;
                    camera_running = clsSharedVariables.getVideo7Connected();
                    audio_enable = clsSharedVariables.getCam7RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam7/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                case 8:
                    cam_type = clsSharedVariables.getCam8Type();
                    cam_enable = clsSharedVariables.getCam8Enabled();
                    event_rec_enable = clsSharedVariables.getEventRecordEnabled();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    addr = clsDefines.CAM8_IPADDR;
                    camera_running = clsSharedVariables.getVideo8Connected();
                    audio_enable = clsSharedVariables.getCam8RecordAudioSelected();
                    video_path = video_filepath.getAbsolutePath() + "/Videos_preevent/Cam8/";
                    dir = new File(video_path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    break;
                default:
                    break;
            }
            // If the camera is running, enabled, and event recording is enabled, proceed with recording
            if (camera_running && cam_enable && event_rec_enable == true && clsSharedVariables.getHardDriveDetected()) {

                try {
                    nif = NetworkInterface.getByName(ETHERNET_NETWORKINTERFACE_PATH);//GPRS_NETWORKINTERFACE_PATH);
                    if (nif == null) {
                        objReadfiles.write_camera_connect_log("nif null");
                        runCmd("sudo ifconfig eth0 up");
                        Thread.sleep(5000);
                    } else if (!nif.isUp()) {
                        objReadfiles.write_camera_connect_log("nif down"); // Log "nif down" event
                        runCmd("sudo ifconfig eth0 up"); // Bring up the network interface
                        Thread.sleep(5000); // Wait for 5 seconds
                    }
// Handle various exceptions but do nothing with them
                } catch (SocketException ex) {
                } catch (InterruptedException ex) {
                } catch (Exception ex) {
                }

                try {
                    rt = Runtime.getRuntime(); // Get the runtime instance

                    // Construct the RTSP URL based on camera type and stream type
                    if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                        if (clsSharedVariables.getEventStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/video/live?channel=1&subtype=0";
                        } else {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/video/live?channel=1&subtype=1";
                        }
                    }else if (cam_type == clsDefines.CAM_SPARSH) {
                        if (clsSharedVariables.getEventStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                           in_url="rtsp://" + user + ":" + pwd + "@"+  pwd + ":554/ch01.264?dev=0";           
                        } else {
                           in_url="rtsp://" + user + ":" + pwd + "@"+  pwd + ":554/ch01.264?dev=1";           
                        }
                    } else if (cam_type == clsDefines.CAM_HIKVISION) {
                        if (clsSharedVariables.getEventStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/Streaming/channels/101";
                        } else {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/Streaming/channels/102";
                        }
                    } else if (cam_type == clsDefines.CAM_FOORIR) {
                        if (clsSharedVariables.getEventStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":8554/camera";
                            //  //System.out.println("cam hikvision");
                        } else {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":8554/live/camera";
                        }
                    }else if (cam_type == clsDefines.CAM_VICON) {
                        if (clsSharedVariables.getEventStreamType() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/stream1";
                            //  //System.out.println("cam hikvision");
                        } else {
                            in_url = "rtsp://" + user + ":" + pwd + "@" + addr + ":554/stream2";
                        }
                    }

                    // Set the output filename based on the camera number
                    switch (camera_no) {
                        case 1:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            // //System.out.println(OutFilename);
                            break;
                        case 2:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                        case 3:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                        case 4:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                        case 5:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                        case 6:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                        case 7:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                        default:
                            OutFilename = video_path + "%Y%m%d%H%M%S.avi ";
                            break;
                    }

                    objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "Pre event  : Connecting"); // Log the connection attempt

                    try {
                        // Execute the FFmpeg command to record the video stream
                        if (audio_enable == true) {
                            cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg  -y -rtsp_transport tcp -i '" + in_url + "'  -c copy -map 0 -loglevel quiet  -segment_time 120 -f segment -reset_timestamps 1  -strftime 1 " + OutFilename});
                        } else {
                            cam1_rec_proc = rt.exec(new String[]{"bash", "-c", "ffmpeg  -y -rtsp_transport tcp -i '" + in_url + "'  -c copy -map 0 -an -loglevel quiet  -segment_time 30 -f segment -reset_timestamps 1  -strftime 1 " + OutFilename});
                        }

                        // Get the process ID of the FFmpeg process
                        f = cam1_rec_proc.getClass().getDeclaredField("pid");
                        f.setAccessible(true);
                        pid = (int) f.get(cam1_rec_proc);

                        outCam1 = new OutStreamCam1Thread(); // Create and start the thread to handle output stream
                        outCam1.start();

                        objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "Pre Event Based Event : start "); // Log start of recording
                        cam1_rec_proc.waitFor(); // Wait for the process to finish
                        objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "Pre Event Based Event : Disconnected"); // Log disconnection

                        outCam1.interrupt(); // Interrupt and stop the thread
                        outCam1 = null;
                    } catch (InterruptedException ex) {
                        objReadfiles.write_camera1_connect_log("InterruptedException Video Cam" + camera_no + "Pre  Event Based Event : Disconnected"); // Log interruption
                    } catch (IOException ex) {
                        objReadfiles.write_camera1_connect_log("IOException Video Cam" + camera_no + "Pre Event Based Event : Disconnected"); // Log I/O exception
                    } catch (Exception ex) {
                        objReadfiles.write_camera1_connect_log("Exception Video Cam" + camera_no + "Pre Event Based Event : Disconnected"); // Log generic exception
                    } finally {
                        try {
                            // Clean up and close streams
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
                            rt.freeMemory(); // Free up memory
                            rt.gc(); // Run garbage collector
                            rt = null;
                        }
                    }
                } catch (Exception ex) {
                }

            }
            try {
                Thread.sleep(1000); // Sleep for 1 second before the next iteration
            } catch (InterruptedException ex) {
                break; // Break the loop if interrupted
            }

        }
        if (outCam1 != null) {
            try {
                outCam1.interrupt();
                outCam1.stop();
                outCam1 = null;
            } catch (Exception ex) {
            }
        }

// Nullify references to allow garbage collection
        OutFilename = null;
        f = null;
        nif = null;
        objReadfiles = null;
        cam1_rec_proc = null;
        outCam1 = null;
        rt = null;
        in_url = null;
        dir = null;
        video_path = null;
    }

    class OutStreamCam1Thread extends Thread {

        BufferedReader reader = null;

        public OutStreamCam1Thread() {
            super();
        }
        clsReadFiles objReadfiles = new clsReadFiles();

        @Override
        public void run() {
            String data;
            Process p;
            try {
                reader = new BufferedReader(new InputStreamReader(cam1_rec_proc.getErrorStream())); // Read from the error stream
                while (true) {
                    // Check if event recording is enabled
                    if (clsSharedVariables.getEventRecordEnabled() == true) {
                        if (reader.ready()) {
                            data = reader.readLine(); // Read data from the error stream
                            if (data.contains("disonnected")) {
                                objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  rec Pre Event Disconnected" + data);

                                try {
                                    cam1_rec_proc.destroy(); // Destroy the process if disconnected
                                } catch (Exception ex) {
                                    objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  process Pre Event destroy" + data);
                                }

                                break;
                            }
                            if (data.contains("failed")) {
                                objReadfiles.write_camera1_connect_log("Cam" + camera_no + " rec data Pre Event Based Event failed : Disconnected" + data);

                                try {
                                    cam1_rec_proc.destroy(); // Destroy the process if failed
                                } catch (Exception ex) {
                                    objReadfiles.write_camera1_connect_log("Cam" + camera_no + " process Pre Event Based Event destroy" + data);
                                }

                                break;
                            }
                            if (data.contains("route to host")) {
                                objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  rec data Pre Event Based Event No route to host" + data);
                                try {
                                    cam1_rec_proc.destroy(); // Destroy the process if no route to host
                                } catch (Exception ex) {
                                    objReadfiles.write_camera1_connect_log("Cam" + camera_no + "  process Pre Event Based Event destroy" + data);
                                }

                                break;
                            }
                        }
                        Thread.sleep(5000); // Sleep for 5 seconds before checking again

                    } else {
                        try {
                            // Close streams and destroy the process
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
                        break;
                    }
                }
            } catch (Exception ex) {
            } finally {
                // Nullify references to allow garbage collection
                objReadfiles = null;
                reader = null;
                data = null;
                p = null;

                outCam1 = null;
                cam1_rec_proc = null;
            }
        }
    }

    class ExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_camera1_connect_log("clsPreEventRecord  Cam" + camera_no + " uncaughtExceptionHandler " + e.getMessage()); // Log uncaught exception
            try {
                clsPreEventREcord objVid = new clsPreEventREcord(camera_no); // Restart the recording on uncaught exception
                objVid.start();
            } catch (Exception ex) {
            }
            objReadFiles = null;
        }
    }
}
