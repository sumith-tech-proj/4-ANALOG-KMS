package obuits;

import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_SUMITH;
import static obuits.clsDefines.can_filepath;
import static obuits.clsDefines.log_filepath;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.main_video_filepath;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDefines.video_filepath;
import static obuits.clsSharedVariables.getOnvifSupported;
import static obuits.clsSharedVariables.setOnvifSupported;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import static obuits.clsDefines.DISABLED;
import static obuits.clsDefines.ENABLED;
import static obuits.clsDefines.VIDEO_RECORD_CONTINUOUS;
import static obuits.clsDefines.VIDEO_RECORD_MANUAL;
import static obuits.clsSharedVariables.setCam1Enabled;
import static obuits.clsSharedVariables.setCam2Enabled;
import static obuits.clsSharedVariables.setCam3Enabled;
import static obuits.clsSharedVariables.setCam4Enabled;
import static obuits.clsSharedVariables.setCam5Enabled;
import static obuits.clsSharedVariables.setCam6Enabled;
import static obuits.clsSharedVariables.setCam7Enabled;
import static obuits.clsSharedVariables.setCam8Enabled;
import static obuits.clsSharedVariables.setVideo1Connected;
import static obuits.clsSharedVariables.setVideo2Connected;
import static obuits.clsSharedVariables.setVideo3Connected;
import static obuits.clsSharedVariables.setVideo4Connected;
import static obuits.clsSharedVariables.getBitratetype;
import static obuits.clsSharedVariables.getBitratetype2;
import static obuits.clsSharedVariables.getBitratetype3;
import static obuits.clsSharedVariables.getBitratetype4;
import static obuits.clsSharedVariables.getFramerate;
import static obuits.clsSharedVariables.getFramerate2;
import static obuits.clsSharedVariables.getFramerate3;
import static obuits.clsSharedVariables.getFramerate4;
import static obuits.clsSharedVariables.getResolution;
import static obuits.clsSharedVariables.getResolution2;
import static obuits.clsSharedVariables.getResolution3;
import static obuits.clsSharedVariables.getResolution4;
import static obuits.clsSharedVariables.getSubBitratetype;
import static obuits.clsSharedVariables.getSubBitratetype2;
import static obuits.clsSharedVariables.getSubBitratetype4;
import static obuits.clsSharedVariables.getSubFramerate;
import static obuits.clsSharedVariables.getSubFramerate2;
import static obuits.clsSharedVariables.getSubFramerate3;
import static obuits.clsSharedVariables.getSubFramerate4;
import static obuits.clsSharedVariables.getSubVideoEncode;
import static obuits.clsSharedVariables.getSubVideoEncode2;
import static obuits.clsSharedVariables.getSubVideoEncode3;
import static obuits.clsSharedVariables.getSubVideoEncode4;
import static obuits.clsSharedVariables.getSubVideomode;
import static obuits.clsSharedVariables.getSubVideomode2;
import static obuits.clsSharedVariables.getSubVideomode3;
import static obuits.clsSharedVariables.getSubVideomode4;
import static obuits.clsSharedVariables.getSubVideoquality;
import static obuits.clsSharedVariables.getSubVideoquality2;
import static obuits.clsSharedVariables.getSubVideoquality3;
import static obuits.clsSharedVariables.getSubVideoquality4;
import static obuits.clsSharedVariables.getVideoEncode;
import static obuits.clsSharedVariables.getVideoEncode2;
import static obuits.clsSharedVariables.getVideoEncode3;
import static obuits.clsSharedVariables.getVideoEncode4;
import static obuits.clsSharedVariables.getVideomode;
import static obuits.clsSharedVariables.getVideomode2;
import static obuits.clsSharedVariables.getVideomode3;
import static obuits.clsSharedVariables.getVideomode4;
import static obuits.clsSharedVariables.getVideoquality;
import static obuits.clsSharedVariables.getVideoquality2;
import static obuits.clsSharedVariables.getVideoquality3;
import static obuits.clsSharedVariables.getVideoquality4;
import static obuits.clsSharedVariables.setBitratetype;
import static obuits.clsSharedVariables.setBitratetype2;
import static obuits.clsSharedVariables.setBitratetype3;
import static obuits.clsSharedVariables.setBitratetype4;
import static obuits.clsSharedVariables.setBitratetype5;
import static obuits.clsSharedVariables.setBitratetype6;
import static obuits.clsSharedVariables.setBitratetype7;
import static obuits.clsSharedVariables.setBitratetype8;
import static obuits.clsSharedVariables.setFramerate;
import static obuits.clsSharedVariables.setFramerate2;
import static obuits.clsSharedVariables.setFramerate3;
import static obuits.clsSharedVariables.setFramerate4;
import static obuits.clsSharedVariables.setFramerate5;
import static obuits.clsSharedVariables.setFramerate6;
import static obuits.clsSharedVariables.setFramerate7;
import static obuits.clsSharedVariables.setFramerate8;
import static obuits.clsSharedVariables.setResolution;
import static obuits.clsSharedVariables.setResolution2;
import static obuits.clsSharedVariables.setResolution3;
import static obuits.clsSharedVariables.setResolution4;
import static obuits.clsSharedVariables.setResolution5;
import static obuits.clsSharedVariables.setResolution6;
import static obuits.clsSharedVariables.setResolution7;
import static obuits.clsSharedVariables.setResolution8;
import static obuits.clsSharedVariables.setSelectCamera;
import static obuits.clsSharedVariables.setSelectCamera2;
import static obuits.clsSharedVariables.setSelectCamera3;
import static obuits.clsSharedVariables.setSelectCamera4;
import static obuits.clsSharedVariables.setSelectCamera7;
import static obuits.clsSharedVariables.setSelectCamera8;
import static obuits.clsSharedVariables.setSubBitratetype;
import static obuits.clsSharedVariables.setSubBitratetype2;
import static obuits.clsSharedVariables.setSubBitratetype3;
import static obuits.clsSharedVariables.setSubBitratetype4;
import static obuits.clsSharedVariables.setSubBitratetype5;
import static obuits.clsSharedVariables.setSubBitratetype6;
import static obuits.clsSharedVariables.setSubBitratetype7;
import static obuits.clsSharedVariables.setSubBitratetype8;
import static obuits.clsSharedVariables.setSubFramerate;
import static obuits.clsSharedVariables.setSubFramerate2;
import static obuits.clsSharedVariables.setSubFramerate3;
import static obuits.clsSharedVariables.setSubFramerate4;
import static obuits.clsSharedVariables.setSubFramerate6;
import static obuits.clsSharedVariables.setSubFramerate7;
import static obuits.clsSharedVariables.setSubFramerate8;
import static obuits.clsSharedVariables.setSubVideoEncode;
import static obuits.clsSharedVariables.setSubVideoEncode2;
import static obuits.clsSharedVariables.setSubVideoEncode3;
import static obuits.clsSharedVariables.setSubVideoEncode4;
import static obuits.clsSharedVariables.setSubVideoEncode5;
import static obuits.clsSharedVariables.setSubVideoEncode6;
import static obuits.clsSharedVariables.setSubVideoEncode7;
import static obuits.clsSharedVariables.setSubVideoEncode8;
import static obuits.clsSharedVariables.setSubVideomode;
import static obuits.clsSharedVariables.setSubVideomode2;
import static obuits.clsSharedVariables.setSubVideomode3;
import static obuits.clsSharedVariables.setSubVideomode4;
import static obuits.clsSharedVariables.setSubVideomode5;
import static obuits.clsSharedVariables.setSubVideomode6;
import static obuits.clsSharedVariables.setSubVideomode7;
import static obuits.clsSharedVariables.setSubVideomode8;
import static obuits.clsSharedVariables.setSubVideoquality;
import static obuits.clsSharedVariables.setSubVideoquality2;
import static obuits.clsSharedVariables.setSubVideoquality3;
import static obuits.clsSharedVariables.setSubVideoquality4;
import static obuits.clsSharedVariables.setSubVideoquality5;
import static obuits.clsSharedVariables.setSubVideoquality6;
import static obuits.clsSharedVariables.setSubVideoquality7;
import static obuits.clsSharedVariables.setSubVideoquality8;
import static obuits.clsSharedVariables.setSubstreamResolution;
import static obuits.clsSharedVariables.setSubstreamResolution2;
import static obuits.clsSharedVariables.setSubstreamResolution3;
import static obuits.clsSharedVariables.setSubstreamResolution4;
import static obuits.clsSharedVariables.setSubstreamResolution5;
import static obuits.clsSharedVariables.setSubstreamResolution6;
import static obuits.clsSharedVariables.setSubstreamResolution7;
import static obuits.clsSharedVariables.setSubstreamResolution8;
import static obuits.clsSharedVariables.setVideoEncode;
import static obuits.clsSharedVariables.setVideoEncode2;
import static obuits.clsSharedVariables.setVideoEncode3;
import static obuits.clsSharedVariables.setVideoEncode4;
import static obuits.clsSharedVariables.setVideoEncode5;
import static obuits.clsSharedVariables.setVideoEncode6;
import static obuits.clsSharedVariables.setVideoEncode7;
import static obuits.clsSharedVariables.setVideoEncode8;
import static obuits.clsSharedVariables.setVideomode;
import static obuits.clsSharedVariables.setVideomode2;
import static obuits.clsSharedVariables.setVideomode3;
import static obuits.clsSharedVariables.setVideomode4;
import static obuits.clsSharedVariables.setVideomode5;
import static obuits.clsSharedVariables.setVideomode6;
import static obuits.clsSharedVariables.setVideomode7;
import static obuits.clsSharedVariables.setVideomode8;
import static obuits.clsSharedVariables.setVideoquality;
import static obuits.clsSharedVariables.setVideoquality2;
import static obuits.clsSharedVariables.setVideoquality3;
import static obuits.clsSharedVariables.setVideoquality4;
import static obuits.clsSharedVariables.setVideoquality5;
import static obuits.clsSharedVariables.setVideoquality6;
import static obuits.clsSharedVariables.setVideoquality7;
import static obuits.clsSharedVariables.setVideoquality8;
import static obuits.clsSharedVariables.getBrightnessLevel;
import static obuits.clsSharedVariables.getBrightnessLevelCam2;
import static obuits.clsSharedVariables.getBrightnessLevelCam3;
import static obuits.clsSharedVariables.getBrightnessLevelCam4;
import static obuits.clsSharedVariables.getBrightnessLevelCam5;
import static obuits.clsSharedVariables.getBrightnessLevelCam6;
import static obuits.clsSharedVariables.getBrightnessLevelCam7;
import static obuits.clsSharedVariables.getBrightnessLevelCam8;
import static obuits.clsSharedVariables.getCam1RecordSelected;
import static obuits.clsSharedVariables.getCam2RecordSelected;
import static obuits.clsSharedVariables.getCam3RecordSelected;
import static obuits.clsSharedVariables.getCam4RecordSelected;
import static obuits.clsSharedVariables.getCam5RecordSelected;
import static obuits.clsSharedVariables.getCam6RecordSelected;
import static obuits.clsSharedVariables.getCam7RecordSelected;
import static obuits.clsSharedVariables.getCam8RecordSelected;
import static obuits.clsSharedVariables.getContrastLevel;
import static obuits.clsSharedVariables.getContrastLevelCam2;
import static obuits.clsSharedVariables.getContrastLevelCam3;
import static obuits.clsSharedVariables.getContrastLevelCam4;
import static obuits.clsSharedVariables.getContrastLevelCam5;
import static obuits.clsSharedVariables.getContrastLevelCam6;
import static obuits.clsSharedVariables.getContrastLevelCam7;
import static obuits.clsSharedVariables.getContrastLevelCam8;
import static obuits.clsSharedVariables.getRecordFromTime;
import static obuits.clsSharedVariables.getRecordToTime;
import static obuits.clsSharedVariables.getRecordType;
import static obuits.clsSharedVariables.getSaturationLevel;
import static obuits.clsSharedVariables.getSaturationLevelCam2;
import static obuits.clsSharedVariables.getSaturationLevelCam3;
import static obuits.clsSharedVariables.getSaturationLevelCam4;
import static obuits.clsSharedVariables.getSaturationLevelCam5;
import static obuits.clsSharedVariables.getSaturationLevelCam6;
import static obuits.clsSharedVariables.getSaturationLevelCam7;
import static obuits.clsSharedVariables.getSaturationLevelCam8;
import static obuits.clsSharedVariables.setBrightnessLevel;
import static obuits.clsSharedVariables.setBrightnessLevelCam2;
import static obuits.clsSharedVariables.setBrightnessLevelCam3;
import static obuits.clsSharedVariables.setBrightnessLevelCam4;
import static obuits.clsSharedVariables.setBrightnessLevelCam5;
import static obuits.clsSharedVariables.setBrightnessLevelCam6;
import static obuits.clsSharedVariables.setBrightnessLevelCam7;
import static obuits.clsSharedVariables.setBrightnessLevelCam8;
import static obuits.clsSharedVariables.setCam1RecordSelected;
import static obuits.clsSharedVariables.setCam2RecordSelected;
import static obuits.clsSharedVariables.setCam3RecordSelected;
import static obuits.clsSharedVariables.setCam4RecordSelected;
import static obuits.clsSharedVariables.setCam5RecordSelected;
import static obuits.clsSharedVariables.setCam6RecordSelected;
import static obuits.clsSharedVariables.setCam7RecordSelected;
import static obuits.clsSharedVariables.setCam8RecordSelected;
import static obuits.clsSharedVariables.setContrastLevel;
import static obuits.clsSharedVariables.setContrastLevelCam2;
import static obuits.clsSharedVariables.setContrastLevelCam3;
import static obuits.clsSharedVariables.setContrastLevelCam4;
import static obuits.clsSharedVariables.setContrastLevelCam5;
import static obuits.clsSharedVariables.setContrastLevelCam6;
import static obuits.clsSharedVariables.setContrastLevelCam7;
import static obuits.clsSharedVariables.setContrastLevelCam8;
import static obuits.clsSharedVariables.setRecordFromTime;
import static obuits.clsSharedVariables.setRecordToTime;
import static obuits.clsSharedVariables.setRecordType;
import static obuits.clsSharedVariables.setSaturationLevel;
import static obuits.clsSharedVariables.setSaturationLevelCam2;
import static obuits.clsSharedVariables.setSaturationLevelCam3;
import static obuits.clsSharedVariables.setSaturationLevelCam4;
import static obuits.clsSharedVariables.setSaturationLevelCam5;
import static obuits.clsSharedVariables.setSaturationLevelCam6;
import static obuits.clsSharedVariables.setSaturationLevelCam7;
import static obuits.clsSharedVariables.setSaturationLevelCam8;
import static obuits.clsSharedVariables.getCameraIdentifer1;
import static obuits.clsSharedVariables.getCameraIdentifer2;
import static obuits.clsSharedVariables.getCameraIdentifer3;
import static obuits.clsSharedVariables.getCameraIdentifer4;
import static obuits.clsSharedVariables.getCameraIdentifer5;
import static obuits.clsSharedVariables.getCameraIdentifer6;
import static obuits.clsSharedVariables.getCameraIdentifer7;
import static obuits.clsSharedVariables.getCameraIdentifer8;
import static obuits.clsSharedVariables.getCameraName1;
import static obuits.clsSharedVariables.getCameraName2;
import static obuits.clsSharedVariables.getCameraName3;
import static obuits.clsSharedVariables.getCameraName4;
import static obuits.clsSharedVariables.getCameraName5;
import static obuits.clsSharedVariables.getCameraName6;
import static obuits.clsSharedVariables.getCameraName7;
import static obuits.clsSharedVariables.getCameraName8;
import static obuits.clsSharedVariables.setCamSpeedEnable1;
import static obuits.clsSharedVariables.setCameraIdentifer1;

public class PanDeleteDefaultFiles extends javax.swing.JPanel {

    String prev_control_name;

    public PanDeleteDefaultFiles() {
        initComponents();
        lblMsgCamera.setText("");
        lblMsgSnapshot.setText("");
        lblMsgEncode.setText("");
        lblMsgBrightness.setText("");
        lblMsgRecord1.setText("");
        lblMsgRecord.setText("");
        lblMsgEncode.setText("");
        lblMsgMirror.setText("");
        lblMsgMotionDetect.setText("");
        lblMsgWatermark.setText("");
        lblMsg.setText(" ");
        lblMsg1.setText(" ");
        lblMsg2.setText(" ");
        lblMsg3.setText(" ");
        lblMsg4.setText(" ");

        Date date;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        try {
            this.txtSerialNo.setText(clsSharedVariables.serial_no);
            chkPedEnable.setVisible(false);
            chkDms.setVisible(false);
            this.lblMsg.setText("");
            spinVidRecStorage1.setValue(clsSharedVariables.getRecExpiryDays());
            spinEventRecStorage.setValue(clsSharedVariables.getRecExpiryDaysEventBased());
            spinSnapStorage.setValue(clsSharedVariables.getSnapDelNoDays());
            this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
            this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
            this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
            this.chkCam4Enable.setSelected(clsSharedVariables.getCam4Enabled());
//            this.chkCam5Enable.setSelected(clsSharedVariables.getCam5Enabled());
//            this.chkCam6Enable.setSelected(clsSharedVariables.getCam6Enabled());
//            this.chkCam7Enable.setSelected(clsSharedVariables.getCam7Enabled());
//            this.chkCam8Enable.setSelected(clsSharedVariables.getCam8Enabled());
            if (clsSharedVariables.getLiveType() == clsDefines.CAM_1) {
                cmbSelectCameraNo.setSelectedIndex(0);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(false);
                this.panCam3.setVisible(false);
                this.panCam4.setVisible(false);
//                this.panCam5.setVisible(false);
//                this.panCam6.setVisible(false);
//                this.panCam7.setVisible(false);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(false);
                this.chkCam3Enable.setSelected(false);
                this.chkCam4Enable.setSelected(false);
//                this.chkCam5Enable.setSelected(false);
//                this.chkCam6Enable.setSelected(false);
//                this.chkCam7Enable.setSelected(false);
//                this.chkCam8Enable.setSelected(false);

            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_2) {
                cmbSelectCameraNo.setSelectedIndex(1);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(false);
                this.panCam4.setVisible(false);
//                this.panCam5.setVisible(false);
//                this.panCam6.setVisible(false);
//                this.panCam7.setVisible(false);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(false);
                this.chkCam4Enable.setSelected(false);
//                this.chkCam5Enable.setSelected(false);
//                this.chkCam6Enable.setSelected(false);
//                this.chkCam7Enable.setSelected(false);
//                this.chkCam8Enable.setSelected(false);

            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_3) {
                cmbSelectCameraNo.setSelectedIndex(2);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(true);
                this.panCam4.setVisible(false);
//                this.panCam5.setVisible(false);
//                this.panCam6.setVisible(false);
//                this.panCam7.setVisible(false);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
                this.chkCam4Enable.setSelected(false);
//                this.chkCam5Enable.setSelected(false);
//                this.chkCam6Enable.setSelected(false);
//                this.chkCam7Enable.setSelected(false);
//                this.chkCam8Enable.setSelected(false);
            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_4) {
                cmbSelectCameraNo.setSelectedIndex(3);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(true);
                this.panCam4.setVisible(true);
//                this.panCam5.setVisible(false);
//                this.panCam6.setVisible(false);
//                this.panCam7.setVisible(false);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
                this.chkCam4Enable.setSelected(clsSharedVariables.getCam4Enabled());
//                this.chkCam5Enable.setSelected(false);
//                this.chkCam6Enable.setSelected(false);
//                this.chkCam7Enable.setSelected(false);
//                this.chkCam8Enable.setSelected(false);
            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_5) {
                cmbSelectCameraNo.setSelectedIndex(4);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(true);
                this.panCam4.setVisible(true);
//                this.panCam5.setVisible(true);
//                this.panCam6.setVisible(false);
//                this.panCam7.setVisible(false);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
                this.chkCam4Enable.setSelected(clsSharedVariables.getCam4Enabled());
//                this.chkCam5Enable.setSelected(clsSharedVariables.getCam5Enabled());
//                this.chkCam6Enable.setSelected(false);
//                this.chkCam7Enable.setSelected(false);
//                this.chkCam8Enable.setSelected(false);
            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_6) {
                cmbSelectCameraNo.setSelectedIndex(5);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(true);
                this.panCam4.setVisible(true);
//                this.panCam5.setVisible(true);
//                this.panCam6.setVisible(true);
//                this.panCam7.setVisible(false);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
                this.chkCam4Enable.setSelected(clsSharedVariables.getCam4Enabled());
//                this.chkCam5Enable.setSelected(clsSharedVariables.getCam5Enabled());
//                this.chkCam6Enable.setSelected(clsSharedVariables.getCam6Enabled());
//                this.chkCam7Enable.setSelected(false);
//                this.chkCam8Enable.setSelected(false);
            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_7) {
                cmbSelectCameraNo.setSelectedIndex(6);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(true);
                this.panCam4.setVisible(true);
//                this.panCam5.setVisible(true);
//                this.panCam6.setVisible(true);
//                this.panCam7.setVisible(true);
//                this.panCam8.setVisible(false);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
                this.chkCam4Enable.setSelected(clsSharedVariables.getCam4Enabled());
//                this.chkCam5Enable.setSelected(clsSharedVariables.getCam5Enabled());
//                this.chkCam6Enable.setSelected(clsSharedVariables.getCam6Enabled());
//                this.chkCam7Enable.setSelected(clsSharedVariables.getCam7Enabled());
//                this.chkCam8Enable.setSelected(false);
            } else if (clsSharedVariables.getLiveType() == clsDefines.CAM_8) {
                cmbSelectCameraNo.setSelectedIndex(7);
                this.panCam1.setVisible(true);
                this.panCam2.setVisible(true);
                this.panCam3.setVisible(true);
                this.panCam4.setVisible(true);
//                this.panCam5.setVisible(true);
//                this.panCam6.setVisible(true);
//                this.panCam7.setVisible(true);
//                this.panCam8.setVisible(true);
                this.chkCam1Enable.setSelected(clsSharedVariables.getCam1Enabled());
                this.chkCam2Enable.setSelected(clsSharedVariables.getCam2Enabled());
                this.chkCam3Enable.setSelected(clsSharedVariables.getCam3Enabled());
                this.chkCam4Enable.setSelected(clsSharedVariables.getCam4Enabled());
//                this.chkCam5Enable.setSelected(clsSharedVariables.getCam5Enabled());
//                this.chkCam6Enable.setSelected(clsSharedVariables.getCam6Enabled());
//                this.chkCam7Enable.setSelected(clsSharedVariables.getCam7Enabled());
//                this.chkCam8Enable.setSelected(clsSharedVariables.getCam8Enabled());
            }

        } catch (Exception ex) {

        }
        try {
            if ((clsSharedVariables.getCam1Enabled()) == true) {
                pan1.setVisible(true);
                cmbCam1Type.setSelectedIndex(clsSharedVariables.getCam1Type());
                txtCam1User.setText(clsSharedVariables.getCam1UserName());
                this.PasswordCam1.setText(clsSharedVariables.getCam1Pwd());
            } else {
                pan1.setVisible(false);
            }

            if ((clsSharedVariables.getCam2Enabled()) == true) {
                pan2.setVisible(true);
                cmbCam2Type.setSelectedIndex(clsSharedVariables.getCam2Type());
                txtCam2User.setText(clsSharedVariables.getCam2UserName());
                this.PasswordCam2.setText(clsSharedVariables.getCam2Pwd());
            } else {
                pan2.setVisible(false);
            }
            if ((clsSharedVariables.getCam3Enabled()) == true) {
                pan3.setVisible(true);
                cmbCam3Type.setSelectedIndex(clsSharedVariables.getCam3Type());
                txtCam3User.setText(clsSharedVariables.getCam3UserName());
                this.PasswordCam3.setText(clsSharedVariables.getCam3Pwd());
            } else {
                pan3.setVisible(false);
            }

            if ((clsSharedVariables.getCam4Enabled()) == true) {
                pan4.setVisible(true);
                cmbCam4Type.setSelectedIndex(clsSharedVariables.getCam4Type());
                txtCam4User.setText(clsSharedVariables.getCam4UserName());
                this.PasswordCam4.setText(clsSharedVariables.getCam4Pwd());
            } else {
                pan4.setVisible(false);
            }
            
            

            
        } catch (Exception ex) {

        }
        //mirror
        if (clsSharedVariables.cam1MirrorEnable == true) {
            this.radMirrorOn.setSelected(true);
        } else {
            this.radMirrorOn.setSelected(false);
        }
        try {
            //video encoding
            cmbVideomode.setVisible(false);
            lblvideoaudio.setVisible(false);

            this.cmbSelectCameraEncode.setSelectedIndex(0);
            if (getResolution() > 0 && getResolution() < 6) {
                this.cmbResolution.setSelectedIndex(getResolution() - 1);
            }

            cmbVideomode.setSelectedItem(getVideomode());
            cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality()));
            cmbFramerate.setSelectedItem((String.valueOf(getFramerate())));
            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate()));
            cmbVidEncoding.setSelectedItem(getVideoEncode());
            cmbBitrateType.setSelectedItem(getBitratetype());
            sliderBrightness.setValue(getBrightnessLevel());
            sliderContrastLevel1.setValue(getContrastLevel());
            sliderSaturationLevel1.setValue(getSaturationLevel());

            lblBright.setText(String.valueOf(sliderBrightness.getValue()));
            lblContrast.setText(String.valueOf(sliderContrastLevel1.getValue()));
            lblSaturation.setText(String.valueOf(sliderSaturationLevel1.getValue()));
        } catch (Exception ex) {

        }
        //VIDEO RECORD
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        Calendar cal1 = Calendar.getInstance();
        try {
            cal1.set(Calendar.HOUR_OF_DAY, 0);
            cal1.set(Calendar.MINUTE, 0);
            Date startTime = cal.getTime();
            cal1.set(Calendar.HOUR, 23);
            cal1.set(Calendar.MINUTE, 59);
            Date endTime = cal1.getTime();

            SpinnerDateModel model = new SpinnerDateModel(startTime, null, endTime, Calendar.MINUTE);

            spinStartDate.setModel(model);
            spinStartDate.setEditor(new JSpinner.DateEditor(spinStartDate, "HH:mm"));
            spinStartDate.setValue(startTime);

            SpinnerDateModel Endmodel = new SpinnerDateModel(endTime, null, endTime, Calendar.MINUTE);

            spinEndDate.setModel(Endmodel);
            spinEndDate.setEditor(new JSpinner.DateEditor(spinEndDate, "HH:mm"));

            spinEndDate.setValue(endTime);

        } catch (Exception ex) {

        }
        if (getRecordType() == VIDEO_RECORD_CONTINUOUS) {
            this.radContRecord.setSelected(true);
            this.panManRecord.setVisible(false);
        } else if (getRecordType() == VIDEO_RECORD_MANUAL) {
            this.radContRecord.setSelected(false);
            this.radManRecord.setSelected(true);
            this.panManRecord.setVisible(true);
            try {
                this.spinStartDate.setValue(sdf1.parse(getRecordFromTime()));
            } catch (ParseException ex) {
            }
            try {
                this.spinEndDate.setValue(sdf1.parse(getRecordToTime()));
            } catch (ParseException ex) {

            }
            if (clsSharedVariables.getSchRecSunday() == ENABLED) {
                chkSunday.setSelected(true);
            } else {
                chkSunday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecMonday() == ENABLED) {
                chkMonday.setSelected(true);
            } else {
                chkMonday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecTuesday() == ENABLED) {
                chkTuesday.setSelected(true);
            } else {
                chkTuesday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecWednesday() == ENABLED) {
                chkWednesday.setSelected(true);
            } else {
                chkWednesday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecThursday() == ENABLED) {
                chkThursday.setSelected(true);
            } else {
                chkThursday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecFriday() == ENABLED) {
                chkFriday.setSelected(true);
            } else {
                chkFriday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecSaturday() == ENABLED) {
                chkSaturday.setSelected(true);
            } else {
                chkSaturday.setSelected(false);
            }
        }
        this.chkCam1Rec.setSelected(getCam1RecordSelected());
        this.chkCam2Rec.setSelected(getCam2RecordSelected());
        this.chkCam3Rec.setSelected(getCam3RecordSelected());
        this.chkCam4Rec.setSelected(getCam4RecordSelected());
//        this.chkCam5Rec.setSelected(getCam5RecordSelected());
//        this.chkCam6Rec.setSelected(getCam6RecordSelected());
//        this.chkCam7Rec.setSelected(getCam7RecordSelected());
//        this.chkCam8Rec.setSelected(getCam8RecordSelected());
        this.chkCam1Audio.setSelected(clsSharedVariables.getCam1RecordAudioSelected());
        this.chkCam2Audio.setSelected(clsSharedVariables.getCam2RecordAudioSelected());
        this.chkCam3Audio.setSelected(clsSharedVariables.getCam3RecordAudioSelected());
        this.chkCam4Audio.setSelected(clsSharedVariables.getCam4RecordAudioSelected());
//        this.chkCam5Audio.setSelected(clsSharedVariables.getCam5RecordAudioSelected());
//        this.chkCam6Audio.setSelected(clsSharedVariables.getCam6RecordAudioSelected());
//        this.chkCam7Audio.setSelected(clsSharedVariables.getCam7RecordAudioSelected());
//        this.chkCam8Audio.setSelected(clsSharedVariables.getCam8RecordAudioSelected());
        
        if (clsSharedVariables.getEventRecordEnabled() == true) {
            this.chkEventRecord.setSelected(true);
            this.SpinEventRecTime.setVisible(true);
            cmbVidStreamTypeEvent.setVisible(true);
            this.SpinEventRecTime.setValue(clsSharedVariables.getEventPostRecordTime());
            this.SpinEventPreRecTime.setValue(clsSharedVariables.getEventPreRecordTime());
            this.cmbVidStreamTypeEvent.setSelectedIndex(clsSharedVariables.getEventStreamType());
            SpinEventPreRecTime.setVisible(true);
        } else {
            this.chkEventRecord.setSelected(false);
            SpinEventRecTime.setVisible(false);
            cmbVidStreamTypeEvent.setVisible(false);
            this.SpinEventRecTime.setValue(1);
            this.cmbVidStreamTypeEvent.setSelectedIndex(1);
            SpinEventPreRecTime.setVisible(false);
        }

        if (clsSharedVariables.getCanRecordEnabled() == true) {
            this.chkCanRecord.setSelected(true);
            this.SpinCanRecTime.setVisible(true);
            this.cmbVidStreamTypeCan.setVisible(true);
            SpinCanPreRecTime.setVisible(true);
            this.SpinCanRecTime.setValue(clsSharedVariables.getCanPostRecordTime());
            this.SpinCanPreRecTime.setValue(clsSharedVariables.getCanPreRecordTime());
            this.cmbVidStreamTypeCan.setSelectedIndex(clsSharedVariables.getCanStreamType());
        } else {
            this.chkCanRecord.setSelected(false);
            this.SpinCanRecTime.setVisible(false);
            this.cmbVidStreamTypeCan.setVisible(false);
            this.SpinCanRecTime.setValue(1);
            this.cmbVidStreamTypeCan.setSelectedIndex(1);
            SpinCanPreRecTime.setVisible(false);
        }
        panSnap.setVisible(true);
        radCam1.setSelected(true);

        if (clsSharedVariables.getSnapShotEnableCam1()) {
            chkSnapShotEnable.setSelected(true);
            if (clsSharedVariables.getSnapShotDig1EnableCam1()) {
                chkSnapDig1.setSelected(true);
                if (clsSharedVariables.getSnapShotDig1StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig1.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig1.setSelectedIndex(1);
                }

                this.spinSnapEventntervalDig1.setValue(clsSharedVariables.getSnapShotDig1IntervalCam1());
                if (clsSharedVariables.getSnapShotDig1FtpUploadEnableCam1()) {
                    chkSnapDi1Upload.setEnabled(true);
                } else {
                    chkSnapDi1Upload.setEnabled(false);
                }
            } else {
                chkSnapDig1.setSelected(false);
                cmbSnapEventStreamDig1.setEnabled(false);
                spinSnapEventntervalDig1.setEnabled(false);
                chkSnapDi1Upload.setEnabled(false);
            }
            if (clsSharedVariables.getSnapShotDig2EnableCam1()) {
                chkSnapDig2.setSelected(true);
                if (clsSharedVariables.getSnapShotDig2StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig2.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig2.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig2.setValue(clsSharedVariables.getSnapShotDig2IntervalCam1());
                if (clsSharedVariables.getSnapShotDig2FtpUploadEnableCam1()) {
                    chkSnapDi2Upload.setEnabled(true);
                } else {
                    chkSnapDi2Upload.setEnabled(false);
                }
            } else {
                chkSnapDig2.setSelected(false);
                cmbSnapEventStreamDig2.setEnabled(false);
                spinSnapEventntervalDig2.setEnabled(false);
                chkSnapDi2Upload.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig3EnableCam1()) {
                chkSnapDig3.setSelected(true);
                if (clsSharedVariables.getSnapShotDig3StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig3.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig3.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig3.setValue(clsSharedVariables.getSnapShotDig3IntervalCam1());
                if (clsSharedVariables.getSnapShotDig3FtpUploadEnableCam1()) {
                    chkSnapDi3Upload.setEnabled(true);
                } else {
                    chkSnapDi3Upload.setEnabled(false);
                }
            } else {
                chkSnapDig3.setSelected(false);
                cmbSnapEventStreamDig3.setEnabled(false);
                spinSnapEventntervalDig3.setEnabled(false);
                chkSnapDi3Upload.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig4EnableCam1()) {
                chkSnapDig4.setSelected(true);
                if (clsSharedVariables.getSnapShotDig4StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig4.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig4.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig4.setValue(clsSharedVariables.getSnapShotDig4IntervalCam1());
                if (clsSharedVariables.getSnapShotDig4FtpUploadEnableCam1()) {
                    chkSnapDi4Upload.setEnabled(true);
                } else {
                    chkSnapDi4Upload.setEnabled(false);
                }
            } else {
                chkSnapDig4.setSelected(false);
                cmbSnapEventStreamDig4.setEnabled(false);
                spinSnapEventntervalDig4.setEnabled(false);
                chkSnapDi4Upload.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotContEnableCam1() == true) {
                chkSnapCont.setSelected(true);
                chkSnapContUpload.setSelected(true);
                if (clsSharedVariables.getSnapShotContStreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapContStreamType.setSelectedIndex(0);
                } else {
                    cmbSnapContStreamType.setSelectedIndex(1);
                }
                spinSnapContInterval.setValue(clsSharedVariables.getSnapShotContIntervalCam1());
            } else {
                chkSnapCont.setSelected(false);
                chkSnapContUpload.setSelected(false);
            }
        } else {
            chkSnapShotEnable.setSelected(false);
        }
        clsSharedVariables.setCameraIdentifer1(true);
        clsSharedVariables.setCameraIdentifer2(true);
        clsSharedVariables.setCameraIdentifer3(true);
        clsSharedVariables.setCameraIdentifer4(true);
        clsSharedVariables.setCameraIdentifer5(true);
        clsSharedVariables.setCameraIdentifer6(true);
        clsSharedVariables.setCameraIdentifer7(true);
        clsSharedVariables.setCameraIdentifer8(true);
        this.cmbSelectCameraWatermark.setSelectedIndex(0);
        txtDeviceName.setText(getCameraName1());

        if (clsSharedVariables.getCamSpeedEnable1() == true) {
            chkSpeed.setSelected(true);
        } else {
            chkSpeed.setSelected(false);
        }

        if (clsSharedVariables.getCamlatlangEnable1() == true) {
            chkCoordinates.setSelected(true);
        } else {
            chkCoordinates.setSelected(false);
        }

        if (clsSharedVariables.getCamVehRegEnable1() == true) {
            chkVechicleRegistration.setSelected(true);
        } else {
            chkVechicleRegistration.setSelected(false);
        }

        if (getCameraIdentifer1() == true) {
            chkCamIdentifier.setSelected(true);
            txtDeviceName.setVisible(true);

            txtDeviceName.setText(getCameraName1());
        } else {
            chkCamIdentifier.setSelected(false);
            txtDeviceName.setVisible(false);

        }
        //motion detect
        radCam1Motion.setSelected(true);
        if (clsSharedVariables.getMotionEnableCam1() == true) {
            panBorder.setVisible(true);
            chkMotionDet.setSelected(true);
            this.spinx1value.setValue(clsSharedVariables.getMotionX1Cam1());
            this.spiny1value.setValue(clsSharedVariables.getMotionY1Cam1());
            this.spinx2value.setValue(clsSharedVariables.getMotionX2Cam1());
            this.spiny2value.setValue(clsSharedVariables.getMotionY2Cam1());
            this.spinsensitivityVal.setValue(clsSharedVariables.getSensitivityCam1());
            this.spinthresholdVal.setValue(clsSharedVariables.getThresholdCam1());
            this.spinrecordtime.setValue(clsSharedVariables.getRecordTimeCam1());
        } else {
            panBorder.setVisible(false);
            chkMotionDet.setSelected(false);
        }

        lblMsgCamera.setText("");

        jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane4.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));

        try {
            if (clsSharedVariables.test_date_time_pid == null) {
                clsSharedVariables.test_date_time_pid = sdf.format(cal.getTime());
            }
            date = sdf.parse(clsSharedVariables.test_date_time_pid);
            this.spinTestDate.setValue(date);
        } catch (ParseException ex) {
        }
        this.spinHwRev.setValue((int) (Double.parseDouble(clsSharedVariables.hw_revision)));

        try {
            if (clsSharedVariables.compilation_fw_datetime == null) {
                clsSharedVariables.compilation_fw_datetime = sdf.format(cal.getTime());
            }
            //2019-06-10T12:52:52.646
            date = sdf.parse(clsSharedVariables.compilation_fw_datetime + ":00.336");
            this.spinComDate.setValue(date);
        } catch (ParseException ex) {

        }
        this.spinWatchdogReset.setValue((clsSharedVariables.obu_watchdog_reset_cnt));
        this.spinLowVoltReset.setValue((clsSharedVariables.obu_low_volt_reset_cnt));
        this.spinOverVolt.setValue((clsSharedVariables.obu_high_volt_cnt));
        this.spinLowVoltage.setValue((clsSharedVariables.obu_low_volt_cnt));
        this.spinOverHeat.setValue((clsSharedVariables.obu_over_heat_cnt));
        this.spinGpsLost.setValue((clsSharedVariables.gps_lost_comm_cnt));
        this.spinGpsInvalid.setValue((clsSharedVariables.gps_invalid_data_cnt));
        this.spinGpsAntenna.setValue((clsSharedVariables.gps_antenna_error_cnt));
        this.spinUsbInvalid.setValue((clsSharedVariables.usb_invalid_cnt));
        this.spinUsbUnknown.setValue((clsSharedVariables.usb_unknown_cnt));
        this.spinUsbInvalFileSys.setValue((clsSharedVariables.usb_invalid_filesystem_cnt));
        this.spinUsbOverCurrent.setValue((clsSharedVariables.usb_overcurrent_cnt));
        this.spinDevResetCnt.setValue((clsSharedVariables.no_times_reset));
        date = null;

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
        if (clsSharedVariables.getRs232Enable()) {
            chkRs232.setSelected(true);
            panBorder1.setVisible(true);
        } else {
            chkRs232.setSelected(false);
            panBorder1.setVisible(false);
        }
        if (clsSharedVariables.getRs232Type() == clsDefines.RS232_GPS) {
            radGpsDataRs232.setSelected(true);
        } else if (clsSharedVariables.getRs232Type() == clsDefines.RS232_RFID) {
            this.radRfidRs232.setSelected(true);
        } else {
            radalcoBrake.setSelected(true);
        }

        spinIntervalRs232.setValue(clsSharedVariables.getRs232GpsModeInterval());

        switch (clsSharedVariables.getRs232BaudRate()) {
            case 4800:
                cmbBaudRate.setSelectedIndex(0);
                break;
            case 9600:
                cmbBaudRate.setSelectedIndex(1);
                break;
            case 19200:
                cmbBaudRate.setSelectedIndex(2);
                break;
            case 38400:
                cmbBaudRate.setSelectedIndex(3);
                break;
            case 57600:
                cmbBaudRate.setSelectedIndex(4);
                break;
            case 115200:
                cmbBaudRate.setSelectedIndex(5);
                break;
            case 230400:
                cmbBaudRate.setSelectedIndex(6);
                break;
            case 460800:
                cmbBaudRate.setSelectedIndex(7);
                break;
            default:
                break;
        }

        lblMsg2.setText("  ");
        if (clsSharedVariables.getEthernetEnable()) {
            chkEthernet.setSelected(true);
            panBorder.setVisible(true);
        } else {
            chkEthernet.setSelected(false);
            panBorder.setVisible(false);
        }
        if (clsSharedVariables.getEthernetType() == clsDefines.ETHERNET_GPS) {
            radGpsData.setSelected(true);
        } else {
            this.radRfid.setSelected(true);
        }
                
        txtPortNo.setText(String.valueOf(clsSharedVariables.getEthernetPortNo()));
        txtIpAddress.setText(clsSharedVariables.getEthernetIpAddr());

        spinInterval.setValue(clsSharedVariables.getEthernetGpsModeInterval());
        lblMsg1.setText("");

        if (clsSharedVariables.getApcEnabled()) {
            chkApc.setSelected(true);
        } else {
            chkApc.setSelected(false);
        }
        if (clsSharedVariables.getApcEnabled1()) {
            chkApc1.setSelected(true);
        } else {
            chkApc1.setSelected(false);
        }
        if (clsSharedVariables.getSharedNetwork()) {
            chkNetwork.setSelected(true);
            chkNetwork.setText("Shared Network");
        } else {
            chkNetwork.setSelected(false);
            chkNetwork.setText("Manual Network");
        }
        if (clsSharedVariables.getFmRadioEnable()) {
            chkRadioEnable.setSelected(true);
        } else {
            chkRadioEnable.setSelected(false);
        }
        if (clsSharedVariables.getPedEnable()) {
            chkPedEnable.setSelected(true);
        } else {
            chkPedEnable.setSelected(false);
        }
        lblMsg.setText(" ");
        // chkStorageType.setVisible(false);

        if (getOnvifSupported() == true) {
            chkOnvifSupport.setSelected(true);
        } else {
            chkOnvifSupport.setSelected(false);
        }

        if (clsSharedVariables.getSeparateGPS() == true) {
            this.chkSeparateGPS.setSelected(true);
        } else {
            chkSeparateGPS.setSelected(false);
        }
        if (clsSharedVariables.getPmiEnable() == true) {
            this.chkPmiEnable.setSelected(true);
        } else {
            chkPmiEnable.setSelected(false);
        }
        if (clsSharedVariables.getSplAudioAnnouncement() == true) {
            this.chkSpelAudioAnnounc.setSelected(true);
        } else {
            chkSpelAudioAnnounc.setSelected(false);
        }

        if (clsSharedVariables.getRestartPortsDetected() == true) {
            this.chkRestartPortsDetect.setSelected(true);
        } else {
            chkRestartPortsDetect.setSelected(false);
        }

        if (clsSharedVariables.getCameraType() == clsDefines.IP_CAMERA) {
            this.chkCameraType.setSelected(true);
            chkCameraType.setText("IP");  ///Analog Cam

        } else {
            chkCameraType.setText("ANALOG");
        }
        if (clsSharedVariables.getQuecModuleRev() == clsDefines.QUECTEL_MOD_EC20) {
            this.cmbModuleType.setSelectedIndex(1);
        } else if (clsSharedVariables.getQuecModuleRev() == clsDefines.QUECTEL_MOD_EC25) {
            this.cmbModuleType.setSelectedIndex(2);
        } else {
            this.cmbModuleType.setSelectedIndex(0);
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.read_application_version_file();
        objReadFiles = null;
        try {
            cmbModVersion.setSelectedIndex(COMPANY_NAME_NMEA_PROT);
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        btnethernertgrp = new javax.swing.ButtonGroup();
        btnRs232grp = new javax.swing.ButtonGroup();
        btnGrpCameraType = new javax.swing.ButtonGroup();
        btnGrpSnapshot = new javax.swing.ButtonGroup();
        btnGrpMirror = new javax.swing.ButtonGroup();
        btnGrpMotionDetect = new javax.swing.ButtonGroup();
        btnGrpRecord = new javax.swing.ButtonGroup();
        btnCamLiveGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        chkSeparateGPS = new javax.swing.JCheckBox();
        chkRestartPortsDetect = new javax.swing.JCheckBox();
        cmbModuleType = new javax.swing.JComboBox<>();
        chkRadioEnable = new javax.swing.JCheckBox();
        chkPedEnable = new javax.swing.JCheckBox();
        chkApc = new javax.swing.JCheckBox();
        chkDms = new javax.swing.JCheckBox();
        btnModifyVersion = new javax.swing.JButton();
        cmbModVersion = new javax.swing.JComboBox();
        btnSave1 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        cmbDeleteFiles = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        lblDelete = new javax.swing.JLabel();
        lblMsg4 = new javax.swing.JLabel();
        chkApc1 = new javax.swing.JCheckBox();
        chkPmiEnable = new javax.swing.JCheckBox();
        chkNetwork = new javax.swing.JCheckBox();
        chkSpelAudioAnnounc = new javax.swing.JCheckBox();
        BtnRemount = new javax.swing.JButton();
        Labelmount = new javax.swing.JLabel();
        btnLive = new javax.swing.JButton();
        lblOut = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        chkEthernet = new javax.swing.JCheckBox();
        panBorder = new javax.swing.JPanel();
        radGpsData = new javax.swing.JRadioButton();
        radRfid = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spinInterval = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        txtPortNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIpAddress = new javax.swing.JTextField();
        btnSave2 = new javax.swing.JButton();
        lblMsg1 = new javax.swing.JLabel();
        lblConfigName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblConfigName1 = new javax.swing.JTextField();
        chkRs232 = new javax.swing.JCheckBox();
        panBorder1 = new javax.swing.JPanel();
        radGpsDataRs232 = new javax.swing.JRadioButton();
        radRfidRs232 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        spinIntervalRs232 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        cmbBaudRate = new javax.swing.JComboBox();
        radalcoBrake = new javax.swing.JRadioButton();
        btnSave3 = new javax.swing.JButton();
        lblMsg2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        txtSerialNo = new javax.swing.JTextField();
        spinTestDate = new javax.swing.JSpinner();
        spinWatchdogReset = new javax.swing.JSpinner();
        lblSerNo = new javax.swing.JLabel();
        lblTestDate = new javax.swing.JLabel();
        lblSerNo1 = new javax.swing.JLabel();
        lblSerNo2 = new javax.swing.JLabel();
        lblSerNo3 = new javax.swing.JLabel();
        lblSerNo4 = new javax.swing.JLabel();
        lblSerNo5 = new javax.swing.JLabel();
        lblSerNo6 = new javax.swing.JLabel();
        lblSerNo7 = new javax.swing.JLabel();
        lblSerNo8 = new javax.swing.JLabel();
        lblSerNo9 = new javax.swing.JLabel();
        lblSerNo10 = new javax.swing.JLabel();
        lblSerNo11 = new javax.swing.JLabel();
        spinGpsLost = new javax.swing.JSpinner();
        spinLowVoltReset = new javax.swing.JSpinner();
        spinGpsInvalid = new javax.swing.JSpinner();
        spinGpsAntenna = new javax.swing.JSpinner();
        spinUsbInvalid = new javax.swing.JSpinner();
        spinUsbUnknown = new javax.swing.JSpinner();
        spinUsbInvalFileSys = new javax.swing.JSpinner();
        spinOverVolt = new javax.swing.JSpinner();
        spinLowVoltage = new javax.swing.JSpinner();
        spinOverHeat = new javax.swing.JSpinner();
        lblTestDate1 = new javax.swing.JLabel();
        lblTestDate2 = new javax.swing.JLabel();
        spinHwRev = new javax.swing.JSpinner();
        spinComDate = new javax.swing.JSpinner();
        lblSerNo12 = new javax.swing.JLabel();
        spinUsbOverCurrent = new javax.swing.JSpinner();
        lblSerNo13 = new javax.swing.JLabel();
        spinDevResetCnt = new javax.swing.JSpinner();
        btnSave4 = new javax.swing.JButton();
        lblMsg3 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        tanCamType = new javax.swing.JPanel();
        btnSave5 = new javax.swing.JButton();
        lblMsgCamera = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        panCam3 = new javax.swing.JPanel();
        chkCam3Enable = new javax.swing.JCheckBox();
        pan3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCam3User = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        PasswordCam3 = new javax.swing.JPasswordField();
        cmbCam3Type = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        btnShowPassword3 = new javax.swing.JButton();
        panCam1 = new javax.swing.JPanel();
        chkCam1Enable = new javax.swing.JCheckBox();
        pan1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCam1User = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PasswordCam1 = new javax.swing.JPasswordField();
        cmbCam1Type = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        btnShowPassword1 = new javax.swing.JButton();
        panCam4 = new javax.swing.JPanel();
        chkCam4Enable = new javax.swing.JCheckBox();
        pan4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCam4User = new javax.swing.JTextField();
        PasswordCam4 = new javax.swing.JPasswordField();
        cmbCam4Type = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        btnShowPassword4 = new javax.swing.JButton();
        panCam2 = new javax.swing.JPanel();
        chkCam2Enable = new javax.swing.JCheckBox();
        pan2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCam2User = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        PasswordCam2 = new javax.swing.JPasswordField();
        cmbCam2Type = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        btnShowPassword2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        cmbSelectCameraNo = new javax.swing.JComboBox<>();
        tabSnapshot = new javax.swing.JPanel();
        btnSaveSnapshot = new javax.swing.JButton();
        lblMsgSnapshot = new javax.swing.JLabel();
        radCam1 = new javax.swing.JRadioButton();
        radCam2 = new javax.swing.JRadioButton();
        radCam3 = new javax.swing.JRadioButton();
        radCam4 = new javax.swing.JRadioButton();
        btnRefreshSnapshot = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        panSnap = new javax.swing.JPanel();
        chkSnapShotEnable = new javax.swing.JCheckBox();
        panSnapCont = new javax.swing.JPanel();
        lblObuId8 = new javax.swing.JLabel();
        cmbSnapContStreamType = new javax.swing.JComboBox();
        lblObuId9 = new javax.swing.JLabel();
        spinSnapContInterval = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        chkSnapContUpload = new javax.swing.JCheckBox();
        chkSnapCont = new javax.swing.JCheckBox();
        jScrollPane8 = new javax.swing.JScrollPane();
        panSnapEvent = new javax.swing.JPanel();
        cmbSnapEventStreamDig1 = new javax.swing.JComboBox();
        chkSnapDig4 = new javax.swing.JCheckBox();
        lblObuId2 = new javax.swing.JLabel();
        lblObuId1 = new javax.swing.JLabel();
        spinSnapEventntervalDig1 = new javax.swing.JSpinner();
        chkSnapDig2 = new javax.swing.JCheckBox();
        chkSnapDig3 = new javax.swing.JCheckBox();
        chkSnapDig1 = new javax.swing.JCheckBox();
        cmbSnapEventStreamDig2 = new javax.swing.JComboBox();
        spinSnapEventntervalDig2 = new javax.swing.JSpinner();
        cmbSnapEventStreamDig3 = new javax.swing.JComboBox();
        spinSnapEventntervalDig3 = new javax.swing.JSpinner();
        cmbSnapEventStreamDig4 = new javax.swing.JComboBox();
        spinSnapEventntervalDig4 = new javax.swing.JSpinner();
        chkSnapDi1Upload = new javax.swing.JCheckBox();
        chkSnapDi3Upload = new javax.swing.JCheckBox();
        chkSnapDi4Upload = new javax.swing.JCheckBox();
        chkSnapDi2Upload = new javax.swing.JCheckBox();
        TabVideoEncoding = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        lblObuId = new javax.swing.JLabel();
        cmbStreamType = new javax.swing.JComboBox();
        lblvideoaudio = new javax.swing.JLabel();
        cmbVideomode = new javax.swing.JComboBox();
        lblresoulation = new javax.swing.JLabel();
        cmbResolution = new javax.swing.JComboBox();
        lblbitrate = new javax.swing.JLabel();
        cmbBitrateType = new javax.swing.JComboBox();
        lblvideoquality = new javax.swing.JLabel();
        cmbVideoquality = new javax.swing.JComboBox();
        lblframerate = new javax.swing.JLabel();
        cmbMaxbitrate = new javax.swing.JComboBox();
        cmbFramerate = new javax.swing.JComboBox();
        lblvideoencode = new javax.swing.JLabel();
        lblmaxbit = new javax.swing.JLabel();
        cmbVidEncoding = new javax.swing.JComboBox();
        lbselectcamera = new javax.swing.JLabel();
        cmbSelectCameraEncode = new javax.swing.JComboBox();
        lblframerate1 = new javax.swing.JLabel();
        spinFrameInterval = new javax.swing.JSpinner();
        lblMsgEncode = new javax.swing.JLabel();
        btnSaveEncode = new javax.swing.JButton();
        btnRefresh1 = new javax.swing.JButton();
        pbarEncode = new javax.swing.JProgressBar();
        tabCamBrightness = new javax.swing.JPanel();
        lbselectcamera1 = new javax.swing.JLabel();
        cmbSelectCameraBrightness = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        lblcontrast1 = new javax.swing.JLabel();
        sliderSaturationLevel1 = new javax.swing.JSlider();
        sliderBrightness = new javax.swing.JSlider();
        lblBrihtness = new javax.swing.JLabel();
        lblSaturationLevel1 = new javax.swing.JLabel();
        sliderContrastLevel1 = new javax.swing.JSlider();
        lblBright = new javax.swing.JLabel();
        lblContrast = new javax.swing.JLabel();
        lblSaturation = new javax.swing.JLabel();
        btnSaveBrightness = new javax.swing.JButton();
        btnRefresh2 = new javax.swing.JButton();
        lblMsgBrightness = new javax.swing.JLabel();
        pbarBrightness = new javax.swing.JProgressBar();
        tabMirror = new javax.swing.JPanel();
        btnSaveMirror = new javax.swing.JButton();
        lblMsgMirror = new javax.swing.JLabel();
        btnRefreshMirror = new javax.swing.JButton();
        lbselectcamera3 = new javax.swing.JLabel();
        cmbSelectCameraMirror = new javax.swing.JComboBox();
        radMirrorOn = new javax.swing.JRadioButton();
        radMirrorOff = new javax.swing.JRadioButton();
        pbarMirror = new javax.swing.JProgressBar();
        tabWaterMark = new javax.swing.JPanel();
        lbselectcamera2 = new javax.swing.JLabel();
        cmbSelectCameraWatermark = new javax.swing.JComboBox();
        lblMsgWatermark = new javax.swing.JLabel();
        btnSaveWatermark = new javax.swing.JButton();
        btnRefreshWatermark = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        txtDeviceName = new javax.swing.JTextField();
        chkSpeed = new javax.swing.JCheckBox();
        chkCamIdentifier = new javax.swing.JCheckBox();
        chkVechicleRegistration = new javax.swing.JCheckBox();
        chkCoordinates = new javax.swing.JCheckBox();
        pbarWaterMark = new javax.swing.JProgressBar();
        jPanel12 = new javax.swing.JPanel();
        lblEmergencyService2 = new javax.swing.JLabel();
        spinVidRecStorage1 = new javax.swing.JSpinner();
        lblEmergencyService4 = new javax.swing.JLabel();
        lblEmergencyService3 = new javax.swing.JLabel();
        spinEventRecStorage = new javax.swing.JSpinner();
        lblEmergencyService5 = new javax.swing.JLabel();
        lblEmergencyService1 = new javax.swing.JLabel();
        spinSnapStorage = new javax.swing.JSpinner();
        lblEmergencyService6 = new javax.swing.JLabel();
        btnSaveVideoLimit = new javax.swing.JButton();
        lblMsgRecord1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabRecordConfig = new javax.swing.JPanel();
        panManRecord = new javax.swing.JPanel();
        lblFromDate = new javax.swing.JLabel();
        spinStartDate = new javax.swing.JSpinner();
        lblToDate = new javax.swing.JLabel();
        spinEndDate = new javax.swing.JSpinner();
        chkSunday = new javax.swing.JCheckBox();
        chkMonday = new javax.swing.JCheckBox();
        chkTuesday = new javax.swing.JCheckBox();
        chkWednesday = new javax.swing.JCheckBox();
        chkThursday = new javax.swing.JCheckBox();
        chkFriday = new javax.swing.JCheckBox();
        chkSaturday = new javax.swing.JCheckBox();
        lblMsgRecord = new javax.swing.JLabel();
        btnSaveRecord = new javax.swing.JButton();
        radManRecord = new javax.swing.JRadioButton();
        radContRecord = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        chkCam1Audio = new javax.swing.JCheckBox();
        chkCam4Audio = new javax.swing.JCheckBox();
        chkCam2Audio = new javax.swing.JCheckBox();
        chkCam3Audio = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        lblObuId6 = new javax.swing.JLabel();
        lblObuId4 = new javax.swing.JLabel();
        SpinEventRecTime = new javax.swing.JSpinner();
        chkEventRecord = new javax.swing.JCheckBox();
        SpinEventPreRecTime = new javax.swing.JSpinner();
        lblObuId7 = new javax.swing.JLabel();
        SpinCanRecTime = new javax.swing.JSpinner();
        SpinCanPreRecTime = new javax.swing.JSpinner();
        chkCanRecord = new javax.swing.JCheckBox();
        cmbVidStreamTypeCan = new javax.swing.JComboBox();
        cmbVidStreamTypeEvent = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        chkCam1Rec = new javax.swing.JCheckBox();
        chkCam2Rec = new javax.swing.JCheckBox();
        chkCam3Rec = new javax.swing.JCheckBox();
        chkCam4Rec = new javax.swing.JCheckBox();
        btnRefresh3 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        chkOnvifSupport = new javax.swing.JCheckBox();
        chkCameraType = new javax.swing.JCheckBox();
        btnSave6 = new javax.swing.JButton();
        lblText = new javax.swing.JLabel();
        tabMotionDetect = new javax.swing.JPanel();
        chkMotionDet = new javax.swing.JCheckBox();
        btnSaveMotion = new javax.swing.JButton();
        lblMsgMotionDetect = new javax.swing.JLabel();
        radCam1Motion = new javax.swing.JRadioButton();
        radCam4Motion = new javax.swing.JRadioButton();
        radCam3Motion = new javax.swing.JRadioButton();
        radCam2Motion = new javax.swing.JRadioButton();
        btnRefreshMotion = new javax.swing.JButton();
        lblVideoWaterMarkingConfigName1 = new javax.swing.JTextField();
        pbarMotion = new javax.swing.JProgressBar();
        jScrollPane7 = new javax.swing.JScrollPane();
        panBorder2 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        spinx1value = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        spinx2value = new javax.swing.JSpinner();
        spiny1value = new javax.swing.JSpinner();
        jLabel29 = new javax.swing.JLabel();
        spiny2value = new javax.swing.JSpinner();
        jLabel30 = new javax.swing.JLabel();
        spinsensitivityVal = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        spinthresholdVal = new javax.swing.JSpinner();
        jLabel33 = new javax.swing.JLabel();
        spinrecordtime = new javax.swing.JSpinner();

        jFormattedTextField1.setText("jFormattedTextField1");

        setPreferredSize(new java.awt.Dimension(504, 430));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(510, 600));

        chkSeparateGPS.setFont(chkSeparateGPS.getFont().deriveFont(chkSeparateGPS.getFont().getStyle() | java.awt.Font.BOLD, chkSeparateGPS.getFont().getSize()+6));
        chkSeparateGPS.setForeground(new java.awt.Color(102, 0, 0));
        chkSeparateGPS.setText("L89 ");
        chkSeparateGPS.setPreferredSize(new java.awt.Dimension(200, 30));
        chkSeparateGPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSeparateGPSActionPerformed(evt);
            }
        });

        chkRestartPortsDetect.setFont(chkRestartPortsDetect.getFont().deriveFont(chkRestartPortsDetect.getFont().getStyle() | java.awt.Font.BOLD, chkRestartPortsDetect.getFont().getSize()+6));
        chkRestartPortsDetect.setForeground(new java.awt.Color(102, 0, 0));
        chkRestartPortsDetect.setText("GSM MODULE TYPE");
        chkRestartPortsDetect.setPreferredSize(new java.awt.Dimension(200, 30));
        chkRestartPortsDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRestartPortsDetectActionPerformed(evt);
            }
        });

        cmbModuleType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbModuleType.setForeground(new java.awt.Color(102, 0, 0));
        cmbModuleType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "EC20", "EC25" }));
        cmbModuleType.setSelectedIndex(1);

        chkRadioEnable.setFont(chkRadioEnable.getFont().deriveFont(chkRadioEnable.getFont().getStyle() | java.awt.Font.BOLD, chkRadioEnable.getFont().getSize()+6));
        chkRadioEnable.setForeground(new java.awt.Color(102, 0, 0));
        chkRadioEnable.setText("RADIO");
        chkRadioEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRadioEnableActionPerformed(evt);
            }
        });

        chkPedEnable.setFont(chkPedEnable.getFont().deriveFont(chkPedEnable.getFont().getStyle() | java.awt.Font.BOLD, chkPedEnable.getFont().getSize()+6));
        chkPedEnable.setForeground(new java.awt.Color(102, 0, 0));
        chkPedEnable.setText("Pedestrian");
        chkPedEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPedEnableActionPerformed(evt);
            }
        });

        chkApc.setFont(chkApc.getFont().deriveFont(chkApc.getFont().getStyle() | java.awt.Font.BOLD, chkApc.getFont().getSize()+6));
        chkApc.setForeground(new java.awt.Color(102, 0, 0));
        chkApc.setText("FRONT APC");
        chkApc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkApcActionPerformed(evt);
            }
        });

        chkDms.setFont(chkDms.getFont().deriveFont(chkDms.getFont().getStyle() | java.awt.Font.BOLD, chkDms.getFont().getSize()+6));
        chkDms.setForeground(new java.awt.Color(102, 0, 0));
        chkDms.setText("DMS");

        btnModifyVersion.setBackground(new java.awt.Color(47, 49, 51));
        btnModifyVersion.setFont(btnModifyVersion.getFont().deriveFont(btnModifyVersion.getFont().getStyle() | java.awt.Font.BOLD, btnModifyVersion.getFont().getSize()+7));
        btnModifyVersion.setForeground(new java.awt.Color(255, 255, 255));
        btnModifyVersion.setText("PROTOCOL IP4");
        btnModifyVersion.setPreferredSize(new java.awt.Dimension(200, 32));
        btnModifyVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyVersionActionPerformed(evt);
            }
        });

        cmbModVersion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbModVersion.setForeground(new java.awt.Color(102, 0, 0));
        cmbModVersion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SUMITH", "JBM", "AMINEX", "DIMTS NAGPUR" }));
        cmbModVersion.setPreferredSize(new java.awt.Dimension(138, 32));
        cmbModVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbModVersionActionPerformed(evt);
            }
        });

        btnSave1.setBackground(new java.awt.Color(47, 49, 51));
        btnSave1.setFont(btnSave1.getFont().deriveFont(btnSave1.getFont().getStyle() | java.awt.Font.BOLD, btnSave1.getFont().getSize()+7));
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("SAVE");
        btnSave1.setAutoscrolls(true);
        btnSave1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave1.setPreferredSize(new java.awt.Dimension(100, 32));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | java.awt.Font.BOLD, btnSave.getFont().getSize()+6));
        btnSave.setForeground(new java.awt.Color(0, 0, 102));
        btnSave.setText("SAVE");
        btnSave.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+1));
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("All default values deleted");

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Delete Files"));

        cmbDeleteFiles.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbDeleteFiles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Routes", "Logs", "Videos", "Can", "GPS Data", "ALL" }));

        btnDelete.setBackground(new java.awt.Color(47, 49, 51));
        btnDelete.setFont(btnDelete.getFont().deriveFont(btnDelete.getFont().getStyle() | java.awt.Font.BOLD, btnDelete.getFont().getSize()+7));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(cmbDeleteFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDeleteFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblMsg4.setFont(lblMsg4.getFont().deriveFont(lblMsg4.getFont().getStyle() | java.awt.Font.BOLD, lblMsg4.getFont().getSize()+1));
        lblMsg4.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg4.setText("All default values deleted");

        chkApc1.setFont(chkApc1.getFont().deriveFont(chkApc1.getFont().getStyle() | java.awt.Font.BOLD, chkApc1.getFont().getSize()+6));
        chkApc1.setForeground(new java.awt.Color(102, 0, 0));
        chkApc1.setText("BACK APC");
        chkApc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkApc1ActionPerformed(evt);
            }
        });

        chkPmiEnable.setFont(chkPmiEnable.getFont().deriveFont(chkPmiEnable.getFont().getStyle() | java.awt.Font.BOLD, chkPmiEnable.getFont().getSize()+6));
        chkPmiEnable.setForeground(new java.awt.Color(102, 0, 0));
        chkPmiEnable.setText("PMI");
        chkPmiEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPmiEnableActionPerformed(evt);
            }
        });

        chkNetwork.setFont(chkNetwork.getFont().deriveFont(chkNetwork.getFont().getStyle() | java.awt.Font.BOLD, chkNetwork.getFont().getSize()+6));
        chkNetwork.setForeground(new java.awt.Color(102, 0, 0));
        chkNetwork.setText("Shared network");
        chkNetwork.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkNetworkStateChanged(evt);
            }
        });
        chkNetwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNetworkActionPerformed(evt);
            }
        });

        chkSpelAudioAnnounc.setFont(chkSpelAudioAnnounc.getFont().deriveFont(chkSpelAudioAnnounc.getFont().getStyle() | java.awt.Font.BOLD, chkSpelAudioAnnounc.getFont().getSize()+6));
        chkSpelAudioAnnounc.setForeground(new java.awt.Color(102, 0, 0));
        chkSpelAudioAnnounc.setText("SPELAUDIO");
        chkSpelAudioAnnounc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSpelAudioAnnouncActionPerformed(evt);
            }
        });

        BtnRemount.setBackground(new java.awt.Color(0, 0, 0));
        BtnRemount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnRemount.setForeground(new java.awt.Color(255, 255, 255));
        BtnRemount.setText("Remount");
        BtnRemount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRemountActionPerformed(evt);
            }
        });

        Labelmount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLive.setBackground(new java.awt.Color(0, 0, 0));
        btnLive.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLive.setForeground(new java.awt.Color(255, 255, 255));
        btnLive.setText("LIVE");
        btnLive.setMaximumSize(new java.awt.Dimension(90, 27));
        btnLive.setMinimumSize(new java.awt.Dimension(90, 27));
        btnLive.setPreferredSize(new java.awt.Dimension(90, 27));
        btnLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiveActionPerformed(evt);
            }
        });

        lblOut.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnModifyVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbModVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(chkApc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkApc1)
                                    .addComponent(chkRadioEnable)
                                    .addComponent(chkSeparateGPS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkSpelAudioAnnounc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkNetwork)
                                            .addComponent(chkPedEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(chkPmiEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkRestartPortsDetect, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbModuleType, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkDms)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Labelmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnRemount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(lblMsg4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkRadioEnable)
                    .addComponent(chkPmiEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDms))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSeparateGPS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtnRemount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkNetwork, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSpelAudioAnnounc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkApc)
                    .addComponent(Labelmount, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkPedEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkApc1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbModuleType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkRestartPortsDetect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblOut, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModifyVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbModVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SYSTEM", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 500));

        chkEthernet.setFont(chkEthernet.getFont().deriveFont(chkEthernet.getFont().getStyle() | java.awt.Font.BOLD, chkEthernet.getFont().getSize()+5));
        chkEthernet.setForeground(new java.awt.Color(0, 0, 102));
        chkEthernet.setText("Enable Ethernet");
        chkEthernet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEthernetActionPerformed(evt);
            }
        });

        panBorder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnethernertgrp.add(radGpsData);
        radGpsData.setFont(radGpsData.getFont().deriveFont(radGpsData.getFont().getStyle() | java.awt.Font.BOLD, radGpsData.getFont().getSize()+4));
        radGpsData.setForeground(new java.awt.Color(0, 0, 102));
        radGpsData.setSelected(true);
        radGpsData.setText("Send GNSS Data");

        btnethernertgrp.add(radRfid);
        radRfid.setFont(radRfid.getFont().deriveFont(radRfid.getFont().getStyle() | java.awt.Font.BOLD, radRfid.getFont().getSize()+4));
        radRfid.setForeground(new java.awt.Color(0, 0, 102));
        radRfid.setText("Retrieve RFID data");

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+6));
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Mode Interval");

        spinInterval.setFont(spinInterval.getFont().deriveFont(spinInterval.getFont().getStyle() | java.awt.Font.BOLD, spinInterval.getFont().getSize()+6));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(spinInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+6));
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Port No");

        txtPortNo.setFont(txtPortNo.getFont().deriveFont(txtPortNo.getFont().getStyle() | java.awt.Font.BOLD, txtPortNo.getFont().getSize()+6));
        txtPortNo.setText("5557");
        txtPortNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPortNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPortNoFocusLost(evt);
            }
        });

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD, jLabel3.getFont().getSize()+6));
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("IP Address");

        txtIpAddress.setFont(txtIpAddress.getFont().deriveFont(txtIpAddress.getFont().getStyle() | java.awt.Font.BOLD, txtIpAddress.getFont().getSize()+6));
        txtIpAddress.setText("192.168.0.26");
        txtIpAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIpAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIpAddressFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panBorderLayout = new javax.swing.GroupLayout(panBorder);
        panBorder.setLayout(panBorderLayout);
        panBorderLayout.setHorizontalGroup(
            panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62))
            .addGroup(panBorderLayout.createSequentialGroup()
                .addGroup(panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBorderLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIpAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtPortNo)))
                    .addGroup(panBorderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(radGpsData)
                        .addGap(18, 18, 18)
                        .addComponent(radRfid)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panBorderLayout.setVerticalGroup(
            panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBorderLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radGpsData)
                    .addComponent(radRfid))
                .addGap(18, 18, 18)
                .addGroup(panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIpAddress)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txtPortNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSave2.setBackground(new java.awt.Color(47, 49, 51));
        btnSave2.setFont(btnSave2.getFont().deriveFont(btnSave2.getFont().getStyle() | java.awt.Font.BOLD, btnSave2.getFont().getSize()+7));
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setText("SAVE");
        btnSave2.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        lblMsg1.setFont(lblMsg1.getFont().deriveFont(lblMsg1.getFont().getStyle() | java.awt.Font.BOLD, lblMsg1.getFont().getSize()+7));
        lblMsg1.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg1.setText("Configuration Saved");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkEthernet)
                        .addGap(0, 241, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chkEthernet)
                .addGap(18, 18, 18)
                .addComponent(panBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("ETHERNET");
        lblConfigName.setPreferredSize(new java.awt.Dimension(500, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConfigName, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ETHERNET", jPanel2);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 500));

        lblConfigName1.setEditable(false);
        lblConfigName1.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName1.setFont(lblConfigName1.getFont().deriveFont(lblConfigName1.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName1.getFont().getSize()+10));
        lblConfigName1.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName1.setText("RS232");
        lblConfigName1.setPreferredSize(new java.awt.Dimension(500, 30));

        chkRs232.setFont(chkRs232.getFont().deriveFont(chkRs232.getFont().getStyle() | java.awt.Font.BOLD, chkRs232.getFont().getSize()+5));
        chkRs232.setForeground(new java.awt.Color(0, 0, 102));
        chkRs232.setText("Enable RS232");
        chkRs232.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRs232ActionPerformed(evt);
            }
        });

        panBorder1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panBorder1.setPreferredSize(new java.awt.Dimension(453, 180));

        btnRs232grp.add(radGpsDataRs232);
        radGpsDataRs232.setFont(radGpsDataRs232.getFont().deriveFont(radGpsDataRs232.getFont().getStyle() | java.awt.Font.BOLD, radGpsDataRs232.getFont().getSize()+4));
        radGpsDataRs232.setForeground(new java.awt.Color(0, 0, 102));
        radGpsDataRs232.setText("Send GNSS Data");
        radGpsDataRs232.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radGpsDataRs232ActionPerformed(evt);
            }
        });

        btnRs232grp.add(radRfidRs232);
        radRfidRs232.setFont(radRfidRs232.getFont().deriveFont(radRfidRs232.getFont().getStyle() | java.awt.Font.BOLD, radRfidRs232.getFont().getSize()+4));
        radRfidRs232.setForeground(new java.awt.Color(0, 0, 102));
        radRfidRs232.setText("Retrieve RFID ");
        radRfidRs232.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRfidRs232ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, jLabel4.getFont().getSize()+6));
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Mode Interval");

        spinIntervalRs232.setFont(spinIntervalRs232.getFont().deriveFont(spinIntervalRs232.getFont().getStyle() | java.awt.Font.BOLD, spinIntervalRs232.getFont().getSize()+6));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(48, 48, 48)
                .addComponent(spinIntervalRs232, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinIntervalRs232, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD, jLabel5.getFont().getSize()+6));
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Baud rate");

        cmbBaudRate.setFont(cmbBaudRate.getFont().deriveFont(cmbBaudRate.getFont().getStyle() | java.awt.Font.BOLD, cmbBaudRate.getFont().getSize()+6));
        cmbBaudRate.setForeground(new java.awt.Color(0, 0, 102));
        cmbBaudRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4800", "9600", "19200", "38400", "57600", "115200", "230400", "460800", " " }));

        btnRs232grp.add(radalcoBrake);
        radalcoBrake.setFont(radalcoBrake.getFont().deriveFont(radalcoBrake.getFont().getStyle() | java.awt.Font.BOLD, radalcoBrake.getFont().getSize()+4));
        radalcoBrake.setForeground(new java.awt.Color(0, 0, 102));
        radalcoBrake.setText("Alco Brake ");
        radalcoBrake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radalcoBrakeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panBorder1Layout = new javax.swing.GroupLayout(panBorder1);
        panBorder1.setLayout(panBorder1Layout);
        panBorder1Layout.setHorizontalGroup(
            panBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBorder1Layout.createSequentialGroup()
                .addComponent(radGpsDataRs232)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radRfidRs232, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radalcoBrake, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panBorder1Layout.createSequentialGroup()
                .addGroup(panBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBorder1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbBaudRate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panBorder1Layout.setVerticalGroup(
            panBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBorder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radGpsDataRs232)
                    .addComponent(radRfidRs232)
                    .addComponent(radalcoBrake))
                .addGap(18, 18, 18)
                .addGroup(panBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBaudRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSave3.setBackground(new java.awt.Color(47, 49, 51));
        btnSave3.setFont(btnSave3.getFont().deriveFont(btnSave3.getFont().getStyle() | java.awt.Font.BOLD, btnSave3.getFont().getSize()+7));
        btnSave3.setForeground(new java.awt.Color(255, 255, 255));
        btnSave3.setText("SAVE");
        btnSave3.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave3ActionPerformed(evt);
            }
        });

        lblMsg2.setFont(lblMsg2.getFont().deriveFont(lblMsg2.getFont().getStyle() | java.awt.Font.BOLD, lblMsg2.getFont().getSize()+7));
        lblMsg2.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg2.setText("Configuration Saved");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfigName1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(chkRs232)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblConfigName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkRs232)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg2))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RS232", jPanel5);

        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+7));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("DEVICE PID CODES");
        jTextField1.setPreferredSize(new java.awt.Dimension(513, 28));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(490, 450));

        jPanel8.setPreferredSize(new java.awt.Dimension(480, 1500));

        txtSerialNo.setFont(txtSerialNo.getFont().deriveFont(txtSerialNo.getFont().getStyle() | java.awt.Font.BOLD, txtSerialNo.getFont().getSize()+5));
        txtSerialNo.setForeground(new java.awt.Color(102, 0, 0));
        txtSerialNo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtSerialNo.setText("10");
        txtSerialNo.setPreferredSize(new java.awt.Dimension(190, 32));
        txtSerialNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSerialNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSerialNoFocusLost(evt);
            }
        });
        txtSerialNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerialNoActionPerformed(evt);
            }
        });

        spinTestDate.setFont(spinTestDate.getFont().deriveFont(spinTestDate.getFont().getStyle() | java.awt.Font.BOLD, spinTestDate.getFont().getSize()+5));
        spinTestDate.setModel(new javax.swing.SpinnerDateModel());
        spinTestDate.setFocusable(false);
        spinTestDate.setMaximumSize(new java.awt.Dimension(31, 31));
        spinTestDate.setName(""); // NOI18N
        spinTestDate.setPreferredSize(new java.awt.Dimension(190, 32));
        spinTestDate.setVerifyInputWhenFocusTarget(false);

        spinWatchdogReset.setFont(spinWatchdogReset.getFont().deriveFont(spinWatchdogReset.getFont().getStyle() | java.awt.Font.BOLD, spinWatchdogReset.getFont().getSize()+6));
        spinWatchdogReset.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinWatchdogReset.setPreferredSize(new java.awt.Dimension(190, 32));

        lblSerNo.setFont(lblSerNo.getFont().deriveFont(lblSerNo.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo.getFont().getSize()+5));
        lblSerNo.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo.setText("SERIAL NO");
        lblSerNo.setPreferredSize(new java.awt.Dimension(222, 30));

        lblTestDate.setFont(lblTestDate.getFont().deriveFont(lblTestDate.getFont().getStyle() | java.awt.Font.BOLD, lblTestDate.getFont().getSize()+5));
        lblTestDate.setForeground(new java.awt.Color(0, 0, 102));
        lblTestDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTestDate.setText("TEST DATE");
        lblTestDate.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo1.setFont(lblSerNo1.getFont().deriveFont(lblSerNo1.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo1.getFont().getSize()+5));
        lblSerNo1.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo1.setText("WATCHDOG RESET");
        lblSerNo1.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo2.setFont(lblSerNo2.getFont().deriveFont(lblSerNo2.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo2.getFont().getSize()+5));
        lblSerNo2.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo2.setText("LOW VOLTAGE RESET");
        lblSerNo2.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo3.setFont(lblSerNo3.getFont().deriveFont(lblSerNo3.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo3.getFont().getSize()+5));
        lblSerNo3.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo3.setText("GPS LOST");
        lblSerNo3.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo4.setFont(lblSerNo4.getFont().deriveFont(lblSerNo4.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo4.getFont().getSize()+5));
        lblSerNo4.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo4.setText("GPS INVALID DATA");
        lblSerNo4.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo5.setFont(lblSerNo5.getFont().deriveFont(lblSerNo5.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo5.getFont().getSize()+5));
        lblSerNo5.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo5.setText("GPS ANTENNA ERROR");
        lblSerNo5.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo6.setFont(lblSerNo6.getFont().deriveFont(lblSerNo6.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo6.getFont().getSize()+5));
        lblSerNo6.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo6.setText("USB INVALID");
        lblSerNo6.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo7.setFont(lblSerNo7.getFont().deriveFont(lblSerNo7.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo7.getFont().getSize()+5));
        lblSerNo7.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo7.setText("USB INVALID FILE SYS");
        lblSerNo7.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo8.setFont(lblSerNo8.getFont().deriveFont(lblSerNo8.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo8.getFont().getSize()+5));
        lblSerNo8.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo8.setText("OVER VOLTAGE");
        lblSerNo8.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo9.setFont(lblSerNo9.getFont().deriveFont(lblSerNo9.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo9.getFont().getSize()+5));
        lblSerNo9.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo9.setText("LOW VOLTAGE");
        lblSerNo9.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo10.setFont(lblSerNo10.getFont().deriveFont(lblSerNo10.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo10.getFont().getSize()+5));
        lblSerNo10.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo10.setText("USB UNKNOWN");
        lblSerNo10.setPreferredSize(new java.awt.Dimension(222, 30));

        lblSerNo11.setFont(lblSerNo11.getFont().deriveFont(lblSerNo11.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo11.getFont().getSize()+5));
        lblSerNo11.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo11.setText("OVER HEAT");
        lblSerNo11.setPreferredSize(new java.awt.Dimension(222, 30));

        spinGpsLost.setFont(spinGpsLost.getFont().deriveFont(spinGpsLost.getFont().getStyle() | java.awt.Font.BOLD, spinGpsLost.getFont().getSize()+6));
        spinGpsLost.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinGpsLost.setPreferredSize(new java.awt.Dimension(190, 32));

        spinLowVoltReset.setFont(spinLowVoltReset.getFont().deriveFont(spinLowVoltReset.getFont().getStyle() | java.awt.Font.BOLD, spinLowVoltReset.getFont().getSize()+6));
        spinLowVoltReset.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinLowVoltReset.setPreferredSize(new java.awt.Dimension(190, 32));

        spinGpsInvalid.setFont(spinGpsInvalid.getFont().deriveFont(spinGpsInvalid.getFont().getStyle() | java.awt.Font.BOLD, spinGpsInvalid.getFont().getSize()+6));
        spinGpsInvalid.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinGpsInvalid.setPreferredSize(new java.awt.Dimension(190, 32));

        spinGpsAntenna.setFont(spinGpsAntenna.getFont().deriveFont(spinGpsAntenna.getFont().getStyle() | java.awt.Font.BOLD, spinGpsAntenna.getFont().getSize()+6));
        spinGpsAntenna.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinGpsAntenna.setPreferredSize(new java.awt.Dimension(190, 32));

        spinUsbInvalid.setFont(spinUsbInvalid.getFont().deriveFont(spinUsbInvalid.getFont().getStyle() | java.awt.Font.BOLD, spinUsbInvalid.getFont().getSize()+6));
        spinUsbInvalid.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinUsbInvalid.setPreferredSize(new java.awt.Dimension(190, 32));

        spinUsbUnknown.setFont(spinUsbUnknown.getFont().deriveFont(spinUsbUnknown.getFont().getStyle() | java.awt.Font.BOLD, spinUsbUnknown.getFont().getSize()+6));
        spinUsbUnknown.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinUsbUnknown.setPreferredSize(new java.awt.Dimension(190, 32));

        spinUsbInvalFileSys.setFont(spinUsbInvalFileSys.getFont().deriveFont(spinUsbInvalFileSys.getFont().getStyle() | java.awt.Font.BOLD, spinUsbInvalFileSys.getFont().getSize()+6));
        spinUsbInvalFileSys.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinUsbInvalFileSys.setPreferredSize(new java.awt.Dimension(190, 32));

        spinOverVolt.setFont(spinOverVolt.getFont().deriveFont(spinOverVolt.getFont().getStyle() | java.awt.Font.BOLD, spinOverVolt.getFont().getSize()+6));
        spinOverVolt.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinOverVolt.setPreferredSize(new java.awt.Dimension(190, 32));

        spinLowVoltage.setFont(spinLowVoltage.getFont().deriveFont(spinLowVoltage.getFont().getStyle() | java.awt.Font.BOLD, spinLowVoltage.getFont().getSize()+6));
        spinLowVoltage.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinLowVoltage.setPreferredSize(new java.awt.Dimension(190, 32));

        spinOverHeat.setFont(spinOverHeat.getFont().deriveFont(spinOverHeat.getFont().getStyle() | java.awt.Font.BOLD, spinOverHeat.getFont().getSize()+6));
        spinOverHeat.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinOverHeat.setPreferredSize(new java.awt.Dimension(190, 32));

        lblTestDate1.setFont(lblTestDate1.getFont().deriveFont(lblTestDate1.getFont().getStyle() | java.awt.Font.BOLD, lblTestDate1.getFont().getSize()+5));
        lblTestDate1.setForeground(new java.awt.Color(0, 0, 102));
        lblTestDate1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTestDate1.setText("HARDWARE REVISION");
        lblTestDate1.setPreferredSize(new java.awt.Dimension(222, 30));

        lblTestDate2.setFont(lblTestDate2.getFont().deriveFont(lblTestDate2.getFont().getStyle() | java.awt.Font.BOLD, lblTestDate2.getFont().getSize()+5));
        lblTestDate2.setForeground(new java.awt.Color(0, 0, 102));
        lblTestDate2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTestDate2.setText("COMPILATION DATE");
        lblTestDate2.setPreferredSize(new java.awt.Dimension(222, 30));

        spinHwRev.setFont(spinHwRev.getFont().deriveFont(spinHwRev.getFont().getStyle() | java.awt.Font.BOLD, spinHwRev.getFont().getSize()+6));
        spinHwRev.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinHwRev.setPreferredSize(new java.awt.Dimension(190, 32));

        spinComDate.setFont(spinComDate.getFont().deriveFont(spinComDate.getFont().getStyle() | java.awt.Font.BOLD, spinComDate.getFont().getSize()+5));
        spinComDate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        spinComDate.setFocusable(false);
        spinComDate.setMaximumSize(new java.awt.Dimension(31, 31));
        spinComDate.setName(""); // NOI18N
        spinComDate.setPreferredSize(new java.awt.Dimension(190, 32));
        spinComDate.setVerifyInputWhenFocusTarget(false);

        lblSerNo12.setFont(lblSerNo12.getFont().deriveFont(lblSerNo12.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo12.getFont().getSize()+5));
        lblSerNo12.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo12.setText("USB OVER CURRENT");
        lblSerNo12.setPreferredSize(new java.awt.Dimension(222, 30));

        spinUsbOverCurrent.setFont(spinUsbOverCurrent.getFont().deriveFont(spinUsbOverCurrent.getFont().getStyle() | java.awt.Font.BOLD, spinUsbOverCurrent.getFont().getSize()+6));
        spinUsbOverCurrent.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinUsbOverCurrent.setPreferredSize(new java.awt.Dimension(190, 32));

        lblSerNo13.setFont(lblSerNo13.getFont().deriveFont(lblSerNo13.getFont().getStyle() | java.awt.Font.BOLD, lblSerNo13.getFont().getSize()+5));
        lblSerNo13.setForeground(new java.awt.Color(0, 0, 102));
        lblSerNo13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSerNo13.setText("DEVICE RESET CNT");
        lblSerNo13.setPreferredSize(new java.awt.Dimension(222, 30));

        spinDevResetCnt.setFont(spinDevResetCnt.getFont().deriveFont(spinDevResetCnt.getFont().getStyle() | java.awt.Font.BOLD, spinDevResetCnt.getFont().getSize()+6));
        spinDevResetCnt.setModel(new javax.swing.SpinnerNumberModel(0, null, 100, 1));
        spinDevResetCnt.setPreferredSize(new java.awt.Dimension(190, 32));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSerNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTestDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTestDate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTestDate2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerNo13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinDevResetCnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinOverHeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinLowVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinOverVolt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbOverCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbInvalFileSys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbUnknown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbInvalid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGpsAntenna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGpsInvalid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGpsLost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinLowVoltReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinWatchdogReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinHwRev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinTestDate, javax.swing.GroupLayout.PREFERRED_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(txtSerialNo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(spinComDate, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinComDate, spinDevResetCnt, spinGpsAntenna, spinGpsInvalid, spinGpsLost, spinHwRev, spinLowVoltReset, spinLowVoltage, spinOverHeat, spinOverVolt, spinTestDate, spinUsbInvalFileSys, spinUsbInvalid, spinUsbOverCurrent, spinUsbUnknown, spinWatchdogReset, txtSerialNo});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSerialNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTestDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinTestDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTestDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinHwRev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblTestDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinComDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinWatchdogReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinLowVoltReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGpsLost, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGpsInvalid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinGpsAntenna, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbInvalid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbUnknown, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbInvalFileSys, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinUsbOverCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinOverVolt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinLowVoltage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinOverHeat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSerNo13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinDevResetCnt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {spinComDate, spinDevResetCnt, spinGpsAntenna, spinGpsInvalid, spinGpsLost, spinHwRev, spinLowVoltReset, spinLowVoltage, spinOverHeat, spinOverVolt, spinTestDate, spinUsbInvalFileSys, spinUsbInvalid, spinUsbOverCurrent, spinUsbUnknown, spinWatchdogReset, txtSerialNo});

        jScrollPane1.setViewportView(jPanel8);

        btnSave4.setBackground(new java.awt.Color(47, 49, 51));
        btnSave4.setFont(btnSave4.getFont().deriveFont(btnSave4.getFont().getStyle() | java.awt.Font.BOLD, btnSave4.getFont().getSize()+7));
        btnSave4.setForeground(new java.awt.Color(255, 255, 255));
        btnSave4.setText("SAVE");
        btnSave4.setAutoscrolls(true);
        btnSave4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave4ActionPerformed(evt);
            }
        });

        lblMsg3.setFont(lblMsg3.getFont().deriveFont(lblMsg3.getFont().getStyle() | java.awt.Font.BOLD, lblMsg3.getFont().getSize()+6));
        lblMsg3.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg3.setText("Saved");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(lblMsg3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave4)
                .addGap(3, 3, 3)
                .addComponent(lblMsg3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
        );

        jTabbedPane1.addTab("PID CODES", jPanel7);

        tanCamType.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tanCamType.setPreferredSize(new java.awt.Dimension(500, 500));

        btnSave5.setBackground(new java.awt.Color(47, 49, 51));
        btnSave5.setFont(btnSave5.getFont().deriveFont(btnSave5.getFont().getStyle() | java.awt.Font.BOLD, btnSave5.getFont().getSize()+7));
        btnSave5.setForeground(new java.awt.Color(255, 255, 255));
        btnSave5.setText("SAVE");
        btnSave5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave5.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave5ActionPerformed(evt);
            }
        });

        lblMsgCamera.setFont(lblMsgCamera.getFont().deriveFont(lblMsgCamera.getFont().getStyle() | java.awt.Font.BOLD, lblMsgCamera.getFont().getSize()+5));
        lblMsgCamera.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgCamera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgCamera.setText("Configuration Saved");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(370, 300));

        jPanel9.setPreferredSize(new java.awt.Dimension(370, 1200));
        jPanel9.setRequestFocusEnabled(false);
        jPanel9.setVerifyInputWhenFocusTarget(false);

        panCam3.setBackground(new java.awt.Color(204, 204, 204));
        panCam3.setBorder(javax.swing.BorderFactory.createTitledBorder("Camera3"));
        panCam3.setPreferredSize(new java.awt.Dimension(160, 150));

        chkCam3Enable.setBackground(new java.awt.Color(204, 204, 204));
        chkCam3Enable.setFont(chkCam3Enable.getFont().deriveFont(chkCam3Enable.getFont().getStyle() | java.awt.Font.BOLD, chkCam3Enable.getFont().getSize()+3));
        chkCam3Enable.setForeground(new java.awt.Color(0, 0, 102));
        chkCam3Enable.setText("Enable");
        chkCam3Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam3EnableActionPerformed(evt);
            }
        });

        pan3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 0, 102))); // NOI18N
        pan3.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getStyle() | java.awt.Font.BOLD, jLabel6.getFont().getSize()+2));
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Name");

        txtCam3User.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCam3User.setText("admin");
        txtCam3User.setPreferredSize(new java.awt.Dimension(83, 20));
        txtCam3User.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCam3UserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCam3UserFocusLost(evt);
            }
        });

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getStyle() | java.awt.Font.BOLD, jLabel7.getFont().getSize()+2));
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("pwd");

        PasswordCam3.setText("sumith123");
        PasswordCam3.setPreferredSize(new java.awt.Dimension(83, 20));
        PasswordCam3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordCam3FocusGained(evt);
            }
        });
        PasswordCam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordCam3ActionPerformed(evt);
            }
        });

        cmbCam3Type.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cmbCam3Type.setForeground(new java.awt.Color(0, 0, 102));
        cmbCam3Type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CP PLUS", "HIKVISION", "SPARSH", "FOORIR", "OTHER", "VICON" }));

        jLabel36.setFont(jLabel36.getFont().deriveFont(jLabel36.getFont().getStyle() | java.awt.Font.BOLD, jLabel36.getFont().getSize()+2));
        jLabel36.setForeground(new java.awt.Color(0, 0, 102));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Type");

        btnShowPassword3.setBackground(new java.awt.Color(153, 0, 0));
        btnShowPassword3.setBorderPainted(false);
        btnShowPassword3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassword3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan3Layout = new javax.swing.GroupLayout(pan3);
        pan3.setLayout(pan3Layout);
        pan3Layout.setHorizontalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan3Layout.createSequentialGroup()
                .addGroup(pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PasswordCam3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(pan3Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCam3Type, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGroup(pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCam3User, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pan3Layout.setVerticalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCam3Type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCam3User, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(PasswordCam3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panCam3Layout = new javax.swing.GroupLayout(panCam3);
        panCam3.setLayout(panCam3Layout);
        panCam3Layout.setHorizontalGroup(
            panCam3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam3Layout.createSequentialGroup()
                .addGroup(panCam3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCam3Enable)
                    .addComponent(pan3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panCam3Layout.setVerticalGroup(
            panCam3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam3Layout.createSequentialGroup()
                .addComponent(chkCam3Enable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panCam1.setBackground(new java.awt.Color(204, 204, 204));
        panCam1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Camera1"));
        panCam1.setPreferredSize(new java.awt.Dimension(160, 150));

        chkCam1Enable.setBackground(new java.awt.Color(204, 204, 204));
        chkCam1Enable.setFont(chkCam1Enable.getFont().deriveFont(chkCam1Enable.getFont().getStyle() | java.awt.Font.BOLD, chkCam1Enable.getFont().getSize()+3));
        chkCam1Enable.setForeground(new java.awt.Color(0, 0, 102));
        chkCam1Enable.setText("Enable");
        chkCam1Enable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                chkCam1EnableFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                chkCam1EnableFocusLost(evt);
            }
        });
        chkCam1Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam1EnableActionPerformed(evt);
            }
        });

        pan1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 0, 102))); // NOI18N
        pan1.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getStyle() | java.awt.Font.BOLD, jLabel8.getFont().getSize()+2));
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Name");

        txtCam1User.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCam1User.setText("admin");
        txtCam1User.setPreferredSize(new java.awt.Dimension(83, 20));
        txtCam1User.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCam1UserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCam1UserFocusLost(evt);
            }
        });
        txtCam1User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCam1UserActionPerformed(evt);
            }
        });

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getStyle() | java.awt.Font.BOLD, jLabel9.getFont().getSize()+2));
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("pwd");

        PasswordCam1.setText("sumith123");
        PasswordCam1.setPreferredSize(new java.awt.Dimension(83, 20));
        PasswordCam1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordCam1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordCam1FocusLost(evt);
            }
        });

        cmbCam1Type.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cmbCam1Type.setForeground(new java.awt.Color(0, 0, 102));
        cmbCam1Type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CP PLUS", "HIKVISION", "SPARSH", "FOORIR", "OTHER", "VICON" }));

        jLabel34.setFont(jLabel34.getFont().deriveFont(jLabel34.getFont().getStyle() | java.awt.Font.BOLD, jLabel34.getFont().getSize()+2));
        jLabel34.setForeground(new java.awt.Color(0, 0, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("Type");

        btnShowPassword1.setBackground(new java.awt.Color(153, 0, 0));
        btnShowPassword1.setBorderPainted(false);
        btnShowPassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassword1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan1Layout = new javax.swing.GroupLayout(pan1);
        pan1.setLayout(pan1Layout);
        pan1Layout.setHorizontalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan1Layout.createSequentialGroup()
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel34))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbCam1Type, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCam1User, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        pan1Layout.setVerticalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan1Layout.createSequentialGroup()
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCam1Type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(txtCam1User, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnShowPassword1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PasswordCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))))
        );

        javax.swing.GroupLayout panCam1Layout = new javax.swing.GroupLayout(panCam1);
        panCam1.setLayout(panCam1Layout);
        panCam1Layout.setHorizontalGroup(
            panCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam1Layout.createSequentialGroup()
                .addGroup(panCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCam1Enable)
                    .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panCam1Layout.setVerticalGroup(
            panCam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam1Layout.createSequentialGroup()
                .addComponent(chkCam1Enable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, Short.MAX_VALUE))
        );

        panCam4.setBackground(new java.awt.Color(204, 204, 204));
        panCam4.setBorder(javax.swing.BorderFactory.createTitledBorder("Camera4"));
        panCam4.setPreferredSize(new java.awt.Dimension(160, 150));

        chkCam4Enable.setBackground(new java.awt.Color(204, 204, 204));
        chkCam4Enable.setFont(chkCam4Enable.getFont().deriveFont(chkCam4Enable.getFont().getStyle() | java.awt.Font.BOLD, chkCam4Enable.getFont().getSize()+3));
        chkCam4Enable.setForeground(new java.awt.Color(0, 0, 102));
        chkCam4Enable.setText("Enable");
        chkCam4Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam4EnableActionPerformed(evt);
            }
        });

        pan4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 0, 102))); // NOI18N
        pan4.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel10.setFont(jLabel10.getFont().deriveFont(jLabel10.getFont().getStyle() | java.awt.Font.BOLD, jLabel10.getFont().getSize()+2));
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("Name");

        jLabel13.setFont(jLabel13.getFont().deriveFont(jLabel13.getFont().getStyle() | java.awt.Font.BOLD, jLabel13.getFont().getSize()+2));
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Pwd");
        jLabel13.setPreferredSize(new java.awt.Dimension(30, 16));

        txtCam4User.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCam4User.setText("admin");
        txtCam4User.setPreferredSize(new java.awt.Dimension(83, 20));
        txtCam4User.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCam4UserFocusGained(evt);
            }
        });

        PasswordCam4.setText("sumith123");
        PasswordCam4.setPreferredSize(new java.awt.Dimension(83, 20));
        PasswordCam4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordCam4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordCam4FocusLost(evt);
            }
        });

        cmbCam4Type.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cmbCam4Type.setForeground(new java.awt.Color(0, 0, 102));
        cmbCam4Type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CP PLUS", "HIKVISION", "SPARSH", "FOORIR", "OTHER", "VICON" }));

        jLabel37.setFont(jLabel37.getFont().deriveFont(jLabel37.getFont().getStyle() | java.awt.Font.BOLD, jLabel37.getFont().getSize()+2));
        jLabel37.setForeground(new java.awt.Color(0, 0, 102));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("Type");

        btnShowPassword4.setBackground(new java.awt.Color(153, 0, 0));
        btnShowPassword4.setBorderPainted(false);
        btnShowPassword4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassword4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan4Layout = new javax.swing.GroupLayout(pan4);
        pan4.setLayout(pan4Layout);
        pan4Layout.setHorizontalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan4Layout.createSequentialGroup()
                .addGroup(pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan4Layout.createSequentialGroup()
                        .addComponent(cmbCam4Type, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCam4User, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan4Layout.createSequentialGroup()
                        .addComponent(PasswordCam4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnShowPassword4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pan4Layout.setVerticalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan4Layout.createSequentialGroup()
                .addGroup(pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCam4Type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel10)
                    .addComponent(txtCam4User, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PasswordCam4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowPassword4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panCam4Layout = new javax.swing.GroupLayout(panCam4);
        panCam4.setLayout(panCam4Layout);
        panCam4Layout.setHorizontalGroup(
            panCam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam4Layout.createSequentialGroup()
                .addGroup(panCam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCam4Enable)
                    .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panCam4Layout.setVerticalGroup(
            panCam4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam4Layout.createSequentialGroup()
                .addComponent(chkCam4Enable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
        );

        panCam2.setBackground(new java.awt.Color(204, 204, 204));
        panCam2.setBorder(javax.swing.BorderFactory.createTitledBorder("Camera2"));
        panCam2.setPreferredSize(new java.awt.Dimension(160, 150));

        chkCam2Enable.setBackground(new java.awt.Color(204, 204, 204));
        chkCam2Enable.setFont(chkCam2Enable.getFont().deriveFont(chkCam2Enable.getFont().getStyle() | java.awt.Font.BOLD, chkCam2Enable.getFont().getSize()+3));
        chkCam2Enable.setForeground(new java.awt.Color(0, 0, 102));
        chkCam2Enable.setText("Enable");
        chkCam2Enable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam2EnableActionPerformed(evt);
            }
        });

        pan2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 0, 102))); // NOI18N
        pan2.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel14.setFont(jLabel14.getFont().deriveFont(jLabel14.getFont().getStyle() | java.awt.Font.BOLD, jLabel14.getFont().getSize()+2));
        jLabel14.setForeground(new java.awt.Color(0, 0, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Name");

        txtCam2User.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtCam2User.setText("admin");
        txtCam2User.setPreferredSize(new java.awt.Dimension(83, 20));
        txtCam2User.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCam2UserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCam2UserFocusLost(evt);
            }
        });

        jLabel17.setFont(jLabel17.getFont().deriveFont(jLabel17.getFont().getStyle() | java.awt.Font.BOLD, jLabel17.getFont().getSize()+2));
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("pwd");

        PasswordCam2.setText("sumith123");
        PasswordCam2.setPreferredSize(new java.awt.Dimension(83, 20));
        PasswordCam2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordCam2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordCam2FocusLost(evt);
            }
        });

        cmbCam2Type.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cmbCam2Type.setForeground(new java.awt.Color(0, 0, 102));
        cmbCam2Type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CP PLUS", "HIKVISION", "SPARSH", "FOORIR", "OTHER", "VICON" }));

        jLabel35.setFont(jLabel35.getFont().deriveFont(jLabel35.getFont().getStyle() | java.awt.Font.BOLD, jLabel35.getFont().getSize()+2));
        jLabel35.setForeground(new java.awt.Color(0, 0, 102));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Type");

        btnShowPassword2.setBackground(new java.awt.Color(153, 0, 0));
        btnShowPassword2.setBorderPainted(false);
        btnShowPassword2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassword2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan2Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCam2Type, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCam2User, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(pan2Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(14, 14, 14)
                .addComponent(PasswordCam2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnShowPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan2Layout.createSequentialGroup()
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCam2Type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCam2User, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(PasswordCam2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panCam2Layout = new javax.swing.GroupLayout(panCam2);
        panCam2.setLayout(panCam2Layout);
        panCam2Layout.setHorizontalGroup(
            panCam2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam2Layout.createSequentialGroup()
                .addGroup(panCam2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCam2Enable)
                    .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panCam2Layout.setVerticalGroup(
            panCam2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCam2Layout.createSequentialGroup()
                .addComponent(chkCam2Enable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panCam1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
            .addComponent(panCam2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
            .addComponent(panCam3, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
            .addComponent(panCam4, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(panCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panCam2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panCam3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panCam4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(629, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel9);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setText("Number of Cameras");

        cmbSelectCameraNo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cmbSelectCameraNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        cmbSelectCameraNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSelectCameraNoItemStateChanged(evt);
            }
        });
        cmbSelectCameraNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectCameraNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tanCamTypeLayout = new javax.swing.GroupLayout(tanCamType);
        tanCamType.setLayout(tanCamTypeLayout);
        tanCamTypeLayout.setHorizontalGroup(
            tanCamTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tanCamTypeLayout.createSequentialGroup()
                .addGroup(tanCamTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tanCamTypeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tanCamTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tanCamTypeLayout.createSequentialGroup()
                                .addComponent(btnSave5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMsgCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tanCamTypeLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSelectCameraNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        tanCamTypeLayout.setVerticalGroup(
            tanCamTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tanCamTypeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(tanCamTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cmbSelectCameraNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tanCamTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tanCamTypeLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblMsgCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tanCamTypeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("CAMERA TYPE", tanCamType);

        tabSnapshot.setPreferredSize(new java.awt.Dimension(500, 600));

        btnSaveSnapshot.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveSnapshot.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSaveSnapshot.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveSnapshot.setText("SAVE");
        btnSaveSnapshot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveSnapshot.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSaveSnapshot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSnapshotActionPerformed(evt);
            }
        });

        lblMsgSnapshot.setFont(lblMsgSnapshot.getFont().deriveFont(lblMsgSnapshot.getFont().getStyle() | java.awt.Font.BOLD, lblMsgSnapshot.getFont().getSize()+5));
        lblMsgSnapshot.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgSnapshot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgSnapshot.setText("Saved");

        btnGrpSnapshot.add(radCam1);
        radCam1.setFont(radCam1.getFont().deriveFont(radCam1.getFont().getStyle() | java.awt.Font.BOLD, radCam1.getFont().getSize()+5));
        radCam1.setForeground(new java.awt.Color(0, 0, 102));
        radCam1.setText("Cam1");
        radCam1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radCam1ItemStateChanged(evt);
            }
        });
        radCam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam1ActionPerformed(evt);
            }
        });

        btnGrpSnapshot.add(radCam2);
        radCam2.setFont(radCam2.getFont().deriveFont(radCam2.getFont().getStyle() | java.awt.Font.BOLD, radCam2.getFont().getSize()+5));
        radCam2.setForeground(new java.awt.Color(0, 0, 102));
        radCam2.setText("Cam2");
        radCam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam2ActionPerformed(evt);
            }
        });

        btnGrpSnapshot.add(radCam3);
        radCam3.setFont(radCam3.getFont().deriveFont(radCam3.getFont().getStyle() | java.awt.Font.BOLD, radCam3.getFont().getSize()+5));
        radCam3.setForeground(new java.awt.Color(0, 0, 102));
        radCam3.setText("Cam3");
        radCam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam3ActionPerformed(evt);
            }
        });

        btnGrpSnapshot.add(radCam4);
        radCam4.setFont(radCam4.getFont().deriveFont(radCam4.getFont().getStyle() | java.awt.Font.BOLD, radCam4.getFont().getSize()+5));
        radCam4.setForeground(new java.awt.Color(0, 0, 102));
        radCam4.setText("Cam4");
        radCam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam4ActionPerformed(evt);
            }
        });

        btnRefreshSnapshot.setBackground(new java.awt.Color(47, 49, 51));
        btnRefreshSnapshot.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRefreshSnapshot.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshSnapshot.setText("Refresh");
        btnRefreshSnapshot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefreshSnapshot.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefreshSnapshot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshSnapshotActionPerformed(evt);
            }
        });

        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setVerifyInputWhenFocusTarget(false);

        panSnap.setBackground(new java.awt.Color(204, 204, 204));
        panSnap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SNAP SHOT", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 0, 51))); // NOI18N
        panSnap.setPreferredSize(new java.awt.Dimension(490, 220));

        chkSnapShotEnable.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapShotEnable.setFont(chkSnapShotEnable.getFont().deriveFont(chkSnapShotEnable.getFont().getStyle() | java.awt.Font.BOLD, chkSnapShotEnable.getFont().getSize()+5));
        chkSnapShotEnable.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapShotEnable.setText("Enable");
        chkSnapShotEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapShotEnableActionPerformed(evt);
            }
        });

        panSnapCont.setBackground(new java.awt.Color(204, 204, 204));
        panSnapCont.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Continuous", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panSnapCont.setPreferredSize(new java.awt.Dimension(120, 210));

        lblObuId8.setFont(lblObuId8.getFont().deriveFont(lblObuId8.getFont().getStyle() | java.awt.Font.BOLD));
        lblObuId8.setForeground(new java.awt.Color(0, 0, 102));
        lblObuId8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblObuId8.setText("Resolution");

        cmbSnapContStreamType.setFont(cmbSnapContStreamType.getFont().deriveFont(cmbSnapContStreamType.getFont().getStyle() | java.awt.Font.BOLD, cmbSnapContStreamType.getFont().getSize()+2));
        cmbSnapContStreamType.setForeground(new java.awt.Color(102, 0, 0));
        cmbSnapContStreamType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbSnapContStreamType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSnapContStreamTypeItemStateChanged(evt);
            }
        });
        cmbSnapContStreamType.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSnapContStreamTypeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSnapContStreamTypeFocusLost(evt);
            }
        });
        cmbSnapContStreamType.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapContStreamTypeCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapContStreamTypeInputMethodTextChanged(evt);
            }
        });
        cmbSnapContStreamType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSnapContStreamTypeActionPerformed(evt);
            }
        });
        cmbSnapContStreamType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbSnapContStreamTypePropertyChange(evt);
            }
        });
        cmbSnapContStreamType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSnapContStreamTypeKeyReleased(evt);
            }
        });

        lblObuId9.setFont(lblObuId9.getFont().deriveFont(lblObuId9.getFont().getStyle() | java.awt.Font.BOLD));
        lblObuId9.setForeground(new java.awt.Color(0, 0, 102));
        lblObuId9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblObuId9.setText("Interval ");
        lblObuId9.setPreferredSize(new java.awt.Dimension(65, 17));

        spinSnapContInterval.setFont(spinSnapContInterval.getFont().deriveFont(spinSnapContInterval.getFont().getStyle() | java.awt.Font.BOLD, spinSnapContInterval.getFont().getSize()+2));
        spinSnapContInterval.setModel(new javax.swing.SpinnerNumberModel(15, 0, 500, 1));

        jLabel26.setFont(jLabel26.getFont().deriveFont(jLabel26.getFont().getStyle() | java.awt.Font.BOLD, jLabel26.getFont().getSize()+1));
        jLabel26.setForeground(new java.awt.Color(0, 0, 51));
        jLabel26.setText("(m)");

        chkSnapContUpload.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapContUpload.setFont(chkSnapContUpload.getFont().deriveFont(chkSnapContUpload.getFont().getStyle() | java.awt.Font.BOLD, chkSnapContUpload.getFont().getSize()+4));
        chkSnapContUpload.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapContUpload.setText("FTP");
        chkSnapContUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapContUploadActionPerformed(evt);
            }
        });

        chkSnapCont.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapCont.setFont(chkSnapCont.getFont().deriveFont(chkSnapCont.getFont().getStyle() | java.awt.Font.BOLD, chkSnapCont.getFont().getSize()+5));
        chkSnapCont.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapCont.setText("Enable");
        chkSnapCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapContActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panSnapContLayout = new javax.swing.GroupLayout(panSnapCont);
        panSnapCont.setLayout(panSnapContLayout);
        panSnapContLayout.setHorizontalGroup(
            panSnapContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSnapContLayout.createSequentialGroup()
                .addGroup(panSnapContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSnapContLayout.createSequentialGroup()
                        .addComponent(cmbSnapContStreamType, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinSnapContInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26))
                    .addGroup(panSnapContLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(chkSnapCont)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSnapContUpload))
                    .addGroup(panSnapContLayout.createSequentialGroup()
                        .addComponent(lblObuId8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblObuId9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panSnapContLayout.setVerticalGroup(
            panSnapContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSnapContLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panSnapContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSnapCont, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSnapContUpload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panSnapContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObuId8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObuId9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panSnapContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSnapContStreamType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinSnapContInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        panSnapEvent.setBackground(new java.awt.Color(204, 204, 204));
        panSnapEvent.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Event", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        panSnapEvent.setPreferredSize(new java.awt.Dimension(234, 210));

        cmbSnapEventStreamDig1.setFont(cmbSnapEventStreamDig1.getFont().deriveFont(cmbSnapEventStreamDig1.getFont().getStyle() | java.awt.Font.BOLD, cmbSnapEventStreamDig1.getFont().getSize()+2));
        cmbSnapEventStreamDig1.setForeground(new java.awt.Color(102, 0, 0));
        cmbSnapEventStreamDig1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbSnapEventStreamDig1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSnapEventStreamDig1ItemStateChanged(evt);
            }
        });
        cmbSnapEventStreamDig1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig1FocusLost(evt);
            }
        });
        cmbSnapEventStreamDig1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig1InputMethodTextChanged(evt);
            }
        });
        cmbSnapEventStreamDig1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSnapEventStreamDig1ActionPerformed(evt);
            }
        });
        cmbSnapEventStreamDig1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbSnapEventStreamDig1PropertyChange(evt);
            }
        });
        cmbSnapEventStreamDig1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSnapEventStreamDig1KeyReleased(evt);
            }
        });

        chkSnapDig4.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDig4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDig4.setForeground(new java.awt.Color(51, 0, 0));
        chkSnapDig4.setText("DI4");
        chkSnapDig4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDig4ActionPerformed(evt);
            }
        });

        lblObuId2.setFont(lblObuId2.getFont().deriveFont(lblObuId2.getFont().getStyle() | java.awt.Font.BOLD, lblObuId2.getFont().getSize()+2));
        lblObuId2.setForeground(new java.awt.Color(0, 0, 102));
        lblObuId2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId2.setText("Snap cnt");

        lblObuId1.setFont(lblObuId1.getFont().deriveFont(lblObuId1.getFont().getStyle() | java.awt.Font.BOLD, lblObuId1.getFont().getSize()+2));
        lblObuId1.setForeground(new java.awt.Color(0, 0, 102));
        lblObuId1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId1.setText("Res");

        spinSnapEventntervalDig1.setFont(spinSnapEventntervalDig1.getFont().deriveFont(spinSnapEventntervalDig1.getFont().getStyle() | java.awt.Font.BOLD, spinSnapEventntervalDig1.getFont().getSize()+2));
        spinSnapEventntervalDig1.setModel(new javax.swing.SpinnerNumberModel(5, 0, 100, 1));

        chkSnapDig2.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDig2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDig2.setForeground(new java.awt.Color(51, 0, 0));
        chkSnapDig2.setText("DI2");
        chkSnapDig2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDig2ActionPerformed(evt);
            }
        });

        chkSnapDig3.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDig3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDig3.setForeground(new java.awt.Color(51, 0, 0));
        chkSnapDig3.setText("DI3");
        chkSnapDig3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDig3ActionPerformed(evt);
            }
        });

        chkSnapDig1.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDig1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDig1.setForeground(new java.awt.Color(51, 0, 0));
        chkSnapDig1.setText("DI1");
        chkSnapDig1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDig1ActionPerformed(evt);
            }
        });

        cmbSnapEventStreamDig2.setFont(cmbSnapEventStreamDig2.getFont().deriveFont(cmbSnapEventStreamDig2.getFont().getStyle() | java.awt.Font.BOLD, cmbSnapEventStreamDig2.getFont().getSize()+2));
        cmbSnapEventStreamDig2.setForeground(new java.awt.Color(102, 0, 0));
        cmbSnapEventStreamDig2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbSnapEventStreamDig2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSnapEventStreamDig2ItemStateChanged(evt);
            }
        });
        cmbSnapEventStreamDig2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig2FocusLost(evt);
            }
        });
        cmbSnapEventStreamDig2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig2CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig2InputMethodTextChanged(evt);
            }
        });
        cmbSnapEventStreamDig2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSnapEventStreamDig2ActionPerformed(evt);
            }
        });
        cmbSnapEventStreamDig2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbSnapEventStreamDig2PropertyChange(evt);
            }
        });
        cmbSnapEventStreamDig2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSnapEventStreamDig2KeyReleased(evt);
            }
        });

        spinSnapEventntervalDig2.setFont(spinSnapEventntervalDig2.getFont().deriveFont(spinSnapEventntervalDig2.getFont().getStyle() | java.awt.Font.BOLD, spinSnapEventntervalDig2.getFont().getSize()+2));
        spinSnapEventntervalDig2.setModel(new javax.swing.SpinnerNumberModel(5, 0, 100, 1));

        cmbSnapEventStreamDig3.setFont(cmbSnapEventStreamDig3.getFont().deriveFont(cmbSnapEventStreamDig3.getFont().getStyle() | java.awt.Font.BOLD, cmbSnapEventStreamDig3.getFont().getSize()+2));
        cmbSnapEventStreamDig3.setForeground(new java.awt.Color(102, 0, 0));
        cmbSnapEventStreamDig3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbSnapEventStreamDig3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSnapEventStreamDig3ItemStateChanged(evt);
            }
        });
        cmbSnapEventStreamDig3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig3FocusLost(evt);
            }
        });
        cmbSnapEventStreamDig3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig3CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig3InputMethodTextChanged(evt);
            }
        });
        cmbSnapEventStreamDig3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSnapEventStreamDig3ActionPerformed(evt);
            }
        });
        cmbSnapEventStreamDig3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbSnapEventStreamDig3PropertyChange(evt);
            }
        });
        cmbSnapEventStreamDig3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSnapEventStreamDig3KeyReleased(evt);
            }
        });

        spinSnapEventntervalDig3.setFont(spinSnapEventntervalDig3.getFont().deriveFont(spinSnapEventntervalDig3.getFont().getStyle() | java.awt.Font.BOLD, spinSnapEventntervalDig3.getFont().getSize()+2));
        spinSnapEventntervalDig3.setModel(new javax.swing.SpinnerNumberModel(5, 0, 100, 1));

        cmbSnapEventStreamDig4.setFont(cmbSnapEventStreamDig4.getFont().deriveFont(cmbSnapEventStreamDig4.getFont().getStyle() | java.awt.Font.BOLD, cmbSnapEventStreamDig4.getFont().getSize()+2));
        cmbSnapEventStreamDig4.setForeground(new java.awt.Color(102, 0, 0));
        cmbSnapEventStreamDig4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbSnapEventStreamDig4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSnapEventStreamDig4ItemStateChanged(evt);
            }
        });
        cmbSnapEventStreamDig4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbSnapEventStreamDig4FocusLost(evt);
            }
        });
        cmbSnapEventStreamDig4.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig4CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbSnapEventStreamDig4InputMethodTextChanged(evt);
            }
        });
        cmbSnapEventStreamDig4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSnapEventStreamDig4ActionPerformed(evt);
            }
        });
        cmbSnapEventStreamDig4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbSnapEventStreamDig4PropertyChange(evt);
            }
        });
        cmbSnapEventStreamDig4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbSnapEventStreamDig4KeyReleased(evt);
            }
        });

        spinSnapEventntervalDig4.setFont(spinSnapEventntervalDig4.getFont().deriveFont(spinSnapEventntervalDig4.getFont().getStyle() | java.awt.Font.BOLD, spinSnapEventntervalDig4.getFont().getSize()+2));
        spinSnapEventntervalDig4.setModel(new javax.swing.SpinnerNumberModel(5, 0, 100, 1));

        chkSnapDi1Upload.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDi1Upload.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDi1Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapDi1Upload.setText("FTP");
        chkSnapDi1Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDi1UploadActionPerformed(evt);
            }
        });

        chkSnapDi3Upload.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDi3Upload.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDi3Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapDi3Upload.setText("FTP");
        chkSnapDi3Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDi3UploadActionPerformed(evt);
            }
        });

        chkSnapDi4Upload.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDi4Upload.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDi4Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapDi4Upload.setText("FTP");
        chkSnapDi4Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDi4UploadActionPerformed(evt);
            }
        });

        chkSnapDi2Upload.setBackground(new java.awt.Color(204, 204, 204));
        chkSnapDi2Upload.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        chkSnapDi2Upload.setForeground(new java.awt.Color(0, 0, 51));
        chkSnapDi2Upload.setText("FTP");
        chkSnapDi2Upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSnapDi2UploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panSnapEventLayout = new javax.swing.GroupLayout(panSnapEvent);
        panSnapEvent.setLayout(panSnapEventLayout);
        panSnapEventLayout.setHorizontalGroup(
            panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSnapEventLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkSnapDig1)
                        .addComponent(chkSnapDig2)
                        .addComponent(chkSnapDig3))
                    .addComponent(chkSnapDig4))
                .addGap(6, 6, 6)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSnapEventLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblObuId1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblObuId2))
                    .addGroup(panSnapEventLayout.createSequentialGroup()
                        .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSnapEventStreamDig1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSnapEventStreamDig2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSnapEventStreamDig3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSnapEventStreamDig4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinSnapEventntervalDig1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinSnapEventntervalDig2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinSnapEventntervalDig3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinSnapEventntervalDig4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSnapEventLayout.createSequentialGroup()
                        .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(chkSnapDi2Upload)
                                .addComponent(chkSnapDi3Upload))
                            .addComponent(chkSnapDi4Upload))
                        .addContainerGap())
                    .addComponent(chkSnapDi1Upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panSnapEventLayout.setVerticalGroup(
            panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSnapEventLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObuId1)
                    .addComponent(lblObuId2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkSnapDig1)
                    .addComponent(cmbSnapEventStreamDig1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSnapDi1Upload, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinSnapEventntervalDig1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkSnapDig2)
                    .addComponent(chkSnapDi2Upload)
                    .addComponent(spinSnapEventntervalDig2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSnapEventStreamDig2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkSnapDig3)
                    .addComponent(cmbSnapEventStreamDig3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSnapDi3Upload)
                    .addComponent(spinSnapEventntervalDig3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkSnapDi4Upload)
                    .addGroup(panSnapEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbSnapEventStreamDig4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinSnapEventntervalDig4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkSnapDig4))))
        );

        jScrollPane8.setViewportView(panSnapEvent);

        javax.swing.GroupLayout panSnapLayout = new javax.swing.GroupLayout(panSnap);
        panSnap.setLayout(panSnapLayout);
        panSnapLayout.setHorizontalGroup(
            panSnapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSnapLayout.createSequentialGroup()
                .addGroup(panSnapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSnapLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chkSnapShotEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panSnapLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panSnapCont, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        panSnapLayout.setVerticalGroup(
            panSnapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSnapLayout.createSequentialGroup()
                .addComponent(chkSnapShotEnable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panSnapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panSnapCont, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(panSnap);

        javax.swing.GroupLayout tabSnapshotLayout = new javax.swing.GroupLayout(tabSnapshot);
        tabSnapshot.setLayout(tabSnapshotLayout);
        tabSnapshotLayout.setHorizontalGroup(
            tabSnapshotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSnapshotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSaveSnapshot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefreshSnapshot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblMsgSnapshot, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(tabSnapshotLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tabSnapshotLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(radCam1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radCam2)
                .addGap(59, 59, 59)
                .addComponent(radCam3)
                .addGap(63, 63, 63)
                .addComponent(radCam4)
                .addGap(51, 51, 51))
        );
        tabSnapshotLayout.setVerticalGroup(
            tabSnapshotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSnapshotLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(tabSnapshotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radCam4)
                    .addComponent(radCam3)
                    .addComponent(radCam2)
                    .addComponent(radCam1))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabSnapshotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveSnapshot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshSnapshot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsgSnapshot))
                .addGap(471, 471, 471))
        );

        jTabbedPane2.addTab("SNAPSHOT", tabSnapshot);

        TabVideoEncoding.setPreferredSize(new java.awt.Dimension(500, 500));

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(400, 355));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setPreferredSize(new java.awt.Dimension(471, 500));

        lblObuId.setFont(lblObuId.getFont().deriveFont(lblObuId.getFont().getStyle() | java.awt.Font.BOLD, lblObuId.getFont().getSize()+5));
        lblObuId.setForeground(new java.awt.Color(0, 0, 102));
        lblObuId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId.setText("Stream Type");
        lblObuId.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbStreamType.setFont(cmbStreamType.getFont().deriveFont(cmbStreamType.getFont().getStyle() | java.awt.Font.BOLD, cmbStreamType.getFont().getSize()+5));
        cmbStreamType.setForeground(new java.awt.Color(102, 0, 0));
        cmbStreamType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Main Stream", "Sub Stream" }));
        cmbStreamType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStreamTypeItemStateChanged(evt);
            }
        });
        cmbStreamType.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbStreamTypeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbStreamTypeFocusLost(evt);
            }
        });
        cmbStreamType.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbStreamTypeCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbStreamTypeInputMethodTextChanged(evt);
            }
        });
        cmbStreamType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStreamTypeActionPerformed(evt);
            }
        });
        cmbStreamType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbStreamTypePropertyChange(evt);
            }
        });
        cmbStreamType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbStreamTypeKeyReleased(evt);
            }
        });

        lblvideoaudio.setFont(lblvideoaudio.getFont().deriveFont(lblvideoaudio.getFont().getStyle() | java.awt.Font.BOLD, lblvideoaudio.getFont().getSize()+5));
        lblvideoaudio.setForeground(new java.awt.Color(0, 0, 102));
        lblvideoaudio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblvideoaudio.setText("Video/Audio Mode");
        lblvideoaudio.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbVideomode.setFont(cmbVideomode.getFont().deriveFont(cmbVideomode.getFont().getStyle() | java.awt.Font.BOLD, cmbVideomode.getFont().getSize()+5));
        cmbVideomode.setForeground(new java.awt.Color(102, 0, 0));
        cmbVideomode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Video Stream", "Audio Stream" }));
        cmbVideomode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVideomodeActionPerformed(evt);
            }
        });

        lblresoulation.setFont(lblresoulation.getFont().deriveFont(lblresoulation.getFont().getStyle() | java.awt.Font.BOLD, lblresoulation.getFont().getSize()+5));
        lblresoulation.setForeground(new java.awt.Color(0, 0, 102));
        lblresoulation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblresoulation.setText("Resolution");
        lblresoulation.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbResolution.setFont(cmbResolution.getFont().deriveFont(cmbResolution.getFont().getStyle() | java.awt.Font.BOLD, cmbResolution.getFont().getSize()+5));
        cmbResolution.setForeground(new java.awt.Color(102, 0, 0));
        cmbResolution.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low", "Below Normal", "Medium", "Above Normal", "High" }));
        cmbResolution.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbResolutionFocusGained(evt);
            }
        });
        cmbResolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResolutionActionPerformed(evt);
            }
        });

        lblbitrate.setFont(lblbitrate.getFont().deriveFont(lblbitrate.getFont().getStyle() | java.awt.Font.BOLD, lblbitrate.getFont().getSize()+5));
        lblbitrate.setForeground(new java.awt.Color(0, 0, 102));
        lblbitrate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblbitrate.setText("BitRate Type");
        lblbitrate.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbBitrateType.setFont(cmbBitrateType.getFont().deriveFont(cmbBitrateType.getFont().getStyle() | java.awt.Font.BOLD, cmbBitrateType.getFont().getSize()+5));
        cmbBitrateType.setForeground(new java.awt.Color(102, 0, 0));
        cmbBitrateType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VBR", "CBR" }));

        lblvideoquality.setFont(lblvideoquality.getFont().deriveFont(lblvideoquality.getFont().getStyle() | java.awt.Font.BOLD, lblvideoquality.getFont().getSize()+5));
        lblvideoquality.setForeground(new java.awt.Color(0, 0, 102));
        lblvideoquality.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblvideoquality.setText("Video Quality");
        lblvideoquality.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbVideoquality.setFont(cmbVideoquality.getFont().deriveFont(cmbVideoquality.getFont().getStyle() | java.awt.Font.BOLD, cmbVideoquality.getFont().getSize()+5));
        cmbVideoquality.setForeground(new java.awt.Color(102, 0, 0));
        cmbVideoquality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));

        lblframerate.setFont(lblframerate.getFont().deriveFont(lblframerate.getFont().getStyle() | java.awt.Font.BOLD, lblframerate.getFont().getSize()+5));
        lblframerate.setForeground(new java.awt.Color(0, 0, 102));
        lblframerate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblframerate.setText("Frame Rate");
        lblframerate.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbMaxbitrate.setFont(cmbMaxbitrate.getFont().deriveFont(cmbMaxbitrate.getFont().getStyle() | java.awt.Font.BOLD, cmbMaxbitrate.getFont().getSize()+5));
        cmbMaxbitrate.setForeground(new java.awt.Color(102, 0, 0));
        cmbMaxbitrate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "256", "512", "1024", "1536", "2048" }));

        cmbFramerate.setFont(cmbFramerate.getFont().deriveFont(cmbFramerate.getFont().getStyle() | java.awt.Font.BOLD, cmbFramerate.getFont().getSize()+5));
        cmbFramerate.setForeground(new java.awt.Color(102, 0, 0));
        cmbFramerate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));

        lblvideoencode.setFont(lblvideoencode.getFont().deriveFont(lblvideoencode.getFont().getStyle() | java.awt.Font.BOLD, lblvideoencode.getFont().getSize()+5));
        lblvideoencode.setForeground(new java.awt.Color(0, 0, 102));
        lblvideoencode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblvideoencode.setText("Video Encoding");
        lblvideoencode.setPreferredSize(new java.awt.Dimension(160, 32));

        lblmaxbit.setFont(lblmaxbit.getFont().deriveFont(lblmaxbit.getFont().getStyle() | java.awt.Font.BOLD, lblmaxbit.getFont().getSize()+5));
        lblmaxbit.setForeground(new java.awt.Color(0, 0, 102));
        lblmaxbit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblmaxbit.setText(" Bitrate");
        lblmaxbit.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbVidEncoding.setFont(cmbVidEncoding.getFont().deriveFont(cmbVidEncoding.getFont().getStyle() | java.awt.Font.BOLD, cmbVidEncoding.getFont().getSize()+5));
        cmbVidEncoding.setForeground(new java.awt.Color(102, 0, 0));
        cmbVidEncoding.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "H.264", "MPEG" }));
        cmbVidEncoding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVidEncodingActionPerformed(evt);
            }
        });

        lbselectcamera.setFont(lbselectcamera.getFont().deriveFont(lbselectcamera.getFont().getStyle() | java.awt.Font.BOLD, lbselectcamera.getFont().getSize()+5));
        lbselectcamera.setForeground(new java.awt.Color(0, 0, 102));
        lbselectcamera.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbselectcamera.setText("Select Camera");
        lbselectcamera.setPreferredSize(new java.awt.Dimension(160, 32));

        cmbSelectCameraEncode.setFont(cmbSelectCameraEncode.getFont().deriveFont(cmbSelectCameraEncode.getFont().getStyle() | java.awt.Font.BOLD, cmbSelectCameraEncode.getFont().getSize()+5));
        cmbSelectCameraEncode.setForeground(new java.awt.Color(102, 0, 0));
        cmbSelectCameraEncode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cam1", "Cam2", "Cam3", "Cam4" }));
        cmbSelectCameraEncode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSelectCameraEncodeItemStateChanged(evt);
            }
        });
        cmbSelectCameraEncode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSelectCameraEncodeFocusGained(evt);
            }
        });
        cmbSelectCameraEncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectCameraEncodeActionPerformed(evt);
            }
        });

        lblframerate1.setFont(lblframerate1.getFont().deriveFont(lblframerate1.getFont().getStyle() | java.awt.Font.BOLD, lblframerate1.getFont().getSize()+5));
        lblframerate1.setForeground(new java.awt.Color(0, 0, 102));
        lblframerate1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblframerate1.setText("Frame Rate Interval");
        lblframerate1.setPreferredSize(new java.awt.Dimension(180, 32));

        spinFrameInterval.setFont(spinFrameInterval.getFont().deriveFont(spinFrameInterval.getFont().getStyle() | java.awt.Font.BOLD, spinFrameInterval.getFont().getSize()+5));
        spinFrameInterval.setModel(new javax.swing.SpinnerNumberModel(50, 19, 150, 1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbselectcamera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObuId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvideoaudio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblresoulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbitrate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvideoquality, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblframerate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblmaxbit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvideoencode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblframerate1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbSelectCameraEncode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbStreamType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbVideomode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbResolution, 0, 1, Short.MAX_VALUE)
                    .addComponent(cmbBitrateType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbVideoquality, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbFramerate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMaxbitrate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbVidEncoding, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spinFrameInterval))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbselectcamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSelectCameraEncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObuId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStreamType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvideoaudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVideomode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResolution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblresoulation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblbitrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBitrateType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvideoquality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVideoquality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblframerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFramerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaxbit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMaxbitrate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblvideoencode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVidEncoding, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblframerate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinFrameInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jScrollPane3.setViewportView(jPanel10);

        lblMsgEncode.setFont(lblMsgEncode.getFont().deriveFont(lblMsgEncode.getFont().getStyle() | java.awt.Font.BOLD, lblMsgEncode.getFont().getSize()+7));
        lblMsgEncode.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgEncode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgEncode.setText("Saved");

        btnSaveEncode.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveEncode.setFont(btnSaveEncode.getFont().deriveFont(btnSaveEncode.getFont().getStyle() | java.awt.Font.BOLD, btnSaveEncode.getFont().getSize()+7));
        btnSaveEncode.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveEncode.setText("SAVE");
        btnSaveEncode.setAutoscrolls(true);
        btnSaveEncode.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveEncode.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaveEncode.setPreferredSize(new java.awt.Dimension(100, 20));
        btnSaveEncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEncodeActionPerformed(evt);
            }
        });

        btnRefresh1.setBackground(new java.awt.Color(47, 49, 51));
        btnRefresh1.setFont(btnRefresh1.getFont().deriveFont(btnRefresh1.getFont().getStyle() | java.awt.Font.BOLD, btnRefresh1.getFont().getSize()+7));
        btnRefresh1.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh1.setText("Refresh");
        btnRefresh1.setAutoscrolls(true);
        btnRefresh1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefresh1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh1.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        pbarEncode.setMaximum(5);
        pbarEncode.setValue(1);
        pbarEncode.setPreferredSize(new java.awt.Dimension(146, 20));

        javax.swing.GroupLayout TabVideoEncodingLayout = new javax.swing.GroupLayout(TabVideoEncoding);
        TabVideoEncoding.setLayout(TabVideoEncodingLayout);
        TabVideoEncodingLayout.setHorizontalGroup(
            TabVideoEncodingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabVideoEncodingLayout.createSequentialGroup()
                .addGroup(TabVideoEncodingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbarEncode, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TabVideoEncodingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSaveEncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblMsgEncode, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TabVideoEncodingLayout.setVerticalGroup(
            TabVideoEncodingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabVideoEncodingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbarEncode, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TabVideoEncodingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveEncode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131)
                .addComponent(lblMsgEncode)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("ENCODING", TabVideoEncoding);

        tabCamBrightness.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCamBrightness.setPreferredSize(new java.awt.Dimension(471, 500));

        lbselectcamera1.setFont(lbselectcamera1.getFont().deriveFont(lbselectcamera1.getFont().getStyle() | java.awt.Font.BOLD, lbselectcamera1.getFont().getSize()+7));
        lbselectcamera1.setForeground(new java.awt.Color(0, 0, 102));
        lbselectcamera1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbselectcamera1.setText("Select Camera");

        cmbSelectCameraBrightness.setFont(cmbSelectCameraBrightness.getFont().deriveFont(cmbSelectCameraBrightness.getFont().getStyle() | java.awt.Font.BOLD, cmbSelectCameraBrightness.getFont().getSize()+5));
        cmbSelectCameraBrightness.setForeground(new java.awt.Color(0, 0, 102));
        cmbSelectCameraBrightness.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cam1", "Cam2", "Cam3", "Cam4" }));
        cmbSelectCameraBrightness.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSelectCameraBrightnessItemStateChanged(evt);
            }
        });
        cmbSelectCameraBrightness.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSelectCameraBrightnessFocusGained(evt);
            }
        });
        cmbSelectCameraBrightness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectCameraBrightnessActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblcontrast1.setFont(lblcontrast1.getFont().deriveFont(lblcontrast1.getFont().getStyle() | java.awt.Font.BOLD, lblcontrast1.getFont().getSize()+5));
        lblcontrast1.setForeground(new java.awt.Color(0, 0, 102));
        lblcontrast1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblcontrast1.setText("Contrast");
        lblcontrast1.setPreferredSize(new java.awt.Dimension(110, 36));

        sliderSaturationLevel1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderSaturationLevel1StateChanged(evt);
            }
        });

        sliderBrightness.setToolTipText("");
        sliderBrightness.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderBrightnessStateChanged(evt);
            }
        });

        lblBrihtness.setFont(lblBrihtness.getFont().deriveFont(lblBrihtness.getFont().getStyle() | java.awt.Font.BOLD, lblBrihtness.getFont().getSize()+5));
        lblBrihtness.setForeground(new java.awt.Color(0, 0, 102));
        lblBrihtness.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBrihtness.setText("Brightness");
        lblBrihtness.setPreferredSize(new java.awt.Dimension(110, 36));

        lblSaturationLevel1.setFont(lblSaturationLevel1.getFont().deriveFont(lblSaturationLevel1.getFont().getStyle() | java.awt.Font.BOLD, lblSaturationLevel1.getFont().getSize()+5));
        lblSaturationLevel1.setForeground(new java.awt.Color(0, 0, 102));
        lblSaturationLevel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaturationLevel1.setText("Saturation");
        lblSaturationLevel1.setPreferredSize(new java.awt.Dimension(110, 36));

        sliderContrastLevel1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderContrastLevel1StateChanged(evt);
            }
        });

        lblBright.setFont(lblBright.getFont().deriveFont(lblBright.getFont().getSize()+4f));
        lblBright.setText("50");

        lblContrast.setFont(lblContrast.getFont().deriveFont(lblContrast.getFont().getSize()+4f));
        lblContrast.setText("50");

        lblSaturation.setFont(lblSaturation.getFont().deriveFont(lblSaturation.getFont().getSize()+4f));
        lblSaturation.setText("50");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBrihtness, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(lblcontrast1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblSaturationLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sliderSaturationLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderContrastLevel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderBrightness, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBright)
                    .addComponent(lblContrast)
                    .addComponent(lblSaturation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblBrihtness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBright))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblcontrast1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderContrastLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrast))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSaturationLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderSaturationLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSaturation))
                .addContainerGap())
        );

        btnSaveBrightness.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveBrightness.setFont(btnSaveBrightness.getFont().deriveFont(btnSaveBrightness.getFont().getStyle() | java.awt.Font.BOLD, btnSaveBrightness.getFont().getSize()+7));
        btnSaveBrightness.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveBrightness.setText("SAVE");
        btnSaveBrightness.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveBrightness.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSaveBrightness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBrightnessActionPerformed(evt);
            }
        });

        btnRefresh2.setBackground(new java.awt.Color(47, 49, 51));
        btnRefresh2.setFont(btnRefresh2.getFont().deriveFont(btnRefresh2.getFont().getStyle() | java.awt.Font.BOLD, btnRefresh2.getFont().getSize()+7));
        btnRefresh2.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh2.setText("Refresh");
        btnRefresh2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefresh2.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh2ActionPerformed(evt);
            }
        });

        lblMsgBrightness.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMsgBrightness.setForeground(new java.awt.Color(0, 102, 51));
        lblMsgBrightness.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgBrightness.setText("Configuration Saved");

        javax.swing.GroupLayout tabCamBrightnessLayout = new javax.swing.GroupLayout(tabCamBrightness);
        tabCamBrightness.setLayout(tabCamBrightnessLayout);
        tabCamBrightnessLayout.setHorizontalGroup(
            tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCamBrightnessLayout.createSequentialGroup()
                .addGroup(tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCamBrightnessLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabCamBrightnessLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(pbarBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbSelectCameraBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tabCamBrightnessLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbselectcamera1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabCamBrightnessLayout.createSequentialGroup()
                        .addComponent(btnSaveBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsgBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        tabCamBrightnessLayout.setVerticalGroup(
            tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCamBrightnessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbselectcamera1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSelectCameraBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pbarBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCamBrightnessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveBrightness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsgBrightness))
                .addContainerGap(583, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("BRIGHTNESS", tabCamBrightness);

        tabMirror.setPreferredSize(new java.awt.Dimension(500, 500));

        btnSaveMirror.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveMirror.setFont(btnSaveMirror.getFont().deriveFont(btnSaveMirror.getFont().getStyle() | java.awt.Font.BOLD, btnSaveMirror.getFont().getSize()+7));
        btnSaveMirror.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveMirror.setText("SAVE");
        btnSaveMirror.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSaveMirror.setRequestFocusEnabled(false);
        btnSaveMirror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMirrorActionPerformed(evt);
            }
        });

        lblMsgMirror.setFont(lblMsgMirror.getFont().deriveFont(lblMsgMirror.getFont().getStyle() | java.awt.Font.BOLD, lblMsgMirror.getFont().getSize()+5));
        lblMsgMirror.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgMirror.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgMirror.setText("Configuration Saved");

        btnRefreshMirror.setBackground(new java.awt.Color(47, 49, 51));
        btnRefreshMirror.setFont(btnRefreshMirror.getFont().deriveFont(btnRefreshMirror.getFont().getStyle() | java.awt.Font.BOLD, btnRefreshMirror.getFont().getSize()+7));
        btnRefreshMirror.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshMirror.setText("Refresh");
        btnRefreshMirror.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefreshMirror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshMirrorActionPerformed(evt);
            }
        });

        lbselectcamera3.setFont(lbselectcamera3.getFont().deriveFont(lbselectcamera3.getFont().getStyle() | java.awt.Font.BOLD, lbselectcamera3.getFont().getSize()+7));
        lbselectcamera3.setForeground(new java.awt.Color(0, 0, 102));
        lbselectcamera3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbselectcamera3.setText("Select Camera");

        cmbSelectCameraMirror.setFont(cmbSelectCameraMirror.getFont().deriveFont(cmbSelectCameraMirror.getFont().getStyle() | java.awt.Font.BOLD, cmbSelectCameraMirror.getFont().getSize()+5));
        cmbSelectCameraMirror.setForeground(new java.awt.Color(102, 0, 0));
        cmbSelectCameraMirror.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cam1", "Cam2", "Cam3", "Cam4", "Cam5", "Cam6", "Cam7", "Cam8" }));
        cmbSelectCameraMirror.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSelectCameraMirrorItemStateChanged(evt);
            }
        });
        cmbSelectCameraMirror.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSelectCameraMirrorFocusGained(evt);
            }
        });
        cmbSelectCameraMirror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectCameraMirrorActionPerformed(evt);
            }
        });

        btnGrpMirror.add(radMirrorOn);
        radMirrorOn.setFont(radMirrorOn.getFont().deriveFont(radMirrorOn.getFont().getStyle() | java.awt.Font.BOLD, radMirrorOn.getFont().getSize()+6));
        radMirrorOn.setText("Mirror ON");

        btnGrpMirror.add(radMirrorOff);
        radMirrorOff.setFont(radMirrorOff.getFont().deriveFont(radMirrorOff.getFont().getStyle() | java.awt.Font.BOLD, radMirrorOff.getFont().getSize()+6));
        radMirrorOff.setSelected(true);
        radMirrorOff.setText("Mirror OFF");

        pbarMirror.setMaximum(10);

        javax.swing.GroupLayout tabMirrorLayout = new javax.swing.GroupLayout(tabMirror);
        tabMirror.setLayout(tabMirrorLayout);
        tabMirrorLayout.setHorizontalGroup(
            tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMirrorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMsgMirror, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabMirrorLayout.createSequentialGroup()
                        .addComponent(pbarMirror, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(tabMirrorLayout.createSequentialGroup()
                        .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabMirrorLayout.createSequentialGroup()
                                .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbselectcamera3)
                                    .addComponent(radMirrorOn))
                                .addGap(25, 25, 25)
                                .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radMirrorOff)
                                    .addComponent(cmbSelectCameraMirror, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tabMirrorLayout.createSequentialGroup()
                                .addComponent(btnSaveMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefreshMirror, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 235, Short.MAX_VALUE))))
        );
        tabMirrorLayout.setVerticalGroup(
            tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMirrorLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbselectcamera3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSelectCameraMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radMirrorOff)
                    .addComponent(radMirrorOn))
                .addGap(18, 18, 18)
                .addComponent(pbarMirror, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMsgMirror, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabMirrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefreshMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveMirror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(610, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("MIRROR", tabMirror);

        tabWaterMark.setPreferredSize(new java.awt.Dimension(500, 500));

        lbselectcamera2.setFont(lbselectcamera2.getFont().deriveFont(lbselectcamera2.getFont().getStyle() | java.awt.Font.BOLD, lbselectcamera2.getFont().getSize()+7));
        lbselectcamera2.setForeground(new java.awt.Color(0, 0, 102));
        lbselectcamera2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbselectcamera2.setText("Select Camera");

        cmbSelectCameraWatermark.setFont(cmbSelectCameraWatermark.getFont().deriveFont(cmbSelectCameraWatermark.getFont().getStyle() | java.awt.Font.BOLD, cmbSelectCameraWatermark.getFont().getSize()+5));
        cmbSelectCameraWatermark.setForeground(new java.awt.Color(102, 0, 0));
        cmbSelectCameraWatermark.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cam1", "Cam2", "Cam3", "Cam4" }));
        cmbSelectCameraWatermark.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSelectCameraWatermarkItemStateChanged(evt);
            }
        });
        cmbSelectCameraWatermark.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSelectCameraWatermarkFocusGained(evt);
            }
        });
        cmbSelectCameraWatermark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectCameraWatermarkActionPerformed(evt);
            }
        });

        lblMsgWatermark.setFont(lblMsgWatermark.getFont().deriveFont(lblMsgWatermark.getFont().getStyle() | java.awt.Font.BOLD, lblMsgWatermark.getFont().getSize()+5));
        lblMsgWatermark.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgWatermark.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgWatermark.setText("Configuration Saved");

        btnSaveWatermark.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveWatermark.setFont(btnSaveWatermark.getFont().deriveFont(btnSaveWatermark.getFont().getStyle() | java.awt.Font.BOLD, btnSaveWatermark.getFont().getSize()+7));
        btnSaveWatermark.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveWatermark.setText("SAVE");
        btnSaveWatermark.setAutoscrolls(true);
        btnSaveWatermark.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveWatermark.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaveWatermark.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSaveWatermark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveWatermarkActionPerformed(evt);
            }
        });

        btnRefreshWatermark.setBackground(new java.awt.Color(47, 49, 51));
        btnRefreshWatermark.setFont(btnRefreshWatermark.getFont().deriveFont(btnRefreshWatermark.getFont().getStyle() | java.awt.Font.BOLD, btnRefreshWatermark.getFont().getSize()+7));
        btnRefreshWatermark.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshWatermark.setText("Refresh");
        btnRefreshWatermark.setAutoscrolls(true);
        btnRefreshWatermark.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefreshWatermark.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefreshWatermark.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefreshWatermark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshWatermarkActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        txtDeviceName.setBackground(new java.awt.Color(204, 204, 204));
        txtDeviceName.setFont(txtDeviceName.getFont().deriveFont(txtDeviceName.getFont().getStyle() | java.awt.Font.BOLD, txtDeviceName.getFont().getSize()+5));
        txtDeviceName.setForeground(new java.awt.Color(102, 0, 0));
        txtDeviceName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDeviceName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDeviceNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDeviceNameFocusLost(evt);
            }
        });
        txtDeviceName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeviceNameActionPerformed(evt);
            }
        });

        chkSpeed.setBackground(new java.awt.Color(204, 204, 204));
        chkSpeed.setFont(chkSpeed.getFont().deriveFont(chkSpeed.getFont().getStyle() | java.awt.Font.BOLD, chkSpeed.getFont().getSize()+4));
        chkSpeed.setForeground(new java.awt.Color(0, 0, 102));
        chkSpeed.setText("Speed");
        chkSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSpeedActionPerformed(evt);
            }
        });

        chkCamIdentifier.setBackground(new java.awt.Color(204, 204, 204));
        chkCamIdentifier.setFont(chkCamIdentifier.getFont().deriveFont(chkCamIdentifier.getFont().getStyle() | java.awt.Font.BOLD, chkCamIdentifier.getFont().getSize()+4));
        chkCamIdentifier.setForeground(new java.awt.Color(0, 0, 102));
        chkCamIdentifier.setText("Camera Name");
        chkCamIdentifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCamIdentifierActionPerformed(evt);
            }
        });

        chkVechicleRegistration.setBackground(new java.awt.Color(204, 204, 204));
        chkVechicleRegistration.setFont(chkVechicleRegistration.getFont().deriveFont(chkVechicleRegistration.getFont().getStyle() | java.awt.Font.BOLD, chkVechicleRegistration.getFont().getSize()+4));
        chkVechicleRegistration.setForeground(new java.awt.Color(0, 0, 102));
        chkVechicleRegistration.setText("Vehicle Registration ");
        chkVechicleRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVechicleRegistrationActionPerformed(evt);
            }
        });

        chkCoordinates.setBackground(new java.awt.Color(204, 204, 204));
        chkCoordinates.setFont(chkCoordinates.getFont().deriveFont(chkCoordinates.getFont().getStyle() | java.awt.Font.BOLD, chkCoordinates.getFont().getSize()+4));
        chkCoordinates.setForeground(new java.awt.Color(0, 0, 102));
        chkCoordinates.setText("Co-ordinates");
        chkCoordinates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCoordinatesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkCoordinates)
                            .addComponent(chkVechicleRegistration))
                        .addGap(156, 156, 156))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(chkCamIdentifier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDeviceName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCamIdentifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDeviceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSpeed)
                .addGap(0, 0, 0)
                .addComponent(chkCoordinates, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkVechicleRegistration)
                .addContainerGap())
        );

        pbarWaterMark.setMaximum(10);

        javax.swing.GroupLayout tabWaterMarkLayout = new javax.swing.GroupLayout(tabWaterMark);
        tabWaterMark.setLayout(tabWaterMarkLayout);
        tabWaterMarkLayout.setHorizontalGroup(
            tabWaterMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabWaterMarkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabWaterMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabWaterMarkLayout.createSequentialGroup()
                        .addComponent(lblMsgWatermark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(141, 141, 141))
                    .addGroup(tabWaterMarkLayout.createSequentialGroup()
                        .addComponent(pbarWaterMark, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(159, Short.MAX_VALUE))
                    .addGroup(tabWaterMarkLayout.createSequentialGroup()
                        .addGroup(tabWaterMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabWaterMarkLayout.createSequentialGroup()
                                .addComponent(lbselectcamera2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbSelectCameraWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabWaterMarkLayout.createSequentialGroup()
                                .addComponent(btnSaveWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRefreshWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        tabWaterMarkLayout.setVerticalGroup(
            tabWaterMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabWaterMarkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabWaterMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbselectcamera2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSelectCameraWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbarWaterMark, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabWaterMarkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefreshWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMsgWatermark, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("WATER MARK", tabWaterMark);

        lblEmergencyService2.setFont(lblEmergencyService2.getFont().deriveFont(lblEmergencyService2.getFont().getStyle() | java.awt.Font.BOLD, lblEmergencyService2.getFont().getSize()+5));
        lblEmergencyService2.setForeground(new java.awt.Color(0, 0, 102));
        lblEmergencyService2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmergencyService2.setText("Video Record Storage");
        lblEmergencyService2.setPreferredSize(new java.awt.Dimension(222, 30));

        spinVidRecStorage1.setFont(spinVidRecStorage1.getFont().deriveFont(spinVidRecStorage1.getFont().getStyle() | java.awt.Font.BOLD, spinVidRecStorage1.getFont().getSize()+5));
        spinVidRecStorage1.setModel(new javax.swing.SpinnerNumberModel(3, 1, 500, 1));

        lblEmergencyService4.setFont(lblEmergencyService4.getFont().deriveFont(lblEmergencyService4.getFont().getStyle() | java.awt.Font.BOLD, lblEmergencyService4.getFont().getSize()+5));
        lblEmergencyService4.setForeground(new java.awt.Color(0, 0, 102));
        lblEmergencyService4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmergencyService4.setText("Days");
        lblEmergencyService4.setPreferredSize(new java.awt.Dimension(60, 30));

        lblEmergencyService3.setFont(lblEmergencyService3.getFont().deriveFont(lblEmergencyService3.getFont().getStyle() | java.awt.Font.BOLD, lblEmergencyService3.getFont().getSize()+5));
        lblEmergencyService3.setForeground(new java.awt.Color(0, 0, 102));
        lblEmergencyService3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmergencyService3.setText("Event Record Storage");
        lblEmergencyService3.setPreferredSize(new java.awt.Dimension(222, 30));

        spinEventRecStorage.setFont(spinEventRecStorage.getFont().deriveFont(spinEventRecStorage.getFont().getStyle() | java.awt.Font.BOLD, spinEventRecStorage.getFont().getSize()+5));
        spinEventRecStorage.setModel(new javax.swing.SpinnerNumberModel(3, 1, 100, 1));

        lblEmergencyService5.setFont(lblEmergencyService5.getFont().deriveFont(lblEmergencyService5.getFont().getStyle() | java.awt.Font.BOLD, lblEmergencyService5.getFont().getSize()+5));
        lblEmergencyService5.setForeground(new java.awt.Color(0, 0, 102));
        lblEmergencyService5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmergencyService5.setText("Days");
        lblEmergencyService5.setPreferredSize(new java.awt.Dimension(60, 30));

        lblEmergencyService1.setFont(lblEmergencyService1.getFont().deriveFont(lblEmergencyService1.getFont().getStyle() | java.awt.Font.BOLD, lblEmergencyService1.getFont().getSize()+5));
        lblEmergencyService1.setForeground(new java.awt.Color(0, 0, 102));
        lblEmergencyService1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmergencyService1.setText("Snapshot Storage");
        lblEmergencyService1.setPreferredSize(new java.awt.Dimension(222, 30));

        spinSnapStorage.setFont(spinSnapStorage.getFont().deriveFont(spinSnapStorage.getFont().getStyle() | java.awt.Font.BOLD, spinSnapStorage.getFont().getSize()+5));
        spinSnapStorage.setModel(new javax.swing.SpinnerNumberModel(3, 1, 100, 1));

        lblEmergencyService6.setFont(lblEmergencyService6.getFont().deriveFont(lblEmergencyService6.getFont().getStyle() | java.awt.Font.BOLD, lblEmergencyService6.getFont().getSize()+5));
        lblEmergencyService6.setForeground(new java.awt.Color(0, 0, 102));
        lblEmergencyService6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmergencyService6.setText("Days");
        lblEmergencyService6.setPreferredSize(new java.awt.Dimension(60, 30));

        btnSaveVideoLimit.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveVideoLimit.setFont(btnSaveVideoLimit.getFont().deriveFont(btnSaveVideoLimit.getFont().getStyle() | java.awt.Font.BOLD, btnSaveVideoLimit.getFont().getSize()+7));
        btnSaveVideoLimit.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveVideoLimit.setText("SAVE");
        btnSaveVideoLimit.setAutoscrolls(true);
        btnSaveVideoLimit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveVideoLimit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaveVideoLimit.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSaveVideoLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveVideoLimitActionPerformed(evt);
            }
        });

        lblMsgRecord1.setFont(lblMsgRecord1.getFont().deriveFont(lblMsgRecord1.getFont().getStyle() | java.awt.Font.BOLD, lblMsgRecord1.getFont().getSize()+5));
        lblMsgRecord1.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgRecord1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgRecord1.setText(" Saved");
        lblMsgRecord1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblEmergencyService2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addComponent(lblEmergencyService3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(lblEmergencyService1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(spinEventRecStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmergencyService5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(spinVidRecStorage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmergencyService4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(spinSnapStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmergencyService6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(btnSaveVideoLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMsgRecord1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinEventRecStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmergencyService3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmergencyService5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinSnapStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmergencyService1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmergencyService6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(btnSaveVideoLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinVidRecStorage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmergencyService2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmergencyService4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblMsgRecord1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("VIDEO LIMIT", jPanel12);

        tabRecordConfig.setPreferredSize(new java.awt.Dimension(500, 500));

        panManRecord.setBackground(new java.awt.Color(204, 204, 204));
        panManRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblFromDate.setFont(lblFromDate.getFont().deriveFont(lblFromDate.getFont().getStyle() | java.awt.Font.BOLD, lblFromDate.getFont().getSize()+4));
        lblFromDate.setForeground(new java.awt.Color(102, 0, 0));
        lblFromDate.setText("From  Time");

        spinStartDate.setFont(spinStartDate.getFont().deriveFont(spinStartDate.getFont().getStyle() | java.awt.Font.BOLD, spinStartDate.getFont().getSize()+4));
        spinStartDate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        spinStartDate.setFocusable(false);
        spinStartDate.setMaximumSize(new java.awt.Dimension(31, 31));
        spinStartDate.setName(""); // NOI18N
        spinStartDate.setVerifyInputWhenFocusTarget(false);
        spinStartDate.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                spinStartDateInputMethodTextChanged(evt);
            }
        });

        lblToDate.setFont(lblToDate.getFont().deriveFont(lblToDate.getFont().getStyle() | java.awt.Font.BOLD, lblToDate.getFont().getSize()+4));
        lblToDate.setForeground(new java.awt.Color(102, 0, 0));
        lblToDate.setText("To Time");

        spinEndDate.setFont(spinEndDate.getFont().deriveFont(spinEndDate.getFont().getStyle() | java.awt.Font.BOLD, spinEndDate.getFont().getSize()+4));
        spinEndDate.setModel(new javax.swing.SpinnerDateModel());
        spinEndDate.setFocusable(false);
        spinEndDate.setMaximumSize(new java.awt.Dimension(31, 31));
        spinEndDate.setName(""); // NOI18N
        spinEndDate.setVerifyInputWhenFocusTarget(false);

        chkSunday.setBackground(new java.awt.Color(204, 204, 204));
        chkSunday.setFont(chkSunday.getFont().deriveFont(chkSunday.getFont().getStyle() | java.awt.Font.BOLD));
        chkSunday.setForeground(new java.awt.Color(0, 0, 102));
        chkSunday.setText("Sun");
        chkSunday.setPreferredSize(new java.awt.Dimension(55, 20));
        chkSunday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSundayActionPerformed(evt);
            }
        });

        chkMonday.setBackground(new java.awt.Color(204, 204, 204));
        chkMonday.setFont(chkMonday.getFont().deriveFont(chkMonday.getFont().getStyle() | java.awt.Font.BOLD));
        chkMonday.setForeground(new java.awt.Color(0, 0, 102));
        chkMonday.setText("Mon");
        chkMonday.setPreferredSize(new java.awt.Dimension(55, 20));
        chkMonday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMondayActionPerformed(evt);
            }
        });

        chkTuesday.setBackground(new java.awt.Color(204, 204, 204));
        chkTuesday.setFont(chkTuesday.getFont().deriveFont(chkTuesday.getFont().getStyle() | java.awt.Font.BOLD));
        chkTuesday.setForeground(new java.awt.Color(0, 0, 102));
        chkTuesday.setText("Tue");
        chkTuesday.setPreferredSize(new java.awt.Dimension(55, 20));

        chkWednesday.setBackground(new java.awt.Color(204, 204, 204));
        chkWednesday.setFont(chkWednesday.getFont().deriveFont(chkWednesday.getFont().getStyle() | java.awt.Font.BOLD));
        chkWednesday.setForeground(new java.awt.Color(0, 0, 102));
        chkWednesday.setText("Wed");
        chkWednesday.setPreferredSize(new java.awt.Dimension(55, 20));
        chkWednesday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkWednesdayActionPerformed(evt);
            }
        });

        chkThursday.setBackground(new java.awt.Color(204, 204, 204));
        chkThursday.setFont(chkThursday.getFont().deriveFont(chkThursday.getFont().getStyle() | java.awt.Font.BOLD));
        chkThursday.setForeground(new java.awt.Color(0, 0, 102));
        chkThursday.setText("Thu");
        chkThursday.setPreferredSize(new java.awt.Dimension(55, 20));

        chkFriday.setBackground(new java.awt.Color(204, 204, 204));
        chkFriday.setFont(chkFriday.getFont().deriveFont(chkFriday.getFont().getStyle() | java.awt.Font.BOLD));
        chkFriday.setForeground(new java.awt.Color(0, 0, 102));
        chkFriday.setText("Fri");
        chkFriday.setPreferredSize(new java.awt.Dimension(55, 20));

        chkSaturday.setBackground(new java.awt.Color(204, 204, 204));
        chkSaturday.setFont(chkSaturday.getFont().deriveFont(chkSaturday.getFont().getStyle() | java.awt.Font.BOLD));
        chkSaturday.setForeground(new java.awt.Color(0, 0, 102));
        chkSaturday.setText("Sat");
        chkSaturday.setPreferredSize(new java.awt.Dimension(55, 20));

        javax.swing.GroupLayout panManRecordLayout = new javax.swing.GroupLayout(panManRecord);
        panManRecord.setLayout(panManRecordLayout);
        panManRecordLayout.setHorizontalGroup(
            panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panManRecordLayout.createSequentialGroup()
                .addGroup(panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panManRecordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spinStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(spinEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panManRecordLayout.createSequentialGroup()
                        .addGroup(panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panManRecordLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblFromDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblToDate))
                            .addGroup(panManRecordLayout.createSequentialGroup()
                                .addComponent(chkSunday, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkMonday, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkTuesday, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkWednesday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkThursday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkFriday, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkSaturday, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panManRecordLayout.setVerticalGroup(
            panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panManRecordLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkSunday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkMonday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTuesday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkWednesday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkThursday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFriday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSaturday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblToDate))
                .addGap(8, 8, 8)
                .addGroup(panManRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMsgRecord.setFont(lblMsgRecord.getFont().deriveFont(lblMsgRecord.getFont().getStyle() | java.awt.Font.BOLD, lblMsgRecord.getFont().getSize()+5));
        lblMsgRecord.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgRecord.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgRecord.setText(" Saved");
        lblMsgRecord.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnSaveRecord.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveRecord.setFont(btnSaveRecord.getFont().deriveFont(btnSaveRecord.getFont().getStyle() | java.awt.Font.BOLD, btnSaveRecord.getFont().getSize()+7));
        btnSaveRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveRecord.setText("SAVE");
        btnSaveRecord.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnSaveRecord.setPreferredSize(new java.awt.Dimension(110, 30));
        btnSaveRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRecordActionPerformed(evt);
            }
        });

        btnGrpRecord.add(radManRecord);
        radManRecord.setFont(radManRecord.getFont().deriveFont(radManRecord.getFont().getStyle() | java.awt.Font.BOLD, radManRecord.getFont().getSize()+4));
        radManRecord.setForeground(new java.awt.Color(0, 51, 102));
        radManRecord.setText("Schedule  Record");
        radManRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radManRecordActionPerformed(evt);
            }
        });

        btnGrpRecord.add(radContRecord);
        radContRecord.setFont(radContRecord.getFont().deriveFont(radContRecord.getFont().getStyle() | java.awt.Font.BOLD, radContRecord.getFont().getSize()+4));
        radContRecord.setForeground(new java.awt.Color(0, 0, 102));
        radContRecord.setText("Continuous Record");
        radContRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radContRecordActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkCam1Audio.setBackground(new java.awt.Color(204, 204, 204));
        chkCam1Audio.setFont(chkCam1Audio.getFont().deriveFont(chkCam1Audio.getFont().getStyle() | java.awt.Font.BOLD, chkCam1Audio.getFont().getSize()+4));
        chkCam1Audio.setForeground(new java.awt.Color(102, 0, 0));
        chkCam1Audio.setText("Aud 1");
        chkCam1Audio.setPreferredSize(new java.awt.Dimension(75, 25));

        chkCam4Audio.setBackground(new java.awt.Color(204, 204, 204));
        chkCam4Audio.setFont(chkCam4Audio.getFont().deriveFont(chkCam4Audio.getFont().getStyle() | java.awt.Font.BOLD, chkCam4Audio.getFont().getSize()+4));
        chkCam4Audio.setForeground(new java.awt.Color(102, 0, 0));
        chkCam4Audio.setText("Aud 4");
        chkCam4Audio.setPreferredSize(new java.awt.Dimension(70, 25));

        chkCam2Audio.setBackground(new java.awt.Color(204, 204, 204));
        chkCam2Audio.setFont(chkCam2Audio.getFont().deriveFont(chkCam2Audio.getFont().getStyle() | java.awt.Font.BOLD, chkCam2Audio.getFont().getSize()+4));
        chkCam2Audio.setForeground(new java.awt.Color(102, 0, 0));
        chkCam2Audio.setText("Aud 2");
        chkCam2Audio.setPreferredSize(new java.awt.Dimension(75, 25));
        chkCam2Audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam2AudioActionPerformed(evt);
            }
        });

        chkCam3Audio.setBackground(new java.awt.Color(204, 204, 204));
        chkCam3Audio.setFont(chkCam3Audio.getFont().deriveFont(chkCam3Audio.getFont().getStyle() | java.awt.Font.BOLD, chkCam3Audio.getFont().getSize()+4));
        chkCam3Audio.setForeground(new java.awt.Color(102, 0, 0));
        chkCam3Audio.setText("Aud 3");
        chkCam3Audio.setPreferredSize(new java.awt.Dimension(70, 25));
        chkCam3Audio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam3AudioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(chkCam1Audio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(chkCam2Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(chkCam3Audio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(chkCam4Audio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCam1Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCam2Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCam3Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCam4Audio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));
        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblObuId6.setFont(lblObuId6.getFont().deriveFont(lblObuId6.getFont().getStyle() | java.awt.Font.BOLD, lblObuId6.getFont().getSize()+3));
        lblObuId6.setForeground(new java.awt.Color(102, 0, 102));
        lblObuId6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId6.setText("post Int(sec)");

        lblObuId4.setFont(lblObuId4.getFont().deriveFont(lblObuId4.getFont().getStyle() | java.awt.Font.BOLD, lblObuId4.getFont().getSize()+3));
        lblObuId4.setForeground(new java.awt.Color(102, 0, 102));
        lblObuId4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId4.setText("Resolution");

        SpinEventRecTime.setFont(SpinEventRecTime.getFont().deriveFont(SpinEventRecTime.getFont().getStyle() | java.awt.Font.BOLD, SpinEventRecTime.getFont().getSize()+3));
        SpinEventRecTime.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        chkEventRecord.setBackground(new java.awt.Color(204, 204, 204));
        chkEventRecord.setFont(chkEventRecord.getFont().deriveFont(chkEventRecord.getFont().getStyle() | java.awt.Font.BOLD, chkEventRecord.getFont().getSize()+2));
        chkEventRecord.setForeground(new java.awt.Color(0, 0, 102));
        chkEventRecord.setText("Event Based");
        chkEventRecord.setPreferredSize(new java.awt.Dimension(120, 25));
        chkEventRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEventRecordActionPerformed(evt);
            }
        });

        SpinEventPreRecTime.setFont(SpinEventPreRecTime.getFont().deriveFont(SpinEventPreRecTime.getFont().getStyle() | java.awt.Font.BOLD, SpinEventPreRecTime.getFont().getSize()+3));
        SpinEventPreRecTime.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        lblObuId7.setFont(lblObuId7.getFont().deriveFont(lblObuId7.getFont().getStyle() | java.awt.Font.BOLD, lblObuId7.getFont().getSize()+3));
        lblObuId7.setForeground(new java.awt.Color(102, 0, 102));
        lblObuId7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId7.setText("pre Int(sec)");

        SpinCanRecTime.setFont(SpinCanRecTime.getFont().deriveFont(SpinCanRecTime.getFont().getStyle() | java.awt.Font.BOLD, SpinCanRecTime.getFont().getSize()+3));
        SpinCanRecTime.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));

        SpinCanPreRecTime.setFont(SpinCanPreRecTime.getFont().deriveFont(SpinCanPreRecTime.getFont().getStyle() | java.awt.Font.BOLD, SpinCanPreRecTime.getFont().getSize()+3));
        SpinCanPreRecTime.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));

        chkCanRecord.setBackground(new java.awt.Color(204, 204, 204));
        chkCanRecord.setFont(chkCanRecord.getFont().deriveFont(chkCanRecord.getFont().getStyle() | java.awt.Font.BOLD, chkCanRecord.getFont().getSize()+2));
        chkCanRecord.setForeground(new java.awt.Color(0, 0, 102));
        chkCanRecord.setText("CAN Based");
        chkCanRecord.setPreferredSize(new java.awt.Dimension(120, 25));
        chkCanRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanRecordActionPerformed(evt);
            }
        });

        cmbVidStreamTypeCan.setFont(cmbVidStreamTypeCan.getFont().deriveFont(cmbVidStreamTypeCan.getFont().getStyle() | java.awt.Font.BOLD, cmbVidStreamTypeCan.getFont().getSize()+3));
        cmbVidStreamTypeCan.setForeground(new java.awt.Color(102, 0, 0));
        cmbVidStreamTypeCan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbVidStreamTypeCan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbVidStreamTypeCanItemStateChanged(evt);
            }
        });
        cmbVidStreamTypeCan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbVidStreamTypeCanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbVidStreamTypeCanFocusLost(evt);
            }
        });
        cmbVidStreamTypeCan.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbVidStreamTypeCanCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbVidStreamTypeCanInputMethodTextChanged(evt);
            }
        });
        cmbVidStreamTypeCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVidStreamTypeCanActionPerformed(evt);
            }
        });
        cmbVidStreamTypeCan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbVidStreamTypeCanPropertyChange(evt);
            }
        });
        cmbVidStreamTypeCan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbVidStreamTypeCanKeyReleased(evt);
            }
        });

        cmbVidStreamTypeEvent.setFont(cmbVidStreamTypeEvent.getFont().deriveFont(cmbVidStreamTypeEvent.getFont().getStyle() | java.awt.Font.BOLD, cmbVidStreamTypeEvent.getFont().getSize()+3));
        cmbVidStreamTypeEvent.setForeground(new java.awt.Color(102, 0, 0));
        cmbVidStreamTypeEvent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low" }));
        cmbVidStreamTypeEvent.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbVidStreamTypeEventItemStateChanged(evt);
            }
        });
        cmbVidStreamTypeEvent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbVidStreamTypeEventFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbVidStreamTypeEventFocusLost(evt);
            }
        });
        cmbVidStreamTypeEvent.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmbVidStreamTypeEventCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbVidStreamTypeEventInputMethodTextChanged(evt);
            }
        });
        cmbVidStreamTypeEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVidStreamTypeEventActionPerformed(evt);
            }
        });
        cmbVidStreamTypeEvent.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cmbVidStreamTypeEventPropertyChange(evt);
            }
        });
        cmbVidStreamTypeEvent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbVidStreamTypeEventKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkCanRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkEventRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(SpinCanPreRecTime))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinEventPreRecTime)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(SpinCanRecTime)
                            .addComponent(SpinEventRecTime)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lblObuId7)
                        .addGap(18, 18, 18)
                        .addComponent(lblObuId6)))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbVidStreamTypeEvent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbVidStreamTypeCan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblObuId4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblObuId6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObuId4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObuId7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkEventRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinEventPreRecTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinEventRecTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVidStreamTypeEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkCanRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinCanPreRecTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SpinCanRecTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVidStreamTypeCan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkCam1Rec.setFont(chkCam1Rec.getFont().deriveFont(chkCam1Rec.getFont().getStyle() | java.awt.Font.BOLD, chkCam1Rec.getFont().getSize()+4));
        chkCam1Rec.setForeground(new java.awt.Color(0, 0, 102));
        chkCam1Rec.setText("CAM1");
        chkCam1Rec.setPreferredSize(new java.awt.Dimension(83, 20));

        chkCam2Rec.setFont(chkCam2Rec.getFont().deriveFont(chkCam2Rec.getFont().getStyle() | java.awt.Font.BOLD, chkCam2Rec.getFont().getSize()+4));
        chkCam2Rec.setForeground(new java.awt.Color(0, 0, 102));
        chkCam2Rec.setText("CAM2");
        chkCam2Rec.setPreferredSize(new java.awt.Dimension(83, 20));
        chkCam2Rec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam2RecActionPerformed(evt);
            }
        });

        chkCam3Rec.setFont(chkCam3Rec.getFont().deriveFont(chkCam3Rec.getFont().getStyle() | java.awt.Font.BOLD, chkCam3Rec.getFont().getSize()+4));
        chkCam3Rec.setForeground(new java.awt.Color(0, 0, 102));
        chkCam3Rec.setText("CAM3");
        chkCam3Rec.setPreferredSize(new java.awt.Dimension(83, 20));
        chkCam3Rec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam3RecActionPerformed(evt);
            }
        });

        chkCam4Rec.setFont(chkCam4Rec.getFont().deriveFont(chkCam4Rec.getFont().getStyle() | java.awt.Font.BOLD, chkCam4Rec.getFont().getSize()+4));
        chkCam4Rec.setForeground(new java.awt.Color(0, 0, 102));
        chkCam4Rec.setText("CAM4");
        chkCam4Rec.setPreferredSize(new java.awt.Dimension(83, 20));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkCam1Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(chkCam2Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(chkCam3Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(chkCam4Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCam1Rec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCam2Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCam3Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCam4Rec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        btnRefresh3.setBackground(new java.awt.Color(47, 49, 51));
        btnRefresh3.setFont(btnRefresh3.getFont().deriveFont(btnRefresh3.getFont().getStyle() | java.awt.Font.BOLD, btnRefresh3.getFont().getSize()+7));
        btnRefresh3.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh3.setText("Refresh");
        btnRefresh3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnRefresh3.setPreferredSize(new java.awt.Dimension(110, 30));
        btnRefresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabRecordConfigLayout = new javax.swing.GroupLayout(tabRecordConfig);
        tabRecordConfig.setLayout(tabRecordConfigLayout);
        tabRecordConfigLayout.setHorizontalGroup(
            tabRecordConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRecordConfigLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnSaveRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsgRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(tabRecordConfigLayout.createSequentialGroup()
                .addGroup(tabRecordConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabRecordConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(radManRecord)
                        .addGap(42, 42, 42)
                        .addComponent(radContRecord))
                    .addGroup(tabRecordConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panManRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        tabRecordConfigLayout.setVerticalGroup(
            tabRecordConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRecordConfigLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabRecordConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radManRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radContRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panManRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(tabRecordConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMsgRecord)
                    .addComponent(btnRefresh3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(tabRecordConfig);

        jScrollPane5.setViewportView(jScrollPane4);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("RECORD", jPanel14);

        chkOnvifSupport.setFont(chkOnvifSupport.getFont().deriveFont(chkOnvifSupport.getFont().getStyle() | java.awt.Font.BOLD, chkOnvifSupport.getFont().getSize()+6));
        chkOnvifSupport.setForeground(new java.awt.Color(102, 0, 0));
        chkOnvifSupport.setText("ONVIF ");
        chkOnvifSupport.setPreferredSize(new java.awt.Dimension(200, 30));
        chkOnvifSupport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOnvifSupportActionPerformed(evt);
            }
        });

        chkCameraType.setFont(chkCameraType.getFont().deriveFont(chkCameraType.getFont().getStyle() | java.awt.Font.BOLD, chkCameraType.getFont().getSize()+6));
        chkCameraType.setForeground(new java.awt.Color(102, 0, 0));
        chkCameraType.setText("CAMERA ANALOG");
        chkCameraType.setPreferredSize(new java.awt.Dimension(200, 30));
        chkCameraType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCameraTypeItemStateChanged(evt);
            }
        });
        chkCameraType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCameraTypeActionPerformed(evt);
            }
        });

        btnSave6.setFont(btnSave6.getFont().deriveFont(btnSave6.getFont().getStyle() | java.awt.Font.BOLD, btnSave6.getFont().getSize()+6));
        btnSave6.setForeground(new java.awt.Color(0, 0, 102));
        btnSave6.setText("SAVE");
        btnSave6.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkOnvifSupport, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCameraType, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkOnvifSupport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCameraType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(729, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("SETTINGS", jPanel15);

        tabMotionDetect.setPreferredSize(new java.awt.Dimension(499, 1000));

        chkMotionDet.setFont(chkMotionDet.getFont().deriveFont(chkMotionDet.getFont().getStyle() | java.awt.Font.BOLD, chkMotionDet.getFont().getSize()+5));
        chkMotionDet.setForeground(new java.awt.Color(0, 0, 102));
        chkMotionDet.setText("Enable Motion");
        chkMotionDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMotionDetActionPerformed(evt);
            }
        });

        btnSaveMotion.setBackground(new java.awt.Color(47, 49, 51));
        btnSaveMotion.setFont(btnSaveMotion.getFont().deriveFont(btnSaveMotion.getFont().getStyle() | java.awt.Font.BOLD, btnSaveMotion.getFont().getSize()+7));
        btnSaveMotion.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveMotion.setText("SAVE");
        btnSaveMotion.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSaveMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMotionActionPerformed(evt);
            }
        });

        lblMsgMotionDetect.setFont(lblMsgMotionDetect.getFont().deriveFont(lblMsgMotionDetect.getFont().getStyle() | java.awt.Font.BOLD, lblMsgMotionDetect.getFont().getSize()+5));
        lblMsgMotionDetect.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgMotionDetect.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgMotionDetect.setText("Configuration Saved");

        btnGrpMotionDetect.add(radCam1Motion);
        radCam1Motion.setFont(radCam1Motion.getFont().deriveFont(radCam1Motion.getFont().getStyle() | java.awt.Font.BOLD, radCam1Motion.getFont().getSize()+7));
        radCam1Motion.setForeground(new java.awt.Color(0, 0, 102));
        radCam1Motion.setText("Cam1");
        radCam1Motion.setBorder(null);
        radCam1Motion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radCam1MotionItemStateChanged(evt);
            }
        });
        radCam1Motion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam1MotionActionPerformed(evt);
            }
        });

        btnGrpMotionDetect.add(radCam4Motion);
        radCam4Motion.setFont(radCam4Motion.getFont().deriveFont(radCam4Motion.getFont().getStyle() | java.awt.Font.BOLD, radCam4Motion.getFont().getSize()+7));
        radCam4Motion.setForeground(new java.awt.Color(0, 0, 102));
        radCam4Motion.setText("Cam4");
        radCam4Motion.setBorder(null);
        radCam4Motion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam4MotionActionPerformed(evt);
            }
        });

        btnGrpMotionDetect.add(radCam3Motion);
        radCam3Motion.setFont(radCam3Motion.getFont().deriveFont(radCam3Motion.getFont().getStyle() | java.awt.Font.BOLD, radCam3Motion.getFont().getSize()+7));
        radCam3Motion.setForeground(new java.awt.Color(0, 0, 102));
        radCam3Motion.setText("Cam3");
        radCam3Motion.setBorder(null);
        radCam3Motion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam3MotionActionPerformed(evt);
            }
        });

        btnGrpMotionDetect.add(radCam2Motion);
        radCam2Motion.setFont(radCam2Motion.getFont().deriveFont(radCam2Motion.getFont().getStyle() | java.awt.Font.BOLD, radCam2Motion.getFont().getSize()+7));
        radCam2Motion.setForeground(new java.awt.Color(0, 0, 102));
        radCam2Motion.setText("Cam2");
        radCam2Motion.setBorder(null);
        radCam2Motion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCam2MotionActionPerformed(evt);
            }
        });

        btnRefreshMotion.setBackground(new java.awt.Color(47, 49, 51));
        btnRefreshMotion.setFont(btnRefreshMotion.getFont().deriveFont(btnRefreshMotion.getFont().getStyle() | java.awt.Font.BOLD, btnRefreshMotion.getFont().getSize()+7));
        btnRefreshMotion.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshMotion.setText("Refresh");
        btnRefreshMotion.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefreshMotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshMotionActionPerformed(evt);
            }
        });

        lblVideoWaterMarkingConfigName1.setEditable(false);
        lblVideoWaterMarkingConfigName1.setBackground(new java.awt.Color(23, 29, 32));
        lblVideoWaterMarkingConfigName1.setFont(lblVideoWaterMarkingConfigName1.getFont().deriveFont(lblVideoWaterMarkingConfigName1.getFont().getStyle() | java.awt.Font.BOLD, lblVideoWaterMarkingConfigName1.getFont().getSize()+10));
        lblVideoWaterMarkingConfigName1.setForeground(new java.awt.Color(255, 255, 255));
        lblVideoWaterMarkingConfigName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblVideoWaterMarkingConfigName1.setText("Motion Detection");
        lblVideoWaterMarkingConfigName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblVideoWaterMarkingConfigName1ActionPerformed(evt);
            }
        });

        panBorder2.setBackground(new java.awt.Color(204, 204, 204));
        panBorder2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel27.setBackground(new java.awt.Color(0, 0, 102));
        jLabel27.setFont(jLabel27.getFont().deriveFont(jLabel27.getFont().getStyle() | java.awt.Font.BOLD, jLabel27.getFont().getSize()+4));
        jLabel27.setForeground(new java.awt.Color(0, 0, 102));
        jLabel27.setText("X1");

        spinx1value.setFont(spinx1value.getFont().deriveFont(spinx1value.getFont().getStyle() | java.awt.Font.BOLD, spinx1value.getFont().getSize()+4));
        spinx1value.setModel(new javax.swing.SpinnerNumberModel(0, 0, 22, 1));

        jLabel28.setBackground(new java.awt.Color(0, 0, 102));
        jLabel28.setFont(jLabel28.getFont().deriveFont(jLabel28.getFont().getStyle() | java.awt.Font.BOLD, jLabel28.getFont().getSize()+4));
        jLabel28.setForeground(new java.awt.Color(0, 0, 102));
        jLabel28.setText("X2");

        spinx2value.setFont(spinx2value.getFont().deriveFont(spinx2value.getFont().getStyle() | java.awt.Font.BOLD, spinx2value.getFont().getSize()+4));
        spinx2value.setModel(new javax.swing.SpinnerNumberModel(0, 0, 17, 1));

        spiny1value.setFont(spiny1value.getFont().deriveFont(spiny1value.getFont().getStyle() | java.awt.Font.BOLD, spiny1value.getFont().getSize()+4));
        spiny1value.setModel(new javax.swing.SpinnerNumberModel(0, 0, 22, 1));

        jLabel29.setBackground(new java.awt.Color(0, 0, 102));
        jLabel29.setFont(jLabel29.getFont().deriveFont(jLabel29.getFont().getStyle() | java.awt.Font.BOLD, jLabel29.getFont().getSize()+4));
        jLabel29.setForeground(new java.awt.Color(0, 0, 102));
        jLabel29.setText("Y1");

        spiny2value.setFont(spiny2value.getFont().deriveFont(spiny2value.getFont().getStyle() | java.awt.Font.BOLD, spiny2value.getFont().getSize()+4));
        spiny2value.setModel(new javax.swing.SpinnerNumberModel(0, 0, 17, 1));

        jLabel30.setBackground(new java.awt.Color(0, 0, 102));
        jLabel30.setFont(jLabel30.getFont().deriveFont(jLabel30.getFont().getStyle() | java.awt.Font.BOLD, jLabel30.getFont().getSize()+4));
        jLabel30.setForeground(new java.awt.Color(0, 0, 102));
        jLabel30.setText("Y2");

        spinsensitivityVal.setFont(spinsensitivityVal.getFont().deriveFont(spinsensitivityVal.getFont().getStyle() | java.awt.Font.BOLD, spinsensitivityVal.getFont().getSize()+4));
        spinsensitivityVal.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel31.setBackground(new java.awt.Color(0, 0, 102));
        jLabel31.setFont(jLabel31.getFont().deriveFont(jLabel31.getFont().getStyle() | java.awt.Font.BOLD, jLabel31.getFont().getSize()+4));
        jLabel31.setForeground(new java.awt.Color(0, 0, 102));
        jLabel31.setText("Sensitivity");

        jLabel32.setBackground(new java.awt.Color(0, 0, 102));
        jLabel32.setFont(jLabel32.getFont().deriveFont(jLabel32.getFont().getStyle() | java.awt.Font.BOLD, jLabel32.getFont().getSize()+4));
        jLabel32.setForeground(new java.awt.Color(0, 0, 102));
        jLabel32.setText("Threshold");

        spinthresholdVal.setFont(spinthresholdVal.getFont().deriveFont(spinthresholdVal.getFont().getStyle() | java.awt.Font.BOLD, spinthresholdVal.getFont().getSize()+4));
        spinthresholdVal.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jLabel33.setBackground(new java.awt.Color(0, 0, 102));
        jLabel33.setFont(jLabel33.getFont().deriveFont(jLabel33.getFont().getStyle() | java.awt.Font.BOLD, jLabel33.getFont().getSize()+4));
        jLabel33.setForeground(new java.awt.Color(0, 0, 102));
        jLabel33.setText("Record time(min)");

        spinrecordtime.setFont(spinrecordtime.getFont().deriveFont(spinrecordtime.getFont().getStyle() | java.awt.Font.BOLD, spinrecordtime.getFont().getSize()+4));
        spinrecordtime.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));

        javax.swing.GroupLayout panBorder2Layout = new javax.swing.GroupLayout(panBorder2);
        panBorder2.setLayout(panBorder2Layout);
        panBorder2Layout.setHorizontalGroup(
            panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBorder2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(spinthresholdVal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(spinx2value, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinx1value, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spiny1value, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spiny2value, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinsensitivityVal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(spinrecordtime, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(51, 51, 51))
        );
        panBorder2Layout.setVerticalGroup(
            panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBorder2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinx1value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinx2value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spiny1value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spiny2value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinsensitivityVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinthresholdVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinrecordtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jScrollPane7.setViewportView(panBorder2);

        javax.swing.GroupLayout tabMotionDetectLayout = new javax.swing.GroupLayout(tabMotionDetect);
        tabMotionDetect.setLayout(tabMotionDetectLayout);
        tabMotionDetectLayout.setHorizontalGroup(
            tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVideoWaterMarkingConfigName1)
            .addGroup(tabMotionDetectLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pbarMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(278, 278, 278))
            .addGroup(tabMotionDetectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabMotionDetectLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(radCam1Motion)
                        .addGap(44, 44, 44)
                        .addComponent(radCam2Motion)
                        .addGap(18, 18, 18)
                        .addComponent(radCam3Motion)
                        .addGap(18, 18, 18)
                        .addComponent(radCam4Motion)
                        .addGap(163, 163, 163))
                    .addGroup(tabMotionDetectLayout.createSequentialGroup()
                        .addComponent(chkMotionDet, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tabMotionDetectLayout.createSequentialGroup()
                        .addComponent(btnSaveMotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefreshMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMsgMotionDetect, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        tabMotionDetectLayout.setVerticalGroup(
            tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMotionDetectLayout.createSequentialGroup()
                .addComponent(lblVideoWaterMarkingConfigName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(radCam1Motion)
                    .addComponent(radCam2Motion)
                    .addComponent(radCam3Motion)
                    .addComponent(radCam4Motion))
                .addGap(40, 40, 40)
                .addGroup(tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMotionDet)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pbarMotion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabMotionDetectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRefreshMotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMsgMotionDetect, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSaveMotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(588, 588, 588))
        );

        jTabbedPane2.addTab("MOTION DETECT", tabMotionDetect);

        jTabbedPane1.addTab("CAMERA", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

        if (cmbDeleteFiles.getSelectedIndex() == 0) {

            File[] route_files = null;
            lblDelete.setText(" ");

            try {
                try {

                    if (route_filepath.exists()) {
                        route_files = route_filepath.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            if (route_files[l].getName().equals("audio.WAV") || route_files[l].getName().equals("ring.wav") || route_files[l].getName().equals("canstoprequested.wav")) {

                            } else {
                                route_files[l].delete();
                            }
                        }
                    }

                    if (main_route_filepath.exists()) {
                        route_files = main_route_filepath.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            if (route_files[l].getName().equals("audio.WAV") || route_files[l].getName().equals("ring.wav") || route_files[l].getName().equals("canstoprequested.wav")) {

                            } else {
                                route_files[l].delete();
                            }
                        }
                    }

                } catch (Exception e) {
                }
                lblDelete.setText("All default route files deleted");

            } catch (Exception e) {
            } finally {

                route_files = null;
            }
        }
        if (cmbDeleteFiles.getSelectedIndex() == 1) {
            File[] files;

            lblDelete.setText(" ");
            try {
                if (log_filepath.exists()) {
                    files = log_filepath.listFiles();
                    for (File file : files) {
                        file.delete();
                    }
                }
                lblDelete.setText("All default log files deleted");

            } finally {
                files = null;
            }

        }
        if (cmbDeleteFiles.getSelectedIndex() == 2) {
            File[] files = null;
            File[] sub_files = null;
            lblDelete.setText(" ");
            int l;
            int i;
            try {
                try {

                    if (video_filepath.exists()) {
                        files = video_filepath.listFiles();
                        //route_files1 = f.listFiles();

                        for (i = 0; i < files.length; i++) { //video files
                            if (files[i].isDirectory()) {
                                sub_files = files[i].listFiles();

                                if (sub_files.length > 0) {
                                    for (l = 0; l < sub_files.length; l++) {
                                        sub_files[l].delete();

                                    }
                                }
                            } else {
                                files[i].delete();
                            }
                        }
                    }

                    if (main_video_filepath.exists()) {
                        files = main_video_filepath.listFiles();
                        for (i = 0; i < files.length; i++) {
                            if (files[i].isDirectory()) {
                                sub_files = files[i].listFiles();

                                if (sub_files.length > 0) {
                                    for (l = 0; l < sub_files.length; l++) {
                                        sub_files[l].delete();

                                    }
                                }
                            } else {
                                files[i].delete();
                            }
                        }
                    }
                } catch (Exception e) {

                }
                lblDelete.setText("All default Videos files deleted");

            } catch (Exception e) {
            } finally {
                files = null;
                sub_files = null;
            }
        }
        if (cmbDeleteFiles.getSelectedIndex() == 3) {
            File file;
            File[] files = null;
            File[] sub_files = null;
            lblDelete.setText(" ");
            try {
                try {
                    file = new File(can_filepath + "/");
                    if (file.exists()) {
                        files = file.listFiles();

                        for (int i = 0; i < files.length; i++) {
                            if (files[i].isDirectory()) {
                                sub_files = files[i].listFiles();

                                if (sub_files.length > 0) {
                                    for (int l = 0; l < sub_files.length; l++) {
                                        sub_files[l].delete();
                                    }
                                }
                            } else {
                                files[i].delete();
                            }
                        }
                    }

                } catch (Exception e) {
                }
                lblDelete.setText("All default CAN files deleted");

            } catch (Exception e) {
            } finally {
                file = null;
                files = null;
                sub_files = null;

            }

        }
        if (cmbDeleteFiles.getSelectedIndex() == 4) {
            File file;
            File[] route_files;

            lblDelete.setText(" ");
            clsReadFiles obj = new clsReadFiles();

            try {

                try {
                    file = new File(media_filepath + "/canstoreddata");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    file = new File(media_filepath + "/canstoreddata2");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    file = new File(media_filepath + "/canstoreddata4");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    file = new File(media_filepath + "/canstoreddata5");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    file = new File(media_filepath + "/storeddata");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    file = new File(media_filepath + "/storeddata2");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    file = new File(media_filepath + "/storeddata2");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    file = new File(media_filepath + "/storeddata3");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    file = new File(media_filepath + "/storeddata4");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {
                }
                try {
                    file = new File(media_filepath + "/storeddata5");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    file = new File(media_filepath + "/NonGpsPkts");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                try {
                    file = new File(media_filepath + "/NonGpsPkts2");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    file = new File(media_filepath + "/NonGpsPkts4");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }
                try {
                    file = new File(media_filepath + "/NonGpsPkts5");
                    if (file.exists()) {
                        route_files = file.listFiles();
                        for (int l = 0; l < route_files.length; l++) {
                            route_files[l].delete();
                        }
                    }
                } catch (Exception e) {

                }

                obj.write_storedData_Params_file("gpsStoredData1.txt", 1);
                obj.write_storedData_Params_file2("gpsStoredData1.txt", 1);
                obj.write_storedData_Params_file2("gpsStoredData1.txt", 1);

                lblDelete.setText("All Default stored log files deleted");

            } catch (Exception e) {
                //  ////System.out.println(e);
            } finally {
                file = null;

                route_files = null;
            }
        }
        if (cmbDeleteFiles.getSelectedIndex() == 5) {
            File[] files = null;
            File[] sub_files = null;
            lblDelete.setText(" ");
            File file;
            File[] route_files = null;
            int l;
            int i;
            try {
                try {

                    if (route_filepath.exists()) {
                        route_files = route_filepath.listFiles();
                        for (l = 0; l < route_files.length; l++) {
                            if (route_files[l].getName().equals("audio.WAV") || route_files[l].getName().equals("ring.wav") || route_files[l].getName().equals("canstoprequested.wav")) {

                            } else {
                                route_files[l].delete();
                            }
                        }
                    }

                    if (main_route_filepath.exists()) {
                        route_files = main_route_filepath.listFiles();
                        for (l = 0; l < route_files.length; l++) {
                            if (route_files[l].getName().equals("audio.WAV") || route_files[l].getName().equals("ring.wav") || route_files[l].getName().equals("canstoprequested.wav")) {

                            } else {
                                route_files[l].delete();
                            }
                        }
                    }

                } catch (Exception e) {
                }
                try {
                    if (log_filepath.exists()) {
                        files = log_filepath.listFiles();
                        for (File filess : files) {
                            filess.delete();
                        }
                    }
                } catch (Exception ex) {

                }
                try {
                    file = new File(can_filepath + "/");
                    if (file.exists()) {
                        files = file.listFiles();

                        for (i = 0; i < files.length; i++) {
                            if (files[i].isDirectory()) {
                                sub_files = files[i].listFiles();

                                if (sub_files.length > 0) {
                                    for (l = 0; l < sub_files.length; l++) {
                                        sub_files[l].delete();
                                    }
                                }
                            } else {
                                files[i].delete();
                            }
                        }
                    }

                } catch (Exception e) {
                }
                try {
                    if (video_filepath.exists()) {
                        files = video_filepath.listFiles();
                        //route_files1 = f.listFiles();

                        for (i = 0; i < files.length; i++) { //video files
                            if (files[i].isDirectory()) {
                                sub_files = files[i].listFiles();

                                if (sub_files.length > 0) {
                                    for (l = 0; l < sub_files.length; l++) {
                                        sub_files[l].delete();

                                    }
                                }
                            } else {
                                files[i].delete();
                            }
                        }
                    }

                    if (main_video_filepath.exists()) {
                        files = main_video_filepath.listFiles();
                        for (i = 0; i < files.length; i++) {
                            if (files[i].isDirectory()) {
                                sub_files = files[i].listFiles();

                                if (sub_files.length > 0) {
                                    for (l = 0; l < sub_files.length; l++) {
                                        sub_files[l].delete();

                                    }
                                }
                            } else {
                                files[i].delete();
                            }
                        }
                    }
                } catch (Exception e) {

                }
                try {

                    try {
                        file = new File(media_filepath + "/canstoreddata");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }

                    try {
                        file = new File(media_filepath + "/canstoreddata2");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        file = new File(media_filepath + "/canstoreddata4");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }

                    try {
                        file = new File(media_filepath + "/storeddata");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }

                    try {
                        file = new File(media_filepath + "/storeddata2");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        file = new File(media_filepath + "/storeddata2");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }

                    try {
                        file = new File(media_filepath + "/storeddata3");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        file = new File(media_filepath + "/storeddata4");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        file = new File(media_filepath + "/storeddata5");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }

                    try {
                        file = new File(media_filepath + "/NonGpsPkts");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }

                    try {
                        file = new File(media_filepath + "/NonGpsPkts2");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        file = new File(media_filepath + "/NonGpsPkts4");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        file = new File(media_filepath + "/NonGpsPkts5");
                        if (file.exists()) {
                            route_files = file.listFiles();
                            for (l = 0; l < route_files.length; l++) {
                                route_files[l].delete();
                            }
                        }
                    } catch (Exception e) {

                    }
                    clsReadFiles obj = new clsReadFiles();
                    obj.write_storedData_Params_file("gpsStoredData1.txt", 1);
                    obj.write_storedData_Params_file2("gpsStoredData1.txt", 1);
                    obj.write_storedData_Params_file2("gpsStoredData1.txt", 1);

                    // runCmd("sudo rm -rf /media/pi/3339OBU/Route/routemas.txt");
                } catch (Exception e) {
                    //  ////System.out.println(e);
                } finally {
                    file = null;

                    route_files = null;
                }
                lblDelete.setText("All files deleted");
            } catch (Exception ex) {

                //  ////System.out.println(e);
            } finally {
                files = null;
                sub_files = null;
            }
        }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void chkOnvifSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOnvifSupportActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkOnvifSupportActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        lblMsg.setText("Please wait for 2 min  ....");
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                clsReadFiles objReadFiles = new clsReadFiles();

                if (chkRestartPortsDetect.isSelected()) {
                    clsSharedVariables.setRestartPortsDetected(true);
                } else {
                    clsSharedVariables.setRestartPortsDetected(false);
                }

                if (chkApc.isSelected()) {
                    clsSharedVariables.setApcEnabled(true);
                } else {
                    clsSharedVariables.setApcEnabled(false);
                }
                if (chkApc1.isSelected()) {
                    clsSharedVariables.setApcEnabled1(true);
                } else {
                    clsSharedVariables.setApcEnabled1(false);
                }
                if (chkRadioEnable.isSelected()) {
                    clsSharedVariables.setFmRadioEnable(true);
                } else {
                    clsSharedVariables.setFmRadioEnable(false);
                }
                if (chkPedEnable.isSelected()) {
                    clsSharedVariables.setPedEnable(true);
                } else {
                    clsSharedVariables.setPedEnable(false);
                }

                if (chkSeparateGPS.isSelected()) {
                    clsSharedVariables.setSeparateGPS(true);
                } else {
                    clsSharedVariables.setSeparateGPS(false);
                }
                if (chkPmiEnable.isSelected()) {
                    clsSharedVariables.setPmiEnable(true);
                } else {
                    clsSharedVariables.setPmiEnable(false);
                }
                if (chkSpelAudioAnnounc.isSelected()) {
                    clsSharedVariables.setSplAudioAnnouncement(true);
                } else {
                    clsSharedVariables.setSplAudioAnnouncement(false);
                }
                if (chkNetwork.isSelected()) {
                    clsSharedVariables.setSharedNetwork(true);
                } else {
                    clsSharedVariables.setSharedNetwork(false);
                }

                if (cmbModuleType.getSelectedIndex() == 1) {
                    clsSharedVariables.setQuecModuleRev(clsDefines.QUECTEL_MOD_EC20);
                } else if (cmbModuleType.getSelectedIndex() == 2) {
                    clsSharedVariables.setQuecModuleRev(clsDefines.QUECTEL_MOD_EC25);
                } else {
                    clsSharedVariables.setQuecModuleRev((byte) 0);
                }

                objReadFiles.write_product_storage_name();
                objReadFiles.write_logOnvifenabled_data();
                //lblMsg.setText("Configuration Saved");

                objReadFiles = null;
                /* clsOnvifUrlCamera onvifUrls = new clsOnvifUrlCamera();
                onvifUrls.update_cam1_urls();
                onvifUrls.update_cam2_urls();
                onvifUrls.update_cam3_urls();
                onvifUrls.update_cam4_urls();
                onvifUrls = null;*/
                return "true";
            }

            @Override
            public void done() {
                lblMsg.setText("Configuration Saved");
            }
        };
        sw1.execute();

    }//GEN-LAST:event_btnSaveActionPerformed

    private void chkSeparateGPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSeparateGPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSeparateGPSActionPerformed

    private void chkRestartPortsDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRestartPortsDetectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRestartPortsDetectActionPerformed

    private void btnModifyVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyVersionActionPerformed
        // TODO add your handling code here:
        // lblMsg.setText(" ");
        // String m = getPassword("Configuration Password");

        // if (m.equals(VERSION_PWD)) {
        //    this.cmbModVersion.setVisible(true);
        //  } else {
        //  this.cmbModVersion.setVisible(false);
        //show_message_dialogbox("Wrong Password");
        //    lblMsg.setText("Wrong Password");
        //   }
        // close_keypad();
    }//GEN-LAST:event_btnModifyVersionActionPerformed

    private void cmbModVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbModVersionActionPerformed
        // TODO add your handling code here:
        lblMsg.setText("");
    }//GEN-LAST:event_cmbModVersionActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed

        if (this.cmbModVersion.getSelectedIndex() == 0) {
            clsDefines.COMPANY_NAME_NMEA_PROT = COMP_SUMITH;
        } else if (this.cmbModVersion.getSelectedIndex() == 1) {
            clsDefines.COMPANY_NAME_NMEA_PROT = clsDefines.COMP_JBM;
        } else if (this.cmbModVersion.getSelectedIndex() == 2) {
            clsDefines.COMPANY_NAME_NMEA_PROT = clsDefines.COMP_AMINEX;
        } else if (this.cmbModVersion.getSelectedIndex() == 3) {
            clsDefines.COMPANY_NAME_NMEA_PROT = clsDefines.COMP_DIMTS_NAGPUR;
        }
        clsReadFiles objReadFiles = new clsReadFiles();
        objReadFiles.write_application_version_file(String.valueOf(clsDefines.COMPANY_NAME_NMEA_PROT));
        objReadFiles = null;

        lblMsg4.setText(String.valueOf(clsDefines.COMPANY_NAME_NMEA_PROT));
        //this.cmbModVersion.setVisible(false);
        // this.btnSave.setVisible(false);
        lblMsg4.setText("Configuration Saved");
        clsDefines.COMPANY_NAME_NMEA_PROT_JBMTESTING = clsDefines.COMPANY_NAME_NMEA_PROT;
        close_keypad();
    }//GEN-LAST:event_btnSave1ActionPerformed
    private void close_keypad() {
        prev_control_name = "";

    }
    private void chkCameraTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCameraTypeItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_chkCameraTypeItemStateChanged

    private void chkCameraTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCameraTypeActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkCameraTypeActionPerformed

    private void chkRadioEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRadioEnableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRadioEnableActionPerformed

    private void chkPedEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPedEnableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPedEnableActionPerformed

    private void chkEthernetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEthernetActionPerformed
        // TODO add your handling code here:
        if (chkEthernet.isSelected()) {
            panBorder.setVisible(true);
        } else {
            panBorder.setVisible(false);
        }
    }//GEN-LAST:event_chkEthernetActionPerformed

    private void txtPortNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortNoFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("IP Addr")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("IP Addr", txtPortNo.getText());
            if (str != null) {
                txtPortNo.setText(str);
            }
            prev_control_name = "IP Addr";
            obj = null;
            str = null;
            txtPortNo.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtPortNoFocusGained

    private void txtPortNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortNoFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtPortNoFocusLost

    private void txtIpAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIpAddressFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Primary Port no")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Primary Port no", txtIpAddress.getText());
            if (str != null) {
                txtIpAddress.setText(str);
            }
            prev_control_name = "Primary Port no";
            obj = null;
            str = null;
            txtPortNo.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtIpAddressFocusGained

    private void txtIpAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIpAddressFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtIpAddressFocusLost

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
        clsReadFiles obj = new clsReadFiles();
        if (chkEthernet.isSelected() == true) {
            clsSharedVariables.setEthernetEnable(true);
        } else {
            clsSharedVariables.setEthernetEnable(false);
        }
        if (radGpsData.isSelected()) {
            clsSharedVariables.setEthernetType(clsDefines.ETHERNET_GPS);
        } else {
            clsSharedVariables.setEthernetType(clsDefines.ETHERNET_RFID);
        }

        clsSharedVariables.setEthernetGpsModeInterval((int) spinInterval.getValue());
        clsSharedVariables.setEthernetPortNo(Integer.parseInt(String.valueOf(txtPortNo.getText())));
        clsSharedVariables.setEthernetIpAddr((String.valueOf(txtIpAddress.getText())));

        if (obj.write_cfg_data_file()) {
            lblMsg1.setText(" Device Details Saved");
        } else {
            lblMsg1.setText(" Device Details Not Saved");
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void chkRs232ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRs232ActionPerformed
        // TODO add your handling code here:
        if (chkRs232.isSelected()) {
            panBorder1.setVisible(true);
        } else {
            panBorder1.setVisible(false);
        }
    }//GEN-LAST:event_chkRs232ActionPerformed

    private void btnSave3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave3ActionPerformed
        // TODO add your handling code here:
        clsReadFiles obj = new clsReadFiles();
        if (chkRs232.isSelected() == true) {
            clsSharedVariables.setRs232Enable(true);
        } else {
            clsSharedVariables.setRs232Enable(false);
        }
        if (radGpsDataRs232.isSelected()) {
            clsSharedVariables.setRs232Type(clsDefines.RS232_GPS);
        } else if(radRfidRs232.isSelected()){
            clsSharedVariables.setRs232Type(clsDefines.RS232_RFID);
        }else{
             clsSharedVariables.setRs232Type(clsDefines.RS232_ALCO);
        }

        clsSharedVariables.setRs232GpsModeInterval((int) spinIntervalRs232.getValue());

        clsSharedVariables.setRs232BaudRate(Integer.parseInt(String.valueOf(cmbBaudRate.getSelectedItem())));

        if (obj.write_cfg_data_file()) {
            lblMsg2.setText(lblMsg2.getText() + " Device Details Saved");

        } else {
            lblMsg2.setText(lblMsg2.getText() + " Device Details Not Saved");

        }
    }//GEN-LAST:event_btnSave3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void txtSerialNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSerialNoFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Serial No")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Serial No", txtSerialNo.getText());
            if (str != null) {
                txtSerialNo.setText(str);
            }
            prev_control_name = "Serial No";
            obj = null;
            str = null;
            txtSerialNo.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtSerialNoFocusGained

    private void txtSerialNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSerialNoFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtSerialNoFocusLost

    private void txtSerialNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerialNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerialNoActionPerformed

    private void btnSave4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave4ActionPerformed
        lblMsg3.setText("");
        clsReadFiles obj = new clsReadFiles();

        clsSharedVariables.serial_no = this.txtSerialNo.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");//yyyy-MM-dd HH:mm:ss.SS");
        try {
            clsSharedVariables.test_date_time_pid = sdf.format(this.spinTestDate.getValue());
        } catch (Exception ex) {

        }

        clsSharedVariables.hw_revision = this.spinHwRev.getValue().toString();
        clsSharedVariables.compilation_fw_datetime = sdf.format(this.spinComDate.getValue());
        clsSharedVariables.obu_watchdog_reset_cnt = Short.parseShort(this.spinWatchdogReset.getValue().toString());
        clsSharedVariables.obu_low_volt_reset_cnt = Short.parseShort(this.spinLowVoltReset.getValue().toString());
        clsSharedVariables.obu_high_volt_cnt = Short.parseShort(this.spinOverVolt.getValue().toString());
        clsSharedVariables.obu_low_volt_cnt = Short.parseShort(this.spinLowVoltage.getValue().toString());
        clsSharedVariables.obu_over_heat_cnt = Short.parseShort(this.spinOverHeat.getValue().toString());
        clsSharedVariables.gps_lost_comm_cnt = Short.parseShort(this.spinGpsLost.getValue().toString());
        clsSharedVariables.gps_invalid_data_cnt = Short.parseShort(this.spinGpsInvalid.getValue().toString());
        clsSharedVariables.gps_antenna_error_cnt = Short.parseShort(this.spinGpsAntenna.getValue().toString());
        clsSharedVariables.usb_invalid_cnt = Short.parseShort(this.spinUsbInvalid.getValue().toString());
        clsSharedVariables.usb_unknown_cnt = Short.parseShort(this.spinUsbUnknown.getValue().toString());
        clsSharedVariables.usb_invalid_filesystem_cnt = Short.parseShort(this.spinUsbInvalFileSys.getValue().toString());
        clsSharedVariables.usb_overcurrent_cnt = Short.parseShort(this.spinUsbOverCurrent.getValue().toString());
        clsSharedVariables.no_times_reset = Short.parseShort(this.spinDevResetCnt.getValue().toString());

        obj.write_reset_data();
        if (obj.write_pid_codes()) {
            lblMsg3.setText(lblMsg3.getText() + " Device Details Saved");

        } else {
            lblMsg3.setText(lblMsg3.getText() + " Device Details Not Saved");

        }
    }//GEN-LAST:event_btnSave4ActionPerformed

    private void btnSave5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave5ActionPerformed
        // TODO add your handling code here:

        try {
            if (cmbSelectCameraNo.getSelectedIndex() == 0) {
                clsSharedVariables.setLiveType(clsDefines.CAM_1);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(false);
                setCam3Enabled(false);
                setCam4Enabled(false);
                setCam5Enabled(false);
                setCam6Enabled(false);
                setCam7Enabled(false);
                setCam8Enabled(false);
                chkCam2Enable.setSelected(false);
                chkCam3Enable.setSelected(false);
                chkCam4Enable.setSelected(false);
//                chkCam5Enable.setSelected(false);
//                chkCam6Enable.setSelected(false);
//                chkCam7Enable.setSelected(false);
//                chkCam8Enable.setSelected(false);
            } else if (cmbSelectCameraNo.getSelectedIndex() == 1) {
                clsSharedVariables.setLiveType(clsDefines.CAM_2);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(false);
                setCam4Enabled(false);
                setCam5Enabled(false);
                setCam6Enabled(false);
                setCam7Enabled(false);
                setCam8Enabled(false);
                chkCam3Enable.setSelected(false);
                chkCam4Enable.setSelected(false);
//                chkCam5Enable.setSelected(false);
//                chkCam6Enable.setSelected(false);
//                chkCam7Enable.setSelected(false);
//                chkCam8Enable.setSelected(false);
            } else if (cmbSelectCameraNo.getSelectedIndex() == 2) {
                clsSharedVariables.setLiveType(clsDefines.CAM_3);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(this.chkCam3Enable.isSelected());
                setCam4Enabled(false);
                setCam5Enabled(false);
                setCam6Enabled(false);
                setCam7Enabled(false);
                setCam8Enabled(false);
                chkCam4Enable.setSelected(false);
//                chkCam5Enable.setSelected(false);
//                chkCam6Enable.setSelected(false);
//                chkCam7Enable.setSelected(false);
//                chkCam8Enable.setSelected(false);
            } else if (cmbSelectCameraNo.getSelectedIndex() == 3) {
                clsSharedVariables.setLiveType(clsDefines.CAM_4);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(this.chkCam3Enable.isSelected());
                setCam4Enabled(this.chkCam4Enable.isSelected());
                setCam5Enabled(false);
                setCam6Enabled(false);
                setCam7Enabled(false);
                setCam8Enabled(false);

//                chkCam5Enable.setSelected(false);
//                chkCam6Enable.setSelected(false);
//                chkCam7Enable.setSelected(false);
//                chkCam8Enable.setSelected(false);

            } else if (cmbSelectCameraNo.getSelectedIndex() == 4) {
                clsSharedVariables.setLiveType(clsDefines.CAM_5);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(this.chkCam3Enable.isSelected());
                setCam4Enabled(this.chkCam4Enable.isSelected());
//                setCam5Enabled(this.chkCam5Enable.isSelected());
                setCam6Enabled(false);
                setCam7Enabled(false);
                setCam8Enabled(false);

//                chkCam6Enable.setSelected(false);
//                chkCam7Enable.setSelected(false);
//                chkCam8Enable.setSelected(false);
            } else if (cmbSelectCameraNo.getSelectedIndex() == 5) {
                clsSharedVariables.setLiveType(clsDefines.CAM_6);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(this.chkCam3Enable.isSelected());
                setCam4Enabled(this.chkCam4Enable.isSelected());
//                setCam5Enabled(this.chkCam5Enable.isSelected());
//                setCam6Enabled(this.chkCam6Enable.isSelected());
                setCam7Enabled(false);
                setCam8Enabled(false);
//                chkCam7Enable.setSelected(false);
//                chkCam8Enable.setSelected(false);
            } else if (cmbSelectCameraNo.getSelectedIndex() == 6) {
                clsSharedVariables.setLiveType(clsDefines.CAM_7);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(this.chkCam3Enable.isSelected());
                setCam4Enabled(this.chkCam4Enable.isSelected());
//                setCam5Enabled(this.chkCam5Enable.isSelected());
//                setCam6Enabled(this.chkCam6Enable.isSelected());
//                setCam7Enabled(this.chkCam7Enable.isSelected());
                setCam8Enabled(false);
//                chkCam8Enable.setSelected(false);
            } else if (cmbSelectCameraNo.getSelectedIndex() == 7) {
                clsSharedVariables.setLiveType(clsDefines.CAM_8);
                setCam1Enabled(this.chkCam1Enable.isSelected());
                setCam2Enabled(this.chkCam2Enable.isSelected());
                setCam3Enabled(this.chkCam3Enable.isSelected());
                setCam4Enabled(this.chkCam4Enable.isSelected());
//                setCam5Enabled(this.chkCam5Enable.isSelected());
//                setCam6Enabled(this.chkCam6Enable.isSelected());
//                setCam7Enabled(this.chkCam7Enable.isSelected());
//                setCam8Enabled(this.chkCam8Enable.isSelected());
            }

            if (chkCam1Enable.isSelected()) {
                clsSharedVariables.setCam1Type((byte) this.cmbCam1Type.getSelectedIndex());
                clsSharedVariables.setCam1UserName(this.txtCam1User.getText());
                clsSharedVariables.setCam1Pwd(this.PasswordCam1.getText());
            }

            if (chkCam2Enable.isSelected()) {
                clsSharedVariables.setCam2Type((byte) this.cmbCam2Type.getSelectedIndex());
                clsSharedVariables.setCam2UserName(this.txtCam2User.getText());
                clsSharedVariables.setCam2Pwd(this.PasswordCam2.getText());
            }

            if (chkCam3Enable.isSelected()) {
                clsSharedVariables.setCam3Type((byte) this.cmbCam3Type.getSelectedIndex());
                clsSharedVariables.setCam3UserName(this.txtCam3User.getText());
                clsSharedVariables.setCam3Pwd(this.PasswordCam3.getText());
            }

            if (chkCam4Enable.isSelected()) {
                clsSharedVariables.setCam4Type((byte) this.cmbCam4Type.getSelectedIndex());
                clsSharedVariables.setCam4UserName(this.txtCam4User.getText());
                clsSharedVariables.setCam4Pwd(this.PasswordCam4.getText());
            }

//            if (chkCam5Enable.isSelected()) {
//                clsSharedVariables.setCam5Type((byte) this.cmbCam5Type.getSelectedIndex());
//                clsSharedVariables.setCam5UserName(this.txtCam5User.getText());
//                clsSharedVariables.setCam5Pwd(this.PasswordCam5.getText());
//            }
//
//            if (chkCam6Enable.isSelected()) {
//                clsSharedVariables.setCam6Type((byte) this.cmbCam6Type.getSelectedIndex());
//                clsSharedVariables.setCam6UserName(this.txtCam6User.getText());
//                clsSharedVariables.setCam6Pwd(this.PasswordCam6.getText());
//            }
//
//            if (chkCam7Enable.isSelected()) {
//                clsSharedVariables.setCam7Type((byte) this.cmbCam7Type.getSelectedIndex());
//                clsSharedVariables.setCam7UserName(this.txtCam7User.getText());
//                clsSharedVariables.setCam7Pwd(this.PasswordCam7.getText());
//            }
//
//            if (chkCam8Enable.isSelected()) {
//                clsSharedVariables.setCam8Type((byte) this.cmbCam8Type.getSelectedIndex());
//                clsSharedVariables.setCam8UserName(this.txtCam8User.getText());
//                clsSharedVariables.setCam8Pwd(this.PasswordCam8.getText());
//            }
            clsReadFiles objReadFiles = new clsReadFiles();
            if (objReadFiles.write_cfg_data_file()) {
                lblMsgCamera.setText("Restart OBU to get Into Effect, Configuration Saved ");
            } else {
                lblMsgCamera.setText("Configuration Not Saved");
            }

            if (clsSharedVariables.getCam1Enabled() == true) {
                MainFrmIts.imgCam1.setVisible(true);
            } else {
                MainFrmIts.imgCam1.setVisible(false);
                setVideo1Connected(false);
            }

            if (clsSharedVariables.getCam2Enabled() == true) {
                MainFrmIts.imgCam2.setVisible(true);
            } else {
                MainFrmIts.imgCam2.setVisible(false);
                setVideo2Connected(false);
            }

            if (clsSharedVariables.getCam3Enabled() == true) {
                MainFrmIts.imgCam3.setVisible(true);
            } else {
                MainFrmIts.imgCam3.setVisible(false);
                setVideo3Connected(false);
            }

            if (clsSharedVariables.getCam4Enabled() == true) {
                MainFrmIts.imgCam4.setVisible(true);
            } else {
                MainFrmIts.imgCam4.setVisible(false);
                setVideo4Connected(false);
            }
//            if (clsSharedVariables.getCam5Enabled() == true) {
//                MainFrmIts.imgCam5.setVisible(true);
//            } else {
//                MainFrmIts.imgCam5.setVisible(false);
//                clsSharedVariables.setVideo5Connected(false);
//            }
//            if (clsSharedVariables.getCam6Enabled() == true) {
//                MainFrmIts.imgCam6.setVisible(true);
//            } else {
//                MainFrmIts.imgCam6.setVisible(false);
//                clsSharedVariables.setVideo6Connected(false);
//            }
//
//            if (clsSharedVariables.getCam7Enabled() == true) {
//                MainFrmIts.imgCam7.setVisible(true);
//            } else {
//                MainFrmIts.imgCam7.setVisible(false);
//                clsSharedVariables.setVideo7Connected(false);
//            }
//            if (clsSharedVariables.getCam8Enabled() == true) {
//                MainFrmIts.imgCam8.setVisible(true);
//            } else {
//                MainFrmIts.imgCam8.setVisible(false);
//                clsSharedVariables.setVideo8Connected(false);
//            }

        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnSave5ActionPerformed

    private void chkCam3EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam3EnableActionPerformed
        // TODO add your handling code here:
        if (chkCam3Enable.isSelected()) {
            chkCam3Enable.setText("Enable");
            pan3.setVisible(true);

        } else {
            chkCam3Enable.setText("Disable");
            pan3.setVisible(false);

        }
    }//GEN-LAST:event_chkCam3EnableActionPerformed

    private void txtCam3UserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam3UserFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam3 user")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam3 user", txtCam3User.getText());
            if (str != null) {
                txtCam3User.setText(str);
            }
            prev_control_name = "Cam3 user";
            obj = null;
            str = null;
            txtCam3User.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCam3UserFocusGained

    private void txtCam3UserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam3UserFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtCam3UserFocusLost

    private void PasswordCam3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam3FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam3 Pwd")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam3 Pwd", PasswordCam3.getText());
            if (str != null) {
                PasswordCam3.setText(str);
            }
            prev_control_name = "Cam3 Pwd";
            obj = null;
            str = null;
            PasswordCam3.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_PasswordCam3FocusGained

    private void PasswordCam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordCam3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordCam3ActionPerformed

    private void chkCam1EnableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chkCam1EnableFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam1EnableFocusGained

    private void chkCam1EnableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chkCam1EnableFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam1EnableFocusLost

    private void chkCam1EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam1EnableActionPerformed
        // TODO add your handling code here:
        if (chkCam1Enable.isSelected()) {
            chkCam1Enable.setText("Enable");
            pan1.setVisible(true);

        } else {
            chkCam1Enable.setText("Disable");
            pan1.setVisible(false);

        }
    }//GEN-LAST:event_chkCam1EnableActionPerformed

    private void txtCam1UserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam1UserFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam1 user")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam1 user", txtCam1User.getText());
            if (str != null) {
                txtCam1User.setText(str);
            }
            prev_control_name = "Cam1 user";
            obj = null;
            str = null;
            txtCam1User.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCam1UserFocusGained

    private void txtCam1UserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam1UserFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtCam1UserFocusLost

    private void txtCam1UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCam1UserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCam1UserActionPerformed

    private void PasswordCam1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam1FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam1 Pwd")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam1 Pwd", PasswordCam1.getText());
            if (str != null) {
                PasswordCam1.setText(str);
            }
            prev_control_name = "Cam1 Pwd";
            obj = null;
            str = null;
            PasswordCam1.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_PasswordCam1FocusGained

    private void PasswordCam1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam1FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_PasswordCam1FocusLost

    private void chkCam4EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam4EnableActionPerformed
        // TODO add your handling code here:
        if (chkCam4Enable.isSelected()) {
            chkCam4Enable.setText("Enable");
            pan4.setVisible(true);

        } else {
            chkCam4Enable.setText("Disable");
            pan4.setVisible(false);

        }
    }//GEN-LAST:event_chkCam4EnableActionPerformed

    private void txtCam4UserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam4UserFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam4 user")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam4 user", txtCam4User.getText());
            if (str != null) {
                txtCam4User.setText(str);
            }
            prev_control_name = "Cam4 user";
            obj = null;
            str = null;
            txtCam4User.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCam4UserFocusGained

    private void PasswordCam4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam4FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam4 Pwd")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam4 Pwd", PasswordCam4.getText());
            if (str != null) {
                PasswordCam4.setText(str);
            }
            prev_control_name = "Cam4 Pwd";
            obj = null;
            str = null;
            PasswordCam4.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_PasswordCam4FocusGained

    private void PasswordCam4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam4FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_PasswordCam4FocusLost

    private void chkCam2EnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam2EnableActionPerformed
        // TODO add your handling code here:
        if (chkCam2Enable.isSelected()) {
            chkCam2Enable.setText("Enable");
            pan2.setVisible(true);

        } else {
            chkCam2Enable.setText("Disable");
            pan2.setVisible(false);

        }
    }//GEN-LAST:event_chkCam2EnableActionPerformed

    private void txtCam2UserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam2UserFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam2 user")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam2 user", txtCam2User.getText());
            if (str != null) {
                txtCam2User.setText(str);
            }
            prev_control_name = "Cam2 user";
            obj = null;
            str = null;
            txtCam2User.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCam2UserFocusGained

    private void txtCam2UserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCam2UserFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtCam2UserFocusLost

    private void PasswordCam2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam2FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Cam2 Pwd")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Cam2 Pwd", PasswordCam2.getText());
            if (str != null) {
                PasswordCam2.setText(str);
            }
            prev_control_name = "Cam2 Pwd";
            obj = null;
            str = null;
            PasswordCam2.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_PasswordCam2FocusGained

    private void PasswordCam2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordCam2FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_PasswordCam2FocusLost

    private void btnSaveSnapshotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSnapshotActionPerformed
        // TODO add your handling code here:
        lblMsgSnapshot.setText("");
        if (radCam1.isSelected()) {
            if (chkSnapShotEnable.isSelected()) {
                clsSharedVariables.setSnapShotEnableCam1(true);

                if (chkSnapDig1.isSelected()) {
                    clsSharedVariables.setSnapShotDig1EnableCam1(true);
                    clsSharedVariables.setSnapShotDig1StreamCam1(cmbSnapEventStreamDig1.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig1IntervalCam1((int) this.spinSnapEventntervalDig1.getValue());
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam1(chkSnapDi1Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig1EnableCam1(false);
                    clsSharedVariables.setSnapShotDig1StreamCam1(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig1IntervalCam1(0);
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam1(false);
                }
                if (chkSnapDig2.isSelected()) {
                    clsSharedVariables.setSnapShotDig2EnableCam1(true);
                    clsSharedVariables.setSnapShotDig2StreamCam1(cmbSnapEventStreamDig2.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig2IntervalCam1((int) this.spinSnapEventntervalDig2.getValue());
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam1(chkSnapDi2Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig2EnableCam1(false);
                    clsSharedVariables.setSnapShotDig2StreamCam1(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig2IntervalCam1(0);
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam1(false);
                }

                if (chkSnapDig3.isSelected()) {
                    clsSharedVariables.setSnapShotDig3EnableCam1(true);
                    clsSharedVariables.setSnapShotDig3StreamCam1(cmbSnapEventStreamDig3.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig3IntervalCam1((int) this.spinSnapEventntervalDig3.getValue());
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam1(chkSnapDi3Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig3EnableCam1(false);
                    clsSharedVariables.setSnapShotDig3StreamCam1(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig3IntervalCam1(0);
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam1(false);
                }
                if (chkSnapDig4.isSelected()) {
                    clsSharedVariables.setSnapShotDig4EnableCam1(true);
                    clsSharedVariables.setSnapShotDig4StreamCam1(cmbSnapEventStreamDig4.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig4IntervalCam1((int) this.spinSnapEventntervalDig4.getValue());
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam1(chkSnapDi4Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig4EnableCam1(false);
                    clsSharedVariables.setSnapShotDig4StreamCam1(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig4IntervalCam1(0);
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam1(false);
                }   //chkSnapCont

                if (chkSnapCont.isSelected()) {
                    clsSharedVariables.setSnapShotContEnableCam1(true);

                    clsSharedVariables.setSnapShotContStreamCam1(cmbSnapContStreamType.getSelectedIndex());
                    clsSharedVariables.setSnapShotContIntervalCam1((int) spinSnapContInterval.getValue());
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam1(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContEnableCam1(false);
                    clsSharedVariables.setSnapShotContStreamCam1(clsDefines.VIDEO_MAIN_STREAM_TYPE);
                    clsSharedVariables.setSnapShotContIntervalCam1((int) 0);
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam1(false);

                }
                if (chkSnapContUpload.isSelected()) {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam1(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam1(false);
                }

            } else {
                clsSharedVariables.setSnapShotEnableCam1(false);
                clsSharedVariables.setSnapShotDig1FtpUploadEnableCam1(false);
                clsSharedVariables.setSnapShotDig2FtpUploadEnableCam1(false);
                clsSharedVariables.setSnapShotDig3FtpUploadEnableCam1(false);
                clsSharedVariables.setSnapShotDig4FtpUploadEnableCam1(false);
                clsSharedVariables.setSnapShotContFtpUploadEnableCam1(false);
                clsSharedVariables.setSnapShotDig1EnableCam1(false);
                clsSharedVariables.setSnapShotDig2EnableCam1(false);
                clsSharedVariables.setSnapShotDig3EnableCam1(false);
                clsSharedVariables.setSnapShotDig4EnableCam1(false);
                clsSharedVariables.setSnapShotContEnableCam1(false);
                clsSharedVariables.setEventBasedRecEnableCam1(false);
            }
        } else if (radCam2.isSelected()) {
            if (chkSnapShotEnable.isSelected()) {
                clsSharedVariables.setSnapShotEnableCam2(true);

                if (chkSnapDig1.isSelected()) {
                    clsSharedVariables.setSnapShotDig1EnableCam2(true);
                    clsSharedVariables.setSnapShotDig1StreamCam2(cmbSnapEventStreamDig1.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig1IntervalCam2((int) this.spinSnapEventntervalDig1.getValue());
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam2(chkSnapDi1Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig1EnableCam2(false);
                    clsSharedVariables.setSnapShotDig1StreamCam2(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig1IntervalCam2(0);
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam2(false);
                }
                if (chkSnapDig2.isSelected()) {
                    clsSharedVariables.setSnapShotDig2EnableCam2(true);
                    clsSharedVariables.setSnapShotDig2StreamCam2(cmbSnapEventStreamDig2.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig2IntervalCam2((int) this.spinSnapEventntervalDig2.getValue());
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam2(chkSnapDi2Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig2EnableCam2(false);
                    clsSharedVariables.setSnapShotDig2StreamCam2(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig2IntervalCam2(0);
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam2(false);
                }

                if (chkSnapDig3.isSelected()) {
                    clsSharedVariables.setSnapShotDig3EnableCam2(true);
                    clsSharedVariables.setSnapShotDig3StreamCam2(cmbSnapEventStreamDig3.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig3IntervalCam2((int) this.spinSnapEventntervalDig3.getValue());
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam2(chkSnapDi3Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig3EnableCam2(false);
                    clsSharedVariables.setSnapShotDig3StreamCam2(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig3IntervalCam2(0);
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam2(false);

                }
                if (chkSnapDig4.isSelected()) {
                    clsSharedVariables.setSnapShotDig4EnableCam2(true);
                    clsSharedVariables.setSnapShotDig4StreamCam2(cmbSnapEventStreamDig4.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig4IntervalCam2((int) this.spinSnapEventntervalDig4.getValue());
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam2(chkSnapDi4Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig4EnableCam2(false);
                    clsSharedVariables.setSnapShotDig4StreamCam2(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig4IntervalCam2(0);
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam2(false);
                }

                if (chkSnapCont.isSelected()) {
                    clsSharedVariables.setSnapShotContEnableCam2(true);

                    clsSharedVariables.setSnapShotContStreamCam2(cmbSnapContStreamType.getSelectedIndex());
                    clsSharedVariables.setSnapShotContIntervalCam2((int) spinSnapContInterval.getValue());
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam2(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContEnableCam2(false);
                    clsSharedVariables.setSnapShotContStreamCam2(clsDefines.VIDEO_MAIN_STREAM_TYPE);
                    clsSharedVariables.setSnapShotContIntervalCam2((int) 0);
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam2(false);

                }
                if (chkSnapContUpload.isSelected()) {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam2(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam2(false);
                }

            } else {
                clsSharedVariables.setSnapShotEnableCam2(false);
                clsSharedVariables.setSnapShotDig1EnableCam2(false);
                clsSharedVariables.setSnapShotContEnableCam2(false);
                clsSharedVariables.setEventBasedRecEnableCam2(false);
                clsSharedVariables.setSnapShotDig1FtpUploadEnableCam2(false);
                clsSharedVariables.setSnapShotDig2FtpUploadEnableCam2(false);
                clsSharedVariables.setSnapShotDig3FtpUploadEnableCam2(false);
                clsSharedVariables.setSnapShotDig4FtpUploadEnableCam2(false);
                clsSharedVariables.setSnapShotContFtpUploadEnableCam2(false);
                clsSharedVariables.setSnapShotDig2EnableCam2(false);
                clsSharedVariables.setSnapShotDig3EnableCam2(false);
                clsSharedVariables.setSnapShotDig4EnableCam2(false);

            }
        } else if (radCam3.isSelected()) {
            if (chkSnapShotEnable.isSelected()) {
                clsSharedVariables.setSnapShotEnableCam3(true);

                if (chkSnapDig1.isSelected()) {
                    clsSharedVariables.setSnapShotDig1EnableCam3(true);
                    clsSharedVariables.setSnapShotDig1StreamCam3(cmbSnapEventStreamDig1.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig1IntervalCam3((int) this.spinSnapEventntervalDig1.getValue());
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam3(chkSnapDi1Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig1EnableCam3(false);
                    clsSharedVariables.setSnapShotDig1StreamCam3(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig1IntervalCam3(0);
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam3(false);
                }
                if (chkSnapDig2.isSelected()) {
                    clsSharedVariables.setSnapShotDig2EnableCam3(true);
                    clsSharedVariables.setSnapShotDig2StreamCam3(cmbSnapEventStreamDig2.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig2IntervalCam3((int) this.spinSnapEventntervalDig2.getValue());
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam3(chkSnapDi2Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig2EnableCam3(false);
                    clsSharedVariables.setSnapShotDig2StreamCam3(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig2IntervalCam3(0);
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam3(false);
                }

                if (chkSnapDig3.isSelected()) {
                    clsSharedVariables.setSnapShotDig3EnableCam3(true);
                    clsSharedVariables.setSnapShotDig3StreamCam3(cmbSnapEventStreamDig3.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig3IntervalCam3((int) this.spinSnapEventntervalDig3.getValue());
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam3(chkSnapDi3Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig3EnableCam3(false);
                    clsSharedVariables.setSnapShotDig3StreamCam3(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig3IntervalCam3(0);
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam3(false);
                }
                if (chkSnapDig4.isSelected()) {
                    clsSharedVariables.setSnapShotDig4EnableCam3(true);
                    clsSharedVariables.setSnapShotDig4StreamCam3(cmbSnapEventStreamDig4.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig4IntervalCam3((int) this.spinSnapEventntervalDig4.getValue());
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam3(chkSnapDi4Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig4EnableCam3(false);
                    clsSharedVariables.setSnapShotDig4StreamCam3(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig4IntervalCam3(0);
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam3(false);
                }

                if (chkSnapCont.isSelected()) {
                    clsSharedVariables.setSnapShotContEnableCam3(true);
                    clsSharedVariables.setSnapShotContStreamCam3(cmbSnapContStreamType.getSelectedIndex());
                    clsSharedVariables.setSnapShotContIntervalCam3((int) spinSnapContInterval.getValue());
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam3(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContEnableCam3(false);
                    clsSharedVariables.setSnapShotContStreamCam3(clsDefines.VIDEO_MAIN_STREAM_TYPE);
                    clsSharedVariables.setSnapShotContIntervalCam3((int) 0);
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam3(false);

                }
                if (chkSnapContUpload.isSelected()) {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam3(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam3(false);
                }

            } else {
                clsSharedVariables.setSnapShotEnableCam3(false);
                clsSharedVariables.setSnapShotDig1EnableCam3(false);
                clsSharedVariables.setSnapShotContEnableCam3(false);
                clsSharedVariables.setEventBasedRecEnableCam3(false);
                clsSharedVariables.setSnapShotDig1FtpUploadEnableCam3(false);
                clsSharedVariables.setSnapShotDig2FtpUploadEnableCam3(false);
                clsSharedVariables.setSnapShotDig3FtpUploadEnableCam3(false);
                clsSharedVariables.setSnapShotDig4FtpUploadEnableCam3(false);
                clsSharedVariables.setSnapShotContFtpUploadEnableCam3(false);
                clsSharedVariables.setSnapShotDig2EnableCam3(false);
                clsSharedVariables.setSnapShotDig3EnableCam3(false);
                clsSharedVariables.setSnapShotDig4EnableCam3(false);
            }
        } else if (radCam4.isSelected()) {
            if (chkSnapShotEnable.isSelected()) {
                clsSharedVariables.setSnapShotEnableCam4(true);

                if (chkSnapDig1.isSelected()) {
                    clsSharedVariables.setSnapShotDig1EnableCam4(true);
                    clsSharedVariables.setSnapShotDig1StreamCam4(cmbSnapEventStreamDig1.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig1IntervalCam4((int) this.spinSnapEventntervalDig1.getValue());
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam4(chkSnapDi1Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig1EnableCam4(false);
                    clsSharedVariables.setSnapShotDig1StreamCam4(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig1IntervalCam4(0);
                    clsSharedVariables.setSnapShotDig1FtpUploadEnableCam4(false);
                }
                if (chkSnapDig2.isSelected()) {
                    clsSharedVariables.setSnapShotDig2EnableCam4(true);
                    clsSharedVariables.setSnapShotDig2StreamCam4(cmbSnapEventStreamDig2.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig2IntervalCam4((int) this.spinSnapEventntervalDig2.getValue());
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam4(chkSnapDi2Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig2EnableCam4(false);
                    clsSharedVariables.setSnapShotDig2StreamCam4(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig2IntervalCam4(0);
                    clsSharedVariables.setSnapShotDig2FtpUploadEnableCam4(false);
                }

                if (chkSnapDig3.isSelected()) {
                    clsSharedVariables.setSnapShotDig3EnableCam4(true);
                    clsSharedVariables.setSnapShotDig3StreamCam4(cmbSnapEventStreamDig3.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig3IntervalCam4((int) this.spinSnapEventntervalDig3.getValue());
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam4(chkSnapDi3Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig3EnableCam4(false);
                    clsSharedVariables.setSnapShotDig3StreamCam4(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig3IntervalCam4(0);
                    clsSharedVariables.setSnapShotDig3FtpUploadEnableCam4(false);
                }
                if (chkSnapDig4.isSelected()) {
                    clsSharedVariables.setSnapShotDig4EnableCam4(true);
                    clsSharedVariables.setSnapShotDig4StreamCam4(cmbSnapEventStreamDig4.getSelectedIndex());
                    clsSharedVariables.setSnapShotDig4IntervalCam4((int) this.spinSnapEventntervalDig4.getValue());
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam4(chkSnapDi4Upload.isSelected());
                } else {
                    clsSharedVariables.setSnapShotDig4EnableCam4(false);
                    clsSharedVariables.setSnapShotDig4StreamCam4(clsDefines.VIDEO_SUB_STREAM_TYPE);
                    clsSharedVariables.setSnapShotDig4IntervalCam4(0);
                    clsSharedVariables.setSnapShotDig4FtpUploadEnableCam4(false);
                }

                if (chkSnapCont.isSelected()) {
                    clsSharedVariables.setSnapShotContEnableCam4(true);

                    clsSharedVariables.setSnapShotContStreamCam4(cmbSnapContStreamType.getSelectedIndex());
                    clsSharedVariables.setSnapShotContIntervalCam4((int) spinSnapContInterval.getValue());
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam4(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContEnableCam4(false);
                    clsSharedVariables.setSnapShotContStreamCam4(clsDefines.VIDEO_MAIN_STREAM_TYPE);
                    clsSharedVariables.setSnapShotContIntervalCam4((int) 0);
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam4(false);

                }
                if (chkSnapContUpload.isSelected()) {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam4(chkSnapContUpload.isSelected());

                } else {
                    clsSharedVariables.setSnapShotContFtpUploadEnableCam4(false);
                }

            } else {
                clsSharedVariables.setSnapShotEnableCam4(false);
                clsSharedVariables.setSnapShotDig1EnableCam4(false);
                clsSharedVariables.setSnapShotContEnableCam4(false);
                clsSharedVariables.setEventBasedRecEnableCam4(false);
                clsSharedVariables.setSnapShotDig1FtpUploadEnableCam4(false);
                clsSharedVariables.setSnapShotDig2FtpUploadEnableCam4(false);
                clsSharedVariables.setSnapShotDig3FtpUploadEnableCam4(false);
                clsSharedVariables.setSnapShotDig4FtpUploadEnableCam4(false);
                clsSharedVariables.setSnapShotContFtpUploadEnableCam4(false);
                clsSharedVariables.setSnapShotDig2EnableCam4(false);
                clsSharedVariables.setSnapShotDig3EnableCam4(false);
                clsSharedVariables.setSnapShotDig4EnableCam4(false);
            }
        } 
        clsReadFiles obj = new clsReadFiles();
        obj.write_cfg_data_file();
        if (obj.write_camera_cfg_file()) {
            lblMsgSnapshot.setText(lblMsgSnapshot.getText() + " Configuration Details Saved");
        } else {
            lblMsgSnapshot.setText(lblMsgSnapshot.getText() + " Configuration Details Not Saved");
        }
    }//GEN-LAST:event_btnSaveSnapshotActionPerformed

    private void chkSnapShotEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapShotEnableActionPerformed
        // TODO add your handling code here:
        if (chkSnapShotEnable.isSelected() == true) {
            this.panSnapEvent.setVisible(true);
            this.panSnapCont.setVisible(true);

        } else {
            this.panSnapEvent.setVisible(false);
            this.panSnapCont.setVisible(false);
        }

        chkSnapDig1ActionPerformed(evt);
        chkSnapDig2ActionPerformed(evt);
        chkSnapDig3ActionPerformed(evt);
        chkSnapDig4ActionPerformed(evt);
        chkSnapContActionPerformed(evt);
    }//GEN-LAST:event_chkSnapShotEnableActionPerformed

    private void cmbSnapEventStreamDig1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1ItemStateChanged

    }//GEN-LAST:event_cmbSnapEventStreamDig1ItemStateChanged

    private void cmbSnapEventStreamDig1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1FocusGained

    }//GEN-LAST:event_cmbSnapEventStreamDig1FocusGained

    private void cmbSnapEventStreamDig1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1FocusLost

    }//GEN-LAST:event_cmbSnapEventStreamDig1FocusLost

    private void cmbSnapEventStreamDig1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1CaretPositionChanged

    }//GEN-LAST:event_cmbSnapEventStreamDig1CaretPositionChanged

    private void cmbSnapEventStreamDig1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig1InputMethodTextChanged

    private void cmbSnapEventStreamDig1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig1ActionPerformed

    private void cmbSnapEventStreamDig1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig1PropertyChange

    private void cmbSnapEventStreamDig1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig1KeyReleased

    private void chkSnapDig4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDig4ActionPerformed
        // TODO add your handling code here:
        if (chkSnapDig4.isSelected()) {
            cmbSnapEventStreamDig4.setEnabled(true);
            spinSnapEventntervalDig4.setEnabled(true);
            chkSnapDi4Upload.isSelected();
            chkSnapDi4Upload.setEnabled(true);
        } else {
            cmbSnapEventStreamDig4.setEnabled(false);
            spinSnapEventntervalDig4.setEnabled(false);
            chkSnapDi4Upload.setSelected(false);
            chkSnapDi4Upload.setEnabled(false);
        }
    }//GEN-LAST:event_chkSnapDig4ActionPerformed

    private void chkSnapDig2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDig2ActionPerformed
        // TODO add your handling code here:
        if (chkSnapDig2.isSelected()) {
            cmbSnapEventStreamDig2.setEnabled(true);
            spinSnapEventntervalDig2.setEnabled(true);
            chkSnapDi2Upload.isSelected();
            chkSnapDi2Upload.setEnabled(true);
        } else {
            cmbSnapEventStreamDig2.setEnabled(false);
            spinSnapEventntervalDig2.setEnabled(false);
            chkSnapDi2Upload.setSelected(false);
            chkSnapDi2Upload.setEnabled(false);
        }
    }//GEN-LAST:event_chkSnapDig2ActionPerformed

    private void chkSnapDig3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDig3ActionPerformed
        // TODO add your handling code here:
        if (chkSnapDig3.isSelected()) {
            cmbSnapEventStreamDig3.setEnabled(true);
            spinSnapEventntervalDig3.setEnabled(true);
            chkSnapDi3Upload.isSelected();
            chkSnapDi3Upload.setEnabled(true);
        } else {
            cmbSnapEventStreamDig3.setEnabled(false);
            spinSnapEventntervalDig3.setEnabled(false);
            chkSnapDi3Upload.setSelected(false);
            chkSnapDi3Upload.setEnabled(false);
        }
    }//GEN-LAST:event_chkSnapDig3ActionPerformed

    private void chkSnapDig1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDig1ActionPerformed
        // TODO add your handling code here:
        if (chkSnapDig1.isSelected()) {
            cmbSnapEventStreamDig1.setEnabled(true);
            spinSnapEventntervalDig1.setEnabled(true);
            chkSnapDi1Upload.setEnabled(true);
            chkSnapDi1Upload.isSelected();

        } else {
            cmbSnapEventStreamDig1.setEnabled(false);
            spinSnapEventntervalDig1.setEnabled(false);
            chkSnapDi1Upload.setEnabled(false);
            chkSnapDi1Upload.setSelected(false);
        }
    }//GEN-LAST:event_chkSnapDig1ActionPerformed

    private void cmbSnapEventStreamDig2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2ItemStateChanged

    private void cmbSnapEventStreamDig2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2FocusGained

    private void cmbSnapEventStreamDig2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2FocusLost

    private void cmbSnapEventStreamDig2CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2CaretPositionChanged

    private void cmbSnapEventStreamDig2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2InputMethodTextChanged

    private void cmbSnapEventStreamDig2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2ActionPerformed

    private void cmbSnapEventStreamDig2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2PropertyChange

    private void cmbSnapEventStreamDig2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig2KeyReleased

    private void cmbSnapEventStreamDig3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3ItemStateChanged

    private void cmbSnapEventStreamDig3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3FocusGained

    private void cmbSnapEventStreamDig3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3FocusLost

    private void cmbSnapEventStreamDig3CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3CaretPositionChanged

    private void cmbSnapEventStreamDig3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3InputMethodTextChanged

    private void cmbSnapEventStreamDig3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3ActionPerformed

    private void cmbSnapEventStreamDig3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3PropertyChange

    private void cmbSnapEventStreamDig3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig3KeyReleased

    private void cmbSnapEventStreamDig4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4ItemStateChanged

    private void cmbSnapEventStreamDig4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4FocusGained

    private void cmbSnapEventStreamDig4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4FocusLost

    private void cmbSnapEventStreamDig4CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4CaretPositionChanged

    private void cmbSnapEventStreamDig4InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4InputMethodTextChanged

    private void cmbSnapEventStreamDig4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4ActionPerformed

    private void cmbSnapEventStreamDig4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4PropertyChange

    private void cmbSnapEventStreamDig4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSnapEventStreamDig4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapEventStreamDig4KeyReleased

    private void chkSnapDi1UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDi1UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSnapDi1UploadActionPerformed

    private void chkSnapDi3UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDi3UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSnapDi3UploadActionPerformed

    private void chkSnapDi4UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDi4UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSnapDi4UploadActionPerformed

    private void chkSnapDi2UploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapDi2UploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSnapDi2UploadActionPerformed

    private void cmbSnapContStreamTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeItemStateChanged

    private void cmbSnapContStreamTypeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeFocusGained

    private void cmbSnapContStreamTypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeFocusLost

    private void cmbSnapContStreamTypeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeCaretPositionChanged

    private void cmbSnapContStreamTypeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeInputMethodTextChanged

    private void cmbSnapContStreamTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeActionPerformed

    private void cmbSnapContStreamTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypePropertyChange

    private void cmbSnapContStreamTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSnapContStreamTypeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSnapContStreamTypeKeyReleased

    private void chkSnapContUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapContUploadActionPerformed
        // TODO add your handling code here:
        if (chkSnapContUpload.isSelected()) {
            cmbSnapContStreamType.setEnabled(true);
            spinSnapContInterval.setEnabled(true);
        } else {
            cmbSnapContStreamType.setEnabled(false);
            spinSnapContInterval.setEnabled(false);
        }
    }//GEN-LAST:event_chkSnapContUploadActionPerformed

    private void chkSnapContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSnapContActionPerformed
        // TODO add your handling code here:
        if (chkSnapCont.isSelected()) {
            cmbSnapContStreamType.setEnabled(true);
            spinSnapContInterval.setEnabled(true);
            chkSnapContUpload.setEnabled(true);
            chkSnapContUpload.isSelected();
        } else {
            cmbSnapContStreamType.setEnabled(false);
            spinSnapContInterval.setEnabled(false);
            chkSnapContUpload.setEnabled(false);
            chkSnapContUpload.setSelected(false);
        }
    }//GEN-LAST:event_chkSnapContActionPerformed

    private void radCam1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radCam1ItemStateChanged
        // TODO add your handling code here:
        lblMsgSnapshot.setText("");
    }//GEN-LAST:event_radCam1ItemStateChanged

    private void radCam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam1ActionPerformed
        lblMsgSnapshot.setText("");        // TODO add your handling code here:

        panSnap.setVisible(true);
        btnSave.setVisible(true);
        if (clsSharedVariables.getSnapShotEnableCam1()) {
            chkSnapShotEnable.setSelected(true);
            if (clsSharedVariables.getSnapShotDig1EnableCam1()) {
                chkSnapDig1.setSelected(true);
                // if(clsSharedVariables.getSnapShotDig1IntervalCam4())
                if (clsSharedVariables.getSnapShotDig1StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig1.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig1.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig1.setValue(clsSharedVariables.getSnapShotDig1IntervalCam1());
                chkSnapDi1Upload.setSelected(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam1());

            } else {
                chkSnapDig1.setSelected(false);
                cmbSnapEventStreamDig1.setEnabled(false);
                spinSnapEventntervalDig1.setEnabled(false);
            }
            if (clsSharedVariables.getSnapShotDig2EnableCam1()) {
                chkSnapDig2.setSelected(true);
                if (clsSharedVariables.getSnapShotDig2StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig2.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig2.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig2.setValue(clsSharedVariables.getSnapShotDig2IntervalCam1());
                chkSnapDi2Upload.setSelected(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam1());
            } else {
                chkSnapDig2.setSelected(false);
                cmbSnapEventStreamDig2.setEnabled(false);
                spinSnapEventntervalDig2.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig3EnableCam1()) {
                chkSnapDig3.setSelected(true);
                if (clsSharedVariables.getSnapShotDig3StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig3.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig3.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig3.setValue(clsSharedVariables.getSnapShotDig3IntervalCam1());
                chkSnapDi3Upload.setSelected(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam1());
            } else {
                chkSnapDig3.setSelected(false);
                cmbSnapEventStreamDig3.setEnabled(false);
                spinSnapEventntervalDig3.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig4EnableCam1()) {
                chkSnapDig4.setSelected(true);
                if (clsSharedVariables.getSnapShotDig4StreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig4.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig4.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig4.setValue(clsSharedVariables.getSnapShotDig4IntervalCam1());
                chkSnapDi4Upload.setSelected(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam1());
            } else {
                chkSnapDig4.setSelected(false);
                cmbSnapEventStreamDig4.setEnabled(false);
                spinSnapEventntervalDig4.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotContEnableCam1() == true) {
                chkSnapCont.setSelected(true);
                chkSnapCont.setSelected(true);
                if (clsSharedVariables.getSnapShotContStreamCam1() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapContStreamType.setSelectedIndex(0);
                } else {
                    cmbSnapContStreamType.setSelectedIndex(1);
                }
                spinSnapContInterval.setValue(clsSharedVariables.getSnapShotContIntervalCam1());
                this.chkSnapContUpload.setSelected(clsSharedVariables.getSnapShotContFtpUploadEnableCam1());

            } else {
                chkSnapCont.setSelected(false);
                chkSnapContUpload.setSelected(false);
                // clsSharedVariables.getSnapShotContEnableCam1(false);
            }

        } else {
            chkSnapShotEnable.setSelected(false);
        }
        chkSnapShotEnableActionPerformed(evt);
        chkSnapDig1ActionPerformed(evt);
        chkSnapDig2ActionPerformed(evt);
        chkSnapDig3ActionPerformed(evt);
        chkSnapDig4ActionPerformed(evt);
        chkSnapContActionPerformed(evt);
    }//GEN-LAST:event_radCam1ActionPerformed

    private void radCam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam2ActionPerformed
        lblMsgSnapshot.setText("");
        panSnap.setVisible(true);
        btnSave.setVisible(true);
        chkSnapShotEnable.setSelected(false);
        chkSnapShotEnableActionPerformed(evt);        // TODO add your handling code here:

        if (clsSharedVariables.getSnapShotEnableCam2()) {
            chkSnapShotEnable.setSelected(true);

            if (clsSharedVariables.getSnapShotDig1EnableCam2()) {
                chkSnapDig1.setSelected(true);
                // if(clsSharedVariables.getSnapShotDig1IntervalCam4())
                if (clsSharedVariables.getSnapShotDig1StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig1.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig1.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig1.setValue(clsSharedVariables.getSnapShotDig1IntervalCam2());
                chkSnapDi1Upload.setSelected(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam2());
            } else {
                chkSnapDig1.setSelected(false);
                cmbSnapEventStreamDig1.setEnabled(false);
                spinSnapEventntervalDig1.setEnabled(false);
            }
            if (clsSharedVariables.getSnapShotDig2EnableCam2()) {
                chkSnapDig2.setSelected(true);
                if (clsSharedVariables.getSnapShotDig2StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig2.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig2.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig2.setValue(clsSharedVariables.getSnapShotDig2IntervalCam2());
                chkSnapDi2Upload.setSelected(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam2());
            } else {
                chkSnapDig2.setSelected(false);
                cmbSnapEventStreamDig2.setEnabled(false);
                spinSnapEventntervalDig2.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig3EnableCam2()) {
                chkSnapDig3.setSelected(true);
                if (clsSharedVariables.getSnapShotDig3StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig3.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig3.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig3.setValue(clsSharedVariables.getSnapShotDig3IntervalCam2());
                chkSnapDi3Upload.setSelected(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam2());
            } else {
                chkSnapDig3.setSelected(false);
                cmbSnapEventStreamDig3.setEnabled(false);
                spinSnapEventntervalDig3.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig4EnableCam2()) {
                chkSnapDig4.setSelected(true);
                if (clsSharedVariables.getSnapShotDig4StreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig4.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig4.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig4.setValue(clsSharedVariables.getSnapShotDig4IntervalCam2());
                chkSnapDi4Upload.setSelected(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam2());
            } else {
                chkSnapDig4.setSelected(false);
                cmbSnapEventStreamDig4.setEnabled(false);
                spinSnapEventntervalDig4.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotContEnableCam2() == true) {
                chkSnapCont.setSelected(true);
                chkSnapContUpload.setSelected(true);
                if (clsSharedVariables.getSnapShotContStreamCam2() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapContStreamType.setSelectedIndex(0);
                } else {
                    cmbSnapContStreamType.setSelectedIndex(1);
                }
                spinSnapContInterval.setValue(clsSharedVariables.getSnapShotContIntervalCam2());
                chkSnapContUpload.setSelected(clsSharedVariables.getSnapShotContFtpUploadEnableCam2());

            } else {
                chkSnapCont.setSelected(false);
                chkSnapContUpload.setSelected(false);
            }

        } else {
            chkSnapShotEnable.setSelected(false);
        }

        chkSnapShotEnableActionPerformed(evt);
        chkSnapDig1ActionPerformed(evt);
        chkSnapDig2ActionPerformed(evt);
        chkSnapDig3ActionPerformed(evt);
        chkSnapDig4ActionPerformed(evt);
        chkSnapContActionPerformed(evt);
    }//GEN-LAST:event_radCam2ActionPerformed

    private void radCam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam3ActionPerformed
        // TODO add your handling code here:
        lblMsgSnapshot.setText("");
        panSnap.setVisible(true);
        btnSave.setVisible(true);
        chkSnapShotEnable.setSelected(false);
        chkSnapShotEnableActionPerformed(evt);
        if (clsSharedVariables.getSnapShotEnableCam3()) {
            chkSnapShotEnable.setSelected(true);

            if (clsSharedVariables.getSnapShotDig1EnableCam3()) {
                chkSnapDig1.setSelected(true);
                // if(clsSharedVariables.getSnapShotDig1IntervalCam4())
                if (clsSharedVariables.getSnapShotDig1StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig1.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig1.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig1.setValue(clsSharedVariables.getSnapShotDig1IntervalCam3());
                chkSnapDi1Upload.setSelected(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam3());

            } else {
                chkSnapDig1.setSelected(false);
                cmbSnapEventStreamDig1.setEnabled(false);
                spinSnapEventntervalDig1.setEnabled(false);
            }
            if (clsSharedVariables.getSnapShotDig2EnableCam3()) {
                chkSnapDig2.setSelected(true);
                if (clsSharedVariables.getSnapShotDig2StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig2.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig2.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig2.setValue(clsSharedVariables.getSnapShotDig2IntervalCam3());
                chkSnapDi2Upload.setSelected(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam3());
            } else {
                chkSnapDig2.setSelected(false);
                cmbSnapEventStreamDig2.setEnabled(false);
                spinSnapEventntervalDig2.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig3EnableCam3()) {
                chkSnapDig3.setSelected(true);
                if (clsSharedVariables.getSnapShotDig3StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig3.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig3.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig3.setValue(clsSharedVariables.getSnapShotDig3IntervalCam3());
                chkSnapDi3Upload.setSelected(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam3());
            } else {
                chkSnapDig3.setSelected(false);
                cmbSnapEventStreamDig3.setEnabled(false);
                spinSnapEventntervalDig3.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig4EnableCam3()) {
                chkSnapDig4.setSelected(true);
                if (clsSharedVariables.getSnapShotDig4StreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig4.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig4.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig4.setValue(clsSharedVariables.getSnapShotDig4IntervalCam3());
                chkSnapDi4Upload.setSelected(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam3());
            } else {
                chkSnapDig4.setSelected(false);
                cmbSnapEventStreamDig4.setEnabled(false);
                spinSnapEventntervalDig4.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotContEnableCam3() == true) {
                chkSnapCont.setSelected(true);
                chkSnapCont.setSelected(true);
                chkSnapContUpload.setSelected(true);
                if (clsSharedVariables.getSnapShotContStreamCam3() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapContStreamType.setSelectedIndex(0);
                } else {
                    cmbSnapContStreamType.setSelectedIndex(1);
                }
                spinSnapContInterval.setValue(clsSharedVariables.getSnapShotContIntervalCam3());
                chkSnapContUpload.setSelected(clsSharedVariables.getSnapShotContFtpUploadEnableCam3());

            } else {
                chkSnapCont.setSelected(false);
                chkSnapContUpload.setSelected(false);
            }

        } else {
            chkSnapShotEnable.setSelected(false);
        }

        chkSnapShotEnableActionPerformed(evt);
        chkSnapDig1ActionPerformed(evt);
        chkSnapDig2ActionPerformed(evt);
        chkSnapDig3ActionPerformed(evt);
        chkSnapDig4ActionPerformed(evt);
        chkSnapContActionPerformed(evt);
    }//GEN-LAST:event_radCam3ActionPerformed

    private void radCam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam4ActionPerformed
        // TODO add your handling code here:
        lblMsgSnapshot.setText("");
        panSnap.setVisible(true);
        btnSave.setVisible(true);
        if (clsSharedVariables.getSnapShotEnableCam4()) {
            chkSnapShotEnable.setSelected(true);

            if (clsSharedVariables.getSnapShotDig1EnableCam4()) {
                chkSnapDig1.setSelected(true);
                // if(clsSharedVariables.getSnapShotDig1IntervalCam4())
                if (clsSharedVariables.getSnapShotDig1StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig1.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig1.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig1.setValue(clsSharedVariables.getSnapShotDig1IntervalCam4());
                chkSnapDi1Upload.setSelected(clsSharedVariables.getSnapShotDig1FtpUploadEnableCam4());

            } else {
                chkSnapDig1.setSelected(false);
                cmbSnapEventStreamDig1.setEnabled(false);
                spinSnapEventntervalDig1.setEnabled(false);
            }
            if (clsSharedVariables.getSnapShotDig2EnableCam4()) {
                chkSnapDig2.setSelected(true);
                if (clsSharedVariables.getSnapShotDig2StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig2.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig2.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig2.setValue(clsSharedVariables.getSnapShotDig2IntervalCam4());
                chkSnapDi2Upload.setSelected(clsSharedVariables.getSnapShotDig2FtpUploadEnableCam4());
            } else {
                chkSnapDig2.setSelected(false);
                cmbSnapEventStreamDig2.setEnabled(false);
                spinSnapEventntervalDig2.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig3EnableCam4()) {
                chkSnapDig3.setSelected(true);
                if (clsSharedVariables.getSnapShotDig3StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig3.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig3.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig3.setValue(clsSharedVariables.getSnapShotDig3IntervalCam4());
                chkSnapDi3Upload.setSelected(clsSharedVariables.getSnapShotDig3FtpUploadEnableCam4());
            } else {
                chkSnapDig3.setSelected(false);
                cmbSnapEventStreamDig3.setEnabled(false);
                spinSnapEventntervalDig3.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotDig4EnableCam4()) {
                chkSnapDig4.setSelected(true);
                if (clsSharedVariables.getSnapShotDig4StreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapEventStreamDig4.setSelectedIndex(0);
                } else {
                    cmbSnapEventStreamDig4.setSelectedIndex(1);
                }
                this.spinSnapEventntervalDig4.setValue(clsSharedVariables.getSnapShotDig4IntervalCam4());
                chkSnapDi4Upload.setSelected(clsSharedVariables.getSnapShotDig4FtpUploadEnableCam4());
            } else {
                chkSnapDig4.setSelected(false);
                cmbSnapEventStreamDig4.setEnabled(false);
                spinSnapEventntervalDig4.setEnabled(false);
            }

            if (clsSharedVariables.getSnapShotContEnableCam4() == true) {
                chkSnapCont.setSelected(true);
                chkSnapContUpload.setSelected(true);
                if (clsSharedVariables.getSnapShotContStreamCam4() == clsDefines.VIDEO_MAIN_STREAM_TYPE) {
                    cmbSnapContStreamType.setSelectedIndex(0);
                } else {
                    cmbSnapContStreamType.setSelectedIndex(1);
                }
                spinSnapContInterval.setValue(clsSharedVariables.getSnapShotContIntervalCam4());
                chkSnapContUpload.setSelected(clsSharedVariables.getSnapShotContFtpUploadEnableCam4());

            } else {
                chkSnapCont.setSelected(false);
                chkSnapContUpload.setSelected(false);
            }

        } else {
            chkSnapShotEnable.setSelected(false);
        }

        chkSnapShotEnableActionPerformed(evt);

        chkSnapDig1ActionPerformed(evt);
        chkSnapDig2ActionPerformed(evt);
        chkSnapDig3ActionPerformed(evt);
        chkSnapDig4ActionPerformed(evt);
        chkSnapContActionPerformed(evt);
    }//GEN-LAST:event_radCam4ActionPerformed

    private void btnRefreshSnapshotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshSnapshotActionPerformed
        // TODO add your handling code here:
        if (radCam1.isSelected()) {
            radCam1ActionPerformed(evt);
        } else if (radCam2.isSelected()) {
            radCam2ActionPerformed(evt);
        } else if (radCam3.isSelected()) {
            radCam3ActionPerformed(evt);
        } else if (radCam4.isSelected()) {
            radCam4ActionPerformed(evt);
        } 

        lblMsgCamera.setText("");
    }//GEN-LAST:event_btnRefreshSnapshotActionPerformed

    private void cmbStreamTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStreamTypeItemStateChanged

        if (cmbStreamType.getSelectedIndex() == clsDefines.CAM_MAIN_STREAM) {
            if (cmbSelectCameraEncode.getSelectedIndex() == 0) {
                if (getResolution() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality()));
                cmbFramerate.setSelectedItem(String.valueOf(getFramerate()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate()));
                cmbVidEncoding.setSelectedItem(getVideoEncode());
                cmbBitrateType.setSelectedItem(getBitratetype());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 1) {
                if (getResolution2() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution2() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode2());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality2()));
                cmbFramerate.setSelectedItem((String.valueOf(getFramerate2())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate2()));
                cmbVidEncoding.setSelectedItem(getVideoEncode2());
                cmbBitrateType.setSelectedItem(getBitratetype2());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval2());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 2) {
                if (getResolution3() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution3() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode3());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality3()));
                cmbFramerate.setSelectedItem((String.valueOf(getFramerate3())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate3()));
                cmbVidEncoding.setSelectedItem(getVideoEncode3());
                cmbBitrateType.setSelectedItem(getBitratetype3());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval3());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 3) {
                if (getResolution4() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution4() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode4());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality4()));
                cmbFramerate.setSelectedItem((String.valueOf(getFramerate4())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate4()));
                cmbVidEncoding.setSelectedItem(getVideoEncode4());
                cmbBitrateType.setSelectedItem(getBitratetype4());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval4());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 4) {
                if (clsSharedVariables.getResolution5() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution5() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode5());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality5()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate5())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate5()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode5());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype5());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval5());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 5) {
                if (clsSharedVariables.getResolution6() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution6() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode6());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality6()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate6())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate6()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode6());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype6());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval6());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 6) {
                if (clsSharedVariables.getResolution7() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution7() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode7());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality7()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate7())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate7()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode7());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype7());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval7());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 7) {
                if (clsSharedVariables.getResolution8() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution8() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode8());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality8()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate8())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate8()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode8());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype8());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval8());
            }

        } else {  //setSubstreamResolution
            if (cmbSelectCameraEncode.getSelectedIndex() == 0) {

                if (clsSharedVariables.getSubstreamResolution() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode());
                //   if(getSubBitratetype()==true){
                cmbBitrateType.setSelectedItem(getSubBitratetype());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval());
                //  }else{
                //   cmbBitrate.setSelectedIndex(1);
                // }
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 1) {
                if (clsSharedVariables.getSubstreamResolution2() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution2() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode2());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality2()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate2()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate2()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode2());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval2());
                cmbBitrateType.setSelectedItem(getSubBitratetype2());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 2) {
                if (clsSharedVariables.getSubstreamResolution3() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution3() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode3());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality3()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate3()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate3()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode3());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval3());
                cmbBitrateType.setSelectedItem(getSubBitratetype());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 3) {
                if (clsSharedVariables.getSubstreamResolution4() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution4() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode4());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality4()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate4()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate4()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode4());
                cmbBitrateType.setSelectedItem(getSubBitratetype4());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval4());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 4) {
                if (clsSharedVariables.getSubstreamResolution5() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution5() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode5());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality5()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate5()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate5()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode5());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype5());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval5());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 5) {
                if (clsSharedVariables.getSubstreamResolution6() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution6() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode6());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality6()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate6()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate6()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode6());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype6());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval6());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 6) {
                if (clsSharedVariables.getSubstreamResolution7() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution7() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode7());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality7()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate7()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate7()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode7());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype7());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval7());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 7) {
                if (clsSharedVariables.getSubstreamResolution8() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution8() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode8());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality8()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate8()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate8()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode8());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype8());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval8());
            }
        }
    }//GEN-LAST:event_cmbStreamTypeItemStateChanged

    private void cmbStreamTypeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbStreamTypeFocusGained

    }//GEN-LAST:event_cmbStreamTypeFocusGained

    private void cmbStreamTypeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbStreamTypeFocusLost

    }//GEN-LAST:event_cmbStreamTypeFocusLost

    private void cmbStreamTypeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbStreamTypeCaretPositionChanged

    }//GEN-LAST:event_cmbStreamTypeCaretPositionChanged

    private void cmbStreamTypeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbStreamTypeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStreamTypeInputMethodTextChanged

    private void cmbStreamTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStreamTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStreamTypeActionPerformed

    private void cmbStreamTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbStreamTypePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStreamTypePropertyChange

    private void cmbStreamTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStreamTypeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStreamTypeKeyReleased

    private void cmbVideomodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVideomodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVideomodeActionPerformed

    private void cmbResolutionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbResolutionFocusGained

    }//GEN-LAST:event_cmbResolutionFocusGained

    private void cmbResolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResolutionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbResolutionActionPerformed

    private void cmbVidEncodingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVidEncodingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidEncodingActionPerformed

    private void cmbSelectCameraEncodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSelectCameraEncodeItemStateChanged
        // TODO add your handling code here:
        cmbStreamType.setSelectedIndex(0);
        if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam1")) {

            if (clsSharedVariables.getResolution() > 0 && clsSharedVariables.getResolution() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution() - 1);
            }
            cmbVideomode.setSelectedItem(getVideomode());
            cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality()));
            cmbFramerate.setSelectedItem((String.valueOf(getFramerate())));
            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate()));
            cmbVidEncoding.setSelectedItem(getVideoEncode());
            cmbBitrateType.setSelectedItem(getBitratetype());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval());

        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam2")) {

            if (clsSharedVariables.getResolution2() > 0 && clsSharedVariables.getResolution2() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution2() - 1);
            }
            cmbVideomode.setSelectedItem(getVideomode2());
            cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality2()));
            cmbFramerate.setSelectedItem((String.valueOf(getFramerate2())));

            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate2()));
            cmbVidEncoding.setSelectedItem(getVideoEncode2());

            cmbBitrateType.setSelectedItem(getBitratetype2());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval2());

        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam3")) {

            if (clsSharedVariables.getResolution3() > 0 && clsSharedVariables.getResolution3() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution3() - 1);
            }
            cmbVideomode.setSelectedItem(getVideomode3());
            cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality3()));
            cmbFramerate.setSelectedItem((String.valueOf(getFramerate3())));

            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate3()));
            cmbVidEncoding.setSelectedItem(getVideoEncode3());
            //if(getBitratetype3()==true){
            cmbBitrateType.setSelectedItem(getBitratetype3());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval3());

        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam4")) {

            if (getResolution4() > 0 && clsSharedVariables.getResolution4() < 5) {
                this.cmbResolution.setSelectedIndex(getResolution4() - 1);
            }
            cmbVideomode.setSelectedItem(getVideomode4());
            cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality4()));
            cmbFramerate.setSelectedItem((String.valueOf(getFramerate4())));

            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate4()));
            cmbVidEncoding.setSelectedItem(getVideoEncode4());

            cmbBitrateType.setSelectedItem(getBitratetype4());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval4());

        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam5")) {

            if (clsSharedVariables.getResolution5() > 0 && clsSharedVariables.getResolution5() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution5() - 1);
            }
            cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode5());
            cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality5()));
            cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate5())));
            //cmbFramerate.setModel((getFramerate()));
            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate5()));
            cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode5());
            cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype5());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval5());

        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam6")) {

            if (clsSharedVariables.getResolution6() > 0 && clsSharedVariables.getResolution6() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution6() - 1);
            }
            cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode6());
            cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality6()));
            cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate6())));

            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate6()));
            cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode6());
            // if(getBitratetype4()==true){
            cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype6());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval6());

        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam7")) {

            if (clsSharedVariables.getResolution7() > 0 && clsSharedVariables.getResolution7() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution7() - 1);
            }
            cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode7());
            cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality7()));
            cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate7())));
            //cmbFramerate.setModel((getFramerate()));
            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate7()));
            cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode7());
            // if(getBitratetype4()==true){
            cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype7());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval7());
            // }else{
            //   cmbBitrate.setSelectedIndex(1);
            //}
        } else if (cmbSelectCameraEncode.getSelectedItem().toString().equals("Cam8")) {

            if (clsSharedVariables.getResolution8() > 0 && clsSharedVariables.getResolution8() < 5) {
                this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution8() - 1);
            }
            cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode8());
            cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality8()));
            cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate8())));
            this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate8()));
            cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode8());
            cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype8());
            spinFrameInterval.setValue(clsSharedVariables.getFrameInterval8());

        }
    }//GEN-LAST:event_cmbSelectCameraEncodeItemStateChanged

    private void cmbSelectCameraEncodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSelectCameraEncodeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraEncodeFocusGained

    private void cmbSelectCameraEncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectCameraEncodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraEncodeActionPerformed

    private void btnSaveEncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEncodeActionPerformed
        lblMsgEncode.setText("");
        pbarEncode.setVisible(true);
        pbarEncode.setValue(1);
        byte cam_no;
        byte stream_type;
        int bitrate;
        int frame_rate = Integer.parseInt(cmbFramerate.getSelectedItem().toString());
        byte res = (byte) (cmbResolution.getSelectedIndex() + 1);
        String bitrate_type = cmbBitrateType.getSelectedItem().toString();
        byte quality = Byte.parseByte(cmbVideoquality.getSelectedItem().toString());
        bitrate = Integer.parseInt(cmbMaxbitrate.getSelectedItem().toString());
        int frame_interval = (int) spinFrameInterval.getValue();

        clsReadFiles obj;
        clsVideoNormalConfigUpdates objVidUpdate = new clsVideoNormalConfigUpdates();

        cam_no = (byte) (cmbSelectCameraEncode.getSelectedIndex() + 1);

        if (cmbStreamType.getSelectedIndex() == 0) {
            stream_type = clsDefines.CAM_MAIN_STREAM;
        } else {
            stream_type = clsDefines.CAM_SUB_STREAM;
        }

        obj = new clsReadFiles();
        try {
            switch (cmbSelectCameraEncode.getSelectedIndex()) {
                case 0:
                    setSelectCamera(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        clsSharedVariables.setStreamtype((String) cmbStreamType.getSelectedItem());
                        setVideomode(cmbVideomode.getSelectedItem().toString());
                        setResolution((byte) (res));
                        setBitratetype(bitrate_type);
                        setVideoquality(quality);
                        setFramerate(frame_rate);
                        clsSharedVariables.setbitrate(bitrate);
                        setVideoEncode(cmbVidEncoding.getSelectedItem().toString());
                        clsSharedVariables.setFrameInterval(frame_interval);

                    } else {
                        clsSharedVariables.setSubStreamtype((String) cmbStreamType.getSelectedItem());
                        setSubVideomode(cmbVideomode.getSelectedItem().toString());
                        setSubstreamResolution((byte) (res));
                        setSubBitratetype(bitrate_type);
                        setSubVideoquality(quality);
                        setSubFramerate(frame_rate);
                        clsSharedVariables.setSubbitrate(bitrate);
                        setSubVideoEncode(cmbVidEncoding.getSelectedItem().toString());
                        clsSharedVariables.setSubFrameInterval(frame_interval);
                    }
                    break;
                case 1:
                    setSelectCamera2(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        clsSharedVariables.setStreamtype2((String) cmbStreamType.getSelectedItem());
                        setVideomode2(cmbVideomode.getSelectedItem().toString());

                        setResolution2((byte) res);

                        setBitratetype2(bitrate_type);

                        setVideoquality2(quality);
                        setFramerate2(frame_rate);
                        clsSharedVariables.setbitrate2(bitrate);
                        setVideoEncode2(cmbVidEncoding.getSelectedItem().toString());
                        clsSharedVariables.setFrameInterval2(frame_interval);
                    } else {
                        clsSharedVariables.setSubStreamtype2((String) cmbStreamType.getSelectedItem());
                        setSubVideomode2(cmbVideomode.getSelectedItem().toString());
                        setSubstreamResolution2((byte) res);
                        setSubBitratetype2(bitrate_type);
                        setSubVideoquality2(quality);
                        setSubFramerate2(frame_rate);
                        clsSharedVariables.setSubbitrate2(bitrate);
                        setSubVideoEncode2(cmbVidEncoding.getSelectedItem().toString());
                        clsSharedVariables.setSubFrameInterval2(frame_interval);
                    }
                    break;
                case 2:
                    setSelectCamera3(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        clsSharedVariables.setStreamtype3((String) cmbStreamType.getSelectedItem());
                        setVideomode3(cmbVideomode.getSelectedItem().toString());

                        setResolution3((byte) res);

                        setBitratetype3(bitrate_type);

                        setVideoquality3(quality);
                        setFramerate3(frame_rate);
                        clsSharedVariables.setbitrate3(bitrate);
                        setVideoEncode3(cmbVidEncoding.getSelectedItem().toString());
                        clsSharedVariables.setFrameInterval3(frame_interval);

                    } else {
                        clsSharedVariables.setSubStreamtype3((String) cmbStreamType.getSelectedItem());
                        setSubVideomode3(cmbVideomode.getSelectedItem().toString());

                        setSubstreamResolution3((byte) res);

                        setSubBitratetype3(bitrate_type);

                        setSubVideoquality3(quality);
                        setSubFramerate3(frame_rate);
                        clsSharedVariables.setSubbitrate3(bitrate);
                        setSubVideoEncode3(cmbVidEncoding.getSelectedItem().toString());
                        clsSharedVariables.setSubFrameInterval3(frame_interval);
                    }
                    break;
                case 3:
                    setSelectCamera4(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        try {
                            clsSharedVariables.setStreamtype4((String) cmbStreamType.getSelectedItem());
                            setVideomode4(cmbVideomode.getSelectedItem().toString());

                            setResolution4((byte) res);

                            setBitratetype4(bitrate_type);

                            setVideoquality4(quality);
                            setFramerate4(frame_rate);
                            clsSharedVariables.setbitrate4(bitrate);
                            setVideoEncode4(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setFrameInterval4(frame_interval);
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            clsSharedVariables.setSubStreamtype4((String) cmbStreamType.getSelectedItem());
                            setSubVideomode4(cmbVideomode.getSelectedItem().toString());
                            setSubstreamResolution4((byte) res);
                            setSubBitratetype4(bitrate_type);
                            setSubVideoquality4(quality);
                            setSubFramerate4(frame_rate);
                            clsSharedVariables.setSubbitrate4(bitrate);
                            setSubVideoEncode4(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setSubFrameInterval4(frame_interval);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 4:
                    setSelectCamera4(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        try {
                            clsSharedVariables.setStreamtype5((String) cmbStreamType.getSelectedItem());
                            setVideomode5(cmbVideomode.getSelectedItem().toString());

                            setResolution5((byte) res);

                            setBitratetype5(bitrate_type);

                            setVideoquality5(quality);
                            setFramerate5(frame_rate);
                            clsSharedVariables.setbitrate5(bitrate);
                            setVideoEncode5(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setFrameInterval5(frame_interval);
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            clsSharedVariables.setSubStreamtype5((String) cmbStreamType.getSelectedItem());
                            setSubVideomode5(cmbVideomode.getSelectedItem().toString());

                            setSubstreamResolution5((byte) res);

                            setSubBitratetype5(bitrate_type);

                            setSubVideoquality5(quality);
                            clsSharedVariables.setSubFramerate5(frame_rate);
                            clsSharedVariables.setSubbitrate5(bitrate);
                            setSubVideoEncode5(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setSubFrameInterval5(frame_interval);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 5:
                    clsSharedVariables.setSelectCamera6(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        try {
                            clsSharedVariables.setStreamtype6((String) cmbStreamType.getSelectedItem());
                            setVideomode6(cmbVideomode.getSelectedItem().toString());

                            setResolution6((byte) res);

                            setBitratetype6(bitrate_type);

                            setVideoquality6(quality);
                            setFramerate6(frame_rate);
                            clsSharedVariables.setbitrate6(bitrate);
                            setVideoEncode6(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setFrameInterval6(frame_interval);
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            clsSharedVariables.setSubStreamtype6((String) cmbStreamType.getSelectedItem());
                            setSubVideomode6(cmbVideomode.getSelectedItem().toString());

                            setSubstreamResolution6((byte) res);

                            setSubBitratetype6(bitrate_type);

                            setSubVideoquality6(quality);
                            setSubFramerate6(frame_rate);
                            clsSharedVariables.setSubbitrate6(bitrate);
                            setSubVideoEncode6(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setSubFrameInterval6(frame_interval);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 6:
                    setSelectCamera7(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        try {
                            clsSharedVariables.setStreamtype7((String) cmbStreamType.getSelectedItem());
                            setVideomode7(cmbVideomode.getSelectedItem().toString());

                            setResolution7((byte) res);

                            setBitratetype7(bitrate_type);

                            setVideoquality7(quality);
                            setFramerate7(frame_rate);
                            clsSharedVariables.setbitrate7(bitrate);
                            setVideoEncode7(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setFrameInterval7(frame_interval);
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            clsSharedVariables.setSubStreamtype7((String) cmbStreamType.getSelectedItem());
                            setSubVideomode7(cmbVideomode.getSelectedItem().toString());

                            setSubstreamResolution7((byte) res);

                            setSubBitratetype7(bitrate_type);

                            setSubVideoquality7(quality);
                            setSubFramerate7(frame_rate);
                            clsSharedVariables.setSubbitrate7(bitrate);
                            setSubVideoEncode7(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setSubFrameInterval7(frame_interval);
                        } catch (Exception e) {

                        }
                    }
                    break;
                case 7:
                    setSelectCamera8(cmbSelectCameraEncode.getSelectedItem().toString());
                    if (cmbStreamType.getSelectedIndex() == 0) {
                        try {
                            clsSharedVariables.setStreamtype8((String) cmbStreamType.getSelectedItem());
                            setVideomode8(cmbVideomode.getSelectedItem().toString());

                            setResolution8((byte) res);

                            setBitratetype8(bitrate_type);

                            setVideoquality8(quality);
                            setFramerate8(frame_rate);
                            clsSharedVariables.setbitrate8(bitrate);
                            setVideoEncode8(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setFrameInterval8(frame_interval);
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            clsSharedVariables.setSubStreamtype8((String) cmbStreamType.getSelectedItem());
                            setSubVideomode8(cmbVideomode.getSelectedItem().toString());

                            setSubstreamResolution8((byte) res);

                            setSubBitratetype8(bitrate_type);

                            setSubVideoquality8(quality);
                            setSubFramerate8(frame_rate);
                            clsSharedVariables.setSubbitrate8(bitrate);
                            setSubVideoEncode8(cmbVidEncoding.getSelectedItem().toString());
                            clsSharedVariables.setSubFrameInterval8(frame_interval);
                        } catch (Exception e) {

                        }
                    }
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
        } finally {
        }
        pbarEncode.setValue(2);
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                objVidUpdate.update_video_res_bitrate_adjustments(cam_no, stream_type);
                pbarEncode.setValue(3);
                obj.write_cfg_data_file();
                if (obj.write_camera_cfg_file()) {
                    pbarEncode.setValue(4);
                    publish(" Video Details Saved");
                } else {
                    pbarEncode.setValue(4);
                    publish(" Video Details Not Saved");
                }
                return "Done";
            }

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    OUTER:
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        lblMsgEncode.setText(data);
                    }
                } catch (Exception e) {
                } finally {
                    data = null;
                }
            }

            @Override
            protected void done() {
                try {
                    pbarEncode.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnSaveEncodeActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:

        if (cmbStreamType.getSelectedIndex() == clsDefines.CAM_MAIN_STREAM) {
            if (cmbSelectCameraEncode.getSelectedIndex() == 0) {
                if (getResolution() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality()));
                cmbFramerate.setSelectedItem(String.valueOf(getFramerate()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate()));
                cmbVidEncoding.setSelectedItem(getVideoEncode());
                cmbBitrateType.setSelectedItem(getBitratetype());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 1) {
                if (getResolution2() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution2() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode2());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality2()));
                cmbFramerate.setSelectedItem((String.valueOf(getFramerate2())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate2()));
                cmbVidEncoding.setSelectedItem(getVideoEncode2());
                cmbBitrateType.setSelectedItem(getBitratetype2());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval2());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 2) {
                if (getResolution3() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution3() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode3());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality3()));
                cmbFramerate.setSelectedItem((String.valueOf(getFramerate3())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate3()));
                cmbVidEncoding.setSelectedItem(getVideoEncode3());
                cmbBitrateType.setSelectedItem(getBitratetype3());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval3());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 3) {
                if (getResolution4() > 0) {
                    this.cmbResolution.setSelectedIndex(getResolution4() - 1);
                }
                cmbVideomode.setSelectedItem(getVideomode4());
                cmbVideoquality.setSelectedItem(String.valueOf(getVideoquality4()));
                cmbFramerate.setSelectedItem((String.valueOf(getFramerate4())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate4()));
                cmbVidEncoding.setSelectedItem(getVideoEncode4());
                cmbBitrateType.setSelectedItem(getBitratetype4());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval4());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 4) {
                if (clsSharedVariables.getResolution5() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution5() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode5());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality5()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate5())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate5()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode5());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype5());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval5());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 5) {
                if (clsSharedVariables.getResolution6() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution6() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode6());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality6()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate6())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate6()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode6());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype6());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval6());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 6) {
                if (clsSharedVariables.getResolution7() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution7() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode7());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality7()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate7())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate7()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode7());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype7());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval7());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 7) {
                if (clsSharedVariables.getResolution8() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getResolution8() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getVideomode8());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getVideoquality8()));
                cmbFramerate.setSelectedItem((String.valueOf(clsSharedVariables.getFramerate8())));
                this.cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getbitrate8()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getVideoEncode8());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getBitratetype8());
                this.spinFrameInterval.setValue(clsSharedVariables.getFrameInterval8());
            }

        } else {  //setSubstreamResolution
            if (cmbSelectCameraEncode.getSelectedIndex() == 0) {

                if (clsSharedVariables.getSubstreamResolution() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode());
                //   if(getSubBitratetype()==true){
                cmbBitrateType.setSelectedItem(getSubBitratetype());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval());
                //  }else{
                //   cmbBitrate.setSelectedIndex(1);
                // }
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 1) {
                if (clsSharedVariables.getSubstreamResolution2() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution2() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode2());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality2()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate2()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate2()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode2());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval2());
                cmbBitrateType.setSelectedItem(getSubBitratetype2());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 2) {
                if (clsSharedVariables.getSubstreamResolution3() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution3() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode3());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality3()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate3()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate3()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode3());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval3());
                cmbBitrateType.setSelectedItem(getSubBitratetype());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 3) {
                if (clsSharedVariables.getSubstreamResolution4() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution4() - 1);
                }
                cmbVideomode.setSelectedItem(getSubVideomode4());
                cmbVideoquality.setSelectedItem(String.valueOf(getSubVideoquality4()));
                cmbFramerate.setSelectedItem(String.valueOf(getSubFramerate4()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate4()));
                cmbVidEncoding.setSelectedItem(getSubVideoEncode4());
                cmbBitrateType.setSelectedItem(getSubBitratetype4());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval4());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 4) {
                if (clsSharedVariables.getSubstreamResolution5() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution5() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode5());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality5()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate5()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate5()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode5());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype5());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval5());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 5) {
                if (clsSharedVariables.getSubstreamResolution6() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution6() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode6());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality6()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate6()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate6()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode6());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype6());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval6());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 6) {
                if (clsSharedVariables.getSubstreamResolution7() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution7() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode7());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality7()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate7()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate7()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode7());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype7());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval7());
            } else if (cmbSelectCameraEncode.getSelectedIndex() == 7) {
                if (clsSharedVariables.getSubstreamResolution8() > 0) {
                    this.cmbResolution.setSelectedIndex(clsSharedVariables.getSubstreamResolution8() - 1);
                }
                cmbVideomode.setSelectedItem(clsSharedVariables.getSubVideomode8());
                cmbVideoquality.setSelectedItem(String.valueOf(clsSharedVariables.getSubVideoquality8()));
                cmbFramerate.setSelectedItem(String.valueOf(clsSharedVariables.getSubFramerate8()));
                cmbMaxbitrate.setSelectedItem(String.valueOf(clsSharedVariables.getSubbitrate8()));
                cmbVidEncoding.setSelectedItem(clsSharedVariables.getSubVideoEncode8());
                cmbBitrateType.setSelectedItem(clsSharedVariables.getSubBitratetype8());
                this.spinFrameInterval.setValue(clsSharedVariables.getSubFrameInterval8());
            }
        }
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void cmbSelectCameraBrightnessItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSelectCameraBrightnessItemStateChanged
        // TODO add your handling code here:
        lblMsgBrightness.setText("");
        this.pbarBrightness.setVisible(false);
        if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam1")) {
            sliderBrightness.setValue(getBrightnessLevel());
            sliderContrastLevel1.setValue(getContrastLevel());
            sliderSaturationLevel1.setValue(getSaturationLevel());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam2")) {
            sliderBrightness.setValue(getBrightnessLevelCam2());
            sliderContrastLevel1.setValue(getContrastLevelCam2());
            sliderSaturationLevel1.setValue(getSaturationLevelCam2());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam3")) {
            sliderBrightness.setValue(getBrightnessLevelCam3());
            sliderContrastLevel1.setValue(getContrastLevelCam3());
            sliderSaturationLevel1.setValue(getSaturationLevelCam3());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam4")) {
            sliderBrightness.setValue(getBrightnessLevelCam4());
            sliderContrastLevel1.setValue(getContrastLevelCam4());
            sliderSaturationLevel1.setValue(getSaturationLevelCam4());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam5")) {
            sliderBrightness.setValue(getBrightnessLevelCam5());
            sliderContrastLevel1.setValue(getContrastLevelCam5());
            sliderSaturationLevel1.setValue(getSaturationLevelCam5());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam6")) {
            sliderBrightness.setValue(getBrightnessLevelCam6());
            sliderContrastLevel1.setValue(getContrastLevelCam6());
            sliderSaturationLevel1.setValue(getSaturationLevelCam6());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam7")) {
            sliderBrightness.setValue(getBrightnessLevelCam7());
            sliderContrastLevel1.setValue(getContrastLevelCam7());
            sliderSaturationLevel1.setValue(getSaturationLevelCam7());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam8")) {
            sliderBrightness.setValue(getBrightnessLevelCam8());
            sliderContrastLevel1.setValue(getContrastLevelCam8());
            sliderSaturationLevel1.setValue(getSaturationLevelCam8());
        }
    }//GEN-LAST:event_cmbSelectCameraBrightnessItemStateChanged

    private void cmbSelectCameraBrightnessFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSelectCameraBrightnessFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraBrightnessFocusGained

    private void cmbSelectCameraBrightnessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectCameraBrightnessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraBrightnessActionPerformed

    private void sliderSaturationLevel1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderSaturationLevel1StateChanged
        // TODO add your handling code here:
        lblSaturation.setText(String.valueOf(sliderSaturationLevel1.getValue()));
    }//GEN-LAST:event_sliderSaturationLevel1StateChanged

    private void sliderBrightnessStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderBrightnessStateChanged
        // TODO add your handling code here:
        lblBright.setText(String.valueOf(sliderBrightness.getValue()));
    }//GEN-LAST:event_sliderBrightnessStateChanged

    private void sliderContrastLevel1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderContrastLevel1StateChanged
        // TODO add your handling code here:
        lblContrast.setText(String.valueOf(sliderContrastLevel1.getValue()));
    }//GEN-LAST:event_sliderContrastLevel1StateChanged

    private void btnSaveBrightnessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBrightnessActionPerformed
        // TODO add your handling code here:
        // String[] str;

        clsVideoNormalConfigUpdates objVidConfig = new clsVideoNormalConfigUpdates();
        int i = sliderBrightness.getValue();
        int j = sliderContrastLevel1.getValue();
        int k = sliderSaturationLevel1.getValue();
        byte cam_no = 1;
        lblMsgBrightness.setText("");
        try {

            if (cmbSelectCameraBrightness.getSelectedIndex() == 0) {
                try {
                    setBrightnessLevel((byte) i);
                    setContrastLevel((byte) j);
                    setSaturationLevel((byte) k);
                    cam_no = 1;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 1) {
                try {
                    setBrightnessLevelCam2((byte) i);
                    setContrastLevelCam2((byte) j);
                    setSaturationLevelCam2((byte) k);
                    cam_no = 2;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 2) {
                try {
                    setBrightnessLevelCam3((byte) i);
                    setContrastLevelCam3((byte) j);
                    setSaturationLevelCam3((byte) k);
                    cam_no = 3;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 3) {
                try {
                    setBrightnessLevelCam4((byte) i);
                    setContrastLevelCam4((byte) j);
                    setSaturationLevelCam4((byte) k);
                    cam_no = 4;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 4) {
                try {
                    setBrightnessLevelCam5((byte) i);
                    setContrastLevelCam5((byte) j);
                    setSaturationLevelCam5((byte) k);
                    cam_no = 5;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 5) {
                try {
                    setBrightnessLevelCam6((byte) i);
                    setContrastLevelCam6((byte) j);
                    setSaturationLevelCam6((byte) k);
                    cam_no = 6;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 6) {
                try {
                    setBrightnessLevelCam7((byte) i);
                    setContrastLevelCam7((byte) j);
                    setSaturationLevelCam7((byte) k);
                    cam_no = 7;

                } catch (Exception ex) {
                    //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (cmbSelectCameraBrightness.getSelectedIndex() == 7) {
                try {
                    setBrightnessLevelCam8((byte) i);
                    setContrastLevelCam8((byte) j);
                    setSaturationLevelCam8((byte) k);
                    cam_no = 8;
                } catch (Exception ex) {

                }
            }

        } catch (Exception ex) {
        } finally {

        }

        final byte final_cam_no = cam_no;

        this.pbarBrightness.setValue(2);
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                final clsReadFiles objReadFiles = new clsReadFiles();
                final clsVideoNormalConfigUpdates objVidUpdate = new clsVideoNormalConfigUpdates();
                objVidUpdate.update_image_brightness_adjustments(final_cam_no);

                pbarBrightness.setValue(3);
                objReadFiles.write_cfg_data_file();
                if (objReadFiles.write_camera_cfg_file()) {
                    pbarBrightness.setValue(4);
                    publish(" Video Details Saved");
                } else {
                    pbarBrightness.setValue(4);
                    publish(" Video Details Not Saved");
                }
                return "Done";
            }

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    OUTER:
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        lblMsgBrightness.setText(data);
                    }
                } catch (Exception e) {
                } finally {
                    data = null;
                }
            }

            @Override
            protected void done() {
                try {
                    pbarMirror.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnSaveBrightnessActionPerformed

    private void btnRefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh2ActionPerformed
        // TODO add your handling code here:
        if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam1")) {
            sliderBrightness.setValue(getBrightnessLevel());
            sliderContrastLevel1.setValue(getContrastLevel());
            sliderSaturationLevel1.setValue(getSaturationLevel());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam2")) {
            sliderBrightness.setValue(getBrightnessLevelCam2());
            sliderContrastLevel1.setValue(getContrastLevelCam2());
            sliderSaturationLevel1.setValue(getSaturationLevelCam2());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam3")) {
            sliderBrightness.setValue(getBrightnessLevelCam3());
            sliderContrastLevel1.setValue(getContrastLevelCam3());
            sliderSaturationLevel1.setValue(getSaturationLevelCam3());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam4")) {
            sliderBrightness.setValue(getBrightnessLevelCam4());
            sliderContrastLevel1.setValue(getContrastLevelCam4());
            sliderSaturationLevel1.setValue(getSaturationLevelCam4());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam5")) {
            sliderBrightness.setValue(getBrightnessLevelCam5());
            sliderContrastLevel1.setValue(getContrastLevelCam5());
            sliderSaturationLevel1.setValue(getSaturationLevelCam5());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam6")) {
            sliderBrightness.setValue(getBrightnessLevelCam6());
            sliderContrastLevel1.setValue(getContrastLevelCam6());
            sliderSaturationLevel1.setValue(getSaturationLevelCam6());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam7")) {
            sliderBrightness.setValue(getBrightnessLevelCam7());
            sliderContrastLevel1.setValue(getContrastLevelCam7());
            sliderSaturationLevel1.setValue(getSaturationLevelCam7());
        } else if (cmbSelectCameraBrightness.getSelectedItem().toString().equals("Cam8")) {
            sliderBrightness.setValue(getBrightnessLevelCam8());
            sliderContrastLevel1.setValue(getContrastLevelCam8());
            sliderSaturationLevel1.setValue(getSaturationLevelCam8());
        }
    }//GEN-LAST:event_btnRefresh2ActionPerformed

    private void btnSaveMirrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMirrorActionPerformed

        lblMsgMirror.setText("");
        pbarMirror.setVisible(true);
        pbarMirror.setValue(1);
        clsReadFiles objReadFiles = new clsReadFiles();
        // TODO add your handling code here:
        final byte cam_no = (byte) (cmbSelectCameraMirror.getSelectedIndex() + 1);
        switch (cmbSelectCameraMirror.getSelectedIndex()) {
            case 0:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam1MirrorEnable = true;
                } else {
                    clsSharedVariables.cam1MirrorEnable = false;
                }
                break;
            case 1:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam2MirrorEnable = true;
                } else {
                    clsSharedVariables.cam2MirrorEnable = false;
                }
                break;
            case 2:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam3MirrorEnable = true;
                } else {
                    clsSharedVariables.cam3MirrorEnable = false;
                }
                break;
            case 3:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam4MirrorEnable = true;
                } else {
                    clsSharedVariables.cam4MirrorEnable = false;
                }
                break;
            case 4:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam5MirrorEnable = true;
                } else {
                    clsSharedVariables.cam5MirrorEnable = false;
                }
                break;
            case 5:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam6MirrorEnable = true;
                } else {
                    clsSharedVariables.cam6MirrorEnable = false;
                }
                break;
            case 6:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam7MirrorEnable = true;
                } else {
                    clsSharedVariables.cam7MirrorEnable = false;
                }
                break;
            case 7:
                if (this.radMirrorOn.isSelected()) {
                    clsSharedVariables.cam8MirrorEnable = true;
                } else {
                    clsSharedVariables.cam8MirrorEnable = false;
                }
                break;
        }
        objReadFiles.write_cfg_data_file();

        pbarMirror.setValue(2);
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                final clsReadFiles objReadFiles = new clsReadFiles();
                final clsVideoNormalConfigUpdates objVidUpdate = new clsVideoNormalConfigUpdates();
                objVidUpdate.update_mirror_image_settings(cam_no);

                pbarMirror.setValue(3);
                if (objReadFiles.write_camera_cfg_file()) {
                    pbarMirror.setValue(4);
                    publish(" Video Details Saved");
                } else {
                    pbarMirror.setValue(4);
                    publish(" Video Details Not Saved");
                }
                return "Done";
            }

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    OUTER:
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        lblMsgMirror.setText(data);
                    }
                } catch (Exception e) {
                } finally {
                    data = null;
                }
            }

            @Override
            protected void done() {
                try {
                    pbarMirror.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnSaveMirrorActionPerformed

    private void btnRefreshMirrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshMirrorActionPerformed
        // TODO add your handling code here:
        switch (cmbSelectCameraMirror.getSelectedIndex()) {
            case 0:
                if (clsSharedVariables.cam1MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;
            case 1:
                if (clsSharedVariables.cam2MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 2:
                if (clsSharedVariables.cam3MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;
            case 3:
                if (clsSharedVariables.cam4MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 4:
                if (clsSharedVariables.cam5MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 5:
                if (clsSharedVariables.cam6MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 6:
                if (clsSharedVariables.cam7MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 7:
                if (clsSharedVariables.cam8MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;
        }
    }//GEN-LAST:event_btnRefreshMirrorActionPerformed

    private void cmbSelectCameraMirrorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSelectCameraMirrorItemStateChanged
        // TODO add your handling code here:
        switch (cmbSelectCameraMirror.getSelectedIndex()) {
            case 0:
                if (clsSharedVariables.cam1MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;
            case 1:
                if (clsSharedVariables.cam2MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 2:
                if (clsSharedVariables.cam3MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;
            case 3:
                if (clsSharedVariables.cam4MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 4:
                if (clsSharedVariables.cam5MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 5:
                if (clsSharedVariables.cam6MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 6:
                if (clsSharedVariables.cam7MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);
                }
                break;

            case 7:
                if (clsSharedVariables.cam8MirrorEnable == true) {
                    this.radMirrorOn.setSelected(true);
                } else {
                    this.radMirrorOff.setSelected(true);

                }
                break;

        }
    }//GEN-LAST:event_cmbSelectCameraMirrorItemStateChanged

    private void cmbSelectCameraMirrorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSelectCameraMirrorFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraMirrorFocusGained

    private void cmbSelectCameraMirrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectCameraMirrorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraMirrorActionPerformed

    private void cmbSelectCameraWatermarkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSelectCameraWatermarkItemStateChanged
        // TODO add your handling code here:
        this.lblMsgWatermark.setText("");
        this.pbarWaterMark.setVisible(false);
        if (cmbSelectCameraWatermark.getSelectedIndex() == 0) {
            if (clsSharedVariables.getCamSpeedEnable1() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable1() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable1() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer1() == true) {

                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName1());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 1) {
            if (clsSharedVariables.getCamSpeedEnable2() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable2() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable2() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer2() == true) {
                chkCamIdentifier.setSelected(true);

                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName2());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 2) {
            if (clsSharedVariables.getCamSpeedEnable3() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable3() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable3() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer3() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName3());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 3) {
            if (clsSharedVariables.getCamSpeedEnable4() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable4() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable4() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer4() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName4());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 4) {
            if (clsSharedVariables.getCamSpeedEnable5() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable5() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable5() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer5() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName5());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 5) {
            if (clsSharedVariables.getCamSpeedEnable6() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable6() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable6() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(true);
            }
            if (getCameraIdentifer6() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName6());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 6) {
            if (clsSharedVariables.getCamSpeedEnable7() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable7() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable7() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer7() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName7());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 7) {
            if (clsSharedVariables.getCamSpeedEnable8() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable8() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable8() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer8() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName8());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        }
    }//GEN-LAST:event_cmbSelectCameraWatermarkItemStateChanged

    private void cmbSelectCameraWatermarkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSelectCameraWatermarkFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraWatermarkFocusGained

    private void cmbSelectCameraWatermarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectCameraWatermarkActionPerformed
        // TODO add your handling code here:

        if (cmbSelectCameraWatermark.getSelectedIndex() == 0) {
            if (clsSharedVariables.getCamSpeedEnable1() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable1() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable1() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer1() == true) {

                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName1());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 1) {
            if (clsSharedVariables.getCamSpeedEnable2() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable2() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable2() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer2() == true) {
                chkCamIdentifier.setSelected(true);

                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName2());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 2) {
            if (clsSharedVariables.getCamSpeedEnable3() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable3() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable3() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer3() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName3());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 3) {
            if (clsSharedVariables.getCamSpeedEnable4() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable4() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable4() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer4() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName4());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 4) {
            if (clsSharedVariables.getCamSpeedEnable5() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable5() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable5() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer5() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName5());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 5) {
            if (clsSharedVariables.getCamSpeedEnable6() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable6() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable6() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(true);
            }
            if (getCameraIdentifer6() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName6());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 6) {
            if (clsSharedVariables.getCamSpeedEnable7() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable7() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable7() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer7() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName7());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        } else if (cmbSelectCameraWatermark.getSelectedIndex() == 7) {
            if (clsSharedVariables.getCamSpeedEnable8() == true) {
                chkSpeed.setSelected(true);
            } else {
                chkSpeed.setSelected(false);
            }
            if (clsSharedVariables.getCamlatlangEnable8() == true) {
                chkCoordinates.setSelected(true);
            } else {
                chkCoordinates.setSelected(false);
            }
            if (clsSharedVariables.getCamVehRegEnable8() == true) {
                chkVechicleRegistration.setSelected(true);
            } else {
                chkVechicleRegistration.setSelected(false);
            }
            if (getCameraIdentifer8() == true) {
                chkCamIdentifier.setSelected(true);
                txtDeviceName.setVisible(true);

                txtDeviceName.setText(getCameraName8());
            } else {
                chkCamIdentifier.setSelected(false);
                txtDeviceName.setVisible(false);

            }
        }
    }//GEN-LAST:event_cmbSelectCameraWatermarkActionPerformed

    private void btnSaveWatermarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveWatermarkActionPerformed
        lblMsgWatermark.setText("");
        pbarWaterMark.setVisible(true);
        pbarWaterMark.setValue(1);

        String cam_name = txtDeviceName.getText();
        cam_name = cam_name.replaceAll(" ", "%20");

        final byte cam_no = (byte) (cmbSelectCameraWatermark.getSelectedIndex() + 1);

        try {
            if (cmbSelectCameraWatermark.getSelectedIndex() == 0) {

                if (chkCamIdentifier.isSelected()) {
                    setCameraIdentifer1(true);
                    clsSharedVariables.setCameraName1(cam_name);
                } else {
                    setCameraIdentifer1(false);
                    clsSharedVariables.setCameraName1("");
                }
                if (chkSpeed.isSelected()) {
                    setCamSpeedEnable1(true);

                } else {
                    setCamSpeedEnable1(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable1(true);
                } else {
                    clsSharedVariables.setCamlatlangEnable1(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable1(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable1(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 1) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer2(true);
                    clsSharedVariables.setCameraName2(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer2(false);
                    clsSharedVariables.setCameraName2("");

                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable2(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable2(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable2(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable2(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable2(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable2(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 2) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer3(true);
                    clsSharedVariables.setCameraName3(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer3(false);
                    clsSharedVariables.setCameraName3("");

                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable3(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable3(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable3(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable3(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable3(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable3(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 3) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer4(true);
                    clsSharedVariables.setCameraName4(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer4(false);
                    clsSharedVariables.setCameraName4("");

                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable4(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable4(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable4(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable4(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable4(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable4(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 4) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer5(true);
                    clsSharedVariables.setCameraName5(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer5(false);
                    clsSharedVariables.setCameraName5("");
                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable5(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable5(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable5(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable5(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable5(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable5(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 5) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer6(true);
                    clsSharedVariables.setCameraName6(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer6(false);
                    clsSharedVariables.setCameraName6("");

                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable6(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable6(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable6(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable6(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable6(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable6(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 6) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer7(true);
                    clsSharedVariables.setCameraName7(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer7(false);
                    clsSharedVariables.setCameraName7("");

                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable7(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable7(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable7(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable7(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable7(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable7(false);

                }

            } else if (cmbSelectCameraWatermark.getSelectedIndex() == 7) {
                if (chkCamIdentifier.isSelected()) {
                    clsSharedVariables.setCameraIdentifer8(true);
                    clsSharedVariables.setCameraName8(cam_name);

                } else {
                    clsSharedVariables.setCameraIdentifer8(false);
                    clsSharedVariables.setCameraName8("");

                }
                if (chkSpeed.isSelected()) {
                    clsSharedVariables.setCamSpeedEnable8(true);

                } else {
                    clsSharedVariables.setCamSpeedEnable8(false);

                }
                if (chkCoordinates.isSelected()) {
                    clsSharedVariables.setCamlatlangEnable8(true);

                } else {
                    clsSharedVariables.setCamlatlangEnable8(false);

                }
                if (chkVechicleRegistration.isSelected()) {
                    clsSharedVariables.setCamVehRegEnable8(true);

                } else {
                    clsSharedVariables.setCamVehRegEnable8(false);

                }

            } else {

            }
        } catch (Exception e) {

        } finally {
            cam_name = null;
        }

        try {

            //obj.write_pid_codes();
            clsReadFiles obj = new clsReadFiles();
            obj.write_cfg_data_file();
            if (obj.write_camera_cfg_file()) {
                lblMsgWatermark.setText(lblMsgWatermark.getText() + " Video Details Saved");
            } else {
                lblMsgWatermark.setText(lblMsgWatermark.getText() + " Video Details Not Saved");
            }
        } catch (Exception ex) {

        }

        pbarWaterMark.setValue(2);
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                final clsReadFiles objReadFiles = new clsReadFiles();
                final clsVideoNormalConfigUpdates objWater = new clsVideoNormalConfigUpdates();
                objWater.setWaterMarking(cam_no);

                pbarWaterMark.setValue(3);
                if (objReadFiles.write_camera_cfg_file()) {
                    pbarWaterMark.setValue(4);
                    publish(" Video Details Saved");
                } else {
                    pbarWaterMark.setValue(4);
                    publish(" Video Details Not Saved");
                }
                return "Done";
            }

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    OUTER:
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        lblMsgWatermark.setText(data);
                    }
                } catch (Exception e) {
                } finally {
                    data = null;
                }
            }

            @Override
            protected void done() {
                try {
                    pbarWaterMark.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnSaveWatermarkActionPerformed

    private void btnRefreshWatermarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshWatermarkActionPerformed
        // TODO add your handling code here:
        cmbSelectCameraWatermarkActionPerformed(evt);
    }//GEN-LAST:event_btnRefreshWatermarkActionPerformed

    private void txtDeviceNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeviceNameFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Camera Name")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Camera Name", txtDeviceName.getText());
            if (str != null) {
                txtDeviceName.setText(str);
            }
            prev_control_name = "Camera Name";
            obj = null;
            str = null;
            txtDeviceName.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDeviceNameFocusGained

    private void txtDeviceNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeviceNameFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDeviceNameFocusLost

    private void txtDeviceNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeviceNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeviceNameActionPerformed

    private void chkSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSpeedActionPerformed

    private void chkCamIdentifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCamIdentifierActionPerformed
        // TODO add your handling code here:
        if (chkCamIdentifier.isSelected() == true) {

            this.txtDeviceName.setVisible(true);
        } else {
            this.txtDeviceName.setVisible(false);
        }
    }//GEN-LAST:event_chkCamIdentifierActionPerformed

    private void chkVechicleRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVechicleRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVechicleRegistrationActionPerformed

    private void chkCoordinatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCoordinatesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCoordinatesActionPerformed

    private void chkMotionDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMotionDetActionPerformed
        // TODO add your handling code here:
        if (chkMotionDet.isSelected()) {
            panBorder.setVisible(true);
        } else {
            panBorder.setVisible(false);
        }
    }//GEN-LAST:event_chkMotionDetActionPerformed

    private void btnSaveMotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMotionActionPerformed
        // TODO add your handling code here:

        byte cam_no;
        int val = 0;
        int sub = 0;
        int i = 0;
        int x1val = (int) spinx1value.getValue();
        int y1val = (int) spiny1value.getValue();
        int x2val = (int) spinx2value.getValue();
        int y2val = (int) spiny2value.getValue();
        int senstivityval = (int) spinsensitivityVal.getValue();
        int thersholdval = (int) spinthresholdVal.getValue();
        int recordtime = (int) spinrecordtime.getValue();
        clsReadFiles obj = new clsReadFiles();
        lblMsgMotionDetect.setText("");
        if (radCam1Motion.isSelected()) {
            cam_no = 1;
            if (chkMotionDet.isSelected()) {
                clsSharedVariables.setMotionEnableCam1(true);
                clsSharedVariables.setMotionX1Cam1((int) this.spinx1value.getValue());
                clsSharedVariables.setMotionY1Cam1((int) this.spiny1value.getValue());
                clsSharedVariables.setMotionX2Cam1((int) this.spinx2value.getValue());
                clsSharedVariables.setMotionY2Cam1((int) this.spiny2value.getValue());
                clsSharedVariables.setSensitivityCam1((int) this.spinsensitivityVal.getValue());
                clsSharedVariables.setThresholdCam1((int) this.spinthresholdVal.getValue());
                clsSharedVariables.setRecordTimeCam1((int) this.spinrecordtime.getValue());
            } else {
                clsSharedVariables.setMotionEnableCam1(false);
            }
        } else if (radCam2Motion.isSelected()) {
            cam_no = 2;
            if (chkMotionDet.isSelected()) {
                clsSharedVariables.setMotionEnableCam2(true);
                clsSharedVariables.setMotionX1Cam2((int) this.spinx1value.getValue());
                clsSharedVariables.setMotionY1Cam2((int) this.spiny1value.getValue());
                clsSharedVariables.setMotionX2Cam2((int) this.spinx2value.getValue());
                clsSharedVariables.setMotionY2Cam2((int) this.spiny2value.getValue());
                clsSharedVariables.setSensitivityCam2((int) this.spinsensitivityVal.getValue());
                clsSharedVariables.setThresholdCam2((int) this.spinthresholdVal.getValue());
                clsSharedVariables.setRecordTimeCam2((int) this.spinrecordtime.getValue());
            } else {
                clsSharedVariables.setMotionEnableCam2(false);
            }
        } else if (radCam3Motion.isSelected()) {
            cam_no = 3;
            if (chkMotionDet.isSelected()) {
                clsSharedVariables.setMotionEnableCam3(true);
                clsSharedVariables.setMotionX1Cam3((int) this.spinx1value.getValue());
                clsSharedVariables.setMotionY1Cam3((int) this.spiny1value.getValue());
                clsSharedVariables.setMotionX2Cam3((int) this.spinx2value.getValue());
                clsSharedVariables.setMotionY2Cam3((int) this.spiny2value.getValue());
                clsSharedVariables.setSensitivityCam3((int) this.spinsensitivityVal.getValue());
                clsSharedVariables.setThresholdCam3((int) this.spinthresholdVal.getValue());
                clsSharedVariables.setRecordTimeCam3((int) this.spinrecordtime.getValue());
            } else {
                clsSharedVariables.setMotionEnableCam3(false);
            }
        } else if (radCam4Motion.isSelected()) {
            cam_no = 4;
            if (chkMotionDet.isSelected()) {
                clsSharedVariables.setMotionEnableCam4(true);
                clsSharedVariables.setMotionX1Cam4((int) this.spinx1value.getValue());
                clsSharedVariables.setMotionY1Cam4((int) this.spiny1value.getValue());
                clsSharedVariables.setMotionX2Cam4((int) this.spinx2value.getValue());
                clsSharedVariables.setMotionY2Cam4((int) this.spiny2value.getValue());
                clsSharedVariables.setSensitivityCam4((int) this.spinsensitivityVal.getValue());
                clsSharedVariables.setThresholdCam4((int) this.spinthresholdVal.getValue());
                clsSharedVariables.setRecordTimeCam4((int) this.spinrecordtime.getValue());
            } else {
                clsSharedVariables.setMotionEnableCam4(false);
            }
        }  else {
            cam_no = 1;
        }

        try {
            sub = x2val - x1val;

            for (i = 0; i < sub; i++) {
                val = (int) (val + Math.pow(2, x1val));
                x1val++;
            }

        } catch (Exception ex) {

        }
        final int final_val = val;
        final byte final_cam_no = cam_no;

        pbarMotion.setValue(2);
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                final clsReadFiles objReadFiles = new clsReadFiles();
                clsVideoNormalConfigUpdates objWater = new clsVideoNormalConfigUpdates();
                objWater.setMotionregion(final_cam_no, final_val);
                objWater = null;
                pbarMotion.setValue(3);
                obj.write_cfg_data_file();
                if (objReadFiles.write_camera_cfg_file()) {
                    pbarMotion.setValue(4);
                    publish(" Video Details Saved");
                } else {
                    pbarMotion.setValue(4);
                    publish(" Video Details Not Saved");
                }
                return "Done";
            }

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    OUTER:
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        lblMsgMotionDetect.setText(data);
                    }
                } catch (Exception e) {
                } finally {
                    data = null;
                }
            }

            @Override
            protected void done() {
                try {
                    pbarWaterMark.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnSaveMotionActionPerformed

    private void radCam1MotionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radCam1MotionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radCam1MotionItemStateChanged

    private void radCam1MotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam1MotionActionPerformed
        // TODO add your handling code here:
        if (clsSharedVariables.getMotionEnableCam1() == true) {
            panBorder.setVisible(true);
            chkMotionDet.setSelected(true);
            this.spinx1value.setValue(clsSharedVariables.getMotionX1Cam1());
            this.spiny1value.setValue(clsSharedVariables.getMotionY1Cam1());
            this.spinx2value.setValue(clsSharedVariables.getMotionX2Cam1());
            this.spiny2value.setValue(clsSharedVariables.getMotionY2Cam1());
            this.spinsensitivityVal.setValue(clsSharedVariables.getSensitivityCam1());
            this.spinthresholdVal.setValue(clsSharedVariables.getThresholdCam1());
            this.spinrecordtime.setValue(clsSharedVariables.getRecordTimeCam1());
        } else {
            panBorder.setVisible(false);
            chkMotionDet.setSelected(false);
        }
        lblMsgMotionDetect.setText("");
    }//GEN-LAST:event_radCam1MotionActionPerformed

    private void radCam4MotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam4MotionActionPerformed
        // TODO add your handling code here:
        lblMsgMotionDetect.setText("");

        if (clsSharedVariables.getMotionEnableCam4() == true) {
            panBorder.setVisible(true);
            chkMotionDet.setSelected(true);
            this.spinx1value.setValue(clsSharedVariables.getMotionX1Cam4());
            this.spiny1value.setValue(clsSharedVariables.getMotionY1Cam4());
            this.spinx2value.setValue(clsSharedVariables.getMotionX2Cam4());
            this.spiny2value.setValue(clsSharedVariables.getMotionY2Cam4());
            this.spinsensitivityVal.setValue(clsSharedVariables.getSensitivityCam4());
            this.spinthresholdVal.setValue(clsSharedVariables.getThresholdCam4());
            this.spinrecordtime.setValue(clsSharedVariables.getRecordTimeCam4());
        } else {
            panBorder.setVisible(false);
            chkMotionDet.setSelected(false);
        }
    }//GEN-LAST:event_radCam4MotionActionPerformed

    private void radCam3MotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam3MotionActionPerformed
        // TODO add your handling code here:
        lblMsgMotionDetect.setText("");

        if (clsSharedVariables.getMotionEnableCam3() == true) {
            panBorder.setVisible(true);
            chkMotionDet.setSelected(true);
            this.spinx1value.setValue(clsSharedVariables.getMotionX1Cam3());
            this.spinx2value.setValue(clsSharedVariables.getMotionY1Cam3());
            this.spiny1value.setValue(clsSharedVariables.getMotionX2Cam3());
            this.spiny2value.setValue(clsSharedVariables.getMotionY2Cam3());
            this.spinsensitivityVal.setValue(clsSharedVariables.getSensitivityCam3());
            this.spinthresholdVal.setValue(clsSharedVariables.getThresholdCam3());
            this.spinrecordtime.setValue(clsSharedVariables.getRecordTimeCam3());
        } else {
            panBorder.setVisible(false);
            chkMotionDet.setSelected(false);
        }
    }//GEN-LAST:event_radCam3MotionActionPerformed

    private void radCam2MotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCam2MotionActionPerformed
        lblMsgMotionDetect.setText("");

        if (clsSharedVariables.getMotionEnableCam2() == true) {
            panBorder.setVisible(true);
            chkMotionDet.setSelected(true);
            this.spinx1value.setValue(clsSharedVariables.getMotionX1Cam2());
            this.spinx2value.setValue(clsSharedVariables.getMotionY1Cam2());
            this.spiny1value.setValue(clsSharedVariables.getMotionX2Cam2());
            this.spiny2value.setValue(clsSharedVariables.getMotionY2Cam2());
            this.spinsensitivityVal.setValue(clsSharedVariables.getSensitivityCam2());
            this.spinthresholdVal.setValue(clsSharedVariables.getThresholdCam2());
            this.spinrecordtime.setValue(clsSharedVariables.getRecordTimeCam2());
        } else {
            panBorder.setVisible(false);
            chkMotionDet.setSelected(false);
        }
    }//GEN-LAST:event_radCam2MotionActionPerformed

    private void btnRefreshMotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshMotionActionPerformed
        // TODO add your handling code here:
        if (radCam1Motion.isSelected()) {
            radCam1MotionActionPerformed(evt);
        } else if (radCam2Motion.isSelected()) {
            radCam2MotionActionPerformed(evt);
        } else if (radCam3Motion.isSelected()) {
            radCam3MotionActionPerformed(evt);
        } else if (radCam4Motion.isSelected()) {
            radCam4MotionActionPerformed(evt);
        } 

        lblMsgCamera.setText("");
    }//GEN-LAST:event_btnRefreshMotionActionPerformed

    private void lblVideoWaterMarkingConfigName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblVideoWaterMarkingConfigName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblVideoWaterMarkingConfigName1ActionPerformed

    private void btnSaveVideoLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveVideoLimitActionPerformed

        clsReadFiles obj = new clsReadFiles();
        clsSharedVariables.setRecExpiryDays((int) this.spinVidRecStorage1.getValue());
        clsSharedVariables.setSnapDelNoDays((int) this.spinSnapStorage.getValue());
        clsSharedVariables.setRecExpiryDaysEventBased((int) this.spinEventRecStorage.getValue());

        obj.write_cfg_data_file();
        if (obj.write_camera_cfg_file()) {
            lblMsgRecord1.setText(" Configuration Saved");
        } else {
            lblMsgRecord1.setText(" Configuration Not Saved");
        }
    }//GEN-LAST:event_btnSaveVideoLimitActionPerformed

    private void spinStartDateInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spinStartDateInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spinStartDateInputMethodTextChanged

    private void chkSundayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSundayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSundayActionPerformed

    private void chkMondayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMondayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkMondayActionPerformed

    private void chkWednesdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkWednesdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkWednesdayActionPerformed

    private void btnSaveRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRecordActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final byte AUDIO_CONNECT = 1;
        final byte AUDIO_DISCONNECT = 0;
        Date start_date;
        Date end_date;
        lblMsgRecord.setText("");
        try {
            if (this.radManRecord.isSelected()) {
                setRecordType(VIDEO_RECORD_MANUAL);  //
                start_date = (Date) spinStartDate.getValue();
                end_date = (Date) spinEndDate.getValue();

                if (start_date.getTime() > end_date.getTime()) {
                    lblMsgRecord.setText("From Time is Greater than To Time");
                    start_date = null;
                    end_date = null;
                    return;
                }
            } else if (this.radContRecord.isSelected()) {
                setRecordType(VIDEO_RECORD_CONTINUOUS);
            }
            //  ////System.out.println(spinVidRecExpiryDays.getValue());
            // clsSharedVariables.setRecExpiryDays((int) spinVidRecExpiryDays.getValue());
            //  ////System.out.println(clsSharedVariables.getRecExpiryDays());
            if (this.chkEventRecord.isSelected()) {
                clsSharedVariables.setEventRecordEnabled(true);
                clsSharedVariables.setEventPostRecordTime(Byte.parseByte(this.SpinEventRecTime.getValue().toString()));
                clsSharedVariables.setEventPreRecordTime(Byte.parseByte(this.SpinEventPreRecTime.getValue().toString()));
                clsSharedVariables.setEventStreamType((byte) (this.cmbVidStreamTypeEvent.getSelectedIndex()));
            } else {
                clsSharedVariables.setEventRecordEnabled(false);
                clsSharedVariables.setEventPostRecordTime((byte) 0);
                clsSharedVariables.setEventPreRecordTime((byte) 0);
                clsSharedVariables.setEventStreamType(clsDefines.VIDEO_SUB_STREAM_TYPE);
            }

            if (this.chkCanRecord.isSelected()) {
                clsSharedVariables.setCanRecordEnabled(true);
                clsSharedVariables.setCanPostRecordTime(Byte.parseByte(this.SpinCanRecTime.getValue().toString()));
                clsSharedVariables.setCanPreRecordTime(Byte.parseByte(this.SpinCanPreRecTime.getValue().toString()));
                clsSharedVariables.setCanStreamType((byte) this.cmbVidStreamTypeCan.getSelectedIndex());
            } else {
                clsSharedVariables.setCanRecordEnabled(false);
                clsSharedVariables.setCanPostRecordTime((byte) 0);
                clsSharedVariables.setCanPreRecordTime((byte) 0);
                clsSharedVariables.setCanStreamType(clsDefines.VIDEO_SUB_STREAM_TYPE);
            }

            try {
                setRecordFromTime(sdf.format(spinStartDate.getValue()));
                setRecordToTime(sdf.format(spinEndDate.getValue()));
            } catch (Exception e) {

            }

            setCam1RecordSelected(this.chkCam1Rec.isSelected());
            setCam2RecordSelected(this.chkCam2Rec.isSelected());
            setCam3RecordSelected(this.chkCam3Rec.isSelected());
            setCam4RecordSelected(this.chkCam4Rec.isSelected());
//            setCam5RecordSelected(this.chkCam5Rec.isSelected());
//            setCam6RecordSelected(this.chkCam6Rec.isSelected());
//            setCam7RecordSelected(this.chkCam7Rec.isSelected());
//            setCam8RecordSelected(this.chkCam8Rec.isSelected());

            clsSharedVariables.setCam1RecordAudioSelected(this.chkCam1Audio.isSelected());
            clsSharedVariables.setCam2RecordAudioSelected(this.chkCam2Audio.isSelected());
            clsSharedVariables.setCam3RecordAudioSelected(this.chkCam3Audio.isSelected());
            clsSharedVariables.setCam4RecordAudioSelected(this.chkCam4Audio.isSelected());
//            clsSharedVariables.setCam5RecordAudioSelected(this.chkCam5Audio.isSelected());
//            clsSharedVariables.setCam6RecordAudioSelected(this.chkCam6Audio.isSelected());
//            clsSharedVariables.setCam7RecordAudioSelected(this.chkCam7Audio.isSelected());
//            clsSharedVariables.setCam8RecordAudioSelected(this.chkCam8Audio.isSelected());
            clsHealthPacketStructure objHealth = new clsHealthPacketStructure();

            if (this.chkCam1Audio.isSelected() == true) {
                objHealth.set_cam_audio1_status(AUDIO_CONNECT);
            } else {
                objHealth.set_cam_audio1_status(AUDIO_DISCONNECT);
            }
            if (this.chkCam2Audio.isSelected() == true) {
                objHealth.set_cam_audio2_status(AUDIO_CONNECT);
            } else {
                objHealth.set_cam_audio2_status(AUDIO_DISCONNECT);
            }
            if (this.chkCam3Audio.isSelected() == true) {
                objHealth.set_cam_audio3_status(AUDIO_CONNECT);
            } else {
                objHealth.set_cam_audio3_status(AUDIO_DISCONNECT);
            }
            if (this.chkCam4Audio.isSelected() == true) {
                objHealth.set_cam_audio4_status(AUDIO_CONNECT);
            } else {
                objHealth.set_cam_audio4_status(AUDIO_DISCONNECT);
            }
//            if (this.chkCam5Audio.isSelected() == true) {
//                objHealth.set_cam_audio5_status(AUDIO_CONNECT);
//            } else {
//                objHealth.set_cam_audio5_status(AUDIO_DISCONNECT);
//            }
//            if (this.chkCam6Audio.isSelected() == true) {
//                objHealth.set_cam_audio6_status(AUDIO_CONNECT);
//            } else {
//                objHealth.set_cam_audio6_status(AUDIO_DISCONNECT);
//            }
//            if (this.chkCam7Audio.isSelected() == true) {
//                objHealth.set_cam_audio7_status(AUDIO_CONNECT);
//            } else {
//                objHealth.set_cam_audio7_status(AUDIO_DISCONNECT);
//            }
//            if (this.chkCam8Audio.isSelected() == true) {
//                objHealth.set_cam_audio8_status(AUDIO_CONNECT);
//            } else {
//                objHealth.set_cam_audio8_status(AUDIO_DISCONNECT);
//            }
            if (chkSunday.isSelected()) {
                clsSharedVariables.setSchRecSunday(ENABLED);
            } else {
                clsSharedVariables.setSchRecSunday(DISABLED);
            }
            if (chkMonday.isSelected()) {
                clsSharedVariables.setSchRecMonday(ENABLED);
            } else {
                clsSharedVariables.setSchRecMonday(DISABLED);
            }
            if (chkTuesday.isSelected()) {
                clsSharedVariables.setSchRecTuesday(ENABLED);

            } else {
                clsSharedVariables.setSchRecTuesday(DISABLED);
            }
            if (chkWednesday.isSelected()) {
                clsSharedVariables.setSchRecWednesday(ENABLED);
            } else {
                clsSharedVariables.setSchRecWednesday(DISABLED);
            }
            if (chkThursday.isSelected()) {
                clsSharedVariables.setSchRecThursday(ENABLED);
            } else {
                clsSharedVariables.setSchRecThursday(DISABLED);
            }
            if (chkFriday.isSelected()) {
                clsSharedVariables.setSchRecFriday(ENABLED);
            } else {
                clsSharedVariables.setSchRecFriday(DISABLED);
            }
            if (chkSaturday.isSelected()) {
                clsSharedVariables.setSchRecSaturday(ENABLED);
            } else {
                clsSharedVariables.setSchRecSaturday(DISABLED);
            }

            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_cfg_data_file();
            objReadFiles.write_camera_cfg_file();
            if (objReadFiles.write_camera_cfg_file()) {
                lblMsgRecord.setText("Configuration Saved");
            } else {
                lblMsgRecord.setText("Configuration Not Saved");
            }

            objReadFiles = null;
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btnSaveRecordActionPerformed

    private void radManRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radManRecordActionPerformed
        // TODO add your handling code here:
        if (radManRecord.isSelected()) {
            this.panManRecord.setVisible(true);
        } else {
            this.panManRecord.setVisible(false);
        }
    }//GEN-LAST:event_radManRecordActionPerformed

    private void radContRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radContRecordActionPerformed
        // TODO add your handling code here:
        if (radContRecord.isSelected()) {
            this.panManRecord.setVisible(false);
        } else {
            this.panManRecord.setVisible(true);
        }
    }//GEN-LAST:event_radContRecordActionPerformed

    private void chkCam2AudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam2AudioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam2AudioActionPerformed

    private void chkCam3AudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam3AudioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam3AudioActionPerformed

    private void chkEventRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEventRecordActionPerformed
        // TODO add your handling code here:
        if (chkEventRecord.isSelected()) {
            SpinEventRecTime.setVisible(true);
            cmbVidStreamTypeEvent.setVisible(true);
            SpinEventPreRecTime.setVisible(true);
        } else {
            SpinEventRecTime.setVisible(false);
            cmbVidStreamTypeEvent.setVisible(false);
            SpinEventPreRecTime.setVisible(false);
        }
    }//GEN-LAST:event_chkEventRecordActionPerformed

    private void chkCanRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanRecordActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (chkCanRecord.isSelected()) {
            SpinCanRecTime.setVisible(true);
            cmbVidStreamTypeCan.setVisible(true);
            SpinCanPreRecTime.setVisible(true);
        } else {
            SpinCanRecTime.setVisible(false);
            cmbVidStreamTypeCan.setVisible(false);
            SpinCanPreRecTime.setVisible(false);
        }
    }//GEN-LAST:event_chkCanRecordActionPerformed

    private void cmbVidStreamTypeCanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanItemStateChanged

    private void cmbVidStreamTypeCanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanFocusGained

    private void cmbVidStreamTypeCanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanFocusLost

    private void cmbVidStreamTypeCanCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanCaretPositionChanged

    private void cmbVidStreamTypeCanInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanInputMethodTextChanged

    private void cmbVidStreamTypeCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanActionPerformed

    private void cmbVidStreamTypeCanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanPropertyChange

    private void cmbVidStreamTypeCanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeCanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeCanKeyReleased

    private void cmbVidStreamTypeEventItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventItemStateChanged

    private void cmbVidStreamTypeEventFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventFocusGained

    private void cmbVidStreamTypeEventFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventFocusLost

    private void cmbVidStreamTypeEventCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventCaretPositionChanged

    private void cmbVidStreamTypeEventInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventInputMethodTextChanged

    private void cmbVidStreamTypeEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventActionPerformed

    private void cmbVidStreamTypeEventPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventPropertyChange

    private void cmbVidStreamTypeEventKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbVidStreamTypeEventKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVidStreamTypeEventKeyReleased

    private void chkCam2RecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam2RecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam2RecActionPerformed

    private void chkCam3RecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam3RecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam3RecActionPerformed

    private void btnRefresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh3ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        Date startTime = cal.getTime();
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        Date endTime = cal.getTime();

        SpinnerDateModel model = new SpinnerDateModel(startTime, null, endTime, Calendar.MINUTE);

        spinStartDate.setModel(model);
        spinStartDate.setEditor(new JSpinner.DateEditor(spinStartDate, "HH:mm"));
        spinStartDate.setValue(startTime);

        SpinnerDateModel Endmodel = new SpinnerDateModel(endTime, null, endTime, Calendar.MINUTE);

        spinEndDate.setModel(Endmodel);
        spinEndDate.setEditor(new JSpinner.DateEditor(spinEndDate, "HH:mm"));
        spinEndDate.setValue(endTime);
        if (getRecordType() == VIDEO_RECORD_CONTINUOUS) {
            this.radContRecord.setSelected(true);
            this.panManRecord.setVisible(false);
        } else if (getRecordType() == VIDEO_RECORD_MANUAL) {

            this.radContRecord.setSelected(false);
            this.radManRecord.setSelected(true);
            this.panManRecord.setVisible(true);
            try {
                this.spinStartDate.setValue(sdf.parse(getRecordFromTime()));
            } catch (ParseException ex) {
            }
            try {
                this.spinEndDate.setValue(sdf.parse(getRecordToTime()));
            } catch (ParseException ex) {

            }

            if (clsSharedVariables.getSchRecSunday() == ENABLED) {
                chkSunday.setSelected(true);
            } else {
                chkSunday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecMonday() == ENABLED) {
                chkMonday.setSelected(true);
            } else {
                chkMonday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecTuesday() == ENABLED) {
                chkTuesday.setSelected(true);
            } else {
                chkTuesday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecWednesday() == ENABLED) {
                chkWednesday.setSelected(true);
            } else {
                chkWednesday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecThursday() == ENABLED) {
                chkThursday.setSelected(true);
            } else {
                chkThursday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecFriday() == ENABLED) {
                chkFriday.setSelected(true);
            } else {
                chkFriday.setSelected(false);
            }
            if (clsSharedVariables.getSchRecSaturday() == ENABLED) {
                chkSaturday.setSelected(true);
            } else {
                chkSaturday.setSelected(false);
            }

        }
        this.chkCam1Rec.setSelected(getCam1RecordSelected());
        this.chkCam2Rec.setSelected(getCam2RecordSelected());
        this.chkCam3Rec.setSelected(getCam3RecordSelected());
        this.chkCam4Rec.setSelected(getCam4RecordSelected());
//        this.chkCam5Rec.setSelected(getCam5RecordSelected());
//        this.chkCam6Rec.setSelected(getCam6RecordSelected());
//        this.chkCam7Rec.setSelected(getCam7RecordSelected());
//        this.chkCam8Rec.setSelected(getCam8RecordSelected());

        this.chkCam1Audio.setSelected(clsSharedVariables.getCam1RecordAudioSelected());
        this.chkCam2Audio.setSelected(clsSharedVariables.getCam2RecordAudioSelected());
        this.chkCam3Audio.setSelected(clsSharedVariables.getCam3RecordAudioSelected());
        this.chkCam4Audio.setSelected(clsSharedVariables.getCam4RecordAudioSelected());
//        this.chkCam5Audio.setSelected(clsSharedVariables.getCam5RecordAudioSelected());
//        this.chkCam6Audio.setSelected(clsSharedVariables.getCam6RecordAudioSelected());
//        this.chkCam7Audio.setSelected(clsSharedVariables.getCam7RecordAudioSelected());
//        this.chkCam8Audio.setSelected(clsSharedVariables.getCam8RecordAudioSelected());

        // this.spinVidRecExpiryDays.setValue(clsSharedVariables.getRecExpiryDays());
        if (clsSharedVariables.getEventRecordEnabled() == true) {
            this.chkEventRecord.setSelected(true);
            this.SpinEventRecTime.setVisible(true);
            cmbVidStreamTypeEvent.setVisible(true);
            this.SpinEventRecTime.setValue(clsSharedVariables.getEventPostRecordTime());
            this.SpinEventPreRecTime.setValue(clsSharedVariables.getEventPreRecordTime());
            this.cmbVidStreamTypeEvent.setSelectedIndex(clsSharedVariables.getEventStreamType());
        } else {
            this.chkEventRecord.setSelected(false);
            SpinEventRecTime.setVisible(false);
            cmbVidStreamTypeEvent.setVisible(false);
            this.SpinEventRecTime.setValue(1);
            this.cmbVidStreamTypeEvent.setSelectedIndex(1);
        }

        if (clsSharedVariables.getCanRecordEnabled() == true) {
            this.chkCanRecord.setSelected(true);
            this.SpinCanRecTime.setVisible(true);
            this.cmbVidStreamTypeCan.setVisible(true);
            this.SpinCanRecTime.setValue(clsSharedVariables.getCanPostRecordTime());
            this.SpinCanPreRecTime.setValue(clsSharedVariables.getCanPreRecordTime());
            this.cmbVidStreamTypeCan.setSelectedIndex(clsSharedVariables.getCanStreamType());
        } else {
            this.chkCanRecord.setSelected(false);
            this.SpinCanRecTime.setVisible(false);
            this.cmbVidStreamTypeCan.setVisible(false);
            this.SpinCanRecTime.setValue(1);
            this.cmbVidStreamTypeCan.setSelectedIndex(1);
        }

        this.lblMsgRecord.setText("");
    }//GEN-LAST:event_btnRefresh3ActionPerformed

    private void btnSave6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave6ActionPerformed
        // TODO add your handling code here:
        lblText.setText("Please wait for 2 min  ....");
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                clsReadFiles objReadFiles = new clsReadFiles();

                if (chkOnvifSupport.isSelected()) {
                    setOnvifSupported(true);
                } else {
                    setOnvifSupported(false);
                }
                if (chkCameraType.isSelected()) {
                    clsSharedVariables.setCameraType(clsDefines.IP_CAMERA);
                } else {
                    clsSharedVariables.setCameraType(clsDefines.ANALOG_CAMERA);
                }

                objReadFiles.write_product_storage_name();
                objReadFiles.write_logOnvifenabled_data();

                objReadFiles = null;
                return "true";
            }

            @Override
            public void done() {
                lblText.setText("Config Saved restart device");
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnSave6ActionPerformed

    private void btnShowPassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassword1ActionPerformed
        // TODO add your handling code here:
        show_message_dialogbox(PasswordCam1.getText());
    }//GEN-LAST:event_btnShowPassword1ActionPerformed

    private void btnShowPassword2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassword2ActionPerformed
        // TODO add your handling code here:
        show_message_dialogbox(PasswordCam2.getText());
    }//GEN-LAST:event_btnShowPassword2ActionPerformed

    private void btnShowPassword3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassword3ActionPerformed
        // TODO add your handling code here:
        show_message_dialogbox(PasswordCam3.getText());
    }//GEN-LAST:event_btnShowPassword3ActionPerformed

    private void btnShowPassword4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassword4ActionPerformed
        // TODO add your handling code here:
        show_message_dialogbox(PasswordCam4.getText());
    }//GEN-LAST:event_btnShowPassword4ActionPerformed

    private void chkApcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkApcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkApcActionPerformed

    private void chkApc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkApc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkApc1ActionPerformed

    private void chkPmiEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPmiEnableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPmiEnableActionPerformed

    private void chkNetworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNetworkActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkNetworkActionPerformed

    private void chkNetworkStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkNetworkStateChanged
        // TODO add your handling code here:
        if (chkNetwork.isSelected()) {

            chkNetwork.setText("Shared Network");
        } else {
            chkNetwork.setText("Manual Network");
        }
    }//GEN-LAST:event_chkNetworkStateChanged

    private void chkSpelAudioAnnouncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSpelAudioAnnouncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSpelAudioAnnouncActionPerformed

    private void cmbSelectCameraNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSelectCameraNoItemStateChanged
        // TODO add your handling code here:
        if (cmbSelectCameraNo.getSelectedIndex() == 0) {
            chkCam1Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(false);
            this.panCam3.setVisible(false);
            this.panCam4.setVisible(false);
//            this.panCam5.setVisible(false);
//            this.panCam6.setVisible(false);
//            this.panCam7.setVisible(false);
//            this.panCam8.setVisible(false);
        } else if (cmbSelectCameraNo.getSelectedIndex() == 1) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(false);
            this.panCam4.setVisible(false);
//            this.panCam5.setVisible(false);
//            this.panCam6.setVisible(false);
//            this.panCam7.setVisible(false);
//            this.panCam8.setVisible(false);

        } else if (cmbSelectCameraNo.getSelectedIndex() == 2) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            chkCam3Enable.setSelected(true);

            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(true);
            this.panCam4.setVisible(false);
//            this.panCam5.setVisible(false);
//            this.panCam6.setVisible(false);
//            this.panCam7.setVisible(false);
//            this.panCam8.setVisible(false);
        } else if (cmbSelectCameraNo.getSelectedIndex() == 3) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            chkCam3Enable.setSelected(true);
            chkCam4Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(true);
            this.panCam4.setVisible(true);
//            this.panCam5.setVisible(false);
//            this.panCam6.setVisible(false);
//            this.panCam7.setVisible(false);
//            this.panCam8.setVisible(false);
        } else if (cmbSelectCameraNo.getSelectedIndex() == 4) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            chkCam3Enable.setSelected(true);
            chkCam4Enable.setSelected(true);
           // chkCam5Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(true);
            this.panCam4.setVisible(true);
//            this.panCam5.setVisible(true);
//            this.panCam6.setVisible(false);
//            this.panCam7.setVisible(false);
//            this.panCam8.setVisible(false);
        } else if (cmbSelectCameraNo.getSelectedIndex() == 5) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            chkCam3Enable.setSelected(true);
            chkCam4Enable.setSelected(true);
//            chkCam5Enable.setSelected(true);
//            chkCam6Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(true);
            this.panCam4.setVisible(true);
//            this.panCam5.setVisible(true);
//            this.panCam6.setVisible(true);
//            this.panCam7.setVisible(false);
//            this.panCam8.setVisible(false);
        } else if (cmbSelectCameraNo.getSelectedIndex() == 6) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            chkCam3Enable.setSelected(true);
            chkCam4Enable.setSelected(true);
//            chkCam5Enable.setSelected(true);
//            chkCam6Enable.setSelected(true);
//            chkCam7Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(true);
            this.panCam4.setVisible(true);
//            this.panCam5.setVisible(true);
//            this.panCam6.setVisible(true);
//            this.panCam7.setVisible(true);
//            this.panCam8.setVisible(false);
        } else if (cmbSelectCameraNo.getSelectedIndex() == 7) {
            chkCam1Enable.setSelected(true);
            chkCam2Enable.setSelected(true);
            chkCam3Enable.setSelected(true);
            chkCam4Enable.setSelected(true);
//            chkCam5Enable.setSelected(true);
//            chkCam6Enable.setSelected(true);
//            chkCam7Enable.setSelected(true);
//            chkCam8Enable.setSelected(true);
            this.panCam1.setVisible(true);
            this.panCam2.setVisible(true);
            this.panCam3.setVisible(true);
            this.panCam4.setVisible(true);
//            this.panCam5.setVisible(true);
//            this.panCam6.setVisible(true);
//            this.panCam7.setVisible(true);
//            this.panCam8.setVisible(true);
        }
    }//GEN-LAST:event_cmbSelectCameraNoItemStateChanged

    private void cmbSelectCameraNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectCameraNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSelectCameraNoActionPerformed

    private void radRfidRs232ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radRfidRs232ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radRfidRs232ActionPerformed

    private void radGpsDataRs232ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radGpsDataRs232ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radGpsDataRs232ActionPerformed

    private void radalcoBrakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radalcoBrakeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radalcoBrakeActionPerformed
private static void modifyGpuMemLine(String configFilePath, String gpuMemLine, String gpuMemKey) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(configFilePath));
        boolean gpuMemFound = false;

        // Check if the line exists or needs modification
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();

            // If the line is commented out, uncomment it
            if (line.startsWith("#" + gpuMemKey)) {
                lines.set(i, gpuMemLine);  // Uncomment and set the new value
                gpuMemFound = true;
                break;
            }

            // If the line exists, modify its value
            if (line.startsWith(gpuMemKey)) {
                lines.set(i, gpuMemLine);  // Change the value
                gpuMemFound = true;
                break;
            }
        }

        // If the gpu_mem line was not found, add it at the end
        if (!gpuMemFound) {
            lines.add(gpuMemLine);  // Add the gpu_mem line
        }
        //System.out.println("lines: " + lines);
        // Write the modified lines back to the file
        Files.write(Paths.get(configFilePath), lines);
    }
 private static void remountFilesystem(String path, String option) throws IOException, InterruptedException {
        ProcessBuilder remountProcessBuilder = new ProcessBuilder("sudo", "mount", "-o", "remount," + option, path);
        Process remountProcess = remountProcessBuilder.start();
        int exitCode = remountProcess.waitFor();
        if (exitCode != 0) {
            throw new IOException("Failed to remount " + path + " as " + option);
        }
    }

    private void BtnRemountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRemountActionPerformed
        // TODO add your handling code here:
        Labelmount.setText(" ");
        String configFilePath = "/boot/config.txt";
        String gpuMemLine = "gpu_mem=512";
        String gpuMemKey = "gpu_mem";
        try {
            // Remount /boot as read-write
            remountFilesystem("/boot", "rw");
            // Modify gpu_mem line in config.txt
            modifyGpuMemLine(configFilePath, gpuMemLine, gpuMemKey);
            // Remount /boot back to read-only
            remountFilesystem("/boot", "ro");
            Labelmount.setText("completed");

            // Output success message (example)
            //  System.out.println("Line edited and /boot remounted as read-only.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        // Method to remount the filesystem with the given option (rw or ro)
    }//GEN-LAST:event_BtnRemountActionPerformed

    private void btnLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiveActionPerformed
         
    lblOut.setText("Installing... Please wait.");

    // Run in a background thread to keep UI responsive
    new Thread(() -> {
        boolean success = false;
        try {
            // === Find the pendrive mount path ===
            File usbRoot = new File("/media/pi/");
            File[] drives = usbRoot.listFiles();
            if (drives == null || drives.length == 0) {
                updateStatus("❌ No USB drives found!");
                return;
            }

            File usbPath = null;
           for (File drive : drives) {
    String name = drive.getName().toLowerCase();

    // ✅ skip system/internal mounts (case-insensitive)
    if (name.contains("boot") || name.contains("rootfs") || name.contains("3339obu") || name.contains("recorder")) {
        continue;
    }
     usbPath = drive;
    break;
}


            if (usbPath == null) {
                updateStatus("❌ No valid pendrive detected.");
                return;
            }

            System.out.println("📂 Using USB path: " + usbPath.getAbsolutePath());

            // === Copy required files ===
            runCommand("sudo cp -f " + usbPath + "/pushstream.sh /home/pi/");
            runCommand("sudo cp -f " + usbPath + "/mediamtx /home/pi/");
            runCommand("sudo cp -f " + usbPath + "/mediamtx.yml /home/pi/");
            runCommand("sudo cp -f " + usbPath + "/LICENSE /home/pi/");
            runCommand("sudo cp -f " + usbPath + "/pushstream.service /etc/systemd/system/");
            runCommand("sudo cp -f " + usbPath + "/mediamtx.service /etc/systemd/system/");

            // === Set permissions ===
            runCommand("sudo chown pi:pi /home/pi/pushstream.sh /home/pi/mediamtx /home/pi/mediamtx.yml /home/pi/LICENSE");
            runCommand("sudo chmod +x /home/pi/pushstream.sh /home/pi/mediamtx");
            runCommand("sudo chmod 644 /home/pi/mediamtx.yml /home/pi/LICENSE /etc/systemd/system/pushstream.service /etc/systemd/system/mediamtx.service");

            // === Reload + enable + restart services ===
            runCommand("sudo systemctl daemon-reload");
            runCommand("sudo systemctl enable mediamtx.service");
            runCommand("sudo systemctl enable pushstream.service");

            runCommand("sudo systemctl restart mediamtx.service");
            Thread.sleep(5000); // wait for mediamtx to start
            runCommand("sudo systemctl restart pushstream.service");

            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // === Update label based on result ===
        if (success) {
            updateStatus("✅ RTMP Setup Successful!");
        } else {
            updateStatus("❌ RTMP Setup Failed!");
        }

    }).start();
    }//GEN-LAST:event_btnLiveActionPerformed
private static void rwFilesystem(String path, String option) throws IOException, InterruptedException {
    ProcessBuilder remountProcessBuilder = new ProcessBuilder("sudo", "mount", "-o", "remount," + option, path);
    Process remountProcess = remountProcessBuilder.start();
    int exitCode = remountProcess.waitFor();
    if (exitCode != 0) {
        throw new IOException("Failed to remount " + path + " as " + option);
    }
}
private void runCommand(String cmd) throws Exception {
    System.out.println("🔧 Running: " + cmd);
    Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", cmd});
    int exitCode = process.waitFor();
    if (exitCode != 0) {
        throw new RuntimeException("Command failed: " + cmd);
    }
}

// Helper: safely update Swing label text
private void updateStatus(String msg) {
    SwingUtilities.invokeLater(() -> lblOut.setText(msg));
}
private static boolean deleteFileFromTerminal(String filePath) throws IOException, InterruptedException {
    ProcessBuilder pb = new ProcessBuilder("sudo", "rm", "-f", filePath);
    Process process = pb.start();
    int exitCode = process.waitFor();
    return (exitCode == 0);
}

  
    private void show_message_dialogbox(String data) {
        try {
            final JLabel label = new JLabel();
            int timerDelay = 1000;

            label.setText(data);
            // label.setBackground(Color.BLUE);
            label.setOpaque(true);
            label.setFont(new java.awt.Font("Tahoma", 1, 16));
            label.setPreferredSize(new java.awt.Dimension(50, 50));
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            int cnt;
            cnt = 2;

            final int time_cnt = cnt;

            new javax.swing.Timer(timerDelay, new ActionListener() {
                int timeLeft = 0;// time_cnt;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeLeft <= time_cnt) {
                        timeLeft++;
                    } else {
                        ((javax.swing.Timer) e.getSource()).stop();
                        Window win = SwingUtilities.getWindowAncestor(label);
                        win.setVisible(false);
                        win = null;

                    }
                }
            }) {
                {
                    setInitialDelay(0);
                }
            }.start();

            // JOptionPane.showMessageDialog(null, label);
            JOptionPane.showMessageDialog(null, label, "Alert", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRemount;
    private javax.swing.JLabel Labelmount;
    private javax.swing.JPasswordField PasswordCam1;
    private javax.swing.JPasswordField PasswordCam2;
    private javax.swing.JPasswordField PasswordCam3;
    private javax.swing.JPasswordField PasswordCam4;
    private javax.swing.JSpinner SpinCanPreRecTime;
    private javax.swing.JSpinner SpinCanRecTime;
    private javax.swing.JSpinner SpinEventPreRecTime;
    private javax.swing.JSpinner SpinEventRecTime;
    private javax.swing.JPanel TabVideoEncoding;
    private javax.swing.ButtonGroup btnCamLiveGroup;
    private javax.swing.JButton btnDelete;
    private javax.swing.ButtonGroup btnGrpCameraType;
    private javax.swing.ButtonGroup btnGrpMirror;
    private javax.swing.ButtonGroup btnGrpMotionDetect;
    private javax.swing.ButtonGroup btnGrpRecord;
    private javax.swing.ButtonGroup btnGrpSnapshot;
    private javax.swing.JButton btnLive;
    private javax.swing.JButton btnModifyVersion;
    private javax.swing.JButton btnRefresh1;
    private javax.swing.JButton btnRefresh2;
    private javax.swing.JButton btnRefresh3;
    private javax.swing.JButton btnRefreshMirror;
    private javax.swing.JButton btnRefreshMotion;
    private javax.swing.JButton btnRefreshSnapshot;
    private javax.swing.JButton btnRefreshWatermark;
    private javax.swing.ButtonGroup btnRs232grp;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSave3;
    private javax.swing.JButton btnSave4;
    private javax.swing.JButton btnSave5;
    private javax.swing.JButton btnSave6;
    private javax.swing.JButton btnSaveBrightness;
    private javax.swing.JButton btnSaveEncode;
    private javax.swing.JButton btnSaveMirror;
    private javax.swing.JButton btnSaveMotion;
    private javax.swing.JButton btnSaveRecord;
    private javax.swing.JButton btnSaveSnapshot;
    private javax.swing.JButton btnSaveVideoLimit;
    private javax.swing.JButton btnSaveWatermark;
    private javax.swing.JButton btnShowPassword1;
    private javax.swing.JButton btnShowPassword2;
    private javax.swing.JButton btnShowPassword3;
    private javax.swing.JButton btnShowPassword4;
    private javax.swing.ButtonGroup btnethernertgrp;
    private javax.swing.JCheckBox chkApc;
    private javax.swing.JCheckBox chkApc1;
    private javax.swing.JCheckBox chkCam1Audio;
    private javax.swing.JCheckBox chkCam1Enable;
    private javax.swing.JCheckBox chkCam1Rec;
    private javax.swing.JCheckBox chkCam2Audio;
    private javax.swing.JCheckBox chkCam2Enable;
    private javax.swing.JCheckBox chkCam2Rec;
    private javax.swing.JCheckBox chkCam3Audio;
    private javax.swing.JCheckBox chkCam3Enable;
    private javax.swing.JCheckBox chkCam3Rec;
    private javax.swing.JCheckBox chkCam4Audio;
    private javax.swing.JCheckBox chkCam4Enable;
    private javax.swing.JCheckBox chkCam4Rec;
    private javax.swing.JCheckBox chkCamIdentifier;
    private javax.swing.JCheckBox chkCameraType;
    private javax.swing.JCheckBox chkCanRecord;
    private javax.swing.JCheckBox chkCoordinates;
    private javax.swing.JCheckBox chkDms;
    private javax.swing.JCheckBox chkEthernet;
    private javax.swing.JCheckBox chkEventRecord;
    private javax.swing.JCheckBox chkFriday;
    private javax.swing.JCheckBox chkMonday;
    private javax.swing.JCheckBox chkMotionDet;
    private javax.swing.JCheckBox chkNetwork;
    private javax.swing.JCheckBox chkOnvifSupport;
    private javax.swing.JCheckBox chkPedEnable;
    private javax.swing.JCheckBox chkPmiEnable;
    private javax.swing.JCheckBox chkRadioEnable;
    private javax.swing.JCheckBox chkRestartPortsDetect;
    private javax.swing.JCheckBox chkRs232;
    private javax.swing.JCheckBox chkSaturday;
    private javax.swing.JCheckBox chkSeparateGPS;
    private javax.swing.JCheckBox chkSnapCont;
    private javax.swing.JCheckBox chkSnapContUpload;
    private javax.swing.JCheckBox chkSnapDi1Upload;
    private javax.swing.JCheckBox chkSnapDi2Upload;
    private javax.swing.JCheckBox chkSnapDi3Upload;
    private javax.swing.JCheckBox chkSnapDi4Upload;
    private javax.swing.JCheckBox chkSnapDig1;
    private javax.swing.JCheckBox chkSnapDig2;
    private javax.swing.JCheckBox chkSnapDig3;
    private javax.swing.JCheckBox chkSnapDig4;
    private javax.swing.JCheckBox chkSnapShotEnable;
    private javax.swing.JCheckBox chkSpeed;
    private javax.swing.JCheckBox chkSpelAudioAnnounc;
    private javax.swing.JCheckBox chkSunday;
    private javax.swing.JCheckBox chkThursday;
    private javax.swing.JCheckBox chkTuesday;
    private javax.swing.JCheckBox chkVechicleRegistration;
    private javax.swing.JCheckBox chkWednesday;
    private javax.swing.JComboBox cmbBaudRate;
    private javax.swing.JComboBox cmbBitrateType;
    private javax.swing.JComboBox cmbCam1Type;
    private javax.swing.JComboBox cmbCam2Type;
    private javax.swing.JComboBox cmbCam3Type;
    private javax.swing.JComboBox cmbCam4Type;
    private javax.swing.JComboBox<String> cmbDeleteFiles;
    private javax.swing.JComboBox cmbFramerate;
    private javax.swing.JComboBox cmbMaxbitrate;
    private javax.swing.JComboBox cmbModVersion;
    private javax.swing.JComboBox<String> cmbModuleType;
    private javax.swing.JComboBox cmbResolution;
    private javax.swing.JComboBox cmbSelectCameraBrightness;
    private javax.swing.JComboBox cmbSelectCameraEncode;
    private javax.swing.JComboBox cmbSelectCameraMirror;
    private javax.swing.JComboBox<String> cmbSelectCameraNo;
    private javax.swing.JComboBox cmbSelectCameraWatermark;
    private javax.swing.JComboBox cmbSnapContStreamType;
    private javax.swing.JComboBox cmbSnapEventStreamDig1;
    private javax.swing.JComboBox cmbSnapEventStreamDig2;
    private javax.swing.JComboBox cmbSnapEventStreamDig3;
    private javax.swing.JComboBox cmbSnapEventStreamDig4;
    private javax.swing.JComboBox cmbStreamType;
    private javax.swing.JComboBox cmbVidEncoding;
    private javax.swing.JComboBox cmbVidStreamTypeCan;
    private javax.swing.JComboBox cmbVidStreamTypeEvent;
    private javax.swing.JComboBox cmbVideomode;
    private javax.swing.JComboBox cmbVideoquality;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblBright;
    private javax.swing.JLabel lblBrihtness;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JTextField lblConfigName1;
    private javax.swing.JLabel lblContrast;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblEmergencyService1;
    private javax.swing.JLabel lblEmergencyService2;
    private javax.swing.JLabel lblEmergencyService3;
    private javax.swing.JLabel lblEmergencyService4;
    private javax.swing.JLabel lblEmergencyService5;
    private javax.swing.JLabel lblEmergencyService6;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblMsg3;
    private javax.swing.JLabel lblMsg4;
    private javax.swing.JLabel lblMsgBrightness;
    private javax.swing.JLabel lblMsgCamera;
    private javax.swing.JLabel lblMsgEncode;
    private javax.swing.JLabel lblMsgMirror;
    private javax.swing.JLabel lblMsgMotionDetect;
    private javax.swing.JLabel lblMsgRecord;
    private javax.swing.JLabel lblMsgRecord1;
    private javax.swing.JLabel lblMsgSnapshot;
    private javax.swing.JLabel lblMsgWatermark;
    private javax.swing.JLabel lblObuId;
    private javax.swing.JLabel lblObuId1;
    private javax.swing.JLabel lblObuId2;
    private javax.swing.JLabel lblObuId4;
    private javax.swing.JLabel lblObuId6;
    private javax.swing.JLabel lblObuId7;
    private javax.swing.JLabel lblObuId8;
    private javax.swing.JLabel lblObuId9;
    private javax.swing.JLabel lblOut;
    private javax.swing.JLabel lblSaturation;
    private javax.swing.JLabel lblSaturationLevel1;
    private javax.swing.JLabel lblSerNo;
    private javax.swing.JLabel lblSerNo1;
    private javax.swing.JLabel lblSerNo10;
    private javax.swing.JLabel lblSerNo11;
    private javax.swing.JLabel lblSerNo12;
    private javax.swing.JLabel lblSerNo13;
    private javax.swing.JLabel lblSerNo2;
    private javax.swing.JLabel lblSerNo3;
    private javax.swing.JLabel lblSerNo4;
    private javax.swing.JLabel lblSerNo5;
    private javax.swing.JLabel lblSerNo6;
    private javax.swing.JLabel lblSerNo7;
    private javax.swing.JLabel lblSerNo8;
    private javax.swing.JLabel lblSerNo9;
    private javax.swing.JLabel lblTestDate;
    private javax.swing.JLabel lblTestDate1;
    private javax.swing.JLabel lblTestDate2;
    private javax.swing.JLabel lblText;
    private javax.swing.JLabel lblToDate;
    private javax.swing.JTextField lblVideoWaterMarkingConfigName1;
    private javax.swing.JLabel lblbitrate;
    private javax.swing.JLabel lblcontrast1;
    private javax.swing.JLabel lblframerate;
    private javax.swing.JLabel lblframerate1;
    private javax.swing.JLabel lblmaxbit;
    private javax.swing.JLabel lblresoulation;
    private javax.swing.JLabel lblvideoaudio;
    private javax.swing.JLabel lblvideoencode;
    private javax.swing.JLabel lblvideoquality;
    private javax.swing.JLabel lbselectcamera;
    private javax.swing.JLabel lbselectcamera1;
    private javax.swing.JLabel lbselectcamera2;
    private javax.swing.JLabel lbselectcamera3;
    private javax.swing.JPanel pan1;
    private javax.swing.JPanel pan2;
    private javax.swing.JPanel pan3;
    private javax.swing.JPanel pan4;
    private javax.swing.JPanel panBorder;
    private javax.swing.JPanel panBorder1;
    private javax.swing.JPanel panBorder2;
    private javax.swing.JPanel panCam1;
    private javax.swing.JPanel panCam2;
    private javax.swing.JPanel panCam3;
    private javax.swing.JPanel panCam4;
    private javax.swing.JPanel panManRecord;
    private javax.swing.JPanel panSnap;
    private javax.swing.JPanel panSnapCont;
    private javax.swing.JPanel panSnapEvent;
    private javax.swing.JProgressBar pbarBrightness;
    private javax.swing.JProgressBar pbarEncode;
    private javax.swing.JProgressBar pbarMirror;
    private javax.swing.JProgressBar pbarMotion;
    private javax.swing.JProgressBar pbarWaterMark;
    private javax.swing.JRadioButton radCam1;
    private javax.swing.JRadioButton radCam1Motion;
    private javax.swing.JRadioButton radCam2;
    private javax.swing.JRadioButton radCam2Motion;
    private javax.swing.JRadioButton radCam3;
    private javax.swing.JRadioButton radCam3Motion;
    private javax.swing.JRadioButton radCam4;
    private javax.swing.JRadioButton radCam4Motion;
    private javax.swing.JRadioButton radContRecord;
    private javax.swing.JRadioButton radGpsData;
    private javax.swing.JRadioButton radGpsDataRs232;
    private javax.swing.JRadioButton radManRecord;
    private javax.swing.JRadioButton radMirrorOff;
    private javax.swing.JRadioButton radMirrorOn;
    private javax.swing.JRadioButton radRfid;
    private javax.swing.JRadioButton radRfidRs232;
    private javax.swing.JRadioButton radalcoBrake;
    private javax.swing.JSlider sliderBrightness;
    private javax.swing.JSlider sliderContrastLevel1;
    private javax.swing.JSlider sliderSaturationLevel1;
    private javax.swing.JSpinner spinComDate;
    private javax.swing.JSpinner spinDevResetCnt;
    private javax.swing.JSpinner spinEndDate;
    private javax.swing.JSpinner spinEventRecStorage;
    private javax.swing.JSpinner spinFrameInterval;
    private javax.swing.JSpinner spinGpsAntenna;
    private javax.swing.JSpinner spinGpsInvalid;
    private javax.swing.JSpinner spinGpsLost;
    private javax.swing.JSpinner spinHwRev;
    private javax.swing.JSpinner spinInterval;
    private javax.swing.JSpinner spinIntervalRs232;
    private javax.swing.JSpinner spinLowVoltReset;
    private javax.swing.JSpinner spinLowVoltage;
    private javax.swing.JSpinner spinOverHeat;
    private javax.swing.JSpinner spinOverVolt;
    private javax.swing.JSpinner spinSnapContInterval;
    private javax.swing.JSpinner spinSnapEventntervalDig1;
    private javax.swing.JSpinner spinSnapEventntervalDig2;
    private javax.swing.JSpinner spinSnapEventntervalDig3;
    private javax.swing.JSpinner spinSnapEventntervalDig4;
    private javax.swing.JSpinner spinSnapStorage;
    private javax.swing.JSpinner spinStartDate;
    private javax.swing.JSpinner spinTestDate;
    private javax.swing.JSpinner spinUsbInvalFileSys;
    private javax.swing.JSpinner spinUsbInvalid;
    private javax.swing.JSpinner spinUsbOverCurrent;
    private javax.swing.JSpinner spinUsbUnknown;
    private javax.swing.JSpinner spinVidRecStorage1;
    private javax.swing.JSpinner spinWatchdogReset;
    private javax.swing.JSpinner spinrecordtime;
    private javax.swing.JSpinner spinsensitivityVal;
    private javax.swing.JSpinner spinthresholdVal;
    private javax.swing.JSpinner spinx1value;
    private javax.swing.JSpinner spinx2value;
    private javax.swing.JSpinner spiny1value;
    private javax.swing.JSpinner spiny2value;
    private javax.swing.JPanel tabCamBrightness;
    private javax.swing.JPanel tabMirror;
    private javax.swing.JPanel tabMotionDetect;
    private javax.swing.JPanel tabRecordConfig;
    private javax.swing.JPanel tabSnapshot;
    private javax.swing.JPanel tabWaterMark;
    private javax.swing.JPanel tanCamType;
    private javax.swing.JTextField txtCam1User;
    private javax.swing.JTextField txtCam2User;
    private javax.swing.JTextField txtCam3User;
    private javax.swing.JTextField txtCam4User;
    private javax.swing.JTextField txtDeviceName;
    private javax.swing.JTextField txtIpAddress;
    private javax.swing.JTextField txtPortNo;
    private javax.swing.JTextField txtSerialNo;
    // End of variables declaration//GEN-END:variables
}
