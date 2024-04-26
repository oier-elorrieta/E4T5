package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBadmin {
	
	private String url = "jdbc:mysql://10.5.6.132:3306/db_spotify5";
	private String erabiltzailea = "admin";
    private String pasahitza = "admin";
    private Connection konexioa = null;
    private String kontsulta;
    private Statement stm;
    private ResultSet rs;
	
	// Datu-basearekin konexioa egiteko metodoa
    public Connection konektatu() {
        
    	try {
    		
            // Konexioa sortu, oraindik ez badago
            if (konexioa == null || konexioa.isClosed()) {
                
                this.konexioa = DriverManager.getConnection(this.url, this.erabiltzailea, this.pasahitza);
                System.out.println("Konektatuta!!!");
            }
        } catch (SQLException e) {
            System.out.println("Errorea datu-basearekin konexioa egiten: " + e.getMessage());
        }
    	return konexioa;
    }
	
   
    

 
    
}
