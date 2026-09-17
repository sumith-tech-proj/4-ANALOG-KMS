package obuits;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.text.SimpleDateFormat;
import static obuits.clsDefines.AVG_BUS_SPEED;
import static obuits.clsDefines.ROUTE_ENABLE;
import static obuits.clsDefines.SCHDEULE_ENABLE;
import static obuits.clsDefines.TRIP_SKIP;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getCurSchTripStatus;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getCurTripStat;
import static obuits.clsSharedVariables.getDriverStopCnt;
import static obuits.clsSharedVariables.getNoRoutes;
import static obuits.clsSharedVariables.getNoStopsRoute;
import static obuits.clsSharedVariables.getSchRouteEnable;
import static obuits.clsSharedVariables.get_des_name;
import static obuits.clsSharedVariables.get_src_name;
import static obuits.clsSharedVariables.gps_data;
import static obuits.clsSharedVariables.incCurTripNo;
import static obuits.clsSharedVariables.objRouteMasFiles;
import static obuits.clsSharedVariables.setCurRouteNo;
import static obuits.clsSharedVariables.setCurTripNo;
import static obuits.clsSharedVariables.setDriverStopCnt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static obuits.PanDisplayBoardDiag.setDisBrdDelPktCame;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;
import static obuits.clsDefines.TRIP_END;
import static obuits.clsDefines.TRIP_UNKNOWN;
import static obuits.clsDefines.main_route_path;
import static obuits.clsInternalDisBrdMessage.fill_internal_details;
//import static obuits.clsPeopleCntEventClear.peopleCountRouteEnd;
import static obuits.clsSharedVariables.driverLoginEnabled;
import static obuits.clsSharedVariables.getCurAutoTripStat;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.get_special_msg_status;
import static obuits.clsSharedVariables.lang_type;
import static obuits.clsSharedVariables.setCurActivity;
import static obuits.clsSharedVariables.setCurRouteStat;
import static obuits.clsSharedVariables.setCurSchTripStatus;
import static obuits.clsSharedVariables.setCurStopNo;
import static obuits.clsSharedVariables.setCurTripStat;
import static obuits.clsSharedVariables.setPrevLat;
import static obuits.clsSharedVariables.setShowStopStopDis;
import static obuits.clsReadFiles.label_names;
import static obuits.clsReadFiles.label_names_cnt;
import static obuits.clsSharedVariables.getApcStatus;
import static obuits.clsSharedVariables.getCurStopNo;
import static obuits.clsSharedVariables.setApcStatus;

public class PanelRoute extends javax.swing.JPanel {

    Timer timer;
    TimerTask timerTask;
    String prev_control_name = "";
    Process keypad_process;
    clsReadFiles objReadFiles = new clsReadFiles();
    clsReadDataFiles objReadDataFiles = new clsReadDataFiles();
    gpsDriving objDriving = new gpsDriving();
    final String[] array_panics = new String[5];
    byte[] array_panic_id = new byte[5];
    static short selected_trip_no = 0;
    String labelRouteNo = "Route No: ";
    String labelTripNo = "Trip no: ";

    class clsRouteStruct {

        short trip_no;
        String route_no;
        String route_name;
    }
    clsRouteStruct[] objCurRouteStruct = new clsRouteStruct[clsDefines.MAX_NO_ROUTES];
    static boolean spec_msg_act_bgcolor = false;
    short cur_routes_cnt = 0;
    String[] cur_no_srcdes_names;
    JPanel panMainPane;
    JPanel panCenterMainPane;
    DefaultTableModel model = new DefaultTableModel(10, 0);

