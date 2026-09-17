/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the structure of a health packet containing various status
 * indicators and timestamps related to an On-Board Unit (OBU).
 *
 * @author Sumitha
 */
public class clsHealthPacketStructure {

    // Status indicators as byte variables
    private static byte ignition_status = 0;
    private static byte mains_status = 0;
    private static byte battery_status = 0;
    private static byte gps_status = 0;
    private static byte gprs_status = 0;

    // Timestamps for packets sent and received to/from different IPs
    private static long last_pkt_received_to_obu_ip1 = 0;
    private static long last_pkt_sent_from_obu_ip4 = 0;
    private static long last_pkt_received_to_obu_ip4 = 0;
    private static long last_pkt_sent_from_obu_ip5 = 0;

    // Route and trip information
    private static String route_no = "";
    private static byte trip_no = 0;

    // Timestamps for specific events
    private static long last_overspeed_time = 0;
    private static long last_harsh_acc_time = 0;
    private static long last_harsh_brk_time = 0;

    // Camera status indicators
    private static boolean camera_status = false;
    private static byte cam1_status = 0;
    private static byte cam2_status = 0;
    private static byte cam3_status = 0;
    private static byte cam4_status = 0;
    private static byte cam5_status = 0;
    private static byte cam6_status = 0;
    private static byte cam7_status = 0;
    private static byte cam8_status = 0;

    // Audio status for each camera
    private static byte cam_audio1_status = 0;
    private static byte cam_audio2_status = 0;
    private static byte cam_audio3_status = 0;
    private static byte cam_audio4_status = 0;
    private static byte cam_audio5_status = 0;
    private static byte cam_audio6_status = 0;
    private static byte cam_audio7_status = 0;
    private static byte cam_audio8_status = 0;

    // CAN (Controller Area Network) status
    private static byte can_status = 0;

    // Other statuses
    private static byte fd_status = 0;
    private static byte sd_status = 0;
    private static byte rd_status = 0;
    private static byte id_status = 5;
    private static byte sd_art_status = 5;
    private static byte id_art_status = 5;

    // Setters for various status indicators
    public void set_ignition_status(byte status) {
        ignition_status = status;
    }

    public byte get_ignition_status() {
        return ignition_status;
    }

    public void set_mains_status(byte status) {
        mains_status = status;
    }

    public byte get_mains_status() {
        return mains_status;
    }

    public void set_battery_status(byte status) {
        battery_status = status;
    }

    public byte get_battery_status() {
        return battery_status;
    }

    public void set_gps_status(byte status) {
        gps_status = status;
    }

    public byte get_gps_status() {
        return gps_status;
    }

    public void set_gprs_status(byte status) {
        gprs_status = status;
    }

    public byte get_gprs_status() {
        return gprs_status;
    }

    // Setters for timestamps
    public void set_last_pkt_received_to_obu_ip1(long data) {
        last_pkt_received_to_obu_ip1 = data;
    }

    public void set_last_pkt_sent_from_obu_ip4(long data) {
        last_pkt_sent_from_obu_ip4 = data;
    }

    public void set_last_pkt_received_to_obu_ip4(long data) {
        last_pkt_received_to_obu_ip4 = data;
    }

    public void set_last_pkt_sent_from_obu_ip5(long data) {
        last_pkt_sent_from_obu_ip5 = data;
    }

    // Setters for route and trip information
    public void set_route_no(String data) {
        route_no = data;
    }

    public void set_trip_no(byte data) {
        trip_no = data;
    }

    // Setters for event timestamps
    public void set_last_overspeed_time(long data) {
        last_overspeed_time = data;
    }

    public void set_last_harsh_acc_time(long data) {
        last_harsh_acc_time = data;
    }

    public void set_last_harsh_brk_time(long data) {
        last_harsh_brk_time = data;
    }

    // Synchronized setter for camera status
    public synchronized void set_cam_status_updated(boolean status) {
        camera_status = status;
    }

    // Synchronized getter for camera status
    public synchronized boolean get_cam_status_updated() {
        return camera_status;
    }

    // Setters for camera statuses
    public void set_cam1_status(byte status) {
        cam1_status = status;
    }

    public void set_cam2_status(byte status) {
        cam2_status = status;
    }

    public void set_cam3_status(byte status) {
        cam3_status = status;
    }

