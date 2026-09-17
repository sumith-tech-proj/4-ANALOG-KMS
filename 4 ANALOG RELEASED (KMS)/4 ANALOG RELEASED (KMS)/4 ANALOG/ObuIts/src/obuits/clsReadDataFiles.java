package obuits;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static obuits.clsDefines.config_filepath;
import static obuits.clsDefines.log_filepath;
import static obuits.clsDefines.main_route_path;
import static obuits.clsDefines.media_filepath;
import static obuits.clsSharedVariables.getSplAudio1;
import static obuits.clsSharedVariables.getSplAudio2;
import static obuits.clsSharedVariables.getSplAudioEnabled;
import static obuits.clsSharedVariables.getSplAudioInterval;

public class clsReadDataFiles {

    // Initialize CAN parameter values to default
    public void init_can_params_value1() {
        int i = 0;
        // Initialize electrical CAN parameters
        for (i = 0; i < PanCanConfig.objCanElectricalCnt1; i++) {
            PanCanConfig.objCanElectrical1[i].value1 = 0;
            PanCanConfig.objCanElectrical1[i].data_updated1 = false;
        }
        // Initialize safety CAN parameters
        for (i = 0; i < PanCanConfig.objCanSafetyCnt1; i++) {
            PanCanConfig.objCanSafety1[i].value1 = 0;
            PanCanConfig.objCanSafety1[i].data_updated1 = false;
        }
        // Initialize transmit CAN parameters
        for (i = 0; i < PanCanConfig.objCanTransmitCnt1; i++) {
            PanCanConfig.objCanTransmit1[i].value1 = 0;
            PanCanConfig.objCanTransmit1[i].data_updated1 = false;
        }
        // Initialize engine CAN parameters
        for (i = 0; i < PanCanConfig.objCanEngineCnt1; i++) {
            PanCanConfig.objCanEngine1[i].value1 = 0;
            PanCanConfig.objCanEngine1[i].data_updated1 = false;
        }
        // Initialize other CAN parameters
        for (i = 0; i < PanCanConfig.objCanOthersCnt1; i++) {
            PanCanConfig.objCanOthers1[i].value1 = 0;
            PanCanConfig.objCanOthers1[i].data_updated1 = false;
        }
    }

    // Read CAN electrical data from file
    public void read_canElectricals_file1() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canElectrical1.txt");
        int inc;

