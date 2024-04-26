package Modelo;

public class Musikaria extends Artista {

	private int musikariaID;

	public Musikaria(int idartista, String izenaartistikoa, String deskribapena, int musikariaID) {
		super(idartista, izenaartistikoa, deskribapena);
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
		return "Musikaria [musikariaID=" + musikariaID + ", idArtista=" + idArtista + ", izenaArtistikoa="
				+ izenaArtistikoa + ", deskribapena=" + deskribapena + "]";
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
