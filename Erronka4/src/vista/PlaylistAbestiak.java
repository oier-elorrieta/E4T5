package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBerrep;
import DB.DBplaylist;
import Metodoak.InportatuExportatu;
import Modelo.Abestia;
import Modelo.LogeazioDatuak;
import Modelo.Playlist;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;

public class PlaylistAbestiak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param selectedPlaylist 
	 * @param playlist 
	 */
	public PlaylistAbestiak(LogeazioDatuak LogData, Playlist selectedPlaylist) {
		
		DBplaylist dbPlaylist = new DBplaylist();
		ArrayList<Abestia> abestiList= dbPlaylist.lortuAbestiak(selectedPlaylist.getIdlist());
		InportatuExportatu exportatu = new InportatuExportatu();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JButton btnErabiltzaile = new JButton("");
		btnErabiltzaile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		btnErabiltzaile.setBounds(682, 11, 87, 23);
		contentPane.add(btnErabiltzaile);
		btnErabiltzaile.setText(LogData.getLogeatuta()); 
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					playListMenu frame = new playListMenu(LogData);
					frame.setVisible(true);
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);
		
		JLabel lblPlaylista = new JLabel("");
		lblPlaylista.setBounds(163, 13, 114, 18);
		contentPane.add(lblPlaylista);
		lblPlaylista.setText(selectedPlaylist.getIzenburua());
		
		JLabel lblNewLabel = new JLabel("Playlista:");
		lblNewLabel.setBounds(109, 15, 60, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox cBPlaylistAbestiak = new JComboBox();
		cBPlaylistAbestiak.setBounds(10, 45, 631, 22);
		contentPane.add(cBPlaylistAbestiak);
		
		for(int i = 0; i<abestiList.size();i++) {
			cBPlaylistAbestiak.addItem(abestiList.get(i).toStringErrep());
		}
		
		JButton btnMenua = new JButton("+");
		btnMenua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Leihoa bistaratu bi aukera dituela
				int hautapen = JOptionPane.showOptionDialog(null, // Amaia componentea (kasu honetan, ez)
						"Hautatu aukera bat", // Erakutsi nahi den mezua
						"Menua", // Leihoaren izenburua
						JOptionPane.YES_NO_OPTION, // Hautapen mota (kasu honetan, bai/ez)
						JOptionPane.QUESTION_MESSAGE, // Mezu mota (kasu honetan, galdera)
						null, // Icono pertsonalizatua (kasu honetan, ez)
						new String[] { "Abestia konpartitu", "Ezabatu playList-etik" }, // Botoien testua
						"Aukera 1" // Aukera lehenetsia 
						);
				// Hautapena zein den egiaztatu 
				if (hautapen == JOptionPane.YES_OPTION) {
					
					if(exportatu.exportatuAbestia(abestiList.get(cBPlaylistAbestiak.getSelectedIndex()))==true) {
						JOptionPane.showMessageDialog(PlaylistAbestiak.this,
								"Fitxeroan ondo exportatuta", "Adi!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(PlaylistAbestiak.this,
								"Fitxeroan ez da exportatu", "Adi!",
								JOptionPane.WARNING_MESSAGE);
					}
					
					
					// Aukera 1 hautatu da
				} else if (hautapen == JOptionPane.NO_OPTION) {

					if(dbPlaylist.ezabatuKantaPlaylistik(selectedPlaylist.getIdlist(),abestiList.get(cBPlaylistAbestiak.getSelectedIndex()).getIdAudio())==true) {
						JOptionPane.showMessageDialog(PlaylistAbestiak.this,
								"Ezabatuta.", "Adi!",
								JOptionPane.WARNING_MESSAGE);
						dispose();
						try {
							PlaylistAbestiak frame = new PlaylistAbestiak(LogData, selectedPlaylist); 
							frame.setVisible(true);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						
					}else {
						
						JOptionPane.showMessageDialog(PlaylistAbestiak.this,
								"Ez da abestia ezabatu", "Adi!",
								JOptionPane.WARNING_MESSAGE);
					}
						
				} else {
					// Hautapena egin gabe leihoa ixten da
				}
			}
		});
		btnMenua.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnMenua.setBounds(682, 44, 89, 23);
		contentPane.add(btnMenua);
		
	}
}