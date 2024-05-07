package Modelo;

import java.sql.Blob;

public class Musikaria extends Artista {
    
    private int musikariaID;
    

    public Musikaria(String izenaartistikoa, String deskribapena, Blob blob) {
        super(izenaartistikoa, deskribapena, blob);
        this.musikariaID = musikariaID;
    }

    public int getMusikariaID() {
        return musikariaID;
    }

    public void setMusikariaID(int musikariaID) {
        this.musikariaID = musikariaID;
        }

    @Override
    public String toString() {
        return izenaArtistikoa;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Musikaria other = (Musikaria) obj;
        return musikariaID == other.musikariaID;
    }
}