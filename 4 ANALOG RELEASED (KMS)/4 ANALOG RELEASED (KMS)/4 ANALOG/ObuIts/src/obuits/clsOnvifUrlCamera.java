package obuits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import javax.xml.soap.SOAPException;
import static obuits.clsDefines.ANALOG_IPADDR;
import static obuits.clsDefines.config_filepath;

/**
 * This class defines various URLs for recording and snapshotting from ONVIF
 * cameras. The URLs are constructed using the IP address, username, and
 * password for each camera. There are main and sub URLs for both recording and
 * snapshotting.
 *
 * @author Sumitha
 */
public class clsOnvifUrlCamera {

    // Define the main recording URLs for 8 cameras
    private static String cam1_record_main_url = "rtsp://" + clsSharedVariables.getCam1UserName() + ":" + clsSharedVariables.getCam1Pwd() + "@" + clsDefines.CAM1_IPADDR + ":554/Streaming/channels/101";
    private static String cam2_record_main_url = "rtsp://" + clsSharedVariables.getCam2UserName() + ":" + clsSharedVariables.getCam2Pwd() + "@" + clsDefines.CAM2_IPADDR + ":554/Streaming/channels/101";
    private static String cam3_record_main_url = "rtsp://" + clsSharedVariables.getCam3UserName() + ":" + clsSharedVariables.getCam3Pwd() + "@" + clsDefines.CAM3_IPADDR + ":554/Streaming/channels/101";
    private static String cam4_record_main_url = "rtsp://" + clsSharedVariables.getCam4UserName() + ":" + clsSharedVariables.getCam4Pwd() + "@" + clsDefines.CAM4_IPADDR + ":554/Streaming/channels/101";
    private static String cam5_record_main_url = "rtsp://" + clsSharedVariables.getCam5UserName() + ":" + clsSharedVariables.getCam5Pwd() + "@" + clsDefines.CAM5_IPADDR + ":554/Streaming/channels/201";
    private static String cam6_record_main_url = "rtsp://" + clsSharedVariables.getCam6UserName() + ":" + clsSharedVariables.getCam6Pwd() + "@" + clsDefines.CAM6_IPADDR + ":554/Streaming/channels/301";
    private static String cam7_record_main_url = "rtsp://" + clsSharedVariables.getCam7UserName() + ":" + clsSharedVariables.getCam7Pwd() + "@" + clsDefines.CAM7_IPADDR + ":554/Streaming/channels/401";
    private static String cam8_record_main_url = "rtsp://" + clsSharedVariables.getCam8UserName() + ":" + clsSharedVariables.getCam8Pwd() + "@" + clsDefines.CAM8_IPADDR + ":554/Streaming/channels/101";