        i = 0;
        if (file.exists()) {
            try {
                // Set file permissions
                file.setReadable(true, false);
                file.setWritable(true, false);
                // Initialize BufferedReader to read the file
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    // Process non-empty lines
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");
                        // Skip the header line
                        if (j > 0) {
                            inc = 0;
                            if (split_str.length >= 12) {
                                // Read and parse the values
                                PanCanConfig.objCanElectrical1[i].name1 = split_str[inc++].trim();
                                PanCanConfig.objCanElectrical1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanElectrical1[i].unit1 = split_str[inc++].trim();
                                try {
                                    PanCanConfig.objCanElectrical1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle parsing exceptions for certain fields
                                }
                                try {
                                    PanCanConfig.objCanElectrical1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle parsing exceptions for threshold
                                }
                                i++;
                            }
                        } else {
                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // Handle file not found exception
            } catch (IOException e) {
                // Handle IO exception
            } catch (Exception ex) {
                // Handle other exceptions
            } finally {
                try {
                    // Update CAN electrical count
                    PanCanConfig.objCanElectricalCnt1 = (byte) i;
                    // Close BufferedReader and clean up
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;
                    }
                } catch (IOException e) {
                    // Handle IO exception during cleanup
                }
            }
            // Copy file to media filepath if it doesn't exist
            file = new File(media_filepath, "canElectrical1.txt");
            if (!file.exists() && clsSharedVariables.getHardDriveDetected()) {
                copyFile(config_filepath.getAbsolutePath() + "/" + "canElectrical1.txt", media_filepath.getAbsolutePath() + "/" + "canElectrical1.txt");
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "canElectrical1.txt");
            if (file.exists()) {
                try {
                    // Set file permissions
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    // Initialize BufferedReader to read the file
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        // Process non-empty lines
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            // Skip the header line
                            if (j > 0) {
                                inc = 0;
                                if (split_str.length >= 12) {
                                    // Read and parse the values
                                    PanCanConfig.objCanElectrical1[i].name1 = split_str[inc++].trim();
                                    PanCanConfig.objCanElectrical1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanElectrical1[i].unit1 = split_str[inc++].trim();
                                    try {
                                        PanCanConfig.objCanElectrical1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanElectrical1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle parsing exceptions for certain fields
                                    }
                                    try {
                                        PanCanConfig.objCanElectrical1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle parsing exceptions for threshold
                                    }
                                    i++;
                                }
                            } else {
                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    // Handle file not found exception
                } catch (IOException e) {
                    // Handle IO exception
                } catch (Exception ex) {
                    // Handle other exceptions
                } finally {
                    try {
                        // Update CAN electrical count
                        PanCanConfig.objCanElectricalCnt1 = (byte) i;
                        // Close BufferedReader and clean up
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }
                    } catch (IOException e) {
                        // Handle IO exception during cleanup
                    }
                }
                // Copy file to config filepath
                copyFile(media_filepath.getAbsolutePath() + "/" + "canElectrical1.txt", config_filepath.getAbsolutePath() + "/" + "canElectrical1.txt");
            }
        }
    }

    // Read CAN safety data from file
    public void read_canSafety_file1() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canSafety1.txt");

        int inc = 0;
        i = 0;
        if (file.exists()) {
            try {
                // Set file permissions
                file.setReadable(true, false);
                file.setWritable(true, false);
                // Initialize BufferedReader to read the file
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    // Process non-empty lines
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");
                        // Skip the header line
                        if (j > 0) {
                            inc = 0;
                            if (split_str.length >= 12) {
                                // Read and parse the values
                                PanCanConfig.objCanSafety1[i].name1 = split_str[inc++].trim();
                                PanCanConfig.objCanSafety1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanSafety1[i].unit1 = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanSafety1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle parsing exceptions for certain fields
                                }
                                try {
                                    PanCanConfig.objCanSafety1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle parsing exceptions for threshold
                                }
                                i++;
                            }
                        } else {
                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // Handle file not found exception
            } catch (IOException e) {
                // Handle IO exception
            } catch (Exception ex) {
                // Handle other exceptions
            } finally {

                // Update CAN safety count
                PanCanConfig.objCanSafetyCnt1 = (byte) i;
                // Close BufferedReader and clean up
                try {
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;
                    }
                } catch (IOException e) {
                    // Handle IO exception during cleanup
                }
            }
            // Copy file to media filepath if it doesn't exist
            file = new File(media_filepath, "canSafety1.txt");
            if (!file.exists() && clsSharedVariables.getHardDriveDetected()) {
                copyFile(config_filepath.getAbsolutePath() + "/" + "canSafety1.txt", media_filepath.getAbsolutePath() + "/" + "canSafety1.txt");
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "canSafety1.txt");
            inc = 0;
            i = 0;
            if (file.exists()) {
                try {
                    // Set file permissions
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                    // Initialize BufferedReader to read the file
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        // Process non-empty lines
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            // Skip the header line
                            if (j > 0) {
                                inc = 0;
                                if (split_str.length >= 12) {
                                    // Read and parse the values
                                    PanCanConfig.objCanSafety1[i].name1 = split_str[inc++].trim();
                                    PanCanConfig.objCanSafety1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanSafety1[i].unit1 = split_str[inc++].trim();
                                    try {
                                        PanCanConfig.objCanSafety1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanSafety1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle parsing exceptions for certain fields
                                    }
                                    try {
                                        PanCanConfig.objCanSafety1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle parsing exceptions for threshold
                                    }
                                    i++;
                                }
                            } else {
                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    // Handle file not found exception
                } catch (IOException e) {
                    // Handle IO exception
                } catch (Exception ex) {
                    // Handle other exceptions
                } finally {

                    // Update CAN safety count
                    PanCanConfig.objCanSafetyCnt1 = (byte) i;

                    try {
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }
                    } catch (IOException e) {
                        // Handle IO exception during cleanup
                    }
                }
                // Copy file to config filepath
                copyFile(media_filepath.getAbsolutePath() + "/" + "canSafety1.txt", config_filepath.getAbsolutePath() + "/" + "canSafety1.txt");

            }

        }
    }

    public void read_canTransmit_file1() {
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canTransmit1.txt");
        int inc = 0;

        i = 0;
        if (file.exists()) {
            try {
                // Initialize BufferedReader to read from the file
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        split_str = line.split(",");

                        if (j > 0) {
                            inc = 0;
                            if (split_str.length >= 12) {
                                // Assign values to PanCanConfig.objCanTransmit1 fields
                                PanCanConfig.objCanTransmit1[i].name1 = split_str[inc++].trim();
                                PanCanConfig.objCanTransmit1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanTransmit1[i].unit1 = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanTransmit1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle parsing exceptions if necessary
                                }
                                try {
                                    PanCanConfig.objCanTransmit1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle parsing exceptions for threshold if necessary
                                }
                                i++;
                            }
                        } else {
                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // Handle file not found exception
            } catch (IOException e) {
                // Handle IO exception
            } catch (Exception ex) {
                // Handle other exceptions
            } finally {
                try {
                    // Update CAN transmit count
                    PanCanConfig.objCanTransmitCnt1 = (byte) i;
                    // Close BufferedReader and clean up
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;
                    }
                } catch (IOException e) {
                    // Handle IO exception during cleanup
                }
            }
            // Copy file to media filepath if it doesn't exist
            if (clsSharedVariables.getHardDriveDetected()) {
                file = new File(media_filepath, "canTransmit1.txt");
                if (!file.exists()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "canTransmit1.txt", media_filepath.getAbsolutePath() + "/" + "canTransmit1.txt");
                }
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            file = new File(media_filepath, "canTransmit1.txt");
            inc = 0;
            i = 0;
            if (file.exists()) {
                try {
                    // Initialize BufferedReader to read from the file
                    br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null) {
                        if (!line.trim().equals("")) {
                            split_str = line.split(",");
                            if (j > 0) {
                                inc = 0;
                                if (split_str.length >= 12) {
                                    // Assign values to PanCanConfig.objCanTransmit1 fields
                                    PanCanConfig.objCanTransmit1[i].name1 = split_str[inc++].trim();
                                    PanCanConfig.objCanTransmit1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanTransmit1[i].unit1 = split_str[inc++].trim();

                                    try {
                                        PanCanConfig.objCanTransmit1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanTransmit1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle parsing exceptions if necessary
                                    }
                                    try {
                                        PanCanConfig.objCanTransmit1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                    }
                                    i++;
                                }
                            } else {
                                j++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                } catch (Exception ex) {
                } finally {
                    try {
                        PanCanConfig.objCanTransmitCnt1 = (byte) i;
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }
                    } catch (IOException e) {
                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canTransmit1.txt", config_filepath.getAbsolutePath() + "/" + "canTransmit1.txt");
            }
        }
    }

    /**
     * Reads data from the "canEngine1.txt" file and populates the
     * PanCanConfig.objCanEngine1 array. If the file doesn't exist in the config
     * directory but exists in the media directory, it copies it to the config
     * directory.
     */
    public void read_canEngine_file1() {
        short i = 0;                     // Initialize index for PanCanConfig.objCanEngine1 array
        short j = 0;                     // Counter for iterations
        String[] split_str;              // Array to hold split parts of each line
        String line;                     // Variable to hold each line read from the file
        BufferedReader br = null;        // Reader for file input
        int inc = 0;                     // Incremental counter for splitting line elements
        File file = new File(config_filepath, "canEngine1.txt");  // File path to read from

        i = 0;                           // Initialize index for reading
        if (file.exists()) {             // Check if the file exists in the config directory
            try {
                br = new BufferedReader(new FileReader(file));  // Open a reader for the file
                while ((line = br.readLine()) != null) {         // Read each line until EOF
                    if (!line.trim().equals("")) {              // Skip empty lines
                        split_str = line.split(",");            // Split line by comma

                        inc = 0;                               // Reset increment counter
                        if (j > 0) {                           // Skip header line
                            if (split_str.length >= 12) {       // Ensure enough elements in split array

                                // Assign values from split_str to PanCanConfig.objCanEngine1 array elements
                                PanCanConfig.objCanEngine1[i].name1 = split_str[inc++].trim();
                                PanCanConfig.objCanEngine1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanEngine1[i].unit1 = split_str[inc++].trim();

                                try {
                                    PanCanConfig.objCanEngine1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle any exceptions that may occur while parsing or assigning values
                                    // (Currently empty)
                                }
                                try {
                                    PanCanConfig.objCanEngine1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle any exceptions that may occur while parsing or assigning values
                                    // (Currently empty)
                                }

                                i++;  // Increment index for PanCanConfig.objCanEngine1 array
                            }
                        } else {
                            j++;  // Increment counter to skip header line
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // Handle file not found exception (Currently empty)
            } catch (IOException e) {
                // Handle IO exception (Currently empty)
            } catch (Exception ex) {
                // Handle any other exceptions that may occur (Currently empty)
            } finally {
                try {
                    PanCanConfig.objCanEngineCnt1 = (byte) i;  // Update count of valid entries in PanCanConfig.objCanEngine1

                    if (br != null) {
                        br.close();    // Close BufferedReader
                        file = null;   // Release file reference
                        br = null;     // Release BufferedReader reference
                    }

                } catch (IOException e) {
                    // Handle IO exception during close operation (Currently empty)
                }
            }

            // Check if hard drive is detected and copy file if needed
            if (clsSharedVariables.getHardDriveDetected()) {
                file = new File(media_filepath, "canEngine1.txt");  // Check media directory for the file
                if (!file.exists()) {
                    // Copy file from config directory to media directory
                    copyFile(config_filepath.getAbsolutePath() + "/" + "canEngine1.txt", media_filepath.getAbsolutePath() + "/" + "canEngine1.txt");
                }
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            // If file does not exist in config directory but hard drive is detected
            file = new File(media_filepath, "canEngine1.txt");  // Check media directory for the file

            i = 0;  // Reset index counter
            if (file.exists()) {
                try {
                    br = new BufferedReader(new FileReader(file));  // Open reader for file in media directory
                    while ((line = br.readLine()) != null) {         // Read each line until EOF
                        if (!line.trim().equals("")) {              // Skip empty lines
                            split_str = line.split(",");            // Split line by comma
                            inc = 0;                               // Reset increment counter

                            if (j > 0) {                           // Skip header line
                                if (split_str.length >= 12) {       // Ensure enough elements in split array

                                    // Assign values from split_str to PanCanConfig.objCanEngine1 array elements
                                    PanCanConfig.objCanEngine1[i].name1 = split_str[inc++].trim();
                                    PanCanConfig.objCanEngine1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanEngine1[i].unit1 = split_str[inc++].trim();

                                    try {
                                        PanCanConfig.objCanEngine1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                        PanCanConfig.objCanEngine1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle any exceptions that may occur while parsing or assigning values
                                        // (Currently empty)
                                    }
                                    try {
                                        PanCanConfig.objCanEngine1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                    } catch (Exception ex) {
                                        // Handle any exceptions that may occur while parsing or assigning values
                                        // (Currently empty)
                                    }

                                    i++;  // Increment index for PanCanConfig.objCanEngine1 array
                                }
                            } else {
                                j++;  // Increment counter to skip header line
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                } catch (Exception ex) {
                } finally {
                    try {
                        PanCanConfig.objCanEngineCnt1 = (byte) i;
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }
                    } catch (IOException e) {
                    }
                }
                copyFile(media_filepath.getAbsolutePath() + "/" + "canEngine1.txt", config_filepath.getAbsolutePath() + "/" + "canEngine1.txt");
            }
        }
    }

    public void read_canOthers_file1() {
        // Initialize variables
        short i = 0;
        short j = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        File file = new File(config_filepath, "canOthers1.txt");
        int inc = 0;

        // Reset counter
        i = 0;

        // Check if the file exists
        if (file.exists()) {
            try {
                // Open the file for reading
                br = new BufferedReader(new FileReader(file));

                // Read each line from the file
                while ((line = br.readLine()) != null) {
                    // Check if the line is not empty
                    if (!line.trim().equals("")) {
                        // Split the line by commas
                        split_str = line.split(",");
                        inc = 0;

                        // Check if we're past the first line
                        if (j > 0) {
                            // Ensure the line has at least 12 fields
                            if (split_str.length >= 12) {
                                // Assign values to object properties
                                PanCanConfig.objCanOthers1[i].name1 = split_str[inc++].trim();
                                PanCanConfig.objCanOthers1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].unit1 = split_str[inc++].trim();

                                // Handle exceptions for parsing
                                try {
                                    PanCanConfig.objCanOthers1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle exception
                                }

                                // Handle threshold exception
                                try {
                                    PanCanConfig.objCanOthers1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle exception
                                }

                                // Increment index for next object
                                i++;
                            }
                        } else {
                            // Increment j to skip the first line
                            j++;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // Handle file not found exception
            } catch (IOException e) {
                // Handle IO exception
            } catch (Exception ex) {
                // Handle other exceptions
            } finally {
                // Set count of objects read
                PanCanConfig.objCanOthersCnt1 = (byte) i;

                try {
                    // Close the buffered reader if it's not null
                    if (br != null) {
                        br.close();
                        file = null;
                        br = null;
                    }
                } catch (IOException e) {
                    // Handle IO exception
                }
            }

            // Check if hard drive is detected
            if (clsSharedVariables.getHardDriveDetected()) {
                // Set file path to media directory
                file = new File(media_filepath, "canOthers1.txt");

                // Check if file doesn't exist and copy it
                if (!file.exists()) {
                    copyFile(config_filepath.getAbsolutePath() + "/" + "canOthers1.txt", media_filepath.getAbsolutePath() + "/" + "canOthers1.txt");
                }
            }
        } else if (clsSharedVariables.getHardDriveDetected()) {
            // Set file path to media directory
            file = new File(media_filepath, "canOthers1.txt");

            // Reset variables
            inc = 0;
            i = 0;

            // Check if file exists in media directory
            if (file.exists()) {
                try {
                    // Open the file for reading
                    br = new BufferedReader(new FileReader(file));

                    // Read each line from the file
                    while ((line = br.readLine()) != null) {
                        // Check if the line is not empty
                        if (!line.trim().equals("")) {
                            // Split the line by commas
                            split_str = line.split(",");
                            inc = 0;

                            // Ensure the line has at least 12 fields
                            if (split_str.length >= 12) {
                                // Assign values to object properties
                                PanCanConfig.objCanOthers1[i].name1 = split_str[inc++].trim();
                                PanCanConfig.objCanOthers1[i].source_address1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].log_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].display_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].transmit_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].alert_enable1 = Boolean.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].alarm_id1 = Short.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].min1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].max1 = Double.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].pgn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].spn1 = Integer.valueOf(split_str[inc++].trim());
                                PanCanConfig.objCanOthers1[i].unit1 = split_str[inc++].trim();

                                // Handle exceptions for parsing
                                try {
                                    PanCanConfig.objCanOthers1[i].can_id1 = Long.decode(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].factor1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].offset1 = Double.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].byte_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].byte_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].bit_pos1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].bit_length1 = Byte.valueOf(split_str[inc++].trim());
                                    PanCanConfig.objCanOthers1[i].reverse1 = Boolean.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle exception
                                }

                                // Handle threshold exception
                                try {
                                    PanCanConfig.objCanOthers1[i].threshold1 = Double.valueOf(split_str[inc++].trim());
                                } catch (Exception ex) {
                                    // Handle exception
                                }

                                // Increment index for next object
                                i++;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    // Handle file not found exception
                } catch (IOException e) {
                    // Handle IO exception
                } catch (Exception ex) {
                    // Handle other exceptions
                } finally {
                    // Set count of objects read
                    PanCanConfig.objCanOthersCnt1 = (byte) i;

                    try {
                        // Close the buffered reader if it's not null
                        if (br != null) {
                            br.close();
                            file = null;
                            br = null;
                        }
                    } catch (IOException e) {
                        // Handle IO exception
                    }
                }

                // Copy file from media to config directory
                copyFile(media_filepath.getAbsolutePath() + "/" + "canOthers1.txt", config_filepath.getAbsolutePath() + "/" + "canOthers1.txt");
            }
        }
    }

    /**
     * Utility method to copy a file from inputPath to outputPath.
     *
     * @param inputPath The path of the input file to copy.
     * @param outputPath The path of the output file where to copy.
     * @return True if the file was copied successfully, false otherwise.
     */
    private boolean copyFile(String inputPath, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        byte[] buffer = new byte[1024];
        int read;
        try {
            // Create output directory if it doesn't exist
            File outDir = new File(outputPath);
            File inDir = new File(inputPath);

            // Check if inputPath is a directory or a file
            if (inDir.isDirectory()) {
                // Handle directory case if needed
            } else if (inDir.isFile()) {
                try {
                    // Open input stream from input file
                    in = new FileInputStream(inDir);

                    // Create output file if it doesn't exist
                    if (!outDir.exists()) {
                        outDir.createNewFile();
                    }

                    // Open output stream to output file, false for overwrite
                    out = new FileOutputStream(outDir, false);

                    // Read from input stream and write to output stream
                    while ((read = in.read(buffer)) != -1) {
                        out.write(buffer, 0, read);
                    }
                } catch (Exception ex) {
                    // Handle exceptions if any
                } finally {
                    // Flush and close output stream, close input stream
                    out.flush();
                    out.close();
                    in.close();

                    // Release resources
                    in = null;
                    out = null;
                    outDir = null;
                    inDir = null;
                }

                // File copied successfully
                return true;
            }
        } catch (Exception e) {
            // Handle exceptions if any
        } finally {
            // Clean up buffer
            buffer = null;
        }

        // File copy failed
        return false;
    }

    public boolean write_spel_audio_data_file() {

        String data;
        File file = new File(config_filepath, "SpelAudio.txt");
        FileOutputStream f = null;
        PrintWriter pw = null;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println("Audio enable");
            data = "Audio :" + getSplAudioEnabled();
            pw.println(data);
            data = "TimeInterval :" + getSplAudioInterval();
            pw.println(data);
            data = "SpelAudio1 :" + getSplAudio1();
            pw.println(data);
            data = "SpelAudio2 :" + getSplAudio2();
            pw.println(data);
            data = "SpelAudio3 :" + clsSharedVariables.getSplAudio3();
            pw.println(data);
            data = "SpelAudio4 :" + clsSharedVariables.getSplAudio4();
            pw.println(data);
            data = "SpelAudio5 :" + clsSharedVariables.getSplAudio5();
            pw.println(data);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {

            return false;
        } catch (IOException e) {

            return false;
        } catch (Exception ex) {

            return false;
        } finally {
            runCmd("sudo sync");
            pw = null;
            f = null;
            data = null;
            file = null;
        }

        return true;
    }

    public void read_spel_audio_data_file() {
        short i = 0;
        String[] split_str;
        String line;
        BufferedReader br = null;
        //Get the text file
        File file = new File(config_filepath, "SpelAudio.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));

                while ((line = br.readLine()) != null) {
                    try {
                        switch (i) {
                            case 0:
                                // skip AUDIO CONFIGURATION
                                break;
                            case 1:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudioEnabled(Boolean.parseBoolean(split_str[1].trim()));
                                } else {
                                    clsSharedVariables.setSplAudioEnabled(false);
                                }

                                break;
                            case 2:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudioInterval(Integer.parseInt(split_str[1].trim()));
                                }
                                break;
                            case 3:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudio1(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 4:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudio2(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 5:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudio3(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 6:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudio4(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                            case 7:
                                split_str = line.split(":");
                                if (split_str.length > 1) {
                                    clsSharedVariables.setSplAudio5(Boolean.parseBoolean(split_str[1].trim()));
                                }
                                break;
                        }
                    } catch (Exception ex) {

                    }
                    i++;
                }

            } catch (Exception e) {

            } finally {

                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                }

                split_str = null;
                file = null;
                br = null;
            }
        }
    }

    public void read_xdotool_path() {
        try {
            String filePath = "/usr/bin/xdotool";

            File file = new File(filePath);
            if (file.exists()) {
                clsDefines.xdotool = true;
            } else {
                clsDefines.xdotool = false;
            }
        } catch (Exception ex) {

        }
    }

    public void create_shell_file_gstreamer_close_script() {

        String fileName = clsDefines.GSTREAMER_KILL_SCRIPT;

        String content = "#!/bin/bash\n\n";
        content += "retry_limit=5\n";
        content += "counter=0\n\n";
        content += "while [ $counter -lt $retry_limit ]; do\n";
        content += "    ffplay_ids=$(xdotool search --onlyvisible --class \"ffplay\")\n";
        content += "    gstreamer_ids=$(xdotool search --onlyvisible --class \"gstreamer\")\n\n";
        content += "    if [ -n \"$ffplay_ids\" ] || [ -n \"$gstreamer_ids\" ]; then\n";
        content += "        for id in $ffplay_ids; do\n";
        content += "            xdotool windowkill $id\n";
        content += "        done\n\n";
        content += "        for id in $gstreamer_ids; do\n";
        content += "            xdotool windowkill $id\n";
        content += "        done\n";
        content += "    else\n";
        content += "        exit 0\n";
        content += "    fi\n\n";
        content += "    # Wait for a few seconds before checking again\n";
        content += "    sleep 1\n\n";
        content += "    counter=$((counter + 1))\n";
        content += "done\n";

        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.write(filePath, content.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create_shell_file_gstreamer_cam1_script() {

        String fileName = clsDefines.GSTREAMER_CAM1_SCRIPT;

        String content = "#!/bin/bash\n";
        content += "GST_URL=\"rtsp://admin:sumith123@10.42.0.3/Streaming/channels/102\"\n";
        content += "GST_X=\"80\"\n";
        content += "GST_Y=\"100\"\n";
        content += "GST_WIDTH=\"720\"\n";
        content += "GST_HEIGHT=\"530\"\n\n";
        content += "if [ $# -ge 1 ]; then\n";
        content += "    GST_URL=$1\n";
        content += "    echo \"url   $GST_URL    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"url   $GST_URL    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 2 ]; then\n";
        content += "    GST_X=$2\n";
        content += "    echo \"X       $GST_X    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"X       $GST_X    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 3 ]; then\n";
        content += "    GST_Y=$3\n";
        content += "    echo \"Y       $GST_Y    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"Y       $GST_Y    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 4 ]; then\n";
        content += "    GST_WIDTH=$4\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 5 ]; then\n";
        content += "    GST_HEIGHT=$5\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (default)\"\n";
        content += "fi\n\n";
        content += "gst-launch-1.0 playbin uri=\"$GST_URL\" latency=100 video-sink=\"videoconvert ! videoscale ! video/x-raw,width=$GST_WIDTH,height=$GST_HEIGHT ! autovideosink\" &\n\n";
        content += "sleep 2\n\n";
        content += "retry_limit=5\n";
        content += "counter=0\n\n";
        content += "while [ $counter -lt $retry_limit ]; do\n";
        content += "    window_ids=$(xdotool search --onlyvisible --name \"gst-launch-1.0\")\n\n";
        content += "    if [ -n \"$window_ids\" ]; then\n";
        content += "        break\n";
        content += "    fi\n\n";
        content += "    sleep 1\n\n";
        content += "    counter=$((counter + 1))\n";
        content += "done\n\n";
        content += "window_id=$(printf '%s' \"$window_ids\" | tail -n 1)\n\n";
        content += "xdotool windowsize $window_id $GST_WIDTH $GST_HEIGHT\n\n";
        content += "xdotool windowmove $window_id $GST_X $GST_Y\n\n";
        content += "window_idabove=$(wmctrl -l -p | grep \"gst-launch-1.0\" | awk '{print $1}')\n\n";
        content += "wmctrl -i -r \"$window_idabove\" -b add,above\n";

        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.write(filePath, content.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void create_shell_file_gstreamer_cam2_script() {
        String fileName = clsDefines.GSTREAMER_CAM2_SCRIPT;

        String content = "#!/bin/bash\n";
        content += "GST_URL=\"rtsp://admin:sumith123@10.42.0.4/Streaming/channels/102\"\n";
        content += "GST_X=\"80\"\n";
        content += "GST_Y=\"100\"\n";
        content += "GST_WIDTH=\"720\"\n";
        content += "GST_HEIGHT=\"530\"\n\n";
        content += "if [ $# -ge 1 ]; then\n";
        content += "    GST_URL=$1\n";
        content += "    echo \"url   $GST_URL    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"url   $GST_URL    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 2 ]; then\n";
        content += "    GST_X=$2\n";
        content += "    echo \"X       $GST_X    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"X       $GST_X    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 3 ]; then\n";
        content += "    GST_Y=$3\n";
        content += "    echo \"Y       $GST_Y    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"Y       $GST_Y    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 4 ]; then\n";
        content += "    GST_WIDTH=$4\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 5 ]; then\n";
        content += "    GST_HEIGHT=$5\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (default)\"\n";
        content += "fi\n\n";
        content += "gst-launch-1.0 playbin uri=\"$GST_URL\" latency=100 video-sink=\"videoconvert ! videoscale ! video/x-raw,width=$GST_WIDTH,height=$GST_HEIGHT ! autovideosink\" &\n\n";
        content += "sleep 2\n\n";
        content += "retry_limit=5\n";
        content += "counter=0\n\n";
        content += "while [ $counter -lt $retry_limit ]; do\n";
        content += "    window_ids=$(xdotool search --onlyvisible --name \"gst-launch-1.0\")\n\n";
        content += "    if [ -n \"$window_ids\" ]; then\n";
        content += "        echo \"Window found!\"\n";
        content += "        break\n";
        content += "    fi\n\n";
        content += "    sleep 1\n\n";
        content += "    counter=$((counter + 1))\n";
        content += "done\n\n";
        content += "echo \"window ids:  $window_ids \"\n\n";
        content += "window_id=$(printf '%s' \"$window_ids\" | tail -n 1)\n\n";
        content += "xdotool windowsize $window_id $GST_WIDTH $GST_HEIGHT\n\n";
        content += "xdotool windowmove $window_id $GST_X $GST_Y\n\n";
        content += "window_idabove=$(wmctrl -l -p | grep \"gst-launch-1.0\" | awk '{print $1}')\n\n";
        content += "wmctrl -i -r \"$window_idabove\" -b add,above\n";

        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.write(filePath, content.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create_shell_file_gstreamer_cam3_script() {
        String fileName = clsDefines.GSTREAMER_CAM3_SCRIPT;
        String content = "#!/bin/bash\n";
        content += "GST_URL=\"rtsp://admin:sumith123@10.42.0.5/Streaming/channels/102\"\n";
        content += "GST_X=\"80\"\n";
        content += "GST_Y=\"100\"\n";
        content += "GST_WIDTH=\"720\"\n";
        content += "GST_HEIGHT=\"530\"\n\n";
        content += "if [ $# -ge 1 ]; then\n";
        content += "    GST_URL=$1\n";
        content += "    echo \"url   $GST_URL    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"url   $GST_URL    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 2 ]; then\n";
        content += "    GST_X=$2\n";
        content += "    echo \"X       $GST_X    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"X       $GST_X    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 3 ]; then\n";
        content += "    GST_Y=$3\n";
        content += "    echo \"Y       $GST_Y    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"Y       $GST_Y    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 4 ]; then\n";
        content += "    GST_WIDTH=$4\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 5 ]; then\n";
        content += "    GST_HEIGHT=$5\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (default)\"\n";
        content += "fi\n\n";
        content += "gst-launch-1.0 playbin uri=\"$GST_URL\" latency=100 video-sink=\"videoconvert ! videoscale ! video/x-raw,width=$GST_WIDTH,height=$GST_HEIGHT ! autovideosink\" &\n\n";
        content += "sleep 2\n\n";
        content += "retry_limit=5\n";
        content += "counter=0\n\n";
        content += "while [ $counter -lt $retry_limit ]; do\n";
        content += "    window_ids=$(xdotool search --onlyvisible --name \"gst-launch-1.0\")\n\n";
        content += "    if [ -n \"$window_ids\" ]; then\n";
        content += "        echo \"Window found!\"\n";
        content += "        break\n";
        content += "    fi\n\n";
        content += "    sleep 1\n\n";
        content += "    counter=$((counter + 1))\n";
        content += "done\n\n";
        content += "window_id=$(printf '%s' \"$window_ids\" | tail -n 1)\n\n";
        content += "xdotool windowsize $window_id $GST_WIDTH $GST_HEIGHT\n\n";
        content += "xdotool windowmove $window_id $GST_X $GST_Y\n\n";
        content += "window_idabove=$(wmctrl -l -p | grep \"gst-launch-1.0\" | awk '{print $1}')\n\n";
        content += "wmctrl -i -r \"$window_idabove\" -b add,above\n";

        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.write(filePath, content.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create_shell_file_gstreamer_cam4_script() {
        String fileName = clsDefines.GSTREAMER_CAM4_SCRIPT;

        String content = "#!/bin/bash\n";
        content += "GST_URL=\"rtsp://admin:sumith123@10.42.0.6/Streaming/channels/102\"\n";
        content += "GST_X=\"80\"\n";
        content += "GST_Y=\"100\"\n";
        content += "GST_WIDTH=\"720\"\n";
        content += "GST_HEIGHT=\"530\"\n\n";
        content += "if [ $# -ge 1 ]; then\n";
        content += "    GST_URL=$1\n";
        content += "    echo \"url   $GST_URL    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"url   $GST_URL    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 2 ]; then\n";
        content += "    GST_X=$2\n";
        content += "    echo \"X       $GST_X    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"X       $GST_X    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 3 ]; then\n";
        content += "    GST_Y=$3\n";
        content += "    echo \"Y       $GST_Y    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"Y       $GST_Y    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 4 ]; then\n";
        content += "    GST_WIDTH=$4\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"WIDTH    $GST_WIDTH    # (default)\"\n";
        content += "fi\n\n";
        content += "if [ $# -ge 5 ]; then\n";
        content += "    GST_HEIGHT=$5\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (from command line)\"\n";
        content += "else\n";
        content += "    echo \"HEIGHT    $GST_HEIGHT    # (default)\"\n";
        content += "fi\n\n";
        content += "gst-launch-1.0 playbin uri=\"$GST_URL\" latency=100 video-sink=\"videoconvert ! videoscale ! video/x-raw,width=$GST_WIDTH,height=$GST_HEIGHT ! autovideosink\" &\n\n";
        content += "sleep 2\n\n";
        content += "retry_limit=5\n";
        content += "counter=0\n\n";
        content += "while [ $counter -lt $retry_limit ]; do\n";
        content += "    window_ids=$(xdotool search --onlyvisible --name \"gst-launch-1.0\")\n\n";
        content += "    if [ -n \"$window_ids\" ]; then\n";
        content += "        break\n";
        content += "    fi\n\n";
        content += "    sleep 1\n\n";
        content += "    counter=$((counter + 1))\n";
        content += "done\n\n";
        content += "window_id=$(printf '%s' \"$window_ids\" | tail -n 1)\n\n";
        content += "xdotool windowsize $window_id $GST_WIDTH $GST_HEIGHT\n\n";
        content += "xdotool windowmove $window_id $GST_X $GST_Y\n\n";
        content += "window_idabove=$(wmctrl -l -p | grep \"gst-launch-1.0\" | awk '{print $1}')\n\n";
        content += "wmctrl -i -r \"$window_idabove\" -b add,above\n";

        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.write(filePath, content.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void read_omxplayer_filepath() {
        if (clsDefines.omxplayer_filepath.exists()) {
            clsDefines.omxplayer_found = true;
        } else {
            clsDefines.omxplayer_found = false;
        }

    }

    public synchronized void read_forrir_rtsp_filepath() {
        if (clsDefines.rtsp_filepath.exists()) {
            clsDefines.rtsp_found = true;
        } else {
            clsDefines.rtsp_found = false;
        }

    }

    public synchronized void read_mpv_filepath() {
        if (clsDefines.mpv_filepath.exists()) {
            clsDefines.mpv_found = true;
        } else {
            clsDefines.mpv_found = false;
        }

    }

    //apc second camera files
    public synchronized void write_apc_route_data(String str) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(log_filepath, "front_apc_route_data.txt");

        try {
            if (file.length() > 500000) {
                delete_lines_file_apc_data("front_apc_route_data.txt", 20);
            }

            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_apc_route_data() {
        BufferedReader br = null;
        String line;

        int cnt;
        String[] data = null;
        int i = 0;

        File file = new File(log_filepath, "front_apc_route_data.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                cnt = (int) (br.lines().count());
                data = new String[cnt];
                br.close();
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    data[i++] = (line);
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }
        return data;
    }

    public synchronized void write_apc_route_data1(String str) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(log_filepath, "back_apc_route_data.txt");

        try {
            if (file.length() > 50000) {
                delete_lines_file_apc_data1("back_apc_route_data.txt", 20);
            }
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, true);
            pw = new PrintWriter(f);
            pw.println(str);
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_apc_route_data1() {
        BufferedReader br = null;
        String line;

        int cnt;
        String[] data = null;
        int i = 0;

        File file = new File(log_filepath, "back_apc_route_data.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                cnt = (int) (br.lines().count());
                data = new String[cnt];
                br.close();
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    data[i++] = (line);
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }
        return data;
    }

    public synchronized void write_apc_hour_stored_data(long startdate, String date, int hour, int people_in, int people_out, boolean append) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        String file_path = log_filepath + "/front_apc_hour_data.txt";
        File file = new File(log_filepath, "front_apc_hour_data.txt");
        String[] data = null;
        BufferedReader br = null;
        int i = 0;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (append == true) {
                f = new FileOutputStream(file, true);
                pw = new PrintWriter(f);
                pw.println(startdate + "," + date + "," + hour + "," + people_in + "," + people_out);
                pw.flush();
                pw.close();
                f.close();
            } else {

                List<String> listOfStrings = new ArrayList<String>();
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
                // br = new BufferedReader(new FileReader(file));
                // data = (String[]) br.lines().toArray();
                if (data.length > 1) {
                    data[data.length - 1] = startdate + "," + date + "," + hour + "," + people_in + "," + people_out;
                } else {
                    data[0] = startdate + "," + date + "," + hour + "," + people_in + "," + people_out;
                }
                //  br.close();
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                if (data.length > clsDefines.NO_DAYS_DELETE * 24) {  //60
                    i = 120;
                } else {
                    i = 0;
                }
                for (; i < data.length; i++) {
                    pw.println(data[i]);
                }
                pw.flush();
                pw.close();
                f.close();
            }
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_apc_hour_stored_data() {
        BufferedReader br = null;
        String[] data = null;
        String file_path = log_filepath + "/front_apc_hour_data.txt";
        File file = new File(log_filepath, "front_apc_hour_data.txt");
        if (file.exists()) {
            try {
                List<String> listOfStrings;
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
                Logger.getLogger(clsReadFiles.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                file = null;
            }
        }
        return data;
    }

    public synchronized void write_apc_hour_stored_data1(long startdate, String date, int hour, int people_in, int people_out, boolean append) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        String file_path = log_filepath + "/back_apc_hour_data.txt";
        File file = new File(log_filepath, "back_apc_hour_data.txt");
        String[] data = null;
        BufferedReader br = null;
        int i = 0;
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (append == true) {
                f = new FileOutputStream(file, true);
                pw = new PrintWriter(f);
                pw.println(startdate + "," + date + "," + hour + "," + people_in + "," + people_out);
                pw.flush();
                pw.close();
                f.close();
            } else {
                List<String> listOfStrings = new ArrayList<String>();
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
                if (data.length > 1) {
                    data[data.length - 1] = startdate + "," + date + "," + hour + "," + people_in + "," + people_out;
                } else {
                    data[0] = startdate + "," + date + "," + hour + "," + people_in + "," + people_out;
                }
                //  br.close();
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                if (data.length > clsDefines.NO_DAYS_DELETE * 24) {  //60
                    i = 120;
                } else {
                    i = 0;
                }
                for (; i < data.length; i++) {
                    pw.println(data[i]);
                }
                pw.flush();
                pw.close();
                f.close();
            }
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_apc_hour_stored_data1() {
        BufferedReader br = null;
        String[] data = null;
        String file_path = log_filepath + "/back_apc_hour_data.txt";
        File file = new File(log_filepath, "back_apc_hour_data.txt");
        if (file.exists()) {
            try {
                List<String> listOfStrings;
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {
                Logger.getLogger(clsReadFiles.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {

                file = null;

            }
        }
        return data;

    }

    public synchronized void write_apc_day_stored_data(long startdate, String date, int day, int people_in, int people_out, boolean append) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        String[] data = null;
        int i = 0;
        String file_path = log_filepath + "/front_apc_day_data.txt";
        File file = new File(log_filepath, "front_apc_day_data.txt");
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (append == true) {
                f = new FileOutputStream(file, true);
                pw = new PrintWriter(f);
                pw.println(startdate + "," + date + "," + day + "," + people_in + "," + people_out);
                pw.flush();
                pw.close();
                f.close();
            } else {
                List<String> listOfStrings = new ArrayList<String>();
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
                //   br = new BufferedReader(new FileReader(file));
                // data = (String[]) br.lines().toArray();
                if (data.length > 1) {
                    data[data.length - 1] = startdate + "," + date + "," + day + "," + people_in + "," + people_out;
                } else {
                    data[0] = startdate + "," + date + "," + day + "," + people_in + "," + people_out;
                }
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                if (data.length > clsDefines.NO_DAYS_DELETE) {  //60
                    i = 5;
                } else {
                    i = 0;
                }
                for (; i < data.length; i++) {
                    pw.println(data[i]);
                }
                pw.flush();
                pw.close();
                f.close();
            }
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_apc_day_stored_data() {

        String[] data = null;
        String file_path = log_filepath + "/front_apc_day_data.txt";
        File file = new File(log_filepath, "front_apc_day_data.txt");
        if (file.exists()) {
            try {
                List<String> listOfStrings;
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
                return data;
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
                Logger.getLogger(clsReadFiles.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                file = null;
            }
        }
        return data;

    }

    public synchronized void write_apc_day_stored_data1(long startdate, String date, int day, int people_in, int people_out, boolean append) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        String[] data = null;
        int i = 0;
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        String file_path = log_filepath + "/back_apc_day_data.txt";
        File file = new File(log_filepath, "back_apc_day_data.txt");
        // System.out.println(startdate + "," + day + "," + people_in + "," + people_out);
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            if (append == true) {
                f = new FileOutputStream(file, true);
                pw = new PrintWriter(f);
                pw.println(startdate + "," + date + "," + day + "," + people_in + "," + people_out);
                pw.flush();
                pw.close();
                f.close();
            } else {
                //read all lines and update last line
                
                List<String> listOfStrings = new ArrayList<String>();
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));
                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
                //   br = new BufferedReader(new FileReader(file));
                // data = (String[]) br.lines().toArray();
                if (data.length > 1) {
                    data[data.length - 1] = startdate + "," + date + "," + day + "," + people_in + "," + people_out;
                } else {
                    data[0] = startdate + "," + date + "," + day + "," + people_in + "," + people_out;
                }
                f = new FileOutputStream(file, false);
                pw = new PrintWriter(f);
                if (data.length > clsDefines.NO_DAYS_DELETE) {  //60
                    i = 5;
                } else {
                    i = 0;
                }
                for (; i < data.length; i++) {
                    pw.println(data[i]);
                }
                pw.flush();
                pw.close();
                f.close();
            }
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized String[] read_apc_day_stored_data1() {
        String[] data = null;
        String file_path = log_filepath + "/back_apc_day_data.txt";
        File file = new File(log_filepath, "back_apc_day_data.txt");
        if (file.exists()) {
            try {
                List<String> listOfStrings;
                // load the data from file
                listOfStrings = Files.readAllLines(Paths.get(file_path));

                // convert arraylist to array
                data = listOfStrings.toArray(new String[0]);
                return data;
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
                Logger.getLogger(clsReadFiles.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {

                file = null;

            }
        }
        return data;
    }

public synchronized void read_apc_stored_data() {
    BufferedReader br = null;
    String line;
    String[] split_str = null;
    File file = new File(log_filepath, "apc_stored_data.txt");
    if (file.exists()) {
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                split_str = line.split(",", -1);

                // Minimum: startTime, routeNo, stopName, status
                if (split_str.length >= 3) {
                    clsSharedVariables.setApcStartTime(split_str[0]);
                    clsSharedVariables.setApcRouteNo(split_str[1]);
                    clsSharedVariables.setApcStopName(split_str[2]);
                   // clsSharedVariables.setApcStatus(Byte.parseByte(split_str[3]));
                }

                
                break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (br != null) br.close(); } catch (IOException ex) {}
        }
    }
}

public synchronized void write_apc_stored_data(String str) {
    FileOutputStream f = null;
    PrintWriter pw = null;
    File file = new File(log_filepath, "apc_stored_data.txt");
    try {
        file.setReadable(true, false);
        file.setWritable(true, false);
        f = new FileOutputStream(file, false); // overwrite old contents
        pw = new PrintWriter(f);
        pw.println(str);
        pw.flush();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try { if (pw != null) pw.close(); } catch (Exception e) {}
        try { if (f != null) f.close(); } catch (Exception e) {}
    }
}

public synchronized void clear_apc_stored_data() {
    write_apc_stored_data(""); // overwrite with empty content
}

    public synchronized void write_apc_present_stored_data1(int people_in, int people_out) {
        FileOutputStream f = null;
        PrintWriter pw = null;

        File file = new File(main_route_path, "filePeopleCntPresent1.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(people_in + "," + people_out);
            pw.flush();
            pw.close();
            f.close();

            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized void read_apc_present_stored_data1() {

        BufferedReader br = null;
        String line;
        String[] split_str = null;
        File file = new File(main_route_path, "filePeopleCntPresent1.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    clsSharedVariables.setApcPeopleIn1(Integer.parseInt(split_str[0]));
                    clsSharedVariables.setApcPeopleOut1(Integer.parseInt(split_str[1]));

                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }

    }

    public synchronized void write_apc_present_stored_data(int people_in, int people_out) {
        FileOutputStream f = null;
        PrintWriter pw = null;
        File file = new File(main_route_path, "filePeopleCntPresent.txt");
        try {
            file.setReadable(true, false);
            file.setWritable(true, false);
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(people_in + "," + people_out);
            pw.flush();
            pw.close();
            f.close();
            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    public synchronized void read_apc_present_stored_data() {

        BufferedReader br = null;
        String line;
        String[] split_str = null;
        File file = new File(main_route_path, "filePeopleCntPresent.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    clsSharedVariables.setApcPeopleIn(Integer.parseInt(split_str[0]));
                    clsSharedVariables.setApcPeopleOut(Integer.parseInt(split_str[1]));
                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }
    }

    public void delete_lines_file_apc(String filename, int no_lines) {
        try {
            File targetFile = new File(main_route_path, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));
                File tempFile = new File(main_route_path, "file_apc_stored_data.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {
                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                targetBuf.close();
                printTemp.close();
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_apc_data(String filename, int no_lines) {
        try {
            File targetFile = new File(log_filepath, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));
                File tempFile = new File(log_filepath, "front_apc_route_data_temp.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {
                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                targetBuf.close();
                printTemp.close();
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public void delete_lines_file_apc_data1(String filename, int no_lines) {
        try {
            File targetFile = new File(log_filepath, filename);
            if (targetFile.exists()) {
                BufferedReader targetBuf = new BufferedReader(new FileReader(targetFile));
                File tempFile = new File(log_filepath, "back_apc_route_data_temp.txt");
                PrintWriter printTemp = new PrintWriter(tempFile);
                try {
                    for (int i = 1; i <= no_lines; i++) {
                        targetBuf.readLine();
                    }
                } catch (Exception ex) {
                }
                String notfau;
                while ((notfau = targetBuf.readLine()) != null) {
                    printTemp.println(notfau);
                }
                targetBuf.close();
                printTemp.close();
                targetFile.delete();
                tempFile.renameTo(targetFile);
                targetFile = null;
                tempFile = null;
                printTemp = null;
                notfau = null;
                targetBuf = null;
            }
        } catch (Exception e) {

        }
    }

    public synchronized void read_both_camera_stored_data() {

        BufferedReader br = null;
        String line;
        String[] split_str = null;
        File file = new File(log_filepath, "filePeopleCounting.txt");
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    split_str = line.split(",", -1);
                    clsSharedVariables.setApcPeopleCountIn(Integer.parseInt(split_str[0]));
                    clsSharedVariables.setApcPeopleCountOut(Integer.parseInt(split_str[1]));

                    break;
                }
            } catch (Exception ex) {

            } finally {
                try {
                    br.close();
                    br = null;
                    file = null;
                    line = null;
                } catch (IOException ex) {

                }
            }
        }

    }

    public synchronized void write_both_camera_stored_data(int people_in, int people_out) {
        FileOutputStream f = null;
        PrintWriter pw = null;

        File file = new File(log_filepath, "filePeopleCounting.txt");

        try {
            file.setReadable(true, false);
            file.setWritable(true, false);

            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(people_in + "," + people_out);
            pw.flush();
            pw.close();
            f.close();

            runCmd("sudo sync");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception ex) {
        } finally {
            f = null;
            pw = null;
            file = null;
        }
    }

    private boolean runCmd(String cmd) {
        Process process = null;

        Runtime rt = null;
        try {
            rt = Runtime.getRuntime();
            process = rt.exec(cmd);
            process.waitFor(1, TimeUnit.SECONDS);
            return true;
        } catch (IOException e) {
            if (rt != null) {
                rt.gc();
            }
            return false;

        } catch (Exception ex) {
            if (rt != null) {
                rt.gc();
            }
            return false;

        } finally {

            try {
                if (process != null) {

                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.getErrorStream().close();
                    process.destroy();
                    process = null;
                    rt = null;
                }
            } catch (IOException e) {
            } catch (Exception ex) {
            }
        }
    }
}
