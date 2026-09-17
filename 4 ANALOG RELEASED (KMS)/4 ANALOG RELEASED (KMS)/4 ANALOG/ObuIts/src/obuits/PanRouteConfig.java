package obuits;

import java.util.Calendar;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.SCHDEULE_ENABLE;
import static obuits.clsSharedVariables.getCurAutoTripStat;
import static obuits.clsSharedVariables.getCurLatitude;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getNonStopDistance;
import static obuits.clsSharedVariables.getNonStopTime;
import static obuits.clsSharedVariables.getSchRouteEnable;
import static obuits.clsSharedVariables.get_gprs_normal_mode_val;
import static obuits.clsSharedVariables.get_gprs_sleep_val;
import static obuits.clsSharedVariables.setAutoTripStat;
import static obuits.clsSharedVariables.setNonStopDistance;
import static obuits.clsSharedVariables.setNonStopTime;
import static obuits.clsSharedVariables.setSchRouteEnable;
import static obuits.clsSharedVariables.set_gprs_normal_mode_val;
import static obuits.clsSharedVariables.set_gprs_sleep_val;
import static obuits.clsSharedVariables.video_update_rate;

public class PanRouteConfig extends javax.swing.JPanel {

    String prev_control_name = "";

    public PanRouteConfig() {
        initComponents();
        lblMsg.setText("");
        lblMsg1.setText("");
        lblMsg2.setText("");

        txtGprsNormal.setText(String.valueOf(get_gprs_normal_mode_val()));
        txtEmergencyInterval.setText(String.valueOf(clsSharedVariables.getEmergencyStateTimeDuration()));

        txtGprsSleep.setText(String.valueOf(get_gprs_sleep_val()));
        this.txtVideoConn.setText(String.valueOf(video_update_rate));
        this.txtCanUpdateTime.setText(String.valueOf(clsSharedVariables.getCanUpdateTimeInterval()));
        txtNonStopTimeUpdate.setText(String.valueOf(getNonStopTime()));
        this.txtNonStopDistanceUpdate.setText(String.valueOf(getNonStopDistance()));
        lblMsg1.setText("");

        if (getSchRouteEnable() == ROUTE_ENABLE) {

            chkAutoScheduling.setSelected(false);
            chkAutoScheduling.setText("Routebased");

            if (getCurAutoTripStat() == true) {
                chkAutoTripDetect.setSelected(true);
                chkAutoTripDetect.setText("Auto Trip");

            } else {
                chkAutoTripDetect.setSelected(false);
                chkAutoTripDetect.setText("Manual Trip");

            }

        } else {

            chkAutoScheduling.setSelected(true);
            chkAutoScheduling.setText("Schedulebased");

            if (getCurAutoTripStat() == true) {
                chkAutoTripDetect.setSelected(true);
                chkAutoTripDetect.setText("Auto Trip");

            } else {
                chkAutoTripDetect.setSelected(false);
                chkAutoTripDetect.setText("Manual Trip");

            }
        }
        if (clsSharedVariables.getOverSpeedBuzzerEnabled() == true) {
            this.cmbOverSpeedBuzzer.setSelectedIndex(0);
        } else {
            this.cmbOverSpeedBuzzer.setSelectedIndex(1);
        }
        lblMsg.setText("");
        txtOverSpeed.setText(String.valueOf(gpsDriving.over_speed_limit));
        txtHarshBrake.setText(String.valueOf(gpsDriving.harsh_brk_threshold)); //String.valueOf(gpsDriving.harsh_brk_dur_1));
        txtHarshAcce.setText(String.valueOf(gpsDriving.harsh_acc_threshold)); //String.valueOf(gpsDriving.harsh_acc_dur_1));
        // DecimalFormat decimalFormat = new DecimalFormat("00000000.0000");
        txtOdometer.setText(String.valueOf(clsSharedVariables.getTravelledDis())); //String.valueOf(gpsDriving.harsh_acc_dur_1));
        txtGyroAngle.setText(String.valueOf(clsSharedVariables.getGyroAngle()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConfigName = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblNormalMode = new javax.swing.JLabel();
        txtGprsNormal = new javax.swing.JTextField();
        lblSleepMode = new javax.swing.JLabel();
        txtGprsSleep = new javax.swing.JTextField();
        lblVideoConn = new javax.swing.JLabel();
        txtVideoConn = new javax.swing.JTextField();
        lblNonStopTime = new javax.swing.JLabel();
        txtNonStopTimeUpdate = new javax.swing.JTextField();
        lblNonStopDistanceUpdate = new javax.swing.JLabel();
        txtNonStopDistanceUpdate = new javax.swing.JTextField();
        lblCanUpdateTime = new javax.swing.JLabel();
        txtCanUpdateTime = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSleepMode1 = new javax.swing.JLabel();
        txtEmergencyInterval = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblMsg1 = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        lblSchType = new javax.swing.JLabel();
        chkAutoScheduling = new javax.swing.JCheckBox();
        lblAutoTripDetect = new javax.swing.JLabel();
        chkAutoTripDetect = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        lblMsg2 = new javax.swing.JLabel();
        btnSave2 = new javax.swing.JButton();
        lblOverSpeed = new javax.swing.JLabel();
        lblHarshBrk = new javax.swing.JLabel();
        lblHarshAcc = new javax.swing.JLabel();
        lblOdometer = new javax.swing.JLabel();
        lblGyroAngle = new javax.swing.JLabel();
        lblAck3 = new javax.swing.JLabel();
        cmbOverSpeedBuzzer = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtOverSpeed = new javax.swing.JTextField();
        txtHarshBrake = new javax.swing.JTextField();
        txtHarshAcce = new javax.swing.JTextField();
        txtOdometer = new javax.swing.JTextField();
        txtGyroAngle = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(583, 410));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("ROUTE");
        lblConfigName.setPreferredSize(new java.awt.Dimension(550, 32));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(500, 635));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(550, 354));

