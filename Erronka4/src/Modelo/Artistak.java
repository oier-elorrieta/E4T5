package Modelo;

import java.util.Objects;

public class Artistak {
protected int idArtista;
protected String izenaArtistikoa;
protected String deskribapena;
protected boolean ezaugarria;

public Artistak(int idArtista, String izenaArtistikoa, String deskribapena, boolean ezaugarria) {
    this.idArtista = idArtista;
    this.izenaArtistikoa = izenaArtistikoa;
    this.deskribapena = deskribapena;
    this.ezaugarria = ezaugarria;
}

public int getIdArtista() {
    return idArtista;
}

public void setIdArtista(int idArtista) {
    this.idArtista = idArtista;
}

public String getIzenaArtistikoa() {
    return izenaArtistikoa;
}

public void setIzenaArtistikoa(String izenaArtistikoa) {
    this.izenaArtistikoa = izenaArtistikoa;
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
    Artistak other = (Artistak) obj;
    return Objects.equals(deskribapena, other.deskribapena) && ezaugarria == other.ezaugarria
            && idArtista == other.idArtista && Objects.equals(izenaArtistikoa, other.izenaArtistikoa);
}

@Override
public String toString() {
    return izenaArtistikoa ;
}


}