    public PanelRoute(JPanel panMainPan, JPanel panCenterMainPan) {
        initComponents();
        if (label_names_cnt > 0 && (lang_type == LANG_HINDI || lang_type == LANG_REG)) {
            try {
                this.btnRouteStart.setText(label_names[9]);
                this.btnRouteSkip.setText(label_names[11]);
                this.btnRouteEnd.setText(label_names[10]);
                this.btnMap.setText(label_names[12]);
                this.btnManualRoute.setText(label_names[13]);

            } catch (Exception ex) {
            }
        }
        panMainPane = panMainPan;
        panCenterMainPane = panCenterMainPan;
        int i;
        int j = 0;
        String src_name = null;
        String des_name = null;
        short cur_trip_no_l = getCurTripNo();
        byte schedule_type = getSchRouteEnable();
        if (schedule_type == ROUTE_ENABLE) {
            lblSearch.setVisible(true);
            this.inputSearch.setVisible(true);
        } else {
            lblSearch.setVisible(false);
            this.inputSearch.setVisible(false);
        }

        String str = objReadFiles.read_driver_delay_reason_messages();
        String[] split_str = str.split(",");
        array_panic_id[0] = 1;
        array_panic_id[1] = 2;
        array_panic_id[2] = 3;
        array_panic_id[3] = 4;
        array_panic_id[4] = 5;

        array_panics[0] = "TRAFFIC JAM";
        array_panics[1] = "ACCIDENT";
        array_panics[2] = "ROUTE DIVERTION";
        array_panics[3] = "SQUAD CHECKING";
        array_panics[4] = "TRAFFIC SIGNAL";

        if (!str.equals("")) {
            for (i = 0; i < split_str.length && i < 10;) {
                try {
                    array_panic_id[j] = Byte.parseByte(split_str[i]);
                    i++;
                    array_panics[j] = split_str[i];
                    i++;
                    j++;
                } catch (Exception ex) {

                }
            }
        }
        split_str = null;

        cur_no_srcdes_names = new String[getCurSchNoTrips()];

        init_cur_route_struct((short) getCurSchNoTrips());

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //Only the third column
                return false;
            }
        };
        model.addColumn("Trip No");
        model.addColumn("Route No");
        model.addColumn("Src-Des");

        tabRoutes.removeAll();

        model.setNumRows(getCurSchNoTrips());
        tabRoutes.setModel(model);
        JTableHeader tabHead = tabRoutes.getTableHeader();
        tabHead.setFont(new Font("Serif", Font.BOLD, 18));
        tabHead.setBackground(new Color(29, 41, 81));
        tabHead.setForeground(Color.WHITE);
        tabRoutes.setTableHeader(tabHead);
        tabRoutes.setCellSelectionEnabled(false);
        tabRoutes.setRowSelectionAllowed(true);
        i = 0;
        try {
            for (i = 0; i < getCurSchNoTrips(); i++) {
                src_name = get_src_name((short) i);
                des_name = get_des_name((short) i);
                if (src_name != null && des_name != null) {
                    cur_no_srcdes_names[i] = src_name + "-" + des_name;
                } else if (src_name != null) {
                    cur_no_srcdes_names[i] = src_name;
                } else if (des_name != null) {
                    cur_no_srcdes_names[i] = des_name;
                } else {
                    cur_no_srcdes_names[i] = "";
                }

                objCurRouteStruct[i].route_no = getCurSchRouteNo(i);
                objCurRouteStruct[i].trip_no = (short) (i + 1);
                objCurRouteStruct[i].route_name = cur_no_srcdes_names[i];

                //  row.clear();
                tabRoutes.setValueAt(objCurRouteStruct[i].trip_no, i, 0);
                tabRoutes.setValueAt(objCurRouteStruct[i].route_no, i, 1);
                tabRoutes.setValueAt(objCurRouteStruct[i].route_name, i, 2);

            }
        } catch (Exception ex) {
        }
        cur_routes_cnt = (short) i;

        tabRoutes.getSelectionModel().setLeadSelectionIndex(0);
        tabRoutes.setVisible(true);
        tabRoutes.setBackground(Color.lightGray);
        tabRoutes.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabRoutes.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabRoutes.getColumnModel().getColumn(2).setPreferredWidth(500);
        tabRoutes.setShowVerticalLines(false);

        tabRoutes.setShowGrid(false);
        tabRoutes.setShowHorizontalLines(false);

        if (cur_trip_no_l <= getCurSchNoTrips() && cur_trip_no_l > 5) {
            tabRoutes.scrollRectToVisible(new Rectangle(tabRoutes.getCellRect(cur_trip_no_l - 4, 0, true)));
        }

        if (getSchRouteEnable() == SCHDEULE_ENABLE) {
            btnRouteSkip.setEnabled(true);
            if (cur_trip_no_l > getCurSchNoTrips()) {
                lblRouteNo.setText("");
                lblTripNo.setText("Routes Are Completed");
                lblSrcDes.setText("Wait for next Schedule");
            } else if (getCurSchNoTrips() == 0) {
                lblRouteNo.setText("");
                lblTripNo.setText("No trips Are Assigned");
                lblSrcDes.setText("Wait for next Schedule");
            } else if (cur_trip_no_l < getCurSchNoTrips()) {
                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(cur_trip_no_l));
                lblTripNo.setText(labelTripNo + (String.valueOf(cur_trip_no_l + 1)));
                lblSrcDes.setText(cur_no_srcdes_names[cur_trip_no_l]);
            }
            selected_trip_no = cur_trip_no_l;

        } else {
            btnRouteSkip.setEnabled(false);
            if (cur_trip_no_l < getCurSchNoTrips() && getCurSchNoTrips() > 0) {
                //lblSchId.setText(labelSchId + get_driver_id());//clsSharedVariables.getSchId());
                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(cur_trip_no_l));
                lblTripNo.setText(labelTripNo + (String.valueOf(cur_trip_no_l + 1)));
                lblSrcDes.setText(cur_no_srcdes_names[cur_trip_no_l]);
                selected_trip_no = cur_trip_no_l;
            } else {
                cur_trip_no_l = 0;
                selected_trip_no = 0;
            }

        }
        try {
            tabRoutes.setRowSelectionInterval(selected_trip_no, 0);
        } catch (Exception e) {
            
        }
        if (getSchRouteEnable() == SCHDEULE_ENABLE) {
            inputSearch.setVisible(true);
            if (getCurTripStat() == TRIP_START) {
                btnRouteStart.setVisible(false);
                lblSearch.setVisible(false);
            this.inputSearch.setVisible(false);
                btnMap.setVisible(true);
                btnManualRoute.setVisible(true);
                btnRouteEnd.setVisible(true);
            } else if (getCurTripStat() == TRIP_SKIP) {
                btnRouteEnd.setVisible(false);
                btnRouteStart.setVisible(true);
                lblSearch.setVisible(true);
            this.inputSearch.setVisible(true);
                btnMap.setVisible(false);
                btnManualRoute.setVisible(false);
            } else {
                btnRouteEnd.setVisible(false);
                btnRouteStart.setVisible(true);
                lblSearch.setVisible(true);
            this.inputSearch.setVisible(true);
                btnMap.setVisible(false);
                btnManualRoute.setVisible(false);
            }
        } else {
            if (getCurTripStat() == TRIP_START) {
                btnRouteStart.setVisible(false);
                lblSearch.setVisible(false);
            this.inputSearch.setVisible(false);
                btnMap.setVisible(true);
                btnManualRoute.setVisible(true);
                btnRouteEnd.setVisible(true);
                try {
                    setCurSchTripStatus(cur_trip_no_l, TRIP_START);
                    selected_trip_no = cur_trip_no_l;
                } catch (Exception ex) {

                }
            } else {
                btnRouteEnd.setVisible(false);
                btnRouteStart.setVisible(true);
                lblSearch.setVisible(true);
            this.inputSearch.setVisible(true);
                btnMap.setVisible(false);
                btnManualRoute.setVisible(false);
                try {
                    setCurSchTripStatus(cur_trip_no_l, TRIP_END);
                } catch (Exception ex) {

                }
            }
            btnRouteSkip.setVisible(false);
        }

        if (get_special_msg_status() == true) {
            lblTripNo.setText("");
            lblSrcDes.setText("Special Messages Are Activated ");
            lblSrcDes.setFont(new Font("Serif", Font.BOLD, 30));

            panBottomRoute.setVisible(false);
            jScrollPane2.setVisible(false);
        } else {
            panBottomRoute.setVisible(true);

            tabRoutes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                private DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                    Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                    short l_tripno = 0;
                    try {
                        l_tripno = Short.parseShort(tabRoutes.getValueAt(row, 0).toString()); // row;
                    } catch (Exception ex) {
                    }

                    if (getSchRouteEnable() == ROUTE_ENABLE) {
                        try {
                            if (selected_trip_no >= 0) {
                                if (getCurTripStat() == TRIP_START && l_tripno == selected_trip_no + 1) {
                                    c.setBackground(Color.GREEN);
                                    c.setForeground(Color.WHITE);
                                    c.setFont(new Font("Serif", Font.BOLD, 30));
                                    return c;
                                } else if (getCurTripStat() == TRIP_END && getCurSchTripStatus(selected_trip_no) == TRIP_END && l_tripno == selected_trip_no + 1 && isSelected) {
                                    c.setBackground(Color.YELLOW);
                                    c.setForeground(Color.BLACK);
                                    c.setFont(new Font("Serif", Font.BOLD, 22));
                                    return c;
                                } else if (getCurTripStat() != TRIP_START && isSelected) { //&& hasFocus) {
                                    c.setBackground(Color.YELLOW);
                                    c.setFont(new Font("Serif", Font.BOLD, 22));

                                    return c;
                                } else {
                                    c.setBackground(Color.WHITE);
                                    c.setForeground(Color.BLACK);
                                    c.setFont(new Font("Serif", Font.BOLD, 22));
                                    return c;
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        try {
                            int cur_trip_no = getCurTripNo();
                            int position = row;
                            if (position == cur_trip_no && getCurSchTripStatus(position) == TRIP_START) {
                                c.setBackground(Color.GREEN);
                                c.setForeground(Color.WHITE);
                                c.setFont(new Font("Serif", Font.BOLD, 30));
                            } else if (position == cur_trip_no && getCurSchTripStatus(position) == TRIP_END) {
                                c.setBackground(Color.RED);
                                c.setForeground(Color.WHITE);
                                c.setFont(new Font("Serif", Font.BOLD, 22));
                            } else if (position < cur_trip_no && getCurSchTripStatus(position) == TRIP_SKIP) {
                                c.setBackground(Color.MAGENTA);
                                c.setForeground(Color.WHITE);
                                c.setFont(new Font("Serif", Font.BOLD, 22));
                            } else if (position < cur_trip_no) { //&& getCurSchTripStatus(position) == TRIP_END) {
                                c.setBackground(Color.RED);
                                c.setForeground(Color.WHITE);
                                c.setFont(new Font("Serif", Font.BOLD, 22));
                            } else if (position > getCurTripNo()) {
                                c.setBackground(Color.YELLOW);
                                c.setForeground(Color.BLACK);
                                c.setFont(new Font("Serif", Font.BOLD, 22));
                            } else {
                                c.setBackground(Color.WHITE);
                                c.setForeground(Color.BLACK);
                                c.setFont(new Font("Serif", Font.BOLD, 22));
                            }
                            return c;
                        } catch (Exception ex) {
                            ex.printStackTrace();

                        }

                    }

                    return this;
                }
            });

            jScrollPane2.setVisible(true);
            jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(50, 0));
            this.tabRoutes.getTableHeader().setReorderingAllowed(false);

        }
        setCurActivity(clsDefines.ROUTE_ACTIVITY);
        try {
            clsReadFiles objReadFilesAct = new clsReadFiles();
            objReadFilesAct.write_activity_name(clsDefines.ROUTE_ACTIVITY);
            objReadFilesAct = null;

            if (cur_routes_cnt > 10) {
                Dimension d = new Dimension(500, 100 * cur_routes_cnt);
                tabRoutes.setPreferredSize(d);
                d = null;
            }

        } catch (Exception ex) {

        }

        startTimer();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        // Remove internal "registered things"
        stopTimer();
        model = null;
    }

    void init_cur_route_struct(short cur_trips_cnt) {
        short i = 0;

        for (i = 0; i < cur_trips_cnt; i++) {
            objCurRouteStruct[i] = new clsRouteStruct();
            objCurRouteStruct[i].route_no = new String();
            objCurRouteStruct[i].route_name = new String();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTripNo = new javax.swing.JLabel();
        lblSrcDes = new javax.swing.JLabel();
        inputSearch = new javax.swing.JTextField();
        lblRouteNo = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        panBottomRoute = new javax.swing.JPanel();
        btnRouteEnd = new javax.swing.JButton();
        btnMap = new javax.swing.JButton();
        btnRouteSkip = new javax.swing.JButton();
        btnRouteStart = new javax.swing.JButton();
        btnManualRoute = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabRoutes = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(102, 102, 102), new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        setFont(getFont().deriveFont(getFont().getSize()+7f));
        setPreferredSize(new java.awt.Dimension(700, 430));

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 90));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        lblTripNo.setFont(lblTripNo.getFont().deriveFont(lblTripNo.getFont().getStyle() & ~java.awt.Font.BOLD, lblTripNo.getFont().getSize()+5));
        lblTripNo.setForeground(new java.awt.Color(255, 255, 255));
        lblTripNo.setText("Trip No   :");

        lblSrcDes.setFont(lblSrcDes.getFont().deriveFont(lblSrcDes.getFont().getStyle() & ~java.awt.Font.BOLD, lblSrcDes.getFont().getSize()+3));
        lblSrcDes.setForeground(new java.awt.Color(255, 255, 255));
        lblSrcDes.setText("Source - Destination");

        inputSearch.setBackground(new java.awt.Color(240, 240, 240));
        inputSearch.setFont(inputSearch.getFont().deriveFont(inputSearch.getFont().getSize()+3f));
        inputSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputSearchFocusLost(evt);
            }
        });
        inputSearch.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                inputSearchInputMethodTextChanged(evt);
            }
        });
        inputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSearchActionPerformed(evt);
            }
        });
        inputSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputSearchKeyTyped(evt);
            }
        });

        lblRouteNo.setFont(lblRouteNo.getFont().deriveFont(lblRouteNo.getFont().getStyle() & ~java.awt.Font.BOLD, lblRouteNo.getFont().getSize()+6));
        lblRouteNo.setForeground(new java.awt.Color(255, 255, 255));
        lblRouteNo.setText("Route No");

        lblSearch.setFont(lblSearch.getFont().deriveFont(lblSearch.getFont().getStyle() | java.awt.Font.BOLD, lblSearch.getFont().getSize()+3));
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSrcDes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRouteNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTripNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch)))
                .addGap(18, 18, 18)
                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblSearch))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblRouteNo)
                        .addGap(9, 9, 9)
                        .addComponent(lblTripNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSrcDes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panBottomRoute.setBackground(new java.awt.Color(23, 29, 32));
        panBottomRoute.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 51, 102), new java.awt.Color(0, 0, 102), new java.awt.Color(0, 51, 102), new java.awt.Color(0, 0, 102)));
        panBottomRoute.setPreferredSize(new java.awt.Dimension(700, 70));

        btnRouteEnd.setBackground(new java.awt.Color(85, 85, 85));
        btnRouteEnd.setFont(btnRouteEnd.getFont().deriveFont(btnRouteEnd.getFont().getStyle() | java.awt.Font.BOLD, btnRouteEnd.getFont().getSize()+7));
        btnRouteEnd.setForeground(new java.awt.Color(255, 255, 255));
        btnRouteEnd.setText("END");
        btnRouteEnd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        btnRouteEnd.setPreferredSize(new java.awt.Dimension(100, 40));
        btnRouteEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteEndActionPerformed(evt);
            }
        });

        btnMap.setBackground(new java.awt.Color(85, 85, 85));
        btnMap.setFont(btnMap.getFont().deriveFont(btnMap.getFont().getStyle() | java.awt.Font.BOLD, btnMap.getFont().getSize()+7));
        btnMap.setForeground(new java.awt.Color(255, 245, 245));
        btnMap.setText("MAP");
        btnMap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        btnMap.setPreferredSize(new java.awt.Dimension(100, 40));
        btnMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapActionPerformed(evt);
            }
        });

        btnRouteSkip.setBackground(new java.awt.Color(85, 85, 85));
        btnRouteSkip.setFont(btnRouteSkip.getFont().deriveFont(btnRouteSkip.getFont().getStyle() | java.awt.Font.BOLD, btnRouteSkip.getFont().getSize()+7));
        btnRouteSkip.setForeground(new java.awt.Color(255, 255, 255));
        btnRouteSkip.setText("SKIP");
        btnRouteSkip.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        btnRouteSkip.setPreferredSize(new java.awt.Dimension(100, 40));
        btnRouteSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteSkipActionPerformed(evt);
            }
        });

        btnRouteStart.setBackground(new java.awt.Color(85, 85, 85));
        btnRouteStart.setFont(btnRouteStart.getFont().deriveFont(btnRouteStart.getFont().getStyle() | java.awt.Font.BOLD, btnRouteStart.getFont().getSize()+7));
        btnRouteStart.setForeground(new java.awt.Color(255, 255, 255));
        btnRouteStart.setText("START");
        btnRouteStart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        btnRouteStart.setPreferredSize(new java.awt.Dimension(100, 40));
        btnRouteStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteStartActionPerformed(evt);
            }
        });

        btnManualRoute.setBackground(new java.awt.Color(85, 85, 85));
        btnManualRoute.setFont(btnManualRoute.getFont().deriveFont(btnManualRoute.getFont().getStyle() | java.awt.Font.BOLD, btnManualRoute.getFont().getSize()+7));
        btnManualRoute.setForeground(new java.awt.Color(255, 255, 255));
        btnManualRoute.setText("Manual");
        btnManualRoute.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        btnManualRoute.setPreferredSize(new java.awt.Dimension(100, 40));
        btnManualRoute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManualRouteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panBottomRouteLayout = new javax.swing.GroupLayout(panBottomRoute);
        panBottomRoute.setLayout(panBottomRouteLayout);
        panBottomRouteLayout.setHorizontalGroup(
            panBottomRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBottomRouteLayout.createSequentialGroup()
                .addComponent(btnRouteStart, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRouteSkip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRouteEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnManualRoute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        panBottomRouteLayout.setVerticalGroup(
            panBottomRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBottomRouteLayout.createSequentialGroup()
                .addGroup(panBottomRouteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRouteStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRouteSkip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRouteEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManualRoute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        panBottomRouteLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnManualRoute, btnMap, btnRouteSkip, btnRouteStart});

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setOpaque(false);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 500));

        tabRoutes.setBackground(new java.awt.Color(240, 240, 240));
        tabRoutes.setFont(tabRoutes.getFont().deriveFont(tabRoutes.getFont().getStyle() | java.awt.Font.BOLD, tabRoutes.getFont().getSize()+7));
        tabRoutes.setForeground(new java.awt.Color(0, 0, 102));
        tabRoutes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Trip No", "Route No", "Source-Destination"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tabRoutes.setGridColor(new java.awt.Color(51, 0, 153));
        tabRoutes.setPreferredSize(new java.awt.Dimension(699, 16000));
        tabRoutes.setRowHeight(50);
        tabRoutes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabRoutes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabRoutesMouseClicked(evt);
            }
        });
        tabRoutes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabRoutesPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tabRoutes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addComponent(panBottomRoute, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panBottomRoute, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 100000ms
        if (get_special_msg_status() == true) {
            timer.schedule(timerTask, 1000, 1000); //
        } else {
            timer.schedule(timerTask, 1000, 1000); //
        }
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
            public void run() {
                try {
                    int no_trips = 0;
                    short no_of_routes;
                    int i = 0;
                    String src_name = null;
                    String des_name = null;
                    //  Vector row = new Vector();
                    if (get_special_msg_status() == true) {
                        if (spec_msg_act_bgcolor == false) {
                            spec_msg_act_bgcolor = true;

                            try {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {

                                        lblSrcDes.setForeground(Color.RED);
                                    }
                                });
                            } catch (Exception ex) {

                            }

                        } else {
                            spec_msg_act_bgcolor = false;
                            try {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {

                                        lblSrcDes.setForeground(Color.WHITE);
                                    }
                                });
                            } catch (Exception ex) {

                            }

                        }

                    } else if (clsSharedVariables.getSchUpdated() == true) {
                        ArrayList<String> items = new ArrayList<>();
                        clsSharedVariables.setSchUpdated(false);
                        no_of_routes = getNoRoutes();
                        if (getSchRouteEnable() == SCHDEULE_ENABLE) {
                            no_trips = getCurSchNoTrips();

                            cur_no_srcdes_names = new String[no_trips];

                            init_cur_route_struct((short) no_trips);
                            model.setNumRows(no_trips);
                            tabRoutes.removeAll();
                            tabRoutes.setModel(model);
                            for (i = 0; i < no_trips; i++) {
                                src_name = get_src_name((short) i);
                                des_name = get_des_name((short) i);
                                if (src_name != null && des_name != null) {
                                    cur_no_srcdes_names[i] = src_name + "-" + des_name;
                                } else if (src_name != null) {
                                    cur_no_srcdes_names[i] = src_name;
                                } else if (des_name != null) {
                                    cur_no_srcdes_names[i] = des_name;
                                } else {
                                    cur_no_srcdes_names[i] = "";
                                }

                                // cur_no_srcdes_names[i] = get_src_name((short) i) + "-" + get_des_name((short) i);
                                objCurRouteStruct[i].route_no = getCurSchRouteNo(i);
                                objCurRouteStruct[i].trip_no = (short) (i + 1);
                                objCurRouteStruct[i].route_name = cur_no_srcdes_names[i];

                                items.add(i + 1 + "         " + objCurRouteStruct[i].route_no + "     " + cur_no_srcdes_names[i]);

                                tabRoutes.setValueAt(i + 1, i, 0);

                                tabRoutes.setValueAt(objCurRouteStruct[i].route_no, i, 1);
                                tabRoutes.setValueAt(cur_no_srcdes_names[i], i, 2);

                            }
                            cur_routes_cnt = (short) (i + 1);

                            tabRoutes.getSelectionModel().setLeadSelectionIndex(0);
                            tabRoutes.setVisible(true);

                            //lblSchId.setText(labelSchId + get_driver_id());//clsSharedVariables.getSchId());
                            lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(getCurTripNo()));
                            lblTripNo.setText(labelTripNo + String.valueOf(getCurTripNo() + 1));
                            lblSrcDes.setText(cur_no_srcdes_names[getCurTripNo()]);

                            if (tabRoutes.getModel().getRowCount() > 0) {
                                tabRoutes.updateUI();
                            }
                            clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
                            objHealth.set_route_no(getCurSchRouteNo(getCurTripNo()));
                            objHealth.set_trip_no((byte) (getCurTripNo() + 1));
                            objHealth = null;

                        } else {

                            init_cur_route_struct((short) no_of_routes);
                            model.setRowCount(no_of_routes);
                            tabRoutes.removeAll();
                            tabRoutes.setModel(model);
                            for (i = 0; i < no_of_routes; i++) {

                                src_name = get_src_name((short) i);
                                des_name = get_des_name((short) i);
                                if (src_name != null && des_name != null) {
                                    cur_no_srcdes_names[i] = src_name + "-" + des_name;
                                } else if (src_name != null) {
                                    cur_no_srcdes_names[i] = src_name;
                                } else if (des_name != null) {
                                    cur_no_srcdes_names[i] = des_name;
                                } else {
                                    cur_no_srcdes_names[i] = "";
                                }
                                objCurRouteStruct[i].route_no = getCurSchRouteNo(i);
                                objCurRouteStruct[i].trip_no = (short) (i + 1);
                                objCurRouteStruct[i].route_name = cur_no_srcdes_names[i];
                                items.add(i + 1 + "            " + objCurRouteStruct[i].route_no + "     " + cur_no_srcdes_names[i]);
                                tabRoutes.setValueAt(i + 1, i, 0);
                                tabRoutes.setValueAt(objCurRouteStruct[i].route_no, i, 1);
                                tabRoutes.setValueAt(cur_no_srcdes_names[i], i, 2);
                            }
                            cur_routes_cnt = (short) i;

                            tabRoutes.getSelectionModel().setLeadSelectionIndex(0);
                            tabRoutes.setVisible(true);

                            lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(getCurTripNo()));
                            lblTripNo.setText(labelTripNo + String.valueOf(getCurTripNo() + 1));
                            lblSrcDes.setText(cur_no_srcdes_names[getCurTripNo()]);
                            if (tabRoutes.getModel().getRowCount() > selected_trip_no) {
                                try {
                                    tabRoutes.setRowSelectionInterval(selected_trip_no, 0);
                                } catch (Exception e) {

                                }
                                if (getSchRouteEnable() == SCHDEULE_ENABLE) {
                                    inputSearch.setVisible(true);
                                    if (getCurTripStat() == TRIP_START) {
                                        btnRouteStart.setVisible(false);
                                        btnMap.setVisible(true);
                                        btnManualRoute.setVisible(true);
                                        btnRouteEnd.setVisible(true);
                                    } else if (getCurTripStat() == TRIP_SKIP) {
                                        btnRouteEnd.setVisible(false);
                                        btnRouteStart.setVisible(true);
                                        btnMap.setVisible(false);
                                        btnManualRoute.setVisible(false);
                                    } else {
                                        btnRouteEnd.setVisible(false);
                                        btnRouteStart.setVisible(true);
                                        btnMap.setVisible(false);
                                        btnManualRoute.setVisible(false);
                                    }
                                } else {
                                    if (getCurTripStat() == TRIP_START) {
                                        btnRouteStart.setVisible(false);
                                        lblSearch.setVisible(false);
                                       inputSearch.setVisible(false);
                                        btnMap.setVisible(true);
                                        btnManualRoute.setVisible(true);
                                        btnRouteEnd.setVisible(true);

                                    } else {
                                        btnRouteEnd.setVisible(false);
                                        btnRouteStart.setVisible(true);
                                        lblSearch.setVisible(true);
                                       inputSearch.setVisible(true);
                                        btnMap.setVisible(false);
                                        btnManualRoute.setVisible(false);

                                    }
                                    btnRouteSkip.setVisible(false);
                                }
                                tabRoutes.updateUI();
                            }
                            clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
                            objHealth.set_route_no(getCurSchRouteNo(getCurTripNo()));
                            objHealth.set_trip_no((byte) (getCurTripNo() + 1));
                            objHealth = null;

                            // lstRoutes .updateUI();  
                        }
                    } else if (clsSharedVariables.getTripStatusUpdated() == true) {
                        clsSharedVariables.setTripStatusUpdated(false);
                        if (tabRoutes.getModel().getRowCount() > 0) {
                            selected_trip_no = getCurTripNo();
                            try {
                                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(getCurTripNo()));
                                lblTripNo.setText(labelTripNo + String.valueOf(getCurTripNo() + 1));
                                lblSrcDes.setText(cur_no_srcdes_names[getCurTripNo()]);
                            } catch (Exception ex) {

                            }
                            tabRoutes.updateUI();
                        }

                    }

                } catch (Exception ex) {

                }

            }
        };
    }

    private void btnRouteStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteStartActionPerformed
        // TODO add your handling code here:
