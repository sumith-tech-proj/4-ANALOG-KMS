package obuits;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class DS1307RTC {

    private I2CBus bus;
    private I2CDevice device;

    // Constructor for initializing the RTC object
    public DS1307RTC() throws IOException {
        try {
            // Initialize the I2C bus and device
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
            device = bus.getDevice(0x68);
        } catch (Exception ex) {
            // Handle any exceptions during initialization
            ex.printStackTrace(); // Consider logging the exception for debugging purposes
        }
    }

    // Method to get the current date and time from the RTC module
    public Date getDate() throws IOException {
        // Read the current date and time from the RTC module
        byte[] buffer = new byte[7];
        device.read(0x00, buffer, 0, 7);
        // Extract individual components of date and time
        int seconds = bcdToDec(buffer[0] & 0x7F);
        int minutes = bcdToDec(buffer[1] & 0x7F);
        int hours = bcdToDec(buffer[2] & 0x3F);
        int day = bcdToDec(buffer[3] & 0x07);
        int date = bcdToDec(buffer[4] & 0x3F);
        int month = bcdToDec(buffer[5] & 0x1F);
        int year = bcdToDec(buffer[6]) + 2000;
        // Create a Calendar instance and set the extracted date and time
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hours, minutes, seconds);
        return cal.getTime(); // Return the Date object representing the current date and time
    }

    // Method to set the date and time on the RTC module
    public void setDate(Date date) throws IOException {
        // Create a Calendar instance and set it to the provided date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // Convert the date and time components to byte array in BCD format
        byte[] buffer = new byte[7];
        buffer[0] = decToBcd(cal.get(Calendar.SECOND));
        buffer[1] = decToBcd(cal.get(Calendar.MINUTE));
        buffer[2] = decToBcd(cal.get(Calendar.HOUR_OF_DAY));
        buffer[3] = decToBcd(cal.get(Calendar.DAY_OF_WEEK));
        buffer[4] = decToBcd(cal.get(Calendar.DAY_OF_MONTH));
        buffer[5] = decToBcd(cal.get(Calendar.MONTH) + 1);
        buffer[6] = decToBcd(cal.get(Calendar.YEAR) - 2000);
        // Write the date and time to the RTC module
        device.write(0x00, buffer, 0, 7);
    }

    // Utility method to convert binary-coded decimal (BCD) to decimal
    private int bcdToDec(int bcd) {
        return (bcd & 0x0F) + ((bcd >> 4) * 10);
    }

    // Utility method to convert decimal to binary-coded decimal (BCD)
    private byte decToBcd(int dec) {
        return (byte) ((dec / 10 << 4) + (dec % 10));
    }
}