    public void set_cam4_status(byte status) {
        cam4_status = status;
    }

    public void set_cam5_status(byte status) {
        cam5_status = status;
    }

    public void set_cam6_status(byte status) {
        cam6_status = status;
    }

    public void set_cam7_status(byte status) {
        cam7_status = status;
    }

    public void set_cam8_status(byte status) {
        cam8_status = status;
    }

    // Setters for other statuses
    public void set_can_status(byte status) {
        can_status = status;
    }

    public void set_fd_status(byte status) {
        fd_status = status;
    }

    public void set_sd_status(byte status) {
        sd_status = status;
    }

    public void set_rd_status(byte status) {
        rd_status = status;
    }

    public void set_id_status(byte status) {
        id_status = status;
    }

    public void set_sd_art_status(byte status) {
        sd_art_status = status;
    }

    public void set_id_art_status(byte status) {
        id_art_status = status;
    }

    // Setters for camera audio statuses
    public void set_cam_audio1_status(byte status) {
        cam_audio1_status = status;
    }

    public void set_cam_audio2_status(byte status) {
        cam_audio2_status = status;
    }

    public void set_cam_audio3_status(byte status) {
        cam_audio3_status = status;
    }

    public void set_cam_audio4_status(byte status) {
        cam_audio4_status = status;
    }

    public void set_cam_audio5_status(byte status) {
        cam_audio5_status = status;
    }

    public void set_cam_audio6_status(byte status) {
        cam_audio6_status = status;
    }

    public void set_cam_audio7_status(byte status) {
        cam_audio7_status = status;
    }

    public void set_cam_audio8_status(byte status) {
        cam_audio8_status = status;
    }

    // Getters for various statuses
    public byte get_fd_status() {
        return fd_status;
    }

    public byte get_sd_status() {
        return sd_status;
    }

    public byte get_rd_status() {
        return rd_status;
    }

    public byte get_id_status() {
        return id_status;
    }

    public byte get_sd_art_status() {
        return sd_art_status;
    }

    public byte get_id_art_status() {
        return id_art_status;
    }

    public byte get_cam1_status() {
        return cam1_status;
    }

    public byte get_cam2_status() {
        return cam2_status;
    }

    public byte get_cam3_status() {
        return cam3_status;
    }

    public byte get_cam4_status() {
        return cam4_status;
    }

    public byte get_cam5_status() {
        return cam5_status;
    }

    public byte get_cam6_status() {
        return cam6_status;
    }

    public byte get_cam7_status() {
        return cam7_status;
    }

    public byte get_cam8_status() {
        return cam8_status;
    }

    public byte get_can_status() {
        return can_status;
    }

    public byte get_cam_audio1_status() {
        return cam_audio1_status;
    }

    public byte get_cam_audio2_status() {
        return cam_audio2_status;
    }

    public byte get_cam_audio3_status() {
        return cam_audio3_status;
    }

    public byte get_cam_audio4_status() {
        return cam_audio4_status;
    }

    public byte get_cam_audio5_status() {
        return cam_audio5_status;
    }

    public byte get_cam_audio6_status() {
        return cam_audio6_status;
    }

    public byte get_cam_audio7_status() {
        return cam_audio7_status;
    }

    public byte get_cam_audio8_status() {
        return cam_audio8_status;
    }

    /**
     * Method to collect a list of statuses and information related to IP4.
     *
     * @return A list containing various statuses and information.
     */
    public List getDataIp4() {
        List lst = new ArrayList();
        lst.add(ignition_status);
        lst.add(mains_status);
        lst.add(battery_status);
        lst.add(gps_status);
        lst.add(gprs_status);
        lst.add(last_pkt_sent_from_obu_ip4);
        lst.add(last_pkt_received_to_obu_ip4);
        lst.add(route_no);
        lst.add(trip_no);
        lst.add(last_overspeed_time);
        lst.add(last_harsh_acc_time);
        lst.add(last_harsh_brk_time);
        lst.add(cam1_status);
        lst.add(cam2_status);
        lst.add(cam3_status);
        lst.add(cam4_status);
        lst.add(can_status);
        lst.add(fd_status);
        lst.add(sd_status);
        lst.add(rd_status);
        lst.add(id_status);
        return lst;
    }
}
