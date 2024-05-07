package Metodoak;

import java.nio.file.Paths;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

public class ErrepMetodoak {
    
    private Clip clip;

    public void erreproduzitu(String selectedAudioName) {
        String erruta = "C:\\Users\\in1dm3-d\\Desktop\\AUDIOS";
        String errutaOsoa = Paths.get(erruta, selectedAudioName + ".wav").toString();
        
        detenerClip();
        
        try {
            this.clip = AudioSystem.getClip();
            System.out.println(errutaOsoa);
            this.clip.open(AudioSystem.getAudioInputStream(new File(errutaOsoa)));
            this.clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Errorea audioan:\n" + ex.getMessage());
        }
    }
    
    public void detenerClip() {
        if (this.clip != null && this.clip.isRunning()) {
            this.clip.stop();
            this.clip.close();
        }
    }
}
