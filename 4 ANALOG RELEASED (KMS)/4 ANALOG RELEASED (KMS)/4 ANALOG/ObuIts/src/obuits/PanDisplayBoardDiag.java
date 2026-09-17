package obuits;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.nio.ByteBuffer;
import javax.swing.ImageIcon;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import static obuits.MainFrmIts.lblFD;
import static obuits.MainFrmIts.lblID;
import static obuits.MainFrmIts.lblIDArt;
import static obuits.MainFrmIts.lblRD;
import static obuits.MainFrmIts.lblSD;
import static obuits.MainFrmIts.lblSDArt;
import static obuits.PanDisplayBoardDiag.setDisBrdLnkChkCame;
import static obuits.PanDisplayBoardDiag.DTC_CODEALL_PKT;
import static obuits.PanDisplayBoardDiag.front_cur_temp;
import static obuits.PanDisplayBoardDiag.front_cur_volt;
import static obuits.PanDisplayBoardDiag.front_high_volt;
import static obuits.PanDisplayBoardDiag.front_low_volt;
import static obuits.PanDisplayBoardDiag.front_over_heat;
import static obuits.clsDefines.FRONT_DB;
import static obuits.clsDefines.INT_DB;
import static obuits.clsDefines.INT_DB_ART;
import static obuits.clsDefines.REAR_DB;
import static obuits.clsDefines.SIDE_DB;
import static obuits.clsDefines.SIDE_DB_ART;
import static obuits.clsDefines.TIME_DISABLED;
import static obuits.clsDefines.TIME_ENABLED;
import static obuits.clsSharedVariables.getArtIntEnable;
import static obuits.clsSharedVariables.getArtSideEnable;
import static obuits.clsSharedVariables.getArticulatedBus;
import static obuits.clsSharedVariables.getFrontEnable;
import static obuits.clsSharedVariables.getIntEnable;
import static obuits.clsSharedVariables.getRearEnable;
import static obuits.clsSharedVariables.getSideEnable;

public class PanDisplayBoardDiag extends javax.swing.JPanel {
    static final byte MAX_NO_DISPLAYBOARDS = 6;
    public static final byte DATA_PKT = (byte) 128;
    public static final byte LINK_CHECK_PKT = (byte) 129;
    public static final byte DISPLAY_ENABLE_DISABLE_PKT = (byte) 130;
    public static final byte TIME_PKT = (byte) 131;
    public static final byte TEST_PKT = (byte) 132;
    public static final byte SOFTREST_PKT = (byte) 133;
    public static final byte PID_CODE_PKT = (byte) 134;
    public static final byte DTC_CODE_PKT = (byte) 135;
    public static final byte DTC_COUNT_CODE_PKT = (byte) 136;
    public static final byte DELETE_PKT = (byte) 139;
    public static final byte DTC_CODEALL_PKT = (byte) 140;
    public static final byte PID_CODEALL_PKT = (byte) 141;
    public static final byte DISBRD_SET_BRD_ADDR_CONFIG_PKT = (byte) 0xA0;
    public static final byte DISBRD_GET_BRD_ADDR_CONFIG_PKT = (byte) 0xA1;
    public static final byte TESTC_PKT = (byte) 176;
    public static final byte CONFIGURATION_INTENSITY_PKT = (byte) 0x8A;
    public static final byte DATA_RES_PKT = (byte) 192;
    public static final byte LINK_CHECK_RES_PKT = (byte) 193;
    public static final byte DISPLAY_ENABLE_DISABLE_RES_PKT = (byte) 194;
    public static final byte TIME_RES_PKT = (byte) 195;
    public static final byte TEST_RES_PKT = (byte) 196;
    public static final byte SOFTREST_RES_PKT = (byte) 197;
    public static final byte PID_CODE_RES_PKT = (byte) 198;
    public static final byte DTC_CODE_RES_PKT = (byte) 199;
    public static final byte DTC_COUNT_CODE_RES_PKT = (byte) 200;
    public static final byte DELETE_PKT_RES_PKT = (byte) 203;
    public static final byte TESTC_RES_PKT = (byte) 240;
    public static final byte CONFIGURATION_INTENSITY_RES_PKT = (byte) 0xCA;
    public static final byte DTC_CODEALL_RES_PKT = (byte) 0xCC;
    public static final byte PID_CODEALL_RES_PKT = (byte) 0xCD;
    public static final byte DISBRD_SET_BRD_ADDR_CONFIG_RES_PKT = (byte) (0xA0 + 0x40);
    public static final byte DISBRD_GET_BRD_ADDR_CONFIG_RES_PKT = (byte) (0xA1 + 0x40);
    private static final byte MAX_SEC_WAIT_TIME = 15;
    final ImageIcon fd_fail_icon;
    final ImageIcon sd_fail_icon;
    final ImageIcon rd_fail_icon;
    final ImageIcon id_fail_icon;
    private static final byte TESTC_ENABLE = 1;
    private static final byte TESTC_DISABLE = 0;
    public static byte[] disBrdLinkChkBuf = new byte[15];
    private static boolean lnkChkDisPktCame = false;
    private static boolean deleteDisPktCame = false;
    private static byte selected_button;
    private final byte BTN_LINK = 0;
    private final byte BTN_RESET = 1;
    private final byte BTN_TEST = 2;
    private final byte BTN_TESTCONT = 3;
    private final byte BTN_DELMSG = 4;
    private final byte BTN_DTC = 5;
    private final byte BTN_INTENSITY = 6;
    private final byte BTN_BRDADDR_SET = 7;
    private final byte BTN_BRDADDR_GET = 8;

    public PanDisplayBoardDiag() {
        initComponents();
        clsSharedVariables.setDiagDispForm(true);
        tabDisplayPid.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tabDisplayPid.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabDisplayPid.getColumnModel().getColumn(1).setPreferredWidth(50);
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
        setDisBrdLnkChkCame(false);
        radBrdAddr.setVisible(false);
        radDtc.setVisible(false);
        int inc = 0;
        tabDisplayDtc.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tabDisplayDtc.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabDisplayDtc.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabDisplayDtc.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabDisplayDtc.setValueAt(front_high_volt, inc++, 1);
        tabDisplayDtc.setValueAt(front_low_volt, inc++, 1);
        tabDisplayDtc.setValueAt(front_over_heat, inc++, 1);
        tabDisplayDtc.setValueAt(front_cur_temp, inc++, 1);
        tabDisplayDtc.setValueAt(front_cur_volt, inc++, 1);
        setDisBrdLnkChkCame(false);
        clsSharedVariables.setDiagDispForm(true);
        lblIntVal.setVisible(false);
        cmbIntVal.setVisible(false);
        this.panIntensity.setVisible(false);
        this.panDelMsg.setVisible(false);
        panTestCont.setVisible(false);
        panDtc.setVisible(false);
        spinDtcVal.setVisible(false);
        pbar.setVisible(false);
        panBrdAddr.setVisible(false);
        pbar.setMaximum(MAX_SEC_WAIT_TIME);

        if (clsSharedVariables.int_disbrd_time_enabled == TIME_DISABLED) {
            this.chkIntTimeEnabled.setSelected(false);
        } else {
            this.chkIntTimeEnabled.setSelected(true);
        }
        if (getArticulatedBus() == true) {
            if (getArtSideEnable()) {
                btnSideLink1.setVisible(true);
            } else {
                btnSideLink1.setVisible(false);
            }
            if (getArtIntEnable()) {
                btnInternalLink1.setVisible(true);
            } else {
                btnInternalLink1.setVisible(false);
            }
        }

        fd_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/fd_fail.png"));
        sd_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/sd_fail.png"));
        rd_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/rd_fail.png"));
        id_fail_icon = new javax.swing.ImageIcon(getClass().getResource("/Images/id_fail.png"));
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        clsSharedVariables.setDiagDispForm(false);
        setDisBrdLnkChkCame(false);
    }
    public static int front_cur_volt = 0;
    public static int front_cur_temp = 0;

    public static int side_cur_volt = 0;
    public static int side_cur_temp = 0;

    public static int rear_cur_volt = 0;
    public static int rear_cur_temp = 0;

    public static int int_cur_volt = 0;
    public static int int_cur_temp = 0;

    public static int sideart_cur_volt = 0;
    public static int sideart_cur_temp = 0;

    public static int intart_cur_volt = 0;
    public static int intart_cur_temp = 0;

    public static int front_high_volt = 0;
    public static int front_low_volt = 0;
    public static int front_over_heat = 0;
    public static int front_high_volt_cnt = 0;
    public static int front_low_volt_cnt = 0;
    public static int front_over_heat_cnt = 0;

    public static int side_high_volt = 0;
    public static int side_low_volt = 0;
    public static int side_over_heat = 0;

    public static int side_high_volt_cnt = 0;
    public static int side_low_volt_cnt = 0;
    public static int side_over_heat_cnt = 0;

    public static int rear_high_volt = 0;
    public static int rear_low_volt = 0;
    public static int rear_over_heat = 0;

    public static int rear_high_volt_cnt = 0;
    public static int rear_low_volt_cnt = 0;
    public static int rear_over_heat_cnt = 0;

    public static int int_high_volt = 0;
    public static int int_low_volt = 0;
    public static int int_over_heat = 0;

    public static int int_high_volt_cnt = 0;
    public static int int_low_volt_cnt = 0;
    public static int int_over_heat_cnt = 0;

    public static int sideart_high_volt = 0;
    public static int sideart_low_volt = 0;
    public static int sideart_over_heat = 0;

    public static int sideart_high_volt_cnt = 0;
    public static int sideart_low_volt_cnt = 0;
    public static int sideart_over_heat_cnt = 0;

    public static int intart_high_volt = 0;
    public static int intart_low_volt = 0;
    public static int intart_over_heat = 0;

    public static int intart_high_volt_cnt = 0;
    public static int intart_low_volt_cnt = 0;
    public static int intart_over_heat_cnt = 0;

    public static class clsBrdLnkRstTst {

        public boolean linkCkeck;
        public boolean reset;
        public boolean test;
        public boolean intensity;
        public boolean dtcCodes;
        public boolean delPkt;
        public boolean test_cont;
        public boolean setAddressPkt;
        public boolean getAddressPkt;
    }
    public static clsBrdLnkRstTst[] objBrdLnkRstTst = new clsBrdLnkRstTst[MAX_NO_DISPLAYBOARDS];
    public static final byte PID_READ = 2;
    public static final int DTC_WATCHDOG_RESET = 0012;
    public static final int DTC_LOW_VOLTAGE_RESET = 0013;
    public static final int DTC_GPS_LOST_COMM = 0020;
    public static final int DTC_GPS_INVALID_DATA = 0021;
    public static final int DTC_GPS_ANTENNA_ERROR = 0022;
    public static final int DTC_USB_INVALID = 0025;
    public static final int DTC_USB_UNKNOWN = 0026;
    public static final int DTC_USB_INVALID_FILESYSTEM = 0027;
    public static final int DTC_USB_OVERCURRENT = 0007;
    public static final int DTC_OBU_OVERVOLTAGE = 0200;
    public static final int DTC_OBU_LOWVOLTAGE = 0201;
    public static final int DTC_OBU_OVERHEAT = 0203;

    public static final int DTC_HIGH_VOLTAGE = 1200;
    public static final int DTC_LOW_VOLTAGE = 1201;
    public static final int DTC_OVER_HEAT_VOLTAGE = 1202;

    public static final short PID_HW_REV = 100;
    public static final short PID_SER_NO = 101;
    public static final short PID_BOOT_LOADER_SW_REV = 102;
    public static final short PID_APP_SW_REV = 103;
    public static final short PID_FONT_LIB_REV = 104;
    public static final short PID_CPU_PART_NO = 105;
    public static final short PID_CPU_QUALIFICATION = 106;
    public static final short PID_CPU_TEMP_RANGE = 107;
    public static final short PID_COMPILATION_FW_DATE_TIME = 108;
    public static final short PID_FLASH_UPATE_STATUS = 109;
    public static final short PID_TEST_DATE_TIME = 110;
    public static final short PID_ARTICLE_NO_SIGN_LEVEL = 114;
    public static final short PID_PRODUCTION_DATE = 115;
    public static final short PID_END_CUSTOMER = 116;
    public static final short PID_ORDER_NO = 117;
    public static final short PID_VEHICLE_TYPE = 118;
    public static final short PID_BUS_BUILDER_NO = 119;
    public static final short PID_LANGUAGE = 208;
    public static final short PID_BOARD_TEMP_SENSOR = 401;
    public static final short PID_INTERNAL_CPU_TEMP = 402;
    public static final short PID_MIN_TEMP_CPU = 600;
    public static final short PID_MAX_TEMP_CPU = 601;
    public static final short PID_MAX_TEMP_BOARD = 602;
    public static final short PID_MIN_TEMP_BOARD = 603;
    public static final short PID_MAX_INPUT_VOLT = 604;
    public static final short PID_MIN_INPUT_VOLT = 605;
    public static final short PID_OPERATING_HRS = 606;
    public static final short PID_NO_RESETS = 607;
    public static final byte NO_PID_CODES = 31;

    public static class pid_codes {

        public String hw_rev_val;
        public String ser_no;
        public String boot_loader_sw_rev;
        public String app_sw_rev;
        public String font_lib_rev;
        public String cpu_part_no;
        public String cpu_qual;
        public String cpu_temp_range;
        public String compilation_fw_date;
        public String flash_update_status;
        public String test_date_time;
        public String article_no_sign_level;
        public String prod_date;
        public String end_customer;
        public String order_no;
        public String vehicle_type;
        public String bus_builder_no;
        public byte language;
        public byte language1;
        public byte language2;
        public byte language3;
        public String board_temp_sensor;
        public String internal_cpu_temp;
        public String min_cpu_temp;
        public String max_cpu_temp;
        public String min_board_temp;
        public String max_board_temp;
        public String min_input_volt;
        public String max_input_volt;
        public long operating_hours;
        public int no_resets;
    }

