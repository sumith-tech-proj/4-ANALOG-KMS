package obuits;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import static obuits.clsSharedVariables.ftpPassword;
import static obuits.clsSharedVariables.ftp_ipaddress;
import static obuits.clsSharedVariables.ftpusername;
import static obuits.clsSharedVariables.getIpAddr1;
import static obuits.clsSharedVariables.getIpAddr2;
import static obuits.clsSharedVariables.getIpAddr3;
import static obuits.clsSharedVariables.getPortNo1;
import static obuits.clsSharedVariables.getPortNo2;
import static obuits.clsSharedVariables.getPortNo3;
import static obuits.clsSharedVariables.setIpAddr1;
import static obuits.clsSharedVariables.setIpAddr1Enable;
import static obuits.clsSharedVariables.setIpAddr2;
import static obuits.clsSharedVariables.setIpAddr2Enable;
import static obuits.clsSharedVariables.setIpAddr3;
import static obuits.clsSharedVariables.setIpAddr3Enable;
import static obuits.clsSharedVariables.setIpAddr4Enable;
import static obuits.clsSharedVariables.setIpAddr5Enable;
import static obuits.clsSharedVariables.setPortNo1;
import static obuits.clsSharedVariables.setPortNo2;
import static obuits.clsSharedVariables.setPortNo3;
import static obuits.clsSharedVariables.setServer2ConfigStatus;
import static obuits.clsSharedVariables.setServer3ConfigStatus;
import static obuits.clsSharedVariables.setServerConfigStatus;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.restart_module_ports_changed;
import static obuits.PanGpsDiag.txtData;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsGprsService.GET_NETWORK_UUID_PATH;
import static obuits.clsSharedVariables.getFtpPortNo;
import static obuits.clsSharedVariables.getGsmModuleOn;
import static obuits.clsSharedVariables.getSimReady;
import static obuits.clsSharedVariables.setIp5Protocol;

public class PanServer extends javax.swing.JPanel {

    String prev_control_name = "";

