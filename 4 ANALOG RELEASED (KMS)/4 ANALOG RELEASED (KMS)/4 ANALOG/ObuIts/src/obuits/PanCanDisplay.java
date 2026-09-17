
package obuits;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import static obuits.clsSharedVariables.setCanElectricalVhmdScreen;
import static obuits.clsSharedVariables.setCanElectricalVhmdScreen1;
import static obuits.clsSharedVariables.setCanEngineVhmdScreen;
import static obuits.clsSharedVariables.setCanEngineVhmdScreen1;
import static obuits.clsSharedVariables.setCanOthersVhmdScreen;
import static obuits.clsSharedVariables.setCanSafetyVhmdScreen;
import static obuits.clsSharedVariables.setCanSafetyVhmdScreen1;
import static obuits.clsSharedVariables.setCanOthersVhmdScreen1;
import static obuits.clsSharedVariables.setCanTransmitVhmdScreen;
import static obuits.clsSharedVariables.setCanTransmitVhmdScreen1;
import static obuits.clsSharedVariables.setCanVhmdScreen;
import static obuits.clsSharedVariables.setCurActivity;

public class PanCanDisplay extends javax.swing.JPanel {

    static byte index = 0;
    static byte index1 = 0;
    final DecimalFormat decimalFormat = new DecimalFormat("##0.##");
    Color color_blue = new Color(85, 85, 85);
    Color color_green = new Color(50, 205, 50);

