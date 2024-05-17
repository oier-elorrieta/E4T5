package DAOtest;

import static org.junit.Assert.*;
import java.sql.Blob;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import DB.DBerrep;

public class DBerrepTest {
    private DBerrep dBerrep;

    @Before
    public void setUp() {
        dBerrep = new DBerrep();
    }
    
    @Before
    public void ezabatuInsertatutakoGustokoak() {
      
        DBerrep dberrep = new DBerrep();
        dberrep.EzabatuInsertatutakoGustokoak("gollum", "Buleria");
    }
   
    
    @Test
    public void testInsertatuGustukoaTrue() {
    DBerrep dberrep = new DBerrep();
    boolean emaitza = dberrep.insertatuGustokoa("gollum", "Buleria");
    assertTrue(emaitza);
    } 
    
    
    
    @Test
    public void testInsertatuGustokoaFalse() {
        DBerrep dberrep = new DBerrep(); 
        boolean emaitza = dberrep.insertatuGustokoa("kk", "Saoko");

     assertFalse(emaitza);     
    }
    
    @Test
    public void testGustokoaDuTrue() {
    DBerrep dberrep = new DBerrep();
    boolean emaitza = dberrep.gustokoaDu("frodo123", "Saoko");
    assertTrue(emaitza);
    }
    
    @Test
    public void testGustokoaDuFalse() {
    DBerrep dberrep = new DBerrep();
    boolean emaitza = dberrep.gustokoaDu("kk", "Saoko");
    assertFalse(emaitza);
    }
    
    @Test
    public void TestPremiumDa() {
	DBerrep dberrep = new DBerrep();
	boolean emaitza = dberrep.premiumDa("frodo123");
	assertTrue(emaitza);
    	
    }
    
    @Test
    public void TestEZPremium() {
	DBerrep dberrep = new DBerrep();
	boolean emaitza = dberrep.premiumDa("mariag");
	assertFalse(emaitza);
    	
    }
    
    @Test
    public void testPremiumDaConUsuarioInexistente() {
    	DBerrep dberrep = new DBerrep();
        boolean emaitza = dberrep.premiumDa("jj");

        assertFalse(emaitza); 
    }
    
    @Test
    public void testInsertatuErrep() {
    	DBerrep dberrep = new DBerrep();
        int idAudio = 11; 
        int idBezero = 2; 
        boolean emaitza = dberrep.insertatuErrep(idAudio, idBezero);   
        
        assertTrue(emaitza);
        
    }
    
    


}