
package obuits;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static obuits.clsCanAlertDisplayQueue.getCanAlertDisplayList;

public class PanCanAlerts extends javax.swing.JPanel {

    Timer timer;
    TimerTask timerTask;
    DefaultTableModel model;
    public PanCanAlerts() {
        initComponents();
        int i = 0;
        model = new DefaultTableModel();
        String str = getCanAlertDisplayList();
        String[] split_str = str.split(",");
        model.addColumn("Time");
        model.addColumn("Description");
        String[] data_split;
        int row = 0;
        model.setNumRows(split_str.length);
        tabCanAlerts.setModel(model);
        tabCanAlerts.getColumnModel().getColumn(1).setPreferredWidth(400);
        for (i = 0; i < split_str.length; i++) {
            try {
                if (split_str[i].length() > 5) {
                    split_str[i] = split_str[i].replace("[", "");
                    split_str[i] = split_str[i].replace("]", "");
                    data_split = split_str[i].split("\\*");
                    tabCanAlerts.setValueAt(data_split[0], row, 0);
                    tabCanAlerts.setValueAt(data_split[1], row, 1);
                    row++;
                }
            } catch (Exception ex) {
            }
        }
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        startTimer();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
    }

    public final void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 100);
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

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        String str = getCanAlertDisplayList();
                        String[] split_str = str.split(",");
                        String[] data_split;
                        int row = 0;
                        model.setNumRows(split_str.length);
                        tabCanAlerts.removeAll();
                        tabCanAlerts.setModel(model);
                        for (int i = 0; i < split_str.length; i++) {
                            try {
                                if (split_str[i].length() > 5) {
                                    split_str[i] = split_str[i].replace("[", "");
                                    split_str[i] = split_str[i].replace("]", "");
                                    data_split = split_str[i].split("\\*");
                                    tabCanAlerts.setValueAt(data_split[0], row, 0);
                                    tabCanAlerts.setValueAt(data_split[1], row, 1);
                                    row++;
                                }
                            } catch (Exception ex) {
                            }
                        }

                    }
                });
            }
        };
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabCanAlerts = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(665, 450));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+10));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("CAN Alerts List");

        tabCanAlerts.setBackground(new java.awt.Color(23, 29, 32));
        tabCanAlerts.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanAlerts.setFont(tabCanAlerts.getFont().deriveFont(tabCanAlerts.getFont().getSize()+5f));
        tabCanAlerts.setForeground(new java.awt.Color(255, 255, 255));
        tabCanAlerts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Time", "Description"
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
        tabCanAlerts.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanAlerts.setRowHeight(30);
        tabCanAlerts.setSelectionBackground(new java.awt.Color(255, 240, 185));
        tabCanAlerts.getTableHeader().setResizingAllowed(false);
        tabCanAlerts.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabCanAlerts);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabCanAlerts;
    // End of variables declaration//GEN-END:variables
}
