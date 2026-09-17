
package obuits;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static obuits.clsDefines.JBM_CAN;
import static obuits.clsDefines.OLECTRA_BYD;
import static obuits.clsDefines.OLECTRA_CX2;
import static obuits.clsDefines.PMI_CAN;
import static obuits.clsDefines.media_filepath;
import static obuits.clsSharedVariables.getCanDataEnabled;
import static obuits.clsSharedVariables.getCanEnabled;
import static obuits.clsSharedVariables.getCanEnabled1;
import static obuits.clsSharedVariables.getCanLogEnabled;
import static obuits.clsSharedVariables.setCanBusType;
import static obuits.clsSharedVariables.setCanDataEnabled;
import static obuits.clsSharedVariables.setCanEnabled;
import static obuits.clsSharedVariables.setCanEnabled1;
import static obuits.clsSharedVariables.setCanLogEnabled;

public class PanCanDetails extends javax.swing.JPanel {

    String prev_control_name = "";

    public PanCanDetails() {
        initComponents();
        lblRevCam1.setVisible(false);
        txtRevCamPgn1.setVisible(false);
        txtRevCamSpn1.setVisible(false);
        spinCamGearVal1.setVisible(false);
        lblRevCam2.setVisible(false);
        txtRevCamPgn2.setVisible(false);
        txtRevCamSpn2.setVisible(false);
        spinCamGearVal2.setVisible(false);
     
        try {
            if (clsSharedVariables.getCanBusType() == clsDefines.OLECTRA_BYD) {
                this.cmbCanBus.setSelectedIndex(0);
            } else if (clsSharedVariables.getCanBusType() == clsDefines.OLECTRA_CX2) {
                this.cmbCanBus.setSelectedIndex(1);
            } else if (clsSharedVariables.getCanBusType() == clsDefines.JBM_CAN) {
                this.cmbCanBus.setSelectedIndex(2);
            } else if (clsSharedVariables.getCanBusType() == clsDefines.PMI_CAN) {
                this.cmbCanBus.setSelectedIndex(3);
            }

            this.lblMsg.setText("");
            chkCanEnable.setSelected(getCanEnabled());
            chkCanEnable1.setSelected(getCanEnabled1());
            chkCanDataEnable.setSelected(getCanDataEnabled());
            chkCanLogEnable.setSelected(getCanLogEnabled());
            this.txtCamRevVal.setText(String.valueOf(clsSharedVariables.getReverseCamVal()));
            this.spinCamGearVal1.setValue(clsSharedVariables.getReverseCamVal1());
            this.spinCamGearVal2.setValue(clsSharedVariables.getReverseCamVal2());
            this.txtRevCamPgn.setText(String.valueOf(clsSharedVariables.getReverseCamPgn()));
            this.txtRevCamPgn1.setText(String.valueOf(clsSharedVariables.getReverseCamPgn1()));
            this.txtRevCamPgn2.setText(String.valueOf(clsSharedVariables.getReverseCamPgn2()));
            this.txtRevCamSpn.setText(String.valueOf(clsSharedVariables.getReverseCamSpn()));
            this.txtRevCamSpn1.setText(String.valueOf(clsSharedVariables.getReverseCamSpn1()));
            this.txtRevCamSpn2.setText(String.valueOf(clsSharedVariables.getReverseCamSpn2()));
            this.spinStopReqVal.setValue(clsSharedVariables.getStopReqVal());
            this.txtStopReqPgn.setText(String.valueOf(clsSharedVariables.getStopReqPgn()));
            this.txtStopReqSpn.setText(String.valueOf(clsSharedVariables.getStopReqSpn()));

            this.spinDoor1OpenVal.setValue(clsSharedVariables.getDoor1OpenVal());
            this.spinDoor1CloseVal.setValue(clsSharedVariables.getDoor1CloseVal());
            this.txtDoor1Pgn.setText(String.valueOf(clsSharedVariables.getDoor1Pgn()));
            this.txtDoor1Spn.setText(String.valueOf(clsSharedVariables.getDoor1Spn()));

            this.spinDoor2OpenVal.setValue(clsSharedVariables.getDoor2OpenVal());
            this.spinDoor2CloseVal.setValue(clsSharedVariables.getDoor2CloseVal());
            this.txtDoor2Pgn.setText(String.valueOf(clsSharedVariables.getDoor2Pgn()));
            this.txtDoor2Spn.setText(String.valueOf(clsSharedVariables.getDoor2Spn()));

            this.spinVidRecordstartVal.setValue(clsSharedVariables.getVideoRecordStartVal());
            this.spinVidRecordendVal.setValue(clsSharedVariables.getVideoRecordEndVal());
            this.txtVidRecordPgn.setText(String.valueOf(clsSharedVariables.getVideoRecordPgn()));
            this.txtVidRecordSpn.setText(String.valueOf(clsSharedVariables.getVideoRecordSpn()));

            if (chkCanEnable.isSelected()) {
                chkCanEnable.setText("CAN ENABLE");

            } else {
                chkCanEnable.setText("CAN DISABLE");

            }
        } catch (Exception ex) {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        lblCanHeading = new javax.swing.JTextField();
        chkCanEnable = new javax.swing.JCheckBox();
        lblMsg = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtRevCamPgn = new javax.swing.JTextField();
        txtRevCamSpn = new javax.swing.JTextField();
        lblStopReq = new javax.swing.JLabel();
        txtStopReqPgn = new javax.swing.JTextField();
        txtStopReqSpn = new javax.swing.JTextField();
        spinStopReqVal = new javax.swing.JSpinner();
        lblRevCam = new javax.swing.JLabel();
        lblSpn = new javax.swing.JLabel();
        lblVal = new javax.swing.JLabel();
        lblDoor2 = new javax.swing.JLabel();
        txtDoor2Pgn = new javax.swing.JTextField();
        txtDoor1Spn = new javax.swing.JTextField();
        spinDoor2OpenVal = new javax.swing.JSpinner();
        lblDoor1 = new javax.swing.JLabel();
        txtDoor1Pgn = new javax.swing.JTextField();
        txtDoor2Spn = new javax.swing.JTextField();
        spinDoor1OpenVal = new javax.swing.JSpinner();
        lblPgn = new javax.swing.JLabel();
        lblOpenVal = new javax.swing.JLabel();
        lblCloseVal = new javax.swing.JLabel();
        spinDoor1CloseVal = new javax.swing.JSpinner();
        spinDoor2CloseVal = new javax.swing.JSpinner();
        chkCanDataEnable = new javax.swing.JCheckBox();
        chkCanLogEnable = new javax.swing.JCheckBox();
        lblVideoRecord = new javax.swing.JLabel();
        txtVidRecordPgn = new javax.swing.JTextField();
        txtVidRecordSpn = new javax.swing.JTextField();
        spinVidRecordstartVal = new javax.swing.JSpinner();
        spinVidRecordendVal = new javax.swing.JSpinner();
        cmbCanBus = new javax.swing.JComboBox<>();
        lblRevCam1 = new javax.swing.JLabel();
        txtRevCamPgn1 = new javax.swing.JTextField();
        txtRevCamSpn1 = new javax.swing.JTextField();
        spinCamGearVal1 = new javax.swing.JSpinner();
        lblRevCam2 = new javax.swing.JLabel();
        txtRevCamPgn2 = new javax.swing.JTextField();
        txtRevCamSpn2 = new javax.swing.JTextField();
        spinCamGearVal2 = new javax.swing.JSpinner();
        txtCamRevVal = new javax.swing.JTextField();
        chkCanEnable1 = new javax.swing.JCheckBox();

        jToolBar1.setRollover(true);

        setPreferredSize(new java.awt.Dimension(500, 430));

        lblCanHeading.setEditable(false);
        lblCanHeading.setBackground(new java.awt.Color(23, 29, 32));
        lblCanHeading.setFont(lblCanHeading.getFont().deriveFont(lblCanHeading.getFont().getStyle() | java.awt.Font.BOLD, lblCanHeading.getFont().getSize()+10));
        lblCanHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblCanHeading.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblCanHeading.setText("CAN");

        chkCanEnable.setBackground(new java.awt.Color(255, 255, 255));
        chkCanEnable.setFont(chkCanEnable.getFont().deriveFont(chkCanEnable.getFont().getStyle() | java.awt.Font.BOLD, chkCanEnable.getFont().getSize()+6));
        chkCanEnable.setForeground(new java.awt.Color(0, 0, 102));
        chkCanEnable.setSelected(true);
        chkCanEnable.setText("CAN1 ENABLE");
        chkCanEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanEnableActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Configuration Saved");

        btnSave.setBackground(new java.awt.Color(47, 49, 51));
        btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | java.awt.Font.BOLD, btnSave.getFont().getSize()+7));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblName.setFont(lblName.getFont().deriveFont(lblName.getFont().getStyle() | java.awt.Font.BOLD, lblName.getFont().getSize()+5));
        lblName.setForeground(new java.awt.Color(102, 0, 0));
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("NAME");

