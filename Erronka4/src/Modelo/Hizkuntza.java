package Modelo;

public class Hizkuntza {
	
	protected String hizkuntzaID;
	protected String desk;
	
	public Hizkuntza(String hizkuntzaID, String desk) {
		this.hizkuntzaID = hizkuntzaID;
		this.desk = desk;
	}

	public String getHizkuntzaID() {
		return hizkuntzaID;
	}

	public void setHizkuntzaID(String hizkuntzaID) {
		this.hizkuntzaID = hizkuntzaID;
	}

	public String getDesk() {
		return desk;
	}

	public void setDesk(String desk) {
		this.desk = desk;
	}

	@Override
	public String toString() {
		return  hizkuntzaID;
	}
	
	
	
}
