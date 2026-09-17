package obuits;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import static obuits.clsDefines.HINDI_FONT_NAME;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;
import static obuits.clsDefines.REG_FONT_NAME;
import static obuits.clsSharedVariables.lang_type;
import static obuits.clsSharedVariables.no_panic_messages;
import static obuits.clsSharedVariables.panic_message;
import static obuits.clsSharedVariables.panic_message_id;
import static obuits.clsSharedVariables.set_panic_data_sent_server;

public class PanSos extends javax.swing.JPanel {

    ArrayList<String> items = new ArrayList<>();
    int position = 0;

    public PanSos() {
        initComponents();
        lblMsg.setText("");
        int i;
        int no_panic_msgs = no_panic_messages;

        for (i = 0; i < no_panic_msgs; i++) {
            items.add("   " + panic_message[i]);
        }
        DefaultListModel<String> model = new DefaultListModel<>();
        items.stream().forEach((s) -> {
            model.addElement(s);
        });

        lsPanics.setModel(model);
        lsPanics.getSelectionModel().setLeadSelectionIndex(0);
        lsPanics.setVisible(true);

        if (lang_type == LANG_HINDI) {
            lblConfigName.setFont(new java.awt.Font(HINDI_FONT_NAME, 1, 28));
            lsPanics.setFont(new java.awt.Font(HINDI_FONT_NAME, 1, 28));
        } else if (lang_type == LANG_REG) {
            lblConfigName.setFont(new java.awt.Font(REG_FONT_NAME, 1, 28));
            lsPanics.setFont(new java.awt.Font(REG_FONT_NAME, 1, 28));
        }

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblMsg = new javax.swing.JLabel();
        lblConfigName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsPanics = new javax.swing.JList();

        setBackground(new java.awt.Color(23, 29, 32));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(255, 248, 249), java.awt.Color.white, java.awt.Color.white));
        setPreferredSize(new java.awt.Dimension(700, 500));

        lblMsg.setBackground(new java.awt.Color(119, 119, 119));
        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(255, 255, 255));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg.setText("Data send To server");

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("SOS Alerts");

        lsPanics.setBackground(new java.awt.Color(240, 240, 240));
        lsPanics.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lsPanics.setFont(lsPanics.getFont().deriveFont(lsPanics.getFont().getStyle() | java.awt.Font.BOLD, lsPanics.getFont().getSize()+11));
        lsPanics.setForeground(new java.awt.Color(0, 0, 102));
        lsPanics.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "TERRORIST ATTACK", "BUS HIJACKED", "MET SEVERE ACCIDENT", "BUS CAUGHT FIRE", "DROWNING IN WATER", "BRAKEDOWN", "FIRE" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lsPanics.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lsPanics.setFixedCellHeight(50);
        lsPanics.setFixedCellWidth(200);
        lsPanics.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lsPanicsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lsPanics);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addComponent(lblConfigName, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lsPanicsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lsPanicsValueChanged
        // TODO add your handling code here:
        lblMsg.setText("");
        position = lsPanics.getSelectedIndex();

        if (position >= 0) {

            try {
                set_panic_data_sent_server(false);

                if (clsSharedVariables.getIpAddr1Enable()) {
                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.panic_messages(panic_message_id[position]);
                    obj16833Pkts = null;
                }
                if (clsSharedVariables.getIpAddr4Enable()) {
                    gpsDriving objDriving = new gpsDriving();
                    objDriving.panic_messages(panic_message_id[position]);
                    objDriving = null;
                }

                lblMsg.setText(lsPanics.getSelectedValue().toString());

            } catch (Exception ex) {
            }

        }
    }//GEN-LAST:event_lsPanicsValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JList lsPanics;
    // End of variables declaration//GEN-END:variables
}
