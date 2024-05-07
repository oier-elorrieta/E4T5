package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelo.Musikaria;
import Modelo.Podcasterra;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PodcasterrakDeskubritu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PodcasterrakDeskubritu() {
		 
		DB.DBpodcast dbPodcast = new DB.DBpodcast();
		ArrayList<Podcasterra> podcasterList = dbPodcast.podcastDeskubritu();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Podcaster guztiak:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(-12, 47, 288, 23);
		contentPane.add(lblNewLabel);

		JButton btnErabiltzaile = new JButton("");
		btnErabiltzaile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnErabiltzaile.setBounds(335, 13, 89, 23);
		contentPane.add(btnErabiltzaile);

		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);

		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuAukeraketa menuaukeraketa = new MenuAukeraketa();
				menuaukeraketa.setVisible(true);
				dispose();
			}
		});
		
		
		
		JComboBox cbPodcaster = new JComboBox();
		cbPodcaster.setBounds(10, 70, 242, 23);
		contentPane.add(cbPodcaster);

		for (int i = 0; i < podcasterList.size(); i++) {
			cbPodcaster.addItem(podcasterList.get(i));
		}

		System.out.println(podcasterList);
		
		JButton btnJarraitu = new JButton("Jarraitu");
		btnJarraitu.setBounds(335, 197, 89, 23);
		contentPane.add(btnJarraitu);

		btnJarraitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				System.out.println("Sartuda");
				podcastGuztiak pdGuztiak = new podcastGuztiak(podcasterList.get(cbPodcaster.getSelectedIndex()));
				pdGuztiak.setVisible(true);
	            dispose();
	           

			}

		});

	}
}