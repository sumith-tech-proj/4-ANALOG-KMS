package obuits;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import jssc.SerialPortList;
import static obuits.GPSLocationService.gpsSerialWriteString;
import static obuits.MainFrmIts.refresh_watchdog;
import static obuits.MainFrmIts.restart_module_ports_changed;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsDefines.GPRS_NETWORKINTERFACE_PATH;
import static obuits.clsDefines.ONE_GB;
import static obuits.clsDefines.REFRESH_WATCHDOG_TIMER_ENABLED;
import static obuits.clsDefines.main_route_path;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.media_path;
import static obuits.clsSharedVariables.getRebootSystemEnabled;
import static obuits.clsSharedVariables.getSimReady;
import static obuits.clsSharedVariables.get_QCCID;
import static obuits.clsSharedVariables.get_own_mobileno;
import static obuits.clsSharedVariables.setCamDiagStatusCamera;
import static obuits.clsSharedVariables.setCamDiagStatusEnabled;
import static obuits.clsSharedVariables.setCan1RawEnable;
import static obuits.clsSharedVariables.setCanDiagEnabled;
import static obuits.clsSharedVariables.setCanDiagEnabled1;
import static obuits.clsSharedVariables.setCanRawEnable;
import static obuits.clsSharedVariables.setGpsDiagEnabled;
import static obuits.clsSharedVariables.setGsmDiagEnabled;
import static obuits.clsSharedVariables.setVideoTamperDiagEnabled;

/**
 *
 * @author Sumitha
 */
public class PanGpsDiag extends javax.swing.JPanel {

    Timer gpsTimer;
    TimerTask gpsTimerTask;
    Process process;
    byte button_pressed = 0;
    final byte btn_gsmPorts = 0;
    final byte btn_imeiNo = 1;
    final byte btn_gsmReset = 2;
    final byte btn_gsmDiag = 3;
    final byte btn_gpsDiag = 4;
    final byte btn_canDiag = 5;
    final byte btn_RsTx232 = 6;
    final byte btn_RsRx232 = 7;
    final byte btn_EthernetTx = 8;
    final byte btn_EthernetRx = 9;
    final byte btn_VideoTamper = 10;
    final byte btn_Harddisk = 11;
    final byte btn_camera = 12;
    final byte btn_Temp = 13;
    final byte btn_canDiag1 = 14;

    public PanGpsDiag() {
        initComponents();
        cmbCam.setVisible(false);
        btnGetImeiMobile.setVisible(true);
        btnVideoTamper.setVisible(false);
        btnEthernetTx.setVisible(false);
        btnEthernetRx.setVisible(false);
        btnRs232Tx.setVisible(true);
        btnRs232Rx.setVisible(true);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        txtBal.setVisible(true);
        txtBal.setText(clsSharedVariables.getChkNetBalCommand());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBal1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtData = new javax.swing.JTextArea();
        btnTtyPorts = new javax.swing.JButton();
        btnGetImeiMobile = new javax.swing.JButton();
        btnGsmReset = new javax.swing.JButton();
        btnGsmDiag = new javax.swing.JButton();
        btnGpsDiag = new javax.swing.JButton();
        btnCanDiag = new javax.swing.JButton();
        btnRs232Tx = new javax.swing.JButton();
        btnRs232Rx = new javax.swing.JButton();
        btnEthernetTx = new javax.swing.JButton();
        btnEthernetRx = new javax.swing.JButton();
        btnVideoTamper = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnHarddiskusage = new javax.swing.JButton();
        txtBal = new javax.swing.JTextField();
        btnCamera = new javax.swing.JButton();
        cmbCam = new javax.swing.JComboBox<>();
        btnTemp = new javax.swing.JButton();
        btnCanDiag1 = new javax.swing.JButton();

        txtBal1.setText("jTextField1");
        txtBal1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBal1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBal1FocusLost(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(500, 430));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(360, 490));

        txtData.setEditable(false);
        txtData.setBackground(new java.awt.Color(240, 240, 240));
        txtData.setColumns(100);
        txtData.setLineWrap(true);
        txtData.setRows(5);
        txtData.setPreferredSize(new java.awt.Dimension(370, 2000));
        jScrollPane1.setViewportView(txtData);

