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
			po1 = new Podcasterra("izenArtistikoa", "deskribapena",null);
			}

			// GETTERRAK ETA SETTERRAK TESTAK //
			
			@Test
			public void testGetIzenArtistikoa() {
				assertEquals("izenArtistikoa", po1.getIzenaartistikoa());
			}

			@Test
			public void testSetIzenArtistikoa() {
				po1.setIzenaartistikoa("Rosalia");
				assertEquals("Rosalia", po1.getIzenaartistikoa());
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
				String txtPodcasterra = po1.toString();
				String esperatutakoPodcasterra = "Musikaria [izenaArtistikoa=" + po1.getIzenaartistikoa() + ", deskribapena="
						+ po1.getDeskribapena() + "]";
				;
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
				Podcasterra po2 = new Podcasterra("izenArtistikoa", "deskribapena",null);
				assertTrue(po1.equals(po2));
				}

	}


