package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBuser;
import Modelo.logeazioDatuak;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class playListMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public playListMenu(DBuser db) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnPlaylistBerria = new JButton("Berria Sortu");
		btnPlaylistBerria.setBounds(312, 37, 112, 43);
		btnPlaylistBerria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					MenuAukeraketa frame = new MenuAukeraketa(db);
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			
			}
		});
		contentPane.add(btnAtzera);
		
		JButton btnPlaylistEzabatu = new JButton("Ezabatu");
		btnPlaylistEzabatu.setBounds(312, 91, 112, 43);
		contentPane.add(btnPlaylistEzabatu);
		
		JButton btnPlaylistInportatu = new JButton("Inportatu");
		btnPlaylistInportatu.setBounds(312, 147, 112, 43);
		contentPane.add(btnPlaylistInportatu);
		
		JButton btnPlaylistExportatu = new JButton("Exportatu");
		btnPlaylistExportatu.setBounds(312, 207, 112, 43);
		contentPane.add(btnPlaylistExportatu);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(20, 47, 255, 22);
		contentPane.add(comboBox);
	}
}
