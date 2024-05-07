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
		m1 = new Musikaria("izenArtistikoa", "deskribapena", null);
	}

	// GETTERRAK ETA SETTERRAK TESTAK //

	@Test
	public void testGetIzenArtistikoa() {
		assertEquals("izenArtistikoa", m1.getIzenaartistikoa());
	}

	@Test
	public void testSetIzenArtistikoa() {
		m1.setIzenaartistikoa("Rosalia");
		assertEquals("Rosalia", m1.getIzenaartistikoa());
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
		String esperatutakoArtista = "Musikaria [izenaArtistikoa=" + m1.getIzenaartistikoa() + ", deskribapena="
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
		Musikaria m2 = new Musikaria("izenArtistikoa", "deskribapena", null);
		assertTrue(m1.equals(m2));
	}

}
