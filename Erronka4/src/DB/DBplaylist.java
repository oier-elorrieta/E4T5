package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import Modelo.Abestia;
import Modelo.Playlist;
import Modelo.LogeazioDatuak;
import Modelo.Musikaria;

public class DBplaylist {
	
	/**
	 * Erabiltzaile baten playList guztiak lortzen ditu
	 * @param logData Erabiltzailearen datuak gordetzen dituen parametroa
	 * @return ArrayList<Playlist> Playlist guztien ArrayList-a 
	 */
	public ArrayList<Playlist> lortuPlaylist(LogeazioDatuak logData) {
		ArrayList<Playlist> playlistList = new ArrayList<>();

		DBuser dbUser = new DBuser();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		konexioa2.konektatu();
		Statement stm;
		ResultSet rs;

		try {
			String kontsulta = "select * from playlist where idBezero = '" + dbUser.lortuUserId(logData.getLogeatuta())
					+ "'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Playlist playlist = new Playlist(rs.getInt("idList"), rs.getString("izena"),
						rs.getString("sorreraData"));
				playlistList.add(playlist);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return playlistList;
	}
	
	/**
	 * Erabiltzaile zehatz baten playlist bat ezabatzen du
	 * @param playListIzena String playListaren izen berria
	 * @param logData LogeazioDatuak Logeatuta dagoen erabiltzailearen datuak
	 */
	public void insertPlaylist(String playListIzena, LogeazioDatuak logData) {

		DBuser dbUser = new DBuser();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		Statement stm;
		try {
			String insert = "INSERT INTO playlist (sorreraData, idBezero, izena) VALUES (NOW(),'"
					+ dbUser.lortuUserId(logData.getLogeatuta()) + "','" + playListIzena + "')";
			stm = konexioa.createStatement();
			int filasAfectadas = stm.executeUpdate(insert);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			konexioa2.deskonektatu();
		}
	}
	
	/**
	 * Playlist bat ezabatzen du
	 * @param idList Ezabatu beharreko playListaren id-a
	 * @return ondo ezabatzen bada
	 * 
	 */
	public boolean ezabatuPlaylist(int idList) {
		boolean ezabatuta = false;
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		PreparedStatement pstmt = null;

		try {
			String deleteQuery = "DELETE FROM playlist where idList ='" + idList + "'";
			pstmt = konexioa.prepareStatement(deleteQuery);
			pstmt.executeUpdate();
			ezabatuta = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ezabatuta;
	}
	
	/**
	 * Playlist zehatz batean kanta bat insertatzen du
	 * @param abestia Abestia autatutako abestia
	 * @param idPlaylist int Autatutako playListaren id-a
	 * @return ondo insertatzen da
	 */
	public boolean insertKantaPlaylistean(Abestia abestia, int idPlaylist){
		boolean insertatuDa = false;

		DBmusika dbMusika = new DBmusika();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();

		Statement stm;
		try {
			String sql = "insert into playlistAbestiak(idAudio,idList) values('"
					+ dbMusika.lortuAbestiId(abestia.getIzenburua()) + "','" + idPlaylist + "')";
			stm = konexioa.createStatement();
			stm.executeUpdate(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} 
		konexioa2.deskonektatu();	
		return insertatuDa;
	}
	
	/**
	 * PlayList zehatz baten abesti guztiak lortzen ditu
	 * @param idList int Beharrezko playList-a
	 * @return ArrayList<Abestia> Playlisteko abesti guztiak
	 */
	public ArrayList<Abestia> lortuAbestiak(int idList) {
		ArrayList<Abestia> abestiList = null;
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;

		try {
			String kontsulta = "select * \r\n" + "from abestiErrepHandienak join audioa using(idAudio)\r\n"
					+ " join abestia s using(idAudio)\r\n" + "    join playlistAbestiak a using (idAudio)\r\n"
					+ "where mota = 'Abestia' and idList='" + idList + "'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Abestia abesti = new Abestia(rs.getInt("idAudio"), rs.getString("iraupena"), rs.getString("izena"),
						(Blob) rs.getBlob("irudia"));
				abestiList.add(abesti);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return abestiList;
	}
	
	/**
	 * Kanta bat playlist batetik ezabatzen du
	 * @param idList int playList-aren di-a
	 * @param idAudio int kantaren audioaren id-a
	 * @return ondo ezabatu da
	 */
	public boolean ezabatuKantaPlaylistik(int idList, int idAudio) {
		boolean ezabatuta = false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		PreparedStatement pstmt = null;

		try {
			String deleteQuery = "DELETE FROM playlistAbestiak where idList ='" + idList + "' and idAudio ='" + idAudio
					+ "'";
			pstmt = konexioa.prepareStatement(deleteQuery);
			pstmt.executeUpdate();
			ezabatuta = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ezabatuta;
	}
	
	/**
	 * Erabiltzaileak playListBerriak sortu ditzakeen lortzen du
	 * @param idBezero
	 * @return
	 */
	public boolean playListakSortuDezake(int idBezero) {
		boolean sortuDitzake=false;
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm = null;
		PreparedStatement pstmt = null;
		
		try {
			String kontsulta = "SELECT * FROM playListErabiltzaile join bezeroDatuak using(idBezero) join playlist using(idBezero) where idBezero='"+idBezero+"'";
			pstmt = konexioa.prepareStatement(kontsulta);
			rs = stm.executeQuery(kontsulta);
			rs.next();
			if(rs.getInt("playlistKopurua")!=3 || rs.getString("mota")=="Premium") {
				sortuDitzake = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return sortuDitzake; 
	}
	
	public boolean abestiaPlaylisteanDago(int idAudio, int idList) {
		boolean playlisteanDago=false;
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		PreparedStatement pstmt = null;
		
		try {
			String kontsulta = "SELECT * FROM db_spotify5.playlistAbestiak where idAudio="+idAudio+" and idList="+idList;
			
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
			rs.next();
			try {
				if(rs.getInt("idList")==idList) {
				playlisteanDago = true;
			}
				}catch(SQLException e2) {
					
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return playlisteanDago;
	}

}