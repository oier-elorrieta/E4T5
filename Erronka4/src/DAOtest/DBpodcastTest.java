package DAOtest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import DB.DBmusika;
import DB.DBpodcast;
import Modelo.Musikaria;
import Modelo.Podcast;
import Modelo.Podcasterra;

public class DBpodcastTest {

	   @Test
	    public void testlortuPodcastBadago() {
		   DBpodcast dbpodcast = new DBpodcast(); 
		   String podcast = "The Wild Project #1";
	        ArrayList<Podcast> emaitza = dbpodcast.lortuPodcast(podcast);

	        assertNotNull(emaitza);
	    }
	   
	    @Test
	    public void testlortuAbestiaEZdago() {
		   DBpodcast dbpodcast = new DBpodcast(); 
	        String podcast = "The Wild Project #5";
	        ArrayList<Podcast> emaitza = dbpodcast.lortuPodcast(podcast);

	        assertNotNull(emaitza); 
	    }
	    
	    @Test
	    public void testMusikaDeskrubitu() {
	    	DBpodcast dbpodcast = new DBpodcast();
	        ArrayList<Podcasterra> emaitza = dbpodcast.podcasterDeskubritu();

	        assertNotNull(emaitza);   
	    }
	    
	    @Test
	    public void testLortuPodcastId() {
	    	DBpodcast dbpodcast = new DBpodcast(); 

	        Podcast selectedPodcast = new Podcast("Jordi Wild", "The Wild Project #1", "122.24");
	        String id = dbpodcast.lortuPodcastId(selectedPodcast);
	        
	        assertNotNull(id);
	   
	    }
}