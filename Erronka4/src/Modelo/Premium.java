package Modelo;

import java.util.Date;
import java.util.Objects;

public class Premium extends Erabiltzaile {

	private Date premiumMuga;

	// KONSTRUKTOREA erabiltzailetik ere atributuak hartzen ditu //
	public Premium(String izena, String abizena, String erabiltzailea, String pasahitza,
			String jaiotze_data) {
		
		super(izena, abizena, erabiltzailea, pasahitza, jaiotze_data);
	}

	// GETTERRAK ETA SETTERRAK //
	
	public Date getPremiunMuga() {
		return premiumMuga;
	}

	public void setPremiunMuga(Date premiunMuga) {
		this.premiumMuga = premiunMuga;
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
		return "Erabiltzaile [Izena=" + Izena + ", Abizena=" + Abizena + ", Erabiltzailea="
				+ Erabiltzailea + ", Pasahitza=" + Pasahitza + ", Jaiotze_data=" + Jaiotze_data + "Premiun [premiunMuga=" + premiumMuga + "]";
	}
}