    // Define the sub recording URLs for 8 cameras
    private static String cam1_record_sub_url = "rtsp://" + clsSharedVariables.getCam1UserName() + ":" + clsSharedVariables.getCam1Pwd() + "@" + clsDefines.CAM1_IPADDR + ":554/Streaming/channels/102";
    private static String cam2_record_sub_url = "rtsp://" + clsSharedVariables.getCam2UserName() + ":" + clsSharedVariables.getCam2Pwd() + "@" + clsDefines.CAM2_IPADDR + ":554/Streaming/channels/102";
    private static String cam3_record_sub_url = "rtsp://" + clsSharedVariables.getCam3UserName() + ":" + clsSharedVariables.getCam3Pwd() + "@" + clsDefines.CAM3_IPADDR + ":554/Streaming/channels/102";
    private static String cam4_record_sub_url = "rtsp://" + clsSharedVariables.getCam4UserName() + ":" + clsSharedVariables.getCam4Pwd() + "@" + clsDefines.CAM4_IPADDR + ":554/Streaming/channels/102";
    private static String cam5_record_sub_url = "rtsp://" + clsSharedVariables.getCam5UserName() + ":" + clsSharedVariables.getCam5Pwd() + "@" + clsDefines.CAM5_IPADDR + ":554/Streaming/channels/202";
    private static String cam6_record_sub_url = "rtsp://" + clsSharedVariables.getCam6UserName() + ":" + clsSharedVariables.getCam6Pwd() + "@" + clsDefines.CAM6_IPADDR + ":554/Streaming/channels/302";
    private static String cam7_record_sub_url = "rtsp://" + clsSharedVariables.getCam7UserName() + ":" + clsSharedVariables.getCam7Pwd() + "@" + clsDefines.CAM7_IPADDR + ":554/Streaming/channels/402";
    private static String cam8_record_sub_url = "rtsp://" + clsSharedVariables.getCam8UserName() + ":" + clsSharedVariables.getCam8Pwd() + "@" + clsDefines.CAM8_IPADDR + ":554/Streaming/channels/102";
    // Define the main snapshot URLs for 8 cameras
    private static String cam1_snapshot_main_url = "http://" + clsSharedVariables.getCam1UserName() + ":" + clsSharedVariables.getCam1Pwd() + "@" + clsDefines.CAM1_IPADDR + "/ISAPI/Streaming/channels/1/picture";
    private static String cam2_snapshot_main_url = "http://" + clsSharedVariables.getCam2UserName() + ":" + clsSharedVariables.getCam2Pwd() + "@" + clsDefines.CAM2_IPADDR + "/ISAPI/Streaming/channels/1/picture";
    private static String cam3_snapshot_main_url = "http://" + clsSharedVariables.getCam3UserName() + ":" + clsSharedVariables.getCam3Pwd() + "@" + clsDefines.CAM3_IPADDR + "/ISAPI/Streaming/channels/1/picture";
    private static String cam4_snapshot_main_url = "http://" + clsSharedVariables.getCam4UserName() + ":" + clsSharedVariables.getCam4Pwd() + "@" + clsDefines.CAM4_IPADDR + "/ISAPI/Streaming/channels/101/picture";
    private static String cam5_snapshot_main_url = "http://" + clsSharedVariables.getCam5UserName() + ":" + clsSharedVariables.getCam5Pwd() + "@" + clsDefines.CAM5_IPADDR + "/ISAPI/Streaming/channels/201/picture";
    private static String cam6_snapshot_main_url = "http://" + clsSharedVariables.getCam6UserName() + ":" + clsSharedVariables.getCam6Pwd() + "@" + clsDefines.CAM6_IPADDR + "/ISAPI/Streaming/channels/301/picture";
    private static String cam7_snapshot_main_url = "http://" + clsSharedVariables.getCam7UserName() + ":" + clsSharedVariables.getCam7Pwd() + "@" + clsDefines.CAM7_IPADDR + "/ISAPI/Streaming/channels/401/picture";
    private static String cam8_snapshot_main_url = "http://" + clsSharedVariables.getCam8UserName() + ":" + clsSharedVariables.getCam8Pwd() + "@" + clsDefines.CAM8_IPADDR + "/ISAPI/Streaming/channels/1/picture";

    // Define the sub snapshot URLs for 8 cameras
    private static String cam1_snapshot_sub_url = "http://" + clsSharedVariables.getCam1UserName() + ":" + clsSharedVariables.getCam1Pwd() + "@" + clsDefines.CAM1_IPADDR + "/ISAPI/Streaming/channels/2/picture";
    private static String cam2_snapshot_sub_url = "http://" + clsSharedVariables.getCam2UserName() + ":" + clsSharedVariables.getCam2Pwd() + "@" + clsDefines.CAM2_IPADDR + "/ISAPI/Streaming/channels/2/picture";
    private static String cam3_snapshot_sub_url = "http://" + clsSharedVariables.getCam3UserName() + ":" + clsSharedVariables.getCam3Pwd() + "@" + clsDefines.CAM3_IPADDR + "/ISAPI/Streaming/channels/2/picture";
    private static String cam4_snapshot_sub_url = "http://" + clsSharedVariables.getCam4UserName() + ":" + clsSharedVariables.getCam4Pwd() + "@" + clsDefines.CAM4_IPADDR + "/ISAPI/Streaming/channels/102/picture";
    private static String cam5_snapshot_sub_url = "http://" + clsSharedVariables.getCam5UserName() + ":" + clsSharedVariables.getCam5Pwd() + "@" + clsDefines.CAM5_IPADDR + "/ISAPI/Streaming/channels/202/picture";
    private static String cam6_snapshot_sub_url = "http://" + clsSharedVariables.getCam6UserName() + ":" + clsSharedVariables.getCam6Pwd() + "@" + clsDefines.CAM6_IPADDR + "/ISAPI/Streaming/channels/302/picture";
    private static String cam7_snapshot_sub_url = "http://" + clsSharedVariables.getCam7UserName() + ":" + clsSharedVariables.getCam7Pwd() + "@" + clsDefines.CAM7_IPADDR + "/ISAPI/Streaming/channels/402/picture";
    private static String cam8_snapshot_sub_url = "http://" + clsSharedVariables.getCam8UserName() + ":" + clsSharedVariables.getCam8Pwd() + "@" + clsDefines.CAM8_IPADDR + "/ISAPI/Streaming/channels/2/picture";

