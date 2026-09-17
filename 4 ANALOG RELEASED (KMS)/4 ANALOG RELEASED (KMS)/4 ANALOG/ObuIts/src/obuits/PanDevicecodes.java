package obuits;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import static obuits.clsSharedVariables.boot_ldr_version;
import static obuits.clsSharedVariables.compilation_fw_datetime;
import static obuits.clsSharedVariables.cpu_part_no;
import static obuits.clsSharedVariables.cpu_qualification;
import static obuits.clsSharedVariables.cpu_temp_range;
import static obuits.clsSharedVariables.font_lib_version;
import static obuits.clsSharedVariables.gps_antenna_error_cnt;
import static obuits.clsSharedVariables.gps_invalid_data_cnt;
import static obuits.clsSharedVariables.gps_lost_comm_cnt;
import static obuits.clsSharedVariables.hw_revision;
import static obuits.clsSharedVariables.obu_high_volt_cnt;
import static obuits.clsSharedVariables.obu_low_volt_cnt;
import static obuits.clsSharedVariables.obu_low_volt_reset_cnt;
import static obuits.clsSharedVariables.obu_over_heat_cnt;
import static obuits.clsSharedVariables.obu_watchdog_reset_cnt;
import static obuits.clsSharedVariables.serial_no;
import static obuits.clsSharedVariables.test_date_time_pid;
import static obuits.clsSharedVariables.usb_invalid_cnt;
import static obuits.clsSharedVariables.usb_invalid_filesystem_cnt;
import static obuits.clsSharedVariables.usb_overcurrent_cnt;
import static obuits.clsSharedVariables.usb_unknown_cnt;

public class PanDevicecodes extends javax.swing.JPanel {

