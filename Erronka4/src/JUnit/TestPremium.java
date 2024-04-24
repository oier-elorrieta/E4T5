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
	p1 = new Premium("izena","Abizena","Erabiltzailea","Pasahitza", "2024-04-18", "2025-04-18");
	}
	
	// GETTERRAK ETA SETTERRAK TESTAK //
	
	@Test
	public void TestGetPremiumMuga(){
		assertEquals("2025-04-18", p1.getPremiumMuga());
	}
	@Test
	public void TestSetpremiumMuga(){
		p1.setPremiumMuga("2026-08-09");
		assertEquals("2026-08-09", p1.getPremiumMuga());
	}
	
	// TOSTRING TESTAK //
	
	@Test
	public void testToString() {
		String TxtFree = p1.toString();
			String esperatutakoaPremium = "Erabiltzaile [Izena=" + p1.getIzena() + ", Abizena=" + p1.getAbizena() + ", Erabiltzailea="
					+ p1.getErabiltzailea() + ", Pasahitza=" + p1.getPasahitza() + ", Jaiotze_data=" + p1.getJaiotze_data() + "Premiun [premiunMuga=" + p1.getPremiumMuga() + "]";
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
		Premium p2 = new Premium("izena","Abizena","Erabiltzailea","Pasahitza","2024-04-18", "2025-04-18");
		assertTrue(p1.equals(p2));
		}
		
		
	}
