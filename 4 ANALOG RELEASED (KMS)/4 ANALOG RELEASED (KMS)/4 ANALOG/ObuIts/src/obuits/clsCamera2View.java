
package obuits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import static obuits.MainFrmIts.getVideoStarted;
import static obuits.MainFrmIts.objCamView2;
import static obuits.clsDefines.ANALOG_IPADDR;
import static obuits.clsDefines.xdotool;

public class clsCamera2View extends Thread {

    static Process cam_view_proc;
    static OutStreamCam1Thread outCam1;
    static long pid = 0;
    static Runtime rt = null;
    boolean all_cameras = false;

    public clsCamera2View(boolean allcams) {
        super();
        all_cameras = allcams;
    }
    clsOnvifUrlCamera objOnvif = new clsOnvifUrlCamera();

    @Override
    public void run() {

        while (true) {
              
            String cmd = null;
            String camurl = "@" + clsDefines.CAM2_IPADDR;
            String user_name = clsSharedVariables.getCam2UserName();
            String pwd = clsSharedVariables.getCam2Pwd();
            byte cam_type = clsSharedVariables.getCam2Type();
            String url = objOnvif.get_cam2_record_sub_url();
            if (clsSharedVariables.camera_type == clsDefines.ANALOG_CAMERA) {
                camurl = "@" + clsDefines.CAM_ANALOG_IPADDR;
            }
            if (all_cameras == false) {
                if (cam_type == clsDefines.CAM_CPPLUS) {

                  
                        if (clsDefines.omxplayer_found) {
                            cmd = "omxplayer --win '0 80 800 600' --live --timeout 3 -o hdmi --fps 30 --aspect-mode stretch " + "'rtsp://" + user_name + ":" + pwd + "@"+ANALOG_IPADDR + ":554/cam/realmonitor?channel=2&subtype=1'";
                        } else {
                            cmd = "ffplay -i -left 0 -top  100 -x 800 -y 530 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'http://" + user_name + ":" + pwd + camurl + "/cgi-bin/mjpg/video.cgi?[channel=2]&subtype=1'";
                        }
                    

                }else if (cam_type == clsDefines.CAM_SPARSH) {

                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        cmd = "omxplayer --win '0 80 800 600' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " +  "'rtsp://" + user_name + ":" + pwd +   camurl + ":554/ch01.264?dev=1'";

                    }} else if (cam_type == clsDefines.CAM_HIKVISION) {
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        if (clsDefines.omxplayer_found) {
                            cmd = "omxplayer --win '0 80 800 600' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            //  System.out.println("cmd: " + cmd);
                        } else {
                            cmd = "ffplay -i -left 0 -top  100 -x 800 -y 530 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";

                        }
                    } else {//analog
                        if (clsDefines.omxplayer_found) {
                            cmd = "omxplayer --win '0 80 800 600' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                        } else {
                            cmd = "ffplay -i -left 0 -top  100 -x 800 -y 530 -an   -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/202'";
                        }
                    }
                } else if (cam_type == clsDefines.CAM_FOORIR) {
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        if (clsDefines.omxplayer_found) {
                            cmd = "omxplayer --win '0 80 800 600' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                        } else {
                            cmd = "ffplay -i -left 0 -top  100 -x 800 -y 530 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";

                        }
                    }
                }else if (cam_type == clsDefines.CAM_VICON) {
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                         cmd = "omxplayer --win '0 80 800 600' --live -o hdmi --fps 30 --aspect-mode stretch 'rtsp://" 
            + user_name + ":" + pwd + camurl + ":554/stream2'";
                    System.out.println("THIS IS VICON CAMERA LIVE STREAMING COMMAND  "+cmd );
                    }
                } else {//other camera

                    cmd = "ffplay -i -left 0 -top  100 -x 800 -y 530 -an -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 '" + url + "'";
                }

            } else {
                if (clsSharedVariables.getLiveType() <= clsDefines.CAM_4) {
                    if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {

                      
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '400 80 800 340' --live --timeout 3 -o hdmi --fps 30 --aspect-mode stretch " + "'rtsp://" + user_name + ":" + pwd + "@"+ANALOG_IPADDR + ":554/cam/realmonitor?channel=2&subtype=1'";
                            } else {
                                cmd = "ffplay -i -left 400 -top 100 -x 400 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'http://" + user_name + ":" + pwd + camurl + "/cgi-bin/mjpg/video.cgi?[channel=2]&subtype=1'";
                            }
                        
                    } else if (cam_type == clsDefines.CAM_SPARSH) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                                                        cmd = "omxplayer --win '400 80 800 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " +  "'rtsp://" + user_name + ":" + pwd +   camurl + ":554/ch01.264?dev=1'";

                        }}else if (cam_type == clsDefines.CAM_HIKVISION) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            if (clsDefines.omxplayer_found) {

                                cmd = "omxplayer --win '400 80 800 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            } else {
                                cmd = "ffplay -i -left 400 -top 100 -x 400 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            }
                        } else {//analog
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '400 80 800 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            } else {
                                cmd = "ffplay -i -left 400 -top 100 -x 400 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            }
                        }
                    } else if (cam_type == clsDefines.CAM_FOORIR) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '400 80 800 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                            } else {
                                cmd = "ffplay -i -left 400 -top 100 -x 400 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                            }
                        }
                    }else if (cam_type == clsDefines.CAM_VICON) {
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        cmd = "omxplayer --win '400 80 800 340' --live -o hdmi --fps 30 --aspect-mode stretch 'rtsp://" 
            + user_name + ":" + pwd + camurl + ":554/stream2'";
                    System.out.println("THIS IS VICON CAMERA LIVE STREAMING COMMAND  "+cmd );
                    }
                } else {//other camera

                        cmd = "ffplay -i -left 400 -top 100 -x 400 -y 265 -an   -noborder  -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 '" + url + "'";

                    }
                } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_6) {
                    if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {

                   
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '266 80 532 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + "@"+ANALOG_IPADDR + ":554/cam/realmonitor?channel=2&subtype=1'";
                            } else {
                                cmd = "ffplay -i -left 266 -top 100 -x 266 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'http://" + user_name + ":" + pwd + camurl + "/cgi-bin/mjpg/video.cgi?[channel=2]&subtype=1'";
                            }
                        
                    }  if (cam_type == clsDefines.CAM_SPARSH) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            cmd = "omxplayer --win '266 80 532 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " +  "'rtsp://" + user_name + ":" + pwd +   camurl + ":554/ch01.264?dev=1'";

                        }
                    }else if (cam_type == clsDefines.CAM_HIKVISION) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            if (clsDefines.omxplayer_found) {

                                cmd = "omxplayer --win '266 80 532 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            } else {
                                cmd = "ffplay -i -left 266 -top 100 -x 266 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            }
                        } else {//analog
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '266 80 532 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            } else {
                                cmd = "ffplay -i -left 266 -top 100 -x 266 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            }
                        }
                    } else if (cam_type == clsDefines.CAM_FOORIR) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '266 80 532 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                            } else {
                                cmd = "ffplay -i -left 266 -top 100 -x 266 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                            }
                        }
                    }else if (cam_type == clsDefines.CAM_VICON) {
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                         cmd = "omxplayer --win '266 80 532 340' --live -o hdmi --fps 30 --aspect-mode stretch 'rtsp://" 
            + user_name + ":" + pwd + camurl + ":554/stream2'";
                    System.out.println("THIS IS VICON CAMERA LIVE STREAMING COMMAND  "+cmd );
                    }
                } else {//other camera

                        cmd = "ffplay -i -left 266 -top 100 -x 266 -y 265 -an   -noborder  -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 '" + url + "'";

                    }
                } else if (clsSharedVariables.getLiveType() <= clsDefines.CAM_8) {
                    if (cam_type == clsDefines.CAM_CPPLUS || cam_type == clsDefines.CAM_DAHUA) {

                       
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '200 80 400 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + "@"+ANALOG_IPADDR + ":554/cam/realmonitor?channel=2&subtype=1'";
                            } else {
                                cmd = "ffplay -i -left 200 -top  100 -x 200 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'http://" + user_name + ":" + pwd + camurl + "/cgi-bin/mjpg/video.cgi?[channel=2]&subtype=1'";
                            }
                        
                    } else  if (cam_type == clsDefines.CAM_SPARSH) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            cmd = "omxplayer --win '200 80 400 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " +  "'rtsp://" + user_name + ":" + pwd +   camurl + ":554/ch01.264?dev=1'";
                        }
                    }else if (cam_type == clsDefines.CAM_HIKVISION) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            if (clsDefines.omxplayer_found) {

                                cmd = "omxplayer --win '200 80 400 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            } else {
                                cmd = "ffplay -i -left 200 -top  100 -x 200 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            }
                        } else {//analog
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '200 80 400 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            } else {
                                cmd = "ffplay -i -left 200 -top  100 -x 200 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":554/Streaming/channels/102'";
                            }
                        }
                    } else if (cam_type == clsDefines.CAM_FOORIR) {

                        if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                            if (clsDefines.omxplayer_found) {
                                cmd = "omxplayer --win '200 80 400 340' --live --timeout 15 -o hdmi --threshold 0.3 --audio_fifo 0 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                            } else {
                                cmd = "ffplay -i -left 200 -top  100 -x 200 -y 265 -an  -noborder -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 " + "'rtsp://" + user_name + ":" + pwd + camurl + ":8554/camera'";
                            }
                        }
                    }else if (cam_type == clsDefines.CAM_VICON) {
                    if (clsSharedVariables.camera_type == clsDefines.IP_CAMERA) {
                        cmd = "omxplayer --win '200 80 400 340' --live -o hdmi --fps 30 --aspect-mode stretch 'rtsp://" 
            + user_name + ":" + pwd + camurl + ":554/stream2'";
                    //System.out.println("THIS IS VICON CAMERA LIVE STREAMING COMMAND  "+cmd );
                    }
                } else {//other camera

                        cmd = "ffplay -i -left 200 -top  100 -x 200 -y 265 -an   -noborder  -window_title \" \" -nostats -sync video -fflags discardcorrupt+fastseek+nobuffer -max_delay 1 '" + url + "'";

                    }