        txtRevCamPgn.setFont(txtRevCamPgn.getFont().deriveFont(txtRevCamPgn.getFont().getSize()+5f));
        txtRevCamPgn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRevCamPgn.setText("61445");
        txtRevCamPgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRevCamPgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRevCamPgnFocusLost(evt);
            }
        });
        txtRevCamPgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRevCamPgnActionPerformed(evt);
            }
        });

        txtRevCamSpn.setFont(txtRevCamSpn.getFont().deriveFont(txtRevCamSpn.getFont().getSize()+5f));
        txtRevCamSpn.setText("523");
        txtRevCamSpn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRevCamSpnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRevCamSpnFocusLost(evt);
            }
        });
        txtRevCamSpn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRevCamSpnActionPerformed(evt);
            }
        });

        lblStopReq.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStopReq.setForeground(new java.awt.Color(0, 0, 102));
        lblStopReq.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStopReq.setText("Stop Request");

        txtStopReqPgn.setFont(txtStopReqPgn.getFont().deriveFont(txtStopReqPgn.getFont().getSize()+5f));
        txtStopReqPgn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtStopReqPgn.setText("65281");
        txtStopReqPgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStopReqPgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStopReqPgnFocusLost(evt);
            }
        });

        txtStopReqSpn.setFont(txtStopReqSpn.getFont().deriveFont(txtStopReqSpn.getFont().getSize()+5f));
        txtStopReqSpn.setText("2");
        txtStopReqSpn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStopReqSpnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStopReqSpnFocusLost(evt);
            }
        });
        txtStopReqSpn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStopReqSpnActionPerformed(evt);
            }
        });

        spinStopReqVal.setFont(spinStopReqVal.getFont().deriveFont(spinStopReqVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinStopReqVal.getFont().getSize()+5));

        lblRevCam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRevCam.setForeground(new java.awt.Color(0, 0, 102));
        lblRevCam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRevCam.setText("Reverse Cam Can1");

        lblSpn.setFont(lblSpn.getFont().deriveFont(lblSpn.getFont().getStyle() | java.awt.Font.BOLD, lblSpn.getFont().getSize()+5));
        lblSpn.setForeground(new java.awt.Color(102, 0, 0));
        lblSpn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSpn.setText("SPN");

        lblVal.setFont(lblVal.getFont().deriveFont(lblVal.getFont().getStyle() | java.awt.Font.BOLD, lblVal.getFont().getSize()+5));
        lblVal.setForeground(new java.awt.Color(102, 0, 0));
        lblVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVal.setText("VALUE");

        lblDoor2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDoor2.setForeground(new java.awt.Color(0, 0, 102));
        lblDoor2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDoor2.setText("Door2");

        txtDoor2Pgn.setFont(txtDoor2Pgn.getFont().deriveFont(txtDoor2Pgn.getFont().getSize()+5f));
        txtDoor2Pgn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDoor2Pgn.setText("65281");
        txtDoor2Pgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDoor2PgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDoor2PgnFocusLost(evt);
            }
        });

        txtDoor1Spn.setFont(txtDoor1Spn.getFont().deriveFont(txtDoor1Spn.getFont().getSize()+5f));
        txtDoor1Spn.setText("2");
        txtDoor1Spn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDoor1SpnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDoor1SpnFocusLost(evt);
            }
        });

        spinDoor2OpenVal.setFont(spinDoor2OpenVal.getFont().deriveFont(spinDoor2OpenVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinDoor2OpenVal.getFont().getSize()+5));

        lblDoor1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDoor1.setForeground(new java.awt.Color(0, 0, 102));
        lblDoor1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDoor1.setText("Door1");

        txtDoor1Pgn.setFont(txtDoor1Pgn.getFont().deriveFont(txtDoor1Pgn.getFont().getSize()+5f));
        txtDoor1Pgn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDoor1Pgn.setText("65281");
        txtDoor1Pgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDoor1PgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDoor1PgnFocusLost(evt);
            }
        });
        txtDoor1Pgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoor1PgnActionPerformed(evt);
            }
        });

        txtDoor2Spn.setFont(txtDoor2Spn.getFont().deriveFont(txtDoor2Spn.getFont().getSize()+5f));
        txtDoor2Spn.setText("2");
        txtDoor2Spn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDoor2SpnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDoor2SpnFocusLost(evt);
            }
        });
        txtDoor2Spn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoor2SpnActionPerformed(evt);
            }
        });

        spinDoor1OpenVal.setFont(spinDoor1OpenVal.getFont().deriveFont(spinDoor1OpenVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinDoor1OpenVal.getFont().getSize()+5));

        lblPgn.setFont(lblPgn.getFont().deriveFont(lblPgn.getFont().getStyle() | java.awt.Font.BOLD, lblPgn.getFont().getSize()+5));
        lblPgn.setForeground(new java.awt.Color(102, 0, 0));
        lblPgn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPgn.setText("PGN");

        lblOpenVal.setFont(lblOpenVal.getFont());
        lblOpenVal.setForeground(new java.awt.Color(102, 0, 0));
        lblOpenVal.setText("Open Val");

        lblCloseVal.setFont(lblCloseVal.getFont());
        lblCloseVal.setForeground(new java.awt.Color(102, 0, 0));
        lblCloseVal.setText("Close Val");

        spinDoor1CloseVal.setFont(spinDoor1CloseVal.getFont().deriveFont(spinDoor1CloseVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinDoor1CloseVal.getFont().getSize()+5));

        spinDoor2CloseVal.setFont(spinDoor2CloseVal.getFont().deriveFont(spinDoor2CloseVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinDoor2CloseVal.getFont().getSize()+5));

        chkCanDataEnable.setBackground(new java.awt.Color(255, 255, 255));
        chkCanDataEnable.setFont(chkCanDataEnable.getFont().deriveFont(chkCanDataEnable.getFont().getStyle() | java.awt.Font.BOLD, chkCanDataEnable.getFont().getSize()+6));
        chkCanDataEnable.setForeground(new java.awt.Color(0, 0, 102));
        chkCanDataEnable.setText("Transmit");
        chkCanDataEnable.setActionCommand("All Transmit");
        chkCanDataEnable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCanDataEnableItemStateChanged(evt);
            }
        });
        chkCanDataEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanDataEnableActionPerformed(evt);
            }
        });

        chkCanLogEnable.setBackground(new java.awt.Color(255, 255, 255));
        chkCanLogEnable.setFont(chkCanLogEnable.getFont().deriveFont(chkCanLogEnable.getFont().getStyle() | java.awt.Font.BOLD, chkCanLogEnable.getFont().getSize()+6));
        chkCanLogEnable.setForeground(new java.awt.Color(0, 0, 102));
        chkCanLogEnable.setText("Log");
        chkCanLogEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanLogEnableActionPerformed(evt);
            }
        });

        lblVideoRecord.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblVideoRecord.setForeground(new java.awt.Color(0, 0, 102));
        lblVideoRecord.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVideoRecord.setText("Video Record");

        txtVidRecordPgn.setFont(txtVidRecordPgn.getFont().deriveFont(txtVidRecordPgn.getFont().getSize()+5f));
        txtVidRecordPgn.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtVidRecordPgn.setText("64454");
        txtVidRecordPgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVidRecordPgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVidRecordPgnFocusLost(evt);
            }
        });
        txtVidRecordPgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVidRecordPgnActionPerformed(evt);
            }
        });

        txtVidRecordSpn.setFont(txtVidRecordSpn.getFont().deriveFont(txtVidRecordSpn.getFont().getSize()+5f));
        txtVidRecordSpn.setText("1");
        txtVidRecordSpn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVidRecordSpnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVidRecordSpnFocusLost(evt);
            }
        });
        txtVidRecordSpn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVidRecordSpnActionPerformed(evt);
            }
        });

        spinVidRecordstartVal.setFont(spinVidRecordstartVal.getFont().deriveFont(spinVidRecordstartVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinVidRecordstartVal.getFont().getSize()+5));

        spinVidRecordendVal.setFont(spinVidRecordendVal.getFont().deriveFont(spinVidRecordendVal.getFont().getStyle() & ~java.awt.Font.BOLD, spinVidRecordendVal.getFont().getSize()+5));

        cmbCanBus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbCanBus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OLECTRA BYD", "OLECTRA CX2", "JBM", "PMI" }));
        cmbCanBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCanBusActionPerformed(evt);
            }
        });

        lblRevCam1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRevCam1.setForeground(new java.awt.Color(0, 0, 102));
        lblRevCam1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRevCam1.setText("Reverse Cam1");

        txtRevCamPgn1.setFont(txtRevCamPgn1.getFont().deriveFont(txtRevCamPgn1.getFont().getSize()+5f));
        txtRevCamPgn1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRevCamPgn1.setText("61445");
        txtRevCamPgn1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRevCamPgn1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRevCamPgn1FocusLost(evt);
            }
        });
        txtRevCamPgn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRevCamPgn1ActionPerformed(evt);
            }
        });

        txtRevCamSpn1.setFont(txtRevCamSpn1.getFont().deriveFont(txtRevCamSpn1.getFont().getSize()+5f));
        txtRevCamSpn1.setText("523");
        txtRevCamSpn1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRevCamSpn1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRevCamSpn1FocusLost(evt);
            }
        });
        txtRevCamSpn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRevCamSpn1ActionPerformed(evt);
            }
        });

        spinCamGearVal1.setFont(spinCamGearVal1.getFont().deriveFont(spinCamGearVal1.getFont().getStyle() & ~java.awt.Font.BOLD, spinCamGearVal1.getFont().getSize()+5));

        lblRevCam2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRevCam2.setForeground(new java.awt.Color(0, 0, 102));
        lblRevCam2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRevCam2.setText("Reverse Cam2");

        txtRevCamPgn2.setFont(txtRevCamPgn2.getFont().deriveFont(txtRevCamPgn2.getFont().getSize()+5f));
        txtRevCamPgn2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRevCamPgn2.setText("61445");
        txtRevCamPgn2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRevCamPgn2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRevCamPgn2FocusLost(evt);
            }
        });
        txtRevCamPgn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRevCamPgn2ActionPerformed(evt);
            }
        });

        txtRevCamSpn2.setFont(txtRevCamSpn2.getFont().deriveFont(txtRevCamSpn2.getFont().getSize()+5f));
        txtRevCamSpn2.setText("523");
        txtRevCamSpn2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRevCamSpn2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRevCamSpn2FocusLost(evt);
            }
        });
        txtRevCamSpn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRevCamSpn2ActionPerformed(evt);
            }
        });

        spinCamGearVal2.setFont(spinCamGearVal2.getFont().deriveFont(spinCamGearVal2.getFont().getStyle() & ~java.awt.Font.BOLD, spinCamGearVal2.getFont().getSize()+5));

        txtCamRevVal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCamRevVal.setText("124");
        txtCamRevVal.setPreferredSize(new java.awt.Dimension(34, 26));
        txtCamRevVal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCamRevValFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCamRevValFocusLost(evt);
            }
        });
        txtCamRevVal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCamRevValActionPerformed(evt);
            }
        });

        chkCanEnable1.setBackground(new java.awt.Color(255, 255, 255));
        chkCanEnable1.setFont(chkCanEnable1.getFont().deriveFont(chkCanEnable1.getFont().getStyle() | java.awt.Font.BOLD, chkCanEnable1.getFont().getSize()+6));
        chkCanEnable1.setForeground(new java.awt.Color(0, 0, 102));
        chkCanEnable1.setSelected(true);
        chkCanEnable1.setText("CAN2 ENABLE");
        chkCanEnable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanEnable1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCanHeading)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblOpenVal)
                .addGap(31, 31, 31)
                .addComponent(lblCloseVal)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chkCanDataEnable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCanLogEnable)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCanBus, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chkCanEnable)
                        .addGap(26, 26, 26)
                        .addComponent(chkCanEnable1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVideoRecord, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDoor2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDoor1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStopReq, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRevCam2))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRevCam1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtVidRecordPgn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtVidRecordSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtDoor1Pgn)
                                                    .addComponent(txtDoor2Pgn))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDoor2Spn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDoor1Spn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtStopReqPgn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtStopReqSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtRevCamPgn2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtRevCamSpn2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtRevCamPgn1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtRevCamSpn1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spinStopReqVal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spinDoor2OpenVal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spinDoor1OpenVal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spinVidRecordstartVal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spinDoor1CloseVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spinDoor2CloseVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spinVidRecordendVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(spinCamGearVal2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spinCamGearVal1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblPgn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblRevCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20)
                        .addComponent(txtRevCamPgn, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRevCamSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVal)
                            .addComponent(txtCamRevVal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDoor1Pgn, txtDoor2Pgn, txtRevCamPgn});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblName, lblRevCam, lblStopReq});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinDoor1OpenVal, spinDoor2OpenVal, spinVidRecordstartVal});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinDoor1CloseVal, spinDoor2CloseVal, spinVidRecordendVal});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDoor1Spn, txtDoor2Spn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCanHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCanEnable)
                    .addComponent(chkCanEnable1))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkCanLogEnable)
                        .addComponent(cmbCanBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkCanDataEnable, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblName)
                    .addComponent(lblPgn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRevCamSpn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRevCamPgn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRevCam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCamRevVal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinCamGearVal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRevCamSpn1)
                    .addComponent(txtRevCamPgn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRevCam1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinCamGearVal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRevCamSpn2)
                    .addComponent(txtRevCamPgn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRevCam2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinStopReqVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStopReqSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStopReqPgn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStopReq))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOpenVal)
                    .addComponent(lblCloseVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinDoor1CloseVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinDoor1OpenVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoor1Spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoor1Pgn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoor1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoor2Pgn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDoor2Spn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinDoor2OpenVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinDoor2CloseVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDoor2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVidRecordPgn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVidRecordSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinVidRecordstartVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinVidRecordendVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVideoRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDoor1Spn, txtDoor2Spn, txtStopReqSpn, txtVidRecordSpn});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblName, lblStopReq});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {spinDoor1OpenVal, spinDoor2OpenVal, spinVidRecordstartVal});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {spinDoor1CloseVal, spinDoor2CloseVal, spinVidRecordendVal});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDoor1Pgn, txtDoor2Pgn, txtRevCamPgn, txtStopReqPgn, txtVidRecordPgn});

        spinDoor2OpenVal.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void chkCanEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanEnableActionPerformed
        // TODO add your handling code here:
        if (chkCanEnable.isSelected()) {
            chkCanEnable.setText("CAN ENABLE");
        } else {
            chkCanEnable.setText("CAN DISABLE");
        }
    }//GEN-LAST:event_chkCanEnableActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
      
        try {
            if (chkCanEnable.isSelected()) {
                setCanEnabled(true);
            } else {
                setCanEnabled(false);
            }
            if (chkCanEnable1.isSelected()) {
                setCanEnabled1(true);
            } else {
                setCanEnabled1(false);
            }

            if (chkCanDataEnable.isSelected()) {
                setCanDataEnabled(true);
            } else {
                setCanDataEnabled(false);
            }

            if (chkCanLogEnable.isSelected()) {
                setCanLogEnabled(true);
            } else {
                setCanLogEnabled(false);
            }

            clsSharedVariables.setReverseCamVal(Short.parseShort(txtCamRevVal.getText().toString()));
            clsSharedVariables.setReverseCamVal(Short.parseShort(txtCamRevVal.getText().toString()));
            clsSharedVariables.setReverseCamVal1(Short.parseShort(spinCamGearVal1.getValue().toString()));
            clsSharedVariables.setReverseCamVal2(Short.parseShort(spinCamGearVal2.getValue().toString()));
            clsSharedVariables.setReverseCamPgn(Integer.parseInt(txtRevCamPgn.getText()));
            clsSharedVariables.setReverseCamPgn1(Integer.parseInt(txtRevCamPgn1.getText()));
            clsSharedVariables.setReverseCamPgn2(Integer.parseInt(txtRevCamPgn.getText()));
            clsSharedVariables.setReverseCamSpn(Integer.parseInt(txtRevCamSpn.getText()));
            clsSharedVariables.setReverseCamSpn1(Integer.parseInt(txtRevCamSpn1.getText()));
            clsSharedVariables.setReverseCamSpn2(Integer.parseInt(txtRevCamSpn2.getText()));

            clsSharedVariables.setStopReqVal(Short.parseShort(spinStopReqVal.getValue().toString()));
            clsSharedVariables.setStopReqPgn(Integer.parseInt(txtStopReqPgn.getText()));
            clsSharedVariables.setStopReqSpn(Integer.parseInt(txtStopReqSpn.getText()));

            clsSharedVariables.setDoor1OpenVal(Short.parseShort(this.spinDoor1OpenVal.getValue().toString()));
            clsSharedVariables.setDoor1CloseVal(Short.parseShort(spinDoor1CloseVal.getValue().toString()));
            clsSharedVariables.setDoor1Pgn(Integer.parseInt(txtDoor1Pgn.getText()));
            clsSharedVariables.setDoor1Spn(Integer.parseInt(txtDoor1Spn.getText()));

            clsSharedVariables.setDoor2OpenVal(Short.parseShort(this.spinDoor2OpenVal.getValue().toString()));
            clsSharedVariables.setDoor2CloseVal(Short.parseShort(spinDoor2CloseVal.getValue().toString()));
            clsSharedVariables.setDoor2Pgn(Integer.parseInt(txtDoor2Pgn.getText()));
            clsSharedVariables.setDoor2Spn(Integer.parseInt(txtDoor2Spn.getText()));

            clsSharedVariables.setVideoRecordStartVal(Short.parseShort(this.spinVidRecordstartVal.getValue().toString()));
            clsSharedVariables.setVideoRecordEndVal(Short.parseShort(spinVidRecordendVal.getValue().toString()));
            clsSharedVariables.setVideoRecordPgn(Integer.parseInt(txtVidRecordPgn.getText()));
            clsSharedVariables.setVideoRecordSpn(Integer.parseInt(txtVidRecordSpn.getText()));

            switch (this.cmbCanBus.getSelectedIndex()) {
                case 0:
                    setCanBusType(OLECTRA_BYD);
                    break;
                case 1:
                    setCanBusType(OLECTRA_CX2);
                    break;
                case 2:
                    setCanBusType(JBM_CAN);
                    break;
                case 3:
                    setCanBusType(PMI_CAN);
                    break;
                default:
                    break;
            }
       
            clsReadFiles objReadFiles = new clsReadFiles();
            if (objReadFiles.write_cfg_data_file()) {
                lblMsg.setText("Configuration Saved");
                if (getCanEnabled() == false) {
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to Delete the Previous CAN DATA?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if ((result == JOptionPane.YES_OPTION)) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {

                                File[] files = null;
                                File f = null;
                                File f1 = null;
                                f1 = new File(media_filepath.getAbsolutePath() + "/canstoreddata2");
                                f = new File(media_filepath.getAbsolutePath() + "/canstoreddata");
                                int i = 0;
                                try {
                                    if (f.exists()) {
                                        files = f.listFiles();
                                        if (files == null) {                                      
                                            return;
                                        }
                                     
                                    }
                                } catch (Exception e) {

                                } finally {
                                    f = null;
                                }
                                try {
                                    if (f1.exists()) {
                                        files = f1.listFiles();
                                        if (files == null) {
                                            return;
                                        }
                                       
                                    }
                                } catch (Exception e) {

                                } finally {
                                    files = null;
                                    f1 = null;
                                }
                            }
                        });
                        lblMsg.setText("Deleted CAN DATA");
                    }

                }

            } else {
                lblMsg.setText("Configuration Not Saved");

            }

            objReadFiles = null;

        } catch (Exception ex) {
            lblMsg.setText("Configuration Not Saved");

        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtRevCamPgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamPgnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Pgn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Pgn", txtRevCamPgn.getText());
            if (str != null) {
                txtRevCamPgn.setText(str);
            }
            prev_control_name = "Rev Cam Pgn";
            obj = null;
            str = null;
            txtRevCamPgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtRevCamPgnFocusGained

    private void txtRevCamSpnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamSpnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Spn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Spn", txtRevCamSpn.getText());
            if (str != null) {
                txtRevCamSpn.setText(str);
            }
            prev_control_name = "Rev Cam Spn";
            obj = null;
            str = null;
            txtRevCamSpn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtRevCamSpnFocusGained

    private void txtRevCamPgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamPgnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtRevCamPgnFocusLost

    private void txtRevCamSpnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamSpnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtRevCamSpnFocusLost

    private void txtStopReqPgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStopReqPgnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Stp Req Pgn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Stp Req Pgn", txtStopReqPgn.getText());
            if (str != null) {
                txtStopReqPgn.setText(str);
            }
            prev_control_name = "Stp Req Pgn";
            obj = null;
            str = null;
            txtStopReqPgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtStopReqPgnFocusGained

    private void txtStopReqPgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStopReqPgnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtStopReqPgnFocusLost

    private void txtStopReqSpnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStopReqSpnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Stp Req Spn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Stp Req Spn", txtStopReqSpn.getText());
            if (str != null) {
                txtStopReqSpn.setText(str);
            }
            prev_control_name = "Stp Req Spn";
            obj = null;
            str = null;
            txtStopReqSpn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtStopReqSpnFocusGained

    private void txtStopReqSpnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStopReqSpnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtStopReqSpnFocusLost

    private void txtDoor2PgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor2PgnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Door 2 Pgn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Door 2 Pgn", txtDoor2Pgn.getText());
            if (str != null) {
                txtDoor2Pgn.setText(str);
            }
            prev_control_name = "Door 2 Pgn";
            obj = null;
            str = null;
            txtDoor2Pgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDoor2PgnFocusGained

    private void txtDoor2PgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor2PgnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDoor2PgnFocusLost

    private void txtDoor1SpnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor1SpnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Door 1 Spn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Door 1 Spn", txtDoor1Spn.getText());
            if (str != null) {
                txtDoor1Spn.setText(str);
            }
            prev_control_name = "Door 1 Spn";
            obj = null;
            str = null;
            txtDoor1Spn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDoor1SpnFocusGained

    private void txtDoor1SpnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor1SpnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDoor1SpnFocusLost

    private void txtDoor1PgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor1PgnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Door 1 Pgn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Door 1 Pgn", txtDoor1Pgn.getText());
            if (str != null) {
                txtDoor1Pgn.setText(str);
            }
            prev_control_name = "Door 1 Pgn";
            obj = null;
            str = null;
            txtDoor1Pgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDoor1PgnFocusGained

    private void txtDoor1PgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor1PgnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDoor1PgnFocusLost

    private void txtDoor2SpnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor2SpnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Door 2 Spn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Door 2 Spn", txtDoor2Spn.getText());
            if (str != null) {
                txtDoor2Spn.setText(str);
            }
            prev_control_name = "Door 2 Spn";
            obj = null;
            str = null;
            txtDoor2Spn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDoor2SpnFocusGained

    private void txtDoor2SpnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDoor2SpnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDoor2SpnFocusLost

    private void txtRevCamSpnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRevCamSpnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevCamSpnActionPerformed

    private void txtDoor2SpnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoor2SpnActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDoor2SpnActionPerformed

    private void txtDoor1PgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoor1PgnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoor1PgnActionPerformed

    private void chkCanDataEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanDataEnableActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkCanDataEnableActionPerformed

    private void chkCanLogEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanLogEnableActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_chkCanLogEnableActionPerformed

    private void txtStopReqSpnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStopReqSpnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStopReqSpnActionPerformed

    private void txtVidRecordPgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVidRecordPgnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Video Record PGN")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Video Record PGN", txtVidRecordPgn.getText());
            if (str != null) {
                txtVidRecordPgn.setText(str);
            }
            prev_control_name = "Video Record PGN";
            obj = null;
            str = null;
            txtVidRecordPgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }

    }//GEN-LAST:event_txtVidRecordPgnFocusGained

    private void txtVidRecordPgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVidRecordPgnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtVidRecordPgnFocusLost

    private void txtVidRecordPgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVidRecordPgnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVidRecordPgnActionPerformed

    private void txtVidRecordSpnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVidRecordSpnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Video Record Spn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Video Record Spn", txtVidRecordSpn.getText());
            if (str != null) {
                txtVidRecordSpn.setText(str);
            }
            prev_control_name = "Video Record Spn";
            obj = null;
            str = null;
            txtVidRecordSpn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtVidRecordSpnFocusGained

    private void txtVidRecordSpnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVidRecordSpnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtVidRecordSpnFocusLost

    private void chkCanDataEnableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCanDataEnableItemStateChanged
        // TODO add your handling code here:


    }//GEN-LAST:event_chkCanDataEnableItemStateChanged

    private void cmbCanBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCanBusActionPerformed
        // TODO add your handling code here:
        if (cmbCanBus.getSelectedIndex() == clsDefines.JBM_CAN) {
            lblDoor1.setVisible(true);
            lblDoor2.setVisible(true);
            lblVideoRecord.setVisible(true);
            txtDoor1Pgn.setVisible(true);
            txtDoor2Pgn.setVisible(true);
            txtVidRecordPgn.setVisible(true);
            txtDoor1Spn.setVisible(true);
            txtDoor2Spn.setVisible(true);
            txtVidRecordSpn.setVisible(true);
            spinDoor1OpenVal.setVisible(true);
            spinDoor2OpenVal.setVisible(true);
            spinVidRecordstartVal.setVisible(true);
            spinDoor1CloseVal.setVisible(true);
            spinDoor2CloseVal.setVisible(true);
            spinVidRecordendVal.setVisible(true);
            lblOpenVal.setVisible(true);
            lblCloseVal.setVisible(true);
            lblStopReq.setVisible(true);
            txtStopReqPgn.setVisible(true);
            txtStopReqSpn.setVisible(true);
            spinStopReqVal.setVisible(true);
        } else {
            lblDoor1.setVisible(false);
            lblDoor2.setVisible(false);
            lblVideoRecord.setVisible(false);
            txtDoor1Pgn.setVisible(false);
            txtDoor2Pgn.setVisible(false);
            txtVidRecordPgn.setVisible(false);
            txtDoor1Spn.setVisible(false);
            txtDoor2Spn.setVisible(false);
            txtVidRecordSpn.setVisible(false);
            spinDoor1OpenVal.setVisible(false);
            spinDoor2OpenVal.setVisible(false);
            spinVidRecordstartVal.setVisible(false);
            spinDoor1CloseVal.setVisible(false);
            spinDoor2CloseVal.setVisible(false);
            spinVidRecordendVal.setVisible(false);
            lblOpenVal.setVisible(false);
            lblCloseVal.setVisible(false);
            lblStopReq.setVisible(false);
            txtStopReqPgn.setVisible(false);
            txtStopReqSpn.setVisible(false);
            spinStopReqVal.setVisible(false);

        }
    }//GEN-LAST:event_cmbCanBusActionPerformed

    private void txtRevCamPgn1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamPgn1FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Pgn1")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Pgn1", txtRevCamPgn1.getText());
            if (str != null) {
                txtRevCamPgn1.setText(str);
            }
            prev_control_name = "Rev Cam Pgn1";
            obj = null;
            str = null;
            txtRevCamPgn1.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtRevCamPgn1FocusGained

    private void txtRevCamPgn1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamPgn1FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtRevCamPgn1FocusLost

    private void txtRevCamSpn1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamSpn1FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Spn1")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Spn1", txtRevCamSpn1.getText());
            if (str != null) {
                txtRevCamSpn1.setText(str);
            }
            prev_control_name = "Rev Cam Spn1";
            obj = null;
            str = null;
            txtRevCamSpn1.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtRevCamSpn1FocusGained

    private void txtRevCamSpn1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamSpn1FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtRevCamSpn1FocusLost

    private void txtRevCamSpn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRevCamSpn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevCamSpn1ActionPerformed

    private void txtRevCamPgn2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamPgn2FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Pgn2")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Pgn2", txtRevCamPgn2.getText());
            if (str != null) {
                txtRevCamPgn2.setText(str);
            }
            prev_control_name = "Rev Cam Pgn2";
            obj = null;
            str = null;
            txtRevCamPgn2.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtRevCamPgn2FocusGained

    private void txtRevCamPgn2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamPgn2FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtRevCamPgn2FocusLost

    private void txtRevCamSpn2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamSpn2FocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Spn2")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Spn2", txtRevCamSpn2.getText());
            if (str != null) {
                txtRevCamSpn2.setText(str);
            }
            prev_control_name = "Rev Cam Spn2";
            obj = null;
            str = null;
            txtRevCamSpn2.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtRevCamSpn2FocusGained

    private void txtRevCamSpn2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRevCamSpn2FocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtRevCamSpn2FocusLost

    private void txtRevCamSpn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRevCamSpn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevCamSpn2ActionPerformed

    private void txtRevCamPgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRevCamPgnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevCamPgnActionPerformed

    private void txtRevCamPgn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRevCamPgn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevCamPgn2ActionPerformed

    private void txtRevCamPgn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRevCamPgn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRevCamPgn1ActionPerformed

    private void txtCamRevValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCamRevValActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamRevValActionPerformed

    private void txtCamRevValFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCamRevValFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Rev Cam Rev Val")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Rev Cam Rev Val", txtCamRevVal.getText());
            if (str != null) {
                txtCamRevVal.setText(str);
            }
            prev_control_name = "Rev Cam Rev Val";
            obj = null;
            str = null;
            txtCamRevVal.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCamRevValFocusGained

    private void txtCamRevValFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCamRevValFocusLost
        // TODO add your handling code here:\
        prev_control_name = "";
    }//GEN-LAST:event_txtCamRevValFocusLost

    private void chkCanEnable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanEnable1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCanEnable1ActionPerformed

    private void txtVidRecordSpnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVidRecordSpnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVidRecordSpnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkCanDataEnable;
    private javax.swing.JCheckBox chkCanEnable;
    private javax.swing.JCheckBox chkCanEnable1;
    private javax.swing.JCheckBox chkCanLogEnable;
    private javax.swing.JComboBox<String> cmbCanBus;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField lblCanHeading;
    private javax.swing.JLabel lblCloseVal;
    private javax.swing.JLabel lblDoor1;
    private javax.swing.JLabel lblDoor2;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOpenVal;
    private javax.swing.JLabel lblPgn;
    private javax.swing.JLabel lblRevCam;
    private javax.swing.JLabel lblRevCam1;
    private javax.swing.JLabel lblRevCam2;
    private javax.swing.JLabel lblSpn;
    private javax.swing.JLabel lblStopReq;
    private javax.swing.JLabel lblVal;
    private javax.swing.JLabel lblVideoRecord;
    private javax.swing.JSpinner spinCamGearVal1;
    private javax.swing.JSpinner spinCamGearVal2;
    private javax.swing.JSpinner spinDoor1CloseVal;
    private javax.swing.JSpinner spinDoor1OpenVal;
    private javax.swing.JSpinner spinDoor2CloseVal;
    private javax.swing.JSpinner spinDoor2OpenVal;
    private javax.swing.JSpinner spinStopReqVal;
    private javax.swing.JSpinner spinVidRecordendVal;
    private javax.swing.JSpinner spinVidRecordstartVal;
    private javax.swing.JTextField txtCamRevVal;
    private javax.swing.JTextField txtDoor1Pgn;
    private javax.swing.JTextField txtDoor1Spn;
    private javax.swing.JTextField txtDoor2Pgn;
    private javax.swing.JTextField txtDoor2Spn;
    private javax.swing.JTextField txtRevCamPgn;
    private javax.swing.JTextField txtRevCamPgn1;
    private javax.swing.JTextField txtRevCamPgn2;
    private javax.swing.JTextField txtRevCamSpn;
    private javax.swing.JTextField txtRevCamSpn1;
    private javax.swing.JTextField txtRevCamSpn2;
    private javax.swing.JTextField txtStopReqPgn;
    private javax.swing.JTextField txtStopReqSpn;
    private javax.swing.JTextField txtVidRecordPgn;
    private javax.swing.JTextField txtVidRecordSpn;
    // End of variables declaration//GEN-END:variables
}
