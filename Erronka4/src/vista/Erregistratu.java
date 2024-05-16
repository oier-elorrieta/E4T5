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


public class Erregistratu extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFIzena;
	private JTextField tFErabiltzailea;
	private JTextField tFJaioData;
	private JTextField tFErregistroData;
	private JTextField tFPremiumMuga;
	private JTextField tFAbizena;
	private JPasswordField pasahitza;
	private JPasswordField pasahitza2;


	public Erregistratu() {
		setResizable(false);
		DBuser db = new DBuser();

		
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
				
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);


		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(30, 45, 46, 14);
		contentPane.add(lblIzena);


		JLabel lblErabiltzailea = new JLabel("Erabiltzailea");
		lblErabiltzailea.setBounds(30, 70, 69, 14);
		contentPane.add(lblErabiltzailea);


		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(30, 95, 57, 14);
		contentPane.add(lblPasahitza);


		JLabel lblKonfirmatu = new JLabel("Konfirmatu");
		lblKonfirmatu.setBounds(30, 120, 69, 14);
		contentPane.add(lblKonfirmatu);


		JLabel lblJaiotzeData = new JLabel("Jaiotze data");
		lblJaiotzeData.setBounds(30, 145, 69, 14);
		contentPane.add(lblJaiotzeData);


		JLabel lblErregistroData = new JLabel("Erregistro data");
		lblErregistroData.setBounds(30, 170, 105, 14);
		contentPane.add(lblErregistroData);


		JLabel lblPremiunMuga = new JLabel("Premium muga");
		lblPremiunMuga.setBounds(30, 195, 105, 14);
		contentPane.add(lblPremiunMuga);


		JLabel lblHizkuntza = new JLabel("Hizkuntza");
		lblHizkuntza.setBounds(30, 220, 57, 14);
		contentPane.add(lblHizkuntza);


		tFIzena = new JTextField();
		tFIzena.setBounds(105, 42, 72, 20);
		contentPane.add(tFIzena);
		tFIzena.setColumns(10);


		tFErabiltzailea = new JTextField();
		tFErabiltzailea.setColumns(10);
		tFErabiltzailea.setBounds(105, 67, 140, 20);
		contentPane.add(tFErabiltzailea);


		tFJaioData = new JTextField();
		tFJaioData.setColumns(10);
		tFJaioData.setBounds(105, 142, 140, 20);
		contentPane.add(tFJaioData);


		tFErregistroData = new JTextField();
		tFErregistroData.setEditable(false);
		tFErregistroData.setColumns(10);
		tFErregistroData.setBounds(124, 167, 140, 20);
		contentPane.add(tFErregistroData);


		tFErregistroData.setText(Erabilgarriak.getDataGaur());


		tFPremiumMuga = new JTextField();
		tFPremiumMuga.setEditable(false);
		tFPremiumMuga.setColumns(10);
		tFPremiumMuga.setBounds(124, 192, 140, 20);
		contentPane.add(tFPremiumMuga);


		JComboBox cbHizkuntza = new JComboBox();
		cbHizkuntza.setBounds(105, 216, 72, 22);
		contentPane.add(cbHizkuntza);


		for (int i = 0; i < hizkuntzaGuztiak.size(); i++) {
			cbHizkuntza.addItem(hizkuntzaGuztiak.get(i));
		}


		JButton btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				if (Erabilgarriak.pasahitzBerdinak(pasahitza, pasahitza2)) {
					
					if (Erabilgarriak.dataBalidatu(tFJaioData.getText())) {
						
						if(db.datuakBeteta(tFIzena, tFAbizena, tFErabiltzailea, tFJaioData, pasahitza)) {
							
							UserFree user = new UserFree(tFIzena.getText(), tFAbizena.getText(), tFErabiltzailea.getText(),pasahitza.getText(), tFJaioData.getText());
							
							if(db.insertatuBezeroBerria(user,cbHizkuntza, tFPremiumMuga)) {
								dispose();
								// db objektuari Logeatuta atributua duena logaetu den erabiltzailearen
								LogeazioDatuak logData = new LogeazioDatuak(tFErabiltzailea.getText());
								
								try {
									MenuAukeraketa frame = new MenuAukeraketa(logData);
									frame.setVisible(true);
								} catch (Exception e3) {
									e3.printStackTrace();
								}	

							} else {
								JOptionPane.showMessageDialog(Erregistratu.this,
										"Erabiltzailea jadanik artuta dago, beste bat aukeratu!\nMesedez berriro saiatu.",
										"Kontuz!", JOptionPane.WARNING_MESSAGE);
							}			
						}else {
							JOptionPane.showMessageDialog(Erregistratu.this,
									"Datu guztiak bete!\nMesedez berriro saiatu.",
						  		"Kontuz!", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(Erregistratu.this,
								"Jaiotze data txarto sartuta\nHurrengo formatuan egon behar da: YYYY-MM-DD.\nMesedez berriro saiatu.",
								"Kontuz!", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// PASAHITZA EZ DA BERDINA
					JOptionPane.showMessageDialog(Erregistratu.this,
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
		btnNewButton_2.setBounds(228, 260, 162, 23);
		contentPane.add(btnNewButton_2);


		tFAbizena = new JTextField();
		tFAbizena.setBounds(255, 42, 89, 20);
		contentPane.add(tFAbizena);
		tFAbizena.setColumns(10);


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





