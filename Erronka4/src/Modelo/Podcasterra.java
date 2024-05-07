package Modelo;

import com.mysql.cj.jdbc.Blob;

public class Podcasterra extends Artista {
private int podcasterID;

public Podcasterra( String izenaartistikoa, String deskribapena, Blob irudia) {
    super(izenaartistikoa, deskribapena, irudia);
    
}

public int getPodacasterID() {
    return podcasterID;
}

public void setPodacasterID(int podacasterID) {
    this.podcasterID = podacasterID;
}

@Override
public String toString() {
    return super.toString();
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