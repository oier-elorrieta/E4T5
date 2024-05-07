package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Modelo.Albumak;
import Modelo.Musikaria;
import Modelo.logeazioDatuak;

import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ArtistaLeihoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ArtistaLeihoa(logeazioDatuak logData,Musikaria musikari) {
		DB.DBmusika dbMusika = new DB.DBmusika();
        ArrayList<Albumak> Albumlist = dbMusika.Albumlist(musikari.getIzenaartistikoa());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Atzera Botoia 
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);
		
		btnAtzera.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		        	MusikaDeskubritu musikadeskubritu = new MusikaDeskubritu(logData);
		        	musikadeskubritu.setVisible(true);
		            setVisible(false);
		            dispose();
		        }
		    });
		
		JLabel lblabeslaria = new JLabel("Abeslaria/Grupo");
		lblabeslaria.setBounds(111, 15, 170, 14);
		contentPane.add(lblabeslaria);
		
		JLabel lblNewLabel = new JLabel("Diska Zerrenda");
		lblNewLabel.setBounds(20, 43, 140, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Informazioa");
		lblNewLabel_1.setBounds(111, 273, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnErabiltzailea = new JButton("");
		btnErabiltzailea.setBounds(661, 11, 89, 23);
		contentPane.add(btnErabiltzailea);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 68, 410, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox cbAlbumak = new JComboBox();
		cbAlbumak.setBounds(10, 7, 376, 22);
		panel.add(cbAlbumak);
		
		for(int i=0; i<Albumlist.size();i++) {	
			cbAlbumak.addItem(Albumlist.get(i));
			}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 314, 768, 110);
		contentPane.add(scrollPane);
		
		JTextPane tPInfoArtista = new JTextPane();
		scrollPane.setViewportView(tPInfoArtista);
		tPInfoArtista.setText((String) null);
		tPInfoArtista.setEditable(false);
		scrollPane.setColumnHeaderView(tPInfoArtista);
		tPInfoArtista.setText(musikari.getDeskribapena());
		
		JButton btnNewButton = new JButton("Aurrera");
		btnNewButton.setBounds(10, 269, 89, 23);
		contentPane.add(btnNewButton);
		
		ImageIcon icon = null;
        try {
            icon = new ImageIcon(musikari.getIrudia().getBytes(1, (int) musikari.getIrudia().length()));
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(icon);
		lblNewLabel_2.setBounds(490, 107, 238, 144);
		contentPane.add(lblNewLabel_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					AlbumLeihoa frame = new AlbumLeihoa(logData,Albumlist.get(cbAlbumak.getSelectedIndex()));
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

	}		
}

