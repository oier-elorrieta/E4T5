package Modelo;

import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import DB.DBerrep;

public class ErreprodukzioAbestia {
	
	private ArrayList<Abestia> abestiList;
	private int posizioa;
	
	public ErreprodukzioAbestia(String selectedAlbum, String selectedAbestia) {
		DB.DBmusika dbmusika = new DB.DBmusika();
		this.abestiList=dbmusika.lortuAbestiak(selectedAlbum);
		
		for(int i=0;i<abestiList.size();i++) {
			if(abestiList.get(i).getIzenburua().equals(selectedAbestia)) {
				this.posizioa=i;
				}	
		} 
	}
	
	public Abestia hurrengoKantara() {
		if(abestiList.size()-1!=posizioa) {
			this.posizioa=posizioa+1;
		}else {
			this.posizioa=0;
		}
		return abestiList.get(posizioa);
	}

	public Abestia aurrekoKantara() {
		if(this.posizioa!=0) {
			this.posizioa=this.posizioa-1;
		}else {
			this.posizioa=abestiList.size()-1;
		}
		return abestiList.get(posizioa);
	}
	
	public Blob getIrudia() {
		Blob irudia;
		DBerrep dbErrep = new DBerrep();
		irudia = dbErrep.irudiaLortu(getAbesti());
		return irudia;
	}
	
	public String getAbesti() {
		return abestiList.get(posizioa).getIzenburua();
	}
	
	public ArrayList<Abestia> getAbestiList() {
		return abestiList;
	}

	public void setAbestiList(ArrayList<Abestia> abestiList) {
		this.abestiList = abestiList;
	}

	public int getPosizioa() {
		return posizioa;
	}

	public void setPosizioa(int posizioa) {
		this.posizioa = posizioa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abestiList == null) ? 0 : abestiList.hashCode());
		result = prime * result + posizioa;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErreprodukzioAbestia other = (ErreprodukzioAbestia) obj;
		if (abestiList == null) {
			if (other.abestiList != null)
				return false;
		} else if (!abestiList.equals(other.abestiList))
			return false;
		if (posizioa != other.posizioa)
			return false;
		return true;
	}
	
	
	
}
