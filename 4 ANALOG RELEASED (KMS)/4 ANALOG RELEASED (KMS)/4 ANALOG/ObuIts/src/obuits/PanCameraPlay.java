package obuits;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.setVideoEnableStarted;
import static obuits.MainFrmIts.setVideoStarted;
import static obuits.clsDefines.can_video_event_filepath;
import static obuits.clsDefines.motion_video_event_filepath;
import static obuits.clsDefines.snap_filepath;
import static obuits.clsDefines.video_event_filepath;
import static obuits.clsDefines.video_filepath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import static obuits.clsDefines.main_route_path;
import static obuits.clsDefines.media_path;
import static obuits.clsDefines.mpv_found;

public class PanCameraPlay extends javax.swing.JPanel {

    static boolean video_playing = false;
    public static Process play_video_process;
    public static long play_process_id = 0;
    ArrayList<JRadioButton> arrayListChkCamFiles = new ArrayList<JRadioButton>();
    JSpinner spinStartTime = new JSpinner();
    JSpinner spinEndTime = new JSpinner();
    JDateChooser dateChooserFrom = new JDateChooser();
    JDateChooser dateChooserTo = new JDateChooser();

    private void runCommand(String command) {
        try {
            play_video_process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ffplay -left 80 -top 100 -x 660 -y 520 -an  -noborder -window_title \" \" -nostats -sync video  -max_delay 1 " + command});
            try {
                Field f = play_video_process.getClass().getDeclaredField("pid");
                f.setAccessible(true);
                play_process_id = (int) f.get(play_video_process);

            } catch (Exception ex) {
            }
            OutStreamCamplayThread outPlay = new OutStreamCamplayThread();
            outPlay.start();
            play_video_process.waitFor();
            outPlay.interrupt();
            try {
                Process p = Runtime.getRuntime().exec("sudo killall -9 " + play_process_id);
                p.waitFor();
                play_video_process.destroy();
                p.destroy();
                p = null;
                play_process_id = 0;
                play_video_process = null;

            } catch (Exception ex) {

            }
        } catch (Exception e) {
        } finally {

            play_video_process = null;
        }
    }

    private boolean runmpvCmd(String cmd) {
        try {
            play_video_process = Runtime.getRuntime().exec("mpv --geometry=800x570+0+0 --no-window-dragging  --no-border --no-audio --no-keepaspect --script-opts=osc-layout=box --input-doubleclick-time=100 " + cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(play_video_process.getErrorStream()));
            MainFrmIts.ButtonStop.setVisible(true);
            String line;
            while ((line = reader.readLine()) != null) {
                // Consume the output, this is important to prevent blocking
                //  System.out.println(line);
            }
            int exitCode = play_video_process.waitFor();
            if (exitCode == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    class OutStreamCamplayThread extends Thread {

        BufferedReader reader = null;

        public OutStreamCamplayThread() {
            super();
        }

        @Override
        public void run() {
            String data;
            try {
                reader = new BufferedReader(new InputStreamReader(play_video_process.getErrorStream()));

                while (true) {
                    if (reader.ready()) {
                        data = reader.readLine();
                        if (data.contains("have a nice day")) {
                            break;
                        }
                        if (data.contains("route to host")) {
                            break;
                        }
                    }
                    if (video_playing == false || play_video_process == null) {
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
                Process p;
                if (mpv_found == true) {
                    p = Runtime.getRuntime().exec("sudo pkill mpv");
                } else {
                    p = Runtime.getRuntime().exec("sudo pkill ffplay");
                }
                p.waitFor();
                p = null;
                play_process_id = 0;
                play_video_process = null;
            } catch (Exception ex) {

            }

            try {

                play_video_process.getInputStream().close();
                play_video_process.getOutputStream().close();
                play_video_process.getErrorStream().close();
                play_video_process.destroyForcibly();
                play_video_process.exitValue();
                play_video_process = null;
            } catch (Exception ex) {

            }

        }

    }
    /**
     * Creates new form PanVideoPlay
     */
    int files_cnt = 0;

    public PanCameraPlay() {
        initComponents();
        belowpanel.setVisible(false);
        btnstp.setVisible(false);
        setVideoEnableStarted(true);
        Date date;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        Font dateFont = new java.awt.Font("Ubuntu", 1, 16);
        dateChooserFrom.setFont(dateFont);
        dateChooserFrom.setDate(date);
        dateChooserFrom.setBounds(10, 5, 170, 25);
        panFromDateTime.add(dateChooserFrom);

        dateChooserTo.setDate(date);
        dateChooserTo.setBounds(10, 5, 170, 25);
        dateChooserTo.setFont(dateFont);
        panToDateTime.add(dateChooserTo);

        SpinnerDateModel timeModel = new SpinnerDateModel();
        spinStartTime.setModel(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinStartTime, "HH:mm");
        spinStartTime.setEditor(timeEditor);
        spinStartTime.setValue(new Date());
        spinStartTime.setEnabled(true);
        spinStartTime.setFont(dateFont);
        spinStartTime.setBounds(200, 5, 90, 25);
        panFromDateTime.add(spinStartTime);

        SpinnerDateModel timeModelTo = new SpinnerDateModel();
        spinEndTime.setModel(timeModelTo);
        JSpinner.DateEditor timeEditorTo = new JSpinner.DateEditor(spinEndTime, "HH:mm");
        spinEndTime.setEditor(timeEditorTo);
        spinEndTime.setValue(new Date());
        spinEndTime.setEnabled(true);
        spinEndTime.setFont(dateFont);
        spinEndTime.setBounds(200, 5, 90, 25);
        panToDateTime.add(spinEndTime);
        ScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblVideoConfigName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        panCamDate1 = new javax.swing.JPanel();
        lblFromDate = new javax.swing.JLabel();
        lblToDate = new javax.swing.JLabel();
        panFromDateTime = new javax.swing.JPanel();
        panToDateTime = new javax.swing.JPanel();
        belowpanel = new javax.swing.JPanel();
        btnstp = new javax.swing.JButton();
        ScrollPane = new javax.swing.JScrollPane();
        PanVideoFilesList = new javax.swing.JPanel();
        btnupdatevideos = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cmbCamera = new javax.swing.JComboBox();
        cmbVideoFiles = new javax.swing.JComboBox<>();
        btnListoffiles = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnApcPing = new javax.swing.JButton();
        cmbPingCheck = new javax.swing.JComboBox<>();
        btnApcPing1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPing = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        btnTakeSnap = new javax.swing.JButton();
        btnChk = new javax.swing.JButton();
        lblSnap = new javax.swing.JLabel();
        lblConfigName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbCameraSnapshot = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(521, 430));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        lblVideoConfigName.setEditable(false);
        lblVideoConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblVideoConfigName.setFont(lblVideoConfigName.getFont().deriveFont(lblVideoConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblVideoConfigName.getFont().getSize()+10));
        lblVideoConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblVideoConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblVideoConfigName.setText("CAMERA PLAY");
        lblVideoConfigName.setPreferredSize(new java.awt.Dimension(500, 32));
        lblVideoConfigName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblVideoConfigNameActionPerformed(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 500));

        panCamDate1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panCamDate1.setPreferredSize(new java.awt.Dimension(387, 99));

        lblFromDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFromDate.setForeground(new java.awt.Color(0, 0, 102));
        lblFromDate.setText("From Date ");

        lblToDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblToDate.setForeground(new java.awt.Color(0, 0, 102));
        lblToDate.setText("To Date");

        panFromDateTime.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panFromDateTime.setFont(panFromDateTime.getFont().deriveFont(panFromDateTime.getFont().getSize()+4f));

        javax.swing.GroupLayout panFromDateTimeLayout = new javax.swing.GroupLayout(panFromDateTime);
        panFromDateTime.setLayout(panFromDateTimeLayout);
        panFromDateTimeLayout.setHorizontalGroup(
            panFromDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panFromDateTimeLayout.setVerticalGroup(
            panFromDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        panToDateTime.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panToDateTimeLayout = new javax.swing.GroupLayout(panToDateTime);
        panToDateTime.setLayout(panToDateTimeLayout);
        panToDateTimeLayout.setHorizontalGroup(
            panToDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panToDateTimeLayout.setVerticalGroup(
            panToDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panCamDate1Layout = new javax.swing.GroupLayout(panCamDate1);
        panCamDate1.setLayout(panCamDate1Layout);
        panCamDate1Layout.setHorizontalGroup(
            panCamDate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCamDate1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panCamDate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblToDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCamDate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panFromDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panToDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panCamDate1Layout.setVerticalGroup(
            panCamDate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panCamDate1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panCamDate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panFromDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panCamDate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblToDate, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(panToDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        belowpanel.setPreferredSize(new java.awt.Dimension(66, 320));

        btnstp.setFont(btnstp.getFont().deriveFont(btnstp.getFont().getStyle() | java.awt.Font.BOLD, btnstp.getFont().getSize()+5));
        btnstp.setForeground(new java.awt.Color(0, 0, 102));
        btnstp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop1.png"))); // NOI18N
        btnstp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnstp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout belowpanelLayout = new javax.swing.GroupLayout(belowpanel);
        belowpanel.setLayout(belowpanelLayout);
        belowpanelLayout.setHorizontalGroup(
            belowpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, belowpanelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(btnstp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        belowpanelLayout.setVerticalGroup(
            belowpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(belowpanelLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(btnstp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        ScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollPane.setPreferredSize(new java.awt.Dimension(400, 240));

        PanVideoFilesList.setPreferredSize(new java.awt.Dimension(400, 5000));

        javax.swing.GroupLayout PanVideoFilesListLayout = new javax.swing.GroupLayout(PanVideoFilesList);
        PanVideoFilesList.setLayout(PanVideoFilesListLayout);
        PanVideoFilesListLayout.setHorizontalGroup(
            PanVideoFilesListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        PanVideoFilesListLayout.setVerticalGroup(
            PanVideoFilesListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5000, Short.MAX_VALUE)
        );

        ScrollPane.setViewportView(PanVideoFilesList);

        btnupdatevideos.setFont(btnupdatevideos.getFont().deriveFont(btnupdatevideos.getFont().getStyle() | java.awt.Font.BOLD, btnupdatevideos.getFont().getSize()+7));
        btnupdatevideos.setForeground(new java.awt.Color(0, 0, 102));
        btnupdatevideos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/play1.png"))); // NOI18N
        btnupdatevideos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnupdatevideos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatevideosActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+4));
        lblMsg.setText("Files");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnupdatevideos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMsg))
                    .addComponent(panCamDate1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(belowpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(belowpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panCamDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnupdatevideos)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblMsg)))))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 44));

        cmbCamera.setFont(cmbCamera.getFont().deriveFont(cmbCamera.getFont().getStyle() | java.awt.Font.BOLD, cmbCamera.getFont().getSize()+5));
        cmbCamera.setForeground(new java.awt.Color(0, 0, 102));
        cmbCamera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CAMERA 1", "CAMERA 2", "CAMERA 3", "CAMERA 4" }));
        cmbCamera.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCameraItemStateChanged(evt);
            }
        });
        cmbCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCameraActionPerformed(evt);
            }
        });

        cmbVideoFiles.setFont(cmbVideoFiles.getFont().deriveFont(cmbVideoFiles.getFont().getStyle() | java.awt.Font.BOLD, cmbVideoFiles.getFont().getSize()+5));
        cmbVideoFiles.setForeground(new java.awt.Color(0, 0, 102));
        cmbVideoFiles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal Video", "Event Video", "CAN Video", "Snap Cont", "Snap Dig1", "Snap Dig2", "Snap Dig3", "Snap Dig4", "Motion", " " }));
        cmbVideoFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVideoFilesActionPerformed(evt);
            }
        });

        btnListoffiles.setBackground(new java.awt.Color(47, 49, 51));
        btnListoffiles.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnListoffiles.setForeground(new java.awt.Color(255, 255, 255));
        btnListoffiles.setText("SHOW");
        btnListoffiles.setPreferredSize(new java.awt.Dimension(133, 47));
        btnListoffiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListoffilesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cmbCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbVideoFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnListoffiles, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbVideoFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListoffiles, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVideoConfigName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 506, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblVideoConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("CAMERA PLAY", jPanel1);

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 500));

        btnApcPing.setBackground(new java.awt.Color(47, 49, 51));
        btnApcPing.setFont(btnApcPing.getFont().deriveFont(btnApcPing.getFont().getStyle() | java.awt.Font.BOLD, btnApcPing.getFont().getSize()+5));
        btnApcPing.setForeground(new java.awt.Color(255, 255, 255));
        btnApcPing.setText("PING START");
        btnApcPing.setBorderPainted(false);
        btnApcPing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApcPingActionPerformed(evt);
            }
        });

        cmbPingCheck.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cmbPingCheck.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CAM1", "CAM2", "CAM3", "CAM4", "CAM5", "CAM6", "CAM7", "CAM8", "SERVER TEST", "WMCTRL", "XDOTOOL", "MPV", "UPDATE", "APC FILE" }));

        btnApcPing1.setBackground(new java.awt.Color(47, 49, 51));
        btnApcPing1.setFont(btnApcPing1.getFont().deriveFont(btnApcPing1.getFont().getStyle() | java.awt.Font.BOLD, btnApcPing1.getFont().getSize()+5));
        btnApcPing1.setForeground(new java.awt.Color(255, 255, 255));
        btnApcPing1.setText("PING STOP");
        btnApcPing1.setBorderPainted(false);
        btnApcPing1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApcPing1ActionPerformed(evt);
            }
        });

        txtPing.setColumns(20);
        txtPing.setRows(5);
        jScrollPane1.setViewportView(txtPing);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cmbPingCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnApcPing, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApcPing1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPingCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApcPing)
                    .addComponent(btnApcPing1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PING", jPanel4);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 500));

        btnTakeSnap.setFont(btnTakeSnap.getFont().deriveFont(btnTakeSnap.getFont().getStyle() | java.awt.Font.BOLD, btnTakeSnap.getFont().getSize()+7));
        btnTakeSnap.setForeground(new java.awt.Color(51, 0, 102));
        btnTakeSnap.setText("Take Snapshot");
        btnTakeSnap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakeSnapActionPerformed(evt);
            }
        });

        btnChk.setFont(btnChk.getFont().deriveFont(btnChk.getFont().getStyle() | java.awt.Font.BOLD, btnChk.getFont().getSize()+5));
        btnChk.setText("Check");
        btnChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChkActionPerformed(evt);
            }
        });

        lblSnap.setIconTextGap(0);
        lblSnap.setPreferredSize(new java.awt.Dimension(490, 350));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("SNAPSHOT");
        lblConfigName.setPreferredSize(new java.awt.Dimension(500, 32));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+5));
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Select");

        cmbCameraSnapshot.setFont(cmbCameraSnapshot.getFont().deriveFont(cmbCameraSnapshot.getFont().getStyle() | java.awt.Font.BOLD, cmbCameraSnapshot.getFont().getSize()+5));
        cmbCameraSnapshot.setForeground(new java.awt.Color(0, 0, 102));
        cmbCameraSnapshot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CAMERA 1", "CAMERA 2", "CAMERA 3", "CAMERA 4", "CAMERA 5", "CAMERA 6", "CAMERA 7", "CAMERA 8" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnTakeSnap, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCameraSnapshot, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSnap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCameraSnapshot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChk, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnTakeSnap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSnap, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        jTabbedPane1.addTab("SNAPSHOT", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    SwingWorker sw1 = null;
    Runtime rt = null;
    Process ping_view_proc;
    int pid;
    OutStreamPingThread pingOutCam = null;
    boolean ping_check_start = false;

    public class clsPingChk extends Thread {

        String pingaddr;

        public clsPingChk(String ipAddressPing) {
            super();
            pingaddr = ipAddressPing;
        }

        @Override
        public void run() {
            String cmd = "ping " + pingaddr;
            switch (cmbPingCheck.getSelectedIndex()) {
                case 9:
                    cmd = "sudo apt-get install wmctrl";
                    break;
                case 10:
                    cmd = "sudo apt-get install -y xdotool";
                    break;
                case 11:
                    cmd = "sudo apt-get install -y mpv";
                    break;
                case 12:
                    cmd = "sudo apt update";
                    break;
                case 13:
                    cmd = "md5sum /usr/bin/rtsp2avi";
                    break;
                default:
                    break;
            }

            try {

                rt = Runtime.getRuntime();
                ping_view_proc = rt.exec(new String[]{"bash", "-c", cmd});
                Field f = ping_view_proc.getClass().getDeclaredField("pid");
                f.setAccessible(true);
                pid = (int) f.get(ping_view_proc);
            } catch (Exception ex) {

            }
            if (pingOutCam != null) {
                pingOutCam.interrupt();
                pingOutCam = null;
            }

            pingOutCam = new OutStreamPingThread();
            pingOutCam.start();

            try {
                ping_view_proc.waitFor();
            } catch (InterruptedException ex) {
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    txtPing.append("Exit ...");
                    txtPing.append("\n");
                }
            });

            pingOutCam.interrupt();
        }
    }

    class OutStreamPingThread extends Thread {

        BufferedReader reader = null;

        public OutStreamPingThread() {
            super();
        }

        @Override
        public void run() {
            String s;
            try {
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(ping_view_proc.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(ping_view_proc.getErrorStream()));

                while ((s = stdInput.readLine()) != null) {
                    final String s1 = s;
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                txtPing.append(s1);
                                txtPing.append("\n");
                                int numLinesToTrunk = txtPing.getLineCount() - clsDefines.SCROLL_BUFFER_SIZE;
                                if (numLinesToTrunk > 0) {
                                    try {
                                        int posOfLastLineToTrunk = txtPing.getLineEndOffset(numLinesToTrunk - 1);
                                        txtPing.replaceRange("", 0, posOfLastLineToTrunk);
                                    } catch (Exception ex) {
                                    }
                                    try {
                                        System.gc();
                                    } catch (Exception ex) {
                                    }
                                }
                            }

                        });
                    } catch (Exception ex) {
                    }

                    if (ping_check_start == false) {
                        try {
                            ping_view_proc.destroy();
                        } catch (Exception ex) {

                        }
                        break;
                    }
                }
                while ((s = stdError.readLine()) != null) {
                    final String s1 = s;
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                txtPing.append(s1);
                                txtPing.append("\n");
                                int numLinesToTrunk = txtPing.getLineCount() - clsDefines.SCROLL_BUFFER_SIZE;
                                if (numLinesToTrunk > 0) {
                                    try {
                                        int posOfLastLineToTrunk = txtPing.getLineEndOffset(numLinesToTrunk - 1);
                                        txtPing.replaceRange("", 0, posOfLastLineToTrunk);
                                    } catch (Exception ex) {
                                    }
                                    try {
                                        System.gc();
                                    } catch (Exception ex) {
                                    }
                                }
                            }
                        });
                    } catch (Exception ex) {
                    }
                    if (ping_check_start == false) {
                        try {
                            ping_view_proc.destroy();
                        } catch (Exception ex) {

                        }
                        break;
                    }
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
                    p.waitFor(5, TimeUnit.SECONDS);
                    p = null;
                } catch (Exception ex) {

                }
                try {
                    rt.freeMemory();
                    rt.gc();
                } catch (Exception ex) {

                }
                try {
                    ping_view_proc.getInputStream().close();
                    ping_view_proc.getOutputStream().close();
                    ping_view_proc.getErrorStream().close();
                } catch (Exception ex) {

                }
                ping_view_proc.destroyForcibly();
                ping_view_proc.exitValue();
                ping_view_proc = null;
                rt = null;

            } catch (Exception ex) {

            }

        }
    }

    @Override
    public void removeNotify() {
        if (sw1 != null) {
            try {
                sw1.cancel(true);
                sw1 = null;
            } catch (Exception ex) {

            }
        }
        video_playing = false;
        MainFrmIts.ButtonStop.setVisible(false);
        setVideoEnableStarted(false);
        clsSharedVariables.setVideoPlayStarted(false);
        SwingUtilities.invokeLater(() -> {
            Process p;
            try {
                if (mpv_found == true) {
                    p = Runtime.getRuntime().exec("sudo pkill mpv");
                }
                //for snapshot killing
                p = Runtime.getRuntime().exec("sudo pkill ffplay");

                if (play_video_process != null && play_video_process.exitValue() != 0) {
                    play_video_process.destroy();
                    play_video_process = null;
                }

                if (play_process_id > 0) {
                    Process p1 = Runtime.getRuntime().exec("sudo kill -SIGKILL   " + play_process_id);
                    p1.wait(2000);
                    p1 = null;
                    play_process_id = 0;

                }
            } catch (IllegalThreadStateException e) {
            } catch (Exception ex) {

            }
        });
        super.removeNotify();

    }

    private void startCopyThread(File cam_path) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {

                JRadioButton index;
                Iterator<JRadioButton> chkCamFiles = null;

                short no_selected_files = 0;
                final File[] files = cam_path.listFiles();
                try {
                    if (files != null) {
                        chkCamFiles = arrayListChkCamFiles.iterator();
                        while (chkCamFiles.hasNext()) {
                            index = chkCamFiles.next();
                            if (index.isSelected()) {
                                no_selected_files++;
                            }
                        }

                        chkCamFiles = arrayListChkCamFiles.iterator();
                        if (arrayListChkCamFiles.isEmpty()) {
                            publish("FilesNotThere");
                            return "FilesNotThere";
                        } else {
                            publish(" File Copying... please don't remove the USB");
                            publish("pbar nofiles:" + no_selected_files);
                            //files_cnt = 0;

                            while (chkCamFiles.hasNext()) {

                                index = chkCamFiles.next();
                                if (index.isSelected()) {
                                    runCmd("sudo chmod -R 777  " + cam_path + "/" + index.getText());
                                }

                            }

                        }
                    }
                } catch (Exception e) {
                    return "Over";
                } finally {
                    index = null;
                    chkCamFiles = null;
                    play_video_process = null;
                }
                publish("Over");
                return "Over";
            }

            int files_cnt = 0;

            @Override
            protected void process(List chunks) {
                String data;
                String[] split_str;
                try {
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        if (data.equals("Over")) {
                            break;

                        } else if (data.equals("Disconnected")) {
                            break;

                        } else if (data.equals("FilesNotThere")) {
                            break;

                        } else if (data.contains("pbar nofiles")) {
                            split_str = data.split(":", -1);
                            if (split_str.length > 1) {
                                try {
                                    //    progBar.setMaximum(Integer.parseInt(split_str[1]));
                                } catch (Exception ex) {

                                }
                            }

                        } else if (data.contains("pbar")) {
                            split_str = data.split(":", -1);
                            if (split_str.length > 1) {
                                try {
                                    //  progBar.setValue(Integer.parseInt(split_str[1]));
                                } catch (Exception ex) {

                                }
                            }

                        }

                    }
                } catch (Exception e) {

                } finally {
                    data = null;
                    split_str = null;
                }

            }

            @Override
            protected void done() {

            }
        };
        sw1.execute();
    }
    private static String[] final_cam_files;
    private static int cam_inc = 0;

    private void startCopy(long from_date_int, long to_date_int, File cam_path) {
        SwingWorker sw1;
        sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i;
                String str;
                long str_int;
                String[] split_str;

                try {

                    final File[] files = cam_path.listFiles();
                    Arrays.sort(files, java.util.Collections.reverseOrder());
                    if (files != null && files.length == 0) {
                        cam_inc = 0;
                        publish("FilesNotThere");
                    } else {
                        final_cam_files = new String[files.length];
                        cam_inc = 0;
                        for (i = 0; i < files.length; i++) {
                            if (files[i].isFile()) {
                                str = files[i].getName();
                                split_str = str.split("_");
                                if (split_str.length > 1) {
                                    str = split_str[1];
                                }

                                try {

                                    str_int = Long.parseLong(str.substring(0, str.length() - 4));

                                    if (str_int > from_date_int && str_int < to_date_int) {
                                        final_cam_files[cam_inc++] = files[i].getName();
                                    }

                                } catch (Exception ex) {
                                    publish(ex.getMessage());
                                }
                            }
                        }
                        publish("Over");
                    }
                } catch (Exception e) {
                    return "disc";
                } finally {
                    str = null;

                    split_str = null;

                }
                return "over";
            }

            int files_cnt = 0;

            @Override
            protected void process(List chunks) {
                String data;
                int y = 0;
                try {
                    for (int j = 0; j < chunks.size(); j++) {
                        data = (String) chunks.get(j);
                        if (data.equals("Over")) {
                            PanVideoFilesList.setVisible(true);
                            if (cam_inc == 0) {
                                lblMsg.setText("No Files");
                            } else {
                                for (int i = 0; i < cam_inc; i++) {

                                    final JRadioButton cb1 = new JRadioButton(final_cam_files[i]); ///files[i].getName()
                                    PanVideoFilesList.add(cb1);
                                    cb1.setBounds(0, y, 450, 20);
                                    cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                    arrayListChkCamFiles.add(cb1);
                                    PanVideoFilesList.add(cb1);
                                    buttonGroup1.add(cb1);
                                    y += 30;
                                }
                            }

                        } else if (data.equals("FilesNotThere")) {

                            lblMsg.setText("Files Not Found");

                        }
                    }
                } catch (Exception e) {

                } finally {
                    data = null;
                }

            }

            @Override
            protected void done() {

            }
        };
        sw1.execute();
    }

    private void startCopyExtraFiles(File cam_path) {
        SwingWorker sw1;

        sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i;
                String str;
                long str_int;
                String[] split_str;

                try {

                    final File[] files = cam_path.listFiles();
                    Arrays.sort(files, java.util.Collections.reverseOrder());
                    if (files != null && files.length == 0) {
                        cam_inc = 0;
                        publish("FilesNotThere");

                    } else {
                        final_cam_files = new String[files.length];
                        cam_inc = 0;
                        for (i = 0; i < files.length; i++) {
                            if (files[i].isFile()) {
                                str = files[i].getName();
                                split_str = str.split("_");
                                if (split_str.length > 1) {
                                    str = split_str[1];
                                }
                                try {
                                    final_cam_files[cam_inc++] = files[i].getName();

                                } catch (Exception ex) {
                                    publish(ex.getMessage());
                                }
                            }
                        }
                        publish("Over");
                    }
                } catch (Exception e) {
                    return "disc";
                } finally {
                    str = null;
                    split_str = null;
                }
                return "over";
            }

            int files_cnt = 0;

            @Override
            protected void process(List chunks) {
                String data;
                int y = 0;
                try {

                    for (int j = 0; j < chunks.size(); j++) {
                        data = (String) chunks.get(j);
                        if (data.equals("Over")) {
                            PanVideoFilesList.setVisible(true);
                            if (cam_inc == 0) {
                                lblMsg.setText("No Files");
                            } else {
                                for (int i = 0; i < cam_inc; i++) {
                                    final JRadioButton cb1 = new JRadioButton(final_cam_files[i]); ///files[i].getName()
                                    PanVideoFilesList.add(cb1);
                                    cb1.setBounds(0, y, 450, 20);
                                    cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                    arrayListChkCamFiles.add(cb1);
                                    PanVideoFilesList.add(cb1);
                                    buttonGroup1.add(cb1);
                                    y += 30;
                                }
                            }

                        } else if (data.equals("FilesNotThere")) {
                            lblMsg.setText("Files Not Found");

                        }
                    }
                } catch (Exception e) {

                } finally {
                    data = null;
                }

            }

            @Override

            protected void done() {
                try {

                } catch (Exception e) {

                }
            }
        };
        sw1.execute();
    }

    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            Thread.sleep(1000);
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
                    process.destroy();
                    process = null;
                    rt = null;
                }
            } catch (Exception ex) {
            }
        }
    }
    private void lblVideoConfigNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblVideoConfigNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblVideoConfigNameActionPerformed

    private void btnstpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstpActionPerformed
        //btnstp.setVisible(false);
        MainFrmIts.ButtonStop.setVisible(false);
        video_playing = false;
        setVideoEnableStarted(false);
        clsSharedVariables.setVideoPlayStarted(false);
        SwingUtilities.invokeLater(() -> {
            Process p;
            try {
                if (mpv_found == true) {
                    p = Runtime.getRuntime().exec("sudo pkill mpv");
                }
                p = Runtime.getRuntime().exec("sudo pkill ffplay");
                if (play_process_id > 0) {
                    p = Runtime.getRuntime().exec("sudo pkill ffplay");

                    p.waitFor();
                }

                if (play_video_process != null && play_video_process.exitValue() != 0) {
                    play_video_process.destroy();
                    play_video_process = null;
                }
            } catch (Exception ex) {

            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PanCameraPlay.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        btnupdatevideos.setVisible(true);

    }//GEN-LAST:event_btnstpActionPerformed

    private void cmbCameraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCameraItemStateChanged
        // TODO add your handling code here:
        if (cmbCamera.getSelectedIndex() == 8) {

            cmbVideoFiles.setVisible(false);
        } else {

            cmbVideoFiles.setVisible(true);
        }
    }//GEN-LAST:event_cmbCameraItemStateChanged

    private void cmbCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCameraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCameraActionPerformed

    private void cmbVideoFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVideoFilesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVideoFilesActionPerformed

    private void btnListoffilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListoffilesActionPerformed
        // TODO add your handling code here:
        //  final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf_time = new SimpleDateFormat("HHmm");
        UserPrincipal p;
        File cam_path;
        long from_date_int;
        long to_date_int;
        String file_path;
        String from_date;
        String to_date;
        String dateTimeFrom = sdf.format(dateChooserFrom.getDate()) + sdf_time.format(spinStartTime.getValue());
        String dateTimeTo = sdf.format(dateChooserTo.getDate()) + sdf_time.format(spinEndTime.getValue());
        PanVideoFilesList.removeAll();
        arrayListChkCamFiles.clear();

        PanVideoFilesList.updateUI();
        if (!clsSharedVariables.getHardDriveDetected()) {
            lblMsg.setText("HardDisk Not detected");
            // return;
        }
        try {
            lblMsg.setText("");
            if (cmbVideoFiles.getSelectedIndex() == 0) {
                file_path = video_filepath.getAbsolutePath() + "/";
            } else if (cmbVideoFiles.getSelectedIndex() == 1) {
                file_path = (video_event_filepath.getAbsolutePath() + "/");
            } else if (cmbVideoFiles.getSelectedIndex() == 2) {
                file_path = (can_video_event_filepath.getAbsolutePath() + "/");
            } else if (cmbVideoFiles.getSelectedIndex() == 8) {
                file_path = (motion_video_event_filepath.getAbsolutePath() + "/");
            } else {
                file_path = (snap_filepath.getAbsolutePath() + "/");
            }
            switch (this.cmbCamera.getSelectedIndex()) {
                case 0:

                    file_path = file_path + "Cam1/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {
                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();

                        if (files != null) {
                            from_date = "1" + dateTimeFrom + "00";
                            from_date_int = Long.parseLong(from_date);
                            to_date = "1" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }
                            buttonGroup1.clearSelection();
                            startCopy(from_date_int, to_date_int, cam_path);
                            btnupdatevideos.setVisible(true);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }
                    break;

                case 1:

                    file_path = file_path + "Cam2/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "2" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "2" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }

                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }
                    break;
                case 2:

                    file_path = file_path + "Cam3/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "3" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "3" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }

                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }
                    break;
                case 3:

                    file_path = file_path + "Cam4/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "4" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "4" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }

                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }
                    break;
                case 4:

                    file_path = file_path + "Cam5/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "5" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "5" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }

                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }

                    break;
                case 5:

                    file_path = file_path + "Cam6/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "6" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "6" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }

                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }

                    break;
                case 6:

                    file_path = file_path + "Cam7/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "7" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "7" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }

                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }

                    break;
                case 7:

                    file_path = file_path + "Cam8/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files != null) {
                            from_date = "8" + dateTimeFrom + "00";

                            from_date_int = Long.parseLong(from_date);
                            to_date = "8" + dateTimeTo + "00";
                            to_date_int = Long.parseLong(to_date);
                            if (files == null || files.length == 0) {
                                lblMsg.setText("No Files");
                                return;
                            }
                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblMsg.setText("No Files");
                    }

                    break;
                case 8:
                    file_path = clsDefines.extra_video_filepath.getAbsolutePath();
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null || files.length == 0) {
                            lblMsg.setText("No Files");
                            return;
                        }

                        startCopyExtraFiles(cam_path);
                    } else {
                        lblMsg.setText("No Files");
                    }
                    break;
            }

        } catch (Exception e) {
            lblMsg.setText("Exc " + e.getMessage());
        } finally {
            cam_path = null;

            // out_path = null;
            file_path = null;
            p = null;
            from_date = null;
            to_date = null;
            dateTimeFrom = null;
            dateTimeTo = null;

        }
    }//GEN-LAST:event_btnListoffilesActionPerformed

    private void btnupdatevideosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatevideosActionPerformed

        if (!clsSharedVariables.getHardDriveDetected()) {
            return;
        }
        belowpanel.setVisible(true);
        btnstp.setVisible(true);
        clsSharedVariables.setVideoPlayStarted(true);
        sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                JRadioButton index;
                Iterator<JRadioButton> chkCamFiles;
                chkCamFiles = arrayListChkCamFiles.iterator();
                File cam_path = null;
                String str;
                String in = "";
                String file_path;
                setVideoStarted(true);
                lblMsg.setText(" ");
                PanVideoFilesList.updateUI();
                if (cmbVideoFiles.getSelectedIndex() == 0) {
                    file_path = video_filepath.getAbsolutePath() + "/";
                } else if (cmbVideoFiles.getSelectedIndex() == 1) {
                    file_path = (video_event_filepath.getAbsolutePath() + "/");
                } else if (cmbVideoFiles.getSelectedIndex() == 2) {
                    file_path = (can_video_event_filepath.getAbsolutePath() + "/");
                } else if (cmbVideoFiles.getSelectedIndex() == 8) {
                    file_path = (motion_video_event_filepath.getAbsolutePath() + "/");
                } else {
                    file_path = (snap_filepath.getAbsolutePath() + "/");
                }
                if (cmbCamera.getSelectedIndex() == 0) {
                    file_path = file_path + "Cam1/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {

                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;

                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {
                                    MainFrmIts.audio_speaker_off();
                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }
                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 1) {
                    file_path = file_path + "Cam2/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 2) {
                    file_path = file_path + "Cam3/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                //for snapshots mpv is not working
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 3) {
                    file_path = file_path + "Cam4/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 4) {
                    file_path = file_path + "Cam5/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 5) {
                    file_path = file_path + "Cam6/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 6) {
                    file_path = file_path + "Cam7/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 7) {
                    file_path = file_path + "Cam8/";
                    if (cmbVideoFiles.getSelectedIndex() == 3) {
                        file_path = file_path + "Cont/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbVideoFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                } else if (cmbCamera.getSelectedIndex() == 8) {
                    file_path = clsDefines.extra_video_filepath.getAbsolutePath();

                    cam_path = new File(file_path);

                    if (arrayListChkCamFiles.isEmpty()) {
                        lblMsg.setText("No Files");
                    } else {
                        while (chkCamFiles.hasNext()) {

                            index = chkCamFiles.next();
                            //files_cnt++;
                            if (index.isSelected()) {
                                str = cam_path + "/" + index.getText();
                                files_cnt++;
                                video_playing = true;
                                if (mpv_found && str.endsWith("avi")) {
                                    runmpvCmd(str);
                                } else {
                                    runCommand(str);
                                }
                                if (video_playing == false) {

                                    sw1.cancel(true);
                                    sw1 = null;
                                    break;
                                }

                            }
                        }
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                try {

                } catch (Exception e) {

                }
            }
        };

        sw1.execute();
    }//GEN-LAST:event_btnupdatevideosActionPerformed

    private void btnApcPingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApcPingActionPerformed

        txtPing.setText("");
        // TODO add your handling code here:
        ping_check_start = true;
        String ipAddressPing = clsDefines.CAM1_IPADDR;
        switch (cmbPingCheck.getSelectedIndex()) {
            case 0:
                ipAddressPing = clsDefines.CAM1_IPADDR;
                break;
            case 1:
                ipAddressPing = clsDefines.CAM2_IPADDR;
                break;
            case 2:
                ipAddressPing = clsDefines.CAM3_IPADDR;
                break;
            case 3:
                ipAddressPing = clsDefines.CAM4_IPADDR;
                break;
            case 4:
                ipAddressPing = clsDefines.CAM5_IPADDR;
                break;
            case 5:
                ipAddressPing = clsDefines.CAM6_IPADDR;
                break;
            case 6:
                ipAddressPing = clsDefines.CAM7_IPADDR;
                break;
            case 7:
                ipAddressPing = clsDefines.CAM8_IPADDR;
                break;
            case 8:
                ipAddressPing = ("8.8.8.8");
                break;

            default:
                break;
        }

        clsPingChk obj = new clsPingChk(ipAddressPing);
        obj.start();
    }//GEN-LAST:event_btnApcPingActionPerformed

    private void btnApcPing1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApcPing1ActionPerformed
        // TODO add your handling code here:
        ping_check_start = false;
    }//GEN-LAST:event_btnApcPing1ActionPerformed

    private void btnTakeSnapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeSnapActionPerformed
        // TODO add your handling code here:
        clsCamSnapshot snap;
        switch (cmbCameraSnapshot.getSelectedIndex()) {
            case 0:
                snap = new clsCamSnapshot((byte) 1);
                break;
            case 1:
                snap = new clsCamSnapshot((byte) 2);
                break;
            case 2:
                snap = new clsCamSnapshot((byte) 3);
                break;
            case 3:
                snap = new clsCamSnapshot((byte) 4);
                break;
            case 4:
                snap = new clsCamSnapshot((byte) 5);
                break;
            case 5:
                snap = new clsCamSnapshot((byte) 6);
                break;
            case 6:
                snap = new clsCamSnapshot((byte) 7);
                break;
            default:
                snap = new clsCamSnapshot((byte) 8);
                break;
        }
        snap.start();
    }//GEN-LAST:event_btnTakeSnapActionPerformed

    private void btnChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChkActionPerformed
        // TODO add your handling code here:
        File f;
        String OutFilename;
        String path;
        byte camera_no = 1;
        try {
            lblSnap.setIcon(null);
            if (clsSharedVariables.getHardDriveDetected()) {
                path = video_filepath.getAbsolutePath();
            } else {
                path = main_route_path.getAbsolutePath();
            }

            switch (cmbCameraSnapshot.getSelectedIndex()) {
                case 0:
                    camera_no = 1;
                    break;
                case 1:
                    camera_no = 2;
                    break;
                case 2:
                    camera_no = 3;
                    break;
                case 3:
                    camera_no = 4;
                    break;
                case 4:
                    camera_no = 5;
                    break;
                case 5:
                    camera_no = 6;
                    break;
                case 6:
                    camera_no = 7;
                    break;
                default:
                    camera_no = 8;
                    break;
            }

            OutFilename = media_path + "/snap1.jpg";
            f = new File(OutFilename);

            if (f.exists()) {
                f.setReadable(true, false);
                f.setWritable(true, false);
                f.setExecutable(true, false);
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    Image dimg = img.getScaledInstance(lblSnap.getWidth(), lblSnap.getHeight(),
                            Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    lblSnap.setIcon(imageIcon);
                    dimg = null;
                    imageIcon = null;
                    img = null;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                lblSnap.setIcon(null);
                lblSnap.setText(f.getAbsolutePath() + " File Not Found");
            }
        } catch (Exception ex) {
        }
        f = null;
        OutFilename = null;
    }//GEN-LAST:event_btnChkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanVideoFilesList;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JPanel belowpanel;
    private javax.swing.JButton btnApcPing;
    private javax.swing.JButton btnApcPing1;
    private javax.swing.JButton btnChk;
    private javax.swing.JButton btnListoffiles;
    private javax.swing.JButton btnTakeSnap;
    private javax.swing.JButton btnstp;
    private javax.swing.JButton btnupdatevideos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCamera;
    private javax.swing.JComboBox cmbCameraSnapshot;
    private javax.swing.JComboBox<String> cmbPingCheck;
    private javax.swing.JComboBox<String> cmbVideoFiles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblSnap;
    private javax.swing.JLabel lblToDate;
    private javax.swing.JTextField lblVideoConfigName;
    private javax.swing.JPanel panCamDate1;
    private javax.swing.JPanel panFromDateTime;
    private javax.swing.JPanel panToDateTime;
    private javax.swing.JTextArea txtPing;
    // End of variables declaration//GEN-END:variables
}
