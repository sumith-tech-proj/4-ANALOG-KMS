package obuits;

import java.io.IOException;
import static obuits.clsSharedVariables.setCurStopNo;
import static obuits.clsSharedVariables.setSimulationEnabled;
import static obuits.clsSharedVariables.setSimulationInc;
import static obuits.clsSharedVariables.setSimulationcnt;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.digOutAudioVoiceCallPin;
import static obuits.MainFrmIts.write_gpio_pin_state;
import static obuits.clsAtSerialPort.atSerialWrite;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.Strings.AUDIO_VOICECALL_COMM_ONOFF;
import static obuits.clsDefines.Strings.VOICE_CALL_OFF_STATE;
import static obuits.clsDefines.Strings.VOICE_CALL_ON_STATE;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.media_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsSharedVariables.getImeiNo;
import static obuits.clsSharedVariables.getSimulationInc;
import static obuits.clsSharedVariables.getSimulationcnt;
import static obuits.clsSharedVariables.get_QCCID;
import static obuits.clsSharedVariables.setCanSimulationEnabled;
import static obuits.clsSharedVariables.setCanSimulationInc;
import static obuits.clsSharedVariables.setCanSimulationcnt;
import static obuits.clsSharedVariables.setCurSec;
import static obuits.clsSharedVariables.setEmergencyMapPktCame;
import static obuits.clsSharedVariables.setStopSimulationEnabled;

public class PanDiagnostics extends javax.swing.JPanel {

    private Process p;
    private ProcessBuilder builder;

