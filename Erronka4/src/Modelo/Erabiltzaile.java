package Modelo;

import java.util.Date;
import java.util.Objects;

public abstract class Erabiltzaile {

	protected String Izena;
	protected String Abizena;
	protected String Erabiltzailea;
	protected String Pasahitza;
	protected String Jaiotze_data;
	
	public Erabiltzaile(String izena, String abizena, String erabiltzailea, String pasahitza,
			String jaiotze_data) {
		
		Izena = izena;
		Abizena = abizena;
		Erabiltzailea = erabiltzailea;
		Pasahitza = pasahitza;
		Jaiotze_data = jaiotze_data;
	}

	public String getIzena() {
		return Izena;
	}

	public void setIzena(String izena) {
		Izena = izena;
	}

	public String getAbizena() {
		return Abizena;
	}

	public void setAbizena(String abizena) {
		Abizena = abizena;
	}

	public String getErabiltzailea() {
		return Erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		Erabiltzailea = erabiltzailea;
	}

	public String getPasahitza() {
		return Pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		Pasahitza = pasahitza;
	}

	public String getJaiotze_data() {
		return Jaiotze_data;
	}

	public void setJaiotze_data(String jaiotze_data) {
		Jaiotze_data = jaiotze_data;
	}

	@Override
	public String toString() {
		return "Erabiltzaile [Izena=" + Izena + ", Abizena=" + Abizena + ", Erabiltzailea="
				+ Erabiltzailea + ", Pasahitza=" + Pasahitza + ", Jaiotze_data=" + Jaiotze_data + "]";
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
		return Objects.equals(Abizena, other.Abizena) && Objects.equals(Erabiltzailea, other.Erabiltzailea)
			&& Objects.equals(Izena, other.Izena)
				&& Objects.equals(Jaiotze_data, other.Jaiotze_data) && Objects.equals(Pasahitza, other.Pasahitza);
	}
	
	
	
}