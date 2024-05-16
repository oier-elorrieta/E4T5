package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import Modelo.Abestia;
import Modelo.Albuma;
import Modelo.Musikaria;

public class DBmusika {
	
	/**
	 * Musikari guztiak lortzen ditu
	 * @return ArrayList<Musikaria> musikari guztien ArrayLista
	 */
	public ArrayList<Musikaria> musikaDeskrubitu() {
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		
		ArrayList<Musikaria> ArtistaLIst = new ArrayList<>();
		
		try {
			String kontsulta = "select * from musikaria";
			stm =konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Musikaria musikaria = new Musikaria(rs.getString("IzenArtistikoa"),rs.getString("deskribapena"),rs.getBlob("irudia") );
				ArtistaLIst.add(musikaria);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return ArtistaLIst;
	}
	
	/**
	 * Artista zehatz baten album guztiak lortzen ditu
	 * @param aukeratutakoArtista Albumak lortzeko behar duzun artista
	 * @return ArrayList<Albuma> albumen Arraylista
	 */
	public ArrayList<Albuma> Albumlist(String aukeratutakoArtista) {
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs;
		Statement stm;
		
		ArrayList<Albuma> AlbumList = new ArrayList<>();
		
		try {
			
			String kontsulta = "select izenburua,urtea,count(idAudio),deskribapena, generoa from musikaria join album using(idmusikaria) join abestia using(idalbum) join audioa using (idaudio) where izenartistikoa = '"+aukeratutakoArtista+"' group by izenburua, urtea, generoa";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
			while (rs.next()) {

				String izenburua = rs.getString("izenburua");
				int urtea = rs.getInt(2);
				int abestiKop = rs.getInt(3);
				Albuma album = new Albuma(izenburua, urtea, abestiKop, rs.getString("generoa"));
				AlbumList.add(album);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		konexioa2.deskonektatu();
		return AlbumList;
	}
	
	/**
	 * Album zehatz baten abestiak lortzen ditu
	 * @param selectedAlbum albumaren izena
	 * @return ArrayList<Abestia> Albumaren abesti guztiak
	 */
	public ArrayList<Abestia> lortuAbestiak(String selectedAlbum){
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ArrayList<Abestia> abestiList = new ArrayList<>();
		try {
				String kontsulta = "select * \r\n"
					+ "from abestiErrepHandienak join audioa using(idAudio)\r\n"
					+ "	join abestia s using(idAudio)\r\n"
					+ "    join album a using (idAlbum)\r\n"
					+ "where mota = 'Abestia' and a.izenburua='"+selectedAlbum+"'";
			Statement stm = konexioa.createStatement();
			ResultSet rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Abestia abesti = new Abestia(rs.getInt("idAudio"),rs.getString("iraupena"),rs.getString("izena"),(Blob) rs.getBlob("irudia"));
				abestiList.add(abesti);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return abestiList;
	}
	

	/*
	 * Abestiaren ida lortzen du
	 * @param username String abestiaren izena
	 * @return String abestiaren id-a
	 */
	public String lortuAbestiId(String abestiIzena) {
		
		String id = null ;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		
		try {
			String kontsulta = "select idAudio from audioa where izena='"+abestiIzena+"'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				id = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
	

	public Abestia lortuAbestiaIDarekin(int id) {
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		Abestia abestia = null;
		try {
			String kontsulta = "Select * from audioa where idAudio='"+id+"'";
			Statement stm = konexioa.createStatement();
			ResultSet rs = stm.executeQuery(kontsulta);

			rs.next();
			abestia = new Abestia(rs.getInt("idAudio"),rs.getString("iraupena"),rs.getString("izena"),(Blob) rs.getBlob("irudia"));


		} catch (SQLException e) {

			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return abestia;
	}
}