//        clsApcPacketConstruct objApc = new clsApcPacketConstruct();
        final short no_of_routes = getNoRoutes();
        final ClsSchedule objSch = new ClsSchedule();
        byte schedule_type = getSchRouteEnable();
        boolean route_fnd = false;
        short k;
        short l_cur_trip_no = getCurTripNo();
        gpsDriving objDriving = new gpsDriving();
        clsBusStopDetection.gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
        setShowStopStopDis((double) 0.0);
        setDisBrdDelPktCame(false);
        setPrevLat(0.0);
        setPrevLat(0.0);
        setCurRouteStat(false);
        setCurStopNo((byte) 0);
        setCurTripStat(TRIP_UNKNOWN);
        clsDefines.route_start = true;
        
       

//        try {
//
//            SwingWorker sw1 = new SwingWorker() {
//                @Override
//                protected String doInBackground() throws Exception {
//                    clsPeopleCntEventClear objWaterMarking = new clsPeopleCntEventClear();
//                    objWaterMarking.setwatermarkforrir();
//                    objWaterMarking.setwatermarkforrir1();
//                    MainFrmIts.lblPeopleOut2.setText("0");
//                    MainFrmIts.lblPeopleIn2.setText("0");
//                    clsSharedVariables.setApcPeopleCountIn(0);
//                    clsSharedVariables.setApcPeopleCountOut(0);
//                    clsSharedVariables.setApcPeopleIn(0);
//                    clsSharedVariables.setApcPeopleOut(0);
//                    objWaterMarking.clearPeopleCountValues();
//                    objWaterMarking.setWaterMarkingDahuaApc(clsSharedVariables.getApcPeopleCountIn(), clsSharedVariables.getApcPeopleCountOut());
//                    clsSharedVariables.setApcPeopleIn1(0);
//                    clsSharedVariables.setApcPeopleOut1(0);
//                    objWaterMarking.clearPeopleCountValues1();
//                    objWaterMarking.setWaterMarkingDahuaApc1(clsSharedVariables.getApcPeopleCountIn(), clsSharedVariables.getApcPeopleCountOut());
//                    objWaterMarking = null;
//                    return "Over";
//                }
//            };
//            sw1.execute();
//        } catch (Exception ex) {
//
//        }
        try {
            if (keypad_process != null) {
                keypad_process = null;
            }
        } catch (Exception ex) {
        }

        if (schedule_type == ROUTE_ENABLE) {
            if (getNoRoutes() <= 0) {
                return;
            }
            short l_tripno = 0;
            int sel_row = 0;
            try {
                if (getCurTripNo() >= getNoRoutes()) {
                    setCurTripNo((short) 0);
                    selected_trip_no = 0;
                    l_cur_trip_no = (short) 0;
                    if (l_cur_trip_no <= getCurSchNoTrips() && l_cur_trip_no > 5) {
                        tabRoutes.scrollRectToVisible(new Rectangle(tabRoutes.getCellRect(l_cur_trip_no, 0, true)));
                    }
                    // this.tabRoutes.ensureIndexIsVisible(cur_trip_no_l);

                } else {

                    for (short j = 0; j < cur_routes_cnt; j++) {
                        try {
                            sel_row = selected_trip_no;// tabRoutes.getSelectedRow();
                            String selected_route = (String) tabRoutes.getValueAt(sel_row, 1).toString();
                            l_tripno = (short) tabRoutes.getValueAt(sel_row, 0);
                            if (selected_route.contains(objCurRouteStruct[l_tripno - 1].route_no)) {
                                route_fnd = true;
                                if (l_tripno > 0) {
                                    l_cur_trip_no = (short) ((short) l_tripno - 1);
                                } else {
                                    l_cur_trip_no = 0;
                                }
                                selected_trip_no = l_cur_trip_no;
                                setCurTripNo((short) selected_trip_no);
                                break;
                            }

                        } catch (Exception ex) {

                        }

                    }
                    if (route_fnd == false) {
                        return;
                    }
                }

                setCurTripStat(TRIP_START);
                setCurSchTripStatus(l_cur_trip_no, TRIP_START);
                setCurRouteNo(getCurSchRouteNo(l_cur_trip_no));
                objSch.send_route_info();
                if (sel_row <= getCurSchNoTrips() && sel_row > 5) {
                    tabRoutes.scrollRectToVisible(new Rectangle(tabRoutes.getCellRect(sel_row - 4, 0, true)));
                }

                int avg_speed = AVG_BUS_SPEED;
                int dur = 100;
                long sec;

                sec = Calendar.getInstance().getTimeInMillis();

                try {

                    if (getNoStopsRoute() > 1) {
                        double next_stop_distance = gps_data[getNoStopsRoute() - 1].stop_stop_dis;
                        dur = (int) (next_stop_distance * 18) / (avg_speed * 5);  //(formaula : time = ((distance * 18)/(speed *5) )
                    }
                } catch (Exception ex) {
                    dur = 10;
                }
                dur = dur * 1000;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(sec + dur);
                if (clsSharedVariables.getIpAddr1Enable()) {
                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));
                    obj16833Pkts = null;
                }
                if (clsSharedVariables.getIpAddr4Enable()) {

                    objDriving.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));

                }
                if (clsSharedVariables.getIpAddr5Enable()) {
                    if (clsSharedVariables.getSuratProtocol()) {
                        clsSmcTrackingPackets objSmc = new clsSmcTrackingPackets();
                        objSmc.event_pkt_smc(clsSharedVariables.route_start);
                        objSmc = null;
                    } else {
                        objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_START);
                    }
                }

                for (int j = 0; j < no_of_routes; j++) {
                    if (j == l_cur_trip_no) {
                        setCurSchTripStatus(l_cur_trip_no, TRIP_START);
                    } else {
                        setCurSchTripStatus(j, TRIP_UNKNOWN);
                    }
                }

                setCurSchTripStatus(l_cur_trip_no, TRIP_START);

                objReadFiles.write_cur_trip_info_file();
                objReadFiles.write_cur_route_info_file();
                objReadFiles.write_cur_schedule_file();

                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(l_cur_trip_no));
                lblTripNo.setText(labelTripNo + (String.valueOf(l_cur_trip_no + 1)));
                lblSrcDes.setText(cur_no_srcdes_names[l_cur_trip_no]);
                //new added
