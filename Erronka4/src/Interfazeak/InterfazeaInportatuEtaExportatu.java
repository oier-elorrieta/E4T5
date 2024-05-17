package Interfazeak;

import Modelo.LogeazioDatuak;
import Modelo.Playlist;

public interface InterfazeaInportatuEtaExportatu {
	
	boolean exportatuPlaylist(Playlist playList);
	
	boolean inportatu(LogeazioDatuak logData);
}