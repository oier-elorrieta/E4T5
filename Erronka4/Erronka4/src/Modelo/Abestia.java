package Modelo;

import java.util.Objects;

public class Abestia {
	protected int idMusikari;
	protected int idAudio;
	protected int iraupena;
	protected String izenburua;

	// KONSTRUKTOREA //
	
	public Abestia(int idMusikari, int idAudio, int iraupena, String izenburua) {
		this.idMusikari = idMusikari;
		this.idAudio = idAudio;
		this.iraupena = iraupena;
		this.izenburua = izenburua;
	}
	
	

	// GETTERS AND SETTERS //
	

	public int getIdmusikari() {
		return idMusikari;
	}


	public void setIdmusikari(int idMusikari) {
		this.idMusikari = idMusikari;
	}


	public int getIdaudio() {
		return idAudio;
	}


	public void setIdaudio(int idAudio) {
		this.idAudio = idAudio;
	}


	public int getIraupena() {
		return iraupena;
	}


	public void setIraupena(int iraupena) {
		this.iraupena = iraupena;
	}


	public String getIzenburua() {
		return izenburua;
	}


	public void setIzenburua(String izenburua) {
		this.izenburua = izenburua;
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
		Abestia other = (Abestia) obj;
		return idAudio == other.idAudio && idMusikari == other.idMusikari && iraupena == other.iraupena
				&& Objects.equals(izenburua, other.izenburua);
	}

	// TOSTRING //
	@Override
	public String toString() {
		return "Abestia [Idmusikari=" + idMusikari + ", IdAudio=" + idAudio + ", Iraupena=" + iraupena + ", Izenburua="
				+ izenburua + "]";
	}

}