    /**
     *
     */
    public static pid_codes[] objPids = new pid_codes[MAX_NO_DISPLAYBOARDS];

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btndisplaylinkgrp = new javax.swing.ButtonGroup();
        btnGrpManual = new javax.swing.ButtonGroup();
        btnGrpsingle = new javax.swing.ButtonGroup();
        btnGrpEnable = new javax.swing.ButtonGroup();
        btnGrpVolt = new javax.swing.ButtonGroup();
        btnGrpGetSet = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnFront = new javax.swing.JButton();
        btnSide = new javax.swing.JButton();
        btnInternal = new javax.swing.JButton();
        btnRear = new javax.swing.JButton();
        btnSideArt = new javax.swing.JButton();
        btnInternalArt = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabDisplayPid = new javax.swing.JTable();
        lblPID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnFront1 = new javax.swing.JButton();
        btnSide1 = new javax.swing.JButton();
        btnInternal1 = new javax.swing.JButton();
        btnRear1 = new javax.swing.JButton();
        btnSide2 = new javax.swing.JButton();
        btnInternal2 = new javax.swing.JButton();
        lblDTC = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabDisplayDtc = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnFrontLink = new javax.swing.JButton();
        btnSideLink = new javax.swing.JButton();
        btnRearLink = new javax.swing.JButton();
        btnInternalLink = new javax.swing.JButton();
        btnSideLink1 = new javax.swing.JButton();
        btnInternalLink1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        radTestCont = new javax.swing.JRadioButton();
        radReset = new javax.swing.JRadioButton();
        radLink = new javax.swing.JRadioButton();
        radTest = new javax.swing.JRadioButton();
        radDelStoredMsg = new javax.swing.JRadioButton();
        radIntensity = new javax.swing.JRadioButton();
        radDtc = new javax.swing.JRadioButton();
        radBrdAddr = new javax.swing.JRadioButton();
        lblTest = new javax.swing.JLabel();
        panIntensity = new javax.swing.JPanel();
        radAutoInt = new javax.swing.JRadioButton();
        radManInt = new javax.swing.JRadioButton();
        cmbIntVal = new javax.swing.JComboBox();
        lblIntVal = new javax.swing.JLabel();
        chkIntTimeEnabled = new javax.swing.JCheckBox();
        panDelMsg = new javax.swing.JPanel();
        radDelAll = new javax.swing.JRadioButton();
        radDelSingle = new javax.swing.JRadioButton();
        spinDelValue = new javax.swing.JSpinner();
        pbar = new javax.swing.JProgressBar();
        panTestCont = new javax.swing.JPanel();
        radTestCEnable = new javax.swing.JRadioButton();
        radTestCDisable = new javax.swing.JRadioButton();
        panDtc = new javax.swing.JPanel();
        radDtcLow = new javax.swing.JRadioButton();
        radDtcOverheat = new javax.swing.JRadioButton();
        radNoResets = new javax.swing.JRadioButton();
        radOperatingHours = new javax.swing.JRadioButton();
        radDtcHigh = new javax.swing.JRadioButton();
        spinDtcVal = new javax.swing.JSpinner();
        jTextField3 = new javax.swing.JTextField();
        panBrdAddr = new javax.swing.JPanel();
        radBrdAddrGet = new javax.swing.JRadioButton();
        radBrdAddrSet = new javax.swing.JRadioButton();
        spinBrdAddrVal = new javax.swing.JSpinner();
        txtBrdWidth = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(516, 430));

        jPanel1.setBackground(new java.awt.Color(23, 29, 32));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));

        jPanel2.setBackground(new java.awt.Color(23, 29, 32));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 58));

        btnFront.setBackground(new java.awt.Color(85, 85, 85));
        btnFront.setFont(btnFront.getFont().deriveFont(btnFront.getFont().getStyle() | java.awt.Font.BOLD, btnFront.getFont().getSize()+4));
        btnFront.setForeground(new java.awt.Color(255, 255, 255));
        btnFront.setText("FRONT");
        btnFront.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFront.setPreferredSize(new java.awt.Dimension(80, 40));
        btnFront.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrontActionPerformed(evt);
            }
        });

        btnSide.setBackground(new java.awt.Color(85, 85, 85));
        btnSide.setFont(btnSide.getFont().deriveFont(btnSide.getFont().getStyle() | java.awt.Font.BOLD, btnSide.getFont().getSize()+4));
        btnSide.setForeground(new java.awt.Color(255, 255, 255));
        btnSide.setText("SIDE");
        btnSide.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSide.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSideActionPerformed(evt);
            }
        });

        btnInternal.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal.setFont(btnInternal.getFont().deriveFont(btnInternal.getFont().getStyle() | java.awt.Font.BOLD, btnInternal.getFont().getSize()+4));
        btnInternal.setForeground(new java.awt.Color(255, 255, 255));
        btnInternal.setText("INT");
        btnInternal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInternal.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInternalActionPerformed(evt);
            }
        });

        btnRear.setBackground(new java.awt.Color(85, 85, 85));
        btnRear.setFont(btnRear.getFont().deriveFont(btnRear.getFont().getStyle() | java.awt.Font.BOLD, btnRear.getFont().getSize()+4));
        btnRear.setForeground(new java.awt.Color(255, 255, 255));
        btnRear.setText("REAR");
        btnRear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRear.setPreferredSize(new java.awt.Dimension(80, 40));
        btnRear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRearActionPerformed(evt);
            }
        });

        btnSideArt.setBackground(new java.awt.Color(85, 85, 85));
        btnSideArt.setFont(btnSideArt.getFont().deriveFont(btnSideArt.getFont().getStyle() | java.awt.Font.BOLD, btnSideArt.getFont().getSize()+4));
        btnSideArt.setForeground(new java.awt.Color(255, 255, 255));
        btnSideArt.setText("ASIDE");
        btnSideArt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSideArt.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSideArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSideArtActionPerformed(evt);
            }
        });

        btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));
        btnInternalArt.setFont(btnInternalArt.getFont().deriveFont(btnInternalArt.getFont().getStyle() | java.awt.Font.BOLD, btnInternalArt.getFont().getSize()+4));
        btnInternalArt.setForeground(new java.awt.Color(255, 255, 255));
        btnInternalArt.setText("AINT");
        btnInternalArt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInternalArt.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInternalArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInternalArtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(btnFront, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnRear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnInternal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnSideArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnInternalArt, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnFront, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInternal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSideArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInternalArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(23, 29, 32));
        jTextField1.setFont(jTextField1.getFont().deriveFont(jTextField1.getFont().getStyle() | java.awt.Font.BOLD, jTextField1.getFont().getSize()+10));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(" PID CODES");
        jTextField1.setPreferredSize(new java.awt.Dimension(500, 32));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jScrollPane3.setPreferredSize(new java.awt.Dimension(500, 350));

        tabDisplayPid.setBackground(new java.awt.Color(240, 240, 240));
        tabDisplayPid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabDisplayPid.setFont(tabDisplayPid.getFont().deriveFont(tabDisplayPid.getFont().getSize()+5f));
        tabDisplayPid.setForeground(new java.awt.Color(0, 0, 102));
        tabDisplayPid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"HARDWARE REVISION", "1"},
                {"SERIAL NO", null},
                {"BOOT LOADER VERSION", null},
                {"APPLICATION  REVISION", null},
                {"FONT LIBRARY REVISION", null},
                {"CPU PART NO", null},
                {"CPU QUALIFICATION", null},
                {"CPU TEMP RANGE", null},
                {"COMPILE FW DATE & TIME", null},
                {"FLASH UPDATE STATUS", null},
                {"TEST DATE & TIME", null},
                {"ARTICLE NO SIGN LEVEL", null},
                {"PRODUCTION DATE", null},
                {"END CUSTOMER NO", null},
                {"ORDER NO", null},
                {"VEHICLE TYPE", null},
                {"BUS BODY BUILDER NO", null},
                {"LANGUAGE ", null},
                {"BOARD TEMP SENSOR", null},
                {"INTERNAL CPU TEMP", null},
                {"MIN CPU TEMP", null},
                {"MAX CPU TEMP", null},
                {"MIN BOARD TEMP ", null},
                {"MAX  BOARD TEMP ", null},
                {"MIN INPUT VOLTAGE", null},
                {"MAX INPUT VOLTAGE", null},
                {"OPERATING HOURS", null},
                {"NO OF RESETS", null}
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
        tabDisplayPid.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabDisplayPid.setPreferredSize(new java.awt.Dimension(503, 1700));
        tabDisplayPid.setRowHeight(30);
        tabDisplayPid.getTableHeader().setResizingAllowed(false);
        tabDisplayPid.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabDisplayPid);

        lblPID.setBackground(new java.awt.Color(0, 0, 0));
        lblPID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPID.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PID CODES", jPanel1);

        jPanel3.setBackground(new java.awt.Color(23, 29, 32));
        jPanel3.setPreferredSize(new java.awt.Dimension(420, 500));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(23, 29, 32));
        jTextField2.setFont(jTextField2.getFont().deriveFont(jTextField2.getFont().getStyle() | java.awt.Font.BOLD, jTextField2.getFont().getSize()+10));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(" DTC CODES");
        jTextField2.setPreferredSize(new java.awt.Dimension(505, 32));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(23, 29, 32));
        jPanel4.setPreferredSize(new java.awt.Dimension(503, 50));

        btnFront1.setBackground(new java.awt.Color(85, 85, 85));
        btnFront1.setFont(btnFront1.getFont().deriveFont(btnFront1.getFont().getStyle() | java.awt.Font.BOLD, btnFront1.getFont().getSize()+4));
        btnFront1.setForeground(new java.awt.Color(255, 255, 255));
        btnFront1.setText("FRONT");
        btnFront1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFront1.setPreferredSize(new java.awt.Dimension(80, 40));
        btnFront1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFront1ActionPerformed(evt);
            }
        });

        btnSide1.setBackground(new java.awt.Color(85, 85, 85));
        btnSide1.setFont(btnSide1.getFont().deriveFont(btnSide1.getFont().getStyle() | java.awt.Font.BOLD, btnSide1.getFont().getSize()+4));
        btnSide1.setForeground(new java.awt.Color(255, 255, 255));
        btnSide1.setText("SIDE");
        btnSide1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSide1.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSide1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSide1ActionPerformed(evt);
            }
        });

        btnInternal1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal1.setFont(btnInternal1.getFont().deriveFont(btnInternal1.getFont().getStyle() | java.awt.Font.BOLD, btnInternal1.getFont().getSize()+4));
        btnInternal1.setForeground(new java.awt.Color(255, 255, 255));
        btnInternal1.setText("INT");
        btnInternal1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInternal1.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInternal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInternal1ActionPerformed(evt);
            }
        });

        btnRear1.setBackground(new java.awt.Color(85, 85, 85));
        btnRear1.setFont(btnRear1.getFont().deriveFont(btnRear1.getFont().getStyle() | java.awt.Font.BOLD, btnRear1.getFont().getSize()+4));
        btnRear1.setForeground(new java.awt.Color(255, 255, 255));
        btnRear1.setText("REAR");
        btnRear1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRear1.setPreferredSize(new java.awt.Dimension(80, 40));
        btnRear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRear1ActionPerformed(evt);
            }
        });

        btnSide2.setBackground(new java.awt.Color(85, 85, 85));
        btnSide2.setFont(btnSide2.getFont().deriveFont(btnSide2.getFont().getStyle() | java.awt.Font.BOLD, btnSide2.getFont().getSize()+4));
        btnSide2.setForeground(new java.awt.Color(255, 255, 255));
        btnSide2.setText("ASIDE");
        btnSide2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSide2.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSide2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSide2ActionPerformed(evt);
            }
        });

        btnInternal2.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal2.setFont(btnInternal2.getFont().deriveFont(btnInternal2.getFont().getStyle() | java.awt.Font.BOLD, btnInternal2.getFont().getSize()+4));
        btnInternal2.setForeground(new java.awt.Color(255, 255, 255));
        btnInternal2.setText("AINT");
        btnInternal2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInternal2.setPreferredSize(new java.awt.Dimension(80, 40));
        btnInternal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInternal2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnFront1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSide1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRear1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInternal1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSide2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInternal2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnFront1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSide1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInternal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSide2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInternal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblDTC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDTC.setForeground(new java.awt.Color(255, 255, 255));
        lblDTC.setPreferredSize(new java.awt.Dimension(400, 450));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 350));

        tabDisplayDtc.setBackground(new java.awt.Color(240, 240, 240));
        tabDisplayDtc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabDisplayDtc.setFont(tabDisplayDtc.getFont().deriveFont(tabDisplayDtc.getFont().getSize()+5f));
        tabDisplayDtc.setForeground(new java.awt.Color(0, 0, 102));
        tabDisplayDtc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"High Voltage", null, null},
                {"Low Voltage", null, null},
                {"Over Heat", null, null},
                {"Current Temperature", null, null},
                {"Current Voltage", null, null},
                {"", null, null}
            },
            new String [] {
                "Parameter Name", "Value", "Counts"
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
        tabDisplayDtc.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tabDisplayDtc.setPreferredSize(new java.awt.Dimension(503, 300));
        tabDisplayDtc.setRowHeight(30);
        jScrollPane1.setViewportView(tabDisplayDtc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTC, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("DTC CODES", jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 570));

        btnFrontLink.setBackground(new java.awt.Color(0, 0, 102));
        btnFrontLink.setFont(btnFrontLink.getFont().deriveFont(btnFrontLink.getFont().getStyle() | java.awt.Font.BOLD, btnFrontLink.getFont().getSize()+6));
        btnFrontLink.setForeground(new java.awt.Color(255, 255, 255));
        btnFrontLink.setText("FRONT");
        btnFrontLink.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFrontLink.setPreferredSize(new java.awt.Dimension(80, 35));
        btnFrontLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFrontLinkfrontMouseClicked(evt);
            }
        });
        btnFrontLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrontLinkActionPerformed(evt);
            }
        });

        btnSideLink.setBackground(new java.awt.Color(0, 0, 102));
        btnSideLink.setFont(btnSideLink.getFont().deriveFont(btnSideLink.getFont().getStyle() | java.awt.Font.BOLD, btnSideLink.getFont().getSize()+6));
        btnSideLink.setForeground(new java.awt.Color(255, 255, 255));
        btnSideLink.setText("SIDE");
        btnSideLink.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSideLink.setPreferredSize(new java.awt.Dimension(80, 35));
        btnSideLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSideLinkActionPerformed(evt);
            }
        });

        btnRearLink.setBackground(new java.awt.Color(0, 0, 102));
        btnRearLink.setFont(btnRearLink.getFont().deriveFont(btnRearLink.getFont().getStyle() | java.awt.Font.BOLD, btnRearLink.getFont().getSize()+6));
        btnRearLink.setForeground(new java.awt.Color(255, 255, 255));
        btnRearLink.setText("REAR");
        btnRearLink.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRearLink.setPreferredSize(new java.awt.Dimension(80, 35));
        btnRearLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRearLinkActionPerformed(evt);
            }
        });

        btnInternalLink.setBackground(new java.awt.Color(0, 0, 102));
        btnInternalLink.setFont(btnInternalLink.getFont().deriveFont(btnInternalLink.getFont().getStyle() | java.awt.Font.BOLD, btnInternalLink.getFont().getSize()+6));
        btnInternalLink.setForeground(new java.awt.Color(255, 255, 255));
        btnInternalLink.setText("INT");
        btnInternalLink.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInternalLink.setPreferredSize(new java.awt.Dimension(80, 35));
        btnInternalLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInternalLinkActionPerformed(evt);
            }
        });

        btnSideLink1.setBackground(new java.awt.Color(0, 0, 102));
        btnSideLink1.setFont(btnSideLink1.getFont().deriveFont(btnSideLink1.getFont().getStyle() | java.awt.Font.BOLD, btnSideLink1.getFont().getSize()+6));
        btnSideLink1.setForeground(new java.awt.Color(255, 255, 255));
        btnSideLink1.setText("SIDE2");
        btnSideLink1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSideLink1.setPreferredSize(new java.awt.Dimension(80, 35));
        btnSideLink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSideLink1ActionPerformed(evt);
            }
        });

        btnInternalLink1.setBackground(new java.awt.Color(0, 0, 102));
        btnInternalLink1.setFont(btnInternalLink1.getFont().deriveFont(btnInternalLink1.getFont().getStyle() | java.awt.Font.BOLD, btnInternalLink1.getFont().getSize()+6));
        btnInternalLink1.setForeground(new java.awt.Color(255, 255, 255));
        btnInternalLink1.setText("INT2");
        btnInternalLink1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInternalLink1.setPreferredSize(new java.awt.Dimension(80, 35));
        btnInternalLink1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInternalLink1ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setPreferredSize(new java.awt.Dimension(503, 101));

        radTestCont.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radTestCont);
        radTestCont.setFont(radTestCont.getFont().deriveFont(radTestCont.getFont().getStyle() | java.awt.Font.BOLD, radTestCont.getFont().getSize()+3));
        radTestCont.setForeground(new java.awt.Color(0, 0, 102));
        radTestCont.setText("TEST CONT");
        radTestCont.setPreferredSize(new java.awt.Dimension(120, 30));
        radTestCont.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radTestContItemStateChanged(evt);
            }
        });
        radTestCont.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radTestContStateChanged(evt);
            }
        });
        radTestCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTestContActionPerformed(evt);
            }
        });

        radReset.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radReset);
        radReset.setFont(radReset.getFont().deriveFont(radReset.getFont().getStyle() | java.awt.Font.BOLD, radReset.getFont().getSize()+3));
        radReset.setForeground(new java.awt.Color(0, 0, 102));
        radReset.setText("RESET");
        radReset.setPreferredSize(new java.awt.Dimension(100, 30));
        radReset.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radResetItemStateChanged(evt);
            }
        });
        radReset.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radResetStateChanged(evt);
            }
        });
        radReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radResetActionPerformed(evt);
            }
        });

        radLink.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radLink);
        radLink.setFont(radLink.getFont().deriveFont(radLink.getFont().getStyle() | java.awt.Font.BOLD, radLink.getFont().getSize()+3));
        radLink.setForeground(new java.awt.Color(0, 0, 102));
        radLink.setSelected(true);
        radLink.setText("LINK");
        radLink.setPreferredSize(new java.awt.Dimension(100, 30));
        radLink.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radLinkItemStateChanged(evt);
            }
        });
        radLink.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radLinkStateChanged(evt);
            }
        });
        radLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radLinkActionPerformed(evt);
            }
        });

        radTest.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radTest);
        radTest.setFont(radTest.getFont().deriveFont(radTest.getFont().getStyle() | java.awt.Font.BOLD, radTest.getFont().getSize()+3));
        radTest.setForeground(new java.awt.Color(0, 0, 102));
        radTest.setText("TEST");
        radTest.setPreferredSize(new java.awt.Dimension(100, 30));
        radTest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radTestItemStateChanged(evt);
            }
        });
        radTest.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radTestStateChanged(evt);
            }
        });

        radDelStoredMsg.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radDelStoredMsg);
        radDelStoredMsg.setFont(radDelStoredMsg.getFont().deriveFont(radDelStoredMsg.getFont().getStyle() | java.awt.Font.BOLD, radDelStoredMsg.getFont().getSize()+3));
        radDelStoredMsg.setForeground(new java.awt.Color(0, 0, 102));
        radDelStoredMsg.setText("DEL MSGS");
        radDelStoredMsg.setPreferredSize(new java.awt.Dimension(100, 30));
        radDelStoredMsg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radDelStoredMsgItemStateChanged(evt);
            }
        });
        radDelStoredMsg.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radDelStoredMsgStateChanged(evt);
            }
        });
        radDelStoredMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDelStoredMsgActionPerformed(evt);
            }
        });

        radIntensity.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radIntensity);
        radIntensity.setFont(radIntensity.getFont().deriveFont(radIntensity.getFont().getStyle() | java.awt.Font.BOLD, radIntensity.getFont().getSize()+3));
        radIntensity.setForeground(new java.awt.Color(0, 0, 102));
        radIntensity.setText("INTENSITY");
        radIntensity.setPreferredSize(new java.awt.Dimension(120, 30));
        radIntensity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radIntensityItemStateChanged(evt);
            }
        });
        radIntensity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radIntensityStateChanged(evt);
            }
        });
        radIntensity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radIntensityActionPerformed(evt);
            }
        });

        radDtc.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radDtc);
        radDtc.setFont(radDtc.getFont().deriveFont(radDtc.getFont().getStyle() | java.awt.Font.BOLD, radDtc.getFont().getSize()+3));
        radDtc.setForeground(new java.awt.Color(0, 0, 102));
        radDtc.setText("DTC CODES");
        radDtc.setPreferredSize(new java.awt.Dimension(120, 30));
        radDtc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radDtcItemStateChanged(evt);
            }
        });
        radDtc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radDtcStateChanged(evt);
            }
        });
        radDtc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDtcActionPerformed(evt);
            }
        });

        radBrdAddr.setBackground(new java.awt.Color(204, 204, 204));
        btndisplaylinkgrp.add(radBrdAddr);
        radBrdAddr.setFont(radBrdAddr.getFont().deriveFont(radBrdAddr.getFont().getStyle() | java.awt.Font.BOLD, radBrdAddr.getFont().getSize()+3));
        radBrdAddr.setForeground(new java.awt.Color(0, 0, 102));
        radBrdAddr.setText("BRD ADDR");
        radBrdAddr.setPreferredSize(new java.awt.Dimension(120, 30));
        radBrdAddr.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radBrdAddrItemStateChanged(evt);
            }
        });
        radBrdAddr.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radBrdAddrStateChanged(evt);
            }
        });
        radBrdAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBrdAddrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(radLink, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(radReset, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(radTest, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radTestCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(radDelStoredMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radIntensity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radDtc, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radBrdAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(radLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radTestCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(radDelStoredMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radDtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radBrdAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        lblTest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTest.setForeground(new java.awt.Color(0, 102, 51));

        panIntensity.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panIntensity.setPreferredSize(new java.awt.Dimension(513, 45));
        panIntensity.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 5));

        btnGrpManual.add(radAutoInt);
        radAutoInt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radAutoInt.setForeground(new java.awt.Color(0, 0, 102));
        radAutoInt.setText("Auto");
        radAutoInt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radAutoIntItemStateChanged(evt);
            }
        });
        radAutoInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAutoIntActionPerformed(evt);
            }
        });
        panIntensity.add(radAutoInt);

        btnGrpManual.add(radManInt);
        radManInt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radManInt.setForeground(new java.awt.Color(0, 0, 102));
        radManInt.setSelected(true);
        radManInt.setText("Manual");
        radManInt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radManIntItemStateChanged(evt);
            }
        });
        radManInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radManIntActionPerformed(evt);
            }
        });
        panIntensity.add(radManInt);

        cmbIntVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbIntVal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100%", "90%", "80%", "70%", "60%", "50%", "40%", "30%", "20%", "10%", " " }));
        panIntensity.add(cmbIntVal);

        lblIntVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIntVal.setForeground(new java.awt.Color(0, 0, 102));
        lblIntVal.setText("Value");
        panIntensity.add(lblIntVal);

        chkIntTimeEnabled.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkIntTimeEnabled.setForeground(new java.awt.Color(0, 0, 102));
        chkIntTimeEnabled.setText("Display Time in ID");
        chkIntTimeEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIntTimeEnabledActionPerformed(evt);
            }
        });
        panIntensity.add(chkIntTimeEnabled);

        panDelMsg.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panDelMsg.setPreferredSize(new java.awt.Dimension(513, 45));

        btnGrpsingle.add(radDelAll);
        radDelAll.setFont(radDelAll.getFont().deriveFont(radDelAll.getFont().getStyle() | java.awt.Font.BOLD, radDelAll.getFont().getSize()+3));
        radDelAll.setForeground(new java.awt.Color(0, 0, 102));
        radDelAll.setText("All");
        radDelAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radDelAllItemStateChanged(evt);
            }
        });
        radDelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDelAllActionPerformed(evt);
            }
        });
        panDelMsg.add(radDelAll);

        btnGrpsingle.add(radDelSingle);
        radDelSingle.setFont(radDelSingle.getFont().deriveFont(radDelSingle.getFont().getStyle() | java.awt.Font.BOLD, radDelSingle.getFont().getSize()+3));
        radDelSingle.setForeground(new java.awt.Color(0, 0, 102));
        radDelSingle.setSelected(true);
        radDelSingle.setText("Single");
        radDelSingle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radDelSingleItemStateChanged(evt);
            }
        });
        radDelSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDelSingleActionPerformed(evt);
            }
        });
        panDelMsg.add(radDelSingle);

        spinDelValue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spinDelValue.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        panDelMsg.add(spinDelValue);

        pbar.setMaximum(10);
        pbar.setPreferredSize(new java.awt.Dimension(430, 35));

        panTestCont.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnGrpEnable.add(radTestCEnable);
        radTestCEnable.setFont(radTestCEnable.getFont().deriveFont(radTestCEnable.getFont().getStyle() | java.awt.Font.BOLD, radTestCEnable.getFont().getSize()+1));
        radTestCEnable.setForeground(new java.awt.Color(0, 0, 102));
        radTestCEnable.setText("Enable");
        panTestCont.add(radTestCEnable);

        btnGrpEnable.add(radTestCDisable);
        radTestCDisable.setFont(radTestCDisable.getFont().deriveFont(radTestCDisable.getFont().getStyle() | java.awt.Font.BOLD, radTestCDisable.getFont().getSize()+1));
        radTestCDisable.setForeground(new java.awt.Color(0, 0, 102));
        radTestCDisable.setSelected(true);
        radTestCDisable.setText("Disable");
        radTestCDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radTestCDisableActionPerformed(evt);
            }
        });
        panTestCont.add(radTestCDisable);

        panDtc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panDtc.setPreferredSize(new java.awt.Dimension(500, 45));

        btnGrpVolt.add(radDtcLow);
        radDtcLow.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radDtcLow.setForeground(new java.awt.Color(0, 0, 102));
        radDtcLow.setSelected(true);
        radDtcLow.setText("Low Volt");
        radDtcLow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDtcLowActionPerformed(evt);
            }
        });
        panDtc.add(radDtcLow);

        btnGrpVolt.add(radDtcOverheat);
        radDtcOverheat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radDtcOverheat.setForeground(new java.awt.Color(0, 0, 102));
        radDtcOverheat.setText("Over Heat");
        radDtcOverheat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDtcOverheatActionPerformed(evt);
            }
        });
        panDtc.add(radDtcOverheat);

        btnGrpVolt.add(radNoResets);
        radNoResets.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radNoResets.setForeground(new java.awt.Color(0, 0, 102));
        radNoResets.setText("No Resets");
        radNoResets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNoResetsActionPerformed(evt);
            }
        });
        panDtc.add(radNoResets);

        btnGrpVolt.add(radOperatingHours);
        radOperatingHours.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radOperatingHours.setForeground(new java.awt.Color(0, 0, 102));
        radOperatingHours.setText("Operating Hrs");
        radOperatingHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radOperatingHoursActionPerformed(evt);
            }
        });
        panDtc.add(radOperatingHours);

        btnGrpVolt.add(radDtcHigh);
        radDtcHigh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radDtcHigh.setForeground(new java.awt.Color(0, 0, 102));
        radDtcHigh.setText("High Volt");
        radDtcHigh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDtcHighActionPerformed(evt);
            }
        });
        panDtc.add(radDtcHigh);

        spinDtcVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spinDtcVal.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));
        panDtc.add(spinDtcVal);

        jTextField3.setBackground(new java.awt.Color(23, 29, 32));
        jTextField3.setFont(jTextField3.getFont().deriveFont(jTextField3.getFont().getStyle() | java.awt.Font.BOLD, jTextField3.getFont().getSize()+7));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("DIAGNOSIS");
        jTextField3.setPreferredSize(new java.awt.Dimension(513, 28));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        panBrdAddr.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panBrdAddr.setPreferredSize(new java.awt.Dimension(513, 45));

        btnGrpGetSet.add(radBrdAddrGet);
        radBrdAddrGet.setFont(radBrdAddrGet.getFont().deriveFont(radBrdAddrGet.getFont().getStyle() | java.awt.Font.BOLD, radBrdAddrGet.getFont().getSize()+3));
        radBrdAddrGet.setForeground(new java.awt.Color(0, 0, 102));
        radBrdAddrGet.setText("GET");
        radBrdAddrGet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radBrdAddrGetItemStateChanged(evt);
            }
        });
        radBrdAddrGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBrdAddrGetActionPerformed(evt);
            }
        });
        panBrdAddr.add(radBrdAddrGet);

        btnGrpGetSet.add(radBrdAddrSet);
        radBrdAddrSet.setFont(radBrdAddrSet.getFont().deriveFont(radBrdAddrSet.getFont().getStyle() | java.awt.Font.BOLD, radBrdAddrSet.getFont().getSize()+3));
        radBrdAddrSet.setForeground(new java.awt.Color(0, 0, 102));
        radBrdAddrSet.setSelected(true);
        radBrdAddrSet.setText("SET");
        radBrdAddrSet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radBrdAddrSetItemStateChanged(evt);
            }
        });
        radBrdAddrSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBrdAddrSetActionPerformed(evt);
            }
        });
        panBrdAddr.add(radBrdAddrSet);

        spinBrdAddrVal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        spinBrdAddrVal.setModel(new javax.swing.SpinnerNumberModel(2, 2, 10, 1));
        panBrdAddr.add(spinBrdAddrVal);

        txtBrdWidth.setFont(txtBrdWidth.getFont().deriveFont(txtBrdWidth.getFont().getStyle() | java.awt.Font.BOLD, txtBrdWidth.getFont().getSize()+4));
        txtBrdWidth.setText("160");
        panBrdAddr.add(txtBrdWidth);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(panDelMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panTestCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnFrontLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnSideLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnRearLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnInternalLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnSideLink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnInternalLink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTest, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(7, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panDtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panBrdAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panIntensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panDelMsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panTestCont, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(panDtc, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panBrdAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnFrontLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSideLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRearLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInternalLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSideLink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInternalLink1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTest, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("TEST", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
   public void link_display_board(byte index, short pkt_type) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;

        int buf_inc = 0;

        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4;

        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        }
        pid_buf[buf_inc++] = (byte) pkt_type;
        buf_inc = clsDefines.DATA_BYTE;

        pkt_length += (short) (buf_inc);
        buf = convertToByteArray((short) (pkt_length));

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB) 
        //  pkt_buf[LEN_LSB)

        pkt_length = (short) (pkt_length + 2);

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        disBrdLinkChkBuf = new byte[buf_inc + 2];
        // StringBuilder sb = new StringBuilder();
        for (i = 0; i < buf_inc + 2; i++) {
            disBrdLinkChkBuf[i] = pid_buf[i];
        }

        setDisBrdLnkChkCame(true);

    }

    public void testc_display_board(byte index, byte enable_disable) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;

        int buf_inc = 0;

        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4;

        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        }
        pid_buf[buf_inc++] = (byte) TESTC_PKT;
        pid_buf[buf_inc++] = enable_disable;

        pkt_length += (short) (buf_inc);
        buf = convertToByteArray((short) (pkt_length));

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB) 
        //  pkt_buf[LEN_LSB)

        pkt_length = (short) (pkt_length + 2);

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        disBrdLinkChkBuf = new byte[buf_inc + 2];
        // StringBuilder sb = new StringBuilder();
        for (i = 0; i < buf_inc + 2; i++) {
            disBrdLinkChkBuf[i] = pid_buf[i];
        }

        setDisBrdLnkChkCame(true);

    }

    public void deletePkt_display_board(byte index, short pkt_no) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;

        int buf_inc = 0;

        pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
        pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;

        buf_inc = 4;

        pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
        if (index == FRONT_DB) {
            pid_buf[buf_inc++] = clsDefines.FD_ADDR;
        } else if (index == SIDE_DB) {
            pid_buf[buf_inc++] = clsDefines.SD_ADDR;
        } else if (index == REAR_DB) {
            pid_buf[buf_inc++] = clsDefines.RD_ADDR;
        } else if (index == INT_DB) {
            pid_buf[buf_inc++] = clsDefines.ID_ADDR;
        } else if (index == SIDE_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
        } else if (index == INT_DB_ART) {
            pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
        }
        pid_buf[buf_inc++] = (byte) DELETE_PKT;
        pid_buf[buf_inc++] = (byte) pkt_no;

        pkt_length += (short) (buf_inc);
        buf = convertToByteArray((short) (pkt_length));

        pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
        pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB) 
        //  pkt_buf[LEN_LSB)

        pkt_length = (short) (pkt_length + 2);

        // Prepare checksum
        pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
        disBrdLinkChkBuf = new byte[buf_inc + 2];
        // StringBuilder sb = new StringBuilder();
        for (i = 0; i < buf_inc + 2; i++) {
            disBrdLinkChkBuf[i] = pid_buf[i];
        }

        setDisBrdLnkChkCame(true);

    }

    public void display_board_intensity(byte index, short pkt_type, byte[] data_buf, byte len) {
        byte[] pid_buf = new byte[200];
        byte[] buf;

        short pkt_length = 0;
        byte i = 0;
        int buf_inc = 0;

        try {
            pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
            pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;
            buf_inc = 4;
            pid_buf[buf_inc++] = clsDefines.VMU_ADDR;

            if (index == FRONT_DB) {
                pid_buf[buf_inc++] = clsDefines.FD_ADDR;
            } else if (index == SIDE_DB) {
                pid_buf[buf_inc++] = clsDefines.SD_ADDR;
            } else if (index == REAR_DB) {
                pid_buf[buf_inc++] = clsDefines.RD_ADDR;
            } else if (index == INT_DB) {
                pid_buf[buf_inc++] = clsDefines.ID_ADDR;
            } else if (index == SIDE_DB_ART) {
                pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
            } else if (index == INT_DB_ART) {
                pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
            }

            pid_buf[buf_inc++] = (byte) pkt_type;
            buf_inc = clsDefines.DATA_BYTE;
            for (i = 0; i < len; i++) {
                pid_buf[buf_inc++] = data_buf[i];
            }
            pkt_length += (short) (buf_inc);

            buf = convertToByteArray((short) (pkt_length));
            pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
            pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];

            //  pkt_buf[LEN_LSB)
            pkt_length = (short) (pkt_length + 2);

            // Prepare checksum
            pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
            disBrdLinkChkBuf = new byte[buf_inc + 2];

            for (i = 0; i < buf_inc + 2; i++) {
                disBrdLinkChkBuf[i] = pid_buf[i];
            }

            setDisBrdLnkChkCame(true);

        } catch (Exception ex) {

        } finally {
            pid_buf = null;
            buf = null;
            data_buf = null;
        }
    }

    public void display_board_set_dtc_value(byte index, short pkt_type, byte[] data_buf, byte len) {
        byte[] pid_buf = new byte[200];
        byte[] buf;
        short pkt_length = 0;
        byte i = 0;
        int buf_inc = 0;
        try {
            pid_buf[buf_inc++] = (byte) clsDefines.HEADER1;
            pid_buf[buf_inc++] = (byte) clsDefines.HEADER2;
            buf_inc = 4;
            pid_buf[buf_inc++] = clsDefines.VMU_ADDR;
            if (index == FRONT_DB) {
                pid_buf[buf_inc++] = clsDefines.FD_ADDR;
            } else if (index == SIDE_DB) {
                pid_buf[buf_inc++] = clsDefines.SD_ADDR;
            } else if (index == REAR_DB) {
                pid_buf[buf_inc++] = clsDefines.RD_ADDR;
            } else if (index == INT_DB) {
                pid_buf[buf_inc++] = clsDefines.ID_ADDR;
            } else if (index == SIDE_DB_ART) {
                pid_buf[buf_inc++] = clsDefines.SD_ART_ADDR;
            } else if (index == INT_DB_ART) {
                pid_buf[buf_inc++] = clsDefines.ID_ART_ADDR;
            }
            pid_buf[buf_inc++] = (byte) pkt_type;
            buf_inc = clsDefines.DATA_BYTE;
            for (i = 0; i < len; i++) {
                pid_buf[buf_inc++] = data_buf[i];
            }
            pkt_length += (short) (buf_inc);
            buf = convertToByteArray((short) (pkt_length));
            pid_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];
            pid_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];            // pkt_buf[LEN_MSB) 
            //  pkt_buf[LEN_LSB)
            pkt_length = (short) (pkt_length + 2);
            // Prepare checksum
            pid_buf = prepare_checksum(pid_buf, (short) pkt_length);
            disBrdLinkChkBuf = new byte[buf_inc + 2];
            // StringBuilder sb = new StringBuilder();
            for (i = 0; i < buf_inc + 2; i++) {
                disBrdLinkChkBuf[i] = pid_buf[i];
            }
            setDisBrdLnkChkCame(true);
        } catch (Exception ex) {

        } finally {
            pid_buf = null;
            buf = null;
            data_buf = null;
        }
    }

    public synchronized static void setDisBrdLnkChkCame(boolean val) {
        lnkChkDisPktCame = val;
    }

    public synchronized static boolean getDisBrdLnkChkCame() {
        return lnkChkDisPktCame;
    }

    public synchronized static void setDisBrdDelPktCame(boolean val) {
        deleteDisPktCame = val;
    }

    public synchronized static boolean getDisBrdDelPktCame() {
        return deleteDisPktCame;
    }

    private static byte[] convertToByteArray(short value) {
        byte[] bytes = new byte[2];
        try {
            ByteBuffer buffer;
            buffer = ByteBuffer.allocate(bytes.length);
            buffer.putShort(value);
            bytes = null;
            return buffer.array();
        } catch (Exception ex) {

        }
        return bytes;
    }

    static boolean found = false;

    private static byte[] prepare_checksum(byte[] str, short length) {
        int index = 2;
        int CRCITTSum = 0xffff;
        int ByteValue = 0;
        byte bitindex = 0;
        byte temp = 0;
        try {
            while (index < (length - 2)) {
                temp = str[index];
                ///*data_packet[index]*/
                ByteValue = temp;
                ByteValue <<= 8;
                bitindex = 0;

                while (bitindex < 8) {
                    if (((((CRCITTSum ^ ByteValue) & 0x8000)) != 0)) {
                        CRCITTSum = (int) (CRCITTSum << 1);
                        CRCITTSum = (int) (CRCITTSum ^ 0x1021);
                    } else {
                        CRCITTSum <<= 1;
                    }
                    ByteValue <<= 1;
                    bitindex += (byte) (1);
                }
                index += (byte) (1);
            }

            str[length - 1] = (byte) (CRCITTSum & 0xff);
            str[length - 2] = (byte) ((CRCITTSum & 0xff00) >> 8);
        } catch (Exception ex) {

        }
        return str;
    }

    private static byte[] convertToByteArrayInt(int value) {

        byte[] bytes = new byte[4];
        ByteBuffer buffer;

        buffer = ByteBuffer.allocate(bytes.length);
        buffer.putInt(value);
        bytes = null;
        return buffer.array();

    }

    public static void init_front_pid_codes() {
        byte i = 0;
        objPids = new pid_codes[MAX_NO_DISPLAYBOARDS];
        objBrdLnkRstTst = new clsBrdLnkRstTst[MAX_NO_DISPLAYBOARDS];
        for (i = 0; i < MAX_NO_DISPLAYBOARDS; i++) {
            objPids[i] = new pid_codes();

            objPids[i].hw_rev_val = "1.0";
            objPids[i].ser_no = " ";
            objPids[i].boot_loader_sw_rev = "1.0";
            objPids[i].app_sw_rev = "";
            objPids[i].font_lib_rev = "1.0";
            objPids[i].cpu_part_no = "STM32F401";
            objPids[i].cpu_qual = "KORHP348";
            objPids[i].cpu_temp_range = "-40°C TO + 105°C";
            objPids[i].compilation_fw_date = " ";
            objPids[i].flash_update_status = "";
            objPids[i].test_date_time = " ";
            if (i == 0) {
                objPids[i].article_no_sign_level = "FD";
            } else if (i == 1 || i == 4) {
                objPids[i].article_no_sign_level = "SD";
            } else if (i == 2) {
                objPids[i].article_no_sign_level = "RD";
            } else if (i == 3 || i == 5) {
                objPids[i].article_no_sign_level = "ID";
            }
            objPids[i].prod_date = " ";
            objPids[i].end_customer = "";
            objPids[i].order_no = "";
            objPids[i].vehicle_type = "";
            objPids[i].bus_builder_no = "";
            objPids[i].language = 0;
            objPids[i].board_temp_sensor = "103AT";
            objPids[i].internal_cpu_temp = " °C";
            objPids[i].min_cpu_temp = "-40°C";
            objPids[i].max_cpu_temp = "105°C";
            objPids[i].min_board_temp = "-25°C";
            objPids[i].max_board_temp = "85°C";
            objPids[i].min_input_volt = "9 V";
            objPids[i].max_input_volt = "39 V";
            objPids[i].operating_hours = 0;
            objPids[i].no_resets = 0;

            objBrdLnkRstTst[i] = new clsBrdLnkRstTst();
            objBrdLnkRstTst[i].linkCkeck = false;
            objBrdLnkRstTst[i].reset = false;
            objBrdLnkRstTst[i].test = false;
            objBrdLnkRstTst[i].test_cont = false;
            objBrdLnkRstTst[i].intensity = false;
            objBrdLnkRstTst[i].dtcCodes = false;
            objBrdLnkRstTst[i].delPkt = false;
            objBrdLnkRstTst[i].setAddressPkt = false;
            objBrdLnkRstTst[i].getAddressPkt = false;
        }

    }
    private void btnFrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrontActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);
        btnFront.setBackground(Color.green);
        btnSide.setBackground(new java.awt.Color(85, 85, 85));
        btnRear.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal.setBackground(new java.awt.Color(85, 85, 85));
        btnSideArt.setBackground(new java.awt.Color(85, 85, 85));
        btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(FRONT_DB, PID_CODEALL_PKT);

        lblPID.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i = 0;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {

                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                btnFront.setBackground(new java.awt.Color(85, 85, 85));

                byte inc = 0;
                byte index = 0;
                try {
                    if (found == true) {

                        lblPID.setText("Front Command Success");

                    } else {

                        lblPID.setText("Front Command Failure");

                    }

                    tabDisplayPid.setValueAt(objPids[index].hw_rev_val, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].ser_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].boot_loader_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].app_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].font_lib_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_part_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_qual, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_temp_range, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].compilation_fw_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].flash_update_status, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].test_date_time, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].article_no_sign_level, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].prod_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].end_customer, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].order_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].vehicle_type, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].bus_builder_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].language, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].board_temp_sensor, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].internal_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].operating_hours, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].no_resets, inc++, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnFrontActionPerformed

    private void btnSideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSideActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnSide.setBackground(Color.green);
        btnFront.setBackground(new java.awt.Color(85, 85, 85));
        btnRear.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal.setBackground(new java.awt.Color(85, 85, 85));
        btnSideArt.setBackground(new java.awt.Color(85, 85, 85));
        btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));

        link_display_board(SIDE_DB, PID_CODEALL_PKT);
        lblPID.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i = 0;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnSide.setBackground(new java.awt.Color(85, 85, 85));

                byte inc = 0;
                byte index = 1;
                try {
                    if (found == true) {

                        lblPID.setText("Side Command Success");

                    } else {

                        lblPID.setText("Side Command Failure");

                    }

                    tabDisplayPid.setValueAt(objPids[index].hw_rev_val, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].ser_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].boot_loader_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].app_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].font_lib_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_part_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_qual, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_temp_range, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].compilation_fw_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].flash_update_status, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].test_date_time, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].article_no_sign_level, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].prod_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].end_customer, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].order_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].vehicle_type, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].bus_builder_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].language, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].board_temp_sensor, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].internal_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].operating_hours, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].no_resets, inc++, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnSideActionPerformed

    private void btnInternalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInternalActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnInternal.setBackground(Color.green);
        btnSide.setBackground(new java.awt.Color(85, 85, 85));
        btnRear.setBackground(new java.awt.Color(85, 85, 85));
        btnFront.setBackground(new java.awt.Color(85, 85, 85));
        btnSideArt.setBackground(new java.awt.Color(85, 85, 85));
        btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(INT_DB, PID_CODEALL_PKT);

        lblPID.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i = 0;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnInternal.setBackground(new java.awt.Color(85, 85, 85));

                byte inc = 0;
                byte index = 3;
                try {
                    if (found == true) {

                        lblPID.setText("  Internal Command Success");

                    } else {

                        lblPID.setText("  Internal Command Failure");

                    }

                    tabDisplayPid.setValueAt(objPids[index].hw_rev_val, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].ser_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].boot_loader_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].app_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].font_lib_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_part_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_qual, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_temp_range, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].compilation_fw_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].flash_update_status, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].test_date_time, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].article_no_sign_level, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].prod_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].end_customer, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].order_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].vehicle_type, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].bus_builder_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].language, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].board_temp_sensor, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].internal_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].operating_hours, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].no_resets, inc++, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnInternalActionPerformed

    private void btnRearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRearActionPerformed
        // TODO add your handling code here:

        btnRear.setBackground(Color.green);
        btnSide.setBackground(new java.awt.Color(85, 85, 85));
        btnFront.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal.setBackground(new java.awt.Color(85, 85, 85));
        btnSideArt.setBackground(new java.awt.Color(85, 85, 85));
        btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));

        link_display_board(REAR_DB, PID_CODEALL_PKT);
        clsDisplayBrdSerialPort.setPidResCame(false);
        lblPID.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i = 0;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnRear.setBackground(new java.awt.Color(85, 85, 85));

                byte inc = 0;
                byte index = 2;
                try {
                    if (found == true) {

                        lblPID.setText("Rear Command Success");

                    } else {

                        lblPID.setText("Rear Command Failure");

                    }

                    tabDisplayPid.setValueAt(objPids[index].hw_rev_val, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].ser_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].boot_loader_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].app_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].font_lib_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_part_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_qual, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_temp_range, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].compilation_fw_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].flash_update_status, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].test_date_time, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].article_no_sign_level, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].prod_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].end_customer, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].order_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].vehicle_type, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].bus_builder_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].language, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].board_temp_sensor, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].internal_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].operating_hours, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].no_resets, inc++, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnRearActionPerformed

    private void btnSideArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSideArtActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);
        btnSideArt.setBackground(Color.green);
        btnSide.setBackground(new java.awt.Color(85, 85, 85));
        btnRear.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal.setBackground(new java.awt.Color(85, 85, 85));
        btnFront.setBackground(new java.awt.Color(85, 85, 85));
        btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(SIDE_DB_ART, PID_CODEALL_PKT);
        lblPID.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i = 0;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution 
                btnSideArt.setBackground(new java.awt.Color(85, 85, 85));

                byte inc = 0;
                byte index = 4;
                try {
                    if (found == true) {

                        lblPID.setText("Articulated Side Command Success");

                    } else {

                        lblPID.setText("Articulated Side Command Failure");

                    }

                    tabDisplayPid.setValueAt(objPids[index].hw_rev_val, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].ser_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].boot_loader_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].app_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].font_lib_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_part_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_qual, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_temp_range, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].compilation_fw_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].flash_update_status, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].test_date_time, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].article_no_sign_level, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].prod_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].end_customer, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].order_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].vehicle_type, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].bus_builder_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].language, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].board_temp_sensor, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].internal_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].operating_hours, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].no_resets, inc++, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnSideArtActionPerformed

    private void btnInternalArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInternalArtActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);
        btnInternalArt.setBackground(Color.green);
        btnSide.setBackground(new java.awt.Color(85, 85, 85));
        btnRear.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal.setBackground(new java.awt.Color(85, 85, 85));
        btnSideArt.setBackground(new java.awt.Color(85, 85, 85));
        btnFront.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(INT_DB_ART, PID_CODEALL_PKT);
        lblPID.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i = 0;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                btnInternalArt.setBackground(new java.awt.Color(85, 85, 85));
                byte inc = 0;
                byte index = 5;
                try {
                    if (found == true) {

                        lblPID.setText("Articulated Internal Command Success");

                    } else {

                        lblPID.setText("Articulated Internal Command Failure");

                    }

                    tabDisplayPid.setValueAt(objPids[index].hw_rev_val, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].ser_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].boot_loader_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].app_sw_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].font_lib_rev, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_part_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_qual, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].cpu_temp_range, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].compilation_fw_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].flash_update_status, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].test_date_time, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].article_no_sign_level, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].prod_date, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].end_customer, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].order_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].vehicle_type, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].bus_builder_no, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].language, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].board_temp_sensor, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].internal_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_cpu_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_board_temp, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].min_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].max_input_volt, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].operating_hours, inc++, 1);
                    tabDisplayPid.setValueAt(objPids[index].no_resets, inc++, 1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnInternalArtActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btnFront1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFront1ActionPerformed
        // TODO add your handling code here:

        btnFront1.setBackground(Color.green);
        btnSide1.setBackground(new java.awt.Color(85, 85, 85));
        btnRear1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal1.setBackground(new java.awt.Color(85, 85, 85));
        btnSide2.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal2.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(FRONT_DB, DTC_CODEALL_PKT);
        clsDisplayBrdSerialPort.setPidResCame(false);
        btnFront1.setBackground(Color.GREEN);
        lblDTC.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                btnFront1.setBackground(new java.awt.Color(85, 85, 85));

                try {
                    if (found == true) {

                        tabDisplayDtc.setValueAt(front_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(front_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(front_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(front_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(front_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(front_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(front_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(front_over_heat_cnt, 2, 2);
                        lblDTC.setText("Front Command Success");

                    } else {
                        tabDisplayDtc.setValueAt(front_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(front_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(front_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(front_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(front_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(front_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(front_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(front_over_heat_cnt, 2, 2);
                        lblDTC.setText("Front Command Failure");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnFront1ActionPerformed

    private void btnSide1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSide1ActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnSide1.setBackground(Color.green);
        btnFront1.setBackground(new java.awt.Color(85, 85, 85));
        btnRear1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal1.setBackground(new java.awt.Color(85, 85, 85));
        btnSide2.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal2.setBackground(new java.awt.Color(85, 85, 85));

        link_display_board(SIDE_DB, DTC_CODEALL_PKT);
        lblDTC.setText("Please wait Processing....");

        btnSide1.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnSide1.setBackground(new java.awt.Color(85, 85, 85));

                try {
                    if (found == true) {
                        tabDisplayDtc.setValueAt(side_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(side_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(side_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(side_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(side_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(side_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(side_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(side_over_heat_cnt, 2, 2);
                        lblDTC.setText("Side Command Success");

                    } else {
                        tabDisplayDtc.setValueAt(side_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(side_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(side_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(side_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(side_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(side_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(side_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(side_over_heat_cnt, 2, 2);
                        lblDTC.setText("Side Command Failure");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sw1.execute();

    }//GEN-LAST:event_btnSide1ActionPerformed

    private void btnInternal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInternal1ActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnInternal1.setBackground(Color.green);
        btnSide1.setBackground(new java.awt.Color(85, 85, 85));
        btnRear1.setBackground(new java.awt.Color(85, 85, 85));
        btnFront1.setBackground(new java.awt.Color(85, 85, 85));
        btnSide2.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal2.setBackground(new java.awt.Color(85, 85, 85));

        link_display_board(INT_DB, DTC_CODEALL_PKT);

        lblDTC.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnInternal1.setBackground(new java.awt.Color(85, 85, 85));

                try {
                    if (found == true) {
                        tabDisplayDtc.setValueAt(int_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(int_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(int_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(int_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(int_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(int_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(int_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(int_over_heat_cnt, 2, 2);
                        lblDTC.setText("Internal Command Success");

                    } else {
                        tabDisplayDtc.setValueAt(int_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(int_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(int_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(int_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(int_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(int_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(int_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(int_over_heat_cnt, 2, 2);
                        lblDTC.setText("Internal Command Failure");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

   
        sw1.execute();
    }//GEN-LAST:event_btnInternal1ActionPerformed

    private void btnRear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRear1ActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnRear1.setBackground(Color.green);
        btnSide1.setBackground(new java.awt.Color(85, 85, 85));
        btnFront1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal1.setBackground(new java.awt.Color(85, 85, 85));
        btnSide2.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal2.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(REAR_DB, DTC_CODEALL_PKT);
        btnRear1.setBackground(Color.GREEN);
        lblDTC.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnRear1.setBackground(new java.awt.Color(85, 85, 85));

                try {
                    if (found == true) {
                        tabDisplayDtc.setValueAt(rear_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(rear_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(rear_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(rear_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(rear_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(rear_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(rear_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(rear_over_heat_cnt, 2, 2);
                        lblDTC.setText("Rear Command Success");

                    } else {
                        tabDisplayDtc.setValueAt(rear_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(rear_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(rear_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(rear_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(rear_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(rear_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(rear_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(rear_over_heat_cnt, 2, 2);
                        lblDTC.setText("Rear Command Failure");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnRear1ActionPerformed

    private void btnSide2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSide2ActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnSide2.setBackground(Color.green);
        btnSide1.setBackground(new java.awt.Color(85, 85, 85));
        btnRear1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal1.setBackground(new java.awt.Color(85, 85, 85));
        btnFront1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal2.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(SIDE_DB_ART, DTC_CODEALL_PKT);

        lblDTC.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnSide2.setBackground(new java.awt.Color(85, 85, 85));

                try {
                    if (found == true) {
                        tabDisplayDtc.setValueAt(sideart_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(sideart_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(sideart_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(sideart_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(sideart_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(sideart_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(sideart_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(sideart_over_heat_cnt, 2, 2);
                        lblDTC.setText("Side Art Command Success");

                    } else {
                        tabDisplayDtc.setValueAt(sideart_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(sideart_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(sideart_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(sideart_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(sideart_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(sideart_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(sideart_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(sideart_over_heat_cnt, 2, 2);
                        lblDTC.setText("Side Art Command Failure");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnSide2ActionPerformed

    private void btnInternal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInternal2ActionPerformed
        // TODO add your handling code here:
        clsDisplayBrdSerialPort.setPidResCame(false);

        btnInternal2.setBackground(Color.green);
        btnSide1.setBackground(new java.awt.Color(85, 85, 85));
        btnRear1.setBackground(new java.awt.Color(85, 85, 85));
        btnInternal1.setBackground(new java.awt.Color(85, 85, 85));
        btnSide2.setBackground(new java.awt.Color(85, 85, 85));
        btnFront1.setBackground(new java.awt.Color(85, 85, 85));
        link_display_board(INT_DB_ART, DTC_CODEALL_PKT);
        btnInternal2.setBackground(Color.GREEN);
        lblDTC.setText("Please wait Processing....");

        SwingWorker sw1 = new SwingWorker() {
            boolean found = false;

            @Override
            protected String doInBackground() throws Exception {
                int i;

                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    if (clsDisplayBrdSerialPort.getPidResCame() == true) {
                        found = true;
                        return "success";
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution

                btnInternal2.setBackground(new java.awt.Color(85, 85, 85));
                try {
                    if (found == true) {
                        tabDisplayDtc.setValueAt(intart_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(intart_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(intart_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(intart_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(intart_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(intart_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(intart_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(intart_over_heat_cnt, 2, 2);
                        lblDTC.setText("Internal Art Command Success");

                    } else {
                        tabDisplayDtc.setValueAt(intart_high_volt, 0, 1);
                        tabDisplayDtc.setValueAt(intart_low_volt, 1, 1);
                        tabDisplayDtc.setValueAt(intart_over_heat, 2, 1);
                        tabDisplayDtc.setValueAt(intart_cur_temp, 3, 1);
                        tabDisplayDtc.setValueAt(intart_cur_volt, 4, 1);

                        tabDisplayDtc.setValueAt(intart_high_volt_cnt, 0, 2);
                        tabDisplayDtc.setValueAt(intart_low_volt_cnt, 1, 2);
                        tabDisplayDtc.setValueAt(intart_over_heat_cnt, 2, 2);
                        lblDTC.setText("Internal Art Command Failure");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnInternal2ActionPerformed

    private void btnFrontLinkfrontMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFrontLinkfrontMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFrontLinkfrontMouseClicked

    private void btnFrontLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrontLinkActionPerformed
        // TODO add your handling code here:
        //   btnFrontLink.setBackground(Color.green);

        if (!getFrontEnable()) {
            lblTest.setText("Front Board Is Disabled");

            lblFD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fd_no_status.png")));

            return;
        }
        if (getDisBrdLnkChkCame()) {
            lblTest.setText("Please wait 5 sec Background processing....");
            setDisBrdLnkChkCame(false);
            return;
        }
        byte[] data_buf = new byte[10];
        byte[] buf;
        short pkt_no = 0;
        pbar.setVisible(true);
        pbar.setValue(0);

        if (this.radLink.isSelected()) {
            selected_button = BTN_LINK;
            objBrdLnkRstTst[FRONT_DB].linkCkeck = false;
            link_display_board(FRONT_DB, LINK_CHECK_PKT);
        } else if (this.radReset.isSelected()) {
            selected_button = BTN_RESET;
            objBrdLnkRstTst[FRONT_DB].reset = false;
            link_display_board(FRONT_DB, SOFTREST_PKT);
        } else if (this.radTest.isSelected()) {
            selected_button = BTN_TEST;
            objBrdLnkRstTst[FRONT_DB].test = false;
            link_display_board(FRONT_DB, TEST_PKT);
        } else if (this.radTestCont.isSelected()) {
            selected_button = BTN_TESTCONT;
            objBrdLnkRstTst[FRONT_DB].test_cont = false;
            if (radTestCEnable.isSelected()) {
                testc_display_board(FRONT_DB, TESTC_ENABLE);
            } else {
                testc_display_board(FRONT_DB, TESTC_DISABLE);
            }
        } else if (this.radDtc.isSelected()) {
            selected_button = BTN_DTC;
            objBrdLnkRstTst[FRONT_DB].dtcCodes = false;
            data_buf = new byte[4];
            if (this.radDtcHigh.isSelected()) {

                buf = convertToByteArray((short) DTC_HIGH_VOLTAGE);
                data_buf[0] = buf[0];//byte) (DTC_HIGH_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[01];//(byte) (DTC_HIGH_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(FRONT_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcLow.isSelected()) {
                buf = convertToByteArray((short) DTC_LOW_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_LOW_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_LOW_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(FRONT_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcOverheat.isSelected()) {
                buf = convertToByteArray((short) DTC_OVER_HEAT_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(FRONT_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radOperatingHours.isSelected()) {
                buf = convertToByteArray((short) PID_OPERATING_HRS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArrayInt(Integer.parseInt(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];
                data_buf[5] = buf[2];
                data_buf[6] = buf[3];

                display_board_intensity(FRONT_DB, PID_CODE_PKT, data_buf, (byte) 7);
            } else if (this.radNoResets.isSelected()) {
                buf = convertToByteArray((short) PID_NO_RESETS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArray((short) Short.parseShort(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];

                display_board_intensity(FRONT_DB, PID_CODE_PKT, data_buf, (byte) 5);
            }

            data_buf = null;
        } else if (this.radIntensity.isSelected()) {
            selected_button = BTN_INTENSITY;
            data_buf = new byte[4];
            objBrdLnkRstTst[FRONT_DB].intensity = false;
            data_buf[0] = 1; //WRITE_MODE
            if (this.radAutoInt.isSelected()) {
                data_buf[1] = 1;
            } else {
                data_buf[1] = 0;
            }
            switch (cmbIntVal.getSelectedIndex()) {
                case 0:
                    data_buf[2] = (byte) 10;
                    break;
                case 1:
                    data_buf[2] = (byte) 9;
                    break;
                case 2:
                    data_buf[2] = (byte) 8;
                    break;
                case 3:
                    data_buf[2] = (byte) 7;
                    break;
                case 4:
                    data_buf[2] = (byte) 6;
                    break;
                case 5:
                    data_buf[2] = (byte) 5;
                    break;
                case 6:
                    data_buf[2] = (byte) 4;
                    break;
                case 7:
                    data_buf[2] = (byte) 3;
                    break;
                case 8:
                    data_buf[2] = (byte) 2;
                    break;
                case 9:
                    data_buf[2] = (byte) 1;
                    break;
                case 10:
                    data_buf[2] = (byte) 0;
                    break;
            }

            data_buf[3] = 1;
            display_board_intensity(FRONT_DB, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            data_buf = null;
        } else if (this.radDelStoredMsg.isSelected()) {
            selected_button = BTN_DELMSG;
            lblTest.setText("");
            objBrdLnkRstTst[FRONT_DB].delPkt = false;
            if (this.radDelSingle.isSelected()) {
                pkt_no = ((short) Short.parseShort(this.spinDelValue.getValue().toString()));
            } else {
                pkt_no = 254;
            }
            deletePkt_display_board(FRONT_DB, pkt_no);
        } else if (this.radBrdAddr.isSelected()) {

            lblTest.setText("");
            if (this.radBrdAddrGet.isSelected()) {
                selected_button = BTN_BRDADDR_GET;

                objBrdLnkRstTst[FRONT_DB].getAddressPkt = false;
                display_board_intensity(FRONT_DB, DISBRD_GET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 0);
            } else {
                selected_button = BTN_BRDADDR_SET;

                objBrdLnkRstTst[FRONT_DB].setAddressPkt = false;
                data_buf[0] = clsDefines.FRONT_BRD;
                data_buf[1] = Byte.parseByte(spinBrdAddrVal.getValue().toString());
                try {
                    data_buf[2] = Byte.parseByte(txtBrdWidth.getText().trim());
                } catch (Exception ex) {
                    data_buf[2] = clsDefines.FD_BRD_WIDTH;
                }

                objBrdLnkRstTst[FRONT_DB].setAddressPkt = false;
                display_board_intensity(FRONT_DB, DISBRD_SET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 3);
            }
        }

        lblTest.setText("");
        btnFrontLink.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i = 0;
                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    switch (selected_button) {
                        case BTN_LINK: {
                            if (objBrdLnkRstTst[FRONT_DB].linkCkeck == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_RESET: {
                            if (objBrdLnkRstTst[FRONT_DB].reset == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TEST: {
                            if (objBrdLnkRstTst[FRONT_DB].test == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TESTCONT: {
                            if (objBrdLnkRstTst[FRONT_DB].test_cont == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DTC: {
                            if (objBrdLnkRstTst[FRONT_DB].dtcCodes == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DELMSG: {
                            if (objBrdLnkRstTst[FRONT_DB].delPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_INTENSITY: {
                            if (objBrdLnkRstTst[FRONT_DB].intensity == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_GET: {
                            if (objBrdLnkRstTst[FRONT_DB].getAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_SET: {
                            if (objBrdLnkRstTst[FRONT_DB].setAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                    }

                    pbar.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setDisBrdLnkChkCame(false);
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                pbar.setVisible(false);
                btnFrontLink.setBackground(new java.awt.Color(0, 0, 102));

                try {
                    if (selected_button == BTN_BRDADDR_GET) {
                        if (found == true) {
                            // lblText.setText("  Get Address Command Success");
                            objBrdLnkRstTst[0].getAddressPkt = true;
                            lblTest.setText(clsSharedVariables.getDisBrdSetAddrData() + " Command Success");
                        } else {
                            lblTest.setText("Get Address Command Failure");

                            lblTest.setText("");
                        }
                    } else {
                        if (found == true) {
                            lblTest.setText("Command Success");

                        } else {
                            lblTest.setText("Command Failure");

                            lblFD.setIcon(fd_fail_icon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnFrontLinkActionPerformed

    private void btnSideLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSideLinkActionPerformed

        if (!getSideEnable()) {
            lblTest.setText("Side Board Is Disabled");

            lblSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sd_no_status.png")));

            return;
        }
        if (getDisBrdLnkChkCame() && pbar.isVisible()) {
            lblTest.setText("Please wait 5 sec Background processing....");
            setDisBrdLnkChkCame(false);
            return;
        }
        byte[] data_buf = new byte[10];
        byte[] buf;
        short pkt_no = 0;
        pbar.setVisible(true);
        pbar.setValue(0);

        if (this.radLink.isSelected()) {
            selected_button = BTN_LINK;
            objBrdLnkRstTst[SIDE_DB].linkCkeck = false;
            link_display_board(SIDE_DB, LINK_CHECK_PKT);
        } else if (this.radReset.isSelected()) {
            selected_button = BTN_RESET;
            objBrdLnkRstTst[SIDE_DB].reset = false;
            link_display_board(SIDE_DB, SOFTREST_PKT);
        } else if (this.radTest.isSelected()) {
            selected_button = BTN_TEST;
            objBrdLnkRstTst[SIDE_DB].test = false;
            link_display_board(SIDE_DB, TEST_PKT);
        } else if (this.radTestCont.isSelected()) {
            selected_button = BTN_TESTCONT;
            objBrdLnkRstTst[SIDE_DB].test_cont = false;
            if (radTestCEnable.isSelected()) {
                testc_display_board(SIDE_DB, TESTC_ENABLE);
            } else {
                testc_display_board(SIDE_DB, TESTC_DISABLE);
            }
        } else if (this.radDtc.isSelected()) {
            selected_button = BTN_DTC;
            objBrdLnkRstTst[SIDE_DB].dtcCodes = false;
            data_buf = new byte[4];
            if (this.radDtcHigh.isSelected()) {

                buf = convertToByteArray((short) DTC_HIGH_VOLTAGE);
                data_buf[0] = buf[0];//byte) (DTC_HIGH_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[01];//(byte) (DTC_HIGH_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(SIDE_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcLow.isSelected()) {
                buf = convertToByteArray((short) DTC_LOW_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_LOW_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_LOW_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(SIDE_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcOverheat.isSelected()) {
                buf = convertToByteArray((short) DTC_OVER_HEAT_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(SIDE_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radOperatingHours.isSelected()) {
                buf = convertToByteArray((short) PID_OPERATING_HRS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArrayInt(Integer.parseInt(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];
                data_buf[5] = buf[2];
                data_buf[6] = buf[3];

                display_board_intensity(SIDE_DB, PID_CODE_PKT, data_buf, (byte) 7);
            } else if (this.radNoResets.isSelected()) {
                buf = convertToByteArray((short) PID_NO_RESETS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArray((short) Short.parseShort(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];

                display_board_intensity(SIDE_DB, PID_CODE_PKT, data_buf, (byte) 5);
            }

            data_buf = null;
        } else if (this.radIntensity.isSelected()) {
            selected_button = BTN_INTENSITY;
            data_buf = new byte[4];
            objBrdLnkRstTst[SIDE_DB].intensity = false;
            data_buf[0] = 1; //WRITE_MODE
            if (this.radAutoInt.isSelected()) {
                data_buf[1] = 1;
            } else {
                data_buf[1] = 0;
            }
            switch (cmbIntVal.getSelectedIndex()) {
                case 0:
                    data_buf[2] = (byte) 10;
                    break;
                case 1:
                    data_buf[2] = (byte) 9;
                    break;
                case 2:
                    data_buf[2] = (byte) 8;
                    break;
                case 3:
                    data_buf[2] = (byte) 7;
                    break;
                case 4:
                    data_buf[2] = (byte) 6;
                    break;
                case 5:
                    data_buf[2] = (byte) 5;
                    break;
                case 6:
                    data_buf[2] = (byte) 4;
                    break;
                case 7:
                    data_buf[2] = (byte) 3;
                    break;
                case 8:
                    data_buf[2] = (byte) 2;
                    break;
                case 9:
                    data_buf[2] = (byte) 1;
                    break;
                case 10:
                    data_buf[2] = (byte) 0;
                    break;
            }

            data_buf[3] = 1;
            display_board_intensity(SIDE_DB, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            data_buf = null;
        } else if (this.radDelStoredMsg.isSelected()) {
            selected_button = BTN_DELMSG;
            lblTest.setText("");
            objBrdLnkRstTst[SIDE_DB].delPkt = false;
            if (this.radDelSingle.isSelected()) {
                pkt_no = ((short) Short.parseShort(this.spinDelValue.getValue().toString()));
            } else {
                pkt_no = 254;
            }
            deletePkt_display_board(SIDE_DB, pkt_no);
        } else if (this.radBrdAddr.isSelected()) {

            lblTest.setText("");
            if (this.radBrdAddrGet.isSelected()) {
                selected_button = BTN_BRDADDR_GET;

                objBrdLnkRstTst[SIDE_DB].getAddressPkt = false;
                display_board_intensity(SIDE_DB, DISBRD_GET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 0);
            } else {
                selected_button = BTN_BRDADDR_SET;

                data_buf[0] = clsDefines.SIDE_BRD;
                data_buf[1] = Byte.parseByte(spinBrdAddrVal.getValue().toString());
                try {
                    data_buf[2] = Byte.parseByte(txtBrdWidth.getText().trim());
                } catch (Exception ex) {
                    data_buf[2] = clsDefines.SD_BRD_WIDTH;
                }

                objBrdLnkRstTst[SIDE_DB].setAddressPkt = false;
                display_board_intensity(SIDE_DB, DISBRD_SET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 3);
            }
        }

        lblTest.setText("");
        btnSideLink.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i = 0;
                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    switch (selected_button) {
                        case BTN_LINK: {
                            if (objBrdLnkRstTst[SIDE_DB].linkCkeck == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_RESET: {
                            if (objBrdLnkRstTst[SIDE_DB].reset == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TEST: {
                            if (objBrdLnkRstTst[SIDE_DB].test == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TESTCONT: {
                            if (objBrdLnkRstTst[SIDE_DB].test_cont == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DTC: {
                            if (objBrdLnkRstTst[SIDE_DB].dtcCodes == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DELMSG: {
                            if (objBrdLnkRstTst[SIDE_DB].delPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_INTENSITY: {
                            if (objBrdLnkRstTst[SIDE_DB].intensity == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_GET: {
                            if (objBrdLnkRstTst[SIDE_DB].getAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_SET: {
                            if (objBrdLnkRstTst[SIDE_DB].setAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                    }
                    pbar.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setDisBrdLnkChkCame(false);
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                pbar.setVisible(false);
                btnSideLink.setBackground(new java.awt.Color(0, 0, 102));
                try {
                    if (selected_button == BTN_BRDADDR_GET) {
                        if (found == true) {
                            // lblText.setText("  Get Address Command Success");
                            objBrdLnkRstTst[0].getAddressPkt = true;
                            lblTest.setText(clsSharedVariables.getDisBrdSetAddrData() + " Command Success");
                        } else {
                            lblTest.setText("Get Address Command Failure");

                            lblTest.setText("");
                        }
                    } else {
                        if (found == true) {
                            lblTest.setText("Command Success");

                        } else {
                            lblTest.setText("Command Failure");

                            lblSD.setIcon(sd_fail_icon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnSideLinkActionPerformed

    private void btnRearLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRearLinkActionPerformed
        // TODO add your handling code here:
        if (!getRearEnable()) {
            lblTest.setText("Rear Board Is Disabled");

            lblRD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rd_no_status.png")));

            return;
        }
        if (getDisBrdLnkChkCame() && pbar.isVisible()) {
            lblTest.setText("Please wait 5 sec Background processing....");
            setDisBrdLnkChkCame(false);
            return;
        }
        byte[] data_buf = new byte[10];
        byte[] buf;
        short pkt_no = 0;
        pbar.setVisible(true);
        pbar.setValue(0);

        if (this.radLink.isSelected()) {
            selected_button = BTN_LINK;
            objBrdLnkRstTst[REAR_DB].linkCkeck = false;
            link_display_board(REAR_DB, LINK_CHECK_PKT);
        } else if (this.radReset.isSelected()) {
            selected_button = BTN_RESET;
            objBrdLnkRstTst[REAR_DB].reset = false;
            link_display_board(REAR_DB, SOFTREST_PKT);
        } else if (this.radTest.isSelected()) {
            selected_button = BTN_TEST;
            objBrdLnkRstTst[REAR_DB].test = false;
            link_display_board(REAR_DB, TEST_PKT);
        } else if (this.radTestCont.isSelected()) {
            selected_button = BTN_TESTCONT;
            objBrdLnkRstTst[REAR_DB].test_cont = false;
            if (radTestCEnable.isSelected()) {
                testc_display_board(REAR_DB, TESTC_ENABLE);
            } else {
                testc_display_board(REAR_DB, TESTC_DISABLE);
            }
        } else if (this.radDtc.isSelected()) {
            selected_button = BTN_DTC;
            objBrdLnkRstTst[REAR_DB].dtcCodes = false;
            data_buf = new byte[4];
            if (this.radDtcHigh.isSelected()) {

                buf = convertToByteArray((short) DTC_HIGH_VOLTAGE);
                data_buf[0] = buf[0];//byte) (DTC_HIGH_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[01];//(byte) (DTC_HIGH_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(REAR_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcLow.isSelected()) {
                buf = convertToByteArray((short) DTC_LOW_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_LOW_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_LOW_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(REAR_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcOverheat.isSelected()) {
                buf = convertToByteArray((short) DTC_OVER_HEAT_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(REAR_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radOperatingHours.isSelected()) {
                buf = convertToByteArray((short) PID_OPERATING_HRS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArrayInt(Integer.parseInt(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];
                data_buf[5] = buf[2];
                data_buf[6] = buf[3];

                display_board_intensity(REAR_DB, PID_CODE_PKT, data_buf, (byte) 7);
            } else if (this.radNoResets.isSelected()) {
                buf = convertToByteArray((short) PID_NO_RESETS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArray((short) Short.parseShort(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];

                display_board_intensity(REAR_DB, PID_CODE_PKT, data_buf, (byte) 5);
            }

            data_buf = null;
        } else if (this.radIntensity.isSelected()) {
            selected_button = BTN_INTENSITY;
            data_buf = new byte[4];
            objBrdLnkRstTst[REAR_DB].intensity = false;
            data_buf[0] = 1; //WRITE_MODE
            if (this.radAutoInt.isSelected()) {
                data_buf[1] = 1;
            } else {
                data_buf[1] = 0;
            }
            switch (cmbIntVal.getSelectedIndex()) {
                case 0:
                    data_buf[2] = (byte) 10;
                    break;
                case 1:
                    data_buf[2] = (byte) 9;
                    break;
                case 2:
                    data_buf[2] = (byte) 8;
                    break;
                case 3:
                    data_buf[2] = (byte) 7;
                    break;
                case 4:
                    data_buf[2] = (byte) 6;
                    break;
                case 5:
                    data_buf[2] = (byte) 5;
                    break;
                case 6:
                    data_buf[2] = (byte) 4;
                    break;
                case 7:
                    data_buf[2] = (byte) 3;
                    break;
                case 8:
                    data_buf[2] = (byte) 2;
                    break;
                case 9:
                    data_buf[2] = (byte) 1;
                    break;
                case 10:
                    data_buf[2] = (byte) 0;
                    break;
            }

            data_buf[3] = 1;
            display_board_intensity(REAR_DB, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            data_buf = null;
        } else if (this.radDelStoredMsg.isSelected()) {
            selected_button = BTN_DELMSG;
            lblTest.setText("");
            objBrdLnkRstTst[REAR_DB].delPkt = false;
            if (this.radDelSingle.isSelected()) {
                pkt_no = ((short) Short.parseShort(this.spinDelValue.getValue().toString()));
            } else {
                pkt_no = 254;
            }
            deletePkt_display_board(REAR_DB, pkt_no);
        } else if (this.radBrdAddr.isSelected()) {
            lblTest.setText("");
            if (this.radBrdAddrGet.isSelected()) {
                objBrdLnkRstTst[REAR_DB].setAddressPkt = false;
                objBrdLnkRstTst[REAR_DB].getAddressPkt = false;
                display_board_intensity(REAR_DB, DISBRD_GET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 0);
            } else {
                objBrdLnkRstTst[REAR_DB].getAddressPkt = false;
                objBrdLnkRstTst[REAR_DB].setAddressPkt = false;
                data_buf[0] = clsDefines.REAR_BRD;
                data_buf[1] = Byte.parseByte(spinBrdAddrVal.getValue().toString());
                try {
                    data_buf[2] = Byte.parseByte(txtBrdWidth.getText().trim());
                } catch (Exception ex) {
                    data_buf[2] = clsDefines.RD_BRD_WIDTH;
                }

                objBrdLnkRstTst[REAR_DB].setAddressPkt = false;
                display_board_intensity(REAR_DB, DISBRD_SET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 3);
            }
        }

        lblTest.setText("");
        btnRearLink.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i = 0;
                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    switch (selected_button) {
                        case BTN_LINK: {
                            if (objBrdLnkRstTst[REAR_DB].linkCkeck == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_RESET: {
                            if (objBrdLnkRstTst[REAR_DB].reset == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TEST: {
                            if (objBrdLnkRstTst[REAR_DB].test == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TESTCONT: {
                            if (objBrdLnkRstTst[REAR_DB].test_cont == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DTC: {
                            if (objBrdLnkRstTst[REAR_DB].dtcCodes == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DELMSG: {
                            if (objBrdLnkRstTst[REAR_DB].delPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_INTENSITY: {
                            if (objBrdLnkRstTst[REAR_DB].intensity == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_GET: {
                            if (objBrdLnkRstTst[REAR_DB].getAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_SET: {
                            if (objBrdLnkRstTst[REAR_DB].setAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                    }
                    pbar.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setDisBrdLnkChkCame(false);
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                pbar.setVisible(false);
                btnRearLink.setBackground(new java.awt.Color(0, 0, 102));
                try {
                    if (selected_button == BTN_BRDADDR_GET) {
                        if (found == true) {
                            // lblText.setText("  Get Address Command Success");
                            objBrdLnkRstTst[0].getAddressPkt = true;
                            lblTest.setText(clsSharedVariables.getDisBrdSetAddrData() + " Command Success");
                        } else {
                            lblTest.setText("Get Address Command Failure");

                            lblTest.setText("");
                        }
                    } else {
                        if (found == true) {
                            lblTest.setText("Command Success");

                        } else {
                            lblTest.setText("Command Failure");

                            lblRD.setIcon(rd_fail_icon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnRearLinkActionPerformed

    private void btnInternalLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInternalLinkActionPerformed
        // TODO add your handling code here:
        if (!getIntEnable()) {
            lblTest.setText("Internal Board Is Disabled");

            lblID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/id_no_status.png")));

            return;
        }
        if (getDisBrdLnkChkCame() && pbar.isVisible()) {
            lblTest.setText("Please wait 5 sec Background processing....");
            setDisBrdLnkChkCame(false);
            return;
        }
        byte[] data_buf = new byte[10];
        byte[] buf;
        short pkt_no = 0;
        pbar.setVisible(true);
        pbar.setValue(0);

        if (this.radLink.isSelected()) {
            selected_button = BTN_LINK;
            objBrdLnkRstTst[INT_DB].linkCkeck = false;
            link_display_board(INT_DB, LINK_CHECK_PKT);
        } else if (this.radReset.isSelected()) {
            selected_button = BTN_RESET;
            objBrdLnkRstTst[INT_DB].reset = false;
            link_display_board(INT_DB, SOFTREST_PKT);
        } else if (this.radTest.isSelected()) {
            selected_button = BTN_TEST;
            objBrdLnkRstTst[INT_DB].test = false;
            link_display_board(INT_DB, TEST_PKT);
        } else if (this.radTestCont.isSelected()) {
            selected_button = BTN_TESTCONT;
            objBrdLnkRstTst[INT_DB].test_cont = false;
            if (radTestCEnable.isSelected()) {
                testc_display_board(INT_DB, TESTC_ENABLE);
            } else {
                testc_display_board(INT_DB, TESTC_DISABLE);
            }
        } else if (this.radDtc.isSelected()) {
            selected_button = BTN_DTC;
            objBrdLnkRstTst[INT_DB].dtcCodes = false;
            data_buf = new byte[4];
            if (this.radDtcHigh.isSelected()) {

                buf = convertToByteArray((short) DTC_HIGH_VOLTAGE);
                data_buf[0] = buf[0];//byte) (DTC_HIGH_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[01];//(byte) (DTC_HIGH_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(INT_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcLow.isSelected()) {
                buf = convertToByteArray((short) DTC_LOW_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_LOW_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_LOW_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(INT_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcOverheat.isSelected()) {
                buf = convertToByteArray((short) DTC_OVER_HEAT_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(INT_DB, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radOperatingHours.isSelected()) {
                buf = convertToByteArray((short) PID_OPERATING_HRS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArrayInt(Integer.parseInt(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];
                data_buf[5] = buf[2];
                data_buf[6] = buf[3];

                display_board_intensity(INT_DB, PID_CODE_PKT, data_buf, (byte) 7);
            } else if (this.radNoResets.isSelected()) {
                buf = convertToByteArray((short) PID_NO_RESETS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArray((short) Short.parseShort(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];

                display_board_intensity(INT_DB, PID_CODE_PKT, data_buf, (byte) 5);
            }

            data_buf = null;
        } else if (this.radIntensity.isSelected()) {
            selected_button = BTN_INTENSITY;
            data_buf = new byte[4];
            objBrdLnkRstTst[INT_DB].intensity = false;
            data_buf[0] = 1; //WRITE_MODE
            if (this.radAutoInt.isSelected()) {
                data_buf[1] = 1;
            } else {
                data_buf[1] = 0;
            }
            switch (cmbIntVal.getSelectedIndex()) {
                case 0:
                    data_buf[2] = (byte) 10;
                    break;
                case 1:
                    data_buf[2] = (byte) 9;
                    break;
                case 2:
                    data_buf[2] = (byte) 8;
                    break;
                case 3:
                    data_buf[2] = (byte) 7;
                    break;
                case 4:
                    data_buf[2] = (byte) 6;
                    break;
                case 5:
                    data_buf[2] = (byte) 5;
                    break;
                case 6:
                    data_buf[2] = (byte) 4;
                    break;
                case 7:
                    data_buf[2] = (byte) 3;
                    break;
                case 8:
                    data_buf[2] = (byte) 2;
                    break;
                case 9:
                    data_buf[2] = (byte) 1;
                    break;
                case 10:
                    data_buf[2] = (byte) 0;
                    break;
            }

            if (chkIntTimeEnabled.isSelected()) {
                data_buf[3] = 32;
                clsSharedVariables.int_disbrd_time_enabled = TIME_ENABLED;
            } else {
                data_buf[3] = 0;
                clsSharedVariables.int_disbrd_time_enabled = TIME_DISABLED;
            }
            clsReadFiles objReadFiles = new clsReadFiles();
            objReadFiles.write_cfg_data_file();

            display_board_intensity(INT_DB, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            display_board_intensity(INT_DB, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            data_buf = null;
        } else if (this.radDelStoredMsg.isSelected()) {
            selected_button = BTN_DELMSG;
            lblTest.setText("");
            objBrdLnkRstTst[INT_DB].delPkt = false;
            if (this.radDelSingle.isSelected()) {
                pkt_no = ((short) Short.parseShort(this.spinDelValue.getValue().toString()));
            } else {
                pkt_no = 255;
            }
            deletePkt_display_board(INT_DB, pkt_no);
        } else if (this.radBrdAddr.isSelected()) {

            lblTest.setText("");
            if (this.radBrdAddrGet.isSelected()) {
                selected_button = BTN_BRDADDR_GET;
                objBrdLnkRstTst[INT_DB].setAddressPkt = false;
                objBrdLnkRstTst[INT_DB].getAddressPkt = false;
                display_board_intensity(INT_DB, DISBRD_GET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 0);
            } else {
                selected_button = BTN_BRDADDR_SET;
                objBrdLnkRstTst[INT_DB].getAddressPkt = false;
                objBrdLnkRstTst[INT_DB].setAddressPkt = false;
                data_buf[0] = clsDefines.INT_BRD;
                data_buf[1] = Byte.parseByte(spinBrdAddrVal.getValue().toString());
                try {
                    data_buf[2] = Byte.parseByte(txtBrdWidth.getText().trim());
                } catch (Exception ex) {
                    data_buf[2] = clsDefines.ID_BRD_WIDTH;
                }

                objBrdLnkRstTst[INT_DB].setAddressPkt = false;
                display_board_intensity(INT_DB, DISBRD_SET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 3);
            }
        }

        lblTest.setText("");
        btnInternalLink.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i = 0;
                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    switch (selected_button) {
                        case BTN_LINK: {
                            if (objBrdLnkRstTst[INT_DB].linkCkeck == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_RESET: {
                            if (objBrdLnkRstTst[INT_DB].reset == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TEST: {
                            if (objBrdLnkRstTst[INT_DB].test == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TESTCONT: {
                            if (objBrdLnkRstTst[INT_DB].test_cont == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DTC: {
                            if (objBrdLnkRstTst[INT_DB].dtcCodes == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DELMSG: {
                            if (objBrdLnkRstTst[INT_DB].delPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_INTENSITY: {
                            if (objBrdLnkRstTst[INT_DB].intensity == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_GET: {
                            if (objBrdLnkRstTst[INT_DB].getAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_SET: {
                            if (objBrdLnkRstTst[INT_DB].setAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                    }
                    pbar.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setDisBrdLnkChkCame(false);
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                pbar.setVisible(false);
                btnInternalLink.setBackground(new java.awt.Color(0, 0, 102));
                try {
                    if (selected_button == BTN_BRDADDR_GET) {
                        if (found == true) {
                            // lblText.setText("  Get Address Command Success");
                            objBrdLnkRstTst[0].getAddressPkt = true;
                            lblTest.setText(clsSharedVariables.getDisBrdSetAddrData() + " Command Success");
                        } else {
                            lblTest.setText("Get Address Command Failure");

                            lblTest.setText("");
                        }
                    } else if (selected_button == BTN_DELMSG) {
                        deletePkt_display_board(INT_DB, (byte) 254);
                    } else {
                        if (found == true) {
                            lblTest.setText("Command Success");

                        } else {
                            lblTest.setText("Command Failure");

                            lblID.setIcon(id_fail_icon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnInternalLinkActionPerformed

    private void btnSideLink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSideLink1ActionPerformed
        // TODO add your handling code here:
        if (!getArtSideEnable()) {
            lblTest.setText("ArtSide Board Is Disabled");

            lblSDArt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sd_no_status.png")));

            return;
        }
        if (getDisBrdLnkChkCame() && pbar.isVisible()) {
            lblTest.setText("Please wait 5 sec Background processing....");
            setDisBrdLnkChkCame(false);
            return;
        }
        byte[] data_buf = new byte[10];
        byte[] buf;
        short pkt_no = 0;
        pbar.setVisible(true);
        pbar.setValue(0);

        if (this.radLink.isSelected()) {
            selected_button = BTN_LINK;
            objBrdLnkRstTst[SIDE_DB_ART].linkCkeck = false;
            link_display_board(SIDE_DB_ART, LINK_CHECK_PKT);
        } else if (this.radReset.isSelected()) {
            selected_button = BTN_RESET;
            objBrdLnkRstTst[SIDE_DB_ART].reset = false;
            link_display_board(SIDE_DB_ART, SOFTREST_PKT);
        } else if (this.radTest.isSelected()) {
            selected_button = BTN_TEST;
            objBrdLnkRstTst[SIDE_DB_ART].test = false;
            link_display_board(SIDE_DB_ART, TEST_PKT);
        } else if (this.radTestCont.isSelected()) {
            selected_button = BTN_TESTCONT;
            objBrdLnkRstTst[SIDE_DB_ART].test_cont = false;
            if (radTestCEnable.isSelected()) {
                testc_display_board(SIDE_DB_ART, TESTC_ENABLE);
            } else {
                testc_display_board(SIDE_DB_ART, TESTC_DISABLE);
            }
        } else if (this.radDtc.isSelected()) {
            selected_button = BTN_DTC;
            objBrdLnkRstTst[SIDE_DB_ART].dtcCodes = false;
            data_buf = new byte[4];
            if (this.radDtcHigh.isSelected()) {

                buf = convertToByteArray((short) DTC_HIGH_VOLTAGE);
                data_buf[0] = buf[0];//byte) (DTC_HIGH_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[01];//(byte) (DTC_HIGH_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(SIDE_DB_ART, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcLow.isSelected()) {
                buf = convertToByteArray((short) DTC_LOW_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_LOW_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_LOW_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(SIDE_DB_ART, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcOverheat.isSelected()) {
                buf = convertToByteArray((short) DTC_OVER_HEAT_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(SIDE_DB_ART, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radOperatingHours.isSelected()) {
                buf = convertToByteArray((short) PID_OPERATING_HRS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArrayInt(Integer.parseInt(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];
                data_buf[5] = buf[2];
                data_buf[6] = buf[3];

                display_board_intensity(SIDE_DB_ART, PID_CODE_PKT, data_buf, (byte) 7);
            } else if (this.radNoResets.isSelected()) {
                buf = convertToByteArray((short) PID_NO_RESETS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArray((short) Short.parseShort(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];

                display_board_intensity(SIDE_DB_ART, PID_CODE_PKT, data_buf, (byte) 5);
            }

            data_buf = null;
        } else if (this.radIntensity.isSelected()) {
            selected_button = BTN_INTENSITY;
            data_buf = new byte[4];
            objBrdLnkRstTst[SIDE_DB_ART].intensity = false;
            data_buf[0] = 1; //WRITE_MODE
            if (this.radAutoInt.isSelected()) {
                data_buf[1] = 1;
            } else {
                data_buf[1] = 0;
            }
            switch (cmbIntVal.getSelectedIndex()) {
                case 0:
                    data_buf[2] = (byte) 10;
                    break;
                case 1:
                    data_buf[2] = (byte) 9;
                    break;
                case 2:
                    data_buf[2] = (byte) 8;
                    break;
                case 3:
                    data_buf[2] = (byte) 7;
                    break;
                case 4:
                    data_buf[2] = (byte) 6;
                    break;
                case 5:
                    data_buf[2] = (byte) 5;
                    break;
                case 6:
                    data_buf[2] = (byte) 4;
                    break;
                case 7:
                    data_buf[2] = (byte) 3;
                    break;
                case 8:
                    data_buf[2] = (byte) 2;
                    break;
                case 9:
                    data_buf[2] = (byte) 1;
                    break;
                case 10:
                    data_buf[2] = (byte) 0;
                    break;
            }

            data_buf[3] = 1;
            display_board_intensity(SIDE_DB_ART, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            data_buf = null;
        } else if (this.radDelStoredMsg.isSelected()) {
            selected_button = BTN_DELMSG;
            lblTest.setText("");
            objBrdLnkRstTst[SIDE_DB_ART].delPkt = false;
            if (this.radDelSingle.isSelected()) {
                pkt_no = ((short) Short.parseShort(this.spinDelValue.getValue().toString()));
            } else {
                pkt_no = 254;
            }
            deletePkt_display_board(SIDE_DB_ART, pkt_no);
        } else if (this.radBrdAddr.isSelected()) {

            lblTest.setText("");
            if (this.radBrdAddrGet.isSelected()) {
                selected_button = BTN_BRDADDR_GET;
                objBrdLnkRstTst[SIDE_DB_ART].setAddressPkt = false;
                objBrdLnkRstTst[SIDE_DB_ART].getAddressPkt = false;
                display_board_intensity(SIDE_DB_ART, DISBRD_GET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 0);
            } else {
                selected_button = BTN_BRDADDR_SET;
                objBrdLnkRstTst[SIDE_DB_ART].getAddressPkt = false;
                objBrdLnkRstTst[SIDE_DB_ART].setAddressPkt = false;
                data_buf[0] = clsDefines.SIDE_BRD;
                data_buf[1] = clsDefines.SD_ART_ADDR;
                data_buf[2] = clsDefines.SD_BRD_WIDTH;

                objBrdLnkRstTst[SIDE_DB_ART].setAddressPkt = false;
                display_board_intensity(SIDE_DB_ART, DISBRD_SET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 3);
            }
        }

        lblTest.setText("");
        btnSideLink1.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i = 0;
                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    switch (selected_button) {
                        case BTN_LINK: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].linkCkeck == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_RESET: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].reset == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TEST: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].test == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TESTCONT: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].test_cont == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DTC: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].dtcCodes == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DELMSG: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].delPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_INTENSITY: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].intensity == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_GET: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].getAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_SET: {
                            if (objBrdLnkRstTst[SIDE_DB_ART].setAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                    }
                    pbar.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setDisBrdLnkChkCame(false);
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                pbar.setVisible(false);
                btnSideLink1.setBackground(new java.awt.Color(0, 0, 102));
                try {
                    if (selected_button == BTN_BRDADDR_GET) {
                        if (found == true) {
                            // lblText.setText("  Get Address Command Success");
                            objBrdLnkRstTst[0].getAddressPkt = true;
                            lblTest.setText(clsSharedVariables.getDisBrdSetAddrData() + " Command Success");
                        } else {
                            lblTest.setText("Get Address Command Failure");

                            lblTest.setText("");
                        }
                    } else {
                        if (found == true) {
                            lblTest.setText("Command Success");

                        } else {
                            lblTest.setText("Command Failure");

                            lblSDArt.setIcon(sd_fail_icon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnSideLink1ActionPerformed

    private void btnInternalLink1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInternalLink1ActionPerformed
        // TODO add your handling code here:
        if (!getArtIntEnable()) {
            lblTest.setText("ArtInt Board Is Disabled");

            lblTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/id_no_status.png")));

            return;
        }
        if (getDisBrdLnkChkCame() && pbar.isVisible()) {
            lblTest.setText("Please wait 5 sec Background processing....");
            setDisBrdLnkChkCame(false);
            return;
        }
        byte[] data_buf = new byte[10];
        byte[] buf;
        short pkt_no = 0;
        pbar.setVisible(true);
        pbar.setValue(0);

        if (this.radLink.isSelected()) {
            selected_button = BTN_LINK;
            objBrdLnkRstTst[INT_DB_ART].linkCkeck = false;
            link_display_board(INT_DB_ART, LINK_CHECK_PKT);
        } else if (this.radReset.isSelected()) {
            selected_button = BTN_RESET;
            objBrdLnkRstTst[INT_DB_ART].reset = false;
            link_display_board(INT_DB_ART, SOFTREST_PKT);
        } else if (this.radTest.isSelected()) {
            selected_button = BTN_TEST;
            objBrdLnkRstTst[INT_DB_ART].test = false;
            link_display_board(INT_DB_ART, TEST_PKT);
        } else if (this.radTestCont.isSelected()) {
            selected_button = BTN_TESTCONT;
            objBrdLnkRstTst[INT_DB_ART].test_cont = false;
            if (radTestCEnable.isSelected()) {
                testc_display_board(INT_DB_ART, TESTC_ENABLE);
            } else {
                testc_display_board(INT_DB_ART, TESTC_DISABLE);
            }
        } else if (this.radDtc.isSelected()) {
            selected_button = BTN_DTC;
            objBrdLnkRstTst[INT_DB_ART].dtcCodes = false;
            data_buf = new byte[4];
            if (this.radDtcHigh.isSelected()) {

                buf = convertToByteArray((short) DTC_HIGH_VOLTAGE);
                data_buf[0] = buf[0];//byte) (DTC_HIGH_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[01];//(byte) (DTC_HIGH_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(INT_DB_ART, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcLow.isSelected()) {
                buf = convertToByteArray((short) DTC_LOW_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_LOW_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_LOW_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(INT_DB_ART, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radDtcOverheat.isSelected()) {
                buf = convertToByteArray((short) DTC_OVER_HEAT_VOLTAGE);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                data_buf[3] = Byte.parseByte(this.spinDtcVal.getValue().toString());

                display_board_intensity(INT_DB_ART, DTC_COUNT_CODE_PKT, data_buf, (byte) 4);
            } else if (this.radOperatingHours.isSelected()) {
                buf = convertToByteArray((short) PID_OPERATING_HRS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArrayInt(Integer.parseInt(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];
                data_buf[5] = buf[2];
                data_buf[6] = buf[3];

                display_board_intensity(INT_DB_ART, PID_CODE_PKT, data_buf, (byte) 7);
            } else if (this.radNoResets.isSelected()) {
                buf = convertToByteArray((short) PID_NO_RESETS);
                data_buf[0] = buf[0];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF00 >> 8);
                data_buf[1] = buf[1];//(byte) (DTC_OVER_HEAT_VOLTAGE & 0xFF);
                data_buf[2] = 1; //WRITE_MODE
                buf = convertToByteArray((short) Short.parseShort(this.spinDtcVal.getValue().toString()));
                data_buf[3] = buf[0];
                data_buf[4] = buf[1];

                display_board_intensity(INT_DB_ART, PID_CODE_PKT, data_buf, (byte) 5);
            }

            data_buf = null;
        } else if (this.radIntensity.isSelected()) {
            selected_button = BTN_INTENSITY;
            data_buf = new byte[4];
            objBrdLnkRstTst[INT_DB_ART].intensity = false;
            data_buf[0] = 1; //WRITE_MODE
            if (this.radAutoInt.isSelected()) {
                data_buf[1] = 1;
            } else {
                data_buf[1] = 0;
            }
            switch (cmbIntVal.getSelectedIndex()) {
                case 0:
                    data_buf[2] = (byte) 10;
                    break;
                case 1:
                    data_buf[2] = (byte) 9;
                    break;
                case 2:
                    data_buf[2] = (byte) 8;
                    break;
                case 3:
                    data_buf[2] = (byte) 7;
                    break;
                case 4:
                    data_buf[2] = (byte) 6;
                    break;
                case 5:
                    data_buf[2] = (byte) 5;
                    break;
                case 6:
                    data_buf[2] = (byte) 4;
                    break;
                case 7:
                    data_buf[2] = (byte) 3;
                    break;
                case 8:
                    data_buf[2] = (byte) 2;
                    break;
                case 9:
                    data_buf[2] = (byte) 1;
                    break;
                case 10:
                    data_buf[2] = (byte) 0;
                    break;
            }

            if (chkIntTimeEnabled.isSelected()) {
                data_buf[3] = 32;
                clsSharedVariables.int_disbrd_time_enabled = TIME_ENABLED;
            } else {
                data_buf[3] = 0;
                clsSharedVariables.int_disbrd_time_enabled = TIME_DISABLED;
            }
            display_board_intensity(INT_DB_ART, CONFIGURATION_INTENSITY_PKT, data_buf, (byte) 4);
            data_buf = null;
        } else if (this.radDelStoredMsg.isSelected()) {
            selected_button = BTN_DELMSG;
            lblTest.setText("");
            objBrdLnkRstTst[INT_DB_ART].delPkt = false;
            if (this.radDelSingle.isSelected()) {
                pkt_no = ((short) Short.parseShort(this.spinDelValue.getValue().toString()));
            } else {
                pkt_no = 254;
            }
            deletePkt_display_board(INT_DB_ART, pkt_no);
        } else if (this.radBrdAddr.isSelected()) {

            lblTest.setText("");
            if (this.radBrdAddrGet.isSelected()) {
                selected_button = BTN_BRDADDR_GET;
                objBrdLnkRstTst[INT_DB_ART].setAddressPkt = false;
                objBrdLnkRstTst[INT_DB_ART].getAddressPkt = false;
                display_board_intensity(INT_DB_ART, DISBRD_GET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 0);
            } else {
                selected_button = BTN_BRDADDR_SET;
                objBrdLnkRstTst[INT_DB_ART].getAddressPkt = false;
                objBrdLnkRstTst[INT_DB_ART].setAddressPkt = false;
                data_buf[0] = clsDefines.INT_BRD;
                data_buf[1] = clsDefines.ID_ART_ADDR;
                data_buf[2] = clsDefines.ID_BRD_WIDTH;

                objBrdLnkRstTst[INT_DB_ART].setAddressPkt = false;
                display_board_intensity(INT_DB_ART, DISBRD_SET_BRD_ADDR_CONFIG_PKT, data_buf, (byte) 3);
            }
        }

        lblTest.setText("");
        btnInternalLink1.setBackground(Color.GREEN);

        SwingWorker sw1 = new SwingWorker() {
            @Override
            protected String doInBackground() throws Exception {
                int i = 0;
                for (i = 0; i < MAX_SEC_WAIT_TIME; i++) {
                    switch (selected_button) {
                        case BTN_LINK: {
                            if (objBrdLnkRstTst[INT_DB_ART].linkCkeck == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_RESET: {
                            if (objBrdLnkRstTst[INT_DB_ART].reset == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TEST: {
                            if (objBrdLnkRstTst[INT_DB_ART].test == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_TESTCONT: {
                            if (objBrdLnkRstTst[INT_DB_ART].test_cont == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DTC: {
                            if (objBrdLnkRstTst[INT_DB_ART].dtcCodes == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_DELMSG: {
                            if (objBrdLnkRstTst[INT_DB_ART].delPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_INTENSITY: {
                            if (objBrdLnkRstTst[INT_DB_ART].intensity == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_GET: {
                            if (objBrdLnkRstTst[INT_DB_ART].getAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                        case BTN_BRDADDR_SET: {
                            if (objBrdLnkRstTst[INT_DB_ART].setAddressPkt == true) {
                                found = true;
                                return "success";
                            }
                            break;
                        }
                    }
                    pbar.setValue(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(PanDisBrdLinkChk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setDisBrdLnkChkCame(false);
                found = false;
                return "fail";
            }

            @Override
            protected void done() {
                // this method is called when the background
                // thread finishes execution
                pbar.setVisible(false);
                btnInternalLink1.setBackground(new java.awt.Color(0, 0, 102));
                try {
                    if (selected_button == BTN_BRDADDR_GET) {
                        if (found == true) {
                            // lblText.setText("  Get Address Command Success");
                            objBrdLnkRstTst[0].getAddressPkt = true;
                            lblTest.setText(clsSharedVariables.getDisBrdSetAddrData() + " Command Success");
                        } else {
                            lblTest.setText("Get Address Command Failure");

                            lblTest.setText("");
                        }
                    } else if (selected_button == BTN_DELMSG) {
                        deletePkt_display_board(INT_DB_ART, (byte) 254);
                    } else {
                        if (found == true) {
                            lblTest.setText("Command Success");

                        } else {
                            lblTest.setText("Command Failure");

                            lblIDArt.setIcon(id_fail_icon);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // executes the swingworker on worker thread
        sw1.execute();
    }//GEN-LAST:event_btnInternalLink1ActionPerformed

    private void radTestContItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radTestContItemStateChanged
        // TODO add your handling code here:
        if (radTestCont.isSelected()) {
            panTestCont.setVisible(true);
            this.panIntensity.setVisible(false);
            this.panDelMsg.setVisible(false);
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panBrdAddr.setVisible(false);
        } else {
            panTestCont.setVisible(false);
        }
    }//GEN-LAST:event_radTestContItemStateChanged

    private void radTestContStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radTestContStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radTestContStateChanged

    private void radTestContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTestContActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_radTestContActionPerformed

    private void radResetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radResetItemStateChanged
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (radReset.isSelected()) {
            this.panIntensity.setVisible(false);
            this.panDelMsg.setVisible(false);
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(false);
        }
    }//GEN-LAST:event_radResetItemStateChanged

    private void radResetStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radResetStateChanged

    }//GEN-LAST:event_radResetStateChanged

    private void radResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radResetActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_radResetActionPerformed

    private void radLinkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radLinkItemStateChanged
        // TODO add your handling code here:
        if (radLink.isSelected()) {
            this.panIntensity.setVisible(false);
            this.panDelMsg.setVisible(false);
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(false);
        }
    }//GEN-LAST:event_radLinkItemStateChanged

    private void radLinkStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radLinkStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radLinkStateChanged

    private void radLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radLinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radLinkActionPerformed

    private void radTestItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radTestItemStateChanged
        // TODO add your handling code here:
        if (radTest.isSelected()) {
            this.panIntensity.setVisible(false);
            this.panDelMsg.setVisible(false);
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(false);
        }
    }//GEN-LAST:event_radTestItemStateChanged

    private void radTestStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radTestStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radTestStateChanged

    private void radDelStoredMsgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radDelStoredMsgItemStateChanged
        // TODO add your handling code here:
        if (radDelStoredMsg.isSelected()) {
            this.panDelMsg.setVisible(true);
            this.panIntensity.setVisible(false);

            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(false);
        } else {
            this.panDelMsg.setVisible(false);
        }
    }//GEN-LAST:event_radDelStoredMsgItemStateChanged

    private void radDelStoredMsgStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radDelStoredMsgStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radDelStoredMsgStateChanged

    private void radDelStoredMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDelStoredMsgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radDelStoredMsgActionPerformed

    private void radIntensityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radIntensityItemStateChanged
        // TODO add your handling code here:
        if (radIntensity.isSelected()) {
            this.panIntensity.setVisible(true);

            this.panDelMsg.setVisible(false);
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(false);
        } else {
            this.panIntensity.setVisible(false);
        }
    }//GEN-LAST:event_radIntensityItemStateChanged

    private void radIntensityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radIntensityStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radIntensityStateChanged

    private void radIntensityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radIntensityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radIntensityActionPerformed

    private void radDtcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radDtcItemStateChanged
        // TODO add your handling code here:
        if (radDtc.isSelected()) {
            panDtc.setVisible(true);
            spinDtcVal.setVisible(true);
            this.panIntensity.setVisible(false);
            this.panDelMsg.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(false);
        } else {
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
        }
    }//GEN-LAST:event_radDtcItemStateChanged

    private void radDtcStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radDtcStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radDtcStateChanged

    private void radDtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDtcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radDtcActionPerformed

    private void radBrdAddrItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radBrdAddrItemStateChanged
        // TODO add your handling code here:
        if (radBrdAddr.isSelected()) {
            this.panIntensity.setVisible(false);

            this.panDelMsg.setVisible(false);
            panDtc.setVisible(false);
            spinDtcVal.setVisible(false);
            panTestCont.setVisible(false);
            panBrdAddr.setVisible(true);
        } else {
            panBrdAddr.setVisible(false);
        }
    }//GEN-LAST:event_radBrdAddrItemStateChanged

    private void radBrdAddrStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radBrdAddrStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_radBrdAddrStateChanged

    private void radBrdAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBrdAddrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radBrdAddrActionPerformed

    private void radAutoIntItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radAutoIntItemStateChanged
        // TODO add your handling code here:
        if (radAutoInt.isSelected()) {
            lblIntVal.setVisible(false);
            cmbIntVal.setVisible(false);
        }
    }//GEN-LAST:event_radAutoIntItemStateChanged

    private void radAutoIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAutoIntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radAutoIntActionPerformed

    private void radManIntItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radManIntItemStateChanged
        // TODO add your handling code here:
        if (radManInt.isSelected()) {
            lblIntVal.setVisible(true);
            cmbIntVal.setVisible(true);
        }
    }//GEN-LAST:event_radManIntItemStateChanged

    private void radManIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radManIntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radManIntActionPerformed

    private void chkIntTimeEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIntTimeEnabledActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkIntTimeEnabledActionPerformed

    private void radDelAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radDelAllItemStateChanged
        // TODO add your handling code here:

        if (radDelAll.isSelected()) {
            spinDelValue.setVisible(false);
        }
    }//GEN-LAST:event_radDelAllItemStateChanged

    private void radDelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDelAllActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_radDelAllActionPerformed

    private void radDelSingleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radDelSingleItemStateChanged
        // TODO add your handling code here:

        if (radDelSingle.isSelected()) {
            spinDelValue.setVisible(true);
        }
    }//GEN-LAST:event_radDelSingleItemStateChanged

    private void radDelSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDelSingleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radDelSingleActionPerformed

    private void radTestCDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radTestCDisableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radTestCDisableActionPerformed

    private void radDtcLowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDtcLowActionPerformed
        // TODO add your handling code here:
        if (radDtcLow.isSelected()) {
            SpinnerModel sm = new SpinnerNumberModel(0, 0, 255, 1); //default value,lower bound,upper bound,increment by
            spinDtcVal.setModel(sm);
            sm = null;
        }
    }//GEN-LAST:event_radDtcLowActionPerformed

    private void radDtcOverheatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDtcOverheatActionPerformed
        // TODO add your handling code here:
        if (radDtcOverheat.isSelected()) {
            SpinnerModel sm = new SpinnerNumberModel(0, 0, 255, 1); //default value,lower bound,upper bound,increment by
            spinDtcVal.setModel(sm);
            sm = null;
        }
    }//GEN-LAST:event_radDtcOverheatActionPerformed

    private void radNoResetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNoResetsActionPerformed
        // TODO add your handling code here:
        if (radNoResets.isSelected()) {
            SpinnerModel sm = new SpinnerNumberModel(0, 0, 65535, 1); //default value,lower bound,upper bound,increment by
            spinDtcVal.setModel(sm);
            sm = null;
        }
    }//GEN-LAST:event_radNoResetsActionPerformed

    private void radOperatingHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radOperatingHoursActionPerformed
        // TODO add your handling code here:
        if (radOperatingHours.isSelected()) {
            SpinnerModel sm = new SpinnerNumberModel(0, 0, 65535, 1); //default value,lower bound,upper bound,increment by
            spinDtcVal.setModel(sm);
            sm = null;
        }
    }//GEN-LAST:event_radOperatingHoursActionPerformed

    private void radDtcHighActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDtcHighActionPerformed
        // TODO add your handling code here:
        if (radDtcHigh.isSelected()) {
            SpinnerModel sm = new SpinnerNumberModel(0, 0, 255, 1); //default value,lower bound,upper bound,increment by
            spinDtcVal.setModel(sm);
            sm = null;
        }
    }//GEN-LAST:event_radDtcHighActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void radBrdAddrGetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radBrdAddrGetItemStateChanged
        // TODO add your handling code here:
        if (radBrdAddrGet.isSelected()) {
            spinBrdAddrVal.setVisible(false);
            txtBrdWidth.setVisible(false);
        }
    }//GEN-LAST:event_radBrdAddrGetItemStateChanged

    private void radBrdAddrGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBrdAddrGetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radBrdAddrGetActionPerformed

    private void radBrdAddrSetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radBrdAddrSetItemStateChanged
        // TODO add your handling code here:
        if (radBrdAddrSet.isSelected()) {
            spinBrdAddrVal.setVisible(true);
            txtBrdWidth.setVisible(true);
        }
    }//GEN-LAST:event_radBrdAddrSetItemStateChanged

    private void radBrdAddrSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBrdAddrSetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radBrdAddrSetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFront;
    private javax.swing.JButton btnFront1;
    private javax.swing.JButton btnFrontLink;
    private javax.swing.ButtonGroup btnGrpEnable;
    private javax.swing.ButtonGroup btnGrpGetSet;
    private javax.swing.ButtonGroup btnGrpManual;
    private javax.swing.ButtonGroup btnGrpVolt;
    private javax.swing.ButtonGroup btnGrpsingle;
    private javax.swing.JButton btnInternal;
    private javax.swing.JButton btnInternal1;
    private javax.swing.JButton btnInternal2;
    private javax.swing.JButton btnInternalArt;
    private javax.swing.JButton btnInternalLink;
    private javax.swing.JButton btnInternalLink1;
    private javax.swing.JButton btnRear;
    private javax.swing.JButton btnRear1;
    private javax.swing.JButton btnRearLink;
    private javax.swing.JButton btnSide;
    private javax.swing.JButton btnSide1;
    private javax.swing.JButton btnSide2;
    private javax.swing.JButton btnSideArt;
    private javax.swing.JButton btnSideLink;
    private javax.swing.JButton btnSideLink1;
    private javax.swing.ButtonGroup btndisplaylinkgrp;
    private javax.swing.JCheckBox chkIntTimeEnabled;
    private javax.swing.JComboBox cmbIntVal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblDTC;
    private javax.swing.JLabel lblIntVal;
    private javax.swing.JLabel lblPID;
    private javax.swing.JLabel lblTest;
    private javax.swing.JPanel panBrdAddr;
    private javax.swing.JPanel panDelMsg;
    private javax.swing.JPanel panDtc;
    private javax.swing.JPanel panIntensity;
    private javax.swing.JPanel panTestCont;
    private javax.swing.JProgressBar pbar;
    private javax.swing.JRadioButton radAutoInt;
    private javax.swing.JRadioButton radBrdAddr;
    private javax.swing.JRadioButton radBrdAddrGet;
    private javax.swing.JRadioButton radBrdAddrSet;
    private javax.swing.JRadioButton radDelAll;
    private javax.swing.JRadioButton radDelSingle;
    private javax.swing.JRadioButton radDelStoredMsg;
    private javax.swing.JRadioButton radDtc;
    private javax.swing.JRadioButton radDtcHigh;
    private javax.swing.JRadioButton radDtcLow;
    private javax.swing.JRadioButton radDtcOverheat;
    private javax.swing.JRadioButton radIntensity;
    private javax.swing.JRadioButton radLink;
    private javax.swing.JRadioButton radManInt;
    private javax.swing.JRadioButton radNoResets;
    private javax.swing.JRadioButton radOperatingHours;
    private javax.swing.JRadioButton radReset;
    private javax.swing.JRadioButton radTest;
    private javax.swing.JRadioButton radTestCDisable;
    private javax.swing.JRadioButton radTestCEnable;
    private javax.swing.JRadioButton radTestCont;
    private javax.swing.JSpinner spinBrdAddrVal;
    private javax.swing.JSpinner spinDelValue;
    private javax.swing.JSpinner spinDtcVal;
    private javax.swing.JTable tabDisplayDtc;
    private javax.swing.JTable tabDisplayPid;
    private javax.swing.JTextField txtBrdWidth;
    // End of variables declaration//GEN-END:variables
}