    public PanDevicecodes() {
        initComponents();
        clsReadFiles obj = new clsReadFiles();
        obj.read_pid_codes();
        obj = null;
        byte row_inc = 0;
        String firmwareCam1 = null;
        String firmwareCam5 = null;
//        clsPeopleCntEventClear objfetch = new clsPeopleCntEventClear();
        try {
            tabDevicePidCodes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
            tabDevicePidCodes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabDevicePidCodes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabDevicePidCodes.setValueAt(String.valueOf(hw_revision), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(serial_no), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(boot_ldr_version), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(clsDefines.SW_16833_FIRMWARE_VERSION), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(font_lib_version), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(cpu_part_no), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(cpu_qualification), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(cpu_temp_range), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(compilation_fw_datetime), row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(test_date_time_pid), row_inc++, 1);
            tabDevicePidCodes.setValueAt(clsSharedVariables.os_version, row_inc++, 1);
            tabDevicePidCodes.setValueAt(String.valueOf(clsDefines.SW_FIRMWARE_VERSION), row_inc++, 1);
//            try {
//                if (clsSharedVariables.getCam6Type() == clsDefines.CAM_FOORIR) {
//                    firmwareCam1 = objfetch.extractFirmwareVer(objfetch.fetchFirmwareVersion((byte) 6));
//                }
//            } catch (Exception ex) {
//
//            }
//            try {
//                if (clsSharedVariables.getCam7Type() == clsDefines.CAM_FOORIR) {
//                    firmwareCam5 = objfetch.extractFirmwareVer(objfetch.fetchFirmwareVersion((byte) 7));
//                }
//            } catch (Exception ex) {
//               // System.out.println(ex.getMessage());
//            }
            tabDevicePidCodes.setValueAt(firmwareCam1, row_inc++, 1);
            tabDevicePidCodes.setValueAt(firmwareCam5, row_inc++, 1);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            firmwareCam5 = null;
            firmwareCam1 = null;
          //  objfetch = null;
        }

        jScrollPane3.getVerticalScrollBar()
                .setPreferredSize(new Dimension(15, 0));
        DefaultTableModel model = new DefaultTableModel();
        byte row_inc1 = 0;

        tabDeviceDtcCodes.getTableHeader()
                .setFont(new Font("SansSerif", Font.BOLD, 16));
        tabDeviceDtcCodes.getTableHeader()
                .setFont(new Font("SansSerif", Font.BOLD, 16));
        tabDeviceDtcCodes.getColumnModel()
                .getColumn(0).setPreferredWidth(100);
        tabDeviceDtcCodes.getColumnModel()
                .getColumn(1).setPreferredWidth(100);

        tabDeviceDtcCodes.setValueAt(String.valueOf(obu_watchdog_reset_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(obu_low_volt_reset_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(gps_lost_comm_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(gps_invalid_data_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(gps_antenna_error_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(usb_invalid_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(usb_unknown_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(usb_invalid_filesystem_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(usb_overcurrent_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(obu_high_volt_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(obu_low_volt_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(obu_over_heat_cnt), row_inc1++, 1);
        tabDeviceDtcCodes.setValueAt(String.valueOf(clsSharedVariables.no_times_reset), row_inc1++, 1);
        jScrollPane1.getVerticalScrollBar()
                .setPreferredSize(new Dimension(15, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabDevicePidCodes = new javax.swing.JTable();
        lblVideoConfigName1 = new javax.swing.JTextField();
        btnVersion = new javax.swing.JButton();
        btnStatus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabDeviceDtcCodes = new javax.swing.JTable();
        lblVideoConfigName = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(505, 430));

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(500, 350));

        tabDevicePidCodes.setBackground(new java.awt.Color(240, 240, 240));
        tabDevicePidCodes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabDevicePidCodes.setFont(tabDevicePidCodes.getFont().deriveFont(tabDevicePidCodes.getFont().getSize()+5f));
        tabDevicePidCodes.setForeground(new java.awt.Color(0, 0, 102));
        tabDevicePidCodes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"HARDWARE REVISION", "1.0"},
                {"SERIAL NUMBER", "SEPL-1234"},
                {"BOOT LOADER S/W REVISION", "1.0"},
                {"APPLICATION S/W REVISION", "1.0"},
                {"FONT LIBRARY REVISION", "1.0"},
                {"CPU PART NUMBER", "100"},
                {"CPU QUALIFICATION", "12"},
                {"CPU TEMP RANGE", "-25 TO 85"},
                {"COMP F/W DATE & TIME", ""},
                {"TEST DATE & TIME", null},
                {"KERNEL VERSION", null},
                {"OBU VERSION", "1.0"},
                {"FORRIR_CAM6_VERSION", "V4"},
                {"FORRIR_CAM7_VERSION", null},
                {null, null}
            },
            new String [] {
                "Parameter Name", "Value"
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
        tabDevicePidCodes.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabDevicePidCodes.setPreferredSize(new java.awt.Dimension(500, 500));
        tabDevicePidCodes.setRequestFocusEnabled(false);
        tabDevicePidCodes.setRowHeight(30);
        tabDevicePidCodes.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabDevicePidCodes);

        lblVideoConfigName1.setEditable(false);
        lblVideoConfigName1.setBackground(new java.awt.Color(23, 29, 32));
        lblVideoConfigName1.setFont(lblVideoConfigName1.getFont().deriveFont(lblVideoConfigName1.getFont().getStyle() | java.awt.Font.BOLD, lblVideoConfigName1.getFont().getSize()+10));
        lblVideoConfigName1.setForeground(new java.awt.Color(255, 255, 255));
        lblVideoConfigName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblVideoConfigName1.setText("PID CODES");
        lblVideoConfigName1.setPreferredSize(new java.awt.Dimension(500, 32));

        btnVersion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnVersion.setText("VERSION");
        btnVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVersionActionPerformed(evt);
            }
        });

        btnStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnStatus.setText("STATUS");
        btnStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblVideoConfigName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(btnVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblVideoConfigName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVersion)
                    .addComponent(btnStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PID CODES", jPanel1);

        jPanel2.setBackground(new java.awt.Color(23, 29, 32));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 434));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 402));

        tabDeviceDtcCodes.setBackground(new java.awt.Color(240, 240, 240));
        tabDeviceDtcCodes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabDeviceDtcCodes.setFont(tabDeviceDtcCodes.getFont().deriveFont(tabDeviceDtcCodes.getFont().getSize()+5f));
        tabDeviceDtcCodes.setForeground(new java.awt.Color(0, 0, 102));
        tabDeviceDtcCodes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"WATCHDOG RESET", null},
                {"LOW VOLTAGE RESET", null},
                {"GPS LOST", null},
                {"GPS INVALID DATA", null},
                {"GPS ANTENNA ERROR", null},
                {"USB INVALID", null},
                {"USB UNKNOWN", null},
                {"USB INVALID FILE SYSTEM", null},
                {"USB OVERCURRENT", null},
                {"OVER VOLTAGE", null},
                {"LOW VOLTAGE", null},
                {"OVER HEAT", null},
                {"NO RESETS", null},
                {null, null}
            },
            new String [] {
                "Parameter Name", "Value"
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
        tabDeviceDtcCodes.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabDeviceDtcCodes.setPreferredSize(new java.awt.Dimension(490, 500));
        tabDeviceDtcCodes.setRowHeight(30);
        tabDeviceDtcCodes.setRowSelectionAllowed(false);
        tabDeviceDtcCodes.getTableHeader().setResizingAllowed(false);
        tabDeviceDtcCodes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabDeviceDtcCodes);

        lblVideoConfigName.setEditable(false);
        lblVideoConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblVideoConfigName.setFont(lblVideoConfigName.getFont().deriveFont(lblVideoConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblVideoConfigName.getFont().getSize()+10));
        lblVideoConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblVideoConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblVideoConfigName.setText("DTC CODES");
        lblVideoConfigName.setPreferredSize(new java.awt.Dimension(500, 32));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVideoConfigName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(lblVideoConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        jTabbedPane1.addTab("DTC CODES", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVersionActionPerformed
        // TODO add your handling code here:
        if (clsSharedVariables.getIpAddr5Enable()) {
            if (clsSharedVariables.getSuratProtocol()) {
                clsSmcTrackingPackets objSmc = new clsSmcTrackingPackets();
                objSmc.event_pkt_smc(clsSharedVariables.version_pkt);
                objSmc = null;
            }
        }

    }//GEN-LAST:event_btnVersionActionPerformed

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
        // TODO add your handling code here:
        if (clsSharedVariables.getIpAddr5Enable()) {
            if (clsSharedVariables.getSuratProtocol()) {
                clsSmcTrackingPackets objSmc = new clsSmcTrackingPackets();
                objSmc.event_pkt_smc(clsSharedVariables.status_bdc);
                objSmc = null;
            }
        }
    }//GEN-LAST:event_btnStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStatus;
    private javax.swing.JButton btnVersion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lblVideoConfigName;
    private javax.swing.JTextField lblVideoConfigName1;
    public static javax.swing.JTable tabDeviceDtcCodes;
    public static javax.swing.JTable tabDevicePidCodes;
    // End of variables declaration//GEN-END:variables
}
