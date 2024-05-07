package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import Modelo.Podcast;
import Modelo.Podcasterra;

public class DBpodcast {

	public ArrayList<Podcast> lortuPodcast(String selectedPodcaster) {
		ArrayList<Podcast> podcastList = new ArrayList<>();

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		konexioa2.konektatu();
		Statement stm;
		ResultSet rs;
		
		try {
			String kontsulta = "SELECT * FROM db_spotify5.podcaster join podcast using(idPodcaster) join audioa using(idAudio) where izenArtistikoa = '"
					+ selectedPodcaster + "'";
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
	 * 
	 * @return
	 */
	public ArrayList<Podcasterra> podcastDeskubritu() {
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

}
