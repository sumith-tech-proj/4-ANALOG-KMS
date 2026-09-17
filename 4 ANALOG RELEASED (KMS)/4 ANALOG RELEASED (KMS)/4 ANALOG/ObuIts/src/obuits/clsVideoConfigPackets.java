/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sumitha
 */
public class clsVideoConfigPackets {

    final byte VBR = 1;
    final byte CBR = 2;
    final byte ENABLE = 1;

    public boolean set_stream_video_bitrate(byte cam_no, int bitrate, byte stream_type) {
        // Set bitrate for the main stream
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setbitrate(bitrate);
                    break;
                case 2:
                    clsSharedVariables.setbitrate2(bitrate);
                    break;
                case 3:
                    clsSharedVariables.setbitrate3(bitrate);
                    break;
                case 4:
                    clsSharedVariables.setbitrate4(bitrate);
                    break;
                case 5:
                    clsSharedVariables.setbitrate5(bitrate);
                    break;
                case 6:
                    clsSharedVariables.setbitrate6(bitrate);
                    break;
                case 7:
                    clsSharedVariables.setbitrate7(bitrate);
                    break;
                case 8:
                    clsSharedVariables.setbitrate8(bitrate);
                    break;
                default:
                    break;
            }
        } else {
            // Set bitrate for the sub-stream
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setSubbitrate(bitrate);
                    break;
                case 2:
                    clsSharedVariables.setSubbitrate2(bitrate);
                    break;
                case 3:
                    clsSharedVariables.setSubbitrate3(bitrate);
                    break;
                case 4:
                    clsSharedVariables.setSubbitrate4(bitrate);
                    break;
                case 5:
                    clsSharedVariables.setSubbitrate5(bitrate);
                    break;
                case 6:
                    clsSharedVariables.setSubbitrate6(bitrate);
                    break;
                case 7:
                    clsSharedVariables.setSubbitrate7(bitrate);
                    break;
                case 8:
                    clsSharedVariables.setSubbitrate8(bitrate);
                    break;
                default:
                    break;
            }
        }
        // Write the configuration to the file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;

        try {
            // Adjust bitrate settings if ONVIF is not supported
            if (clsSharedVariables.getOnvifSupported() == false) {
                clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
                objj.normal_video_res_bitrate_adjustments(cam_no, stream_type);
                return true;
            } else {
                // Adjust bitrate settings using ONVIF
                clsCameraOnvif obj = new clsCameraOnvif();
                return obj.cameraStreamBitrate(cam_no, bitrate, stream_type);
            }
        } catch (Exception ex) {
            // Handle exceptions (e.g., logging)
        }
        return false;
    }

    public boolean set_stream_video_resolution(byte cam_no, byte res, byte stream_type) {
        boolean response = false;

        // Set resolution for the main stream
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setResolution(res);
                    break;
                case 2:
                    clsSharedVariables.setResolution2(res);
                    break;
                case 3:
                    clsSharedVariables.setResolution3(res);
                    break;
                case 4:
                    clsSharedVariables.setResolution4(res);
                    break;
                case 5:
                    clsSharedVariables.setResolution5(res);
                    break;
                case 6:
                    clsSharedVariables.setResolution6(res);
                    break;
                case 7:
                    clsSharedVariables.setResolution7(res);
                    break;
                case 8:
                    clsSharedVariables.setResolution8(res);
                    break;
                default:
                    break;
            }
        } else {
            // Set resolution for the sub-stream
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setSubstreamResolution(res);
                    break;
                case 2:
                    clsSharedVariables.setSubstreamResolution2(res);
                    break;
                case 3:
                    clsSharedVariables.setSubstreamResolution3(res);
                    break;
                case 4:
                    clsSharedVariables.setSubstreamResolution4(res);
                    break;
                case 5:
                    clsSharedVariables.setSubstreamResolution5(res);
                    break;
                case 6:
                    clsSharedVariables.setSubstreamResolution6(res);
                    break;
                case 7:
                    clsSharedVariables.setSubstreamResolution7(res);
                    break;
                case 8:
                    clsSharedVariables.setSubstreamResolution8(res);
                    break;
                default:
                    break;
            }
        }

        // Write the configuration to the file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;

        // Adjust resolution settings if ONVIF is not supported
        if (clsSharedVariables.getOnvifSupported() == false) {
            clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
            objj.normal_video_res_bitrate_adjustments(cam_no, stream_type);
            return true;
        } else {
            // Adjust resolution settings using ONVIF
            try {
                clsCameraOnvif obj = new clsCameraOnvif();
                response = obj.cameraStreamResolution(cam_no, res, stream_type);
                obj = null;
            } catch (IOException ex) {
                // Handle exceptions (e.g., logging)
            }
            return response;
        }
    }

    public boolean set_stream_video_quality(byte cam_no, byte quality, byte stream_type) {
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setVideoquality(quality);
                    break;
                case 2:
                    clsSharedVariables.setVideoquality2(quality);
                    break;
                case 3:
                    clsSharedVariables.setVideoquality3(quality);
                    break;
                case 4:
                    clsSharedVariables.setVideoquality4(quality);
                    break;
                case 5:
                    clsSharedVariables.setVideoquality5(quality);
                    break;
                case 6:
                    clsSharedVariables.setVideoquality6(quality);
                    break;
                case 7:
                    clsSharedVariables.setVideoquality7(quality);
                    break;
                case 8:
                    clsSharedVariables.setVideoquality8(quality);
                    break;
                default:
                    break;
            }
        } else {
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setSubVideoquality(quality);
                    break;
                case 2:
                    clsSharedVariables.setSubVideoquality2(quality);
                    break;
                case 3:
                    clsSharedVariables.setSubVideoquality3(quality);
                    break;
                case 4:
                    clsSharedVariables.setSubVideoquality4(quality);
                    break;
                case 5:
                    clsSharedVariables.setSubVideoquality5(quality);
                    break;
                case 6:
                    clsSharedVariables.setSubVideoquality6(quality);
                    break;
                case 7:
                    clsSharedVariables.setSubVideoquality7(quality);
                    break;
                case 8:
                    clsSharedVariables.setSubVideoquality8(quality);
                    break;
                default:
                    break;
            }
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
        try {
            if (clsSharedVariables.getOnvifSupported() == false) {
                clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
                objj.normal_video_res_bitrate_adjustments(cam_no, stream_type);
                return true;

            } else {

                boolean response;
                clsCameraOnvif obj = new clsCameraOnvif();
                response = obj.cameraStreamQuality(cam_no, quality, stream_type);
                obj = null;
                return response;
            }
        } catch (Exception ex) {
            //   Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean set_main_stream_video_bitrate_type(byte cam_no, byte bitrate_type, byte stream_type) throws IOException {
        /*
         VBR 1
         CBR 2

         */
        switch (cam_no) {
            case 1:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype("VBR");
                } else {
                    clsSharedVariables.setBitratetype("CBR");
                }
                break;
            case 2:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype2("VBR");
                } else {
                    clsSharedVariables.setBitratetype2("CBR");
                }
                break;
            case 3:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype3("VBR");
                } else {
                    clsSharedVariables.setBitratetype3("CBR");
                }
                break;
            case 4:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype4("VBR");
                } else {
                    clsSharedVariables.setBitratetype4("CBR");
                }
                break;
            case 5:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype5("VBR");
                } else {
                    clsSharedVariables.setBitratetype5("CBR");
                }
                break;
            case 6:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype6("VBR");
                } else {
                    clsSharedVariables.setBitratetype6("CBR");
                }
                break;
            case 7:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype7("VBR");
                } else {
                    clsSharedVariables.setBitratetype7("CBR");
                }
                break;
            case 8:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setBitratetype8("VBR");
                } else {
                    clsSharedVariables.setBitratetype8("CBR");
                }
                break;
            default:
                break;
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
        clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
        objj.normal_video_res_bitrate_adjustments(cam_no, stream_type);
        objj = null;
        return true;

    }

    public boolean set_stream_video_max_bitrate(byte cam_no, int bitrate, byte stream_type) {
        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setbitrate(bitrate);
                    break;
                case 2:
                    clsSharedVariables.setbitrate2(bitrate);
                    break;
                case 3:
                    clsSharedVariables.setbitrate3(bitrate);
                    break;
                case 4:
                    clsSharedVariables.setbitrate4(bitrate);
                    break;
                case 5:
                    clsSharedVariables.setbitrate5(bitrate);
                    break;
                case 6:
                    clsSharedVariables.setbitrate6(bitrate);
                    break;
                case 7:
                    clsSharedVariables.setbitrate7(bitrate);
                    break;
                case 8:
                    clsSharedVariables.setbitrate8(bitrate);
                    break;
                default:
                    break;
            }
        } else {
            if (cam_no == 1) {
                clsSharedVariables.setSubbitrate(bitrate);
            } else if (cam_no == 2) {
                clsSharedVariables.setSubbitrate2(bitrate);
            } else if (cam_no == 3) {
                clsSharedVariables.setSubbitrate3(bitrate);
            } else if (cam_no == 4) {
                clsSharedVariables.setSubbitrate4(bitrate);
            } else if (cam_no == 5) {
                clsSharedVariables.setSubbitrate5(bitrate);
            } else if (cam_no == 6) {
                clsSharedVariables.setSubbitrate6(bitrate);
            } else if (cam_no == 7) {
                clsSharedVariables.setSubbitrate7(bitrate);
            } else if (cam_no == 8) {
                clsSharedVariables.setSubbitrate8(bitrate);
            }

        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
        boolean response = false;
        try {
            if (clsSharedVariables.getOnvifSupported() == false) {
                clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
                objj.normal_video_res_bitrate_adjustments(cam_no, stream_type);
                objj = null;
            } else {
                clsCameraOnvif obj = new clsCameraOnvif();
                response = obj.cameraStreamBitrate(cam_no, bitrate, stream_type);
                obj = null;
            }
        } catch (IOException ex) {
            // Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;

    }

    public boolean set_stream_video_framerate(byte cam_no, byte framerate, byte stream_type) {
        boolean response = false;

        if (stream_type == clsDefines.CAM_MAIN_STREAM) {
            switch (cam_no) {
                case 1:
                    clsSharedVariables.setFramerate(framerate);
                    break;
                case 2:
                    clsSharedVariables.setFramerate2(framerate);
                    break;
                case 3:
                    clsSharedVariables.setFramerate3(framerate);
                    break;
                case 4:
                    clsSharedVariables.setFramerate4(framerate);
                    break;
                case 5:
                    clsSharedVariables.setFramerate5(framerate);
                    break;
                case 6:
                    clsSharedVariables.setFramerate6(framerate);
                    break;
                case 7:
                    clsSharedVariables.setFramerate7(framerate);
                    break;
                case 8:
                    clsSharedVariables.setFramerate8(framerate);
                    break;
                default:
                    break;
            }
        } else {
            if (cam_no == 1) {
                clsSharedVariables.setSubFramerate(framerate);
            } else if (cam_no == 2) {
                clsSharedVariables.setSubFramerate2(framerate);
            } else if (cam_no == 3) {
                clsSharedVariables.setSubFramerate3(framerate);
            } else if (cam_no == 4) {
                clsSharedVariables.setSubFramerate4(framerate);
            } else if (cam_no == 5) {
                clsSharedVariables.setSubFramerate5(framerate);
            } else if (cam_no == 6) {
                clsSharedVariables.setSubFramerate6(framerate);
            } else if (cam_no == 7) {
                clsSharedVariables.setSubFramerate7(framerate);
            } else if (cam_no == 8) {
                clsSharedVariables.setSubFramerate8(framerate);
            }
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
        try {
            if (clsSharedVariables.getOnvifSupported() == false) {
                clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
                objj.normal_video_res_bitrate_adjustments(cam_no, stream_type);

            } else {
                clsCameraOnvif obj = new clsCameraOnvif();
                response = obj.cameraStreamFramerate(cam_no, framerate, stream_type);
                obj = null;
            }
        } catch (IOException ex) {
            // Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;

    }

    public boolean set_main_stream_video_frame_interval(byte cam_no, byte frameInterval, byte stream_type) {
        boolean response = false;
        switch (cam_no) {
            case 1:
                clsSharedVariables.setFrameInterval(frameInterval);
                break;
            case 2:
                clsSharedVariables.setFrameInterval2(frameInterval);
                break;
            case 3:
                clsSharedVariables.setFrameInterval3(frameInterval);
                break;
            case 4:
                clsSharedVariables.setFrameInterval4(frameInterval);
                break;
            case 5:
                clsSharedVariables.setFrameInterval5(frameInterval);
                break;
            case 6:
                clsSharedVariables.setFrameInterval6(frameInterval);
                break;
            case 7:
                clsSharedVariables.setFrameInterval7(frameInterval);
                break;
            case 8:
                clsSharedVariables.setFrameInterval8(frameInterval);
                break;
            default:
                break;
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
        try {
            if (clsSharedVariables.getOnvifSupported() == false) {
                clsVideoNormalConfigUpdates obj = new clsVideoNormalConfigUpdates();
                obj.normal_video_res_bitrate_adjustments(cam_no, stream_type);
                obj = null;
                return true;

            } else {
                clsCameraOnvif obj = new clsCameraOnvif();
                response = obj.cameraStreamFramerateInterval(cam_no, frameInterval, stream_type);
                obj = null;
                return response;
            }
        } catch (Exception ex) {
            //  Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean set_sub_stream_video_bitrate(byte cam_no, short bitrate) {
        switch (cam_no) {
            case 1:
                clsSharedVariables.setSubbitrate(bitrate);
                break;
            case 2:
                clsSharedVariables.setSubbitrate2(bitrate);
                break;
            case 3:
                clsSharedVariables.setSubbitrate3(bitrate);
                break;
            case 4:
                clsSharedVariables.setSubbitrate4(bitrate);
                break;
            case 5:
                clsSharedVariables.setSubbitrate5(bitrate);
                break;
            case 6:
                clsSharedVariables.setSubbitrate6(bitrate);
                break;
            case 7:
                clsSharedVariables.setSubbitrate7(bitrate);
                break;
            case 8:
                clsSharedVariables.setSubbitrate8(bitrate);
                break;
            default:
                break;
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
        clsCameraOnvif obj = new clsCameraOnvif();
        try {

            return obj.cameraStreamBitrate(cam_no, bitrate, clsDefines.CAM_SUB_STREAM);

        } catch (IOException ex) {
            // Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            obj = null;
        }
    }

    /**
     * Sets the sub-stream video bitrate type for a specific camera and writes
     * the configuration to a file.
     *
     * @param cam_no The camera number (1-8).
     * @param bitrate_type The bitrate type (e.g., VBR for variable bitrate, CBR
     * for constant bitrate).
     * @return boolean Returns true if the operation is successful.
     * @throws IOException If an I/O error occurs.
     */
    public boolean set_sub_stream_video_bitrate_type(byte cam_no, byte bitrate_type) throws IOException {
        // Determine the bitrate type for the specified camera
        switch (cam_no) {
            case 1:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype("CBR");
                }
                break;
            case 2:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype2("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype2("CBR");
                }
                break;
            case 3:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype3("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype3("CBR");
                }
                break;
            case 4:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype4("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype4("CBR");
                }
                break;
            case 5:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype5("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype5("CBR");
                }
                break;
            case 6:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype6("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype6("CBR");
                }
                break;
            case 7:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype7("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype7("CBR");
                }
                break;
            case 8:
                if (bitrate_type == VBR) {
                    clsSharedVariables.setSubBitratetype8("VBR");
                } else {
                    clsSharedVariables.setSubBitratetype8("CBR");
                }
                break;
            default:
                break;
        }
        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;

        // Adjust the bitrate based on the new configuration
        clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
        objj.normal_video_res_bitrate_adjustments(cam_no, clsDefines.CAM_SUB_STREAM);
        objj = null;
        return true;
    }

    /**
     * Sets the sub-stream video frame interval for a specific camera and writes
     * the configuration to a file.
     *
     * @param cam_no The camera number (1-8).
     * @param interval The frame interval in seconds.
     * @return boolean Returns true if the operation is successful, false
     * otherwise.
     */
    public boolean set_sub_stream_video_frame_interval(byte cam_no, byte interval) {
        // Set the frame interval for the specified camera
        switch (cam_no) {
            case 1:
                clsSharedVariables.setSubFrameInterval(interval);
                break;
            case 2:
                clsSharedVariables.setSubFrameInterval2(interval);
                break;
            case 3:
                clsSharedVariables.setSubFrameInterval3(interval);
                break;
            case 4:
                clsSharedVariables.setSubFrameInterval4(interval);
                break;
            case 5:
                clsSharedVariables.setSubFrameInterval5(interval);
                break;
            case 6:
                clsSharedVariables.setSubFrameInterval6(interval);
                break;
            case 7:
                clsSharedVariables.setSubFrameInterval7(interval);
                break;
            case 8:
                clsSharedVariables.setSubFrameInterval8(interval);
                break;
            default:
                break;
        }

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;

        boolean response;
        try {
            if (clsSharedVariables.getOnvifSupported() == false) {
                // Adjust the bitrate if ONVIF is not supported
                clsVideoNormalConfigUpdates objj = new clsVideoNormalConfigUpdates();
                objj.normal_video_res_bitrate_adjustments(cam_no, clsDefines.CAM_SUB_STREAM);
                return true;
            } else {
                // Adjust the frame rate interval using ONVIF
                clsCameraOnvif obj = new clsCameraOnvif();
                response = obj.cameraStreamFramerateInterval(cam_no, interval, clsDefines.CAM_SUB_STREAM);
                obj = null;
                return response;
            }
        } catch (IOException ex) {
            // Log the exception (commented out here for brevity)
            // Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Enables or disables video recording for a specific camera and writes the
     * configuration to a file.
     *
     * @param cam_no The camera number (1-8).
     * @param status The status (e.g., ENABLE to enable recording, DISABLE to
     * disable recording).
     */
    public void set_video_rec_enable_disable(byte cam_no, byte status) {
        // Enable or disable video recording for the specified camera
        switch (cam_no) {
            case 1:
                if (status == ENABLE) {
                    clsSharedVariables.setCam1RecordSelected(true);
                } else {
                    clsSharedVariables.setCam1RecordSelected(false);
                }
                break;
            case 2:
                if (status == ENABLE) {
                    clsSharedVariables.setCam2RecordSelected(true);
                } else {
                    clsSharedVariables.setCam2RecordSelected(false);
                }
                break;
            case 3:
                if (status == ENABLE) {
                    clsSharedVariables.setCam3RecordSelected(true);
                } else {
                    clsSharedVariables.setCam3RecordSelected(false);
                }
                break;
            case 4:
                if (status == ENABLE) {
                    clsSharedVariables.setCam4RecordSelected(true);
                } else {
                    clsSharedVariables.setCam4RecordSelected(false);
                }
                break;
            case 5:
                if (status == ENABLE) {
                    clsSharedVariables.setCam5RecordSelected(true);
                } else {
                    clsSharedVariables.setCam5RecordSelected(false);
                }
                break;
            case 6:
                if (status == ENABLE) {
                    clsSharedVariables.setCam6RecordSelected(true);
                } else {
                    clsSharedVariables.setCam6RecordSelected(false);
                }
                break;
            case 7:
                if (status == ENABLE) {
                    clsSharedVariables.setCam7RecordSelected(true);
                } else {
                    clsSharedVariables.setCam7RecordSelected(false);
                }
                break;
            case 8:
                if (status == ENABLE) {
                    clsSharedVariables.setCam8RecordSelected(true);
                } else {
                    clsSharedVariables.setCam8RecordSelected(false);
                }
                break;
            default:
                break;
        }

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Enables or disables audio recording for a specific camera and writes the
     * configuration to a file.
     *
     * @param cam_no The camera number (1-8).
     * @param status The status (e.g., ENABLE to enable audio recording, DISABLE
     * to disable audio recording).
     */
    public void set_audio_rec_enable_disable(byte cam_no, byte status) {
        // Enable or disable audio recording for the specified camera
        switch (cam_no) {
            case 1:
                if (status == ENABLE) {
                    clsSharedVariables.setCam1RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam1RecordAudioSelected(false);
                }
                break;
            case 2:
                if (status == ENABLE) {
                    clsSharedVariables.setCam2RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam2RecordAudioSelected(false);
                }
                break;
            case 3:
                if (status == ENABLE) {
                    clsSharedVariables.setCam3RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam3RecordAudioSelected(false);
                }
                break;
            case 4:
                if (status == ENABLE) {
                    clsSharedVariables.setCam4RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam4RecordAudioSelected(false);
                }
                break;
            case 5:
                if (status == ENABLE) {
                    clsSharedVariables.setCam5RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam5RecordAudioSelected(false);
                }
                break;
            case 6:
                if (status == ENABLE) {
                    clsSharedVariables.setCam6RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam6RecordAudioSelected(false);
                }
                break;
            case 7:
                if (status == ENABLE) {
                    clsSharedVariables.setCam7RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam7RecordAudioSelected(false);
                }
                break;
            case 8:
                if (status == ENABLE) {
                    clsSharedVariables.setCam8RecordAudioSelected(true);
                } else {
                    clsSharedVariables.setCam8RecordAudioSelected(false);
                }
                break;
            default:
                break;
        }

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Sets the display name for a specific camera and writes the configuration
     * to a file.
     *
     * @param cam_no The camera number (1-8).
     * @param dis_name The display name for the camera.
     */
    public void set_cam_name_display(byte cam_no, String dis_name) {
        // Set the display name for the specified camera
        switch (cam_no) {
            case 1:
                clsSharedVariables.setCameraName1(dis_name);
                break;
            case 2:
                clsSharedVariables.setCameraName2(dis_name);
                break;
            case 3:
                clsSharedVariables.setCameraName3(dis_name);
                break;
            case 4:
                clsSharedVariables.setCameraName4(dis_name);
                break;
            case 5:
                clsSharedVariables.setCameraName5(dis_name);
                break;
            case 6:
                clsSharedVariables.setCameraName6(dis_name);
                break;
            case 7:
                clsSharedVariables.setCameraName7(dis_name);
                break;
            case 8:
                clsSharedVariables.setCameraName8(dis_name);
                break;
            default:
                break;
        }

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;

        // Update watermarking for the camera
        setWaterMarking(cam_no);
    }

    /**
     * Sets the video recording mode to continuous or scheduled and writes the
     * configuration to a file.
     *
     * @param mode The recording mode (e.g., continuous or scheduled).
     */
    public void set_vid_rec_mode_cont_sch(byte mode) {
        // Set the recording mode
        clsSharedVariables.setRecordType(mode);

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the schedule for video recording and writes the configuration to a
     * file.
     *
     * @param week_days An array of bytes representing the recording schedule
     * for each day of the week.
     * @param start_time The start time of the recording schedule.
     * @param end_time The end time of the recording schedule.
     */
    public void set_vid_rec_mode_sch_config(byte[] week_days, Date start_time, Date end_time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        // Set the recording schedule for each day of the week
        clsSharedVariables.setSchRecSunday(week_days[0]);
        clsSharedVariables.setSchRecMonday(week_days[1]);
        clsSharedVariables.setSchRecTuesday(week_days[2]);
        clsSharedVariables.setSchRecWednesday(week_days[3]);
        clsSharedVariables.setSchRecThursday(week_days[4]);
        clsSharedVariables.setSchRecFriday(week_days[5]);
        clsSharedVariables.setSchRecSaturday(week_days[6]);
        // Set the start and end times for the recording schedule
        clsSharedVariables.setRecordFromTime(sdf1.format(start_time));
        clsSharedVariables.setRecordToTime(sdf1.format(end_time));

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Sets the recording expiration time in days and writes the configuration
     * to a file.
     *
     * @param rec_time The recording expiration time in days.
     */
    public void set_vid_rec_exp_time(byte rec_time) {
        // Set the recording expiration time
        clsSharedVariables.setRecExpiryDays(rec_time);

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Sets the motion detection recording configuration for a specific camera
     * and writes the configuration to a file.
     *
     * @param cam_no The camera number (1-8).
     * @param ena_dis The enable/disable status for motion detection.
     * @param rec_time The recording time in seconds after motion is detected.
     */
    public void set_vid_rec_motion_detect(byte cam_no, byte ena_dis, byte rec_time) {
        // Set motion detection recording configuration for the specified camera
        switch (cam_no) {
            case 1:
                // clsSharedVariables.setMotionEnableCam1(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam1(true);
                    clsSharedVariables.setRecordTimeCam1((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam1(false);
                }
                break;
            case 2:
                // clsSharedVariables.setMotionEnableCam2(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam2(true);
                    clsSharedVariables.setRecordTimeCam2((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam2(false);
                }
                break;
            case 3:
                //clsSharedVariables.setMotionEnableCam3(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam3(true);
                    clsSharedVariables.setRecordTimeCam3((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam3(false);
                }
                break;
            case 4:
                //clsSharedVariables.setMotionEnableCam4(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam4(true);
                    clsSharedVariables.setRecordTimeCam4((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam4(false);
                }
                break;
            case 5:
                //clsSharedVariables.setMotionEnableCam5(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam5(true);
                    clsSharedVariables.setRecordTimeCam5((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam5(false);
                }
                break;
            case 6:
                //  clsSharedVariables.setMotionEnableCam6(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam6(true);
                    clsSharedVariables.setRecordTimeCam6((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam6(false);
                }
                break;
            case 7:
                //clsSharedVariables.setMotionEnableCam7(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam7(true);
                    clsSharedVariables.setRecordTimeCam7((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam7(false);
                }
                break;
            case 8:
                //  clsSharedVariables.setMotionEnableCam8(ena_dis == ENABLE);
                if (ena_dis == ENABLE) {
                    clsSharedVariables.setMotionEnableCam8(true);
                    clsSharedVariables.setRecordTimeCam8((byte) rec_time);
                } else {
                    clsSharedVariables.setMotionEnableCam8(false);
                }
                break;
            default:
                break;
        }

        // Write the updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Sets motion detection sensitivity, threshold, and recording time for a
     * specified camera. Updates corresponding variables in clsSharedVariables
     * and writes configuration to file.
     *
     * @param cam_no The camera number for which motion detection settings are
     * to be set.
     * @param sensitivity The sensitivity level to set for motion detection.
     * @param threshold The threshold value to set for motion detection.
     * @param rec_time The recording time to set after motion detection.
     */
    public void set_vid_rec_motion_detect_sensitivity(byte cam_no, byte sensitivity, byte threshold, byte rec_time) {
        // Initialize variables for motion detection regions
        int X1 = (byte) clsSharedVariables.getMotionX1Cam1();
        int X2 = (byte) clsSharedVariables.getMotionX2Cam1();

        // Set motion detection parameters based on camera number
        switch (cam_no) {
            case 1:
                X1 = (byte) clsSharedVariables.getMotionX1Cam1();
                X2 = (byte) clsSharedVariables.getMotionX2Cam1();
                clsSharedVariables.setThresholdCam1((byte) threshold);
                clsSharedVariables.setSensitivityCam1((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam1((byte) rec_time);
                break;
            case 2:
                X1 = (byte) clsSharedVariables.getMotionX1Cam2();
                X2 = (byte) clsSharedVariables.getMotionX2Cam2();
                clsSharedVariables.setThresholdCam2((byte) threshold);
                clsSharedVariables.setSensitivityCam2((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam2((byte) rec_time);
                break;
            case 3:
                X1 = (byte) clsSharedVariables.getMotionX1Cam3();
                X2 = (byte) clsSharedVariables.getMotionX2Cam3();
                clsSharedVariables.setThresholdCam3((byte) threshold);
                clsSharedVariables.setSensitivityCam3((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam3((byte) rec_time);
                break;
            case 4:
                X1 = (byte) clsSharedVariables.getMotionX1Cam4();
                X2 = (byte) clsSharedVariables.getMotionX2Cam4();
                clsSharedVariables.setThresholdCam4((byte) threshold);
                clsSharedVariables.setSensitivityCam4((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam4((byte) rec_time);
                break;
            case 5:
                X1 = (byte) clsSharedVariables.getMotionX1Cam5();
                X2 = (byte) clsSharedVariables.getMotionX2Cam5();
                clsSharedVariables.setThresholdCam5((byte) threshold);
                clsSharedVariables.setSensitivityCam5((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam5((byte) rec_time);
                break;
            case 6:
                X1 = (byte) clsSharedVariables.getMotionX1Cam6();
                X2 = (byte) clsSharedVariables.getMotionX2Cam6();
                clsSharedVariables.setThresholdCam6((byte) threshold);
                clsSharedVariables.setSensitivityCam6((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam6((byte) rec_time);
                break;
            case 7:
                X1 = (byte) clsSharedVariables.getMotionX1Cam7();
                X2 = (byte) clsSharedVariables.getMotionX2Cam7();
                clsSharedVariables.setThresholdCam7((byte) threshold);
                clsSharedVariables.setSensitivityCam7((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam7((byte) rec_time);
                break;
            default:
                X1 = (byte) clsSharedVariables.getMotionX1Cam8();
                X2 = (byte) clsSharedVariables.getMotionX2Cam8();
                clsSharedVariables.setThresholdCam8((byte) threshold);
                clsSharedVariables.setSensitivityCam8((byte) sensitivity);
                clsSharedVariables.setRecordTimeCam8((byte) rec_time);
                break;
        }

        try {
            // Calculate motion region value based on X1 and X2
            int sub = X2 - X1;
            int val = 0;
            for (int i = 0; i < sub; i++) {
                val = (int) (val + Math.pow(2, X1));
                X1++;
            }

            // Set motion region using clsVideoNormalConfigUpdates class
            clsVideoNormalConfigUpdates objWater = new clsVideoNormalConfigUpdates();
            objWater.setMotionregion(cam_no, val);

            // Write camera configuration data to file
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_camera_cfg_file();
            objWater = null;
            objReadFiles = null;
        } catch (Exception e) {
            // Handle any exceptions if necessary
        }
    }

    /**
     * Sets motion detection area coordinates and recording time for a specified
     * camera. Updates corresponding variables in clsSharedVariables and writes
     * configuration to file.
     *
     * @param cam_no The camera number for which motion detection area is to be
     * set.
     * @param X1 The X-coordinate of the top-left corner of motion detection
     * area.
     * @param X2 The X-coordinate of the bottom-right corner of motion detection
     * area.
     * @param Y1 The Y-coordinate of the top-left corner of motion detection
     * area.
     * @param Y2 The Y-coordinate of the bottom-right corner of motion detection
     * area.
     * @param rec_time The recording time to set after motion detection.
     */
    public void set_vid_rec_motion_detect_area(byte cam_no, byte X1, byte X2, byte Y1, byte Y2, byte rec_time) {
        // Set motion detection area parameters based on camera number
        if (cam_no == 1) {
            clsSharedVariables.setMotionX1Cam1((byte) X1);
            clsSharedVariables.setMotionY1Cam1((byte) Y1);
            clsSharedVariables.setMotionX2Cam1((byte) X2);
            clsSharedVariables.setMotionY2Cam1((byte) Y2);
            clsSharedVariables.setRecordTimeCam1((byte) rec_time);
        } else if (cam_no == 2) {
            clsSharedVariables.setMotionX1Cam2((byte) X1);
            clsSharedVariables.setMotionY1Cam2((byte) Y1);
            clsSharedVariables.setMotionX2Cam2((byte) X2);
            clsSharedVariables.setMotionY2Cam2((byte) Y2);
            clsSharedVariables.setRecordTimeCam2((byte) rec_time);
        } else if (cam_no == 3) {
            clsSharedVariables.setMotionX1Cam3((byte) X1);
            clsSharedVariables.setMotionY1Cam3((byte) Y1);
            clsSharedVariables.setMotionX2Cam3((byte) X2);
            clsSharedVariables.setMotionY2Cam3((byte) Y2);
            clsSharedVariables.setRecordTimeCam3((byte) rec_time);
        } else if (cam_no == 4) {
            clsSharedVariables.setMotionX1Cam4((byte) X1);
            clsSharedVariables.setMotionY1Cam4((byte) Y1);
            clsSharedVariables.setMotionX2Cam4((byte) X2);
            clsSharedVariables.setMotionY2Cam4((byte) Y2);
            clsSharedVariables.setRecordTimeCam4((byte) rec_time);
        } else if (cam_no == 5) {
            clsSharedVariables.setMotionX1Cam5((byte) X1);
            clsSharedVariables.setMotionY1Cam5((byte) Y1);
            clsSharedVariables.setMotionX2Cam5((byte) X2);
            clsSharedVariables.setMotionY2Cam5((byte) Y2);
            clsSharedVariables.setRecordTimeCam5((byte) rec_time);
        } else if (cam_no == 6) {
            clsSharedVariables.setMotionX1Cam6((byte) X1);
            clsSharedVariables.setMotionY1Cam6((byte) Y1);
            clsSharedVariables.setMotionX2Cam6((byte) X2);
            clsSharedVariables.setMotionY2Cam6((byte) Y2);
            clsSharedVariables.setRecordTimeCam6((byte) rec_time);
        } else if (cam_no == 7) {
            clsSharedVariables.setMotionX1Cam7((byte) X1);
            clsSharedVariables.setMotionY1Cam7((byte) Y1);
            clsSharedVariables.setMotionX2Cam7((byte) X2);
            clsSharedVariables.setMotionY2Cam7((byte) Y2);
            clsSharedVariables.setRecordTimeCam7((byte) rec_time);
        } else {
            clsSharedVariables.setMotionX1Cam8((byte) X1);
            clsSharedVariables.setMotionY1Cam8((byte) Y1);
            clsSharedVariables.setMotionX2Cam8((byte) X2);
            clsSharedVariables.setMotionY2Cam8((byte) Y2);
            clsSharedVariables.setRecordTimeCam8((byte) rec_time);
        }

        try {
            // Calculate motion region value based on X1 and X2
            int sub = X2 - X1;
            int val = 0;
            for (int i = 0; i < sub; i++) {
                val = (int) (val + Math.pow(2, X1));
                X1++;
            }
            clsVideoNormalConfigUpdates objWater = new clsVideoNormalConfigUpdates();
            objWater.setMotionregion(cam_no, val);

            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_camera_cfg_file();
            objWater = null;
            objReadFiles = null;
        } catch (Exception e) {

        }
    }

    /**
     * Sets the pre-event recording interval in clsSharedVariables and writes
     * configuration to file.
     *
     * @param interval The duration (in seconds) to record before an event
     * occurs.
     */
    public void set_vid_rec_pre_event_interval(byte interval) {
        // Set the pre-event recording interval
        clsSharedVariables.setEventPreRecordTime(interval);

        // Create a new instance of clsReadFiles to handle file writing
        clsReadFiles objReadFiles = new clsReadFiles();

        // Write the updated configuration to file
        objReadFiles.write_cfg_data_file();

        // Clean up the object to free resources
        objReadFiles = null;
    }

    /**
     * Sets the post-event recording interval in clsSharedVariables and writes
     * configuration to file.
     *
     * @param interval The duration (in seconds) to record after an event
     * occurs.
     */
    public void set_vid_rec_post_event_interval(byte interval) {
        // Set the post-event recording interval
        clsSharedVariables.setEventPostRecordTime(interval);

        // Create a new instance of clsReadFiles to handle file writing
        clsReadFiles objReadFiles = new clsReadFiles();

        // Write the updated configuration to file
        objReadFiles.write_cfg_data_file();

        // Clean up the object to free resources
        objReadFiles = null;
    }

    /**
     * Sets the snapshot resolution for all digital streams of a specified
     * camera. Updates corresponding variables in clsSharedVariables and writes
     * configuration to file.
     *
     * @param cam_no The camera number for which snapshot resolution is to be
     * set.
     * @param stream_type The type of stream to set (e.g., resolution type).
     */
    public void set_cam_snapshot_resolution_backend(byte cam_no, byte stream_type) {
        switch (cam_no) {
            case 1:
                // Set snapshot resolution for camera 1's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam1(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam1(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam1(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam1(stream_type);
                break;
            case 2:
                // Set snapshot resolution for camera 2's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam2(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam2(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam2(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam2(stream_type);
                break;
            case 3:
                // Set snapshot resolution for camera 3's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam3(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam3(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam3(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam3(stream_type);
                break;
            case 4:
                // Set snapshot resolution for camera 4's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam4(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam4(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam4(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam4(stream_type);
                break;
            case 5:
                // Set snapshot resolution for camera 5's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam5(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam5(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam5(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam5(stream_type);
                break;
            case 6:
                // Set snapshot resolution for camera 6's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam6(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam6(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam6(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam6(stream_type);
                break;
            case 7:
                // Set snapshot resolution for camera 7's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam7(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam7(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam7(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam7(stream_type);
                break;
            case 8:
                // Set snapshot resolution for camera 8's digital streams
                clsSharedVariables.setSnapShotDig1StreamCam8(stream_type);
                clsSharedVariables.setSnapShotDig2StreamCam8(stream_type);
                clsSharedVariables.setSnapShotDig3StreamCam8(stream_type);
                clsSharedVariables.setSnapShotDig4StreamCam8(stream_type);
                break;
            default:
                // Handle any other camera numbers (if applicable)
                break;
        }

        // Write camera configuration data to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Sets the continuous snapshot interval for a specified camera. Updates
     * corresponding variables in clsSharedVariables and writes configuration to
     * file.
     *
     * @param cam_no The camera number for which continuous snapshot interval is
     * to be set.
     * @param interval The interval value to set for continuous snapshots.
     */
    
    public void set_cam_snapshot_cont_interval(byte cam_no, byte interval) {
        switch (cam_no) {
            case 1:
                // Set continuous snapshot interval for camera 1
                clsSharedVariables.setSnapShotContIntervalCam1(interval);
                break;
            case 2:
                // Set continuous snapshot interval for camera 2
                clsSharedVariables.setSnapShotContIntervalCam2(interval);
                break;
            case 3:
                // Set continuous snapshot interval for camera 3
                clsSharedVariables.setSnapShotContIntervalCam3(interval);
                break;
            case 4:
                // Set continuous snapshot interval for camera 4
                clsSharedVariables.setSnapShotContIntervalCam4(interval);
                break;
            case 5:
                // Set continuous snapshot interval for camera 5
                clsSharedVariables.setSnapShotContIntervalCam5(interval);
                break;
            case 6:
                // Set continuous snapshot interval for camera 6
                clsSharedVariables.setSnapShotContIntervalCam6(interval);
                break;
            case 7:
                // Set continuous snapshot interval for camera 7
                clsSharedVariables.setSnapShotContIntervalCam7(interval);
                break;
            case 8:
                // Set continuous snapshot interval for camera 8
                clsSharedVariables.setSnapShotContIntervalCam8(interval);
                break;
            default:
                // Handle any other camera numbers (if applicable)
                break;
        }

        // Write camera configuration data to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_camera_cfg_file();
        objReadFiles = null;
    }

    /**
     * Sets the ignition on/off configuration parameters and writes them to the
     * configuration file.
     *
     * @param mode Sleep mode state.
     * @param delay Main device shut time delay.
     * @param sleep_interval GPRS sleep interval value.
     * @param normal_interval GPRS normal mode value.
     */
    
    public void set_ign_onoff_config(byte mode, byte delay, byte sleep_interval, byte normal_interval) {
        // Set configuration parameters in shared variables
        clsSharedVariables.setSleepModeState(mode);
        clsSharedVariables.setMainDeviceShutTime(delay);
        clsSharedVariables.set_gprs_sleep_val(sleep_interval);
        clsSharedVariables.set_gprs_normal_mode_val(normal_interval);

        // Write updated configuration to file
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the primary IP address and writes it to the configuration file.
     *
     * @param addr Primary IP address to be set.
     */
    public void set_primary_ipaddress(String addr) {
        // Set primary IP address in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setIpAddr1(addr);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the secondary IP address and writes it to the configuration file.
     *
     * @param addr Secondary IP address to be set.
     */
    public void set_secondary_ipaddress(String addr) {
        // Set secondary IP address in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setIpAddr2(addr);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the primary port number and writes it to the configuration file.
     *
     * @param port_no Primary port number to be set.
     */
    public void set_primary_port(short port_no) {
        // Set primary port number in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setPortNo1(port_no);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the secondary port number and writes it to the configuration file.
     *
     * @param port_no Secondary port number to be set.
     */
    public void set_secondary_port(short port_no) {
        // Set secondary port number in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setPortNo2(port_no);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the vehicle registration number and writes it to the configuration
     * file.
     *
     * @param data Vehicle registration number to be set.
     */
    public void set_vehicle_regno(String data) {
        // Set vehicle registration number in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setVechicleRegNo(data);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the Access Point Name (APN) and writes it to the configuration file.
     *
     * @param data APN to be set.
     */
    public void set_apn(String data) {
        // Set APN in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setApn(data);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the overspeed limit and writes it to the configuration file.
     *
     * @param data Overspeed limit to be set.
     */
    public void set_overspeed(String data) {
        // Set overspeed limit in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        gpsDriving.over_speed_limit = Short.parseShort(data);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the harsh acceleration threshold and writes it to the configuration
     * file.
     *
     * @param data Harsh acceleration threshold to be set.
     */
    public void set_harshacc(String data) {
        // Set harsh acceleration threshold in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        gpsDriving.harsh_acc_threshold = Short.parseShort(data);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the harsh braking threshold and writes it to the configuration file.
     *
     * @param data Harsh braking threshold to be set.
     */
    public void set_harshbreak(String data) {
        // Set harsh braking threshold in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        gpsDriving.harsh_brk_threshold = Short.parseShort(data);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the rash turning threshold and writes it to the configuration file.
     *
     * @param data Rash turning threshold to be set.
     */
    public void set_rashturn(String data) {
        // Set rash turning threshold in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setGyroAngle(Short.parseShort(data));

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the emergency duration and writes it to the configuration file.
     *
     * @param duration Emergency duration to be set.
     */
    public void set_emergency_duration(byte duration) {
        // Set emergency duration in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setEmergencyStateTimeDuration(duration);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets the transmission mode for always-on functionality and writes it to
     * the configuration file.
     *
     * @param emergency_button Emergency button mode.
     * @param sms_mode SMS mode.
     */
    public void set_transmission_mode_always_on(byte emergency_button, byte sms_mode) {
        // Set transmission mode parameters in shared variables
        clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.setEmergencyModeByPanicButton(emergency_button);
        clsSharedVariables.setEmergencyModeBySmsMode(sms_mode);

        // Write updated configuration to file
        objReadFiles.write_cfg_data_file();
        objReadFiles = null;
    }

    /**
     * Sets watermarking configurations for a specified camera. This method
     * ensures thread safety by synchronizing access.
     *
     * @param cam_no The camera number for which watermarking is to be set.
     * @return true if watermarking was successfully set, false otherwise.
     */
    public synchronized boolean setWaterMarking(byte cam_no) {
        try {
            // Create an instance of clsVideoNormalConfigUpdates to manage video configuration updates
            clsVideoNormalConfigUpdates obj = new clsVideoNormalConfigUpdates();

            // Call setWaterMarking method to apply watermarking for the specified camera
            obj.setWaterMarking(cam_no);

            // Clean up the object reference to free up resources
            obj = null;

            // Return true indicating watermarking was successfully set
            return true;
        } catch (Exception ex) {
            // Handle any exceptions that occur during watermarking setup
            // Logging the exception can be uncommented for debugging purposes
            // Logger.getLogger(clsVideoConfigPackets.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Return false indicating failure to set watermarking
        return false;
    }
}
