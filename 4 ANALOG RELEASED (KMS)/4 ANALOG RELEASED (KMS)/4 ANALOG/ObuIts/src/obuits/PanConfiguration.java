package obuits;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import static obuits.clsSharedVariables.configuration_pwd;

public class PanConfiguration extends javax.swing.JPanel {

    private JPanel objPanelConfig = null;
    static boolean keyPadOpen = false;

    public PanConfiguration() {
        initComponents();
       // btnCanConfig.setVisible(false);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        if (clsSharedVariables.driverLoginEnabled == true && clsSharedVariables.getRs232Type() == clsDefines.RS232_RFID) {
            btnLanguage.setVisible(false);
        } else {
            btnLanguage.setVisible(true);
        }
//        if (clsSharedVariables.getSplAudioAnnouncement()) {
//            btnSplAudio.setVisible(true);
//        } else {
//            btnSplAudio.setVisible(false);
//        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        btnCameraEncode1 = new javax.swing.JButton();
        panConfig = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btnDevice = new javax.swing.JButton();
        btnDisplayBoard = new javax.swing.JButton();
        btnServer = new javax.swing.JButton();
        btnVolumeControl = new javax.swing.JButton();
        btnLanguage = new javax.swing.JButton();
        btnNmea = new javax.swing.JButton();
        btnRoute = new javax.swing.JButton();
        btnIO = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        btnCameraEncode1.setBackground(new java.awt.Color(43, 122, 202));
        btnCameraEncode1.setFont(btnCameraEncode1.getFont().deriveFont(btnCameraEncode1.getFont().getStyle() | java.awt.Font.BOLD, btnCameraEncode1.getFont().getSize()+5));
        btnCameraEncode1.setForeground(new java.awt.Color(255, 255, 255));
        btnCameraEncode1.setText("CAMERA ENCODE");
        btnCameraEncode1.setActionCommand("");
        btnCameraEncode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCameraEncode1ActionPerformed(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(700, 430));

