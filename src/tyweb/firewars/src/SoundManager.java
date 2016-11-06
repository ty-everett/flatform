package tyweb.firewars.src;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class SoundManager {
    
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream(url));
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                clip.open(inputStream);
                clip.start(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     }).start();
   }
    
}
