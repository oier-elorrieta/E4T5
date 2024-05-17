package Modelo;

import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

import DB.DBerrep;

public class Erreprodukzio {
	
	private ArrayList<Abestia> abestiList;
	private int posizioa;
	
	public Erreprodukzio(String selectedAlbum, String selectedAbestia) {
		DB.DBmusika dbmusika = new DB.DBmusika();
		this.abestiList=dbmusika.lortuAbestiak(selectedAlbum);
		System.out.println("selectedAbestia:"+selectedAbestia);
		for(int i=0;i<abestiList.size();i++) {
			System.out.println(abestiList.get(i).getIzenburua());
			if(abestiList.get(i).getIzenburua().equals(selectedAbestia)) {
				System.out.println("Berdina da");
				this.posizioa=i;
				System.out.println(this.posizioa);}
			
		}
	}
	
	public Abestia hurrengoKantara() {
		if(abestiList.size()-1!=posizioa) {
			this.posizioa=posizioa+1;
			System.out.println(posizioa);
		}
		return abestiList.get(posizioa);
	}

	public Abestia aurrekoKantara() {
		if(this.posizioa!=0) {
			this.posizioa=this.posizioa-1;
			System.out.println(posizioa);
		}
		return abestiList.get(posizioa);
	}
	
	public Blob getIrudia() {
		System.out.println("irudia lortzeko -->"+getAbesti());
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
		Erreprodukzio other = (Erreprodukzio) obj;
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
