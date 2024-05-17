package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

import Modelo.Abestia;
import Modelo.Playlist;
import Modelo.LogeazioDatuak;
import Modelo.Musikaria;

public class DBplaylist {

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

	public void insertPlaylist(String PlaylistIzena, LogeazioDatuak logData) {

		DBuser dbUser = new DBuser();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		Statement stm;
		try {
			String insert = "INSERT INTO playlist (sorreraData, idBezero, izena) VALUES (NOW(),'"
					+ dbUser.lortuUserId(logData.getLogeatuta()) + "','" + PlaylistIzena + "')";
			stm = konexioa.createStatement();
			int filasAfectadas = stm.executeUpdate(insert);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			konexioa2.deskonektatu();
		}
	} 

	public boolean ezabatuPlaylist(int idList) {
		boolean ezabatuta=false;
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		PreparedStatement pstmt = null;

		try {
			String deleteQuery = "DELETE FROM playlist where idList ='" + idList + "'";
			pstmt = konexioa.prepareStatement(deleteQuery);
			pstmt.executeUpdate();
			ezabatuta=true;
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return ezabatuta;
	}

	public boolean insertKantaPlaylistean(Abestia selectedAbestia, int idPlaylist) {
		boolean insertatuDa = false;

		DBmusika dbMusika = new DBmusika();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();

		Statement stm;
		try {
			String sql = "insert into playlistAbestiak(idAudio,idList) values('"
					+ dbMusika.lortuAbestiId(selectedAbestia.getIzenburua()) + "','" + idPlaylist + "')";
			stm = konexioa.createStatement();
			stm.executeUpdate(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			konexioa2.deskonektatu();
		} 
		return insertatuDa;
	}

	public ArrayList<Abestia> lortuAbestiak(int idList) {
		ArrayList<Abestia> abestiList = new ArrayList<Abestia>();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		
		try {
			String kontsulta = "select * from abestiErrepHandienak join audioa using(idAudio) join playlistAbestiak using(idAudio) where idList='" + idList + "'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Abestia abesti = new Abestia(rs.getInt("idAudio"), rs.getString("iraupena"), rs.getString("izena"),(Blob) rs.getBlob("irudia"),rs.getInt("erreprodukzioKopurua"));
				abestiList.add(abesti);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return abestiList;
	}

	public boolean ezabatuKantaPlaylistik(int idList, int idAudio) {
		boolean ezabatuta=false;
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		PreparedStatement pstmt = null;
		
		try {
			String deleteQuery = "DELETE FROM playlistAbestiak where idList ='" + idList + "' and idAudio ='"+idAudio+"'";
			pstmt = konexioa.prepareStatement(deleteQuery);
			pstmt.executeUpdate();
			ezabatuta=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ezabatuta; 
	}
	
	public boolean kantaPlaylisteanDago(int idList, int idAudio) {
		boolean badago=false;
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		PreparedStatement pstmt = null;
		
		try {
			String deleteQuery = "select count(*) FROM playlistAbestiak where idList ='" + idList + "' and idAudio ='"+idAudio+"'";
			pstmt = konexioa.prepareStatement(deleteQuery);
			pstmt.executeUpdate();
			rs.next();
			if(rs.getInt("count(*)")==1) {
				badago=true;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return badago;
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
            }else {
            	JOptionPane.showMessageDialog( null, "Errorea, ezin duzu playlist gehiago sortu Free zarelako", "Kontuz!!",
						JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return sortuDitzake; 
    } 
	
	
	
}