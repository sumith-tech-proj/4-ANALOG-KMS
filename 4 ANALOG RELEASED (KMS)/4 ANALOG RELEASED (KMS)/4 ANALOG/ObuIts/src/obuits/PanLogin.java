package obuits;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.DRIVER_LOGIN_INITIAL;
import static obuits.clsDefines.LANG_ENGLISH;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;
import static obuits.clsSharedVariables.conductorDetailsCnt;
import static obuits.clsSharedVariables.configuration_pwd;
import static obuits.clsSharedVariables.driverDetailsCnt;
import static obuits.clsSharedVariables.objConductorDetails;
import static obuits.clsSharedVariables.objDriverDetails;
import static obuits.clsSharedVariables.set_driver_name;

/**
 *
 * @author Sumitha
 */
public class PanLogin extends javax.swing.JPanel {

    /**
     * Creates new form PanLogin
     */
    JPanel panCenterMainPane;
    JPanel panMainPane;
    JPanel PanLeftpane;
    JButton btnLogout;
    JButton[] btnList;
    JLabel lbldriverid1;
    JLabel lblconductorid1;
    public static byte driver_login_send = DRIVER_LOGIN_INITIAL;

    public PanLogin(JPanel panCenterPan, JPanel panMainPan, JButton btnLogou, JButton[] btnLt, JPanel panLeftpan, JLabel lbldriveri) {
        panCenterMainPane = panCenterPan;
        panMainPane = panMainPan;
        PanLeftpane = panLeftpan;
        btnLogout = btnLogou;
        btnList = btnLt;
        lbldriverid1 = lbldriveri;
        initComponents();
        txtMsg.setText("");
        String[] split_str;
        String dname = null;
        String cname = null;
        clsReadFiles obj = new clsReadFiles();
        String data = obj.read_last_driver_details_file();

        obj = null;
        if (data != null) {
            split_str = data.split(",");
            if (split_str.length > 1) {
                dname = split_str[0];
                cname = split_str[1];
            }
        }

        cmbdriverid.removeAllItems();
        cmbconductorid.removeAllItems();
        for (int i = 0; i < driverDetailsCnt; i++) {
            cmbdriverid.addItem(objDriverDetails[i].driver_id);
            if (dname != null && dname.equals(objDriverDetails[i].driver_id)) {
                cmbdriverid.setSelectedIndex(i);
            }
        }
        for (int i = 0; i < conductorDetailsCnt; i++) {
            cmbconductorid.addItem(objConductorDetails[i].conductor_id);
            if (cname != null && cname.equals(objConductorDetails[i].conductor_id)) {
                cmbconductorid.setSelectedIndex(i);
            }
        }
        if (driverDetailsCnt == 0) {
            objDriverDetails[0] = new clsSharedVariables.clsDriverDetails();
            objDriverDetails[0].driver_id = "3339";
            objDriverDetails[0].driver_name = "3339";
            objDriverDetails[0].pwd = "3339";
        }
        if (driverDetailsCnt == 0) {
            cmbdriverid.addItem("3339");
            driverDetailsCnt = 1;
        }      
        if (clsSharedVariables.getRs232Type() == clsDefines.RS232_ALCO) {
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jLabel2.setVisible(false);
            jLabel1.setVisible(false);
            cmbdriverid.setVisible(false);
            cmbconductorid.setVisible(false);
            txtPwd.setVisible(false);
            cmbLangName.setVisible(false);
            btnLogin.setVisible(false);
            
            txtMsg.setText("PLEASE WAIT UNTIL CAMERAS ON"
                    + " AND "
                    + "TAKE ALCOBRAKE TEST");
                    
              
            
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPwd = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbLangName = new javax.swing.JComboBox();
        lblSumithLogo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbdriverid = new javax.swing.JComboBox();
        cmbconductorid = new javax.swing.JComboBox();
        txtMsg = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 240, 225));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(700, 450));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+10));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("LOGIN");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+7));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Password");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txtPwd.setFont(txtPwd.getFont().deriveFont(txtPwd.getFont().getStyle() | java.awt.Font.BOLD, txtPwd.getFont().getSize()+7));
        txtPwd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPwdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPwdFocusLost(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(119, 119, 119));
        btnLogin.setFont(btnLogin.getFont().deriveFont(btnLogin.getFont().getStyle() | java.awt.Font.BOLD, btnLogin.getFont().getSize()+9));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login.png"))); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, jLabel4.getFont().getSize()+7));
        jLabel4.setText("Driver ID");

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+7));
        jLabel1.setText("Language");

        cmbLangName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbLangName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English", "Hindi", "Bengali", "Marathi", "Telugu", "Tamil", "Urdu", "Gujarati", "Kannada", "Malayalam", "Odia", "Punjabi", "Assamese", " " }));

        lblSumithLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSumithLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sumith_logo.png"))); // NOI18N
        lblSumithLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD, jLabel5.getFont().getSize()+7));
        jLabel5.setText("Conductor ID");

        cmbdriverid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbdriverid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select ID" }));
        cmbdriverid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbdriveridFocusGained(evt);
            }
        });

        cmbconductorid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbconductorid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select ID" }));
        cmbconductorid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbconductoridFocusGained(evt);
            }
        });

        txtMsg.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtMsg.setForeground(new java.awt.Color(102, 0, 51));
        txtMsg.setText("Login Here");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblSumithLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbLangName, 0, 152, Short.MAX_VALUE)
                    .addComponent(cmbconductorid, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbdriverid, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPwd))
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel4))
                            .addComponent(cmbdriverid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbconductorid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbLangName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblSumithLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addGap(38, 38, 38)
                .addComponent(txtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    private void loginCheck() {
        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {

                for (int i = 0; i < 100; i++) {
                    if (get_login_status() == clsDefines.DRIVER_LOGIN_SUCCESS) {

                        return "Success";

                    } else if (get_login_status() == clsDefines.DRIVER_LOGIN_FAILURE) {

                        return "fail";
                    }

                    Thread.sleep(1000);

                }
                return "Success";
                // return ("Over");
            }

            @Override
            protected void process(List chunks) {
                String data = (String) chunks.get(0);

                if (data.equals("Over")) {
                    txtMsg.setText("No Response From Server");

                } else if (data.equals("Success")) {
                    txtMsg.setText("Login Success");

                } else if (data.equals("fail")) {
                    txtMsg.setText("Invalid User ID / Password");

                }
            }

            @Override
            protected void done() {
                int i = 0;
                btnLogin.setEnabled(true);
                if (get_login_status() == clsDefines.DRIVER_LOGIN_SUCCESS) {
                    btnLogout.setVisible(true);
                    btnLogout.setEnabled(true);
                    clsSharedVariables.set_driver_id(cmbdriverid.getSelectedItem().toString());
                    clsSharedVariables.set_conductor_id(cmbconductorid.getSelectedItem().toString());
                    //set_driver_name(objDriverDetails[i].driver_name);
                    txtMsg.setText("");
                    lbldriverid1.setText("D.ID" + cmbdriverid.getSelectedItem().toString() + " C.ID" + cmbconductorid.getSelectedItem().toString());

                    panCenterMainPane.setVisible(true);
                    panMainPane.removeAll();
                    panMainPane.setLayout(new java.awt.BorderLayout());
                    panMainPane.add(panCenterMainPane);
                    panMainPane.revalidate();
                    panMainPane.repaint();
                    PanLeftpane.setVisible(true);

                    clsReadFiles obj = new clsReadFiles();
                    obj.read_label_names_details();
                    obj.read_panic_messages();
                    btnList[0].setVisible(true);

                    btnList[0].setVisible(true);
                    MainFrmIts.btnHome.setVisible(true);
                    MainFrmIts.btnHome.setEnabled(true);
                    obj = null;
                }
            }

        };
        // executes the swingworker on worker thread
        sw1.execute();
    }

    public static synchronized void set_login_status(byte status) {
        driver_login_send = status;
    }

    public static synchronized byte get_login_status() {
        return driver_login_send;

    }
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here: 
        boolean found = false;
        clsReadFiles obj = new clsReadFiles();
        String driver_name = "";
        String conductor_name = "";
        int i = 0;
        if (cmbdriverid.getSelectedItem().toString().trim().equals("")) {
            txtMsg.setText("Please Enter Valid Driver ID");

            return;
        } else if (txtPwd.getText().trim().equals("")) {
            txtMsg.setText("Please Enter Valid Password");

            return;
        }
        if (cmbdriverid.getSelectedItem().toString().equals(configuration_pwd)) {
            if (txtPwd.getText().equalsIgnoreCase(configuration_pwd)) {
                found = true;
            }
        }
        if (found == false) {

            if (this.cmbLangName.getSelectedIndex() == 0) {
                clsSharedVariables.lang_type = LANG_ENGLISH;
            } else if (this.cmbLangName.getSelectedIndex() == 1) {
                clsSharedVariables.lang_type = LANG_HINDI;
            } else {
                clsSharedVariables.lang_type = LANG_REG;
            }
            {
                for (i = 0; i < driverDetailsCnt; i++) {
                    if (cmbdriverid.getSelectedItem().toString().equals(objDriverDetails[i].driver_id)) {
                        if (txtPwd.getText().equalsIgnoreCase(objDriverDetails[i].pwd)) {

                            found = true;
                            break;
                        }
                    }
                }
            }
        }

        if (found == false) {
            txtMsg.setText("Please Enter correct Password");

        } else {
            btnLogout.setVisible(true);
            btnLogout.setEnabled(true);
            clsSharedVariables.set_driver_id(cmbdriverid.getSelectedItem().toString());
            //set_driver_name(objDriverDetails[i].driver_name);
            txtMsg.setText("");
            if (cmbconductorid.getItemCount() >= 1) {
                lbldriverid1.setText("Dvr: " + cmbdriverid.getSelectedItem().toString() + " Cdr: " + cmbconductorid.getSelectedItem().toString());
                obj.write_last_driver_details_file(cmbdriverid.getSelectedItem().toString(), cmbconductorid.getSelectedItem().toString());
                conductor_name = objConductorDetails[cmbconductorid.getSelectedIndex()].conductor_name;
                driver_name = objDriverDetails[i].driver_name;
                clsSharedVariables.set_conductor_name(cmbconductorid.getSelectedItem().toString());
            } else {
                lbldriverid1.setText("Dvr: " + cmbdriverid.getSelectedItem().toString());
                obj.write_last_driver_details_file(cmbdriverid.getSelectedItem().toString(), "");
                clsSharedVariables.set_conductor_name("");

            }
            set_driver_name(cmbdriverid.getSelectedItem().toString());

            panCenterMainPane.setVisible(true);
            panMainPane.removeAll();
            panMainPane.setLayout(new java.awt.BorderLayout());
            panMainPane.add(panCenterMainPane);
            panMainPane.revalidate();
            panMainPane.repaint();
            PanLeftpane.setVisible(true);

            if (this.cmbLangName.getSelectedIndex() == 0) {
                clsSharedVariables.lang_type = LANG_ENGLISH;
            } else if (this.cmbLangName.getSelectedIndex() == 1) {
                clsSharedVariables.lang_type = LANG_HINDI;
            } else {
                clsSharedVariables.lang_type = LANG_REG;
            }

            obj.read_label_names_details();
            obj.read_panic_messages();

            btnList[0].setVisible(true);

            btnList[0].setVisible(true);
            MainFrmIts.btnHome.setVisible(true);
            MainFrmIts.btnHome.setEnabled(true);
            MainFrmIts.btnHome.setVisible(true);

            obj = null;
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    String prev_control_name = "";
    private void txtPwdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPwdFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Password")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Password", txtPwd.getText());
            if (str != null) {
                txtPwd.setText(str);
            }
            prev_control_name = "Password";
            obj = null;
            str = null;
            txtPwd.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }

    }//GEN-LAST:event_txtPwdFocusGained

    private void txtPwdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPwdFocusLost
        // TODO add your handling code here:
        prev_control_name = "";
    }//GEN-LAST:event_txtPwdFocusLost

    private void cmbdriveridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbdriveridFocusGained


    }//GEN-LAST:event_cmbdriveridFocusGained

    private void cmbconductoridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbconductoridFocusGained

    }//GEN-LAST:event_cmbconductoridFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox cmbLangName;
    private javax.swing.JComboBox cmbconductorid;
    private javax.swing.JComboBox cmbdriverid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblSumithLogo;
    public static javax.swing.JLabel txtMsg;
    private javax.swing.JPasswordField txtPwd;
    // End of variables declaration//GEN-END:variables
}
