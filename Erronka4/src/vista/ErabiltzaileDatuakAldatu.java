package vista;

import Modelo.Erabiltzaile;
import Modelo.UserFree;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBuser;
import Metodoak.Erabilgarriak;
import Modelo.Erabiltzaile;
import Modelo.Hizkuntza;
import Modelo.Premium;
import Modelo.LogeazioDatuak;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ErabiltzaileDatuakAldatu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFIzena;
	private JTextField tFErabiltzailea;
	private JTextField tFJaioData;
	private JTextField tFPremiumMuga;
	private JTextField tFAbizena;
	private JPasswordField pasahitza;
	private JPasswordField pasahitza2; 

	public ErabiltzaileDatuakAldatu(LogeazioDatuak logData) {
		setResizable(false);
		DBuser db = new DBuser();
		
		ArrayList<String> erabiltzaileDatuak = db.lortuErabiltzaileDatuak(logData.getLogeatuta()); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ArrayList<Hizkuntza> hizkuntzaGuztiak = db.hizkuntzakLortu();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Atzera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		}); 

		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(30, 45, 46, 14);
		contentPane.add(lblIzena);

		JLabel lblErabiltzailea = new JLabel("Erabiltzailea");
		lblErabiltzailea.setBounds(30, 70, 72, 14);
		contentPane.add(lblErabiltzailea);

		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(30, 95, 57, 14);
		contentPane.add(lblPasahitza);

		JLabel lblKonfirmatu = new JLabel("Konfirmatu");
		lblKonfirmatu.setBounds(30, 120, 69, 14);
		contentPane.add(lblKonfirmatu);

		JLabel lblJaiotzeData = new JLabel("Jaiotze data");
		lblJaiotzeData.setBounds(30, 145, 129, 14);
		contentPane.add(lblJaiotzeData);

		JLabel lblPremiunMuga = new JLabel("Premiun muga");
		lblPremiunMuga.setBounds(30, 170, 129, 14);
		contentPane.add(lblPremiunMuga);

		JLabel lblHizkuntza = new JLabel("Hizkuntza");
		lblHizkuntza.setBounds(30, 201, 57, 14);
		contentPane.add(lblHizkuntza);

		tFIzena = new JTextField();
		tFIzena.setBounds(105, 42, 72, 20);
		contentPane.add(tFIzena);
		tFIzena.setColumns(10);
		
		tFIzena.setText(erabiltzaileDatuak.get(0));
		System.out.println(erabiltzaileDatuak.get(0));
		
		tFErabiltzailea = new JTextField();
		tFErabiltzailea.setEditable(false);
		tFErabiltzailea.setColumns(10);
		tFErabiltzailea.setBounds(105, 67, 140, 20);
		contentPane.add(tFErabiltzailea);
		
		tFErabiltzailea.setText(logData.getLogeatuta());

		tFJaioData = new JTextField();
		tFJaioData.setColumns(10);
		tFJaioData.setBounds(146, 142, 140, 20);
		contentPane.add(tFJaioData);
		
		tFJaioData.setText(erabiltzaileDatuak.get(2));
		
		tFPremiumMuga = new JTextField();
		tFPremiumMuga.setEditable(false);
		tFPremiumMuga.setColumns(10);
		tFPremiumMuga.setBounds(146, 167, 140, 20);
		contentPane.add(tFPremiumMuga);
		
		tFPremiumMuga.setText(erabiltzaileDatuak.get(3));
		
		JComboBox cbHizkuntza = new JComboBox();
		cbHizkuntza.setBounds(105, 197, 72, 22);
		contentPane.add(cbHizkuntza);

		for (int i = 0; i < hizkuntzaGuztiak.size(); i++) {
			cbHizkuntza.addItem(hizkuntzaGuztiak.get(i));
		}

		JButton btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (Erabilgarriak.pasahitzBerdinak(pasahitza, pasahitza2)) {

					if (Erabilgarriak.dataBalidatu(tFJaioData.getText())) {

						if (db.datuakBeteta(tFIzena, tFAbizena, tFErabiltzailea, tFJaioData, pasahitza)) {

							UserFree user = new UserFree(tFIzena.getText(), tFAbizena.getText(),logData.getLogeatuta(), pasahitza.getText(), tFJaioData.getText());
				
							if (db.erabiltzaileaEguneratu(user, cbHizkuntza, tFPremiumMuga, logData.getLogeatuta())) {

								JOptionPane.showMessageDialog(ErabiltzaileDatuakAldatu.this,"Datuen aldaketak gorde dira", "Adi egon!", JOptionPane.WARNING_MESSAGE);
								dispose();
								try {
									MenuAukeraketa frame = new MenuAukeraketa(logData);
									frame.setVisible(true);
								} catch (Exception e3) {
									e3.printStackTrace();
								}

							} else {
								JOptionPane.showMessageDialog(ErabiltzaileDatuakAldatu.this,
										"Erabiltzailea jadanik artuta dago, beste bat aukeratu!\nMesedez berriro saiatu.",
										"Kontuz!", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(ErabiltzaileDatuakAldatu.this,
									"Datu guztiak bete!\nMesedez berriro saiatu.", "Kontuz!",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(ErabiltzaileDatuakAldatu.this,
								"Jaiotze data txarto sartuta\nHurrengo formatuan egon behar da: YYYY-MM-DD.\nMesedez berriro saiatu.",
								"Kontuz!", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// PASAHITZA EZ DA BERDINA
					JOptionPane.showMessageDialog(ErabiltzaileDatuakAldatu.this,
							"Zure kredentzialak ez dira zuzenak.\nMesedez berriro saiatu.", "Kontuz!",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnGorde.setBounds(46, 260, 89, 23);
		contentPane.add(btnGorde);

		JButton btnNewButton_2 = new JButton("Erosi Premiun(+1 urte)");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tFPremiumMuga.getText().isEmpty()) {
					tFPremiumMuga.setText(Erabilgarriak.urteaGehitu(Erabilgarriak.getDataGaur()));
				} else {
					tFPremiumMuga.setText(Erabilgarriak.urteaGehitu(tFPremiumMuga.getText()));
				}

			}
		});
		btnNewButton_2.setBounds(218, 260, 172, 23);
		contentPane.add(btnNewButton_2);

		tFAbizena = new JTextField();
		tFAbizena.setBounds(255, 42, 89, 20);
		contentPane.add(tFAbizena);
		tFAbizena.setColumns(10);

		tFAbizena.setText(erabiltzaileDatuak.get(1));
		
		JLabel lblNewLabel = new JLabel("Abizena");
		lblNewLabel.setBounds(207, 45, 57, 14);
		contentPane.add(lblNewLabel);

		pasahitza = new JPasswordField();
		pasahitza.setBounds(105, 92, 140, 20);
		contentPane.add(pasahitza);

		pasahitza2 = new JPasswordField();
		pasahitza2.setBounds(105, 117, 140, 20);
		contentPane.add(pasahitza2);
	}
}