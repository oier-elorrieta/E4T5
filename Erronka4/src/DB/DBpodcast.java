package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import Modelo.Playlist;
import Modelo.Podcast;
import Modelo.Podcasterra;

public class DBpodcast {
	
	/**
	 *Podcaster zehatz batek dituen podcast guztiak lortzen ditu 
	 * @param podcaster Podcasterraren izena
	 * @return ArrayList<Podcast> podcast guztiak bueltatzen ditu
	 */
	public ArrayList<Podcast> lortuPodcast(String podcaster) {
		ArrayList<Podcast> podcastList = new ArrayList<>();

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		konexioa2.konektatu();
		Statement stm;
		ResultSet rs;
 
		try {
			String kontsulta = "SELECT * FROM db_spotify5.podcaster join podcast using(idPodcaster) join audioa using(idAudio) where izenArtistikoa = '"
					+ podcaster + "'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Podcast podcast = new Podcast(rs.getString("kolaboratzaileak"), rs.getString("izena"),
						rs.getString("iraupena"));
				podcastList.add(podcast);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return podcastList;
	}

	/**
	 * Podcasterr guztiak lortzen ditu
	 * @return ArrayList<Podcasterra> podcaster guztiak lortzen ditu
	 */
	public ArrayList<Podcasterra> podcasterDeskubritu() {
		ArrayList<Podcasterra> podcasterList = new ArrayList<>();
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		konexioa2.konektatu();
		Statement stm;
		ResultSet rs;

		try {
			String kontsulta = "select * from podcaster";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

			while (rs.next()) {
				Podcasterra podcaster = new Podcasterra(rs.getString("izenArtistikoa"), rs.getString("deskribapena"),
						(Blob) rs.getBlob("irudia"));
				podcasterList.add(podcaster);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return podcasterList;
	}
	
	/**
	 * Podcast zehazt baten id-a lortzen du
	 * @param podcast Podcast objektua
	 * @return Objektuaren podcastaren id-a 
	 */
    public String lortuPodcastId(Podcast podcast) {
		
		String id = null ;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		
		try {
			String kontsulta = "select idAudio from audioa where izena='"+podcast.getIzenburua()+"'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return id;
		
	}
	

}