        lblNormalMode.setFont(lblNormalMode.getFont().deriveFont(lblNormalMode.getFont().getStyle() | java.awt.Font.BOLD, lblNormalMode.getFont().getSize()+3));
        lblNormalMode.setForeground(new java.awt.Color(0, 0, 102));
        lblNormalMode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNormalMode.setText("Normal Mode");
        lblNormalMode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNormalMode.setPreferredSize(new java.awt.Dimension(200, 40));

        txtGprsNormal.setFont(txtGprsNormal.getFont().deriveFont(txtGprsNormal.getFont().getStyle() | java.awt.Font.BOLD, txtGprsNormal.getFont().getSize()+4));
        txtGprsNormal.setForeground(new java.awt.Color(0, 102, 51));
        txtGprsNormal.setText("10");
        txtGprsNormal.setPreferredSize(new java.awt.Dimension(150, 40));
        txtGprsNormal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGprsNormalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGprsNormalFocusLost(evt);
            }
        });
        txtGprsNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGprsNormalActionPerformed(evt);
            }
        });

        lblSleepMode.setFont(lblSleepMode.getFont().deriveFont(lblSleepMode.getFont().getStyle() | java.awt.Font.BOLD, lblSleepMode.getFont().getSize()+3));
        lblSleepMode.setForeground(new java.awt.Color(0, 0, 102));
        lblSleepMode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSleepMode.setText("Sleep Mode");
        lblSleepMode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblSleepMode.setPreferredSize(new java.awt.Dimension(200, 40));

        txtGprsSleep.setFont(txtGprsSleep.getFont().deriveFont(txtGprsSleep.getFont().getStyle() | java.awt.Font.BOLD, txtGprsSleep.getFont().getSize()+4));
        txtGprsSleep.setForeground(new java.awt.Color(0, 102, 51));
        txtGprsSleep.setText("15");
        txtGprsSleep.setPreferredSize(new java.awt.Dimension(150, 40));
        txtGprsSleep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGprsSleepFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGprsSleepFocusLost(evt);
            }
        });
        txtGprsSleep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGprsSleepActionPerformed(evt);
            }
        });

        lblVideoConn.setFont(lblVideoConn.getFont().deriveFont(lblVideoConn.getFont().getStyle() | java.awt.Font.BOLD, lblVideoConn.getFont().getSize()+3));
        lblVideoConn.setForeground(new java.awt.Color(0, 0, 102));
        lblVideoConn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVideoConn.setText("Health Mode");
        lblVideoConn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblVideoConn.setPreferredSize(new java.awt.Dimension(200, 40));

        txtVideoConn.setFont(txtVideoConn.getFont().deriveFont(txtVideoConn.getFont().getStyle() | java.awt.Font.BOLD, txtVideoConn.getFont().getSize()+4));
        txtVideoConn.setForeground(new java.awt.Color(0, 102, 51));
        txtVideoConn.setText("10");
        txtVideoConn.setPreferredSize(new java.awt.Dimension(150, 40));
        txtVideoConn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVideoConnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVideoConnFocusLost(evt);
            }
        });
        txtVideoConn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVideoConnActionPerformed(evt);
            }
        });

        lblNonStopTime.setFont(lblNonStopTime.getFont().deriveFont(lblNonStopTime.getFont().getStyle() | java.awt.Font.BOLD, lblNonStopTime.getFont().getSize()+3));
        lblNonStopTime.setForeground(new java.awt.Color(0, 0, 102));
        lblNonStopTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNonStopTime.setText("Bus Idle time Mode");
        lblNonStopTime.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNonStopTime.setPreferredSize(new java.awt.Dimension(200, 40));

        txtNonStopTimeUpdate.setFont(txtNonStopTimeUpdate.getFont().deriveFont(txtNonStopTimeUpdate.getFont().getStyle() | java.awt.Font.BOLD, txtNonStopTimeUpdate.getFont().getSize()+4));
        txtNonStopTimeUpdate.setForeground(new java.awt.Color(0, 102, 51));
        txtNonStopTimeUpdate.setPreferredSize(new java.awt.Dimension(150, 40));
        txtNonStopTimeUpdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNonStopTimeUpdateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNonStopTimeUpdateFocusLost(evt);
            }
        });
        txtNonStopTimeUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNonStopTimeUpdateActionPerformed(evt);
            }
        });

        lblNonStopDistanceUpdate.setFont(lblNonStopDistanceUpdate.getFont().deriveFont(lblNonStopDistanceUpdate.getFont().getStyle() | java.awt.Font.BOLD, lblNonStopDistanceUpdate.getFont().getSize()+3));
        lblNonStopDistanceUpdate.setForeground(new java.awt.Color(0, 0, 102));
        lblNonStopDistanceUpdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNonStopDistanceUpdate.setText("Non Stop Distance");
        lblNonStopDistanceUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNonStopDistanceUpdate.setPreferredSize(new java.awt.Dimension(200, 40));

        txtNonStopDistanceUpdate.setFont(txtNonStopDistanceUpdate.getFont().deriveFont(txtNonStopDistanceUpdate.getFont().getStyle() | java.awt.Font.BOLD, txtNonStopDistanceUpdate.getFont().getSize()+4));
        txtNonStopDistanceUpdate.setForeground(new java.awt.Color(0, 102, 51));
        txtNonStopDistanceUpdate.setPreferredSize(new java.awt.Dimension(150, 40));
        txtNonStopDistanceUpdate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNonStopDistanceUpdateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNonStopDistanceUpdateFocusLost(evt);
            }
        });
        txtNonStopDistanceUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNonStopDistanceUpdateActionPerformed(evt);
            }
        });

        lblCanUpdateTime.setFont(lblCanUpdateTime.getFont().deriveFont(lblCanUpdateTime.getFont().getStyle() | java.awt.Font.BOLD, lblCanUpdateTime.getFont().getSize()+3));
        lblCanUpdateTime.setForeground(new java.awt.Color(0, 0, 102));
        lblCanUpdateTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCanUpdateTime.setText("Can Mode");
        lblCanUpdateTime.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblCanUpdateTime.setPreferredSize(new java.awt.Dimension(200, 40));

        txtCanUpdateTime.setFont(txtCanUpdateTime.getFont().deriveFont(txtCanUpdateTime.getFont().getStyle() | java.awt.Font.BOLD, txtCanUpdateTime.getFont().getSize()+4));
        txtCanUpdateTime.setForeground(new java.awt.Color(0, 102, 51));
        txtCanUpdateTime.setPreferredSize(new java.awt.Dimension(150, 40));
        txtCanUpdateTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCanUpdateTimeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCanUpdateTimeFocusLost(evt);
            }
        });
        txtCanUpdateTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCanUpdateTimeActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(47, 49, 51));
        btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | java.awt.Font.BOLD, btnSave.getFont().getSize()+7));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblMsg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Configuration Saved");
        lblMsg.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnRefresh.setBackground(new java.awt.Color(47, 49, 51));
        btnRefresh.setFont(btnRefresh.getFont().deriveFont(btnRefresh.getFont().getStyle() | java.awt.Font.BOLD, btnRefresh.getFont().getSize()+7));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setAutoscrolls(true);
        btnRefresh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setPreferredSize(new java.awt.Dimension(110, 42));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("min");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("sec");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("sec");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("min");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("min");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("sec");

        lblSleepMode1.setFont(lblSleepMode1.getFont().deriveFont(lblSleepMode1.getFont().getStyle() | java.awt.Font.BOLD, lblSleepMode1.getFont().getSize()+3));
        lblSleepMode1.setForeground(new java.awt.Color(0, 0, 102));
        lblSleepMode1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSleepMode1.setText("Emergency Mode");
        lblSleepMode1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblSleepMode1.setPreferredSize(new java.awt.Dimension(200, 40));

        txtEmergencyInterval.setFont(txtEmergencyInterval.getFont().deriveFont(txtEmergencyInterval.getFont().getStyle() | java.awt.Font.BOLD, txtEmergencyInterval.getFont().getSize()+4));
        txtEmergencyInterval.setForeground(new java.awt.Color(0, 102, 51));
        txtEmergencyInterval.setText("10");
        txtEmergencyInterval.setPreferredSize(new java.awt.Dimension(150, 40));
        txtEmergencyInterval.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmergencyIntervalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmergencyIntervalFocusLost(evt);
            }
        });
        txtEmergencyInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmergencyIntervalActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("sec");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblNormalMode, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                            .addComponent(lblSleepMode, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblVideoConn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblSleepMode1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNonStopTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                        .addComponent(lblNonStopDistanceUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(lblCanUpdateTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmergencyInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNonStopDistanceUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtNonStopTimeUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtVideoConn, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtGprsSleep, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtGprsNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(txtCanUpdateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNormalMode, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGprsNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSleepMode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGprsSleep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtVideoConn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(lblVideoConn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCanUpdateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(lblCanUpdateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSleepMode1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmergencyInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNonStopTimeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(lblNonStopTime, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNonStopDistanceUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNonStopDistanceUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MODE CONFIG", jPanel1);

        jPanel3.setPreferredSize(new java.awt.Dimension(480, 600));

        lblMsg1.setFont(lblMsg1.getFont().deriveFont(lblMsg1.getFont().getStyle() | java.awt.Font.BOLD, lblMsg1.getFont().getSize()+7));
        lblMsg1.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg1.setText("Configuration Saved");

        btnSave1.setBackground(new java.awt.Color(47, 49, 51));
        btnSave1.setFont(btnSave1.getFont().deriveFont(btnSave1.getFont().getStyle() | java.awt.Font.BOLD, btnSave1.getFont().getSize()+7));
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("SAVE");
        btnSave1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        lblSchType.setFont(lblSchType.getFont().deriveFont(lblSchType.getFont().getStyle() | java.awt.Font.BOLD, lblSchType.getFont().getSize()+5));
        lblSchType.setForeground(new java.awt.Color(0, 51, 102));
        lblSchType.setText("Schedule Type ");

        chkAutoScheduling.setFont(chkAutoScheduling.getFont().deriveFont(chkAutoScheduling.getFont().getStyle() | java.awt.Font.BOLD, chkAutoScheduling.getFont().getSize()+5));
        chkAutoScheduling.setForeground(new java.awt.Color(0, 0, 102));
        chkAutoScheduling.setText("Route  Based");
        chkAutoScheduling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAutoSchedulingActionPerformed(evt);
            }
        });

        lblAutoTripDetect.setFont(lblAutoTripDetect.getFont().deriveFont(lblAutoTripDetect.getFont().getStyle() | java.awt.Font.BOLD, lblAutoTripDetect.getFont().getSize()+5));
        lblAutoTripDetect.setForeground(new java.awt.Color(0, 0, 102));
        lblAutoTripDetect.setText("Auto Trip Detection");

        chkAutoTripDetect.setFont(chkAutoTripDetect.getFont().deriveFont(chkAutoTripDetect.getFont().getStyle() | java.awt.Font.BOLD, chkAutoTripDetect.getFont().getSize()+5));
        chkAutoTripDetect.setForeground(new java.awt.Color(0, 0, 102));
        chkAutoTripDetect.setText("Manual");
        chkAutoTripDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAutoTripDetectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSchType, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAutoTripDetect, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAutoScheduling)
                            .addComponent(chkAutoTripDetect)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAutoTripDetect)
                        .addComponent(chkAutoTripDetect))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkAutoScheduling)
                            .addComponent(lblSchType))
                        .addGap(50, 50, 50)))
                .addGap(44, 44, 44)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("SCHEDULE CONFIG", jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 500));

        lblMsg2.setFont(lblMsg2.getFont().deriveFont(lblMsg2.getFont().getStyle() | java.awt.Font.BOLD, lblMsg2.getFont().getSize()+7));
        lblMsg2.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg2.setText("Configuration Saved");

        btnSave2.setBackground(new java.awt.Color(47, 49, 51));
        btnSave2.setFont(btnSave2.getFont().deriveFont(btnSave2.getFont().getStyle() | java.awt.Font.BOLD, btnSave2.getFont().getSize()+7));
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setText("SAVE");
        btnSave2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave2.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        lblOverSpeed.setFont(lblOverSpeed.getFont().deriveFont(lblOverSpeed.getFont().getStyle() | java.awt.Font.BOLD, lblOverSpeed.getFont().getSize()+5));
        lblOverSpeed.setForeground(new java.awt.Color(0, 0, 102));
        lblOverSpeed.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOverSpeed.setText("Overspeed Limit");

        lblHarshBrk.setFont(lblHarshBrk.getFont().deriveFont(lblHarshBrk.getFont().getStyle() | java.awt.Font.BOLD, lblHarshBrk.getFont().getSize()+5));
        lblHarshBrk.setForeground(new java.awt.Color(0, 0, 102));
        lblHarshBrk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHarshBrk.setText("Harsh Brake");

        lblHarshAcc.setFont(lblHarshAcc.getFont().deriveFont(lblHarshAcc.getFont().getStyle() | java.awt.Font.BOLD, lblHarshAcc.getFont().getSize()+5));
        lblHarshAcc.setForeground(new java.awt.Color(0, 0, 102));
        lblHarshAcc.setText("Harsh Acceleration ");

        lblOdometer.setFont(lblOdometer.getFont().deriveFont(lblOdometer.getFont().getStyle() | java.awt.Font.BOLD, lblOdometer.getFont().getSize()+5));
        lblOdometer.setForeground(new java.awt.Color(0, 0, 102));
        lblOdometer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOdometer.setText("Odometer Reading");

        lblGyroAngle.setFont(lblGyroAngle.getFont().deriveFont(lblGyroAngle.getFont().getStyle() | java.awt.Font.BOLD, lblGyroAngle.getFont().getSize()+5));
        lblGyroAngle.setForeground(new java.awt.Color(0, 0, 102));
        lblGyroAngle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGyroAngle.setText("Gyro Angle");

        lblAck3.setFont(lblAck3.getFont().deriveFont(lblAck3.getFont().getStyle() | java.awt.Font.BOLD, lblAck3.getFont().getSize()+5));
        lblAck3.setForeground(new java.awt.Color(0, 0, 102));
        lblAck3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAck3.setText("Over Speed Buzzer");
        lblAck3.setPreferredSize(new java.awt.Dimension(222, 30));

        cmbOverSpeedBuzzer.setFont(cmbOverSpeedBuzzer.getFont().deriveFont(cmbOverSpeedBuzzer.getFont().getStyle() | java.awt.Font.BOLD, cmbOverSpeedBuzzer.getFont().getSize()+5));
        cmbOverSpeedBuzzer.setForeground(new java.awt.Color(102, 0, 0));
        cmbOverSpeedBuzzer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enable", "Disable" }));
        cmbOverSpeedBuzzer.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 120));
        jLabel8.setText("kmph");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 120));
        jLabel9.setText("kmph");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 120));
        jLabel10.setText("kmph");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 120));
        jLabel11.setText("m");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 120));
        jLabel12.setText("degree");

        txtOverSpeed.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtOverSpeed.setForeground(new java.awt.Color(0, 102, 51));
        txtOverSpeed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOverSpeedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOverSpeedFocusLost(evt);
            }
        });
        txtOverSpeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOverSpeedActionPerformed(evt);
            }
        });

        txtHarshBrake.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtHarshBrake.setForeground(new java.awt.Color(0, 102, 51));
        txtHarshBrake.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHarshBrakeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHarshBrakeFocusLost(evt);
            }
        });

        txtHarshAcce.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtHarshAcce.setForeground(new java.awt.Color(0, 102, 51));
        txtHarshAcce.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHarshAcceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHarshAcceFocusLost(evt);
            }
        });

        txtOdometer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtOdometer.setForeground(new java.awt.Color(0, 102, 51));
        txtOdometer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOdometerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOdometerFocusLost(evt);
            }
        });

        txtGyroAngle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtGyroAngle.setForeground(new java.awt.Color(0, 102, 51));
        txtGyroAngle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGyroAngleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGyroAngleFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHarshBrk, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOverSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOdometer, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGyroAngle, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAck3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHarshAcc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOverSpeed)
                            .addComponent(cmbOverSpeedBuzzer, javax.swing.GroupLayout.Alignment.TRAILING, 0, 107, Short.MAX_VALUE)
                            .addComponent(txtHarshBrake)
                            .addComponent(txtHarshAcce, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtOdometer)
                            .addComponent(txtGyroAngle, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lblMsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOverSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOverSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHarshBrk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtHarshBrake, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHarshAcc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtHarshAcce, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOdometer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtOdometer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGyroAngle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtGyroAngle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAck3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbOverSpeedBuzzer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DRIVE PARAMETERS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtGprsNormalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGprsNormalFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Normal mode")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Normal mode", txtGprsNormal.getText());
            if (str != null) {
                txtGprsNormal.setText(str);
            }
            prev_control_name = "Normal mode";
            obj = null;
            str = null;
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtGprsNormalFocusGained

    private void txtGprsNormalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGprsNormalFocusLost

        prev_control_name = "";

    }//GEN-LAST:event_txtGprsNormalFocusLost

    private void txtGprsNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGprsNormalActionPerformed
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtGprsNormalActionPerformed

    private void txtGprsSleepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGprsSleepFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Sleep mode")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Sleep mode", txtGprsSleep.getText());
            if (str != null) {
                txtGprsSleep.setText(str);
            }
            prev_control_name = "Sleep mode";
            obj = null;
            str = null;
            txtGprsSleep.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtGprsSleepFocusGained

    private void txtGprsSleepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGprsSleepFocusLost
        // TODO add your handling code here:

        prev_control_name = "";

    }//GEN-LAST:event_txtGprsSleepFocusLost

    private void txtGprsSleepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGprsSleepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGprsSleepActionPerformed

    private void txtVideoConnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVideoConnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Video Conn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Video Conn", txtVideoConn.getText());
            if (str != null) {
                txtVideoConn.setText(str);
            }
            prev_control_name = "Video Conn";
            obj = null;
            str = null;
            txtVideoConn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtVideoConnFocusGained

    private void txtVideoConnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVideoConnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtVideoConnFocusLost

    private void txtVideoConnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVideoConnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVideoConnActionPerformed

    private void txtNonStopTimeUpdateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNonStopTimeUpdateFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Non Stoppage Update Time")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Non Stoppage Update Time", txtNonStopTimeUpdate.getText());
            if (str != null) {
                txtNonStopTimeUpdate.setText(str);
            }
            prev_control_name = "Non Stoppage Update Time";
            obj = null;
            str = null;
            txtNonStopTimeUpdate.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtNonStopTimeUpdateFocusGained

    private void txtNonStopTimeUpdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNonStopTimeUpdateFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtNonStopTimeUpdateFocusLost

    private void txtNonStopTimeUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNonStopTimeUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNonStopTimeUpdateActionPerformed

    private void txtNonStopDistanceUpdateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNonStopDistanceUpdateFocusGained
        // TODO add your handling code here:

        if (!prev_control_name.equals("Non Stop Distance")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Non Stop Distance", txtNonStopDistanceUpdate.getText());
            if (str != null) {
                txtNonStopDistanceUpdate.setText(str);
            }
            prev_control_name = "Non Stop Distance";
            obj = null;
            str = null;
            txtNonStopDistanceUpdate.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtNonStopDistanceUpdateFocusGained

    private void txtNonStopDistanceUpdateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNonStopDistanceUpdateFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtNonStopDistanceUpdateFocusLost

    private void txtNonStopDistanceUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNonStopDistanceUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNonStopDistanceUpdateActionPerformed

    private void txtCanUpdateTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCanUpdateTimeFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Can update time")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Can update time", txtCanUpdateTime.getText());
            if (str != null) {
                txtCanUpdateTime.setText(str);
            }
            prev_control_name = "Can update time";
            obj = null;
            str = null;
            txtCanUpdateTime.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCanUpdateTimeFocusGained

    private void txtCanUpdateTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCanUpdateTimeFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtCanUpdateTimeFocusLost

    private void txtCanUpdateTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCanUpdateTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCanUpdateTimeActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        short gprs_nor = 0;
        short gprs_sleep = 0;
        int non_stop_time_dur = 0;

        clsReadFiles obj = new clsReadFiles();
        try {

            gprs_nor = Short.parseShort((txtGprsNormal.getText()));
            set_gprs_normal_mode_val(gprs_nor);

            clsSharedVariables.setEmergencyStateTimeDuration(Byte.parseByte(this.txtEmergencyInterval.getText()));
            gprs_sleep = Short.parseShort((txtGprsSleep.getText()));
            set_gprs_sleep_val(gprs_sleep);
            clsSharedVariables.video_update_rate = Short.parseShort((txtVideoConn.getText()));

            clsSharedVariables.setCanUpdateTimeInterval(Short.parseShort(this.txtCanUpdateTime.getText()));

            non_stop_time_dur = Integer.parseInt((txtNonStopTimeUpdate.getText()));
            setNonStopTime(non_stop_time_dur);

            non_stop_time_dur = Integer.parseInt((txtNonStopDistanceUpdate.getText()));
            setNonStopDistance(non_stop_time_dur);

        } catch (NumberFormatException ex) {
            lblMsg.setText("Error!  " + ex.getMessage());
            return;
        }

        if (obj.write_cfg_data_file()) {
            lblMsg.setText("Configuration saved");
        } else {
            lblMsg.setText("Server Details Not Saved");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:

        lblMsg.setText("");
        txtGprsNormal.setText(String.valueOf(get_gprs_normal_mode_val()));
        txtEmergencyInterval.setText(String.valueOf(clsSharedVariables.getEmergencyStateTimeDuration()));
        txtGprsSleep.setText(String.valueOf(get_gprs_sleep_val()));
        this.txtVideoConn.setText(String.valueOf(video_update_rate));
        this.txtCanUpdateTime.setText(String.valueOf(clsSharedVariables.getCanUpdateTimeInterval()));
        txtNonStopTimeUpdate.setText(String.valueOf(getNonStopTime()));
        this.txtNonStopDistanceUpdate.setText(String.valueOf(getNonStopDistance()));

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtEmergencyIntervalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmergencyIntervalFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Emergency interval")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Emergency interval", txtEmergencyInterval.getText());
            if (str != null) {
                txtEmergencyInterval.setText(str);
            }
            prev_control_name = "Emergency interval";
            obj = null;
            str = null;
            txtEmergencyInterval.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtEmergencyIntervalFocusGained

    private void txtEmergencyIntervalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmergencyIntervalFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtEmergencyIntervalFocusLost

    private void txtEmergencyIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmergencyIntervalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmergencyIntervalActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:

        clsReadFiles objReadFiles = new clsReadFiles();

        if (chkAutoScheduling.isSelected()) {
            setSchRouteEnable(SCHDEULE_ENABLE);
        } else {
            setSchRouteEnable(ROUTE_ENABLE);
        }

        if (chkAutoTripDetect.isSelected()) {
            setAutoTripStat(chkAutoTripDetect.isSelected());
        } else {
            setAutoTripStat(false);
        }
        if (objReadFiles.write_cfg_data_file()) {
            this.lblMsg1.setText("Configuration Saved");

        } else {
            this.lblMsg1.setText("Configuration Not Saved");

        }

    }//GEN-LAST:event_btnSave1ActionPerformed

    private void chkAutoSchedulingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAutoSchedulingActionPerformed
        // TODO add your handling code here:
        if (chkAutoScheduling.isSelected()) {
            chkAutoScheduling.setText("Schedulebased");

        } else {
            chkAutoScheduling.setText("Routebased");

        }
    }//GEN-LAST:event_chkAutoSchedulingActionPerformed

    private void chkAutoTripDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAutoTripDetectActionPerformed
        // TODO add your handling code here:
        if (chkAutoTripDetect.isSelected()) {
            chkAutoTripDetect.setText("Auto Trip");

        } else {
            chkAutoTripDetect.setText("Manual Trip");

        }
    }//GEN-LAST:event_chkAutoTripDetectActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:

        prev_control_name = "Normal mode";
        prev_control_name = "OverSpeed";


    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
        short over_speed = Short.parseShort(txtOverSpeed.getText().toString());
        short harsh_brk_threshold = Short.parseShort(txtHarshBrake.getText().toString());
        short harsh_acc_threshold = Short.parseShort(txtHarshAcce.getText().toString());
        gpsDriving.over_speed_limit = over_speed;
        gpsDriving.harsh_brk_threshold = harsh_brk_threshold;
        gpsDriving.harsh_acc_threshold = harsh_acc_threshold;
        try {
            clsSharedVariables.setTravelledDis(Double.parseDouble(txtOdometer.getText().toString()));
        } catch (Exception ex) {
            lblMsg.setText("Error! " + ex.getMessage());
            return;
        }
        if (this.cmbOverSpeedBuzzer.getSelectedIndex() == 0) {
            clsSharedVariables.setOverSpeedBuzzerEnabled(true);
        } else {
            clsSharedVariables.setOverSpeedBuzzerEnabled(false);
        }

        clsSharedVariables.setGyroAngle(Short.parseShort(txtGyroAngle.getText().toString()));

        clsReadFiles obj = new clsReadFiles();
        obj.write_odometer_distance_file(clsSharedVariables.getTravelledDis(), getCurLatitude(), getCurLongitude(), Calendar.getInstance().getTimeInMillis());
        obj.write_odometer_distance_alt_file(clsSharedVariables.getTravelledDis(), getCurLatitude(), getCurLongitude(), Calendar.getInstance().getTimeInMillis());

        if (obj.write_cfg_data_file()) {
            lblMsg2.setText("Driving Details Saved");

        } else {
            lblMsg2.setText("Driving Details Not Saved");

            obj = null;
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void txtOverSpeedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOverSpeedFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("OverSpeed")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("OverSpeed", txtOverSpeed.getText());
            if (str != null) {
                txtOverSpeed.setText(str);
            }
            prev_control_name = "OverSpeed";
            obj = null;
            str = null;
            txtOverSpeed.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtOverSpeedFocusGained

    private void txtOverSpeedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOverSpeedFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtOverSpeedFocusLost

    private void txtHarshBrakeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHarshBrakeFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtHarshBrakeFocusLost

    private void txtHarshBrakeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHarshBrakeFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Harsh Brake")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Harsh Brake", txtHarshBrake.getText());
            if (str != null) {
                txtHarshBrake.setText(str);
            }
            prev_control_name = "Harsh Brake";
            obj = null;
            str = null;
            txtHarshBrake.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtHarshBrakeFocusGained

    private void txtHarshAcceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHarshAcceFocusLost
        // TODO add your handling code here:

        prev_control_name = "";

    }//GEN-LAST:event_txtHarshAcceFocusLost

    private void txtHarshAcceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHarshAcceFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Harsh Acceleration")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Harsh Acceleration", txtHarshAcce.getText());
            if (str != null) {
                txtHarshAcce.setText(str);
            }
            prev_control_name = "Harsh Acceleration";
            obj = null;
            str = null;
            txtHarshAcce.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtHarshAcceFocusGained

    private void txtOdometerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOdometerFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtOdometerFocusLost

    private void txtOdometerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOdometerFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Odometer")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Odometer", txtOdometer.getText());
            if (str != null) {
                txtOdometer.setText(str);
            }
            prev_control_name = "Odometer";
            obj = null;
            str = null;
            txtOdometer.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtOdometerFocusGained

    private void txtGyroAngleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGyroAngleFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtGyroAngleFocusLost

    private void txtGyroAngleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGyroAngleFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("GYROANGLE")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("GYROANGLE", txtGyroAngle.getText());
            if (str != null) {
                txtGyroAngle.setText(str);
            }
            prev_control_name = "GYROANGLE";
            obj = null;
            str = null;
            txtGyroAngle.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtGyroAngleFocusGained

    private void txtOverSpeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOverSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOverSpeedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JCheckBox chkAutoScheduling;
    private javax.swing.JCheckBox chkAutoTripDetect;
    private javax.swing.JComboBox cmbOverSpeedBuzzer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAck3;
    private javax.swing.JLabel lblAutoTripDetect;
    private javax.swing.JLabel lblCanUpdateTime;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblGyroAngle;
    private javax.swing.JLabel lblHarshAcc;
    private javax.swing.JLabel lblHarshBrk;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblNonStopDistanceUpdate;
    private javax.swing.JLabel lblNonStopTime;
    private javax.swing.JLabel lblNormalMode;
    private javax.swing.JLabel lblOdometer;
    private javax.swing.JLabel lblOverSpeed;
    private javax.swing.JLabel lblSchType;
    private javax.swing.JLabel lblSleepMode;
    private javax.swing.JLabel lblSleepMode1;
    private javax.swing.JLabel lblVideoConn;
    private javax.swing.JTextField txtCanUpdateTime;
    private javax.swing.JTextField txtEmergencyInterval;
    private javax.swing.JTextField txtGprsNormal;
    private javax.swing.JTextField txtGprsSleep;
    private javax.swing.JTextField txtGyroAngle;
    private javax.swing.JTextField txtHarshAcce;
    private javax.swing.JTextField txtHarshBrake;
    private javax.swing.JTextField txtNonStopDistanceUpdate;
    private javax.swing.JTextField txtNonStopTimeUpdate;
    private javax.swing.JTextField txtOdometer;
    private javax.swing.JTextField txtOverSpeed;
    private javax.swing.JTextField txtVideoConn;
    // End of variables declaration//GEN-END:variables
}
