package obuits;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.reboot_system;
import static obuits.clsDefines.WIFI_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.obu_filepath;
import static obuits.clsDefines.udev_rules_filepath;
import static obuits.clsDefines.usb_filepath;
import static obuits.clsSharedVariables.setWifiConnect;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import static obuits.MainFrmIts.digOutAudioVoiceCallPin;
import static obuits.MainFrmIts.imgWifi;
import static obuits.clsDefines.Strings.AUDIO_VOICECALL_COMM_ONOFF;
import static obuits.clsDefines.Strings.VOICE_CALL_ON_STATE;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.panel_config_filepath;
import static obuits.clsDefines.panel_desktop_filepath;
import static obuits.clsDefines.panel_filepath;
import static obuits.clsDefines.watchdog_filepath;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.MainFrmIts.write_gpio_pin_state;
import static obuits.clsDefines.ROUTEFS_PATH;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.Strings.VOICE_CALL_OFF_STATE;
import static obuits.clsDefines.TRIP_END;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsDefines.apc_filepath;
import static obuits.clsDefines.autostart_file;
import static obuits.clsDefines.blacklist_config_filepath;
import static obuits.clsDefines.can_video_event_filepath;
import static obuits.clsDefines.config_filepath;
import static obuits.clsDefines.config_title_bar_filepath;
import static obuits.clsDefines.extra_video_filepath;
import static obuits.clsDefines.main_filepath;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.main_route_path;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.motion_video_event_filepath;
import static obuits.clsDefines.obu_images_filepath;
import static obuits.clsDefines.obu_library_filepath;
import static obuits.clsDefines.quectel_rules_d_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDefines.rtsp_filepath;
import static obuits.clsDefines.rtsp_forrir_filepath;
import static obuits.clsDefines.snap_filepath;
import static obuits.clsDefines.sourceslist_file;
import static obuits.clsDefines.video_event_filepath;
import static obuits.clsDefines.video_filepath;
import static obuits.clsSharedVariables.ftp_port_no;
import static obuits.clsSharedVariables.ftpPassword;
import static obuits.clsSharedVariables.ftp_ipaddress;
import static obuits.clsSharedVariables.ftpusername;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getNoRoutes;
import static obuits.clsSharedVariables.objRouteMasFiles;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSharedVariables.setCurSchEndDate;
import static obuits.clsSharedVariables.setCurSchEndTime;
import static obuits.clsSharedVariables.setCurSchNoTrips;
import static obuits.clsSharedVariables.setCurSchRouteNo;
import static obuits.clsSharedVariables.setCurSchStartDate;
import static obuits.clsSharedVariables.setCurSchStartTime;
import static obuits.clsSharedVariables.setCurSchTripStatus;
import static obuits.clsSharedVariables.setCurTripNo;
import static obuits.clsSharedVariables.setCurTripStat;
import static obuits.clsSharedVariables.setSchId;
import static obuits.clsSharedVariables.setSchRouteEnable;
import org.apache.commons.net.ftp.FTPFile;

public class PanFileTransfer extends javax.swing.JPanel {

    boolean ph_contacts_updated = false;
    FTPClient ftp = null;
    static Timer wifi_status_timer = null;
    static TimerTask wifi_status_timer_task = null;
    ArrayList<JCheckBox> arrayListChkCamFiles = new ArrayList<JCheckBox>();
    final byte BTN_USB = 0;
    final byte BTN_FTP = 1;
    private byte btn_selected = BTN_USB;
    JSpinner spinStartTime = new JSpinner();
    JSpinner spinEndTime = new JSpinner();
    JDateChooser dateChooserFrom = new JDateChooser();
    JDateChooser dateChooserTo = new JDateChooser();
    final Color bgcolorusbwifi = new Color(47, 49, 51);
    final Color bgcolor = new Color(0, 0, 102);