// ✅ Clear and save fresh trip info for continuity
try {
    clsReadDataFiles objReadData = new clsReadDataFiles();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
String startTime = sdf.format(new java.util.Date());
    
    
    StringBuilder sb = new StringBuilder();
    sb.append(startTime).append(",");  // Start timestamp
    sb.append(getCurSchRouteNo(getCurTripNo())).append(",");  // Route number
    sb.append(gps_data[getCurStopNo()].stop_name).append(","); // Route name
   // sb.append("START"); // Marker

    objReadData.write_apc_stored_data(sb.toString()); // overwrite old data
} catch (Exception ex) {
    System.out.println("[WARN] Failed to write apc_stored_data at route start: " + ex.getMessage());
}

                
            } catch (Exception ex) {

            }

            btnRouteStart.setVisible(false);
            lblSearch.setVisible(false);
            this.inputSearch.setVisible(false);
            btnRouteEnd.setVisible(true);
            btnRouteSkip.setVisible(false);
            btnManualRoute.setVisible(true);
            btnMap.setVisible(true);

            setCurTripStat(TRIP_START);

            clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
            objHealth.set_route_no(getCurSchRouteNo(l_cur_trip_no));
            objHealth.set_trip_no((byte) (l_cur_trip_no + 1));
            objHealth = null;
            objDriving = null;

