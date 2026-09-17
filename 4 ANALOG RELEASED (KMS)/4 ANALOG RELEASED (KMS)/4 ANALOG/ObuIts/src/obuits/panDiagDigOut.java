package obuits;

import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import static obuits.clsDefines.HARDDISK_PATH;
import static obuits.clsDefines.VIDEO_CONNECT;
import static obuits.clsDefines.VIDEO_DISCONNECT;
import static obuits.clsDefines.usb_filepath;
import static obuits.clsSharedVariables.getDigInp1Name;
import static obuits.clsSharedVariables.getDigInp2Name;
import static obuits.clsSharedVariables.getDigInp3Name;
import static obuits.clsSharedVariables.getDigInp4Name;

public class panDiagDigOut extends javax.swing.JPanel {

    Timer timer = null;
    TimerTask timerTask = null;
    Timer timerLed = null;
    TimerTask timerTaskLed = null;
    Timer timerReset = null;
    TimerTask timerTaskReset = null;
    Timer timer_usbhd = null;
    TimerTask timerTask_usbhd = null;

    public panDiagDigOut() {
        initComponents();
        jLayeredPane1.setVisible(false);
        jPanel1.setVisible(false);
        btnCanReset.setVisible(false);
        lblMsg.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblConfigName = new javax.swing.JTextField();
        lblMsg = new javax.swing.JLabel();
        btnCanReset = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        chkHardDisk = new javax.swing.JCheckBox();
        chkUsb = new javax.swing.JCheckBox();
        btnUsbHd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        chkDigOut1 = new javax.swing.JCheckBox();
        chkDigOut2 = new javax.swing.JCheckBox();
        chkDigOut4 = new javax.swing.JCheckBox();
        chkDigOut3 = new javax.swing.JCheckBox();
        btnDigOut2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        chkCam6 = new javax.swing.JCheckBox();
        chkCam7 = new javax.swing.JCheckBox();
        chkCam4 = new javax.swing.JCheckBox();
        chkCam8 = new javax.swing.JCheckBox();
        chkCam2 = new javax.swing.JCheckBox();
        chkCam1 = new javax.swing.JCheckBox();
        chkCam3 = new javax.swing.JCheckBox();
        chkCam5 = new javax.swing.JCheckBox();
        btLedTest = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblDigInVal1 = new javax.swing.JLabel();
        lblDigInVal4 = new javax.swing.JLabel();
        lblDigInVal2 = new javax.swing.JLabel();
        btnDigIn1 = new javax.swing.JButton();
        btnDigIn3 = new javax.swing.JButton();
        btnDigIn2 = new javax.swing.JButton();
        btnDigIn4 = new javax.swing.JButton();
        lblDigInVal3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAnalogIn3 = new javax.swing.JButton();
        scrollpane = new javax.swing.JScrollPane();
        lblAnalogIn3 = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        btnChkUsb = new javax.swing.JButton();
        btnAutoMountHd = new javax.swing.JButton();
        btnChkHarddisk = new javax.swing.JButton();
        btnMountHd = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setFont(getFont().deriveFont(getFont().getStyle() | java.awt.Font.BOLD, getFont().getSize()+4));
        setPreferredSize(new java.awt.Dimension(500, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("INPUT/OUTPUT");
        lblConfigName.setPreferredSize(new java.awt.Dimension(500, 32));
        lblConfigName.setRequestFocusEnabled(false);
        lblConfigName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblConfigNameActionPerformed(evt);
            }
        });

        lblMsg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Testing");

        btnCanReset.setBackground(new java.awt.Color(47, 49, 51));
        btnCanReset.setFont(btnCanReset.getFont().deriveFont(btnCanReset.getFont().getStyle() | java.awt.Font.BOLD, btnCanReset.getFont().getSize()+5));
        btnCanReset.setForeground(new java.awt.Color(255, 255, 255));
        btnCanReset.setText("CAN RESET");
        btnCanReset.setPreferredSize(new java.awt.Dimension(100, 30));
        btnCanReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanResetActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkHardDisk.setFont(chkHardDisk.getFont().deriveFont(chkHardDisk.getFont().getStyle() | java.awt.Font.BOLD, chkHardDisk.getFont().getSize()+5));
        chkHardDisk.setText("HARD DISK");

