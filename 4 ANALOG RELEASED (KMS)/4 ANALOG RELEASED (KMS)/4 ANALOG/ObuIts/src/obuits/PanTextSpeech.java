package obuits;

import java.awt.Dimension;
import java.util.List;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.ObjTextSpeech;
import static obuits.MainFrmIts.audio_speaker_off;
import static obuits.MainFrmIts.audio_speaker_on;

public class PanTextSpeech extends javax.swing.JPanel {

    public PanTextSpeech() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblConfigName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMessages = new javax.swing.JList();
        btnSend = new javax.swing.JButton();
        txtMsg = new javax.swing.JTextField();
        btnSendText = new javax.swing.JButton();

        setBackground(new java.awt.Color(23, 29, 32));
        setPreferredSize(new java.awt.Dimension(700, 430));

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("Text to Speech");
        lblConfigName.setPreferredSize(new java.awt.Dimension(700, 32));

        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 340));

        lstMessages.setFont(lstMessages.getFont().deriveFont(lstMessages.getFont().getStyle() | java.awt.Font.BOLD, lstMessages.getFont().getSize()+13));
        lstMessages.setForeground(new java.awt.Color(0, 0, 102));
        lstMessages.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "WELCOME TO BUS STOP", "MAINTAIN PHYSICAL  DISTANCE", "DUE TO COVID 19 ABOVE 65 YEARS AND BELOW 3 YEARS OF AGE DO NOT TRAVEL", "WEAR MASKS", "NO MASK NO ENTRY INTO THE BUS", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstMessages.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstMessages.setFixedCellHeight(50);
        lstMessages.setFixedCellWidth(100);
        lstMessages.setOpaque(false);
        lstMessages.setPreferredSize(new java.awt.Dimension(640, 1000));
        lstMessages.setSelectedIndex(0);
        jScrollPane1.setViewportView(lstMessages);

        btnSend.setBackground(new java.awt.Color(47, 49, 51));
        btnSend.setFont(btnSend.getFont().deriveFont(btnSend.getFont().getStyle() | java.awt.Font.BOLD, btnSend.getFont().getSize()+7));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText("PLAY");
        btnSend.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtMsg.setFont(txtMsg.getFont().deriveFont(txtMsg.getFont().getStyle() & ~java.awt.Font.BOLD, txtMsg.getFont().getSize()+5));
        txtMsg.setText("Hello");
        txtMsg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMsgFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMsgFocusLost(evt);
            }
        });

        btnSendText.setBackground(new java.awt.Color(47, 49, 51));
        btnSendText.setFont(btnSendText.getFont().deriveFont(btnSendText.getFont().getStyle() | java.awt.Font.BOLD, btnSendText.getFont().getSize()+7));
        btnSendText.setForeground(new java.awt.Color(255, 255, 255));
        btnSendText.setText("PLAY");
        btnSendText.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSendText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblConfigName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMsg)
                        .addGap(69, 69, 69)
                        .addComponent(btnSendText, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(2, 2, 2))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendText, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    @Override
    public void removeNotify() {
        super.removeNotify();
    }

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        try {
            // TODO add your handling code here:
            if (this.lstMessages.getSelectedIndex() >= 0) {
                String str = this.lstMessages.getSelectedValue().toString();
                SwingWorker sw1 = new SwingWorker() {
                    @Override
                    protected String doInBackground() throws Exception {

                        // System.out.println("str" + str);
                        audio_speaker_on();
                        try {
                            ObjTextSpeech.speak(str);
                            audio_speaker_off();
                        } catch (Exception ex) {
                        } finally {
                            ObjTextSpeech = null;
                            //   str = null;
                        }

                        return null;
                    }

                    @Override
                    protected void process(List chunks) {
                    }
                };

                sw1.execute();

            }
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_btnSendActionPerformed


    private void btnSendTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendTextActionPerformed
        // TODO add your handling code here:
        String str = this.txtMsg.getText();
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                audio_speaker_on();
                ObjTextSpeech.speak(str);

                audio_speaker_off();
                ObjTextSpeech = null;
                return null;
            }

            @Override
            protected void process(List chunks) {
            }
        };

        sw1.execute();

    }//GEN-LAST:event_btnSendTextActionPerformed
    String prev_control_name = "";
    private void txtMsgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMsgFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("msg")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("msg", txtMsg.getText());
            if (str != null) {
                txtMsg.setText(str);
            }
            prev_control_name = "msg";
            obj = null;
            str = null;
            txtMsg.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }
    }//GEN-LAST:event_txtMsgFocusGained

    private void txtMsgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMsgFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtMsgFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSendText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JList lstMessages;
    private javax.swing.JTextField txtMsg;
    // End of variables declaration//GEN-END:variables
}
