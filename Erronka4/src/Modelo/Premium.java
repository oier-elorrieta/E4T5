package Modelo;

import java.util.Date;
import java.util.Objects;

public class Premium extends Erabiltzaile {

	private String premiumMuga;

	// KONSTRUKTOREA erabiltzailetik ere atributuak hartzen ditu //
	public Premium(String izena, String abizena, String erabiltzailea, String pasahitza, String jaiotze_data,
			String premiumMuga) {
		super(izena, abizena, erabiltzailea, pasahitza, jaiotze_data);
		this.premiumMuga = premiumMuga;
	}
 
	// GETTERRAK ETA SETTERRAK // 
	public String getPremiumMuga() {
		return premiumMuga;
	}



	public void setPremiumMuga(String premiumMuga) {
		this.premiumMuga = premiumMuga;
	}
	

	// EQUALS //
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Premium other = (Premium) obj;
		return Objects.equals(premiumMuga, other.premiumMuga);
	}
	
	// TOSTRING //
	@Override
	public String toString() {
		return "Erabiltzaile [Izena=" + izena + ", Abizena=" + abizena + ", Erabiltzailea="
				+ erabiltzailea + ", Pasahitza=" + pasahitza + ", Jaiotze_data=" + jaiotze_data + "Premiun [premiunMuga=" + premiumMuga + "]";
	}

	

	
}
