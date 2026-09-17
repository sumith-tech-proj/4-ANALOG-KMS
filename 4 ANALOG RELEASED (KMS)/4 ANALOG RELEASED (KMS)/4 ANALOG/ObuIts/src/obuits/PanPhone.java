package obuits;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static obuits.MainFrmIts.audio_speaker_off;
import static obuits.MainFrmIts.digOutAudioVoiceCallPin;
import static obuits.MainFrmIts.write_gpio_pin_state;
import static obuits.clsDefines.CALL_BUSY;
import static obuits.clsDefines.CALL_CONNECT;
import static obuits.clsDefines.CALL_DIALTONE;
import static obuits.clsDefines.CALL_HELD;
import static obuits.clsDefines.CALL_NOCARRIER;
import static obuits.clsDefines.CALL_OUT_ALERT;
import static obuits.clsDefines.CALL_OUT_DIAL;
import static obuits.clsDefines.CALL_WAIT;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;
import static obuits.clsDefines.NO_CALL;
import static obuits.clsDefines.OUT_CALL;
import static obuits.clsDefines.Strings.AUDIO_VOICECALL_COMM_ONOFF;
import static obuits.clsDefines.Strings.VOICE_CALL_OFF_STATE;
import static obuits.clsDefines.Strings.VOICE_CALL_ON_STATE;
import static obuits.clsReadFiles.label_names;
import static obuits.clsReadFiles.label_names_cnt;
import static obuits.clsSharedVariables.getPhoneName;
import static obuits.clsSharedVariables.getPhoneNo;
import static obuits.clsSharedVariables.getPhoneNoCnt;
import static obuits.clsSharedVariables.lang_type;
import static obuits.clsSharedVariables.setCurActivity;
import static obuits.clsSharedVariables.set_phonecall_type;
import static obuits.clsSharedVariables.set_voice_call_enabled_status;
import static obuits.clsSharedVariables.set_voice_call_start_status;
import static obuits.clsDefines.media_filepath;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public class PanPhone extends javax.swing.JPanel {

    Timer timer;
    TimerTask timerTask;
    DefaultTableModel model = new DefaultTableModel(10, 0);
    int selected_ph_no_row = -1;
    boolean ph_call_on = false;

    public PanPhone() {
        initComponents();
        int i = 0;
        if (label_names_cnt > 0 && (lang_type == LANG_HINDI || lang_type == LANG_REG)) {
            try {
                this.btnPhoneCall.setText(label_names[14]);
                this.btnPhoneEndCall.setText(label_names[15]);

            } catch (Exception ex) {
            }
            btnPhoneCall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/phone_call.png")));
            btnPhoneEndCall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/phone_end_call.png")));
        }
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //Only the third column
                return false;
            }
        };
        model.addColumn("NAME");
        model.addColumn("CONTACT NUMBER");
        tabPhone.removeAll();
        model.setNumRows(getPhoneNoCnt());
        tabPhone.setModel(model);
        tabPhone.setRowSelectionAllowed(true);
        JTableHeader tabHead = tabPhone.getTableHeader();
        tabHead.setFont(new Font("Serif", Font.BOLD, 25));
        tabHead.setBackground(new Color(23, 29, 32));
        tabHead.setForeground(Color.WHITE);
        tabPhone.setTableHeader(tabHead);
        try {
            for (i = 0; i < getPhoneNoCnt(); i++) {
                tabPhone.setValueAt(getPhoneName((byte) i), i, 0);
                System.out.println("getPhoneName "+ tabPhone.getValueAt(i,i));
                tabPhone.setValueAt(getPhoneNo((byte) i), i, 1);
                
            }
            tabPhone.setVisible(true);
            tabPhone.getSelectionModel().setLeadSelectionIndex(0);
            tabPhone.setVisible(true);
            tabPhone.getColumnModel().getColumn(0).setPreferredWidth(70);
            tabPhone.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabPhone.setShowVerticalLines(false);
            tabPhone.setShowGrid(false);
            tabPhone.setShowHorizontalLines(false);
        } catch (Exception ex) {

        }
        btnPhoneCall.setVisible(true);
        btnPhoneEndCall.setVisible(false);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        setCurActivity(clsDefines.PHONE_ACTIVITY);
        try {
            clsReadFiles objReadFilesAct = new clsReadFiles();
            objReadFilesAct.write_activity_name(clsDefines.PHONE_ACTIVITY);
            objReadFilesAct = null;
        } catch (Exception ex) {

        }
        tabPhone.getTableHeader().setReorderingAllowed(false);
        tabPhone.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                if (ph_call_on == true && selected_ph_no_row >= 0) {
                    if (row == selected_ph_no_row) {
                        if (clsSharedVariables.get_phonecall_type() == CALL_CONNECT) {
                            setBackground(Color.GREEN);
                            setForeground(Color.white);
                            setFont(new Font("Serif", Font.BOLD, 32));
                        } else if (clsSharedVariables.get_phonecall_type() == OUT_CALL) {
                            setBackground(Color.YELLOW);
                            setForeground(Color.BLACK);
                            setFont(new Font("Serif", Font.BOLD, 32));
                        } else if (clsSharedVariables.get_phonecall_type() == NO_CALL) {
                            setBackground(Color.WHITE);
                            setForeground(Color.BLACK);
                            setFont(new Font("Serif", Font.BOLD, 22));
                        }
                    } else {
                        setBackground(new Color(240, 240, 240));//Color.LIGHT_GRAY);
                        setForeground(Color.BLACK);
                        setFont(new Font("Serif", Font.BOLD, 22));
                    }
                }
                return this;
            }

        }
        );
        reloadTableData();  
    }
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabPhone = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnPhoneCall = new javax.swing.JButton();
        btnPhoneEndCall = new javax.swing.JButton();
        lblCallMessage = new javax.swing.JLabel();
        delete = new javax.swing.JButton();

        setBackground(new java.awt.Color(18, 23, 51));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 430));
        setRequestFocusEnabled(false);

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 485));

        tabPhone.setBackground(new java.awt.Color(240, 240, 240));
        tabPhone.setFont(tabPhone.getFont().deriveFont(tabPhone.getFont().getStyle() | java.awt.Font.BOLD, tabPhone.getFont().getSize()+7));
        tabPhone.setForeground(new java.awt.Color(0, 0, 102));
        tabPhone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Contact Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPhone.setGridColor(new java.awt.Color(204, 204, 204));
        tabPhone.setPreferredSize(new java.awt.Dimension(690, 1500));
        tabPhone.setRowHeight(50);
        tabPhone.setSelectionBackground(new java.awt.Color(50, 128, 224));
        tabPhone.setSelectionForeground(new java.awt.Color(217, 224, 229));
        tabPhone.setUpdateSelectionOnSort(false);
        tabPhone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPhoneMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabPhone);

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 77));

        btnPhoneCall.setFont(btnPhoneCall.getFont().deriveFont(btnPhoneCall.getFont().getStyle() | java.awt.Font.BOLD, btnPhoneCall.getFont().getSize()+7));
        btnPhoneCall.setForeground(new java.awt.Color(255, 255, 255));
        btnPhoneCall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/phone_call.png"))); // NOI18N
        btnPhoneCall.setText("CALL");
        btnPhoneCall.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPhoneCall.setBorderPainted(false);
        btnPhoneCall.setContentAreaFilled(false);
        btnPhoneCall.setPreferredSize(new java.awt.Dimension(100, 60));
        btnPhoneCall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhoneCallActionPerformed(evt);
            }
        });

        btnPhoneEndCall.setFont(btnPhoneEndCall.getFont().deriveFont(btnPhoneEndCall.getFont().getStyle() | java.awt.Font.BOLD, btnPhoneEndCall.getFont().getSize()+7));
        btnPhoneEndCall.setForeground(new java.awt.Color(255, 255, 255));
        btnPhoneEndCall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/phone_end_call.png"))); // NOI18N
        btnPhoneEndCall.setText("END CALL");
        btnPhoneEndCall.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPhoneEndCall.setBorderPainted(false);
        btnPhoneEndCall.setContentAreaFilled(false);
        btnPhoneEndCall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhoneEndCall.setIconTextGap(0);
        btnPhoneEndCall.setPreferredSize(new java.awt.Dimension(120, 60));
        btnPhoneEndCall.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPhoneEndCall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhoneEndCallActionPerformed(evt);
            }
        });

        lblCallMessage.setBackground(new java.awt.Color(23, 29, 32));
        lblCallMessage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCallMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblCallMessage.setOpaque(true);
        lblCallMessage.setPreferredSize(new java.awt.Dimension(700, 37));

        delete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        delete.setForeground(new java.awt.Color(0, 0, 102));
        delete.setText("DELETE");
        delete.setActionCommand("delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnPhoneCall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPhoneEndCall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblCallMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPhoneCall, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPhoneEndCall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCallMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPhoneCall.getAccessibleContext().setAccessibleName("btnPhoneCall");
        btnPhoneEndCall.getAccessibleContext().setAccessibleName("btnEndCall");
        lblCallMessage.getAccessibleContext().setAccessibleName("lblCallMessage");
        delete.getAccessibleContext().setAccessibleName("delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void removeNotify() {
        super.removeNotify();
        set_voice_call_start_status(false);
        set_voice_call_enabled_status(false);
        close_call_end_gpio_controls();
        try {
            clsAtSerialPort.atSerialWrite("ATH\r\n");
            clsAtSerialPort.atSerialWrite("ATH\r\n");
            clsAtSerialPort.atSerialWrite("ATH\r\n");
        } catch (Exception ex) {
        }
        set_phonecall_type(CALL_NOCARRIER);
        stopTimer();

    }
    private void btnPhoneCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhoneCallActionPerformed
        // TODO add your handling code here:
       
        StringBuilder sb = new StringBuilder();
        String str;
        ph_call_on = true;

        int sel_row = tabPhone.getSelectedRow();
        selected_ph_no_row = sel_row;
        String name = (String) tabPhone.getValueAt(sel_row, 0).toString();
        String ph_no = (String) tabPhone.getValueAt(sel_row, 1).toString();

        try {
            set_phonecall_type(OUT_CALL);
            sb = sb.delete(0, sb.length());
            sb.append("ATD");
            sb.append(ph_no);
            sb.append(";\r\n");
            try {
                clsAtSerialPort.atSerialWrite(sb.toString());

            } catch (Exception ex) {
            }
            Thread.sleep(1000);
            lblCallMessage.setText("Call Dialling..." + name);
            btnPhoneCall.setVisible(false);
            delete.setVisible(false);
            btnPhoneEndCall.setVisible(true);
            set_voice_call_enabled_status(true);
            open_call_gpio_controls();
            set_voice_call_start_status(true);
            startTimer();
            str = null;
            sb = null;
            if (tabPhone.getModel().getRowCount() > 0) {
                tabPhone.updateUI();
            }

        } catch (InterruptedException ex) {
        }

    }//GEN-LAST:event_btnPhoneCallActionPerformed

    private void btnPhoneEndCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhoneEndCallActionPerformed

        // TODO add your handling code here: 
        set_voice_call_start_status(false);
        set_voice_call_enabled_status(false);
        close_call_end_gpio_controls();
        delete.setVisible(true);
        try {
            clsAtSerialPort.atSerialWrite("ATH\r\n");
            clsAtSerialPort.atSerialWrite("ATH\r\n");
            clsAtSerialPort.atSerialWrite("ATH\r\n");
        } catch (Exception ex) {
        }
        lblCallMessage.setText("Call Disconnected...");

        set_phonecall_type(CALL_NOCARRIER);
        stopTimer();
        tabPhone.updateUI();
        ph_call_on = false;
    }//GEN-LAST:event_btnPhoneEndCallActionPerformed

    private void tabPhoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPhoneMouseClicked
        // TODO add your handling code here:

        if (ph_call_on == false) {
        }

    }//GEN-LAST:event_tabPhoneMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       int sel_row = tabPhone.getSelectedRow();
       
    if (sel_row == -1) {
        JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        return;
    }
    String name=tabPhone.getValueAt(sel_row, 0).toString().trim();
    String ph_no = tabPhone.getValueAt(sel_row, 1).toString().trim(); // Phone number is in column 1
                    System.out.println("ph_no "+ph_no);
                    // Ask for confirmation before deleting
    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Do you want to delete phone number: " + name + "?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return; // User clicked "No", cancel the deletion
    }


    File file = new File(media_filepath, "phonenum.txt");

    List<String> lines = new ArrayList<>();
    boolean isDeleted = false;

    // Read all lines from the file
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splitStr = line.split(",");

            // Safety check and comparison
            if (splitStr.length >= 1) {
                System.out.println("splitStr.length "+splitStr.length);
                String filePhNo = splitStr[0].trim(); // Phone number is in column 0 of the file
                System.out.println("filePhNo "+filePhNo);
                if (!filePhNo.equals(ph_no)) {
                    lines.add(line); // Only keep lines that don't match
                } else {
                    System.out.println("equalll");
                    isDeleted = true; // Mark that we deleted a line
                }
            } else {
                // Line is malformed (no comma), keep it to be safe
                lines.add(line);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error reading file.");
        return;
    }

    // If deleted, write the updated list back to the file
    if (isDeleted) {
        System.out.println("deleted");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error writing file.");
            return;
        }
           //reloadd
          reloadTableData();
           if (tabPhone.getRowCount() > 0) {
            if (sel_row < tabPhone.getRowCount()) {
                tabPhone.setRowSelectionInterval(sel_row, sel_row); // Keep the selection at the same index (if valid)
            } else {
                tabPhone.setRowSelectionInterval(sel_row - 1, sel_row - 1); // If last row was deleted, select the previous row
            }
        }
        JOptionPane.showMessageDialog(null, "Phone number deleted successfully.");
    } else {
        JOptionPane.showMessageDialog(null, "Phone number not found in file.");
    }


    }//GEN-LAST:event_deleteActionPerformed
   private void reloadTableData() {
    // Assuming you have a model for your table, like DefaultTableModel
    DefaultTableModel model = (DefaultTableModel) tabPhone.getModel();

    // Clear the existing data from the table
    model.setRowCount(0);

    // Now load the updated data from the file and add it to the table
    File file = new File(media_filepath, "phonenum.txt");
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        String[] splitLine;
        

        while ((line = reader.readLine()) != null) {
            splitLine= line.split(",");
             if (splitLine.length >= 2) {
                String phoneNumber = splitLine[0].trim();
                String name = splitLine[1].trim();
                model.addRow(new Object[]{name, phoneNumber});
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   public void stopTimer() {
        try {
            if (timer != null) {
                timer.cancel();
            }
            if (timerTask != null) {
                timerTask.cancel();
            }
            timer = null;
            timerTask = null;

        } catch (Exception ex) {
        }
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                btnPhoneCall.setVisible(true);
                btnPhoneEndCall.setVisible(false);
                set_phonecall_type(NO_CALL);
                lblCallMessage.setText("");
            }
        });
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms
        timer.schedule(timerTask, 1000, 2000); // 
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                try {
                    byte call_type_in_out = clsSharedVariables.get_phonecall_type();
                    if (call_type_in_out == CALL_BUSY) {
                        set_voice_call_start_status(false);
                        set_voice_call_enabled_status(false);
                        update_thread_text_fields(lblCallMessage, "Call Busy...");
                        call_type_in_out = NO_CALL;
                        clsSharedVariables.set_phonecall_type(call_type_in_out);
                        tabPhone.updateUI();
                        stopTimer();
                    } else if (call_type_in_out == CALL_DIALTONE) {
                        set_voice_call_start_status(false);
                        set_voice_call_enabled_status(false);
                        update_thread_text_fields(lblCallMessage, "No Dialtone");
                        call_type_in_out = NO_CALL;
                        set_phonecall_type(NO_CALL);
                        tabPhone.updateUI();
                        stopTimer();

                    } else if (call_type_in_out == CALL_NOCARRIER) {
                        set_voice_call_start_status(false);
                        set_voice_call_enabled_status(false);
                        update_thread_text_fields(lblCallMessage, "Call End");
                        set_phonecall_type(NO_CALL);
                        call_type_in_out = NO_CALL;
                        tabPhone.updateUI();
                        stopTimer();
                    } else if (call_type_in_out == CALL_CONNECT) {
                        update_thread_text_fields(lblCallMessage, "Call Connected...");
                        set_phonecall_type(NO_CALL);
                        tabPhone.updateUI();
                    } else if (call_type_in_out == CALL_HELD) {
                        update_thread_text_fields(lblCallMessage, "Call Held...");
                        set_phonecall_type(NO_CALL);
                        tabPhone.updateUI();
                        stopTimer();
                    } else if (call_type_in_out == CALL_WAIT) {
                        update_thread_text_fields(lblCallMessage, "Call waiting ...");
                        set_phonecall_type(NO_CALL);
                        tabPhone.setVisible(true);
                        tabPhone.updateUI();
                        stopTimer();
                    } else if (call_type_in_out == CALL_OUT_DIAL) {
                        update_thread_text_fields(lblCallMessage, "Call Dialling ...");
                        set_phonecall_type(NO_CALL);
                    } else if (call_type_in_out == CALL_OUT_ALERT) {
                        update_thread_text_fields(lblCallMessage, "Call Alert ...");
                    }
                } catch (Exception ex) {
                }
            }
        };
    }

    public void close_call_end_gpio_controls() {
        set_voice_call_enabled_status(false);
        write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_OFF_STATE, digOutAudioVoiceCallPin);
        audio_speaker_off();
        set_voice_call_start_status(false);
    }

    private void open_call_gpio_controls() {
        set_voice_call_enabled_status(true);
        write_gpio_pin_state(AUDIO_VOICECALL_COMM_ONOFF, VOICE_CALL_ON_STATE, digOutAudioVoiceCallPin);
    }

    private synchronized void update_thread_text_fields(JLabel lbl, String str) {
        SwingUtilities.invokeLater(() -> {
            lbl.setText(str);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPhoneCall;
    private javax.swing.JButton btnPhoneEndCall;
    private javax.swing.JButton delete;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCallMessage;
    private javax.swing.JTable tabPhone;
    // End of variables declaration//GEN-END:variables
}
