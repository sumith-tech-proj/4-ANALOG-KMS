package obuits;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsDefines.OBU_BRD_TYPE;
import static obuits.clsDefines.RASPBERRY_BOARD;
import static obuits.clsDefines.TINKER_BOARD;
import static obuits.clsSharedVariables.get_ring_tone_volume;
import static obuits.clsSharedVariables.get_speaker_volume;
import static obuits.clsSharedVariables.get_voice_call_volume;
import static obuits.clsSharedVariables.set_ring_tone_volume;
import static obuits.clsSharedVariables.set_speaker_volume;
import static obuits.clsSharedVariables.set_voice_call_volume;

public class PanVolume extends javax.swing.JPanel {

    public PanVolume() {
        initComponents();
        // jPanel3.setVisible(false);
        sliderVoiceCallVolume.setValue(get_voice_call_volume());
        sliderSpeakerVolume.setValue(get_speaker_volume());
        sliderRingtoneVolume.setValue(get_ring_tone_volume());
        jPanel3.setVisible(false);
        this.lblMsg.setText("");
        this.lblMsg1.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConfigName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblSpeaker = new javax.swing.JLabel();
        sliderSpeakerVolume = new javax.swing.JSlider();
        btnSpeakerVolume = new javax.swing.JButton();
        lblMsg1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblVoiceCall = new javax.swing.JLabel();
        sliderVoiceCallVolume = new javax.swing.JSlider();
        btnVoiceCallVolume = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblRingtone = new javax.swing.JLabel();
        sliderRingtoneVolume = new javax.swing.JSlider();
        btnRingToneVolume = new javax.swing.JButton();
        lblMsg2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("VOLUME");

        jPanel1.setBackground(new java.awt.Color(149, 149, 62));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSpeaker.setFont(lblSpeaker.getFont().deriveFont(lblSpeaker.getFont().getStyle() | java.awt.Font.BOLD, lblSpeaker.getFont().getSize()+7));
        lblSpeaker.setForeground(new java.awt.Color(255, 255, 255));
        lblSpeaker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSpeaker.setText("Speaker Volume");

        sliderSpeakerVolume.setMaximum(8);
        sliderSpeakerVolume.setValue(5);

        btnSpeakerVolume.setBackground(new java.awt.Color(47, 49, 51));
        btnSpeakerVolume.setFont(btnSpeakerVolume.getFont().deriveFont(btnSpeakerVolume.getFont().getStyle() | java.awt.Font.BOLD, btnSpeakerVolume.getFont().getSize()+7));
        btnSpeakerVolume.setForeground(new java.awt.Color(255, 255, 255));
        btnSpeakerVolume.setText("OK");
        btnSpeakerVolume.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSpeakerVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpeakerVolumeActionPerformed(evt);
            }
        });

        lblMsg1.setFont(lblMsg1.getFont().deriveFont(lblMsg1.getFont().getStyle() | java.awt.Font.BOLD, lblMsg1.getFont().getSize()+7));
        lblMsg1.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg1.setText("Configuration Saved");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSpeakerVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMsg1))
                    .addComponent(sliderSpeakerVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderSpeakerVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSpeakerVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(52, 114, 137));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblVoiceCall.setFont(lblVoiceCall.getFont().deriveFont(lblVoiceCall.getFont().getStyle() | java.awt.Font.BOLD, lblVoiceCall.getFont().getSize()+7));
        lblVoiceCall.setForeground(new java.awt.Color(255, 255, 255));
        lblVoiceCall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblVoiceCall.setText("Voice Calls Volume");

        sliderVoiceCallVolume.setMaximum(7);
        sliderVoiceCallVolume.setMinimum(1);
        sliderVoiceCallVolume.setValue(3);

        btnVoiceCallVolume.setBackground(new java.awt.Color(47, 49, 51));
        btnVoiceCallVolume.setFont(btnVoiceCallVolume.getFont().deriveFont(btnVoiceCallVolume.getFont().getStyle() | java.awt.Font.BOLD, btnVoiceCallVolume.getFont().getSize()+7));
        btnVoiceCallVolume.setForeground(new java.awt.Color(255, 255, 255));
        btnVoiceCallVolume.setText("OK");
        btnVoiceCallVolume.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVoiceCallVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoiceCallVolumeActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg.setText("Configuration Saved");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVoiceCall, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnVoiceCallVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMsg))
                    .addComponent(sliderVoiceCallVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(186, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblVoiceCall, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderVoiceCallVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoiceCallVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 162, 46));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblRingtone.setFont(lblRingtone.getFont().deriveFont(lblRingtone.getFont().getStyle() | java.awt.Font.BOLD, lblRingtone.getFont().getSize()+7));
        lblRingtone.setForeground(new java.awt.Color(255, 255, 255));
        lblRingtone.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRingtone.setText("Driver Speaker Volume");

        sliderRingtoneVolume.setMajorTickSpacing(5);
        sliderRingtoneVolume.setMinimum(50);
        sliderRingtoneVolume.setSnapToTicks(true);
        sliderRingtoneVolume.setValue(85);

        btnRingToneVolume.setBackground(new java.awt.Color(47, 49, 51));
        btnRingToneVolume.setFont(btnRingToneVolume.getFont().deriveFont(btnRingToneVolume.getFont().getStyle() | java.awt.Font.BOLD, btnRingToneVolume.getFont().getSize()+7));
        btnRingToneVolume.setForeground(new java.awt.Color(255, 255, 255));
        btnRingToneVolume.setText("OK");
        btnRingToneVolume.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRingToneVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRingToneVolumeActionPerformed(evt);
            }
        });

        lblMsg2.setFont(lblMsg2.getFont().deriveFont(lblMsg2.getFont().getStyle() | java.awt.Font.BOLD, lblMsg2.getFont().getSize()+7));
        lblMsg2.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg2.setText("Configuration Saved");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRingtone, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRingToneVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMsg2))
                    .addComponent(sliderRingtoneVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblRingtone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderRingtoneVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRingToneVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfigName)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoiceCallVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoiceCallVolumeActionPerformed
        // TODO add your handling code here:
        int i = sliderVoiceCallVolume.getValue();
        String str = "AT+CLVL=" + i + "\r\n";
        atSerialWrite(str);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
        }
        atSerialWrite(str);
        str = "AT&W\r\n";
        atSerialWrite(str);

        set_voice_call_volume((byte) i);

        clsReadFiles obj = new clsReadFiles();
        if (obj.write_cfg_data_file()) {
            lblMsg.setText("Device Details Saved");
        } else {
            lblMsg.setText("Device Details Not Saved");

        }
    }//GEN-LAST:event_btnVoiceCallVolumeActionPerformed
    private boolean runCmd(String cmd) {
        Process process = null;

        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(5, TimeUnit.SECONDS);
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

                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                    process = null;
                    rt = null;

                    rt = null;
                }
            } catch (IOException e) {
            } catch (Exception ex) {
            }
        }

    }
    private void btnSpeakerVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpeakerVolumeActionPerformed
        // TODO add your handling code here:

        // amixer set 'PCM' 80%
        int vol = sliderSpeakerVolume.getValue();
        if (clsSharedVariables.os_version.contains("5.15.32")) {
            runCmd("sudo amixer sset 'Headphone'  unmute");
            switch (vol) {
                case 0:

                    runCmd("sudo amixer sset 'Headphone' 73% unmute \r\n ");

                    break;

                case 1:

                    runCmd("sudo amixer sset 'Headphone' 74% unmute \r\n");

                    break;

                case 2:

                    runCmd("sudo amixer sset 'Headphone' 75% unmute \r\n");

                    break;

                case 3:

                    runCmd("sudo amixer sset 'Headphone' 78% unmute \r\n");

                    break;

                case 4:

                    runCmd("sudo amixer sset 'Headphone' 80% unmute \r\n");

                    break;

                case 5:

                    runCmd("sudo amixer sset 'Headphone' 85% unmute \r\n");

                    break;

                case 6:

                    runCmd("sudo amixer sset 'Headphone' 88% unmute \r\n");

                    break;

                case 7:

                    runCmd("sudo amixer sset 'Headphone' 90% unmute \r\n");

                    break;

                case 8:

                    runCmd("sudo amixer sset 'Headphone' 92% unmute \r\n");

                    break;

                case 9:

                    runCmd("sudo amixer sset 'Headphone' 94% unmute \r\n");

                    break;

                case 10:

                    runCmd("sudo amixer sset 'Headphone' 96% unmute \r\n");

                    break;
            }

        } else if (OBU_BRD_TYPE == TINKER_BOARD) {
            runCmd("amixer sset Master unmute \r\n");

            switch (vol) {

                case 0:

                    runCmd("amixer sset Master 0%");

                    break;

                case 1:

                    runCmd("amixer sset Master 40%");
                    break;

                case 2:

                    runCmd("amixer sset Master 50%");
                    break;

                case 3:

                    runCmd("amixer sset Master 60%");
                    break;

                case 4:

                    runCmd("amixer sset Master 70%");
                    break;

                case 5:

                    runCmd("amixer sset Master 76%");
                    break;

                case 6:

                    runCmd("amixer sset Master 78%");
                    break;

                case 7:

                    runCmd("amixer sset Master 80%");
                    break;

                case 8:

                    runCmd("amixer sset Master 85%");
                    break;

                case 9:

                    runCmd("amixer sset Master 88%");
                    break;

                case 10:

                    runCmd("amixer sset Master 89%");

                    break;
            }
        } else if (OBU_BRD_TYPE == RASPBERRY_BOARD) {
            if (clsSharedVariables.os_version.contains("5.10.103")) {
                /// System.out.println("volume changed");
                /* if (vol < 3 || vol > 10) {
                vol = 7;
                set_speaker_volume((byte) 7);
            }*/
                // //System.out.println("omxplayer audio main");

                runCmd("amixer sset Master unmute \r\n");

                switch (vol) {

                    case 0:

                        runCmd("amixer sset Master 0%");

                        break;

                    case 1:

                        runCmd("amixer sset Master 40%");
                        break;

                    case 2:

                        runCmd("amixer sset Master 50%");
                        break;

                    case 3:

                        runCmd("amixer sset Master 60%");
                        break;

                    case 4:

                        runCmd("amixer sset Master 70%");
                        break;

                    case 5:

                        runCmd("amixer sset Master 76%");
                        break;

                    case 6:

                        runCmd("amixer sset Master 78%");
                        break;

                    case 7:

                        runCmd("amixer sset Master 80%");
                        break;

                    case 8:

                        runCmd("amixer sset Master 85%");
                        break;

                    case 9:

                        runCmd("amixer sset Master 88%");
                        break;

                    case 10:

                        runCmd("amixer sset Master 89%");

                        break;

                }
            }
        } else {
            if (vol < 3 || vol > 10) {
                vol = 7;
                set_speaker_volume((byte) 7);
            }

            runCmd("amixer set 'PCM' unmute \r\n");

            switch (vol) {

                case 0:

                    runCmd("amixer -c 0 set  PCM  mute \r\n ");

                    break;

                case 1:

                    runCmd("amixer -c 0 set  PCM   60% unmute \r\n");

                    break;

                case 2:

                    runCmd("amixer -c 0 set  PCM   65% unmute \r\n");

                    break;

                case 3:

                    runCmd("amixer -c 0 set  PCM  70% unmute \r\n");

                    break;

                case 4:

                    runCmd("amixer -c 0 set  PCM  73% unmute \r\n");

                    break;

                case 5:

                    runCmd("amixer -c 0 set  PCM  76% unmute \r\n");

                    break;

                case 6:

                    runCmd("amixer -c 0 set  PCM  78% unmute \r\n");

                    break;

                case 7:

                    runCmd("amixer -c 0 set  PCM  80% unmute \r\n");

                    break;

                case 8:

                    runCmd("amixer -c 0 set  PCM  82% unmute \r\n");

                    break;

                case 9:

                    runCmd("amixer -c 0 set  PCM  83% unmute \r\n");

                    break;

                case 10:

                    runCmd("amixer -c 0 set  PCM  85% unmute \r\n");

                    break;

            }
        }
        set_speaker_volume((byte) vol);

        clsReadFiles obj = new clsReadFiles();
        if (obj.write_cfg_data_file()) {
            lblMsg1.setText("Device Details Saved");

        } else {
            lblMsg1.setText("Device Details Not Saved");

        }
    }//GEN-LAST:event_btnSpeakerVolumeActionPerformed

    private void btnRingToneVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRingToneVolumeActionPerformed
        // TODO add your handling code here:
        int i = sliderRingtoneVolume.getValue();
        String str;

        if (clsSharedVariables.getQuecModuleRev() == clsDefines.QUECTEL_MOD_EC25) {
            str = "AT+QAUDCFG=\"nau8814/dlgain\"," + i + "\r\n";
        } else {
            str = "AT+CRSL=" + i + "\r\n";
        }
        atSerialWrite(str);
        atSerialWrite(str);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(PanVolume.class.getName()).log(Level.SEVERE, null, ex);
        }
        atSerialWrite(str);
        str = "AT&W\r\n";
        atSerialWrite(str);

        set_ring_tone_volume((byte) i);

        clsReadFiles obj = new clsReadFiles();
        if (obj.write_cfg_data_file()) {
            lblMsg2.setText("Device Details Saved");

        } else {
            lblMsg2.setText("Device Details Not Saved");

        }
    }//GEN-LAST:event_btnRingToneVolumeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRingToneVolume;
    private javax.swing.JButton btnSpeakerVolume;
    private javax.swing.JButton btnVoiceCallVolume;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblMsg2;
    private javax.swing.JLabel lblRingtone;
    private javax.swing.JLabel lblSpeaker;
    private javax.swing.JLabel lblVoiceCall;
    private javax.swing.JSlider sliderRingtoneVolume;
    private javax.swing.JSlider sliderSpeakerVolume;
    private javax.swing.JSlider sliderVoiceCallVolume;
    // End of variables declaration//GEN-END:variables

}
