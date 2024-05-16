package MetodoakTest;

import static org.junit.Assert.*;

import java.io.File;
import javax.sound.sampled.Clip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Metodoak.iragarkiaMetodoak;

public class iragarkiaMetodoakTest {

	private iragarkiaMetodoak iragarkiaMetodoak;
	private String iragarkiaFilePath = "C:\\Users\\in1dm3-d\\Desktop\\Iragarkia\\audio Securitas Direct.wav";

	@Before
	public void setUp() throws Exception {
		iragarkiaMetodoak = new iragarkiaMetodoak();
	}

	@Test
	public void testErreproduzitu() {
		File audioFile = new File(iragarkiaFilePath);
		assertTrue(audioFile.exists());
		iragarkiaMetodoak.erreproduzitu();
	}

	@Test 
	public void testAudioaAmatatu() {
		iragarkiaMetodoak.erreproduzitu();
		iragarkiaMetodoak.audioaAmatatu();
		assertFalse(iragarkiaMetodoak.errepDago());
	}

	@Test
	public void testErrepDago() {
		iragarkiaMetodoak.erreproduzitu();
		assertTrue(iragarkiaMetodoak.errepDago());
		iragarkiaMetodoak.audioaAmatatu();
	}
	
	
}