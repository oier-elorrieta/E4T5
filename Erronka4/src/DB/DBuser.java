package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

import Metodoak.Erabilgarriak;
import Modelo.Hizkuntza;
import Modelo.Erabiltzaile;

public class DBuser {
	
	private String erabiltzaile = "admin";
	private String psw = "admin";
	
	/**
	 * Datu basean kontsulta bat egiten du parametroz jasotako erabiltzailea eta
	 * pasahitza erabiliz. Sartutako erabiltzailearen pasahitza parametroz jasotakoa
	 * baldin bada True itzultzen du beztela false
	 * @param erabiltzaile
	 * @param pasahitza
	 * @return boolean
	 * @throws SQLException
	 */ 
	public boolean isLoginOk(String erabiltzaile, String pasahitza) throws SQLException {

		boolean loginOk = false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		
	
		String kontsulta = "select count(izena) from bezero where erabiltzailea = '" + erabiltzaile
				+ "' and pasahitza ='" + pasahitza + "'";
		stm = konexioa.createStatement();
		rs = stm.executeQuery(kontsulta);

		
		rs.next(); /* Kursorea hurrengo hilarara mugitzen dugu */
		if (rs.getInt(1) == 1) {
			loginOk = true;
		} else {
		}
		
		konexioa2.deskonektatu();
		return loginOk;
	}
	
