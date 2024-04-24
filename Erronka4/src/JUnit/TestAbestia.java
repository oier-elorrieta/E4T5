package JUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Modelo.Abestia;


public class TestAbestia {

	// SETUP BAT TESTAK EGITEKO //
	
	static Abestia ab1;
	@Before
	public void setUp() throws Exception {
	ab1 = new Abestia(1,1,200,"Izenburua");
	}
	
	// GETTERS ETA SETTERS TESTAK //
	
	@Test
	public void testGetIdMusikari() {
		assertEquals(1, ab1.getIdMusikari());
	}
	@Test
	public void testSetIdMusikari() {
		ab1.setIdMusikari(0);
		assertEquals(0, ab1.getIdMusikari());
	}
	@Test
	public void testGetIdAudioa() {
		assertEquals(1, ab1.getIdAudio());
	}
	@Test
	public void testSetIdaudioa() {
		ab1.setIdAudio(0);
		assertEquals(0, ab1.getIdAudio());
	}
	@Test
	public void testGetIraupena() {
		assertEquals(200, ab1.getIraupena());
	}
	@Test
	public void testSetIraupena() {
		ab1.setIraupena(220);
		assertEquals(220, ab1.getIraupena());
	}
	@Test
	public void testGetIzenburua() {
		assertEquals("Izenburua", ab1.getIzenburua());
	}
	@Test
	public void testSetIzenburua() {
		ab1.setIzenburua("Izenburu1");
		assertEquals("Izenburu1", ab1.getIzenburua());
	}
	
	// TOSTRING TESTAK //
	
	@Test
	public void testtoString() {
		String txtAbestiak = ab1.toString();
		String esperotakoaAbesti = "Abestiak [IdMusikari=" + ab1.getIdMusikari() + ", IdAudio=" + ab1.getIdAudio() + ", Iraupena=" + ab1.getIraupena() + ", Izenburua="
				+ ab1.getIzenburua() + "]";
	}
	
	//EQUALS TEST//
	
		@Test
		public void TestEqualsTrue(){
		assertTrue(ab1.equals(ab1));	
		}
		
		@Test
		public void TestEqualsNull(){
		assertFalse(ab1.equals(null));	
		}
		
		@Test
		public void TestEqualsTxarto(){
		String txarto = "aa";
		assertFalse(ab1.equals(txarto));		}

		@Test
		public void TestEquals(){
		Abestia ab2 = new Abestia(1,1,200,"Izenburua");
		assertTrue(ab1.equals(ab2));
		}
}

