package obuits;

import java.text.SimpleDateFormat;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.SCHDEULE_ENABLE;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getSchRouteEnable;
import static obuits.clsSharedVariables.setAutoTripStat;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSharedVariables.setCurSchEndDate;
import static obuits.clsSharedVariables.setCurSchEndTime;
import static obuits.clsSharedVariables.setCurSchNoTrips;
import static obuits.clsSharedVariables.setCurSchRouteNo;
import static obuits.clsSharedVariables.setCurSchStartDate;
import static obuits.clsSharedVariables.setCurSchStartTime;
import static obuits.clsSharedVariables.setCurSchTripStatus;
import static obuits.clsSharedVariables.setCurTripNo;
import static obuits.clsSharedVariables.setCurTripStat;
import static obuits.clsSharedVariables.setSchId;
import static obuits.clsSharedVariables.setSchRouteEnable;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import static obuits.clsSharedVariables.getCurAutoTripStat;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getNoRoutes;
import static obuits.clsSharedVariables.getSchId;
import static obuits.clsSharedVariables.objRouteMasFiles;

public class panCreateSchedule extends javax.swing.JPanel {

    Process keypad_process;

    public panCreateSchedule() {
        initComponents();
        lblMsg.setText("");
        int i = 0;

        panEndDate.setVisible(false);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode2 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode3 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode4 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode5 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode6 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode7 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode8 = new DefaultComboBoxModel();
        DefaultComboBoxModel<String> mode9 = new DefaultComboBoxModel();

        for (i = 0; i < getNoRoutes(); i++) {
            model.addElement(objRouteMasFiles[i].route_no);
            mode2.addElement(objRouteMasFiles[i].route_no);
            mode3.addElement(objRouteMasFiles[i].route_no);
            mode4.addElement(objRouteMasFiles[i].route_no);
            mode5.addElement(objRouteMasFiles[i].route_no);
            mode6.addElement(objRouteMasFiles[i].route_no);
            mode7.addElement(objRouteMasFiles[i].route_no);
            mode8.addElement(objRouteMasFiles[i].route_no);
            mode9.addElement(objRouteMasFiles[i].route_no);

        }
        cmb1.setModel(model);
        cmb2.setModel(mode2);
        cmb3.setModel(mode3);
        cmb4.setModel(mode4);
        cmb5.setModel(mode5);
        cmb6.setModel(mode6);
        cmb7.setModel(mode7);
        cmb8.setModel(mode8);
        cmb9.setModel(mode9);

        SpinnerNumberModel spinModelNoTrips = new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1));

        spinModelNoTrips.setMinimum(1);
        spinModelNoTrips.setMaximum(10);
        cmbNoTrips.setModel(spinModelNoTrips);

        if (getSchRouteEnable() == ROUTE_ENABLE) {
            this.panScheduleBased.setVisible(false);
            chkAutoScheduling.setSelected(false);
            chkAutoScheduling.setText("Routebased");

            chkAutoTripDetect.setSelected(false);
            cmbNoTrips.setValue(getNoRoutes());

        } else {
            this.panScheduleBased.setVisible(true);
            txtSchId.setText(getSchId());
            chkPermTempSch.setSelected(true);
            chkAutoScheduling.setSelected(true);
            chkAutoScheduling.setText("Schedulebased");

            if (getCurAutoTripStat() == true) {
                chkAutoTripDetect.setSelected(true);
                chkAutoTripDetect.setText("Auto Trip");

            } else {
                chkAutoTripDetect.setSelected(false);
                chkAutoTripDetect.setText("Manual Trip");

                chkPermTempSch.setSelected(true);
                chkPermTempSch.setText("Permanent");

            }
            cmbNoTrips.setValue(getCurSchNoTrips());
            updateComboTripDetails((byte) getCurSchNoTrips());
        }

    }

    private void updateComboTripDetails(byte no_trips) {
        try {

            String rno;
            int j = 0;
            switch (no_trips) {
                case 1:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }

                    break;
                case 2:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 3:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 4:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(3);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb4.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 5:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(3);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb4.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(4);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb5.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 6:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(3);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb4.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(4);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb5.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(5);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb6.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 7:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(3);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb4.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(4);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb5.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(5);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb6.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(6);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb7.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 8:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(3);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb4.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(4);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb5.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(5);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb6.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(6);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb7.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(7);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb8.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;
                case 9:
                    rno = getCurSchRouteNo(0);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb1.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(1);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb2.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(2);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb3.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(3);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb4.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(4);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb5.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(5);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb6.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(6);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb7.setSelectedIndex(j);
                            break;
                        }
                    }

                    rno = getCurSchRouteNo(7);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb8.setSelectedIndex(j);
                            break;
                        }
                    }
                    rno = getCurSchRouteNo(8);
                    for (j = 0; j < getNoRoutes(); j++) {
                        if (rno.equals(objRouteMasFiles[j].route_no)) {
                            cmb9.setSelectedIndex(j);
                            break;
                        }
                    }
                    break;

            }

        } catch (Exception ex) {

        }

    }

    private String get_stop_names(byte no_trips) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            switch (no_trips) {
                case 1:
                    sb.append(cmb1.getSelectedItem().toString());
                    break;
                case 2:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    break;
                case 3:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    break;
                case 4:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb4.getSelectedItem().toString());
                    break;
                case 5:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb4.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb5.getSelectedItem().toString());
                    break;
                case 6:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb4.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb5.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb6.getSelectedItem().toString());
                    break;
                case 7:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb4.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb5.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb6.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb7.getSelectedItem().toString());
                    break;
                case 8:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb4.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb5.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb6.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb7.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb8.getSelectedItem().toString());
                    break;
                case 9:
                    sb.append(cmb1.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb2.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb3.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb4.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb5.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb6.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb7.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb8.getSelectedItem().toString());
                    sb.append(",");
                    sb.append(cmb9.getSelectedItem().toString());
                    break;

            }
            return sb.toString();
        } catch (Exception ex) {

        }
        return "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        chkAutoScheduling = new javax.swing.JCheckBox();
        panScheduleBased = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        chkAutoTripDetect = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        chkPermTempSch = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        txtSchId = new javax.swing.JTextField();
        panStartDate = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        spinStartDate = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        cmbNoTrips = new javax.swing.JSpinner();
        panEndDate = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        spinEndDate = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmb1 = new javax.swing.JComboBox();
        cmb2 = new javax.swing.JComboBox();
        cmb3 = new javax.swing.JComboBox();
        cmb4 = new javax.swing.JComboBox();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        cmb5 = new javax.swing.JComboBox();
        cmb6 = new javax.swing.JComboBox();
        cmb7 = new javax.swing.JComboBox();
        cmb8 = new javax.swing.JComboBox();
        cmb9 = new javax.swing.JComboBox();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();

        btnSave.setBackground(new java.awt.Color(47, 49, 51));
        btnSave.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+3));
        jLabel2.setText("Schedule Type ");

        chkAutoScheduling.setFont(chkAutoScheduling.getFont().deriveFont(chkAutoScheduling.getFont().getSize()+3f));
        chkAutoScheduling.setText("Route Based");
        chkAutoScheduling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAutoSchedulingActionPerformed(evt);
            }
        });

        panScheduleBased.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panScheduleBased.setPreferredSize(new java.awt.Dimension(600, 392));

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD, jLabel3.getFont().getSize()+2));
        jLabel3.setText("Auto Trip Detection");

        chkAutoTripDetect.setFont(chkAutoTripDetect.getFont().deriveFont(chkAutoTripDetect.getFont().getSize()+2f));
        chkAutoTripDetect.setText("Manual");
        chkAutoTripDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAutoTripDetectActionPerformed(evt);
            }
        });

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, jLabel4.getFont().getSize()+2));
        jLabel4.setText("Schedule");

        chkPermTempSch.setFont(chkPermTempSch.getFont().deriveFont(chkPermTempSch.getFont().getSize()+2f));
        chkPermTempSch.setText("Permanent");
        chkPermTempSch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPermTempSchActionPerformed(evt);
            }
        });

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD, jLabel5.getFont().getSize()+2));
        jLabel5.setText("Schedule ID");

        txtSchId.setFont(txtSchId.getFont().deriveFont(txtSchId.getFont().getSize()+2f));
        txtSchId.setText("01");
        txtSchId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSchIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSchIdFocusLost(evt);
            }
        });

        panStartDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getStyle() | java.awt.Font.BOLD, jLabel6.getFont().getSize()+2));
        jLabel6.setText("Start Date");

        spinStartDate.setFont(spinStartDate.getFont().deriveFont(spinStartDate.getFont().getSize()+4f));
        spinStartDate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(1533062160000L), null, java.util.Calendar.HOUR));
        spinStartDate.setFocusable(false);
        spinStartDate.setMaximumSize(new java.awt.Dimension(31, 31));
        spinStartDate.setName(""); // NOI18N
        spinStartDate.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panStartDateLayout = new javax.swing.GroupLayout(panStartDate);
        panStartDate.setLayout(panStartDateLayout);
        panStartDateLayout.setHorizontalGroup(
            panStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panStartDateLayout.createSequentialGroup()
                .addGroup(panStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panStartDateLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6))
                    .addGroup(panStartDateLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(spinStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panStartDateLayout.setVerticalGroup(
            panStartDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panStartDateLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spinStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getStyle() | java.awt.Font.BOLD, jLabel8.getFont().getSize()+2));
        jLabel8.setText("No Trips");

        cmbNoTrips.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbNoTrips.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cmbNoTripsStateChanged(evt);
            }
        });
        cmbNoTrips.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cmbNoTripsInputMethodTextChanged(evt);
            }
        });

        panEndDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panEndDate.setPreferredSize(new java.awt.Dimension(341, 46));

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getStyle() | java.awt.Font.BOLD, jLabel7.getFont().getSize()+2));
        jLabel7.setText("End Date");

        spinEndDate.setFont(spinEndDate.getFont().deriveFont(spinEndDate.getFont().getSize()+4f));
        spinEndDate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(1533062160000L), null, java.util.Calendar.HOUR));
        spinEndDate.setFocusable(false);
        spinEndDate.setMaximumSize(new java.awt.Dimension(31, 31));
        spinEndDate.setName(""); // NOI18N
        spinEndDate.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panEndDateLayout = new javax.swing.GroupLayout(panEndDate);
        panEndDate.setLayout(panEndDateLayout);
        panEndDateLayout.setHorizontalGroup(
            panEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spinEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel7)
        );
        panEndDateLayout.setVerticalGroup(
            panEndDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panEndDateLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spinEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getStyle() | java.awt.Font.BOLD, jLabel9.getFont().getSize()+1));
        jLabel9.setText("Trip No");

        jLabel10.setFont(jLabel10.getFont().deriveFont(jLabel10.getFont().getStyle() | java.awt.Font.BOLD, jLabel10.getFont().getSize()+1));
        jLabel10.setText("Route ID");

        cmb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl1.setText("1");

        lbl2.setText("2");

        lbl3.setText("3");

        lbl4.setText("4");

        cmb5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl5.setText("5");

        lbl6.setText("6");

        lbl7.setText("7");

        lbl8.setText("8");

        lbl9.setText("9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel9)
                        .addGap(77, 77, 77)
                        .addComponent(jLabel10)))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cmb1, cmb2, cmb3, cmb4, cmb5, cmb6, cmb7, cmb8, cmb9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl6)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmb6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl7)
                        .addGap(18, 18, 18)
                        .addComponent(lbl8)
                        .addGap(12, 12, 12)
                        .addComponent(lbl9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmb7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout panScheduleBasedLayout = new javax.swing.GroupLayout(panScheduleBased);
        panScheduleBased.setLayout(panScheduleBasedLayout);
        panScheduleBasedLayout.setHorizontalGroup(
            panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panScheduleBasedLayout.createSequentialGroup()
                .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panScheduleBasedLayout.createSequentialGroup()
                        .addComponent(panStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panScheduleBasedLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panScheduleBasedLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(3, 3, 3)
                                .addComponent(chkAutoTripDetect)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkPermTempSch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panScheduleBasedLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSchId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22))
            .addGroup(panScheduleBasedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panScheduleBasedLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cmbNoTrips, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        panScheduleBasedLayout.setVerticalGroup(
            panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panScheduleBasedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAutoTripDetect)
                    .addComponent(jLabel3)
                    .addComponent(chkPermTempSch)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panScheduleBasedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbNoTrips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        lblMsg.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMsg.setForeground(new java.awt.Color(0, 102, 0));
        lblMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMsg.setText("Configuration Saved");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panScheduleBased, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(chkAutoScheduling)
                    .addContainerGap(223, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(panScheduleBased, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(chkAutoScheduling))
                    .addContainerGap(452, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

        byte no_trips;
        int i;
        int j = 0;

        String[] spilt_str;
        String[] sch_data = new String[clsDefines.MAX_NO_ROUTES * 2];
        short start_pkt = 0;

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        clsReadFiles objReadFiles = new clsReadFiles();

        if (chkAutoScheduling.isSelected()) {
            setSchRouteEnable(SCHDEULE_ENABLE);

        } else {
            setSchRouteEnable(ROUTE_ENABLE);

        }
        if (chkAutoTripDetect.isSelected()) {
            setAutoTripStat(chkAutoTripDetect.isSelected());
        } else {
            setAutoTripStat(false);
        }

        if (chkAutoScheduling.isSelected()) {
            setSchRouteEnable(SCHDEULE_ENABLE);
            chkPermTempSch.setSelected(true);
            sch_data[0] = this.txtSchId.getText().trim();
            if (sch_data[0].equals("")) {
                // Toast.makeText(this, "Please Enter Schedule ID", Toast.LENGTH_LONG);
                return;
            }
            if (chkPermTempSch.isSelected()) {
                String start_date = sdfDate.format(this.spinStartDate.getValue());
                String start_time = sdfTime.format(this.spinStartDate.getValue()) + ":00";
                sch_data[0] = txtSchId.getText();
                sch_data[1] = start_time;
                sch_data[2] = (start_date);// start_date.toString();
                sch_data[3] = cmbNoTrips.getValue().toString();
                no_trips = Byte.parseByte(sch_data[3]);
                sch_data[4] = get_stop_names(no_trips);
                if (sch_data[4].trim().equals("")) {
                    // Toast.makeText(this, "Please Enter Route ID's Information", Toast.LENGTH_LONG);
                    return;
                }

                spilt_str = sch_data[4].split(",");
                for (i = 0; i < spilt_str.length; i++) {
                    sch_data[i + 4] = spilt_str[i];
                }

                objReadFiles.write_data_to_perm_sch_file(sch_data, start_pkt);
            } else {
                String start_date = sdfDate.format(this.spinStartDate.getValue());
                String start_time = sdfTime.format(this.spinStartDate.getValue()) + ":00";
                sch_data[0] = this.txtSchId.getText();

                String end_date = sdfDate.format(this.spinEndDate.getValue());
                String end_time = sdfTime.format(this.spinEndDate.getValue()) + ":00";
                sch_data[1] = start_time;
                sch_data[2] = (start_date);//start_date.toString();
                sch_data[3] = end_time;
                sch_data[4] = end_date;// end_date.toString();
                sch_data[5] = cmbNoTrips.getValue().toString();

                no_trips = Byte.parseByte(sch_data[5]);
                sch_data[6] = get_stop_names(no_trips);
                if (sch_data[6].trim().equals("")) {
                    // Toast.makeText(this, "Please Enter Route ID's Information", Toast.LENGTH_LONG);
                    return;
                }

                spilt_str = sch_data[6].split(",");
                for (i = 0; i < spilt_str.length; i++) {
                    sch_data[i + 6] = spilt_str[i];
                }
                objReadFiles.write_data_to_temp_sch_file(sch_data, start_pkt);
            }
        } else {
            chkPermTempSch.setSelected(false);
            setSchRouteEnable(ROUTE_ENABLE);
            setCurSchNoTrips(getNoRoutes());
            setSchId("1");
            for (j = 0; j < getCurSchNoTrips(); j++) {
                setCurSchRouteNo(j, objRouteMasFiles[j].route_no);
                setCurSchTripStatus(j, TRIP_UNKNOWN);
            }

            final Calendar cal = Calendar.getInstance();
            setCurSchStartDate(cal.getTime());
            setCurSchStartTime(cal.getTime().getTime());
            cal.add(Calendar.DATE, 1);
            setCurSchEndDate(cal.getTime());
            setCurSchEndTime(cal.getTime().getTime());
            setCurTripNo((byte) 0);
            setCurTripStat(TRIP_UNKNOWN);
            setCurRouteStat(false);
            objReadFiles.write_cur_route_info_file();
            objReadFiles.write_cur_trip_info_file();
            objReadFiles.write_cur_schedule_file();
            final ClsSchedule objSch = new ClsSchedule();
            if (getNoRoutes() > 0) {
                objSch.send_route_info();
            }
        }
        if (objReadFiles.write_cfg_data_file()) {
            this.lblMsg.setText("Configuration Saved");

        } else {
            this.lblMsg.setText("Configuration Not Saved");

        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void chkAutoSchedulingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAutoSchedulingActionPerformed
        // TODO add your handling code here:
        if (chkAutoScheduling.isSelected()) {
            chkAutoScheduling.setText("Schedulebased");
            panScheduleBased.setVisible(true);

        } else {
            chkAutoScheduling.setText("Routebased");
            panScheduleBased.setVisible(false);

        }
    }//GEN-LAST:event_chkAutoSchedulingActionPerformed

    private void chkAutoTripDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAutoTripDetectActionPerformed
        // TODO add your handling code here:
        if (chkAutoTripDetect.isSelected()) {
            chkAutoTripDetect.setText("Auto Trip");
        } else {
            chkAutoTripDetect.setText("Manual Trip");

        }
    }//GEN-LAST:event_chkAutoTripDetectActionPerformed

    private void chkPermTempSchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPermTempSchActionPerformed
        // TODO add your handling code here:
        if (chkPermTempSch.isSelected()) {
            chkPermTempSch.setText("Permanent");
            panStartDate.setVisible(true);
            panEndDate.setVisible(false);

        } else {
            panStartDate.setVisible(true);
            chkPermTempSch.setText("Temporary");
            panEndDate.setVisible(true);

        }
    }//GEN-LAST:event_chkPermTempSchActionPerformed

    private void txtSchIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSchIdFocusGained
        // TODO add your handling code here:

        String cmd = ("sudo /usr/bin/matchbox-keyboard");
        keypad_process = null;
        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            keypad_process = rt.exec(cmd);
        } catch (Exception e) {
            if (rt != null) {
                rt.gc();
            }
        } finally {
            try {
                if (keypad_process != null) {
                    keypad_process = null;
                    rt = null;
                }
            } catch (Exception ex) {
            }
        }
    }//GEN-LAST:event_txtSchIdFocusGained

    private void txtSchIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSchIdFocusLost
        // TODO add your handling code here:
        try {
            if (keypad_process != null) {
                keypad_process.destroy();
                keypad_process = null;
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_txtSchIdFocusLost

    private void cmbNoTripsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cmbNoTripsStateChanged
        // TODO add your handling code here:

        int no_trips = Integer.parseInt(cmbNoTrips.getValue().toString());
        switch (no_trips) {
            case 0:
                cmb1.setVisible(false);
                cmb2.setVisible(false);
                cmb3.setVisible(false);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(false);
                lbl2.setVisible(false);
                lbl3.setVisible(false);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 1:
                cmb1.setVisible(true);
                cmb2.setVisible(false);
                cmb3.setVisible(false);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(false);
                lbl3.setVisible(false);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 2:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(false);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(false);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 3:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 4:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 5:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 6:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 7:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(true);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(true);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 8:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(true);
                cmb8.setVisible(true);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(true);
                lbl8.setVisible(true);
                lbl9.setVisible(false);

                break;
            case 9:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(true);
                cmb8.setVisible(true);
                cmb9.setVisible(true);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(true);
                lbl8.setVisible(true);
                lbl9.setVisible(true);

                break;
        }
    }//GEN-LAST:event_cmbNoTripsStateChanged

    private void cmbNoTripsInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmbNoTripsInputMethodTextChanged
        // TODO add your handling code here:
        int no_trips = Integer.parseInt(cmbNoTrips.getValue().toString());
        switch (no_trips) {
            case 0:
                cmb1.setVisible(false);
                cmb2.setVisible(false);
                cmb3.setVisible(false);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(false);
                lbl2.setVisible(false);
                lbl3.setVisible(false);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 1:
                cmb1.setVisible(true);
                cmb2.setVisible(false);
                cmb3.setVisible(false);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(false);
                lbl3.setVisible(false);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 2:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(false);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(false);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 3:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(false);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(false);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 4:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(false);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(false);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 5:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(false);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(false);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 6:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(false);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(false);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 7:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(true);
                cmb8.setVisible(false);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(true);
                lbl8.setVisible(false);
                lbl9.setVisible(false);

                break;
            case 8:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(true);
                cmb8.setVisible(true);
                cmb9.setVisible(false);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(true);
                lbl8.setVisible(true);
                lbl9.setVisible(false);

                break;
            case 9:
                cmb1.setVisible(true);
                cmb2.setVisible(true);
                cmb3.setVisible(true);
                cmb4.setVisible(true);
                cmb5.setVisible(true);
                cmb6.setVisible(true);
                cmb7.setVisible(true);
                cmb8.setVisible(true);
                cmb9.setVisible(true);

                lbl1.setVisible(true);
                lbl2.setVisible(true);
                lbl3.setVisible(true);
                lbl4.setVisible(true);
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                lbl7.setVisible(true);
                lbl8.setVisible(true);
                lbl9.setVisible(true);

                break;
        }
    }//GEN-LAST:event_cmbNoTripsInputMethodTextChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkAutoScheduling;
    private javax.swing.JCheckBox chkAutoTripDetect;
    private javax.swing.JCheckBox chkPermTempSch;
    private javax.swing.JComboBox cmb1;
    private javax.swing.JComboBox cmb2;
    private javax.swing.JComboBox cmb3;
    private javax.swing.JComboBox cmb4;
    private javax.swing.JComboBox cmb5;
    private javax.swing.JComboBox cmb6;
    private javax.swing.JComboBox cmb7;
    private javax.swing.JComboBox cmb8;
    private javax.swing.JComboBox cmb9;
    private javax.swing.JSpinner cmbNoTrips;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JPanel panEndDate;
    private javax.swing.JPanel panScheduleBased;
    private javax.swing.JPanel panStartDate;
    private javax.swing.JSpinner spinEndDate;
    private javax.swing.JSpinner spinStartDate;
    private javax.swing.JTextField txtSchId;
    // End of variables declaration//GEN-END:variables
}
