package obuits;

import java.io.File;
import static obuits.clsDefines.route_filepath;
import static obuits.clsSharedVariables.getSplAudio1;
import static obuits.clsSharedVariables.getSplAudio2;
import static obuits.clsSharedVariables.getSplAudio3;
import static obuits.clsSharedVariables.getSplAudio4;
import static obuits.clsSharedVariables.getSplAudio5;
import static obuits.clsSharedVariables.getSplAudioEnabled;
import static obuits.clsSharedVariables.setSplAudio1;
import static obuits.clsSharedVariables.setSplAudioEnabled;

public class PanAudioContinous extends javax.swing.JPanel {

    public static clsAudioFilesQueue objAudQueSpel = new clsAudioFilesQueue();
    String prev_control_name = "";

    public PanAudioContinous() {
        initComponents();

        chkAudoSplEnable.setSelected(getSplAudioEnabled());
        if (chkAudoSplEnable.isSelected()) {
            chkAudoSplEnable.setText("AUDIO ENABLE");
        } else {
            chkAudoSplEnable.setText("AUDIO DISABLE");
        }
        this.txtTimeInterval.setText(String.valueOf(clsSharedVariables.getSplAudioInterval()));
        chkaudio1.setSelected(getSplAudio1());
        chkaudio2.setSelected(getSplAudio2());
        chkaudio3.setSelected(getSplAudio3());
        chkaudio4.setSelected(getSplAudio4());
        chkaudio5.setSelected(getSplAudio5());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAudioHeading = new javax.swing.JTextField();
        chkAudoSplEnable = new javax.swing.JCheckBox();
        lblTime = new javax.swing.JLabel();
        txtTimeInterval = new javax.swing.JTextField();
        chkaudio1 = new javax.swing.JCheckBox();
        chkaudio2 = new javax.swing.JCheckBox();
        chkaudio3 = new javax.swing.JCheckBox();
        chkaudio4 = new javax.swing.JCheckBox();
        chkaudio5 = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        btnPlay1 = new javax.swing.JButton();
        btnPlay5 = new javax.swing.JButton();
        btnPlay4 = new javax.swing.JButton();
        btnPlay3 = new javax.swing.JButton();
        btnPlay2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();

        lblAudioHeading.setEditable(false);
        lblAudioHeading.setBackground(new java.awt.Color(23, 29, 32));
        lblAudioHeading.setFont(lblAudioHeading.getFont().deriveFont(lblAudioHeading.getFont().getStyle() | java.awt.Font.BOLD, lblAudioHeading.getFont().getSize()+10));
        lblAudioHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblAudioHeading.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblAudioHeading.setText("AUDIO ");
        lblAudioHeading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblAudioHeadingActionPerformed(evt);
            }
        });

        chkAudoSplEnable.setBackground(new java.awt.Color(255, 255, 255));
        chkAudoSplEnable.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkAudoSplEnable.setText("AudioEnable");
        chkAudoSplEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAudoSplEnableActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblTime.setText("Time Interval");

        txtTimeInterval.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        txtTimeInterval.setText("1");
        txtTimeInterval.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimeIntervalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimeIntervalFocusLost(evt);
            }
        });

        chkaudio1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        chkaudio1.setText("SpelAudio1");

        chkaudio2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        chkaudio2.setText("SpelAudio2");

        chkaudio3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        chkaudio3.setText("SpelAudio3");

        chkaudio4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        chkaudio4.setText("SpelAudio4");

        chkaudio5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        chkaudio5.setText("SpelAudio5");

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPlay1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlay1.setText("PLAY");
        btnPlay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlay1ActionPerformed(evt);
            }
        });

        btnPlay5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlay5.setText("PLAY");
        btnPlay5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlay5ActionPerformed(evt);
            }
        });

        btnPlay4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlay4.setText("PLAY");
        btnPlay4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlay4ActionPerformed(evt);
            }
        });

        btnPlay3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlay3.setText("PLAY");
        btnPlay3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlay3ActionPerformed(evt);
            }
        });

        btnPlay2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPlay2.setText("PLAY");
        btnPlay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlay2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("min");

        lblMsg.setBackground(new java.awt.Color(255, 255, 255));
        lblMsg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAudioHeading)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkaudio1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(chkaudio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkaudio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkaudio4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkaudio5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPlay2)
                            .addComponent(btnPlay1)
                            .addComponent(btnPlay3)
                            .addComponent(btnPlay4)
                            .addComponent(btnPlay5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkAudoSplEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblAudioHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAudoSplEnable)
                    .addComponent(txtTimeInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPlay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkaudio1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkaudio2)
                    .addComponent(btnPlay2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkaudio3)
                    .addComponent(btnPlay3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkaudio4)
                    .addComponent(btnPlay4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkaudio5)
                    .addComponent(btnPlay5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAudioHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblAudioHeadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAudioHeadingActionPerformed

    private void chkAudoSplEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAudoSplEnableActionPerformed
        // TODO add your handling code here:
        if (chkAudoSplEnable.isSelected()) {
            chkAudoSplEnable.setText("AUDIO ENABLE");

        } else {
            chkAudoSplEnable.setText("AUDIO DISABLE");

        }
    }//GEN-LAST:event_chkAudoSplEnableActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        clsReadDataFiles objReadData = new clsReadDataFiles();
        if (chkAudoSplEnable.isSelected()) {
            setSplAudioEnabled(true);
        } else {
            setSplAudioEnabled(false);
        }
        clsSharedVariables.setSplAudioInterval(Integer.parseInt(txtTimeInterval.getText()));
        // TODO add your handling code here:
        if (chkaudio1.isSelected()) {
            setSplAudio1(true);
        } else {
            setSplAudio1(false);
        }
        if (chkaudio2.isSelected()) {
            clsSharedVariables.setSplAudio2(true);
        } else {
            clsSharedVariables.setSplAudio2(false);
        }
        if (chkaudio3.isSelected()) {
            clsSharedVariables.setSplAudio3(true);
        } else {
            clsSharedVariables.setSplAudio3(false);
        }
        if (chkaudio4.isSelected()) {
            clsSharedVariables.setSplAudio4(true);
        } else {
            clsSharedVariables.setSplAudio4(false);
        }
        if (chkaudio5.isSelected()) {
            clsSharedVariables.setSplAudio5(true);
        } else {
            clsSharedVariables.setSplAudio5(false);
        }
        objReadData.write_spel_audio_data_file();
        if (objReadData.write_spel_audio_data_file()) {
            lblMsg.setText("Configuration saved");
        } else {
            lblMsg.setText("Configuration Details Not Saved");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPlay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlay1ActionPerformed

        try {
            File f;
            if (chkaudio1.isSelected()) {
                f = new File(route_filepath, "SpelAudio1.WAV");
                if (f.exists()) {
                    objAudQueSpel.addDataSpel("SpelAudio1.WAV");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btnPlay1ActionPerformed

    private void btnPlay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlay2ActionPerformed
        // TODO add your handling code here:
        try {
            File f;
            if (chkaudio2.isSelected()) {
                f = new File(route_filepath, "SpelAudio2.WAV");
                if (f.exists()) {
                    objAudQueSpel.addDataSpel("SpelAudio2.WAV");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btnPlay2ActionPerformed

    private void btnPlay3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlay3ActionPerformed
        // TODO add your handling code here:
        try {
            File f;
            if (chkaudio3.isSelected()) {
                f = new File(route_filepath, "SpelAudio3.WAV");
                if (f.exists()) {
                    objAudQueSpel.addDataSpel("SpelAudio3.WAV");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnPlay3ActionPerformed

    private void btnPlay4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlay4ActionPerformed
        // TODO add your handling code here:
        try {
            File f;
            if (chkaudio4.isSelected()) {
                f = new File(route_filepath, "SpelAudio4.WAV");
                if (f.exists()) {
                    objAudQueSpel.addDataSpel("SpelAudio4.WAV");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnPlay4ActionPerformed

    private void btnPlay5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlay5ActionPerformed
        // TODO add your handling code here:
        try {
            File f;
            if (chkaudio5.isSelected()) {
                f = new File(route_filepath, "SpelAudio5.WAV");
                if (f.exists()) {
                    objAudQueSpel.addDataSpel("SpelAudio5.WAV");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btnPlay5ActionPerformed

    private void txtTimeIntervalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimeIntervalFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtTimeIntervalFocusLost

    private void txtTimeIntervalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimeIntervalFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Spl Audio")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Spl Audio", txtTimeInterval.getText());
            if (str != null) {
                txtTimeInterval.setText(str);
            }
            prev_control_name = "Spl Audio";
            obj = null;
            str = null;
            txtTimeInterval.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtTimeIntervalFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlay1;
    private javax.swing.JButton btnPlay2;
    private javax.swing.JButton btnPlay3;
    private javax.swing.JButton btnPlay4;
    private javax.swing.JButton btnPlay5;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkAudoSplEnable;
    private javax.swing.JCheckBox chkaudio1;
    private javax.swing.JCheckBox chkaudio2;
    private javax.swing.JCheckBox chkaudio3;
    private javax.swing.JCheckBox chkaudio4;
    private javax.swing.JCheckBox chkaudio5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField lblAudioHeading;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField txtTimeInterval;
    // End of variables declaration//GEN-END:variables
}
