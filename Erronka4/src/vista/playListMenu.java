package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBplaylist;
import DB.DBuser;
import Metodoak.InportatuExportatu;
import Modelo.LogeazioDatuak;
import Modelo.Playlist;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class playListMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final JComboBox<Playlist> cBPlaylistak = null;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public playListMenu(LogeazioDatuak LogData) {
		InportatuExportatu inportExport = new InportatuExportatu();
		DB.DBplaylist dbPlayilist = new DB.DBplaylist();
		ArrayList<Playlist> playlistList = dbPlayilist.lortuPlaylist(LogData);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JFrame PopUp = new JFrame("Playlist berria");
		PopUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PopUp.setSize(400, 300);

		JButton btnPlaylistBerria = new JButton("Berria Sortu");
		btnPlaylistBerria.setBounds(312, 37, 112, 43);
		btnPlaylistBerria.addActionListener(e -> {

			String PlaylistIzena = JOptionPane.showInputDialog(PopUp, "Sartu playlist berriaren izena");
			if (PlaylistIzena != null && !PlaylistIzena.isEmpty()) {
				dbPlayilist.insertPlaylist(PlaylistIzena, LogData);
				dispose();
				try {
					playListMenu frame = new playListMenu(LogData);
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(PopUp, "Errorea, Ez duzu Playlistaren izena sartu", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(btnPlaylistBerria);

		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(10, 11, 89, 23);
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					MenuAukeraketa frame = new MenuAukeraketa(LogData);
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}

			}
		});
		contentPane.add(btnAtzera);

		JComboBox cBPlaylist = new JComboBox();
		cBPlaylist.setBounds(20, 47, 255, 22);
		contentPane.add(cBPlaylist);

		for (int i = 0; i < playlistList.size(); i++) {
			cBPlaylist.addItem(playlistList.get(i));
		}

		JButton btnPlaylistEzabatu = new JButton("Ezabatu");
		btnPlaylistEzabatu.setBounds(312, 91, 112, 43);
		contentPane.add(btnPlaylistEzabatu);

		btnPlaylistEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dbPlayilist.ezabatuPlaylist(playlistList.get(cBPlaylist.getSelectedIndex()).getIdlist())) {
					JOptionPane.showMessageDialog(null, "Playlist ezabatu egin da.", "Ezabatzea",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					try {
						playListMenu frame = new playListMenu(LogData);
						frame.setVisible(true);
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Errorea, ezin izan da playlist-a ezabatu.", "Errorea",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton btnPlaylistInportatu = new JButton("Inportatu");
		btnPlaylistInportatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inportExport.inportatu(LogData);
				dispose();
				try {
					playListMenu frame = new playListMenu(LogData);
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		btnPlaylistInportatu.setBounds(312, 147, 112, 43);
		contentPane.add(btnPlaylistInportatu);

		JButton btnPlaylistExportatu = new JButton("Exportatu");
		btnPlaylistExportatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inportExport.exportatuPlaylist(playlistList.get(cBPlaylist.getSelectedIndex()))) {
				JOptionPane.showMessageDialog(null, "Playlista exportatu da", "Exportaxioa",
						JOptionPane.INFORMATION_MESSAGE);}
			}
		});
		btnPlaylistExportatu.setBounds(312, 207, 112, 43);
		contentPane.add(btnPlaylistExportatu);

		JButton btnAurrera = new JButton("Aurrera"); 
		btnAurrera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					PlaylistAbestiak frame = new PlaylistAbestiak(LogData, playlistList.get(cBPlaylist.getSelectedIndex())); 
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAurrera.setBounds(88, 221, 102, 29);
		contentPane.add(btnAurrera);

	}
}