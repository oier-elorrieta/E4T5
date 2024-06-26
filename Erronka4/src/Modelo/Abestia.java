package Modelo;

import java.util.Objects;

import com.mysql.cj.jdbc.Blob;

public class Abestia {

    private int IdAudio;
    private String Iraupena;
    private String Izenburua;
    private Blob irudia;
    private int errep;
    // KONSTRUKTOREA //
    
    public Abestia( int idAudio, String iraupena, String izenburua, Blob irudia) {
        IdAudio = idAudio;
        Iraupena = iraupena;
        Izenburua = izenburua;
        this.irudia = irudia;
    }
    
    public Abestia( int idAudio, String iraupena, String izenburua, Blob irudia, int errep) {
        IdAudio = idAudio;
        Iraupena = iraupena;
        Izenburua = izenburua;
        this.irudia = irudia;
        this.errep = errep;
    }

    // GETTERRAK ETA SETTERRAK //
   
	public int getIdAudio() {
        return IdAudio;
    }

    public void setIdAudio(int idAudio) {
        IdAudio = idAudio;
    }

    public String getIraupena() {
        return Iraupena;
    }

    public void setIraupena(String iraupena) {
        Iraupena = iraupena;
    }

    public String getIzenburua() {
        return Izenburua;
    }

    public void setIzenburua(String izenburua) {
        Izenburua = izenburua;
    }
    
     public Blob getIrudia() {
		return irudia;
	}

	public void setIrudia(Blob irudia) {
		this.irudia = irudia;
	}

    // TOSTRING //
    
	@Override
    public String toString() {
        return "Izenburua=" + Izenburua +" | Iraupena=" + Iraupena ;
    }
	
	public String toStringErrep() {
		 return Izenburua +" | Iraupena=" + Iraupena +" | Entzunaldiak: "+errep;
		 }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abestia other = (Abestia) obj;
		if (IdAudio != other.IdAudio)
			return false;
		if (Iraupena == null) {
			if (other.Iraupena != null)
				return false;
		} else if (!Iraupena.equals(other.Iraupena))
			return false;
		if (Izenburua == null) {
			if (other.Izenburua != null)
				return false;
		} else if (!Izenburua.equals(other.Izenburua))
			return false;
		if (irudia == null) {
			if (other.irudia != null)
				return false;
		} else if (!irudia.equals(other.irudia))
			return false;
		return true;
	}

	


}