package Modelo;

import java.util.Date;
import java.util.Objects;

public class Playlist {
	private int idList;
	private String izenburua;
	private Date sorrera_data;
    
    // KONTRUKTOREA //
    public Playlist(int idList, String izenburua, Date sorrera_data) {
		super();
		this.idList = idList;
		this.izenburua = izenburua;
		this.sorrera_data = sorrera_data;
	}

    // GETTERRAK ETA SETTERRAK //
    public int getIdlist() {
  		return idList;
  	}

  	public void setIdlist(int idList) {
  		this.idList = idList;
  	}

  	public String getIzenburua() {
  		return izenburua;
  	}

  	public void setIzenburua(String izenburua) {
  		this.izenburua = izenburua;
  	}

  	public Date getSorrera_data() {
  		return sorrera_data;
  	}

  	public void setSorrera_data(Date sorrera_data) {
  		this.sorrera_data = sorrera_data;
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
		Playlist other = (Playlist) obj;
		return idList == other.idList && Objects.equals(izenburua, other.izenburua)
				&& Objects.equals(sorrera_data, other.sorrera_data);
	}
  	
    // TOSTRING //
	@Override
	public String toString() {
		return "Playlist [idlist=" + idList + ", izenburua=" + izenburua + ", sorrera_data=" + sorrera_data + "]";
	}
   

    
    
    
}