package obuits;

import static obuits.clsDefines.LANG_ENGLISH;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;

public class PanLanguage extends javax.swing.JPanel {

    public PanLanguage() {
        initComponents();
        lblMsg.setText("");
        if (clsSharedVariables.lang_type == LANG_ENGLISH) {
            this.cmbLangName.setSelectedIndex(0);
        } else if (clsSharedVariables.lang_type == LANG_HINDI) {
            this.cmbLangName.setSelectedIndex(1);
        } else {
            this.cmbLangName.setSelectedIndex(2);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnSave = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        lblConfigName = new javax.swing.JTextField();
        cmbLangName = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(450, 430));

        btnSave.setBackground(new java.awt.Color(47, 49, 51));
        btnSave.setFont(btnSave.getFont().deriveFont(btnSave.getFont().getStyle() | java.awt.Font.BOLD, btnSave.getFont().getSize()+7));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblMsg.setFont(lblMsg.getFont().deriveFont(lblMsg.getFont().getStyle() | java.awt.Font.BOLD, lblMsg.getFont().getSize()+7));
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg.setText("Configuration Saved");

        lblConfigName.setEditable(false);
        lblConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblConfigName.setFont(lblConfigName.getFont().deriveFont(lblConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblConfigName.getFont().getSize()+10));
        lblConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblConfigName.setText("LANGUAGE");

        cmbLangName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbLangName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English", "Hindi", "Bengali", "Marathi", "Telugu", "Tamil", "Urdu", "Gujarati", "Kannada", "Malayalam", "Odia", "Punjabi", "Assamese", " " }));
        cmbLangName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLangNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+7));
        jLabel1.setText("Language");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblConfigName)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jLabel1)
                            .addGap(46, 46, 46)
                            .addComponent(cmbLangName, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(145, 145, 145)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbLangName, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (this.cmbLangName.getSelectedIndex() == 0) {
            clsSharedVariables.lang_type = LANG_ENGLISH;
        } else if (this.cmbLangName.getSelectedIndex() == 1) {
            clsSharedVariables.lang_type = LANG_HINDI;
        } else {
            clsSharedVariables.lang_type = LANG_REG;
        }
        clsReadFiles obj = new clsReadFiles();
        obj.write_cfg_data_file();
        obj.read_label_names_details();
        lblMsg.setText("Configuration Saved");

    }//GEN-LAST:event_btnSaveActionPerformed

    private void cmbLangNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLangNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLangNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbLangName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField lblConfigName;
    private javax.swing.JLabel lblMsg;
    // End of variables declaration//GEN-END:variables
}
