package Modelo;

import java.util.Objects;

public abstract class Artista {
	protected int idArtista;
	protected String izenaArtistikoa;
	protected String deskribapena;

	public Artista(int idartista, String izenaartistikoa, String deskribapena) {
		this.idArtista = idartista;
		this.izenaArtistikoa = izenaartistikoa;
		this.deskribapena = deskribapena;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(deskribapena, other.deskribapena) && idArtista == other.idArtista
				&& Objects.equals(izenaArtistikoa, other.izenaArtistikoa);
	}

	@Override
	public String toString() {
		return izenaArtistikoa;
	}

}