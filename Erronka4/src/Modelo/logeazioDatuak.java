package Modelo;

import java.util.Objects;

public class logeazioDatuak {
	protected String logeatuta;
	
	public logeazioDatuak(String logeatuta) {
		this.logeatuta = logeatuta;
	}


	public String getLogeatuta() {
		return logeatuta;
	}

	public void setLogeatuta(String logeatuta) {
		this.logeatuta = logeatuta;
	}
	
	@Override
	public String toString() {
		return "logeazioDatuak [logeatuta=" + logeatuta + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		logeazioDatuak other = (logeazioDatuak) obj;
		return Objects.equals(logeatuta, other.logeatuta);
	}
	
	
}