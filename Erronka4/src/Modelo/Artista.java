package Modelo;

import java.util.Objects;

public class Artista {
protected int idArtista;
protected String izenaArtistikoa;
protected String deskribapena;
protected boolean ezaugarria; 

public Artista(int idartista, String izenaartistikoa, String deskribapena, boolean ezaugarria) {
    this.idArtista = idartista;
    this.izenaArtistikoa = izenaartistikoa;
    this.deskribapena = deskribapena;
    this.ezaugarria = ezaugarria;
}

// GETTERS AND SETTERS

public int getIdartista() {
	return idArtista;
}

public void setIdartista(int idartista) {
	this.idArtista = idartista;
}

public String getIzenaartistikoa() {
	return izenaArtistikoa;
}

public void setIzenaartistikoa(String izenaartistikoa) {
	this.izenaArtistikoa = izenaartistikoa;
}

public String getDeskribapena() {
	return deskribapena;
}

public void setDeskribapena(String deskribapena) {
	this.deskribapena = deskribapena;
}

public boolean isEzaugarria() {
	return ezaugarria;
}

public void setEzaugarria(boolean ezaugarria) {
	this.ezaugarria = ezaugarria;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Artista other = (Artista) obj;
    return Objects.equals(deskribapena, other.deskribapena) && ezaugarria == other.ezaugarria
            && idArtista == other.idArtista && Objects.equals(izenaArtistikoa, other.izenaArtistikoa);
}

@Override
public String toString() {
    return izenaArtistikoa ;
}


}