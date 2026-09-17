package obuits;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import static javax.speech.synthesis.Voice.GENDER_FEMALE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author sumitha
 */

/**
 * Class for text-to-speech synthesis using the FreeTTS library.
 */

public class clsTextSpeechTinker {

    static SynthesizerModeDesc desc;
    static Synthesizer synth;
    //static final String voiceName = "kevin16";

    /**
     * Constructor for clsTextSpeechTinker class. Initializes the FreeTTS
     * synthesizer with the specified voice.
     *
     * @param voiceName The name of the voice to be used for synthesis.
     */
    
    public clsTextSpeechTinker(String voiceName) {
        try {
            
            // Set system properties for FreeTTS
            System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            // Create a new synthesizer with the default voice
            desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);

            synth = Central.createSynthesizer(desc);
            synth.allocate();
            desc = (SynthesizerModeDesc) synth.getEngineModeDesc();
            Voice[] voices = desc.getVoices();
            Voice voice = null;
            // Set the specified voice for synthesis
            for (Voice entry : voices) {
                //System.out.println("voice name :" + entry);
                if (entry.getName().equals(voiceName)) {
                    voice = entry;
                    //System.out.println("voice gender :" + voice.getGender());
                    voice.setGender(GENDER_FEMALE);// set gender if applicable
                    //System.out.println("voice gender :" + voice.getGender());
                    break;
                }
            }
            // Set voice and volume properties
            synth.getSynthesizerProperties().setVoice(voice);
            //System.out.println("voice volume :" + synth.getSynthesizerProperties().getVolume());
            synth.getSynthesizerProperties().setVolume(10);

            //System.out.println("voice volume :" + synth.getSynthesizerProperties().getVolume());
            synth.resume(); //Resume synthesizer
        } catch (Exception ex) {
            // Handle exceptions
            String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";

        }
    }

    /**
     * Method to synthesize and speak the specified text.
     *
     * @param text The text to be spoken.
     */
    public static synchronized void speak(String text) {
        // Check if the text is empty or null
        if (text == null || text.trim().isEmpty()) {
            return; // Do nothing if text is empty
        }

        try {
            // Synthesize and speak the text
            synth.speakPlainText(text, null);
            //synth.speak(text, null);
            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);// Wait for speech synthesis to finish

            // synth.deallocate();
            //uncomment above if only want to speaking once
        } catch (Exception ex) {
            // Handle exceptions
            String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
            //System.out.println("" + ex);
            //System.out.println(message);
            ex.printStackTrace();
        }
    }
}
