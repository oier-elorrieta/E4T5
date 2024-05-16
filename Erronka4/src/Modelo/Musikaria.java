package Modelo;

import java.sql.Blob;

public class Musikaria extends Artista {
    
    public Musikaria(String izenaartistikoa, String deskribapena, Blob blob) {
        super(izenaartistikoa, deskribapena, blob);
    }
    @Override
    public String toString() {
        return izenaArtistikoa;
    }
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

    
}
