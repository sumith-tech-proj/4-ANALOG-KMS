/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package obuits;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import static obuits.clsDefines.mpv_found;

/**
 *
 * @author yaswa
 */
public class PanelAlcoBrake extends javax.swing.JPanel {
    JSpinner spinStartTime = new JSpinner();
    JDateChooser dateChooserFrom = new JDateChooser();
    DefaultTableModel model;
    boolean checkboxValue = false;
    public static Process play_video_process;
    static boolean video_playing = false;
    public static long play_process_id = 0;
    String[] split_str;
    String[] data;
    public PanelAlcoBrake() {
        initComponents();
        Date date;
        String str = null;
        long from_date_int = 0;
        long val;
        int i = 0;
        int j = 0;
        Date time_start;
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat sdf_time = new SimpleDateFormat("HHmm");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf_datetime = new SimpleDateFormat("yyyyMMddHHmm");
        final SimpleDateFormat sdf_input = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = calendar.getTime();
        time_start = date;
        try {
            time_start = sdf_time.parse("0000");
        } catch (ParseException ex) {
        }
        Font dateFont = new Font("Ubuntu", Font.BOLD, 18);
        dateChooserFrom.setFont(dateFont);
        dateChooserFrom.setDate(new Date()); // Set current date
        dateChooserFrom.setBounds(15, 5, 150, 30);
        panFromDateAlco.add(dateChooserFrom);
        SpinnerDateModel timeModel = new SpinnerDateModel();
        spinStartTime.setModel(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinStartTime, "HH:mm");
        spinStartTime.setEditor(timeEditor);
        spinStartTime.setValue(time_start);
        spinStartTime.setEnabled(true);
        spinStartTime.setFont(dateFont);
        spinStartTime.setBounds(170, 5, 80, 30);
        panFromDateAlco.add(spinStartTime);
        model = new DefaultTableModel(5, 0);
        model.addColumn("Sno");
        model.addColumn("Date and Time");
        model.addColumn("Result");
        model.addColumn("Image");
        tableAlcoDetails.setModel(model);
        tableAlcoDetails.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableAlcoDetails.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableAlcoDetails.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableAlcoDetails.getColumnModel().getColumn(3).setPreferredWidth(80);
        String dateTimeFrom = sdf.format(dateChooserFrom.getDate()) + sdf_time.format(spinStartTime.getValue());
        // Set the checkbox column renderer and editor
        tableAlcoDetails.getColumn("Image").setCellRenderer(tableAlcoDetails.getDefaultRenderer(Boolean.class));
        tableAlcoDetails.getColumn("Image").setCellEditor(tableAlcoDetails.getDefaultEditor(Boolean.class));
        try {
            from_date_int = sdf_datetime.parse(dateTimeFrom).getTime();
        } catch (ParseException ex) {
        }
        clsReadFiles obj = new clsReadFiles();
        data = obj.read_data_alco_log();
        tableAlcoDetails.removeAll();
        model.setNumRows(0);
        if (data != null) {
            for (; i < data.length; i++) {
                split_str = data[i].split(",");
                String milstr = split_str[0];
                try {
                    date = sdf_datetime.parse(milstr);
                } catch (ParseException ex) {
                }
                val = date.getTime();
                if (val >= from_date_int) {
                    model.setNumRows(data.length - i);
                    for (; i < data.length; i++) {
                        split_str = data[i].split(",");
                        try {
                            if (split_str.length == 4) {
                                tableAlcoDetails.setValueAt(j + 1, j, 0);
                                tableAlcoDetails.setValueAt(split_str[1], j, 1);
                                tableAlcoDetails.setValueAt(split_str[2], j, 2);
                                tableAlcoDetails.setValueAt(checkboxValue, j, 3);
                                j++;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        tableAlcoDetails.getColumn("Image").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Object getCellEditorValue() {
                boolean isSelected = (Boolean) super.getCellEditorValue();  // Get the checkbox state
                 split_str = null;
                clsReadFiles obj = new clsReadFiles();
                data = obj.read_data_alco_log();
                int selectedRow = tableAlcoDetails.getSelectedRow();
                boolean matchFound = false;
                long selectedMillis = 0;
                long dataMillis = 0;
                if (selectedRow != -1) {
                    String selectedDateTime = (String) tableAlcoDetails.getValueAt(selectedRow, 1);
                    System.out.println("selectedDateTime " + selectedDateTime);
                    try {
                        Date parsedDate = sdf_input.parse(selectedDateTime);
                        String formattedDate = sdf_datetime.format(parsedDate);
                        Date formattedDateParsed = sdf_datetime.parse(formattedDate);
                        selectedMillis = formattedDateParsed.getTime();
                        System.out.println("Selected time in milliseconds: " + selectedMillis);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < data.length; i++) {
                        split_str = data[i].split(",");
                        if (split_str.length >= 4) {
                            try {
                                Date parsedDate1 = sdf_datetime.parse(split_str[0]);
                                dataMillis = parsedDate1.getTime();
                                System.out.println("dataMillis: " + dataMillis);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (selectedMillis == dataMillis) {
                                String imagePath = split_str[3]; // The image path is in split_str[3]
                                System.out.println("Image path: " + imagePath);
                                // Run the command with the correct image path
                                runCommand(imagePath, selectedRow);
                                // Debugging outputs
                                System.out.println("Data matched at index: " + i);
                                System.out.println("Data at index: " + data[i]);
                                matchFound = true;
                                break;  // Exit loop once a match is found
                            }
                        }
                    }
                    if (isSelected) {
                        // Unselect all checkboxes except the one in the selected row
                        for (int row = 0; row < tableAlcoDetails.getRowCount(); row++) {
                            if (row != selectedRow) {
                                tableAlcoDetails.setValueAt(Boolean.FALSE, row, 3);  // Uncheck other checkboxes
                            }
                        }
                    }
                }
                return super.getCellEditorValue();  // Return the checkbox value
            }
            private void runCommand(String command, int selectedRow) {
                // Immediately update the checkbox state before starting the video
                tableAlcoDetails.setValueAt(Boolean.TRUE, selectedRow, 3);  // Set the checkbox as checked for the selected row
                // Ensure any previous video is stopped
                if (play_video_process != null) {
                    try {
                        // Kill the existing video process
                        Process killProcess = Runtime.getRuntime().exec("sudo killall -9 ffplay");
                        killProcess.waitFor();
                        play_video_process.destroy();
                    } catch (Exception ex) {
                    }
                }
                try {
                    play_video_process = Runtime.getRuntime().exec(new String[]{
                        "bash", "-c", "ffplay -left 80 -top 100 -x 660 -y 520 -an -noborder -window_title \" \" -nostats -sync video -max_delay 1 " + command
                    });
                    try {
                        Field f = play_video_process.getClass().getDeclaredField("pid");
                        f.setAccessible(true);
                        play_process_id = (int) f.get(play_video_process);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Thread.sleep(3000); // 3000 ms = 3 seconds
                    if (play_video_process != null) {
                        play_video_process.destroy();
                        play_video_process = null; // Clean up the process object
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            class OutStreamCamplayThread1 extends Thread {
                BufferedReader reader = null;
                public OutStreamCamplayThread1() {
                    super();
                }
                @Override
                public void run() {
                    String data;
                    try {
                        reader = new BufferedReader(new InputStreamReader(play_video_process.getErrorStream()));
                        while (true) {
                            if (reader.ready()) {
                                data = reader.readLine();
                                if (data.contains("have a nice day")) {
                                    break;
                                }
                                if (data.contains("route to host")) {
                                    break;
                                }
                            }
                            if (video_playing == false || play_video_process == null) {
                                break;
                            }
                            Thread.sleep(1000);
                        }
                    } catch (Exception ex) {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {

                        }
                    }
                    try {
                        Process p;
                        if (mpv_found == true) {
                            p = Runtime.getRuntime().exec("sudo pkill mpv");
                        } else {
                            p = Runtime.getRuntime().exec("sudo pkill ffplay");
                        }
                        p.waitFor();
                        p = null;
                        play_process_id = 0;
                        play_video_process = null;
                    } catch (Exception ex) {
                    }
                    try {
                        play_video_process.getInputStream().close();
                        play_video_process.getOutputStream().close();
                        play_video_process.getErrorStream().close();
                        play_video_process.destroyForcibly();
                        play_video_process.exitValue();
                        play_video_process = null;
                    } catch (Exception ex) {
                    }
                }
            }
        });
        jScrollPane1.setVisible(true);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        dateFont = null;
        data = null;
        split_str = null;
        obj = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panAlcoDate = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        showAlcoDetails = new javax.swing.JButton();
        panFromDateAlco = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAlcoDetails = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(555, 430));

        panAlcoDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panAlcoDate.setPreferredSize(new java.awt.Dimension(506, 96));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("DATE : ");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        showAlcoDetails.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        showAlcoDetails.setText("SHOW");
        showAlcoDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        showAlcoDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAlcoDetailsActionPerformed(evt);
            }
        });

        panFromDateAlco.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panFromDateAlco.setFont(panFromDateAlco.getFont().deriveFont(panFromDateAlco.getFont().getSize()+4f));

        javax.swing.GroupLayout panFromDateAlcoLayout = new javax.swing.GroupLayout(panFromDateAlco);
        panFromDateAlco.setLayout(panFromDateAlcoLayout);
        panFromDateAlcoLayout.setHorizontalGroup(
            panFromDateAlcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );
        panFromDateAlcoLayout.setVerticalGroup(
            panFromDateAlcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panAlcoDateLayout = new javax.swing.GroupLayout(panAlcoDate);
        panAlcoDate.setLayout(panAlcoDateLayout);
        panAlcoDateLayout.setHorizontalGroup(
            panAlcoDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAlcoDateLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(panFromDateAlco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showAlcoDetails)
                .addGap(32, 32, 32))
        );
        panAlcoDateLayout.setVerticalGroup(
            panAlcoDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAlcoDateLayout.createSequentialGroup()
                .addGroup(panAlcoDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panAlcoDateLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panFromDateAlco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panAlcoDateLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panAlcoDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showAlcoDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(490, 600));

        tableAlcoDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sno", "Time", "Result", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableAlcoDetails.setMinimumSize(new java.awt.Dimension(105, 100));
        tableAlcoDetails.setPreferredSize(new java.awt.Dimension(490, 1500));
        jScrollPane1.setViewportView(tableAlcoDetails);
        if (tableAlcoDetails.getColumnModel().getColumnCount() > 0) {
            tableAlcoDetails.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panAlcoDate, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panAlcoDate, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showAlcoDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAlcoDetailsActionPerformed
        long from_date_int = 0;
        String[] data;
        String[] split_str;
        String str = null;
        long val;
        int i = 0;
        int j = 0;
        Date date = null;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf_datetime = new SimpleDateFormat("yyyyMMddHHmm");
        final SimpleDateFormat sdf_time = new SimpleDateFormat("HHmm");
        final SimpleDateFormat sdf_input = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateTimeFrom = sdf.format(dateChooserFrom.getDate()) + sdf_time.format(spinStartTime.getValue());
        tableAlcoDetails.getColumn("Image").setCellRenderer(tableAlcoDetails.getDefaultRenderer(Boolean.class));
        tableAlcoDetails.getColumn("Image").setCellEditor(tableAlcoDetails.getDefaultEditor(Boolean.class));
        try {
            from_date_int = sdf_datetime.parse(dateTimeFrom).getTime();
        } catch (ParseException ex) {
        }
        clsReadFiles obj = new clsReadFiles();
        data = obj.read_data_alco_log();
        tableAlcoDetails.removeAll();
        model.setNumRows(0);
        if (data != null) {
            for (; i < data.length; i++) {
                split_str = data[i].split(",");
                String milstr = split_str[0];
                try {
                    date = sdf_datetime.parse(milstr);
                } catch (ParseException ex) {
                }
                val = date.getTime();
                if (val >= from_date_int) {
                    model.setNumRows(data.length - i);
                    for (; i < data.length; i++) {
                        split_str = data[i].split(",");
                        if (split_str.length == 4) {
                            tableAlcoDetails.setValueAt(j + 1, j, 0);
                            tableAlcoDetails.setValueAt(split_str[1], j, 1);
                            tableAlcoDetails.setValueAt(split_str[2], j, 2);
                            tableAlcoDetails.setValueAt(checkboxValue, j, 3);

                            j++;
                        }
                    }
                    break;
                }
            }
        }
        tableAlcoDetails.getColumn("Image").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Object getCellEditorValue() {
                boolean isSelected = (Boolean) super.getCellEditorValue();  // Get the checkbox state
                String[] split_str = null;
                clsReadFiles obj = new clsReadFiles();
                String[] data = obj.read_data_alco_log();  // Assuming this fetches the correct data
                int selectedRow = tableAlcoDetails.getSelectedRow();
                boolean matchFound = false;
                long selectedMillis = 0;
                long dataMillis = 0;
                if (selectedRow != -1) {
                    String selectedDateTime = (String) tableAlcoDetails.getValueAt(selectedRow, 1);
                    System.out.println("selectedDateTime " + selectedDateTime);
                    try {
                        Date parsedDate = sdf_input.parse(selectedDateTime);
                        String formattedDate = sdf_datetime.format(parsedDate);
                        Date formattedDateParsed = sdf_datetime.parse(formattedDate);
                        System.out.println("formattedDateParsed " + formattedDateParsed);
                        selectedMillis = formattedDateParsed.getTime(); // This is in milliseconds
                        System.out.println("Selected time in milliseconds: " + selectedMillis);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < data.length; i++) {
                        split_str = data[i].split(",");
                        if (split_str.length >= 4) {
                            try {
                                Date parsedDate1 = sdf_datetime.parse(split_str[0]);
                                dataMillis = parsedDate1.getTime();
                              //  System.out.println("dataMillis: " + dataMillis);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (selectedMillis == dataMillis) {
                                String imagePath = split_str[3]; // The image path is in split_str[3]
                                System.out.println("Image path: " + imagePath);
                                runCommand(imagePath, selectedRow);
                                // Debugging outputs
                                //System.out.println("Data matched at index: " + i);
                                //System.out.println("Data at index: " + data[i]);
                                matchFound = true;
                                break;  
                            }
                        }
                    }
                    if (isSelected) {
                        for (int row = 0; row < tableAlcoDetails.getRowCount(); row++) {
                            if (row != selectedRow) {
                                tableAlcoDetails.setValueAt(Boolean.FALSE, row, 3); 
                            }
                        }
                    }
                }
                return super.getCellEditorValue();
            }
            private void runCommand(String command, int selectedRow) {
                tableAlcoDetails.setValueAt(Boolean.TRUE, selectedRow, 3);
                if (play_video_process != null) {
                    try {
                        Process killProcess = Runtime.getRuntime().exec("sudo killall -9 ffplay");
                        killProcess.waitFor();
                        play_video_process.destroy();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    play_video_process = Runtime.getRuntime().exec(new String[]{
                        "bash", "-c", "ffplay -left 80 -top 100 -x 660 -y 520 -an -noborder -window_title \" \" -nostats -sync video -max_delay 1 " + command
                    });
                    try {
                        Field f = play_video_process.getClass().getDeclaredField("pid");
                        f.setAccessible(true);
                        play_process_id = (int) f.get(play_video_process);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Thread.sleep(3000);
                    if (play_video_process != null) {
                        play_video_process.destroy();
                        play_video_process = null;
                    }
                } catch (Exception e) {
                }
            }

            class OutStreamCamplayThread1 extends Thread {

                BufferedReader reader = null;

                public OutStreamCamplayThread1() {
                    super();
                }

                @Override
                public void run() {
                    String data;
                    try {
                        reader = new BufferedReader(new InputStreamReader(play_video_process.getErrorStream()));
                        while (true) {
                            if (reader.ready()) {
                                data = reader.readLine();
                                if (data.contains("have a nice day")) {
                                    break;
                                }
                                if (data.contains("route to host")) {
                                    break;
                                }
                            }
                            if (video_playing == false || play_video_process == null) {
                                break;
                            }
                            Thread.sleep(1000);
                        }

                    } catch (Exception ex) {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {

                        }
                    }
                    try {
                        Process p;
                        if (mpv_found == true) {
                            p = Runtime.getRuntime().exec("sudo pkill mpv");
                        } else {
                            p = Runtime.getRuntime().exec("sudo pkill ffplay");
                        }
                        p.waitFor();
                        p = null;
                        play_process_id = 0;
                        play_video_process = null;
                    } catch (Exception ex) {
                    }
                    try {
                        play_video_process.getInputStream().close();
                        play_video_process.getOutputStream().close();
                        play_video_process.getErrorStream().close();
                        play_video_process.destroyForcibly();
                        play_video_process.exitValue();
                        play_video_process = null;
                    } catch (Exception ex) {
                    }
                }
            }
        });
        data = null;
        split_str = null;
        obj = null;
        obj = null;
    }//GEN-LAST:event_showAlcoDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panAlcoDate;
    private javax.swing.JPanel panFromDateAlco;
    private javax.swing.JButton showAlcoDetails;
    private javax.swing.JTable tableAlcoDetails;
    // End of variables declaration//GEN-END:variables
}
