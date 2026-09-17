package obuits;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDisplayBrdSerialPort.objdisQue;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.objAudQue;
import static obuits.clsSharedVariables.setPreloadMsgDataSent;

public class PanPreloads extends javax.swing.JPanel {

    public PanPreloads() {
        initComponents();
        lblPreloadMsg.setText("");
        int i;
        ArrayList<String> items = new ArrayList<>();
        for (i = 0; i < 10; i++) {
            items.add("preload" + (i + 1));
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        items.stream().forEach((s) -> {
            model.addElement(s);
        });
        lstPreloads.setModel(model);
        lstPreloads.getSelectionModel().setLeadSelectionIndex(0);
        lstPreloads.setVisible(true);

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConfigName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPreloads = new javax.swing.JList();
        btnSend = new javax.swing.JButton();
        lblPreloadMsg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(23, 29, 32));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(700, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("Preloaded Messages");
        lblConfigName.setPreferredSize(new java.awt.Dimension(700, 32));

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 340));

        lstPreloads.setBackground(new java.awt.Color(240, 240, 240));
        lstPreloads.setFont(lstPreloads.getFont().deriveFont(lstPreloads.getFont().getStyle() | java.awt.Font.BOLD, lstPreloads.getFont().getSize()+13));
        lstPreloads.setForeground(new java.awt.Color(0, 0, 102));
        lstPreloads.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstPreloads.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstPreloads.setFixedCellHeight(50);
        lstPreloads.setFixedCellWidth(100);
        lstPreloads.setOpaque(false);
        lstPreloads.setPreferredSize(new java.awt.Dimension(640, 1000));
        lstPreloads.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                lstPreloadsInputMethodTextChanged(evt);
            }
        });
        lstPreloads.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPreloadsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstPreloads);

        btnSend.setBackground(new java.awt.Color(47, 49, 51));
        btnSend.setFont(btnSend.getFont().deriveFont(btnSend.getFont().getStyle() | java.awt.Font.BOLD, btnSend.getFont().getSize()+7));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText("SEND");
        btnSend.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        lblPreloadMsg.setFont(lblPreloadMsg.getFont().deriveFont(lblPreloadMsg.getFont().getStyle() | java.awt.Font.BOLD, lblPreloadMsg.getFont().getSize()+7));
        lblPreloadMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblPreloadMsg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPreloadMsg.setText("Data Sent");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPreloadMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreloadMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstPreloadsInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lstPreloadsInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lstPreloadsInputMethodTextChanged

    private void lstPreloadsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPreloadsValueChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_lstPreloadsValueChanged

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        String preloaded_msg = lstPreloads.getSelectedValue().toString() + ".txt";
        String preloaded_wave = lstPreloads.getSelectedValue().toString() + ".wav";
        File file;
        File f;
        if (getIntEnable()) {
            file = new File(route_filepath, preloaded_msg);
            f = new File(main_route_filepath, preloaded_msg);
            objdisQue.removeAllMessagesQueue();
            if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                objdisQue.addData(preloaded_msg + "," + 0);
                lblPreloadMsg.setText("Data Sent to Display Board");

            } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
                objdisQue.addData(preloaded_msg + "," + 0);
                lblPreloadMsg.setText("Data Sent to Display Board");

            } else {
                lblPreloadMsg.setText("File Doesn't exist");

            }
            setPreloadMsgDataSent(true);
        } else {
            lblPreloadMsg.setText("Internal Display Board is Disabled");

            setPreloadMsgDataSent(false);

        }
        file = new File(route_filepath, preloaded_wave);
        f = new File(main_route_filepath, preloaded_wave);
        if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            objAudQue.removeAllMessagesQueue();
            objAudQue.addData(preloaded_wave);
            lblPreloadMsg.setText("Playing preload  audio");

        } else if (clsDefines.DUPLICATE_FILES_MAIN_PATH && f.exists()) {
            objAudQue.removeAllMessagesQueue();
            objAudQue.addData(preloaded_wave);
            lblPreloadMsg.setText("Playing preload  audio");

        } else {
            lblPreloadMsg.setText("preloaded wave Dosn't exist");

        }
        file = null;
        f = null;

    }//GEN-LAST:event_btnSendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblConfigName;
    public static javax.swing.JLabel lblPreloadMsg;
    private javax.swing.JList lstPreloads;
    // End of variables declaration//GEN-END:variables
}
