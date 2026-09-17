/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import com.raspoid.Tools;
import com.raspoid.additionalcomponents.MPU6050;
import static com.raspoid.additionalcomponents.MPU6050.DEFAULT_DLPF_CFG;
import static com.raspoid.additionalcomponents.MPU6050.DEFAULT_SMPLRT_DIV;
import static com.raspoid.additionalcomponents.MPU6050.accelToString;
import static com.raspoid.additionalcomponents.MPU6050.angleToString;
import static com.raspoid.additionalcomponents.MPU6050.angularSpeedToString;
import static com.raspoid.additionalcomponents.MPU6050.xyzValuesToString;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static obuits.clsDefines.GYROSCOPE_THROUGH_GPS_ENABLE;
import static obuits.clsSharedVariables.setGyroValuesActivated;

/*
*@author Sumitha
*/
/**
 * Class to handle gyroscope data reading and processing in a separate thread.
 * It reads data from the MPU6050 sensor, processes it, and detects harsh
 * acceleration and braking events.
 */
public class clsGyroScope extends Thread {

    // Variables to store the formatted sensor data
    private static String accel_angles;
    private static String accel_accelerations;
    private static String gyro_angles;
    private static String gyro_speeds;
    private static String filtered_angles;

    // Constants for the number of records and harsh event detection
    byte MAX_NO_RECORDS = 15;
    byte MAX_NO_HA_RECORDS = 10;

    // Arrays to store gyroscope angles and speeds
    double[] gyro_angle_x = new double[MAX_NO_RECORDS];
    double[] gyro_angle_y = new double[MAX_NO_RECORDS];
    double[] gyro_angle_z = new double[MAX_NO_RECORDS];
    double[] ha_speed = new double[MAX_NO_HA_RECORDS];
    double[] hb_speed = new double[MAX_NO_HA_RECORDS];

    boolean LOG_GYRO_ENABLED = true; // Enable or disable logging
    clsReadFiles objReadFiles = new clsReadFiles(); // File reading and writing object
    boolean harsh_acc_detected = false; // Flag for harsh acceleration detection
    int harsh_acc_pkt_resend_inc = 0; // Counter for harsh acceleration packet resend
    boolean harsh_break_detected = false; // Flag for harsh braking detection
    int harsh_break_pkt_resend_inc = 0; // Counter for harsh braking packet resend

