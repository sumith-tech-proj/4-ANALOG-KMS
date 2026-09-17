package obuits;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import static obuits.MainFrmIts.audio_speaker_off;
import static obuits.MainFrmIts.audio_speaker_on;
import static obuits.clsDefines.config_filepath;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsDefines.specl_audio_playing;
import static obuits.clsSharedVariables.set_speaker_enabled_status;

public class AudioPlayActivity {

    // Define constant for buffer size
    private static final int BUFFER_SIZE = 4096;

    // Declare static variable to hold AudioPlayAcitvityThread instance
    private static AudioPlayAcitvityThread objAudio = null;

    // Constructor
    AudioPlayActivity() {
        // If objAudio is null, create a new instance of AudioPlayAcitvityThread and start it
        if (objAudio == null) {
            objAudio = new AudioPlayAcitvityThread();
            objAudio.start();
        }
    }
    static boolean isAudioPlaying = false;

    //static clsAudioFilesQueue objQue = new clsAudioFilesQueue(); 
    public static synchronized boolean getAudioPlayingStat() {
        return isAudioPlaying;
    }

    public static synchronized void setAudioPlayingStat(boolean stat) {
        isAudioPlaying = stat;
    }

    public static void playAudio(String audioFilePath) {
        // Initialize variables
        File audioFile = new File(audioFilePath);
        AudioInputStream audioStream = null;
        AudioFormat format = null;
        SourceDataLine audioLine = null;
        DataLine.Info info = null;
        byte[] bytesBuffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        try {
            // Get AudioInputStream from the audio file
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(SourceDataLine.class, format);
            audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);
            audioLine.start();
            try {
                Thread.sleep(100); // Delay to allow audio line to start
            } catch (InterruptedException ex) {
            }
            // Read audio data into buffer and write it to audio line
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
            // Drain the audio line and close resources
            audioLine.drain();
            audioLine.close();
            audioStream.close();
        } catch (UnsupportedAudioFileException ex) {
        } catch (LineUnavailableException ex) {
        } catch (IOException ex) {
        } finally {
            // Clean up resources
            audioFile = null;
            audioStream = null;
            format = null;
            audioLine = null;
            info = null;
            bytesBuffer = null;
        }
    }

    // Inner class representing the thread for audio playback
    class AudioPlayAcitvityThread extends Thread {

        @Override
        public void run() {
            // Set uncaught exception handler for the thread
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionAudioActivity());
            File file;
            File f;
            byte i;
            byte j;
            String aud_file;
            try {
                setAudioPlayingStat(true);
                audio_speaker_on();

                // Loop for processing audio queue
                int que_cnt;
                for (j = 0; j < 5; j++) {
                    que_cnt = clsSharedVariables.objAudQue.audioQueueCnt();
                    for (i = 0; i < que_cnt; i++) {
                        aud_file = clsSharedVariables.objAudQue.removeData();
                        if (!aud_file.equals("")) {
                            // Determine file location and handle playback
                            if (aud_file.equals("/stoprequest.wav") || aud_file.equals("/emergency.wav")) {
                                f = new File(config_filepath, aud_file);
                            } else {
                                f = new File(main_route_filepath, aud_file);
                            }
                            file = new File(route_filepath, aud_file);
                            // Check if file exists and play it
                            // Handle WAV and MP3 files differently
                            if (f.exists()) {
                                if (f.getName().endsWith("wav") || f.getName().endsWith("WAV")) {
                                    Thread.sleep(500);
                                    this.setPriority(6);
                                    playAudio(f.getPath());
                                    this.setPriority(NORM_PRIORITY);
                                } else {
                                    Mp3Player mp3play = new Mp3Player();
                                    Thread.sleep(1000);
                                    mp3play.play(f.getPath());
                                    mp3play = null;
                                }
                            } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                                if (file.getName().endsWith("wav") || file.getName().endsWith("WAV")) {
                                    Thread.sleep(500);
                                    this.setPriority(6);
                                    playAudio(file.getPath());
                                    this.setPriority(NORM_PRIORITY);
                                } else {
                                    Mp3Player mp3play = new Mp3Player();
                                    Thread.sleep(1000);
                                    mp3play.play(file.getPath());
                                    mp3play = null;
                                }
                            }
                        }
                    }
                    if (clsSharedVariables.objAudQue.audioQueueCnt() <= 0) {
                        break;
                    }
                }
                que_cnt = clsSharedVariables.objAudQue.audioQueueCntSpel();
                for (i = 0; i <= que_cnt; i++) {
                    if (clsSharedVariables.objAudQue.audioQueueCnt() > 0) {

                        for (i = 0; i < clsSharedVariables.objAudQue.audioQueueCnt(); i++) {
                            aud_file = clsSharedVariables.objAudQue.removeData();
                            // System.out.println("audio4");
                            if (!aud_file.equals("")) {
                                if (aud_file.equals("stoprequest.WAV") || aud_file.equals("emergency.WAV")) {
                                    f = new File(config_filepath, aud_file);
                                } else {
                                    f = new File(main_route_filepath, aud_file);
                                }
                                file = new File(route_filepath, aud_file);
                                if (f.exists()) {
                                    if (f.getName().endsWith("wav") || f.getName().endsWith("WAV")) {
                                        Thread.sleep(500);
                                        this.setPriority(6);
                                        playAudio(f.getPath());
                                        this.setPriority(NORM_PRIORITY);
                                    } else {
                                        Mp3Player mp3play = new Mp3Player();
                                        Thread.sleep(1000);
                                        mp3play.play(f.getPath());
                                        mp3play = null;
                                    }
                                } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
                                    if (file.getName().endsWith("wav") || file.getName().endsWith("WAV")) {
                                        Thread.sleep(500);
                                        this.setPriority(6);
                                        playAudio(file.getPath());
                                        this.setPriority(NORM_PRIORITY);
                                    } else {
                                        Mp3Player mp3play = new Mp3Player();
                                        Thread.sleep(1000);
                                        mp3play.play(file.getPath());
                                        mp3play = null;
                                    }
                                }
                            }
                        }
                    }
                    aud_file = clsSharedVariables.objAudQue.removeDataSpel();
                    if (!aud_file.equals("")) {
                        if (aud_file.contains("SpelAudio")) {
                            f = new File(main_route_filepath, aud_file);
                            file = new File(route_filepath, aud_file);
                            if (f.exists()) {
                                if (f.getName().endsWith("wav") || f.getName().endsWith("WAV")) {
                                    Thread.sleep(500);
                                    this.setPriority(6);
                                    playAudio(f.getPath());
                                    this.setPriority(NORM_PRIORITY);
                                } else {
                                    Mp3Player mp3play = new Mp3Player();
                                    Thread.sleep(1000);
                                    mp3play.play(f.getPath());
                                    mp3play = null;
                                }
                                specl_audio_playing = true;
                                //System.out.println("specl_audio_playing true");
                            } else if (file.exists()) {
                                if (file.getName().endsWith("wav") || file.getName().endsWith("WAV")) {
                                    Thread.sleep(500);
                                    this.setPriority(6);
                                    playAudio(file.getPath());
                                    this.setPriority(NORM_PRIORITY);
                                } else {
                                    Mp3Player mp3play = new Mp3Player();
                                    Thread.sleep(1000);
                                    mp3play.play(file.getPath());

                                    mp3play = null;
                                }
                                specl_audio_playing = true;
                               // System.out.println("specl_audio_playing true");
                            }
                        }
                    }
                }

            } catch (Exception ex) {
            } finally {
                this.setPriority(NORM_PRIORITY);
                audio_speaker_off();
                set_speaker_enabled_status(false);
                setAudioPlayingStat(false);
                objAudio = null;
                file = null;
                f = null;
                aud_file = null;
            }
        }
    }

    // Inner class representing the uncaught exception handler
    class ExceptionAudioActivity implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // Interrupt and reset objAudio on uncaught exception
            if (objAudio != null) {
                objAudio.isInterrupted();
                objAudio = null;
            }
            objAudio = new AudioPlayAcitvityThread();
            objAudio.start();
        }
    }
}
