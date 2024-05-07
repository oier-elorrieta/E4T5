package Modelo;



public class Albumak {
	protected int idAlbum;
	protected String izenburua;
	protected int urtea;
	protected String generoa;
	protected int idArtista;
	protected int abestiKop;

	public Albumak(String izenburua, int urtea, int abestiKop, String generoa) {
		this.izenburua = izenburua;
		this.urtea = urtea;
		this.abestiKop = abestiKop;
		this.generoa = generoa;
	}
	
	public Albumak(int idAlbum, String izenburua, int urtea, String generoa, int idArtista, int abestiKop) {
		super();
		this.idAlbum = idAlbum;
		this.izenburua = izenburua;
		this.urtea = urtea;
		this.generoa = generoa;
		this.idArtista = idArtista;
		this.abestiKop = abestiKop;
	}

	public int getAbestiKop() {
		return abestiKop;
	}

	public void setAbestiKop(int abestiKop) {
		this.abestiKop = abestiKop;
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getIzenburua() {
		return izenburua;
	}

	public void setIzenburua(String izenburua) {
		this.izenburua = izenburua;
	}

	public int getUrtea() {
		return urtea;
	}

	public void setUrtea(int urtea) {
		this.urtea = urtea;
	}

	public String getGeneroa() {
		return generoa;
	}

	public void setGeneroa(String generoa) {
		this.generoa = generoa;
	}

	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}


	@Override
	public String toString() {
		return izenburua + " | " + urtea + " | Abesti kopurua:" + abestiKop ;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Albumak other = (Albumak) obj;
		if (generoa == null) {
			if (other.generoa != null)
				return false;
		} else if (!generoa.equals(other.generoa))
			return false;
		if (idAlbum != other.idAlbum)
			return false;
		if (idArtista != other.idArtista)
			return false;
		if (izenburua == null) {
			if (other.izenburua != null)
				return false;
		} else if (!izenburua.equals(other.izenburua))
			return false;
		return false;
	}

}
