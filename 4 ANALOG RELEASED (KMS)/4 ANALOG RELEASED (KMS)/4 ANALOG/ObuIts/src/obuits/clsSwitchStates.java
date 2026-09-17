package obuits;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class defines various constants representing different states for bus
 * stops, GSM, schedules, and display boards.
 *
 * @author Sumitha
 */
public class clsSwitchStates {

    // Bus stop states
    public static final byte BUS_STOP_STATE_IDLE = 0;               // Bus stop is idle
    public static final byte BUS_STOP_STATE_REACH_ANNOUNCE = 2;     // Announce reaching bus stop
    public static final byte BUS_STOP_STATE_REACHED = 3;            // Bus stop reached
    public static final byte BUS_STOP_STATE_CROSSED = 4;            // Bus stop crossed
    public static final byte BUS_STATE_NEXT_STOP = 5;               // Bus approaching next stop
    public static final byte BUS_STATE_LAST_STOP = 7;               // Last stop

    // Schedule states
    public static final byte SCH_INIT_STATE = 0;                    // Initial state for scheduling
    public static final byte SCH_SCH_REQ = 1;                       // Schedule request
    public static final byte SCH_STATE_CHK_SCHDULE = 2;             // Check schedule state
    public static final byte SCH_STATE_LOAD_CUR_SCH = 3;            // Load current schedule state
    public static final byte SCH_STATE_LOAD_TEMP_SCH = 4;           // Load temporary schedule state
    public static final byte SCH_STATE_LOAD_PER_SCH = 5;            // Load permanent schedule state
    public static final byte SCH_STATE_WAIT_FOR_SCHEDULE = 6;       // Wait for schedule state
    public static final byte SCH_STATE_LOAD_PER_AIGAN_SCH = 7;      // Load permanent schedule again state
    public static final byte SCH_STATE_SEND_DISPLAY_DATA = 8;       // Send display data state
    public static final byte SCH_CHK_CUR_STATE = 9;                 // Check current state
}
