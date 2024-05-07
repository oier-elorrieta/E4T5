package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.logeazioDatuak;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textErabiltzaile;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public login() {
		
		DB.DBuser dbuser = new DB.DBuser();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textErabiltzaile = new JTextField();
		textErabiltzaile.setBounds(203, 69, 86, 20);
		contentPane.add(textErabiltzaile);
		textErabiltzaile.setColumns(10);
		
		JLabel lblErabiltzaile = new JLabel("Erabiltzailea:");
		lblErabiltzaile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErabiltzaile.setHorizontalAlignment(SwingConstants.CENTER);
		lblErabiltzaile.setBounds(54, 69, 104, 17);
		contentPane.add(lblErabiltzaile);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasahitza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasahitza.setBounds(54, 113, 104, 17);
		contentPane.add(lblPasahitza);
		
		JComboBox cBAdmin = new JComboBox();
		cBAdmin.setBounds(143, 158, 146, 22);
		contentPane.add(cBAdmin);
		cBAdmin.addItem("ADMIN");
		cBAdmin.addItem("Erabiltzaile estandarra");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cBAdmin.getSelectedIndex()==1) {
					System.out.println("User bezala sartzen...");	
					
				try {
					
					if(dbuser.isLoginOk(textErabiltzaile.getText(),passwordField.getText())){
						dispose();
						
						logeazioDatuak logData = new logeazioDatuak(textErabiltzaile.getText());
						
						try {
							MenuAukeraketa frame = new MenuAukeraketa(logData);
							frame.setVisible(true);
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				}else {
					
					System.out.println("Admin bezala sartzen...");		
				}
						
			}
		});
		btnLogin.setBounds(69, 203, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnErregistratu = new JButton("Erregistratu");
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			dispose();
			
				try {
					Erregistratu frame = new Erregistratu();
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnErregistratu.setBounds(246, 203, 112, 23);
		contentPane.add(btnErregistratu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(203, 113, 86, 20);
		contentPane.add(passwordField);
	}
}
