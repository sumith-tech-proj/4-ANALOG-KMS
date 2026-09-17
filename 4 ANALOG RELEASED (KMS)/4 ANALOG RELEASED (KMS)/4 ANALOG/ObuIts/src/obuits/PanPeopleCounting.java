
package obuits;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
public class PanPeopleCounting extends javax.swing.JPanel {
    JSpinner spinStartTime = new JSpinner();
    JDateChooser dateChooserFrom = new JDateChooser();

    DefaultTableModel model;

    public PanPeopleCounting() {
        initComponents();
        Date date;
        String[] data;
        String[] split_str;
        int i = 0;
        int j = 0;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf_time = new SimpleDateFormat("HHmm");
        final SimpleDateFormat sdf_datetime = new SimpleDateFormat("yyyyMMddHHmm");
        long from_date_int = 0;
        long val;
        Date time_start;

        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        time_start = date;
        try {
            time_start = sdf_time.parse("0000");
        } catch (ParseException ex) {
        }
        Font dateFont = new java.awt.Font("Ubuntu", 1, 18);
        dateChooserFrom.setFont(dateFont);
        dateChooserFrom.setDate(date);
        dateChooserFrom.setBounds(10, 5, 150, 30);
        panFromDateTime.add(dateChooserFrom);

        SpinnerDateModel timeModel = new SpinnerDateModel();
        spinStartTime.setModel(timeModel);

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinStartTime, "HH:mm");

        spinStartTime.setEditor(timeEditor);
        spinStartTime.setValue(time_start);
        spinStartTime.setEnabled(true);
        spinStartTime.setFont(dateFont);
        spinStartTime.setBounds(170, 5, 80, 30);
        panFromDateTime.add(spinStartTime);
        model = new DefaultTableModel(5, 0);
        model.addColumn("Sno");
        model.addColumn("Entry Time");
        model.addColumn("Exit Time");
        model.addColumn("IN");
        model.addColumn("OUT");
        model.addColumn("Stop Name");
        model.addColumn("Route No");
        tableApc.removeAll();
        model.setNumRows(10);
        tableApc.setModel(model);
        tableApc.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableApc.getColumnModel().getColumn(3).setPreferredWidth(20);
        tableApc.getColumnModel().getColumn(4).setPreferredWidth(20);

        String dateTimeFrom = sdf.format(dateChooserFrom.getDate()) + sdf_time.format(spinStartTime.getValue());
        try {
            from_date_int = sdf_datetime.parse(dateTimeFrom).getTime();
        } catch (ParseException ex) {
        }

        clsReadDataFiles obj = new clsReadDataFiles();
        data = obj.read_apc_route_data();

