package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBmusika;
import Modelo.Abestia;
import Modelo.Albuma;
import Modelo.LogeazioDatuak;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AlbumLeihoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AlbumLeihoa(LogeazioDatuak logData,Albuma selectedAlbum) {
		DB.DBmusika dbMusika = new DBmusika();
		
		ArrayList<Abestia> abestiList = dbMusika.lortuAbestiak(selectedAlbum.getIzenburua()); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
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
		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);
		
		JLabel lblNewLabel = new JLabel("Album:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(107, 15, 46, 14);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 71, 360, 179);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(71, 7, 0, 0);
		panel.add(list);
		
		JComboBox cBAbesti = new JComboBox();
		cBAbesti.setBounds(10, 7, 340, 22);
		panel.add(cBAbesti);
		
		for(int i=0; i<abestiList.size();i++) {	
			cBAbesti.addItem(abestiList.get(i));
			}
		
		JLabel lblNewLabel_1 = new JLabel("Kanta Zerrenda:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 45, 106, 14);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(403, 71, 287, 179);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane tPInfo = new JTextPane();
		tPInfo.setBounds(10, 11, 267, 72);
		panel_1.add(tPInfo);
		tPInfo.setText("Abesti kopurua: "+selectedAlbum.getAbestiKop()+"\nGeneroa:"+selectedAlbum.getGeneroa()+"\nArgitaratze urtea: "+ selectedAlbum.getUrtea());
		
		JLabel lblNewLabel_2 = new JLabel("Albumaren Informazioa:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(393, 45, 153, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblAlbum = new JLabel("New label");
		lblAlbum.setBounds(151, 15, 237, 14);
		contentPane.add(lblAlbum);
		lblAlbum.setText(selectedAlbum.getIzenburua());
		
		JButton btnErreproduzitu = new JButton("Erreproduzitu");
		btnErreproduzitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					ErrepAbesti frame = new ErrepAbesti(logData,abestiList.get(cBAbesti.getSelectedIndex()),selectedAlbum);
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnErreproduzitu.setBounds(10, 261, 120, 23);
		contentPane.add(btnErreproduzitu);
		
		JButton btnErabiltzaile = new JButton("");
		btnErabiltzaile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ErabiltzaileDatuakAldatu frame = new ErabiltzaileDatuakAldatu(logData);
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnErabiltzaile.setBounds(584, 11, 153, 23);
		contentPane.add(btnErabiltzaile);
		btnErabiltzaile.setText(logData.getLogeatuta());
	}
}
