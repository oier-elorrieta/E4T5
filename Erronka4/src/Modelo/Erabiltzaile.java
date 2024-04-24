package Modelo;

import java.util.Date;
import java.util.Objects;

public abstract class Erabiltzaile {

	protected String izena;
	protected String abizena;
	protected String erabiltzailea;
	protected String pasahitza;
	protected String jaiotze_data;
	
	public Erabiltzaile(String izena, String abizena, String erabiltzailea, String pasahitza,
			String jaiotze_data) {
		
		this.izena = izena;
		this.abizena = abizena;
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.jaiotze_data = jaiotze_data; 
	}

	

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public String getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getJaiotze_data() {
		return jaiotze_data;
	}

	public void setJaiotze_data(String jaiotze_data) {
		this.jaiotze_data = jaiotze_data;
	}

	

	@Override
	public String toString() {
		return "Erabiltzaile [izena=" + izena + ", abizena=" + abizena + ", erabiltzailea=" + erabiltzailea
				+ ", pasahitza=" + pasahitza + ", jaiotze_data=" + jaiotze_data + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erabiltzaile other = (Erabiltzaile) obj;
		return Objects.equals(abizena, other.abizena) && Objects.equals(erabiltzailea, other.erabiltzailea)
				&& Objects.equals(pasahitza, other.pasahitza)
				&& Objects.equals(izena, other.izena) && Objects.equals(jaiotze_data, other.jaiotze_data);
				
	}

	
	
	
	
}