//            if (getApcStatus() != clsDefines.APC_START) {
//                setApcStatus(clsDefines.APC_START);
//                objApc.apc_start_packet(getCurSchRouteNo(l_cur_trip_no), gps_data[getCurStopNo()].stop_name);
//
//            }
        } else {
            if (l_cur_trip_no < getCurSchNoTrips()) {
                for (k = 0; k < no_of_routes; k++) {
                    if (objRouteMasFiles[k].route_no.equals(getCurSchRouteNo(l_cur_trip_no))) {
                        route_fnd = true;
                    }
                }
                if (route_fnd == false) {
                    return;
                }
                if (l_cur_trip_no <= getCurSchNoTrips() && l_cur_trip_no > 5) {
                    tabRoutes.scrollRectToVisible(new Rectangle(tabRoutes.getCellRect(l_cur_trip_no - 4, 0, true)));
                }

                if (getCurTripStat() != TRIP_START || getCurSchTripStatus(l_cur_trip_no) != TRIP_START) {
                    // end the trip
                    setCurTripStat(TRIP_START);
                    setCurSchTripStatus(l_cur_trip_no, TRIP_START);
                    setCurRouteNo(getCurSchRouteNo(l_cur_trip_no));

                    long sec;
                    int avg_speed = AVG_BUS_SPEED;
                    int dur = 100;

                    sec = Calendar.getInstance().getTimeInMillis();

                    if (getNoStopsRoute() > 1) {

                        try {
                            double next_stop_distance = gps_data[getNoStopsRoute() - 1].stop_stop_dis;
                            dur = (int) (next_stop_distance * 18) / (avg_speed * 5);  //(formaula : time = ((distance * 18)/(speed *5) )
                        } catch (Exception ex) {

                            dur = 10;
                        }
                    }
                    dur = dur * 1000;
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(sec + dur);

                    if (clsSharedVariables.getIpAddr1Enable()) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));
                        obj16833Pkts = null;
                    }
                    if (clsSharedVariables.getIpAddr4Enable()) {

                        objDriving.trip_start_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));

                    }
                    if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                        objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_START);

                    }

                    //lblSchId.setText(labelSchId + get_driver_id());//clsSharedVariables.getSchId());
                    lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(l_cur_trip_no));
                    lblTripNo.setText(labelTripNo + (String.valueOf(l_cur_trip_no + 1)));
                    lblSrcDes.setText(cur_no_srcdes_names[l_cur_trip_no]);
                    objReadFiles.write_cur_trip_info_file();
                    objReadFiles.write_cur_route_info_file();
                    objReadFiles.write_cur_schedule_file();

                    //sending information to Display boards
                    objSch.send_route_info();
                    btnRouteStart.setVisible(false);
                    lblSearch.setVisible(false);
            this.inputSearch.setVisible(false);
                    btnRouteEnd.setVisible(true);
                    btnRouteSkip.setVisible(false);
                    btnManualRoute.setVisible(true);
                    btnMap.setVisible(true);

                    clsHealthPacketStructure objHealth = new clsHealthPacketStructure();
                    objHealth.set_route_no(getCurSchRouteNo(l_cur_trip_no));
                    objHealth.set_trip_no((byte) (l_cur_trip_no + 1));
                    objHealth = null;

                } else {
                    btnRouteStart.setVisible(false);
                   lblSearch.setVisible(false);
            this.inputSearch.setVisible(false);
                    btnRouteEnd.setVisible(true);
                    btnRouteSkip.setVisible(false);
                    btnManualRoute.setVisible(true);
                    btnMap.setVisible(true);

                }

                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(l_cur_trip_no));
