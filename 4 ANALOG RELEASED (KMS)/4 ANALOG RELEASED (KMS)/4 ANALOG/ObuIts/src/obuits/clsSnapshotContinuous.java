/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static obuits.clsDefines.ETHERNET_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.video_filepath;
import static obuits.clsSharedVariables.getHardDriveDetected;

/**
 *
 * @author Sumitha
 */
public class clsSnapshotContinuous extends Thread {

    clsSnapshotContinuous() {
        // Constructor
    }

    /*
 * This method executes a command using Runtime.exec().
 *
 * @param cmd The command to execute.
 * @return true if the command executed successfully, false otherwise.
     */
    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            // Get the current runtime instance
            rt = Runtime.getRuntime();

            // Execute the command
            process = rt.exec(cmd);

            // Wait for the process to complete within 5 seconds
            process.waitFor(5, TimeUnit.SECONDS);

            // Return true if the command executed successfully
            return true;
        } catch (IOException e) {
            // Handle IOException
            if (rt != null) {
                rt.gc(); // Run garbage collector to free resources
            }
            return false; // Command execution failed
        } catch (Exception ex) {
            // Handle other exceptions
            if (rt != null) {
                rt.gc(); // Run garbage collector to free resources
            }
            return false; // Command execution failed
        } finally {
            try {
                // Close streams and destroy process
                if (process != null) {
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                    process = null;
                    rt = null;
                }
            } catch (IOException e) {
                // Handle IOException while closing streams
            } catch (Exception ex) {
                // Handle other exceptions while closing streams
            }
        }
    }

    @Override
    public void run() {
        // Initialization of variables and objects
        String OutFilename;
        Field f;
        NetworkInterface nif;
        clsReadFiles objReadfiles = new clsReadFiles();
        String in_url;
        Runtime rt = null;
        String path;
        Process cam_rec_proc = null;
       // clsFtpUploadData objFtpUpload = new clsFtpUploadData();
        int cam1_inc = 0;
        int cam2_inc = 0;
        int cam3_inc = 0;
        int cam4_inc = 0;
        int cam5_inc = 0;
        int cam6_inc = 0;
        int cam7_inc = 0;
        int cam8_inc = 0;
        SimpleDateFormat sdfOutFileFormat = new SimpleDateFormat("yyyyMMddHHmmss");//%Y%m%d%H%M%S
        clsOnvifUrlCamera onvifUrls = new clsOnvifUrlCamera();
        Date now_time;
        
        // Check if hard drive is detected
        if (getHardDriveDetected()) {
            // Ensure directories for snapshot storage exist
            File dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Cont");
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }

        // Main execution loop
        while (true) {
            if (!getHardDriveDetected()) {
                try {
                    // If hard drive is not detected, sleep for 5 seconds
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(clsSnapshotContinuous.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
               
                try {
                    // Check network interface status
                    nif = NetworkInterface.getByName(ETHERNET_NETWORKINTERFACE_PATH);
                    if (nif == null) {
                        // Log and attempt to bring up the network interface
                        objReadfiles.write_camera_connect_log("nif null");
                        runCmd("sudo ifconfig eth0 up");
                        Thread.sleep(5000);
                    } else if (!nif.isUp()) {
                        // Log and attempt to bring up the network interface
                        objReadfiles.write_camera_connect_log("nif down");
                        runCmd("sudo ifconfig eth0 up");
                        Thread.sleep(5000);
                    }
                } catch (SocketException ex) {
                    // Handle SocketException
                    // Logger.getLogger(clsVideoRecord.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    // Handle InterruptedException
                    // Logger.getLogger(clsVideoRecord.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    // Handle other exceptions
                    // Logger.getLogger(clsVideoRecord.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    // Get current time
                    now_time = new Date(Calendar.getInstance().getTimeInMillis());

                    // Check if continuous snapshot capture is enabled for Cam1
                    if (clsSharedVariables.getSnapShotEnableCam1() && clsSharedVariables.getSnapShotContEnableCam1()) {
                        // Reset increment counter if it exceeds interval
                        if (cam1_inc >= clsSharedVariables.getSnapShotContIntervalCam1()) {
                            cam1_inc = 0;
                        }
                        // Capture snapshot if it's time
                        if (cam1_inc == 0) {
                            // Determine snapshot URL and filename based on settings
                            if (clsSharedVariables.getSnapShotContStreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam1_snapshot_main_url();
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Cont/" + clsSharedVariables.getOBUID() + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam1_snapshot_sub_url();
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Cont/" + clsSharedVariables.getOBUID() + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            // Construct ffmpeg command to capture snapshot
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;

                            // Log snapshot connection attempt
                            objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Cont : Connecting " + path);
                            try {
                                // Execute ffmpeg command
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                // Get process ID using reflection
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);

                                // Wait for snapshot process to complete
                                cam_rec_proc.waitFor();

                                // Log disconnection after snapshot
                                objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Based Cont Snapshot : Disconnected");
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam1() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {
                                // Handle exceptions during snapshot execution
                            } finally {
                                try {
                                    // Ensure proper closure of process streams and destroy process
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        // Forcefully destroy process if it's still alive
                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;
                                    }
                                } catch (IOException ex) {
                                    // Handle IOException while closing streams
                                } finally {
                                    // Clean up variables and memory
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }
                        }
                        // Increment snapshot counter
                        cam1_inc++;
                    }

                    // Check if continuous snapshot capture is enabled for Cam2
                    if (clsSharedVariables.getSnapShotEnableCam2() && clsSharedVariables.getSnapShotContEnableCam2()) {
                        
                        // Reset increment counter if it exceeds the interval
                        if (cam2_inc >= clsSharedVariables.getSnapShotContIntervalCam2()) {
                            cam2_inc = 0;
                        }
                       
                        // Capture snapshot if it's time
                        if (cam2_inc == 0) {
                            
                            // Determine snapshot URL and filename based on settings
                            if (clsSharedVariables.getSnapShotContStreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                               
                                in_url = onvifUrls.get_cam2_snapshot_main_url();
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Cont/" + clsSharedVariables.getOBUID() + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                               
                                in_url = onvifUrls.get_cam2_snapshot_sub_url();
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Cont/" + clsSharedVariables.getOBUID() + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            // Construct ffmpeg command to capture snapshot
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;

                            // Log snapshot connection attempt
                            objReadfiles.write_camera2_connect_log("Video Cam2 Snapshot Cont : Connecting " + path);
                            try {
                                // Execute ffmpeg command
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                // Get process ID using reflection
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);

                                // Wait for snapshot process to complete
                                cam_rec_proc.waitFor();

                                // Log disconnection after snapshot
                                objReadfiles.write_camera2_connect_log("Video Cam2 Snapshot Based Cont Snapshot : Disconnected");
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam2() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {
                                // Handle exceptions during snapshot execution
                            } finally {
                                try {
                                    // Ensure proper closure of process streams and destroy process
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        // Forcefully destroy process if it's still alive
                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;
                                    }
                                } catch (IOException ex) {
                                    // Handle IOException while closing streams
                                } finally {
                                    // Clean up variables and memory
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }
                        }
                        // Increment snapshot counter
                        cam2_inc++;
                    }

                    // Check if continuous snapshot capture is enabled for Cam3
                    if (clsSharedVariables.getSnapShotEnableCam3() && clsSharedVariables.getSnapShotContEnableCam3()) {
                        // Reset increment counter if it exceeds the interval
                        if (cam3_inc >= clsSharedVariables.getSnapShotContIntervalCam3()) {
                            cam3_inc = 0;
                        }

                        // Capture snapshot if it's time
                        if (cam3_inc == 0) {
                            // Determine snapshot URL and filename based on settings
                            if (clsSharedVariables.getSnapShotContStreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam3_snapshot_main_url();
                                // in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM3_IPADDR + "/ISAPI/Streaming/channels/1/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Cont/" + clsSharedVariables.getOBUID() + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam3_snapshot_sub_url();
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Cont/" + clsSharedVariables.getOBUID() + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            // Construct ffmpeg command to capture snapshot
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;

                            // Log snapshot connection attempt
                            objReadfiles.write_camera3_connect_log("Video Cam3 Snapshot Cont : Connecting " + path);
                            try {
                                // Execute ffmpeg command
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                // Get process ID using reflection
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);

                                // Wait for snapshot process to complete
                                cam_rec_proc.waitFor();

                                // Log disconnection after snapshot
                                objReadfiles.write_camera3_connect_log("Video Cam3 Snapshot Based Cont Snapshot : Disconnected");

                                // Upload snapshot via FTP if enabled
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam3() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {
                                // Handle exceptions during snapshot execution
                            } finally {
                                try {
                                    // Ensure proper closure of process streams and destroy process
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        // Forcefully destroy process if it's still alive
                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;
                                    }
                                } catch (Exception ex) {
                                    // Handle exceptions while closing streams
                                } finally {
                                    // Clean up variables and memory
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }
                        }
                        // Increment snapshot counter
                        cam3_inc++;
                    }

                    // Check if continuous snapshot capture is enabled for Cam4
                    if (clsSharedVariables.getSnapShotEnableCam4() && clsSharedVariables.getSnapShotContEnableCam4()) {
                        // Reset increment counter if it exceeds the interval
                        if (cam4_inc >= clsSharedVariables.getSnapShotContIntervalCam4()) {
                            cam4_inc = 0;
                        }

                        // Capture snapshot if it's time
                        if (cam4_inc == 0) {
                            // Determine snapshot URL and filename based on settings
                            if (clsSharedVariables.getSnapShotContStreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam4_snapshot_main_url();
                                // Alternative URL (commented out)
                                // in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM4_IPADDR + "/ISAPI/Streaming/channels/1/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Cont/" + clsSharedVariables.getOBUID() + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam4_snapshot_sub_url();
                                // Alternative URL (commented out)
                                // in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM4_IPADDR + "/ISAPI/Streaming/channels/2/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Cont/" + clsSharedVariables.getOBUID() + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            // Construct ffmpeg command to capture snapshot
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;

                            // Log snapshot connection attempt
                            objReadfiles.write_camera4_connect_log("Video Cam4 Snapshot Cont : Connecting " + path);
                            try {
                                // Execute ffmpeg command
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                // Get process ID using reflection
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);

                                // Wait for snapshot process to complete
                                cam_rec_proc.waitFor();

                                // Log disconnection after snapshot
                                objReadfiles.write_camera4_connect_log("Video Cam4 Snapshot Based Cont Snapshot : Disconnected");

                                // Upload snapshot via FTP if enabled
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam4() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {
                                // Handle exceptions during snapshot execution
                            } finally {
                                try {
                                    // Ensure proper closure of process streams and destroy process
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        // Forcefully destroy process if it's still alive
                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;
                                    }
                                } catch (Exception ex) {
                                    // Handle exceptions while closing streams
                                } finally {
                                    // Clean up variables and memory
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }
                        }
                        // Increment snapshot counter
                        cam4_inc++;
                    }

                    if (clsSharedVariables.getSnapShotEnableCam5() && clsSharedVariables.getSnapShotContEnableCam5()) {
                        if (cam5_inc >= clsSharedVariables.getSnapShotContIntervalCam5()) {
                            cam5_inc = 0;
                        }
                        if (cam5_inc == 0) {
                            if (clsSharedVariables.getSnapShotContStreamCam5() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam5_snapshot_main_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM5_IPADDR + "/ISAPI/Streaming/channels/1/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Cont/" + clsSharedVariables.getOBUID() + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam5_snapshot_sub_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM5_IPADDR + "/ISAPI/Streaming/channels/5/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Cont/" + clsSharedVariables.getOBUID() + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera_connect_log("Video Cam5 Snapshot Cont : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
                                objReadfiles.write_camera_connect_log("Video Cam5 Snapshot Based Cont Snapshot : Disconnected");
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam5() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {

                            } finally {
                                try {
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;

                                    }
                                } catch (Exception ex) {

                                } finally {
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }

                        }
                        cam5_inc++;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam6() && clsSharedVariables.getSnapShotContEnableCam6()) {
                        if (cam6_inc >= clsSharedVariables.getSnapShotContIntervalCam6()) {
                            cam6_inc = 0;
                        }
                        if (cam6_inc == 0) {
                            if (clsSharedVariables.getSnapShotContStreamCam6() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam6_snapshot_main_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM6_IPADDR + "/ISAPI/Streaming/channels/1/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Cont/" + clsSharedVariables.getOBUID() + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {

                                in_url = onvifUrls.get_cam6_snapshot_sub_url();
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Cont/" + clsSharedVariables.getOBUID() + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera_connect_log("Video Cam6 Snapshot Cont : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
                                objReadfiles.write_camera_connect_log("Video Cam6 Snapshot Based Cont Snapshot : Disconnected");
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam6() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {
                            } finally {
                                try {
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;

                                    }
                                } catch (Exception ex) {

                                } finally {
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }

                        }
                        cam6_inc++;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam7() && clsSharedVariables.getSnapShotContEnableCam7()) {
                        if (cam7_inc >= clsSharedVariables.getSnapShotContIntervalCam7()) {
                            cam7_inc = 0;
                        }
                        if (cam7_inc == 0) {
                            if (clsSharedVariables.getSnapShotContStreamCam7() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {

                                in_url = onvifUrls.get_cam7_snapshot_main_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM7_IPADDR + "/ISAPI/Streaming/channels/1/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Cont/" + clsSharedVariables.getOBUID() + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {

                                in_url = onvifUrls.get_cam7_snapshot_sub_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM7_IPADDR + "/ISAPI/Streaming/channels/7/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Cont/" + clsSharedVariables.getOBUID() + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera_connect_log("Video Cam7 Snapshot Cont : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
                                objReadfiles.write_camera_connect_log("Video Cam7 Snapshot Based Cont Snapshot : Disconnected");
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam7() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {

                            } finally {
                                try {
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;

                                    }
                                } catch (Exception ex) {

                                } finally {
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }

                        }
                        cam7_inc++;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam8() && clsSharedVariables.getSnapShotContEnableCam8()) {
                        if (cam8_inc >= clsSharedVariables.getSnapShotContIntervalCam8()) {
                            cam8_inc = 0;
                        }
                        if (cam8_inc == 0) {
                            if (clsSharedVariables.getSnapShotContStreamCam8() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam8_snapshot_main_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM8_IPADDR + "/ISAPI/Streaming/channels/1/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Cont/" + clsSharedVariables.getOBUID() + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam8_snapshot_sub_url();
                                //in_url = "http://"+clsDefines.camera_username+":"+clsDefines.camera_pwd+"@" + clsDefines.CAM8_IPADDR + "/ISAPI/Streaming/channels/8/picture";
                                OutFilename = video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Cont/" + clsSharedVariables.getOBUID() + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera_connect_log("Video Cam8 Snapshot Cont : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
                                objReadfiles.write_camera_connect_log("Video Cam8 Snapshot Based Cont Snapshot : Disconnected");
//                                if (clsSharedVariables.getSnapShotContFtpUploadEnableCam8() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                            } catch (InterruptedException | IOException | NoSuchFieldException | SecurityException ex) {

                            } finally {
                                try {
                                    if (cam_rec_proc != null) {
                                        cam_rec_proc.getInputStream().close();
                                        cam_rec_proc.getOutputStream().close();
                                        cam_rec_proc.getErrorStream().close();
                                        cam_rec_proc.destroy();

                                        if (cam_rec_proc.isAlive()) {
                                            cam_rec_proc.destroyForcibly();
                                        }
                                        cam_rec_proc = null;

                                    }
                                } catch (Exception ex) {

                                } finally {
                                    cam_rec_proc = null;
                                    in_url = null;
                                    rt.freeMemory();
                                    rt.gc();
                                    rt = null;
                                }
                            }

                        }
                        cam8_inc++;
                    }
                } catch (Exception ex) {
                }
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {

                }

            }
        }
    }

}
