
package obuits;

import java.awt.Color;
import javax.swing.JPanel;
import static obuits.PanCanConfig.objCanElectrical;
import static obuits.PanCanConfig.objCanTransmit;

public class PanCanConfig extends javax.swing.JPanel {

    Color color_blue = new Color(85, 85, 85);
    Color color_green = new Color(50, 205, 50);
    public static final byte MAX_NO_CAN_PARAMS = 100;
    private JPanel objPanel = null;

    class clsCanParams {

        String name;
        int pgn;
        int spn;
        double min;
        double max;
        double threshold;
        String unit;
        boolean log_enable;
        boolean display_enable;
        boolean transmit_enable;
        boolean alert_enable;
        byte vehicle_type;
        int source_address;
        double value;
        short alarm_id;
        byte alarm_state;
        long can_id;
        byte byte_pos;
        byte byte_length;
        byte bit_pos;
        byte bit_length;
        boolean reverse;
        double factor;
        double offset;
        boolean data_updated;
        //can2
        String name1;
        int pgn1;
        int spn1;
        double min1;
        double max1;
        double threshold1;
        String unit1;
        boolean log_enable1;
        boolean display_enable1;
        boolean transmit_enable1;
        boolean alert_enable1;
        byte vehicle_type1;
        int source_address1;
        double value1;
        short alarm_id1;
        byte alarm_state1;
        long can_id1;
        byte byte_pos1;
        byte byte_length1;
        byte bit_pos1;
        byte bit_length1;
        boolean reverse1;
        double factor1;
        double offset1;
        boolean data_updated1;

    }

    public static clsCanParams[] objCanElectrical = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanSafety = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanTransmit = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanEngine = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanOthers = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static byte objCanElectricalCnt = MAX_NO_CAN_PARAMS;
    public static byte objCanSafetyCnt = MAX_NO_CAN_PARAMS;
    public static byte objCanTransmitCnt = MAX_NO_CAN_PARAMS;
    public static byte objCanEngineCnt = MAX_NO_CAN_PARAMS;
    public static byte objCanOthersCnt = MAX_NO_CAN_PARAMS;
    //can2
    public static clsCanParams[] objCanElectrical1 = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanSafety1 = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanTransmit1 = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanEngine1 = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static clsCanParams[] objCanOthers1 = new clsCanParams[MAX_NO_CAN_PARAMS];
    public static byte objCanElectricalCnt1 = MAX_NO_CAN_PARAMS;
    public static byte objCanSafetyCnt1 = MAX_NO_CAN_PARAMS;
    public static byte objCanTransmitCnt1 = MAX_NO_CAN_PARAMS;
    public static byte objCanEngineCnt1 = MAX_NO_CAN_PARAMS;
    public static byte objCanOthersCnt1 = MAX_NO_CAN_PARAMS;

    public PanCanConfig() {
        initComponents();

        btnOthers.setVisible(false);

    }

