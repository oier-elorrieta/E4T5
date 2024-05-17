package Modelo;

public class LogeazioDatuak {
	protected String logeatuta;
	
	public LogeazioDatuak(String logeatuta) {
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