	/**
	 * Datu basean gordeta dauden hizkuntzak ArrayList batean gordetzen ditu,
	 * Hizkuntza objektuetan gordez
	 * @return ArrayList<Hizkuntza> hizkuntza guztiak
	 */
	public ArrayList<Hizkuntza> hizkuntzakLortu() {
		ArrayList<Hizkuntza> hizkuntzaGuztiak = new ArrayList<>();
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
	
		ResultSet rs = null;
		Statement stm;
		
		try {
			String kontsulta = "select idHizkuntza, deskribapena from hizkuntza";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				Hizkuntza hzk = new Hizkuntza(rs.getString("idHizkuntza"), rs.getString("deskribapena"));
				hizkuntzaGuztiak.add(hzk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		konexioa2.deskonektatu();
		return hizkuntzaGuztiak;
	}
	/**
	 * Parametroz jasotako aldagaietaz baliatuz erabiltzaile berri bat sortu eta datu basean insertatzen du
	 * Erabiltzaile berria ondo insertatzen baldin badu True itzultzen du bezala False
	 * @param izena
	 * @param abizena
	 * @param erabiltzailea
	 * @param jaioData
	 * @param erregData
	 * @param premiumData
	 * @param hizkuntza
	 * @param pasahitza
	 * @return Boolean
	 */
	public boolean insertatuBezeroBerria(Erabiltzaile user, JComboBox hizkuntza, JTextField premiumData) {
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		
		String hzkAutatua=hizkuntzakLortu().get(hizkuntza.getSelectedIndex()).getHizkuntzaID();
		
		boolean insertatua = false;
		String mota = premiumData.getText().isEmpty() ? "Free" : "Premium";
		String sql = "INSERT INTO bezero (izena, abizena, hizkuntza, erabiltzailea, pasahitza, jaiotzeData, erregistroData, mota) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		if(erabiltzaileaArtuta(user.getErabiltzailea())==false) {
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,
					psw);
				
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
					pstmt.setString(1, user.getIzena());
					pstmt.setString(2, user.getAbizena());
					pstmt.setString(3, hzkAutatua);
					pstmt.setString(4, user.getErabiltzailea());
					pstmt.setString(5, user.getPasahitza());
					pstmt.setString(6, ( user.getJaiotze_data()));
					pstmt.setString(7, Erabilgarriak.getDataGaur());
					pstmt.setString(8, mota);
					int filasAfectadas = pstmt.executeUpdate();
					insertatua = true;
				
					if(mota=="Premium") {
						insertatuPremium(user.getErabiltzailea(),premiumData.getText());
					}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		konexioa2.deskonektatu();
		return insertatua;
	}
	

	
	/*
	 * Erabiltzailearen id-a lortzen du
	 * @param username String erabiltzailea
	 * @return String id
	 */
	public String lortuUserId(String username) {
		
		String id = null ;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		ResultSet rs = null;
		Statement stm;
		
		try {
			String kontsulta = "select idBezero from bezero where erabiltzailea='"+username+"'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				id = rs.getString(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		konexioa2.deskonektatu();
		return id;
		
	}
	
	/**
	 * Parametroz jasotako jTextField-ak hutzik dauden baloratzen du eta True itzultzen du Parametroak hutzik ez daudenean
	 * @param izena JTextField
	 * @param abizena JTextField
	 * @param erabiltzailea JTextField
	 * @param jaioData JTextField
	 * @param erregData JTextField
	 * @return boolean Beteta daude
	 */
	public boolean datuakBeteta(JTextField izena, JTextField abizena, JTextField erabiltzailea, JTextField jaioData,JTextField erregData) {
		boolean datuakBeteta = true;
		ArrayList<JTextField> betegarriak = new ArrayList<>();
		betegarriak.add(izena);
		betegarriak.add(abizena);
		betegarriak.add(erabiltzailea);
		betegarriak.add(jaioData);
		betegarriak.add(erregData);
		for (int i = 0; i < 5; i++) {
			if (betegarriak.get(i).getText().isEmpty()) {
				datuakBeteta = false;
				break;
			}
			
		}
		return datuakBeteta;
	}
	
	/**
	 * Erabiltzailea erabiltzen den lortzen du
	 * @param username String erabiltzailea
	 * @return erabiltzen da
	 */
	public boolean erabiltzaileaArtuta(String username) {
		boolean artutaDago=true;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		
		if(lortuUserId(username)==null) {
			artutaDago=false;
		}
		
		return artutaDago;
	}
	
	/**
	 * Erabiltzaileen datuak aldatzen ditu
	 * @param user Erabiltzailearen objektua
	 * @param hizkuntza Hizkuntzaren JComboBox
	 * @param premiumData JTextField premium mugarekin
	 * @param logeatutakoUserr String logeatutako erabiltzailearen izena
	 * @return True bueltatzen du datuen update-a egiten duenean
	 */
	public boolean erabiltzaileaEguneratu(Erabiltzaile user,JComboBox hizkuntza,JTextField premiumData, String logeatutakoUserr) {
		
		boolean eguneratua=false;
		
		Konexioa konexioa2 = new Konexioa();
	    Connection konexioa = konexioa2.konektatu();
	    DBerrep dbErrep = new DBerrep();
	    
	    String hzkAutatua = hizkuntzakLortu().get(hizkuntza.getSelectedIndex()).getHizkuntzaID();

	    
	    String sql = "UPDATE bezero SET izena=?, abizena=?, hizkuntza=?, pasahitza=?, jaiotzeData=?, mota=?,erabiltzailea=?  WHERE idBezero='"+lortuUserId(logeatutakoUserr)+"'";

	  
	        try {//KoNPONDU PREMIUM
	        	
	            if (dbErrep.premiumDa(user.getErabiltzailea())) {
	            	if(dbErrep.premiumDa(user.getErabiltzailea())==true) {
	            		eguneratuPremium(user.getErabiltzailea(), premiumData.getText());

	            	}else {
	            		insertatuPremium(user.getErabiltzailea(),  premiumData.getText());

	            	}        
	            }
	            
	        	Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,psw);
	        	
	        	String mota = premiumData.getText().isEmpty() ? "Free" : "Premium";
	        	
	            PreparedStatement pstmt = conn.prepareStatement(sql); {
	            pstmt.setString(1, user.getIzena());
	            pstmt.setString(2, user.getAbizena());
	            pstmt.setString(3, hzkAutatua);
	            pstmt.setString(4, user.getPasahitza());
	            pstmt.setString(5,user.getJaiotze_data());
	            pstmt.setString(6, mota);
	            pstmt.setString(7, user.getErabiltzailea());

	            pstmt.executeUpdate();
	            eguneratua = true;

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	    konexioa2.deskonektatu();
	    return eguneratua;		
		
	}
	
	/**
	 * Erabiltzaileei premium muga aldatzea lortzen du
	 * @param username String erabiltzailearen izena
	 * @param dataPremium String noizarteIzango den premium
	 * @return True bueltatzen du updatea ondo egitean
	 */
	public boolean eguneratuPremium(String username, String dataPremium) {
		boolean insertatuta=false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		
		String sql = "Update premium set idBezero=?, iraungitzeData=? where idBezero='"+lortuUserId(username)+"'";
	
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,psw);
			
			PreparedStatement pstmt = conn.prepareStatement(sql); {
		
			pstmt.setString(1, lortuUserId(username));
			pstmt.setString(2, dataPremium);
			insertatuta = true;
            pstmt.executeUpdate();
            
			}
			}catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		konexioa2.deskonektatu();
		return insertatuta;
	}
	/**
	 * Premium taulan zein erabiltzaile eta noizarte premium den ezartzen du
	 * @param username String erabiltzailea
	 * @param dataPremium Premium muga data
	 * @return ondo insertatu da
	 */
	public boolean insertatuPremium(String username, String dataPremium) {
		boolean insertatuta=false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		
		String sql = "INSERT INTO premium (idBezero, iraungitzeData)" + "VALUES (?, ?)";
		
		try { 
		Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,
				psw);
				
				PreparedStatement pstmt = conn.prepareStatement(sql); {
			
				pstmt.setString(1, lortuUserId(username));
				pstmt.setString(2, dataPremium);
				
				insertatuta = true;
				pstmt.executeUpdate();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		konexioa2.deskonektatu();
		return insertatuta;
	}
	
	public ArrayList<String> lortuErabiltzaileDatuak(String erabiltzailea){
		ArrayList<String> datuak = new ArrayList<String>();
		
		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		
		String sql = "Select * from bezeroDatuak where erabiltzailea='"+erabiltzailea+"'";
		
		try {	
			Statement stm = konexioa.createStatement();
			ResultSet rs = stm.executeQuery(sql);
				
			rs.next();
			
				datuak.add(rs.getString("izena"));
				datuak.add(rs.getString("abizena"));
				datuak.add(rs.getString("jaiotzeData"));
				datuak.add(rs.getString("iraungitzeData"));
				
				for(int i = 0;i<=3;i++) {
					System.out.println(datuak.get(i));
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return datuak;
	}
}	


