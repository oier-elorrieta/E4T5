package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Modelo.Podcast;
import Modelo.LogeazioDatuak;

public class TestPodcast {

	// SETUP BAT TESTAK EGITEKO //

	static Podcast pod1;

	@Before
	public void setUp() throws Exception {
		pod1 = new Podcast("kolaboratzaileak", "izenburua", "200");
	}

	// GETTERS ETA SETTERS TESTAK //

	@Test
	public void testGetKolaboratzaileak() {
		assertEquals("kolaboratzaileak", pod1.getKolaboratzaile());
	}

	@Test
	public void testSetIdaudioa() {
		pod1.setKolaboratzaile("Jon");
		assertEquals("Jon", pod1.getKolaboratzaile());
	}

	@Test
	public void testGetIzenburua() {
		assertEquals("izenburua", pod1.getIzenburua());
	}

	@Test
	public void testSetIzenburua() {
		pod1.setIzenburua("Club 113");
		assertEquals("Club 113", pod1.getIzenburua());
	}

	@Test
	public void testGetIraupena() {
		assertEquals("200", pod1.getIraupena());
	}

	@Test
	public void testSetIraupena() {
		pod1.setIraupena("220");
		assertEquals("220", pod1.getIraupena());
	}

	// TOSTRING TESTAK //

	@Test
	public void testtoString() {
		String txtPodcast = pod1.toString();
		String esperotakoaPodcast = pod1.getIzenburua() + " | kolaboratzailea/k=" + pod1.getKolaboratzaile()
				+ " | iraupena=" + pod1.getIraupena();
	}

	// EQUALS TEST//

	@Test
	public void TestEqualsTrue() {
		assertTrue(pod1.equals(pod1));
	}

	@Test
	public void TestEqualsNull() {
		assertFalse(pod1.equals(null));
	}

	@Test
	public void TestEqualsTxarto() {
		String txarto = "aa";
		assertFalse(pod1.equals(txarto));
	}

	@Test
	public void TestEquals() {
		Podcast pod2 = new Podcast("kolaboratzaileak", "izenburua", "200");
		assertTrue(pod1.equals(pod2));
	}

}
