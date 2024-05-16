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

	/*
	 * Iragarkia erreproduzitzen du, bidea hardkodeatuta dago
	 */
	public void erreproduzitu() {
		String bidea = "C:\\Users\\in1dm3-d\\Desktop\\Iragarkia\\audio Securitas Direct.wav";

		super.audioaAmatatu();
		try {
			this.clip = AudioSystem.getClip();
			this.clip.open(AudioSystem.getAudioInputStream(new File(bidea)));
			this.clip.start();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Errorea audioan:\n" + ex.getMessage());
		}
	}

	/**
	 * Momentuan audio bat erreproduzitzen badago true bueltatzen du beztela falses
	 * 
	 * @return
	 */
	public boolean errepDago() {
		boolean errepDago = false;

		if (this.clip != null) {
			if (this.clip.isRunning()) {
				errepDago = true;
			}
		}
		return errepDago;
	}

}
