package JUnit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Modelo.Playlist;

public class TestPlaylist {

	// SETUP BAT TESTAK EGITEKO//
	
	static Playlist pl1;
	@Before
	public void setUp() throws Exception {
	pl1 = new Playlist(1,"Izenburua","2024");
	}
	
	// GETTERS ETA SETTERS TESTAK //
	
	@Test
	public void testGetIdList() {
		assertEquals(1, pl1.getIdlist());
	}

	@Test
	public void testSetIdList() {
		pl1.setIdlist(0);
		assertEquals(0, pl1.getIdlist());
	}
	
	@Test
	public void testGetIzenburua() {
		assertEquals("Izenburua", pl1.getIzenburua());
	}

	@Test
	public void testSetIzenburura() {
		pl1.setIzenburua("Izenburua1");
		assertEquals("Izenburua1", pl1.getIzenburua());
	}
	@Test
	public void testGetSorreraData() {
		assertEquals("2024", pl1.getSorrera_data());
	}

	@Test
	public void testSetSorreraData() {
		pl1.setSorrera_data("2023");
		assertEquals("2023", pl1.getSorrera_data());
	}
	
	// TOSTRING TESTAK //
	
	@Test
	public void testtoString() {
		String txtPlaylist = pl1.toString();
		String esperotakoaPlaylist = pl1.getIzenburua();
	}
	
	//EQUALS TEST//
	
		@Test
		public void TestEqualsTrue(){
		assertTrue(pl1.equals(pl1));	
		}
		
		@Test
		public void TestEqualsNull(){
		assertFalse(pl1.equals(null));	
		}
		
		@Test
		public void TestEqualsTxarto(){
		String txarto = "aa";
		assertFalse(pl1.equals(txarto));		}

		@Test
		public void TestEquals(){
			Date Sorrera_data = new Date(2018, 04, 18);
		Playlist pl2 = new Playlist(1,"Izenburua","2024");
		assertTrue(pl1.equals(pl2));
		}
		
}