//                if (getApcStatus() != clsDefines.APC_START) {
//                    setApcStatus(clsDefines.APC_START);
//                    objApc.apc_start_packet(getCurSchRouteNo(l_cur_trip_no), gps_data[getCurStopNo()].stop_name);
//
//                }

            } else {
                return;
            }
        }

        if (getDriverStopCnt()
                > 0) {
            setDriverStopCnt((short) 0);
            PanDriverHaltMessage objPanel = new PanDriverHaltMessage(panMainPane, panCenterMainPane);
            panMainPane.removeAll();
            panMainPane.setLayout(new java.awt.BorderLayout());
            panMainPane.add(objPanel);
            panMainPane.revalidate();
            panMainPane.repaint();
            objPanel = null;
        }

        if (tabRoutes.getModel()
                .getRowCount() > 0) {
            tabRoutes.updateUI();
        }

        if (driverLoginEnabled
                == true && clsSharedVariables.getDisplayDriverDb()) {
            if (getIntEnable()) {

                if (!clsSharedVariables.get_conductor_name().equals("")) {
                    fill_internal_details("DRIVER: " + clsSharedVariables.get_driver_name() + "CONDUCTOR : " + clsSharedVariables.get_conductor_name(), clsDefines.INTDB_DATA_SAVE);
                } else {
                    fill_internal_details("DRIVER: " + clsSharedVariables.get_driver_name(), clsDefines.INTDB_DATA_SAVE);

                }
            }

        }
    }//GEN-LAST:event_btnRouteStartActionPerformed

    private void btnRouteSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteSkipActionPerformed
        // TODO add your handling code here:

        short l_cur_trip_no_skip = getCurTripNo();
        clsBusStopDetection.gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
        setShowStopStopDis((double) 0.0);
        byte schedule_type = getSchRouteEnable();
        clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
        objwatDog.setBusStopDet_watchdog_val((byte) 0);
        objwatDog = null;
        try {
            if (keypad_process != null) {
                keypad_process = null;
            }
        } catch (Exception ex) {
        }
        if (schedule_type == SCHDEULE_ENABLE) {
            btnRouteStart.setVisible(true);
            lblSearch.setVisible(true);
            this.inputSearch.setVisible(true);
            btnRouteEnd.setVisible(false);
            btnManualRoute.setVisible(true);
            btnMap.setVisible(true);


            /* NotificationPane notificationPane = new NotificationPane(this);
             notificationPane.setText(getCurSchRouteNo(l_cur_trip_no_skip) + " Trip Skipped");
             notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
             notificationPane.show();*/
            if (l_cur_trip_no_skip < getCurSchNoTrips()) {

                // end the trip
                setCurTripStat(TRIP_SKIP);
                setCurRouteStat(false);
                setCurRouteNo(getCurSchRouteNo(l_cur_trip_no_skip));
                setCurSchTripStatus(l_cur_trip_no_skip, TRIP_SKIP);

                long sec;
                int avg_speed = AVG_BUS_SPEED;
                int dur = 100;

                sec = Calendar.getInstance().getTimeInMillis();

                if (getNoStopsRoute() > 1) {
                    try {
                        double next_stop_distance = gps_data[getNoStopsRoute() - 1].stop_stop_dis;
                        dur = (int) (next_stop_distance * 18) / (avg_speed * 5);  //(formaula : time = ((distance * 18)/(speed *5) )
                    } catch (Exception ex) {
                        dur = 10;
                    }
                }
                dur = dur * 1000;
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(sec + dur);
                if (clsSharedVariables.getIpAddr1Enable()) {
                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trip_skip_pkt();//trip_skip_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));

                    obj16833Pkts = null;
                }
                if (clsSharedVariables.getIpAddr4Enable()) {

                    objDriving.trip_skip_pkt((byte) cal.get(Calendar.MINUTE), (byte) cal.get(Calendar.SECOND));

                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_START);

                }
                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(l_cur_trip_no_skip));

                if (l_cur_trip_no_skip <= getCurSchNoTrips()) {
                    incCurTripNo();

                } else {
                    /*   notificationPane = new NotificationPane(this);
                     notificationPane.setText(getCurSchRouteNo(l_cur_trip_no_skip) + " All Trips Are Completed");
                     notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
                     notificationPane.show();*/
                    return;

                }

                //lblSchId.setText(labelSchId + get_driver_id());//clsSharedVariables.getSchId());
                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(l_cur_trip_no_skip));
                lblTripNo.setText(labelTripNo + (String.valueOf(l_cur_trip_no_skip + 1)));
                lblSrcDes.setText(cur_no_srcdes_names[l_cur_trip_no_skip]);
                // objSch.send_route_info();
                setCurStopNo((byte) 0);
                objReadFiles.write_cur_trip_info_file();
                objReadFiles.write_cur_route_info_file();
                objReadFiles.write_cur_schedule_file();

                if (tabRoutes.getModel().getRowCount() > 0) {
                    tabRoutes.updateUI();
                }
            } else {
                /*notificationPane = new NotificationPane(this);
                 notificationPane.setText(getCurSchRouteNo(l_cur_trip_no_skip) + " All Trips Are Completed");
                 notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
                 notificationPane.show();*/
                return;
            }
        }

    }//GEN-LAST:event_btnRouteSkipActionPerformed

    private void btnRouteEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteEndActionPerformed
        // TODO add your handling code here:
        short l_cur_trip_no_end = (short) getCurTripNo();
        short k;
        clsWatchdogResetVariables objwatDog = new clsWatchdogResetVariables();
        objwatDog.setBusStopDet_watchdog_val((byte) 0);
        objwatDog = null;
       // clsApcPacketConstruct objApc = new clsApcPacketConstruct();
        btnRouteStart.setVisible(true);
        lblSearch.setVisible(true);
            this.inputSearch.setVisible(true);
        btnRouteEnd.setVisible(false);
        btnRouteSkip.setVisible(true);
        btnManualRoute.setVisible(false);
        btnMap.setVisible(false);
        try {
            if (keypad_process != null) {
                keypad_process = null;
            }
        } catch (Exception ex) {
        }
        // if (driverLoginEnabled == true && clsSharedVariables.getDisplayDriverDb()) {
        if (getIntEnable()) {
            setDisBrdDelPktCame(true);
        }
        // }
        if (getCurAutoTripStat() == false) {
            byte schedule_type = getSchRouteEnable();
            if (schedule_type == ROUTE_ENABLE) {
                btnRouteSkip.setVisible(false);
                try {
                    if (clsSharedVariables.getIpAddr1Enable()) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trip_end_pkt();
                        obj16833Pkts = null;
                    }
                } catch (Exception ex) {
                }
                if (clsSharedVariables.getIpAddr4Enable()) {

                    objDriving.trip_end_pkt();

                }
                if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                    objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_END);

                }
//                try {
//                    if (getApcStatus() != clsDefines.APC_STOP) {
//                        setApcStatus(clsDefines.APC_STOP);
//                        objApc.apc_end_packet();
//
//                    }
//                } catch (Exception ex) {
//                }
                SwingWorker sw1 = new SwingWorker() {
                    @Override
                    protected String doInBackground() throws Exception {

//                        try {
//                            peopleCountRouteEnd((byte) 6);
//                        } catch (Exception ex) {
//
//                        }
                        try {
                            StringBuilder sb = new StringBuilder();
                            sb.append(Calendar.getInstance().getTimeInMillis());
                            sb.append(",");
                            sb.append(clsSharedVariables.getApcStartTime());
                            sb.append(",");
                            sb.append(clsSharedVariables.getApcStopTime());
                            sb.append(",");
                            sb.append(clsSharedVariables.getApcPeopleIn());
                            sb.append(",");
                            sb.append(clsSharedVariables.getApcPeopleOut());
                            sb.append(",");
                            sb.append(clsSharedVariables.getApcStopName());
                            sb.append(",");
                            sb.append(clsSharedVariables.getApcRouteNo());
                            sb.append(",");
                            objReadDataFiles.write_apc_route_data(sb.toString());
//                            try {
//                                peopleCountRouteEnd((byte) 7);
//                            } catch (Exception ex) {
//
//                            }
                            StringBuilder sb1 = new StringBuilder();
                            sb1.append(Calendar.getInstance().getTimeInMillis());
                            sb1.append(",");
                            sb1.append(clsSharedVariables.getApcStartTime());
                            sb1.append(",");
                            sb1.append(clsSharedVariables.getApcStopTime());
                            sb1.append(",");
                            sb1.append(clsSharedVariables.getApcPeopleIn1());
                            sb1.append(",");
                            sb1.append(clsSharedVariables.getApcPeopleOut1());
                            sb1.append(",");
                            sb1.append(clsSharedVariables.getApcStopName());
                            sb1.append(",");
                            sb1.append(clsSharedVariables.getApcRouteNo());
                            sb1.append(",");
                            //  System.out.println("in" +clsSharedVariables.getApcPeopleIn1() + "out" + clsSharedVariables.getApcPeopleOut1());
                            objReadDataFiles.write_apc_route_data1(sb1.toString());
                        } catch (Exception ex) {

                        }
                         try {
                    if (clsSharedVariables.getIpAddr1Enable()) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                       // System.out.println(clsSharedVariables.getApcPeopleIn() + clsSharedVariables.getApcPeopleOut() + clsSharedVariables.getApcPeopleIn1() + clsSharedVariables.getApcPeopleOut1());
                        obj16833Pkts.people_count_packet_on_route_end();
                        obj16833Pkts = null;
                    }
                } catch (Exception ex) {
                }
                        return "true";
                    }
                };
                sw1.execute();
               
                setCurTripStat(TRIP_END);
                setCurRouteStat(false);
                setCurStopNo((byte) 0);
                for (k = 0; k < getNoRoutes(); k++) {
                    setCurSchTripStatus(k, TRIP_UNKNOWN);
                    setCurRouteStat(false);
                }
                setCurSchTripStatus(l_cur_trip_no_end, TRIP_END);
                objReadFiles.write_cur_trip_info_file();
                objReadFiles.write_cur_route_info_file();
                objReadFiles.write_cur_schedule_file();
                selected_trip_no = l_cur_trip_no_end;

                if (tabRoutes.getModel().getRowCount() > 0) {
                    tabRoutes.updateUI();
                }
            } else {
                if (l_cur_trip_no_end < getCurSchNoTrips()) {
                    setCurTripStat(TRIP_END);
                    try {
                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.trip_end_pkt();

                            obj16833Pkts = null;
                        }
                    } catch (Exception ex) {
                    }
                    if (clsSharedVariables.getIpAddr4Enable()) {

                        objDriving.trip_end_pkt();

                    }
                    if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                        objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_END);

                    }
