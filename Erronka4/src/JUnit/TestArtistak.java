package JUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Modelo.Artistak;


public class TestArtistak {

	// SETUP BAT TESTAK EGITEKO //
	
	static Artistak ar1;
	@Before
	public void setUp() throws Exception {
	ar1 = new Artistak(1,"IzenArtistiko","Deskribapena",true);
	}

	// GETTERRAK ETA SETTERRAK TESTAK //
	
	@Test
	public void testGetIdArtista() {
		assertEquals(1, ar1.getIdArtista());
	}
	
	@Test
	public void testSetIdArtista() {
		ar1.setIdArtista(0);
		assertEquals(0, ar1.getIdArtista());
	}
	@Test
	public void testGetIzenArtistiko() {
		assertEquals("IzenArtistiko", ar1.getIzenaArtistikoa());
	}
	@Test
	public void testSetIzenartistiko() {
		ar1.setIzenaArtistikoa("Marie Curlie");
		assertEquals("Marie Curlie", ar1.getIzenaArtistikoa());
	}
	@Test
	public void testGetDeskribapena() {
		assertEquals("Deskribapena", ar1.getDeskribapena());
	}
	@Test
	public void testSetDeskribapena() {
		ar1.setDeskribapena("kk");
		assertEquals("kk", ar1.getDeskribapena());
	}
	
	@Test
	public void testGetEzaugarriaTrue() {
		assertTrue(ar1.isEzaugarria());
	}
	@Test
	public void testGetEzaugarriaFalse() {
		ar1.setEzaugarria(false);
		assertFalse(ar1.isEzaugarria());
	}
	
	// TO STRING TESTAK //
	
	@Test
	public void testToString() {
		String txtArtista = ar1.toString();
		String esperatutakoArtista = "Artistak [idArtista=" + ar1.getIdArtista() + ", izenaArtistikoa=" + ar1.getIzenaArtistikoa() + ", deskribapena="
				+ ar1.getDeskribapena() + ", ezaugarria=" + ar1.isEzaugarria() + "]";
	}
	
	//EQUALS TEST//
	
		@Test
		public void TestEqualsTrue(){
		assertTrue(ar1.equals(ar1));	
		}
		
		@Test
		public void TestEqualsNull(){
		assertFalse(ar1.equals(null));	
		}
		
		@Test
		public void TestEqualsTxarto(){
		String txarto = "aa";
		assertFalse(ar1.equals(txarto));		}

		@Test
		public void TestEquals(){
		Artistak ar2 = new Artistak(1,"IzenArtistiko","Deskribapena",true);
		assertTrue(ar1.equals(ar2));
		}
}
