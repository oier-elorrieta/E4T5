package Modelo;

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
	
}