        panConfig.setPreferredSize(new java.awt.Dimension(700, 530));
        panConfig.setRequestFocusEnabled(false);
        panConfig.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panConfigLayout = new javax.swing.GroupLayout(panConfig);
        panConfig.setLayout(panConfigLayout);
        panConfigLayout.setHorizontalGroup(
            panConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        panConfigLayout.setVerticalGroup(
            panConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(202, 500));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(0, 0, 102), new java.awt.Color(90, 119, 246)));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 2000));

        btnDevice.setBackground(new java.awt.Color(47, 49, 51));
        btnDevice.setFont(btnDevice.getFont().deriveFont(btnDevice.getFont().getStyle() | java.awt.Font.BOLD, btnDevice.getFont().getSize()+5));
        btnDevice.setForeground(new java.awt.Color(255, 255, 255));
        btnDevice.setText("SYSTEM");
        btnDevice.setActionCommand("");
        btnDevice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDevice.setPreferredSize(new java.awt.Dimension(178, 40));
        btnDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeviceActionPerformed(evt);
            }
        });

        btnDisplayBoard.setBackground(new java.awt.Color(47, 49, 51));
        btnDisplayBoard.setFont(btnDisplayBoard.getFont().deriveFont(btnDisplayBoard.getFont().getStyle() | java.awt.Font.BOLD, btnDisplayBoard.getFont().getSize()+5));
        btnDisplayBoard.setForeground(new java.awt.Color(255, 255, 255));
        btnDisplayBoard.setText("DISPLAY BRD");
        btnDisplayBoard.setActionCommand("");
        btnDisplayBoard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDisplayBoard.setPreferredSize(new java.awt.Dimension(178, 40));
        btnDisplayBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayBoardActionPerformed(evt);
            }
        });

        btnServer.setBackground(new java.awt.Color(47, 49, 51));
        btnServer.setFont(btnServer.getFont().deriveFont(btnServer.getFont().getStyle() | java.awt.Font.BOLD, btnServer.getFont().getSize()+5));
        btnServer.setForeground(new java.awt.Color(255, 255, 255));
        btnServer.setText("NETWORK");
        btnServer.setActionCommand("");
        btnServer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnServer.setPreferredSize(new java.awt.Dimension(178, 40));
        btnServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerActionPerformed(evt);
            }
        });

        btnVolumeControl.setBackground(new java.awt.Color(47, 49, 51));
        btnVolumeControl.setFont(btnVolumeControl.getFont().deriveFont(btnVolumeControl.getFont().getStyle() | java.awt.Font.BOLD, btnVolumeControl.getFont().getSize()+5));
        btnVolumeControl.setForeground(new java.awt.Color(255, 255, 255));
        btnVolumeControl.setText("VOLUME");
        btnVolumeControl.setActionCommand("");
        btnVolumeControl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVolumeControl.setPreferredSize(new java.awt.Dimension(178, 40));
        btnVolumeControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolumeControlActionPerformed(evt);
            }
        });

        btnLanguage.setBackground(new java.awt.Color(47, 49, 51));
        btnLanguage.setFont(btnLanguage.getFont().deriveFont(btnLanguage.getFont().getStyle() | java.awt.Font.BOLD, btnLanguage.getFont().getSize()+5));
        btnLanguage.setForeground(new java.awt.Color(255, 255, 255));
        btnLanguage.setText("LANGUAGE");
        btnLanguage.setActionCommand("");
        btnLanguage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLanguage.setPreferredSize(new java.awt.Dimension(178, 40));
        btnLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanguageActionPerformed(evt);
            }
        });

        btnNmea.setBackground(new java.awt.Color(47, 49, 51));
        btnNmea.setFont(btnNmea.getFont().deriveFont(btnNmea.getFont().getStyle() | java.awt.Font.BOLD, btnNmea.getFont().getSize()+5));
        btnNmea.setForeground(new java.awt.Color(255, 255, 255));
        btnNmea.setText("GNSS");
        btnNmea.setActionCommand("");
        btnNmea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNmea.setPreferredSize(new java.awt.Dimension(178, 40));
        btnNmea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNmeaActionPerformed(evt);
            }
        });

        btnRoute.setBackground(new java.awt.Color(47, 49, 51));
        btnRoute.setFont(btnRoute.getFont().deriveFont(btnRoute.getFont().getStyle() | java.awt.Font.BOLD, btnRoute.getFont().getSize()+5));
        btnRoute.setForeground(new java.awt.Color(255, 255, 255));
        btnRoute.setText("ROUTE");
        btnRoute.setActionCommand("");
        btnRoute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRoute.setPreferredSize(new java.awt.Dimension(178, 40));
        btnRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteActionPerformed(evt);
            }
        });

        btnIO.setBackground(new java.awt.Color(47, 49, 51));
        btnIO.setFont(btnIO.getFont().deriveFont(btnIO.getFont().getStyle() | java.awt.Font.BOLD, btnIO.getFont().getSize()+5));
        btnIO.setForeground(new java.awt.Color(255, 255, 255));
        btnIO.setText("DIGITAL INPUT");
        btnIO.setActionCommand("");
        btnIO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIO.setPreferredSize(new java.awt.Dimension(178, 40));
        btnIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisplayBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolumeControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNmea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnDevice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnDisplayBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolumeControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNmea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(panConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    String prev_control_name = "";
    private void close_keypad() {
        prev_control_name = "";
    }

    public String getPassword(String title) {
        JPanel panel = new JPanel();
        final JPasswordField passwordField = new JPasswordField(10);
        panel.add(new JLabel("Password"));
        panel.add(passwordField);
        JOptionPane pane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION) {
            @Override
            public void selectInitialValue() {
                passwordField.requestFocusInWindow();

            }

        };
        pane.setPreferredSize(new Dimension(300, 130));
        pane.setFont(new java.awt.Font("Tahoma", 1, 18));
        JPanel buttonPanel = (JPanel) pane.getComponent(1);
        // get the handle to the ok button
        JButton buttonOk = (JButton) buttonPanel.getComponent(0);
        // set the text
        buttonOk.setText("OK");

        buttonOk.setFont(new java.awt.Font("Tahoma", 1, 18));
        buttonOk.setPreferredSize(new Dimension(80, 40));  //Set Button size here
        buttonOk.validate();

        JButton buttonCancel = (JButton) buttonPanel.getComponent(1);
        // set the text
        buttonCancel.setFont(new java.awt.Font("Tahoma", 1, 18));
        buttonCancel.setText("CANCEL");
        buttonCancel.setPreferredSize(new Dimension(100, 40));  //Set Button size here
        buttonCancel.validate();

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFieldFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFieldFocusLost(evt);
            }

            private void txtPasswordFieldFocusGained(FocusEvent evt) {
                // TODO add your handling code here:
                if (!prev_control_name.equals("Password")) {
                    clsKeyBoardView obj = new clsKeyBoardView();
                    String str = obj.getKeyBoardValue("Password", "");
                    if (str != null) {
                        passwordField.setText(str);
                    }
                    prev_control_name = "Password";
                    obj = null;
                    str = null;
                } else {
                    prev_control_name = "";
                }
            }

            private void txtPasswordFieldFocusLost(FocusEvent evt) {
                prev_control_name = "";
            }
        });

        JDialog dlg = pane.createDialog(null, title);
        dlg.setVisible(true);

        Object selectedValue = pane.getValue();
        int n = -1;

        if (selectedValue == null) {
            n = JOptionPane.CLOSED_OPTION;
        } else {
            n = Integer.parseInt(selectedValue.toString());
        }
        if (n == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword());

        } else if (n == JOptionPane.CANCEL_OPTION) {
            return null;
        } else {
            return null;
        }
    }

    private void btnDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeviceActionPerformed
        // TODO add your handling code here:
        String m = getPassword("Configuration Password");
        if (m.equals(configuration_pwd)) {
            if (objPanelConfig != null) {
                objPanelConfig.removeAll();
                objPanelConfig = null;
            }
            objPanelConfig = new PanDevice();
            panConfig.removeAll();
            panConfig.setLayout(new java.awt.BorderLayout());
            panConfig.add(objPanelConfig);
            panConfig.revalidate();
            panConfig.repaint();
        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();
    }//GEN-LAST:event_btnDeviceActionPerformed

    private void btnDisplayBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayBoardActionPerformed
        // TODO add your handling code here:

        String m = getPassword("Configuration Password");
        if (m.equals(configuration_pwd)) {
            if (objPanelConfig != null) {
                objPanelConfig.removeAll();
                objPanelConfig = null;
            }
            objPanelConfig = new PanDisplayboard();
            panConfig.removeAll();
            panConfig.setLayout(new java.awt.BorderLayout());
            panConfig.add(objPanelConfig);
            panConfig.revalidate();
            panConfig.repaint();
        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();
    }//GEN-LAST:event_btnDisplayBoardActionPerformed

    private void btnNmeaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNmeaActionPerformed
        // TODO add your handling code here:

        String m = getPassword("Configuration Password");
        if (m.equals(configuration_pwd)) {
            if (objPanelConfig != null) {
                objPanelConfig.removeAll();
                objPanelConfig = null;
            }
            objPanelConfig = new PanNmea();
            panConfig.removeAll();
            panConfig.setLayout(new java.awt.BorderLayout());
            panConfig.add(objPanelConfig);
            panConfig.revalidate();
            panConfig.repaint();
        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();
    }//GEN-LAST:event_btnNmeaActionPerformed

    private void btnServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerActionPerformed
        // TODO add your handling code here:

        String m = getPassword("Configuration Password");
        try {
            if (m.equals(configuration_pwd)) {
                if (objPanelConfig != null) {
                    objPanelConfig.removeAll();
                    objPanelConfig = null;
                }
                objPanelConfig = new PanServer();

                panConfig.removeAll();
                panConfig.setLayout(new java.awt.BorderLayout());
                panConfig.add(objPanelConfig);
                panConfig.revalidate();
                panConfig.repaint();
            } else {
                show_message_dialogbox("Wrong Password");
            }
        } catch (Exception ex) {
            //  System.out.println("network error" + ex.getMessage());
        }
        close_keypad();
    }//GEN-LAST:event_btnServerActionPerformed

    private void btnVolumeControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolumeControlActionPerformed
        // TODO add your handling code here:
        if (objPanelConfig != null) {
            objPanelConfig.removeAll();
            objPanelConfig = null;
        }
        objPanelConfig = new PanVolume();
        panConfig.removeAll();
        panConfig.setLayout(new java.awt.BorderLayout());
        panConfig.add(objPanelConfig);
        panConfig.revalidate();
        panConfig.repaint();
    }//GEN-LAST:event_btnVolumeControlActionPerformed

    private void btnLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanguageActionPerformed
        // TODO add your handling code here:

