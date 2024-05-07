package Metodoak;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class iragarkiaMetodoak extends ErrepMetodoak {

	private Clip clip;
	
    public void erreproduzitu() {
        String bidea = "C:\\Users\\in1dm3-d\\Desktop\\Iragarkia\\audio Securitas Direct.wav";
        
        super.audioaGelditu();
        try {
            this.clip = AudioSystem.getClip();
            System.out.println(bidea);
            this.clip.open(AudioSystem.getAudioInputStream(new File(bidea)));
            this.clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Errorea audioan:\n" + ex.getMessage());
        }
    }
	
}