//System.out.println("the live cmdddd"+cmd);
                }
            }
            if (getVideoStarted() == false) {
                break;
            }
           

            rt = Runtime.getRuntime();
            try {
                if ((all_cameras == false) && (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) && (xdotool == true)) {
                    cam_view_proc = rt.exec(cmd);
                } else {
                    cam_view_proc = rt.exec(new String[]{"bash", "-c", cmd});
                }

                try {
                    Field f = cam_view_proc.getClass().getDeclaredField("pid");
                    f.setAccessible(true);
                    pid = (long) f.getLong(cam_view_proc);

                } catch (Exception ex) {

                }
                MainFrmIts.setPreVideoLiveState(MainFrmIts.getVideoLiveState());
                outCam1 = new OutStreamCam1Thread();
                outCam1.start();

                cam_view_proc.waitFor();
                if (MainFrmIts.getVideoLiveState() == 0 || MainFrmIts.getVideoLiveState() != MainFrmIts.getPreVideoLiveState()) {
                    if (objCamView2 != null) {
                        objCamView2.interrupt();
                        cam_view_proc.destroy();
                    }
                    break;
                }
                if (MainFrmIts.getVideoStarted() == false) {

                    break;
                }
                if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
                    break;
                }
            } catch (InterruptedException ex) {

            } catch (IOException ex) {

            } catch (Exception ex) {

            } finally {
                try {
                    outCam1.stop();
                    rt.freeMemory();
                    rt.gc();
                    cam_view_proc.getInputStream().close();
                    cam_view_proc.getOutputStream().close();
                    cam_view_proc.getErrorStream().close();
                    cam_view_proc.destroy();
                    cam_view_proc = null;
                    rt = null;

                } catch (Exception ex) {
                } finally {
                    cam_view_proc = null;
                    outCam1 = null;
                }

            }
            if (MainFrmIts.getVideoStarted() == false) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }

    class OutStreamCam1Thread extends Thread {

        BufferedReader reader = null;

        public OutStreamCam1Thread() {
            super();
        }

        @Override
        public void run() {
            String data;
            try {
                reader = new BufferedReader(new InputStreamReader(cam_view_proc.getErrorStream()));
                while (true) {
                    if (reader.ready()) {
                        if (MainFrmIts.getVideoLiveState() == 0 || MainFrmIts.getVideoLiveState() != MainFrmIts.getPreVideoLiveState()) {
                            if (objCamView2 != null) {
                                objCamView2.interrupt();
                                cam_view_proc.destroy();
                            }
                            break;
                        }
                        data = reader.readLine();

                        if (data.contains("have a nice day")) {
                            clsSharedVariables.setCam_status_live(clsDefines.CAM_DISCONNECTED);
                            clsSharedVariables.setCam2_status_live(clsDefines.CAM_DISCONNECTED);

                            try {
                                cam_view_proc.destroy();
                            } catch (Exception ex) {

                            }
                            break;
                        }
                        if (data.contains("route to host")) {
                            clsSharedVariables.setCam_status_live(clsDefines.CAM_DISCONNECTED);
                            clsSharedVariables.setCam2_status_live(clsDefines.CAM_DISCONNECTED);

                            try {
                                cam_view_proc.destroy();
                            } catch (Exception ex) {

                            }
                            break;
                        }
                       
                        if (MainFrmIts.getVideoStarted() == false) {
                            clsSharedVariables.setCam_status_live(clsDefines.CAM_DISCONNECTED);
                            clsSharedVariables.setCam2_status_live(clsDefines.CAM_DISCONNECTED);

                            try {
                                cam_view_proc.destroy();
                            } catch (Exception ex) {

                            }
                            break;
                        } else {
                            clsSharedVariables.setCam_status_live(clsDefines.CAM_CONNECTED);
                            clsSharedVariables.setCam2_status_live(clsDefines.CAM_CONNECTED);
                        }
                    }
                    if (MainFrmIts.getVideoStarted() == false) {
                        clsSharedVariables.setCam2_status_live(clsDefines.CAM_DISCONNECTED);
                        break;
                    }
                    Thread.sleep(1000);
                }

            } catch (Exception ex) {

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
            }
            try {
                try {

                    Process p = Runtime.getRuntime().exec("sudo killall -9 " + pid);
                    p.waitFor();

                } catch (Exception ex) {

                }
                try {
                    rt.freeMemory();
                    rt.gc();
                } catch (Exception ex) {

                }
                try {
                    cam_view_proc.getInputStream().close();
                    cam_view_proc.getOutputStream().close();
                    cam_view_proc.getErrorStream().close();
                } catch (Exception ex) {

                }
                cam_view_proc.destroyForcibly();
                cam_view_proc.exitValue();
                cam_view_proc = null;
                rt = null;

            } catch (Exception ex) {

            }

        }

    }

}