    // Getters and setters for the main recording URLs
    public String get_cam1_record_main_url() {
        return cam1_record_main_url;
    }

    public void set_cam1_record_main_url(String url) {
        cam1_record_main_url = url;
    }

    public String get_cam2_record_main_url() {
        return cam2_record_main_url;
    }

    public void set_cam2_record_main_url(String url) {
        cam2_record_main_url = url;
    }

    public String get_cam3_record_main_url() {
        return cam3_record_main_url;
    }

    public void set_cam3_record_main_url(String url) {
        cam3_record_main_url = url;
    }

    public String get_cam4_record_main_url() {
        return cam4_record_main_url;
    }

    public void set_cam4_record_main_url(String url) {
        cam4_record_main_url = url;
    }

    public String get_cam5_record_main_url() {
        return cam5_record_main_url;
    }

    public void set_cam5_record_main_url(String url) {
        cam5_record_main_url = url;
    }

    public String get_cam6_record_main_url() {
        return cam6_record_main_url;
    }

    public void set_cam6_record_main_url(String url) {
        cam6_record_main_url = url;
    }

    public String get_cam7_record_main_url() {
        return cam7_record_main_url;
    }

    public void set_cam7_record_main_url(String url) {
        cam7_record_main_url = url;
    }

    public String get_cam8_record_main_url() {
        return cam8_record_main_url;
    }

    public void set_cam8_record_main_url(String url) {
        cam8_record_main_url = url;
    }

    // Getters and setters for the sub recording URLs
    public String get_cam1_record_sub_url() {
        return cam1_record_sub_url;
    }

    public void set_cam1_record_sub_url(String url) {
        cam1_record_sub_url = url;
    }

    public String get_cam2_record_sub_url() {
        return cam2_record_sub_url;
    }

    public void set_cam2_record_sub_url(String url) {
        cam2_record_sub_url = url;
    }

    public String get_cam3_record_sub_url() {
        return cam3_record_sub_url;
    }

    public void set_cam3_record_sub_url(String url) {
        cam3_record_sub_url = url;
    }

    public String get_cam4_record_sub_url() {
        return cam4_record_sub_url;
    }

    public void set_cam4_record_sub_url(String url) {
        cam4_record_sub_url = url;
    }

    public String get_cam5_record_sub_url() {
        return cam5_record_sub_url;
    }

    public void set_cam5_record_sub_url(String url) {
        cam5_record_sub_url = url;
    }

    public String get_cam6_record_sub_url() {
        return cam6_record_sub_url;
    }

    public void set_cam6_record_sub_url(String url) {
        cam6_record_sub_url = url;
    }

    public String get_cam7_record_sub_url() {
        return cam7_record_sub_url;
    }

    public void set_cam7_record_sub_url(String url) {
        cam7_record_sub_url = url;
    }

    public String get_cam8_record_sub_url() {
        return cam8_record_sub_url;
    }

    public void set_cam8_record_sub_url(String url) {
        cam8_record_sub_url = url;
    }

