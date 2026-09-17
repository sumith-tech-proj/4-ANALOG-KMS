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
import static obuits.MainFrmIts.reboot_system;
import static obuits.clsDefines.FACTORY_RESET_PWD;
import static obuits.clsDefines.Strings.MANUAL_RESET;

public class PanAllDiagnosis extends javax.swing.JPanel {

    private JPanel objPanelCenterDiag = null;
    String prev_control_name = "";

    public PanAllDiagnosis() {
        initComponents();
       // btnSMSTest.setVisible(true);
        btnScheduleing.setVisible(false);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
      //  jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(15, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPing = new javax.swing.JButton();
        panCenterDiagPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanLeftDiag = new javax.swing.JPanel();
        btnAudio = new javax.swing.JButton();
        btnResetDevice = new javax.swing.JButton();
        btnDtcDevice = new javax.swing.JButton();
        btnGpsGsmDiag = new javax.swing.JButton();
        btnPidDisBrd = new javax.swing.JButton();
        btnCamPlay = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnScheduleing = new javax.swing.JButton();
        btnGyroscope = new javax.swing.JButton();
        btnOutput = new javax.swing.JButton();

        btnPing.setBackground(new java.awt.Color(47, 49, 51));
        btnPing.setFont(btnPing.getFont().deriveFont(btnPing.getFont().getStyle() | java.awt.Font.BOLD, btnPing.getFont().getSize()+5));
        btnPing.setForeground(new java.awt.Color(255, 255, 255));
        btnPing.setText("PING");
        btnPing.setBorderPainted(false);
        btnPing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPingActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(23, 29, 32));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 430));

        panCenterDiagPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panCenterDiagPane.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout panCenterDiagPaneLayout = new javax.swing.GroupLayout(panCenterDiagPane);
        panCenterDiagPane.setLayout(panCenterDiagPaneLayout);
        panCenterDiagPaneLayout.setHorizontalGroup(
            panCenterDiagPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        panCenterDiagPaneLayout.setVerticalGroup(
            panCenterDiagPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        jScrollPane1.setBackground(new java.awt.Color(23, 29, 32));
        jScrollPane1.setFocusable(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(190, 430));

        PanLeftDiag.setDoubleBuffered(false);
        PanLeftDiag.setPreferredSize(new java.awt.Dimension(190, 1300));

        btnAudio.setBackground(new java.awt.Color(47, 49, 51));
        btnAudio.setFont(btnAudio.getFont().deriveFont(btnAudio.getFont().getStyle() | java.awt.Font.BOLD, btnAudio.getFont().getSize()+5));
        btnAudio.setForeground(new java.awt.Color(255, 255, 255));
        btnAudio.setText("AUDIO");
        btnAudio.setBorderPainted(false);
        btnAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAudioActionPerformed(evt);
            }
        });

        btnResetDevice.setBackground(new java.awt.Color(47, 49, 51));
        btnResetDevice.setFont(btnResetDevice.getFont().deriveFont(btnResetDevice.getFont().getStyle() | java.awt.Font.BOLD, btnResetDevice.getFont().getSize()+5));
        btnResetDevice.setForeground(new java.awt.Color(255, 255, 255));
        btnResetDevice.setText("RESET");
        btnResetDevice.setBorderPainted(false);
        btnResetDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetDeviceActionPerformed(evt);
            }
        });

        btnDtcDevice.setBackground(new java.awt.Color(47, 49, 51));
        btnDtcDevice.setFont(btnDtcDevice.getFont().deriveFont(btnDtcDevice.getFont().getStyle() | java.awt.Font.BOLD, btnDtcDevice.getFont().getSize()+5));
        btnDtcDevice.setForeground(new java.awt.Color(255, 255, 255));
        btnDtcDevice.setText("DEVICE ");
        btnDtcDevice.setBorderPainted(false);
        btnDtcDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDtcDeviceActionPerformed(evt);
            }
        });

        btnGpsGsmDiag.setBackground(new java.awt.Color(47, 49, 51));
        btnGpsGsmDiag.setFont(btnGpsGsmDiag.getFont().deriveFont(btnGpsGsmDiag.getFont().getStyle() | java.awt.Font.BOLD, btnGpsGsmDiag.getFont().getSize()+5));
        btnGpsGsmDiag.setForeground(new java.awt.Color(255, 255, 255));
        btnGpsGsmDiag.setText("MODULE");
        btnGpsGsmDiag.setBorderPainted(false);
        btnGpsGsmDiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGpsGsmDiagActionPerformed(evt);
            }
        });

        btnPidDisBrd.setBackground(new java.awt.Color(47, 49, 51));
        btnPidDisBrd.setFont(btnPidDisBrd.getFont().deriveFont(btnPidDisBrd.getFont().getStyle() | java.awt.Font.BOLD, btnPidDisBrd.getFont().getSize()+5));
        btnPidDisBrd.setForeground(new java.awt.Color(255, 255, 255));
        btnPidDisBrd.setText("DISPLAY BRD");
        btnPidDisBrd.setBorderPainted(false);
        btnPidDisBrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPidDisBrdActionPerformed(evt);
            }
        });

        btnCamPlay.setBackground(new java.awt.Color(47, 49, 51));
        btnCamPlay.setFont(btnCamPlay.getFont().deriveFont(btnCamPlay.getFont().getStyle() | java.awt.Font.BOLD, btnCamPlay.getFont().getSize()+5));
        btnCamPlay.setForeground(new java.awt.Color(255, 255, 255));
        btnCamPlay.setText("CAMERA PLAY");
        btnCamPlay.setBorderPainted(false);
        btnCamPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCamPlayActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(47, 49, 51));
        btnDelete.setFont(btnDelete.getFont().deriveFont(btnDelete.getFont().getStyle() | java.awt.Font.BOLD, btnDelete.getFont().getSize()+5));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("SYSTEM");
        btnDelete.setBorderPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnScheduleing.setBackground(new java.awt.Color(47, 49, 51));
        btnScheduleing.setFont(btnScheduleing.getFont().deriveFont(btnScheduleing.getFont().getStyle() | java.awt.Font.BOLD, btnScheduleing.getFont().getSize()+5));
        btnScheduleing.setForeground(new java.awt.Color(255, 255, 255));
        btnScheduleing.setText("SCHEDULING");
        btnScheduleing.setBorderPainted(false);
        btnScheduleing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleingActionPerformed(evt);
            }
        });

        btnGyroscope.setBackground(new java.awt.Color(47, 49, 51));
        btnGyroscope.setFont(btnGyroscope.getFont().deriveFont(btnGyroscope.getFont().getStyle() | java.awt.Font.BOLD, btnGyroscope.getFont().getSize()+5));
        btnGyroscope.setForeground(new java.awt.Color(255, 255, 255));
        btnGyroscope.setText("GYROSCOPE");
        btnGyroscope.setBorderPainted(false);
        btnGyroscope.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGyroscopeActionPerformed(evt);
            }
        });

        btnOutput.setBackground(new java.awt.Color(47, 49, 51));
        btnOutput.setFont(btnOutput.getFont().deriveFont(btnOutput.getFont().getStyle() | java.awt.Font.BOLD, btnOutput.getFont().getSize()+5));
        btnOutput.setForeground(new java.awt.Color(255, 255, 255));
        btnOutput.setText("DIGITAL I/O");
        btnOutput.setBorderPainted(false);
        btnOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOutputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanLeftDiagLayout = new javax.swing.GroupLayout(PanLeftDiag);
        PanLeftDiag.setLayout(PanLeftDiagLayout);
        PanLeftDiagLayout.setHorizontalGroup(
            PanLeftDiagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanLeftDiagLayout.createSequentialGroup()
                .addGroup(PanLeftDiagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnGpsGsmDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCamPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnScheduleing, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPidDisBrd, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDtcDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGyroscope, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        PanLeftDiagLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAudio, btnCamPlay, btnDelete, btnDtcDevice, btnGpsGsmDiag, btnPidDisBrd, btnResetDevice, btnScheduleing});

        PanLeftDiagLayout.setVerticalGroup(
            PanLeftDiagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanLeftDiagLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnDtcDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPidDisBrd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGpsGsmDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCamPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGyroscope, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnScheduleing, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(866, Short.MAX_VALUE))
        );

        PanLeftDiagLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAudio, btnCamPlay, btnDelete, btnDtcDevice, btnGpsGsmDiag, btnPidDisBrd, btnResetDevice, btnScheduleing});

        jScrollPane1.setViewportView(PanLeftDiag);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panCenterDiagPane, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panCenterDiagPane, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDtcDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDtcDeviceActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new PanDevicecodes();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();
    }//GEN-LAST:event_btnDtcDeviceActionPerformed

    private void btnAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAudioActionPerformed
        // TODO add your handling code here:

        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new PanDiagnostics();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();
    }//GEN-LAST:event_btnAudioActionPerformed

    private void btnResetDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetDeviceActionPerformed
        // TODO add your handling code here: 
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
        Dimension d = new Dimension(300, 100);
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
    }//GEN-LAST:event_btnResetDeviceActionPerformed

    private void btnGpsGsmDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGpsGsmDiagActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new PanGpsDiag();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();
    }//GEN-LAST:event_btnGpsGsmDiagActionPerformed

    private void btnPidDisBrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPidDisBrdActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new PanDisplayBoardDiag();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();

    }//GEN-LAST:event_btnPidDisBrdActionPerformed

    private void btnCamPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCamPlayActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new PanCameraPlay();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();

    }//GEN-LAST:event_btnCamPlayActionPerformed
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

    private void close_keypad() {
        prev_control_name = "";
    }
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        String m = getPassword("Configuration Password");
        if (m.equals(FACTORY_RESET_PWD)) {
            if (objPanelCenterDiag != null) {
                objPanelCenterDiag.removeAll();
                objPanelCenterDiag = null;
            }
            objPanelCenterDiag = new PanDeleteDefaultFiles();
            panCenterDiagPane.removeAll();
            panCenterDiagPane.setLayout(new java.awt.BorderLayout());
            panCenterDiagPane.add(objPanelCenterDiag);
            panCenterDiagPane.revalidate();
            panCenterDiagPane.repaint();
        } else {
            show_message_dialogbox("Wrong Password");
        }
        close_keypad();

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnScheduleingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleingActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new panCreateSchedule();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();
    }//GEN-LAST:event_btnScheduleingActionPerformed

    private void btnGyroscopeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGyroscopeActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new PanGyroscope();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();
    }//GEN-LAST:event_btnGyroscopeActionPerformed

    private void btnPingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPingActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnPingActionPerformed

    private void btnOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOutputActionPerformed
        // TODO add your handling code here:
        if (objPanelCenterDiag != null) {
            objPanelCenterDiag.removeAll();
            objPanelCenterDiag = null;
        }
        objPanelCenterDiag = new panDiagDigOut();
        panCenterDiagPane.removeAll();
        panCenterDiagPane.setLayout(new java.awt.BorderLayout());
        panCenterDiagPane.add(objPanelCenterDiag);
        panCenterDiagPane.revalidate();
        panCenterDiagPane.repaint();
    }//GEN-LAST:event_btnOutputActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanLeftDiag;
    private javax.swing.JButton btnAudio;
    private javax.swing.JButton btnCamPlay;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDtcDevice;
    private javax.swing.JButton btnGpsGsmDiag;
    private javax.swing.JButton btnGyroscope;
    private javax.swing.JButton btnOutput;
    private javax.swing.JButton btnPidDisBrd;
    private javax.swing.JButton btnPing;
    private javax.swing.JButton btnResetDevice;
    private javax.swing.JButton btnScheduleing;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panCenterDiagPane;
    // End of variables declaration//GEN-END:variables
}
