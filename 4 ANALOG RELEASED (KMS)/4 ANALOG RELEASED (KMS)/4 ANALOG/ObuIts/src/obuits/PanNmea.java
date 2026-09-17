
package obuits;

import static obuits.GPSLocationService.gpsSerialWrite;
import static obuits.GPSLocationService.gpsSerialWriteString;
import static obuits.clsSharedVariables.get_nmea_gpgga;
import static obuits.clsSharedVariables.get_nmea_gpgsa;
import static obuits.clsSharedVariables.get_nmea_gpgsv;
import static obuits.clsSharedVariables.get_nmea_gprmc;
import static obuits.clsSharedVariables.get_nmea_gpvtg;
import static obuits.clsSharedVariables.set_nmea_gpgga;
import static obuits.clsSharedVariables.set_nmea_gpgsa;
import static obuits.clsSharedVariables.set_nmea_gpgsv;
import static obuits.clsSharedVariables.set_nmea_gprmc;
import static obuits.clsSharedVariables.set_nmea_gpvtg;

public class PanNmea extends javax.swing.JPanel {

    public PanNmea() {
        initComponents();
        lblMsg.setText("");

        if (clsSharedVariables.get_irnss_enabled() && clsSharedVariables.get_gps_enabled()) {
            chkGprmc.setText("GPRMC/GNRMC/GIRMC");
            chkGpgsa.setText("GPGSA/GNGSA/GIGSA");
            chkGpgsv.setText("GPGSV/GNGSV/GIGSV");
            chkGpgga.setText("GPGGA/GNGGA/GIGGA");
            chkGpvtg.setText("GPVTG/GNVTG/GIVTG");
        } else if (clsSharedVariables.get_gps_enabled()) {
            chkGprmc.setText("GPRMC");
            chkGpgsa.setText("GPGSA");
            chkGpgsv.setText("GPGSV");
            chkGpgga.setText("GPGGA");
            chkGpvtg.setText("GPVTG");
        } else {
            chkGprmc.setText("GPRMC/GNRMC/GIRMC");
            chkGpgsa.setText("GPGSA/GNGSA/GIGSA");
            chkGpgsv.setText("GPGSV/GNGSV/GIGSV");
            chkGpgga.setText("GPGGA/GNGGA/GIGGA");
            chkGpvtg.setText("GPVTG/GNVTG/GIVTG");
        }

        if (get_nmea_gprmc() == true) {
            chkGprmc.setSelected(true);
        } else {
            chkGprmc.setSelected(false);
        }

        if (get_nmea_gpgga() == true) {
            chkGpgga.setSelected(true);
        } else {
            chkGpgga.setSelected(false);
        }

        if (get_nmea_gpvtg() == true) {
            chkGpvtg.setSelected(true);
        } else {
            chkGpvtg.setSelected(false);
        }

        if (get_nmea_gpgsa() == true) {
            chkGpgsv.setSelected(true);
        } else {
            chkGpgsv.setSelected(false);
        }

        if (get_nmea_gpgsv() == true) {
            chkGpgsa.setSelected(true);
        } else {
            chkGpgsa.setSelected(false);
        }

        if (clsSharedVariables.get_gps_enabled() == true) {
            chkGps.setSelected(true);
        } else {
            chkGps.setSelected(false);
        }

        if (clsSharedVariables.get_irnss_enabled() == true) {
            chkIrnss.setSelected(true);
        } else {
            chkIrnss.setSelected(false);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMsg = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        chkGpgga = new javax.swing.JCheckBox();
        chkGpvtg = new javax.swing.JCheckBox();
        chkGpgsv = new javax.swing.JCheckBox();
        chkGprmc = new javax.swing.JCheckBox();
        chkGpgsa = new javax.swing.JCheckBox();
        lblConfigName = new javax.swing.JTextField();
        chkIrnss = new javax.swing.JCheckBox();
        chkGps = new javax.swing.JCheckBox();
        lblMsgData = new javax.swing.JLabel();
        chkGpsMsgCfg = new javax.swing.JCheckBox();

        setMinimumSize(new java.awt.Dimension(450, 420));
        setPreferredSize(new java.awt.Dimension(450, 430));

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+3));
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

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