    // Getters and setters for the main snapshot URLs
    public String get_cam1_snapshot_main_url() {
        return cam1_snapshot_main_url;
    }

    public void set_cam1_snapshot_main_url(String url) {
        cam1_snapshot_main_url = url;
    }

    public String get_cam2_snapshot_main_url() {
        return cam2_snapshot_main_url;
    }

    public void set_cam2_snapshot_main_url(String url) {
        cam2_snapshot_main_url = url;
    }

    public String get_cam3_snapshot_main_url() {
        return cam3_snapshot_main_url;
    }

    public void set_cam3_snapshot_main_url(String url) {
        cam3_snapshot_main_url = url;
    }

    public String get_cam4_snapshot_main_url() {
        return cam4_snapshot_main_url;
    }

    public void set_cam4_snapshot_main_url(String url) {
        cam4_snapshot_main_url = url;
    }

    public String get_cam5_snapshot_main_url() {
        return cam5_snapshot_main_url;
    }

    public void set_cam5_snapshot_main_url(String url) {
        cam5_snapshot_main_url = url;
    }

    public String get_cam6_snapshot_main_url() {
        return cam6_snapshot_main_url;
    }

    public void set_cam6_snapshot_main_url(String url) {
        cam6_snapshot_main_url = url;
    }

    public String get_cam7_snapshot_main_url() {
        return cam7_snapshot_main_url;
    }

    public void set_cam7_snapshot_main_url(String url) {
        cam7_snapshot_main_url = url;
    }

    public String get_cam8_snapshot_main_url() {
        return cam8_snapshot_main_url;
    }

    public void set_cam8_snapshot_main_url(String url) {
        cam8_snapshot_main_url = url;
    }

    // Getters and setters for the sub snapshot URLs
    public String get_cam1_snapshot_sub_url() {
        return cam1_snapshot_sub_url;
    }

    public void set_cam1_snapshot_sub_url(String url) {
        cam1_snapshot_sub_url = url;
    }

    public String get_cam2_snapshot_sub_url() {
        return cam2_snapshot_sub_url;
    }

    public void set_cam2_snapshot_sub_url(String url) {
        cam2_snapshot_sub_url = url;
    }

    public String get_cam3_snapshot_sub_url() {
        return cam3_snapshot_sub_url;
    }

    public void set_cam3_snapshot_sub_url(String url) {
        cam3_snapshot_sub_url = url;
    }

    public String get_cam4_snapshot_sub_url() {
        return cam4_snapshot_sub_url;
    }

    public void set_cam4_snapshot_sub_url(String url) {
        cam4_snapshot_sub_url = url;
    }

    public String get_cam5_snapshot_sub_url() {
        return cam5_snapshot_sub_url;
    }

    public void set_cam5_snapshot_sub_url(String url) {
        cam5_snapshot_sub_url = url;
    }

    public String get_cam6_snapshot_sub_url() {
        return cam6_snapshot_sub_url;
    }

    public void set_cam6_snapshot_sub_url(String url) {
        cam6_snapshot_sub_url = url;
    }

    public String get_cam7_snapshot_sub_url() {
        return cam7_snapshot_sub_url;
    }

    public void set_cam7_snapshot_sub_url(String url) {
        cam7_snapshot_sub_url = url;
    }

    public String get_cam8_snapshot_sub_url() {
        return cam8_snapshot_sub_url;
    }

    public void set_cam8_snapshot_sub_url(String url) {
        cam8_snapshot_sub_url = url;
    }

