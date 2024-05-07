package Modelo;

import com.mysql.cj.jdbc.Blob;

public class Podcasterra extends Artista {


public Podcasterra( String izenaartistikoa, String deskribapena, Blob blob) {
	super(izenaartistikoa, deskribapena, blob);
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