    public PanFileTransfer() {
        initComponents();

        try {
            int i = 0;

            File[] out_path;
            progress.setVisible(false);
            lblwifi.setText(" ");
            lblFileProgress.setText("");
            this.panRadBtn.setVisible(false);
            this.panCopyTo.setVisible(false);
            chkWifi.setVisible(false);
            btnCopy.setVisible(false);
            lblwifi.setVisible(false);
            progBar.setVisible(false);
            cmbSubSelectFiles.setVisible(true);

            for (int j = 0; j < 2; j++) {
                out_path = usb_filepath.listFiles();
                Arrays.sort(out_path);
                for (i = 0; out_path != null && i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {

                        if (!out_path[i].canRead() && !out_path[i].canWrite()) {
                            runCmd("sudo rm -rf " + out_path[i]);

                        }
                    }
                }
            }

            try {
                NetworkInterface nif = NetworkInterface.getByName(WIFI_NETWORKINTERFACE_PATH);
                if (nif != null && nif.isUp()) {
                    imgWifi.setVisible(true);
                    chkWifi.setSelected(true);
                    setWifiConnect(true);
                } else {
                    imgWifi.setVisible(false);
                    chkWifi.setSelected(false);
                    setWifiConnect(false);
                }
                nif = null;
            } catch (SocketException ex) {
            }

            Date date;
            Calendar calendar = Calendar.getInstance();
            date = calendar.getTime();
            Font dateFont = new java.awt.Font("Ubuntu", 1, 14);
            dateChooserFrom.setFont(dateFont);
            dateChooserFrom.setDate(date);
            dateChooserFrom.setBounds(10, 5, 150, 30);
            panFromDateTime.add(dateChooserFrom);
            dateChooserTo.setDate(date);
            dateChooserTo.setBounds(10, 5, 150, 30);
            dateChooserTo.setFont(dateFont);
            panToDateTime.add(dateChooserTo);
            SpinnerDateModel timeModel = new SpinnerDateModel();
            spinStartTime.setModel(timeModel);
            JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinStartTime, "HH:mm");
            spinStartTime.setEditor(timeEditor);
            spinStartTime.setValue(new Date());
            spinStartTime.setEnabled(true);
            spinStartTime.setFont(dateFont);
            spinStartTime.setBounds(170, 5, 80, 30);
            panFromDateTime.add(spinStartTime);
            SpinnerDateModel timeModelTo = new SpinnerDateModel();
            spinEndTime.setModel(timeModelTo);
            JSpinner.DateEditor timeEditorTo = new JSpinner.DateEditor(spinEndTime, "HH:mm");
            spinEndTime.setEditor(timeEditorTo);
            spinEndTime.setValue(new Date());
            spinEndTime.setEnabled(true);
            spinEndTime.setFont(dateFont);
            spinEndTime.setBounds(170, 5, 80, 30);
            panToDateTime.add(spinEndTime);
            ScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
            calendar = null;
            date = null;
        } catch (Exception ex) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnUSB = new javax.swing.JButton();
        btnWifi = new javax.swing.JButton();
        lblConfigName = new javax.swing.JTextField();
        chkWifi = new javax.swing.JCheckBox();
        panCopyTo = new javax.swing.JPanel();
        cmbSelectFiles = new javax.swing.JComboBox<>();
        btnListFiles = new javax.swing.JButton();
        ScrollPane = new javax.swing.JScrollPane();
        panListFiles = new javax.swing.JPanel();
        AllSelect = new javax.swing.JCheckBox();
        panCamDate = new javax.swing.JPanel();
        lblFromDate = new javax.swing.JLabel();
        lblToDate = new javax.swing.JLabel();
        panFromDateTime = new javax.swing.JPanel();
        panToDateTime = new javax.swing.JPanel();
        cmbSubSelectFiles = new javax.swing.JComboBox<>();
        progBar = new javax.swing.JProgressBar();
        btnCopy = new javax.swing.JButton();
        lblFileProgress = new javax.swing.JLabel();
        panRadBtn = new javax.swing.JPanel();
        btnInstallApp = new javax.swing.JButton();
        btnCopyFilesFromUsb = new javax.swing.JButton();
        btnCopyFilesToUsb = new javax.swing.JButton();
        btnLibraries = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        lblwifi = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(700, 430));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        btnUSB.setBackground(new java.awt.Color(47, 49, 51));
        btnUSB.setFont(btnUSB.getFont().deriveFont(btnUSB.getFont().getStyle() | java.awt.Font.BOLD, btnUSB.getFont().getSize()+13));
        btnUSB.setForeground(new java.awt.Color(255, 255, 255));
        btnUSB.setText("USB");
        btnUSB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUSB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUSBActionPerformed(evt);
            }
        });

        btnWifi.setBackground(new java.awt.Color(47, 49, 51));
        btnWifi.setFont(btnWifi.getFont().deriveFont(btnWifi.getFont().getStyle() | java.awt.Font.BOLD, btnWifi.getFont().getSize()+13));
        btnWifi.setForeground(new java.awt.Color(255, 255, 255));
        btnWifi.setText("WIFI");
        btnWifi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnWifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWifiActionPerformed(evt);
            }
        });

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("File Transfer");

        chkWifi.setFont(chkWifi.getFont().deriveFont(chkWifi.getFont().getStyle() | java.awt.Font.BOLD, chkWifi.getFont().getSize()+3));
        chkWifi.setText("WIFI");
        chkWifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkWifiActionPerformed(evt);
            }
        });

        panCopyTo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cmbSelectFiles.setFont(cmbSelectFiles.getFont().deriveFont(cmbSelectFiles.getFont().getStyle() | java.awt.Font.BOLD, cmbSelectFiles.getFont().getSize()+5));
        cmbSelectFiles.setForeground(new java.awt.Color(0, 51, 102));
        cmbSelectFiles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cam1", "Cam2", "Cam3", "Cam4", "Cam5", "Cam6", "Cam7", "Cam8", "Log Files", "Stored Data Log", "CAN" }));
        cmbSelectFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectFilesActionPerformed(evt);
            }
        });

        btnListFiles.setBackground(new java.awt.Color(47, 49, 51));
        btnListFiles.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btnListFiles.setForeground(new java.awt.Color(255, 255, 255));
        btnListFiles.setText("List of Files");
        btnListFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListFilesActionPerformed(evt);
            }
        });

        ScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panListFiles.setPreferredSize(new java.awt.Dimension(500, 2000));

        javax.swing.GroupLayout panListFilesLayout = new javax.swing.GroupLayout(panListFiles);
        panListFiles.setLayout(panListFilesLayout);
        panListFilesLayout.setHorizontalGroup(
            panListFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        panListFilesLayout.setVerticalGroup(
            panListFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
        );

        ScrollPane.setViewportView(panListFiles);

        AllSelect.setFont(AllSelect.getFont().deriveFont(AllSelect.getFont().getStyle() | java.awt.Font.BOLD, AllSelect.getFont().getSize()+3));
        AllSelect.setText("select all");
        AllSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllSelectActionPerformed(evt);
            }
        });

        panCamDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblFromDate.setFont(lblFromDate.getFont().deriveFont(lblFromDate.getFont().getStyle() | java.awt.Font.BOLD, lblFromDate.getFont().getSize()+5));
        lblFromDate.setForeground(new java.awt.Color(0, 0, 102));
        lblFromDate.setText("From  Date ");

        lblToDate.setFont(lblToDate.getFont().deriveFont(lblToDate.getFont().getStyle() | java.awt.Font.BOLD, lblToDate.getFont().getSize()+5));
        lblToDate.setForeground(new java.awt.Color(0, 0, 102));
        lblToDate.setText("To Date");

        panFromDateTime.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panFromDateTime.setFont(panFromDateTime.getFont().deriveFont(panFromDateTime.getFont().getSize()+4f));

        javax.swing.GroupLayout panFromDateTimeLayout = new javax.swing.GroupLayout(panFromDateTime);
        panFromDateTime.setLayout(panFromDateTimeLayout);
        panFromDateTimeLayout.setHorizontalGroup(
            panFromDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );
        panFromDateTimeLayout.setVerticalGroup(
            panFromDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panCamDateLayout = new javax.swing.GroupLayout(panCamDate);
        panCamDate.setLayout(panCamDateLayout);
        panCamDateLayout.setHorizontalGroup(
            panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCamDateLayout.createSequentialGroup()
                .addComponent(panFromDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panToDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panCamDateLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lblFromDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(lblToDate)
                .addGap(130, 130, 130))
        );
        panCamDateLayout.setVerticalGroup(
            panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCamDateLayout.createSequentialGroup()
                .addGroup(panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFromDate)
                    .addComponent(lblToDate))
                .addGap(2, 2, 2)
                .addGroup(panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panFromDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panToDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panCamDateLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {panFromDateTime, panToDateTime});

        cmbSubSelectFiles.setFont(cmbSubSelectFiles.getFont().deriveFont(cmbSubSelectFiles.getFont().getStyle() | java.awt.Font.BOLD, cmbSubSelectFiles.getFont().getSize()+5));
        cmbSubSelectFiles.setForeground(new java.awt.Color(0, 51, 102));
        cmbSubSelectFiles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal Video", "Event Video", "CAN Video", "Motion Detection", "Snapshot Cont", "Snapshot Dig1", "Snapshot Dig2", "Snapshot Dig3", "Snapshot Dig4" }));
        cmbSubSelectFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSubSelectFilesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panCopyToLayout = new javax.swing.GroupLayout(panCopyTo);
        panCopyTo.setLayout(panCopyToLayout);
        panCopyToLayout.setHorizontalGroup(
            panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCopyToLayout.createSequentialGroup()
                .addGroup(panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panCopyToLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnListFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSubSelectFiles, 0, 0, Short.MAX_VALUE)
                            .addComponent(cmbSelectFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(AllSelect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panCamDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panCopyToLayout.createSequentialGroup()
                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panCopyToLayout.setVerticalGroup(
            panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCopyToLayout.createSequentialGroup()
                .addGroup(panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panCamDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panCopyToLayout.createSequentialGroup()
                        .addComponent(cmbSelectFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSubSelectFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panCopyToLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panCopyToLayout.createSequentialGroup()
                        .addComponent(btnListFiles)
                        .addGap(6, 6, 6)
                        .addComponent(AllSelect))
                    .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnCopy.setBackground(new java.awt.Color(47, 49, 51));
        btnCopy.setFont(btnCopy.getFont().deriveFont(btnCopy.getFont().getStyle() | java.awt.Font.BOLD, btnCopy.getFont().getSize()+7));
        btnCopy.setForeground(new java.awt.Color(255, 255, 255));
        btnCopy.setText("COPY");
        btnCopy.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyActionPerformed(evt);
            }
        });

        lblFileProgress.setFont(lblFileProgress.getFont().deriveFont(lblFileProgress.getFont().getStyle() | java.awt.Font.BOLD, lblFileProgress.getFont().getSize()+2));
        lblFileProgress.setText("Progress");

        btnInstallApp.setBackground(new java.awt.Color(0, 0, 102));
        btnInstallApp.setFont(btnInstallApp.getFont().deriveFont(btnInstallApp.getFont().getStyle() | java.awt.Font.BOLD, btnInstallApp.getFont().getSize()+5));
        btnInstallApp.setForeground(new java.awt.Color(255, 255, 255));
        btnInstallApp.setText("Install Application");
        btnInstallApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstallAppActionPerformed(evt);
            }
        });

        btnCopyFilesFromUsb.setBackground(new java.awt.Color(0, 0, 102));
        btnCopyFilesFromUsb.setFont(btnCopyFilesFromUsb.getFont().deriveFont(btnCopyFilesFromUsb.getFont().getStyle() | java.awt.Font.BOLD, btnCopyFilesFromUsb.getFont().getSize()+5));
        btnCopyFilesFromUsb.setForeground(new java.awt.Color(255, 255, 255));
        btnCopyFilesFromUsb.setText("Download");
        btnCopyFilesFromUsb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyFilesFromUsbActionPerformed(evt);
            }
        });

        btnCopyFilesToUsb.setBackground(new java.awt.Color(0, 0, 102));
        btnCopyFilesToUsb.setFont(btnCopyFilesToUsb.getFont().deriveFont(btnCopyFilesToUsb.getFont().getStyle() | java.awt.Font.BOLD, btnCopyFilesToUsb.getFont().getSize()+5));
        btnCopyFilesToUsb.setForeground(new java.awt.Color(255, 255, 255));
        btnCopyFilesToUsb.setText("Upload");
        btnCopyFilesToUsb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopyFilesToUsbActionPerformed(evt);
            }
        });

        btnLibraries.setBackground(new java.awt.Color(0, 0, 102));
        btnLibraries.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLibraries.setForeground(new java.awt.Color(255, 255, 255));
        btnLibraries.setText("Libraries");
        btnLibraries.setPreferredSize(new java.awt.Dimension(50, 30));
        btnLibraries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLibrariesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panRadBtnLayout = new javax.swing.GroupLayout(panRadBtn);
        panRadBtn.setLayout(panRadBtnLayout);
        panRadBtnLayout.setHorizontalGroup(
            panRadBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRadBtnLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(btnInstallApp, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLibraries, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCopyFilesFromUsb, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCopyFilesToUsb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        panRadBtnLayout.setVerticalGroup(
            panRadBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRadBtnLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panRadBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCopyFilesFromUsb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCopyFilesToUsb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLibraries, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnInstallApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panRadBtnLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCopyFilesFromUsb, btnCopyFilesToUsb});

        progress.setMaximum(10);

        lblwifi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUSB, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnWifi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkWifi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(lblwifi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panRadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progBar, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(lblFileProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panCopyTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUSB)
                            .addComponent(btnWifi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkWifi)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblwifi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panRadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panCopyTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFileProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progBar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            // process = rt.exec(cmd);
            process = rt.exec(new String[]{"bash", "-c", cmd});
            process.waitFor(10, TimeUnit.SECONDS);

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

    private boolean runCmdWifi(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            // process = rt.exec(cmd);
            process = rt.exec(new String[]{"bash", "-c", cmd});
            process.waitFor(60, TimeUnit.SECONDS);

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
    private void btnUSBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUSBActionPerformed
        // TODO add your handling code here:
        int i = 0;
        String path = null;
        btn_selected = BTN_USB;
        chkWifi.setVisible(false);
        this.btnUSB.setBackground(Color.GREEN);
        this.btnWifi.setBackground(bgcolorusbwifi);
        progress.setVisible(false);
        btnInstallApp.setBackground(bgcolor);
        this.btnCopyFilesFromUsb.setBackground(bgcolor);
        btnCopyFilesToUsb.setBackground(bgcolor);
        this.panCopyTo.setVisible(false);
        this.panRadBtn.setVisible(true);
        this.btnCopyFilesFromUsb.setText("Download");
        this.btnCopyFilesToUsb.setText("Upload");
        panCopyTo.setVisible(false);
        btnCopy.setVisible(false);
        lblFileProgress.setText("");
        UserPrincipal p = null;
        File[] out_path = usb_filepath.listFiles();
        Arrays.sort(out_path);
        if (out_path == null) {
            return;
        }
        if (out_path.length == 0) {
            path = usb_filepath.getAbsolutePath();
        } else if (out_path.length == 2) {
            for (i = 0; i < out_path.length; i++) {
                if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                    path = out_path[i].getAbsolutePath();

                    break;
                } else {
                    path = out_path[i].getAbsolutePath();

                }
            }
        } else {
            for (i = 0; i < out_path.length; i++) {
                if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                    try {
                        p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);

                        if (p.toString().equals("root")) {
                            runCmd("sudo rm -rf " + out_path[i]);
                        } else {
                            path = out_path[i].getAbsolutePath();
                        }

                    } catch (IOException ex) {
                    } catch (Exception ex) {
                    }
                }
            }
        }

        p = null;
        if (path == null) {
            return;
        }

    }//GEN-LAST:event_btnUSBActionPerformed
    private void downloadRouteFilesFromWifi() {

        runCmd("sudo chmod 777 -R " + clsDefines.config_filepath);
        runCmd("sudo chmod 777 -R " + clsDefines.media_filepath);

        runCmd("sudo chmod 777 -R " + clsDefines.route_filepath);
        runCmd("sudo chmod 777 -R " + clsDefines.main_route_filepath);

        lblFileProgress.setText("");
        progBar.setVisible(true);
        progBar.setValue(0);
        startDownloadAllFilesWifiThread("");
    }

    private void downloadLibraryFilesFromWifi() {

        runCmd("sudo chmod 777 -R " + clsDefines.obu_library_filepath);
        lblFileProgress.setText("");
        progBar.setVisible(true);
        progBar.setValue(0);
        startDownloadLibraryFilesWifiThread();
    }

    private void install_application_using_usb() {
        // TODO add your handling code here:
        int i = 0;
        String path = null;
        btn_selected = BTN_USB;
        chkWifi.setVisible(false);
        this.panCopyTo.setVisible(false);
        this.panRadBtn.setVisible(true);
        File[] files;
        UserPrincipal p;
        File f = null;
        File[] out_path = usb_filepath.listFiles();
        if (out_path == null) {
            lblFileProgress.setText("Check Pendrive ");
            return;
        }
        Arrays.sort(out_path);
        switch (out_path.length) {
            case 0:
                path = usb_filepath.getAbsolutePath();
                break;
            case 2:
                for (i = 0; i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                        path = out_path[i].getAbsolutePath();

                        break;
                    } else {
                        path = out_path[i].getAbsolutePath();

                    }
                }
                break;
            default:
                for (i = 0; i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                        try {
                            p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);

                            if (p.toString().equals("root")) {
                                runCmd("sudo rm -rf " + out_path[i]);
                            } else {
                                path = out_path[i].getAbsolutePath();
                            }

                        } catch (IOException ex) {
                        } catch (Exception ex) {
                        }
                    }
                }
                break;
        }

        p = null;
        if (path == null) {
            lblFileProgress.setText("Please Insert Pendrive");
            return;
        }
        f = new File(path);
        files = f.listFiles();

        boolean found = false;
        lblFileProgress.setText("");

        progBar.setValue(0);
        lblFileProgress.setText("");

        //Install Application
        if (!f.exists() || files == null) {
            lblFileProgress.setText("Please Insert Pendrive");

            return;
        }
        for (i = 0; i < files.length; i++) {
            if (files[i].getName().equals("ObuIts.jar")) {
                found = true;
                break;
            }
        }

        if (found == true) {
            runCmd("sudo chmod 777 -R " + obu_filepath);

            f = new File(obu_filepath.getPath() + "/ObuIts1.jar");
            if (f.exists()) {
                f.delete();
            }
            f = new File(obu_filepath.getPath() + "/ObuIts2.jar");
            if (f.exists()) {
                f.delete();
            }
            if (copyFile(files[i].getPath(), obu_filepath.getPath() + "/ObuIts1.jar") == true) {
                // if (clsDefines.DEBUG_ENABLE) {
                //  if(obu_filepath.getPath().length()==)
                //}
                //rename actual file to another file

                if (files[i].length() == new File(obu_filepath.getPath() + "/ObuIts1.jar").length()) {
                    f = new File(obu_filepath.getPath() + "/ObuIts.jar");
                    File oldFile = new File(obu_filepath.getPath() + "/ObuIts2.jar");
                    f.renameTo(oldFile);

                    //copied new file to actual file
                    f = new File(obu_filepath.getPath() + "/ObuIts1.jar");

                    if (f.length() == files[i].length()) {
                        File newFile = new File(obu_filepath.getPath() + "/ObuIts.jar");
                        f.renameTo(newFile);
                        lblFileProgress.setText("File Copied, Restarting the OBU");

                        runCmd("sudo sync");
                        reboot_system(" App Install");
                    } else {
                        lblFileProgress.setText("Unable to install the application so Please format the Pendrive");

                    }
                } else {
                    lblFileProgress.setText("Unable to install the application so Please format the Pendrive");

                }
                // restartApplication();
            } else {
                lblFileProgress.setText("File Not Copied " + files[i].getPath() + "  " + obu_filepath.getPath());

            }
        } else {
            lblFileProgress.setText("Application File Not Found");

        }
    }

    private static void driver_alarm() {
        try {
            final String data = "completed";
            SwingWorker sw1 = new SwingWorker() {
                @Override
                protected String doInBackground() throws Exception {
                    try {
                        write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);
                        String str = "AT+QTTS=2,\"" + data + "\" \r\n";
                        atSerialWrite(str);
                        Thread.sleep(3000);
                        write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
                    } catch (InterruptedException ex) {
                    } catch (Exception ex) {
                    }
                    return null;
                }

                @Override
                protected void process(List chunks) {
                    String data1 = (String) chunks.get(0);
                }
            };
            sw1.execute();
        } catch (Exception ex) {

        }
    }

    private class AsyncTaskRunnerGprsSingle extends Thread {

        String filename;

        AsyncTaskRunnerGprsSingle(String file_name) {
            filename = file_name;
        }

        @Override
        public void run() {
            try {
                String path;
                if (filename.contains("ObuIts.jar")) {
                    final File file1 = new File(obu_filepath + "/" + "ObuIts1.jar");
                    startDownloadSingleFileWifiThread("ObuIts.jar", file1);
                }
            } catch (Exception e) {

            }
        }

        //for sftp
        private void startDownloadSingleFileWifiThread(String filename, File localFile) {
            SwingWorker<Void, String> sw1 = new SwingWorker<Void, String>() {
                @Override
                protected Void doInBackground() throws Exception {
                    JSch jsch = new JSch();
                    Session session = null;
                    ChannelSftp channelSftp = null;
                    try {
                        publish("Connecting to SFTP Server");
                        session = jsch.getSession(ftpusername, ftp_ipaddress, ftp_port_no);
                        session.setPassword(ftpPassword);
                        //System.out.println("porttttt" + clsSharedVariables.getFtpPortNo());
                        session.setConfig("StrictHostKeyChecking", "no");
                        session.connect();
                        channelSftp = (ChannelSftp) session.openChannel("sftp");
                        channelSftp.connect();
                        publish("SFTP Connected");
                        // Download file
                        publish("Downloading file: " + filename);
                        channelSftp.get(filename, localFile.getAbsolutePath());
                        publish("Downloaded Successfully");
                        // Additional processing for "ObuIts" file
                        if (filename.contains("ObuIts")) {
                            File f = new File(obu_filepath.getPath() + "/ObuIts.jar");
                            File oldFile = new File(obu_filepath.getPath() + "/ObuIts2.jar");
                            f.renameTo(oldFile);

                            // Copy new file to actual file
                            f = new File(obu_filepath.getPath() + "/ObuIts1.jar");
                            File newFile = new File(obu_filepath.getPath() + "/ObuIts.jar");
                            if (f.renameTo(newFile)) {
                                publish("Rebooting System...");
                                reboot_system("App Wifi Download");
                            } else {
                                publish("Failed to rename file.");
                            }
                        }

                    } catch (Exception ex) {
                        publish("Download Failure: " + ex.getMessage());
                    } finally {
                        if (channelSftp != null) {
                            channelSftp.disconnect();
                        }
                        if (session != null) {
                            session.disconnect();
                        }
                    }
                    return null;
                }

                @Override
                protected void process(List<String> chunks) {
                    for (String data : chunks) {
                        lblFileProgress.setText(data);
                    }
                }

                @Override
                protected void done() {
                    lblFileProgress.setText("Completed Process");
                    progBar.setVisible(false);
                }
            };
            sw1.execute();
        }

    }

    private void startDownloadLibraryFilesWifiThread() {
        SwingWorker<Void, String> sw1 = new SwingWorker<Void, String>() {
            Session session = null;
            ChannelSftp channelSftp = null;
            File file;
            String resultMessage;

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    // Establishing SFTP connection
                    JSch jsch = new JSch();
                    session = jsch.getSession(ftpusername, ftp_ipaddress, ftp_port_no);
                    session.setPassword(ftpPassword);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();
                    // Opening SFTP channel
                    channelSftp = (ChannelSftp) session.openChannel("sftp");
                    channelSftp.connect();
                    publish("SFTP Login Success");
                    // Specifying file path
                    String file_path = "/home/sumith";
                    // Get current date to create a new folder
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = sdf.format(new Date());
                    file_path = file_path + "/" + currentDate;  
                    String vehicleId = clsSharedVariables.getVechicleRegNo();
                    file_path = file_path + "/" + vehicleId;
                    String fileCopyListFile = file_path + "/FILELIB.LST";

                    // Downloading FILELIB.LST
                    if (clsSharedVariables.getHardDriveDetected()) {
                        file = new File(route_filepath, "FILELIB.LST");
                    } else {
                        file = new File(main_route_filepath, "FILELIB.LST");
                    }
                    channelSftp.get(fileCopyListFile, file.getAbsolutePath());
                    publish("FILELIB.LST Download Success");

                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        int files_cnt = 0;
                        while ((line = br.readLine()) != null && files_cnt < 1000) {
                            String remoteFilePath = file_path + "/" + line;
                            // Create a new file path in the date-specific folder
                            File localFile = new File(file_path, line);
                            try (InputStream inputStream = channelSftp.get(remoteFilePath); OutputStream outputStream = new FileOutputStream(localFile)) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = inputStream.read(buffer)) != -1) {
                                    outputStream.write(buffer, 0, bytesRead);
                                }
                                SftpATTRS attrs = channelSftp.lstat(remoteFilePath);

                                if (attrs != null && attrs.getSize() == localFile.length()) {
                                    resultMessage = line + " Download Success";
                                } else {
                                    resultMessage = line + " Download Failure";
                                }

                                publish(resultMessage);
                            }
                            files_cnt++;
                        }
                    }
                    runCmd("sudo chmod 777  -R " + file_path);
                    publish("All Files Downloaded");
                } catch (Exception ex) {
                    publish("Download Failure: " + ex.getMessage());
                }
                return null;
            }
            @Override
            protected void process(List<String> chunks) {
                String latestMessage = chunks.get(chunks.size() - 1); // Only update with the latest message
                lblFileProgress.setText(latestMessage);
            }

            @Override
            protected void done() {
                lblFileProgress.setText("Completed Process");
                progBar.setVisible(false);
                progBar.setValue(0);

                // Graceful disconnect
                if (channelSftp != null) {
                    channelSftp.disconnect();
                }
                if (session != null) {
                    session.disconnect();
                }
            }
        };
        sw1.execute();
    }

    private void startThread(File cam_path) {
        SwingWorker<String, String> sw1 = new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() throws Exception {
                int i;
                String str;
                File file = null;
                Iterator<JCheckBox> list;
                JCheckBox file1;
                long size_file;
                final File[] files = cam_path.listFiles();
                if (files == null) {
                    return "";
                }
                String[] final_files_to_upload = new String[files.length];
                int final_files_to_upload_cnt = 0;
                list = arrayListChkCamFiles.iterator();

                while (list.hasNext()) {
                    file1 = list.next();
                    if (file1.isSelected()) {
                        if (file1.getText().startsWith("e")) {
                            final File path = new File(extra_video_filepath + "/" + file1.getText());
                            if (path.exists()) {
                                final_files_to_upload[final_files_to_upload_cnt++] = path.getAbsolutePath();
                            }
                        } else {
                            try {
                                final File path = new File(cam_path + "/" + file1.getText());
                                final_files_to_upload[final_files_to_upload_cnt++] = path.getAbsolutePath();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                if (final_files_to_upload_cnt == 0) {
                    publish("No Files to Upload...");
                } else {
                    Session session = null;
                    ChannelSftp sftpChannel = null;
                    FileInputStream inStream = null;
                    try {
                        publish("Uploading File...");
                        try {
                            JSch jsch = new JSch();
                            session = jsch.getSession(ftpusername, ftp_ipaddress.split("/")[0], ftp_port_no);
                        } catch (Exception ex) {
                        }
                        session.setPassword(ftpPassword);
                        session.setConfig("StrictHostKeyChecking", "no");
                        session.connect();
                        sftpChannel = (ChannelSftp) session.openChannel("sftp");
                        sftpChannel.connect();
                        String sftpRemoteDirectory = "/home/sumith"; // Set your remote directory here
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = sdf.format(new Date());
                        String dateFolderPath = sftpRemoteDirectory + "/" + currentDate;

                        // Create the date folder on the SFTP server
                        //sftpChannel.mkdir(dateFolderPath);
                        //   sftpChannel.cd(dateFolderPath);
                        if (!dateFolderPath.equals("")) {
                            try {
                                sftpChannel.cd(dateFolderPath);
                            } catch (SftpException e) {
                                try {
                                    sftpChannel.mkdir(dateFolderPath);
                                    sftpChannel.cd(dateFolderPath);
                                } catch (SftpException ex) {
                                    sftpChannel.cd("..");
                                }
                            }
                        }
                        runCmd("sudo chmod 777  -R " + dateFolderPath);
                        String vehicleId = clsSharedVariables.getVechicleRegNo();
                        String vehicleFolderPath = dateFolderPath + "/" + vehicleId;
                        if (!vehicleFolderPath.equals("")) {
                            try {
                                sftpChannel.cd(vehicleFolderPath);
                            } catch (SftpException e) {
                                try {
                                    sftpChannel.mkdir(vehicleFolderPath);
                                    sftpChannel.cd(vehicleFolderPath);
                                } catch (SftpException ex) {
                                    sftpChannel.cd("..");
                                }
                            }
                        }

                        runCmd("sudo chmod 777  -R " + vehicleFolderPath);
                        for (i = 0; i < final_files_to_upload_cnt; i++) {
                            str = final_files_to_upload[i];
                            try {
                                file = new File(str);
                                inStream = new FileInputStream(file);
                                size_file = java.nio.file.Files.size(file.toPath()) / 100;
                                publish("SFTP storing file " + file.getName());
                                sftpChannel.put(inStream, file.getName());
                                publish(file.getName() + " Uploaded Successfully");
                            } catch (Exception ex) {
                                publish(file.getName() + " Upload Failed");
                            }
                        }
                        driver_alarm();
                    } catch (Exception ex) {
                        publish("File Upload Exception " + ex.getMessage());
                    } finally {
                        // Close the input stream if it's open
                        if (inStream != null) {
                            try {
                                inStream.close();
                            } catch (IOException e) {
                                // Handle IOException
                                e.printStackTrace();
                            }
                        }
                        // Disconnect from SFTP
                        if (sftpChannel != null) {
                            sftpChannel.disconnect();
                        }
                        if (session != null) {
                            session.disconnect();
                        }
                    }
                }
                return "Over";
            }
            int files_cnt = 0;

            @Override
            protected void process(List<String> chunks) {
                String data;
                try {
                    for (String chunk : chunks) {
                        data = chunk;
                        progBar.setValue(files_cnt);

                        if (data.equals("Over")) {
                            progBar.setVisible(false);
                            break;
                        } else if (data.equals("Disconnected")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error, USB Disconnected");
                            break;
                        } else {
                            lblFileProgress.setText(data);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void done() {
                try {
                    progBar.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void startUploadOtherThread(File cam_path) {
        SwingWorker<Void, String> sw1 = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    publish("Trying to coonect to ftp");
                    JSch jsch = new JSch();
                    Session session = jsch.getSession(ftpusername, ftp_ipaddress.split("/")[0], ftp_port_no);
                    session.setPassword(ftpPassword);

                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();

                    ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
                    sftpChannel.connect();

                    // Assigning the file path as "/home/sumith"
                    String file_path = "/home/sumith";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = sdf.format(new Date());
                    file_path = file_path + "/" + currentDate;
                    if (!file_path.equals("")) {
                        try {
                            sftpChannel.cd(file_path);
                        } catch (SftpException e) {
                            try {
                                sftpChannel.mkdir(file_path);
                                sftpChannel.cd(file_path);
                            } catch (SftpException ex) {
                                sftpChannel.cd("..");
                            }
                        }
                    }
                    runCmd("sudo chmod 777  -R " + file_path);

                    String vehicleId = clsSharedVariables.getVechicleRegNo();
                    file_path = file_path + "/" + vehicleId;
                    if (!file_path.equals("")) {
                        try {
                            sftpChannel.cd(file_path);
                        } catch (SftpException e) {
                            try {
                                sftpChannel.mkdir(file_path);
                                sftpChannel.cd(file_path);
                            } catch (SftpException ex) {
                                sftpChannel.cd("..");
                            }
                        }
                    }

                    runCmd("sudo chmod 777  -R " + file_path);

                    final File[] files = cam_path.listFiles();
                    if (files == null) {
                        return null;
                    }

                    for (File file : files) {
                        if (file.isFile()) {
                            publish("Uploading file: " + file.getName());
                            sftpChannel.put(new FileInputStream(file), file.getName());
                            publish(file.getName() + " uploaded successfully");
                        }
                    }
                    sftpChannel.disconnect();
                    session.disconnect();
                } catch (Exception ex) {
                    publish("File Upload Exception: " + ex.getMessage());
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String chunk : chunks) {
                    lblFileProgress.setText(chunk);
                }
            }

            @Override
            protected void done() {
                lblFileProgress.setText("Processing Completed");
                progBar.setVisible(false);
                progBar.setValue(0);
            }
        };
        sw1.execute();
    }

    private void startDownloadAllFilesWifiThread(String folder_name) {
        SwingWorker<String, String> sw1 = new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() {
                Session session = null;
                ChannelSftp channelSftp = null;
                boolean route_files_found = false;
                try {
                    JSch jsch = new JSch();
                    session = jsch.getSession(ftpusername, ftp_ipaddress.split("/")[0], ftp_port_no);
                    session.setPassword(ftpPassword);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();
                    Channel channel = session.openChannel("sftp");
                    channel.connect();
                    channelSftp = (ChannelSftp) channel;
                    String[] split_str = ftp_ipaddress.split("/");
                    StringBuilder file_path = new StringBuilder();
                    for (int i = 1; i < split_str.length; i++) {
                        file_path.append("/").append(split_str[i]);
                    }
                    if (!folder_name.isEmpty()) {
                        file_path.append("/").append(folder_name);
                    }
                    // Change to the correct directory if needed
                    String file1_path = "/home/sumith";

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = sdf.format(new Date());
                     file1_path = file1_path + "/" + currentDate;
                    String vehicleId = clsSharedVariables.getVechicleRegNo();
                     file1_path = file1_path + "/" + vehicleId;
                     System.out.println("download all wifi thread files "+file1_path);
                    file_path = new StringBuilder(file1_path);
                    Vector<ChannelSftp.LsEntry> files = channelSftp.ls(file_path.toString());
                    int size_dir = files.size();
                    if (size_dir > 3) {
                        channelSftp.getSession().setTimeout(60000 * size_dir);
                    }
                    String[] files_list = new String[size_dir];
                    String fileCopyListFile;
                    if (file_path.toString().isEmpty()) {
                        fileCopyListFile = "FILECOPY.LST";
                    } else {
                        fileCopyListFile = file_path + "/" + "FILECOPY.LST";
                    }
                    SftpATTRS attrs = channelSftp.lstat(fileCopyListFile);
                    long size_file = attrs.getSize();
                    channelSftp.get(fileCopyListFile, "FILECOPY.LST");
                    if (new File("FILECOPY.LST").length() == size_file) {
                        try (BufferedReader br = new BufferedReader(new FileReader("FILECOPY.LST"))) {
                            String line;
                            int files_cnt = 0;
                            while ((line = br.readLine()) != null && files_cnt < 1000) {
                                for (ChannelSftp.LsEntry file : files) {
                                    if (file.getFilename().equals(line)) {
                                        files_list[files_cnt++] = line;
                                    }
                                }
                            }
                            for (int i = 0; i < files_cnt; i++) {
                                try {
                                    File file = clsSharedVariables.getHardDriveDetected()
                                            ? new File(route_filepath, files_list[i])
                                            : new File(main_route_filepath, files_list[i]);

                                    line = files_list[i];
                                    if (!file_path.toString().trim().isEmpty()) {
                                        files_list[i] = file_path + "/" + files_list[i];
                                    }
                                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                                        channelSftp.get(files_list[i], outputStream);
                                        outputStream.flush();
                                    }
                                    size_file = file.length();
                                    progBar.setValue(i);
                                    if (size_file == file.length()) {
                                        publish(files_list[i] + " Download Success");

                                        runCmd("sudo chmod 777 " + files_list[i]);
                                        if (line.contains("ConfigParams.txt") || line.contains("CameraConfig.txt") || line.contains("CameraConfigArray.txt")) {
                                            if (copyFile(file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line)) {
                                                publish(line + " File Copied");
                                            }
                                            if (clsSharedVariables.getHardDriveDetected()) {
                                                if (copyFile(file.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + line)) {
                                                    publish(line + " File Copied");
                                                }
                                            }
                                        } else if (line.contains("canElectrical.txt") || line.contains("canSafety.txt") || line.contains("canTransmit.txt")
                                                || line.contains("canEngine.txt") || line.contains("canOthers.txt") || line.contains("canElectrical1.txt")
                                                || line.contains("canSafety1.txt") || line.contains("canTransmit1.txt") || line.contains("canEngine1.txt")
                                                || line.contains("canOthers1.txt") || line.contains("phonenum.txt") || line.contains("names.txt")
                                                || line.contains("PreDefLoc.txt") || line.contains("TSPPoint.txt") || line.contains("codedmsg.txt")
                                                || line.contains("speclmsg.txt") || line.contains("drivrlst.txt") || line.contains("fileChangePwd.txt")
                                                || line.contains("driver_details.txt") || line.contains("ConductorIds.txt")) {

                                            if (clsSharedVariables.getHardDriveDetected()) {
                                                if (copyFile(file.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + line)) {
                                                    publish(line + " File Copied");
                                                }
                                            }

                                            if (copyFile(file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line)) {
                                                publish(line + " File Copied");
                                            }
                                        } else {
                                            route_files_found = true;
                                        }
                                    } else {
                                        publish(files_list[i] + " Download Failure");
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            publish("All Files Downloaded");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            clsReadFiles objReadFiles = new clsReadFiles();
                            clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
                            objReadFiles.read_special_msg_info();
                            objReadFiles.read_route_master_file();
                            objReadFiles.read_cfg_data_file();
                            objReadFiles.read_camera_cfg_file();
                            objReadFiles.read_phone_no_details();
                            objReadFiles.read_password_change();
                            objReadFiles.read_traffic_points();
                            objReadFiles.read_pre_def_location_points();
                            objReadFiles.read_panic_messages();
                            objReadFiles.read_special_msg_info();
                            objReadFiles.read_reset_data();
                            objReadFiles.read_driver_names();
                            objReadFiles.read_conductor_names();
                            objReadFiles.read_label_names_details();
                            objReadFiles.read_canElectricals_file();
                            objReadFiles.read_canSafety_file();
                            objReadFiles.read_canEngine_file();
                            objReadFiles.read_canTransmit_file();
                            objReadFiles.read_canOthers_file();
                            objReadDataFiles.read_canElectricals_file1();
                            objReadDataFiles.read_canSafety_file1();
                            objReadDataFiles.read_canEngine_file1();
                            objReadDataFiles.read_canTransmit_file1();
                            objReadDataFiles.read_canOthers_file1();

                            if (route_files_found) {
                                if (clsSharedVariables.getCurTripStat() != TRIP_START) {
                                    setSchRouteEnable(ROUTE_ENABLE);
                                    objReadFiles.read_route_master_file();
                                    setCurSchNoTrips(getNoRoutes());
                                    setSchId("1");
                                    for (int j = 0; j < getCurSchNoTrips(); j++) {
                                        setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                                        setCurSchTripStatus(j, TRIP_UNKNOWN);
                                    }
                                    final Calendar cal = Calendar.getInstance();
                                    setCurSchStartDate(cal.getTime());
                                    setCurSchStartTime(cal.getTime().getTime());
                                    cal.add(Calendar.DATE, 1);
                                    setCurSchEndDate(cal.getTime());
                                    setCurSchEndTime(cal.getTime().getTime());
                                    setCurTripNo((byte) 0);
                                    setCurTripStat(TRIP_UNKNOWN);
                                    setCurRouteStat(false);
                                    objReadFiles.write_cur_route_info_file();
                                    objReadFiles.write_cur_trip_info_file();
                                    objReadFiles.write_cur_schedule_file();
                                    final ClsSchedule objSch = new ClsSchedule();
                                    if (getNoRoutes() > 0) {
                                        objSch.send_route_info();
                                    }
                                }
                            }
                        }
                    } else {
                        publish("Download Failure");
                        return "Failure";
                    }
                    return "Over";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return "Failure";
                } finally {
                    if (channelSftp != null) {
                        channelSftp.exit();
                    }
                    if (session != null) {
                        session.disconnect();
                    }
                }
            }

            @Override
            protected void process(List<String> chunks) {
                for (String data : chunks) {
                    lblFileProgress.setText(data);
                    if (data.equals("Over") || data.equals("Disconnected") || data.equals("Failure")) {
                        break;
                    }
                }
            }

            @Override
            protected void done() {
                try {
                    progBar.setVisible(false);
                    progBar.setValue(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private boolean copyZipFile(File sourceZip, File destinationFolder) {
        if (!sourceZip.exists()) {
            return false; // Source ZIP file does not exist
        }

        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs(); // Create destination folder if it doesn't exist
        }

        File destFile = new File(destinationFolder, sourceZip.getName());

        try {
            Files.copy(sourceZip.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception (e.g., log it)
            return false;
        }

        return true;
    }

    private boolean copyFile(String inputPath, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        byte[] buffer = new byte[1024];
        int read;
        File tempDir;
        try {
            File outDir = new File(outputPath);
            File inDir = new File(inputPath);

            if (inDir.isFile()) {

                try {
                    in = new FileInputStream(inDir);
                    if (!outDir.exists()) {
                        try {
                            outDir.createNewFile();
                        } catch (Exception ex) {
                            tempDir = new File(outDir.getParent());
                            if (!tempDir.exists()) {
                                tempDir.mkdirs();
                            }
                            outDir.createNewFile();
                        }

                    } else {
                        outDir.delete();
                        outDir.createNewFile();
                    }
                    out = new FileOutputStream(outDir, false);
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }

                } catch (Exception ex) {
                    lblFileProgress.setText("Exception " + ex.getMessage());
                } finally {
                    out.flush();
                    out.close();
                    in.close();
                    in = null;
                    out = null;
                    outDir = null;
                    inDir = null;
                }
                return true;
            }
        } catch (Exception e) {
            lblFileProgress.setText("Please FORMAT the Pendrive and again reconnect the Pendrive");

        } finally {
            buffer = null;
        }

        return false;
    }
    int max_filescnt = 1000;

    public static void compareBothFiles(String filePath, String mainFilePath) {
        try {
            String[] mainFile = readDataFromFile(filePath);
            String[] copyFile = readDataFromFile(mainFilePath);
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(mainFilePath, true));

            if (filePath.contains("routeinf.txt")) {
                for (String line : mainFile) {
                    if (!containsLine(copyFile, line)) {
                        fileWriter.write(line);
                        fileWriter.newLine();
                    }
                }
            } else {
                for (String line : mainFile) {
                    if (!containsData(copyFile, line)) {
                        fileWriter.write(line);
                        fileWriter.newLine();
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] readDataFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString().split("\n");
        }
    }

    private static boolean containsData(String[] lines, String line) {
        String[] splitStr = line.split(",");
        String data = splitStr[0];
        for (String s : lines) {
            String[] splitStr1 = s.split(",");
            if (splitStr1.length > 0 && splitStr1[0].equals(data)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLine(String[] lines, String line) {
        for (String s : lines) {
            if (s.equals(line)) {
                return true;
            }
        }
        return false;
    }

    private void startCopyRouteFilesUSBThread(String path) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                String line;
                BufferedReader br = null;
                boolean route_files_found = false;
                File output_copy_path;
                int l = 0;
                File copy_file;
                boolean adding_previous_routes = false;
                File mainroutepath = null;
                File routefilepath = null;
                try {
                    runCmd("sudo chmod 777 -R " + clsDefines.route_filepath);
                    runCmd("sudo chmod 777 -R " + clsDefines.main_route_filepath);
                    int i = 0;
                    files_cnt = 0;
                    final File file = new File(path + "/FILECOPY.LST");
                    br = new BufferedReader(new FileReader(file));
                    final long cnt = br.lines().count();
                    max_filescnt = ((int) cnt);
                    br = new BufferedReader(new FileReader(file));
                    clsReadFiles objReadFiles = new clsReadFiles();
                    clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
                    int add_routes = JOptionPane.showConfirmDialog(null, "Do you want to delete previous routes", "Route", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if ((add_routes == JOptionPane.YES_OPTION)) {
                        adding_previous_routes = false;
                    } else {
                        adding_previous_routes = true;
                    }
                    while ((line = br.readLine()) != null) {
                        copy_file = new File(path + "//" + line.trim());
                        output_copy_path = new File(path);
                        i++;
                        files_cnt++;
                        if (copy_file.exists()) {
                            if (!output_copy_path.exists()) {
                                publish("Copying Error,USB Disconnected");
                                return "Disconnected";
                            } else if (Files.getOwner(output_copy_path.toPath()).toString().equals("root")) {
                                runCmd("sudo rm -rf " + output_copy_path);
                                publish("Copying Error,USB Disconnected");
                                return "Disconnected";
                            }
                            if (line.contains("tokengeneration.jar")) {
                                try {
                                    runCmd("sudo chmod 777 " + obu_filepath);
                                    if (copyFile(copy_file.getAbsolutePath(), obu_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod 777 -R " + obu_filepath);
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("77-mm-quectel-port-types.rules")) {
                                try {
                                    if (copyFile(copy_file.getAbsolutePath(), quectel_rules_d_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("sources.list")) {
                                try {
                                    if (copyFile(copy_file.getAbsolutePath(), sourceslist_file.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }

                            } else if (line.contains("stoprequest.wav") || line.contains("emergency.wav")) {
                                if (copyFile(copy_file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line) == true) {
                                    publish(line + " File Copied");
                                    runCmd("sudo chmod 777 -R " + config_filepath);

                                }
                                if (copyFile(copy_file.getAbsolutePath(), route_filepath.getAbsolutePath() + "/" + line) == true) {
                                    publish(line + " File Copied");
                                    runCmd("sudo chmod 777 -R " + route_filepath);

                                }
                                if (copyFile(copy_file.getAbsolutePath(), main_route_filepath.getAbsolutePath() + "/" + line) == true) {
                                    publish(line + " File Copied");
                                    runCmd("sudo chmod 777 -R " + main_route_filepath);

                                }

                            } else if (line.contains("gstreamer_cam1.sh") || line.contains("gstreamer_cam2.sh") || line.contains("gstreamer_cam3.sh") || line.contains("gstreamer_cam4.sh") || line.contains("gstreamer_top.sh") || line.contains("close_gstreamer.sh")) {
                                if (copyFile(copy_file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line) == true) {
                                    publish(line + " File Copied");
                                    runCmd("sudo chmod 777 -R " + config_filepath);
                                }
                            } else if (line.contains("lxde-rc.xml")) {
                                if (clsDefines.OBU_BRD_TYPE == clsDefines.TINKER_BOARD) {
                                    if (copyFile(copy_file.getAbsolutePath(), config_title_bar_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod 777 -R " + config_title_bar_filepath);
                                    }
                                }
                            } else if (line.contains("pmpml_sumith_oauth.sh") || line.contains("pmpml_sumith.json")) {
                                try {
                                    runCmd("sudo chmod 777 " + main_filepath);
                                    if (copyFile(copy_file.getAbsolutePath(), main_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod 777 -R " + main_filepath);
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("autostart")) {
                                try {
                                    runCmd("sudo chmod 777 " + autostart_file);
                                    if (copyFile(copy_file.getAbsolutePath(), autostart_file.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod 777 " + autostart_file + "/autostart");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("raspi-blacklist.conf")) {
                                try {
                                    runCmd("sudo chmod 777 " + blacklist_config_filepath);
                                    if (copyFile(copy_file.getAbsolutePath(), blacklist_config_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("99-com.rules")) {
                                try {
                                    runCmd("sudo chmod 777 " + udev_rules_filepath);
                                    if (copyFile(copy_file.getAbsolutePath(), udev_rules_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if ((line.contains("json-rpc-1.0"))) {
                                try {
                                    runCmd("sudo chmod 777 " + clsDefines.obu_filepath);
                                    if (copyFile(copy_file.getAbsolutePath(), obu_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("ConfigParams.txt") || (line.contains("CameraConfig.txt")) || (line.contains("CameraConfigArray.txt"))) {
                                try {
                                    runCmd("sudo chmod 777 " + media_filepath);
                                    runCmd("sudo chmod 777 " + config_filepath);
                                    if (clsSharedVariables.getHardDriveDetected()) {
                                        if (copyFile(copy_file.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + line) == true) {
                                            publish(line + " File Copied");
                                        }
                                    }
                                    if (copyFile(copy_file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if ((line.contains("canElectrical.txt")) || (line.contains("canSafety.txt"))
                                    || (line.contains("canTransmit.txt")) || (line.contains("canEngine.txt")) || (line.contains("canOthers.txt"))
                                    || (line.contains("canElectrical1.txt")) || (line.contains("canSafety1.txt"))
                                    || (line.contains("canTransmit1.txt")) || (line.contains("canEngine1.txt")) || (line.contains("canOthers1.txt"))
                                    || (line.contains("phonenum.txt"))
                                    || (line.contains("names.txt")) || (line.contains("PreDefLoc.txt")) || (line.contains("TSPPoint.txt"))
                                    || (line.contains("codedmsg.txt")) || (line.contains("speclmsg.txt")) || (line.contains("drivrlst.txt"))
                                    || (line.contains("fileChangePwd.txt")) || (line.contains("driver_details.txt"))
                                    || (line.contains("ConductorIds.txt"))) {

                                try {
                                    runCmd("sudo chmod 777 " + media_filepath);
                                    runCmd("sudo chmod 777 " + config_filepath);
                                    if (clsSharedVariables.getHardDriveDetected()) {
                                        if (copyFile(copy_file.getAbsolutePath(), media_filepath.getAbsolutePath() + "/" + line) == true) {
                                            publish(line + " File Copied");
                                        }
                                    }
                                    if (copyFile(copy_file.getAbsolutePath(), config_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }

                            } else if ((line.contains("json-rpc-1.0.jar")) || (line.contains("okhttp-2.4.0.jar"))
                                    || (line.contains("okio-1.4.0.jar")) || (line.contains("directions-api-java-client-geocoding-0.8.2.1.jar"))
                                    || (line.contains("gson-2.3.1.jar")) || (line.contains("swagger-annotations-1.5.0.jar"))
                                    || (line.contains("jcalendar-1.4.jar")) || (line.contains("jpos.jar"))
                                    || (line.contains("AbsoluteLayout.jar")) || (line.contains("freetts-1.2.2.jar"))
                                    || (line.contains("jl1.0.jar")) || (line.contains("apache-commons-net.jar"))
                                    || (line.contains("JMapViewer-1.14.1.jar")) || (line.contains("hid4java-0.5.0.jar"))
                                    || (line.contains("comm-2.0.jar")) || (line.contains("commons-net-3.3-ftp.jar"))
                                    || (line.contains("hid4java-0.5.0.jar")) || (line.contains("org.apache.commons.io.jar"))
                                    || (line.contains("jna-4.2.2.jar")) || (line.contains("jssc-2.8.0.jar"))
                                    || (line.contains("org-apache-commons-codec.jar")) || (line.contains("pi4j-core.jar"))
                                    || (line.contains("raspoid-1.0-all"))) {
                                try {
                                    runCmd("sudo chmod 777 " + obu_filepath);
                                    if (copyFile(copy_file.getAbsolutePath(), obu_filepath.getAbsolutePath() + "/lib/" + line) == true) {
                                        publish(line + " File Copied");
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("panel")) {
                                try {
                                    runCmd("sudo chmod 777 " + panel_filepath);

                                    if (copyFile(copy_file.getAbsolutePath(), panel_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    } else {
                                        publish(line + " File Not Copied");

                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("toggle-matchbox-keyboard.sh")) {
                                try {
                                    runCmd("sudo chmod 777 " + panel_config_filepath);

                                    if (copyFile(copy_file.getAbsolutePath(), panel_config_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod +x /usr/bin/toggle-matchbox-keyboard.sh");
                                    } else {
                                        publish(line + " File Not Copied");

                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("toggle-matchbox-keyboard.desktop")) {
                                try {
                                    runCmd("sudo chmod 777 " + panel_desktop_filepath);

                                    if (copyFile(copy_file.getAbsolutePath(), panel_desktop_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        //  runCmd("sudo chmod +x /usr/bin/toggle-matchbox-keyboard.sh");
                                    } else {
                                        publish(line + " File Not Copied");

                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("watchdog.conf")) {
                                try {
                                    runCmd("sudo chmod 777 " + watchdog_filepath);

                                    if (copyFile(copy_file.getAbsolutePath(), watchdog_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    } else {
                                        publish(line + " File Not Copied");

                                    }
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("rtsp2avi")) {//apc file
                                try {
                                    if (copyFile(copy_file.getAbsolutePath(), rtsp_forrir_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    } else {
                                        publish(line + " File Not Copied");
                                    }
                                    runCmd("sudo chmod 777 " + rtsp_filepath);
                                } catch (Exception ex) {

                                }
                            } else if (line.contains("updateKL")) { // Check for condition
                                try {
                                    // Assuming 'copy_file' is a ZIP file, not a folder
                                    File sourceZip = new File(copy_file.getAbsolutePath()); // The source ZIP file
                                    File destinationFolder = new File(main_route_path.getAbsolutePath() + "/" + line); // The destination folder

                                    // Check if the source file is a valid ZIP file
                                    if (sourceZip.exists() && sourceZip.isFile()) {
                                        if (copyZipFile(sourceZip, destinationFolder)) {
                                            publish(line + " zip Copied");
                                        } else {
                                            publish(line + " zip Not Copied");
                                        }

                                        // Set permissions for the copied file/folder (destination)
                                        runCmd("sudo chmod -R 777 " + destinationFolder.getAbsolutePath());
                                    } else {
                                        publish("Source file is not a valid ZIP file");
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace(); // Log the exception
                                }
                            } else if (line.contains("sysctl.conf")) {
                                try {
                                    runCmd("sudo chmod 777 " + watchdog_filepath);

                                    if (copyFile(copy_file.getAbsolutePath(), watchdog_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                    } else {
                                        publish(line + " File Not Copied");

                                    }
                                } catch (Exception ex) {

                                }
                            } else {
                                route_files_found = true;
                                //code to keep previous routes
                                if (adding_previous_routes == true) {
                                    if (line.contains("stopmas.txt") || line.contains("routemas.txt") || line.contains("routeinf.txt")) {
                                        if (clsSharedVariables.getHardDriveDetected()) {
                                            routefilepath = new File(route_filepath.getAbsolutePath() + "/" + line);
                                            if (routefilepath.exists()) {
                                                compareBothFiles(copy_file.getAbsolutePath(), route_filepath.getAbsolutePath() + "/" + line);
                                            } else {
                                                if (copyFile(copy_file.getAbsolutePath(), route_filepath.getAbsolutePath() + "/" + line) == true) {
                                                    publish(line + " File Copied");
                                                }
                                            }
                                        }
                                        if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                                            mainroutepath = new File(main_route_filepath.getAbsolutePath() + "/" + line);
                                            if (mainroutepath.exists()) {
                                                compareBothFiles(copy_file.getAbsolutePath(), main_route_filepath.getAbsolutePath() + "/" + line);
                                            } else {
                                                if (copyFile(copy_file.getAbsolutePath(), main_route_filepath.getAbsolutePath() + "/" + line) == true) {
                                                    publish(line + " File Copied");
                                                }
                                            }
                                        }
                                    } else {
                                        try {
                                            if (clsSharedVariables.getHardDriveDetected()) {
                                                if (copyFile(copy_file.getAbsolutePath(), route_filepath.getAbsolutePath() + "/" + line) == true) {
                                                    publish(line + " File Copied");
                                                }
                                            }
                                            if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                                                if (copyFile(copy_file.getAbsolutePath(), main_route_filepath.getAbsolutePath() + "/" + line) == true) {
                                                    publish(line + " File Copied");
                                                }
                                            }
                                        } catch (Exception ex) {

                                        }
                                    }
                                } else {
                                    try {
                                        if (clsSharedVariables.getHardDriveDetected()) {
                                            if (copyFile(copy_file.getAbsolutePath(), route_filepath.getAbsolutePath() + "/" + line) == true) {
                                                publish(line + " File Copied");
                                            }
                                        }
                                        if (clsDefines.DUPLICATE_FILES_MAIN_PATH) {
                                            if (copyFile(copy_file.getAbsolutePath(), main_route_filepath.getAbsolutePath() + "/" + line) == true) {
                                                publish(line + " File Copied");
                                            }
                                        }
                                    } catch (Exception ex) {

                                    }
                                }
                            }

                            copy_file = null;
                        }
                    }

                    progBar.setValue((int) cnt);

                    runCmd("sudo sync");
                    objReadFiles.read_special_msg_info();
                    //objReadFiles.init_schedule_files();
                    // objReadFiles.init_route_master_file();
                    objReadFiles.read_route_master_file();
                    objReadFiles.read_cfg_data_file();
                    objReadFiles.read_camera_cfg_file();
                    objReadFiles.read_phone_no_details();
                    objReadFiles.read_password_change();
                    objReadFiles.read_traffic_points();
                    objReadFiles.read_pre_def_location_points();
                    objReadFiles.read_panic_messages();
                    objReadFiles.read_special_msg_info();
                    objReadFiles.read_reset_data();
                    objReadFiles.read_driver_names();
                    objReadFiles.read_conductor_names();
                    objReadFiles.read_label_names_details();
                    objReadFiles.read_canElectricals_file();
                    objReadFiles.read_canSafety_file();
                    objReadFiles.read_canEngine_file();
                    objReadFiles.read_canTransmit_file();
                    objReadFiles.read_canOthers_file();
                    objReadDataFiles.read_canElectricals_file1();
                    objReadDataFiles.read_canSafety_file1();
                    objReadDataFiles.read_canEngine_file1();
                    objReadDataFiles.read_canTransmit_file1();
                    objReadDataFiles.read_canOthers_file1();

                    publish("Files Copied");
                    if (route_files_found == true) {
                        if (clsSharedVariables.getCurTripStat() != TRIP_START) {
                            objReadFiles.read_route_master_file();
                            if (clsSharedVariables.getSchRouteEnable() == ROUTE_ENABLE) {
                                setCurSchNoTrips(getNoRoutes());
                                setSchId("1");
                                for (int j = 0; j < getCurSchNoTrips(); j++) {
                                    setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                                    setCurSchTripStatus(j, TRIP_UNKNOWN);
                                }
                            } else {

                                for (int j = 0; j < getCurSchNoTrips(); j++) {
                                    for (int k = 0; k < getCurSchNoTrips(); k++) {
                                        if (objRouteMasFiles[j].route_no.equals(getCurSchRouteNo(k))) {
                                            setCurSchRouteNo(l, objRouteMasFiles[j].route_no);
                                            setCurSchTripStatus(l, TRIP_UNKNOWN);
                                        }
                                    }
                                }
                                setCurSchNoTrips(l);
                            }

                            final Calendar cal = Calendar.getInstance();
                            setCurSchStartDate(cal.getTime());
                            setCurSchStartTime(cal.getTime().getTime());
                            cal.add(Calendar.DATE, 1);
                            setCurSchEndDate(cal.getTime());
                            setCurSchEndTime(cal.getTime().getTime());
                            setCurTripNo((byte) 0);
                            setCurTripStat(TRIP_UNKNOWN);
                            setCurRouteStat(false);
                            objReadFiles.write_cur_route_info_file();
                            objReadFiles.write_cur_trip_info_file();
                            objReadFiles.write_cur_schedule_file();
                        } else {
                            int j = 0;
                            int result = JOptionPane.showConfirmDialog(null, "Route in Start Mode", "Auto Route End ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if ((result == JOptionPane.YES_OPTION)) {
                                clsSharedVariables.setCurTripStat(TRIP_END);

                                objReadFiles.read_route_master_file();
                                if (clsSharedVariables.getSchRouteEnable() == ROUTE_ENABLE) {
                                    setCurSchNoTrips(getNoRoutes());
                                    setSchId("1");
                                    for (j = 0; j < getCurSchNoTrips(); j++) {
                                        setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                                        setCurSchTripStatus(j, TRIP_UNKNOWN);
                                    }
                                } else {

                                    for (j = 0; j < getCurSchNoTrips(); j++) {
                                        for (int k = 0; k < getCurSchNoTrips(); k++) {
                                            if (objRouteMasFiles[j].route_no.equals(getCurSchRouteNo(k))) {
                                                setCurSchRouteNo(l, objRouteMasFiles[j].route_no);
                                                setCurSchTripStatus(l, TRIP_UNKNOWN);
                                            }
                                        }
                                    }
                                    setCurSchNoTrips(l);
                                }
                                final Calendar cal = Calendar.getInstance();

                                setCurSchStartDate(cal.getTime());
                                setCurSchStartTime(cal.getTime().getTime());
                                cal.add(Calendar.DATE, 1);
                                setCurSchEndDate(cal.getTime());
                                setCurSchEndTime(cal.getTime().getTime());
                                setCurTripNo((byte) 0);
                                setCurTripStat(TRIP_UNKNOWN);
                                setCurRouteStat(false);
                                objReadFiles.write_cur_route_info_file();
                                objReadFiles.write_cur_trip_info_file();
                                objReadFiles.write_cur_schedule_file();
                            }
                        }
                    }
                    publish("Files Copied");
                    driver_alarm();
                    ph_contacts_updated = true;

                } catch (FileNotFoundException e) {
                    return ("Error " + e.getMessage());

                } catch (IOException e) {
                    return ("Error " + e.getMessage());

                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                }
                return ("Over");
            }

            int files_cnt = 0;

            @Override
            protected void process(List chunks) {
                String data;
                try {
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        progBar.setValue(files_cnt);
                        if (data.equals("Over")) {
                            progBar.setVisible(false);

                            break;
                        } else if (data.equals("Disconnected")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,USB Disconnected");

                            break;
                        } else if (data.equals("Error")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,Please Try Again");

                            break;
                        } else {
                            lblFileProgress.setText(data);
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
                    progBar.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void startCopyLibraryFilesUSBThread(String path) {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                String line;
                BufferedReader br = null;
                File output_copy_path;
                File copy_file;

                try {

                    runCmd("sudo chmod 777 -R " + clsDefines.obu_library_filepath);

                    int i = 0;
                    files_cnt = 0;
                    final File file = new File(path + "/FILELIB.LST");
                    br = new BufferedReader(new FileReader(file));
                    final long cnt = br.lines().count();
                    max_filescnt = ((int) cnt);
                    br = new BufferedReader(new FileReader(file));

                    while ((line = br.readLine()) != null) {
                        if (line.endsWith("jar")) {
                            copy_file = new File(path + "//" + line);
                            output_copy_path = new File(path);
                            i++;
                            files_cnt++;
                            if (copy_file.exists()) {

                                if (!output_copy_path.exists()) {
                                    publish("Copying Error,USB Disconnected");
                                    return "Disconnected";

                                } else if (Files.getOwner(output_copy_path.toPath()).toString().equals("root")) {
                                    runCmd("sudo rm -rf " + output_copy_path);
                                    publish("Copying Error,USB Disconnected");
                                    return "Disconnected";
                                }

                                try {

                                    if (copyFile(copy_file.getAbsolutePath(), obu_library_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod 777 -R " + clsDefines.obu_library_filepath);
                                    }
                                } catch (Exception ex) {

                                }
                            }
                        } else {
                            copy_file = new File(path + "//" + line);
                            output_copy_path = new File(path);
                            i++;
                            files_cnt++;
                            if (copy_file.exists()) {

                                if (!output_copy_path.exists()) {
                                    publish("Copying Error,USB Disconnected");
                                    return "Disconnected";

                                } else if (Files.getOwner(output_copy_path.toPath()).toString().equals("root")) {
                                    runCmd("sudo rm -rf " + output_copy_path);
                                    publish("Copying Error,USB Disconnected");
                                    return "Disconnected";
                                }

                                try {
                                    if (!obu_images_filepath.exists()) {
                                        runCmd("sudo mkdir /usr/share/icons/sumith_icon/");
                                        runCmd("sudo mkdir /usr/share/icons/sumith_icon/Images");
                                    }
                                    if (copyFile(copy_file.getAbsolutePath(), obu_images_filepath.getAbsolutePath() + "/" + line) == true) {
                                        publish(line + " File Copied");
                                        runCmd("sudo chmod 777 -R " + clsDefines.obu_images_filepath);
                                    }
                                } catch (Exception ex) {

                                }
                            }

                        }

                        publish("Files Copied");

                    }
                    publish("Files Copied");
                    driver_alarm();

                } catch (FileNotFoundException e) {
                    return ("Error " + e.getMessage());

                } catch (IOException e) {
                    return ("Error " + e.getMessage());

                } finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException e) {

                    }
                }
                return ("Over");
            }

            int files_cnt = 0;

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        progBar.setValue(files_cnt);
                        //  lblFileProgress.setText(data);
                        if (data.equals("Over")) {
                            progBar.setVisible(false);
                            //  lblFileProgress.setText("Processing Completed...");

                            break;
                        } else if (data.equals("Disconnected")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,USB Disconnected");

                            break;
                        } else if (data.equals("Error")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,Please Try Again");

                            break;
                        } else {
                            lblFileProgress.setText(data);
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
                    progBar.setVisible(false);
                    btnLibraries.setBackground(bgcolor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void btnWifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWifiActionPerformed
        // TODO add your handling code here:
        btn_selected = BTN_FTP;
        this.btnUSB.setBackground(bgcolorusbwifi);
        this.btnWifi.setBackground(Color.GREEN);
        progress.setVisible(false);
        btnInstallApp.setBackground(bgcolor);
        this.btnCopyFilesFromUsb.setBackground(bgcolor);
        btnCopyFilesToUsb.setBackground(bgcolor);
        lblFileProgress.setText("");
        chkWifi.setVisible(true);
        this.panCopyTo.setVisible(false);
        this.panRadBtn.setVisible(true);
        this.btnCopyFilesFromUsb.setText("Download");
        this.btnCopyFilesToUsb.setText("Upload");
        panCopyTo.setVisible(false);
        btnCopy.setVisible(false);
        lblFileProgress.setText("");
        lblFileProgress.setText("");

    }//GEN-LAST:event_btnWifiActionPerformed

    private void cmbSelectFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectFilesActionPerformed
        // TODO add your handling code here:

        if (cmbSelectFiles.getSelectedIndex() == 0 || cmbSelectFiles.getSelectedIndex() == 1
                || cmbSelectFiles.getSelectedIndex() == 2 || cmbSelectFiles.getSelectedIndex() == 3 || cmbSelectFiles.getSelectedIndex() == 4 || cmbSelectFiles.getSelectedIndex() == 5
                || cmbSelectFiles.getSelectedIndex() == 6 || cmbSelectFiles.getSelectedIndex() == 7 || cmbSelectFiles.getSelectedIndex() == 8 || cmbSelectFiles.getSelectedIndex() == 9
                || cmbSelectFiles.getSelectedIndex() == 10 || cmbSelectFiles.getSelectedIndex() == 11) {
            panCamDate.setVisible(true);
        } else {
            panCamDate.setVisible(false);
        }
        lblFileProgress.setText("");
    }//GEN-LAST:event_cmbSelectFilesActionPerformed
    private void copyFilesFromUSB() {
        // TODO add your handling code here:
        int i = 0;
        String path = null;
        File[] out_path;
        File[] files;
        boolean found = false;
        UserPrincipal p;
        File file;
        runCmd("sudo chmod 777 " + clsDefines.config_filepath);
        runCmd("sudo chmod 777 " + clsDefines.media_filepath);

        runCmd("sudo chmod 777 " + clsDefines.route_filepath);
        runCmd("sudo chmod 777 " + clsDefines.main_route_filepath);
        try {
            out_path = usb_filepath.listFiles();

            if (out_path == null || out_path.length == 0) {
                path = usb_filepath.getAbsolutePath();
            } else if (out_path.length == 2) {
                Arrays.sort(out_path);
                for (i = 0; i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                        path = out_path[i].getAbsolutePath();

                        break;
                    }
                }
            } else {
                Arrays.sort(out_path);
                for (i = 0; i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                        p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);

                        if (p.toString().equals("root")) {
                            runCmd("sudo rm -rf " + out_path[i]);

                        } else {
                            path = out_path[i].getAbsolutePath();
                        }
                    }
                }
            }

            if (path == null) {
                lblFileProgress.setText("Please Insert Pendrive");
                return;
            }

            lblFileProgress.setText("");
            file = new File(path);
            //copy files through USB
            if (!file.exists()) {
                lblFileProgress.setText("Please Insert Pendrive");

                file = null;
                return;
            }
            progBar.setValue(0);
            progBar.setVisible(true);
            files = file.listFiles();

            for (i = 0; files != null && i < files.length; i++) {
                if (files[i].getName().equals("FILECOPY.LST")) {

                    found = true;
                }
            }
            if (found) {
                progBar.setVisible(true);
                lblFileProgress.setText("Copying Files ...");
                startCopyRouteFilesUSBThread(path);
            } else {
                progBar.setVisible(false);
                lblFileProgress.setText("No Files");
            }
        } catch (Exception e) {
        } finally {
            path = null;
            out_path = null;
            files = null;
            file = null;
            p = null;
        }
    }

    private void installAppWifi() {
        runCmd("sudo chmod 777 " + obu_filepath);
        lblFileProgress.setText("Application Downloading ...");
        progBar.setValue(0);
        progBar.setVisible(true);
        AsyncTaskRunnerGprsSingle gprsRunnerSingle;
        gprsRunnerSingle = new AsyncTaskRunnerGprsSingle("ObuIts.jar");
        gprsRunnerSingle.start();
    }

    private void btnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyActionPerformed
        // TODO add your handling code here:

        if (btn_selected == BTN_USB) {
            if (clsSharedVariables.getHardDriveDetected()) {
                this.copyRouteFilesToUsb();
            } else {
                this.lblFileProgress.setText("Hard Disk Not Detected");
            }
        } else if (btn_selected == BTN_FTP) {
            uploadRouteFilesToFtp();
        }
    }//GEN-LAST:event_btnCopyActionPerformed

    private void copyRouteFilesToUsb() {
        File cam_path;
        File[] out_path;
        out_path = usb_filepath.listFiles();
        if (out_path == null) {
            return;
        }
        Arrays.sort(out_path);
        runCmd("sudo chmod -R 777 " + usb_filepath);
        String file_path;
        switch (cmbSubSelectFiles.getSelectedIndex()) {
            case 0:
                file_path = video_filepath.getAbsolutePath() + "/";
                break;
            case 1:
                file_path = (video_event_filepath.getAbsolutePath() + "/");
                break;
            case 2:
                file_path = (can_video_event_filepath.getAbsolutePath() + "/");
                break;
            case 3:
                file_path = (motion_video_event_filepath.getAbsolutePath() + "/");
                break;
            default:
                file_path = (snap_filepath.getAbsolutePath() + "/");
                break;
        }
        try {
            int index = cmbSelectFiles.getSelectedIndex();
            switch (index) {
                case 0:
                    file_path = file_path + "Cam1/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 1:
                    file_path = file_path + "Cam2/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 2:
                    file_path = file_path + "Cam3/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 3:
                    file_path = file_path + "Cam4/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 4:
                    file_path = file_path + "Cam5/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 5:
                    file_path = file_path + "Cam6/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 6:
                    file_path = file_path + "Cam7/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 7:
                    file_path = file_path + "Cam8/";
                    switch (cmbSubSelectFiles.getSelectedIndex()) {
                        case 4:
                            file_path = file_path + "Cont/";
                            break;
                        case 5:
                            file_path = file_path + "Dig1/";
                            break;
                        case 6:
                            file_path = file_path + "Dig2/";
                            break;
                        case 7:
                            file_path = file_path + "Dig3/";
                            break;
                        case 8:
                            file_path = file_path + "Dig4/";
                            break;
                        default:
                            break;
                    }
                    cam_path = new File(file_path);
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 8:
                    cam_path = new File(media_filepath + "/Log");
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 9:
                    //network log
                    cam_path = new File(media_filepath.getAbsolutePath() + "/storeddata");
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                case 10:
                    cam_path = new File(media_filepath + "/Log/CAN");
                    progBar.setVisible(true);
                    startCopyThread(cam_path);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {

        } finally {
            cam_path = null;
            out_path = null;
        }
    }

    private void startCopyThread(File cam_path) {
        SwingWorker sw1 = new SwingWorker() {

            @Override
            protected String doInBackground() throws Exception {
                int i;
                //String str;
                JCheckBox index;
                Iterator<JCheckBox> chkCamFiles;
                //  long str_int;
                String path = usb_filepath.getAbsolutePath();
                // String[] array;
                File output_copy_path;
                UserPrincipal p;
                File obu_dir_path;
                short no_selected_files = 0;
                File[] out_path = usb_filepath.listFiles();
                if (out_path == null) {
                    return "";
                }
                try {

                    chkCamFiles = arrayListChkCamFiles.iterator();
                    while (chkCamFiles.hasNext()) {
                        index = chkCamFiles.next();
                        if (index.isSelected()) {
                            no_selected_files++;
                        }
                    }

                    chkCamFiles = arrayListChkCamFiles.iterator();

                    for (i = 0; i < out_path.length; i++) {
                        if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                            p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);
                            if (p.toString().equals("root")) {
                            } else {
                                path = out_path[i].getAbsolutePath();
                            }
                        }
                    }

                    output_copy_path = new File(path);

                    path = path + "/" + clsSharedVariables.getOBUID();
                    obu_dir_path = new File(path);

                    if (!obu_dir_path.exists()) {
                        if (obu_dir_path.mkdir() == false) {
                            obu_dir_path = output_copy_path;

                        }
                    } else if (!obu_dir_path.isDirectory()) {
                        if (obu_dir_path.mkdir() == false) {
                            obu_dir_path = output_copy_path;
                        }
                    }

                    if (arrayListChkCamFiles.isEmpty()) {
                        publish("FilesNotThere");
                        return "FilesNotThere";
                    } else {
                        publish(" File Copying... please don't remove the USB");
                        publish("pbar nofiles:" + no_selected_files);
                        files_cnt = 0;

                        while (chkCamFiles.hasNext()) {
                            if (!output_copy_path.exists()) {
                                publish("Disconnected");
                                return "Disconnected";
                            } else if (Files.getOwner(output_copy_path.toPath()).toString().equals("root")) {
                                publish("Disconnected");
                                return "Disconnected";
                            }
                            index = chkCamFiles.next();
                            files_cnt++;
                            if (index.isSelected()) {
                                if (index.getText().startsWith("e")) {
                                    {
                                        if (copyFile(clsDefines.extra_video_filepath.getAbsolutePath() + "/" + index.getText(), path + "/" + index.getText().substring(1, index.getText().length())) == true) {
                                            publish(index.getText() + " File Copied");

                                            publish("pbar :" + files_cnt);
                                        }
                                    }
                                } else {
                                    runCmd("sudo chmod -R 777  " + cam_path + "/" + index.getText());
                                    if (copyFile(cam_path + "/" + index.getText(), path + "/" + index.getText()) == true) {
                                        publish(index.getText() + " File Copied");
                                        publish("pbar :" + files_cnt);
                                    }
                                }
                            }

                        }
                        driver_alarm();

                    }
                } catch (Exception e) {
                    return "Over";
                } finally {
                    output_copy_path = null;
                    path = null;
                    index = null;
                    chkCamFiles = null;
                    path = null;
                    output_copy_path = null;
                    p = null;
                    obu_dir_path = null;
                    out_path = null;
                }
                publish("Over");
                return "Over";
            }

            int files_cnt = 0;

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                String[] split_str;
                try {
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        progBar.setValue(files_cnt);
                        if (data.equals("Over")) {
                            progBar.setVisible(false);
                            break;

                        } else if (data.equals("Disconnected")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,USB Disconnected");

                            break;

                        } else if (data.equals("FilesNotThere")) {
                            lblFileProgress.setText("No Files to Copy");

                            break;
                        } else if (data.contains("pbar nofiles")) {
                            split_str = data.split(":", -1);
                            if (split_str.length > 1) {
                                try {
                                    progBar.setMaximum(Integer.parseInt(split_str[1]));
                                } catch (Exception ex) {
                                }
                            }

                        } else if (data.contains("pbar")) {
                            split_str = data.split(":", -1);
                            if (split_str.length > 1) {
                                try {
                                    progBar.setValue(Integer.parseInt(split_str[1]));
                                } catch (Exception ex) {
                                }
                            }

                        } else {
                            lblFileProgress.setText(data);
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
                try {
                    progBar.setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void startCopy(long from_date_int, long to_date_int, File cam_path) {
        SwingWorker sw1;
        sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i;
                String str;
                long str_int;
                String[] split_str;
                int y = 0;
                arrayListChkCamFiles.clear();
                panListFiles.removeAll();
                try {
                    final File[] files = cam_path.listFiles();
                    if (files != null && files.length == 0) {
                        publish("FilesNotThere");
                        return "FilesNotThere";
                    } else {
                        Arrays.sort(files, java.util.Collections.reverseOrder());
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        for (i = 0; i < files.length; i++) {
                            str = files[i].getName();
                            split_str = str.split("_");
                            if (split_str.length > 1) {
                                str = split_str[1];
                            }
                            try {
                                str_int = Long.parseLong(str.substring(0, str.length() - 4));
                                if (str_int > from_date_int && str_int < to_date_int) {
                                    final JCheckBox cb1 = new JCheckBox(files[i].getName());
                                    panListFiles.add(cb1);
                                    cb1.setBounds(0, y, 450, 20);
                                    cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                    arrayListChkCamFiles.add(cb1);
                                    panListFiles.add(cb1);
                                    y += 30;

                                }
                            } catch (Exception ex) {
                                publish(ex.getMessage());
                            }
                        }
                    }
                    publish("Over");
                } catch (Exception e) {
                    publish("Over");
                } finally {
                    str = null;
                    split_str = null;
                    str_int = 0;
                }
                ScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(50, 0));
                publish("Over");
                return "Error";
            }

            @Override
            protected void process(List chunks
            ) {
                // define what the event dispatch thread
                // will do with the intermediate results received
                // while the thread is executing
                String data;
                try {
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        if (data.equals("Over")) {
                            progBar.setVisible(false);
                            break;

                        } else if (data.equals("Disconnected")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,USB Disconnected");

                            break;

                        } else if (data.equals("FilesNotThere")) {
                            lblFileProgress.setText("No Files to Copy");

                            break;
                        } else if (data.equals("Error")) {
                            lblFileProgress.setText("Error");

                            break;
                        } else {
                            lblFileProgress.setText(data);

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
                    progBar.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void startCopySnapshot(long from_date_int, long to_date_int, File cam_path) {
        SwingWorker sw1;
        sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i;
                String str;
                long str_int;
                String[] split_str;

                int y = 0;
                arrayListChkCamFiles.clear();
                panListFiles.removeAll();
                try {
                    final File[] files = cam_path.listFiles();
                    if (files != null && files.length == 0) {
                        publish("FilesNotThere");
                        return "FilesNotThere";
                    } else {
                        Arrays.sort(files, java.util.Collections.reverseOrder());
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        for (i = 0; i < files.length; i++) {
                            str = files[i].getName();
                            split_str = str.split("_");
                            if (split_str.length > 1) {
                                str = split_str[1];
                            }
                            try {
                                str_int = Long.parseLong(str.substring(0, str.length() - 4));
                                if (str_int > from_date_int && str_int < to_date_int) {
                                    final JCheckBox cb1 = new JCheckBox(files[i].getName()); ///files[i].getName()
                                    panListFiles.add(cb1);
                                    cb1.setBounds(0, y, 450, 20);
                                    cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                    arrayListChkCamFiles.add(cb1);
                                    panListFiles.add(cb1);
                                    y += 30;
                                }
                            } catch (Exception ex) {
                                publish(ex.getMessage());
                            }
                        }
                    }
                    publish("Over");
                } catch (Exception e) {
                    publish("Over");
                } finally {
                    str = null;
                    split_str = null;
                    str_int = 0;
                }
                ScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
                publish("Over");
                return "Error";
            }

            @Override
            protected void process(List chunks) {
                String data;
                try {
                    for (int i = 0; i < chunks.size(); i++) {
                        data = (String) chunks.get(i);
                        if (data.equals("Over")) {
                            progBar.setVisible(false);
                            break;
                        } else if (data.equals("Disconnected")) {
                            progBar.setVisible(false);
                            lblFileProgress.setText("Copying Error,USB Disconnected");
                            break;

                        } else if (data.equals("FilesNotThere")) {
                            lblFileProgress.setText("No Files to Copy");
                            break;
                        } else if (data.equals("Error")) {
                            lblFileProgress.setText("Error");
                            break;
                        } else {
                            lblFileProgress.setText(data);
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
                    progBar.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void uploadRouteFilesToFtp() {
        // TODO add your handling code here:
        int index = cmbSelectFiles.getSelectedIndex();
        File cam_path;
        File f;
        try {
            String file_path;
            if (cmbSubSelectFiles.getSelectedIndex() == 0) {
                file_path = video_filepath.getAbsolutePath() + "/";
            } else if (cmbSubSelectFiles.getSelectedIndex() == 1) {
                file_path = (video_event_filepath.getAbsolutePath() + "/");
            } else if (cmbSubSelectFiles.getSelectedIndex() == 2) {
                file_path = (can_video_event_filepath.getAbsolutePath() + "/");
            } else if (cmbSubSelectFiles.getSelectedIndex() == 3) {
                file_path = (motion_video_event_filepath.getAbsolutePath() + "/");
            } else {
                file_path = (snap_filepath.getAbsolutePath() + "/");
            }
            switch (index) {
                case 0:
                    file_path = file_path + "Cam1/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {
                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                        lblFileProgress.setText("Uploading Video File ...");
                        progBar.setVisible(true);
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                        startThread(cam_path);
                    }
                    break;
                case 1:
                    file_path = file_path + "Cam2/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                        } else {
                            lblFileProgress.setText("Uploading Video File ...");
                            startThread(cam_path);
                            progBar.setMaximum(cam_path.listFiles().length);
                            progBar.setValue(0);
                        }

                    }
                    break;
                case 2:
                    file_path = file_path + "Cam3/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        } else {
                            lblFileProgress.setText("Uploading Video File ...");
                            startThread(cam_path);

                        }
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                    }
                    break;
                case 3:
                    file_path = file_path + "Cam4/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {
                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                        lblFileProgress.setText("Uploading Video File ...");
                        startThread(cam_path);
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                    }
                    break;
                case 4:
                    file_path = file_path + "Cam5/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                        lblFileProgress.setText("Uploading Video File ...");
                        startThread(cam_path);
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                    }
                    break;
                case 5:
                    file_path = file_path + "Cam6/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                        lblFileProgress.setText("Uploading Video File ...");
                        startThread(cam_path);
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                    }
                    break;
                case 6:
                    file_path = file_path + "Cam7/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {
                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                        lblFileProgress.setText("Uploading Video File ...");
                        startThread(cam_path);
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                    }
                    break;
                case 7:
                    file_path = file_path + "Cam8/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        if (cam_path.listFiles() != null && cam_path.listFiles().length == 0) {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                        lblFileProgress.setText("Uploading Video File ...");
                        startThread(cam_path);
                        progBar.setMaximum(cam_path.listFiles().length);
                        progBar.setValue(0);
                    }
                    break;
                case 8:
                    f = new File(media_filepath + "/Log");
                    if (f.exists()) {

                        if (f.listFiles() != null && f.listFiles().length > 0) {
                            startUploadOtherThread(f);
                            progBar.setMaximum(f.listFiles().length);
                            progBar.setValue(0);
                        } else {
                            lblFileProgress.setText("No Files to Upload");
                        }

                    } else {
                        lblFileProgress.setText("No Files to Upload");

                    }
                    break;
                case 9:
                    f = new File(media_filepath.getAbsolutePath() + "/storeddata");
                    if (f.exists()) {

                        if (f.listFiles() != null && f.listFiles().length > 0) {
                            startUploadOtherThread(f);
                            progBar.setMaximum(f.listFiles().length);
                            progBar.setValue(0);
                        } else {
                            lblFileProgress.setText("No Files to Upload");
                            return;
                        }
                    } else {
                        lblFileProgress.setText("No Files to Upload");
                        return;
                    }
                    f = new File(media_filepath.getAbsolutePath() + "/storeddata2");
                    if (f.exists()) {

                        final File[] files = f.listFiles();
                        if (files != null && files.length > 0) {
                            startUploadOtherThread(f);
                            progBar.setMaximum(f.listFiles().length);
                            progBar.setValue(0);
                        } else {
                            lblFileProgress.setText("No Files to Upload");

                            return;
                        }
                    } else {
                        lblFileProgress.setText("No Files to Upload");
                        return;
                    }
                    try {
                        f = new File(media_filepath + "/NonGpsPkts.txt");
                        if (f.exists()) {
                            final File[] files = f.listFiles();
                            if (files != null && files.length > 0) {
                                startUploadOtherThread(f);
                                progBar.setMaximum(f.listFiles().length);
                                progBar.setValue(0);
                            } else {
                                lblFileProgress.setText("No Files to Upload");

                                return;
                            }
                        } else {
                            lblFileProgress.setText("No Files to Upload");

                            return;
                        }
                    } catch (Exception ex2) {
                        lblFileProgress.setText(ex2.getMessage());
                    }
                    try {
                        f = new File(media_filepath + "/NonGpsPkts2.txt");
                        if (f.exists()) {

                            final File[] files = f.listFiles();
                            if (files != null && files.length > 0) {
                                startUploadOtherThread(f);
                                progBar.setMaximum(f.listFiles().length);
                                progBar.setValue(0);
                            } else {
                                lblFileProgress.setText("No Files to Upload");

                            }
                        } else {
                            lblFileProgress.setText("No Files to Upload");

                        }
                    } catch (Exception ex2) {
                        lblFileProgress.setText(ex2.getMessage());
                    }
                    break;
                case 10:
                    f = new File(media_filepath + "/Log/CAN");
                    if (f.exists()) {
                        if (f.listFiles() != null && f.listFiles().length > 0) {
                            startUploadOtherThread(f);
                            progBar.setMaximum(f.listFiles().length);
                            progBar.setValue(0);
                        } else {
                            lblFileProgress.setText("No Files to Upload");
                        }

                    } else {
                        lblFileProgress.setText("No Files to Upload");
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        } finally {
            cam_path = null;
            f = null;
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
    }

    private void nmcli_commands_wifi() {
        File f = new File("/etc/NetworkManager/system-connections");
        File[] str = f.listFiles();
        String[] split_str;
        int i;
        try {
            if (str != null) {
                for (i = 0; i < str.length; i++) {
                    if (str[i].getName().startsWith("WIRELESS")) {
                        split_str = str[i].getName().split("\\.", -1);
                        runCmdWifi("sudo nmcli con delete " + split_str[0]);
                        str[i].delete();
                    }
                }
            }
        } catch (Exception ex) {
        }
        runCmdWifi("sudo ifconfig wlan0 up");
        runCmdWifi("sudo ifconfig wlan0 up");
        runCmd("sudo nmcli radio  wifi on");
        runCmdWifi("sudo nmcli device wifi connect \"" + clsSharedVariables.wifi_ssid + "\" password " + clsSharedVariables.wifi_password + " name WIRELESS");
        progress.setValue(9);
        runCmdWifi("sudo chmod -R 777 /etc/NetworkManager/system-connections");
    }

    private synchronized void update_wifi_icon(boolean state) {
        if (state == true) {

            if (clsDefines.obu_images_filepath.exists() == true) {
                imgWifi.setIcon(new javax.swing.ImageIcon(obu_images_filepath + "/wifi-signal.png"));
                imgWifi.repaint();
            } else {
                imgWifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/wifi-signal.png")));
                imgWifi.repaint();
            }
            imgWifi.setVisible(true);
        } else {
            imgWifi.setVisible(false);
        }
    }

    public void startTimer() {
        //set a new Timer
        if (wifi_status_timer == null) {
            wifi_status_timer = new Timer();
            //initialize the TimerTask's job
            initializeWifiTimerTask();
            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            wifi_status_timer.schedule(wifi_status_timer_task, 2000, 5000);
        }
    }

    public void initializeWifiTimerTask() {
        wifi_status_timer_task = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (wifiSelect == true) {
                        runCmdWifi("sudo ifconfig wlan0 up");
                    }
                    NetworkInterface nif = NetworkInterface.getByName(WIFI_NETWORKINTERFACE_PATH);
                    if (nif != null && nif.isUp()) {
                        setWifiConnect(true);
                        update_wifi_icon(true);

                    } else {
                        runCmd("nmcli con down id WIRELESS");
                        update_wifi_icon(false);
                        setWifiConnect(false);
                        chkWifi.setSelected(false);
                    }
                    stopTimer();
                    nif = null;
                } catch (SocketException ex) {
                }
            }
        };
    }

    public void stopTimer() {

        try {
            progress.setVisible(false);
            lblwifi.setVisible(false);
            if (wifi_status_timer != null) {
                wifi_status_timer.cancel();
                wifi_status_timer_task.cancel();
            }

        } catch (Exception ex) {

        } finally {
            wifi_status_timer = null;
            wifi_status_timer_task = null;
        }
    }

    static boolean wifiSelect = false;
    private void chkWifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkWifiActionPerformed
        try {
            progress.setVisible(true);
            lblwifi.setVisible(true);
            lblwifi.setText(" ");
            progress.setValue(0);
            SwingWorker sw1 = new SwingWorker() {
                @Override
                protected String doInBackground() throws Exception {
                    NetworkInterface nif = NetworkInterface.getByName(WIFI_NETWORKINTERFACE_PATH);
                    if (chkWifi.isSelected()) {
                        try {
                            progress.setValue(0);
                            lblwifi.setText("Please wait....");
                            runCmd("rfkill unblock all");
                            progress.setValue(5);
                            wifiSelect = true;
                            progress.setValue(7);
                            setWifiConnect(true);
                            nmcli_commands_wifi();
                            startTimer();

                        } catch (Exception ex) {

                        }
                    } else {
                        setWifiConnect(false);
                        wifiSelect = false;
                        runCmd("nmcli con down id WIRELESS");
                        runCmd("nmcli con down id WIRELESS");
                        startTimer();
                    }
                    if (nif != null && nif.isUp()) {
                        update_wifi_icon(true);
                    } else {
                        update_wifi_icon(false);
                    }
                    nif = null;
                    return "true";
                }

                @Override
                public void done() {
                    progress.setValue(10);
                    try {
                        NetworkInterface nif = NetworkInterface.getByName(clsDefines.WIFI_NETWORKINTERFACE_PATH);
                        if (nif != null && nif.isUp()) {
                            lblwifi.setText("wifi connected");
                        } else {
                            lblwifi.setText("wifi disconnected");
                        }
                    } catch (SocketException ex) {
                    }
                }
            };
            sw1.execute();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_chkWifiActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost

    }//GEN-LAST:event_formFocusLost

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden


    }//GEN-LAST:event_formComponentHidden

    private void btnListFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListFilesActionPerformed
        // TODO add your handling code here:
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf_time = new SimpleDateFormat("HHmm");
        UserPrincipal p;
        int index = cmbSelectFiles.getSelectedIndex();
        lblFileProgress.setText("  ");
        File cam_path;
        long from_date_int;
        long to_date_int;
        File[] out_path;
        out_path = usb_filepath.listFiles();
        AllSelect.setSelected(false);
        panListFiles.removeAll();
        Arrays.sort(out_path, java.util.Collections.reverseOrder());
        runCmd("sudo chmod -R 777 " + usb_filepath);
        String dateTimeFrom = sdf.format(dateChooserFrom.getDate()) + sdf_time.format(spinStartTime.getValue());
        String dateTimeTo = sdf.format(dateChooserTo.getDate()) + sdf_time.format(spinEndTime.getValue());
        int y = 0;
        int i = 0;
        panListFiles.removeAll();
        arrayListChkCamFiles.clear();
        panListFiles.updateUI();
        String file_path;
        if (cmbSubSelectFiles.getSelectedIndex() == 0) { //normal video
            file_path = video_filepath.getAbsolutePath() + "/";
        } else if (cmbSubSelectFiles.getSelectedIndex() == 1) {  //Event detection
            file_path = (video_event_filepath.getAbsolutePath() + "/");
        } else if (cmbSubSelectFiles.getSelectedIndex() == 2) {   //CAN
            file_path = (can_video_event_filepath.getAbsolutePath() + "/");
        } else if (cmbSubSelectFiles.getSelectedIndex() == 3) {   //Motion
            file_path = (motion_video_event_filepath.getAbsolutePath() + "/");
        } else {
            file_path = (snap_filepath.getAbsolutePath() + "/"); //Dig Snapshot rec
        }

        try {
            switch (index) {
                case 0:
                    file_path = file_path + "Cam1/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "1" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "1" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                       // lblFileProgress.setText("Please wait ...");

                        if (cam_path.getAbsolutePath().contains("Snapshot")) {
                            startCopySnapshot(from_date_int, to_date_int, cam_path);
                        } else {
                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblFileProgress.setText(" Files are not Available");

                    }
                    break;
                case 1:
                    file_path = file_path + "Cam2/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "2" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "2" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                       // lblFileProgress.setText("Please wait ...");

                        if (cam_path.getAbsolutePath().contains("Snapshot")) {
                            startCopySnapshot(from_date_int, to_date_int, cam_path);
                        } else {
                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblFileProgress.setText(" Files are not Available");

                    }
                    break;
                case 2:
                    file_path = file_path + "Cam3/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {
                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "3" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "3" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                            d = null;
                        }
                        progBar.setVisible(true);
                      //  lblFileProgress.setText("Please wait ...");

                        if (cam_path.getAbsolutePath().contains("Snapshot")) {
                            startCopySnapshot(from_date_int, to_date_int, cam_path);
                        } else {
                            startCopy(from_date_int, to_date_int, cam_path);
                        }
                    } else {
                        lblFileProgress.setText(" Files are not Available");

                    }
                    break;
                case 3:
                    file_path = file_path + "Cam4/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "4" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "4" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                        //lblFileProgress.setText("Please wait ...");
                        startCopy(from_date_int, to_date_int, cam_path);
                    } else {
                        lblFileProgress.setText(" Files are not Available");
                    }
                    break;
                case 4:
                    file_path = file_path + "Cam5/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "5" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "5" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");
                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                      //  lblFileProgress.setText("Please wait ...");
                        startCopy(from_date_int, to_date_int, cam_path);
                    } else {
                        lblFileProgress.setText(" Files are not Available");
                    }
                    break;
                case 5:
                    file_path = file_path + "Cam6/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "6" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "6" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");
                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                       // lblFileProgress.setText("Please wait ...");
                        startCopy(from_date_int, to_date_int, cam_path);
                    } else {
                        lblFileProgress.setText(" Files are not Available");
                    }
                    break;
                case 6:
                    file_path = file_path + "Cam7/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "7" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "7" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");
                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                       // lblFileProgress.setText("Please wait ...");
                        startCopy(from_date_int, to_date_int, cam_path);
                    } else {
                        lblFileProgress.setText(" Files are not Available");
                    }
                    break;
                case 7:
                    file_path = file_path + "Cam8/";
                    if (cmbSubSelectFiles.getSelectedIndex() == 4) {
                        file_path = file_path + "Cont/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 5) {
                        file_path = file_path + "Dig1/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 6) {
                        file_path = file_path + "Dig2/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 7) {
                        file_path = file_path + "Dig3/";
                    } else if (cmbSubSelectFiles.getSelectedIndex() == 8) {
                        file_path = file_path + "Dig4/";
                    }
                    cam_path = new File(file_path);
                    if (cam_path.exists()) {

                        runCmd("sudo chmod -R 777 " + cam_path);
                        final File[] files = cam_path.listFiles();
                        if (files == null) {
                            lblFileProgress.setText(" Files are not Available");

                            return;
                        }
                        String from_date = "8" + dateTimeFrom + "00";

                        from_date_int = Long.parseLong(from_date);
                        String to_date = "8" + dateTimeTo + "00";
                        to_date_int = Long.parseLong(to_date);
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText(" Files are not Available");
                            return;
                        }
                        if (files.length > 10) {
                            Dimension d = new Dimension(500, 50 * files.length);
                            panListFiles.setPreferredSize(d);
                        }
                        progBar.setVisible(true);
                        //lblFileProgress.setText("Please wait ...");
                        startCopy(from_date_int, to_date_int, cam_path);
                    } else {
                        lblFileProgress.setText(" Files are not Available");
                    }
                    break;
                case 8: {
                    File f = new File(media_filepath + "/Log");

                    if (f.exists()) {
                        runCmd("sudo chmod -R 777 " + f);
                        final File[] files = f.listFiles();
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText("No Files to Copy");

                            return;
                        } else {
                            y = 0;
                            if (files.length > 10) {
                                Dimension d = new Dimension(500, 50 * files.length);
                                panListFiles.setPreferredSize(d);
                            }
                            arrayListChkCamFiles.clear();
                            for (i = 0; i < files.length; i++) {
                                final JCheckBox cb1 = new JCheckBox(files[i].getName()); ///files[i].getName()
                                panListFiles.add(cb1);
                                cb1.setBounds(0, y, 450, 20);
                                cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                arrayListChkCamFiles.add(cb1);
                                panListFiles.add(cb1);
                                y += 30;
                            }
                        }
                    } else {
                        lblFileProgress.setText(" Files are not Available");

                    }
                    break;
                }
                case 9: {
                    File f = new File(media_filepath.getAbsolutePath() + "/storeddata");

                    if (f.exists()) {
                        runCmd("sudo chmod -R 777 " + f);
                        final File[] files = f.listFiles();
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText("No Files to Copy");

                            return;
                        } else {
                            y = 0;
                            if (files.length > 10) {
                                Dimension d = new Dimension(500, 50 * files.length);
                                panListFiles.setPreferredSize(d);
                            }
                            for (i = 0; i < files.length; i++) {
                                final JCheckBox cb1 = new JCheckBox(files[i].getName()); ///files[i].getName()
                                panListFiles.add(cb1);
                                cb1.setBounds(0, y, 450, 20);
                                cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                arrayListChkCamFiles.add(cb1);
                                panListFiles.add(cb1);
                                y += 30;
                            }
                        }
                    } else {
                        lblFileProgress.setText(" Files are not Available");

                    }
                    break;
                }
                case 10: {

                    File f = new File(media_filepath + "/Log/CAN");
                    if (f.exists()) {
                        runCmd("sudo chmod -R 777 " + f);
                        final File[] files = f.listFiles();
                        if (files == null || files.length == 0) {
                            lblFileProgress.setText("No Files to Copy");

                            return;
                        } else {
                            y = 0;
                            if (files.length > 10) {
                                Dimension d = new Dimension(500, 50 * files.length);
                                panListFiles.setPreferredSize(d);
                            }
                            for (i = 0; i < files.length; i++) {
                                final JCheckBox cb1 = new JCheckBox(files[i].getName()); ///files[i].getName()
                                panListFiles.add(cb1);
                                cb1.setBounds(0, y, 450, 20);
                                cb1.setFont(new Font("Ubuntu", Font.BOLD, 15));
                                arrayListChkCamFiles.add(cb1);
                                panListFiles.add(cb1);
                                y += 30;
                            }
                        }
                    } else {
                        lblFileProgress.setText(" Files are not Available");

                    }
                    break;
                }

                default:
                    break;
            }
        } catch (Exception e) {

        } finally {
            cam_path = null;
            out_path = null;

            p = null;
        }

    }//GEN-LAST:event_btnListFilesActionPerformed

    private void AllSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllSelectActionPerformed
        // TODO add your handling code here:
        int selectedCounter = 0;
        try {
            Iterator<JCheckBox> iter = arrayListChkCamFiles.iterator();
            while (iter.hasNext()) {
                JCheckBox check = iter.next();
                if (AllSelect.isSelected()) {
                    check.doClick();
                } else {
                    check.setSelected(false);
                    selectedCounter -= 1;
                    if (selectedCounter < 0) {
                        selectedCounter = 0;
                    }
                }

            }
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_AllSelectActionPerformed

    private void btnInstallAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstallAppActionPerformed
        // TODO add your handling code here:
        panCopyTo.setVisible(false);
        btnCopy.setVisible(false);
        btnInstallApp.setBackground(Color.MAGENTA);
        this.btnCopyFilesFromUsb.setBackground(bgcolor);
        this.btnCopyFilesToUsb.setBackground(bgcolor);
        btnLibraries.setBackground(bgcolor);
        lblFileProgress.setText("");
        progBar.setValue(0);
        progBar.setMaximum(10);
        panListFiles.removeAll();
        if (btn_selected == BTN_USB) {
            this.install_application_using_usb();
        } else if (btn_selected == BTN_FTP) {
            installAppWifi();
        }
    }//GEN-LAST:event_btnInstallAppActionPerformed

    private void btnCopyFilesFromUsbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyFilesFromUsbActionPerformed
        // TODO add your handling code here:
        panCopyTo.setVisible(false);
        btnCopy.setVisible(false);
        btnCopyFilesFromUsb.setBackground(Color.MAGENTA);
        this.btnInstallApp.setBackground(bgcolor);
        this.btnCopyFilesToUsb.setBackground(bgcolor);
        btnLibraries.setBackground(bgcolor);
        lblFileProgress.setText("");
        progBar.setValue(0);
        progBar.setMaximum(10);
        if (btn_selected == BTN_USB) {
            this.copyFilesFromUSB();
        } else if (btn_selected == BTN_FTP) {
            this.downloadRouteFilesFromWifi();
        }
    }//GEN-LAST:event_btnCopyFilesFromUsbActionPerformed

    private void btnCopyFilesToUsbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopyFilesToUsbActionPerformed

        btnCopyFilesToUsb.setBackground(Color.MAGENTA);
        this.btnInstallApp.setBackground(bgcolor);
        this.btnCopyFilesFromUsb.setBackground(bgcolor);
        btnLibraries.setBackground(bgcolor);
        lblFileProgress.setText("");
        panCopyTo.setVisible(true);
        btnCopy.setVisible(true);
        panCamDate.setEnabled(true);
        panListFiles.removeAll();

    }//GEN-LAST:event_btnCopyFilesToUsbActionPerformed

    private void cmbSubSelectFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSubSelectFilesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSubSelectFilesActionPerformed

    private void btnLibrariesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibrariesActionPerformed
        // TODO add your handling code here:
        panCopyTo.setVisible(false);
        btnCopy.setVisible(false);
        btnLibraries.setBackground(Color.MAGENTA);
        this.btnCopyFilesFromUsb.setBackground(bgcolor);
        this.btnCopyFilesToUsb.setBackground(bgcolor);
        btnInstallApp.setBackground(bgcolor);
        lblFileProgress.setText("");
        progBar.setValue(0);
        progBar.setMaximum(10);
        panListFiles.removeAll();
        if (btn_selected == BTN_USB) {
            int i = 0;
            String path = null;
            btn_selected = BTN_USB;
            chkWifi.setVisible(false);
            this.panCopyTo.setVisible(false);
            this.panRadBtn.setVisible(true);
            File[] files;
            UserPrincipal p;
            File f = null;
            File[] out_path = usb_filepath.listFiles();
            Arrays.sort(out_path);
            if (out_path == null || out_path.length == 0) {
                path = usb_filepath.getAbsolutePath();
            } else if (out_path.length == 2) {
                for (i = 0; i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                        path = out_path[i].getAbsolutePath();

                        break;
                    } else {
                        path = out_path[i].getAbsolutePath();
                    }
                }
            } else {
                for (i = 0; i < out_path.length; i++) {
                    if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                        try {
                            p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);
                            if (p.toString().equals("root")) {
                                runCmd("sudo rm -rf " + out_path[i]);
                            } else {
                                path = out_path[i].getAbsolutePath();
                            }
                        } catch (IOException ex) {
                        } catch (Exception ex) {
                        }
                    }
                }
            }

            p = null;
            if (path == null) {
                lblFileProgress.setText("Please Insert Pendrive");
                return;
            }
            f = new File(path);
            files = f.listFiles();

            lblFileProgress.setText("");

            progBar.setValue(0);
            lblFileProgress.setText("");
            if (!f.exists() || files == null) {
                lblFileProgress.setText("Please Insert Pendrive");

                return;
            }
            startCopyLibraryFilesUSBThread(path);
        } else if (btn_selected == BTN_FTP) {
            downloadLibraryFilesFromWifi();
        }
    }//GEN-LAST:event_btnLibrariesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AllSelect;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton btnCopy;
    private javax.swing.JButton btnCopyFilesFromUsb;
    private javax.swing.JButton btnCopyFilesToUsb;
    private javax.swing.JButton btnInstallApp;
    private javax.swing.JButton btnLibraries;
    private javax.swing.JButton btnListFiles;
    private javax.swing.JButton btnUSB;
    private javax.swing.JButton btnWifi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkWifi;
    private javax.swing.JComboBox<String> cmbSelectFiles;
    private javax.swing.JComboBox<String> cmbSubSelectFiles;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblFileProgress;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblToDate;
    private javax.swing.JLabel lblwifi;
    private javax.swing.JPanel panCamDate;
    private javax.swing.JPanel panCopyTo;
    private javax.swing.JPanel panFromDateTime;
    private javax.swing.JPanel panListFiles;
    private javax.swing.JPanel panRadBtn;
    private javax.swing.JPanel panToDateTime;
    private javax.swing.JProgressBar progBar;
    private javax.swing.JProgressBar progress;
    // End of variables declaration//GEN-END:variables
}
