package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import DB.DBerrep;
import DB.DBmusika;
import DB.DBplaylist;
import DB.DBuser;
import Metodoak.Erabilgarriak;
import Metodoak.ErrepMetodoak;
import Modelo.Abestia;
import Modelo.Albuma;
import Modelo.ErreprodukzioAbestia;
import Modelo.Playlist;
import Modelo.LogeazioDatuak;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ErrepAbesti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFinfo;
	private long azkenKlika;
	private JTextField tFDenbora; 

	public ErrepAbesti(LogeazioDatuak logData, Abestia selectedAbestia, Albuma selectedAlbum) {

		ErreprodukzioAbestia erreprodukzio = new ErreprodukzioAbestia(selectedAlbum.getIzenburua(),
				selectedAbestia.getIzenburua());
		ErrepMetodoak errepMetodoak = new ErrepMetodoak();
		DBerrep dbErrep = new DBerrep();
		DBuser dbUser = new DBuser();
		DBplaylist dbPlaylist = new DBplaylist();
		DBmusika dbMusika = new DBmusika();

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
				try {
					AlbumLeihoa frame = new AlbumLeihoa(logData, selectedAlbum);
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
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
		btnErabiltzailea.setBounds(795, 11, 170, 23);
		contentPane.add(btnErabiltzailea);
		btnErabiltzailea.setText(logData.getLogeatuta());

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 437, 955, 92);
		contentPane.add(panel);
		panel.setLayout(null);

		String infoS = "Abestia: " + erreprodukzio.getAbesti() + "\n | Albuma: " + selectedAlbum.getIzenburua()
				+ "\n | Iraupena: " + selectedAbestia.getIraupena();

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

		JButton btnNewButton = new JButton("MENUA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Leihoa bistaratu bi aukera dituela
				int hautapen = JOptionPane.showOptionDialog(null, // Amaia componentea (kasu honetan, ez)
						"Hautatu aukera bat", // Erakutsi nahi den mezua
						"Menua", // Leihoaren izenburua
						JOptionPane.YES_NO_OPTION, // Hautapen mota (kasu honetan, bai/ez)
						JOptionPane.QUESTION_MESSAGE, // Mezu mota (kasu honetan, galdera)
						null, // Icono pertsonalizatua (kasu honetan, ez)
						new String[] { "Gorde playlist-ean", "Gorde fitxeroan" }, // Botoien testua
						"Aukera 1" // Aukera lehenetsia
				);

				// Hautapena zein den egiaztatu
				if (hautapen == JOptionPane.YES_OPTION) {

					ArrayList<Playlist> playlistList = dbPlaylist.lortuPlaylist(logData);
					JComboBox<Playlist> comboBox = new JComboBox<>();
					for (Playlist playlist : playlistList) {
						comboBox.addItem(playlist);
					}

					JPanel deletePanel = new JPanel();
					deletePanel.add(new JLabel("Aukeratu playlist-a:"));
					deletePanel.add(comboBox);

					int result = JOptionPane.showConfirmDialog(null, deletePanel, "Ezabatu Playlist",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {

						// Autatutako playlista nulla izan daiteke erabiltzaileak ez baditu playlistak
						// sortu beraz null ez dela konprobatzen dugu
						if (playlistList.size() != 0) {
							Playlist selectedPlaylist = playlistList.get(comboBox.getSelectedIndex());
							
							if(dbPlaylist.abestiaPlaylisteanDago(Integer.parseInt(dbMusika.lortuAbestiId(erreprodukzio.getAbesti())),playlistList.get(comboBox.getSelectedIndex()).getIdlist())==false ) {
								 dbPlaylist.insertKantaPlaylistean(erreprodukzio.getAbestiList().get(erreprodukzio.getPosizioa()) , selectedPlaylist.getIdlist());
								 JOptionPane.showMessageDialog(ErrepAbesti.this,
											"Gordeta", "Adi!",
											JOptionPane.WARNING_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(ErrepAbesti.this,
										"Abesti hau jadanik dago playlistean", "Adi!",
										JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(ErrepAbesti.this,
									"Ez duzu Playlistarik sortu, mesedez playlist berri bat sortu", "Kontuz!",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					// Aukera 1 hautatu da
				} else if (hautapen == JOptionPane.NO_OPTION) {
				} else {
					// Hautapena egin gabe leihoa ixten da
				}
			}
		});
		btnNewButton.setBounds(195, 376, 116, 23);
		contentPane.add(btnNewButton);

		ImageIcon icon = null;
		try {
			icon = new ImageIcon(selectedAbestia.getIrudia().getBytes(1, (int) selectedAbestia.getIrudia().length()));
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
					errepMetodoak.erreproduzitu(erreprodukzio.getAbesti());
					btnPLAYSTOP.setText("STOP");

					dbErrep.insertatuErrep(Integer.parseInt(dbMusika.lortuAbestiId(selectedAbestia.getIzenburua())),
							Integer.parseInt(dbUser.lortuUserId(logData.getLogeatuta())));
				} else {
					btnPLAYSTOP.setText("PLAY");
					errepMetodoak.audioaAmatatu();

				}
			}
		});
		btnPLAYSTOP.setBounds(453, 376, 89, 23);
		contentPane.add(btnPLAYSTOP);

		JButton btnAurrekoKanta = new JButton("<");
		btnAurrekoKanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long tOrain = System.currentTimeMillis();

				String abestiOrain = erreprodukzio.getAbesti();
				// Free bada azken hamar minutuetan kanta aldatu duen konprobatzen du,
				// Erabiltzailea premium bada ere konprobatzen du
				if (tOrain - azkenKlika >= 10 * 1000 || dbErrep.premiumDa(logData.getLogeatuta()) == true) {

					btnPLAYSTOP.setText("PLAY");
					errepMetodoak.audioaAmatatu();

					Abestia autatutakoAbestiBerria = erreprodukzio.aurrekoKantara();
					String infoS = "Abestia: " + erreprodukzio.getAbesti() + "\n | Albuma: "
							+ selectedAlbum.getIzenburua() + "\n | Iraupena: " + autatutakoAbestiBerria.getIraupena();
					tFinfo.setText(infoS);
					azkenKlika = tOrain;

					ImageIcon icon = null;
					try {
						icon = new ImageIcon(dbErrep.irudiaLortu(erreprodukzio.getAbesti()).getBytes(1,
								(int) dbErrep.irudiaLortu(erreprodukzio.getAbesti()).length()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					if (dbErrep.premiumDa(logData.getLogeatuta()) == false) {
						dispose();
						try {
							Iragarkia frame = new Iragarkia(logData, erreprodukzio.getAbestiList().get(erreprodukzio.getPosizioa()), selectedAlbum);
							frame.setVisible(true);
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
					lblirudia.setIcon(icon);

					// Aurreko abestira mugitu aurretik zein abestian dagoen gordetzen du
					// abestiaOrain aldagaian eta aurreko abestira mugitzen da.
					// Aurreko abestia eta abestiaOrain abestia berdinak badira jadanik azken kantan
					// zegoen eta mezu bat ateratzen du pantailara
					if (abestiOrain == erreprodukzio.getAbesti()) {
						JOptionPane.showMessageDialog(ErrepAbesti.this, "Ez daude kanta gehiago!");
					}
				} else {
					JOptionPane.showMessageDialog(ErrepAbesti.this,
							"Kanta bakarra saltatu dezakezu 10 minuturo, nahi izanez gero PREMIUM-a erosi eta salto ilimitatutak lortu");
				}
			}
		});
		btnAurrekoKanta.setBounds(352, 376, 54, 23);
		contentPane.add(btnAurrekoKanta);

		JButton btnHurrengoKanta = new JButton(">");
		btnHurrengoKanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long tOrain = System.currentTimeMillis();

				String abestiaOrain = erreprodukzio.getAbesti();
				// Free bada azken hamar minutuetan kanta aldatu duen konprobatzen du,
				// Erabiltzailea premium bada ere konprobatzen du
				if (tOrain - azkenKlika >= 10 * 1000 || dbErrep.premiumDa(logData.getLogeatuta()) == true) {

					btnPLAYSTOP.setText("PLAY");
					errepMetodoak.audioaAmatatu();
					azkenKlika = tOrain;
					Abestia autatutakoAbestiBerria = erreprodukzio.hurrengoKantara();
					ImageIcon icon = null;
					try {
						icon = new ImageIcon(dbErrep.irudiaLortu(erreprodukzio.getAbesti()).getBytes(1,
								(int) dbErrep.irudiaLortu(erreprodukzio.getAbesti()).length()));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					lblirudia.setIcon(icon);
					// Hurrengo abestira mugitu aurretik zein abestian dagoen gordetzen du
					// abestiaOrain aldagaian eta hurrengo abestira mugitzen da.
					// Hurrengo abestia eta abestiaOrain abestia berdinak badira jadanik azken
					// kantan zegoen eta mezu bat ateratzen du pantailara
					if (abestiaOrain == erreprodukzio.getAbesti()) {
						JOptionPane.showMessageDialog(ErrepAbesti.this, "Ez daude kanta gehiago");
					}
					String infoS = "Abestia: " + erreprodukzio.getAbesti() + "\n | Albuma: "
							+ selectedAlbum.getIzenburua() + "\n | Iraupena: " + autatutakoAbestiBerria.getIraupena();
					tFinfo.setText(infoS);
					
					if (dbErrep.premiumDa(logData.getLogeatuta()) == false){
						dispose();
						try {
							Iragarkia frame = new Iragarkia(logData,erreprodukzio.getAbestiList().get(erreprodukzio.getPosizioa()) , selectedAlbum);
							frame.setVisible(true);
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(ErrepAbesti.this,
							"Kanta bakarra saltatu dezakezu 10 minuturo, nahi izanez gero PREMIUM-a erosi eta salto ilimitatutak lortu");
				}
			}
		});

		btnHurrengoKanta.setBounds(590, 376, 54, 23);
		contentPane.add(btnHurrengoKanta);

		JButton btnNewButton_4 = new JButton("GUSTOKOAk");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBerrep dbErrep = new DBerrep();
				// Jadanik erabiltzailea kanta gustoko duen konprobatzen du
				if (dbErrep.gustokoaDu(logData.getLogeatuta(), erreprodukzio.getAbesti()) == false) {
					dbErrep.insertatuGustokoa(logData.getLogeatuta(), erreprodukzio.getAbesti());
					JOptionPane.showMessageDialog(ErrepAbesti.this, "Ondo gordeta", "", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(ErrepAbesti.this, "Kanta jadanik gustokoa duzu.", "Adi egon!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_4.setBounds(684, 376, 116, 23);
		contentPane.add(btnNewButton_4);

		tFDenbora = new JTextField();
		tFDenbora.setHorizontalAlignment(SwingConstants.CENTER);
		tFDenbora.setEditable(false);
		tFDenbora.setBounds(453, 345, 86, 20);
		contentPane.add(tFDenbora);
		tFDenbora.setColumns(10);

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
