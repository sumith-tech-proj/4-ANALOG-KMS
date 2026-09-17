
package obuits;

import com.pi4j.io.gpio.PinState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static obuits.MainFrmIts.DigInp1CheckState;
import static obuits.MainFrmIts.DigInp2CheckState;
import static obuits.MainFrmIts.DigInp3CheckState;
import static obuits.MainFrmIts.DigInp4CheckState;
import static obuits.MainFrmIts.reboot_system;
import static obuits.clsDefines.SLEEP_SHUTDOWN_MODE;
import static obuits.clsDefines.SLEEP_SLEEP_MODE;
import static obuits.clsDefines.Strings.MANUAL_RESET;
import static obuits.clsSharedVariables.driverDetailsCnt;
import static obuits.clsSharedVariables.driverLoginEnabled;

import static obuits.clsSharedVariables.getMainDeviceShutTime;

import static obuits.clsSharedVariables.getOBUID;
import static obuits.clsSharedVariables.getVechicleRegNo;
import static obuits.clsSharedVariables.get_driver_id;
import static obuits.clsSharedVariables.objDriverDetails;
import static obuits.clsSharedVariables.setMainDeviceShutTime;

import static obuits.clsSharedVariables.setOBUID;
import static obuits.clsSharedVariables.setVechicleRegNo;

public class PanDevice extends javax.swing.JPanel {

    String prev_control_name = "";

    boolean driver_login = false;

    public PanDevice() {
        initComponents();
        Date date;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        try {
            lblMsg.setText("");
            lblMsg1.setText("");
            lblMsg2.setText("");
            driver_login = driverLoginEnabled;
            txtDeviceShutTime.setText(String.valueOf(getMainDeviceShutTime()));

            if (clsSharedVariables.getSleepModeState() == SLEEP_SHUTDOWN_MODE) {
                this.cmbSleepMode.setSelectedIndex(1);
            } else {
                this.cmbSleepMode.setSelectedIndex(0);
            }

            txtDeviceOBUId.setText(getOBUID());
            txtVehicleRegNo.setText(getVechicleRegNo());

            if (driverLoginEnabled == true) {
                this.cmbDriverLogin.setSelectedIndex(0);
            } else {
                this.cmbDriverLogin.setSelectedIndex(1);
            }

        } catch (Exception ex) {
        }
        date = null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConfigName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        txtDeviceOBUId = new javax.swing.JTextField();
        lblObuId = new javax.swing.JLabel();
        lblShutSleep = new javax.swing.JLabel();
        lblDriverLogin = new javax.swing.JLabel();
        cmbSleepMode = new javax.swing.JComboBox();
        lblIgnOff = new javax.swing.JLabel();
        txtDeviceShutTime = new javax.swing.JTextField();
        cmbDriverLogin = new javax.swing.JComboBox();
        lblVehicleRegNo = new javax.swing.JLabel();
        txtVehicleRegNo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNewPwd = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        lblConfirmPwd = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnSave1 = new javax.swing.JButton();
        lblMsg1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblChangePwd = new javax.swing.JLabel();
        txtPwd = new javax.swing.JTextField();
        btnSave2 = new javax.swing.JButton();
        lblMsg2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(500, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("SYSTEM");

        btnSave.setBackground(new java.awt.Color(47, 49, 51));
        btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | java.awt.Font.BOLD, btnSave.getFont().getSize()+7));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setAutoscrolls(true);
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setPreferredSize(new java.awt.Dimension(100, 42));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblMsg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Configuration");

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

        jPanel1.setPreferredSize(new java.awt.Dimension(471, 800));

