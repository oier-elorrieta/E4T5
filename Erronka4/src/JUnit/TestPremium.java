package JUnit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import Modelo.Premium;


public class TestPremium {

	// SETUP BAT TESTAK EGITEKO //
	
	static Premium p1;
	@Before
	public void setUp() throws Exception {
	String data = "2024-04-18";
	p1 = new Premium("izena","Abizena","Erabiltzailea","Pasahitza", "2010-01-10");
	}
	
	// GETTERRAK ETA SETTERRAK TESTAK //
	
	@Test
	public void TestGetPremiummuga(){
		String data = "2024-04-18";
		assertEquals(data, p1.getPremiunMuga());
	}
	@Test
	public void TestSetpremiumMuga(){
		Date data1 = new Date(2024, 04, 13);
		p1.setPremiunMuga(data1);
		assertEquals(data1, p1.getPremiunMuga());
	}
	
	// TOSTRING TESTAK //
	
	@Test
	public void testToString() {
		String TxtFree = p1.toString();
			String esperatutakoaPremium = "Erabiltzaile [Izena=" + p1.getIzena() + ", Abizena=" + p1.getAbizena() + ", Erabiltzailea="
					+ p1.getErabiltzailea() + ", Pasahitza=" + p1.getPasahitza() + ", Jaiotze_data=" + p1.getJaiotze_data() + "Premiun [premiunMuga=" + p1.getPremiunMuga() + "]";
		}
		
	//EQUALS TESTAK//
	
		@Test
		public void TestEqualsTrue(){
		assertTrue(p1.equals(p1));	
		}
		
		@Test
		public void TestEqualsNull(){
		assertFalse(p1.equals(null));	
		}
		
		@Test
		public void TestEqualsTxarto(){
		String txarto = "aa";
		assertFalse(p1.equals(txarto));		}

		@Test
		public void Test(){
		Date data = new Date(2024, 04, 18);
		Premium p2 = new Premium("izena","Abizena","Erabiltzailea","Pasahitza","2010-10-10");
		assertTrue(p1.equals(p2));
		}
		
		
	}