        btnTtyPorts.setBackground(new java.awt.Color(119, 119, 119));
        btnTtyPorts.setFont(btnTtyPorts.getFont().deriveFont(btnTtyPorts.getFont().getStyle() | java.awt.Font.BOLD, btnTtyPorts.getFont().getSize()+2));
        btnTtyPorts.setForeground(new java.awt.Color(255, 255, 255));
        btnTtyPorts.setText("GSM PORTS");
        btnTtyPorts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTtyPorts.setPreferredSize(new java.awt.Dimension(132, 40));
        btnTtyPorts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTtyPortsActionPerformed(evt);
            }
        });

        btnGetImeiMobile.setBackground(new java.awt.Color(119, 119, 119));
        btnGetImeiMobile.setFont(btnGetImeiMobile.getFont().deriveFont(btnGetImeiMobile.getFont().getStyle() | java.awt.Font.BOLD, btnGetImeiMobile.getFont().getSize()+2));
        btnGetImeiMobile.setForeground(new java.awt.Color(255, 255, 255));
        btnGetImeiMobile.setText("IMEI Number");
        btnGetImeiMobile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGetImeiMobile.setPreferredSize(new java.awt.Dimension(132, 40));
        btnGetImeiMobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetImeiMobileActionPerformed(evt);
            }
        });

        btnGsmReset.setBackground(new java.awt.Color(119, 119, 119));
        btnGsmReset.setFont(btnGsmReset.getFont().deriveFont(btnGsmReset.getFont().getStyle() | java.awt.Font.BOLD, btnGsmReset.getFont().getSize()+2));
        btnGsmReset.setForeground(new java.awt.Color(255, 255, 255));
        btnGsmReset.setText("GSM RESET");
        btnGsmReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGsmReset.setPreferredSize(new java.awt.Dimension(132, 40));
        btnGsmReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGsmResetActionPerformed(evt);
            }
        });

        btnGsmDiag.setBackground(new java.awt.Color(119, 119, 119));
        btnGsmDiag.setFont(btnGsmDiag.getFont().deriveFont(btnGsmDiag.getFont().getStyle() | java.awt.Font.BOLD, btnGsmDiag.getFont().getSize()+2));
        btnGsmDiag.setForeground(new java.awt.Color(255, 255, 255));
        btnGsmDiag.setText("GSM DIAG");
        btnGsmDiag.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGsmDiag.setPreferredSize(new java.awt.Dimension(132, 40));
        btnGsmDiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGsmDiagActionPerformed(evt);
            }
        });

        btnGpsDiag.setBackground(new java.awt.Color(119, 119, 119));
        btnGpsDiag.setFont(btnGpsDiag.getFont().deriveFont(btnGpsDiag.getFont().getStyle() | java.awt.Font.BOLD, btnGpsDiag.getFont().getSize()+2));
        btnGpsDiag.setForeground(new java.awt.Color(255, 255, 255));
        btnGpsDiag.setText("GPS DIAG");
        btnGpsDiag.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGpsDiag.setPreferredSize(new java.awt.Dimension(132, 40));
        btnGpsDiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGpsDiagActionPerformed(evt);
            }
        });

        btnCanDiag.setBackground(new java.awt.Color(119, 119, 119));
        btnCanDiag.setFont(btnCanDiag.getFont().deriveFont(btnCanDiag.getFont().getStyle() | java.awt.Font.BOLD, btnCanDiag.getFont().getSize()+2));
        btnCanDiag.setForeground(new java.awt.Color(255, 255, 255));
        btnCanDiag.setText("CAN1 DIAG");
        btnCanDiag.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCanDiag.setPreferredSize(new java.awt.Dimension(132, 40));
        btnCanDiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanDiagActionPerformed(evt);
            }
        });

        btnRs232Tx.setBackground(new java.awt.Color(119, 119, 119));
        btnRs232Tx.setFont(btnRs232Tx.getFont().deriveFont(btnRs232Tx.getFont().getStyle() | java.awt.Font.BOLD, btnRs232Tx.getFont().getSize()+2));
        btnRs232Tx.setForeground(new java.awt.Color(255, 255, 255));
        btnRs232Tx.setText("RS TX 232");
        btnRs232Tx.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRs232Tx.setPreferredSize(new java.awt.Dimension(132, 40));
        btnRs232Tx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRs232TxActionPerformed(evt);
            }
        });

        btnRs232Rx.setBackground(new java.awt.Color(119, 119, 119));
        btnRs232Rx.setFont(btnRs232Rx.getFont().deriveFont(btnRs232Rx.getFont().getStyle() | java.awt.Font.BOLD, btnRs232Rx.getFont().getSize()+2));
        btnRs232Rx.setForeground(new java.awt.Color(255, 255, 255));
        btnRs232Rx.setText("RS RX 232");
        btnRs232Rx.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRs232Rx.setPreferredSize(new java.awt.Dimension(132, 40));
        btnRs232Rx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRs232RxActionPerformed(evt);
            }
        });

        btnEthernetTx.setBackground(new java.awt.Color(119, 119, 119));
        btnEthernetTx.setFont(btnEthernetTx.getFont().deriveFont(btnEthernetTx.getFont().getStyle() | java.awt.Font.BOLD, btnEthernetTx.getFont().getSize()+2));
        btnEthernetTx.setForeground(new java.awt.Color(255, 255, 255));
        btnEthernetTx.setText("Ethernet TX");
        btnEthernetTx.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEthernetTx.setPreferredSize(new java.awt.Dimension(132, 40));
        btnEthernetTx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEthernetTxActionPerformed(evt);
            }
        });

        btnEthernetRx.setBackground(new java.awt.Color(119, 119, 119));
        btnEthernetRx.setFont(btnEthernetRx.getFont().deriveFont(btnEthernetRx.getFont().getStyle() | java.awt.Font.BOLD, btnEthernetRx.getFont().getSize()+2));
        btnEthernetRx.setForeground(new java.awt.Color(255, 255, 255));
        btnEthernetRx.setText("Ethernet RX");
        btnEthernetRx.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEthernetRx.setPreferredSize(new java.awt.Dimension(132, 40));
        btnEthernetRx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEthernetRxActionPerformed(evt);
            }
        });

        btnVideoTamper.setBackground(new java.awt.Color(119, 119, 119));
        btnVideoTamper.setFont(btnVideoTamper.getFont().deriveFont(btnVideoTamper.getFont().getStyle() | java.awt.Font.BOLD, btnVideoTamper.getFont().getSize()+2));
        btnVideoTamper.setForeground(new java.awt.Color(255, 255, 255));
        btnVideoTamper.setText("VIDEO TAMPER");
        btnVideoTamper.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnVideoTamper.setPreferredSize(new java.awt.Dimension(132, 40));
        btnVideoTamper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVideoTamperActionPerformed(evt);
            }
        });

        btnStop.setFont(btnStop.getFont().deriveFont(btnStop.getFont().getStyle() | java.awt.Font.BOLD, btnStop.getFont().getSize()+4));
        btnStop.setText("START");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnHarddiskusage.setBackground(new java.awt.Color(119, 119, 119));
        btnHarddiskusage.setFont(btnHarddiskusage.getFont().deriveFont(btnHarddiskusage.getFont().getStyle() | java.awt.Font.BOLD, btnHarddiskusage.getFont().getSize()+2));
        btnHarddiskusage.setForeground(new java.awt.Color(255, 255, 255));
        btnHarddiskusage.setText("Harddisk Usage");
        btnHarddiskusage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnHarddiskusage.setPreferredSize(new java.awt.Dimension(132, 40));
        btnHarddiskusage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHarddiskusageActionPerformed(evt);
            }
        });

        txtBal.setText("jTextField1");
        txtBal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBalFocusLost(evt);
            }
        });
        txtBal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBalActionPerformed(evt);
            }
        });

        btnCamera.setBackground(new java.awt.Color(119, 119, 119));
        btnCamera.setFont(btnCamera.getFont().deriveFont(btnCamera.getFont().getStyle() | java.awt.Font.BOLD, btnCamera.getFont().getSize()+2));
        btnCamera.setForeground(new java.awt.Color(255, 255, 255));
        btnCamera.setText("CAMERA");
        btnCamera.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCameraActionPerformed(evt);
            }
        });

        cmbCam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cam1", "Cam2", "Cam3", "Cam4", "Cam5", "Cam6", "Cam7", "Cam8" }));
        cmbCam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCamItemStateChanged(evt);
            }
        });
        cmbCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCamActionPerformed(evt);
            }
        });

        btnTemp.setBackground(new java.awt.Color(119, 119, 119));
        btnTemp.setFont(btnTemp.getFont().deriveFont(btnTemp.getFont().getStyle() | java.awt.Font.BOLD, btnTemp.getFont().getSize()+2));
        btnTemp.setForeground(new java.awt.Color(255, 255, 255));
        btnTemp.setText("Temperature");
        btnTemp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTemp.setPreferredSize(new java.awt.Dimension(132, 40));
        btnTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTempActionPerformed(evt);
            }
        });

        btnCanDiag1.setBackground(new java.awt.Color(119, 119, 119));
        btnCanDiag1.setFont(btnCanDiag1.getFont().deriveFont(btnCanDiag1.getFont().getStyle() | java.awt.Font.BOLD, btnCanDiag1.getFont().getSize()+2));
        btnCanDiag1.setForeground(new java.awt.Color(255, 255, 255));
        btnCanDiag1.setText("CAN2 DIAG");
        btnCanDiag1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCanDiag1.setPreferredSize(new java.awt.Dimension(132, 40));
        btnCanDiag1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanDiag1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTtyPorts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRs232Rx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCam, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGetImeiMobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGsmReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGsmDiag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGpsDiag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCanDiag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEthernetTx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEthernetRx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVideoTamper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHarddiskusage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRs232Tx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCanDiag1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCanDiag, btnEthernetRx, btnEthernetTx, btnGetImeiMobile, btnGpsDiag, btnGsmDiag, btnGsmReset, btnRs232Rx, btnRs232Tx, btnTtyPorts});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTtyPorts, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGetImeiMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGsmReset, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGsmDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGpsDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCanDiag, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCanDiag1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnHarddiskusage, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVideoTamper, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRs232Tx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRs232Rx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEthernetRx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEthernetTx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents
   public void startTimer() {
        //set a new Timer
        if (gpsTimer != null) {
            gpsTimer = new java.util.Timer();
            //initialize the TimerTask's job

            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            gpsTimer.scheduleAtFixedRate(task, 1000,
                    1000);
        }

    }

    public void stopTimer() {
        try {
            if (gpsTimer != null) {
                gpsTimer.cancel();
            }
            if (task != null) {
                task.cancel();
            }
            gpsTimer = null;
            task = null;

        } catch (Exception ex) {
        }
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // task to run goes here 
            try {

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        String str = clsSharedVariables.getNmeaDiagData();
                        txtData.append(str);

                    }
                });

            } catch (Exception ex) {

            }
        }
    };

    @Override
    public void removeNotify() {
        super.removeNotify();
        // Remove internal "registered things"
        stopTimer();
        setGsmDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
    }

    private void gsm_reset() {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
                    objwatDog.setgps_watchdog_val((byte) 0);
                    objwatDog = null;
                    restart_module_ports_changed(true);
                } catch (Exception ex) {

                }
                return "";
            }

            @Override
            protected void done() {
                // this method is called when the background 
                // thread finishes execution
                try {
                    txtData.setText("GSM MODULE RESETTING COMPLETED");
                    btnTtyPorts.setEnabled(true);
                    btnGsmReset.setEnabled(true);
                    btnCanDiag.setEnabled(true);
                    btnCanDiag1.setEnabled(true);
                    btnGpsDiag.setEnabled(true);
                    btnGsmDiag.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();
    }

    private void btnTtyPortsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTtyPortsActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_gsmPorts;
        txtData.setText("");
        txtData.setVisible(true);
        btnStop.setText("STOP");
        setGsmDiagEnabled(false);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);

        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        String[] ports = SerialPortList.getPortNames();

        txtData.setText("PORTS");
        for (byte i = 0; i < ports.length; i++) {
            txtData.append("Port" + i + "    " + ports[i] + "\r\n");
        }
        setGsmDiagEnabled(false);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
    }//GEN-LAST:event_btnTtyPortsActionPerformed

    private void btnGetImeiMobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetImeiMobileActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_imeiNo;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        txtData.setText("IMEI NO");
        setGsmDiagEnabled(false);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("Retrieving Information Please wait...");
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {

                    atSerialWrite("AT+QSIMSTAT?\r\n");
                    Thread.sleep(1000);
                    //  atSerialWrite(" AT+CIMI\r\n");
                    //  Thread.sleep(100);
                    if (getSimReady() == true) {
                        publish("\nSIM INSERTED");
                    } else {
                        publish("\nSIM NOT INSERTED");
                    }
                    atSerialWrite("AT+GSN\r\n");
                    Thread.sleep(1000);
                    publish("\r\nImei No " + clsSharedVariables.getImeiNo());
                    atSerialWrite("AT+QCCID\r\n");
                    Thread.sleep(1000);
                    publish(" \r\n Qccid : " + get_QCCID());
                    atSerialWrite("AT+CNUM\r\n");
                    Thread.sleep(1000);
                    publish(" \r\n Mobile No : " + get_own_mobileno());

                } catch (InterruptedException ex) {
                    //Logger.getLogger(PanGpsDiag.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "";
            }

            @Override
            protected void process(List chunks) {
                // define what the event dispatch thread 
                // will do with the intermediate results received
                // while the thread is executing
                String data = (String) chunks.get(0);
                txtData.append(data);
            }

            @Override
            protected void done() {
            }
        };
        sw1.execute();
    }//GEN-LAST:event_btnGetImeiMobileActionPerformed

    private void btnGsmResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGsmResetActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_gsmReset;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        btnTtyPorts.setEnabled(false);
        btnGsmReset.setEnabled(false);
        btnCanDiag.setEnabled(false);
        btnCanDiag1.setEnabled(false);
        btnGpsDiag.setEnabled(false);
        btnGsmDiag.setEnabled(false);
        setGsmDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("");
        txtData.setText("GSM MODULE RESETTING ....");
        if (getRebootSystemEnabled() == false) {
            if (REFRESH_WATCHDOG_TIMER_ENABLED) {
                refresh_watchdog();
            }
        }
        try {
            gsm_reset();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnGsmResetActionPerformed
    SwingWorker sw1;
    private void btnGsmDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGsmDiagActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_gsmDiag;
        txtData.setText("");
        txtData.setVisible(true);
        btnStop.setText("STOP");
        setGsmDiagEnabled(true);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);

        txtData.setText("");
        sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    String str;
                    str = "AT\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Quectel Information \n");
                    str = "ATI\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Current Configuration \n");
                    str = "AT&V\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("IMEI Number \n");
                    str = "AT+GSN\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    str = "AT+CUSD=1,\"" + clsSharedVariables.getChkNetBalCommand() + "\",15\r\n";
                    /////System.out.println(str);
                    publish(str + "\n");
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("cpin status \n");
                    str = "AT+cpin?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Sim Status \n");
                    str = "AT+QSIMSTAT?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Signal Strength \n");
                    str = "AT+CSQ\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Network Status \n");
                    str = "AT+creg?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Currently registered Operator \n");
                    str = "AT+cops?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Network Registration Status \n");
                    str = "AT+CGREG?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    str = "AT+CGREG?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Currently registered Operator \n");
                    str = "AT+cops?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Network attached  Status 1--> enabled \n");
                    str = "AT+cgatt?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Audio Volume \n");
                    str = "AT+CLVL?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Audio Mode \n");
                    str = "AT+QAUDMOD?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Audio Channel \n");
                    str = "AT+QDAI?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Audio SIDE DET \n");
                    str = "AT+QSIDET?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Service Provider \n");
                    str = "AT+CGDCONT?\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Activate Provider \n");
                    str = "AT+CGACT=1,1\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                    publish("Address Provider \n");
                    str = "AT+CGPADDR=1\r\n";
                    publish(str);
                    atSerialWrite(str);
                    Thread.sleep(2000);

                } catch (InterruptedException ex) {
                } catch (Exception ex) {

                }

                return null;
            }

            @Override
            protected void process(List chunks) {
                String data = (String) chunks.get(0);
                txtData.append(data);
            }
        };

        sw1.execute();
        startGpsDiagTimer();
    }//GEN-LAST:event_btnGsmDiagActionPerformed

    private void btnGpsDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGpsDiagActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_gpsDiag;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        setGpsDiagEnabled(true);
        setGsmDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("GPS Diagnosis");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnGpsDiagActionPerformed

    private void btnCanDiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanDiagActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_canDiag;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        setCanDiagEnabled(true);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("CAN DIAGNOSIS");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnCanDiagActionPerformed

    private void btnModuleFirmwareUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModuleFirmwareUpdateActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtData.setText("");
        txtData.setVisible(false);
        cmbCam.setVisible(false);

    }//GEN-LAST:event_btnModuleFirmwareUpdateActionPerformed

    private void btnRs232TxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRs232TxActionPerformed
        // TODO add your handling code here:

        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {
        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        btnStop.setText("STOP");
        button_pressed = btn_RsTx232;
        txtData.setVisible(true);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(true);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("RS232 TX DIAGNOSIS");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnRs232TxActionPerformed

    private void btnRs232RxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRs232RxActionPerformed
        // TODO add your handling code here:

        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {
        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        btnStop.setText("STOP");
        button_pressed = btn_RsRx232;
        txtData.setVisible(true);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(true);

        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("RS232 RX DIAGNOSIS");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnRs232RxActionPerformed

    private void btnEthernetTxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEthernetTxActionPerformed
        // TODO add your handling code here:

        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {
        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        btnStop.setText("STOP");
        button_pressed = btn_EthernetTx;
        txtData.setVisible(true);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(true);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("Ethernet TX DIAGNOSIS");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnEthernetTxActionPerformed

    private void btnEthernetRxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEthernetRxActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {
        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        btnStop.setText("STOP");
        button_pressed = btn_EthernetRx;
        txtData.setVisible(true);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);

        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(true);
        txtData.setText("Ethernet RX DIAGNOSIS");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnEthernetRxActionPerformed

    private void btnVideoTamperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVideoTamperActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        btnStop.setText("STOP");
        cmbCam.setVisible(false);
        button_pressed = btn_VideoTamper;
        txtData.setVisible(true);
        setVideoTamperDiagEnabled(true);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("VIDEO TAMPER Diagnosis\n");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnVideoTamperActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
        cmbCam.setVisible(false);
        if (btnStop.getText().equals("STOP")) {
            btnStop.setText("START");
            setVideoTamperDiagEnabled(false);
            setGpsDiagEnabled(false);
            setGsmDiagEnabled(false);
            setCanDiagEnabled(false);
            setCanDiagEnabled1(false);
            setCan1RawEnable(false);
            setCanRawEnable(false);
            setCamDiagStatusEnabled(false);
            clsSharedVariables.setRs232TxDiagEnabled(false);
            clsSharedVariables.setRs232RxDiagEnabled(false);
            clsSharedVariables.setEthernetTxDiagEnabled(false);
            clsSharedVariables.setEthernetRxDiagEnabled(false);
        } else {
            btnStop.setText("STOP");

            if (button_pressed == btn_gsmPorts || button_pressed == btn_imeiNo || button_pressed == btn_gsmReset) {
                setVideoTamperDiagEnabled(false);
                setGpsDiagEnabled(false);
                setGsmDiagEnabled(false);
                setCanDiagEnabled(false);
                setCanDiagEnabled1(false);
                setCamDiagStatusEnabled(false);
                setCan1RawEnable(false);
                clsSharedVariables.setCanRawEnable(false);
                clsSharedVariables.setRs232TxDiagEnabled(false);
                clsSharedVariables.setRs232RxDiagEnabled(false);
                clsSharedVariables.setEthernetTxDiagEnabled(false);
                clsSharedVariables.setEthernetRxDiagEnabled(false);

            } else if (button_pressed == btn_gsmDiag) {
                setGsmDiagEnabled(true);
            } else if (button_pressed == btn_gpsDiag) {
                setGpsDiagEnabled(true);
            } else if (button_pressed == btn_canDiag) {
                setCanDiagEnabled(true);
                setCanRawEnable(true);
            } else if (button_pressed == btn_RsTx232) {
                clsSharedVariables.setRs232TxDiagEnabled(true);
            } else if (button_pressed == btn_RsRx232) {
                clsSharedVariables.setRs232RxDiagEnabled(true);
            } else if (button_pressed == btn_EthernetTx) {
                clsSharedVariables.setEthernetTxDiagEnabled(true);
            } else if (button_pressed == btn_EthernetRx) {
                clsSharedVariables.setEthernetRxDiagEnabled(true);
            } else if (button_pressed == btn_VideoTamper) {
                setVideoTamperDiagEnabled(true);
            } else if (button_pressed == btn_camera) {
                // setVideoTamperDiagEnabled(true);
                setCamDiagStatusEnabled(true);
            } else if (button_pressed == btn_canDiag1) {
                setCanDiagEnabled1(true);
                setCan1RawEnable(true);
            }

        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnHarddiskusageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarddiskusageActionPerformed
        // TODO add your handling code here:
        File f = clsDefines.video_filepath;
        double size;

        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        button_pressed = btn_Harddisk;
        txtData.setVisible(true);
        cmbCam.setVisible(false);
        btnStop.setText("STOP");
        txtData.setText("Hard disk Usage");
        setGsmDiagEnabled(false);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);

        clsReadFiles objReadFiles = new clsReadFiles();

        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        button_pressed = btn_Harddisk;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        txtData.setText("STORAGE \n\n\n");
        setGsmDiagEnabled(false);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        // setVideoTamperDiagEnabled(false);
        // clsSharedVariables.setRs232TxDiagEnabled(false);
        // clsSharedVariables.setRs232RxDiagEnabled(false);
        // clsSharedVariables.setEthernetTxDiagEnabled(false);
        // clsSharedVariables.setEthernetRxDiagEnabled(false);
        DecimalFormat df = new DecimalFormat("###.##");
        try {
            f = media_filepath;
            if (clsSharedVariables.getStorageType() == clsDefines.STORAGE_HARDDISK) {

                txtData.append("\r\nHARD DISK\n");

                if (f.exists()) {
                    if (objReadFiles.check_harddisk_mount_state() == true) {
                        txtData.append("\r\n-------------------------------------------------\n");
                        txtData.append("\r\n HARD DISK Mounted\n\n");
                        txtData.append("\r\n    ACTUAL SIZE  " + df.format(f.getTotalSpace() / ONE_GB) + " GB");
                        txtData.append(" \r\n\n   FREE SPACE : " + df.format(f.getFreeSpace() / ONE_GB) + " GB");
                        txtData.append("\r\n-------------------------------------------------\n");
                    } else {
                        txtData.append("\r\n HARD DISK Disconnected \n\n");
                    }
                }
            }

            txtData.append("\r\nSD CARD\n");

        } catch (Exception ex) {
            //Logger.getLogger(PanGpsDiag.class.getName()).log(Level.SEVERE, null, ex);
        }
        f = new File(media_path);
        txtData.append("\r\n-------------------------------------------------\n");
        txtData.append("\r\n    ACTUAL SIZE  " + df.format(f.getTotalSpace() / ONE_GB) + " GB");
        txtData.append(" \r\n\n   FREE SPACE : " + df.format(f.getFreeSpace() / ONE_GB) + " GB");
        txtData.append("\r\n-------------------------------------------------\n");

        f = new File(main_route_path.getAbsolutePath());
        txtData.append("\r\nSD CARD ROUTE\n");
        txtData.append("\r\n-------------------------------------------------\n");
        txtData.append("\r\n    ACTUAL SIZE  " + df.format(f.getTotalSpace() / ONE_GB) + " GB");
        txtData.append(" \r\n\n   FREE SPACE : " + df.format(f.getFreeSpace() / ONE_GB) + " GB");
        txtData.append("\r\n-------------------------------------------------\n");

    }//GEN-LAST:event_btnHarddiskusageActionPerformed
    String prev_control_name = "";
    private void txtBalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBalFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Balance")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Balance", txtBal.getText());
            if (str != null) {
                txtBal.setText(str);
            }
            prev_control_name = "Balance";
            obj = null;
            str = null;
            txtBal.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtBalFocusGained

    private void txtBalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBalFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtBalFocusLost

    private void txtBal1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBal1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBal1FocusGained

    private void txtBal1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBal1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBal1FocusLost

    private void btnCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCameraActionPerformed
        // TODO add your handling code here:
        prev_control_name = "";
        button_pressed = btn_camera;
        txtData.setText("CAMERA");
        cmbCam.setVisible(true);
        setCamDiagStatusEnabled(true);
        setCamDiagStatusCamera((byte) (cmbCam.getSelectedIndex() + 1));
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        startGpsDiagTimer();
    }//GEN-LAST:event_btnCameraActionPerformed

    private void cmbCamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCamItemStateChanged
        // TODO add your handling code here:
        setCamDiagStatusCamera((byte) (cmbCam.getSelectedIndex() + 1));
    }//GEN-LAST:event_cmbCamItemStateChanged

    private void txtBalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBalActionPerformed

    private void cmbCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCamActionPerformed

    private void btnTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTempActionPerformed
        // TODO add your handling code here:
        String str = "$PSTMGETPAR,1201<CR><LF>\r\n";
        gpsSerialWriteString(str);

        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        button_pressed = btn_Temp;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        txtData.setText("TEMPERATURE \n\n\n");
        setGsmDiagEnabled(false);
        setGpsDiagEnabled(false);
        setCanDiagEnabled(false);
        setCanDiagEnabled1(false);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setCamDiagStatusEnabled(false);
        String fileName = "/sys/class/thermal/thermal_zone0/temp";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                float tempC = (Integer.parseInt(line) / 1000);
                float tempF = ((tempC / 5) * 9) + 32;
                txtData.append("Temp °C: " + tempC);
            }
            bufferedReader.close();
        } catch (Exception ex) {

        }


    }//GEN-LAST:event_btnTempActionPerformed

    private void btnCanDiag1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanDiag1ActionPerformed
        // TODO add your handling code here:
        try {
            if (sw1 != null) {
                sw1.cancel(true);
                sw1 = null;
            }
        } catch (Exception ex) {

        }
        txtBal.setVisible(false);
        cmbCam.setVisible(false);
        button_pressed = btn_canDiag1;
        txtData.setVisible(true);
        btnStop.setText("STOP");
        setCanDiagEnabled(false);
        setCanDiagEnabled1(true);
        setCanRawEnable(false);
        setCan1RawEnable(false);
        setGpsDiagEnabled(false);
        setGsmDiagEnabled(false);
        setCamDiagStatusEnabled(false);
        setVideoTamperDiagEnabled(false);
        clsSharedVariables.setRs232TxDiagEnabled(false);
        clsSharedVariables.setRs232RxDiagEnabled(false);
        clsSharedVariables.setEthernetTxDiagEnabled(false);
        clsSharedVariables.setEthernetRxDiagEnabled(false);
        txtData.setText("CAN DIAGNOSIS");
        startGpsDiagTimer();
    }//GEN-LAST:event_btnCanDiag1ActionPerformed
    private byte KillGprsScript() {

        byte status = 0;
        int data;
        StringBuilder sb = new StringBuilder();

        //   objReadFiles.write_log_gprs_connectivity("Kill GPRS Connectivity");
        Process kill_proc = null;
        String cmd = "sudo sh " + clsDefines.QUECTEL_KILL_PPP_SCRIPT;

        Runtime rt = null;
        DataInputStream is = null;
        try {
            rt = Runtime.getRuntime();
            kill_proc = rt.exec(cmd);

            is = new DataInputStream(kill_proc.getInputStream());

            while ((data = is.read()) != -1) {
                sb.append(data);

            }
            String final_data = sb.toString();
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        txtData.append(final_data);
                    }
                });
            } catch (Exception ex) {

            }
            //objReadFiles.write_log_gprs_connectivity("Kill End GPRS Connectivity" + sb.toString());
            Thread.sleep(1000);
            NetworkInterface nif = NetworkInterface.getByName(GPRS_NETWORKINTERFACE_PATH);

            if (nif.isUp() == false) {
                // objReadFiles.write_log_gprs_connectivity("Kill GPRS Disconnected");
            } else {
                //  objReadFiles.write_log_gprs_connectivity("Kill GPRS Connected");
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ////Logger.getLogger(clsGprsService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return status;
        } catch (IOException e) {
            return status;
            //e.printStackTrace();
        } catch (Exception ex) {
            return status;
            // ex.printStackTrace();
        } finally {

            try {
                if (kill_proc != null) {
                    rt.freeMemory();
                    rt.gc();

                    kill_proc.getInputStream().close();
                    kill_proc.getOutputStream().close();
                    kill_proc.getErrorStream().close();
                    kill_proc.destroy();
                    kill_proc = null;
                    rt = null;

                }
            } catch (IOException e) {
            } catch (Exception ex) {
            }
        }
    }

    class RestartModThread extends Thread {

        public RestartModThread() {
            super();
        }
    }