    @Override
    public void run() {
        String str;
        MPU6050 mpu6050 = null;

        try {
            // Initialize the MPU6050 sensor
            mpu6050 = new MPU6050(0x69, DEFAULT_DLPF_CFG, DEFAULT_SMPLRT_DIV);
            double[] gyroAngles;
            double[] accelAngles;
            double[] accelAccelerations;
            double[] gyroAngularSpeeds;
            double[] filteredAngles;
            mpu6050.startUpdatingThread();

            // Initialize gyroscope angle arrays
            byte i;
            for (i = 0; i < MAX_NO_RECORDS; i++) {
                gyro_angle_x[i] = 0.0;
                gyro_angle_y[i] = 0.0;
                gyro_angle_z[i] = 0.0;
            }

            double sum_x = 0;
            double sum_y = 0;
            double sum_z = 0;
            int start_val = 0;
            int WAIT_TIME_NOT_SEND_PKT_AGAIN = 120; // Wait time between sending packets
            boolean rash_turn_pkt_send = false; // Flag for rash turn packet send
            int rash_pkt_resend_inc = 0; // Counter for rash turn packet resend
            double accelX;
            double accelY;
            double accelZ;
            double totalAccel;

            while (true) {
                // Read gyroscope and accelerometer data
                mpu6050.getGyroAngularSpeeds();
                accelAngles = mpu6050.getAccelAngles();
                str = xyzValuesToString(angleToString(accelAngles[0]),
                        angleToString(accelAngles[1]), angleToString(accelAngles[2]));
                set_accel_angles(str);

                accelAccelerations = mpu6050.getAccelAccelerations();
                str = xyzValuesToString(accelToString(accelAccelerations[0]),
                        accelToString(accelAccelerations[1]), accelToString(accelAccelerations[2]));
                //   Tools.log("\tAccelerations: " + str);
                if (LOG_GYRO_ENABLED) {
                    //  objReadFiles.write_gyro_data_from_server("Accelerations  :" + str);
                }
                set_accelerations(str);

                // Calculate total acceleration and detect harsh acceleration and braking
                accelX = accelAccelerations[0];
                accelY = accelAccelerations[1];
                accelZ = accelAccelerations[2];
                totalAccel = Math.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
                harsh_acceleration_detection(totalAccel);
                harsh_break_detection(accelZ);

                // Read gyroscope angles
                gyroAngles = mpu6050.getGyroAngles();
                str = xyzValuesToString(angleToString(gyroAngles[0]),
                        angleToString(gyroAngles[1]), angleToString(gyroAngles[2]));
                if (LOG_GYRO_ENABLED) {
                    // objReadFiles.write_gyro_data_from_server_log(str);
                }
                //Tools.log("\t" + str);
                if (start_val >= MAX_NO_RECORDS) {
                    for (i = 0; i < MAX_NO_RECORDS - 1; i++) {
                        gyro_angle_x[i] = gyro_angle_x[i + 1];
                        gyro_angle_y[i] = gyro_angle_y[i + 1];
                        gyro_angle_z[i] = gyro_angle_z[i + 1];
                    }
                    gyro_angle_x[i] = gyroAngles[0];
                    gyro_angle_y[i] = gyroAngles[1];
                    gyro_angle_z[i] = gyroAngles[2];

                    sum_x = 0.0;
                    sum_y = 0.0;
                    sum_z = 0.0;

                    if (rash_turn_pkt_send == false) {
                        if (rash_turn_detection_x() == true) {
                            rash_turn_pkt_send = true;
                        } else if (rash_turn_detection_y() == true) {
                            //show_message_dialogbox("RASH TURN");
                            rash_turn_pkt_send = true;
                        } else if (rash_turn_detection_z() == true) {
                            //  show_message_dialogbox("RASH TURN");
                            rash_turn_pkt_send = true;
                        }
                    }
                    {
                        if (rash_turn_pkt_send == true) {
                        rash_pkt_resend_inc++;
                        if (rash_pkt_resend_inc > 1 * 60 * 1000 / 50) { // 2 minutes
                            rash_turn_pkt_send = false;
                            rash_pkt_resend_inc = 0;
                            objReadFiles.write_gyro_data_from_server("Rash Turning restart after 1 min");
                            }
                        }
                    }
                } else {
                    gyro_angle_x[start_val] = gyroAngles[0];
                    gyro_angle_y[start_val] = gyroAngles[1];
                    gyro_angle_z[start_val] = gyroAngles[2];
                    start_val++;
                }
                set_gyro_angles(str);

                // Read and log gyroscope angular speeds
                gyroAngularSpeeds = mpu6050.getGyroAngularSpeeds();
                str = xyzValuesToString(angularSpeedToString(gyroAngularSpeeds[0]),
                        angularSpeedToString(gyroAngularSpeeds[1]), angularSpeedToString(gyroAngularSpeeds[2]));
                set_gyro_speeds(str);

                // Read and log filtered angles
                filteredAngles = mpu6050.getFilteredAngles();
                str = xyzValuesToString(angleToString(filteredAngles[0]),
                        angleToString(filteredAngles[1]), angleToString(filteredAngles[2]));
                set_filtered_angles(str);

                Tools.sleepMilliseconds(50); // Sleep for 50 milliseconds
                setGyroValuesActivated(true);

                try {
                    Thread.sleep(50); // Sleep for 50 milliseconds
                } catch (InterruptedException ex) {
                    break; // Break the loop if interrupted
                }
            }
        } catch (Exception ex) {
            setGyroValuesActivated(false);
            if (mpu6050 != null) {
                try {
                    mpu6050.stopUpdatingThread();
                } catch (InterruptedException ex1) {
                    // //Logger.getLogger(clsGyroScope.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (Exception ex1) {
                    // //Logger.getLogger(clsGyroScope.class.getName()).log(Level.SEVERE, null, ex1);
                }
                try {
                    mpu6050.stopUpdatingThread();
                } catch (InterruptedException ex1) {
                    //Logger.getLogger(clsGyroScope.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (Exception ex1) {
                    // //Logger.getLogger(clsGyroScope.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

   }
    int harsh_acc_cnt = 0; // Counter for harsh acceleration events
    double start_harsh_acc_speed = 0; // Starting speed for harsh acceleration

    int harsh_brk_cnt = 0; // Counter for harsh braking events
    double start_harsh_brk_speed = 0; // Starting speed for harsh braking

    /**
     * Method to detect harsh braking events based on the speed.
     *
     * @param speed The current speed
     */
    
    void harsh_break_detection(double speed) {
        hb_speed[9] = (speed);
        if (GYROSCOPE_THROUGH_GPS_ENABLE == false) {
            if (harsh_break_detected == false) {
                if ((harsh_brk_cnt >= 10)) {
                    if ((hb_speed[8] - hb_speed[9]) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[8];
                        harsh_brk_cnt = 0;
                        harsh_break_detected = true;
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;   // }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) >= gpsDriving.harsh_brk_threshold)) {
                        start_harsh_brk_speed = hb_speed[7];
                        harsh_brk_cnt = 0;
                        harsh_break_detected = true;
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;   // }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[6];
                        harsh_brk_cnt = 0;
                        harsh_break_detected = true;
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;     // }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7]) + (hb_speed[5] - hb_speed[6])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[5];
                        harsh_brk_cnt = 0;
                        harsh_break_detected = true;
                        //   if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;    //  }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                            + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[4];
                        harsh_break_detected = true;
                        harsh_brk_cnt = 0;
                        // if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;    // }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                            + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[3];
                        harsh_break_detected = true;
                        // if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;    // }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                            + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])
                            + (hb_speed[2] - hb_speed[3])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[2];
                        harsh_brk_cnt = 0;
                        //if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        harsh_break_detected = true;
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;       // }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                            + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])
                            + (hb_speed[2] - hb_speed[3]) + (hb_speed[1] - hb_speed[2])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[1];
                        harsh_break_detected = true;
                        harsh_brk_cnt = 0;
                        //  if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;    //  }
                    } else if (((hb_speed[8] - hb_speed[9]) + (hb_speed[7] - hb_speed[8]) + (hb_speed[6] - hb_speed[7])
                            + (hb_speed[5] - hb_speed[6]) + (hb_speed[4] - hb_speed[5]) + (hb_speed[3] - hb_speed[4])
                            + (hb_speed[2] - hb_speed[3]) + (hb_speed[1] - hb_speed[2]) + (hb_speed[0] - hb_speed[1])) >= gpsDriving.harsh_brk_threshold) {
                        start_harsh_brk_speed = hb_speed[0];
                        harsh_break_detected = true;
                        harsh_brk_cnt = 0;
                        // if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHBRK_PKT, 'L', false, " ");
                        obj16833Pkts = null;     //  }
                    }
                } else {
                    harsh_brk_cnt++;
                    if ((harsh_brk_cnt >= 254)) {
                        harsh_brk_cnt = 0;
                }
                }
            } else if (harsh_break_detected == true) {
                harsh_break_pkt_resend_inc++;
                if (harsh_break_pkt_resend_inc > 1 * 30 * 1000 / 50) { // 1 minutes if 60 instead 30 2 min
                    harsh_break_detected = false;
                    harsh_break_pkt_resend_inc = 0;
                }
            }
         //   System.arraycopy(hb_speed, 1, hb_speed, 0, 9);
        }
        hb_speed[0] = hb_speed[1];
        hb_speed[1] = hb_speed[2];
        hb_speed[2] = hb_speed[3];
        hb_speed[3] = hb_speed[4];
        hb_speed[4] = hb_speed[5];
        hb_speed[5] = hb_speed[6];
        hb_speed[6] = hb_speed[7];
        hb_speed[7] = hb_speed[8];
        hb_speed[8] = hb_speed[9];
    }

    /**
     * Method to detect harsh acceleration events based on the speed.
     *
     * @param speed The current speed
     */
    
    void harsh_acceleration_detection(double speed) {
        // Harsh Acceleration
        ha_speed[9] = (speed);
        if (GYROSCOPE_THROUGH_GPS_ENABLE == false) {
            if (harsh_acc_detected == false) {
                if ((harsh_acc_cnt >= 10)) {
                    if (((ha_speed[9] + ha_speed[8]) >= (gpsDriving.harsh_acc_threshold))) {
                        start_harsh_acc_speed = ha_speed[8];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
			{
			cls16833Protocols obj16833Pkts = new cls16833Protocols();
                            obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                            obj16833Pkts = null;

                        }
		    }else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7])) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[7];
                        harsh_acc_cnt = 0;
                        //if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        harsh_acc_detected = true;
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;        // }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6])) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[6];
                        harsh_acc_cnt = 0;
                        //if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        harsh_acc_detected = true;
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;       // }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6]
                            + (ha_speed[6] + ha_speed[5]))) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[5];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
                        //  if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;     // }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6]
                            + (ha_speed[6] + ha_speed[5]) + (ha_speed[5] + ha_speed[4]))) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[4];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
                        //if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;      //  }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6]
                            + (ha_speed[6] + ha_speed[5]) + (ha_speed[5] + ha_speed[4]) + (ha_speed[4] + ha_speed[3]))) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[3];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
                        // if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;        // }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6]
                            + (ha_speed[6] + ha_speed[5]) + (ha_speed[5] + ha_speed[4]) + (ha_speed[4] + ha_speed[3])
                            + (ha_speed[3] + ha_speed[2]))) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[2];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
                        // if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;        // }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6]
                            + (ha_speed[6] + ha_speed[5]) + (ha_speed[5] + ha_speed[4]) + (ha_speed[4] + ha_speed[3])
                            + (ha_speed[3] + ha_speed[2]) + (ha_speed[2] + ha_speed[1]))) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[1];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
                        // if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;        // }
                    } else if ((((ha_speed[9] + ha_speed[8]) + (ha_speed[8] + ha_speed[7]) + (ha_speed[7] + ha_speed[6]
                            + (ha_speed[6] + ha_speed[5]) + (ha_speed[5] + ha_speed[4]) + (ha_speed[4] + ha_speed[3])
                            + (ha_speed[3] + ha_speed[2]) + (ha_speed[2] + ha_speed[1]) + (ha_speed[1] + ha_speed[0]))) >= gpsDriving.harsh_acc_threshold)) {
                        start_harsh_acc_speed = ha_speed[0];
                        harsh_acc_cnt = 0;
                        harsh_acc_detected = true;
                        //if (clsDefines.PROTOCOL_16833 == clsDefines.PRODUCT_UBS2) {
                        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                        obj16833Pkts.trackingMessagePkt(clsPacketTypes.HARSHACC_PKT, 'L', false, " ");
                        obj16833Pkts = null;       // }
                    }
                } else {
                    harsh_acc_cnt++;
                    if ((harsh_acc_cnt >= 254)) {
                        harsh_acc_cnt = 0;
                }
                }
            } else if (harsh_acc_detected == true) {
                harsh_acc_pkt_resend_inc++;
                if (harsh_acc_pkt_resend_inc > 1 * 30 * 1000 / 50) { // 1 minutes if 60 instead 30 2 min
                    harsh_acc_detected = false;
                    harsh_acc_pkt_resend_inc = 0;
                }
            }
        }
        ha_speed[0] = ha_speed[1];
        ha_speed[1] = ha_speed[2];
        ha_speed[2] = ha_speed[3];
        ha_speed[3] = ha_speed[4];
        ha_speed[4] = ha_speed[5];
        ha_speed[5] = ha_speed[6];
        ha_speed[6] = ha_speed[7];
        ha_speed[7] = ha_speed[8];
        ha_speed[8] = ha_speed[9];
        }
    boolean rash_turn_detection_x() {
        int i = MAX_NO_RECORDS - 1;
        int j = MAX_NO_RECORDS - 2;
        int k = 0;
        double diff = 0.0;
        double rash_turn_threshold = clsSharedVariables.getGyroAngle();
        double sum = 0.0;
        try {
            for (k = 0; k < MAX_NO_RECORDS - 1; k++) {
                if (gyro_angle_x[i] > gyro_angle_x[j]) {
                    diff = gyro_angle_x[i] - gyro_angle_x[j];
                } else {
                    diff = gyro_angle_x[j] - gyro_angle_x[i];
    }
                sum = sum + diff;
                if (sum > rash_turn_threshold) {

                    cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.RASH_TURNING_PKT, 'L', false, " ");
                    obj16833Pkts = null;

                    if (LOG_GYRO_ENABLED) {
                        objReadFiles.write_gyro_data_from_server("Gyroangle detected x :" + sum);
                    }
                    return true;
                }

                i--;
                j--;
            }
        } catch (Exception ex) {
            if (LOG_GYRO_ENABLED) {
                objReadFiles.write_gyro_data_from_server("exception x:" + ex.getMessage());
            }
        }
        if (LOG_GYRO_ENABLED) {
            objReadFiles.write_gyro_data_from_server("Gyroangle not detected x :" + sum);
        }
        return false;
    }
    boolean rash_turn_detection_y() {
        int i = MAX_NO_RECORDS - 1;
        int j = MAX_NO_RECORDS - 2;
        int k = 0;
        double diff = 0.0;
        double threshold = clsSharedVariables.getGyroAngle();
        double start_gyro_angle = gyro_angle_y[i];
        double sum = 0.0;
        try {
            for (k = 0; k < MAX_NO_RECORDS - 1; k++) {
                start_gyro_angle = gyro_angle_y[j];
                if (gyro_angle_y[i] > gyro_angle_y[j]) {
                    diff = gyro_angle_y[i] - gyro_angle_y[j];
                } else {
                    diff = gyro_angle_y[j] - gyro_angle_y[i];
                }
                sum = sum + diff;
                if (sum > threshold) {
        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.RASH_TURNING_PKT, 'L', false, " ");
        obj16833Pkts = null;
                    if (LOG_GYRO_ENABLED) {
                        objReadFiles.write_gyro_data_from_server("Gyroangle detected y :" + sum);
    }
                    return true;
                }
                i--;
                j--;
            }
            if (LOG_GYRO_ENABLED) {
                objReadFiles.write_gyro_data_from_server("Gyroangle not detected y :" + sum);
            }
        } catch (Exception ex) {
            if (LOG_GYRO_ENABLED) {
                objReadFiles.write_gyro_data_from_server("exception y:" + ex.getMessage());
            }
        }
        return false;
    }

    boolean rash_turn_detection_z() {
        int i = MAX_NO_RECORDS - 1;
        int j = MAX_NO_RECORDS - 2;
        int k = 0;
        double diff = 0.0;
        double threshold = clsSharedVariables.getGyroAngle();
        double start_gyro_angle;
        double sum = 0.0;
        try {
            for (k = 0; k < MAX_NO_RECORDS - 1; k++) {
                start_gyro_angle = gyro_angle_z[j];
                if (gyro_angle_z[i] > gyro_angle_z[j]) {
                    diff = gyro_angle_z[i] - gyro_angle_z[j];
                } else {
                    diff = gyro_angle_z[j] - gyro_angle_z[i];
                }
                sum = sum + diff;
                if (sum > threshold) {
        cls16833Protocols obj16833Pkts = new cls16833Protocols();
                    obj16833Pkts.trackingMessagePkt(clsPacketTypes.RASH_TURNING_PKT, 'L', false, " ");
        obj16833Pkts = null;
                    if (LOG_GYRO_ENABLED) {
                        objReadFiles.write_gyro_data_from_server("Gyroangle detected z :" + sum);
                    }
                    return true;
                }
                i--;
                j--;
            }
            if (LOG_GYRO_ENABLED) {
                objReadFiles.write_gyro_data_from_server("Gyroangle not detected z :" + sum);
            }
        } catch (Exception ex) {
            if (LOG_GYRO_ENABLED) {
                objReadFiles.write_gyro_data_from_server("exception z:" + ex.getMessage());
            }
        }
        return false;
    }
   
    public synchronized String get_accel_angles() {
        return accel_angles;
    }
    public synchronized void set_accel_angles(String data) {
        accel_angles = data;
    }

    public synchronized String get_accelerations() {
        return accel_accelerations;
    }
    public synchronized void set_accelerations(String data) {
        accel_accelerations = data;
    }

    public synchronized String get_gyro_angles() {
        return gyro_angles;
    }

    public synchronized void set_gyro_angles(String data) {
        gyro_angles = data;
    }

    public synchronized String get_gyro_speeds() {
        return gyro_speeds;
    }

    public synchronized void set_gyro_speeds(String data) {
        gyro_speeds = data;
    }

    public synchronized String get_filtered_angles() {
        return filtered_angles;
    }

    public synchronized void set_filtered_angles(String data) {
        filtered_angles = data;
    }
}
