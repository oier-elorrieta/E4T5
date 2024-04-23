package Modelo;

import java.util.Date;
import java.util.Objects;

public class Playlistak {
    protected int IdList;
    protected String Izenburua;
    protected Date Sorrera_data;
    
    // KONTRUKTOREA //
    public Playlistak(int idList, String izenburua, Date sorrera_data) {
        IdList = idList;
        Izenburua = izenburua;
        Sorrera_data = sorrera_data;
    }

    // GETTERRAK ETA SETTERRAK //
    public int getIdList() {
        return IdList;
    }

    public void setIdList(int idList) {
        IdList = idList;
    }

    public String getIzenburua() {
        return Izenburua;
    }

    public void setIzenburua(String izenburua) {
        Izenburua = izenburua;
    }

    public Date getSorrera_data() {
        return Sorrera_data;
    }

    public void setSorrera_data(Date sorrera_data) {
        Sorrera_data = sorrera_data;
    }

    // EQUALS //
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlistak other = (Playlistak) obj;
        return IdList == other.IdList && Objects.equals(Izenburua, other.Izenburua)
                && Objects.equals(Sorrera_data, other.Sorrera_data);
    }

    // TOSTRING //
    
    @Override
    public String toString() {
        return "Playlistak [IdList=" + IdList + ", Izenburua=" + Izenburua + ", Sorrera_data=" + Sorrera_data + "]";
    }
    
    
}