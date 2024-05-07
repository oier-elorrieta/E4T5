package Modelo;

public class Podcasterra extends Artista {
private int podcasterID;

public Podcasterra(int idartista, String izenaartistikoa, String deskribapena, int podacasterID) {
	super(idartista, izenaartistikoa, deskribapena);
	this.podcasterID = podacasterID;
}

public int getPodacasterID() {
	return podcasterID;
}

public void setPodacasterID(int podacasterID) {
	this.podcasterID = podacasterID;
}

@Override
public String toString() {
	return "Podcasterra [podacasterID=" + podcasterID + "]";
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Podcasterra other = (Podcasterra) obj;
	return podcasterID == other.podcasterID;
}



}
