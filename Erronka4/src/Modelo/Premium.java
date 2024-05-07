package Modelo;

import java.util.Date;
import java.util.Objects;

public class Premium extends Erabiltzaile {

    private String premiumMuga;

    // KONSTRUKTOREA erabiltzailetik ere atributuak hartzen ditu //
    public Premium(String izena, String abizena, String erabiltzailea, String pasahitza,
            String jaiotze_data, String premiunMuga) {
        
        super(izena, abizena, erabiltzailea, pasahitza, jaiotze_data);
        
        this.premiumMuga = premiunMuga;
    }

    // GETTERRAK ETA SETTERRAK //
    
    public String getPremiunMuga() {
        return premiumMuga;
    }

    public void setPremiunMuga(String premiunMuga) {
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
        return "Erabiltzaile[ Izena=" + Izena + ", Abizena=" + Abizena + ", Erabiltzailea="
                + Erabiltzailea + ", Pasahitza=" + Pasahitza + ", Jaiotze_data=" + Jaiotze_data + "Premiun [premiunMuga=" + premiumMuga + "]";
    }
}