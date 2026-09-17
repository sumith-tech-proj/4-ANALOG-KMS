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
import static obuits.clsDefines.ETHERNET_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.main_video_filepath;
import static obuits.clsDefines.video_filepath;
import static obuits.clsSharedVariables.getHardDriveDetected;

/*
*@author sumitha
 */
//This class represents a snapshot of digital inputs.
public class clsSnapshotDigInputs extends Thread {

    byte dig_input;

    /**
     * Constructor to initialize the digital input snapshot.
     *
     * @param no The digital input value to capture.
     */
    clsSnapshotDigInputs(byte no) {
        dig_input = no;
    }

    /**
     * Executes a command and waits for completion.
     *
     * @param cmd The command to execute.
     * @return true if the command executed successfully, false otherwise.
     */
    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(5, TimeUnit.SECONDS);
            return true;
        } catch (IOException e) {
            // Handle IO exceptions
            if (rt != null) {
                rt.gc(); // Clean up resources
            }
            return false;
        } catch (Exception ex) {
            // Handle other exceptions
            if (rt != null) {
                rt.gc(); // Clean up resources
            }
            return false;
        } finally {
            // Ensure resources are cleaned up
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
                // Handle IO exceptions during resource cleanup
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void run() {
        String OutFilename;
        Field f;
        NetworkInterface nif;
        clsReadFiles objReadfiles = new clsReadFiles();
        String in_url;
        Runtime rt = null;
        String path;
        Process cam_rec_proc = null;
        int cam1_inc = 0;
        int cam2_inc = 0;
        int cam3_inc = 0;
        int cam4_inc = 0;
        int cam5_inc = 0;
        int cam6_inc = 0;
        int cam7_inc = 0;
        int cam8_inc = 0;
        SimpleDateFormat sdfOutFileFormat = new SimpleDateFormat("yyyyMMddHHmmss");//%Y%m%d%H%M%S
        Date now_time;
        File dir;
        String snap_path1;
        String snap_path2;
        String snap_path3;
        String snap_path4;
        String snap_path5 = null;
        String snap_path6;
        String snap_path7;
        String snap_path8;
        boolean cam1_completed = false;
        boolean cam2_completed = false;
        boolean cam3_completed = false;
        boolean cam4_completed = false;
        boolean cam5_completed = false;
        boolean cam6_completed = false;
        boolean cam7_completed = false;
        boolean cam8_completed = false;
        clsOnvifUrlCamera onvifUrls = new clsOnvifUrlCamera();
     //   clsFtpUploadData objFtpUpload = new clsFtpUploadData();
        if (getHardDriveDetected()) {
            if (dig_input == 1) {
                snap_path1 = video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig1/";
                snap_path2 = video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig1/";
                snap_path3 = video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig1/";
                snap_path4 = video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig1/";
                snap_path5 = video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig1/";
                snap_path6 = video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig1/";
                snap_path7 = video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig1/";
                snap_path8 = video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig1/";
            } else if (dig_input == 2) {
                snap_path1 = video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig2/";
                snap_path2 = video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig2/";
                snap_path3 = video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig2/";
                snap_path4 = video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig2/";
                snap_path5 = video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig2/";
                snap_path6 = video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig2/";
                snap_path7 = video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig2/";
                snap_path8 = video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig2/";
            } else if (dig_input == 3) {
                snap_path1 = video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig3/";
                snap_path2 = video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig3/";
                snap_path3 = video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig3/";
                snap_path4 = video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig3/";
                snap_path5 = video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig3/";
                snap_path6 = video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig3/";
                snap_path7 = video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig3/";
                snap_path8 = video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig3/";
            } else {
                snap_path1 = video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig4/";
                snap_path2 = video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig4/";
                snap_path3 = video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig4/";
                snap_path4 = video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig4/";
                snap_path5 = video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig4/";
                snap_path6 = video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig4/";
                snap_path7 = video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig4/";
                snap_path8 = video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig4/";
            }

            dir = new File(snap_path1);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path2);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path3);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path4);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path5);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path6);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path7);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path8);
            if (!dir.exists()) {
                dir.mkdirs();
            }

        } else {
            if (dig_input == 1) {
                snap_path1 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig1/";
                snap_path2 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig1/";
                snap_path3 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig1/";
                snap_path4 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig1/";
                snap_path5 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig1/";
                snap_path6 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig1/";
                snap_path7 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig1/";
                snap_path8 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig1/";
            } else if (dig_input == 2) {
                snap_path1 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig2/";
                snap_path2 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig2/";
                snap_path3 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig2/";
                snap_path4 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig2/";
                snap_path5 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig2/";
                snap_path6 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig2/";
                snap_path7 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig2/";
                snap_path8 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig2/";
            } else if (dig_input == 3) {
                snap_path1 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig3/";
                snap_path2 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig3/";
                snap_path3 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig3/";
                snap_path4 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig3/";
                snap_path5 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig3/";
                snap_path6 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig3/";
                snap_path7 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig3/";
                snap_path8 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig3/";
            } else {
                snap_path1 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam1/Dig4/";
                snap_path2 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam2/Dig4/";
                snap_path3 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam3/Dig4/";
                snap_path4 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam4/Dig4/";
                snap_path5 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam5/Dig4/";
                snap_path6 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam6/Dig4/";
                snap_path7 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam7/Dig4/";
                snap_path8 = main_video_filepath.getAbsolutePath() + "/Snapshot/Cam8/Dig4/";
            }

            dir = new File(snap_path1);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path2);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path3);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path4);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path5);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path6);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path7);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(snap_path8);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        while (true) {
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
            } catch (InterruptedException ex) {
            } catch (Exception ex) {
            }
            try {
                now_time = new Date(Calendar.getInstance().getTimeInMillis());
                rt = Runtime.getRuntime();
                if (dig_input == 1) {
                    if (clsSharedVariables.getSnapShotEnableCam1() && clsSharedVariables.getSnapShotDig1EnableCam1()) {
                        if (cam1_inc >= clsSharedVariables.getSnapShotDig1IntervalCam1() && cam1_completed == false) {
                            cam1_inc = 0;
                            cam1_completed = true;
                        }
                        if (cam1_completed == false) {
                            cam1_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam1_snapshot_main_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam1_snapshot_sub_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp1  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
                                ////System.out.println("ftp snapshots");
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam1() == true) {
//                                    //////////System.out.println("getSnapShotDig1FtpUploadEnableCam1" + clsSharedVariables.getSnapShotDig1FtpUploadEnableCam1());
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    ////System.out.println("OutFilename" +OutFilename);
//                                    ////System.out.println("ftp snapshots added");
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp1  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam1_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam2() && clsSharedVariables.getSnapShotDig1EnableCam2()) {
                        if (cam2_inc >= clsSharedVariables.getSnapShotDig1IntervalCam2() && cam2_completed == false) {
                            cam2_inc = 0;
                            cam2_completed = true;
                        }
                        if (cam2_completed == false) {
                            cam2_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam2_snapshot_main_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam2_snapshot_sub_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp1  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam2() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp1 : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam2_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam3() && clsSharedVariables.getSnapShotDig1EnableCam3()) {
                        if (cam3_inc >= clsSharedVariables.getSnapShotDig1IntervalCam3() && cam3_completed == false) {
                            cam3_inc = 0;
                            cam3_completed = true;
                        }
                        if (cam3_completed == false) {
                            cam3_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam3_snapshot_main_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam3_snapshot_sub_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp1 " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam3() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera3_connect_log("Video Cam3 Snapshot Dig inp1 : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam3_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam4() && clsSharedVariables.getSnapShotDig1EnableCam4()) {
                        if (cam4_inc >= clsSharedVariables.getSnapShotDig1IntervalCam4() && cam4_completed == false) {
                            cam4_inc = 0;
                            cam4_completed = true;
                        }
                        if (cam4_completed == false) {
                            cam4_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam4_snapshot_main_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam4_snapshot_sub_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam4 Snapshot Dig inp1  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam4() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera4_connect_log("Video Cam4 Snapshot Dig inp1  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam4_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam5() && clsSharedVariables.getSnapShotDig1EnableCam5()) {
                        if (cam5_inc >= clsSharedVariables.getSnapShotDig1IntervalCam5() && cam5_completed == false) {
                            cam5_inc = 0;
                            cam5_completed = true;
                        }
                        if (cam5_completed == false) {
                            cam5_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam5() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam5_snapshot_main_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam5_snapshot_sub_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp1  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam5() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp1  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam5_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam6() && clsSharedVariables.getSnapShotDig1EnableCam6()) {
                        if (cam6_inc >= clsSharedVariables.getSnapShotDig1IntervalCam6() && cam6_completed == false) {
                            cam6_inc = 0;
                            cam6_completed = true;
                        }
                        if (cam6_completed == false) {
                            cam6_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam6() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam6_snapshot_main_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam6_snapshot_sub_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam6 Snapshot Dig inp1  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam6() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera2_connect_log("Video Cam6 Snapshot Dig inp1  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam6_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam7() && clsSharedVariables.getSnapShotDig1EnableCam7()) {
                        if (cam7_inc >= clsSharedVariables.getSnapShotDig1IntervalCam7() && cam7_completed == false) {
                            cam7_inc = 0;
                            cam7_completed = true;
                        }
                        if (cam7_completed == false) {
                            cam7_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam7() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam7_snapshot_main_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam7_snapshot_sub_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam7 Snapshot Dig inp1  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam7() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera3_connect_log("Video Cam7 Snapshot Dig inp1  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam7_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam8() && clsSharedVariables.getSnapShotDig1EnableCam8()) {
                        if (cam8_inc >= clsSharedVariables.getSnapShotDig1IntervalCam8() && cam8_completed == false) {
                            cam8_inc = 0;
                            cam8_completed = true;
                        }
                        if (cam8_completed == false) {
                            cam8_inc++;
                            if (clsSharedVariables.getSnapShotDig1StreamCam8() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam8_snapshot_main_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam8_snapshot_sub_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam8 Snapshot Dig inp1  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam8() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera4_connect_log("Video Cam8 Snapshot Dig inp1  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam8_completed = true;
                    }
                } //digital input 2
                else if (dig_input == 2) {  //else
                    if (clsSharedVariables.getSnapShotEnableCam1() && clsSharedVariables.getSnapShotDig2EnableCam1()) {
                        if (cam1_inc >= clsSharedVariables.getSnapShotDig2IntervalCam1() && cam1_completed == false) {
                            cam1_inc = 0;
                            cam1_completed = true;
                        }
                        if (cam1_completed == false) {
                            //   ////////////System.out.println("cam1_completed   : " + cam1_completed);
                            cam1_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam1_snapshot_main_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam1_snapshot_sub_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp2  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam1() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp2  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam1_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam2() && clsSharedVariables.getSnapShotDig2EnableCam2()) {
                        if (cam2_inc >= clsSharedVariables.getSnapShotDig2IntervalCam2() && cam2_completed == false) {
                            cam2_inc = 0;
                            cam2_completed = true;
                        }
                        if (cam2_completed == false) {
                            cam2_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam2_snapshot_main_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam2_snapshot_sub_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp2  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam2() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp2 : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam2_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam3() && clsSharedVariables.getSnapShotDig2EnableCam3()) {
                        if (cam3_inc >= clsSharedVariables.getSnapShotDig2IntervalCam3() && cam3_completed == false) {
                            cam3_inc = 0;
                            cam3_completed = true;
                        }
                        if (cam3_completed == false) {
                            cam3_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam3_snapshot_main_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam3_snapshot_sub_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp2 " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam3() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp2 : Disconnected");

                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam3_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam4() && clsSharedVariables.getSnapShotDig2EnableCam4()) {
                        if (cam4_inc >= clsSharedVariables.getSnapShotDig2IntervalCam4() && cam4_completed == false) {
                            cam4_inc = 0;
                            cam4_completed = true;
                        }
                        if (cam4_completed == false) {
                            cam4_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam4_snapshot_main_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {

                                in_url = onvifUrls.get_cam4_snapshot_sub_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam4 Snapshot Dig inp2  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam4() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam34 Snapshot Dig inp2  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam4_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam5() && clsSharedVariables.getSnapShotDig2EnableCam5()) {
                        if (cam5_inc >= clsSharedVariables.getSnapShotDig2IntervalCam5() && cam5_completed == false) {
                            cam5_inc = 0;
                            cam5_completed = true;
                        }
                        if (cam5_completed == false) {
                            cam5_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam5() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam5_snapshot_main_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam5_snapshot_sub_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp2  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam5() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp2  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam5_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam6() && clsSharedVariables.getSnapShotDig2EnableCam6()) {
                        if (cam6_inc >= clsSharedVariables.getSnapShotDig2IntervalCam6() && cam6_completed == false) {
                            cam6_inc = 0;
                            cam6_completed = true;
                        }
                        if (cam6_completed == false) {
                            cam6_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam6() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam6_snapshot_main_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam6_snapshot_sub_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam6 Snapshot Dig inp2  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam6() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam36 Snapshot Dig inp2  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam6_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam7() && clsSharedVariables.getSnapShotDig2EnableCam7()) {
                        if (cam7_inc >= clsSharedVariables.getSnapShotDig2IntervalCam7() && cam7_completed == false) {
                            cam7_inc = 0;
                            cam7_completed = true;
                        }
                        if (cam7_completed == false) {
                            cam7_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam7() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam7_snapshot_main_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam7_snapshot_sub_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam7 Snapshot Dig inp2  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam7() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam37 Snapshot Dig inp2  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam7_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam8() && clsSharedVariables.getSnapShotDig2EnableCam8()) {
                        if (cam8_inc >= clsSharedVariables.getSnapShotDig2IntervalCam8() && cam8_completed == false) {
                            cam8_inc = 0;
                            cam8_completed = true;
                        }
                        if (cam8_completed == false) {
                            cam8_inc++;
                            if (clsSharedVariables.getSnapShotDig2StreamCam8() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam8_snapshot_main_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam8_snapshot_sub_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam8 Snapshot Dig inp2  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam8() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam38 Snapshot Dig inp2  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam8_completed = true;
                    }
                } //digital input 3
                else if (dig_input == 3) { //else
                    if (clsSharedVariables.getSnapShotEnableCam1() && clsSharedVariables.getSnapShotDig3EnableCam1()) {
                        if (cam1_inc >= clsSharedVariables.getSnapShotDig3IntervalCam1() && cam1_completed == false) {
                            cam1_inc = 0;
                            cam1_completed = true;
                        }
                        if (cam1_completed == false) {
                            // ////////////System.out.println("cam1_completed  dig_input 3 : " + cam1_completed);
                            cam1_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam1_snapshot_main_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam1_snapshot_sub_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp3  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam1() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp3  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam1_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam2() && clsSharedVariables.getSnapShotDig3EnableCam2()) {
                        if (cam2_inc >= clsSharedVariables.getSnapShotDig3IntervalCam2() && cam2_completed == false) {
                            cam2_inc = 0;
                            cam2_completed = true;
                        }
                        if (cam2_completed == false) {
                            cam2_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam2_snapshot_main_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam2_snapshot_sub_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp3  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam2() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp3 : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam2_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam3() && clsSharedVariables.getSnapShotDig3EnableCam3()) {
                        if (cam3_inc >= clsSharedVariables.getSnapShotDig3IntervalCam3() && cam3_completed == false) {
                            cam3_inc = 0;
                            cam3_completed = true;
                        }
                        if (cam3_completed == false) {
                            cam3_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam3_snapshot_main_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam3_snapshot_sub_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp3 " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam3() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp3 : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam3_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam4() && clsSharedVariables.getSnapShotDig3EnableCam4()) {
                        if (cam4_inc >= clsSharedVariables.getSnapShotDig3IntervalCam4() && cam4_completed == false) {
                            cam4_inc = 0;
                            cam4_completed = true;
                        }
                        if (cam4_completed == false) {
                            cam4_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam4_snapshot_main_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam4_snapshot_sub_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam4 Snapshot Dig inp3  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam4() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam4 Snapshot Dig inp3  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam4_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam5() && clsSharedVariables.getSnapShotDig3EnableCam5()) {
                        if (cam5_inc >= clsSharedVariables.getSnapShotDig3IntervalCam5() && cam5_completed == false) {
                            cam5_inc = 0;
                            cam5_completed = true;
                        }
                        if (cam5_completed == false) {
                            cam5_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam5() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam5_snapshot_main_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam5_snapshot_sub_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp3  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam5() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp3  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam5_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam6() && clsSharedVariables.getSnapShotDig3EnableCam6()) {
                        if (cam6_inc >= clsSharedVariables.getSnapShotDig3IntervalCam6() && cam6_completed == false) {
                            cam6_inc = 0;
                            cam6_completed = true;
                        }
                        if (cam6_completed == false) {
                            cam6_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam6() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam6_snapshot_main_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam6_snapshot_sub_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam6 Snapshot Dig inp3  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam6() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam6 Snapshot Dig inp3  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam6_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam7() && clsSharedVariables.getSnapShotDig3EnableCam7()) {
                        if (cam7_inc >= clsSharedVariables.getSnapShotDig3IntervalCam7() && cam7_completed == false) {
                            cam7_inc = 0;
                            cam7_completed = true;
                        }
                        if (cam7_completed == false) {
                            cam7_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam7() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam7_snapshot_main_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam7_snapshot_sub_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam7 Snapshot Dig inp3  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam8() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam7 Snapshot Dig inp3  : Disconnected");

                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam7_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam8() && clsSharedVariables.getSnapShotDig3EnableCam8()) {
                        if (cam8_inc >= clsSharedVariables.getSnapShotDig3IntervalCam8() && cam8_completed == false) {
                            cam8_inc = 0;
                            cam8_completed = true;
                        }
                        if (cam8_completed == false) {
                            cam8_inc++;
                            if (clsSharedVariables.getSnapShotDig3StreamCam8() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam8_snapshot_main_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam8_snapshot_sub_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam8 Snapshot Dig inp3  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam1() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam8 Snapshot Dig inp3  : Disconnected");

                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam8_completed = true;
                    }
                } //digital input 4
                else if (dig_input == 4) { // else
                    if (clsSharedVariables.getSnapShotEnableCam1() && clsSharedVariables.getSnapShotDig4EnableCam1()) {
                        if (cam1_inc >= clsSharedVariables.getSnapShotDig4IntervalCam1() && cam1_completed == false) {
                            cam1_inc = 0;
                            cam1_completed = true;
                        }
                        if (cam1_completed == false) {
                            cam1_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam1_snapshot_main_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam1_snapshot_sub_url();
                                OutFilename = snap_path1 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_1" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp4  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam1() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot Dig inp4  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam1_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam2() && clsSharedVariables.getSnapShotDig4EnableCam2()) {
                        if (cam2_inc >= clsSharedVariables.getSnapShotDig4IntervalCam2() && cam2_completed == false) {
                            cam2_inc = 0;
                            cam2_completed = true;
                        }
                        if (cam2_completed == false) {
                            cam2_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam2_snapshot_main_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam2_snapshot_sub_url();
                                OutFilename = snap_path2 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_2" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp4  " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam2() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam2 Snapshot Dig inp4 : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam2_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam3() && clsSharedVariables.getSnapShotDig4EnableCam3()) {
                        if (cam3_inc >= clsSharedVariables.getSnapShotDig4IntervalCam3() && cam3_completed == false) {
                            cam3_inc = 0;
                            cam3_completed = true;
                        }
                        if (cam3_completed == false) {
                            cam3_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam3_snapshot_main_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam3_snapshot_sub_url();
                                OutFilename = snap_path3 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_3" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp4 " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam3() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam3 Snapshot Dig inp4 : Disconnected");

                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam3_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam4() && clsSharedVariables.getSnapShotDig4EnableCam4()) {
                        // ////////////System.out.println("getSnapShotEnableCam4   : " + dig_input);
                        if (cam4_inc >= clsSharedVariables.getSnapShotDig4IntervalCam4() && cam4_completed == false) {
                            cam4_inc = 0;
                            cam4_completed = true;
                        }
                        if (cam4_completed == false) {
                            cam4_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam4_snapshot_main_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam4_snapshot_sub_url();
                                OutFilename = snap_path4 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_4" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            ////////////System.out.println("cam2  dig_input 4 : " + in_url + "OutFilename  " + OutFilename);

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam4 Snapshot Dig inp4 " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});

                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam4() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam4 Snapshot Dig inp4 : Disconnected");

                            } catch (InterruptedException ex) {
                                // ////////////System.out.println("cam2  dig_input 4 : " + ex + "OutFilename  " + ex);
                            } catch (IOException ex) {
                                ////////////System.out.println("cam2  dig_input 4 : " + ex + "OutFilename  " + ex);
                            } catch (Exception ex) {
                                //  ////////////System.out.println("cam2  dig_input 4 : " + ex + "OutFilename  " + ex);
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
                    } else {
                        cam4_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam5() && clsSharedVariables.getSnapShotDig4EnableCam5()) {
                        if (cam5_inc >= clsSharedVariables.getSnapShotDig4IntervalCam5() && cam5_completed == false) {
                            cam5_inc = 0;
                            cam5_completed = true;
                        }
                        if (cam5_completed == false) {
                            cam5_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam5() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam5_snapshot_main_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam5_snapshot_sub_url();
                                OutFilename = snap_path5 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_5" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }

                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp4 : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam5() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam5 Snapshot Dig inp4  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam5_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam6() && clsSharedVariables.getSnapShotDig4EnableCam6()) {

                        if (cam6_inc >= clsSharedVariables.getSnapShotDig4IntervalCam6() && cam6_completed == false) {
                            cam6_inc = 0;
                            cam6_completed = true;
                        }
                        if (cam6_completed == false) {
                            cam6_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam6() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam6_snapshot_main_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam6_snapshot_sub_url();
                                OutFilename = snap_path6 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_6" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam6 Snapshot Dig inp4 : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam6() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam6 Snapshot Dig inp4  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam6_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam7() && clsSharedVariables.getSnapShotDig4EnableCam7()) {

                        if (cam7_inc >= clsSharedVariables.getSnapShotDig4IntervalCam7() && cam7_completed == false) {
                            cam7_inc = 0;
                            cam7_completed = true;
                        }
                        if (cam7_completed == false) {
                            cam7_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam7() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam7_snapshot_main_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam7_snapshot_sub_url();
                                OutFilename = snap_path7 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_7" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam7 Snapshot Dig inp4  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam7() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam7 Snapshot Dig inp4  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (Exception ex) {
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
                    } else {
                        cam7_completed = true;
                    }
                    if (clsSharedVariables.getSnapShotEnableCam8() && clsSharedVariables.getSnapShotDig4EnableCam8()) {

                        if (cam8_inc >= clsSharedVariables.getSnapShotDig4IntervalCam8() && cam8_completed == false) {
                            cam8_inc = 0;
                            cam8_completed = true;
                        }
                        if (cam8_completed == false) {
                            cam8_inc++;
                            if (clsSharedVariables.getSnapShotDig4StreamCam8() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                                in_url = onvifUrls.get_cam8_snapshot_main_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            } else {
                                in_url = onvifUrls.get_cam8_snapshot_sub_url();
                                OutFilename = snap_path8 + clsSharedVariables.getOBUID() + "dig" + dig_input + "_8" + sdfOutFileFormat.format(now_time) + ".jpg ";
                            }
                            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
                            objReadfiles.write_camera1_connect_log("Video Cam8 Snapshot Dig inp4  : Connecting " + path);
                            try {
                                rt = Runtime.getRuntime();
                                cam_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                                f = cam_rec_proc.getClass().getDeclaredField("pid");
                                f.setAccessible(true);
                                cam_rec_proc.waitFor();
//                                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam8() == true) {
//                                    objFtpUpload.addFtpPkts("*" + OutFilename);
//                                    objFtpUpload.writeAllFtpPktsFile();
//                                }
                                objReadfiles.write_camera1_connect_log("Video Cam8 Snapshot Dig inp4  : Disconnected");
                            } catch (InterruptedException ex) {
                            } catch (IOException ex) {
                            } catch (NoSuchFieldException | SecurityException ex) {
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
                    } else {
                        cam8_completed = true;
                    }
                }
            } catch (Exception ex) {
            }
            if (cam1_completed == true && cam2_completed == true && cam3_completed == true && cam4_completed == true && cam5_completed == true && cam6_completed == true && cam7_completed == true && cam8_completed == true) {
                if (dig_input == 1) {
                    clsSharedVariables.setSnapDig1State(false);
                }
                if (dig_input == 2) {
                    clsSharedVariables.setSnapDig2State(false);
                }
                if (dig_input == 3) {
                    clsSharedVariables.setSnapDig3State(false);
                }
                if (dig_input == 4) {
                    clsSharedVariables.setSnapDig4State(false);
                }
                OutFilename = null;
                f = null;
                nif = null;
                objReadfiles = null;
                in_url = null;
                rt = null;
                path = null;
                cam_rec_proc = null;
                sdfOutFileFormat = null;
                now_time = null;
                dir = null;
                snap_path1 = null;
                snap_path2 = null;
                snap_path3 = null;
                snap_path4 = null;
                snap_path5 = null;
                snap_path6 = null;
                snap_path7 = null;
                snap_path8 = null;
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

}
