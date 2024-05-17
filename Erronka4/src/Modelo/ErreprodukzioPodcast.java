package Modelo;

import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import DB.DBerrep;
import DB.DBpodcast;

public class ErreprodukzioPodcast {
	
	private ArrayList<Podcast> podcastList;
	private int posizioa;
	
	public ErreprodukzioPodcast(String autatutaPodcaster, String podcast) {
		DBpodcast dbPodcast= new DBpodcast();
		this.podcastList=dbPodcast.lortuPodcast(autatutaPodcaster);
		
		for(int i=0;i<podcastList.size();i++) {
			if(podcastList.get(i).getIzenburua().equals(podcast)) {
				this.posizioa=i;
				}	
		}
	}
	
	public Podcast hurrengoPodcastera() {
		if(podcastList.size()-1!=posizioa) {
			posizioa=posizioa+1;
		}else {
			posizioa=0;
		}
		return podcastList.get(posizioa);
	}

	public Podcast aurrekoPodcastera() {
		if(posizioa!=0) {
			posizioa=posizioa-1;
		}else {
			posizioa=podcastList.size()-1;
		}
		return podcastList.get(posizioa);
	}
	
	public Blob getIrudia() {
		Blob irudia;
		DBerrep dbErrep = new DBerrep();
		irudia = dbErrep.irudiaLortu(getAbesti());
		return irudia;
	}
	
	public String getAbesti() {
		return podcastList.get(posizioa).getIzenburua();
	}
	
	public ArrayList<Podcast> getAbestiList() {
		return podcastList;
	}

	public void setAbestiList(ArrayList<Podcast> podcastList) {
		this.podcastList = podcastList;
	}

	public int getPosizioa() {
		return posizioa;
	}

	public void setPosizioa(int posizioa) {
		this.posizioa = posizioa;
	}
	
	public int podcastKop() {
		return podcastList.size();
	}
}
