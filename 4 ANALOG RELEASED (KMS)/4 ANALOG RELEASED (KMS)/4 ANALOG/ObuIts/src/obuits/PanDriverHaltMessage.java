
package obuits;

import javax.swing.JPanel;

public class PanDriverHaltMessage extends javax.swing.JPanel {

    byte[] array_panic_id = new byte[5];
    String[] array_panics = new String[5];
    JPanel panMainPane;
    JPanel panCenterMainPane;

    public PanDriverHaltMessage(JPanel panMainPan, JPanel panCenterMainPan) {
        initComponents();
        panMainPane = panMainPan;
        panCenterMainPane = panCenterMainPan;
        short i = 0;
        byte j = 0;
        clsReadFiles objReadFiles = new clsReadFiles();

        String str = objReadFiles.read_driver_delay_reason_messages();
        String[] split_str = str.split(",");
        array_panic_id[0] = 1;
        array_panic_id[1] = 2;
        array_panic_id[2] = 3;
        array_panic_id[3] = 4;
        array_panic_id[4] = 5;

        array_panics[0] = "TRAFFIC JAM";
        array_panics[1] = "ACCIDENT";
        array_panics[2] = "ROUTE DIVERTION";
        array_panics[3] = "SQUAD CHECKING";
        array_panics[4] = "TRAFFIC SIGNAL";

        if (!str.equals("")) {
            for (i = 0; i < split_str.length && i < 5;) {
                try {
                    array_panic_id[j] = Byte.parseByte(split_str[i]);
                    i++;
                    array_panics[j] = split_str[i];

                    i++;
                    j++;
                } catch (Exception ex) {

                }
            }
        }

        this.btnAlert1.setText(array_panics[0]);
        this.btnAlert2.setText(array_panics[1]);
        this.btnAlert3.setText(array_panics[2]);
        this.btnAlert4.setText(array_panics[3]);
        this.btnAlert5.setText(array_panics[4]);

        split_str = null;

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        btnAlert2 = new javax.swing.JButton();
        btnAlert1 = new javax.swing.JButton();
        btnAlert4 = new javax.swing.JButton();
        btnAlert5 = new javax.swing.JButton();
        btnAlert3 = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 480));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+10));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Driver Halt Reasons");

        btnAlert2.setBackground(new java.awt.Color(47, 49, 51));
        btnAlert2.setFont(btnAlert2.getFont().deriveFont(btnAlert2.getFont().getStyle() | java.awt.Font.BOLD, btnAlert2.getFont().getSize()+13));
        btnAlert2.setForeground(new java.awt.Color(255, 255, 255));
        btnAlert2.setText("ACCIDENT");
        btnAlert2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnAlert2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlert2ActionPerformed(evt);
            }
        });

        btnAlert1.setBackground(new java.awt.Color(47, 49, 51));
        btnAlert1.setFont(btnAlert1.getFont().deriveFont(btnAlert1.getFont().getStyle() | java.awt.Font.BOLD, btnAlert1.getFont().getSize()+13));
        btnAlert1.setForeground(new java.awt.Color(255, 255, 255));
        btnAlert1.setText("TRAFFIC JAM");
        btnAlert1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnAlert1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlert1ActionPerformed(evt);
            }
        });

        btnAlert4.setBackground(new java.awt.Color(47, 49, 51));
        btnAlert4.setFont(btnAlert4.getFont().deriveFont(btnAlert4.getFont().getStyle() | java.awt.Font.BOLD, btnAlert4.getFont().getSize()+13));
        btnAlert4.setForeground(new java.awt.Color(255, 255, 255));
        btnAlert4.setText("SQUAD CHECKING");
        btnAlert4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnAlert4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlert4ActionPerformed(evt);
            }
        });

        btnAlert5.setBackground(new java.awt.Color(47, 49, 51));
        btnAlert5.setFont(btnAlert5.getFont().deriveFont(btnAlert5.getFont().getStyle() | java.awt.Font.BOLD, btnAlert5.getFont().getSize()+13));
        btnAlert5.setForeground(new java.awt.Color(255, 255, 255));
        btnAlert5.setText("TRAFFIC SIGNAL");
        btnAlert5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnAlert5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlert5ActionPerformed(evt);
            }
        });

        btnAlert3.setBackground(new java.awt.Color(47, 49, 51));
        btnAlert3.setFont(btnAlert3.getFont().deriveFont(btnAlert3.getFont().getStyle() | java.awt.Font.BOLD, btnAlert3.getFont().getSize()+13));
        btnAlert3.setForeground(new java.awt.Color(255, 255, 255));
        btnAlert3.setText("ROUTE DIVERTION");
        btnAlert3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 102), new java.awt.Color(0, 0, 204), new java.awt.Color(51, 0, 204), new java.awt.Color(0, 51, 153)));
        btnAlert3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlert3ActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getSize()+7f));
        lblMsg.setForeground(new java.awt.Color(0, 51, 51));
        lblMsg.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlert2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlert4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlert5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlert3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlert1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMsg)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAlert1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnAlert2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlert3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlert4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlert5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMsg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlert2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlert2ActionPerformed
        // TODO add your handling code here: 

        try {
            if (clsSharedVariables.getIpAddr1Enable()) {
                cls16833Protocols obj16833Pkts = new cls16833Protocols();
                obj16833Pkts.driver_stopped_reason_messages((byte) array_panic_id[1]);
                obj16833Pkts = null;
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                gpsDriving objDriving = new gpsDriving();
                objDriving.driver_stopped_reason_messages((byte) array_panic_id[1]);
                objDriving = null;
            }
            lblMsg.setText(array_panics[1]);
        } catch (Exception ex) {
        }

        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(panCenterMainPane);
        panMainPane.revalidate();
        panMainPane.repaint();
    }//GEN-LAST:event_btnAlert2ActionPerformed

    private void btnAlert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlert1ActionPerformed
        // TODO add your handling code here:

        if (clsSharedVariables.getIpAddr1Enable()) {
            cls16833Protocols obj16833Pkts = new cls16833Protocols();
            obj16833Pkts.driver_stopped_reason_messages((byte) array_panic_id[0]);
            obj16833Pkts = null;
        }
        if (clsSharedVariables.getIpAddr4Enable()) {
            gpsDriving objDriving = new gpsDriving();
            objDriving.driver_stopped_reason_messages((byte) array_panic_id[0]);
            objDriving = null;
        }
    }//GEN-LAST:event_btnAlert1ActionPerformed

    private void btnAlert4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlert4ActionPerformed
        // TODO add your handling code here:
        if (clsSharedVariables.getIpAddr1Enable()) {
            cls16833Protocols obj16833Pkts = new cls16833Protocols();
            obj16833Pkts.driver_stopped_reason_messages((byte) array_panic_id[3]);
            obj16833Pkts = null;
        }
        if (clsSharedVariables.getIpAddr4Enable()) {
            gpsDriving objDriving = new gpsDriving();
            objDriving.driver_stopped_reason_messages((byte) array_panic_id[3]);
            objDriving = null;
        }
    }//GEN-LAST:event_btnAlert4ActionPerformed

    private void btnAlert5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlert5ActionPerformed
        // TODO add your handling code here:
        if (clsSharedVariables.getIpAddr1Enable()) {
            cls16833Protocols obj16833Pkts = new cls16833Protocols();
            obj16833Pkts.driver_stopped_reason_messages((byte) array_panic_id[4]);
            obj16833Pkts = null;
        }
        if (clsSharedVariables.getIpAddr4Enable()) {
            gpsDriving objDriving = new gpsDriving();
            objDriving.driver_stopped_reason_messages((byte) array_panic_id[4]);
            objDriving = null;
        }
    }//GEN-LAST:event_btnAlert5ActionPerformed

    private void btnAlert3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlert3ActionPerformed
        // TODO add your handling code here:
        if (clsSharedVariables.getIpAddr1Enable()) {
            cls16833Protocols obj16833Pkts = new cls16833Protocols();
            obj16833Pkts.driver_stopped_reason_messages((byte) array_panic_id[2]);
            obj16833Pkts = null;
        }
        if (clsSharedVariables.getIpAddr4Enable()) {
            gpsDriving objDriving = new gpsDriving();
            objDriving.driver_stopped_reason_messages((byte) array_panic_id[2]);
            objDriving = null;
        }
    }//GEN-LAST:event_btnAlert3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlert1;
    private javax.swing.JButton btnAlert2;
    private javax.swing.JButton btnAlert3;
    private javax.swing.JButton btnAlert4;
    private javax.swing.JButton btnAlert5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblMsg;
    // End of variables declaration//GEN-END:variables
}