    public void init_can_electrical_systems() {
        try {
            int i = 0;
            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanElectrical[i] = new clsCanParams();
            }
            objCanElectricalCnt = 0;
        
        } catch (Exception ex) {
        }
    }

    public void init_can_safety() {
        int i = 0;
        try {

            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanSafety[i] = new clsCanParams();
            }
        } catch (Exception ex) {

        }
        objCanSafetyCnt = 0;

    }

    public void init_can_transmission() {
        int i = 0;
        try {
            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanTransmit[i] = new clsCanParams();
            }
        } catch (Exception ex) {

        }
        objCanTransmitCnt = 0;
    }

    public void init_can_engine() {
        int i = 0;
        try {
            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanEngine[i] = new clsCanParams();
            }
        } catch (Exception ex) {

        }

        objCanEngineCnt = 0;

    }

    public void init_can_others() {
        int i = 0;

        for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
            objCanOthers[i] = new clsCanParams();
        }
        objCanOthersCnt = 0;
    }

    //can2
    public void init_can_electrical_systems1() {
        try {
            int i = 0;
            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanElectrical1[i] = new clsCanParams();
            }
            objCanElectricalCnt1 = 0;

        } catch (Exception ex) {
        }
    }

    public void init_can_safety1() {
        int i = 0;
        try {

            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanSafety1[i] = new clsCanParams();
            }
        } catch (Exception ex) {

        }
        objCanSafetyCnt1 = 0;

    }

    public void init_can_transmission1() {
        int i = 0;
        try {
            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanTransmit1[i] = new clsCanParams();
            }
        } catch (Exception ex) {

        }
        objCanTransmitCnt1 = 0;
    }

    public void init_can_engine1() {
        int i = 0;
        try {
            for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
                objCanEngine1[i] = new clsCanParams();
            }
        } catch (Exception ex) {

        }

        objCanEngineCnt1 = 0;

    }

    public void init_can_others1() {
        int i = 0;

        for (i = 0; i < MAX_NO_CAN_PARAMS; i++) {
            objCanOthers1[i] = new clsCanParams();
        }
        objCanOthersCnt1 = 0;

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnElectricalSystems = new javax.swing.JButton();
        btnSafety = new javax.swing.JButton();
        btnTransmission = new javax.swing.JButton();
        btnEngine = new javax.swing.JButton();
        btnOthers = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        PanCenterCan = new javax.swing.JPanel();

        setBackground(new java.awt.Color(23, 29, 32));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 467));

        btnElectricalSystems.setBackground(new java.awt.Color(85, 85, 85));
        btnElectricalSystems.setFont(btnElectricalSystems.getFont().deriveFont(btnElectricalSystems.getFont().getSize()+4f));
        btnElectricalSystems.setForeground(new java.awt.Color(255, 255, 255));
        btnElectricalSystems.setText(" Electrical Systems");
        btnElectricalSystems.setActionCommand("<html >Vehicle Electrical <br> Systems </html>");
        btnElectricalSystems.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnElectricalSystems.setPreferredSize(new java.awt.Dimension(176, 36));
        btnElectricalSystems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElectricalSystemsActionPerformed(evt);
            }
        });

        btnSafety.setBackground(new java.awt.Color(85, 85, 85));
        btnSafety.setFont(btnSafety.getFont().deriveFont(btnSafety.getFont().getSize()+4f));
        btnSafety.setForeground(new java.awt.Color(255, 255, 255));
        btnSafety.setText("Safety & Performance");
        btnSafety.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSafety.setPreferredSize(new java.awt.Dimension(180, 36));
        btnSafety.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyActionPerformed(evt);
            }
        });

        btnTransmission.setBackground(new java.awt.Color(85, 85, 85));
        btnTransmission.setFont(btnTransmission.getFont().deriveFont(btnTransmission.getFont().getSize()+4f));
        btnTransmission.setForeground(new java.awt.Color(255, 255, 255));
        btnTransmission.setText("Transmission");
        btnTransmission.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTransmission.setPreferredSize(new java.awt.Dimension(180, 36));
        btnTransmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransmissionActionPerformed(evt);
            }
        });

        btnEngine.setBackground(new java.awt.Color(85, 85, 85));
        btnEngine.setFont(btnEngine.getFont().deriveFont(btnEngine.getFont().getSize()+4f));
        btnEngine.setForeground(new java.awt.Color(255, 255, 255));
        btnEngine.setText("Engine");
        btnEngine.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEngine.setPreferredSize(new java.awt.Dimension(100, 36));
        btnEngine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEngineActionPerformed(evt);
            }
        });

        btnOthers.setBackground(new java.awt.Color(85, 85, 85));
        btnOthers.setFont(btnOthers.getFont().deriveFont(btnOthers.getFont().getSize()+4f));
        btnOthers.setForeground(new java.awt.Color(255, 255, 255));
        btnOthers.setText("Others");
        btnOthers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOthers.setPreferredSize(new java.awt.Dimension(37, 36));
        btnOthers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOthersActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+10));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("CAN Configuration");

        PanCenterCan.setBackground(new java.awt.Color(23, 29, 32));
        PanCenterCan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanCenterCan.setForeground(new java.awt.Color(255, 255, 255));
        PanCenterCan.setPreferredSize(new java.awt.Dimension(700, 367));

        javax.swing.GroupLayout PanCenterCanLayout = new javax.swing.GroupLayout(PanCenterCan);
        PanCenterCan.setLayout(PanCenterCanLayout);
        PanCenterCanLayout.setHorizontalGroup(
            PanCenterCanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanCenterCanLayout.setVerticalGroup(
            PanCenterCanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanCenterCan, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnElectricalSystems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSafety, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTransmission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEngine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSafety, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransmission, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEngine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnElectricalSystems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanCenterCan, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnElectricalSystems, btnEngine, btnOthers, btnSafety, btnTransmission});

    }// </editor-fold>//GEN-END:initComponents

    private void btnElectricalSystemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElectricalSystemsActionPerformed
        // TODO add your handling code here: 
        btnElectricalSystems.setBackground(color_green);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_blue);

        if (objPanel != null) {
            objPanel.removeAll();
            objPanel = null;
        }
        objPanel = new PanCanElectricalSystems((byte) 0);
        PanCenterCan.removeAll();
        PanCenterCan.setLayout(new java.awt.BorderLayout());
        PanCenterCan.add(objPanel);
        PanCenterCan.revalidate();
        PanCenterCan.repaint();
    }//GEN-LAST:event_btnElectricalSystemsActionPerformed

    private void btnSafetyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyActionPerformed
        // TODO add your handling code here:
        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_green);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_blue);
        if (objPanel != null) {
            objPanel.removeAll();
            objPanel = null;
        }
        objPanel = new PanCanElectricalSystems((byte) 1);
        PanCenterCan.removeAll();
        PanCenterCan.setLayout(new java.awt.BorderLayout());
        PanCenterCan.add(objPanel);
        PanCenterCan.revalidate();
        PanCenterCan.repaint();
    }//GEN-LAST:event_btnSafetyActionPerformed

    private void btnTransmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransmissionActionPerformed
        // TODO add your handling code here:

        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_green);
        this.btnOthers.setBackground(color_blue);
        if (objPanel != null) {
            objPanel.removeAll();
            objPanel = null;
        }
        objPanel = new PanCanElectricalSystems((byte) 2);
        PanCenterCan.removeAll();
        PanCenterCan.setLayout(new java.awt.BorderLayout());
        PanCenterCan.add(objPanel);
        PanCenterCan.revalidate();
        PanCenterCan.repaint();
    }//GEN-LAST:event_btnTransmissionActionPerformed

    private void btnEngineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEngineActionPerformed
        // TODO add your handling code here:

        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_green);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_blue);
        if (objPanel != null) {
            objPanel.removeAll();
            objPanel = null;
        }
        objPanel = new PanCanElectricalSystems((byte) 3);
        PanCenterCan.removeAll();
        PanCenterCan.setLayout(new java.awt.BorderLayout());
        PanCenterCan.add(objPanel);
        PanCenterCan.revalidate();
        PanCenterCan.repaint();
    }//GEN-LAST:event_btnEngineActionPerformed

    private void btnOthersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOthersActionPerformed
        // TODO add your handling code here:

        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_green);
        if (objPanel != null) {
            objPanel.removeAll();
            objPanel = null;
        }
        objPanel = new PanCanElectricalSystems((byte) 4);
        PanCenterCan.removeAll();
        PanCenterCan.setLayout(new java.awt.BorderLayout());
        PanCenterCan.add(objPanel);
        PanCenterCan.revalidate();
        PanCenterCan.repaint();
    }//GEN-LAST:event_btnOthersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanCenterCan;
    private javax.swing.JButton btnElectricalSystems;
    private javax.swing.JButton btnEngine;
    private javax.swing.JButton btnOthers;
    private javax.swing.JButton btnSafety;
    private javax.swing.JButton btnTransmission;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
