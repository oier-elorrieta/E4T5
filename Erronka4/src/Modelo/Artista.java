package Modelo;

import java.util.Objects;

import com.mysql.cj.jdbc.Blob;

public abstract class Artista {
	protected String izenaArtistikoa;
	protected String deskribapena;
	protected Blob irudia;

	public Artista(String izenaartistikoa, String deskribapena, java.sql.Blob blob) {
		this.irudia = (Blob) blob;
		this.izenaArtistikoa = izenaartistikoa;
		this.deskribapena = deskribapena;
	}

// GETTERS AND SETTERS

	public String getIzenaartistikoa() {
		return izenaArtistikoa;
	}

	public Blob getIrudia() {
		return irudia;
	}

	public void setIrudia(Blob irudia) {
		this.irudia = irudia;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(deskribapena, other.deskribapena)
				&& Objects.equals(izenaArtistikoa, other.izenaArtistikoa);
	}

	@Override
	public String toString() {
		return izenaArtistikoa;
	}

}