    public PanServer() {
        initComponents();
        int i;
        lblMsg.setText("");
        lblMsg1.setText("");
        lblMsg2.setText("");
        this.txtSsid.setText(clsSharedVariables.wifi_ssid);
        txtPwd.setText(clsSharedVariables.wifi_password);
        btnResetModule.setVisible(false);
        btnNetworkName.setVisible(false);
        lblNetBal.setVisible(false);
        txtNetBalance.setVisible(false);
        txtIpAddr.setText(String.valueOf(getIpAddr1()));
        txtPortNo.setText(String.valueOf(getPortNo1()));
        txtIpAddr2.setText(String.valueOf(getIpAddr2()));
        txtPortNo2.setText(String.valueOf(getPortNo2()));
        this.chkFirstIpEnable.setSelected(clsSharedVariables.getIpAddr1Enable());
        this.chkSecondIpEnable.setSelected(clsSharedVariables.getIpAddr2Enable());
//        this.chkThirdIpEnable.setSelected(clsSharedVariables.getIpAddr3Enable());
//        this.chkFourthIpEnable.setSelected(clsSharedVariables.getIpAddr4Enable());
//        this.chkFifthIpEnable.setSelected(clsSharedVariables.getIpAddr5Enable());
//        txtIpAddr3.setText(String.valueOf(getIpAddr3()));
//        txtPortNo3.setText(String.valueOf(getPortNo3()));
//        txtIpAddr4.setText(String.valueOf(clsSharedVariables.getIpAddr4()));
//        txtPortNo4.setText(String.valueOf(clsSharedVariables.getPortNo4()));
//        txtIpAddr5.setText(String.valueOf(clsSharedVariables.getIpAddr5()));
//        txtPortNo5.setText(String.valueOf(clsSharedVariables.getPortNo5()));
        txtApn.setText(String.valueOf(clsSharedVariables.getApn()));

        txtFtpIpAddr.setText(String.valueOf(ftp_ipaddress));
        txtFtpUsername.setText(String.valueOf(ftpusername));
        txtFtpPwd.setText(String.valueOf(ftpPassword));
        txtFtpPort.setText(String.valueOf(getFtpPortNo()));

//        if (clsSharedVariables.getIp5Protocol() == clsDefines.AIS140) {
//            this.cmdIp5.setSelectedIndex(0);
//        } else if (clsSharedVariables.getIp5Protocol() == clsDefines.SURAT) {
//            this.cmdIp5.setSelectedIndex(1);
//        }

        if (chkFirstIpEnable.isSelected()) {
            txtIpAddr.setEnabled(true);
            txtPortNo.setEnabled(true);
        } else {
            txtIpAddr.setText("");
            txtPortNo.setText("0");
            txtIpAddr.setEnabled(false);
            txtPortNo.setEnabled(false);
        }
        if (chkSecondIpEnable.isSelected()) {
            txtIpAddr2.setEnabled(true);
            txtPortNo2.setEnabled(true);
        } else {
            txtIpAddr2.setText("");
            txtPortNo2.setText("0");
            txtIpAddr2.setEnabled(false);
            txtPortNo2.setEnabled(false);
        }
//        if (chkThirdIpEnable.isSelected()) {
//            txtIpAddr3.setEnabled(true);
//            txtPortNo3.setEnabled(true);
//        } else {
//            txtIpAddr3.setText("");
//            txtPortNo3.setText("0");
//            txtIpAddr3.setEnabled(false);
//            txtPortNo3.setEnabled(false);
//        }
//        if (chkFourthIpEnable.isSelected()) {
//            txtIpAddr4.setEnabled(true);
//            txtPortNo4.setEnabled(true);
//        } else {
//            txtIpAddr4.setText("");
//            txtPortNo4.setText("0");
//            txtIpAddr4.setEnabled(false);
//            txtPortNo4.setEnabled(false);
//        }
//        if (chkFifthIpEnable.isSelected()) {
//            txtIpAddr5.setEnabled(true);
//            txtPortNo5.setEnabled(true);
//        } else {
//            txtIpAddr5.setText("");
//            txtPortNo5.setText("0");
//            txtIpAddr5.setEnabled(false);
//            txtPortNo5.setEnabled(false);
//        }
        this.txtNetBalance.setText(clsSharedVariables.getChkNetBalCommand());

        cmbDataSendMode.setSelectedIndex(clsSharedVariables.getCommMode());

        this.txtSmsContactNo.setText(clsSharedVariables.getSmsPhoneNo());
        spinSmsUpdateTime.setValue(clsSharedVariables.sms_update_rate);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        if (clsSharedVariables.getEmbNorSimType() == clsDefines.EMBEDDED_SIM) {
            PanEmbSim.setVisible(true);
            radEmbSimType.setSelected(true);
            //  System.out.println("network 10");
            if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_AUTO_MODE) {
                radSwitchAuto.setSelected(true);
                cmbSwitchMode.setVisible(false);
            } else if (clsSharedVariables.getEmbSimAutoSwitch() == clsDefines.SWITCH_MANUAL_MODE) {
                radSwitchManual.setSelected(true);
                cmbSwitchMode.setVisible(false);
            } else {
                cmbSwitchMode.setVisible(true);
                radSwitchFixed.setSelected(true);
                if (clsSharedVariables.getEmbSimFixedSwitchMode() == clsDefines.SWITCH_PRIMARY_MODE) {
                    cmbSwitchMode.setSelectedIndex(0);
                } else {
                    cmbSwitchMode.setSelectedIndex(1);
                }
            }

        } else {
            //  System.out.println("network 11");
            radRegularSimType.setSelected(true);
            PanEmbSim.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsimtypegrp = new javax.swing.ButtonGroup();
        btnswicthgrp = new javax.swing.ButtonGroup();
        lblConfigName = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        txtApn = new javax.swing.JTextField();
        lblApn = new javax.swing.JLabel();
        lblNetBal = new javax.swing.JLabel();
        txtNetBalance = new javax.swing.JTextField();
        lblIpInterface1 = new javax.swing.JLabel();
        cmbDataSendMode = new javax.swing.JComboBox();
        lblFtpPassword1 = new javax.swing.JLabel();
        txtSmsContactNo = new javax.swing.JTextField();
        lblFtpPassword2 = new javax.swing.JLabel();
        spinSmsUpdateTime = new javax.swing.JSpinner();
        panServerIpPort = new javax.swing.JPanel();
        lblPortNo1 = new javax.swing.JLabel();
        txtIpAddr = new javax.swing.JTextField();
        txtPortNo = new javax.swing.JTextField();
        lblIpAddr1 = new javax.swing.JLabel();
        chkFirstIpEnable = new javax.swing.JCheckBox();
        chkSecondIpEnable = new javax.swing.JCheckBox();
        txtIpAddr2 = new javax.swing.JTextField();
        txtPortNo2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblFtpIpAddr = new javax.swing.JLabel();
        lblFtpUserName = new javax.swing.JLabel();
        lblFtpPassword = new javax.swing.JLabel();
        txtFtpIpAddr = new javax.swing.JTextField();
        txtFtpUsername = new javax.swing.JTextField();
        txtFtpPwd = new javax.swing.JTextField();
        txtFtpPort = new javax.swing.JTextField();
        lblFtpPort = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        panWifiUsr = new javax.swing.JPanel();
        lblSsid = new javax.swing.JLabel();
        lblPwd = new javax.swing.JLabel();
        txtSsid = new javax.swing.JTextField();
        btnsavewifi = new javax.swing.JButton();
        lblMsg1 = new javax.swing.JLabel();
        txtPwd = new javax.swing.JPasswordField();
        btnShowPassword1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnNetworkName = new javax.swing.JButton();
        btnResetModule = new javax.swing.JButton();
        lblMsg2 = new javax.swing.JLabel();
        PanEmbSim = new javax.swing.JPanel();
        radSwitchAuto = new javax.swing.JRadioButton();
        radSwitchManual = new javax.swing.JRadioButton();
        radSwitchFixed = new javax.swing.JRadioButton();
        cmbSwitchMode = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        radRegularSimType = new javax.swing.JRadioButton();
        radEmbSimType = new javax.swing.JRadioButton();
        btnSetup = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(500, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("NETWORK");
        lblConfigName.setPreferredSize(new java.awt.Dimension(550, 32));

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusLost(evt);
            }
        });

        jScrollPane1.setNextFocusableComponent(btnSave);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(440, 400));
        jScrollPane1.setRequestFocusEnabled(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 1500));

        txtApn.setFont(txtApn.getFont().deriveFont(txtApn.getFont().getStyle() | java.awt.Font.BOLD, txtApn.getFont().getSize()+4));
        txtApn.setForeground(new java.awt.Color(0, 102, 51));
        txtApn.setText("internet");
        txtApn.setPreferredSize(new java.awt.Dimension(150, 40));
        txtApn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApnFocusLost(evt);
            }
        });
        txtApn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApnActionPerformed(evt);
            }
        });

        lblApn.setFont(lblApn.getFont().deriveFont(lblApn.getFont().getStyle() | java.awt.Font.BOLD, lblApn.getFont().getSize()+3));
        lblApn.setForeground(new java.awt.Color(0, 0, 102));
        lblApn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblApn.setText("APN");
        lblApn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblApn.setPreferredSize(new java.awt.Dimension(200, 40));

        lblNetBal.setFont(lblNetBal.getFont().deriveFont(lblNetBal.getFont().getStyle() | java.awt.Font.BOLD, lblNetBal.getFont().getSize()+3));
        lblNetBal.setForeground(new java.awt.Color(0, 0, 102));
        lblNetBal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNetBal.setText("Net Balance");
        lblNetBal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNetBal.setPreferredSize(new java.awt.Dimension(200, 40));

        txtNetBalance.setFont(txtNetBalance.getFont().deriveFont(txtNetBalance.getFont().getStyle() | java.awt.Font.BOLD, txtNetBalance.getFont().getSize()+4));
        txtNetBalance.setForeground(new java.awt.Color(0, 102, 51));
        txtNetBalance.setText("*125#");
        txtNetBalance.setPreferredSize(new java.awt.Dimension(150, 40));
        txtNetBalance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNetBalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNetBalanceFocusLost(evt);
            }
        });
        txtNetBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNetBalanceActionPerformed(evt);
            }
        });

        lblIpInterface1.setFont(lblIpInterface1.getFont().deriveFont(lblIpInterface1.getFont().getStyle() | java.awt.Font.BOLD, lblIpInterface1.getFont().getSize()+3));
        lblIpInterface1.setForeground(new java.awt.Color(0, 0, 102));
        lblIpInterface1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIpInterface1.setText("Data Send Mode");
        lblIpInterface1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblIpInterface1.setPreferredSize(new java.awt.Dimension(200, 40));

        cmbDataSendMode.setFont(cmbDataSendMode.getFont().deriveFont(cmbDataSendMode.getFont().getStyle() | java.awt.Font.BOLD, cmbDataSendMode.getFont().getSize()+4));
        cmbDataSendMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GPRS", "GPRS-SMS" }));
        cmbDataSendMode.setPreferredSize(new java.awt.Dimension(150, 40));

        lblFtpPassword1.setFont(lblFtpPassword1.getFont().deriveFont(lblFtpPassword1.getFont().getStyle() | java.awt.Font.BOLD, lblFtpPassword1.getFont().getSize()+3));
        lblFtpPassword1.setForeground(new java.awt.Color(0, 0, 102));
        lblFtpPassword1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFtpPassword1.setText("SMS Mobile No");
        lblFtpPassword1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblFtpPassword1.setPreferredSize(new java.awt.Dimension(200, 40));

        txtSmsContactNo.setFont(txtSmsContactNo.getFont().deriveFont(txtSmsContactNo.getFont().getStyle() | java.awt.Font.BOLD, txtSmsContactNo.getFont().getSize()+4));
        txtSmsContactNo.setForeground(new java.awt.Color(0, 102, 51));
        txtSmsContactNo.setText("20");
        txtSmsContactNo.setPreferredSize(new java.awt.Dimension(150, 40));
        txtSmsContactNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSmsContactNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSmsContactNoFocusLost(evt);
            }
        });
        txtSmsContactNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSmsContactNoActionPerformed(evt);
            }
        });

        lblFtpPassword2.setFont(lblFtpPassword2.getFont().deriveFont(lblFtpPassword2.getFont().getStyle() | java.awt.Font.BOLD, lblFtpPassword2.getFont().getSize()+3));
        lblFtpPassword2.setForeground(new java.awt.Color(0, 0, 102));
        lblFtpPassword2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFtpPassword2.setText("SMS Update Time");
        lblFtpPassword2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblFtpPassword2.setPreferredSize(new java.awt.Dimension(200, 40));

        spinSmsUpdateTime.setFont(spinSmsUpdateTime.getFont().deriveFont(spinSmsUpdateTime.getFont().getStyle() | java.awt.Font.BOLD, spinSmsUpdateTime.getFont().getSize()+5));
        spinSmsUpdateTime.setModel(new javax.swing.SpinnerNumberModel(1, 0, 5000, 1));
        spinSmsUpdateTime.setPreferredSize(new java.awt.Dimension(150, 40));

        panServerIpPort.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SERVER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        panServerIpPort.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblPortNo1.setFont(lblPortNo1.getFont().deriveFont(lblPortNo1.getFont().getStyle() | java.awt.Font.BOLD, lblPortNo1.getFont().getSize()+3));
        lblPortNo1.setForeground(new java.awt.Color(0, 0, 102));
        lblPortNo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPortNo1.setText("Port no");
        lblPortNo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblPortNo1.setPreferredSize(new java.awt.Dimension(200, 40));

        txtIpAddr.setFont(txtIpAddr.getFont().deriveFont(txtIpAddr.getFont().getStyle() | java.awt.Font.BOLD, txtIpAddr.getFont().getSize()+4));
        txtIpAddr.setForeground(new java.awt.Color(0, 102, 51));
        txtIpAddr.setPreferredSize(new java.awt.Dimension(150, 40));
        txtIpAddr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIpAddrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIpAddrFocusLost(evt);
            }
        });
        txtIpAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIpAddrActionPerformed(evt);
            }
        });

        txtPortNo.setFont(txtPortNo.getFont().deriveFont(txtPortNo.getFont().getStyle() | java.awt.Font.BOLD, txtPortNo.getFont().getSize()+4));
        txtPortNo.setForeground(new java.awt.Color(0, 102, 51));
        txtPortNo.setPreferredSize(new java.awt.Dimension(150, 40));
        txtPortNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPortNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPortNoFocusLost(evt);
            }
        });

        lblIpAddr1.setFont(lblIpAddr1.getFont().deriveFont(lblIpAddr1.getFont().getStyle() | java.awt.Font.BOLD, lblIpAddr1.getFont().getSize()+3));
        lblIpAddr1.setForeground(new java.awt.Color(0, 0, 102));
        lblIpAddr1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIpAddr1.setText("IP Address");
        lblIpAddr1.setPreferredSize(new java.awt.Dimension(200, 40));

        chkFirstIpEnable.setFont(chkFirstIpEnable.getFont().deriveFont(chkFirstIpEnable.getFont().getStyle() | java.awt.Font.BOLD, chkFirstIpEnable.getFont().getSize()+4));
        chkFirstIpEnable.setForeground(new java.awt.Color(51, 0, 102));
        chkFirstIpEnable.setText("IP1");
        chkFirstIpEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFirstIpEnableActionPerformed(evt);
            }
        });

        chkSecondIpEnable.setFont(chkSecondIpEnable.getFont().deriveFont(chkSecondIpEnable.getFont().getStyle() | java.awt.Font.BOLD, chkSecondIpEnable.getFont().getSize()+4));
        chkSecondIpEnable.setForeground(new java.awt.Color(51, 0, 102));
        chkSecondIpEnable.setText("IP2");
        chkSecondIpEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSecondIpEnableActionPerformed(evt);
            }
        });

        txtIpAddr2.setFont(txtIpAddr2.getFont().deriveFont(txtIpAddr2.getFont().getStyle() | java.awt.Font.BOLD, txtIpAddr2.getFont().getSize()+4));
        txtIpAddr2.setForeground(new java.awt.Color(0, 102, 51));
        txtIpAddr2.setPreferredSize(new java.awt.Dimension(150, 40));
        txtIpAddr2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIpAddr2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIpAddr2FocusLost(evt);
            }
        });

        txtPortNo2.setFont(txtPortNo2.getFont().deriveFont(txtPortNo2.getFont().getStyle() | java.awt.Font.BOLD, txtPortNo2.getFont().getSize()+4));
        txtPortNo2.setForeground(new java.awt.Color(0, 102, 51));
        txtPortNo2.setPreferredSize(new java.awt.Dimension(150, 40));
        txtPortNo2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPortNo2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPortNo2FocusLost(evt);
            }
        });
        txtPortNo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortNo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panServerIpPortLayout = new javax.swing.GroupLayout(panServerIpPort);
        panServerIpPort.setLayout(panServerIpPortLayout);
        panServerIpPortLayout.setHorizontalGroup(
            panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panServerIpPortLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panServerIpPortLayout.createSequentialGroup()
                        .addGroup(panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panServerIpPortLayout.createSequentialGroup()
                                .addComponent(chkFirstIpEnable)
                                .addGap(18, 18, 18)
                                .addComponent(txtIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panServerIpPortLayout.createSequentialGroup()
                                .addComponent(chkSecondIpEnable)
                                .addGap(19, 19, 19)
                                .addComponent(txtIpAddr2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panServerIpPortLayout.createSequentialGroup()
                        .addComponent(lblIpAddr1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGroup(panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panServerIpPortLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblPortNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPortNo2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(txtPortNo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        panServerIpPortLayout.setVerticalGroup(
            panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panServerIpPortLayout.createSequentialGroup()
                .addGroup(panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPortNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIpAddr1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkFirstIpEnable)
                    .addComponent(txtIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPortNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panServerIpPortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIpAddr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPortNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSecondIpEnable))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panServerIpPortLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtIpAddr2, txtPortNo2});

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("FTP"));

        lblFtpIpAddr.setFont(lblFtpIpAddr.getFont().deriveFont(lblFtpIpAddr.getFont().getStyle() | java.awt.Font.BOLD, lblFtpIpAddr.getFont().getSize()+3));
        lblFtpIpAddr.setForeground(new java.awt.Color(0, 0, 102));
        lblFtpIpAddr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFtpIpAddr.setText(" IP Address");
        lblFtpIpAddr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblFtpIpAddr.setPreferredSize(new java.awt.Dimension(200, 40));

        lblFtpUserName.setFont(lblFtpUserName.getFont().deriveFont(lblFtpUserName.getFont().getStyle() | java.awt.Font.BOLD, lblFtpUserName.getFont().getSize()+3));
        lblFtpUserName.setForeground(new java.awt.Color(0, 0, 102));
        lblFtpUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFtpUserName.setText("User Name");
        lblFtpUserName.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblFtpUserName.setPreferredSize(new java.awt.Dimension(200, 40));

        lblFtpPassword.setFont(lblFtpPassword.getFont().deriveFont(lblFtpPassword.getFont().getStyle() | java.awt.Font.BOLD, lblFtpPassword.getFont().getSize()+3));
        lblFtpPassword.setForeground(new java.awt.Color(0, 0, 102));
        lblFtpPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFtpPassword.setText("Password");
        lblFtpPassword.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblFtpPassword.setPreferredSize(new java.awt.Dimension(200, 40));

        txtFtpIpAddr.setFont(txtFtpIpAddr.getFont().deriveFont(txtFtpIpAddr.getFont().getStyle() | java.awt.Font.BOLD, txtFtpIpAddr.getFont().getSize()+4));
        txtFtpIpAddr.setForeground(new java.awt.Color(0, 102, 51));
        txtFtpIpAddr.setPreferredSize(new java.awt.Dimension(150, 40));
        txtFtpIpAddr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFtpIpAddrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFtpIpAddrFocusLost(evt);
            }
        });
        txtFtpIpAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFtpIpAddrActionPerformed(evt);
            }
        });

        txtFtpUsername.setFont(txtFtpUsername.getFont().deriveFont(txtFtpUsername.getFont().getStyle() | java.awt.Font.BOLD, txtFtpUsername.getFont().getSize()+4));
        txtFtpUsername.setForeground(new java.awt.Color(0, 102, 51));
        txtFtpUsername.setPreferredSize(new java.awt.Dimension(150, 40));
        txtFtpUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFtpUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFtpUsernameFocusLost(evt);
            }
        });

        txtFtpPwd.setFont(txtFtpPwd.getFont().deriveFont(txtFtpPwd.getFont().getStyle() | java.awt.Font.BOLD, txtFtpPwd.getFont().getSize()+4));
        txtFtpPwd.setForeground(new java.awt.Color(0, 102, 51));
        txtFtpPwd.setPreferredSize(new java.awt.Dimension(150, 40));
        txtFtpPwd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFtpPwdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFtpPwdFocusLost(evt);
            }
        });
        txtFtpPwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFtpPwdActionPerformed(evt);
            }
        });

        txtFtpPort.setFont(txtFtpPort.getFont().deriveFont(txtFtpPort.getFont().getStyle() | java.awt.Font.BOLD, txtFtpPort.getFont().getSize()+4));
        txtFtpPort.setForeground(new java.awt.Color(0, 102, 51));
        txtFtpPort.setPreferredSize(new java.awt.Dimension(150, 40));
        txtFtpPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFtpPortFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFtpPortFocusLost(evt);
            }
        });
        txtFtpPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFtpPortActionPerformed(evt);
            }
        });

        lblFtpPort.setFont(lblFtpPort.getFont().deriveFont(lblFtpPort.getFont().getStyle() | java.awt.Font.BOLD, lblFtpPort.getFont().getSize()+3));
        lblFtpPort.setForeground(new java.awt.Color(0, 0, 102));
        lblFtpPort.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFtpPort.setText(" PORT");
        lblFtpPort.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblFtpPort.setPreferredSize(new java.awt.Dimension(200, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFtpIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblFtpIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFtpUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFtpUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFtpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFtpPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFtpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFtpIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFtpUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFtpIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFtpUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFtpPwd, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFtpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFtpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("min");

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

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Configuration Saved");
        lblMsg.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFtpPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFtpPassword1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(spinSmsUpdateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGap(92, 92, 92))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panServerIpPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNetBal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIpInterface1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblApn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDataSendMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNetBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSmsContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblApn, lblFtpPassword1, lblFtpPassword2, lblIpInterface1, lblNetBal});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panServerIpPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNetBal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNetBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDataSendMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIpInterface1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSmsContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFtpPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinSmsUpdateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFtpPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMsg)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        jTabbedPane1.addTab("SERVER", jScrollPane1);
        jScrollPane1.getAccessibleContext().setAccessibleName("");
        jScrollPane1.getAccessibleContext().setAccessibleDescription("");
        jScrollPane1.getAccessibleContext().setAccessibleParent(jScrollPane1);

        panWifiUsr.setPreferredSize(new java.awt.Dimension(500, 500));

        lblSsid.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblSsid.setForeground(new java.awt.Color(0, 0, 102));
        lblSsid.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSsid.setText("USERNAME");

        lblPwd.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblPwd.setForeground(new java.awt.Color(0, 0, 102));
        lblPwd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPwd.setText("PASSWORD");

        txtSsid.setPreferredSize(new java.awt.Dimension(200, 30));
        txtSsid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSsidFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSsidFocusLost(evt);
            }
        });
        txtSsid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSsidActionPerformed(evt);
            }
        });

        btnsavewifi.setBackground(new java.awt.Color(47, 49, 51));
        btnsavewifi.setFont(btnsavewifi.getFont().deriveFont(btnsavewifi.getFont().getStyle() | java.awt.Font.BOLD, btnsavewifi.getFont().getSize()+7));
        btnsavewifi.setForeground(new java.awt.Color(255, 255, 255));
        btnsavewifi.setText("SAVE");
        btnsavewifi.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsavewifi.setPreferredSize(new java.awt.Dimension(100, 42));
        btnsavewifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsavewifiActionPerformed(evt);
            }
        });

        lblMsg1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMsg1.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg1.setText("Configuration Saved");

        txtPwd.setFont(txtPwd.getFont().deriveFont(txtPwd.getFont().getStyle() | java.awt.Font.BOLD, txtPwd.getFont().getSize()+4));
        txtPwd.setText("jPasswordField1");
        txtPwd.setPreferredSize(new java.awt.Dimension(200, 30));
        txtPwd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPwdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPwdFocusLost(evt);
            }
        });
        txtPwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPwdActionPerformed(evt);
            }
        });

        btnShowPassword1.setBackground(new java.awt.Color(153, 0, 0));
        btnShowPassword1.setBorderPainted(false);
        btnShowPassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassword1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panWifiUsrLayout = new javax.swing.GroupLayout(panWifiUsr);
        panWifiUsr.setLayout(panWifiUsrLayout);
        panWifiUsrLayout.setHorizontalGroup(
            panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panWifiUsrLayout.createSequentialGroup()
                .addGroup(panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panWifiUsrLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSsid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSsid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panWifiUsrLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnsavewifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panWifiUsrLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        panWifiUsrLayout.setVerticalGroup(
            panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panWifiUsrLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSsid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panWifiUsrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsavewifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(532, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("WIFI", panWifiUsr);
        panWifiUsr.getAccessibleContext().setAccessibleParent(this);

        btnNetworkName.setBackground(new java.awt.Color(47, 49, 51));
        btnNetworkName.setFont(btnNetworkName.getFont().deriveFont(btnNetworkName.getFont().getStyle() | java.awt.Font.BOLD, btnNetworkName.getFont().getSize()+7));
        btnNetworkName.setForeground(new java.awt.Color(255, 255, 255));
        btnNetworkName.setText("Get Network Operator");
        btnNetworkName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNetworkName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNetworkNameActionPerformed(evt);
            }
        });

        btnResetModule.setBackground(new java.awt.Color(47, 49, 51));
        btnResetModule.setFont(btnResetModule.getFont().deriveFont(btnResetModule.getFont().getStyle() | java.awt.Font.BOLD, btnResetModule.getFont().getSize()+7));
        btnResetModule.setForeground(new java.awt.Color(255, 255, 255));
        btnResetModule.setText("Reset Module");
        btnResetModule.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnResetModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetModuleActionPerformed(evt);
            }
        });

        lblMsg2.setFont(lblMsg2.getFont().deriveFont(lblMsg2.getFont().getStyle() | java.awt.Font.BOLD, lblMsg2.getFont().getSize()+7));
        lblMsg2.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PanEmbSim.setBorder(javax.swing.BorderFactory.createTitledBorder("Switching Type"));

        btnswicthgrp.add(radSwitchAuto);
        radSwitchAuto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radSwitchAuto.setText("AUTO");
        radSwitchAuto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radSwitchAutoStateChanged(evt);
            }
        });

        btnswicthgrp.add(radSwitchManual);
        radSwitchManual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radSwitchManual.setSelected(true);
        radSwitchManual.setText("MANUAL");
        radSwitchManual.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radSwitchManualStateChanged(evt);
            }
        });

        btnswicthgrp.add(radSwitchFixed);
        radSwitchFixed.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radSwitchFixed.setText("FIXED");
        radSwitchFixed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radSwitchFixedStateChanged(evt);
            }
        });

        cmbSwitchMode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbSwitchMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRIMARY", "SECONDARY" }));

        javax.swing.GroupLayout PanEmbSimLayout = new javax.swing.GroupLayout(PanEmbSim);
        PanEmbSim.setLayout(PanEmbSimLayout);
        PanEmbSimLayout.setHorizontalGroup(
            PanEmbSimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanEmbSimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radSwitchAuto)
                .addGap(18, 18, 18)
                .addComponent(radSwitchManual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radSwitchFixed)
                .addGap(40, 40, 40)
                .addComponent(cmbSwitchMode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanEmbSimLayout.setVerticalGroup(
            PanEmbSimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanEmbSimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanEmbSimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radSwitchAuto)
                    .addComponent(radSwitchManual)
                    .addComponent(radSwitchFixed)
                    .addComponent(cmbSwitchMode, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sim Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 102))); // NOI18N

        btnsimtypegrp.add(radRegularSimType);
        radRegularSimType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        radRegularSimType.setText("Regular SIM");
        radRegularSimType.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radRegularSimTypeStateChanged(evt);
            }
        });
        radRegularSimType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radRegularSimTypeActionPerformed(evt);
            }
        });

        btnsimtypegrp.add(radEmbSimType);
        radEmbSimType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        radEmbSimType.setSelected(true);
        radEmbSimType.setText("Embedded SIM");
        radEmbSimType.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radEmbSimTypeStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radEmbSimType)
                .addGap(63, 63, 63)
                .addComponent(radRegularSimType)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radEmbSimType)
                    .addComponent(radRegularSimType))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSetup.setBackground(new java.awt.Color(47, 49, 51));
        btnSetup.setFont(btnSetup.getFont().deriveFont(btnSetup.getFont().getStyle() | java.awt.Font.BOLD, btnSetup.getFont().getSize()+7));
        btnSetup.setForeground(new java.awt.Color(255, 255, 255));
        btnSetup.setText("SAVE");
        btnSetup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanEmbSim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(lblMsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNetworkName, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(btnSetup, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnResetModule, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanEmbSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSetup, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNetworkName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetModule, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(430, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SIM", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        int port_no = 0;
        String ip_addr = "";
        int port_no2 = 0;
        String ip_addr2 = "";
        int port_no3 = 0;
        String ip_addr3 = "";
        int port_no4 = 0;
        String ip_addr4 = "";
        int port_no5 = 0;
        String ip_addr5 = "";
        String ftp_ipaddr;
        String ftp_username;
        String ftp_pwd;
        String gsm_connection_name;
        int ftp_port=0;

        clsReadFiles obj = new clsReadFiles();
        try {
            try {
                setIpAddr1Enable(this.chkFirstIpEnable.isSelected());
                setIpAddr2Enable(this.chkSecondIpEnable.isSelected());
//                setIpAddr3Enable(this.chkThirdIpEnable.isSelected());
//                setIpAddr4Enable(this.chkFourthIpEnable.isSelected());
//                setIpAddr5Enable(this.chkFifthIpEnable.isSelected());

                port_no = (Integer.parseInt(String.valueOf(txtPortNo.getText())));
                setPortNo1(port_no);
            } catch (NumberFormatException ex) {
                setPortNo1(0);
            }

            ip_addr = String.valueOf(txtIpAddr.getText());
            setIpAddr1(ip_addr);

            try {
                port_no2 = (Integer.parseInt(String.valueOf(txtPortNo2.getText())));
                setPortNo2(port_no2);
            } catch (Exception ex) {
                setPortNo2(0);
            }

            ip_addr2 = String.valueOf(txtIpAddr2.getText());
            setIpAddr2(ip_addr2);

//            ip_addr3 = String.valueOf(txtIpAddr3.getText());
//            setIpAddr3(ip_addr3);
//            try {
//                port_no3 = (Integer.parseInt(String.valueOf(txtPortNo3.getText())));
//                setPortNo3(port_no3);
//            } catch (Exception ex) {
//                setPortNo3(0);
//            }
//            ip_addr4 = String.valueOf(txtIpAddr4.getText());
//            clsSharedVariables.setIpAddr4(ip_addr4);
//            try {
//                port_no4 = (Integer.parseInt(String.valueOf(txtPortNo4.getText())));
//                clsSharedVariables.setPortNo4(port_no4);
//            } catch (Exception ex) {
//                clsSharedVariables.setPortNo4(0);
//            }
//            ip_addr5 = String.valueOf(txtIpAddr5.getText());
//            clsSharedVariables.setIpAddr5(ip_addr5);
//            try {
//                port_no5 = (Integer.parseInt(String.valueOf(txtPortNo5.getText())));
//                clsSharedVariables.setPortNo5(port_no5);
//            } catch (Exception ex) {
//                clsSharedVariables.setPortNo5(0);
//            }
            clsSharedVariables.setApn(String.valueOf(txtApn.getText()));

            ftp_ipaddr = String.valueOf(txtFtpIpAddr.getText());
            ftp_ipaddress = ftp_ipaddr;
            ftp_username = String.valueOf(txtFtpUsername.getText());
            ftpusername = ftp_username;
            ftp_pwd = String.valueOf(txtFtpPwd.getText());
            ftpPassword = ftp_pwd;
            ftp_port = (Integer.parseInt(String.valueOf(txtFtpPort.getText())));
             clsSharedVariables.setFtpPortNo(ftp_port);

            clsSharedVariables.setChkNetBalCommand(this.txtNetBalance.getText());

            clsSharedVariables.setCommMode((byte) cmbDataSendMode.getSelectedIndex());

            clsSharedVariables.setSmsPhoneNo(this.txtSmsContactNo.getText().trim());
            clsSharedVariables.sms_update_rate = (int) spinSmsUpdateTime.getValue();

//            switch (this.cmdIp5.getSelectedIndex()) {
//                case 0:
//                    setIp5Protocol(clsDefines.AIS140);
//                    clsSharedVariables.setSuratProtocol(false);
//                    break;
//                case 1:
//                    setIp5Protocol(clsDefines.SURAT);
//                    clsSharedVariables.setSuratProtocol(true);
//                    break;     
//                default:
//                    break;
//            }

            gsm_connection_name = get_ppp_gsm_name();
            if (clsDefines.NETWORKMANAGER_GPRS == true) {
                runCmd("nmcli c modify " + gsm_connection_name + " apn " + clsSharedVariables.getApn());
            }
        } catch (NumberFormatException ex) {
            lblMsg.setText("Error!  " + ex.getMessage());
            return;
        }

        setServerConfigStatus(true);
        setServer2ConfigStatus(true);
        setServer3ConfigStatus(true);

        clsSharedVariables.setServer4ConfigStatus(true);
        clsSharedVariables.setServer5ConfigStatus(true);
        if (obj.write_cfg_data_file()) {
            lblMsg.setText("Restart OBU Details Saved");
        } else {
            lblMsg.setText("Server Details Not Saved");
        }
     System.out.println("ftp_ipaddress-------"+ftp_ipaddress);
      System.out.println("ftpusername--------:"+ftpusername);
      System.out.println("ftpPassword:--------"+ftpPassword);
      System.out.println("portttt"+getFtpPortNo());

    }//GEN-LAST:event_btnSaveActionPerformed
    private String get_ppp_gsm_name() {
        BufferedReader br = null;

        File[] files_list;
        byte i;
        File file = new File(GET_NETWORK_UUID_PATH);
        if (file.exists()) {
            try {
                files_list = file.listFiles();
                if (files_list != null && files_list.length > 0) {
                    for (i = 0; i < files_list.length; i++) {
                        if (files_list[i].getName().contains("GSM")) {
                            return files_list[i].getName();
                        }
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                    br = null;
                    file = null;

                } catch (IOException e) {

                }
            }

        }
        return "";
    }

    private void txtIpAddrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIpAddrFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Primary IP Address")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Primary IP Address", txtIpAddr.getText());
            if (str != null) {
                txtIpAddr.setText(str);
            }
            prev_control_name = "Primary IP Address";
            obj = null;
            str = null;
            txtIpAddr.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtIpAddrFocusGained

    private void txtPortNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortNoFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Primary Port no")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Primary Port no", txtPortNo.getText());
            if (str != null) {
                txtPortNo.setText(str);
            }
            prev_control_name = "Primary Port no";
            obj = null;
            str = null;
            txtPortNo.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtPortNoFocusGained

    private void txtFtpIpAddrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpIpAddrFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("FTP IP Address")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("FTP IP Address", txtFtpIpAddr.getText());
            if (str != null) {
                txtFtpIpAddr.setText(str);
            }
            prev_control_name = "FTP IP Address";
            obj = null;
            str = null;
            txtFtpIpAddr.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtFtpIpAddrFocusGained

    private void txtFtpUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpUsernameFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("FTP Username")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("FTP Username", txtFtpUsername.getText());
            if (str != null) {
                txtFtpUsername.setText(str);
            }
            prev_control_name = "FTP Username";
            obj = null;
            str = null;
            txtFtpUsername.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }

    }//GEN-LAST:event_txtFtpUsernameFocusGained

    private void txtFtpPwdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpPwdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("FTP Password")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("FTP Password", txtFtpPwd.getText());
            if (str != null) {
                txtFtpPwd.setText(str);
            }
            prev_control_name = "FTP Password";
            obj = null;
            str = null;
            txtFtpPwd.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtFtpPwdFocusGained

    private void txtIpAddrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIpAddrFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtIpAddrFocusLost

    private void txtPortNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortNoFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtPortNoFocusLost

    private void txtFtpIpAddrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpIpAddrFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtFtpIpAddrFocusLost

    private void txtFtpUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpUsernameFocusLost

    }//GEN-LAST:event_txtFtpUsernameFocusLost

    private void txtFtpPwdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpPwdFocusLost

    }//GEN-LAST:event_txtFtpPwdFocusLost

    private void txtApnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("APN")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("APN", txtApn.getText());
            if (str != null) {
                txtApn.setText(str);
            }
            prev_control_name = "APN";
            obj = null;
            str = null;
            txtApn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtApnFocusGained

    private void txtApnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtApnFocusLost

    private void txtIpAddr2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIpAddr2FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Secondary IP Address")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Secondary IP Address", txtIpAddr2.getText());
            if (str != null) {
                txtIpAddr2.setText(str);
            }
            prev_control_name = "Secondary IP Address";
            obj = null;
            str = null;
            txtIpAddr2.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtIpAddr2FocusGained

    private void txtIpAddr2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIpAddr2FocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtIpAddr2FocusLost

    private void txtPortNo2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortNo2FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Secondary Port no")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Secondary Port no", txtPortNo2.getText());
            if (str != null) {
                txtPortNo2.setText(str);
            }
            prev_control_name = "Secondary Port no";
            obj = null;
            str = null;
            txtPortNo2.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtPortNo2FocusGained

    private void txtPortNo2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortNo2FocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtPortNo2FocusLost

    private void txtIpAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIpAddrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIpAddrActionPerformed

    private void txtNetBalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNetBalanceFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Net Bal")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Net Bal", txtNetBalance.getText());
            if (str != null) {
                txtNetBalance.setText(str);
            }
            prev_control_name = "Net Bal";
            obj = null;
            str = null;
            txtNetBalance.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtNetBalanceFocusGained

    private void txtNetBalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNetBalanceFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtNetBalanceFocusLost

    private void txtFtpIpAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFtpIpAddrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFtpIpAddrActionPerformed

    private void txtFtpPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFtpPwdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFtpPwdActionPerformed

    private void txtNetBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNetBalanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNetBalanceActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        int i;

        lblMsg.setText("");

        txtIpAddr.setText(String.valueOf(getIpAddr1()));
        txtPortNo.setText(String.valueOf(getPortNo1()));

        txtIpAddr2.setText(String.valueOf(getIpAddr2()));
        txtPortNo2.setText(String.valueOf(getPortNo2()));
        txtApn.setText(String.valueOf(clsSharedVariables.getApn()));

        txtFtpIpAddr.setText(String.valueOf(ftp_ipaddress));
        txtFtpUsername.setText(String.valueOf(ftpusername));
        txtFtpPwd.setText(String.valueOf(ftpPassword));
        txtFtpPort.setText(String.valueOf(getFtpPortNo()));

        /*  if (getIpInterface() == SINGLE_IP_INTERFACE) {
            this.cmbIpInterface.setSelectedIndex(0);
        } else if (getIpInterface() == DUAL_IP_INTERFACE) {
            this.cmbIpInterface.setSelectedIndex(1);
        } else {
            this.cmbIpInterface.setSelectedIndex(2);
        }*/
        this.txtNetBalance.setText(clsSharedVariables.getChkNetBalCommand());

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtSmsContactNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSmsContactNoFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("txtSmsContactNo")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("txtSmsContactNo", txtSmsContactNo.getText());
            if (str != null) {
                txtSmsContactNo.setText(str);
            }
            prev_control_name = "txtSmsContactNo";
            obj = null;
            str = null;
            txtSmsContactNo.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtSmsContactNoFocusGained

    private void txtSmsContactNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSmsContactNoFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtSmsContactNoFocusLost

    private void txtSmsContactNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSmsContactNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSmsContactNoActionPerformed

    private void txtPortNo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortNo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPortNo2ActionPerformed

    private void txtApnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApnActionPerformed

    private void chkSecondIpEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSecondIpEnableActionPerformed
        // TODO add your handling code here:
        if (chkSecondIpEnable.isSelected()) {
            txtIpAddr2.setEnabled(true);
            txtPortNo2.setEnabled(true);
        } else {
            txtIpAddr2.setText("0");
            txtPortNo2.setText("0");
            txtIpAddr2.setEnabled(false);
            txtPortNo2.setEnabled(false);
        }
    }//GEN-LAST:event_chkSecondIpEnableActionPerformed

    private void chkFirstIpEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFirstIpEnableActionPerformed
        // TODO add your handling code here:
        if (chkFirstIpEnable.isSelected()) {
            txtIpAddr.setEnabled(true);
            txtPortNo.setEnabled(true);
        } else {
            txtIpAddr.setText("0");
            txtPortNo.setText("0");
            txtIpAddr.setEnabled(false);
            txtPortNo.setEnabled(false);
        }
    }//GEN-LAST:event_chkFirstIpEnableActionPerformed

    private void txtSsidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSsidFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("SSID")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("SSID", txtSsid.getText());
            if (str != null) {
                txtSsid.setText(str);
            }
            prev_control_name = "SSID";
            obj = null;
            str = null;
            txtSsid.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtSsidFocusGained

    private void txtSsidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSsidFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtSsidFocusLost

    private void txtSsidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSsidActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSsidActionPerformed
    private synchronized boolean runCmd(String cmd) {
        Process process = null;

        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(10, TimeUnit.SECONDS);
            return true;
        } catch (IOException | InterruptedException e) {
            return false;
            //
        } //
        finally {

            try {
                if (process != null) {
                    rt.freeMemory();
                    rt.gc();

                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                    process = null;
                    rt = null;

                }
            } catch (IOException e) {
                // 
            } catch (Exception ex) {
                // 
            }
        }
    }

    private void btnsavewifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsavewifiActionPerformed
        // TODO add your handling code here:
        clsSharedVariables.wifi_ssid = String.valueOf(txtSsid.getText().trim());
        clsSharedVariables.wifi_password = String.valueOf(txtPwd.getText().trim());

        clsReadFiles obj = new clsReadFiles();
        try {
            obj.wpa();

            runCmd("sudo nmcli radio wifi on");
            runCmd("sudo nmcli device wifi connect \"" + clsSharedVariables.wifi_ssid + "\" password " + clsSharedVariables.wifi_password);

            lblMsg1.setText("Wifi configuration Saved");
            // reboot_system();
        } catch (Exception ex) {
            //Logger.getLogger(PanWifi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnsavewifiActionPerformed

    private void txtPwdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPwdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("PWD")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("PWD", txtPwd.getText());
            if (str != null) {
                txtPwd.setText(str);
            }
            prev_control_name = "PWD";
            obj = null;
            str = null;
            txtPwd.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtPwdFocusGained

    private void txtPwdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPwdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtPwdFocusLost

    private void txtPwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPwdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPwdActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:

        //  prev_control_name = "SSID";

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusLost

    private void btnNetworkNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNetworkNameActionPerformed
        
        // TODO add your handling code here:
        lblMsg.setText("");
        if (getSimReady() == false) {
            lblMsg.setText("SIM is not ready");
            return;

        } else if (getGsmModuleOn() == false) {
            lblMsg.setText("Module Down");
            return;
        }

        lblMsg.setText(clsSharedVariables.getNetworkOperatorName());
    }//GEN-LAST:event_btnNetworkNameActionPerformed

    private void btnResetModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetModuleActionPerformed
        // TODO add your handling code here:
        gsm_reset();
    }//GEN-LAST:event_btnResetModuleActionPerformed

    private void radSwitchAutoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radSwitchAutoStateChanged
        // TODO add your handling code here:
        if (radSwitchAuto.isSelected()) {
            this.cmbSwitchMode.setVisible(false);
        }
    }//GEN-LAST:event_radSwitchAutoStateChanged

    private void radSwitchManualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radSwitchManualStateChanged
        // TODO add your handling code here:
        if (radSwitchManual.isSelected()) {
            this.cmbSwitchMode.setVisible(false);
        }
    }//GEN-LAST:event_radSwitchManualStateChanged

    private void radSwitchFixedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radSwitchFixedStateChanged
        // TODO add your handling code here:
        if (radSwitchFixed.isSelected()) {
            this.cmbSwitchMode.setVisible(true);
        }
    }//GEN-LAST:event_radSwitchFixedStateChanged

    private void radRegularSimTypeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radRegularSimTypeStateChanged
        // TODO add your handling code here:
        if (radRegularSimType.isSelected()) {
            this.PanEmbSim.setVisible(false);
        }
    }//GEN-LAST:event_radRegularSimTypeStateChanged

    private void radRegularSimTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radRegularSimTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radRegularSimTypeActionPerformed

    private void radEmbSimTypeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radEmbSimTypeStateChanged
        // TODO add your handling code here:
        if (radEmbSimType.isSelected()) {
            this.PanEmbSim.setVisible(true);
        }
    }//GEN-LAST:event_radEmbSimTypeStateChanged

    private void btnSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupActionPerformed
        // TODO add your handling code here:

        if (this.radEmbSimType.isSelected()) {
            clsSharedVariables.setEmbNorSimType(clsDefines.EMBEDDED_SIM);
            if (radSwitchAuto.isSelected()) {
                clsSharedVariables.setEmbSimAutoSwitch(clsDefines.SWITCH_AUTO_MODE);
            } else if (this.radSwitchManual.isSelected()) {
                clsSharedVariables.setEmbSimAutoSwitch(clsDefines.SWITCH_MANUAL_MODE);
                clsSharedVariables.setEmbSimManualSwitchMode(clsDefines.SWITCH_SECONDARY_MODE);

            } else {
                clsSharedVariables.setEmbSimAutoSwitch(clsDefines.SWITCH_FIXED_MODE);
                if (this.cmbSwitchMode.getSelectedIndex() == 0) {
                    clsSharedVariables.setEmbSimFixedSwitchMode(clsDefines.SWITCH_PRIMARY_MODE);
                    // clsSharedVariables.setEmbSimAutoCommandStart((byte) 2);
                } else {
                    clsSharedVariables.setEmbSimFixedSwitchMode(clsDefines.SWITCH_SECONDARY_MODE);
                    // clsSharedVariables.setEmbSimAutoCommandStart((byte) 3);
                }
            }
            clsSharedVariables.setEmbSimAutoCommandStart((byte) 1);
        } else {
            clsSharedVariables.setEmbNorSimType(clsDefines.NORMAL_SIM);
            String str = "AT+QSTK=0\r\n";
            atSerialWrite(str);

        }
        lblMsg2.setText("Please Wait for 5 minutes to change profile....");
        clsReadFiles obj = new clsReadFiles();
        obj.write_embedded_switch_profile_data();
        obj = null;
    }//GEN-LAST:event_btnSetupActionPerformed
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
    private void btnShowPassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassword1ActionPerformed
        // TODO add your handling code here:
        show_message_dialogbox(txtPwd.getText());
    }//GEN-LAST:event_btnShowPassword1ActionPerformed

    private void txtFtpPortFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpPortFocusGained
        // TODO add your handling code here:
         if (!prev_control_name.equals("FTP Port")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("FTP Port", txtFtpPort.getText());
            if (str != null) {
                txtFtpPort.setText(str);
            }
            prev_control_name = "FTP Port";
            obj = null;
            str = null;
            txtFtpPort.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtFtpPortFocusGained

    private void txtFtpPortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFtpPortFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFtpPortFocusLost

    private void txtFtpPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFtpPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFtpPortActionPerformed

    private void gsm_reset() {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
                    objwatDog.setgps_watchdog_val((byte) 0);
                    objwatDog = null;
                    restart_module_ports_changed(true);
                } catch (Exception ex) {

                }
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background 
                // thread finishes execution
                try {
                    txtData.setText("GSM MODULE RESETTING COMPLETED");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanEmbSim;
    private javax.swing.JButton btnNetworkName;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnResetModule;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSetup;
    private javax.swing.JButton btnShowPassword1;
    private javax.swing.JButton btnsavewifi;
    private javax.swing.ButtonGroup btnsimtypegrp;
    private javax.swing.ButtonGroup btnswicthgrp;
    private javax.swing.JCheckBox chkFirstIpEnable;
    private javax.swing.JCheckBox chkSecondIpEnable;
    private javax.swing.JComboBox cmbDataSendMode;
    private javax.swing.JComboBox<String> cmbSwitchMode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblApn;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblFtpIpAddr;
    private javax.swing.JLabel lblFtpPassword;
    private javax.swing.JLabel lblFtpPassword1;
    private javax.swing.JLabel lblFtpPassword2;
    private javax.swing.JLabel lblFtpPort;
    private javax.swing.JLabel lblFtpUserName;
    private javax.swing.JLabel lblIpAddr1;
    private javax.swing.JLabel lblIpInterface1;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblNetBal;
    private javax.swing.JLabel lblPortNo1;
    private javax.swing.JLabel lblPwd;
    private javax.swing.JLabel lblSsid;
    private javax.swing.JPanel panServerIpPort;
    private javax.swing.JPanel panWifiUsr;
    private javax.swing.JRadioButton radEmbSimType;
    private javax.swing.JRadioButton radRegularSimType;
    private javax.swing.JRadioButton radSwitchAuto;
    private javax.swing.JRadioButton radSwitchFixed;
    private javax.swing.JRadioButton radSwitchManual;
    private javax.swing.JSpinner spinSmsUpdateTime;
    private javax.swing.JTextField txtApn;
    private javax.swing.JTextField txtFtpIpAddr;
    private javax.swing.JTextField txtFtpPort;
    private javax.swing.JTextField txtFtpPwd;
    private javax.swing.JTextField txtFtpUsername;
    private javax.swing.JTextField txtIpAddr;
    private javax.swing.JTextField txtIpAddr2;
    private javax.swing.JTextField txtNetBalance;
    private javax.swing.JTextField txtPortNo;
    private javax.swing.JTextField txtPortNo2;
    private javax.swing.JPasswordField txtPwd;
    private javax.swing.JTextField txtSmsContactNo;
    private javax.swing.JTextField txtSsid;
    // End of variables declaration//GEN-END:variables
}
