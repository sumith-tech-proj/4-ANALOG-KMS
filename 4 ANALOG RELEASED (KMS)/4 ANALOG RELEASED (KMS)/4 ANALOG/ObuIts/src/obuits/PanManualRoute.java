package obuits;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDisplayBrdSerialPort.objdisQue;
import static obuits.clsSharedVariables.getArtIntEnable;
import static obuits.clsSharedVariables.getArticulatedBus;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.getNoStopsRoute;
import static obuits.clsSharedVariables.get_special_msg_status;
import static obuits.clsSharedVariables.gps_data;

public class PanManualRoute extends javax.swing.JPanel {

    ArrayList<String> items = new ArrayList<>();
    int position = 0;
    JPanel panMainPane;
    JPanel panCenterMainPane;
    Timer timer;
    TimerTask timerTask;
    static short curstop_no;

    public PanManualRoute(JPanel panMainPan, JPanel panCenterMainPan) {
        initComponents();
        panMainPane = panMainPan;
        panCenterMainPane = panCenterMainPan;
        lblMsg.setText("");
        int i;
        int no_stops = getNoStopsRoute();

        if (no_stops > 0) {
            lblRouteNo.setText("Route No : " + getCurSchRouteNo(getCurTripNo()));
            for (i = 0; i < no_stops; i++) {
                items.add(gps_data[i].stop_name);
            }

            DefaultListModel<String> model = new DefaultListModel<>();
            items.stream().forEach((s) -> {
                model.addElement(s);
            });
            lstStops.setModel(model);
            lstStops.getSelectionModel().setLeadSelectionIndex(0);
            lstStops.setVisible(true);

            lblStopName.setText("Stop : " + items.get(0));
        } else {
            lblRouteNo.setText("No Stops In this Route");
            lblStopName.setText("");
            DefaultListModel model = new DefaultListModel();
            model.clear();
            lstStops.setModel(model);
            btnCurStop.setEnabled(false);
        }

        this.lstStops.setCellRenderer(new MyCellRenderer());
        try {
            if (clsSharedVariables.getCurTripNo() <= no_stops) {
                this.lstStops.setSelectedIndex(clsSharedVariables.getCurTripNo());
                this.lstStops.ensureIndexIsVisible(clsSharedVariables.getCurTripNo());
            }
        } catch (Exception ex) {

        }
        curstop_no = clsSharedVariables.getCurStopNo();
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        startTimer();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
    }

    class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            int position = index;

