package Modelo;

import java.sql.Blob;

public class Podcast {
	
	private String kolaboratzaile;
	private String izenburua;
	private String iraupena;
	
	public Podcast(String kolaboratzaile, String izenburua, String iraupena) {
		this.kolaboratzaile = kolaboratzaile;
		this.izenburua = izenburua;
		this.iraupena = iraupena;
	}

	public String getKolaboratzaile() {
		return kolaboratzaile;
	}

	public void setKolaboratzaile(String kolaboratzaile) {
		this.kolaboratzaile = kolaboratzaile;
	}

	public String getIzenburua() {
		return izenburua;
	}

	public void setIzenburua(String izenburua) {
		this.izenburua = izenburua;
	}

	public String getIraupena() {
		return iraupena;
	}

	public void setIraupena(String iraupena) {
		this.iraupena = iraupena;
	}

	@Override
	public String toString() {
		return izenburua + " | kolaboratzailea/k=" + kolaboratzaile +  " | iraupena=" + iraupena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iraupena == null) ? 0 : iraupena.hashCode());
		result = prime * result + ((izenburua == null) ? 0 : izenburua.hashCode());
		result = prime * result + ((kolaboratzaile == null) ? 0 : kolaboratzaile.hashCode());
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
		Podcast other = (Podcast) obj;
		if (iraupena == null) {
			if (other.iraupena != null)
				return false;
		} else if (!iraupena.equals(other.iraupena))
			return false;
		if (izenburua == null) {
			if (other.izenburua != null)
				return false;
		} else if (!izenburua.equals(other.izenburua))
			return false;
		if (kolaboratzaile == null) {
			if (other.kolaboratzaile != null)
				return false;
		} else if (!kolaboratzaile.equals(other.kolaboratzaile))
			return false;
		return true;
	}
	
	
	
}
