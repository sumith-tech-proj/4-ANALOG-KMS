/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import static obuits.clsSharedVariables.getCurLatitude;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getVechicleRegNo;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpRequest;
import org.jpos.util.NameRegistrar;
import com.squareup.okhttp.Request;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.SwingWorker;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sumitha
 */
public class clsVideoNormalConfigUpdates {

    /*
     "D1"
     704 x 576
     704 x 480
     "HD1"
     352 x 576
     352 x 480
     "BCIF"/"2CIF"
     704 x 288
     704 x 240
     General APIs 17
     Fixed Resolution Name Size in PAL Size in NTSC
     "CIF"
     352 x 288
     352 x 240
     "QCIF"
     176 x 144
     176 x 120
     "NHD"
     640 x 360
     —
     "VGA"
     640 x 480
     —
     "QVGA"
     320 x 240
     —
     "SVCD"
     480 x 480
     —
     "QQVGA"
     160 x 128
     —
     "SVGA"
     800 x 592
     —
     "SVGA1"
     800 x 600
     —
     "WVGA"
     800 x 480
     —
     "FWVGA"
     854 x 480
     —
     "DVGA"
     960 x 640
     —
     "XVGA"
     1024 x 768
     —
     "WXGA"
     1280 x 800
     —
     "WXGA2"
     1280 x 768
     —
     "WXGA3"
     1280 x 854
     —
     "WXGA4"
     1366 x 768
     —
     "SXGA"
     1280 x 1024
     —
     "SXGA+"
     1400 x 1050
     —
     "WSXGA"
     1600 x 1024
     —
     "UXGA"
     1600 x 1200
     —
     "WUXGA"
     1920 x 1200
     —
     "ND1"
     240 x 192
     —
     "720P"
     1280 x 720
     —
     "1080P"
     1920 x 1080
     —
     "QFHD"
     3840 x 2160
     —
     "1_3M", "1280x960"
     1280 x 960 (1.3 Mega Pixels)
     —
     "2_5M", "1872x1408"
     1872 x 1408 (2.5 Mega Pixels)
     —
     "5M", "3744x1408"
     3744 x 1408 (5 Mega Pixels)
     —
     "3M", "2048x1536"
     2048 x 1536 (3 Mega Pixels)
     —
     "5_0M", "2432x2048"
     2432 x 2048 (5 Mega Pixels)
     —
     "1_2M", "1216x1024"
     1216 x 1024 (1.2 Mega Pixels)
     —
     "1408x1024"
     1408 x 1024 (1.5 Mega Pixels)
     —
     "3296x2472"
     3296 x 2472 (8 Mega Pixels)
     —
     "5_1M", "2560x1920"
     2560 x 1920 (5 Mega Pixels)
     —
     "960H",
     960 x 576
     960 x 480
     "DV720P"
     960 x 720
     —
     "2560x1600"
     2560 x 1600 (4 Mega Pixels)
     —
     "2336x1752"
     2336 x 1752 (4 Mega Pixels)
     —
     "2592x2048"
     2592 x 2048
     —
     "2448x2048"
     2448 x 2048
     —
     "1920x1440"
     1920x1440
     —
     "2752x2208"
     2752x2208
     —
     "3840x2160"
     3840x2160
     —
     "4096x2160"
     4096x2160
     —
     General APIs 18
     Fixed Resolution Name Size in PAL Size in NTSC
     "3072x2048"
     3072x2048
     */
    public synchronized void update_video_res_bitrate_adjustments(byte cam_no, byte stream_type) {
        // Initialize variables
        String addr = null;
        int width = 0;
        int height = 0;
        int bitrate = 0;
        byte res = 0;
        int quality = 0;
        int framerate = 0;
        String bitrate_type_control = "VBR";
        byte cam_type = clsSharedVariables.getCam1Type();
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
        int frame_interval_gop = 0;
        String uri;
        String[] width_arr = null;
        String[] height_arr = null;
        String[] frame_rate_arr = null;
        String[] quality_arr = null;

        // Check if the stream type is the main stream
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    res = clsSharedVariables.getResolution();
                    bitrate = clsSharedVariables.getbitrate();
                    quality = clsSharedVariables.getVideoquality();
                    framerate = clsSharedVariables.getFramerate();
                    cam_type = clsSharedVariables.getCam1Type();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype();
                    frame_interval_gop = clsSharedVariables.getFrameInterval();
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    res = clsSharedVariables.getResolution2();
                    bitrate = clsSharedVariables.getbitrate2();
                    quality = clsSharedVariables.getVideoquality2();
                    framerate = clsSharedVariables.getFramerate2();
                    cam_type = clsSharedVariables.getCam2Type();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype2();
                    frame_interval_gop = clsSharedVariables.getFrameInterval2();
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    res = clsSharedVariables.getResolution3();
                    bitrate = clsSharedVariables.getbitrate3();
                    quality = clsSharedVariables.getVideoquality3();
                    framerate = clsSharedVariables.getFramerate3();
                    cam_type = clsSharedVariables.getCam3Type();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype3();
                    frame_interval_gop = clsSharedVariables.getFrameInterval3();
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    res = clsSharedVariables.getResolution4();
                    bitrate = clsSharedVariables.getbitrate4();
                    quality = clsSharedVariables.getVideoquality4();
                    framerate = clsSharedVariables.getFramerate4();
                    cam_type = clsSharedVariables.getCam4Type();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype4();
                    frame_interval_gop = clsSharedVariables.getFrameInterval4();
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    res = clsSharedVariables.getResolution5();
                    bitrate = clsSharedVariables.getbitrate5();
                    quality = clsSharedVariables.getVideoquality5();
                    framerate = clsSharedVariables.getFramerate5();
                    cam_type = clsSharedVariables.getCam5Type();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype5();
                    frame_interval_gop = clsSharedVariables.getFrameInterval5();
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    res = clsSharedVariables.getResolution6();
                    bitrate = clsSharedVariables.getbitrate6();
                    quality = clsSharedVariables.getVideoquality6();
                    framerate = clsSharedVariables.getFramerate6();
                    cam_type = clsSharedVariables.getCam6Type();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype6();
                    frame_interval_gop = clsSharedVariables.getFrameInterval6();
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    res = clsSharedVariables.getResolution7();
                    bitrate = clsSharedVariables.getbitrate7();
                    quality = clsSharedVariables.getVideoquality7();
                    framerate = clsSharedVariables.getFramerate7();
                    cam_type = clsSharedVariables.getCam7Type();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype7();
                    frame_interval_gop = clsSharedVariables.getFrameInterval7();
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    res = clsSharedVariables.getResolution8();
                    bitrate = clsSharedVariables.getbitrate8();
                    quality = clsSharedVariables.getVideoquality8();
                    framerate = clsSharedVariables.getFramerate8();
                    cam_type = clsSharedVariables.getCam8Type();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype8();
                    frame_interval_gop = clsSharedVariables.getFrameInterval8();
                    break;
                default:
                    break;
            }
        } else {
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution();
                    bitrate = clsSharedVariables.getSubbitrate();
                    quality = clsSharedVariables.getSubVideoquality();
                    framerate = clsSharedVariables.getSubFramerate();
                    cam_type = clsSharedVariables.getCam1Type();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval();
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution2();
                    bitrate = clsSharedVariables.getSubbitrate2();
                    quality = clsSharedVariables.getSubVideoquality2();
                    framerate = clsSharedVariables.getSubFramerate2();
                    cam_type = clsSharedVariables.getCam2Type();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype2();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval2();
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution3();
                    bitrate = clsSharedVariables.getSubbitrate3();
                    quality = clsSharedVariables.getSubVideoquality3();
                    framerate = clsSharedVariables.getSubFramerate3();
                    cam_type = clsSharedVariables.getCam3Type();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype3();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval3();
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution4();
                    bitrate = clsSharedVariables.getSubbitrate4();
                    quality = clsSharedVariables.getSubVideoquality4();
                    framerate = clsSharedVariables.getSubFramerate4();
                    cam_type = clsSharedVariables.getCam4Type();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype4();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval4();
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution5();
                    bitrate = clsSharedVariables.getSubbitrate5();
                    quality = clsSharedVariables.getSubVideoquality5();
                    framerate = clsSharedVariables.getSubFramerate5();
                    cam_type = clsSharedVariables.getCam5Type();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype5();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval5();
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution6();
                    bitrate = clsSharedVariables.getSubbitrate6();
                    quality = clsSharedVariables.getSubVideoquality6();
                    framerate = clsSharedVariables.getSubFramerate6();
                    cam_type = clsSharedVariables.getCam6Type();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype6();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval6();
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution7();
                    bitrate = clsSharedVariables.getSubbitrate7();
                    quality = clsSharedVariables.getSubVideoquality7();
                    framerate = clsSharedVariables.getSubFramerate7();
                    cam_type = clsSharedVariables.getCam7Type();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype7();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval7();
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution8();
                    bitrate = clsSharedVariables.getSubbitrate8();
                    quality = clsSharedVariables.getSubVideoquality8();
                    framerate = clsSharedVariables.getSubFramerate8();
                    cam_type = clsSharedVariables.getCam8Type();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype8();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval8();
                    break;
                default:
                    break;
            }
        }
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }
        // Set width and height based on resolution for main or sub-stream
        if (cam_type != clsDefines.CAM_HIKVISION) {
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                switch (res) {
                    case 0:
                        width = 1920;
                        height = 1080;
                        break;
                    case 1:
                        width = 1920;
                        height = 1080;
                        break;
                    case 2:
                        width = 1280;
                        height = 960;
                        break;
                    case 3:
                        width = 1280;
                        height = 720;
                        break;
                    case 4:
                        width = 1280;
                        height = 720;
                        break;
                    default:
                        break;
                }
            } else {
                //D1,VGA,CIF
                //"D1  704 x 576
                //VGA: 640 x 480
                //CIF  352 x 288
                switch (res) {
                    case 0:
                        // 1080P(1920*1080,1280x960,720P(1280 x 720)
                        width = 704;
                        height = 576;
                        break;
                    case 1:
                        width = 704;
                        height = 576;
                        break;
                    case 2:
                        width = 640;
                        height = 480;
                        break;
                    case 3:
                        width = 352;
                        height = 288;
                        break;
                    case 4:
                        width = 352;
                        height = 288;
                        break;
                    default:
                        break;
                }

            }

        }
        if (clsSharedVariables.getOnvifSupported() == false) {
            if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                    uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop;  // `date '+%Y-%m-%d%%20%H:%M:%S'";
                    digestUpdateData(uri, user, pwd);
                    //  runCmd("omxplayer http://" + user + ":" + pwd + "@" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                } else {
                    // runCmd("omxplayer http://" + user + ":" + pwd + "@" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                    uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop; // `date '+%Y-%m-%d%%20%H:%M:%S'";
                    digestUpdateData(uri, user, pwd);
                }
                // dahua_update_bitrate_control_vbr_cbr(cam_no, stream_type, bitrate_type_control);
            } else if (cam_type == clsDefines.CAM_HIKVISION) {
                try {
                    //  uri = uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
                    // digestUpdateData(uri, user, pwd);
                    hikvision_Encoding(cam_no, stream_type);
                } catch (Exception e) {
                }
            }
        } else { //onvif supported camera
            clsCameraOnvif objOnvif = new clsCameraOnvif();
            try {
                if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                    if (objOnvif.updateCameraAllFeatures(cam_no, stream_type, res, bitrate, (byte) quality, framerate, frame_interval_gop) == false) { //onvif returns false
                        if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop;  // `date '+%Y-%m-%d%%20%H:%M:%S'";
                                digestUpdateData(uri, user, pwd);
                                //  runCmd("omxplayer http://" + user + ":" + pwd + "@" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                            } else {
                                // runCmd("omxplayer http://" + user + ":" + pwd + "@" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop; // `date '+%Y-%m-%d%%20%H:%M:%S'";
                                digestUpdateData(uri, user, pwd);
                            }
                            // dahua_update_bitrate_control_vbr_cbr(cam_no, stream_type, bitrate_type_control);
                        } else if (cam_type == clsDefines.CAM_HIKVISION) {
                            try {
                                //  uri = uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
                                // digestUpdateData(uri, user, pwd);
                                hikvision_Encoding(cam_no, stream_type);
                            } catch (Exception e) {
                            }
                        }
                    }
                } else {
                    if (objOnvif.updateCameraAllFeatures(cam_no, stream_type, res, bitrate, (byte) quality, framerate, frame_interval_gop) == false) {
                        if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop;  // `date '+%Y-%m-%d%%20%H:%M:%S'";
                                digestUpdateData(uri, user, pwd);
                                //  runCmd("omxplayer http://" + user + ":" + pwd + "@" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                            } else {
                                // runCmd("omxplayer http://" + user + ":" + pwd + "@" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop; // `date '+%Y-%m-%d%%20%H:%M:%S'";
                                digestUpdateData(uri, user, pwd);
                            }
                            // dahua_update_bitrate_control_vbr_cbr(cam_no, stream_type, bitrate_type_control);
                        } else if (cam_type == clsDefines.CAM_HIKVISION) {
                            try {
                                //  uri = uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
                                // digestUpdateData(uri, user, pwd);
                                hikvision_Encoding(cam_no, stream_type);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                //Logger.getLogger(clsVideoNormalConfigUpdates.class.getName()).log(Level.SEVERE, null, ex);
            }
            objOnvif = null;
        }
        addr = null;
        uri = null;
    }

    public synchronized void update_image_brightness_adjustments(byte cam_no) {
        // Initialize variables
        String addr = null;
        int brightness = 0;
        int contrast = 0;
        int saturation = 0;
        String uri;

        // Get camera type, username, and password from shared variables
        byte cam_type = clsSharedVariables.getCam1Type(); // Default initialization for cam_type
        String user = clsSharedVariables.getCam1UserName(); // Default initialization for user
        String pwd = clsSharedVariables.getCam1Pwd(); // Default initialization for pwd

        // Determine camera-specific settings based on cam_no
        switch (cam_no) {
            case 1:
                addr = clsDefines.CAM1_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevel();
                contrast = clsSharedVariables.getContrastLevel();
                saturation = clsSharedVariables.getSaturationLevel();
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
                break;
            case 2:
                addr = clsDefines.CAM2_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam2();
                contrast = clsSharedVariables.getContrastLevelCam2();
                saturation = clsSharedVariables.getSaturationLevelCam2();
                user = clsSharedVariables.getCam2UserName();
                pwd = clsSharedVariables.getCam2Pwd();
                break;
            case 3:
                addr = clsDefines.CAM3_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam3();
                contrast = clsSharedVariables.getContrastLevelCam3();
                saturation = clsSharedVariables.getSaturationLevelCam3();
                user = clsSharedVariables.getCam3UserName();
                pwd = clsSharedVariables.getCam3Pwd();
                break;
            case 4:
                addr = clsDefines.CAM4_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam4();
                contrast = clsSharedVariables.getContrastLevelCam4();
                saturation = clsSharedVariables.getSaturationLevelCam4();
                user = clsSharedVariables.getCam4UserName();
                pwd = clsSharedVariables.getCam4Pwd();
                break;
            case 5:
                addr = clsDefines.CAM5_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam5();
                contrast = clsSharedVariables.getContrastLevelCam5();
                saturation = clsSharedVariables.getSaturationLevelCam5();
                user = clsSharedVariables.getCam5UserName();
                pwd = clsSharedVariables.getCam5Pwd();
                break;
            case 6:
                addr = clsDefines.CAM6_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam6();
                contrast = clsSharedVariables.getContrastLevelCam6();
                saturation = clsSharedVariables.getSaturationLevelCam6();
                user = clsSharedVariables.getCam6UserName();
                pwd = clsSharedVariables.getCam6Pwd();
                break;
            case 7:
                addr = clsDefines.CAM7_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam7();
                contrast = clsSharedVariables.getContrastLevelCam7();
                saturation = clsSharedVariables.getSaturationLevelCam7();
                user = clsSharedVariables.getCam7UserName();
                pwd = clsSharedVariables.getCam7Pwd();
                break;
            case 8:
                addr = clsDefines.CAM8_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam8();
                contrast = clsSharedVariables.getContrastLevelCam8();
                saturation = clsSharedVariables.getSaturationLevelCam8();
                user = clsSharedVariables.getCam8UserName();
                pwd = clsSharedVariables.getCam8Pwd();
                break;
            default:
                break;
        }

        // Check if ONVIF is supported
        if (clsSharedVariables.getOnvifSupported() == false) {
            // If ONVIF is not supported and camera type is CPPLUS or DAHUA
            if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                // Construct URI for camera configuration update
                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&VideoColor[0][0].Brightness=" + brightness
                        + "&VideoColor[0][0].Contrast=" + contrast + "&VideoColor[0][0].Saturation=" + saturation;

                // Call method to digest update data with user credentials
                digestUpdateData(uri, user, pwd);
            } else if (cam_type == clsDefines.CAM_HIKVISION) {
                // If camera type is HIKVISION, call specific image adjustment method
                hikvision_imageadjustment(cam_no, brightness + "," + contrast + "," + saturation);
            }
        } else {
            // If ONVIF is supported, create a new instance of clsCameraOnvif and adjust image brightness settings
            clsCameraOnvif camOnvif = new clsCameraOnvif();
            camOnvif.ImageBrightnessSettings(cam_no, clsDefines.CAM_MAIN_STREAM);
            camOnvif = null; // Release resources
        }

        // Write camera configuration to file
        clsReadFiles obj = new clsReadFiles();
        obj.write_camera_cfg_file();
        obj = null; // Release resources
        addr = null; // Reset addr
        uri = null; // Reset uri
    }

    public synchronized void update_mirror_image_settings(byte cam_no) {
        // Initialize variables
        String str;
        byte cam_type = clsSharedVariables.getCam1Type();
        String addr = null;
        String user;
        String pwd;
        boolean mirr_enable;

        // Switch statement to determine camera-specific settings based on cam_no
        switch (cam_no) {
            case 1:
                addr = clsDefines.CAM1_IPADDR;
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
                mirr_enable = clsSharedVariables.cam1MirrorEnable;
                cam_type = clsSharedVariables.getCam1Type();
                break;
            case 2:
                addr = clsDefines.CAM2_IPADDR;
                user = clsSharedVariables.getCam2UserName();
                pwd = clsSharedVariables.getCam2Pwd();
                mirr_enable = clsSharedVariables.cam2MirrorEnable;
                cam_type = clsSharedVariables.getCam2Type();
                break;
            case 3:
                addr = clsDefines.CAM3_IPADDR;
                user = clsSharedVariables.getCam3UserName();
                pwd = clsSharedVariables.getCam3Pwd();
                mirr_enable = clsSharedVariables.cam3MirrorEnable;
                cam_type = clsSharedVariables.getCam3Type();
                break;
            case 4:
                addr = clsDefines.CAM4_IPADDR;
                //System.out.println("update_mirror_image_settings3");
                user = clsSharedVariables.getCam4UserName();
                pwd = clsSharedVariables.getCam4Pwd();
                mirr_enable = clsSharedVariables.cam4MirrorEnable;
                cam_type = clsSharedVariables.getCam4Type();
                //System.out.println("update_mirror_image_settings3" + clsSharedVariables.getCam4UserName() + clsSharedVariables.getCam4Pwd() + clsSharedVariables.cam4MirrorEnable);
                break;
            case 5:
                addr = clsDefines.CAM5_IPADDR;
                user = clsSharedVariables.getCam5UserName();
                pwd = clsSharedVariables.getCam5Pwd();
                mirr_enable = clsSharedVariables.cam5MirrorEnable;
                cam_type = clsSharedVariables.getCam5Type();
                break;
            case 6:
                addr = clsDefines.CAM6_IPADDR;
                user = clsSharedVariables.getCam6UserName();
                pwd = clsSharedVariables.getCam6Pwd();
                mirr_enable = clsSharedVariables.cam6MirrorEnable;
                cam_type = clsSharedVariables.getCam6Type();
                break;
            case 7:
                addr = clsDefines.CAM7_IPADDR;
                user = clsSharedVariables.getCam7UserName();
                pwd = clsSharedVariables.getCam7Pwd();
                mirr_enable = clsSharedVariables.cam7MirrorEnable;
                cam_type = clsSharedVariables.getCam7Type();
                break;
            case 8:
                addr = clsDefines.CAM8_IPADDR;
                user = clsSharedVariables.getCam8UserName();
                pwd = clsSharedVariables.getCam8Pwd();
                mirr_enable = clsSharedVariables.cam8MirrorEnable;
                cam_type = clsSharedVariables.getCam8Type();
                break;
            default:
                addr = clsDefines.CAM1_IPADDR;
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
                mirr_enable = clsSharedVariables.cam1MirrorEnable;
                break;
        }

        try {
            // Check camera type for configuration updates
            if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                // For CPPLUS or DAHUA cameras
                if (mirr_enable == true) {
                    // If mirror mode is enabled, set configurations accordingly
                    str = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
                    digestUpdateData(str, user, pwd);

                    str = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=true";
                    digestUpdateData(str, user, pwd);
                } else {
                    // If mirror mode is disabled, set configuration accordingly
                    str = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&VideoInOptions[0].Mirror=false";
                    digestUpdateData(str, user, pwd);
                }
            } else {
                // For other camera types (e.g., HIKVISION), call specific mirror mode method
                hikvision_mirrorOn(addr);
            }
        } catch (Exception e) {
            // Exception handling block (currently empty)
        } finally {
            // Cleanup: Set variables to null to release resources
            str = null;
            addr = null;
            user = null;
            pwd = null;
        }
    }

    public synchronized void normal_video_res_bitrate_adjustments(byte cam_no, byte stream_type) {
        // Initialize variables
        String addr = null;     // IP address of the camera
        int width = 0;          // Video width
        int height = 0;         // Video height
        int bitrate = 0;        // Video bitrate
        byte res = 0;           // Resolution type
        int quality = 0;        // Video quality
        int framerate = 0;      // Frames per second
        String bitrate_type_control = "VBR"; // Bitrate control type (Variable Bit Rate by default)
        byte cam_type = clsSharedVariables.getCam1Type(); // Camera type (default initialization)
        String user = clsSharedVariables.getCam1UserName(); // Camera username
        String pwd = clsSharedVariables.getCam1Pwd();       // Camera password
        int frame_interval_gop = 0; // GOP (Group of Pictures) frame interval

        // Determine camera-specific settings based on cam_no and stream_type
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            // Settings for main stream
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    res = clsSharedVariables.getResolution();
                    bitrate = clsSharedVariables.getbitrate();
                    quality = clsSharedVariables.getVideoquality();
                    framerate = clsSharedVariables.getFramerate();
                    cam_type = clsSharedVariables.getCam1Type();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype();
                    frame_interval_gop = clsSharedVariables.getFrameInterval();
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    res = clsSharedVariables.getResolution2();
                    bitrate = clsSharedVariables.getbitrate2();
                    quality = clsSharedVariables.getVideoquality2();
                    framerate = clsSharedVariables.getFramerate2();
                    cam_type = clsSharedVariables.getCam2Type();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype2();
                    frame_interval_gop = clsSharedVariables.getFrameInterval2();
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    res = clsSharedVariables.getResolution3();
                    bitrate = clsSharedVariables.getbitrate3();
                    quality = clsSharedVariables.getVideoquality3();
                    framerate = clsSharedVariables.getFramerate3();
                    cam_type = clsSharedVariables.getCam3Type();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype3();
                    frame_interval_gop = clsSharedVariables.getFrameInterval3();
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    res = clsSharedVariables.getResolution4();
                    bitrate = clsSharedVariables.getbitrate4();
                    quality = clsSharedVariables.getVideoquality4();
                    framerate = clsSharedVariables.getFramerate4();
                    cam_type = clsSharedVariables.getCam4Type();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype4();
                    frame_interval_gop = clsSharedVariables.getFrameInterval4();
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    res = clsSharedVariables.getResolution5();
                    bitrate = clsSharedVariables.getbitrate5();
                    quality = clsSharedVariables.getVideoquality5();
                    framerate = clsSharedVariables.getFramerate5();
                    cam_type = clsSharedVariables.getCam5Type();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype5();
                    frame_interval_gop = clsSharedVariables.getFrameInterval5();
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    res = clsSharedVariables.getResolution6();
                    bitrate = clsSharedVariables.getbitrate6();
                    quality = clsSharedVariables.getVideoquality6();
                    framerate = clsSharedVariables.getFramerate6();
                    cam_type = clsSharedVariables.getCam6Type();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype6();
                    frame_interval_gop = clsSharedVariables.getFrameInterval6();
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    res = clsSharedVariables.getResolution7();
                    bitrate = clsSharedVariables.getbitrate7();
                    quality = clsSharedVariables.getVideoquality7();
                    framerate = clsSharedVariables.getFramerate7();
                    cam_type = clsSharedVariables.getCam7Type();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype7();
                    frame_interval_gop = clsSharedVariables.getFrameInterval7();
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    res = clsSharedVariables.getResolution8();
                    bitrate = clsSharedVariables.getbitrate8();
                    quality = clsSharedVariables.getVideoquality8();
                    framerate = clsSharedVariables.getFramerate8();
                    cam_type = clsSharedVariables.getCam8Type();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype8();
                    frame_interval_gop = clsSharedVariables.getFrameInterval8();
                    break;
                default:
                    break;
            }
        } else {
            // Settings for sub stream
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution();
                    bitrate = clsSharedVariables.getSubbitrate();
                    quality = clsSharedVariables.getSubVideoquality();
                    framerate = clsSharedVariables.getSubFramerate();
                    cam_type = clsSharedVariables.getCam1Type();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval();
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution2();
                    bitrate = clsSharedVariables.getSubbitrate2();
                    quality = clsSharedVariables.getSubVideoquality2();
                    framerate = clsSharedVariables.getSubFramerate2();
                    cam_type = clsSharedVariables.getCam2Type();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype2();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval2();
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution3();
                    bitrate = clsSharedVariables.getSubbitrate3();
                    quality = clsSharedVariables.getSubVideoquality3();
                    framerate = clsSharedVariables.getSubFramerate3();
                    cam_type = clsSharedVariables.getCam3Type();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype3();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval3();
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution4();
                    bitrate = clsSharedVariables.getSubbitrate4();
                    quality = clsSharedVariables.getSubVideoquality4();
                    framerate = clsSharedVariables.getSubFramerate4();
                    cam_type = clsSharedVariables.getCam4Type();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype4();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval4();
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution5();
                    bitrate = clsSharedVariables.getSubbitrate5();
                    quality = clsSharedVariables.getSubVideoquality5();
                    framerate = clsSharedVariables.getSubFramerate5();
                    cam_type = clsSharedVariables.getCam5Type();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype5();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval5();
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution6();
                    bitrate = clsSharedVariables.getSubbitrate6();
                    quality = clsSharedVariables.getSubVideoquality6();
                    framerate = clsSharedVariables.getSubFramerate6();
                    cam_type = clsSharedVariables.getCam6Type();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype6();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval6();
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution7();
                    bitrate = clsSharedVariables.getSubbitrate7();
                    quality = clsSharedVariables.getSubVideoquality7();
                    framerate = clsSharedVariables.getSubFramerate7();
                    cam_type = clsSharedVariables.getCam7Type();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype7();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval7();
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution8();
                    bitrate = clsSharedVariables.getSubbitrate8();
                    quality = clsSharedVariables.getSubVideoquality8();
                    framerate = clsSharedVariables.getSubFramerate8();
                    cam_type = clsSharedVariables.getCam8Type();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    bitrate_type_control = clsSharedVariables.getSubBitratetype8();
                    frame_interval_gop = clsSharedVariables.getSubFrameInterval8();
                    break;
                default:
                    break;
            }
        }

        // Handle analog camera specific address
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        // Determine video resolution based on res value
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (res) {
                case 0:
                    width = 1920;
                    height = 1080;
                    break;
                case 1:
                    width = 1920;
                    height = 1080;
                    break;
                case 2:
                    width = 1280;
                    height = 960;
                    break;
                case 3:
                    width = 1280;
                    height = 720;
                    break;
                case 4:
                    width = 1280;
                    height = 720;
                    break;
                default:
                    break;
            }
        } else {
            // Handle resolution for sub stream
            switch (res) {
                case 0:
                    width = 704;
                    height = 576;
                    break;
                case 1:
                    width = 704;
                    height = 576;
                    break;
                case 2:
                    width = 640;
                    height = 480;
                    break;
                case 3:
                    width = 352;
                    height = 288;
                    break;
                case 4:
                    width = 352;
                    height = 288;
                    break;
                default:
                    break;
            }
        }

        // Construct URI for camera configuration update
        String uri;
        if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                // Construct URI for main stream settings update
                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.Width=" + width + "&Encode[0].MainFormat[0].Video.Height=" + height + "&Encode[0].MainFormat[0].Video.BitRate=" + bitrate + "&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].MainFormat[0].Video.Quality=" + quality + "&Encode[0].MainFormat[0].Video.FPS=" + framerate + "&Encode[0].MainFormat[0].Video.GOP=" + frame_interval_gop;
                digestUpdateData(uri, user, pwd);
            } else {
                // Construct URI for sub stream settings update
                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].ExtraFormat[0].Video.Width=" + width + "&Encode[0].ExtraFormat[0].Video.Height=" + height + "&Encode[0].ExtraFormat[0].Video.BitRate=" + bitrate + "&Encode[0].ExtraFormat[0].Video.BitRateControl=" + bitrate_type_control + "&Encode[0].ExtraFormat[0].Video.Quality=" + quality + "&Encode[0].ExtraFormat[0].Video.FPS=" + framerate + "&Encode[0].ExtraFormat[0].Video.GOP=" + frame_interval_gop;

                // Send configuration update request
                digestUpdateData(uri, user, pwd);
            }
        } else if (cam_type == clsDefines.CAM_HIKVISION) {
            // Handle HIKVISION camera specific encoding settings
            try {
                hikvision_Encoding(cam_no, stream_type);
            } catch (Exception e) {
                // Handle exceptions related to HIKVISION camera encoding
            }
        }

        // Clean up resources
        addr = null;
    }

    public synchronized void setWaterMarking(byte cam_no) {
        // Initialize variables
        String addr = null; // IP address of the camera
        String user; // Username for camera authentication
        String pwd; // Password for camera authentication
        String data; // Data to be used for watermarking
        byte cam_type; // Camera type identifier
        String uri; // URI for sending configuration commands
        String veh_reg_no = getVechicleRegNo().trim(); // Vehicle registration number retrieved from a method
        try {
            // Replace spaces in vehicle registration number with %20 for URL encoding
            veh_reg_no = veh_reg_no.replaceAll(" ", "%20");
            DecimalFormat df = new DecimalFormat("00.000000");

            // Format current latitude and longitude to 6 decimal places
            String lat = df.format(getCurLatitude());
            String lon = df.format(getCurLongitude());

            // Switch statement to handle different camera numbers
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR; // Assign IP address for Camera 1
                    user = clsSharedVariables.getCam1UserName(); // Get username for Camera 1
                    pwd = clsSharedVariables.getCam1Pwd(); // Get password for Camera 1
                    if (clsSharedVariables.getCameraIdentifer1()) {
                        data = clsSharedVariables.getCameraName1(); // Get camera name for Camera 1
                        data = data.replaceAll(" ", "%20"); // Replace spaces in camera name with %20
                    } else {
                        data = "Camera1"; // Default name if camera identifier is not set
                    }
                    if (clsSharedVariables.getCamSpeedEnable1()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed(); // Append current speed if enabled
                    }
                    if (clsSharedVariables.getCamlatlangEnable1()) {
                        data = data + "\n" + lat + "\n" + lon; // Append latitude and longitude if enabled
                    }
                    if (clsSharedVariables.getCamVehRegEnable1()) {
                        data = data + "\n" + veh_reg_no; // Append vehicle registration number if enabled
                    }
                    cam_type = clsSharedVariables.getCam1Type(); // Get camera type for Camera 1
                    break;
                // Cases 2 to 7 follow a similar pattern for Cameras 2 to 7
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    if (clsSharedVariables.getCameraIdentifer2()) {
                        data = clsSharedVariables.getCameraName2();
                        data = data.replaceAll(" ", "%20");
                    } else {
                        data = "Camera2";
                    }
                    if (clsSharedVariables.getCamSpeedEnable2()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed();
                    }
                    if (clsSharedVariables.getCamlatlangEnable2()) {
                        data = data + "\n" + lat + "\n" + lon;
                    }
                    if (clsSharedVariables.getCamVehRegEnable2()) {
                        data = data + "\n" + veh_reg_no;
                    }
                    cam_type = clsSharedVariables.getCam2Type();
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    if (clsSharedVariables.getCameraIdentifer3()) {
                        data = clsSharedVariables.getCameraName3();
                        data = data.replaceAll(" ", "%20");
                    } else {
                        data = "Camera3";
                    }
                    if (clsSharedVariables.getCamSpeedEnable3()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed();
                    }
                    if (clsSharedVariables.getCamlatlangEnable3()) {
                        data = data + "\n" + lat + "\n" + lon;
                    }
                    if (clsSharedVariables.getCamVehRegEnable3()) {
                        data = data + "\n" + veh_reg_no;
                    }
                    cam_type = clsSharedVariables.getCam3Type();
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    if (clsSharedVariables.getCameraIdentifer4()) {
                        data = clsSharedVariables.getCameraName4();
                        data = data.replaceAll(" ", "%20");
                    } else {
                        data = "Camera4";
                    }
                    if (clsSharedVariables.getCamSpeedEnable4()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed();
                    }
                    if (clsSharedVariables.getCamlatlangEnable4()) {
                        data = data + "\n" + lat + "\n" + lon;
                    }
                    if (clsSharedVariables.getCamVehRegEnable4()) {
                        data = data + "\n" + veh_reg_no;
                    }
                    cam_type = clsSharedVariables.getCam4Type();
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    if (clsSharedVariables.getCameraIdentifer5()) {
                        data = clsSharedVariables.getCameraName5();
                        data = data.replaceAll(" ", "%20");
                    } else {
                        data = "Camera5";
                    }
                    if (clsSharedVariables.getCamSpeedEnable5()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed();
                    }
                    if (clsSharedVariables.getCamlatlangEnable5()) {
                        data = data + "\n" + lat + "\n" + lon;
                    }
                    if (clsSharedVariables.getCamVehRegEnable5()) {
                        data = data + "\n" + veh_reg_no;
                    }
                    cam_type = clsSharedVariables.getCam5Type();
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    if (clsSharedVariables.getCameraIdentifer6()) {
                        data = clsSharedVariables.getCameraName6();
                        data = data.replaceAll(" ", "%20");
                    } else {
                        data = "Camera6";
                    }
                    if (clsSharedVariables.getCamSpeedEnable6()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed();
                    }
                    if (clsSharedVariables.getCamlatlangEnable6()) {
                        data = data + "\n" + lat + "\n" + lon;
                    }
                    if (clsSharedVariables.getCamVehRegEnable6()) {
                        data = data + "\n" + veh_reg_no;
                    }
                    cam_type = clsSharedVariables.getCam6Type();
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    if (clsSharedVariables.getCameraIdentifer7()) {
                        data = clsSharedVariables.getCameraName7();
                        data = data.replaceAll(" ", "%20");
                    } else {
                        data = "Camera7";
                    }
                    if (clsSharedVariables.getCamSpeedEnable7()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed();
                    }
                    if (clsSharedVariables.getCamlatlangEnable7()) {
                        data = data + "\n" + lat + "\n" + lon;
                    }
                    if (clsSharedVariables.getCamVehRegEnable7()) {
                        data = data + "\n" + veh_reg_no;
                    }
                    cam_type = clsSharedVariables.getCam7Type();
                    break;
                default:
                    addr = clsDefines.CAM8_IPADDR; // Assign IP address for Camera 8 (default case)
                    user = clsSharedVariables.getCam8UserName(); // Get username for Camera 8
                    pwd = clsSharedVariables.getCam8Pwd(); // Get password for Camera 8
                    if (clsSharedVariables.getCameraIdentifer8()) {
                        data = clsSharedVariables.getCameraName8(); // Get camera name for Camera 8
                        data = data.replaceAll(" ", "%20"); // Replace spaces in camera name with %20
                    } else {
                        data = "Camera8"; // Default name if camera identifier is not set
                    }
                    if (clsSharedVariables.getCamSpeedEnable8()) {
                        data = data + "\n" + clsSharedVariables.getCurSpeed(); // Append current speed if enabled
                    }
                    if (clsSharedVariables.getCamlatlangEnable8()) {
                        data = data + "\n" + lat + "\n" + lon; // Append latitude and longitude if enabled
                    }
                    if (clsSharedVariables.getCamVehRegEnable8()) {
                        data = data + "\n" + veh_reg_no; // Append vehicle registration number if enabled
                    }
                    cam_type = clsSharedVariables.getCam8Type(); // Get camera type for Camera 8
                    break;
            }

            // Handle analog camera type by assigning a specific IP address
            if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
                addr = clsDefines.CAM_ANALOG_IPADDR; // Assign analog camera IP address
            }

            // Handle different camera types (CPPLUS, Dahua, Hikvision)
            if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                // Construct URI for CPPLUS or Dahua cameras to set watermark data
                uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&ChannelTitle[0].Name=" + data;
                digestUpdateData(uri, user, pwd); // Send HTTP request to update watermark data
            } else if (cam_type == clsDefines.CAM_HIKVISION) {
                try {
                    hikvision_watermarking(addr, user, pwd, data); // Call method for Hikvision watermarking
                } catch (Exception e) {
                    // Handle any exceptions from Hikvision watermarking method
                }
            }
        } catch (Exception ex) {
            // Handle any exceptions that occur during execution
        }

        // Clean up variables after use
        uri = null;
        addr = null;
        user = null;
        pwd = null;
        data = null;
        veh_reg_no = null;
    }

    /**
     * Updates the bitrate control (VBR/CBR) configuration for Dahua cameras.
     *
     * @param cam_no Camera number to identify which camera's settings to
     * update.
     * @param stream_type Stream type (main or sub) to determine which stream's
     * settings to update.
     * @param bitrate_type_control The bitrate control type (VBR or CBR) to set
     * for the camera.
     */
    public synchronized void dahua_update_bitrate_control_vbr_cbr(byte cam_no, byte stream_type, String bitrate_type_control) {
        String addr = clsDefines.CAM1_IPADDR; // Default IP address
        String user = clsSharedVariables.getCam1UserName(); // Default username
        String pwd = clsSharedVariables.getCam1Pwd(); // Default password
        String uri; // URI for HTTP request

        // Determine which camera's settings to update based on cam_no
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    clsSharedVariables.setBitratetype(bitrate_type_control); // Set bitrate type for camera 1
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    clsSharedVariables.setBitratetype2(bitrate_type_control); // Set bitrate type for camera 2
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    clsSharedVariables.setBitratetype3(bitrate_type_control); // Set bitrate type for camera 3
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    clsSharedVariables.setBitratetype4(bitrate_type_control); // Set bitrate type for camera 4
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    clsSharedVariables.setBitratetype5(bitrate_type_control); // Set bitrate type for camera 5
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    clsSharedVariables.setBitratetype6(bitrate_type_control); // Set bitrate type for camera 6
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    clsSharedVariables.setBitratetype7(bitrate_type_control); // Set bitrate type for camera 7
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    clsSharedVariables.setBitratetype8(bitrate_type_control); // Set bitrate type for camera 8
                    break;
                default:
                    break;
            }
            if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
                addr = clsDefines.CAM_ANALOG_IPADDR;
            }
            uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.BitRateControl=" + bitrate_type_control; // bitrte control
            digestUpdateData(uri, user, pwd);
        } else {
            // Update sub stream bitrate control for the specified camera
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    clsSharedVariables.setSubBitratetype(bitrate_type_control); // Set sub stream bitrate type for camera 1
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    clsSharedVariables.setSubBitratetype2(bitrate_type_control); // Set sub stream bitrate type for camera 2
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    clsSharedVariables.setSubBitratetype3(bitrate_type_control); // Set sub stream bitrate type for camera 3
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    clsSharedVariables.setSubBitratetype4(bitrate_type_control); // Set sub stream bitrate type for camera 4
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    clsSharedVariables.setSubBitratetype5(bitrate_type_control); // Set sub stream bitrate type for camera 5
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    clsSharedVariables.setSubBitratetype6(bitrate_type_control); // Set sub stream bitrate type for camera 6
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    clsSharedVariables.setSubBitratetype7(bitrate_type_control); // Set sub stream bitrate type for camera 7
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    clsSharedVariables.setSubBitratetype8(bitrate_type_control); // Set sub stream bitrate type for camera 8
                    break;
                default:
                    break;
            }
        }

        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR; // Set analog camera IP address
        }

        // Construct URI for HTTP request to update bitrate control setting
        uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&Encode[0]."
                + (stream_type == clsDefines.CAM_MAIN_STREAM ? "MainFormat" : "ExtraFormat")
                + "[0].Video.BitRateControl=" + bitrate_type_control;

        digestUpdateData(uri, user, pwd); // Send HTTP request to update bitrate control

        // Clear variables
        addr = null;
        user = null;
        pwd = null;
        uri = null;
        //http://10.42.0.6/cgi-bin/configManager.cgi?action=setConfig&Encode[0].MainFormat[0].Video.BitRateControl=CBR
    }

    private boolean hikvision_Encoding(byte cam_no, byte stream_type) {
        OkHttpClient client;
        Response response;
        MediaType mediaType;
        RequestBody body;
        Request request;
        String addr = null;
        int height = 0;
        int width = 0;
        byte res = 0;
        int quality = 0;
        int framerate = 0;
        int framerate_interval = 0;
        String bitrate_type_control = "VBR";
        byte cam_type = clsSharedVariables.getCam1Type();
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
        int bitrate = 0;
        String uri;
        String[] width_arr = null;
        String[] height_arr = null;
        String[] frame_rate_arr = null;
        String[] quality_arr = null;
        String[] frame_int_arr = null;
        String[] bitrate_arr = null;
        int i = 0;
        int channel_id;
        int channel_no;
        String dat;
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
            channel_id = 101;
            channel_no = 1;
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    res = clsSharedVariables.getResolution();
                    bitrate = clsSharedVariables.getbitrate();
                    quality = clsSharedVariables.getVideoquality();
                    framerate = clsSharedVariables.getFramerate();
                    framerate_interval = clsSharedVariables.getFrameInterval();
                    cam_type = clsSharedVariables.getCam1Type();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    bitrate_type_control = clsSharedVariables.getBitratetype();
                    try {
                        width_arr = clsSharedVariables.getCam1WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam1HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam1MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam1FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam1FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam1ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }

                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    res = clsSharedVariables.getResolution2();
                    bitrate = clsSharedVariables.getbitrate2();
                    quality = clsSharedVariables.getVideoquality2();
                    framerate = clsSharedVariables.getFramerate2();
                    framerate_interval = clsSharedVariables.getFrameInterval2();
                    cam_type = clsSharedVariables.getCam2Type();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype2();
                        width_arr = clsSharedVariables.getCam2WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam2HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam2MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam2FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam2FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam2ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    res = clsSharedVariables.getResolution3();
                    bitrate = clsSharedVariables.getbitrate3();
                    quality = clsSharedVariables.getVideoquality3();
                    framerate = clsSharedVariables.getFramerate3();
                    framerate_interval = clsSharedVariables.getFrameInterval3();
                    cam_type = clsSharedVariables.getCam3Type();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype3();
                        width_arr = clsSharedVariables.getCam3WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam3HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam3MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam3FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam3FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam3ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    res = clsSharedVariables.getResolution4();
                    bitrate = clsSharedVariables.getbitrate4();
                    quality = clsSharedVariables.getVideoquality4();
                    framerate = clsSharedVariables.getFramerate4();
                    framerate_interval = clsSharedVariables.getFrameInterval4();
                    cam_type = clsSharedVariables.getCam4Type();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype4();
                        width_arr = clsSharedVariables.getCam4WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam4HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam4MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam4FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam4FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam4ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    res = clsSharedVariables.getResolution5();
                    bitrate = clsSharedVariables.getbitrate5();
                    quality = clsSharedVariables.getVideoquality5();
                    framerate = clsSharedVariables.getFramerate5();
                    framerate_interval = clsSharedVariables.getFrameInterval5();
                    cam_type = clsSharedVariables.getCam5Type();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype5();
                        width_arr = clsSharedVariables.getCam5WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam5HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam5MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam5FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam5FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam5ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    res = clsSharedVariables.getResolution6();
                    bitrate = clsSharedVariables.getbitrate6();
                    quality = clsSharedVariables.getVideoquality6();
                    framerate = clsSharedVariables.getFramerate6();
                    framerate_interval = clsSharedVariables.getFrameInterval6();
                    cam_type = clsSharedVariables.getCam6Type();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype6();
                        width_arr = clsSharedVariables.getCam6WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam6HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam6MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam6FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam6FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam6ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    res = clsSharedVariables.getResolution7();
                    bitrate = clsSharedVariables.getbitrate7();
                    quality = clsSharedVariables.getVideoquality7();
                    framerate = clsSharedVariables.getFramerate7();
                    framerate_interval = clsSharedVariables.getFrameInterval7();
                    cam_type = clsSharedVariables.getCam7Type();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype7();
                        width_arr = clsSharedVariables.getCam7WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam7HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam7MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam7FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam7FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam7ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    res = clsSharedVariables.getResolution8();
                    bitrate = clsSharedVariables.getbitrate8();
                    quality = clsSharedVariables.getVideoquality8();
                    framerate = clsSharedVariables.getFramerate8();
                    framerate_interval = clsSharedVariables.getFrameInterval8();
                    cam_type = clsSharedVariables.getCam8Type();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype8();

                        width_arr = clsSharedVariables.getCam8WidthArr().split(",");
                        height_arr = clsSharedVariables.getCam8HeightArr().split(",");
                        frame_rate_arr = clsSharedVariables.getCam8MaxFramerateArr().split(",");
                        quality_arr = clsSharedVariables.getCam8FixedQualityArr().split(",");
                        frame_int_arr = clsSharedVariables.getCam8FrameIntervalArr().split(",");
                        bitrate_arr = clsSharedVariables.getCam8ConstBitrateArr().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                default:
                    break;
            }
        } else {
            uri = "http://" + addr + "/ISAPI/Streaming/channels/102/";
            channel_id = 102;
            channel_no = 2;
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution();
                    bitrate = clsSharedVariables.getSubbitrate();
                    quality = clsSharedVariables.getSubVideoquality();
                    framerate = clsSharedVariables.getSubFramerate();
                    framerate_interval = clsSharedVariables.getSubFrameInterval();
                    cam_type = clsSharedVariables.getCam1Type();
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype();

                        width_arr = clsSharedVariables.getCam1WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam1HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam1MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam1FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam1FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam1ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution2();
                    bitrate = clsSharedVariables.getSubbitrate2();
                    quality = clsSharedVariables.getSubVideoquality2();
                    framerate = clsSharedVariables.getSubFramerate2();
                    framerate_interval = clsSharedVariables.getSubFrameInterval2();
                    cam_type = clsSharedVariables.getCam2Type();
                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype2();
                        width_arr = clsSharedVariables.getCam2WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam2HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam2MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam2FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam2FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam2ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution3();
                    bitrate = clsSharedVariables.getSubbitrate3();
                    quality = clsSharedVariables.getSubVideoquality3();
                    framerate = clsSharedVariables.getSubFramerate3();
                    framerate_interval = clsSharedVariables.getSubFrameInterval3();
                    cam_type = clsSharedVariables.getCam3Type();
                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype3();
                        width_arr = clsSharedVariables.getCam3WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam3HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam3MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam3FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam3FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam3ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution4();
                    bitrate = clsSharedVariables.getSubbitrate4();
                    quality = clsSharedVariables.getSubVideoquality4();
                    framerate = clsSharedVariables.getSubFramerate4();
                    framerate_interval = clsSharedVariables.getSubFrameInterval4();
                    cam_type = clsSharedVariables.getCam4Type();
                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype4();
                        width_arr = clsSharedVariables.getCam4WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam4HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam4MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam4FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam4FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam4ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution5();
                    bitrate = clsSharedVariables.getSubbitrate5();
                    quality = clsSharedVariables.getSubVideoquality5();
                    framerate = clsSharedVariables.getSubFramerate5();
                    framerate_interval = clsSharedVariables.getSubFrameInterval5();
                    cam_type = clsSharedVariables.getCam5Type();
                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype5();
                        width_arr = clsSharedVariables.getCam5WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam5HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam5MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam5FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam5FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam5ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution6();
                    bitrate = clsSharedVariables.getSubbitrate6();
                    quality = clsSharedVariables.getSubVideoquality6();
                    framerate = clsSharedVariables.getSubFramerate6();
                    framerate_interval = clsSharedVariables.getSubFrameInterval6();
                    cam_type = clsSharedVariables.getCam6Type();
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype6();
                        width_arr = clsSharedVariables.getCam6WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam6HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam6MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam6FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam6FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam6ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution7();
                    bitrate = clsSharedVariables.getSubbitrate7();
                    quality = clsSharedVariables.getSubVideoquality7();
                    framerate = clsSharedVariables.getSubFramerate7();
                    framerate_interval = clsSharedVariables.getSubFrameInterval7();
                    cam_type = clsSharedVariables.getCam7Type();
                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype7();
                        width_arr = clsSharedVariables.getCam7WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam7HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam7MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam7FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam7FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam7ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;
                    res = clsSharedVariables.getSubstreamResolution8();
                    bitrate = clsSharedVariables.getSubbitrate8();
                    quality = clsSharedVariables.getSubVideoquality8();
                    framerate = clsSharedVariables.getSubFramerate8();
                    framerate_interval = clsSharedVariables.getSubFrameInterval8();
                    cam_type = clsSharedVariables.getCam8Type();
                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();
                    try {
                        bitrate_type_control = clsSharedVariables.getBitratetype8();
                        width_arr = clsSharedVariables.getCam8WidthArrSub().split(",");
                        height_arr = clsSharedVariables.getCam8HeightArrSub().split(",");
                        frame_rate_arr = clsSharedVariables.getCam8MaxFramerateArrSub().split(",");
                        quality_arr = clsSharedVariables.getCam8FixedQualityArrSub().split(",");
                        frame_int_arr = clsSharedVariables.getCam8FrameIntervalArrSub().split(",");
                        bitrate_arr = clsSharedVariables.getCam8ConstBitrateArrSub().split(",");
                    } catch (Exception ex) {

                    }
                    break;
                default:
                    break;
            }
        }

        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                switch (cam_no) {
                    case 1:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
                        channel_id = 101;
                        channel_no = 1;
                        break;
                    case 2:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/201/";
                        channel_id = 201;
                        channel_no = 2;
                        break;
                    case 3:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/301/";
                        channel_id = 301;
                        channel_no = 3;
                        break;
                    case 4:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/401/";
                        channel_id = 401;
                        channel_no = 4;
                        break;
                    case 5:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/501/";
                        channel_id = 501;
                        channel_no = 5;
                        break;
                    case 6:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/601/";
                        channel_id = 601;
                        channel_no = 6;
                        break;
                    case 7:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/701/";
                        channel_id = 701;
                        channel_no = 7;
                        break;
                    case 8:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/801/";
                        channel_id = 801;
                        channel_no = 8;
                        break;
                }
            } else {
                switch (cam_no) {
                    case 1:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/102/";
                        channel_id = 102;
                        channel_no = 1;
                        break;
                    case 2:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/202/";
                        channel_id = 202;
                        channel_no = 2;
                        break;
                    case 3:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/302/";
                        channel_id = 302;
                        channel_no = 3;
                        break;
                    case 4:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/402/";
                        channel_id = 402;
                        channel_no = 4;
                        break;
                    case 5:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/502/";
                        channel_id = 502;
                        channel_no = 5;
                        break;
                    case 6:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/602/";
                        channel_id = 602;
                        channel_no = 6;
                        break;
                    case 7:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/702/";
                        channel_id = 702;
                        channel_no = 7;
                        break;
                    case 8:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/802/";
                        channel_id = 802;
                        channel_no = 8;
                        break;

                }
            }
        } else {
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
            } else {
                uri = "http://" + addr + "/ISAPI/Streaming/channels/102/";
            }
        }
        String authStr = user + ":" + pwd;
        String auth = "Basic " + Base64.getEncoder()
                .encodeToString(authStr.getBytes());

        if (width_arr != null) {
            if (res > 0) {
                // width = Integer.parseInt(width_arr[res - 1]);
                //height = Integer.parseInt(height_arr[res - 1]);

                if (width_arr.length >= 5) {
                    switch (res) {
                        case 1:
                            width = Integer.parseInt(width_arr[4]);
                            height = Integer.parseInt(height_arr[4]);
                            break;
                        case 2:
                            width = Integer.parseInt(width_arr[3]);
                            height = Integer.parseInt(height_arr[3]);
                            break;
                        case 3:
                            width = Integer.parseInt(width_arr[2]);
                            height = Integer.parseInt(height_arr[2]);
                            break;
                        case 4:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;
                        case 5:
                            width = Integer.parseInt(width_arr[0]);
                            height = Integer.parseInt(height_arr[0]);
                            break;
                        default:
                            break;
                    }
                } else if (width_arr.length >= 4) {
                    switch (res) {
                        case 1:
                            width = Integer.parseInt(width_arr[3]);
                            height = Integer.parseInt(height_arr[3]);
                            break;
                        case 2:
                            width = Integer.parseInt(width_arr[2]);
                            height = Integer.parseInt(height_arr[2]);
                            break;
                        case 3:
                        case 4:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;

                        case 5:
                            width = Integer.parseInt(width_arr[0]);
                            height = Integer.parseInt(height_arr[0]);
                            break;

                        default:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;
                    }
                } else if (width_arr.length >= 3) {
                    switch (res) {
                        case 1:
                            width = Integer.parseInt(width_arr[2]);
                            height = Integer.parseInt(height_arr[2]);
                            break;
                        case 2:
                        case 3:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;
                        case 4:
                        case 5:
                            width = Integer.parseInt(width_arr[0]);
                            height = Integer.parseInt(height_arr[0]);
                            break;

                        default:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;
                    }
                } else if (width_arr.length >= 2) {
                    switch (res) {
                        case 1:
                        case 2:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;
                        case 3:
                        case 4:
                        case 5:
                            width = Integer.parseInt(width_arr[0]);
                            height = Integer.parseInt(height_arr[0]);
                            break;

                        default:
                            width = Integer.parseInt(width_arr[1]);
                            height = Integer.parseInt(height_arr[1]);
                            break;
                    }
                }

            }
        } else {
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                switch (res) {
                    case 1:
                        width = 1920;
                        height = 1080;
                        break;
                    case 2:
                        width = 1920;
                        height = 1080;
                        break;
                    case 3:
                        width = 1280;
                        height = 960;
                        break;
                    case 4:
                        width = 1280;
                        height = 720;
                        break;
                    case 5:
                        width = 1280;
                        height = 720;
                        break;
                    default:
                        break;
                }
            } else {
                switch (res) {
                    case 1:
                        width = 704;
                        height = 576;
                        break;
                    case 2:
                        width = 704;
                        height = 576;
                        break;
                    case 3:
                        width = 640;
                        height = 480;
                        break;
                    case 4:
                        width = 352;
                        height = 288;
                        break;
                    case 5:
                        width = 352;
                        height = 288;
                        break;
                    default:
                        break;
                }
            }

        }

        //frame_rate_arr {2500,2200,2000,1800,....6}
        framerate = framerate * 100;
        try {

            for (i = frame_rate_arr.length - 1; i >= 0; i--) {
                if (framerate <= Integer.parseInt(frame_rate_arr[i])) {
                    framerate = Integer.parseInt(frame_rate_arr[i]);
                    break;
                }
            }

        } catch (Exception ex) {

        }

        //quality_arr {1,20,40,60,80,100}
        try {
            if (quality < quality_arr.length) {
                quality = Integer.parseInt(quality_arr[quality]);
            }
        } catch (Exception ex) {

        }

        //frame_int_arr {1,400}
        try {
            if (framerate_interval > Integer.parseInt(frame_int_arr[1])) {
                framerate_interval = Integer.parseInt(frame_int_arr[1]);
            } else if (framerate_interval < Integer.parseInt(frame_int_arr[0])) {
                framerate_interval = Integer.parseInt(frame_int_arr[0]);
            }
        } catch (Exception ex) {

        }
        //bitrate_arr {32,8192}
        try {
            if (bitrate > Integer.parseInt(bitrate_arr[1])) {
                bitrate = Integer.parseInt(bitrate_arr[1]);
            } else if (bitrate < Integer.parseInt(bitrate_arr[0])) {
                bitrate = Integer.parseInt(bitrate_arr[0]);
            }
        } catch (Exception ex) {

        }

        //frame rate:  2500,2200,2000,1800,1600,1500,1200,1000,800,600,400,200,100,50,25,12,6 ( 1 to 25)
        //quality 1,20,40,60,80,100 ( 1 to 6)
        //frame interval min="1" max="400"
        //max bitrate min="32" max="8192"
        /*  if (frame_rate_arr != null) {

             
           if (framerate < 25) { 
                try {
                    switch (framerate) {
                        case 1:
                            framerate = Integer.parseInt(frame_rate_arr[frame_rate_arr.length - 1]);
                            break;
                        case 2:
                            framerate = Integer.parseInt(frame_rate_arr[frame_rate_arr.length - 2]);
                            break;
                        case 3:
                            framerate = Integer.parseInt(frame_rate_arr[frame_rate_arr.length - 3]);
                            break;
                        case 4:
                            framerate = Integer.parseInt(frame_rate_arr[frame_rate_arr.length - 4]);
                            break;
                        case 5:
                            framerate = 100;
                            break;
                        case 6:
                            framerate = 300;
                            break;
                        case 25:
                            framerate = Integer.parseInt(frame_rate_arr[0]);
                            break;
                        default:
                            framerate = framerate * 100;
                            break;
                    }

                } catch (Exception ex) {
                    framerate = framerate * 100;
                }
            }
        }*/
        //quality 1,20,40,60,80,100 ( 1 to 6)
        /*   if (quality_arr != null) {
            try {
                if (quality_arr != null) {
                    switch (quality) {
                        case 1:
                            quality = Integer.parseInt(quality_arr[0]);
                            break;
                        case 2:
                            quality = Integer.parseInt(quality_arr[1]);
                            break;
                        case 3:
                            quality = Integer.parseInt(quality_arr[2]);
                            break;
                        case 4:
                            quality = Integer.parseInt(quality_arr[3]);
                            break;
                        case 5:
                            quality = Integer.parseInt(quality_arr[4]);
                            break;
                        case 6:
                            quality = Integer.parseInt(quality_arr[5]);
                            break;

                    }
                }
            } catch (Exception ex) {
                quality = quality * 10;

            }
            
        }*/
        try {
            /*    if ("VBR".equals(bitrate_type_control)) {
                uri = "http://www.hikvision.com/ver20/XMLSchema\" version=\"2.0\">\r\n<Video>\r\n<enabled>true</enabled>\r\n<videoInputChannelID>1</videoInputChannelID>\r\n<videoCodecType>H.264</videoCodecType>\r\n<videoScanType>progressive</videoScanType>\r\n<videoResolutionWidth>" + width + "</videoResolutionWidth>\r\n<videoResolutionHeight>" + height + "</videoResolutionHeight>\r\n<videoQualityControlType>" + bitrate_type_control + "</videoQualityControlType>\r\n<constantBitRate>6144</constantBitRate>\r\n<fixedQuality>60</fixedQuality>\r\n<vbrUpperCap>" + bitrate + "</vbrUpperCap>\r\n<vbrLowerCap>32</vbrLowerCap>\r\n<maxFrameRate>" + framerate + "000</maxFrameRate>\r\n<keyFrameInterval>2000</keyFrameInterval>\r\n<snapShotImageType>JPEG</snapShotImageType>\r\n<H264Profile>Main</H264Profile>\r\n<GovLength>50</GovLength>\r\n<SVC>\r\n<enabled>false</enabled>\r\n</SVC>\r\n<PacketType>PS</PacketType>\r\n<PacketType>RTP</PacketType>\r\n<smoothing>50</smoothing>\r\n<H265Profile>Main</H265Profile>\r\n<SmartCodec>\r\n<enabled>false</enabled>\r\n</SmartCodec>\r\n</Video>\r\n</StreamingChannel>"; //2020-01-08T18:44:38
            } else {
                uri = "http://www.hikvision.com/ver20/XMLSchema\" version=\"2.0\">\r\n<Video>\r\n<enabled>true</enabled>\r\n<videoInputChannelID>1</videoInputChannelID>\r\n<videoCodecType>H.264</videoCodecType>\r\n<videoScanType>progressive</videoScanType>\r\n<videoResolutionWidth>" + width + "</videoResolutionWidth>\r\n<videoResolutionHeight>" + height + "</videoResolutionHeight>\r\n<videoQualityControlType>" + bitrate_type_control + "</videoQualityControlType>\r\n<constantBitRate>" + bitrate + "</constantBitRate>\r\n<fixedQuality>60</fixedQuality>\r\n<vbrUpperCap>256</vbrUpperCap>\r\n<vbrLowerCap>32</vbrLowerCap>\r\n<maxFrameRate>" + framerate + "000</maxFrameRate>\r\n<keyFrameInterval>2000</keyFrameInterval>\r\n<snapShotImageType>JPEG</snapShotImageType>\r\n<H264Profile>Main</H264Profile>\r\n<GovLength>50</GovLength>\r\n<SVC>\r\n<enabled>false</enabled>\r\n</SVC>\r\n<PacketType>PS</PacketType>\r\n<PacketType>RTP</PacketType>\r\n<smoothing>50</smoothing>\r\n<H265Profile>Main</H265Profile>\r\n<SmartCodec>\r\n<enabled>false</enabled>\r\n</SmartCodec>\r\n</Video>\r\n</StreamingChannel>";

            }
            digestUpdateData(uri,user,pwd);*/

            // get_hikvision_encoding_params_data(uri, auth);
            client = new OkHttpClient();

            mediaType = MediaType.parse("application/xml");

// if ("VBR".equals(bitrate_type_control)) {
            //  body = RequestBody.create(mediaType, "<StreamingChannel xmlns=\"http://www.hikvision.com/ver20/XMLSchema\" version=\"2.0\">\r\n<Video>\r\n<enabled>true</enabled>\r\n<videoInputChannelID>1</videoInputChannelID>\r\n<videoCodecType>H.264</videoCodecType>\r\n<videoScanType>progressive</videoScanType>\r\n<videoResolutionWidth>" + width + "</videoResolutionWidth>\r\n<videoResolutionHeight>" + height + "</videoResolutionHeight>\r\n<videoQualityControlType>" + bitrate_type_control + "</videoQualityControlType>\r\n<constantBitRate>6144</constantBitRate>\r\n<fixedQuality>60</fixedQuality>\r\n<vbrUpperCap>" + bitrate + "</vbrUpperCap>\r\n<vbrLowerCap>32</vbrLowerCap>\r\n<maxFrameRate>" + framerate + "000</maxFrameRate>\r\n<keyFrameInterval>2000</keyFrameInterval>\r\n<snapShotImageType>JPEG</snapShotImageType>\r\n<H264Profile>Main</H264Profile>\r\n<GovLength>50</GovLength>\r\n<SVC>\r\n<enabled>false</enabled>\r\n</SVC>\r\n<PacketType>PS</PacketType>\r\n<PacketType>RTP</PacketType>\r\n<smoothing>50</smoothing>\r\n<H265Profile>Main</H265Profile>\r\n<SmartCodec>\r\n<enabled>false</enabled>\r\n</SmartCodec>\r\n</Video>\r\n</StreamingChannel>"); //2020-01-08T18:44:38
            // } else {
            //body = RequestBody.create(mediaType, "<StreamingChannel xmlns=\"http://www.hikvision.com/ver20/XMLSchema\" version=\"2.0\">\r\n<Video>\r\n<enabled>true</enabled>\r\n<videoInputChannelID>1</videoInputChannelID>\r\n<videoCodecType>H.264</videoCodecType>\r\n<videoScanType>progressive</videoScanType>\r\n<videoResolutionWidth>" + width + "</videoResolutionWidth>\r\n<videoResolutionHeight>" + height + "</videoResolutionHeight>\r\n<videoQualityControlType>" + bitrate_type_control + "</videoQualityControlType>\r\n<constantBitRate>" + bitrate + "</constantBitRate>\r\n<fixedQuality>60</fixedQuality>\r\n<vbrUpperCap>256</vbrUpperCap>\r\n<vbrLowerCap>32</vbrLowerCap>\r\n<maxFrameRate>" + framerate + "000</maxFrameRate>\r\n<keyFrameInterval>2000</keyFrameInterval>\r\n<snapShotImageType>JPEG</snapShotImageType>\r\n<H264Profile>Main</H264Profile>\r\n<GovLength>50</GovLength>\r\n<SVC>\r\n<enabled>false</enabled>\r\n</SVC>\r\n<PacketType>PS</PacketType>\r\n<PacketType>RTP</PacketType>\r\n<smoothing>50</smoothing>\r\n<H265Profile>Main</H265Profile>\r\n<SmartCodec>\r\n<enabled>false</enabled>\r\n</SmartCodec>\r\n</Video>\r\n</StreamingChannel>"); //2020-01-08T18:44:38
            // }
            if ("CBR".equals(bitrate_type_control)) {

                dat = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                        + "<StreamingChannel version=\"1.0\" xmlns=\"http://www.hikvision.com/ver20/XMLSchema\">\n"
                        + "    <id>" + channel_id + "</id>\n" //101
                        + "    <channelName>" + channel_id + "</channelName>\n" //1
                        + "    <enabled>true</enabled>\n"
                        + "    <Transport>\n"
                        + "        <ControlProtocolList>\n"
                        + "            <ControlProtocol>\n"
                        + "                <streamingTransport>RTSP</streamingTransport>\n"
                        + "            </ControlProtocol>\n"
                        + "        </ControlProtocolList>\n"
                        + "    </Transport>\n"
                        + "    <Video>\n"
                        + "        <enabled>true</enabled>\n"
                        + "        <videoInputChannelID>" + channel_no + "</videoInputChannelID>\n" //1
                        + "        <videoCodecType>H.264</videoCodecType>\n"
                        + "        <videoResolutionWidth>" + width + "</videoResolutionWidth>\n"
                        + "        <videoResolutionHeight>" + height + "</videoResolutionHeight>\n"
                        + "        <videoQualityControlType>" + bitrate_type_control + "</videoQualityControlType>\n"
                        + "        <constantBitRate>" + bitrate + "</constantBitRate>\n" // 512
                        + "        <fixedQuality>" + quality + "</fixedQuality>\n" //min 20 to 30, 45, 60, 75,90 (for VBR)
                        + "          <maxFrameRate>" + framerate + "</maxFrameRate>\n" //22 means 22 *100(2200),1 (100), (max framrate (0))
                        + "          <GovLength>" + framerate_interval + "</GovLength>\n"
                        + "       \n"
                        + "       \n"
                        + "    </Video>\n"
                        + "   \n"
                        + "</StreamingChannel>";
            } else {
                dat = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                        + "<StreamingChannel version=\"1.0\" xmlns=\"http://www.hikvision.com/ver20/XMLSchema\">\n"
                        + "    <id>" + channel_id + "</id>\n" //101
                        + "    <channelName>" + channel_id + "</channelName>\n" //1
                        + "    <enabled>true</enabled>\n"
                        + "    <Transport>\n"
                        + "        <ControlProtocolList>\n"
                        + "            <ControlProtocol>\n"
                        + "                <streamingTransport>RTSP</streamingTransport>\n"
                        + "            </ControlProtocol>\n"
                        + "        </ControlProtocolList>\n"
                        + "    </Transport>\n"
                        + "    <Video>\n"
                        + "        <enabled>true</enabled>\n"
                        + "        <videoInputChannelID>" + channel_no + "</videoInputChannelID>\n" //1
                        + "        <videoCodecType>H.264</videoCodecType>\n"
                        + "        <videoResolutionWidth>" + width + "</videoResolutionWidth>\n"
                        + "        <videoResolutionHeight>" + height + "</videoResolutionHeight>\n"
                        + "        <videoQualityControlType>" + bitrate_type_control + "</videoQualityControlType>\n"
                        + "        <constantBitRate>" + bitrate + "</constantBitRate>\n" // 512
                        + "        <fixedQuality>" + quality + "</fixedQuality>\n" //min 20 to 30, 45, 60, 75,90 (for VBR)
                        + "        <vbrUpperCap>" + bitrate + "</vbrUpperCap>\n"
                        + "        <vbrLowerCap>32</vbrLowerCap>\n"
                        + "         <maxFrameRate>" + framerate + "</maxFrameRate>\n" //22 means 22 *100(2200),1 (100), (max framrate (0))
                        + "          <GovLength>" + framerate_interval + "</GovLength>\n"
                        + "       \n"
                        + "       \n"
                        + "    </Video>\n"
                        + "   \n"
                        + "</StreamingChannel>";
            }

            body = RequestBody.create(mediaType, dat);
            request = new Request.Builder()
                    .url("http://" + addr + "/ISAPI/Streaming/channels/" + channel_id + "/")
                    .put(body)
                    .addHeader("Content-Type", "application/xml")
                    .addHeader("Authorization", auth)
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "nocache")
                    .addHeader("Host", addr)
                    .addHeader("accept-encoding", "gzip, deflate")
                    .addHeader("content-length", "170")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request).execute();
            return response.code() == 200; ////System.out.println("hikvision Encoding Saved Success for Cam " + cam_no);
            ////System.out.println("hikvision Encoding failure for Cam " + cam_no);
        } catch (Exception ex) {
        } finally {
            client = null;
            response = null;
            mediaType = null;
            body = null;
            request = null;
            addr = null;
        }
        return false;
    }

    public void get_hikvision_encoding_params_data(byte cam_no, byte stream_type) {
        HttpResponse response;
        String addr = null;
        int height = 0;
        int width = 0;
        int quality = 0;
        int framerate = 0;
        String bitrate_type_control = "VBR";
        int bitrate = 0;
        int frame_interval = 0;

        String quality_control_arr = null;
        String constant_bitrate_min_max_arr = null;
        String fixed_quality_arr = null;
        String max_framerate_arr = null;
        String width_arr = null;
        String height_arr = null;
        String frame_interval_arr = null;
        int i = 0;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
        String uri;
        HttpGet httpget; // Set the action you want to do
        HttpEntity entity;
        InputStream is; // Create an InputStream with the response

        HttpClient httpclient;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        String[] split_str_wid;
        String[] split_str_hei;

        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;
                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();
                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;

                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();

                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;

                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();

                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;

                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();

                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;

                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();

                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;
                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();

                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;

                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();

                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;

                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();

                    break;
                default:
                    break;
            }
        } else {
            uri = "http://" + addr + "/ISAPI/Streaming/channels/102/";
            switch (cam_no) {
                case 1:
                    addr = clsDefines.CAM1_IPADDR;

                    user = clsSharedVariables.getCam1UserName();
                    pwd = clsSharedVariables.getCam1Pwd();

                    break;
                case 2:
                    addr = clsDefines.CAM2_IPADDR;

                    user = clsSharedVariables.getCam2UserName();
                    pwd = clsSharedVariables.getCam2Pwd();

                    break;
                case 3:
                    addr = clsDefines.CAM3_IPADDR;

                    user = clsSharedVariables.getCam3UserName();
                    pwd = clsSharedVariables.getCam3Pwd();

                    break;
                case 4:
                    addr = clsDefines.CAM4_IPADDR;

                    user = clsSharedVariables.getCam4UserName();
                    pwd = clsSharedVariables.getCam4Pwd();

                    break;
                case 5:
                    addr = clsDefines.CAM5_IPADDR;

                    user = clsSharedVariables.getCam5UserName();
                    pwd = clsSharedVariables.getCam5Pwd();

                    break;
                case 6:
                    addr = clsDefines.CAM6_IPADDR;

                    user = clsSharedVariables.getCam6UserName();
                    pwd = clsSharedVariables.getCam6Pwd();

                    break;
                case 7:
                    addr = clsDefines.CAM7_IPADDR;

                    user = clsSharedVariables.getCam7UserName();
                    pwd = clsSharedVariables.getCam7Pwd();

                    break;
                case 8:
                    addr = clsDefines.CAM8_IPADDR;

                    user = clsSharedVariables.getCam8UserName();
                    pwd = clsSharedVariables.getCam8Pwd();

                    break;
                default:
                    break;
            }
        }

        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                switch (cam_no) {
                    case 1:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
                        break;
                    case 2:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/201/";
                        break;
                    case 3:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/301/";
                        break;
                    case 4:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/401/";
                        break;
                    case 5:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/501/";
                        break;
                    case 6:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/601/";
                        break;
                    case 7:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/701/";
                        break;
                    case 8:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/801/";
                        break;
                }
            } else {
                switch (cam_no) {
                    case 1:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/102/";
                        break;
                    case 2:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/202/";
                        break;
                    case 3:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/302/";
                        break;
                    case 4:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/402/";
                        break;
                    case 5:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/502/";
                        break;
                    case 6:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/602/";
                        break;
                    case 7:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/702/";
                        break;
                    case 8:
                        uri = "http://" + addr + "/ISAPI/Streaming/channels/802/";
                        break;

                }
            }
        } else {
            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                uri = "http://" + addr + "/ISAPI/Streaming/channels/101/";
            } else {
                uri = "http://" + addr + "/ISAPI/Streaming/channels/102/";
            }
        }
        uri = uri + "capabilities";
        //  String auth = "Basic " + Base64.getEncoder()
        // .encodeToString(authStr.getBytes());
        try {

            httpclient = new DefaultHttpClient();
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();

            try {
                httpget = new HttpGet(uri); // Set the action you want to do
                httpget.addHeader(BasicScheme.authenticate(
                        new UsernamePasswordCredentials(user, pwd),
                        "UTF-8", false));
                httpget.addHeader("content-type", "application/xml");
                response = httpclient.execute(httpget); // Executeit
                if (response.getStatusLine().getStatusCode() == 200) {
                    entity = response.getEntity();

                    is = entity.getContent(); // Create an InputStream with the response

                    Document doc = builder.parse(is);
                    // Element root = doc.getDocumentElement();
                    doc.getDocumentElement().normalize();
                    //System.out("Root element :" + doc.getDocumentElement().getNodeName());
                    NodeList nList = doc.getElementsByTagName("Video");
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        //System.out("\nCurrent Element :" + nNode.getNodeName());
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            //System.out("videoInputChannelID : "                            +eElement.getAttribute("videoInputChannelID"));

                            ////System.out.println("videoInputChannelID : "                                    +eElement                                    .getElementsByTagName("videoInputChannelID")                                    .item(0)                                    .getTextContent()                            );
                            String node_values = eElement.getElementsByTagName("videoCodecType").item(0).getAttributes().item(0).getNodeValue();
                            //System.out("videoCodecType Nodes: " + node_values);

                            ////System.out.println("videoCodecType : " + eElement.getElementsByTagName("videoCodecType").item(0).getTextContent());
                            width_arr = ((eElement.getElementsByTagName("videoResolutionWidth").item(0).getAttributes().item(0).getNodeValue().toString()));

                            ////System.out.println("videoResolutionWidth Nodes: " + width_arr);
                            width = Integer.parseInt(eElement.getElementsByTagName("videoResolutionWidth").item(0).getTextContent());
                            ////System.out.println("videoResolutionWidth : "                            +width                            );

                            height_arr = (eElement.getElementsByTagName("videoResolutionHeight").item(0).getAttributes().item(0).getNodeValue());
                            //System.out("videoResolutionHeight Nodes: " + height_arr);

                            height = Integer.parseInt(eElement.getElementsByTagName("videoResolutionHeight").item(0).getTextContent());
                            ////System.out.println("videoResolutionHeight : "                            +eElement.getElementsByTagName("videoResolutionHeight").item(0).getTextContent()                            );

                            //quality control VBR,CBR
                            quality_control_arr = (eElement.getElementsByTagName("videoQualityControlType").item(0).getAttributes().item(0).getNodeValue());
                            ////System.out.println("videoQualityControlType Nodes: " + quality_control_arr);

                            bitrate_type_control = (eElement.getElementsByTagName("videoQualityControlType").item(0).getTextContent());
                            ////System.out.println("videoQualityControlType : "
                            //  +eElement.getElementsByTagName("videoQualityControlType").item(0).getTextContent()                            );

                            //constant bitrate min and maximum in array ( min="32" max="8192")<constantBitRate min="32" max="8192">256</constantBitRate>
                            if (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().getLength() == 2) {
                                if (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().item(1).getNodeName().equals("min")) {
                                    constant_bitrate_min_max_arr = (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().item(1).getNodeValue());
                                    constant_bitrate_min_max_arr = constant_bitrate_min_max_arr + "," + (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().item(0).getNodeValue());

                                } else {
                                    constant_bitrate_min_max_arr = (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().item(0).getNodeValue());
                                    constant_bitrate_min_max_arr = constant_bitrate_min_max_arr + "," + (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().item(1).getNodeValue());

                                }

                            } else {
                                constant_bitrate_min_max_arr = (eElement.getElementsByTagName("constantBitRate").item(0).getAttributes().item(0).getNodeValue());

                            }
                            //System.out("constantBitRate Nodes: " + constant_bitrate_min_max_arr);

                            bitrate = Integer.parseInt(eElement.getElementsByTagName("constantBitRate").item(0).getTextContent());

                            fixed_quality_arr = (eElement.getElementsByTagName("fixedQuality").item(0).getAttributes().item(0).getNodeValue());
                            //System.out("fixedQuality Nodes: " + fixed_quality_arr);

                            quality = Integer.parseInt(eElement.getElementsByTagName("fixedQuality").item(0).getTextContent());

                            max_framerate_arr = (eElement.getElementsByTagName("maxFrameRate").item(0).getAttributes().item(0).getNodeValue());
                            //System.out("maxFrameRate Nodes: " + max_framerate_arr);

                            framerate = Integer.parseInt(eElement.getElementsByTagName("maxFrameRate").item(0).getTextContent());
                            frame_interval_arr = (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(0).getNodeValue());
                            //System.out("GovLength frame interval length Nodes: " + frame_interval_arr);

                            if (eElement.getElementsByTagName("GovLength").item(0).getAttributes().getLength() == 2) {
                                if (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(1).getNodeName().equals("min")) {
                                    frame_interval_arr = (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(1).getNodeValue());
                                    frame_interval_arr = frame_interval_arr + "," + (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(0).getNodeValue());

                                } else {
                                    frame_interval_arr = (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(0).getNodeValue());
                                    frame_interval_arr = frame_interval_arr + "," + (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(1).getNodeValue());

                                }

                            } else {
                                frame_interval_arr = (eElement.getElementsByTagName("GovLength").item(0).getAttributes().item(0).getNodeValue());

                            }
                            //System.out("frma interval aaray : "                            +frame_interval_arr                            );

                            frame_interval = Integer.parseInt(eElement.getElementsByTagName("GovLength").item(0).getTextContent());
                            //System.out("GovLength Frame Inteval : "                            +frame_interval    );

                        }
                    }

                    is.close();
                }

            } catch (Exception ex) {
            }

            if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                switch (cam_no) {
                    case 1:
                        clsSharedVariables.setCam1WidthArr(width_arr);
                        clsSharedVariables.setCam1HeightArr(height_arr);
                        clsSharedVariables.setCam1QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam1ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam1FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam1MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam1FrameIntervalArr(frame_interval_arr);

                        break;
                    case 2:

                        clsSharedVariables.setCam2WidthArr(width_arr);
                        clsSharedVariables.setCam2HeightArr(height_arr);
                        clsSharedVariables.setCam2QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam2ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam2FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam2MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam2FrameIntervalArr(frame_interval_arr);

                        break;
                    case 3:

                        clsSharedVariables.setCam3WidthArr(width_arr);
                        clsSharedVariables.setCam3HeightArr(height_arr);
                        clsSharedVariables.setCam3QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam3ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam3FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam3MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam3FrameIntervalArr(frame_interval_arr);
                        break;
                    case 4:
                        clsSharedVariables.setCam4WidthArr(width_arr);
                        clsSharedVariables.setCam4HeightArr(height_arr);
                        clsSharedVariables.setCam4QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam4ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam4FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam4MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam4FrameIntervalArr(frame_interval_arr);
                        break;
                    case 5:
                        clsSharedVariables.setCam5WidthArr(width_arr);
                        clsSharedVariables.setCam5HeightArr(height_arr);
                        clsSharedVariables.setCam5QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam5ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam5FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam5MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam5FrameIntervalArr(frame_interval_arr);
                        break;
                    case 6:

                        clsSharedVariables.setCam6WidthArr(width_arr);
                        clsSharedVariables.setCam6HeightArr(height_arr);
                        clsSharedVariables.setCam6QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam6ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam6FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam6MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam6FrameIntervalArr(frame_interval_arr);
                        break;
                    case 7:

                        clsSharedVariables.setCam7WidthArr(width_arr);
                        clsSharedVariables.setCam7HeightArr(height_arr);
                        clsSharedVariables.setCam7QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam7ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam7FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam7MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam7FrameIntervalArr(frame_interval_arr);
                        break;
                    case 8:

                        clsSharedVariables.setCam8WidthArr(width_arr);
                        clsSharedVariables.setCam8HeightArr(height_arr);
                        clsSharedVariables.setCam8QualityCtrlArr(quality_control_arr);
                        clsSharedVariables.setCam8ConstBitrateArr(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam8FixedQualityArr(fixed_quality_arr);
                        clsSharedVariables.setCam8MaxFramerateArr(max_framerate_arr);
                        clsSharedVariables.setCam8FrameIntervalArr(frame_interval_arr);
                        break;
                }
            } else {
                switch (cam_no) {
                    case 1:
                        clsSharedVariables.setCam1WidthArrSub(width_arr);
                        clsSharedVariables.setCam1HeightArrSub(height_arr);
                        clsSharedVariables.setCam1QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam1ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam1FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam1MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam1FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 2:

                        clsSharedVariables.setCam2WidthArrSub(width_arr);
                        clsSharedVariables.setCam2HeightArrSub(height_arr);
                        clsSharedVariables.setCam2QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam2ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam2FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam2MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam2FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 3:

                        clsSharedVariables.setCam3WidthArrSub(width_arr);
                        clsSharedVariables.setCam3HeightArrSub(height_arr);
                        clsSharedVariables.setCam3QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam3ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam3FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam3MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam3FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 4:
                        clsSharedVariables.setCam4WidthArrSub(width_arr);
                        clsSharedVariables.setCam4HeightArrSub(height_arr);
                        clsSharedVariables.setCam4QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam4ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam4FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam4MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam4FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 5:
                        clsSharedVariables.setCam5WidthArrSub(width_arr);
                        clsSharedVariables.setCam5HeightArrSub(height_arr);
                        clsSharedVariables.setCam5QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam5ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam5FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam5MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam5FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 6:

                        clsSharedVariables.setCam6WidthArrSub(width_arr);
                        clsSharedVariables.setCam6HeightArrSub(height_arr);
                        clsSharedVariables.setCam6QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam6ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam6FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam6MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam6FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 7:

                        clsSharedVariables.setCam7WidthArrSub(width_arr);
                        clsSharedVariables.setCam7HeightArrSub(height_arr);
                        clsSharedVariables.setCam7QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam7ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam7FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam7MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam7FrameIntervalArrSub(frame_interval_arr);
                        break;
                    case 8:

                        clsSharedVariables.setCam8WidthArrSub(width_arr);
                        clsSharedVariables.setCam8HeightArrSub(height_arr);
                        clsSharedVariables.setCam8QualityCtrlArrSub(quality_control_arr);
                        clsSharedVariables.setCam8ConstBitrateArrSub(constant_bitrate_min_max_arr);
                        clsSharedVariables.setCam8FixedQualityArrSub(fixed_quality_arr);
                        clsSharedVariables.setCam8MaxFramerateArrSub(max_framerate_arr);
                        clsSharedVariables.setCam8FrameIntervalArrSub(frame_interval_arr);
                        break;
                }

            }
        } catch (ParserConfigurationException ex) {
            //Logger.getLogger(clsVideoNormalConfigUpdates.class
            // .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            //Logger.getLogger(clsVideoNormalConfigUpdates.class
            //   .getName()).log(Level.SEVERE, null, ex);

        } finally {
            httpget = null;
            response = null;
            entity = null;
            is = null;
            response = null;
            addr = null;
            bitrate_type_control = null;
            quality_control_arr = null;
            constant_bitrate_min_max_arr = null;
            fixed_quality_arr = null;
            max_framerate_arr = null;
            width_arr = null;
            height_arr = null;
            frame_interval_arr = null;
            user = null;
            pwd = null;
            uri = null;

            httpget = null; // Set the action you want to do
            entity = null;
            is = null; // Create an InputStream with the response 
            httpclient = null;
            factory = null;
            builder = null;
        }
    }

    private void hikvision_imageadjustment(byte cam_no, String data) {
        OkHttpClient client; // OkHttpClient instance for making HTTP requests
        Response response; // Response object to handle the HTTP response
        MediaType mediaType; // Media type for the request body
        RequestBody body; // Request body to send data in the request
        Request request; // Request object to build and execute HTTP requests
        String[] split_str; // Array to store split parts of data string
        String addr = null; // Variable to store camera IP address
        String user = "admin"; // Default username for camera authentication
        String pwd = "sumith123"; // Default password for camera authentication

        // Determine the camera address, username, and password based on cam_no
        if (cam_no == 1) {
            addr = clsDefines.CAM1_IPADDR;
            user = clsSharedVariables.getCam1UserName();
            pwd = clsSharedVariables.getCam1Pwd();
        } else if (cam_no == 2) {
            addr = clsDefines.CAM2_IPADDR;
            user = clsSharedVariables.getCam2UserName();
            pwd = clsSharedVariables.getCam2Pwd();
        } else if (cam_no == 3) {
            addr = clsDefines.CAM3_IPADDR;
            user = clsSharedVariables.getCam3UserName();
            pwd = clsSharedVariables.getCam3Pwd();
        } else if (cam_no == 4) {
            addr = clsDefines.CAM4_IPADDR;
            user = clsSharedVariables.getCam4UserName();
            pwd = clsSharedVariables.getCam4Pwd();
        } else if (cam_no == 5) {
            addr = clsDefines.CAM5_IPADDR;
            user = clsSharedVariables.getCam5UserName();
            pwd = clsSharedVariables.getCam5Pwd();
        } else if (cam_no == 6) {
            addr = clsDefines.CAM6_IPADDR;
            user = clsSharedVariables.getCam6UserName();
            pwd = clsSharedVariables.getCam6Pwd();
        } else if (cam_no == 7) {
            addr = clsDefines.CAM7_IPADDR;
            user = clsSharedVariables.getCam7UserName();
            pwd = clsSharedVariables.getCam7Pwd();
        } else if (cam_no == 8) {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        try {
            client = new OkHttpClient(); // Initialize OkHttpClient
            split_str = data.split(","); // Split data string into parts

            // Create authorization string in Base64 format
            String authStr = user + ":" + pwd;
            String auth = "Basic " + Base64.getEncoder().encodeToString(authStr.getBytes());

            // Prepare request body in XML format with image adjustment parameters
            mediaType = MediaType.parse("application/xml");
            body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf 8\"?>\n<Color>\n<brightnessLevel>" + split_str[0] + 0 + "</brightnessLevel>\n<contrastLevel>" + split_str[1] + 0 + "</contrastLevel>\n<saturationLevel>" + split_str[2] + 0 + "</saturationLevel>\n</Color>");

            // Build the HTTP request with necessary headers and parameters
            request = new Request.Builder()
                    .url("http://" + addr + "/ISAPI/Image/channels/101") // URL for image adjustment endpoint
                    .put(body) // HTTP method PUT with request body
                    .addHeader("Content-Type", "application/xml")
                    .addHeader("Authorization", auth) // Authorization header with Basic authentication
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "nocache")
                    .addHeader("Host", addr)
                    .addHeader("accept-encoding", "gzip, deflate")
                    .addHeader("content-length", "170")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = client.newCall(request).execute(); // Execute the HTTP request

            if (response.code() == 200) {
                // Handle successful response if HTTP status code is 200
                ////System.out.println("Brightness response true");
            }
        } catch (Exception ex) {
            // Handle any exceptions that occur during the HTTP request
        } finally {
            // Clean up resources: set variables to null
            client = null;
            response = null;
            mediaType = null;
            body = null;
            request = null;
            addr = null;
        }
        //  return true;
    }

    private void hikvision_mirrorOn(String addr) {
        OkHttpClient client;
        Response response;
        MediaType mediaType;
        RequestBody body;
        Request request;
        String user = "admin";
        String pwd = "sumith321";

        // Initialize OkHttpClient and other variables
        try {
            client = new OkHttpClient();
            mediaType = MediaType.parse("application/xml");
            // Determine which camera's username and password to use based on addr parameter
            if (addr.equals(clsDefines.CAM1_IPADDR)) {
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
                // Check if mirror is enabled for CAM1
                if (clsSharedVariables.cam1MirrorEnable == true) {
                    // Create request body for enabling mirror
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    // Create request body for disabling mirror
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else if (addr.equals(clsDefines.CAM2_IPADDR)) {
                user = clsSharedVariables.getCam2UserName();
                pwd = clsSharedVariables.getCam2Pwd();
                if (clsSharedVariables.cam2MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else if (addr.equals(clsDefines.CAM3_IPADDR)) {
                user = clsSharedVariables.getCam3UserName();
                pwd = clsSharedVariables.getCam3Pwd();
                if (clsSharedVariables.cam3MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else if (addr.equals(clsDefines.CAM4_IPADDR)) {
                user = clsSharedVariables.getCam4UserName();
                pwd = clsSharedVariables.getCam4Pwd();
                if (clsSharedVariables.cam4MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else if (addr.equals(clsDefines.CAM5_IPADDR)) {
                user = clsSharedVariables.getCam5UserName();
                pwd = clsSharedVariables.getCam5Pwd();
                if (clsSharedVariables.cam5MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else if (addr.equals(clsDefines.CAM6_IPADDR)) {
                user = clsSharedVariables.getCam6UserName();
                pwd = clsSharedVariables.getCam6Pwd();
                if (clsSharedVariables.cam6MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else if (addr.equals(clsDefines.CAM7_IPADDR)) {
                user = clsSharedVariables.getCam7UserName();
                pwd = clsSharedVariables.getCam7Pwd();
                if (clsSharedVariables.cam7MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            } else {
                user = clsSharedVariables.getCam8UserName();
                pwd = clsSharedVariables.getCam8Pwd();
                if (clsSharedVariables.cam8MirrorEnable == true) {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>true</enabled>\n<ImageFlipStyle>LEFTRIGHT</ImageFlipStyle>\n</ImageFlip>");
                } else {
                    body = RequestBody.create(mediaType, "<ImageFlip version=\"2.0\">\n<enabled>false</enabled>\n</ImageFlip>");
                }
            }

            // Create Basic authentication header
            String authStr = user + ":" + pwd;
            String auth = "Basic " + Base64.getEncoder()
                    .encodeToString(authStr.getBytes());

            // Build the HTTP request
            request = new Request.Builder()
                    .url("http://" + addr + "/ISAPI/Image/channels/101/ImageFlip")
                    .put(body)
                    .addHeader("Content-Type", "application/xml")
                    .addHeader("Authorization", auth)
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "nocache")
                    .addHeader("Host", addr)
                    .addHeader("accept-encoding", "gzip, deflate")
                    .addHeader("content-length", "170")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("cache-control", "no-cache")
                    .build();

            // Execute the HTTP request
            response = client.newCall(request).execute();
            if (response.code() == 403) {
                // Retry with the new URL
                request = new Request.Builder()
                        .url("http://" + addr + "/ISAPI/Image/channels/1/ImageFlip")
                        .put(body)
                        .addHeader("Content-Type", "application/xml")
                        .addHeader("Authorization", auth)
                        .addHeader("Accept", "*/*")
                        .addHeader("Cache-Control", "nocache")
                        .addHeader("Host", addr)
                        .addHeader("accept-encoding", "gzip, deflate")
                        .addHeader("content-length", "170")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("cache-control", "no-cache")
                        .build();

                // Execute the new request
                response = client.newCall(request).execute();
            }
        } catch (IOException ex) {
            // Handle exceptions
        } finally {
            // Clean up resources
            client = null;
            response = null;
            mediaType = null;
            body = null;
            request = null;
            addr = null;
        }
    }

    private boolean hikvision_time_update(String addr, String time) {
        OkHttpClient client;
        MediaType mediaType;
        RequestBody body;
        Request request;
        String user = "admin";
        String pwd = "sumith123";

        try {
            client = new OkHttpClient();

            // Set user credentials based on the camera address
            if (addr.equals(clsDefines.CAM1_IPADDR)) {
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
            } else if (addr.equals(clsDefines.CAM2_IPADDR)) {
                user = clsSharedVariables.getCam2UserName();
                pwd = clsSharedVariables.getCam2Pwd();
            } else if (addr.equals(clsDefines.CAM3_IPADDR)) {
                user = clsSharedVariables.getCam3UserName();
                pwd = clsSharedVariables.getCam3Pwd();
            } else if (addr.equals(clsDefines.CAM4_IPADDR)) {
                user = clsSharedVariables.getCam4UserName();
                pwd = clsSharedVariables.getCam4Pwd();
            } else if (addr.equals(clsDefines.CAM5_IPADDR)) {
                user = clsSharedVariables.getCam5UserName();
                pwd = clsSharedVariables.getCam5Pwd();
            } else if (addr.equals(clsDefines.CAM6_IPADDR)) {
                user = clsSharedVariables.getCam6UserName();
                pwd = clsSharedVariables.getCam6Pwd();
            } else if (addr.equals(clsDefines.CAM7_IPADDR)) {
                user = clsSharedVariables.getCam7UserName();
                pwd = clsSharedVariables.getCam7Pwd();
            } else if (addr.equals(clsDefines.CAM8_IPADDR)) {
                user = clsSharedVariables.getCam8UserName();
                pwd = clsSharedVariables.getCam8Pwd();
            }

            // Create authorization header
            String authStr = user + ":" + pwd;
            String auth = "Basic " + Base64.getEncoder().encodeToString(authStr.getBytes());

            // Set media type and request body
            mediaType = MediaType.parse("application/xml");
            body = RequestBody.create(mediaType, "<?xml version=\"1.0\" encoding=\"utf 8\"?>\r\n<Time>\r\n\r\n<timeMode>manual</timeMode>\r\n\r\n<localTime>" + time + "</localTime>\r\n\r\n<timeZone>IST-5:30:00</timeZone>\r\n\r\n</Time>"); //2020-01-08T18:44:38

            // Build the HTTP request
            request = new Request.Builder()
                    .url("http://" + addr + "/ISAPI/System/time")
                    .put(body)
                    .addHeader("Content-Type", "application/xml")
                    .addHeader("Authorization", auth) //"Basic YWRtaW46c3VtaXRoMTIz")
                    //.addHeader("User-Agent", "PostmanRuntime/7.15.0")
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "nocache")
                    .addHeader("Host", addr)
                    .addHeader("accept-encoding", "gzip, deflate")
                    .addHeader("content-length", "170")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("cache-control", "no-cache")
                    .build();

            // Execute the request
            Response res = client.newCall(request).execute();

            // Check response code for success or failure
            if (res.code() >= 200 && res.code() <= 210) {
                return true; // Success
            } else if (res.code() == 401) {
                return false; // Unauthorized
            }
        } catch (Exception ex) {
            // Handle any exceptions
        } finally {
            // Clean up resources
            client = null;
            body = null;
            mediaType = null;
            request = null;
            addr = null;
            time = null;
        }
        return false; // Default failure
    }

    private boolean hikvision_watermarking(String addr, String user, String pwd, String data) {
        OkHttpClient client;
        Response response;
        MediaType mediaType;
        RequestBody body;
        Request request;
        try {
            // Create authorization header
            String authStr = user + ":" + pwd;
            String auth = "Basic " + Base64.getEncoder()
                    .encodeToString(authStr.getBytes());

            // Initialize OkHttpClient
            client = new OkHttpClient();
            mediaType = MediaType.parse("application/xml");

            // Prepare XML data for watermarking
            String str1;
            str1 = "<VideoInputChannelList version=\"2.0\" >\n"
                    + "        <VideoInputChannel>\n"
                    + "            <id>1</id>\n"
                    + "            <inputPort>1</inputPort>\n"
                    + "            <name>" + data + "</name>\n"
                    + "            <videoFormat>PAL</videoFormat>\n"
                    + "        </VideoInputChannel>\n"
                    + "    </VideoInputChannelList>";

            // Create request body
            body = RequestBody.create(mediaType, str1);

            // Build HTTP request
            request = new Request.Builder()
                    .url("http://" + user + ":" + pwd + "@" + addr + "/ISAPI/System/Video/inputs/channels/1/")
                    .put(body)
                    .addHeader("Content-Type", "application/xml")
                    .addHeader("Authorization", auth)
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "nocache")
                    .addHeader("Host", addr)
                    .addHeader("accept-encoding", "gzip, deflate")
                    .addHeader("content-length", "292")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("cache-control", "no-cache")
                    .build();

            // Execute the request
            response = client.newCall(request).execute();

            // Check response code for success
            if (response.code() == 200) {
                return true; // Success
            }
        } catch (Exception ex) {
            // Handle any exceptions
        } finally {
            // Clean up resources
            client = null;
            response = null;
            mediaType = null;
            body = null;
            request = null;
        }
        return false; // Default failure
    }

    static boolean digest_upd_status;

    private boolean digestUpdateData(String uri, String user_name, String pwd) {
        // SwingWorker to perform background task
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    // Initialize DigestScheme for MD5 authentication
                    final DigestScheme md5Auth = new DigestScheme();
                    HttpClient client = HttpClientBuilder.create().build();

                    // Execute initial request to authenticate
                    HttpResponse authResponse = client.execute(new HttpGet(uri));
                    InputStream ipp = null;

                    // Initialize status to false
                    digest_upd_status = false;

                    // Handle Unauthorized status
                    if (authResponse.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                        if (authResponse.containsHeader("WWW-Authenticate")) {
                            // Process authentication challenge
                            final Header challenge = authResponse.getHeaders("WWW-Authenticate")[0];
                            NameRegistrar.register("www-authenticate", authResponse.getHeaders("WWW-Authenticate")[0]);
                            md5Auth.processChallenge(challenge);

                            // Create authentication solution
                            final Header solution = md5Auth.authenticate(
                                    new UsernamePasswordCredentials(user_name, pwd),
                                    new BasicHttpRequest(HttpGet.METHOD_NAME, new URL(uri)
                                            .getPath()), new HttpClientContext());

                            // Create HTTP request with authentication headers
                            HttpGet request = new HttpGet(new URI(uri));
                            md5Auth.createCnonce();
                            request.addHeader("Accept", "application/json");
                            request.addHeader(solution.getName(), solution.getValue());

                            // Execute request
                            HttpResponse resp = client.execute(request);

                            // Check response code for success
                            if (resp.getStatusLine().getStatusCode() == 200) {
                                digest_upd_status = true; // Success
                            } else {
                                digest_upd_status = false; // Failure
                            }

                            // Get response content
                            try {
                                ipp = resp.getEntity().getContent();
                            } finally {
                                client = null;
                                authResponse = null;
                            }
                        }
                    }
                } catch (Exception ex) {
                    // Handle any exceptions
                }
                return null;
            }

            @Override
            protected void process(List chunks) {
                // Process intermediate chunks if needed
            }

            @Override
            protected void done() {
                try {
                    // Clean up after task completion if needed
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Execute SwingWorker task
        sw1.execute();

        // Return final status
        return digest_upd_status;
    }

    public synchronized boolean update_camera_time_system_time(byte cam_no) {
        String cmd; // Command to set the time
        Calendar cal;
        cal = Calendar.getInstance(); // Get the current time

        String addr = null; // IP address of the camera
        String user = clsSharedVariables.getCam1UserName(); // Username for the camera
        String pwd = clsSharedVariables.getCam1Pwd(); // Password for the camera
        byte cam_type = clsSharedVariables.getCam1Type(); // Camera type
        String uri; // URI to set the time on the camera

        // Set camera details based on camera number
        switch (cam_no) {
            case 1:
                addr = clsDefines.CAM1_IPADDR;
                cam_type = clsSharedVariables.getCam1Type();
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
                break;
            case 2:
                addr = clsDefines.CAM2_IPADDR;
                cam_type = clsSharedVariables.getCam2Type();
                user = clsSharedVariables.getCam2UserName();
                pwd = clsSharedVariables.getCam2Pwd();
                break;
            case 3:
                addr = clsDefines.CAM3_IPADDR;
                cam_type = clsSharedVariables.getCam3Type();
                user = clsSharedVariables.getCam3UserName();
                pwd = clsSharedVariables.getCam3Pwd();
                break;
            case 4:
                addr = clsDefines.CAM4_IPADDR;
                cam_type = clsSharedVariables.getCam4Type();
                user = clsSharedVariables.getCam4UserName();
                pwd = clsSharedVariables.getCam4Pwd();
                break;
            case 5:
                addr = clsDefines.CAM5_IPADDR;
                cam_type = clsSharedVariables.getCam5Type();
                user = clsSharedVariables.getCam5UserName();
                pwd = clsSharedVariables.getCam5Pwd();
                break;
            case 6:
                addr = clsDefines.CAM6_IPADDR;
                cam_type = clsSharedVariables.getCam6Type();
                user = clsSharedVariables.getCam6UserName();
                pwd = clsSharedVariables.getCam6Pwd();
                break;
            case 7:
                addr = clsDefines.CAM7_IPADDR;
                cam_type = clsSharedVariables.getCam7Type();
                user = clsSharedVariables.getCam7UserName();
                pwd = clsSharedVariables.getCam7Pwd();
                break;
            case 8:
                addr = clsDefines.CAM8_IPADDR;
                cam_type = clsSharedVariables.getCam8Type();
                user = clsSharedVariables.getCam8UserName();
                pwd = clsSharedVariables.getCam8Pwd();
                break;
            default:
                addr = clsDefines.CAM1_IPADDR;
                cam_type = clsSharedVariables.getCam1Type();
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
                break;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
            cal = Calendar.getInstance();
            Date gpsdate = new Date(cal.getTimeInMillis()); // Get the current date and time

            // Format the command to set the time
            cmd = ((1900 + gpsdate.getYear()) + "-" + (1 + gpsdate.getMonth()) + "-" + gpsdate.getDate() + "T" + gpsdate.getHours() + ":" + gpsdate.getMinutes() + ":" + gpsdate.getSeconds());

            // Update time for CPPlus or Dahua cameras
            if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
                uri = "http://" + addr + "/cgi-bin/global.cgi?action=setCurrentTime&time=" + (1900 + gpsdate.getYear()) + "-" + (1 + gpsdate.getMonth()) + "-" + gpsdate.getDate() + "%20" + gpsdate.getHours() + ":" + gpsdate.getMinutes() + ":" + gpsdate.getSeconds(); // `date '+%Y-%m-%d%%20%H:%M:%S'";
                digestUpdateData(uri, user, pwd);
                return true;
            } else if (cam_type == clsDefines.CAM_HIKVISION) {
                return hikvision_time_update(addr, cmd); // Update time for Hikvision cameras
            } else {
                return true; // Assume success for other camera types
            }
        } catch (Exception ex) {
            // Handle exceptions if necessary
        } finally {
            cmd = null;
            cal = null;
            addr = null;
            uri = null;
        }
        return false;
    }

    public synchronized void setMotionregion(byte cam_no, int value) {
        String addr = null; // IP address of the camera
        String user = clsSharedVariables.getCam1UserName(); // Username for the camera
        String pwd = clsSharedVariables.getCam1Pwd(); // Password for the camera
        byte cam_type = clsSharedVariables.getCam1Type(); // Camera type

        int[] region = new int[18];
        byte Y1;
        byte Y2;
        byte threshold;
        byte sensitivity;
        threshold = (byte) clsSharedVariables.getThresholdCam1();
        sensitivity = (byte) clsSharedVariables.getSensitivityCam1();
        Y1 = (byte) clsSharedVariables.getMotionY1Cam1();
        Y2 = (byte) clsSharedVariables.getMotionY2Cam1();

        // Set camera details based on camera number
        if (cam_no == 1) {
            addr = clsDefines.CAM1_IPADDR;
            user = clsSharedVariables.getCam1UserName();
            pwd = clsSharedVariables.getCam1Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam1();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam1();
            threshold = (byte) clsSharedVariables.getThresholdCam1();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam1();

            // Set the motion detection region
            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam1Type();
        } else if (cam_no == 2) {
            addr = clsDefines.CAM2_IPADDR;
            user = clsSharedVariables.getCam2UserName();
            pwd = clsSharedVariables.getCam2Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam2();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam2();
            threshold = (byte) clsSharedVariables.getThresholdCam2();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam2();
            cam_type = clsSharedVariables.getCam2Type();
        } else if (cam_no == 3) {
            addr = clsDefines.CAM3_IPADDR;
            user = clsSharedVariables.getCam3UserName();
            pwd = clsSharedVariables.getCam3Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam3();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam3();
            threshold = (byte) clsSharedVariables.getThresholdCam3();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam3();

            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam3Type();
        } else if (cam_no == 4) {
            addr = clsDefines.CAM4_IPADDR;
            user = clsSharedVariables.getCam4UserName();
            pwd = clsSharedVariables.getCam4Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam4();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam4();
            threshold = (byte) clsSharedVariables.getThresholdCam4();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam4();

            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam4Type();
        } else if (cam_no == 5) {
            addr = clsDefines.CAM5_IPADDR;
            user = clsSharedVariables.getCam5UserName();
            pwd = clsSharedVariables.getCam5Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam5();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam5();
            threshold = (byte) clsSharedVariables.getThresholdCam5();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam5();

            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam5Type();
        } else if (cam_no == 6) {
            addr = clsDefines.CAM6_IPADDR;
            user = clsSharedVariables.getCam6UserName();
            pwd = clsSharedVariables.getCam6Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam6();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam6();
            threshold = (byte) clsSharedVariables.getThresholdCam6();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam6();

            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam6Type();
        } else if (cam_no == 7) {
            addr = clsDefines.CAM7_IPADDR;
            user = clsSharedVariables.getCam7UserName();
            pwd = clsSharedVariables.getCam7Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam7();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam7();
            threshold = (byte) clsSharedVariables.getThresholdCam7();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam7();

            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam7Type();
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
            Y1 = (byte) clsSharedVariables.getMotionY1Cam8();
            Y2 = (byte) clsSharedVariables.getMotionY2Cam8();
            threshold = (byte) clsSharedVariables.getThresholdCam8();
            sensitivity = (byte) clsSharedVariables.getSensitivityCam8();

            for (int i = 0; i <= 17; i++) {
                if (i > Y2 || i < Y1) {
                    region[i] = 0;
                } else {
                    region[i] = value;
                }
            }
            cam_type = clsSharedVariables.getCam8Type();
        }

        String uri;

        // Update motion detection region for CPPlus or Dahua cameras
        if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {
            uri = "http://" + addr + "/cgi-bin/configManager.cgi?action=setConfig&MotionDetect[0].MotionDetectWindow[0].Region[0]=" + region[0] + "&MotionDetect[0].MotionDetectWindow[0].Region[1]=" + region[1] + "&MotionDetect[0].MotionDetectWindow[0].Region[2]=" + region[2] + "&"
                    + "MotionDetect[0].MotionDetectWindow[0].Region[3]=" + region[3] + "&MotionDetect[0].MotionDetectWindow[0].Region[4]=" + region[4] + "&MotionDetect[0].MotionDetectWindow[0].Region[5]=" + region[5] + "&MotionDetect[0].MotionDetectWindow[0].Region[6]=" + region[6] + "&"
                    + "MotionDetect[0].MotionDetectWindow[0].Region[7]=" + region[7] + "&MotionDetect[0].MotionDetectWindow[0].Region[8]=" + region[8] + "&MotionDetect[0].MotionDetectWindow[0].Region[9]=" + region[9] + "&"
                    + "MotionDetect[0].MotionDetectWindow[0].Region[10]=" + region[10] + "&MotionDetect[0].MotionDetectWindow[0].Region[11]=" + region[11] + "&"
                    + "MotionDetect[0].MotionDetectWindow[0].Region[12]=" + region[12] + "&MotionDetect[0].MotionDetectWindow[0].Region[13]=" + region[13] + "&MotionDetect[0].MotionDetectWindow[0].Region[14]=" + region[14] + "&"
                    + "MotionDetect[0].MotionDetectWindow[0].Region[15]=" + region[15] + "&MotionDetect[0].MotionDetectWindow[0].Region[16]=" + region[16] + "&MotionDetect[0].MotionDetectWindow[0].Region[17]=" + region[17] + "&MotionDetect[0].MotionDetectWindow[0].Sensitive=" + sensitivity + "&MotionDetect[0].MotionDetectWindow[0].Threshold=" + threshold + "&MotionDetect[0].DetectVersion=V3.0";

            digestUpdateData(uri, user, pwd); // Send the update command
        } else if (cam_type == clsDefines.CAM_HIKVISION) {
            try {
                uri = "http://" + addr + "/ISAPI/Event/notification/alertStream";
                // hikvision_watermarking(addr, user, pwd, data); // Update motion detection for Hikvision cameras
            } catch (Exception e) {
                // Handle exceptions if necessary
            }
        }
    }
}
