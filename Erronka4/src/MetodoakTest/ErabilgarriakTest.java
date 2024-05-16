package MetodoakTest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.swing.JPasswordField;

import org.junit.Before;
import org.junit.Test;

import Metodoak.Erabilgarriak;
import Modelo.Abestia;

public class ErabilgarriakTest {


	@Before
	public void setUp() throws Exception {
	}
	
	
	@Test
	public void getDatagaurTest() {
	Erabilgarriak erabilgarriak = new Erabilgarriak();
	String emaitza = erabilgarriak.getDataGaur();
	assertEquals(LocalDate.now().toString(),emaitza);
	}
	
	@Test 
	public void urteaGehituTest() {
	Erabilgarriak erabilgarriak = new Erabilgarriak();
	String hasierakoData = "2023-10-12";	
	String esperatutakoData ="2024-10-12";
	
	String emaitza = erabilgarriak.urteaGehitu(hasierakoData);
	assertEquals(esperatutakoData,emaitza); 
		
	}
	
	@Test
	public void DatabalidatuTestTrue() {
	Erabilgarriak erabilgarriak = new Erabilgarriak();
	String balidatutakoData ="2023-10-12";
	
	boolean emaitza = erabilgarriak.dataBalidatu(balidatutakoData);
	assertTrue(emaitza);
	
	}
	
	@Test
	public void DatabalidatuTestFalse() {
	Erabilgarriak erabilgarriak = new Erabilgarriak();
	String inbalidatutakoData ="2050-05-13";
	
	boolean emaitza = erabilgarriak.dataBalidatu(inbalidatutakoData);
	assertFalse(emaitza);
	
	}
	
	@Test
	public void PasahitzBerdinakTestTrue() {
	JPasswordField passwordField1 = new JPasswordField("123");
	JPasswordField passwordField2 = new JPasswordField("123");
	
	Boolean emaitza = Erabilgarriak.pasahitzBerdinak(passwordField1, passwordField2);
	assertTrue(emaitza);
	}
	
	@Test
	public void PasahitzBerdinakTestFalse() {
	JPasswordField passwordField1 = new JPasswordField("123");
	JPasswordField passwordField2 = new JPasswordField("50");
	
	Boolean emaitza = Erabilgarriak.pasahitzBerdinak(passwordField1, passwordField2);
	assertFalse(emaitza);
	}
	
	@Test
	public void DenboraMinututanTest() {
	int segundoak = 125;
	String esperatutakoEmaitza = "2 : 5";
	
	String emaitza = Erabilgarriak.lortuDenboraMinutuetan(segundoak);
	assertEquals(esperatutakoEmaitza,emaitza);
	}
	
	
	

}