// Thread that reads process output
    static boolean gprs_connected = false;

    private synchronized static void set_gprs_connected_status(boolean status) {
        gprs_connected = status;
    }

    public synchronized static boolean get_gprs_connected_status() {
        return gprs_connected;
    }

    // Thread that reads process error output
    static Timer gps_diag_timer = null;
    static TimerTask gps_diag_timer_task = null;

    public void startGpsDiagTimer() {
        if (gps_diag_timer != null) {
            gps_diag_timer = new Timer();
            initializeGsmTimerTask();
            gps_diag_timer.schedule(gps_diag_timer_task, 0, 30000);
        }
    }

    public void initializeGsmTimerTask() {
        gps_diag_timer_task = new TimerTask() {
            @Override
            public void run() {
                try {
                    gps_diag_timer.cancel();
                    gps_diag_timer.cancel();
                    setVideoTamperDiagEnabled(false);
                    setGpsDiagEnabled(false);
                    setGsmDiagEnabled(false);
                    setCanDiagEnabled(false);
                    setCanDiagEnabled1(false);
                    setCanRawEnable(false);
                    setCan1RawEnable(false);
                    setCamDiagStatusEnabled(false);
                    clsSharedVariables.setRs232TxDiagEnabled(false);
                    clsSharedVariables.setRs232RxDiagEnabled(false);
                    clsSharedVariables.setEthernetTxDiagEnabled(false);
                    clsSharedVariables.setEthernetRxDiagEnabled(false);
                } catch (Exception ex) {
                } finally {
                    gps_diag_timer = null;
                    gps_diag_timer_task = null;
                }
            }

        };
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCamera;
    private javax.swing.JButton btnCanDiag;
    private javax.swing.JButton btnCanDiag1;
    private javax.swing.JButton btnEthernetRx;
    private javax.swing.JButton btnEthernetTx;
    private javax.swing.JButton btnGetImeiMobile;
    private javax.swing.JButton btnGpsDiag;
    private javax.swing.JButton btnGsmDiag;
    private javax.swing.JButton btnGsmReset;
    private javax.swing.JButton btnHarddiskusage;
    private javax.swing.JButton btnRs232Rx;
    private javax.swing.JButton btnRs232Tx;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnTemp;
    private javax.swing.JButton btnTtyPorts;
    private javax.swing.JButton btnVideoTamper;
    private javax.swing.JComboBox<String> cmbCam;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBal;
    private javax.swing.JTextField txtBal1;
    public static javax.swing.JTextArea txtData;
    // End of variables declaration//GEN-END:variables
}
