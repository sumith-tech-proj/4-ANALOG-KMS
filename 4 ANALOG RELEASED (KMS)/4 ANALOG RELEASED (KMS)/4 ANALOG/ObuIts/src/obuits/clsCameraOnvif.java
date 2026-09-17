package obuits;

import de.onvif.soap.OnvifDevice;
import de.onvif.soap.SOAP;
import de.onvif.soap.devices.ImagingDevices;
import de.onvif.soap.devices.InitialDevices;
import de.onvif.soap.devices.MediaDevices;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.xml.soap.SOAPException;
import org.onvif.ver10.device.wsdl.SetSystemDateAndTime;
import org.onvif.ver10.device.wsdl.SetSystemDateAndTimeResponse;
import org.onvif.ver10.schema.DateTime;
import org.onvif.ver10.schema.H264Configuration;
import org.onvif.ver10.schema.H264Options;
import org.onvif.ver10.schema.ImagingSettings20;
import org.onvif.ver10.schema.IntRange;
import org.onvif.ver10.schema.JpegOptions;
import org.onvif.ver10.schema.Mpeg4Configuration;
import org.onvif.ver10.schema.Mpeg4Options;
import org.onvif.ver10.schema.Profile;
import org.onvif.ver10.schema.SetDateTimeType;
import org.onvif.ver10.schema.VideoEncoderConfiguration;
import org.onvif.ver10.schema.VideoEncoderConfigurationOptions;
import org.onvif.ver10.schema.VideoRateControl;
import org.onvif.ver10.schema.VideoResolution;
import org.onvif.ver10.schema.VideoSourceConfiguration;

public class clsCameraOnvif {

    clsCameraOnvif() {

    }

    /**
     * Adjusts the brightness, contrast, and saturation settings of an image
     * from a specified camera stream.
     *
     * @param cam_no The camera number.
     * @param stream_type The type of stream from the camera.
     * @return True if the settings were successfully adjusted, false otherwise.
     */
    public boolean ImageBrightnessSettings(byte cam_no, byte stream_type) {
        try {
            OnvifDevice nvt = null;
            String addr = null;
            InitialDevices devices;
            List<Profile> profiles;
            byte brightness;
            byte saturation;
            byte contrast;
            String user = clsSharedVariables.getCam1UserName();
            String pwd = clsSharedVariables.getCam1Pwd();

            // Determine camera IP address, brightness, saturation, and contrast based on camera number
            if (cam_no == 1) {
                addr = clsDefines.CAM1_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevel();
                saturation = clsSharedVariables.getSaturationLevel();
                contrast = clsSharedVariables.getContrastLevel();
                user = clsSharedVariables.getCam1UserName();
                pwd = clsSharedVariables.getCam1Pwd();
            } else if (cam_no == 2) {
                addr = clsDefines.CAM2_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam2();
                saturation = clsSharedVariables.getSaturationLevelCam2();
                contrast = clsSharedVariables.getContrastLevelCam2();
                user = clsSharedVariables.getCam2UserName();
                pwd = clsSharedVariables.getCam2Pwd();
            } else if (cam_no == 3) {
                addr = clsDefines.CAM3_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam3();
                saturation = clsSharedVariables.getSaturationLevelCam3();
                contrast = clsSharedVariables.getContrastLevelCam3();
                user = clsSharedVariables.getCam3UserName();
                pwd = clsSharedVariables.getCam3Pwd();
            } else if (cam_no == 4) {
                addr = clsDefines.CAM4_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam4();
                saturation = clsSharedVariables.getSaturationLevelCam4();
                contrast = clsSharedVariables.getContrastLevelCam4();
                user = clsSharedVariables.getCam4UserName();
                pwd = clsSharedVariables.getCam4Pwd();
            } else if (cam_no == 5) {
                addr = clsDefines.CAM5_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam5();
                saturation = clsSharedVariables.getSaturationLevelCam5();
                contrast = clsSharedVariables.getContrastLevelCam5();
                user = clsSharedVariables.getCam5UserName();
                pwd = clsSharedVariables.getCam5Pwd();
            } else if (cam_no == 6) {
                addr = clsDefines.CAM6_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam6();
                saturation = clsSharedVariables.getSaturationLevelCam6();
                contrast = clsSharedVariables.getContrastLevelCam6();
                user = clsSharedVariables.getCam6UserName();
                pwd = clsSharedVariables.getCam6Pwd();
            } else if (cam_no == 7) {
                addr = clsDefines.CAM7_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam7();
                saturation = clsSharedVariables.getSaturationLevelCam7();
                contrast = clsSharedVariables.getContrastLevelCam7();
                user = clsSharedVariables.getCam7UserName();
                pwd = clsSharedVariables.getCam7Pwd();
            } else  {
                addr = clsDefines.CAM8_IPADDR;
                brightness = clsSharedVariables.getBrightnessLevelCam8();
                saturation = clsSharedVariables.getSaturationLevelCam8();
                contrast = clsSharedVariables.getContrastLevelCam8();
                user = clsSharedVariables.getCam8UserName();
                pwd = clsSharedVariables.getCam8Pwd();
           
            }

            // Check if the camera is an analog camera
            if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
                addr = clsDefines.CAM_ANALOG_IPADDR;
            }

            try {
                // Create OnvifDevice instance with provided address and credentials
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }
            } catch (Exception ex) {
                return false; // Return false if an exception occurs during device creation
            }

