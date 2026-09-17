package obuits;

public class clsWatchdogResetVariables {

    static int gps_watchdog_var = 0;
    static int gps_serial_watchdog_var = 0;
    static int gprmc_watchdog_var = 0;
    static int at_watchdog_var = 0;
    static int at_serial_watchdog_var = 0;
    static int can_serial_watchdog_var = 0;
    static int can_serial_watchdog_var1 = 0;
    static int can_queue_watchdog_var = 0;
    static int can_queue_watchdog_var1 = 0;
    static int bus_stopdet_watchdog_var = 0;
    static int disbrd_watchdog_var = 0;
    static int disbrdSerial_watchdog_var = 0;
    static int sch_watchdog_var = 0;
    static int apc_watchdog_var = 0;
    static int apc_watchdog_var1 = 0;

    public void setgps_watchdog_val(int val) {
        gps_watchdog_var = val;
    }

    public int getgps_watchdog_val() {
        return gps_watchdog_var;
    }

    public void setgpsSerial_watchdog_val(int val) {
        gps_serial_watchdog_var = val;
    }

    public int getgpsSerial_watchdog_val() {
        return gps_serial_watchdog_var;
    }

    public void setgprmc_watchdog_val(int val) {
        gprmc_watchdog_var = val;
    }

    public int getgprmc_watchdog_val() {
        return gprmc_watchdog_var;
    }

    public void setAtSerial_watchdog_val(int val) {
        at_serial_watchdog_var = val;
    }

    public int getAtSerial_watchdog_val() {
        return at_serial_watchdog_var;
    }

    public void setAt_watchdog_val(int val) {
        at_watchdog_var = val;
    }

    public int getAt_watchdog_val() {
        return at_watchdog_var;
    }

    public void setCan_watchdog_val(int val) {
    }

    public void setCan_watchdog_val1(int val) {
    }

    public void setCanSerial_watchdog_val(int val) {
        can_serial_watchdog_var = val;
    }

    public int getCanSerial_watchdog_val() {
        return can_serial_watchdog_var;
    }

    public void setCanSerial_watchdog_val1(int val) {
        can_serial_watchdog_var1 = val;
    }

    public int getCanSerial_watchdog_val1() {
        return can_serial_watchdog_var1;
    }

    public void setCanQueue_watchdog_val(int val) {
        can_queue_watchdog_var = val;
    }

    public int getCanQueue_watchdog_val() {
        return can_queue_watchdog_var;
    }

    public void setCanQueue_watchdog_val1(int val) {
        can_queue_watchdog_var1 = val;
    }

    public int getCanQueue_watchdog_val1() {
        return can_queue_watchdog_var1;
    }

    public void setBusStopDet_watchdog_val(int val) {
        bus_stopdet_watchdog_var = val;
    }

    public int getBusStopDet_watchdog_val() {
        return bus_stopdet_watchdog_var;
    }

    public void setDisbrd_watchdog_val(int val) {
        disbrd_watchdog_var = val;
    }

    public int getDisbrd_watchdog_val() {
        return disbrd_watchdog_var;
    }

    public void setDisbrdSerial_watchdog_val(int val) {
        disbrdSerial_watchdog_var = val;
    }

    public int getDisbrdSerial_watchdog_val() {
        return disbrdSerial_watchdog_var;
    }

    public void setSch_watchdog_val(int val) {
        sch_watchdog_var = val;
    }

    public int getSch_watchdog_val() {
        return sch_watchdog_var;
    }

    public void setapc_watchdog_val(int val) {
        apc_watchdog_var = val;
    }

    public int getapc_watchdog_val() {
        return apc_watchdog_var;
    }

    public void setapc_watchdog_val1(int val) {
        apc_watchdog_var1 = val;
    }

    public int getapc_watchdog_val1() {
        return apc_watchdog_var1;
    }
}