        txtDeviceOBUId.setFont(txtDeviceOBUId.getFont().deriveFont(txtDeviceOBUId.getFont().getStyle() | java.awt.Font.BOLD, txtDeviceOBUId.getFont().getSize()+5));
        txtDeviceOBUId.setForeground(new java.awt.Color(102, 0, 0));
        txtDeviceOBUId.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDeviceOBUId.setText("000009");
        txtDeviceOBUId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDeviceOBUIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDeviceOBUIdFocusLost(evt);
            }
        });
        txtDeviceOBUId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeviceOBUIdActionPerformed(evt);
            }
        });

        lblObuId.setFont(lblObuId.getFont().deriveFont(lblObuId.getFont().getStyle() | java.awt.Font.BOLD, lblObuId.getFont().getSize()+5));
        lblObuId.setForeground(new java.awt.Color(0, 0, 102));
        lblObuId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObuId.setText("OBU ID");
        lblObuId.setPreferredSize(new java.awt.Dimension(222, 30));

        lblShutSleep.setFont(lblShutSleep.getFont().deriveFont(lblShutSleep.getFont().getStyle() | java.awt.Font.BOLD, lblShutSleep.getFont().getSize()+5));
        lblShutSleep.setForeground(new java.awt.Color(0, 0, 102));
        lblShutSleep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblShutSleep.setText("Ignition Off Interval");
        lblShutSleep.setPreferredSize(new java.awt.Dimension(222, 30));

        lblDriverLogin.setFont(lblDriverLogin.getFont().deriveFont(lblDriverLogin.getFont().getStyle() | java.awt.Font.BOLD, lblDriverLogin.getFont().getSize()+5));
        lblDriverLogin.setForeground(new java.awt.Color(0, 0, 102));
        lblDriverLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDriverLogin.setText("Driver Login");
        lblDriverLogin.setPreferredSize(new java.awt.Dimension(222, 30));

        cmbSleepMode.setFont(cmbSleepMode.getFont().deriveFont(cmbSleepMode.getFont().getStyle() | java.awt.Font.BOLD, cmbSleepMode.getFont().getSize()+5));
        cmbSleepMode.setForeground(new java.awt.Color(102, 0, 0));
        cmbSleepMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sleep Mode", "Shutdown" }));

        lblIgnOff.setFont(lblIgnOff.getFont().deriveFont(lblIgnOff.getFont().getStyle() | java.awt.Font.BOLD, lblIgnOff.getFont().getSize()+5));
        lblIgnOff.setForeground(new java.awt.Color(0, 0, 102));
        lblIgnOff.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIgnOff.setText("Ignition Off Condition");
        lblIgnOff.setPreferredSize(new java.awt.Dimension(222, 30));

        txtDeviceShutTime.setFont(txtDeviceShutTime.getFont().deriveFont(txtDeviceShutTime.getFont().getStyle() | java.awt.Font.BOLD, txtDeviceShutTime.getFont().getSize()+5));
        txtDeviceShutTime.setForeground(new java.awt.Color(102, 0, 0));
        txtDeviceShutTime.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDeviceShutTime.setText("30");
        txtDeviceShutTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDeviceShutTimeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDeviceShutTimeFocusLost(evt);
            }
        });
        txtDeviceShutTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDeviceShutTimeActionPerformed(evt);
            }
        });

        cmbDriverLogin.setFont(cmbDriverLogin.getFont().deriveFont(cmbDriverLogin.getFont().getStyle() | java.awt.Font.BOLD, cmbDriverLogin.getFont().getSize()+5));
        cmbDriverLogin.setForeground(new java.awt.Color(102, 0, 0));
        cmbDriverLogin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enable", "Disable" }));
        cmbDriverLogin.setToolTipText("");
        cmbDriverLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDriverLoginActionPerformed(evt);
            }
        });

        lblVehicleRegNo.setFont(lblVehicleRegNo.getFont().deriveFont(lblVehicleRegNo.getFont().getStyle() | java.awt.Font.BOLD, lblVehicleRegNo.getFont().getSize()+5));
        lblVehicleRegNo.setForeground(new java.awt.Color(0, 0, 102));
        lblVehicleRegNo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVehicleRegNo.setText("Vehicle Registration No");
        lblVehicleRegNo.setPreferredSize(new java.awt.Dimension(222, 30));

        txtVehicleRegNo.setFont(txtVehicleRegNo.getFont().deriveFont(txtVehicleRegNo.getFont().getStyle() | java.awt.Font.BOLD, txtVehicleRegNo.getFont().getSize()+5));
        txtVehicleRegNo.setForeground(new java.awt.Color(102, 0, 0));
        txtVehicleRegNo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtVehicleRegNo.setText("TS 36 0A 3339");
        txtVehicleRegNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVehicleRegNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVehicleRegNoFocusLost(evt);
            }
        });
        txtVehicleRegNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVehicleRegNoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 120));
        jLabel1.setText("min");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("DEVICE PASSWORD"));

        lblNewPwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNewPwd.setForeground(new java.awt.Color(0, 0, 102));
        lblNewPwd.setText("New Password");

        txtNewPassword.setFont(txtNewPassword.getFont().deriveFont(txtNewPassword.getFont().getStyle() | java.awt.Font.BOLD, txtNewPassword.getFont().getSize()+7));
        txtNewPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNewPasswordFocusLost(evt);
            }
        });

        lblConfirmPwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblConfirmPwd.setForeground(new java.awt.Color(0, 0, 102));
        lblConfirmPwd.setText("Confirm Password");

        txtConfirmPassword.setFont(txtConfirmPassword.getFont().deriveFont(txtConfirmPassword.getFont().getStyle() | java.awt.Font.BOLD, txtConfirmPassword.getFont().getSize()+7));
        txtConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConfirmPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtConfirmPasswordFocusLost(evt);
            }
        });

        btnSave1.setBackground(new java.awt.Color(47, 49, 51));
        btnSave1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("SAVE");
        btnSave1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        lblMsg1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMsg1.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg1.setText("Configuration");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblNewPwd)
                        .addGap(71, 71, 71)
                        .addComponent(lblConfirmPwd)))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConfirmPwd))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConfirmPassword)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg1)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DRIVER PASSWORD"));

        lblChangePwd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblChangePwd.setForeground(new java.awt.Color(0, 0, 102));
        lblChangePwd.setText("Change Password");

        txtPwd.setFont(txtPwd.getFont().deriveFont(txtPwd.getFont().getStyle() | java.awt.Font.BOLD, txtPwd.getFont().getSize()+7));
        txtPwd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPwdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPwdFocusLost(evt);
            }
        });

        btnSave2.setBackground(new java.awt.Color(47, 49, 51));
        btnSave2.setFont(btnSave2.getFont().deriveFont(btnSave2.getFont().getStyle() | java.awt.Font.BOLD, btnSave2.getFont().getSize()+7));
        btnSave2.setForeground(new java.awt.Color(255, 255, 255));
        btnSave2.setText("SAVE");
        btnSave2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        lblMsg2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMsg2.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg2.setText("Configuration");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblChangePwd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblChangePwd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPwd)
                    .addComponent(btnSave2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMsg2)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDriverLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblObuId, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblIgnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblShutSleep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblVehicleRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSleepMode, 0, 165, Short.MAX_VALUE)
                            .addComponent(txtDeviceOBUId)
                            .addComponent(txtDeviceShutTime)
                            .addComponent(txtVehicleRegNo)
                            .addComponent(cmbDriverLogin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObuId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDeviceOBUId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVehicleRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVehicleRegNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbSleepMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIgnOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDeviceShutTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblShutSleep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDriverLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDriverLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfigName, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblMsg)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(51, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDeviceShutTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeviceShutTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeviceShutTimeActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        lblMsg.setText("");
        clsReadFiles obj = new clsReadFiles();
        if (!getOBUID().equals(txtDeviceOBUId.getText().trim())) {
            obj.write_data_imp_log(getOBUID() + " Prev OBUID Changed Deleted Old Files " + txtDeviceOBUId.getText().trim());
            obj.delete_nonobuid_files(txtDeviceOBUId.getText().trim());
            obj.delete_gps_can_stored_data_file_obu_change();
        }
        setOBUID(txtDeviceOBUId.getText().trim());
        setVechicleRegNo(txtVehicleRegNo.getText().trim());
        setMainDeviceShutTime(Integer.parseInt((txtDeviceShutTime.getText().trim())));
        if (cmbSleepMode.getSelectedIndex() == 0) {
            clsSharedVariables.setSleepModeState(SLEEP_SLEEP_MODE);
        } else {
            clsSharedVariables.setSleepModeState(SLEEP_SHUTDOWN_MODE);
        }

        if (this.cmbDriverLogin.getSelectedIndex() == 0) {
            clsSharedVariables.driverLoginEnabled = true;
        } else {
            clsSharedVariables.driverLoginEnabled = false;
        }

        if (clsSharedVariables.getDigInp1DefaultClosedState() == true) {
            DigInp1CheckState = PinState.HIGH;
        } else {
            DigInp1CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp2DefaultClosedState() == true) {
            DigInp2CheckState = PinState.HIGH;
        } else {
            DigInp2CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp3DefaultClosedState() == true) {
            DigInp3CheckState = PinState.HIGH;
        } else {
            DigInp3CheckState = PinState.LOW;
        }

        if (clsSharedVariables.getDigInp4DefaultClosedState() == true) {
            DigInp4CheckState = PinState.HIGH;
        } else {
            DigInp4CheckState = PinState.LOW;
        }

        obj.write_pid_codes();
        if (obj.write_cfg_data_file()) {
            lblMsg.setText(lblMsg.getText() + " Configuration Saved");
        } else {
            lblMsg.setText(lblMsg.getText() + " Configuration Not Saved");
        }

        if (clsSharedVariables.driverLoginEnabled != driver_login) {
            //show_message_dialogbox("Restart the Unit Driver Login Changed");
           clsReadFiles objReadFiles = new clsReadFiles();
        clsSharedVariables.reset_type = MANUAL_RESET;
        objReadFiles.write_reset_data();
        javax.swing.JPanel Pane = new javax.swing.JPanel();
        JOptionPane pane = new JOptionPane(Pane, JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION) {
            @Override
            public void selectInitialValue() {

            }
        };
        JDialog getKeyBrdDialog = pane.createDialog(null, "Do you want to Restart OBU?, Confirm");
        Dimension d = new Dimension(350, 100);
        getKeyBrdDialog.setSize(d);
        getKeyBrdDialog.setAlwaysOnTop(true);
        getKeyBrdDialog.setVisible(true);
        Object selectedValue = pane.getValue();
        int n = -1;
        if (selectedValue == null) {
            n = JOptionPane.NO_OPTION;
        } else {
            n = Integer.parseInt(selectedValue.toString());

        }
        if (n == JOptionPane.YES_OPTION) {
            objReadFiles.write_data_imp_log("Rebooting system from Device Reset");
            reboot_system(" Reboot Diag");
        }
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    private void show_message_dialogbox(String data) {
        try {
            final JLabel label = new JLabel();
            int timerDelay = 1000;
            label.setText(data);
            label.setBackground(Color.BLUE);
            label.setSize(500, 500);
            new javax.swing.Timer(timerDelay, new ActionListener() {
                int timeLeft = 5;

                public void actionPerformed(ActionEvent e) {
                    if (timeLeft > 0) {
                        timeLeft--;
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
            JOptionPane.showMessageDialog(null, label);
        } catch (Exception ex) {
        }
    }

    private void txtDeviceOBUIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeviceOBUIdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("OBU Id")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("OBU Id", txtDeviceOBUId.getText());
            if (str != null) {
                txtDeviceOBUId.setText(str);
            }
            prev_control_name = "OBU Id";
            obj = null;
            str = null;
            txtDeviceOBUId.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDeviceOBUIdFocusGained

    private void txtDeviceOBUIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeviceOBUIdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDeviceOBUIdFocusLost

    private void txtDeviceShutTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeviceShutTimeFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Shutdown Time")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Shutdown Time", txtDeviceShutTime.getText());
            if (str != null) {
                txtDeviceShutTime.setText(str);
            }
            prev_control_name = "Shutdown Time";
            obj = null;
            str = null;
            txtDeviceShutTime.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDeviceShutTimeFocusGained

    private void txtDeviceShutTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDeviceShutTimeFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDeviceShutTimeFocusLost

    private void txtDeviceOBUIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDeviceOBUIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeviceOBUIdActionPerformed

    private void cmbDriverLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDriverLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDriverLoginActionPerformed

    private void txtVehicleRegNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVehicleRegNoFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Vehicle Registration No")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Vehicle Registration No", txtVehicleRegNo.getText());
            if (str != null) {
                txtVehicleRegNo.setText(str);
            }
            prev_control_name = "Vehicle Registration No";
            obj = null;
            str = null;
            txtVehicleRegNo.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }

    }//GEN-LAST:event_txtVehicleRegNoFocusGained

    private void txtVehicleRegNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVehicleRegNoFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtVehicleRegNoFocusLost

    private void txtVehicleRegNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVehicleRegNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVehicleRegNoActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        Date date;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        try {
            txtDeviceShutTime.setText(String.valueOf(getMainDeviceShutTime()));

            if (clsSharedVariables.getSleepModeState() == SLEEP_SHUTDOWN_MODE) {
                this.cmbSleepMode.setSelectedIndex(1);
            } else {
                this.cmbSleepMode.setSelectedIndex(0);
            }

            txtDeviceOBUId.setText(getOBUID());
            txtVehicleRegNo.setText(getVechicleRegNo());

            if (driverLoginEnabled == true) {
                this.cmbDriverLogin.setSelectedIndex(0);
            } else {
                this.cmbDriverLogin.setSelectedIndex(1);
            }

            lblMsg.setText("");
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtNewPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPasswordFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("New Password")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("New Password", txtNewPassword.getText());
            if (str != null) {
                txtNewPassword.setText(str);
            }
            prev_control_name = "New Password";
            obj = null;
            str = null;
            txtNewPassword.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtNewPasswordFocusGained

    private void txtNewPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPasswordFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
      
    }//GEN-LAST:event_txtNewPasswordFocusLost

    private void txtConfirmPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmPasswordFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Confirm Password")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Confirm Password", txtConfirmPassword.getText());
            if (str != null) {
                txtConfirmPassword.setText(str);
            }
            prev_control_name = "Confirm Password";
            obj = null;
            str = null;
            txtConfirmPassword.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtConfirmPasswordFocusGained

    private void txtConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmPasswordFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
     
    }//GEN-LAST:event_txtConfirmPasswordFocusLost

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed

        if (!txtNewPassword.getText().isEmpty() && !txtConfirmPassword.getText().isEmpty()) {
            if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                clsSharedVariables.configuration_pwd = txtNewPassword.getText();
                clsReadFiles obj = new clsReadFiles();
                obj.write_password_change(txtNewPassword.getText());
                obj = null;
                lblMsg1.setText("Password Changed");

            } else {
                lblMsg1.setText("New And confirm password not matched");

            }
        } else {
            lblMsg1.setText("no password entered");
        }

    }//GEN-LAST:event_btnSave1ActionPerformed

    private void txtPwdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPwdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Password")) {
            String str = getKeyBoardValue("Password", txtPwd.getText());
            if (str != null) {
                txtPwd.setText(str);
            }
            prev_control_name = "Password";
            //obj=null;
            str = null;
            txtPwd.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }

    private void txtDeviceMicShutTimeFocusGained(java.awt.event.FocusEvent evt) {
        
    }//GEN-LAST:event_txtPwdFocusGained

    private void txtPwdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPwdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtPwdFocusLost
    public String getKeyBoardValue(String title, String txt_val) {
        PanKeyBoard panel = new PanKeyBoard(txt_val);

        JOptionPane pane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION) {
            @Override
            public void selectInitialValue() {

            }
        };

        JDialog getKeyBrdDialog = pane.createDialog(null, title);
        getKeyBrdDialog.setVisible(true);
        Object selectedValue = pane.getValue();
        int n = -1;

        if (selectedValue == null) {
            n = JOptionPane.CANCEL_OPTION;
        } else {
            n = Integer.parseInt(selectedValue.toString());
        }

        if (n == JOptionPane.OK_OPTION) {
            return PanKeyBoard.txtKeyBoardData.getText();
        } else {

            return null;
        }
    }

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:

        try {
            if (clsSharedVariables.driverLoginEnabled == true) {
                String driver_id = get_driver_id();

                for (int i = 0; i < driverDetailsCnt; i++) {
                    if (objDriverDetails[i].driver_id.equals(driver_id)) {
                        objDriverDetails[i].pwd = this.txtPwd.getText();
                        break;
                    }
                }

                clsReadFiles objReadFiles = new clsReadFiles();

                objReadFiles.write_driver_details_file();
                objReadFiles = null;
                lblMsg2.setText("Configuration Saved");

            } else {
                lblMsg2.setText("Driver Login Disabled");

            }

        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btnSave2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JComboBox cmbDriverLogin;
    private javax.swing.JComboBox cmbSleepMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChangePwd;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblConfirmPwd;
    private javax.swing.JLabel lblDriverLogin;
    private javax.swing.JLabel lblIgnOff;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblNewPwd;
    private javax.swing.JLabel lblObuId;
    private javax.swing.JLabel lblShutSleep;
    private javax.swing.JLabel lblVehicleRegNo;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtDeviceOBUId;
    private javax.swing.JTextField txtDeviceShutTime;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtPwd;
    private javax.swing.JTextField txtVehicleRegNo;
    // End of variables declaration//GEN-END:variables
}