        chkUsb.setFont(chkUsb.getFont().deriveFont(chkUsb.getFont().getStyle() | java.awt.Font.BOLD, chkUsb.getFont().getSize()+5));
        chkUsb.setText("USB ");
        chkUsb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUsbActionPerformed(evt);
            }
        });

        btnUsbHd.setBackground(new java.awt.Color(47, 49, 51));
        btnUsbHd.setFont(btnUsbHd.getFont().deriveFont(btnUsbHd.getFont().getStyle() | java.awt.Font.BOLD, btnUsbHd.getFont().getSize()+5));
        btnUsbHd.setForeground(new java.awt.Color(255, 255, 255));
        btnUsbHd.setText("SEND");
        btnUsbHd.setPreferredSize(new java.awt.Dimension(100, 30));
        btnUsbHd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsbHdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkUsb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkHardDisk, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsbHd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkUsb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkHardDisk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsbHd, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(504, 51));

        chkDigOut1.setFont(chkDigOut1.getFont().deriveFont(chkDigOut1.getFont().getStyle() | java.awt.Font.BOLD, chkDigOut1.getFont().getSize()+5));
        chkDigOut1.setForeground(new java.awt.Color(0, 51, 51));
        chkDigOut1.setText("D/O 1");
        chkDigOut1.setPreferredSize(new java.awt.Dimension(90, 35));
        chkDigOut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDigOut1ActionPerformed(evt);
            }
        });

        chkDigOut2.setFont(chkDigOut2.getFont().deriveFont(chkDigOut2.getFont().getStyle() | java.awt.Font.BOLD, chkDigOut2.getFont().getSize()+5));
        chkDigOut2.setForeground(new java.awt.Color(0, 51, 51));
        chkDigOut2.setText("D/O 2");
        chkDigOut2.setPreferredSize(new java.awt.Dimension(90, 35));
        chkDigOut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDigOut2ActionPerformed(evt);
            }
        });

        chkDigOut4.setFont(chkDigOut4.getFont().deriveFont(chkDigOut4.getFont().getStyle() | java.awt.Font.BOLD, chkDigOut4.getFont().getSize()+5));
        chkDigOut4.setForeground(new java.awt.Color(0, 51, 51));
        chkDigOut4.setText("D/O 4");
        chkDigOut4.setPreferredSize(new java.awt.Dimension(90, 35));
        chkDigOut4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDigOut4ActionPerformed(evt);
            }
        });

        chkDigOut3.setFont(chkDigOut3.getFont().deriveFont(chkDigOut3.getFont().getStyle() | java.awt.Font.BOLD, chkDigOut3.getFont().getSize()+5));
        chkDigOut3.setForeground(new java.awt.Color(0, 51, 51));
        chkDigOut3.setText("D/O 3");
        chkDigOut3.setPreferredSize(new java.awt.Dimension(90, 35));
        chkDigOut3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDigOut3ActionPerformed(evt);
            }
        });

        btnDigOut2.setBackground(new java.awt.Color(47, 49, 51));
        btnDigOut2.setFont(btnDigOut2.getFont().deriveFont(btnDigOut2.getFont().getStyle() | java.awt.Font.BOLD, btnDigOut2.getFont().getSize()+2));
        btnDigOut2.setForeground(new java.awt.Color(255, 255, 255));
        btnDigOut2.setText("SEND");
        btnDigOut2.setPreferredSize(new java.awt.Dimension(80, 35));
        btnDigOut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigOut2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkDigOut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkDigOut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkDigOut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkDigOut4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDigOut2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkDigOut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDigOut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDigOut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDigOut4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDigOut2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkCam6.setFont(chkCam6.getFont().deriveFont(chkCam6.getFont().getStyle() | java.awt.Font.BOLD, chkCam6.getFont().getSize()+3));
        chkCam6.setForeground(new java.awt.Color(0, 51, 51));
        chkCam6.setText("C6");
        chkCam6.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam6ActionPerformed(evt);
            }
        });

        chkCam7.setFont(chkCam7.getFont().deriveFont(chkCam7.getFont().getStyle() | java.awt.Font.BOLD, chkCam7.getFont().getSize()+3));
        chkCam7.setForeground(new java.awt.Color(0, 51, 51));
        chkCam7.setText("C7");
        chkCam7.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam7ActionPerformed(evt);
            }
        });

        chkCam4.setFont(chkCam4.getFont().deriveFont(chkCam4.getFont().getStyle() | java.awt.Font.BOLD, chkCam4.getFont().getSize()+3));
        chkCam4.setForeground(new java.awt.Color(0, 51, 51));
        chkCam4.setText("C4");
        chkCam4.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam4ActionPerformed(evt);
            }
        });

        chkCam8.setFont(chkCam8.getFont().deriveFont(chkCam8.getFont().getStyle() | java.awt.Font.BOLD, chkCam8.getFont().getSize()+3));
        chkCam8.setForeground(new java.awt.Color(0, 51, 51));
        chkCam8.setText("C8");
        chkCam8.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam8ActionPerformed(evt);
            }
        });

        chkCam2.setFont(chkCam2.getFont().deriveFont(chkCam2.getFont().getStyle() | java.awt.Font.BOLD, chkCam2.getFont().getSize()+3));
        chkCam2.setForeground(new java.awt.Color(0, 51, 51));
        chkCam2.setText("C2");
        chkCam2.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam2ActionPerformed(evt);
            }
        });

        chkCam1.setFont(chkCam1.getFont().deriveFont(chkCam1.getFont().getStyle() | java.awt.Font.BOLD, chkCam1.getFont().getSize()+3));
        chkCam1.setForeground(new java.awt.Color(0, 51, 51));
        chkCam1.setText("C1");
        chkCam1.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam1ActionPerformed(evt);
            }
        });

        chkCam3.setFont(chkCam3.getFont().deriveFont(chkCam3.getFont().getStyle() | java.awt.Font.BOLD, chkCam3.getFont().getSize()+3));
        chkCam3.setForeground(new java.awt.Color(0, 51, 51));
        chkCam3.setText("C3");
        chkCam3.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam3ActionPerformed(evt);
            }
        });

        chkCam5.setFont(chkCam5.getFont().deriveFont(chkCam5.getFont().getStyle() | java.awt.Font.BOLD, chkCam5.getFont().getSize()+3));
        chkCam5.setForeground(new java.awt.Color(0, 51, 51));
        chkCam5.setText("C5");
        chkCam5.setPreferredSize(new java.awt.Dimension(50, 35));
        chkCam5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCam5ActionPerformed(evt);
            }
        });

        btLedTest.setBackground(new java.awt.Color(47, 49, 51));
        btLedTest.setFont(btLedTest.getFont().deriveFont(btLedTest.getFont().getStyle() | java.awt.Font.BOLD, btLedTest.getFont().getSize()-2));
        btLedTest.setForeground(new java.awt.Color(255, 255, 255));
        btLedTest.setText("LED");
        btLedTest.setPreferredSize(new java.awt.Dimension(100, 30));
        btLedTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLedTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(chkCam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(chkCam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCam5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCam6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCam7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCam8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLedTest, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(chkCam1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chkCam8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btLedTest, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setPreferredSize(new java.awt.Dimension(503, 92));

        lblDigInVal1.setText("1");
        lblDigInVal1.setPreferredSize(new java.awt.Dimension(80, 40));

        lblDigInVal4.setText("1");
        lblDigInVal4.setPreferredSize(new java.awt.Dimension(80, 40));

        lblDigInVal2.setText("1");
        lblDigInVal2.setPreferredSize(new java.awt.Dimension(80, 40));

        btnDigIn1.setBackground(new java.awt.Color(0, 0, 102));
        btnDigIn1.setFont(btnDigIn1.getFont().deriveFont(btnDigIn1.getFont().getStyle() | java.awt.Font.BOLD, btnDigIn1.getFont().getSize()+5));
        btnDigIn1.setForeground(new java.awt.Color(255, 255, 255));
        btnDigIn1.setText("Dig In 1");
        btnDigIn1.setPreferredSize(new java.awt.Dimension(97, 35));
        btnDigIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigIn1ActionPerformed(evt);
            }
        });

        btnDigIn3.setBackground(new java.awt.Color(0, 0, 102));
        btnDigIn3.setFont(btnDigIn3.getFont().deriveFont(btnDigIn3.getFont().getStyle() | java.awt.Font.BOLD, btnDigIn3.getFont().getSize()+5));
        btnDigIn3.setForeground(new java.awt.Color(255, 255, 255));
        btnDigIn3.setText("Dig In 3");
        btnDigIn3.setPreferredSize(new java.awt.Dimension(97, 35));
        btnDigIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigIn3ActionPerformed(evt);
            }
        });

        btnDigIn2.setBackground(new java.awt.Color(0, 0, 102));
        btnDigIn2.setFont(btnDigIn2.getFont().deriveFont(btnDigIn2.getFont().getStyle() | java.awt.Font.BOLD, btnDigIn2.getFont().getSize()+5));
        btnDigIn2.setForeground(new java.awt.Color(255, 255, 255));
        btnDigIn2.setText("Dig In 2");
        btnDigIn2.setPreferredSize(new java.awt.Dimension(97, 35));
        btnDigIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigIn2ActionPerformed(evt);
            }
        });

        btnDigIn4.setBackground(new java.awt.Color(0, 0, 102));
        btnDigIn4.setFont(btnDigIn4.getFont().deriveFont(btnDigIn4.getFont().getStyle() | java.awt.Font.BOLD, btnDigIn4.getFont().getSize()+5));
        btnDigIn4.setForeground(new java.awt.Color(255, 255, 255));
        btnDigIn4.setText("Dig In 4");
        btnDigIn4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDigIn4.setPreferredSize(new java.awt.Dimension(97, 35));
        btnDigIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDigIn4ActionPerformed(evt);
            }
        });

        lblDigInVal3.setText("1");
        lblDigInVal3.setPreferredSize(new java.awt.Dimension(80, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnDigIn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDigInVal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnDigIn3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDigInVal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDigIn2, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(btnDigIn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDigInVal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDigInVal4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDigInVal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDigIn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDigInVal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDigIn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnDigIn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDigIn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDigInVal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDigInVal3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAnalogIn3.setBackground(new java.awt.Color(47, 49, 51));
        btnAnalogIn3.setFont(btnAnalogIn3.getFont().deriveFont(btnAnalogIn3.getFont().getStyle() | java.awt.Font.BOLD, btnAnalogIn3.getFont().getSize()+5));
        btnAnalogIn3.setForeground(new java.awt.Color(255, 255, 255));
        btnAnalogIn3.setText("Analog");
        btnAnalogIn3.setPreferredSize(new java.awt.Dimension(97, 35));
        btnAnalogIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalogIn3ActionPerformed(evt);
            }
        });

        lblAnalogIn3.setColumns(20);
        lblAnalogIn3.setLineWrap(true);
        lblAnalogIn3.setRows(5);
        lblAnalogIn3.setWrapStyleWord(true);
        scrollpane.setViewportView(lblAnalogIn3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnalogIn3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalogIn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnChkUsb.setText("usbchk");

        btnAutoMountHd.setText("Auto Mount HD");
        btnAutoMountHd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoMountHdActionPerformed(evt);
            }
        });

        btnChkHarddisk.setText("harddisk chk");
        btnChkHarddisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChkHarddiskActionPerformed(evt);
            }
        });

        btnMountHd.setText("Mount HD");
        btnMountHd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMountHdActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(btnChkUsb, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnAutoMountHd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnChkHarddisk, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnMountHd, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnChkHarddisk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChkUsb)
                .addGap(18, 18, 18)
                .addComponent(btnMountHd)
                .addGap(5, 5, 5)
                .addComponent(btnAutoMountHd)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnChkUsb)
                        .addComponent(btnChkHarddisk))
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnMountHd)
                        .addComponent(btnAutoMountHd)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConfigName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnCanReset, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(13, 13, 13))
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCanReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDigOut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigOut2ActionPerformed
        // TODO add your handling code here:

        if (chkDigOut1.isSelected()) {
            clsSharedVariables.setDigOut1Value((byte) 1);
        } else {
            clsSharedVariables.setDigOut1Value((byte) 0);
        }
        if (chkDigOut2.isSelected()) {
            clsSharedVariables.setDigOut2Value((byte) 1);
        } else {
            clsSharedVariables.setDigOut2Value((byte) 0);
        }
        if (chkDigOut3.isSelected()) {
            clsSharedVariables.setDigOut3Value((byte) 1);
        } else {
            clsSharedVariables.setDigOut3Value((byte) 0);
        }

        if (chkDigOut4.isSelected()) {
            clsSharedVariables.setDigOut4Value((byte) 1);
        } else {
            clsSharedVariables.setDigOut4Value((byte) 0);
        }
        clsSharedVariables.setDigOutPktCame(true);
        lblMsg.setText("Processing Dig");

        startTimer();
    }//GEN-LAST:event_btnDigOut2ActionPerformed

    private void lblConfigNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblConfigNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblConfigNameActionPerformed

    private void btnDigIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigIn1ActionPerformed
        // TODO add your handling code here:
        if (getDigInp1Name() == clsDefines.DIG_INPUT_NONE) {
            lblDigInVal1.setText(String.valueOf(clsSharedVariables.getDigInp1Value()));
        } else if ((getDigInp1Name()) == clsDefines.DIG_INPUT_SOS) {
            lblDigInVal1.setText(String.valueOf(clsSharedVariables.getDigInp1Value()));
        } else if (getDigInp1Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            lblDigInVal1.setText(String.valueOf(clsSharedVariables.getDigInp1Value()));
        }


    }//GEN-LAST:event_btnDigIn1ActionPerformed

    private void btnDigIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigIn2ActionPerformed
        // TODO add your handling code here:
        lblDigInVal2.setText(getDigInp2Name() + " ");
        if (getDigInp2Name() == clsDefines.DIG_INPUT_NONE) {
            lblDigInVal2.setText(String.valueOf(clsSharedVariables.getDigInp2Value()));
        } else if (getDigInp2Name() == clsDefines.DIG_INPUT_SOS) {
            lblDigInVal2.setText(String.valueOf(clsSharedVariables.getDigInp2Value()));
        } else if (getDigInp2Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            lblDigInVal2.setText(String.valueOf(clsSharedVariables.getDigInp2Value()));
        }
    }//GEN-LAST:event_btnDigIn2ActionPerformed

    private void btnDigIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigIn3ActionPerformed
        // TODO add your handling code here:
        lblDigInVal3.setText(getDigInp3Name() + " ");
        if (getDigInp3Name() == clsDefines.DIG_INPUT_NONE) {
            lblDigInVal3.setText(String.valueOf(clsSharedVariables.getDigInp3Value()));
        } else if ((getDigInp3Name()) == clsDefines.DIG_INPUT_SOS) {
            lblDigInVal3.setText(String.valueOf(clsSharedVariables.getDigInp3Value()));
        } else if (getDigInp3Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            lblDigInVal3.setText(String.valueOf(clsSharedVariables.getDigInp3Value()));
        }
    }//GEN-LAST:event_btnDigIn3ActionPerformed

    private void btnDigIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDigIn4ActionPerformed
        // TODO add your handling code here:
        lblDigInVal4.setText(getDigInp4Name() + " ");
        if (getDigInp4Name() == clsDefines.DIG_INPUT_NONE) {
            lblDigInVal4.setText(String.valueOf(clsSharedVariables.getDigInp4Value()));
        } else if ((getDigInp4Name()) == clsDefines.DIG_INPUT_SOS) {
            lblDigInVal4.setText(String.valueOf(clsSharedVariables.getDigInp4Value()));
        } else if (getDigInp4Name() == clsDefines.DIG_INPUT_VIDEO_RECORDING) {
            lblDigInVal4.setText(String.valueOf(clsSharedVariables.getDigInp4Value()));
        }
    }//GEN-LAST:event_btnDigIn4ActionPerformed

    private void btnAnalogIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalogIn3ActionPerformed
        // TODO add your handling code here:
        try {

            StringBuilder sb = new StringBuilder();
            sb.append("Battery: ");
            sb.append(clsSharedVariables.getAnaBatVoltage());
            sb.append(" V    Mains: ");
            sb.append(clsSharedVariables.getAnaMainsVoltage());
            sb.append(" V \n");

            sb.append("Temp: ");
            sb.append(clsSharedVariables.getAnaTemp());
            sb.append(" °c    ADC0: ");
            sb.append(clsSharedVariables.getAnaAdc0());
            sb.append(" V \n");

            sb.append("ADC1: ");
            sb.append(clsSharedVariables.getAnaAdc1());
            sb.append(" V    ADC2: ");
            sb.append(clsSharedVariables.getAnaAdc2());
            sb.append(" V \n");

            sb.append("ADC3: ");
            sb.append(clsSharedVariables.getAnaAdc3());
            sb.append(" V ");

            lblAnalogIn3.setText(sb.toString());
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnAnalogIn3ActionPerformed

    private void chkDigOut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDigOut1ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_chkDigOut1ActionPerformed

    private void chkDigOut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDigOut2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkDigOut2ActionPerformed

    private void chkDigOut3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDigOut3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkDigOut3ActionPerformed

    private void chkDigOut4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDigOut4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkDigOut4ActionPerformed

    private void chkCam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam1ActionPerformed

    private void chkCam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam2ActionPerformed

    private void chkCam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam3ActionPerformed

    private void chkCam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam4ActionPerformed

    private void chkCam5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam5ActionPerformed

    private void chkCam6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam6ActionPerformed

    private void chkCam7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam7ActionPerformed

    private void chkCam8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCam8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCam8ActionPerformed

    private void btLedTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLedTestActionPerformed
        // TODO add your handling code here:
        clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
        if (chkCam1.isSelected()) {
            objHealth.set_cam1_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam1_status(VIDEO_DISCONNECT);
        }
        if (chkCam2.isSelected()) {
            objHealth.set_cam2_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam2_status(VIDEO_DISCONNECT);
        }
        if (chkCam3.isSelected()) {
            objHealth.set_cam3_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam3_status(VIDEO_DISCONNECT);
        }
        if (chkCam4.isSelected()) {
            objHealth.set_cam4_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam4_status(VIDEO_DISCONNECT);
        }
        if (chkCam5.isSelected()) {
            objHealth.set_cam5_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam5_status(VIDEO_DISCONNECT);
        }
        if (chkCam6.isSelected()) {
            objHealth.set_cam6_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam6_status(VIDEO_DISCONNECT);
        }
        if (chkCam7.isSelected()) {
            objHealth.set_cam7_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam7_status(VIDEO_DISCONNECT);
        }
        if (chkCam8.isSelected()) {
            objHealth.set_cam8_status(VIDEO_CONNECT);
        } else {
            objHealth.set_cam8_status(VIDEO_DISCONNECT);
        }
        lblMsg.setText("Processing LED");
        objHealth.set_cam_status_updated(true);
        clsSharedVariables.setLedDataResCame(false);
        startTimerLed();
    }//GEN-LAST:event_btLedTestActionPerformed

    private void btnCanResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanResetActionPerformed
        // TODO add your handling code here:
        clsCanSerialPort obj = new clsCanSerialPort();
        obj.can_reset_serialport();
        obj = null;
        lblMsg.setText("Processing Can Reset");

        startTimerReset();
    }//GEN-LAST:event_btnCanResetActionPerformed

    private void chkUsbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUsbActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_chkUsbActionPerformed

    private void btnUsbHdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsbHdActionPerformed
        // TODO add your handling code here:
        if (chkUsb.isSelected()) {
            clsSharedVariables.setUsbStatEnableDisable((byte) 1);
        } else {
            clsSharedVariables.setUsbStatEnableDisable((byte) 0);
        }

        if (chkHardDisk.isSelected()) {
            clsSharedVariables.setHdStatEnableDisable((byte) 1);
        } else {
            clsSharedVariables.setHdStatEnableDisable((byte) 0);
        }

        lblMsg.setText("Processing USB and Harddisk Pkt");

        startTimerUsbHd();
    }//GEN-LAST:event_btnUsbHdActionPerformed

    private void btnChkHarddiskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChkHarddiskActionPerformed
        // TODO add your handling code here:

        clsReadFiles objReadFiles = new clsReadFiles();
        if (objReadFiles.check_harddisk_mounted() == true) {
            lblMsg.setText("Harddisk Mounted");

        } else {
            lblMsg.setText("Harddisk Not Mounted");

        }
    }//GEN-LAST:event_btnChkHarddiskActionPerformed
    private boolean check_pendrive_detection() {
        UserPrincipal p = null;
        String path = null;
        int i = 0;
        File[] out_path = usb_filepath.listFiles();
        if (out_path == null) {
            return false;
        }
        Arrays.sort(out_path);

        for (i = 0; i < out_path.length; i++) {
            ////System.out.println(out_path[i]);
            if (!out_path[i].toString().contains("3339OBU") && !out_path[i].toString().contains("Recorder")) {
                try {

                    p = java.nio.file.Files.getOwner(out_path[i].toPath(), LinkOption.NOFOLLOW_LINKS);

                    if (p.toString().equals("root")) {
                    } else {
                        path = out_path[i].getAbsolutePath();
                    }
                } catch (IOException ex) {
                } catch (Exception ex) {
                }
            }
        }

        p = null;
        if (path == null) {
            return false;
        }
        return true;

    }
    private void btnChkUsbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChkUsbActionPerformed
        // TODO add your handling code here:

        if (check_pendrive_detection() == true) {
            lblMsg.setText("Pendrive Detected");
        } else {
            lblMsg.setText("Pendrive Not Detected");

        }

    }//GEN-LAST:event_btnChkUsbActionPerformed

    private void chkUsbStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkUsbStateChanged
        // TODO add your handling code here:
        if (chkUsb.isSelected()) {
            chkUsb.setText("USB Connect");

        } else {
            chkUsb.setText("USB Disconnect");

        }
    }//GEN-LAST:event_chkUsbStateChanged

    private void chkHardDiskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkHardDiskStateChanged
        // TODO add your handling code here:
        if (chkHardDisk.isSelected()) {
            chkHardDisk.setText("HD Connect");
        } else {
            chkHardDisk.setText("HD Disconnect");

        }
    }//GEN-LAST:event_chkHardDiskStateChanged
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

                }

            } catch (IOException e) {

            } catch (Exception ex) {

            }

        }

    }

    private void btnMountHdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMountHdActionPerformed
        // TODO add your handling code here:
        runCmd("sudo mount " + HARDDISK_PATH);
        clsReadFiles objReadFiles = new clsReadFiles();
        if (objReadFiles.check_harddisk_mounted() == true) {
            lblMsg.setText("Harddisk Mounted");
        } else {
            lblMsg.setText("Harddisk Not Mounted");
        }
        objReadFiles = null;
    }//GEN-LAST:event_btnMountHdActionPerformed

    private void btnAutoMountHdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoMountHdActionPerformed

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    clsReadFiles objReadFiles = new clsReadFiles();
                    objReadFiles.harddisk_power_on(false);
                    publish("Harddisk Power Off..");
                    Thread.sleep(1000);
                    objReadFiles.harddisk_power_on(true);
                    publish("Harddisk Power on..");
                    Thread.sleep(1000);
                    objReadFiles.harddisk_mount();
                    publish("Harddisk Mounting..");
                    Thread.sleep(1000);
                    if (objReadFiles.check_harddisk_mounted() == true) {
                        publish("Harddisk Mounted");
                    } else {
                        publish("Harddisk Not Mounted");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(panDiagDigOut.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }

            @Override
            protected void process(List chunks) {
                try {
                    lblMsg.setText((String) chunks.remove(0));
                } catch (Exception ex) {

                }
            }

            @Override
            protected void done() {

            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnAutoMountHdActionPerformed
    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
        stopTimerLed();
        stopTimerReset();
        stopTimerUsbHd();
    }

    static byte timer_inc = 0;

    public void startTimer() {
        //set a new Timer
        if (timer == null) {
            timer_inc = 0;
            timer = new Timer();
            //initialize the TimerTask's job
            initializeTimerTask();
            //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms 
            timer.schedule(timerTask, 500, 500);
        }

    }

    private void stopTimer() {
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            if (timerTask != null) {
                timerTask.cancel();
                timerTask = null;
            }
        } catch (Exception ex) {

        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                if (clsSharedVariables.getDigOutPktCame() == false) {
                    lblMsg.setText("Packet Sent Successfully");

                    timer_inc = 0;
                    stopTimer();
                }
                if (timer_inc++ >= 10) {
                    lblMsg.setText("Packet Failed");

                    timer_inc = 0;
                    stopTimer();
                }

            }
        };
    }

    static byte led_timer_inc = 0;

    public void startTimerLed() {
        //set a new Timer
        if (timerLed == null) {
            led_timer_inc = 0;
            timerLed = new Timer();
            //initialize the TimerTask's job
            initializeTimerTaskLed();
            //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms

            timerLed.schedule(timerTaskLed, 0, 500);
        }

    }

    private void stopTimerLed() {
        try {
            if (timerLed != null) {
                timerLed.cancel();
                timerLed = null;
            }
            if (timerTaskLed != null) {
                timerTaskLed.cancel();
                timerTaskLed = null;
            }
        } catch (Exception ex) {

        }
    }

    public void initializeTimerTaskLed() {
        timerTaskLed = new TimerTask() {
            public void run() {

                if (clsSharedVariables.getLedDataResCame() == true) {
                    lblMsg.setText("Packet Sent Successfully");
                    led_timer_inc = 0;
                    stopTimerLed();
                }
                if (led_timer_inc++ >= 10) {
                    lblMsg.setText("Packet Failed");
                    led_timer_inc = 0;
                    stopTimerLed();
                }

            }
        };
    }

    static byte reset_timer_inc = 0;

    public void startTimerReset() {
        //set a new Timer
        if (timerReset == null) {
            reset_timer_inc = 0;
            timerReset = new Timer();
            //initialize the TimerTask's job
            initializeTimerTaskReset();
            //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms

            timerReset.schedule(timerTaskReset, 0, 500);
        }

    }

    private void stopTimerReset() {
        try {
            if (timerReset != null) {
                timerReset.cancel();
                timerReset = null;
            }
            if (timerTaskReset != null) {
                timerTaskReset.cancel();
                timerTaskReset = null;
            }
        } catch (Exception ex) {

        }
    }

    public void initializeTimerTaskReset() {
        timerTaskReset = new TimerTask() {
            public void run() {

                if (clsSharedVariables.getResetCanDataResCame() == true) {
                    lblMsg.setText("Packet Reset Sent Successfully");

                    reset_timer_inc = 0;
                    stopTimerReset();
                }
                if (reset_timer_inc++ >= 10) {
                    lblMsg.setText("Packet Faireset");

                    reset_timer_inc = 0;
                    stopTimerReset();
                }

            }
        };
    }

    static byte timer_inc_usbhd = 0;

    public void startTimerUsbHd() {
        //set a new Timer
        if (timer_usbhd == null) {

            clsSharedVariables.setUsbHdPktResCame(false);
            clsSharedVariables.setUsbHdPktCame(true);
            timer_inc_usbhd = 0;
            timer_usbhd = new Timer();
            //initialize the TimerTask's job
            initializeTimerTaskUsbHd();
            //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms 
            timer_usbhd.schedule(timerTask_usbhd, 500, 500);
        }

    }

    private void stopTimerUsbHd() {
        try {
            timer_inc_usbhd = 0;
            clsSharedVariables.setUsbHdPktCame(false);
            clsSharedVariables.setUsbHdPktResCame(false);
            if (timer_usbhd != null) {
                timer_usbhd.cancel();
                timer_usbhd = null;
            }
            if (timerTask_usbhd != null) {
                timerTask_usbhd.cancel();
                timerTask_usbhd = null;
            }
        } catch (Exception ex) {

        }
    }

    public void initializeTimerTaskUsbHd() {
        timerTask_usbhd = new TimerTask() {
            public void run() {
                if (clsSharedVariables.getUsbHdPktResCame() == true) {
                    lblMsg.setText("Packet Sent Successfully");

                    timer_inc_usbhd = 0;
                    stopTimerUsbHd();
                }
                if (timer_inc_usbhd++ >= 10) {
                    lblMsg.setText("Packet Failed");

                    timer_inc_usbhd = 0;
                    stopTimerUsbHd();
                }

            }
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLedTest;
    private javax.swing.JButton btnAnalogIn3;
    private javax.swing.JButton btnAutoMountHd;
    private javax.swing.JButton btnCanReset;
    private javax.swing.JButton btnChkHarddisk;
    private javax.swing.JButton btnChkUsb;
    private javax.swing.JButton btnDigIn1;
    private javax.swing.JButton btnDigIn2;
    private javax.swing.JButton btnDigIn3;
    private javax.swing.JButton btnDigIn4;
    private javax.swing.JButton btnDigOut2;
    private javax.swing.JButton btnMountHd;
    private javax.swing.JButton btnUsbHd;
    private javax.swing.JCheckBox chkCam1;
    private javax.swing.JCheckBox chkCam2;
    private javax.swing.JCheckBox chkCam3;
    private javax.swing.JCheckBox chkCam4;
    private javax.swing.JCheckBox chkCam5;
    private javax.swing.JCheckBox chkCam6;
    private javax.swing.JCheckBox chkCam7;
    private javax.swing.JCheckBox chkCam8;
    private javax.swing.JCheckBox chkDigOut1;
    private javax.swing.JCheckBox chkDigOut2;
    private javax.swing.JCheckBox chkDigOut3;
    private javax.swing.JCheckBox chkDigOut4;
    private javax.swing.JCheckBox chkHardDisk;
    private javax.swing.JCheckBox chkUsb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextArea lblAnalogIn3;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblDigInVal1;
    private javax.swing.JLabel lblDigInVal2;
    private javax.swing.JLabel lblDigInVal3;
    private javax.swing.JLabel lblDigInVal4;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JScrollPane scrollpane;
    // End of variables declaration//GEN-END:variables
}
