package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import DB.DBerrep;
import DB.DBmusika;
import DB.DBpodcast;
import DB.DBuser;
import Metodoak.Erabilgarriak;
import Metodoak.ErrepMetodoak;
import Modelo.Abestia;
import Modelo.Albuma;
import Modelo.ErreprodukzioPodcast;
import Modelo.Podcast;
import Modelo.Podcasterra;
import Modelo.LogeazioDatuak;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ErrepPodcast extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFinfo;
	private JTextField tFDenbora;
	private int clipDenbora=0;
	private long azkenKlika; 

	public ErrepPodcast(LogeazioDatuak logData, Podcasterra selectedPodcaster, Podcast selectedPodcast) {
		
		ErrepMetodoak errepMetodoak = new ErrepMetodoak();
		DBerrep dbErrep = new DBerrep();
		DBpodcast dbPodcast = new DBpodcast();
		DBuser dbUser = new DBuser();
		ErreprodukzioPodcast erreprodukzio = new ErreprodukzioPodcast(selectedPodcaster.getIzenaartistikoa(),selectedPodcast.getIzenburua());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 991, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errepMetodoak.audioaAmatatu();
				dispose();
				PodcastGuztiak pdGuztiak = new PodcastGuztiak(logData, selectedPodcaster);
				pdGuztiak.setVisible(true);
			}
		});

		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);

		JButton btnErabiltzailea = new JButton("");
		btnErabiltzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ErabiltzaileDatuakAldatu frame = new ErabiltzaileDatuakAldatu(logData);
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}); 
		btnErabiltzailea.setBounds(810, 11, 155, 23);
		contentPane.add(btnErabiltzailea);
		btnErabiltzailea.setText(logData.getLogeatuta());

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 437, 955, 92);
		contentPane.add(panel);
		panel.setLayout(null);
	
		String infoS = "Podcast: "+selectedPodcast.getIzenburua()+" | Podcaster: "+selectedPodcaster.getIzenaartistikoa()+" | Kolaboratzaileak: "+selectedPodcast.getKolaboratzaile()+" | Iraupena:"+selectedPodcast.getIraupena();
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(503, 14, 0, 0);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel_1);

		
		
		tFinfo = new JTextField();
		tFinfo.setHorizontalAlignment(SwingConstants.CENTER);
		tFinfo.setEditable(false);
		tFinfo.setBounds(10, 14, 935, 67);
		panel.add(tFinfo);
		tFinfo.setColumns(10);
		tFinfo.setText(infoS);	
		
		JLabel lblNewLabel = new JLabel("Informazioa:");
		lblNewLabel.setBounds(26, 425, 95, 14);
		contentPane.add(lblNewLabel);

		ImageIcon icon = null;
		try {
			icon = new ImageIcon((selectedPodcaster.getIrudia().getBytes(1, (int) selectedPodcaster.getIrudia().length())));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JLabel lblirudia = new JLabel("");
		lblirudia.setIcon(icon);
		lblirudia.setBounds(380, 57, 506, 284);
		contentPane.add(lblirudia);

		JButton btnPLAYSTOP = new JButton("PLAY");
		btnPLAYSTOP.addActionListener(new ActionListener() {
			// Botoia klikatzean testus aldatzen da PLAY badago klikatzerakoan STOP jartzen
			// da eta alderantziz
			// PLAY badago klikatzerakoan kanta hasten da eta STOP badago kanta gelditzen da
			public void actionPerformed(ActionEvent e) {
				if (btnPLAYSTOP.getText() == "PLAY") {
					btnPLAYSTOP.setText("STOP");
					if(errepMetodoak.getErreproduzitzenDago()==true) {
						errepMetodoak.audioaBerrezarri(clipDenbora,selectedPodcast.getIzenburua());
						
						}else {
							errepMetodoak.erreproduzitu(selectedPodcast.getIzenburua());
							dbErrep.insertatuErrep(Integer.parseInt( dbPodcast.lortuPodcastId(selectedPodcast)),Integer.parseInt(dbUser.lortuUserId(logData.getLogeatuta())));						
						}	
				} else {
					clipDenbora=errepMetodoak.kantaDaramatzanSeg();
					btnPLAYSTOP.setText("PLAY");
					errepMetodoak.audioaAmatatu();
					errepMetodoak.setErreproduzitzenDago(true);
				}
			}
		});
		btnPLAYSTOP.setBounds(512, 376, 106, 23);
		contentPane.add(btnPLAYSTOP);

		tFDenbora = new JTextField();
		tFDenbora.setHorizontalAlignment(SwingConstants.CENTER);
		tFDenbora.setEditable(false);
		tFDenbora.setBounds(455, 352, 86, 20);
		contentPane.add(tFDenbora);
		tFDenbora.setColumns(10);
		
		JButton btnBerrerapizti = new JButton("Berriro hasi");
		btnBerrerapizti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errepMetodoak.erreproduzitu(selectedPodcast.getIzenburua());
				btnPLAYSTOP.setText("STOP");
				}
		});
		btnBerrerapizti.setBounds(380, 376, 106, 23);
		contentPane.add(btnBerrerapizti);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					
					btnPLAYSTOP.setText("PLAY");
					errepMetodoak.audioaAmatatu();

					System.out.println(erreprodukzio.getPosizioa());
					Podcast autatutakoPodcastBerria = erreprodukzio.aurrekoPodcastera();
	
					String infoS = "Podcast: "+autatutakoPodcastBerria.getIzenburua()+" | Podcaster: "+selectedPodcaster.getIzenaartistikoa()+" | Kolaboratzaileak: "+autatutakoPodcastBerria.getKolaboratzaile()+" | Iraupena:"+autatutakoPodcastBerria.getIraupena();
					
					tFinfo.setText(infoS);
					System.out.println(erreprodukzio.getPosizioa());
					
					{
				}
					
			}
		});
		btnNewButton.setBounds(245, 376, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(erreprodukzio.getPosizioa());
				
				btnPLAYSTOP.setText("PLAY");
				errepMetodoak.audioaAmatatu();
				Podcast autatutakoPodcastBerria = erreprodukzio.hurrengoPodcastera();

				System.out.println(erreprodukzio.getPosizioa());
				String infoS = "Podcast: "+autatutakoPodcastBerria.getIzenburua()+" | Podcaster: "+selectedPodcaster.getIzenaartistikoa()+" | Kolaboratzaileak: "+autatutakoPodcastBerria.getKolaboratzaile()+" | Iraupena:"+autatutakoPodcastBerria.getIraupena();
				
				tFinfo.setText(infoS);
				
				
			}
		});
		btnNewButton_1.setBounds(661, 376, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		JButton btnAbiadura = new JButton("");
		btnAbiadura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAbiadura.getText()=="x1") {
					btnAbiadura.setText("x2");
				}else if(btnAbiadura.getText()=="x0.5") {
					btnAbiadura.setText("x1");
				}else if(btnAbiadura.getText()=="x2"){
					btnAbiadura.setText("x0.5");
				}					
			}
		});
		btnAbiadura.setBounds(797, 376, 89, 23);
		contentPane.add(btnAbiadura);
		btnAbiadura.setText("x1");
		/*
		 * Momentu guztietan erreprodukzioa badago ala ez denbora eguneratzen du segundo
		 * guztietan
		 */
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(() -> {

			SwingUtilities.invokeLater(() -> {
				tFDenbora.setText(Erabilgarriak.lortuDenboraMinutuetan(errepMetodoak.kantaDaramatzanSeg()));
			});// Lehenengo aldagaiak zenbat segundo itzaron behar dituen lehen aldian zehazten
				// du eta bigarrenak zenbat segunduro errepikatu behar den
		}, 0, 1, TimeUnit.SECONDS);
	}
}
