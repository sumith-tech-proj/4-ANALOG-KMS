package obuits;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import static obuits.clsDefines.config_filepath;
import static obuits.clsDefines.media_filepath;

public class PanCanElectricalSystems extends javax.swing.JPanel {

    byte can_id = 0;
    int index = 0;
    String prev_control_name = "";

    public PanCanElectricalSystems(byte l_can_id) {
        initComponents();
        byte i = 0;
        can_id = l_can_id;
        ArrayList items = new ArrayList();
        DefaultListModel<String> model = new DefaultListModel<>();
        index = 0;
        try {
            if (can_id == 0) {
                for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {

                    items.add(PanCanConfig.objCanElectrical[i].name);

                    lblCanName.setText(PanCanConfig.objCanElectrical[index].name);
                    txtPgn.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].pgn));
                    txtSpn.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].spn));
                    txtMin.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].min));
                    txtMax.setText(String.valueOf(PanCanConfig.objCanElectrical[index].max));
                    this.txThreshold.setText(String.valueOf(PanCanConfig.objCanElectrical[index].threshold));
                    lblUnit.setText(String.valueOf(PanCanConfig.objCanElectrical[index].unit));

                    this.chkLog.setSelected(PanCanConfig.objCanElectrical[index].log_enable);
                    this.chkDisplay.setSelected(PanCanConfig.objCanElectrical[index].display_enable);
                    this.chkTransmit.setSelected(PanCanConfig.objCanElectrical[index].transmit_enable);
                    this.chkAlarm.setSelected(PanCanConfig.objCanElectrical[index].alert_enable);
                    this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].alarm_id));

                    this.txtCanId.setText(String.valueOf(PanCanConfig.objCanElectrical[index].can_id));
                    this.txtFactor.setText(String.valueOf(PanCanConfig.objCanElectrical[index].factor));
                    this.txtOffset.setText(String.valueOf(PanCanConfig.objCanElectrical[index].offset));
                    this.spinByteVal.setValue(PanCanConfig.objCanElectrical[index].byte_pos);
                    this.spinByteLen.setValue(PanCanConfig.objCanElectrical[index].byte_length);
                    this.spinBitPos.setValue(PanCanConfig.objCanElectrical[index].bit_pos);
                    this.spinBitLen.setValue(PanCanConfig.objCanElectrical[index].bit_length);
                    this.chkReverse.setSelected(PanCanConfig.objCanElectrical[index].reverse);

                    // }
                }
            } else if (can_id == 1) {
                for (i = 0; i < PanCanConfig.objCanSafetyCnt; i++) {
                    items.add(PanCanConfig.objCanSafety[i].name);
                    lblCanName.setText(PanCanConfig.objCanSafety[index].name);
                    txtPgn.setText(String.valueOf(PanCanConfig.objCanSafety[index].pgn));
                    txtSpn.setText(String.valueOf(PanCanConfig.objCanSafety[index].spn));
                    txtMin.setText(String.valueOf(PanCanConfig.objCanSafety[index].min));
                    txtMax.setText(String.valueOf(PanCanConfig.objCanSafety[index].max));
                    this.txThreshold.setText(String.valueOf(PanCanConfig.objCanSafety[index].threshold));
                    lblUnit.setText(String.valueOf(PanCanConfig.objCanSafety[index].unit));
                    this.chkLog.setSelected(PanCanConfig.objCanSafety[index].log_enable);
                    this.chkDisplay.setSelected(PanCanConfig.objCanSafety[index].display_enable);
                    this.chkTransmit.setSelected(PanCanConfig.objCanSafety[index].transmit_enable);
                    this.chkAlarm.setSelected(PanCanConfig.objCanSafety[index].alert_enable);
                    this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanSafety[index].alarm_id));
                    this.txtCanId.setText(String.valueOf(PanCanConfig.objCanSafety[index].can_id));
                    this.txtFactor.setText(String.valueOf(PanCanConfig.objCanSafety[index].factor));
                    this.txtOffset.setText(String.valueOf(PanCanConfig.objCanSafety[index].offset));
                    this.spinByteVal.setValue((PanCanConfig.objCanSafety[index].byte_pos));
                    this.spinByteLen.setValue((PanCanConfig.objCanSafety[index].byte_length));
                    this.spinBitPos.setValue((PanCanConfig.objCanSafety[index].bit_pos));
                    this.spinBitLen.setValue((PanCanConfig.objCanSafety[index].bit_length));
                    this.chkReverse.setSelected(PanCanConfig.objCanSafety[index].reverse);

                }
            } else if (can_id == 2) {
                for (i = 0; i < PanCanConfig.objCanTransmitCnt; i++) {
                    items.add(PanCanConfig.objCanTransmit[i].name);
                    lblCanName.setText(PanCanConfig.objCanTransmit[index].name);
                    txtPgn.setText(String.valueOf(PanCanConfig.objCanTransmit[index].pgn));
                    txtSpn.setText(String.valueOf(PanCanConfig.objCanTransmit[index].spn));
                    txtMin.setText(String.valueOf(PanCanConfig.objCanTransmit[index].min));
                    txtMax.setText(String.valueOf(PanCanConfig.objCanTransmit[index].max));
                    this.txThreshold.setText(String.valueOf(PanCanConfig.objCanTransmit[index].threshold));
                    lblUnit.setText(String.valueOf(PanCanConfig.objCanTransmit[index].unit));
                    this.chkLog.setSelected(PanCanConfig.objCanTransmit[index].log_enable);
                    this.chkDisplay.setSelected(PanCanConfig.objCanTransmit[index].display_enable);
                    this.chkTransmit.setSelected(PanCanConfig.objCanTransmit[index].transmit_enable);
                    this.chkAlarm.setSelected(PanCanConfig.objCanTransmit[index].alert_enable);
                    this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanTransmit[index].alarm_id));

                    this.txtCanId.setText(String.valueOf(PanCanConfig.objCanTransmit[index].can_id));
                    this.txtFactor.setText(String.valueOf(PanCanConfig.objCanTransmit[index].factor));
                    this.txtOffset.setText(String.valueOf(PanCanConfig.objCanTransmit[index].offset));
                    this.spinByteVal.setValue((PanCanConfig.objCanTransmit[index].byte_pos));
                    this.spinByteLen.setValue((PanCanConfig.objCanTransmit[index].byte_length));
                    this.spinBitPos.setValue((PanCanConfig.objCanTransmit[index].bit_pos));
                    this.spinBitLen.setValue((PanCanConfig.objCanTransmit[index].bit_length));
                    this.chkReverse.setSelected(PanCanConfig.objCanTransmit[index].reverse);
                    // }
                }
            } else if (can_id == 3) {
                for (i = 0; i < PanCanConfig.objCanEngineCnt; i++) {
                    items.add(PanCanConfig.objCanEngine[i].name);

                    lblCanName.setText(PanCanConfig.objCanEngine[index].name);
                    txtPgn.setText(String.valueOf(PanCanConfig.objCanEngine[index].pgn));
                    txtSpn.setText(String.valueOf(PanCanConfig.objCanEngine[index].spn));
                    txtMin.setText(String.valueOf(PanCanConfig.objCanEngine[index].min));
                    txtMax.setText(String.valueOf(PanCanConfig.objCanEngine[index].max));
                    this.txThreshold.setText(String.valueOf(PanCanConfig.objCanEngine[index].threshold));
                    lblUnit.setText(String.valueOf(PanCanConfig.objCanEngine[index].unit));
                    this.chkLog.setSelected(PanCanConfig.objCanEngine[index].log_enable);
                    this.chkDisplay.setSelected(PanCanConfig.objCanEngine[index].display_enable);
                    this.chkTransmit.setSelected(PanCanConfig.objCanEngine[index].transmit_enable);
                    this.chkAlarm.setSelected(PanCanConfig.objCanEngine[index].alert_enable);
                    this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanEngine[index].alarm_id));

                    this.txtCanId.setText(String.valueOf(PanCanConfig.objCanEngine[index].can_id));
                    this.txtFactor.setText(String.valueOf(PanCanConfig.objCanEngine[index].factor));
                    this.txtOffset.setText(String.valueOf(PanCanConfig.objCanEngine[index].offset));
                    this.spinByteVal.setValue((PanCanConfig.objCanEngine[index].byte_pos));
                    this.spinByteLen.setValue((PanCanConfig.objCanEngine[index].byte_length));
                    this.spinBitPos.setValue((PanCanConfig.objCanEngine[index].bit_pos));
                    this.spinBitLen.setValue((PanCanConfig.objCanEngine[index].bit_length));
                    this.chkReverse.setSelected(PanCanConfig.objCanEngine[index].reverse);
                    // }
                }
            } else if (can_id == 4) {
                for (i = 0; i < PanCanConfig.objCanOthersCnt; i++) {
                    items.add(PanCanConfig.objCanOthers[i].name);

                    lblCanName.setText(PanCanConfig.objCanOthers[index].name);
                    txtPgn.setText(String.valueOf(PanCanConfig.objCanOthers[index].pgn));
                    txtSpn.setText(String.valueOf(PanCanConfig.objCanOthers[index].spn));
                    txtMin.setText(String.valueOf(PanCanConfig.objCanOthers[index].min));
                    txtMax.setText(String.valueOf(PanCanConfig.objCanOthers[index].max));
                    this.txThreshold.setText(String.valueOf(PanCanConfig.objCanOthers[index].threshold));
                    lblUnit.setText(String.valueOf(PanCanConfig.objCanOthers[index].unit));
                    this.chkLog.setSelected(PanCanConfig.objCanOthers[index].log_enable);
                    this.chkDisplay.setSelected(PanCanConfig.objCanOthers[index].display_enable);
                    this.chkTransmit.setSelected(PanCanConfig.objCanOthers[index].transmit_enable);
                    this.chkAlarm.setSelected(PanCanConfig.objCanOthers[index].alert_enable);
                    this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanOthers[index].alarm_id));

                    try {
                        this.txtCanId.setText(String.valueOf(PanCanConfig.objCanOthers[index].can_id));
                        this.txtFactor.setText(String.valueOf(PanCanConfig.objCanOthers[index].factor));
                        this.txtOffset.setText(String.valueOf(PanCanConfig.objCanOthers[index].offset));
                        this.spinByteVal.setValue(Byte.valueOf(PanCanConfig.objCanOthers[index].byte_pos));
                        this.spinByteLen.setValue(Byte.valueOf(PanCanConfig.objCanOthers[index].byte_length));
                        this.spinBitPos.setValue(Byte.valueOf(PanCanConfig.objCanOthers[index].bit_pos));
                        this.spinBitLen.setValue(Byte.valueOf(PanCanConfig.objCanOthers[index].bit_length));
                        this.chkReverse.setSelected((PanCanConfig.objCanOthers[index].reverse));
                    } catch (Exception ex) {

                    }
                }
            }

        } catch (Exception ex) {

        }

        items.stream().forEach((s) -> {
            model.addElement((String) s);
        });

        lstCanNames.setModel(model);
        lstCanNames.getSelectionModel().setLeadSelectionIndex(0);
        lblMsg.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        lblCanName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        chkTransmit = new javax.swing.JCheckBox();
        chkAlarm = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        txtMin = new javax.swing.JTextField();
        txtMax = new javax.swing.JTextField();
        lblMsg = new javax.swing.JLabel();
        txtPgn = new javax.swing.JTextField();
        txtSpn = new javax.swing.JTextField();
        lblUnit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlarmId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        spinBitPos = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        spinBitLen = new javax.swing.JSpinner();
        chkReverse = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        spinByteVal = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        spinByteLen = new javax.swing.JSpinner();
        chkDisplay = new javax.swing.JCheckBox();
        chkLog = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        txtFactor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtOffset = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCanId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txThreshold = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCanNames = new javax.swing.JList();

        setBackground(new java.awt.Color(51, 51, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));
        setPreferredSize(new java.awt.Dimension(680, 467));

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setLastDividerLocation(200);
        jSplitPane1.setOneTouchExpandable(true);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(500, 304));

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(540, 440));

        lblCanName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCanName.setForeground(new java.awt.Color(255, 255, 255));
        lblCanName.setText("Name");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PGN");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SPN");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Min");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Max");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Unit");

        chkTransmit.setText("Transmit");
        chkTransmit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTransmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTransmitActionPerformed(evt);
            }
        });

        chkAlarm.setText("Alarm");
        chkAlarm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jPanel3.setBackground(new java.awt.Color(23, 29, 32));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btnUpdate.setBackground(new java.awt.Color(85, 85, 85));
        btnUpdate.setFont(btnUpdate.getFont().deriveFont(btnUpdate.getFont().getStyle() | java.awt.Font.BOLD, btnUpdate.getFont().getSize()+3));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        txtMin.setText("1");
        txtMin.setPreferredSize(new java.awt.Dimension(100, 30));
        txtMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMinFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMinFocusLost(evt);
            }
        });

        txtMax.setText("5");
        txtMax.setPreferredSize(new java.awt.Dimension(100, 30));
        txtMax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaxFocusLost(evt);
            }
        });
        txtMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Data Updated");

        txtPgn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPgn.setText("1234");
        txtPgn.setPreferredSize(new java.awt.Dimension(100, 30));
        txtPgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPgnFocusLost(evt);
            }
        });
        txtPgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPgnActionPerformed(evt);
            }
        });

        txtSpn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtSpn.setText("1234");
        txtSpn.setPreferredSize(new java.awt.Dimension(100, 30));
        txtSpn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSpnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSpnFocusLost(evt);
            }
        });

        lblUnit.setText("deg C");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Alarm ID");

        txtAlarmId.setText("1");
        txtAlarmId.setPreferredSize(new java.awt.Dimension(100, 30));
        txtAlarmId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAlarmIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlarmIdFocusLost(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(23, 29, 32));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Start Bit Pos");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Bit Length");

        chkReverse.setText("Reverse");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinBitPos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinBitLen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkReverse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinBitLen, spinBitPos});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(spinBitPos, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addComponent(spinBitLen)
                    .addComponent(chkReverse))
                .addGap(7, 7, 7))
        );

        jPanel4.setBackground(new java.awt.Color(23, 29, 32));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Start Byte Pos");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Byte Length");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinByteVal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinByteLen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinByteLen, spinByteVal});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(spinByteVal)
                    .addComponent(jLabel14)
                    .addComponent(spinByteLen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        chkDisplay.setText("Display");
        chkDisplay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDisplayActionPerformed(evt);
            }
        });

        chkLog.setText("LOG");
        chkLog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Factor");

        txtFactor.setText("0");
        txtFactor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFactorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFactorFocusLost(evt);
            }
        });
        txtFactor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFactorActionPerformed(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Offset");

        txtOffset.setText("0");
        txtOffset.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOffsetFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOffsetFocusLost(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("CAN ID");

        txtCanId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtCanId.setText("1234");
        txtCanId.setPreferredSize(new java.awt.Dimension(100, 30));
        txtCanId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCanIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCanIdFocusLost(evt);
            }
        });
        txtCanId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCanIdActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Threshold");

        txThreshold.setText("5");
        txThreshold.setPreferredSize(new java.awt.Dimension(100, 20));
        txThreshold.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txThresholdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txThresholdFocusLost(evt);
            }
        });
        txThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txThresholdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCanId, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPgn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSpn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel10)))
                                    .addComponent(txtOffset, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAlarm)
                            .addComponent(chkTransmit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAlarmId, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chkLog, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblCanName, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel4, jLabel6, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chkDisplay, chkLog, chkTransmit});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblUnit, txThreshold, txtFactor, txtMax, txtMin, txtOffset, txtPgn, txtSpn});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblCanName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCanId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(txtPgn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel15)
                            .addComponent(txtFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txtOffset, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(lblUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkAlarm)
                    .addComponent(jLabel3)
                    .addComponent(txtAlarmId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkTransmit)
                    .addComponent(chkDisplay)
                    .addComponent(chkLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel4, jLabel6, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblUnit, txThreshold, txtFactor, txtMax, txtMin, txtOffset, txtPgn, txtSpn});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chkDisplay, chkLog, chkTransmit});

        jSplitPane1.setRightComponent(jPanel1);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 130));

        lstCanNames.setBackground(new java.awt.Color(23, 29, 32));
        lstCanNames.setFont(lstCanNames.getFont().deriveFont(lstCanNames.getFont().getStyle() | java.awt.Font.BOLD));
        lstCanNames.setForeground(new java.awt.Color(255, 255, 255));
        lstCanNames.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Low Beam Head Light", "High Beam Head Light", "Tractor Marker Light", "Left Turn Signal Lights", "Right Turn Signal Lights", "OEM Option1 Light", "Back Up Light and Alarm Horn", "Tractor Front Fog Lights", "ITS Supply", "Transmission Engine Crank Enable", "Front Operator Wiper Switch", "Emergency stop / status" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstCanNames.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCanNames.setFixedCellHeight(25);
        lstCanNames.setFixedCellWidth(50);
        lstCanNames.setPreferredSize(new java.awt.Dimension(130, 1500));
        lstCanNames.setSelectionBackground(new java.awt.Color(204, 204, 0));
        lstCanNames.setVisibleRowCount(13);
        lstCanNames.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCanNamesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstCanNames);

        jSplitPane1.setLeftComponent(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstCanNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCanNamesValueChanged
        // TODO add your handling code here:
        index = lstCanNames.getSelectedIndex();
        lblMsg.setText("");
        if (can_id == 0) {
            lblCanName.setText(PanCanConfig.objCanElectrical[index].name);
            txtPgn.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].pgn));
            txtSpn.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].spn));
            txtMin.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].min));
            txtMax.setText(String.valueOf(PanCanConfig.objCanElectrical[index].max));
            this.txThreshold.setText(String.valueOf(PanCanConfig.objCanElectrical[index].threshold));
            lblUnit.setText(String.valueOf(PanCanConfig.objCanElectrical[index].unit));
            this.chkLog.setSelected(PanCanConfig.objCanElectrical[index].log_enable);
            this.chkDisplay.setSelected(PanCanConfig.objCanElectrical[index].display_enable);
            this.chkTransmit.setSelected(PanCanConfig.objCanElectrical[index].transmit_enable);
            this.chkAlarm.setSelected(PanCanConfig.objCanElectrical[index].alert_enable);
            this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanElectrical[index].alarm_id));

            this.txtCanId.setText(String.valueOf(PanCanConfig.objCanElectrical[index].can_id));
            this.txtFactor.setText(String.valueOf(PanCanConfig.objCanElectrical[index].factor));
            this.txtOffset.setText(String.valueOf(PanCanConfig.objCanElectrical[index].offset));
            this.spinByteVal.setValue(PanCanConfig.objCanElectrical[index].byte_pos);
            this.spinByteLen.setValue(PanCanConfig.objCanElectrical[index].byte_length);
            this.spinBitPos.setValue(PanCanConfig.objCanElectrical[index].bit_pos);
            this.spinBitLen.setValue(PanCanConfig.objCanElectrical[index].bit_length);
            this.chkReverse.setSelected(PanCanConfig.objCanElectrical[index].reverse);

        } else if (can_id == 1) {
            lblCanName.setText(PanCanConfig.objCanSafety[index].name);
            txtPgn.setText(String.valueOf(PanCanConfig.objCanSafety[index].pgn));
            txtSpn.setText(String.valueOf(PanCanConfig.objCanSafety[index].spn));
            txtMin.setText(String.valueOf(PanCanConfig.objCanSafety[index].min));
            txtMax.setText(String.valueOf(PanCanConfig.objCanSafety[index].max));
            this.txThreshold.setText(String.valueOf(PanCanConfig.objCanSafety[index].threshold));
            lblUnit.setText(String.valueOf(PanCanConfig.objCanSafety[index].unit));
            this.chkLog.setSelected(PanCanConfig.objCanSafety[index].log_enable);
            this.chkDisplay.setSelected(PanCanConfig.objCanSafety[index].display_enable);
            this.chkTransmit.setSelected(PanCanConfig.objCanSafety[index].transmit_enable);
            this.chkAlarm.setSelected(PanCanConfig.objCanSafety[index].alert_enable);
            this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanSafety[index].alarm_id));

            this.txtCanId.setText(String.valueOf(PanCanConfig.objCanSafety[index].can_id));
            this.txtFactor.setText(String.valueOf(PanCanConfig.objCanSafety[index].factor));
            this.txtOffset.setText(String.valueOf(PanCanConfig.objCanSafety[index].offset));
            this.spinByteVal.setValue(PanCanConfig.objCanSafety[index].byte_pos);
            this.spinByteLen.setValue(PanCanConfig.objCanSafety[index].byte_length);
            this.spinBitPos.setValue(PanCanConfig.objCanSafety[index].bit_pos);
            this.spinBitLen.setValue(PanCanConfig.objCanSafety[index].bit_length);
            this.chkReverse.setSelected(PanCanConfig.objCanSafety[index].reverse);
        } else if (can_id == 2) {
            lblCanName.setText(PanCanConfig.objCanTransmit[index].name);
            txtPgn.setText(String.valueOf(PanCanConfig.objCanTransmit[index].pgn));
            txtSpn.setText(String.valueOf(PanCanConfig.objCanTransmit[index].spn));
            txtMin.setText(String.valueOf(PanCanConfig.objCanTransmit[index].min));
            txtMax.setText(String.valueOf(PanCanConfig.objCanTransmit[index].max));
            this.txThreshold.setText(String.valueOf(PanCanConfig.objCanTransmit[index].threshold));
            lblUnit.setText(String.valueOf(PanCanConfig.objCanTransmit[index].unit));
            this.chkLog.setSelected(PanCanConfig.objCanTransmit[index].log_enable);
            this.chkDisplay.setSelected(PanCanConfig.objCanTransmit[index].display_enable);
            this.chkTransmit.setSelected(PanCanConfig.objCanTransmit[index].transmit_enable);
            this.chkAlarm.setSelected(PanCanConfig.objCanTransmit[index].alert_enable);
            this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanTransmit[index].alarm_id));

            this.txtCanId.setText(String.valueOf(PanCanConfig.objCanTransmit[index].can_id));
            this.txtFactor.setText(String.valueOf(PanCanConfig.objCanTransmit[index].factor));
            this.txtOffset.setText(String.valueOf(PanCanConfig.objCanTransmit[index].offset));
            this.spinByteVal.setValue(PanCanConfig.objCanTransmit[index].byte_pos);
            this.spinByteLen.setValue(PanCanConfig.objCanTransmit[index].byte_length);
            this.spinBitPos.setValue(PanCanConfig.objCanTransmit[index].bit_pos);
            this.spinBitLen.setValue(PanCanConfig.objCanTransmit[index].bit_length);
            this.chkReverse.setSelected(PanCanConfig.objCanTransmit[index].reverse);
        } else if (can_id == 3) {
            lblCanName.setText(PanCanConfig.objCanEngine[index].name);
            txtPgn.setText(String.valueOf(PanCanConfig.objCanEngine[index].pgn));
            txtSpn.setText(String.valueOf(PanCanConfig.objCanEngine[index].spn));
            txtMin.setText(String.valueOf(PanCanConfig.objCanEngine[index].min));
            txtMax.setText(String.valueOf(PanCanConfig.objCanEngine[index].max));
            this.txThreshold.setText(String.valueOf(PanCanConfig.objCanEngine[index].threshold));
            lblUnit.setText(String.valueOf(PanCanConfig.objCanEngine[index].unit));
            this.chkLog.setSelected(PanCanConfig.objCanEngine[index].log_enable);
            this.chkDisplay.setSelected(PanCanConfig.objCanEngine[index].display_enable);
            this.chkTransmit.setSelected(PanCanConfig.objCanEngine[index].transmit_enable);
            this.chkAlarm.setSelected(PanCanConfig.objCanEngine[index].alert_enable);
            this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanEngine[index].alarm_id));

            this.txtCanId.setText(String.valueOf(PanCanConfig.objCanEngine[index].can_id));
            this.txtFactor.setText(String.valueOf(PanCanConfig.objCanEngine[index].factor));
            this.txtOffset.setText(String.valueOf(PanCanConfig.objCanEngine[index].offset));
            this.spinByteVal.setValue(PanCanConfig.objCanEngine[index].byte_pos);
            this.spinByteLen.setValue(PanCanConfig.objCanEngine[index].byte_length);
            this.spinBitPos.setValue(PanCanConfig.objCanEngine[index].bit_pos);
            this.spinBitLen.setValue(PanCanConfig.objCanEngine[index].bit_length);
            this.chkReverse.setSelected(PanCanConfig.objCanEngine[index].reverse);
        } else if (can_id == 4) {
            lblCanName.setText(PanCanConfig.objCanOthers[index].name);
            txtPgn.setText(String.valueOf(PanCanConfig.objCanOthers[index].pgn));
            txtSpn.setText(String.valueOf(PanCanConfig.objCanOthers[index].spn));
            txtMin.setText(String.valueOf(PanCanConfig.objCanOthers[index].min));
            txtMax.setText(String.valueOf(PanCanConfig.objCanOthers[index].max));
            this.txThreshold.setText(String.valueOf(PanCanConfig.objCanOthers[index].threshold));
            lblUnit.setText(String.valueOf(PanCanConfig.objCanOthers[index].unit));
            this.chkLog.setSelected(PanCanConfig.objCanOthers[index].log_enable);
            this.chkDisplay.setSelected(PanCanConfig.objCanOthers[index].display_enable);
            this.chkTransmit.setSelected(PanCanConfig.objCanOthers[index].transmit_enable);
            this.chkAlarm.setSelected(PanCanConfig.objCanOthers[index].alert_enable);
            this.txtAlarmId.setText(String.valueOf((int) PanCanConfig.objCanOthers[index].alarm_id));

            this.txtCanId.setText(String.valueOf(PanCanConfig.objCanOthers[index].can_id));
            this.txtFactor.setText(String.valueOf(PanCanConfig.objCanOthers[index].factor));
            this.txtOffset.setText(String.valueOf(PanCanConfig.objCanOthers[index].offset));
            this.spinByteVal.setValue(PanCanConfig.objCanOthers[index].byte_pos);
            this.spinByteLen.setValue(PanCanConfig.objCanOthers[index].byte_length);
            this.spinBitPos.setValue(PanCanConfig.objCanOthers[index].bit_pos);
            this.spinBitLen.setValue(PanCanConfig.objCanOthers[index].bit_length);
            this.chkReverse.setSelected(PanCanConfig.objCanOthers[index].reverse);
        }
    }//GEN-LAST:event_lstCanNamesValueChanged

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (can_id == 0) {
            PanCanConfig.objCanElectrical[index].log_enable = this.chkLog.isSelected();
            PanCanConfig.objCanElectrical[index].display_enable = this.chkDisplay.isSelected();
            PanCanConfig.objCanElectrical[index].transmit_enable = this.chkTransmit.isSelected();
            PanCanConfig.objCanElectrical[index].alert_enable = this.chkAlarm.isSelected();
            try {
                PanCanConfig.objCanElectrical[index].max = Double.parseDouble(this.txtMax.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Max Value");
                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].min = Double.parseDouble(this.txtMin.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Min Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].threshold = Double.parseDouble(this.txThreshold.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Threshold   Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].pgn = Integer.parseInt(this.txtPgn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct pgn Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].spn = Integer.parseInt(this.txtSpn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct spn Value");
                return;
            }
            PanCanConfig.objCanElectrical[index].unit = (this.lblUnit.getText());

            if (this.chkAlarm.isSelected()) {
                try {
                    PanCanConfig.objCanElectrical[index].alarm_id = Short.parseShort(this.txtAlarmId.getText().trim());
                } catch (Exception ex) {
                    lblMsg.setText("Please Enter alarm id Value");

                    return;
                }
            }
            try {
                PanCanConfig.objCanElectrical[index].can_id = Long.parseLong(this.txtCanId.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct can_id Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].factor = Double.parseDouble(this.txtFactor.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Factor Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].offset = Double.valueOf(this.txtOffset.getText().trim());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct txtOffset Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].byte_pos = Byte.parseByte(this.spinByteVal.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_pos Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].byte_length = Byte.parseByte(this.spinByteLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_length Value");

                return;
            }
            try {
                PanCanConfig.objCanElectrical[index].bit_pos = Byte.parseByte(this.spinBitPos.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_pos Value");

                return;
            }

            try {
                PanCanConfig.objCanElectrical[index].bit_length = Byte.parseByte(this.spinBitLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_length Value");

                return;
            }
            PanCanConfig.objCanElectrical[index].reverse = this.chkReverse.isSelected();
            if (clsSharedVariables.getHardDriveDetected()) {
                write_canElectrical_file(media_filepath);
            }
            write_canElectrical_file(config_filepath);
            lblMsg.setText("Information Updated");

        } else if (can_id == 1) {
            PanCanConfig.objCanSafety[index].log_enable = this.chkLog.isSelected();
            PanCanConfig.objCanSafety[index].display_enable = this.chkDisplay.isSelected();
            PanCanConfig.objCanSafety[index].transmit_enable = this.chkTransmit.isSelected();
            PanCanConfig.objCanSafety[index].alert_enable = this.chkAlarm.isSelected();
            try {
                PanCanConfig.objCanSafety[index].max = Double.parseDouble(this.txtMax.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Max Value");

                return;
            }
            try {
                PanCanConfig.objCanSafety[index].min = Double.parseDouble(this.txtMin.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Min Value");

                return;
            }

            try {
                PanCanConfig.objCanSafety[index].threshold = Double.parseDouble(this.txThreshold.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Threshold Value");

                return;
            }
            try {
                PanCanConfig.objCanSafety[index].pgn = Integer.parseInt(this.txtPgn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct pgn Value");

                return;
            }
            try {
                PanCanConfig.objCanSafety[index].spn = Integer.parseInt(this.txtSpn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Spn Value");

                return;
            }
            PanCanConfig.objCanSafety[index].unit = (this.lblUnit.getText());

            if (this.chkAlarm.isSelected()) {
                try {
                    PanCanConfig.objCanSafety[index].alarm_id = Short.parseShort(this.txtAlarmId.getText().trim());
                } catch (Exception ex) {
                    lblMsg.setText("Please Enter alarm id Value");

                    return;
                }
            }
            try {
                PanCanConfig.objCanSafety[index].can_id = Long.parseLong(this.txtCanId.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct can_id Value");
                return;
            }
            try {
                PanCanConfig.objCanSafety[index].factor = Double.valueOf(this.txtFactor.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Factor Value");
                return;
            }
            try {
                PanCanConfig.objCanSafety[index].offset = Double.valueOf(this.txtOffset.getText().trim());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct txtOffset Value");
                return;
            }
            try {
                PanCanConfig.objCanSafety[index].byte_pos = Byte.parseByte(this.spinByteVal.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_pos Value");

                return;
            }
            try {
                PanCanConfig.objCanSafety[index].byte_length = Byte.parseByte(this.spinByteLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_length Value");

                return;
            }
            try {
                PanCanConfig.objCanSafety[index].bit_pos = Byte.parseByte(this.spinBitPos.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_pos Value");

                return;
            }

            try {
                PanCanConfig.objCanSafety[index].bit_length = Byte.parseByte(this.spinBitLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_length Value");

                return;
            }

            PanCanConfig.objCanSafety[index].reverse = this.chkReverse.isSelected();
            if (clsSharedVariables.getHardDriveDetected()) {
                write_canSafety_file(media_filepath);
            }
            write_canSafety_file(config_filepath);
            lblMsg.setText("Information Updated");

        } else if (can_id == 2) {
            PanCanConfig.objCanTransmit[index].log_enable = this.chkLog.isSelected();
            PanCanConfig.objCanTransmit[index].display_enable = this.chkDisplay.isSelected();
            PanCanConfig.objCanTransmit[index].transmit_enable = this.chkTransmit.isSelected();
            PanCanConfig.objCanTransmit[index].alert_enable = this.chkAlarm.isSelected();

            try {
                PanCanConfig.objCanTransmit[index].max = Double.parseDouble(this.txtMax.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Max Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].min = Double.parseDouble(this.txtMin.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Min Value");

                return;
            }

            try {
                PanCanConfig.objCanTransmit[index].threshold = Double.parseDouble(this.txThreshold.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Threshold Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].pgn = Integer.parseInt(this.txtPgn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Pgn Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].spn = Integer.parseInt(this.txtSpn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Spn Value");

                return;
            }

            PanCanConfig.objCanTransmit[index].unit = (this.lblUnit.getText());

            if (this.chkAlarm.isSelected()) {
                try {

                    PanCanConfig.objCanTransmit[index].alarm_id = Short.parseShort(this.txtAlarmId.getText().trim());

                } catch (Exception ex) {
                    lblMsg.setText("Please Enter alarm id Value");

                    return;
                }
            }

            try {
                PanCanConfig.objCanTransmit[index].can_id = Long.parseLong(this.txtCanId.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct can_id Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].factor = Double.parseDouble(this.txtFactor.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Factor Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].offset = Double.valueOf(this.txtOffset.getText().trim());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct txtOffset Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].byte_pos = Byte.parseByte(this.spinByteVal.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_pos Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].byte_length = Byte.parseByte(this.spinByteLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_length Value");

                return;
            }
            try {
                PanCanConfig.objCanTransmit[index].bit_pos = Byte.parseByte(this.spinBitPos.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_pos Value");

                return;
            }

            try {
                PanCanConfig.objCanTransmit[index].bit_length = Byte.parseByte(this.spinBitLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_length Value");

                return;
            }

            PanCanConfig.objCanTransmit[index].reverse = this.chkReverse.isSelected();
            if (clsSharedVariables.getHardDriveDetected()) {
                write_canTransmit_file(media_filepath);
            }
            write_canTransmit_file(config_filepath);

            lblMsg.setText("Information Updated");

        } else if (can_id == 3) {
            PanCanConfig.objCanEngine[index].log_enable = this.chkLog.isSelected();
            PanCanConfig.objCanEngine[index].display_enable = this.chkDisplay.isSelected();
            PanCanConfig.objCanEngine[index].transmit_enable = this.chkTransmit.isSelected();
            PanCanConfig.objCanEngine[index].alert_enable = this.chkAlarm.isSelected();
            try {
                PanCanConfig.objCanEngine[index].max = Double.parseDouble(this.txtMax.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Max Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].min = Double.parseDouble(this.txtMin.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Min Value");

                return;
            }

            try {
                PanCanConfig.objCanEngine[index].threshold = Double.parseDouble(this.txThreshold.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Threshold Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].pgn = Integer.parseInt(this.txtPgn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct pgn Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].spn = Integer.parseInt(this.txtSpn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct spn Value");

                return;
            }

            PanCanConfig.objCanEngine[index].unit = (this.lblUnit.getText());

            if (this.chkAlarm.isSelected()) {
                try {

                    PanCanConfig.objCanEngine[index].alarm_id = Short.parseShort(this.txtAlarmId.getText().trim());

                } catch (Exception ex) {
                    lblMsg.setText("Please Enter alarm id Value");

                    return;
                }
            }

            try {
                PanCanConfig.objCanEngine[index].can_id = Long.parseLong(this.txtCanId.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct can_id Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].factor = Double.parseDouble(this.txtFactor.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Factor Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].offset = Double.valueOf(this.txtOffset.getText().trim());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct txtOffset Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].byte_pos = Byte.parseByte(this.spinByteVal.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_pos Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].byte_length = Byte.parseByte(this.spinByteLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_length Value");

                return;
            }
            try {
                PanCanConfig.objCanEngine[index].bit_pos = Byte.parseByte(this.spinBitPos.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_pos Value");

                return;
            }

            try {
                PanCanConfig.objCanEngine[index].bit_length = Byte.parseByte(this.spinBitLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_length Value");

                return;
            }

            PanCanConfig.objCanEngine[index].reverse = this.chkReverse.isSelected();
            if (clsSharedVariables.getHardDriveDetected()) {
                write_canEngine_file(media_filepath);
            }
            write_canEngine_file(config_filepath);

            lblMsg.setText("Information Updated");

        } else if (can_id == 4) {
            PanCanConfig.objCanOthers[index].log_enable = this.chkLog.isSelected();
            PanCanConfig.objCanOthers[index].display_enable = this.chkDisplay.isSelected();
            PanCanConfig.objCanOthers[index].transmit_enable = this.chkTransmit.isSelected();
            PanCanConfig.objCanOthers[index].alert_enable = this.chkAlarm.isSelected();
            try {
                PanCanConfig.objCanOthers[index].max = Double.parseDouble(this.txtMax.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Max Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].min = Double.parseDouble(this.txtMin.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Min Value");

                return;
            }

            try {
                PanCanConfig.objCanOthers[index].threshold = Double.parseDouble(this.txThreshold.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Threshold Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].pgn = Integer.parseInt(this.txtPgn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Pgn Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].spn = Integer.parseInt(this.txtSpn.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct spn Value");

                return;
            }

            PanCanConfig.objCanOthers[index].unit = (this.lblUnit.getText());

            if (this.chkAlarm.isSelected()) {
                try {
                    PanCanConfig.objCanOthers[index].alarm_id = Short.parseShort(this.txtAlarmId.getText().trim());
                } catch (Exception ex) {
                    lblMsg.setText("Please Enter alarm id Value");

                    return;
                }
            }

            try {
                PanCanConfig.objCanOthers[index].can_id = Long.parseLong(this.txtCanId.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct can_id Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].factor = Double.parseDouble(this.txtFactor.getText());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct Factor Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].offset = Double.valueOf(this.txtOffset.getText().trim());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct txtOffset Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].byte_pos = Byte.parseByte(this.spinByteVal.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_pos Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].byte_length = Byte.parseByte(this.spinByteLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct byte_length Value");

                return;
            }
            try {
                PanCanConfig.objCanOthers[index].bit_pos = Byte.parseByte(this.spinBitPos.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_pos Value");

                return;
            }

            try {
                PanCanConfig.objCanOthers[index].bit_length = Byte.parseByte(this.spinBitLen.getValue().toString());
            } catch (Exception ex) {
                lblMsg.setText("Please Enter Correct bit_length Value");

                return;
            }

            PanCanConfig.objCanOthers[index].reverse = this.chkReverse.isSelected();
            if (clsSharedVariables.getHardDriveDetected()) {
                write_canOthers_file(media_filepath);
            }
            write_canOthers_file(config_filepath);

            lblMsg.setText("Information Updated");

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtPgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPgnActionPerformed
        // TODO add your handling code here:
        if (!prev_control_name.equals("pgn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("pgn", txtPgn.getText());
            if (str != null) {
                txtPgn.setText(str);
            }
            prev_control_name = "pgn";
            obj = null;
            str = null;
            txtPgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtPgnActionPerformed

    private void txtSpnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSpnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("spn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("spn", txtSpn.getText());
            if (str != null) {
                txtSpn.setText(str);
            }
            prev_control_name = "spn";
            obj = null;
            str = null;
            txtSpn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtSpnFocusGained

    private void txtMinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("min")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("min", txtMin.getText());
            if (str != null) {
                txtMin.setText(str);
            }
            prev_control_name = "min";
            obj = null;
            str = null;
            txtMin.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtMinFocusGained

    private void txtMaxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaxFocusGained
        if (!prev_control_name.equals("max")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("max", txtMax.getText());
            if (str != null) {
                txtMax.setText(str);
            }
            prev_control_name = "max";
            obj = null;
            str = null;
            txtMax.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtMaxFocusGained

    private void txtPgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPgnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtPgnFocusLost

    private void txtSpnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSpnFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtSpnFocusLost

    private void txtMinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMinFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtMinFocusLost

    private void txtMaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaxFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtMaxFocusLost

    private void txtAlarmIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlarmIdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";

    }//GEN-LAST:event_txtAlarmIdFocusLost

    private void txtAlarmIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlarmIdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("alarmid")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("alarmid", txtAlarmId.getText());
            if (str != null) {
                txtAlarmId.setText(str);
            }
            prev_control_name = "alarmid";
            obj = null;
            str = null;
            txtAlarmId.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtAlarmIdFocusGained

    private void txtCanIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCanIdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtCanIdFocusLost

    private void txtCanIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCanIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCanIdActionPerformed

    private void chkTransmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTransmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTransmitActionPerformed

    private void chkDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDisplayActionPerformed

    private void txtMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxActionPerformed

    private void txThresholdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txThresholdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Thershold")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Thershold", txThreshold.getText());
            if (str != null) {
                txThreshold.setText(str);
            }
            prev_control_name = "Thershold";
            obj = null;
            str = null;
            txThreshold.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txThresholdFocusGained

    private void txThresholdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txThresholdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txThresholdFocusLost

    private void txThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txThresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txThresholdActionPerformed

    private void txtPgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPgnFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("pgn")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("pgn", txtPgn.getText());
            if (str != null) {
                txtPgn.setText(str);
            }
            prev_control_name = "pgn";
            obj = null;
            str = null;
            txtPgn.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtPgnFocusGained

    private void txtFactorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFactorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFactorActionPerformed

    private void txtFactorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFactorFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Factor")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Factor", txtFactor.getText());
            if (str != null) {
                txtFactor.setText(str);
            }
            prev_control_name = "Factor";
            obj = null;
            str = null;
            txtFactor.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtFactorFocusGained

    private void txtOffsetFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOffsetFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("offset")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("offset", txtOffset.getText());
            if (str != null) {
                txtOffset.setText(str);
            }
            prev_control_name = "offset";
            obj = null;
            str = null;
            txtOffset.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtOffsetFocusGained

    private void txtOffsetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOffsetFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtOffsetFocusLost

    private void txtFactorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFactorFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtFactorFocusLost

    private void txtCanIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCanIdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("CanId")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("CanId", txtCanId.getText());
            if (str != null) {
                txtCanId.setText(str);
            }
            prev_control_name = "CanId";
            obj = null;
            str = null;
            txtCanId.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtCanIdFocusGained

    public void write_canElectrical_file(File path) {
        File file = new File(path, "canElectrical.txt");
        FileOutputStream f;
        PrintWriter pw;
        String str;
        int i = 0;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            str = "Name,Source Address,log,Display,Transmit,Alert,Alarm ID,Min,Max,Pgn,Spn,Unit,Can ID,Factor,Offset,Bytepos,Byte length,BitPos,Bit Length,Reverse,Threshold";
            pw.println(str);
            for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                str = PanCanConfig.objCanElectrical[i].name + "," + PanCanConfig.objCanElectrical[i].source_address + ","
                        + PanCanConfig.objCanElectrical[i].log_enable + "," + PanCanConfig.objCanElectrical[i].display_enable
                        + "," + PanCanConfig.objCanElectrical[i].transmit_enable + "," + PanCanConfig.objCanElectrical[i].alert_enable
                        + "," + PanCanConfig.objCanElectrical[i].alarm_id + "," + PanCanConfig.objCanElectrical[i].min
                        + "," + PanCanConfig.objCanElectrical[i].max + "," + PanCanConfig.objCanElectrical[i].pgn
                        + "," + PanCanConfig.objCanElectrical[i].spn + "," + PanCanConfig.objCanElectrical[i].unit
                        + "," + "0x" + Long.toHexString(PanCanConfig.objCanElectrical[i].can_id) + "," + PanCanConfig.objCanElectrical[i].factor
                        + "," + PanCanConfig.objCanElectrical[i].offset + "," + PanCanConfig.objCanElectrical[i].byte_pos
                        + "," + PanCanConfig.objCanElectrical[i].byte_length + "," + PanCanConfig.objCanElectrical[i].bit_pos
                        + "," + PanCanConfig.objCanElectrical[i].bit_length + "," + PanCanConfig.objCanElectrical[i].reverse
                        + "," + PanCanConfig.objCanElectrical[i].threshold;

                pw.println(str);
            }

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        //   tv.append("\n\nFile written to "+file);
    }

    public void write_canSafety_file(File path) {
        File file = new File(path, "canSafety.txt");
        FileOutputStream f;
        PrintWriter pw;
        String str;
        int i = 0;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            str = "Name,Source Address,log,Display,Transmit,Alert,Alarm ID,Min,Max,Pgn,Spn,Unit,Can ID,Factor,Offset,Bytepos,Byte length,BitPos,Bit Length,Reverse,Threshold";
            pw.println(str);
            for (i = 0; i < PanCanConfig.objCanSafetyCnt; i++) {

                str = PanCanConfig.objCanSafety[i].name + "," + PanCanConfig.objCanSafety[i].source_address + ","
                        + PanCanConfig.objCanSafety[i].log_enable + "," + PanCanConfig.objCanSafety[i].display_enable
                        + "," + PanCanConfig.objCanSafety[i].transmit_enable + "," + PanCanConfig.objCanSafety[i].alert_enable
                        + "," + PanCanConfig.objCanSafety[i].alarm_id + "," + PanCanConfig.objCanSafety[i].min
                        + "," + PanCanConfig.objCanSafety[i].max + "," + PanCanConfig.objCanSafety[i].pgn
                        + "," + PanCanConfig.objCanSafety[i].spn + "," + PanCanConfig.objCanSafety[i].unit
                        + "," + "0x" + Long.toHexString(PanCanConfig.objCanSafety[i].can_id) + "," + PanCanConfig.objCanSafety[i].factor
                        + "," + PanCanConfig.objCanSafety[i].offset + "," + PanCanConfig.objCanSafety[i].byte_pos
                        + "," + PanCanConfig.objCanSafety[i].byte_length + "," + PanCanConfig.objCanSafety[i].bit_pos
                        + "," + PanCanConfig.objCanSafety[i].bit_length + "," + PanCanConfig.objCanSafety[i].reverse
                        + "," + PanCanConfig.objCanSafety[i].threshold;

                pw.println(str);
            }

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        //   tv.append("\n\nFile written to "+file);
    }

    public void write_canTransmit_file(File path) {
        File file = new File(path, "canTransmit.txt");
        FileOutputStream f;
        PrintWriter pw;
        String str;
        int i = 0;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            str = "Name,Source Address,log,Display,Transmit,Alert,Alarm ID,Min,Max,Pgn,Spn,Unit,Can ID,Factor,Offset,Bytepos,Byte length,BitPos,Bit Length,Reverse,Threshold";
            pw.println(str);
            for (i = 0; i < PanCanConfig.objCanTransmitCnt; i++) {

                str = PanCanConfig.objCanTransmit[i].name + "," + PanCanConfig.objCanTransmit[i].source_address + ","
                        + PanCanConfig.objCanTransmit[i].log_enable + "," + PanCanConfig.objCanTransmit[i].display_enable
                        + "," + PanCanConfig.objCanTransmit[i].transmit_enable + "," + PanCanConfig.objCanTransmit[i].alert_enable
                        + "," + PanCanConfig.objCanTransmit[i].alarm_id + "," + PanCanConfig.objCanTransmit[i].min
                        + "," + PanCanConfig.objCanTransmit[i].max + "," + PanCanConfig.objCanTransmit[i].pgn
                        + "," + PanCanConfig.objCanTransmit[i].spn + "," + PanCanConfig.objCanTransmit[i].unit
                        + "," + "0x" + Long.toHexString(PanCanConfig.objCanTransmit[i].can_id) + "," + PanCanConfig.objCanTransmit[i].factor
                        + "," + PanCanConfig.objCanTransmit[i].offset + "," + PanCanConfig.objCanTransmit[i].byte_pos
                        + "," + PanCanConfig.objCanTransmit[i].byte_length + "," + PanCanConfig.objCanTransmit[i].bit_pos
                        + "," + PanCanConfig.objCanTransmit[i].bit_length + "," + PanCanConfig.objCanTransmit[i].reverse
                        + "," + PanCanConfig.objCanTransmit[i].threshold;

                pw.println(str);
            }

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        //   tv.append("\n\nFile written to "+file);
    }

    public void write_canEngine_file(File path) {
        File file = new File(path, "canEngine.txt");
        FileOutputStream f;
        PrintWriter pw;
        String str;
        int i = 0;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            str = "Name,Source Address,log,Display,Transmit,Alert,Alarm ID,Min,Max,Pgn,Spn,Unit,Can ID,Factor,Offset,Bytepos,Byte length,BitPos,Bit Length,Reverse,Threshold";
            pw.println(str);
            for (i = 0; i < PanCanConfig.objCanEngineCnt; i++) {
                str = PanCanConfig.objCanEngine[i].name + "," + PanCanConfig.objCanEngine[i].source_address + ","
                        + PanCanConfig.objCanEngine[i].log_enable + "," + PanCanConfig.objCanEngine[i].display_enable
                        + "," + PanCanConfig.objCanEngine[i].transmit_enable + "," + PanCanConfig.objCanEngine[i].alert_enable
                        + "," + PanCanConfig.objCanEngine[i].alarm_id + "," + PanCanConfig.objCanEngine[i].min
                        + "," + PanCanConfig.objCanEngine[i].max + "," + PanCanConfig.objCanEngine[i].pgn
                        + "," + PanCanConfig.objCanEngine[i].spn + "," + PanCanConfig.objCanEngine[i].unit
                        + "," + "0x" + Long.toHexString(PanCanConfig.objCanEngine[i].can_id) + "," + PanCanConfig.objCanEngine[i].factor
                        + "," + PanCanConfig.objCanEngine[i].offset + "," + PanCanConfig.objCanEngine[i].byte_pos
                        + "," + PanCanConfig.objCanEngine[i].byte_length + "," + PanCanConfig.objCanEngine[i].bit_pos
                        + "," + PanCanConfig.objCanEngine[i].bit_length + "," + PanCanConfig.objCanEngine[i].reverse
                        + "," + PanCanConfig.objCanEngine[i].threshold;

                pw.println(str);
            }

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        //   tv.append("\n\nFile written to "+file);
    }

    public void write_canOthers_file(File path) {
        File file = new File(path, "canOthers.txt");
        FileOutputStream f;
        PrintWriter pw;
        String str;
        int i = 0;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            str = "Name,Source Address,log,Display,Transmit,Alert,Alarm ID,Min,Max,Pgn,Spn,Unit,Can ID,Factor,Offset,Bytepos,Byte length,BitPos,Bit Length,Reverse,Threshold";
            pw.println(str);
            for (i = 0; i < PanCanConfig.objCanOthersCnt; i++) {
                str = PanCanConfig.objCanOthers[i].name + "," + PanCanConfig.objCanOthers[i].source_address + ","
                        + PanCanConfig.objCanOthers[i].log_enable + "," + PanCanConfig.objCanOthers[i].display_enable
                        + "," + PanCanConfig.objCanOthers[i].transmit_enable + "," + PanCanConfig.objCanOthers[i].alert_enable
                        + "," + PanCanConfig.objCanOthers[i].alarm_id + "," + PanCanConfig.objCanOthers[i].min
                        + "," + PanCanConfig.objCanOthers[i].max + "," + PanCanConfig.objCanOthers[i].pgn
                        + "," + PanCanConfig.objCanOthers[i].spn + "," + PanCanConfig.objCanOthers[i].unit
                        + "," + "0x" + Long.toHexString(PanCanConfig.objCanOthers[i].can_id) + "," + PanCanConfig.objCanOthers[i].factor
                        + "," + PanCanConfig.objCanOthers[i].offset + "," + PanCanConfig.objCanOthers[i].byte_pos
                        + "," + PanCanConfig.objCanOthers[i].byte_length + "," + PanCanConfig.objCanOthers[i].bit_pos
                        + "," + PanCanConfig.objCanOthers[i].bit_length + "," + PanCanConfig.objCanOthers[i].reverse
                        + "," + PanCanConfig.objCanOthers[i].threshold;

                pw.println(str);
            }

            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
            f = null;
            pw = null;
        }
        //   tv.append("\n\nFile written to "+file);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkAlarm;
    private javax.swing.JCheckBox chkDisplay;
    private javax.swing.JCheckBox chkLog;
    private javax.swing.JCheckBox chkReverse;
    private javax.swing.JCheckBox chkTransmit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblCanName;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JTextField lblUnit;
    private javax.swing.JList lstCanNames;
    private javax.swing.JSpinner spinBitLen;
    private javax.swing.JSpinner spinBitPos;
    private javax.swing.JSpinner spinByteLen;
    private javax.swing.JSpinner spinByteVal;
    private javax.swing.JTextField txThreshold;
    private javax.swing.JTextField txtAlarmId;
    private javax.swing.JTextField txtCanId;
    private javax.swing.JTextField txtFactor;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    private javax.swing.JTextField txtOffset;
    private javax.swing.JTextField txtPgn;
    private javax.swing.JTextField txtSpn;
    // End of variables declaration//GEN-END:variables
}
