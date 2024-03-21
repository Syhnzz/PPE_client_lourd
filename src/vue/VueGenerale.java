package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Mairie;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel(); 
	private JButton btRDVMairie = new JButton("Rendez vous Mairie"); 
	private JButton btActivite = new JButton("Activite"); 
	private JButton btUtilisateur = new JButton("Utilisateur"); 
	private JButton btCentreLoisir = new JButton("Centre Loisir");
	private JButton btEnfant = new JButton("Enfant"); 
	private JButton btStats = new JButton("Stats"); 
	private JButton btQuitter = new JButton("Quitter");
	private JButton btProfil = new JButton("Profil"); 
	
	//instanciation des panels : 
	private PanelProfil unPanelProfil ; 
	private PanelRDVMairie unPanelRDVMairie = new PanelRDVMairie(); 
	private PanelActivite unPanelActivite = new PanelActivite(); 
	private PanelUtilisateur unPanelUtilisateur = new PanelUtilisateur(); 
	private PanelCentreLoisir unPanelCentreLoisir = new PanelCentreLoisir(); 
	private PanelEnfant unPanelEnfant = new PanelEnfant(); 
	private PanelStats unPanelStats = new PanelStats(); 
	
	public VueGenerale(User unUser) {
		this.setTitle("Mairie de Villier");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color (0, 255, 255));
		this.setLayout(null);
		this.setBounds(200, 200, 1200, 500);
		this.setResizable(false);
		
		//instanciation du panel Profil 
		this.unPanelProfil = new PanelProfil(unUser);
		
		//placement du menu 
		this.panelMenu.setBounds(30, 10, 1200, 30);
		this.panelMenu.setBackground(new Color (0, 255, 255));
		this.panelMenu.setLayout(new GridLayout(1, 7));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btRDVMairie); 
		this.panelMenu.add(this.btActivite);
		this.panelMenu.add(this.btUtilisateur);
		this.panelMenu.add(this.btCentreLoisir);
		this.panelMenu.add(this.btEnfant);
		this.panelMenu.add(this.btStats);
		this.panelMenu.add(this.btQuitter);
		this.panelMenu.setVisible(true);
		this.add(this.panelMenu); 
		
		//rendre les boutons ecoutables 
		this.btQuitter.addActionListener(this);
		this.btRDVMairie.addActionListener(this);
		this.btEnfant.addActionListener(this);
		this.btCentreLoisir.addActionListener(this);
		this.btActivite.addActionListener(this);
		this.btUtilisateur.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btProfil.addActionListener(this);
		
		//ajout des panels dans la fenetre 
		this.add(this.unPanelProfil);
		this.add(this.unPanelRDVMairie);
		this.add(this.unPanelEnfant); 
		this.add(this.unPanelCentreLoisir); 
		this.add(this.unPanelActivite); 
		this.add(this.unPanelUtilisateur); 
		this.add(this.unPanelStats); 
		
		
		this.setVisible(true);
	}
	
	public void afficherPanel (int choix) {
		this.unPanelRDVMairie.setVisible(false);
		this.unPanelEnfant.setVisible(false);
		this.unPanelCentreLoisir.setVisible(false);
		this.unPanelActivite.setVisible(false);
		this.unPanelUtilisateur.setVisible(false);
		this.unPanelStats.setVisible(false);
		this.unPanelProfil.setVisible(false);
		
		switch(choix){
		case 1 : unPanelRDVMairie.setVisible(true); 		break;
		case 2 : unPanelActivite.setVisible(true); 		break;
		case 3 : unPanelUtilisateur.setVisible(true); 	break;
		case 4 : unPanelCentreLoisir.setVisible(true);  break;
		case 5 : unPanelEnfant.setVisible(true);  		break;
		case 6 : unPanelStats.setVisible(true);  		break;
		case 7 : unPanelProfil.setVisible(true);  		break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btQuitter) {
			 Mairie.rendreVisibleGenerale(false,null);
			 Mairie.rendreVisibleConnexion(true);
		 }
		 else if (e.getSource() == this.btRDVMairie) {
			 this.afficherPanel(1);
		 }
		 else if (e.getSource() == this.btActivite) {
			 this.afficherPanel(2);
		 }
		 else if (e.getSource() == this.btUtilisateur) {
			 this.afficherPanel(3);
		 }
		 else if (e.getSource() == this.btCentreLoisir) {
			 this.afficherPanel(4);
		 }
		 else if (e.getSource() == this.btEnfant) {
			 this.afficherPanel(5);
		 }
		 else if (e.getSource() == this.btStats) {
			 this.afficherPanel(6);
		 }
		 else if (e.getSource() == this.btProfil) {
			 this.afficherPanel(7);
		 }
	}

}