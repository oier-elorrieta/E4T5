package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.logeazioDatuak;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAukeraketa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public MenuAukeraketa(logeazioDatuak logData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAukeratu = new JLabel("Aukeratu");
		lblAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		lblAukeratu.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAukeratu.setBounds(156, 37, 129, 54);
		contentPane.add(lblAukeratu);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(24, 23, 89, 23);
		contentPane.add(btnAtzera);
		
		JButton btnErabiltzaile = new JButton("");
		btnErabiltzaile.setBounds(316, 23, 89, 23);
		contentPane.add(btnErabiltzaile);
		
		JButton btnNirePlaylist = new JButton("Nire Playlist-ak");
		btnNirePlaylist.setBounds(131, 201, 183, 23);
		contentPane.add(btnNirePlaylist);
		
		JButton btnPodcastDesk = new JButton("Podcastak deskubritu");
		btnPodcastDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					PodcasterrakDeskubritu frame = new PodcasterrakDeskubritu();
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnPodcastDesk.setBounds(131, 154, 183, 23);
		contentPane.add(btnPodcastDesk);
		
		JButton btnMusikaDesk = new JButton("Musika deskubritu");
		btnMusikaDesk.setBounds(131, 106, 183, 23);
		contentPane.add(btnMusikaDesk);
		
		btnMusikaDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					MusikaDeskubritu frame = new MusikaDeskubritu(logData);
					frame.setVisible(true);
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
	}


	
	
	}
		
		
		
		
	

	

