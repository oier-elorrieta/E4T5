package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Modelo.Albuma;

public class TestAlbuma {

	// SET UP //
	static Albuma alb1;

	@Before
	public void setUp() throws Exception {
		alb1 = new Albuma(1, "izenburua", 2024, "pop", 1, 3);
	}

	// TEST GETTERS AND SETTERS //
	
	@Test
	public void TestGetIdAlbum() {
		assertEquals(1, alb1.getIdAlbum());
	}

	@Test
	public void TestSetIdAlbum() {
		alb1.setIdAlbum(2);
		assertEquals(2, alb1.getIdAlbum());
	}

	@Test
	public void TestGetIzenburua() {
		assertEquals("izenburua", alb1.getIzenburua());
	}

	@Test
	public void TestSetIzenburua() {
		alb1.setIzenburua("Lemonade");
		assertEquals("Lemonade", alb1.getIzenburua());
	}

	@Test
	public void TestGetUrtea() {
		assertEquals(2024, alb1.getUrtea());
	}

	@Test
	public void TestSetUrtea() {
		alb1.setUrtea(2023);
		assertEquals(2023, alb1.getUrtea());
	}

	@Test
	public void TestGetGeneroa() {
		assertEquals("pop", alb1.getGeneroa());
	}

	@Test
	public void TestSetGeneroa() {
		alb1.setGeneroa("k-pop");
		assertEquals("k-pop", alb1.getGeneroa());
	}

	@Test
	public void TestGetIdArtista() {
		assertEquals(1, alb1.getIdArtista());
	}

	@Test
	public void TestSetIdArtista() {
		alb1.setIdArtista(2);
		assertEquals(2, alb1.getIdArtista());
	}

	@Test
	public void TestGetAbestiKop() {
		assertEquals(3, alb1.getAbestiKop());
	}

	@Test
	public void TestSetAbestiKop() {
		alb1.setAbestiKop(4);
		assertEquals(4, alb1.getAbestiKop());
	}
	
	// TOSTRING TESTAK //
	
		@Test
		public void testtoString() {
			String txtAlbumak = alb1.toString();
			String esperotakoaAlbuma = alb1.getIzenburua() + alb1.getUrtea() + "AbestiKop =" + alb1.getAbestiKop();
		}

	// EQUALS TEST//
		
	@Test
	public void TestEqualsTrue() {
		assertTrue(alb1.equals(alb1));
	}

	@Test
	public void TestEqualsNull() {
		assertFalse(alb1.equals(null));
	}

	@Test
	public void TestEqualsTxarto() {
		String txarto = "aa";
		assertFalse(alb1.equals(txarto));
	}

	@Test
	public void TestEquals() {
		Albuma alb2 = new Albuma(1, "izenburua", 2024, "pop", 1, 3);
		assertFalse(alb1.equals(alb2));
	}
}