//                    try {
//                        if (getApcStatus() != clsDefines.APC_STOP) {
//                            setApcStatus(clsDefines.APC_STOP);
//                            objApc.apc_end_packet();
//
//                        }
//                    } catch (Exception ex) {
//
//                    }
//                    SwingWorker sw1 = new SwingWorker() {
//                        @Override
//                        protected String doInBackground() throws Exception {
//                            try {
//                                peopleCountRouteEnd((byte) 6);
//                            } catch (Exception ex) {
//
//                            }
//                            try {
//                                StringBuilder sb = new StringBuilder();
//                                sb.append(Calendar.getInstance().getTimeInMillis());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcStartTime());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcStopTime());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcPeopleIn());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcPeopleOut());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcStopName());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcRouteNo());
//                                sb.append(",");
//
//                                objReadDataFiles.write_apc_route_data(sb.toString());
//                                try {
//                                    peopleCountRouteEnd((byte) 7);
//                                } catch (Exception ex) {
//
//                                }
//                                StringBuilder sb1 = new StringBuilder();
//                                sb1.append(Calendar.getInstance().getTimeInMillis());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcStartTime());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcStopTime());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcPeopleIn1());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcPeopleOut1());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcStopName());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcRouteNo());
//                                sb1.append(",");
//                                objReadDataFiles.write_apc_route_data1(sb1.toString());
//                            } catch (Exception ex) {
//
//                            }
//                             try {
//                    if (clsSharedVariables.getIpAddr1Enable()) {
//                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
//                       // System.out.println(clsSharedVariables.getApcPeopleIn() + clsSharedVariables.getApcPeopleOut() + clsSharedVariables.getApcPeopleIn1() + clsSharedVariables.getApcPeopleOut1());
//                        obj16833Pkts.people_count_packet_on_route_end();
//                        obj16833Pkts = null;
//                    }
//                } catch (Exception ex) {
//                }
//                            return "Over";
//                        }
//                    };
//                    sw1.execute();
                   
                    setCurRouteStat(false);
                    setCurSchTripStatus(l_cur_trip_no_end, TRIP_END);
                    if (l_cur_trip_no_end <= getCurSchNoTrips()) {
                        incCurTripNo();

                    } else {

                        setCurStopNo((byte) 0);
                        return;
                    }

                    // objSch.send_route_info();
                    setCurStopNo((byte) 0);
                    setCurRouteNo(getCurSchRouteNo(l_cur_trip_no_end));

                    objReadFiles.write_cur_trip_info_file();
                    objReadFiles.write_cur_route_info_file();
                    objReadFiles.write_cur_schedule_file();
                    selected_trip_no = l_cur_trip_no_end;
                    if (tabRoutes.getModel().getRowCount() > 0) {
                        tabRoutes.updateUI();
                    }

                } else {

                    return;
                }
            }
        } else {
            clsBusStopDetection.gps_bus_stop_state = clsSwitchStates.BUS_STOP_STATE_IDLE;
            setShowStopStopDis((double) 0.0);
            //  setCurStopNo((byte) 0);
            if (getCurTripStat() == TRIP_START) {

                byte schedule_type = getSchRouteEnable();

                if (schedule_type == ROUTE_ENABLE) {
                    btnRouteSkip.setVisible(false);
                    try {
                        if (clsSharedVariables.getIpAddr1Enable()) {
                            cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.trip_end_pkt();

                            obj16833Pkts = null;
                        }
                    } catch (Exception ex) {
                    }
                    if (clsSharedVariables.getIpAddr4Enable()) {

                        objDriving.trip_end_pkt();

                    }
                    if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                        objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_END);

                    }
//                    try {
//                        if (getApcStatus() != clsDefines.APC_STOP) {
//                            setApcStatus(clsDefines.APC_STOP);
//                            objApc.apc_end_packet();
//
//                        }
//                    } catch (Exception ex) {
//                    }
//                    SwingWorker sw1 = new SwingWorker() {
//                        @Override
//                        protected String doInBackground() throws Exception {
//                            try {
//                                peopleCountRouteEnd((byte) 6);
//                            } catch (Exception ex) {
//
//                            }
//                            try {
//                                StringBuilder sb = new StringBuilder();
//                                sb.append(Calendar.getInstance().getTimeInMillis());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcStartTime());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcStopTime());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcPeopleIn());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcPeopleOut());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcStopName());
//                                sb.append(",");
//                                sb.append(clsSharedVariables.getApcRouteNo());
//                                sb.append(",");
//
//                                objReadDataFiles.write_apc_route_data(sb.toString());
//                                try {
//                                    peopleCountRouteEnd((byte) 7);
//                                } catch (Exception ex) {
//
//                                }
//                                StringBuilder sb1 = new StringBuilder();
//                                sb1.append(Calendar.getInstance().getTimeInMillis());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcStartTime());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcStopTime());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcPeopleIn1());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcPeopleOut1());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcStopName());
//                                sb1.append(",");
//                                sb1.append(clsSharedVariables.getApcRouteNo());
//                                sb1.append(",");
//                                objReadDataFiles.write_apc_route_data1(sb1.toString());
//                            } catch (Exception ex) {
//                            }
//                             try {
//                    if (clsSharedVariables.getIpAddr1Enable()) {
//                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
//                        //System.out.println(clsSharedVariables.getApcPeopleIn() + clsSharedVariables.getApcPeopleOut() + clsSharedVariables.getApcPeopleIn1() + clsSharedVariables.getApcPeopleOut1());
//                        obj16833Pkts.people_count_packet_on_route_end();
//                        obj16833Pkts = null;
//                    }
//                } catch (Exception ex) {
//                }
//                            return "Over";
//                        }
//                    };
//                    sw1.execute();
                  
                    setCurStopNo((byte) 0);
                    setCurTripStat(TRIP_END);
                    setCurRouteStat(false);

                    for (k = 0; k < getNoRoutes(); k++) {
                        setCurSchTripStatus(k, TRIP_UNKNOWN);
                        setCurRouteStat(false);
                    }

                    setCurSchTripStatus(l_cur_trip_no_end, TRIP_END);

                    objReadFiles.write_cur_trip_info_file();
                    objReadFiles.write_cur_route_info_file();
                    objReadFiles.write_cur_schedule_file();
                    if (tabRoutes.getModel().getRowCount() > 0) {
                        tabRoutes.updateUI();
                    }

                } else {
                    if (l_cur_trip_no_end < getCurSchNoTrips()) {
                        setCurTripStat(TRIP_END);
                        try {
                            if (clsSharedVariables.getIpAddr1Enable()) {
                                cls16833Protocols obj16833Pkts = new cls16833Protocols();
                                obj16833Pkts.trip_end_pkt();

                                obj16833Pkts = null;
                            }
                        } catch (Exception ex) {
                        }
                        if (clsSharedVariables.getIpAddr4Enable()) {

                            objDriving.trip_end_pkt();

                        }
                        if (clsSharedVariables.getIpAddr5Enable() && clsSharedVariables.getSuratProtocol() == false) {

                            objDriving.obu_cs_event_pkt_construction(clsDefines.PKT_TYPE_TRIP_END);

                        }
//                        try {
//                            if (getApcStatus() != clsDefines.APC_STOP) {
//                                setApcStatus(clsDefines.APC_STOP);
//                                objApc.apc_end_packet();
//
//                            }
//                        } catch (Exception ex) {
//                        }
//                        SwingWorker sw1 = new SwingWorker() {
//                            @Override
//                            protected String doInBackground() throws Exception {
//                                try {
//                                    peopleCountRouteEnd((byte) 6);
//                                } catch (Exception ex) {
//
//                                }
//                                try {
//                                    StringBuilder sb = new StringBuilder();
//                                    sb.append(Calendar.getInstance().getTimeInMillis());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcStartTime());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcStopTime());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcPeopleIn());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcPeopleOut());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcStopName());
//                                    sb.append(",");
//                                    sb.append(clsSharedVariables.getApcRouteNo());
//                                    sb.append(",");
//
//                                    objReadDataFiles.write_apc_route_data(sb.toString());
//                                    try {
//                                        peopleCountRouteEnd((byte) 7);
//                                    } catch (Exception ex) {
//
//                                    }
//                                    StringBuilder sb1 = new StringBuilder();
//                                    sb1.append(Calendar.getInstance().getTimeInMillis());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcStartTime());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcStopTime());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcPeopleIn1());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcPeopleOut1());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcStopName());
//                                    sb1.append(",");
//                                    sb1.append(clsSharedVariables.getApcRouteNo());
//                                    sb1.append(",");
//                                    objReadDataFiles.write_apc_route_data1(sb1.toString());
//                                } catch (Exception ex) {
//                                }
//                                 try {
//                    if (clsSharedVariables.getIpAddr1Enable()) {
//                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
//                       // System.out.println(clsSharedVariables.getApcPeopleIn() + clsSharedVariables.getApcPeopleOut() + clsSharedVariables.getApcPeopleIn1() + clsSharedVariables.getApcPeopleOut1());
//                        obj16833Pkts.people_count_packet_on_route_end();
//                        obj16833Pkts = null;
//                    }
//                } catch (Exception ex) {
//                }
//                                return "Over";
//                            }
//                        };
//                        sw1.execute();
                       
                        setCurRouteStat(false);
                        setCurStopNo((byte) 0);
                        setCurSchTripStatus(l_cur_trip_no_end, TRIP_END);
                        if (l_cur_trip_no_end <= getCurSchNoTrips()) {
                            incCurTripNo();
                            if (l_cur_trip_no_end > getCurSchNoTrips()) {
                                /*  notificationPane = new NotificationPane(this);
                                 notificationPane.setText(getCurSchRouteNo(l_cur_trip_no_end) + " All Trips Are Completed");
                                 notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
                                 notificationPane.show();*/
                            }
                        } else {
                            /*  notificationPane = new NotificationPane(this);
                             notificationPane.setText(getCurSchRouteNo(l_cur_trip_no_end) + " All Trips Are Completed");
                             notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
                             notificationPane.show();*/
                            return;
                        }

                        // objSch.send_route_info();
                        setCurStopNo((byte) 0);
                        setCurRouteNo(getCurSchRouteNo(l_cur_trip_no_end));

                        objReadFiles.write_cur_trip_info_file();
                        objReadFiles.write_cur_route_info_file();
                        objReadFiles.write_cur_schedule_file();
                        if (tabRoutes.getModel().getRowCount() > 0) {
                            tabRoutes.updateUI();
                        }

                    } else {
                        /* notificationPane = new NotificationPane(this);
                         notificationPane.setText(getCurSchRouteNo(l_cur_trip_no_end) + " All Trips Are Completed");
                         notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
                         notificationPane.show();*/
                        return;
                    }
                }
            }
            if (getDriverStopCnt() > 0) {
                //  bus_driver_message();
                setDriverStopCnt((short) 0);
                PanDriverHaltMessage objPanel = new PanDriverHaltMessage(panMainPane, panCenterMainPane);
                panMainPane.removeAll();
                panMainPane.setLayout(new java.awt.BorderLayout());
                panMainPane.add(objPanel);
                panMainPane.revalidate();
                panMainPane.repaint();
                objPanel = null;

            }
        }
        // ✅ Clear stored data since trip ended