        tableApc.removeAll();
        model.setNumRows(0);
        if (data != null) {
            for (i = 0; i < data.length; i++) {
                split_str = data[i].split(",");
                val = Long.parseLong(split_str[0]);
                if (val >= from_date_int) {
                    model.setNumRows(data.length - i);
                    for (; i < data.length; i++) {
                        split_str = data[i].split(",", -1);
                        if (split_str.length >= 7) {
                            tableApc.setValueAt(j + 1, j, 0);
                            tableApc.setValueAt(split_str[1], j, 1);
                            tableApc.setValueAt(split_str[2], j, 2);
                            tableApc.setValueAt(split_str[3], j, 3);
                            tableApc.setValueAt(split_str[4], j, 4);
                            tableApc.setValueAt(split_str[5], j, 5);
                            tableApc.setValueAt(split_str[6], j, 6);
                            j++;
                        }
                    }
                }
            }
        }
        jScrollPane1.setVisible(true);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        date = null;
        calendar = null;
        timeModel = null;
        timeEditor = null;
        dateFont = null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableApc = new javax.swing.JTable();
        lblVideoConfigName = new javax.swing.JTextField();
        panCamDate = new javax.swing.JPanel();
        panFromDateTime = new javax.swing.JPanel();
        cmbSelect = new javax.swing.JComboBox<>();
        btnShow = new javax.swing.JButton();
        cmdApcSelect = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(555, 430));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(490, 600));

        tableApc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sno", "Entry Time", "Exit Time", "IN", "OUT", "Stop Name", "Route No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableApc.setPreferredSize(new java.awt.Dimension(490, 1500));
        tableApc.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(tableApc);

        lblVideoConfigName.setEditable(false);
        lblVideoConfigName.setBackground(new java.awt.Color(23, 29, 32));
        lblVideoConfigName.setFont(lblVideoConfigName.getFont().deriveFont(lblVideoConfigName.getFont().getStyle() | java.awt.Font.BOLD, lblVideoConfigName.getFont().getSize()+10));
        lblVideoConfigName.setForeground(new java.awt.Color(255, 255, 255));
        lblVideoConfigName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblVideoConfigName.setText("People Counting");
        lblVideoConfigName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblVideoConfigNameActionPerformed(evt);
            }
        });

        panCamDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panFromDateTime.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panFromDateTime.setFont(panFromDateTime.getFont().deriveFont(panFromDateTime.getFont().getSize()+4f));

        javax.swing.GroupLayout panFromDateTimeLayout = new javax.swing.GroupLayout(panFromDateTime);
        panFromDateTime.setLayout(panFromDateTimeLayout);
        panFromDateTimeLayout.setHorizontalGroup(
            panFromDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        panFromDateTimeLayout.setVerticalGroup(
            panFromDateTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        cmbSelect.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Route", "Day" }));
        cmbSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectActionPerformed(evt);
            }
        });

        btnShow.setBackground(new java.awt.Color(47, 49, 51));
        btnShow.setFont(btnShow.getFont().deriveFont(btnShow.getFont().getStyle() | java.awt.Font.BOLD, btnShow.getFont().getSize()+4));
        btnShow.setForeground(new java.awt.Color(255, 255, 255));
        btnShow.setText("Show");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        cmdApcSelect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmdApcSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FRONT CAMERA", "BACK CAMERA" }));

        javax.swing.GroupLayout panCamDateLayout = new javax.swing.GroupLayout(panCamDate);
        panCamDate.setLayout(panCamDateLayout);
        panCamDateLayout.setHorizontalGroup(
            panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCamDateLayout.createSequentialGroup()
                .addGroup(panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdApcSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panFromDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panCamDateLayout.setVerticalGroup(
            panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCamDateLayout.createSequentialGroup()
                .addGroup(panCamDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbSelect, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panFromDateTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdApcSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblVideoConfigName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panCamDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblVideoConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panCamDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panCamDate.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void lblVideoConfigNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblVideoConfigNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblVideoConfigNameActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf_time = new SimpleDateFormat("HHmm");
        final SimpleDateFormat sdf_datetime = new SimpleDateFormat("yyyyMMddHHmm");
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        long from_date_int = 0;
        Calendar cal = Calendar.getInstance();
        long val;
        String[] data;
        String[] split_str;
        int i = 0;
        int j = 0;
        //day
        String dateTimeFrom = sdf.format(dateChooserFrom.getDate()) + sdf_time.format(spinStartTime.getValue());
        try {
            from_date_int = sdf_datetime.parse(dateTimeFrom).getTime();
        } catch (ParseException ex) {
        }
       clsReadDataFiles obj = new clsReadDataFiles();

        if (cmdApcSelect.getSelectedIndex() == 0) {

            if (cmbSelect.getSelectedIndex() == 0) {
                data = obj.read_apc_route_data();
                tableApc.removeAll();
                model.setNumRows(0);
                if (data != null) {
                    for (i = 0; i < data.length; i++) {
                        split_str = data[i].split(",");
                        val = Long.parseLong(split_str[0]);
                        if (val >= from_date_int) {
                            model.setNumRows(data.length - i);
                            for (; i < data.length; i++) {
                                split_str = data[i].split(",", -1);
                                if (split_str.length >= 7) {
                                    tableApc.setValueAt(j + 1, j, 0);
                                    tableApc.setValueAt(split_str[1], j, 1);
                                    tableApc.setValueAt(split_str[2], j, 2);
                                    tableApc.setValueAt(split_str[3], j, 3);
                                    tableApc.setValueAt(split_str[4], j, 4);
                                    tableApc.setValueAt(split_str[5], j, 5);
                                    tableApc.setValueAt(split_str[6], j, 6);
                                    j++;
                                }

                            }
                            break;
                        }
                    }
                }
            }  else if (cmbSelect.getSelectedIndex() == 1) {
                data = obj.read_apc_day_stored_data();
                tableApc.removeAll();
                model.setNumRows(0);
                if (data != null) {
                    for (i = 0; i < data.length; i++) {
                        split_str = data[i].split(",");
                        val = Long.parseLong(split_str[0]);
                        if (val >= from_date_int) {
                            model.setNumRows(data.length - i);
                            for (; i < data.length; i++) {
                                split_str = data[i].split(",", -1);
                                if (split_str.length >= 4) {
                                    val = Long.parseLong(split_str[0]);
                                    cal.setTimeInMillis(val);
                                    tableApc.setValueAt(j + 1, j, 0);
                                    tableApc.setValueAt(sdf1.format(cal.getTime()), j, 1);
                                    tableApc.setValueAt(split_str[3], j, 2);
                                    tableApc.setValueAt(split_str[4], j, 3);
                                    j++;
                                }

                            }
                            break;
                        }
                    }
                }
            }
        } else {
            if (cmbSelect.getSelectedIndex() == 0) {
                data = obj.read_apc_route_data1();
                // System.out.println("data");
                tableApc.removeAll();
                model.setNumRows(0);
                if (data != null) {
                    for (i = 0; i < data.length; i++) {
                        split_str = data[i].split(",");
                        val = Long.parseLong(split_str[0]);
                        if (val >= from_date_int) {
                            model.setNumRows(data.length - i);
                            for (; i < data.length; i++) {
                                split_str = data[i].split(",", -1);
                                if (split_str.length >= 7) {
                                    tableApc.setValueAt(j + 1, j, 0);
                                    tableApc.setValueAt(split_str[1], j, 1);
                                    tableApc.setValueAt(split_str[2], j, 2);
                                    tableApc.setValueAt(split_str[3], j, 3);
                                    tableApc.setValueAt(split_str[4], j, 4);
                                    tableApc.setValueAt(split_str[5], j, 5);
                                    tableApc.setValueAt(split_str[6], j, 6);
                                    j++;
                                }

                            }
                            break;
                        }
                    }
                }
            }  else if (cmbSelect.getSelectedIndex() == 1) {
                data = obj.read_apc_day_stored_data1();
                tableApc.removeAll();
                model.setNumRows(0);
                if (data != null) {
                    for (i = 0; i < data.length; i++) {
                        split_str = data[i].split(",");
                        val = Long.parseLong(split_str[0]);
                        if (val >= from_date_int) {
                            //  cal.setTimeInMillis(val);
                            model.setNumRows(data.length - i);
                            for (; i < data.length; i++) {
                                split_str = data[i].split(",", -1);
                                if (split_str.length >= 4) {
                                    val = Long.parseLong(split_str[0]);
                                    cal.setTimeInMillis(val);
                                    tableApc.setValueAt(j + 1, j, 0);
                                    tableApc.setValueAt(sdf1.format(cal.getTime()), j, 1);
                                    tableApc.setValueAt(split_str[3], j, 2);
                                    tableApc.setValueAt(split_str[4], j, 3);
                                    //  tableApc.setValueAt(split_str[4], j, 4);
                                    j++;
                                }

                            }
                            break;
                        }
                    }
                }
            }
        }

        data = null;
        split_str = null;
        dateTimeFrom = null;
        obj = null;
        obj = null;
    }//GEN-LAST:event_btnShowActionPerformed

    private void cmbSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectActionPerformed
        // TODO add your handling code here:
        if (cmbSelect.getSelectedIndex() == 0) {
            model.setColumnCount(0);
            model.addColumn("Sno");
            model.addColumn("Entry Time");
            model.addColumn("Exit Time");
            model.addColumn("IN");
            model.addColumn("OUT");
            model.addColumn("Stop Name");
            model.addColumn("Route No");
            model.setNumRows(10);
            tableApc.setModel(model);
        }  else if (cmbSelect.getSelectedIndex() == 1) {
            model.setColumnCount(0);
            model.addColumn("Sno");
            model.addColumn("Date");
            model.addColumn("IN");
            model.addColumn("OUT");
            model.setNumRows(10);
            tableApc.setModel(model);

        }


    }//GEN-LAST:event_cmbSelectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox<String> cmbSelect;
    private javax.swing.JComboBox<String> cmdApcSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblVideoConfigName;
    private javax.swing.JPanel panCamDate;
    private javax.swing.JPanel panFromDateTime;
    private javax.swing.JTable tableApc;
    // End of variables declaration//GEN-END:variables
}