//        String m = getPassword("Configuration Password");
//        if (m.equals(configuration_pwd)) {
//            if (objPanelConfig != null) {
//                objPanelConfig.removeAll();
//                objPanelConfig = null;
//            }
//            objPanelConfig = new PanLanguage();
//            panConfig.removeAll();
//            panConfig.setLayout(new java.awt.BorderLayout());
//            panConfig.add(objPanelConfig);
//            panConfig.revalidate();
//            panConfig.repaint();
    }//GEN-LAST:event_btnLanguageActionPerformed
//    else {
//            show_message_dialogbox("Wrong Password");
//        }
//        close_keypad();
//    }
    private void btnCameraEncode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCameraEncode1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCameraEncode1ActionPerformed

    private void btnVIDWatermarkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVIDWatermarkingActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnVIDWatermarkingActionPerformed

    private void btnRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteActionPerformed
        // TODO add your handling code here:
        String m = getPassword("Configuration Password");
        if (m.equals(configuration_pwd)) {
            if (objPanelConfig != null) {
                objPanelConfig.removeAll();
                objPanelConfig = null;
            }
            objPanelConfig = new PanRouteConfig();
            panConfig.removeAll();
            panConfig.setLayout(new java.awt.BorderLayout());
            panConfig.add(objPanelConfig);
            panConfig.revalidate();
            panConfig.repaint();
        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();
    }//GEN-LAST:event_btnRouteActionPerformed

    private void btnIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIOActionPerformed
        // TODO add your handling code here:
        String m = getPassword("Configuration Password");
        if (m.equals(configuration_pwd)) {
            if (objPanelConfig != null) {
                objPanelConfig.removeAll();
                objPanelConfig = null;
            }
            objPanelConfig = new PanInputOutput();
            panConfig.removeAll();
            panConfig.setLayout(new java.awt.BorderLayout());
            panConfig.add(objPanelConfig);
            panConfig.revalidate();
            panConfig.repaint();
        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();
    }//GEN-LAST:event_btnIOActionPerformed
    private void show_message_dialogbox(String data) {
        try {
            final JLabel label = new JLabel();
            int timerDelay = 1000;
            label.setText(data);
            label.setBackground(Color.BLUE);
            label.setSize(500, 500);
            new javax.swing.Timer(timerDelay, new ActionListener() {
                int timeLeft = 2;

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCameraEncode1;
    private javax.swing.JButton btnDevice;
    private javax.swing.JButton btnDisplayBoard;
    private javax.swing.JButton btnIO;
    private javax.swing.JButton btnLanguage;
    private javax.swing.JButton btnNmea;
    private javax.swing.JButton btnRoute;
    private javax.swing.JButton btnServer;
    private javax.swing.JButton btnVolumeControl;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panConfig;
    // End of variables declaration//GEN-END:variables
}