        chkGpgga.setFont(chkGpgga.getFont().deriveFont(chkGpgga.getFont().getStyle() | java.awt.Font.BOLD, chkGpgga.getFont().getSize()+7));
        chkGpgga.setForeground(new java.awt.Color(0, 0, 102));
        chkGpgga.setText("GPGGA");
        chkGpgga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGpggaActionPerformed(evt);
            }
        });

        chkGpvtg.setFont(chkGpvtg.getFont().deriveFont(chkGpvtg.getFont().getStyle() | java.awt.Font.BOLD, chkGpvtg.getFont().getSize()+7));
        chkGpvtg.setForeground(new java.awt.Color(0, 0, 102));
        chkGpvtg.setText("GPVTG");

        chkGpgsv.setFont(chkGpgsv.getFont().deriveFont(chkGpgsv.getFont().getStyle() | java.awt.Font.BOLD, chkGpgsv.getFont().getSize()+7));
        chkGpgsv.setForeground(new java.awt.Color(0, 0, 102));
        chkGpgsv.setText("GPGSV");

        chkGprmc.setFont(chkGprmc.getFont().deriveFont(chkGprmc.getFont().getStyle() | java.awt.Font.BOLD, chkGprmc.getFont().getSize()+7));
        chkGprmc.setForeground(new java.awt.Color(0, 0, 102));
        chkGprmc.setSelected(true);
        chkGprmc.setText("GPRMC");

        chkGpgsa.setFont(chkGpgsa.getFont().deriveFont(chkGpgsa.getFont().getStyle() | java.awt.Font.BOLD, chkGpgsa.getFont().getSize()+7));
        chkGpgsa.setForeground(new java.awt.Color(0, 0, 102));
        chkGpgsa.setText("GPGSA");

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("GNSS");
        lblConfigName.setPreferredSize(new java.awt.Dimension(500, 32));
        lblConfigName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblConfigNameActionPerformed(evt);
            }
        });

        chkIrnss.setFont(chkIrnss.getFont().deriveFont(chkIrnss.getFont().getStyle() | java.awt.Font.BOLD, chkIrnss.getFont().getSize()+7));
        chkIrnss.setForeground(new java.awt.Color(0, 0, 102));
        chkIrnss.setText("IRNSS");
        chkIrnss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIrnssActionPerformed(evt);
            }
        });

        chkGps.setFont(chkGps.getFont().deriveFont(chkGps.getFont().getStyle() | java.awt.Font.BOLD, chkGps.getFont().getSize()+7));
        chkGps.setForeground(new java.awt.Color(0, 0, 102));
        chkGps.setSelected(true);
        chkGps.setText("GNSS");
        chkGps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGpsActionPerformed(evt);
            }
        });

        lblMsgData.setFont(lblMsgData.getFont().deriveFont(lblMsgData.getFont().getStyle() | java.awt.Font.BOLD, lblMsgData.getFont().getSize()+7));
        lblMsgData.setForeground(new java.awt.Color(0, 102, 0));
        lblMsgData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        chkGpsMsgCfg.setFont(chkGpsMsgCfg.getFont().deriveFont(chkGpsMsgCfg.getFont().getStyle() | java.awt.Font.BOLD, chkGpsMsgCfg.getFont().getSize()+7));
        chkGpsMsgCfg.setForeground(new java.awt.Color(0, 0, 102));
        chkGpsMsgCfg.setText("GPS MSG CFG");
        chkGpsMsgCfg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkGpsMsgCfgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfigName, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkGprmc)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkIrnss)
                        .addGap(93, 93, 93)
                        .addComponent(chkGps))
                    .addComponent(chkGpgsa)
                    .addComponent(chkGpgsv)
                    .addComponent(chkGpgga)
                    .addComponent(chkGpvtg)
                    .addComponent(chkGpsMsgCfg))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMsgData, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chkGpgga, chkGpgsa, chkGpgsv, chkGprmc, chkGpvtg});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkIrnss)
                    .addComponent(chkGps))
                .addGap(10, 10, 10)
                .addComponent(chkGprmc)
                .addGap(6, 6, 6)
                .addComponent(chkGpgsa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkGpgsv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkGpgga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkGpvtg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkGpsMsgCfg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsgData, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chkGpgga, chkGpgsa, chkGpgsv, chkGprmc, chkGpvtg});

    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        String str;
        String checksum;
        if (chkGprmc.isSelected()) {
            set_nmea_gprmc(true);
        } else {
            set_nmea_gprmc(false);
        }
        if (chkGpgga.isSelected()) {
            set_nmea_gpgga(true);
        } else {
            set_nmea_gpgga(false);
        }

        if (chkGpvtg.isSelected()) {
            set_nmea_gpvtg(true);
        } else {
            set_nmea_gpvtg(false);
        }

        if (chkGpgsv.isSelected()) {
            set_nmea_gpgsa(true);
        } else {
            set_nmea_gpgsa(false);
        }

        if (chkGpgsa.isSelected()) {
            set_nmea_gpgsv(true);
        } else {
            set_nmea_gpgsv(false);
        }
        if (!chkIrnss.isSelected() && !chkGps.isSelected()) {
            lblMsg.setText("select GPS/IRNSS/GLONASSS");
            return;
        }

        if (chkGps.isSelected()) {
            clsSharedVariables.set_gps_enabled(true);
        } else {
            clsSharedVariables.set_gps_enabled(false);
        }
        if (chkIrnss.isSelected()) {
            clsSharedVariables.set_irnss_enabled(true);
        } else {
            clsSharedVariables.set_irnss_enabled(false);
        }
        if (chkIrnss.isSelected() && chkGps.isSelected()) {
            str = "$PAIR066,1,0,1,0,0,1*3B\r\n";
            lblMsg.setText(gpsSerialWriteString(str));

        } else if (chkGps.isSelected()) {
            str = "$PAIR066,1,0,0,0,0,0*3B\r\n";
            lblMsg.setText(gpsSerialWriteString(str));

        } else if (chkIrnss.isSelected()) {
            str = "$PAIR066,0,0,0,0,0,1*3B\r\n";
            lblMsg.setText(gpsSerialWriteString(str));

        }

        clsReadFiles obj = new clsReadFiles();
        if (obj.write_cfg_data_file()) {
            lblMsg.setText(" Configuration Saved");
        } else {
            lblMsg.setText("Configuration Not Saved");
        }
        gpsSerialWrite();

        if (chkGpsMsgCfg.isSelected()) {
            //$PSTMCFGMSGL,0,1,00180056,7ec21ff0<CR><LF>
            //0x7ec22000-0x10 ==7EC21FF0
            checksum = "PSTMCFGMSGL,0,1,00180056,7ec21ff0";
            str = "$PSTMCFGMSGL,0,1,00180056,7ec21ff0*" + checksum + "/r/n";
            lblMsg.setText(gpsSerialWriteString(str));
        } else {
            checksum = "PSTMCFGMSGL,0,1,00180056,7ec22000";
            str = "$PSTMCFGMSGL,0,1,00180056,7ec22000*" + checksum + "/r/n";
            gpsSerialWriteString(str);
        }

        checksum = "PSTMSAVEPAR"; //Save the parameter
        str = "$PSTMSAVEPAR*" + checksum + "/r/n";
        gpsSerialWriteString(str);

        checksum = "PSTMSRR"; //reset the gps module
        str = "$PSTMSRR*" + checksum + "/r/n";
        gpsSerialWriteString(str);

    }//GEN-LAST:event_btnSaveActionPerformed

    private void lblConfigNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblConfigNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblConfigNameActionPerformed

    private void chkGpggaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGpggaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkGpggaActionPerformed

    private void chkIrnssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIrnssActionPerformed
        // TODO add your handling code here:
        if (chkIrnss.isSelected()) {
            chkGprmc.setText("GPRMC/GNRMC/GIRMC");
            chkGpgsa.setText("GPGSA/GNGSA/GIGSA");
            chkGpgsv.setText("GPGSV/GNGSV/GIGSV");
            chkGpgga.setText("GPGGA/GNGGA/GIGGA");
            chkGpvtg.setText("GPVTG/GNVTG/GIVTG");
        } else {
            chkGprmc.setText("GPRMC");
            chkGpgsa.setText("GPGSA");
            chkGpgsv.setText("GPGSV");
            chkGpgga.setText("GPGGA");
            chkGpvtg.setText("GPVTG");
        }

    }//GEN-LAST:event_chkIrnssActionPerformed

    private void chkGpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGpsActionPerformed
        // TODO add your handling code here:
        if (chkGps.isSelected() && !chkIrnss.isSelected()) {
            chkGprmc.setText("GPRMC");
            chkGpgsa.setText("GPGSA");
            chkGpgsv.setText("GPGSV");
            chkGpgga.setText("GPGGA");
            chkGpvtg.setText("GPVTG");
        } else if (chkGps.isSelected() && chkIrnss.isSelected()) {
            chkGprmc.setText("GPRMC/GNRMC/GIRMC");
            chkGpgsa.setText("GPGSA/GNGSA/GIGSA");
            chkGpgsv.setText("GPGSV/GNGSV/GIGSV");
            chkGpgga.setText("GPGGA/GNGGA/GIGGA");
            chkGpvtg.setText("GPVTG/GNVTG/GIVTG");
        }
    }//GEN-LAST:event_chkGpsActionPerformed

    private void chkGpsMsgCfgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkGpsMsgCfgActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkGpsMsgCfgActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkGpgga;
    private javax.swing.JCheckBox chkGpgsa;
    private javax.swing.JCheckBox chkGpgsv;
    private javax.swing.JCheckBox chkGprmc;
    private javax.swing.JCheckBox chkGps;
    private javax.swing.JCheckBox chkGpsMsgCfg;
    private javax.swing.JCheckBox chkGpvtg;
    private javax.swing.JCheckBox chkIrnss;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblMsgData;
    // End of variables declaration//GEN-END:variables
}
