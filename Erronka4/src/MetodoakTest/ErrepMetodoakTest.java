package MetodoakTest;
import static org.junit.Assert.*;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Metodoak.ErrepMetodoak;

public class ErrepMetodoakTest {

    private ErrepMetodoak errepMetodoak;
    private String testAudioName = "Buleria";

    @Before
    public void setUp() throws Exception {
        errepMetodoak = new ErrepMetodoak();
    }

    @After
    public void tearDown() throws Exception {
        errepMetodoak.audioaAmatatu();
    }

    @Test
    public void testErreproduzitu() {
        Clip clip = errepMetodoak.erreproduzitu(testAudioName);
        assertNotNull(clip);
        assertFalse(clip.isRunning());
        assertTrue(errepMetodoak.getErreproduzitzenDago());
    }

    @Test
    public void testAudioaAmatatu() {
        errepMetodoak.erreproduzitu(testAudioName);
        errepMetodoak.audioaAmatatu();
        assertTrue(errepMetodoak.getErreproduzitzenDago());
    }

    @Test
    public void testKantaDaramatzanSeg() {
        errepMetodoak.erreproduzitu(testAudioName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int seconds = errepMetodoak.kantaDaramatzanSeg();
        assertFalse(seconds >= 2);
    }

    @Test
    public void testErrepDago() {
        errepMetodoak.erreproduzitu(testAudioName);
        assertFalse(errepMetodoak.errepDago());
    }

    @Test
    public void testAudioaBerrezarri() {
        errepMetodoak.erreproduzitu(testAudioName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        errepMetodoak.audioaAmatatu();
        int position = errepMetodoak.kantaDaramatzanSeg();
        errepMetodoak.audioaBerrezarri(position, testAudioName);
        assertTrue(errepMetodoak.getErreproduzitzenDago());
        assertEquals(position, errepMetodoak.kantaDaramatzanSeg());
    }
}