package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Modelo.Abestia;
import Modelo.Hizkuntza;

public class TestHizkuntza {

	// SETUP BAT TESTAK EGITEKO //
	
		static Hizkuntza h1;
		@Before
		public void setUp() throws Exception {
		h1 = new Hizkuntza("EU","euskera");
		}
		
		// GETTERS ETA SETTERS TESTAK //
		
		@Test
		public void testGetHizkuntzaID() {
			assertEquals("EU", h1.getHizkuntzaid());
		}
		@Test
		public void testSetHizkuntzaID() {
			h1.setHizkuntzaid("ES");
			assertEquals("ES", h1.getHizkuntzaid());
		}
		@Test
		public void testGetHizkuntzaDesk() {
			assertEquals("euskera", h1.getDesk());
		}
		@Test
		public void testSetHizkuntzaDesk() {
			h1.setDesk("español");
			assertEquals("español", h1.getDesk());
		}
		
		// TOSTRING TESTAK //
		@Test
		public void testToString() {
			String txtHizkuntza = h1.toString();
			String esperatutakoHizkuntza =  "hizkuntzaId=" + h1.getHizkuntzaid();
					
		}
		

}
