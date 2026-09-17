/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.nio.ByteBuffer;
import static obuits.clsDefines.FONT_16_BOLD;
import static obuits.clsDefines.NO_SCROLL_INTERNAL_DISPLAY_BRD;
import static obuits.clsDefines.SCR_FULL;
import static obuits.clsDisplayBrdSerialPort.DRIVER_NO_DISPLAY_INT_PKT_NO;
import static obuits.clsSharedVariables.int_disbrd_msg_buf;
import static obuits.clsSharedVariables.setIntDisbBrdMsgCame;

/**
 * @author Sumith
 *
 */

/*
 * Class for handling internal display board messages.
 */
public class clsInternalDisBrdMessage {

    /**
     * Converts a short value to a byte array.
     *
     * @param value the short value to convert.
     * @return the byte array representing the short value.
     */
    public static byte[] convertToByteArray(short value) {
        byte[] bytes = new byte[2];
        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(bytes.length);
        buffer.putShort(value);
        bytes = null;
        return buffer.array();
    }

    /**
     * Fills internal details for display board messages.
     *
     * @param data the data to be included in the message.
     * @param save_data the flag indicating whether to save the data.
     */
    public static synchronized void fill_internal_details(String data, byte save_data) {
        int i = 0;
        final int MAX_PKT_BUFFER_SIZE = 6500;
        final int MAX_BITMAP_LEN_INT_LINE1_2 = 6000;
        byte[] pkt_buf = new byte[MAX_PKT_BUFFER_SIZE];
        byte[] int_bitmap = null;
        byte[] buf;
        setIntDisbBrdMsgCame(false);
        try {
            byte language_cnt = 0;
            byte type = 0;
            int bitmap_length;
            short bitmap_len_msb;
            int pkt_length = 0;
            byte msb_val = 0;
            byte lsb_val = 0;

            int byte_inc = 0;
            int buf_inc = 0;

            // Add headers to the packet buffer
            pkt_buf[buf_inc++] = (byte) clsDefines.HEADER1;
            pkt_buf[buf_inc++] = (byte) clsDefines.HEADER2;

            buf_inc = 4;

            // Add VMU and ID addresses, data packet type
            pkt_buf[buf_inc++] = clsDefines.VMU_ADDR;
            pkt_buf[buf_inc++] = clsDefines.ID_ADDR;
            pkt_buf[buf_inc++] = (byte) clsDefines.DATA_PKT;
            byte_inc = clsDefines.DATA_BYTE;

            // Save data and message type
            pkt_buf[byte_inc] = save_data;
            byte_inc = byte_inc + 1;
            pkt_buf[byte_inc] = ((byte) (0));
            byte_inc = byte_inc + 1;

            // Determine message number based on save_data flag
            if (save_data == clsDefines.INTDB_DATA_SAVE) {
                pkt_buf[byte_inc] = ((byte) (DRIVER_NO_DISPLAY_INT_PKT_NO));
            } else {
                pkt_buf[byte_inc] = ((byte) (1));
            }
            byte_inc = byte_inc + 1;

            // No scroll internal display board
            pkt_buf[byte_inc] = NO_SCROLL_INTERNAL_DISPLAY_BRD;
            byte_inc = byte_inc + 1;

            language_cnt = 0;
            type = 0;
            language_cnt = 1;
            pkt_buf[byte_inc] = language_cnt;
            byte_inc = byte_inc + 1;

            // Convert data to byte array if not empty
            if (!"".equals(data)) {
                int_bitmap = data.getBytes();
                bitmap_length = int_bitmap.length;

                // Limit bitmap length if it exceeds max length
                if (bitmap_length > MAX_BITMAP_LEN_INT_LINE1_2) {
                    bitmap_length = MAX_BITMAP_LEN_INT_LINE1_2;
                }

                // Calculate MSB and LSB values for bitmap length
                lsb_val = (byte) (bitmap_length & 0xFF);
                bitmap_len_msb = (short) (bitmap_length & 0xFF00);
                bitmap_len_msb = (short) (bitmap_len_msb >> 8);
                msb_val = (byte) (bitmap_len_msb & 0xFF); // pkt_buf(LEN_LSB);

                // Add MSB, LSB, and other details to packet buffer
                pkt_buf[byte_inc] = ((byte) (msb_val));
                byte_inc = byte_inc + 1;
                pkt_buf[byte_inc] = ((byte) (lsb_val));
                byte_inc = byte_inc + 1;
                pkt_buf[byte_inc] = ((byte) (0)); // bitmap
                byte_inc = byte_inc + 1;
                pkt_buf[byte_inc] = FONT_16_BOLD;
                byte_inc = byte_inc + 1;
                // 6th byte display position
                pkt_buf[byte_inc] = SCR_FULL;
                byte_inc = byte_inc + 1;
                //7th byte SCROLL SPEED/ DISPLAY TIME
                pkt_buf[byte_inc] = 30;
                byte_inc = byte_inc + 1;
                //8th onwards data
                i = 0;

                // Add data to packet buffer
                while (i < bitmap_length) {
                    pkt_buf[byte_inc] = (int_bitmap[i]);
                    byte_inc = byte_inc + 1;
                    i++;
                }
            } else {
                // Handle case when data is empty
                pkt_buf[byte_inc] = 0;
                byte_inc = byte_inc + 1;
                pkt_buf[byte_inc] = 0;
                byte_inc = byte_inc + 1;
            }

            pkt_buf[byte_inc] = 0;
            byte_inc = byte_inc + 1;
            pkt_buf[byte_inc] = 0;
            byte_inc = byte_inc + 1;

            pkt_buf[byte_inc] = 0;
            byte_inc = byte_inc + 1;
            pkt_buf[byte_inc] = 0;
            byte_inc = byte_inc + 1;

            pkt_buf[byte_inc] = 0;
            byte_inc = byte_inc + 1;
            pkt_buf[byte_inc] = 0;
            byte_inc = byte_inc + 1;
            pkt_length += (short) (byte_inc);
            buf = convertToByteArray((short) (byte_inc));

            // Set packet length in buffer
            pkt_buf[clsDefines.PKT_LEN_LSB_BYTE] = buf[1];
            pkt_buf[clsDefines.PKT_LEN_MSB_BYTE] = buf[0];

            pkt_length = (short) (pkt_length + 2);

            // Prepare checksum for the packet
            pkt_buf = prepare_checksum(pkt_buf, (short) pkt_length);
            int_disbrd_msg_buf = new byte[byte_inc + 2];

            // Copy final packet buffer to message buffer
            for (i = 0; i < byte_inc + 2; i++) {
                int_disbrd_msg_buf[i] = pkt_buf[i];
            }
            clsSharedVariables.int_disbrd_msg_buf_len = i;
            setIntDisbBrdMsgCame(true);

        } catch (Exception ex) {
            // Handle exceptions
        }

        // Clear buffers
        pkt_buf = null;
        buf = null;
        int_bitmap = null;
    }

    /**
     * Prepares checksum for the given packet buffer.
     *
     * @param str the packet buffer.
     * @param length the length of the packet.
     * @return the packet buffer with checksum.
     */
    private static byte[] prepare_checksum(byte[] str, short length) {
        int index = 2;
        int CRCITTSum = 0xffff;
        int ByteValue = 0;
        byte bitindex = 0;
        byte temp = 0;
        try {
            while (index < (length - 2)) {
                temp = str[index];
                ByteValue = temp;
                ByteValue <<= 8;
                bitindex = 0;

                // Calculate CRC-16-CCITT checksum
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

            // Add checksum to packet buffer
            str[length - 1] = (byte) (CRCITTSum & 0xff);
            str[length - 2] = (byte) ((CRCITTSum & 0xff00) >> 8);
        } catch (Exception ex) {
            // Handle exceptions
        }
        return str;
    }
}
