package JUnit;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Modelo.UserFree;

public class TestFree {

	// SETUP BAT TESTAK EGITEKO //
	
	static UserFree f1;
	@Before
	public void setUp() throws Exception {
	String data = "2024-04-18";
	 f1 = new UserFree("izena","Abizena","Erabiltzailea","Pasahitza",data);
	}
	 
	//TEST GETTERS AND SETERS
	
	@Test
	public void TestGetIzena(){
		assertEquals("izena", f1.getIzena());   
	}
	@Test
	public void TestSetIzena(){
		f1.setIzena("Ibai");
		assertEquals("Ibai", f1.getIzena());
	}
	@Test
	public void TestGetAbizena(){
		assertEquals("Abizena", f1.getAbizena());
	}
	@Test
	public void TestSetAbizena(){
		f1.setAbizena("Martinez");
		assertEquals("Martinez", f1.getAbizena());
	}
	@Test
	public void TestGetErabiltzailea(){
		assertEquals("Erabiltzailea", f1.getErabiltzailea());
	}
	@Test
	public void TestSetErabiltzailea(){
		f1.setErabiltzailea("Ibaferre");
		assertEquals("Ibaferre", f1.getErabiltzailea());
	}
	@Test
	public void TestGetPasahitza(){
		assertEquals("Pasahitza", f1.getPasahitza());
	}
	@Test
	public void TestSetpasahitza(){
		f1.setPasahitza("1234");
		assertEquals("1234", f1.getPasahitza());
	}
	@Test
	public void TestGetData(){
		String data = "2024-04-18";
		assertEquals(data, f1.getJaiotze_data());
	}
	@Test
	public void TestSetData(){
		String data1 = "2024-04-13";
		f1.setJaiotze_data(data1);
		assertEquals(data1, f1.getJaiotze_data());
	}
	
	// TEST TOSTRING //
	
	@Test
	public void TesttoString() {
		String TxtFree = f1.toString();
		String esperotakoaFree = "Erabiltzaile [zena=" + f1.getIzena() + ", Abizena=" + f1.getAbizena() + ", Erabiltzailea="
				+ f1.getErabiltzailea() + ", Pasahitza=" + f1.getPasahitza() + ", Jaiotze_data=" + f1.getJaiotze_data() + "]";
	}
	
	
	//EQUALS TEST//
	
	@Test
	public void TestEqualsTrue(){
	assertTrue(f1.equals(f1));	
	}
	
	@Test
	public void TestEqualsNull(){
	assertFalse(f1.equals(null));	
	}
	
	@Test
	public void TestEqualsTxarto(){
	String txarto = "aa";
	assertFalse(f1.equals(txarto));		}

	@Test
	public void TestEquals(){
	String data = "2024-04-18";
	UserFree f2 = new UserFree("Jon","Abizena","Erabiltzailea","Pasahitza",data);
	assertFalse(f1.equals(f2));
	}
	
	@Test
	public void TestequalsData(){
	String data = "2024-04-13";
	UserFree f2 = new UserFree("Izena","Abizena","Erabiltzailea","Pasahitza",data);
	assertFalse(f1.equals(f2));
	}
}
