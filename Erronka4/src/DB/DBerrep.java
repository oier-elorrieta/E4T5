package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Blob;

import Metodoak.Erabilgarriak;
import Modelo.Abestia;
import Modelo.Playlist;

public class DBerrep {

	/**
	 * Premium taulan zein erabiltzaile eta noizarte premium den ezartzen du
	 * @param username String erabiltzailea
	 * @param abestiIzena String abestiaren izena
	 * @return ondo insertatu da
	 */ 
	public boolean insertatuGustokoa(String username, String abestiIzena){
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

			pstmt.executeUpdate();

			insertatuta = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return insertatuta;

	}

	/**
	 * erabiltzailea abestia gustokoa duen lortzen du
	 * @param username String erabiltzailea
	 * @param abestiIzena String abestiaren izena
	 * @return erabiltzailea gustokoa du abestia
	 */
	public boolean gustokoaDu(String username, String abestiIzena){
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		konexioa2.deskonektatu();
		return gustokoaDu;

	}

	/**
	 * Erabiltzailea premium den egiaztatzen du
	 * @param username Erabiltzailearen izena
	 * @return Erabiltzailea Premium bada true bueltatzen du beztela false
	 */
	public boolean premiumDa(String username) {
		boolean premiumDa = false;

		Konexioa konexioa2 = new Konexioa();
		DBuser dbUser = new DBuser();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;

		try {
			String kontsulta = "Select count(idBezero) from premium where idBezero ='" + dbUser.lortuUserId(username)+"'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
		
			if (rs.getInt("count(idBezero)") == 1) {
				premiumDa = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		konexioa2.deskonektatu();
		return premiumDa;
	}

	/**
	 * Abestiaren irudia lortzen du
	 * 
	 * @param abestia Abestiaren izena(Datu basean duen izena)
	 * @return Irudia bueltatzen du
	 */
	public Blob irudiaLortu(String abestia) {
		Blob irudia = null;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;

		try {
			String kontsulta = "Select * from audioa where izena='" + abestia + "'";
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

	/**
	 * Datu basean erreprodukzioa insertatzen du
	 * 
	 * @param idAudio  Erreproduzitu den audioaren id-a
	 * @param idBezero Audioa erreproduzitu duen erabiltzailearen id-a
	 * @return Insert-a ondo egin bada true bueltatzen du beztela false
	 */
	public boolean insertatuErrep(int idAudio, int idBezero) {
		boolean insertaEginda = false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();

		Statement stm;
		try {
			String sql = "insert into erreprodukzioak(idBezero,idAudioa,errepData) values('" + idBezero + "','"
					+ idAudio + "','" + Erabilgarriak.getDataGaur() + " " + Erabilgarriak.unekoOrduaLortu() + "')";
			stm = konexioa.createStatement();
			stm.executeUpdate(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			konexioa2.deskonektatu();
		}

		return insertaEginda;
	}

	/**
	 * Erabiltzaile baten gustoko duen abesti bat ezabatzen du
	 * @param username String erabiltzailearen erabiltzailea
	 * @param abestiIzena String abestiaren izena
	 */
	public void EzabatuInsertatutakoGustokoak(String username, String abestiIzena) {

		String erabiltzaile = "admin";

		String psw = "admin";

		String sql = "DELETE FROM gustokoak WHERE idBezero = ? AND idAudio = ?";

		DBuser dbuser = new DBuser();

		DBmusika dbMusika = new DBmusika();

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,
				psw);

				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, dbuser.lortuUserId(username)); //

			pstmt.setNString(2, dbMusika.lortuAbestiId(abestiIzena));

			int filasAfectadas = pstmt.executeUpdate();

		} catch (SQLException e) {


			e.printStackTrace();

		}

	}

}
