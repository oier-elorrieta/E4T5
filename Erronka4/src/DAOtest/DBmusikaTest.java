package DAOtest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import DB.DBmusika;
import Modelo.Abestia;
import Modelo.Albuma;
import Modelo.Musikaria;

public class DBmusikaTest {

	 @Test
	    public void testMusikaDeskrubitu() {
	        DBmusika dbmusika = new DBmusika();
	        ArrayList<Musikaria> emaitza = dbmusika.musikaDeskrubitu();

	        assertNotNull(emaitza);   
	    }
	 
	 
	   @Test
	    public void testAlbumlistArtistaBadago() {
		   DBmusika dbmusika = new DBmusika(); 
	        String artista = "Drake";
	        ArrayList<Albuma> emaitza = dbmusika.Albumlist(artista);

	        assertNotNull(emaitza); 
	        assertFalse(emaitza.isEmpty());
	    }
	   
	   @Test
	    public void testAlbumlistArtistaEZdago() {
		   DBmusika dbmusika = new DBmusika(); 
	        String artista = "kk";
	        ArrayList<Albuma> emaitza = dbmusika.Albumlist(artista);

	        assertNotNull(emaitza); 
	        assertTrue(emaitza.isEmpty());
	    }
	   
	   @Test
	    public void testlortuAbestiaBadago() {
		   DBmusika dbmusika = new DBmusika(); 
	        String album = "Motomami";
	        ArrayList<Abestia> emaitza = dbmusika.lortuAbestiak(album);

	        assertNotNull(emaitza); 
	        assertFalse(emaitza.isEmpty());
	    }
	   
	   @Test
	    public void testlortuAbestiaEZdago() {
		   DBmusika dbmusika = new DBmusika(); 
	        String album = "kk";
	        ArrayList<Abestia> emaitza = dbmusika.lortuAbestiak(album);

	        assertNotNull(emaitza); 
	        assertTrue(emaitza.isEmpty());
	    }
	   
	   
	   @Test
	    public void testlortuAbestiID() {
		   DBmusika dbmusika = new DBmusika(); 
	       String izena  = "Buleria";
	       String emaitza = dbmusika.lortuAbestiId(izena);

	        assertNotNull(emaitza); 
	    }
	   
	   
	   
	   
	   
	   
	   
	   
	   

}