            if (position < clsSharedVariables.getCurStopNo()) {
                c.setBackground(Color.RED);
            } else if (position == clsSharedVariables.getCurStopNo()) {
                c.setBackground(Color.GREEN);

            }
            return c;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtManStopSel = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnCurStop = new javax.swing.JButton();
        btnNextStop = new javax.swing.JButton();
        btnAppStop = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstStops = new javax.swing.JList();
        lblMsg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRouteNo = new javax.swing.JLabel();
        lblStopName = new javax.swing.JLabel();
        btnRouteBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(23, 29, 32));
        setForeground(new java.awt.Color(0, 0, 102));
        setPreferredSize(new java.awt.Dimension(700, 500));

        txtManStopSel.setEditable(false);
        txtManStopSel.setBackground(new java.awt.Color(0, 0, 102));
        txtManStopSel.setFont(txtManStopSel.getFont().deriveFont(txtManStopSel.getFont().getStyle() | java.awt.Font.BOLD, txtManStopSel.getFont().getSize()+10));
        txtManStopSel.setForeground(new java.awt.Color(255, 255, 255));
        txtManStopSel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtManStopSel.setText("Manual Stop Selection");
        txtManStopSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManStopSelActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(31, 25, 81));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        btnCurStop.setBackground(new java.awt.Color(85, 85, 85));
        btnCurStop.setFont(btnCurStop.getFont().deriveFont(btnCurStop.getFont().getStyle() | java.awt.Font.BOLD, btnCurStop.getFont().getSize()+9));
        btnCurStop.setForeground(new java.awt.Color(255, 255, 255));
        btnCurStop.setText("Current Stop");
        btnCurStop.setActionCommand("Current");
        btnCurStop.setAlignmentY(0.0F);
        btnCurStop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCurStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCurStop.setIconTextGap(0);
        btnCurStop.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCurStop.setPreferredSize(new java.awt.Dimension(100, 35));
        btnCurStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurStopActionPerformed(evt);
            }
        });

        btnNextStop.setBackground(new java.awt.Color(85, 85, 85));
        btnNextStop.setFont(btnNextStop.getFont().deriveFont(btnNextStop.getFont().getStyle() | java.awt.Font.BOLD, btnNextStop.getFont().getSize()+9));
        btnNextStop.setForeground(new java.awt.Color(255, 255, 255));
        btnNextStop.setText("Next Stop");
        btnNextStop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNextStop.setPreferredSize(new java.awt.Dimension(100, 35));
        btnNextStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextStopActionPerformed(evt);
            }
        });

        btnAppStop.setBackground(new java.awt.Color(85, 85, 85));
        btnAppStop.setFont(btnAppStop.getFont().deriveFont(btnAppStop.getFont().getStyle() | java.awt.Font.BOLD, btnAppStop.getFont().getSize()+9));
        btnAppStop.setForeground(new java.awt.Color(255, 255, 255));
        btnAppStop.setText("Approaching Stop");
        btnAppStop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAppStop.setPreferredSize(new java.awt.Dimension(100, 35));
        btnAppStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCurStop, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAppStop, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCurStop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAppStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(115, 143, 171));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(690, 412));

        lstStops.setBackground(new java.awt.Color(240, 240, 240));
        lstStops.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lstStops.setFont(lstStops.getFont().deriveFont(lstStops.getFont().getStyle() | java.awt.Font.BOLD, lstStops.getFont().getSize()+11));
        lstStops.setForeground(new java.awt.Color(0, 0, 102));
        lstStops.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstStops.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstStops.setFixedCellHeight(50);
        lstStops.setFixedCellWidth(200);
        lstStops.setPreferredSize(new java.awt.Dimension(510, 5000));
        lstStops.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                lstStopsInputMethodTextChanged(evt);
            }
        });
        lstStops.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstStopsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstStops);

        lblMsg.setBackground(new java.awt.Color(115, 143, 171));
        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsg.setText("Data Send");

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        lblRouteNo.setBackground(new java.awt.Color(0, 0, 102));
        lblRouteNo.setFont(lblRouteNo.getFont().deriveFont(lblRouteNo.getFont().getStyle() | java.awt.Font.BOLD, lblRouteNo.getFont().getSize()+8));
        lblRouteNo.setForeground(new java.awt.Color(255, 255, 255));
        lblRouteNo.setText("Route No");

        lblStopName.setBackground(new java.awt.Color(0, 0, 102));
        lblStopName.setFont(lblStopName.getFont().deriveFont(lblStopName.getFont().getStyle() | java.awt.Font.BOLD, lblStopName.getFont().getSize()+4));
        lblStopName.setForeground(new java.awt.Color(255, 255, 255));
        lblStopName.setText("Stop Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRouteNo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStopName, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblRouteNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStopName, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        btnRouteBack.setBackground(new java.awt.Color(85, 85, 85));
        btnRouteBack.setFont(btnRouteBack.getFont().deriveFont(btnRouteBack.getFont().getStyle() | java.awt.Font.BOLD, btnRouteBack.getFont().getSize()+7));
        btnRouteBack.setForeground(new java.awt.Color(255, 255, 255));
        btnRouteBack.setText("ROUTE");
        btnRouteBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRouteBack.setPreferredSize(new java.awt.Dimension(100, 35));
        btnRouteBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtManStopSel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRouteBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtManStopSel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRouteBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg))
                .addContainerGap(75, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtManStopSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManStopSelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManStopSelActionPerformed

     private void btnCurStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurStopActionPerformed
         // TODO add your handling code here:
         File file;
         File f;
         int i;

         try {
             lblMsg.setText("");
             String eng_wav_file;
             String reg1_wav_file;
             String reg2_wav_file;

             if (clsSharedVariables.getIpAddr1Enable()) {
                 cls16833Protocols obj16833Pkts = new cls16833Protocols();
                 obj16833Pkts.cur_stop_pkt(gps_data[position].stop_name);
                 obj16833Pkts = null;
             }
             if (clsSharedVariables.getIpAddr4Enable()) {
                 gpsDriving objDriving = new gpsDriving();
                 objDriving.cur_stop_pkt(gps_data[position].stop_name);
                 objDriving = null;
             }
             eng_wav_file = gps_data[position].cur_eng_file;
             reg1_wav_file = gps_data[position].cur_reg1_file;
             reg2_wav_file = gps_data[position].cur_reg2_file;

             clsSharedVariables.objAudQue.removeAllMessagesQueue();
             file = new File(route_filepath, eng_wav_file);
             f = new File(main_route_filepath, eng_wav_file);

             if (f.exists()) {
                 clsSharedVariables.objAudQue.addData(eng_wav_file);
                 lblMsg.setText("Playing English audio");

             } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

                 clsSharedVariables.objAudQue.addData(eng_wav_file);
                 lblMsg.setText("Playing English audio");
             } else {
                 lblMsg.setText("English audio File Dosn't exist");
             }

             //reg1 audio file
             file = new File(route_filepath, reg1_wav_file);

             f = new File(main_route_filepath, reg1_wav_file);

             if (f.exists()) {
                 clsSharedVariables.objAudQue.addData(reg1_wav_file);
             } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                 clsSharedVariables.objAudQue.addData(reg1_wav_file);
             } else {
                 lblMsg.setText("Regional1 File Dosn't exist");
             }
             file = new File(route_filepath, reg2_wav_file);
             f = new File(main_route_filepath, reg2_wav_file);
             if (f.exists()) {
                 clsSharedVariables.objAudQue.addData(reg2_wav_file);
             } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                 clsSharedVariables.objAudQue.addData(reg2_wav_file);
             } else {
                 lblMsg.setText("Regional2 File Dosn't exist");

             }
             file = null;
             f = null;
         } catch (Exception ex) {
         }
         objdisQue.removeAllMessagesQueue();
         if (getIntEnable()) {
             file = new File(route_filepath, gps_data[position].cur_db_file);
             f = new File(main_route_filepath, gps_data[position].cur_db_file);
             if (f.exists()) {
                 objdisQue.addData(gps_data[position].cur_db_file + "," + 0);
                 lblMsg.setText("Data Sent to Display Board");

             } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                 objdisQue.addData(gps_data[position].cur_db_file + "," + 0);
                 lblMsg.setText("Data Sent to Display Board");

             } else {
                 lblMsg.setText("  File Dosn't exist");
             }
         }
         if (getArtIntEnable() && getArticulatedBus()) {
             file = new File(route_filepath, gps_data[position].cur_db_file);
             f = new File(main_route_filepath, gps_data[position].cur_db_file);
             if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                 objdisQue.addData(gps_data[position].cur_db_file + "articulated" + "," + 0);
                 lblMsg.setText("Data Sent to Display Board");

             } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                 objdisQue.addData(gps_data[position].cur_db_file + "articulated" + "," + 0);
                 lblMsg.setText("Data Sent to Display Board");

             } else {
                 lblMsg.setText("  File Dosn't exist");

             }
         }
         clsSharedVariables.setCurStopNo((byte) position);
         for (i = 0; i < position; i++) {
             gps_data[i].stop_identify_status = true;
         }
         for (; i < getNoStopsRoute(); i++) {
             gps_data[i].stop_identify_status = false;
         }
         this.lstStops.updateUI();
         file = null;
    }//GEN-LAST:event_btnCurStopActionPerformed

    private void btnRouteBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteBackActionPerformed
        // TODO add your handling code here:
        PanelRoute objPanel = new PanelRoute(panMainPane, panCenterMainPane);
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
    }//GEN-LAST:event_btnRouteBackActionPerformed

    private void btnNextStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextStopActionPerformed
        // TODO add your handling code here:
        File file;
        File f;
        try {
            lblMsg.setText("");
            String eng_wav_file;
            String reg1_wav_file;
            String reg2_wav_file;
            if (clsSharedVariables.getIpAddr1Enable()) {
                cls16833Protocols obj16833Pkts = new cls16833Protocols();
                obj16833Pkts.bus_crossed_stop_pkt(gps_data[position].stop_name);
                obj16833Pkts = null;
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                gpsDriving objDriving = new gpsDriving();
                objDriving.bus_crossed_stop_pkt(gps_data[position].stop_name);
                objDriving = null;
            }
            eng_wav_file = gps_data[position].next_eng_file;
            reg1_wav_file = gps_data[position].next_reg1_file;
            reg2_wav_file = gps_data[position].next_reg2_file;

            clsSharedVariables.objAudQue.removeAllMessagesQueue();
            file = new File(route_filepath, eng_wav_file);

            f = new File(main_route_filepath, eng_wav_file);

            if (f.exists()) {
                clsSharedVariables.objAudQue.addData(eng_wav_file);
                lblMsg.setText("Playing English audio");
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                clsSharedVariables.objAudQue.addData(eng_wav_file);
                lblMsg.setText("Playing English audio");
            } else {
                lblMsg.setText("English audio File Dosn't exist");
            }
            //reg1 audio file
            f = new File(main_route_filepath, reg1_wav_file);
            file = new File(route_filepath, reg1_wav_file);
            if (f.exists()) {
                clsSharedVariables.objAudQue.addData(reg1_wav_file);
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                clsSharedVariables.objAudQue.addData(reg1_wav_file);
            } else {
                lblMsg.setText("Regional1 File Dosn't exist");
            }
            //reg2 audio file
            file = new File(route_filepath, reg2_wav_file);
            f = new File(main_route_filepath, reg2_wav_file);
            if (f.exists()) {
                clsSharedVariables.objAudQue.addData(reg2_wav_file);
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                clsSharedVariables.objAudQue.addData(reg2_wav_file);
            } else {
                lblMsg.setText("Regional2 File Dosn't exist");
            }
            if (position > 0) {
                int i = 0;
                clsSharedVariables.setCurStopNo((byte) (position));
                for (i = 0; i < position; i++) {
                    gps_data[i].stop_identify_status = true;
                }
                for (; i < getNoStopsRoute(); i++) {
                    gps_data[i].stop_identify_status = false;
                }
                this.lstStops.updateUI();
            }
            file = null;
            f = null;
        } catch (Exception ex) {
        }
        objdisQue.removeAllMessagesQueue();
        if (getIntEnable()) {
            file = new File(route_filepath, gps_data[position].next_db_file);
            f = new File(main_route_filepath, gps_data[position].next_db_file);
            if (f.exists()) {
                objdisQue.addData(gps_data[position].next_db_file + "," + 0);
                lblMsg.setText("Data Sent to Display Board");

            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addData(gps_data[position].next_db_file + "," + 0);
                lblMsg.setText("Data Sent to Display Board");
            } else {
                lblMsg.setText("  File Dosn't exist");
            }
        }
        if (getArtIntEnable() && getArticulatedBus()) {

            file = new File(route_filepath, gps_data[position].next_db_file);
            f = new File(main_route_filepath, gps_data[position].next_db_file);
            if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                objdisQue.addData(gps_data[position].next_db_file + "articulated" + "," + 0);
                lblMsg.setText("Data Sent to Display Board");
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addData(gps_data[position].next_db_file + "articulated" + "," + 0);
                lblMsg.setText("Data Sent to Display Board");
            } else {
                lblMsg.setText("  File Dosn't exist");

            }

        }
        file = null;
    }//GEN-LAST:event_btnNextStopActionPerformed

    private void btnAppStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppStopActionPerformed
        // TODO add your handling code here:
        File file;
        File f;
        try {
            lblMsg.setText("");
            String eng_wav_file;
            String reg1_wav_file;
            String reg2_wav_file;
            if (clsSharedVariables.getIpAddr1Enable()) {
                cls16833Protocols obj16833Pkts = new cls16833Protocols();
                obj16833Pkts.app_stop_pkt(gps_data[position].stop_name);
                obj16833Pkts = null;
            }
            if (clsSharedVariables.getIpAddr4Enable()) {
                gpsDriving objDriving = new gpsDriving();
                objDriving.app_stop_pkt(gps_data[position].stop_name);
                objDriving = null;
            }
            eng_wav_file = gps_data[position].arr_eng_file;
            reg1_wav_file = gps_data[position].arr_reg1_file;
            reg2_wav_file = gps_data[position].arr_reg2_file;
            clsSharedVariables.objAudQue.removeAllMessagesQueue();
            file = new File(route_filepath, eng_wav_file);
            f = new File(main_route_filepath, eng_wav_file);
            if (f.exists()) {
                clsSharedVariables.objAudQue.addData(eng_wav_file);
                lblMsg.setText("Playing English audio");

            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {

                clsSharedVariables.objAudQue.addData(eng_wav_file);
                lblMsg.setText("Playing English audio");
            } else {
                lblMsg.setText("English audio File Dosn't exist");
            }
            //reg1 audio file
            file = new File(route_filepath, reg1_wav_file);
            if (f.exists()) {
                clsSharedVariables.objAudQue.addData(reg1_wav_file);
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                clsSharedVariables.objAudQue.addData(reg1_wav_file);
            } else {
                lblMsg.setText("Regional1 File Dosn't exist");
            }
            //reg2 audio file
            file = new File(route_filepath, reg2_wav_file);

            f = new File(main_route_filepath, reg2_wav_file);
            if (f.exists()) {
                clsSharedVariables.objAudQue.addData(reg2_wav_file);
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                clsSharedVariables.objAudQue.addData(reg2_wav_file);
            } else {
                lblMsg.setText("Regional2 File Dosn't exist");

            }
            file = null;
            f = null;
        } catch (Exception ex) {

        }
        objdisQue.removeAllMessagesQueue();
        if (getIntEnable()) {
            file = new File(route_filepath, gps_data[position].arr_db_file);
            f = new File(main_route_filepath, gps_data[position].arr_db_file);
            if (f.exists()) {
                objdisQue.addData(gps_data[position].arr_db_file + "," + 0);
                lblMsg.setText("Data Sent to Display Board");
            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addData(gps_data[position].arr_db_file + "," + 0);
                lblMsg.setText("Data Sent to Display Board");
            } else {
                lblMsg.setText("  File Dosn't exist");

            }

        }

        if (getArtIntEnable() && getArticulatedBus()) {
            file = new File(route_filepath, gps_data[position].arr_db_file);
            f = new File(main_route_filepath, gps_data[position].arr_db_file);
            if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                objdisQue.addData(gps_data[position].arr_db_file + "articulated" + "," + 0);
                lblMsg.setText("Data Sent to Display Board");

            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addData(gps_data[position].arr_db_file + "articulated" + "," + 0);
                lblMsg.setText("Data Sent to Display Board");

            } else {
                lblMsg.setText("  File Dosn't exist");
            }
        }
        if (position >= 0) {
            int i = 0;
            clsSharedVariables.setCurStopNo((byte) (position));
            for (i = 0; i < position; i++) {
                gps_data[i].stop_identify_status = true;
            }
            for (; i < getNoStopsRoute(); i++) {
                gps_data[i].stop_identify_status = false;
            }
            this.lstStops.updateUI();
        }
        file = null;
    }//GEN-LAST:event_btnAppStopActionPerformed

    private void lstStopsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstStopsValueChanged
        // TODO add your handling code here:
        lblMsg.setText("");
        position = lstStops.getSelectedIndex();
        if (position >= 0) {
            lblStopName.setText("Stop : " + lstStops.getSelectedValue().toString());
        }
    }//GEN-LAST:event_lstStopsValueChanged

    private void lstStopsInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lstStopsInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lstStopsInputMethodTextChanged
    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms
        if (get_special_msg_status() == true) {
            timer.schedule(timerTask, 10000, 5000); //
        } else {
            timer.schedule(timerTask, 10000, 5000); //
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
            @Override
            public void run() {
                if (curstop_no != clsSharedVariables.getCurStopNo()) {
                    curstop_no = clsSharedVariables.getCurStopNo();
                    lstStops.updateUI();
                }

            }
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAppStop;
    private javax.swing.JButton btnCurStop;
    private javax.swing.JButton btnNextStop;
    private javax.swing.JButton btnRouteBack;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblRouteNo;
    private javax.swing.JLabel lblStopName;
    private javax.swing.JList lstStops;
    private javax.swing.JTextField txtManStopSel;
    // End of variables declaration//GEN-END:variables
}
