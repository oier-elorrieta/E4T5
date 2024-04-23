package JUnit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Modelo.Playlistak;

public class TestPlaylisttak {

	// SETUP BAT TESTAK EGITEKO//
	
	static Playlistak pl1;
	@Before
	public void setUp() throws Exception {
	Date Sorrera_data = new Date(2018, 04, 18);
	pl1 = new Playlistak(1,"Izenburua",Sorrera_data);
	}
	
	// GETTERS ETA SETTERS TESTAK //
	
	@Test
	public void testGetIdList() {
		assertEquals(1, pl1.getIdList());
	}

	@Test
	public void testSetIdList() {
		pl1.setIdList(0);
		assertEquals(0, pl1.getIdList());
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
		Date Sorrera_data = new Date(2018, 04, 18);
		assertEquals(Sorrera_data, pl1.getSorrera_data());
	}

	@Test
	public void testSetSorreraData() {
		Date Sorrera_data1 = new Date(2013, 04, 18);
		pl1.setSorrera_data(Sorrera_data1);
		assertEquals(Sorrera_data1, pl1.getSorrera_data());
	}
	
	// TOSTRING TESTAK //
	
	@Test
	public void testtoString() {
		String txtPlaylist = pl1.toString();
		String esperotakoaPlaylist = "Playlistak [IdList=" + pl1.getIdList() + ", Izenburua=" + pl1.getIzenburua() + ", Sorrera_data=" + pl1.getSorrera_data() + "]";
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
		Playlistak pl2 = new Playlistak(1,"Izenburua",Sorrera_data);
		assertTrue(pl1.equals(pl2));
		}
		
}
