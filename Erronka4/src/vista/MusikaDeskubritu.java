package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelo.Musikaria;
import Modelo.LogeazioDatuak;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MusikaDeskubritu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public MusikaDeskubritu(LogeazioDatuak logData) {
		 DB.DBmusika dbMusika = new DB.DBmusika();

		ArrayList<Musikaria> artistalist = dbMusika.musikaDeskrubitu();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Artisten zerrenda (Entzunaldi Kopurua)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(-12, 47, 288, 23);
		contentPane.add(lblNewLabel);

		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);

		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuAukeraketa menuaukeraketa = new MenuAukeraketa(logData);
				menuaukeraketa.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		
		
		JComboBox cbArtistak = new JComboBox();
		cbArtistak.setBounds(10, 70, 242, 23);
		contentPane.add(cbArtistak);

		for (int i = 0; i < artistalist.size(); i++) {
			cbArtistak.addItem(artistalist.get(i));
		}

		JButton btnJarraitu = new JButton("Jarraitu");
		btnJarraitu.setBounds(335, 197, 89, 23);
		contentPane.add(btnJarraitu);
		
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
		btnErabiltzailea.setBounds(279, 11, 145, 23);
		contentPane.add(btnErabiltzailea);
		btnErabiltzailea.setText(logData.getLogeatuta());

		btnJarraitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArtistaLeihoa artistaLehioa = new ArtistaLeihoa(logData,artistalist.get(cbArtistak.getSelectedIndex()));
				artistaLehioa.setVisible(true);
				dispose();

			}

		});

	}
}
