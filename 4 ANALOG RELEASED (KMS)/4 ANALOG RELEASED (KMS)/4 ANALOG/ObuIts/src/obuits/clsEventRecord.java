package obuits;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static obuits.MainFrmIts.setVideoRecordStarted;
import static obuits.clsDefines.video_filepath;

public class clsEventRecord extends Thread {

    public clsEventRecord() {
        super();
    }

    // Method to concatenate pre-event video files for a specific camera
    private static void concat_preevent_video_files(byte camera_no, String out_file) {

        Runtime rt1 = null;
        Process p;

        // Calculate recording time
        int rec_time = clsSharedVariables.getEventPreRecordTime() + clsSharedVariables.getEventPostRecordTime();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        cal.add(Calendar.MINUTE, -rec_time);
        String datstr = sdf.format(cal.getTime());
        long now_time = Long.parseLong(datstr);
        long fileTime = 0;
        int cnt;
        int j;
        File cam_path;
        File[] files_list;
        String[] split_str;
        int no_files_inc = 0;
       // clsFtpUploadData objFtpUpload = new clsFtpUploadData();
        StringBuilder sb = new StringBuilder();

        try {
            // Construct path to the directory containing pre-event video files for the camera
            cam_path = new File(video_filepath.getAbsolutePath() + "/Videos_preevent/Cam" + camera_no + "/");
            if (cam_path.exists()) {
                files_list = cam_path.listFiles();
                if (files_list == null) {
                    return;
                }
                cnt = files_list.length;
                Arrays.sort(files_list);
                for (j = 0; j < cnt; j++) {
                    split_str = (files_list[j].getName()).split("[.]");
                    try {
                        fileTime = Long.parseLong(split_str[0]);
                    } catch (NumberFormatException ex) {

                    }
                    // Filter out files recorded after the calculated current time
                    if (fileTime > now_time) {
                        sb.append(files_list[j]);
                        sb.append("|");
                        no_files_inc++;
                    }
                }
                rt1 = Runtime.getRuntime();

                if (sb.toString().length() > 0) {
                    sb = sb.deleteCharAt(sb.lastIndexOf("|"));
                    // Execute ffmpeg command to concatenate selected video files into a single output file
                    String[] cmd = {"bash", "-c", "ffmpeg  -y -i \"concat:" + sb.toString() + "\" -c copy " + out_file};
                    p = rt1.exec(cmd);
                    p.waitFor();
                    // If FTP upload is enabled, add the output file to the FTP upload queue
//                    if (clsSharedVariables.getVideoDig1FtpUploadEnable() == true || clsSharedVariables.getVideoDig2FtpUploadEnable() == true || clsSharedVariables.getVideoDig3FtpUploadEnable() == true
//                            || clsSharedVariables.getVideoDig4FtpUploadEnable() == true) {
//                        objFtpUpload.addFtpPkts("*" + out_file);
//                        objFtpUpload.writeAllFtpPktsFile();
//                    }

                }
            }
        } catch (IOException | InterruptedException ex) {
        } finally {
            // Cleanup resources
            cam_path = null;
            files_list = null;
            rt1 = null;
            p = null;
            cal = null;
            sdf = null;
            datstr = null;
            split_str = null;
            sb = null;
        }
    }

    // Overridden method to define the behavior of the thread
    @Override
    public void run() {
        SimpleDateFormat sdfOutFileFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now_time;
        String OutFilename;
        int cam_no = 0;
        // Get the current time
        now_time = new Date(Calendar.getInstance().getTimeInMillis());
        try {
            // Check if event recording is enabled
            if (clsSharedVariables.getEventRecordEnabled() == true) {
                // Iterate through each camera
                for (cam_no = 0; cam_no < 8; cam_no++) {
                    switch (cam_no) {
                        // Construct output file name for each enabled camera and concatenate pre-event video files
                        case 0:
                            if (clsSharedVariables.getCam1Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam1/" + clsSharedVariables.getOBUID() + "_1" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 1:
                            if (clsSharedVariables.getCam2Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam2/" + clsSharedVariables.getOBUID() + "_2" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 2:
                            if (clsSharedVariables.getCam3Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam3/" + clsSharedVariables.getOBUID() + "_3" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 3:
                            if (clsSharedVariables.getCam4Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam4/" + clsSharedVariables.getOBUID() + "_4" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 4:
                            if (clsSharedVariables.getCam5Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam5/" + clsSharedVariables.getOBUID() + "_5" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 5:
                            if (clsSharedVariables.getCam6Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam6/" + clsSharedVariables.getOBUID() + "_6" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 6:
                            if (clsSharedVariables.getCam7Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam7/" + clsSharedVariables.getOBUID() + "_7" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                        case 7:
                            if (clsSharedVariables.getCam8Enabled()) {
                                OutFilename = video_filepath.getAbsolutePath() + "/Videos_event/Cam8/" + clsSharedVariables.getOBUID() + "_8" + sdfOutFileFormat.format(now_time) + ".avi ";
                                concat_preevent_video_files((byte) (cam_no + 1), OutFilename);
                            }
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            // Exception handling
        } finally {
            // Cleanup tasks and resource releases
            clsSharedVariables.setEventBasedRecordingStart(false);
            setVideoRecordStarted(false);
            if (clsSharedVariables.getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                MainFrmIts.imgSos.setVisible(false);
            } else if (clsSharedVariables.getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                MainFrmIts.imgDigI2.setVisible(false);
            } else if (clsSharedVariables.getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                MainFrmIts.imgDigI3.setVisible(false);
            } else if (clsSharedVariables.getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
                MainFrmIts.imgDigI4.setVisible(false);
            }
            sdfOutFileFormat = null;
            now_time = null;
            OutFilename = null;
        }
    }
}
