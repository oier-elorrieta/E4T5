package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Modelo.Musikaria;
import Modelo.Podcasterra;

public class TestPodcasterra {

	// SETUP BAT TESTAK EGITEKO //
	
			static Podcasterra po1;
			@Before
			public void setUp() throws Exception {
			po1 = new Podcasterra(1, "izenArtistikoa", "deskribapena",1);
			}

			// GETTERRAK ETA SETTERRAK TESTAK //
			
			@Test
			public void testGetIdArtista() {
				assertEquals(1, po1.getIdartista());
			}
			
			@Test
			public void testSetIdArtista() {
				po1.setIdartista(0);
				assertEquals(0, po1.getIdartista());
			}
			
			@Test
			public void testGetIdMusikari() {
			assertEquals(1,po1.getPodacasterID());
			}
			
			@Test
			public void testSetIdMusikari() {
				po1.setPodacasterID(0);
				assertEquals(0, po1.getPodacasterID());
			}
			
			@Test
			public void testGetDeskribapena() {
				assertEquals("deskribapena", po1.getDeskribapena());
			}
			
			@Test
			public void testSetDeskribapena() {
				po1.setDeskribapena("kk");
				assertEquals("kk", po1.getDeskribapena());
			}
			
			
			// TO STRING TESTAK //
			
			@Test
			public void testToString() {
				String txtMusikaria = po1.toString();
				String esperatutakoArtista = "Musikaria [musikariaID=" + po1.getPodacasterID()+", idArtista=" + po1.getIdartista()
						+ ", izenaArtistikoa=" + po1.getIzenaartistikoa() + ", deskribapena=" + po1.getDeskribapena() 
						+ "]";;
			}
			
			//EQUALS TEST//
			
				@Test
				public void TestEqualsTrue(){
				assertTrue(po1.equals(po1));	
				}
				
				@Test
				public void TestEqualsNull(){
				assertFalse(po1.equals(null));	
				} 
				
				@Test
				public void TestEqualsTxarto(){
				String txarto = "aa";
				assertFalse(po1.equals(txarto));		}

				@Test
				public void TestEquals(){
				Podcasterra po2 = new Podcasterra(1, "izenArtistikoa", "deskribapena",1);
				assertTrue(po1.equals(po2));
				}

	}