try {
    clsReadDataFiles objReadData = new clsReadDataFiles();
    objReadData.clear_apc_stored_data(); // method will overwrite with empty file
} catch (Exception ex) {
    System.out.println("[WARN] Failed to clear apc_stored_data at route end: " + ex.getMessage());
}

    }//GEN-LAST:event_btnRouteEndActionPerformed

    private void btnMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapActionPerformed
        // TODO add your handling code here:
        //panMap objPanel = new panMap(panMainPane, panCenterMainPane);

        if (clsSharedVariables.getMapsEnable() == true) {
            if (clsDefines.COMPANY_NAME_NMEA_PROT == clsDefines.COMP_AMINEX) {

                Map objPanel = new Map(panMainPane, panCenterMainPane); // Map(panMainPane, panCenterMainPane);
                panMainPane.removeAll();
                panMainPane.setLayout(new java.awt.BorderLayout());
                panMainPane.add(objPanel);
                panMainPane.revalidate();
                panMainPane.repaint();
                try {
                    if (keypad_process != null) {
                        keypad_process = null;
                    }
                } catch (Exception ex) {
                }
            } else {
                panMap objPanel = new panMap(panMainPane, panCenterMainPane);
                panMainPane.removeAll();
                panMainPane.setLayout(new java.awt.BorderLayout());
                panMainPane.add(objPanel);
                panMainPane.revalidate();
                panMainPane.repaint();
                try {
                    if (keypad_process != null) {
                        keypad_process = null;
                    }
                } catch (Exception ex) {
                }
            }
        } else {
            panMap objPanel = new panMap(panMainPane, panCenterMainPane);
            panMainPane.removeAll();
            panMainPane.setLayout(new java.awt.BorderLayout());
            panMainPane.add(objPanel);
            panMainPane.revalidate();
            panMainPane.repaint();
            try {
                if (keypad_process != null) {
                    keypad_process = null;
                }
            } catch (Exception ex) {
            }
        }

    }//GEN-LAST:event_btnMapActionPerformed

    private void inputSearchInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_inputSearchInputMethodTextChanged


    }//GEN-LAST:event_inputSearchInputMethodTextChanged

    private void inputSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_inputSearchKeyTyped

    private void inputSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputSearchKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_inputSearchKeyReleased
    private void btnManualRouteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManualRouteActionPerformed
        // TODO add your handling code here: 

        PanManualRoute objPanel = new PanManualRoute(panMainPane, panCenterMainPane);
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
        try {
            if (keypad_process != null) {
                keypad_process = null;
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_btnManualRouteActionPerformed

    private void inputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputSearchActionPerformed

    private void inputSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusGained
        // TODO add your handling code here:
        if (!prev_control_name.equals("Search")) {
            clsKeyBoardView obj = new clsKeyBoardView();
            String str = obj.getKeyBoardValue("Search", "");
            if (str != null) {
                inputSearch.setText(str);
            }
            prev_control_name = "Search";
            obj = null;
            str = null;
            tabRoutes.getSelectionModel().setLeadSelectionIndex(0);
            inputSearch.transferFocusUpCycle();
        } else {
            prev_control_name = "";
        }

    }//GEN-LAST:event_inputSearchFocusGained

    private void inputSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputSearchFocusLost
        // TODO add your handling code here: 
        prev_control_name = "";
        String searchString = inputSearch.getText().toString();

        int i = 0;
        int k = 0;

        if (getCurTripStat() != TRIP_START) {
            tabRoutes.getSelectionModel().setLeadSelectionIndex(0);
            if (searchString.trim().equals("")) {
                model.setNumRows(getCurSchNoTrips());
                tabRoutes.removeAll();
                tabRoutes.setModel(model);
                for (i = 0; i < getCurSchNoTrips(); i++) {

                    tabRoutes.setValueAt(objCurRouteStruct[i].trip_no, k, 0);
                    tabRoutes.setValueAt(objCurRouteStruct[i].route_no, k, 1);
                    tabRoutes.setValueAt(objCurRouteStruct[i].route_name, k, 2);
                    k++;

                }

            } else {
                for (i = 0; i < getCurSchNoTrips(); i++) {
                    if (objCurRouteStruct[i].route_no.toUpperCase().contains(searchString.toUpperCase())) { //cur_no_routes[k])) {
                        k++;
                    }
                }

                model.setNumRows(k);//searchList.size());
                tabRoutes.removeAll();
                tabRoutes.setModel(model);

                k = 0;
                for (i = 0; i < getCurSchNoTrips(); i++) {
                    if (objCurRouteStruct[i].route_no.toUpperCase().contains(searchString.toUpperCase())) {//cur_no_routes[k])) {

                        tabRoutes.setValueAt(objCurRouteStruct[i].trip_no, k, 0);
                        tabRoutes.setValueAt(objCurRouteStruct[i].route_no, k, 1);
                        tabRoutes.setValueAt(objCurRouteStruct[i].route_name, k, 2);
                        k++;
                    }

                }

            }
            try {

                tabRoutes.setVisible(true);
                tabRoutes.updateUI();
                tabRoutes.getSelectionModel().setLeadSelectionIndex(0);

            } catch (Exception ex) {

            }
        } else {
            inputSearch.setText("");
        }
    }//GEN-LAST:event_inputSearchFocusLost

    private void tabRoutesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabRoutesMouseClicked
        // TODO add your handling code here:
        if (getCurTripStat() != TRIP_START) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            selected_trip_no = (short) row;
        }

    }//GEN-LAST:event_tabRoutesMouseClicked

    private void tabRoutesPropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnManualRoute;
    private javax.swing.JButton btnMap;
    private javax.swing.JButton btnRouteEnd;
    private javax.swing.JButton btnRouteSkip;
    private javax.swing.JButton btnRouteStart;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblRouteNo;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSrcDes;
    private javax.swing.JLabel lblTripNo;
    private javax.swing.JPanel panBottomRoute;
    private javax.swing.JTable tabRoutes;
    // End of variables declaration//GEN-END:variables
}
