package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Metodoak.Erabilgarriak;
import Metodoak.iragarkiaMetodoak;
import Modelo.Abestia;
import Modelo.Albuma;
import Modelo.logeazioDatuak;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class iragarkia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public iragarkia(logeazioDatuak logData,Abestia selectedAbestia, Albuma selectedAlbum) {
		iragarkiaMetodoak iragarkiaMetodoak = new iragarkiaMetodoak();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EROSI PREMIUM ");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 434, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblIragarkarikGabekoMusika = new JLabel("iragarkarik gabeko musika entsuteko");
		lblIragarkarikGabekoMusika.setHorizontalAlignment(SwingConstants.CENTER);
		lblIragarkarikGabekoMusika.setBounds(0, 28, 434, 51);
		contentPane.add(lblIragarkarikGabekoMusika);
	
		
		JLabel lblirudia = new JLabel("");
		lblirudia.setIcon(new ImageIcon("C:\\Users\\in1dm3-d\\Desktop\\Iragarkia\\securitas Direct.jpg"));
		lblirudia.setBounds(98, 73, 263, 163);
		contentPane.add(lblirudia);
		
		iragarkiaMetodoak.erreproduzitu();
		
		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        scheduler.scheduleAtFixedRate(() -> {
	           
	            SwingUtilities.invokeLater(() -> {
	            if(iragarkiaMetodoak.erreproduzitzenDago()==false) {
	            	dispose();
					try {
						errepAbesti frame = new errepAbesti(logData,selectedAbestia,selectedAlbum);
						frame.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
	            }
	            });
	        }, 0, 1, TimeUnit.SECONDS);	
		
		
	}
}
