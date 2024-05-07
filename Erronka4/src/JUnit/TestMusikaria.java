package JUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Modelo.Musikaria;

public class TestMusikaria {

	// SETUP BAT TESTAK EGITEKO //

	static Musikaria m1;

	@Before
	public void setUp() throws Exception {
		m1 = new Musikaria(1, "izenArtistikoa", "deskribapena", 1);
	}

	// GETTERRAK ETA SETTERRAK TESTAK //

	@Test
	public void testGetIdArtista() {
		assertEquals(1, m1.getIdartista());
	}

	@Test
	public void testSetIdArtista() {
		m1.setIdartista(0);
		assertEquals(0, m1.getIdartista());
	}

	@Test
	public void testGetIdMusikari() {
		assertEquals(1, m1.getMusikariaID());
	}

	@Test
	public void testSetIdMusikari() {
		m1.setMusikariaID(0);
		assertEquals(0, m1.getMusikariaID());
	}

	@Test
	public void testGetDeskribapena() {
		assertEquals("deskribapena", m1.getDeskribapena());
	}

	@Test
	public void testSetDeskribapena() {
		m1.setDeskribapena("kk");
		assertEquals("kk", m1.getDeskribapena());
	}

	// TO STRING TESTAK //

	@Test
	public void testToString() {
		String txtMusikaria = m1.toString();
		String esperatutakoArtista = "Musikaria [musikariaID=" + m1.getMusikariaID() + ", idArtista="
				+ m1.getIdartista() + ", izenaArtistikoa=" + m1.getIzenaartistikoa() + ", deskribapena="
				+ m1.getDeskribapena() + "]";
		;
	}

	// EQUALS TEST//

	@Test
	public void TestEqualsTrue() {
		assertTrue(m1.equals(m1));
	}

	@Test
	public void TestEqualsNull() {
		assertFalse(m1.equals(null));
	}

	@Test
	public void TestEqualsTxarto() {
		String txarto = "aa";
		assertFalse(m1.equals(txarto));
	}

	@Test
	public void TestEquals() {
		Musikaria m2 = new Musikaria(1, "izenArtistikoa", "deskribapena", 1);
		assertTrue(m1.equals(m2));
	}

}
