package obuits;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;
import static obuits.clsDefines.ETHERNET_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.media_path;

/**
 * A class to capture snapshots from network cameras using ONVIF protocol.
 * Extends Thread to enable concurrent execution.
 */
public class clsCamSnapshot extends Thread {

    byte camera_no;

    /**
     * Constructor to initialize the camera number.
     *
     * @param no The camera number
     */
    clsCamSnapshot(byte no) {
        camera_no = no;
    }

    /**
     * Executes a command in the system shell.
     *
     * @param cmd The command to be executed
     * @return True if the command executes successfully, false otherwise
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

    @Override
    public void run() {

        String OutFilename;

        NetworkInterface nif;
        clsReadFiles objReadfiles = new clsReadFiles();
        String in_url = null;
        Runtime rt = null;
        String path;
        Process cam1_rec_proc = null;
        clsOnvifUrlCamera onvifCam = new clsOnvifUrlCamera();
        switch (camera_no) {
            case 1:
                in_url = onvifCam.get_cam1_snapshot_main_url();
                break;
            case 2:
                in_url = onvifCam.get_cam2_snapshot_main_url();
                break;
            case 3:
                in_url = onvifCam.get_cam3_snapshot_main_url();
                break;
            case 4:
                in_url = onvifCam.get_cam4_snapshot_main_url();
                break;
            case 5:
                in_url = onvifCam.get_cam5_snapshot_main_url();
                break;
            case 6:
                in_url = onvifCam.get_cam6_snapshot_main_url();
                break;
            case 7:
                in_url = onvifCam.get_cam7_snapshot_main_url();
                break;
            default:
                in_url = onvifCam.get_cam8_snapshot_main_url();
                break;
        }

        try {
            nif = NetworkInterface.getByName(ETHERNET_NETWORKINTERFACE_PATH);
            if (nif == null) {
                objReadfiles.write_camera_connect_log("nif null");
                runCmd("sudo ifconfig eth0 up");
                Thread.sleep(1000);
            } else if (!nif.isUp()) {
                objReadfiles.write_camera_connect_log("nif down");
                runCmd("sudo ifconfig eth0 up");
                Thread.sleep(10000);

            }
        } catch (SocketException ex) {
        } catch (InterruptedException ex) {
        } catch (Exception ex) {
        }
        try {
            if (!clsSharedVariables.getHardDriveDetected()) {
                // return;
            }
            rt = Runtime.getRuntime();
            OutFilename = media_path + "/snap1.jpg"; // video_filepath.getAbsolutePath() +   "/Snapshot/Cam" + camera_no+ "/" +file_name + ".jpg ";

            path = "ffmpeg -i '" + in_url + "' -vcodec copy -y " + OutFilename;
            System.out.println("url" + in_url);

            objReadfiles.write_camera1_connect_log("Video Cam1 Snapshot  : Connecting ");
            try {
                cam1_rec_proc = rt.exec(new String[]{"bash", "-c", path});
                objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + " Snapshot Based Snapshot : start ");

                cam1_rec_proc.waitFor();

                objReadfiles.write_camera1_connect_log("Video Cam" + camera_no + "  Snapshot Based Snapshot : Disconnected");

            } catch (InterruptedException ex) {

                objReadfiles.write_camera1_connect_log("InterruptedException Video Cam" + camera_no + "  Snapshot Based Snapshot : Disconnected");

            } catch (IOException ex) {

                objReadfiles.write_camera1_connect_log("IOException Video Cam" + camera_no + "  Snapshot Based Snapshot : Disconnected");

            } catch (Exception ex) {
                objReadfiles.write_camera1_connect_log(ex.getMessage() + " Exception Video Cam" + camera_no + "  Snapshot Based Snapshot : Disconnected");
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

                        cam1_rec_proc = null;
                        in_url = null;

                    }

                } catch (Exception ex) {
                } finally {
                    cam1_rec_proc = null;
                    in_url = null;
                    rt.freeMemory();
                    rt.gc();
                    rt = null;
                    path = null;
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
