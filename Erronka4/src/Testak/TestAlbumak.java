package Testak;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import Modelo.Albumak;


public class TestAlbumak {

    static Albumak alb1;
    
    @Before
    public void setUp() throws Exception {
    alb1 = new Albuma(1, "izenburua",2024, "pop",1,3);
    }
    
    
    public void TestGetIdAlbum(){
    assertEquals(1, alb1.getIdAlbum());
    }
    
    public void TestSetIdAlbum() {
        alb1.setIdAlbum(2);
        assertEquals(2, alb1.getIdAlbum());
    }

    public void TestGetIzenburua() {
        assertEquals("izenburua", alb1.getIzenburua());
    }
    
    public void TestSetIzenburua() {
        alb1.setIzenburua("Lemonade");
        assertEquals("Lemonade", alb1.getIzenburua());
    }
    
    public void TestGetUrtea() {
        assertEquals(2024, alb1.getUrtea());
    }
    
    public void TestSetUrtea() {
        alb1.setUrtea(2023);
        assertEquals(2023, alb1.getUrtea());
    }
    
    public void TestGetGeneroa() {
        assertEquals("pop", alb1.getGeneroa());
    }
    
    public void TestSetGeneroa() {
        alb1.setGeneroa("k-pop");
        assertEquals("k-pop", alb1.getGeneroa());
    }
    
    public void TestGetIdArtista() {
        assertEquals(1, alb1.getIdArtista());
    }
    
    public void TestSetIdArtista() {
        alb1.setIdArtista(2);
        assertEquals(2, alb1.getIdArtista());
    }
    
    public void TestGetAbestiKop() {
        assertEquals(1, alb1.getAbestiKop());
    }
    
    public void TestSetAbestiKop() {
        alb1.setAbestiKop(2);
        assertEquals(2, alb1.getAbestiKop());
    }
}