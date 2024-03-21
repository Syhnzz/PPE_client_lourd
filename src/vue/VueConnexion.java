package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Mairie;
import controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JPanel panelConnexion = new JPanel(); 
	private JTextField txtEmail = new JTextField("a@gmail.com"); 
	private JPasswordField txtMdp = new JPasswordField("123"); 
	private JButton btSeConnecter = new JButton("Se Connecter"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	
	public  VueConnexion() {
		this.setTitle("Mairie de Villier");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (0, 255, 255));
		this.setLayout(null);
		this.setBounds(200, 200, 600, 300);
		this.setResizable(false);
		
		//placement du logo 
		ImageIcon uneImage = new ImageIcon ("src/images/logo.png"); 
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(30, 20, 240, 220);
		this.add(unLogo);
		//placement du pannel 
		this.panelConnexion.setBounds(300, 40, 250, 200);
		this.panelConnexion.setBackground(new Color (0, 255, 255));
		this.panelConnexion.setLayout(new GridLayout(3,2));
		this.panelConnexion.add(new JLabel("Email : ")); 
		this.panelConnexion.add(this.txtEmail); 
		this.panelConnexion.add(new JLabel("MDP : ")); 
		this.panelConnexion.add(this.txtMdp);
		this.panelConnexion.add(this.btAnnuler); 
		this.panelConnexion.add(this.btSeConnecter);
		this.panelConnexion.setVisible(true);
		this.add(this.panelConnexion); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les txtEmail et txtMdp ecoutables 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
		 }
		 else if (e.getSource() == this.btSeConnecter) {
			 this.traitement (); 
		 }
		
	}

	public void traitement () {
		String email = this.txtEmail.getText(); 
		 String mdp = new String (this.txtMdp.getPassword()); 
		 
		 //on vérifie dans la base 
		 User unUser = Controleur.selectWhereUser(email, mdp); 
		 if (unUser != null) {
			 JOptionPane.showMessageDialog(this, "Bienvenue " + unUser.getNom() + "  " + unUser.getPrenom());
		 
			 //on appelle la vue générale 
			 Mairie.rendreVisibleGenerale(true, unUser);
			 Mairie.rendreVisibleConnexion(false);
		 }else {
			 JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
		 }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement ();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}







