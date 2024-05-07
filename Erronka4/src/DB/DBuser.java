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

import Metodoak.Metodoak;
import Modelo.Hizkuntza;
import Modelo.Erabiltzaile;

public class DBuser {
	
	private String erabiltzaile = "admin";
	private String psw = "admin";
	
	/**
	 * Datu basean kontsulta bat egiten du parametroz jasotako erabiltzailea eta
	 * pasahitza erabiliz. Sartutako erabiltzailearen pasahitza parametroz jasotakoa
	 * baldin bada True itzultzen du beztela false
	 * 
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
		
		try {
			String kontsulta = "select count(izena) from bezero where erabiltzailea = '" + erabiltzaile
					+ "' and pasahitza ='" + pasahitza + "'";
			stm = konexioa.createStatement();
			rs = stm.executeQuery(kontsulta);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rs.next(); /* Kursorea hurrengo hilarara mugitzen dugu */
		if (rs.getInt(1) == 1) {
			System.out.println("sartuta");
			loginOk = true;
		} else {
			System.out.println("Ez gara sartu");
		}
		
		konexioa2.deskonektatu();
		return loginOk;
	}
	
	/**
	 * Datu basean gordeta dauden hizkuntzak ArrayList batean gordetzen ditu,
	 * Hizkuntza objektuetan gordez
	 * 
	 * @return ArrayList<Hizkuntza>
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
					pstmt.setString(7, Metodoak.getDataGaur());
					pstmt.setString(8, mota);
					int filasAfectadas = pstmt.executeUpdate();
					System.out.println("Afektatutako ilara: " + filasAfectadas);
					insertatua = true;
				
					if(mota=="Premium") {
						insertatuPremium(user.getErabiltzailea(),premiumData.getText());
					}
			
			} catch (SQLException e) {
				System.out.println("Datu basera konektatzean Errorea.");
				e.printStackTrace();
			}
		}
		konexioa2.deskonektatu();
		return insertatua;
	}
	
	/**
	 * Premium taulan zein erabiltzaile eta noizarte premium den ezartzen du
	 * @param username
	 * @param dataPremium
	 * @return
	 */
	public boolean insertatuPremium(String username, String dataPremium) {
		boolean insertatuta=false;

		Konexioa konexioa2 = new Konexioa();
		Connection konexioa = konexioa2.konektatu();
		
		String sql = "INSERT INTO premium (idBezero, iraungitzeData) "
				+ "VALUES (?, ?)";
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.5.6.67:3306/db_spotify5", erabiltzaile,
				psw);
				
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
				pstmt.setString(1, lortuUserId(username));
				pstmt.setString(2, dataPremium);
				
				int filasAfectadas = pstmt.executeUpdate();
				System.out.println("Afektatutako ilara: " + filasAfectadas);
				insertatuta = true;
			
		} catch (SQLException e) {
			System.out.println("Datu basera konektatzean Errorea.");
			e.printStackTrace();
		}
		
		konexioa2.deskonektatu();
		return insertatuta;
		
	}
	
	

	/*
	 * Parametroz bezala jasotako erabiltzailearen Id-a String bezala bueltatzen du
	 * @param username
	 * @return
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
	 * @param izena
	 * @param abizena
	 * @param erabiltzailea
	 * @param jaioData
	 * @param erregData
	 * @return boolean
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
				System.out.println("Ez daude datu guztiak beteta");
				break;
			}
			
		}
		return datuakBeteta;
	}
	
	/**
	 * Jasotako erabiltzailea datu basean dagoen egiaztatzen du
	 * @param username
	 * @return
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
}


