package Metodoak;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Erabilgarriak {

	// Funtzio honek zenbakiak balidatzen ditu
	/**
	 * Sartutako String-a zenbaki osoa bada True itzultzen du
	 * 
	 * @param lerroa
	 * @return
	 */
	public boolean zbkDa(String lerroa) {
		try {
			Integer.parseInt(lerroa);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Gaurko data String bezala bueltazen du
	 * @return String
	 */
	public static String getDataGaur() {
		 LocalDate dataGaur = LocalDate.now();
	     
		 //Data Stringera aldatzen du
	     String sdataGaur = dataGaur.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        
	     return sdataGaur;
	     }
	
	/**
	 * Parametroz String bezala jasotako datari urte bat gehitzen dio
	 * @param data
	 * @return
	 */
	public static String urteaGehitu(String data) {

		LocalDate dataOr = LocalDate.parse(data, DateTimeFormatter.ISO_DATE);

		LocalDate nuevaFecha = dataOr.plusYears(1);

		return nuevaFecha.format(DateTimeFormatter.ISO_DATE);
	}
	
	/**
	 * Jasotako data 1900-01-01 / 2023-12-31 artean badago True itzultzen du
	 * @param data
	 * @return boolean
	 */
	public static boolean dataBalidatu(String data) {
		try {
			LocalDate dataOr = LocalDate.parse(data);
			LocalDate dataMin = LocalDate.of(1900, 1, 1);
			LocalDate dataMax = LocalDate.of(2023, 12, 31);

			return !dataOr.isBefore(dataMin) && !dataOr.isAfter(dataMax);
		} catch (DateTimeParseException e) {
			System.out.println("Data txarto sartuta");

			return false;
		}
	}
	
	/**
	 * Bi passwordField-ak konparatzen du eta true bueltatzen du berdinak diren bakoitzean
	 * @param passwordField1
	 * @param passwordField2
	 * @return
	 */
	public static boolean pasahitzBerdinak(JPasswordField passwordField1, JPasswordField passwordField2) {

		char[] password1 = passwordField1.getPassword();
		char[] password2 = passwordField2.getPassword();

		return new String(password1).equals(new String(password2));
	}
	
	 public static String lortuDenboraMinutuetan(int segundo) {
	        int minutos = segundo / 60;
	        int segundosRestantes = segundo % 60;
	        
	        String denboraOndo = minutos + " : " + segundosRestantes;

	        return denboraOndo;
	    }
}