    public PanCanDisplay() {
        initComponents();
        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        int row = 0;
        this.btnOthers.setVisible(false);
        this.btnOthers1.setVisible(false);
        //can1 data
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanElectricalCnt);
            tabCanParamsElectrical.setModel(model);
            tabCanParamsElectrical.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsElectrical.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanElectricalCnt; i++) {
                if (PanCanConfig.objCanElectrical[i].display_enable) {
                    if (PanCanConfig.objCanElectrical[i].unit.equals("bit")) {
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].name, row, 0);
                        tabCanParamsElectrical.setValueAt((int) (PanCanConfig.objCanElectrical[i].value), row, 1);
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].unit, row, 2);
                    } else {
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].name, row, 0);
                        tabCanParamsElectrical.setValueAt(decimalFormat.format(PanCanConfig.objCanElectrical[i].value), row, 1);
                        tabCanParamsElectrical.setValueAt(PanCanConfig.objCanElectrical[i].unit, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanSafetyCnt);
            tabCanParamsSafety.setModel(model);
            tabCanParamsSafety.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsSafety.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanSafetyCnt; i++) {
                if (PanCanConfig.objCanSafety[i].display_enable) {
                    if (PanCanConfig.objCanSafety[i].unit.equals("bit")) {
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].name, row, 0);
                        tabCanParamsSafety.setValueAt((int) PanCanConfig.objCanSafety[i].value, row, 1);
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].unit, row, 2);
                    } else {
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].name, row, 0);
                        tabCanParamsSafety.setValueAt(decimalFormat.format(PanCanConfig.objCanSafety[i].value), row, 1);
                        tabCanParamsSafety.setValueAt(PanCanConfig.objCanSafety[i].unit, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;

        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanTransmitCnt);
            tabCanParamsTransmit.setModel(model);
            tabCanParamsTransmit.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsTransmit.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanTransmitCnt; i++) {
                if (PanCanConfig.objCanTransmit[i].display_enable) {
                    if (PanCanConfig.objCanTransmit[i].unit.equals("bit")) {
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].name, row, 0);
                        tabCanParamsTransmit.setValueAt((int) PanCanConfig.objCanTransmit[i].value, row, 1);
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].unit, row, 2);
                    } else {
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].name, row, 0);
                        tabCanParamsTransmit.setValueAt(decimalFormat.format(PanCanConfig.objCanTransmit[i].value), row, 1);
                        tabCanParamsTransmit.setValueAt(PanCanConfig.objCanTransmit[i].unit, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanEngineCnt);
            tabCanParamsEngine.setModel(model);
            tabCanParamsEngine.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsEngine.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanEngineCnt; i++) {
                if (PanCanConfig.objCanEngine[i].display_enable) {
                    if (PanCanConfig.objCanEngine[i].unit.equals("bit")) {
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].name, row, 0);
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].value, row, 1);
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].unit, row, 2);
                    } else {
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].name, row, 0);
                        tabCanParamsEngine.setValueAt(decimalFormat.format(PanCanConfig.objCanEngine[i].value), row, 1);
                        tabCanParamsEngine.setValueAt(PanCanConfig.objCanEngine[i].unit, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanOthersCnt);
            tabCanParamsOthers.setModel(model);
            tabCanParamsOthers.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsOthers.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanOthersCnt; i++) {
                if (PanCanConfig.objCanOthers[i].display_enable) {
                    if (PanCanConfig.objCanOthers[i].unit.equals("bit")) {
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].name, row, 0);
                        tabCanParamsOthers.setValueAt((int) PanCanConfig.objCanOthers[i].value, row, 1);
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].unit, row, 2);
                    } else {
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].name, row, 0);
                        tabCanParamsOthers.setValueAt(decimalFormat.format(PanCanConfig.objCanOthers[i].value), row, 1);
                        tabCanParamsOthers.setValueAt(PanCanConfig.objCanOthers[i].unit, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        //can1 data
        //can2 data
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanElectricalCnt1);
            tabCanParamsElectrical1.setModel(model);
            tabCanParamsElectrical1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsElectrical1.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanElectricalCnt1; i++) {
                if (PanCanConfig.objCanElectrical1[i].display_enable1) {
                    if (PanCanConfig.objCanElectrical1[i].unit1.equals("bit")) {
                        tabCanParamsElectrical1.setValueAt(PanCanConfig.objCanElectrical1[i].name1, row, 0);
                        tabCanParamsElectrical1.setValueAt((int) (PanCanConfig.objCanElectrical1[i].value1), row, 1);
                        tabCanParamsElectrical1.setValueAt(PanCanConfig.objCanElectrical1[i].unit1, row, 2);
                    } else {
                        tabCanParamsElectrical1.setValueAt(PanCanConfig.objCanElectrical1[i].name1, row, 0);
                        tabCanParamsElectrical1.setValueAt(decimalFormat.format(PanCanConfig.objCanElectrical1[i].value1), row, 1);
                        tabCanParamsElectrical1.setValueAt(PanCanConfig.objCanElectrical1[i].unit1, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanSafetyCnt1);
            tabCanParamsSafety1.setModel(model);
            tabCanParamsSafety1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsSafety1.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanSafetyCnt1; i++) {
                if (PanCanConfig.objCanSafety1[i].display_enable1) {
                    if (PanCanConfig.objCanSafety1[i].unit1.equals("bit")) {
                        tabCanParamsSafety1.setValueAt(PanCanConfig.objCanSafety1[i].name1, row, 0);
                        tabCanParamsSafety1.setValueAt((int) PanCanConfig.objCanSafety1[i].value1, row, 1);
                        tabCanParamsSafety1.setValueAt(PanCanConfig.objCanSafety1[i].unit1, row, 2);
                    } else {
                        tabCanParamsSafety1.setValueAt(PanCanConfig.objCanSafety1[i].name1, row, 0);
                        tabCanParamsSafety1.setValueAt(decimalFormat.format(PanCanConfig.objCanSafety1[i].value1), row, 1);
                        tabCanParamsSafety1.setValueAt(PanCanConfig.objCanSafety1[i].unit1, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;

        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanTransmitCnt1);
            tabCanParamsTransmit1.setModel(model);
            tabCanParamsTransmit1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsTransmit1.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanTransmitCnt1; i++) {
                if (PanCanConfig.objCanTransmit1[i].display_enable1) {
                    if (PanCanConfig.objCanTransmit1[i].unit1.equals("bit")) {
                        tabCanParamsTransmit1.setValueAt(PanCanConfig.objCanTransmit1[i].name1, row, 0);
                        tabCanParamsTransmit1.setValueAt((int) PanCanConfig.objCanTransmit1[i].value1, row, 1);
                        tabCanParamsTransmit1.setValueAt(PanCanConfig.objCanTransmit1[i].unit1, row, 2);
                    } else {
                        tabCanParamsTransmit1.setValueAt(PanCanConfig.objCanTransmit1[i].name1, row, 0);
                        tabCanParamsTransmit1.setValueAt(decimalFormat.format(PanCanConfig.objCanTransmit1[i].value1), row, 1);
                        tabCanParamsTransmit1.setValueAt(PanCanConfig.objCanTransmit1[i].unit1, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanEngineCnt1);
            tabCanParamsEngine1.setModel(model);
            tabCanParamsEngine1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsEngine1.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanEngineCnt1; i++) {
                if (PanCanConfig.objCanEngine1[i].display_enable1) {
                    if (PanCanConfig.objCanEngine1[i].unit1.equals("bit")) {
                        tabCanParamsEngine1.setValueAt(PanCanConfig.objCanEngine1[i].name1, row, 0);
                        tabCanParamsEngine1.setValueAt(PanCanConfig.objCanEngine1[i].value1, row, 1);
                        tabCanParamsEngine1.setValueAt(PanCanConfig.objCanEngine1[i].unit1, row, 2);
                    } else {
                        tabCanParamsEngine1.setValueAt(PanCanConfig.objCanEngine1[i].name1, row, 0);
                        tabCanParamsEngine1.setValueAt(decimalFormat.format(PanCanConfig.objCanEngine1[i].value1), row, 1);
                        tabCanParamsEngine1.setValueAt(PanCanConfig.objCanEngine1[i].unit1, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        model = new DefaultTableModel();
        try {
            model.addColumn("Parameter Name");
            model.addColumn("Value");
            model.addColumn("Unit");
            model.setNumRows(PanCanConfig.objCanOthersCnt1);
            tabCanParamsOthers1.setModel(model);
            tabCanParamsOthers1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsOthers1.getColumnModel().getColumn(1).setPreferredWidth(200);
            row = 0;
            for (i = 0; i < PanCanConfig.objCanOthersCnt1; i++) {
                if (PanCanConfig.objCanOthers1[i].display_enable1) {
                    if (PanCanConfig.objCanOthers1[i].unit1.equals("bit")) {
                        tabCanParamsOthers1.setValueAt(PanCanConfig.objCanOthers1[i].name1, row, 0);
                        tabCanParamsOthers1.setValueAt((int) PanCanConfig.objCanOthers1[i].value1, row, 1);
                        tabCanParamsOthers1.setValueAt(PanCanConfig.objCanOthers1[i].unit1, row, 2);
                    } else {
                        tabCanParamsOthers1.setValueAt(PanCanConfig.objCanOthers1[i].name1, row, 0);
                        tabCanParamsOthers1.setValueAt(decimalFormat.format(PanCanConfig.objCanOthers1[i].value1), row, 1);
                        tabCanParamsOthers1.setValueAt(PanCanConfig.objCanOthers1[i].unit1, row, 2);
                    }
                    row++;
                }
            }
        } catch (Exception ex) {
        }
        model = null;
        //can2 data end
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane4.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane5.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane6.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane7.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane8.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane9.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane10.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane11.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        jScrollPane12.getVerticalScrollBar().setPreferredSize(new Dimension(30, 0));
        setCanVhmdScreen(true);
        setCurActivity(clsDefines.VHMD_ACTIVITY);
        try {
            clsReadFiles objReadFilesAct = new clsReadFiles();
            objReadFilesAct.write_activity_name(clsDefines.VHMD_ACTIVITY);
            objReadFilesAct = null;
        } catch (Exception ex) {
        }
        setCanElectricalVhmdScreen(true);
        setCanTransmitVhmdScreen(false);
        setCanSafetyVhmdScreen(false);
        setCanEngineVhmdScreen(false);
        setCanOthersVhmdScreen(false);
        setCanElectricalVhmdScreen1(false);
        setCanTransmitVhmdScreen1(false);
        setCanSafetyVhmdScreen1(false);
        setCanEngineVhmdScreen1(false);
        setCanOthersVhmdScreen1(false);
        jScrollPane3.setVisible(true);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(false);
        jScrollPane7.setVisible(false);
        jScrollPane8.setVisible(false);
        jScrollPane9.setVisible(false);
        jScrollPane10.setVisible(false);
        jScrollPane11.setVisible(false);
        jScrollPane12.setVisible(false);
        btnElectricalSystems.setBackground(color_green);
        btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        btnTransmission.setBackground(color_blue);
        btnOthers.setBackground(color_blue);
        tabCanParamsElectrical.getTableHeader().setReorderingAllowed(false);
        tabCanParamsSafety.getTableHeader().setReorderingAllowed(false);
        tabCanParamsTransmit.getTableHeader().setReorderingAllowed(false);
        tabCanParamsEngine.getTableHeader().setReorderingAllowed(false);
        tabCanParamsOthers.getTableHeader().setReorderingAllowed(false);
        jSplitPane1.setOneTouchExpandable(false);
        tabCanParamsElectrical1.getTableHeader().setReorderingAllowed(false);
        tabCanParamsSafety1.getTableHeader().setReorderingAllowed(false);
        tabCanParamsTransmit1.getTableHeader().setReorderingAllowed(false);
        tabCanParamsEngine1.getTableHeader().setReorderingAllowed(false);
        tabCanParamsOthers1.getTableHeader().setReorderingAllowed(false);
        jSplitPane2.setOneTouchExpandable(false);
        revalidate();
        repaint();
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        btnElectricalSystems = new javax.swing.JButton();
        btnSafety = new javax.swing.JButton();
        btnTransmission = new javax.swing.JButton();
        btnEngine = new javax.swing.JButton();
        btnOthers = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabCanParamsElectrical = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabCanParamsSafety = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabCanParamsTransmit = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabCanParamsEngine = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabCanParamsOthers = new javax.swing.JTable();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        btnElectricalSystems1 = new javax.swing.JButton();
        btnSafety1 = new javax.swing.JButton();
        btnTransmission1 = new javax.swing.JButton();
        btnEngine1 = new javax.swing.JButton();
        btnOthers1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabCanParamsElectrical1 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabCanParamsSafety1 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabCanParamsTransmit1 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabCanParamsEngine1 = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tabCanParamsOthers1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(23, 29, 32));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 400));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(698, 627));

        jSplitPane1.setBackground(new java.awt.Color(23, 29, 32));
        jSplitPane1.setDividerLocation(60);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setToolTipText("");
        jSplitPane1.setOpaque(false);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(690, 500));

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 23, 51), 1, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 92));

        btnElectricalSystems.setBackground(new java.awt.Color(85, 85, 85));
        btnElectricalSystems.setFont(btnElectricalSystems.getFont().deriveFont(btnElectricalSystems.getFont().getStyle() & ~java.awt.Font.BOLD, btnElectricalSystems.getFont().getSize()+5));
        btnElectricalSystems.setForeground(new java.awt.Color(255, 255, 255));
        btnElectricalSystems.setText("Electrical Systems");
        btnElectricalSystems.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnElectricalSystems.setPreferredSize(new java.awt.Dimension(200, 40));
        btnElectricalSystems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElectricalSystemsActionPerformed(evt);
            }
        });

        btnSafety.setBackground(new java.awt.Color(85, 85, 85));
        btnSafety.setFont(btnSafety.getFont().deriveFont(btnSafety.getFont().getStyle() & ~java.awt.Font.BOLD, btnSafety.getFont().getSize()+5));
        btnSafety.setForeground(new java.awt.Color(255, 255, 255));
        btnSafety.setText("Safety & Performance");
        btnSafety.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSafety.setPreferredSize(new java.awt.Dimension(240, 40));
        btnSafety.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyActionPerformed(evt);
            }
        });

        btnTransmission.setBackground(new java.awt.Color(85, 85, 85));
        btnTransmission.setFont(btnTransmission.getFont().deriveFont(btnTransmission.getFont().getStyle() & ~java.awt.Font.BOLD, btnTransmission.getFont().getSize()+5));
        btnTransmission.setForeground(new java.awt.Color(255, 255, 255));
        btnTransmission.setText("Transmission");
        btnTransmission.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTransmission.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTransmission.setPreferredSize(new java.awt.Dimension(130, 40));
        btnTransmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransmissionActionPerformed(evt);
            }
        });

        btnEngine.setBackground(new java.awt.Color(85, 85, 85));
        btnEngine.setFont(btnEngine.getFont().deriveFont(btnEngine.getFont().getStyle() & ~java.awt.Font.BOLD, btnEngine.getFont().getSize()+5));
        btnEngine.setForeground(new java.awt.Color(255, 255, 255));
        btnEngine.setText("Engine");
        btnEngine.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEngine.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEngine.setPreferredSize(new java.awt.Dimension(80, 40));
        btnEngine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEngineActionPerformed(evt);
            }
        });

        btnOthers.setBackground(new java.awt.Color(119, 119, 119));
        btnOthers.setFont(btnOthers.getFont().deriveFont(btnOthers.getFont().getStyle() & ~java.awt.Font.BOLD, btnOthers.getFont().getSize()+5));
        btnOthers.setText("Others");
        btnOthers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOthers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOthers.setPreferredSize(new java.awt.Dimension(65, 40));
        btnOthers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOthersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnElectricalSystems, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSafety, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnTransmission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEngine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOthers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnElectricalSystems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSafety, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransmission, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEngine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOthers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jSplitPane1.setTopComponent(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 450));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(700, 300));

        tabCanParamsElectrical.setBackground(new java.awt.Color(240, 240, 240));
        tabCanParamsElectrical.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 23, 51), 1, true));
        tabCanParamsElectrical.setFont(tabCanParamsElectrical.getFont().deriveFont(tabCanParamsElectrical.getFont().getSize()+5f));
        tabCanParamsElectrical.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsElectrical.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsElectrical.setEnabled(false);
        tabCanParamsElectrical.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsElectrical.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsElectrical.setRequestFocusEnabled(false);
        tabCanParamsElectrical.setRowHeight(30);
        tabCanParamsElectrical.getTableHeader().setResizingAllowed(false);
        tabCanParamsElectrical.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabCanParamsElectrical);
        if (tabCanParamsElectrical.getColumnModel().getColumnCount() > 0) {
            tabCanParamsElectrical.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsElectrical.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsElectrical.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsElectrical.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsElectrical.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsElectrical.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(700, 500));

        tabCanParamsSafety.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsSafety.setFont(tabCanParamsSafety.getFont().deriveFont(tabCanParamsSafety.getFont().getSize()+5f));
        tabCanParamsSafety.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsSafety.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsSafety.setEnabled(false);
        tabCanParamsSafety.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsSafety.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsSafety.setRowHeight(30);
        tabCanParamsSafety.getTableHeader().setResizingAllowed(false);
        tabCanParamsSafety.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tabCanParamsSafety);
        if (tabCanParamsSafety.getColumnModel().getColumnCount() > 0) {
            tabCanParamsSafety.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsSafety.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsSafety.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsSafety.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsSafety.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsSafety.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane5.setPreferredSize(new java.awt.Dimension(700, 500));

        tabCanParamsTransmit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsTransmit.setFont(tabCanParamsTransmit.getFont().deriveFont(tabCanParamsTransmit.getFont().getSize()+5f));
        tabCanParamsTransmit.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsTransmit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsTransmit.setEnabled(false);
        tabCanParamsTransmit.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsTransmit.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsTransmit.setRowHeight(30);
        tabCanParamsTransmit.getTableHeader().setResizingAllowed(false);
        tabCanParamsTransmit.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tabCanParamsTransmit);
        if (tabCanParamsTransmit.getColumnModel().getColumnCount() > 0) {
            tabCanParamsTransmit.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsTransmit.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsTransmit.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsTransmit.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsTransmit.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsTransmit.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane6.setPreferredSize(new java.awt.Dimension(700, 500));

        tabCanParamsEngine.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsEngine.setFont(tabCanParamsEngine.getFont().deriveFont(tabCanParamsEngine.getFont().getSize()+5f));
        tabCanParamsEngine.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsEngine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {"", null, ""},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsEngine.setEnabled(false);
        tabCanParamsEngine.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsEngine.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsEngine.setRowHeight(30);
        tabCanParamsEngine.getTableHeader().setResizingAllowed(false);
        tabCanParamsEngine.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tabCanParamsEngine);
        if (tabCanParamsEngine.getColumnModel().getColumnCount() > 0) {
            tabCanParamsEngine.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsEngine.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsEngine.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsEngine.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsEngine.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsEngine.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane7.setPreferredSize(new java.awt.Dimension(700, 500));

        tabCanParamsOthers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsOthers.setFont(tabCanParamsOthers.getFont().deriveFont(tabCanParamsOthers.getFont().getSize()+5f));
        tabCanParamsOthers.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsOthers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsOthers.setEnabled(false);
        tabCanParamsOthers.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsOthers.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsOthers.setRowHeight(30);
        tabCanParamsOthers.getTableHeader().setResizingAllowed(false);
        tabCanParamsOthers.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tabCanParamsOthers);
        if (tabCanParamsOthers.getColumnModel().getColumnCount() > 0) {
            tabCanParamsOthers.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsOthers.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsOthers.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsOthers.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsOthers.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsOthers.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jSplitPane1.setBottomComponent(jPanel2);

        jTabbedPane1.addTab("CAN1 DATA", jSplitPane1);

        jSplitPane2.setBackground(new java.awt.Color(23, 29, 32));
        jSplitPane2.setDividerLocation(60);
        jSplitPane2.setDividerSize(0);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setResizeWeight(1.0);
        jSplitPane2.setToolTipText("");
        jSplitPane2.setOpaque(false);

        jPanel4.setBackground(new java.awt.Color(23, 29, 32));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 23, 51), 1, true));

        btnElectricalSystems1.setBackground(new java.awt.Color(85, 85, 85));
        btnElectricalSystems1.setFont(btnElectricalSystems1.getFont().deriveFont(btnElectricalSystems1.getFont().getStyle() & ~java.awt.Font.BOLD, btnElectricalSystems1.getFont().getSize()+5));
        btnElectricalSystems1.setForeground(new java.awt.Color(255, 255, 255));
        btnElectricalSystems1.setText("Electrical Systems");
        btnElectricalSystems1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnElectricalSystems1.setPreferredSize(new java.awt.Dimension(200, 40));
        btnElectricalSystems1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElectricalSystems1ActionPerformed(evt);
            }
        });

        btnSafety1.setBackground(new java.awt.Color(85, 85, 85));
        btnSafety1.setFont(btnSafety1.getFont().deriveFont(btnSafety1.getFont().getStyle() & ~java.awt.Font.BOLD, btnSafety1.getFont().getSize()+5));
        btnSafety1.setForeground(new java.awt.Color(255, 255, 255));
        btnSafety1.setText("Safety & Performance");
        btnSafety1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSafety1.setPreferredSize(new java.awt.Dimension(240, 40));
        btnSafety1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafety1ActionPerformed(evt);
            }
        });

        btnTransmission1.setBackground(new java.awt.Color(85, 85, 85));
        btnTransmission1.setFont(btnTransmission1.getFont().deriveFont(btnTransmission1.getFont().getStyle() & ~java.awt.Font.BOLD, btnTransmission1.getFont().getSize()+5));
        btnTransmission1.setForeground(new java.awt.Color(255, 255, 255));
        btnTransmission1.setText("Transmission");
        btnTransmission1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTransmission1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTransmission1.setPreferredSize(new java.awt.Dimension(130, 40));
        btnTransmission1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransmission1ActionPerformed(evt);
            }
        });

        btnEngine1.setBackground(new java.awt.Color(85, 85, 85));
        btnEngine1.setFont(btnEngine1.getFont().deriveFont(btnEngine1.getFont().getStyle() & ~java.awt.Font.BOLD, btnEngine1.getFont().getSize()+5));
        btnEngine1.setForeground(new java.awt.Color(255, 255, 255));
        btnEngine1.setText("Engine");
        btnEngine1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEngine1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEngine1.setPreferredSize(new java.awt.Dimension(80, 40));
        btnEngine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEngine1ActionPerformed(evt);
            }
        });

        btnOthers1.setBackground(new java.awt.Color(119, 119, 119));
        btnOthers1.setFont(btnOthers1.getFont().deriveFont(btnOthers1.getFont().getStyle() & ~java.awt.Font.BOLD, btnOthers1.getFont().getSize()+5));
        btnOthers1.setText("Others");
        btnOthers1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOthers1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOthers1.setPreferredSize(new java.awt.Dimension(65, 40));
        btnOthers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOthers1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnElectricalSystems1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSafety1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTransmission1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEngine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOthers1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnElectricalSystems1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSafety1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransmission1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEngine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOthers1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jSplitPane2.setTopComponent(jPanel4);

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setPreferredSize(new java.awt.Dimension(700, 500));

        tabCanParamsElectrical1.setBackground(new java.awt.Color(240, 240, 240));
        tabCanParamsElectrical1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(18, 23, 51), 1, true));
        tabCanParamsElectrical1.setFont(tabCanParamsElectrical1.getFont().deriveFont(tabCanParamsElectrical1.getFont().getSize()+5f));
        tabCanParamsElectrical1.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsElectrical1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsElectrical1.setEnabled(false);
        tabCanParamsElectrical1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsElectrical1.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsElectrical1.setRequestFocusEnabled(false);
        tabCanParamsElectrical1.setRowHeight(30);
        tabCanParamsElectrical1.getTableHeader().setResizingAllowed(false);
        tabCanParamsElectrical1.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(tabCanParamsElectrical1);
        if (tabCanParamsElectrical1.getColumnModel().getColumnCount() > 0) {
            tabCanParamsElectrical1.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsElectrical1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsElectrical1.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsElectrical1.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsElectrical1.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsElectrical1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane9.setPreferredSize(new java.awt.Dimension(700, 1700));

        tabCanParamsSafety1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsSafety1.setFont(tabCanParamsSafety1.getFont().deriveFont(tabCanParamsSafety1.getFont().getSize()+5f));
        tabCanParamsSafety1.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsSafety1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsSafety1.setEnabled(false);
        tabCanParamsSafety1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsSafety1.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsSafety1.setRowHeight(30);
        tabCanParamsSafety1.getTableHeader().setResizingAllowed(false);
        tabCanParamsSafety1.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tabCanParamsSafety1);
        if (tabCanParamsSafety1.getColumnModel().getColumnCount() > 0) {
            tabCanParamsSafety1.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsSafety1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsSafety1.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsSafety1.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsSafety1.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsSafety1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane10.setPreferredSize(new java.awt.Dimension(695, 1700));

        tabCanParamsTransmit1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsTransmit1.setFont(tabCanParamsTransmit1.getFont().deriveFont(tabCanParamsTransmit1.getFont().getSize()+5f));
        tabCanParamsTransmit1.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsTransmit1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsTransmit1.setEnabled(false);
        tabCanParamsTransmit1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsTransmit1.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsTransmit1.setRowHeight(30);
        tabCanParamsTransmit1.getTableHeader().setResizingAllowed(false);
        tabCanParamsTransmit1.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(tabCanParamsTransmit1);
        if (tabCanParamsTransmit1.getColumnModel().getColumnCount() > 0) {
            tabCanParamsTransmit1.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsTransmit1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsTransmit1.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsTransmit1.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsTransmit1.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsTransmit1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane11.setPreferredSize(new java.awt.Dimension(695, 1700));

        tabCanParamsEngine1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsEngine1.setFont(tabCanParamsEngine1.getFont().deriveFont(tabCanParamsEngine1.getFont().getSize()+5f));
        tabCanParamsEngine1.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsEngine1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {"", null, ""},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsEngine1.setEnabled(false);
        tabCanParamsEngine1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsEngine1.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsEngine1.setRowHeight(30);
        tabCanParamsEngine1.getTableHeader().setResizingAllowed(false);
        tabCanParamsEngine1.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tabCanParamsEngine1);
        if (tabCanParamsEngine1.getColumnModel().getColumnCount() > 0) {
            tabCanParamsEngine1.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsEngine1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsEngine1.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsEngine1.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsEngine1.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsEngine1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jScrollPane12.setPreferredSize(new java.awt.Dimension(695, 1700));

        tabCanParamsOthers1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabCanParamsOthers1.setFont(tabCanParamsOthers1.getFont().deriveFont(tabCanParamsOthers1.getFont().getSize()+5f));
        tabCanParamsOthers1.setForeground(new java.awt.Color(0, 0, 102));
        tabCanParamsOthers1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Unit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabCanParamsOthers1.setEnabled(false);
        tabCanParamsOthers1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabCanParamsOthers1.setPreferredSize(new java.awt.Dimension(700, 5000));
        tabCanParamsOthers1.setRowHeight(30);
        tabCanParamsOthers1.getTableHeader().setResizingAllowed(false);
        tabCanParamsOthers1.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(tabCanParamsOthers1);
        if (tabCanParamsOthers1.getColumnModel().getColumnCount() > 0) {
            tabCanParamsOthers1.getColumnModel().getColumn(0).setResizable(false);
            tabCanParamsOthers1.getColumnModel().getColumn(0).setPreferredWidth(300);
            tabCanParamsOthers1.getColumnModel().getColumn(1).setResizable(false);
            tabCanParamsOthers1.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabCanParamsOthers1.getColumnModel().getColumn(2).setResizable(false);
            tabCanParamsOthers1.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel5.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 328));

        jSplitPane2.setBottomComponent(jPanel5);

        jTabbedPane1.addTab("CAN2 DATA", jSplitPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void removeNotify() {
        super.removeNotify();
        // Remove internal "registered things"
        //  stopTimer();
        setCanElectricalVhmdScreen(false);
        setCanTransmitVhmdScreen(false);
        setCanSafetyVhmdScreen(false);
        setCanEngineVhmdScreen(false);
        setCanOthersVhmdScreen(false);
        setCanElectricalVhmdScreen1(false);
        setCanTransmitVhmdScreen1(false);
        setCanEngineVhmdScreen1(false);
        setCanOthersVhmdScreen1(false);
    }

    private void btnSafetyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyActionPerformed
        // TODO add your handling code here:
        index = 1;
        setCanElectricalVhmdScreen(false);
        setCanTransmitVhmdScreen(false);
        setCanEngineVhmdScreen(false);
        setCanOthersVhmdScreen(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(true);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(false);
        jScrollPane7.setVisible(false);
        setCanSafetyVhmdScreen(true);
        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_green);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_blue);
        revalidate();
        repaint();

    }//GEN-LAST:event_btnSafetyActionPerformed

    private void btnTransmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransmissionActionPerformed
        // TODO add your handling code here:
        index = 2;
        setCanElectricalVhmdScreen(false);
        setCanSafetyVhmdScreen(false);
        setCanEngineVhmdScreen(false);
        setCanOthersVhmdScreen(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(true);
        jScrollPane6.setVisible(false);
        jScrollPane7.setVisible(false);
        setCanTransmitVhmdScreen(true);
        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_green);
        this.btnOthers.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnTransmissionActionPerformed

    private void btnEngineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEngineActionPerformed
        // TODO add your handling code here:
        index = 3;
        setCanElectricalVhmdScreen(false);
        setCanTransmitVhmdScreen(false);
        setCanSafetyVhmdScreen(false);
        setCanOthersVhmdScreen(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(true);
        jScrollPane7.setVisible(false);
        setCanEngineVhmdScreen(true);
        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_green);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnEngineActionPerformed

    private void btnOthersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOthersActionPerformed
        // TODO add your handling code here:
        index = 4;
        setCanElectricalVhmdScreen(false);
        setCanTransmitVhmdScreen(false);
        setCanSafetyVhmdScreen(false);
        setCanEngineVhmdScreen(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(false);
        jScrollPane7.setVisible(true);
        setCanOthersVhmdScreen(true);
        btnElectricalSystems.setBackground(color_blue);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_green);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnOthersActionPerformed

    private void btnElectricalSystemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElectricalSystemsActionPerformed
        // TODO add your handling code here:
        index = 1;
        setCanElectricalVhmdScreen(true);
        setCanSafetyVhmdScreen(false);
        setCanTransmitVhmdScreen(false);
        setCanEngineVhmdScreen(false);
        setCanOthersVhmdScreen(false);
        jScrollPane3.setVisible(true);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(false);
        jScrollPane7.setVisible(false);
        btnElectricalSystems.setBackground(color_green);
        this.btnSafety.setBackground(color_blue);
        btnEngine.setBackground(color_blue);
        this.btnTransmission.setBackground(color_blue);
        this.btnOthers.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnElectricalSystemsActionPerformed

    private void btnElectricalSystems1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElectricalSystems1ActionPerformed
        // TODO add your handling code here:
        index1 = 1;
        setCanElectricalVhmdScreen1(true);
        setCanSafetyVhmdScreen1(false);
        setCanTransmitVhmdScreen1(false);
        setCanEngineVhmdScreen1(false);
        setCanOthersVhmdScreen1(false);
        jScrollPane8.setVisible(true);
        jScrollPane9.setVisible(false);
        jScrollPane10.setVisible(false);
        jScrollPane11.setVisible(false);
        jScrollPane12.setVisible(false);
        btnElectricalSystems1.setBackground(color_green);
        this.btnSafety1.setBackground(color_blue);
        btnEngine1.setBackground(color_blue);
        this.btnTransmission1.setBackground(color_blue);
        this.btnOthers1.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnElectricalSystems1ActionPerformed

    private void btnSafety1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafety1ActionPerformed
        // TODO add your handling code here:
        index1 = 1;
        setCanElectricalVhmdScreen1(false);
        setCanTransmitVhmdScreen1(false);
        setCanEngineVhmdScreen1(false);
        setCanOthersVhmdScreen1(false);
        jScrollPane8.setVisible(false);
        jScrollPane9.setVisible(true);
        jScrollPane10.setVisible(false);
        jScrollPane11.setVisible(false);
        jScrollPane12.setVisible(false);
        setCanSafetyVhmdScreen1(true);
        btnElectricalSystems1.setBackground(color_blue);
        this.btnSafety1.setBackground(color_green);
        btnEngine1.setBackground(color_blue);
        this.btnTransmission1.setBackground(color_blue);
        this.btnOthers1.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnSafety1ActionPerformed

    private void btnTransmission1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransmission1ActionPerformed
        // TODO add your handling code here:
        index1 = 2;
        setCanElectricalVhmdScreen1(false);
        setCanSafetyVhmdScreen1(false);
        setCanEngineVhmdScreen1(false);
        setCanOthersVhmdScreen1(false);
        jScrollPane8.setVisible(false);
        jScrollPane9.setVisible(false);
        jScrollPane10.setVisible(true);
        jScrollPane11.setVisible(false);
        jScrollPane12.setVisible(false);
        setCanTransmitVhmdScreen1(true);
        btnElectricalSystems1.setBackground(color_blue);
        this.btnSafety1.setBackground(color_blue);
        btnEngine1.setBackground(color_blue);
        this.btnTransmission1.setBackground(color_green);
        this.btnOthers1.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnTransmission1ActionPerformed

    private void btnEngine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEngine1ActionPerformed
        // TODO add your handling code here:
        index1 = 3;
        setCanElectricalVhmdScreen1(false);
        setCanTransmitVhmdScreen1(false);
        setCanSafetyVhmdScreen1(false);
        setCanOthersVhmdScreen1(false);
        jScrollPane8.setVisible(false);
        jScrollPane9.setVisible(false);
        jScrollPane10.setVisible(false);
        jScrollPane11.setVisible(true);
        jScrollPane12.setVisible(false);
        setCanEngineVhmdScreen1(true);
        btnElectricalSystems1.setBackground(color_blue);
        this.btnSafety1.setBackground(color_blue);
        btnEngine1.setBackground(color_green);
        this.btnTransmission1.setBackground(color_blue);
        this.btnOthers1.setBackground(color_blue);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnEngine1ActionPerformed

    private void btnOthers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOthers1ActionPerformed
        // TODO add your handling code here:
        index1 = 4;
        setCanElectricalVhmdScreen1(false);
        setCanTransmitVhmdScreen1(false);
        setCanSafetyVhmdScreen1(false);
        setCanEngineVhmdScreen1(false);
        jScrollPane8.setVisible(false);
        jScrollPane9.setVisible(false);
        jScrollPane10.setVisible(false);
        jScrollPane11.setVisible(false);
        jScrollPane12.setVisible(true);
        setCanOthersVhmdScreen1(true);
        btnElectricalSystems1.setBackground(color_blue);
        this.btnSafety1.setBackground(color_blue);
        btnEngine1.setBackground(color_blue);
        this.btnTransmission1.setBackground(color_blue);
        this.btnOthers1.setBackground(color_green);
        revalidate();
        repaint();
    }//GEN-LAST:event_btnOthers1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnElectricalSystems;
    private javax.swing.JButton btnElectricalSystems1;
    private javax.swing.JButton btnEngine;
    private javax.swing.JButton btnEngine1;
    private javax.swing.JButton btnOthers;
    private javax.swing.JButton btnOthers1;
    private javax.swing.JButton btnSafety;
    private javax.swing.JButton btnSafety1;
    private javax.swing.JButton btnTransmission;
    private javax.swing.JButton btnTransmission1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable tabCanParamsElectrical;
    public static javax.swing.JTable tabCanParamsElectrical1;
    public static javax.swing.JTable tabCanParamsEngine;
    public static javax.swing.JTable tabCanParamsEngine1;
    public static javax.swing.JTable tabCanParamsOthers;
    public static javax.swing.JTable tabCanParamsOthers1;
    public static javax.swing.JTable tabCanParamsSafety;
    public static javax.swing.JTable tabCanParamsSafety1;
    public static javax.swing.JTable tabCanParamsTransmit;
    public static javax.swing.JTable tabCanParamsTransmit1;
    // End of variables declaration//GEN-END:variables

}
