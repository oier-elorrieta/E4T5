package vista;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import DB.DBerrep;
import DB.DBmusika;
import Metodoak.ErrepMetodoak;
import Modelo.Abestiak;
import Modelo.Albumak;
import Modelo.Erreprodukzio;
import Modelo.logeazioDatuak;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class errepAbesti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFinfo;
	private long azkenKlika;

	public errepAbesti(logeazioDatuak logData,Abestiak selectedAbestia, Albumak selectedAlbum) {

		Erreprodukzio erreprodukzio = new Erreprodukzio(selectedAlbum.getIzenburua(),selectedAbestia.getIzenburua());
		ErrepMetodoak errepMetodoak = new ErrepMetodoak();
		DBerrep dbErrep = new DBerrep();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 991, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errepMetodoak.detenerClip();
				dispose();
				try {
					AlbumLeihoa frame = new AlbumLeihoa(logData,selectedAlbum);
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnAtzera.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtzera);
		
		JButton btnErabiltzailea = new JButton("");
		btnErabiltzailea.setBounds(876, 11, 89, 23);
		contentPane.add(btnErabiltzailea);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 437, 955, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		String infoS="Abestia: "+erreprodukzio.getAbesti()+"\n | Albuma: "+selectedAlbum.getIzenburua()+"\n | Iraupena: "+selectedAbestia.getIraupena();
		System.out.println(infoS);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(503, 14, 0, 0);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel_1);
		
		tFinfo = new JTextField();
		tFinfo.setHorizontalAlignment(SwingConstants.CENTER);
		tFinfo.setEditable(false);
		tFinfo.setBounds(10, 14, 935, 67);
		panel.add(tFinfo);
		tFinfo.setColumns(10);
		tFinfo.setText(infoS);
		
		JLabel lblNewLabel = new JLabel("Informazioa:");
		lblNewLabel.setBounds(26, 425, 95, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("MENUA");
		btnNewButton.setBounds(195, 376, 116, 23);
		contentPane.add(btnNewButton);
		
		ImageIcon icon = null;
        try {
            icon = new ImageIcon(selectedAbestia.getIrudia().getBytes(1, (int) selectedAbestia.getIrudia().length()));
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		JLabel lblirudia = new JLabel("");
		lblirudia.setIcon(icon);
		lblirudia.setBounds(380, 57, 506, 284);
		contentPane.add(lblirudia);
			
		JButton btnPLAYSTOP = new JButton("PLAY");
		btnPLAYSTOP.addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
			    	if(btnPLAYSTOP.getText()=="PLAY") {
			    		errepMetodoak.erreproduzitu(erreprodukzio.getAbesti());
			    		btnPLAYSTOP.setText("STOP");
			    	}else {
			    		btnPLAYSTOP.setText("PLAY");
			    		errepMetodoak.detenerClip();
			    	}
			    }
			});
		btnPLAYSTOP.setBounds(453, 376, 89, 23);
		contentPane.add(btnPLAYSTOP);
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    long tiempoActual = System.currentTimeMillis();
			    
			    String abestiOrain = erreprodukzio.getAbesti();
			    
                if (tiempoActual - azkenKlika >= 10 * 60 * 1000 || dbErrep.premiumDa(logData.getLogeatuta())==true) {
                	   
                	btnPLAYSTOP.setText("PLAY");
                	errepMetodoak.detenerClip();
                	Abestiak autatutakoAbestiBerria=erreprodukzio.aurrekoKantara();
                	String infoS="Abestia: "+erreprodukzio.getAbesti()+"\n | Albuma: "+selectedAlbum.getIzenburua()+"\n | Iraupena: "+autatutakoAbestiBerria.getIraupena();
					tFinfo.setText(infoS);
                    azkenKlika = tiempoActual;
                    
                    ImageIcon icon = null;	
                	try {
                        icon = new ImageIcon(dbErrep.irudiaLortu(erreprodukzio.getAbesti()).getBytes(1, (int) dbErrep.irudiaLortu(erreprodukzio.getAbesti()).length()));
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                	
                	lblirudia.setIcon(icon);
                    
                	if(abestiOrain == erreprodukzio.getAbesti()) {
                		 JOptionPane.showMessageDialog(errepAbesti.this, "Ez daude kanta gehiago!");
                	} 
                } else {
                    JOptionPane.showMessageDialog(errepAbesti.this, "Kanta bakarra saltatu dezakezu 10 minuturo, nahi izanez gero PREMIUM-a erosi eta salto ilimitatutak lortu");
                }
			}
		});
		btnNewButton_1.setBounds(352, 376, 54, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton(">");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 long tiempoActual = System.currentTimeMillis();
				 
				 String abestiaOrain = erreprodukzio.getAbesti();
				 
				 if (tiempoActual - azkenKlika >=10 * 60 * 1000 || dbErrep.premiumDa(logData.getLogeatuta())==true) {
					 
					 btnPLAYSTOP.setText("PLAY");
					 errepMetodoak.detenerClip();
					 azkenKlika = tiempoActual;
	                 Abestiak autatutakoAbestiBerria=erreprodukzio.hurrengoKantara();
	                 ImageIcon icon = null;	
	                	try {
	                        icon = new ImageIcon(dbErrep.irudiaLortu(erreprodukzio.getAbesti()).getBytes(1, (int) dbErrep.irudiaLortu(erreprodukzio.getAbesti()).length()));
	                    } catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                	
	                	lblirudia.setIcon(icon);
					 
	                 if(abestiaOrain==erreprodukzio.getAbesti()) {
	                	 JOptionPane.showMessageDialog(errepAbesti.this, "Ez daude kanta gehiago");
					 }
	                    String infoS="Abestia: "+erreprodukzio.getAbesti()+"\n | Albuma: "+selectedAlbum.getIzenburua()+"\n | Iraupena: "+autatutakoAbestiBerria.getIraupena();
	                    tFinfo.setText(infoS);
	                    
	                } else {
	                    JOptionPane.showMessageDialog(errepAbesti.this, "Kanta bakarra saltatu dezakezu 10 minuturo, nahi izanez gero PREMIUM-a erosi eta salto ilimitatutak lortu");
	                }	
			}
		});
		
		btnNewButton_3.setBounds(590, 376, 54, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("GUSTOKOAk");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBerrep dbErrep = new DBerrep();
				if(dbErrep.gustokoaDu(logData.getLogeatuta(),erreprodukzio.getAbesti())==false) {
					//Erabiltzailea Gorde behar dugu, hemen sartzeko
					dbErrep.insertatuGustokoa(logData.getLogeatuta(),erreprodukzio.getAbesti());
					JOptionPane.showMessageDialog(errepAbesti.this,"Ondo gordeta","", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(errepAbesti.this,
							"Kanta jadanik gustokoa duzu.",
							"Adi egon!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_4.setBounds(684, 376, 116, 23);
		contentPane.add(btnNewButton_4);
	}
}