    public boolean update_cam1_urls(boolean power_on) {
        String path;
        String user;
        user = clsSharedVariables.getCam1UserName() + ":" + clsSharedVariables.getCam1Pwd() + "@";
        String[] split_str;
        boolean update = false;
        if (power_on == true) {
            update = false;
        } else {
            if (clsSharedVariables.getOnvifSupported()) {
                clsCameraOnvif onv = new clsCameraOnvif();
                try {
                    // Main stream paths
                    path = onv.getStreamPaths((byte) 1, clsDefines.CAM_MAIN_STREAM);
                    split_str = path.split(",");
                    if (!split_str[0].equals("null")) {
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam1_record_main_url(split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam1_snapshot_main_url(split_str[1]);
                        update = true;
                    }

                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    path = onv.getStreamPaths((byte) 1, clsDefines.CAM_SUB_STREAM);
                    split_str = path.split(",");
                    if (!split_str[0].equals("null")) {
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam1_record_sub_url(split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam1_snapshot_sub_url(split_str[1]);
                        update = true;
                    }

                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (update == true) {
                    write_cam_urls_file();
                }
                onv = null;
                path = null;
                split_str = null;
            }
        }

        if (update == false) {
             user = user + ANALOG_IPADDR;
           
            switch (clsSharedVariables.getCam1Type()) {
                case clsDefines.CAM_CPPLUS:
                   
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam1_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam1_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam1_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam1_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_HIKVISION:
                    
                        path = "rtsp://" + user + ":554/Streaming/channels/101";
                        set_cam1_record_main_url(path);
                        System.out.println("in onvifurlcamera cls path  for 4th cam is "+path);
                        path = "rtsp://" + user + ":554/Streaming/channels/102";
                        set_cam1_record_sub_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/101/picture";
                        set_cam1_snapshot_main_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/102/picture";
                        set_cam1_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_DAHUA:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam1_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam1_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam1_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam1_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam1_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam1_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam1_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam1_snapshot_sub_url(path);
                    }
                    break;
                case clsDefines.CAM_FOORIR:
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam1_record_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam1_record_sub_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam1_snapshot_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam1_snapshot_sub_url(path);
                    break;
                default:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    }
                    break;
            }
            return true;
        }
        path = null;
        user = null;
        return true;
    }

    public boolean update_cam2_urls(boolean power_on) {
        String path;
        String user;
        String[] split_str;
        boolean update = false;
        user = clsSharedVariables.getCam2UserName() + ":" + clsSharedVariables.getCam2Pwd() + "@";

        if (power_on == true) {
            update = false;
        } else {
            if (clsSharedVariables.getOnvifSupported()) {
                clsCameraOnvif onv = new clsCameraOnvif();

                try {
                    // Retrieve main stream paths from ONVIF
                    path = onv.getStreamPaths((byte) 2, clsDefines.CAM_MAIN_STREAM);
                    split_str = path.split(",");

                    if (!split_str[0].equals("null")) {
                        // Update main recording URL with user credentials
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam2_record_main_url(split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        // Update main snapshot URL with user credentials
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam2_snapshot_main_url(split_str[1]);
                        update = true;
                    }

                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    // Retrieve sub stream paths from ONVIF
                    path = onv.getStreamPaths((byte) 2, clsDefines.CAM_SUB_STREAM);
                    split_str = path.split(",");

                    if (!split_str[0].equals("null")) {
                        // Update sub recording URL with user credentials
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam2_record_sub_url(split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        // Update sub snapshot URL with user credentials
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam2_snapshot_sub_url(split_str[1]);
                        update = true;
                    }

                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (update == true) {
                    // Write updated URLs to file
                    write_cam_urls_file();
                }
                onv = null;
                path = null;
                split_str = null;

            }
        }
        if (update == false) {
             user = user + ANALOG_IPADDR;
           
            switch (clsSharedVariables.getCam2Type()) {
                case clsDefines.CAM_CPPLUS:
                   
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=2&subtype=0";
                        set_cam2_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=2&subtype=1";
                        set_cam2_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=2&subtype=0";
                        set_cam2_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=2&subtype=1";
                        set_cam2_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_HIKVISION:
                    
                        path = "rtsp://" + user + ":554/Streaming/channels/201";
                        set_cam2_record_main_url(path);
                        System.out.println("in onvifurlcamera cls path  for 4th cam is "+path);
                        path = "rtsp://" + user + ":554/Streaming/channels/202";
                        set_cam2_record_sub_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/201/picture";
                        set_cam2_snapshot_main_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/202/picture";
                        set_cam2_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_DAHUA:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam2_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam2_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam2_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam2_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam2_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam2_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam2_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam2_snapshot_sub_url(path);
                    }
                    break;
                case clsDefines.CAM_FOORIR:
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam2_record_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam2_record_sub_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam2_snapshot_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam2_snapshot_sub_url(path);
                    break;
                default:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    }
                    break;
            }
            return true;
        }
        path = null;
        user = null;
        return true;
    }

    public boolean update_cam3_urls(boolean power_on) {

        String path;
        String user;
        user = clsSharedVariables.getCam3UserName() + ":" + clsSharedVariables.getCam3Pwd() + "@";
        String[] split_str;
        boolean update = false;
        if (power_on == true) {
            update = false;
        } else {
            if (clsSharedVariables.getOnvifSupported()) {
                clsCameraOnvif onv = new clsCameraOnvif();

                try {
                    path = onv.getStreamPaths((byte) 3, clsDefines.CAM_MAIN_STREAM);
                    split_str = path.split(",");
                    if (!split_str[0].equals("null")) {
                        // "rtsp://10.42.0.6:554/cam/realmonitor?channel=1&subtype=0&unicast=true&proto=Onvif";
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam3_record_main_url(split_str[0]);
                        ////System.out.println("Retrieved cam1  ONVIF main rec :" + split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        //  http://10.42.0.6/onvifsnapshot/media_service/snapshot?channel=1&subtype=0
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam3_snapshot_main_url(split_str[1]);
                        // ////System.out.println("Retrieved cam1  ONVIF main snap :" + split_str[1]);
                        update = true;
                    }
                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    path = onv.getStreamPaths((byte) 3, clsDefines.CAM_SUB_STREAM);
                    split_str = path.split(",");
                    if (!split_str[0].equals("null")) {
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam3_record_sub_url(split_str[0]);
                        //  ////System.out.println("Retrieved cam1  ONVIF sub rec :" + split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam3_snapshot_sub_url(split_str[1]);
                        //  ////System.out.println("Retrieved cam1  ONVIF sub snap :" + split_str[1]);
                        update = true;
                    }
                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (update == true) {
                    write_cam_urls_file();
                }
                onv = null;
                path = null;
                split_str = null;
            }
        }
        if (update == false) {
             user = user + ANALOG_IPADDR;
           
            switch (clsSharedVariables.getCam3Type()) {
                case clsDefines.CAM_CPPLUS:
                   
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=3&subtype=0";
                        set_cam3_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=3&subtype=1";
                        set_cam3_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=3&subtype=0";
                        set_cam3_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=3&subtype=1";
                        set_cam3_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_HIKVISION:
                    
                        path = "rtsp://" + user + ":554/Streaming/channels/301";
                        set_cam3_record_main_url(path);
                        System.out.println("in onvifurlcamera cls path  for 4th cam is "+path);
                        path = "rtsp://" + user + ":554/Streaming/channels/302";
                        set_cam3_record_sub_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/301/picture";
                        set_cam3_snapshot_main_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/302/picture";
                        set_cam3_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_DAHUA:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    }
                    break;
                case clsDefines.CAM_FOORIR:
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_record_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_record_sub_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_snapshot_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_snapshot_sub_url(path);
                    break;
                default:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    }
                    break;
            }
            return true;
        }
        return true;
    }

    public boolean update_cam4_urls(boolean power_on) {
        String path;
        String user;
        user = clsSharedVariables.getCam4UserName() + ":" + clsSharedVariables.getCam4Pwd() + "@";
        String[] split_str;
        boolean update = false;
        if (power_on == true) {
            update = false;
        } else {
            if (clsSharedVariables.getOnvifSupported()) {
                clsCameraOnvif onv = new clsCameraOnvif();
                try {
                    // Main stream paths
                    path = onv.getStreamPaths((byte) 4, clsDefines.CAM_MAIN_STREAM);
                    split_str = path.split(",");
                    if (!split_str[0].equals("null")) {
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam4_record_main_url(split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam4_snapshot_main_url(split_str[1]);
                        update = true;
                    }

                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    path = onv.getStreamPaths((byte) 4, clsDefines.CAM_SUB_STREAM);
                    split_str = path.split(",");
                    if (!split_str[0].equals("null")) {
                        split_str[0] = split_str[0].replaceFirst("rtsp://", "rtsp://" + user);
                        set_cam4_record_sub_url(split_str[0]);
                    }
                    if (!split_str[1].equals("null")) {
                        split_str[1] = split_str[1].replaceFirst("http://", "http://" + user);
                        set_cam4_snapshot_sub_url(split_str[1]);
                        update = true;
                    }

                } catch (SOAPException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    //Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (update == true) {
                    write_cam_urls_file();
                }
                onv = null;
                path = null;
                split_str = null;
            }
        }

        if (update == false) {
             user = user + ANALOG_IPADDR;
           
            switch (clsSharedVariables.getCam4Type()) {
                case clsDefines.CAM_CPPLUS:
                   
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_HIKVISION:
                        path = "rtsp://" + user + ":554/Streaming/channels/401";
                        set_cam4_record_main_url(path);
                        System.out.println("in onvifurlcamera cls path  for 4th cam is "+path);
                        path = "rtsp://" + user + ":554/Streaming/channels/402";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/401/picture";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/ISAPI/Streaming/channels/402/picture";
                        set_cam4_snapshot_sub_url(path);
                    
                    break;
                case clsDefines.CAM_DAHUA:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    }
                    break;
                case clsDefines.CAM_FOORIR:
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_record_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_record_sub_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_snapshot_main_url(path);
                    path = "rtsp://" + user + ":8554/camera";
                    set_cam4_snapshot_sub_url(path);
                    break;
                default:
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=1&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=1&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    } else {
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=0";
                        set_cam4_record_main_url(path);
                        path = "rtsp://" + user + ":554/cam/realmonitor?channel=4&subtype=1";
                        set_cam4_record_sub_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=0";
                        set_cam4_snapshot_main_url(path);
                        path = "http://" + user + "/cgi-bin/snapshot.cgi?channel=4&subtype=1";
                        set_cam4_snapshot_sub_url(path);
                    }
                    break;
            }
            return true;
        }
        path = null;
        user = null;
        return true;
    }

    

