package Modelo;

import java.util.Objects;

public class Abestiak {
	protected int IdMusikari;
	protected int IdAudio;
	protected int Iraupena;
	protected String Izenburua;

	// KONSTRUKTOREA //
	
	public Abestiak(int idMusikari, int idAudio, int iraupena, String izenburua) {
		IdMusikari = idMusikari;
		IdAudio = idAudio;
		Iraupena = iraupena;
		Izenburua = izenburua;
	}

	// GETTERRAK ETA SETTERRAK //
	
	public int getIdMusikari() {
		return IdMusikari;
	}

	public void setIdMusikari(int idMusikari) {
		IdMusikari = idMusikari;
	}

	public int getIdAudio() {
		return IdAudio;
	}

	public void setIdAudio(int idAudio) {
		IdAudio = idAudio;
	}

	public int getIraupena() {
		return Iraupena;
	}

	public void setIraupena(int iraupena) {
		Iraupena = iraupena;
	}

	public String getIzenburua() {
		return Izenburua;
	}

	public void setIzenburua(String izenburua) {
		Izenburua = izenburua;
	}

	// EQUALS //
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abestiak other = (Abestiak) obj;
		return IdAudio == other.IdAudio && IdMusikari == other.IdMusikari && Iraupena == other.Iraupena
				&& Objects.equals(Izenburua, other.Izenburua);
	}

	// TOSTRING //
	
	@Override
	public String toString() {
		return "Abestiak [IdMusikari=" + IdMusikari + ", IdAudio=" + IdAudio + ", Iraupena=" + Iraupena + ", Izenburua="
				+ Izenburua + "]";
	}

}
