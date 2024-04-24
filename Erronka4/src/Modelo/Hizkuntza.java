package Modelo;

import java.util.Objects;

public class Hizkuntza {
	
	protected String hizkuntzaId;
	protected String desk;
	
	public Hizkuntza(String hizkuntzaid, String desk) {
		this.hizkuntzaId = hizkuntzaid;
		this.desk = desk;
	}

	

	public String getHizkuntzaid() {
		return hizkuntzaId;
	}



	public void setHizkuntzaid(String hizkuntzaid) {
		this.hizkuntzaId = hizkuntzaid;
	}



	public String getDesk() {
		return desk;
	}



	public void setDesk(String desk) {
		this.desk = desk;
	}


	
	@Override
	public String toString() {
		return  hizkuntzaId;
	}

	
	
	
	
	
}