    public void write_cam_urls_file() {
        File file = new File(config_filepath, "ConfigCamUrls.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;

        try {
            file.setReadable(true, false); // Ensure file is readable
            file.setWritable(true, false); // Ensure file is writable
            f = new FileOutputStream(file, false); // Open file for writing (overwrite mode)
            pw = new PrintWriter(f);

            // Write main record URLs for cams 1 to 8
            pw.println(get_cam1_record_main_url());
            pw.println(get_cam2_record_main_url());
            pw.println(get_cam3_record_main_url());
            pw.println(get_cam4_record_main_url());
            pw.println(get_cam5_record_main_url());
            pw.println(get_cam6_record_main_url());
            pw.println(get_cam7_record_main_url());
            pw.println(get_cam8_record_main_url());

            // Write sub record URLs for cams 1 to 8
            pw.println(get_cam1_record_sub_url());
            pw.println(get_cam2_record_sub_url());
            pw.println(get_cam3_record_sub_url());
            pw.println(get_cam4_record_sub_url());
            pw.println(get_cam5_record_sub_url());
            pw.println(get_cam6_record_sub_url());
            pw.println(get_cam7_record_sub_url());
            pw.println(get_cam8_record_sub_url());

            // Write main snapshot URLs for cams 1 to 8
            pw.println(get_cam1_snapshot_main_url());
            pw.println(get_cam2_snapshot_main_url());
            pw.println(get_cam3_snapshot_main_url());
            pw.println(get_cam4_snapshot_main_url());
            pw.println(get_cam5_snapshot_main_url());
            pw.println(get_cam6_snapshot_main_url());
            pw.println(get_cam7_snapshot_main_url());
            pw.println(get_cam8_snapshot_main_url());

            // Write sub snapshot URLs for cams 1 to 8
            pw.println(get_cam1_snapshot_sub_url());
            pw.println(get_cam2_snapshot_sub_url());
            pw.println(get_cam3_snapshot_sub_url());
            pw.println(get_cam4_snapshot_sub_url());
            pw.println(get_cam5_snapshot_sub_url());
            pw.println(get_cam6_snapshot_sub_url());
            pw.println(get_cam7_snapshot_sub_url());
            pw.println(get_cam8_snapshot_sub_url());

        } catch (Exception ex) {
            // Handle exceptions, if necessary
        } finally {
            try {
                if (f != null) {
                    f.close(); // Close the FileOutputStream
                }
                if (pw != null) {
                    pw.close(); // Close the PrintWriter
                }
            } catch (IOException ex) {
                // Handle IOException during close, if needed
                // Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
            }

            f = null;
            pw = null;
            file = null;
        }
    }

    /**
     * Reads camera URLs from a configuration file and sets them accordingly.
     */
    public void read_cam_urls_file() {
        short i = 0;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "ConfigCamUrls.txt");

        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));

                // Read each line and set corresponding URL based on index i
                while ((line = br.readLine()) != null) {
                    switch (i) {
                        case 0:
                            set_cam1_record_main_url(line);
                            break;
                        case 1:
                            set_cam2_record_main_url(line);
                            break;
                        case 2:
                            set_cam3_record_main_url(line);
                            break;
                        case 3:
                            set_cam4_record_main_url(line);
                            break;
                        case 4:
                            set_cam5_record_main_url(line);
                            break;
                        case 5:
                            set_cam6_record_main_url(line);
                            break;
                        case 6:
                            set_cam7_record_main_url(line);
                            break;
                        case 7:
                            set_cam8_record_main_url(line);
                            break;
                        case 8:
                            set_cam1_record_sub_url(line);
                            break;
                        case 9:
                            set_cam2_record_sub_url(line);
                            break;
                        case 10:
                            set_cam3_record_sub_url(line);
                            break;
                        case 11:
                            set_cam4_record_sub_url(line);
                            break;
                        case 12:
                            set_cam5_record_sub_url(line);
                            break;
                        case 13:
                            set_cam6_record_sub_url(line);
                            break;
                        case 14:
                            set_cam7_record_sub_url(line);
                            break;
                        case 15:
                            set_cam8_record_sub_url(line);
                            break;
                        case 16:
                            set_cam1_snapshot_main_url(line);
                            break;
                        case 17:
                            set_cam2_snapshot_main_url(line);
                            break;
                        case 18:
                            set_cam3_snapshot_main_url(line);
                            break;
                        case 19:
                            set_cam4_snapshot_main_url(line);
                            break;
                        case 20:
                            set_cam5_snapshot_main_url(line);
                            break;
                        case 21:
                            set_cam6_snapshot_main_url(line);
                            break;
                        case 22:
                            set_cam7_snapshot_main_url(line);
                            break;
                        case 23:
                            set_cam8_snapshot_main_url(line);
                            break;
                        case 24:
                            set_cam1_snapshot_sub_url(line);
                            break;
                        case 25:
                            set_cam2_snapshot_sub_url(line);
                            break;
                        case 26:
                            set_cam3_snapshot_sub_url(line);
                            break;
                        case 27:
                            set_cam4_snapshot_sub_url(line);
                            break;
                        case 28:
                            set_cam5_snapshot_sub_url(line);
                            break;
                        case 29:
                            set_cam6_snapshot_sub_url(line);
                            break;
                        case 30:
                            set_cam7_snapshot_sub_url(line);
                            break;
                        case 31:
                            set_cam8_snapshot_sub_url(line);
                            break;
                    }
                    i++;
                }

            } catch (Exception ex) {
                // Handle exceptions, if necessary
            } finally {
                try {
                    if (br != null) {
                        br.close(); // Close the BufferedReader
                    }
                } catch (IOException ex) {
                    // Handle IOException during close, if needed
                    // Logger.getLogger(clsOnvifUrlCamera.class.getName()).log(Level.SEVERE, null, ex);
                }

                br = null;
                file = null;
                line = null;
            }
        }
    }
}
