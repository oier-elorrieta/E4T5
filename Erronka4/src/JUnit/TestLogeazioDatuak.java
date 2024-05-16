package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Modelo.Abestia;
import Modelo.LogeazioDatuak;

public class TestLogeazioDatuak {

	// SETUP BAT TESTAK EGITEKO //
	
		static LogeazioDatuak ld1;
		@Before
		public void setUp() throws Exception {
		ld1 = new LogeazioDatuak("logeatuta"); 
		}
	
		// GETTERS ETA SETTERS TESTAK //
		
		@Test
		public void testGetLogeatuta() {
			assertEquals("logeatuta", ld1.getLogeatuta());
		}
		@Test
		public void testSetIdaudioa() {
			ld1.setLogeatuta("login");
			assertEquals("login", ld1.getLogeatuta()); 
		}
		
		// TOSTRING TESTAK //
		
		@Test
		public void testtoString() {
			String txtLogeazioDatuak = ld1.toString();
			String esperotakoaLogDatuak = "logeazioDatuak [logeatuta=" + ld1.getLogeatuta() + "]";
		}
		
		//EQUALS TEST//
		
			@Test
			public void TestEqualsTrue(){
			assertTrue(ld1.equals(ld1));	
			}
			
			@Test
			public void TestEqualsNull(){
			assertFalse(ld1.equals(null));	
			}
			
			@Test
			public void TestEqualsTxarto(){
			String txarto = "aa";
			assertFalse(ld1.equals(txarto));		}

			@Test
			public void TestEquals(){
			LogeazioDatuak ab2 = new LogeazioDatuak("logeatuta");
			assertFalse(ld1.equals(ab2));
			}
}