            // Retrieve device profiles and video source configuration
            devices = nvt.getDevices();
            profiles = devices.getProfiles();
            VideoSourceConfiguration vidSrc = profiles.get(stream_type).getVideoSourceConfiguration();
            ImagingDevices imgDev = nvt.getImaging();
            ImagingSettings20 img20 = new ImagingSettings20();
            float a = 12; // Placeholder value for brightness (change as needed)
            img20.setBrightness(a);

            String srcToken = vidSrc.getSourceToken();
            ImagingSettings20 imgSet = nvt.getImaging().getImagingSettings(srcToken);

            // If imaging settings are retrieved successfully, adjust brightness, contrast, and saturation
            if (imgSet != null) {
                imgSet.setBrightness(Float.valueOf(brightness));
                imgSet.setContrast(Float.valueOf(contrast));
                imgSet.setColorSaturation(Float.valueOf(saturation));
                nvt.getImaging().setImagingSettings(srcToken, imgSet);
            }

            return true; // Return true if settings were successfully adjusted
        } catch (Exception ex) {
            // Catch any exceptions that occur during the process
        }
        return false; // Return false if an exception occurred or settings couldn't be adjusted
    }

    /**
     * Sets the date and time of the specified camera.
     *
     * @param cam_no The camera number.
     * @return True if the date and time were successfully set, false otherwise.
     */
    public boolean setDateTimeCam(byte cam_no) {
        OnvifDevice nvt = null;
        SOAP soap;
        String addr = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        DateTime dt;
        SetSystemDateAndTime setDatTime;
        SetSystemDateAndTimeResponse response;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();

        // Determine camera IP address, username, and password based on camera number
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Check if the camera type is analog
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            if ((addr != null) && (!addr.isEmpty())) {
                if ((user != null) && (!user.isEmpty())) {
                    nvt = new OnvifDevice(addr, user, pwd);
                    nvt.getSoap().setLogging(false);
                } else {
                    nvt = new OnvifDevice(addr);
                    nvt.getSoap().setLogging(false);
                }
            }
            soap = nvt.getSoap();

            org.onvif.ver10.schema.Date date = new org.onvif.ver10.schema.Date();

            date.setDay(cal.get(Calendar.DATE));
            date.setMonth(cal.get(Calendar.MONTH) + 1);
            date.setYear(cal.get(Calendar.YEAR));

            org.onvif.ver10.schema.Time time = new org.onvif.ver10.schema.Time();
            time.setHour(cal.get(Calendar.HOUR));
            time.setMinute(cal.get(Calendar.MINUTE));
            time.setSecond(cal.get(Calendar.SECOND));

            dt = new DateTime();
            dt.setDate(date);
            dt.setTime(time);

            setDatTime = new SetSystemDateAndTime();
            setDatTime.setDateTimeType(SetDateTimeType.MANUAL);
            setDatTime.setUTCDateTime(dt);
            setDatTime.setDaylightSavings(false);

            response = new SetSystemDateAndTimeResponse();

            try {
                response = (SetSystemDateAndTimeResponse) soap.createSOAPDeviceRequest(setDatTime, response, false);
            } catch (Exception e) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        } finally {
            response = null;
            soap = null;
            addr = null;
            cal = null;
            dt = null;
            setDatTime = null;
            nvt = null;
        }
        return true;
    }

    /**
     * * Retrieves RTSP and snapshot stream paths for the specified camera and
     * stream type.
     *
     * @param cam_no The camera number.
     * @param stream_type The stream type.
     * @return A string containing the RTSP stream path and snapshot stream path
     * separated by a comma.
     * @throws SOAPException If an error occurs during SOAP communication.
     * @throws ConnectException If an error occurs while connecting to the
     * device.
     */
    public String getStreamPaths(byte cam_no, byte stream_type) throws SOAPException, ConnectException {
        OnvifDevice nvt = null; // Initialize OnvifDevice object
        InitialDevices devices; // Initialize InitialDevices object
        MediaDevices media; // Initialize MediaDevices object
        List<Profile> profiles; // Initialize list to store profiles
        String profileToken; // Initialize variable to store profile token

        String addr = null; // Initialize address variable
        String rtsp_stream = null; // Initialize RTSP stream path variable
        String snap_shot_stream = null; // Initialize snapshot stream path variable
        String user = clsSharedVariables.getCam1UserName(); // Get username from shared variables
        String pwd = clsSharedVariables.getCam1Pwd(); // Get password from shared variables

        // Determine camera IP address, username, and password based on camera number
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Check if the camera type is analog and update the address accordingly
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            // Create OnvifDevice object if address is not null or empty
            if ((addr != null) && (!addr.isEmpty())) {
                if ((user != null) && (!user.isEmpty())) {
                    nvt = new OnvifDevice(addr, user, pwd);
                    nvt.getSoap().setLogging(false); // Disable SOAP logging
                } else {
                    nvt = new OnvifDevice(addr);
                    nvt.getSoap().setLogging(false); // Disable SOAP logging
                }
            }

            // Get devices and media from OnvifDevice
            devices = nvt.getDevices();
            media = nvt.getMedia();
            profiles = devices.getProfiles(); // Get profiles from devices
            profileToken = profiles.get(stream_type).getToken(); // Get profile token based on stream type

            // Get RTSP stream path and snapshot stream path using profile token
            rtsp_stream = media.getRTSPStreamUri(profileToken);
            snap_shot_stream = media.getSnapshotUri(profileToken);

        } catch (Exception ex) {
            // Handle exceptions
        } finally {
            // Clean up resources
            nvt = null;
            devices = null;
            media = null;
            profiles = null;
            profileToken = null;
            addr = null;
        }

        // Return RTSP stream path and snapshot stream path separated by a comma
        return rtsp_stream + "," + snap_shot_stream;
    }

    public synchronized boolean cameraStreamResolution(byte cam_no, byte res, byte stream_type)
            throws IOException {
        // Initialize variables
        OnvifDevice nvt = null;
        InitialDevices devices;
        MediaDevices media;
        String profileToken;
        VideoEncoderConfiguration videoEncoderConfiguration;

        List<Profile> profiles;
        VideoEncoderConfigurationOptions videoEncoderConfigurationOptions;
        H264Options h264Options;

        JpegOptions jpegOptions;
        Mpeg4Options mpeg4Options;
        List<VideoResolution> jpegResolutions = null;
        VideoResolution jpegResolution = null;

        int no_resolutions = 0;
        String addr = null;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();

        // Determine camera IP address and credentials based on cam_no
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Adjust IP address for analog camera if necessary
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            // Get stream paths
            getStreamPaths(cam_no, stream_type);

            try {
                // Create OnvifDevice object with address and credentials
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }

                // Retrieve devices, media, and profiles
                devices = nvt.getDevices();
                media = nvt.getMedia();
                profiles = devices.getProfiles();
                profileToken = profiles.get(stream_type).getToken();

                // Retrieve video encoder configuration and options
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media.getVideoEncoderConfigurationOptions(profileToken);

                // Determine available resolutions based on encoding type
                if (videoEncoderConfiguration.getEncoding().value().equals("JPEG")) {
                    if (videoEncoderConfigurationOptions.getJPEG() != null) {
                        jpegOptions = videoEncoderConfigurationOptions.getJPEG();
                        jpegResolutions = jpegOptions.getResolutionsAvailable();
                    }
                } else if (videoEncoderConfiguration.getEncoding().value().equals("H264")) {
                    if (videoEncoderConfigurationOptions.getH264() != null) {
                        h264Options = videoEncoderConfigurationOptions.getH264();
                        jpegResolutions = h264Options.getResolutionsAvailable();
                    }
                } else if (videoEncoderConfiguration.getEncoding().value().equals("MPEG4")) {
                    if (videoEncoderConfigurationOptions.getMPEG4() != null) {
                        mpeg4Options = videoEncoderConfigurationOptions.getMPEG4();
                        jpegResolutions = mpeg4Options.getResolutionsAvailable();
                    }
                }

                // Initialize selectedResolutionIndex
                int selectedResolutionIndex = 0;

                // Select resolution based on 'res' parameter and available resolutions
                if (res != 0) {
                    no_resolutions = jpegResolutions.size();
                    if (res <= no_resolutions) {
                        if (no_resolutions >= 5) {
                            selectedResolutionIndex = no_resolutions;
                            jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                        } else if (no_resolutions >= 4) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 3;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 4;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        } else if (no_resolutions >= 3) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 3;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 3;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        } else if (no_resolutions >= 2) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        } else if (no_resolutions >= 1) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        }

                    } else {
                        selectedResolutionIndex = no_resolutions - 1;
                        jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                    }
                }
                if (jpegResolution != null) {
                    videoEncoderConfiguration.setResolution(jpegResolution);
                    profiles.get(stream_type).setVideoEncoderConfiguration(videoEncoderConfiguration);
                    media.setVideoEncoderConfiguration(videoEncoderConfiguration);

                    return true;
                }

            } catch (Exception e) {
                // Handle exceptions
                return false;
            }

        } catch (Exception e) {
            // Handle exceptions
            return false;
        } finally {
            // Cleanup resources
            nvt = null;
            videoEncoderConfiguration = null;
            media = null;
            profiles = null;
            jpegResolutions = null;
            h264Options = null;
            profileToken = null;
            devices = null;
            videoEncoderConfigurationOptions = null;
            jpegResolution = null;
            addr = null;
        }
        return false;
    }

    public synchronized boolean cameraStreamBitrate(byte cam_no, int data, byte stream_type)
            throws IOException {
        // Initialize variables
        OnvifDevice nvt = null;
        InitialDevices devices;
        MediaDevices media;
        String profileToken;
        VideoEncoderConfiguration videoEncoderConfiguration;
        VideoEncoderConfigurationOptions videoEncoderConfigurationOptions;
        List<Profile> profiles;
        VideoRateControl videoRateControl;
        String addr = null;

        // Get camera credentials based on cam_no
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Adjust IP address for analog camera if necessary
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            try {
                // Create OnvifDevice object with address and credentials
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }

                // Retrieve devices and media
                devices = nvt.getDevices();
                media = nvt.getMedia();

                profiles = devices.getProfiles();

                // Retrieve capabilities and video encoder configuration
                org.onvif.ver10.schema.Capabilities cap = devices.getCapabilities();
                profileToken = profiles.get(stream_type).getToken();
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media.getVideoEncoderConfigurationOptions(profileToken);
                videoRateControl = videoEncoderConfiguration.getRateControl();

                // Set bitrate limit
                videoRateControl.setBitrateLimit(data);

                // Update video encoder configuration and media
                videoEncoderConfiguration.setRateControl(videoRateControl);
                profiles.get(stream_type).setVideoEncoderConfiguration(videoEncoderConfiguration);
                media.setVideoEncoderConfiguration(videoEncoderConfiguration);

                // Update bitrate values in shared variables based on stream type and camera number
                if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                    if (cam_no == 1) {
                        clsSharedVariables.setbitrate(data);
                    } else if (cam_no == 2) {
                        clsSharedVariables.setbitrate2(data);
                    } else if (cam_no == 3) {
                        clsSharedVariables.setbitrate3(data);
                    } else if (cam_no == 4) {
                        clsSharedVariables.setbitrate4(data);
                    } else if (cam_no == 5) {
                        clsSharedVariables.setbitrate5(data);
                    } else if (cam_no == 6) {
                        clsSharedVariables.setbitrate6(data);
                    } else if (cam_no == 7) {
                        clsSharedVariables.setbitrate7(data);
                    } else if (cam_no == 8) {
                        clsSharedVariables.setbitrate8(data);
                    }
                } else {
                    if (cam_no == 1) {
                        clsSharedVariables.setSubbitrate(data);
                    } else if (cam_no == 2) {
                        clsSharedVariables.setSubbitrate2(data);
                    } else if (cam_no == 3) {
                        clsSharedVariables.setSubbitrate3(data);
                    } else if (cam_no == 4) {
                        clsSharedVariables.setSubbitrate4(data);
                    } else if (cam_no == 5) {
                        clsSharedVariables.setSubbitrate5(data);
                    } else if (cam_no == 6) {
                        clsSharedVariables.setSubbitrate6(data);
                    } else if (cam_no == 7) {
                        clsSharedVariables.setSubbitrate7(data);
                    } else if (cam_no == 8) {
                        clsSharedVariables.setSubbitrate8(data);
                    }
                }

                // Write camera configuration file
                clsReadFiles objReadFiles = new clsReadFiles();
                objReadFiles.write_camera_cfg_file();
                objReadFiles = null;

                return true;
            } catch (Exception e) {
                // Handle exceptions
                return false;
            }
        } catch (Exception e) {
            // Handle exceptions
            return false;
        } finally {
            // Cleanup resources
            nvt = null;
            videoEncoderConfiguration = null;
            media = null;
            profiles = null;
            videoRateControl = null;
            profileToken = null;
            devices = null;
            videoEncoderConfigurationOptions = null;
            addr = null;
        }
    }

    public synchronized boolean cameraStreamFramerate(byte cam_no, int data, byte stream_type)
            throws IOException {
        // Initialize variables
        OnvifDevice nvt = null;
        InitialDevices devices;
        MediaDevices media;
        String profileToken;
        VideoEncoderConfiguration videoEncoderConfiguration;
        VideoEncoderConfigurationOptions videoEncoderConfigurationOptions;
        List<Profile> profiles;
        VideoRateControl videoRateControl;
        String addr = null;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();

        // Determine the camera address and credentials based on cam_no
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Adjust IP address for an analog camera if necessary
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            try {
                // Create OnvifDevice object with address and credentials
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }

                // Retrieve devices and media
                devices = nvt.getDevices();
                media = nvt.getMedia();
                profiles = devices.getProfiles();

                // Retrieve profile token and video encoder configuration
                profileToken = profiles.get(stream_type).getToken();
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media.getVideoEncoderConfigurationOptions(profileToken);
                videoRateControl = videoEncoderConfiguration.getRateControl();

                // Retrieve min and max frame rate values
                int min_val = videoEncoderConfigurationOptions.getH264().getFrameRateRange().getMin();
                int max_val = videoEncoderConfigurationOptions.getH264().getFrameRateRange().getMax();

                // Set frame rate limit within the valid range
                if (data < min_val) {
                    videoRateControl.setFrameRateLimit(min_val);
                } else if (data >= min_val && data <= max_val) {
                    videoRateControl.setFrameRateLimit(data);
                } else {
                    videoRateControl.setFrameRateLimit(max_val);
                }

                // Update video encoder configuration and media
                videoEncoderConfiguration.setRateControl(videoRateControl);
                profiles.get(stream_type).setVideoEncoderConfiguration(videoEncoderConfiguration);
                media.setVideoEncoderConfiguration(videoEncoderConfiguration);
                return true;
            } catch (Exception e) {
                // Handle exceptions
            }
        } catch (Exception e) {
            // Handle exceptions
        } finally {
            // Cleanup resources
            nvt = null;
            videoEncoderConfiguration = null;
            media = null;
            profiles = null;
            videoRateControl = null;
            profileToken = null;
            devices = null;
            videoEncoderConfigurationOptions = null;
            addr = null;
        }

        return false;
    }

    public synchronized boolean cameraStreamFramerateInterval(byte cam_no, int data, byte stream_type)
            throws IOException {
        // Initialize variables
        OnvifDevice nvt = null;
        InitialDevices devices;
        MediaDevices media;
        String profileToken;
        VideoEncoderConfiguration videoEncoderConfiguration;
        VideoEncoderConfigurationOptions videoEncoderConfigurationOptions;
        List<Profile> profiles;
        H264Configuration h264Con = new H264Configuration();
        Mpeg4Configuration mpeg4Con = new Mpeg4Configuration();
        H264Options h264Options;
        JpegOptions jpegOptions;
        Mpeg4Options mpeg4Options;
        String addr = null;
        IntRange frame_rate_interval_gov_range;
        int min_val;
        int max_val;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();

        // Determine the camera address and credentials based on cam_no
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Adjust IP address for an analog camera if necessary
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            try {
                // Create OnvifDevice object with address and credentials
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }
                // Retrieve devices and media
                devices = nvt.getDevices();
                media = nvt.getMedia();
                profiles = devices.getProfiles();
                profileToken = profiles.get(stream_type).getToken();
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media
                        .getVideoEncoderConfigurationOptions(profileToken);
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media.getVideoEncoderConfigurationOptions(profileToken);

                // Adjust configuration based on encoding type (H264 or MPEG4)
                if (videoEncoderConfiguration.getEncoding().value().equals("H264")) {
                    if (videoEncoderConfigurationOptions.getH264() != null) {
                        h264Options = videoEncoderConfigurationOptions.getH264();
                        frame_rate_interval_gov_range = h264Options.getGovLengthRange();
                        // Retrieve frame rate interval range
                        min_val = frame_rate_interval_gov_range.getMin();
                        max_val = frame_rate_interval_gov_range.getMax();

                        // Set frame rate interval within the valid range
                        if (data >= min_val && data <= max_val) {
                            h264Con.setGovLength(data);
                        } else if (data < min_val) {
                            h264Con.setGovLength(min_val);
                        } else {
                            h264Con.setGovLength(max_val);
                        }
                        videoEncoderConfiguration.setH264(h264Con);
                    }
                } else if (videoEncoderConfiguration.getEncoding().value().equals("MPEG4")) {
                    if (videoEncoderConfigurationOptions.getMPEG4() != null) {
                        mpeg4Options = videoEncoderConfigurationOptions.getMPEG4();
                        frame_rate_interval_gov_range = mpeg4Options.getGovLengthRange();
                        // Retrieve frame rate interval range
                        min_val = frame_rate_interval_gov_range.getMin();
                        max_val = frame_rate_interval_gov_range.getMax();

                        // Set frame rate interval within the valid range
                        if (data >= min_val && data <= max_val) {
                            mpeg4Con.setGovLength(data);
                        } else if (data < min_val) {
                            mpeg4Con.setGovLength(min_val);
                        } else {
                            mpeg4Con.setGovLength(max_val);
                        }
                        videoEncoderConfiguration.setMPEG4(mpeg4Con);
                    }
                }

                // Update video encoder configuration and media
                profiles.get(stream_type).setVideoEncoderConfiguration(videoEncoderConfiguration);
                media.setVideoEncoderConfiguration(videoEncoderConfiguration);

                // Update frame interval for main or sub stream
                if (stream_type == clsDefines.CAM_MAIN_STREAM) {
                    if (cam_no == 1) {
                        clsSharedVariables.setFrameInterval(data);
                    } else if (cam_no == 2) {
                        clsSharedVariables.setFrameInterval2(data);
                    } else if (cam_no == 3) {
                        clsSharedVariables.setFrameInterval3(data);
                    } else if (cam_no == 4) {
                        clsSharedVariables.setFrameInterval4(data);
                    } else if (cam_no == 5) {
                        clsSharedVariables.setFrameInterval5(data);
                    } else if (cam_no == 6) {
                        clsSharedVariables.setFrameInterval6(data);
                    } else if (cam_no == 7) {
                        clsSharedVariables.setFrameInterval7(data);
                    } else if (cam_no == 8) {
                        clsSharedVariables.setFrameInterval8(data);
                    }

                } else {
                    if (cam_no == 1) {
                        clsSharedVariables.setSubFrameInterval(data);
                    } else if (cam_no == 2) {
                        clsSharedVariables.setSubFrameInterval2(data);
                    } else if (cam_no == 3) {
                        clsSharedVariables.setSubFrameInterval3(data);
                    } else if (cam_no == 4) {
                        clsSharedVariables.setSubFrameInterval4(data);
                    } else if (cam_no == 5) {
                        clsSharedVariables.setSubFrameInterval5(data);
                    } else if (cam_no == 6) {
                        clsSharedVariables.setSubFrameInterval6(data);
                    } else if (cam_no == 7) {
                        clsSharedVariables.setSubFrameInterval7(data);
                    } else if (cam_no == 8) {
                        clsSharedVariables.setSubFrameInterval8(data);
                    }
                }

                // Write updated camera configuration to file
                clsReadFiles objReadFiles = new clsReadFiles();
                objReadFiles.write_camera_cfg_file();
                objReadFiles = null;
                return true;
            } catch (Exception e) {
                // Handle exceptions
            }
        } catch (Exception e) {
            // Handle exceptions
        } finally {
            // Cleanup resources
            nvt = null;
            videoEncoderConfiguration = null;
            media = null;
            profiles = null;
            profileToken = null;
            devices = null;
            videoEncoderConfigurationOptions = null;
            mpeg4Con = null;
            h264Con = null;
            addr = null;
            h264Options = null;
            jpegOptions = null;
            mpeg4Options = null;
            frame_rate_interval_gov_range = null;
            user = null;
            pwd = null;
        }

        return false;
    }

    public synchronized boolean cameraStreamQuality(byte cam_no, byte data, byte stream_type)
            throws IOException {
        // Initialize variables
        OnvifDevice nvt = null;
        InitialDevices devices;
        MediaDevices media;
        String profileToken;
        VideoEncoderConfiguration videoEncoderConfiguration;
        VideoEncoderConfigurationOptions videoEncoderConfigurationOptions;
        List<Profile> profiles;
        String addr = null;
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();

        // Determine the camera address and credentials based on cam_no
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Adjust IP address for an analog camera if necessary
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            try {
                // Create OnvifDevice object with address and credentials
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }
                // Retrieve devices and media
                devices = nvt.getDevices();
                media = nvt.getMedia();
                profiles = devices.getProfiles();
                profileToken = profiles.get(stream_type).getToken();
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media.getVideoEncoderConfigurationOptions(profileToken);

                // Adjust quality value to fit within the valid range
                int value = data;
                int min_val = videoEncoderConfigurationOptions.getQualityRange().getMin();
                int max_val = videoEncoderConfigurationOptions.getQualityRange().getMax();

                // If min_val is 0 and data > 1, decrement data
                if (min_val == 0 && data > 1) {
                    data = (byte) ((byte) data - 1);
                }

                // Ensure data falls within the valid range
                if (data < min_val) {
                    value = min_val;
                } else if (data > max_val) {
                    value = max_val;
                } else {
                    value = data;
                }

                // Set quality value in video encoder configuration
                videoEncoderConfiguration.setQuality(value);

                // Update video encoder configuration and media
                profiles.get(stream_type).setVideoEncoderConfiguration(videoEncoderConfiguration);
                media.setVideoEncoderConfiguration(videoEncoderConfiguration);
                return true;

            } catch (Exception e) {
                // Handle exceptions
                return false;
            }

        } catch (Exception e) {
            // Handle exceptions
            return false;
        } finally {
            // Cleanup resources
            nvt = null;
            videoEncoderConfiguration = null;
            media = null;
            profiles = null;
            profileToken = null;
            devices = null;
            videoEncoderConfigurationOptions = null;
            addr = null;
        }
    }

    public synchronized boolean updateCameraAllFeatures(byte cam_no, byte stream_type, byte res, int bitrate, byte quality, int frame_rate, int frame_rate_interval)
            throws IOException {
        // Initialize variables
        OnvifDevice nvt = null;
        InitialDevices devices;
        MediaDevices media;
        String profileToken;
        VideoEncoderConfiguration videoEncoderConfiguration;
        VideoEncoderConfigurationOptions videoEncoderConfigurationOptions;
        List<Profile> profiles;
        H264Options h264Options;
        JpegOptions jpegOptions;
        Mpeg4Options mpeg4Options;
        VideoRateControl videoRateControl;
        List<VideoResolution> jpegResolutions = null;
        VideoResolution jpegResolution = null;
        int no_resolutions = 0;
        String addr = null;
        IntRange frame_rate_range = null;
        IntRange frame_rate_interval_gov_range = null;
        H264Configuration h264Con = new H264Configuration();
        Mpeg4Configuration mpeg4Con = new Mpeg4Configuration();
        String user = clsSharedVariables.getCam1UserName();
        String pwd = clsSharedVariables.getCam1Pwd();
        int min_val;
        int max_val;

        // Determine camera address and credentials based on cam_no
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
        } else {
            addr = clsDefines.CAM8_IPADDR;
            user = clsSharedVariables.getCam8UserName();
            pwd = clsSharedVariables.getCam8Pwd();
        }

        // Adjust IP address for an analog camera if necessary
        if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
            addr = clsDefines.CAM_ANALOG_IPADDR;
        }

        try {
            try {
                // Connect to the camera device
                if ((addr != null) && (!addr.isEmpty())) {
                    if ((user != null) && (!user.isEmpty())) {
                        nvt = new OnvifDevice(addr, user, pwd);
                        nvt.getSoap().setLogging(false);
                    } else {
                        nvt = new OnvifDevice(addr);
                        nvt.getSoap().setLogging(false);
                    }
                }

                // Retrieve device and media information
                devices = nvt.getDevices();
                media = nvt.getMedia();
                profiles = devices.getProfiles();
                profileToken = profiles.get(stream_type).getToken();
                videoEncoderConfiguration = profiles.get(stream_type).getVideoEncoderConfiguration();
                videoEncoderConfigurationOptions = media.getVideoEncoderConfigurationOptions(profileToken);

                // Switch based on the encoding type of the video encoder configuration
                switch (videoEncoderConfiguration.getEncoding().value()) {
                    case "JPEG":
                        // If JPEG, retrieve JPEG options
                        if (videoEncoderConfigurationOptions.getJPEG() != null) {
                            jpegOptions = videoEncoderConfigurationOptions.getJPEG();
                            jpegResolutions = jpegOptions.getResolutionsAvailable();
                            frame_rate_range = jpegOptions.getFrameRateRange();
                        }
                        break;
                    case "H264":
                        // If H264, retrieve H264 options
                        if (videoEncoderConfigurationOptions.getH264() != null) {
                            h264Options = videoEncoderConfigurationOptions.getH264();
                            jpegResolutions = h264Options.getResolutionsAvailable();
                            frame_rate_range = h264Options.getFrameRateRange();
                            frame_rate_interval_gov_range = h264Options.getGovLengthRange();
                            min_val = frame_rate_interval_gov_range.getMin();
                            max_val = frame_rate_interval_gov_range.getMax();

                            // Adjust frame rate interval if necessary
                            if (frame_rate_interval >= min_val && frame_rate_interval <= max_val) {
                                h264Con.setGovLength(frame_rate_interval);
                            } else if (frame_rate_interval < min_val) {
                                h264Con.setGovLength(min_val);
                            } else {
                                h264Con.setGovLength(max_val);
                            }
                            videoEncoderConfiguration.setH264(h264Con);
                        }
                        break;
                    case "MPEG4":
                        // If MPEG4, retrieve MPEG4 options
                        if (videoEncoderConfigurationOptions.getMPEG4() != null) {
                            mpeg4Options = videoEncoderConfigurationOptions.getMPEG4();
                            jpegResolutions = mpeg4Options.getResolutionsAvailable();
                            frame_rate_range = mpeg4Options.getFrameRateRange();
                            frame_rate_interval_gov_range = mpeg4Options.getGovLengthRange();
                            min_val = frame_rate_interval_gov_range.getMin();
                            max_val = frame_rate_interval_gov_range.getMax();

                            // Adjust frame rate interval if necessary
                            if (frame_rate_interval >= min_val && frame_rate_interval <= max_val) {
                                mpeg4Con.setGovLength(frame_rate_interval);
                            } else if (frame_rate_interval < min_val) {
                                mpeg4Con.setGovLength(min_val);
                            } else {
                                mpeg4Con.setGovLength(max_val);
                            }
                            videoEncoderConfiguration.setMPEG4(mpeg4Con);
                        }
                        break;
                    default:
                        break;
                }

                // Determine the selected resolution index and set it in the video encoder configuration
                int selectedResolutionIndex = 0;
                if (res != 0) {
                    no_resolutions = jpegResolutions.size();
                    if (res <= no_resolutions) {
                        if (no_resolutions >= 5) {
                            selectedResolutionIndex = no_resolutions;
                            jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                        } else if (no_resolutions >= 4) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 3;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 4;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        } else if (no_resolutions >= 3) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 3;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 3;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        } else if (no_resolutions >= 2) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 2;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        } else if (no_resolutions >= 1) {
                            if (res == 5) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 4) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 3) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 2) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else if (res == 1) {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            } else {
                                selectedResolutionIndex = no_resolutions - 1;
                                jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                            }
                        }

                    } else {
                        selectedResolutionIndex = no_resolutions - 1;
                        jpegResolution = jpegResolutions.get(selectedResolutionIndex);
                    }
                }
                videoEncoderConfiguration.setResolution(jpegResolution);

                // Set bitrate limit in video rate control
                videoRateControl = videoEncoderConfiguration.getRateControl();
                videoRateControl.setBitrateLimit(bitrate);

                // Set frame rate limit in video rate control
                min_val = frame_rate_range.getMin();
                max_val = frame_rate_range.getMax();
                // Adjust frame rate limit if necessary
                if (frame_rate < min_val) {
                    videoRateControl.setFrameRateLimit(min_val);
                } else if (frame_rate >= min_val && frame_rate <= max_val) {
                    videoRateControl.setFrameRateLimit(frame_rate);
                } else {
                    videoRateControl.setFrameRateLimit(max_val);
                }

                // Adjust quality value to fit within the valid range
                min_val = videoEncoderConfigurationOptions.getQualityRange().getMin();
                max_val = videoEncoderConfigurationOptions.getQualityRange().getMax();
                if (min_val == 0 && quality > 0) {
                    quality = (byte) (quality - 1);
                }
                if (quality < min_val) {
                    quality = (byte) min_val;
                } else if (quality > max_val) {
                    quality = (byte) max_val;
                }
                videoEncoderConfiguration.setQuality(quality);

                // Set video rate control and update video encoder configuration and media
                videoEncoderConfiguration.setRateControl(videoRateControl);
                profiles.get(stream_type).setVideoEncoderConfiguration(videoEncoderConfiguration);
                media.setVideoEncoderConfiguration(videoEncoderConfiguration);

                return true;

            } catch (ConnectException | SOAPException e) {
                // Handle specific exceptions
                return false;
            } catch (NullPointerException e) {
                // Handle NullPointerException
                return false;
            } catch (Exception e) {
                // Handle general exceptions
                return false;
            }
        } catch (Exception e) {
            // Handle exceptions
            return false;
        } finally {
            // Clean up resources
            nvt = null;
            videoEncoderConfiguration = null;
            media = null;
            profiles = null;
            jpegResolutions = null;
            h264Options = null;
            profileToken = null;
            devices = null;
            videoEncoderConfigurationOptions = null;
            videoRateControl = null;
            jpegResolution = null;
            addr = null;
        }
    }
}