    public PanDiagnostics() {
        initComponents();
        this.btnStartCanSimulation.setVisible(false);
        btnCollectGps.setVisible(false);
        btnCollectSample.setVisible(false);
        chkRouteSimulation.setVisible(false);
        this.btnStartSimulation.setVisible(false);
        chkAmbulRoute.setVisible(false);
        chkCanSimulation.setVisible(false);
        if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX || clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_SUMITH) {
            // chkAmbulRoute.setVisible(true); //true for Aminex
            chkAmbulRoute.setVisible(false);
        } else {
            chkAmbulRoute.setVisible(false);
        }
        lblMsg1.setText("");
        this.txtImeiNumber.setVisible(false);
        btnGetImei.setVisible(false);
        chkAllStopsSimulation.setVisible(false);
        panEmerDest.setVisible(false);
        panEmerSrc.setVisible(false);
        this.chkAmbulanceSource.setVisible(false);
        this.btnEmergencyStart.setVisible(false);
        this.lblTxt.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        chkRouteSimulation = new javax.swing.JCheckBox();
        btnStartSimulation = new javax.swing.JButton();
        btnTestAudio = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        lblMsg1 = new javax.swing.JLabel();
        chkCanSimulation = new javax.swing.JCheckBox();
        btnStartCanSimulation = new javax.swing.JButton();
        btnGetImei = new javax.swing.JButton();
        txtImeiNumber = new javax.swing.JTextField();
        chkAllStopsSimulation = new javax.swing.JCheckBox();
        panEmerDest = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDestLong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDestLat = new javax.swing.JTextField();
        chkAmbulRoute = new javax.swing.JCheckBox();
        panEmerSrc = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSrcLong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSrcLat = new javax.swing.JTextField();
        btnEmergencyStart = new javax.swing.JButton();
        chkAmbulanceSource = new javax.swing.JCheckBox();
        lblTxt = new javax.swing.JLabel();
        eadMp3 = new javax.swing.JRadioButton();
        radWav = new javax.swing.JRadioButton();
        btnCollectGps = new javax.swing.JButton();
        btnCollectSample = new javax.swing.JButton();
        btnTestDriverSpeaker = new javax.swing.JButton();
        txtDriverSpeaker = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(500, 430));

        chkRouteSimulation.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkRouteSimulation.setForeground(new java.awt.Color(0, 0, 102));
        chkRouteSimulation.setText("Route Simulation");
        chkRouteSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRouteSimulationActionPerformed(evt);
            }
        });

        btnStartSimulation.setBackground(new java.awt.Color(47, 49, 51));
        btnStartSimulation.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnStartSimulation.setForeground(new java.awt.Color(255, 255, 255));
        btnStartSimulation.setText("Start Route Sim");
        btnStartSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSimulationActionPerformed(evt);
            }
        });

        btnTestAudio.setBackground(new java.awt.Color(47, 49, 51));
        btnTestAudio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTestAudio.setForeground(new java.awt.Color(255, 255, 255));
        btnTestAudio.setLabel("Test Audio");
        btnTestAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestAudioActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+10));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("DIAGNOSTICS");
        jTextField1.setPreferredSize(new java.awt.Dimension(500, 32));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        lblMsg1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMsg1.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg1.setText("Playing Audio");

        chkCanSimulation.setFont(chkCanSimulation.getFont().deriveFont(chkCanSimulation.getFont().getSize()+1f));
        chkCanSimulation.setForeground(new java.awt.Color(0, 0, 102));
        chkCanSimulation.setText("CAN");
        chkCanSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCanSimulationActionPerformed(evt);
            }
        });

        btnStartCanSimulation.setBackground(new java.awt.Color(47, 49, 51));
        btnStartCanSimulation.setFont(btnStartCanSimulation.getFont().deriveFont(btnStartCanSimulation.getFont().getSize()+1f));
        btnStartCanSimulation.setForeground(new java.awt.Color(255, 255, 255));
        btnStartCanSimulation.setText("Start CAN Simulation");
        btnStartCanSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartCanSimulationActionPerformed(evt);
            }
        });

        btnGetImei.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGetImei.setForeground(new java.awt.Color(0, 102, 51));
        btnGetImei.setText("Get IMEINo");
        btnGetImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetImeiActionPerformed(evt);
            }
        });

        txtImeiNumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtImeiNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImeiNumberActionPerformed(evt);
            }
        });

        chkAllStopsSimulation.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkAllStopsSimulation.setForeground(new java.awt.Color(0, 0, 102));
        chkAllStopsSimulation.setText("Stops Simulation");
        chkAllStopsSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAllStopsSimulationActionPerformed(evt);
            }
        });

        panEmerDest.setBackground(new java.awt.Color(255, 204, 204));
        panEmerDest.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(jLabel2.getFont().deriveFont((float)16));
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Destination Longitude");

        txtDestLong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDestLong.setText("75.8957142");
        txtDestLong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDestLongFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDestLongFocusLost(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont((float)16));
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Destination Latitude");

        txtDestLat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDestLat.setText("22.75095518");
        txtDestLat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDestLatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDestLatFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panEmerDestLayout = new javax.swing.GroupLayout(panEmerDest);
        panEmerDest.setLayout(panEmerDestLayout);
        panEmerDestLayout.setHorizontalGroup(
            panEmerDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEmerDestLayout.createSequentialGroup()
                .addGroup(panEmerDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panEmerDestLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panEmerDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDestLat, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(txtDestLong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panEmerDestLayout.setVerticalGroup(
            panEmerDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEmerDestLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panEmerDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDestLat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panEmerDestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestLong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        chkAmbulRoute.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkAmbulRoute.setForeground(new java.awt.Color(0, 0, 102));
        chkAmbulRoute.setText("Ambulance EMERGENCY");
        chkAmbulRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAmbulRouteActionPerformed(evt);
            }
        });

        panEmerSrc.setBackground(new java.awt.Color(204, 255, 204));
        panEmerSrc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(jLabel5.getFont().deriveFont((float)16));
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Source Longitude");

        txtSrcLong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSrcLong.setText("75.90087879");
        txtSrcLong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSrcLongFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSrcLongFocusLost(evt);
            }
        });

        jLabel6.setFont(jLabel6.getFont().deriveFont((float)16));
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Source Latitude");

        txtSrcLat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSrcLat.setText("22.76195973");
        txtSrcLat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSrcLatFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSrcLatFocusLost(evt);
            }
        });

        javax.swing.GroupLayout panEmerSrcLayout = new javax.swing.GroupLayout(panEmerSrc);
        panEmerSrc.setLayout(panEmerSrcLayout);
        panEmerSrcLayout.setHorizontalGroup(
            panEmerSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEmerSrcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panEmerSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panEmerSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSrcLat)
                    .addComponent(txtSrcLong))
                .addContainerGap())
        );
        panEmerSrcLayout.setVerticalGroup(
            panEmerSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEmerSrcLayout.createSequentialGroup()
                .addGroup(panEmerSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(panEmerSrcLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5))
                    .addGroup(panEmerSrcLayout.createSequentialGroup()
                        .addComponent(txtSrcLat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtSrcLong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnEmergencyStart.setBackground(new java.awt.Color(47, 49, 51));
        btnEmergencyStart.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEmergencyStart.setForeground(new java.awt.Color(255, 255, 255));
        btnEmergencyStart.setText("START");
        btnEmergencyStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmergencyStartActionPerformed(evt);
            }
        });

        chkAmbulanceSource.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkAmbulanceSource.setForeground(new java.awt.Color(0, 0, 102));
        chkAmbulanceSource.setText("SOURCE From GPS");
        chkAmbulanceSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAmbulanceSourceActionPerformed(evt);
            }
        });

        lblTxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTxt.setForeground(new java.awt.Color(153, 0, 0));
        lblTxt.setText("Enter Data");

        buttonGroup1.add(eadMp3);
        eadMp3.setFont(eadMp3.getFont().deriveFont(eadMp3.getFont().getStyle() | java.awt.Font.BOLD, eadMp3.getFont().getSize()+3));
        eadMp3.setText("MP3");

        buttonGroup1.add(radWav);
        radWav.setFont(radWav.getFont().deriveFont(radWav.getFont().getStyle() | java.awt.Font.BOLD, radWav.getFont().getSize()+3));
        radWav.setSelected(true);
        radWav.setText("wav");

        btnCollectGps.setBackground(new java.awt.Color(47, 49, 51));
        btnCollectGps.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCollectGps.setForeground(new java.awt.Color(255, 255, 255));
        btnCollectGps.setText("collect gps");
        btnCollectGps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollectGpsActionPerformed(evt);
            }
        });

        btnCollectSample.setBackground(new java.awt.Color(47, 49, 51));
        btnCollectSample.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCollectSample.setForeground(new java.awt.Color(255, 255, 255));
        btnCollectSample.setText("GPS Sample");
        btnCollectSample.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollectSampleActionPerformed(evt);
            }
        });

        btnTestDriverSpeaker.setBackground(new java.awt.Color(47, 49, 51));
        btnTestDriverSpeaker.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTestDriverSpeaker.setForeground(new java.awt.Color(255, 255, 255));
        btnTestDriverSpeaker.setText("Driver Speaker");
        btnTestDriverSpeaker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestDriverSpeakerActionPerformed(evt);
            }
        });

        txtDriverSpeaker.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtDriverSpeaker.setText("welcome");
        txtDriverSpeaker.setPreferredSize(new java.awt.Dimension(250, 28));
        txtDriverSpeaker.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDriverSpeakerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDriverSpeakerFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radWav)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eadMp3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTestAudio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDriverSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTestDriverSpeaker)
                        .addGap(18, 18, 18)
                        .addComponent(chkCanSimulation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStartCanSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panEmerDest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panEmerSrc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEmergencyStart)
                            .addComponent(btnCollectSample)))
                    .addComponent(lblTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(chkRouteSimulation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStartSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCollectGps)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAmbulRoute)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGetImei, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImeiNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAmbulanceSource)
                    .addComponent(chkAllStopsSimulation))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRouteSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCollectGps, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(radWav)
                    .addComponent(eadMp3)
                    .addComponent(btnTestAudio)
                    .addComponent(lblMsg1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDriverSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTestDriverSpeaker)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnStartCanSimulation)
                        .addComponent(chkCanSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkAmbulRoute)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGetImei, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtImeiNumber))
                            .addComponent(chkAllStopsSimulation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkAmbulanceSource)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEmergencyStart, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnCollectSample, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panEmerSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panEmerDest, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addComponent(lblTxt)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkRouteSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRouteSimulationActionPerformed
        // TODO add your handling code here:
        if (!chkRouteSimulation.isSelected()) {
            setSimulationEnabled(false);
            clsSharedVariables.gps_simul_data = null;
            setSimulationInc(0);
        }
    }//GEN-LAST:event_chkRouteSimulationActionPerformed

    private void btnTestAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestAudioActionPerformed
        // TODO add your handling code here:
        try {
            lblTxt.setText("");
            File file;
            File f;
            if (radWav.isSelected()) {
                file = new File(main_route_filepath, "audio.wav");
                f = new File(route_filepath, "audio.wav");
                if (file.exists()) {
                    clsSharedVariables.objAudQue.addData("/audio.wav");
                    lblTxt.setText("Playing audio.wav");
                    Thread.sleep(2000);

                } else if ((new File(main_route_filepath, "audio.WAV")).exists()) {

                    clsSharedVariables.objAudQue.addData("/audio.WAV");
                    lblTxt.setText("Playing audio.WAV");

                    Thread.sleep(2000);

                } else if ((new File(route_filepath, "audio.wav")).exists()) {
                    clsSharedVariables.objAudQue.addData("/audio.wav");
                    lblTxt.setText("Playing audio.wav");

                    Thread.sleep(2000);

                } else if ((new File(route_filepath, "audio.WAV")).exists()) {
                    clsSharedVariables.objAudQue.addData("/audio.WAV");
                    lblTxt.setText("Playing audio.WAV");
                    Thread.sleep(2000);
                }

            } else if ((file = new File(main_route_filepath, "audio.mp3")).exists()) {
                clsSharedVariables.objAudQue.addData("/audio.mp3");
                lblTxt.setText("Playing audio.mp3 file...");

                Thread.sleep(2000);

            } else if ((f = new File(route_filepath, "audio.mp3")).exists()) {
                clsSharedVariables.objAudQue.addData("/audio.mp3");
                lblTxt.setText("Playing audio.mp3 file...");

                Thread.sleep(2000);

            } else {
                lblTxt.setText("audio File Dosn't exist");

            }
            lblTxt.setText(" ");
            file = null;
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnTestAudioActionPerformed

    private void btnStartSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSimulationActionPerformed
        // TODO add your handling code here:
        if (chkRouteSimulation.isSelected() == true) {
            setCurSec(Calendar.getInstance().getTimeInMillis());
            setSimulationEnabled(true);
            clsReadFiles objReadFiles = new clsReadFiles();

            if (objReadFiles.read_gps_data_from_file() == true) {
                if (getSimulationcnt() > 0) {
                    setSimulationInc(0);
                    setCurStopNo((byte) 0);
                    lblTxt.setText("Simulation Started");

                } else {
                    lblTxt.setText("No data in File");

                }
            } else {
                lblTxt.setText("File Not Found");

            }
        } else {
            setSimulationEnabled(false);
            setSimulationInc(0);
            setCurStopNo((byte) 0);

            lblTxt.setText("");
            setSimulationcnt(0);
        }
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    private void chkCanSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCanSimulationActionPerformed

    }//GEN-LAST:event_chkCanSimulationActionPerformed

    private void btnStartCanSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartCanSimulationActionPerformed

        if (chkCanSimulation.isSelected() == true) {
            setCanSimulationEnabled(true);
            clsReadFiles objReadFiles = new clsReadFiles();
            clsSharedVariables.can_simul_data = new String[5500];

            File file = new File(media_filepath, "candata.txt");
            if (file.exists()) {
                lblTxt.setText("Can Simulation Started");

                objReadFiles.read_can_data_from_file();
                setCanSimulationcnt(0);
            } else {
                lblTxt.setText("File Doesn't Exist");

            }
            file = null;

        } else {
            setCanSimulationEnabled(false);
            setCanSimulationInc(0);
            lblTxt.setText("");
            setCanSimulationcnt(0);
        }
    }//GEN-LAST:event_btnStartCanSimulationActionPerformed

    private void btnGetImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetImeiActionPerformed
        try {
            // TODO add your handling code here:
            txtImeiNumber.setText(getImeiNo());
            atSerialWrite("AT+GSN\r\n");
            Thread.sleep(100);

            atSerialWrite("AT+QCCID\r\n");

            txtImeiNumber.setText("Imei No " + getImeiNo() + "  Qccid : " + get_QCCID());
        } catch (InterruptedException ex) {
        }
    }//GEN-LAST:event_btnGetImeiActionPerformed

    private void txtImeiNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImeiNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImeiNumberActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void chkAllStopsSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAllStopsSimulationActionPerformed
        // TODO add your handling code here:
        if (chkAllStopsSimulation.isSelected()) {
            setStopSimulationEnabled(true);
        } else {
            setStopSimulationEnabled(false);
        }
    }//GEN-LAST:event_chkAllStopsSimulationActionPerformed

    private void chkAmbulRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAmbulRouteActionPerformed
        // TODO add your handling code here:
        lblTxt.setText("");
        if (chkAmbulRoute.isSelected()) {
            panEmerDest.setVisible(true);
            panEmerSrc.setVisible(true);
            this.chkAmbulanceSource.setVisible(true);
            this.btnEmergencyStart.setVisible(true);
            setEmergencyMapPktCame(true);
        } else {
            panEmerDest.setVisible(false);
            panEmerSrc.setVisible(false);
            this.chkAmbulanceSource.setVisible(false);
            this.btnEmergencyStart.setVisible(false);
        }
    }//GEN-LAST:event_chkAmbulRouteActionPerformed

    private void chkAmbulanceSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAmbulanceSourceActionPerformed
        // TODO add your handling code here:
        lblTxt.setText("");
        if (chkAmbulanceSource.isSelected()) {
            txtSrcLat.setEnabled(false);
            panEmerSrc.setEnabled(false);
            txtSrcLong.setEnabled(false);
            this.txtSrcLat.setText(String.valueOf(clsSharedVariables.getCurLatitude()));
            this.txtSrcLong.setText(String.valueOf(clsSharedVariables.getCurLongitude()));

        } else {
            panEmerSrc.setEnabled(true);
            txtSrcLat.setEnabled(true);
            txtSrcLong.setEnabled(true);
            this.txtSrcLat.setText("");
            this.txtSrcLong.setText("");
        }
    }//GEN-LAST:event_chkAmbulanceSourceActionPerformed

    private void btnEmergencyStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmergencyStartActionPerformed
        // TODO add your handling code here:
        lblTxt.setText("");
        if (chkAmbulRoute.isSelected()) {
            double src_lat;
            double src_long;
            double des_lat;
            double des_long;
            clsReadFiles obj = new clsReadFiles();

            try {
                src_lat = Double.parseDouble(this.txtSrcLat.getText()); //17.430167
            } catch (Exception ex) {
                lblTxt.setText("Enter Proper Source Latitude");

                return;
            }
            try {
                src_long = Double.parseDouble(this.txtSrcLong.getText()); // 78.541717
            } catch (Exception ex) {
                lblTxt.setText("Enter Proper Source Longitude");

                return;
            }
            try {
                des_lat = Double.parseDouble(this.txtDestLat.getText());//17.400108
            } catch (Exception ex) {
                lblTxt.setText("Enter Proper Destination Latitude");

                return;
            }
            try {
                des_long = Double.parseDouble(this.txtDestLong.getText());//78.558280
            } catch (Exception ex) {
                lblTxt.setText("Enter Proper Destination Longitude");

                return;
            }
            if (src_lat == 0.0) {
                lblTxt.setText("Enter Proper Source Latitude");

                return;
            }
            if (src_long == 0.0) {
                lblTxt.setText("Enter Proper Source Longitude");

                return;
            }
            clsSharedVariables.no_maps_latlong_points = 0;
            if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                if (obj.read_destination_map_data_google(src_lat, src_long, des_lat, des_long) == false) {
                    lblTxt.setText("Please Check Net Connection Not fetched location Points");
                    return;
                }
            } else {
                obj.read_destination_map_data(src_lat, src_long, des_lat, des_long);
            }

            clsSharedVariables.setEmergencySrcLat(src_lat);
            clsSharedVariables.setEmergencySrcLong(src_long);
            clsSharedVariables.setEmergencyDesLat(des_lat);
            clsSharedVariables.setEmergencyDesLong(des_long);
            clsSharedVariables.setNoEmergencyPtsFromGprs(2);
            obj.write_emergency_map_data();
            obj = null;
            // obj.read_emergency_map_data();
            lblTxt.setText("Map Started with points " + clsSharedVariables.no_maps_latlong_points);
            setEmergencyMapPktCame(true);
        } else {
            panEmerDest.setVisible(false);
        }
    }//GEN-LAST:event_btnEmergencyStartActionPerformed
    String prev_control_name = "";
    private void txtSrcLatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSrcLatFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("srclat")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("srclat", txtSrcLat.getText());
            if (str != null) {
                txtSrcLat.setText(str);
            }
            prev_control_name = "srclat";
            obj = null;
            str = null;
            txtSrcLat.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtSrcLatFocusGained

    private void txtSrcLongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSrcLongFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("srclong")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("srclong", txtSrcLong.getText());
            if (str != null) {
                txtSrcLong.setText(str);
            }
            prev_control_name = "srclong";
            obj = null;
            str = null;
            txtSrcLong.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtSrcLongFocusGained

    private void txtDestLatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDestLatFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("destlat")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("destlat", txtDestLat.getText());
            if (str != null) {
                txtDestLat.setText(str);
            }
            prev_control_name = "destlat";
            obj = null;
            str = null;
            txtDestLat.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDestLatFocusGained

    private void txtDestLongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDestLongFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("destlong")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("destlong", txtDestLong.getText());
            if (str != null) {
                txtDestLong.setText(str);
            }
            prev_control_name = "destlong";
            obj = null;
            str = null;
            txtDestLong.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDestLongFocusGained

    private void btnCollectGpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollectGpsActionPerformed

        clsDefines.SIM_LOG_COLLECT = false;
        int result = JOptionPane.showConfirmDialog(null, "Are you Sure to Delete Simulation File?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ((result == JOptionPane.YES_OPTION)) {
            File file = new File(route_filepath, "simulGprmc.txt");
            file.delete();
            lblMsg1.setText("simulGprmc Deleted");

            file = null;
        }
        clsDefines.SIM_LOG_COLLECT = true;
        lblMsg1.setText("Collecting Data Started ");
    }//GEN-LAST:event_btnCollectGpsActionPerformed

    private void btnCollectSampleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollectSampleActionPerformed
        // TODO add your handling code here:
        clsReadFiles obj = new clsReadFiles();
        obj.write_gps_sample_collection_file();
        obj = null;
        lblTxt.setText(getSimulationInc() + " stop : " + clsSharedVariables.getCurStopNo() + " lat: " + clsSharedVariables.getCurLatitude() + "   lon: " + String.valueOf(clsSharedVariables.getCurLongitude()));
    }//GEN-LAST:event_btnCollectSampleActionPerformed

    private void btnTestDriverSpeakerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestDriverSpeakerActionPerformed

        try {
            final String data = txtDriverSpeaker.getText();
            SwingWorker sw1 = new SwingWorker() {
                @Override
                protected String doInBackground() throws Exception {
                    try {
                        // TODO add your handling code here:
                        write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);

                        String str = "AT+QTTS=2,\"" + data + "\" \r\n";
                        atSerialWrite(str);
                        Thread.sleep(3000);
                        write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(clsAtSerialPort.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {

                    }

                    return null;
                }

                @Override
                protected void process(List chunks) {
                    String data1 = (String) chunks.get(0);
                    lblMsg1.setText("Playing Audio Driver Speaker");

                }
            };

            sw1.execute();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_btnTestDriverSpeakerActionPerformed

    private void txtDriverSpeakerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDriverSpeakerFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("driverSpeak")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("driverSpeak", txtDriverSpeaker.getText());
            if (str != null) {
                txtDriverSpeaker.setText(str);
            }
            prev_control_name = "driverSpeak";
            obj = null;
            str = null;
            txtDriverSpeaker.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtDriverSpeakerFocusGained

    private void txtDriverSpeakerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDriverSpeakerFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDriverSpeakerFocusLost

    private void txtSrcLatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSrcLatFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtSrcLatFocusLost

    private void txtSrcLongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSrcLongFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtSrcLongFocusLost

    private void txtDestLatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDestLatFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDestLatFocusLost

    private void txtDestLongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDestLongFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtDestLongFocusLost
    private boolean runCmd(String cmd) {
        Process process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(new String[]{"bash", "-c", cmd});
            process.waitFor(); //5, TimeUnit.SECONDS);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCollectGps;
    private javax.swing.JButton btnCollectSample;
    private javax.swing.JButton btnEmergencyStart;
    private javax.swing.JButton btnGetImei;
    private javax.swing.JButton btnStartCanSimulation;
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JButton btnTestAudio;
    private javax.swing.JButton btnTestDriverSpeaker;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkAllStopsSimulation;
    private javax.swing.JCheckBox chkAmbulRoute;
    private javax.swing.JCheckBox chkAmbulanceSource;
    private javax.swing.JCheckBox chkCanSimulation;
    private javax.swing.JCheckBox chkRouteSimulation;
    private javax.swing.JRadioButton eadMp3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblMsg1;
    private javax.swing.JLabel lblTxt;
    private javax.swing.JPanel panEmerDest;
    private javax.swing.JPanel panEmerSrc;
    private javax.swing.JRadioButton radWav;
    private javax.swing.JTextField txtDestLat;
    private javax.swing.JTextField txtDestLong;
    private javax.swing.JTextField txtDriverSpeaker;
    private javax.swing.JTextField txtImeiNumber;
    private javax.swing.JTextField txtSrcLat;
    private javax.swing.JTextField txtSrcLong;
    // End of variables declaration//GEN-END:variables
}
