package Metodoak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DB.DBmusika;
import DB.DBplaylist;
import DB.DBuser;
import Modelo.Abestia;
import Modelo.LogeazioDatuak;
import Modelo.Playlist;

public class InportatuExportatu implements Interfazeak.InterfazeaInportatuEtaExportatu {

	public boolean exportatuPlaylist(Playlist playList) {

		boolean inportatuta = false;
		String karpetarenIzena = "C:\\Playlist Exportatu";
		File karpeta = new File(karpetarenIzena);

		if (!karpeta.exists()) {
			karpeta.mkdirs();

		}

		String karpetarenRuta = karpetarenIzena + "//" + playList.getIzenburua() + ".txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(karpetarenRuta))) {
			writer.write("Playlista: " + playList.getIzenburua() + " (id='" + playList.getIdlist() + "')");
			inportatuta = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return inportatuta;

	}

	@Override
	public boolean inportatu(LogeazioDatuak logData) {
		boolean inportatuta = false;
		File bidea = new File("C:\\Users\\in1dm3-d\\Desktop\\Ibai Martinez DAM\\Erronka_4\\inport.txt");
		DB.DBplaylist dbPlaylist = new DB.DBplaylist();
		DB.DBuser dbUser = new DB.DBuser();
		DB.DBmusika dbMusika = new DB.DBmusika();
		try {
			FileReader fr = new FileReader(bidea);
			BufferedReader br = new BufferedReader(fr);

			String lerroa;
			lerroa = br.readLine();

			String[] Datuak = new String[lerroa.split("-").length];

			Datuak = lerroa.split("-");

			// 0 posizioa PlayListaren id-ari dagokio 
			dbPlaylist.insertPlaylist(Datuak[0], logData);

			for (int i = 1; i < Datuak.length; i++) {

				Abestia abestia = dbMusika.lortuAbestiaIDarekin(Integer.parseInt(Datuak[i]));
				// falla prk datuak 0 es un string asik tengo k hcer funcion que me devuelva el
				// id de la ultima playlist creada de un usuario
				dbPlaylist.insertKantaPlaylistean(abestia, Integer.parseInt(Datuak[0]));
			}
			inportatuta = true;
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inportatuta;
	}

	public boolean exportatuAbestia(Abestia abestia) {

		boolean inportatuta = false;
		String karpetarenIzena = "C:\\Abestia Exportatu";
		File karpeta = new File(karpetarenIzena);

		if (!karpeta.exists()) {
			karpeta.mkdirs();
		}

		String karpetarenRuta = karpetarenIzena + "//" + abestia.getIzenburua() + ".txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(karpetarenRuta))) {
			writer.write(abestia.toStringErrep());
			inportatuta = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return inportatuta;

	}

}