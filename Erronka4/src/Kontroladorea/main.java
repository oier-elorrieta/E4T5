package Kontroladorea;

import DB.DBuser;
import vista.login;

public class main {
	
	public static void main(String args[]) {
		
		DBuser db = new DBuser();
		
			login frame = new login();
			frame.setVisible(true);
			
	}
	
}