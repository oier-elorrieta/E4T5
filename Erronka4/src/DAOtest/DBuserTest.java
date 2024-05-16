package DAOtest;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DB.DBerrep;
import DB.DBuser;
import Modelo.Erabiltzaile;
import Modelo.Hizkuntza;
import Modelo.Premium;
import Modelo.UserFree;

public class DBuserTest {
    private static DBuser dbUser;
    private static Connection connection;

    
    @BeforeClass
    public static void setUp() {
        dbUser = new DBuser();
       
    }
    
    @Test 
    public void testIsLoginOk_True() {
        try {
            assertTrue(dbUser.isLoginOk("frodo123", "frodopass"));
        } catch (SQLException e) {
            
        }
    }

    @Test
    public void testIsLoginOk_False() {
        try {
            assertFalse(dbUser.isLoginOk("carols123", "123"));
        } catch (SQLException e) {
           
        } 
    }

    @Test
    public void testHizkuntzakLortu() {
        ArrayList<Hizkuntza> result = dbUser.hizkuntzakLortu();
        assertNotNull(result);
    }

    @Test
    public void testDatuakBeteta_True() {
    	 JTextField izena = new JTextField("Frodo");
         JTextField abizena = new JTextField("Baggins");
         JTextField erabiltzailea = new JTextField("frodo123");
         JTextField jaioData = new JTextField("1990-07-19"); 
         JTextField erregData = new JTextField("2020-01-15");

        assertTrue(dbUser.datuakBeteta(izena, abizena, erabiltzailea, jaioData, erregData));
    }

    @Test
    public void testDatuakBeteta_False() {
        JTextField izena = new JTextField("carols");
        JTextField abizena = new JTextField("fdfadfasf");
        JTextField erabiltzailea = new JTextField("carols123");
        JTextField jaioData = new JTextField(""); 
        JTextField erregData = new JTextField("2000-01-15");

        assertFalse(dbUser.datuakBeteta(izena, abizena, erabiltzailea, jaioData, erregData));
    }

    @Test
    public void testErabiltzaileaArtuta_True() {
        assertTrue(dbUser.erabiltzaileaArtuta("frodo123"));
    }

    @Test
    public void testErabiltzaileaArtuta_False() {
        assertFalse(dbUser.erabiltzaileaArtuta("Frodo123124"));
    }
    
   
   
    @Test
    public void testInsertatuBezeroBerria() {
        DBuser dbuser = new DBuser(); 
        Premium user = new Premium("kugggi", "fr", "ikvg", "157", "2005-11-11", "2024-10-11");
        JComboBox hizkuntza = new JComboBox();
        hizkuntza.addItem("hizkuntza");
        JTextField premiumData = new JTextField("2030-09-09");
        boolean emaitza = dbuser.insertatuBezeroBerria(user, hizkuntza, premiumData);

        assertTrue("Erabiltzaile berria ez da insertatu",emaitza); 
    }
    
}