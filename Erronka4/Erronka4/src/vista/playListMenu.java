package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBuser;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class playListMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

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
		contentPane.setLayout(null);
		
		JLabel lblPlaylistMenua = new JLabel("PlayList Menua\r\n");
		lblPlaylistMenua.setFont(new Font("Times New Roman", Font.BOLD, 33));
		lblPlaylistMenua.setBounds(96, 55, 223, 39);
		contentPane.add(lblPlaylistMenua);
		
		JButton btnPlaylistKudeatu = new JButton("Nire PlayList-ak kudeatu");
		btnPlaylistKudeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlaylistKudeatu.setBounds(124, 116, 160, 23);
		contentPane.add(btnPlaylistKudeatu);
		
		JButton btnPlaylistKudeatu_1 = new JButton("Nire PlayList-ak kudeatu");
		btnPlaylistKudeatu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlaylistKudeatu_1.setBounds(124, 157, 160, 23);
		contentPane.add(btnPlaylistKudeatu_1);
		
		JButton btnAtzera = new JButton("Atzera");
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
		btnAtzera.setBounds(24, 23, 89, 23);
		contentPane.add(btnAtzera);
	}
}
