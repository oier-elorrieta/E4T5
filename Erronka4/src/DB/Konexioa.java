package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.jdbc.Blob;

public class Konexioa {
	
	private String url = "jdbc:mysql://10.5.6.67:3306/db_spotify5";
	private String erabiltzaile = "admin";
	private String psw = "admin";
	Connection konexioa = null;
	
	/**
	 * Datu basera konexioa egiten du
	 * 
	 * @return Connection konexioa
	 */
	// Datu-basearekin konexioa egiteko metodoa
	public  Connection konektatu() {
		 Connection konexioa = null;
		try {

			// Konexioa sortu, oraindik ez badago
			if (konexioa == null || konexioa.isClosed()) {

				konexioa = DriverManager.getConnection(this.url, this.erabiltzaile, this.psw);
			}
		} catch (SQLException e) {
		}
		
		return konexioa;
		
	}
	
	/**
	 * Datu basetik deskonektatzen da
	 */
	// Datu-basearekin konexioa egiteko metodoa
	public void deskonektatu() {

		try {
			// Konexioa deskonektatu
			if (konexioa != null ) {		
				konexioa.close();	
			}
		} catch (SQLException e) {
		}
	
	}
}
