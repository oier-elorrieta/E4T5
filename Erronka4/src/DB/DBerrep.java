package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Blob;

public class DBerrep {

	/**
	 * Premium taulan zein erabiltzaile eta noizarte premium den ezartzen du
	 * 
	 * @param username
	 * @param dataPremium
	 * @return
	 */
	public boolean insertatuGustokoa(String username, String abestiIzena) {
		boolean insertatuta = false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		String erabiltzaile = "admin";
		String psw = "admin";

		String sql = "INSERT INTO gustokoak (idBezero, idAudio) VALUES (?, ?)";

		DBuser dbuser = new DBuser();
		DBmusika dbMusika = new DBmusika();

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,
				psw);

				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, dbuser.lortuUserId(username));
			pstmt.setNString(2, dbMusika.lortuAbestiId(abestiIzena));

			int filasAfectadas = pstmt.executeUpdate();
			System.out.println("Afektatutako ilara: " + filasAfectadas);
			insertatuta = true;

		} catch (SQLException e) {
			System.out.println("Datu basera konektatzean Errorea.");
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return insertatuta;

	}

	// Erabiltzaileak kanta gustokoa duen begiratzen du
	public boolean gustokoaDu(String username, String abestiIzena) {
		boolean gustokoaDu = true;

		DBmusika dbMusika = new DBmusika();
		Konexioa konexioa2 = new Konexioa();
		DBuser dbUser = new DBuser();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;

		try {
			String kontsulta = "select count(idAudio) from gustokoak where idAudio = '"
					+ dbMusika.lortuAbestiId(abestiIzena) + "' and idBezero ='" + dbUser.lortuUserId(username) + "'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs.next();
			if (rs.getInt(1) == 1) {

			} else {
				gustokoaDu = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		konexioa2.deskonektatu();
		return gustokoaDu;
		
	}

	public boolean premiumDa(String username) {
		boolean premiumDa = false;

		Konexioa konexioa2 = new Konexioa();
		DBuser dbUser = new DBuser();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;

		try {
			String kontsulta = "Select count(idBezero) from premium where idBezero ='"+dbUser.lortuUserId(username)+"'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
			if (rs.getInt(1) == 1) {
				premiumDa = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return premiumDa;
	}
	
	public Blob irudiaLortu(String selectedAbestia) {
		Blob irudia = null;
	
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;

		try {
			String kontsulta = "Select * from audioa where izena='"+selectedAbestia+"'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
			irudia = (Blob) rs.getBlob("irudia");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return irudia;
	}

}
