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
    private boolean erreproduzitzenDago=false;
    
    /**
     * Parametroz jasotako audioaren izen berdina duen AUDIOS karpetako audioa erreproduzitzen du 
     * Jadanik audio bat erreproduzitzen badago, gelditu eta berria erreproduzituko du
     * @param selectedAudioName
     * @return 
     */
    public Clip erreproduzitu(String selectedAudioName) {
        String bidea = "C:\\Users\\in1dm3-d\\Desktop\\AUDIOS";
        String errutaOsoa = Paths.get(bidea, selectedAudioName + ".wav").toString();
      
        audioaAmatatu();
          
        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(AudioSystem.getAudioInputStream(new File(errutaOsoa)));
            this.clip.start();
            erreproduzitzenDago = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Errorea audioan:\n" + ex.getMessage());
        }
		return clip;
    }
    
    /**
     * Momentuan erreproduzitzen den audioa amatatzen du
     */
    public void audioaAmatatu() {
        if (this.clip != null && this.clip.isRunning()) {
            this.clip.stop();
            this.clip.close();
            erreproduzitzenDago = false;
        }
    }
    
    /**
     * Momentuan erreproduzitzen ari den kantak senbat segundu daramatzan bueltazen du
     * @return
     */
    public int kantaDaramatzanSeg() {
    	int luzaera;
        if (this.clip != null && this.clip.isRunning()) {
            long microsegundos = this.clip.getMicrosecondPosition();
            luzaera =  (int) (microsegundos / 1_000_000); 
        } else {
        	luzaera=0; 
        }
        return luzaera;
    }
    
    /**
     * Momentuan audio bat erreproduzitzen badago true bueltatzen du beztela falses
     * @return
     */
    public boolean errepDago(){
    	boolean errepDago=false;
    	
    	if(this.clip!= null) {
    		if(this.clip.isRunning()) {
    			errepDago=true;
    		}
    	}
    	return errepDago;
    }
	
    /**
     * Erreproduzitzen dagoen klipa parapetroz jasotako segundutik erreproduzitzen hasten da
     * @param t denpora segunduetan
     * @param selectedAudioName
     */
	public void audioaBerrezarri(int t,String selectedAudioName) {
		
		//Milisegundoetara bilakatzen dugu
		t=t*1000000;
	    if (this.clip != null && this.clip.isRunning()==false) {
	    	erreproduzitu(selectedAudioName);
	        this.clip.setMicrosecondPosition(t);
	    }
	}
	
	public boolean getErreproduzitzenDago() {
		return this.erreproduzitzenDago;
	}
	
	public void setErreproduzitzenDago(boolean errep) {
		this.erreproduzitzenDago=errep;
	}
}
